#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/dataNGIN.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/dataNGIN/bin
pwd >> ${logfile}

nohup ./dataNGIN > ../log/$NOME 

echo Fim execucao: $NOME >> ${logfile}
