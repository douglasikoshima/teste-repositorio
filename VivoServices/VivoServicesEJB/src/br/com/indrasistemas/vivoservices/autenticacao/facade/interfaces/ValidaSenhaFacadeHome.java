/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.autenticacao.facade.interfaces;

/**
 * Interface Home para ValidaSenhaFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface ValidaSenhaFacadeHome
   extends javax.ejb.EJBHome
{
   public static final String COMP_NAME="java:comp/env/ejb/ValidaSenhaFacade";
   public static final String JNDI_NAME="ejb/vivoservices/ValidaSenhaFacade";

   public br.com.indrasistemas.vivoservices.autenticacao.facade.interfaces.ValidaSenhaFacade create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}
