#!/bin/sh -

# Data do processo
DTMASK=+%Y%m%d-%H%M%S
DATETIME=$(date $DTMASK)

# Input File Separator $'\n'
IFS='
'

# Nome do script
BASENAME=$(basename $0|cut -d. -f1)

# Diretorio do script
DIRNAME=$(dirname $0)

# Entra no diretorio do script
cd $DIRNAME

# Extensoes de arquivos
FILE_DATA_EXT=.txt
FILE_PRC_EXT=.prc
FILE_ERR_EXT=.bad
FILE_DSC_EXT=.dis
FILE_LOG_EXT=.log

# Carrega configuracao
. ./$BASENAME.cfg

# Arquivo de log
LOGFILE=$DIR_LOG/$BASENAME-$DATETIME$FILE_LOG_EXT

# Arquivo de controle do SQL Loader
SQLLDR_CONTROL=$BASENAME.ctl

# Arquivo de parametros do SQL Loader
SQLLDR_PARFILE=$BASENAME.par

# SQL Loader return codes
EX_SUCC=0
EX_FAIL=1
EX_WARN=2
EX_FTL=3

exec 1>> ${LOGFILE}
exec 2>> ${LOGFILE}

# Funcao p/ truncate no sqlplus
trunc() 
{
	echo "$(date $DTMASK) -- Executando SQLPLUS..."
	
	sqlplus "$DB_USER/$DB_PASS@$DB_NAME" <<endl
SET SERVEROUTPUT ON
SET TIMING ON
EXEC LOAD.TRUNC_CARGAGESTORES
exit
endl

	retcode=`echo $?`
	echo "$(date $DTMASK) -- Codigo retorno SQLPLUS: $retcode"
	return $retcode
}

# Funcao p/ procedure de atualizacao
atualiza()
{
	VO_CDERRO=
	VO_DSERRO=
	echo "$(date $DTMASK) -- Executando SQLPLUS..."

	sqlplus "$DB_USER/$DB_PASS@$DB_NAME" @$BASENAME.sql "$1" "$2"
	retcode=`echo $?`

	VO_CDERRO=$(cat "$1" | egrep '^ *VO_CDERRO' | sed 's/^ *VO_CDERRO *//')
	VO_DSERRO=$(cat "$1" | egrep '^ *VO_DSERRO' | sed 's/^ *VO_DSERRO *//')
	
	echo "$(date $DTMASK) -- Codigo retorno SQLPLUS: $retcode"
	return $retcode
}

# Inicio
echo "$(date $DTMASK) >>> $BASENAME"
echo "$(date $DTMASK) -- DB_USER=$DB_USER"
echo "$(date $DTMASK) -- DB_NAME=$DB_NAME"
echo "$(date $DTMASK) -- DIR_DATA=$DIR_DATA"
echo "$(date $DTMASK) -- DIR_PRC=$DIR_PRC"
echo "$(date $DTMASK) -- DIR_ERR=$DIR_ERR"
echo "$(date $DTMASK) -- DIR_LOG=$DIR_LOG"
echo "$(date $DTMASK) -- LOCK_FILE=$LOCK_FILE"
echo "$(date $DTMASK) -- PARAM_COMMIT=$PARAM_COMMIT"

# Arquivo p/ trava do processo
#LOCK_FILE="/tmp/$BASENAME.lock"
echo "$(date $DTMASK) -- Verificando arquivo de trava $LOCK_FILE."
if [ -e ${LOCK_FILE} ] && kill -0 `cat ${LOCK_FILE}`; then
	echo "$(date $DTMASK) -- Nao foi possivel obter $LOCK_FILE!"
	echo "$(date $DTMASK) <<< $BASENAME"
	exit 1 
fi

# Remove LOCK_FILE quando processo encerrado 
# OBS: O arquivo LOCK_FILE deve ser removido se o processo for finalizado com 'kill -9'!
trap "rm -f ${LOCK_FILE}; exit" INT TERM EXIT
echo $$ > ${LOCK_FILE}

