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
@Table(name="MATRIZOFERTAITEM", schema = "CATALOGOPRS_OW")
public class MatrizOfertaItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public MatrizOfertaItem() {}
	
	@Id
	@Column(unique=true, nullable=false, precision=22)
	private Integer idMatrizOfertaItem;

    @Temporal( TemporalType.DATE)
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
	private Date dtUltimaAlteracao;

	@Column(length=255)
	private String inDisponivel;

	@Column(length=255)
	private String nmUsuarioAlteracao;

	@Column(length=255)
	private String nmUsuarioCriacao;

	//bi-directional many-to-one association to Ofertasap
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDOFERTASAP")
	private OfertaSAP ofertaSAP;

	//bi-directional many-to-one association to Produto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPRODUTO")
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

	public Integer getIdMatrizOfertaItem() {
		return idMatrizOfertaItem;
	}

	public void setIdMatrizOfertaItem(Integer idMatrizOfertaItem) {
		this.idMatrizOfertaItem = idMatrizOfertaItem;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
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

	public OfertaSAP getOfertaSAP() {
		return ofertaSAP;
	}

	public void setOfertaSAP(OfertaSAP ofertaSAP) {
		this.ofertaSAP = ofertaSAP;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}