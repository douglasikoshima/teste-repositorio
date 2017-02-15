package com.indracompany.catalogo.to;

import java.io.Serializable;

/**
 * @author Luiz
 *
 */
public class DescontoCondPagtoTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public DescontoCondPagtoTO() {}
	
	public DescontoCondPagtoTO(Integer idDescontoCondPagto) {
		this.idDescontoCondPagto = idDescontoCondPagto;
	}
	
	private Integer idDescontoCondPagto;
	private Float fatorPreco;
	private CanalTO canalTO;
	private CondicaoPagamentoTO condicaoPagamentoTO;

	public CanalTO getCanalTO() {
		return canalTO;
	}

	public void setCanalTO(CanalTO canalTO) {
		this.canalTO = canalTO;
	}

	public CondicaoPagamentoTO getCondicaoPagamentoTO() {
		return condicaoPagamentoTO;
	}

	public void setCondicaoPagamentoTO(CondicaoPagamentoTO condicaoPagamentoTO) {
		this.condicaoPagamentoTO = condicaoPagamentoTO;
	}

	public Float getFatorPreco() {
		return fatorPreco;
	}

	public void setFatorPreco(Float fatorPreco) {
		this.fatorPreco = fatorPreco;
	}

	public Integer getIdDescontoCondPagto() {
		return idDescontoCondPagto;
	}

	public void setIdDescontoCondPagto(Integer idDescontoCondPagto) {
		this.idDescontoCondPagto = idDescontoCondPagto;
	}
}
