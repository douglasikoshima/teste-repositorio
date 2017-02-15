package br.com.indrasistemas.vivoservices.portabilidade.programapontos.delegate;

import java.rmi.RemoteException;

import javax.ejb.EJBException;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BaseBusinessDelegate;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.portabilidade.programapontos.facade.interfaces.ConsultarPortabilidadeFacade;
import br.com.indrasistemas.vivoservices.portabilidade.programapontos.facade.interfaces.ConsultarPortabilidadeFacadeHome;
import br.com.indrasistemas.vivoservices.portabilidade.programapontos.facade.interfaces.ConsultarPortabilidadeFacadeLocal;
import br.com.indrasistemas.vivoservices.portabilidade.programapontos.facade.interfaces.ConsultarPortabilidadeFacadeLocalHome;
import br.com.indrasistemas.vivoservices.portabilidade.programapontos.webservice.to.RespostaStatusPortabilidadeTO;

public class ConsultarPortabilidadeBD extends BaseBusinessDelegate {

	public ConsultarPortabilidadeBD() {
	}

	public RespostaStatusPortabilidadeTO consultarStatusPortabilidade(RequestInfoTO requestInfo, String nrLinha) throws BusinessException, BusinessDelegateException {
		RespostaStatusPortabilidadeTO result = null;
		try {
			if (isFacadeLocal()) {
				ConsultarPortabilidadeFacadeLocal local = (ConsultarPortabilidadeFacadeLocal) ServiceLocator
						.getInstance()
						.getLocalFacade(ConsultarPortabilidadeFacadeLocalHome.JNDI_NAME);
				result = local.consultarStatusPortabilidade(requestInfo, nrLinha);
			} else {
				ConsultarPortabilidadeFacade remote = (ConsultarPortabilidadeFacade) ServiceLocator
						.getInstance()
						.getRemoteFacade(
								ConsultarPortabilidadeFacadeHome.JNDI_NAME,
								ConsultarPortabilidadeFacadeHome.class);
				result = remote.consultarStatusPortabilidade(requestInfo, nrLinha);
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
