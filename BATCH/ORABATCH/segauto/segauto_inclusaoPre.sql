SET PAGESIZE 0
SET FEEDBACK OFF
SET VERIFY OFF
SET HEADING OFF
SET ECHO OFF
SET COLSEP "|"
SET TAB OFF
SET PAGESIZE 0
SET TRIMS ON
SET WRAP OFF
SET SERVEROUTPUT ON
WHENEVER OSERROR EXIT 9
WHENEVER SQLERROR EXIT SQL.SQLCODE

ALTER SESSION FORCE PARALLEL DML PARALLEL 64
ALTER SESSION SET COMMIT_LOGGING = 'IMMEDIATE'
ALTER SESSION SET COMMIT_WAIT = NOWAIT

DECLARE
    V_DATA_CRITERIO VARCHAR2(255);
	V_ROWCOUNT NUMBER;
BEGIN
	
	V_DATA_CRITERIO := '&1';

	INSERT INTO CUSTOMER.CARGAPROGRAMARELACIONAMENTO
	SELECT /*+ PARALLEL (64) */
	       NVL (PRPRE.IDPESSOAENDERECO, '00000000000') AS NRDOCUMENTO,
		   PRPRE.NRLINHA,
		   1 INPR,
		   NVL (DTCADASTRO, SYSDATE) DTCADASTROPR,
		   NVL (DTULTIMAATUALIZACAO, SYSDATE) DTATUALIZACAOPR, 
		   1 INCADASTROMANUAL,
		   PRPRE.IDUSUARIOATUALIZACAO NMLOGINATUALIZACAOPR, 
		   9 IDSISTEMAORIGEMPR,
		   NULL NRLINHACONTATO, 
		   NULL NMLOGINATUALIZACAOLC,
		   NULL IDSISTEMAATUALIZACAOLC
	  FROM CUSTOMER.PROGRAMARELACIONAMENTOPRECTRL PRPRE
	 WHERE TRUNC(PRPRE.DTCADASTRO) = TO_DATE(V_DATA_CRITERIO, 'DD/MM/YYYY')
		AND NOT EXISTS (SELECT 1
						 FROM CUSTOMER.CARGAPROGRAMARELACIONAMENTO C
						WHERE C.NRTELEFONE = PRPRE.NRLINHA
						  AND C.NRDOCUMENTO = PRPRE.IDPESSOAENDERECO);

	V_ROWCOUNT:=SQL%ROWCOUNT;
	COMMIT;
	DBMS_OUTPUT.PUT_LINE('Total de linhas inluidas INCLUSAO PRE: '||V_ROWCOUNT);
END;
/

SET ECHO OFF
SET TIMI OFF
SET TIME OFF
EXIT