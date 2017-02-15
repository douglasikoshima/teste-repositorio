/*#########################################################
#  BATCH......: processasolicitacoesvv
#  Criado por.: Williams Santos 19/04/2016
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

    TYPE TB_PROGRELAC IS RECORD
    (    
        IDPARAMPROGRELACIONAMENTO VIVOVALORIZA.PARAMPROGRELACIONAMENTO.IDPARAMPROGRELACIONAMENTO%TYPE,
        DSOPERACAO                VIVOVALORIZA.PARAMPROGRELACIONAMENTO.DSOPERACAO%TYPE,
        IDPESSOA                  VIVOVALORIZA.PARAMPROGRELACIONAMENTO.IDPESSOA%TYPE,
        DOCUMENTO                 VIVOVALORIZA.PARAMPROGRELACIONAMENTO.DOCUMENTO%TYPE,
        NRTELEFONE                VIVOVALORIZA.PARAMPROGRELACIONAMENTO.NRTELEFONE%TYPE,
        IDUSUARIOATUALIZACAO      VIVOVALORIZA.PARAMPROGRELACIONAMENTO.IDUSUARIOATUALIZACAO%TYPE,
        NRTELEFONESOLICITANTE     VIVOVALORIZA.PARAMPROGRELACIONAMENTO.NRTELEFONESOLICITANTE%TYPE,
        NRTELEFONECONTATO         VIVOVALORIZA.PARAMPROGRELACIONAMENTO.NRTELEFONECONTATO%TYPE,
        INCLIENTE                 VIVOVALORIZA.PARAMPROGRELACIONAMENTO.INCLIENTE%TYPE,
        INSTATUS                  VIVOVALORIZA.PARAMPROGRELACIONAMENTO.INSTATUS%TYPE,
        DTULTIMAALTERACAO         VIVOVALORIZA.PARAMPROGRELACIONAMENTO.DTULTIMAALTERACAO%TYPE,
        NRTELEFONELVV             VIVOVALORIZA.PARAMPROGRELACIONAMENTO.NRTELEFONE%TYPE,
        DTEXCLUSAO                VIVOVALORIZA.CLIENTEVV.DTEXCLUSAO%TYPE,
        IDCLIENTE                 VIVOVALORIZA.CLIENTEVV.IDCLIENTEVV%TYPE
    );
    
    TYPE T_PROGRELAC IS TABLE OF TB_PROGRELAC INDEX BY BINARY_INTEGER;    
    V_PROGRELAC T_PROGRELAC;
    
    -- FP PASSO 02    
    CURSOR CMP IS 
    SELECT PPR.IDPARAMPROGRELACIONAMENTO
         , PPR.DSOPERACAO
         , PPR.IDPESSOA
         , PPR.DOCUMENTO
         , PPR.NRTELEFONE
         , PPR.IDUSUARIOATUALIZACAO
         , PPR.NRTELEFONESOLICITANTE
         , PPR.NRTELEFONECONTATO
         , PPR.INCLIENTE
         , PPR.INSTATUS
         , PPR.DTULTIMAALTERACAO
         , PPR.NRTELEFONE
         , CVV.DTEXCLUSAO
         , CVV.IDCLIENTEVV
      FROM VIVOVALORIZA.PARAMPROGRELACIONAMENTO PPR
         , VIVOVALORIZA.CLIENTEVV CVV         
     WHERE CVV.NRCPF = PPR.DOCUMENTO
       AND PPR.INSTATUS = 0 -- PENDENTE
     ORDER BY CVV.NRCPF, PPR.DTULTIMAALTERACAO ASC;

    V_LIMITE NUMBER := 5000; 
    V_DONE BOOLEAN;    
    V_CDERRO NUMBER;
    V_DSERRO VARCHAR2(256);
    
    V_QTD NUMBER := 0;
    
    V_IDLINHATELEFONICA LINHA.LINHATELEFONICA.IDLINHATELEFONICA%TYPE;
    V_IDPESSOADEPARA    CUSTOMER.PESSOALINHA.IDPESSOADEPARA%TYPE;
    V_IDTIPOLINHA       LINHA.LINHATELEFONICA.IDTIPOLINHA%TYPE;
    V_IDPESSOAENDERECO  CUSTOMER.PESSOAENDERECO.IDPESSOAENDERECO%TYPE;
    V_COUNTPOS  NUMBER := 0;
    V_IDCLIENTE NUMBER;
    V_NRDOC     VIVOVALORIZA.PARAMPROGRELACIONAMENTO.DOCUMENTO%TYPE;
    
    V_COUNTPRP  NUMBER := 0;
    V_COUNTPRPC NUMBER := 0;
    V_COUNTCPR  NUMBER := 0;
    
BEGIN
    
    DBMS_OUTPUT.ENABLE (buffer_size => NULL);
    DBMS_OUTPUT.PUT_LINE('Inicio de processamento: '||TO_CHAR(SYSDATE,'DD/MM/YYYY HH24:MI:SS'));   
    
    V_NRDOC := '0';
    
    OPEN CMP;
    LOOP
    FETCH CMP BULK COLLECT INTO V_PROGRELAC LIMIT V_LIMITE;   
        V_DONE := CMP%NOTFOUND;
        FOR PP IN 1 .. V_PROGRELAC.COUNT
        LOOP
           
           IF ( V_PROGRELAC(PP).IDUSUARIOATUALIZACAO IS NULL) THEN
                V_PROGRELAC(PP).IDUSUARIOATUALIZACAO := 1;
           END IF;
           -- DBMS_OUTPUT.PUT_LINE('FP PASSO 04: '||V_PROGRELAC(PP).DSOPERACAO);
            -- FP PASSO 04
            IF (V_PROGRELAC(PP).DSOPERACAO = 'INCLUIR' OR  V_PROGRELAC(PP).DSOPERACAO = 'ATUALIZAR' ) THEN        
                            
                -- FP PASSO 05   1 = MOVEL - VERIFICA SE E MOVEL 
               -- DBMS_OUTPUT.PUT_LINE('P PASSO 05   1 = MOVEL - VERIFICA SE E MOVEL: '||V_PROGRELAC(PP).INCLIENTE);
                IF  ( V_PROGRELAC(PP).INCLIENTE = 1 ) THEN
                                    
                    -- FP PASSO 09 depois do 06                      
                    BEGIN                      
                        VIVOVALORIZA.PRC_MANTERLINHAPR( V_PROGRELAC(PP).DOCUMENTO, V_PROGRELAC(PP).NRTELEFONELVV
                                                      , V_PROGRELAC(PP).IDUSUARIOATUALIZACAO, V_CDERRO, V_DSERRO);                                                      
                    EXCEPTION
                    WHEN OTHERS THEN
                        NULL;
                    END;  

                -- FA 02
                -- FA 02 PASSO 01    VERIFICA SE E FIXO            
                ELSIF ( V_PROGRELAC(PP).INCLIENTE = 2 ) THEN
                   
                    BEGIN  
                       -- DBMS_OUTPUT.PUT_LINE('FA 02 PASSO 03: '||V_PROGRELAC(PP).IDCLIENTE);
                        -- FA 02 PASSO 03
                        --VIVOVALORIZA.PRC_MANTERCLIENTE(V_PROGRELAC(PP).DOCUMENTO, V_PROGRELAC(PP).IDUSUARIOATUALIZACAO, V_IDCLIENTE, V_CDERRO, V_DSERRO);                        
                        VIVOVALORIZA.PRC_MANTERLINHASCLIENTE(V_PROGRELAC(PP).IDCLIENTE, V_PROGRELAC(PP).IDUSUARIOATUALIZACAO,  V_CDERRO, V_DSERRO);
                      --  DBMS_OUTPUT.PUT_LINE('FA 02 PASSO 03: V_DSERRO '||V_DSERRO);
                    EXCEPTION
                        -- FA 03
                        WHEN NO_DATA_FOUND THEN                             
                            NULL;                   
                    END;      
                   
                     -- FP PASSO 08   
                     -- FA 02 PASSO 04 E 05                                                                      
                END IF;
                             
            -- FA01 PASSO 01           
            ELSE -- EXCLUIR
                
                IF ( V_NRDOC <> V_PROGRELAC(PP).DOCUMENTO ) THEN -- IMPEDE QUE O MESMO DOCUMENTO SEJA REPROCESSADO PELO LOOP
                     V_NRDOC := V_PROGRELAC(PP).DOCUMENTO;
                    
                    V_COUNTPRP  := 0;
                    V_COUNTPRPC := 0;
                    V_COUNTCPR  := 0;
                    
                    -- FA 01 PASSO 03                    
                    /*
                    UPDATE CUSTOMER.PROGRAMARELACIONAMENTOPOS PRP
                       SET PRP.DTEXCLUSAO = V_PROGRELAC(PP).DTEXCLUSAO
                     WHERE PRP.NRDOCUMENTO = V_PROGRELAC(PP).DOCUMENTO;
                                        
                    --Nesta tabela o IDPESSOAENDERECO é o documento                    
                    UPDATE CUSTOMER.PROGRAMARELACIONAMENTOPRECTRL PRPC
                       SET PRPC.DTEXCLUSAO = V_PROGRELAC(PP).DTEXCLUSAO
                     WHERE PRPC.IDPESSOAENDERECO = V_PROGRELAC(PP).DOCUMENTO;
                    */
                    
                    SELECT COUNT(1)
                      INTO V_COUNTPRP
                      FROM CUSTOMER.PROGRAMARELACIONAMENTOPOS PRP
                     WHERE PRP.NRDOCUMENTO = V_PROGRELAC(PP).DOCUMENTO;
                    
                    SELECT COUNT(1)
                      INTO V_COUNTPRPC
                      FROM CUSTOMER.PROGRAMARELACIONAMENTOPRECTRL PRPC                       
                     WHERE PRPC.IDPESSOAENDERECO = V_PROGRELAC(PP).DOCUMENTO;
                    
                    SELECT COUNT(1)
                      INTO V_COUNTCPR
                      FROM CUSTOMER.CARGAPROGRAMARELACIONAMENTO CPR
                     WHERE CPR.NRDOCUMENTO = V_PROGRELAC(PP).DOCUMENTO; 
                    
                    IF( V_COUNTPRP > 0 )
                    THEN                    
                        DELETE CUSTOMER.PROGRAMARELACIONAMENTOPOS PRP
                         WHERE PRP.NRDOCUMENTO = V_PROGRELAC(PP).DOCUMENTO;
                    END IF; 
                    
                    IF( V_COUNTPRPC > 0 )
                    THEN
                        DELETE CUSTOMER.PROGRAMARELACIONAMENTOPRECTRL PRPC                       
                         WHERE PRPC.IDPESSOAENDERECO = V_PROGRELAC(PP).DOCUMENTO;
                    END IF; 
                    
                    IF( V_COUNTCPR > 0 )
                    THEN
                        DELETE CUSTOMER.CARGAPROGRAMARELACIONAMENTO CPR
                         WHERE CPR.NRDOCUMENTO = V_PROGRELAC(PP).DOCUMENTO;
                    END IF; 
                                        
                    -- FA 01 PASSO 02
                    UPDATE VIVOVALORIZA.LINHAVV LVV
                       SET LVV.INSINCRONIZA = 'P'
                         , LVV.DTEXCLUSAO = V_PROGRELAC(PP).DTEXCLUSAO
                     WHERE LVV.IDCLIENTEVV IN ( SELECT CVV.IDCLIENTEVV 
                                                  FROM VIVOVALORIZA.CLIENTEVV CVV
                                                 WHERE CVV.NRCPF = V_PROGRELAC(PP).DOCUMENTO ); 
                  
                    IF( V_COUNTPRP > 0 OR V_COUNTPRPC > 0 OR V_COUNTCPR > 0 )
                    THEN
                  
                        BEGIN
                      
                                FOR LINHASEXCLUIDAS IN ( SELECT LT.IDLINHATELEFONICA, TO_CHAR (LVV.NRTELEFONE) AS NRLINHA
														  FROM VIVOVALORIZA.CLIENTEVV CVV JOIN VIVOVALORIZA.LINHAVV LVV USING (IDCLIENTEVV)
															   JOIN LINHA.LINHABASE LB 
															   ON (LB.NRLINHA = TO_NUMBER (SUBSTR (LVV.NRTELEFONE, 3)))
															   JOIN APOIO.AREAREGISTRO AR
															   ON (    AR.IDAREAREGISTRO = LB.IDAREAREGISTRO
																	   AND AR.CDAREAREGISTRO = TO_NUMBER (SUBSTR (LVV.NRTELEFONE, 1, 2))
																  )
															   JOIN LINHA.LINHATELEFONICA LT ON (LT.IDLINHABASE = LB.IDLINHABASE)
														 WHERE LB.NRLINHA > 0 AND CVV.NRCPF = V_PROGRELAC(PP).DOCUMENTO
														 )
                                LOOP
                                
                                     BEGIN
                                        
                                        INSERT INTO CUSTOMER.LINHASEXCLUIDASPR( IDLINHATELEFONICA
                                                                              , NRLINHA
                                                                              , DTEXCLUSAO
                                                                              , NMUSUARIO )
                                                                        VALUES( LINHASEXCLUIDAS.IDLINHATELEFONICA
                                                                              , LINHASEXCLUIDAS.NRLINHA
                                                                              , V_PROGRELAC(PP).DTEXCLUSAO
                                                                              , V_PROGRELAC(PP).IDUSUARIOATUALIZACAO );    
                                            
                                    EXCEPTION
                                      WHEN DUP_VAL_ON_INDEX THEN
                                      NULL;
                                    END;
                                
                                END LOOP; 
                        EXCEPTION
                        WHEN OTHERS THEN
                            NULL;
                        END;
                    END IF;        
                END IF;                    
            END IF;    
          
            UPDATE VIVOVALORIZA.PARAMPROGRELACIONAMENTO PPR
               SET PPR.INSTATUS = 1 -- PROCESSADO
             WHERE PPR.IDPARAMPROGRELACIONAMENTO = V_PROGRELAC(PP).IDPARAMPROGRELACIONAMENTO;

        END LOOP;
                       
        COMMIT;
        EXIT WHEN (V_DONE);  
            
    END LOOP; 
       
    CLOSE CMP;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Fim de processamento: '||TO_CHAR(SYSDATE,'DD/MM/YYYY HH24:MI:SS'));
    
END;
/

SPOOL OFF
SET ECHO OFF
SET TIMI OFF
SET TIME OFF
EXIT