#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/enviaLegado2.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/enviaLegado2/bin
pwd >> ${logfile}

nohup ./enviaLegado2 > ../log/$NOME

echo Fim execucao: $NOME >> ${logfile}
