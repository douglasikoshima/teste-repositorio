package com.indracompany.catalogo.to;

import java.math.BigDecimal;
import java.util.Date;

public class CondicaoPagamentoEncargoTO {

	private Long idCondicaoPagamentoEncargo;
	private BigDecimal vlParcela;
	private Date dtInicial;
	private Date dtFinal;
	private CanalVendaTO canalVendaTO;
	private CondicaoPagamentoTO condicaoPagamentoTO; 
	private Long idEncargo;
	private PoliticaDispCndcPagamentoTO politicaDispCndcPagamentoTO;
	private String inDisponivel;
	private String inCriacaoCatalogo;
	private Boolean inPossuiAreaConcorrencia = Boolean.FALSE;
	private Boolean inPossuiPlanoMinuto = Boolean.FALSE;
	
	public CondicaoPagamentoEncargoTO() {
		super();
	}

	public CondicaoPagamentoEncargoTO(Long idCondicaoPagamentoEncargo) {
		super();
		this.idCondicaoPagamentoEncargo = idCondicaoPagamentoEncargo;
	}

	public CondicaoPagamentoEncargoTO(CanalVendaTO canalVendaTO, Long idEncargo) {
		super();
		this.canalVendaTO = canalVendaTO;
		this.idEncargo = idEncargo;
	}

	public CondicaoPagamentoEncargoTO(BigDecimal vlParcela, Date dtInicial, CanalVendaTO canalVendaTO, CondicaoPagamentoTO condicaoPagamentoTO, Long idEncargo, String inDisponivel) {
		super();
		this.vlParcela = vlParcela;
		this.dtInicial = dtInicial;
		this.canalVendaTO = canalVendaTO;
		this.condicaoPagamentoTO = condicaoPagamentoTO;
		this.idEncargo = idEncargo;
		this.inDisponivel = inDisponivel;
	}

	public CondicaoPagamentoEncargoTO(Long idCondicaoPagamentoEncargo, BigDecimal vlParcela, Date dtInicial, CanalVendaTO canalVendaTO, CondicaoPagamentoTO condicaoPagamentoTO, Long idEncargo, String inDisponivel) {
		super();
		this.idCondicaoPagamentoEncargo = idCondicaoPagamentoEncargo;
		this.vlParcela = vlParcela;
		this.dtInicial = dtInicial;
		this.canalVendaTO = canalVendaTO;
		this.condicaoPagamentoTO = condicaoPagamentoTO;
		this.idEncargo = idEncargo;
		this.inDisponivel = inDisponivel;
	}
	
	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
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

	public CanalVendaTO getCanalVendaTO() {
		return canalVendaTO;
	}

	public void setCanalVendaTO(CanalVendaTO canalVendaTO) {
		this.canalVendaTO = canalVendaTO;
	}

	public CondicaoPagamentoTO getCondicaoPagamentoTO() {
		return condicaoPagamentoTO;
	}

	public void setCondicaoPagamentoTO(CondicaoPagamentoTO condicaoPagamentoTO) {
		this.condicaoPagamentoTO = condicaoPagamentoTO;
	}

	public Long getIdCondicaoPagamentoEncargo() {
		return idCondicaoPagamentoEncargo;
	}

	public void setIdCondicaoPagamentoEncargo(Long idCondicaoPagamentoEncargo) {
		this.idCondicaoPagamentoEncargo = idCondicaoPagamentoEncargo;
	}

	public Long getIdEncargo() {
		return idEncargo;
	}

	public void setIdEncargo(Long idEncargo) {
		this.idEncargo = idEncargo;
	}

	public PoliticaDispCndcPagamentoTO getPoliticaDispCndcPagamentoTO() {
		return politicaDispCndcPagamentoTO;
	}

	public void setPoliticaDispCndcPagamentoTO(
			PoliticaDispCndcPagamentoTO politicaDispCndcPagamentoTO) {
		this.politicaDispCndcPagamentoTO = politicaDispCndcPagamentoTO;
	}

	public BigDecimal getVlParcela() {
		return vlParcela;
	}

	public void setVlParcela(BigDecimal vlParcela) {
		this.vlParcela = vlParcela;
	}

	public Boolean getInPossuiAreaConcorrencia() {
		return inPossuiAreaConcorrencia;
	}

	public void setInPossuiAreaConcorrencia(Boolean inPossuiAreaConcorrencia) {
		this.inPossuiAreaConcorrencia = inPossuiAreaConcorrencia;
	}

	public Boolean getInPossuiPlanoMinuto() {
		return inPossuiPlanoMinuto;
	}

	public void setInPossuiPlanoMinuto(Boolean inPossuiPlanoMinuto) {
		this.inPossuiPlanoMinuto = inPossuiPlanoMinuto;
	}

	public String getInCriacaoCatalogo() {
		return inCriacaoCatalogo;
	}

	public void setInCriacaoCatalogo(String inCriacaoCatalogo) {
		this.inCriacaoCatalogo = inCriacaoCatalogo;
	}
	

}
