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
public class MultimidiaTO implements Serializable, Jsonable {

	private static final long serialVersionUID = 1L;
	
	private CorTO corTO;
	private Date dataCriacao;
	private Date dataUltimaAlteracao;
	private GrupoProdutoTO grupoProdutoTO;
	private Integer idMultimidia;
	private String nomeMultimidia;
	private String nomeUsuarioCriacao;
	private String nomeUsuarioUltimaAlteracao;
	private TipoMultimidiaTO tipoMultimidiaTO;
	private ClassificacaoMultimidiaTO classificacaoTO;
	
	public CorTO getCorTO() {
		return corTO;
	}
	public void setCorTO(CorTO corTO) {
		this.corTO = corTO;
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
	public GrupoProdutoTO getGrupoProdutoTO() {
		return grupoProdutoTO;
	}
	public void setGrupoProdutoTO(GrupoProdutoTO grupoProdutoTO) {
		this.grupoProdutoTO = grupoProdutoTO;
	}
	public Integer getIdMultimidia() {
		return idMultimidia;
	}
	public void setIdMultimidia(Integer idMultimidia) {
		this.idMultimidia = idMultimidia;
	}
	public String getNomeMultimidia() {
		return nomeMultimidia;
	}
	public void setNomeMultimidia(String nomeMultimidia) {
		this.nomeMultimidia = nomeMultimidia;
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
	public TipoMultimidiaTO getTipoMultimidiaTO() {
		return tipoMultimidiaTO;
	}
	public void setTipoMultimidiaTO(TipoMultimidiaTO tipoMultimidiaTO) {
		this.tipoMultimidiaTO = tipoMultimidiaTO;
	}
	public ClassificacaoMultimidiaTO getClassificacaoTO() {
		return classificacaoTO;
	}
	public void setClassificacaoTO(ClassificacaoMultimidiaTO classificacaoTO) {
		this.classificacaoTO = classificacaoTO;
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("idMultimidia", this.getIdMultimidia());
		jsonObject.put("nomeMultimidia", this.getNomeMultimidia());
		
		TipoMultimidiaTO tipoMultimidiaTO = this.getTipoMultimidiaTO();
		if (tipoMultimidiaTO != null) {
			jsonObject.put("tipoMultimidia", tipoMultimidiaTO.toJSONObject());
		}
		
		ClassificacaoMultimidiaTO classificacaoTO = this.getClassificacaoTO();
		if (classificacaoTO != null) {
			jsonObject.put("classificacao", classificacaoTO.toJSONObject());
		}
		
		CorTO corTO = this.getCorTO();
		if (corTO != null) {
			jsonObject.put("cor", corTO.toJSONObject());
		}
		
		return jsonObject;
	}
	
}