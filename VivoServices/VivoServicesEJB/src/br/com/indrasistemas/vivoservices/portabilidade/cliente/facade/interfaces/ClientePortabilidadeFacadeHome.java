/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.portabilidade.cliente.facade.interfaces;

/**
 * Interface Home para ClientePortabilidadeFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface ClientePortabilidadeFacadeHome
   extends javax.ejb.EJBHome
{
   public static final String COMP_NAME="java:comp/env/ejb/ClientePortabilidadeFacade";
   public static final String JNDI_NAME="ejb/vivoservices/ClientePortabilidadeFacade";

   public br.com.indrasistemas.vivoservices.portabilidade.cliente.facade.interfaces.ClientePortabilidadeFacade create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}
