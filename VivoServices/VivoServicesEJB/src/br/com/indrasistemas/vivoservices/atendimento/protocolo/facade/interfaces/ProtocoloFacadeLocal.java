/*
 * Gerado pelo XDoclet - N�o edite!
 */
package br.com.indrasistemas.vivoservices.atendimento.protocolo.facade.interfaces;

/**
 * Interface Local para ProtocoloFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface ProtocoloFacadeLocal
   extends javax.ejb.EJBLocalObject
{

   public br.com.indrasistemas.vivoservices.atendimento.protocolo.to.ProtocoloTO aberturaProtocolo( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.atendimento.protocolo.to.ProtocoloTO to ) throws br.com.indrasistemas.framework.service.BusinessException;

   public br.com.indrasistemas.vivoservices.atendimento.protocolo.to.ProtocoloTO fechamentoProtocolo( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.atendimento.protocolo.to.ProtocoloTO to ) throws br.com.indrasistemas.framework.service.BusinessException;

}
