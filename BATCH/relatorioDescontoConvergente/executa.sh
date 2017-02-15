#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/relatorioDescontoConvergente.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/relatorioDescontoConvergente/bin
pwd >> ${logfile}

nohup ./relatorioDescontoConvergente > ../log/$NOME &

echo Fim execucao: $NOME >> ${logfile}