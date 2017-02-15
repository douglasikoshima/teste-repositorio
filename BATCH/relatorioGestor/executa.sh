#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/relatorioGestor.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/relatorioGestor/bin
pwd >> ${logfile}

nohup ./relatorioGestor > ../log/$NOME

echo Fim execucao: $NOME >> ${logfile}
