#!/bin/bash
############################################################
#  BATCH......: expurgoSincronismoAMDOCS
#  Criado por.: Lucas Gomes Fenelon de Moraes (12/04/2016)
#  Funcional..: Erika Brum
############################################################

############################################################
# CARREGAMENTO DE ARQUIVOS COM CONFIGURACOES DE BANCO
############################################################

function processaExpurgoBkp (){
	log ${LOG_PREFIX} "->> processaExpurgoBkp"

	estado=$1
	dataProcessamento=$2

	salvaDadoNoArquivo ${ARQUIVO_ESTADO_PROCESSAMENTO} "$estado" 
	buscaDadoNoArquivo ${ARQUIVO_ESTADO_PROCESSAMENTO}

	arquivo=${ARQUIVO_BACKUP}${retorno:21}_$dataProcessamento.txt
	nomeArquivo=${NOME_ARQUIVO_ATENDIMENTO}${retorno:21}_$dataProcessamento
	
	executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/${retorno:14} ${SEM_RETORNO} $arquivo $dataProcessamento
	comprimeArquivo $nomeArquivo $arquivo
	
	log ${LOG_PREFIX} "\tEstado processado [$estado]"
	log ${LOG_PREFIX} "<<- processaExpurgoBkp"
}

function processaExpurgo (){
	log ${LOG_PREFIX} "->> processaExpurgo"
	
	estado=$1
	dataProcessamento=$2

	salvaDadoNoArquivo ${ARQUIVO_ESTADO_PROCESSAMENTO} "$estado" 
	buscaDadoNoArquivo ${ARQUIVO_ESTADO_PROCESSAMENTO}	
	executaQuery ${ORABATCH_CONNECTION} ${ORABATCH_DIR} sql/${retorno:14} ${SEM_RETORNO} $dataProcessamento
	
	log ${LOG_PREFIX} "\tEstado processado [$estado]"
	log ${LOG_PREFIX} "<<- processaExpurgo"
}

function processaExpurgoArquivos (){
	log ${LOG_PREFIX} "->> processaExpurgoArquivos"
	
	baseArquivo=$1
	path="hist/"

	for arquivo in ${path}${baseArquivo}*.txt.gz
	do
		validaDiferencaDatas ${arquivo:(-15):8}
		log ${LOG_PREFIX} "\tRetorno de diferenca de datas [$retorno]"
		if [ $retorno == 0 ]
		then
			log ${LOG_PREFIX} "\tArquivo ${arquivo} fora do tempo de expurgo"
		else
			rm -f ${arquivo} 2> /dev/null
			log ${LOG_PREFIX} "\tArquivo ${arquivo} expurgado"
		fi
	done
	
	log ${LOG_PREFIX} "\tEstado processado [$baseArquivo] efetuado."
	log ${LOG_PREFIX} "<<- processaExpurgoArquivos"
}
