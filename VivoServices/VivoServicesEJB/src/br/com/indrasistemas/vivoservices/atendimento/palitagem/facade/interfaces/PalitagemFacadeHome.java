/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.atendimento.palitagem.facade.interfaces;

/**
 * Interface Home para PalitagemFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface PalitagemFacadeHome
   extends javax.ejb.EJBHome
{
   public static final String COMP_NAME="java:comp/env/ejb/PalitagemFacade";
   public static final String JNDI_NAME="ejb/vivoservices/PalitagemFacade";

   public br.com.indrasistemas.vivoservices.atendimento.palitagem.facade.interfaces.PalitagemFacade create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}
