set echo on
set serveroutput on

BEGIN
  LOAD.PRC_CARGACONTATO;
  LOAD.PRC_CARGACONTATOGRUPO;
END;
/

EXIT

set serveroutput off
set echo off