package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.math.BigDecimal;

public class ServicoFixaEncargoTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8509751346779983841L;

	private String cdEncargo;
	
	private Long idSolicitacaoComercial;
	
	private BigDecimal vlEncargoInicial;
	
	private BigDecimal vlEncargoFinal;
	
	private String cdPacote;
	
	private Long pzGratuidade;
	
	private Long idTipoEncargo;

	public String getCdEncargo() {
		return cdEncargo;
	}

	public void setCdEncargo(String cdEncargo) {
		this.cdEncargo = cdEncargo;
	}

	public String getCdPacote() {
		return cdPacote;
	}

	public void setCdPacote(String cdPacote) {
		this.cdPacote = cdPacote;
	}

	public Long getIdSolicitacaoComercial() {
		return idSolicitacaoComercial;
	}

	public void setIdSolicitacaoComercial(Long idSolicitacaoComercial) {
		this.idSolicitacaoComercial = idSolicitacaoComercial;
	}

	public Long getIdTipoEncargo() {
		return idTipoEncargo;
	}

	public void setIdTipoEncargo(Long idTipoEncargo) {
		this.idTipoEncargo = idTipoEncargo;
	}

	public Long getPzGratuidade() {
		return pzGratuidade;
	}

	public void setPzGratuidade(Long pzGratuidade) {
		this.pzGratuidade = pzGratuidade;
	}

	public BigDecimal getVlEncargoFinal() {
		return vlEncargoFinal;
	}

	public void setVlEncargoFinal(BigDecimal vlEncargoFinal) {
		this.vlEncargoFinal = vlEncargoFinal;
	}

	public BigDecimal getVlEncargoInicial() {
		return vlEncargoInicial;
	}

	public void setVlEncargoInicial(BigDecimal vlEncargoInicial) {
		this.vlEncargoInicial = vlEncargoInicial;
	}
}
