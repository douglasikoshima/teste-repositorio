#!/bin/bash
############################################################
#  BATCH......: meuVivoDW_Anual
#  Criado por.: Eder Jani Martins (11/02/2015)
#  Alterado por: Lucas Gomes(03/09/2015)
#  Funcional..: Marcia Santana Silva
############################################################

function carregaArquivosAtendimentoParaDistinct () {
	log ${LOG_PREFIX} "->> carregaArquivosParaDistinct"
	log ${LOG_PREFIX} "\t================================"
	log ${LOG_PREFIX} "\tFAZENDO DISTINCT DOS ARQUIVOS"
	log ${LOG_PREFIX} "\t================================"
	log ${LOG_PREFIX} "\tIniciando a importacao dos arquivos para LOAD.MEUVIVODW_01"
	log ${LOG_PREFIX} "\t================================"

	parametro=$1
	log ${LOG_PREFIX} "\tParametro de entrada [$parametro]"
	arquivoPar=$2
	log ${LOG_PREFIX} "\tArquivo .PAR [$arquivoPar]"
	
	for arquivos in ${parametro}*.txt.gz
	do
		#descomprime o arquivo
		arquivo=${arquivos%.*}
		descomprimeArquivo ${NOME_ARQUIVO_ATENDIMENTO}${arquivos:(-15):12} ${arquivo}

		log ${LOG_PREFIX} "\tIniciando carga do arquivo: [$arquivo]"
		${SQLLDR} userid=${ORABATCH_CONNECTION} parfile=${ORABATCH_DIR}/sql/${arquivoPar} data=${ORABATCH_DIR}/${arquivo} log=${ORABATCH_DIR}/${arquivo}.log bad=${ORABATCH_DIR}/${arquivo}.bad discard=${ORABATCH_DIR}/${arquivo}.dsc	
		RETCODE=$?

		case "${RETCODE}" in 
		0) 
			log ${LOG_PREFIX} "\tSQL*Loader - Arquivo carregado com retorno EX_OK"
			log ${LOG_PREFIX} "\tApagando o arquivo [${ORABATCH_DIR}/${arquivo}]"
			rm -f ${ORABATCH_DIR}/${arquivo} 2> /dev/null
			# Se sucesso apaga o arquivo de log, nao sera necessario
			rm -f ${ORABATCH_DIR}/${arquivo}.log 2> /dev/null
			rm -f ${ORABATCH_DIR}/${arquivo} 2> /dev/null
			log ${LOG_PREFIX} "\tArquivo apagado"
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
			log ${LOG_PREFIX} "<<- carregaArquivosParaDistinct"
			rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
			exit ${RETCODE}
		fi
	done
	log ${LOG_PREFIX} "<<- carregaArquivosParaDistinct"
}

function carregaArquivoClienteParaDistinct () {
	log ${LOG_PREFIX} "->> carregaArquivoClienteParaDistinct"
	log ${LOG_PREFIX} "\t================================"
	log ${LOG_PREFIX} "\tIniciando a importacao dos arquivos para LOAD.MEUVIVODW_02"
	log ${LOG_PREFIX} "\t================================"

	#arqv=$1.txt.gz
	arquivo=$1
	#log ${LOG_PREFIX} "\tParametro de entrada [$arqv]"
	log ${LOG_PREFIX} "\tParametro de entrada [$arquivo]"
	arquivoPar=MEUVIVODW_ANUAL_CARGA_DISTINCT_CLIENTE
	log ${LOG_PREFIX} "\tArquivo .PAR [$arquivoPar]"

	#descomprime o arquivo
	#nomeArquivo=`basename ${arqv}`
	#arquivo=${arqv%.*}
	#descomprimeArquivo ${nomeArquivo%.*} ${arquivo}

	# A primeira coisa a fazer e apagar a primeira tabela temporaria LOAD.MEUVIVODW_02
	#executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/MEUVIVODW_TRUNCATABELA ${SEM_RETORNO} 02

	log ${LOG_PREFIX} "\tIniciando carga do arquivo: [$arquivo]"
	${SQLLDR} userid=${ORABATCH_CONNECTION} parfile=${ORABATCH_DIR}/sql/${arquivoPar}.par data=${ORABATCH_DIR}/${arquivo} log=${ORABATCH_DIR}/${arquivo}.log bad=${ORABATCH_DIR}/${arquivo}.bad discard=${ORABATCH_DIR}/${arquivo}.dsc	
	RETCODE=$?

	case "${RETCODE}" in 
	0) 
		log ${LOG_PREFIX} "\tSQL*Loader - Arquivo carregado com retorno EX_OK"
		log ${LOG_PREFIX} "\tApagando o arquivo [${ORABATCH_DIR}/${arquivo}]"
		#rm -f ${ORABATCH_DIR}/${arquivo} 2> /dev/null
		mv ${ORABATCH_DIR}/${arquivo} ${ORABATCH_DIR}/${arquivo}_SEM_DISTINCT
		# Se sucesso apaga o arquivo de log, nao sera necessario
		rm -f ${ORABATCH_DIR}/${arquivo}.log 2> /dev/null
		rm -f ${ORABATCH_DIR}/${arquivo} 2> /dev/null
		log ${LOG_PREFIX} "\tArquivo apagado"
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
		log ${LOG_PREFIX} "<<- carregaArquivoClienteParaDistinct"
		rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
		exit ${RETCODE}
	fi

	log ${LOG_PREFIX} "<<- carregaArquivoClienteParaDistinct"
}



