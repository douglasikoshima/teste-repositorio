package br.com.vivo.fo.atmi;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;

@Stateless(name = "TuxedoServiceCall", mappedName = "TuxedoServiceCall")
@Local(TuxedoServiceCall.class)
@Session(ejbName = "TuxedoServiceCall", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class TuxedoServiceCallBean implements TuxedoServiceCall {

	public String callService(String serviceName, String data) throws TuxedoServiceCallerException {
		TuxedoServiceCaller caller = null;
		String result = null;
		try {
			caller = new TuxedoServiceCaller();
			result = caller.call(serviceName, data);

		} catch (TuxedoServiceCallerException ex) {
			throw new TuxedoServiceCallerException(ex);

		} finally {
			if (caller != null) {
				caller.close();
			}
		}
		return result;
	}
}
