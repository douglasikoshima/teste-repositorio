package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorActionForm;

public class CategorizacaoAnaliseCreditoForm extends ValidatorActionForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long paginaSolicitada;
	private Boolean cadastrar;
	
	private Integer idCategoriaScoreSearch;
	
	private Integer idAnaliseCredito;
	private String nome;
	private Integer[] idPlataformas;
	private String categoria;
	private String optPesquisa;
	private Integer idCategoriaScore;
	private String tpPesquisa;
	private Integer checkRecord[];
	
	public Integer getIdCategoriaScore() {
		return idCategoriaScore;
	}

	public void setIdCategoriaScore(Integer idCategoriaScore) {
		this.idCategoriaScore = idCategoriaScore;
	}

	public Boolean getCadastrar() {
		return cadastrar;
	}

	public void setCadastrar(Boolean cadastrar) {
		this.cadastrar = cadastrar;
	}

	public Integer getIdAnaliseCredito() {
		return idAnaliseCredito;
	}

	public void setIdAnaliseCredito(Integer idAnaliseCredito) {
		this.idAnaliseCredito = idAnaliseCredito;
	}

	public Long getPaginaSolicitada() {
		return paginaSolicitada;
	}

	public void setPaginaSolicitada(Long paginaSolicitada) {
		this.paginaSolicitada = paginaSolicitada;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getOptPesquisa() {
		return optPesquisa;
	}

	public void setOptPesquisa(String optPesquisa) {
		this.optPesquisa = optPesquisa;
	}

	public Integer[] getIdPlataformas() {
		return idPlataformas;
	}

	public void setIdPlataformas(Integer[] idPlataformas) {
		this.idPlataformas = idPlataformas;
	}

	public Integer getIdCategoriaScoreSearch() {
		return idCategoriaScoreSearch;
	}

	public void setIdCategoriaScoreSearch(Integer idCategoriaScoreSearch) {
		this.idCategoriaScoreSearch = idCategoriaScoreSearch;
	}

	public String getTpPesquisa() {
		return tpPesquisa;
	}

	public void setTpPesquisa(String tpPesquisa) {
		this.tpPesquisa = tpPesquisa;
	}

	public Integer[] getCheckRecord() {
		return checkRecord;
	}

	public void setCheckRecord(Integer[] checkRecord) {
		this.checkRecord = checkRecord;
	}

}
