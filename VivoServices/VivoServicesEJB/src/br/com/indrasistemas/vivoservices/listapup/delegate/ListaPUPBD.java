package br.com.indrasistemas.vivoservices.listapup.delegate;

import java.rmi.RemoteException;
import javax.ejb.EJBException;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BaseBusinessDelegate;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.listapup.facade.interfaces.ListaPUPFacade;
import br.com.indrasistemas.vivoservices.listapup.facade.interfaces.ListaPUPFacadeHome;
import br.com.indrasistemas.vivoservices.listapup.facade.interfaces.ListaPUPFacadeLocal;
import br.com.indrasistemas.vivoservices.listapup.facade.interfaces.ListaPUPFacadeLocalHome;
import br.com.indrasistemas.vivoservices.listapup.to.LinhaPUPWSTO;
import br.com.indrasistemas.vivoservices.listapup.webservice.to.ResultadoLinhaPUPTO;

public class ListaPUPBD extends BaseBusinessDelegate {

	public ListaPUPBD() {
	}

	public ResultadoLinhaPUPTO gravarLinhaPUP(RequestInfoTO requestInfo,
			LinhaPUPWSTO to) throws BusinessException,
			BusinessDelegateException {
		ResultadoLinhaPUPTO result = null;

		try {
			if (isFacadeLocal()) {
				ListaPUPFacadeLocal local = (ListaPUPFacadeLocal) ServiceLocator
						.getInstance().getLocalFacade(
								ListaPUPFacadeLocalHome.JNDI_NAME);

				result = local.gravarLinhaPUP(requestInfo, to);
			} else {
				ListaPUPFacade remote = (ListaPUPFacade) ServiceLocator
						.getInstance().getRemoteFacade(
								ListaPUPFacadeHome.JNDI_NAME,
								ListaPUPFacadeHome.class);

				result = remote.gravarLinhaPUP(requestInfo, to);
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

	public ResultadoLinhaPUPTO consultarLinhaPUP(RequestInfoTO requestInfo,
			LinhaPUPWSTO to) throws BusinessException,
			BusinessDelegateException {

		ResultadoLinhaPUPTO result = null;

		try {
			if (isFacadeLocal()) {
				ListaPUPFacadeLocal local = (ListaPUPFacadeLocal) ServiceLocator
						.getInstance().getLocalFacade(
								ListaPUPFacadeLocalHome.JNDI_NAME);

				result = local.consultarLinhaPUP(requestInfo, to);
			} else {
				ListaPUPFacade remote = (ListaPUPFacade) ServiceLocator
						.getInstance().getRemoteFacade(
								ListaPUPFacadeHome.JNDI_NAME,
								ListaPUPFacadeHome.class);

				result = remote.consultarLinhaPUP(requestInfo, to);
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
