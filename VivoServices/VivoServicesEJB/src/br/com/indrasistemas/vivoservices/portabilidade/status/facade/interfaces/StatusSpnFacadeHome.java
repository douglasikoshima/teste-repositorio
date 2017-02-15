/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.portabilidade.status.facade.interfaces;

/**
 * Interface Home para StatusSpnFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface StatusSpnFacadeHome
   extends javax.ejb.EJBHome
{
   public static final String COMP_NAME="java:comp/env/ejb/StatusSpnFacade";
   public static final String JNDI_NAME="ejb/vivoservices/StatusSpnFacade";

   public br.com.indrasistemas.vivoservices.portabilidade.status.facade.interfaces.StatusSpnFacade create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}
