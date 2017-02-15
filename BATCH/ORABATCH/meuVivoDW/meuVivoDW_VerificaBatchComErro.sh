#!/bin/bash
############################################################
#  BATCH......: meuVivoDW
#  Criado por.: Eder Jani Martins (11/02/2015)
#  Alterado por: Lucas Gomes(03/09/2015)
#  Funcional..: Marcia Santana Silva
############################################################

############################################################
# FUNCAO PESQUISA O ULTIMO ESTADO
############################################################
function buscaEstado() {
	log ${LOG_PREFIX} "->> buscaEstado"
	# Busca o ultimo estado de processamento 
	retorno=`sqlplus -s ${ORABATCH_CONNECTION} <<EOF
	            SET PAGESIZE 0 
	            SET FEEDBACK OFF
	            SET VERIFY OFF
	            SET HEADING OFF
	            SET ECHO OFF
	            SELECT DSVALORPARAMETRO FROM APOIO.PARAMETRO WHERE CDPARAMETRO = 'MEUVIVODW_ESTADO';
	            EXIT;
	            EOF`
	# Verifica se houve erro no sqlplus 
	sql_return_code=$?
	if [ $sql_return_code != 0 ]
	then
		log ${LOG_PREFIX} "ERRO: Nao foi possivel carregar o parametro 'MEUVIVODW_ESTADO' em APOIO.PARAMETRO. Codigo de erro [$retorno]"
		exit -1
	fi
	log ${LOG_PREFIX} "	Estado atual [$retorno]"
	log ${LOG_PREFIX} "<<- buscaEstado"
}

############################################################
# FUNCAO PESQUISA O ULTIMA DATA
############################################################
function buscaUltimaDataProcessamento () {
	log ${LOG_PREFIX} "->> buscaUltimaDataProcessamento"
	# Busca a ultima data de processamento
	retorno=`sqlplus -s ${ORABATCH_CONNECTION} <<EOF
	            SET PAGESIZE 0 
	            SET FEEDBACK OFF
	            SET VERIFY OFF
	            SET HEADING OFF
	            SET ECHO OFF
	            SELECT TO_CHAR(DTULTIMAALTERACAO, 'DD/MM/YYYY HH24:MI:SS') FROM APOIO.PARAMETRO WHERE CDPARAMETRO = 'MEUVIVODW_ESTADO';
	            EXIT;
	            EOF`
	# Verifica se houve erro no sqlplus 
	sql_return_code=$?
	if [ $sql_return_code != 0 ]
	then
		log ${LOG_PREFIX} "ERRO: Nao foi possivel buscar a ultima de processamento do parametro 'MEUVIVODW_ESTADO' em APOIO.PARAMETRO. Codigo de erro [$retorno]"
		exit -1;
	fi
	log ${LOG_PREFIX} "\tUltima data processada [$retorno]"
	log ${LOG_PREFIX} "<<- buscaUltimaDataProcessamento"
}

############################################################
# FUNCAO PESQUISA O ULTIMO CRITERIO DE PROCESSAMENTO
############################################################
function buscaCriterioUltimoProcessamento () {
	log ${LOG_PREFIX} "->> buscaCriterioUltimoProcessamento"
	#Busca o ultimo criterio de processamento (eh uma data, pois o MEUVIVODW processa por dia)
	retorno=`sqlplus -s ${ORABATCH_CONNECTION} <<EOF
	            SET PAGESIZE 0 
	            SET FEEDBACK OFF
	            SET VERIFY OFF
	            SET HEADING OFF
	            SET ECHO OFF
				
	            SELECT DSVALORPARAMETRO FROM APOIO.PARAMETRO WHERE CDPARAMETRO = 'MEUVIVODW_CRITERIO';
				
	            EXIT;
	            EOF`

	retorno_secundario=`sqlplus -s ${ORABATCH_CONNECTION} <<EOF
	            SET PAGESIZE 0 
	            SET FEEDBACK OFF
	            SET VERIFY OFF
	            SET HEADING OFF
	            SET ECHO OFF
				
	            SELECT TO_CHAR(TO_DATE(DSVALORPARAMETRO, 'DD/MM/YYYY'), 'DDMMYYYY') AS DATA FROM APOIO.PARAMETRO WHERE CDPARAMETRO = 'MEUVIVODW_CRITERIO';
				
	            EXIT;
	            EOF`
		# Verifica se houve erro no sqlplus 
	sql_return_code=$?
	if [ $sql_return_code != 0 ]
	then
		log ${LOG_PREFIX} "ERRO: Nao foi possivel buscar o ultimo criterio processamento do parametro 'MEUVIVODW_CRITERIO' em APOIO.PARAMETRO. Codigo de erro [$retorno]"
		exit -1;
	fi
	log ${LOG_PREFIX} "	Ultimo criterio (data que sera usada no MEUVIVODW) [$retorno]"
	log ${LOG_PREFIX} "<<- buscaCriterioUltimoProcessamento"
}

