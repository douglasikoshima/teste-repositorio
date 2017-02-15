package campanha.Relatorio;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.campanha.vo.ApoioVODocument.ApoioVO;
import br.com.vivo.fo.campanha.vo.AreasRegistroVODocument.AreasRegistroVO;
import br.com.vivo.fo.campanha.vo.AreasRegistroVODocument.AreasRegistroVO.AreaRegistro;
import br.com.vivo.fo.campanha.vo.CampanhaExecScriptVODocument.CampanhaExecScriptVO;
import br.com.vivo.fo.campanha.vo.CampanhaFiltroRelatorioVODocument.CampanhaFiltroRelatorioVO;
import br.com.vivo.fo.campanha.vo.CampanhaPerguntaVODocument.CampanhaPerguntaVO;
import br.com.vivo.fo.campanha.vo.CampanhaRelatorioOperadorVODocument.CampanhaRelatorioOperadorVO;
import br.com.vivo.fo.campanha.vo.CampanhaRelatorioVODocument.CampanhaRelatorioVO;
import br.com.vivo.fo.campanha.vo.CampanhaRelatorioVODocument.CampanhaRelatorioVO.Linha;
import br.com.vivo.fo.campanha.vo.CampanhaRelatorioVODocument.CampanhaRelatorioVO.Numerico;
import br.com.vivo.fo.campanha.vo.GrupoCampanhaApoioVODocument.GrupoCampanhaApoioVO;
import br.com.vivo.fo.campanha.vo.RelEfetividadeVODocument.RelEfetividadeVO;
import br.com.vivo.fo.campanha.vo.RelaAgendaAniversarioVODocument.RelaAgendaAniversarioVO;
import br.com.vivo.fo.campanha.vo.RelatorioCampanhaHeaderVODocument.RelatorioCampanhaHeaderVO;
import br.com.vivo.fo.campanha.vo.RelatorioPercentualVODocument.RelatorioPercentualVO;
import br.com.vivo.fo.campanha.vo.RelatorioRespostasVODocument.RelatorioRespostasVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.UFOperadoraUsuarioVODocument.UFOperadoraUsuarioVO;
import br.com.vivo.fo.usuario.vo.UsuariosVODocument.UsuariosVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

@SuppressWarnings({"deprecation","rawtypes","unchecked"})
public class RelatorioController extends AbstractAction {

	private static final long serialVersionUID = -8852223850241408005L;

	private static Logger log = new Logger("campanha");

	protected global.Global globalApp = new global.Global();

	@EJB
	private br.com.vivo.fo.ctrls.campanha.relatorio.RelatorioCampanhaFacade relatorioFacade;

	@EJB
	private br.com.vivo.fo.ctrls.campanha.manter.ApoioFacade apoioFacade;

	@EJB
	private br.com.vivo.fo.ctrls.campanha.configurar.ConfigurarCampanha configCampanhaFacade;

	@EJB
	private br.com.vivo.fo.ctrls.campanha.questionario.QuestionarioCampanhaFacade questFacade;

	public static final int RELATORIO_GERENCIAMIENTO = 1;
	public static final int RELATORIO_EFETIVIDADE = 2;
	public static final int RELATORIO_PERCENTUAL_POR_OPERADOR = 3;
	public static final int RELATORIO_PERCENTUAL_POR_MOTIVOS = 4;
	public static final int RELATORIO_RESPOSTAS = 5;
	public static final int RELATORIO_AGENDA_ANIVERSARIO = 6;

	private static final String SEPARADOR = ",";

	private RelatorioMotivoForm relatorioMotivoForm;
	private RelatorioEfectForm relatorioEfectForm;
	private RelaOperadorForm relaOperadorForm;
	private RelaRespostaForm relaRespostaForm;
	private RelGerenciamentoForm relGerenciamentoForm;
	private RelaAgendaAnivForm relaAgendaAnivForm;
	private RelEfetividadeActionForm relEfetividadeActionForm = new RelEfetividadeActionForm();
	private RelatorioCampanhaHeaderVO headerVO;

	public RelatorioEfectForm getRelatorioEfectForm() {
		if (relatorioEfectForm == null) {
			relatorioEfectForm = new RelatorioEfectForm();
		}
		return this.relatorioEfectForm;
	}

	public RelatorioCampanhaHeaderVO getHeaderVO() {
		if (headerVO == null) {
			headerVO = RelatorioCampanhaHeaderVO.Factory.newInstance();
		}
		return this.headerVO;
	}

	public RelGerenciamentoForm getRelGerenciamentoForm() {
		return relGerenciamentoForm;
	}

	public RelaOperadorForm getRelaOperadorForm() {
		return relaOperadorForm;
	}

	public RelEfetividadeActionForm getRelEfetividadeActionForm() {
		return relEfetividadeActionForm;
	}

	public RelaRespostaForm getRelaRespostaForm() {
		return relaRespostaForm;
	}

