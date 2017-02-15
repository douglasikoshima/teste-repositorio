#!/bin/sh

. ${HOME}/BATCH/SetEnv.sh

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/planoservicolinha_tmp.`whoami`.log

echo Execucao.....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/planoservicolinha_tmp/bin
pwd >> ${logfile}

./updt_planoservicoLinha.sh > ../log/$NOME 2>&1

echo Fim execucao.: $NOME >> ${logfile}