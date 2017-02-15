package br.com.indrasistemas.vivoservices.atendimento.ordemvenda.facade.ejb;

import java.rmi.RemoteException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.atendimento.ordemvenda.application.ManterOrdemVendaAS;
import br.com.indrasistemas.vivoservices.atendimento.ordemvenda.to.PalitagemTO;

/**
 * @ejb.bean name="ManterOrdemVendaFacade" display-name="ManterOrdemVendaFacade"
 *           description="" jndi-name="ejb/vivoservices/ManterOrdemVendaFacade"
 *           type="Stateless"
 *           local-jndi-name="ejb/vivoservices/local/ManterOrdemVendaFacade"
 *           view-type="both"
 * @ejb.transaction type="Required"
 * @weblogic.transaction-descriptor trans-timeout-seconds="300"
 * @weblogic.enable-call-by-reference True
 */
public class ManterOrdemVendaFacadeBean implements SessionBean {

    private static final long serialVersionUID = 7464972990067179130L;

    /**
     * Logger for this class
     */
    private static final Log  logger           = LogFactory.getLog(ManterOrdemVendaFacadeBean.class);

    /** The session context */
    private SessionContext    context;

    /**
     * @J2EE_METHOD -- ManterOrdemVendaFacadeBean
     */
    public ManterOrdemVendaFacadeBean() {

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
     * @J2EE_METHOD -- gravarLinhaPUP
     */
    public PalitagemTO registrarPalito(RequestInfoTO requestInfo, PalitagemTO to) throws BusinessException {
        PalitagemTO result = null;
        try{
            ManterOrdemVendaAS as = new ManterOrdemVendaAS(this.getSessionContext());
            result = as.registrarPalito(requestInfo, to);
            if(logger.isDebugEnabled()){
                logger.debug("AS OK");
            }
        }catch(Exception e){
            logger.error("ERROR::ManterOrdemVendaFacadeBean::registrarPalito", e);
            throw new EJBException(e);
        }
        return result;
    }
}
