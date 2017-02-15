#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/ativarAparelho.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/ativarAparelho/bin
pwd >> ${logfile}

nohup ./ativarAparelho > ../log/$NOME 

echo Fim execucao: $NOME >> ${logfile}
