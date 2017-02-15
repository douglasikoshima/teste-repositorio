package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="PRODUTOMODELOVENDA", schema = "CATALOGOPRS_OW")
public class ProdutoModeloVenda implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public ProdutoModeloVenda() {}
	
	@Id
	@Column(name = "IDPRODUTOMODELOVENDA")
	private Integer idProdutoModeloVenda;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

	//bi-directional many-to-one association to Modelovenda
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDMODELOVENDA", nullable=false)
	private ModeloVenda modeloVenda;

	//bi-directional many-to-one association to Produto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPRODUTO", nullable=false)
	private Produto produto;

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

	public Integer getIdProdutoModeloVenda() {
		return idProdutoModeloVenda;
	}

	public void setIdProdutoModeloVenda(Integer idProdutoModeloVenda) {
		this.idProdutoModeloVenda = idProdutoModeloVenda;
	}

	public ModeloVenda getModeloVenda() {
		return modeloVenda;
	}

	public void setModeloVenda(ModeloVenda modeloVenda) {
		this.modeloVenda = modeloVenda;
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

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}