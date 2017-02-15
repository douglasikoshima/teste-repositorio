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

prompt "Executando drop de particao para ATLYS";
EXECUTE TIBCO_OW.PCK_CTRL_PARTITION.DROP_PARTITION ('TIBCO_OW', 'ATLYS_CRM_PBLCTN', 90);

prompt "Executando drop de particao para NGIN";
EXECUTE TIBCO_OW.PCK_CTRL_PARTITION.DROP_PARTITION ('TIBCO_OW', 'NGN_LINHA_SP', 90);

prompt "Executando drop de particao para NGIN";
EXECUTE SINCRONISMO.PCK_CTRL_PARTITION.DROP_PARTITION ('SINCRONISMO', 'CONTROLESINCRONISMO', 90);

exit sql.sqlcode 
EOF
cdErro=$?

cat spool.dat >> ${LOG_ARQUIVO}
if [ $cdErro -ne 0 ]
then
	log "Erro de banco $cdErro"
 	exit $cdErro
fi

