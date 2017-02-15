#!/bin/sh

. ${HOME}/BATCH/SetEnv.sh

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/relatorioArvore.`whoami`.log

echo Execucao.....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/relatorioArvore/bin
pwd >> ${logfile}

./relatorioArvore ./relatorioArvore.cfg > ../log/$NOME

echo Fim execucao.: $NOME >> ${logfile}
