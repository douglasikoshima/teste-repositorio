#!/bin/bash
############################################################
#  BATCH......: meuVivoDW
#  Criado por.: Eder Jani Martins (11/02/2015)
#  Alterado por: Lucas Gomes(03/09/2015)
#  Funcional..: Marcia Santana Silva
############################################################

############################################################
# Atualiza o estado
############################################################
function atualizaEstado () {
	log ${LOG_PREFIX} "->> atualizaEstado"
	estado=$1
	log ${LOG_PREFIX} "	Estado recebido como parametro [$estado]"
	case "$estado" in
		PROCESSANDO|FINALIZADO|ERRO)
			# Atualiza o estado 
			dummy=`sqlplus -s ${ORABATCH_CONNECTION} <<EOF
	            SET HEADING OFF
				WHENEVER SQLERROR EXIT SQL.SQLCODE
				UPDATE APOIO.PARAMETRO SET 
				     DSVALORPARAMETRO = '$estado',
				     IDUSUARIOALTERACAO = 1,
				     DTULTIMAALTERACAO = SYSDATE
				WHERE
				    CDPARAMETRO = 'MEUVIVODW_ESTADO';

				COMMIT;

	            EXIT;
	            EOF`
			# Verifica se houve erro no sqlplus 
			sql_return_code=$?
			if [ $sql_return_code != 0 ]
			then
				log ${LOG_PREFIX} "ERRO[atualizaEstado]: Nao foi possivel carregar o parametro 'MEUVIVODW_ESTADO' em APOIO.PARAMETRO. Codigo de erro [$dummy]"
				exit -1;
			fi
			log ${LOG_PREFIX} "	Estado alterado para [$estado]"
						;;
		*) 
			log ${LOG_PREFIX} "ERRO[atualizaEstado]: Estado recebido como parametro esta invalido: [$estado]."
			log ${LOG_PREFIX} "                      Deve ser: PROCESSANDO, FINALIZADO ou ERRO."
			exit -1 # Aborta, o parametro passado nao existe ou esta com um valor errado
			;;
	esac
	log ${LOG_PREFIX} "<<- atualizaEstado"
}

############################################################
# Atualiza a etapa
############################################################
function atualizaEtapa () {
	log ${LOG_PREFIX} "->> atualizaEtapa"
	etapa=$1
	log ${LOG_PREFIX} "	Etapa recebida como parametro [$etapa]"
	case "$etapa" in
		CHECAGEM | ATENDIMENTOS_PALITOS | ATENDIMENTOS_PROTOCOLOS | ATENDIMENTOS | CLIENTES | GERA_ARQUIVO_CLINTE | PALITOS_MARCAS | PALITOS_CONTAS | PALITOS_PESSOAS | PALITOS_CONSOLIDADO | PALITOS_NRTELEFONE | PALITOS | GERA_ARQUIVO_PROMOS)
			# Atualiza com a estapa em processo 
			dummy=`sqlplus -s ${ORABATCH_CONNECTION} <<EOF
				SET HEADING OFF
				WHENEVER SQLERROR EXIT SQL.SQLCODE
				UPDATE APOIO.PARAMETRO SET 
					 DSVALORPARAMETRO = '$etapa',
					 IDUSUARIOALTERACAO = 1,
					 DTULTIMAALTERACAO = SYSDATE
				WHERE
					CDPARAMETRO = 'MEUVIVODW_ETAPA';
				COMMIT;
				EXIT;
				EOF`

			# Verifica se houve erro no sqlplus 
			sql_return_code=$?
			if [ $sql_return_code != 0 ]
			then
				log ${LOG_PREFIX} "ERRO[atualizaEtapa]: Nao foi possivel carregar o parametro 'MEUVIVODW_ETAPA' em APOIO.PARAMETRO. Codigo de erro [$dummy]"
				exit 0;
			fi
			log ${LOG_PREFIX} "	Etapa alterada para [$etapa]"
			;;
		*) 
			log ${LOG_PREFIX} "ERRO[atualizaEtapa]: Etapa recebida como parametro esta invalida: [$etapa]."
			log ${LOG_PREFIX} "      Deve ser: CHECAGEM, INCLUSAO_POS, EXCLUSAO_POS, INCLUSAO_PRE e EXCLUSAO_PRE."
			exit -1 # Aborta, o parametro passado nao existe ou esta com um valor errado
			;;
	esac
	log ${LOG_PREFIX} "<<- atualizaEtapa"
}


