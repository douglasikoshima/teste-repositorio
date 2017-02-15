/*
 * Gerado pelo XDoclet - Não edite!
 */
package br.com.indrasistemas.vivoservices.autenticacao.facade.ejb;

import javax.ejb.EJBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Camada Session para AlteraSenhaFacade.
 * @xdoclet-generated at ${TODAY}
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public class AlteraSenhaFacadeSession
   extends br.com.indrasistemas.vivoservices.autenticacao.facade.ejb.AlteraSenhaFacadeBean
   implements javax.ejb.SessionBean
{
   private static final long serialVersionUID = 1L;

   private static final Log logger = LogFactory.getLog(AlteraSenhaFacadeSession.class);

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

   public br.com.indrasistemas.vivoservices.autenticacao.to.ResultadoAlterarSenhaTO alteraSenha( br.com.indrasistemas.framework.service.to.RequestInfoTO requestInfo,br.com.indrasistemas.vivoservices.autenticacao.to.DadosAlteraSenhaTO dados ) throws br.com.indrasistemas.framework.service.BusinessException	{
      //Verificação do parâmetro RequestInfoTO
      if(requestInfo == null)	{
        String errMsg = "O parâmetro requestInfo não pode ser nulo. O método alteraSenha não pode ser executado.";
        logger.error(errMsg);
      	throw new NullPointerException(errMsg);
      }
      //Verificação do parâmetro RequestInfoTO.userName
      if(requestInfo.getUserName() == null)	{
        String errMsg = "O parâmetro RequestInfoTO.userName não pode ser nulo. O método alteraSenha não pode ser executado.";
        logger.error(errMsg);
      	throw new NullPointerException(errMsg);
      }
      //Registro da app na base de dados
      br.com.indrasistemas.framework.foundation.database.AppRegisterManager appRegister = new br.com.indrasistemas.framework.foundation.database.AppRegisterManager();
      appRegister.register("br.com.indrasistemas.vivoservices.autenticacao.facade.ejb.AlteraSenhaFacadeBean", "alteraSenha");
      //Se nulo é a primeira interação da cadeia de chamadas.
      if(requestInfo.getServiceName() == null)	{
        requestInfo.setServiceName("br.com.indrasistemas.vivoservices.autenticacao.facade.ejb.AlteraSenhaFacadeBean.alteraSenha");
      }
      //Início token audit.
      br.com.indrasistemas.framework.foundation.audit.TokenAudit tokenAudit = new br.com.indrasistemas.framework.foundation.audit.TokenAudit();
      if(tokenAudit.isEnabled())  {
          tokenAudit.setServiceName("AlteraSenhaFacade.alteraSenha");
          tokenAudit.setRequestInfo(requestInfo);
          tokenAudit.sendStartToken();
      }

      //Standard audit
      br.com.indrasistemas.framework.foundation.audit.StandardAudit standardAudit = new br.com.indrasistemas.framework.foundation.audit.StandardAudit();
      if(standardAudit.isEnabled())  {
          standardAudit.setServiceName("AlteraSenhaFacade.alteraSenha");
          standardAudit.putParameter(requestInfo);
          standardAudit.putParameter(dados);
      }      

      //Call
      br.com.indrasistemas.vivoservices.autenticacao.to.ResultadoAlterarSenhaTO result = null;
      try	{
	      result = super.alteraSenha( requestInfo,dados );
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
          msgBuilder.putParameter(dados);
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
          msgBuilder.putParameter(dados);
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
