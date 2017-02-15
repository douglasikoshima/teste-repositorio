/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.listapup.facade.interfaces;

/**
 * Interface Home para ListaPUPFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface ListaPUPFacadeHome
   extends javax.ejb.EJBHome
{
   public static final String COMP_NAME="java:comp/env/ejb/ListaPUPFacade";
   public static final String JNDI_NAME="ejb/vivoservices/ListaPUPFacade";

   public br.com.indrasistemas.vivoservices.listapup.facade.interfaces.ListaPUPFacade create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}
