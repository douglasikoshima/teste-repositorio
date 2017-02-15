set echo on
set serveroutput on

BEGIN
  SFA.LOAD_CARTEIRAPROSPECCAOVENDA;
END;
/

EXIT

set echo off
set serveroutput off
