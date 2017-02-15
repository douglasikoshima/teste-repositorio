#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/cargaAnatel.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/cargaAnatel/bin
pwd >> ${logfile}

./cargaAnatel > ../log/$NOME 

echo Fim execucao: $NOME >> ${logfile}
