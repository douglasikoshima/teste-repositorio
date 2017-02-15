package br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice.to;

import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class RespostaManutencaoProcessoTO extends RespostaWSTO {

	public RespostaManutencaoProcessoTO() {
	}

	private static final long serialVersionUID = -17470621978845386L;

	private Long nrProtocolo;
	   
	public Long getNrProtocolo() {
		return nrProtocolo;
	}

	public void setNrProtocolo(Long nrProtocolo) {
		this.nrProtocolo = nrProtocolo;
	}

}
