package br.com.vivo.fo.ctrls.admsistemas.arvoreSatisfacao;

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
import br.com.vivo.fo.admsistemas.vo.AdmArvoreSatisfacaoVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmArvoreSatisfacaoVODocument.AdmArvoreSatisfacaoVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "ArvoreSatisfacao", mappedName = "ArvoreSatisfacao")
@Local(ArvoreSatisfacao.class)
@Session(ejbName = "ArvoreSatisfacao", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ArvoreSatisfacaoImpl implements ArvoreSatisfacao {

	@EJB
	private TuxedoServiceCall tuxedo;

	private String xmlIN = ConstantesCRM.SVAZIO;
	private String xmlOUT = ConstantesCRM.SVAZIO;

	private static Logger log = new Logger("admsistemas");

	/**
	 * @common:operation
	 */
	public AdmArvoreSatisfacaoVO getListaQuestionarios(String user) throws TuxedoException, FacadeException {
		try {
			log.debug("ArvoreSatisfacaoImpl:getListaQuestionarios(" + user + ")");
			AdmArvoreSatisfacaoVO lsQuestionarios = null;
			xmlIN = XmlManager.MakeXmlIn(user, "TuxConsAllGrp", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			AdmArvoreSatisfacaoVODocument msg = AdmArvoreSatisfacaoVODocument.Factory.parse(xmlOUT);
			lsQuestionarios = msg.getAdmArvoreSatisfacaoVO();
			xmlIN = ConstantesCRM.SVAZIO;
			xmlOUT = ConstantesCRM.SVAZIO;
			return lsQuestionarios;

		} catch (XmlException ex) {
			log.error("XmlException - ArvoreSatisfacaoImpl:getListaQuestionarios(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreSatisfacaoImpl:getListaQuestionarios", ex));

		} catch (Exception ex) {
			log.error("Exception - ArvoreSatisfacaoImpl:getListaQuestionarios(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void setNovoQuestionario(String user, String descricao) throws TuxedoException, FacadeException {
		try {
			log.debug("ArvoreSatisfacaoImpl:setNovoQuestionario(" + user + ", " + descricao + ")");

			xmlIN = "<descricao>" + descricao + "</descricao>";
			xmlIN = XmlManager.MakeXmlIn(user, "writeGrpsProcVO", xmlIN);

			// (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			xmlIN = ConstantesCRM.SVAZIO;
			xmlOUT = ConstantesCRM.SVAZIO;

		} catch (XmlException ex) {
			log.error("XmlException - ArvoreSatisfacaoImpl:setNovoQuestionario(" + user + ", String descricao) - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreSatisfacaoImpl:setNovoQuestionario", ex));

		} catch (Exception ex) {
			log.error("Exception - ArvoreSatisfacaoImpl:setNovoQuestionario(" + user + ", String descricao) - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}
}