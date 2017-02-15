/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.atendimento.retencao.facade.interfaces;

/**
 * Interface Home para RetencaoUraFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface RetencaoUraFacadeHome
   extends javax.ejb.EJBHome
{
   public static final String COMP_NAME="java:comp/env/ejb/RetencaoUraFacade";
   public static final String JNDI_NAME="ejb/vivoservices/RetencaoUraFacade";

   public br.com.indrasistemas.vivoservices.atendimento.retencao.facade.interfaces.RetencaoUraFacade create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}
