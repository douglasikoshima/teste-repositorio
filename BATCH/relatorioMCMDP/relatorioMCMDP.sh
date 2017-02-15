#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/relatorioMCMDP.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/relatorioMCMDP/bin
pwd >> ${logfile}

nohup ./relatorioMCMDP > ../log/$NOME

echo Fim execucao: $NOME >> ${logfile}
