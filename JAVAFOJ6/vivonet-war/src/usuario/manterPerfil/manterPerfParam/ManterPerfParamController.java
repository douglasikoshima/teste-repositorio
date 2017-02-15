package usuario.manterPerfil.manterPerfParam;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.usuario.manterGrupo.ManterGrupoFacade;
import br.com.vivo.fo.ctrls.usuario.manterSistema.ManterSistemaFacade;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.GrupamentosExistentesUsuarioVODocument.GrupamentosExistentesUsuarioVO;
import br.com.vivo.fo.usuario.vo.GrupamentosRelacionadosUsuarioVODocument.GrupamentosRelacionadosUsuarioVO;
import br.com.vivo.fo.usuario.vo.GrupamentosUsuarioVODocument.GrupamentosUsuarioVO;
import br.com.vivo.fo.usuario.vo.ListaParamGrupamUnidVODocument.ListaParamGrupamUnidVO;
import br.com.vivo.fo.usuario.vo.PaginaSimplVODocument.PaginaSimplVO;
import br.com.vivo.fo.usuario.vo.PerfilGrupamentoUsuarioVODocument.PerfilGrupamentoUsuarioVO;
import br.com.vivo.fo.usuario.vo.PerfilUnidadeUsuarioVODocument.PerfilUnidadeUsuarioVO;
import br.com.vivo.fo.usuario.vo.PerfilUnidadeUsuarioVODocument.PerfilUnidadeUsuarioVO.UnidadesRelacionadas;
import br.com.vivo.fo.usuario.vo.PerfilUsuarioVODocument.PerfilUsuarioVO;
import br.com.vivo.fo.usuario.vo.PerfisUsuarioVODocument.PerfisUsuarioVO;
import br.com.vivo.fo.usuario.vo.SistemaIDVODocument.SistemaIDVO;
import br.com.vivo.fo.usuario.vo.SistemasSubSistPaginasVODocument.SistemasSubSistPaginasVO;
import br.com.vivo.fo.usuario.vo.SubSistemaPaginasVODocument.SubSistemaPaginasVO;
import br.com.vivo.fo.usuario.vo.SubSistemaUsuarioVODocument.SubSistemaUsuarioVO;
import br.com.vivo.fo.usuario.vo.SubSistemasUsuarioVODocument.SubSistemasUsuarioVO;
import br.com.vivo.fo.usuario.vo.UnidadeSimplVODocument.UnidadeSimplVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ManterPerfParamController extends AbstractAction {

	private static final long serialVersionUID = 8270853413791609751L;

	@EJB
	private ManterSistemaFacade controlSistema;

	@EJB
	private ManterGrupoFacade controlGrupoPerfil;

	private ListaPerfisForm lPerfilForm = new ListaPerfisForm();

	private RelPerfilUnidForm relPerfilUnidForm = new RelPerfilUnidForm();

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("usuario");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("listaPerfis".equals(mapping.getParameter())) {
			return listaPerfis(mapping, form, request, response);
		} else if ("chamaEdicaoPerfil".equals(mapping.getParameter())) {
			return chamaEdicaoPerfil(mapping, form, request, response);
		} else if ("salvaPerfil".equals(mapping.getParameter())) {
			return salvaPerfil(mapping, form, request, response);
		} else if ("removePerfil".equals(mapping.getParameter())) {
			return removePerfil(mapping, form, request, response);
		} else if ("editarRelPerfil".equals(mapping.getParameter())) {
			return editarRelPerfil(mapping, form, request, response);
		} else if ("listaSistemas".equals(mapping.getParameter())) {
			return listaSistemas(mapping, form, request, response);
		} else if ("obtemSubSistemas".equals(mapping.getParameter())) {
			return obtemSubSistemas(mapping, form, request, response);
		} else if ("listaPaginasSub".equals(mapping.getParameter())) {
			return listaPaginasSub(mapping, form, request, response);
		} else if ("obtemUnidades".equals(mapping.getParameter())) {
			return obtemUnidades(mapping, form, request, response);
		} else if ("salvaGrupamento".equals(mapping.getParameter())) {
			return salvaGrupamento(mapping, form, request, response);
		} else if ("salvaUnidade".equals(mapping.getParameter())) {
			return salvaUnidade(mapping, form, request, response);
		} else if ("alterarDsPerfil".equals(mapping.getParameter())) {
			return alterarDsPerfil(mapping, form, request, response);
		} else if ("relacionarPerfilGrupam".equals(mapping.getParameter())) {
			return relacionarPerfilGrupam(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisarPerfil.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterPerfParamController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		try {
			if (lPerfilForm == null) {
				lPerfilForm = new ListaPerfisForm();
			}

			lPerfilForm.setDsPerfil(ConstantesCRM.SVAZIO);
			if (lPerfilForm.getArrayPerfisVO() != null && lPerfilForm.getArrayPerfisVO().getPerfilUsuarioVOArray() != null && lPerfilForm.getArrayPerfisVO().getPerfilUsuarioVOArray().length > 0) {
				lPerfilForm.setIndicativoOperacao("Resultado");
			}

			relPerfilUnidForm = new RelPerfilUnidForm();
			lPerfilForm.setMsgError(ConstantesCRM.SVAZIO);

		} catch (Exception e) {
			log.error("ManterPerfParamController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
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
	 * @jpf:forward name="success" path="pesquisarPerfil.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward listaPerfis(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ListaPerfisForm form = (ListaPerfisForm) formParam;

		log.debug("ManterPerfParamController:listaPerfis" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		lPerfilForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			PerfisUsuarioVO perfis = PerfisUsuarioVO.Factory.newInstance();
			PerfilUsuarioVO perfil = PerfilUsuarioVO.Factory.newInstance();

			perfil.setDsPerfil(form.getDsPerfil());
			lPerfilForm.setPerfilUsuarioVO(perfil);

			// Chama o servico de pesquisa
			perfis = controlGrupoPerfil.pesquisaPerfil(perfil, ConstantesCRM.getCurrentUser(request.getSession()));

			// Atualiza o formulario com o resultado da pesquisa
			lPerfilForm.setParametroPesquisa(form.getParametroPesquisa());
			lPerfilForm.setArrayPerfisVO(perfis);

			if (perfis.getPerfilUsuarioVOArray().length > 0) {
				lPerfilForm.setIndicativoOperacao("Resultado");
			} else {
				lPerfilForm.setIndicativoOperacao("Vazio");
			}

			lPerfilForm.perfilUsuarioVO.setIdPerfil("00");
			lPerfilForm.setDsPerfil(perfil.getDsPerfil());

		} catch (TuxedoWarningException twe) {
			log.warn("ManterPerfParamController:listaPerfis" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			lPerfilForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterPerfParamController:listaPerfis" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="pesquisarPerfil.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward chamaEdicaoPerfil(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterPerfParamController:chamaEdicaoPerfil" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		lPerfilForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			PerfilUsuarioVO perfil = null;
			PerfisUsuarioVO perfis = null;

			String idPerfil = request.getParameter("idPerfil");

			perfil = lPerfilForm.getPerfilVOById(idPerfil, lPerfilForm.getArrayPerfisVO().getPerfilUsuarioVOArray());

			lPerfilForm.setIdPerfil(perfil.getIdPerfil());
			lPerfilForm.setDsPerfil(perfil.getDsPerfil());

		} catch (Exception e) {
			log.error("ManterPerfParamController:chamaEdicaoPerfil" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
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
	 * @jpf:forward name="success" path="pesquisarPerfil.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaPerfil(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ListaPerfisForm form = (ListaPerfisForm) formParam;

		log.debug("ManterPerfParamController:salvaPerfil" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		lPerfilForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			PerfilUsuarioVO perfil = PerfilUsuarioVO.Factory.newInstance();
			PerfisUsuarioVO perfis = null;
			String inInclui = (request.getParameter(ConstantesCRM.SACTION) != null) ? request.getParameter(ConstantesCRM.SACTION) : ConstantesCRM.SVAZIO;
			perfil.setIdPerfil(form.getIdPerfil());
			perfil.setDsPerfil(form.getDsPerfil());

			// Verifica se é uma edição ou insercao
			if (!inInclui.trim().equals(ConstantesCRM.SVAZIO) && inInclui.trim().equals("incluir")) {
				perfil.setIdPerfil(ConstantesCRM.SVAZIO);
				perfis = controlGrupoPerfil.cadastraPerfil(perfil, ConstantesCRM.getCurrentUser(request.getSession()));
			} else {
				perfis = controlGrupoPerfil.editaPerfil(perfil, ConstantesCRM.getCurrentUser(request.getSession()));
			}

			// atualiza as variaveis dos formularios
			if (perfis.getPerfilUsuarioVOArray().length > 0) {
				lPerfilForm.setIndicativoOperacao("Resultado");
			} else {
				lPerfilForm.setIndicativoOperacao("Vazio");
			}
			lPerfilForm.setDsPerfil(ConstantesCRM.SVAZIO);
			lPerfilForm.perfilUsuarioVO.setIdPerfil("00");
			lPerfilForm.setArrayPerfisVO(perfis);
			lPerfilForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("ManterPerfParamController:salvaPerfil" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			lPerfilForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterPerfParamController:salvaPerfil" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="pesquisarPerfil.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removePerfil(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ListaPerfisForm form = (ListaPerfisForm) formParam;

		log.debug("ManterPerfParamController:removePerfil" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		lPerfilForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			PerfilUsuarioVO perfil = PerfilUsuarioVO.Factory.newInstance();
			form.getArrayPerfisVO().getPerfilUsuarioVOArray();

			perfil.setIdPerfil(form.getIdPerfil());

			// Remove o perfil
			controlGrupoPerfil.removePerfil(perfil, ConstantesCRM.getCurrentUser(request.getSession()));

			perfil.setIdPerfil(ConstantesCRM.SVAZIO);
			perfil.setDsPerfil(lPerfilForm.getDsPerfil());

			// Atualiza a lista de resultados
			PerfisUsuarioVO perfis = controlGrupoPerfil.pesquisaPerfil(perfil, ConstantesCRM.getCurrentUser(request.getSession()));

			if (perfis.getPerfilUsuarioVOArray().length > 0) {
				lPerfilForm.setIndicativoOperacao("Resultado");
			} else {
				lPerfilForm.setIndicativoOperacao("Vazio");
			}

			lPerfilForm.perfilUsuarioVO.setIdPerfil("00");
			lPerfilForm.setArrayPerfisVO(perfis);
			lPerfilForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("ManterPerfParamController:removePerfil" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			lPerfilForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterPerfParamController:removePerfil" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="editarRelPerfil.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward editarRelPerfil(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ListaPerfisForm form = (ListaPerfisForm) formParam;

		log.debug("ManterPerfParamController:editarRelPerfil" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		lPerfilForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			PerfilUsuarioVO perfil = PerfilUsuarioVO.Factory.newInstance();

			// Recupera o registro do perfil clicado
			String idPerfil = request.getParameter("perfilID");
			perfil = lPerfilForm.getPerfilVOById(idPerfil, lPerfilForm.getArrayPerfisVO().getPerfilUsuarioVOArray());

			lPerfilForm.setPerfilUsuarioVO(perfil);

			form.getArrayPerfisVO().getPerfilUsuarioVOArray();

			// lPerfilForm.setArrayPerfisVO(form.getArrayPerfisVO());
			lPerfilForm.setParametroPesquisa(form.getParametroPesquisa());
			form.setPerfilUsuarioVO(perfil);
			form.setDsPerfil(perfil.getDsPerfil());
			lPerfilForm.setDsPerfil(perfil.getDsPerfil());
			form.setIdPerfil(perfil.getIdPerfil().toString());
			lPerfilForm.setIdPerfil(perfil.getIdPerfil().toString());
			form.setIndicativoOperacao("grupamento");
			lPerfilForm.setIndicativoOperacao("grupamento");
			// lPerfilForm.setExibeUnidade("nao");

			GrupamentosUsuarioVO grupamento = GrupamentosUsuarioVO.Factory.newInstance();

			grupamento = controlGrupoPerfil.listaRelacionarPerfilGrupamento(perfil, ConstantesCRM.getCurrentUser(request.getSession()));

			lPerfilForm.setArrayGrupamentosExistentes(grupamento.getGrupamentosExistentesUsuarioVOArray());
			lPerfilForm.setArrayGrupamentosRelacionados(grupamento.getGrupamentosRelacionadosUsuarioVOArray());
			form.setArrayGrupamentosExistentes(grupamento.getGrupamentosExistentesUsuarioVOArray());
			form.setArrayGrupamentosRelacionados(grupamento.getGrupamentosRelacionadosUsuarioVOArray());

		} catch (TuxedoWarningException twe) {
			log.warn("ManterPerfParamController:editarRelPerfil" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			lPerfilForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterPerfParamController:editarRelPerfil" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/*
	 * Esta acao preenche o combo de sistemas
	 */
	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relacionarPerfilUnidade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward listaSistemas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ListaPerfisForm form = (ListaPerfisForm) formParam;

		log.debug("ManterPerfParamController:listaSistemas" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		ActionForward retorno = mapping.findForward(ConstantesCRM.SUCCESS);
		// if (relPerfilUnidForm == null)
		relPerfilUnidForm = new RelPerfilUnidForm();
		relPerfilUnidForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			// Inicializa as variavais da aba Relacionar Unidades a Perfil
			PerfilUnidadeUsuarioVO perfilUnidadeUsuarioVO = PerfilUnidadeUsuarioVO.Factory.newInstance();
			perfilUnidadeUsuarioVO.setIdPerfil(lPerfilForm.getIdPerfil());
			relPerfilUnidForm.setIdPerfil(lPerfilForm.getIdPerfil());

			ListaParamGrupamUnidVO listaParamGrupamUnidVO = controlSistema.listaParamRelGrupamUnidades(ConstantesCRM.getCurrentUser(request.getSession()));

			relPerfilUnidForm.setIdPagina("000");
			// Atualiza a variavel que armazena a lista de Grupamentos, Sistemas e SubSistemas
			if (listaParamGrupamUnidVO.getSistemasSubSistPaginasVOArray().length > 0) {
				// monta a lista de Sistemas
				relPerfilUnidForm.setListaSistemas(listaParamGrupamUnidVO.getSistemasSubSistPaginasVOArray());

				// monta a lista de SubSistemas
				relPerfilUnidForm.setListaSistemasSubSistemasPaginas(listaParamGrupamUnidVO.getSistemasSubSistPaginasVOArray());
			}

		} catch (TuxedoWarningException twe) {
			log.warn("ManterPerfParamController:listaSistemas" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			relPerfilUnidForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterPerfParamController:listaSistemas" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		// Indica que a montagem do select de subsistemas será feito por javascript
		relPerfilUnidForm.setExibeStruts("nao");
		relPerfilUnidForm.setExibePagina("nao");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return retorno;
	}

	private void montaListaSubSistema(SistemasSubSistPaginasVO[] sistemasSubSistPaginasVO) throws Exception {
		// monta a lista de subsistemas
		if (relPerfilUnidForm.getIdSistema() != null) {
			SistemasSubSistPaginasVO sistemaSubSistemas = getSistemaById(relPerfilUnidForm.getIdSistema(), relPerfilUnidForm.getListaSistemas());
			if (sistemaSubSistemas.getSubSistemaPaginasVOArray().length > 0) {
				relPerfilUnidForm.setListaSubSistemas(sistemaSubSistemas.getSubSistemaPaginasVOArray());
			}

			relPerfilUnidForm.setExibeStruts("sim");
		} else {
			relPerfilUnidForm.setExibeStruts("nao");
		}

		relPerfilUnidForm.setExibePagina("nao");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relacionarPerfilUnidade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward obtemSubSistemas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		RelPerfilUnidForm form = (RelPerfilUnidForm) formParam;

		log.debug("ManterPerfParamController:obtemSubSistemas" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		relPerfilUnidForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			SistemaIDVO sistemaIDVO = SistemaIDVO.Factory.newInstance();
			sistemaIDVO.setIdSistema(form.getIdSistema());

			SubSistemasUsuarioVO subSistemasUsuarioVO = SubSistemasUsuarioVO.Factory.newInstance();
			subSistemasUsuarioVO = controlSistema.listaSubSistemasPorSistema(sistemaIDVO, ConstantesCRM.getCurrentUser(request.getSession()));
			relPerfilUnidForm.setListaSubSistemasUsuario(subSistemasUsuarioVO.getSubSistemaUsuarioVOArray());

			// this.listaSistemas();
			relPerfilUnidForm.setIdSistema(form.getIdSistema());
			relPerfilUnidForm.setIdSubSistema(form.getIdSubSistema());
			relPerfilUnidForm.setIdPerfil(lPerfilForm.getIdPerfil());

			// Limpa as listas de unidades existentes e relacionadas.
			relPerfilUnidForm.setListaUnidadesExist(new UnidadeSimplVO[0]);
			relPerfilUnidForm.setListaUnidadesRel(new UnidadeSimplVO[0]);

			montaListaSubSistema(relPerfilUnidForm.getListaSistemasSubSistemasPaginas());

		} catch (TuxedoWarningException twe) {
			log.warn("ManterPerfParamController:obtemSubSistemas" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			relPerfilUnidForm.setMsgError(twe.getXmlHeader().getStatusText());
		} catch (Exception e) {
			log.error("ManterPerfParamController:obtemSubSistemas" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		relPerfilUnidForm.setExibeStruts("sim");
		relPerfilUnidForm.setExibePagina("nao");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/*
	 * Esta acao preenche o combo de paginas do sub-sistema selecionado
	 */
	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relacionarPerfilUnidade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward listaPaginasSub(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		RelPerfilUnidForm form = (RelPerfilUnidForm) formParam;

		log.debug("ManterPerfParamController:listaPaginasSub" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		ActionForward retorno = mapping.findForward(ConstantesCRM.SUCCESS);
		relPerfilUnidForm.setMsgError("");
		try {
			// Recupera o id do SubSistema
			SubSistemaUsuarioVO subSistemaUsuarioVO = SubSistemaUsuarioVO.Factory.newInstance();
			subSistemaUsuarioVO.setIdSubSistema(form.getIdSubSistema());

			// Grava o id do Sistema e SubSistema
			relPerfilUnidForm.setIdSistema(form.getIdSistema());
			relPerfilUnidForm.setIdSubSistema(form.getIdSubSistema());
			relPerfilUnidForm.setIdPerfil(lPerfilForm.getIdPerfil());

			// Chama o serviço que lista as páginas dado um id de subsistema
			SubSistemaPaginasVO subSistemaPaginasVO = controlSistema.listaPaginasPorIdSubSistema(subSistemaUsuarioVO, ConstantesCRM.getCurrentUser(request.getSession()));

			// Atualiza as variaveis do formulario
			if (subSistemaPaginasVO.getPaginaSimplVOArray().length > 0) {
				relPerfilUnidForm.setListaPaginas(subSistemaPaginasVO.getPaginaSimplVOArray());
			} else {
				relPerfilUnidForm.setListaPaginas(new PaginaSimplVO[0]);
			}

			// Indica que a montagem do select de subsistemas será feito por struts
			relPerfilUnidForm.setExibeStruts("sim");

			// monta a funcao javascript
			montaListaSubSistema(relPerfilUnidForm.getListaSistemasSubSistemasPaginas());

			// monta a lista de subsistemas
			SistemasSubSistPaginasVO sistemaSubSistemas = getSistemaById(form.getIdSistema(), relPerfilUnidForm.getListaSistemas());
			if (sistemaSubSistemas.getSubSistemaPaginasVOArray().length > 0) {
				relPerfilUnidForm.setListaSubSistemas(sistemaSubSistemas.getSubSistemaPaginasVOArray());
			}

			// Limpa as listas de unidades existentes e relacionadas.
			relPerfilUnidForm.setListaUnidadesExist(new UnidadeSimplVO[0]);
			relPerfilUnidForm.setListaUnidadesRel(new UnidadeSimplVO[0]);
		} catch (TuxedoWarningException twe) {
			log.warn("ManterPerfParamController:listaPaginasSub" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			relPerfilUnidForm.setMsgError(twe.getXmlHeader().getStatusText());
		} catch (Exception e) {
			log.error("ManterPerfParamController:listaPaginasSub" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		relPerfilUnidForm.setExibeStruts("sim");
		relPerfilUnidForm.setExibePagina("sim");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return retorno;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relacionarPerfilUnidade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward obtemUnidades(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		RelPerfilUnidForm form = (RelPerfilUnidForm) formParam;

		log.debug("ManterPerfParamController:obtemUnidades" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		ActionForward retorno = mapping.findForward(ConstantesCRM.SUCCESS);
		relPerfilUnidForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			PerfilUnidadeUsuarioVO perfilUnidadeUsuarioVO = PerfilUnidadeUsuarioVO.Factory.newInstance();
			relPerfilUnidForm.setIdPagina(form.getIdPagina());
			perfilUnidadeUsuarioVO.setIdPagina(form.getIdPagina());
			perfilUnidadeUsuarioVO.setIdPerfil(lPerfilForm.getIdPerfil());

			PerfilUnidadeUsuarioVO listaUnidades = controlGrupoPerfil.listaRelacionarPerfilUnidades(perfilUnidadeUsuarioVO, ConstantesCRM.getCurrentUser(request.getSession()));

			// Grava as listas de unidades existentes e relacionadas.
			if (listaUnidades.getUnidadesExistentes().getUnidadeSimplVOArray().length > 0) {
				relPerfilUnidForm.setListaUnidadesExist(listaUnidades.getUnidadesExistentes().getUnidadeSimplVOArray());
			} else {
				relPerfilUnidForm.setListaUnidadesExist(new UnidadeSimplVO[0]);
			}
			if (listaUnidades.getUnidadesRelacionadas().getUnidadeSimplVOArray().length > 0) {
				relPerfilUnidForm.setListaUnidadesRel(listaUnidades.getUnidadesRelacionadas().getUnidadeSimplVOArray());
			} else {
				relPerfilUnidForm.setListaUnidadesRel(new UnidadeSimplVO[0]);
			}
			// monta a funcao javascript
			montaListaSubSistema(relPerfilUnidForm.getListaSistemasSubSistemasPaginas());

		} catch (TuxedoWarningException twe) {
			log.warn("ManterPerfParamController:listaPaginasSub" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			relPerfilUnidForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterPerfParamController:listaPaginasSub" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		relPerfilUnidForm.setExibeStruts("sim");
		relPerfilUnidForm.setExibePagina("sim");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return retorno;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relacionarPerfilGrupam.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaGrupamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ListaPerfisForm form = (ListaPerfisForm) formParam;

		log.debug("ManterPerfParamController:salvaGrupamento" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		lPerfilForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			String lista[] = form.getArraySelecaoRelacionados();
			PerfilGrupamentoUsuarioVO perfilGrupamentoUsuarioVO = PerfilGrupamentoUsuarioVO.Factory.newInstance();

			for (int i = 0; i < form.getArraySelecaoRelacionados().length; i++) {
				GrupamentosRelacionadosUsuarioVO grupamentosRelacionadosUsuarioVO = GrupamentosRelacionadosUsuarioVO.Factory.newInstance();
				grupamentosRelacionadosUsuarioVO.setIdGrupamento(lista[i]);
				grupamentosRelacionadosUsuarioVO.setDsGrupamento(ConstantesCRM.SVAZIO);
				perfilGrupamentoUsuarioVO.insertNewGrupamentosRelacionadosUsuarioVO(i);
				perfilGrupamentoUsuarioVO.setGrupamentosRelacionadosUsuarioVOArray(i, grupamentosRelacionadosUsuarioVO);
			}

			perfilGrupamentoUsuarioVO.setIdPerfil(lPerfilForm.getIdPerfil());
			GrupamentosUsuarioVO grupamento = controlGrupoPerfil.salvaPerfilGrupamentoRelacionados(perfilGrupamentoUsuarioVO, ConstantesCRM.getCurrentUser(request.getSession()));

			PerfilUsuarioVO perfil = PerfilUsuarioVO.Factory.newInstance();
			perfil.setIdPerfil(lPerfilForm.getIdPerfil());
			perfil.setDsPerfil(lPerfilForm.getDsPerfil());

			lPerfilForm.setPerfilUsuarioVO(perfil);
			// lPerfilForm.setArrayPerfisVO(form.getArrayPerfisVO());
			lPerfilForm.setParametroPesquisa(form.getParametroPesquisa());
			form.setPerfilUsuarioVO(perfil);
			form.setDsPerfil(perfil.getDsPerfil());
			lPerfilForm.setDsPerfil(perfil.getDsPerfil());
			form.setIdPerfil(perfil.getIdPerfil().toString());
			lPerfilForm.setIdPerfil(perfil.getIdPerfil().toString());
			form.setIndicativoOperacao("grupamento");
			lPerfilForm.setIndicativoOperacao("grupamento");

			lPerfilForm.setArrayGrupamentosExistentes(grupamento.getGrupamentosExistentesUsuarioVOArray());
			lPerfilForm.setArrayGrupamentosRelacionados(grupamento.getGrupamentosRelacionadosUsuarioVOArray());
			form.setArrayGrupamentosExistentes(grupamento.getGrupamentosExistentesUsuarioVOArray());
			form.setArrayGrupamentosRelacionados(grupamento.getGrupamentosRelacionadosUsuarioVOArray());
			lPerfilForm.setMsgError(ConstantesCRM.SSucesso);
		} catch (TuxedoWarningException twe) {
			log.warn("ManterPerfParamController:salvaGrupamento" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			lPerfilForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterPerfParamController:salvaGrupamento" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="relacionarPerfilUnidade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaUnidade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		RelPerfilUnidForm form = (RelPerfilUnidForm) formParam;

		log.debug("ManterPerfParamController:salvaUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		ActionForward retorno = mapping.findForward(ConstantesCRM.SUCCESS);
		relPerfilUnidForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			String lista[] = form.getUnidadesRel();

			PerfilUnidadeUsuarioVO perfilUnidadeUsuarioVO = PerfilUnidadeUsuarioVO.Factory.newInstance();
			perfilUnidadeUsuarioVO.setIdPerfil(lPerfilForm.getIdPerfil());
			perfilUnidadeUsuarioVO.setIdPagina(form.getIdPagina());

			UnidadeSimplVO[] unidadesSimplVO = new UnidadeSimplVO[lista.length];
			for (int i = 0; i < form.getUnidadesRel().length; i++) {
				unidadesSimplVO[i] = UnidadeSimplVO.Factory.newInstance();
				unidadesSimplVO[i].setIdUnidade(lista[i]);
				unidadesSimplVO[i].setNmUnidade(ConstantesCRM.SVAZIO);
			}

			perfilUnidadeUsuarioVO.setUnidadesRelacionadas(UnidadesRelacionadas.Factory.newInstance());
			perfilUnidadeUsuarioVO.getUnidadesRelacionadas().setUnidadeSimplVOArray(unidadesSimplVO);

			PerfilUnidadeUsuarioVO listaRetorno = controlGrupoPerfil.salvaPerfilUnidadesRelacionadas(perfilUnidadeUsuarioVO, ConstantesCRM.getCurrentUser(request.getSession()));

			relPerfilUnidForm.setListaUnidadesExist(listaRetorno.getUnidadesExistentes().getUnidadeSimplVOArray());

			relPerfilUnidForm.setListaUnidadesRel(listaRetorno.getUnidadesRelacionadas().getUnidadeSimplVOArray());

			PerfilUnidadeUsuarioVO newPerfilUnidadeUsuarioVO = PerfilUnidadeUsuarioVO.Factory.newInstance();
			relPerfilUnidForm.setIdPagina(form.getIdPagina());
			newPerfilUnidadeUsuarioVO.setIdPagina(form.getIdPagina());
			newPerfilUnidadeUsuarioVO.setIdPerfil(lPerfilForm.getIdPerfil());

			// Recupera o id do SubSistema
			SubSistemaUsuarioVO subSistemaUsuarioVO = SubSistemaUsuarioVO.Factory.newInstance();
			subSistemaUsuarioVO.setIdSubSistema(form.getIdSubSistema());

			// Grava o id do Sistema e SubSistema
			relPerfilUnidForm.setIdSistema(form.getIdSistema());
			relPerfilUnidForm.setIdSubSistema(form.getIdSubSistema());
			relPerfilUnidForm.setIdPerfil(lPerfilForm.getIdPerfil());

			// Chama o serviço que lista as páginas dado um id de subsistema
			SubSistemaPaginasVO subSistemaPaginasVO = controlSistema.listaPaginasPorIdSubSistema(subSistemaUsuarioVO, ConstantesCRM.getCurrentUser(request.getSession()));

			// Atualiza as variaveis do formulario
			if (subSistemaPaginasVO.getPaginaSimplVOArray().length > 0) {
				relPerfilUnidForm.setListaPaginas(subSistemaPaginasVO.getPaginaSimplVOArray());
			} else {
				relPerfilUnidForm.setListaPaginas(new PaginaSimplVO[0]);
			}

			// Indica que a montagem do select de subsistemas será feito por struts
			relPerfilUnidForm.setExibeStruts("sim");

			// monta a funcao javascript
			montaListaSubSistema(relPerfilUnidForm.getListaSistemasSubSistemasPaginas());

			// monta a lista de subsistemas
			SistemasSubSistPaginasVO sistemaSubSistemas = getSistemaById(form.getIdSistema(), relPerfilUnidForm.getListaSistemas());
			if (sistemaSubSistemas.getSubSistemaPaginasVOArray().length > 0) {
				relPerfilUnidForm.setListaSubSistemas(sistemaSubSistemas.getSubSistemaPaginasVOArray());
			}

			PerfilUnidadeUsuarioVO listaUnidades = controlGrupoPerfil.listaRelacionarPerfilUnidades(perfilUnidadeUsuarioVO, ConstantesCRM.getCurrentUser(request.getSession()));

			// Grava as listas de unidades existentes e relacionadas.
			if (listaUnidades.getUnidadesExistentes().getUnidadeSimplVOArray().length > 0) {
				relPerfilUnidForm.setListaUnidadesExist(listaUnidades.getUnidadesExistentes().getUnidadeSimplVOArray());
			} else {
				relPerfilUnidForm.setListaUnidadesExist(new UnidadeSimplVO[0]);
			}
			if (listaUnidades.getUnidadesRelacionadas().getUnidadeSimplVOArray().length > 0) {
				relPerfilUnidForm.setListaUnidadesRel(listaUnidades.getUnidadesRelacionadas().getUnidadeSimplVOArray());
			} else {
				relPerfilUnidForm.setListaUnidadesRel(new UnidadeSimplVO[0]);
			}
			// monta a funcao javascript
			montaListaSubSistema(relPerfilUnidForm.getListaSistemasSubSistemasPaginas());
			lPerfilForm.setMsgError(ConstantesCRM.SSucesso);
		} catch (TuxedoWarningException twe) {
			log.warn("ManterPerfParamController:salvaUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			relPerfilUnidForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterPerfParamController:salvaUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		relPerfilUnidForm.setExibeStruts("sim");
		relPerfilUnidForm.setExibePagina("sim");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return retorno;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="IncluirAlterarPerfil.jsp"
	 */
	public ActionForward alterarDsPerfil(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ListaPerfisForm form = (ListaPerfisForm) formParam;
		log.debug("ManterPerfParamController:alterarDsPerfil" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		lPerfilForm.setMsgError(ConstantesCRM.SVAZIO);
		String idArray = null;
		idArray = request.getParameter("idArray");
		if(idArray!=null){
			lPerfilForm.setDsPerfil(this.lPerfilForm.getArrayPerfisVO().getPerfilUsuarioVOArray(Integer.parseInt(idArray)).getDsPerfil());
			lPerfilForm.setIdPerfil(this.lPerfilForm.getArrayPerfisVO().getPerfilUsuarioVOArray(Integer.parseInt(idArray)).getIdPerfil());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relacionarPerfilGrupam.jsp"
	 */
	public ActionForward relacionarPerfilGrupam(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterPerfParamController:relacionarPerfilGrupam" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		lPerfilForm.setMsgError(ConstantesCRM.SVAZIO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class ListaPerfisForm extends ActionForm {

		private String msgError = ConstantesCRM.SVAZIO;

		private String[] arraySubSistemas;
		private String[] arraySelecaoRelacionados;
		private String[] arraySelecaoExistentes;
		private GrupamentosExistentesUsuarioVO[] arrayGrupamentosExistentes;
		private GrupamentosRelacionadosUsuarioVO[] arrayGrupamentosRelacionados;
		private String indicativoOperacao;
		private String idPerfil;
		private String dsPerfil;
		private String parametroPesquisa;
		private PerfisUsuarioVO arrayPerfisVO;
		private PerfilUsuarioVO perfilUsuarioVO;

		public ListaPerfisForm() {

			arrayPerfisVO = PerfisUsuarioVO.Factory.newInstance();
			perfilUsuarioVO = PerfilUsuarioVO.Factory.newInstance();
			perfilUsuarioVO.setIdPerfil("-1");

		}

		public void setPerfilUsuarioVO(PerfilUsuarioVO perfilUsuarioVO) {
			this.perfilUsuarioVO = perfilUsuarioVO;
		}

		public PerfilUsuarioVO getPerfilUsuarioVO() {

			if (this.perfilUsuarioVO == null) {
				this.perfilUsuarioVO = PerfilUsuarioVO.Factory.newInstance();
			}

			return this.perfilUsuarioVO;
		}

		public void setArrayPerfisVO(PerfisUsuarioVO arrayPerfisVO) {
			this.arrayPerfisVO = arrayPerfisVO;

		}

		public PerfisUsuarioVO getArrayPerfisVO() {

			if (this.arrayPerfisVO == null) {
				this.arrayPerfisVO = PerfisUsuarioVO.Factory.newInstance();
			}

			return this.arrayPerfisVO;
		}

		public void setParametroPesquisa(String parametroPesquisa) {

			this.parametroPesquisa = parametroPesquisa;
		}

		public String getParametroPesquisa() {

			return this.parametroPesquisa;
		}

		public void setIdPerfil(String idPerfil) {

			this.idPerfil = idPerfil;
		}

		public String getIdPerfil() {

			return this.idPerfil;
		}

		public void setIndicativoOperacao(String indicativoOperacao) {

			this.indicativoOperacao = indicativoOperacao;
		}

		public String getIndicativoOperacao() {

			return this.indicativoOperacao;
		}

		public void setArrayGrupamentosRelacionados(GrupamentosRelacionadosUsuarioVO[] arrayGrupamentosRelacionados) {

			this.arrayGrupamentosRelacionados = arrayGrupamentosRelacionados;
		}

		public GrupamentosRelacionadosUsuarioVO[] getArrayGrupamentosRelacionados() {

			if (this.arrayGrupamentosRelacionados == null) {
				this.arrayGrupamentosRelacionados = new GrupamentosRelacionadosUsuarioVO[1];
			}

			return this.arrayGrupamentosRelacionados;
		}

		public void setArrayGrupamentosExistentes(GrupamentosExistentesUsuarioVO[] arrayGrupamentosExistentes) {

			this.arrayGrupamentosExistentes = arrayGrupamentosExistentes;
		}

		public GrupamentosExistentesUsuarioVO[] getArrayGrupamentosExistentes() {
			if (this.arrayGrupamentosExistentes == null) {
				this.arrayGrupamentosExistentes = new GrupamentosExistentesUsuarioVO[1];
			}

			return this.arrayGrupamentosExistentes;
		}

		public void setArraySelecaoExistentes(String[] arraySelecaoExistentes) {
			this.arraySelecaoExistentes = arraySelecaoExistentes;
		}

		public String[] getArraySelecaoExistentes() {
			if (this.arraySelecaoExistentes == null || this.arraySelecaoExistentes.length == 0) {

				this.arraySelecaoExistentes = new String[1];
			}

			return this.arraySelecaoExistentes;
		}

		public void setArraySelecaoRelacionados(String[] arraySelecaoRelacionados) {
			this.arraySelecaoRelacionados = arraySelecaoRelacionados;
		}

		public String[] getArraySelecaoRelacionados() {
			if (this.arraySelecaoRelacionados == null || this.arraySelecaoRelacionados.length == 0) {

				this.arraySelecaoRelacionados = new String[1];
			}

			return this.arraySelecaoRelacionados;
		}

		public void setDsPerfil(String dsPerfil) {
			this.dsPerfil = dsPerfil;
		}

		public String getDsPerfil() {

			return this.dsPerfil;
		}

		public PerfilUsuarioVO getPerfilVOById(String id, PerfilUsuarioVO[] perfis) {
			for (int i = 0; i < perfis.length; i++) {
				if (perfis[i].getIdPerfil().equals(id)) {
					return perfis[i];
				}
			}
			return null;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}
	}

	public static class RelPerfilUnidForm extends ActionForm {
		private SistemasSubSistPaginasVO[] listaSistemasSubSistemasPaginas;

		private SubSistemaUsuarioVO[] listaSubSistemasUsuario;

		private String exibePagina;

		private String msgError;

		private String listaSubSistemasLength;

		private String[] unidadesRel;
		private String[] unidadesExist;
		private UnidadeSimplVO[] listaUnidadesRel;
		private UnidadeSimplVO[] listaUnidadesExist;
		private SubSistemaPaginasVO[] listaSubSistemas;
		private SistemasSubSistPaginasVO[] listaSistemas;
		private PaginaSimplVO[] listaPaginas;
		private String exibeStruts;
		private String idSubSistema;
		private String idSistema;
		private String idPagina;
		private String idPerfil;

		public RelPerfilUnidForm() {

			// //////////////////////////////////
			// Bloco de Inicializacao - Begin //
			// //////////////////////////////////
			idPerfil = ConstantesCRM.SVAZIO;
			idPagina = ConstantesCRM.SVAZIO;
			idSistema = ConstantesCRM.SVAZIO;
			idSubSistema = ConstantesCRM.SVAZIO;
			exibeStruts = ConstantesCRM.SVAZIO;
			listaPaginas = new PaginaSimplVO[0];
			listaSistemas = new SistemasSubSistPaginasVO[0];
			listaSubSistemas = new SubSistemaPaginasVO[0];
			listaUnidadesExist = new UnidadeSimplVO[0];
			listaUnidadesRel = new UnidadeSimplVO[0];
			unidadesExist = new String[0];
			unidadesRel = new String[0];
			// ////////////////////////////////
			// Bloco de Inicializacao - End //
			// ////////////////////////////////
		}

		public void setIdPerfil(String idPerfil) {

			this.idPerfil = idPerfil;
		}

		public String getIdPerfil() {

			return this.idPerfil;
		}

		public void setIdPagina(String idPagina) {

			this.idPagina = idPagina;
		}

		public String getIdPagina() {

			return this.idPagina;
		}

		public void setIdSistema(String idSistema) {

			this.idSistema = idSistema;
		}

		public String getIdSistema() {

			return this.idSistema;
		}

		public void setIdSubSistema(String idSubSistema) {

			this.idSubSistema = idSubSistema;
		}

		public String getIdSubSistema() {

			return this.idSubSistema;
		}

		public void setExibeStruts(String exibeStruts) {

			this.exibeStruts = exibeStruts;
		}

		public String getExibeStruts() {

			return this.exibeStruts;
		}

		public void setListaPaginas(PaginaSimplVO[] listaPaginas) {

			this.listaPaginas = listaPaginas;
		}

		public PaginaSimplVO[] getListaPaginas() {

			if (this.listaPaginas == null || this.listaPaginas.length == 0) {
				this.listaPaginas = new PaginaSimplVO[1];
				this.listaPaginas[0] = PaginaSimplVO.Factory.newInstance();
			}

			return this.listaPaginas;
		}

		public void setListaSistemas(SistemasSubSistPaginasVO[] listaSistemas) {

			this.listaSistemas = listaSistemas;
		}

		public SistemasSubSistPaginasVO[] getListaSistemas() {

			if (this.listaSistemas == null || this.listaSistemas.length == 0) {
				this.listaSistemas = new SistemasSubSistPaginasVO[1];
				this.listaSistemas[0] = SistemasSubSistPaginasVO.Factory.newInstance();
			}

			return this.listaSistemas;
		}

		public void setListaSubSistemas(SubSistemaPaginasVO[] listaSubSistemas) {

			this.listaSubSistemas = listaSubSistemas;
		}

		public SubSistemaPaginasVO[] getListaSubSistemas() {

			if (this.listaSubSistemas == null || this.listaSubSistemas.length == 0) {
				this.listaSubSistemas = new SubSistemaPaginasVO[1];
				this.listaSubSistemas[0] = SubSistemaPaginasVO.Factory.newInstance();
			}

			return this.listaSubSistemas;
		}

		public void setListaUnidadesExist(UnidadeSimplVO[] listaUnidadesExist) {

			this.listaUnidadesExist = listaUnidadesExist;
		}

		public UnidadeSimplVO[] getListaUnidadesExist() {

			if (this.listaUnidadesExist == null || this.listaUnidadesExist.length == 0) {

				this.listaUnidadesExist = new UnidadeSimplVO[1];
				this.listaUnidadesExist[0] = UnidadeSimplVO.Factory.newInstance();
			}

			return this.listaUnidadesExist;
		}

		public void setListaUnidadesRel(UnidadeSimplVO[] listaUnidadesRel) {

			this.listaUnidadesRel = listaUnidadesRel;
		}

		public UnidadeSimplVO[] getListaUnidadesRel() {

			if (this.listaUnidadesRel == null || this.listaUnidadesRel.length == 0) {
				this.listaUnidadesRel = new UnidadeSimplVO[1];
				this.listaUnidadesRel[0] = UnidadeSimplVO.Factory.newInstance();
			}

			return this.listaUnidadesRel;
		}

		public void setUnidadesExist(String[] unidadesExist) {
			this.unidadesExist = unidadesExist;
		}

		public String[] getUnidadesExist() {
			if (this.unidadesExist == null || this.unidadesExist.length == 0) {
				this.unidadesExist = new String[1];
			}
			return this.unidadesExist;
		}

		public void setUnidadesRel(String[] unidadesRel) {
			this.unidadesRel = unidadesRel;
		}

		public String[] getUnidadesRel() {

			if (this.unidadesRel == null || this.unidadesRel.length == 0) {
				this.unidadesRel = new String[1];
			}

			return this.unidadesRel;
		}

		public void setListaSubSistemasLength(String listaSubSistemasLength) {
			this.listaSubSistemasLength = listaSubSistemasLength;
		}

		public String getListaSubSistemasLength() {
			return this.listaSubSistemasLength;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}

		public void setExibePagina(String exibePagina) {
			this.exibePagina = exibePagina;
		}

		public String getExibePagina() {
			return this.exibePagina;
		}

		public void setListaSubSistemasUsuario(SubSistemaUsuarioVO[] listaSubSistemasUsuario) {
			this.listaSubSistemasUsuario = listaSubSistemasUsuario;
		}

		public SubSistemaUsuarioVO[] getListaSubSistemasUsuario() {
			return this.listaSubSistemasUsuario;
		}

		public void setListaSistemasSubSistemasPaginas(SistemasSubSistPaginasVO[] listaSistemasSubSistemasPaginas) {
			this.listaSistemasSubSistemasPaginas = listaSistemasSubSistemasPaginas;
		}

		public SistemasSubSistPaginasVO[] getListaSistemasSubSistemasPaginas() {
			return this.listaSistemasSubSistemasPaginas;
		}
	}

	/**
	 * Getters dos Forms
	 */
	public ListaPerfisForm getListaPerfisForm() {
		return (this.lPerfilForm);
	}

	public RelPerfilUnidForm getRelPerfilUnidForm() {
		return (this.relPerfilUnidForm);
	}

	public SistemasSubSistPaginasVO getSistemaById(String id, SistemasSubSistPaginasVO[] listaSistemas) {

		for (int i = 0; i < listaSistemas.length; i++) {
			if (listaSistemas[i].getIdSistema().equals(id)) {
				return listaSistemas[i];
			}
		}
		return null;
	}

}
