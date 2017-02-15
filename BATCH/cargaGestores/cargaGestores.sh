#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/cargaGestores.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/cargaGestores/bin
pwd >> ${logfile}

nohup ./cargaGestores > ../log/$NOME 

echo Fim execucao: $NOME >> ${logfile}
