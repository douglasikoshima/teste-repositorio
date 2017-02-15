set serveroutput on

variable retcode number

begin
    :retcode := VOL.TRUNCMAILINGIPHONE;
end;
/
exit :retcode;
