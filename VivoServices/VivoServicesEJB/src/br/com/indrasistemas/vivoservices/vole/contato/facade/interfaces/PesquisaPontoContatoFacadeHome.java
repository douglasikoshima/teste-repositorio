/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.vole.contato.facade.interfaces;

/**
 * Interface Home para PesquisaPontoContatoFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface PesquisaPontoContatoFacadeHome
   extends javax.ejb.EJBHome
{
   public static final String COMP_NAME="java:comp/env/ejb/PesquisaPontoContatoFacade";
   public static final String JNDI_NAME="ejb/vivoservices/PesquisaPontoContatoFacade";

   public br.com.indrasistemas.vivoservices.vole.contato.facade.interfaces.PesquisaPontoContatoFacade create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}
