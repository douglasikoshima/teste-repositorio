package br.com.vivo.fo.ctrls.workflow.notasura;

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
import br.com.vivo.fo.commons.utils.geral.TuxedoServiceBridge;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO;
import br.com.vivo.fo.workflow.vo.WFAtdNotaVODocument.WFAtdNotaVO;
import br.com.vivo.fo.workflow.vo.WFAtdNotasVODocument;
import br.com.vivo.fo.workflow.vo.WFAtdNotasVODocument.WFAtdNotasVO;
import br.com.vivo.fo.workflow.vo.WFAtdTipoNotasVODocument;
import br.com.vivo.fo.workflow.vo.WFAtdTipoNotasVODocument.WFAtdTipoNotasVO;
import br.com.vivo.fo.workflow.vo.WFMotivoVODocument.WFMotivoVO;

/**
 * @editor-info:code-gen control-interface="true"
 */
@Stateless(name="NotasURAFacade",mappedName="NotasURAFacade")
@Local(NotasURAFacade.class)
@Session(ejbName = "NotasURAFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class NotasURAFacadeImpl implements NotasURAFacade {
	
	@EJB
	private TuxedoServiceCall tuxedo;

	private static Logger log = new Logger("workflow");

	static final long serialVersionUID = 1L;

	/**
	 * @common:operation
	 */
	public WFAtdNotasVO pesquisarNotasURA(String user, WFAtdNotaVO notaVO)
			throws TuxedoException, FacadeException {
		String xmlIn = notaVO.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
		String xmlOut = null;
		try {
			log.debug("NotasURAFacadeImpl:pesquisarNotasURA(" + user + ")");
			// xmlOut =
			// workflowTux.atdPsqNotas(TuxedoServiceBridge.getXMLRequest(user,"WFATDNOTAS",xmlIn));
			xmlOut = ConstantesCRM.SVAZIO;// workflowTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"ATDPSQNOTAS",xmlIn));
			xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"ATDPSQNOTAS",xmlIn));
			
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
			return WFAtdNotasVODocument.Factory.parse(
					msgDocRet.getMsg().getMsgBody().xmlText())
					.getWFAtdNotasVO();
		} catch (XmlException e) {
			log.error("XmlException - NotasURAFacadeImpl:pesquisarNotasURA("
					+ user + ") - [" + e.getMessage() + "]");
			throw new FacadeException(e);
		} catch (TuxedoServiceCallerException e) {
			throw new TuxedoException(e);
		}
	}

	/**
	 * @common:operation
	 */
	public WFAtdNotaVO lerNotaURA(String user, WFAtdNotaVO notaVO)
			throws TuxedoException, FacadeException {
		notaVO.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
		// String xmlOut = null;
		
		return lerNotasURADetalhes(user, notaVO);
	}

	/**
	 * @common:operation
	 */
	public WFAcaoRetornoVO gravarNotaURA(String user, WFAtdNotaVO notaVO)
			throws TuxedoException, FacadeException {
		String xmlIn = notaVO.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
		String xmlOut = null;
		try {
			xmlOut = ConstantesCRM.SVAZIO;// workflowTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"ATDSETNOTA",xmlIn));
			xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"ATDSETNOTA",xmlIn));
			
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
			return WFAcaoRetornoVODocument.Factory.parse(
					msgDocRet.getMsg().getMsgBody().xmlText())
					.getWFAcaoRetornoVO();
		} catch (XmlException e) {
			log.error("XmlException - NotasURAFacadeImpl:gravarNotaURA(" + user
					+ ") - [" + e.getMessage() + "]");
			throw new FacadeException(e);
		} catch (TuxedoServiceCallerException e) {
			throw new TuxedoException(e);
		}
	}

	/**
	 * @common:operation
	 */
	public WFAtdNotaVO lerNotasURADetalhes(String user, WFAtdNotaVO notaVO)
			throws TuxedoException, FacadeException {
		String xmlIn = notaVO.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
		String xmlOut = null;
		try {
			log.debug("NotasURAFacadeImpl:lerNotasURADetalhes(" + user + ")");
			xmlOut = "";// workflowTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"ATDGETNOTA",xmlIn));
			xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"ATDGETNOTA",xmlIn));
			
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
			WFAtdNotasVO notas = WFAtdNotasVODocument.Factory.parse(
					msgDocRet.getMsg().getMsgBody().xmlText())
					.getWFAtdNotasVO();
			if (notas.getWFAtdNotaVOArray().length > 0)
				return notas.getWFAtdNotaVOArray()[0];
			else
				return null;
		} catch (XmlException e) {
			log.error("XmlException - NotasURAFacadeImpl:lerNotasURADetalhes("
					+ user + ") - [" + e.getMessage() + "]");
			throw new FacadeException(e);
		} catch (TuxedoServiceCallerException e) {
			throw new TuxedoException(e);
		}
	}

	/**
	 * @common:operation
	 */
	public WFMotivoVO[] getMotivos(String user, String xml)
			throws TuxedoException, FacadeException {
		String xmlIn = xml;
		String xmlOut = null;
		try {
			log.debug("NotasURAFacadeImpl:getMotivos(" + user + ", " + xml
					+ ")");
			// xmlOut =
			// workflowTux.atdGetMotivos(TuxedoServiceBridge.getXMLRequest(user,"ATDGETMOTIVO",xmlIn));
			xmlOut = ConstantesCRM.SVAZIO;// workflowTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"ATDGETMOTIVOS",xmlIn));
			xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"ATDGETMOTIVOS",xmlIn));
			
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
			WFAtdNotasVO notas = WFAtdNotasVODocument.Factory.parse(
					msgDocRet.getMsg().getMsgBody().xmlText())
					.getWFAtdNotasVO();
			if (notas.getWFAtdNotaVOArray().length > 0)
				return notas.getWFAtdNotaVOArray()[0].getWFMotivoVOArray();
			else
				return null;
		} catch (XmlException e) {
			log.error("XmlException - NotasURAFacadeImpl:getMotivos(" + user
					+ ") - [" + e.getMessage() + "]");
			throw new FacadeException(e);
		} catch (TuxedoServiceCallerException e) {
			throw new TuxedoException(e);
		}
	}

	/**
	 * @common:operation
	 */
	public WFAcaoRetornoVO gravarHistoricoNota(String user, WFAtdNotaVO notaVO)
			throws TuxedoException, FacadeException {
		String xmlIn = notaVO.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
		String xmlOut = null;
		try {
			xmlOut = ConstantesCRM.SVAZIO;// workflowTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"ATDSETCONTATO",xmlIn));
			xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"ATDSETCONTATO",xmlIn));
			
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
			return WFAcaoRetornoVODocument.Factory.parse(
					msgDocRet.getMsg().getMsgBody().xmlText())
					.getWFAcaoRetornoVO();
		} catch (XmlException e) {
			log.error("XmlException - NotasURAFacadeImpl:gravarHistoricoNota("
					+ user + ") - [" + e.getMessage() + "]");
			throw new FacadeException(e);
		} catch (TuxedoServiceCallerException e) {
			throw new TuxedoException(e);
		}
	}

	/**
	 * @common:operation
	 */
	public WFAtdTipoNotasVO getTipoNotas(String user) throws TuxedoException,
			FacadeException {
		String xmlIn = ConstantesCRM.SVAZIO;
		String xmlOut = null;
		try {
			log.debug("NotasURAFacadeImpl:getTipoNotas(" + user + ")");
			xmlOut = ConstantesCRM.SVAZIO;// workflowTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"ATDTIPONOTA",xmlIn));
			xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"ATDTIPONOTA",xmlIn));
			
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
			WFAtdTipoNotasVO tipoNotas = WFAtdTipoNotasVODocument.Factory
					.parse(msgDocRet.getMsg().getMsgBody().xmlText())
					.getWFAtdTipoNotasVO();
			return tipoNotas;
		} catch (XmlException e) {
			log.error("XmlException - NotasURAFacadeImpl:getTipoNotas(" + user
					+ ") - [" + e.getMessage() + "]");
			throw new FacadeException(e);
		} catch (TuxedoServiceCallerException e) {
			throw new TuxedoException(e);
		}

	}
}
