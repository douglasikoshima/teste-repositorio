#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/relatorioMVE.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/relatorioMVE/bin
pwd >> ${logfile}

nohup ./relatorioMVE > ../log/$NOME

echo Fim execucao: $NOME >> ${logfile}
