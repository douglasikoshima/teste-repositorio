#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/cargaPlanoDados.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/cargaPlanoDados/bin
pwd >> ${logfile}

nohup ./cargaPlanoDados > ../log/$NOME 

echo Fim execucao: $NOME >> ${logfile}
