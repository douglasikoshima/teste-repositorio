/***********************************************************
  BATCH......: meuVivoDW
  Criado por.: Eder Jani Martins (11/02/2015)
  Alterado por: Lucas Gomes(03/09/2015)
  Funcional..: Marcia Santana Silva
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
SELECT TRANSLATE(
		DSNOME||';'||
		DSEMAIL||';'||
		NRDOCUMENTO||';'||
		CDAREAREGISTRO||';'||
		NRLINHA||';'||
		SGSERVICO||';'|| 
		DSSERVICO||';'|| 
		(CASE 
	         WHEN SGSERVICO LIKE ('%DESATIVACAO') THEN '4'
	         WHEN SGSERVICO LIKE ('%ATIVACAO') THEN '3'
		 WHEN SGSERVICO LIKE ('ContatoCMP%') THEN '2'
	         ELSE '1'
                END) ||';'|| 
		IDCANAL||';'||
		NMCANAL||';'||
		INCONSULTATRAFEGODADOS||';'||
		INCONTATOSALDOPONTO||';'||
		INCONTATOATENDIMENTOAGENDADO||';'||
        	TO_CHAR(DTABERTURA, 'DD/MM/YYYY HH24:MI:SS')||';'||
        	TO_CHAR(DTULTIMAALTERACAO, 'DD/MM/YYYY HH24:MI:SS')||';'||
		IDATENDIMENTO||';'||
		IDATENDIMENTOPROTOCOLO||';'||
		NMPATH,
		'·ÈÌÛ˙‡ËÏÚ˘‚ÍÓÙ˚„ıÒ¸Á¡…Õ”⁄A»Ã“Ÿ¬ Œ‘€√’—‹«',
		'aeiouaeiouaeiouaonucAEIOUAEIOUAEIOUAONUC') AS LINHA
   FROM LOAD.MEUVIVODW_02;
SPOOL OFF
SET ECHO OFF
SET TIMI OFF
SET TIME OFF
EXIT
