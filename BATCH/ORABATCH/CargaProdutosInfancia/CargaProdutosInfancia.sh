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

sql_loader() {
	printm "Executando SQL*Loader.."
		
	# Nome do arquivo de entrada com extensao .bad
	FILE_BAD="${DIR_ERR}/${BASE_NAME_DATA}${FILE_SQLLDR_EXT}${FILE_BAD_EXT}"

	# Nome do arquivo de entrada com extensao .dsc
	FILE_DSC="${DIR_ERR}/${BASE_NAME_DATA}${FILE_SQLLDR_EXT}${FILE_DSC_EXT}"
					
	# Nome do arquivo de entrada com extensao .log
	FILE_LOG="${DIR_LOG}/${BASE_NAME_DATA}${FILE_SQLLDR_EXT}${FILE_LOG_EXT}"

	# Executa sql loader
	sqlldr "userid=${SQL_CONN}" "control=${SQLLDR_CONTROL}" "parfile=${SQLLDR_PARFILE}" "data=${FILE_DATA}" "bad=${FILE_BAD}" "discard=${FILE_DSC}" "log=${FILE_LOG}"
	RET_CODE=$?

	# Informa codigo de retorno do SQL Loader
	case "${RET_CODE}" in 
	$SQLLDR_EX_SUCC) printm "SQL*Loader executado com sucesso." ;; 
	$SQLLDR_EX_FAIL) abort "SQL*Loader executado com falha [EX_FAIL]." ;; 
	$SQLLDR_EX_WARN) printm "SQL*Loader executado com critica [EX_WARN]." ;; 
	$SQLLDR_EX_FTL) abort "SQL*Loader executado com erro fatal." ;; 
	*) abort "SQL*Loader: codigo de retorno desconhecido [${RET_CODE}]." ;; 
	esac

	# Informa arquivos gerados pelo SQL Loader se houver
	if test -e "${FILE_LOG}"; then printm "SQL*Loader log file: ${FILE_LOG}"; fi
	if test -e "${FILE_BAD}"; then printm "SQL*Loader bad file (ARQUIVO DE CRITICA/LAYOUT): ${FILE_BAD}"; fi
	if test -e "${FILE_DSC}"; then printm "SQL*Loader discard file (ARQUIVO DE CRITICA/LAYOUT): ${FILE_DSC}"; fi

	return ${RET_CODE}
}

sql_trunc() {		
	printm "Executando SQL*Plus.."

	sqlplus "${SQL_CONN}" <<EOB
SET TERMOUT ON;
SET SQLPROMPT '';
SET FEEDBACK OFF;
SET ECHO OFF;
SET AUTOTRACE OFF;
SET FLUSH ON;
SET HEADING OFF;
SET SERVEROUTPUT ON SIZE 1000000;
SET TIME OFF;
SET TRIMOUT ON;
SET TRIMSPOOL ON;
SET PAUSE OFF;
SET SHOWMODE OFF;
SET SQLBLANKLINES OFF;
SET SQLNUMBER OFF;
SET TAB OFF;
SET VERIFY OFF;
SET WRAP OFF;
SET FEED OFF;
SET NEWPAGE NONE;
SET LINESIZE 32767;
SET TIMING ON;
WHENEVER SQLERROR EXIT SQL.SQLCODE;
WHENEVER OSERROR EXIT FAILURE; 
EXEC LOAD.TRUNC_PLANOSERVICOSUBSEGMENTO();
EXIT 0;
EOB
	
	RET_CODE=$?
	printm "Codigo retorno SQL*Plus: ${RET_CODE}"
	
	# Aborta processamento se houve erro sql
	if (( ${RET_CODE} ))
	then
		abort "sql_trunc: Erro no sqlplus exit=${RET_CODE}"
	fi
	
	return ${RET_CODE}
}

