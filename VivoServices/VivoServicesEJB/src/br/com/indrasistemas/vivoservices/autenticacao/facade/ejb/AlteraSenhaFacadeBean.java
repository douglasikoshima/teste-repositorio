package br.com.indrasistemas.vivoservices.autenticacao.facade.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.autenticacao.application.AutenticacaoAS;
import br.com.indrasistemas.vivoservices.autenticacao.to.DadosAlteraSenhaTO;
import br.com.indrasistemas.vivoservices.autenticacao.to.ResultadoAlterarSenhaTO;

/**
 * @ejb.bean name="AlteraSenhaFacade" display-name="AlteraSenhaFacade" description=""
 * 
 * jndi-name="ejb/vivoservices/AlteraSenhaFacade" type="Stateless"
 * local-jndi-name="ejb/vivoservices/local/AlteraSenhaFacade" view-type="both"
 * 
 * @ejb.transaction type="Required"
 * 
 * @weblogic.transaction-descriptor trans-timeout-seconds="300"
 * @weblogic.enable-call-by-reference True
 */
public class AlteraSenhaFacadeBean implements SessionBean {

	
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(AlteraSenhaFacadeBean.class);

	/** The session context */
	private SessionContext context;		
	
	
	/**
	 * @J2EE_METHOD -- AlteraSenhaFacadeBean
	 */
	public AlteraSenhaFacadeBean() {}
	
	
	public void ejbActivate() throws EJBException, RemoteException {

	}

	public void ejbPassivate() throws EJBException, RemoteException {

	}

	public void ejbRemove() throws EJBException, RemoteException {

	}

	/**
	 * @param sessionContext
	 * @throws javax.ejb.EJBException
	 * @throws java.rmi.RemoteException
	 * @J2EE_METHOD -- setSessionContext Set the associated session context. The
	 *              container calls this method after the instance creation. The
	 *              enterprise Bean instance should store the reference to the
	 *              context object in an instance variable. This method is
	 *              called with no transaction context.
	 */
	public void setSessionContext(SessionContext sessionContext) throws EJBException, RemoteException {
		context = sessionContext;
	}
	
	/**
	 * @return javax.ejb.SessionContext
	 * @J2EE_METHOD -- getSessionContext
	 */
	public SessionContext getSessionContext() {
		if (logger.isDebugEnabled()) {
			logger.debug("getSessionContext() - start");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getSessionContext() - end");
		}
		return this.context;
	}
	
	/**
	 * @ejb.interface-method view-type="both"
	 * @return
	 * @throws BusinessException
	 * @J2EE_METHOD -- alteraSenha
	 */
	public ResultadoAlterarSenhaTO alteraSenha(RequestInfoTO requestInfo, DadosAlteraSenhaTO dados) throws BusinessException {
		ResultadoAlterarSenhaTO result = null;
		try {
			AutenticacaoAS as = new AutenticacaoAS(this.getSessionContext());
			result = as.alteraSenha(requestInfo, dados);
		} catch (Exception e) {
			throw new EJBException(e);
		}
		return result;
	}	
	

}
