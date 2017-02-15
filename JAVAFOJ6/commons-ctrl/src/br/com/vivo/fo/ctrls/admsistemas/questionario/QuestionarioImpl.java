package br.com.vivo.fo.ctrls.admsistemas.questionario;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import noNamespace.MsgDocument;
import noNamespace.MsgHeaderVO;

import org.apache.xmlbeans.XmlException;

import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.admsistemas.vo.AdmListaVincularQuestionarioVODocument.AdmListaVincularQuestionarioVO;
import br.com.vivo.fo.admsistemas.vo.AdmQuestionarioContainerVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmQuestionarioContainerVODocument.AdmQuestionarioContainerVO;
import br.com.vivo.fo.admsistemas.vo.AdmQuestionarioVODocument.AdmQuestionarioVO;
import br.com.vivo.fo.admsistemas.vo.AdmVincularQuestionarioVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmVincularQuestionarioVODocument.AdmVincularQuestionarioVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlHeader;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "Questionario", mappedName = "Questionario")
@Local(Questionario.class)
@Session(ejbName = "Questionario", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class QuestionarioImpl implements Questionario {

	@EJB
	private TuxedoServiceCall tuxedo;

	private static Logger log = new Logger("admsistemas");

	private String xmlIN = ConstantesCRM.SVAZIO;
	private String xmlOUT = ConstantesCRM.SVAZIO;

	/**
	 * @common:operation
	 */
	public AdmVincularQuestionarioVO listaQuestionario(AdmVincularQuestionarioVO vincularQuestionarioVO, String user) throws TuxedoException, FacadeException {
		try {
			log.debug("QuestionarioImpl:listaQuestionario(" + user + ")");

			xmlIN = vincularQuestionarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "CttPerListar", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnectorADM", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			AdmVincularQuestionarioVODocument doc = AdmVincularQuestionarioVODocument.Factory.parse(xmlOUT);

			return doc.getAdmVincularQuestionarioVO();

		} catch (XmlException ex) {
			log.error("XmlException - QuestionarioImpl:listaQuestionario(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: QuestionarioImpl:listaQuestionario", ex));

		} catch (Exception ex) {
			log.error("Exception - QuestionarioImpl:listaQuestionario(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void salvaOperadoras(AdmVincularQuestionarioVO admListaVincularQuestionarioVO, String user) throws TuxedoException, FacadeException {
		try {
			log.debug("QuestionarioImpl:salvaOperadoras(" + user + ")");

			xmlIN = admListaVincularQuestionarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = admListaVincularQuestionarioVO.xmlText().replaceAll("vo1:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "CttPerIncluir", xmlIN);

			xmlIN = xmlIN.replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = xmlIN.replaceAll("vo1:", ConstantesCRM.SVAZIO);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			MsgHeaderVO msgHeaderVO =  msgDocRet.getMsg().getMsgHdr();
			tratarWarningException(msgHeaderVO);

			return;

		} catch (XmlException ex) {
			log.error("XmlException - QuestionarioImpl:salvaOperadoras(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: QuestionarioImpl:salvaOperadoras", ex));

		} catch (TuxedoWarningException twe) {
			log.error("TuxedoWarningException - QuestionarioImpl:salvaOperadoras(" + user + ") - [" + twe.getMessage() + "]");
			throw twe;
		
		} catch (Exception ex) {
			log.error("Exception - QuestionarioImpl:salvaOperadoras(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void removerQuestionario(AdmListaVincularQuestionarioVO admListaVincularQuestionarioVO, String user) throws TuxedoException, FacadeException {
		try {
			log.debug("QuestionarioImpl:removerQuestionario(" + user + ")");

			xmlIN = XmlManager.MakeXmlIn(user, "SatRemove", admListaVincularQuestionarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

			// xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			MsgHeaderVO msgHeaderVO =  msgDocRet.getMsg().getMsgHdr();
			tratarWarningException(msgHeaderVO);

			return;

		} catch (XmlException ex) {
			log.error("XmlException - QuestionarioImpl:removerQuestionario(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: QuestionarioImpl:removerQuestionario", ex));

		} catch (TuxedoWarningException twe) {
			log.error("TuxedoWarningException - QuestionarioImpl:removerQuestionarioId(" + user + ") - [" + twe.getMessage() + "]");
			throw twe;

		} catch (Exception ex) {
			log.error("Exception - QuestionarioImpl:removerQuestionario(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AdmQuestionarioContainerVO listaQuestionarioPesquisado(AdmQuestionarioVO admQuestionarioVO, String user) throws TuxedoException, FacadeException {
		try {
			log.debug("QuestionarioImpl:listaQuestionarioPesquisado(" + user + ")");

			xmlIN = XmlManager.MakeXmlIn(user, "SatListar", admQuestionarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

			// xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			AdmQuestionarioContainerVODocument doc = AdmQuestionarioContainerVODocument.Factory.parse(xmlOUT);

			return doc.getAdmQuestionarioContainerVO();

		} catch (XmlException ex) {
			log.error("XmlException - QuestionarioImpl:listaQuestionarioPesquisado(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: QuestionarioImpl:listaQuestionarioPesquisado", ex));

		} catch (Exception ex) {
			log.error("Exception - QuestionarioImpl:listaQuestionarioPesquisado(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AdmQuestionarioContainerVO listaTodosQuestionarios(String user) throws TuxedoException, FacadeException {
		try {
			log.debug("QuestionarioImpl:listaTodosQuestionarios(" + user + ")");

			xmlIN = XmlManager.MakeXmlIn(user, "SatListar", "<Vivo></Vivo>");

			// xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			AdmQuestionarioContainerVODocument doc = AdmQuestionarioContainerVODocument.Factory.parse(xmlOUT);

			return doc.getAdmQuestionarioContainerVO();

		} catch (XmlException ex) {
			log.error("XmlException - QuestionarioImpl:listaTodosQuestionarios(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: QuestionarioImpl:listaTodosQuestionarios", ex));

		} catch (Exception ex) {
			log.error("Exception - QuestionarioImpl:listaTodosQuestionarios(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void inserirQuestionario(AdmQuestionarioVO questionario, String user) throws TuxedoException, FacadeException {
		try {
			log.debug("QuestionarioImpl:inserirQuestionario(" + user + ")");

			xmlIN = XmlManager.MakeXmlIn(user, "SatIncluir", questionario.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

			// (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			MsgHeaderVO msgHeaderVO =  msgDocRet.getMsg().getMsgHdr();
			tratarWarningException(msgHeaderVO);

			return;

		} catch (XmlException ex) {
			log.error("XmlException - QuestionarioImpl:inserirQuestionario(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ", ex));

		} catch (TuxedoWarningException twe) {
			log.error("TuxedoWarningException - QuestionarioImpl:inserirQuestionario(" + user + ") - [" + twe.getMessage() + "]");
			throw twe;

		} catch (Exception ex) {
			log.error("Exception - QuestionarioImpl:inserirQuestionario(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void removerQuestionarioId(AdmQuestionarioVO questionario, String user) throws TuxedoException, FacadeException {
		try {
			log.debug("QuestionarioImpl:removerQuestionarioId(" + user + ")");

			xmlIN = XmlManager.MakeXmlIn(user, "SatRemove", questionario.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			MsgHeaderVO msgHeaderVO =  msgDocRet.getMsg().getMsgHdr();
			tratarWarningException(msgHeaderVO);

			return;

		} catch (XmlException ex) {
			log.error("XmlException - QuestionarioImpl:removerQuestionarioId(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: QuestionarioImpl:removerQuestionarioId", ex));

		} catch (TuxedoWarningException twe) {
			log.error("TuxedoWarningException - QuestionarioImpl:removerQuestionarioId(" + user + ") - [" + twe.getMessage() + "]");
			throw twe;

		} catch (Exception ex) {
			log.error("Exception - QuestionarioImpl:removerQuestionarioId(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void salvarQuestionarioEditado(AdmQuestionarioVO questionario, String user) throws TuxedoException, FacadeException {
		try {
			log.debug("QuestionarioImpl:salvarQuestionarioEditado(" + user + ")");

			xmlIN = XmlManager.MakeXmlIn(user, "SatEditar", questionario.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

			// (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			MsgHeaderVO msgHeaderVO =  msgDocRet.getMsg().getMsgHdr();
			tratarWarningException(msgHeaderVO);

			return;

		} catch (XmlException ex) {
			log.error("XmlException - QuestionarioImpl:salvarQuestionarioEditado(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: QuestionarioImpl:salvarQuestionarioEditado", ex));

		} catch (TuxedoWarningException twe) {
			log.error("TuxedoWarningException - QuestionarioImpl:salvarQuestionarioEditado(" + user + ") - [" + twe.getMessage() + "]");
			throw twe;
		} catch (Exception ex) {
			log.error("Exception - QuestionarioImpl:salvarQuestionarioEditado(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}
	
	private void tratarWarningException(MsgHeaderVO msgHeaderVO) throws TuxedoWarningException {
		
		if(msgHeaderVO != null && msgHeaderVO.getStatusCode().indexOf("W")>-1) {
			XmlHeader xmlHeader = new XmlHeader(msgHeaderVO.getService(),msgHeaderVO.getUser(), "fo", 'W', msgHeaderVO.getStatusCode().substring(0,4), msgHeaderVO.getStatusText());
			throw new TuxedoWarningException(xmlHeader);
		} else if(msgHeaderVO != null && msgHeaderVO.getStatusCode().indexOf("E")>-1) {
			XmlHeader xmlHeader = new XmlHeader(msgHeaderVO.getService(),msgHeaderVO.getUser(), "fo", 'E', msgHeaderVO.getStatusCode().substring(0,4), msgHeaderVO.getStatusText());
			throw new TuxedoWarningException(xmlHeader);
		}

	}

}
