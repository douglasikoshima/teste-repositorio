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
SQL_TRUNC_FILE=${DIR_SQL}/${BASE_NAME}.trunc.sql

# Variaveis sql loader
SQLLDR_CONTROL=${DIR_SQL}/${BASE_NAME}.ctl
SQLLDR_PARFILE=${DIR_SQL}/${BASE_NAME}.par

# SQL Loader return codes
SQLLDR_EX_SUCC=0
SQLLDR_EX_FAIL=1
SQLLDR_EX_WARN=2
SQLLDR_EX_FTL=3

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
	echo $$ > ${LOCK_FILE}

	# Retorna sucesso
	return 0
}

start() {
	printm "${BASE_NAME} : Inicio do processamento"
	
	# dos2unix
	find ${HOME}/deploy/processos/ORABATCH/cargaDocumentoWhiteList/data -type f -name '*.[Tt][Xx][Tt]' -exec perl -i -pe 's/\r\n/\n/g' {} \;

	# remove linhas em branco
	find ${HOME}/deploy/processos/ORABATCH/cargaDocumentoWhiteList/data -type f -name '*.[Tt][Xx][Tt]' -exec perl -i -pe 's/^[\t\s\r]*$//g' {} \;
	
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
	
	# retorna status code
	return $?
}

sql_trunc() {
	printm "Executando SQL*Plus.."

	sqlplus "${SQL_CONN}" "@${SQL_TRUNC_FILE}"
	local retcode=$?
	printm "Codigo retorno SQL*Plus: $retcode"
	
	# Aborta processamento se houve erro sql
	if (( $retcode ))
	then
		RET_CODE=${retcode}
		abort 'sql_trunc: Erro no sqlplus exit=${retcode}'
	fi
	
	return $retcode
}

sql_loader() {
	printm "Executando SQL*Loader.."
		
	# Nome do arquivo encontrado
	BASE_NAME_DATA=$(basename $FILE_DATA|cut -d. -f1)
		
	# Nome do arquivo de entrada com extensao .bad
	FILE_BAD="${DIR_BAD}/${BASE_NAME_DATA}${FILE_SQLLDR_EXT}${FILE_BAD_EXT}"

	# Nome do arquivo de entrada com extensao .dsc
	FILE_DSC="${DIR_BAD}/${BASE_NAME_DATA}${FILE_SQLLDR_EXT}${FILE_DSC_EXT}"
					
	# Nome do arquivo de entrada com extensao .log
	FILE_LOG="${DIR_LOG}/${BASE_NAME_DATA}${FILE_SQLLDR_EXT}${FILE_LOG_EXT}"

	# Executa sql loader
	sqlldr "userid=${SQL_CONN}" "control=${SQLLDR_CONTROL}" "parfile=${SQLLDR_PARFILE}" "data=${FILE_DATA}" "bad=${FILE_BAD}" "discard=${FILE_DSC}" "log=${FILE_LOG}"
	local retcode=$?

	# Informa codigo de retorno do SQL Loader
	case "$retcode" in 
	$SQLLDR_EX_SUCC) printm "SQL*Loader executado com sucesso." ;; 
	$SQLLDR_EX_FAIL) RET_CODE=$retcode; abort "SQL*Loader executado com falha [EX_FAIL]." ;; 
	$SQLLDR_EX_WARN) printm "SQL*Loader executado com critica [EX_WARN]." ;; 
	$SQLLDR_EX_FTL) RET_CODE=$retcode; abort "SQL*Loader executado com erro fatal." ;; 
	*) RET_CODE=$retcode; abort "SQL*Loader: codigo de retorno desconhecido [${retcode}]." ;; 
	esac

	# Informa arquivos gerados pelo SQL Loader se houver
	if test -e "${FILE_LOG}"; then printm "SQL*Loader log file: ${FILE_LOG}"; fi
	if test -e "${FILE_BAD}"; then printm "SQL*Loader bad file: ${FILE_BAD}"; fi
	if test -e "${FILE_DSC}"; then printm "SQL*Loader discard file: ${FILE_DSC}"; fi

	return ${retcode}
}

move_prc()
{
	# Nome do arquivo 'processado'
	FILE_PRC="${DIR_PRC}/${BASE_NAME_DATA}${FILE_PRC_EXT}"

	# Move arquivo lido para arquivo processado
	printm "Movendo ${FILE_DATA} para ${FILE_PRC}"
	mv "${FILE_DATA}" "${FILE_PRC}" 
}

# Inicio do processamento
start

# Se trava nao pode ser obtida
if [ $? -ne 0 ] 
then
	RET_CODE=1
	printm "Falha ao obter trava do processo, setando retcode: ${RET_CODE}"
	
	printm "PID do processo existente: $(cat ${LOCK_FILE})"
	finish
fi

# Buscando arquivos para leitura
printm "Buscando arquivos para processamento em: ${DIR_DATA_IN} "

# Arquivos de cliente
set -A CARGA $(ls -1tr ${DIR_DATA_IN}/${FILE_PREFIX}*${FILE_DATA_EXT} 2>/dev/null)
printm "Arquivos encontrados: ${#CARGA[*]}"
for file in ${CARGA[*]}; do printm "[${file}]"; done

# Verifica se há arquivos para carga
if test ${#CARGA[*]} -eq 0
then
	# Nao ha arquivos para carregar
	printm "Nao ha arquivos para carregar.."
	
	# Fim do processamento
	finish
fi

# Carga dos arquivos
printm "Iniciando processo de carga dos arquivos:"

# Loader dos arquivoS
if (( ${#CARGA[*]} ))
then
	# Limpando tabela
	if test ${DB_TRUNC} -eq 1
	then
		printm "Limpando tabela.."
		sql_trunc
	fi
	
	# Carrega os arquivos com o sql loader
	for FILE_DATA in ${CARGA[*]}
	do 
		# Chama SQL Loader para realizar a carga
		printm "Carregando arquivo: ${FILE_DATA}"
		sql_loader
		move_prc
	done
fi

# Fim do processamento
finish
