package com.indracompany.catalogo.to;

import java.util.Date;

public class SistemaTO {

	private Integer idSistema;

	private Date dtCriacao;

	private Date dtUltimaAlteracao;

	private String nmSistema;

	private String nmUsuarioAlteracao;

	private String nmUsuarioCriacao;

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

	public Integer getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(Integer idSistema) {
		this.idSistema = idSistema;
	}

	public String getNmSistema() {
		return nmSistema;
	}

	public void setNmSistema(String nmSistema) {
		this.nmSistema = nmSistema;
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
	
	
}
