package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ENCARGO", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "ENCARGO_SQ", sequenceName = "CATALOGOPRS_OW.ENCARGO_SQ", allocationSize = 1)	
public class Encargo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1769774797576180296L;
	
	@Id
	@Column(name = "IDENCARGO")
	private Long idEncargo;
	
	@Column(name = "CDENCARGO")
	private String cdEncargo;

	@Column(name = "DSENCARGO")
	private String dsEncargo;

	@ManyToOne
	@JoinColumn(name = "IDTIPOENCARGO")
	private TipoEncargo tipoEncargo;

	@Column(name = "VLENCARGO")
	private BigDecimal vlEncargo;

	@Column(name = "CDMOEDA")
	private String cdMoeda;

	@Column(name = "PZGRATUIDADE")
	private Long pzGratuidade;

	@ManyToOne
	@JoinColumn(name = "IDSOLICITACAOCOMERCIAL")
	private SolicitacaoComercial solicitacaoComercial;
	
	@ManyToOne
	@JoinColumn(name = "IDVALORPOLITICAPRECIFICACAO")
	private ValorPoliticaPrecificacao valorPoliticaPrecificacao;
	
	@OneToMany(mappedBy="encargo")
	private List<CondicaoPagamentoEncargo> condicaoPagamentoEncargoList;
	
	public Encargo() {
		super();
	}

	public Encargo(Long idEncargo) {
		super();
		this.idEncargo = idEncargo;
	}

	public String getCdEncargo() {
		return cdEncargo;
	}

	public void setCdEncargo(String cdEncargo) {
		this.cdEncargo = cdEncargo;
	}

	public String getCdMoeda() {
		return cdMoeda;
	}

	public void setCdMoeda(String cdMoeda) {
		this.cdMoeda = cdMoeda;
	}

	public String getDsEncargo() {
		return dsEncargo;
	}

	public void setDsEncargo(String dsEncargo) {
		this.dsEncargo = dsEncargo;
	}

	public void setIdEncargo(Long idEncargo) {
		this.idEncargo = idEncargo;
	}

	public Long getPzGratuidade() {
		return pzGratuidade;
	}

	public void setPzGratuidade(Long pzGratuidade) {
		this.pzGratuidade = pzGratuidade;
	}

	public BigDecimal getVlEncargo() {
		return vlEncargo;
	}

	public void setVlEncargo(BigDecimal vlEncargo) {
		this.vlEncargo = vlEncargo;
	}

	public SolicitacaoComercial getSolicitacaoComercial() {
		return solicitacaoComercial;
	}

	public void setSolicitacaoComercial(SolicitacaoComercial solicitacaoComercial) {
		this.solicitacaoComercial = solicitacaoComercial;
	}

	public TipoEncargo getTipoEncargo() {
		return tipoEncargo;
	}

	public void setTipoEncargo(TipoEncargo tipoEncargo) {
		this.tipoEncargo = tipoEncargo;
	}

	public Long getIdEncargo() {
		return idEncargo;
	}

	public List<CondicaoPagamentoEncargo> getCondicaoPagamentoEncargoList() {
		return condicaoPagamentoEncargoList;
	}

	public void setCondicaoPagamentoEncargoList(
			List<CondicaoPagamentoEncargo> condicaoPagamentoEncargoList) {
		this.condicaoPagamentoEncargoList = condicaoPagamentoEncargoList;
	}

	public ValorPoliticaPrecificacao getValorPoliticaPrecificacao() {
		return valorPoliticaPrecificacao;
	}

	public void setValorPoliticaPrecificacao(
			ValorPoliticaPrecificacao valorPoliticaPrecificacao) {
		this.valorPoliticaPrecificacao = valorPoliticaPrecificacao;
	}
	
}
