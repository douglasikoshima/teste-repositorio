package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the PLANOCSA database table.
 * 
 */
@Entity
@Table(name="PLANOCSA", schema = "CATALOGOPRS_OW")
public class PlanoCSA implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public PlanoCSA() {}
	
	@Id
	@Column(name = "IDPLANOCSA")
	private Integer idPlanocsa;

	@Column(name = "CDANATEL")
	private String cdAnatel;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTFINAL")
	private Date dtFinal;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTINICIAL")
	private Date dtInicial;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "INDISPONIVEL")
	private String inDisponivel;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

	@Column(name = "QTMINFRANQUIA")
	private Integer qtMinfranquia;

	@Column(name = "VALORASSINATURAMENSAL")
	private Float valorAssinaturaMensal;

	//bi-directional many-to-one association to Csa
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCSA", nullable=false)
	private CSA csa;

	//bi-directional many-to-one association to Plano
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPLANO", nullable=false)
	private Plano plano;

	public String getCdAnatel() {
		return cdAnatel;
	}

	public void setCdAnatel(String cdAnatel) {
		this.cdAnatel = cdAnatel;
	}

	public CSA getCsa() {
		return csa;
	}

	public void setCsa(CSA csa) {
		this.csa = csa;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}

	public Date getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}

	public Date getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public Integer getIdPlanocsa() {
		return idPlanocsa;
	}

	public void setIdPlanocsa(Integer idPlanocsa) {
		this.idPlanocsa = idPlanocsa;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
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

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public Integer getQtMinfranquia() {
		return qtMinfranquia;
	}

	public void setQtMinfranquia(Integer qtMinfranquia) {
		this.qtMinfranquia = qtMinfranquia;
	}

	public Float getValorAssinaturaMensal() {
		return valorAssinaturaMensal;
	}

	public void setValorAssinaturaMensal(Float valorAssinaturaMensal) {
		this.valorAssinaturaMensal = valorAssinaturaMensal;
	}
}