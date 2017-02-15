package br.com.indrasistemas.vivoservices.vole.contato.delegate;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.EJBException;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BaseBusinessDelegate;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.vole.contato.facade.interfaces.PesquisaPontoContatoFacade;
import br.com.indrasistemas.vivoservices.vole.contato.facade.interfaces.PesquisaPontoContatoFacadeHome;
import br.com.indrasistemas.vivoservices.vole.contato.facade.interfaces.PesquisaPontoContatoFacadeLocal;
import br.com.indrasistemas.vivoservices.vole.contato.facade.interfaces.PesquisaPontoContatoFacadeLocalHome;
import br.com.indrasistemas.vivoservices.vole.contato.to.PontoContatoTO;

public class PesquisaPontoContatoBD extends BaseBusinessDelegate {

	public PesquisaPontoContatoBD() {
	}

	public List buscarPontoContato(RequestInfoTO requestInfo, PontoContatoTO to, Integer pagina, Integer qtdeRegistros)
			throws BusinessException, BusinessDelegateException {
		List result = null;

		try {
			if (isFacadeLocal()) {
				PesquisaPontoContatoFacadeLocal local = (PesquisaPontoContatoFacadeLocal) ServiceLocator
						.getInstance().getLocalFacade(
								PesquisaPontoContatoFacadeLocalHome.JNDI_NAME);

				result = local.buscarPontoContato(requestInfo, to, pagina, qtdeRegistros);
			} else {
				PesquisaPontoContatoFacade remote = (PesquisaPontoContatoFacade) ServiceLocator
						.getInstance().getRemoteFacade(
								PesquisaPontoContatoFacadeHome.JNDI_NAME,
								PesquisaPontoContatoFacadeHome.class);

				result = remote.buscarPontoContato(requestInfo, to, pagina, qtdeRegistros);

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
