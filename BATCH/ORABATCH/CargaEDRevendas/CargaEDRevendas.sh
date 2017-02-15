#!/bin/sh

IFS='
'
. ${HOME}/.profile
MYDIR=$(dirname $0)
cd $MYDIR
. ./CargaEDRevendas.cfg

DIR_LOG="./Log"
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

#find ./Data -type f -name '*.[Tt][Xx][Tt]' -exec perl -i -pe 's/\r\n/\n/g' {} \;

export NLS_LANG=AMERICAN_AMERICA.WE8ISO8859P1
echo ""
echo "*********************************************"
echo " step 1/8 - Carregando Trunc"
echo "*********************************************"
nohup sqlplus "$CONNSTR" @truncSapoio.sql >> "$DIR_LOG/truncSapoio_$DATA.log" 2>&1
retcode=`echo $?` 
case "$retcode" in 
0) echo "SQL executado com sucesso." ;; 
1) echo "SQL executado com erro EX_FAIL, ver logfile." ;; 
2) echo "SQL executado com erro EX_WARN, ver logfile." ;; 
3) echo "SQL executado com erro fatal." ;; 
*) echo "codigo de retorno desconhecido.";; 
esac
	
echo "*********************************************"
echo " step 2/8 - Carregando Tabela CLASSIFICACAO  "
echo "*********************************************"
nohup sqlldr "$CONNSTR" control=sapoio_classificacao.ctl log="$DIR_LOG/sapoio_classificacao_$DATA.log" >> "$DIR_LOG/sapoio_classificacao_$DATA.log" 2>&1
retcode=`echo $?` 
case "$retcode" in 
0) echo "SQL*Loader executado com sucesso." ;; 
1) echo "SQL*Loader executado com erro EX_FAIL, ver logfile." ;; 
2) echo "SQL*Loader executado com erro EX_WARN, ver logfile." ;; 
3) echo "SQL*Loader executado com erro fatal." ;; 
*) echo "codigo de retorno desconhecido.";; 
esac

echo ""
echo "*********************************************"
echo " step 3/8 - Carregando Tabela PARCEIRO       "
echo "*********************************************"
nohup sqlldr "$CONNSTR" control=sapoio_parceiro.ctl log="$DIR_LOG/sapoio_parceiro_$DATA.log" >> "$DIR_LOG/sapoio_parceiro_$DATA.log" 2>&1
retcode=`echo $?` 
case "$retcode" in 
0) echo "SQL*Loader executado com sucesso." ;; 
1) echo "SQL*Loader executado com erro EX_FAIL, ver logfile." ;; 
2) echo "SQL*Loader executado com erro EX_WARN, ver logfile." ;; 
3) echo "SQL*Loader executado com erro fatal." ;; 
*) echo "codigo de retorno desconhecido.";; 
esac

echo ""
echo "************************************************"
echo " step 4/8 - Carregando Tabela ENDERECOPARCEIRO  "
echo "************************************************"
nohup sqlldr "$CONNSTR" control=sapoio_enderecoparceiro.ctl log="$DIR_LOG/sapoio_enderecoparceiro_$DATA.log" >> "$DIR_LOG/sapoio_enderecoparceiro_$DATA.log" 2>&1
retcode=`echo $?` 
case "$retcode" in 
0) echo "SQL*Loader executado com sucesso." ;; 
1) echo "SQL*Loader executado com erro EX_FAIL, ver logfile." ;; 
2) echo "SQL*Loader executado com erro EX_WARN, ver logfile." ;; 
3) echo "SQL*Loader executado com erro fatal." ;; 
*) echo "codigo de retorno desconhecido.";; 
esac

echo ""
echo "**************************************************"
echo " step 5/8 - Carregando Tabela LINHAPARCEIRO       "
echo "**************************************************"
nohup sqlldr "$CONNSTR" control=sapoio_linhaparceiro.ctl log="$DIR_LOG/sapoio_linhaparceiro_$DATA.log" >> "$DIR_LOG/sapoio_linhaparceiro_$DATA.log" 2>&1
retcode=`echo $?` 
case "$retcode" in 
0) echo "SQL*Loader executado com sucesso." ;; 
1) echo "SQL*Loader executado com erro EX_FAIL, ver logfile." ;; 
2) echo "SQL*Loader executado com erro EX_WARN, ver logfile." ;; 
3) echo "SQL*Loader executado com erro fatal." ;; 
*) echo "codigo de retorno desconhecido.";; 
esac

echo ""
echo "***********************************************************"
echo " step 6/8 - Carregando Tabela LINHAINTERCEPTACAO"
echo "***********************************************************"
nohup sqlldr "$CONNSTR" control=sapoio_linhainterceptacao.ctl log="$DIR_LOG/sapoio_linhainterceptacao_$DATA.log" > "$DIR_LOG/sapoio_linhainterceptacao_$DATA.log" 2>&1
retcode=`echo $?` 
case "$retcode" in 
0) echo "SQL*Loader executado com sucesso." ;; 
1) echo "SQL*Loader executado com erro EX_FAIL, ver logfile." ;; 
2) echo "SQL*Loader executado com erro EX_WARN, ver logfile." ;; 
3) echo "SQL*Loader executado com erro fatal." ;; 
*) echo "codigo de retorno desconhecido.";; 
esac

echo ""
echo "***********************************************************"
echo " step 7/8 - Carregando Tabela HISTORICOLINHAPARCEIRO       "
echo "***********************************************************"
nohup sqlldr "$CONNSTR" control=sapoio_histlinhaparceiro.ctl log="$DIR_LOG/sapoio_histlinhaparceiro_$DATA.log" >> "$DIR_LOG/sapoio_histlinhaparceiro_$DATA.log" 2>&1
retcode=`echo $?` 
case "$retcode" in 
0) echo "SQL*Loader executado com sucesso." ;; 
1) echo "SQL*Loader executado com erro EX_FAIL, ver logfile." ;; 
2) echo "SQL*Loader executado com erro EX_WARN, ver logfile." ;; 
3) echo "SQL*Loader executado com erro fatal." ;; 
*) echo "codigo de retorno desconhecido.";; 
esac

echo ""
echo "***********************************************************"
echo " step 8/8 - Carregando Tabela HISTORICOLINHAINTERCEPTACAO  "
echo "***********************************************************"
nohup sqlldr "$CONNSTR" control=sapoio_histlinhainterceptacao.ctl log="$DIR_LOG/sapoio_histlinhainterceptacao_$DATA.log" >> "$DIR_LOG/sapoio_histlinhainterceptacao_$DATA.log" 2>&1
retcode=`echo $?` 
case "$retcode" in 
0) echo "SQL*Loader executado com sucesso." ;; 
1) echo "SQL*Loader executado com erro EX_FAIL, ver logfile." ;; 
2) echo "SQL*Loader executado com erro EX_WARN, ver logfile." ;; 
3) echo "SQL*Loader executado com erro fatal." ;; 
*) echo "codigo de retorno desconhecido.";; 
esac
echo ""

nohup sqlplus "$CONNSTR" @equaliza.sql /nolog 2>&1