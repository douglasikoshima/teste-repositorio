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
@Table(name="FABRICANTESISTEMA", schema = "CATALOGOPRS_OW")
public class FabricanteSistema implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public FabricanteSistema() {}
	
	@Id
	@Column(name = "IDFABRICANTESISTEMA")
	private Integer idFabricanteSistema;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

    @Column(name = "SGFABRICANTE")
	private String sgFabricante;

	//bi-directional many-to-one association to Fabricante
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDFABRICANTE", nullable=false)
	private Fabricante fabricante;

	//bi-directional many-to-one association to Sistema
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDSISTEMA", nullable=false)
	private Sistema sistema;

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

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public Integer getIdFabricanteSistema() {
		return idFabricanteSistema;
	}

	public void setIdFabricanteSistema(Integer idFabricanteSistema) {
		this.idFabricanteSistema = idFabricanteSistema;
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

	public String getSgFabricante() {
		return sgFabricante;
	}

	public void setSgFabricante(String sgFabricante) {
		this.sgFabricante = sgFabricante;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

    
}