package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Luiz Pereira
 *
 */
public class TipoProdutoTO implements Serializable, Jsonable {

	private static final long serialVersionUID = 1L;
	
	public TipoProdutoTO() {}
	
	public TipoProdutoTO(Integer idTipoProduto) {
		this.idTipoProduto = idTipoProduto;
	}

	private Integer idTipoProduto;
	private String nmTipoProduto;
	private Date dtCriacao;
	private String nmUsuarioCriacao;
	private String nmUsuarioAlteracao;
	private Date dtUltimaAlteracao;
	private String sgTipoProduto;
	private String utilizaChip;
	private TipoLinhaTO tipoLinhaTO;
	private List<AssociaRestricaoCompativelTO> associaRestricaoCompativelTOList; 
	
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

	public Integer getIdTipoProduto() {
		return idTipoProduto;
	}

	public void setIdTipoProduto(Integer idTipoProduto) {
		this.idTipoProduto = idTipoProduto;
	}

	public String getNmTipoProduto() {
		return nmTipoProduto;
	}

	public void setNmTipoProduto(String nmTipoProduto) {
		this.nmTipoProduto = nmTipoProduto;
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

	public String getSgTipoProduto() {
		return sgTipoProduto;
	}

	public void setSgTipoProduto(String sgTipoProduto) {
		this.sgTipoProduto = sgTipoProduto;
	}

	public TipoLinhaTO getTipoLinhaTO() {
		return tipoLinhaTO;
	}

	public void setTipoLinhaTO(TipoLinhaTO tipoLinhaTO) {
		this.tipoLinhaTO = tipoLinhaTO;
	}

	public String getUtilizaChip() {
		return utilizaChip;
	}

	public void setUtilizaChip(String utilizaChip) {
		this.utilizaChip = utilizaChip;
	}

	public List<AssociaRestricaoCompativelTO> getAssociaRestricaoCompativelTOList() {
		return associaRestricaoCompativelTOList;
	}

	public void setAssociaRestricaoCompativelTOList(
			List<AssociaRestricaoCompativelTO> associaRestricaoCompativelTOList) {
		this.associaRestricaoCompativelTOList = associaRestricaoCompativelTOList;
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("idTipoProduto", this.getIdTipoProduto());
		jsonObject.put("nmTipoProduto", this.getNmTipoProduto());
		return jsonObject;
	}
	
	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idTipoProduto: " + this.idTipoProduto, "nmTipoProduto: " + this.nmTipoProduto}, ", ");
	}
	
}