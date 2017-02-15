/**
 * 
 */
package br.com.indrasistemas.vivoservices.tracking.webservice.impl;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.valuehandler.ValueList;
import br.com.indrasistemas.vivoservices.tracking.delegate.TrackingAparelhosBD;
import br.com.indrasistemas.vivoservices.tracking.to.DetalhePedidoTO;
import br.com.indrasistemas.vivoservices.tracking.webservice.BuscarDetalhesPedidoTrackingAparelhosWS;
import br.com.indrasistemas.vivoservices.tracking.webservice.to.ResultadoDetalhesPedidoTO;
import br.com.indrasistemas.vivoservices.webservice.to.RequestInfoWSTO;
import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

/**
 * @author c_cgarcia
 * 
 */
public class BuscarDetalhesPedidoTrackingAparelhosWSImpl implements
		BuscarDetalhesPedidoTrackingAparelhosWS {

	/**
	 * 
	 */
	public BuscarDetalhesPedidoTrackingAparelhosWSImpl() {

	}

	public ResultadoDetalhesPedidoTO buscarDetalhesPedidoPorDocumento(
			RequestInfoWSTO requestInfo, Long nrDocumento, Long nrPedido,
			Long nrNotaFiscal, Integer pagina, Integer qtdeRegistros) {
		ResultadoDetalhesPedidoTO to = new ResultadoDetalhesPedidoTO();

		try {

			DetalhePedidoTO toPesquisa = new DetalhePedidoTO();
			toPesquisa.setNrDocumento(nrDocumento);
			toPesquisa.setIdPedido(nrPedido);
			toPesquisa.setNrNotaFiscal(nrNotaFiscal);

			TrackingAparelhosBD bd = new TrackingAparelhosBD();
			ValueList listaPedidos = bd.buscarDetalhesPedido(requestInfo,
					toPesquisa, pagina, qtdeRegistros);

			to.setStatus(RespostaWSTO.OK);
			to.setListaPedidos(listaPedidos.getList());

		} catch (BusinessDelegateException e) {
			to.setStatus(RespostaWSTO.NAO_OK);
			to.setReason(e.getMessage());

		} catch (BusinessException e) {
			to.setStatus(RespostaWSTO.NAO_OK);
			to.setReason(e.getMessage());
		}

		return to;
	}

}
