set echo off
set pages 0
set feedback off
set trimspool on
set heading off
set lines 32767
set arraysize 100
set timing off
set time off
set flush off
set term off
set show off
SET VERIFY OFF

SPOOL RelatoriosCadManual/RelCadManualTotal.csv

SELECT 'Total_de_Logradouros_novos;Total_de_CEP_novos;Total_de_Localidades_novas;Data_Geracao_Relatorio' FROM DUAL
UNION ALL
SELECT  (SELECT COUNT(1)
            FROM LOGRADOURO
            WHERE USUARIO != 'loadDNE'
            AND VER_NBR > 1
            AND TRUNC (DAT_ATUALIZACAO) >= TRUNC (SYSDATE - 30))
        || ';'  
		|| (SELECT COUNT(1)
            FROM CEP
            WHERE USUARIO != 'loadDNE'
            AND VER_NBR > 1
            AND TRUNC (DAT_ATUALIZACAO) >= TRUNC (SYSDATE - 30))
        || ';'
        || (SELECT COUNT(1)
            FROM LOCALIDADE
            WHERE USUARIO != 'loadDNE'
            AND VER_NBR > 1
            AND TRUNC (DAT_ATUALIZACAO) >= TRUNC (SYSDATE - 30))
        || ';'
        || to_char(TRUNC(SYSDATE),'dd/mm/yyyy')
  FROM DUAL;

SPOOL off

QUIT;
/