package com.indracompany.catalogo.to;

import java.io.Serializable;

public class TecnologiaTpFrequenciaVlTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idTecnologiaTpFrequenciaVl;
	private TecnologiaTpFrequenciaTO tecnologiaTpFrequenciaTO;
	private VlFrequenciaTO vlFrequenciaTO;
	
	public TecnologiaTpFrequenciaVlTO() {
	}
	
	public TecnologiaTpFrequenciaVlTO(Integer idTecnologiaTpFrequenciaVl) {
		this.idTecnologiaTpFrequenciaVl = idTecnologiaTpFrequenciaVl;
	}
	
	public TecnologiaTpFrequenciaVlTO(TecnologiaTpFrequenciaTO tecnologiaTpFrequenciaTO, VlFrequenciaTO vlFrequenciaTO) {
		this.tecnologiaTpFrequenciaTO = tecnologiaTpFrequenciaTO;
		this.vlFrequenciaTO = vlFrequenciaTO;
	}
	
	public Integer getIdTecnologiaTpFrequenciaVl() {
		return idTecnologiaTpFrequenciaVl;
	}
	
	public void setIdTecnologiaTpFrequenciaVl(Integer idTecnologiaTpFrequenciaVl) {
		this.idTecnologiaTpFrequenciaVl = idTecnologiaTpFrequenciaVl;
	}
	
	public TecnologiaTpFrequenciaTO getTecnologiaTpFrequenciaTO() {
		return tecnologiaTpFrequenciaTO;
	}
	
	public void setTecnologiaTpFrequenciaTO(TecnologiaTpFrequenciaTO tecnologiaTpFrequenciaTO) {
		this.tecnologiaTpFrequenciaTO = tecnologiaTpFrequenciaTO;
	}
	
	public VlFrequenciaTO getVlFrequenciaTO() {
		return vlFrequenciaTO;
	}
	
	public void setVlFrequenciaTO(VlFrequenciaTO vlFrequenciaTO) {
		this.vlFrequenciaTO = vlFrequenciaTO;
	}
	
}