set time on
set timing on
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
   
   v_iAtualizado                 NUMBER;
   v_iErrado                     NUMBER;
   v_sqlstmt                     varchar2(16000);

BEGIN
   dbms_output.put_line( '--- Inicio de Atualizacao ---' );
   EXECUTE IMMEDIATE 'alter session set nls_date_format = ''DD/MM/RRRR''';
   
   v_iAtualizado := 0;
   v_iErrado := 0;
   
   select   /*+ FIRST_ROWS(5000) NO_CPU_COSTING */
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
   WHERE
      INSTATUS = 0;


   FOR i IN V_IDSERVICO.FIRST .. V_IDSERVICO.LAST
   LOOP
      --dbms_output.put_line( 'Leu IDSERVLINHASISTORIGEM ' || '[' || V_IDSERVLINHASISTORIGEM(i) || ']' );
      --dbms_output.put_line( 'Leu IDLINHASISTEMAORIGEM ' || '[' || V_IDLINHASISTEMAORIGEM(i) || ']' );

      begin

         update /*+ FIRST_ROWS(5000) NO_CPU_COSTING */
             linha.planoservicolinha 
         set DTEXPIRACAO = V_DTBAIXA(i)
         where idservlinhasistorigem = V_IDSERVLINHASISTORIGEM(i)
         and idLinhaTelefonica in ( select idLinhaTelefonica from linha.linhatelefonica where idLinhaSistemaOrigem = V_IDLINHASISTEMAORIGEM(i) )
         and idServico = ( select idservico from linha.planoservico where sgservico = TO_CHAR(V_IDSERVICO(i)) );
         
         --dbms_output.put_line( 'Atualizou ' || sql%rowcount || ' linhas' );

         if sql%rowcount > 0 then
            --dbms_output.put_line( 'Atualizou IDLINHASISTEMAORIGEM ' || '[' || V_IDLINHASISTEMAORIGEM(i) || '] com DTEXPIRACAO = [' || TO_CHAR(V_DTBAIXA(i),'DD/MM/RRRR') || ']' );
            UPDATE /*+ FIRST_ROWS(5000) NO_CPU_COSTING */
                  LOAD.PLANOSERVICOLINHA_TMP SET INSTATUS = 2 
            where idservlinhasistorigem = V_IDSERVLINHASISTORIGEM(i)
            and IDLINHASISTEMAORIGEM = V_IDLINHASISTEMAORIGEM(i)
            and idServico = V_IDSERVICO(i);

            v_iAtualizado := v_iAtualizado + 1;
         else
            --dbms_output.put_line( 'Descartado ==> IDSERVICO [' || V_IDSERVICO(i) || '] - IDSERVLINHASISTORIGEM [' || V_IDSERVLINHASISTORIGEM(i) || '] - IDLINHASISTEMAORIGEM [' || V_IDLINHASISTEMAORIGEM(i) || ']' );
            
            v_sqlstmt :=
            'UPDATE LOAD.PLANOSERVICOLINHA_TMP SET INSTATUS = 1 where idservlinhasistorigem ';
            
            IF V_IDSERVLINHASISTORIGEM(i) IS NOT NULL THEN
               v_sqlstmt := v_sqlstmt || ' = ' || '''' || V_IDSERVLINHASISTORIGEM(i) || '''';
            ELSE
               v_sqlstmt := v_sqlstmt || 'IS NULL ';
            END IF;
            
            IF V_IDLINHASISTEMAORIGEM(i) IS NOT NULL THEN
               v_sqlstmt := v_sqlstmt || ' and IDLINHASISTEMAORIGEM = ' || '''' || V_IDLINHASISTEMAORIGEM(i) || '''';
            ELSE
               v_sqlstmt := v_sqlstmt || ' and IDLINHASISTEMAORIGEM IS NULL ';
            END IF;

            IF V_IDSERVICO(i) IS NOT NULL THEN
               v_sqlstmt := v_sqlstmt || ' and idServico = ' || V_IDSERVICO(i);
            ELSE
               v_sqlstmt := v_sqlstmt || ' and idServico IS NULL ';
            END IF;

           --dbms_output.put_line( v_sqlstmt );
           
           execute immediate v_sqlstmt;
            
            v_iErrado := v_iErrado + 1;
         end if;
         COMMIT;
      exception
         when others then
            rollback;
            dbms_output.put_line( 'Erro Oracle: ' || SQLCODE || ' - ' || SQLERRM );
      end;

   END LOOP;

   dbms_output.put_line( 'Registros atualizados: ' || v_iAtualizado );
   dbms_output.put_line( 'Registros invalidos..: ' || v_iErrado );
END;
/

set timi off
spool off
quit
