set echo on
set serveroutput on

declare
   v_idcampo_os      VARCHAR2 (50) := 2927;
   v_idcampo_osi     VARCHAR2 (50) := 2928;
   v_idtipolinha     VARCHAR2 (50) := 1740;
   v_idufoperadora   VARCHAR2 (50) := 21;
   v_tipocarteira    NUMBER := 13;
   v_count           NUMBER;
   vo_dserro         NUMBER;
begin
   --TIPOCARTEIRA


   begin
      merge into   contatoadm.contatotipocarteira e
           USING   (select   cf.idcontato
                      from   (select   distinct idcontato
                                from   load.cargacontato
                               where   tipo like 'V%') cc,
                             contatoadm.contatofolha cf
                     --,apoio.tipocarteira tc
                     where   cc.idcontato = cf.idcontato
                             and not exists
                                   (select   1
                                      from   contatoadm.contatotipocarteira
                                     where   idcontato = cc.idcontato
                                             and idtipocarteira =
                                                   v_tipocarteira)) h
              on   (e.idcontato = h.idcontato
                    and e.idtipocarteira = v_tipocarteira)
      when not matched
      then
         insert              (idcontatotipocarteira,
                              idcontato,
                              idtipocarteira,
                              idusuarioalteracao,
                              dtultimaalteracao)
             values   (contatoadm.contatotipocarteirasq.nextval,
                       h.idcontato,
                       v_tipocarteira,
                       777,
                       SYSDATE);

      commit;
   exception
      when OTHERS
      then
         DBMS_OUTPUT.PUT_LINE ('contatotipocarteira - ' || sqlerrm);
   end;



   begin
      merge into   contatoadm.contatoufoperadora e
           USING   (select   c.idcontato
                      from   (select   distinct idcontato
                                from   load.cargacontato
                               where   tipo like 'V%') c,
                             contatoadm.contatofolha cf
                     where   c.idcontato = cf.idcontato) h
              on   (e.idcontato = h.idcontato
                    and e.idufoperadora = v_idufoperadora)
      when not matched
      then
         insert              (idcontatoufoperadora,
                              idufoperadora,
                              idcontato,
                              dtiniciovigencia,
                              dtfimvigencia,
                              indisponibilidade,
                              idusuarioalteracao,
                              dtultimaalteracao)
             values   (contatoadm.contatoufoperadorasq.nextval,
                       v_idufoperadora,
                       h.idcontato,
                       TO_DATE (SYSDATE, 'dd/mm/rr'),
                       null,
                       1,
                       777,
                       SYSDATE);

      commit;

      commit;
   exception
      when OTHERS
      then
         DBMS_OUTPUT.PUT_LINE ('contatoufoperadora - ' || sqlerrm);
   end;

   begin
      merge into   contatoadm.contatotiporelacionamento e
           USING   (select   t.idtiporelacionamento as idtiporelacionamento,
                             c.idcontato
                      from   (select   distinct idcontato
                                from   load.cargacontato
                               where   tipo like 'V%') c,
                             customer.tiporelacionamento t,
                             contatoadm.contatofolha cf
                     where   t.nmtiporelacionamento = 'CLIENTE'
                             and cf.idcontato = c.idcontato) h
              on   (e.idcontato = h.idcontato
                    and e.idtiporelacionamento = h.idtiporelacionamento)
      when not matched
      then
         insert              (idcontatotiporelacionamento,
                              idtiporelacionamento,
                              idcontato,
                              idusuarioalteracao,
                              dtultimaalteracao)
             values   (contatoadm.contatotiporelacionamentosq.nextval,
                       h.idtiporelacionamento,
                       h.idcontato,
                       777,
                       SYSDATE);

      commit;
   exception
      when OTHERS
      then
         DBMS_OUTPUT.PUT_LINE ('contatotiporelacionamento - ' || sqlerrm);
   end;


   begin
      merge into   contatoadm.contatosegmentacao e
           USING   (select   s.idsegmentacao as idsegmentacao,
                             c.idcontato as idcontato
                      from   (select   distinct idcontato
                                from   load.cargacontato
                               where   tipo like 'V%') c,
                             (select   idsegmentacao
                                from   apoio.segmentacao
                               where   dssegmentacao = 'NÃO SEGMENTADO') s,
                             contatoadm.contatofolha cf
                     where   cf.idcontato = c.idcontato) h
              on   (e.idcontato = h.idcontato
                    and e.idsegmentacao = h.idsegmentacao)
      when not matched
      then
         insert              (idcontatosegmentacao,
                              idsegmentacao,
                              idcontato,
                              idusuarioalteracao,
                              dtultimaalteracao)
             values   (contatoadm.contatosegmentacaosq.nextval,
                       h.idsegmentacao,
                       h.idcontato,
                       777,
                       SYSDATE);

      commit;
   exception
      when OTHERS
      then
         DBMS_OUTPUT.PUT_LINE ('contatosegmentacao - ' || sqlerrm);
   end;



   begin
      merge into   contatoadm.contatotipolinha e
           USING   (select   c.idcontato as idcontato
                      from   (select   distinct idcontato
                                from   load.cargacontato
                               where   tipo like 'V%') c,
                             apoio.tipolinha t,
                             contatoadm.contatofolha cf
                     where   cf.idcontato = c.idcontato
                             and t.idtipolinha = v_idtipolinha) h
              on   (e.idcontato = h.idcontato
                    and e.idtipolinha = v_idtipolinha)
      when not matched
      then
         insert              (idcontatotipolinha,
                              idtipolinha,
                              idcontato,
                              idusuarioalteracao,
                              dtultimaalteracao)
             values   (contatoadm.contatotipolinhasq.nextval,
                       v_idtipolinha,
                       h.idcontato,
                       777,
                       SYSDATE);

      commit;
   exception
      when OTHERS
      then
         DBMS_OUTPUT.PUT_LINE ('contatotipolinha - ' || sqlerrm);
   end;


   begin
      insert into contatoadm.contatogrupo (idcontatogrupo,
                                           idgrupo,
                                           idcontato,
                                           idusuarioalteracao,
                                           dtultimaalteracao)
         select   contatoadm.contatogruposq.nextval,
                  c.idgrupo,
                  c.idcontato,
                  777,
                  SYSDATE
           from   (select   distinct idcontato, idgrupo, nmgrupo
                     from   load.cargacontatogrupo
                    where       idcontato is not null
                            and idgrupo is not null
                            and tipo like 'V%') c
          where   not exists
                     (select   1
                        from   contatoadm.contatogrupo d
                       where   d.idcontato = c.idcontato
                               and d.idgrupo = c.idgrupo);

      commit;
   exception
      when OTHERS
      then
         DBMS_OUTPUT.PUT_LINE ('contatogrupo - ' || sqlerrm);
   end;



   begin
      insert into contatoadm.sequencia (idsequencia,
                                        idcontatogrupo,
                                        idtiposequencia,
                                        sqordem,
                                        idusuarioalteracao,
                                        dtultimaalteracao,
                                        dtexclusao)
         select   contatoadm.sequenciasq.nextval,
                  cf.idcontatogrupo,
                  1,
                  0,
                  777,
                  SYSDATE,
                  null
           from   load.cargacontatogrupo c, contatoadm.contatogrupo cf
          where       c.idcontato = cf.idcontato
                  and c.idgrupo = cf.idgrupo
                  and c.tipo like 'V%';

      --and c.nmgrupo = 'E-MAIL';

      commit;
   exception
      when OTHERS
      then
         DBMS_OUTPUT.PUT_LINE ('sequencia - ' || sqlerrm);
   end;


   begin
      merge into   contatoadm.contatofolhacampo e
           USING   (select   c.idcontato as idcontato
                      from   (select   distinct idcontato
                                from   load.cargacontato
                               where   inosbd = '2' and tipo like 'V%') c,
                             customer.ufoperadora u,
                             apoio.tipolinha t,
                             contatoadm.contatofolha cf
                     where   cf.idcontato = c.idcontato
                             and t.idtipolinha = v_idtipolinha) h
              on   (    e.idcontato = h.idcontato
                    and e.idufoperadora = v_idufoperadora
                    and e.idtipolinha = v_idtipolinha
                    and e.idcampo = v_idcampo_osi
                    and e.idfaseprocesso = 1)
      when not matched
      then
         insert              (idcontatofolhacampo,
                              idcontato,
                              idufoperadora,
                              idtipolinha,
                              idcampo,
                              sqordemapresentacao,
                              idfaseprocesso,
                              idusuarioalteracao,
                              dtultimaalteracao,
                              idsubformulario)
             values   (contatoadm.contatofolhacamposq.nextval,
                       h.idcontato,
                       v_idufoperadora,
                       v_idtipolinha,
                       v_idcampo_osi,
                       1,
                       1,
                       777,
                       SYSDATE,
                       null);

      commit;
   exception
      when OTHERS
      then
         DBMS_OUTPUT.PUT_LINE ('contatofolhacampo OSI - ' || sqlerrm);
   end;


   begin
      merge into   contatoadm.contatofolhacampo e
           USING   (select   c.idcontato as idcontato
                      from   (select   distinct idcontato
                                from   load.cargacontato
                               where   inosbd = '1' and tipo like 'V%') c,
                             customer.ufoperadora u,
                             apoio.tipolinha t,
                             contatoadm.contatofolha cf
                     where   cf.idcontato = c.idcontato
                             and t.idtipolinha = v_idtipolinha) h
              on   (    e.idcontato = h.idcontato
                    and e.idufoperadora = v_idufoperadora
                    and e.idtipolinha = v_idtipolinha
                    and e.idcampo = v_idcampo_os
                    and e.idfaseprocesso = 1)
      when not matched
      then
         insert              (idcontatofolhacampo,
                              idcontato,
                              idufoperadora,
                              idtipolinha,
                              idcampo,
                              sqordemapresentacao,
                              idfaseprocesso,
                              idusuarioalteracao,
                              dtultimaalteracao,
                              idsubformulario)
             values   (contatoadm.contatofolhacamposq.nextval,
                       h.idcontato,
                       v_idufoperadora,
                       v_idtipolinha,
                       v_idcampo_os,
                       1,
                       1,
                       777,
                       SYSDATE,
                       null);

      commit;
   exception
      when OTHERS
      then
         DBMS_OUTPUT.PUT_LINE ('contatofolhacampo OS - '|| sqlerrm);
   end;
end;
/

EXIT 0;

set serveroutput off
set echo off