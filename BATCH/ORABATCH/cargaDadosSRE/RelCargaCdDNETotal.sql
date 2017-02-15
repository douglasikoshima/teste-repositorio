
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

SPOOL RelatoriosCargaCdDNE/RelCargaCdDNETotal.csv

SELECT 'Total_de_logradouros_novos;Total_de_CEP_novos;Total_de_localidades_novas;Data_da_atualizacao' FROM DUAL
UNION ALL
SELECT  (SELECT COUNT (1)
             FROM localidade
            WHERE ver_nbr = 1
              AND TRUNC (dat_carga) = (SELECT TRUNC (MAX (pc.dat_carga))
                                         FROM processo_carga pc))
       || ';'
       || (SELECT COUNT (1)
             FROM cep
            WHERE ver_nbr = 1
              AND TRUNC (dat_carga) = (SELECT TRUNC (MAX (pc.dat_carga))
                                         FROM processo_carga pc))
       || ';'
       || (SELECT COUNT (1)
             FROM logradouro
            WHERE ver_nbr = 1
              AND TRUNC (dat_carga) = (SELECT TRUNC (MAX (pc.dat_carga))
                                         FROM processo_carga pc))
       || ';'
       || (SELECT to_char(TRUNC (MAX (pc.dat_carga)),'dd/mm/yyyy')
             FROM processo_carga pc)
  FROM DUAL;

SPOOL off

QUIT;
/