#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/criaCad.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/criaCad/bin
pwd >> ${logfile}

nohup ./criaCad > ../log/$NOME &

echo Fim execucao: $NOME >> ${logfile}