############################################################
# Atualiza o criterio
############################################################
function atualizaCriterio () {
	log ${LOG_PREFIX} "->> atualizaCriterio"
	# Atualiza o CRITERIO com um dia a mais 
	dummy=`sqlplus -s ${ORABATCH_CONNECTION} <<EOF
	    SET HEADING OFF
		WHENEVER SQLERROR EXIT SQL.SQLCODE
		UPDATE APOIO.PARAMETRO SET 
			 DSVALORPARAMETRO = TO_CHAR(TO_DATE(DSVALORPARAMETRO, 'DD/MM/YYYY')+1, 'DD/MM/YYYY'),
			 IDUSUARIOALTERACAO = 1,
			 DTULTIMAALTERACAO = SYSDATE
		WHERE
			CDPARAMETRO = 'MEUVIVODW_CRITERIO';
		COMMIT;
	    EXIT;
	    EOF`

	# Verifica se houve erro no sqlplus 
	sql_return_code=$?
	if [ $sql_return_code != 0 ]
	then
		log ${LOG_PREFIX} "ERRO[atualizaCriterio]: Nao foi possivel atualizar o parametro 'MEUVIVODW_CRITERIO' em APOIO.PARAMETRO. Codigo de erro [$dummy]"
		exit -1;
	fi
	log ${LOG_PREFIX} "	Creterio incrementado em 1 dia."
	log ${LOG_PREFIX} "<<- atualizaCriterio"
}

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
		atualizaEstado ERRO
		rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
		exit -1;
	fi
	log ${LOG_PREFIX} "<<- executaQuery"
}

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
function executaQuerySemGravarEstado() {
	log ${LOG_PREFIX} "->> executaQuerySemGravarEstado"
	V_ORABATCH_CONNECTION=$1
	V_ORABATCH_DIR=$2
	V_SQL_FILE_NAME=$3
	V_LOG_RETURN=$4
	V_PARAM1=$5
	V_PARAM2=$6
	V_PARAM3=$7
	V_PARAM4=$8
	log ${LOG_PREFIX} "	ORABATCH_CONNECTION [$V_ORABATCH_CONNECTION]"
	log ${LOG_PREFIX} "	V_ORABATCH_DIR [$V_ORABATCH_DIR]"
	log ${LOG_PREFIX} "	V_SQL_FILE_NAME [$V_SQL_FILE_NAME]"
	log ${LOG_PREFIX} "	V_LOG_RETURN [$V_LOG_RETURN]"
	log ${LOG_PREFIX} "	PARAM1 [$V_PARAM1]"
	log ${LOG_PREFIX} "	PARAM2 [$V_PARAM2]"
	log ${LOG_PREFIX} "	PARAM3 [$V_PARAM3]"
	log ${LOG_PREFIX} "	PARAM4 [$V_PARAM4]"
	log ${LOG_PREFIX} "	Iniciando a execucao de $V_SQL_FILE_NAME.sql: [`date`]"
	if [ $V_LOG_RETURN != 0 ]
	then
		retorno=`sqlplus -s ${V_ORABATCH_CONNECTION} @${V_ORABATCH_DIR}/$V_SQL_FILE_NAME.sql $V_PARAM1 $V_PARAM2 $V_PARAM3 $V_PARAM4`
		sql_return_code=$?
		log ${LOG_PREFIX} "	Retorno de $V_SQL_FILE_NAME.sql: [$retorno]"
	else
		sqlplus -s ${V_ORABATCH_CONNECTION} @${V_ORABATCH_DIR}/$V_SQL_FILE_NAME.sql $V_PARAM1 $V_PARAM2 $V_PARAM3 $V_PARAM4
		sql_return_code=$?
	fi
	log ${LOG_PREFIX} "	Execucao de $V_SQL_FILE_NAME.sql finalizada: [`date`]"
	if [ $sql_return_code != 0 ]
	then
		log ${LOG_PREFIX} "	ERRO: Falha ao executar o arquivo $V_SQL_FILE_NAME.sql[$sql_return_code]"
		log ${LOG_PREFIX} "<<- executaQuerySemGravarEstado"
		rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
		exit -1;
	fi
	log ${LOG_PREFIX} "<<- executaQuerySemGravarEstado"
}