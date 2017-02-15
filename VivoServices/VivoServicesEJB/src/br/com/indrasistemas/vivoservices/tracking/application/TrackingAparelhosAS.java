package br.com.indrasistemas.vivoservices.tracking.application;

import javax.ejb.SessionContext;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.application.ApplicationServiceException;
import br.com.indrasistemas.framework.service.application.BaseApplicationService;
import br.com.indrasistemas.framework.service.valuehandler.ValueList;
import br.com.indrasistemas.framework.service.valuehandler.ValueListHandlerException;
import br.com.indrasistemas.vivoservices.tracking.to.DetalhePedidoTO;
import br.com.indrasistemas.vivoservices.tracking.to.PedidoTO;
import br.com.indrasistemas.vivoservices.tracking.valuehandler.TrackingAparelhosDetalhesPedidoCriteria;
import br.com.indrasistemas.vivoservices.tracking.valuehandler.TrackingAparelhosDetalhesPedidoVLH;
import br.com.indrasistemas.vivoservices.tracking.valuehandler.TrackingAparelhosListaPedidosCriteria;
import br.com.indrasistemas.vivoservices.tracking.valuehandler.TrackingAparelhosListaPedidosVLH;

public class TrackingAparelhosAS extends BaseApplicationService {

	public TrackingAparelhosAS(SessionContext sessionContext) {
		super(sessionContext);
	}

	public ValueList buscarListaPedidos(PedidoTO filtro, Integer pagina,
			Integer qtdeRegistros) throws ApplicationServiceException,
			BusinessException {
		ValueList result = null;
		int first = 0;
		int max = PedidoTO.RESULTADO_MAXIMO;

		if (pagina != null) {
			first = ((pagina.intValue() - 1) * PedidoTO.RESULTADO_MAXIMO);
		}
		if (qtdeRegistros != null) {
			max = first + qtdeRegistros.intValue();
		}

		if (max - first > PedidoTO.RESULTADO_MAXIMO) {
			max = first + PedidoTO.RESULTADO_MAXIMO;
		}
		
		try {

			TrackingAparelhosListaPedidosCriteria criteria = new TrackingAparelhosListaPedidosCriteria(
					new Integer(first), new Integer(max));
			criteria.setFiltro(filtro);
			TrackingAparelhosListaPedidosVLH vlh = new TrackingAparelhosListaPedidosVLH(criteria);
			vlh.ignoreCache();
			result = vlh.getList();
		} catch (ValueListHandlerException ex) {
			getSessionContext().setRollbackOnly();
			throw new ApplicationServiceException(ex);
		}
		return result;
	}

	
	public ValueList buscarDetalhesPedido(DetalhePedidoTO filtro, Integer pagina,
			Integer qtdeRegistros)
			throws ApplicationServiceException, BusinessException {
		ValueList result = null;
		int first = 0;
		int max = DetalhePedidoTO.RESULTADO_MAXIMO;

		try {

			TrackingAparelhosDetalhesPedidoCriteria criteria = new TrackingAparelhosDetalhesPedidoCriteria(
					new Integer(first), new Integer(max));
			criteria.setFiltro(filtro);
			TrackingAparelhosDetalhesPedidoVLH vlh = new TrackingAparelhosDetalhesPedidoVLH(criteria);
			vlh.ignoreCache();
			result = vlh.getList();
		} catch (ValueListHandlerException ex) {
			getSessionContext().setRollbackOnly();
			throw new ApplicationServiceException(ex);
		}
		return result;
	}

}
