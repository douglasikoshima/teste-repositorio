set timi on
set echo on
spool A_L1120.log

CREATE TABLE ATENDIMENTO.ATENDIMENTOANATELARQUIVO
(
  IDATENDIMENTOANATELARQUIVO       NUMBER                        NOT NULL,
  NMARQUIVO                        VARCHAR2(255 BYTE),
  DTENVIO                          DATE,
  FLSTATUS                         NUMBER(1),
  QTPROCESSADO                     NUMBER,
  QTREJEITADO                      NUMBER,
  QTERROPROCESSADO                 NUMBER,
  QTTOTALREGISTROS                 NUMBER,
  QTPROTOCOLOCLIENTE               NUMBER,
  QTPROTOCOLONAOCLIENTE            NUMBER,
  QTCONTATONOVO                    NUMBER,
  QTCONTATOGENERICO                NUMBER,
  DSARQUIVOLOG                     VARCHAR2(255 BYTE),
  CDPRESTADORA                	   NUMBER,
  NMPRESTADORA                     VARCHAR2(255 BYTE),  
  IDUSUARIOALTERACAO               NUMBER,
  DTULTIMAALTERACAO                DATE                      DEFAULT sysdate               NOT NULL
)
tablespace tbs_dados ;

COMMENT ON COLUMN ATENDIMENTO.ATENDIMENTOANATELARQUIVO.IDATENDIMENTOANATELARQUIVO IS 'chave primaria da tabela';
COMMENT ON COLUMN ATENDIMENTO.ATENDIMENTOANATELARQUIVO.NMARQUIVO IS 'nome do arquivo';
COMMENT ON COLUMN ATENDIMENTO.ATENDIMENTOANATELARQUIVO.DTENVIO IS 'data de envio do arquivo';
COMMENT ON COLUMN ATENDIMENTO.ATENDIMENTOANATELARQUIVO.FLSTATUS IS 'flag de status do arquivo';
COMMENT ON COLUMN ATENDIMENTO.ATENDIMENTOANATELARQUIVO.QTPROCESSADO IS 'quantidade de solicita��es processadas';
COMMENT ON COLUMN ATENDIMENTO.ATENDIMENTOANATELARQUIVO.QTREJEITADO IS 'quantidade de solicita��es rejeitadas';
COMMENT ON COLUMN ATENDIMENTO.ATENDIMENTOANATELARQUIVO.QTERROPROCESSADO IS 'quantidade de erro de processamento';
COMMENT ON COLUMN ATENDIMENTO.ATENDIMENTOANATELARQUIVO.QTTOTALREGISTROS IS 'quantidade total de registros do arquivo';
COMMENT ON COLUMN ATENDIMENTO.ATENDIMENTOANATELARQUIVO.QTPROTOCOLOCLIENTE IS 'quantidade de protocolo abertos para clientes';
COMMENT ON COLUMN ATENDIMENTO.ATENDIMENTOANATELARQUIVO.QTPROTOCOLONAOCLIENTE IS 'quantidade de protocolo abertos para n�o clientes';
COMMENT ON COLUMN ATENDIMENTO.ATENDIMENTOANATELARQUIVO.QTCONTATONOVO IS 'quantidade de contatos novos abertos';
COMMENT ON COLUMN ATENDIMENTO.ATENDIMENTOANATELARQUIVO.QTCONTATOGENERICO IS 'quantidade de contatos gen�ricos abertos';
COMMENT ON COLUMN ATENDIMENTO.ATENDIMENTOANATELARQUIVO.DSARQUIVOLOG IS 'arquivo de log';
COMMENT ON COLUMN ATENDIMENTO.ATENDIMENTOANATELARQUIVO.CDPRESTADORA IS 'identificador interno da prestadora/holding';
COMMENT ON COLUMN ATENDIMENTO.ATENDIMENTOANATELARQUIVO.NMPRESTADORA IS 'nome da prestadora/holding';
COMMENT ON COLUMN ATENDIMENTO.ATENDIMENTOANATELARQUIVO.IDUSUARIOALTERACAO IS 'usu�rio de grava��o';
COMMENT ON COLUMN ATENDIMENTO.ATENDIMENTOANATELARQUIVO.DTULTIMAALTERACAO IS 'data de inclus�o ou altera��o';

CREATE UNIQUE INDEX ATENDIMENTO.ATENDIMENTOANATELARQUIVOPK ON ATENDIMENTO.ATENDIMENTOANATELARQUIVO
(IDATENDIMENTOANATELARQUIVO)
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

ALTER TABLE ATENDIMENTO.ATENDIMENTOANATELARQUIVO ADD (
  CONSTRAINT ATENDIMENTOANATELARQUIVOPK
 PRIMARY KEY
 (IDATENDIMENTOANATELARQUIVO)
    USING INDEX 
    TABLESPACE TBS_INDICES
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       UNLIMITED
                PCTINCREASE      0
               ));





spool off
set echo off
set timi off