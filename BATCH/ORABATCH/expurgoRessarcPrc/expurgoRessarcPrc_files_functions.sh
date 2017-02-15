#!/bin/bash
############################################################
#  BATCH......: expurgoRessarcPrc
#  Criado por.: Eder Jani Martins (04/05/2015)
#  Funcional..: Erika Brum
############################################################

############################################################
#  Apaga arquivos com mais de x dias
#    Parametro 1: Path de onde estao os arquivos a serem apagados sem "/" no final
#    Parametro 2: Chave de pesquisa Exemplos: *.txt, ARQUIVO*.log, *, etc.
#    Parametro 3: Quantidade de dias (somente arquivo mais velhos que a quantidade de dias serao apagados)
############################################################
function apagaAquivos() {
	log ${LOG_PREFIX} "->> apagaAquivos"
	caminho=${1}
	quantidadeDias=${2}	
	chavePesquisa='*'
	
	log ${LOG_PREFIX} "\t========================="
	log ${LOG_PREFIX} "\t caminho........: ${caminho}"
	log ${LOG_PREFIX} "\t chavePesquisa..: ${chavePesquisa}"
	log ${LOG_PREFIX} "\t quantidadeDias.: ${quantidadeDias}"
	quantidadeHoras=$((quantidadeDias * 24))
	log ${LOG_PREFIX} "\t quantidadeHoras: ${quantidadeHoras}"
	log ${LOG_PREFIX} "\t========================="
	#Recupera arquivo maior que x dias, como "-cmin" eh em horas, logo tem que fazer: quantidadeDias * 24
	log ${LOG_PREFIX} "\tIniciando a remocao de arquivos em historico"
	for arquivo in $(find ${caminho}/${chavePesquisa} -cmin +${quantidadeHoras})
	do
		# Verifica se o arquivo existe (as vezes o for..in.. nao acha o arquivo e retorna o valor de pesquisa)
		if [ -f ${arquivo} ]
		then
			log ${LOG_PREFIX} "\tApagando o arquivo ${arquivo}"
			rm -f ${arquivo}
			log ${LOG_PREFIX} "\tArquivo apagado com sucesso!"
		fi
	done
	log ${LOG_PREFIX} "<<- apagaAquivos"
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