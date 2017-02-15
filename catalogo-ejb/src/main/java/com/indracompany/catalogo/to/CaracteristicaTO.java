package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.indracompany.catalogo.util.JSONUtil;

public class CaracteristicaTO implements Serializable, Jsonable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1322671280630515964L;
	private Integer idCaracteristica;
	private String descricao;
	private Date dtCriacao;
	private Date dtUltimaAlteracao;
	private String indComparavel;
	private String indExibivel;
	private String inDisponivel;
	private String inSimulador;
	private String nmCaracteristica;
	private String nmUsuarioAlteracao;
	private String nmUsuarioCriacao;
	private GrupoCaracteristicaTO grupoCaracteristicaTO;
	private Boolean existGrupoProdutoCaracteristica;
	private ValorCaracteristicaTO valorCaracteristicaTO;
	private List<ValorCaracteristicaTO> dominioValoresCaracteristica; 

	public CaracteristicaTO() {}
	
	public CaracteristicaTO(Integer idCaracteristica) {
		this.idCaracteristica = idCaracteristica;
	}
	
	public ValorCaracteristicaTO getValorCaracteristicaTO() {
		return valorCaracteristicaTO;
	}

	public void setValorCaracteristicaTO(ValorCaracteristicaTO valorCaracteristicaTO) {
		this.valorCaracteristicaTO = valorCaracteristicaTO;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
	public Integer getIdCaracteristica() {
		return idCaracteristica;
	}
	public void setIdCaracteristica(Integer idCaracteristica) {
		this.idCaracteristica = idCaracteristica;
	}
	public String getIndComparavel() {
		return indComparavel;
	}
	public void setIndComparavel(String indComparavel) {
		this.indComparavel = indComparavel;
	}
	public String getIndExibivel() {
		return indExibivel;
	}
	public void setIndExibivel(String indExibivel) {
		this.indExibivel = indExibivel;
	}
	public String getInDisponivel() {
		return inDisponivel;
	}
	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}
	public String getInSimulador() {
		return inSimulador;
	}
	public void setInSimulador(String inSimulador) {
		this.inSimulador = inSimulador;
	}
	public String getNmCaracteristica() {
		return nmCaracteristica;
	}
	public void setNmCaracteristica(String nmCaracteristica) {
		this.nmCaracteristica = nmCaracteristica;
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
	public GrupoCaracteristicaTO getGrupoCaracteristicaTO() {
		return grupoCaracteristicaTO;
	}
	public void setGrupoCaracteristicaTO(GrupoCaracteristicaTO grupoCaracteristicaTO) {
		this.grupoCaracteristicaTO = grupoCaracteristicaTO;
	}
	
	public Boolean getExistGrupoProdutoCaracteristica() {
		return existGrupoProdutoCaracteristica;
	}

	public void setExistGrupoProdutoCaracteristica(
			Boolean existGrupoProdutoCaracteristica) {
		this.existGrupoProdutoCaracteristica = existGrupoProdutoCaracteristica;
	}

	public List<ValorCaracteristicaTO> getDominioValoresCaracteristica() {
		return dominioValoresCaracteristica;
	}

	public void setDominioValoresCaracteristica(
			List<ValorCaracteristicaTO> dominioValoresCaracteristica) {
		this.dominioValoresCaracteristica = dominioValoresCaracteristica;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idCaracteristica: " + this.idCaracteristica, "nmCaracteristica: " + this.nmCaracteristica}, ", ");
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jsonCaracteristica = new JSONObject();
		jsonCaracteristica.put("idCaracteristica", this.getIdCaracteristica());
		jsonCaracteristica.put("nmCaracteristica", this.getNmCaracteristica());
		
		List<ValorCaracteristicaTO> valorCaracteristicaTOList = this.getDominioValoresCaracteristica();
		if (valorCaracteristicaTOList != null && !valorCaracteristicaTOList.isEmpty()) {
			JSONArray jsonValorCaracteristicaList = JSONUtil.toJSONArray(valorCaracteristicaTOList);
			jsonCaracteristica.put("valorCaracteristicaList", jsonValorCaracteristicaList);
		}
		
		return jsonCaracteristica;
	}
	
}