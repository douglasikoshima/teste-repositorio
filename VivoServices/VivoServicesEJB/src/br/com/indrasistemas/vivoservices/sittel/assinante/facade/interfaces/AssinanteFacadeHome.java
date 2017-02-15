/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.sittel.assinante.facade.interfaces;

/**
 * Interface Home para ListaPUPFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface AssinanteFacadeHome
   extends javax.ejb.EJBHome
{
   public static final String COMP_NAME="java:comp/env/ejb/AssinanteFacade";
   public static final String JNDI_NAME="ejb/vivoservices/AssinanteFacade";

   public br.com.indrasistemas.vivoservices.sittel.assinante.facade.interfaces.AssinanteFacade create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}
