package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Luiz Pereira
 *
 */
public class SistemaProdutoTO implements Serializable, Jsonable {

	private static final long serialVersionUID = 1L;
	
	public SistemaProdutoTO() {}
	
	public SistemaProdutoTO(String cdCodigo) {
		this.cdCodigo = cdCodigo;
	}
	
	private Integer idSistemaProduto;
	private String cdCodigo;
	private Date dtCriacao;
	private Date dtUltimaAlteracao;
	private String nmUsuarioAlteracao;
	private String nmUsuarioCriacao;
	
	public String getCdCodigo() {
		return cdCodigo;
	}

	public void setCdCodigo(String cdCodigo) {
		this.cdCodigo = cdCodigo;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public Integer getIdSistemaProduto() {
		return idSistemaProduto;
	}

	public void setIdSistemaProduto(Integer idSistemaProduto) {
		this.idSistemaProduto = idSistemaProduto;
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

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idSistemaProduto: " + this.idSistemaProduto, "cdCodigo: " + this.cdCodigo}, ", ");
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("idSistemaProduto", this.getIdSistemaProduto());
		jsonObject.put("cdCodigo", this.getCdCodigo());
		return jsonObject;
	}
	
}