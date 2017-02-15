#!/bin/bash
############################################################
#  BATCH......: cargaServicoBandaLarga
#  Criado por.: Jair  14/01/2016
#  Funcional..: Mirian Yuka
############################################################

############################################################
# CARREGAMENTO DE ARQUIVOS COM CONFIGURACOES DE BANCO############################################################
export LOG_PREFIX="cargaServicoBandaLarga"
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
SYSLOGFILE="${ORABATCH_DIR_LOG}/${LOG_PREFIX}_SYS_${LOG_HOJE}.log"
# O arquivo que vem do DW tem esta estrutura: DW_DDMMAAAAHHMISS.txt
ARQUIVO_BASE=servicoBandaLarga_${LOG_HOJE}
# Variaveis para controle de retorno do SQLPLUS
SEM_RETORNO=0
COM_RETORNO=1
# Arquivo para controle de execucao
ARQUIVO_EM_EXECUCAO=cargaServicoBandaLarga.lock


############################################################
# Desviando o SYSLOG para o caso de erro no BATCH
############################################################
exec 1> $SYSLOGFILE
exec 2>> $SYSLOGFILE


# Arquivo de log Sysout
log ${LOG_PREFIX} "====================="
log ${LOG_PREFIX} "INICIANDO"
log ${LOG_PREFIX} "====================="
log ${LOG_PREFIX} "LOG_PREFIX...........: $LOG_PREFIX"
log ${LOG_PREFIX} "DIR_SHELL............: $DIR_SHELL"
log ${LOG_PREFIX} "BASE_NAME............: $BASE_NAME"
log ${LOG_PREFIX} "CONFIG_FILE..........: $CONFIG_FILE"
log ${LOG_PREFIX} "CONFIG_FILE_DEF......: $CONFIG_FILE_DEF"
log ${LOG_PREFIX} "ORABATCH_DIR.........: $ORABATCH_DIR"
log ${LOG_PREFIX} "ORABATCH_DIR_LOG.....: $ORABATCH_DIR_LOG"
log ${LOG_PREFIX} "ORABATCH_CONNECTION..: $ORABATCH_CONNECTION"
log ${LOG_PREFIX} "ORABATCH_CONNECTION_2: $ORABATCH_CONNECTION_2"
log ${LOG_PREFIX} "====================="


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

function sql_trunc() {		
	log ${LOG_PREFIX} "Executando SQL*Plus.."

	sqlplus "${ORABATCH_CONNECTION}" <<EOB
SET TERMOUT ON;
SET SQLPROMPT '';
SET FEEDBACK OFF;
SET ECHO OFF;
SET AUTOTRACE OFF;
SET FLUSH ON;
SET HEADING OFF;
SET SERVEROUTPUT ON SIZE 1000000;
SET TIME OFF;
SET TRIMOUT ON;
SET TRIMSPOOL ON;
SET PAUSE OFF;
SET SHOWMODE OFF;
SET SQLBLANKLINES OFF;
SET SQLNUMBER OFF;
SET TAB OFF;
SET VERIFY OFF;
SET WRAP OFF;
SET FEED OFF;
SET NEWPAGE NONE;
SET LINESIZE 32767;
SET TIMING ON;
WHENEVER SQLERROR EXIT SQL.SQLCODE;
WHENEVER OSERROR EXIT FAILURE; 
EXEC LOAD.TRUNC_CARGABUSERVICOBANDALARGA();
EXIT 0;
EOB
	
	RET_CODE=$?
	log ${LOG_PREFIX} "Codigo retorno SQL*Plus: ${RET_CODE}"
	
	# Aborta processamento se houve erro sql
	if (( ${RET_CODE} ))
	then
		processoEmExecucao "PARAR"
		log ${LOG_PREFIX} "Erro no ao Limpar Tabela LOAD.CARGABUSERVICOBANDALARGA exit=${RET_CODE}"
		exit ${RETCODE}
	fi
	
	return ${RET_CODE}
}

############################################################
# carrega as funcoes para nao poluir o arquivo principal
############################################################
retorno="" #variavel global
retorno_secundario="" #variavel global
source ${ORABATCH_DIR}/cargaServicoBandaLarga_db_functions.sh
source ${ORABATCH_DIR}/cargaServicoBandaLarga_files_functions.sh



############################################################
# Antes de iniciar o processamento verifica se ja esta em execucao
# O valor de retorno vai para a variavel $retorno
############################################################
processoEmExecucao "ESTADO"
if [ ${retorno} = "PARADO" ]
then
	# Cria o arquivo de lock, que indica a execucao para evitar dupla execucao
	processoEmExecucao "INICIAR"
	
    log ${LOG_PREFIX} "INICIAR EXTRACAO ARQUIVO SERVICOBANDALARGA"
	executaQuery ${ORABATCH_CONNECTION_2} ${ORABATCH_DIR} sql/cargaServicoBandaLarga ${SEM_RETORNO} ${ARQUIVO_BASE}
    log ${LOG_PREFIX} "FIM EXTRACAO ARQUIVO SERVICOBANDALARGA"
	
	# Le os arquivos para carga, enviados pelo DW
	for arquivo in ${ORABATCH_DIR_DATA_IN}/${ARQUIVO_BASE}.[tT][xX][tT]
	do
		# Verifica se o arquivo existe, isso ocorre porque se o for..in quando não achar retorna um arquivo invalido
		if [ -f ${arquivo} ]
		then
			
			# Limpando tabela de load
			log ${LOG_PREFIX} "LIMPANDO TABELA LOAD.CARGABUSERVICOBANDALARGA de carga.."
			sql_trunc

			log ${LOG_PREFIX} "O SQL LOADER sera chamado com o arquivo ${arquivo}"

			# Faz a carga do arquivo e processa
			cargaArquivos ${ORABATCH_DIR_SQL}/cargaServicoBandaLarga.par ${arquivo}

			# Remove o Arquivo para a pasta hist
                        move_arquivos_para_hist

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
	log ${LOG_PREFIX} "	Verifique se o processo esta em execucao: ps -ef | grep cargaServicoBandaLarga"
	log ${LOG_PREFIX} "	Se não estiver em execucao apague o arquivo [${ARQUIVO_EM_EXECUCAO}] e dispare o BATCH novamente"
	log ${LOG_PREFIX} "================================"
fi
