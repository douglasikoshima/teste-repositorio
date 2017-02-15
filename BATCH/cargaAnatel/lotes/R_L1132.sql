set timi on
set echo on
spool R_L1132.log

drop TABLE ATENDIMENTO.ATENDIMENTOANATELATIVIDADE CASCADE CONSTRAINTS;


spool off
set echo off
set timi off