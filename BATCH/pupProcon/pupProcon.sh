#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/pupProcon.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/pupProcon/bin
pwd >> ${logfile}

./pupProcon > ../log/$NOME 

echo Fim execucao: $NOME >> ${logfile}
