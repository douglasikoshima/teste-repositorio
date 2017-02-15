set echo off
set feed off
set pages 0
set lines 32767
set trimspool on
set timing off
set termout off
set verify off
 
spool data/&1

SELECT 'NOME|CPF|CONVERG�NCIA|EMAIL|COMPLEMENTO_ENDERECO|CHAVE_END|PRODUTO_LINHA FIXA_POSSE|DETALHE_LINHA FIXA_POSSE|TELEFONE_LINHA FIXA_POSSE|DT. AQUISI��O_LINHA FIXA_POSSE|VALOR_LINHA FIXA_POSSE|NRC|PRODUTO_LINHA M�VEL_POSSE|DETALHE_LINHA M�VEL_POSSE|TELEFONE_LINHA M�VEL_POSSE|DT. AQUISI��O_LINHA M�VEL_POSSE|VALOR_LINHA M�VEL_POSSE|ID LINHA|PRODUTO_BL LARGA_POSSE|DETALHE_BL LARGA_POSSE|DT. AQUISI��O_BL LARGA_POSSE|VALOR_BL LARGA_POSSE|PRODUTO_TV_POSSE|DETALHE_TV_POSSE|DT. AQUISI��O_TV_POSSE|VALOR_TV_POSSE|VALOR_TOTAL PRODS_POSSE|PRODUTO_LINHA FIXA_OFERTA|DETALHE_LINHA FIXA_OFERTA|PRODUTO_LINHA M�VEL_OFERTA|DETALHE_LINHA M�VEL_OFERTA|PRODUTO_BL LARGA_OFERTA|DETALHE_BL LARGA_OFERTA|PRODUTO_TV_OFERTA|DETALHE_TV_OFERTA|MOTIVO_TABULA��O_OPORTUNIDADE|SUBMOTIVO_TABULA��O_OPORTUNIDADE|DT_TABULA��O_OPORTUNIDADE|MARCA��O_NOVO LEAD' FROM DUAL;

SELECT
    NOME || '|' ||
    CPF || '|' ||
    CONVERGENCIA || '|' ||
    EMAIL || '|' ||
    COMPLEMENTO_ENDERECO || '|' ||
    CHAVE_END || '|' ||
    PRODUTO_LINHA_FIXA_POSSE || '|' ||
    DETALHE_LINHA_FIXA_POSSE || '|' ||
    TELEFONE_LINHA_FIXA_POSSE || '|' ||
    DT_AQUIS_LINHA_FIXA_POSSE || '|' ||
    VALOR_LINHA_FIXA_POSSE || '|' ||
    NRC || '|' ||
    PRODUTO_LINHA_MOVEL_POSSE || '|' ||
    DETALHE_LINHA_MOVEL_POSSE || '|' ||
    TELEFONE_LINHA_MOVEL_POSSE || '|' ||
    DT_AQUIS_LINHA_MOVEL_POSSE || '|' ||
    VALOR_LINHA_MOVEL_POSSE || '|' ||
    ID_LINHA || '|' ||
    PRODUTO_BL_LARGA_POSSE || '|' ||
    DETALHE_BL_LARGA_POSSE || '|' ||
    DT_AQUIS_BL_LARGA_POSSE || '|' ||
    VALOR_BL_LARGA_POSSE || '|' ||
    PRODUTO_TV_POSSE || '|' ||
    DETALHE_TV_POSSE || '|' ||
    DT_AQUIS_TV_POSSE || '|' ||
    VALOR_TV_POSSE || '|' ||
    VALOR_TOTAL_PRODS_POSSE || '|' ||
    PRODUTO_LINHA_FIXA_OFERTA || '|' ||
    DETALHE_LINHA_FIXA_OFERTA || '|' ||
    PRODUTO_LINHA_MOVEL_OFERTA || '|' ||
    DETALHE_LINHA_MOVEL_OFERTA || '|' ||
    PRODUTO_BL_LARGA_OFERTA || '|' ||
    DETALHE_BL_LARGA_OFERTA || '|' ||
    PRODUTO_TV_OFERTA || '|' ||
    DETALHE_TV_OFERTA || '|' ||
    MOTIVO_TABULACAO_OPORTUNIDADE || '|' ||
    SUBMOT_TABULACAO_OPORTUNIDADE || '|' ||
    DT_TABULACAO_OPORTUNIDADE || '|' ||
    MARCACAO_NOVO_LEAD
