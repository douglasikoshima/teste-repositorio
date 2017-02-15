SET SQLPROMPT ''
SET FEEDBACK OFF
SET ECHO OFF
SET AUTOTRACE OFF
SET FLUSH ON
SET HEADING OFF
SET SERVEROUTPUT ON
SET TIME OFF
SET TIMING OFF
SET TRIMOUT ON
SET TERMOUT ON
SET TRIMSPOOL ON
SET PAUSE OFF
SET SHOWMODE OFF
SET SQLBLANKLINES OFF
SET SQLNUMBER OFF
SET TAB OFF
SET VERIFY ON
SET WRAP OFF
SET FEED OFF
SET NEWPAGE NONE
SET LINESIZE 1024

VARIABLE VO_CDERRO NUMBER
VARIABLE VO_DSERRO VARCHAR2(512)

SPOOL &1 APPEND

declare
  VI_IDUSUARIOALTERACAO NUMBER := &2;
  VI_NUMREGCOMMIT NUMBER := &3;
  
  v_TempoExpiracaoInfancia APOIO.PARAMETRO.DSVALORPARAMETRO%TYPE;

  TYPE TB_SUBSEGMENTOINFANCIA IS
  TABLE OF APOIO.SUBSEGMENTO.IDSUBSEGMENTO%TYPE
  INDEX BY APOIO.SUBSEGMENTO.SGSUBSEGMENTOINFANCIA%TYPE;
  R_SUBSEGMENTOINFANCIA TB_SUBSEGMENTOINFANCIA;

  V_UPDTREGCOUNT PLS_INTEGER := 0;
begin

  -- Recupera o valor do parametro UltimoProcessadoInfancia da tabela APOIO.PARAMETRO
	SELECT DSVALORPARAMETRO
  INTO v_TempoExpiracaoInfancia
	FROM APOIO.PARAMETRO
	WHERE CDPARAMETRO = 'TempoExpiracaoInfancia';

  -- Recupera subsegmento infancia para realizar de-para
  FOR R IN (
    select IDSUBSEGMENTO, NVL(SGSUBSEGMENTOINFANCIA, '0') AS SGSUBSEGMENTOINFANCIA
    from APOIO.SUBSEGMENTO
  )
  LOOP
    R_SUBSEGMENTOINFANCIA(R.SGSUBSEGMENTOINFANCIA) := R.IDSUBSEGMENTO;
  END LOOP;

  -- Busca registros da tabela CUSTOMER.PESSOASUBSEGMENTO
  FOR R_PESSOASUBSEGMENTO
  IN (
    select /*+ PARALLEL(12) */ PS.rowid AS RID, S.SGSUBSEGMENTO
    from CUSTOMER.PESSOASUBSEGMENTO PS, APOIO.SUBSEGMENTO S
    where PS.IDSUBSEGMENTO = S.IDSUBSEGMENTO
      and S.ININFANCIA = 1
    and (sysdate - ps.dtultimaalteracao) >= v_TempoExpiracaoInfancia
  )
  LOOP
		update CUSTOMER.PESSOASUBSEGMENTO
		set IDSUBSEGMENTO = R_SUBSEGMENTOINFANCIA(R_PESSOASUBSEGMENTO.SGSUBSEGMENTO),
			DTULTIMAALTERACAO = SYSDATE,
			IDUSUARIOALTERACAO = VI_IDUSUARIOALTERACAO
		where rowid = R_PESSOASUBSEGMENTO.RID;

    V_UPDTREGCOUNT := V_UPDTREGCOUNT + 1;

    -- Realiza commit a cada VI_NUMREGCOMMIT
    IF V_UPDTREGCOUNT >= VI_NUMREGCOMMIT
    THEN
      V_UPDTREGCOUNT := 0;
      COMMIT;
    END IF;
  END LOOP;

  COMMIT;
  :VO_CDERRO := 0;
  :VO_DSERRO := 'Sucesso';
EXCEPTION
	WHEN OTHERS THEN
		:VO_CDERRO := 99;
		:VO_DSERRO := 'ERRO NR.: ' || SQLCODE || ' - DESCRI��O DO ERRO: ' || SQLERRM;
    ROLLBACK;

end;
/

select CHR(10) from dual;
select 'VO_CDERRO', :VO_CDERRO from dual;
select 'VO_DSERRO', :VO_DSERRO from dual;

SPOOL OFF

exit 0
