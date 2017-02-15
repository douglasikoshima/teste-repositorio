package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

public class ClassificacaoCategoriaScoreTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idClassificacaoCategoriaScore;
	private String nmClassificacaoCategoriaScore;
	private String cdClassificacaoCategoriaScore;
	private Date dtCriacao;
	private String nmUsuarioCriacao;
	private Date dtUltimaAlteracao;
	private String nmUsuarioAlteracao;
	
	public String getCdClassificacaoCategoriaScore() {
		return cdClassificacaoCategoriaScore;
	}
	public void setCdClassificacaoCategoriaScore(
			String cdClassificacaoCategoriaScore) {
		this.cdClassificacaoCategoriaScore = cdClassificacaoCategoriaScore;
	}
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
	public Integer getIdClassificacaoCategoriaScore() {
		return idClassificacaoCategoriaScore;
	}
	public void setIdClassificacaoCategoriaScore(
			Integer idClassificacaoCategoriaScore) {
		this.idClassificacaoCategoriaScore = idClassificacaoCategoriaScore;
	}
	public String getNmClassificacaoCategoriaScore() {
		return nmClassificacaoCategoriaScore;
	}
	public void setNmClassificacaoCategoriaScore(
			String nmClassificacaoCategoriaScore) {
		this.nmClassificacaoCategoriaScore = nmClassificacaoCategoriaScore;
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
	
	
}
