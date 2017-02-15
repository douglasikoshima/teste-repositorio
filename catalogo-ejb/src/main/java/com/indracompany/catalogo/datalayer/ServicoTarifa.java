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
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="SERVICOTARIFA", schema = "CATALOGOPRS_OW")
public class ServicoTarifa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public ServicoTarifa() {}
	
	@Id
	@Column(name = "IDSERVICOTARIFA")
	private Integer idServicoTarifa;

	@Column(name = "CDPERCOBRANCA")
	private String cdPerCobranca;

	@Column(name = "CDTPTARIFA")
	private String cdTpTarifa;

	@Column(name = "DSCCOBRANCA")
	private String dscCobranca;

	@Column(name = "DSCTARIFA")
	private String dscTarifa;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "INDTARIFA")
	private String indTarifa;

    @Column(name = "INNEGOCIAVEL")
	private String inNegociavel;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

    @Column(name = "PRECOBASE")
	private Float precoBase;

    @Column(name = "PRECOBASEIMP")
	private String precoBaseImp;

    @Column(name = "TPDEBITOTARIFA")
	private String tpDebitoTarifa;

    @Column(name = "TPUNIDADETARIFA")
	private String tpUnidadeTarifa;

    @Column(name = "VLMAXNEGOCIACAO")
	private Float vlMaxNegociacao;

    @Column(name = "VLMINNEGOCIACAO")
	private Float vlMinNegociacao;

	//bi-directional many-to-one association to Csa
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCSA", nullable=false)
	private CSA csa;

	//bi-directional many-to-one association to Servico
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDSERVICO", nullable=false)
	private Servico servico;

	public String getCdPerCobranca() {
		return cdPerCobranca;
	}

	public void setCdPerCobranca(String cdPerCobranca) {
		this.cdPerCobranca = cdPerCobranca;
	}

	public String getCdTpTarifa() {
		return cdTpTarifa;
	}

	public void setCdTpTarifa(String cdTpTarifa) {
		this.cdTpTarifa = cdTpTarifa;
	}

	public CSA getCsa() {
		return csa;
	}

	public void setCsa(CSA csa) {
		this.csa = csa;
	}

	public String getDscCobranca() {
		return dscCobranca;
	}

	public void setDscCobranca(String dscCobranca) {
		this.dscCobranca = dscCobranca;
	}

	public String getDscTarifa() {
		return dscTarifa;
	}

	public void setDscTarifa(String dscTarifa) {
		this.dscTarifa = dscTarifa;
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

	public Integer getIdServicoTarifa() {
		return idServicoTarifa;
	}

	public void setIdServicoTarifa(Integer idServicoTarifa) {
		this.idServicoTarifa = idServicoTarifa;
	}

	public String getIndTarifa() {
		return indTarifa;
	}

	public void setIndTarifa(String indTarifa) {
		this.indTarifa = indTarifa;
	}

	public String getInNegociavel() {
		return inNegociavel;
	}

	public void setInNegociavel(String inNegociavel) {
		this.inNegociavel = inNegociavel;
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

	public Float getPrecoBase() {
		return precoBase;
	}

	public void setPrecoBase(Float precoBase) {
		this.precoBase = precoBase;
	}

	public String getPrecoBaseImp() {
		return precoBaseImp;
	}

	public void setPrecoBaseImp(String precoBaseImp) {
		this.precoBaseImp = precoBaseImp;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public String getTpDebitoTarifa() {
		return tpDebitoTarifa;
	}

	public void setTpDebitoTarifa(String tpDebitoTarifa) {
		this.tpDebitoTarifa = tpDebitoTarifa;
	}

	public String getTpUnidadeTarifa() {
		return tpUnidadeTarifa;
	}

	public void setTpUnidadeTarifa(String tpUnidadeTarifa) {
		this.tpUnidadeTarifa = tpUnidadeTarifa;
	}

	public Float getVlMaxNegociacao() {
		return vlMaxNegociacao;
	}

	public void setVlMaxNegociacao(Float vlMaxNegociacao) {
		this.vlMaxNegociacao = vlMaxNegociacao;
	}

	public Float getVlMinNegociacao() {
		return vlMinNegociacao;
	}

	public void setVlMinNegociacao(Float vlMinNegociacao) {
		this.vlMinNegociacao = vlMinNegociacao;
	}
}