set serveroutput on

variable retcode number

begin
    :retcode := loadsup.trunctabcarga;
end;
/
exit :retcode;
