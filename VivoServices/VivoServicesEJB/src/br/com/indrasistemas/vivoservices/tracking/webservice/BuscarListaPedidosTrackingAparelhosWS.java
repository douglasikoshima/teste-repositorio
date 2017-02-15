package br.com.indrasistemas.vivoservices.tracking.webservice;

import java.util.Date;

import br.com.indrasistemas.vivoservices.tracking.webservice.to.ResultadoListaPedidosTO;
import br.com.indrasistemas.vivoservices.webservice.to.RequestInfoWSTO;

/**
 * 
 */
public interface BuscarListaPedidosTrackingAparelhosWS {

	public ResultadoListaPedidosTO buscarListaPedidosPorDocumento(
			RequestInfoWSTO requestInfo, Long nrDocumento, Date dataInicial,
			Date dataFinal, Integer pagina, Integer qtdeRegistros, Long nrPedido);
}
