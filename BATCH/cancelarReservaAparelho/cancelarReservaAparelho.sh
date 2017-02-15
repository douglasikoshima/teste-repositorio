#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/cancelarReservaAparelho.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/cancelarReservaAparelho/bin
pwd >> ${logfile}

./cancelarReservaAparelho > ../log/$NOME 

echo Fim execucao: $NOME >> ${logfile}
