/*#########################################################
#  BATCH......: cargaLoginClientesTVantagens
#  Criado por.: Williams Santos 12/01/2016
#  Funcional..: Erika Brum
###########################################################*/
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

    TYPE TB_LOGINMARCAS IS RECORD
    (
        NRDOCUMENTO       ACESSO.LOGINMARCAS.NRDOCUMENTO%TYPE,
        TPCLIENTE         ACESSO.LOGINMARCAS.TPCLIENTE%TYPE,
        DSEMAIL           ACESSO.LOGINMARCAS.DSEMAIL%TYPE,
        DSSENHATVANTAGENS ACESSO.LOGINMARCAS.DSSENHATVANTAGENS%TYPE
    );

    TYPE T_LOGINMARCAS IS TABLE OF TB_LOGINMARCAS INDEX BY BINARY_INTEGER;
    V_LOGINMARCAS T_LOGINMARCAS;

    CURSOR CMP IS
    SELECT CCG.N1 AS NRDOCUMENTO
         , CCG.N2 AS TPCLIENTE
         , CCG.N3 AS DSEMAIL
         , CCG.N4 AS DSSENHATVANTAGENS
      FROM LOAD.CARGACONTATOGRUPO CCG;

    V_NRDOCUMENTO VARCHAR2(20);

    V_LIMITE NUMBER := 10000;--0;
    V_DONE BOOLEAN;
    V_QTD NUMBER := 0;

BEGIN

    DBMS_OUTPUT.ENABLE (buffer_size => NULL);

    OPEN CMP;
    LOOP
    FETCH CMP BULK COLLECT INTO V_LOGINMARCAS LIMIT V_LIMITE;
        V_DONE := CMP%NOTFOUND;

        FOR DOC IN 1 .. V_LOGINMARCAS.COUNT
        LOOP

           BEGIN

                IF (V_LOGINMARCAS(DOC).DSEMAIL IS NULL OR
                    V_LOGINMARCAS(DOC).TPCLIENTE IS NULL OR
                    V_LOGINMARCAS(DOC).NRDOCUMENTO IS NULL OR
                    V_LOGINMARCAS(DOC).DSSENHATVANTAGENS IS NULL)
                THEN

                    DBMS_OUTPUT.PUT_LINE(V_LOGINMARCAS(DOC).NRDOCUMENTO||';'||V_LOGINMARCAS(DOC).TPCLIENTE ||';'||V_LOGINMARCAS(DOC).DSEMAIL||';'||V_LOGINMARCAS(DOC).DSSENHATVANTAGENS);

                ELSE

                    SELECT COUNT(1)
                      INTO V_QTD
                      FROM ACESSO.LOGINMARCAS LM
                     WHERE LM.NRDOCUMENTO = V_LOGINMARCAS(DOC).NRDOCUMENTO;

                    IF (V_QTD = 0)
                    THEN

                        INSERT INTO ACESSO.LOGINMARCAS ALM (ALM.IDLOGIN
                                                  , ALM.DSEMAIL
                                                  , ALM.TPCLIENTE
                                                  , ALM.NRDOCUMENTO
                                                  , ALM.DSNOME
                                                  , ALM.SENHA
                                                  , ALM.CHAVETEMP
                                                  , ALM.QTERROLOGIN
                                                  , ALM.INATIVO
                                                  , ALM.STATUS
                                                  , ALM.DTULTIMAALTERACAO
                                                  , ALM.IDFB
                                                  , ALM.INDATIVOFB
                                                  , ALM.CANAL
                                                  , ALM.INSENHAUNIFICADA
                                                  , ALM.DSURLFACEBOOK
                                                  , ALM.DSSENHATVANTAGENS
                                                  , ALM.DSSENHAVIVO2
                                                  , ALM.INVALIDACAOPJ )
                         VALUES (ACESSO.LOGINMARCASSQ.NEXTVAL --V_LOGINMARCAS(DOC).IDLOGIN
                               , V_LOGINMARCAS(DOC).DSEMAIL
                               , V_LOGINMARCAS(DOC).TPCLIENTE
                               , V_LOGINMARCAS(DOC).NRDOCUMENTO
                               , NULL    --V_LOGINMARCAS(DOC).DSNOME
                               , NULL    --V_LOGINMARCAS(DOC).SENHA
                               , NULL    --V_LOGINMARCAS(DOC).CHAVETEMP
                               , 0       --V_LOGINMARCAS(DOC).QTERROLOGIN
                               , 1       --V_LOGINMARCAS(DOC).INATIVO
                               , NULL    --V_LOGINMARCAS(DOC).STATUS
                               , SYSDATE --V_LOGINMARCAS(DOC).DTULTIMAALTERACAO
                               , NULL    --V_LOGINMARCAS(DOC).IDFB
                               , NULL    --V_LOGINMARCAS(DOC).INDATIVOFB
                               , 'TVANTAGENS' --V_LOGINMARCAS(DOC).CANAL
                               , 0       --V_LOGINMARCAS(DOC).INSENHAUNIFICADA
                               , NULL --V_LOGINMARCAS(DOC).DSURLFACEBOOK
                               , V_LOGINMARCAS(DOC).DSSENHATVANTAGENS
                               , NULL --V_LOGINMARCAS(DOC).DSSENHAVIVO2
                               , 0       --V_LOGINMARCAS(DOC).INVALIDACAOPJ
                               );


                    ELSE

                        UPDATE ACESSO.LOGINMARCAS LM
                           SET DSSENHATVANTAGENS = V_LOGINMARCAS(DOC).DSSENHATVANTAGENS
                             , INVALIDACAOPJ = 0
                             , INSENHAUNIFICADA = 0
                             , DTULTIMAALTERACAO = SYSDATE
                         WHERE NRDOCUMENTO = V_LOGINMARCAS(DOC).NRDOCUMENTO;

                    END IF;

                END IF;

            EXCEPTION
            WHEN DUP_VAL_ON_INDEX THEN
                DBMS_OUTPUT.PUT_LINE(V_LOGINMARCAS(DOC).NRDOCUMENTO||';'||V_LOGINMARCAS(DOC).TPCLIENTE ||';'||V_LOGINMARCAS(DOC).DSEMAIL||';'||V_LOGINMARCAS(DOC).DSSENHATVANTAGENS);
            WHEN OTHERS THEN
                 DBMS_OUTPUT.PUT_LINE(V_LOGINMARCAS(DOC).NRDOCUMENTO||';'||V_LOGINMARCAS(DOC).TPCLIENTE ||';'||V_LOGINMARCAS(DOC).DSEMAIL||';'||V_LOGINMARCAS(DOC).DSSENHATVANTAGENS);
            END;

        END LOOP;

        COMMIT;
        EXIT WHEN (V_DONE);
    END LOOP;

    CLOSE CMP;
    LOAD.PRC_CARGACONTATOGRUPO;
    COMMIT;
EXCEPTION
WHEN  OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('ERRO NR.: ' || SQLCODE || ' - DESCRICAO DO ERRO: ' || SQLERRM);
END;
/

SPOOL OFF
SET ECHO OFF
SET TIMI OFF
SET TIME OFF
EXIT
