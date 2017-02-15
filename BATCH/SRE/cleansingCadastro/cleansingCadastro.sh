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
export LOG_PREFIX="cleansingCadastro"
export LOG_HOJE=`date +"%Y%m%d%H%M%S"`
export LOG_ARQUIVO="${DIR_EXEC}/log/${LOG_PREFIX}_${LOG_HOJE}.log"
export LOG_SYSOUT="${DIR_EXEC}/log/${LOG_PREFIX}_${LOG_HOJE}.sysout.log"


# ARQUIVOS
ARQ_DATA="DC_SRE_PONTUAL*.txt"
ARQ_INSERIDOS="DC_SRE_PONTUAL_INSERIDOS_${LOG_HOJE}.txt"
ARQ_REJEITADOS="DC_SRE_PONTUAL_REJEITADOS_${LOG_HOJE}.txt"

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

cd ${DIR_PENDENTE}

log "Varrendo todos os arquivos para carga"
for i in `ls $ARQ_DATA`
do

    #-------------------------------------------------------------------------------
    # Loader na tabela CARGA_CEP.
    #-------------------------------------------------------------------------------
    log "Loader do arquivo do ${i}"
    sqlldr userid=$CONNECTION data=${i} direct=y control=${DIR_EXEC}/loader.ctl

    n=$?
    if [ $n -ne 0 ]
    then
        log "Erro no Loader do arquivo do ${i}"
        exit 1
    fi 

    mv ${i} ${DIR_PROCESSADO}/

done

cd ${DIR_EXEC}

#-------------------------------------------------------------------------------
# Processamento.
#-------------------------------------------------------------------------------
log "processamento do resultado do loader na base do SRE"
sqlplus -s /nolog << EOF
whenever sqlerror exit sql.sqlcode rollback
connect $CONNECTION
SET ECHO ON
prompt "Processando Carga CEP"
execute ADM_PROJECT.CARGA_CEP.CARGA;
commit;
EXIT
EOF

n=$?
if [ $n -ne 0 ]
then
    log "Erro no procprocessamento do resultado do loader"
    exit 1
fi 

#-------------------------------------------------------------------------------
# Gerando arquivo dos inseridos com sucesso.
#-------------------------------------------------------------------------------
log "Gerando arquivo dos inseridos com sucesso."
sqlplus -s $CONNECTION << EOF > inseridos.log
whenever sqlerror exit sql.sqlcode rollback
${ORABATCH_SPOOL_PARAM}
@@inseridos.sql
exit 
EOF

n=$?
if [ $n -ne 0 ]
then
    log "Erro Gerando arquivo dos inseridos com sucesso."
    exit 1
fi 

mv inseridos.dat ${DIR_PROCESSADO}/${ARQ_INSERIDOS}

#-------------------------------------------------------------------------------
# Gerando arquivo dos rejeitados. .
#-------------------------------------------------------------------------------
log "Gerando arquivo dos rejeitados"
sqlplus -s $CONNECTION << EOF > rejeitados.log
whenever sqlerror exit sql.sqlcode rollback
${ORABATCH_SPOOL_PARAM}
@@rejeitados.sql
exit 
EOF

n=$?
if [ $n -ne 0 ]
then
    log "Gerando arquivo dos rejeitados"
    exit 1
fi 

mv rejeitados.dat ${DIR_PROCESSADO}/${ARQ_REJEITADOS}

log "Fim do processo"
exit 0

