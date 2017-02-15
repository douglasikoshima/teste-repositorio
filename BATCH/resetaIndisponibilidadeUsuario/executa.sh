#!/bin/sh

. ${HOME}/BATCH/SetEnv.sh

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/resetaIndisponibilidadeUsuario.`whoami`.log

echo Execucao.....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/resetaIndisponibilidadeUsuario/bin
pwd >> ${logfile}

./resetaIndisponibilidadeUsuario > ../log/$NOME

echo Fim execucao.: $NOME >> ${logfile}
