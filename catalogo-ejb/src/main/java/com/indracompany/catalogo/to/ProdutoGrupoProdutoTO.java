package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author Luiz Pereira
 *
 */
public class ProdutoGrupoProdutoTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ProdutoGrupoProdutoTO() {}
	
	public ProdutoGrupoProdutoTO(Integer idProdutoGrupoProduto) {
		this.idProdutoGrupoProduto = idProdutoGrupoProduto;
	}
	
	private Integer idProdutoGrupoProduto;
	private String inDisponivel;
	private ProdutoTO produtoTO;
	private String inVendaCruzada;
	private Date dtCriacao;
	private GrupoProdutoTO grupoProdutoTO;
	private String nmUsuarioCriacao;
	private String nmUsuarioAlteracao;
	private Date dtUltimaAlteracao;
	
	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public GrupoProdutoTO getGrupoProdutoTO() {
		return grupoProdutoTO;
	}

	public void setGrupoProdutoTO(GrupoProdutoTO grupoProdutoTO) {
		this.grupoProdutoTO = grupoProdutoTO;
	}

	public Integer getIdProdutoGrupoProduto() {
		return idProdutoGrupoProduto;
	}

	public void setIdProdutoGrupoProduto(Integer idProdutoGrupoProduto) {
		this.idProdutoGrupoProduto = idProdutoGrupoProduto;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public String getInVendaCruzada() {
		return inVendaCruzada;
	}

	public void setInVendaCruzada(String inVendaCruzada) {
		this.inVendaCruzada = inVendaCruzada;
	}

	public String getNmUsuarioAlteracao() {
		return nmUsuarioAlteracao;
	}

	public void setNmUsuarioAlteracao(String nmUsuarioAlteracao) {
		this.nmUsuarioAlteracao = nmUsuarioAlteracao;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

	public ProdutoTO getProdutoTO() {
		return produtoTO;
	}

	public void setProdutoTO(ProdutoTO produtoTO) {
		this.produtoTO = produtoTO;
	}
	
	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idProdutoGrupoProduto: " + this.idProdutoGrupoProduto, "cdCodigoinDisponivel: " + this.inDisponivel}, ", ");
	}
}
