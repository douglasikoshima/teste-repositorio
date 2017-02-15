package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class ValorCaracteristicaTO implements Serializable, Jsonable {
	
	public ValorCaracteristicaTO() {}
	
	public ValorCaracteristicaTO(Integer idValorCaracteristica) {
		this.idValorCaracteristica = idValorCaracteristica;
	}
	
	private static final long serialVersionUID = 1L;
	private Integer idValorCaracteristica;
	private Date dtCriacao;
	private Date dtUltimaAlteracao;
	private String nmUsuarioAlteracao;
	private String nmUsuarioCriacao;
	private String valor;
	private CaracteristicaTO caracteristicaTO;
	private Boolean existGrupoProdutoCaracteristica;
	
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
	public Integer getIdValorCaracteristica() {
		return idValorCaracteristica;
	}
	public void setIdValorCaracteristica(Integer idValorCaracteristica) {
		this.idValorCaracteristica = idValorCaracteristica;
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
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public CaracteristicaTO getCaracteristicaTO() {
		return caracteristicaTO;
	}
	public void setCaracteristicaTO(CaracteristicaTO caracteristicaTO) {
		this.caracteristicaTO = caracteristicaTO;
	}
	
	public Boolean getExistGrupoProdutoCaracteristica() {
		return existGrupoProdutoCaracteristica;
	}
	public void setExistGrupoProdutoCaracteristica(
			Boolean existGrupoProdutoCaracteristica) {
		this.existGrupoProdutoCaracteristica = existGrupoProdutoCaracteristica;
	}
	
	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idValorCaracteristica: " + this.idValorCaracteristica, "valor: " + this.valor}, ", ");
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("idValorCaracteristica", this.getIdValorCaracteristica());
		jsonObject.put("valor", this.getValor());
		return jsonObject;
	}
	
}