/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.autenticacao.facade.interfaces;

/**
 * Interface Local para AlteraSenhaFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface AlteraSenhaFacadeLocal
   extends javax.ejb.EJBLocalObject
{

   public br.com.indrasistemas.vivoservices.autenticacao.to.ResultadoAlterarSenhaTO alteraSenha( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.autenticacao.to.DadosAlteraSenhaTO dados ) throws br.com.indrasistemas.framework.service.BusinessException;

}
