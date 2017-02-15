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
public class TecnologiaTO implements Serializable, Jsonable {

	private static final long serialVersionUID = 1L;
	
	public TecnologiaTO() {}
	
	public TecnologiaTO(Integer idTecnologia) {
		this.idTecnologia = idTecnologia;
	}

	private Integer idTecnologia;
	private Date dtCriacao;
	private Date dtUltimaAlteracao;
	private String nmTecnologia;
	private String nmUsuarioAlteracao;
	private String nmUsuarioCriacao;
	private String sgTecnologia;
	private List<TipoFrequenciaTO> tipoFrequenciaTOList;

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

	public Integer getIdTecnologia() {
		return idTecnologia;
	}

	public void setIdTecnologia(Integer idTecnologia) {
		this.idTecnologia = idTecnologia;
	}

	public String getNmTecnologia() {
		return nmTecnologia;
	}

	public void setNmTecnologia(String nmTecnologia) {
		this.nmTecnologia = nmTecnologia;
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

	public String getSgTecnologia() {
		return sgTecnologia;
	}

	public void setSgTecnologia(String sgTecnologia) {
		this.sgTecnologia = sgTecnologia;
	}

	public List<TipoFrequenciaTO> getTipoFrequenciaTOList() {
		return tipoFrequenciaTOList;
	}

	public void setTipoFrequenciaTOList(List<TipoFrequenciaTO> tipoFrequenciaTOList) {
		this.tipoFrequenciaTOList = tipoFrequenciaTOList;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idTecnologia: " + this.idTecnologia, "nmTecnologia: " + this.nmTecnologia}, ", ");
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("idTecnologia", this.getIdTecnologia());
		jsonObject.put("nmTecnologia", this.getNmTecnologia());
		return jsonObject;
	}
	
}