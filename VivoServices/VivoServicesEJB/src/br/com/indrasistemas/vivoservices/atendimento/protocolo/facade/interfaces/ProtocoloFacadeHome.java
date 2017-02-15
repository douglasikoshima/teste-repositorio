/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.atendimento.protocolo.facade.interfaces;

/**
 * Interface Home para ProtocoloFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface ProtocoloFacadeHome
   extends javax.ejb.EJBHome
{
   public static final String COMP_NAME="java:comp/env/ejb/ProtocoloFacade";
   public static final String JNDI_NAME="ejb/vivoservices/ProtocoloFacade";

   public br.com.indrasistemas.vivoservices.atendimento.protocolo.facade.interfaces.ProtocoloFacade create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}
