/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.atendimento.palitagem.facade.interfaces;

/**
 * Interface Remota para PalitagemFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface PalitagemFacade
   extends javax.ejb.EJBObject
{

   public br.com.indrasistemas.vivoservices.atendimento.palitagem.to.PalitagemTO registrarPalito( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.atendimento.palitagem.to.PalitagemTO to )
      throws br.com.indrasistemas.framework.service.BusinessException, java.rmi.RemoteException;

}
