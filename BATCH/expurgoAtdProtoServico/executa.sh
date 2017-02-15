#!/bin/sh

. ${HOME}/BATCH/SetEnv.sh

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/expurgoAtdProtoServico.`whoami`.log

echo Execucao.....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/expurgoAtdProtoServico/bin
pwd >> ${logfile}

./expurgoAtdProtoServico.sh > ../log/$NOME 2>&1

echo Fim execucao.: $NOME >> ${logfile}