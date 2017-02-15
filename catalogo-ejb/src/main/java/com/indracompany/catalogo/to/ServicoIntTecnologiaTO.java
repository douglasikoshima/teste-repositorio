package com.indracompany.catalogo.to;

import java.util.Date;

import com.indracompany.catalogo.datalayer.Tecnologia;

public class ServicoIntTecnologiaTO {
	
	public ServicoIntTecnologiaTO() {}
	
	public ServicoIntTecnologiaTO(Integer idServicoIntTecnologia) {
		this.idServicoIntTecnologia = idServicoIntTecnologia;		
	}
	
	private Integer idServicoIntTecnologia;
	private Tecnologia tecnologia;
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

	public ServicoInteratividadeTO getServicoInteratividadeTO() {
		return servicoInteratividadeTO;
	}

	public void setServicoInteratividadeTO(
			ServicoInteratividadeTO servicoInteratividadeTO) {
		this.servicoInteratividadeTO = servicoInteratividadeTO;
	}

	public Tecnologia getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(Tecnologia tecnologia) {
		this.tecnologia = tecnologia;
	}

	public Integer getIdServicoIntTecnologia() {
		return idServicoIntTecnologia;
	}

	public void setIdServicoIntTecnologia(Integer idServicoIntTecnologia) {
		this.idServicoIntTecnologia = idServicoIntTecnologia;
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
