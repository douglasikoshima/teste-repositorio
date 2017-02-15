package br.com.indrasistemas.vivoservices.listapup.webservice.to;

import br.com.indrasistemas.vivoservices.listapup.to.LinhaPUPWSTO;
import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class ResultadoLinhaPUPTO extends RespostaWSTO {

	public ResultadoLinhaPUPTO() {
	}

	private static final long serialVersionUID = -1347032197801232386L;

	private LinhaPUPWSTO linhaPUPWSTO;

	private String cdRetorno;

	public String getCdRetorno() {
		return cdRetorno;
	}

	public Long idAtendimento;
	
	public void setCdRetorno(String cdRetorno) {
		this.cdRetorno = cdRetorno;
	}

	public LinhaPUPWSTO getLinhaPUPWSTO() {
		return linhaPUPWSTO;
	}

	public void setLinhaPUPWSTO(LinhaPUPWSTO linhaPUPWSTO) {
		this.linhaPUPWSTO = linhaPUPWSTO;
	}

	public Long getIdAtendimento() {
		return idAtendimento;
	}

	public void setIdAtendimento(Long idAtendimento) {
		this.idAtendimento = idAtendimento;
	}

}