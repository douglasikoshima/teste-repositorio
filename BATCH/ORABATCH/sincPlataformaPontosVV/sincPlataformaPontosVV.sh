#!/bin/bash
############################################################
#  BATCH......: sincPlataformaPontosVV
#  Criado por.: Williams Santos 25/04/2016
#  Funcional..: Dener Santos
############################################################

############################################################
# CARREGAMENTO DE ARQUIVOS COM CONFIGURACOES DE BANCO
############################################################
export LOG_PREFIX="sincPlataformaPontosVV"
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


LOG_HOJE=`date +"%d%m%Y"`
#DATA_COMPLETA=`date +"%d%m%Y%H%M%S"`
DATA_COMPLETA=`date +"%Y%m%d%H%M%S"`
SYSLOGFILE="${ORABATCH_DIR_LOG}/${LOG_PREFIX}_SYS_${LOG_HOJE}.log"
# Variaveis para controle de retorno do SQLPLUS
SEM_RETORNO=0
COM_RETORNO=1
# Arquivo para controle de execucao
ARQUIVO_EM_EXECUCAO=sincPlataformaPontosVV.lock
NOME_ARQUIVO=ParqueMovelVV_${DATA_COMPLETA}

# Apaga arquivos mais velhos que 3 meses
PERIODO_LIMPEZA=30

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
	log ${LOG_PREFIX} "====================================="
	log ${LOG_PREFIX} "ERRO: Processo recebeu sinal de cancelamento e esta cancelando..."
	processoEmExecucao "PARAR"
	log ${LOG_PREFIX} "====================================="
	log ${LOG_PREFIX} "PROCESSO FOI INTERROMPIDO COM EXIT -1"
	log ${LOG_PREFIX} "====================================="
	exit -1;
}


############################################################
# carrega as funcoes para nao poluir o arquivo principal
############################################################
retorno="" #variavel global
retorno_secundario="" #variavel global
source ${ORABATCH_DIR}/sincPlataformaPontosVV_db_functions.sh
source ${ORABATCH_DIR}/sincPlataformaPontosVV_files_functions.sh



############################################################
# Antes de iniciar o processamento verifica se ja esta em execucao
# O valor de retorno vai para a variavel $retorno
############################################################
processoEmExecucao "ESTADO"
if [ ${retorno} = "PARADO" ]
then
	# Cria o arquivo de lock, que indica a execucao para evitar dupla execucao
	processoEmExecucao "INICIAR"

	executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/sincPlataformaPontosVV ${SEM_RETORNO} ${NOME_ARQUIVO}
	
	# Copiando arquivo gerado para a pasta hist
	cp ${ORABATCH_DIR_DATA_IN}/${NOME_ARQUIVO}.txt ${ORABATCH_DIR_HIST}/
	
	log ${LOG_PREFIX} "== Apagando arquivos historiocos"	
	apagaAquivos ${ORABATCH_DIR_HIST} ${PERIODO_LIMPEZA}
	
	# Apaga o arquivo de lock, que indica a execucao
	processoEmExecucao "PARAR"
	log ${LOG_PREFIX} "================================"
	log ${LOG_PREFIX} "TERMINOU COM SUCESSO"
	log ${LOG_PREFIX} "================================"
else
	log ${LOG_PREFIX} "================================"
	log ${LOG_PREFIX} "PROCESSO EM EXECUCAO"
	log ${LOG_PREFIX} "================================"
	log ${LOG_PREFIX} "	Verifique se o processo esta em execucao: ps -ef | grep sincPlataformaPontosVV"
	log ${LOG_PREFIX} "	Se não estiver em execucao apague o arquivo [${ARQUIVO_EM_EXECUCAO}] e dispare o BATCH novamente"
	log ${LOG_PREFIX} "================================"
fi
