#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/relatorioSP.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/relatorioSP/bin
pwd >> ${logfile}

nohup ./relatorioSP > ../log/$NOME

echo Fim execucao: $NOME >> ${logfile}
