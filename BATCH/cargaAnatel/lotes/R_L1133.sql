set timi on
set echo on
spool R_L1133.log

drop TABLE ATENDIMENTO.ATENDIMENTOANATELATVANEXO CASCADE CONSTRAINTS;


spool off
set echo off
set timi off