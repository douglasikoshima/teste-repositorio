. ${HOME}/.profile
#export ORACLE_HOME=/opt/oracle/9.2.0
#export ORACLE_HOME=/opt/oracle/product/client/11.2.0
#export PATH=$PATH:$ORACLE_HOME/bin
export ORACLE_SID=FOD1
export NLS_LANG=AMERICAN_AMERICA.WE8ISO8859P1

SQLLDR=${ORACLE_HOME}/bin/sqlldr
SQLPLUS=${ORACLE_HOME}/bin/sqlplus

FILENAME=$1
SCRIPTDIR=${HOME}/deploy/processos/planoservicolinha_tmp/bin
BADDIR=${HOME}/deploy/processos/planoservicolinha_tmp/log
DSCDIR=${HOME}/deploy/processos/planoservicolinha_tmp/log
LOGDIR=${HOME}/deploy/processos/planoservicolinha_tmp/log
DATADIR=${HOME}/deploy/processos/planoservicolinha_tmp/data

###############################################
### string de conexao ao BD
###############################################
CONNSTR=/@FOD1

###############################################

INPUTFILE=${FILENAME}
echo $FILENAME

READFILE=`echo ${INPUTFILE} | cut -c9-255`
INPUTNAME=`echo ${INPUTFILE} | cut -c9-255`
INPUTNAME=`echo ${INPUTNAME} | cut -d"." -f1`
echo $INPUTNAME
${SQLPLUS} "${CONNSTR}" @${SCRIPTDIR}/trunc_planoservicolinha_tmp.sql
###############################################
# sqlldr
###############################################
${SQLLDR} userid=${CONNSTR} parfile=${SCRIPTDIR}/planoservicolinha_tmp.par data=${DATADIR}/${READFILE} log=${LOGDIR}/${INPUTNAME}.log bad=${BADDIR}/${INPUTNAME}.bad discard=${DSCDIR}/${INPUTNAME}.dsc

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

${SQLPLUS} "${CONNSTR}" @${SCRIPTDIR}/planoservicolinha_tmp.sql ${LOGDIR}/${INPUTNAME}.trc

${SQLPLUS} "${CONNSTR}" @${SCRIPTDIR}/descarte.sql ${LOGDIR}/${INPUTNAME}.err