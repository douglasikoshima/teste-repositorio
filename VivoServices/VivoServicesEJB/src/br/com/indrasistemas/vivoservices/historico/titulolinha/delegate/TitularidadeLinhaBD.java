package br.com.indrasistemas.vivoservices.historico.titulolinha.delegate;

import java.rmi.RemoteException;

import javax.ejb.EJBException;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BaseBusinessDelegate;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.historico.titulolinha.facade.interfaces.TitularidadeLinhaFacade;
import br.com.indrasistemas.vivoservices.historico.titulolinha.facade.interfaces.TitularidadeLinhaFacadeHome;
import br.com.indrasistemas.vivoservices.historico.titulolinha.facade.interfaces.TitularidadeLinhaFacadeLocal;
import br.com.indrasistemas.vivoservices.historico.titulolinha.facade.interfaces.TitularidadeLinhaFacadeLocalHome;
import br.com.indrasistemas.vivoservices.historico.titulolinha.to.ParametrosTO;
import br.com.indrasistemas.vivoservices.historico.titulolinha.to.TitularidadeLinhaTO;

public class TitularidadeLinhaBD extends BaseBusinessDelegate {

    public TitularidadeLinhaBD() {
    }

    public TitularidadeLinhaTO pesquisarTitularidadeLinha(RequestInfoTO requestInfo, ParametrosTO to) throws BusinessException, BusinessDelegateException {

	TitularidadeLinhaTO result = null;
	try {
	    if (isFacadeLocal()) {
		TitularidadeLinhaFacadeLocal local = (TitularidadeLinhaFacadeLocal) ServiceLocator
			.getInstance().getLocalFacade(
				TitularidadeLinhaFacadeLocalHome.JNDI_NAME);
		result = local.pesquisarTitularidadeLinha(requestInfo, to);

	    } else {
		TitularidadeLinhaFacade remote = (TitularidadeLinhaFacade) ServiceLocator
			.getInstance().getRemoteFacade(
				TitularidadeLinhaFacadeHome.JNDI_NAME,
				TitularidadeLinhaFacadeHome.class);
		result = remote.pesquisarTitularidadeLinha(requestInfo, to);
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
