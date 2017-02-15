package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author Luiz Pereira
 *
 */
public class TipoClienteTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public TipoClienteTO() {}
	
	public TipoClienteTO(Integer idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}

	private Integer idTipoCliente;
	private Date dtCriacao;
	private Date dtUltimaAlteracao;
	private String nmTipoCliente;
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

	public Integer getIdTipoCliente() {
		return idTipoCliente;
	}

	public void setIdTipoCliente(Integer idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}

	public String getNmTipoCliente() {
		return nmTipoCliente;
	}

	public void setNmTipoCliente(String nmTipoCliente) {
		this.nmTipoCliente = nmTipoCliente;
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

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idTipoCliente: " + this.idTipoCliente, "nmTipoCliente: " + this.nmTipoCliente}, ", ");
	}
}
