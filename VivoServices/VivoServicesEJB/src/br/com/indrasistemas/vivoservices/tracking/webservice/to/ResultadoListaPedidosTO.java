package br.com.indrasistemas.vivoservices.tracking.webservice.to;

import java.util.List;

import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class ResultadoListaPedidosTO extends RespostaWSTO {

	/**
	 * 
	 */
	public ResultadoListaPedidosTO() {

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1747032197896232386L;

	private List listaPedidos;

	public List getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(List listaPedidosTO) {
		this.listaPedidos = listaPedidosTO;
	}

}
