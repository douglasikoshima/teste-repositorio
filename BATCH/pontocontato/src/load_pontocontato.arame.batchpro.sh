export ORACLE_HOME=/opt/oracle/9.2.0
export PATH=$PATH:$ORACLE_HOME/bin
export ORACLE_SID=fopa
export NLS_LANG=AMERICAN_AMERICA.WE8ISO8859P1

SQLLDR=${ORACLE_HOME}/bin/sqlldr
SQLPLUS=${ORACLE_HOME}/bin/sqlplus

FILENAME=$1
SCRIPTDIR=/var/opt/batch/producao/deploy/processos/pontocontato/bin
BADDIR=/var/opt/batch/producao/deploy/processos/pontocontato/log
DSCDIR=/var/opt/batch/producao/deploy/processos/pontocontato/log
LOGDIR=/var/opt/batch/producao/deploy/processos/pontocontato/log

DATADIR=/var/opt/batch/producao/deploy/processos/pontocontato/data

###############################################
### string de conexao ao BD
###############################################
CONNSTR=/@fopa
###############################################

INPUTFILE=${FILENAME}

INPUTNAME=`echo ${INPUTFILE} | cut -d"." -f1`
INPUTLOGNAME=`echo ${INPUTFILE} | cut -c9-49`

###############################################
# Truncando a Tabela Load.PontoContato
###############################################
${SQLPLUS} "${CONNSTR}" @${SCRIPTDIR}/preload.sql
RETCODE=`echo $?`
case "${RETCODE}" in
0) echo "Trunc Load.PontoContato - execucao concluida com EX_OK" ;;
1) echo "Trunc Load.PontoContato - execucao concluida com EX_FAIL, verifique logfile" ;;
2) echo "Trunc Load.PontoContato - execucao concluida com EX_WARN" ;;
3) echo "Trunc Load.PontoContato - erro fatal na execucao" ;;
*) echo "Trunc Load.PontoContato - erro desconhecido";;
esac

if [ "${RETCODE}" != "0" ]
then
LINES=`wc -l ${INPUTFILE} | cut -c 2-8` 
  ${SQLPLUS} "${CONNSTR}" @${SCRIPTDIR}/errload.sql ${LINES} ${LINES} ${INPUTFILE}
  exit ${RETCODE}
fi

###############################################
# sqlldr
###############################################
${SQLLDR} userid=${CONNSTR} parfile=${SCRIPTDIR}/load_pontocontato.par data=${DATADIR}/${INPUTFILE} log=${LOGDIR}/SQLLDR${INPUTLOGNAME}.log bad=${BADDIR}/${INPUTLOGNAME}.bad discard=${DSCDIR}/${INPUTLOGNAME}.dsc
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
LINES=`wc -l ${INPUTFILE} | cut -c 2-8` 
ERRLINES=`wc -l ${BADDIR}/${INPUTLOGNAME}.bad | cut -c 2-8`
  ${SQLPLUS} "${CONNSTR}" @${SCRIPTDIR}/errload.sql ${ERRLINES} ${LINES} ${INPUTFILE}
  exit ${RETCODE}
fi

if [ "${RETCODE}" = "2" ]
then
LINES=`wc -l ${INPUTFILE} | cut -c 2-8` 
ERRLINES=`wc -l ${BADDIR}/${INPUTLOGNAME}.bad | cut -c 2-8`
  ${SQLPLUS} "${CONNSTR}" @${SCRIPTDIR}/errload.sql ${ERRLINES} ${LINES} ${INPUTFILE}
  ${SQLPLUS} "${CONNSTR}" @${SCRIPTDIR}/falhaload.sql ${INPUTFILE}
fi

###############################################
# Executando a procedure Vol.CargaPontoContato
###############################################
${SQLPLUS} "${CONNSTR}" @${SCRIPTDIR}/posload.sql ${INPUTFILE}
RETCODE=`echo $?`

case "${RETCODE}" in
0) echo "Carga Load.PontoContato - execucao concluida com EX_OK" ;;
1) echo "Carga Load.PontoContato - execucao concluida com EX_FAIL, verifique logfile" ;;
2) echo "Carga Load.PontoContato - execucao concluida com EX_WARN" ;;
3) echo "Carga Load.PontoContato - erro fatal na execucao" ;;
*) echo "Carga Load.PontoContato - erro desconhecido";;
esac

if [ "${RETCODE}" != "0" ]
then
  exit ${RETCODE}
fi


exit ${RETCODE}

