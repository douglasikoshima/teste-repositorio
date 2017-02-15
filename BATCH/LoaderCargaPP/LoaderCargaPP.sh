#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/LoaderCargaPP.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/LoaderCargaPP/bin
pwd >> ${logfile}

nohup ./LoaderCargaPP > ../log/$NOME 

echo Fim execucao: $NOME >> ${logfile}
