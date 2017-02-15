set echo on
set serveroutput on

DECLARE 

  v_rid rowid;    
  VI_nmgrupo VARCHAR2(200);
  VI_n1 VARCHAR2(200);
  VI_n2 VARCHAR2(200);
  VI_idcontato VARCHAR2(200);
  VI_tipo VARCHAR2(200);
  VI_idgrupo VARCHAR2(200);
  VI_idcontatogrupo VARCHAR2(200);
  VI_idsequencia VARCHAR2(200);
  VI_qtdseq VARCHAR2(200);
  
CURSOR CR IS 
SELECT rowid rid
     , C.nmgrupo -- NMGRUPO
     , C.n1 -- NMPATH
     , C.idcontato 
     , C.tipo  -- HABILITADO
     , C.n2     -- SEQUENCIA
  FROM LOAD.CARGACONTATOGRUPO C;
 
BEGIN 

    DBMS_OUTPUT.PUT_LINE('Inicio de processamento: '||TO_CHAR(SYSDATE,'DD/MM/YYYY HH24:MI:SS'));
    
OPEN CR;
LOOP
    FETCH cr INTO v_rid,
                  VI_nmgrupo,
                  VI_n1,                  
                  VI_idcontato,
                  VI_tipo,
                  VI_n2;
    exit when cr%notfound;
           
    begin
    
        SELECT IDGRUPO
          INTO VI_idgrupo    
          FROM acesso.grupo 
         WHERE nmgrupo = VI_nmgrupo
           AND ROWNUM <= 1; 
                         
       EXCEPTION
        WHEN NO_DATA_FOUND THEN        
        DBMS_OUTPUT.PUT_LINE(CHR(10)||'CONTINUE ** Grupo: nmgrupo='||VI_nmgrupo||' nao encontrado');
                        
    end;   
      
   IF (VI_idgrupo IS NOT NULL) THEN
    BEGIN
    
        SELECT IDCONTATOGRUPO
          INTO VI_idcontatogrupo    
          FROM contatoadm.contatogrupo 
         WHERE idgrupo = VI_idgrupo  
           AND idcontato = VI_idcontato
           AND ROWNUM <= 1;        
         
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE(CHR(10)||'contatogrupo:  idgrupo='||VI_idgrupo||' idcontato='||VI_idcontato||'  nao encontrado');
               
    END;
      
       IF (VI_idcontatogrupo IS NOT NULL) THEN
       
        BEGIN
                   
            SELECT a.IDSEQUENCIA
              INTO VI_idsequencia 
              FROM contatoadm.sequencia a 
             WHERE a.IDCONTATOGRUPO = VI_idcontatogrupo
               AND A.IDTIPOSEQUENCIA = VI_n2
               AND ROWNUM <= 1; 
               
            DBMS_OUTPUT.PUT_LINE('1- Excluido sequencia IDSEQUENCIA '||VI_idsequencia);
                        
			DELETE contatoadm.tiporetornosequencia
             WHERE idsequencia = VI_idsequencia;
           
			 
			 DELETE contatoadm.nivelsequencia
             WHERE idsequencia = VI_idsequencia;  
                    
			DELETE contatoadm.sequencia a
             WHERE idsequencia = VI_idsequencia;
           
           
            SELECT COUNT(IDSEQUENCIA) 
              INTO VI_qtdseq
              FROM contatoadm.sequencia
             WHERE IDCONTATOGRUPO = VI_idcontatogrupo;
             
             -- CASO NAO EXISTAM MAIS SEQUENCIAS PARA ESTE GRUPO, OS PAIS SERAO APAGADOS
             IF (VI_qtdseq = 0) THEN
             
                DBMS_OUTPUT.PUT_LINE('1- Excluido ContatoGrupo IDCONTATOGRUPO '||VI_idcontatogrupo);
                
                DELETE contatoadm.contatogrupo
                 WHERE idgrupo = VI_idgrupo 
                   AND idcontato = VI_idcontato;
                
             END IF;
              
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                
                SELECT COUNT(IDSEQUENCIA) 
                  INTO VI_qtdseq
                  FROM contatoadm.sequencia
                 WHERE IDCONTATOGRUPO = VI_idcontatogrupo;
                 
                IF (VI_qtdseq = 0) THEN             
                    DBMS_OUTPUT.PUT_LINE('2- Excluido ContatoGrupo IDCONTATOGRUPO; '||VI_idcontatogrupo);                    
                    DELETE contatoadm.contatogrupo
                     WHERE idgrupo = VI_idgrupo 
                       AND idcontato = VI_idcontato;                    
                END IF;
                           
        END;
       
       END IF; 
   END IF;  
    
END LOOP;

  COMMIT;
  CLOSE CR; 
  DBMS_OUTPUT.PUT_LINE(CHR(10)||'Fim de processamento: '||TO_CHAR(SYSDATE,'DD/MM/YYYY HH24:MI:SS'));
  
EXCEPTION  
    WHEN OTHERS THEN
   -- ROLLBACK;
    DBMS_OUTPUT.PUT_LINE('Script encerrado com erro. Codigo: ' || SQLCODE || '  Descrição do erro: ' || SQLERRM); 
END;
/

EXIT

set serveroutput off
set echo off