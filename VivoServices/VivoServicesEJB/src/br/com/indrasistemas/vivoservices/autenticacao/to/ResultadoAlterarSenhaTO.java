package br.com.indrasistemas.vivoservices.autenticacao.to;

import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class ResultadoAlterarSenhaTO extends RespostaWSTO {
	
	private static final long serialVersionUID = 1L;
	private String cdErro;
	private String dsErro;
	public String getCdErro() {
		return cdErro;
	}
	public void setCdErro(String cdErro) {
		this.cdErro = cdErro;
	}
	public String getDsErro() {
		return dsErro;
	}
	public void setDsErro(String dsErro) {
		this.dsErro = dsErro;
	}


	

}
