#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/mailing.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/mailing/bin
pwd >> ${logfile}

nohup ./mailing > ../log/$NOME &

echo Fim execucao: $NOME >> ${logfile}
