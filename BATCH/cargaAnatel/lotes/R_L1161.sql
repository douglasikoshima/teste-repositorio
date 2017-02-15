set timi on
set echo on
spool R_L1161.log

ALTER TABLE INFRA.ARQUIVOFUNCIONALIDADE DROP (NMARQUIVOORIGINAL);

spool off
set echo off
set timi off
