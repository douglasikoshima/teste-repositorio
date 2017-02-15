package br.com.indrasistemas.vivoservices.sittel.assinante.delegate;

import java.rmi.RemoteException;

import javax.ejb.EJBException;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BaseBusinessDelegate;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.vivoservices.sittel.assinante.facade.interfaces.AssinanteFacade;
import br.com.indrasistemas.vivoservices.sittel.assinante.facade.interfaces.AssinanteFacadeHome;
import br.com.indrasistemas.vivoservices.sittel.assinante.facade.interfaces.AssinanteFacadeLocal;
import br.com.indrasistemas.vivoservices.sittel.assinante.facade.interfaces.AssinanteFacadeLocalHome;
import br.com.indrasistemas.vivoservices.sittel.assinante.to.AssinanteTO;
import br.com.indrasistemas.vivoservices.sittel.assinante.to.ResultadoConsultaTO;

public class AssinanteBD extends BaseBusinessDelegate {

	public AssinanteBD() {
	}

	public ResultadoConsultaTO gravarRequisicaoProcessum (AssinanteTO to, Integer idTipoSolicitacao  ) throws BusinessException,
			BusinessDelegateException {
		ResultadoConsultaTO result = null;

		try {
			if (isFacadeLocal()) {
				AssinanteFacadeLocal local = (AssinanteFacadeLocal) ServiceLocator
						.getInstance().getLocalFacade(
								AssinanteFacadeLocalHome.JNDI_NAME);

				result = local.gravarRequisicaoProcessum(to, idTipoSolicitacao);
			} else {
				AssinanteFacade remote = (AssinanteFacade) ServiceLocator
						.getInstance().getRemoteFacade(
								AssinanteFacadeHome.JNDI_NAME,
								AssinanteFacadeHome.class);

				result = remote.gravarRequisicaoProcessum(to, idTipoSolicitacao);
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
