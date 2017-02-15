package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author gmuniz
 *
 */
public class GamaTO implements Serializable, Jsonable {
	
	private static final long serialVersionUID = 1L;
	
	public GamaTO() {
	}
	
	private Integer idGama;
	private String nmGama;
	private String sgGama;
	private String nmUsuarioCriacao;
	private Date dtCriacao;
	private String nmUsuarioAlteracao;
	private Date dtUltimaAlteracao;
	
	public Integer getIdGama() {
		return idGama;
	}
	
	public void setIdGama(Integer idGama) {
		this.idGama = idGama;
	}
	
	public String getNmGama() {
		return nmGama;
	}
	
	public void setNmGama(String nmGama) {
		this.nmGama = nmGama;
	}
	
	public String getSgGama() {
		return sgGama;
	}
	
	public void setSgGama(String sgGama) {
		this.sgGama = sgGama;
	}
	
	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}
	
	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}
	
	public Date getDtCriacao() {
		return dtCriacao;
	}
	
	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}
	
	public String getNmUsuarioAlteracao() {
		return nmUsuarioAlteracao;
	}
	
	public void setNmUsuarioAlteracao(String nmUsuarioAlteracao) {
		this.nmUsuarioAlteracao = nmUsuarioAlteracao;
	}
	
	public Date getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}
	
	public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("idGama", this.getIdGama());
		jsonObject.put("nmGama", this.getNmGama());
		return jsonObject;
	}
	
}