#!/bin/bash
############################################################
#  BATCH......: cargaLinhasParceiros
#  Criado por.: Eder Jani Martins (19/02/2015)
#  Funcional..: Marcia Santana Silva
############################################################

function carregaArquivo () {
	log ${LOG_PREFIX} "->> carregaArquivo"
	log ${LOG_PREFIX} "\t================================"
	log ${LOG_PREFIX} "\tIniciando a importacao dos arquivos para LOAD.CARGALINHASPARCEIROS_01"
	log ${LOG_PREFIX} "\t================================"

	arquivo=$1
	log ${LOG_PREFIX} "\tArquivo de entrada [$arquivo]"
	#remove a extensao do arquivo
	arquivo_base="${arquivo%.*}"
	log ${LOG_PREFIX} "\tNome base do arquivo [$arquivo_base]"
	arquivoPar=$2
	log ${LOG_PREFIX} "\tArquivo .PAR [$arquivoPar]"
	
	log ${LOG_PREFIX} "\tIniciando carga do arquivo: [$arquivo]"
	${SQLLDR} userid=${ORABATCH_CONNECTION} parfile=${arquivoPar} data=${ORABATCH_DIR}/${arquivo} log=${ORABATCH_DIR}/${arquivo_base}.log bad=${ORABATCH_DIR}/${arquivo_base}.bad discard=${ORABATCH_DIR}/${arquivo_base}.dsc	
	RETCODE=$?

	log ${LOG_PREFIX} "\t================================"
	case "${RETCODE}" in 
	0) 
		log ${LOG_PREFIX} "\tSQL*Loader - execucao concluida com EX_OK"
		;; 
	1) 
		log ${LOG_PREFIX} "\tSQL*Loader - execucao concluida com EX_FAIL, verifique logfile"
		log ${LOG_PREFIX} "\tSQL*Loader - verifique a pasta de log"
		;; 
	2) 
		log ${LOG_PREFIX} "\tSQL*Loader - execucao concluida com EX_WARN"
		log ${LOG_PREFIX} "\tSQL*Loader - verifique a pasta de log"
		;; 
	3) 
		log ${LOG_PREFIX} "\tSQL*Loader - ERRO fatal na execucao"
		log ${LOG_PREFIX} "\tSQL*Loader - verifique a pasta de log"
		;; 
	*) 
		log ${LOG_PREFIX} "\tSQL*Loader - ERRO desconhecido"
		log ${LOG_PREFIX} "\tSQL*Loader - verifique a pasta de log"
		;; 
	esac
	if [ -f "${ORABATCH_DIR}/${arquivo_base}.log" ]
	then
		log ${LOG_PREFIX} "\tMovendo ${ORABATCH_DIR}/${arquivo_base}.log para ${ORABATCH_DIR_LOG}"
		mv ${ORABATCH_DIR}/${arquivo_base}.log ${ORABATCH_DIR_LOG}
		log ${LOG_PREFIX} "\tArquivo movido para ${ORABATCH_DIR_LOG}"
	fi
	if [ -f "${ORABATCH_DIR}/${arquivo_base}.bad" ]
	then
		log ${LOG_PREFIX} "\tMovendo ${ORABATCH_DIR}/${arquivo_base}.bad para ${ORABATCH_DIR_LOG}"
		mv ${ORABATCH_DIR}/${arquivo_base}.bad ${ORABATCH_DIR_LOG}
		log ${LOG_PREFIX} "\tArquivo movido para ${ORABATCH_DIR_LOG}"
	fi
	if [ "${RETCODE}" != "0" -a "${RETCODE}" != "2" ]
	then
		log ${LOG_PREFIX} "\t================================"
		log ${LOG_PREFIX} "<<- carregaArquivo"
		exit ${RETCODE}
	fi
	log ${LOG_PREFIX} "\t================================"
	log ${LOG_PREFIX} "<<- carregaArquivo"
}



