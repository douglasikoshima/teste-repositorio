package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.indracompany.catalogo.util.JSONUtil;

/**
 * @author Luiz Pereira
 *
 */
public class TipoFrequenciaTO implements Serializable, Jsonable {

	private static final long serialVersionUID = 1L;
	
	public TipoFrequenciaTO() {}
	
	public TipoFrequenciaTO(Integer idTipoFrequencia) {
		this.idTipoFrequencia = idTipoFrequencia;
	}
	
	private Integer idTipoFrequencia;
	private String nmTipoFrequencia;
	private Date dtCriacao;
	private String nmUsuarioCriacao;
	private Date dtUltimaAlteracao;
	private String nmUsuarioAlteracao;
	private Integer qtFrequencia;
	private List<VlFrequenciaTO> vlFrequenciaTOList;

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

	public Integer getIdTipoFrequencia() {
		return idTipoFrequencia;
	}

	public void setIdTipoFrequencia(Integer idTipoFrequencia) {
		this.idTipoFrequencia = idTipoFrequencia;
	}

	public String getNmTipoFrequencia() {
		return nmTipoFrequencia;
	}

	public void setNmTipoFrequencia(String nmTipoFrequencia) {
		this.nmTipoFrequencia = nmTipoFrequencia;
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

	public Integer getQtFrequencia() {
		return qtFrequencia;
	}

	public void setQtFrequencia(Integer qtFrequencia) {
		this.qtFrequencia = qtFrequencia;
	}

	public List<VlFrequenciaTO> getVlFrequenciaTOList() {
		return vlFrequenciaTOList;
	}

	public void setVlFrequenciaTOList(List<VlFrequenciaTO> vlFrequenciaTOList) {
		this.vlFrequenciaTOList = vlFrequenciaTOList;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idTipoFrequencia: " + this.idTipoFrequencia, "nmTipoFrequencia: " + this.nmTipoFrequencia}, ", ");
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jsonTipoFrequencia = new JSONObject();
		jsonTipoFrequencia.put("idTipoFrequencia", this.getIdTipoFrequencia());
		jsonTipoFrequencia.put("nmTipoFrequencia", this.getNmTipoFrequencia());
		
		List<VlFrequenciaTO> vlFrequenciaTOList = this.getVlFrequenciaTOList();
		if (vlFrequenciaTOList != null && !vlFrequenciaTOList.isEmpty()) {
			JSONArray jsonVlFrequenciaList = JSONUtil.toJSONArray(vlFrequenciaTOList);
			jsonTipoFrequencia.put("vlFrequenciaList", jsonVlFrequenciaList);
		}
		
		return jsonTipoFrequencia;
	}
	
}