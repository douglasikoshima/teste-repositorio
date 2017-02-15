#!/bin/bash

############################################################
# CARREGAMENTO DE ARQUIVOS COM CONFIGURACOES DE BANCO
############################################################
DIR_SHELL=`dirname $0`
cd ${DIR_SHELL} 

. ./orabatch.cfg
############################################################
export ANO_MES="${1}"
export LOG_HOJE=`date +"%Y%m%d"`
export LOG_ARQUIVO="log/expurgo_${LOG_HOJE}.log"

log ()
{
    LOG_AGORA=`date +"%H:%M:%S"`
    echo "${LOG_AGORA} - ${1}" >> "${LOG_ARQUIVO}"
}


log "INICIO DO PROCESSO PARA ${ANO_MES}"

sqlplus -s /nolog << EOF
whenever sqlerror exit sql.sqlcode
connect ${ORABATCH_CONNECTION} 
set timing on time on
spool spool.dat

prompt "Criando particao para ATIS";
EXECUTE TIBCO_OW.PCK_CTRL_PARTITION.ADD_PARTITION ('TIBCO_OW', 'ATIS_CRM_PBLCTN', 'TBS_SINCRONISMO',  1, 30);

prompt "Criando particao para CSO";
EXECUTE TIBCO_OW.PCK_CTRL_PARTITION.ADD_PARTITION ('TIBCO_OW', 'CSO_CRM_PBLCTN', 'TBS_SINCRONISMO',  1, 30);

prompt "Criando particao para CONTROLESINCRONISMOFIXA";
EXECUTE SINCRONISMO.PCK_CTRL_PARTITION.ADD_PARTITION ('SINCRONISMO', 'CONTROLESINCRONISMOFIXA', 'TBS_SINCRONISMO',  1, 30);

exit sql.sqlcode 
EOF
cdErro=$?

cat spool.dat >> ${LOG_ARQUIVO}
if [ $cdErro -ne 0 ]
then
	log "Erro de banco $cdErro"
 	exit $cdErro
fi

