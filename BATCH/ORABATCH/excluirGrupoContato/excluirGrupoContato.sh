#!/bin/sh

IFS='
'
MYDIR=$(dirname $0)
cd $MYDIR
. ./excluirGrupoContato.cfg

echo $MYDIR
DIR_LOG="./log"
DATADIR="./data"
CTRLDIR="./ctrl"
FILEDATA1="data/palitos.csv"
###############################################
### Modo de captura Data
###############################################
DATA=`date '+%Y%m%d%H%M'` #Data + Ano, Mes, Dia, Hora, Minuto
###############################################
### string de conexao ao BD
###############################################
CONNSTR="$USR/$PWD@$SID"
#CONNSTR_INS="$USR2/$PWD2@$SID"
###############################################
export NLS_LANG=AMERICAN_AMERICA.WE8ISO8859P1

exec 1> $DIR_LOG/excluirGrupoContatoSys_$DATA.log
exec 2>> $DIR_LOG/excluirGrupoContatoSys_$DATA.log

echo "Limpando Arquivos " ${FILEDATA1}  ##" e " ${FILEDATA2} 

find ${HOME}/deploy/processos/ORABATCH/excluirGrupoContato/data/ -type f -name '*.[Cc][Ss][Vv]' -exec perl -i -pe 's/\r\n/\n/g' {} \;

find ${HOME}/deploy/processos/ORABATCH/excluirGrupoContato/data/ -type f -name '*.[Cc][Ss][Vv]' -exec perl -i -pe 's/^[\t\s\r]*$//g' {} \;

echo ""
echo "*********************************************"
echo " Limpando tabela "
echo "*********************************************"
sqlplus "$CONNSTR" @truncCargaContato.sql >> "$DIR_LOG/truncCargaContato$DATA.log" 2>&1
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
sqlldr "$CONNSTR" control=${CTRLDIR}/carga_grupo.ctl log="$DIR_LOG/carga_grupo_$DATA.log" >> "$DIR_LOG/carga_grupo_$DATA.log" 2>&1
retcode=`echo $?` 
case "$retcode" in 
0) echo "SQL*Loader executado com sucesso.";; 
1) echo "SQL*Loader executado com erro EX_FAIL, ver logfile." ;; 
2) echo "SQL*Loader executado com erro EX_WARN, ver logfile.";; 
3) echo "SQL*Loader executado com erro fatal." ;; 
*) echo "codigo de retorno desconhecido.";; 
esac

echo ""
echo "*********************************************"
echo " Carregando Contatos "
echo "*********************************************"
sqlplus "$CONNSTR" @insertContatos.sql >> "$DIR_LOG/insertContatos_$DATA.log" 2>&1
retcode=`echo $?` 
case "$retcode" in 
0) echo "SQL executado com sucesso." ;; 
1) echo "SQL executado com erro EX_FAIL, ver logfile." ;; 
2) echo "SQL executado com erro EX_WARN, ver logfile." ;; 
3) echo "SQL executado com erro fatal." ;; 
*) echo "codigo de retorno desconhecido.";; 
esac