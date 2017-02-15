set timi on
set echo on
spool A_L1115.log


ALTER TABLE 
   ATENDIMENTO.ATENDIMENTOPRIORIZACAO
DROP 
   (
      INANATEL,IDATENDIMENTOANATEL 
   );
   
COMMIT;



spool off
set echo off
set timi off
