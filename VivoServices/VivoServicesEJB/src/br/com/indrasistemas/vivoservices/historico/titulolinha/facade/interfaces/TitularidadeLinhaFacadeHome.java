/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.historico.titulolinha.facade.interfaces;

/**
 * Interface Home para TitularidadeLinhaFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface TitularidadeLinhaFacadeHome
   extends javax.ejb.EJBHome
{
   public static final String COMP_NAME="java:comp/env/ejb/TitularidadeLinhaFacade";
   public static final String JNDI_NAME="ejb/vivoservices/TitularidadeLinhaFacade";

   public br.com.indrasistemas.vivoservices.historico.titulolinha.facade.interfaces.TitularidadeLinhaFacade create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}
