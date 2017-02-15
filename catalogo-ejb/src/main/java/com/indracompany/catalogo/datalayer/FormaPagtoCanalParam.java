package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@SequenceGenerator(name = "FORMAPAGTOCANALPARAM_SQ", sequenceName = "CATALOGOPRS_OW.FORMAPAGTOCANALPARAM_SQ", allocationSize = 1)
@Table(name="FORMAPAGTOCANALPARAM", schema = "CATALOGOPRS_OW")
public class FormaPagtoCanalParam implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public FormaPagtoCanalParam() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FORMAPAGTOCANALPARAM_SQ")
	@Column(name = "IDFORMAPAGTOCANALPARAM")
	private Integer idFormaPagtoCanalParam;

	@Column(name = "NRMAXPARCSEMJUROS")
	private Integer nrMaxParcSemJuros;

	@Column(name = "NRPARCELASMAX")
	private Integer nrParcelasMax;

	@Column(name = "TAXAJUROS")
	private Float taxaJuros;

	//bi-directional many-to-one association to FormaPagamento
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDFORMAPAGAMENTO", nullable=false)
	private FormaPagamento formaPagamento;
	
	//bi-directional many-to-one association to Canal
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCANAL", nullable=false)
	private Canal canal;

	//bi-directional one-to-one association to FormaPagtoCanalAtndParam
	@OneToOne(mappedBy="formaPagtoCanalParam")
	private FormaPagtoCanalAtndParam formaPagtoCanalAtndParam;
	
	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public Integer getIdFormaPagtoCanalParam() {
		return idFormaPagtoCanalParam;
	}

	public void setIdFormaPagtoCanalParam(Integer idFormaPagtoCanalParam) {
		this.idFormaPagtoCanalParam = idFormaPagtoCanalParam;
	}

	public Integer getNrMaxParcSemJuros() {
		return nrMaxParcSemJuros;
	}

	public void setNrMaxParcSemJuros(Integer nrMaxParcSemJuros) {
		this.nrMaxParcSemJuros = nrMaxParcSemJuros;
	}

	public Integer getNrParcelasMax() {
		return nrParcelasMax;
	}

	public void setNrParcelasMax(Integer nrParcelasMax) {
		this.nrParcelasMax = nrParcelasMax;
	}

	public Float getTaxaJuros() {
		return taxaJuros;
	}

	public void setTaxaJuros(Float taxaJuros) {
		this.taxaJuros = taxaJuros;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public FormaPagtoCanalAtndParam getFormaPagtoCanalAtndParam() {
		return formaPagtoCanalAtndParam;
	}

	public void setFormaPagtoCanalAtndParam(
			FormaPagtoCanalAtndParam formaPagtoCanalAtndParam) {
		this.formaPagtoCanalAtndParam = formaPagtoCanalAtndParam;
	}
}