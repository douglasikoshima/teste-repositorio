/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.atendimento.backoffice.facade.ejb;

import javax.ejb.EJBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Camada Session para ComprovanteCancelamentoFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public class ComprovanteCancelamentoFacadeSession
   extends br.com.indrasistemas.vivoservices.atendimento.backoffice.facade.ejb.ComprovanteCancelamentoFacadeBean
   implements javax.ejb.SessionBean
{
   private static final long serialVersionUID = 1L;

   private static final Log logger = LogFactory.getLog(ComprovanteCancelamentoFacadeSession.class);

   public void ejbActivate() throws javax.ejb.EJBException, java.rmi.RemoteException
   {

      super.ejbActivate();
   }

   public void ejbPassivate() throws javax.ejb.EJBException, java.rmi.RemoteException
   {
      super.ejbPassivate();
   }

   public void setSessionContext(javax.ejb.SessionContext ctx) throws javax.ejb.EJBException, java.rmi.RemoteException
   {
      super.setSessionContext(ctx);
   }

   public void unsetSessionContext() 
   {
   }

   public void ejbRemove() throws javax.ejb.EJBException, java.rmi.RemoteException
   {
      super.ejbRemove();
   }

   public void ejbCreate() throws javax.ejb.CreateException
   {
   }

   public br.com.indrasistemas.framework.service.valuehandler.ValueList buscarComprovanteCancelamento( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.atendimento.backoffice.to.ComprovanteCancelamentoTO to,java.lang.Integer pagina,java.lang.Integer qtdeRegistros ) throws br.com.indrasistemas.framework.service.BusinessException	{
      //Verificação do parâmetro RequestInfoTO
      if(requestInfo == null)	{
        String errMsg = "O parâmetro requestInfo não pode ser nulo. O método buscarComprovanteCancelamento não pode ser executado.";
        logger.error(errMsg);
      	throw new NullPointerException(errMsg);
      }
      //Verificação do parâmetro RequestInfoTO.userName
      if(requestInfo.getUserName() == null)	{
        String errMsg = "O parâmetro RequestInfoTO.userName não pode ser nulo. O método buscarComprovanteCancelamento não pode ser executado.";
        logger.error(errMsg);
      	throw new NullPointerException(errMsg);
      }
      //Registro da app na base de dados
      br.com.indrasistemas.framework.foundation.database.AppRegisterManager appRegister = new br.com.indrasistemas.framework.foundation.database.AppRegisterManager();
      appRegister.register("br.com.indrasistemas.vivoservices.atendimento.backoffice.facade.ejb.ComprovanteCancelamentoFacadeBean", "buscarComprovanteCancelamento");
      //Se nulo é a primeira interação da cadeia de chamadas.
      if(requestInfo.getServiceName() == null)	{
        requestInfo.setServiceName("br.com.indrasistemas.vivoservices.atendimento.backoffice.facade.ejb.ComprovanteCancelamentoFacadeBean.buscarComprovanteCancelamento");
      }
      //Início token audit.
      br.com.indrasistemas.framework.foundation.audit.TokenAudit tokenAudit = new br.com.indrasistemas.framework.foundation.audit.TokenAudit();
      if(tokenAudit.isEnabled())  {
          tokenAudit.setServiceName("ComprovanteCancelamentoFacade.buscarComprovanteCancelamento");
          tokenAudit.setRequestInfo(requestInfo);
          tokenAudit.sendStartToken();
      }

      //Standard audit
      br.com.indrasistemas.framework.foundation.audit.StandardAudit standardAudit = new br.com.indrasistemas.framework.foundation.audit.StandardAudit();
      if(standardAudit.isEnabled())  {
          standardAudit.setServiceName("ComprovanteCancelamentoFacade.buscarComprovanteCancelamento");
          standardAudit.putParameter(requestInfo);
          standardAudit.putParameter(to);
          standardAudit.putParameter(pagina);
          standardAudit.putParameter(qtdeRegistros);
      }      

      //Call
      br.com.indrasistemas.framework.service.valuehandler.ValueList result = null;
      try	{
	      result = super.buscarComprovanteCancelamento( requestInfo,to,pagina,qtdeRegistros );
	  } catch (br.com.indrasistemas.framework.service.BusinessException e)	{
	  	  //Standard audit -- set exception.
	      if(standardAudit.isEnabled())  {
	          standardAudit.setException(e);
	      }     
	      throw e;
	  } catch (EJBException e)	{
		  //Standard audit -- set exception.
	      if(standardAudit.isEnabled())  {
	          standardAudit.setException(e);
	      }  
	  	  //Log de erro.
	      br.com.indrasistemas.framework.foundation.exception.MessageBuilder msgBuilder = new br.com.indrasistemas.framework.foundation.exception.MessageBuilder();
          msgBuilder.putParameter(requestInfo);
          msgBuilder.putParameter(to);
          msgBuilder.putParameter(pagina);
          msgBuilder.putParameter(qtdeRegistros);
	  	  logger.error(msgBuilder.buildMessage(), e);
	  	  throw e;		      
	  } catch (RuntimeException e)	{
	  	  //Standard audit -- set exception.
	      if(standardAudit.isEnabled())  {
	          standardAudit.setException(e);
	      }  
	  	  //Log de erro.
	      br.com.indrasistemas.framework.foundation.exception.MessageBuilder msgBuilder = new br.com.indrasistemas.framework.foundation.exception.MessageBuilder();
          msgBuilder.putParameter(requestInfo);
          msgBuilder.putParameter(to);
          msgBuilder.putParameter(pagina);
          msgBuilder.putParameter(qtdeRegistros);
	  	  logger.error(msgBuilder.buildMessage(), e);
	  	  throw new EJBException(e);	
	  } finally {
	      //Token audit -- final.
	      if(tokenAudit.isEnabled())  {
	          tokenAudit.sendReleaseToken();
	      }

	      //Standard audit -- final.
	      if(standardAudit.isEnabled())  {
	          standardAudit.setResult(result);
	          standardAudit.sendAuditInformation();
	      }
	  }

      return result;
   }

}
