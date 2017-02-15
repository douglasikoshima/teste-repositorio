#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/cartaoVivoItauCard.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/cartaoVivoItauCard/bin
pwd >> ${logfile}

nohup ./cartaoVivoItauCard > ../log/$NOME

echo Fim execucao: $NOME >> ${logfile}
