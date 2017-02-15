#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/protocoloNaoSincronizado.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/protocoloNaoSincronizado/bin
pwd >> ${logfile}

nohup ./protocoloNaoSincronizado > ../log/$NOME

echo Fim execucao: $NOME >> ${logfile}
