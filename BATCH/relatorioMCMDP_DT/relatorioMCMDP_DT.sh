#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/relatorioMCMDP_DT.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/relatorioMCMDP_DT/bin
pwd >> ${logfile}

nohup ./relatorioMCMDP_DT > ../log/$NOME

echo Fim execucao: $NOME >> ${logfile}
