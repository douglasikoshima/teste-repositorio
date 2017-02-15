package br.com.indrasistemas.vivoservices.tracking.valuehandler;

import br.com.indrasistemas.framework.service.valuehandler.PaginatedCriteria;
import br.com.indrasistemas.vivoservices.tracking.to.DetalhePedidoTO;

public class TrackingAparelhosDetalhesPedidoCriteria extends PaginatedCriteria {

	DetalhePedidoTO filtro;

	public TrackingAparelhosDetalhesPedidoCriteria(Integer firstResult, Integer maxResults) {
		super(firstResult, maxResults);
	}

	public DetalhePedidoTO getFiltro() {
		return filtro;
	}

	public void setFiltro(DetalhePedidoTO filtro) {
		this.filtro = filtro;
	}

}
