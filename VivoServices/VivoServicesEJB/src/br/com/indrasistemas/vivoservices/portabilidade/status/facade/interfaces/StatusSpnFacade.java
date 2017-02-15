/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.portabilidade.status.facade.interfaces;

/**
 * Interface Remota para StatusSpnFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface StatusSpnFacade
   extends javax.ejb.EJBObject
{

   public br.com.indrasistemas.vivoservices.portabilidade.status.to.RespostaTO validaStatusSpn( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,java.lang.String nrLinha )
      throws br.com.indrasistemas.framework.service.BusinessException, java.rmi.RemoteException;

}
