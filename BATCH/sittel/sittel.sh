#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/sittel.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/sittel/bin
pwd >> ${logfile}

NLS_LANG=AMERICAN_AMERICA.WE8ISO8859P1
export NLS_LANG

ORA_XML_MESG=$ORACLE_HOME/xdk/mesg
export ORA_XML_MESG

ORA_NLS10=$ORACLE_HOME/nls/data
export ORA_NLS10

nohup ./sittel_ID > ../log/$NOME 2>&1
retcode=`echo $?`

echo Fim execucao: $NOME >> ${logfile}

if [ $retcode == 0 ]
then
	echo "Processo executado com sucesso!"
	exit 0
else
	echo "Processo executado com erro/critica: $retcode"
	exit 1
fi
