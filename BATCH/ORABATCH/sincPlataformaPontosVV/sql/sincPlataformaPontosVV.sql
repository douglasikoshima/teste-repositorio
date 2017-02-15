/*#########################################################
#  BATCH......: sincPlataformaPontosVV
#  Criado por.: Williams Santos 25/04/2016
#  Funcional..: Dener Santos
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
    
    TYPE TB_LINHAVV IS RECORD ( 
       IDCLIENTEVV          VIVOVALORIZA.LINHAVV.IDCLIENTEVV%TYPE,          
       NRDOCUMENTO          CUSTOMER.DOCUMENTO.NRDOCUMENTO%TYPE,
       NMPESSOA             CUSTOMER.PESSOA.NMPESSOA%TYPE,
       DTNASCIMENTO         CUSTOMER.PESSOAFISICA.DTNASCIMENTO%TYPE,
       EMAIL                CUSTOMER.PESSOACOMUNICACAO.DSCONTATO%TYPE,
       IDSEGMENTACAOCLIENTE CUSTOMER.SEGMENTACAOCONSOLIDADA.IDSEGMETNACAOCLIENTE%TYPE,     
       NRTELEFONE           VIVOVALORIZA.LINHAVV.NRTELEFONE%TYPE, -- LINHA.LINHABASE.NRLINHA%TYPE,
       SGUF                 APOIO.UF.SGUF%TYPE,
       PLATAFORMA           APOIO.SUBTIPOLINHA.CDSUBTIPOLINHA%TYPE,
       CDCONTA              LINHA.LINHATELEFONICA.IDLINHASISTEMAORIGEM%TYPE,
       DTEXCLUSAO           VIVOVALORIZA.CLIENTEVV.DTEXCLUSAO%TYPE,       
       DTDESATIVACAO        VIVOVALORIZA.LINHAVV.DTEXCLUSAO%TYPE,
       DTADESAO             VIVOVALORIZA.LINHAVV.DTCADASTRO%TYPE,
       DTULTIMAATUALIZACAO  VIVOVALORIZA.LINHAVV.DTULTIMAATUALIZACAO%TYPE,
       IDLINHATELEFONICA    LINHA.LINHATELEFONICA.IDLINHATELEFONICA%TYPE,
       ROWIDLVV             ROWID
    );
    
    TYPE T_LINHAVV IS TABLE OF TB_LINHAVV INDEX BY BINARY_INTEGER;
    V_LINHAVV T_LINHAVV;
 
    ---------------------------------------------
  ----IDSEGMETNACAOCLIENTE 34 = BABY SITTING
  ---------------------------------------------
  CURSOR CRS (P_TOTALLINHAS NUMBER) IS
    SELECT /*+ FIRST_ROWS(5000) */
       LVV.IDCLIENTEVV,
       CVV.NRCPF,
       CP.NMPESSOA,
       NVL(PF.DTNASCIMENTO, TO_DATE('01/01/1960', 'dd/mm/yyyy')) AS DTNASCIMENTO,
       (SELECT PC.DSCONTATO 
          FROM CUSTOMER.PESSOACOMUNICACAO PC 
         WHERE PC.IDPESSOA = CVV.IDPESSOA 
           AND REGEXP_LIKE( PC.DSCONTATO, '@' ) 
           AND IDTIPOCOMUNICACAO IN (12, 6, 1838, 2141) 
           AND ROWNUM = 1 ) AS EMAIL,
           NVL(CSC.IDSEGMETNACAOCLIENTE, 34) AS IDSEGMETNACAOCLIENTE,
           LVV.NRTELEFONE,
        (SELECT AUF.SGUF 
           FROM CUSTOMER.UFOPERADORA UFO
                JOIN APOIO.AREAREGISTRO AR ON (UFO.IDUFOPERADORA = AR.IDUFOPERADORA)
                JOIN APOIO.UF AUF ON (AUF.IDUF = UFO.IDUF) 
           WHERE AR.CDAREAREGISTRO = SUBSTR(LVV.NRTELEFONE, 1, 2 )) AS SGUF,
        CASE
            WHEN  TL.IDCLASSIFICACAOTIPOLINHA = 'POS' THEN 'MV0002'
            WHEN  TL.IDCLASSIFICACAOTIPOLINHA = 'CTR' THEN 'MV0003'      
            WHEN  STL.CDSUBTIPOLINHA = 'CONTROLE N' THEN 'MV0004'
            WHEN  STL.CDSUBTIPOLINHA = 'VIVO NEXT' OR STL.CDSUBTIPOLINHA = 'VIVO EASY' THEN 'MV0005'
            WHEN  TL.IDCLASSIFICACAOTIPOLINHA = 'PRE' THEN 'PREPAGO'
            ELSE 'PREPAGO' 
        END AS V_PLATAFORMA,
        LT.IDLINHASISTEMAORIGEM,
        CVV.DTEXCLUSAO,
        LVV.DTEXCLUSAO,
        LVV.DTCADASTRO,
        LVV.DTULTIMAATUALIZACAO,
        LT.IDLINHATELEFONICA,
        LVV.RID 
   FROM (SELECT ROWID AS RID,
                IDCLIENTEVV,
                IDLINHATELEFONICA,
                NRTELEFONE,
                DTCADASTRO,
                DTEXCLUSAO,
                DTULTIMAATUALIZACAO
           FROM VIVOVALORIZA.LINHAVV LVV
          WHERE INSINCRONIZA = 'P'
            AND ROWNUM < P_TOTALLINHAS) LVV         
        JOIN VIVOVALORIZA.CLIENTEVV CVV ON ( CVV.IDCLIENTEVV = LVV.IDCLIENTEVV)
        LEFT JOIN CUSTOMER.PESSOAFISICA PF ON ( PF.IDPESSOA = CVV.IDPESSOA )            
        JOIN CUSTOMER.PESSOA CP ON (CP.IDPESSOA = CVV.IDPESSOA )          
        LEFT JOIN LINHA.LINHATELEFONICA LT ON ( LVV.IDLINHATELEFONICA = LT.IDLINHATELEFONICA )     
        LEFT JOIN CUSTOMER.SEGMENTACAOCONSOLIDADA CSC ON ( CSC.NRDOCUMENTO = CVV.NRCPF )
        LEFT JOIN APOIO.TIPOLINHA TL ON ( TL.IDTIPOLINHA = LT.IDTIPOLINHA )
        LEFT JOIN APOIO.SUBTIPOLINHA STL ON ( STL.IDSUBTIPOLINHA = LT.IDSUBTIPOLINHA );

          
    V_TP_REGISTRO              VARCHAR2(10) := '01'; 
    V_CDAREAREGISTRO NUMBER;
    V_NRLINHA NUMBER;   
    V_COUNT NUMBER := 0;
    V_LIMITE NUMBER := 5000;
    V_DONE BOOLEAN;
    V_DTDESATIVACAO VARCHAR2(50);
    V_DTEXCLUSAO DATE;  
    V_IDCLIENTEVV VIVOVALORIZA.LINHAVV.IDCLIENTEVV%TYPE := 0;
    V_TOTALLINHAS NUMBER;
    V_COUNT_INI NUMBER;

