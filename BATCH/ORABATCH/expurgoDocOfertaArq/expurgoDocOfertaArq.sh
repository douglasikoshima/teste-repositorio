#!/bin/sh -

# Input File Separator $'\n'
IFS='
'

# Diretorio do script
DIR_NAME=$(dirname $0)

# Entra no diretorio do script
cd $DIR_NAME

# Nome do script
BASE_NAME=$(basename $0|cut -d. -f1)

# Arquivos de configuracao
DIR_CFG=cfg
CONFIG_FILE_DEF=${DIR_CFG}/${BASE_NAME}.$(hostname).$(whoami).cfg
CONFIG_FILE=${DIR_CFG}/${BASE_NAME}.cfg
if [ -e ${CONFIG_FILE_DEF} ]
then
	# Carrega configuracao para maquina.usuario
	. ./${CONFIG_FILE_DEF}
elif [ -e ${CONFIG_FILE} ]
then
	# Carrega configuracao padrao
	. ./${CONFIG_FILE}
else
	# Arquivo de configuracao nao encontrado
	echo "ERRO: Arquivo de configuracao (${CONFIG_FILE_DEF} ou ${CONFIG_FILE}) nao encontrado!" 
	exit -1
fi

# Variaveis para export
export LC_ALL

# Data do processo
DATE_TIME=$(date ${DATE_MASK})

# Arquivo de log
LOG_FILE="${DIR_LOG}/${BASE_NAME}-${DATE_TIME}${FILE_LOG_EXT}"

# Variavel com codigo de retorno
RET_CODE=0

# Redireciona stdout e stderr para arquivo de log
exec 1>> ${LOG_FILE}
exec 2>> ${LOG_FILE}

# Arquivo de trava
if ! test "${LOCK_FILE}"
then
	LOCK_FILE=${DIR_TMP}/${BASE_NAME}.lock
fi

# Funcoes
printm() {
	echo $(date ${DATE_MASK}) -- $@
}

lockoff() {
	printm "Removendo arquivo de trava: ${LOCK_FILE}"
	rm -f ${LOCK_FILE}
	return $?
}

finish() {
	# Remove arquivo de trava
	lockoff
	
	# Codigo de retorno
	printm "Saindo com codigo de retorno: ${RET_CODE}"

	# Fim
	printm "${BASE_NAME} : Fim do processamento"

	# Retorna status code do processo
	exit ${RET_CODE}
}

abort() {
	if (( $# ))
	then
		printm "abort:" $*
	else
		printm "abort: Sinal de termino recebido, saindo..."
	fi
	
	# Seta status code p/ erro
	if test -z "${RET_CODE}" -o ${RET_CODE} -eq 0
	then
		RET_CODE=1
	fi
	
	# Chama funcao de finalizacao
	finish
}

lockon() {
	printm "Verificando arquivo de trava: ${LOCK_FILE}"
	
	# Verifica aquivo de trava
	if [ -e ${LOCK_FILE} ] && kill -0 `cat ${LOCK_FILE}`; then
		printm "Nao foi possivel obter arquivo de trava: ${LOCK_FILE}"
		
		# Retorna erro
		return 1
	fi

	# Arma sinais de interrupcao do processo
	trap abort INT TERM HUP QUIT

	# Grava PID atual no arquivo de log
	# OBS: O arquivo LOCK_FILE deve ser removido se o processo for finalizado com 'kill -9'!
	echo $$ > ${LOCK_FILE}

	# Retorna sucesso
	return 0
}

start() {
	printm "${BASE_NAME} : Inicio do processamento"
	
	# Parametros de configuracao
	printm "Parametros de configuracao: "

	# Informa parametros de configuracao
	cat ${CONFIG_FILE} | cut -d= -f1 | while read cfg
	do
		case "${cfg}" in
			"DB_PASS") 
				printm "[${cfg}=$(eval echo \$${cfg} | sed 's/./*/g')]"
				;;
			*)
				printm "[${cfg}=$(eval echo \$${cfg})]"
				;;
		esac
	done
	
	# Obtendo arquivo de trava do processo
	lockon
	RET_CODE=$?
	
	# Se trava nao pode ser obtida
	if test ${RET_CODE} -ne 0 
	then
		printm "Falha ao obter trava do processo, setando retcode: ${RET_CODE}"

		printm "PID do processo existente: $(cat ${LOCK_FILE})"
		finish
	fi
	
	# retorna status code
	return ${RET_CODE}
}

purge() {
	printm "Executando expurgo em ${DIR_EXPURGO_ATIV}"
	local exp_ativ=0
	find "${DIR_EXPURGO_ATIV}" -mtime +${FILE_EXPURGO_DIAS} -name "*${FILE_EXPURGO_EXT}" -type f | while read file
	do
		exp_ativ=$((exp_ativ + 1))
		printm "Deletando arquivo: ${file}"
		rm -f "${file}"
		RET_CODE=$?
	done 
	printm "Arquivos expurgados: ${exp_ativ}"

	printm "Executando expurgo em ${DIR_EXPURGO_CANC}"
	local exp_canc=0
	find "${DIR_EXPURGO_CANC}" -mtime +${FILE_EXPURGO_DIAS} -name "*${FILE_EXPURGO_EXT}" -type f  | while read file
	do
		exp_canc=$((exp_canc + 1))
		printm "Deletando arquivo: ${file}"
		rm -f "${file}"
		RET_CODE=$?
	done
	printm "Arquivos expurgados: ${exp_canc}"
}


# Inicio do processamento
start

# Limpando
purge

# Fim do processamento
finish

