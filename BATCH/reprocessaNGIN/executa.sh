#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/reprocessaNGIN.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/reprocessaNGIN/bin
pwd >> ${logfile}

nohup ./reprocessaNGIN > ../log/$NOME &

echo Fim execucao: $NOME >> ${logfile}
