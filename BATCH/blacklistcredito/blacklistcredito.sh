#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/blacklistcredito.`whoami`.log

# dos2unix
find ${HOME}/deploy/processos/blacklistcredito/data -type f -name '*.[Tt][Xx][Tt]' -exec perl -i -pe 's/\r\n/\n/g' {} \;

# remove linhas em branco
find ${HOME}/deploy/processos/blacklistcredito/data -type f -name '*.[Tt][Xx][Tt]' -exec perl -i -pe 's/^[\t\s\r]*$//g' {} \;

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/blacklistcredito/bin
pwd >> ${logfile}

NOME_SYS=../log/blacklistcreditoSys_"`date +%Y%m%d-%H%M%S`.log"
exec 2>&1 >> ${NOME_SYS}

nohup ./blacklistcredito > ../log/$NOME
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
