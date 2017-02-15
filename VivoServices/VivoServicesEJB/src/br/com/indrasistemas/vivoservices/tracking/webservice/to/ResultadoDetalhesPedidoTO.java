package br.com.indrasistemas.vivoservices.tracking.webservice.to;

import java.util.List;

import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class ResultadoDetalhesPedidoTO extends RespostaWSTO {

	/**
	 * 
	 */
	public ResultadoDetalhesPedidoTO() {

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1747032197896232386L;

	private List listaDetalhesPedido;

	public List getListaDetalhesPedido() {
		return listaDetalhesPedido;
	}

	public void setListaPedidos(List listaDetalhePedidosTO) {
		this.listaDetalhesPedido = listaDetalhePedidosTO;
	}

}
