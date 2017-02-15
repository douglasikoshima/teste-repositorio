#!/bin/bash

############################################################
# CARREGAMENTO DE ARQUIVOS COM CONFIGURACOES DE BANCO
############################################################
DIR_SHELL=`dirname $0`
cd ${DIR_SHELL} 

. ./orabatch.cfg
############################################################

############################################################
# DIRETORIO DE EXECUCAO E PARAMETROS DE BANCO 
############################################################
# ----------------------------------------------------------
export DIR_EXEC=`pwd`
export DIR_PENDENTE=${DIR_EXEC}/pendente
export DIR_PROCESSADO=${DIR_EXEC}/processado
export CONNECTION=${ORABATCH_CONNECTION}
# ----------------------------------------------------------

# ARQUIVO  LOG
export DATA_ATUAL=`date +"%Y%m%d%H%M%S"`
export LOG_PREFIX="cleansingRollback"
export LOG_HOJE=`date +"%Y%m%d%H%M%S"`
export LOG_ARQUIVO="${DIR_EXEC}/log/${LOG_PREFIX}_${LOG_HOJE}.log"
export LOG_SYSOUT="${DIR_EXEC}/log/${LOG_PREFIX}_${LOG_HOJE}.sysout.log"


# ARQUIVOS
ARQ_ROLLBACK="DC_SRE_PONTUAL_ROLLBACK_LOG_${LOG_HOJE}.txt"

#---------------------------------------------------
# LOG do processo
#---------------------------------------------------
# @param  $1 Nome do arquivo de log sem extensao
# @param  $2 Texto a ser incluido no arquivo
#---------------------------------------------------
log ()
{
    
    LOG_AGORA=`date +"%H:%M:%S"`
    echo "${LOG_AGORA} - ${1}" >> "${LOG_ARQUIVO}"
}

#---------------------------------------------------
# Processo de inclusao no programa de relacionamento
#---------------------------------------------------

exec 1> ${LOG_SYSOUT}
exec 2>> ${LOG_SYSOUT}

cd ${DIR_EXEC}

log "Inicio do processo"

#-------------------------------------------------------------------------------
# Gerar Rollback.
#-------------------------------------------------------------------------------
log "processamento para geracao de script de rollback"
sqlplus -s /nolog << EOF
whenever sqlerror exit sql.sqlcode rollback
connect $CONNECTION
SET ECHO ON
prompt "Processando Carga CEP"
execute ADM_PROJECT.CARGA_CEP.GERAR_ROLLBACK;
commit;
EXIT
EOF

n=$?
if [ $n -ne 0 ]
then
    log "Erro processamento para geracao de script de rollback"
    exit 1
fi 

#-------------------------------------------------------------------------------
# Gerando arquivo do rollback.
#-------------------------------------------------------------------------------
log "Gerando arquivo do rollback"
sqlplus -s $CONNECTION << EOF > rollback.log
whenever sqlerror exit sql.sqlcode rollback
${ORABATCH_SPOOL_PARAM}
set linesize 4000
@@rollback.sql
exit 
EOF

n=$?
if [ $n -ne 0 ]
then
    log "Erro gerando arquivo do rollback"
    exit 1
fi 

mv rollback.dat ${DIR_PROCESSADO}/${ARQ_ROLLBACK}

log "Fim do processo"
exit 0

