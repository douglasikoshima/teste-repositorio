package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class CorTO implements Serializable, Jsonable {

	private static final long serialVersionUID = 1L;
	
	private Integer idCor;
	private String rgb;
	private String nmCor;
	private Date dtCriacao;
    private String nmUsuarioCriacao;
	private Date dtAlteracao;
	private String nmUsuarioAlteracao;
	
	public CorTO() {
	}
	
	public CorTO(Integer idCor) {
		this.idCor = idCor;
	}
	
	public Date getDtAlteracao() {
		return dtAlteracao;
	}
	public void setDtAlteracao(Date dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}
	public Date getDtCriacao() {
		return dtCriacao;
	}
	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}
	public Integer getIdCor() {
		return idCor;
	}
	public void setIdCor(Integer idCor) {
		this.idCor = idCor;
	}
	public String getNmCor() {
		return nmCor;
	}
	public void setNmCor(String nmCor) {
		this.nmCor = nmCor;
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
	public String getRgb() {
		return rgb;
	}
	public void setRgb(String rgb) {
		this.rgb = rgb;
	}		
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("idCor", this.getIdCor());
		jsonObject.put("nmCor", this.getNmCor());
		return jsonObject;
	}
	
	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idCor: " + this.idCor, "nmCor: " + this.nmCor}, ", ");
	}
	
}