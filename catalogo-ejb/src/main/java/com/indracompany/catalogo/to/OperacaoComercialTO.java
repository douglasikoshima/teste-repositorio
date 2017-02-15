package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.json.JSONException;
import org.json.JSONObject;

public class OperacaoComercialTO implements Jsonable, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 285228223489498995L;
	private Long idOperacaoComercial;
	private String cdOperacaoComercial;
	private String nmOperacaoComercial;
	private String dsOperacaoComercial;
	
	public OperacaoComercialTO() {}	
	
	
	public String getCdOperacaoComercial() {
		return cdOperacaoComercial;
	}

	public void setCdOperacaoComercial(String cdOperacaoComercial) {
		this.cdOperacaoComercial = cdOperacaoComercial;
	}

	public String getDsOperacaoComercial() {
		return dsOperacaoComercial;
	}

	public void setDsOperacaoComercial(String dsOperacaoComercial) {
		this.dsOperacaoComercial = dsOperacaoComercial;
	}

	public String getNmOperacaoComercial() {
		return nmOperacaoComercial;
	}

	public void setNmOperacaoComercial(String nmOperacaoComercial) {
		this.nmOperacaoComercial = nmOperacaoComercial;
	}
	
	public OperacaoComercialTO(Long id){
		idOperacaoComercial = id;
	}
	
	public Long getIdOperacaoComercial() {
		return idOperacaoComercial;
	}

	public void setIdOperacaoComercial(Long idOperacaoComercial) {
		this.idOperacaoComercial = idOperacaoComercial;
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject operacaoComercialJson = new JSONObject();
		for(Field field: OperacaoComercialTO.class.getDeclaredFields()){
			try {
				operacaoComercialJson.put(field.getName(), field.get(this)) ;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return operacaoComercialJson;
	}	
}