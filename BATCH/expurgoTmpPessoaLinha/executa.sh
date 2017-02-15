#!/bin/sh

. ${HOME}/BATCH/SetEnv.sh

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/expurgoTmpPessoaLinha.`whoami`.log

echo Execucao.....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/expurgoTmpPessoaLinha/bin
pwd >> ${logfile}

./expurgoTmpPessoaLinha.sh > ../log/$NOME 2>&1

echo Fim execucao.: $NOME >> ${logfile}