package br.com.indrasistemas.vivoservices.atendimento.backoffice.webservice.to;

import java.util.List;

import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class ResultadoComprovanteCancelamentoTO extends RespostaWSTO {

	/**
	 * 
	 */
	public ResultadoComprovanteCancelamentoTO() {

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1747032197896232386L;

	private List comprovanteCancelamento;

	public List getComprovanteCancelamento() {
		return comprovanteCancelamento;
	}

	public void setComprovanteCancelamento(List ComprovanteCancelamentoTO) {
		this.comprovanteCancelamento = ComprovanteCancelamentoTO;
	}

}
