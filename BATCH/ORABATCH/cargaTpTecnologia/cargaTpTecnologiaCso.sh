#!/bin/sh

IFS='
'
. ${HOME}/.profile
MYDIR=$(dirname $0)
cd $MYDIR
. ./cargaTpTecnologia.cfg

DIR_LOG="./log"
###############################################
### Modo de captura Data
###############################################
DATA=`date '+%Y%m%d%H%M'` #Data + Ano, Mes, Dia, Hora, Minuto
###############################################
### string de conexao ao BD
###############################################
#CONNSTR=a5113943/a5113943@INTDEVA
CONNSTR="$USR/$PWD@$SID"
###############################################

export NLS_LANG=AMERICAN_AMERICA.WE8ISO8859P1
echo "*********************************************"
echo " step 1/7 - Carregando Tabela TMPTIPOTECNOLOGIA  "
echo "*********************************************"
nohup sqlldr "$CONNSTR" control=cargaTpTecnologiaCso.ctl log="$DIR_LOG/cargaTpTecnologiaCso_$DATA.log" >> "$DIR_LOG/cargaTpTecnologiaCso_$DATA.log" 2>&1
retcode=`echo $?` 
case "$retcode" in 
0) echo "SQL*Loader executado com sucesso." ;; 
1) echo "SQL*Loader executado com erro EX_FAIL, ver logfile." ;; 
2) echo "SQL*Loader executado com erro EX_WARN, ver logfile." ;; 
3) echo "SQL*Loader executado com erro fatal." ;; 
*) echo "codigo de retorno desconhecido.";; 
esac
