package questionario;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import br.com.vivo.fo.campanha.vo.CampanhaParametrosVODocument.CampanhaParametrosVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.questionario.DadosQuestionario;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.questionario.vo.PerguntaQuestionarioVODocument.PerguntaQuestionarioVO;
import br.com.vivo.fo.questionario.vo.PerguntaRespondidaVODocument.PerguntaRespondidaVO;
import br.com.vivo.fo.questionario.vo.RespostasQuestionarioVODocument.RespostasQuestionarioVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

import fidelizacao.FidelizacaoController.ActionRetencaoForm;
import fidelizacao.FidelizacaoController.DadosClienteForm;

public class QuestionarioController extends AbstractAction {

	/*
	 * PARA QUE ESTE FLUXO SEJA UTILIZADO CORRETAMENTE SÃO NECESSARIOS OS SEGUINTES PARAMENTROS: - UMA STRING QUE APONTE
	 * QUAL É O JSP/JPF PARA O QUAL ELE TERA QUE VOLTAR CASO O USUARIO CLIQUE NO BOTÃO VOLTAR ANTES DE REALIZAR ALGUMA
	 * PERGUNTA DO QUESTIONARIO - OS PARAMETROS QUE ESPECIFIQUEM OS DADOS DA CAMPANHA QUE SERÁ REALIZADA E QUAL O CANAL
	 * DE ORIGEM OBS: INFORMAÇÕES SOBRE ESTES PARAMETROS ESTÃO NA DESCRIÇÃO DO ARRAY "dadosCampanha"
	 */

	private static final long serialVersionUID = 2483222499957587619L;

	@EJB
	private br.com.vivo.fo.ctrls.questionario.ScriptQuestionarioFac questionarioFac;

	@EJB
	private br.com.vivo.fo.ctrls.campanha.manter.CampanhaFacade campanhaFac;

	/*
	 * Descricao do array dadosCampanha: [0] = idListaConteudo [1] = idCanalCampanha [2] = idCliente [3] =
	 * idSubCampanhaHistorico [4] = idMotivoCampanha [5] = idSubMotivoCampanha [6] = idTipoMotivoCampanha [7] =
	 * idTipoSubMotivoCampanha
	 */
	private final static Logger log = new Logger("questionario");

	private DadosQuestionario dadosQst;
	protected global.Global globalApp = new global.Global();
	private QuestionarioForm questionario = null;
	private SalvaAgendaAniversarioForm salvaAgendaAniversarioForm = null;
	private IniciaQuestionarioForm iniciaQuestionarioForm = new IniciaQuestionarioForm();
	// FORMULARIOS PARA CONTROLE DE RETENCAO
	private DadosClienteForm retencaoDadosClienteForm;
	private ActionRetencaoForm retencaoActionRetencaForm;
	// FIM

	private static final String OPERACAO_DEFAULT = ConstantesCRM.SONE;
	// private static final String OPERACAO_ATENDIMENTO = ConstantesCRM.STWO;
	private static final String OPERACAO_SIMULACAO = ConstantesCRM.STHREE;
	private static final String OPERACAO_TELA_INICIAL = ConstantesCRM.SFOUR;
	private static final String CHECK_BOX = "CHK";
	private static final String RADIO_BOTTON = "RAD";
	private static final String TEXTO = "TXT";

	private String nrTempoMedio;

	private String getNrTempoMedio() {
		if (this.nrTempoMedio == null) {
			return ConstantesCRM.SZERO;
		} else {
			return this.nrTempoMedio;
		}
	}

	private void setNrTempoMedio(String nrTempoMedio) {
		this.nrTempoMedio = nrTempoMedio;
	}

	/*
	 * Descricação das operações 1 - Normal(DEFAULT), geralmente chamada através da Fidelização 2 - Campanha-Atendimento
	 * - Botão [Terminar] não é exibido 3 - Campanha-Configuração-Simulação - Serviço STARTCAMPANHA não é chamado - Ao
	 * invés de chamar o serviço FINALCAMPANHA chama o serviço FINALCAMPSIMUL
	 */
	// private String operacao = ConstantesCRM.SONE;
	// private CampanhaParametrosVO campanhaParametrosVO = null;

	public QuestionarioForm getQuestionario() {
		if (questionario == null) {
			questionario = new QuestionarioForm();
		}
		return this.questionario;
	}

	public DadosQuestionario getDadosQst() {
		if (this.dadosQst == null) {
			this.dadosQst = new DadosQuestionario();
		}
		return this.dadosQst;
	}

	public void setDadosQst(DadosQuestionario dadosQst) {
		this.dadosQst = dadosQst;
	}

	public SalvaAgendaAniversarioForm getSalvaAgendaAniversarioForm() {
		if (salvaAgendaAniversarioForm == null) {
			salvaAgendaAniversarioForm = new SalvaAgendaAniversarioForm();
		}

		return this.salvaAgendaAniversarioForm;
	}

	private String getOperacao() {
		return getDadosQst().getOperacao();
	}

	public void setOperacao(String operacao) {
		getDadosQst().setOperacao(operacao);
	}

	public String getTempoCorrido() {
		// if(filaRespostasFac != null){
		return Long.toString(getDadosQst().getTempoAtendimentoSegundos());
		// } else {
		// return ConstantesCRM.SZERO;
		// }
	}

	public String getTempoTotal() throws Exception {
		if (!ConstantesCRM.SZERO.equals(this.getNrTempoMedio())) {
			try {
				return Integer.toString(Integer.parseInt(this.getNrTempoMedio()) * 60);
			} catch (Exception e) {
				return ConstantesCRM.SZERO;
			}
		} else {
			return ConstantesCRM.SZERO;
		}
	}

