package br.com.vivo.fo.dbclasses;

import java.io.Serializable;
import java.sql.Date;

public class ApoioParametro implements Serializable {

	private static final long serialVersionUID = 17053543381L;

	private long idParametro;
	private String cdParametro;
	private String dsParametro;
	private String dsValorParametro;
	private long idUsuarioAlteracao;
	private Date dtUltimaAlteracao;

	public long getIdParametro() {
		return this.idParametro;
	}

	public void setIdParametro(long arg) {
		this.idParametro = arg;
	}

	public String getCdParametro() {
		return this.cdParametro;
	}

	public void setCdParametro(String arg) {
		this.cdParametro = arg;
	}

	public String getDsParametro() {
		return this.dsParametro;
	}

	public void setDsParametro(String arg) {
		this.dsParametro = arg;
	}

	public String getDsValorParametro() {
		return this.dsValorParametro;
	}

	public void setDsValorParametro(String arg) {
		this.dsValorParametro = arg;
	}

	public long getIdUsuarioAlteracao() {
		return this.idUsuarioAlteracao;
	}

	public void setIdUsuarioAlteracao(long arg) {
		this.idUsuarioAlteracao = arg;
	}

	public Date getDtUltimaAlteracao() {
		return this.dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Date arg) {
		this.dtUltimaAlteracao = arg;
	}

}