#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/copiaArvore.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/copiaArvore/bin
pwd >> ${logfile}

nohup ./copiaArvore > ../log/$NOME &

echo Fim execucao: $NOME >> ${logfile}
