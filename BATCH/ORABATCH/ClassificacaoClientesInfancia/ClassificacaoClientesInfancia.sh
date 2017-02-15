#!/bin/bash

# Input File Separator $'\n'
IFS='
'

# Diretorio do script
DIR_NAME=$(dirname $0)

# Entra no diretorio do script
cd $DIR_NAME

# Nome do script
BASE_NAME=$(basename $0|cut -d. -f1)
datainicial=`date +%s`

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
SQL_PLUS_FILE_CONSULTA=${DIR_SQL}/ClassificacaoClienteInfanciaConsultaultimaData.sql

# Variavel com codigo de retorno
RET_CODE=0

# Redireciona stdout e stderr para arquivo de log
exec 1>> ${LOG_FILE}
exec 2>> ${LOG_FILE}

# Arquivo de trava
if ! test "${LOCK_FILE}"
then
	printm "Arquivo de trava"
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
	printm "Inicio abort"
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
	printm "Fim abort"
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

	sqlplus "${SQL_CONN}" "@${SQL_PLUS_FILE_CONSULTA}" > "${FILE_SQL_LOG}" 2>&1
	RET_CODE=$?	
	VO_QUANTIDADE_PARTICOES=$(cat "${FILE_SQL_LOG}" | egrep '^VO_QUANTIDADE_PARTICOES' | sed 's/.*VO_QUANTIDADE_PARTICOES *//')
	contPart=1	
	
	rm -f ERRO.txt 2>> /dev/null
	
    while [ $contPart -le $VO_QUANTIDADE_PARTICOES ]
	do
	
		nmLog=${FILE_SQL_LOG}_P$contPart
		# Executa sqlplus
		sqlplus ${SQL_CONN} @${SQL_PLUS_FILE} $IDUSUARIOALTERACAO $PARAM_COMMIT P$contPart > ${FILE_SQL_LOG}_P$contPart || echo 'PART - $contPart: $?' >> ERRO.txt & 		
		
		contPart=$((contPart+1))		
		sleep 10

	done
	
	pids=`jobs -l | awk '{print $2}'`
    wait $pids
	
	contPart=1
	erros=0
	while [ $contPart -le $VO_QUANTIDADE_PARTICOES ]
	do
		
		arq_log=${FILE_SQL_LOG}_P$contPart
		VO_CDERRO=$(cat "${arq_log}" | egrep '^VO_CDERRO' | sed 's/.*VO_CDERRO *//')
		VO_DSERRO=$(cat "${arq_log}" | egrep '^VO_DSERRO' | sed 's/.*VO_DSERRO *//')	
		
		if [ ${VO_CDERRO} -ne 0 ] 
		then		
			erros=$((erros+1))			
		fi
		
		printm "Particao: P$contPart VO_CDERRO: $VO_CDERRO VO_DSERRO: $VO_DSERRO"		
		contPart=$((contPart+1))
	done
		
	datafinal=`date +%s`	
	soma=`expr $datafinal - $datainicial` 
   # printm "Fim do processo, tempo gasto:  $(($soma / 60)):$(($soma / 60)):$(($soma % 60))"
		
	return ${erros}
}

# Inicio do processamento
start

# Processando
sql_plus

# Fim do processamento
finish
