
set serveroutput on

variable retcode number

declare
   v_codret number;
   v_msg    varchar2(256);
begin

    CargaIphone('&1',v_codret,v_msg);
    :retcode := v_codret;

end;
/
exit :retcode;