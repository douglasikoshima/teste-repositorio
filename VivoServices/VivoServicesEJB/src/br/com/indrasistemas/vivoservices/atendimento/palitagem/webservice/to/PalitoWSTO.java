package br.com.indrasistemas.vivoservices.atendimento.palitagem.webservice.to;

import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class PalitoWSTO extends RespostaWSTO {

	private static final long serialVersionUID = -1347032197801232386L;

	private Long nrProtocolo;
	private String idAtendimento;
	private String msgError;
	private String codError;

    public PalitoWSTO() {
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

    public String getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(String idAtendimento) {
        this.idAtendimento = idAtendimento;
    }

}