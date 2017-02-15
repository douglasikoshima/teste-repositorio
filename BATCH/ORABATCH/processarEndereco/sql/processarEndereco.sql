/*#########################################################
#  BATCH......: processarEndereco
#  Criado por.: Denis Hideki 10/02/2016
#  Funcional..: Loiana Moura
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

     TYPE T_PROCESSAENDERECO IS RECORD
        (                
        COD_REL_CEP_LOGRAD_BAIRRO   ADM_PROJECT.ATUALIZA_ENDERECO.COD_REL_CEP_LOGRAD_BAIRRO%TYPE,                                                               
        NUM_CEP                     ADM_PROJECT.ATUALIZA_ENDERECO.NUM_CEP%TYPE,                                          
        DSC_TIPO_LOGRAD             ADM_PROJECT.ATUALIZA_ENDERECO.DSC_TIPO_LOGRAD%TYPE,                                             
        DSC_TITULO_LOGRAD           ADM_PROJECT.ATUALIZA_ENDERECO.DSC_TITULO_LOGRAD%TYPE,                                          
        NOM_LOGRADOURO              ADM_PROJECT.ATUALIZA_ENDERECO.NOM_LOGRADOURO%TYPE,                                          
        NOM_BAIRRO                  ADM_PROJECT.ATUALIZA_ENDERECO.NOM_BAIRRO%TYPE,                                          
        DSC_LOCALIDADE              ADM_PROJECT.ATUALIZA_ENDERECO.DSC_LOCALIDADE%TYPE,                                          
        SGL_UF                      ADM_PROJECT.ATUALIZA_ENDERECO.SGL_UF%TYPE,                                          
        NOM_UF                      ADM_PROJECT.ATUALIZA_ENDERECO.NOM_UF%TYPE,                                          
        NUM_INICIAL                 ADM_PROJECT.ATUALIZA_ENDERECO.NUM_INICIAL%TYPE,                                          
        NUM_FINAL                   ADM_PROJECT.ATUALIZA_ENDERECO.NUM_FINAL%TYPE,                                           
        COD_LADO                    ADM_PROJECT.ATUALIZA_ENDERECO.COD_LADO%TYPE,
        DSC_LADO                    ADM_PROJECT.ATUALIZA_ENDERECO.DSC_LADO%TYPE,
        SGL_PAIS                    ADM_PROJECT.ATUALIZA_ENDERECO.SGL_PAIS%TYPE,                                          
        NOM_PAIS                    ADM_PROJECT.ATUALIZA_ENDERECO.NOM_PAIS%TYPE,                                          
        COD_NAC_LOCALIDADE          ADM_PROJECT.ATUALIZA_ENDERECO.COD_NAC_LOCALIDADE%TYPE,                                          
        COD_IBGE                    ADM_PROJECT.ATUALIZA_ENDERECO.COD_IBGE%TYPE,                                          
        SGL_AREA_LOCAL              ADM_PROJECT.ATUALIZA_ENDERECO.SGL_AREA_LOCAL%TYPE,                                          
        IN_AREA_RISCO               ADM_PROJECT.ATUALIZA_ENDERECO.IN_AREA_RISCO%TYPE,                                          
        IN_AREA_CONCESSAO           ADM_PROJECT.ATUALIZA_ENDERECO.IN_AREA_CONCESSAO%TYPE,                                          
        IN_AREA_RURAL               ADM_PROJECT.ATUALIZA_ENDERECO.IN_AREA_RURAL%TYPE                      
        );
      
   TYPE TB_PROCESSAENDERECO IS TABLE OF T_PROCESSAENDERECO INDEX BY BINARY_INTEGER;  
   V_PROCESSAENDERECO TB_PROCESSAENDERECO;

   V_COUNT NUMBER;
   V_STATUS_HIST NUMBER;
   V_STATUS_ENDER NUMBER;
   V_ID_ARQUIVO NUMBER;
   V_COUNT_HIST NUMBER;
   V_COUNT_FIM NUMBER := 0;
   V_NOM_ARQUIVO VARCHAR2(50) := SUBSTR(TRIM('&1'),-26,22);

BEGIN
    
    --VERIFICA SE EXISTE UM NOM_ARQUIVO VAZIO
    SELECT COUNT(1)
        INTO V_COUNT_HIST
        FROM ADM_PROJECT.HISTORICO_ENDERECO_ALT HIST
        WHERE HIST.STATUS = 0
          AND HIST.NOM_ARQUIVO IS NULL;
    
    --SELECT ENTIDADE HISTORICO_ENDERECO_ALT ID_ARQUIVO
    IF(V_COUNT_HIST >= 0) THEN
        SELECT ID_ARQUIVO
            INTO V_ID_ARQUIVO
            FROM(SELECT HIST.ID_ARQUIVO
                FROM ADM_PROJECT.HISTORICO_ENDERECO_ALT HIST
                ORDER BY HIST.ID_ARQUIVO DESC
            )
        WHERE ROWNUM <= 1;    
    ELSE
        SELECT HIST.ID_ARQUIVO  
            INTO V_ID_ARQUIVO 
            FROM ADM_PROJECT.HISTORICO_ENDERECO_ALT HIST
            WHERE HIST.STATUS = 0
              AND HIST.NOM_ARQUIVO IS NULL;
    END IF;
 
    SELECT AE.COD_REL_CEP_LOGRAD_BAIRRO                                                               
         , AE.NUM_CEP                                                       
         , AE.DSC_TIPO_LOGRAD                                               
         , AE.DSC_TITULO_LOGRAD                                             
         , AE.NOM_LOGRADOURO                                                
         , AE.NOM_BAIRRO                                                    
         , AE.DSC_LOCALIDADE                                                
         , AE.SGL_UF                                                        
         , AE.NOM_UF                                                        
         , AE.NUM_INICIAL                                                   
         , AE.NUM_FINAL                                                     
         , AE.COD_LADO                                                      
         , DECODE(AE.COD_LADO, 1, 'IMPAR', 2, 'PAR', 0, 'AMBOS') AS DSC_LADO
         , AE.SGL_PAIS                                                      
         , AE.NOM_PAIS                                                      
         , AE.COD_NAC_LOCALIDADE                                            
         , AE.COD_IBGE                                                      
         , AE.SGL_AREA_LOCAL                                                
         , AE.IN_AREA_RISCO                                                 
         , AE.IN_AREA_CONCESSAO                                             
         , AE.IN_AREA_RURAL
      BULK COLLECT INTO V_PROCESSAENDERECO                                                 
      FROM ADM_PROJECT.ATUALIZA_ENDERECO AE;

      DBMS_OUTPUT.PUT_LINE('sreID'||';'||'zipCode'||';'||'addressTypeStreetType'                                               
                        ||';'||'addressTitleStreetTitle'||';'||'streetName'||';'||'neighborhood'                                                    
                        ||';'||'city'||';'||'stateInitials'||';'||'state'                                                        
                        ||';'||'NUM_INICIAL'||';'||'NUM_FINAL'||';'||'COD_LADO'                                                      
                        ||';'||'DSC_LADO'||';'||'SGL_PAIS'||';'||'NOM_PAIS'
                        ||';'||'nacLocalityCode'||';'||'COD_IBGE'||';'||'SGL_AREA_LOCAL'                                                
                        ||';'||'riskArea'||';'||'concessionArea'||';'||'fatb'  );


    FOR VAR IN 1..V_PROCESSAENDERECO.COUNT 
    LOOP
    
        DBMS_OUTPUT.PUT_LINE(V_PROCESSAENDERECO(VAR).COD_REL_CEP_LOGRAD_BAIRRO||';'||V_PROCESSAENDERECO(VAR).NUM_CEP||';'||V_PROCESSAENDERECO(VAR).DSC_TIPO_LOGRAD                                               
                        ||';'||V_PROCESSAENDERECO(VAR).DSC_TITULO_LOGRAD||';'||V_PROCESSAENDERECO(VAR).NOM_LOGRADOURO||';'||V_PROCESSAENDERECO(VAR).NOM_BAIRRO                                                    
                        ||';'||V_PROCESSAENDERECO(VAR).DSC_LOCALIDADE||';'||V_PROCESSAENDERECO(VAR).SGL_UF||';'||V_PROCESSAENDERECO(VAR).NOM_UF                                                        
                        ||';'||V_PROCESSAENDERECO(VAR).NUM_INICIAL||';'||V_PROCESSAENDERECO(VAR).NUM_FINAL||';'||V_PROCESSAENDERECO(VAR).COD_LADO                                                      
                        ||';'||V_PROCESSAENDERECO(VAR).DSC_LADO||';'||V_PROCESSAENDERECO(VAR).SGL_PAIS||';'||V_PROCESSAENDERECO(VAR).NOM_PAIS
                        ||';'||V_PROCESSAENDERECO(VAR).COD_NAC_LOCALIDADE||';'||V_PROCESSAENDERECO(VAR).COD_IBGE||';'||V_PROCESSAENDERECO(VAR).SGL_AREA_LOCAL                                                
                        ||';'||V_PROCESSAENDERECO(VAR).IN_AREA_RISCO||';'||V_PROCESSAENDERECO(VAR).IN_AREA_CONCESSAO||';'||V_PROCESSAENDERECO(VAR).IN_AREA_RURAL  );
       
        
        V_COUNT_FIM := V_COUNT_FIM + 1;
        
        --SELECT ENTIDADE ATUALIZA_ENDERECO V_STATUS_ENDER   
        SELECT AE.STATUS
            INTO V_STATUS_ENDER
            FROM ADM_PROJECT.ATUALIZA_ENDERECO AE
            WHERE AE.COD_REL_CEP_LOGRAD_BAIRRO = V_PROCESSAENDERECO(VAR).COD_REL_CEP_LOGRAD_BAIRRO;
         
              
        --UPDATE ENTIDADE ATUALIZA_ENDERECO STATUS
        IF (V_STATUS_ENDER = 0) THEN         
            UPDATE ADM_PROJECT.ATUALIZA_ENDERECO AE                                   
                SET AE.ID_ARQUIVO = V_ID_ARQUIVO
                  , AE.STATUS = 1
                  , AE.DAT_ATUALIZACAO = SYSDATE
             WHERE AE.COD_REL_CEP_LOGRAD_BAIRRO = V_PROCESSAENDERECO(VAR).COD_REL_CEP_LOGRAD_BAIRRO;
        ELSIF (V_STATUS_ENDER = 3) THEN
            UPDATE ADM_PROJECT.ATUALIZA_ENDERECO AE                                   
                SET AE.ID_ARQUIVO = V_ID_ARQUIVO
                  , AE.STATUS = 2
                  , AE.DAT_ATUALIZACAO = SYSDATE
              WHERE AE.COD_REL_CEP_LOGRAD_BAIRRO = V_PROCESSAENDERECO(VAR).COD_REL_CEP_LOGRAD_BAIRRO;
        END IF;
    
        IF(V_COUNT = 100000)
        THEN
            V_COUNT := V_COUNT + 1;
            COMMIT;        
        END IF;
        
    END LOOP;

    
    --SELECT ENTIDADE HISTORICO_ENDERECO_ALT V_STATUS_HIST
    SELECT HIST.STATUS
        INTO V_STATUS_HIST
        FROM ADM_PROJECT.HISTORICO_ENDERECO_ALT HIST
        WHERE HIST.ID_ARQUIVO = V_ID_ARQUIVO;

    --UPDATE ENTIDADE HISTORICO_ENDERECO_ALT NOME ARQUIVO STATUS
        IF (V_STATUS_HIST = 0) THEN
            UPDATE ADM_PROJECT.HISTORICO_ENDERECO_ALT AH
               SET AH.NOM_ARQUIVO = V_NOM_ARQUIVO
                 , AH.STATUS = 1
                 , AH.DT_EXECUCAO = SYSDATE
             WHERE AH.ID_ARQUIVO = V_ID_ARQUIVO;
        ELSE
            UPDATE ADM_PROJECT.HISTORICO_ENDERECO_ALT AH
               SET AH.NOM_ARQUIVO = V_NOM_ARQUIVO
                 , AH.STATUS = 2
                 , AH.DT_EXECUCAO = SYSDATE
             WHERE AH.ID_ARQUIVO = V_ID_ARQUIVO; 
        END IF;
    
    COMMIT;
        
    DBMS_OUTPUT.PUT_LINE('FIM' ||':'|| TO_CHAR(V_COUNT_FIM));     
END;
/

SPOOL OFF
SET ECHO OFF
SET TIMI OFF
SET TIME OFF
EXIT