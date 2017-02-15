/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.atendimento.ordemvenda.facade.interfaces;

/**
 * Interface Home para PalitagemFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface ManterOrdemVendaFacadeHome
   extends javax.ejb.EJBHome
{
   public static final String COMP_NAME="java:comp/env/ejb/ManterOrdemVendaFacade";
   public static final String JNDI_NAME="ejb/vivoservices/ManterOrdemVendaFacade";

   public br.com.indrasistemas.vivoservices.atendimento.ordemvenda.facade.interfaces.ManterOrdemVendaFacade create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}
