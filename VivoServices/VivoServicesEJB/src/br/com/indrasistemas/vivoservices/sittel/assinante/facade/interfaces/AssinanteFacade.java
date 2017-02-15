/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.sittel.assinante.facade.interfaces;

/**
 * Interface Remota para ListaPUPFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface AssinanteFacade
   extends javax.ejb.EJBObject
{

   public br.com.indrasistemas.vivoservices.sittel.assinante.to.ResultadoConsultaTO gravarRequisicaoProcessum(br.com.indrasistemas.vivoservices.sittel.assinante.to.AssinanteTO to, Integer idTipoSolicitacao )
      throws br.com.indrasistemas.framework.service.BusinessException, java.rmi.RemoteException;
 
}
