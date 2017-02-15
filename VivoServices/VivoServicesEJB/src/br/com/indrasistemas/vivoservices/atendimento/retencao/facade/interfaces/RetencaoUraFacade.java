/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.atendimento.retencao.facade.interfaces;

/**
 * Interface Remota para RetencaoUraFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface RetencaoUraFacade
   extends javax.ejb.EJBObject
{

   public br.com.indrasistemas.vivoservices.atendimento.retencao.to.RetencaoTO consultarOfertas( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.atendimento.retencao.to.RetencaoTO to )
      throws br.com.indrasistemas.framework.service.BusinessException, java.rmi.RemoteException;

   public br.com.indrasistemas.vivoservices.atendimento.retencao.to.RetencaoTO aceitarOfertas( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.atendimento.retencao.to.RetencaoTO to )
      throws br.com.indrasistemas.framework.service.BusinessException, java.rmi.RemoteException;

   public br.com.indrasistemas.vivoservices.atendimento.retencao.to.RetencaoTO recusarOfertas( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.atendimento.retencao.to.RetencaoTO to )
      throws br.com.indrasistemas.framework.service.BusinessException, java.rmi.RemoteException;

}
