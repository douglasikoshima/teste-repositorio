#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/atendimentoVOLE.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/atendimentoVOLE/bin
pwd >> ${logfile}

#nohup ./atendimentoVOLE > ../log/$NOME 

echo Fim execucao: $NOME >> ${logfile}
