package campanha.Manter.ManterResposta;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.campanha.vo.CampanhaExecScriptVODocument.CampanhaExecScriptVO;
import br.com.vivo.fo.campanha.vo.CampanhaPerguntaVODocument.CampanhaPerguntaVO;
import br.com.vivo.fo.campanha.vo.CampanhaRespostaVODocument.CampanhaRespostaVO;
import br.com.vivo.fo.campanha.vo.CampanhaViewRespostaVODocument.CampanhaViewRespostaVO;
import br.com.vivo.fo.campanha.vo.ListaCampanhaPerguntaVODocument.ListaCampanhaPerguntaVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

@SuppressWarnings({"unused"})
public class ManterRespostaController extends AbstractAction {

	private static final long serialVersionUID = 5757400131355915814L;

	@EJB
	private br.com.vivo.fo.ctrls.campanha.questionario.QuestionarioCampanhaFacade questFacade;

	protected global.Global globalApp = new global.Global();

	private CampanhaRespostaVO campanhaRespostaVO = null;

	private String user;
	private String idCampanha = ConstantesCRM.SZERO;
	private String idSubCampanha = ConstantesCRM.SZERO;
	private String idSubCampanhaFixa = ConstantesCRM.SZERO;
	private String idCanalCampanha = ConstantesCRM.SZERO;
	private String idPergunta = ConstantesCRM.SZERO;
	private String idResposta = ConstantesCRM.SZERO;
	private String dsPergunta = ConstantesCRM.SVAZIO;
	private String dsCanalCampanha = ConstantesCRM.SVAZIO;
	private ManterRespostaCampanhaActionForm manterRespostaCampanhaActionForm;
	private String sgTipoApresentacaoPergunta = ConstantesCRM.SVAZIO;
	private static final String MSG_ADMIN_RESPOSTA = "msgAdminResposta";

	private String getUser(HttpServletRequest request) {
		if (this.user == null) {
			this.user = ConstantesCRM.getCurrentUser(request.getSession());
		}
		return this.user;
	}

	public String getSgTipoApresentacaoPergunta() {
		return this.sgTipoApresentacaoPergunta;
	}

	private void setSgTipoApresentacaoPergunta(String sgTipoApresentacaoPergunta) {
		this.sgTipoApresentacaoPergunta = sgTipoApresentacaoPergunta;
	}

	public ManterRespostaCampanhaActionForm getManterRespostaCampanhaActionForm() {
		if (this.manterRespostaCampanhaActionForm == null) {
			this.manterRespostaCampanhaActionForm = new ManterRespostaCampanhaActionForm();
		}
		return this.manterRespostaCampanhaActionForm;
	}