sql_plus() {
	printm "Executando SQL*Plus.."

	# Nome do arquivo de spool do sqlplus
	FILE_LOG="${DIR_LOG}/${BASE_NAME_DATA}${FILE_SQLPLUS_EXT}${FILE_LOG_EXT}"

	# Executa sqlplus
	sqlplus "${SQL_CONN}" "@${SQL_PLUS_FILE}" "${IDUSUARIOALTERACAO}" > "${FILE_LOG}" 2>&1
	RET_CODE=$?
	printm "Codigo retorno SQL*Plus: ${RET_CODE}"

	# Variaveis de retorno do plsql	
	VO_CDERRO=$(cat "${FILE_LOG}" | egrep '^VO_CDERRO' | sed 's/.*VO_CDERRO *//')
	VO_DSERRO=$(cat "${FILE_LOG}" | egrep '^VO_DSERRO' | sed 's/.*VO_DSERRO *//')	
	
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
		
		if test ${VO_CDERRO} -eq 0
		then
			VO_COUNT_MERGE=$(cat "${FILE_LOG}" | egrep '^VO_COUNT_MERGE' | sed 's/.*VO_COUNT_MERGE *//')	
			printm "SQL*Plus: Variavel VO_COUNT_MERGE: '${VO_COUNT_MERGE}'"
		fi

		# Retorna valor de VO_CDERRO no exit
		RET_CODE=${VO_CDERRO}
	fi
	
	return ${RET_CODE}
}

move_prc()
{
	# Nome do arquivo 'processado'
	FILE_PRC="${DIR_PRC}/${BASE_NAME_DATA}${FILE_PRC_EXT}"

	# Move arquivo lido para arquivo processado
	printm "Movendo ${FILE_DATA} para ${FILE_PRC}"
	mv "${FILE_DATA}" "${FILE_PRC}" 
}

move_err()
{
	# Caminho do arquivo err
	FILE_ERR="${DIR_ERR}/${BASE_NAME_DATA}${FILE_ERR_EXT}"

	# Move arquivo criticado para pasta err
	printm "Movendo ${FILE_DATA} para ${FILE_ERR}"
	mv "${FILE_DATA}" "${FILE_ERR}" 
}

# Inicio do processamento
start

# Buscando arquivos para leitura
printm "Buscando arquivos para processamento em: ${DIR_DATA} "

# Arquivos de carga
set -A ARQUIVOSCARGA $(ls -1tr ${DIR_DATA}/${FILE_PREFIX}*${FILE_DATA_EXT} 2>/dev/null)
printm "Arquivos encontrados: ${#ARQUIVOSCARGA[*]}"
for file in ${ARQUIVOSCARGA[*]}; do printm "[${file}]"; done

# Loader dos arquivos
if (( ${#ARQUIVOSCARGA[*]} ))
then
	# Carga dos arquivos
	printm "Iniciando processo de carga dos arquivos:"

	# Carrega os arquivos com o sql loader
	for FILE_DATA in ${ARQUIVOSCARGA[*]}
	do 
		# Nome do arquivo encontrado
		BASE_NAME_DATA=$(basename $FILE_DATA|cut -d. -f1)

		# Se o arquivo contem dados
		if test -s "${FILE_DATA}"
		then		
			# Limpando tabela de load
			printm "Limpando tabela de carga.."
			sql_trunc
	
			# Chama SQL Loader para realizar a carga
			printm "Carregando arquivo: ${FILE_DATA}"
			sql_loader
	
			# Executando plsql para processar os dados carregados
			printm "Processando arquivo: ${FILE_DATA}"
			sql_plus
	
			# Move arquivo processado
			move_prc
		else
			# Arquivo vazio para pasta err
			printm "Descartando arquivo vazio: ${FILE_DATA}"
			move_err
		fi
	done
else
	# Nao ha arquivos para carregar
	printm "Nao ha arquivos para carregar em ${DIR_DATA}"
fi

# Fim do processamento
finish
