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
export CONNECTION=${ORABATCH_CONNECTION}
# ----------------------------------------------------------

# LOG 
export DATA_ATUAL=`date +"%d%m%Y"`
export LOG_PREFIX="expurgoPreVivoValoriza"
export LOG_HOJE=`date +"%Y%m%d"`
export LOG_ARQUIVO="${DIR_EXEC}/log/${LOG_PREFIX}_${LOG_HOJE}.log"
export LOG_SYSOUT="${DIR_EXEC}/log/${LOG_PREFIX}_${LOG_HOJE}.sysout.log"
export LOG_SPOOL="${DIR_EXEC}/log/${LOG_PREFIX}_${LOG_HOJE}.spool.log"

# ----------------------------------------------------------
# PARALELISMO
# Total de processos iniciados em paralelo para exclusao
# ----------------------------------------------------------
export TOTAL_PARTICOES=8
# ----------------------------------------------------------

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


log "========================================"
log "== INICIANDO"
log "========================================"
log "DIR_SHELL...............: $DIR_SHELL"
log "DIR_EXEC................: $DIR_EXEC"
log "CONNECTION..............: $CONNECTION"
log "DATA_ATUAL..............: $DATA_ATUAL"
log "LOG_PREFIX..............: $LOG_PREFIX"
log "LOG_HOJE................: $LOG_HOJE"
log "LOG_ARQUIVO.............: $LOG_ARQUIVO"
log "LOG_SYSOUT..............: $LOG_SYSOUT"
log "TOTAL_PARTICOES.........: $TOTAL_PARTICOES"
log "========================================"


cd ${DIR_EXEC}

log "Inicio do processo para expurgo"

for (( i=1; i<=${TOTAL_PARTICOES}; i++ ))
do
	
	log "Executando expurgo particao ${i}/${TOTAL_PARTICOES} : sqlplus -s ${CONNECTION} @expurgoPreVivoValoriza.sql ${i} ${TOTAL_PARTICOES} \"${LOG_SPOOL}_${i}\" &"
	sqlplus -s ${CONNECTION} @expurgoPreVivoValoriza.sql ${i} ${TOTAL_PARTICOES} "${LOG_SPOOL}_${i}" &
	
done

wait

log "Fim da execução dos processos em paralelo"

log "Fim do processo"

