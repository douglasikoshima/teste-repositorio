#!/bin/sh

IFS='
'
. ${HOME}/.profile
MYDIR=$(dirname $0)
cd $MYDIR
. ./arvoreVendas.cfg

DIR_LOG="./log"
DATADIR="./data"
CTRLDIR="./ctrl"
###############################################
### Modo de captura Data
###############################################
DATA=`date '+%Y%m%d%H%M'` #Data + Ano, Mes, Dia, Hora, Minuto
###############################################
### string de conexao ao BD
###############################################
#CONNSTR="STA_MIG/STA_MIG@INTDEVA"
CONNSTR="$USR/$PWD@$SID"
###############################################
export NLS_LANG=AMERICAN_AMERICA.WE8ISO8859P1
#export NLS_LANG=BRAZILIAN PORTUGUESE_BRAZIL.WE8ISO8859P1


echo ""
echo "*********************************************"
echo " Limpando a Tabela "
echo "*********************************************"
nohup sqlplus "$CONNSTR" @truncCargaContato.sql >> "$DIR_LOG/truncCargaContato_$DATA.log" 2>&1
retcode=`echo $?` 
case "$retcode" in 
0) echo "SQL executado com sucesso." ;; 
1) echo "SQL executado com erro EX_FAIL, ver logfile." ;; 
2) echo "SQL executado com erro EX_WARN, ver logfile." ;; 
3) echo "SQL executado com erro fatal." ;; 
*) echo "codigo de retorno desconhecido.";; 
esac

echo "*********************************************"
echo " step 1/2 - Carregando Tabela CARGACONTATO"
echo "*********************************************"
nohup sqlldr "$CONNSTR" control=${CTRLDIR}/arvoreVendas_contato.ctl log="$DIR_LOG/arquivo_contato_$DATA.log" >> "$DIR_LOG/arquivo_contato_$DATA.log" 2>&1
retcode=`echo $?` 
case "$retcode" in 
0) echo "SQL*Loader executado com sucesso.";; 
1) echo "SQL*Loader executado com erro EX_FAIL, ver logfile." ;; 
2) echo "SQL*Loader executado com erro EX_WARN, ver logfile.";; 
3) echo "SQL*Loader executado com erro fatal." ;; 
*) echo "codigo de retorno desconhecido.";; 
esac


echo "*********************************************"
echo " step 2/2 - Carregando Tabela GRUPO"
echo "*********************************************"
nohup sqlldr "$CONNSTR" control=${CTRLDIR}/arvoreVendas_grupo.ctl log="$DIR_LOG/arquivo_grupo_$DATA.log" >> "$DIR_LOG/arquivo_grupo_$DATA.log" 2>&1
retcode=`echo $?` 
case "$retcode" in 
0) echo "SQL*Loader executado com sucesso.";; 
1) echo "SQL*Loader executado com erro EX_FAIL, ver logfile." ;; 
2) echo "SQL*Loader executado com erro EX_WARN, ver logfile.";; 
3) echo "SQL*Loader executado com erro fatal." ;; 
*) echo "codigo de retorno desconhecido.";; 
esac
