#!/bin/sh

. ${HOME}/BATCH/SetEnv.sh

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/pontocontato.`whoami`.log

echo Execucao.....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/pontocontato/bin
pwd >> ${logfile}

./preload_pontocontato.sh > ../log/$NOME 2>&1

echo Fim execucao.: $NOME >> ${logfile}
