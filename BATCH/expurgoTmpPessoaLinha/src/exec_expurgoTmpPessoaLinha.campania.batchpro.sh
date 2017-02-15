export ORACLE_HOME=/opt/oracle/product/client/11.2.0/
export PATH=$PATH:$ORACLE_HOME/bin
export ORACLE_SID=fopa
export NLS_LANG=AMERICAN_AMERICA.WE8ISO8859P1

SQLLDR=${ORACLE_HOME}/bin/sqlldr
SQLPLUS=${ORACLE_HOME}/bin/sqlplus

SCRIPTDIR=/var/opt/batch/producao/deploy/processos/expurgoTmpPessoaLinha/bin
BADDIR=/var/opt/batch/producao/deploy/processos/expurgoTmpPessoaLinha/log
DSCDIR=/var/opt/batch/producao/deploy/processos/expurgoTmpPessoaLinha/log
LOGDIR=/var/opt/batch/producao/deploy/processos/expurgoTmpPessoaLinha/log

###############################################
### string de conexao ao BD
###############################################
CONNSTR=/@fopa

###############################################

${SQLPLUS} "${CONNSTR}" @${SCRIPTDIR}/expurgoTmpPessoaLinha.sql

RETCODE=`echo $?` 

case "${RETCODE}" in 
0) echo "Execucao concluida com EX_OK" ;; 
1) echo "Execucao concluida com EX_FAIL, verifique logfile" ;; 
2) echo "Execucao concluida com EX_WARN" ;; 
3) echo "Erro fatal na execucao" ;; 
*) echo "Erro desconhecido";; 
esac

if [ "${RETCODE}" != "0" -a "${RETCODE}" != "2" ]
then
  exit ${RETCODE}
fi