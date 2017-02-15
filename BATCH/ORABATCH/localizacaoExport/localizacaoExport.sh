#!/bin/sh

IFS='
'

DTM=$(date '+%Y%m%d-%H%M%S')
LOG="Log/$DTM.log"

DIR=$(dirname $0)
cd "$DIR"

. "${HOME}/.profile"

export NLS_LANG=AMERICAN_AMERICA.WE8ISO8859P1
. ./localizacaoExport.cfg

## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## 
#  CONNSTR="indralog/vivo2010@fod1"
#  CONNSTRCAT="catalogoprs_cn/vivocat@soabdev"
## ## ## ## ## ## ## ## ## ## ## ## ## ## ## ## 
CONNSTR="$USR/$PWD@$SID"
CONNSTRCAT="$USRCAT/$PWDCAT@$SIDCAT"

echo INI SPOOL - `date +"%d/%m/%Y %X"` > $LOG 2>&1
sqlplus -s -l "$CONNSTR" @query_spool.sql >> $LOG 2>&1

if [ $? -ne 0 ] ; then
    echo An error occurred while SPOOL - `date +"%d/%m/%Y %X"` >> $LOG 2>&1
    echo ERROR > Log/database.log
    exit 1 
fi
echo FIM SPOOL - `date +"%d/%m/%Y %X"` >> $LOG 2>&1

echo INI LOADER - `date +"%d/%m/%Y %X"` >> $LOG 2>&1
sqlldr userid="$CONNSTRCAT" parfile=./localizacaoExport.par data=export.apoio.localidadeosb7.txt log=export.apoio.localidadeosb7.log bad=export.apoio.localidadeosb7.bad discard=export.apoio.localidadeosb7.dsc >> $LOG 2>&1
echo FIM LOADER - `date +"%d/%m/%Y %X"` >> $LOG 2>&1

echo INI PROCEDURE - `date +"%d/%m/%Y %X"` >> $LOG 2>&1
sqlplus -s -l "$CONNSTRCAT" @call_procedure.sql >> $LOG 2>&1
rm -r export.apoio.localidadeosb7.* >> $LOG 2>&1
if [ $? -ne 0 ] ; then
    echo An error occurred while CALL PROCEDURE - `date +"%d/%m/%Y %X"` >> $LOG 2>&1
    echo ERROR > Log/database.log
    exit 1 
fi
echo FIM PROCEDURE - `date +"%d/%m/%Y %X"` >> $LOG 2>&1
exit 0