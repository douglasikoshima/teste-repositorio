#!/bin/sh

. ${HOME}/BATCH/SetEnv.sh

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/atualizaStatus.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/atualizaStatus/bin
pwd >> ${logfile}

./atualizaStatus > ../log/$NOME

echo Fim execucao: $NOME >> ${logfile}
