package com.indracompany.catalogo.to;

import java.io.Serializable;

/**
 * @author Luiz
 *
 */
public class FormaPagtoCanalParamTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public FormaPagtoCanalParamTO() {}
	
	public FormaPagtoCanalParamTO(Integer idFormaPagtoCanalParam) {
		this.idFormaPagtoCanalParam = idFormaPagtoCanalParam;
	}
	
	private Integer idFormaPagtoCanalParam;
	private Integer nrMaxParcSemJuros;
	private Integer nrParcelasMax;
	private Float taxaJuros;
	private FormaPagamentoTO formaPagamentoTO;
	private CanalTO canalTO;
	private FormaPagtoCanalAtndParamTO formaPagtoCanalAtndParamTO;

	public CanalTO getCanalTO() {
		return canalTO;
	}

	public void setCanalTO(CanalTO canalTO) {
		this.canalTO = canalTO;
	}

	public FormaPagamentoTO getFormaPagamentoTO() {
		return formaPagamentoTO;
	}

	public void setFormaPagamentoTO(FormaPagamentoTO formaPagamentoTO) {
		this.formaPagamentoTO = formaPagamentoTO;
	}

	public FormaPagtoCanalAtndParamTO getFormaPagtoCanalAtndParamTO() {
		return formaPagtoCanalAtndParamTO;
	}

	public void setFormaPagtoCanalAtndParamTO(
			FormaPagtoCanalAtndParamTO formaPagtoCanalAtndParamTO) {
		this.formaPagtoCanalAtndParamTO = formaPagtoCanalAtndParamTO;
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
}