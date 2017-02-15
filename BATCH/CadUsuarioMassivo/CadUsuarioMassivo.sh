#!/bin/sh

. ${HOME}/.profile

export NLS_LANG=AMERICAN_AMERICA.WE8ISO8859P1

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/CadUsuarioMassivo.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/CadUsuarioMassivo/bin
pwd >> ${logfile}

NOME_SYS=../log/CadUsuarioMassivoSys_"`date +%Y%m%d-%H%M%S`.log"
exec 2>&1 >> ${NOME_SYS}

nohup ./CadUsuarioMassivo >> ../log/$NOME
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
