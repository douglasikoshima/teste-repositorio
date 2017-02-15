SET SQLPROMPT '';
SET TERMOUT ON;
SET FEEDBACK OFF;
SET ECHO OFF;
SET AUTOTRACE OFF;
SET FLUSH ON;
SET HEADING OFF;
SET SERVEROUTPUT ON SIZE 1000000;
SET TIME OFF;
SET TIMING ON;
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

WHENEVER SQLERROR EXIT SQL.SQLCODE
WHENEVER OSERROR EXIT FAILURE 

DECLARE
    V_IDPESSOA            NUMBER;
    V_NMPESSOA            VARCHAR2(256);
    V_SGUF                VARCHAR2(3);
    V_INASSOCIACAO        VARCHAR2(1);
    V_NRDOCUMENTO         VARCHAR2(15);
    V_NRCNPJ              VARCHAR2(15);
    V_ROWID               VARCHAR2(256);
    V_CNT                 NUMBER;
    V_INCONSOLIDA         NUMBER;
    V_TIPODOCUMENTO       NUMBER;
    V_IDLINHATELEFONICA   NUMBER ;

    VO_CDERRO NUMBER ;
    VO_DSERRO VARCHAR2(512);

    CURSOR C_REMOVEDOC(P_NRDOCUMENTO VARCHAR2) IS
        SELECT ROWID FROM CUSTOMER.DOCUMENTOCONSOLIDADO WHERE NRDOCUMENTO = P_NRDOCUMENTO ;
    
    CURSOR C_PARAMETRO IS
        SELECT 
           NRDOCUMENTO , 
           INCONSOLIDA ,
           INDOCUMENTO 
        FROM 
           APOIO.CONSOLIDADOC
        WHERE
           INDOCUMENTO = 1 ;

        
    CURSOR C_SINCRONIZA_CPF(P_NRDOCUMENTO VARCHAR2) IS
    SELECT /*+ NO_INDEX(LT LINHATELEFONICAIE7, LT LINHATELEFONICAIE8) */ 
          LT.IDLINHATELEFONICA
     FROM CUSTOMER.DOCUMENTO D
        , CUSTOMER.PESSOADEPARA PDP
        , CUSTOMER.PESSOADOCUMENTO PD
        , CUSTOMER.PESSOALINHA PL
        , LINHA.LINHATELEFONICA LT
    WHERE D.NRDOCUMENTO = P_NRDOCUMENTO
      AND PD.IDDOCUMENTO = D.IDDOCUMENTO
      AND PD.IDPESSOA = PDP.IDPESSOA
      AND PDP.IDPESSOADEPARA = PL.IDPESSOADEPARA 
      AND PL.IDTIPORELACIONAMENTO = 2            
      AND PL.IDLINHATELEFONICA = LT.IDLINHATELEFONICA
      AND (PD.DTEXPIRACAO IS NULL OR PD.DTEXPIRACAO > SYSDATE) ;
   
BEGIN
    VO_CDERRO := 00;
    VO_DSERRO := '>>> Sucesso na Sincronizacao <<<';
    
    DBMS_OUTPUT.ENABLE(1000000);
    DBMS_OUTPUT.PUT_LINE(TO_CHAR(SYSDATE,'YYYY-MM-DD HH24:MI:SS') || ' >>> Inicio de Processamento : ConsolidaDocumento');
    
    OPEN C_PARAMETRO ;
    LOOP
        FETCH C_PARAMETRO INTO V_NRDOCUMENTO , V_INCONSOLIDA, V_TIPODOCUMENTO ;
        EXIT WHEN C_PARAMETRO%NOTFOUND;
        V_CNT := 0 ;
        OPEN C_REMOVEDOC(V_NRDOCUMENTO);
        LOOP
            FETCH C_REMOVEDOC INTO V_ROWID ;
            EXIT WHEN C_REMOVEDOC%NOTFOUND;

            DELETE FROM CUSTOMER.DOCUMENTOCONSOLIDADO WHERE ROWID = V_ROWID ;   -- Mesmo sendo inConsolida = ZERO deve remover
            V_CNT := V_CNT + 1;
            IF V_CNT > 1000 THEN
               COMMIT;
               V_CNT := 1;
            END IF ;

        END LOOP;
        CLOSE C_REMOVEDOC;

        IF V_INCONSOLIDA = 1 THEN   -- Deve ser criada a consulta consolidada
             
            IF V_TIPODOCUMENTO = 1 THEN
                V_CNT := 0 ;
                OPEN C_SINCRONIZA_CPF(V_NRDOCUMENTO) ;
                LOOP
                    FETCH C_SINCRONIZA_CPF INTO V_IDLINHATELEFONICA ;

                    EXIT WHEN C_SINCRONIZA_CPF%NOTFOUND;

                    -- Sistema insere um registro conforme descrito abaixo
                    INSERT INTO CUSTOMER.DOCUMENTOCONSOLIDADO 
                    (
                        IDDOCUMENTOCONSOLIDADO ,
                        IDPESSOA ,
                        IDLINHATELEFONICA ,
                        SGUF ,
                        NRDOCUMENTO ,
                        INTIPOPESSOA ,
                        IDUSUARIO 
                    ) 
                    VALUES 
                    (
                        CUSTOMER.DOCUMENTOCONSOLIDADOSQ.NEXTVAL ,
                        1 ,
                        V_IDLINHATELEFONICA ,
                        '1' ,
                        V_NRDOCUMENTO ,
                        1 ,
                        1
                    );
                    
                    V_CNT := V_CNT + 1;
                    IF V_CNT > 1000 THEN
                       COMMIT;
                       V_CNT := 1;
                    END IF ;

                END LOOP;
                CLOSE C_SINCRONIZA_CPF ;
                COMMIT;
                    
            END IF ;   -- ELSE IF V_TIPODOCUMENTO = 1 THEN

        END IF ;   -- IF V_INCONSOLIDA = 1 THEN

    
    END LOOP;
    CLOSE C_PARAMETRO ;

    DBMS_OUTPUT.PUT_LINE('CDERRO: '||VO_CDERRO||', DSERRO: '||VO_DSERRO);
    DBMS_OUTPUT.PUT_LINE(TO_CHAR(SYSDATE,'YYYY-MM-DD HH24:MI:SS') || ' <<< Fim de Processamento : ConsolidaDocumento');

EXCEPTION
	WHEN OTHERS 
	THEN
        VO_CDERRO := 99;
        VO_DSERRO := 'ERRO NR.: ' || SQLCODE || ' - DESCRICAO DO ERRO: ' || SQLERRM;

        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('CDERRO: '||VO_CDERRO||', DSERRO: '||VO_DSERRO);
        DBMS_OUTPUT.PUT_LINE(TO_CHAR(SYSDATE,'YYYY-MM-DD HH24:MI:SS') || ' <<< ERRO : Fim de Processamento : ConsolidaDocumento');
END;
/

set echo off
set feedback off
set heading off
set arraysize 100
set autotrace on
set flush off
set pages 0
set lines 32767
set trimspool on
set timi on
set time on
set termout off

exit;

