package br.com.vivo.fo.atmi;

import java.util.logging.Level;
import java.util.logging.Logger;
import weblogic.wtc.gwt.TuxedoConnection;
import weblogic.wtc.gwt.TuxedoConnectionFactory;
import weblogic.wtc.jatmi.Reply;
import weblogic.wtc.jatmi.TPException;
import weblogic.wtc.jatmi.TPReplyException;
import weblogic.wtc.jatmi.TypedBuffer;
import weblogic.wtc.jatmi.TypedString;

class TuxedoServiceCaller {

	private static final String JNDI_TUX_CONNECTION = "tuxedo.services.TuxedoConnection";
	private Logger logger = Logger.getAnonymousLogger();

	private TuxedoConnection conn = null;

	public TuxedoServiceCaller() throws TuxedoServiceCallerException {
		super();
	}

	public void close() {
		if (this.conn == null) {
			return;
		}
		closeTuxedoConnection(this.conn);
		this.conn = null;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		close();
	}

	public synchronized String call(String serviceName, String data) throws TuxedoServiceCallerException {
		String result = null;
        try {
            this.conn = getConnection();
        } catch (Exception ex) {
            throw new TuxedoServiceCallerException(ex);
        }
		try {
			data = changeEncoding(data);
			logger.log(Level.INFO, "serviceName: "+serviceName+" data: "+data);
			
			TypedString typedData = new TypedString(data);
			Reply reply = this.conn.tpcall(serviceName, typedData, 0);
			result = (reply != null ? reply.getReplyBuffer().toString() : null);
			
		} catch (TPReplyException ex) {
			logger.log(Level.SEVERE, "TPReplyException", ex);
			try {
				TypedBuffer typedDataErr = ex.getExceptionReply().getReplyBuffer();
				result = typedDataErr.toString();
			} catch (RuntimeException rex) {
			}
			if (result == null || "".equals(result)) {
				throw new TuxedoServiceCallerException(ex);
			}

		} catch (TPException ex) {
			logger.log(Level.SEVERE, "TPException", ex);
			try {
				TypedBuffer typedDataErr = ex.getReplyRtn().getReplyBuffer();
				result = typedDataErr.toString();
			} catch (RuntimeException rex) {
			}
			if (result == null || "".equals(result)) {
				throw new TuxedoServiceCallerException(ex);
			}
		} catch(Exception e) {
            logger.log(Level.SEVERE, "Exception", e);
            result = makeXML(serviceName, "00E0000", e.getMessage());
		}
		
		logger.log(Level.INFO, "Tamanho da Mensagem: "+result.length());
		logger.log(Level.INFO, "Reply: "+result);
		return result;
	}

	private String changeEncoding(String xml) {
		return xml.replaceAll("UTF-8", "ISO-8859-1");
	}

	private TuxedoConnection getConnection() throws TuxedoServiceCallerException, TPException {
		TuxedoConnectionFactory tuxConFactory = null;
		TuxedoServiceLocator sLocator = TuxedoServiceLocator.getInstance();
		tuxConFactory = sLocator.getTuxedoConnectionFactory(TuxedoServiceCaller.JNDI_TUX_CONNECTION);
		return tuxConFactory.getTuxedoConnection();
	}

	private void closeTuxedoConnection(TuxedoConnection conn) {
		try {
			conn.tpterm();
		} catch (Exception ex) {
		}
	}

	private String makeXML(String service, String statusCode, String statusText) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<?xml version='1.0' encoding='ISO-8859-1'?>");
        buffer.append("<msg>");
        buffer.append("<msgHdr>");
        buffer.append("<user>1</user>");
        buffer.append("<service>").append(service).append("</service>");
        buffer.append("<statusCode>").append(statusCode).append("</statusCode>");
        buffer.append("<statusText>").append(statusText).append("</statusText>");
        buffer.append("</msgHdr>");
        buffer.append("<msgBody/>");
        buffer.append("</msg>");
        return buffer.toString();
	}
}