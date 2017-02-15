#!/bin/sh

IFS='
'
. ${HOME}/.profile

MYDIR=$(dirname $0)
cd $MYDIR
. ./cfg/ConsolidaDocumento.cfg

DIR_LOG="./log"
DIR_SQL="./sql"
DIR_CTL="./ctl"

DATA=`date '+%Y%m%d%H%M'`
ARQLOG="$DIR_LOG/ConsolidaDocumento-$DATA.log"

v_thread_limit=16   # valor default, caso nao obtenha parametrizacao do banco
ct_thread=1   #Contador de threads, deixo assim para inicializar o LOOP de limite das threads
nrThreads=1
ctInstancias=0

arquivo=ctDocumentos.txt              # arquivo com a qtde. de documentos a serem consolidados
V_SQL_FILE_NAME=nrDocumentos.sql      # Query para consulta dos documentos a serem consolidados
V_NAME_ARQ_SPOOL=nrDocumentos.lst     # arquivo com todos os documentos que serao consolidados
param_nr_thread=param_nr_thread.ctl   # arquivo gerado com o parametro maximo de threads de execucao

# Locale oracle
export NLS_LANG=AMERICAN_AMERICA.WE8ISO8859P1

#sqlplus $USR/$PWD@$SID @$DIR_SQL/ConsolidaDocumento.sql "$DIR_LOG/ConsolidaDocumento-$DATA.spool" >>$ARQLOG 2>&1


$ORACLE_HOME/bin/sqlplus -s $USR/$PWD@$SID @$DIR_SQL/consulta_parametro.sql $param_nr_thread /nolog &
pid=$!
wait ${pid}   # Parametriza o limite de threads para execucao
export v_thread_limit=`cat $param_nr_thread`   # Guarda na variavel nrThreads, o a qtde de registros obtidos

$ORACLE_HOME/bin/sqlplus -s $USR/$PWD@$SID @$DIR_SQL/contaRegistros.sql /nolog &
pid=$!
wait ${pid}   # Aguarda a contagem dos registros e geracao do arquivo
export nrThreads=`cat $arquivo`   # Guarda na variavel nrThreads, o a qtde de registros obtidos

if [ $nrThreads -gt 0 ]; then   # Se for maior que ZERO, existem documentos a serem consolidados

   $ORACLE_HOME/bin/sqlplus -s $USR/$PWD@$SID @$DIR_SQL/$V_SQL_FILE_NAME $V_NAME_ARQ_SPOOL /nolog &
   pid=$!
   wait ${pid}   # Aguarda a geracao dos documentos a serem consolidados
   
   cat $V_NAME_ARQ_SPOOL | while read v_documento; do

        while [ $ct_thread -gt 0 ]
        do
            if [ $ct_thread -lt $v_thread_limit ]; then   # Aqui eh instanciado no maximo, o limite de v_thread_limit
                ctInstancias=`expr $ctInstancias + 1`   # Acrescenta 1 no contador de threads
                echo Iniciando a consulta do documento:
                echo $v_documento
                $ORACLE_HOME/bin/sqlplus -s $USR/$PWD@$SID @$DIR_SQL/consolida_cnpj.sql $v_documento /nolog &
                ct_thread=`jobs | wc -l`
                echo $ct_thread
                break
            elif [ $ct_thread -eq $v_thread_limit ]; then   # Atingiu o limite de instancias, aguarda 3 seg e retoma o o processo
                echo Atingiu o limite de threads, aguardando 30 segundos
                sleep 30
                ct_thread=`jobs | wc -l`
                continue
            fi
        done

   done
   echo Fim de Processamento
   echo Total de Instancias realizadas
   echo $ctInstancias
   
fi

$ORACLE_HOME/bin/sqlplus -s $USR/$PWD@$SID @$DIR_SQL/ConsolidaDocumento.sql "$DIR_LOG/ConsolidaDocumento-$DATA.spool" >>$ARQLOG 2>&1


exit 0
