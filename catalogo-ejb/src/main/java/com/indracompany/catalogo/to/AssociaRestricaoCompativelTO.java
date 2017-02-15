package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author Luiz Pereira
 *
 */
public class AssociaRestricaoCompativelTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public AssociaRestricaoCompativelTO() {}
	
    private Integer idAssociaRestricaoCompativel;
    private TipoProdutoTO tipoProdutoRestricaoTO;
    private TipoProdutoTO tipoProdutoCompativelTO;
    private Date dtCriacao;
    private String nmUsuarioCriacao;
    
	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Integer getIdAssociaRestricaoCompativel() {
		return idAssociaRestricaoCompativel;
	}

	public void setIdAssociaRestricaoCompativel(Integer idAssociaRestricaoCompativel) {
		this.idAssociaRestricaoCompativel = idAssociaRestricaoCompativel;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

	public TipoProdutoTO getTipoProdutoCompativelTO() {
		return tipoProdutoCompativelTO;
	}

	public void setTipoProdutoCompativelTO(TipoProdutoTO tipoProdutoCompativelTO) {
		this.tipoProdutoCompativelTO = tipoProdutoCompativelTO;
	}

	public TipoProdutoTO getTipoProdutoRestricaoTO() {
		return tipoProdutoRestricaoTO;
	}

	public void setTipoProdutoRestricaoTO(TipoProdutoTO tipoProdutoRestricaoTO) {
		this.tipoProdutoRestricaoTO = tipoProdutoRestricaoTO;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idAssociaRestricaoCompativel: " + this.idAssociaRestricaoCompativel}, ", ");
	}
}
