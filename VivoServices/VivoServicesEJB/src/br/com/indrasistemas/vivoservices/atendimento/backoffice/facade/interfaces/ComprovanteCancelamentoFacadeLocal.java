/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.atendimento.backoffice.facade.interfaces;

/**
 * Interface Local para ComprovanteCancelamentoFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface ComprovanteCancelamentoFacadeLocal
   extends javax.ejb.EJBLocalObject
{

   public br.com.indrasistemas.framework.service.valuehandler.ValueList buscarComprovanteCancelamento( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.atendimento.backoffice.to.ComprovanteCancelamentoTO to,java.lang.Integer pagina,java.lang.Integer qtdeRegistros ) throws br.com.indrasistemas.framework.service.BusinessException;

}
