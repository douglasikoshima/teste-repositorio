#!/bin/bash

DIR_SHELL=`dirname $0`
DIR_LOG=${DIR_SHELL}/log
ARQ_NAME=`basename $0 | cut -d. -f1`
DAT_EXT="csv"

# PARAMETROS PARA CONTROLE DO PROCESSO 
HOJE=`date +"%Y%m%d"`

# ARQUIVO DE LOG
ARQ_DAT=${1}
ARQ_LOG=${DIR_LOG}/${ARQ_NAME}_${HOJE}.log

#---------------------------------------------------
# LOG do processo
#---------------------------------------------------
log ()
{
	AGORA=`date +"%H:%M:%S"`
	echo "${AGORA} - ${1}" >> ${ARQ_LOG}
}

cd ${DIR_SHELL}

log "INICIO processo controlador"

for i in `ls *.${DAT_EXT}`
do
	log "Executando loader para ${i} and background"
	./loaderpessoasubsegmento.sh ${i} &
done

wait

log "FIM processo controlador"