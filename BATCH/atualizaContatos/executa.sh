#!/bin/sh

. ${HOME}/BATCH/SetEnv.sh

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/atualizaContatos.`whoami`.log

echo Execucao.....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/atualizaContatos/bin
pwd >> ${logfile}

./atualizaContatos > ../log/$NOME

echo Fim execucao.: $NOME >> ${logfile}
