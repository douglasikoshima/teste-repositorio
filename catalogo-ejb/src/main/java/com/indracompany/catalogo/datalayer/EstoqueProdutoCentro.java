package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="ESTOQUEPRODUTOCENTRO", schema = "CATALOGOPRS_OW")
public class EstoqueProdutoCentro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public EstoqueProdutoCentro() {}
	
	@Id
	@Column(name = "IDESTOQUEPRODUTOCENTRO")
	private Integer idEstoqueProdutoCentro;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtcriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtultimaalteracao;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmusuarioalteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmusuariocriacao;

    @Column(name = "QTDEESTOQUE")
	private Integer qtdeestoque;

	//bi-directional many-to-one association to Centro
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCENTRO", nullable=false)
	private Centro centro;

	//bi-directional many-to-one association to Produto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPRODUTO", nullable=false)
	private Produto produto;

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	public Date getDtcriacao() {
		return dtcriacao;
	}

	public void setDtcriacao(Date dtcriacao) {
		this.dtcriacao = dtcriacao;
	}

	public Date getDtultimaalteracao() {
		return dtultimaalteracao;
	}

	public void setDtultimaalteracao(Date dtultimaalteracao) {
		this.dtultimaalteracao = dtultimaalteracao;
	}

	public Integer getIdEstoqueProdutoCentro() {
		return idEstoqueProdutoCentro;
	}

	public void setIdEstoqueProdutoCentro(Integer idEstoqueProdutoCentro) {
		this.idEstoqueProdutoCentro = idEstoqueProdutoCentro;
	}

	public String getNmusuarioalteracao() {
		return nmusuarioalteracao;
	}

	public void setNmusuarioalteracao(String nmusuarioalteracao) {
		this.nmusuarioalteracao = nmusuarioalteracao;
	}

	public String getNmusuariocriacao() {
		return nmusuariocriacao;
	}

	public void setNmusuariocriacao(String nmusuariocriacao) {
		this.nmusuariocriacao = nmusuariocriacao;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQtdeestoque() {
		return qtdeestoque;
	}

	public void setQtdeestoque(Integer qtdeestoque) {
		this.qtdeestoque = qtdeestoque;
	}
}