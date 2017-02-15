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

@Entity
@SequenceGenerator(name = "CONDICAOPAGAMENTOENCARGO_SQ", sequenceName = "CATALOGOPRS_OW.CONDICAOPAGAMENTOENCARGO_SQ", allocationSize = 1)
@Table(name="CONDICAOPAGAMENTOENCARGO", schema = "CATALOGOPRS_OW")
public class CondicaoPagamentoEncargo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5723495446363992360L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONDICAOPAGAMENTOENCARGO_SQ")
	@Column(name = "IDCONDICAOPAGAMENTOENCARGO")
	private Long idCondicaoPagamentoEncargo;

	@Column(name = "VLPARCELA")
	private BigDecimal vlParcela;
	
	@Column(name = "INDISPONIVEL")
	private String inDisponivel;
	
	@Temporal( TemporalType.DATE)
	@Column(name = "DTINICIAL", updatable = false, insertable = true)
	private Date dtInicial;
	
	@Temporal( TemporalType.DATE)
	@Column(name = "DTFINAL", updatable = false, insertable = true)
	private Date dtFinal;
	
	@Column(name = "INCRIACAOCATALOGO")
	private String inCriacaoCatalogo;
	
	@ManyToOne
	@JoinColumn(name = "IDCANALVENDA")
	private CanalVenda canalVenda;
	
	@ManyToOne
	@JoinColumn(name = "IDCONDICAOPAGAMENTO")
	private CondicaoPagamento condicaoPagamento; 
	
	@ManyToOne
	@JoinColumn(name = "IDENCARGO")
	private Encargo encargo;
	
	@ManyToOne
	@JoinColumn(name = "IDPOLITICADISPCNDCPAGAMENTO")
	private PoliticaDispCndcPagamento politicaDispCndcPagamento;
	
	@OneToMany(mappedBy="condicaoPagamentoEncargo")
	private List<DisponibilidadeCndcPagamento> disponibilidadeCndcPagamentoList;
	
	public CondicaoPagamentoEncargo() {
		super();
	}

	public CondicaoPagamentoEncargo(Long idCondicaoPagamentoEncargo) {
		super();
		this.idCondicaoPagamentoEncargo = idCondicaoPagamentoEncargo;
	}

	public List<DisponibilidadeCndcPagamento> getDisponibilidadeCndcPagamentoList() {
		return disponibilidadeCndcPagamentoList;
	}

	public void setDisponibilidadeCndcPagamentoList(
			List<DisponibilidadeCndcPagamento> disponibilidadeCndcPagamentoList) {
		this.disponibilidadeCndcPagamentoList = disponibilidadeCndcPagamentoList;
	}

	public CanalVenda getCanalVenda() {
		return canalVenda;
	}

	public void setCanalVenda(CanalVenda canalVenda) {
		this.canalVenda = canalVenda;
	}

	public CondicaoPagamento getCondicaoPagamento() {
		return condicaoPagamento;
	}

	public void setCondicaoPagamento(CondicaoPagamento condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
	}

	public Encargo getEncargo() {
		return encargo;
	}

	public void setEncargo(Encargo encargo) {
		this.encargo = encargo;
	}

	public Long getIdCondicaoPagamentoEncargo() {
		return idCondicaoPagamentoEncargo;
	}

	public void setIdCondicaoPagamentoEncargo(Long idCondicaoPagamentoEncargo) {
		this.idCondicaoPagamentoEncargo = idCondicaoPagamentoEncargo;
	}

	public PoliticaDispCndcPagamento getPoliticaDispCndcPagamento() {
		return politicaDispCndcPagamento;
	}

	public void setPoliticaDispCndcPagamento(
			PoliticaDispCndcPagamento politicaDispCndcPagamento) {
		this.politicaDispCndcPagamento = politicaDispCndcPagamento;
	}

	public BigDecimal getVlParcela() {
		return vlParcela;
	}

	public void setVlParcela(BigDecimal vlParcela) {
		this.vlParcela = vlParcela;
	}

	public Date getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}

	public Date getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public String getInCriacaoCatalogo() {
		return inCriacaoCatalogo;
	}

	public void setInCriacaoCatalogo(String inCriacaoCatalogo) {
		this.inCriacaoCatalogo = inCriacaoCatalogo;
	}

}
