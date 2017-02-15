#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/relPalitagem.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/relPalitagem/bin
pwd >> ${logfile}

nohup ./relPalitagem > ../log/$NOME &

echo Fim execucao: $NOME >> ${logfile}
