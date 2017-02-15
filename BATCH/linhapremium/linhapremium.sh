#!/bin/sh

. ${HOME}/BATCH/SetEnv.sh

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/linhapremium.`whoami`.log

echo Execucao.....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/linhapremium/bin
pwd >> ${logfile}

./preload_linhapremium.sh > ../log/$NOME 2>&1

echo Fim execucao.: $NOME >> ${logfile}