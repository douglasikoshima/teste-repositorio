#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/notasUra.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/notasURA/bin
pwd >> ${logfile}

nohup ./notasUra > ../log/$NOME

echo Fim execucao: $NOME >> ${logfile}
