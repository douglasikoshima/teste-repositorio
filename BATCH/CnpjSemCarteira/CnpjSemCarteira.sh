#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/CnpjSemCarteira.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/CnpjSemCarteira/bin
pwd >> ${logfile}

nohup ./CnpjSemCarteira > ../log/$NOME

echo Fim execucao: $NOME >> ${logfile}
