#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
NOME_SYS=../log/enviaProtocoloSmsSys_"`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/enviaProtocoloSms.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/enviaProtocoloSms/bin
pwd >> ${logfile}

exec 2>&1 >> ${NOME_SYS}

#nohup ./enviaProtocoloSms 10.26.33.133 17901 5000 10 > ../log/$NOME 
nohup ./enviaProtocoloSms 10.26.33.133 17901 > ../log/$NOME 
retcode=$?

echo Fim execucao: $NOME >> ${logfile}

if [ $retcode == 0 ]
then
	echo "Processo executado com sucesso!"
	exit 0
else
	echo "Processo executado com erro/critica: $retcode"
	exit 1
fi
