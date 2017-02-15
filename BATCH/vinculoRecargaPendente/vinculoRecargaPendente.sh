#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/vinculoRecargaPendente.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/vinculoRecargaPendente/bin
pwd >> ${logfile}

nohup ./vinculoRecargaPendente > ../log/$NOME 

echo Fim execucao: $NOME >> ${logfile}
