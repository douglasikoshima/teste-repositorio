
set serveroutput off

begin

    DELETE FROM ATENDIMENTO.ATENDIMENTOPROTOCSERVICO
          WHERE TRUNC (DTULTIMAALTERACAO) < TRUNC (SYSDATE - 45);
    
    COMMIT;

end;
/