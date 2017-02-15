package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="OFERTASAP", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "OFERTASAP_SQ", sequenceName = "CATALOGOPRS_OW.OFERTASAP_SQ", allocationSize = 1)
@NamedQuery( name = "OfertaSAP.findAll", query = "SELECT os FROM OfertaSAP os WHERE os.inDisponivel = 'S' ORDER BY os.cdOfertaSAP ")
public class OfertaSAP implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public OfertaSAP() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OFERTASAP_SQ")
	@Column(name = "IDOFERTASAP")
	private Integer idOfertaSAP;

	@Column(name = "CDOFERTASAP")
	private String cdOfertaSAP;

	@Column(name = "DSCOFERTASAP")
	private String dscOfertaSAP;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "INDISPONIVEL")
	private String inDisponivel;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

    @Column(name = "SGUTILIZACAO")
	private String sgUtilizacao;

	//bi-directional many-to-one association to Matrizofertaitem
	@OneToMany(mappedBy="ofertaSAP")
	private List<MatrizOfertaItem> matrizOfertaItemList;

	//bi-directional many-to-one association to Ofertasapplanooferta
	@OneToMany(mappedBy="ofertaSAP")
	private List<OfertaSAPPlanoOferta> ofertaSAPPlanoOfertaList;

	public String getCdOfertaSAP() {
		return cdOfertaSAP;
	}

	public void setCdOfertaSAP(String cdOfertaSAP) {
		this.cdOfertaSAP = cdOfertaSAP;
	}

	public String getDscOfertaSAP() {
		return dscOfertaSAP;
	}

	public void setDscOfertaSAP(String dscOfertaSAP) {
		this.dscOfertaSAP = dscOfertaSAP;
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

	public Integer getIdOfertaSAP() {
		return idOfertaSAP;
	}

	public void setIdOfertaSAP(Integer idOfertaSAP) {
		this.idOfertaSAP = idOfertaSAP;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public List<MatrizOfertaItem> getMatrizOfertaItemList() {
		return matrizOfertaItemList;
	}

	public void setMatrizOfertaItemList(List<MatrizOfertaItem> matrizOfertaItemList) {
		this.matrizOfertaItemList = matrizOfertaItemList;
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

	public String getSgUtilizacao() {
		return sgUtilizacao;
	}

	public void setSgUtilizacao(String sgUtilizacao) {
		this.sgUtilizacao = sgUtilizacao;
	}
}