package com.indracompany.catalogo.to;

import java.util.Date;

import com.indracompany.catalogo.datalayer.CanalAtendimento;

public class ServicoIntCanalTO {
	
	public ServicoIntCanalTO() {}
	
	public ServicoIntCanalTO(Integer idServicoIntCanal) {
		this.idServicoIntCanal = idServicoIntCanal;
	}

	private Integer idServicoIntCanal;
	private CanalAtendimento canalAtendimento;
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

	public Integer getIdServicoIntCanal() {
		return idServicoIntCanal;
	}
	public void setIdServicoIntCanal(Integer idServicoIntCanal) {
		this.idServicoIntCanal = idServicoIntCanal;
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

	public CanalAtendimento getCanalAtendimento() {
		return canalAtendimento;
	}

	public void setCanalAtendimento(CanalAtendimento canalAtendimento) {
		this.canalAtendimento = canalAtendimento;
	}

	public ServicoInteratividadeTO getServicoInteratividadeTO() {
		return servicoInteratividadeTO;
	}

	public void setServicoInteratividadeTO(
			ServicoInteratividadeTO servicoInteratividadeTO) {
		this.servicoInteratividadeTO = servicoInteratividadeTO;
	}

}
