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
set -A SQL_TRUNC_FILE
SQL_TRUNC_FILE[1]=${DIR_SQL}/${BASE_NAME}.trunc_cliente.sql
SQL_TRUNC_FILE[2]=${DIR_SQL}/${BASE_NAME}.trunc_protocolo.sql
SQL_TRUNC_FILE[3]=${DIR_SQL}/${BASE_NAME}.trunc_atendimento.sql
set -A SQL_PLUS_FILE
SQL_PLUS_FILE[1]=${DIR_SQL}/${BASE_NAME}.cliente.sql
SQL_PLUS_FILE[2]=${DIR_SQL}/${BASE_NAME}.protocolo.sql
SQL_PLUS_FILE[3]=${DIR_SQL}/${BASE_NAME}.atendimento.sql

# Variaveis sql loader
set -A SQLLDR_CONTROL
SQLLDR_CONTROL[1]=${DIR_SQL}/${BASE_NAME}.cliente.ctl
SQLLDR_CONTROL[2]=${DIR_SQL}/${BASE_NAME}.protocolo.ctl
SQLLDR_CONTROL[3]=${DIR_SQL}/${BASE_NAME}.atendimento.ctl
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

sql_trunc() {
	local trunc_file

	case "$1" in
		cliente) trunc_file=${SQL_TRUNC_FILE[1]} ;;
		protocolo) trunc_file=${SQL_TRUNC_FILE[2]} ;;
		atendimento) trunc_file=${SQL_TRUNC_FILE[3]} ;;
		*) abort 'sql_trunc: argumento invalido' ;;
	esac
						
	printm "Executando SQL*Plus.."

	sqlplus "${SQL_CONN}" "@${trunc_file}"
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
	local sqlldr_file

	case "$1" in
		cliente) sqlldr_file=${SQLLDR_CONTROL[1]} ;;
		protocolo) sqlldr_file=${SQLLDR_CONTROL[2]} ;;
		atendimento) sqlldr_file=${SQLLDR_CONTROL[3]} ;;
		*) abort 'sql_loader: argumento invalido' ;;
	esac
	
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
	sqlldr "userid=${SQL_CONN}" "control=${sqlldr_file}" "parfile=${SQLLDR_PARFILE}" "data=${FILE_DATA}" "bad=${FILE_BAD}" "discard=${FILE_DSC}" "log=${FILE_LOG}"
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

sql_plus() {
	local sql_file

	case "$1" in
		cliente) sql_file=${SQL_PLUS_FILE[1]} ;;
		protocolo) sql_file=${SQL_PLUS_FILE[2]} ;;
		atendimento) sql_file=${SQL_PLUS_FILE[3]} ;;
		*) abort 'sql_plus: argumento invalido' ;;
	esac
	
	printm "Executando SQL*Plus.."

	# Nome do arquivo de spool do sqlplus
	local FILE_SPOOL="${DIR_LOG}/${BASE_NAME}-${DATE_TIME}.${1}${FILE_SQLPLUS_EXT}${FILE_LOG_EXT}"
	
	# Variaveis de retorno do plsql
	VO_CDERRO=
	VO_DSERRO=
	
	local VI_COUNT_TOTAL=0
	if test -n "$2"
	then
		VI_COUNT_TOTAL=$2
	fi

	sqlplus "${SQL_CONN}" "@${sql_file}" "${FILE_SPOOL}" "${DB_PARAM_COMMIT}" "${DB_PARAM_DATE_MASK}" "${DB_PARAM_DATE_LIMIT}" "${FIELD_DELIMITER}" "${ID_USUARIO_ALTERACAO}" "${VI_COUNT_TOTAL}"
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
		
		RET_CODE=${retcode}
		abort "sql_plus: Abortando devido a possivel erro na execucao do PLSQL.."
	else
		# Informa conteudo das variaveis de retorno
		printm "SQL*Plus: Variavel VO_CDERRO: '${VO_CDERRO}'"
		printm "SQL*Plus: Variavel VO_DSERRO: '${VO_DSERRO}'"
		
		# Retorna valor de VO_CDERRO no exit
		RET_CODE=${VO_CDERRO}
		
		# Verifica se contagem de registros pendentes ocorreu com sucesso
		if test ${VO_CDERRO} -ne 0 -a "${VO_DSERRO}" != 'SUCESSO'
		then
			 # Se CDERRO 98 atingiu horario limite de execucao, caso contrario erro sql
			if test ${VO_CDERRO} -eq 98
			then
				printm "Os registros pendentes serao processados na proxima execucao dentro do horario permitido"
				finish
			else
				abort "sql_plus: Abortando devido a possivel erro na execucao do PLSQL.."
			fi	
		fi
	fi
	
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
	
