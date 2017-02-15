package br.com.indrasistemas.vivoservices.tracking.delegate;

import java.rmi.RemoteException;

import javax.ejb.EJBException;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BaseBusinessDelegate;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.framework.service.valuehandler.ValueList;
import br.com.indrasistemas.vivoservices.tracking.facade.interfaces.TrackingAparelhosFacade;
import br.com.indrasistemas.vivoservices.tracking.facade.interfaces.TrackingAparelhosFacadeHome;
import br.com.indrasistemas.vivoservices.tracking.facade.interfaces.TrackingAparelhosFacadeLocal;
import br.com.indrasistemas.vivoservices.tracking.facade.interfaces.TrackingAparelhosFacadeLocalHome;
import br.com.indrasistemas.vivoservices.tracking.to.DetalhePedidoTO;
import br.com.indrasistemas.vivoservices.tracking.to.PedidoTO;

public class TrackingAparelhosBD extends BaseBusinessDelegate {

	public TrackingAparelhosBD() {
	}

	public ValueList buscarListaPedidos(RequestInfoTO requestInfo,
			PedidoTO to, Integer pagina, Integer qtdeRegistros)
			throws BusinessException, BusinessDelegateException {
		ValueList result = null;

		try {
			if (isFacadeLocal()) {
				TrackingAparelhosFacadeLocal local = (TrackingAparelhosFacadeLocal) ServiceLocator
						.getInstance()
						.getLocalFacade(
								TrackingAparelhosFacadeLocalHome.JNDI_NAME);

				result = local.buscarListaPedidos(requestInfo, to,
						pagina, qtdeRegistros);
			} else {
				TrackingAparelhosFacade remote = (TrackingAparelhosFacade) ServiceLocator
						.getInstance().getRemoteFacade(
								TrackingAparelhosFacadeHome.JNDI_NAME,
								TrackingAparelhosFacadeHome.class);

				result = remote.buscarListaPedidos(requestInfo, to,
						pagina, qtdeRegistros);
			}
		} catch (ServiceLocatorException slex) {
			throw new BusinessDelegateException(slex);

		} catch (EJBException ejbx) {
			throw new BusinessDelegateException(ejbx);

		} catch (RemoteException slex) {
			throw new BusinessDelegateException(slex);
		}

		return result;
	}
	
	public ValueList buscarDetalhesPedido(RequestInfoTO requestInfo,
			DetalhePedidoTO to, Integer pagina, Integer qtdeRegistros)
			throws BusinessException, BusinessDelegateException {
		ValueList result = null;

		try {
			if (isFacadeLocal()) {
				TrackingAparelhosFacadeLocal local = (TrackingAparelhosFacadeLocal) ServiceLocator
						.getInstance()
						.getLocalFacade(
								TrackingAparelhosFacadeLocalHome.JNDI_NAME);

				result = local.buscarDetalhesPedido(requestInfo, to,
						pagina, qtdeRegistros);
			} else {
				TrackingAparelhosFacade remote = (TrackingAparelhosFacade) ServiceLocator
						.getInstance().getRemoteFacade(
								TrackingAparelhosFacadeHome.JNDI_NAME,
								TrackingAparelhosFacadeHome.class);

				result = remote.buscarDetalhesPedido(requestInfo, to,
						pagina, qtdeRegistros);
			}
		} catch (ServiceLocatorException slex) {
			throw new BusinessDelegateException(slex);

		} catch (EJBException ejbx) {
			throw new BusinessDelegateException(ejbx);

		} catch (RemoteException slex) {
			throw new BusinessDelegateException(slex);
		}

		return result;
	}
	
}
