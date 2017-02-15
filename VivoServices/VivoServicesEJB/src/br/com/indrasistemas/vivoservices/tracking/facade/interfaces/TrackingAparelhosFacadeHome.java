/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.tracking.facade.interfaces;

/**
 * Interface Home para TrackingAparelhosFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface TrackingAparelhosFacadeHome
   extends javax.ejb.EJBHome
{
   public static final String COMP_NAME="java:comp/env/ejb/TrackingAparelhosFacade";
   public static final String JNDI_NAME="ejb/vivoservices/TrackingAparelhosFacade";

   public br.com.indrasistemas.vivoservices.tracking.facade.interfaces.TrackingAparelhosFacade create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}
