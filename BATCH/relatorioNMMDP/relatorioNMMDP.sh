#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/relatorioNMMDP.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/relatorioNMMDP/bin
pwd >> ${logfile}

nohup ./relatorioNMMDP > ../log/$NOME

echo Fim execucao: $NOME >> ${logfile}
