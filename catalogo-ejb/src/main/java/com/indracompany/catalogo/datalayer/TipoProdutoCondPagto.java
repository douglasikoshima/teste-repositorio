package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="TIPOPRODUTOCONDPAGTO", schema = "CATALOGOPRS_OW")
public class TipoProdutoCondPagto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public TipoProdutoCondPagto() {}
	
	@Id
	@Column(name = "IDTIPOPRODUTOCONDPAGTO")
	private Integer idTipoProdutoCondPagto;

	//bi-directional many-to-one association to Canal
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCANAL", nullable=false)
	private Canal canal;

	//bi-directional many-to-one association to Plataforma
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPLATAFORMA")
	private Plataforma plataforma;

	//bi-directional many-to-one association to Tipoproduto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTIPOPRODUTO", nullable=false)
	private TipoProduto tipoProduto;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCONDICAOPAGAMENTO", nullable=false)
	private CondicaoPagamento condicaoPagamento;
	
	public CondicaoPagamento getCondicaoPagamento() {
		return condicaoPagamento;
	}

	public void setCondicaoPagamento(CondicaoPagamento condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
	}

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public Integer getIdTipoProdutoCondPagto() {
		return idTipoProdutoCondPagto;
	}

	public void setIdTipoProdutoCondPagto(Integer idTipoProdutoCondPagto) {
		this.idTipoProdutoCondPagto = idTipoProdutoCondPagto;
	}

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
}