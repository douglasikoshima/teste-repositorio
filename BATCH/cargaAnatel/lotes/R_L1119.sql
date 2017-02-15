set timi on
set echo on
spool R_L1119.log

ALTER TABLE 
   ATENDIMENTO.ATENDIMENTO
DROP 
   (
      INANATEL,IDATENDIMENTOANATEL
   );
   
commit;


spool off
set echo off
set timi off