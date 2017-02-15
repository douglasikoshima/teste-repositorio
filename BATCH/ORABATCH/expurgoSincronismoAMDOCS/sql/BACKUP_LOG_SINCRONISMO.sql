/***********************************************************
#  BATCH......: expurgoSincronismoAMDOCS
#  Criado por.: Lucas Gomes Fenelon de Moraes (12/04/2016)
#  Funcional..: Erika Brum
***********************************************************/
SET SQLPROMPT '';
SET TERMOUT OFF;
SET FEEDBACK OFF;
SET ECHO OFF;
SET AUTOTRACE OFF;
SET FLUSH ON;
SET HEADING OFF;
SET SERVEROUTPUT ON SIZE UNLIMITED;
SET TIME OFF;
SET TIMING OFF;
SET TRIMOUT ON;
SET TRIMSPOOL ON;
SET PAUSE OFF;
SET SHOWMODE OFF;
SET SQLBLANKLINES OFF;
SET SQLNUMBER OFF;
SET TAB OFF;
SET VERIFY OFF;
SET WRAP OFF;
SET FEED OFF;
SET NEWPAGE NONE;
SET LINESIZE 32767;
set echo off trimspool on pagesize 0 underline off feedback off verify off termout off timing off time off linesize 4000;
SPOOL &1;
	SELECT IDLOGSINCRONISMO
		   || '|' || IDFILA
		   || '|' || IDSISTEMAORIGEM
		   || '|' || SGSISTEMAORIGEM
		   || '|' || TO_CHAR(PARAMSISTEMAORIGEM)
		   || '|' || PARAMSISTEMAORIGEMVL
		   || '|' || TO_CHAR(PARAMCHAVE)
		   || '|' || PARAMCHAVEVL
		   || '|' || INTERFACETYPE
		   || '|' || INTERFACENAME
		   || '|' || DSMSG
		   || '|' || SGMSGTYPE
		   || '|' || DTLOG
	  FROM CONVERGENTE_OW.LOG_SINCRONISMO
     WHERE IDFILA IN 
		(SELECT IDFILAAMDOCS 
		   FROM CONVERGENTE_OW.FILASINCRONISMOAMDOCS 
		  WHERE DTULTIMAALTERACAO < TO_DATE('&2', 'DDMMYYYY') - 
             (
			  TO_NUMBER((SELECT DSVALORPARAMETRO 
						   FROM APOIO.PARAMETRO 
						  WHERE CDPARAMETRO = 'QTD_MES_EXPURGO_FILA_SINCRONISMO_AMDOCS' 
						    AND ROWNUM < 2
					   )) * 30
			 )
		);
SPOOL OFF
SET ECHO OFF
SET TIMI OFF
SET TIME OFF
EXIT