/*
 * Gerado pelo XDoclet - N�o edite!
 */
package br.com.indrasistemas.vivoservices.administracaosistema.endereco.facade.interfaces;

/**
 * Interface Local para PesquisaEnderecoFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface PesquisaEnderecoFacadeLocal
   extends javax.ejb.EJBLocalObject
{

   public br.com.indrasistemas.framework.service.valuehandler.ValueList buscarEndereco( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.administracaosistema.endereco.to.PesquisaEnderecoTO to,java.lang.Integer pagina,java.lang.Integer qtdeRegistros ) throws br.com.indrasistemas.framework.service.BusinessException;

   public java.util.List buscarUFs( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo ) throws br.com.indrasistemas.framework.service.BusinessException;

}
