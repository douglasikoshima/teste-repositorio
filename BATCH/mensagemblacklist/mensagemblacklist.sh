#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/mensagemblacklist.`whoami`.log

echo Execucao....: $NOME > ${logfile}

# dos2unix

find ${HOME}/deploy/processos/mensagemblacklist/data -type f -name '*.[Tt][Xx][Tt]' -exec perl -i -pe 's/\r\n/\n/g' {} \;

# remove linhas em branco
find ${HOME}/deploy/processos/mensagemblacklist/data -type f -name '*.[Tt][Xx][Tt]' -exec perl -i -pe 's/^[\t\s\r]*$//g' {} \;

cd ${HOME}/deploy/processos/mensagemblacklist/bin
pwd >> ${logfile}

NOME_SYS=../log/mensagemblacklistSys_"`date +%Y%m%d-%H%M%S`.log"
exec 2>&1 >> ${NOME_SYS}

nohup ./mensagemblacklist > ../log/$NOME
retcode=$?

echo Fim execucao: $NOME >> ${logfile}

if [ $retcode == 0 ]
then
	echo "Processo executado com sucesso!"
	exit 0
else
	echo "Processo executado com erro/critica: $retcode"
	exit 1
fi
