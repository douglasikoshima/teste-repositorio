#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/extratorDB.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/extratorDB/bin
pwd >> ${logfile}

nohup ./extratorDB > ../log/$NOME 

echo Fim execucao: $NOME >> ${logfile}