BEGIN

    DBMS_OUTPUT.ENABLE (buffer_size => NULL);           
    DBMS_OUTPUT.PUT_LINE('00|'||TO_CHAR(SYSDATE, 'ddmmyyyy')||'|'||to_char(SYSDATE, 'hh24miss' ));
    
    SELECT TO_NUMBER(DSVALORPARAMETRO)
    INTO V_TOTALLINHAS
    FROM APOIO.PARAMETRO
    WHERE CDPARAMETRO = 'PLATAFORMAVV_TOTAL_REGISTROS';
    
    OPEN CRS(V_TOTALLINHAS);
    LOOP
    FETCH CRS BULK COLLECT INTO V_LINHAVV LIMIT V_LIMITE;
        V_DONE := CRS%NOTFOUND;
        
        FOR VAR1 IN 1..V_LINHAVV.COUNT
        LOOP

            SAVEPOINT S;
            V_COUNT_INI := V_COUNT;

            BEGIN
                
                IF  ( V_LINHAVV(VAR1).NRDOCUMENTO IS NOT NULL 
                    AND V_LINHAVV(VAR1).NMPESSOA IS NOT NULL 
                    AND V_LINHAVV(VAR1).DTNASCIMENTO IS NOT NULL 
                    AND V_LINHAVV(VAR1).IDSEGMENTACAOCLIENTE IS NOT NULL       
                    AND V_LINHAVV(VAR1).DTADESAO IS NOT NULL 
                    AND V_LINHAVV(VAR1).DTULTIMAATUALIZACAO IS NOT NULL ) THEN
                        
                        V_CDAREAREGISTRO := NULL;
                        V_NRLINHA := NULL;
                    
                        V_CDAREAREGISTRO := SUBSTR(V_LINHAVV(VAR1).NRTELEFONE, 1, 2);
                        V_NRLINHA := SUBSTR(V_LINHAVV(VAR1).NRTELEFONE, 3);
            
                        V_DTDESATIVACAO := '';
                        V_DTEXCLUSAO := V_LINHAVV(VAR1).DTEXCLUSAO;
                        IF(V_LINHAVV(VAR1).IDLINHATELEFONICA IS NULL) -- TROCA DE TITULARIDADE
                        THEN
                            V_DTDESATIVACAO := TO_CHAR(SYSDATE, 'ddmmyyyy');            
                        ELSE
                            V_DTDESATIVACAO := TO_CHAR(V_LINHAVV(VAR1).DTDESATIVACAO, 'ddmmyyyy');
                        END IF;
            
                        --Se clienteVV possuir data de exclusao e ele nao estiver sido processado ainda, 
            --todos os registros israo receber data de exclusao                    
            IF(V_DTEXCLUSAO IS NOT NULL AND V_IDCLIENTEVV != V_LINHAVV(VAR1).IDCLIENTEVV)
            THEN          
                V_IDCLIENTEVV := V_LINHAVV(VAR1).IDCLIENTEVV;           
                UPDATE VIVOVALORIZA.LINHAVV LVV
                SET LVV.INSINCRONIZA = 'S'
                    , LVV.DTULTIMAATUALIZACAO = SYSDATE
                    , LVV.DTEXCLUSAO = CASE 
                            WHEN LVV.DTEXCLUSAO IS NULL
                                    THEN TO_DATE(V_DTDESATIVACAO, 'ddmmyyyy') 
                                    ELSE LVV.DTEXCLUSAO
                                    END
                WHERE LVV.IDCLIENTEVV = V_LINHAVV(VAR1).IDCLIENTEVV;
            ELSIF(V_DTEXCLUSAO IS NULL) --Asseguro que o registro nao seja reprocessado (update) quando o cliente esteja com data de exclusao
                        THEN
                UPDATE VIVOVALORIZA.LINHAVV LVV
                SET LVV.INSINCRONIZA = 'S'
                    , LVV.DTULTIMAATUALIZACAO = SYSDATE
                    , LVV.DTEXCLUSAO = CASE 
                            WHEN LVV.DTEXCLUSAO IS NULL
                                                    THEN TO_DATE(V_DTDESATIVACAO, 'ddmmyyyy') 
                                                    ELSE LVV.DTEXCLUSAO
                                                    END
                            WHERE LVV.ROWID = V_LINHAVV(VAR1).ROWIDLVV;
                                
                        END IF;
    
                        DBMS_OUTPUT.PUT_LINE( V_TP_REGISTRO||'|'||V_LINHAVV(VAR1).NRDOCUMENTO||'|'||V_LINHAVV(VAR1).NMPESSOA||'|'||TO_CHAR(V_LINHAVV(VAR1).DTNASCIMENTO, 'ddmmyyyy')||'|'||
                                            V_LINHAVV(VAR1).EMAIL||'|'||V_LINHAVV(VAR1).IDSEGMENTACAOCLIENTE||'|'||V_CDAREAREGISTRO||'|'||V_NRLINHA||'|'||
                                            V_LINHAVV(VAR1).SGUF||'|'||V_LINHAVV(VAR1).PLATAFORMA||'|'||V_LINHAVV(VAR1).CDCONTA||'|'||TO_CHAR(V_LINHAVV(VAR1).DTEXCLUSAO, 'ddmmyyyy')||'|'||
                                            V_DTDESATIVACAO||'|'||TO_CHAR(V_LINHAVV(VAR1).DTADESAO, 'ddmmyyyy')||'|'||TO_CHAR(V_LINHAVV(VAR1).DTULTIMAATUALIZACAO, 'ddmmyyyyhh24miss'));
                        V_COUNT := V_COUNT + 1;                                                    
                ELSE
                
                    V_DTEXCLUSAO := V_LINHAVV(VAR1).DTEXCLUSAO;
                    IF(V_LINHAVV(VAR1).IDLINHATELEFONICA IS NULL)
                    THEN                     
                        V_DTDESATIVACAO :=  TO_CHAR(SYSDATE, 'ddmmyyyy');  
                    ELSE
                        V_DTDESATIVACAO := TO_CHAR(V_LINHAVV(VAR1).DTDESATIVACAO, 'ddmmyyyy');      
                    END IF;
                    
                    IF(V_DTEXCLUSAO IS NOT NULL AND V_IDCLIENTEVV != V_LINHAVV(VAR1).IDCLIENTEVV)
                    THEN                
                        V_IDCLIENTEVV := V_LINHAVV(VAR1).IDCLIENTEVV;                        
                        UPDATE VIVOVALORIZA.LINHAVV LVV
                        SET LVV.INSINCRONIZA = 'E'
                            , LVV.DTULTIMAATUALIZACAO = SYSDATE
                            , LVV.DTEXCLUSAO = CASE 
                                                WHEN LVV.DTEXCLUSAO IS NULL
                                                THEN TO_DATE(V_DTDESATIVACAO, 'ddmmyyyy')
                                                ELSE LVV.DTEXCLUSAO
                                                END
                        WHERE LVV.IDCLIENTEVV = V_LINHAVV(VAR1).IDCLIENTEVV;                    
                    ELSIF(V_DTEXCLUSAO IS NULL)
                    THEN                     
                        UPDATE VIVOVALORIZA.LINHAVV LVV
                        SET LVV.INSINCRONIZA = 'E'
                            , LVV.DTULTIMAATUALIZACAO = SYSDATE
                            , LVV.DTEXCLUSAO = CASE 
                                                WHEN LVV.DTEXCLUSAO IS NULL
                                                THEN TO_DATE(V_DTDESATIVACAO, 'ddmmyyyy')
                                                ELSE LVV.DTEXCLUSAO
                                                END
                        WHERE LVV.ROWID = V_LINHAVV(VAR1).ROWIDLVV;                        
                    END IF;
                                    
                END IF;
            EXCEPTION
                WHEN OTHERS THEN
                ROLLBACK TO SAVEPOINT S;
                V_COUNT := V_COUNT_INI;
            END;
                    
        END LOOP;
        COMMIT;        
        EXIT WHEN (V_DONE);
        
    END LOOP;
    
    COMMIT;   
    CLOSE CRS;     
    DBMS_OUTPUT.PUT_LINE('99|'||V_COUNT);  
   
END;
/

SPOOL OFF
SET ECHO OFF
SET TIMI OFF
SET TIME OFF
EXIT