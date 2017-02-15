set timi on
set echo on
spool A_L1161.log

ALTER TABLE INFRA.ARQUIVOFUNCIONALIDADE ADD (NMARQUIVOORIGINAL VARCHAR2(255));

spool off
set echo off
set timi off
