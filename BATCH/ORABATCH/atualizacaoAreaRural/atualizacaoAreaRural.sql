SET SQLPROMPT ''
SET FEEDBACK OFF
SET ECHO OFF
SET AUTOTRACE OFF
SET FLUSH ON
SET HEADING OFF
SET SERVEROUTPUT OFF
SET TIME OFF
SET TIMING OFF
SET TRIMOUT OFF
SET TERMOUT OFF
SET TRIMSPOOL OFF
SET PAUSE OFF
SET SHOWMODE OFF
SET SQLBLANKLINES OFF
SET SQLNUMBER OFF
SET TAB OFF
SET VERIFY OFF
SET WRAP OFF
SET LINESIZE 1024
SET NEWPAGE NONE
SET SKIP PAGE
SPOOL '&1' APPEND
SELECT NUM_CEP FROM LOAD_CEPAREARURAL WHERE NUM_CEP NOT IN (SELECT NUM_CEP FROM CEP);
SPOOL OFF
EXIT