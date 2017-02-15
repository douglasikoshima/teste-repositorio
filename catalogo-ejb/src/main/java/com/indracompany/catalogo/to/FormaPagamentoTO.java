package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Luiz
 *
 */
public class FormaPagamentoTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public FormaPagamentoTO() {}
	
	public FormaPagamentoTO(Integer idFormaPagamento) {
		this.idFormaPagamento = idFormaPagamento;
	}
	
	private Integer idFormaPagamento;
	private Date dtCriacao;
	private Date dtUltimaAlteracao;
	private String nmFormaPagamento;
	private String nmUsuarioAlteracao;
	private String nmUsuarioCriacao;
	private String sgFormaPagamento;
	private List<FormaPagamentoBandeiraTO> formaPagamentoBandeiraTOList;
	private List<CondicaoPagamentoTO> condicaoPagamentoTOList;
	private MeioPagamentoTO meioPagamentoTO;
	private FormaPagtoCanalParamTO formaPagtoCanalParamTO;
    private List<PlataformaTO> plataformaTOList;
	
	public List<CondicaoPagamentoTO> getCondicaoPagamentoTOList() {
		return condicaoPagamentoTOList;
	}

	public FormaPagtoCanalParamTO getFormaPagtoCanalParamTO() {
		return formaPagtoCanalParamTO;
	}

	public void setFormaPagtoCanalParamTO(
			FormaPagtoCanalParamTO formaPagtoCanalParamTO) {
		this.formaPagtoCanalParamTO = formaPagtoCanalParamTO;
	}

	public void setCondicaoPagamentoTOList(
			List<CondicaoPagamentoTO> condicaoPagamentoTOList) {
		this.condicaoPagamentoTOList = condicaoPagamentoTOList;
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

	public Integer getIdFormaPagamento() {
		return idFormaPagamento;
	}

	public void setIdFormaPagamento(Integer idFormaPagamento) {
		this.idFormaPagamento = idFormaPagamento;
	}

	public String getNmFormaPagamento() {
		return nmFormaPagamento;
	}

	public void setNmFormaPagamento(String nmFormaPagamento) {
		this.nmFormaPagamento = nmFormaPagamento;
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

	public String getSgFormaPagamento() {
		return sgFormaPagamento;
	}

	public void setSgFormaPagamento(String sgFormaPagamento) {
		this.sgFormaPagamento = sgFormaPagamento;
	}

	public List<FormaPagamentoBandeiraTO> getFormaPagamentoBandeiraTOList() {
		return formaPagamentoBandeiraTOList;
	}

	public void setFormaPagamentoBandeiraTOList(
			List<FormaPagamentoBandeiraTO> formaPagamentoBandeiraTOList) {
		this.formaPagamentoBandeiraTOList = formaPagamentoBandeiraTOList;
	}

	public MeioPagamentoTO getMeioPagamentoTO() {
		return meioPagamentoTO;
	}

	public void setMeioPagamentoTO(MeioPagamentoTO meioPagamentoTO) {
		this.meioPagamentoTO = meioPagamentoTO;
	}

    public List<PlataformaTO> getPlataformaTOList() {
        return plataformaTOList;
    }

    public void setPlataformaTOList(List<PlataformaTO> plataformaTOList) {
        this.plataformaTOList = plataformaTOList;
    }
}
