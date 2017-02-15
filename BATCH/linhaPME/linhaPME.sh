#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/linhaPME.`whoami`.log
ULOGIN=`whoami`

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/linhaPME/bin
pwd >> ${logfile}

./linhaPME $ULOGIN > ../log/$NOME 

echo Fim execucao: $NOME >> ${logfile}
