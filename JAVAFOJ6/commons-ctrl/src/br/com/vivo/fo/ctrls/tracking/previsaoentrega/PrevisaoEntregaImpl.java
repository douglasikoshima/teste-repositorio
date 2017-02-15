package br.com.vivo.fo.ctrls.tracking.previsaoentrega;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import noNamespace.MsgDocument;

import org.apache.xmlbeans.XmlException;

import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.tracking.vo.TrackingPrevisaoEntregaVODocument;
import br.com.vivo.fo.tracking.vo.TrackingPrevisaoEntregaVODocument.TrackingPrevisaoEntregaVO;
import br.com.vivo.fo.xml.XmlManager;

/**
 * @editor-info:code-gen control-interface="true"
 */
@Stateless(name="PrevisaoEntrega",mappedName="PrevisaoEntrega")
@Local(PrevisaoEntrega.class)
@Session(ejbName = "PrevisaoEntrega", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PrevisaoEntregaImpl implements PrevisaoEntrega {

	@EJB
	private TuxedoServiceCall tuxedo;

	static final long serialVersionUID = 1L;

	private transient Logger log = new Logger("clientes");

	private String xmlIN = ConstantesCRM.SVAZIO;
	private String xmlOUT = ConstantesCRM.SVAZIO;

	/**
	 * @common:operation
	 */
	public TrackingPrevisaoEntregaVO getTRACK(String user, TrackingPrevisaoEntregaVO filtro) throws TuxedoException, FacadeException {
		try {
			xmlIN = filtro.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "GETTRACK", xmlIN);

			//xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			return TrackingPrevisaoEntregaVODocument.Factory.parse(xmlOUT).getTrackingPrevisaoEntregaVO();

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:consultaLinhaVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:consultaLinhaVO", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:consultaLinhaVO(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:consultaLinhaVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public TrackingPrevisaoEntregaVO setTRACK(String user, TrackingPrevisaoEntregaVO filtro) throws TuxedoException, FacadeException {
		try {
			xmlIN = filtro.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "SETTRACK", xmlIN);

			//xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			return TrackingPrevisaoEntregaVODocument.Factory.parse(xmlOUT).getTrackingPrevisaoEntregaVO();

		} catch (XmlException ex) {
			log.error("XmlException - TelaInicialFacadeImpl:consultaLinhaVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TelaInicialFacadeImpl:consultaLinhaVO", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TelaInicialFacadeImpl:consultaLinhaVO(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TelaInicialFacadeImpl:consultaLinhaVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

}
