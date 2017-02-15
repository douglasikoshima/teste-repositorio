/*#########################################################
#  BATCH......: excluiProgramaRelacionamento
#  Criado por.: Williams Santos 27/09/2016
#  Funcional..: Dener
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
--SPOOL &1;
WHENEVER OSERROR EXIT 9
WHENEVER SQLERROR EXIT SQL.SQLCODE

DECLARE

    TYPE T_EXCLUIPP IS RECORD
   (   
        NRCPF_LOAD            VIVOVALORIZA.CLIENTEVV.NRCPF%TYPE,       
        DTEXCLUSAO_LOAD       VIVOVALORIZA.CLIENTEVV.DTEXCLUSAO%TYPE,
        NRTELEFONE_LVV        VIVOVALORIZA.LINHAVV.NRTELEFONE%TYPE,
        IDLINHATELEFONICA_LVV VIVOVALORIZA.LINHAVV.IDLINHATELEFONICA%TYPE,
        ROWID_CVV             ROWID,        
        NRDOCUMENTO_CPR       CUSTOMER.CARGAPROGRAMARELACIONAMENTO.NRDOCUMENTO%TYPE, 
        ROWID_PRP             ROWID,
		IDCLIENTE_LVV		  VIVOVALORIZA.LINHAVV.IDCLIENTEVV%TYPE				
   );
   
   TYPE TB_EXCLUIPP IS TABLE OF T_EXCLUIPP INDEX BY BINARY_INTEGER;  
   V_EXCLUIPP TB_EXCLUIPP;

    CURSOR crs IS    
    SELECT EPR.NRCPF
         , EPR.DTEXCLUSAO
         , LVV.NRTELEFONE
         , LVV.IDLINHATELEFONICA
         , CVV.ROWID AS ROWID_CVV        
         , CPR.NRDOCUMENTO AS NRDOCUMENTO_CPR
         , PRP.ROWID AS ROWID_PRP
		 , LVV.IDCLIENTEVV AS IDCLIENTE_CVV
      FROM LOAD.EXCLUSAOPROGRAMARELACIONAMENTO EPR
         , VIVOVALORIZA.CLIENTEVV CVV
         , VIVOVALORIZA.LINHAVV LVV
         , CUSTOMER.CARGAPROGRAMARELACIONAMENTO CPR
         , CUSTOMER.PROGRAMARELACIONAMENTOPOS PRP
     WHERE CVV.NRCPF(+) = EPR.NRCPF
       AND LVV.IDCLIENTEVV(+) = CVV.IDCLIENTEVV    
       AND CPR.NRDOCUMENTO(+) = EPR.NRCPF        
       AND PRP.NRDOCUMENTO(+) = EPR.NRCPF
     ORDER BY EPR.NRCPF;        
     
    V_LIMITE NUMBER := 5000;
    V_DONE   BOOLEAN;
    
    V_IDLINHATELEFONICA CUSTOMER.PESSOALINHA.IDLINHATELEFONICA%TYPE;
    V_COUNT_CPR NUMBER;
    V_COUNT_PRP NUMBER;
    V_NRCPF VIVOVALORIZA.CLIENTEVV.NRCPF%TYPE := '-';
    V_IDUSUARIOALTERACAO VARCHAR2(10) := '946835'; --numero da demanda
   
BEGIN
   
    OPEN crs;
    LOOP    
    FETCH crs BULK COLLECT INTO V_EXCLUIPP LIMIT V_LIMITE;
        V_DONE := crs%NOTFOUND;        
        FOR FILA IN 1 .. V_EXCLUIPP.COUNT
        LOOP
           
            IF( V_EXCLUIPP(FILA).NRCPF_LOAD <> V_NRCPF )
            THEN
                
               IF(V_EXCLUIPP(FILA).NRDOCUMENTO_CPR IS NOT NULL)
               THEN                   
                    DELETE CUSTOMER.CARGAPROGRAMARELACIONAMENTO CPR
                     WHERE CPR.NRDOCUMENTO = V_EXCLUIPP(FILA).NRDOCUMENTO_CPR;
               END IF;
                    
               IF(V_EXCLUIPP(FILA).ROWID_PRP IS NOT NULL)
               THEN
                    DELETE CUSTOMER.PROGRAMARELACIONAMENTOPOS PRP
                     WHERE PRP.ROWID = V_EXCLUIPP(FILA).ROWID_PRP;
               END IF;                     
                    
                IF(V_EXCLUIPP(FILA).ROWID_CVV IS NOT NULL)
                THEN                  
                    UPDATE VIVOVALORIZA.CLIENTEVV CVV
                       SET CVV.DTEXCLUSAO = V_EXCLUIPP(FILA).DTEXCLUSAO_LOAD
                         , CVV.DTULTIMAATUALIZACAO = SYSDATE
                         , CVV.IDUSUARIOATUALIZACAO = V_IDUSUARIOALTERACAO
                     WHERE CVV.ROWID = V_EXCLUIPP(FILA).ROWID_CVV;                     
                END IF; 

				IF(V_EXCLUIPP(FILA).IDCLIENTE_LVV IS NOT NULL)
				THEN 
					 UPDATE VIVOVALORIZA.LINHAVV LVV
					    SET LVV.DTEXCLUSAO = SYSDATE
					  WHERE LVV.IDCLIENTEVV = V_EXCLUIPP(FILA).IDCLIENTE_LVV
					    AND LVV.DTEXCLUSAO IS NULL;					  
				END IF;		
                
                V_NRCPF := V_EXCLUIPP(FILA).NRCPF_LOAD;
                
            END IF;
                  
            IF( V_EXCLUIPP(FILA).ROWID_PRP IS NOT NULL OR V_EXCLUIPP(FILA).NRDOCUMENTO_CPR IS NOT NULL)
            THEN
              
                BEGIN
            
                INSERT INTO CUSTOMER.LINHASEXCLUIDASPR( IDLINHATELEFONICA
                                                      , NRLINHA
                                                      , DTEXCLUSAO
                                                      , NMUSUARIO )
                                                VALUES( V_EXCLUIPP(FILA).IDLINHATELEFONICA_LVV
                                                      , V_EXCLUIPP(FILA).NRTELEFONE_LVV
                                                      , V_EXCLUIPP(FILA).DTEXCLUSAO_LOAD
                                                      , V_IDUSUARIOALTERACAO);
                EXCEPTION
                WHEN OTHERS THEN                    
                    NULL;
                END;
                            
            END IF;
        
        END LOOP;        
        
        COMMIT;
        EXIT WHEN (V_DONE);
    END LOOP;   
    LOAD.TRUNCEXCLPROGRELACIONAMENTO;
    COMMIT;
    
END;
/

SPOOL OFF
SET ECHO OFF
SET TIMI OFF
SET TIME OFF
EXIT