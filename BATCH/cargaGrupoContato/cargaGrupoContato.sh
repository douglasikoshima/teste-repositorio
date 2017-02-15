#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/cargaGrupoContato.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/cargaGrupoContato/bin
pwd >> ${logfile}

./cargaGrupoContato > ../log/$NOME 

echo Fim execucao: $NOME >> ${logfile}
