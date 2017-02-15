#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/enviaVIP.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/enviaVIP/bin
pwd >> ${logfile}

nohup ./enviaVIP > ../log/$NOME &

echo Fim execucao: $NOME >> ${logfile}
