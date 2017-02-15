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
export DIR_DATA=${DIR_EXEC}/data
export DIR_PROC=${DIR_EXEC}/processado
export DIR_LOG=${DIR_EXEC}/log
export CONNECTION=${ORABATCH_CONNECTION}
# ----------------------------------------------------------

# ARQUIVO 
export DATA_ATUAL=`date +"%d%m%Y"`
export LOG_PREFIX="cargaProgramaRelacionamento"
export LOG_HOJE=`date +"%Y%m%d"`
export LOG_ARQUIVO="${DIR_LOG}/${LOG_PREFIX}_${LOG_HOJE}.log"
export LOG_SYSOUT="${DIR_LOG}/${LOG_PREFIX}_${LOG_HOJE}.sysout.log"

export HORA_LIMITE=0

#---------------------------------------------------
# LOG do processo
#---------------------------------------------------
# @param  $1 Nome do arquivo de log sem extensao
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

log "Inicio do processo de loader"

cd ${DIR_DATA}

for i in `ls`
do

HORA_ATUAL=`date +"%H"`

if [ ${HORA_LIMITE} -eq 0 ] || [ ${HORA_ATUAL} -lt ${HORA_LIMITE} ]
then 

	if [ ${i: -3} == ".gz" ]
	then
		log "Descompactando arquivo ${i}"
		gzip -d ${i}
		arquivo=`echo $i | sed "s/^\(.*\)\..*$/\1/"`
	else 
		log "Arquivo ${i} nao precisa ser descompactado"
		arquivo=$i
	fi

	log "Processando arquivo ${arquivo}"

NLS_DATE_FORMAT='YYYY-MM-DD HH24:MI:SS'
export NLS_DATE_FORMAT

sqlldr userid=${CONNECTION} control=${DIR_EXEC}/cargaProgramaRelacionamento.ctl data=${DIR_DATA}/${arquivo} log=${DIR_LOG}/${arquivo}.log bad=${DIR_LOG}/${i}.bad discard=${DIR_LOG}/${arquivo}.dsc errors=500000 direct=y

mv $arquivo ${DIR_PROC}/

else
	log "Processo interrompido por exceder hora limite: ${HORA_LIMITE}"	
	exit 0
fi

done

log "Fim do processo"

