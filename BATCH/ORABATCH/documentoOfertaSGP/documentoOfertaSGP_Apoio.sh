#!/bin/bash
############################################################
#  BATCH......: documentoOfertaSGP
#  Criado por.: Lucas Gomes (29/09/2015)
#  Funcional..: Dener Neves dos santos
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
		rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
		exit -1;
	fi
	log ${LOG_PREFIX} "<<- executaQuery"
}

function verficaSeEstaEmExecucao {
	log ${LOG_PREFIX} "->> verficaSeEstaEmExecucao"
	#Arquivo recebido como parametro
	arquivo=$1
	# Se não achar o arquivo retorna zero
	retorno=0
	log ${LOG_PREFIX} "Arquivo: [$arquivo]"
	if [ -f "$arquivo" ]
	then
		log ${LOG_PREFIX} "${LOG_PREFIX} EM execucao. Arquivo [$arquivo] encontrado. "
		retorno=1
	else
		echo 1 > $arquivo
		log ${LOG_PREFIX} "${LOG_PREFIX} NAO esta em execucao. \n\tArquivo de controle de execucao criado [${arquivo}]."
	fi
	log ${LOG_PREFIX} "<<- verficaSeEstaEmExecucao"
	return $retorno
}

function verficaExistenciaArquivo {
	log ${LOG_PREFIX} "->> verficaExistenciaArquivo"
	#Arquivo recebido como parametro
	parametro=$1
	# Se não achar o arquivo retorna zero
	retorno=0
	log ${LOG_PREFIX} "Arquivo: [$arquivo]"

	for arquivo in ${parametro}*.txt
	do
		if [ -f "$arquivo" ]
		then
			log ${LOG_PREFIX} "Arquivo [$arquivo] encontrado. "
			retorno=1
		else
			log ${LOG_PREFIX} "Arquivo [${arquivo}] nao encontrado. "
			retorno=0
		fi
	done

	log ${LOG_PREFIX} "<<- verficaExistenciaArquivo"
	return $retorno
}