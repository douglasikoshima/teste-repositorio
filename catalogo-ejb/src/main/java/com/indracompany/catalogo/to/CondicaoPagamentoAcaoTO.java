package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Luiz
 *
 */
public class CondicaoPagamentoAcaoTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public CondicaoPagamentoAcaoTO() {}
	
	public CondicaoPagamentoAcaoTO(Integer idFormaPagamento) {
		
	}
	
	private Integer idCondicaoPagamentoAcao;
	private Date dtCriacao;
	private String nmUsuarioCriacao;
	private AcaoTO acaoTO;
	private CondicaoPagamentoTO condicaoPagamentoTO;

	public AcaoTO getAcaoTO() {
		return acaoTO;
	}

	public void setAcaoTO(AcaoTO acaoTO) {
		this.acaoTO = acaoTO;
	}

	public CondicaoPagamentoTO getCondicaoPagamentoTO() {
		return condicaoPagamentoTO;
	}

	public void setCondicaoPagamentoTO(CondicaoPagamentoTO condicaoPagamentoTO) {
		this.condicaoPagamentoTO = condicaoPagamentoTO;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Integer getIdCondicaoPagamentoAcao() {
		return idCondicaoPagamentoAcao;
	}

	public void setIdCondicaoPagamentoAcao(Integer idCondicaoPagamentoAcao) {
		this.idCondicaoPagamentoAcao = idCondicaoPagamentoAcao;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}
}
