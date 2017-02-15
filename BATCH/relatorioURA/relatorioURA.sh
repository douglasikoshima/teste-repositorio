#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/relatorioURA.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/relatorioURA/bin
pwd >> ${logfile}

nohup ./relatorioURA > ../log/$NOME

echo Fim execucao: $NOME >> ${logfile}
