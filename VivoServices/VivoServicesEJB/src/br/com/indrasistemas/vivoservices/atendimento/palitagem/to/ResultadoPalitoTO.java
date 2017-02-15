package br.com.indrasistemas.vivoservices.atendimento.palitagem.to;

import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class ResultadoPalitoTO extends RespostaWSTO {

	private static final long serialVersionUID = -1347032197801232386L;

	public String idAtendimento;
	private String cdRetorno;

    public ResultadoPalitoTO() {
    }

	public String getCdRetorno() {
		return cdRetorno;
	}
	
	public void setCdRetorno(String cdRetorno) {
	    if ( cdRetorno != null )
	        this.cdRetorno = cdRetorno;
	}

    public String getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(String idAtendimento) {
        if ( idAtendimento != null )
            this.idAtendimento = idAtendimento;
    }
}