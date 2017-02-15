package br.com.vivo.fo.commons.utils.businessDelegate;

import java.io.Serializable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.xmlbeans.XmlException;

import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

public class JATMIBusinessDelegate implements Serializable {

	private static final long serialVersionUID = -1265315819557363438L;

	private static Logger log = new Logger("start");

	public JATMIBusinessDelegate() {
	}

	public String executeCommnad(String method, String input) throws TuxedoServiceCallerException {
		try {
			Context context = new InitialContext();
			TuxedoServiceCall jatmi = (TuxedoServiceCall) context.lookup("java:comp/env/ejb/TuxedoServiceCall");

			String xmlIN = XmlManager.MakeXmlIn(ConstantesCRM.SONE, method, input);
			return jatmi.callService("TuxConnector", xmlIN);

		} catch (NamingException e) {
			log.error("JATMIBusinessDelegate::executeCommnad::NamingException", e);
			throw new TuxedoServiceCallerException(e);

		} catch (TuxedoServiceCallerException e) {
			log.error("JATMIBusinessDelegate::executeCommnad::TuxedoServiceCallerException", e);
			throw new TuxedoServiceCallerException(e);

		} catch (XmlException e) {
			log.error("JATMIBusinessDelegate::executeCommnad::XmlException", e);
			throw new TuxedoServiceCallerException(e);
		}
	}

	public String executeCommnad(String user, String method, String input) throws TuxedoServiceCallerException {
		try {
			Context context = new InitialContext();
			TuxedoServiceCall jatmi = (TuxedoServiceCall) context.lookup("java:comp/env/ejb/TuxedoServiceCall");

			String xmlIN = XmlManager.MakeXmlIn(user, method, input);
			return jatmi.callService("TuxConnector", xmlIN);

		} catch (NamingException e) {
			log.error("JATMIBusinessDelegate::executeCommnad::NamingException", e);
			throw new TuxedoServiceCallerException(e);

		} catch (TuxedoServiceCallerException e) {
			log.error("JATMIBusinessDelegate::executeCommnad::TuxedoServiceCallerException", e);
			throw new TuxedoServiceCallerException(e);

		} catch (XmlException e) {
			log.error("JATMIBusinessDelegate::executeCommnad::XmlException", e);
			throw new TuxedoServiceCallerException(e);
		}
	}
}
