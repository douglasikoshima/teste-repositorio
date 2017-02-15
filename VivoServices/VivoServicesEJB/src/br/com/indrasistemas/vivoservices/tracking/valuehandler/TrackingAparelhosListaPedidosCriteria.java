package br.com.indrasistemas.vivoservices.tracking.valuehandler;

import br.com.indrasistemas.framework.service.valuehandler.PaginatedCriteria;
import br.com.indrasistemas.vivoservices.tracking.to.PedidoTO;

public class TrackingAparelhosListaPedidosCriteria extends PaginatedCriteria {

	PedidoTO filtro;

	public TrackingAparelhosListaPedidosCriteria(Integer firstResult, Integer maxResults) {
		super(firstResult, maxResults);
	}

	public PedidoTO getFiltro() {
		return filtro;
	}

	public void setFiltro(PedidoTO filtro) {
		this.filtro = filtro;
	}

}
