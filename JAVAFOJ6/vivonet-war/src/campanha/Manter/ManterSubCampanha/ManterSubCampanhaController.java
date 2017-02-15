package campanha.Manter.ManterSubCampanha;

import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import br.com.vivo.fo.campanha.vo.ApoioCanalVODocument.ApoioCanalVO;
import br.com.vivo.fo.campanha.vo.ApoioPerfilVODocument.ApoioPerfilVO;
import br.com.vivo.fo.campanha.vo.ApoioVODocument.ApoioVO;
import br.com.vivo.fo.campanha.vo.CampanhaParametrosVODocument.CampanhaParametrosVO;
import br.com.vivo.fo.campanha.vo.GrupoCampanhaApoioVODocument.GrupoCampanhaApoioVO;
import br.com.vivo.fo.campanha.vo.SubCampanhaVODocument.SubCampanhaVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.retornotux.vo.RetornoCampanhaVODocument.RetornoCampanhaVO;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;
import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

@SuppressWarnings({"rawtypes","unchecked"})
public class ManterSubCampanhaController extends AbstractAction {

	private static final long serialVersionUID = -4183547896889054667L;

	@EJB
	private br.com.vivo.fo.ctrls.campanha.manter.ApoioFacade apoioFacade;

	@EJB
	private br.com.vivo.fo.ctrls.campanha.manter.CampanhaFacade campanhaFacade;

	@EJB
	private br.com.vivo.fo.ctrls.campanha.copia.CopiaPerguntasFacade copiaFacade;

	// private SubCampanhaVO subCampanha = null;
	private CampanhaParametrosVO paramCampanha = null;

	// private static transient Logger log = new Logger("campanha");
	// private ListaCampanhaPerguntaVO listaPergunta = null;

	private String user;
	private ManterCampanhaActionForm manterCampanhaActionForm;
	private ParametrosSubCampanhaActionForm parametrosSubCampanhaActionForm = new ParametrosSubCampanhaActionForm();
	private CopiaPerguntasActionForm copiaPerguntasActionForm = new CopiaPerguntasActionForm();

	private String idCampanha = ConstantesCRM.SZERO;
	private String idSubCampanha = ConstantesCRM.SZERO;
	private String idSubCampanhaFixa = ConstantesCRM.SZERO;
	private String dsCampanha = ConstantesCRM.SVAZIO;

	public ManterCampanhaActionForm getManterCampanhaActionForm() {
		if (this.manterCampanhaActionForm == null) {
			this.manterCampanhaActionForm = new ManterCampanhaActionForm();
		}
		return this.manterCampanhaActionForm;
	}

