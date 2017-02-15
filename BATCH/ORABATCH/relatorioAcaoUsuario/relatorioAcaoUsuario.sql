set echo off
set feed off
set pages 0
set lines 32767
set trimspool on
set timing off
set termout off
set verify off

spool data/&1

SELECT 'CHAVE_END|USU�RIO|NOME_VENDEDOR|DESCRI��O_TIPO_A��O|DATA_A��O|HORA_A��O|NOME_CONTATO|FUN��O_CONTATO|LOCAL_A��O|DESCRI��O_MOTIVO|DESCRI��O_SUBMOTIVO|DATA_TABULA��O' FROM DUAL;

SELECT 
        (SELECT COND.CDCNL || COND.CDLOGRADOUROCSO || COND.NRLOGRADOURO
            FROM SFA.CARTEIRAVENDA CARTV, SFA.CONDOMINIO COND
                WHERE AC.IDCARTEIRAVENDA = CARTV.IDCARTEIRAVENDA
                  AND CARTV.IDCONDOMINIO = COND.IDCONDOMINIO) || '|' ||
        AC.CDUSUARIOACAO || '|' ||
        (SELECT CARTV.NMVENDEDOR
            FROM SFA.CARTEIRAVENDA CARTV
                WHERE AC.IDCARTEIRAVENDA = CARTV.IDCARTEIRAVENDA) || '|' ||
        (SELECT TAC.DSTIPOACAO
            FROM SFA.TIPOACAO TAC
                WHERE AC.IDTIPOACAO = TAC.IDTIPOACAO) || '|' ||
        TO_CHAR(AC.DTREALIZACAOACAO, 'DD/MM/YYYY') || '|' ||
        AC.HRREALIZACAOACAO || '|' ||
        AC.NMCONTATOACAO || '|' ||
        AC.DSFUNCAOCONTATOACAO || '|' ||
        AC.DSLOCALACAO || '|' ||
        (SELECT MOT.DSMOTIVOTABULACAO
            FROM SFA.SUBMOTIVOTABULACAO SUBMOT, SFA.MOTIVOTABULACAO MOT
                WHERE AC.IDSUBMOTIVOTABULACAO = SUBMOT.IDSUBMOTIVOTABULACAO
                  AND SUBMOT.IDMOTIVOTABULACAO = MOT.IDMOTIVOTABULACAO) || '|' ||
        (SELECT SUBMOT.DSSUBMOTIVOTABULACAO
            FROM SFA.SUBMOTIVOTABULACAO SUBMOT
                WHERE AC.IDSUBMOTIVOTABULACAO = SUBMOT.IDSUBMOTIVOTABULACAO) || '|' ||
        TO_CHAR(AC.DTSUBMOTIVOTABULACAO, 'DD/MM/YYYY HH24:MI:SS')
    FROM SFA.ACAO AC
        WHERE AC.DTULTIMAALTERACAO >= (SYSDATE - 7);
    
    
spool off

exit;