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
public class FabricanteTO implements Serializable, Jsonable {

	private static final long serialVersionUID = 1L;
	
	public FabricanteTO() {}
	
	public FabricanteTO(Integer idFabricante) {
		this.idFabricante = idFabricante;
	}
	
	private Integer idFabricante;
	private String nmFabricante;
	private Date dtCriacao;
	private String nmUsuarioCriacao;
	private Date dtUltimaAlteracao;
	private Integer idTipoProduto;
	
	public Integer getIdTipoProduto() {
		return idTipoProduto;
	}

	public void setIdTipoProduto(Integer idTipoProduto) {
		this.idTipoProduto = idTipoProduto;
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

	public Integer getIdFabricante() {
		return idFabricante;
	}

	public void setIdFabricante(Integer idFabricante) {
		this.idFabricante = idFabricante;
	}

	public String getNmFabricante() {
		return nmFabricante;
	}

	public void setNmFabricante(String nmFabricante) {
		this.nmFabricante = nmFabricante;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idFabricante: " + this.idFabricante, "nmFabricante: " + this.nmFabricante}, ", ");
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("idFabricante", this.getIdFabricante());
		jsonObject.put("nmFabricante", this.getNmFabricante());
		return jsonObject;
	}
	
}