package com.indracompany.catalogo.to;

import org.json.JSONException;
import org.json.JSONObject;


public class RegraPrioridadeAltaTO {
	private Long idRegraPrioridadeAlta;
	private Long cdPrioridade;
	private String dsRegraAlta;
	private Long idTipoAlta;
	private String dsTipoAlta;
	
	public RegraPrioridadeAltaTO() {
		super();
	}
	public RegraPrioridadeAltaTO(Long idRegraPrioridadeAlta, Long cdPrioridade, String dsRegraAlta) {
		super();
		this.idRegraPrioridadeAlta = idRegraPrioridadeAlta;
		this.cdPrioridade = cdPrioridade;
		this.dsRegraAlta = dsRegraAlta;
	}
	public String toString(){
		JSONObject regraPrioridadeAltaJSONObject = new JSONObject();
		try {
			regraPrioridadeAltaJSONObject.put("idRegraPrioridadeAlta", idRegraPrioridadeAlta);
			regraPrioridadeAltaJSONObject.put("cdPrioridade", cdPrioridade);
			regraPrioridadeAltaJSONObject.put("dsRegraAlta", dsRegraAlta);
			regraPrioridadeAltaJSONObject.put("idTipoAlta", idTipoAlta);
			regraPrioridadeAltaJSONObject.put("dsTipoAlta", dsTipoAlta);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return regraPrioridadeAltaJSONObject.toString();
	}
	public Long getCdPrioridade() {
		return cdPrioridade;
	}
	public void setCdPrioridade(Long cdPrioridade) {
		this.cdPrioridade = cdPrioridade;
	}
	public String getDsRegraAlta() {
		return dsRegraAlta;
	}
	public void setDsRegraAlta(String dsRegraAlta) {
		this.dsRegraAlta = dsRegraAlta;
	}
	public String getDsTipoAlta() {
		return dsTipoAlta;
	}
	public void setDsTipoAlta(String dsTipoAlta) {
		this.dsTipoAlta = dsTipoAlta;
	}
	public Long getIdRegraPrioridadeAlta() {
		return idRegraPrioridadeAlta;
	}
	public void setIdRegraPrioridadeAlta(Long idRegraPrioridadeAlta) {
		this.idRegraPrioridadeAlta = idRegraPrioridadeAlta;
	}
	public Long getIdTipoAlta() {
		return idTipoAlta;
	}
	public void setIdTipoAlta(Long idTipoAlta) {
		this.idTipoAlta = idTipoAlta;
	}
}
