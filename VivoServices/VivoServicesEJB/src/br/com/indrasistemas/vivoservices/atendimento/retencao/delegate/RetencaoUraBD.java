package br.com.indrasistemas.vivoservices.atendimento.retencao.delegate;

import java.rmi.RemoteException;
import javax.ejb.EJBException;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.delegate.BaseBusinessDelegate;
import br.com.indrasistemas.framework.service.delegate.BusinessDelegateException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.atendimento.retencao.facade.interfaces.RetencaoUraFacade;
import br.com.indrasistemas.vivoservices.atendimento.retencao.facade.interfaces.RetencaoUraFacadeHome;
import br.com.indrasistemas.vivoservices.atendimento.retencao.facade.interfaces.RetencaoUraFacadeLocal;
import br.com.indrasistemas.vivoservices.atendimento.retencao.facade.interfaces.RetencaoUraFacadeLocalHome;
import br.com.indrasistemas.vivoservices.atendimento.retencao.to.RetencaoTO;

public class RetencaoUraBD extends BaseBusinessDelegate {

    public RetencaoUraBD() {
    }

    public RetencaoTO consultarOfertas(RequestInfoTO requestInfo, RetencaoTO to) throws BusinessException, BusinessDelegateException {
        RetencaoTO result = new RetencaoTO();
        try{
            if(isFacadeLocal()){
                RetencaoUraFacadeLocal local = (RetencaoUraFacadeLocal) ServiceLocator.getInstance().getLocalFacade(RetencaoUraFacadeLocalHome.JNDI_NAME);
                result = local.consultarOfertas(requestInfo, to);
            }else{
                RetencaoUraFacade remote = (RetencaoUraFacade) ServiceLocator.getInstance().getRemoteFacade(RetencaoUraFacadeHome.JNDI_NAME, RetencaoUraFacadeHome.class);
                result = remote.consultarOfertas(requestInfo, to);
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

    public RetencaoTO aceitarOfertas(RequestInfoTO requestInfo, RetencaoTO to) throws BusinessException, BusinessDelegateException {
        RetencaoTO result = new RetencaoTO();
        try{
            if(isFacadeLocal()){
                RetencaoUraFacadeLocal local = (RetencaoUraFacadeLocal) ServiceLocator.getInstance().getLocalFacade(RetencaoUraFacadeLocalHome.JNDI_NAME);
                result = local.aceitarOfertas(requestInfo, to);
            }else{
                RetencaoUraFacade remote = (RetencaoUraFacade) ServiceLocator.getInstance().getRemoteFacade(RetencaoUraFacadeHome.JNDI_NAME, RetencaoUraFacadeHome.class);
                result = remote.aceitarOfertas(requestInfo, to);
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

    public RetencaoTO recusarOfertas(RequestInfoTO requestInfo, RetencaoTO to) throws BusinessException, BusinessDelegateException {
        RetencaoTO result = new RetencaoTO();
        try{
            if(isFacadeLocal()){
                RetencaoUraFacadeLocal local = (RetencaoUraFacadeLocal) ServiceLocator.getInstance().getLocalFacade(RetencaoUraFacadeLocalHome.JNDI_NAME);
                result = local.recusarOfertas(requestInfo, to);
            }else{
                RetencaoUraFacade remote = (RetencaoUraFacade) ServiceLocator.getInstance().getRemoteFacade(RetencaoUraFacadeHome.JNDI_NAME, RetencaoUraFacadeHome.class);
                result = remote.recusarOfertas(requestInfo, to);
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
