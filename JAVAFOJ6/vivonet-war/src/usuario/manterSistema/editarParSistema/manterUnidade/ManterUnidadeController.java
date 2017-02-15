package usuario.manterSistema.editarParSistema.manterUnidade;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.usuario.manterSistema.ManterSistemaFacade;
import br.com.vivo.fo.exception.FOException;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.ListaUnidadesPesquisaVODocument.ListaUnidadesPesquisaVO;
import br.com.vivo.fo.usuario.vo.PaginaSimplVODocument.PaginaSimplVO;
import br.com.vivo.fo.usuario.vo.PaginaUnidadesVODocument.PaginaUnidadesVO;
import br.com.vivo.fo.usuario.vo.SistemaIDVODocument.SistemaIDVO;
import br.com.vivo.fo.usuario.vo.SubSistemaPaginasVODocument.SubSistemaPaginasVO;
import br.com.vivo.fo.usuario.vo.SubSistemaUnidadesVODocument.SubSistemaUnidadesVO;
import br.com.vivo.fo.usuario.vo.SubSistemaUsuarioVODocument.SubSistemaUsuarioVO;
import br.com.vivo.fo.usuario.vo.SubSistemasUsuarioVODocument.SubSistemasUsuarioVO;
import br.com.vivo.fo.usuario.vo.UnidadePesquisaVODocument.UnidadePesquisaVO;
import br.com.vivo.fo.usuario.vo.UnidadeSalvaVODocument.UnidadeSalvaVO;
import br.com.vivo.fo.usuario.vo.UnidadeSimplVODocument.UnidadeSimplVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ManterUnidadeController extends AbstractAction {

	private static final long serialVersionUID = -6064071751180514488L;

	@EJB
	private ManterSistemaFacade controlSistema;

	// atributos e objetos
	private UnidadeForm unidadeForm = new UnidadeForm();

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("usuario");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("carregaSelectPagina".equals(mapping.getParameter())) {
			return carregaSelectPagina(mapping, form, request, response);
		} else if ("salvaUnidade".equals(mapping.getParameter())) {
			return salvaUnidade(mapping, form, request, response);
		} else if ("editaUnidade".equals(mapping.getParameter())) {
			return editaUnidade(mapping, form, request, response);
		} else if ("limpaUnidade".equals(mapping.getParameter())) {
			return limpaUnidade(mapping, form, request, response);
		} else if ("pesquisaUnidades".equals(mapping.getParameter())) {
			return pesquisaUnidades(mapping, form, request, response);
		} else if ("removeUnidade".equals(mapping.getParameter())) {
			return removeUnidade(mapping, form, request, response);
		} else if ("incluirAlterarUnidade".equals(mapping.getParameter())) {
			return incluirAlterarUnidade(mapping, form, request, response);
		} else if ("incluiUnidade".equals(mapping.getParameter())) {
			return incluiUnidade(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="manterUnidade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			log.debug("ManterUnidadeController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

			unidadeForm = new UnidadeForm();

			// Recupera o id do sistema
			unidadeForm.setIdSistema(request.getParameter("sistemaId"));
			unidadeForm.setMsgError(ConstantesCRM.SVAZIO);

			SistemaIDVO sistemaIdListar = SistemaIDVO.Factory.newInstance();
			sistemaIdListar.setIdSistema(unidadeForm.getIdSistema());

			// recupera todos os sub-sistemas relacionados ao ID de sistema
			SubSistemasUsuarioVO subSistemasUsuarioVO = controlSistema.listaSubSistemasPorSistema(sistemaIdListar, ConstantesCRM.getCurrentUser(request.getSession()));

			// se algum resultado foi retornado, guarda os valores no formulario
			if (subSistemasUsuarioVO.sizeOfSubSistemaUsuarioVOArray() > 0) {
				unidadeForm.setListaSubSistemasUsuarioVO(subSistemasUsuarioVO.getSubSistemaUsuarioVOArray());
			} else {
				subSistemasUsuarioVO.addNewSubSistemaUsuarioVO();
				SubSistemaUsuarioVO subSistemaUsuarioVO = SubSistemaUsuarioVO.Factory.newInstance();
				subSistemaUsuarioVO.setIdSubSistema("0000");
				subSistemaUsuarioVO.setDsSubSistema(" ");
				unidadeForm.setListaSubSistemasUsuarioVO(subSistemasUsuarioVO.getSubSistemaUsuarioVOArray());
			}

		} catch (TuxedoWarningException twe) {
			log.warn("ManterUnidadeController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			unidadeForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterUnidadeController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
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
	 * @jpf:forward name="success" path="manterUnidade.jsp"
	 * @jpf:forward name="incluiAltera" path="incluirAlterarUnidade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward carregaSelectPagina(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UnidadeForm form = (UnidadeForm) formParam;

		log.debug("ManterUnidadeController:carregaSelectPagina" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		String retorno = ConstantesCRM.SVAZIO;
		unidadeForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			retorno = request.getParameter("incluiAltera");

			// Pega o id do subsistema selecionado
			String idSubSistema = form.getUnidadeAtual().getIdSubSistema();

			SubSistemaUsuarioVO subSistemaUsuarioVO = SubSistemaUsuarioVO.Factory.newInstance();
			subSistemaUsuarioVO.setIdSubSistema(idSubSistema);
			SubSistemaPaginasVO subSistemaPaginasVO = controlSistema.listaPaginasPorIdSubSistema(subSistemaUsuarioVO, ConstantesCRM.getCurrentUser(request.getSession()));

			// Grava esse id no unidadeForm
			UnidadeSalvaVO unidadeAtual = unidadeForm.getUnidadeAtual();
			unidadeAtual.setIdSubSistema(idSubSistema);
			unidadeForm.setUnidadeAtual(unidadeAtual);

			// Grava na variavel apropriada as paginas associadas ao SubSistema
			// selecionado
			// ///unidadeForm.setListaPaginasVO(unidadeForm.getListaPaginaById(idSubSistema,
			// unidadeForm.getListaSubSistemaPaginas()));
			if (subSistemaPaginasVO.sizeOfPaginaSimplVOArray() > 0) {
				unidadeForm.setListaPaginasVO(subSistemaPaginasVO.getPaginaSimplVOArray());
			} else {
				subSistemaPaginasVO.addNewPaginaSimplVO();
				PaginaSimplVO paginaSimplVO = PaginaSimplVO.Factory.newInstance();
				paginaSimplVO.setIdPagina("0000");
				paginaSimplVO.setNmPagina("Não existe paginas relacionadas a esse subsistema.");
				subSistemaPaginasVO.setPaginaSimplVOArray(0, paginaSimplVO);
				unidadeForm.setListaPaginasVO(subSistemaPaginasVO.getPaginaSimplVOArray());
			}

		} catch (TuxedoWarningException twe) {
			log.warn("ManterUnidadeController:carregaSelectPagina" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			unidadeForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterUnidadeController:carregaSelectPagina" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget("top.frameApp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
		}

		if (retorno == null || retorno.trim().equals("")) {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} else {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("incluiAltera");
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterUnidade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaUnidade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UnidadeForm form = (UnidadeForm) formParam;

		log.debug("ManterUnidadeController:salvaUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		unidadeForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			UnidadeSalvaVO unidadeSalvaVO = UnidadeSalvaVO.Factory.newInstance();
			ListaUnidadesPesquisaVO listaUnidadesPesquisaVO;

			// grava os dados da unidade a ser salva.
			if (request.getParameter("flag").equals(ConstantesCRM.SZERO)) {

				unidadeSalvaVO.setIdSubSistema(form.getUnidadeAtual().getIdSubSistema());
				unidadeSalvaVO.setIdPagina(form.getUnidadeAtual().getIdPagina());
				unidadeSalvaVO.setCdUnidade(form.getUnidadeAtual().getCdUnidade());
				unidadeSalvaVO.setNmUnidade(form.getUnidadeAtual().getNmUnidade());
				unidadeSalvaVO.setInDetalhe(form.getUnidadeAtual().getInDetalhe());
				unidadeSalvaVO.setIdSistema(unidadeForm.getIdSistema());

				unidadeSalvaVO.setIdUnidade(ConstantesCRM.SVAZIO);
				listaUnidadesPesquisaVO = controlSistema.salvaUnidade(unidadeSalvaVO, "I", ConstantesCRM.getCurrentUser(request.getSession()));
				unidadeForm.setListaUnidadesLength(ConstantesCRM.SZERO);
				int countUnidades = unidadeForm.countUnidades(listaUnidadesPesquisaVO.getSubSistemaUnidadesVOArray());

				if (countUnidades > 0) {
					unidadeForm.setIndicativoOperacao("Resultado");
					unidadeForm.setListaUnidadesLength(Integer.toString(countUnidades));
					unidadeForm.setListaUnidadesPesquisa(unidadeForm.converteUnidadesPesquisa(listaUnidadesPesquisaVO.getSubSistemaUnidadesVOArray(), countUnidades));
				} else {
					unidadeForm.setIndicativoOperacao("Vazio");
				}
			} else if (request.getParameter("flag").equals(ConstantesCRM.SONE)) {
				unidadeSalvaVO.setIdSubSistema(request.getParameter("idSubSistemaTemp"));
				unidadeSalvaVO.setIdPagina(request.getParameter("idPaginaTemp"));
				unidadeSalvaVO.setCdUnidade(form.getUnidadeAtual().getCdUnidade());
				unidadeSalvaVO.setNmUnidade(form.getUnidadeAtual().getNmUnidade());
				unidadeSalvaVO.setInDetalhe(form.getUnidadeAtual().getInDetalhe());
				unidadeSalvaVO.setIdSistema(unidadeForm.getIdSistema());
				unidadeSalvaVO.setIdUnidade(request.getParameter("idUnidade"));

				listaUnidadesPesquisaVO = controlSistema.salvaUnidade(unidadeSalvaVO, "E", ConstantesCRM.getCurrentUser(request.getSession()));
				unidadeForm.setListaUnidadesLength(ConstantesCRM.SZERO);
				int countUnidades = unidadeForm.countUnidades(listaUnidadesPesquisaVO.getSubSistemaUnidadesVOArray());

				if (countUnidades > 0) {
					unidadeForm.setIndicativoOperacao("Resultado");
					unidadeForm.setListaUnidadesLength(Integer.toString(countUnidades));
					unidadeForm.setListaUnidadesPesquisa(unidadeForm.converteUnidadesPesquisa(listaUnidadesPesquisaVO.getSubSistemaUnidadesVOArray(), countUnidades));
				} else {
					unidadeForm.setIndicativoOperacao("Vazio");
				}

			} else {
				throw new FOException("Tipo de insercao/edicao de unidades inválido ou nulo.");
			}

			unidadeForm.getUnidadeAtual().setCdUnidade(ConstantesCRM.SVAZIO);
			unidadeForm.getUnidadeAtual().setIdPagina(ConstantesCRM.SVAZIO);
			unidadeForm.getUnidadeAtual().setIdSistema(ConstantesCRM.SVAZIO);
			unidadeForm.getUnidadeAtual().setIdSubSistema(ConstantesCRM.SVAZIO);
			unidadeForm.getUnidadeAtual().setIdUnidade(ConstantesCRM.SVAZIO);
			unidadeForm.getUnidadeAtual().setInDetalhe(ConstantesCRM.SVAZIO);
			unidadeForm.getUnidadeAtual().setNmUnidade(ConstantesCRM.SVAZIO);
			form.getUnidadeAtual().setIdSubSistema(ConstantesCRM.SVAZIO);
			unidadeForm.setMsgError(ConstantesCRM.SSucesso);
		} catch (TuxedoWarningException twe) {
			log.warn("ManterUnidadeController:salvaUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			unidadeForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterUnidadeController:salvaUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
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
	 * @jpf:forward name="success" path="manterUnidade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward editaUnidade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterUnidadeController:editaUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		unidadeForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			// getUnidadeById(String idUnidade, UnidadePesquisaVO[]
			// listaUnidades)
			UnidadeSalvaVO unidadeAtual = UnidadeSalvaVO.Factory.newInstance();
			// Recupera o id da unidade
			String idUnidade = request.getParameter("unidadeId");
			// Procura a unidade com esse id
			UnidadePesquisaVO unidadeEditar = unidadeForm.getUnidadeById(idUnidade, unidadeForm.getListaUnidadesPesquisa());

			// Joga a unidade na variavel apropriada
			unidadeAtual.setIdSubSistema(unidadeEditar.getIdSubSistema());
			unidadeAtual.setIdPagina(unidadeEditar.getIdPagina());
			unidadeAtual.setIdUnidade(unidadeEditar.getIdUnidade());
			unidadeAtual.setNmUnidade(unidadeEditar.getNmUnidade());
			unidadeAtual.setCdUnidade(unidadeEditar.getCdUnidade());
			unidadeAtual.setInDetalhe(unidadeEditar.getInDetalhe());

			unidadeForm.setUnidadeAtual(unidadeAtual);

			// monta o select das paginas associadas ao id do subsistema a ser
			// editado.
			unidadeForm.setListaPaginasVO(unidadeForm.getListaPaginaById(unidadeEditar.getIdSubSistema(), unidadeForm.getListaSubSistemaPaginas()));

			unidadeForm.getUnidadeAtual().setCdUnidade(ConstantesCRM.SVAZIO);
			unidadeForm.getUnidadeAtual().setIdPagina(ConstantesCRM.SVAZIO);
			unidadeForm.getUnidadeAtual().setIdSistema(ConstantesCRM.SVAZIO);
			unidadeForm.getUnidadeAtual().setIdSubSistema(ConstantesCRM.SVAZIO);
			unidadeForm.getUnidadeAtual().setIdUnidade(ConstantesCRM.SVAZIO);
			unidadeForm.getUnidadeAtual().setInDetalhe(ConstantesCRM.SVAZIO);
			unidadeForm.getUnidadeAtual().setNmUnidade(ConstantesCRM.SVAZIO);

		} catch (Exception e) {
			log.error("ManterUnidadeController:editaUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget("top.frameApp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterUnidade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward limpaUnidade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterUnidadeController:limpaUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		unidadeForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			// Cria uma unidade vazia
			UnidadeSalvaVO unidadeVazia = UnidadeSalvaVO.Factory.newInstance();

			// Limpa os dados do formulario
			unidadeForm.setUnidadeAtual(unidadeVazia);

			PaginaSimplVO[] paginaSimplVO = new PaginaSimplVO[1];
			paginaSimplVO[0] = PaginaSimplVO.Factory.newInstance();
			unidadeForm.setListaPaginasVO(paginaSimplVO);

		} catch (Exception e) {
			log.error("ManterUnidadeController:limpaUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget("top.frameApp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterUnidade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward pesquisaUnidades(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UnidadeForm form = (UnidadeForm) formParam;

		log.debug("ManterUnidadeController:pesquisaUnidades" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		unidadeForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			UnidadeSalvaVO unidadePesquisa;
			ListaUnidadesPesquisaVO listaUnidadesPesquisaVO;
			unidadePesquisa = form.getUnidadeAtual();
			unidadeForm.setUnidadeTemporaria(unidadePesquisa);

			if (form != null && form.getUnidadeAtual() != null && form.getUnidadeAtual().getIdPagina() != null && form.getUnidadeAtual().getIdPagina().equals("0000")) {
				unidadePesquisa.setIdPagina(ConstantesCRM.SVAZIO);
			}

			unidadeForm.getUnidadeAtual().setIdPagina(form.getUnidadeAtual().getIdPagina());
			unidadeForm.getUnidadeAtual().setNmUnidade(form.getUnidadeAtual().getNmUnidade());
			unidadeForm.getUnidadeAtual().setCdUnidade(form.getUnidadeAtual().getCdUnidade());

			unidadePesquisa.setIdSistema(unidadeForm.getIdSistema());
			unidadePesquisa.setIdUnidade(ConstantesCRM.SVAZIO);
			unidadePesquisa.setInDetalhe(ConstantesCRM.SVAZIO);

			listaUnidadesPesquisaVO = controlSistema.pesquisaUnidades(unidadePesquisa, ConstantesCRM.getCurrentUser(request.getSession()));
			unidadeForm.setListaUnidadesLength(ConstantesCRM.SZERO);
			int countUnidades = unidadeForm.countUnidades(listaUnidadesPesquisaVO.getSubSistemaUnidadesVOArray());

			if (countUnidades > 0) {
				unidadeForm.setListaUnidadesLength(Integer.toString(countUnidades));
				unidadeForm.setListaUnidadesPesquisa(unidadeForm.converteUnidadesPesquisa(listaUnidadesPesquisaVO.getSubSistemaUnidadesVOArray(), countUnidades));
				unidadeForm.setIndicativoOperacao("Resultado");
			} else {
				unidadeForm.setIndicativoOperacao("Vazio");
			}

			unidadeForm.getUnidadeAtual().setNmUnidade(form.getUnidadeAtual().getNmUnidade());
			unidadeForm.getUnidadeAtual().setCdUnidade(form.getUnidadeAtual().getCdUnidade());

		} catch (TuxedoWarningException twe) {
			log.warn("ManterUnidadeController:pesquisaUnidades" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			unidadeForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {

			log.error("ManterUnidadeController:pesquisaUnidades" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget("top.frameApp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterUnidade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removeUnidade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterUnidadeController:removeUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		unidadeForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			ListaUnidadesPesquisaVO listaUnidadesPesquisaVO;
			String unidadeID = request.getParameter("unidadeId");
			String idPagina = request.getParameter("idPagina");
			unidadeForm.getUnidadeTemporaria().setIdUnidade(unidadeID);
			unidadeForm.getUnidadeTemporaria().setIdPagina(idPagina);

			listaUnidadesPesquisaVO = controlSistema.removeUnidade(unidadeForm.getUnidadeTemporaria(), ConstantesCRM.getCurrentUser(request.getSession()));
			unidadeForm.setListaUnidadesLength(ConstantesCRM.SZERO);

			int countUnidades = unidadeForm.countUnidades(listaUnidadesPesquisaVO.getSubSistemaUnidadesVOArray());

			if (countUnidades > 0) {
				unidadeForm.setListaUnidadesLength(Integer.toString(countUnidades));
				unidadeForm.setListaUnidadesPesquisa(unidadeForm.converteUnidadesPesquisa(listaUnidadesPesquisaVO.getSubSistemaUnidadesVOArray(), countUnidades));
			} else {
				unidadeForm.setIndicativoOperacao("Vazio");
				unidadeForm.setListaUnidadesLength("-1");
				unidadeForm.setListaUnidadesPesquisa(new UnidadePesquisaVO[0]);
			}
			unidadeForm.setMsgError(ConstantesCRM.SSucesso);
		} catch (TuxedoWarningException twe) {
			log.warn("ManterUnidadeController:removeUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());

			unidadeForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterUnidadeController:removeUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget("top.frameApp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluirAlterarUnidade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward incluirAlterarUnidade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UnidadeForm form = (UnidadeForm) formParam;

		log.debug("ManterUnidadeController:incluirAlterarUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (unidadeForm != null) {
			unidadeForm.setMsgError(ConstantesCRM.SVAZIO);
		}

		try {
			// getUnidadeById(String idUnidade, UnidadePesquisaVO[]
			// listaUnidades)
			UnidadeSalvaVO unidadeAtual = UnidadeSalvaVO.Factory.newInstance();
			// Recupera o id da unidade
			String idUnidade = form.getIdUnidade();
			// Procura a unidade com esse id
			UnidadePesquisaVO unidadeEditar = unidadeForm.getUnidadeById(idUnidade, unidadeForm.getListaUnidadesPesquisa());

			// Joga a unidade na variavel apropriada
			unidadeAtual.setIdSubSistema(unidadeEditar.getIdSubSistema());
			unidadeAtual.setIdPagina(unidadeEditar.getIdPagina());
			unidadeAtual.setIdUnidade(unidadeEditar.getIdUnidade());
			unidadeAtual.setNmUnidade(unidadeEditar.getNmUnidade());
			unidadeAtual.setCdUnidade(unidadeEditar.getCdUnidade());
			unidadeAtual.setInDetalhe(unidadeEditar.getInDetalhe());

			unidadeForm.setUnidadeAtual(unidadeAtual);

			// monta o select das paginas associadas ao id do subsistema a ser
			// editado.
			PaginaSimplVO[] PaginaSimplVO = unidadeForm.getListaPaginaById(unidadeEditar.getIdSubSistema(), unidadeForm.getListaSubSistemaPaginas());
			if (PaginaSimplVO != null) {
				unidadeForm.setListaPaginasVO(PaginaSimplVO);
			}

		} catch (Exception e) {
			log.error("ManterUnidadeController:incluirAlterarUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget("parent.frameApp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluirAlterarUnidade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward incluiUnidade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterUnidadeController:incluiUnidade" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		unidadeForm.getUnidadeAtual().setCdUnidade(ConstantesCRM.SVAZIO);
		unidadeForm.getUnidadeAtual().setIdPagina(ConstantesCRM.SVAZIO);
		unidadeForm.getUnidadeAtual().setIdSistema(ConstantesCRM.SVAZIO);
		unidadeForm.getUnidadeAtual().setIdSubSistema(ConstantesCRM.SVAZIO);
		unidadeForm.getUnidadeAtual().setIdUnidade(ConstantesCRM.SVAZIO);
		unidadeForm.getUnidadeAtual().setInDetalhe(ConstantesCRM.SVAZIO);
		unidadeForm.getUnidadeAtual().setNmUnidade(ConstantesCRM.SVAZIO);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class UnidadeForm extends ActionForm {

		private static final long serialVersionUID = -5543479797300188807L;

		private String msgError = ConstantesCRM.SVAZIO;
		private String indicativoOperacao;
		private UnidadeSalvaVO unidadeTemporaria;
		private SubSistemaUsuarioVO[] listaSubSistemasUsuarioVO;
		private String[] listaPaginas;
		private String[] listaSubSistemas;
		private UnidadePesquisaVO[] listaUnidadesPesquisa;
		private PaginaSimplVO[] listaPaginasVO;
		private SubSistemaPaginasVO[] listaSubSistemaPaginas;
		private UnidadeSalvaVO unidadePesquisa;
		private UnidadeSalvaVO unidadeAtual;
		private String listaUnidadesLength;
		private String idSistema;
		private String idUnidade;

		public UnidadeForm() {
			idSistema = ConstantesCRM.SVAZIO;
			idUnidade = ConstantesCRM.SVAZIO;
			listaUnidadesLength = ConstantesCRM.SZERO;
			unidadeAtual = UnidadeSalvaVO.Factory.newInstance();
			unidadePesquisa = UnidadeSalvaVO.Factory.newInstance();
			// inicializando listaSubSistemaPaginas
			listaSubSistemaPaginas = new SubSistemaPaginasVO[0];
			listaUnidadesPesquisa = new UnidadePesquisaVO[0];
			listaPaginasVO = new PaginaSimplVO[0];
			listaSubSistemas = new String[0];
			listaPaginas = new String[0];
		}

		public void setIdSistema(String idSistema) {
			this.idSistema = idSistema;
		}

		public String getIdSistema() {
			return this.idSistema;
		}

		public void setIdUnidade(String idUnidade) {
			this.idUnidade = idUnidade;
		}

		public String getIdUnidade() {
			return this.idUnidade;
		}

		public void setListaUnidadesLength(String listaUnidadesLength) {
			this.listaUnidadesLength = listaUnidadesLength;
		}

		public String getListaUnidadesLength() {
			return this.listaUnidadesLength;
		}

		// conta o numero de unidades presentes no retorno da pesquisa
		public int countUnidades(SubSistemaUnidadesVO[] subSistemaUnidades) {

			int count = 0;
			PaginaUnidadesVO[] listaPaginas;
			UnidadeSimplVO[] listaUnidades;

			for (int i = 0; i < subSistemaUnidades.length; i++) {
				listaPaginas = subSistemaUnidades[i].getPaginaUnidadesVOArray();
				for (int j = 0; j < listaPaginas.length; j++) {
					listaUnidades = listaPaginas[j].getUnidadeSimplVOArray();
					count += listaUnidades.length;
				}
			}
			if (subSistemaUnidades.length > 0) {
				if (subSistemaUnidades[0].getPaginaUnidadesVOArray(0).getNmPagina().equals("")) {
					return 0;
				}
			}
			return count;
		}

		public void setUnidadeAtual(UnidadeSalvaVO unidadeAtual) {
			this.unidadeAtual = unidadeAtual;
		}

		public UnidadeSalvaVO getUnidadeAtual() {
			return this.unidadeAtual;
		}

		public void setUnidadePesquisa(UnidadeSalvaVO unidadePesquisa) {
			this.unidadePesquisa = unidadePesquisa;
		}

		public UnidadeSalvaVO getUnidadePesquisa() {
			return this.unidadePesquisa;
		}

		public void setListaSubSistemaPaginas(SubSistemaPaginasVO[] listaSubSistemaPaginas) {
			this.listaSubSistemaPaginas = listaSubSistemaPaginas;
		}

		public SubSistemaPaginasVO[] getListaSubSistemaPaginas() {
			return this.listaSubSistemaPaginas;
		}

		public void setListaSubSistemas(String[] listaSubSistemas) {
			this.listaSubSistemas = listaSubSistemas;
		}

		public String[] getListaSubSistemas() {
			if (this.listaSubSistemas == null || this.listaSubSistemas.length == 0) {
				this.listaSubSistemas = new String[1];
			}
			return this.listaSubSistemas;
		}

		public void setListaPaginas(String[] listaPaginas) {
			this.listaPaginas = listaPaginas;
		}

		public String[] getListaPaginas() {
			if (this.listaPaginas == null || this.listaPaginas.length == 0) {
				this.listaPaginas = new String[1];
			}
			return this.listaPaginas;
		}

		public void setListaPaginasVO(PaginaSimplVO[] listaPaginasVO) {
			this.listaPaginasVO = listaPaginasVO;
		}

		public PaginaSimplVO[] getListaPaginasVO() {
			return this.listaPaginasVO;
		}

		// retorna a lista de paginas associadas ao id de um subsistema
		public PaginaSimplVO[] getListaPaginaById(String idSubSistema, SubSistemaPaginasVO[] subSistemaPaginas) {
			for (int i = 0; i < subSistemaPaginas.length; i++) {
				if (subSistemaPaginas[i].getIdSubSistema().equals(idSubSistema)) {
					return subSistemaPaginas[i].getPaginaSimplVOArray();
				}
			}
			return null;
		}

		public void setListaUnidadesPesquisa(UnidadePesquisaVO[] listaUnidadesPesquisa) {
			this.listaUnidadesPesquisa = listaUnidadesPesquisa;
		}

		public UnidadePesquisaVO[] getListaUnidadesPesquisa() {
			return this.listaUnidadesPesquisa;
		}

		// Pega o complexo array de retorno de uma pesquisa e transforma num
		// array de unidadesPesquisaVO
		public UnidadePesquisaVO[] converteUnidadesPesquisa(SubSistemaUnidadesVO[] listaUnidadesPesquisa, int length) {

			int count = 0;
			PaginaUnidadesVO[] listaPaginas;
			UnidadeSimplVO[] listaUnidades;
			UnidadePesquisaVO[] listaUnidadesComp;
			String idSubSistema, dsSubSistema;
			String idPagina, nmPagina;

			listaUnidadesComp = new UnidadePesquisaVO[length];
			// Pega o array de paginas por subsistema
			for (int i = 0; i < listaUnidadesPesquisa.length; i++) {
				listaPaginas = listaUnidadesPesquisa[i].getPaginaUnidadesVOArray();
				idSubSistema = listaUnidadesPesquisa[i].getIdSubSistema();
				dsSubSistema = listaUnidadesPesquisa[i].getDsSubSistema();

				// Pega o array de unidades por paginas
				for (int j = 0; j < listaPaginas.length; j++) {
					listaUnidades = listaPaginas[j].getUnidadeSimplVOArray();
					idPagina = listaPaginas[j].getIdPagina();
					nmPagina = listaPaginas[j].getNmPagina();

					// Monta o array de unidades
					for (int k = 0; k < listaUnidades.length; k++) {
						UnidadePesquisaVO unidade = UnidadePesquisaVO.Factory.newInstance();
						unidade.setIdSubSistema(idSubSistema);
						unidade.setDsSubSistema(dsSubSistema);
						unidade.setIdPagina(idPagina);
						unidade.setNmPagina(nmPagina);
						unidade.setIdUnidade(listaUnidades[k].getIdUnidade());
						unidade.setNmUnidade(listaUnidades[k].getNmUnidade());
						unidade.setCdUnidade(listaUnidades[k].getCdUnidade());
						unidade.setInDetalhe(listaUnidades[k].getInDetalhe());
						listaUnidadesComp[count] = unidade;
						count += 1;
					}
				}
			}
			return listaUnidadesComp;
		}

		// percorre o array de unidadePesquisaVO e retorna um deles caso o id
		// seja igual ao parametro passado
		public UnidadePesquisaVO getUnidadeById(String idUnidade, UnidadePesquisaVO[] listaUnidades) {
			for (int i = 0; i < listaUnidades.length; i++) {
				if (listaUnidades[i].getIdUnidade().equals(idUnidade)) {
					return listaUnidades[i];
				}
			}
			return null;
		}

		public void setListaSubSistemasUsuarioVO(SubSistemaUsuarioVO[] listaSubSistemasUsuarioVO) {
			this.listaSubSistemasUsuarioVO = listaSubSistemasUsuarioVO;
		}

		public SubSistemaUsuarioVO[] getListaSubSistemasUsuarioVO() {
			return this.listaSubSistemasUsuarioVO;
		}

		public void setUnidadeTemporaria(UnidadeSalvaVO unidadeTemporaria) {
			this.unidadeTemporaria = unidadeTemporaria;
		}

		public UnidadeSalvaVO getUnidadeTemporaria() {
			return this.unidadeTemporaria;
		}

		public void setIndicativoOperacao(String indicativoOperacao) {
			this.indicativoOperacao = indicativoOperacao;
		}

		public String getIndicativoOperacao() {
			return this.indicativoOperacao;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}
	}

	public UnidadeForm getUnidadeForm() {
		return (this.unidadeForm);
	}
}
