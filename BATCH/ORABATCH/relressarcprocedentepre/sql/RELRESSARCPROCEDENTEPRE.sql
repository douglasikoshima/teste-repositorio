/***********************************************************
  BATCH......: relressarcimprocedente
  Criado por.: Eder Jani Martins (06/05/2015)
  Funcional..: Erika Brum
***********************************************************/
SET SQLPROMPT '';
SET TERMOUT OFF;
SET FEEDBACK OFF;
SET ECHO OFF;
SET AUTOTRACE OFF;
SET FLUSH ON;
SET HEADING OFF;
SET SERVEROUTPUT ON SIZE UNLIMITED;
SET TIME OFF;
SET TIMING OFF;
SET TRIMOUT ON;
SET TRIMSPOOL ON;
SET PAUSE OFF;
SET SHOWMODE OFF;
SET SQLBLANKLINES OFF;
SET SQLNUMBER OFF;
SET TAB OFF;
SET VERIFY OFF;
SET WRAP OFF;
SET FEED OFF;
SET NEWPAGE NONE;
SET LINESIZE 32767;
SPOOL &1;

WHENEVER OSERROR EXIT 9
WHENEVER SQLERROR EXIT SQL.SQLCODE

ALTER SESSION SET COMMIT_LOGGING = 'IMMEDIATE'
ALTER SESSION SET COMMIT_WAIT = NOWAIT

DECLARE
    V_LINHA           VARCHAR2(2000) DEFAULT NULL;
	V_IDSISTEMAORIGEM NUMBER DEFAULT 0;
	V_SYSDATE         DATE DEFAULT SYSDATE;

	TYPE R_RESSARCIMENTOBILLING IS RECORD 
    (    
        DSSTATUS              LOAD.RESSARCIMENTOBILLING.DSSTATUS%TYPE
      , IDPEDIDORESSARCIMENTO ATENDIMENTO.PEDIDORESSARCIMENTO.IDPEDIDORESSARCIMENTO%TYPE    
    );
    
    TYPE TP_RESSARCIMENTOBILLING IS TABLE OF R_RESSARCIMENTOBILLING;
    V_RESSARCIMENTOBILLING TP_RESSARCIMENTOBILLING;

	--Cursor que verifica PROCEDENTES NAO ENCONTRADOS
    CURSOR CURSOR_PROCEDENTEPOS IS 
	SELECT DSTIPORESSARCIMENTO      || ', '||
		   CDLINHADW                || ', '||
		   NRASSINATURA             || ', '||
		   NRLINHA                  || ', '||
		   DSPLANO                  || ', '||
		   NREVENTOANATEL           || ', '||
		   DTINICIOINTERRUPCAO      || ', '||
		   DTFIMINTERRUPCAO         || ', '||
		   HRINICIOINTERRUPCAO      || ', '||
		   HRFIMINTERRUPCAO         || ', '||
		   NRTEMPOINTERRUPCAO       || ', '||
		   VLRESSARCIMENTO          || ', '||
		   DTCONCESSAORESSARCIMENTO || ', '||
		   DSSTATUS                 || ', '||
		   NRCICLO                  || ', '||
		   DTFATURA                 || ', '||
		   DTPROCESSAMENTO          || ', '||
		   SGUF                     || ', '||
		   NMMUNICIPIO              || ', '||
		   VLARPU AS LINHA
	  FROM LOAD.RESSARCIMENTOBILLING LRB
	 WHERE NOT EXISTS 
	 (
		SELECT 1
		  FROM ATENDIMENTO.PEDIDORESSARCIMENTO APR
		 WHERE APR.CDAREAREGISTRO      = SUBSTR(LRB.NRLINHA,1,2)
		   AND APR.NRLINHA             = SUBSTR(LRB.NRLINHA,3)
		   AND APR.DTINICIOINTERRUPCAO = TO_DATE(LRB.DTINICIOINTERRUPCAO||LRB.HRINICIOINTERRUPCAO, 'DD/MM/YYHH24:MI')
		   AND APR.DTFIMINTERRUPCAO    = TO_DATE(LRB.DTFIMINTERRUPCAO||LRB.HRFIMINTERRUPCAO, 'DD/MM/YYHH24:MI')
	  );


