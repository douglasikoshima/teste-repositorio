package br.com.indrasistemas.vivoservices.atendimento.ordemvenda.delegate;

import java.rmi.RemoteException;
import javax.ejb.EJBException;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BaseBusinessDelegate;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.atendimento.ordemvenda.facade.interfaces.ManterOrdemVendaFacade;
import br.com.indrasistemas.vivoservices.atendimento.ordemvenda.facade.interfaces.ManterOrdemVendaFacadeHome;
import br.com.indrasistemas.vivoservices.atendimento.ordemvenda.facade.interfaces.ManterOrdemVendaFacadeLocal;
import br.com.indrasistemas.vivoservices.atendimento.ordemvenda.facade.interfaces.ManterOrdemVendaFacadeLocalHome;
import br.com.indrasistemas.vivoservices.atendimento.ordemvenda.to.PalitagemTO;

public class ManterOrdemVendaBD extends BaseBusinessDelegate {

	public ManterOrdemVendaBD() {
	}

	public PalitagemTO registrarPalito(RequestInfoTO requestInfo, PalitagemTO to) throws BusinessException, BusinessDelegateException {
	    PalitagemTO result = null;
		try {
			if (isFacadeLocal()) {
				ManterOrdemVendaFacadeLocal local = (ManterOrdemVendaFacadeLocal) ServiceLocator.getInstance().getLocalFacade(ManterOrdemVendaFacadeLocalHome.JNDI_NAME);
				result = local.registrarPalito(requestInfo, to);
			} else {
				ManterOrdemVendaFacade remote = (ManterOrdemVendaFacade) ServiceLocator.getInstance().getRemoteFacade(ManterOrdemVendaFacadeHome.JNDI_NAME,ManterOrdemVendaFacadeHome.class);
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
