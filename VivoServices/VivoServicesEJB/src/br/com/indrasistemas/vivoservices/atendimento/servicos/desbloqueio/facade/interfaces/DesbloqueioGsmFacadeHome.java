/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.facade.interfaces;

/**
 * Interface Home para DesbloqueioGsmFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface DesbloqueioGsmFacadeHome
   extends javax.ejb.EJBHome
{
   public static final String COMP_NAME="java:comp/env/ejb/DesbloqueioGsmFacade";
   public static final String JNDI_NAME="ejb/vivoservices/DesbloqueioGsmFacade";

   public br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.facade.interfaces.DesbloqueioGsmFacade create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}
