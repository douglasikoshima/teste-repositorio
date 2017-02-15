#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/importFileDadosAjato.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/importFileDadosAjato/bin
pwd >> ${logfile}

nohup ./importFileDadosAjato > ../log/$NOME &

echo Fim execucao: $NOME >> ${logfile}