	private String getUser(HttpServletRequest request) {
		if (this.user == null) {
			this.user = ConstantesCRM.getCurrentUser(request.getSession());
		}
		return this.user;
	}

	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("ParametrosSubCampanhaAction".equals(mapping.getParameter())) {
			return ParametrosSubCampanhaAction(mapping, form, request, response);
		} else if ("pesquisarParametrosSubCampanha".equals(mapping.getParameter())) {
			return pesquisarParametrosSubCampanha(mapping, form, request, response);
		} else if ("salvarParametrosSubCampanha".equals(mapping.getParameter())) {
			return salvarParametrosSubCampanha(mapping, form, request, response);
		} else if ("ManterSubCampanhaAction".equals(mapping.getParameter())) {
			return ManterSubCampanhaAction(mapping, form, request, response);
		} else if ("manterSubCampanhaActionAlterar".equals(mapping.getParameter())) {
			return manterSubCampanhaActionAlterar(mapping, form, request, response);
		} else if ("CopiaPerguntasAction".equals(mapping.getParameter())) {
			return CopiaPerguntasAction(mapping, form, request, response);
		} else if ("copiaPerguntasSalvar".equals(mapping.getParameter())) {
			return copiaPerguntasSalvar(mapping, form, request, response);
		} else if ("obtemVersoes".equals(mapping.getParameter())) {
			return obtemVersoes(mapping, form, request, response);
		} else if ("obtemCanais".equals(mapping.getParameter())) {
			return obtemCanais(mapping, form, request, response);
		} else if ("obtemSubCampanha".equals(mapping.getParameter())) {
			return obtemSubCampanha(mapping, form, request, response);
		} else if ("ProcessaSubCampanhaAction".equals(mapping.getParameter())) {
			return ProcessaSubCampanhaAction(mapping, form, request, response);
		} else if ("voltarAction".equals(mapping.getParameter())) {
			return voltarAction(mapping, form, request, response);
		} else if ("criarVersao".equals(mapping.getParameter())) {
			return criarVersao(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="copia" path="CopiaPerguntasAction.do"
	 * @jpf:forward name="parametros" path="ParametrosSubCampanhaAction.do"
	 * @jpf:forward name="subcampanha" path="ManterSubCampanhaAction.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String sdestino = "subcampanha";
		try {
			loadPage(request);

			if (request.getParameter("page") != null && !ConstantesCRM.SVAZIO.equals(request.getParameter("page"))) {
				sdestino = request.getParameter("page");
			}
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, "/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(sdestino);
	}

	private void loadPage(HttpServletRequest request) throws Exception {
		try {

			String s = request.getParameter("idCampanha");
			String s1 = request.getParameter("idSubCampanha");
			dsCampanha = request.getParameter("dsCampanha");

			idCampanha = s;

			if (!"incluir".equals(request.getParameter(ConstantesCRM.SACTION))) {
				idSubCampanha = s1;
			}

			idSubCampanhaFixa = idCampanha;
		} catch (Exception e) {
			throw new Exception("Código campanha e sub campanha inválidos", e);
		}

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ParametrizaSubCampanha.jsp"
	 * @jpf:forward name="fechar" path="/campanha/Manter/ManterArvoreConfig/fechaFrameSubCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward ParametrosSubCampanhaAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ParametrosSubCampanhaActionForm form = (ParametrosSubCampanhaActionForm) formParam;

		try {

			form.setiIdCampanha(idCampanha);
			form.setiIdSubCampanha(idSubCampanha);
			// Buscando os canais da subCampanha

			form.setListaCanalDisp(apoioFacade.getApoioCanalCampanha(getUser(request), form.getiIdSubCampanha(), 0).getSubGrupoApoioVOArray(0).getApoioVOArray());
			form.formLoad();

			parametrosSubCampanhaActionForm = form;
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, "/begin.do");
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
	 * @jpf:forward name="success" path="ParametrizaSubCampanha.jsp"
	 * @jpf:forward name="fechar" path="/campanha/Manter/ManterArvoreConfig/fechaFrameSubCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward pesquisarParametrosSubCampanha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ParametrosSubCampanhaActionForm form = (ParametrosSubCampanhaActionForm) formParam;
		try {
			form.setiIdCampanha(idCampanha);
			form.setiIdSubCampanha(idSubCampanha);

			/********************************* PESQUISA ********************************************************************************************/
			paramCampanha = campanhaFacade.getParametrosSubCampanha(getUser(request), form.getCanalDispSelecionado());
			form.setParamCampanhaForm(paramCampanha);
			form.formLoad();
			form.setiAcao(new Integer(1)); // Indicar Alteração
			form.setListaCanalDisp(parametrosSubCampanhaActionForm.getListaCanalDisp());
			// form.setCanalDispSelecionado(canalDisp);
			parametrosSubCampanhaActionForm = form;

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/campanha/Manter/ManterSubCampanha/ParametrosSubCampanhaAction");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute("showPanel", ConstantesCRM.SONE);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ParametrizaSubCampanha.jsp"
	 * @jpf:forward name="fechar" path="/campanha/Manter/ManterArvoreConfig/fechaFrameSubCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward salvarParametrosSubCampanha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			ParametrosSubCampanhaActionForm form = (ParametrosSubCampanhaActionForm) formParam;

			form.setListaCanalDisp(parametrosSubCampanhaActionForm.getListaCanalDisp());
			form.setiIdCampanha(idCampanha);
			form.setiIdSubCampanha(idSubCampanha);

			if (form.getiAcao().intValue() == 0) {

				campanhaFacade.addParametrosCampanha(getUser(request), form.getParamCampanhaForm());
				form.setOperacao("incluir");
			} else if (form.getiAcao().intValue() == 1) {
				/********************************* ALTERAÇÃO ********************************************************************************************/
				campanhaFacade.setParametrosSubCampanha(getUser(request), form.getParamCampanhaForm());
				form.setOperacao("alterar");
			}

			form.setiAcao(new Integer(3)); // Indicar Alteração

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/campanha/Manter/ManterSubCampanha/ParametrosSubCampanhaAction");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute("showPanel", ConstantesCRM.SONE);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="InsereSubCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward ManterSubCampanhaAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ManterCampanhaActionForm form = (ManterCampanhaActionForm) formParam;

			/********************** Verifica se está incluindo ou editando uma subcampanha para habilitar botão versão *******/
			Integer iAcao = new Integer(0); // Inclusão de uma nova subcampanha
			form.setIdCampanha(idCampanha);
			form.setIdSubCampanhaFixa(idSubCampanhaFixa);
			form.setSgCampanha(dsCampanha);
			form.setSgSubCampanha(dsCampanha);

			form.setiRecontato(ConstantesCRM.SZERO);

			// IF INCLUIR
			// iAcao = 0; // Inclusão de uma subCampanha

			/****************** Manda inNegative = 0 para retornar todos canais disponíveis **********************************/
			// form.setListaCanalDisp(apoioFacade.getApoioCanalUFOper(user, 0,
			// 0).getSubGrupoApoioVOArray(0).getApoioVOArray());

			form.setListaCanalDisp(apoioFacade.getApoioCanalUFOper(getUser(request), 0, 0).getSubGrupoApoioVOArray(0).getApoioVOArray());

			form.setiStatus(String.valueOf(1));

			// end if INCLUIR
			/***********************************************************************************************************************/

			/***************************************************** Obtem do form principal as lista carregadas anteriormente *******/
			form.setIdAcao(iAcao);

			/************* Recarrega lista anteriores pois ocorreram modificações ******************************************************/

			// form.setListaTipoCampanha(apoioFacade.getApoioTipoCampanha(user).getSubGrupoApoioVOArray(0).getApoioVOArray());
			form.setListaTipoCampanha(apoioFacade.getApoioTipoCampanha(getUser(request)).getSubGrupoApoioVOArray(0).getApoioVOArray());
			/****************** Manda inNegative = 1 para retornar todos grupos disponíveis que não estão associados a subcampanha **********************************/
			// form.setListaPerfilDisp(apoioFacade.getApoioGrupo(user, form.getIdSubCampanhaFixa(),
			// 1).getSubGrupoApoioVOArray(0).getApoioVOArray());
			form.setListaPerfilDisp(apoioFacade.getApoioGrupo(getUser(request), Integer.parseInt(form.getIdSubCampanhaFixa()), 1).getSubGrupoApoioVOArray(0).getApoioVOArray());

			/********************* Buscar todos os Perfis ja configurados para idSubCampanhaFixa ******************************************************************************/
			if (form.getIdAcao().intValue() == 0) {
				// ApoioVO[] apoioVO = apoioFacade.getApoioGrupo(user, form.getIdSubCampanhaFixa(),
				// 0).getSubGrupoApoioVOArray(0).getApoioVOArray();
				ApoioVO[] apoioVO = apoioFacade.getApoioGrupo(getUser(request), Integer.parseInt(form.getIdSubCampanhaFixa()), 0).getSubGrupoApoioVOArray(0).getApoioVOArray();
				ApoioPerfilVO[] perfilUtil = new ApoioPerfilVO[apoioVO.length];
				if (apoioVO.length > 0) {
					for (int i = 0; i < apoioVO.length; i++) {
						ApoioPerfilVO perfilVO = ApoioPerfilVO.Factory.newInstance();
						perfilVO.setIdGrupo(String.valueOf(apoioVO[i].getCodigo()));
						perfilVO.setNmGrupo(apoioVO[i].getDescricao());
						perfilUtil[i] = perfilVO;
					}
				}
				form.setListaPerfilUtil(perfilUtil);
			}
			this.manterCampanhaActionForm = form;

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="InsereSubCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward manterSubCampanhaActionAlterar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ManterCampanhaActionForm form = (ManterCampanhaActionForm) formParam;

			/********************** Verifica se está incluindo ou editando uma subcampanha para habilitar botão versão *******/
			Integer iAcao = new Integer(0); // Inclusão de uma nova subcampanha
			form.setIdCampanha(idCampanha);
			form.setIdSubCampanhaFixa(idSubCampanhaFixa);
			form.setSgCampanha(dsCampanha);
			form.setSgSubCampanha(dsCampanha);

			// IF EDITAR
			iAcao = new Integer(1); // Edição de uma subCampanha
			// String idSubCampanhaHistorico = idSubCampanha;

			/************** Busca Informações referentes a subCampanha para Edição ********************************************/
			// form.setIdSubCampanha(idSubCampanhaHistorico);
			/*********************** Buscar Campanha Inserida **********************************************************************************************************/
			// SubCampanhaVO subCampanhaVO = campanhaFacade.getSubCampanha(user, form.getIdSubCampanhaFixa(),
			// form.getIdSubCampanha());
			SubCampanhaVO subCampanhaVO = campanhaFacade.getSubCampanha(getUser(request), Integer.parseInt((String) request.getSession().getAttribute("idSubCampanhaFixa")), Integer.parseInt(form.getIdSubCampanha()));

			form.setSubCampanhaVO(subCampanhaVO);
			form.formLoad();

			// form.setiStatus(subCampanhaVO.getid);

			/****************** Manda inNegative = 1 para retornar todos canais disponíveis que não estão associados a subcampanha **********************************/
			// form.setListaCanalDisp(apoioFacade.getApoioCanalUFOper(user, form.getIdSubCampanha(),
			// 1).getSubGrupoApoioVOArray(0).getApoioVOArray());
			form.setListaCanalDisp(apoioFacade.getApoioCanalUFOper(getUser(request), Integer.parseInt(form.getIdSubCampanha()), 1).getSubGrupoApoioVOArray(0).getApoioVOArray());
			// end if EDITAR
			/***********************************************************************************************************************/

			/***************************************************** Obtem do form principal as lista carregadas anteriormente *******/
			form.setIdAcao(iAcao);

			/************* Recarrega lista anteriores pois ocorreram modificações ******************************************************/

			// form.setListaTipoCampanha(apoioFacade.getApoioTipoCampanha(user).getSubGrupoApoioVOArray(0).getApoioVOArray());
			form.setListaTipoCampanha(apoioFacade.getApoioTipoCampanha(getUser(request)).getSubGrupoApoioVOArray(0).getApoioVOArray());
			/****************** Manda inNegative = 1 para retornar todos grupos disponíveis que não estão associados a subcampanha **********************************/
			// form.setListaPerfilDisp(apoioFacade.getApoioGrupo(user, form.getIdSubCampanhaFixa(),
			// 1).getSubGrupoApoioVOArray(0).getApoioVOArray());
			form.setListaPerfilDisp(apoioFacade.getApoioGrupo(getUser(request), Integer.parseInt(form.getIdSubCampanhaFixa()), 1).getSubGrupoApoioVOArray(0).getApoioVOArray());

			/********************* Buscar todos os Perfis ja configurados para idSubCampanhaFixa ******************************************************************************/
			if (form.getIdAcao().intValue() == 0) {
				// ApoioVO[] apoioVO = apoioFacade.getApoioGrupo(user, form.getIdSubCampanhaFixa(),
				// 0).getSubGrupoApoioVOArray(0).getApoioVOArray();
				ApoioVO[] apoioVO = apoioFacade.getApoioGrupo(getUser(request), Integer.parseInt(form.getIdSubCampanhaFixa()), 0).getSubGrupoApoioVOArray(0).getApoioVOArray();
				ApoioPerfilVO[] perfilUtil = new ApoioPerfilVO[apoioVO.length];

				if (apoioVO.length > 0) {

					for (int i = 0; i < apoioVO.length; i++) {

						ApoioPerfilVO perfilVO = ApoioPerfilVO.Factory.newInstance();
						perfilVO.setIdGrupo(String.valueOf(apoioVO[i].getCodigo()));
						perfilVO.setNmGrupo(apoioVO[i].getDescricao());

						perfilUtil[i] = perfilVO;
					}
				}

				form.setListaPerfilUtil(perfilUtil);

			}
			this.manterCampanhaActionForm = form;

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, "../ManterArvoreConfig/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);

			// throw e;
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public ArrayList getListaModificacoes(String[] listaBase, String[] listaAtual) {
		try {
			ArrayList aRetorno = new ArrayList();
			if ((listaBase == null) || (listaAtual == null)) {
				/*********************** Listas Null ***********************/
				throw (new Exception("Listas vazia"));
			}

			for (int i = 0; i < listaBase.length; i++) {
				for (int j = 0; j < listaAtual.length; j++) {
					if (listaBase[i].equalsIgnoreCase(listaAtual[j])) {
						aRetorno.add(listaBase[i]);
					}
				}
			}

			return aRetorno;

		} catch (Exception e) {
			return new ArrayList();
		}
	}

	public CopiaPerguntasActionForm getCopiaPerguntasActionForm() {
		return copiaPerguntasActionForm;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="CopiaSubCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward CopiaPerguntasAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CopiaPerguntasActionForm form = (CopiaPerguntasActionForm) formParam;

		/****************************** Carrega lista fixas que não dependem de parametros ************************************************/
		// form.setListaSubCampanhaOrig(copiaPerguntasActionForm.getListaSubCampanhaOrig());
		// form.setListaSubCampanhaDest(copiaPerguntasActionForm.getListaSubCampanhaDest());

		form.setListaCanalOrig(copiaPerguntasActionForm.getListaCanalOrig());
		// form.setListaCanalDest(copiaPerguntasActionForm.getListaCanalDest());
		// form.setListaCampanhaDest(copiaPerguntasActionForm.getListaCampanhaDest());
		// copiaPerguntasActionForm = form;

		try {
			copiaPerguntasActionForm.setIdAcao(new Integer(0));
			if ("salvar".equalsIgnoreCase(request.getParameter(ConstantesCRM.SACTION))) {
				int iCanalOrigem = Integer.parseInt(form.getCanalSelecionadoOrig());
				int iCanalDest = Integer.parseInt(form.getCanalSelecionadoDest());

				RetornoVO retorno = copiaFacade.addCopia(getUser(request), iCanalOrigem, iCanalDest);
				int perguntasCopiadas = Integer.parseInt(retorno.getValor());

				if (perguntasCopiadas > 0) {
					copiaPerguntasActionForm.setIdAcao(new Integer(3));
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("fechar");

				} else {
					copiaPerguntasActionForm.setSMsg("Perguntas já copiadas para destino selecionado");
					copiaPerguntasActionForm.setIdAcao(new Integer(1));
				}
			}

			/***************** Envia parametro inNegative = 1 (Retornar todas os canais campanha associados á campanha) *******************************/
			GrupoCampanhaApoioVO grupoCanalCampanhaApoio = apoioFacade.getApoioCanalCampanha(getUser(request), idSubCampanha, 0);
			copiaPerguntasActionForm.setListaCanalOrig(grupoCanalCampanhaApoio.getSubGrupoApoioVOArray(0).getApoioVOArray());

			/*********************** Lista de Campanhas *******************************************************************/
			GrupoCampanhaApoioVO campanhaApoio = apoioFacade.getApoioCampanha(getUser(request));
			copiaPerguntasActionForm.setListaCampanhaDest(campanhaApoio.getSubGrupoApoioVOArray(0).getApoioVOArray());

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="CopiaSubCampanha.jsp"
	 * @jpf:forward name="fechar" path="/campanha/Manter/ManterArvoreConfig/fechaFrameSubCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward copiaPerguntasSalvar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CopiaPerguntasActionForm form = (CopiaPerguntasActionForm) formParam;
		try {
			copiaPerguntasActionForm.setIdAcao(new Integer(0));
			copiaPerguntasActionForm.setCanalSelecionadoDest(form.getCanalSelecionadoDest());

			int iCanalOrigem = Integer.parseInt(form.getCanalSelecionadoOrig());
			int iCanalDest = Integer.parseInt(form.getCanalSelecionadoDest());

			RetornoVO retorno = copiaFacade.addCopia(getUser(request), iCanalOrigem, iCanalDest);
			int perguntasCopiadas = Integer.parseInt(retorno.getValor());

			if (perguntasCopiadas > 0) {
				copiaPerguntasActionForm.setIdAcao(new Integer(3));
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("fechar");

			} else {
				// copiaPerguntasActionForm.setSMsg("Perguntas já copiadas para destino selecionado");
				request.setAttribute("isErrorCopiaSubCampanha", "Perguntas já copiadas para destino selecionado");
				copiaPerguntasActionForm.setIdAcao(new Integer(1));
			}

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="IFrameObtemVersoes.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward obtemVersoes(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			CopiaPerguntasActionForm form = (CopiaPerguntasActionForm) formParam;

			copiaPerguntasActionForm.setSubCampanhaDestSelecionada(form.getSubCampanhaDestSelecionada());

			if (form.getSubCampanhaDestSelecionada() != null && !ConstantesCRM.SVAZIO.equals(form.getSubCampanhaDestSelecionada())) {
				GrupoCampanhaApoioVO grupoSubCampanhaApoio = apoioFacade.getApoioVersao(getUser(request), form.getSubCampanhaDestSelecionada());
				copiaPerguntasActionForm.setListaVersaoDest(grupoSubCampanhaApoio.getSubGrupoApoioVOArray(0).getApoioVOArray());
				copiaPerguntasActionForm.setVersaoDestSelecionada(ConstantesCRM.SVAZIO);

				String idVersao = ConstantesCRM.SVAZIO;

				if (grupoSubCampanhaApoio.getSubGrupoApoioVOArray(0).getApoioVOArray() != null && grupoSubCampanhaApoio.getSubGrupoApoioVOArray(0).getApoioVOArray().length > 0) {
					idVersao = grupoSubCampanhaApoio.getSubGrupoApoioVOArray(0).getApoioVOArray()[0].getCodigo();
				}

				GrupoCampanhaApoioVO grupoCanalCampanhaApoio = apoioFacade.getApoioCanalCampanha(getUser(request), idVersao, 0);

				copiaPerguntasActionForm.setListaCanalDest(grupoCanalCampanhaApoio.getSubGrupoApoioVOArray(0).getApoioVOArray());
				copiaPerguntasActionForm.setCampanhaDestSelecionada(ConstantesCRM.SVAZIO);
			}
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
	 * @jpf:forward name="success" path="IFrameObtemListas.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward obtemCanais(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			CopiaPerguntasActionForm form = (CopiaPerguntasActionForm) formParam;

			copiaPerguntasActionForm.setVersaoDestSelecionada(form.getVersaoDestSelecionada());

			if ((form.getVersaoDestSelecionada() != null) && (!form.getVersaoDestSelecionada().equals(ConstantesCRM.SVAZIO))) {
				GrupoCampanhaApoioVO grupoCanalCampanhaApoio = apoioFacade.getApoioCanalCampanha(getUser(request), form.getVersaoDestSelecionada(), 0);

				copiaPerguntasActionForm.setListaCanalDest(grupoCanalCampanhaApoio.getSubGrupoApoioVOArray(0).getApoioVOArray());

				copiaPerguntasActionForm.setCampanhaDestSelecionada(ConstantesCRM.SVAZIO);
			}
		} catch (Exception e) {
			throw e;
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="IFrameObtemListas.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward obtemSubCampanha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			CopiaPerguntasActionForm form = (CopiaPerguntasActionForm) formParam;

			copiaPerguntasActionForm.setCampanhaDestSelecionada(form.getCampanhaDestSelecionada());

			if (form.getCampanhaDestSelecionada() != null && !ConstantesCRM.SVAZIO.equals(form.getCampanhaDestSelecionada())) {
				GrupoCampanhaApoioVO grupoSubCampanhaApoio = apoioFacade.getApoioSubCampanha(getUser(request), form.getCampanhaDestSelecionada());

				copiaPerguntasActionForm.setListaSubCampanhaDest(grupoSubCampanhaApoio.getSubGrupoApoioVOArray(0).getApoioVOArray());

				copiaPerguntasActionForm.setSubCampanhaDestSelecionada(ConstantesCRM.SVAZIO);
			}
		} catch (Exception e) {
			throw e;
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="fechar" path="/campanha/Manter/ManterArvoreConfig/fechaFrameSubCampanha.jsp"
	 * @jpf:forward name="manterSubCampanhaAction1" path="ManterSubCampanhaAction.do"
	 * @jpf:forward name="manterSubCampanhaAction" path="InsereSubCampanha.jsp"
	 */
	protected ActionForward ProcessaSubCampanhaAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterCampanhaActionForm form = (ManterCampanhaActionForm) formParam;

		request.setCharacterEncoding(ConstantesCRM.SISO);
		String _idSubCampanhaHistorico = ConstantesCRM.SZERO;
		try {

			/***************************** Listas de Canais ********************************************************************************************************************/
			int iTam = getManterCampanhaActionForm().getListaCanalDisp().length;
			String[] listaCanalDispOriginal = new String[iTam];
			for (int i = 0; i < iTam; i++) {
				listaCanalDispOriginal[i] = String.valueOf(getManterCampanhaActionForm().getListaCanalDisp()[i].getCodigo());
			}

			iTam = getManterCampanhaActionForm().getListaCanalUtil().length;
			String[] listaCanalUtilOriginal = new String[iTam];
			for (int i = 0; i < iTam; i++) {
				listaCanalUtilOriginal[i] = String.valueOf(getManterCampanhaActionForm().getListaCanalUtil()[i].getCodigo());
			}

			ArrayList aCanalInseridos;
			ArrayList aCanalRemovidos;

			/**************** Obtem Lista de Canais que deverão ser inseridos na associação **********************************************************/
			aCanalInseridos = getListaModificacoes(form.getCanalUtilSelecionado(), listaCanalDispOriginal);

			/**************** Obtem Lista de Canais que deverão ser removido das associações anteriores **********************************************************/
			aCanalRemovidos = getListaModificacoes(listaCanalUtilOriginal, form.getCanalDispSelecionado());

			/***************************** Listas de Perfis ********************************************************************************************************************/

			iTam = getManterCampanhaActionForm().getListaPerfilDisp().length;
			String[] listaPerfilDispOriginal = new String[iTam];
			for (int i = 0; i < iTam; i++) {
				listaPerfilDispOriginal[i] = String.valueOf(getManterCampanhaActionForm().getListaPerfilDisp()[i].getCodigo());
			}

			iTam = getManterCampanhaActionForm().getListaPerfilUtil().length;
			String[] listaPerfilUtilOriginal = new String[iTam];
			for (int i = 0; i < iTam; i++) {
				listaPerfilUtilOriginal[i] = String.valueOf(getManterCampanhaActionForm().getListaPerfilUtil()[i].getIdGrupo());
			}

			ArrayList aPerfilInseridos;
			ArrayList aPerfilRemovidos;

			/**************** Obtem Lista de Perfis que deverão ser inseridos na associação **********************************************************/
			aPerfilInseridos = getListaModificacoes(form.getPerfilUtilSelecionado(), listaPerfilDispOriginal);

			/**************** Obtem Lista de Canais que deverão ser removido das associações anteriores **********************************************************/
			aPerfilRemovidos = getListaModificacoes(listaPerfilUtilOriginal, form.getPerfilDispSelecionado());

			/************* Preencher SubCampanhaVO para inclusão *********************************************************************************************************/
			SubCampanhaVO subCampanha = SubCampanhaVO.Factory.newInstance();
			form.setSubCampanhaVO(subCampanha);

			if ("salvar".equalsIgnoreCase(request.getParameter("processa"))) {

				if (form.getIdAcao().intValue() == 0) {

					RetornoCampanhaVO retorno = campanhaFacade.addSubCampanha(getUser(request), form.getSubCampanhaVO(aCanalInseridos, aCanalRemovidos, aPerfilInseridos, aPerfilRemovidos));
					request.getSession().setAttribute("idSubCampanhaFixa", retorno.getIdSubCampanhaFixa());
					_idSubCampanhaHistorico = retorno.getIdSubCampanhaHistorico();
					request.setAttribute("tipo", ConstantesCRM.SONE);
					getManterCampanhaActionForm().setIdSubCampanha(_idSubCampanhaHistorico);
					getManterCampanhaActionForm().setIdCampanha(idCampanha);

				} else if (form.getIdAcao().intValue() == 1) {

					campanhaFacade.setSubCampanha(getUser(request), form.getSubCampanhaVO(aCanalInseridos, aCanalRemovidos, aPerfilInseridos, aPerfilRemovidos));
					_idSubCampanhaHistorico = form.getIdSubCampanha();
					request.setAttribute("tipo", ConstantesCRM.SNINE);
				}

			}

			/*
			 * Pablo - 16-11-04 Gostaria muito de tirar estes elementos da sessão, ou ao menos colocar seus Keys como
			 * constantes, para evitar duplicidade, mas infelizmente não sei ao certo onde estes elementos são usados!
			 */
			request.getSession().setAttribute("idSubCampanha", _idSubCampanhaHistorico);

		} catch (TuxedoWarningException e) {
			/*
			 * Alteração Pablo - 16-11-04 Ocorrência do clearquest para nomes duplicados para mesma campanha
			 */

			request.setAttribute("manterCampanhaActionForm", getManterCampanhaActionForm());

			switch (form.getIdAcao().intValue()) {
			case 0:
				request.setAttribute(ConstantesCRM.SACTION, "incluir");
				break;
			case 1:
				request.setAttribute(ConstantesCRM.SACTION, "editar");
				break;
			}

			getManterCampanhaActionForm().setErroMsg(e.getXmlHeader().getStatusText());
			
			return mapping.findForward("manterSubCampanhaAction");

		} catch (Exception e) {
			throw e;
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("fechar");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="script" path="/campanha/Manter/ManterArvoreConfig/fechaFrameSubCampanha.jsp"
	 * @jpf:forward name="nada" path="/campanha/Manter/ManterArvoreConfig/nada.html"
	 */
	protected ActionForward voltarAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String destino = "nada";

		if (request.getParameter("voltarPara") != null) {
			destino = request.getParameter("voltarPara");
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(destino);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="script" path="/campanha/Manter/ManterArvoreConfig/fechaFrameSubCampanha.jsp"
	 * @jpf:forward name="success" path="InsereSubCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward criarVersao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			ManterCampanhaActionForm form = (ManterCampanhaActionForm) formParam;

			RetornoVO retorno = campanhaFacade.criarVersao(getUser(request), form.getIdSubCampanha());
			request.getSession().setAttribute("idSubCampanha", retorno.getValor());
		} catch (TuxedoWarningException e) {
			getManterCampanhaActionForm().setErroMsg(e.getXmlHeader().getStatusText());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute("tipo", ConstantesCRM.SFIVE);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("script");
	}

	public static class ManterCampanhaActionForm extends ActionForm {

		private static final long serialVersionUID = -7574784125208075425L;

		private String erroMsg;
		private String nmSubCampanha;
		private String iAgenda = ConstantesCRM.SVAZIO;
		private String iStatus = ConstantesCRM.SONE;
		private String iRecontato = ConstantesCRM.SZERO;
		private String iCliente = ConstantesCRM.SONE;
		private String idCampanha;
		private String idSubCampanha;
		private String idVersao = ConstantesCRM.SONE;
		private String idSubCampanhaFixa;
		private String inFidelizacao = ConstantesCRM.SZERO;
		private String dtTermino;
		private String dtInicio;
		private String dsSubCampanha;
		private String sTipoCampanha;
		private String sgSubCampanha;
		private String sgCampanha;
		private Integer idAcao = new Integer(0);

		private SubCampanhaVO subCampanhaVOForm = SubCampanhaVO.Factory.newInstance();

		private ApoioVO[] listaCanalDisp;
		private String[] canalDispSelecionado;

		private ApoioCanalVO[] listaCanalUtil;
		private String[] canalUtilSelecionado;

		private ApoioVO[] listaPerfilDisp;
		private String[] perfilDispSelecionado;

		private ApoioPerfilVO[] listaPerfilUtil;
		private String[] perfilUtilSelecionado;

		private ApoioVO[] listaTipoCampanha;
		private String tipoCampanhaSelecionado;

		public Integer getIdAcao() {
			return this.idAcao;
		}

		public void setIdAcao(Integer idAcao) {
			this.idAcao = idAcao;
		}

		public String getInFidelizacao() {
			return this.inFidelizacao;
		}

		public void setInFidelizacao(String inFidelizacao) {
			this.inFidelizacao = inFidelizacao;
		}

		/*************************** Canal Disponivel ************************************************/
		public ApoioVO[] getListaCanalDisp() {
			if (this.listaCanalDisp == null) {
				this.listaCanalDisp = new ApoioVO[0];
			}
			return this.listaCanalDisp;
		}

		public void setListaCanalDisp(ApoioVO[] lista) {
			listaCanalDisp = lista;
		}

		public String[] getCanalDispSelecionado() {
			return canalDispSelecionado;
		}

		public void setCanalDispSelecionado(String[] s) {
			canalDispSelecionado = s;
		}

		/*************************** Canal Utilizado ************************************************/
		public ApoioCanalVO[] getListaCanalUtil() {
			if (this.listaCanalUtil == null) {
				this.listaCanalUtil = new ApoioCanalVO[0];
			}
			return this.listaCanalUtil;
		}

		public void setListaCanalUtil(ApoioCanalVO[] lista) {
			listaCanalUtil = lista;
		}

		public String[] getCanalUtilSelecionado() {
			return canalUtilSelecionado;
		}

		public void setCanalUtilSelecionado(String[] s) {
			canalUtilSelecionado = s;
		}

		/*************************** Perfil Disponivel ************************************************/
		public ApoioVO[] getListaPerfilDisp() {
			if (this.listaPerfilDisp == null) {
				this.listaPerfilDisp = new ApoioVO[0];
			}
			return this.listaPerfilDisp;
		}

		public void setListaPerfilDisp(ApoioVO[] lista) {
			listaPerfilDisp = lista;
		}

		public String[] getPerfilDispSelecionado() {
			return perfilDispSelecionado;
		}

		public void setPerfilDispSelecionado(String[] s) {
			perfilDispSelecionado = s;
		}

		/*************************** Perfil Utilizado ************************************************/
		public ApoioPerfilVO[] getListaPerfilUtil() {
			if (this.listaPerfilUtil == null) {
				this.listaPerfilUtil = new ApoioPerfilVO[0];
			}
			return this.listaPerfilUtil;
		}

		public void setListaPerfilUtil(ApoioPerfilVO[] lista) {
			listaPerfilUtil = lista;
		}

		public String[] getPerfilUtilSelecionado() {
			return perfilUtilSelecionado;
		}

		public void setPerfilUtilSelecionado(String[] s) {
			perfilUtilSelecionado = s;
		}

		/*************************** Tipo Campanha ************************************************/
		public ApoioVO[] getListaTipoCampanha() {
			if (this.listaTipoCampanha == null) {
				this.listaTipoCampanha = new ApoioVO[0];
			}
			return this.listaTipoCampanha;
		}

		public void setListaTipoCampanha(ApoioVO[] lista) {
			listaTipoCampanha = lista;
		}

		public String getTipoCampanhaSelecionado() {
			return tipoCampanhaSelecionado;
		}

		public void setTipoCampanhaSelecionado(String s) {
			tipoCampanhaSelecionado = s;
		}

		/****** Método que preenche VO com dados do BD para carregamento do formulario *********************/
		private void setSubCampanhaVO(SubCampanhaVO subCampanhaBD) {
			this.subCampanhaVOForm = subCampanhaBD;
		}

		/******* Metodo retorna VO preenchido com dados enviados pelo usuário através do formulario *****/
		private SubCampanhaVO getSubCampanhaVO(ArrayList aCanalAtivo, ArrayList aCanalInativo, ArrayList aPerfilAtivo, ArrayList aPerfilInativo) throws Exception {
			if (this.subCampanhaVOForm != null) {

				try {
					this.subCampanhaVOForm.setIdSubCampanha(idSubCampanha);
					this.subCampanhaVOForm.setDsSubCampanha(dsSubCampanha);
					this.subCampanhaVOForm.setInFidelizacao(this.inFidelizacao);

					this.subCampanhaVOForm.setInCliente(iCliente);
					if ("".equals(iAgenda)) {
						this.subCampanhaVOForm.setIAgendaMax(ConstantesCRM.SZERO);
					} else {
						this.subCampanhaVOForm.setIAgendaMax(iAgenda);
					}

					this.subCampanhaVOForm.setSqVersao(idVersao);

					this.subCampanhaVOForm.setIRecontato(iRecontato);
					this.subCampanhaVOForm.setIdTipoCampanha(tipoCampanhaSelecionado);

					this.subCampanhaVOForm.setIdSubCampanhaFixa(idSubCampanhaFixa);

					// this.subCampanhaVOForm.setInAtivo(iStatus);
					this.subCampanhaVOForm.setInAtivo(ConstantesCRM.SONE);

					this.subCampanhaVOForm.setInDisponibilidade(iStatus);

					this.subCampanhaVOForm.setIdCampanha(idCampanha);

					this.subCampanhaVOForm.setDtInicio(dtInicio);
					this.subCampanhaVOForm.setDtTermino(dtTermino);

					this.subCampanhaVOForm.setNmSubCampanha(this.nmSubCampanha);

					/**************************** Canal de Atendimento *********************************************************/
					int i = 0;
					if ((aCanalAtivo.size() > 0) || (aCanalInativo.size() > 0)) {
						this.subCampanhaVOForm.addNewCanalUtil();
					}

					for (i = 0; i < aCanalAtivo.size(); i++) {

						ApoioCanalVO canalVO = ApoioCanalVO.Factory.newInstance();

						canalVO.setSqApresentacao(String.valueOf(i));
						canalVO.setIdSubCampanhaHistorico(this.idSubCampanha);

						canalVO.setCnlcmpinAtivo(ConstantesCRM.SONE);
						String sId = (String) aCanalAtivo.get(i);

						canalVO.setIdCanalUFOperadora(sId);
						// canalVO.setCodigo(Integer.parseInt(sId));

						this.subCampanhaVOForm.getCanalUtil().addNewApoioCanalVO();
						this.subCampanhaVOForm.getCanalUtil().setApoioCanalVOArray(i, canalVO);

					}

					int j = i;
					for (int l = 0; l < aCanalInativo.size(); l++) {

						ApoioCanalVO canalVO = ApoioCanalVO.Factory.newInstance();

						canalVO.setSqApresentacao(ConstantesCRM.SONE);
						canalVO.setIdSubCampanhaHistorico(this.idSubCampanha);
						canalVO.setCnlcmpinAtivo(ConstantesCRM.SZERO);

						String sId = (String) aCanalInativo.get(l);
						canalVO.setIdCanalUFOperadora(sId);

						this.subCampanhaVOForm.getCanalUtil().addNewApoioCanalVO();
						this.subCampanhaVOForm.getCanalUtil().setApoioCanalVOArray(j, canalVO);
						j++;
					}

					/**************************** Perfil de Acesso *********************************************************/
					if ((aPerfilAtivo.size() > 0) || (aPerfilInativo.size() > 0)) {
						this.subCampanhaVOForm.addNewPerfilUtil();
					}

					for (i = 0; i < aPerfilAtivo.size(); i++) {

						ApoioPerfilVO perfilVO = ApoioPerfilVO.Factory.newInstance();

						perfilVO.setGinAtivo(ConstantesCRM.SONE);

						String sId = (String) aPerfilAtivo.get(i);
						perfilVO.setIdGrupo(sId);

						this.subCampanhaVOForm.getPerfilUtil().addNewApoioPerfilVO();
						this.subCampanhaVOForm.getPerfilUtil().setApoioPerfilVOArray(i, perfilVO);

					}
					j = i;
					for (int l = 0; l < aPerfilInativo.size(); l++) {

						ApoioPerfilVO perfilVO = ApoioPerfilVO.Factory.newInstance();

						perfilVO.setGinAtivo(ConstantesCRM.SZERO);

						String sId = (String) aPerfilInativo.get(l);
						perfilVO.setIdGrupo(sId);

						this.subCampanhaVOForm.getPerfilUtil().addNewApoioPerfilVO();
						this.subCampanhaVOForm.getPerfilUtil().setApoioPerfilVOArray(j, perfilVO);
						j++;
					}

				} catch (Exception e) {
					throw e;
				}
			}

			return this.subCampanhaVOForm;
		}

		public void setSgCampanha(String sgCampanha) {
			this.sgCampanha = sgCampanha;
		}

		public String getSgCampanha() {
			return this.sgCampanha;
		}

		public void setSgSubCampanha(String sgSubCampanha) {
			this.sgSubCampanha = sgSubCampanha;
		}

		public String getSgSubCampanha() {
			return this.sgSubCampanha;
		}

		public void setTipoCampanha(String aTipoCampanha) {
			this.sTipoCampanha = aTipoCampanha;
		}

		public String getTipoCampanha() {
			return this.sTipoCampanha;
		}

		public void setDsSubCampanha(String dsSubCampanha) {
			this.dsSubCampanha = dsSubCampanha;
		}

		public String getDsSubCampanha() {
			return this.dsSubCampanha;
		}

		public void setDtInicio(String dtInicio) {
			this.dtInicio = dtInicio;
		}

		public String getDtInicio() {
			return this.dtInicio;
		}

		public void setDtTermino(String dtTermino) {
			this.dtTermino = dtTermino;
		}

		public String getDtTermino() {
			return this.dtTermino;
		}

		public void setiRecontato(String iRecontato) {
			this.iRecontato = iRecontato;
		}

		public String getiRecontato() {
			return this.iRecontato;
		}

		public void setiStatus(String iStatus) {
			this.iStatus = iStatus;
		}

		public String getiStatus() {
			return this.iStatus;
		}

		public void setiAgenda(String iAgenda) {
			this.iAgenda = iAgenda;
		}

		public String getiAgenda() {
			return this.iAgenda;
		}

		public String getIdCampanha() {
			return this.idCampanha;
		}

		public void setIdCampanha(String iIdCampanha) {
			this.idCampanha = iIdCampanha;
		}

		public String getIdSubCampanha() {
			return this.idSubCampanha;
		}

		public void setIdSubCampanha(String iIdSubCampanha) {
			this.idSubCampanha = iIdSubCampanha;
		}

		public void setiCliente(String iCliente) {
			this.iCliente = iCliente;
		}

		public String getiCliente() {
			return this.iCliente;
		}

		public void formLoad() throws Exception {

			try {
				if (this.subCampanhaVOForm != null) {
					this.idVersao = this.subCampanhaVOForm.getSqVersao();
					this.iAgenda = this.subCampanhaVOForm.getIAgendaMax();
					// this.iStatus = this.subCampanhaVOForm.getInAtivo();
					this.iStatus = this.subCampanhaVOForm.getInDisponibilidade();
					this.iRecontato = this.subCampanhaVOForm.getIRecontato();
					this.iCliente = this.subCampanhaVOForm.getInCliente();
					this.idCampanha = this.subCampanhaVOForm.getIdCampanha();
					this.idSubCampanha = this.subCampanhaVOForm.getIdSubCampanha();
					this.dtTermino = this.subCampanhaVOForm.getDtTermino();
					this.dtInicio = this.subCampanhaVOForm.getDtInicio();
					this.dsSubCampanha = this.subCampanhaVOForm.getDsSubCampanha();
					this.sgSubCampanha = this.subCampanhaVOForm.getDsSubCampanha();
					this.sgCampanha = this.subCampanhaVOForm.getSgCampanha();
					this.idSubCampanhaFixa = this.subCampanhaVOForm.getIdSubCampanhaFixa();
					this.tipoCampanhaSelecionado = String.valueOf(this.subCampanhaVOForm.getIdTipoCampanha());
					this.nmSubCampanha = this.subCampanhaVOForm.getNmSubCampanha();
					this.inFidelizacao = this.subCampanhaVOForm.getInFidelizacao();

					/************************ Lista de Canais Utilizados pela SubCampanha ******************************************/
					if (this.subCampanhaVOForm.getCanalUtil().getApoioCanalVOArray().length > 0) {
						this.listaCanalUtil = this.subCampanhaVOForm.getCanalUtil().getApoioCanalVOArray();
					}
					/************************ Lista de Grupos Utilizados pela SubCampanha ******************************************/
					if (this.subCampanhaVOForm.getPerfilUtil().getApoioPerfilVOArray().length > 0) {
						this.listaPerfilUtil = this.subCampanhaVOForm.getPerfilUtil().getApoioPerfilVOArray();
					}
				}
			} catch (Exception e) {
				throw e;
			}
		}

		public void clear() {
			this.iAgenda = ConstantesCRM.SZERO;
			this.iStatus = ConstantesCRM.SONE;
			this.iRecontato = ConstantesCRM.SZERO;
			this.iCliente = ConstantesCRM.SZERO;
			this.idCampanha = ConstantesCRM.SZERO;
			this.idSubCampanha = ConstantesCRM.SZERO;
			this.idSubCampanhaFixa = ConstantesCRM.SZERO;
			this.dtTermino = ConstantesCRM.SVAZIO;
			this.dtInicio = ConstantesCRM.SVAZIO;
			this.dsSubCampanha = ConstantesCRM.SVAZIO;
			this.sTipoCampanha = ConstantesCRM.SVAZIO;
			this.sgSubCampanha = ConstantesCRM.SVAZIO;
			this.sgCampanha = ConstantesCRM.SVAZIO;
			this.listaCanalUtil = new ApoioCanalVO[0];
			this.listaPerfilUtil = new ApoioPerfilVO[0];
		}

		public String getIdVersao() {
			return idVersao;
		}

		public void setIdVersao(String i) {
			idVersao = i;
		}

		public String getIdSubCampanhaFixa() {
			return idSubCampanhaFixa;
		}

		public void setIdSubCampanhaFixa(String i) {
			this.idSubCampanhaFixa = i;
		}

		public void setNmSubCampanha(String nmSubCampanha) {
			this.nmSubCampanha = nmSubCampanha;
		}

		public String getNmSubCampanha() {
			return this.nmSubCampanha;
		}

		public void setErroMsg(String erroMsg) {
			this.erroMsg = erroMsg;
		}

		public String getErroMsg() {
			return this.erroMsg;
		}
	}

	public ParametrosSubCampanhaActionForm getParametrosSubCampanhaActionForm() {
		return parametrosSubCampanhaActionForm;
	}

	public static class ParametrosSubCampanhaActionForm extends ActionForm {

		private static final long serialVersionUID = 6560637935967616709L;

		private String operacao;
		private String iIdCanal;
		private String sCanal;
		private Integer iAcao;
		private String iIdSubCampanha;
		private String iIdCampanha;
		// private int iIdCanalCampanha;
		private String sSubCampanha;
		private String sCampanha;
		private String fOperNaoAdesao;
		private String fOperAdesao;
		private String fOperContEfetivo;
		private String fOperContSucesso;
		private String fOperMetaContEfetivo;
		private String fCampNaoAdesao;
		private String fCampAdesao;
		private String fCampContEfetivo;
		private String fCampContSucesso;
		private String fCampMetaDiaria;
		private String nrTempoMedio;
		private String fTMA;
		private String iPublicoTotal;
		private ApoioVO[] listaCanalDisp = new ApoioVO[0];
		private String canalDispSelecionado;

		// private ApoioCanalVO[] listaCanalUtil = new ApoioCanalVO[0];
		// private String[] canalUtilSelecionado;

		private CampanhaParametrosVO paramCampanhaForm = CampanhaParametrosVO.Factory.newInstance();

		/*************************** Canal Disponivel ************************************************/
		public ApoioVO[] getListaCanalDisp() {
			return listaCanalDisp;
		}

		public void setListaCanalDisp(ApoioVO[] lista) {
			listaCanalDisp = lista;
		}

		public String getCanalDispSelecionado() {
			return canalDispSelecionado;
		}

		public void setCanalDispSelecionado(String s) {
			canalDispSelecionado = s;
		}

		/*************************** Canal Utilizado ************************************************/
		/*
		 * public ApoioCanalVO[] getListaCanalUtil() { return listaCanalUtil; } public void
		 * setListaCanalUtil(ApoioCanalVO[] lista) { listaCanalUtil = lista; } public String[] getCanalUtilSelecionado()
		 * { return canalUtilSelecionado; } public void setCanalUtilSelecionado(String[] s) { canalUtilSelecionado = s;
		 * }
		 */
		/****** Método que preenche VO com dados do BD para carregamento do formulario *********************/
		public void setParamCampanhaForm(CampanhaParametrosVO paramCampanhaBD) {
			this.paramCampanhaForm = paramCampanhaBD;
		}

		/******* Metodo retorna VO preenchido com dados enviados pelo usuário através do formulario *****/
		public CampanhaParametrosVO getParamCampanhaForm() {

			this.paramCampanhaForm.setNrAderiuCmp(fCampAdesao);
			this.paramCampanhaForm.setNrAderiuOpr(fOperAdesao);

			this.paramCampanhaForm.setNrContEfetivoCmp(fCampContEfetivo);
			this.paramCampanhaForm.setNrContEfetivoOpr(fOperContEfetivo);

			this.paramCampanhaForm.setNrContSucessoCmp(fCampContSucesso);
			this.paramCampanhaForm.setNrContSucessoOpr(fOperContSucesso);

			this.paramCampanhaForm.setNrMetaDiariaCmp(fCampMetaDiaria);
			this.paramCampanhaForm.setNrMetaDiariaOpr(fOperMetaContEfetivo);

			this.paramCampanhaForm.setNrNaoAderiuCmp(fCampNaoAdesao);
			this.paramCampanhaForm.setNrNaoAderiuOpr(fOperNaoAdesao);

			this.paramCampanhaForm.setNrPublicoTotal(iPublicoTotal);
			this.paramCampanhaForm.setNrTempoMedio(String.valueOf(fTMA));

			if ((!canalDispSelecionado.equals(ConstantesCRM.SVAZIO))) {
				this.paramCampanhaForm.addNewCanalUtil();

				/*
				 * for (int i =0 ; i< canalDispSelecionado.length; i++) {
				 */
				// String sId = canalDispSelecionado[i];
				int i = 0;
				String sId = canalDispSelecionado;
				ApoioCanalVO canalVO = ApoioCanalVO.Factory.newInstance();
				canalVO.setCnlcmpinAtivo(ConstantesCRM.SONE);
				canalVO.setIdCanalCampanha(sId);

				this.paramCampanhaForm.getCanalUtil().addNewApoioCanalVO();
				this.paramCampanhaForm.getCanalUtil().setApoioCanalVOArray(i, canalVO);
				// }
			}

			return this.paramCampanhaForm;
		}

		public void setiPublicoTotal(String iPublicoTotal) {
			this.iPublicoTotal = iPublicoTotal;
		}

		public String getiPublicoTotal() {
			return this.iPublicoTotal;
		}

		public void setSTMA(String i) {
			this.fTMA = i;
		}

		public String getSTMA() {
			return this.fTMA;
		}

		public void setfCampMetaDiaria(String fCampMetaDiaria) {
			this.fCampMetaDiaria = fCampMetaDiaria;
		}

		public String getfCampMetaDiaria() {
			return this.fCampMetaDiaria;
		}

		public void setNrTempoMedio(String nrTempoMedio) {
			this.nrTempoMedio = nrTempoMedio;
		}

		public String getNrTempoMedio() {
			return nrTempoMedio;
		}

		public void setfCampContSucesso(String fCampContSucesso) {
			this.fCampContSucesso = fCampContSucesso;
		}

		public String getfCampContSucesso() {
			return this.fCampContSucesso;
		}

		public void setfCampContEfetivo(String fCampContEfetivo) {
			this.fCampContEfetivo = fCampContEfetivo;
		}

		public String getfCampContEfetivo() {
			return this.fCampContEfetivo;
		}

		public void setfCampAdesao(String fCampAdesao) {
			this.fCampAdesao = fCampAdesao;
		}

		public String getfCampAdesao() {
			return this.fCampAdesao;
		}

		public void setfCampNaoAdesao(String fCampNaoAdesao) {
			this.fCampNaoAdesao = fCampNaoAdesao;
		}

		public String getfCampNaoAdesao() {
			return this.fCampNaoAdesao;
		}

		public void setfOperMetaContEfetivo(String fOperMetaContEfetivo) {
			this.fOperMetaContEfetivo = fOperMetaContEfetivo;
		}

		public String getfOperMetaContEfetivo() {
			return this.fOperMetaContEfetivo;
		}

		public void setfOperContSucesso(String fOperContSucesso) {
			this.fOperContSucesso = fOperContSucesso;
		}

		public String getfOperContSucesso() {
			return this.fOperContSucesso;
		}

		public void setfOperContEfetivo(String fOperContEfetivo) {
			this.fOperContEfetivo = fOperContEfetivo;
		}

		public String getfOperContEfetivo() {
			return this.fOperContEfetivo;
		}

		public void setfOperAdesao(String fOperAdesao) {
			this.fOperAdesao = fOperAdesao;
		}

		public String getfOperAdesao() {
			return this.fOperAdesao;
		}

		public void setfOperNaoAdesao(String fOperNaoAdesao) {
			this.fOperNaoAdesao = fOperNaoAdesao;
		}

		public String getfOperNaoAdesao() {
			return this.fOperNaoAdesao;
		}

		public void setsCampanha(String sCampanha) {
			this.sCampanha = sCampanha;
		}

		public String getsCampanha() {
			return this.sCampanha;
		}

		public void setsSubCampanha(String sSubCampanha) {
			this.sSubCampanha = sSubCampanha;
		}

		public String getsSubCampanha() {
			return this.sSubCampanha;
		}

		public void setiIdCampanha(String iIdCampanha) {
			this.iIdCampanha = iIdCampanha;
		}

		public String getiIdCampanha() {
			return this.iIdCampanha;
		}

		public void setiIdSubCampanha(String iIdSubCampanha) {
			this.iIdSubCampanha = iIdSubCampanha;
		}

		public String getiIdSubCampanha() {
			return this.iIdSubCampanha;
		}

		public void clear() {
			this.iIdSubCampanha = ConstantesCRM.SZERO;
			this.iIdCampanha = ConstantesCRM.SZERO;
			this.sSubCampanha = ConstantesCRM.SVAZIO;
			this.sCampanha = ConstantesCRM.SVAZIO;

			this.fOperNaoAdesao = ConstantesCRM.SZERO;
			this.fOperAdesao = ConstantesCRM.SZERO;
			this.fOperContEfetivo = ConstantesCRM.SZERO;
			this.fOperContSucesso = ConstantesCRM.SZERO;
			this.fOperMetaContEfetivo = ConstantesCRM.SZERO;

			this.fCampNaoAdesao = ConstantesCRM.SZERO;
			this.fCampAdesao = ConstantesCRM.SZERO;
			this.fCampContEfetivo = ConstantesCRM.SZERO;
			this.fCampContSucesso = ConstantesCRM.SZERO;
			this.fCampMetaDiaria = ConstantesCRM.SZERO;

			this.fTMA = ConstantesCRM.SZERO;
			this.iPublicoTotal = ConstantesCRM.SZERO;

		}

		public void formLoad() {
			if (this.paramCampanhaForm != null) {

				fCampAdesao = paramCampanhaForm.getNrAderiuCmp();
				fOperAdesao = paramCampanhaForm.getNrAderiuOpr();

				fCampContEfetivo = paramCampanhaForm.getNrContEfetivoCmp();
				fOperContEfetivo = paramCampanhaForm.getNrContEfetivoOpr();

				fCampContSucesso = paramCampanhaForm.getNrContSucessoCmp();
				fOperContSucesso = paramCampanhaForm.getNrContSucessoOpr();

				fCampMetaDiaria = paramCampanhaForm.getNrMetaDiariaCmp();
				fOperMetaContEfetivo = paramCampanhaForm.getNrMetaDiariaOpr();

				fCampNaoAdesao = paramCampanhaForm.getNrNaoAderiuCmp();
				fOperNaoAdesao = paramCampanhaForm.getNrNaoAderiuOpr();

				iPublicoTotal = paramCampanhaForm.getNrPublicoTotal();
				fTMA = paramCampanhaForm.getNrTempoMedio();

				if (paramCampanhaForm.getCanalUtil() != null) {
					canalDispSelecionado = String.valueOf(paramCampanhaForm.getCanalUtil().getApoioCanalVOArray(0).getIdCanalCampanha());
				}

			}

		}

		public void setiAcao(Integer iAcao) {
			this.iAcao = iAcao;
		}

		public Integer getiAcao() {
			return this.iAcao;
		}

		public void setsCanal(String sCanal) {
			this.sCanal = sCanal;
		}

		public String getsCanal() {
			return this.sCanal;
		}

		public void setiIdCanal(String iIdCanal) {
			this.iIdCanal = iIdCanal;
		}

		public String getiIdCanal() {
			return this.iIdCanal;
		}

		public void setOperacao(String operacao) {
			this.operacao = operacao;
		}

		public String getOperacao() {
			return this.operacao;
		}
	}

	public static class CopiaPerguntasActionForm extends ActionForm {

		private static final long serialVersionUID = -5260864780919049601L;

		private String versaoDestSelecionada;
		private ApoioVO[] listaVersaoDest;
		private String sMsg = ConstantesCRM.SVAZIO;
		private Integer idAcao = new Integer(0);
		private ApoioVO[] listaCampanhaOrig = new ApoioVO[0];
		private String campanhaOrigSelecionada;
		private ApoioVO[] listaSubCampanhaOrig = new ApoioVO[0];
		private String subCampanhaOrigSelecionada;
		private ApoioVO[] listaCanalOrig = new ApoioVO[0];
		private String canalSelecionadoOrig;
		private ApoioVO[] listaCampanhaDest = new ApoioVO[0];
		private String campanhaDestSelecionada;
		private ApoioVO[] listaSubCampanhaDest = new ApoioVO[0];
		private String subCampanhaDestSelecionada;
		private ApoioVO[] listaCanalDest = new ApoioVO[0];
		private String canalSelecionadoDest;

		/****************************** Listas de Origem ********************************************************************************/

		public void setListaCampanhaOrig(ApoioVO[] listaCampanhaOrig) {
			this.listaCampanhaOrig = listaCampanhaOrig;
		}

		public ApoioVO[] getListaCampanhaOrig() {
			return this.listaCampanhaOrig;
		}

		public void setCampanhaOrigSelecionada(String campanhaOrigSelecionada) {
			this.campanhaOrigSelecionada = campanhaOrigSelecionada;
		}

		public String getCampanhaOrigSelecionada() {
			return this.campanhaOrigSelecionada;
		}

		public void setListaSubCampanhaOrig(ApoioVO[] listaSubCampanhaOrig) {
			this.listaSubCampanhaOrig = listaSubCampanhaOrig;
		}

		public ApoioVO[] getListaSubCampanhaOrig() {
			return this.listaSubCampanhaOrig;
		}

		public void setSubCampanhaOrigSelecionada(String subCampanhaOrigSelecionada) {
			this.subCampanhaOrigSelecionada = subCampanhaOrigSelecionada;
		}

		public String getSubCampanhaOrigSelecionada() {
			return this.subCampanhaOrigSelecionada;
		}

		public void setListaCanalOrig(ApoioVO[] listaCanalOrig) {
			this.listaCanalOrig = listaCanalOrig;
		}

		public ApoioVO[] getListaCanalOrig() {
			return this.listaCanalOrig;
		}

		public void setCanalSelecionadoOrig(String canalSelecionadoOrig) {
			this.canalSelecionadoOrig = canalSelecionadoOrig;
		}

		public String getCanalSelecionadoOrig() {
			return this.canalSelecionadoOrig;
		}

		/*********************************************** Listas de Destino ***************************************************************************/

		public void setListaCampanhaDest(ApoioVO[] lista) {
			this.listaCampanhaDest = lista;
		}

		public ApoioVO[] getListaCampanhaDest() {
			return this.listaCampanhaDest;
		}

		public void setCampanhaDestSelecionada(String s) {
			this.campanhaDestSelecionada = s;
		}

		public String getCampanhaDestSelecionada() {
			return this.campanhaDestSelecionada;
		}

		public void setListaSubCampanhaDest(ApoioVO[] lista) {
			this.listaSubCampanhaDest = lista;
		}

		public ApoioVO[] getListaSubCampanhaDest() {
			return this.listaSubCampanhaDest;
		}

		public void setSubCampanhaDestSelecionada(String s) {
			this.subCampanhaDestSelecionada = s;
		}

		public String getSubCampanhaDestSelecionada() {
			return this.subCampanhaDestSelecionada;
		}

		public void setListaCanalDest(ApoioVO[] lista) {
			this.listaCanalDest = lista;
		}

		public ApoioVO[] getListaCanalDest() {
			return this.listaCanalDest;
		}

		public void setCanalSelecionadoDest(String s) {
			this.canalSelecionadoDest = s;
		}

		public String getCanalSelecionadoDest() {
			return this.canalSelecionadoDest;
		}

		public void setSMsg(String s) {
			this.sMsg = s;
		}

		public String getSMsg() {
			return this.sMsg;
		}

		public void setIdAcao(Integer i) {
			this.idAcao = i;
		}

		public Integer getIdAcao() {
			return this.idAcao;
		}

		public void setListaVersaoDest(ApoioVO[] listaVersaoDest) {
			this.listaVersaoDest = listaVersaoDest;
		}

		public ApoioVO[] getListaVersaoDest() {
			if (this.listaVersaoDest == null) {
				this.listaVersaoDest = new ApoioVO[0];
			}

			return this.listaVersaoDest;
		}

		public void setVersaoDestSelecionada(String versaoDestSelecionada) {
			this.versaoDestSelecionada = versaoDestSelecionada;
		}

		public String getVersaoDestSelecionada() {
			return this.versaoDestSelecionada;
		}
	}
}
