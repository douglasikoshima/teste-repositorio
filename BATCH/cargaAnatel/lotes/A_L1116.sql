set timi on
set echo on
spool A_L1116.log


ALTER TABLE 
   CONTATOADM.CONTATOFOLHA
ADD 
   (
      INANATEL            NUMBER DEFAULT 0
   );

CREATE INDEX CONTATOADM.CONTATOFOLHAIE3 ON CONTATOADM.CONTATOFOLHA
(INANATEL)
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

COMMENT ON COLUMN CONTATOADM.CONTATOFOLHA.INANATEL IS 'indica se o atendimento é da anatel com valor 1';



spool off
set echo off
set timi off
