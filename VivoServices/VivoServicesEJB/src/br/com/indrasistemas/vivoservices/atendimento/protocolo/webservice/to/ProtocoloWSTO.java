package br.com.indrasistemas.vivoservices.atendimento.protocolo.webservice.to;

import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class ProtocoloWSTO extends RespostaWSTO {

    private static final long serialVersionUID = 3886252484790878039L;

    private Long nrProtocolo;
	private String msgError;
	private String codError;

    public ProtocoloWSTO() {
    }

	public Long getNrProtocolo() {
		return nrProtocolo;
	}

	public void setNrProtocolo(Long nrProtocolo) {
		this.nrProtocolo = nrProtocolo;
	}

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public String getCodError() {
        return codError;
    }

    public void setCodError(String codError) {
        this.codError = codError;
    }

}