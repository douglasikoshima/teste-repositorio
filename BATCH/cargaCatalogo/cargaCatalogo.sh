#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/cargaCatalogo.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/cargaCatalogo/bin
pwd >> ${logfile}

# Locale oracle
export NLS_LANG=AMERICAN_AMERICA.WE8ISO8859P1

nohup ./cargaCatalogo > ../log/$NOME

echo Fim execucao: $NOME >> ${logfile}
