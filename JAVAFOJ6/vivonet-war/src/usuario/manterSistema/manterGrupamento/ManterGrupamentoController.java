package usuario.manterSistema.manterGrupamento;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.commons.utils.StringUtilStatic;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.usuario.manterSistema.ManterSistemaFacade;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.GrupamentosExistentesUsuarioVODocument.GrupamentosExistentesUsuarioVO;
import br.com.vivo.fo.usuario.vo.ListaGrupamentosUsuarioVODocument.ListaGrupamentosUsuarioVO;
import br.com.vivo.fo.usuario.vo.ListaParamGrupamUnidVODocument.ListaParamGrupamUnidVO;
import br.com.vivo.fo.usuario.vo.ListaUnidadesGrupamentoVODocument.ListaUnidadesGrupamentoVO;
import br.com.vivo.fo.usuario.vo.PaginaSimplVODocument.PaginaSimplVO;
import br.com.vivo.fo.usuario.vo.SalvaUnidadesGrupamentoVODocument.SalvaUnidadesGrupamentoVO;
import br.com.vivo.fo.usuario.vo.SistemasSubSistPaginasVODocument.SistemasSubSistPaginasVO;
import br.com.vivo.fo.usuario.vo.SubSistemaPaginasVODocument.SubSistemaPaginasVO;
import br.com.vivo.fo.usuario.vo.SubSistemaUsuarioVODocument.SubSistemaUsuarioVO;
import br.com.vivo.fo.usuario.vo.UnidadesExistentesUsuarioVODocument.UnidadesExistentesUsuarioVO;
import br.com.vivo.fo.usuario.vo.UnidadesRelacionadasUsuarioVODocument.UnidadesRelacionadasUsuarioVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ManterGrupamentoController extends AbstractAction {

	private static final long serialVersionUID = 2990298036133284101L;

	@EJB
	private ManterSistemaFacade controlSistema;

	protected global.Global globalApp = new global.Global();

	private ManterGrupamForm manterGrupamForm = new ManterGrupamForm();
	private RelGrupamForm relGrupamForm = new RelGrupamForm();

	private static transient Logger log = new Logger("usuario");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("listaGrupam".equals(mapping.getParameter())) {
			return listaGrupam(mapping, form, request, response);
		} else if ("removeGrupam".equals(mapping.getParameter())) {
			return removeGrupam(mapping, form, request, response);
		} else if ("editaGrupam".equals(mapping.getParameter())) {
			return editaGrupam(mapping, form, request, response);
		} else if ("salvaGrupam".equals(mapping.getParameter())) {
			return salvaGrupam(mapping, form, request, response);
		} else if ("editaParGrupam".equals(mapping.getParameter())) {
			return editaParGrupam(mapping, form, request, response);
		} else if ("listaPaginasSub".equals(mapping.getParameter())) {
			return listaPaginasSub(mapping, form, request, response);
		} else if ("listaRelacaoUnid".equals(mapping.getParameter())) {
			return listaRelacaoUnid(mapping, form, request, response);
		} else if ("relacionaUnid".equals(mapping.getParameter())) {
			return relacionaUnid(mapping, form, request, response);
		} else if ("iniciaGrupam".equals(mapping.getParameter())) {
			return iniciaGrupam(mapping, form, request, response);
		} else if ("salvaEditaGrupam".equals(mapping.getParameter())) {
			return salvaEditaGrupam(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterGrupam.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			log.debug("ManterGrupamentoController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

			// Cria um atributo Formulario
			if (manterGrupamForm == null || request.getParameter("isMenu") == null) {
				manterGrupamForm = new ManterGrupamForm();
			}
			if (relGrupamForm == null) {
				relGrupamForm = new RelGrupamForm();
			}

			manterGrupamForm.setMsgError(ConstantesCRM.SVAZIO);

		} catch (Exception e) {
			log.error("ManterGrupamentoController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/index.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterGrupam.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward listaGrupam(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterGrupamForm form = (ManterGrupamForm) formParam;

		log.debug("ManterGrupamentoController:listaGrupam" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		manterGrupamForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			// Busca a lista de grupamentos existentes
			ListaGrupamentosUsuarioVO listaGrupamentos = ListaGrupamentosUsuarioVO.Factory.newInstance();

			GrupamentosExistentesUsuarioVO grupamentosExistentesUsuarioVO = GrupamentosExistentesUsuarioVO.Factory.newInstance();

			grupamentosExistentesUsuarioVO.setDsGrupamento(form.getDsGrupamento());

			listaGrupamentos = controlSistema.listaTodosGrupamentos(grupamentosExistentesUsuarioVO, ConstantesCRM.getCurrentUser(request.getSession()));

			// Se existir grupamentos, gravar no formulario.
			if (listaGrupamentos.getGrupamentosExistentesUsuarioVOArray().length > 0) {
				manterGrupamForm.setListaGrupamento(listaGrupamentos.getGrupamentosExistentesUsuarioVOArray());
			}

			manterGrupamForm.setListaGrupamentoLength(Integer.toString(listaGrupamentos.getGrupamentosExistentesUsuarioVOArray().length));

		} catch (Exception e) {

			log.error("ManterGrupamentoController:listaGrupam" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="manterGrupam.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removeGrupam(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterGrupamentoController:removeGrupam" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		manterGrupamForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			// Recupera o id do Grupamento a ser removido
			GrupamentosExistentesUsuarioVO grupamentoRemover = GrupamentosExistentesUsuarioVO.Factory.newInstance();
			String grupamId = request.getParameter("grupamId");
			grupamentoRemover.setIdGrupamento(grupamId);

			// chama o serviço para remover o grupamento
			ListaGrupamentosUsuarioVO listaGrupamentos = controlSistema.removeGrupamento(grupamentoRemover, ConstantesCRM.getCurrentUser(request.getSession()));

			// Se existir grupamentos, gravar no formulario.
			if (listaGrupamentos.getGrupamentosExistentesUsuarioVOArray().length > 0) {
				manterGrupamForm.setListaGrupamento(listaGrupamentos.getGrupamentosExistentesUsuarioVOArray());
			}

			manterGrupamForm.setListaGrupamentoLength(Integer.toString(listaGrupamentos.getGrupamentosExistentesUsuarioVOArray().length));
			manterGrupamForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("ManterGrupamentoController:removeGrupam" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) " + twe.getXmlHeader().getStatusText());
			manterGrupamForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterGrupamentoController:removeGrupam" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="manterGrupam.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward editaGrupam(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterGrupamentoController:editaGrupam" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		manterGrupamForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			// recupera o id do grupamento clicado
			String idGrupam = request.getParameter("grupamId");

			// procura pelos dados desse id num array
			GrupamentosExistentesUsuarioVO grupamento = getGrupamentoById(idGrupam, manterGrupamForm.getListaGrupamento());

			// retorna para o formulario os dados do grupamento.
			manterGrupamForm.setGrupamento(grupamento);
			relGrupamForm.setIdGrupamento(idGrupam);

		} catch (Exception e) {
			log.error("ManterGrupamentoController:editaGrupam" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="manterGrupam.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaGrupam(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterGrupamForm form = (ManterGrupamForm) formParam;

		log.debug("ManterGrupamentoController:salvaGrupam" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		manterGrupamForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			// Recupera os dados do Grupamento
			GrupamentosExistentesUsuarioVO grupamento = GrupamentosExistentesUsuarioVO.Factory.newInstance();
			grupamento.setIdGrupamento(form.getIdGrupamento());

			if (form.getDsGrupamento() != null) {
				grupamento.setDsGrupamento(form.getDsGrupamento().trim());
			}

			String tipo = ConstantesCRM.SVAZIO;
			// Verifica se é uma inserção ou uma edição
			if ((grupamento.getIdGrupamento() == null) || (grupamento.getIdGrupamento().equals(""))) {
				tipo = "novo";
			} else {
				tipo = "edicao";
			}

			// Chama o serviço para adicionar ou editar um grupamento dependendo
			// do parametro tipo
			ListaGrupamentosUsuarioVO listaGrupamentos = controlSistema.salvaGrupamento(grupamento, tipo, ConstantesCRM.getCurrentUser(request.getSession()));

			// Se existir grupamentos, gravar no formulario.
			if (listaGrupamentos.getGrupamentosExistentesUsuarioVOArray().length > 0) {
				manterGrupamForm = new ManterGrupamForm();
				manterGrupamForm.setListaGrupamento(listaGrupamentos.getGrupamentosExistentesUsuarioVOArray());
			}

			manterGrupamForm.setListaGrupamentoLength(Integer.toString(listaGrupamentos.getGrupamentosExistentesUsuarioVOArray().length));
			manterGrupamForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("ManterGrupamentoController:removeGrupam" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) " + twe.getXmlHeader().getStatusText());
			manterGrupamForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterGrupamentoController:removeGrupam" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="relacionarGrupamUnidade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward editaParGrupam(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterGrupamentoController:editaParGrupam" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		ActionForward retorno = mapping.findForward(ConstantesCRM.SUCCESS);
		relGrupamForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			// Recupera o id do Grupamento que foi selecionado
			SalvaUnidadesGrupamentoVO salvaUnidadeGrupam = SalvaUnidadesGrupamentoVO.Factory.newInstance();
			salvaUnidadeGrupam.setIdGrupamento(request.getParameter("grupamId"));
			relGrupamForm.setIdGrupamento(request.getParameter("grupamId"));
			relGrupamForm.setSalvaUnidadeGrupam(salvaUnidadeGrupam);
			relGrupamForm.setDsGrupamento(request.getParameter("dsGrupamento"));

			ListaParamGrupamUnidVO listaParamGrupamUnidVO = controlSistema.listaParamRelGrupamUnidades(ConstantesCRM.getCurrentUser(request.getSession()));

			// Atualiza a variavel que armazena a lista de Grupamentos, Sistemas
			// e SubSistemas
			if (listaParamGrupamUnidVO.getSistemasSubSistPaginasVOArray().length > 0) {
				relGrupamForm.setListaSistemas(listaParamGrupamUnidVO.getSistemasSubSistPaginasVOArray());
				// Indica que a montagem do select de subsistemas será feito por
				// javascript
				relGrupamForm.setExibeStruts(ConstantesCRM.SZERO);
				// monta a funcao javascript
				String listaJavascriptSubSistemas = montaListaSubSistema(listaParamGrupamUnidVO.getSistemasSubSistPaginasVOArray());
				request.setAttribute("lista", listaJavascriptSubSistemas);
			}

		} catch (TuxedoWarningException twe) {
			log.warn("ManterGrupamentoController:editaParGrupam" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) " + twe.getXmlHeader().getStatusText());
			relGrupamForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterGrupamentoController:editaParGrupam" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return retorno;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relacionarGrupamUnidade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward listaPaginasSub(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelGrupamForm form = (RelGrupamForm) formParam;

		log.debug("ManterGrupamentoController:listaPaginasSub" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		ActionForward retorno = mapping.findForward(ConstantesCRM.SUCCESS);
		relGrupamForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			// Recupera o id do SubSistema
			SubSistemaUsuarioVO subSistemaUsuarioVO = SubSistemaUsuarioVO.Factory.newInstance();
			subSistemaUsuarioVO.setIdSubSistema(form.getIdSubSistema());

			// Grava o id do Sistema e SubSistema
			relGrupamForm.setIdSistema(form.getIdSistema());
			SalvaUnidadesGrupamentoVO salvaUnidade = SalvaUnidadesGrupamentoVO.Factory.newInstance();
			salvaUnidade.setIdSistema(form.getIdSistema());
			relGrupamForm.setSalvaUnidadeGrupam(salvaUnidade);
			relGrupamForm.setIdSubSistema(form.getIdSubSistema());
			relGrupamForm.setListaUnidadesExist(new UnidadesExistentesUsuarioVO[0]);
			relGrupamForm.setListaUnidadesRel(new UnidadesRelacionadasUsuarioVO[0]);

			// Chama o serviço que lista as páginas dado um id de subsistema
			SubSistemaPaginasVO subSistemaPaginasVO = controlSistema.listaPaginasPorIdSubSistema(subSistemaUsuarioVO, ConstantesCRM.getCurrentUser(request.getSession()));

			// Atualiza as variaveis do formulario
			if (subSistemaPaginasVO.getPaginaSimplVOArray().length > 0) {
				relGrupamForm.setListaPaginas(subSistemaPaginasVO.getPaginaSimplVOArray());
			} else {
				relGrupamForm.setListaPaginas(new PaginaSimplVO[0]);
			}
			relGrupamForm.getSalvaUnidadeGrupam().setIdSubSistema(subSistemaPaginasVO.getIdSubSistema());

			// Indica que a montagem do select de subsistemas será feito por
			// struts
			relGrupamForm.setExibeStruts(ConstantesCRM.SONE);

			// monta a lista de subsistemas
			SistemasSubSistPaginasVO sistemaSubSistemas = getSistemaById(form.getIdSistema(), relGrupamForm.getListaSistemas());
			if (sistemaSubSistemas.getSubSistemaPaginasVOArray().length > 0) {
				relGrupamForm.setListaSubSistemas(sistemaSubSistemas.getSubSistemaPaginasVOArray());
			}

		} catch (TuxedoWarningException twe) {
			log.warn("ManterGrupamentoController:listaPaginasSub" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) " + twe.getXmlHeader().getStatusText());
			relGrupamForm.setListaPaginas(new PaginaSimplVO[0]);
			relGrupamForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterGrupamentoController:listaPaginasSub" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		// monta a funcao javascript
		String listaJavascriptSubSistemas = montaListaSubSistema(relGrupamForm.getListaSistemas());
		request.setAttribute("lista", listaJavascriptSubSistemas);
		
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return retorno;

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relacionarGrupamUnidade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward listaRelacaoUnid(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelGrupamForm form = (RelGrupamForm) formParam;

		log.debug("ManterGrupamentoController:listaRelacaoUnid" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		ActionForward retorno = mapping.findForward(ConstantesCRM.SUCCESS);
		relGrupamForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			// Recupera o objeto com os parametros necessarios para pesquisa das
			// relacoes existentes
			SalvaUnidadesGrupamentoVO salvaUnidadeGrupam = SalvaUnidadesGrupamentoVO.Factory.newInstance();

			// carrega Bean.
			salvaUnidadeGrupam.setIdGrupamento(relGrupamForm.getIdGrupamento());
			salvaUnidadeGrupam.setIdSistema(form.getIdSistema());
			salvaUnidadeGrupam.setIdSubSistema(form.getIdSubSistema());
			salvaUnidadeGrupam.setIdPagina(form.getIdPagina());
			// fim carregamento.

			relGrupamForm.setIdPagina(form.getIdPagina());

			ListaUnidadesGrupamentoVO listaUnidadesGrupamentoVO = controlSistema.listaRelGrupamUnidades(salvaUnidadeGrupam, ConstantesCRM.getCurrentUser(request.getSession()));

			// Atualiza as unidades existentes e relacionadas no formulario
			if (listaUnidadesGrupamentoVO.getUnidadesExistentesUsuarioVOArray().length > 0) {
				relGrupamForm.setListaUnidadesExist(listaUnidadesGrupamentoVO.getUnidadesExistentesUsuarioVOArray());
			} else {
				// se retorno sem registros relacionados, instancia objeto vazio
				relGrupamForm.setListaUnidadesExist(new UnidadesExistentesUsuarioVO[0]);
			}

			// Atualiza as unidades existentes e relacionadas no formulario
			if (listaUnidadesGrupamentoVO.getUnidadesRelacionadasUsuarioVOArray().length > 0) {
				relGrupamForm.setListaUnidadesRel(listaUnidadesGrupamentoVO.getUnidadesRelacionadasUsuarioVOArray());
			} else {
				// se retorno sem registros relacionados, instancia objeto vazio
				relGrupamForm.setListaUnidadesRel(new UnidadesRelacionadasUsuarioVO[0]);
			}

			// monta a funcao javascript
			String listaJavascriptSubSistemas = montaListaSubSistema(relGrupamForm.getListaSistemas());
			request.setAttribute("lista", listaJavascriptSubSistemas);

		} catch (Exception e) {
			log.error("ManterGrupamentoController:listaRelacaoUnid" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return retorno;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relacionarGrupamUnidade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward relacionaUnid(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelGrupamForm form = (RelGrupamForm) formParam;

		log.debug("ManterGrupamentoController:relacionaUnid" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		ActionForward retorno = mapping.findForward(ConstantesCRM.SUCCESS);
		relGrupamForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			// Recupera o objeto com os parametros necessarios para pesquisa das
			// relacoes existentes
			SalvaUnidadesGrupamentoVO salvaUnidadeGrupam = SalvaUnidadesGrupamentoVO.Factory.newInstance();
			UnidadesRelacionadasUsuarioVO unidadeRel;

			salvaUnidadeGrupam.setIdGrupamento(relGrupamForm.getIdGrupamento());
			salvaUnidadeGrupam.setIdSistema(form.getIdSistema());
			salvaUnidadeGrupam.setIdSubSistema(form.getIdSubSistema());
			salvaUnidadeGrupam.setIdPagina(form.getIdPagina());

			String[] listaUnidades = form.getUnidadesRel();
			// Grava a lista de ids de unidades relacionadas
			for (int i = 0; i < listaUnidades.length; i++) {
				unidadeRel = UnidadesRelacionadasUsuarioVO.Factory.newInstance();
				unidadeRel.setIdUnidade(listaUnidades[i]);
				salvaUnidadeGrupam.insertNewUnidadesRelacionadasUsuarioVO(i);
				salvaUnidadeGrupam.setUnidadesRelacionadasUsuarioVOArray(i, unidadeRel);
			}

			ListaUnidadesGrupamentoVO listaUnidadesGrupamentoVO = controlSistema.salvaRelGrupamUnidades(salvaUnidadeGrupam, ConstantesCRM.getCurrentUser(request.getSession()));

			// Atualiza as unidades existentes e relacionadas no formulario
			if (listaUnidadesGrupamentoVO.getUnidadesExistentesUsuarioVOArray().length > 0) {
				relGrupamForm.setListaUnidadesExist(listaUnidadesGrupamentoVO.getUnidadesExistentesUsuarioVOArray());
			} else {
				relGrupamForm.setListaUnidadesExist(new UnidadesExistentesUsuarioVO[0]);
			}

			if (listaUnidadesGrupamentoVO.getUnidadesRelacionadasUsuarioVOArray().length > 0) {
				relGrupamForm.setListaUnidadesRel(listaUnidadesGrupamentoVO.getUnidadesRelacionadasUsuarioVOArray());
			} else {
				relGrupamForm.setListaUnidadesRel(new UnidadesRelacionadasUsuarioVO[0]);
			}

			// monta a funcao javascript
			String listaJavascriptSubSistemas = montaListaSubSistema(relGrupamForm.getListaSistemas());

			request.setAttribute("lista", listaJavascriptSubSistemas);
			relGrupamForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("ManterGrupamentoController:relacionaUnid" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) " + twe.getXmlHeader().getStatusText());
			relGrupamForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterGrupamentoController:listaRelacaoUnid" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return retorno;
	}

	private String montaListaSubSistema(SistemasSubSistPaginasVO[] sistemasSubSistPaginasVO) {
		boolean primeiro;
		String texto = "<script language=\"Javascript\">";
		texto = texto + "function montaListaSubSistemas() {document.forms[0].idSubSistema.options.length=0;";
		for (int i = 0; i < sistemasSubSistPaginasVO.length; i++) {
			primeiro = true;
			if (sistemasSubSistPaginasVO[i].getSubSistemaPaginasVOArray().length > 0) {
				texto = texto + "if (document.forms[0].idSistema.value == '" + sistemasSubSistPaginasVO[i].getIdSistema() + "'){";
				texto = texto + "document.forms[0].idSubSistema.options[document.forms[0].idSubSistema.length] = new Option('Escolha uma opção...','000');";
				for (int j = 0; j < sistemasSubSistPaginasVO[i].getSubSistemaPaginasVOArray().length; j++) {
					texto = texto + "document.forms[0].idSubSistema.options[document.forms[0].idSubSistema.length] = new Option('" + StringUtilStatic.escapaComillasJS(sistemasSubSistPaginasVO[i].getSubSistemaPaginasVOArray(j).getDsSubSistema().replaceAll("&", "&#38;")) + "','" + sistemasSubSistPaginasVO[i].getSubSistemaPaginasVOArray(j).getIdSubSistema() + "');";
				}
				texto = texto + "}";
			} else {
				// grava a mensagem que o sistema escolhido não possui
				// subsistemas.
				if (primeiro) {
					texto = texto + "if (document.forms[0].idSistema.value == '" + sistemasSubSistPaginasVO[i].getIdSistema() + "'){";
					texto = texto + "document.forms[0].idSubSistema.options[document.forms[0].idSubSistema.length] = new Option('O sistema escolhido não possui sub-sistemas...','000');}";
					primeiro = false;
				}
			}
		}
		texto = texto + "}";

		return texto + "</script>";
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relacionarGrupamUnidade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward iniciaGrupam(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterGrupamentoController:iniciaGrupam" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		ActionForward retorno = mapping.findForward(ConstantesCRM.SUCCESS);
		relGrupamForm.setMsgError("");

		try {
			// Recupera o id do Grupamento que foi selecionado
			SalvaUnidadesGrupamentoVO salvaUnidadeGrupam = SalvaUnidadesGrupamentoVO.Factory.newInstance();
			salvaUnidadeGrupam.setIdGrupamento(request.getParameter("grupamId"));
			relGrupamForm.setIdGrupamento(request.getParameter("grupamId"));
			relGrupamForm.setSalvaUnidadeGrupam(salvaUnidadeGrupam);

			GrupamentosExistentesUsuarioVO grupamento = getGrupamentoById(request.getParameter("grupamId"), manterGrupamForm.getListaGrupamento());
			relGrupamForm.setDsGrupamento(grupamento.getDsGrupamento());

			relGrupamForm.setIdPagina(ConstantesCRM.SVAZIO);
			relGrupamForm.setIdSistema(ConstantesCRM.SVAZIO);
			relGrupamForm.setIdSubSistema(ConstantesCRM.SVAZIO);
			relGrupamForm.setListaPaginas(new PaginaSimplVO[0]);

			// ListaParamGrupamUnidVO listaParamGrupamUnidVO =
			// controlSistema.listaParamRelGrupamUnidades(user);

			ListaParamGrupamUnidVO listaParamGrupamUnidVO = controlSistema.listaParamRelGrupamUnidades(ConstantesCRM.getCurrentUser(request.getSession()));

			relGrupamForm.setListaUnidadesExist(new UnidadesExistentesUsuarioVO[0]);

			relGrupamForm.setListaUnidadesRel(new UnidadesRelacionadasUsuarioVO[0]);

			// Atualiza a variavel que armazena a lista de Grupamentos, Sistemas
			// e SubSistemas
			if (listaParamGrupamUnidVO.getSistemasSubSistPaginasVOArray().length > 0) {
				relGrupamForm.setListaSistemas(listaParamGrupamUnidVO.getSistemasSubSistPaginasVOArray());
				// Indica que a montagem do select de subsistemas será feito por
				// javascript
				relGrupamForm.setExibeStruts(ConstantesCRM.SZERO);
				// monta a funcao javascript
				String listaJavascriptSubSistemas = montaListaSubSistema(listaParamGrupamUnidVO.getSistemasSubSistPaginasVOArray());
				request.setAttribute("lista", listaJavascriptSubSistemas);
			}

		} catch (TuxedoWarningException twe) {
			log.warn("ManterGrupamentoController:relacionaUnid" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) " + twe.getXmlHeader().getStatusText());
			relGrupamForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterGrupamentoController:relacionaUnid" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return retorno;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="alteraIncluiGrupamento.jsp"
	 */
	public ActionForward salvaEditaGrupam(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelGrupamForm form = (RelGrupamForm) formParam;

		log.debug("ManterGrupamentoController:salvaEditaGrupam" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		String operacao = (String)request.getParameter("operacao"); 
		log.debug("ManterGrupamentoController:salvaEditaGrupam: IdGrupamento: " + "( " + form.getIdGrupamento() + " )");
		log.debug("ManterGrupamentoController:salvaEditaGrupam: Operacao: " + "( " + operacao + " )");
		manterGrupamForm.setMsgError(ConstantesCRM.SVAZIO);
		
		if (!"incluir".equalsIgnoreCase(operacao)) {
			GrupamentosExistentesUsuarioVO grupamento = getGrupamentoById(form.getIdGrupamento(), manterGrupamForm.getListaGrupamento());
	
			manterGrupamForm.setIdGrupamento(form.getIdGrupamento());
			manterGrupamForm.setDsGrupamento(grupamento.getDsGrupamento());
		}	
		
		manterGrupamForm.setMsgError(ConstantesCRM.SSucesso);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class ManterGrupamForm extends ActionForm {

		private static final long serialVersionUID = 3051268722961716118L;

		private String msgError = ConstantesCRM.SVAZIO;
		private GrupamentosExistentesUsuarioVO grupamento;
		private GrupamentosExistentesUsuarioVO itemGrupamento;
		private GrupamentosExistentesUsuarioVO[] listaGrupamento;
		private String listaGrupamentoLength;
		private String dsGrupamento;
		private String idGrupamento;

		public ManterGrupamForm() {
			listaGrupamento = new GrupamentosExistentesUsuarioVO[0];
			grupamento = GrupamentosExistentesUsuarioVO.Factory.newInstance();
			itemGrupamento = GrupamentosExistentesUsuarioVO.Factory.newInstance();
			listaGrupamentoLength = "-1";
			idGrupamento = ConstantesCRM.SVAZIO;
			dsGrupamento = ConstantesCRM.SVAZIO;
		}

		public void setGrupamento(GrupamentosExistentesUsuarioVO grupamento) {

			this.grupamento = grupamento;
		}

		public GrupamentosExistentesUsuarioVO getGrupamento() {

			return this.grupamento;
		}

		public void setListaGrupamento(GrupamentosExistentesUsuarioVO[] listaGrupamento) {

			this.listaGrupamento = listaGrupamento;
		}

		public GrupamentosExistentesUsuarioVO[] getListaGrupamento() {

			if ((this.listaGrupamento == null) || (this.listaGrupamento.length == 0)) {
				this.listaGrupamento = new GrupamentosExistentesUsuarioVO[1];
				this.listaGrupamento[0] = GrupamentosExistentesUsuarioVO.Factory.newInstance();
			}

			return this.listaGrupamento;
		}

		public void setItemGrupamento(GrupamentosExistentesUsuarioVO itemGrupamento) {
			this.itemGrupamento = itemGrupamento;
		}

		public GrupamentosExistentesUsuarioVO getItemGrupamento() {

			if (this.itemGrupamento == null) {
				this.itemGrupamento = GrupamentosExistentesUsuarioVO.Factory.newInstance();
			}

			return this.itemGrupamento;
		}

		public void setListaGrupamentoLength(String listaGrupamentoLength) {
			this.listaGrupamentoLength = listaGrupamentoLength;
		}

		public String getListaGrupamentoLength() {
			return this.listaGrupamentoLength;
		}

		public void setIdGrupamento(String idGrupamento) {

			this.idGrupamento = idGrupamento;
		}

		public String getIdGrupamento() {

			return this.idGrupamento;
		}

		public void setDsGrupamento(String dsGrupamento) {

			this.dsGrupamento = dsGrupamento;
		}

		public String getDsGrupamento() {

			return this.dsGrupamento;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}
	}

	public static class RelGrupamForm extends ActionForm {

		private static final long serialVersionUID = 7351877355829337165L;

		private String msgError;
		private String exibeStruts;
		private String idPagina;
		private String idSubSistema;
		private String idSistema;
		private String dsGrupamento;
		private String idGrupamento;
		private SalvaUnidadesGrupamentoVO salvaUnidadeGrupam;
		private SistemasSubSistPaginasVO[] listaSistemas;
		private SubSistemaPaginasVO[] listaSubSistemas;
		private PaginaSimplVO[] listaPaginas;
		private UnidadesExistentesUsuarioVO[] listaUnidadesExist;
		private UnidadesRelacionadasUsuarioVO[] listaUnidadesRel;
		private String[] unidadesRel;
		private String[] unidadesExist;

		public RelGrupamForm() {
			idGrupamento = ConstantesCRM.SVAZIO;
			dsGrupamento = ConstantesCRM.SVAZIO;
			idSubSistema = ConstantesCRM.SVAZIO;
			idSistema = ConstantesCRM.SVAZIO;
			exibeStruts = ConstantesCRM.SZERO;
			listaSistemas = new SistemasSubSistPaginasVO[0];
			listaSubSistemas = new SubSistemaPaginasVO[0];
			listaPaginas = new PaginaSimplVO[0];
			listaUnidadesExist = new UnidadesExistentesUsuarioVO[0];
			listaUnidadesRel = new UnidadesRelacionadasUsuarioVO[0];
			salvaUnidadeGrupam = SalvaUnidadesGrupamentoVO.Factory.newInstance();
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

		public void setListaUnidadesRel(UnidadesRelacionadasUsuarioVO[] listaUnidadesRel) {

			this.listaUnidadesRel = listaUnidadesRel;
		}

		public UnidadesRelacionadasUsuarioVO[] getListaUnidadesRel() {

			if (this.listaUnidadesRel == null || this.listaUnidadesRel.length == 0) {
				this.listaUnidadesRel = new UnidadesRelacionadasUsuarioVO[1];
				this.listaUnidadesRel[0] = UnidadesRelacionadasUsuarioVO.Factory.newInstance();
			}

			return this.listaUnidadesRel;
		}

		public void setListaUnidadesExist(UnidadesExistentesUsuarioVO[] listaUnidadesExist) {

			this.listaUnidadesExist = listaUnidadesExist;
		}

		public UnidadesExistentesUsuarioVO[] getListaUnidadesExist() {

			if (this.listaUnidadesExist == null || this.listaUnidadesExist.length == 0) {
				this.listaUnidadesExist = new UnidadesExistentesUsuarioVO[1];
				this.listaUnidadesExist[0] = UnidadesExistentesUsuarioVO.Factory.newInstance();
			}

			return this.listaUnidadesExist;
		}

		public void setIdGrupamento(String idGrupamento) {
			this.idGrupamento = idGrupamento;
		}

		public String getIdGrupamento() {

			return this.idGrupamento;
		}

		public void setSalvaUnidadeGrupam(SalvaUnidadesGrupamentoVO salvaUnidadeGrupam) {

			this.salvaUnidadeGrupam = salvaUnidadeGrupam;
		}

		public SalvaUnidadesGrupamentoVO getSalvaUnidadeGrupam() {

			if (this.salvaUnidadeGrupam == null) {
				this.salvaUnidadeGrupam = SalvaUnidadesGrupamentoVO.Factory.newInstance();
			}

			return this.salvaUnidadeGrupam;
		}

		public void setDsGrupamento(String dsGrupamento) {
			this.dsGrupamento = dsGrupamento;
		}

		public String getDsGrupamento() {
			return this.dsGrupamento;
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

		public void setIdPagina(String idPagina) {

			this.idPagina = idPagina;
		}

		public String getIdPagina() {

			return this.idPagina;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}
	}

	public RelGrupamForm getRelGrupamForm() {

		return this.relGrupamForm;
	}

	public ManterGrupamForm getManterGrupamForm() {

		return this.manterGrupamForm;
	}

	public GrupamentosExistentesUsuarioVO getGrupamentoById(String id, GrupamentosExistentesUsuarioVO[] grupamentos) {

		for (int i = 0; i < grupamentos.length; i++) {
			if (grupamentos[i].getIdGrupamento().equals(id)) {
				return grupamentos[i];
			}
		}
		return null;
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
