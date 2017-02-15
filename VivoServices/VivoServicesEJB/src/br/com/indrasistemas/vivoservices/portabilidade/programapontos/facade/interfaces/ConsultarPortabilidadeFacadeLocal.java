/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.portabilidade.programapontos.facade.interfaces;

/**
 * Interface Local para ConsultarPortabilidadeFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface ConsultarPortabilidadeFacadeLocal
   extends javax.ejb.EJBLocalObject
{

   public br.com.indrasistemas.vivoservices.portabilidade.programapontos.webservice.to.RespostaStatusPortabilidadeTO consultarStatusPortabilidade( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,java.lang.String nrLinha ) throws br.com.indrasistemas.framework.service.BusinessException;

}
