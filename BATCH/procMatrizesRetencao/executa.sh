#!/bin/sh

. ${HOME}/BATCH/SetEnv.sh

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/procMatrizesRetencao.`whoami`.log

echo Execucao.....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/procMatrizesRetencao/bin
pwd >> ${logfile}

./procMatrizesRetencao > ../log/$NOME

echo Fim execucao.: $NOME >> ${logfile}
