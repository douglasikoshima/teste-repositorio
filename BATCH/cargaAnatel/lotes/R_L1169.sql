set timi on
set echo on
spool R_L1169.log


ALTER TABLE 
   ATENDIMENTO.ATENDIMENTOANATELANEXO
DROP 
   (
      IDUSUARIOALTERACAO, IDUSUARIOEXCLUSAO, DESCRICAO
   );
   

spool off
set echo off
set timi off
