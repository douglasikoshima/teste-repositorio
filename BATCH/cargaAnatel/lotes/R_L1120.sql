set timi on
set echo on
spool R_L1120.log

drop TABLE ATENDIMENTO.ATENDIMENTOANATELARQUIVO CASCADE CONSTRAINTS;


spool off
set echo off
set timi off