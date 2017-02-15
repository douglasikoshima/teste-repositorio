package br.com.vivo.fo.ctrls.admsistemas.dominio;

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
import br.com.vivo.fo.admsistemas.vo.AdmTabelaDominioVODocument.AdmTabelaDominioVO;
import br.com.vivo.fo.admsistemas.vo.AdmTabelaDominiosVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmTabelaDominiosVODocument.AdmTabelaDominiosVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "DominioFacade", mappedName = "DominioFacade")
@Local(DominioFacade.class)
@Session(ejbName = "DominioFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class DominioFacadeImpl implements DominioFacade {

	static final long serialVersionUID = 1L;
	private static Logger log = new Logger("admsistemas");
	private String xmlIN = ConstantesCRM.SVAZIO;
	private String xmlOUT = ConstantesCRM.SVAZIO;

	@EJB
	private TuxedoServiceCall tuxedo;

	/**
	 * @common:operation
	 */
	public void removeDominio(AdmTabelaDominioVO admTabelaDominioVO, String user) throws TuxedoException, FacadeException {
		try {
			log.debug("DominioFacadeImpl:removeDominio(" + user + ")");

			xmlIN = admTabelaDominioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "ADMDOMREMOVE", xmlIN);

			// new ControlTuxedoCall().execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

		} catch (XmlException ex) {
			log.error("XmlException - DominioFacadeImpl:removeDominio(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: DominioFacadeImpl:removeDominio", ex));

		} catch (Exception ex) {
			log.error("Exception - DominioFacadeImpl:removeDominio(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AdmTabelaDominiosVO listaDominios(AdmTabelaDominioVO admTabelaDominioVO, String user) throws TuxedoException, FacadeException {
		try {
			log.debug("DominioFacadeImpl:listaDominios(" + user + ")");

			xmlIN = admTabelaDominioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "ADMDOMLISTA", xmlIN);

			// xmlOUT = new ControlTuxedoCall().execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			return AdmTabelaDominiosVODocument.Factory.parse(xmlOUT).getAdmTabelaDominiosVO();

		} catch (XmlException ex) {
			log.error("XmlException - DominioFacadeImpl:listaDominios(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: DominioFacadeImpl:listaDominios", ex));

		} catch (Exception ex) {
			log.error("Exception - DominioFacadeImpl:listaDominios(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AdmTabelaDominiosVO addDominio(AdmTabelaDominioVO admTabelaDominioVO, String user) throws TuxedoException, FacadeException {
		try {
			log.debug("DominioFacadeImpl:addDominio(" + user + ")");

			xmlIN = admTabelaDominioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "ADMDOMINSERI", xmlIN);

			// xmlOUT = new ControlTuxedoCall().execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			return AdmTabelaDominiosVODocument.Factory.parse(xmlOUT).getAdmTabelaDominiosVO();

		} catch (XmlException ex) {
			log.error("XmlException - DominioFacadeImpl:addDominio(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: DominioFacadeImpl:addDominio", ex));

		} catch (Exception ex) {
			log.error("Exception - DominioFacadeImpl:addDominio(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AdmTabelaDominiosVO updateDominio(AdmTabelaDominioVO admTabelaDominioVO, String user) throws TuxedoException, FacadeException {
		try {
			log.debug("DominioFacadeImpl:updateDominio(" + user + ")");

			xmlIN = admTabelaDominioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "ADMDOMALTERA", xmlIN);

			// xmlOUT = new ControlTuxedoCall().execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			return AdmTabelaDominiosVODocument.Factory.parse(xmlOUT).getAdmTabelaDominiosVO();

		} catch (XmlException ex) {
			log.error("XmlException - DominioFacadeImpl:updateDominio(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: DominioFacadeImpl:updateDominio", ex));

		} catch (Exception ex) {
			log.error("Exception - DominioFacadeImpl:updateDominio(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}
}
