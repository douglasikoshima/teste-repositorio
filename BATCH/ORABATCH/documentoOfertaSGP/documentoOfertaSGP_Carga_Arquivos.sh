#!/bin/bash
############################################################
#  BATCH......: documentoOfertaSGP
#  Criado por.: Lucas Gomes (29/09/2015)
#  Funcional..: Dener Neves dos Santos
############################################################

function carregaArquivosDocumentoPresentinho () {
	log ${LOG_PREFIX} "->> carregaArquivosDocumentoPresentinho"
	log ${LOG_PREFIX} "================================"
	log ${LOG_PREFIX} "CARREGANDO DADOS DO ARQUIVOS"
	log ${LOG_PREFIX} "================================"
	log ${LOG_PREFIX} "Iniciando a importacao dos arquivos para LOAD.DOCUMENTOOFERTASGP"
	log ${LOG_PREFIX} "================================"

	parametro=$1
	log ${LOG_PREFIX} "Parametro de entrada [$parametro]"
	arquivoPar=$2
	log ${LOG_PREFIX} "Arquivo .PAR [$arquivoPar]"

	for arquivo in ${parametro}*.txt
	do
		log ${LOG_PREFIX} "\tIniciando carga do arquivo: [$arquivo]"
		${SQLLDR} userid=${ORABATCH_CONNECTION} parfile=${ORABATCH_DIR}/sql/${arquivoPar} data=${ORABATCH_DIR}/${arquivo} log=${ORABATCH_DIR}/${arquivo}.log bad=${ORABATCH_DIR}/${arquivo}.bad discard=${ORABATCH_DIR}/${arquivo}.dsc	
		RETCODE=$?

		case "${RETCODE}" in 
		0) 
			log ${LOG_PREFIX} "SQL*Loader - Arquivo carregado com retorno EX_OK"
			# Se sucesso apaga o arquivo de log, nao sera necessario
			rm -f ${ORABATCH_DIR}/${arquivo}.log 2> /dev/null
			log ${LOG_PREFIX} "Arquivo de log apagado"
			mv -f ${arquivo} ${ORABATCH_DIR_DATA_IN} 
			log ${LOG_PREFIX} "mv ${arquivo} ${ORABATCH_DIR_DATA_IN}"
			;; 
		1) 
			log ${LOG_PREFIX} "SQL*Loader - execucao concluida com EX_FAIL, verifique logfile"
			;; 
		2) 
			log ${LOG_PREFIX} "SQL*Loader - execucao concluida com EX_WARN"
			mv -f ${arquivo} ${ORABATCH_DIR_DATA_IN} 
			log ${LOG_PREFIX} "mv ${arquivo} ${ORABATCH_DIR_DATA_IN}"
			;; 
		3) 
			log ${LOG_PREFIX} "SQL*Loader - ERRO fatal na execucao"
			;; 
		*) 
			log ${LOG_PREFIX} "\tSQL*Loader - ERRO desconhecido"
			;; 
		esac

		if [ "${RETCODE}" != "0" -a "${RETCODE}" != "2" ]
		then
			log ${LOG_PREFIX} "<<- carregaArquivosDocumentoPresentinho"
			rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
			exit ${RETCODE}
		fi
	done
	log ${LOG_PREFIX} "<<- carregaArquivosDocumentoPresentinho"
}



