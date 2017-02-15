package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author Luiz Pereira
 *
 */
public class FormaPagamentoBandeiraTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FormaPagamentoBandeiraTO() {}
	
	public FormaPagamentoBandeiraTO(Integer idFormaPagamentoBandeira, Integer idFormaPagamento) {
		this.idFormaPagamentoBandeira = idFormaPagamentoBandeira;
		this.idFormaPagamento = idFormaPagamento;
	}

	private Integer idFormaPagamentoBandeira;
	private Integer idFormaPagamento;
	private BandeiraTO bandeiraTO;
	private Date dtCriacao;
	private String nmUsuarioCriacao;
	
	public BandeiraTO getBandeiraTO() {
		return bandeiraTO;
	}

	public void setBandeiraTO(BandeiraTO bandeiraTO) {
		this.bandeiraTO = bandeiraTO;
	}

	public Integer getIdFormaPagamento() {
		return idFormaPagamento;
	}

	public void setIdFormaPagamento(Integer idFormaPagamento) {
		this.idFormaPagamento = idFormaPagamento;
	}

	public Integer getIdFormaPagamentoBandeira() {
		return idFormaPagamentoBandeira;
	}

	public void setIdFormaPagamentoBandeira(Integer idFormaPagamentoBandeira) {
		this.idFormaPagamentoBandeira = idFormaPagamentoBandeira;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idFormaPagamentoBandeira: " + this.idFormaPagamentoBandeira, "idFormaPagamento: " + this.idFormaPagamento}, ", ");
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}
}
