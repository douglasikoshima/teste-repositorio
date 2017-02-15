package com.indracompany.catalogo.to;

import java.io.Serializable;

public class TipoEncargoTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7244676836060036924L;
	
	private Long idTipoEncargo;
	private String cdTipoEncargo;
	private String dsTipoEncargo;
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
