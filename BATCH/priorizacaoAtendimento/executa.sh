#!/bin/sh

. ${HOME}/BATCH/SetEnv.sh

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/priorizacaoAtendimento.`whoami`.log

echo Execucao.....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/priorizacaoAtendimento/bin
pwd >> ${logfile}

./priorizacaoAtendimento > ../log/$NOME

echo Fim execucao.: $NOME >> ${logfile}
