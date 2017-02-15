package campanha.SimulacaoPerguntas;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.campanha.vo.ApoioVODocument.ApoioVO;
import br.com.vivo.fo.campanha.vo.CampanhaPerguntaVODocument.CampanhaPerguntaVO;
import br.com.vivo.fo.campanha.vo.CampanhaRespostaVODocument.CampanhaRespostaVO;
import br.com.vivo.fo.campanha.vo.GrupoCampanhaApoioVODocument.GrupoCampanhaApoioVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.fidelizacao.vo.ItemArvoreVODocument.ItemArvoreVO;

import com.indracompany.actions.AbstractAction;

@SuppressWarnings("unused")
public class SimulacaoPerguntasController extends AbstractAction {

	private static final long serialVersionUID = 5458872894814557211L;

	protected global.Global globalApp = new global.Global();

	@EJB
	private br.com.vivo.fo.ctrls.campanha.manter.ApoioFacade apoioFacade;

	private String user = ConstantesCRM.SVAZIO;

	private int idCampanha = 0;

	private String idSubCampanha = ConstantesCRM.SZERO;

	private InicioSimuladorActionForm inicioSimuladorActionForm = new InicioSimuladorActionForm();

	public InicioSimuladorActionForm getInicioSimuladorActionForm() {
		return inicioSimuladorActionForm;
	}
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("InicioSimuladorAction".equals(mapping.getParameter())) {
			return InicioSimuladorAction(mapping, form, request, response);
		} else if ("ObtemListaAction".equals(mapping.getParameter())) {
			return ObtemListaAction(mapping, form, request, response);
		} else if ("ExecSimulacaoAction".equals(mapping.getParameter())) {
			return ExecSimulacaoAction(mapping, form, request, response);
		} else if ("QuestionarioDone".equals(mapping.getParameter())) {
			return QuestionarioDone(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="InicioSimuladorAction.do"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		user = ConstantesCRM.getCurrentUser(request.getSession());
		PageLoad(request);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void PageLoad(HttpServletRequest request) throws Exception {
		String s = request.getParameter("idCampanha");
		String s1 = request.getParameter("idSubCampanha");

		try {
			idCampanha = Integer.parseInt(s);
			idSubCampanha = s1;
			// idSubCampanhaFixa = idCampanha;
		} catch (Exception e) {
			throw new Exception("Código campanha e sub campanha inválidos", e);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="InicioSimulacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward InicioSimuladorAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		InicioSimuladorActionForm form = (InicioSimuladorActionForm) formParam;

		/****************************** Carrega lista fixas que não dependem de parametros ************************************************/
		inicioSimuladorActionForm = form;
		try {
			/*********************** Lista de Canais *******************************************************************************************/
			GrupoCampanhaApoioVO grupoCanalCampanhaApoio = apoioFacade.getApoioCanalCampanha(user, idSubCampanha, 0);
			inicioSimuladorActionForm.setListaCanal(grupoCanalCampanhaApoio.getSubGrupoApoioVOArray(0).getApoioVOArray());

		} catch (Exception e) {
			// FormError formError = globalApp.buildFormError(e, request);
			// request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			// request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);

		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="IFrameObtemListas.jsp"
	 */
	protected ActionForward ObtemListaAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="begin" path="begin.do"
	 * @jpf:forward name="showQuestionario" path="/questionario/QuestionarioController.jpf"
	 */
	protected ActionForward ExecSimulacaoAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		InicioSimuladorActionForm form = (InicioSimuladorActionForm) formParam;

		if (form == null) {
			return mapping.findForward("begin");
		}

		// parametros exigidos para a execução do questionario.

		// --request.setAttribute("urlRetorno",
		// request.getContextPath()+"/campanha/SimulacaoPerguntas/SimulacaoPerguntasController.jpf");
		request.getSession().setAttribute("idCanalCampanha", form.getCanalSelecionado());
		// --request.setAttribute("idCliente","1");
		request.getSession().setAttribute("idSubCampanhaHistorico", idSubCampanha);
		// --request.setAttribute("idMotivoCampanha","1");
		// --request.setAttribute("idSubMotivoCampanha","0");
		// --request.setAttribute("idTipoMotivoCampanha","0");
		// --request.setAttribute("idTipoSubMotivoCampanha","0");
		// Parametro que indica ao questionario que esta chamada é para uma simulação
		request.setAttribute("operacao", ConstantesCRM.STHREE);

		request.getSession().setAttribute("idCampanha", Integer.toString(idCampanha));
		request.getSession().setAttribute("idSubCampanha", idSubCampanha);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("showQuestionario");
	}

	private String VerificaItemMenu(ItemArvoreVO itens, String tree) {

		String node = ConstantesCRM.SVAZIO;

		if (itens.getItemArvoreVOArray().length == 0) {
			return ConstantesCRM.SVAZIO;
		} else {
			for (int x = 0; x < itens.getItemArvoreVOArray().length; x++) {
				if (itens.getItemArvoreVOArray(x) != null) {
					node = node + "var " + tree + String.valueOf(x) + " = new WebFXTreeItem('" + itens.getItemArvoreVOArray(x).getDescricao() + "');";
				}
				node = node + VerificaItemMenu(itens.getItemArvoreVOArray(x), tree + String.valueOf(x));
				node = node + tree + ".add(" + tree + String.valueOf(x) + ");\n";
			}
			/*
			 * for( int x = 0; x < itens.getItemArvoreVOArray().length;x++ ) { node = node + VerificaItemMenu(
			 * itens.getItemArvoreVOArray(x),tree+ String.valueOf(x) ); node = node + tree + ".add(" + tree+
			 * String.valueOf(x) + ");\n"; }
			 */
		}
		return node;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="InicioSimuladorAction.do"
	 */
	protected ActionForward QuestionarioDone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String s = (String) request.getSession().getAttribute("idCampanha");
		String s1 = (String) request.getSession().getAttribute("idSubCampanha");
		// String s2 = (String)request.getAttribute(ConstantesCRM.CT_QU_IDENTIFICADOR_ATENDIMENTO);

		try {
			idCampanha = Integer.parseInt(s);
			idSubCampanha = s1;
			// idSubCampanhaFixa = idCampanha;
			// idAtendimentoCampanha = Integer.parseInt(s2);
		} catch (Exception e) {
			throw new Exception("Código campanha ou sub campanha inválidos", e);
		} finally {
			request.getSession().removeAttribute("idCampanha");
			request.getSession().removeAttribute("idSubCampanha");
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class InicioSimuladorActionForm extends ActionForm {

		private static final long serialVersionUID = 7111586182467091709L;

		private String sgCanal;
		private int idCanal;
		private String sgSubCampanha;
		private int idSubCampanha;
		private String sgCampanha;
		private int idCampanha;
		private int idCanalCampanha = 1;
		private ApoioVO[] listaCanal = new ApoioVO[0];
		private String canalSelecionado;
		private String subCampanhaSelecionada;
		private ApoioVO[] listaSubCampanha = new ApoioVO[0];
		private ApoioVO[] listaCampanha = new ApoioVO[0];
		private String campanhaSelecionada;
		private CampanhaPerguntaVO[] scriptCampanhaFormVO = new CampanhaPerguntaVO[0];
		private CampanhaPerguntaVO perguntaVO = CampanhaPerguntaVO.Factory.newInstance();
		private CampanhaRespostaVO[] respostaVO = new CampanhaRespostaVO[0];
		private int indexPergunta = 0;
		private int idApresentacao = -1;
		private String respostaSelecionada = ConstantesCRM.SVAZIO;
		private String textoResposta = ConstantesCRM.SVAZIO;

		public int getIdCanalCampanha() {
			return idCanalCampanha;
		}

		public void setIdCanalCampanha(int i) {
			idCanalCampanha = i;
		}

		public String getTextoResposta() {
			return textoResposta;
		}

		public void setTextoResposta(String s) {
			textoResposta = s;
		}

		public String getRespostaSelecionada() {
			return respostaSelecionada;
		}

		public void setRespostaSelecionada(String s) {
			respostaSelecionada = s;
		}

		public String getIdApresentacao() {
			if ((indexPergunta < 0) || (indexPergunta >= scriptCampanhaFormVO.length)) {
				indexPergunta = 0;
			}

			return scriptCampanhaFormVO[indexPergunta].getIdApresentacao();

		}

		public void setIdApresentacao(int i) {
			idApresentacao = i;
		}

		public int getIndexPergunta() {
			return indexPergunta;
		}

		public void setIndexPergunta(int i) {
			indexPergunta = i;
		}

		public void setPerguntaVO(CampanhaPerguntaVO pergunta) {
			perguntaVO = pergunta;
		}

		public CampanhaPerguntaVO getPerguntaVO() {
			if ((indexPergunta < 0) || (indexPergunta >= scriptCampanhaFormVO.length)) {
				indexPergunta = 0;
			}

			return scriptCampanhaFormVO[indexPergunta];

		}

		/*
		 * public ApresentacaoVO getApresentacaoVO() { if((indexPergunta < 0)||(indexPergunta >=
		 * scriptCampanhaFormVO.length)) indexPergunta = 0;
		 * 
		 * return scriptCampanhaFormVO[indexPergunta].getApresentacaoVO();
		 * 
		 * }
		 */

		public void setRespostaVO(CampanhaRespostaVO[] respostas) {
			respostaVO = respostas;
		}

		public CampanhaRespostaVO[] getRespostaVO() {
			if ((indexPergunta < 0) || (indexPergunta >= scriptCampanhaFormVO.length)) {
				indexPergunta = 0;
			}

			return scriptCampanhaFormVO[indexPergunta].getCampanhaRespostaVOArray();

		}

		public CampanhaPerguntaVO[] getScriptCampanhaFormVO() {
			return scriptCampanhaFormVO;
		}

		public void setScriptCampanhaFormVO(CampanhaPerguntaVO[] script) {
			scriptCampanhaFormVO = script;
		}

		public void setListaCampanha(ApoioVO[] listaCampanha) {
			this.listaCampanha = listaCampanha;
		}

		public ApoioVO[] getListaCampanha() {
			return this.listaCampanha;
		}

		public void setCampanhaSelecionada(String campanhaSelecionada) {
			this.campanhaSelecionada = campanhaSelecionada;
		}

		public String getCampanhaSelecionada() {
			return this.campanhaSelecionada;
		}

		public void setListaSubCampanha(ApoioVO[] listaSubCampanha) {
			this.listaSubCampanha = listaSubCampanha;
		}

		public ApoioVO[] getListaSubCampanha() {
			return this.listaSubCampanha;
		}

		public void setSubCampanhaSelecionada(String subCampanhaSelecionada) {
			this.subCampanhaSelecionada = subCampanhaSelecionada;
		}

		public String getSubCampanhaSelecionada() {
			return this.subCampanhaSelecionada;
		}

		public void setListaCanal(ApoioVO[] listaCanal) {
			this.listaCanal = listaCanal;
		}

		public ApoioVO[] getListaCanal() {
			return this.listaCanal;
		}

		public void setCanalSelecionado(String canalSelecionado) {
			this.canalSelecionado = canalSelecionado;
		}

		public String getCanalSelecionado() {
			return this.canalSelecionado;
		}

		public int getIdCampanha() {
			try {
				return Integer.parseInt(campanhaSelecionada);
			} catch (Exception e) {
				return 0;
			}
		}

		public void setSgCampanha(String sgCampanha) {
			this.sgCampanha = sgCampanha;
		}

		public String getSgCampanha() {
			return this.sgCampanha;
		}

		public int getIdSubCampanha() {
			try {
				return Integer.parseInt(subCampanhaSelecionada);
			} catch (Exception e) {
				return 0;
			}
		}

		public void setSgSubCampanha(String sgSubCampanha) {
			this.sgSubCampanha = sgSubCampanha;
		}

		public String getSgSubCampanha() {
			return this.sgSubCampanha;
		}

		public int getIdCanal() {
			try {
				return Integer.parseInt(canalSelecionado);
			} catch (Exception e) {
				return 0;
			}
		}

		public void setSgCanal(String sgCanal) {
			this.sgCanal = sgCanal;
		}

		public String getSgCanal() {
			return this.sgCanal;
		}
	}
}