############################################################
# FUNCAO PESQUISA A ULTIMA ETAPA DE PROCESSAMENTO
############################################################
function buscaEtapaUltimoProcessamento () {
	log ${LOG_PREFIX} "->> buscaEtapaUltimoProcessamento"
	#Busca o ultimo criterio de processamento (eh uma data, pois o MEUVIVODW processa por dia)
	retorno=`sqlplus -s ${ORABATCH_CONNECTION} <<EOF
	            SET PAGESIZE 0 
	            SET FEEDBACK OFF
	            SET VERIFY OFF
	            SET HEADING OFF
	            SET ECHO OFF
	            SELECT DSVALORPARAMETRO FROM APOIO.PARAMETRO WHERE CDPARAMETRO = 'MEUVIVODW_ETAPA';
	            EXIT;
	            EOF`
	# Verifica se houve erro no sqlplus 
	sql_return_code=$?
	if [ $sql_return_code != 0 ]
	then
		log ${LOG_PREFIX} "ERRO: Nao foi possivel buscar a ultima etapa de processamento do parametro 'MEUVIVODW_ETAPA' em APOIO.PARAMETRO. Codigo de erro [$retorno]"
		exit -1;
	fi
	log ${LOG_PREFIX} "	Ultima etapa processada [$retorno]"
	log ${LOG_PREFIX} "<<- buscaEtapaUltimoProcessamento"
}


############################################################
# FUNCAO PESQUISA E REPROCESSA A ULTIMA EXECUCAO COM ERRO
############################################################
function verificaBatchComErro () {
	log ${LOG_PREFIX} "->> verificaBatchComErro"
	valor=0
	# Busca a ultima data de processamento
	buscaUltimaDataProcessamento
	dataUltimoProcessamento=$retorno

	# Busca o ultimo estado de processamento
	buscaEstado
	estadoUltimoProcessamento=$retorno

	#Busca o ultimo criterio de processamento (eh uma data, pois o MEUVIVODW processa por dia)
	buscaCriterioUltimoProcessamento
	criterioUltimoProcessamento=$retorno

	# Verifica em qual estado o MEUVIVODW se encontra e toma uma decisao
	log ${LOG_PREFIX} "estadoUltimoProcessamento[$estadoUltimoProcessamento]"
	case "$estadoUltimoProcessamento" in
		"PROCESSANDO")
			retorno="	Estado[PROCESSANDO]: O MEUVIVODW esta em processamento, verifique se o processo esta em execucao no servidor de BATCH\n\tCaso nao esteja em execucao, mude o parametro 'MEUVIVODW_ESTADO' em APOIO.PARAMETRO para FINALIZADO\n\te execute o MEUVIVODW novamente"
			valor=2 # Aborta, ou o MEUVIVODW esta processando ou terminou e ficou num estado inconsistente
			;;
		"FINALIZADO") 
			retorno="	Estado[FINALIZADO]: MEUVIVODW foi finalizado com sucesso em [$dataUltimoProcessamento]\n\tO criterio de pesquisa foi [$criterioUltimoProcessamento]\n\tNao houve erros o MEUVIVODW ira retomar a pesquisa com o criterio seguinte (que eh a data acima +1 dia)"
			valor=0 # Indica para iniciar o proximo dia
			;;
		"ERRO")
			retorno="	Estado[ERRO]: MEUVIVODW foi finalizado com erro em [$dataUltimoProcessamento]\n\tIra reiniciar com o criterio de pesquisa [$criterioUltimoProcessamento]"
			valor=1 # Deve reprocessar da etapa em que parou
			;;
		*) 
			retorno="	ERRO: Estado do processo MEUVIVODW inconsistente: [$estadoUltimoProcessamento].\n\tVerifique o parametro 'MEUVIVODW_ESTADO' em APOIO.PARAMETRO"
			valor=2 # Aborta, o parametro MEUVIVODW_ESTADO nao existe ou esta com um valor errado
			;;
	esac
	log ${LOG_PREFIX} "$retorno"
	log ${LOG_PREFIX} "<<- verificaBatchComErro:Retorno[$valor]"
	return $valor
}

############################################################
# FUNCAO VERIFICA SE JA PROCESSOU ATE SYSDATE-1
############################################################
function temPesquisaRetroativa() {
	log ${LOG_PREFIX} "->> temPesquisaRetroativa"
	# Retorna a quantidade de dias entre o ultimo criterio (data utilizada na pesquisa) e SYSDATE
	retorno=`sqlplus -s ${ORABATCH_CONNECTION} <<EOF
	            SET PAGESIZE 0 
	            SET FEEDBACK OFF
	            SET VERIFY OFF
	            SET HEADING OFF
	            SET ECHO OFF
				
				SELECT TRIM(TRUNC(SYSDATE-1) - TO_DATE(
					   (SELECT DSVALORPARAMETRO
						  FROM APOIO.PARAMETRO
						 WHERE CDPARAMETRO = 'MEUVIVODW_CRITERIO'), 'DD/MM/YYYY')) AS DIAS
				  FROM DUAL;
				
	            EXIT;
	            EOF`
	# Verifica se houve erro no sqlplus 
	sql_return_code=$?
	if [ $sql_return_code != 0 ]
	then
		log ${LOG_PREFIX} "ERRO: Nao foi possivel contas os dias atraves do parametro 'MEUVIVODW_CRITERIO' em APOIO.PARAMETRO. Codigo de erro [$retorno]"
		exit -1
	fi
	log ${LOG_PREFIX} "	Quantidade de dias entre a ultima pesquisa e SYSDATE-1 [$retorno]"
	log ${LOG_PREFIX} "<<- temPesquisaRetroativa"
	return $retorno
}
