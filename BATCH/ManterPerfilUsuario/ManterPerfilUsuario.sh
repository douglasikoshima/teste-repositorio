#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/ManterPerfilUsuario.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/ManterPerfilUsuario/bin
pwd >> ${logfile}

nohup ./ManterPerfilUsuario > ../log/$NOME 

echo Fim execucao: $NOME >> ${logfile}
