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
export CONNECTION=${ORABATCH_CONNECTION}
# ----------------------------------------------------------

# ARQUIVO 
export DATA_ATUAL=`date +"%d%m%Y"`
export LOG_PREFIX="expurgoFWTPR"
export LOG_HOJE=`date +"%Y%m%d"`
export LOG_ARQUIVO="${DIR_EXEC}/log/${LOG_PREFIX}_${LOG_HOJE}.log"
export LOG_SYSOUT="${DIR_EXEC}/log/${LOG_PREFIX}_${LOG_HOJE}.sysout.log"

# HORA LIMITE EXECUCAO (0 desabilita)
export HORA_LIMITE=0

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

log "Inicio do processo para exclusao "

cd ${DIR_PENDENTE}

for i in `ls *.sql*`
do

HORA_ATUAL=`date +"%H"`

if [ ${HORA_LIMITE} -eq 0 ] || [ ${HORA_ATUAL} -lt ${HORA_LIMITE} ]
then 

log "Processando arquivo ${i}"

sqlplus -s /nolog << EOF
whenever sqlerror exit sql.sqlcode rollback
connect ${CONNECTION}
execute DBMS_APPLICATION_INFO.SET_MODULE('EXPURGO FWT PR','ARQUIVO ${i}');
@@${i};
COMMIT;
exit
EOF

n=$?
	if [ $n -ne 0 ]
	then
		log "Erro no processamento do arquivo ${i}: ${n}"
		mv ${i} ../erro/
	else
		log "Arquivo ${i} processado com sucesso"
		mv ${i} ../processado/
	fi 

else
	log "Processo interrompido por exceder hrao limite: ${HORA_LIMITE}"	
	exit 0
fi

done

log "Fim do processo"

