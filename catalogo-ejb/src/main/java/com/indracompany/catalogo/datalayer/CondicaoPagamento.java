package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@SequenceGenerator(name = "CONDICAOPAGAMENTO_SQ", sequenceName = "CATALOGOPRS_OW.CONDICAOPAGAMENTO_SQ", allocationSize = 1)
@Table(name="CONDICAOPAGAMENTO", schema = "CATALOGOPRS_OW")
public class CondicaoPagamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public CondicaoPagamento() {}
	
	public CondicaoPagamento(Integer idCondicaoPagamento) {
		this.idCondicaoPagamento = idCondicaoPagamento;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONDICAOPAGAMENTO_SQ")
	@Column(name = "IDCONDICAOPAGAMENTO")
	private Integer idCondicaoPagamento;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO", insertable = true, updatable = false)
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO", insertable = false, updatable = true)
	private Date dtUltimaAlteracao;

    @Column(name = "NMCONDICAOPAGAMENTO")
	private String nmCondicaoPagamento;

    @Column(name = "NMUSUARIOALTERACAO", insertable = false, updatable = true)
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO", insertable = true, updatable = false)
	private String nmUsuarioCriacao;

    @Column(name = "QTPARCELAS")
	private Integer qtParcelas;

    @Column(name = "SGCONDICAOPAGAMENTO")
	private String sgCondicaoPagamento;
    
    @Column(name = "INDISPONIVEL")
    private String inDisponivel;

    @Column(name = "INFIXA")
    private String inFixa;
    
    @Column(name = "INCRIACAOCATALOGO")
    private String inCriacaoCatalogo;
    
    @Column(name = "TXJUROPARCELA")
    private BigDecimal txJuroParcela;
    
    @Column(name = "CDCONDICAOPAGAMENTO")
    private String cdCondicaoPagamento;
    
    @Column(name = "INALTERACAOCATALOGO")
    private String inAlteracaoCatalogo;
    
	//bi-directional many-to-one association to Formapagamento
	@ManyToOne
	@JoinColumn(name="IDFORMAPAGAMENTO")
	private FormaPagamento formaPagamento;

	//bi-directional many-to-one association to Condicaopagamentoacao
	@OneToMany(mappedBy="condicaoPagamento")
	private List<CondicaoPagamentoAcao> condicaoPagamentoAcaoList;

	//bi-directional many-to-one association to Descontocondpagto
	@OneToMany(mappedBy="condicaoPagamento")
	private List<DescontoCondPagto> descontoCondPagtoList;

	//bi-directional many-to-one association to Tipoprodutocondpagto
	@OneToMany(mappedBy="condicaoPagamento")
	private List<TipoProdutoCondPagto> tipoProdutoCondPagtoList;

	public List<CondicaoPagamentoAcao> getCondicaoPagamentoAcaoList() {
		return condicaoPagamentoAcaoList;
	}

	public void setCondicaoPagamentoAcaoList(
			List<CondicaoPagamentoAcao> condicaoPagamentoAcaoList) {
		this.condicaoPagamentoAcaoList = condicaoPagamentoAcaoList;
	}

	public List<DescontoCondPagto> getDescontoCondPagtoList() {
		return descontoCondPagtoList;
	}

	public void setDescontoCondPagto(List<DescontoCondPagto> descontoCondPagtoList) {
		this.descontoCondPagtoList = descontoCondPagtoList;
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

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
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

	public List<TipoProdutoCondPagto> getTipoProdutoCondPagtoList() {
		return tipoProdutoCondPagtoList;
	}

	public void setTipoProdutoCondPagtoList(
			List<TipoProdutoCondPagto> tipoProdutoCondPagtoList) {
		this.tipoProdutoCondPagtoList = tipoProdutoCondPagtoList;
	}
    
	public String getInDisponivel() {
		return this.inDisponivel;
	}
	
	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}
	
	public String getInFixa() {
		return this.inFixa;
	}
	
	public void setInFixa(String inFixa) {
		this.inFixa = inFixa;
	}	

	public String getInCriacaoCatalogo() {
		return inCriacaoCatalogo;
	}

	public void setInCriacaoCatalogo(String inCriacaoCatalogo) {
		this.inCriacaoCatalogo = inCriacaoCatalogo;
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

    public String getInAlteracaoCatalogo() {
        return inAlteracaoCatalogo;
    }

    public void setInAlteracaoCatalogo(String inAlteracaoCatalogo) {
        this.inAlteracaoCatalogo = inAlteracaoCatalogo;
    }
	
	
}