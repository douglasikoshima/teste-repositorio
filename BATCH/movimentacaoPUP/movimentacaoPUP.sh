#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/movimentacaoPUP.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/movimentacaoPUP/bin
pwd >> ${logfile}

nohup ./movimentacaoPUP > ../log/$NOME 

echo Fim execucao: $NOME >> ${logfile}
