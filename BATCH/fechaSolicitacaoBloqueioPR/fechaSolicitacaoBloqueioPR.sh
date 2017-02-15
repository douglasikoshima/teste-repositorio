#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/fechaSolicitacaoBloqueioPR.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/fechaSolicitacaoBloqueioPR/bin
pwd >> ${logfile}

nohup ./fechaSolicitacaoBloqueioPR > ../log/$NOME

echo Fim execucao: $NOME >> ${logfile}
