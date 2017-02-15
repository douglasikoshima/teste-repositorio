/**
 * 
 */
package br.com.indrasistemas.vivoservices.tracking.webservice.impl;

import java.util.Date;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.valuehandler.ValueList;
import br.com.indrasistemas.vivoservices.tracking.delegate.TrackingAparelhosBD;
import br.com.indrasistemas.vivoservices.tracking.to.PedidoTO;
import br.com.indrasistemas.vivoservices.tracking.webservice.BuscarListaPedidosTrackingAparelhosWS;
import br.com.indrasistemas.vivoservices.tracking.webservice.to.ResultadoListaPedidosTO;
import br.com.indrasistemas.vivoservices.webservice.to.RequestInfoWSTO;
import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

/**
 * @author c_cgarcia
 * 
 */
public class BuscarListaPedidosTrackingAparelhosWSImpl implements
		BuscarListaPedidosTrackingAparelhosWS {

	/**
	 * 
	 */
	public BuscarListaPedidosTrackingAparelhosWSImpl() {

	}

	public ResultadoListaPedidosTO buscarListaPedidosPorDocumento(
			RequestInfoWSTO requestInfo, Long nrDocumento, Date dataInicial,
			Date dataFinal, Integer pagina, Integer qtdeRegistros,
			Long nrPedido) {
		ResultadoListaPedidosTO to = new ResultadoListaPedidosTO();

		try {

			PedidoTO toPesquisa = new PedidoTO();
			toPesquisa.setNrDocumento(nrDocumento);
			toPesquisa.setDataInicial(dataInicial);
			toPesquisa.setDataFinal(dataFinal);
			
			if ( nrPedido != null ) {
				toPesquisa.setIdPedido(nrPedido);
			}

			TrackingAparelhosBD bd = new TrackingAparelhosBD();
			ValueList listaPedidos = bd.buscarListaPedidos(requestInfo,
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
