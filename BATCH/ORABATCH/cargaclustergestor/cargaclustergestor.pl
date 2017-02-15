#!/usr/opt/perl5/bin/perl_64bit -W

# Modulos perl
use strict;
use FindBin qw($Bin);
use Env qw(DB_NAME DB_USER DB_PASS DIR_DATA DIR_PRC DIR_ERR DIR_LOG PARAM_VOLUMETRIA PARAM_COMMIT IDUSUARIOALTERACAO);
use File::Basename;
use File::stat;
use DBI;
use DBD::Oracle qw(:ora_types);
use Data::Dumper;
use POSIX qw(strftime);
use IO::Handle;
use Fcntl qw(:flock SEEK_END);
use Fatal qw(chdir open opendir closedir rename seek flock);
use Carp;

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
PARAM_VOLUMETRIA: $PARAM_VOLUMETRIA
PARAM_COMMIT: $PARAM_COMMIT
IDUSUARIOALTERACAO: $IDUSUARIOALTERACAO
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

# Imprime arquivos obtidos para carga
printm "Arquivo obtido: $_\n" foreach (@data);

# Conecta no banco de dados
printm "connect: $DB_NAME\n";
my $dbh = DBI->connect('dbi:Oracle:'.$DB_NAME, $DB_USER, $DB_PASS, {
	AutoCommit => 0,
	PrintWarn => 1,
	PrintError => 0,
	RaiseError => 0,
	ShowErrorStatement => 1,
	HandleError => sub { printm(shift) }
}) 
or die "connect to $DB_NAME: $DBI::errstr";


# Carrega apoio.clusterlinha
printm "Obtendo apoio.clusterlinha...\n";
my $sth_cl = $dbh->prepare(q{
	select idclusterlinha, dscluster from apoio.clusterlinha
});
$sth_cl->execute or die $sth_cl->errstr;
my $clusterlinha_href = $sth_cl->fetchall_hashref('IDCLUSTERLINHA');


