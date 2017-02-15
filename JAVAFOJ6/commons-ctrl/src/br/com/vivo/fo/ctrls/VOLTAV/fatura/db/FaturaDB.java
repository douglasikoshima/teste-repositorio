package br.com.vivo.fo.ctrls.VOLTAV.fatura.db;

import br.com.vivo.fo.ctrls.VOLTAV.fatura.db.dbClasses.TipoComunicacao;
import java.sql.SQLException;

import javax.ejb.Local;

/**
 * @jc:connection data-source-jndi-name="jdbc.OracleDS"
 */
@Local
public interface FaturaDB {

    static final long serialVersionUID = 15448540004L;

    /**
     * @jc:sql
     *   statement="SELECT DECODE(COUNT(*),0,0,1) inhierarquia FROM customer.contahierarquia ch, customer.conta c, apoio.sistemaorigem so WHERE so.sgsistemaorigem = 'ATY' AND so.idsistemaorigem = c.idsistemaorigem AND c.idcontasistemaorigem = {cdConta} AND (ch.idconta = c.idconta OR ch.idcontapai = c.idconta)"
     */
    int inHierarquia(java.lang.String cdConta) throws SQLException;

    /**
     * @jc:sql statement::
     * SELECT COUNT(1)
     *   FROM LINHA.LINHABASE LINHABASE,
     *        LINHA.LINHATELEFONICA LINHATELEFONICA,
     *        APOIO.AREAREGISTRO AREAREGISTRO,
     *		  LINHA.PLANOSERVICOLINHA PLANOSERVICOLINHA,
     *		  LINHA.PLANOSERVICO PLANOSERVICO
     *	WHERE LINHABASE.NRLINHA = {nrLinha}
     *	  AND LINHABASE.IDAREAREGISTRO = AREAREGISTRO.IDAREAREGISTRO
     *	  AND AREAREGISTRO.CDAREAREGISTRO = {cdDDD}
     *	  AND LINHABASE.IDLINHABASE = LINHATELEFONICA.IDLINHABASE
     *	  AND LINHATELEFONICA.IDLINHATELEFONICA = PLANOSERVICOLINHA.IDLINHATELEFONICA
     *	  AND PLANOSERVICOLINHA.IDSERVICO = PLANOSERVICO.IDSERVICO
     *	  AND PLANOSERVICO.NMSERVICO LIKE '%BASICO CARTAO%'
     *    AND (PLANOSERVICOLINHA.DTEXPIRACAO IS NULL OR PLANOSERVICOLINHA.DTEXPIRACAO  >= SYSDATE)
     *    AND (PLANOSERVICOLINHA.DTVIGENCIAFINAL IS NULL OR PLANOSERVICOLINHA.DTVIGENCIAFINAL >= SYSDATE)
     * ::
     */
    int inClienteDadosZap(String cdDDD, String nrLinha) throws SQLException;

    /**
     * @jc:sql
     *   statement="SELECT * FROM APOIO.TIPOCOMUNICACAO WHERE SGCLASSIFICACAO = {sgClassificacao}"
     */
    TipoComunicacao[] getTipoComunicacaoBySgClassificacao(java.lang.String sgClassificacao) throws SQLException;
}
