package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="FORMAPAGTOPARCELAMINIMA", schema = "CATALOGOPRS_OW")
public class FormaPagtoParcelaMinima implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public FormaPagtoParcelaMinima() {}
	
	@Id
	@Column(name = "IDFORMAPAGTOPARCELAMINIMA")
	private Integer idFormaPagtoParcelaMinima;

	@Column(name = "VLMINIMOPARCELA")
	private Float vlMinimoParcela;

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
	@JoinColumn(name="IDFORMAPAGAMENTO", nullable=false)
	private FormaPagamento formaPagamento;
	
	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public Integer getIdFormaPagtoParcelaMinima() {
		return idFormaPagtoParcelaMinima;
	}

	public void setIdFormaPagtoParcelaMinima(Integer idFormaPagtoParcelaMinima) {
		this.idFormaPagtoParcelaMinima = idFormaPagtoParcelaMinima;
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

	public Float getVlMinimoParcela() {
		return vlMinimoParcela;
	}

	public void setVlMinimoParcela(Float vlMinimoParcela) {
		this.vlMinimoParcela = vlMinimoParcela;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
}