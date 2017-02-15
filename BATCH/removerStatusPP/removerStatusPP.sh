#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/removerStatusPP.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/removerStatusPP/bin
pwd >> ${logfile}

nohup ./removerStatusPP > ../log/$NOME 

echo Fim execucao: $NOME >> ${logfile}
