package br.com.indrasistemas.vivoservices.vole.contato.webservice.to;

import java.util.List;

import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class ResultadoPontoContatoTO extends RespostaWSTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1747032197896232386L;

	private List pontoContato;

	public List getPontoContato() {
		return pontoContato;
	}

	public void setPontoContato(List pontoContato) {
		this.pontoContato = pontoContato;
	}

}
