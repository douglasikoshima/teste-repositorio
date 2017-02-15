package com.indracompany.catalogo.to;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author gmuniz
 *
 */
public class VlFrequenciaTO implements Serializable, Jsonable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idVlFrequencia;
	private String vlFrequencia;
	
	public VlFrequenciaTO() {
	}
	public VlFrequenciaTO(Integer idVlFrequencia) {
		this.idVlFrequencia = idVlFrequencia;
	}

	public Integer getIdVlFrequencia() {
		return idVlFrequencia;
	}
	public void setIdVlFrequencia(Integer idVlFrequencia) {
		this.idVlFrequencia = idVlFrequencia;
	}
	public String getVlFrequencia() {
		return vlFrequencia;
	}
	public void setVlFrequencia(String vlFrequencia) {
		this.vlFrequencia = vlFrequencia;
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("idVlFrequencia", this.getIdVlFrequencia());
		jsonObject.put("vlFrequencia", this.getVlFrequencia());
		return jsonObject;
	}
	
}