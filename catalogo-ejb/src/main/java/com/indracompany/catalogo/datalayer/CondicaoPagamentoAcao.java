package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="CONDICAOPAGAMENTOACAO", schema = "CATALOGOPRS_OW")
public class CondicaoPagamentoAcao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public CondicaoPagamentoAcao() {}
	
	@Id
	@Column(name = "IDCONDICAOPAGAMENTOACAO")
	private Integer idCondicaoPagamentoAcao;

    @Temporal( TemporalType.DATE)
	@Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

	//bi-directional many-to-one association to Acao
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDACAO", nullable=false)
	private Acao acao;

	//bi-directional many-to-one association to Condicaopagamento
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCONDICAOPAGAMENTO", nullable=false)
	private CondicaoPagamento condicaoPagamento;

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public CondicaoPagamento getCondicaoPagamento() {
		return condicaoPagamento;
	}

	public void setCondicaoPagamento(CondicaoPagamento condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
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