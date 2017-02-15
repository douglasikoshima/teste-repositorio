/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.autenticacao.facade.interfaces;

/**
 * Interface Local para ValidaSenhaFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface ValidaSenhaFacadeLocal
   extends javax.ejb.EJBLocalObject
{

   public br.com.indrasistemas.vivoservices.autenticacao.to.ResultadoValidarSenhaTO validaSenha( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.autenticacao.to.DadosValidaSenhaTO dados ) throws br.com.indrasistemas.framework.service.BusinessException;

}
