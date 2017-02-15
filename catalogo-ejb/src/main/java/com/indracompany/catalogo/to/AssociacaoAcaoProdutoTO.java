package com.indracompany.catalogo.to;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * @author Luiz Pereira
 *
 */
public class AssociacaoAcaoProdutoTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public AssociacaoAcaoProdutoTO() {}
	
	public AssociacaoAcaoProdutoTO(
			Integer idProduto
			,	String cdProduto
			,	String nmProduto
			,	String nmModelo
			,	String nmFabricante
			,	String nmTipoProduto
			,	String nmTipoLinha
			,	Integer idPlataforma
			,	String nmPlataforma
			,	Integer idOrganizacaoVenda
			,	String nmOrganizacaoVenda) {
		
		this.idProduto = idProduto;
		this.cdProduto = cdProduto;
		this.nmProduto = nmProduto;
		this.nmModelo = nmModelo;
		this.nmFabricante = nmFabricante;
		this.nmTipoProduto = nmTipoProduto;
		this.nmTipoLinha = nmTipoLinha;
		this.idPlataforma = idPlataforma;
		this.nmPlataforma = nmPlataforma;
		this.idOrganizacaoVenda = idOrganizacaoVenda;
		this.nmOrganizacaoVenda = nmOrganizacaoVenda;
	}
	
	private Integer idProduto;
	private String cdProduto;
	private String nmProduto;
	private String nmModelo;
	private String nmFabricante;
	private String nmTipoProduto;
	private String nmTipoLinha;
	private Integer idPlataforma;
	private String nmPlataforma;
	private Integer idOrganizacaoVenda;
	private String nmOrganizacaoVenda;
	
	public Integer getIdOrganizacaoVenda() {
		return idOrganizacaoVenda;
	}

	public void setIdOrganizacaoVenda(Integer idOrganizacaoVenda) {
		this.idOrganizacaoVenda = idOrganizacaoVenda;
	}

	public Integer getIdPlataforma() {
		return idPlataforma;
	}

	public void setIdPlataforma(Integer idPlataforma) {
		this.idPlataforma = idPlataforma;
	}

	public String getCdProduto() {
		return cdProduto;
	}

	public void setCdProduto(String cdProduto) {
		this.cdProduto = cdProduto;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getNmFabricante() {
		return nmFabricante;
	}

	public void setNmFabricante(String nmFabricante) {
		this.nmFabricante = nmFabricante;
	}

	public String getNmModelo() {
		return nmModelo;
	}

	public void setNmModelo(String nmModelo) {
		this.nmModelo = nmModelo;
	}

	public String getNmOrganizacaoVenda() {
		return nmOrganizacaoVenda;
	}

	public void setNmOrganizacaoVenda(String nmOrganizacaoVenda) {
		this.nmOrganizacaoVenda = nmOrganizacaoVenda;
	}

	public String getNmPlataforma() {
		return nmPlataforma;
	}

	public void setNmPlataforma(String nmPlataforma) {
		this.nmPlataforma = nmPlataforma;
	}

	public String getNmProduto() {
		return nmProduto;
	}

	public void setNmProduto(String nmProduto) {
		this.nmProduto = nmProduto;
	}

	public String getNmTipoLinha() {
		return nmTipoLinha;
	}

	public void setNmTipoLinha(String nmTipoLinha) {
		this.nmTipoLinha = nmTipoLinha;
	}

	public String getNmTipoProduto() {
		return nmTipoProduto;
	}

	public void setNmTipoProduto(String nmTipoProduto) {
		this.nmTipoProduto = nmTipoProduto;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idProduto: " + this.idProduto, "cdProduto: " + this.cdProduto}, ", ");
	}
}