	public RelaAgendaAnivForm getRelaAgendaAnivForm() {
		return relaAgendaAnivForm;
	}
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("relEfetividadeAction".equals(mapping.getParameter())) {
			return relEfetividadeAction(mapping, form, request, response);
		} else if ("Done".equals(mapping.getParameter())) {
			return Done(mapping, form, request, response);
		} else if ("obtemListaSubCampanha".equals(mapping.getParameter())) {
			return obtemListaSubCampanha(mapping, form, request, response);
		} else if ("obtemListaVersao".equals(mapping.getParameter())) {
			return obtemListaVersao(mapping, form, request, response);
		} else if ("obtemListaCanal".equals(mapping.getParameter())) {
			return obtemListaCanal(mapping, form, request, response);
		} else if ("cargarAreaRegistro".equals(mapping.getParameter())) {
			return cargarAreaRegistro(mapping, form, request, response);
		} else if ("cargarPergunta".equals(mapping.getParameter())) {
			return cargarPergunta(mapping, form, request, response);
		} else if ("generarRelatorio".equals(mapping.getParameter())) {
			return generarRelatorio(mapping, form, request, response);
		} else if ("generarImpressao".equals(mapping.getParameter())) {
			return generarImpressao(mapping, form, request, response);
		} else if ("generarArquivo".equals(mapping.getParameter())) {
			return generarArquivo(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="relEfetividadeAction.do"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		// String user = ConstantesCRM.getCurrentUser(request.getSession()) ;
		if (request.getAttribute("idRelatorio") != null) {
			request.removeAttribute("idRelatorio");
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="motivo" path="index.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward relEfetividadeAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelEfetividadeActionForm form = (RelEfetividadeActionForm) formParam;

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		relEfetividadeActionForm = form;
		try {
			GrupoCampanhaApoioVO campanhaApoio = apoioFacade.getApoioCampanha(user);
			form.setListaCampanha(campanhaApoio.getSubGrupoApoioVOArray(0).getApoioVOArray());
			getRelEfetividadeActionForm().setListaCampanha(campanhaApoio.getSubGrupoApoioVOArray(0).getApoioVOArray());
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("motivo");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="done" return-action="RelatorioDone"
	 */
	protected ActionForward Done(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("done");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="innerBrowserSubCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward obtemListaSubCampanha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelEfetividadeActionForm form = (RelEfetividadeActionForm) formParam;

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			form.setListaCampanha(getRelEfetividadeActionForm().getListaCampanha());
			GrupoCampanhaApoioVO grupoSubCampanhaApoio = apoioFacade.getApoioSubCampanha(user, form.getCampanhaSelecionada());
			form.setListaSubCampanha(grupoSubCampanhaApoio.getSubGrupoApoioVOArray(0).getApoioVOArray());
			relEfetividadeActionForm = new RelEfetividadeActionForm();
			relEfetividadeActionForm = form;
			request.setAttribute("idRelatorio", request.getParameter("idRelatorio"));
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="innerBrowserVersao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward obtemListaVersao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelEfetividadeActionForm form = (RelEfetividadeActionForm) formParam;

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			form.setListaCampanha(relEfetividadeActionForm.getListaCampanha());
			form.setListaSubCampanha(relEfetividadeActionForm.getListaSubCampanha());

			GrupoCampanhaApoioVO grupoSubCampanhaApoio = apoioFacade.getApoioVersao(user, form.getSubcampanhaSelecionada());

			relEfetividadeActionForm.setListaVersao(grupoSubCampanhaApoio.getSubGrupoApoioVOArray(0).getApoioVOArray());

			if (grupoSubCampanhaApoio.getSubGrupoApoioVOArray(0).getApoioVOArray().length > 0) {

				String idVersao = grupoSubCampanhaApoio.getSubGrupoApoioVOArray(0).getApoioVOArray()[0].getCodigo();

				GrupoCampanhaApoioVO listaCanal = apoioFacade.getApoioCanalCampanha(user, idVersao, 0);
				if (listaCanal.getSubGrupoApoioVOArray(0) != null) {
					relEfetividadeActionForm.setListaCanal(listaCanal.getSubGrupoApoioVOArray(0).getApoioVOArray());
				}

				relEfetividadeActionForm.setListaPerfil(apoioFacade.getApoioGrupo(user, Integer.parseInt(idVersao), 1).getSubGrupoApoioVOArray(0).getApoioVOArray());

				GrupoCampanhaApoioVO configMotivo = configCampanhaFacade.getMotivoId(user, Integer.parseInt(idVersao), -1, 2);

				relEfetividadeActionForm.setListaMotivo(configMotivo.getSubGrupoApoioVOArray(0).getApoioVOArray());

				UsuariosVO ufOperadora = UsuariosVO.Factory.newInstance();
				ufOperadora = relatorioFacade.getUFOperadora(user);
				relEfetividadeActionForm.setListaRegional(ufOperadora.getUFOperadoraUsuarioVOArray());

				CampanhaPerguntaVO[] listaPergunta = new CampanhaPerguntaVO[0];
				relEfetividadeActionForm.setListaPergunta(listaPergunta);

				relEfetividadeActionForm.setSubcampanhaSelecionada(form.getSubcampanhaSelecionada());
			}

			request.setAttribute("idRelatorio", request.getParameter("idRelatorio"));
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="innerBrowserSubCampanhaItens.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward obtemListaCanal(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelEfetividadeActionForm form = (RelEfetividadeActionForm) formParam;

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			GrupoCampanhaApoioVO listaCanal = apoioFacade.getApoioCanalCampanha(user, form.getVersaoSelecionada(), 0);
			if (listaCanal.getSubGrupoApoioVOArray(0) != null) {
				relEfetividadeActionForm.setListaCanal(listaCanal.getSubGrupoApoioVOArray(0).getApoioVOArray());
			}
			relEfetividadeActionForm.setListaPerfil(apoioFacade.getApoioGrupo(user, Integer.parseInt(form.getVersaoSelecionada()), 1).getSubGrupoApoioVOArray(0).getApoioVOArray());

			GrupoCampanhaApoioVO configMotivo = configCampanhaFacade.getMotivoId(user, Integer.parseInt(form.getVersaoSelecionada()), -1, 2);

			relEfetividadeActionForm.setListaMotivo(configMotivo.getSubGrupoApoioVOArray(0).getApoioVOArray());

			UsuariosVO ufOperadora = UsuariosVO.Factory.newInstance();
			ufOperadora = relatorioFacade.getUFOperadora(user);
			relEfetividadeActionForm.setListaRegional(ufOperadora.getUFOperadoraUsuarioVOArray());

			CampanhaPerguntaVO[] listaPergunta = new CampanhaPerguntaVO[0];
			relEfetividadeActionForm.setListaPergunta(listaPergunta);

			relEfetividadeActionForm.setSubcampanhaSelecionada(form.getSubcampanhaSelecionada());
			request.setAttribute("idRelatorio", request.getParameter("idRelatorio"));
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="principal" path="innerBrowserAreaRegistro.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward cargarAreaRegistro(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelEfetividadeActionForm form = (RelEfetividadeActionForm) formParam;

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			AreasRegistroVO area = relatorioFacade.getAreaRegistro(user, form.getRegionalSelecionada());
			getRelEfetividadeActionForm().setListaAreaRegistro(area.getAreaRegistroArray());
			request.setAttribute("idRelatorio", request.getParameter("idRelatorio"));
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("principal");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="principal" path="innerBrowserPergunta.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward cargarPergunta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelEfetividadeActionForm form = (RelEfetividadeActionForm) formParam;

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			CampanhaExecScriptVO campanhaExecScriptVO = questFacade.getListaPergunta(user, Integer.parseInt(form.getCanalSelecionado()), 0, 0);
			getRelEfetividadeActionForm().setListaPergunta(campanhaExecScriptVO.getCampanhaPerguntaVOArray());
			getRelEfetividadeActionForm().setCanalSelecionado(form.getCanalSelecionado());
			request.setAttribute("idRelatorio", request.getParameter("idRelatorio"));
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("principal");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="RelaGeren" path="RelatorioGere.jsp"
	 * @jpf:forward name="RelaMotivo" path="RelatorioMotivo.jsp"
	 * @jpf:forward name="RelaEfectiv" path="RelatorioEfeti.jsp"
	 * @jpf:forward name="RelaOperador" path="RelaOperador.jsp"
	 * @jpf:forward name="RelaResposta" path="RelatorioResposta.jsp"
	 * @jpf:forward name="RelaAgendaAniv" path="RelaAgendaAniv.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward generarRelatorio(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelEfetividadeActionForm form = (RelEfetividadeActionForm) formParam;

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		try {
			form.getCampanhaFiltroRelatorioVO().setIdRelatorio(request.getParameter("idRelatorio"));
			String rela = ConstantesCRM.SVAZIO;
			int idRelatorio = 0;

			for (int a = 0; a < relEfetividadeActionForm.listaCampanha.length; a++) {
				if (String.valueOf(relEfetividadeActionForm.listaCampanha[a].getVariante()).equals(form.getCampanhaSelecionada())) {
					relEfetividadeActionForm.setSgCampanha(relEfetividadeActionForm.listaCampanha[a].getSigla());
					a = a + relEfetividadeActionForm.listaCampanha.length;
				}
			}

			for (int a = 0; a < relEfetividadeActionForm.listaSubCampanha.length; a++) {
				if (String.valueOf(relEfetividadeActionForm.listaSubCampanha[a].getCodigo()).equals(form.getSubcampanhaSelecionada())) {
					relEfetividadeActionForm.setNmSubCampanha(relEfetividadeActionForm.listaSubCampanha[a].getNmSubCampanha());
					a = a + relEfetividadeActionForm.listaSubCampanha.length;
				}
			}
			relEfetividadeActionForm.setDtFim(form.getDtFim());
			relEfetividadeActionForm.setDtInicio(form.getDtInicio());

			if ((request.getParameter("idRelatorio") != null) && (!request.getParameter("idRelatorio").equals(ConstantesCRM.SVAZIO))) {
				idRelatorio = Integer.parseInt(request.getParameter("idRelatorio"));
			}

			switch (idRelatorio) {
			case RELATORIO_GERENCIAMIENTO:
				CampanhaRelatorioVO campG = relatorioFacade.getRelatorioGere(user, form.getCampanhaFiltroRelatorioVO());

				if (campG.getTotal().equals("0")) {
					request.setAttribute("msgErrorRelatorio", "A Pesquisa Não Retornou Resultado!");
					rela = ConstantesCRM.SUCCESS;
					request.setAttribute("idRelatorio", request.getParameter("idRelatorio"));
				} else {
					relGerenciamentoForm = new RelGerenciamentoForm();
					relGerenciamentoForm.setCampanhaRelatorioVO(campG);
					this.headerVO = campG.getRelatorioCampanhaHeaderVO();
					this.headerVO.setTotal(campG.getTotal());

					if (this.headerVO.getSgUsuario() == null || this.headerVO.getSgUsuario().equals(ConstantesCRM.SVAZIO)) {
						this.headerVO.setSgUsuario("todos");
					}
					rela = "RelaGeren";
				}
				break;

			case RELATORIO_EFETIVIDADE:
				RelEfetividadeVO relEfectividadeVO = relatorioFacade.getRelaEfetividade(user, form.getCampanhaFiltroRelatorioVO());

				if (relEfectividadeVO.getTotal().equals(ConstantesCRM.SZERO)) {
					request.setAttribute("msgErrorRelatorio", "A Pesquisa Não Retornou Resultado!");
					rela = ConstantesCRM.SUCCESS;
					request.setAttribute("idRelatorio", request.getParameter("idRelatorio"));
				} else {
					relatorioEfectForm = new RelatorioEfectForm();
					relatorioEfectForm.setLinha(relEfectividadeVO.getNumericoArray());
					relatorioEfectForm.setRelEfetividadeVO(relEfectividadeVO);
					this.headerVO = relEfectividadeVO.getRelatorioCampanhaHeaderVO();
					this.headerVO.setTotal(relEfectividadeVO.getTotal());
					rela = "RelaEfectiv";
				}
				break;

			case RELATORIO_PERCENTUAL_POR_OPERADOR:
				CampanhaRelatorioOperadorVO campPP = relatorioFacade.getRelaOperador(user, form.getCampanhaFiltroRelatorioVO2());

				if (relaOperadorForm == null) {
					relaOperadorForm = new RelaOperadorForm();
				}

				relaOperadorForm.setNumerico(campPP.getNumericoArray());
				relEfetividadeActionForm.setTotalReg(campPP.getTotal());

				if (campPP.getTotal().equals("0")) {
					request.setAttribute("msgErrorRelatorio", "A Pesquisa Não Retornou Resultado!");
					rela = ConstantesCRM.SUCCESS;
					request.setAttribute("idRelatorio", request.getParameter("idRelatorio"));
				} else {
					relEfetividadeActionForm.setSgOperador(form.getCampanhaFiltroRelatorioVO().getSgOperador());

					this.headerVO = campPP.getRelatorioCampanhaHeaderVO();

					if (form.getCampanhaFiltroRelatorioVO().getSgOperador() == null || form.getCampanhaFiltroRelatorioVO().getSgOperador().equals("")) {
						this.headerVO.setSgUsuario("todos");
					} else {
						this.headerVO.setSgUsuario(form.getCampanhaFiltroRelatorioVO().getSgOperador());
					}

					rela = "RelaOperador";
					this.headerVO.setTotal(campPP.getTotal());
				}
				break;

			case RELATORIO_PERCENTUAL_POR_MOTIVOS:
				RelatorioPercentualVO relPM = relatorioFacade.getRelaPercentual(user, form.getCampanhaFiltroRelatorioVO());

				if (relPM.getTotal().equals("0")) {
					request.setAttribute("msgErrorRelatorio", "A Pesquisa Não Retornou Resultado!");
					rela = ConstantesCRM.SUCCESS;
					request.setAttribute("idRelatorio", request.getParameter("idRelatorio"));
				} else {
					if (relatorioMotivoForm == null) {
						relatorioMotivoForm = new RelatorioMotivoForm();
					}

					relatorioMotivoForm.setRelatorioPercentualVO(relPM);
					this.headerVO = relPM.getRelatorioCampanhaHeaderVO();
					this.headerVO.setTotal(relPM.getTotal());

					if (this.headerVO.getInDistincao() != null && this.headerVO.getInDistincao().equals("1")) {
						this.headerVO.setInDistincao(ConstantesCRM.SYES);
					} else {
						this.headerVO.setInDistincao("Não");
					}

					rela = "RelaMotivo";
				}
				break;

			case RELATORIO_RESPOSTAS:
				RelatorioRespostasVO relRespVO = relatorioFacade.getRelaRespostas(user, form.getCampanhaFiltroRelatorioVO());
				// String oldPergunta = "", newPergunta = "";
				// int total = 0;

				if (relRespVO.getTotal().equals(ConstantesCRM.SZERO)) {
					request.setAttribute("msgErrorRelatorio", "A Pesquisa Não Retornou Resultado!");
					rela = ConstantesCRM.SUCCESS;
					request.setAttribute("idRelatorio", request.getParameter("idRelatorio"));
				} else {
					if (relaRespostaForm == null) {
						relaRespostaForm = new RelaRespostaForm();
					}
					relaRespostaForm.setRelatorioRespostasVO(relRespVO.getPerguntasArray(0));
					relEfetividadeActionForm.setPerguntaSelecionada(form.getPerguntaSelecionada());
					this.headerVO = relRespVO.getRelatorioCampanhaHeaderVO();
					this.headerVO.setTotal(relRespVO.getTotal());

					if (this.headerVO.getInDistincao() != null && this.headerVO.getInDistincao().equals("1")) {
						this.headerVO.setInDistincao(ConstantesCRM.SYES);
					} else {
						this.headerVO.setInDistincao("Não");
					}

					rela = "RelaResposta";
				}
				break;

			case RELATORIO_AGENDA_ANIVERSARIO:
				RelaAgendaAniversarioVO relaAgendaAniversarioVO = relatorioFacade.getRelaAgendaAniv(user, form.getCampanhaFiltroRelatorioVO());
				relaAgendaAnivForm = new RelaAgendaAnivForm();
				relaAgendaAnivForm.setLinhaAniversario(relaAgendaAniversarioVO.getLinhaAniversarioArray());
				relEfetividadeActionForm.setTotalReg(String.valueOf(relaAgendaAnivForm.getLinhaAniversario().length));
				if (relaAgendaAniversarioVO.getLinhaAniversarioArray(0).getLinha().equals("A Pesquisa Não Retornou Resultado")) {
					request.setAttribute("msgErrorRelatorio", "A Pesquisa Não Retornou Resultado!");
					relEfetividadeActionForm.setSgOperador(form.getSgOperador());
					relEfetividadeActionForm.setLinha(form.getLinha());
					relEfetividadeActionForm.setDataAniversario(form.getDataAniversario());
					rela = ConstantesCRM.SUCCESS;
					request.setAttribute("idRelatorio", request.getParameter("idRelatorio"));
				} else {
					this.headerVO = relaAgendaAniversarioVO.getRelatorioCampanhaHeaderVO();
					this.headerVO.setTotal(Integer.toString(relaAgendaAniversarioVO.getLinhaAniversarioArray().length));
					rela = "RelaAgendaAniv";
				}
				request.setAttribute("total", String.valueOf(relaAgendaAniversarioVO.getLinhaAniversarioArray().length));
				break;
			}

			relEfetividadeActionForm.setCampanhaSelecionada(ConstantesCRM.SVAZIO);

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(rela);
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

	}

	public RelatorioMotivoForm getRelatorioMotivoForm() {
		if (this.relatorioMotivoForm == null) {
			this.relatorioMotivoForm = new RelatorioMotivoForm();
		}

		return this.relatorioMotivoForm;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="relaGeren" path="relatorioGereImpressao.jsp"
	 * @jpf:forward name="relaMotivo" path="relatorioMotivoImpressao.jsp"
	 * @jpf:forward name="relaEfectiv" path="relatorioEfetiImpressao.jsp"
	 * @jpf:forward name="relaOperador" path="relaOperadorImpressao.jsp"
	 * @jpf:forward name="relaResposta" path="relatorioRespostaImpressao.jsp"
	 * @jpf:forward name="relaAgendaAniv" path="relaAgendaAnivImpressao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward generarImpressao(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		int idRelatorio = 0;

		String valor = ConstantesCRM.SVAZIO, descricao = ConstantesCRM.SVAZIO, forwardName = ConstantesCRM.SUCCESS, urlParam = "&width=350&height=250&format=png", urlParamRespostas = "&width=290&height=280&format=png", graphCallParam = "/FrontOfficeWeb/SimpleChart?class=campanha.Relatorio.", nameParam = ConstantesCRM.SVAZIO;

		String[] c;

		double[] v;

		try {

			if ((request.getParameter("idRelatorio") != null) && (!request.getParameter("idRelatorio").equals(ConstantesCRM.SVAZIO))) {
				idRelatorio = Integer.parseInt(request.getParameter("idRelatorio"));
			}

			switch (idRelatorio) {
			case RELATORIO_GERENCIAMIENTO:

				c = new String[relGerenciamentoForm.getCampanhaRelatorioVO().getGraficoArray().length];
				v = new double[relGerenciamentoForm.getCampanhaRelatorioVO().getGraficoArray().length];

				for (int i = 0; i < relGerenciamentoForm.getCampanhaRelatorioVO().getGraficoArray().length; i++) {
					c[i] = relGerenciamentoForm.getCampanhaRelatorioVO().getGraficoArray(i).getDescricao();
					valor = relGerenciamentoForm.getCampanhaRelatorioVO().getGraficoArray(i).getValor();
					try {
						v[i] = Double.parseDouble(valor);
					} catch (NumberFormatException nfe) {
						v[i] = Double.parseDouble(ConstantesCRM.SZERO);
						log.error(nfe.getMessage());
					}
					urlParam += "&" + c[i] + "=" + v[i];
				}
				nameParam = "&name=RELATÓRIO DE GERENCIAMENTO";

				request.setAttribute("graph", graphCallParam + "Graph" + urlParam.replaceAll("\"", "") + nameParam + "&font=" + "10");
				urlParam = "&width=350&height=250&format=png";

				c = new String[relGerenciamentoForm.getCampanhaRelatorioVO().getGraficoAuxiliarArray().length];
				v = new double[relGerenciamentoForm.getCampanhaRelatorioVO().getGraficoAuxiliarArray().length];

				for (int i = 0; i < relGerenciamentoForm.getCampanhaRelatorioVO().getGraficoAuxiliarArray().length; i++) {
					c[i] = relGerenciamentoForm.getCampanhaRelatorioVO().getGraficoAuxiliarArray(i).getDescricao();
					valor = relGerenciamentoForm.getCampanhaRelatorioVO().getGraficoAuxiliarArray(i).getValor();
					try {
						v[i] = Double.parseDouble(valor);
					} catch (NumberFormatException nfe) {
						v[i] = Double.parseDouble(ConstantesCRM.SZERO);
						log.error(nfe.getMessage());
					}
					urlParam += "&" + c[i] + "=" + v[i];
				}
				request.setAttribute("graph2", graphCallParam + "Graph" + urlParam.replaceAll("\"", "") + nameParam + "&font=" + "10");

				forwardName = "relaGeren";

				break;

			case RELATORIO_EFETIVIDADE:
				int size = relatorioEfectForm.getRelEfetividadeVO().getGraficoArray().length;

				c = new String[size];
				v = new double[size];

				for (int i = 0; i < size; i++) {

					c[i] = relatorioEfectForm.getRelEfetividadeVO().getGraficoArray(i).getDescricao();
					valor = relatorioEfectForm.getRelEfetividadeVO().getGraficoArray(i).getValor();
					try {
						v[i] = Double.parseDouble(valor);
					} catch (NumberFormatException nfe) {
						v[i] = Double.parseDouble(ConstantesCRM.SZERO);
						log.error(nfe.getMessage());
					}
					urlParam += "&" + c[i] + "=" + v[i];
				}
				nameParam = "&name=RELATÓRIO DE EFETIVIDADE";

				request.setAttribute("graph", graphCallParam + "Graph" + urlParam.replaceAll("\"", "") + nameParam + "&font=" + "10");
				forwardName = "relaEfectiv";
				break;

			case RELATORIO_PERCENTUAL_POR_OPERADOR:
				forwardName = "relaOperador";
				break;

			case RELATORIO_PERCENTUAL_POR_MOTIVOS:
				forwardName = "relaMotivo";
				break;

			case RELATORIO_RESPOSTAS:
				String name = ConstantesCRM.SVAZIO,
				Params;
				// double dVal = 0;
				int length = relaRespostaForm.getRelatorioRespostasVO().getGraficoArray().length;
				int fontSize;

				request.setAttribute("length", ConstantesCRM.SVAZIO + length);

				for (int i = 0; i < length; i++) {
					Params = ConstantesCRM.SVAZIO;
					fontSize = 10;

					for (int a = 0; a < relaRespostaForm.getRelatorioRespostasVO().getGraficoArray(i).getParametrosGraficoVOArray().length; a++) {
						descricao = relaRespostaForm.getRelatorioRespostasVO().getGraficoArray(i).getParametrosGraficoVOArray(a).getDescricao();
						valor = relaRespostaForm.getRelatorioRespostasVO().getGraficoArray(i).getParametrosGraficoVOArray(a).getValor();
						/*
						 * try{ dVal = Double.parseDouble(valor); }catch(NumberFormatException nfe){ dVal =
						 * Double.parseDouble("0"); log.error( nfe.getMessage() ); }
						 */
						Params += "&" + descricao + "=" + valor;
						fontSize = (fontSize == 6 || descricao.length() > 35) ? 6 : 10;
					}

					name = relaRespostaForm.getRelatorioRespostasVO().getGraficoArray(i).getDsPergunta();
					request.setAttribute("graph" + i, graphCallParam + "Graph" + urlParamRespostas.replaceAll("\"", "") + Params + "&name=" + name + "&font=" + fontSize);
				}

				forwardName = "relaResposta";
				break;

			case RELATORIO_AGENDA_ANIVERSARIO:
				forwardName = "relaAgendaAniv";
				break;
			}
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(forwardName);
	}

	private void formataArquivo(StringBuffer sbIn, String sIn) {

		sbIn.append(sIn != null ? sIn : ConstantesCRM.SVAZIO);
		sbIn.append(SEPARADOR);
	}

	/**
	 * @jpf:action
	 */
	protected ActionForward generarArquivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		StringBuffer relaEfeStr = null;
		int idRelatorio = 0;
		try {

			if ((request.getParameter("idRelatorio") != null) && (!request.getParameter("idRelatorio").equals(ConstantesCRM.SVAZIO))) {
				idRelatorio = Integer.parseInt(request.getParameter("idRelatorio"));
			}
			relaEfeStr = new StringBuffer();
			String descriaoRpl = ConstantesCRM.SVAZIO;
			String descricao = ConstantesCRM.SVAZIO;
			java.util.Date hoje = new java.util.Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

			switch (idRelatorio) {
			case RELATORIO_GERENCIAMIENTO:
				if (relGerenciamentoForm.getCampanhaRelatorioVO().getNumericoArray() != null) {

					Numerico[] linhaGerenc = relGerenciamentoForm.getCampanhaRelatorioVO().getNumericoArray();

					relaEfeStr.append("Campanha:  ");
					relaEfeStr.append(getHeaderVO().getSgCampanha());
					relaEfeStr.append("\nSub Campanha:  ");
					relaEfeStr.append(getHeaderVO().getNmSubCampanha());
					relaEfeStr.append("\nPeríodo:  ");
					relaEfeStr.append(getHeaderVO().getFiltroDtInicio());
					relaEfeStr.append("  à  ");
					relaEfeStr.append(getHeaderVO().getFiltroDtFim());
					relaEfeStr.append("\nData da Geração do Arquivo: ");
					relaEfeStr.append(formatter.format(hoje));
					relaEfeStr.append("\n\n");
					relaEfeStr.append("Descricao");
					relaEfeStr.append(SEPARADOR);
					relaEfeStr.append("Valor");
					relaEfeStr.append("\n");
					for (int a = 0; a < linhaGerenc.length; a++) {
						descricao = linhaGerenc[a].getDescricao();
						descriaoRpl = descricao.replaceAll(",", " ");

						relaEfeStr.append(descriaoRpl);
						relaEfeStr.append(SEPARADOR);
						relaEfeStr.append(linhaGerenc[a].getValor());
						relaEfeStr.append("\n");

						descricao = ConstantesCRM.SVAZIO;
						descriaoRpl = ConstantesCRM.SVAZIO;
					}

					response.addHeader("Content-Disposition", "attachment; filename=relatorioGeren.csv");
				}
				break;

			case RELATORIO_EFETIVIDADE:
				if (relatorioEfectForm.getLinha() != null) {

					br.com.vivo.fo.campanha.vo.RelEfetividadeVODocument.RelEfetividadeVO.Numerico[] linhaEfe = relatorioEfectForm.getLinha();

					relaEfeStr.append("Campanha:  ");
					relaEfeStr.append(getHeaderVO().getSgCampanha());
					relaEfeStr.append("\nSub Campanha:  ");
					relaEfeStr.append(getHeaderVO().getNmSubCampanha());
					relaEfeStr.append("\nPeríodo:  ");
					relaEfeStr.append(getHeaderVO().getFiltroDtInicio());
					relaEfeStr.append("  à  ");
					relaEfeStr.append(getHeaderVO().getFiltroDtFim());
					relaEfeStr.append("\nOperador:  ");
					relaEfeStr.append(getHeaderVO().getSgUsuario());
					relaEfeStr.append("\nData da Geração do Arquivo: ");
					relaEfeStr.append(formatter.format(hoje));
					relaEfeStr.append("\n\n");
					relaEfeStr.append("Descricao");
					relaEfeStr.append(SEPARADOR);
					relaEfeStr.append("Valor");
					relaEfeStr.append("\n");
					for (int a = 0; a < linhaEfe.length; a++) {
						descricao = linhaEfe[a].getDescricao();
						descriaoRpl = descricao.replaceAll(",", " ");

						relaEfeStr.append(descriaoRpl);
						relaEfeStr.append(SEPARADOR);
						relaEfeStr.append(linhaEfe[a].getValor());
						relaEfeStr.append("\n");

						descricao = ConstantesCRM.SVAZIO;
						descriaoRpl = ConstantesCRM.SVAZIO;
					}

					response.addHeader("Content-Disposition", "attachment; filename=relatorioEfetividade.csv");
				}
				break;

			case RELATORIO_PERCENTUAL_POR_OPERADOR:
				int index = 0;

				if (relaOperadorForm.getLinha() != null && relaOperadorForm.getNumerico().length > 0) {
					br.com.vivo.fo.campanha.vo.CampanhaRelatorioOperadorVODocument.CampanhaRelatorioOperadorVO.Numerico[] linhaOperador = relaOperadorForm.getNumerico();

					relaEfeStr.append("Campanha:  ");
					relaEfeStr.append(getHeaderVO().getSgCampanha());
					relaEfeStr.append("\nSub Campanha:  ");
					relaEfeStr.append(getHeaderVO().getNmSubCampanha());
					relaEfeStr.append("\nPeríodo:  ");
					relaEfeStr.append(getHeaderVO().getFiltroDtInicio());
					relaEfeStr.append("  à  ");
					relaEfeStr.append(getHeaderVO().getFiltroDtFim());
					relaEfeStr.append("\nOperador:  ");
					relaEfeStr.append(getHeaderVO().getSgUsuario());
					relaEfeStr.append("\nData da Geração do Arquivo: ");
					relaEfeStr.append(formatter.format(hoje));
					relaEfeStr.append("\n\n");
					relaEfeStr.append("Operador");
					relaEfeStr.append(SEPARADOR);
					relaEfeStr.append("Publico Total");
					relaEfeStr.append(SEPARADOR);
					relaEfeStr.append("Meta Diaria");
					relaEfeStr.append(SEPARADOR);
					relaEfeStr.append("Contatos Efetivos");
					relaEfeStr.append(SEPARADOR);
					relaEfeStr.append("Tempo Médio Operador(seg)");
					relaEfeStr.append(SEPARADOR);
					relaEfeStr.append("Motivos");
					relaEfeStr.append(SEPARADOR);
					relaEfeStr.append("Qtd.");
					relaEfeStr.append(SEPARADOR);
					relaEfeStr.append("Não Adesões");
					relaEfeStr.append(SEPARADOR);
					relaEfeStr.append("Reagendados");
					relaEfeStr.append(SEPARADOR);
					relaEfeStr.append("Adesões");
					relaEfeStr.append(SEPARADOR);
					relaEfeStr.append("Submotivo Oferta");
					relaEfeStr.append(SEPARADOR);
					relaEfeStr.append("Qtd.");
					relaEfeStr.append("\n");

					// monta linhas
					for (int a = 0; a < linhaOperador.length; a++) {
						this.formataArquivo(relaEfeStr, linhaOperador[a].getOperador());
						this.formataArquivo(relaEfeStr, linhaOperador[a].getPublicoTotal());
						this.formataArquivo(relaEfeStr, linhaOperador[a].getMetaDiaria());
						this.formataArquivo(relaEfeStr, linhaOperador[a].getContatosEfetivos());
						this.formataArquivo(relaEfeStr, linhaOperador[a].getTmo());

						if (linhaOperador[a].getMotivos() != null && linhaOperador[a].getMotivos().getDsMotivoArray().length > 0) {
							this.formataArquivo(relaEfeStr, linhaOperador[a].getMotivos().getDsMotivoArray(0));
						} else {
							this.formataArquivo(relaEfeStr, ConstantesCRM.SVAZIO);
						}

						if (linhaOperador[a].getMotivos() != null && linhaOperador[a].getMotivos().getQtdMotivoArray().length > 0) {
							this.formataArquivo(relaEfeStr, linhaOperador[a].getMotivos().getQtdMotivoArray(0));
						} else {
							this.formataArquivo(relaEfeStr, ConstantesCRM.SVAZIO);
						}

						this.formataArquivo(relaEfeStr, linhaOperador[a].getNaoAdesoes());
						this.formataArquivo(relaEfeStr, linhaOperador[a].getReagendados());
						this.formataArquivo(relaEfeStr, linhaOperador[a].getAdesoes());

						if (linhaOperador[a].getOfertas() != null && linhaOperador[a].getOfertas().getDsOfertaArray().length > 0) {
							this.formataArquivo(relaEfeStr, linhaOperador[a].getOfertas().getDsOfertaArray(0));
						} else {
							this.formataArquivo(relaEfeStr, ConstantesCRM.SVAZIO);
						}

						if (linhaOperador[a].getOfertas() != null && linhaOperador[a].getOfertas().getQtdOfertaArray().length > 0) {
							this.formataArquivo(relaEfeStr, linhaOperador[a].getOfertas().getQtdOfertaArray(0));
						} else {
							this.formataArquivo(relaEfeStr, ConstantesCRM.SVAZIO);
						}

						relaEfeStr.append("\n");

						if (linhaOperador[a].getMotivos().getDsMotivoArray().length > linhaOperador[a].getOfertas().getDsOfertaArray().length) {
							index = linhaOperador[a].getMotivos().getDsMotivoArray().length;
						} else {
							index = linhaOperador[a].getOfertas().getDsOfertaArray().length;
						}

						// monta array de colunas
						for (int o = 1; o < index; o++) {
							// campos vazios
							for (int p = 0; p < 5; p++) {
								this.formataArquivo(relaEfeStr, ConstantesCRM.SVAZIO);
							}

							if (linhaOperador[a].getMotivos().getDsMotivoArray().length > o) {
								this.formataArquivo(relaEfeStr, linhaOperador[a].getMotivos().getDsMotivoArray(o));
								this.formataArquivo(relaEfeStr, linhaOperador[a].getMotivos().getQtdMotivoArray(o));
							} else {
								this.formataArquivo(relaEfeStr, ConstantesCRM.SVAZIO);
								this.formataArquivo(relaEfeStr, ConstantesCRM.SVAZIO);
							}

							// campos vazios
							for (int p = 0; p < 3; p++) {
								this.formataArquivo(relaEfeStr, ConstantesCRM.SVAZIO);
							}

							if (linhaOperador[a].getOfertas().getDsOfertaArray().length > o) {
								this.formataArquivo(relaEfeStr, linhaOperador[a].getOfertas().getDsOfertaArray(o));
								this.formataArquivo(relaEfeStr, linhaOperador[a].getOfertas().getQtdOfertaArray(o));
							} else {
								this.formataArquivo(relaEfeStr, ConstantesCRM.SVAZIO);
								this.formataArquivo(relaEfeStr, ConstantesCRM.SVAZIO);
							}
							relaEfeStr.append("\n");
						}
					}
					response.addHeader("Content-Disposition", "attachment; filename=relatorioPercOperador.csv");
				}
				break;

			case RELATORIO_PERCENTUAL_POR_MOTIVOS:
				if (relatorioMotivoForm.getRelatorioPercentualVO() != null) {

					RelatorioPercentualVO.Motivo.Numerico[] linhaPerc = relatorioMotivoForm.getRelatorioPercentualVO().getNumericoArray();

					relaEfeStr.append("Campanha:  ");
					relaEfeStr.append(getHeaderVO().getSgCampanha());
					relaEfeStr.append("\nSub Campanha:  ");
					relaEfeStr.append(getHeaderVO().getNmSubCampanha());
					relaEfeStr.append("\nPeríodo:  ");
					relaEfeStr.append(getHeaderVO().getFiltroDtInicio());
					relaEfeStr.append("  à  ");
					relaEfeStr.append(getHeaderVO().getFiltroDtFim());
					relaEfeStr.append("\nData da Geração do Arquivo: ");
					relaEfeStr.append(formatter.format(hoje));
					relaEfeStr.append("\n\n");
					relaEfeStr.append("Motivo");
					relaEfeStr.append(SEPARADOR);
					relaEfeStr.append("Sub Motivo");
					relaEfeStr.append(SEPARADOR);
					relaEfeStr.append("Nro Atendimento");
					relaEfeStr.append(SEPARADOR);
					relaEfeStr.append("Porcentagem");
					relaEfeStr.append("\n");
					for (int a = 0; a < linhaPerc.length; a++) {
						descricao = linhaPerc[a].getSgTipoMotivoCampanha();
						descriaoRpl = descricao.replaceAll(",", " ");
						relaEfeStr.append(descriaoRpl);
						relaEfeStr.append(SEPARADOR);

						descricao = ConstantesCRM.SVAZIO;
						descriaoRpl = ConstantesCRM.SVAZIO;
						descricao = linhaPerc[a].getSgTipoSubMotivoCampanha();
						descriaoRpl = descricao.replaceAll(",", " ");

						relaEfeStr.append(descriaoRpl);
						relaEfeStr.append(SEPARADOR);
						relaEfeStr.append(linhaPerc[a].getNroAtendimento());
						relaEfeStr.append(SEPARADOR);
						relaEfeStr.append(linhaPerc[a].getPorcent());
						relaEfeStr.append("\n");

						descricao = ConstantesCRM.SVAZIO;
						descriaoRpl = ConstantesCRM.SVAZIO;
					}
					response.addHeader("Content-Disposition", "attachment; filename=relatorioPercMotivos.csv");
				}
				break;

			case RELATORIO_RESPOSTAS:
				if (relaRespostaForm.getRelatorioRespostasVO() != null) {

					RelatorioRespostasVO.Perguntas.Numerico[] linhaResp = relaRespostaForm.getRelatorioRespostasVO().getNumericoArray();
					relaEfeStr.append("Campanha:  ");
					relaEfeStr.append(getHeaderVO().getSgCampanha());
					relaEfeStr.append("\nSub Campanha:  ");
					relaEfeStr.append(getHeaderVO().getNmSubCampanha());
					relaEfeStr.append("\nPeríodo:  ");
					relaEfeStr.append(getHeaderVO().getFiltroDtInicio());
					relaEfeStr.append("  à  ");
					relaEfeStr.append(getHeaderVO().getFiltroDtFim());
					relaEfeStr.append("\nData da Geração do Arquivo: ");
					relaEfeStr.append(formatter.format(hoje));
					relaEfeStr.append("\n\n");
					relaEfeStr.append("Pergunta");
					relaEfeStr.append(",");
					relaEfeStr.append("Resposta");
					relaEfeStr.append(",");
					relaEfeStr.append("Nro Atendimento");
					relaEfeStr.append(",");
					relaEfeStr.append("Porcentagem");
					relaEfeStr.append("\n");
					for (int a = 0; a < linhaResp.length; a++) {
						descricao = linhaResp[a].getDsPergunta();
						descriaoRpl = descricao.replaceAll(",", " ");

						relaEfeStr.append(descriaoRpl);
						relaEfeStr.append(SEPARADOR);

						descricao = ConstantesCRM.SVAZIO;
						descriaoRpl = ConstantesCRM.SVAZIO;

						descricao = linhaResp[a].getDsResposta();
						descriaoRpl = descricao.replaceAll(",", " ");

						relaEfeStr.append(descriaoRpl);
						relaEfeStr.append(SEPARADOR);
						relaEfeStr.append(linhaResp[a].getNroAtendimento());
						relaEfeStr.append(SEPARADOR);
						relaEfeStr.append(linhaResp[a].getPorcent());
						relaEfeStr.append("\n");

						descricao = ConstantesCRM.SVAZIO;
						descriaoRpl = ConstantesCRM.SVAZIO;
					}
					response.addHeader("Content-Disposition", "attachment; filename=relatorioRespostas.csv");
				}
				break;

			case RELATORIO_AGENDA_ANIVERSARIO:
				if (relaAgendaAnivForm.getLinhaAniversario() != null) {

					br.com.vivo.fo.campanha.vo.RelaAgendaAniversarioVODocument.RelaAgendaAniversarioVO.LinhaAniversario[] linhaResp = relaAgendaAnivForm.getLinhaAniversario();

					relaEfeStr.append("Período:  ");
					relaEfeStr.append(getHeaderVO().getFiltroDtInicio());
					relaEfeStr.append("  à  ");
					relaEfeStr.append(getHeaderVO().getFiltroDtFim());
					relaEfeStr.append("\nData da Geração do Arquivo: ");
					relaEfeStr.append(formatter.format(hoje));
					relaEfeStr.append("\n");
					relaEfeStr.append("Linha");
					relaEfeStr.append(SEPARADOR);
					relaEfeStr.append("Data Aniversario");
					relaEfeStr.append(SEPARADOR);
					relaEfeStr.append("Aniversariante");
					relaEfeStr.append(SEPARADOR);
					relaEfeStr.append("Operador");
					relaEfeStr.append(SEPARADOR);
					relaEfeStr.append("Data Atendimento");
					relaEfeStr.append(SEPARADOR);
					relaEfeStr.append("Periodo Dia");
					relaEfeStr.append("\n");
					for (int a = 0; a < linhaResp.length; a++) {
						relaEfeStr.append(linhaResp[a].getLinha());
						relaEfeStr.append(SEPARADOR);
						relaEfeStr.append(linhaResp[a].getDataAniversario());
						relaEfeStr.append(SEPARADOR);
						relaEfeStr.append(linhaResp[a].getAniversariante());
						relaEfeStr.append(SEPARADOR);
						relaEfeStr.append(linhaResp[a].getOperador());
						relaEfeStr.append(SEPARADOR);
						relaEfeStr.append(linhaResp[a].getDataAtendimento());
						relaEfeStr.append(SEPARADOR);
						relaEfeStr.append(linhaResp[a].getPeriodoDia() != null ? linhaResp[a].getPeriodoDia() : "");
						relaEfeStr.append("\n");
					}
					response.addHeader("Content-Disposition", "attachment; filename=relatorioAgendaAniversario.csv");
				}
				break;
			}

			response.addHeader("Content-Type", "application/force-download");
			response.addHeader("Content-Transfer-Encoding", "binary");
			response.addHeader("Pragma", "no-cache");
			response.addHeader("Expires", "0");

			PrintWriter out = response.getWriter();
			out.println(relaEfeStr.toString());
			out.flush();
			out.close();

		} catch (Exception e) {
			log.error("=========================================================", e);
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		return null;
	}

	// FORM
	public static class RelEfetividadeActionForm extends ActionForm {

		private static final long serialVersionUID = 2766799021369692922L;
		private String areaRegistroSelecionada = ConstantesCRM.SVAZIO;
		private AreaRegistro[] listaAreaRegistro;
		private String totalReg;
		private String nmSubCampanha;
		private String sgCampanha;
		private String dataAniversario;
		private String linha;
		private String respostaSeleccionada;
		private String distincaoSelecionada;
		// private CampanhaPerguntaVO[] listaResposta;
		private String dtFim;
		private String dtInicio;
		private String sgOperador;
		private String nmOperador;
		private ApoioVO listaCampanha[] = new ApoioVO[0];
		private String campanhaSelecionada = ConstantesCRM.SVAZIO;
		private ApoioVO listaSubCampanha[] = new ApoioVO[0];
		private String subcampanhaSelecionada = ConstantesCRM.SVAZIO;
		private ApoioVO listaCanal[] = new ApoioVO[0];
		private String canalSelecionado = ConstantesCRM.SVAZIO;
		private ApoioVO listaVersao[] = new ApoioVO[0];
		private String versaoSelecionada = ConstantesCRM.SVAZIO;
		private ApoioVO listaPerfil[] = new ApoioVO[0];
		private String perfilSelecionado = ConstantesCRM.SVAZIO;
		private UFOperadoraUsuarioVO[] listaRegional = new UFOperadoraUsuarioVO[0];
		private String regionalSelecionada = ConstantesCRM.SVAZIO;
		private ApoioVO[] listaMotivo = new ApoioVO[0];
		private String motivoSelecionado = ConstantesCRM.SVAZIO;
		private CampanhaPerguntaVO[] listaPergunta = new CampanhaPerguntaVO[0];
		private String perguntaSelecionada = ConstantesCRM.SVAZIO;
		private HashMap listaDistincao;
		private CampanhaFiltroRelatorioVO campanhaFiltroRelatorioFormVO = CampanhaFiltroRelatorioVO.Factory.newInstance();

		public void setCampanhaFiltroRelatorioVO(CampanhaFiltroRelatorioVO campanhaRelatorioBD) {

			this.campanhaFiltroRelatorioFormVO = campanhaRelatorioBD;
		}

		public CampanhaFiltroRelatorioVO getCampanhaFiltroRelatorioVO() {

			if (campanhaFiltroRelatorioFormVO != null) {
				campanhaFiltroRelatorioFormVO.setDtInicio(this.dtInicio);
				campanhaFiltroRelatorioFormVO.setDtFim(this.dtFim);
				campanhaFiltroRelatorioFormVO.setIdCampanha(this.campanhaSelecionada);
				campanhaFiltroRelatorioFormVO.setIdSubCampanha(this.versaoSelecionada);
				campanhaFiltroRelatorioFormVO.setIdCanalCampanha(this.canalSelecionado);
				campanhaFiltroRelatorioFormVO.setIdGrupo(this.perfilSelecionado);
				campanhaFiltroRelatorioFormVO.setIdRegional(this.regionalSelecionada);
				campanhaFiltroRelatorioFormVO.setIdOperador(ConstantesCRM.SVAZIO);
				campanhaFiltroRelatorioFormVO.setSgOperador(this.sgOperador);
				campanhaFiltroRelatorioFormVO.setIdMotivo(this.motivoSelecionado);
				campanhaFiltroRelatorioFormVO.setIdPergunta(this.perguntaSelecionada);
				campanhaFiltroRelatorioFormVO.setIdAreaRegistro(this.areaRegistroSelecionada);
				campanhaFiltroRelatorioFormVO.setDataAniversario(this.dataAniversario);
				String newLinha = this.linha;
				if ((linha != null) && (!linha.equals(ConstantesCRM.SVAZIO)) && (linha.length() >= 10)) {
					newLinha = linha.substring(1, 3);
					newLinha += linha.substring(4);
					newLinha = newLinha.replaceAll("-", ConstantesCRM.SVAZIO);
				}
				campanhaFiltroRelatorioFormVO.setLinha(newLinha);
				campanhaFiltroRelatorioFormVO.setInDistincao(this.distincaoSelecionada);
			}
			return this.campanhaFiltroRelatorioFormVO;
		}

		public CampanhaFiltroRelatorioVO getCampanhaFiltroRelatorioVO2() {

			if (campanhaFiltroRelatorioFormVO != null) {
				campanhaFiltroRelatorioFormVO.setDtInicio(this.dtInicio);
				campanhaFiltroRelatorioFormVO.setDtFim(this.dtFim);
				campanhaFiltroRelatorioFormVO.setIdCampanha(this.campanhaSelecionada);
				campanhaFiltroRelatorioFormVO.setIdSubCampanha(this.versaoSelecionada);
				campanhaFiltroRelatorioFormVO.setIdCanalCampanha(this.canalSelecionado);
				campanhaFiltroRelatorioFormVO.setIdGrupo(this.perfilSelecionado);
				campanhaFiltroRelatorioFormVO.setIdRegional(this.regionalSelecionada);
				campanhaFiltroRelatorioFormVO.setIdOperador(ConstantesCRM.SVAZIO);
				campanhaFiltroRelatorioFormVO.setSgOperador(ConstantesCRM.SVAZIO);
				campanhaFiltroRelatorioFormVO.setNmOperador(this.sgOperador);
				campanhaFiltroRelatorioFormVO.setIdMotivo(this.motivoSelecionado);
				campanhaFiltroRelatorioFormVO.setIdPergunta(this.perguntaSelecionada);
				campanhaFiltroRelatorioFormVO.setIdAreaRegistro(this.areaRegistroSelecionada);
				campanhaFiltroRelatorioFormVO.setDataAniversario(this.dataAniversario);
				String newLinha = this.linha;
				if ((linha != null) && (!linha.equals(ConstantesCRM.SVAZIO)) && (linha.length() >= 10)) {
					newLinha = linha.substring(1, 3);
					newLinha += linha.substring(4);
					newLinha = newLinha.replaceAll("-", ConstantesCRM.SVAZIO);
				}
				campanhaFiltroRelatorioFormVO.setLinha(newLinha);
				campanhaFiltroRelatorioFormVO.setInDistincao(this.distincaoSelecionada);
			}
			return this.campanhaFiltroRelatorioFormVO;
		}

		public ApoioVO[] getListaCampanha() {

			return this.listaCampanha;
		}

		public void setListaCampanha(ApoioVO[] sLista) {

			this.listaCampanha = sLista;
		}

		public String getCampanhaSelecionada() {

			return campanhaSelecionada;
		}

		public void setCampanhaSelecionada(String s) {

			this.campanhaSelecionada = s;
		}

		public ApoioVO[] getListaSubCampanha() {

			return this.listaSubCampanha;
		}

		public void setListaSubCampanha(ApoioVO[] sLista) {

			this.listaSubCampanha = sLista;
		}

		public String getSubcampanhaSelecionada() {

			return subcampanhaSelecionada;
		}

		public void setSubcampanhaSelecionada(String s) {

			this.subcampanhaSelecionada = s;
		}

		public ApoioVO[] getListaCanal() {

			return this.listaCanal;
		}

		public void setListaCanal(ApoioVO[] sLista) {

			this.listaCanal = sLista;
		}

		public String getCanalSelecionado() {

			return canalSelecionado;
		}

		public void setCanalSelecionado(String s) {

			this.canalSelecionado = s;
		}

		public ApoioVO[] getListaVersao() {

			return this.listaVersao;
		}

		public void setListaVersao(ApoioVO[] sLista) {

			this.listaVersao = sLista;
		}

		public String getVersaoSelecionada() {

			return versaoSelecionada;
		}

		public void setVersaoSelecionada(String s) {

			this.versaoSelecionada = s;
		}

		public ApoioVO[] getListaPerfil() {

			return this.listaPerfil;
		}

		public void setListaPerfil(ApoioVO[] sLista) {

			this.listaPerfil = sLista;
		}

		public String getPerfilSelecionado() {

			return perfilSelecionado;
		}

		public void setPerfilSelecionado(String s) {

			this.perfilSelecionado = s;
		}

		public UFOperadoraUsuarioVO[] getListaRegional() {

			return this.listaRegional;
		}

		public void setListaRegional(UFOperadoraUsuarioVO[] sLista) {

			this.listaRegional = sLista;
		}

		public String getRegionalSelecionada() {

			return regionalSelecionada;
		}

		public void setRegionalSelecionada(String s) {

			this.regionalSelecionada = s;
		}

		public ApoioVO[] getListaMotivo() {

			return this.listaMotivo;
		}

		public void setListaMotivo(ApoioVO[] sLista) {

			this.listaMotivo = sLista;
		}

		public String getMotivoSelecionado() {

			return motivoSelecionado;
		}

		public void setMotivoSelecionado(String s) {

			this.motivoSelecionado = s;
		}

		public CampanhaPerguntaVO[] getListaPergunta() {

			return this.listaPergunta;
		}

		public void setListaPergunta(CampanhaPerguntaVO[] sLista) {

			this.listaPergunta = sLista;
		}

		public String getPerguntaSelecionada() {

			return perguntaSelecionada;
		}

		public void setPerguntaSelecionada(String s) {
			this.perguntaSelecionada = s;
		}

		public HashMap getListaDistincao() {

			this.listaDistincao = new HashMap();
			this.listaDistincao.put(new Integer(1), "sim");
			this.listaDistincao.put(new Integer(0), "não");
			return this.listaDistincao;
		}

		public void setListaDistincao(HashMap _listaDistincao) {

			this.listaDistincao = _listaDistincao;
		}

		public String getDistincaoSelecionada() {

			return distincaoSelecionada;
		}

		public void setDistincaoSelecionada(String s) {

			this.distincaoSelecionada = s;
		}

		public void setDtInicio(String dtInicio) {

			this.dtInicio = dtInicio;
		}

		public String getDtInicio() {

			return this.dtInicio;
		}

		public void setDtFim(String dtFim) {

			this.dtFim = dtFim;
		}

		public String getDtFim() {

			return this.dtFim;
		}

		public void setNmOperador(String s) {

			this.nmOperador = s;
		}

		public String getNmOperador() {

			return this.nmOperador;
		}

		public void setSgOperador(String s) {

			this.sgOperador = s;
		}

		public String getSgOperador() {

			return this.sgOperador;
		}

		public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
			ActionErrors errs = new ActionErrors();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
			if ((dtInicio != null) && (!dtInicio.equals(ConstantesCRM.SVAZIO))) {
				try {
					formatter.parse(dtInicio);
				} catch (Exception e) {
					errs.add("dtInicio", new ActionError("badDateFormat"));
				}

			}

			if ((dtFim != null) && (!dtFim.equals(ConstantesCRM.SVAZIO))) {
				try {
					formatter.parse(dtFim);
				} catch (Exception e) {
					errs.add("dtFim", new ActionError("badDateFormat"));
				}

			}
			return errs;
		}

		public void setRespostaSeleccionada(String respostaSeleccionada) {

			this.respostaSeleccionada = respostaSeleccionada;
		}

		public String getRespostaSeleccionada() {

			return this.respostaSeleccionada;
		}

		public void setLinha(String linha) {

			this.linha = linha;
		}

		public String getLinha() {

			return this.linha;
		}

		public void setDataAniversario(String dataAniversario) {

			this.dataAniversario = dataAniversario;
		}

		public String getDataAniversario() {

			return this.dataAniversario;
		}

		public void setSgCampanha(String sgCampanha) {

			this.sgCampanha = sgCampanha;
		}

		public String getSgCampanha() {

			return this.sgCampanha;
		}

		public void setNmSubCampanha(String nmSubCampanha) {

			this.nmSubCampanha = nmSubCampanha;
		}

		public String getNmSubCampanha() {

			return this.nmSubCampanha;
		}

		public void setTotalReg(String totalReg) {

			this.totalReg = totalReg;
		}

		public String getTotalReg() {

			return this.totalReg;
		}

		public void setListaAreaRegistro(AreaRegistro[] listaAreaRegistro) {

			this.listaAreaRegistro = listaAreaRegistro;
		}

		public AreaRegistro[] getListaAreaRegistro() {

			if (this.listaAreaRegistro == null) {
				this.listaAreaRegistro = new AreaRegistro[0];
			}
			return this.listaAreaRegistro;
		}

		public void setAreaRegistroSelecionada(String areaRegistroSelecionada) {

			this.areaRegistroSelecionada = areaRegistroSelecionada;
		}

		public String getAreaRegistroSelecionada() {

			return this.areaRegistroSelecionada;
		}
	}

	// FORM
	public static class RelatorioMotivoForm extends ActionForm {

		private static final long serialVersionUID = 8110015862702077501L;
		private RelatorioPercentualVO.Motivo relatorioPercentualVO;

		public void setRelatorioPercentualVO(RelatorioPercentualVO relPercVO) {

			this.relatorioPercentualVO = relPercVO.getMotivoArray(0);
		}

		public RelatorioPercentualVO.Motivo getRelatorioPercentualVO() {

			if (relatorioPercentualVO == null) {
				relatorioPercentualVO = RelatorioPercentualVO.Motivo.Factory.newInstance();
			}
			return this.relatorioPercentualVO;
		}
	}

	// FORM
	public static class RelatorioEfectForm extends ActionForm {

		private static final long serialVersionUID = -197923725191432164L;
		private RelEfetividadeVO.Numerico[] linha;
		// private RelEfetividadeVO.Numerico[] linhaEfe;
		private RelEfetividadeVO relEfetividadeVO;

		public void setRelEfetividadeVO(RelEfetividadeVO relEfetividadeVO) {

			this.relEfetividadeVO = relEfetividadeVO;
		}

		public RelEfetividadeVO getRelEfetividadeVO() {

			return this.relEfetividadeVO;
		}

		public void setLinha(RelEfetividadeVO.Numerico[] linha) {

			this.linha = linha;
		}

		public RelEfetividadeVO.Numerico[] getLinha() {

			return this.linha;
		}
	}

	// FORM
	public static class RelGerenciamentoForm extends ActionForm {

		private static final long serialVersionUID = -4828009940060472831L;
		private br.com.vivo.fo.campanha.vo.CampanhaRelatorioVODocument.CampanhaRelatorioVO campanhaRelatorioVO;

		public void setCampanhaRelatorioVO(CampanhaRelatorioVO campanhaRelatorioVO) {

			this.campanhaRelatorioVO = campanhaRelatorioVO;
		}

		public CampanhaRelatorioVO getCampanhaRelatorioVO() {

			if (this.campanhaRelatorioVO == null) {
				this.campanhaRelatorioVO = CampanhaRelatorioVO.Factory.newInstance();
			}
			return this.campanhaRelatorioVO;
		}
	}

	// FORM
	public static class RelaOperadorForm extends ActionForm {

		private static final long serialVersionUID = -7877819559840166817L;
		private br.com.vivo.fo.campanha.vo.CampanhaRelatorioOperadorVODocument.CampanhaRelatorioOperadorVO.Numerico[] numerico;
		private br.com.vivo.fo.campanha.vo.CampanhaRelatorioVODocument.CampanhaRelatorioVO.Linha[] linha;

		public void setNumerico(br.com.vivo.fo.campanha.vo.CampanhaRelatorioOperadorVODocument.CampanhaRelatorioOperadorVO.Numerico[] numerico) {

			this.numerico = numerico;
		}

		public br.com.vivo.fo.campanha.vo.CampanhaRelatorioOperadorVODocument.CampanhaRelatorioOperadorVO.Numerico[] getNumerico() {

			if (this.numerico == null) {
				this.numerico = new br.com.vivo.fo.campanha.vo.CampanhaRelatorioOperadorVODocument.CampanhaRelatorioOperadorVO.Numerico[0];
			}
			return this.numerico;
		}

		public void setLinha(br.com.vivo.fo.campanha.vo.CampanhaRelatorioVODocument.CampanhaRelatorioVO.Linha[] linha) {

			this.linha = linha;
		}

		public br.com.vivo.fo.campanha.vo.CampanhaRelatorioVODocument.CampanhaRelatorioVO.Linha[] getLinha() {

			if (this.linha == null) {
				this.linha = new Linha[0];
			}
			return this.linha;
		}
	}

	// FORM
	public static class RelaRespostaForm extends ActionForm {

		private static final long serialVersionUID = -2779671715151217369L;
		private RelatorioRespostasVO.Perguntas relatorioRespostasVO;

		public void setRelatorioRespostasVO(RelatorioRespostasVO.Perguntas relatorioRespostasVO) {

			this.relatorioRespostasVO = relatorioRespostasVO;
		}

		public RelatorioRespostasVO.Perguntas getRelatorioRespostasVO() {

			if (this.relatorioRespostasVO == null) {
				this.relatorioRespostasVO = RelatorioRespostasVO.Perguntas.Factory.newInstance();
			}
			return this.relatorioRespostasVO;
		}
	}

	// FORM
	public static class RelaAgendaAnivForm extends ActionForm {

		private static final long serialVersionUID = -1731784962512862387L;
		// private String msgErro;
		private br.com.vivo.fo.campanha.vo.RelaAgendaAniversarioVODocument.RelaAgendaAniversarioVO.LinhaAniversario[] linhaAniversario;

		public void setLinhaAniversario(br.com.vivo.fo.campanha.vo.RelaAgendaAniversarioVODocument.RelaAgendaAniversarioVO.LinhaAniversario[] linhaAniversario) {

			this.linhaAniversario = linhaAniversario;
		}

		public br.com.vivo.fo.campanha.vo.RelaAgendaAniversarioVODocument.RelaAgendaAniversarioVO.LinhaAniversario[] getLinhaAniversario() {

			return this.linhaAniversario;
		}
	}
}