package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class GrupoProdutoTO implements Serializable, Jsonable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1491147850279929445L;
	private Integer idGrupoProduto;
	private String nmGrupoProduto;
	private String inDisponivel;
	private Date dtCriacao;
	private String nmUsuarioCriacao;
	private Date dtUltimaAlteracao;
	private String nmUsuarioAlteracao;
	private String inFimVida;
	private String dsNota;
	private String url;
	private CorTO corTO;
	private Integer qtProdutoGrupoProduto;
	private FabricanteTO fabricanteTO;
	private TipoProdutoTO tipoProdutoTO;
	private List<ModeloTipoProdutoRestricaoTO> modeloTipoProdutoRestricaoTOList;
	private List<TecnologiaTO> tecnologiaTOList;
	private List<CaracteristicaTO> caracteristicaTOList;
	private List<ValorCaracteristicaTO> valorCaracteristicaTOList;
	private List<MultimidiaTO> multimidiaTOList;
	private List<CorTO> cores;
	
	public GrupoProdutoTO() {}
	
	public GrupoProdutoTO(Integer idGrupoProduto) {
		this.idGrupoProduto = idGrupoProduto;
	}
	
	public List<CaracteristicaTO> getCaracteristicaTOList() {
		return caracteristicaTOList;
	}

	public void setCaracteristicaTOList(List<CaracteristicaTO> caracteristicaTOList) {
		this.caracteristicaTOList = caracteristicaTOList;
	}

	public List<TecnologiaTO> getTecnologiaTOList() {
		return tecnologiaTOList;
	}

	public void setTecnologiaTOList(List<TecnologiaTO> tecnologiaTOList) {
		this.tecnologiaTOList = tecnologiaTOList;
	}

	public CorTO getCorTO() {
		return corTO;
	}

	public void setCorTO(CorTO corTO) {
		this.corTO = corTO;
	}

	public String getDsNota() {
		return dsNota;
	}

	public void setDsNota(String dsNota) {
		this.dsNota = dsNota;
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

	public Integer getIdGrupoProduto() {
		return idGrupoProduto;
	}

	public void setIdGrupoProduto(Integer idGrupoProduto) {
		this.idGrupoProduto = idGrupoProduto;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public String getInFimVida() {
		return inFimVida;
	}

	public void setInFimVida(String inFimVida) {
		this.inFimVida = inFimVida;
	}

	public String getNmGrupoProduto() {
		return nmGrupoProduto;
	}

	public void setNmGrupoProduto(String nmGrupoProduto) {
		this.nmGrupoProduto = nmGrupoProduto;
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
	
	public Integer getQtProdutoGrupoProduto() {
		return qtProdutoGrupoProduto;
	}

	public void setQtProdutoGrupoProduto(Integer qtProdutoGrupoProduto) {
		this.qtProdutoGrupoProduto = qtProdutoGrupoProduto;
	}

	public FabricanteTO getFabricanteTO() {
		return fabricanteTO;
	}

	public void setFabricanteTO(FabricanteTO fabricanteTO) {
		this.fabricanteTO = fabricanteTO;
	}

	public TipoProdutoTO getTipoProdutoTO() {
		return tipoProdutoTO;
	}

	public void setTipoProdutoTO(TipoProdutoTO tipoProdutoTO) {
		this.tipoProdutoTO = tipoProdutoTO;
	}

	public List<ModeloTipoProdutoRestricaoTO> getModeloTipoProdutoRestricaoTOList() {
		return modeloTipoProdutoRestricaoTOList;
	}

	public void setModeloTipoProdutoRestricaoTOList(
			List<ModeloTipoProdutoRestricaoTO> modeloTipoProdutoRestricaoTOList) {
		this.modeloTipoProdutoRestricaoTOList = modeloTipoProdutoRestricaoTOList;
	}

	public List<CorTO> getCores() {
		return cores;
	}

	public void setCores(List<CorTO> cores) {
		this.cores = cores;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<ValorCaracteristicaTO> getValorCaracteristicaTOList() {
		return valorCaracteristicaTOList;
	}

	public void setValorCaracteristicaTOList(
			List<ValorCaracteristicaTO> valorCaracteristicaTOList) {
		this.valorCaracteristicaTOList = valorCaracteristicaTOList;
	}

	public List<MultimidiaTO> getMultimidiaTOList() {
		return multimidiaTOList;
	}

	public void setMultimidiaTOList(List<MultimidiaTO> multimidiaTOList) {
		this.multimidiaTOList = multimidiaTOList;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idGrupoProduto: " + this.idGrupoProduto, "nmGrupoProduto: " + this.nmGrupoProduto}, ", ");
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("idGrupoProduto", this.getIdGrupoProduto());
		jsonObject.put("nmGrupoProduto", this.getNmGrupoProduto());
		jsonObject.put("dsNota", this.getDsNota());
		jsonObject.put("inFimVida", this.getInFimVida());
		jsonObject.put("inDisponivel", this.getInDisponivel());
		
		TipoProdutoTO tipoProdutoTO = this.getTipoProdutoTO();
		if (tipoProdutoTO != null) {
			jsonObject.put("tipoProduto", tipoProdutoTO.toJSONObject());
		}
		
		FabricanteTO fabricanteTO = this.getFabricanteTO();
		if (fabricanteTO != null) {
			jsonObject.put("fabricante", fabricanteTO.toJSONObject());
		}
		
		CorTO corTO = this.getCorTO();
		if (corTO != null) {
			jsonObject.put("cor", corTO.toJSONObject());
		}
		
		return jsonObject;
	}
	
}