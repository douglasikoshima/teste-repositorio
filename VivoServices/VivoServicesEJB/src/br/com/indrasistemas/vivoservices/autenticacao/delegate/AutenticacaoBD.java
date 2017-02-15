package br.com.indrasistemas.vivoservices.autenticacao.delegate;

import java.rmi.RemoteException;

import javax.ejb.EJBException;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BaseBusinessDelegate;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.autenticacao.facade.interfaces.AlteraSenhaFacade;
import br.com.indrasistemas.vivoservices.autenticacao.facade.interfaces.AlteraSenhaFacadeHome;
import br.com.indrasistemas.vivoservices.autenticacao.facade.interfaces.AlteraSenhaFacadeLocal;
import br.com.indrasistemas.vivoservices.autenticacao.facade.interfaces.AlteraSenhaFacadeLocalHome;
import br.com.indrasistemas.vivoservices.autenticacao.facade.interfaces.ValidaSenhaFacade;
import br.com.indrasistemas.vivoservices.autenticacao.facade.interfaces.ValidaSenhaFacadeHome;
import br.com.indrasistemas.vivoservices.autenticacao.facade.interfaces.ValidaSenhaFacadeLocal;
import br.com.indrasistemas.vivoservices.autenticacao.facade.interfaces.ValidaSenhaFacadeLocalHome;
import br.com.indrasistemas.vivoservices.autenticacao.to.DadosAlteraSenhaTO;
import br.com.indrasistemas.vivoservices.autenticacao.to.DadosValidaSenhaTO;
import br.com.indrasistemas.vivoservices.autenticacao.to.ResultadoAlterarSenhaTO;
import br.com.indrasistemas.vivoservices.autenticacao.to.ResultadoValidarSenhaTO;

public class AutenticacaoBD extends BaseBusinessDelegate {

	
	public ResultadoAlterarSenhaTO alterarSenha (RequestInfoTO requestInfo, DadosAlteraSenhaTO dados)  throws BusinessException, BusinessDelegateException {
		ResultadoAlterarSenhaTO result = null;
		
		try {
			if (isFacadeLocal()) {
				AlteraSenhaFacadeLocal local = (AlteraSenhaFacadeLocal) ServiceLocator.getInstance().getLocalFacade(AlteraSenhaFacadeLocalHome.JNDI_NAME);
				result = local.alteraSenha(requestInfo, dados);
			} else {
				AlteraSenhaFacade remote = (AlteraSenhaFacade) ServiceLocator.getInstance().getRemoteFacade(AlteraSenhaFacadeHome.JNDI_NAME, AlteraSenhaFacadeHome.class);
				result = remote.alteraSenha(requestInfo, dados);
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
	
	
	public ResultadoValidarSenhaTO validarSenha (RequestInfoTO requestInfo, DadosValidaSenhaTO dados)  throws BusinessException, BusinessDelegateException {

		ResultadoValidarSenhaTO result = null;
		
		try {
			if (isFacadeLocal()) {
				ValidaSenhaFacadeLocal local = (ValidaSenhaFacadeLocal) ServiceLocator.getInstance().getLocalFacade(ValidaSenhaFacadeLocalHome.JNDI_NAME);
				result = local.validaSenha(requestInfo, dados);
			} else {
				ValidaSenhaFacade remote = (ValidaSenhaFacade) ServiceLocator.getInstance().getRemoteFacade(ValidaSenhaFacadeHome.JNDI_NAME, ValidaSenhaFacadeHome.class);
				result = remote.validaSenha(requestInfo, dados);
			}
		}catch (ServiceLocatorException slex) {
			throw new BusinessDelegateException(slex);
		} catch (EJBException ejbx) {
			throw new BusinessDelegateException(ejbx);
		} catch (RemoteException slex) {
			throw new BusinessDelegateException(slex);
		}
		return result;

	}

}
