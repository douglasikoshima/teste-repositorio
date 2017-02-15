package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TIPOENCARGO", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "TIPOENCARGO_SQ", sequenceName = "CATALOGOPRS_OW.TIPOENCARGO_SQ", allocationSize = 1)
@NamedQuery(name = "TipoEncargo.findAll", query = "select tpoenc from TipoEncargo tpoenc")
public class TipoEncargo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6567721455497249127L;

	@Id
	@Column(name = "IDTIPOENCARGO")
	private Long idTipoEncargo;
	
	@Column(name = "CDTIPOENCARGO")
	private String cdTipoEncargo;

	@Column(name = "DSTIPOENCARGO")
	private String dsTipoEncargo;

	@Column(name = "NMTIPOENCARGO")
	private String nmTipoEncargo;

	public String getCdTipoEncargo() {
		return cdTipoEncargo;
	}

	public void setCdTipoEncargo(String cdTipoEncargo) {
		this.cdTipoEncargo = cdTipoEncargo;
	}

	public String getDsTipoEncargo() {
		return dsTipoEncargo;
	}

	public void setDsTipoEncargo(String dsTipoEncargo) {
		this.dsTipoEncargo = dsTipoEncargo;
	}

	public Long getIdTipoEncargo() {
		return idTipoEncargo;
	}

	public void setIdTipoEncargo(Long idTipoEncargo) {
		this.idTipoEncargo = idTipoEncargo;
	}

	public String getNmTipoEncargo() {
		return nmTipoEncargo;
	}

	public void setNmTipoEncargo(String nmTipoEncargo) {
		this.nmTipoEncargo = nmTipoEncargo;
	}
}
