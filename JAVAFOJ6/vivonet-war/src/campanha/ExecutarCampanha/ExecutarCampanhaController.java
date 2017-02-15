package campanha.ExecutarCampanha;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.campanha.vo.ApoioVODocument.ApoioVO;
import br.com.vivo.fo.campanha.vo.ContatoAtendimentoCampanhaVODocument.ContatoAtendimentoCampanhaVO;
import br.com.vivo.fo.campanha.vo.GrupoCampanhaApoioVODocument.GrupoCampanhaApoioVO;
import br.com.vivo.fo.campanha.vo.ListaAtendimentoCampanhaVODocument.ListaAtendimentoCampanhaVO;
import br.com.vivo.fo.campanha.vo.ListaAtendimentoCampanhaVODocument.ListaAtendimentoCampanhaVO.CampanhasRelacionadas;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ExecutarCampanhaController extends AbstractAction {

	private static final long serialVersionUID = 8140314055173371468L;

	protected global.Global globalApp = new global.Global();

	@EJB
	private br.com.vivo.fo.ctrls.campanha.atendimento.ListaAtendimentoCampanhaFacade listaAtendimentoCampanhaFac;

	@EJB
	private br.com.vivo.fo.ctrls.campanha.configurar.ConfigurarCampanha configCampanhaFacade;

	private CampanhasRelacionadas[] campanhasRelacionadas;
	private String numeroLinha = ConstantesCRM.SVAZIO;
	private String propietarioLinha = ConstantesCRM.SVAZIO;
	private String idCampanha = ConstantesCRM.SZERO;
	private String idSubCampanhaHistorico = ConstantesCRM.SZERO;
	private String idListaConteudo = ConstantesCRM.SZERO;
	private ObtemCampanhaListaActionForm obtemCampanhaListaActionForm;
	private ViewScriptActionForm viewScriptActionForm = new ViewScriptActionForm();

	public ViewScriptActionForm getViewScriptActionForm() {
		return viewScriptActionForm;
	}

	public void setViewScriptActionForm(ViewScriptActionForm viewScriptActionForm) {
		this.viewScriptActionForm = viewScriptActionForm;
	}

	public CampanhasRelacionadas[] getCampanhasRelacionadas() {
		if (this.campanhasRelacionadas == null) {
			ListaAtendimentoCampanhaVO vo = ListaAtendimentoCampanhaVO.Factory.newInstance();
			vo.addNewCampanhasRelacionadas();
			this.campanhasRelacionadas = vo.getCampanhasRelacionadasArray();
		}
		return this.campanhasRelacionadas;
	}

	public void setCampanhasRelacionadas(CampanhasRelacionadas[] _campanhasRelacionadas) {
		this.campanhasRelacionadas = _campanhasRelacionadas;
	}

	public String getIdCampanha() {
		return this.idCampanha;
	}

	public void setIdCampanha(String _idCampanha) {
		this.idCampanha = _idCampanha;
	}

	public String getIdSubCampanhaHistorico() {
		return this.idSubCampanhaHistorico;
	}

	public void setIdSubCampanhaHistorico(String _idSubCampanhaHistorico) {
		this.idSubCampanhaHistorico = _idSubCampanhaHistorico;
	}

	public String getIdListaConteudo() {
		return this.idListaConteudo;
	}

	public void setIdListaConteudo(String _idListaConteudo) {
		this.idListaConteudo = _idListaConteudo;
	}

	public String getNumeroLinha() {
		return this.numeroLinha;
	}

	public void setNumeroLinha(String _numeroLinha) {
		this.numeroLinha = _numeroLinha;
	}

	public String getPropietarioLinha() {
		return this.propietarioLinha;
	}

	public void setPropietarioLinha(String propietarioLinha) {
		this.propietarioLinha = propietarioLinha;
	}

	public ObtemCampanhaListaActionForm getObtemCampanhaListaActionForm() {
		if (obtemCampanhaListaActionForm == null) {
			this.obtemCampanhaListaActionForm = new ObtemCampanhaListaActionForm();
		}
		return this.obtemCampanhaListaActionForm;
	}

	public void setObtemCampanhaListaActionForm(ObtemCampanhaListaActionForm _obtemCampanhaListaActionForm) {
		this.obtemCampanhaListaActionForm = _obtemCampanhaListaActionForm;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("TerminoCampanhaAction".equals(mapping.getParameter())) {
			return TerminoCampanhaAction(mapping, form, request, response);
		} else if ("InicioCampanhaAction".equals(mapping.getParameter())) {
			return InicioCampanhaAction(mapping, form, request, response);
		} else if ("AtendimentoCampanhaAction".equals(mapping.getParameter())) {
			return AtendimentoCampanhaAction(mapping, form, request, response);
		} else if ("ViewScriptAction".equals(mapping.getParameter())) {
			return ViewScriptAction(mapping, form, request, response);
		} else if ("LigarManualmente".equals(mapping.getParameter())) {
			return LigarManualmente(mapping, form, request, response);
		} else if ("QuestionarioDone".equals(mapping.getParameter())) {
			return QuestionarioDone(mapping, form, request, response);
		} else if ("obtemCampanhaListaAction".equals(mapping.getParameter())) {
			return obtemCampanhaListaAction(mapping, form, request, response);
		} else if ("telaInicial".equals(mapping.getParameter())) {
			return telaInicial(mapping, form, request, response);
		} else if ("obtemMotivos".equals(mapping.getParameter())) {
			return obtemMotivos(mapping, form, request, response);
		} else if ("obtemSubMotivos".equals(mapping.getParameter())) {
			return obtemSubMotivos(mapping, form, request, response);
		} else if ("getCampanhasRelacionadas".equals(mapping.getParameter())) {
			return getCampanhasRelacionadas(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="inicio" path="AtendimentoCampanha.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String user = ConstantesCRM.getCurrentUser(request.getSession());
			carregarLista(user);
			request.getSession().setAttribute(ConstantesCRM.CT_AT_OPERACAO_EXECUTAR_CAMPANHA, ConstantesCRM.SZERO);
		} catch (Exception e) {
			throw e;
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("inicio");
	}
	
	protected ActionForward getCampanhasRelacionadas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			String user = ConstantesCRM.getCurrentUser(request.getSession());
			carregarLista(user);
			request.getSession().setAttribute(ConstantesCRM.CT_AT_OPERACAO_EXECUTAR_CAMPANHA, ConstantesCRM.SZERO);
		} catch (Exception e) {
			throw e;
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}


	/**
	 * @jpf:action
	 * @jpf:forward name="termino" path="TerminoCampanha.jsp"
	 */
	protected ActionForward TerminoCampanhaAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		// String user = ConstantesCRM.getCurrentUser(request.getSession());
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("termino");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="perguntas" path="AtendimentoCampanha.jsp"
	 */
	protected ActionForward InicioCampanhaAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		// String user = ConstantesCRM.getCurrentUser(request.getSession());
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("perguntas");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="atendimento" path="AtendimentoCampanha.jsp"
	 */
	protected ActionForward AtendimentoCampanhaAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		// String user = ConstantesCRM.getCurrentUser(request.getSession());
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("atendimento");
	}

	public void carregarLista(String user) throws Exception {

		try {

			// this.numeroLinha = ConstantesCRM.SVAZIO;
			this.setNumeroLinha(ConstantesCRM.SVAZIO);
			// this.propietarioLinha = ConstantesCRM.SVAZIO;
			this.setPropietarioLinha(ConstantesCRM.SVAZIO);
			// this.idListaConteudo = ConstantesCRM.SZERO;
			this.setIdListaConteudo(ConstantesCRM.SZERO);
			// this.idCampanha = ConstantesCRM.SZERO;
			this.setIdCampanha(ConstantesCRM.SZERO);

			ListaAtendimentoCampanhaVO vo = listaAtendimentoCampanhaFac.getListaAtendimentoCampanha(user);
			if (vo != null && vo.getCampanhasRelacionadasArray() != null) {

				// campanhasRelacionadas =
				// listaAtendimentoCampanhaFac.getListaAtendimentoCampanha(user).getCampanhasRelacionadasArray();
				setCampanhasRelacionadas(vo.getCampanhasRelacionadasArray());

			}

		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="showQuestionario" path="/questionario/begin.do"
	 * @jpf:forward name="begin" path="fechaFrame.html"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward ViewScriptAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ViewScriptActionForm form = (ViewScriptActionForm) formParam;

		// String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			request.setAttribute("operacao", ConstantesCRM.STWO);

			if (ConstantesCRM.SONE.equals(request.getSession().getAttribute(ConstantesCRM.CT_AT_OPERACAO_EXECUTAR_CAMPANHA))) {
				this.setIdListaConteudo(form.getIdListaConteudoSel());
				this.setIdCampanha(form.getCampanha());
				this.setIdSubCampanhaHistorico(form.getIdSubCampanhaHistoricoSel());
				request.setAttribute("operacao", ConstantesCRM.SFOUR);

			} else {

				if (!ConstantesCRM.SZERO.equals(request.getSession().getAttribute(ConstantesCRM.CT_AT_OPERACAO_EXECUTAR_CAMPANHA))) {
					Exception e = new Exception("Operação invalida");
					FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/campanha/ExecutarCampanha/telaInicial.do");
					request.setAttribute(ConstantesCRM.SFORMERROR, formError);
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(ConstantesCRM.SERROR);
				}
			}
			if (this.getIdCampanha() == null || ConstantesCRM.SZERO.equals(this.getIdCampanha())) {
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("begin");
			}
			// request.setAttribute("idCanalCampanha", this.idCampanha);
			request.setAttribute("idCanalCampanha", this.getIdCampanha());
			// request.setAttribute("idSubCampanhaHistorico",this.idSubCampanhaHistorico);
			request.setAttribute("idSubCampanhaHistorico", this.getIdSubCampanhaHistorico());
			request.setAttribute("idListaConteudo", this.getIdListaConteudo());
			// Eu acho que não usa mais
			// --request.setAttribute("idTipoSubMotivoCampanha", ConstantesCRM.SZERO);
			// --request.setAttribute("idTipoMotivoCampanha", ConstantesCRM.SZERO);
			// --request.setAttribute("idSubMotivoCampanha", ConstantesCRM.SZERO);
			// --request.setAttribute("idCliente", ConstantesCRM.SONE);
			// --request.setAttribute("idMotivoCampanha", ConstantesCRM.SONE);
			/*
			 * parametros que vou precisar ter que usar no retorno do questionario, para associar com
			 * campanha.MotivoCampanha e campanha.AtendimentoCampanha
			 */
			request.getSession().setAttribute("propietarioLinha", this.getPropietarioLinha());
			request.getSession().setAttribute("numeroLinha", this.getNumeroLinha());
			request.getSession().setAttribute("idListaConteudo", this.getIdListaConteudo());
			request.getSession().setAttribute("idCanalCampanha", this.getIdCampanha());
			request.getSession().setAttribute("idSubCampanhaHistorico", this.getIdSubCampanhaHistorico());
		} catch (Exception e) {
			// Observação: Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("showQuestionario");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="AtendimentoCampanha.jsp"
	 */
	protected ActionForward LigarManualmente(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ViewScriptActionForm form = (ViewScriptActionForm) formParam;

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		try {
			if (getCampanhasRelacionadas() != null && getCampanhasRelacionadas().length > 0) {

				ContatoAtendimentoCampanhaVO lista = listaAtendimentoCampanhaFac.getContatoAtendimentoCampanha(user, form.getCampanha());
				this.setNumeroLinha(lista.getNrTelefone());
				this.setPropietarioLinha(lista.getNmContato());
				this.setIdListaConteudo(String.valueOf(lista.getIdListaConteudo()));
				this.setIdCampanha(form.getCampanha());
				this.setIdSubCampanhaHistorico(form.getIdSubCampanhaHistoricoSel());

			} else {
				carregarLista(user);
			}
		} catch (Exception e) {
			throw e;
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="TerminoCampanha.jsp"
	 */
	protected ActionForward QuestionarioDone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			// this.idCampanha = (String) request.getSession().getAttribute("idCanalCampanha");
			this.setIdCampanha((String) request.getSession().getAttribute("idCanalCampanha"));
			// this.idSubCampanhaHistorico = (String) request.getSession().getAttribute("idSubCampanhaHistorico");
			this.setIdSubCampanhaHistorico((String) request.getSession().getAttribute("idSubCampanhaHistorico"));
			// this.propietarioLinha = (String) request.getSession().getAttribute("propietarioLinha");
			this.setPropietarioLinha((String) request.getSession().getAttribute("propietarioLinha"));
			// this.numeroLinha = (String) request.getSession().getAttribute("numeroLinha");
			this.setNumeroLinha((String) request.getSession().getAttribute("numeroLinha"));
			getObtemCampanhaListaActionForm().setStatusDispSelecionado(ConstantesCRM.SVAZIO);
			getObtemCampanhaListaActionForm().setMotivoDispSelecionado(ConstantesCRM.SVAZIO);
			getObtemCampanhaListaActionForm().setMotivoDisp(null);
			getObtemCampanhaListaActionForm().setSubMotivoDispSelecionado(ConstantesCRM.SVAZIO);
			getObtemCampanhaListaActionForm().setSubMotivoDisp(null);

			GrupoCampanhaApoioVO configCampanha = configCampanhaFacade.getGrupoConfigurarCampanhaVO(user, Integer.parseInt(this.getIdCampanha()), Integer.parseInt(this.getIdSubCampanhaHistorico()), 0, 1);
			getObtemCampanhaListaActionForm().setStatusDisp(configCampanha.getSubGrupoApoioVOArray(0).getApoioVOArray());
		} catch (Exception e) {
			throw e;
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="IFrameObtemListas.jsp"
	 * @jpf:forward name="salvar" path="fechaFrame.html"
	 * @jpf:forward name="telaInicial" path="telaInicial.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward obtemCampanhaListaAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ObtemCampanhaListaActionForm form = (ObtemCampanhaListaActionForm) formParam;

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String forward = new String("salvar");
		try {
			/*************** Salva as listas carregadas para o formulario atual *************************/
			form.setStatusDisp(getObtemCampanhaListaActionForm().getStatusDisp());
			form.setMotivoDisp(getObtemCampanhaListaActionForm().getMotivoDisp());
			form.setSubMotivoDisp(getObtemCampanhaListaActionForm().getSubMotivoDisp());

			// obtemCampanhaListaActionForm = form;
			this.setObtemCampanhaListaActionForm(form);

			// if("salvar".equals(request.getParameter(ConstantesCRM.SACTION)))
			// {

			listaAtendimentoCampanhaFac.setQuestionarioStatus(user, (String) request.getSession().getAttribute(ConstantesCRM.CT_QU_IDENTIFICADOR_ATENDIMENTO), form.getStatusDispSelecionado(), form.getMotivoDispSelecionado(), form.getSubMotivoDispSelecionado(), this.getIdSubCampanhaHistorico());

			/*
			 * Removo as variaveis de sessão
			 */
			request.getSession().removeAttribute(ConstantesCRM.CT_QU_IDENTIFICADOR_ATENDIMENTO);
			request.getSession().removeAttribute("idCanalCampanha");
			request.getSession().removeAttribute("idSubCampanhaHistorico");
			request.getSession().removeAttribute("numeroLinha");
			request.getSession().removeAttribute("propietarioLinha");

			/*
			 * Operações 0 - Atendimento normal 1 - Atendimento tela inicial Estas variaveis são definidas na action:
			 * ViewScriptAction
			 */
			if (ConstantesCRM.SONE.equals(request.getSession().getAttribute(ConstantesCRM.CT_AT_OPERACAO_EXECUTAR_CAMPANHA))) {
				// request.getSession().removeAttribute(ConstantesCRM.CT_AT_OPERACAO_EXECUTAR_CAMPANHA);
				forward = "telaInicial";
			}
			// else
			// {
			// request.getSession().removeAttribute(ConstantesCRM.CT_AT_OPERACAO_EXECUTAR_CAMPANHA);
			// forward = "salvar";
			// }
			// request.getSession().removeAttribute(ConstantesCRM.CT_AT_OPERACAO_EXECUTAR_CAMPANHA);
			// }

		} catch (Exception e) {
			// Observação: Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, "/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(forward);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="campanhasRelacionadasTelaInicial.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 * @jpf:forward name="onReturnTelaInicial" path="/questionario/onReturnTelaInicial.do"
	 */
	protected ActionForward telaInicial(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		if (request.getSession().getAttribute("DADOS_QST_TELA_INICIAL") != null) {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("onReturnTelaInicial");
		}

		try {

			ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);

			this.setNumeroLinha(ConstantesCRM.SVAZIO);
			this.setPropietarioLinha(ConstantesCRM.SVAZIO);
			this.setIdListaConteudo(ConstantesCRM.SZERO);

			ListaAtendimentoCampanhaVO vo = listaAtendimentoCampanhaFac.getListaAtendimentoCampanhaLinha(user, parametros.getNrLinha());
			if (vo != null && vo.getCampanhasRelacionadasArray() != null) {
				this.campanhasRelacionadas = vo.getCampanhasRelacionadasArray();
				request.setAttribute("campanhasRelacionadas", vo.getCampanhasRelacionadasArray());
			}

			request.getSession().setAttribute(ConstantesCRM.CT_AT_OPERACAO_EXECUTAR_CAMPANHA, ConstantesCRM.SONE);

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="IFrameObtemListas.jsp"
	 */
	protected ActionForward obtemMotivos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ObtemCampanhaListaActionForm form = (ObtemCampanhaListaActionForm) formParam;

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			/*************** Salva as listas carregadas para o formulario atual *************************/
			form.setStatusDisp(getObtemCampanhaListaActionForm().getStatusDisp());
			form.setMotivoDisp(getObtemCampanhaListaActionForm().getMotivoDisp());
			form.setSubMotivoDisp(getObtemCampanhaListaActionForm().getSubMotivoDisp());

			// obtemCampanhaListaActionForm = form;
			this.setObtemCampanhaListaActionForm(form);

			// obtemCampanhaListaActionForm.setMotivoDispSelecionado(ConstantesCRM.SVAZIO);
			getObtemCampanhaListaActionForm().setMotivoDispSelecionado(ConstantesCRM.SVAZIO);
			// obtemCampanhaListaActionForm.setSubMotivoDispSelecionado(ConstantesCRM.SVAZIO);
			getObtemCampanhaListaActionForm().setSubMotivoDispSelecionado(ConstantesCRM.SVAZIO);

			int iIdStatus = Integer.parseInt(request.getParameter("idStatus"));

			// GrupoCampanhaApoioVO configMotivo;

			/*********************** Lista de Motivo *******************************************************************************************/
			GrupoCampanhaApoioVO configMotivo = configCampanhaFacade.getMotivoId(user, Integer.parseInt(this.getIdSubCampanhaHistorico()), iIdStatus, 1);
			getObtemCampanhaListaActionForm().setMotivoDisp(configMotivo.getSubGrupoApoioVOArray(0).getApoioVOArray());
		} catch (Exception e) {
			throw e;
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="IFrameObtemListas.jsp"
	 */
	protected ActionForward obtemSubMotivos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ObtemCampanhaListaActionForm form = (ObtemCampanhaListaActionForm) formParam;

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			/*************** Salva as listas carregadas para o formulario atual *************************/
			form.setStatusDisp(getObtemCampanhaListaActionForm().getStatusDisp());
			// form.setMotivoDisp(obtemCampanhaListaActionForm.getMotivoDisp());
			form.setMotivoDisp(getObtemCampanhaListaActionForm().getMotivoDisp());
			// form.setSubMotivoDisp(obtemCampanhaListaActionForm.getSubMotivoDisp());
			form.setSubMotivoDisp(getObtemCampanhaListaActionForm().getSubMotivoDisp());

			// obtemCampanhaListaActionForm = form;
			setObtemCampanhaListaActionForm(form);

			// obtemCampanhaListaActionForm.setSubMotivoDispSelecionado(ConstantesCRM.SVAZIO);
			getObtemCampanhaListaActionForm().setSubMotivoDispSelecionado(ConstantesCRM.SVAZIO);

			int iIdMotivo = Integer.parseInt(request.getParameter("idMotivo"));
			int iIdStatus = Integer.parseInt(request.getParameter("idStatus"));
			GrupoCampanhaApoioVO configSubMotivo = configCampanhaFacade.getSubMotivoId(user, Integer.parseInt(this.getIdSubCampanhaHistorico()), iIdStatus, iIdMotivo, 1);
			getObtemCampanhaListaActionForm().setSubMotivoDisp(configSubMotivo.getSubGrupoApoioVOArray(0).getApoioVOArray());
		} catch (Exception e) {
			throw e;
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class ViewScriptActionForm extends ActionForm {

		private static final long serialVersionUID = -5309194144637719067L;
		private String idListaConteudoSel;
		private String[] idListaConteudo;
		// private int tipoOperacao;
		private String campanha = ConstantesCRM.SZERO;
		private String[] idSubCampanhaHistorico;
		private String idSubCampanhaHistoricoSel;

		public void setIdSubCampanhaHistorico(String[] idSubCampanhaHistorico) {
			this.idSubCampanhaHistorico = idSubCampanhaHistorico;
		}

		public String[] getIdSubCampanhaHistorico() {
			return this.idSubCampanhaHistorico;
		}

		public void setIdSubCampanhaHistoricoSel(String idSubCampanhaHistoricoSel) {
			this.idSubCampanhaHistoricoSel = idSubCampanhaHistoricoSel;
		}

		public String getIdSubCampanhaHistoricoSel() {
			return this.idSubCampanhaHistoricoSel;
		}

		public void setCampanha(String campanha) {
			this.campanha = campanha;
		}

		public String getCampanha() {
			return this.campanha;
		}

		public void setIdListaConteudo(String[] idListaConteudo) {
			this.idListaConteudo = idListaConteudo;
		}

		public String[] getIdListaConteudo() {
			return this.idListaConteudo;
		}

		public void setIdListaConteudoSel(String idListaConteudoSel) {
			this.idListaConteudoSel = idListaConteudoSel;
		}

		public String getIdListaConteudoSel() {
			return this.idListaConteudoSel;
		}
	}

	public static class ObtemCampanhaListaActionForm extends ActionForm {

		private static final long serialVersionUID = 1404516436945310161L;

		private int idSubCampanha;
		private int idCampanha;
		private String sSgSubCampanha;
		private String sSgCampanha;
		private ApoioVO[] statusDisp;
		private String statusDispSelecionado;
		private ApoioVO[] motivoDisp;
		private String motivoDispSelecionado;
		private ApoioVO[] subMotivoDisp;
		private String subMotivoDispSelecionado;

		/***********************************************************************************************
		 * TIPO STATUS CAMPANHA
		 **********************************************************************************************/

		/********************* Status Disponiveis ******************************************/
		public ApoioVO[] getStatusDisp() {
			if (this.statusDisp == null) {
				this.statusDisp = new ApoioVO[0];
			}
			return this.statusDisp;
		}

		public void setStatusDisp(ApoioVO[] lista) {
			statusDisp = lista;
		}

		public String getStatusDispSelecionado() {
			return this.statusDispSelecionado;
		}

		public void setStatusDispSelecionado(String lista) {
			this.statusDispSelecionado = lista;
		}

		/***********************************************************************************************
		 * TIPO MOTIVO CAMPANHA
		 **********************************************************************************************/

		/********************* Motivos Disponiveis ******************************************/
		public ApoioVO[] getMotivoDisp() {
			if (this.motivoDisp == null) {
				this.motivoDisp = new ApoioVO[0];
			}
			return this.motivoDisp;
		}

		public void setMotivoDisp(ApoioVO[] lista) {
			motivoDisp = lista;
		}

		public String getMotivoDispSelecionado() {
			return motivoDispSelecionado;
		}

		public void setMotivoDispSelecionado(String lista) {
			motivoDispSelecionado = lista;
		}

		/***********************************************************************************************
		 * TIPO SUBMOTIVO CAMPANHA
		 **********************************************************************************************/

		/********************* SubMotivos Disponiveis ******************************************/
		public ApoioVO[] getSubMotivoDisp() {
			if (this.subMotivoDisp == null) {
				this.subMotivoDisp = new ApoioVO[0];
			}
			return this.subMotivoDisp;
		}

		public void setSubMotivoDisp(ApoioVO[] lista) {
			this.subMotivoDisp = lista;
		}

		public String getSubMotivoDispSelecionado() {
			return subMotivoDispSelecionado;
		}

		public void setSubMotivoDispSelecionado(String lista) {
			subMotivoDispSelecionado = lista;
		}

		/*********************************************************************************************************************/

		public void setIdCampanha(int idCampanha) {
			this.idCampanha = idCampanha;
		}

		public int getIdCampanha() {
			return this.idCampanha;
		}

		public void setIdSubCampanha(int idSubCampanha) {
			this.idSubCampanha = idSubCampanha;
		}

		public void setsSgCampanha(String sSgCampanha) {
			this.sSgCampanha = sSgCampanha;
		}

		public String getsSgCampanha() {
			return this.sSgCampanha;
		}

		public void setsSgSubCampanha(String sSgSubCampanha) {
			this.sSgSubCampanha = sSgSubCampanha;
		}

		public String getsSgSubCampanha() {
			return this.sSgSubCampanha;
		}

		public int getIdSubCampanha() {
			return this.idSubCampanha;
		}
	}

	public static class QuestionarioStatusForm extends ActionForm {

		private static final long serialVersionUID = 4725849188718061730L;

		private String idTipoSubMotivoCampanha;
		private String idTipoMotivoCampanha;
		private String idTipoStatusCampanha;

		public void setIdTipoStatusCampanha(String idTipoStatusCampanha) {
			this.idTipoStatusCampanha = idTipoStatusCampanha;
		}

		public String getIdTipoStatusCampanha() {
			return this.idTipoStatusCampanha;
		}

		public void setIdTipoMotivoCampanha(String idTipoMotivoCampanha) {
			this.idTipoMotivoCampanha = idTipoMotivoCampanha;
		}

		public String getIdTipoMotivoCampanha() {
			return this.idTipoMotivoCampanha;
		}

		public void setIdTipoSubMotivoCampanha(String idTipoSubMotivoCampanha) {
			this.idTipoSubMotivoCampanha = idTipoSubMotivoCampanha;
		}

		public String getIdTipoSubMotivoCampanha() {
			return this.idTipoSubMotivoCampanha;
		}
	}
}