package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="PRODUTOCANAL", schema = "CATALOGOPRS_OW")
public class ProdutoCanal implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public ProdutoCanal() {}
	
	@Id
	@Column(name = "IDPRODUTOCANAL")
	private Integer idProdutoCanal;

	//bi-directional many-to-one association to Canal
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCANAL", nullable=false)
	private Canal canal;

	//bi-directional many-to-one association to Produto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPRODUTO", nullable=false)
	private Produto produto;

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public Integer getIdProdutoCanal() {
		return idProdutoCanal;
	}

	public void setIdProdutoCanal(Integer idProdutoCanal) {
		this.idProdutoCanal = idProdutoCanal;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}