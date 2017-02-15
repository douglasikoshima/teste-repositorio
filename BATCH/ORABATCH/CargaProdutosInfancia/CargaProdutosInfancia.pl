#!/usr/opt/perl5/bin/perl_64bit -W

# Modulos perl
use strict;
use FindBin qw($Bin);
use Env qw(DB_NAME DB_USER DB_PASS DIR_DATA DIR_PRC DIR_ERR DIR_LOG PARAM_COMMIT);
use File::Basename;
use File::stat;
use DBI;
use DBD::Oracle qw(:ora_types);
use Data::Dumper;
use POSIX qw(strftime);
use IO::Handle;
use Fcntl qw(:flock SEEK_END);
use Fatal qw(chdir open opendir closedir rename seek flock);

# Autoflush output
STDERR->autoflush(1);
STDOUT->autoflush(1);

# Variaveis
my $FILEXT = '.txt';

# Nome deste pl
my $NAME = basename($0);

# Log
sub printm {
	my $now = strftime "%Y-%m-%d %H:%M:%S -- ", localtime;
	print $now, @_;
}

# Print inicial
printm <<BUFF;
>>> $NAME
DB_NAME: $DB_NAME
DB_USER: $DB_USER 
DIR_DATA: $DIR_DATA
DIR_PRC: $DIR_PRC
DIR_ERR: $DIR_ERR
DIR_LOG: $DIR_LOG
PARAM_COMMIT: $PARAM_COMMIT
PATH: $Bin
BUFF

# Muda para o dir deste script
chdir $Bin;

# Verifica execucao paralela
my $cfgfile = $NAME;
$cfgfile =~ s/\.pl$/.cfg/i;
open(my $fk, '>>', $cfgfile);
seek($fk, 0, SEEK_END);
flock($fk, LOCK_EX | LOCK_NB);

# Busca arquivos que serao lidos
printm "opendir: $DIR_DATA\n";
opendir(my $dh, $DIR_DATA);

# Somente arquivos .txt ordernados pela data de modificacao
my @data = sort {stat("$DIR_DATA/$a")->mtime <=> stat("$DIR_DATA/$b")->mtime} grep {/$FILEXT$/i} readdir($dh);
closedir $dh;

# Verifica se ha arquivos para carga
unless (scalar @data) {
	printm ">>> NAO HA ARQUIVOS PARA CARGA <<<\n";
	printm "<<< $NAME\n";
	exit 0;
}

# Conecta no banco de dados
printm "connect: $DB_NAME\n";
my $dbh = DBI->connect('dbi:Oracle:'.$DB_NAME, $DB_USER, $DB_PASS, {
	AutoCommit => 0,
	PrintError => 0,
	RaiseError => 1
});

# Obtem apoio.subsegmentacao
printm "Obtendo apoio.subsegmentacao...\n";
my $sth_ss = $dbh->prepare(q{
	SELECT SGSUBSEGMENTO FROM APOIO.SUBSEGMENTO
});
$sth_ss->execute or die $sth_ss->errstr;
my $subsegmentacao_href = $sth_ss->fetchall_hashref('SGSUBSEGMENTO');


