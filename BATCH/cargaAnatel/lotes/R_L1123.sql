set timi on
set echo on
spool R_L1123.log

drop TABLE ATENDIMENTO.ATENDIMENTOANATELDADOS CASCADE CONSTRAINTS;

spool off
set echo off
set timi off