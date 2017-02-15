#!/bin/sh

. ./CargaSubSegmentacao.cfg

prc_atualizasubsegmentacao() 
{
sqlplus "$DB_USER/$DB_PASS@$DB_NAME" <<endl
	SET SQLPROMPT ''
	SET FEEDBACK OFF
	SET ECHO OFF
	SET AUTOTRACE OFF
	SET FLUSH ON
	SET HEADING OFF
	SET SERVEROUTPUT OFF
	SET TIME OFF
	SET TIMING OFF
	SET TRIMOUT ON
	SET TERMOUT ON
	SET TRIMSPOOL ON
	SET PAUSE OFF
	SET SHOWMODE OFF
	SET SQLBLANKLINES OFF
	SET SQLNUMBER OFF
	SET TAB OFF
	SET VERIFY OFF
	SET WRAP OFF
	SET LINESIZE 512
	SET NEWPAGE NONE
	
	VARIABLE P_PARTICAO      NUMBER
	VARIABLE P_IDUSUARIO     NUMBER
	VARIABLE O_PROCESSADO    NUMBER
	VARIABLE O_SUCESSO       NUMBER
	
	EXEC :P_PARTICAO := '$1';
	EXEC :P_IDUSUARIO := '$2';
	
	EXEC LOAD.PRC_ATUALIZASUBSEGMENTACAO(P_PARTICAO => :P_PARTICAO, P_IDUSUARIO => :P_IDUSUARIO, O_PROCESSADO => :O_PROCESSADO, O_SUCESSO => :O_SUCESSO);
	
	exit
	
endl
return $?
}


(prc_atualizasubsegmentacao 1 33374) &
(prc_atualizasubsegmentacao 2 33374) &
(prc_atualizasubsegmentacao 3 33374) &
(prc_atualizasubsegmentacao 4 33374) &
(prc_atualizasubsegmentacao 5 33374) &
(prc_atualizasubsegmentacao 6 33374) &
(prc_atualizasubsegmentacao 7 33374) &
(prc_atualizasubsegmentacao 8 33374) &

wait 

