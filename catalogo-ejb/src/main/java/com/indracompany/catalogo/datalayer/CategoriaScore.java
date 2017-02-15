package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIASCORE", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "CATEGORIASCORE_SQ", sequenceName = "CATALOGOPRS_OW.CATEGORIASCORE_SQ", allocationSize = 1)
@NamedQuery(name = "CategoriaScore.findAll", query = "SELECT c FROM CategoriaScore c")
public class CategoriaScore implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public CategoriaScore() {}
	
	public CategoriaScore(Integer idCategoriaScore) {
		this.idCategoriaScore = idCategoriaScore;
	}
	
	@Id
	@Column(name = "IDCATEGORIASCORE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORIASCORE_SQ")
	private Integer idCategoriaScore;
	
	@Column(name = "CDCATEGORIASCORE")
	private String cdCategoriaScore;
	
	@Column(name = "NMCATEGORIASCORE")
	private String nmCategoriaScore;
	
	@Column(name = "DTCRIACAO")
	private Date dtCriacao;
	
	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;
	
	@Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;
	
	@Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;
	
	@ManyToOne
	@JoinColumn(name = "IDCLASSIFICACAOCATEGORIASCORE")
	private ClassificacaoCategoriaScore classificacaoCategoriaScore;
	
	@OneToMany(mappedBy = "categoriaScore", cascade = CascadeType.REMOVE)
	private List<PlanoCategoriaScore> planoCategoriaScoreList;
	
	@OneToMany(mappedBy = "categoriaScore", cascade = CascadeType.REMOVE)
	private List<ServicoCategoriaScore> servicoCategoriaScoreList;
	
	@OneToMany(mappedBy = "categoriaScore", cascade = CascadeType.REMOVE)
	private List<OfertaServicoCategoriaScore> ofertaServicoCategoriaScoreList;
	
	public List<OfertaServicoCategoriaScore> getOfertaServicoCategoriaScoreList() {
		return ofertaServicoCategoriaScoreList;
	}

	public void setOfertaServicoCategoriaScoreList(
			List<OfertaServicoCategoriaScore> ofertaServicoCategoriaScoreList) {
		this.ofertaServicoCategoriaScoreList = ofertaServicoCategoriaScoreList;
	}

	public List<ServicoCategoriaScore> getServicoCategoriaScoreList() {
		return servicoCategoriaScoreList;
	}

	public void setServicoCategoriaScoreList(
			List<ServicoCategoriaScore> servicoCategoriaScoreList) {
		this.servicoCategoriaScoreList = servicoCategoriaScoreList;
	}

	public String getCdCategoriaScore() {
		return cdCategoriaScore;
	}

	public void setCdCategoriaScore(String cdCategoriaScore) {
		this.cdCategoriaScore = cdCategoriaScore;
	}

	public ClassificacaoCategoriaScore getClassificacaoCategoriaScore() {
		return classificacaoCategoriaScore;
	}

	public void setClassificacaoCategoriaScore(
			ClassificacaoCategoriaScore classificacaoCategoriaScore) {
		this.classificacaoCategoriaScore = classificacaoCategoriaScore;
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

	public Integer getIdCategoriaScore() {
		return idCategoriaScore;
	}

	public void setIdCategoriaScore(Integer idCategoriaScore) {
		this.idCategoriaScore = idCategoriaScore;
	}

	public String getNmCategoriaScore() {
		return nmCategoriaScore;
	}

	public void setNmCategoriaScore(String nmCategoriaScore) {
		this.nmCategoriaScore = nmCategoriaScore;
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

	public List<PlanoCategoriaScore> getPlanoCategoriaScoreList() {
		return planoCategoriaScoreList;
	}

	public void setPlanoCategoriaScoreList(
			List<PlanoCategoriaScore> planoCategoriaScoreList) {
		this.planoCategoriaScoreList = planoCategoriaScoreList;
	}
	
}
