/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.atendimento.protocolo.facade.interfaces;

/**
 * Interface Remota para ProtocoloFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface ProtocoloFacade
   extends javax.ejb.EJBObject
{

   public br.com.indrasistemas.vivoservices.atendimento.protocolo.to.ProtocoloTO aberturaProtocolo( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.atendimento.protocolo.to.ProtocoloTO to )
      throws br.com.indrasistemas.framework.service.BusinessException, java.rmi.RemoteException;

   public br.com.indrasistemas.vivoservices.atendimento.protocolo.to.ProtocoloTO fechamentoProtocolo( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.atendimento.protocolo.to.ProtocoloTO to )
      throws br.com.indrasistemas.framework.service.BusinessException, java.rmi.RemoteException;

}
