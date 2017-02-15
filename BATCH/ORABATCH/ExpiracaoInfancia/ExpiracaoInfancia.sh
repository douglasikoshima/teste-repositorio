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
FILE_LOG_EXT=.log
FILE_SQLPLUS_EXT=.sqlplus.log

# Carrega configuracao
. ./$BASENAME.cfg

# Arquivo de log
LOGFILE=$DIR_LOG/$BASENAME-$DATETIME$FILE_LOG_EXT

# Arquivo de spool do sqlplus
LOGFILE_SQLPLUS=$DIR_LOG/$BASENAME-$DATETIME$FILE_SQLPLUS_EXT

# Inicio
retcode=0
echo "$(date $DTMASK) >>> $BASENAME" >> $LOGFILE
echo "$(date $DTMASK) -- DB_USER=$DB_USER" >> $LOGFILE
echo "$(date $DTMASK) -- DB_NAME=$DB_NAME" >> $LOGFILE
echo "$(date $DTMASK) -- DIR_LOG=$DIR_LOG" >> $LOGFILE
echo "$(date $DTMASK) -- IDUSUARIOALTERACAO=$IDUSUARIOALTERACAO" >> $LOGFILE
echo "$(date $DTMASK) -- LOCK_FILE=$LOCK_FILE" >> $LOGFILE
echo "$(date $DTMASK) -- PARAM_COMMIT=$PARAM_COMMIT" >> $LOGFILE

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
trap "rm -f ${LOCK_FILE}; exit" INT TERM EXIT
echo $$ > ${LOCK_FILE}

echo "$(date $DTMASK) -- Executando SQLPLUS..." >> $LOGFILE
	
# Variaveis de retorno da procedure
VO_CDERRO=
VO_DSERRO=

# Chama SQLPLUS
sqlplus "$DB_USER/$DB_PASS@$DB_NAME" @$BASENAME.sql "$LOGFILE_SQLPLUS" "$IDUSUARIOALTERACAO" "$PARAM_COMMIT" >> $LOGFILE 2>&1
retcode=$?

echo "$(date $DTMASK) -- Codigo retorno SQLPLUS: $retcode" >> $LOGFILE

# Informa arquivo de log do SQL Loader se houver
if [ -e "$LOGFILE_SQLPLUS" ]
then
	echo "$(date $DTMASK) -- SQLPLUS log file: $LOGFILE_SQLPLUS" >> $LOGFILE
fi

# Obtem valor das variaveis de erro
VO_CDERRO=$(cat "$LOGFILE_SQLPLUS" | egrep '^VO_CDERRO' | sed 's/.*VO_CDERRO *//')
VO_DSERRO=$(cat "$LOGFILE_SQLPLUS" | egrep '^VO_DSERRO' | sed 's/.*VO_DSERRO *//')

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

# Remove arquivo de trava
echo "$(date $DTMASK) -- Removendo arquivo de trava $LOCK_FILE" >> $LOGFILE
rm -f ${LOCK_FILE} >> $LOGFILE 2>&1 

# Fim do processo
echo "$(date $DTMASK) -- Codigo de retorno: exit($retcode)" >> $LOGFILE
echo "$(date $DTMASK) <<< $BASENAME" >> $LOGFILE

# Retorna status code do processo
exit $retcode
