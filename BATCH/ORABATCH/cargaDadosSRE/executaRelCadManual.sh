#!/bin/sh

CFG='cargaDadosSRE.cfg'

DIR=$(dirname $0)

cd "$DIR"
. "${HOME}/.profile"
. "$DIR/$CFG"

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=$DIR/logCadManual/relatorioCadManual.`whoami`.log

echo Execucao....: $NOME >> ${logfile}


for file in RelCadManualTotal RelCadManualLogradouro RelCadManualBairro RelCadManualCep RelCadManualLocalidade; do \
	echo $file; \
	sqlplus -s $USER/$PASSWORD@$SID @$DIR/$file.sql; \

echo Fim execucao: $NOME >> ${logfile}

done
