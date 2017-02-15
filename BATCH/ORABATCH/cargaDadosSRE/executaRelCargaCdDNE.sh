#!/bin/sh

CFG='cargaDadosSRE.cfg'

DIR=$(dirname $0)

cd "$DIR"
. "${HOME}/.profile"
. "$DIR/$CFG"

NOME="`date +%Y%m%d-%H%M%S`.log"
logfile=$DIR/logRelCarga/relatorioCargaCdDNE.`whoami`.log

echo Execucao....: $NOME >> ${logfile}


for file in RelCargaCdDNETotal RelCargaCdDNECep RelCargaCdDNELimpeza; do \
	echo $file; \
	sqlplus -s $USER/$PASSWORD@$SID @$DIR/$file.sql; \

echo Fim execucao: $NOME >> ${logfile}

done