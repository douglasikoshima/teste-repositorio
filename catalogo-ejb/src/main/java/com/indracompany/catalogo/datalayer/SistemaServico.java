package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the SISTEMASERVICO database table.
 * 
 */
@Entity
@Table(name = "SISTEMASERVICO", schema = "CATALOGOPRS_OW")
public class SistemaServico implements Serializable {
	private static final long serialVersionUID = 1L;

	public SistemaServico() {
	}

	@Id
	@Column(name = "IDSISTEMASERVICO")
	private Integer idSistemaServico;

	@Column(name = "CDCODIGO")
	private String cdCodigo;

	@Column(name = "CDSERVICETYPECODE")
	private String cdServiceTypeCode;

	@Temporal(TemporalType.DATE)
	@Column(name = "DTCRIACAO")
	private Date dtCriacao;

	@Temporal(TemporalType.DATE)
	@Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

	@Column(name = "INATRIBUTOESPECIAL")
	private String inAtributoEspecial;

	@Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

	// bi-directional many-to-one association to Matrizofertatipocontrato
	@OneToMany(mappedBy = "sistemaServico", fetch = FetchType.LAZY)
	private List<MatrizOfertaTipoContrato> matrizOfertaTipoContratoList;

	@OneToMany(mappedBy = "sistemaServico", fetch = FetchType.LAZY)
	private List<SolicitacaoComercial> solicitacaoComercialList;

	// bi-directional many-to-one association to Servicoatributo
	@OneToMany(mappedBy = "sistemaServico", fetch = FetchType.LAZY)
	private List<ServicoAtributo> servicoAtributoList;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SERVICOATRIBUTO", joinColumns = @JoinColumn(name = "IDSISTEMASERVICO"), inverseJoinColumns = @JoinColumn(name = "IDATRIBUTO"))
	private List<Atributo> atributoList;

	// bi-directional many-to-one association to Servico
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDSERVICO", nullable = false)
	private Servico servico;

	// bi-directional many-to-one association to Sistema
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDSISTEMA", nullable = false)
	private Sistema sistema;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDSISTEMASERVICO")
	private ComplementoLegado complementoLegado;

	@OneToMany()
	public List<Atributo> getAtributoList() {
		return atributoList;
	}

	public void setAtributoList(List<Atributo> atributoList) {
		this.atributoList = atributoList;
	}

	public String getCdCodigo() {
		return cdCodigo;
	}

	public void setCdCodigo(String cdCodigo) {
		this.cdCodigo = cdCodigo;
	}

	public String getCdServiceTypeCode() {
		return cdServiceTypeCode;
	}

	public void setCdServiceTypeCode(String cdServiceTypeCode) {
		this.cdServiceTypeCode = cdServiceTypeCode;
	}

	public ComplementoLegado getComplementoLegado() {
		return complementoLegado;
	}

	public void setComplementoLegado(ComplementoLegado complementoLegado) {
		this.complementoLegado = complementoLegado;
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

	public Integer getIdSistemaServico() {
		return idSistemaServico;
	}

	public void setIdSistemaServico(Integer idSistemaServico) {
		this.idSistemaServico = idSistemaServico;
	}

	public String getInAtributoEspecial() {
		return inAtributoEspecial;
	}

	public void setInAtributoEspecial(String inAtributoEspecial) {
		this.inAtributoEspecial = inAtributoEspecial;
	}

	public List<MatrizOfertaTipoContrato> getMatrizOfertaTipoContratoList() {
		return matrizOfertaTipoContratoList;
	}

	public List<SolicitacaoComercial> getSolicitacaoComercialList() {
		return solicitacaoComercialList;
	}

	public void setSolicitacaoComercialList(
			List<SolicitacaoComercial> solicitacaoComercialList) {
		this.solicitacaoComercialList = solicitacaoComercialList;
	}

	public void setMatrizOfertaTipoContratoList(
			List<MatrizOfertaTipoContrato> matrizOfertaTipoContratoList) {
		this.matrizOfertaTipoContratoList = matrizOfertaTipoContratoList;
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

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public List<ServicoAtributo> getServicoAtributoList() {
		return servicoAtributoList;
	}

	public void setServicoAtributoList(List<ServicoAtributo> servicoAtributoList) {
		this.servicoAtributoList = servicoAtributoList;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

}