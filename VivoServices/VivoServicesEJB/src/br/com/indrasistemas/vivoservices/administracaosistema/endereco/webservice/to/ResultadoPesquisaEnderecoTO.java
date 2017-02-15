package br.com.indrasistemas.vivoservices.administracaosistema.endereco.webservice.to;

import java.util.List;

import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class ResultadoPesquisaEnderecoTO extends RespostaWSTO {

	/**
	 * 
	 */
	public ResultadoPesquisaEnderecoTO() {

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1747032197896232386L;

	private List enderecos;

	public List getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List enderecoTO) {
		this.enderecos = enderecoTO;
	}

}
