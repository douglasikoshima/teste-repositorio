#!/bin/bash
############################################################
#  BATCH......: expurgoSincronismoAMDOCS
#  Criado por.: Lucas Gomes Fenelon de Moraes (15/04/2016)
#  Funcional..: Erika Brum
############################################################

############################################################
# CARREGAMENTO DE ARQUIVOS COM CONFIGURACOES DE BANCO
############################################################
export LOG_PREFIX="EXP_SINC_AMDOCS_ARQ"
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
# Path e nome do arquivo de LOG para desvia o SYSLOG do SO
############################################################
LOG_HOJE=`date +"%Y%m%d"`
SYSLOGFILE="${ORABATCH_DIR_LOG}/${LOG_PREFIX}_SYS_${LOG_HOJE}.log"

#Nome do arquivo de controle
ARQUIVO_DATA_PROCESSADA=datatmp/arquivo_data_processada.txt
ARQUIVO_ESTADO_PROCESSAMENTO=datatmp/arquivo_estado_processamento.txt
ARQUIVO_EM_EXECUCAO=${ORABATCH_DIR}/batche_em_execucao.lock
#ARQUIVO_SQL="EXPURGO_GENERICO"

#Nome do arquivo de armazenamento
NOME_ARQUIVO_ATENDIMENTO=expurgo
ARQUIVO_BACKUP='hist/$NOME_ARQUIVO_ATENDIMENTO'

SEM_RETORNO=0
COM_RETORNO=1

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
# Se receber algum sinal cancelamento aborta o processo e atualiza o estado
############################################################
trap sinal_cancel INT SIGINT SIGHUP SIGQUIT SIGTERM

function sinal_cancel() {
	log ${LOG_PREFIX} "ERRO: Processo recebeu sinal de cancelamento e esta cancelando..."
	salvaDadoNoArquivo ERRO
	log ${LOG_PREFIX} "ERRO: ...processo esta cancelado."
	exit -1;
}

############################################################
# carrega as funcoes para nao poluir o arquivo principal
############################################################
retorno="" #variavel global
source ${ORABATCH_DIR}/expurgoSincronismoAMDOCS_Apoio.sh
source ${ORABATCH_DIR}/expurgoSincronismoAMDOCS_Dao.sh
source ${ORABATCH_DIR}/expurgoSincronismoAMDOCS_Negocio.sh

############################################################
# Verifica em que ponto esta entre as datas inicial e final
############################################################
verficaExecucao ${ARQUIVO_EM_EXECUCAO}
return_code=$?
if [ $return_code != 0 ]
then
	log ${LOG_PREFIX} "======================"
	log ${LOG_PREFIX} "ERRO: ${LOG_PREFIX} esta em execucao.\n\tVerifique se o processo esta no ar, se nao tiver apague o arquivo [$ARQUIVO_EM_EXECUCAO] e execute novamente"
	log ${LOG_PREFIX} "======================"
	exit -1;
else
	salvaDadoNoArquivo ${ARQUIVO_ESTADO_PROCESSAMENTO} PROCESSAMENTO
	geraDataProcessada ${ARQUIVO_DATA_PROCESSADA}
	salvaDadoNoArquivo ${ARQUIVO_DATA_PROCESSADA} $retorno
	retorno_data=$retorno
fi

buscaDadoNoArquivo ${ARQUIVO_ESTADO_PROCESSAMENTO}
if [ $retorno == "PROCESSAMENTO" ];
then
	processaExpurgoArquivos "expurgo"	
	salvaDadoNoArquivo ${ARQUIVO_ESTADO_PROCESSAMENTO} PROCESSADO
else
	log ${LOG_PREFIX} "O processo esta em um estado que nao exige atuacao."
fi

buscaDadoNoArquivo ${ARQUIVO_ESTADO_PROCESSAMENTO}
if [ $retorno == "PROCESSADO" ];
then
	salvaDadoNoArquivo ${ARQUIVO_ESTADO_PROCESSAMENTO} FINALIZADO
	log ${LOG_PREFIX} "================================"
	log ${LOG_PREFIX} "FINALIZADO COM SUCESSO"
	log ${LOG_PREFIX} "================================"
else
	salvaDadoNoArquivo ${ARQUIVO_ESTADO_PROCESSAMENTO} ERRO
	log ${LOG_PREFIX} "================================"
	log ${LOG_PREFIX} "HOUVE ALGUM PROBLEMA NA EXECUCAO DO PROCESSO"
	log ${LOG_PREFIX} "================================"
fi

rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
log ${LOG_PREFIX} "================================"
log ${LOG_PREFIX} "TERMINOU SEM EXIT"
log ${LOG_PREFIX} "================================"
