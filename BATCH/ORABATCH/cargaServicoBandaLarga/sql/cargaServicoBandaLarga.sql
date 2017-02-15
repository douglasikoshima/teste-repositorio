/*#########################################################
#  BATCH......: cargaServicoBandaLarga
#  Criado por.: Jair  14/01/2016
#  Funcional..: Mirian Yuka
###########################################################*/
SET SQLPROMPT '';
SET TERMOUT OFF;
SET FEEDBACK OFF;
SET ECHO OFF;
SET AUTOTRACE OFF;
SET FLUSH ON;
SET HEADING OFF;
SET SERVEROUTPUT ON SIZE UNLIMITED;
SET TIME OFF;
SET TIMING OFF;
SET TRIMOUT ON;
SET TRIMSPOOL ON;
SET PAUSE OFF;
SET SHOWMODE OFF;
SET SQLBLANKLINES OFF;
SET SQLNUMBER OFF;
SET TAB OFF;
SET VERIFY OFF;
SET WRAP OFF;
SET FEED OFF;
SET NEWPAGE NONE;
SET LINESIZE 32767;
SPOOL &1;

WHENEVER OSERROR EXIT 9
WHENEVER SQLERROR EXIT SQL.SQLCODE

ALTER SESSION SET COMMIT_LOGGING = 'IMMEDIATE'
ALTER SESSION SET COMMIT_WAIT = NOWAIT

DECLARE
                V_LINHA             VARCHAR2(2000);

                CURSOR CURSOR_APEDIDO IS 
                  SELECT SRV.CDSERVICO ||';'|| 
                     SRV.NMCOMERCIAL ||';'|| 
                     T.SGTECNOLOGIA ||';'||
                     T.NMTECNOLOGIA ||';'||
                     TS.CDTIPOSERVICO ||';'||
                     TS.DSCTIPOSERVICO ||';'|| 
                     S.NMSISTEMA ||';'||
                     CASE S.NMSISTEMA WHEN 'ATIS' THEN CL.CDPS ELSE NULL END ||';'||
                     CASE S.NMSISTEMA WHEN 'ATIS' THEN NULL ELSE CL.CDCLASSESERVICOADICIONAL END ||';'||
                     CASE S.NMSISTEMA WHEN 'ATIS' THEN NULL ELSE CL.CDTIPOEQUIPAMENTO END ||';'||
                     '0' ||';' AS SERVICOBANDALARGA
                  FROM SERVICO SRV
                   INNER JOIN SISTEMASERVICO SSRV ON SRV.IDSERVICO = SSRV.IDSERVICO
                   INNER JOIN COMPLEMENTOLEGADO CL ON SSRV.IDSISTEMASERVICO = CL.IDSISTEMASERVICO
                   INNER JOIN SISTEMA S ON SSRV.IDSISTEMA = S.IDSISTEMA
                   INNER JOIN TIPOSERVICO TS ON SRV.IDTIPOSERVICO = TS.IDTIPOSERVICO
                   LEFT JOIN SERVICOTECNOLOGIA ST ON SRV.IDSERVICO = ST.IDSERVICO
                   LEFT JOIN TECNOLOGIA T ON ST.IDTECNOLOGIA = T.IDTECNOLOGIA
                  WHERE NMSISTEMA IN ('CSO','ATIS')
                   AND TS.CDTIPOSERVICO='16'
                 UNION
                  SELECT DISTINCT SP.CDCODIGO ||';'|| 
                     P.NMCOMERCIAL ||';'|| 
                     T.SGTECNOLOGIA ||';'||
                     T.NMTECNOLOGIA ||';'||
                     CP.SGCLASSIFICACAOPLANO ||';'||
                     CP.DESCRICAO ||';'|| 
                     SIS.NMSISTEMA ||';'||
                     NULL ||';'||
                     NULL ||';'||
                     NULL ||';'||
                     '1' ||';' AS SERVICOBANDALARGA
                  FROM PLANO P
                   INNER JOIN SISTEMAPLANO SP ON SP.IDPLANO = P.IDPLANO
                   INNER JOIN PLANOSERVICO PS ON PS.IDPLANO = SP.IDPLANO
                   INNER JOIN CLASSIFICACAOPLANO CP ON CP.IDCLASSIFICACAOPLANO = P.IDCLASSIFICACAOPLANO
                   LEFT JOIN SISTEMA SIS ON SP.IDSISTEMA = SIS.IDSISTEMA
                   LEFT JOIN SERVICOTECNOLOGIA ST ON ST.IDSERVICO = PS.IDSERVICO
                   LEFT JOIN TECNOLOGIA T ON  T.IDTECNOLOGIA = ST.IDTECNOLOGIA
                  WHERE CP.SGCLASSIFICACAOPLANO IN ('PM','PB','PN') -- PM/MODEM - PB/BOX - PN/NAVEGAR
                   AND P.INDISPONIVEL = 'S';

BEGIN
                DBMS_OUTPUT.ENABLE (buffer_size => NULL);
                OPEN CURSOR_APEDIDO;
                LOOP
                               FETCH CURSOR_APEDIDO INTO V_LINHA;
                               EXIT WHEN CURSOR_APEDIDO%NOTFOUND;
                               DBMS_OUTPUT.PUT_LINE(V_LINHA);                 
                END LOOP;
                CLOSE CURSOR_APEDIDO;
END;
/

SPOOL OFF
SET ECHO OFF
SET TIMI OFF
SET TIME OFF
EXIT



