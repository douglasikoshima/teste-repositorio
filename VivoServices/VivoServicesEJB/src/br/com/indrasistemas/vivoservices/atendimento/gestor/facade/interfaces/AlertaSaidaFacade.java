/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.atendimento.gestor.facade.interfaces;

/**
 * Interface Remota para AlertaSaidaFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface AlertaSaidaFacade
   extends javax.ejb.EJBObject
{

   public br.com.indrasistemas.vivoservices.atendimento.gestor.to.AlertaSaidaTO consultar( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,java.lang.Long cdAreaRegistro,java.lang.Long nrLinha )
      throws br.com.indrasistemas.framework.service.BusinessException, java.rmi.RemoteException;

}
