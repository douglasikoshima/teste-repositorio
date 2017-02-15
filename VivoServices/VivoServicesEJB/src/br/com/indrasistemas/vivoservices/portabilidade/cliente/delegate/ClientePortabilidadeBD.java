package br.com.indrasistemas.vivoservices.portabilidade.cliente.delegate;

import java.rmi.RemoteException;

import javax.ejb.EJBException;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BaseBusinessDelegate;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.facade.interfaces.ClientePortabilidadeFacade;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.facade.interfaces.ClientePortabilidadeFacadeHome;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.facade.interfaces.ClientePortabilidadeFacadeLocal;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.facade.interfaces.ClientePortabilidadeFacadeLocalHome;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.to.DadosTO;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.to.PortabilidadeProcessosTO;
import br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice.to.RespostaManutencaoProcessoTO;
import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class ClientePortabilidadeBD extends BaseBusinessDelegate {

	public ClientePortabilidadeBD() {
	}

	public RespostaWSTO gravarClientePortabilidade(RequestInfoTO requestInfo,
			DadosTO dados) throws BusinessException, BusinessDelegateException {

		RespostaWSTO result = null;

		try {

			if (isFacadeLocal()) {
				ClientePortabilidadeFacadeLocal local = (ClientePortabilidadeFacadeLocal) ServiceLocator
						.getInstance().getLocalFacade(
								ClientePortabilidadeFacadeLocalHome.JNDI_NAME);

				result = local.gravarClientePortabilidade(requestInfo, dados);

			} else {
				ClientePortabilidadeFacade remote = (ClientePortabilidadeFacade) ServiceLocator
						.getInstance().getRemoteFacade(
								ClientePortabilidadeFacadeHome.JNDI_NAME,
								ClientePortabilidadeFacadeHome.class);

				result = remote.gravarClientePortabilidade(requestInfo, dados);
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

	public RespostaManutencaoProcessoTO gravarProcessoPortabilidade(
			RequestInfoTO requestInfo, PortabilidadeProcessosTO dados)
			throws BusinessException, BusinessDelegateException {

		RespostaManutencaoProcessoTO result = null;

		try {

			if (isFacadeLocal()) {
				ClientePortabilidadeFacadeLocal local = (ClientePortabilidadeFacadeLocal) ServiceLocator
						.getInstance().getLocalFacade(
								ClientePortabilidadeFacadeLocalHome.JNDI_NAME);

				result = local.gravarProcessoPortabilidade(requestInfo, dados);

			} else {
				ClientePortabilidadeFacade remote = (ClientePortabilidadeFacade) ServiceLocator
						.getInstance().getRemoteFacade(
								ClientePortabilidadeFacadeHome.JNDI_NAME,
								ClientePortabilidadeFacadeHome.class);

				result = remote.gravarProcessoPortabilidade(requestInfo, dados);
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
