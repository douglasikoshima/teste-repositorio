/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.portabilidade.cliente.facade.interfaces;

/**
 * Interface Local para ClientePortabilidadeFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface ClientePortabilidadeFacadeLocal
   extends javax.ejb.EJBLocalObject
{

   public br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO gravarClientePortabilidade( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.portabilidade.cliente.to.DadosTO dados ) throws br.com.indrasistemas.framework.service.BusinessException;

   public br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice.to.RespostaManutencaoProcessoTO gravarProcessoPortabilidade( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.portabilidade.cliente.to.PortabilidadeProcessosTO dados ) throws br.com.indrasistemas.framework.service.BusinessException;

}
