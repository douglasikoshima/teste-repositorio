/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.historico.titulolinha.facade.interfaces;

/**
 * Interface Remota para TitularidadeLinhaFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface TitularidadeLinhaFacade
   extends javax.ejb.EJBObject
{

   public br.com.indrasistemas.vivoservices.historico.titulolinha.to.TitularidadeLinhaTO pesquisarTitularidadeLinha( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.historico.titulolinha.to.ParametrosTO to )
      throws br.com.indrasistemas.framework.service.BusinessException, java.rmi.RemoteException;

}
