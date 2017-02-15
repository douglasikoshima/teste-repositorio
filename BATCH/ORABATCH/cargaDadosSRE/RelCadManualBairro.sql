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

SPOOL RelatoriosCadManual/RelCadManualBairro.csv
SELECT 'Nome_do_Bairro;CEP;Logradouro;Data_de_Alteracao;Login_do_Usuario' FROM DUAL
UNION ALL
SELECT B.NOM_BAIRRO || ';' || C.NUM_CEP || ';' || LOGRAD.NOM_LOGRADOURO || ';' || to_char(TRUNC (B.DAT_ATUALIZACAO),'dd/mm/yyyy') || ';' || B.USUARIO
    FROM  BAIRRO B, CEP C, LOGRADOURO LOGRAD, REL_CEP_LOGRAD_BAIRRO REL
    WHERE B.USUARIO <> 'loadDNE'
    AND B.VER_NBR > 1
    AND REL.COD_BAIRRO = B.COD_BAIRRO
    AND REL.COD_CEP = C.COD_CEP
    AND REL.COD_LOGRADOURO = LOGRAD.COD_LOGRADOURO
    AND TRUNC (B.DAT_ATUALIZACAO) >= TRUNC (SYSDATE - 30);

SPOOL OFF

QUIT;
/