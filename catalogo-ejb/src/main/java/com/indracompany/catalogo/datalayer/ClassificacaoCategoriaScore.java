package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "CLASSIFICACAOCATEGORIASCORE", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "CLASSIFICACAOCATEGORIASCORE_SQ", sequenceName = "CLASSIFICACAOCATEGORIASCORE_SQ", allocationSize = 1)
@NamedQuery(name = "ClassificacaoCategoriaScore.findAll", query = "SELECT c FROM ClassificacaoCategoriaScore c")
public class ClassificacaoCategoriaScore implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public ClassificacaoCategoriaScore() {}
	
	@Id
	@Column(name = "IDCLASSIFICACAOCATEGORIASCORE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLASSIFICACAOCATEGORIASCORE_SQ")
	private Integer idClassificacaoCategoriaScore;
	
	@Column(name = "NMCLASSIFICACAOCATEGORIASCORE")
	private String nmClassificacaoCategoriaScore;
	
	@Column(name = "CDCLASSIFICACAOCATEGORIASCORE")
	private String cdClassificacaoCategoriaScore;
	
	@Column(name = "DTCRIACAO")
	private Date dtCriacao;
	
	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;
	
	@Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;
	
	@Column(name = "NMUSUARIOALTERACAO")
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
