#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/cargaCatalogo.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/cargaCatalogo/bin
pwd >> ${logfile}

nohup ./cargaCatalogo > ../log/$NOME &

echo Fim execucao: $NOME >> ${logfile}
