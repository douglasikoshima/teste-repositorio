#!/bin/sh

. ${HOME}/.profile

DATA=`date '+%Y%m%d%H%M'` #Data + Ano, Mes, Dia, Hora, Minuto

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/cargaConsultor.`whoami`.log
logfile_err=/tmp/cargaConsultor.`whoami`.err

exec 1> ${HOME}/deploy/processos/cargaConsultor/log/cargaConsultorSys_$DATA.log
exec 2>> ${HOME}/deploy/processos/cargaConsultor/log/cargaConsultorSys_$DATA.log

#exec 2>> ${logfile_err}
echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/cargaConsultor/data

find ${HOME}/deploy/processos/cargaConsultor/data/ -type f -name '*.[Tt][Xx][Tt]' -exec perl -i -pe 's/\r\n/\n/g' {} \;

find ${HOME}/deploy/processos/cargaConsultor/data/ -type f -name '*.[Tt][Xx][Tt]' -exec perl -i -pe 's/^[\t\s\r]*$//g' {} \;

cd ${HOME}/deploy/processos/cargaConsultor/bin
pwd >> ${logfile}

./cargaConsultor >> ../log/$NOME

echo Fim execucao: $NOME >> ${logfile}
