set timi on
set echo on
spool R_L1136.log


ALTER TABLE 
   INFRA.ARQUIVOFUNCIONALIDADE
DROP 
   (
      IDATENDIMENTOANATELARQUIVO
   );
   



spool off
set echo off
set timi off
