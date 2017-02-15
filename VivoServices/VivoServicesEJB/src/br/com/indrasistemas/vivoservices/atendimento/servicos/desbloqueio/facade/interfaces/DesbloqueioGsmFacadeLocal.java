/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.facade.interfaces;

/**
 * Interface Local para DesbloqueioGsmFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface DesbloqueioGsmFacadeLocal
   extends javax.ejb.EJBLocalObject
{

   public br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.to.DesbloqueioGsmTO desbloquearAparelho( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.to.DesbloqueioGsmTO to ) throws br.com.indrasistemas.framework.service.BusinessException;

}