# Busca arquivos a serem lidos
foreach my $file (@data) {
	# Variaveis
	local $_;
	my $fhe;
	my $reg_count = 0;
	my $err_count = 0;
	my $updt_count = 0;
	my $file_lines = 0;

	# Trunc na tabela de carga
	printm "Executando LOAD.TRUNC_PLANOSERVICOSUBSEGMENTO\n";
	my $sth_trunc = $dbh->prepare(q{
		BEGIN
		    LOAD.TRUNC_PLANOSERVICOSUBSEGMENTO();
		END;
	});
	$sth_trunc->execute;

	# Abre arquivo para leitura
	printm "open: $file\n";
	open(my $fh, "$DIR_DATA/$file");
	
	my $err_file = $file;
	$err_file =~ s/$FILEXT$/.err/i;

	# Le arquivo linha a linha
	printm "Lendo arquivo...\n";
		
	# Percorre arquivo
	while (<$fh>) {
		# Remove terminador de linha;
		chomp;
		
		# Se Registro valido
		if ( my($infancia, $idservico, $dssistemaorigem, $sgsubsegmentacao, $plano, $plataforma) = /^\s*(\d)\s*\|\s*(.+)\s*\|\s*(\w+)\s*\|\s*(\w+)\s*\|\s*(1|2)\s*\|\s*(\d*)\s*$/ ) 
		{
			### Ordem das Colunas
			# - INFANCIA – Definição se o produto é de classificação infância. 1 – Sim, 2 - Não
			# - IDSERVICO – Código de Integração do produto. (Obrigatório)
			# - DSSISTEMAORIGEM – Descrição Sistema Origem do Produto. (Obrigatório) 
			# - SGSUBSEGMENTACAO - Código da classificação Infância -  (obrigatório) 
			# - PLANO – Indicador se o produto é plano ou serviço. 1 – Plano, 2 - Serviço
			# - PLATAFORMA – 1 - Pré, 2 - Pós, 3 – Controle

			# Incrementa contador de registros
			$reg_count++;

			# Verifica indicador infancia
			unless ($infancia =~ m/1|2/) {
				# Incrementa contador de erro
				$err_count++;
						
				# Abre arquivo de log (err)
				open($fhe, '>', "$DIR_ERR/$err_file") unless $fhe;
	
				# Escreve registo com motivo de erro
				print $fhe "$_|Indicador Infancia Invalido\n";
				
				# Pula para o proximo registro
				next;
			}

			# Verifica se sgsubsegmentacao existe na base
			if(not exists $subsegmentacao_href->{$sgsubsegmentacao}) {		
				# Incrementa contador de erro
				$err_count++;
						
				# Abre arquivo de log (err)
				open($fhe, '>', "$DIR_ERR/$err_file") unless $fhe;
	
				# Escreve registo com motivo de erro
				print $fhe "$_|CODIGO SUBSEGMENTO nao encontrado em apoio.subsegmento\n";
				
				# Pula para o proximo registro
				next;
			}
			
			# Verifica plataforma
			if ($plataforma and not ($plataforma =~ m/1|2|3/)) {
				# Incrementa contador de erro
				$err_count++;
						
				# Abre arquivo de log (err)
				open($fhe, '>', "$DIR_ERR/$err_file") unless $fhe;
	
				# Escreve registo com motivo de erro
				print $fhe "$_|Plataforma Invalida\n";
				
				# Pula para o proximo registro
				next;
			}

			# Query sql
			my $sth = $dbh->prepare(q{
					INSERT INTO LOAD.PLANOSERVICOSUBSEGMENTO (
					  SGSUBSEGMENTO,
					  ININFANCIA,
					  TPPLATAFORMA,
				  SGSERVICO
					) VALUES (
					  :SGSUBSEGMENTO,
					  :ININFANCIA,
					  :TPPLATAFORMA,
				  :SGSERVICO
					)
				});
	
				# Parametros sql
				$sth->bind_param(':SGSUBSEGMENTO', $sgsubsegmentacao);
				$sth->bind_param(':ININFANCIA', $infancia);
				$sth->bind_param(':TPPLATAFORMA', $plataforma);
			$sth->bind_param(':SGSERVICO', $idservico);

				# Executa sql
				my $rv = $sth->execute;	

			
			# Incrementa contador de update
			$updt_count++;
			
			# Execute commit se atingiu limite de update
			if($updt_count && !($updt_count % $PARAM_COMMIT)) {
				printm "commit: $updt_count\n";
				$dbh->commit or die $dbh->errstr;
				
				# Imprime informacoes da carga
				printm "Registros lidos: $reg_count\n";
				printm "Registros descartados: $err_count\n";
				printm "Registros atualizados: $updt_count\n";
			}
		}
		# Se Registro invalido
		else {
			# Descarta linha em branco
			next if (/^\s*$/);
			
			# Incrementa contador de registros
			$reg_count++;
			
			# Incrementa contador de erro
			$err_count++;

			# Abre arquivo de log (err)
			open($fhe, '>', "$DIR_ERR/$err_file") unless $fhe;

			# Escreve registo invalido
			print $fhe "$_|Registro invalido\n";
		}
	}
	close $fhe if $fhe;
	
	# Commit
	if ($updt_count) {
		printm "commit: $updt_count\n";
		$dbh->commit or die $dbh->errstr;
	}

	# Imprime informacoes da carga
	printm "Registros lidos: $reg_count\n";
	printm "Registros descartados: $err_count\n";
	printm "Registros atualizados: $updt_count\n";
	
	printm "close: $file\n";
	close $fh;
	
	my $prc_file = $file;
	$prc_file =~ s/$FILEXT$/.prc/i;

	printm "move: $file -> $prc_file\n";
	rename("$DIR_DATA/$file", "$DIR_PRC/$prc_file");
}


# Merge na tabela de carga
printm "Executando LOAD.PRC_AtualizaSubSegPlanoServico\n";
my $sth_merge = $dbh->prepare(q{
	DECLARE
		CDERRO NUMBER;
		DSERRO VARCHAR2(255);
	BEGIN
		LOAD.PRC_AtualizaSubSegPlanoServico(VO_CDERRO => CDERRO, VO_DSERRO => DSERRO);
	END;
});
$sth_merge->execute;


# Disconecta do banco de dados
$dbh->disconnect;

# Remove lock
flock($fk, LOCK_UN);
close($fk) or warn "close: $!";

# Print final
printm "<<< $NAME\n";

