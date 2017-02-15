#!/bin/bash
############################################################
#  BATCH......: relRessarcProcedentePre
#  Criado por.: Eder Jani Martins (08/05/2015)
#  Funcional..: Erika Brum
############################################################

############################################################
# CARREGAMENTO DE ARQUIVOS COM CONFIGURACOES DE BANCO
############################################################
export LOG_PREFIX="PROCEDENTEPOS"
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
# O arquivo que vem do DW tem esta estrutura: DW_DDMMAAAAHHMISS.txt
ARQUIVO_BASE=AutomaticCompensation
# Variaveis para controle de retorno do SQLPLUS
SEM_RETORNO=0
COM_RETORNO=1
# Arquivo para controle de execucao
ARQUIVO_EM_EXECUCAO=relRessarcProcedentePre.lock

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
source ${ORABATCH_DIR}/relRessarcProcedentePre_db_functions.sh
source ${ORABATCH_DIR}/relRessarcProcedentePre_files_functions.sh

############################################################
# Antes de iniciar o processamento verifica se ja esta em execucao
# O valor de retorno vai para a variavel $retorno
############################################################
processoEmExecucao "ESTADO"
if [ ${retorno} = "PARADO" ]
then
	# Cria o arquivo de lock, que indica a execucao para evitar dupla execucao
	processoEmExecucao "INICIAR"
	# Le os arquivos para carga, enviados pelo DW: AutomaticCompensationYYYYMMDDHHMMSS.ped
	for arquivo in ${ORABATCH_DIR_DATA_IN}/${ARQUIVO_BASE}*.[Pp][Ee][Dd]
	do
		# Verifica se o arquivo existe, isso ocorre porque se o for..in quando não achar retorna um arquivo invalido
		if [ -f ${arquivo} ]
		then
			log ${LOG_PREFIX} "O SQL LOADER sera chamado com o arquivo ${arquivo}"
			# Faz a carga do arquivo do DW e processa 
			# O PRIMEIRO CAMPO PRECISA TER A STRING 'PED', senao sera rejeitado para o arquivo BAD
			# Conforme descrito documento funcional "RS04.3 Script para processamento de Ressarcimentos Procedentes do Pré Pago (RF03)"
			# No caso alternativo "A4 – Arquivo sem registros de 'A PEDIDO'"
			cargaArquivos ${ORABATCH_DIR_SQL}/RELRESSARCPROCEDENTEPRE.par ${arquivo}

			############################################################
			# Executa uma query para processar os dados carregados pelo SQL LOADER
			# Tambem gera um arquivo com as linhas nao encontradas (enviadas pelo DW, 
            # mas nao existem em ATENDIMENTO.PEDIDORESSARCIMENTO)
			############################################################
			log ${LOG_PREFIX} "Iniciando o processamento dos registros em LOAD.RESSARCIMENTOBILLING"
			##arquivo_sem_extensao=`basename $arquivo .[pP][eE][dD]`	
			
			executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/RELRESSARCPROCEDENTEPRE ${SEM_RETORNO} ${arquivo}
			
			#${ORABATCH_DIR_LOG}/${arquivo_sem_extensao}-NAO-ENCONTADO.log

			############################################################
			# Fim do relatorio
			############################################################
			log ${LOG_PREFIX} "================================"
			log ${LOG_PREFIX} "Arquivo processado: ${arquivo}"
			log ${LOG_PREFIX} "================================"
		else
			log ${LOG_PREFIX} "================================"
			log ${LOG_PREFIX} "ARQUIVO NAO ENCONTRADO PARA PROCESSAR"
			log ${LOG_PREFIX} "================================"
		fi
	done
	# Apaga o arquivo de lock, que indica a execucao
	processoEmExecucao "PARAR"
	log ${LOG_PREFIX} "================================"
	log ${LOG_PREFIX} "TERMINOU COM SUCESSO"
	log ${LOG_PREFIX} "================================"
else
	log ${LOG_PREFIX} "================================"
	log ${LOG_PREFIX} "PROCESSO EM EXECUCAO"
	log ${LOG_PREFIX} "================================"
	log ${LOG_PREFIX} "\tVerifique se o processo esta em execucao: ps -ef | grep relRessarcProcedentePre"
	log ${LOG_PREFIX} "\tSe não estiver em execucao apague o arquivo [${ARQUIVO_EM_EXECUCAO}] e dispare o BATCH novamente"
	log ${LOG_PREFIX} "================================"
fi
