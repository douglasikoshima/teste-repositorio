set echo off
set feed off
set pages 0
set lines 20000
set trimspool on
set timing off
set termout off
set time on
set autotrace on
set serveroutput on size 100000
set heading off
set feedback off
set arraysize 100
set flush off
set term off
set show off
SET VERIFY OFF

ALTER SESSION FORCE PARALLEL DML PARALLEL 16;
ALTER SESSION SET COMMIT_LOGGING = 'IMMEDIATE';
ALTER SESSION SET COMMIT_WAIT = NOWAIT;

spool PESDOC.txt

DECLARE

	 TYPE T_PESSOA_DOC IS RECORD
    (	
        V_IDDOCUMENTO CUSTOMER.DOCUMENTO.IDDOCUMENTO%TYPE --INDEX BY BINARY_INTEGER
      , V_IDPESSOASISTEMAORIGEM CUSTOMER.PESSOA.IDPESSOASISTEMAORIGEM%TYPE --INDEX BY BINARY_INTEGER
      , V_IDTIPODOCUMENTO CUSTOMER.DOCUMENTO.IDTIPODOCUMENTO%TYPE --INDEX BY BINARY_INTEGER
      , V_IDDOCUMENTOSISTEMAORIGEM CUSTOMER.PESSOADOCUMENTO.IDDOCUMENTOSISTEMAORIGEM%TYPE --INDEX BY BINARY_INTEGER
      , V_IDPESSOADOCUMENTO CUSTOMER.PESSOADOCUMENTO.IDPESSOADOCUMENTO%TYPE --INDEX BY BINARY_INTEGER    
    );

    TYPE PESSOA_DOC IS TABLE OF T_PESSOA_DOC;    
    TB_PESSOA_DOC PESSOA_DOC;
    
    CURSOR CMP IS select d.IDDOCUMENTO
                       , p.IDPESSOASISTEMAORIGEM
                       , d.IDTIPODOCUMENTO
                       , pd.IDDOCUMENTOSISTEMAORIGEM
                       , pd.IDPESSOADOCUMENTO
                    from customer.pessoaDocumento PARTITION(&1) pd  
                       , apoio.sistemaorigem so
                       , customer.pessoa p
                       , customer.documento d
                   where pd.IDSISTEMAORIGEM = so.IDSISTEMAORIGEM
                     and p.IDPESSOA = pd.IDPESSOA
                     and d.IDDOCUMENTO = pd.IDDOCUMENTO 
                     and upper(so.SGSISTEMAORIGEM) in ('SIA', 'NSIA');
                                          
    V_CONT NUMBER := 0;	
    V_CONT_ERR NUMBER := 0;  
    V_DONE BOOLEAN;
	
BEGIN
	
	DBMS_OUTPUT.PUT_LINE(CHR(10)||TO_CHAR(SYSDATE,'DD/MM/YYYY HH24:MI:SS')||' -- Inicio do processamento');			
	
	OPEN CMP;     
    LOOP
    FETCH CMP BULK COLLECT INTO TB_PESSOA_DOC LIMIT &3;
          
		V_DONE := CMP%NOTFOUND;
    
        FOR VAR IN 1 .. TB_PESSOA_DOC.COUNT LOOP
            BEGIN
            
                UPDATE CUSTOMER.pessoaDocumento  PARTITION(&1) CPD 
                   SET CPD.IDDOCUMENTOSISTEMAORIGEM = TB_PESSOA_DOC(VAR).V_IDPESSOASISTEMAORIGEM||TB_PESSOA_DOC(VAR).V_IDTIPODOCUMENTO
                 WHERE CPD.IDPESSOADOCUMENTO = TB_PESSOA_DOC(VAR).V_IDPESSOADOCUMENTO;
				                 
                V_CONT := V_CONT + 1;
                      
            EXCEPTION
            WHEN OTHERS THEN
                V_CONT_ERR := V_CONT_ERR + 1;    
            END;
        END LOOP;
		
		COMMIT;
		EXIT WHEN (V_DONE);
    END LOOP;
        
    DBMS_OUTPUT.PUT_LINE(CHR(10)||TO_CHAR(SYSDATE,'DD/MM/YYYY HH24:MI:SS')||' -- Fim do processamento');		
	COMMIT;
	
END;
/

spool off
set echo off
set timi off
set time off
exit
