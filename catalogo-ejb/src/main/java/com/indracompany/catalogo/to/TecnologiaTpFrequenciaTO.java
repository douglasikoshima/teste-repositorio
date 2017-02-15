package com.indracompany.catalogo.to;

import java.io.Serializable;

public class TecnologiaTpFrequenciaTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idTecnologiaTpFrequencia;
	private TecnologiaTO tecnologiaTO;
	private TipoFrequenciaTO tipoFrequenciaTO;
	
	public TecnologiaTpFrequenciaTO() {
	}
	
	public TecnologiaTpFrequenciaTO(Integer idTecnologiaTpFrequencia) {
		this.idTecnologiaTpFrequencia = idTecnologiaTpFrequencia;
	}
	
	public TecnologiaTpFrequenciaTO(TecnologiaTO tecnologiaTO, TipoFrequenciaTO tipoFrequenciaTO) {
		this.tecnologiaTO = tecnologiaTO;
		this.tipoFrequenciaTO = tipoFrequenciaTO;
	}
	
	public Integer getIdTecnologiaTpFrequencia() {
		return idTecnologiaTpFrequencia;
	}
	
	public void setIdTecnologiaTpFrequencia(Integer idTecnologiaTpFrequencia) {
		this.idTecnologiaTpFrequencia = idTecnologiaTpFrequencia;
	}
	
	public TecnologiaTO getTecnologiaTO() {
		return tecnologiaTO;
	}
	
	public void setTecnologiaTO(TecnologiaTO tecnologiaTO) {
		this.tecnologiaTO = tecnologiaTO;
	}
	
	public TipoFrequenciaTO getTipoFrequenciaTO() {
		return tipoFrequenciaTO;
	}
	
	public void setTipoFrequenciaTO(TipoFrequenciaTO tipoFrequenciaTO) {
		this.tipoFrequenciaTO = tipoFrequenciaTO;
	}
	
}