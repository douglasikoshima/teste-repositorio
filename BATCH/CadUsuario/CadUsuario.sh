#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/CadUsuario.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/CadUsuario/bin
pwd >> ${logfile}

nohup ./CadUsuario > ../log/$NOME

echo Fim execucao: $NOME >> ${logfile}
