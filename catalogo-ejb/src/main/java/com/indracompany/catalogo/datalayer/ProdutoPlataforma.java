package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="PRODUTOPLATAFORMA", schema = "CATALOGOPRS_OW")
public class ProdutoPlataforma implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public ProdutoPlataforma() {}
	
	@Id
	@Column(name = "IDPRODUTOPLATAFORMA")
	private Integer idProdutoPlataforma;

	//bi-directional many-to-one association to Plataforma
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPLATAFORMA", nullable=false)
	private Plataforma plataforma;

	//bi-directional many-to-one association to Produto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPRODUTO", nullable=false)
	private Produto produto;

	public Integer getIdProdutoPlataforma() {
		return idProdutoPlataforma;
	}

	public void setIdProdutoPlataforma(Integer idProdutoPlataforma) {
		this.idProdutoPlataforma = idProdutoPlataforma;
	}

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}