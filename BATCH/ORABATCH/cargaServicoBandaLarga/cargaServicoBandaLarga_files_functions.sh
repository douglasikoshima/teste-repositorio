#!/bin/bash
############################################################
#  BATCH......: cargaServicoBandaLarga
#  Criado por.: Jair  14/01/2016
#  Funcional..: Mirian Yuka
############################################################

############################################################
#  Move os arquivos do dia anterior para a parta hist
############################################################
function move_arquivos_para_hist () {
	log ${LOG_PREFIX} "->> move_arquivos_para_hist"
	log ${LOG_PREFIX} "	Verificando se existem arquivos para serem movidos para a pasta hist"

	for arquivo in ${ORABATCH_DIR_PRC}/${ARQUIVO_BASE}.[tT][xX][tT]
	do
		# Verifica se o arquivo existe (as vezes o for..in.. nao acha o arquivo e retorna o valor de pesquisa)
		if [ -f ${arquivo} ]
		then
				log ${LOG_PREFIX} "	Movendo o arquivo ${arquivo} para ${ORABATCH_DIR_HIST}"
				mv -f ${arquivo} ${ORABATCH_DIR_HIST}
				log ${LOG_PREFIX} "	Arquivo movido com sucesso!"
		fi
	done
	log ${LOG_PREFIX} "<<- move_arquivos_para_hist"
}

############################################################
# Salva os dados (passado por parametro) em um arquivo (tambem passado por parametro)
############################################################
function salvaDadoNoArquivo () {
	log ${LOG_PREFIX} "->> salvaDadoNoArquivo"
	# Recupera o nome do arquivo
	arquivo=$1
	# Recupera o dado a ser gravado
	dados=$2
	log ${LOG_PREFIX} "\tArquivo: [$arquivo]"
	log ${LOG_PREFIX} "\tDados..: [$dados]"
	tamanho=${#dados}
	# Grava o dados no arquivo
	echo $dados > $arquivo
	# Recupera o nome do arquivo
	return_code=$?
	if [ $return_code != 0 ]
	then
		log ${LOG_PREFIX} "\tERRO: Nao foi possivel gravar [$dados] no arquivo [$arquivo]"
		log ${LOG_PREFIX} "<<- salvaDadoNoArquivo"
		processoEmExecucao "PARAR"
		exit -1;
	fi
	log ${LOG_PREFIX} "<<- salvaDadoNoArquivo"
}

############################################################
# Abre o arquivo e retorna TODO o conteudo para uma variavel
############################################################
function buscaDadoNoArquivo() {
	log ${LOG_PREFIX} "->> buscaDadoNoArquivo"
	# Recupera o nome do arquivo
	arquivo=$1
	# Le o conteudo do arquivo e salva na variavel global de retorno
	retorno=`cat $arquivo`
	# Verifica se houve erro
	return_code=$?
	if [ $return_code != 0 ]
	then
		log ${LOG_PREFIX} "\tERRO: Nao foi possivel ler o arquivo [$arquivo]"
		log ${LOG_PREFIX} "<<- buscaDadoNoArquivo"
		processoEmExecucao "PARAR"
		exit -1
	fi
	log ${LOG_PREFIX} "\tDado lido no arquivo [$retorno]"
	log ${LOG_PREFIX} "<<- buscaDadoNoArquivo"
}

############################################################
# Indica que o processo está em execucao ou parado. Evita a dupla execucao
############################################################
function processoEmExecucao() {
	log ${LOG_PREFIX} "->> processoEmExecucao"
	# Recupera o parametro de entrada
	parametro_processo=$1
	log ${LOG_PREFIX} "\tParametro recebido: [${parametro_processo}]"
	if [ ${parametro_processo} = "INICIAR" ]
	then
		log ${LOG_PREFIX} "\tArquivo de lock gravado com sucesso"
		echo "Processo iniciado em: `date +"%d/%m/%Y %H:%M:%S"`" > ${ARQUIVO_EM_EXECUCAO}
	elif [ ${parametro_processo} = "PARAR" ]
	then
		log ${LOG_PREFIX} "\tArquivo de lock apagado com sucesso"
		rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
 	elif [ ${parametro_processo} = "ESTADO" ]
	then
		if [ -f ${ARQUIVO_EM_EXECUCAO} ]
		then
			log ${LOG_PREFIX} "\tProcesso em EXECUCAO"
			retorno="EXECUTANDO"
		else
			log ${LOG_PREFIX} "\tProcesso em PARADO"
			retorno="PARADO"
		fi
	else
		log ${LOG_PREFIX} "\tParametro recebido invalido. Processo sera abortado."
		log ${LOG_PREFIX} "<<- processoEmExecucao"
		rm -f ${ARQUIVO_EM_EXECUCAO} 2> /dev/null
		exit -1
	fi
	log ${LOG_PREFIX} "<<- processoEmExecucao"
}

