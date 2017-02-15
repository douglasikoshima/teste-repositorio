spool &1
set serveroutput on size 1000000

DECLARE
   TYPE TY_IDSERVICO             IS TABLE OF NUMBER;
   TYPE TY_IDLINHASISTEMAORIGEM  IS TABLE OF VARCHAR2(255);
   TYPE TY_IDSERVLINHASISTORIGEM IS TABLE OF VARCHAR2(255);
   TYPE TY_CODIGOPRODATIS        IS TABLE OF VARCHAR2(255);
   TYPE TY_DTBAIXA               IS TABLE OF DATE;
   
   V_IDSERVICO                   TY_IDSERVICO;
   V_IDLINHASISTEMAORIGEM        TY_IDLINHASISTEMAORIGEM;
   V_IDSERVLINHASISTORIGEM       TY_IDSERVLINHASISTORIGEM;
   V_CODIGOPRODATIS              TY_CODIGOPRODATIS;
   V_DTBAIXA                     TY_DTBAIXA;
   v_iErrado                        NUMBER;

BEGIN
   EXECUTE IMMEDIATE 'alter session set nls_date_format = ''DD/MM/RRRR''';
   
   begin
      select   /*+ FIRST_ROWS(4000) NO_CPU_COSTING */
         IDSERVICO ,              -- Linha.PlanoServico
         IDLINHASISTEMAORIGEM ,   -- linha.linhatelefonica
         IDSERVLINHASISTORIGEM ,  -- linha.PlanoServicoLinha
         CODIGOPRODATIS ,
         DTBAIXA  
      BULK COLLECT INTO
         V_IDSERVICO ,
         V_IDLINHASISTEMAORIGEM ,
         V_IDSERVLINHASISTORIGEM ,
         V_CODIGOPRODATIS ,
         V_DTBAIXA 
      from
         LOAD.PLANOSERVICOLINHA_TMP 
      where
         INSTATUS = 1;

      v_iErrado := 0;
      FOR i IN V_IDSERVICO.FIRST .. V_IDSERVICO.LAST
      LOOP

         dbms_output.put_line( V_IDSERVICO(i) || ';' || V_IDLINHASISTEMAORIGEM(i) || ';' || V_IDSERVLINHASISTORIGEM(i) || ';' || V_CODIGOPRODATIS(i) || ';' || TO_CHAR( V_DTBAIXA(i), 'rrrrmmdd') || ';' );
         v_iErrado := v_iErrado + 1;

      END LOOP;
      dbms_output.put_line( 'Registros inválidos..: ' || v_iErrado );

   exception
       WHEN NO_DATA_FOUND THEN NULL;
       WHEN OTHERS THEN NULL;
   end;
   
END;
/

spool off
quit
