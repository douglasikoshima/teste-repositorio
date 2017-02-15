#!/bin/bash
############################################################
#  BATCH......: EXPURGOINTENCAOCOMPRA
#  Criado por.: Williams Santos
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
	log ${LOG_PREFIX} "\tIniciando a execucao de ${V_SQL_FILE_NAME}.sql: [`date`]"
	if [ $V_LOG_RETURN != 0 ]
	then
		############################################################
		# Forçando Idioma do BD para utulizar acentuação
		############################################################
		export NLS_LANG="BRAZILIAN PORTUGUESE_BRAZIL.WE8ISO8859P1"
		retorno=`sqlplus -s ${V_ORABATCH_CONNECTION} @${V_ORABATCH_DIR}/$V_SQL_FILE_NAME.sql $V_PARAM1 $V_PARAM2 $V_PARAM3`
		sql_return_code=$?
		log ${LOG_PREFIX} "\tRetorno de $V_SQL_FILE_NAME.sql: [$retorno]"
	else
		sqlplus -s ${V_ORABATCH_CONNECTION} @${V_ORABATCH_DIR}/$V_SQL_FILE_NAME.sql $V_PARAM1 $V_PARAM2 $V_PARAM3
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
