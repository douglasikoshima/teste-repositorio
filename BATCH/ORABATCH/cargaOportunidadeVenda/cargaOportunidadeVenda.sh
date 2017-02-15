#!/bin/sh

IFS='
'
. ${HOME}/.profile
MYDIR=$(dirname $0)
cd $MYDIR
. ./cargaOportunidadeVenda.cfg

DATE=`date '+%d%m%Y`
CONNSTR="$USR/$PWD@$SID"
DIR_RECEIVED='./received'
DIR_PROCESSED='./processed'
DIR_REJECTED='./rejected'
DIR_LOG='./log'
RECEIVED_IS_EMPTY=`echo $DIR_RECEIVED ls | wc -l`

if [ $RECEIVED_IS_EMPTY -ne 0 ]
then
	export NLS_LANG=AMERICAN_AMERICA.WE8ISO8859P1
	
	FILE_LOAD="0"

	for filename in ./received/*.txt
	do 
		FILE_LOAD=$filename
	done

	FILE_NAME="cargaOportunidadeVenda_$DATE"
	FILE_LOAD_LOG="$DIR_LOG/$FILE_NAME.log"
	FILE_LOAD_BAD="$DIR_REJECTED/$FILE_NAME.bad"
	
	#Permissao no arquivo novo
	chmod 0777 $FILE_LOAD
	
	#Convertendo para formato UNIX
	#perl -i -pe 's/\r\n/\n/g' $FILE_LOAD
	
	echo "*********************************************"
	echo " step 1/4 - Carregando Oportunidade Venda"
	echo "*********************************************"
	nohup sqlldr "$CONNSTR" control=cargaOportunidadeVenda.ctl data="$FILE_LOAD" log="$FILE_LOAD_LOG" bad="$FILE_LOAD_BAD" >> "$DIR_LOG/cargaOportunidadeVenda_$DATE.log" 2>&1
	retcode=`echo $?` 
	case "$retcode" in 
	0) echo "SQL*Loader executado com sucesso." ;; 
	1) echo "SQL*Loader executado com erro EX_FAIL, ver logfile." ;; 
	2) echo "SQL*Loader executado com erro EX_WARN, ver logfile." ;; 
	3) echo "SQL*Loader executado com erro fatal." ;; 
	*) echo "codigo de retorno desconhecido.";; 
	esac

	mv $DIR_RECEIVED/*.txt $DIR_PROCESSED/cargaOportunidadeVenda_$DATE.txt

	echo ""
	echo "*********************************************"
	echo " step 2/4 - Carregando Tabelas"
	echo "*********************************************"
	nohup sqlplus "$CONNSTR" @manterCargaOportunidade.sql >> "$DIR_LOG/manterCargaOportunidade_$DATE.log" 2>&1
	retcode=`echo $?` 
	case "$retcode" in 
	0) echo "SQL executado com sucesso." ;; 
	1) echo "SQL executado com erro EX_FAIL, ver logfile." ;; 
	2) echo "SQL executado com erro EX_WARN, ver logfile." ;; 
	3) echo "SQL executado com erro fatal." ;; 
	*) echo "codigo de retorno desconhecido.";; 
	esac
	
	echo ""
	echo "*********************************************"
	echo " step 3/4 - Carregando rejeitados"
	echo "*********************************************"
	nohup sqlplus -s /nolog <<EOF
			connect $CONNSTR
			execute DBMS_APPLICATION_INFO.SET_MODULE('CARTPROSPECCAOVENDA_LDR','spool cargaOportunidadeVenda.txt');
			set echo off trimspool on pagesize 0 underline off feedback off verify off termout off timing off time off
			@@rejectedsCargaOportunidade.sql
			exit
EOF
	retcode=$?
	case "$retcode" in
	0) echo "SQL executado com sucesso." ;;
	1) echo "SQL executado com erro EX_FAIL, ver logfile." ;;
	2) echo "SQL executado com erro EX_WARN, ver logfile." ;;
	3) echo "SQL executado com erro fatal." ;;
	*) echo "codigo de retorno desconhecido.";;
	esac
	
	mv cargaOportunidadeVenda.txt $DIR_REJECTED/cargaOportunidadeVenda$DATE.txt
	
	echo ""
	echo "*********************************************"
	echo " step 4/4 - Carregando Trunc"
	echo "*********************************************"
	nohup sqlplus "$CONNSTR" @truncCargaOpotunidade.sql >> "$DIR_LOG/truncCargaOpotunidade_$DATE.log" 2>&1
	retcode=`echo $?` 
	case "$retcode" in 
	0) echo "SQL executado com sucesso." ;; 
	1) echo "SQL executado com erro EX_FAIL, ver logfile." ;; 
	2) echo "SQL executado com erro EX_WARN, ver logfile." ;; 
	3) echo "SQL executado com erro fatal." ;; 
	*) echo "codigo de retorno desconhecido.";; 
	esac
fi
