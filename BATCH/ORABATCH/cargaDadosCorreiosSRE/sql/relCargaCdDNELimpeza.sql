
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

SPOOL relatoriosCargaCdDNE/relCargaCdDNELimpeza.csv

SELECT 'UF;Localidade;CEP;Numero_de_Logradouros' FROM DUAL
UNION ALL
SELECT tab.NOM_UF || ';' || tab.DSC_LOCALIDADE || ';' || tab.NUM_CEP || ';' || (SELECT COUNT (1) FROM ADM_PROJECT.LOGRADOURO LOGRAD WHERE LOGRAD.COD_LOCALIDADE = tab.COD_LOCALIDADE) count_loc 
FROM (SELECT UF.NOM_UF, LOC.COD_LOCALIDADE, LOC.DSC_LOCALIDADE, C.NUM_CEP 
            FROM ADM_PROJECT.LOCALIDADE LOC, 
            	 ADM_PROJECT.UNIDADE_FEDERACAO UF, 
            	 ADM_PROJECT.CEP C
            WHERE LOC.COD_LOCALIDADE = C.COD_LOCALIDADE
            AND LOC.COD_UF = UF.COD_UF
            AND TRUNC (LOC.dat_carga) < (SELECT TRUNC (MAX (pc.dat_carga)) FROM ADM_PROJECT.processo_carga pc)) tab;

SPOOL OFF

QUIT;
/