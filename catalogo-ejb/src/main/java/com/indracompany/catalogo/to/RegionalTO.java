package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.List;

public class RegionalTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -56468879841L;

	private Integer idRegional;
	private String nmRegional;
	private List<UfTO> ufs;
	
	public RegionalTO() {}
	public RegionalTO(Integer idRegional, String nmRegional) {
		this.idRegional = idRegional;
		this.nmRegional = nmRegional;
	}
	
	public RegionalTO(Integer idRegional) {
		this.idRegional = idRegional;
	}
	
	public Integer getIdRegional() {
		return idRegional;
	}
	public void setIdRegional(Integer idRegional) {
		this.idRegional = idRegional;
	}
	public String getNmRegional() {
		return nmRegional;
	}
	public void setNmRegional(String nmRegional) {
		this.nmRegional = nmRegional;
	}
	public List<UfTO> getUfs() {
		return ufs;
	}
	public void setUfs(List<UfTO> ufs) {
		this.ufs = ufs;
	}
}
