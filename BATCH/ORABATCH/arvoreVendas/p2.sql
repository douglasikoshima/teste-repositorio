set echo on
set serveroutput on


declare
   v_rid          ROWID;
   vi_n1          VARCHAR2 (200);
   vi_n2          VARCHAR2 (200);
   vi_n3          VARCHAR2 (200);
   vi_n4          VARCHAR2 (200);
   vi_n5          VARCHAR2 (200);
   vi_nmgrupo     VARCHAR2 (200);
   vo_idcontato   NUMBER;
   vo_cderro      NUMBER;
   vo_dserro      VARCHAR2 (200);

   cursor cr
   is
      select   ROWID rid,
               c.n1,
               TRIM(NVL (replace (c.n2, CHR (13), ''), 'x')),
               TRIM(NVL (replace (c.n3, CHR (13), ''), 'x')),
               TRIM(NVL (replace (c.n4, CHR (13), ''), 'x')),
               TRIM(NVL (replace (c.n5, CHR (13), ''), 'x')),
               c.nmgrupo
        from   load.cargacontatogrupo c
       where   idcontato is null and tipo like 'C%';

   v_idgrupo      NUMBER;
   v_idcontato    NUMBER;
   v_count        NUMBER;
begin
   open cr;

   loop
      fetch cr into v_rid, vi_n1, vi_n2, vi_n3, vi_n4, vi_n5, vi_nmgrupo;

      exit when cr%notfound;

      begin
      
     --DBMS_OUTPUT.PUT_LINE(v_rid||','||vi_n1||','||vi_n2||','||vi_n3||','||vi_n4||','||vi_n5||','||vi_nmgrupo);
      
      
         if vi_n1 is null
         then
            raise NO_DATA_FOUND;
            vo_cderro := 1;
         end if;

         select   COUNT ( * )
           into   v_count
           from   acesso.grupo
          where   nmgrupo = vi_nmgrupo;


         if v_count > 0
         then
            select   idgrupo
              into   v_idgrupo
              from   acesso.grupo
             where   nmgrupo = vi_nmgrupo;

            update   load.cargacontatogrupo c
               set   c.idgrupo = v_idgrupo
             where   ROWID = v_rid;

            commit;

            vo_cderro := 0;
         else
            vo_cderro := 1;
         end if;


         select   COUNT ( * )
           into   v_count
           from   load.cargacontato
          where       idcontato is not null
                  and NVL (TRIM (n1), 'x') = TRIM (vi_n1)
                  and NVL (TRIM (n2), 'x') = TRIM (vi_n2)
                  and NVL (TRIM (n3), 'x') = TRIM (vi_n3)
                  and NVL (TRIM (n4), 'x') = TRIM (vi_n4)
                  and NVL (TRIM (n5), 'x') = TRIM (vi_n5)
                  and tipo like 'C%';

--DBMS_OUTPUT.PUT_LINE('v_count= '||v_count);

         if v_count > 0
         then
            select  distinct idcontato
              into   v_idcontato
              from   load.cargacontato
             where       idcontato is not null
                     and NVL (TRIM (n1), 'x') = TRIM (vi_n1)
                     and NVL (TRIM (n2), 'x') = TRIM (vi_n2)
                     and NVL (TRIM (n3), 'x') = TRIM (vi_n3)
                     and NVL (TRIM (n4), 'x') = TRIM (vi_n4)
                     and NVL (TRIM (n5), 'x') = TRIM (vi_n5)
                     and tipo like 'C%';

            update   load.cargacontatogrupo c
               set   c.idcontato = v_idcontato
             where   ROWID = v_rid;

            commit;

            if vo_cderro = 1
            then
               vo_cderro := 2;
            end if;
         else
            if vo_cderro = 1
            then
               vo_cderro := 3;
            else
               vo_cderro := 2;
            end if;
         end if;

         update   load.cargacontatogrupo c
            set   c.cderro = vo_cderro
          where   ROWID = v_rid;

         commit;
      exception
         when OTHERS
         then
            DBMS_OUTPUT.PUT_LINE(sqlerrm);
      end;
   end loop;
end;
/

EXIT 0;

set serveroutput off
set echo off