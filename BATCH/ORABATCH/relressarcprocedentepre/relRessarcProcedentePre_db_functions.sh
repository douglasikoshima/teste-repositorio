#!/bin/bash
############################################################
#  BATCH......: relRessarcImprocedente
#  Criado por.: Eder Jani Martins (04/05/2015)
#  Funcional..: Erika Brum
############################################################

############################################################
# Executa a query
# 	$1 Dados para a conexao (user/pass@DB)
# 	$2 Diretorio onde estao os arquivo SQL
# 	$3 Nome do arquivo sem a estensao ".sql"
#
# 	$4 Valor numerico, se diferente de zero recupera o texto de retorno do sqlplus
#      desta, nao pega o retorno do sqlplus, que em alguns casos pode ser gigante
#
# 	$5 Parametro livre, para uso geral
# 	$6 Parametro livre, para uso geral
# 	$7 Parametro livre, para uso geral
############################################################
function executaQuery() {
	log ${LOG_PREFIX} "->> executaQuery"
	V_ORABATCH_CONNECTION=$1
	V_ORABATCH_DIR=$2
	V_SQL_FILE_NAME=$3
	V_LOG_RETURN=$4
	V_PARAM1=$5
	V_PARAM2=$6
	V_PARAM3=$7
	log ${LOG_PREFIX} "\tORABATCH_CONNECTION [$V_ORABATCH_CONNECTION]"
	log ${LOG_PREFIX} "\tV_ORABATCH_DIR [$V_ORABATCH_DIR]"
	log ${LOG_PREFIX} "\tV_SQL_FILE_NAME [$V_SQL_FILE_NAME]"
	log ${LOG_PREFIX} "\tV_LOG_RETURN [$V_LOG_RETURN]"
	log ${LOG_PREFIX} "\tPARAM1 [$V_PARAM1]"
	log ${LOG_PREFIX} "\tPARAM2 [$V_PARAM2]"
	log ${LOG_PREFIX} "\tPARAM3 [$V_PARAM3]"
	log ${LOG_PREFIX} "\tIniciando a execucao de ${V_SQL_FILE_NAME}.sql"
	
	arquivo_sem_extensao=`basename $V_PARAM1 .ped`
	
	if [ $V_LOG_RETURN != 0 ]
	then
		retorno=`sqlplus -s ${V_ORABATCH_CONNECTION} @${V_ORABATCH_DIR}/$V_SQL_FILE_NAME.sql ${ORABATCH_DIR_LOG}/${arquivo_sem_extensao}-NAO-ENCONTADO.log`		
		sql_return_code=$?
		log ${LOG_PREFIX} "\tRetorno de $V_SQL_FILE_NAME.sql: [$retorno]"
	else
		sqlplus -s ${V_ORABATCH_CONNECTION} @${V_ORABATCH_DIR}/$V_SQL_FILE_NAME.sql ${ORABATCH_DIR_LOG}/${arquivo_sem_extensao}-NAO-ENCONTADO.log
		sql_return_code=$?
	fi
	log ${LOG_PREFIX} "\tExecucao de ${V_SQL_FILE_NAME}.sql finalizada: [`date`]"
	if [ $sql_return_code != 0 ]
	then
		log ${LOG_PREFIX} "ERRO: Falha ao executar o arquivo ${V_SQL_FILE_NAME}.sql[${sql_return_code}]"
		log ${LOG_PREFIX} "<<- executaQuery"
		processoEmExecucao "PARAR"
		exit -1;
	fi
	log ${LOG_PREFIX} "<<- executaQuery"
}

#Variavel para o SQL LOADER
SQLLDR=${ORACLE_HOME}/bin/sqlldr
############################################################
# Carrega um aruqivo atraves do SQL LOADER
#   Parametro 1: Arquivo PAR (que contem o arquivo de controle para o SQL LOADER)
#   Parametro 2: Nome do arquivo a ser carregado
############################################################
function cargaArquivos() {
	log ${LOG_PREFIX} "->> cargaArquivos"
	# Arquivo de controle do SQL LOADER
	arquivoPar=${1}
	#Arquivo a ser carregado
	arquivoCarga=${2}
	#Removendo a extensao para gerar arquivos de LOG, BAD e descarte com o mesmo nome do arquivo de importacao
	arquivo_sem_extensao=`basename $arquivoCarga .ped`
	
	#Dispara o SQL LOADER
	log ${LOG_PREFIX} "\tSQL*Loader - Iniciando..."
	${SQLLDR} userid=${ORABATCH_CONNECTION} parfile=${arquivoPar} data=${arquivoCarga} log=${ORABATCH_DIR_LOG}/${arquivo_sem_extensao}.log bad=${ORABATCH_DIR_BAD}/${arquivo_sem_extensao}.bad discard=${ORABATCH_DIR_BAD}/${arquivo_sem_extensao}.dsc	
	RETCODE=$?
	
		log ${LOG_PREFIX} "\tMovendo o arquivo: ${arquivoCarga}"
		log ${LOG_PREFIX} "\tPara: ${ORABATCH_DIR_PRC}"
		mv -f ${arquivoCarga} ${ORABATCH_DIR_PRC}
		log ${LOG_PREFIX} "\tArquivo Movido com sucesso"
	
	case "${RETCODE}" in 
	0) 
		log ${LOG_PREFIX} "\tSQL*Loader - Arquivo carregado com retorno EX_OK"		
		;; 
	1) 
		log ${LOG_PREFIX} "\tSQL*Loader - execucao concluida com EX_FAIL, verifique logfile"
		;; 
	2) 
		log ${LOG_PREFIX} "\tSQL*Loader - execucao concluida com EX_WARN"
		;; 
	3) 
		log ${LOG_PREFIX} "\tSQL*Loader - ERRO fatal na execucao"
		;; 
	*) 
		log ${LOG_PREFIX} "\tSQL*Loader - ERRO desconhecido"
		;; 
	esac

	if [ "${RETCODE}" != "0" -a "${RETCODE}" != "2" ]
	then
		log ${LOG_PREFIX} "<<- cargaArquivos"
		processoEmExecucao "PARAR"
		exit ${RETCODE}
	fi
	log ${LOG_PREFIX} "<<- cargaArquivos"
}