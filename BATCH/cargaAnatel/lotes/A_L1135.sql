set timi on
set echo on
spool A_L1135.log

CREATE INDEX ATENDIMENTO.ATDANATELARQUIVOAK1 ON ATENDIMENTO.ATENDIMENTOANATELARQUIVO
(NMARQUIVO)
LOGGING
TABLESPACE TBS_INDICES
PCTFREE    10
INITRANS   2
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       UNLIMITED
            PCTINCREASE      0
            BUFFER_POOL      DEFAULT
           )
NOPARALLEL;

spool off
set echo off
set timi off