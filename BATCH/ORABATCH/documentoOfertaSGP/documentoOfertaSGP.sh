#!/bin/bash
############################################################
#  BATCH......: documentoOfertaSGP
#  Criado por.: Lucas Gomes (29/09/2015)
#  Funcional..: Dener Neves dos Santos
############################################################

############################################################
# CARREGAMENTO DE ARQUIVOS COM CONFIGURACOES DE BANCO
############################################################
export LOG_PREFIX="DOCUMENTO_OFERTA_SGP"
DIR_SHELL=`dirname $0`
cd ${DIR_SHELL} 

BASE_NAME=orabatch
DIR_CFG=cfg
CONFIG_FILE_DEF=${DIR_CFG}/${BASE_NAME}.$(hostname).$(whoami).cfg
CONFIG_FILE=${BASE_NAME}.cfg
if [ -e ${CONFIG_FILE_DEF} ]
then
	# Carrega configuracao para maquina.usuario
	. ./${CONFIG_FILE_DEF}
elif [ -e ${CONFIG_FILE} ]
then
	# Carrega configuracao padrao
	. ./${CONFIG_FILE}
else
	# Arquivo de configuracao nao encontrado
	echo "ERRO: Arquivos de configuracao (${CONFIG_FILE_DEF} ou ${CONFIG_FILE}) nao encontrados!" 
	exit -1
fi
############################################################
# Ajusta arquivos da pasta data antes de efetuar a carga
############################################################
# dos2unix
find ./data -type f -name '*.[Tt][Xx][Tt]' -exec perl -i -pe 's/\r\n/\n/g' {} \;
# remove linhas em branco
find ./data -type f -name '*.[Tt][Xx][Tt]' -exec perl -i -pe 's/^[\t\s\r]*$//g' {} \;

############################################################
# Path e nome do arquivo de LOG para desvia o SYSLOG do SO
############################################################
LOG_HOJE=`date +"%Y%m%d"`
SYSLOGFILE="${ORABATCH_DIR_LOG}/${LOG_PREFIX}_SYS_${LOG_HOJE}.log"
SEM_RETORNO=0
COM_RETORNO=1
SQLLDR=${ORACLE_HOME}/bin/sqlldr

#Arquivos temporarios
ARQUIVO_CARGA=data/DOCOFERTAS_

#Nome do arquivo de controle
ARQUIVO_EM_EXECUCAO=${ORABATCH_DIR}/batche_em_execucao.lock

############################################################
# Desviando o SYSLOG para o caso de erro no BATCH 
############################################################
exec 1> $SYSLOGFILE
exec 2>> $SYSLOGFILE

# Arquivo de log Sysout
log ${LOG_PREFIX} "==================="
log ${LOG_PREFIX} "INICIANDO"
log ${LOG_PREFIX} "==================="
log ${LOG_PREFIX} "LOG_PREFIX.........: $LOG_PREFIX"
log ${LOG_PREFIX} "DIR_SHELL..........: $DIR_SHELL"
log ${LOG_PREFIX} "BASE_NAME..........: $BASE_NAME"
log ${LOG_PREFIX} "CONFIG_FILE........: $CONFIG_FILE"
log ${LOG_PREFIX} "CONFIG_FILE_DEF....: $CONFIG_FILE_DEF"
log ${LOG_PREFIX} "ORABATCH_DIR.......: $ORABATCH_DIR"
log ${LOG_PREFIX} "ORABATCH_DIR_LOG...: $ORABATCH_DIR_LOG"
log ${LOG_PREFIX} "ORABATCH_CONNECTION: $ORABATCH_CONNECTION"
log ${LOG_PREFIX} "==================="

############################################################
# Se receber algum sinal cancelamento aborta o processo e remove o arquivo de lock
############################################################
trap sinal_cancel INT SIGINT

function sinal_cancel() {
	log ${LOG_PREFIX} "ERRO: Processo recebeu sinal de cancelamento e foi cancelado..."
	rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
	exit -1;
}

############################################################
# carrega as funcoes para nao poluir o arquivo principal
############################################################
retorno="" #variavel global
arquivo="" #variavel global
source ${ORABATCH_DIR}/documentoOfertaSGP_Apoio.sh
source ${ORABATCH_DIR}/documentoOfertaSGP_Carga_Arquivos.sh

############################################################
# Verifica em que ponto esta entre as datas inicial e final
############################################################
verficaSeEstaEmExecucao ${ARQUIVO_EM_EXECUCAO}
return_code=$?
if [ $return_code != 0 ]
then
	log ${LOG_PREFIX} "======================"
	log ${LOG_PREFIX} "ERRO: ${LOG_PREFIX} esta em execucao.\nVerifique se o processo esta no ar, se nao tiver apague o arquivo [$ARQUIVO_EM_EXECUCAO] e execute novamente"
	log ${LOG_PREFIX} "======================"
	exit -1;
else
	verficaExistenciaArquivo ${ARQUIVO_CARGA}
	return_code=$?

	if [ $return_code != 0 ]
	then
		executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/${LOG_PREFIX}_TRUNCATABELA ${COM_RETORNO}
		carregaArquivosDocumentoPresentinho ${ARQUIVO_CARGA} ${LOG_PREFIX}_CARGA.par
	else
		log ${LOG_PREFIX} "Arquivo para execucao nao encontrado"
	fi
fi

rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
log ${LOG_PREFIX} "================================"
log ${LOG_PREFIX} "FINALIZADO COM SUCESSO"
log ${LOG_PREFIX} "================================"

