package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author Luiz Pereira
 *
 */
public class MeioPagamentoTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public MeioPagamentoTO() {}
	
	public MeioPagamentoTO(Integer idMeioPagamento, String sgMeioPagamento) {
		this.idMeioPagamento = idMeioPagamento;
		this.sgMeioPagamento = sgMeioPagamento;
	}
	
	private Integer idMeioPagamento;
	private Date dtCriacao;
	private Date dtUltimaAlteracao;
	private String nmMeioPagamento;
	private String nmUsuarioAlteracao;
	private String nmUsuarioCriacao;
	private String sgMeioPagamento;
	
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

	public Integer getIdMeioPagamento() {
		return idMeioPagamento;
	}

	public void setIdMeioPagamento(Integer idMeioPagamento) {
		this.idMeioPagamento = idMeioPagamento;
	}

	public String getNmMeioPagamento() {
		return nmMeioPagamento;
	}

	public void setNmMeioPagamento(String nmMeioPagamento) {
		this.nmMeioPagamento = nmMeioPagamento;
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

	public String getSgMeioPagamento() {
		return sgMeioPagamento;
	}

	public void setSgMeioPagamento(String sgMeioPagamento) {
		this.sgMeioPagamento = sgMeioPagamento;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idMeioPagamento: " + this.idMeioPagamento, "nmMeioPagamento: " + this.nmMeioPagamento}, ", ");
	}
}
