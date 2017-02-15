package br.com.indrasistemas.vivoservices.tracking.webservice;

import br.com.indrasistemas.vivoservices.tracking.webservice.to.ResultadoDetalhesPedidoTO;
import br.com.indrasistemas.vivoservices.webservice.to.RequestInfoWSTO;

/**
 * 
 */
public interface BuscarDetalhesPedidoTrackingAparelhosWS {

	public ResultadoDetalhesPedidoTO buscarDetalhesPedidoPorDocumento(
			RequestInfoWSTO requestInfo, Long nrDocumento, Long nrPedido,
			Long nrNotaFiscal, Integer pagina, Integer qtdeRegistros);

}
