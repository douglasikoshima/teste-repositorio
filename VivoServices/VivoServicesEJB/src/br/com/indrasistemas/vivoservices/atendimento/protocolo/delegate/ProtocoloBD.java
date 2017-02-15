package br.com.indrasistemas.vivoservices.atendimento.protocolo.delegate;

import java.rmi.RemoteException;
import javax.ejb.EJBException;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BaseBusinessDelegate;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.facade.interfaces.ProtocoloFacade;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.facade.interfaces.ProtocoloFacadeHome;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.facade.interfaces.ProtocoloFacadeLocal;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.facade.interfaces.ProtocoloFacadeLocalHome;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.to.ProtocoloTO;

public class ProtocoloBD extends BaseBusinessDelegate {

	public ProtocoloBD() {
	}

	public ProtocoloTO aberturaProtocolo(RequestInfoTO requestInfo, ProtocoloTO to) throws BusinessException, BusinessDelegateException {
	    ProtocoloTO result = null;
		try {
			if (isFacadeLocal()) {
				ProtocoloFacadeLocal local = (ProtocoloFacadeLocal) ServiceLocator.getInstance().getLocalFacade(ProtocoloFacadeLocalHome.JNDI_NAME);
				result = local.aberturaProtocolo(requestInfo, to);
			} else {
				ProtocoloFacade remote = (ProtocoloFacade) ServiceLocator.getInstance().getRemoteFacade(ProtocoloFacadeHome.JNDI_NAME, ProtocoloFacadeHome.class);
				result = remote.aberturaProtocolo(requestInfo, to);
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

    public ProtocoloTO fechamentoProtocolo(RequestInfoTO requestInfo, ProtocoloTO to) throws BusinessException, BusinessDelegateException {
        ProtocoloTO result = null;
        try {
            if (isFacadeLocal()) {
                ProtocoloFacadeLocal local = (ProtocoloFacadeLocal) ServiceLocator.getInstance().getLocalFacade(ProtocoloFacadeLocalHome.JNDI_NAME);
                result = local.fechamentoProtocolo(requestInfo, to);
            } else {
                ProtocoloFacade remote = (ProtocoloFacade) ServiceLocator.getInstance().getRemoteFacade(ProtocoloFacadeHome.JNDI_NAME, ProtocoloFacadeHome.class);
                result = remote.fechamentoProtocolo(requestInfo, to);
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
