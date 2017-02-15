package br.com.vivo.fo.ctrls.workflow.atendimento;

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
import br.com.vivo.fo.admsistemas.vo.FormularioVODocument;
import br.com.vivo.fo.admsistemas.vo.FormularioVODocument.FormularioVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.commons.utils.geral.TuxedoServiceBridge;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.UsuarioVIVODocument;
import br.com.vivo.fo.usuario.vo.UsuarioVIVODocument.UsuarioVIVO;
import br.com.vivo.fo.workflow.vo.AlertaVODocument.AlertaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoDetalheVODocument;
import br.com.vivo.fo.workflow.vo.AtendimentoDetalheVODocument.AtendimentoDetalheVO;
import br.com.vivo.fo.workflow.vo.AtendimentoFilaPesquisaVODocument.AtendimentoFilaPesquisaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoHistoricoVODocument.AtendimentoHistoricoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoInBoxPesquisaVODocument.AtendimentoInBoxPesquisaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoInformacaoVODocument;
import br.com.vivo.fo.workflow.vo.AtendimentoInformacaoVODocument.AtendimentoInformacaoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTecnicoDocVODocument.AtendimentoWorkflowTecnicoDocVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowVODocument;
import br.com.vivo.fo.workflow.vo.ChamadaTelefonicaVODocument;
import br.com.vivo.fo.workflow.vo.ChamadaTelefonicaVODocument.ChamadaTelefonicaVO;
import br.com.vivo.fo.workflow.vo.ListaDadosVODocument;
import br.com.vivo.fo.workflow.vo.ListaDadosVODocument.ListaDadosVO;
import br.com.vivo.fo.workflow.vo.RWFInBoxPesquisaVODocument.RWFInBoxPesquisaVO;
import br.com.vivo.fo.workflow.vo.RetornoWFCTIResultadoVODocument;
import br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument.RetornoWFCTIVO;
import br.com.vivo.fo.workflow.vo.WFAndamentoObservacaoVODocument;
import br.com.vivo.fo.workflow.vo.WFAndamentoObservacaoVODocument.WFAndamentoObservacaoVO;
import br.com.vivo.fo.workflow.vo.WFEstadoVODocument.WFEstadoVO;
import br.com.vivo.fo.workflow.vo.WFEstadosVODocument;
import br.com.vivo.fo.workflow.vo.WFEstadosVODocument.WFEstadosVO;
import br.com.vivo.fo.workflow.vo.WFSubEstadoVODocument.WFSubEstadoVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "AtendimentoFacade", mappedName = "AtendimentoFacade")
@Local(AtendimentoFacade.class)
@Session(ejbName = "AtendimentoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AtendimentoFacadeImpl implements AtendimentoFacade {

	@EJB
	private TuxedoServiceCall tuxedo;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.RouterService.Router routerTuxControl;

	static final long serialVersionUID = 1L;

	private static Logger log = new Logger("workflow");

	/**
	 * @common:operation
	 */
	public RetornoWFCTIVO[] obtemCampanhaGrupoVO(String user, String idGrupo) throws FacadeException {
		try {
			log.debug("AtendimentoFacadeImpl:obtemCampanhaGrupoVO(" + user + ")");
			String xmlOUT = ConstantesCRM.SVAZIO;// workflowTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"WFOBTCAMPGRU","<idGrupo>" + idGrupo + "</idGrupo>"));
			xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "WFOBTCAMPGRU", "<idGrupo>" + idGrupo + "</idGrupo>"));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			return RetornoWFCTIResultadoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRetornoWFCTIResultadoVO().getRetornoWFCTIVOArray();

		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:obtemCampanhaGrupoVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoFacadeImpl:obtemCampanhaGrupoVO", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AtendimentoInformacaoVO obtemFilaInformacaoVO(String user, AtendimentoFilaPesquisaVO atendimentoFilaPesquisaVO) throws TuxedoException, FacadeException {
		try {
			log.debug("AtendimentoFacadeImpl:obtemFilaInformacaoVO(" + user + ", " + atendimentoFilaPesquisaVO + ")");
			String xmlIN = atendimentoFilaPesquisaVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

			String xmlOUT = routerTuxControl.executarScriptXMLDB(user, ConstantesCRM.CT_WF_SCRIPTDB_FILA_PESQUISA_GRUPO, xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			return AtendimentoInformacaoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAtendimentoInformacaoVO();

		} catch (TuxedoException ex) {
			log.error("AtendimentoFacadeImpl:obtemFilaInformacaoVO(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:obtemFilaInformacaoVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoFacadeImpl:obtemFilaInformacaoVO", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AtendimentoInformacaoVO obtemAtendimentoInformacaoVO(String user, AtendimentoFilaPesquisaVO atendimentoFilaPesquisaVO) throws FacadeException {
		try {
			log.debug("AtendimentoFacadeImpl:obtemAtendimentoInformacaoVO(" + user + ", " + atendimentoFilaPesquisaVO + ")");
			String xmlIN = atendimentoFilaPesquisaVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			String xmlOUT = "";// workflowTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"PSQFILA",xmlIN));
			xmlIN = XmlManager.MakeXmlIn(user, "PSQFILA", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOUT);

			return AtendimentoInformacaoVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getAtendimentoInformacaoVO();
		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:obtemAtendimentoInformacaoVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro Calling Tuxedo Function PSQFILA - " + ex.getMessage()));
		}
	}

	/**
	 * @common:operation
	 */
	public AtendimentoInformacaoVO psqFilaCRI(String user, AtendimentoFilaPesquisaVO atendimentoFilaPesquisaVO) throws FacadeException {
		try {
			log.debug("AtendimentoFacadeImpl:psqFilaCRI(" + user + ", " + atendimentoFilaPesquisaVO + ")");
			String xmlIN = atendimentoFilaPesquisaVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			String inService = TuxedoServiceBridge.getXMLRequest(user, "PSQFILACRI", xmlIN);

			String xmlOUT = ConstantesCRM.SVAZIO;// workflowTux.GETSERVICE(inService);
			xmlOUT = tuxedo.callService("TuxConnector", inService);

			MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOUT);
			return AtendimentoInformacaoVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getAtendimentoInformacaoVO();
		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:psqFilaCRI(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro Calling Tuxedo Function PSQFILACRI - " + ex.getMessage()));
		}
	}

	/**
	 * @common:operation
	 */
	public WFSubEstadoVO[] obtemSubEstadoVO(String user, WFEstadoVO wFEstadoVO) throws TuxedoException, FacadeException {
		try {
			log.debug("AtendimentoFacadeImpl:obtemSubEstadoVO(" + user + ", " + wFEstadoVO + ")");
			String xmlIN = wFEstadoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

			String xmlOUT = routerTuxControl.executarScriptXMLDB(user,ConstantesCRM.CT_WF_SCRIPTDB_FILA_PESQUISA_SUBESTADO,xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			return AtendimentoInformacaoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAtendimentoInformacaoVO().getWFSubEstadoVOArray();

		} catch (TuxedoException ex) {
			log.error("AtendimentoFacadeImpl:obtemSubEstadoVO(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:obtemSubEstadoVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoFacadeImpl:obtemSubEstadoVO", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AtendimentoInformacaoVO obtemAtendimentoInformacaoVOInBox(String user, AtendimentoInBoxPesquisaVO atendimentoInBoxPesquisaVO) throws TuxedoException, FacadeException {
		try {
			log.debug("AtendimentoFacadeImpl:obtemAtendimentoInformacaoVOInBox(" + user + ", " + atendimentoInBoxPesquisaVO + ")");
			String xmlIN = atendimentoInBoxPesquisaVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

			String xmlOUT = routerTuxControl.executarScriptXMLDB(user,ConstantesCRM.CT_WF_SCRIPTDB_INBOX_PESQUISA, xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			return AtendimentoInformacaoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAtendimentoInformacaoVO();

		} catch (TuxedoException ex) {
			log.error("AtendimentoFacadeImpl:obtemAtendimentoInformacaoVOInBox(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:obtemAtendimentoInformacaoVOInBox(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoFacadeImpl:obtemAtendimentoInformacaoVOInBox", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public UsuarioVIVO obtemUsuarioVIVO(String user, UsuarioVIVO usuarioVIVO) throws TuxedoException, FacadeException {
		try {
			log.debug("AtendimentoFacadeImpl:obtemUsuarioVIVO(" + user + ", " + usuarioVIVO + ")");
			String xmlIN = usuarioVIVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

			String xmlOUT = routerTuxControl.executarScriptXMLDB(user,ConstantesCRM.CT_WF_SCRIPTDB_INBOX_PESQUISA_USUARIO, xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			return UsuarioVIVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getUsuarioVIVO();

		} catch (TuxedoException ex) {
			log.error("AtendimentoFacadeImpl:obtemUsuarioVIVO(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:obtemUsuarioVIVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoFacadeImpl:obtemUsuarioVIVO", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void analistaDisponivel(String user, UsuarioVIVO usuarioVIVO) throws TuxedoException, FacadeException {
		try {
			log.debug("AtendimentoFacadeImpl:analistaDisponivel(" + user + ", " + usuarioVIVO + ")");
			String xmlIN = usuarioVIVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

			routerTuxControl.executarScriptXMLDB(user,ConstantesCRM.CT_WF_SCRIPTDB_INBOX_DISPONIBILIDADE, xmlIN);

		} catch (TuxedoException ex) {
			log.error("AtendimentoFacadeImpl:analistaDisponivel(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:analistaDisponivel(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoFacadeImpl:analistaDisponivel", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AlertaVO[] obtemAlertaVO(String user, String idAtendimento, String isSimplificado) throws TuxedoException, FacadeException {
		try {
			log.debug("AtendimentoFacadeImpl:obtemAlertaVO(" + user + ", " + idAtendimento + ")");
			StringBuffer xmlIN = new StringBuffer();
			xmlIN.append("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");
			xmlIN.append("<isSimplificado>").append(isSimplificado).append("</isSimplificado>");
			String xmlIn = XmlManager.MakeXmlIn(user, "LSTALERTAATD", xmlIN.toString());
			// String xmlOUT = (new ControlTuxedoCall()).execute(this,workflowTux, "GETSERVICE", xmlIn);
			String xmlOUT = tuxedo.callService("TuxConnector", xmlIn);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			return AtendimentoVODocument.Factory.parse(xmlOUT).getAtendimentoVO().getAlertaVOArray();

		} catch (TuxedoServiceCallerException ex) {
			log.error("AtendimentoFacadeImpl:obtemAlertaVO(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:obtemAlertaVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoFacadeImpl:obtemAlertaVO", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AlertaVO[] obtemAlertaVOEx(String user, String idAtendimento, String isSimplificado) throws TuxedoException, FacadeException {
		try {
			log.debug("AtendimentoFacadeImpl:obtemAlertaVOEx(" + user + ", " + idAtendimento + ")");
			StringBuffer xmlIN = new StringBuffer();
			xmlIN.append("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");
			xmlIN.append("<isSimplificado>").append(isSimplificado).append("</isSimplificado>");
			String xmlIn = XmlManager.MakeXmlIn(user, "LSTALERTAATDEX", xmlIN.toString());
			// String xmlOUT = (new ControlTuxedoCall()).execute(this, workflowTux, "GETSERVICE", xmlIn);
			String xmlOUT = tuxedo.callService("TuxConnector", xmlIn);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			return AtendimentoVODocument.Factory.parse(xmlOUT).getAtendimentoVO().getAlertaVOArray();

		} catch (TuxedoServiceCallerException ex) {
			log.error("AtendimentoFacadeImpl:obtemAlertaVOEx(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:obtemAlertaVOEx(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoFacadeImpl:obtemAlertaVOEx", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AlertaVO[] obtemPrazos(String user) throws FacadeException {
		try {
			String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDGETPRAZOS", ConstantesCRM.SVAZIO);
			String xmlOut = ConstantesCRM.SVAZIO;// workflowTux.GETSERVICE(inService);
			xmlOut = tuxedo.callService("TuxConnector", inService);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
			return AtendimentoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAtendimentoVO().getAlertaVOArray();
		} catch (XmlException ex) {
			log.error("AtendimentoFacadeImpl:obtemPrazos(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoFacadeImpl:obtemPrazos", ex));
		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:obtemPrazos(" + user + ") - [" + ex.getMessage() + "]");
			throw new FacadeException(ex);
		}
	}

	/**
	 * @common:operation
	 */
	public AlertaVO[] obtemPrazosCRI(String user) throws FacadeException {
		try {
			StringBuffer xmlIN = new StringBuffer();
			xmlIN.append("<inCRI>1</inCRI>");
			String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDGETPRAZOS", xmlIN.toString());

			String xmlOut = ConstantesCRM.SVAZIO;// workflowTux.GETSERVICE(inService);
			xmlOut = tuxedo.callService("TuxConnector", inService);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
			return AtendimentoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAtendimentoVO().getAlertaVOArray();

		} catch (XmlException ex) {
			log.error("AtendimentoFacadeImpl:obtemPrazosCRI(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoFacadeImpl:obtemPrazosCRI", ex));

		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:obtemPrazosCRI(" + user + ") - [" + ex.getMessage() + "]");
			throw new FacadeException(ex);
		}
	}

	/**
	 * @common:operation
	 */
	public WFAndamentoObservacaoVO obtemComentarioVO(String user, String idAndamento) throws TuxedoException, FacadeException {
		try {
			log.debug("AtendimentoFacadeImpl:obtemComentarioVO(" + user + ", " + idAndamento + ")");
			String xmlIN = "<idAndamento>" + idAndamento + "</idAndamento>";

			String xmlOUT = routerTuxControl.executarScriptXMLDB(user, 650, xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			return WFAndamentoObservacaoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAndamentoObservacaoVO();

		} catch (TuxedoException ex) {
			log.error("AtendimentoFacadeImpl:obtemComentarioVO(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:obtemComentarioVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoFacadeImpl:obtemComentarioVO", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AtendimentoInformacaoVO obtemAtendimentoInformacaoVOFechamentoMassa(String user, String idContato, String comentario, String login, String dtSuspeitoFim, String dtSuspeitoInicio, UsuarioVIVO usuarioVIVO) throws FacadeException {

		try {
			log.debug("AtendimentoFacadeImpl:obtemAtendimentoInformacaoVOFechamentoMassa(" + user + idContato + comentario + login + dtSuspeitoFim + dtSuspeitoInicio + usuarioVIVO + ")");
			StringBuffer xmlIN = new StringBuffer();
			xmlIN.append("<AtendimentoFechamentoMassaPesquisaVO>");
			xmlIN.append("<inMassa>1</inMassa>");
			xmlIN.append("<idContato>" + idContato + "</idContato>");
			xmlIN.append("<nmLoginUsuario>" + login + "</nmLoginUsuario>");
			xmlIN.append("<dtSuspeitoInicio>" + dtSuspeitoInicio + "</dtSuspeitoInicio>");
			xmlIN.append("<dtSuspeitoFim>" + dtSuspeitoFim + "</dtSuspeitoFim>");
			xmlIN.append("</AtendimentoFechamentoMassaPesquisaVO>");

			String xmlOUT = "";// workflowTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"PSQFILA",xmlIN.toString()));
			xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"PSQFILA",xmlIN.toString()));

			MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOUT);
			return AtendimentoInformacaoVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getAtendimentoInformacaoVO();
		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:obtemAtendimentoInformacaoVOFechamentoMassa(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoFacadeImpl:obtemAtendimentoInformacaoVOFechamentoMassa", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AtendimentoDetalheVO obtemAtendimentoDetalheVO(String user, String idAtendimento) throws TuxedoException, FacadeException {
		try {
			log.debug("AtendimentoFacadeImpl:obtemAtendimentoDetalheVO(" + user + ", " + idAtendimento + ")");
			String xmlIN = "<idPessoaUsuario>" + user + "</idPessoaUsuario>";
			xmlIN += "<idAtendimento>" + idAtendimento + "</idAtendimento>";

			String xmlOUT = routerTuxControl.executarScriptXMLDB(user, ConstantesCRM.CT_WF_SCRIPTDB_DETALHE_PROCESSO, xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			return AtendimentoDetalheVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAtendimentoDetalheVO();

		} catch (TuxedoException ex) {
			log.error("AtendimentoFacadeImpl:obtemAtendimentoDetalheVO(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:obtemAtendimentoDetalheVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoFacadeImpl:obtemAtendimentoDetalheVO", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AtendimentoHistoricoVO[] obtemAtendimentoHistoricoVO(String user, String idAtendimento, String idEstado, String idSubEstado, String inCRI, String inRC) throws FacadeException {
		try {
			log.debug("AtendimentoFacadeImpl:obtemAtendimentoHistoricoVO(" + user + ", " + idAtendimento + ", " + idEstado + ", " + idSubEstado + ")");

			String xmlIN = "<idUsuario>" + user + "</idUsuario>";
			xmlIN += "<idAtendimento>" + idAtendimento + "</idAtendimento>";
			xmlIN += "<idEstado>" + idEstado + "</idEstado>";
			xmlIN += "<idSubEstado>" + idSubEstado + "</idSubEstado>";
			xmlIN += "<inCRI>" + inCRI + "</inCRI>";
			xmlIN += "<inRC>" + inRC + "</inRC>";

			String xmlOut = "";// workflowTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,
			// "ATDHISTORICO", xmlIN));
			xmlIN = XmlManager.MakeXmlIn(user, "ATDHISTORICO", xmlIN);
			xmlOut = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
			return AtendimentoDetalheVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAtendimentoDetalheVO().getAtendimentoVO().getAtendimentoHistoricoVOArray();
		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:obtemAtendimentoHistoricoVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AtendimentoHistoricoVO[] obtemAtendimentoHistoricoVOEx(String user, String idAtendimento, String idEstado, String idSubEstado, String inCRI, String inRC) throws FacadeException {
		try {
			log.debug("AtendimentoFacadeImpl:obtemAtendimentoHistoricoVOEx(" + user + ", " + idAtendimento + ", " + idEstado + ", " + idSubEstado + ")");

			String xmlIN = "<idUsuario>" + user + "</idUsuario>";
			xmlIN += "<idAtendimento>" + idAtendimento + "</idAtendimento>";
			xmlIN += "<idEstado>" + idEstado + "</idEstado>";
			xmlIN += "<idSubEstado>" + idSubEstado + "</idSubEstado>";
			xmlIN += "<inCRI>" + inCRI + "</inCRI>";
			xmlIN += "<inRC>" + inRC + "</inRC>";

			String xmlOut = "";// workflowTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,
			// "ATDHISTORICOEX", xmlIN));
			xmlIN = XmlManager.MakeXmlIn(user, "ATDHISTORICOEX", xmlIN);
			xmlOut = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
			return AtendimentoDetalheVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAtendimentoDetalheVO().getAtendimentoVO().getAtendimentoHistoricoVOArray();
		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:obtemAtendimentoHistoricoVOEx(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AtendimentoWorkflowTecnicoDocVO[] obtemAtendimentoWorkflowTecnicoDocVO(String user, String idAtendimento) throws TuxedoException, FacadeException {
		try {
			log.debug("AtendimentoFacadeImpl:obtemAtendimentoWorkflowTecnicoDocVO(" + user + ", " + idAtendimento + ")");
			String xmlIN = "<idAtendimento>" + idAtendimento + "</idAtendimento>";

			String xmlOUT = routerTuxControl.executarScriptXMLDB(user,ConstantesCRM.CT_WF_SCRIPTDB_FECHAMENTO_MASSA_PESQUISA_DOC_TEC,xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			return AtendimentoWorkflowVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAtendimentoWorkflowVO().getAtendimentoWorkflowTecnicoVO().getAtendimentoWorkflowTecnicoDocVOArray();

		} catch (TuxedoException ex) {
			log.error("AtendimentoFacadeImpl:obtemAtendimentoWorkflowTecnicoDocVO(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:obtemAtendimentoWorkflowTecnicoDocVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoFacadeImpl:obtemAtendimentoWorkflowTecnicoDocVO", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public FormularioVO obtemFormularioVO(String user) throws TuxedoException, FacadeException {
		try {
			log.debug("AtendimentoFacadeImpl:obtemFormularioVO(" + user + ")");
			String xmlIN = "";
			xmlIN = XmlManager.MakeXmlIn(user, "ATDCPFRMFILA", xmlIN);
			// String xmlOUT = (new ControlTuxedoCall()).execute(this, workflowTux, "GETSERVICE", xmlIN);
			String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			return FormularioVODocument.Factory.parse(xmlOUT).getFormularioVO();

		} catch (TuxedoServiceCallerException ex) {
			log.error("AtendimentoFacadeImpl:obtemFormularioVO(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:obtemFormularioVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoFacadeImpl:obtemFormularioVO", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public FormularioVO obtemValoresDominio(String user, String idCampo, String idTipoLinha, String idRegional) throws TuxedoException, FacadeException {
		try {
			log.debug("AtendimentoFacadeImpl:obtemValoresDominio(" + user + ")");
			String xmlIN = "<idCampo>" + idCampo + "</idCampo>\n" + "<idTipoLinha>" + idTipoLinha + "</idTipoLinha>\n" + "<idUfOperadora>" + idRegional + "</idUfOperadora>\n";
			xmlIN = XmlManager.MakeXmlIn(user, "WFDOMCAMPODIN", xmlIN);
			// String xmlOUT = (new ControlTuxedoCall()).execute(this, 0workflowTux, "GETSERVICE", xmlIN);
			String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			return FormularioVODocument.Factory.parse(xmlOUT).getFormularioVO();

		} catch (TuxedoServiceCallerException ex) {
			log.error("AtendimentoFacadeImpl:obtemValoresDominio(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:obtemValoresDominio(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoFacadeImpl:obtemValoresDominio", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public RetornoWFCTIVO[] obtemRetornoWFCTIVO(String user) throws TuxedoException, FacadeException {
		try {
			log.debug("AtendimentoFacadeImpl:obtemRetornoWFCTIVO(" + user + ")");
			String xmlIN = "<idUsuario>" + user + "</idUsuario>";
			String xmlOUT = routerTuxControl.executarScriptXMLDB(user,ConstantesCRM.CT_WF_SCRIPTDB_INBOX_CAMPANHA_RETORNO_PESQUISAR,xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			return RetornoWFCTIResultadoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRetornoWFCTIResultadoVO().getRetornoWFCTIVOArray();

		} catch (TuxedoException ex) {
			log.error("AtendimentoFacadeImpl:obtemRetornoWFCTIVO(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:obtemRetornoWFCTIVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoFacadeImpl:obtemRetornoWFCTIVO", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public ChamadaTelefonicaVO obtemChamadaTelefonicaVO(String user, String idPessoaDePara, String tipoPessoa, String inChamada) throws FacadeException {
		try {
			log.debug("AtendimentoFacadeImpl:obtemChamadaTelefonicaVO(" + user + ")");
			String xmlIN = "<idPessoaDePara>" + idPessoaDePara + "</idPessoaDePara>";
			xmlIN += "<tipoPessoa>" + tipoPessoa + "</tipoPessoa>";
			if (inChamada != null) {
				xmlIN += "<inChamada>1</inChamada>";
			}
			String xmlOUT = "";// workflowTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"INCLCHAMTEL",xmlIN));
			xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"INCLCHAMTEL",xmlIN));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			return ChamadaTelefonicaVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getChamadaTelefonicaVO();
		} catch (XmlException ex) {
			log.error("AtendimentoFacadeImpl:obtemChamadaTelefonicaVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoFacadeImpl:obtemChamadaTelefonicaVO", ex));
		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:obtemChamadaTelefonicaVO(" + user + ") - [" + ex.getMessage() + "]");
			throw new FacadeException(ex);
		}
	}

	/**
	 * @common:operation
	 */
	public ChamadaTelefonicaVO obtemChamadaTelefonicaTdVO(String user) throws FacadeException {
		try {
			log.debug("AtendimentoFacadeImpl:obtemChamadaTelefonicaTdVO(" + user + ")");
			String xmlIN = ConstantesCRM.SVAZIO;
			String xmlOUT = ConstantesCRM.SVAZIO;// workflowTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"INCLCHAMTEL",xmlIN));
			xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"INCLCHAMTEL",xmlIN));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			return ChamadaTelefonicaVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getChamadaTelefonicaVO();
		} catch (XmlException ex) {
			log.error("AtendimentoFacadeImpl:obtemChamadaTelefonicaTdVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoFacadeImpl:obtemChamadaTelefonicaTdVO", ex));
		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:obtemChamadaTelefonicaTdVO(" + user + ") - [" + ex.getMessage() + "]");
			throw new FacadeException(ex);
		}
	}

	/**
	 * @common:operation
	 */
	public void obtemProximoProcesso(String user) throws TuxedoException, FacadeException {
		try {
			log.debug("AtendimentoFacadeImpl:obtemProximoProcesso(" + user + ")");
			String xmlIN = "";
			routerTuxControl.executarScriptXMLDB(user, ConstantesCRM.CT_WF_SCRIPTDB_INBOX_PROXIMO_PROCESSO, xmlIN);

		} catch (TuxedoException ex) {
			log.error("AtendimentoFacadeImpl:obtemProximoProcesso(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:obtemProximoProcesso(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoFacadeImpl:obtemProximoProcesso", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public WFEstadosVO getWFEstadosVO(String user, boolean inPortabilidade) throws Exception {
		log.debug("AtendimentoFacadeImpl:getWFEstadosVO(" + user + ") >>");
		String xmlIN = ConstantesCRM.SVAZIO;
		if (inPortabilidade) {
			xmlIN = "<inPortout>1</inPortout>";
		}
		String xmlOUT = ConstantesCRM.SVAZIO;// workflowTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user, "ATDPESQESTSUB", xmlIN));
		xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "ATDPESQESTSUB", xmlIN));

		MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
		return WFEstadosVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFEstadosVO();
	}

	/**
	 * @common:operation
	 */
	public WFEstadosVO getWFEstadosVOEx(String user, boolean inPortabilidade) throws Exception {
		log.debug("AtendimentoFacadeImpl:getWFEstadosVOEx(" + user + ") >>");
		String xmlIN = ConstantesCRM.SVAZIO;
		if (inPortabilidade) {
			xmlIN = "<inPortout>1</inPortout>";
		}
		String xmlOUT = ConstantesCRM.SVAZIO;// workflowTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user, "ATDPSQESTSUBEX", xmlIN));
		xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "ATDPSQESTSUBEX", xmlIN));

		MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
		return WFEstadosVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFEstadosVO();
	}

	/**
	 * @common:operation
	 */
	public AtendimentoInformacaoVO psqFilaResp(String user, RWFInBoxPesquisaVO pesquisa) throws FacadeException {
		try {
			log.debug("AtendimentoFacadeImpl:psqFilaResp(" + user + ")");
			StringBuffer xmlIn = new StringBuffer();
			xmlIn.append(pesquisa.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO));
			String inService = TuxedoServiceBridge.getXMLRequest(user, "PSQFILARC", xmlIn.toString());
			String xmlOUT = ConstantesCRM.SVAZIO;// workflowTux.GETSERVICE(inService);
			xmlOUT = tuxedo.callService("TuxConnector", inService);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			return AtendimentoInformacaoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAtendimentoInformacaoVO();
		} catch (XmlException ex) {
			log.error("AtendimentoFacadeImpl:psqFilaResp(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoFacadeImpl:psqFilaResp", ex));
		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:psqFilaResp(" + user + ") - [" + ex.getMessage() + "]");
			throw new FacadeException(ex);
		}
	}

	/**
	 * @common:operation
	 */
	public ListaDadosVO obtemLinhasAssociadas(String user, String idAtendimento, String idPessoa) throws TuxedoException, FacadeException {
		try {
			log.debug("AtendimentoFacadeImpl:obtemLinhasAssociadas(" + user + ")");
			String xmlIN = new StringBuffer("<tpOperacao>3</tpOperacao>").append("<idAtendimento>").append(idAtendimento).append("</idAtendimento>").append("<idPessoa>").append(idPessoa).append("</idPessoa>").toString();
			xmlIN = XmlManager.MakeXmlIn(user, "CONSULTACONTA", xmlIN);
			// String xmlOUT = (new ControlTuxedoCall()).execute(this,workflowTux, "GETSERVICE", xmlIN);
			String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			return ListaDadosVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaDadosVO();
		} catch (TuxedoServiceCallerException ex) {
			log.error("AtendimentoFacadeImpl:obtemLinhasAssociadas(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);
		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:obtemLinhasAssociadas(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoFacadeImpl:obtemLinhasAssociadas", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public ListaDadosVO obtemLinhasAssociadasEx(String user, String idAtendimento, String idPessoa) throws TuxedoException, FacadeException {
		try {
			log.debug("AtendimentoFacadeImpl:obtemLinhasAssociadasEx(" + user + ")");
			String xmlIN = new StringBuffer("<tpOperacao>3</tpOperacao>").append("<idAtendimento>").append(idAtendimento).append("</idAtendimento>").append("<idPessoa>").append(idPessoa).append("</idPessoa>").toString();
			xmlIN = XmlManager.MakeXmlIn(user, "CONSULTACTAEX", xmlIN);
			// String xmlOUT = (new ControlTuxedoCall()).execute(this,workflowTux, "GETSERVICE", xmlIN);
			String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			return ListaDadosVODocument.Factory.parse(xmlOUT).getListaDadosVO();
		} catch (TuxedoServiceCallerException ex) {
			log.error("AtendimentoFacadeImpl:obtemLinhasAssociadasEx(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);
		} catch (Exception ex) {
			log.error("AtendimentoFacadeImpl:obtemLinhasAssociadasEx(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AtendimentoFacadeImpl:obtemLinhasAssociadasEx", ex));
		}
	}

}