sql_pending() {
	printm "Executando SQL*Plus.."

	# Nome do arquivo de spool do sqlplus
	local FILE_SPOOL="${DIR_LOG}/${BASE_NAME}-${DATE_TIME}.count${FILE_SQLPLUS_EXT}${FILE_LOG_EXT}"

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
VARIABLE VO_COUNT_CLIENTE NUMBER;
VARIABLE VO_COUNT_PROTOCOLO NUMBER;
VARIABLE VO_COUNT_ATENDIMENTO NUMBER;
DECLARE
	V_HORARIO_LIMITE_EXEC DATE;
BEGIN
	SELECT /*+ parallel(4) */ TO_DATE(DSVALORPARAMETRO, '${DB_PARAM_DATE_MASK}')
	INTO V_HORARIO_LIMITE_EXEC
	FROM APOIO.PARAMETRO 
	WHERE CDPARAMETRO = '${DB_PARAM_DATE_LIMIT}';
	IF TO_CHAR(SYSDATE, 'HH24MI') >= TO_CHAR(V_HORARIO_LIMITE_EXEC, 'HH24MI')
	THEN
		:VO_CDERRO := 98;
		:VO_DSERRO := 'Atingido horario limite de execucao: '||TO_CHAR(V_HORARIO_LIMITE_EXEC, '${DB_PARAM_DATE_MASK}');
		LOAD.LOG_CARGAPROTOCOLOSTV(:VO_DSERRO);
	ELSE
		select /*+ parallel(6) */ count(1) into :VO_COUNT_CLIENTE from LOAD.CARGACLIENTETV where status = 0;
		select /*+ parallel(6) */ count(1) into :VO_COUNT_PROTOCOLO from LOAD.CARGAPROTOCOLOTV where status = 0;
		select /*+ parallel(6) */ count(1) into :VO_COUNT_ATENDIMENTO from LOAD.CARGAATENDIMENTOTV where status = 0;

		:VO_CDERRO := 0;
		:VO_DSERRO := 'SUCESSO';
		LOAD.LOG_CARGAPROTOCOLOSTV('Registros pendentens em CARGACLIENTETV: '||:VO_COUNT_CLIENTE);
		LOAD.LOG_CARGAPROTOCOLOSTV('Registros pendentens em CARGAPROTOCOLOTV: '||:VO_COUNT_PROTOCOLO);
		LOAD.LOG_CARGAPROTOCOLOSTV('Registros pendentens em CARGAATENDIMENTOTV: '||:VO_COUNT_ATENDIMENTO);		
	END IF;
EXCEPTION
	WHEN OTHERS THEN
		:VO_CDERRO := 99;
		:VO_DSERRO := 'ERRO NR.: '||SQLCODE||' - DESCRICAO DO ERRO: '||SQLERRM;
END;
/
SET TIMING OFF;
select CHR(10) from dual;
select 'VO_CDERRO', :VO_CDERRO from dual;
select 'VO_DSERRO', :VO_DSERRO from dual;
select 'VO_COUNT_CLIENTE', :VO_COUNT_CLIENTE from dual;
select 'VO_COUNT_PROTOCOLO', :VO_COUNT_PROTOCOLO from dual;
select 'VO_COUNT_ATENDIMENTO', :VO_COUNT_ATENDIMENTO from dual;
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
			 # Se CDERRO 98 atingiu horario limite de execucao, caso contrario erro sql
			if test ${VO_CDERRO} -eq 98
			then
				printm "Os registros pendentes serao processados na proxima execucao dentro do horario permitido"
				finish
			else
				abort "sql_plus: Abortando devido a possivel erro na execucao do PLSQL.."
			fi	
		fi
		
		# Obtem valor das variaveis de count
		local VO_COUNT_CLIENTE=$(cat "${FILE_SPOOL}" | egrep '^VO_COUNT_CLIENTE' | sed 's/.*VO_COUNT_CLIENTE *//')
		local VO_COUNT_PROTOCOLO=$(cat "${FILE_SPOOL}" | egrep '^VO_COUNT_PROTOCOLO' | sed 's/.*VO_COUNT_PROTOCOLO *//')
		local VO_COUNT_ATENDIMENTO=$(cat "${FILE_SPOOL}" | egrep '^VO_COUNT_ATENDIMENTO' | sed 's/.*VO_COUNT_ATENDIMENTO *//')	
		
		# Informa quantidade pendente
		printm "Registros pendentens em CARGACLIENTETV: ${VO_COUNT_CLIENTE}"
		printm "Registros pendentens em CARGAPROTOCOLOTV: ${VO_COUNT_PROTOCOLO}"
		printm "Registros pendentens em CARGAATENDIMENTOTV: ${VO_COUNT_ATENDIMENTO}"
		
		# Executando plsql para processar os registros pendentes de cliente
		if (( ${VO_COUNT_CLIENTE} ))
		then
			printm "Processando registros pendentes em CARGACLIENTETV.."
			sql_plus 'cliente' "${VO_COUNT_CLIENTE}"
		fi
		
		# Executando plsql para processar os registros pendentes de protocolo
		if (( ${VO_COUNT_PROTOCOLO} ))
		then
			printm "Processando registros pendentes em CARGAPROTOCOLOTV.."
			sql_plus 'protocolo' "${VO_COUNT_PROTOCOLO}"
		fi
		
		# Executando plsql para processar os registros pendentes de atendimento
		if (( ${VO_COUNT_ATENDIMENTO} ))
		then
			printm "Processando registros pendentes em CARGAATENDIMENTOTV.."
			sql_plus 'atendimento' "${VO_COUNT_ATENDIMENTO}"
		fi

		# Atualiza data do processo se houve processamento de registros pendentes
		if test ${VO_COUNT_CLIENTE} -gt 0 -o ${VO_COUNT_PROTOCOLO} -gt 0 -o ${VO_COUNT_ATENDIMENTO} -gt 0
		then
			DATE_TIME=$(date ${DATE_MASK})
			printm "Atualizando data do processo: ${DATE_TIME}"
		fi
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
printm "Verificando se ha registros pendentes de processamento.."
sql_pending

# Buscando arquivos para leitura
printm "Buscando arquivos para processamento em: ${DIR_DATA_IN} "

# Arquivos de cliente
set -A CARGACLIENTETV $(ls -1tr ${DIR_DATA_IN}/${FILE_CLIENTE_PREFIX}*${FILE_DATA_EXT} 2>/dev/null)
printm "Arquivos de cliente encontrados: ${#CARGACLIENTETV[*]}"
for file in ${CARGACLIENTETV[*]}; do printm "[${file}]"; done

# Arquivos de protocolo
set -A CARGAPROTOCOLOTV $(ls -1tr ${DIR_DATA_IN}/${FILE_PROTOCOLO_PREFIX}*${FILE_DATA_EXT} 2>/dev/null)
printm "Arquivos de protocolo encontrados: ${#CARGAPROTOCOLOTV[*]}"
for file in ${CARGAPROTOCOLOTV[*]}; do printm "[${file}]"; done

# Arquivos de atendimento
set -A CARGAATENDIMENTOTV $(ls -1tr ${DIR_DATA_IN}/${FILE_ATENDIMENTO_PREFIX}*${FILE_DATA_EXT} 2>/dev/null)
printm "Arquivos de atendimento encontrados: ${#CARGAATENDIMENTOTV[*]}"
for file in ${CARGAATENDIMENTOTV[*]}; do printm "[${file}]"; done

# Verifica se há arquivos para carga
if [[ ${#CARGACLIENTETV[*]} -eq 0 && ${#CARGAPROTOCOLOTV[*]} -eq 0 && ${#CARGAATENDIMENTOTV[*]} -eq 0 ]]
then
	# Nao ha arquivos para carregar
	printm "Nao ha arquivos para carregar.."
	
	# Fim do processamento
	finish
fi

# Carga dos arquivos
printm "Iniciando processo de carga dos arquivos:"

# Loader dos arquivos de cliente
if (( ${#CARGACLIENTETV[*]} ))
then
	# Limpando tabela de load
	printm "Limpando tabela CARGACLIENTETV.."
	sql_trunc 'cliente'
	
	# Carrega os arquivos com o sql loader
	for FILE_DATA in ${CARGACLIENTETV[*]}
	do 
		# Chama SQL Loader para realizar a carga
		printm "Carregando arquivo: ${FILE_DATA}"
		sql_loader 'cliente'
		move_prc
	done
	
	# Executando plsql para processar os dados carregados
	printm "Processando dados carregados em CARGACLIENTETV.."
	sql_plus 'cliente'
fi

# Loader dos arquivos de protocolo
if (( ${#CARGAPROTOCOLOTV[*]} ))
then
	# Limpando tabela de load
	printm "Limpando tabela CARGAPROTOCOLOTV.."
	sql_trunc 'protocolo'
	
	# Carrega os arquivos com o sql loader
	for FILE_DATA in ${CARGAPROTOCOLOTV[*]}
	do 
		# Chama SQL Loader para realizar a carga
		printm "Carregando arquivo: ${FILE_DATA}"
		sql_loader 'protocolo'
		move_prc
	done
	
	# Executando plsql para processar os dados carregados
	printm "Processando dados carregados em CARGAPROTOCOLOTV.."
	sql_plus 'protocolo'
fi

# Loader dos arquivos de atendimento
if (( ${#CARGAATENDIMENTOTV[*]} ))
then
	# Limpando tabela de load
	printm "Limpando tabela CARGAATENDIMENTOTV.."
	sql_trunc 'atendimento'
	
	# Carrega os arquivos com o sql loader
	for FILE_DATA in ${CARGAATENDIMENTOTV[*]}
	do 
		# Chama SQL Loader para realizar a carga
		printm "Carregando arquivo: ${FILE_DATA}"
		sql_loader 'atendimento'
		move_prc
	done
	
	# Executando plsql para processar os dados carregados
	printm "Processando dados carregados em CARGAATENDIMENTOTV.."
	sql_plus 'atendimento'
fi

# Fim do processamento
finish
