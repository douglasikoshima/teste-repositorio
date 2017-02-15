#!/usr/opt/perl5/bin/perl_64bit -W

# Modulos perl
use strict;
use FindBin qw($Bin);
use Env qw(DB_NAME DB_USER DB_PASS DIR_DATA DIR_PRC DIR_ERR DIR_LOG DIR_TMP PARAM_VOLUMETRIA PARAM_COMMIT);
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
my $IDUSUARIOALTERACAO = 0;
my $FILEPREFIX = 'carga_cluster';
my $FILEXT = '.txt';
my $FILESUFFIXMASK = "_%Y%m%d-%H%M%S";

# Nome deste pl
my $NAME = basename($0);

# Log
sub printm {
	my $now = strftime "%Y-%m-%d %H:%M:%S -- ", localtime;
	print $now, @_;
	return $now, @_;
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
DIR_TMP: $DIR_TMP
PARAM_VOLUMETRIA: $PARAM_VOLUMETRIA
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
	my $fhl;
	my $reg_count = 0;
	my $err_count = 0;
	my $updt_count = 0;
	my $DSERROPROCESSAMENTO = 'SUCESSO';
	my $file_lines = 0;
	my $FILESUFFIX = strftime $FILESUFFIXMASK, localtime;
	
	# Obtem nome do arquivo com extensao .err
	my $err_file = $file;
	$err_file =~ s/$FILEXT$/$FILESUFFIX.err/i;

	# Obtem nome do arquivo com extensao .log
	my $log_file = $file; 
	$log_file =~ s/$FILEXT$/$FILESUFFIX.log/i;

	# Obtem nome do arquivo com extensao .prc
	my $prc_file = $file;
	$prc_file =~ s/$FILEXT$/.prc/i;

	# Verifica prefixo no nome do arquivo
	if ($file =~ m/^$FILEPREFIX/i)
	{
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
		
			# Fecha arquivo lido
			printm "close: $file\n";
			close $fh;
			
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
			if ( my($linha, $cluster, $acao) = /^\s*(\d{9,11})\s*;\s*(\d*)\s*;\s*([Rr]|[Mm])\s*$/ ) 
			{
				# Incrementa contador de registros
				$reg_count++;
				
				# Verifica obrigatoriedade do cluster caso acao seja M
				if ($acao =~ /M/i and not $cluster) {
					# Incrementa contador de erro
					$err_count++;
					
					# Abre arquivo de log (err)
						open($fhe, '>', "$DIR_TMP/$err_file") unless $fhe;
		
					# Escreve registo com motivo de erro
					print $fhe "$_;Cluster nao informado para marcacao\n";
					
					# Pula para o proximo registro
					next;
				}
				
				# Verifica se idclusterlinha existe na base
				if($acao =~ /M/i and not exists $clusterlinha_href->{$cluster}) {
					# Incrementa contador de erro
					$err_count++;
					
					# Abre arquivo de log (err)
						open($fhe, '>', "$DIR_TMP/$err_file") unless $fhe;
		
					# Escreve registo com motivo de erro
					print $fhe "$_;Cluster nao encontrado em apoio.clusterlinha\n";
					
					# Pula para o proximo registro
					next;
				}
				
				# Busca Linha Telefonica
				my $sth_val = $dbh->prepare(q{
					SELECT 	/*+ PARALLEL (ltf,8) PARALLEL (lbs,8) */
						ltf.idlinhatelefonica , ltf.idclusterlinha
					FROM 
						linha.linhabase lbs
					,	apoio.arearegistro arg
					,	linha.linhatelefonica ltf
					WHERE   ltf.idlinhabase = lbs.idlinhabase
					AND	lbs.nrlinha = substr(:NRLINHA, 3)
					AND	arg.idarearegistro = lbs.idarearegistro
					AND	arg.cdarearegistro = substr(:CDAREAREGISTRO, 1, 2)
				});
				
				# Parametros sql
				$sth_val->bind_param(':NRLINHA', $linha);
				$sth_val->bind_param(':CDAREAREGISTRO', $linha);
				
				# Executa sql
				unless ($sth_val->execute) {
					printm "execute: ".$sth_val->errstr."\n";
					
					# Incrementa contador de erro
					$err_count++;
					
					# Abre arquivo de log (err)
						open($fhe, '>', "$DIR_TMP/$err_file") unless $fhe;
		
					# Escreve registo com motivo de erro
					my $errstr = $sth_val->errstr;
					chomp($errstr);
					print $fhe "$_;Erro Oracle: $errstr\n";
					
					# Pula para o proximo registro
					next;
				}
				
				while(my($idlinhatelefonica, $idclusterlinha) = $sth_val->fetchrow_array()) {
					#printm "$_;Encontrado idlinhatelefonica=$idlinhatelefonica, idclusterlinha=".(defined $idclusterlinha ? $idclusterlinha : 'NULL')."\n";
					
					# AtualizaCluster
					my $sth_updt = $dbh->prepare(q{
			             UPDATE linha.linhatelefonica
			             SET    idclusterlinha = :IDCLUSTERLINHA
			             WHERE  idlinhatelefonica = :IDLINHATELEFONICA
					});
					
					$sth_updt->bind_param(':IDCLUSTERLINHA', $cluster) if ($acao =~ /M/i);
					$sth_updt->bind_param(':IDCLUSTERLINHA', undef) if ($acao =~ /R/i);
					$sth_updt->bind_param(':IDLINHATELEFONICA', $idlinhatelefonica);
		
					# Executa sql
					unless ($sth_updt->execute) {
						printm "execute: ".$sth_updt->errstr."\n";
						
						# Incrementa contador de erro
						$err_count++;
						
						# Abre arquivo de log (err)
							open($fhe, '>', "$DIR_TMP/$err_file") unless $fhe;
			
						# Escreve registo com motivo de erro
						my $errstr = $sth_updt->errstr;
						chomp($errstr);
						print $fhe "$_;Erro Oracle: $errstr\n";
						
						# Pula para o proximo registro
						next;
					}
		
					# Se update nao alterou registro
					unless( $sth_updt->rows ) {
						# Incrementa contador de erro
						$err_count++;
						
						# Abre arquivo de log (err)
							open($fhe, '>', "$DIR_TMP/$err_file") unless $fhe;
			
						# Escreve registo com motivo de erro
						print $fhe "$_;UPDATE em linha.linhatelefonica para idlinhatelefonica=$idlinhatelefonica retornou zero\n";
	
						# Pula para o proximo registro
						next;
					}	
					
					# Incrementa contador de update
					#$updt_count += $sth_updt->rows;
					$updt_count++;
					
					# Execute commit se atingiu limite de update
					unless($updt_count % $PARAM_COMMIT) {
						printm "commit: $updt_count\n";
						$dbh->commit or die $dbh->errstr;
						
						# Imprime informacoes da carga
						printm "Registros lidos: $reg_count\n";
						printm "Registros descartados: $err_count\n";
						printm "Registros atualizados: $updt_count\n";
					}
				}
				
				# Se nao encontrou registro em linha.linhabase, apoio.arearegistro, linha.linhatelefonica
				unless ($sth_val->rows) {
					# Incrementa contador de erro
					$err_count++;
		
					# Abre arquivo de log (err)
						open($fhe, '>', "$DIR_TMP/$err_file") unless $fhe;
		
					# Escreve registo invalido
					print $fhe $_.";Linha nao encontrada em linha.linhatelefonica, linha.linhabase, apoio.arearegistro\n";
					
					# Pula para o proximo registro
					next;
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
					open($fhe, '>', "$DIR_TMP/$err_file") unless $fhe;
	
				# Escreve registo invalido
				print $fhe $_.";Layout do registro esta inconsistente\n";
			}
		}
		# Fecha arquivo de erro
		close $fhe if $fhe;
		
		# Commit
		if ($updt_count) {
			printm "commit: $updt_count\n";
			$dbh->commit or die $dbh->errstr;
		}
	
			# Cria arquivo de log 
			open($fhl, '>', "$DIR_TMP/$log_file");
			
		# Imprime informacoes da carga
			print $fhl &printm("Registros lidos: $reg_count\n");
			print $fhl &printm("Registros descartados: $err_count\n");
			print $fhl &printm("Registros atualizados: $updt_count\n");
				
			# se nao houve critica			
		if (!$err_count) {
				print $fhl &printm("Arquivo processado com sucesso.\n");
		}
		else {
				print $fhl &printm("Arquivo processado com críticas.\n");
		}
				
			# Fecha arquivo de log
			close $fhl;
					
		# Fecha arquivo lido
		printm "close: $file\n";
		close $fh;
				
			# Move arquivo para DIR_ERR
			printm "move: $DIR_TMP/$log_file -> $DIR_ERR/$log_file\n";
			rename("$DIR_TMP/$log_file", "$DIR_ERR/$log_file");
		
			if (-e "$DIR_TMP/$err_file") {
				printm "move: $DIR_TMP/$err_file -> $DIR_ERR/$err_file\n";
				rename("$DIR_TMP/$err_file", "$DIR_ERR/$err_file");
			}
		
		# Move arquivo para DIR_PRC
		printm "move: $file -> $prc_file\n";
		rename("$DIR_DATA/$file", "$DIR_PRC/$prc_file");	
		
		$DSERROPROCESSAMENTO = ($err_count ? 'FALHA - Ha registros rejeitados' : 'SUCESSO');
	}
	} 
	# Nomenclatura do arquivo invalida
	else 
	{
		&printm("Descartando arquivo: $file\n");
		
		# Mensagem de erro
		$DSERROPROCESSAMENTO = 'Nomenclatura do arquivo de origem invalida';
		
		# Cria arquivo de log 
		open($fhl, '>', "$DIR_ERR/$log_file");
		print $fhl &printm("$DSERROPROCESSAMENTO\n");
		close $fhl;
		
		# Move arquivo para DIR_PRC
		printm "move: $file -> $prc_file\n";
		rename("$DIR_DATA/$file", "$DIR_PRC/$prc_file");
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
		AND SGFUNCIONALIDADE = 'CCLTR'
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
				'CCLTR',
				:NMARQUIVO,
				602,
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

# Retorno Sucesso
exit 0
