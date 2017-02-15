#!/bin/bash
############################################################
#  BATCH......: expurgoRessarcimento
#  Criado por.: Eder Jani Martins (08/05/2015)
#  Funcional..: Erika Brum
############################################################

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