set timi on
set echo on
spool R_L1174.log

delete from APOIO.PRESTADORAANATEL ;
commit;

spool off
set echo off
set timi off
