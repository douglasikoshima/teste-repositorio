#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/sincronismoPP.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/sincronismoPP/bin
pwd >> ${logfile}

#---------------------------------------------------
# Controle de execucao paralela
#---------------------------------------------------
echo "Verificacao de lock" >> ${logfile}
if [ -f sincronismoPP.lock ] ; then
    if [ "$(ps -p `cat ./sincronismoPP.lock` | wc -l)" -gt 1 ]; then
        echo "ERRO - Fim da rotina, outro processo ainda rodando - `cat ./sincronismoPP.lock`" >> ${logfile}
        exit 0
    else
        echo "O programa nao esta em execucao, removendo lock indevido" >> ${logfile}
        rm -f sincronismoPP.lock
    fi
fi

echo "Criando lock para controle de processo paralelo" >> ${logfile}
trap "rm -f sincronismoPP.lock; exit" INT TERM EXIT
echo $$> sincronismoPP.lock
#---------------------------------------------------

NOME_SYS=../log/sincronismoPPSys_"`date +%Y%m%d-%H%M%S`.log"
exec 2>&1 >> ${NOME_SYS}

 ./sincronismoPP > ../log/$NOME
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
