package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class GrupoCaracteristicaTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public GrupoCaracteristicaTO() {}
	
	public GrupoCaracteristicaTO(Integer idGrupoCaracteristica) {
		this.idGrupoCaracteristica = idGrupoCaracteristica;
	}
	
	private Integer idGrupoCaracteristica;
	private String nmGrupoCaracteristica;	
	private String iconeGrupoCaracteristica;
	private Date dtCriacao;
	private Date dtAlteracao;
	private String nmUsuarioCriacao;
	private String nmUsuarioAlteracao;
	private List<CaracteristicaTO> caracteristicaList;
	private Boolean existCaracteristicaAssociada;
	
	public Date getDtAlteracao() {
		return dtAlteracao;
	}
	public void setDtAlteracao(Date dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}
	public Date getDtCriacao() {
		return dtCriacao;
	}
	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}
	public String getIconeGrupoCaracteristica() {
		return iconeGrupoCaracteristica;
	}
	public void setIconeGrupoCaracteristica(String iconeGrupoCaracteristica) {
		this.iconeGrupoCaracteristica = iconeGrupoCaracteristica;
	}

	public Integer getIdGrupoCaracteristica() {
		return idGrupoCaracteristica;
	}
	public void setIdGrupoCaracteristica(Integer idGrupoCaracteristica) {
		this.idGrupoCaracteristica = idGrupoCaracteristica;
	}
	public String getNmGrupoCaracteristica() {
		return nmGrupoCaracteristica;
	}
	public void setNmGrupoCaracteristica(String nmGrupoCaracteristica) {
		this.nmGrupoCaracteristica = nmGrupoCaracteristica;
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
	
	public List<CaracteristicaTO> getCaracteristicaList() {
		return caracteristicaList;
	}

	public void setCaracteristicaList(List<CaracteristicaTO> caracteristicaList) {
		this.caracteristicaList = caracteristicaList;
	}
	
	public Boolean getExistCaracteristicaAssociada() {
		return existCaracteristicaAssociada;
	}

	public void setExistCaracteristicaAssociada(Boolean existCaracteristicaAssociada) {
		this.existCaracteristicaAssociada = existCaracteristicaAssociada;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idGrupoCaracteristica: " + this.idGrupoCaracteristica, "nmGrupoCaracteristica: " + this.nmGrupoCaracteristica}, ", ");
	}
}
