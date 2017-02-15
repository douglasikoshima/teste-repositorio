whenever sqlerror exit 1;
declare
    o_cdresultado number;
    o_dsresultado varchar(255);
begin
  CATALOGOPRS_OW.load_catalogo_local_vivonet(o_cdresultado, o_dsresultado);
  if o_cdresultado <> 0 then
    RAISE_APPLICATION_ERROR(-20002, 'Erro: ' || o_cdresultado || ': ' || o_dsresultado);
  end if;
end;
/
exit;