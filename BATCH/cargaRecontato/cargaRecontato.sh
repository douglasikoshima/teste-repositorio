#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/cargaRecontato.`whoami`.log
logfile_err=/tmp/cargaRecontato.`whoami`.err

exec 2>> ${logfile_err}
echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/cargaRecontato/bin
pwd >> ${logfile}

./cargaRecontato > ../log/$NOME 

echo Fim execucao: $NOME >> ${logfile}
