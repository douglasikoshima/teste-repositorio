package br.com.indrasistemas.vivoservices.administracaosistema.endereco.delegate;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.EJBException;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BaseBusinessDelegate;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.framework.service.valuehandler.ValueList;
import br.com.indrasistemas.vivoservices.administracaosistema.endereco.facade.interfaces.PesquisaEnderecoFacade;
import br.com.indrasistemas.vivoservices.administracaosistema.endereco.facade.interfaces.PesquisaEnderecoFacadeHome;
import br.com.indrasistemas.vivoservices.administracaosistema.endereco.facade.interfaces.PesquisaEnderecoFacadeLocal;
import br.com.indrasistemas.vivoservices.administracaosistema.endereco.facade.interfaces.PesquisaEnderecoFacadeLocalHome;
import br.com.indrasistemas.vivoservices.administracaosistema.endereco.to.PesquisaEnderecoTO;

public class PesquisaEnderecoBD extends BaseBusinessDelegate {

	public PesquisaEnderecoBD() {
	}

	public ValueList buscarEndereco(RequestInfoTO requestInfo, PesquisaEnderecoTO to, Integer pagina, Integer qtdeRegistros)
			throws BusinessException, BusinessDelegateException {
		ValueList result = null;

		try {
			if (isFacadeLocal()) {
				PesquisaEnderecoFacadeLocal local = (PesquisaEnderecoFacadeLocal) ServiceLocator
						.getInstance().getLocalFacade(
								PesquisaEnderecoFacadeLocalHome.JNDI_NAME);

				result = local.buscarEndereco(requestInfo, to, pagina, qtdeRegistros);
			} else {
				PesquisaEnderecoFacade remote = (PesquisaEnderecoFacade) ServiceLocator
						.getInstance().getRemoteFacade(
								PesquisaEnderecoFacadeHome.JNDI_NAME,
								PesquisaEnderecoFacadeHome.class);

				result = remote.buscarEndereco(requestInfo, to, pagina, qtdeRegistros);
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

	public List buscarUFs(RequestInfoTO requestInfo) throws BusinessException,
			BusinessDelegateException {
		List result = null;

		try {
			if (isFacadeLocal()) {
				PesquisaEnderecoFacadeLocal local = (PesquisaEnderecoFacadeLocal) ServiceLocator
						.getInstance().getLocalFacade(
								PesquisaEnderecoFacadeLocalHome.JNDI_NAME);

				result = local.buscarUFs(requestInfo);
			} else {
				PesquisaEnderecoFacade remote = (PesquisaEnderecoFacade) ServiceLocator
						.getInstance().getRemoteFacade(
								PesquisaEnderecoFacadeHome.JNDI_NAME,
								PesquisaEnderecoFacadeHome.class);

				result = remote.buscarUFs(requestInfo);
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
