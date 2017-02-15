#!/bin/sh

IFS='
'
. ${HOME}/.profile
MYDIR=$(dirname $0)
cd $MYDIR
. ./relatorioSenhasGestor.cfg

EXECUTE="FALSE"
LENGTHBYTES=0
DATEFILE=`date '+%d%m%Y'`
DATADIR="./data"
SENDDIR="./enviado"
MAILDIR="./mail"
LOGDIR="./log"
ARQLOG="$LOGDIR/relatorioSenhasGestor_$DATEFILE`date '+%H%M%S'`.log"
CONNSTR="$USR/$PWD@$SID"

export NLS_LANG=AMERICAN_AMERICA.WE8ISO8859P1

echo `date '+%H'`':'`date '+%M'`':'`date '+%S'` >>$ARQLOG
echo "Validando Piloto validaPiloto.sql" >>$ARQLOG
PILOTO=`sqlplus -s $CONNSTR <<END
        set pagesize 0 feedback off verify off heading off echo off;
        @validaPiloto.sql
        exit
        END`
echo `date '+%H'`':'`date '+%M'`':'`date '+%S'` >>$ARQLOG
echo "Buscando Email emailEnvio.sql" >>$ARQLOG
TO=`sqlplus -s $CONNSTR <<END
    set pagesize 0 feedback off verify off heading off echo off;
    @emailEnvio.sql
    exit
    END`
echo `date '+%H'`':'`date '+%M'`':'`date '+%S'` >>$ARQLOG

## Valida extracao piloto ##
echo "Valida extracao piloto" >>$ARQLOG
if [ $PILOTO -eq 1 ]
then
    EXECUTE="TRUE SEMANAL"
	echo "Execucao semanal" >>$ARQLOG
else
    EXECUTE="TRUE MENSAL"
	echo "Execucao mensal" >>$ARQLOG
fi

echo `date '+%H'`':'`date '+%M'`':'`date '+%S'` >>$ARQLOG