	protected void loadPage(HttpServletRequest request) throws Exception {

		this.idCampanha = (String) request.getAttribute("idCampanha");
		this.idSubCampanha = (String) request.getAttribute("idSubCampanha");
		this.idCanalCampanha = (String) request.getAttribute("idCanalCampanha");
		dsCanalCampanha = (String) request.getAttribute("dsCanalCampanha");
		String _idPergunta = (String) request.getAttribute("idPergunta");
		String _idResposta = (String) request.getAttribute("idResposta");
		this.dsPergunta = (String) request.getAttribute("Descricao");
		this.setSgTipoApresentacaoPergunta(request.getParameter("sgTipoApresentacaoPergunta"));
		// this.idTipoApresentacao = (String)request.getParameter("idTipoApresentacao");

		if ("editar".equals(request.getParameter(ConstantesCRM.SACTION)) && (_idResposta == null || _idResposta.equals(ConstantesCRM.SVAZIO))) {
			throw new Exception("Codigo resposta invalido");
		} else {
			if (_idPergunta == null || _idPergunta.equals(ConstantesCRM.SVAZIO)) {
				throw new Exception("Codigo pergunta invalido");
			} else {
				// idCanalCampanha = s2;
				this.idPergunta = _idPergunta;
				this.idResposta = _idResposta;
			}
		}
		this.idSubCampanhaFixa = this.idCampanha;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("manterRespostaAlterar".equals(mapping.getParameter())) {
			return manterRespostaAlterar(mapping, form, request, response);
		} else if ("incluirResposta".equals(mapping.getParameter())) {
			return incluirResposta(mapping, form, request, response);
		} else if ("alterarResposta".equals(mapping.getParameter())) {
			return alterarResposta(mapping, form, request, response);
		} else if ("ProcessaRespostaCampanhaAction".equals(mapping.getParameter())) {
			return ProcessaRespostaCampanhaAction(mapping, form, request, response);
		} else if ("voltarAction".equals(mapping.getParameter())) {
			return voltarAction(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="AdminRespostaCampanha.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) { // Inclusão
		// de
		// resposta

		try {
			request.setCharacterEncoding(ConstantesCRM.SISO);
			loadPage(request);

			getManterRespostaCampanhaActionForm().setsDsPergunta(dsPergunta);
			getManterRespostaCampanhaActionForm().setiIdPergunta(idPergunta);
			getManterRespostaCampanhaActionForm().setIdCanalCampanha(idCanalCampanha);
			getManterRespostaCampanhaActionForm().setDsCanalCampanha(dsCanalCampanha);
			getManterRespostaCampanhaActionForm().setIdResposta(String.valueOf(idResposta));

			/*
			 * Somente para resposta cujo a pergunta seja do tipo RADIO possuem salto para outra pergunta
			 */
			if ("RAD".equals(this.getSgTipoApresentacaoPergunta())) {
				CampanhaExecScriptVO listaPergunta = questFacade.getListaPergunta(getUser(request), Integer.parseInt(this.idCanalCampanha), Integer.parseInt(this.idPergunta), 0);
				getManterRespostaCampanhaActionForm().setListaPergunta(listaPergunta.getCampanhaPerguntaVOArray());
			}
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/campanha/Manter/ManterArvoreConfig/fechaFrameSubCampanha.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="done" return-action="ManterRespostaDone"
	 */
	public ActionForward done(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("done");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="AdminRespostaCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward manterRespostaAlterar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			loadPage(request);

			request.setCharacterEncoding(ConstantesCRM.SISO);

			getManterRespostaCampanhaActionForm().setsDsPergunta(dsPergunta);
			getManterRespostaCampanhaActionForm().setiIdPergunta(idPergunta);
			getManterRespostaCampanhaActionForm().setIdCanalCampanha(idCanalCampanha);
			getManterRespostaCampanhaActionForm().setDsCanalCampanha(dsCanalCampanha);
			getManterRespostaCampanhaActionForm().setIdResposta(String.valueOf(idResposta));

			CampanhaViewRespostaVO respostaVO = questFacade.getRespostaCampanhaId(getUser(request), Integer.parseInt(this.idResposta), Integer.parseInt(this.idCanalCampanha));

			this.idPergunta = respostaVO.getIdPergunta();
			getManterRespostaCampanhaActionForm().setCampanhaViewRespostaVO(respostaVO);
			getManterRespostaCampanhaActionForm().formLoad();
			this.setSgTipoApresentacaoPergunta(respostaVO.getSgTipoApresentacaoPergunta());
			getManterRespostaCampanhaActionForm().setAcao(1);
			getManterRespostaCampanhaActionForm().setPerguntaSelecionada(respostaVO.getIdProximaPergunta());

			/*
			 * Somente para resposta cujo a pergunta seja do tipo RADIO possuem salto para outra pergunta
			 */
			if ("RAD".equals(this.getSgTipoApresentacaoPergunta())) {
				CampanhaExecScriptVO listaPergunta = questFacade.getListaPergunta(getUser(request), Integer.parseInt(getManterRespostaCampanhaActionForm().getIdCanalCampanha()), Integer.parseInt(getManterRespostaCampanhaActionForm().getiIdPergunta()), 0);
				getManterRespostaCampanhaActionForm().setListaPergunta(listaPergunta.getCampanhaPerguntaVOArray());
			}

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/campanha/Manter/ManterArvoreConfig/fechaFrameSubCampanha.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="fechaFrame" path="../ManterArvoreConfig/fechaFrameSubCampanha.jsp"
	 * @jpf:forward name="voltarDuplicado" path="AdminRespostaCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 * 
	 */
	protected ActionForward incluirResposta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			ManterRespostaCampanhaActionForm form = (ManterRespostaCampanhaActionForm) formParam;

			/*********************** INCLUSÃO DE UMA RESPOSTA ********************************************************/
			RetornoVO tmp = questFacade.addRespostaCampanha(getUser(request), form.getCampanhaViewRespostaFormVO());
			/*
			 * O foco deste metódo abaixo, dever ser pouco usados no dia a dia, porém sã ótimos para aprendizado
			 */
			if (tmp != null && tmp.getValor() != null && tmp.getValor().equals("-1") && tmp.getDescricao() != null) {
				request.setAttribute(MSG_ADMIN_RESPOSTA, tmp.getDescricao());
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("voltarDuplicado");
			}

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/campanha/Manter/ManterArvoreConfig/fechaFrameSubCampanha.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute("tipo", ConstantesCRM.STHREE);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("fechaFrame");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="fechaFrame" path="../ManterArvoreConfig/fechaFrameSubCampanha.jsp"
	 * @jpf:forward name="voltarDuplicado" path="AdminRespostaCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 * 
	 */
	protected ActionForward alterarResposta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			ManterRespostaCampanhaActionForm form = (ManterRespostaCampanhaActionForm) formParam;

			CampanhaViewRespostaVO cvr = form.getCampanhaViewRespostaFormVO();

			if (request.getParameter("encerra").equalsIgnoreCase("true")) {
				cvr.setInEncerramento(ConstantesCRM.SONE);
			} else {
				cvr.setInEncerramento(ConstantesCRM.SZERO);
			}

			if (request.getParameter("disponivel").equalsIgnoreCase("true")) {
				cvr.setInStatus(ConstantesCRM.SONE);
			} else {
				cvr.setInStatus(ConstantesCRM.SZERO);
			}

			RetornoVO tmp = questFacade.setRespostaCampanha(getUser(request), cvr);

			if (tmp != null && tmp.getValor() != null && tmp.getValor().equals("-1") && tmp.getDescricao() != null) {
				request.setAttribute(MSG_ADMIN_RESPOSTA, tmp.getDescricao());
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("voltarDuplicado");
			}

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/campanha/Manter/ManterArvoreConfig/fechaFrameSubCampanha.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute("tipo", ConstantesCRM.STHREE);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("fechaFrame");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="AdminRespostaCampanha.jsp"
	 * @jpf:forward name="fechaFrame" path="../ManterArvoreConfig/fechaFrameSubCampanha.jsp"
	 * @jpf:forward name="voltarDuplicado" path="AdminRespostaCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 * 
	 */
	protected ActionForward ProcessaRespostaCampanhaAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			ManterRespostaCampanhaActionForm form = (ManterRespostaCampanhaActionForm) formParam;

			if ("editar".equals(request.getParameter(ConstantesCRM.SACTION))) {
				/*********************** EDIÇÃO DE UMA RESPOSTA ********************************************************/

				RetornoVO tmp = questFacade.setRespostaCampanha(getUser(request), form.getCampanhaViewRespostaFormVO());

				if (tmp != null && tmp.getValor() != null && tmp.getValor().equals("-1") && tmp.getDescricao() != null) {
					// this.mensagemRetorno = tmp.getDescricao();
					request.setAttribute(MSG_ADMIN_RESPOSTA, tmp.getDescricao());
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("voltarDuplicado");
				}

				CampanhaViewRespostaVO respostaVO = questFacade.getRespostaCampanhaId(getUser(request), Integer.parseInt(form.getIdResposta()), Integer.parseInt(form.getIdCanalCampanha()));

				form.setCampanhaViewRespostaVO(respostaVO);
				form.formLoad();

				form.setListaPergunta(manterRespostaCampanhaActionForm.getListaPergunta());
				// ************* Alteração *************************************************************/
				form.setAcao(1);

			} else if ("incluir".equals(request.getParameter(ConstantesCRM.SACTION))) {

				/*********************** INCLUSÃO DE UMA RESPOSTA ********************************************************/
				RetornoVO tmp = questFacade.addRespostaCampanha(getUser(request), form.getCampanhaViewRespostaFormVO());
				if (tmp != null && "-1".equals(tmp.getValor()) && tmp.getDescricao() != null) {
					request.setAttribute(MSG_ADMIN_RESPOSTA, tmp.getDescricao());
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("voltarDuplicado");
				}
			}
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/campanha/Manter/ManterArvoreConfig/fechaFrameSubCampanha.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("fechaFrame");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="../ManterArvoreConfig/fechaFrameSubCampanha.jsp"
	 */
	protected ActionForward voltarAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class ManterRespostaCampanhaActionForm extends ActionForm {

		private static final long serialVersionUID = 1899282170236044178L;

		private int acao;
		private Integer iDisponibilidade = new Integer(1);
		private Integer iSeqApresentacao = new Integer(-1);
		private String iIdPergunta;
		private String iEncerramento = ConstantesCRM.SZERO;
		private String perguntaSelecionada = ConstantesCRM.SVAZIO;
		private String idCanalCampanha;
		private String idProximaPergunta;
		private String idResposta;
		private String sDsPergunta;
		private String sDsScriptResposta;
		private String sDsResposta;
		private String dsCanalCampanha;
		private CampanhaViewRespostaVO campanhaViewRespostaFormVO = CampanhaViewRespostaVO.Factory.newInstance();
		private ListaCampanhaPerguntaVO listaPerguntaFormVO = ListaCampanhaPerguntaVO.Factory.newInstance();
		private CampanhaPerguntaVO[] listaPergunta;

		public CampanhaPerguntaVO[] getListaPergunta() {
			if (this.listaPergunta == null) {
				this.listaPergunta = new CampanhaPerguntaVO[0];
			}
			return this.listaPergunta;
		}

		public void setListaPergunta(CampanhaPerguntaVO[] listaPergunta) {
			this.listaPergunta = listaPergunta;
		}

		public String getPerguntaSelecionada() {
			return this.perguntaSelecionada;
		}

		public void setPerguntaSelecionada(String perguntaSelecionada) {
			this.perguntaSelecionada = perguntaSelecionada;
		}

		public String getDsCanalCampanha() {
			return this.dsCanalCampanha;
		}

		public void setDsCanalCampanha(String s) {
			dsCanalCampanha = s;
		}

		public void setlistaPerguntaFormVO(ListaCampanhaPerguntaVO listaPerguntaVOBD) {
			this.listaPerguntaFormVO = listaPerguntaVOBD;
		}

		public void setCampanhaViewRespostaVO(CampanhaViewRespostaVO campanhaRespostaVOBD) {
			this.campanhaViewRespostaFormVO = campanhaRespostaVOBD;
		}

		public CampanhaViewRespostaVO getCampanhaViewRespostaFormVO() {
			if (campanhaViewRespostaFormVO != null) {
				this.campanhaViewRespostaFormVO.setIdResposta(this.idResposta);
				this.campanhaViewRespostaFormVO.setDsResposta(this.sDsResposta);
				this.campanhaViewRespostaFormVO.setDsScriptResposta(this.sDsScriptResposta);
				this.campanhaViewRespostaFormVO.setIdPergunta(this.iIdPergunta);
				this.campanhaViewRespostaFormVO.setInEncerramento(this.iEncerramento);
				this.campanhaViewRespostaFormVO.setSqApresentacao(this.iSeqApresentacao.toString());
				this.campanhaViewRespostaFormVO.setInStatus(this.iDisponibilidade.toString());
				String idProximaPergunta = ConstantesCRM.SZERO;
				if (!perguntaSelecionada.equals(ConstantesCRM.SVAZIO)) {
					idProximaPergunta = this.perguntaSelecionada;
				}

				this.campanhaViewRespostaFormVO.setIdProximaPergunta(idProximaPergunta);
			}

			return this.campanhaViewRespostaFormVO;
		}

		public void setIDisponibilidade(Integer iStatus) {
			this.iDisponibilidade = iStatus;
		}

		public Integer getIDisponibilidade() {
			return this.iDisponibilidade;
		}

		public void setIdCanalCampanha(String i) {
			this.idCanalCampanha = i;
		}

		public String getIdCanalCampanha() {
			return this.idCanalCampanha;
		}

		public void setiIdPergunta(String iIdPergunta) {
			if (this.iIdPergunta == null) {
				this.iIdPergunta = ConstantesCRM.SZERO;
			}
			this.iIdPergunta = iIdPergunta;
		}

		public String getiIdPergunta() {
			return this.iIdPergunta;
		}

		public void setsDsResposta(String sDsResposta) {
			this.sDsResposta = sDsResposta;
		}

		public String getsDsResposta() {
			return this.sDsResposta;
		}

		public void setsDsScriptResposta(String sDsScriptResposta) {
			this.sDsScriptResposta = sDsScriptResposta;
		}

		public String getsDsScriptResposta() {
			return this.sDsScriptResposta;
		}

		public void setiEncerramento(String iEncerramento) {
			this.iEncerramento = iEncerramento;
		}

		public String getiEncerramento() {
			return this.iEncerramento;
		}

		public void setsDsPergunta(String sDsPergunta) {
			this.sDsPergunta = sDsPergunta;
		}

		public String getsDsPergunta() {
			return this.sDsPergunta;
		}

		public void setIdProximaPergunta(String i) {
			this.idProximaPergunta = i;
		}

		public String getIdProximaPergunta() {
			return this.idProximaPergunta;
		}

		public void clear() {
			this.iDisponibilidade = new Integer(1);
			this.iEncerramento = ConstantesCRM.SZERO;
			this.iSeqApresentacao = new Integer(-1);
			this.iIdPergunta = "-1";
			this.sDsPergunta = ConstantesCRM.SVAZIO;
			this.sDsScriptResposta = ConstantesCRM.SVAZIO;
			this.sDsResposta = ConstantesCRM.SVAZIO;
			this.idCanalCampanha = ConstantesCRM.SZERO;
			this.dsCanalCampanha = ConstantesCRM.SVAZIO;
			this.acao = 0; // Inclusão
			this.idProximaPergunta = ConstantesCRM.SZERO;
			this.idResposta = ConstantesCRM.SZERO;
		}

		public void formLoad() {
			if (this.campanhaViewRespostaFormVO != null) {
				this.iDisponibilidade = new Integer(this.campanhaViewRespostaFormVO.getInStatus());
				this.iEncerramento = this.campanhaViewRespostaFormVO.getInEncerramento();
				this.iSeqApresentacao = new Integer(this.campanhaViewRespostaFormVO.getSqApresentacao());
				this.idResposta = campanhaViewRespostaFormVO.getIdResposta();
				this.sDsScriptResposta = this.campanhaViewRespostaFormVO.getDsScriptResposta();
				this.sDsResposta = this.campanhaViewRespostaFormVO.getDsResposta();
				this.perguntaSelecionada = String.valueOf(campanhaViewRespostaFormVO.getIdProximaPergunta());
				this.iIdPergunta = campanhaViewRespostaFormVO.getIdPergunta();
				this.sDsPergunta = campanhaViewRespostaFormVO.getDsPergunta();
				this.idCanalCampanha = campanhaViewRespostaFormVO.getIdCanalCampanha();
				this.dsCanalCampanha = campanhaViewRespostaFormVO.getNmCanal();
			}
		}

		public void setAcao(int acao) {
			this.acao = acao;
		}

		public int getAcao() {
			return this.acao;
		}

		public void setIdResposta(String idResposta) {
			this.idResposta = idResposta;
		}

		public String getIdResposta() {
			return this.idResposta;
		}

	}
}
