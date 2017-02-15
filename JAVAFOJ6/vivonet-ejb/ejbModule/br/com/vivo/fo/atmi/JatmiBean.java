package br.com.vivo.fo.atmi;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import weblogic.wtc.gwt.TuxedoConnection;
import weblogic.wtc.gwt.TuxedoConnectionFactory;
import weblogic.wtc.jatmi.Reply;
import weblogic.wtc.jatmi.TPException;
import weblogic.wtc.jatmi.TPReplyException;
import weblogic.wtc.jatmi.TypedString;

@Stateless(name = "JATMI", mappedName = "JATMI")
@Local(JATMI.class)
@Session(ejbName = "JATMI", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class JatmiBean implements JATMI {

	private static final long serialVersionUID = 7607267299688339009L;
	private Logger logger = Logger.getAnonymousLogger();

	@Override
	public String executeCommnad(String method, String input) throws TPException, TPReplyException {
		Context ctx;

		TuxedoConnectionFactory tcf;

		TuxedoConnection myTux;
		TypedString myData;
		Reply myRtn;

		try {
			ctx = new InitialContext();
			tcf = (TuxedoConnectionFactory) ctx.lookup("tuxedo.services.TuxedoConnection");

		} catch (NamingException ne) {
			throw new TPException(TPException.TPENOENT, "Could not getTuxedoConnectionFactory : " + ne);
		}

		myTux = tcf.getTuxedoConnection();
		myData = new TypedString(input);

		try {
			myRtn = myTux.tpcall(method, myData, 0);

		} catch (TPReplyException tre) {
			throw tre;

		} catch (TPException te) {
			throw te;

		} catch (Exception ee) {
			throw new TPException(TPException.TPESYSTEM, "Exception: " + ee);
		}
		myData = (TypedString) myRtn.getReplyBuffer();
		myTux.tpterm();

		logger.log(Level.INFO, myData.toString());
		return (myData.toString());
	}
}
