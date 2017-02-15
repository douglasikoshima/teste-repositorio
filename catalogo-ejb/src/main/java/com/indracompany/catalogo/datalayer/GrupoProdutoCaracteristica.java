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
@SequenceGenerator(name = "GRUPOPRODUTOCARACTERISTICA_SQ" ,sequenceName = "CATALOGOPRS_OW.GRUPOPRODUTOCARACTERISTICA_SQ", allocationSize = 1)
@Table(name = "GRUPOPRODUTOCARACTERISTICA", schema = "CATALOGOPRS_OW")
public class GrupoProdutoCaracteristica implements Serializable {
	private static final long serialVersionUID = 1L;

    public GrupoProdutoCaracteristica() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRUPOPRODUTOCARACTERISTICA_SQ")
	@Column(name = "IDGRUPOPRODUTOCARACTERISTICA")
	private Integer idGrupoProdutoCaracteristica;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCARACTERISTICA")
	private Caracteristica caracteristica;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDGRUPOPRODUTO")
	private GrupoProduto grupoProduto;
	
	@Temporal( TemporalType.DATE)
	@Column(name = "DTCRIACAO")
	private Date dtCriacao;
	
	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;
	
	@Temporal( TemporalType.DATE)
	@Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;
	
	@Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDVALORCARACTERISTICA")
	private ValorCaracteristica valorCaracteristica;

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

	public Integer getIdGrupoProdutoCaracteristica() {
		return idGrupoProdutoCaracteristica;
	}

	public void setIdGrupoProdutoCaracteristica(Integer idGrupoProdutoCaracteristica) {
		this.idGrupoProdutoCaracteristica = idGrupoProdutoCaracteristica;
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

	public Caracteristica getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(Caracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}

	public GrupoProduto getGrupoProduto() {
		return grupoProduto;
	}

	public void setGrupoProduto(GrupoProduto grupoProduto) {
		this.grupoProduto = grupoProduto;
	}

	public ValorCaracteristica getValorCaracteristica() {
		return valorCaracteristica;
	}

	public void setValorCaracteristica(ValorCaracteristica valorCaracteristica) {
		this.valorCaracteristica = valorCaracteristica;
	}
	
}