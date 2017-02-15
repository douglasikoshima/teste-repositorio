package usuario.manterSistema.editarParSistema.manterPagina;

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
import br.com.vivo.fo.usuario.vo.ListaPaginasUsuarioVODocument.ListaPaginasUsuarioVO;
import br.com.vivo.fo.usuario.vo.PaginaUsuarioVODocument.PaginaUsuarioVO;
import br.com.vivo.fo.usuario.vo.SistemaIDVODocument.SistemaIDVO;
import br.com.vivo.fo.usuario.vo.SubSistemaUsuarioVODocument.SubSistemaUsuarioVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ManterPaginaController extends AbstractAction {

	private static final long serialVersionUID = -1660370070731550400L;

	@EJB
	private ManterSistemaFacade controlSistema;

	// atributos e objetos
	private PaginaForm paginaForm = new PaginaForm();

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("usuario");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("listaPaginas".equals(mapping.getParameter())) {
			return listaPaginas(mapping, form, request, response);
		} else if ("limpaPagina".equals(mapping.getParameter())) {
			return limpaPagina(mapping, form, request, response);
		} else if ("salvaPagina".equals(mapping.getParameter())) {
			return salvaPagina(mapping, form, request, response);
		} else if ("editaPagina".equals(mapping.getParameter())) {
			return editaPagina(mapping, form, request, response);
		} else if ("removePagina".equals(mapping.getParameter())) {
			return removePagina(mapping, form, request, response);
		} else if ("incluirAlterarPagina".equals(mapping.getParameter())) {
			return incluirAlterarPagina(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="manterPagina.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PaginaForm form = (PaginaForm) formParam;

		log.debug("ManterPaginaController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		try {
			paginaForm = new PaginaForm();
			paginaForm.setIdSistema(request.getParameter("sistemaId"));

			// instancia o id do sistema
			SistemaIDVO sistemaIdListar = SistemaIDVO.Factory.newInstance();
			sistemaIdListar.setIdSistema(paginaForm.getIdSistema());

			// recupera todos os sub-sistemas relacionados ao ID de sistema
			SubSistemaUsuarioVO[] listaObjSubSistemas = controlSistema.listaSubSistemasPorSistema(sistemaIdListar, ConstantesCRM.getCurrentUser(request.getSession())).getSubSistemaUsuarioVOArray();

			// se algum resultado foi retornado, guarda os valores no formulario
			if (listaObjSubSistemas.length > 0) {
				paginaForm.setListaSubSistemasVO(listaObjSubSistemas);
			}

			form = paginaForm;

		} catch (Exception e) {
			log.error("ManterPaginaController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/index.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterPagina.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward listaPaginas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PaginaForm form = (PaginaForm) formParam;

		log.debug("ManterPaginaController:listaPaginas" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		paginaForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			ListaPaginasUsuarioVO listaPaginasUsuarioVO = ListaPaginasUsuarioVO.Factory.newInstance();

			// aloca um espaco no objeto listaPaginasUsuarioVO para o objeto PaginaUsuarioVO
			PaginaUsuarioVO paginaUsuarioVO = PaginaUsuarioVO.Factory.newInstance();
			listaPaginasUsuarioVO.insertNewPaginaUsuarioVO(0);

			// armazena o id do sistema
			listaPaginasUsuarioVO.setIdSistema(paginaForm.getIdSistema());

			// Grava os dados de pesquisa no objeto apropriado
			paginaUsuarioVO.setIdSubSistema(form.getIdSubSistema());
			paginaUsuarioVO.setNmPagina(form.getNmPagina());
			paginaUsuarioVO.setNmUrl(form.getNmUrl());
			paginaUsuarioVO.setInDisponib(form.getInDisponib());

			listaPaginasUsuarioVO.setPaginaUsuarioVOArray(0, paginaUsuarioVO);

			listaPaginasUsuarioVO = controlSistema.pesquisaPaginas(listaPaginasUsuarioVO, ConstantesCRM.getCurrentUser(request.getSession()));

			// caso a busca tenha retornado paginas
			if (listaPaginasUsuarioVO != null && listaPaginasUsuarioVO.getPaginaUsuarioVOArray().length > 0) {
				paginaForm.setListaPaginasVO(listaPaginasUsuarioVO.getPaginaUsuarioVOArray());
				paginaForm.setListaPaginasLength(Integer.toString(listaPaginasUsuarioVO.getPaginaUsuarioVOArray().length));
				// caso a busca não tenha retornado paginas
			} else {
				paginaForm.setListaPaginasVO(new PaginaUsuarioVO[0]);
				paginaForm.setListaPaginasLength("-1");
			}

			paginaForm.getPaginaAtual().setNmPagina(form.getNmPagina());
			paginaForm.getPaginaAtual().setIdSubSistema(form.getIdSubSistema());
			paginaForm.getPaginaAtual().setInDisponib(form.getInDisponib());
			paginaForm.getPaginaAtual().setNmUrl(form.getNmUrl());

		} catch (TuxedoWarningException twe) {
			log.warn("ManterPaginaController:listaPaginas" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			paginaForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {

			log.error("ManterPaginaController:listaPaginas" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="manterPagina.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward limpaPagina(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterPaginaController:limpaPagina" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		paginaForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			// Cria uma pagina vazia
			PaginaUsuarioVO paginaVazia = PaginaUsuarioVO.Factory.newInstance();

			// Limpa os dados do formulario
			paginaForm.setPaginaAtual(paginaVazia);
			paginaForm.setMsgError(ConstantesCRM.SVAZIO);

		} catch (Exception e) {
			log.error("ManterPaginaController:limpaPagina" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="manterPagina.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaPagina(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PaginaForm form = (PaginaForm) formParam;

		log.debug("ManterPaginaController:salvaPagina" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		paginaForm.setMsgError(ConstantesCRM.SVAZIO);

		try {

			String tipo = request.getParameter("tipo");
			String idPagina = request.getParameter("idPagina");

			// Grava os dados na variavel adequada.
			PaginaUsuarioVO paginaUsuarioVO = PaginaUsuarioVO.Factory.newInstance();
			paginaUsuarioVO.setIdSubSistema(form.getIdSubSistema());
			paginaUsuarioVO.setInDisponib(form.getInDisponib());
			paginaUsuarioVO.setNmPagina(form.getNmPagina());
			paginaUsuarioVO.setNmUrl(form.getNmUrl());

			ListaPaginasUsuarioVO listaPaginasUsuarioVO = ListaPaginasUsuarioVO.Factory.newInstance();

			// Chamo o servico para criar uma novo Pagina
			if (tipo.equals("novo")) {

				paginaUsuarioVO.setIdPagina(ConstantesCRM.SVAZIO);
				listaPaginasUsuarioVO = controlSistema.salvaPagina(paginaUsuarioVO, tipo, ConstantesCRM.getCurrentUser(request.getSession()));

				// Chamo o servico para editar uma pagina existente
			} else if (tipo.equals("edicao")) {

				paginaUsuarioVO.setIdPagina(idPagina);
				listaPaginasUsuarioVO = controlSistema.salvaPagina(paginaUsuarioVO, tipo, ConstantesCRM.getCurrentUser(request.getSession()));

				paginaForm.getPaginaAtual().setDsSubSistema(ConstantesCRM.SVAZIO);
				paginaForm.getPaginaAtual().setIdPagina(ConstantesCRM.SVAZIO);
				paginaForm.getPaginaAtual().setIdSubSistema(ConstantesCRM.SVAZIO);
				paginaForm.getPaginaAtual().setInDisponib(ConstantesCRM.SVAZIO);
				paginaForm.getPaginaAtual().setNmPagina(ConstantesCRM.SVAZIO);
				paginaForm.getPaginaAtual().setNmUrl(ConstantesCRM.SVAZIO);

			} else {
				throw new FOException("Tipo de insercao/edicao de páginas inválido ou nulo.");
			}

			// Testa se é necessário limpar o length das paginas
			if (paginaForm.getListaPaginasLength().equals("-1")) {
				paginaForm.setListaPaginasLength(ConstantesCRM.SZERO);
			}

			// grava o array de paginas retornado no formulario
			if (listaPaginasUsuarioVO.getPaginaUsuarioVOArray().length > 0) {
				paginaForm.setListaPaginasVO(listaPaginasUsuarioVO.getPaginaUsuarioVOArray());
				paginaForm.setListaPaginasLength(Integer.toString(listaPaginasUsuarioVO.getPaginaUsuarioVOArray().length));
			}
			paginaForm.setMsgError(ConstantesCRM.SSucesso);
		} catch (TuxedoWarningException twe) {
			log.warn("ManterPaginaController:salvaPagina" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			paginaForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterPaginaController:salvaPagina" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="manterPagina.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward editaPagina(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterPaginaController:editaPagina" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		paginaForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			PaginaUsuarioVO paginaAtual = PaginaUsuarioVO.Factory.newInstance();
			// Recupero o ID da pagina que foi clicada.
			paginaAtual.setIdPagina(request.getParameter("paginaId"));
			// Recupero o array de paginas do Form
			PaginaUsuarioVO[] listaPaginas = paginaForm.getListaPaginasVO();

			// Testa se é necessário limpar o length das paginas
			if (paginaForm.getListaPaginasLength().equals("-1")) {
				paginaForm.setListaPaginasLength(ConstantesCRM.SZERO);
			}

			// Procuro a pagina correspondente ao id recebido
			paginaAtual = paginaForm.getPaginaUsuarioVOById(paginaAtual.getIdPagina(), listaPaginas);

			// Joga para o formulario (atributo paginaAtual) os dados do pagina a ser editada
			paginaForm.setPaginaAtual(paginaAtual);

		} catch (Exception e) {
			log.error("ManterPaginaController:editaPagina" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
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
	 * @jpf:forward name="success" path="manterPagina.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removePagina(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterPaginaController:removePagina" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		paginaForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			PaginaUsuarioVO paginaRemover = PaginaUsuarioVO.Factory.newInstance();

			// Recupero o ID da pagina que foi clicada.
			paginaRemover.setIdPagina(request.getParameter("paginaId"));

			// Recupero o array de paginas do Form
			PaginaUsuarioVO[] listaPaginas = paginaForm.getListaPaginasVO();

			// Procuro a pagina correspondente ao id recebido
			paginaRemover = paginaForm.getPaginaUsuarioVOById(paginaRemover.getIdPagina(), listaPaginas);

			// remove a página do id passado.
			ListaPaginasUsuarioVO listaPaginasUsuarioVO = controlSistema.removePagina(paginaRemover, ConstantesCRM.getCurrentUser(request.getSession()));

			// grava o array de paginas retornado no formulario
			if (listaPaginasUsuarioVO.getPaginaUsuarioVOArray() != null) {
				paginaForm.setListaPaginasVO(listaPaginasUsuarioVO.getPaginaUsuarioVOArray());
				paginaForm.setListaPaginasLength(Integer.toString(listaPaginasUsuarioVO.getPaginaUsuarioVOArray().length));

			} else {
				paginaForm.setListaPaginasVO(new PaginaUsuarioVO[0]);
				paginaForm.setListaPaginasLength(ConstantesCRM.SZERO);
			}
			paginaForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("ManterPaginaController:removePagina" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			paginaForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterPaginaController:removePagina" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="incluirAlterarPagina.jsp"
	 */
	public ActionForward incluirAlterarPagina(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PaginaForm form = (PaginaForm) formParam;

		String operacao = (String) request.getParameter("operacao");
		log.debug("ManterPaginaController:incluirAlterarPagina" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) Operaqcao: " + operacao);
		
		if (!"incluir".equalsIgnoreCase(operacao)) {
			PaginaUsuarioVO pagina = paginaForm.getPaginaUsuarioVOById(form.getIdPagina(), paginaForm.getListaPaginasVO());
	
			paginaForm.getPaginaAtual().setNmPagina(pagina.getNmPagina());
			paginaForm.getPaginaAtual().setIdSubSistema(pagina.getIdSubSistema());
			paginaForm.getPaginaAtual().setInDisponib(pagina.getInDisponib());
			paginaForm.getPaginaAtual().setNmUrl(pagina.getNmUrl());
		}
		// paginaForm.setIdPagina(form.getIdPagina());
		// paginaForm.setNmPagina(pagina.getNmPagina());
		// paginaForm.setIdSubSistema(pagina.getIdSubSistema());
		// paginaForm.setNmUrl(pagina.getNmUrl());
		// paginaForm.setInDisponib(pagina.getInDisponib());
		// paginaForm.getListaSubSistemasVO()

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class PaginaForm extends ActionForm {

		private static final long serialVersionUID = 981477413634452909L;

		private String msgError = ConstantesCRM.SVAZIO;

		private String idPagina;
		private String idSubSistema;
		private String inDisponib;
		private String nmUrl;
		private String nmPagina;
		private String idSistema;
		private String listaPaginasLength;
		private PaginaUsuarioVO paginaAtual;
		private String[] listaPaginas;
		private PaginaUsuarioVO[] listaPaginasVO;
		private SubSistemaUsuarioVO[] listaSubSistemasVO;

		public PaginaForm() {

			// //////////////////////////////////
			// Bloco de Inicializacao - Begin //
			// //////////////////////////////////
			idSistema = ConstantesCRM.SVAZIO;
			idSubSistema = ConstantesCRM.SVAZIO;
			nmPagina = ConstantesCRM.SVAZIO;
			nmUrl = ConstantesCRM.SVAZIO;
			inDisponib = ConstantesCRM.SVAZIO;
			listaPaginasLength = ConstantesCRM.SZERO;
			paginaAtual = PaginaUsuarioVO.Factory.newInstance();
			listaPaginas = new String[0];
			listaPaginasVO = new PaginaUsuarioVO[0];
			listaSubSistemasVO = new SubSistemaUsuarioVO[0];

			// ////////////////////////////////
			// Bloco de Inicializacao - End //
			// ////////////////////////////////
		}

		public void setPaginaAtual(PaginaUsuarioVO paginaAtual) {

			this.paginaAtual = paginaAtual;
		}

		public PaginaUsuarioVO getPaginaAtual() {

			return this.paginaAtual;
		}

		public PaginaUsuarioVO getPaginaUsuarioVOById(String id, PaginaUsuarioVO[] paginas) {
			for (int i = 0; i < paginas.length; i++) {
				if (paginas[i].getIdPagina().equals(id)) {
					return paginas[i];
				}
			}
			return null;
		}

		public void setListaPaginasVO(PaginaUsuarioVO[] listaPaginasVO) {

			this.listaPaginasVO = listaPaginasVO;
		}

		public PaginaUsuarioVO[] getListaPaginasVO() {

			return this.listaPaginasVO;
		}

		public void setListaPaginas(String[] listaPaginas) {

			this.listaPaginas = listaPaginas;
		}

		public String[] getListaPaginas() {

			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.listaPaginas == null || this.listaPaginas.length == 0) {
				this.listaPaginas = new String[1];
			}

			return this.listaPaginas;
		}

		public void setListaSubSistemasVO(SubSistemaUsuarioVO[] listaSubSistemasVO) {

			this.listaSubSistemasVO = listaSubSistemasVO;
		}

		public SubSistemaUsuarioVO[] getListaSubSistemasVO() {

			return this.listaSubSistemasVO;
		}

		public void setIdSistema(String idSistema) {

			this.idSistema = idSistema;
		}

		public String getIdSistema() {

			return this.idSistema;
		}

		public void setListaPaginasLength(String listaPaginasLength) {

			this.listaPaginasLength = listaPaginasLength;
		}

		public String getListaPaginasLength() {

			return this.listaPaginasLength;
		}

		public void setNmPagina(String nmPagina) {

			this.nmPagina = nmPagina;
		}

		public String getNmPagina() {

			return this.nmPagina;
		}

		public void setInDisponib(String inDisponib) {

			this.inDisponib = inDisponib;
		}

		public String getInDisponib() {

			return this.inDisponib;
		}

		public void setIdSubSistema(String idSubSistema) {

			this.idSubSistema = idSubSistema;
		}

		public String getIdSubSistema() {

			return this.idSubSistema;
		}

		public void setNmUrl(String nmUrl) {
			this.nmUrl = nmUrl;
		}

		public String getNmUrl() {
			return this.nmUrl;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}

		public void setIdPagina(String idPagina) {
			this.idPagina = idPagina;
		}

		public String getIdPagina() {
			return this.idPagina;
		}
	}

	public PaginaForm getPaginaForm() {

		return (this.paginaForm);
	}

}
