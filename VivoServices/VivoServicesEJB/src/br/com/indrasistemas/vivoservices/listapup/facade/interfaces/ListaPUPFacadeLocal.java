/*
 * Gerado pelo XDoclet - N�o edite!
 */
package br.com.indrasistemas.vivoservices.listapup.facade.interfaces;

/**
 * Interface Local para ListaPUPFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface ListaPUPFacadeLocal
   extends javax.ejb.EJBLocalObject
{

   public br.com.indrasistemas.vivoservices.listapup.webservice.to.ResultadoLinhaPUPTO gravarLinhaPUP( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.listapup.to.LinhaPUPWSTO to ) throws br.com.indrasistemas.framework.service.BusinessException;

   public br.com.indrasistemas.vivoservices.listapup.webservice.to.ResultadoLinhaPUPTO consultarLinhaPUP( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.listapup.to.LinhaPUPWSTO to ) throws br.com.indrasistemas.framework.service.BusinessException;

}
