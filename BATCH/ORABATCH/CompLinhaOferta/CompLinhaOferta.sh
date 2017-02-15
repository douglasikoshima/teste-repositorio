#!/bin/sh

IFS='
'
. ${HOME}/.profile

MYDIR=$(dirname $0)
cd $MYDIR
. ./CompLinhaOferta.cfg

DIR_LOG="./log"
DIR_SQL="./sql"

DATA=`date '+%Y%m%d%H%M'`
ARQLOG="$DIR_LOG/CompLinhaOferta-$DATA.log"

# Locale oracle
export NLS_LANG=AMERICAN_AMERICA.WE8ISO8859P1

echo ">>> CompLinhaOferta" >$ARQLOG
echo "***** Iniciando o Processamento *****" >>$ARQLOG

sqlplus $USR/$PWD@$SID @$DIR_SQL/CompLinhaOferta.sql "$DIR_LOG/CompLinhaOferta-$DATA.spool" >>$ARQLOG 2>&1

echo "***** Processamento Finalizado *****" >>$ARQLOG
echo "<<< CompLinhaOferta" >>$ARQLOG
exit 0
