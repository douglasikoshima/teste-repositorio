package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.util.List;

import org.apache.struts.validator.ValidatorActionForm;

import br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ResultadoBuscarListaParamDescontoListaCondicaoPagamentoCondicaoPagamento;
import br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ResultadoBuscarListaParamDescontoListaParcelasParcelas;

public class DescontosForm extends ValidatorActionForm  implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private Long idFormaPagamento;
	private Long idCanal;
	private Integer numMaxParcelas;
	private String taxaJuros;
	private Integer numParcelasSemJuros;
	private Double[] descontos;
	private Long[] idsCondicaoPagamento;
	
	private List<Double> descontosList;
	
	private List<ResultadoBuscarListaParamDescontoListaParcelasParcelas> parcelasList;
	private List<ResultadoBuscarListaParamDescontoListaCondicaoPagamentoCondicaoPagamento> condicaoPagamentoList;
	
	public Long[] getIdsCondicaoPagamento() {
		return idsCondicaoPagamento;
	}
	public void setIdsCondicaoPagamento(Long[] idsCondicaoPagamento) {
		this.idsCondicaoPagamento = idsCondicaoPagamento;
	}
	public Double[] getDescontos() {
		return descontos;
	}
	public void setDescontos(Double[] descontos) {
		this.descontos = descontos;
	}
	public Integer getNumMaxParcelas() {
		return numMaxParcelas;
	}
	public void setNumMaxParcelas(Integer numMaxParcelas) {
		this.numMaxParcelas = numMaxParcelas;
	}
	public Integer getNumParcelasSemJuros() {
		return numParcelasSemJuros;
	}
	public void setNumParcelasSemJuros(Integer numParcelasSemJuros) {
		this.numParcelasSemJuros = numParcelasSemJuros;
	}
	public String getTaxaJuros() {
		return taxaJuros;
	}
	public void setTaxaJuros(String taxaJuros) {
		this.taxaJuros = taxaJuros;
	}
	public Long getIdFormaPagamento() {
		return idFormaPagamento;
	}
	public void setIdFormaPagamento(Long idFormaPagamento) {
		this.idFormaPagamento = idFormaPagamento;
	}
	public Long getIdCanal() {
		return idCanal;
	}
	public void setIdCanal(Long idCanal) {
		this.idCanal = idCanal;
	}
	public void resetForm() {
		setIdCanal(null);
		setIdFormaPagamento(null);
	}
	public List<ResultadoBuscarListaParamDescontoListaParcelasParcelas> getParcelasList() {
		return parcelasList;
	}
	public void setParcelasList(List<ResultadoBuscarListaParamDescontoListaParcelasParcelas> parcelasList) {
		this.parcelasList = parcelasList;
	}
	public List<ResultadoBuscarListaParamDescontoListaCondicaoPagamentoCondicaoPagamento> getCondicaoPagamentoList() {
		return condicaoPagamentoList;
	}
	public void setCondicaoPagamentoList(
			List<ResultadoBuscarListaParamDescontoListaCondicaoPagamentoCondicaoPagamento> condicaoPagamentoList) {
		this.condicaoPagamentoList = condicaoPagamentoList;
	}
	public List<Double> getDescontosList() {
		return descontosList;
	}
	public void setDescontosList(List<Double> descontosList) {
		this.descontosList = descontosList;
	}
}
