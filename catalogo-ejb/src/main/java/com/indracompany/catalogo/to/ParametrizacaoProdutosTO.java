package com.indracompany.catalogo.to;

import java.util.List;

/**
 * 
 * @author Luciano
 *
 *	TO da tela de Parametrizacao Produtos.
 *	Parametrizacao-> Produtos -> Produtos
 */
public class ParametrizacaoProdutosTO extends PaginacaoDataTableTO {

	private static final long serialVersionUID = 1901528724218943954L;

	// filtros
	private Integer idTipoProduto;
	private Integer idFabricante;
	private Integer idTecnologia;
	private Boolean filtrarModelos;
	private Integer idModelo;
	private String codigoProduto;
	private Boolean removerVinculos;
	private Integer idMultimidia;

	//resultado
	private Integer idProduto;
	private String descricaoSAP;
	private String descricaoProdutoCatalogo;
	private String nomeTipoProduto;
	private String nomeTecnologia;
	private String nomeFabricante;
	private Integer idGrupoProduto;
	private String nomeGrupoProduto;
	private String nomeProduto;
	private String nomeCor;
	private String rgbCor;
	private String descricaoNota;
	private String nomeUsuarioAltercao = null;
	private List<TipoProdutoTO> tipoProdutos = null;
	private List<GrupoProdutoTO> grupoProdutoTOs = null;
	private List<Integer> idProdutos = null;
	public String getCodigoProduto() {
		return codigoProduto;
	}
	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	public Integer getIdFabricante() {
		return idFabricante;
	}
	public void setIdFabricante(Integer idFabricante) {
		this.idFabricante = idFabricante;
	}
	public Integer getIdModelo() {
		return idModelo;
	}
	public void setIdModelo(Integer idModelo) {
		this.idModelo = idModelo;
	}
	public Integer getIdTecnologia() {
		return idTecnologia;
	}
	public void setIdTecnologia(Integer idTecnologia) {
		this.idTecnologia = idTecnologia;
	}
	public Integer getIdTipoProduto() {
		return idTipoProduto;
	}
	public void setIdTipoProduto(Integer idTipoProduto) {
		this.idTipoProduto = idTipoProduto;
	}
	public String getDescricaoProdutoCatalogo() {
		return descricaoProdutoCatalogo;
	}
	public void setDescricaoProdutoCatalogo(String descricaoProdutoCatalogo) {
		this.descricaoProdutoCatalogo = descricaoProdutoCatalogo;
	}
	public String getDescricaoSAP() {
		return descricaoSAP;
	}
	public void setDescricaoSAP(String descricaoSAP) {
		this.descricaoSAP = descricaoSAP;
	}
	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	public String getNomeCor() {
		return nomeCor;
	}
	public void setNomeCor(String nomeCor) {
		this.nomeCor = nomeCor;
	}
	public String getNomeFabricante() {
		return nomeFabricante;
	}
	public void setNomeFabricante(String nomeFabricante) {
		this.nomeFabricante = nomeFabricante;
	}
	public String getNomeGrupoProduto() {
		return nomeGrupoProduto;
	}
	public void setNomeGrupoProduto(String nomeGrupoProduto) {
		this.nomeGrupoProduto = nomeGrupoProduto;
	}
	public String getNomeTecnologia() {
		return nomeTecnologia;
	}
	public void setNomeTecnologia(String nomeTecnologia) {
		this.nomeTecnologia = nomeTecnologia;
	}
	public String getNomeTipoProduto() {
		return nomeTipoProduto;
	}
	public void setNomeTipoProduto(String nomeTipoProduto) {
		this.nomeTipoProduto = nomeTipoProduto;
	}
	public Boolean getFiltrarModelos() {
		return filtrarModelos;
	}
	public void setFiltrarModelos(Boolean filtrarModelos) {
		this.filtrarModelos = filtrarModelos;
	}
	public String getRgbCor() {
		return rgbCor;
	}
	public void setRgbCor(String rgbCor) {
		this.rgbCor = rgbCor;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public String getDescricaoNota() {
		return descricaoNota;
	}
	public void setDescricaoNota(String descricaoNota) {
		this.descricaoNota = descricaoNota;
	}
	public List<TipoProdutoTO> getTipoProdutos() {
		return tipoProdutos;
	}
	public void setTipoProdutos(List<TipoProdutoTO> tipoProdutos) {
		this.tipoProdutos = tipoProdutos;
	}
	public String getNomeUsuarioAltercao() {
		return nomeUsuarioAltercao;
	}
	public void setNomeUsuarioAltercao(String nomeUsuarioAltercao) {
		this.nomeUsuarioAltercao = nomeUsuarioAltercao;
	}
	public List<GrupoProdutoTO> getGrupoProdutoTOs() {
		return grupoProdutoTOs;
	}
	public void setGrupoProdutoTOs(List<GrupoProdutoTO> grupoProdutoTOs) {
		this.grupoProdutoTOs = grupoProdutoTOs;
	}
	public List<Integer> getIdProdutos() {
		return idProdutos;
	}
	public void setIdProdutos(List<Integer> idProdutos) {
		this.idProdutos = idProdutos;
	}
	public Boolean getRemoverVinculos() {
		return removerVinculos;
	}
	public void setRemoverVinculos(Boolean removerVinculos) {
		this.removerVinculos = removerVinculos;
	}
	public Integer getIdMultimidia() {
		return idMultimidia;
	}
	public void setIdMultimidia(Integer idMultimidia) {
		this.idMultimidia = idMultimidia;
	}
	public Integer getIdGrupoProduto() {
		return idGrupoProduto;
	}
	public void setIdGrupoProduto(Integer idGrupoProduto) {
		this.idGrupoProduto = idGrupoProduto;
	}
}
