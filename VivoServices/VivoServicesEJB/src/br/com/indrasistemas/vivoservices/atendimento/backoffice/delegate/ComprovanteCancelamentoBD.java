package br.com.indrasistemas.vivoservices.atendimento.backoffice.delegate;

import java.rmi.RemoteException;

import javax.ejb.EJBException;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BaseBusinessDelegate;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.framework.service.valuehandler.ValueList;
import br.com.indrasistemas.vivoservices.atendimento.backoffice.facade.interfaces.ComprovanteCancelamentoFacade;
import br.com.indrasistemas.vivoservices.atendimento.backoffice.facade.interfaces.ComprovanteCancelamentoFacadeHome;
import br.com.indrasistemas.vivoservices.atendimento.backoffice.facade.interfaces.ComprovanteCancelamentoFacadeLocal;
import br.com.indrasistemas.vivoservices.atendimento.backoffice.facade.interfaces.ComprovanteCancelamentoFacadeLocalHome;
import br.com.indrasistemas.vivoservices.atendimento.backoffice.to.ComprovanteCancelamentoTO;

public class ComprovanteCancelamentoBD extends BaseBusinessDelegate {

	public ComprovanteCancelamentoBD() {
	}

	public ValueList buscarComprovanteCancelamento(RequestInfoTO requestInfo,
			ComprovanteCancelamentoTO to, Integer pagina, Integer qtdeRegistros)
			throws BusinessException, BusinessDelegateException {
		ValueList result = null;

		try {
			if (isFacadeLocal()) {
				ComprovanteCancelamentoFacadeLocal local = (ComprovanteCancelamentoFacadeLocal) ServiceLocator
						.getInstance()
						.getLocalFacade(
								ComprovanteCancelamentoFacadeLocalHome.JNDI_NAME);

				result = local.buscarComprovanteCancelamento(requestInfo, to,
						pagina, qtdeRegistros);
			} else {
				ComprovanteCancelamentoFacade remote = (ComprovanteCancelamentoFacade) ServiceLocator
						.getInstance().getRemoteFacade(
								ComprovanteCancelamentoFacadeHome.JNDI_NAME,
								ComprovanteCancelamentoFacadeHome.class);

				result = remote.buscarComprovanteCancelamento(requestInfo, to,
						pagina, qtdeRegistros);
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
