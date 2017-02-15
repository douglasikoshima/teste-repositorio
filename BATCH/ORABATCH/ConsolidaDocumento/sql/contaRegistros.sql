set pages 0
set lines 5000
set trimspool on
set echo off
set termout off
set feed off
SET VERIFY OFF

WHENEVER SQLERROR EXIT SQLCODE ROLLBACK

spool ctDocumentos.txt

SELECT COUNT(*) FROM APOIO.CONSOLIDADOC WHERE INCONSOLIDA = 1 AND INDOCUMENTO = 2 ;

spool off

exit;