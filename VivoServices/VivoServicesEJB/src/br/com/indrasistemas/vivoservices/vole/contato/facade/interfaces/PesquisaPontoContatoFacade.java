/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.vole.contato.facade.interfaces;

/**
 * Interface Remota para PesquisaPontoContatoFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface PesquisaPontoContatoFacade
   extends javax.ejb.EJBObject
{

   public java.util.List buscarPontoContato( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.vole.contato.to.PontoContatoTO to,java.lang.Integer pagina,java.lang.Integer qtdeRegistros )
      throws br.com.indrasistemas.framework.service.BusinessException, java.rmi.RemoteException;

}
