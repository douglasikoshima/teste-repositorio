#!/bin/sh

. ${HOME}/BATCH/SetEnv.sh

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/listaCampanha.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/listaCampanha/bin
pwd >> ${logfile}

./listaCampanha > ../log/$NOME

echo Fim execucao: $NOME >> ${logfile}
