# Habilita modo DEBUG
#exec 1>> $DIR_LOG/`date +%Y%m%d-%H%M%S`.debug
#exec 2>> $DIR_LOG/`date +%Y%m%d-%H%M%S`.debug

#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/cancelamentoProdutoPF.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/cancelamentoProdutoPF/bin
pwd >> ${logfile}

NOME_SYS=../log/cancelamentoProdutoPFSys_"`date +%Y%m%d-%H%M%S`.log"

exec 2>&1 >> ${NOME_SYS}

nohup ./cancelamentoProdutoPF >> ../log/$NOME
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
