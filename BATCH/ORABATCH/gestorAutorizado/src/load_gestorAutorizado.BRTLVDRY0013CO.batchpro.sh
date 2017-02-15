#ORACLE_HOME=/opt/oracle/9.2.0 
. ${HOME}/.profile
#ORACLE_HOME=/opt/oracle/product/client/11.2.0
#export PATH=$PATH:$ORACLE_HOME/bin
export ORACLE_SID=FOPAQA
export NLS_LANG=AMERICAN_AMERICA.WE8ISO8859P1

SQLLDR=${ORACLE_HOME}/bin/sqlldr
SQLPLUS=${ORACLE_HOME}/bin/sqlplus

FILENAME=$1
SCRIPTDIR=${HOME}/deploy/processos/ORABATCH/gestorAutorizado/bin
BADDIR=${HOME}/deploy/processos/ORABATCH/gestorAutorizado/proc
DSCDIR=${HOME}/deploy/processos/ORABATCH/gestorAutorizado/proc
LOGDIR=${HOME}/deploy/processos/ORABATCH/gestorAutorizado/log
DATADIR=${HOME}/deploy/processos/ORABATCH/gestorAutorizado/data
PRCDIR=${HOME}/deploy/processos/ORABATCH/gestorAutorizado/proc

###############################################
### string de conexao ao BD
###############################################
CONNSTR=/@FOPAQA

###############################################

EXTPRC=".prc"
INPUTFILE=${FILENAME}
INPUTFILE=`echo ${INPUTFILE} | awk -F / '{print $NF}'`
#echo $FILENAME

INPUTNAME=`echo ${INPUTFILE} | awk -F / '{print $NF}'`
INPUTNAME=`echo ${INPUTNAME} | cut -d"." -f1`
#echo $INPUTNAME

NAMEREN=${INPUTNAME}".`date +%Y%m%d_%H%M%S`"
#echo $NAMEREN


###############################################
# sqlldr
###############################################
${SQLLDR} userid=${CONNSTR} parfile=${SCRIPTDIR}/gestorAutorizado.par data=${DATADIR}/${INPUTFILE} log=${LOGDIR}/${NAMEREN}.log bad=${BADDIR}/${NAMEREN}.bad discard=${DSCDIR}/${NAMEREN}.dsc

RETCODE=`echo $?` 

case "${RETCODE}" in 
0) echo "SQL*Loader - execucao concluida com EX_OK" ;; 
1) echo "SQL*Loader - execucao concluida com EX_FAIL, verifique logfile" ;; 
2) echo "SQL*Loader - execucao concluida com EX_WARN" ;; 
3) echo "SQL*Loader - erro fatal na execucao" ;; 
*) echo "SQL*Loader - erro desconhecido";; 
esac

mv ${DATADIR}/${INPUTFILE} ${PRCDIR}/${NAMEREN}${EXTPRC}

if [ "${RETCODE}" != "0" -a "${RETCODE}" != "2" ]
then
  exit ${RETCODE}
fi
