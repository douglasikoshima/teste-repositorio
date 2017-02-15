package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class PlanoCategoriaScoreTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public PlanoCategoriaScoreTO() {}
	
	public PlanoCategoriaScoreTO(Integer idPlano) {
		this.idPlano = idPlano;
	}
	
	public PlanoCategoriaScoreTO(Integer idPlanoCategoriaScore, CategoriaScoreTO categoriaScoreTO) {
		this.idPlanoCategoriaScore = idPlanoCategoriaScore;
		this.categoriaScoreTO = categoriaScoreTO;
	}
	
	private Integer idPlanoCategoriaScore;
	private Integer idPlano;
	private String nmPlano;
	private CategoriaScoreTO categoriaScoreTO;
	private Date dtUsuarioCriacao;
	private String nmUsuarioCriacao;
	private Float vlPlano;
	
	public CategoriaScoreTO getCategoriaScoreTO() {
		return categoriaScoreTO;
	}
	public void setCategoriaScoreTO(CategoriaScoreTO categoriaScoreTO) {
		this.categoriaScoreTO = categoriaScoreTO;
	}
	public Date getDtUsuarioCriacao() {
		return dtUsuarioCriacao;
	}
	public void setDtUsuarioCriacao(Date dtUsuarioCriacao) {
		this.dtUsuarioCriacao = dtUsuarioCriacao;
	}
	public Float getVlPlano() {
		return vlPlano;
	}

	public void setVlPlano(Float vlPlano) {
		this.vlPlano = vlPlano;
	}

	public Integer getIdPlano() {
		return idPlano;
	}
	public void setIdPlano(Integer idPlano) {
		this.idPlano = idPlano;
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
	
	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idPlanoCategoriaScore: " + this.idPlanoCategoriaScore}, ", ");
	}

	public String getNmPlano() {
		return nmPlano;
	}

	public void setNmPlano(String nmPlano) {
		this.nmPlano = nmPlano;
	}
}