if [ $EXECUTE != "FALSE" ]
then
		echo "Executando..." >>$ARQLOG
		
       		## relGestorAltSenha ##

       		echo `date '+%H'`':'`date '+%M'`':'`date '+%S'` >>$ARQLOG
       		echo "Executando relGestorAltSenha.sql" >>$ARQLOG

		if [ $EXECUTE = "TRUE SEMANAL" ]
		then
			sqlplus -s /nolog <<EOF
			connect $CONNSTR
			execute DBMS_APPLICATION_INFO.SET_MODULE('PM','spool relGestorAltSenha.txt');
			set echo off trimspool on pagesize 0 underline off feedback off verify off termout off timing off time off linesize 10000
			@@relGestorAltSenhaSemanal.sql
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
		else
			sqlplus -s /nolog <<EOF
			connect $CONNSTR
			execute DBMS_APPLICATION_INFO.SET_MODULE('PM','spool relGestorAltSenha.txt');
			set echo off trimspool on pagesize 0 underline off feedback off verify off termout off timing off time off linesize 10000
			@@relGestorAltSenhaMensal.sql
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
		fi

        	echo `date '+%H'`':'`date '+%M'`':'`date '+%S'` >>$ARQLOG
       		mv relGestorAltSenha.txt $DATADIR/relGestorAltSenha$DATEFILE.txt

		echo "Zipando arquivo a enviar" >>$ARQLOG
		cp $DATADIR/relGestorAltSenha$DATEFILE.txt $SENDDIR/relGestorAltSenha$DATEFILE.txt
		gzip -9 -f $SENDDIR/relGestorAltSenha$DATEFILE.txt

		#Logica para validar o tamanho do arquivo
                LENGTHBYTES=`du -Hsm $DATADIR/relGestorAltSenha$DATEFILE.txt`
                LENGTHBYTES=${LENGTHBYTES%	*};
		LENGTHBYTES=${LENGTHBYTES%.*};
		echo "Tamanho do arquivo $LENGTHBYTES MegaBytes" >>$ARQLOG

       		if [ LENGTHBYTES -le 25 ]
		then
			uuencode $SENDDIR/relGestorAltSenha$DATEFILE.txt.gz relGestorAltSenha$DATEFILE.zip > $MAILDIR/relGestorAltSenha_uuencode.txt
			cat $MAILDIR/conteudoMailAltAnexo.txt $MAILDIR/relGestorAltSenha_uuencode.txt > $MAILDIR/bodymail.txt
			mailx -s "Relatorio relGestorAltSenha" $TO < $MAILDIR/bodymail.txt
			echo "Copiando arquivo enviado" >>$ARQLOG
		#else
		#	cat $MAILDIR/conteudoMailAlt.txt > $MAILDIR/bodymail.txt
		#	mailx -s "Relatorio relGestorAltSenha" $TO < $MAILDIR/bodymail.txt
		#	echo "Copiando arquivo enviado" >>$ARQLOG
		fi

       		## relGestorNAltSenha ##

       		echo `date '+%H'`':'`date '+%M'`':'`date '+%S'` >>$ARQLOG
       	 	echo "Executando relGestorNAltSenha.sql" >>$ARQLOG

		if [ $EXECUTE = "TRUE SEMANAL" ]
		then
			sqlplus -s /nolog <<EOF
			connect $CONNSTR
			execute DBMS_APPLICATION_INFO.SET_MODULE('PM','spool relGestorNAltSenha.txt');
			set echo off trimspool on pagesize 0 underline off feedback off verify off termout off timing off time off linesize 10000
			@@relGestorNAltSenhaSemanal.sql
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
		else
			sqlplus -s /nolog <<EOF
			connect $CONNSTR
			execute DBMS_APPLICATION_INFO.SET_MODULE('PM','spool relGestorNAltSenha.txt');
			set echo off trimspool on pagesize 0 underline off feedback off verify off termout off timing off time off linesize 10000
			@@relGestorNAltSenhaMensal.sql
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
		fi

        	echo `date '+%H'`':'`date '+%M'`':'`date '+%S'` >>$ARQLOG
        	mv relGestorNAltSenha.txt $DATADIR/relGestorNAltSenha$DATEFILE.txt
		
		echo "Zipando arquivo a enviar" >>$ARQLOG
		cp $DATADIR/relGestorNAltSenha$DATEFILE.txt $SENDDIR/relGestorNAltSenha$DATEFILE.txt
		gzip -9 -f $SENDDIR/relGestorNAltSenha$DATEFILE.txt
		
		#Logica para validar o tamanho do arquivo
		LENGTHBYTES=`du -Hsm $DATADIR/relGestorAltSenha$DATEFILE.txt`
		LENGTHBYTES=${LENGTHBYTES%	*}
		LENGTHBYTES=${LENGTHBYTES%.*}
		echo "Tamanho do arquivo $LENGTHBYTES MegaBytes" >>$ARQLOG

        	if [ LENGTHBYTES -le 25 ]
		then
			uuencode $SENDDIR/relGestorNAltSenha$DATEFILE.txt.gz relGestorNAltSenha$DATEFILE.zip > $MAILDIR/relGestorNAltSenha_uuencode.txt
			cat $MAILDIR/conteudoMailNAltAnexo.txt $MAILDIR/relGestorNAltSenha_uuencode.txt > $MAILDIR/bodymail.txt
			mailx -s "Relatorio relGestorNAltSenha" $TO < $MAILDIR/bodymail.txt
			echo "Copiando arquivo enviado" >>$ARQLOG
		#else
		#	cat $MAILDIR/conteudoMailNAlt.txt $MAILDIR/relGestorNAltSenha_uuencode.txt > $MAILDIR/bodymail.txt
		#	mailx -s "Relatorio relGestorNAltSenha" $TO < $MAILDIR/bodymail.txt
		#	echo "Copiando arquivo enviado" >>$ARQLOG
		fi
else
	echo "Nao Executado..." >>$ARQLOG
fi

echo "Processo finalizado ..." >>$ARQLOG
echo `date '+%H'`':'`date '+%M'`':'`date '+%S'` >>$ARQLOG
