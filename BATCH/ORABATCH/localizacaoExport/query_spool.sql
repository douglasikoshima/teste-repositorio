whenever sqlerror exit 1;

set echo off
set pages 5
set feedback off
set trimspool on
set heading off
set lines 32767
set arraysize 100
set timing off
set time off
set flush off
set term off
set show off
SET VERIFY OFF

alter session force parallel ddl;

alter session force parallel dml;

alter session force parallel query;

spool export.apoio.localidadeosb7.txt

SELECT CDLOCALIDADE || ';' || 
       NMLOCALIDADE || ';' || 
       SGUF  || ';' || 
       NMMUNICIPIO  || ';' || 
       CDAREAREGISTRO || ';' || 
	   IDUSUARIOALTERACAO || ';' || 
       to_char(DTULTIMAALTERACAO,'dd/mm/yyyy hh24:mi:ss') || ';' || 
       SGLOCALIDADE || ';'
FROM APOIO.localidadeosb7;

spool off

exit
/
