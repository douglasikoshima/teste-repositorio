package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "FORMAPAGAMENTOBANDEIRA_SQ", sequenceName = "CATALOGOPRS_OW.FORMAPAGAMENTOBANDEIRA_SQ", allocationSize = 1)
@Table(name="FORMAPAGAMENTOBANDEIRA")
public class FormaPagamentoBandeira implements Serializable {
	private static final long serialVersionUID = 1L;

	public FormaPagamentoBandeira() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FORMAPAGAMENTOBANDEIRA_SQ")
	@Column(name = "IDFORMAPAGAMENTOBANDEIRA")
	private Integer idFormaPagamentoBandeira;

	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name="IDFORMAPAGAMENTO")
	private FormaPagamento formaPagamento;

	@Column(name = "DTCRIACAO")
	private Date dtCriacao;

	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name="IDBANDEIRA")
	private Bandeira bandeira;

	public Bandeira getBandeira() {
		return bandeira;
	}

	public void setBandeira(Bandeira bandeira) {
		this.bandeira = bandeira;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Integer getIdFormaPagamentoBandeira() {
		return idFormaPagamentoBandeira;
	}

	public void setIdFormaPagamentoBandeira(Integer idFormaPagamentoBandeira) {
		this.idFormaPagamentoBandeira = idFormaPagamentoBandeira;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}
}