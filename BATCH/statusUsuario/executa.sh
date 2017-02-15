#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/statusUsuario.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/statusUsuario/bin
pwd >> ${logfile}

nohup ./statusUsuario > ../log/$NOME &

echo Fim execucao: $NOME >> ${logfile}

