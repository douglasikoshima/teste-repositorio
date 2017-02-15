#!/bin/bash

############################################################
# CARREGAMENTO DE ARQUIVOS COM CONFIGURACOES DE BANCO
############################################################
DIR_SHELL=`dirname $0`
cd ${DIR_SHELL} 
# . ../orabatch.cfg
. ./orabatch.cfg
############################################################

############################################################
# DIRETORIO DE EXECUCAO E PARAMETROS DE BANCO 
############################################################
# ----------------------------------------------------------
export DIR_EXEC=${DIR_SHELL}
export CONNECTION=${ORABATCH_CONNECTION}
# ----------------------------------------------------------
export SPOOL_PARAM=${ORABATCH_SPOOL_PARAM}
export OPTIMIZER_QUERY=${ORABATCH_OPTIMIZER_QUERY}
export OPTIMIZER_DDL=${ORABATCH_OPTIMIZER_DDL}
export OPTIMIZER_DML=${ORABATCH_OPTIMIZER_DML}
# ----------------------------------------------------------

# ARQUIVO 
export DATA_ATUAL=`date +"%d%m%Y"`
export LOG_PREFIX="INCLUSAO_PR"

#---------------------------------------------------
# Processo de inclusao no programa de relacionamento
#---------------------------------------------------

cd ${DIR_EXEC}

log ${LOG_PREFIX} "Inicio do da inclusaode clientes pos e controle"

sqlplus -s /nolog << EOF
whenever sqlerror exit sql.sqlcode rollback
connect ${CONNECTION}
execute DBMS_APPLICATION_INFO.SET_MODULE('INCLUSAO PR','INCLUSAO POS CONTROLE');
${OPTIMIZER_QUERY};
${OPTIMIZER_DDL};
${OPTIMIZER_DML};
@@inclusaoPR.sql;
COMMIT;
exit
EOF

log ${LOG_PREFIX} "Fim do processo"

