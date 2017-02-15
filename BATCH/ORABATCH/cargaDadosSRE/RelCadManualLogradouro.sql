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

SPOOL RelatoriosCadManual/RelCadManualLogradouro.csv 
SELECT 'UF;Localidade;Logradouro;CEP;Data_da_Alteracao;Login_do_Usuario' FROM DUAL
UNION ALL
SELECT UF.NOM_UF || ';' || LOC.DSC_LOCALIDADE || ';' || LOGRAD.NOM_LOGRADOURO || ';' || C.NUM_CEP || ';' || to_char(TRUNC (LOGRAD.DAT_ATUALIZACAO),'dd/mm/yyyy') || ';' || LOGRAD.USUARIO
    FROM LOGRADOURO LOGRAD, UNIDADE_FEDERACAO UF, CEP C, LOCALIDADE LOC
    WHERE LOGRAD.USUARIO <> 'loadDNE'
    AND LOGRAD.VER_NBR > 1
    AND LOGRAD.COD_LOCALIDADE = LOC.COD_LOCALIDADE
    AND UF.COD_UF = LOC.COD_UF
    AND C.COD_LOCALIDADE = LOC.COD_LOCALIDADE
    AND TRUNC (LOGRAD.DAT_ATUALIZACAO) >= TRUNC (SYSDATE - 30);

SPOOL OFF

QUIT;
/