set timi on
set echo on
spool A_L1124.log

drop TABLE ATENDIMENTO.ATENDIMENTOANATELHISTORICO CASCADE CONSTRAINTS;


spool off
set echo off
set timi off