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
@Table(name = "PLANOCATEGORIASCORE", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "PLANOCATEGORIASCORE_SQ", sequenceName = "CATALOGOPRS_OW.PLANOCATEGORIASCORE_SQ", allocationSize = 1)
public class PlanoCategoriaScore implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public PlanoCategoriaScore(){}
	
	public PlanoCategoriaScore(Integer idPlanoCategoriaScore){
		this.idPlanoCategoriaScore = idPlanoCategoriaScore;
	}
	
	@Id
	@Column(name = "IDPLANOCATEGORIASCORE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLANOCATEGORIASCORE_SQ")
	private Integer idPlanoCategoriaScore;
	
	@Column(name = "DTCRIACAO")
	private Date dtUsuarioCriacao;
	
	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="IDCATEGORIASCORE")
	private CategoriaScore categoriaScore;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="IDPLANO")
	private Plano plano;
	
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

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public Integer getIdPlanoCategoriaScore() {
		return idPlanoCategoriaScore;
	}

	public void setIdPlanoCategoriaScore(Integer idPlanoCategoriaScore) {
		this.idPlanoCategoriaScore = idPlanoCategoriaScore;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

}
