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
export NLS_LANG
export NLS_DATE_FORMAT
export NLS_TIMESTAMP_FORMAT

# Data do processo
DATE_TIME=$(date ${DATE_MASK})

# Arquivo de log
LOG_FILE="${DIR_LOG}/${BASE_NAME}-${DATE_TIME}${FILE_LOG_EXT}"

# Variaveis SQL
SQL_CONN=${DB_USER}/${DB_PASS}@${DB_NAME}
SQL_PLUS_FILE=${DIR_SQL}/${BASE_NAME}.sql

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
	if [ -e ${LOCK_FILE} ] && kill -0 `cat ${LOCK_FILE}` > /dev/null 2>&1 ; then
		printm "Nao foi possivel obter arquivo de trava: ${LOCK_FILE}"
		
		# Retorna erro
		return 1
	fi

	# Arma sinais de interrupcao do processo
	trap abort INT TERM HUP QUIT

	# Grava PID atual no arquivo de log
	echo $$ > ${LOCK_FILE}

	# Retorna sucesso
	return 0
}

start() {
	printm "${BASE_NAME} : Inicio do processamento"
	
	# Parametros de configuracao
	printm "Parametros de configuracao:"

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
	if [ ${RET_CODE} -ne 0 ] 
	then
		RET_CODE=1
		printm "Falha ao obter trava do processo, setando retcode: ${RET_CODE}"

		printm "PID do processo existente: $(cat ${LOCK_FILE})"
		finish
	fi
	
	# retorna status code
	return ${RET_CODE}
}

sql_plus() {
	printm "Executando SQL*Plus.."

	# Nome do arquivo de spool do sqlplus
	FILE_SQL_LOG="$DIR_LOG/$BASE_NAME-$DATE_TIME$FILE_SQLPLUS_EXT"

	# Executa sqlplus
	sqlplus "${SQL_CONN}" "@${SQL_PLUS_FILE}" "$IDUSUARIOALTERACAO" "$PARAM_COMMIT" > "${FILE_SQL_LOG}" 2>&1
	RET_CODE=$?
	printm "Codigo retorno SQL*Plus: ${RET_CODE}"

	# Variaveis de retorno do plsql	
	VO_CDERRO=$(cat "${FILE_SQL_LOG}" | egrep '^VO_CDERRO' | sed 's/.*VO_CDERRO *//')
	VO_DSERRO=$(cat "${FILE_SQL_LOG}" | egrep '^VO_DSERRO' | sed 's/.*VO_DSERRO *//')
	VO_COUNT=$(cat "${FILE_SQL_LOG}" | egrep '^VO_COUNT' | sed 's/.*VO_COUNT *//')	
	
	# Verifica se variaveis de erro estao preenchidas
	if test -z "${VO_CDERRO}" -a -z "${VO_DSERRO}"
	then
		# Informa conteudo das variaveis de retorno
		printm "SQL*Plus: Variavel VO_CDERRO vazia, ERRO no SQLPLUS ?"
		printm "SQL*Plus: Variavel VO_DSERRO vazia, ERRO no SQLPLUS ?"

		abort "sql_plus: Abortando devido a possivel erro na execucao do PLSQL.."
	else
		# Informa conteudo das variaveis de retorno
		printm "SQL*Plus: Variavel VO_CDERRO: '${VO_CDERRO}'"
		printm "SQL*Plus: Variavel VO_DSERRO: '${VO_DSERRO}'"
		printm "SQL*Plus: Variavel VO_COUNT_DELETADOS: '${VO_COUNT}'"
		
		# Retorna valor de VO_CDERRO no exit
		RET_CODE=${VO_CDERRO}
	fi
	
	return ${RET_CODE}
}

# Inicio do processamento
start

# Processando
sql_plus

# Fim do processamento
finish
