package br.com.indrasistemas.framework.foundation.servicegateway.tuxedo.ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * XDoclet-based session bean. The class must be declared public according to
 * the EJB specification.
 * 
 * To generate the EJB related files to this EJB: - Add Standard EJB module to
 * XDoclet project properties - Customize XDoclet configuration for your
 * appserver - Run XDoclet
 * 
 * Below are the xdoclet-related tags needed for this EJB.
 * 
 * @ejb.bean name="TuxedoServiceCaller" display-name="Name for
 *           TuxedoServiceCaller" description="Description for
 *           TuxedoServiceCaller" jndi-name="ejb/TuxedoServiceCaller"
 *           type="Stateless" view-type="both"
 */
class TuxedoServiceCallerBean implements SessionBean {

	private static final long serialVersionUID = 6663505629262931875L;

	/** The session context */
	private SessionContext context;

	public TuxedoServiceCallerBean() {
		super();
	}

	/**
	 * Set the associated session context. The container calls this method after
	 * the instance creation.
	 * 
	 * The enterprise bean instance should store the reference to the context
	 * object in an instance variable.
	 * 
	 * This method is called with no transaction context.
	 * 
	 * @throws EJBException
	 *             Thrown if method fails due to system-level error.
	 */
	public void setSessionContext(SessionContext newContext)
			throws EJBException {
		context = newContext;
	}

	/**
	 * Get the associated session context.
	 * 
	 * @return MessageDrivenContext object
	 */
	public SessionContext getSessionContext() {
		return this.context;
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

	/**
	 * @ejb.interface-method view-type = "both"
	 * @ejb.transaction type="Required"
	 * 
	 * @throws EJBException
	 *             Thrown if method fails due to system-level error.
	 */
	public String callServiceWithTransaction(String serviceName, String data)
			throws EJBException {
		return callService(serviceName, data);
	}

	/**
	 * @ejb.interface-method view-type = "both"
	 * @ejb.transaction type="RequiresNew"
	 * 
	 * @throws EJBException
	 *             Thrown if method fails due to system-level error.
	 */
	public String callServiceWithNewTransaction(String serviceName, String data)
			throws EJBException {
		return callService(serviceName, data);
	}

	/**
	 * @ejb.interface-method view-type = "both"
	 * @ejb.transaction type="NotSupported"
	 * 
	 * @throws EJBException
	 *             Thrown if method fails due to system-level error.
	 */
	public String callServiceWithoutTransaction(String serviceName, String data)
			throws EJBException {
		return callService(serviceName, data);
	}

	private String callService(String serviceName, String data)
			throws EJBException {
		TuxedoServiceCaller caller = null;
		String result = null;
		try {
			caller = new TuxedoServiceCaller();
			result = caller.call(serviceName, data);
		} catch (TuxedoServiceCallerException ex) {
			throw new EJBException(ex);
		} finally {
			caller.close();
		}
		return result;
	}
}
