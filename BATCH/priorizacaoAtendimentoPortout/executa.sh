#!/bin/sh

. ${HOME}/BATCH/SetEnv.sh

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/priorizacaoAtendimentoPortout.`whoami`.log

echo Execucao.....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/priorizacaoAtendimentoPortout/bin
pwd >> ${logfile}

./priorizacaoAtendimentoPortout > ../log/$NOME

echo Fim execucao.: $NOME >> ${logfile}
