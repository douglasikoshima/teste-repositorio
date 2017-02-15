#!/bin/sh

. ${HOME}/BATCH/SetEnv.sh

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/encaminhaContatoPrevio.`whoami`.log

echo Execucao.....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/encaminhaContatoPrevio/bin
pwd >> ${logfile}

./encaminhaContatoPrevio > ../log/$NOME

echo Fim execucao.: $NOME >> ${logfile}
