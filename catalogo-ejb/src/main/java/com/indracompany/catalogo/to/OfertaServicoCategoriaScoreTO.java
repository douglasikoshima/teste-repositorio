package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class OfertaServicoCategoriaScoreTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public OfertaServicoCategoriaScoreTO() {}
	
	public OfertaServicoCategoriaScoreTO(Integer idOfertaServico) {
		this.idOfertaServico = idOfertaServico;
	}
	
	public OfertaServicoCategoriaScoreTO(Integer idOfertaServicoCategoriaScore, CategoriaScoreTO categoriaScoreTO) {
		this.idOfertaServicoCategoriaScore = idOfertaServicoCategoriaScore;
		this.categoriaScoreTO = categoriaScoreTO;
	}
	
	private Integer idOfertaServicoCategoriaScore;
	private Integer idOfertaServico;
	private String nmOfertaServico;
	private CategoriaScoreTO categoriaScoreTO;
	private Date dtUsuarioCriacao;
	private String nmUsuarioCriacao;
	private Float vlOfertaServico;
	
	public String getNmOfertaServico() {
		return nmOfertaServico;
	}

	public void setNmOfertaServico(String nmOfertaServico) {
		this.nmOfertaServico = nmOfertaServico;
	}

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
	public Integer getIdOfertaServico() {
		return idOfertaServico;
	}
	public void setIdOfertaServico(Integer idOfertaServico) {
		this.idOfertaServico = idOfertaServico;
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
	
	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idOfertaServicoCategoriaScore: " + this.idOfertaServicoCategoriaScore}, ", ");
	}

	public Float getVlOfertaServico() {
		return vlOfertaServico;
	}

	public void setVlOfertaServico(Float vlOfertaServico) {
		this.vlOfertaServico = vlOfertaServico;
	}
}
