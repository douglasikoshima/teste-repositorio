package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class ServicoCategoriaScoreTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public ServicoCategoriaScoreTO() {}
	
	public ServicoCategoriaScoreTO(Integer idServico) {
		this.idServico = idServico;
	}
	
	public ServicoCategoriaScoreTO(Integer idServicoCategoriaScore, CategoriaScoreTO categoriaScoreTO) {
		this.idServicoCategoriaScore = idServicoCategoriaScore;
		this.categoriaScoreTO = categoriaScoreTO;
	}
	
	private Integer idServicoCategoriaScore;
	private Integer idServico;
	private String nmServico;
	private Float vlServico;
	private CategoriaScoreTO categoriaScoreTO;
	private Date dtUsuarioCriacao;
	private String nmUsuarioCriacao;
	
	public String getNmServico() {
		return nmServico;
	}

	public void setNmServico(String nmServico) {
		this.nmServico = nmServico;
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
	public Integer getIdServico() {
		return idServico;
	}
	public void setIdServico(Integer idServico) {
		this.idServico = idServico;
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
	
	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idServicoCategoriaScore: " + this.idServicoCategoriaScore}, ", ");
	}

	public Float getVlServico() {
		return vlServico;
	}

	public void setVlServico(Float vlServico) {
		this.vlServico = vlServico;
	}
}
