#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/finalizaCadastroPortIn.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/finalizaCadastroPortIn/bin
pwd >> ${logfile}

nohup ./finalizaCadastroPortIn > ../log/$NOME &

echo Fim execucao: $NOME >> ${logfile}
