package br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.delegate;

import java.rmi.RemoteException;
import javax.ejb.EJBException;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BaseBusinessDelegate;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.facade.interfaces.DesbloqueioGsmFacade;
import br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.facade.interfaces.DesbloqueioGsmFacadeHome;
import br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.facade.interfaces.DesbloqueioGsmFacadeLocal;
import br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.facade.interfaces.DesbloqueioGsmFacadeLocalHome;
import br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.to.DesbloqueioGsmTO;

public class DesbloqueioGsmBD extends BaseBusinessDelegate {

	public DesbloqueioGsmBD() {
	}

	public DesbloqueioGsmTO desbloquearAparelho(RequestInfoTO requestInfo, DesbloqueioGsmTO to) throws BusinessException, BusinessDelegateException {

	    DesbloqueioGsmTO result = null;
	    try {
			if(isFacadeLocal()){
				DesbloqueioGsmFacadeLocal local = (DesbloqueioGsmFacadeLocal) ServiceLocator.getInstance().getLocalFacade(DesbloqueioGsmFacadeLocalHome.JNDI_NAME);
				result = local.desbloquearAparelho(requestInfo, to);
			} else {
				DesbloqueioGsmFacade remote = (DesbloqueioGsmFacade) ServiceLocator.getInstance().getRemoteFacade(DesbloqueioGsmFacadeHome.JNDI_NAME,DesbloqueioGsmFacadeHome.class);
				result = remote.desbloquearAparelho(requestInfo, to);
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
