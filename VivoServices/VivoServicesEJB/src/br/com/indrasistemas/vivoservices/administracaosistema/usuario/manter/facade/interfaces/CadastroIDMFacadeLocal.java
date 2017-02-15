/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.facade.interfaces;

/**
 * Interface Local para CadastroIDMFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface CadastroIDMFacadeLocal
   extends javax.ejb.EJBLocalObject
{

   public br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.to.RespostaTO cadastrar( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.to.ParametrosTO parametros ) throws br.com.indrasistemas.framework.service.BusinessException;

   public br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.to.RespostaTO relacionarUsuarioIdm( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.to.RelacionaUsuarioTO parametros ) throws br.com.indrasistemas.framework.service.BusinessException;

   public br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.to.RespostaTO ativarUsuarioIdm( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.to.ParametrosTO parametros ) throws br.com.indrasistemas.framework.service.BusinessException;

}
