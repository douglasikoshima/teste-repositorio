/*
 * Gerado pelo XDoclet - N�o edite!
 */
package br.com.indrasistemas.vivoservices.atendimento.retencao.facade.interfaces;

/**
 * Interface Local para RetencaoUraFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface RetencaoUraFacadeLocal
   extends javax.ejb.EJBLocalObject
{

   public br.com.indrasistemas.vivoservices.atendimento.retencao.to.RetencaoTO consultarOfertas( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.atendimento.retencao.to.RetencaoTO to ) throws br.com.indrasistemas.framework.service.BusinessException;

   public br.com.indrasistemas.vivoservices.atendimento.retencao.to.RetencaoTO aceitarOfertas( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.atendimento.retencao.to.RetencaoTO to ) throws br.com.indrasistemas.framework.service.BusinessException;

   public br.com.indrasistemas.vivoservices.atendimento.retencao.to.RetencaoTO recusarOfertas( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.atendimento.retencao.to.RetencaoTO to ) throws br.com.indrasistemas.framework.service.BusinessException;

}
