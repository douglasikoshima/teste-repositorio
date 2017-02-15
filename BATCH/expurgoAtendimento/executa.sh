#!/bin/sh

. ${HOME}/BATCH/SetEnv.sh

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/expurgoAtendimento.`whoami`.log

echo Execucao.....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/expurgoAtendimento/bin
pwd >> ${logfile}

./expurgoAtendimento > ../log/$NOME

echo Fim execucao.: $NOME >> ${logfile}
