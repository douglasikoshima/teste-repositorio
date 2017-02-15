package br.com.indrasistemas.vivoservices.atendimento.gestor.delegate;

import java.rmi.RemoteException;
import javax.ejb.EJBException;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BaseBusinessDelegate;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.atendimento.gestor.facade.interfaces.AlertaSaidaFacade;
import br.com.indrasistemas.vivoservices.atendimento.gestor.facade.interfaces.AlertaSaidaFacadeHome;
import br.com.indrasistemas.vivoservices.atendimento.gestor.facade.interfaces.AlertaSaidaFacadeLocal;
import br.com.indrasistemas.vivoservices.atendimento.gestor.facade.interfaces.AlertaSaidaFacadeLocalHome;
import br.com.indrasistemas.vivoservices.atendimento.gestor.to.AlertaSaidaTO;

public class AlertaSaidaBD extends BaseBusinessDelegate {

    public AlertaSaidaBD() {
    }

    public AlertaSaidaTO consultar(RequestInfoTO requestInfo, Long cdAreaRegistro, Long nrLinha) throws BusinessException, BusinessDelegateException {
        AlertaSaidaTO result = null;
        try{
            if(isFacadeLocal()){
                AlertaSaidaFacadeLocal local = (AlertaSaidaFacadeLocal) ServiceLocator.getInstance().getLocalFacade(AlertaSaidaFacadeLocalHome.JNDI_NAME);
                result = local.consultar(requestInfo, cdAreaRegistro, nrLinha);
            }else{
                AlertaSaidaFacade remote = (AlertaSaidaFacade) ServiceLocator.getInstance().getRemoteFacade(AlertaSaidaFacadeHome.JNDI_NAME, AlertaSaidaFacadeHome.class);
                result = remote.consultar(requestInfo, cdAreaRegistro, nrLinha);
            }
        }catch(ServiceLocatorException slex){
            throw new BusinessDelegateException(slex);

        }catch(EJBException ejbx){
            throw new BusinessDelegateException(ejbx);

        }catch(RemoteException slex){
            throw new BusinessDelegateException(slex);
        }
        return result;
    }
}
