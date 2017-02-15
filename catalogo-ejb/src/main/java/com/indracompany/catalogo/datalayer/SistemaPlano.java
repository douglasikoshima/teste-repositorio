package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="SISTEMAPLANO", schema = "CATALOGOPRS_OW")
public class SistemaPlano implements Serializable {
	private static final long serialVersionUID = 1L;
	
	 public SistemaPlano() {}
	
	@Id
	@Column(name = "IDSISTEMAPLANO")
	private Integer idSistemaPlano;

	@Column(name = "CDCODIGO")
	private String cdCodigo;

	@Column(name = "CDCODIGOMASTER")
	private String cdCodigoMaster;

	@Column(name = "CDSERVICETYPECODE")
	private String cdServiceTypeCode;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

	//bi-directional many-to-one association to Sistema
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPLANO", nullable=false)
	private Plano plano;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

	//bi-directional many-to-one association to Matrizofertatipocontrato
	@OneToMany(mappedBy="sistemaPlano")
	private List<MatrizOfertaTipoContrato> matrizOfertaTipoContratoList;

	//bi-directional many-to-one association to Ofertasapplanooferta
	@OneToMany(mappedBy="sistemaPlano")
	private List<OfertaSAPPlanoOferta> ofertaSAPPlanoOfertaList;

	//bi-directional many-to-one association to Sistema
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDSISTEMA", nullable=false)
	private Sistema sistema;

	public String getCdCodigo() {
		return cdCodigo;
	}

	public void setCdCodigo(String cdCodigo) {
		this.cdCodigo = cdCodigo;
	}

	public String getCdCodigoMaster() {
		return cdCodigoMaster;
	}

	public void setCdCodigoMaster(String cdCodigoMaster) {
		this.cdCodigoMaster = cdCodigoMaster;
	}

	public String getCdServiceTypeCode() {
		return cdServiceTypeCode;
	}

	public void setCdServiceTypeCode(String cdServiceTypeCode) {
		this.cdServiceTypeCode = cdServiceTypeCode;
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

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public Integer getIdSistemaPlano() {
		return idSistemaPlano;
	}

	public void setIdSistemaPlano(Integer idSistemaPlano) {
		this.idSistemaPlano = idSistemaPlano;
	}

	public List<MatrizOfertaTipoContrato> getMatrizOfertaTipoContratoList() {
		return matrizOfertaTipoContratoList;
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

	public List<OfertaSAPPlanoOferta> getOfertaSAPPlanoOfertaList() {
		return ofertaSAPPlanoOfertaList;
	}

	public void setOfertaSAPPlanoOfertaList(
			List<OfertaSAPPlanoOferta> ofertaSAPPlanoOfertaList) {
		this.ofertaSAPPlanoOfertaList = ofertaSAPPlanoOfertaList;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}
}