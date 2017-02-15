#!/bin/sh

IFS='
'
. ${HOME}/.profile
MYDIR=$(dirname $0)
cd $MYDIR
. ./arvoreVendas.cfg

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
#CONNSTR="STA_MIG/STA_MIG@INTDEVA"
CONNSTR="$USR/$PWD@$SID"
###############################################
export NLS_LANG=AMERICAN_AMERICA.WE8ISO8859P1

#nohup sqlplus "$CONNSTR" @p3.sql & >> "$DIR_LOG/P3.log" 2>&1
nohup sqlplus "$CONNSTR" @p3.sql > "$DIR_LOG/P3_$DATA.log" &
retcode=`echo $?` 
case "$retcode" in 
0) echo "SQL P3 executado com sucesso." ;; 
1) echo "SQL P3 executado com erro EX_FAIL, ver logfile." ;; 
2) echo "SQL P3 executado com erro EX_WARN, ver logfile." ;; 
3) echo "SQL P3 executado com erro fatal." ;; 
*) echo "codigo de retorno desconhecido.";; 
esac
