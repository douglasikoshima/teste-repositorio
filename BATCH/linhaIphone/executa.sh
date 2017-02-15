#!/bin/sh

. ${HOME}/BATCH/SetEnv.sh

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/linhaIphone.`whoami`.log

echo Execucao.....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/linhaIphone/data
find ./ -type f -name '*.[Tt][Xx][Tt]' -exec perl -i -pe 's/\r\n/\n/g' {} \;

cd ${HOME}/deploy/processos/linhaIphone/bin
pwd >> ${logfile}

./preload_linhaIphone.sh > ../log/$NOME 2>&1

echo Fim execucao.: $NOME >> ${logfile}