package br.com.indrasistemas.vivoservices.tracking.webservice.to;

import java.util.List;

import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class ResultadoEtapaPedidoTO extends RespostaWSTO {

	/**
	 * 
	 */
	public ResultadoEtapaPedidoTO() {

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1747032197896232386L;

	private List listaEtapaPedido;

	public List getListaEtapaPedido() {
		return listaEtapaPedido;
	}

	public void setEtapaPedido(List listaEtapaPedidoTO) {
		this.listaEtapaPedido = listaEtapaPedidoTO;
	}

}
