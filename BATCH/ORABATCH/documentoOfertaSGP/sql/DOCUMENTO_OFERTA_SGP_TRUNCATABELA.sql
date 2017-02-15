/***********************************************************
  BATCH......: documentoOfertaSGP 
  Criado por.: Lucas Gomes (29/09/2015)
  Funcional..: Dener Neves dos Santos
***********************************************************/
SET PAGESIZE 0
SET FEEDBACK OFF
SET VERIFY OFF
SET HEADING OFF
SET ECHO OFF
SET TAB OFF
SET PAGESIZE 0
SET TRIMS ON
SET WRAP OFF
SET SERVEROUTPUT OFF
WHENEVER OSERROR EXIT 9
WHENEVER SQLERROR EXIT SQL.SQLCODE

BEGIN
	CUSTOMER.PRC_TRUNC_DOCUMENTOOFERTASGP;
END;
/

SET ECHO OFF
SET TIMI OFF
SET TIME OFF
EXIT
