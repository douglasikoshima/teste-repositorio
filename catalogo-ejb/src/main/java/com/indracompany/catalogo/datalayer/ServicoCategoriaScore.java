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
@Table(name = "SERVICOCATEGORIASCORE", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "SERVICOCATEGORIASCORE_SQ", sequenceName = "CATALOGOPRS_OW.SERVICOCATEGORIASCORE_SQ", allocationSize = 1)
public class ServicoCategoriaScore implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public ServicoCategoriaScore(){}
	
	public ServicoCategoriaScore(Integer idServicoCategoriaScore){
		this.idServicoCategoriaScore = idServicoCategoriaScore;
	}
	
	@Id
	@Column(name = "IDSERVICOCATEGORIASCORE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICOCATEGORIASCORE_SQ")
	private Integer idServicoCategoriaScore;

	@Column(name = "DTCRIACAO")
	private Date dtUsuarioCriacao;
	
	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="IDCATEGORIASCORE")
	private CategoriaScore categoriaScore;
	
	// bi-directional many-to-one association to Servico
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="IDSERVICO", nullable=false)
	private Servico servico;
	
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

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Integer getIdServicoCategoriaScore() {
		return idServicoCategoriaScore;
	}

	public void setIdServicoCategoriaScore(Integer idServicoCategoriaScore) {
		this.idServicoCategoriaScore = idServicoCategoriaScore;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}


}
