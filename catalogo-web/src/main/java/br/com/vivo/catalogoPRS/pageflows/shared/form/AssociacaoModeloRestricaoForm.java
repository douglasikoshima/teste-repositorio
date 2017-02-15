package br.com.vivo.catalogoPRS.pageflows.shared.form;


import org.apache.struts.validator.ValidatorActionForm;


public class AssociacaoModeloRestricaoForm extends ValidatorActionForm implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5117204587644854772L;
	
	private Long paginaSolicitada;
	private Boolean cadastrar;
	
	private Integer idGrupoProduto;
	
	private Integer idTipoProdutoSearch;
	private Integer idFabricanteSearch;
	private Integer idModeloSearch;
	private Integer idTipoRestricaoArray[];
	private String idTipoCompativeisSelecionados[];
	private Integer idTipocompativelArray;
	
	private Integer idTipoProdutoCompativel;
	
	public Integer getIdTipoProdutoCompativel() {
		return idTipoProdutoCompativel;
	}
	public void setIdTipoProdutoCompativel(Integer idTipoProdutoCompativel) {
		this.idTipoProdutoCompativel = idTipoProdutoCompativel;
	}
	public String[] getIdTipoCompativeisSelecionados() {
		return idTipoCompativeisSelecionados;
	}
	public void setIdTipoCompativeisSelecionados(
			String[] idTipoCompativeisSelecionados) {
		this.idTipoCompativeisSelecionados = idTipoCompativeisSelecionados;
	}
	public Boolean getCadastrar() {
		return cadastrar;
	}
	public void setCadastrar(Boolean cadastrar) {
		this.cadastrar = cadastrar;
	}
	public Integer getIdFabricanteSearch() {
		return idFabricanteSearch;
	}
	public void setIdFabricanteSearch(Integer idFabricanteSearch) {
		this.idFabricanteSearch = idFabricanteSearch;
	}
	public Integer getIdGrupoProduto() {
		return idGrupoProduto;
	}
	public void setIdGrupoProduto(Integer idGrupoProduto) {
		this.idGrupoProduto = idGrupoProduto;
	}
	public Integer getIdModeloSearch() {
		return idModeloSearch;
	}
	public void setIdModeloSearch(Integer idModeloSearch) {
		this.idModeloSearch = idModeloSearch;
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
	public Integer[] getIdTipoRestricaoArray() {
		return idTipoRestricaoArray;
	}
	public void setIdTipoRestricaoArray(Integer[] idTipoRestricaoArray) {
		this.idTipoRestricaoArray = idTipoRestricaoArray;
	}
	public Integer getIdTipocompativelArray() {
		return idTipocompativelArray;
	}
	public void setIdTipocompativelArray(Integer idTipocompativelArray) {
		this.idTipocompativelArray = idTipocompativelArray;
	}
	

}
