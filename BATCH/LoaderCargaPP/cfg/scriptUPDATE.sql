set echo off
set feed off
set pages 0
set lines 20000
set trimspool on
set timing on
set termout off

spool UPDATEresult.txt

declare

cursor crs is
select rowid rid
from  customer.cargaprogramarelacionamento a
where a.DTATUALIZACAOPR is null;

rid varchar2(255);

begin

open crs;


loop
        fetch crs into rid;

                EXIT WHEN crs%notfound;
            update customer.cargaprogramarelacionamento a
                    set --a.DTCADASTROPR=(select c.DTINICIORELACIONAMENTO from customer.segmentacaoconsolidada c where c.NRDOCUMENTO=a.NRDOCUMENTO),
                        a.DTATUALIZACAOPR=(select c.DTINICIORELACIONAMENTO from customer.segmentacaoconsolidada c where c.NRDOCUMENTO=a.NRDOCUMENTO),
                        a.inpr=1,
                        a.incadastromanual=1,
                        a.NMLOGINATUALIZACAOPR='adminfo',
                        a.IDSISTEMAORIGEMPR=19
                where a.rowid =rid;

            COMMIT;

end loop;
end;
/
exit
