#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/desativarComprovante.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/desativarComprovante/bin
pwd >> ${logfile}

nohup ./desativarComprovante > ../log/$NOME &

echo Fim execucao: $NOME >> ${logfile}
