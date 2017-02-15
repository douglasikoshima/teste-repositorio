/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.portabilidade.programapontos.facade.interfaces;

/**
 * Interface Home para ConsultarPortabilidadeFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface ConsultarPortabilidadeFacadeHome
   extends javax.ejb.EJBHome
{
   public static final String COMP_NAME="java:comp/env/ejb/ConsultarPortabilidadeFacade";
   public static final String JNDI_NAME="ejb/vivoservices/ConsultarPortabilidadeFacade";

   public br.com.indrasistemas.vivoservices.portabilidade.programapontos.facade.interfaces.ConsultarPortabilidadeFacade create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}
