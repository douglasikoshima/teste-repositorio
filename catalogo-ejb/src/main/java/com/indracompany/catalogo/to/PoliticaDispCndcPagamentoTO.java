package com.indracompany.catalogo.to;

import java.io.Serializable;

public class PoliticaDispCndcPagamentoTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8140855372879757014L;

	private Long idPoliticaDispCndcPagamento;
	private String inAreaConcorrencia; 
	private String inPlanoMinuto;
	
	public PoliticaDispCndcPagamentoTO() {
		super();
	}

	public PoliticaDispCndcPagamentoTO(Long idPoliticaDispCndcPagamento) {
		super();
		this.idPoliticaDispCndcPagamento = idPoliticaDispCndcPagamento;
	}
	
	public PoliticaDispCndcPagamentoTO(String inAreaConcorrencia, String inPlanoMinuto) {
		super();
		this.inAreaConcorrencia = inAreaConcorrencia;
		this.inPlanoMinuto = inPlanoMinuto;
	}

	public Long getIdPoliticaDispCndcPagamento() {
		return idPoliticaDispCndcPagamento;
	}

	public String getInAreaConcorrencia() {
		return inAreaConcorrencia;
	}

	public void setInAreaConcorrencia(String inAreaConcorrencia) {
		this.inAreaConcorrencia = inAreaConcorrencia;
	}

	public String getInPlanoMinuto() {
		return inPlanoMinuto;
	}

	public void setInPlanoMinuto(String inPlanoMinuto) {
		this.inPlanoMinuto = inPlanoMinuto;
	}

	public void setIdPoliticaDispCndcPagamento(Long idPoliticaDispCndcPagamento) {
		this.idPoliticaDispCndcPagamento = idPoliticaDispCndcPagamento;
	}


}
