/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.administracaosistema.endereco.facade.interfaces;

/**
 * Interface Home para PesquisaEnderecoFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface PesquisaEnderecoFacadeHome
   extends javax.ejb.EJBHome
{
   public static final String COMP_NAME="java:comp/env/ejb/PesquisaEnderecoFacade";
   public static final String JNDI_NAME="ejb/vivoservices/PesquisaEnderecoFacade";

   public br.com.indrasistemas.vivoservices.administracaosistema.endereco.facade.interfaces.PesquisaEnderecoFacade create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}
