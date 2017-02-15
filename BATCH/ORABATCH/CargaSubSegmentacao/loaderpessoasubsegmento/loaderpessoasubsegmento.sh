#!/usr/bin/bash

DIR_SHELL=`dirname $0`
DIR_LOG=${DIR_SHELL}/log

. ../CargaSubSegmentacao.cfg

# PARAMETROS PARA CONTROLE DO PROCESSO 
HOJE=`date +"%Y%m%d"`

# ARQUIVO DE LOG
ARQ_DAT=${1}
ARQ_LOG=${DIR_LOG}/${ARQ_DAT}_${HOJE}.log

#---------------------------------------------------
# LOG do processo
#---------------------------------------------------
log ()
{
	AGORA=`date +"%H:%M:%S"`
	echo "${AGORA} - ${1}" >> ${ARQ_LOG}
}

cd ${DIR_SHELL} 

exec 1> ${ARQ_LOG}.sysout
exec 2>> ${ARQ_LOG}.sysout

log "INICIO loader ${ARQ_DAT}"

sqlldr userid=${DB_USER}/${DB_PASS}@${DB_NAME} control=loaderpessoasubsegmento.ctl bad=${DIR_LOG}/${ARQ_DAT}.bad log=${DIR_LOG}/${ARQ_DAT}.log data=${ARQ_DAT} rows=1000 errors=50000000 direct=false parallel=true

n=$?
if [ $n -ne 0 ]
then
	log "ERRO ${n} loader ${ARQ_DAT}"
	exit $n
fi 

log "FIM loader ${ARQ_DAT}"
