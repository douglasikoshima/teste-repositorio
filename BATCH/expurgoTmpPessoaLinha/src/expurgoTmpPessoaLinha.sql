
set serveroutput on

declare
   vDia        VARCHAR2(3);
   CDERRO      NUMBER;
   DSERRO      VARCHAR2(255);
begin
    SELECT LPAD(TO_CHAR(TRUNC(SYSDATE-1),'DD'),2,0) INTO vDia FROM DUAL;
    CUSTOMER.PRC_PESSOA_AUX(vDia,CDERRO,DSERRO);
end;
/