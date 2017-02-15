#!/bin/sh

# ==== blackListRel.pcpp ====
# . ${HOME}/.profile
# NOME="`date +%Y%m%d-%H%M%S`.log"
# logfile=/tmp/blackListRel.`whoami`.log
# echo Execucao....: $NOME > ${logfile}
# cd ${HOME}/deploy/processos/blackListRel/bin
# pwd >> ${logfile}
# nohup ./blackListRel > ../log/$NOME &
# echo Fim execucao: $NOME >> ${logfile}
# exit
# ==== blackListRel.pcpp ====


# Chama blackListRel.sh
cd ${HOME}/deploy/processos/blackListRel/
nohup ./blackListRel.sh >> log/nohup.out 2>&1
