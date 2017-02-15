#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/manutencaoAcesso.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/manutencaoAcesso/bin

#find ../data -type f -name '*.[Tt][Xx][Tt]' -exec perl -i -pe 's/\r\n/\n/g' {} \;

pwd >> ${logfile}

nohup ./manutencaoAcesso > ../log/$NOME

echo Fim execucao: $NOME >> ${logfile}
