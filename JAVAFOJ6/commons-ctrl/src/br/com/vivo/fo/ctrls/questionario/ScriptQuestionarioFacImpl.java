package br.com.vivo.fo.ctrls.questionario;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import noNamespace.MsgDocument;

import org.apache.commons.lang.StringEscapeUtils;
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
import br.com.vivo.fo.questionario.vo.PerguntaRespondidaVODocument.PerguntaRespondidaVO;
import br.com.vivo.fo.questionario.vo.ScriptQuestionarioVODocument;
import br.com.vivo.fo.questionario.vo.ScriptQuestionarioVODocument.ScriptQuestionarioVO;
import br.com.vivo.fo.questionario.vo.StartCampanhaVODocument;
import br.com.vivo.fo.xml.XmlManager;

/**
 * @editor-info:code-gen control-interface="true"
 */
@Stateless(name="ScriptQuestionarioFac",mappedName="ScriptQuestionarioFac")
@Local(ScriptQuestionarioFac.class)
@Session(ejbName = "ScriptQuestionarioFac", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ScriptQuestionarioFacImpl implements ScriptQuestionarioFac {

	@EJB
	private TuxedoServiceCall tuxedo;

	static final long serialVersionUID = 1L;

	// private static NonCatalogLogger log = new NonCatalogLogger(ScriptQuestionarioFac.class.getName());
	private static Logger log = new Logger("questionario");

	/**
	 * @common:operation
	 */
	public String startQuestionario(String user, String[] dadosCamanha) throws Exception, FacadeException, TuxedoException {
		try {
			StringBuffer xmlInBuffer = new StringBuffer();
			/*
			 * this.dadosCampanha[0] = getRequest().getParameter("idListaConteudo"); this.dadosCampanha[1] =
			 * getRequest().getParameter("idCanalCampanha"); this.dadosCampanha[2] =
			 * getRequest().getParameter("idCliente"); this.dadosCampanha[3] =
			 * getRequest().getParameter("idSubCampanhaHistorico"); this.dadosCampanha[4] =
			 * getRequest().getParameter("idMotivoCampanha"); this.dadosCampanha[5] =
			 * getRequest().getParameter("idSubMotivoCampanha"); this.dadosCampanha[6] =
			 * getRequest().getParameter("idTipoMotivoCampanha"); this.dadosCampanha[7] =
			 * getRequest().getParameter("idTipoSubMotivoCampanha");
			 */
			xmlInBuffer.append("<idListaConteudo>").append(dadosCamanha[0]).append("</idListaConteudo>");
			xmlInBuffer.append("<idCanalCampanha>").append(dadosCamanha[1]).append("</idCanalCampanha>");
			xmlInBuffer.append("<idPessoaUsuario>").append(dadosCamanha[2]).append("</idPessoaUsuario>");
			xmlInBuffer.append("<idSubCampanhaHistorico>").append(dadosCamanha[3]).append("</idSubCampanhaHistorico>");
			xmlInBuffer.append("<idMotivoCampanha>").append(dadosCamanha[4]).append("</idMotivoCampanha>");
			xmlInBuffer.append("<idTipoStatusCampanha>").append(dadosCamanha[5]).append("</idTipoStatusCampanha>");
			xmlInBuffer.append("<idTipoMotivoCampanha>").append(dadosCamanha[6]).append("</idTipoMotivoCampanha>");
			xmlInBuffer.append("<idTipoSubMotivoCampanha>").append(dadosCamanha[7]).append("</idTipoSubMotivoCampanha>");

			String xmlIn = XmlManager.MakeXmlIn(user, "STARTCAMPANHA", xmlInBuffer.toString());
			// String xmlOut = (new ControlTuxedoCall()).execute(this, questionarioTux , "GETSERVICE", xmlIn);
			//String xmlOut = "";// questionarioTux.GETSERVICE( xmlIn );
			String xmlOut = tuxedo.callService("TuxConnector", xmlIn);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
			return StartCampanhaVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getStartCampanhaVO().getIdAtendimentoCampanha();
			// idAtendimentoCampanha = new ScriptQuestionarioVOAssembler().getIdAtendimentoCampanha(xmlOut);
			// return idAtendimentoCampanha;

		} catch (XmlException ex) {
			log.error("XmlException - ScriptQuestionarioFacImpl:startQuestionario(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		// catch(TuxedoException ex){
		// log.error("ScriptQuestionarioFacImpl:startQuestionario(" + user +") - [" + ex.getMessage() + "]");
		// throw(ex);
		// }
		catch (Exception ex) {
			log.error("Exception - ScriptQuestionarioFacImpl:startQuestionario(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public ScriptQuestionarioVO getPergunta(String user, String idPergunta, String idCanalCampanha) throws FacadeException, TuxedoException {
		// ScriptQuestionarioVO questionario = null;
		StringBuffer xmlInBuffer = new StringBuffer();
		try {
			xmlInBuffer.append("<idPergunta>").append(idPergunta).append("</idPergunta>");
			xmlInBuffer.append("<idCanalCampanha>").append(idCanalCampanha).append("</idCanalCampanha>");
			//String xmlOut = "";// questionarioTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"GETQUESTIONAR", xmlInBuffer.toString()));
			String xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"GETQUESTIONAR", xmlInBuffer.toString()));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);

			return ScriptQuestionarioVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getScriptQuestionarioVO();

		} catch (XmlException ex) {
			log.error("XmlException - ScriptQuestionarioFacImpl:getPergunta(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		/*
		 * catch(TuxedoException ex) { 
		 *     log.error("ScriptQuestionarioFacImpl:getPergunta(" + user + ") - [" +ex.getMessage() + "]");
		 *     throw(ex);
		 * }
		 */
		catch (Exception ex) {
			log.error("Exception - ScriptQuestionarioFacImpl:getPergunta(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		// return questionario;
	}

	/**
	 * @common:operation
	 */
	public void stopQuestionario(String user, String idAtendimentocampanha, long qtTempoAtendimento, PerguntaRespondidaVO[] sqRespostas, String idPessaoUsuario, String operacao, String dataAniversario) throws FacadeException, TuxedoException {
		/*
		 * Descricação das operações 1 - Normal(DEFAULT), geralmente chamada através da Fidelização 2 -
		 * Campanha-Atendimento - Botão [Terminar] não é exibido 3 - Campanha-Configuração-Simulação - Serviço
		 * STARTCAMPANHA não é chamado - Ao invés de chamar o serviço FINALCAMPANHA chama o serviço FINALCAMPSIMUL
		 */
		// --String[] param = new String[2];
		boolean isSimulacao = false;
		StringBuffer xmlInBuffer = new StringBuffer();
		try {
			if (ConstantesCRM.STHREE.equals(operacao)) {
				// idAtendimentocampanha == idCanalCampanha no caso de simulação
				xmlInBuffer.append("<idCanalCampanha>").append(idAtendimentocampanha).append("</idCanalCampanha>");
				xmlInBuffer.append("<idPessoaUsuario>").append(idPessaoUsuario).append("</idPessoaUsuario>");
				// --param[0] = "FINALCAMPSIMUL";
				// --param[1] = "stopCampanhaSimulacao";
				isSimulacao = true;

			} else {
				xmlInBuffer.append("<idAtendimentoCampanha>").append(idAtendimentocampanha).append("</idAtendimentoCampanha>");
				xmlInBuffer.append("<qtTempoAtendimento>").append(qtTempoAtendimento).append("</qtTempoAtendimento>");
				xmlInBuffer.append("<dataAniversario>").append(dataAniversario).append("</dataAniversario>");
				// --param[0] = "FINALCAMPANHA";
				// --param[1] = "stopCampanha";
			}

			for (int i = 0; i < sqRespostas.length; i++) {

				xmlInBuffer.append("<perguntaListVO>");

				xmlInBuffer.append("<idPergunta>");
				xmlInBuffer.append(sqRespostas[i].getPerguntaQuestionarioVO().getIdPergunta());
				xmlInBuffer.append("</idPergunta>");

				// if(sqRespostas[i].getTextoResposta() != null && sqRespostas[i].getTextoResposta().length()>0){
				if (sqRespostas[i].getPerguntaQuestionarioVO().getIdTipoApresentacaoPergunta().equals("5")) {
					xmlInBuffer.append("<idResposta>0</idResposta>");
					xmlInBuffer.append("<dsTextoLivre>");
					xmlInBuffer.append(StringEscapeUtils.escapeXml(sqRespostas[i].getTextoResposta()));
					xmlInBuffer.append("</dsTextoLivre>");
				}
				// else
				else if (sqRespostas[i].getPerguntaQuestionarioVO().getIdTipoApresentacaoPergunta().equals("4")) {
					xmlInBuffer.append("<idResposta>");
					xmlInBuffer.append(sqRespostas[i].getPerguntaQuestionarioVO().getRespostasQuestionarioVOArray(Integer.parseInt(sqRespostas[i].getIndiceResposta().trim())).getIdResposta());
					xmlInBuffer.append("</idResposta>");
					xmlInBuffer.append("<dsTextoLivre></dsTextoLivre>");
				} else if (sqRespostas[i].getPerguntaQuestionarioVO().getIdTipoApresentacaoPergunta().equals("2")) {
					for (int j = 0; j < sqRespostas[i].getIndicesRespostaArray().length; j++) {
						xmlInBuffer.append("<idResposta>");
						xmlInBuffer.append(sqRespostas[i].getPerguntaQuestionarioVO().getRespostasQuestionarioVOArray(Integer.parseInt(sqRespostas[i].getIndicesRespostaArray(j).trim())).getIdResposta());
						xmlInBuffer.append("</idResposta>");
						xmlInBuffer.append("<dsTextoLivre></dsTextoLivre>");
					}
				}
				xmlInBuffer.append("</perguntaListVO>");
			}
			String xmlIn;
			// String xmlOut;
			if (isSimulacao) {
				// String xmlIn = XmlManager.MakeXmlIn(user , param[0], xmlInBuffer.toString());
				// String xmlOut = (new ControlTuxedoCall()).execute(this,questionarioTux , param[1], xmlIn);
				xmlIn = XmlManager.MakeXmlIn(user, "FINALCAMPSIMUL", xmlInBuffer.toString());
				// questionarioTux.GETSERVICE( xmlIn );
				tuxedo.callService("TuxConnector", xmlIn);

			} else {
				// xmlIn = XmlManager.MakeXmlIn(user , param[0], xmlInBuffer.toString());
				// String xmlOut = (new ControlTuxedoCall()).execute(this,questionarioTux , param[1], xmlIn);
				xmlIn = XmlManager.MakeXmlIn(user, "FINALCAMPANHA", xmlInBuffer.toString());
				// questionarioTux.GETSERVICE( xmlIn );
				tuxedo.callService("TuxConnector", xmlIn);
			}

		} catch (XmlException ex) {
			log.error("XmlException - ScriptQuestionarioFacImpl:stopQuestionario(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		/*
		 * catch(TuxedoException ex) {
		 *     log.error("ScriptQuestionarioFacImpl:stopQuestionario(" + user + ") - [" +ex.getMessage() + "]");
		 *     throw(ex);
		 * }
		 */
		catch (Exception ex) {
			log.error("Exception - ScriptQuestionarioFacImpl:stopQuestionario(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}
}
