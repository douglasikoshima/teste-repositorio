#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/ValidarLinhaCPF.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/ValidarLinhaCPF/bin
pwd >> ${logfile}

nohup ./ValidarLinhaCPF > ../log/$NOME

echo Fim execucao: $NOME >> ${logfile}
