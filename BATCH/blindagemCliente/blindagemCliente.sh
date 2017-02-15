#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/blindagemCliente.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/blindagemCliente/bin
pwd >> ${logfile}

./blindagemCliente > ../log/$NOME 

echo Fim execucao: $NOME >> ${logfile}
