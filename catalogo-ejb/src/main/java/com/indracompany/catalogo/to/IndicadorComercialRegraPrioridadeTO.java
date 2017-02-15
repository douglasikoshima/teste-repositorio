package com.indracompany.catalogo.to;

import org.json.JSONException;
import org.json.JSONObject;


public class IndicadorComercialRegraPrioridadeTO {
	private Long idIndicadorComercial;
	private String nmIndicadorComercial;
	private Long inTipoFiltro;
	private RegraPrioridadeAltaTO regraPrioridadeAltaTO;
	
	public IndicadorComercialRegraPrioridadeTO() {
		super();
	}
	public IndicadorComercialRegraPrioridadeTO(Long idIndicadorComercial) {
		super();
		this.idIndicadorComercial = idIndicadorComercial;
	}
	public IndicadorComercialRegraPrioridadeTO(String nmIndicadorComercial) {
		super();
		this.nmIndicadorComercial = nmIndicadorComercial;
	}
	public IndicadorComercialRegraPrioridadeTO(Long idIndicadorComercial, String nmIndicadorComercial) {
		super();
		this.idIndicadorComercial = idIndicadorComercial;
		this.nmIndicadorComercial = nmIndicadorComercial;
	}
	public IndicadorComercialRegraPrioridadeTO(Long idIndicadorComercial, String nmIndicadorComercial, RegraPrioridadeAltaTO regraPrioridadeAltaTO) {
		super();
		this.idIndicadorComercial = idIndicadorComercial;
		this.nmIndicadorComercial = nmIndicadorComercial;
		this.regraPrioridadeAltaTO = regraPrioridadeAltaTO;
	}
	public IndicadorComercialRegraPrioridadeTO(Long idIndicadorComercial, String nmIndicadorComercial, Long inTipoFiltro) {
		super();
		this.idIndicadorComercial = idIndicadorComercial;
		this.nmIndicadorComercial = nmIndicadorComercial;
		this.inTipoFiltro = inTipoFiltro;
	}
	public String toString(){
		JSONObject indicadorComercialJSON = new JSONObject();
		try {
			indicadorComercialJSON.put("idIndicadorComercial", idIndicadorComercial);
			indicadorComercialJSON.put("nmIndicadorComercial", nmIndicadorComercial);
			indicadorComercialJSON.put("inTipoFiltro", inTipoFiltro);
			indicadorComercialJSON.put("regraPrioridadeAltaTO", regraPrioridadeAltaTO);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return indicadorComercialJSON.toString();
	}
	public Long getIdIndicadorComercial() {
		return idIndicadorComercial;
	}
	public void setIdIndicadorComercial(Long idIndicadorComercial) {
		this.idIndicadorComercial = idIndicadorComercial;
	}
	public Long getInTipoFiltro() {
		return inTipoFiltro;
	}
	public void setInTipoFiltro(Long inTipoFiltro) {
		this.inTipoFiltro = inTipoFiltro;
	}
	public String getNmIndicadorComercial() {
		return nmIndicadorComercial;
	}
	public void setNmIndicadorComercial(String nmIndicadorComercial) {
		this.nmIndicadorComercial = nmIndicadorComercial;
	}
	public RegraPrioridadeAltaTO getRegraPrioridadeAltaTO() {
		return regraPrioridadeAltaTO;
	}
	public void setRegraPrioridadeAltaTO(RegraPrioridadeAltaTO regraPrioridadeAltaTO) {
		this.regraPrioridadeAltaTO = regraPrioridadeAltaTO;
	}
}
