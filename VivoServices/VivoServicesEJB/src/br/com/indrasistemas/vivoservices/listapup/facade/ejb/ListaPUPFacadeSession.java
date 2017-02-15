/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.listapup.facade.ejb;

import javax.ejb.EJBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Camada Session para ListaPUPFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public class ListaPUPFacadeSession
   extends br.com.indrasistemas.vivoservices.listapup.facade.ejb.ListaPUPFacadeBean
   implements javax.ejb.SessionBean
{
   private static final long serialVersionUID = 1L;

   private static final Log logger = LogFactory.getLog(ListaPUPFacadeSession.class);

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

   public br.com.indrasistemas.vivoservices.listapup.webservice.to.ResultadoLinhaPUPTO gravarLinhaPUP( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.listapup.to.LinhaPUPWSTO to ) throws br.com.indrasistemas.framework.service.BusinessException	{
      //Verificação do parâmetro RequestInfoTO
      if(requestInfo == null)	{
        String errMsg = "O parâmetro requestInfo não pode ser nulo. O método gravarLinhaPUP não pode ser executado.";
        logger.error(errMsg);
      	throw new NullPointerException(errMsg);
      }
      //Verificação do parâmetro RequestInfoTO.userName
      if(requestInfo.getUserName() == null)	{
        String errMsg = "O parâmetro RequestInfoTO.userName não pode ser nulo. O método gravarLinhaPUP não pode ser executado.";
        logger.error(errMsg);
      	throw new NullPointerException(errMsg);
      }
      //Registro da app na base de dados
      br.com.indrasistemas.framework.foundation.database.AppRegisterManager appRegister = new br.com.indrasistemas.framework.foundation.database.AppRegisterManager();
      appRegister.register("br.com.indrasistemas.vivoservices.listapup.facade.ejb.ListaPUPFacadeBean", "gravarLinhaPUP");
      //Se nulo é a primeira interação da cadeia de chamadas.
      if(requestInfo.getServiceName() == null)	{
        requestInfo.setServiceName("br.com.indrasistemas.vivoservices.listapup.facade.ejb.ListaPUPFacadeBean.gravarLinhaPUP");
      }
      //Início token audit.
      br.com.indrasistemas.framework.foundation.audit.TokenAudit tokenAudit = new br.com.indrasistemas.framework.foundation.audit.TokenAudit();
      if(tokenAudit.isEnabled())  {
          tokenAudit.setServiceName("ListaPUPFacade.gravarLinhaPUP");
          tokenAudit.setRequestInfo(requestInfo);
          tokenAudit.sendStartToken();
      }

      //Standard audit
      br.com.indrasistemas.framework.foundation.audit.StandardAudit standardAudit = new br.com.indrasistemas.framework.foundation.audit.StandardAudit();
      if(standardAudit.isEnabled())  {
          standardAudit.setServiceName("ListaPUPFacade.gravarLinhaPUP");
          standardAudit.putParameter(requestInfo);
          standardAudit.putParameter(to);
      }      

      //Call
      br.com.indrasistemas.vivoservices.listapup.webservice.to.ResultadoLinhaPUPTO result = null;
      try	{
	      result = super.gravarLinhaPUP( requestInfo,to );
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

   public br.com.indrasistemas.vivoservices.listapup.webservice.to.ResultadoLinhaPUPTO consultarLinhaPUP( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.listapup.to.LinhaPUPWSTO to ) throws br.com.indrasistemas.framework.service.BusinessException	{
      //Verificação do parâmetro RequestInfoTO
      if(requestInfo == null)	{
        String errMsg = "O parâmetro requestInfo não pode ser nulo. O método consultarLinhaPUP não pode ser executado.";
        logger.error(errMsg);
      	throw new NullPointerException(errMsg);
      }
      //Verificação do parâmetro RequestInfoTO.userName
      if(requestInfo.getUserName() == null)	{
        String errMsg = "O parâmetro RequestInfoTO.userName não pode ser nulo. O método consultarLinhaPUP não pode ser executado.";
        logger.error(errMsg);
      	throw new NullPointerException(errMsg);
      }
      //Registro da app na base de dados
      br.com.indrasistemas.framework.foundation.database.AppRegisterManager appRegister = new br.com.indrasistemas.framework.foundation.database.AppRegisterManager();
      appRegister.register("br.com.indrasistemas.vivoservices.listapup.facade.ejb.ListaPUPFacadeBean", "consultarLinhaPUP");
      //Se nulo é a primeira interação da cadeia de chamadas.
      if(requestInfo.getServiceName() == null)	{
        requestInfo.setServiceName("br.com.indrasistemas.vivoservices.listapup.facade.ejb.ListaPUPFacadeBean.consultarLinhaPUP");
      }
      //Início token audit.
      br.com.indrasistemas.framework.foundation.audit.TokenAudit tokenAudit = new br.com.indrasistemas.framework.foundation.audit.TokenAudit();
      if(tokenAudit.isEnabled())  {
          tokenAudit.setServiceName("ListaPUPFacade.consultarLinhaPUP");
          tokenAudit.setRequestInfo(requestInfo);
          tokenAudit.sendStartToken();
      }

      //Standard audit
      br.com.indrasistemas.framework.foundation.audit.StandardAudit standardAudit = new br.com.indrasistemas.framework.foundation.audit.StandardAudit();
      if(standardAudit.isEnabled())  {
          standardAudit.setServiceName("ListaPUPFacade.consultarLinhaPUP");
          standardAudit.putParameter(requestInfo);
          standardAudit.putParameter(to);
      }      

      //Call
      br.com.indrasistemas.vivoservices.listapup.webservice.to.ResultadoLinhaPUPTO result = null;
      try	{
	      result = super.consultarLinhaPUP( requestInfo,to );
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
