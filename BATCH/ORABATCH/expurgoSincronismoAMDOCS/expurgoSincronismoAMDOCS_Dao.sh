#!/bin/bash
############################################################
#  BATCH......: expurgoSincronismoAMDOCS
#  Criado por.: Lucas Gomes Fenelon de Moraes (12/04/2016)
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
	log ${LOG_PREFIX} "	ORABATCH_CONNECTION [$V_ORABATCH_CONNECTION]"
	log ${LOG_PREFIX} "	V_ORABATCH_DIR [$V_ORABATCH_DIR]"
	log ${LOG_PREFIX} "	V_SQL_FILE_NAME [$V_SQL_FILE_NAME]"
	log ${LOG_PREFIX} "	V_LOG_RETURN [$V_LOG_RETURN]"
	log ${LOG_PREFIX} "	PARAM1 [$V_PARAM1]"
	log ${LOG_PREFIX} "	PARAM2 [$V_PARAM2]"
	log ${LOG_PREFIX} "	PARAM3 [$V_PARAM3]"
	log ${LOG_PREFIX} "	Iniciando a execucao de $V_SQL_FILE_NAME.sql: [`date`]"
	if [ $V_LOG_RETURN != 0 ]
	then
		retorno=`sqlplus -s ${V_ORABATCH_CONNECTION} @${V_ORABATCH_DIR}/$V_SQL_FILE_NAME.sql $V_PARAM1 $V_PARAM2 $V_PARAM3`
		sql_return_code=$?
		log ${LOG_PREFIX} "	Retorno de $V_SQL_FILE_NAME.sql: [$retorno]"
	else
		sqlplus -s ${V_ORABATCH_CONNECTION} @${V_ORABATCH_DIR}/$V_SQL_FILE_NAME.sql $V_PARAM1 $V_PARAM2 $V_PARAM3
		sql_return_code=$?
	fi
	log ${LOG_PREFIX} "	Execucao de $V_SQL_FILE_NAME.sql finalizada: [`date`]"
	if [ $sql_return_code != 0 ]
	then
		log ${LOG_PREFIX} "ERRO: Falha ao executar o arquivo $V_SQL_FILE_NAME.sql[$sql_return_code]"
		salvaDadoNoArquivo ${ARQUIVO_ESTADO_PROCESSAMENTO} ERRO
		rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
		exit -1;
	fi
	log ${LOG_PREFIX} "<<- executaQuery"
}

#################################################################
# Cria um parametro temporario com a data de atual processamento
#################################################################
function validaDiferencaDatas() {
	log ${LOG_PREFIX} "->> validaDiferencaDatas"

	V_PARAM1=$1

	# Cria a data processada como o dia atual 
	retorno=`sqlplus -s ${ORABATCH_CONNECTION} <<EOF
				SET PAGESIZE 0
				SET LINESIZE 32766
				SET FEEDBACK OFF
				SET VERIFY OFF
				SET HEADING OFF
				SET ECHO OFF
				SET COLSEP "|"
				SET TAB OFF
				SET PAGESIZE 0
				SET TRIMS ON
				SET WRAP OFF
				SET HEADING OFF
				SET SERVEROUTPUT ON
				WHENEVER SQLERROR EXIT SQL.SQLCODE

				SELECT CASE WHEN TO_NUMBER(SYSDATE - TO_DATE('$V_PARAM1', 'DDMMYYYY')) >
                              TO_NUMBER((SELECT DSVALORPARAMETRO 
                                           FROM APOIO.PARAMETRO 
                                          WHERE CDPARAMETRO = 'QTD_MES_EXPURGO_ARQUIVOS_AMDOCS')) * 30
					   THEN 1 
					   ELSE 0 
					   END 
				  FROM DUAL;
				
				EXIT;
				EOF`

	# Verifica se houve erro no sqlplus 
	return_code=$?
	if [ $return_code != 0 ]
	then
		log ${LOG_PREFIX} "\tERRO: Nao foi possivel incremetar a data atraves do  banco de dados"
		log ${LOG_PREFIX} "<<- validaDiferencaDatas"
		rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
		exit -1;
	fi

	log ${LOG_PREFIX} "\tRetorno processado[$retorno]"
	log ${LOG_PREFIX} "<<- validaDiferencaDatas"
}

#################################################################
# Cria um parametro temporario com a data de atual processamento
#################################################################
function geraDataProcessada() {
	log ${LOG_PREFIX} "->> geraDataProcessada"

	arquivo=$1

	# Cria a data processada como o dia atual 
	retorno=`sqlplus -s ${ORABATCH_CONNECTION} <<EOF
				SET PAGESIZE 0
				SET LINESIZE 32766
				SET FEEDBACK OFF
				SET VERIFY OFF
				SET HEADING OFF
				SET ECHO OFF
				SET COLSEP "|"
				SET TAB OFF
				SET PAGESIZE 0
				SET TRIMS ON
				SET WRAP OFF
				SET HEADING OFF
				SET SERVEROUTPUT ON
				WHENEVER SQLERROR EXIT SQL.SQLCODE
				
				SELECT TRIM(TO_CHAR(SYSDATE, 'DDMMYYYY')) AS DATA FROM DUAL;
				
				EXIT;
				EOF`

	# Verifica se houve erro no sqlplus 
	return_code=$?
	if [ $return_code != 0 ]
	then
		log ${LOG_PREFIX} "\tERRO: Nao foi possivel incremetar a data atraves do  banco de dados"
		log ${LOG_PREFIX} "<<- geraDataProcessada"
		rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
		exit -1;
	fi

	log ${LOG_PREFIX} "\tData processada[$retorno]"
	log ${LOG_PREFIX} "<<- geraDataProcessada"
}