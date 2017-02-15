#!/bin/sh -

DTMASK=+%Y%m%d-%H%M%S
DATETIME=$(date $DTMASK)
IFS='
'
BASENAME=$(basename $0|cut -d. -f1)
DIRNAME=$(dirname $0)
cd $DIRNAME
. ./$BASENAME.cfg
LOGFILE=$DIR_LOG/$BASENAME-$DATETIME$FILE_LOG_EXT

truncceparearural() 
{
	sqlplus "$DB_USER/$DB_PASS@$DB_NAME" <<endl
SET SERVEROUTPUT ON
SET TIMING ON
EXEC ADM_PROJECT.LOAD_TRUNC_CEPAREARURAL
exit
endl
}

atualizaarearural()
{
	sqlplus "$DB_USER/$DB_PASS@$DB_NAME" <<endl
SET FLUSH ON
SET TIMING ON
SET SERVEROUTPUT ON
SET ECHO ON
BEGIN
	DBMS_OUTPUT.PUT_LINE(CHR(10)||CHR(10)||'Atualizando CEP.INAREARURAL...'||CHR(10));
	UPDATE ADM_PROJECT.CEP
	SET INAREARURAL = 1
	WHERE NUM_CEP IN (SELECT NUM_CEP FROM ADM_PROJECT.LOAD_CEPAREARURAL);
	DBMS_OUTPUT.PUT_LINE(CHR(10)||'Registros atualizados em CEP: '||SQL%ROWCOUNT||CHR(10));
	COMMIT;

	DBMS_OUTPUT.PUT_LINE(CHR(10)||'Verficando nao atualizados...'||CHR(10));
END;
/
exit
endl

sqlplus "$DB_USER/$DB_PASS@$DB_NAME" @$BASENAME.sql "$1"

}

echo "$(date $DTMASK) -- >>> $BASENAME" > $LOGFILE

#LOCK_FILE="/tmp/$BASENAME.lock"
if [ -e ${LOCK_FILE} ] && kill -0 `cat ${LOCK_FILE}`; then
	echo "$(date $DTMASK) -- Nao foi possivel obter $LOCK_FILE!" >> $LOGFILE 2>&1
	echo "$(date $DTMASK) -- <<< $BASENAME" >> $LOGFILE
	exit 1 
fi

trap "rm -f ${LOCK_FILE}; exit" INT TERM EXIT
echo $$ > ${LOCK_FILE}

echo "$(date $DTMASK) -- Buscando arquivos: $DIR_DATA/*$FILE_DATA_EXT" >> $LOGFILE

for file_data in $(ls $DIR_DATA/*$FILE_DATA_EXT 2>/dev/null)
do
	BASENAME_DATA=$(basename $file_data|cut -d. -f1)
	echo "$(date $DTMASK) -- Processando arquivo $file_data" >> $LOGFILE
	
	echo "$(date $DTMASK) -- Executando LOAD_TRUNC_CEPAREARURAL..." >> $LOGFILE
	truncceparearural >> $LOGFILE 2>&1
	
	echo "$(date $DTMASK) -- Executando SQL*Loader..." >> $LOGFILE
	sqlldr userid=\'$DB_USER/$DB_PASS@$DB_NAME\' \
		control=\'$SQLLDR_CONTROL\' \
		parfile=\'$SQLLDR_PARFILE\' \
		log=\'$DIR_LOG/sqlldr.$BASENAME_DATA-$DATETIME$FILE_LOG_EXT\' \
		data=\'$file_data\' \
		bad=\'$DIR_ERR/$BASENAME_DATA$FILE_ERR_EXT\' >> $LOGFILE 2>&1
	retcode=`echo $?` 
	case "$retcode" in 
	0) echo "$(date $DTMASK) -- SQL*Loader executado com sucesso." >> $LOGFILE;; 
	*) echo "$(date $DTMASK) -- SQL*Loader, retcode: $retcode." >> $LOGFILE;; 
	esac
	
	echo "$(date $DTMASK) -- Atualizando CEP.INAREARURAL..." >> $LOGFILE
	atualizaarearural "$DIR_ERR/$BASENAME_DATA$FILE_ERR_EXT"  >> $LOGFILE 2>&1

	echo "$(date $DTMASK) -- Movendo $file_data" >> $LOGFILE
	mv $file_data $DIR_PRC/$BASENAME_DATA$FILE_PRC_EXT >> $LOGFILE 2>&1
done

echo "$(date $DTMASK) -- <<< $BASENAME" >> $LOGFILE

rm -f ${LOCK_FILE}
exit 0

