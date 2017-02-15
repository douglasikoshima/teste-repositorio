#!/bin/sh

SQL='relatorioCarteiraProspect.sql'
CFG='relatorioCarteiraProspect.cfg'

IFS='
'

EXE=$(basename $0)
DIR=$(dirname $0)

DTM=$(date '+%d%m%Y-%H%M%S')
LOG="log/$DTM.log"

cd "$DIR"
. "${HOME}/.profile"
. "$DIR/$CFG"

# Locale oracle
export NLS_LANG=AMERICAN_AMERICA.WE8ISO8859P1

export USR
export PWD
export SID

ORAUTH="$USR/$PWD@$SID"
DATA=`date '+%d%m%Y'`

nohup sqlplus "$ORAUTH" "@relatorioCarteiraProspect.sql" "relCarteiraProspect_$DATA.txt" >> "log/relatorioCarteiraProspect-$DATA.log" 2>&1

exit 0