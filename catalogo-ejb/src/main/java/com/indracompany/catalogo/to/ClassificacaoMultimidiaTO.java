package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author gmuniz
 *
 */
public class ClassificacaoMultimidiaTO implements Serializable, Jsonable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idClassificacao;
	private String nomeClassificacao;
	private String siglaClassificacao;
	private Date dataCriacao;
	private Date dataUltimaAlteracao;
	private String nomeUsuarioCriacao;
	private String nomeUsuarioUltimaAlteracao;
	
	public ClassificacaoMultimidiaTO() {
	}
	
	public ClassificacaoMultimidiaTO(Integer idClassificacao) {
		this.idClassificacao = idClassificacao;
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
	public Integer getIdClassificacao() {
		return idClassificacao;
	}
	public void setIdClassificacao(Integer idClassificacao) {
		this.idClassificacao = idClassificacao;
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
	public String getNomeClassificacao() {
		return nomeClassificacao;
	}
	public void setNomeClassificacao(String nomeClassificacao) {
		this.nomeClassificacao = nomeClassificacao;
	}
	public String getSiglaClassificacao() {
		return siglaClassificacao;
	}
	public void setSiglaClassificacao(String siglaClassificacao) {
		this.siglaClassificacao = siglaClassificacao;
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("idClassificacao", this.getIdClassificacao());
		jsonObject.put("nomeClassificacao", this.getNomeClassificacao());
		return jsonObject;
	}
	
}