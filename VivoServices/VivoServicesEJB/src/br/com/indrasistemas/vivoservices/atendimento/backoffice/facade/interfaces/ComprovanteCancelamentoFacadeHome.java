/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.atendimento.backoffice.facade.interfaces;

/**
 * Interface Home para ComprovanteCancelamentoFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface ComprovanteCancelamentoFacadeHome
   extends javax.ejb.EJBHome
{
   public static final String COMP_NAME="java:comp/env/ejb/ComprovanteCancelamentoFacade";
   public static final String JNDI_NAME="ejb/vivoservices/ComprovanteCancelamentoFacade";

   public br.com.indrasistemas.vivoservices.atendimento.backoffice.facade.interfaces.ComprovanteCancelamentoFacade create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}
