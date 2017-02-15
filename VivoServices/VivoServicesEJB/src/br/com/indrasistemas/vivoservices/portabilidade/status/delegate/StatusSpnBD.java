package br.com.indrasistemas.vivoservices.portabilidade.status.delegate;

import java.rmi.RemoteException;

import javax.ejb.EJBException;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BaseBusinessDelegate;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.portabilidade.status.facade.interfaces.StatusSpnFacade;
import br.com.indrasistemas.vivoservices.portabilidade.status.facade.interfaces.StatusSpnFacadeHome;
import br.com.indrasistemas.vivoservices.portabilidade.status.facade.interfaces.StatusSpnFacadeLocal;
import br.com.indrasistemas.vivoservices.portabilidade.status.facade.interfaces.StatusSpnFacadeLocalHome;
import br.com.indrasistemas.vivoservices.portabilidade.status.to.RespostaTO;

public class StatusSpnBD extends BaseBusinessDelegate {

	public StatusSpnBD() {
	}

	public RespostaTO validaStatusSpn(RequestInfoTO requestInfo, String nrLinha) throws BusinessException, BusinessDelegateException {

		RespostaTO result = null;

		try {
			if (isFacadeLocal()) {
				StatusSpnFacadeLocal local = (StatusSpnFacadeLocal) ServiceLocator.getInstance().getLocalFacade(StatusSpnFacadeLocalHome.JNDI_NAME);
				result = local.validaStatusSpn(requestInfo, nrLinha);

			} else {
				StatusSpnFacade remote = (StatusSpnFacade) ServiceLocator.getInstance().getRemoteFacade(StatusSpnFacadeHome.JNDI_NAME, StatusSpnFacadeHome.class);
				result = remote.validaStatusSpn(requestInfo, nrLinha);
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
