/*
 * Gerado pelo XDoclet - N�o edite!
 */
package br.com.indrasistemas.vivoservices.tracking.facade.interfaces;

/**
 * Interface Local para TrackingAparelhosFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface TrackingAparelhosFacadeLocal
   extends javax.ejb.EJBLocalObject
{

   public br.com.indrasistemas.framework.service.valuehandler.ValueList buscarListaPedidos( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.tracking.to.PedidoTO to,java.lang.Integer pagina,java.lang.Integer qtdeRegistros ) throws br.com.indrasistemas.framework.service.BusinessException;

   public br.com.indrasistemas.framework.service.valuehandler.ValueList buscarDetalhesPedido( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.tracking.to.DetalhePedidoTO to,java.lang.Integer pagina,java.lang.Integer qtdeRegistros ) throws br.com.indrasistemas.framework.service.BusinessException;

}
