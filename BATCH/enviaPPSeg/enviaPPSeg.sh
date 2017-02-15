#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
NOME_SYS=../log/enviaPPSegSys_"`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/enviaPPSeg.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/enviaPPSeg/bin
pwd >> ${logfile}

exec 2>&1 >> ${NOME_SYS}

nohup ./enviaPPSeg > ../log/$NOME
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