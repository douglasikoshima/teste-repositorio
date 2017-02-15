package com.indracompany.catalogo.to;

import java.io.Serializable;

public class TipoPlanoTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1066364064538545512L;

	private Integer idTipoPlano;
	
	private String nmTipoPlano;

	public Integer getIdTipoPlano() {
		return idTipoPlano;
	}

	public void setIdTipoPlano(Integer idTipoPlano) {
		this.idTipoPlano = idTipoPlano;
	}

	public String getNmTipoPlano() {
		return nmTipoPlano;
	}

	public void setNmTipoPlano(String nmTipoPlano) {
		this.nmTipoPlano = nmTipoPlano;
	}
	
}
