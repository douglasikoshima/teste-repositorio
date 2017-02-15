package br.com.indrasistemas.vivoservices.atendimento.palitagem.delegate;

import java.rmi.RemoteException;
import javax.ejb.EJBException;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BaseBusinessDelegate;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.facade.interfaces.PalitagemFacade;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.facade.interfaces.PalitagemFacadeHome;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.facade.interfaces.PalitagemFacadeLocal;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.facade.interfaces.PalitagemFacadeLocalHome;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.to.PalitagemTO;

public class PalitagemBD extends BaseBusinessDelegate {

	public PalitagemBD() {
	}

	public PalitagemTO registrarPalito(RequestInfoTO requestInfo, PalitagemTO to) throws BusinessException, BusinessDelegateException {
	    PalitagemTO result = null;
		try {
			if (isFacadeLocal()) {
				PalitagemFacadeLocal local = (PalitagemFacadeLocal) ServiceLocator.getInstance().getLocalFacade(PalitagemFacadeLocalHome.JNDI_NAME);
				result = local.registrarPalito(requestInfo, to);
			} else {
				PalitagemFacade remote = (PalitagemFacade) ServiceLocator.getInstance().getRemoteFacade(PalitagemFacadeHome.JNDI_NAME,PalitagemFacadeHome.class);
				result = remote.registrarPalito(requestInfo, to);
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
