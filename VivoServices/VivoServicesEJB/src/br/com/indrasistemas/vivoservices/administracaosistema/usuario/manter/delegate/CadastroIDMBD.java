package br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.delegate;

import java.rmi.RemoteException;

import javax.ejb.EJBException;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BaseBusinessDelegate;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.facade.interfaces.CadastroIDMFacade;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.facade.interfaces.CadastroIDMFacadeHome;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.facade.interfaces.CadastroIDMFacadeLocal;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.facade.interfaces.CadastroIDMFacadeLocalHome;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.to.ParametrosTO;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.to.RelacionaUsuarioTO;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.to.RespostaTO;

public class CadastroIDMBD extends BaseBusinessDelegate {

	public CadastroIDMBD() {
	}

	public RespostaTO cadastrar(RequestInfoTO requestInfo, ParametrosTO parametros) throws BusinessException, BusinessDelegateException {

		RespostaTO result = null;
		try {
			if (isFacadeLocal()) {
				CadastroIDMFacadeLocal local = (CadastroIDMFacadeLocal) ServiceLocator.getInstance().getLocalFacade(CadastroIDMFacadeLocalHome.JNDI_NAME);
				result = local.cadastrar(requestInfo, parametros);

			} else {
				CadastroIDMFacade remote = (CadastroIDMFacade) ServiceLocator.getInstance().getRemoteFacade(CadastroIDMFacadeHome.JNDI_NAME,CadastroIDMFacadeHome.class);
				result = remote.cadastrar(requestInfo, parametros);
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

	public RespostaTO relacionarUsuarioIdm(RequestInfoTO requestInfo, RelacionaUsuarioTO parametros) throws BusinessException, BusinessDelegateException {
		RespostaTO result = null;
		try {
			if (isFacadeLocal()) {
				CadastroIDMFacadeLocal local = (CadastroIDMFacadeLocal) ServiceLocator.getInstance().getLocalFacade(CadastroIDMFacadeLocalHome.JNDI_NAME);
				result = local.relacionarUsuarioIdm(requestInfo, parametros);

			} else {
				CadastroIDMFacade remote = (CadastroIDMFacade) ServiceLocator.getInstance().getRemoteFacade(CadastroIDMFacadeHome.JNDI_NAME,CadastroIDMFacadeHome.class);
				result = remote.relacionarUsuarioIdm(requestInfo, parametros);
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

	public RespostaTO ativarUsuarioIdm(RequestInfoTO requestInfo, ParametrosTO parametros) throws BusinessException, BusinessDelegateException {
		RespostaTO result = null;
		try {
			if (isFacadeLocal()) {
				CadastroIDMFacadeLocal local = (CadastroIDMFacadeLocal) ServiceLocator.getInstance().getLocalFacade(CadastroIDMFacadeLocalHome.JNDI_NAME);
				result = local.ativarUsuarioIdm(requestInfo, parametros);

			} else {
				CadastroIDMFacade remote = (CadastroIDMFacade) ServiceLocator.getInstance().getRemoteFacade(CadastroIDMFacadeHome.JNDI_NAME,CadastroIDMFacadeHome.class);
				result = remote.ativarUsuarioIdm(requestInfo, parametros);
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
