package com.indracompany.catalogo.datalayer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUTOMELHOROFERTA", schema = "CATALOGOPRS_OW")
public class ProdutoMelhorOferta {
	
	@Id
	@Column(name = "IDMATRIZOFERTAITEMPRECO")
	private Long idMatrizOfertaItemPreco;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDMATRIZOFERTAITEMPRECO" , insertable = false , updatable = false)
	private MatrizOfertaItemPreco matrizOfertaItemPreco;

	public Long getIdMatrizOfertaItemPreco() {
		return idMatrizOfertaItemPreco;
	}

	public void setIdMatrizOfertaItemPreco(Long idMatrizOfertaItemPreco) {
		this.idMatrizOfertaItemPreco = idMatrizOfertaItemPreco;
	}

	public MatrizOfertaItemPreco getMatrizOfertaItemPreco() {
		return matrizOfertaItemPreco;
	}

	public void setMatrizOfertaItemPreco(MatrizOfertaItemPreco matrizOfertaItemPreco) {
		this.matrizOfertaItemPreco = matrizOfertaItemPreco;
	}
}
