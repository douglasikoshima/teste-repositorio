#!/bin/sh

IFS='
'
MYDIR=$(dirname $0)
cd $MYDIR
. ./corrigePessoaDoc.cfg

echo $MYDIR
DIR_LOG="./log"
DATADIR="./data"
CTRLDIR="./ctrl"
###############################################
### Modo de captura Data
###############################################
DATA=`date '+%Y%m%d%H%M'` #Data + Ano, Mes, Dia, Hora, Minuto
###############################################
### string de conexao ao BD
###############################################
CONNSTR="$USR/$PWD@$SID"
###############################################
export NLS_LANG=AMERICAN_AMERICA.WE8ISO8859P1

exec 1> $DIR_LOG/corrigePessoaDoc_$DATA.log
exec 2>> $DIR_LOG/corrigePessoaDoc_$DATA.log

	echo "Inicio do Processo";
	
	LIMITE=1000
	
	nohup sqlplus "$CONNSTR" @PESDOC_P01.sql P1 1 $LIMITE &	
	nohup sqlplus "$CONNSTR" @PESDOC_P02.sql P2 2 $LIMITE &	
	nohup sqlplus "$CONNSTR" @PESDOC_P03.sql P3 3 $LIMITE &	
	nohup sqlplus "$CONNSTR" @PESDOC_P04.sql P4 4 $LIMITE &	
	nohup sqlplus "$CONNSTR" @PESDOC_P05.sql P5 5 $LIMITE &	
	nohup sqlplus "$CONNSTR" @PESDOC_P06.sql P6 6 $LIMITE &	
	nohup sqlplus "$CONNSTR" @PESDOC_P07.sql P7 7 $LIMITE &	
	nohup sqlplus "$CONNSTR" @PESDOC_P08.sql P8 8 $LIMITE &
		
	echo "Fim do Processo";
	
retcode=`echo $?` 
case "$retcode" in 
0) echo "SQL executado com sucesso." ;; 
1) echo "SQL executado com erro EX_FAIL, ver logfile." ;; 
2) echo "SQL executado com erro EX_WARN, ver logfile." ;; 
3) echo "SQL executado com erro fatal." ;; 
*) echo "codigo de retorno desconhecido.";; 
esac	