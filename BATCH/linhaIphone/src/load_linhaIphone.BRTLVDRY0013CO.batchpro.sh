. ${HOME}/.profile
#export ORACLE_HOME=/opt/oracle/9.2.0
#export PATH=$PATH:$ORACLE_HOME/bin
export ORACLE_SID=FOPAQA
export NLS_LANG=AMERICAN_AMERICA.WE8ISO8859P1

SQLLDR=${ORACLE_HOME}/bin/sqlldr
SQLPLUS=${ORACLE_HOME}/bin/sqlplus

FILENAME=$1
SCRIPTDIR=${HOME}/deploy/processos/linhaIphone/bin
BADDIR=${HOME}/deploy/processos/linhaIphone/log
DSCDIR=${HOME}/deploy/processos/linhaIphone/log
LOGDIR=${HOME}/deploy/processos/linhaIphone/log
DATADIR=${HOME}/deploy/processos/linhaIphone/data

###############################################
### string de conexao ao BD
###############################################
CONNSTR=/@FOPAQA

###############################################

INPUTFILE=${FILENAME}
echo $FILENAME

INPUTNAME=`echo ${INPUTFILE} | cut -d"." -f1`
echo $INPUTNAME
${SQLPLUS} "${CONNSTR}" @${SCRIPTDIR}/load_linhaIphone.sql
###############################################
# sqlldr
###############################################
${SQLLDR} userid=${CONNSTR} parfile=${SCRIPTDIR}/load_linhaIphone.par data=${DATADIR}/${INPUTFILE} log=${LOGDIR}/${INPUTNAME}.log bad=${BADDIR}/${INPUTNAME}.bad discard=${DSCDIR}/${INPUTNAME}.dsc

RETCODE=`echo $?` 

case "${RETCODE}" in 
0) echo "SQL*Loader - execucao concluida com EX_OK" ;; 
1) echo "SQL*Loader - execucao concluida com EX_FAIL, verifique logfile" ;; 
2) echo "SQL*Loader - execucao concluida com EX_WARN" ;; 
3) echo "SQL*Loader - erro fatal na execucao" ;; 
*) echo "SQL*Loader - erro desconhecido";; 
esac

if [ "${RETCODE}" != "0" -a "${RETCODE}" != "2" ]
then
  exit ${RETCODE}
fi