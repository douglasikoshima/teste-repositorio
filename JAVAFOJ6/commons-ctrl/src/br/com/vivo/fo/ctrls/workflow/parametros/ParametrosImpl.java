package br.com.vivo.fo.ctrls.workflow.parametros;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import noNamespace.MsgDocument;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "Parametros", mappedName = "Parametros")
@Local(Parametros.class)
@Session(ejbName = "Parametros", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ParametrosImpl implements Parametros {

	@EJB
	private TuxedoServiceCall tuxedo;

	private static Logger log = new Logger("workflow");

	/**
	 * @common:operation
	 */
	public ParametrosVO retornarParametros(String user, String idAtendimento) throws TuxedoException, FacadeException {
		try {
			log.debug("ParametrosImpl:retornarParametros("+user+")");
			StringBuffer xmlIn = new StringBuffer("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");
			//TuxedoServiceBridge.getXMLRequest(user, "ATDOBTERPARAM", xmlIn.toString());
			String xmlIN = XmlManager.MakeXmlIn(user, "ATDOBTERPARAM", xmlIn.toString());
			String xmlOut = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOut);
			
			return ParametrosVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getParametrosVO();

		} catch (Exception ex) {
			log.error("ParametrosImpl:retornarParametros(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}
}
