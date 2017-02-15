package com.indracompany.catalogo.to;

import java.util.Date;

import com.indracompany.catalogo.datalayer.Plataforma;

public class ServicoIntPlataformaTO {
	
	public ServicoIntPlataformaTO() {}
	
	public ServicoIntPlataformaTO(Integer idServicoIntPlataforma) {
		this.idServicoIntPlataforma = idServicoIntPlataforma;
		
	}

	private Integer idServicoIntPlataforma;
	private Plataforma plataforma;
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

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

	public ServicoInteratividadeTO getServicoInteratividadeTO() {
		return servicoInteratividadeTO;
	}

	public void setServicoInteratividadeTO(
			ServicoInteratividadeTO servicoInteratividadeTO) {
		this.servicoInteratividadeTO = servicoInteratividadeTO;
	}

	public Integer getIdServicoIntPlataforma() {
		return idServicoIntPlataforma;
	}

	public void setIdServicoIntPlataforma(Integer idServicoIntPlataforma) {
		this.idServicoIntPlataforma = idServicoIntPlataforma;
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