BEGIN

    DBMS_OUTPUT.ENABLE (buffer_size => NULL);

    SELECT IDSISTEMAORIGEM 
      INTO V_IDSISTEMAORIGEM
      FROM APOIO.SISTEMAORIGEM 
     WHERE UPPER(NMSISTEMAORIGEM) = 'NGIN CARE SP';

	-- INSERE OS RESSACIMENTOS EM ATENDIMENTO.RESSARCIMENTOBILLING OU ATUALIZA QUANDO JA EXISTE
    MERGE /*+ FIRST_ROWS */ INTO ATENDIMENTO.RESSARCIMENTOBILLING ARB
    USING 
    ( 
        SELECT APR.IDPEDIDORESSARCIMENTO,
               APR.DSSTATUSRESSARCIMENTO,
               APR.CDUSUARIOALTERACAO,
               APR.DTULTIMAALTERACAO,
               LRB.CDLINHADW AS ID_LINHA,
               LRB.NRASSINATURA AS ID_ASSINATURA,
               LRB.DSPLANO AS PLANO,
               LRB.NREVENTOANATEL AS NUMERO_EVENTO,
               LRB.VLRESSARCIMENTO AS VALOR_RESSARCIMENTO,
               TO_DATE(LRB.DTCONCESSAORESSARCIMENTO, 'DD/MM/YYYY') AS DATA_CONCESSAO_RESSARCIMENTO,
               LRB.NRCICLO AS CICLO_FATURAMENTO,
               LRB.DTFATURA AS MES_ANO_FATURA,
               TO_DATE(LRB.DTPROCESSAMENTO, 'DD/MM/YYYY') AS DATA_PROCESSAMENTO,
               LRB.VLARPU AS ARPU
          FROM LOAD.RESSARCIMENTOBILLING LRB,
               ATENDIMENTO.PEDIDORESSARCIMENTO APR
         WHERE APR.CDAREAREGISTRO      = SUBSTR(LRB.NRLINHA,1,2)
           AND APR.NRLINHA             = SUBSTR(LRB.NRLINHA,3)
           AND APR.DTINICIOINTERRUPCAO = TO_DATE(LRB.DTINICIOINTERRUPCAO||LRB.HRINICIOINTERRUPCAO, 'DD/MM/YYHH24:MI')
           AND APR.DTFIMINTERRUPCAO    = TO_DATE(LRB.DTFIMINTERRUPCAO||LRB.HRFIMINTERRUPCAO, 'DD/MM/YYHH24:MI')
    ) RESS
    ON 
    (
		ARB.IDPEDIDORESSARCIMENTO = RESS.IDPEDIDORESSARCIMENTO
    )
    WHEN MATCHED THEN UPDATE SET
        ARB.CDLINHADW                = RESS.ID_LINHA,
        ARB.NRASSINATURA             = RESS.ID_ASSINATURA,
        ARB.DSPLANO                  = RESS.PLANO,
        ARB.NREVENTOANATEL           = RESS.NUMERO_EVENTO,
        ARB.VLRESSARCIMENTO          = RESS.VALOR_RESSARCIMENTO,
        ARB.DTCONCESSAORESSARCIMENTO = RESS.DATA_CONCESSAO_RESSARCIMENTO,
        ARB.NRCICLO                  = RESS.CICLO_FATURAMENTO,
        ARB.DTFATURA                 = RESS.MES_ANO_FATURA,
        ARB.DTPROCESSAMENTO          = RESS.DATA_PROCESSAMENTO,
        ARB.VLARPU                   = RESS.ARPU,
        ARB.IDSISTEMAORIGEM          = V_IDSISTEMAORIGEM,
        ARB.CDUSUARIOALTERACAO       = 1,
        ARB.DTULTIMAALTERACAO        = V_SYSDATE
    WHEN NOT MATCHED THEN INSERT
    (
        IDPEDIDORESSARCIMENTO,
        CDLINHADW,
        NRASSINATURA,
        DSPLANO,
        NREVENTOANATEL,
        VLRESSARCIMENTO,
        DTCONCESSAORESSARCIMENTO,
        NRCICLO,
        DTFATURA,
        DTPROCESSAMENTO,
        VLARPU,
        IDSISTEMAORIGEM,
        CDUSUARIOALTERACAO,
        DTULTIMAALTERACAO    
    ) VALUES (
        RESS.IDPEDIDORESSARCIMENTO,
        RESS.ID_LINHA,
        RESS.ID_ASSINATURA,
        RESS.PLANO,
        RESS.NUMERO_EVENTO,
        RESS.VALOR_RESSARCIMENTO,
        RESS.DATA_CONCESSAO_RESSARCIMENTO,
        RESS.CICLO_FATURAMENTO,
        RESS.MES_ANO_FATURA,
        RESS.DATA_PROCESSAMENTO,
        RESS.ARPU,
        V_IDSISTEMAORIGEM,
        1,
        V_SYSDATE
    );

	--ATUALIZA O STATUS E A DATA DO RESSACIMENTOS EM ATENDIMENTO.PEDIDORESSARCIMENTO
    SELECT LRB.DSSTATUS AS STATUS 
         , APR.IDPEDIDORESSARCIMENTO         
      BULK COLLECT INTO V_RESSARCIMENTOBILLING
      FROM LOAD.RESSARCIMENTOBILLING LRB
         , ATENDIMENTO.PEDIDORESSARCIMENTO APR
         WHERE APR.CDAREAREGISTRO      = SUBSTR(LRB.NRLINHA,1,2)
           AND APR.NRLINHA             = SUBSTR(LRB.NRLINHA,3)
           AND APR.DTINICIOINTERRUPCAO = TO_DATE(LRB.DTINICIOINTERRUPCAO||LRB.HRINICIOINTERRUPCAO, 'DD/MM/YYHH24:MI')
       AND APR.DTFIMINTERRUPCAO    = TO_DATE(LRB.DTFIMINTERRUPCAO||LRB.HRFIMINTERRUPCAO, 'DD/MM/YYHH24:MI');
      
    FOR VAR IN 1..V_RESSARCIMENTOBILLING.COUNT 
    LOOP
    
        UPDATE ATENDIMENTO.PEDIDORESSARCIMENTO APR
           SET APR.DSSTATUSRESSARCIMENTO = V_RESSARCIMENTOBILLING(VAR).DSSTATUS
             , APR.CDUSUARIOALTERACAO    = 1
             , APR.DTULTIMAALTERACAO     = V_SYSDATE
         WHERE APR.IDPEDIDORESSARCIMENTO = V_RESSARCIMENTOBILLING(VAR).IDPEDIDORESSARCIMENTO;
    
    END LOOP;    
    COMMIT;

	--EXECUTA O CURSOR COM OS RESSACIMENTOS NAO ENCONTRADOS E GRAVA NUM ARQUIVO
	/*
		Descomentar linha abaixo quando o batch subir para producao
	*/
    --DBMS_OUTPUT.PUT_LINE('DSTIPORESSARCIMENTO, CDLINHADW, NRASSINATURA, NRLINHA, DSPLANO, NREVENTOANATEL, DTINICIOINTERRUPCAO, DTFIMINTERRUPCAO, HRINICIOINTERRUPCAO, HRFIMINTERRUPCAO, NRTEMPOINTERRUPCAO, VLRESSARCIMENTO, DTCONCESSAORESSARCIMENTO, DSSTATUS, NRCICLO, DTFATURA, DTPROCESSAMENTO, SGUF, NMMUNICIPIO, VLARPU');
    OPEN CURSOR_PROCEDENTEPOS;
    LOOP
        FETCH CURSOR_PROCEDENTEPOS INTO V_LINHA;
        EXIT WHEN CURSOR_PROCEDENTEPOS%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(V_LINHA);
    END LOOP;
    CLOSE CURSOR_PROCEDENTEPOS;
    DBMS_OUTPUT.PUT_LINE('== FIM LINHAS NAO ENCONTRADAS EM ATENDIMENTO.PEDIDORESSARCIMENTO ==');
END;
/

SPOOL OFF
SET ECHO OFF
SET TIMI OFF
SET TIME OFF
EXIT