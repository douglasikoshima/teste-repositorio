package br.com.indrasistemas.vivoservices.atendimento.palitagem.webservice.to;

import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class ResultadoPalitoWSTO extends RespostaWSTO {

	private static final long serialVersionUID = -1347032197801232386L;

	public Long idAtendimento;
	private String cdRetorno;

    public ResultadoPalitoWSTO() {
    }

	public String getCdRetorno() {
		return cdRetorno;
	}
	
	public void setCdRetorno(String cdRetorno) {
		this.cdRetorno = cdRetorno;
	}

	public Long getIdAtendimento() {
		return idAtendimento;
	}

	public void setIdAtendimento(Long idAtendimento) {
		this.idAtendimento = idAtendimento;
	}

}