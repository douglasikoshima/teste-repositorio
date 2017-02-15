set serveroutput on

variable retcode number

begin
    :retcode := LOAD.TRUNCPLANOSERVICOTMP;
end;
/
exit :retcode;
