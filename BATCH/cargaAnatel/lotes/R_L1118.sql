set timi on
set echo on
spool R_L1118.log

DROP SEQUENCE ATENDIMENTO.ATENDIMENTOANATELARQUIVOSQ;
commit;




spool off
set echo off
set timi off