	private void setDadosCampanha(HttpServletRequest request) throws Exception {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		/*
		 * // DADOS FAQUE PARA TESTE DE FUNCIONALIDADE this.dadosCampanha[0] = "3"; this.dadosCampanha[1] = "140";
		 * this.dadosCampanha[2] = "1"; this.dadosCampanha[3] = "0"; this.dadosCampanha[4] = "1"; this.dadosCampanha[5]
		 * = "0"; this.dadosCampanha[6] = "0"; this.dadosCampanha[7] = "0";
		 */

		getDadosQst().setDados(0, (String) request.getSession().getAttribute("idListaConteudo"));
		getDadosQst().setDados(1, (String) request.getSession().getAttribute("idCanalCampanha"));
		getDadosQst().setDados(3, (String) request.getSession().getAttribute("idSubCampanhaHistorico"));
		// Teste, acho que não usa estes dados!
		// --getDadosQst().setDados(5, (String)request.getAttribute("idSubMotivoCampanha"));
		// --getDadosQst().setDados(6, (String)request.getAttribute("idTipoMotivoCampanha"));
		// --getDadosQst().setDados(7, (String)request.getAttribute("idTipoSubMotivoCampanha"));
		// --getDadosQst().setDados(4, (String)request.getAttribute("idMotivoCampanha"));
		// --getDadosQst().setDados(2, (String)request.getAttribute("idCliente"));
		getDadosQst().setDados(2, ConstantesCRM.SONE);
		getDadosQst().setDados(4, ConstantesCRM.SONE);
		getDadosQst().setDados(5, ConstantesCRM.SZERO);
		getDadosQst().setDados(6, ConstantesCRM.SZERO);
		getDadosQst().setDados(7, ConstantesCRM.SZERO);

		// Consistencia de operação
		if (request.getAttribute("operacao") != null) {
			setOperacao((String) request.getAttribute("operacao"));
		}

		/*
		 * Para operação 1(Fidelização), tenho que retornar alguns valores, no voltar da primeira pergunta!
		 */
		if (getOperacao().equals(OPERACAO_DEFAULT)) {
			ActionRetencaoForm actionRetencaoForm = (ActionRetencaoForm) request.getAttribute("actionRetencaoForm");

			iniciaQuestionarioForm.setActionRetencaoForm(actionRetencaoForm);
			// Priscilla Paula - 25/02/2005
			// se retencao - atendimento, recupera formularios do request para não perder os dados posteriormente em
			// retencao
			retencaoDadosClienteForm = (DadosClienteForm) request.getAttribute("retencao_DadosClienteForm");
			retencaoActionRetencaForm = (ActionRetencaoForm) request.getAttribute("retencao_ActionRetencaoForm");
			// fim Priscilla Paula
		}

		CampanhaParametrosVO campanhaParametrosVO = campanhaFac.getParametrosSubCampanha(user, getDadosQst().getDados(1));
		if (campanhaParametrosVO != null) {
			this.setNrTempoMedio(campanhaParametrosVO.getNrTempoMedio());
		}

		// Israel 14.02.04
		// Calendar tmp = Calendar.getInstance();
		// tempSegundoIni = tmp.getTime();
	}

	private void saveDadosQstSession(HttpServletRequest request) {
		if (OPERACAO_TELA_INICIAL.equals(getOperacao())) {
			// getDadosQst().setSeqRespostas( filaRespostasFac.getSeqRespostas() );
			// getDadosQst().setTempoDecorrido( filaRespostasFac.getTempoAtendimentoSegundos() );
			request.getSession().setAttribute("DADOS_QST_TELA_INICIAL", getDadosQst());
		}
	}

