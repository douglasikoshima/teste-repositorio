#!/bin/bash
############################################################
#  BATCH......: expurgoSincronismoAMDOCS
#  Criado por.: Lucas Gomes Fenelon de Moraes (12/04/2016)
#  Funcional..: Erika Brum
############################################################

function buscaDadoNoArquivo() {
	log ${LOG_PREFIX} "->> buscaDadoNoArquivo"
	# Busca o ultimo estado de processamento 
	arquivo=$1
	retorno=`cat $arquivo`
	
	# Verifica se houve erro no sqlplus 
	return_code=$?
	if [ $return_code != 0 ]
	then
		log ${LOG_PREFIX} "\tERRO: Nao foi possivel ler o arquivo [$arquivo]"
		log ${LOG_PREFIX} "<<- buscaDadoNoArquivo"
		rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
		exit -1
	fi

	log ${LOG_PREFIX} "\tDado lido no arquivo [$retorno]"
	log ${LOG_PREFIX} "<<- buscaDadoNoArquivo"
}

function salvaDadoNoArquivo() {
	log ${LOG_PREFIX} "->> salvaDadoNoArquivo"
	arquivo=$1
	dados=$2
	log ${LOG_PREFIX} "\tArquivo: [$arquivo]"
	log ${LOG_PREFIX} "\tDados..: [$dados]"
	tamanho=${#dados}
	echo $dados > $arquivo
	return_code=$?
	if [ $return_code != 0 ]
	then
		log ${LOG_PREFIX} "\tERRO: Nao foi possivel gravar isto [$dados] no arquivo [$arquivo]"
		log ${LOG_PREFIX} "<<- salvaDadoNoArquivo"
		rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
		exit -1;
	fi
	log ${LOG_PREFIX} "<<- salvaDadoNoArquivo"
}

function verficaExecucao() {
	log ${LOG_PREFIX} "->> verficaSeEstaEmExecucao"
	#Arquivo recebido como parametro
	arquivo=$1
	# Se não achar o arquivo retorna zero
	retorno=0
	log ${LOG_PREFIX} "\tArquivo: [$arquivo]"
	if [ -f "$arquivo" ]
	then
		log ${LOG_PREFIX} "\t${LOG_PREFIX} EM execucao. \n\tArquivo [$arquivo] encontrado. "
		retorno=1
	else
		echo 1 > $arquivo
		log ${LOG_PREFIX} "\t${LOG_PREFIX} NAO esta em execucao. \n\tArquivo de controle de execucao criado [${arquivo}]."
	fi
	log ${LOG_PREFIX} "<<- verficaSeEstaEmExecucao"
	return $retorno
}

############################################################
# Realiza um ZIP no arquivo afim de comprimir e diminuir o 
# tamanho de espaco alocado em disco
############################################################
function comprimeArquivo() {
	log ${LOG_PREFIX} "->> comprimeArquivo"
	nomeArquivo=$1
	arquivo=$2

	gzip $arquivo

	if [ `find . -name  $nomeArquivo.gz` == "./$arquivo.gz" ]
	then
			log ${LOG_PREFIX} "\tArquivo [$nomeArquivo.gz] processado"
			retorno="$arquivo"
			return_code=1
	else
			return_code=0
	fi

	# Verifica se houve erro no processo 
	if [ $return_code != 0 ]
	then
		log ${LOG_PREFIX} "\tERRO: Nao foi possivel comprimir o arquivo [$arquivo]"
		log ${LOG_PREFIX} "<<- comprimeArquivo"
		rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
		exit -1
	fi
	log ${LOG_PREFIX} "\tArquivo [$retorno] comprimido"
	log ${LOG_PREFIX} "<<- comprimeArquivo"
}

############################################################
# Realiza um UNZIP no arquivo afim de descomprimir um arquivo
# que esteja comprimido
############################################################
function descomprimeArquivo() {
	log ${LOG_PREFIX} "->> descomprimeArquivo"
	nomeArquivo=$1
	arquivo=$2

	gunzip $arquivo.gz

	if [ `find . -name  $nomeArquivo` == "./$arquivo" ]
	then
			log ${LOG_PREFIX} "\tArquivo [$nomeArquivo] processado"
			retorno="$arquivo"
			return_code=1
	else
			return_code=0
	fi

	# Verifica se houve erro no processo 
	if [ $return_code != 0 ]
	then
		log ${LOG_PREFIX} "\tERRO: Nao foi possivel descomprimir o arquivo [$arquivo.gz]"
		log ${LOG_PREFIX} "<<- descomprimeArquivo"
		rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
		exit -1
	fi
	log ${LOG_PREFIX} "\tArquivo [$retorno.gz] descomprimido"
	log ${LOG_PREFIX} "<<- descomprimeArquivo"
}