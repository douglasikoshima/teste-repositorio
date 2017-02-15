#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/enviaInterceptacao.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/enviaInterceptacao/bin
pwd >> ${logfile}

nohup ./enviaInterceptacao > ../log/$NOME &

echo Fim execucao: $NOME >> ${logfile}
