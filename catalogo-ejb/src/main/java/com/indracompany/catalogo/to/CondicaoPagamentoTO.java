package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Luiz
 *
 */
public class CondicaoPagamentoTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public CondicaoPagamentoTO() {}
	
	public CondicaoPagamentoTO(Integer idCondicaoPagamento) {
		this();
		this.idCondicaoPagamento = idCondicaoPagamento;
	}
	
	public CondicaoPagamentoTO(Integer qtParcelas, String cdCondicaoPagamento, BigDecimal txJuroParcela) {
		this();
		this.qtParcelas = qtParcelas;
		this.txJuroParcela = txJuroParcela;
        this.cdCondicaoPagamento = cdCondicaoPagamento;
	}

	private Integer idCondicaoPagamento;
	private Date dtCriacao;
	private Date dtUltimaAlteracao;
	private String nmCondicaoPagamento;
	private String nmUsuarioAlteracao;
	private String nmUsuarioCriacao;
	private Integer qtParcelas;
	private String sgCondicaoPagamento;
	private FormaPagamentoTO formaPagamentoTO;
	private List<CondicaoPagamentoAcaoTO> condicaoPagamentoAcaoTOList;
	private DescontoCondPagtoTO descontoCondPagtoTO;
	private List<TipoProdutoCondPagtoTO> tipoProdutoCondPagtoTOList;
	private Boolean inCriacaoCatalogo;
	private Boolean inDisponivel;
	private String usuarioForm;
	private Boolean inFixa;
    private String cdCondicaoPagamento;
    private BigDecimal txJuroParcela;
	

	public DescontoCondPagtoTO getDescontoCondPagtoTO() {
		return descontoCondPagtoTO;
	}

	public void setDescontoCondPagtoTO(DescontoCondPagtoTO descontoCondPagtoTO) {
		this.descontoCondPagtoTO = descontoCondPagtoTO;
	}

	public List<CondicaoPagamentoAcaoTO> getCondicaoPagamentoAcaoTOList() {
		return condicaoPagamentoAcaoTOList;
	}

	public void setCondicaoPagamentoAcaoTOList(
			List<CondicaoPagamentoAcaoTO> condicaoPagamentoAcaoTOList) {
		this.condicaoPagamentoAcaoTOList = condicaoPagamentoAcaoTOList;
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

	public FormaPagamentoTO getFormaPagamentoTO() {
		return formaPagamentoTO;
	}

	public void setFormaPagamentoTO(FormaPagamentoTO formaPagamentoTO) {
		this.formaPagamentoTO = formaPagamentoTO;
	}

	public Integer getIdCondicaoPagamento() {
		return idCondicaoPagamento;
	}

	public void setIdCondicaoPagamento(Integer idCondicaoPagamento) {
		this.idCondicaoPagamento = idCondicaoPagamento;
	}

	public String getNmCondicaoPagamento() {
		return nmCondicaoPagamento;
	}

	public void setNmCondicaoPagamento(String nmCondicaoPagamento) {
		this.nmCondicaoPagamento = nmCondicaoPagamento;
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

	public Integer getQtParcelas() {
		return qtParcelas;
	}

	public void setQtParcelas(Integer qtParcelas) {
		this.qtParcelas = qtParcelas;
	}

	public String getSgCondicaoPagamento() {
		return sgCondicaoPagamento;
	}

	public void setSgCondicaoPagamento(String sgCondicaoPagamento) {
		this.sgCondicaoPagamento = sgCondicaoPagamento;
	}

	public List<TipoProdutoCondPagtoTO> getTipoProdutoCondPagtoTOList() {
		return tipoProdutoCondPagtoTOList;
	}

	public void setTipoProdutoCondPagtoTOList(
			List<TipoProdutoCondPagtoTO> tipoProdutoCondPagtoTOList) {
		this.tipoProdutoCondPagtoTOList = tipoProdutoCondPagtoTOList;
	}

	public Boolean getInCriacaoCatalogo() {
		return inCriacaoCatalogo;
	}

	public void setInCriacaoCatalogo(Boolean inCriacaoCatalogo) {
		this.inCriacaoCatalogo = inCriacaoCatalogo;
	}

	public Boolean getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(Boolean inDisponivel) {
		this.inDisponivel = inDisponivel;
	}
	
	public String getUsuarioForm() {
		return this.usuarioForm;
	}

	public void setUsuarioForm(String usuarioForm) {
		this.usuarioForm = usuarioForm;
	}

	public Boolean getInFixa() {
		return this.inFixa;
	}
	
	public void setInFixa(Boolean inFixa) {
		this.inFixa = inFixa;
	}

    public String getCdCondicaoPagamento() {
        return cdCondicaoPagamento;
    }

    public void setCdCondicaoPagamento(String cdCondicaoPagamento) {
        this.cdCondicaoPagamento = cdCondicaoPagamento;
    }

    public BigDecimal getTxJuroParcela() {
        return txJuroParcela;
    }

    public void setTxJuroParcela(BigDecimal txJuroParcela) {
        this.txJuroParcela = txJuroParcela;
    }
	
}
