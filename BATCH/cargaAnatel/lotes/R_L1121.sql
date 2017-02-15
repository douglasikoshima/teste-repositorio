set timi on
set echo on
spool R_L1121.log

drop table  ATENDIMENTO.ATENDIMENTOANATEL CASCADE CONSTRAINTS;
commit;

spool off
set echo off
set timi off