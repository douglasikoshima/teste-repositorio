package br.com.vivo.fo.ctrls.workflow.relacionamento;

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
import br.com.vivo.fo.commons.utils.geral.TuxedoServiceBridge;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.AtendimentoPesquisaVODocument;
import br.com.vivo.fo.workflow.vo.AtendimentoPesquisaVODocument.AtendimentoPesquisaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoRelacionamentosVODocument;
import br.com.vivo.fo.workflow.vo.AtendimentoRelacionamentosVODocument.AtendimentoRelacionamentosVO;

@Stateless(name="RelacionamentoFacade",mappedName="RelacionamentoFacade")
@Local(RelacionamentoFacade.class)
@Session(ejbName = "RelacionamentoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RelacionamentoFacadeImpl implements RelacionamentoFacade {

	@EJB
	private TuxedoServiceCall tuxedo;
	private static Logger log = new Logger("workflow");

	//@EJB private br.com.vivo.fo.ctrls.workflow.RouterService.Router routerTuxControl;

	static final long serialVersionUID = 1L;
	private StringBuffer xmlIN = null;
	private String xmlOUT = ConstantesCRM.SVAZIO;

	/**
	 * @common:operation
	 */
	public AtendimentoPesquisaVO obterEstados(String user) throws TuxedoException, FacadeException {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("RelacionamentoFacadeImpl:obterEstados(");
			sb.append(user);
			sb.append(")");
			log.debug(sb.toString());
			//xmlOUT = workflowTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user, "ATDPESQEST", "<rsBody></rsBody>"));
			xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "ATDPESQEST", "<rsBody></rsBody>"));

			MsgDocument atdoc = MsgDocument.Factory.parse(xmlOUT);
			AtendimentoPesquisaVODocument atendimentoPesquisaVOdoc = AtendimentoPesquisaVODocument.Factory.parse(atdoc.getMsg().getMsgBody().xmlText());    //new RelacionamentoAssembler().montarAtendimentoPesquisaVO(xmlOUT);
			return (atendimentoPesquisaVOdoc.getAtendimentoPesquisaVO());
		} catch (Exception ex) {
			log.error("Exception - RelacionamentoFacadeImpl:obterEstados(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AtendimentoPesquisaVO pesquisarSubEstado(String user, String idEstado) throws TuxedoException, FacadeException {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("RelacionamentoFacadeImpl:pesquisarSubEstado(").append(user).append(", ").append(idEstado).append(")");
			log.debug(sb.toString());
			StringBuffer sbIn = new StringBuffer();
			sbIn.append("<idEstado>").append(idEstado).append("</idEstado>");
			String xmlIN = sbIn.toString();
			//xmlOUT = workflowTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user, "ATDPESQSUBEST", xmlIN));
			xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "ATDPESQSUBEST", xmlIN));

			MsgDocument atdoc = MsgDocument.Factory.parse(xmlOUT);
			AtendimentoPesquisaVODocument atendimentoPesquisaVOdoc = AtendimentoPesquisaVODocument.Factory.parse(atdoc.getMsg().getMsgBody().xmlText());    //new RelacionamentoAssembler().montarAtendimentoPesquisaVO(xmlOUT);
			return (atendimentoPesquisaVOdoc.getAtendimentoPesquisaVO());
		} catch (Exception ex) {
			log.error("Exception - RelacionamentoFacadeImpl:pesquisarSubEstado(" + user + ", " + idEstado + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AtendimentoRelacionamentosVO pesquisarRelacionamento(String user, AtendimentoPesquisaVO atPesquisaVO) throws TuxedoException, FacadeException {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("RelacionamentoFacadeImpl:pesquisarRelacionamento(").append(user).append(", ").append(atPesquisaVO).append(")");
			log.debug(sb.toString());
			AtendimentoRelacionamentosVO atendimentoRelacionamentosVO = null;
			xmlIN = new StringBuffer();
			xmlIN.append(atPesquisaVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
			//xmlOUT = workflowTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user, "PSQRELACIONAM", xmlIN.toString()));
			xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "PSQRELACIONAM", xmlIN.toString()));

			MsgDocument atdoc = MsgDocument.Factory.parse(xmlOUT);
			atendimentoRelacionamentosVO = AtendimentoRelacionamentosVODocument.Factory.parse(atdoc.getMsg().getMsgBody().xmlText()).getAtendimentoRelacionamentosVO();
			return (atendimentoRelacionamentosVO);
		} catch (XmlException ex) {
			log.error("XmlException - RelacionamentoFacadeImpl:pesquisarRelacionamento(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: RelacionamentoFacadeImpl:pesquisarRelacionamento", ex));
		} catch (Exception ex) {
			log.error("Exception - RelacionamentoFacadeImpl:pesquisarRelacionamento(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}
}
