#!/bin/sh

. ${HOME}/BATCH/SetEnv.sh

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/gestorAutorizado.`whoami`.log

echo Execucao.....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/ORABATCH/gestorAutorizado/bin
pwd >> ${logfile}

./preload_gestorAutorizado.sh > ../log/$NOME 2>&1

echo Fim execucao.: $NOME >> ${logfile}