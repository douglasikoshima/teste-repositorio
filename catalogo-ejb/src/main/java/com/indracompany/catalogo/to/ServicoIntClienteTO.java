package com.indracompany.catalogo.to;

import java.util.Date;

import com.indracompany.catalogo.datalayer.TipoCliente;

public class ServicoIntClienteTO {

	public ServicoIntClienteTO() {}
	
	public ServicoIntClienteTO(Integer idServicoIntCliente) {
		this.idServicoIntCliente = idServicoIntCliente;		
	}
	
	
	private Integer idServicoIntCliente;
	private TipoCliente tipoCliente;
	private ServicoInteratividadeTO servicoInteratividadeTO;
	private Date dtCriacao;
	private String nmUsuarioCriacao;
	private Date dtUltimaAlteracao;
	private String nmUsuarioAlteracao;
	
	
	
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

	public Integer getIdServicoIntCliente() {
		return idServicoIntCliente;
	}

	public void setIdServicoIntCliente(Integer idServicoIntCliente) {
		this.idServicoIntCliente = idServicoIntCliente;
	}

	public ServicoInteratividadeTO getServicoInteratividadeTO() {
		return servicoInteratividadeTO;
	}

	public void setServicoInteratividadeTO(
			ServicoInteratividadeTO servicoInteratividadeTO) {
		this.servicoInteratividadeTO = servicoInteratividadeTO;
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
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
