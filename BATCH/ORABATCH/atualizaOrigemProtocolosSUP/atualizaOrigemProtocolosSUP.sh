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
	RET_CODE=1
	
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

sql_plus() {
	printm "Executando SQL*Plus.."

	# Nome do arquivo de spool do sqlplus
	local FILE_SPOOL="${DIR_LOG}/${BASE_NAME}-${DATE_TIME}${FILE_SQLPLUS_EXT}${FILE_LOG_EXT}"

	# Variaveis de retorno do plsql
	VO_CDERRO=
	VO_DSERRO=

	sqlplus "${SQL_CONN}" <<EOB >>${FILE_SPOOL} 2>&1
SET SQLPROMPT '';
SET TERMOUT OFF;
SET FEEDBACK OFF;
SET ECHO OFF;
SET AUTOTRACE OFF;
SET HEADING OFF;
SET SERVEROUTPUT OFF;
SET TIME OFF;
SET TIMING ON;
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
SET LINESIZE 2048;
SET FLUSH ON;
WHENEVER SQLERROR EXIT SQL.SQLCODE;
WHENEVER OSERROR EXIT FAILURE;
VARIABLE VO_CDERRO NUMBER;
VARIABLE VO_DSERRO VARCHAR2(512);
VARIABLE VO_COUNT_ATUALIZADOS NUMBER;
BEGIN
	UPDATE atendimento.atendimentoprotocolomigracao SET CDORIGEM = 'FIXO' WHERE idsistemaorigem = 85;
	:VO_COUNT_ATUALIZADOS := sql%rowcount;
	COMMIT;
	:VO_CDERRO := 0;
	:VO_DSERRO := 'SUCESSO';
EXCEPTION
	WHEN OTHERS THEN
		:VO_CDERRO := 99;
		:VO_DSERRO := 'ERRO NR.: '||SQLCODE||' - DESCRICAO DO ERRO: '||SQLERRM;
		ROLLBACK;
END;
/
SET TIMING OFF;
select CHR(10) from dual;
select 'VO_CDERRO', :VO_CDERRO from dual;
select 'VO_DSERRO', :VO_DSERRO from dual;
select 'VO_COUNT_ATUALIZADOS', :VO_COUNT_ATUALIZADOS from dual;
EXIT :VO_CDERRO;
EOB
	
	local retcode=$?
	printm "Codigo retorno SQL*Plus: $retcode"

	VO_CDERRO=$(cat "${FILE_SPOOL}" | egrep '^VO_CDERRO' | sed 's/.*VO_CDERRO *//')
	VO_DSERRO=$(cat "${FILE_SPOOL}" | egrep '^VO_DSERRO' | sed 's/.*VO_DSERRO *//')	
			
	# Verifica se variaveis de erro estao preenchidas
	if test -z "${VO_CDERRO}" -a -z "${VO_DSERRO}"
	then
		# Informa conteudo das variaveis de retorno
		printm "SQL*Plus: Variavel VO_CDERRO vazia, ERRO no SQLPLUS ?"
		printm "SQL*Plus: Variavel VO_DSERRO vazia, ERRO no SQLPLUS ?"
		abort 'sql_pending: Abortando devido a possivel erro na execucao do PLSQL..'
	else
		# Informa conteudo das variaveis de retorno
		printm "SQL*Plus: Variavel VO_CDERRO: '${VO_CDERRO}'"
		printm "SQL*Plus: Variavel VO_DSERRO: '${VO_DSERRO}'"
		
		# Retorna valor de VO_CDERRO no exit
		RET_CODE=${VO_CDERRO}
		
		# Verifica se contagem de registros pendentes ocorreu com sucesso
		if test ${VO_CDERRO} -ne 0 -a "${VO_DSERRO}" != 'SUCESSO'
		then
			abort "sql_plus: Abortando devido a possivel erro na execucao do PLSQL.."
		fi
		
		# Obtem valor das variaveis de count
		local VO_COUNT_ATUALIZADOS=$(cat "${FILE_SPOOL}" | egrep '^VO_COUNT_ATUALIZADOS' | sed 's/.*VO_COUNT_ATUALIZADOS *//')
		
		# Informa quantidade pendente
		printm "Registros atualizados em ATENDIMENTOPROTOCOLOMIGRACAO: ${VO_COUNT_ATUALIZADOS}"
	fi

	return ${retcode}
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

# Verifica se há registros pendentes de processamento
printm "Iniciando processamento.."
sql_plus

# Fim do processamento
finish
