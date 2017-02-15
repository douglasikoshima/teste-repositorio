package br.com.indrasistemas.vivoservices.tracking.webservice.to;

import java.util.List;

import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class ResultadoOrdemVendaTO extends RespostaWSTO {

	/**
	 * 
	 */
	public ResultadoOrdemVendaTO() {

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1747032197896232386L;

	private List listaOrdemVenda;

	public List getListaOrdemVenda() {
		return listaOrdemVenda;
	}

	public void setListaOrdemVenda(List listaOrdemVendaTO) {
		this.listaOrdemVenda = listaOrdemVendaTO;
	}

}