# Busca arquivos a serem lidos
foreach my $file (@data) {
	# Variaveis
	local $_;
	my $fhe;
	my $reg_count = 0;
	my $err_count = 0;
	my $updt_count = 0;
	my $DSERROPROCESSAMENTO = 'SUCESSO';
	my $file_lines = 0;
	
	# Obtem nome do arquivo com extensao .err
	my $err_file = $file;
	$err_file =~ s/$FILEXT$/.err/i;

	# Abre arquivo para leitura
	printm "open: $file\n";
	open(my $fh, "$DIR_DATA/$file");

	# Total de linhas do arquivo
	$file_lines++ while <$fh>;
	printm "$file: $file_lines lines\n";
	
	# Verifica volumetria
	if ($file_lines > $PARAM_VOLUMETRIA) {
		$DSERROPROCESSAMENTO = "Arquivo $file ultrapassou limite de volumetria (PARAM_VOLUMETRIA=$PARAM_VOLUMETRIA)";
		printm "$DSERROPROCESSAMENTO\n";
		
		# Move arquivo para DIR_ERR
		printm "move: $file -> $err_file\n";
		rename("$DIR_DATA/$file", "$DIR_ERR/$err_file");
	}
	# Percorre conteudo do arquivo
	else {
		# Seta cursor para o inicio do arquivo
		seek($fh, 0, 0);
		
		# Le arquivo linha a linha
		printm "Lendo arquivo...\n";
		while (<$fh>) {
			# Remove terminador de linha;
			chomp;
			
			# Se Registro valido
			if ( my($linha, $cluster, $acao) = /^\s*(\d{9,11})\s*\|\s*(\d*)\s*\|\s*([Ii]|[Ee])\s*$/ ) 
			{
				# Incrementa contador de registros
				$reg_count++;
				
				# Verifica obrigatoriedade do cluster caso acao seja I
				if ($acao =~ /I/i and not $cluster) {
					# Incrementa contador de erro
					$err_count++;
					
					# Abre arquivo de log (err)
					open($fhe, '>', "$DIR_ERR/$err_file") unless $fhe;
		
					# Escreve registo com motivo de erro
					print $fhe "$_;Cluster nao informado para insercao\n";
					
					# Pula para o proximo registro
					next;
				}
				
				# Verifica se idclusterlinha existe na base
				if($acao =~ /I/i and not exists $clusterlinha_href->{$cluster}) {
					# Incrementa contador de erro
					$err_count++;
					
					# Abre arquivo de log (err)
					open($fhe, '>', "$DIR_ERR/$err_file") unless $fhe;
		
					# Escreve registo com motivo de erro
					print $fhe "$_;Cluster nao encontrado em apoio.clusterlinha\n";
					
					# Pula para o proximo registro
					next;
				}
				
				# AtualizaCluster
				my $sth_updt = $dbh->prepare(q{
		             UPDATE customer.pessoagestor
		             SET    IDCLUSTERLINHAGESTOR = :IDCLUSTERLINHAGESTOR
		             WHERE  NRTELEFONECELULARVIVO = :NRTELEFONECELULARVIVO
				});
				
				$sth_updt->bind_param(':IDCLUSTERLINHAGESTOR', $cluster) if ($acao =~ /I/i);
				$sth_updt->bind_param(':IDCLUSTERLINHAGESTOR', undef) if ($acao =~ /E/i);
				$sth_updt->bind_param(':NRTELEFONECELULARVIVO', $linha);
	
				# Executa sql
				unless ($sth_updt->execute) {
					printm "execute: ".$sth_updt->errstr."\n";
					
					# Incrementa contador de erro
					$err_count++;
					
					# Abre arquivo de log (err)
					open($fhe, '>', "$DIR_ERR/$err_file") unless $fhe;
		
					# Escreve registo com motivo de erro
					my $errstr = $sth_updt->errstr;
					chomp($errstr);
					print $fhe "$_;Erro Oracle: $errstr\n";
					
					# Pula para o proximo registro
					next;
				}
				
				# Se update nao alterou registro
				unless( $sth_updt->rows ) {
					# Tenta atualizar por NRTELEFONECELULAROUTRO
					my $sth_updt = $dbh->prepare(q{
			             UPDATE customer.pessoagestor
			             SET    IDCLUSTERLINHAGESTOR = :IDCLUSTERLINHAGESTOR
			             WHERE  NRTELEFONECELULAROUTRO = :NRTELEFONECELULAROUTRO
					});
					
					$sth_updt->bind_param(':IDCLUSTERLINHAGESTOR', $cluster) if ($acao =~ /I/i);
					$sth_updt->bind_param(':IDCLUSTERLINHAGESTOR', undef) if ($acao =~ /E/i);
					$sth_updt->bind_param(':NRTELEFONECELULAROUTRO', $linha);
					
					# Executa sql
					unless ($sth_updt->execute) {
						printm "execute: ".$sth_updt->errstr."\n";
						
						# Incrementa contador de erro
						$err_count++;
						
						# Abre arquivo de log (err)
						open($fhe, '>', "$DIR_ERR/$err_file") unless $fhe;
			
						# Escreve registo com motivo de erro
						my $errstr = $sth_updt->errstr;
						chomp($errstr);
						print $fhe "$_;Erro Oracle: $errstr\n";
						
						# Pula para o proximo registro
						next;
					}
					
					unless ($sth_updt->rows) {
						# Incrementa contador de erro
						$err_count++;
			
						# Abre arquivo de log (err)
						open($fhe, '>', "$DIR_ERR/$err_file") unless $fhe;
			
						# Escreve registo invalido
						print $fhe $_.";Linha nao encontrada em customer.pessoagestor\n";
						
						# Pula para o proximo registro
						next;
					}
				}	

				# Incrementa contador de update
				$updt_count++;
				
				# Execute commit se atingiu limite de update
				if ($updt_count && !($updt_count % $PARAM_COMMIT)) {
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
				print $fhe $_.";Registro invalido\n";
			}
		}
		# Fecha arquivo de erro
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
				
		# Fecha arquivo lido
		printm "close: $file\n";
		close $fh;
				
		# Obtem nome do arquivo com extensao .prc
		my $prc_file = $file;
		$prc_file =~ s/$FILEXT$/.prc/i;
	
		# Move arquivo para DIR_PRC
		printm "move: $file -> $prc_file\n";
		rename("$DIR_DATA/$file", "$DIR_PRC/$prc_file");	
		
		$DSERROPROCESSAMENTO = ($err_count ? 'FALHA - Ha registros rejeitados' : 'SUCESSO');
	}

	# Atualiza infra.arquivofuncionalidade
	printm "Atualiza infra.arquivofuncionalidade...\n";
	my $sth_af = $dbh->prepare(q{
		UPDATE infra.arquivofuncionalidade
		SET dtprocessamento = sysdate,
			DSERROPROCESSAMENTO = :DSERROPROCESSAMENTO,
			QTREGISTROSPROCESSADOS = :QTREGISTROSPROCESSADOS,
			QTREGISTROSDESCARTADOS = :QTREGISTROSDESCARTADOS,
			INCONTROLEPROCESSAMENTO = 3
		WHERE NMARQUIVO = :NMARQUIVO
		AND SGFUNCIONALIDADE = 'CCLSG'
	});
	$sth_af->bind_param(':DSERROPROCESSAMENTO', $DSERROPROCESSAMENTO);
	$sth_af->bind_param(':QTREGISTROSPROCESSADOS', $updt_count);
	$sth_af->bind_param(':QTREGISTROSDESCARTADOS', $err_count);
	$sth_af->bind_param(':NMARQUIVO', $file);
	$sth_af->execute or die $sth_af->errstr;
	
	# Se atualizacao nao efetivada em infra.arquivofuncionalidade entao cria registro
	unless($sth_af->rows) {
		$sth_af = $dbh->prepare(q{
			INSERT INTO infra.arquivofuncionalidade (
				SGFUNCIONALIDADE,
				NMARQUIVO,
				IDUSUARIOINCLUSAO,
				DTINCLUSAO,
				INCONTROLEPROCESSAMENTO,
				DTPROCESSAMENTO,
				QTREGISTROSPROCESSADOS,
				QTREGISTROSDESCARTADOS,
				DSERROPROCESSAMENTO
			) VALUES (
				'CCLSG',
				:NMARQUIVO,
				33374,
				SYSDATE,
				3,
				SYSDATE,
				:QTREGISTROSPROCESSADOS,
				:QTREGISTROSDESCARTADOS,
				:DSERROPROCESSAMENTO
			)
		});
		$sth_af->bind_param(':NMARQUIVO', $file);
		$sth_af->bind_param(':QTREGISTROSPROCESSADOS', $updt_count);
		$sth_af->bind_param(':QTREGISTROSDESCARTADOS', $err_count);
		$sth_af->bind_param(':DSERROPROCESSAMENTO', $DSERROPROCESSAMENTO);
		$sth_af->execute or die $sth_af->errstr;
	}
	
	# Commit final
	$dbh->commit or die $dbh->errstr;
}


# Desconecta do banco de dados
$dbh->disconnect or warn $dbh->errstr;

# Remove lock
flock($fk, LOCK_UN);
close($fk) or warn "close: $!";

# Print final
printm "<<< $NAME\n";

