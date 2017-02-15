#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/vinculoRecargaSMS.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/vinculoRecargaSMS/bin
pwd >> ${logfile}

nohup ./vinculoRecargaSMS > ../log/$NOME

echo Fim execucao: $NOME >> ${logfile}