# Busca arquivo para leitura
echo "$(date $DTMASK) -- Buscando arquivos: $DIR_DATA/*$FILE_DATA_EXT"
retcode=
for file_data in $(ls -1tr $DIR_DATA/*$FILE_DATA_EXT 2>/dev/null)
do
	# Nome do arquivo encontrado
	BASENAME_DATA=$(basename $file_data|cut -d. -f1)

	# Carregar apenas o arquivo mais atual
	if [ -z "$retcode" ]
	then
		echo "$(date $DTMASK) -- Processando arquivo $file_data"

		# Chama funcao que executa o TRUNCATE
		echo "$(date $DTMASK) -- Chamando procedure de truncate..."
		trunc

		# Arquivo de log do SQL Loader
		LOGFILE_SQLLDR="$DIR_LOG/$BASENAME_DATA-$DATETIME.sqlldr$FILE_LOG_EXT"

		# Execta SQL Loader
		echo "$(date $DTMASK) -- Executando SQL*Loader..."
		sqlldr userid=\'$DB_USER/$DB_PASS@$DB_NAME\' \
		control=\'$SQLLDR_CONTROL\' \
		parfile=\'$SQLLDR_PARFILE\' \
		log=\'$LOGFILE_SQLLDR\' \
		data=\'$file_data\' \
		discard=\'$DIR_ERR/$BASENAME_DATA$FILE_DSC_EXT\' \
		bad=\'$DIR_ERR/$BASENAME_DATA$FILE_ERR_EXT\'
		retcode=`echo $?` 

		# Informa arquivo de log do SQL Loader se houver
		if [ -e "$LOGFILE_SQLLDR" ]
		then
			echo "$(date $DTMASK) -- SQL*Loader logfile: $LOGFILE_SQLLDR"
		fi

		# Informa codigo de retorno do SQL Loader
		case "$retcode" in 
		$EX_SUCC) echo "$(date $DTMASK) -- SQL*Loader execution successful." ;; 
		$EX_FAIL) echo "$(date $DTMASK) -- SQL*Loader execution exited with EX_FAIL, see logfile."  ;; 
		$EX_WARN) echo "$(date $DTMASK) -- SQL*Loader execution exited with EX_WARN, see logfile."  ;; 
		$EX_FTL) echo "$(date $DTMASK) -- SQL*Loader execution encountered a fatal error."  ;; 
		*) echo "$(date $DTMASK) -- SQL*Loader: unknown return code." ;; 
		esac

		# Arquivo de log do SQL Plus
		LOGFILE_SQLPLUS="$DIR_LOG/$BASENAME_DATA-$DATETIME.sqlplus$FILE_LOG_EXT"

		# Chama funcao que executa a PROCEDURE
		echo "$(date $DTMASK) -- Chamando procedure de atualizacao..."
		atualiza "$LOGFILE_SQLPLUS" "$PARAM_COMMIT"
		
		# Informa conteudo das variaveis de returno;
		echo "$(date $DTMASK) -- VO_CDERRO: '$VO_CDERRO'"
		echo "$(date $DTMASK) -- VO_DSERRO: '$VO_DSERRO'"
		
		# Verifica se variaveis de erro estao preenchidas
		if [ -z "$VO_CDERRO" ] && [ -z "$VO_DSERRO" ]
		then
			echo "$(date $DTMASK) -- Variaveis VO_CDERRO e VO_DSERRO estao vazias, ERRO no SQLPLUS?"
		fi
		
	else
		# Informa descarte do arquivo
		echo "$(date $DTMASK) -- Descartando arquivo $file_data."
	fi

	# Nome do arquivo 'processado'
	FILE_PRC=$DIR_PRC/$BASENAME_DATA$FILE_PRC_EXT

	# Move arquivo lido para arquivo processado
	echo "$(date $DTMASK) -- Movendo $file_data para $FILE_PRC."
	mv $file_data $FILE_PRC
done


# Remove arquivo de trava
echo "$(date $DTMASK) -- Removendo arquivo de trava $LOCK_FILE"
rm -f ${LOCK_FILE} 

# Fim do processo
echo "$(date $DTMASK) <<< Codigo de retorno: $retcode"
echo "$(date $DTMASK) <<< $BASENAME"

# Retorna status code do processo
exit $retcode

