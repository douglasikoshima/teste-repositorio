set serveroutput on

variable retcode number

begin
    :retcode := VOL.TRUNCLINHAPREMIUM;
end;
/
exit :retcode;
