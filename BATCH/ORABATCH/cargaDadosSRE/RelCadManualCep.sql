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

SPOOL RelatoriosCadManual/RelCadManualCep.csv
SELECT 'UF;Localidade;CEP;Bairro;Logradouro;Data_de_Alteracao;Login_do_Usuario' FROM DUAL
UNION ALL
SELECT UF.NOM_UF || ';' || LOC.DSC_LOCALIDADE || ';' || C.NUM_CEP || ';' || B.NOM_BAIRRO || ';' || LOGRAD.NOM_LOGRADOURO || ';' || to_char(TRUNC (C.DAT_ATUALIZACAO),'dd/mm/yyyy') || ';' || C.USUARIO
    FROM CEP C, UNIDADE_FEDERACAO UF, LOCALIDADE LOC, BAIRRO B, LOGRADOURO LOGRAD, REL_CEP_LOGRAD_BAIRRO REL
    WHERE C.USUARIO <> 'loadDNE'
    AND C.VER_NBR > 1
    AND C.COD_LOCALIDADE = LOC.COD_LOCALIDADE
    AND UF.COD_UF = LOC.COD_UF
    AND REL.COD_BAIRRO = B.COD_BAIRRO
    AND REL.COD_LOGRADOURO = LOGRAD.COD_LOGRADOURO
    AND REL.COD_CEP = C.COD_CEP
    AND TRUNC (C.DAT_ATUALIZACAO) >= TRUNC (SYSDATE - 30);

SPOOL OFF

QUIT;
/