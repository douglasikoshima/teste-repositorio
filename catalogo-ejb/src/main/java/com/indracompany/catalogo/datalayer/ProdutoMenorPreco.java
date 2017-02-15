package com.indracompany.catalogo.datalayer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUTOMENORPRECO", schema = "CATALOGOPRS_OW")
public class ProdutoMenorPreco {
	
	@Id
	@Column(name = "IDPRODUTOMENORPRECO") 
	private Long idProdutoMenorPreco;
	
	@Column(name = "IDMATRIZOFERTAITEMPRECO")
	private Long idMatrizOfertaItemPreco;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDMATRIZOFERTAITEMPRECO", insertable = false , updatable = false)
	private MatrizOfertaItemPreco matrizOfertaItemPreco;

	public Long getIdMatrizOfertaItemPreco() {
		return idMatrizOfertaItemPreco;
	}

	public void setIdMatrizOfertaItemPreco(Long idMatrizOfertaItemPreco) {
		this.idMatrizOfertaItemPreco = idMatrizOfertaItemPreco;
	}

	public Long getIdProdutoMenorPreco() {
		return idProdutoMenorPreco;
	}

	public void setIdProdutoMenorPreco(Long idProdutoMenorPreco) {
		this.idProdutoMenorPreco = idProdutoMenorPreco;
	}

	public MatrizOfertaItemPreco getMatrizOfertaItemPreco() {
		return matrizOfertaItemPreco;
	}

	public void setMatrizOfertaItemPreco(MatrizOfertaItemPreco matrizOfertaItemPreco) {
		this.matrizOfertaItemPreco = matrizOfertaItemPreco;
	}
}
