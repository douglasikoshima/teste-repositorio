package br.com.vivo.catalogoPRS.pageflows.shared.form;

import org.apache.struts.validator.ValidatorActionForm;

public class AssociacaoAcaoForm extends ValidatorActionForm implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long paginaSolicitada;
	private Boolean cadastrar;
	
	private Integer idAcao;
	private String idProdutosCheck[];
	private String idProdutosAdicionadosCheck[];
	
	private Integer idTipoProdutoSearch;
	private Integer idFabricanteSearch;
	private Integer idModeloSearch;
	private Integer idTipoLinhaSearch;
	private String cdProdutoSearch;

	public String[] getIdProdutosAdicionadosCheck() {
		return idProdutosAdicionadosCheck;
	}

	public void setIdProdutosAdicionadosCheck(String[] idProdutosAdicionadosCheck) {
		this.idProdutosAdicionadosCheck = idProdutosAdicionadosCheck;
	}

	public Boolean getCadastrar() {
		return cadastrar;
	}

	public void setCadastrar(Boolean cadastrar) {
		this.cadastrar = cadastrar;
	}

	public String getCdProdutoSearch() {
		return cdProdutoSearch;
	}

	public void setCdProdutoSearch(String cdProdutoSearch) {
		this.cdProdutoSearch = cdProdutoSearch;
	}

	public Integer getIdAcao() {
		return idAcao;
	}

	public void setIdAcao(Integer idAcao) {
		this.idAcao = idAcao;
	}

	public Integer getIdFabricanteSearch() {
		return idFabricanteSearch;
	}

	public void setIdFabricanteSearch(Integer idFabricanteSearch) {
		this.idFabricanteSearch = idFabricanteSearch;
	}

	public Integer getIdModeloSearch() {
		return idModeloSearch;
	}

	public void setIdModeloSearch(Integer idModeloSearch) {
		this.idModeloSearch = idModeloSearch;
	}

	public String[] getIdProdutosCheck() {
		return idProdutosCheck;
	}

	public void setIdProdutosCheck(String[] idProdutosCheck) {
		this.idProdutosCheck = idProdutosCheck;
	}

	public Integer getIdTipoLinhaSearch() {
		return idTipoLinhaSearch;
	}

	public void setIdTipoLinhaSearch(Integer idTipoLinhaSearch) {
		this.idTipoLinhaSearch = idTipoLinhaSearch;
	}

	public Integer getIdTipoProdutoSearch() {
		return idTipoProdutoSearch;
	}

	public void setIdTipoProdutoSearch(Integer idTipoProdutoSearch) {
		this.idTipoProdutoSearch = idTipoProdutoSearch;
	}

	public Long getPaginaSolicitada() {
		return paginaSolicitada;
	}

	public void setPaginaSolicitada(Long paginaSolicitada) {
		this.paginaSolicitada = paginaSolicitada;
	}
}