FROM
    (SELECT /*+ FIRST_ROWS(500) NO_CPU_COSTING */
       OV.NMCLIENTE AS NOME,
       OV.NRCPFCLIENTE AS CPF,
       OV.DSCONVERGENCIA AS CONVERGENCIA,
       OV.DSEMAILCLIENTE AS EMAIL,
       OV.DSCOMPENDCLIENTE AS COMPLEMENTO_ENDERECO,
       (CD.CDCNL || CD.CDLOGRADOUROCSO || CD.NRLOGRADOURO) AS CHAVE_END,
       (SELECT PR.DSPRODUTO
        FROM SFA.PRODUTOPOSSECLIENTE PP
        JOIN SFA.PRODUTODETALHE PD ON PP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE PP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND TP.DSTIPOPRODUTO = 'LINHA FIXA'
        AND ROWNUM < 2
       ) PRODUTO_LINHA_FIXA_POSSE,
       (SELECT PD.DSDETALHEPRODUTO
        FROM SFA.PRODUTOPOSSECLIENTE PP
        JOIN SFA.PRODUTODETALHE PD ON PP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE PP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND TP.DSTIPOPRODUTO = 'LINHA FIXA'
        AND ROWNUM < 2
       ) DETALHE_LINHA_FIXA_POSSE,
       (SELECT PP.NRLINHA
        FROM SFA.PRODUTOPOSSECLIENTE PP
        JOIN SFA.PRODUTODETALHE PD ON PP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE PP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND TP.DSTIPOPRODUTO = 'LINHA FIXA'
        AND ROWNUM < 2
       ) TELEFONE_LINHA_FIXA_POSSE,
       (SELECT TO_CHAR(PP.DTAQUISICAOPRODUTO, 'DD/MM/YYYY HH24:MI:SS')
        FROM SFA.PRODUTOPOSSECLIENTE PP
        JOIN SFA.PRODUTODETALHE PD ON PP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE PP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND TP.DSTIPOPRODUTO = 'LINHA FIXA'
        AND ROWNUM < 2
       ) DT_AQUIS_LINHA_FIXA_POSSE,
       (SELECT PP.VLPAGOPRODUTO
        FROM SFA.PRODUTOPOSSECLIENTE PP
        JOIN SFA.PRODUTODETALHE PD ON PP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE PP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND TP.DSTIPOPRODUTO = 'LINHA FIXA'
        AND ROWNUM < 2
       ) VALOR_LINHA_FIXA_POSSE,
       (SELECT PP.CDPRODUTOLEGADO
        FROM SFA.PRODUTOPOSSECLIENTE PP
        JOIN SFA.PRODUTODETALHE PD ON PP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE PP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND TP.DSTIPOPRODUTO = 'LINHA FIXA'
        AND ROWNUM < 2
       ) NRC,
       (SELECT PR.DSPRODUTO
        FROM SFA.PRODUTOPOSSECLIENTE PP
        JOIN SFA.PRODUTODETALHE PD ON PP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE PP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND (TP.DSTIPOPRODUTO = 'LINHA MOVEL' OR TP.DSTIPOPRODUTO = 'LINHA M�VEL')
        AND ROWNUM < 2
       ) PRODUTO_LINHA_MOVEL_POSSE,
       (SELECT PD.DSDETALHEPRODUTO
        FROM SFA.PRODUTOPOSSECLIENTE PP
        JOIN SFA.PRODUTODETALHE PD ON PP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE PP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND (TP.DSTIPOPRODUTO = 'LINHA MOVEL' OR TP.DSTIPOPRODUTO = 'LINHA M�VEL')
        AND ROWNUM < 2
       ) DETALHE_LINHA_MOVEL_POSSE,
       (SELECT PP.NRLINHA
        FROM SFA.PRODUTOPOSSECLIENTE PP
        JOIN SFA.PRODUTODETALHE PD ON PP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE PP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND (TP.DSTIPOPRODUTO = 'LINHA MOVEL' OR TP.DSTIPOPRODUTO = 'LINHA M�VEL')
        AND ROWNUM < 2
       ) TELEFONE_LINHA_MOVEL_POSSE,
       (SELECT TO_CHAR(PP.DTAQUISICAOPRODUTO, 'DD/MM/YYYY HH24:MI:SS')
        FROM SFA.PRODUTOPOSSECLIENTE PP
        JOIN SFA.PRODUTODETALHE PD ON PP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE PP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND (TP.DSTIPOPRODUTO = 'LINHA MOVEL' OR TP.DSTIPOPRODUTO = 'LINHA M�VEL')
        AND ROWNUM < 2
       ) DT_AQUIS_LINHA_MOVEL_POSSE,
       (SELECT PP.VLPAGOPRODUTO
        FROM SFA.PRODUTOPOSSECLIENTE PP
        JOIN SFA.PRODUTODETALHE PD ON PP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE PP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND (TP.DSTIPOPRODUTO = 'LINHA MOVEL' OR TP.DSTIPOPRODUTO = 'LINHA M�VEL')
        AND ROWNUM < 2
       ) VALOR_LINHA_MOVEL_POSSE,
       (SELECT PP.CDPRODUTOLEGADO
        FROM SFA.PRODUTOPOSSECLIENTE PP
        JOIN SFA.PRODUTODETALHE PD ON PP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE PP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND TP.DSTIPOPRODUTO = 'LINHA MOVEL'
        AND ROWNUM < 2
       ) ID_LINHA,
       (SELECT PR.DSPRODUTO
        FROM SFA.PRODUTOPOSSECLIENTE PP
        JOIN SFA.PRODUTODETALHE PD ON PP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE PP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND TP.DSTIPOPRODUTO = 'BANDA LARGA'
        AND ROWNUM < 2
       ) PRODUTO_BL_LARGA_POSSE,
       (SELECT PD.DSDETALHEPRODUTO
        FROM SFA.PRODUTOPOSSECLIENTE PP
        JOIN SFA.PRODUTODETALHE PD ON PP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE PP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND TP.DSTIPOPRODUTO = 'BANDA LARGA'
        AND ROWNUM < 2
       ) DETALHE_BL_LARGA_POSSE,
       (SELECT TO_CHAR(PP.DTAQUISICAOPRODUTO, 'DD/MM/YYYY HH24:MI:SS')
        FROM SFA.PRODUTOPOSSECLIENTE PP
        JOIN SFA.PRODUTODETALHE PD ON PP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE PP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND TP.DSTIPOPRODUTO = 'BANDA LARGA'
        AND ROWNUM < 2
       ) DT_AQUIS_BL_LARGA_POSSE,
       (SELECT PP.VLPAGOPRODUTO
        FROM SFA.PRODUTOPOSSECLIENTE PP
        JOIN SFA.PRODUTODETALHE PD ON PP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE PP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND TP.DSTIPOPRODUTO = 'BANDA LARGA'
        AND ROWNUM < 2
       ) VALOR_BL_LARGA_POSSE,
       (SELECT PR.DSPRODUTO
        FROM SFA.PRODUTOPOSSECLIENTE PP
        JOIN SFA.PRODUTODETALHE PD ON PP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE PP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND TP.DSTIPOPRODUTO = 'TV'
        AND ROWNUM < 2
       ) PRODUTO_TV_POSSE,
       (SELECT PD.DSDETALHEPRODUTO
        FROM SFA.PRODUTOPOSSECLIENTE PP
        JOIN SFA.PRODUTODETALHE PD ON PP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE PP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND TP.DSTIPOPRODUTO = 'TV'
        AND ROWNUM < 2
       ) DETALHE_TV_POSSE,
       (SELECT TO_CHAR(PP.DTAQUISICAOPRODUTO, 'DD/MM/YYYY HH24:MI:SS')
        FROM SFA.PRODUTOPOSSECLIENTE PP
        JOIN SFA.PRODUTODETALHE PD ON PP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE PP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND TP.DSTIPOPRODUTO = 'TV'
        AND ROWNUM < 2
       ) DT_AQUIS_TV_POSSE,
       (SELECT PP.VLPAGOPRODUTO
        FROM SFA.PRODUTOPOSSECLIENTE PP
        JOIN SFA.PRODUTODETALHE PD ON PP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE PP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND TP.DSTIPOPRODUTO = 'TV'
        AND ROWNUM < 2
       ) VALOR_TV_POSSE,
       OV.VLTOTALPRODUTO AS VALOR_TOTAL_PRODS_POSSE,
       (SELECT PR.DSPRODUTO
        FROM SFA.OPORTUNIDADEVENDAPRODUTO OP
        JOIN SFA.PRODUTODETALHE PD ON OP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE OP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND TP.DSTIPOPRODUTO = 'LINHA FIXA'
        AND ROWNUM < 2
       ) PRODUTO_LINHA_FIXA_OFERTA,
       (SELECT PD.DSDETALHEPRODUTO
        FROM SFA.OPORTUNIDADEVENDAPRODUTO OP
        JOIN SFA.PRODUTODETALHE PD ON OP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE OP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND TP.DSTIPOPRODUTO = 'LINHA FIXA'
        AND ROWNUM < 2
       ) DETALHE_LINHA_FIXA_OFERTA,
       (SELECT PR.DSPRODUTO
        FROM SFA.OPORTUNIDADEVENDAPRODUTO OP
        JOIN SFA.PRODUTODETALHE PD ON OP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE OP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND (TP.DSTIPOPRODUTO = 'LINHA MOVEL' OR TP.DSTIPOPRODUTO = 'LINHA M�VEL')
        AND ROWNUM < 2
       ) PRODUTO_LINHA_MOVEL_OFERTA,
       (SELECT PD.DSDETALHEPRODUTO
        FROM SFA.OPORTUNIDADEVENDAPRODUTO OP
        JOIN SFA.PRODUTODETALHE PD ON OP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE OP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND (TP.DSTIPOPRODUTO = 'LINHA MOVEL' OR TP.DSTIPOPRODUTO = 'LINHA M�VEL')
        AND ROWNUM < 2
       ) DETALHE_LINHA_MOVEL_OFERTA,
       (SELECT PR.DSPRODUTO
        FROM SFA.OPORTUNIDADEVENDAPRODUTO OP
        JOIN SFA.PRODUTODETALHE PD ON OP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE OP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND TP.DSTIPOPRODUTO = 'BANDA LARGA'
        AND ROWNUM < 2
       ) PRODUTO_BL_LARGA_OFERTA,
       (SELECT PD.DSDETALHEPRODUTO 
        FROM SFA.OPORTUNIDADEVENDAPRODUTO OP
        JOIN SFA.PRODUTODETALHE PD ON OP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE OP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND TP.DSTIPOPRODUTO = 'BANDA LARGA'
        AND ROWNUM < 2
       ) DETALHE_BL_LARGA_OFERTA,
       (SELECT PR.DSPRODUTO
        FROM SFA.OPORTUNIDADEVENDAPRODUTO OP
        JOIN SFA.PRODUTODETALHE PD ON OP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE OP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND TP.DSTIPOPRODUTO = 'TV'
        AND ROWNUM < 2
       ) PRODUTO_TV_OFERTA,
       (SELECT PD.DSDETALHEPRODUTO
        FROM SFA.OPORTUNIDADEVENDAPRODUTO OP
        JOIN SFA.PRODUTODETALHE PD ON OP.IDDETALHEPRODUTO = PD.IDDETALHEPRODUTO
        LEFT JOIN SFA.PRODUTO PR ON PR.IDPRODUTO = PD.IDPRODUTO
        LEFT JOIN SFA.TIPOPRODUTO TP ON TP.IDTIPOPRODUTO = PR.IDTIPOPRODUTO
        WHERE OP.IDOPORTUNIDADEVENDA = OV.IDOPORTUNIDADEVENDA
        AND TP.DSTIPOPRODUTO = 'TV'
        AND ROWNUM < 2
       ) DETALHE_TV_OFERTA,
       MT.DSMOTIVOTABULACAO AS MOTIVO_TABULACAO_OPORTUNIDADE,      
       SM.DSSUBMOTIVOTABULACAO AS SUBMOT_TABULACAO_OPORTUNIDADE,      
       TO_CHAR(OV.DTSUBMOTIVOTABULACAO, 'DD/MM/YYYY HH24:MI:SS') AS DT_TABULACAO_OPORTUNIDADE,
       OV.INNOVAOPORTUNIDADEVENDA AS MARCACAO_NOVO_LEAD
	FROM SFA.OPORTUNIDADEVENDA OV
	JOIN SFA.CARTEIRAVENDA CT ON CT.IDCARTEIRAVENDA = OV.IDCARTEIRAVENDA
	JOIN SFA.CONDOMINIO CD ON CD.IDCONDOMINIO = CT.IDCONDOMINIO
	LEFT JOIN SFA.SUBMOTIVOTABULACAO SM ON SM.IDSUBMOTIVOTABULACAO = OV.IDSUBMOTIVOTABULACAO
	LEFT JOIN SFA.MOTIVOTABULACAO MT ON MT.IDMOTIVOTABULACAO = SM.IDMOTIVOTABULACAO
	WHERE OV.DTULTIMAALTERACAO >= (SYSDATE - 7)
	ORDER BY OV.Nmcliente
  );
     
spool off

exit;          