package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.validator.ValidatorActionForm;

import br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.ResultadoBuscarListaFormaCondPagtoFormaPagamento;

public class FormasCondicoesPagamentoForm extends ValidatorActionForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String idsTiposProduto;
	private Long idPlataforma;
	private Long idCanal;
	
	private String formasCondicoesAntigas;
	private String todasFormasCondicoes;
	private String[] condicoesSelecionadas;
	private String[] formasSelecionadas;
	private String[] parcelasMinimas;
	
	private List<ResultadoBuscarListaFormaCondPagtoFormaPagamento> formaPagamentoList;
	
	
	public String getIdsTiposProduto() {
		return idsTiposProduto;
	}
	public void setIdsTiposProduto(String idsTiposProduto) {
		this.idsTiposProduto = idsTiposProduto;
	}
	public Long getIdPlataforma() {
		return idPlataforma;
	}
	public void setIdPlataforma(Long idPlataforma) {
		this.idPlataforma = idPlataforma;
	}
	public Long getIdCanal() {
		return idCanal;
	}
	public void setIdCanal(Long idCanal) {
		this.idCanal = idCanal;
	}
	public String getFormasCondicoesAntigas() {
		return formasCondicoesAntigas;
	}
	public void setFormasCondicoesAntigas(String formasCondicoesAntigas) {
		this.formasCondicoesAntigas = formasCondicoesAntigas;
	}
	public String getTodasFormasCondicoes() {
		return todasFormasCondicoes;
	}
	public void setTodasFormasCondicoes(String todasFormasCondicoes) {
		this.todasFormasCondicoes = todasFormasCondicoes;
	}
	public String[] getCondicoesSelecionadas() {
		return condicoesSelecionadas;
	}
	public void setCondicoesSelecionadas(String[] condicoesSelecionadas) {
		this.condicoesSelecionadas = condicoesSelecionadas;
	}
	public String[] getFormasSelecionadas() {
		return formasSelecionadas;
	}
	public void setFormasSelecionadas(String[] formasSelecionadas) {
		this.formasSelecionadas = formasSelecionadas;
	}
	public String[] getParcelasMinimas() {
		return parcelasMinimas;
	}
	public void setParcelasMinimas(String[] parcelasMinimas) {
		this.parcelasMinimas = parcelasMinimas;
	}
	public List<ResultadoBuscarListaFormaCondPagtoFormaPagamento> getFormaPagamentoList() {
		return formaPagamentoList;
	}
	public void setFormaPagamentoList(List<ResultadoBuscarListaFormaCondPagtoFormaPagamento> formaPagamentoList) {
		this.formaPagamentoList = formaPagamentoList;
	}
}
