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
import weblogic.wtc.jatmi.TypedBuffer;
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

        logger.log(Level.FINE, "serviceName: "+method+" data: "+input);
	    Context ctx = null;
		TuxedoConnectionFactory tcf = null;
		TuxedoConnection myTux = null;
		TypedString myData = null;
		Reply myRtn = null;
		String result = null;

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
            result = (myRtn != null ? myRtn.getReplyBuffer().toString() : null);

		} catch (TPReplyException tre) {
            logger.log(Level.WARNING, "TPReplyException", tre);
            try {
                TypedBuffer typedDataErr = tre.getExceptionReply().getReplyBuffer();
                result = typedDataErr.toString();
            } catch (RuntimeException rex) {
            }
            if (result == null) {
                throw tre;
            }

		} catch (TPException te) {
            logger.log(Level.WARNING, "TPException", te);
            try {
                TypedBuffer typedDataErr = te.getReplyRtn().getReplyBuffer();
                result = typedDataErr.toString();
            } catch (RuntimeException rex) {
            }
            if (result == null) {
                throw te;
            }

		} catch (Exception ee) {
			throw new TPException(TPException.TPESYSTEM, "Exception: " + ee);
		}
		myTux.tpterm();

		logger.log(Level.INFO, result);
		return result;
	}
}
