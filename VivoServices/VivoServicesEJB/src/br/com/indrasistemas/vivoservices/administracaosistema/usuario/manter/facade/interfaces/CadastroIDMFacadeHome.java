/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.facade.interfaces;

/**
 * Interface Home para CadastroIDMFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface CadastroIDMFacadeHome
   extends javax.ejb.EJBHome
{
   public static final String COMP_NAME="java:comp/env/ejb/CadastroIDMFacade";
   public static final String JNDI_NAME="ejb/vivoservices/CadastroIDMFacade";

   public br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.facade.interfaces.CadastroIDMFacade create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}