	private void removeDadosQstSession(HttpServletRequest request) {
		if (OPERACAO_TELA_INICIAL.equals(getOperacao())) {
			request.getSession().removeAttribute("DADOS_QST_TELA_INICIAL");
		}
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("onReturnTelaInicial".equals(mapping.getParameter())) {
			return onReturnTelaInicial(mapping, form, request, response);
		} else if ("factoryRespostaForward".equals(mapping.getParameter())) {
			return factoryRespostaForward(mapping, form, request, response);
		} else if ("respostaCheck".equals(mapping.getParameter())) {
			return respostaCheck(mapping, form, request, response);
		} else if ("respostaRadio".equals(mapping.getParameter())) {
			return respostaRadio(mapping, form, request, response);
		} else if ("respostaTexto".equals(mapping.getParameter())) {
			return respostaTexto(mapping, form, request, response);
		} else if ("finalizarQuestionario".equals(mapping.getParameter())) {
			return finalizarQuestionario(mapping, form, request, response);
		} else if ("showAndamento".equals(mapping.getParameter())) {
			return showAndamento(mapping, form, request, response);
		} else if ("voltarQuestionario".equals(mapping.getParameter())) {
			return voltarQuestionario(mapping, form, request, response);
		} else if ("salvaAgendaAniversario".equals(mapping.getParameter())) {
			return salvaAgendaAniversario(mapping, form, request, response);
		} else if ("dataAniversario".equals(mapping.getParameter())) {
			return dataAniversario(mapping, form, request, response);
		} else if ("voltarNavegacaoScript".equals(mapping.getParameter())) {
			return voltarNavegacaoScript(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="1" path="questionarioFidelAtendimento.jsp"
	 * @jpf:forward name="2" path="questionarioCmpAtendimento.jsp"
	 * @jpf:forward name="3" path="questionarioCmpSimulacao.jsp"
	 * @jpf:forward name="4" path="questionarioCmpAtendimento.jsp"
	 * @jpf:forward name="finalizar" path="finalizarQuestionario.do"
	 * @jpf:forward name="questionarioVazio1" path="questionarioVazioFidelAtendimento.jsp"
	 * @jpf:forward name="questionarioVazio2" path="questionarioVazioCmpAtendimento.jsp"
	 * @jpf:forward name="questionarioVazio3" path="questionarioVazioCmpSimulacao.jsp"
	 * @jpf:forward name="questionarioVazio4" path="questionarioVazioCmpAtendimento.jsp"
	 * @jpf:forward name="errorFidel" path="done.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("QuestionarioController:begin()");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			setDadosCampanha(request);

			// filaRespostasFac.startAtendimento();
			getDadosQst().startAtendimento();

			if (OPERACAO_SIMULACAO.equals(getOperacao())) {
				// Caso seja simulação, set igual a 1, para não ficar passando por aqui,
				// todo tempo
				getQuestionario().setIdAtendimentoCampanha(ConstantesCRM.SONE);
			} else {
				// Seta IdAtendimento na sessão pq pode-se mudar de controler
				request.getSession().setAttribute("IdAtendimento", questionarioFac.startQuestionario(user, getDadosQst().getDados()));

				// usado somente para quem não muda de sessão
				// getQuestionario().setIdAtendimentoCampanha(questionarioFac.startQuestionario(user,
				// getDadosQst().getDados()));

				// Seto na sessão, para utilizar no UPDSTMTCAMP, em atendimento campanha
				/**
				 * request.getSession().setAttribute(ConstantesCRM.CT_QU_IDENTIFICADOR_ATENDIMENTO,
				 * getQuestionario().getIdAtendimentoCampanha());
				 **/
				request.getSession().setAttribute(ConstantesCRM.CT_QU_IDENTIFICADOR_ATENDIMENTO, ((String) request.getSession().getAttribute("IdAtendimento")).trim());
			}

			getDadosQst().setScript(questionarioFac.getPergunta(user, ConstantesCRM.SZERO, getDadosQst().getDados(1)));
			/*
			 * QUESTIONARIO VAZIO Caso não exista perguntas cadastradas remeto a página de questionario vazio
			 */
			if (getDadosQst().getScript() == null || getDadosQst().getScript().getPerguntaQuestionarioVOArray().length == 0) {
				// request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				// return mapping.findForward("questionarioVazio" + getOperacao());
				// Madrid - 14/02/2005
				if (OPERACAO_DEFAULT.equals(getOperacao())) {
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("errorFidel");
				} else {
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(new StringBuffer("questionarioVazio").append(getOperacao()).toString());
				}
			}

			getDadosQst().setIndice(0);
			getQuestionario().setPerguntaVO(getDadosQst().getScript().getPerguntaQuestionarioVOArray(getDadosQst().getIndice()));

			/*
			 * Atributo usado para consistir se o botão voltar será usado na primeira pergunta ou não.
			 */
			request.setAttribute("isPrimeiraPergunta", ConstantesCRM.SONE);
			saveDadosQstSession(request);

		} catch (Exception e) {
			if (OPERACAO_DEFAULT.equals(getOperacao())) {
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("errorFidel");

			} else {
				FormError formError = globalApp.buildFormError(e, request);
				formError.setTarget(ConstantesCRM.FRAMEAPP);
				request.setAttribute(ConstantesCRM.SFORMERROR, formError);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SERROR);
			}
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(getOperacao());
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="2" path="questionarioCmpAtendimento.jsp"
	 * @jpf:forward name="4" path="questionarioCmpAtendimento.jsp"
	 * @jpf:forward name="finalizar" path="finalizarQuestionario.do"
	 * @jpf:forward name="questionarioVazio2" path="questionarioVazioCmpAtendimento.jsp"
	 * @jpf:forward name="questionarioVazio4" path="questionarioVazioCmpAtendimento.jsp"
	 * @jpf:forward name="errorFidel" path="done.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward onReturnTelaInicial(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// String user = ConstantesCRM.getCurrentUser(request.getSession());
		DadosQuestionario _dadosQst = (DadosQuestionario) request.getSession().getAttribute("DADOS_QST_TELA_INICIAL");
		if (_dadosQst == null) {
			FormError formError = globalApp.buildFormError(new Exception("Dados do questionario vazio!!!"), "/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		setDadosQst(_dadosQst);
		getDadosQst().startAtendimento();

		// VERIFICA SE É A PRIMEIRA PERGUNTA
		try {
			getQuestionario().setIdAtendimentoCampanha((String) request.getSession().getAttribute(ConstantesCRM.CT_QU_IDENTIFICADOR_ATENDIMENTO));

			/*
			 * QUESTIONARIO VAZIO Caso não exista perguntas cadastradas remeto a página de questionario vazio
			 */
			if (getDadosQst().getScript() == null || getDadosQst().getScript().getPerguntaQuestionarioVOArray().length == 0) {
				// request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				// return mapping.findForward("questionarioVazio" + getOperacao());
				// Madrid - 14/02/2005
				if (OPERACAO_DEFAULT.equals(getOperacao())) {
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("errorFidel");
				} else {
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(new StringBuffer("questionarioVazio").append(getOperacao()).toString());
				}
			}
			getQuestionario().setPerguntaVO(getDadosQst().getScript().getPerguntaQuestionarioVOArray(getDadosQst().getIndice()));

		} catch (Exception e) {
			if (OPERACAO_DEFAULT.equals(getOperacao())) {
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("errorFidel");
				// } else if (OPERACAO_TELA_INICIAL.equals(getOperacao())) {
				// FormError formError = globalApp.buildFormError(e,
				// "/FrontOfficeWeb/cliente/TelaInicial/begin.do?nrLinha=" + parametros);
				// FormError formError = globalApp.buildFormError(e, request);
				// formError.setTarget(ConstantesCRM.FRAMEAPP);
				// request.setAttribute(ConstantesCRM.SFORMERROR,formError);
				// request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				// return mapping.findForward(ConstantesCRM.SERROR);
			} else {
				FormError formError = globalApp.buildFormError(e, request);
				formError.setTarget(ConstantesCRM.FRAMEAPP);
				request.setAttribute(ConstantesCRM.SFORMERROR, formError);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SERROR);
			}
		}

		/*
		 * Atributo usado para consistir se o botão voltar será usado na primeira pergunta ou não.
		 */
		request.setAttribute("isPrimeiraPergunta", ConstantesCRM.SONE);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(getOperacao());
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="done" return-action="QuestionarioDone"
	 */
	public ActionForward done(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		// String user = ConstantesCRM.getCurrentUser(request.getSession());
		if (OPERACAO_DEFAULT.equals(getOperacao())) {// Valido para Fidelização-Atendimento

			request.setAttribute("iniciaQuestionarioForm", iniciaQuestionarioForm);
			// Priscilla Paula 23/02/2005
			request.setAttribute("retencao_DadosClienteForm", retencaoDadosClienteForm);
			request.setAttribute("retencao_ActionRetencaoForm", retencaoActionRetencaForm);
			// fim Priscilla Paula
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("done");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="respostaTexto" path="respostaTexto.do"
	 * @jpf:forward name="respostaRadio" path="respostaRadio.do"
	 * @jpf:forward name="respostaCheck" path="respostaCheck.do"
	 */
	public ActionForward factoryRespostaForward(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		IniciaQuestionarioForm form = (IniciaQuestionarioForm) formParam;
		// String user = ConstantesCRM.getCurrentUser(request.getSession());
		String apre = getQuestionario().getPerguntaVO().getSgTipoApresentacaoPergunta();
		// Adiciono a pergunta resposta a pilha de respostas
		getDadosQst().getPilhaRespostas().push(Integer.toString(getDadosQst().getIndice()));
		if (CHECK_BOX.equals(apre)) {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("respostaCheck");
		} else {
			if (RADIO_BOTTON.equals(apre)) {
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("respostaRadio");
			} else {
				if (TEXTO.equals(apre)) {
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("respostaTexto");
				} else {
					throw new Exception(" Pergunta não qualifidacada!");
				}
			}
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="1" path="questionarioFidelAtendimento.jsp"
	 * @jpf:forward name="2" path="questionarioCmpAtendimento.jsp"
	 * @jpf:forward name="3" path="questionarioCmpSimulacao.jsp"
	 * @jpf:forward name="4" path="questionarioCmpAtendimento.jsp"
	 * @jpf:forward name="finalizar" path="finalizarQuestionario.do"
	 * @jpf:forward name="regreso" path="/fidelizacao/regresoVoltar.do"
	 */
	public ActionForward respostaCheck(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		IniciaQuestionarioForm form = (IniciaQuestionarioForm) formParam;

		// String user = ConstantesCRM.getCurrentUser(request.getSession());
		PerguntaRespondidaVO resposta = PerguntaRespondidaVO.Factory.newInstance();
		resposta.setPerguntaQuestionarioVO(getQuestionario().getPerguntaVO());

		resposta.setIndicesRespostaArray(form.getIndiceResposta());

		// Marca as respostas selecionadas
		for (int i = 0; i < getQuestionario().getPerguntaVO().getRespostasQuestionarioVOArray().length; i++) {
			for (int j = 0; j < form.getIndiceResposta().length; j++) {
				if (getQuestionario().getPerguntaVO().getRespostasQuestionarioVOArray(i).getIdResposta().equalsIgnoreCase(String.valueOf(form.getIndiceResposta()[j]))) {
					resposta.setIndicesRespostaArray(j, String.valueOf(i));
				}
			}
		}

		// filaRespostasFac.addResposta(resposta);
		getDadosQst().addResposta(resposta);
		getDadosQst().incrementaIndice();
		saveDadosQstSession(request);

		// Verifica se a PERGUNTA é de incerramento imediato
		if (getQuestionario().getPerguntaVO().getInEncerramento().equals(ConstantesCRM.SONE)) {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("finalizar");
		}

		// VERIFICA SE É O FIM DO QUESTIONARIO
		if (getDadosQst().getIndice() >= getDadosQst().getScript().getPerguntaQuestionarioVOArray().length) {
			if (OPERACAO_DEFAULT.equals(getOperacao())) // Fidelização, alteração Marta
			{
				form.setActionRetencaoForm(iniciaQuestionarioForm.getActionRetencaoForm());
				form.setQuestionarioForm(getQuestionario());
				iniciaQuestionarioForm.setTextoResposta(form.getTextoResposta());
				iniciaQuestionarioForm.setQuestionarioForm(form.getQuestionarioForm());
			}
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("finalizar");
		}

		getQuestionario().setPerguntaVO(getDadosQst().getScript().getPerguntaQuestionarioVOArray(getDadosQst().getIndice()));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(getOperacao());
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="1" path="questionarioFidelAtendimento.jsp"
	 * @jpf:forward name="2" path="questionarioCmpAtendimento.jsp"
	 * @jpf:forward name="3" path="questionarioCmpSimulacao.jsp"
	 * @jpf:forward name="4" path="questionarioCmpAtendimento.jsp"
	 * @jpf:forward name="finalizar" path="finalizarQuestionario.do"
	 * @jpf:forward name="regreso" path="/fidelizacao/regresoVoltar.do"
	 */
	public ActionForward respostaRadio(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		IniciaQuestionarioForm form = (IniciaQuestionarioForm) formParam;
		// String user = ConstantesCRM.getCurrentUser(request.getSession());
		PerguntaRespondidaVO resposta = PerguntaRespondidaVO.Factory.newInstance();
		resposta.setPerguntaQuestionarioVO(getQuestionario().getPerguntaVO());

		for (int i = 0; i < getQuestionario().getPerguntaVO().getRespostasQuestionarioVOArray().length; i++) {
			if (getQuestionario().getPerguntaVO().getRespostasQuestionarioVOArray(i).getIdResposta().equalsIgnoreCase(String.valueOf(form.getIndiceResposta()[0]))) {
				resposta.setIndiceResposta(String.valueOf(i));
			}
		}

		// filaRespostasFac.addResposta(resposta);
		getDadosQst().addResposta(resposta);
		saveDadosQstSession(request);

		/*
		 * Consisto se a pergunta ou a resposta é de encerramento imediato, caso seja finalizo o questionario
		 */
		if (getQuestionario().getPerguntaVO().getInEncerramento().equals(ConstantesCRM.SONE)) {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("finalizar");
		} else if (getQuestionario().getPerguntaVO().getRespostasQuestionarioVOArray(Integer.parseInt(resposta.getIndiceResposta())).getInEncerramento().equals(ConstantesCRM.SONE)) {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("finalizar");
		}

		// VERIFICA SE A RESPOSTA ESCOLHIDA ALTERA O FLUXO DO QUESTIONARIO

		if ((resposta.getPerguntaQuestionarioVO().getRespostasQuestionarioVOArray().length > 0 && !resposta.getPerguntaQuestionarioVO().getRespostasQuestionarioVOArray(Integer.parseInt(resposta.getIndiceResposta())).getIdProximaPergunta().equals(ConstantesCRM.SZERO))) {

			int i;

			for (i = 0; i < getDadosQst().getScript().getPerguntaQuestionarioVOArray().length; i++) {

				if (resposta.getPerguntaQuestionarioVO().getRespostasQuestionarioVOArray(Integer.parseInt(resposta.getIndiceResposta())).getIdProximaPergunta().equals(getDadosQst().getScript().getPerguntaQuestionarioVOArray(i).getIdPergunta().trim())) {
					break;
				}
			}
			getDadosQst().setIndice(i);

		} else {
			getDadosQst().incrementaIndice();
		}

		// VERIFICA SE É O FIM DO QUESTIONARIO
		if (getDadosQst().getIndice() >= getDadosQst().getScript().getPerguntaQuestionarioVOArray().length) {
			if (OPERACAO_DEFAULT.equals(getOperacao())) {// Fidelização, alteração Marta
				form.setActionRetencaoForm(iniciaQuestionarioForm.getActionRetencaoForm());
				form.setQuestionarioForm(getQuestionario());
				iniciaQuestionarioForm.setTextoResposta(form.getTextoResposta());
				iniciaQuestionarioForm.setQuestionarioForm(form.getQuestionarioForm());
			}
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("finalizar");
		}

		getQuestionario().setPerguntaVO(getDadosQst().getScript().getPerguntaQuestionarioVOArray(getDadosQst().getIndice()));
		saveDadosQstSession(request);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(getOperacao());

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="1" path="questionarioFidelAtendimento.jsp"
	 * @jpf:forward name="2" path="questionarioCmpAtendimento.jsp"
	 * @jpf:forward name="3" path="questionarioCmpSimulacao.jsp"
	 * @jpf:forward name="4" path="questionarioCmpAtendimento.jsp"
	 * @jpf:forward name="finalizar" path="finalizarQuestionario.do"
	 * @jpf:forward name="regreso" path="/fidelizacao/regresoVoltar.do"
	 */
	public ActionForward respostaTexto(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		IniciaQuestionarioForm form = (IniciaQuestionarioForm) formParam;
		// String user = ConstantesCRM.getCurrentUser(request.getSession());
		PerguntaRespondidaVO resposta = PerguntaRespondidaVO.Factory.newInstance();
		resposta.setPerguntaQuestionarioVO(getQuestionario().getPerguntaVO());

		if (form.getTextoResposta() != null && !form.getTextoResposta().equals(ConstantesCRM.SVAZIO)) {
			resposta.setTextoResposta(form.getTextoResposta());
		}

		// filaRespostasFac.addResposta(resposta);
		getDadosQst().addResposta(resposta);
		getDadosQst().incrementaIndice();
		saveDadosQstSession(request);
		/*
		 * Consisto se a pergunta ou a resposta é de encerramento imediato, caso seja finalizo o questionario
		 */
		if (getQuestionario().getPerguntaVO().getInEncerramento().equals(ConstantesCRM.SONE)) {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("finalizar");
		}

		// VERIFICA SE É O FIM DO QUESTIONARIO
		if (getDadosQst().getIndice() >= getDadosQst().getScript().getPerguntaQuestionarioVOArray().length) {
			if (OPERACAO_DEFAULT.equals(getOperacao())) {
				// Fidelização, alteração Marta
				form.setActionRetencaoForm(iniciaQuestionarioForm.getActionRetencaoForm());
				form.setQuestionarioForm(getQuestionario());
				iniciaQuestionarioForm.setTextoResposta(form.getTextoResposta());
				iniciaQuestionarioForm.setQuestionarioForm(form.getQuestionarioForm());
			}
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("finalizar");
		}

		getQuestionario().setPerguntaVO(getDadosQst().getScript().getPerguntaQuestionarioVOArray(getDadosQst().getIndice()));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(getOperacao());

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="done.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward finalizarQuestionario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		// int tempoAtendimento = filaRespostasFac.getTempoAtendimentoSegundos();
		// int tempoAtendimento = filaRespostasFac.getTempoAtendimento();

		// PerguntaRespondidaVO[] pergRespArray = filaRespostasFac.getSeqRespostas();
		// PerguntaRespondidaVO[] pergRespArray;

		// if( OPERACAO_TELA_INICIAL.equals( getOperacao()) ) {
		// pergRespArray = getDadosQst().getRespostas();
		// pergRespArray = filaRespostasFac.getSeqRespostas();
		// } else {
		// pergRespArray = filaRespostasFac.getSeqRespostas();
		// }
		/*
		 * operacao 1 - Fidelizacao - Atendimento 2 - Campanha - Atendimento 3 - Campanha - Configuracao - Simulacao
		 */
		try {
			if (OPERACAO_SIMULACAO.equals(getOperacao())) {
				// questionarioFac.stopQuestionario(user, getDadosQst().getDados(1),
				// filaRespostasFac.getTempoAtendimentoSegundos(), pergRespArray, getDadosQst().getDados(2),
				// getOperacao(), getSalvaAgendaAniversarioForm().getDataAniversario());
				questionarioFac.stopQuestionario(user, getDadosQst().getDados(1), getDadosQst().getTempoAtendimentoSegundos(), getDadosQst().getRespostas(), getDadosQst().getDados(2), getOperacao(), getSalvaAgendaAniversarioForm().getDataAniversario());
			} else {
				// questionarioFac.stopQuestionario(user,getQuestionario().getIdAtendimentoCampanha(),filaRespostasFac.getTempoAtendimentoSegundos(),
				// pergRespArray, getDadosQst().getDados(2), getOperacao(),
				// getSalvaAgendaAniversarioForm().getDataAniversario());
				questionarioFac.stopQuestionario(user, ((String) request.getSession().getAttribute("IdAtendimento")).trim(), getDadosQst().getTempoAtendimentoSegundos(), getDadosQst().getRespostas(), getDadosQst().getDados(2), getOperacao(), getSalvaAgendaAniversarioForm().getDataAniversario());
			}
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		} finally {
			removeDadosQstSession(request);
			request.getSession().removeAttribute("IdAtendimento");
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="1" path="viewScriptFidelAtendimento.jsp"
	 * @jpf:forward name="2" path="viewScriptCmpAtendimento.jsp"
	 * @jpf:forward name="3" path="viewScriptCmpSimulacao.jsp"
	 * @jpf:forward name="4" path="viewScriptCmpAtendimento.jsp"
	 */
	public ActionForward showAndamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// String user = ConstantesCRM.getCurrentUser(request.getSession());
		// String node = ConstantesCRM.SVAZIO;
		// Madrid - 14/02/2005: String -> StringBuffer
		StringBuffer sbNode = new StringBuffer();

		sbNode.append("if (document.getElementById) {var tree = new WebFXTree('Perguntas Respodidas');tree.setBehavior('classic');");

		// PerguntaRespondidaVO[] resposta;

		// if( OPERACAO_TELA_INICIAL.equals( getOperacao()) ) {
		// resposta = getDadosQst().getSeqRespostas();
		// } else {
		// resposta = filaRespostasFac.getSeqRespostas();
		// }

		for (int i = 0; i < getDadosQst().getPerguntasRespondidasSize(); i++) {
			/*
			 * CHECK BOX - 2 RADIO BOTTON - 4 TEXTO - 5
			 */

			// node += "var " + "Pergunta" + StringEscapeUtils.escapeJavaScript(String.valueOf(i)) +
			// " = new WebFXTreeItem('" +
			// StringEscapeUtils.escapeJavaScript(resposta[i].getPerguntaQuestionarioVO().getDsScriptPergunta()) +
			// "','','', '' ,'' );" ;
			sbNode.append("var ").append("Pergunta").append(StringEscapeUtils.escapeJavaScript(String.valueOf(i))).append(" = new WebFXTreeItem('").append(StringEscapeUtils.escapeJavaScript(getDadosQst().getRespostas(i).getPerguntaQuestionarioVO().getDsScriptPergunta())).append("','','', '' ,'' );");
			// if(resposta[i].getTextoResposta() != null && resposta[i].getTextoResposta().length()>0)

			// Israel 14.02
			// int icase = Integer.parseInt(
			// getDadosQst().getRespostas(i).getPerguntaQuestionarioVO().getIdTipoApresentacaoPergunta());
			String apre = getDadosQst().getRespostas(i).getPerguntaQuestionarioVO().getSgTipoApresentacaoPergunta();

			if (CHECK_BOX.equals(apre)) {
				for (int j = 0; j < getDadosQst().getRespostas(i).getIndicesRespostaArray().length; j++) {
					sbNode.append("var ").append("Resposta").append(StringEscapeUtils.escapeJavaScript(String.valueOf(i + j))).append(" = new WebFXTreeItem('").append(StringEscapeUtils.escapeJavaScript(getDadosQst().getRespostas(i).getPerguntaQuestionarioVO().getRespostasQuestionarioVOArray(Integer.parseInt(getDadosQst().getRespostas(i).getIndicesRespostaArray(j))).getDsScriptResposta())).append("','','', '' ,'' );");
					sbNode.append("Pergunta").append(StringEscapeUtils.escapeJavaScript(String.valueOf(i))).append(".add(").append("Resposta").append(StringEscapeUtils.escapeJavaScript(String.valueOf(i + j))).append(");");
				}
			} else {
				if (RADIO_BOTTON.equals(apre)) {
					sbNode.append("var Resposta").append(StringEscapeUtils.escapeJavaScript(String.valueOf(i))).append(" = new WebFXTreeItem('").append(StringEscapeUtils.escapeJavaScript(getDadosQst().getRespostas(i).getPerguntaQuestionarioVO().getRespostasQuestionarioVOArray(Integer.parseInt(getDadosQst().getRespostas(i).getIndiceResposta())).getDsScriptResposta())).append("','','', '' ,'' );");
					sbNode.append("Pergunta").append(StringEscapeUtils.escapeJavaScript(String.valueOf(i))).append(".add(").append("Resposta" + StringEscapeUtils.escapeJavaScript(String.valueOf(i))).append(");");
				} else {
					if (TEXTO.equals(apre)) {
						sbNode.append("var Resposta").append(StringEscapeUtils.escapeJavaScript(String.valueOf(i))).append(" = new WebFXTreeItem('").append(StringEscapeUtils.escapeJavaScript(getDadosQst().getRespostas(i).getTextoResposta())).append("','','', '' ,'' );");
						sbNode.append("Pergunta").append(StringEscapeUtils.escapeJavaScript(String.valueOf(i))).append(".add(Resposta").append(String.valueOf(i)).append(");");
					}
				}
			}

			/*
			 * if(resposta[i].getPerguntaQuestionarioVO().getIdTipoApresentacaoPergunta().equals(ConstantesCRM.SFIVE)){
			 * //node += "var " + "Resposta" + StringEscapeUtils.escapeJavaScript(String.valueOf(i)) +
			 * " = new WebFXTreeItem('" + StringEscapeUtils.escapeJavaScript(resposta[i].getTextoResposta()) +
			 * "','','', '' ,'' );" ; //node += "Pergunta"+ StringEscapeUtils.escapeJavaScript(String.valueOf(i))+
			 * ".add(" + "Resposta" + String.valueOf(i) +");";
			 * sbNode.append("var Resposta").append(StringEscapeUtils.escapeJavaScript
			 * (String.valueOf(i))).append(" = new WebFXTreeItem('"
			 * ).append(StringEscapeUtils.escapeJavaScript(resposta[i].getTextoResposta())).append(
			 * "','','', '' ,'' );") ;
			 * sbNode.append("Pergunta").append(StringEscapeUtils.escapeJavaScript(String.valueOf
			 * (i))).append(".add(Resposta" ).append( String.valueOf(i) ).append(");"); } else
			 * if(resposta[i].getPerguntaQuestionarioVO().getIdTipoApresentacaoPergunta().equals(ConstantesCRM.SFOUR)){
			 * //node += "var " + "Resposta" + StringEscapeUtils.escapeJavaScript(String.valueOf(i)) +
			 * " = new WebFXTreeItem('" +
			 * StringEscapeUtils.escapeJavaScript(resposta[i].getPerguntaQuestionarioVO().getRespostasQuestionarioVOArray
			 * (Integer.parseInt(resposta[i].getIndiceResposta())).getDsScriptResposta()) + "','','', '' ,'' );" ;
			 * //node += "Pergunta"+ StringEscapeUtils.escapeJavaScript(String.valueOf(i))+ ".add(" + "Resposta" +
			 * StringEscapeUtils.escapeJavaScript(String.valueOf(i)) +");"; sbNode.append("var Resposta" ).append(
			 * StringEscapeUtils.escapeJavaScript(String.valueOf(i)) ).append( " = new WebFXTreeItem('" ).append(
			 * StringEscapeUtils
			 * .escapeJavaScript(resposta[i].getPerguntaQuestionarioVO().getRespostasQuestionarioVOArray
			 * (Integer.parseInt(resposta[i].getIndiceResposta())).getDsScriptResposta()) ).append(
			 * "','','', '' ,'' );") ; sbNode.append("Pergunta").append(
			 * StringEscapeUtils.escapeJavaScript(String.valueOf(i))).append( ".add(" ).append( "Resposta" +
			 * StringEscapeUtils.escapeJavaScript(String.valueOf(i)) ).append(");"); } else
			 * if(resposta[i].getPerguntaQuestionarioVO().getIdTipoApresentacaoPergunta().equals(ConstantesCRM.STWO)){
			 * //int x = i; for(int j = 0; j < resposta[i].getIndicesRespostaArray().length; j++) { //node += "var " +
			 * "Resposta" + StringEscapeUtils.escapeJavaScript(String.valueOf(i + j)) + " = new WebFXTreeItem('" +
			 * StringEscapeUtils
			 * .escapeJavaScript(resposta[i].getPerguntaQuestionarioVO().getRespostasQuestionarioVOArray
			 * (Integer.parseInt(resposta[i].getIndicesRespostaArray(j))).getDsScriptResposta()) + "','','', '' ,'' );"
			 * ; //node += "Pergunta"+ StringEscapeUtils.escapeJavaScript(String.valueOf(i))+ ".add(" + "Resposta" +
			 * StringEscapeUtils.escapeJavaScript(String.valueOf(i + j)) +");"; sbNode.append("var " ).append(
			 * "Resposta" ).append( StringEscapeUtils.escapeJavaScript(String.valueOf(i + j)) ).append(
			 * " = new WebFXTreeItem('" ).append(
			 * StringEscapeUtils.escapeJavaScript(resposta[i].getPerguntaQuestionarioVO
			 * ().getRespostasQuestionarioVOArray
			 * (Integer.parseInt(resposta[i].getIndicesRespostaArray(j))).getDsScriptResposta()) ).append(
			 * "','','', '' ,'' );") ; sbNode.append("Pergunta").append(
			 * StringEscapeUtils.escapeJavaScript(String.valueOf(i))).append( ".add(" ).append( "Resposta" ).append(
			 * StringEscapeUtils.escapeJavaScript(String.valueOf(i + j)) ).append(");"); //x ++; } }
			 */
			// node += "tree.add(Pergunta" + StringEscapeUtils.escapeJavaScript(String.valueOf(i))+ ");";
			sbNode.append("tree.add(Pergunta").append(StringEscapeUtils.escapeJavaScript(String.valueOf(i))).append(");");
		}

		// script += node + "document.write(tree);}";
		// script = sbNode.append("document.write(tree);}").toString();
		sbNode.append("document.write(tree);}");
		request.setAttribute("scriptQuestionario", sbNode.toString());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(getOperacao());
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="1" path="questionarioFidelAtendimento.jsp"
	 * @jpf:forward name="2" path="questionarioCmpAtendimento.jsp"
	 * @jpf:forward name="3" path="questionarioCmpSimulacao.jsp"
	 * @jpf:forward name="4" path="questionarioCmpAtendimento.jsp"
	 * @jpf:forward name="regreso" path="/fidelizacao/getLinhas.do"
	 */
	public ActionForward voltarQuestionario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		// String user = ConstantesCRM.getCurrentUser(request.getSession());
		// if(filaRespostasFac.getSize() > 0){
		if (getDadosQst().getPerguntasRespondidasSize() > 0) {

			getDadosQst().setIndice(Integer.parseInt((String) getDadosQst().getPilhaRespostas().pop()));

			// Remove o último registro
			// filaRespostasFac.removeSeqRespostas(filaRespostasFac.getSize() - 1);
			getDadosQst().removeResposta(getDadosQst().getPerguntasRespondidasSize() - 1);

		} else {
			if (OPERACAO_DEFAULT.equals(getOperacao())) {// Fidelização, alteração Marta
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("regreso");
				// request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				// return mapping.findForward("regreso");
			} else {
				// Primeira pergunta
				getDadosQst().setIndice(0);
			}
		}

		getQuestionario().setPerguntaVO(getDadosQst().getScript().getPerguntaQuestionarioVOArray(getDadosQst().getIndice()));

		// if(filaRespostasFac.getSize() == 0){
		if (getDadosQst().getPerguntasRespondidasSize() == 0) {
			request.setAttribute("isPrimeiraPergunta", ConstantesCRM.SONE);
		}

		saveDadosQstSession(request);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(getOperacao());
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="dataAniversario.jsp"
	 */
	public ActionForward salvaAgendaAniversario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		SalvaAgendaAniversarioForm form = (SalvaAgendaAniversarioForm) formParam;
		// String user = ConstantesCRM.getCurrentUser(request.getSession());
		getSalvaAgendaAniversarioForm().setDataAniversario(form.getDataAniversario());
		ActionRedirect forward = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
		forward.addParameter("isSalvaAgendaAniversario", ConstantesCRM.SONE);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return forward;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="dataAniversario.jsp"
	 */
	public ActionForward dataAniversario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		// String user = ConstantesCRM.getCurrentUser(request.getSession());
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="1" path="questionarioFidelAtendimento.jsp"
	 * @jpf:forward name="2" path="questionarioCmpAtendimento.jsp"
	 * @jpf:forward name="3" path="questionarioCmpSimulacao.jsp"
	 * @jpf:forward name="4" path="questionarioCmpAtendimento.jsp"
	 */
	public ActionForward voltarNavegacaoScript(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		// String user = ConstantesCRM.getCurrentUser(request.getSession());
		if (getDadosQst().getPerguntasRespondidasSize() == 0) {
			request.setAttribute("isPrimeiraPergunta", ConstantesCRM.SONE);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(getOperacao());
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class QuestionarioForm extends ActionForm {

		private static final long serialVersionUID = 7098840565247904075L;
		private int indiceResposta;
		private String textoResposta;
		private String idAtendimentoCampanha;
		private PerguntaQuestionarioVO perguntaVO;

		public String getIdAtendimentoCampanha() {
			return this.idAtendimentoCampanha;
		}

		public void setIdAtendimentoCampanha(String idAtendimentoCampanha) {
			this.idAtendimentoCampanha = idAtendimentoCampanha.trim();
		}

		public String getInObrigatoria() {
			return this.perguntaVO.getInObrigatoria();
		}

		public void setPerguntaVO(PerguntaQuestionarioVO perguntaVO) {
			this.perguntaVO = perguntaVO;
		}

		public PerguntaQuestionarioVO getPerguntaVO() {
			return this.perguntaVO;
		}

		public String getIdPergunta() {
			return this.perguntaVO.getIdPergunta();
		}

		public void setIdPergunta(String idPergunta) {
			this.perguntaVO.setIdPergunta(idPergunta);
		}

		public String getDsPergunta() {
			return this.perguntaVO.getDsPergunta();
		}

		public void setDsPergunta(String dsPergunta) {
			this.perguntaVO.setDsPergunta(dsPergunta);
		}

		public String getDsScriptPergunta() {
			return this.perguntaVO.getDsScriptPergunta();
		}

		public void setDsScriptPergunta(String dsScriptPergunta) {
			this.perguntaVO.setDsScriptPergunta(dsScriptPergunta);
		}

		public String getSqApresentacao() {
			return this.getSqApresentacao();
		}

		public void setSqApresentacao(String sqApresentacao) {
			this.perguntaVO.setSqApresentacao(sqApresentacao);
		}

		public void setInEncerramanto(String inEncerramento) {
			this.perguntaVO.getInEncerramento();
		}

		public String getInEncerramanto() {
			return this.perguntaVO.getInEncerramento();
		}

		public void setInObrigatoria(String inObrigatoria) {
			this.perguntaVO.getInObrigatoria();
		}

		// public void setIdTipoApresentacaoPergunta(String idTipoApresentacaoPergunta){
		// this.perguntaVO.setIdTipoApresentacaoPergunta(idTipoApresentacaoPergunta);
		// }

		// public String getIdTipoApresentacaoPergunta(){
		// return this.perguntaVO.getIdTipoApresentacaoPergunta();
		// }

		// metodos geters e seters para manipulação de respostas

		public String getIdProximaPergunta(int indice) {
			return this.perguntaVO.getRespostasQuestionarioVOArray(indice).getIdProximaPergunta();
		}

		public void setRespostas(RespostasQuestionarioVO[] respostas) {
			this.perguntaVO.setRespostasQuestionarioVOArray(respostas);
		}

		public RespostasQuestionarioVO[] getRespostas() {

			return this.perguntaVO.getRespostasQuestionarioVOArray();
		}

		public void setTextoResposta(String textoResposta) {
			this.textoResposta = textoResposta;
		}

		public String getTextoResposta() {
			return this.textoResposta;
		}

		/*
		 * public void setTipopergunta(int tipopergunta) { this.tipopergunta = tipopergunta; }
		 */
		public String getSgTipoApresentacaoPergunta() {
			/*
			 * CHECK BOX - 2 RADIO BOTTON - 4 TEXTO - 5
			 */
			// return Integer.parseInt(this.getPerguntaVO().getIdTipoApresentacaoPergunta());
			return this.getPerguntaVO().getSgTipoApresentacaoPergunta().toUpperCase();
		}

		public void setIndiceResposta(int indiceResposta) {
			this.indiceResposta = indiceResposta;
		}

		public int getIndiceResposta() {
			return this.indiceResposta;
		}
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class IniciaQuestionarioForm extends ActionForm {

		private static final long serialVersionUID = 5374775770458012317L;
		private QuestionarioForm questionarioForm = new QuestionarioForm();
		private ActionRetencaoForm actionRetencaoForm = new ActionRetencaoForm();

		private String[] indiceResposta;
		private String dsResposta;
		private String textoResposta;

		public void setTextoResposta(String textoResposta) {
			this.textoResposta = textoResposta;
		}

		public String getTextoResposta() {
			return this.textoResposta;
		}

		public void setDsResposta(String dsResposta) {
			this.dsResposta = dsResposta;
		}

		public String getDsResposta() {
			return this.dsResposta;
		}

		public void setIndiceResposta(String[] indiceResposta) {
			this.indiceResposta = indiceResposta;
		}

		public String[] getIndiceResposta() {
			return this.indiceResposta;
		}

		public void setActionRetencaoForm(ActionRetencaoForm actionRetencaoForm) {
			this.actionRetencaoForm = actionRetencaoForm;
		}

		public ActionRetencaoForm getActionRetencaoForm() {
			if (this.actionRetencaoForm == null) {
				this.actionRetencaoForm = new ActionRetencaoForm();
			}
			return this.actionRetencaoForm;
		}

		public void setQuestionarioForm(QuestionarioForm questionarioForm) {
			this.questionarioForm = questionarioForm;
		}

		public QuestionarioForm getQuestionarioForm() {
			if (this.questionarioForm == null) {
				this.questionarioForm = new QuestionarioForm();
			}
			return this.questionarioForm;
		}
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class SalvaAgendaAniversarioForm extends ActionForm {

		private static final long serialVersionUID = -5289547962341720260L;
		private String dataAniversario = ConstantesCRM.SVAZIO;

		public void setDataAniversario(String dataAniversario) {
			this.dataAniversario = dataAniversario;
		}

		public String getDataAniversario() {
			return this.dataAniversario;
		}
	}
}
