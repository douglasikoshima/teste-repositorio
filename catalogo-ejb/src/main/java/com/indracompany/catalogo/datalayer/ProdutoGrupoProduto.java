package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name = "PRODUTOGRUPOPRODUTO", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "PRODUTOGRUPOPRODUTO_SQ" ,sequenceName = "CATALOGOPRS_OW.PRODUTOGRUPOPRODUTO_SQ", allocationSize = 1)
public class ProdutoGrupoProduto implements Serializable {
	
	private static final long serialVersionUID = 1L;

    public ProdutoGrupoProduto() {}
    
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUTOGRUPOPRODUTO_SQ")
	@Column(name = "IDPRODUTOGRUPOPRODUTO")
	private Integer idProdutoGrupoProduto;
	
	@Column(name = "INDISPONIVEL")
	private String inDisponivel;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPRODUTO")
	private Produto produto;
	
	@Column(name = "INVENDACRUZADA")
	private String inVendaCruzada;

	@Temporal( TemporalType.DATE)
	@Column(name = "DTCRIACAO")
	private Date dtCriacao;
		
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDGRUPOPRODUTO")
	private GrupoProduto grupoProduto;

	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;
	
	@Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;
	
	@Temporal( TemporalType.DATE)
	@Column(name = "DTULTIMAALTERACAO")
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

	public GrupoProduto getGrupoProduto() {
		return grupoProduto;
	}

	public void setGrupoProduto(GrupoProduto grupoProduto) {
		this.grupoProduto = grupoProduto;
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

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}