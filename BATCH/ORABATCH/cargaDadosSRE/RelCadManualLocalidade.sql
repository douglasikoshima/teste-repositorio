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

SPOOL RelatoriosCadManual/RelCadManualLocalidade.csv
SELECT 'UF;Localidade;Data_de_Alteracao;Login_do_Usuario' FROM DUAL
UNION ALL
SELECT UF.NOM_UF || ';' || LOC.DSC_LOCALIDADE || ';' || to_char(TRUNC (LOC.DAT_ATUALIZACAO),'dd/mm/yyyy') || ';' || LOC.USUARIO
    FROM LOCALIDADE LOC, UNIDADE_FEDERACAO UF
    WHERE LOC.USUARIO <> 'loadDNE'
    AND LOC.VER_NBR > 1
    AND LOC.COD_UF = UF.COD_UF
    AND TRUNC (LOC.DAT_ATUALIZACAO) >= TRUNC (SYSDATE - 30);
    
SPOOL OFF

QUIT;
/