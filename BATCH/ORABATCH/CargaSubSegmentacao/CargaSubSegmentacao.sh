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
FILE_SQLPLUS_EXT=.sqlplus.log

# Carrega configuracao
. ./$BASENAME.cfg

# Arquivo de log
LOGFILE=$DIR_LOG/$BASENAME-$DATETIME$FILE_LOG_EXT

# Arquivo de log Sysout
SYSLOGFILE=$DIR_LOG/$BASENAME-Sys-$DATETIME$FILE_LOG_EXT


# Arquivo de controle do SQL Loader
SQLLDR_CONTROL=$BASENAME.ctl

# Arquivo de parametros do SQL Loader
SQLLDR_PARFILE=$BASENAME.par

# SQL Loader return codes
EX_SUCC=0
EX_FAIL=1
EX_WARN=2
EX_FTL=3

# Locale oracle
export NLS_LANG=AMERICAN_AMERICA.WE8ISO8859P1


verificaReprocessamento()
{

	echo "$(date $DTMASK) -- Verificando o se eh reprocessamento"

sqlplus "$DB_USER/$DB_PASS@$DB_NAME" <<EOF
VARIABLE V_COUNT NUMBER;
BEGIN
    SELECT DECODE(COUNT(1),0,0,1) INTO :V_COUNT FROM LOAD.CARGASUBSEGMENTACAO WHERE INPROCESSADO = 0;
END;
/
EXIT :V_COUNT
EOF

	n=$?
	return $n
}

# Funcao p/ truncate no sqlplus
trunc() 
{
	echo "$(date $DTMASK) -- Executando SQLPLUS..."
	
	sqlplus "$DB_USER/$DB_PASS@$DB_NAME" <<endl
SET SERVEROUTPUT ON
SET TIMING ON
EXEC LOAD.TRUNC_CARGASUBSEGMENTACAO
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

	sqlplus "$DB_USER/$DB_PASS@$DB_NAME" @$BASENAME.sql "$1" "$2" 2>&1
	retcode=`echo $?`

	VO_CDERRO=$(cat "$1" | egrep '^VO_CDERRO' | sed 's/.*VO_CDERRO *//')
	VO_DSERRO=$(cat "$1" | egrep '^VO_DSERRO' | sed 's/.*VO_DSERRO *//')
	
	echo "$(date $DTMASK) -- Codigo retorno SQLPLUS: $retcode"
	return $retcode
}

# Inicio
retcode=
echo "$(date $DTMASK) >>> $BASENAME" >> $LOGFILE
echo "$(date $DTMASK) -- DB_USER=$DB_USER" >> $LOGFILE
echo "$(date $DTMASK) -- DB_NAME=$DB_NAME" >> $LOGFILE
echo "$(date $DTMASK) -- DIR_DATA=$DIR_DATA" >> $LOGFILE
echo "$(date $DTMASK) -- DIR_PRC=$DIR_PRC" >> $LOGFILE
echo "$(date $DTMASK) -- DIR_ERR=$DIR_ERR" >> $LOGFILE
echo "$(date $DTMASK) -- DIR_LOG=$DIR_LOG" >> $LOGFILE
echo "$(date $DTMASK) -- LOCK_FILE=$LOCK_FILE" >> $LOGFILE
echo "$(date $DTMASK) -- IDUSUARIOALTERACAO=$IDUSUARIOALTERACAO" >> $LOGFILE
echo "$(date $DTMASK) -- PARAM_COMMIT=$PARAM_COMMIT" >> $LOGFILE

exec 1> $SYSLOGFILE
exec 2>> $SYSLOGFILE

# Arquivo p/ trava do processo
#LOCK_FILE="/tmp/$BASENAME.lock"
echo "$(date $DTMASK) -- Verificando arquivo de trava $LOCK_FILE." >> $LOGFILE
if [ -e ${LOCK_FILE} ] && kill -0 `cat ${LOCK_FILE}`; then
	echo "$(date $DTMASK) -- Nao foi possivel obter $LOCK_FILE!" >> $LOGFILE 2>&1
	echo "$(date $DTMASK) <<< $BASENAME" >> $LOGFILE

	# Retorna PID do processo existente
	retcode=$(cat ${LOCK_FILE})
	exit $retcode
fi

# Remove LOCK_FILE quando processo encerrado 
# OBS: O arquivo LOCK_FILE deve ser removido se o processo for finalizado com 'kill -9'!
trap "rm -f ${LOCK_FILE}; exit" INT TERM EXIT
echo $$ > ${LOCK_FILE}

# Busca arquivo para leitura
echo "$(date $DTMASK) -- Buscando arquivos: $DIR_DATA/*$FILE_DATA_EXT" >> $LOGFILE
	
#echo "$(date $DTMASK) -- Verificando se eh reprocesamento $DIR_DATA/*$FILE_DATA_EXT " >> $LOGFILE

verificaReprocessamento
reprocessamento=$?

if [ reprocessamento -eq 0 ]
then

