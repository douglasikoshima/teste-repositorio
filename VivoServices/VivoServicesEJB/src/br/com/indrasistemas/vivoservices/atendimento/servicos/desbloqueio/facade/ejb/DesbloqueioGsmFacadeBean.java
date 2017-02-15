package br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.facade.ejb;

import java.rmi.RemoteException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.application.ApplicationServiceException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.to.DesbloqueioGsmTO;
import br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.application.DesbloqueioGsmAS;

/**
 * @ejb.bean name="DesbloqueioGsmFacade" display-name="DesbloqueioGsmFacade"
 *           description="" jndi-name="ejb/vivoservices/DesbloqueioGsmFacade"
 *           type="Stateless"
 *           local-jndi-name="ejb/vivoservices/local/DesbloqueioGsmFacade"
 *           view-type="both"
 * @ejb.transaction type="Required"
 * @weblogic.transaction-descriptor trans-timeout-seconds="300"
 * @weblogic.enable-call-by-reference True
 */
public class DesbloqueioGsmFacadeBean implements SessionBean {

    private static final long serialVersionUID = 5709654900336064610L;

    /**
     * Logger for this class
     */
    private static final Log  logger           = LogFactory.getLog(DesbloqueioGsmFacadeBean.class);

    /** The session context */
    private SessionContext    context;

    /**
     * @J2EE_METHOD -- DesbloqueioGsmFacadeBean
     */
    public DesbloqueioGsmFacadeBean() {

    }

    /**
     * @throws javax.ejb.EJBException
     * @throws java.rmi.RemoteException
     * @J2EE_METHOD -- ejbActivate The activate method is called when the
     *              instance is activated from its 'passive' state. The instance
     *              should acquire any resource that it has released earlier in
     *              the ejbPassivate() method. This method is called with no
     *              transaction context.
     */
    public void ejbActivate() throws EJBException, RemoteException {

    }

    /**
     * @throws javax.ejb.EJBException
     * @throws java.rmi.RemoteException
     * @J2EE_METHOD -- ejbPassivate The passivate method is called before the
     *              instance enters the 'passive' state. The instance should
     *              release any resources that it can re-acquire later in the
     *              ejbActivate() method. After the passivate method completes,
     *              the instance must be in a state that allows the container to
     *              use the Java Serialization protocol to externalize and store
     *              away the instance's state. This method is called with no
     *              transaction context.
     */
    public void ejbPassivate() throws EJBException, RemoteException {

    }

    /**
     * @throws javax.ejb.EJBException
     * @throws java.rmi.RemoteException
     * @J2EE_METHOD -- ejbRemove A container invokes this method before it ends
     *              the life of the session object. This happens as a result of
     *              a client's invoking a remove operation, or when a container
     *              decides to terminate the session object after a timeout.
     *              This method is called with no transaction context.
     */
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
        if(logger.isDebugEnabled()){
            logger.debug("getSessionContext() - start");
        }
        if(logger.isDebugEnabled()){
            logger.debug("getSessionContext() - end");
        }
        return this.context;
    }

    /**
     * @ejb.interface-method view-type="both"
     * @return
     * @throws BusinessException
     * @J2EE_METHOD -- desbloquearAparelho
     */
    public DesbloqueioGsmTO desbloquearAparelho(RequestInfoTO requestInfo, DesbloqueioGsmTO to) throws BusinessException {

        DesbloqueioGsmTO result = null;
        try{
            DesbloqueioGsmAS as = new DesbloqueioGsmAS(this.getSessionContext());
            result = as.desbloquearAparelho(requestInfo, to);
        }catch(ApplicationServiceException e){
            throw new EJBException(e);
        }
        return result;
    }
}
