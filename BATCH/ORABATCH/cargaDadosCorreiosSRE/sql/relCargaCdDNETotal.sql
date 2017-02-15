
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

SPOOL relatoriosCargaCdDNE/relCargaCdDNETotal.csv

SELECT 'Total_de_logradouros_novos;Total_de_CEP_novos;Total_de_localidades_novas;Data_da_atualizacao' FROM DUAL
UNION ALL
SELECT  (SELECT COUNT (1)
             FROM ADM_PROJECT.localidade
            WHERE ver_nbr = 1
              AND TRUNC (dat_carga) = (SELECT TRUNC (MAX (pc.dat_carga))
                                         FROM ADM_PROJECT.processo_carga pc))
       || ';'
       || (SELECT COUNT (1)
             FROM ADM_PROJECT.cep
            WHERE ver_nbr = 1
              AND TRUNC (dat_carga) = (SELECT TRUNC (MAX (pc.dat_carga))
                                         FROM ADM_PROJECT.processo_carga pc))
       || ';'
       || (SELECT COUNT (1)
             FROM ADM_PROJECT.logradouro
            WHERE ver_nbr = 1
              AND TRUNC (dat_carga) = (SELECT TRUNC (MAX (pc.dat_carga))
                                         FROM ADM_PROJECT.processo_carga pc))
       || ';'
       || (SELECT to_char(TRUNC (MAX (pc.dat_carga)),'dd/mm/yyyy')
             FROM ADM_PROJECT.processo_carga pc)
  FROM DUAL;

SPOOL off

QUIT;
/