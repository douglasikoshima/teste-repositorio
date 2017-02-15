set serveroutput on;
set timi on;
set echo on;
spool &3
DECLARE

    V_PARTICAO          NUMBER := &1;
    V_TOTAL_PARTICOES   NUMBER := &2;

    TYPE TR_LINHA IS RECORD (LINHA NUMBER, ACAO NUMBER, RID ROWID);
    TYPE TT_LINHA IS TABLE OF TR_LINHA;

    CURSOR cLINHAS(P_PARTICAO NUMBER, P_TOTAL_PARTICOES NUMBER) IS
        ----------------------------------------------------------------------
        -- IDENTIFICANDO LINHAS PRE-PAGAS PARA EXCLUSAO DO VIVO VALORIZA
        ----------------------------------------------------------------------
        SELECT /*+ FIRST_ROWS PARALLEL(PRE,4)*/
            PRE.NRLINHA AS LINHA,
            CASE
                ----------------------------------------------------------------------
                -- LINHAS CONTROLE N E VIVO EASY NAO DEVEM SER REMOVIADAS
                ----------------------------------------------------------------------
                WHEN LT.IDSUBTIPOLINHA IN (50,200) THEN 0
                ----------------------------------------------------------------------
                -- LINHA NAO ESTA MAIS NA VIVO (APAGAR PRECTRL, LINHAVV E CARGAPROG)
                ----------------------------------------------------------------------
                WHEN LB.IDLINHABASE IS NULL OR LT.IDLINHATELEFONICA IS NULL THEN 1
                ----------------------------------------------------------------------
                -- A LINHA ESTA INDALTERADA (APAGAR PRECTRL, LINHAVV E CARGAPROG)
                ----------------------------------------------------------------------
                WHEN NVL(LT.IDLINHATELEFONICA,0) = PRE.IDLINHATELEFONICA AND LT.IDTIPOLINHA IN (2,6) THEN 2
                ----------------------------------------------------------------------
                -- HOUVE TROCA DE TITULARIDADE MAS A LINHA AINDA POSSUI CARACTERISTICAS DE EXCLUSAO (APAGAR PRECTRL, LINHAVV E CARGAPROG)
                ----------------------------------------------------------------------
                WHEN NVL(LT.IDLINHATELEFONICA,0) != PRE.IDLINHATELEFONICA AND LT.IDTIPOLINHA IN (2,6) THEN 3
                ----------------------------------------------------------------------
                -- HOUVE TROCA DE TITULARIDADE OU NUMERO (APAGAR PRECTRL)
                ----------------------------------------------------------------------
                WHEN LT.IDLINHATELEFONICA IS NULL THEN 4
                ----------------------------------------------------------------------
                -- CASO CONTRARIO APAGA APENAS A PRECTRL
                ----------------------------------------------------------------------
                ELSE 5
            END AS ACAO,
            PRE.ROWID AS RID
        FROM
            CUSTOMER.PROGRAMARELACIONAMENTOPRECTRL PRE
            JOIN APOIO.AREAREGISTRO AR ON AR.CDAREAREGISTRO = TO_NUMBER(SUBSTR(PRE.NRLINHA,1,2))
            LEFT JOIN LINHA.LINHABASE LB ON AR.IDAREAREGISTRO = LB.IDAREAREGISTRO AND TO_NUMBER(SUBSTR(PRE.NRLINHA,3)) = LB.NRLINHA 
            LEFT JOIN LINHA.LINHATELEFONICA LT ON LT.IDLINHABASE = LB.IDLINHABASE 
        WHERE
            ORA_HASH(PRE.ROWID,P_TOTAL_PARTICOES-1) = P_PARTICAO-1;

    R_LINHA  TT_LINHA;
    V_LIMITE NUMBER := 100;
    V_HORA_LIMITE VARCHAR2(10);
    V_ERRMSG VARCHAR2(3000);

    PROCEDURE LOG(I_MSG VARCHAR2)
    IS
    BEGIN
        DBMS_OUTPUT.PUT_LINE(TO_CHAR(SYSDATE,'HH24:MI:SS')||' - ' || I_MSG);
    END;

BEGIN
 
    DBMS_APPLICATION_INFO.SET_MODULE('EXPURGO PRE VIVOVALORIZA','PARTITION ' || V_PARTICAO || '/' || V_TOTAL_PARTICOES);

    -- IDENTIFICAMOS E INTERROMPEMOS O SCRIPT NO HORARIO COMERCIAL
    -- COLOCAMOS A VERIFICACAO NO COMECO PARA IMPEDIR A ABERTURA DO CURSOR NO HORARIO COMERCIAL
    SELECT DSVALORPARAMETRO INTO V_HORA_LIMITE FROM APOIO.PARAMETRO WHERE CDPARAMETRO = 'BUSINESS_HOUR_INI';

    IF V_HORA_LIMITE < TO_CHAR(SYSDATE,'HH24MI') THEN

        LOG('Processo bloqueado para execucao no comercial '|| V_HORA_LIMITE);    
    
    ELSE

        LOG('Inicio de processamento: PARTICAO ' || V_PARTICAO || '/' || V_TOTAL_PARTICOES);

        OPEN cLINHAS(V_PARTICAO,V_TOTAL_PARTICOES);

        LOOP
            FETCH cLINHAS BULK COLLECT INTO R_LINHA LIMIT V_LIMITE;

            FOR I IN 1..R_LINHA.COUNT LOOP

                SAVEPOINT S;

                BEGIN

                    LOG('Processando linha: ' || R_LINHA(I).LINHA || ' acao: ' || R_LINHA(I).ACAO);

                    IF R_LINHA(I).ACAO != 0 THEN

                        DELETE FROM CUSTOMER.PROGRAMARELACIONAMENTOPRECTRL WHERE ROWID = R_LINHA(I).RID;

                    END IF;

                    IF R_LINHA(I).ACAO IN (1,2,3) THEN

                        DELETE FROM CUSTOMER.CARGAPROGRAMARELACIONAMENTO WHERE NRTELEFONE = R_LINHA(I).LINHA;
                        DELETE FROM VIVOVALORIZA.LINHAVV WHERE NRTELEFONE = R_LINHA(I).LINHA;

                    END IF;

                EXCEPTION
                    WHEN OTHERS THEN
                    ROLLBACK TO SAVEPOINT S;
                    V_ERRMSG := SUBSTR(SQLERRM,1,3000);
                    LOG('Erro na exclusao da linha ' || R_LINHA(I).LINHA || ' - Erro: ' || V_ERRMSG );
                END;

                LOG('FIM - Processando linha: ' || R_LINHA(I).LINHA || ' - acao: ' || R_LINHA(I).ACAO);

            END LOOP;

            COMMIT;
            
            -- IDENTIFICAMOS E INTERROMPEMOS O SCRIPT NO HORARIO COMERCIAL
            SELECT DSVALORPARAMETRO INTO V_HORA_LIMITE FROM APOIO.PARAMETRO WHERE CDPARAMETRO = 'BUSINESS_HOUR_INI';

            EXIT WHEN R_LINHA.COUNT < V_LIMITE OR V_HORA_LIMITE < TO_CHAR(SYSDATE,'HH24MI');
        END LOOP;

    END IF;

    LOG('Fim de processamento: PARTICAO ' || V_PARTICAO || '/' || V_TOTAL_PARTICOES);
    
END;
/

spool off
exit