# Habilita modo DEBUG
#exec 1>> $DIR_LOG/`date +%Y%m%d-%H%M%S`.debug
#exec 2>> $DIR_LOG/`date +%Y%m%d-%H%M%S`.debug

#!/bin/sh

. ${HOME}/.profile

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=/tmp/CancelAutomaticoPF.`whoami`.log

echo Execucao....: $NOME > ${logfile}

cd ${HOME}/deploy/processos/CancelAutomaticoPF/bin
pwd >> ${logfile}

nohup ./CancelAutomaticoPF > ../log/$NOME

echo Fim execucao: $NOME >> ${logfile}
