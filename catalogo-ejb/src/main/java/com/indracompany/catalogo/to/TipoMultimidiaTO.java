package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Luciano
 *
 */
public class TipoMultimidiaTO implements Serializable, Jsonable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idTipoMultimidia;
	private String nomeTipoMultimidia;
	private String siglaTipoMultimidia;
	private Date dataCriacao;
	private Date dataUltimaAlteracao;
	private String nomeUsuarioCriacao;
	private String nomeUsuarioUltimaAlteracao;
	
	public TipoMultimidiaTO() {
	}
	
	public TipoMultimidiaTO(Integer idTipoMultimidia) {
		this.idTipoMultimidia = idTipoMultimidia;
	}
	
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}
	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}
	public Integer getIdTipoMultimidia() {
		return idTipoMultimidia;
	}
	public void setIdTipoMultimidia(Integer idTipoMultimidia) {
		this.idTipoMultimidia = idTipoMultimidia;
	}
	public String getNomeUsuarioCriacao() {
		return nomeUsuarioCriacao;
	}
	public void setNomeUsuarioCriacao(String nomeUsuarioCriacao) {
		this.nomeUsuarioCriacao = nomeUsuarioCriacao;
	}
	public String getNomeUsuarioUltimaAlteracao() {
		return nomeUsuarioUltimaAlteracao;
	}
	public void setNomeUsuarioUltimaAlteracao(String nomeUsuarioUltimaAlteracao) {
		this.nomeUsuarioUltimaAlteracao = nomeUsuarioUltimaAlteracao;
	}
	public String getNomeTipoMultimidia() {
		return nomeTipoMultimidia;
	}
	public void setNomeTipoMultimidia(String nomeTipoMultimidia) {
		this.nomeTipoMultimidia = nomeTipoMultimidia;
	}
	public String getSiglaTipoMultimidia() {
		return siglaTipoMultimidia;
	}
	public void setSiglaTipoMultimidia(String siglaTipoMultimidia) {
		this.siglaTipoMultimidia = siglaTipoMultimidia;
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("idTipoMultimidia", this.getIdTipoMultimidia());
		jsonObject.put("nomeTipoMultimidia", this.getNomeTipoMultimidia());
		return jsonObject;
	}
	
}