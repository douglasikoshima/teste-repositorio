package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorActionForm;

public class CategoriaScoreForm extends ValidatorActionForm implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	private Long paginaSolicitada;
	private boolean cadastrar; 
	
	private Integer idCategoriaScore;
	private String cdCategoriaScore;
	private String nmCategoriaScore;
	private Integer idClassificacaoCategoriaScore;
	private String nmClassificacaoCategoriaScore;
	
	private String nmCategoriaScoreSearch;
	private Integer idClassificacaoCategoriaScoreSearch;
	
	private char possuiAssociacao;
	
	public Integer getIdClassificacaoCategoriaScoreSearch() {
		return idClassificacaoCategoriaScoreSearch;
	}
	public void setIdClassificacaoCategoriaScoreSearch(
			Integer idClassificacaoCategoriaScoreSearch) {
		this.idClassificacaoCategoriaScoreSearch = idClassificacaoCategoriaScoreSearch;
	}
	public String getNmCategoriaScoreSearch() {
		return nmCategoriaScoreSearch;
	}
	public void setNmCategoriaScoreSearch(String nmCategoriaScoreSearch) {
		this.nmCategoriaScoreSearch = nmCategoriaScoreSearch;
	}
	public String getCdCategoriaScore() {
		return cdCategoriaScore;
	}
	public void setCdCategoriaScore(String cdCategoriaScore) {
		this.cdCategoriaScore = cdCategoriaScore;
	}
	public Integer getIdCategoriaScore() {
		return idCategoriaScore;
	}
	public void setIdCategoriaScore(Integer idCategoriaScore) {
		this.idCategoriaScore = idCategoriaScore;
	}
	public Integer getIdClassificacaoCategoriaScore() {
		return idClassificacaoCategoriaScore;
	}
	public void setIdClassificacaoCategoriaScore(
			Integer idClassificacaoCategoriaScore) {
		this.idClassificacaoCategoriaScore = idClassificacaoCategoriaScore;
	}
	public String getNmCategoriaScore() {
		return nmCategoriaScore;
	}
	public void setNmCategoriaScore(String nmCategoriaScore) {
		this.nmCategoriaScore = nmCategoriaScore;
	}
	public String getNmClassificacaoCategoriaScore() {
		return nmClassificacaoCategoriaScore;
	}
	public void setNmClassificacaoCategoriaScore(
			String nmClassificacaoCategoriaScore) {
		this.nmClassificacaoCategoriaScore = nmClassificacaoCategoriaScore;
	}
	public Long getPaginaSolicitada() {
		return paginaSolicitada;
	}
	public void setPaginaSolicitada(Long paginaSolicitada) {
		this.paginaSolicitada = paginaSolicitada;
	}
	public char getPossuiAssociacao() {
		return possuiAssociacao;
	}
	public void setPossuiAssociacao(char possuiAssociacao) {
		this.possuiAssociacao = possuiAssociacao;
	}
	public boolean isCadastrar() {
		return cadastrar;
	}
	public void setCadastrar(boolean cadastrar) {
		this.cadastrar = cadastrar;
	}

}
