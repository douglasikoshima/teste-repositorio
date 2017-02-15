#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/relatorioPP.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/relatorioPP/bin
pwd >> ${logfile}

#nohup ./relatorioPP > ../log/$NOME 

echo Fim execucao: $NOME >> ${logfile}
