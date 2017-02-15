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


@Entity
@Table(name = "OFERTASERVICOCATEGORIASCORE", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "OFERTASERVICOCATEGORIASCORE_SQ", sequenceName = "CATALOGOPRS_OW.OFERTASERVICOCATEGORIASCORE_SQ", allocationSize = 1)
public class OfertaServicoCategoriaScore implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public OfertaServicoCategoriaScore(){}
	
	public OfertaServicoCategoriaScore(Integer idOfertaServicoCategoriaScore){
		this.idOfertaServicoCategoriaScore = idOfertaServicoCategoriaScore;
	}
	
	@Id
	@Column(name = "IDOFERTASERVICOCATEGORIASCORE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OFERTASERVICOCATEGORIASCORE_SQ")
	private Integer idOfertaServicoCategoriaScore;

	@Column(name = "DTCRIACAO")
	private Date dtUsuarioCriacao;
	
	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="IDCATEGORIASCORE")
	private CategoriaScore categoriaScore;
	
	//bi-directional many-to-one association to Ofertaservico
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="IDOFERTASERVICO", nullable=false)
	private OfertaServico ofertaServico;
	
	public CategoriaScore getCategoriaScore() {
		return categoriaScore;
	}

	public void setCategoriaScore(CategoriaScore categoriaScore) {
		this.categoriaScore = categoriaScore;
	}

	public Date getDtUsuarioCriacao() {
		return dtUsuarioCriacao;
	}

	public void setDtUsuarioCriacao(Date dtUsuarioCriacao) {
		this.dtUsuarioCriacao = dtUsuarioCriacao;
	}

	public OfertaServico getOfertaServico() {
		return ofertaServico;
	}

	public void setOfertaServico(OfertaServico ofertaServico) {
		this.ofertaServico = ofertaServico;
	}

	public Integer getIdOfertaServicoCategoriaScore() {
		return idOfertaServicoCategoriaScore;
	}

	public void setIdOfertaServicoCategoriaScore(
			Integer idOfertaServicoCategoriaScore) {
		this.idOfertaServicoCategoriaScore = idOfertaServicoCategoriaScore;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

	
}
