package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Luiz
 *
 */
public class FormaPagtoCanalAtndParamTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public FormaPagtoCanalAtndParamTO() {}
	
	public FormaPagtoCanalAtndParamTO(Integer idFormaPagtoCanalAtndParam) {
		this.idFormaPagtoCanalAtndParam = idFormaPagtoCanalAtndParam;
	}
	
	private Integer idFormaPagtoCanalAtndParam;
	private String cdInstituicaoFinanceira;
	private Date dtCriacao;
	private Date dtUltimaAlteracao;
	private String nmUsuarioCriacao;
	private String nmUsuarioUltimaAlteracao;
	private Float valorDesconto;
	private Float vlParcelaMinima;
	private CanalAtendimentoTO canalAtendimentoTO;
	private FormaPagtoCanalParamTO formaPagtoCanalParamTO;

	public CanalAtendimentoTO getCanalAtendimentoTO() {
		return canalAtendimentoTO;
	}

	public void setCanalAtendimentoTO(CanalAtendimentoTO canalAtendimentoTO) {
		this.canalAtendimentoTO = canalAtendimentoTO;
	}

	public String getCdInstituicaoFinanceira() {
		return cdInstituicaoFinanceira;
	}

	public void setCdInstituicaoFinanceira(String cdInstituicaoFinanceira) {
		this.cdInstituicaoFinanceira = cdInstituicaoFinanceira;
	}

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

	public FormaPagtoCanalParamTO getFormaPagtoCanalParamTO() {
		return formaPagtoCanalParamTO;
	}

	public void setFormaPagtoCanalParamTO(
			FormaPagtoCanalParamTO formaPagtoCanalParamTO) {
		this.formaPagtoCanalParamTO = formaPagtoCanalParamTO;
	}

	public Integer getIdFormaPagtoCanalAtndParam() {
		return idFormaPagtoCanalAtndParam;
	}

	public void setIdFormaPagtoCanalAtndParam(Integer idFormaPagtoCanalAtndParam) {
		this.idFormaPagtoCanalAtndParam = idFormaPagtoCanalAtndParam;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

	public String getNmUsuarioUltimaAlteracao() {
		return nmUsuarioUltimaAlteracao;
	}

	public void setNmUsuarioUltimaAlteracao(String nmUsuarioUltimaAlteracao) {
		this.nmUsuarioUltimaAlteracao = nmUsuarioUltimaAlteracao;
	}

	public Float getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(Float valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public Float getVlParcelaMinima() {
		return vlParcelaMinima;
	}

	public void setVlParcelaMinima(Float vlParcelaMinima) {
		this.vlParcelaMinima = vlParcelaMinima;
	}
}
