#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/expurgoRegistrosPreAtivacao.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/expurgoRegistrosPreAtivacao/bin
pwd >> ${logfile}

nohup ./expurgoRegistrosPreAtivacao > ../log/$NOME

echo Fim execucao: $NOME >> ${logfile}