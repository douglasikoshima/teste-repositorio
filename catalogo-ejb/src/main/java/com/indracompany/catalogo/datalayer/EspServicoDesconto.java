package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ESPSERVICODESCONTO", schema = "CATALOGOPRS_OW")
public class EspServicoDesconto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5260655917367678310L;
	
	@Id
	@Column(name = "IDSERVICO")
	private Integer idServico;
	
	@Column(name = "VLDESCONTOABSOLUTO")
	private BigDecimal vlDescontoAbsoluto;
	
	@Column(name = "SGTIPOAPLICACAODESCONTO")
	private String sgTipoAplicacaoDesconto;

    @Column(name = "SGTIPOAPLICACAODESCONTOAPERIOD")
	private String sgTipoAplicacaoDescontoAperiod;
	
	@Column(name = "TXDESCONTOPERCENTUAL")
	private BigDecimal txDescontoPercentual;
	
	@OneToOne
	@JoinColumn(name = "IDSERVICO", nullable = false)
	private Servico servico;

	public Integer getIdServico() {
		return idServico;
	}

	public void setIdServico(Integer idServico) {
		this.idServico = idServico;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public String getSgTipoAplicacaoDesconto() {
		return sgTipoAplicacaoDesconto;
	}

	public void setSgTipoAplicacaoDesconto(String sgTipoAplicacaoDesconto) {
		this.sgTipoAplicacaoDesconto = sgTipoAplicacaoDesconto;
	}

	public BigDecimal getTxDescontoPercentual() {
		return txDescontoPercentual;
	}

	public void setTxDescontoPercentual(BigDecimal txDescontoPercentual) {
		this.txDescontoPercentual = txDescontoPercentual;
	}

	public BigDecimal getVlDescontoAbsoluto() {
		return vlDescontoAbsoluto;
	}

	public void setVlDescontoAbsoluto(BigDecimal vlDescontoAbsoluto) {
		this.vlDescontoAbsoluto = vlDescontoAbsoluto;
	}

    public String getSgTipoAplicacaoDescontoAperiod() {
        return sgTipoAplicacaoDescontoAperiod;
    }

    public void setSgTipoAplicacaoDescontoAperiod(
            String sgTipoAplicacaoDescontoAperiod) {
        this.sgTipoAplicacaoDescontoAperiod = sgTipoAplicacaoDescontoAperiod;
    }
	



}
