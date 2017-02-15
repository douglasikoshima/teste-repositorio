package br.com.vivo.fo.ctrls.senha.cti;

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
import br.com.vivo.fo.senha.vo.ArvoreNavegacaoUraVODocument;
import br.com.vivo.fo.senha.vo.ArvoreNavegacaoUraVODocument.ArvoreNavegacaoUraVO;
import br.com.vivo.fo.senha.vo.RamaisUraVODocument;
import br.com.vivo.fo.senha.vo.RamaisUraVODocument.RamaisUraVO;
import br.com.vivo.fo.senha.vo.RamaisVODocument;
import br.com.vivo.fo.senha.vo.RamaisVODocument.RamaisVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name="TransferirFacade",mappedName="TransferirFacade")
@Local(TransferirFacade.class)
@Session(ejbName = "TransferirFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class TransferirFacadeImpl implements TransferirFacade {

	@EJB
	private TuxedoServiceCall tuxedo;

	static final long serialVersionUID = 1L;

	private static Logger log = new Logger("senha");

	private transient String xmlIN = ConstantesCRM.SVAZIO;
	private transient String xmlOUT = ConstantesCRM.SVAZIO;

	// private final String TUX_SERVICE = "CustomService";

	/**
	 * @common:operation
	 */
	public ArvoreNavegacaoUraVO ConsArvoreNavegacaoUra(String xmlIn, String user) throws TuxedoException, FacadeException {
		try {
			log.debug("TransferirFacadeImpl:ConsArvoreNavegacaoUra(" + xmlIn + ", " + user + ")");

			xmlIn = XmlManager.MakeXmlIn(user, "ConsRamalURA", xmlIn);
			//xmlOUT = (new ControlTuxedoCall()).execute(this, senhaTux, "GETSERVICE", xmlIn);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			return ArvoreNavegacaoUraVODocument.Factory.parse(xmlOUT).getArvoreNavegacaoUraVO();

		} catch (XmlException ex) {
			log.error("XmlException - TransferirFacadeImpl:ConsArvoreNavegacaoUra(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TransferirFacadeImpl:ConsArvoreNavegacaoUra", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TransferirFacadeImpl:ConsArvoreNavegacaoUra(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TransferirFacadeImpl:ConsArvoreNavegacaoUra(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TransferirFacadeImpl:ConsArvoreNavegacaoUra", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public RamaisUraVO ConsRamalURA(String idUra, String user) throws TuxedoException, FacadeException {
		try {
			log.debug("TransferirFacadeImpl:ConsRamalUra(" + idUra + ", " + user + ")");

			xmlIN = this.getXmlRamaisUra(idUra);
			xmlIN = XmlManager.MakeXmlIn(user, "ConsRamalURA", xmlIN);
			//xmlOUT = (new ControlTuxedoCall()).execute(this, senhaTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			return RamaisUraVODocument.Factory.parse(xmlOUT).getRamaisUraVO();

		} catch (XmlException ex) {
			log.error("XmlException - TransferirFacadeImpl:ConsRamalUra(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TransferirFacadeImpl:ConsRamalUra", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TransferirFacadeImpl:ConsRamalUra(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TransferirFacadeImpl:ConsRamalUra(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TransferirFacadeImpl:ConsRamalUra", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public RamaisVO ConsRamal(String idCallCenter, String user) throws TuxedoException, FacadeException {
		try {
			log.debug("TransferirFacadeImpl:ConsRamal(" + idCallCenter + ", " + user + ")");

			xmlIN = this.getXmlRamais(idCallCenter);
			xmlIN = XmlManager.MakeXmlIn(user, "ConsRamal", xmlIN);
			//xmlOUT = (new ControlTuxedoCall()).execute(this, senhaTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			return RamaisVODocument.Factory.parse(xmlOUT).getRamaisVO();

		} catch (XmlException ex) {
			log.error("XmlException - TransferirFacadeImpl:ConsRamal(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TransferirFacadeImpl:ConsRamal", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - TransferirFacadeImpl:ConsRamal(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - TransferirFacadeImpl:ConsRamal(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: TransferirFacadeImpl:ConsRamal", ex));
		}
	}

	private String getXmlRamais(String idCallCenter) {
		StringBuffer xmlIN = new StringBuffer();

		xmlIN.append("");
		xmlIN.append("  <idCallCenter>").append(idCallCenter).append("</idCallCenter>");
		xmlIN.append("");

		return xmlIN.toString();
	}

	private String getXmlRamaisUra(String idUra) {
		StringBuffer xmlIN = new StringBuffer();

		xmlIN.append("");
		xmlIN.append("  <idRamalSenha>").append(idUra).append("</idRamalSenha>");
		xmlIN.append("");

		return xmlIN.toString();
	}
}
