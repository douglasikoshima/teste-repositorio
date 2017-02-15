#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/relatorioIMEI.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/relatorioIMEI/bin
pwd >> ${logfile}

nohup ./relatorioIMEI > ../log/$NOME &

echo Fim execucao: $NOME >> ${logfile}
