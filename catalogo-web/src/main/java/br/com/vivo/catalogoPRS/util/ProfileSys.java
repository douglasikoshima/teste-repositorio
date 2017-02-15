package br.com.vivo.catalogoPRS.util;

import java.io.Serializable;

public class ProfileSys implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long idSistema;
	private String name;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdSistema() {
		return idSistema;
	}
	public void setIdSistema(Long idSistema) {
		this.idSistema = idSistema;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
