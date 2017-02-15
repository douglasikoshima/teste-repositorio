set timi on
set echo on
spool R_L1116.log

ALTER TABLE 
   CONTATOADM.CONTATOFOLHA
DROP 
   (
      INANATEL
   );

commit;





spool off
set echo off
set timi off