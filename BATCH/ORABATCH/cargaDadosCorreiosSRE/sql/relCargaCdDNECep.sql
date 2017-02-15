
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

SPOOL relatoriosCargaCdDNE/relCargaCdDNECep.csv

SELECT 'UF;Localidade;CEP;Data_da_atualizacao' FROM DUAL
UNION ALL
       SELECT UF.NOM_UF || ';' || LOC.DSC_LOCALIDADE || ';' || C.NUM_CEP || ';' || (SELECT to_char(TRUNC (MAX (pc.dat_carga)),'dd/mm/yyyy') FROM ADM_PROJECT.processo_carga pc)
        FROM ADM_PROJECT.localidade loc, 
        	 ADM_PROJECT.unidade_federacao uf, 
        	 ADM_PROJECT.cep c
        WHERE LOC.ver_nbr = 1
        AND UF.COD_UF = LOC.COD_UF
        AND C.COD_LOCALIDADE = LOC.COD_LOCALIDADE
        AND TRUNC (LOC.dat_carga) = (SELECT TRUNC (MAX (pc.dat_carga)) FROM ADM_PROJECT.processo_carga pc);

SPOOL off

QUIT;
/