for file_data in $(ls -1tr $DIR_DATA/*$FILE_DATA_EXT 2>/dev/null)
do
	# Nome do arquivo encontrado
	BASENAME_DATA=$(basename $file_data|cut -d. -f1)
	
	# Carregar apenas o arquivo mais atual
	if [ -z "$retcode" ]
	then
		echo "$(date $DTMASK) -- Processando arquivo $file_data" >> $LOGFILE
		
		# Chama funcao que executa o TRUNCATE
		echo "$(date $DTMASK) -- Chamando procedure de truncate..." >> $LOGFILE
		trunc >> $LOGFILE 2>&1
		
		# Arquivo de log do SQL Loader
		LOGFILE_SQLLDR="$DIR_LOG/$BASENAME_DATA-$DATETIME.sqlldr$FILE_LOG_EXT"
		
		# Execta SQL Loader
		echo "$(date $DTMASK) -- Executando SQL*Loader..." >> $LOGFILE
		sqlldr userid=\'$DB_USER/$DB_PASS@$DB_NAME\' \
		control=\'$SQLLDR_CONTROL\' \
		parfile=\'$SQLLDR_PARFILE\' \
		log=\'$LOGFILE_SQLLDR\' \
		data=\'$file_data\' \
		discard=\'$DIR_ERR/$BASENAME_DATA$FILE_DSC_EXT\' \
		bad=\'$DIR_ERR/$BASENAME_DATA$FILE_ERR_EXT\' >> $LOGFILE 2>&1
		retcode=`echo $?`

		# Informa arquivo de log do SQL Loader se houver
		if [ -e "$LOGFILE_SQLLDR" ]
then
			echo "$(date $DTMASK) -- SQL*Loader logfile: $LOGFILE_SQLLDR" >> $LOGFILE
		fi

		# Informa codigo de retorno do SQL Loader
		case "$retcode" in 
		$EX_SUCC) echo "$(date $DTMASK) -- SQL*Loader execution successful." >> $LOGFILE ;; 
		$EX_FAIL) echo "$(date $DTMASK) -- SQL*Loader execution exited with EX_FAIL, see logfile."  >> $LOGFILE ;; 
		$EX_WARN) echo "$(date $DTMASK) -- SQL*Loader execution exited with EX_WARN, see logfile."  >> $LOGFILE ;; 
		$EX_FTL) echo "$(date $DTMASK) -- SQL*Loader execution encountered a fatal error."  >> $LOGFILE ;; 
		*) echo "$(date $DTMASK) -- SQL*Loader: unknown return code." >> $LOGFILE ;; 
		esac

		# Arquivo de log do SQL Plus
		LOGFILE_SQLPLUS="$DIR_LOG/$BASENAME_DATA-$DATETIME$FILE_SQLPLUS_EXT"

		# Chama funcao que executa a PROCEDURE
		echo "$(date $DTMASK) -- Chamando procedure de atualizacao..." >> $LOGFILE
		atualiza "$LOGFILE_SQLPLUS" "$PARAM_COMMIT" >> $LOGFILE 2>&1
		
		# Verifica se variaveis de erro estao preenchidas
		if [ -z "$VO_CDERRO" ] && [ -z "$VO_DSERRO" ]
		then
			echo "$(date $DTMASK) -- Variaveis VO_CDERRO e VO_DSERRO estao vazias, ERRO no SQLPLUS?" >> $LOGFILE
			
			# retorna -1 no exit
			retcode=-1
else
			# Informa conteudo das variaveis de retorno
			echo "$(date $DTMASK) -- VO_CDERRO: '$VO_CDERRO'" >> $LOGFILE
			echo "$(date $DTMASK) -- VO_DSERRO: '$VO_DSERRO'" >> $LOGFILE
			
			# Retorna valor de VO_CDERRO no exit
			retcode=$VO_CDERRO
		fi
		
			#echo "$(date $DTMASK) -- Executando procedure LOAD.PRC_ATUALIZASUBSEGMENTACAO" >> $LOGFILE
			#./prc_atualizasubsegmentacao.sh
		
	else
		# Informa descarte do arquivo
		echo "$(date $DTMASK) -- Descartando arquivo $file_data." >> $LOGFILE
fi

	# Nome do arquivo 'processado'
	FILE_PRC=$DIR_PRC/$BASENAME_DATA$FILE_PRC_EXT

	# Move arquivo lido para arquivo processado
	echo "$(date $DTMASK) -- Movendo $file_data para $FILE_PRC." >> $LOGFILE
	mv $file_data $FILE_PRC >> $LOGFILE 2>&1
done
else

	echo "$(date $DTMASK) -- Iniciando reprocessamento." >> $LOGFILE

	# Arquivo de log do SQL Plus
	LOGFILE_SQLPLUS="$DIR_LOG/Reprocessamento-$DATETIME$FILE_SQLPLUS_EXT"

	# Chama funcao que executa a PROCEDURE
	echo "$(date $DTMASK) -- Chamando procedure de atualizacao..." >> $LOGFILE
	atualiza "$LOGFILE_SQLPLUS" "$PARAM_COMMIT" >> $LOGFILE 2>&1
fi

# Remove arquivo de trava
echo "$(date $DTMASK) -- Removendo arquivo de trava $LOCK_FILE" >> $LOGFILE
rm -f ${LOCK_FILE} >> $LOGFILE 2>&1 

# Fim do processo
echo "$(date $DTMASK) -- Codigo de retorno: exit($retcode)" >> $LOGFILE
echo "$(date $DTMASK) <<< $BASENAME" >> $LOGFILE

# Retorna status code do processo
exit $retcode

