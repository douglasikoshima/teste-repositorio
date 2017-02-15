package usuario.manterSistema.editarParSistema;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.usuario.manterSistema.ManterSistemaFacade;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.ItemMenuEditarVODocument.ItemMenuEditarVO;
import br.com.vivo.fo.usuario.vo.ItemMenuInserirVODocument.ItemMenuInserirVO;
import br.com.vivo.fo.usuario.vo.ItemMenuMoverVODocument.ItemMenuMoverVO;
import br.com.vivo.fo.usuario.vo.ItemMenuRemoverVODocument.ItemMenuRemoverVO;
import br.com.vivo.fo.usuario.vo.ItemMenuVODocument.ItemMenuVO;
import br.com.vivo.fo.usuario.vo.ListaSistemaUsuarioVODocument.ListaSistemaUsuarioVO;
import br.com.vivo.fo.usuario.vo.ListaSubSistemasPaginasVODocument.ListaSubSistemasPaginasVO;
import br.com.vivo.fo.usuario.vo.PaginaSimplVODocument.PaginaSimplVO;
import br.com.vivo.fo.usuario.vo.SistemaIDArvoreVODocument.SistemaIDArvoreVO;
import br.com.vivo.fo.usuario.vo.SistemaIDVODocument.SistemaIDVO;
import br.com.vivo.fo.usuario.vo.SistemaManterUsuarioVODocument.SistemaManterUsuarioVO;
import br.com.vivo.fo.usuario.vo.SubSistemaPaginasVODocument.SubSistemaPaginasVO;
import br.com.vivo.fo.usuario.vo.SubSistemaUsuarioVODocument.SubSistemaUsuarioVO;
import br.com.vivo.fo.usuario.vo.SubSistemasUsuarioVODocument.SubSistemasUsuarioVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class EditarParSistemaController extends AbstractAction {

	private static final long serialVersionUID = -4813639125976202018L;

	@EJB
	private ManterSistemaFacade controlSistema;

	private SistemasForm sistemasForm = new SistemasForm();
	private FormArvore formArvore = new FormArvore();

	private ItemMenuVO item = null;

	public static final String FOLHA = "1";
	public static final String PASTA = "0";
	public static final String VISIVEL = "1";
	public static final String INVISIVEL = "0";

	private static transient Logger log = new Logger("usuario");

	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("iniciaArvore".equals(mapping.getParameter())) {
			return iniciaArvore(mapping, form, request, response);			
		} else if ("alterarSistema".equals(mapping.getParameter())) {
			return alterarSistema(mapping, form, request, response);
		} else if ("listaSistemas".equals(mapping.getParameter())) {
			return listaSistemas(mapping, form, request, response);
		} else if ("editaSistema".equals(mapping.getParameter())) {
			return editaSistema(mapping, form, request, response);
		} else if ("limpaSistemas".equals(mapping.getParameter())) {
			return limpaSistemas(mapping, form, request, response);
		} else if ("salvaSistema".equals(mapping.getParameter())) {
			return salvaSistema(mapping, form, request, response);
		} else if ("removeSistema".equals(mapping.getParameter())) {
			return removeSistema(mapping, form, request, response);
		} else if ("montaSubSistema".equals(mapping.getParameter())) {
			return montaSubSistema(mapping, form, request, response);
		} else if ("inserirItemMenu".equals(mapping.getParameter())) {
			return inserirItemMenu(mapping, form, request, response);
		} else if ("editarItemMenu".equals(mapping.getParameter())) {
			return editarItemMenu(mapping, form, request, response);
		} else if ("salvaItemEditado".equals(mapping.getParameter())) {
			return salvaItemEditado(mapping, form, request, response);
		} else if ("insereItem".equals(mapping.getParameter())) {
			return insereItem(mapping, form, request, response);
		} else if ("removeItem".equals(mapping.getParameter())) {
			return removeItem(mapping, form, request, response);
		} else if ("decrementaIncrementaSequencia".equals(mapping.getParameter())) {
			return decrementaIncrementaSequencia(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterSistema.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			log.debug("EditarParSistemaController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
			sistemasForm = new SistemasForm();
			/*if (sistemasForm.getListaSistemas() != null && sistemasForm.getListaSistemas().length > 0) {
				sistemasForm.setListaSistemasLength(String.valueOf(sistemasForm.getListaSistemas().length));
			}*/
			sistemasForm.setMsgError(ConstantesCRM.SVAZIO);

		} catch (Exception e) {
			log.error("EditarParSistemaController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/index.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private String trataStringRetorno(String strParam) {
		if (strParam == null) {
			return null;
		}
		strParam = strParam.replaceAll("'", "&#39;");
		strParam = strParam.replaceAll("\"", "&#34;");
		strParam = strParam.replaceAll("\\\\", "\\\\\\\\");
		strParam = strParam.replaceAll("Ç", "&#199;");
		strParam = strParam.replaceAll("ç", "&#231;");
		return strParam;
	}

	private String trataStringRetorno1(String strParam) {
		if (strParam == null) {
			return null;
		}
		strParam = strParam.replaceAll("'", "'+String.fromCharCode(39)+'");
		strParam = strParam.replaceAll("\"", "'+String.fromCharCode(34)+'");
		strParam = strParam.replaceAll("\\\\", "'+String.fromCharCode(92)+String.fromCharCode(92)+'");
		strParam = strParam.replaceAll("Ç", "'+String.fromCharCode(199)+'");
		strParam = strParam.replaceAll("ç", "'+String.fromCharCode(231)+'");
		return strParam;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="alterarSistema.jsp"
	 */
	public ActionForward alterarSistema(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("EditarParSistemaController:alterarSistema" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (sistemasForm == null) {
			sistemasForm = new SistemasForm();
		}
		sistemasForm.setMsgError(ConstantesCRM.SVAZIO);

		// operacao=alterar ou operacao=incluir
		String dsOperacao = request.getParameter("operacao");

		SistemaManterUsuarioVO sistemaManterUsuarioVO = SistemaManterUsuarioVO.Factory.newInstance();
		if (dsOperacao.equals("alterar")) {
			// Recupera o id do sistema
			String idSistema = request.getParameter("idSistema");
			// Procura a unidade com esse id
			sistemaManterUsuarioVO = sistemasForm.getSistemaById(idSistema, sistemasForm.getListaSistemas());
			sistemasForm.setIdSistema(sistemaManterUsuarioVO.getIdSistema());
			sistemasForm.setDsSistema(sistemaManterUsuarioVO.getDsSistema());
			sistemasForm.setSgSistema(sistemaManterUsuarioVO.getSgSistema());
			sistemasForm.setNmUrlBase(sistemaManterUsuarioVO.getNmUrlBase());
			sistemasForm.setInAcessoControlado(sistemaManterUsuarioVO.getInAcessoControlado());

		} else {
			sistemasForm.setIdSistema(ConstantesCRM.SVAZIO);
			sistemasForm.setDsSistema(ConstantesCRM.SVAZIO);
			sistemasForm.setSgSistema(ConstantesCRM.SVAZIO);
			sistemasForm.setNmUrlBase(ConstantesCRM.SVAZIO);
			sistemasForm.setInAcessoControlado(ConstantesCRM.SVAZIO);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterSistema.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward listaSistemas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("EditarParSistemaController:listaSistemas" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (sistemasForm == null) {
			sistemasForm = new SistemasForm();
		}
		sistemasForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			SistemaManterUsuarioVO sistemaManterUsuarioVO = SistemaManterUsuarioVO.Factory.newInstance();

			sistemaManterUsuarioVO.setDsSistema(request.getParameter("dsSistema"));
			sistemaManterUsuarioVO.setSgSistema(request.getParameter("sgSistema"));
			sistemaManterUsuarioVO.setNmUrlBase(request.getParameter("nmUrlBase"));
			sistemaManterUsuarioVO.setInAcessoControlado(request.getParameter("inAcessoControlado"));

			// Lista todos os sistemas existentes e armazena nas variaveis dos
			// formularios
			ListaSistemaUsuarioVO listaSistemaUsuarioVO = controlSistema.listaSistemas(sistemaManterUsuarioVO, ConstantesCRM.getCurrentUser(request.getSession()));
			sistemasForm.setListaSistemas(listaSistemaUsuarioVO.getSistemaManterUsuarioVOArray());

			if (listaSistemaUsuarioVO.getSistemaManterUsuarioVOArray().length > 0) {
				// Armazena a qtidade de sistemas retornados
				sistemasForm.setListaSistemasLength(Integer.toString(sistemasForm.getListaSistemas().length));
			} else {
				// Armazena a qtidade de sistemas retornados
				sistemasForm.setListaSistemasLength("-1");
			}

			sistemasForm.getSistema().setDsSistema(sistemaManterUsuarioVO.getDsSistema());
			sistemasForm.getSistema().setSgSistema(sistemaManterUsuarioVO.getSgSistema());
			sistemasForm.getSistema().setNmUrlBase(sistemaManterUsuarioVO.getNmUrlBase());
			sistemasForm.getSistema().setInAcessoControlado(sistemaManterUsuarioVO.getInAcessoControlado());

		} catch (TuxedoWarningException twe) {
			log.warn("EditarParSistemaController:listaSistemas" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			sistemasForm.setMsgError(twe.getMessage());

		} catch (Exception e) {
			log.error("EditarParSistemaController:listaSistemas" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="param" path="editarParSistema.jsp"
	 * @jpf:forward name="dados" path="manterSistema.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward editaSistema(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("EditarParSistemaController:editaSistema" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		String retorno = ConstantesCRM.SVAZIO;
		if (sistemasForm == null) {
			sistemasForm = new SistemasForm();
		}

		sistemasForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			if (request.getAttribute("arvore").equals("exibe")) {
				request.setAttribute("aba", "abaSelected(btAba, bt02);CarregaAba('bt02');");
				request.setAttribute("arvore", ConstantesCRM.SVAZIO);
			} else {
				request.setAttribute("aba", "abaSelected(btAba, bt01);CarregaAba('bt01');");
				// Recupero o ID do sistema clicado e salvo no formulario
				// sistemasForm.setIdSistema(request.getParameter("sistemaId"));
			}
		} catch (Exception e) {
			request.setAttribute("aba", "abaSelected(btAba, bt01);CarregaAba('bt01');");
		}

		try {
			// Recupero o tipo de edicao de dados (Dados ou parametros do
			// sistema)
			retorno = request.getParameter("tipo");

			// Recupero o ID do sistema clicado e salvo no formulario
			sistemasForm.setIdSistema(request.getParameter("sistemaId"));

			// Recupero o array de sistemas do Form
			SistemaManterUsuarioVO[] listaSistemas = sistemasForm.getListaSistemas();

			// Procura o sistema correspondente ao id recebido
			SistemaManterUsuarioVO sistemaEdit = sistemasForm.getSistemaUsuarioVOById(sistemasForm.getIdSistema(), listaSistemas);

			// Joga para o formulario (atributo itemSistema) os dados do sistema
			// a ser editado
			sistemasForm.setSistema(sistemaEdit);
			sistemasForm.setDsSistema(sistemaEdit.getDsSistema());

		} catch (Exception e) {
			log.error("EditarParSistemaController:editaSistema" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget("parent.frameApp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(retorno);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterSistema.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward limpaSistemas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("EditarParSistemaController:limpaSistemas" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (sistemasForm == null) {
			sistemasForm = new SistemasForm();
		}
		sistemasForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			// Cria um sistema vazio
			SistemaManterUsuarioVO sistemaVazio = SistemaManterUsuarioVO.Factory.newInstance();

			// Limpa os dados do formulario
			sistemasForm.setIdSistema(ConstantesCRM.SVAZIO);
			sistemasForm.setIdSistema(ConstantesCRM.SVAZIO);
			sistemasForm.setSistema(sistemaVazio);
			sistemasForm.setMsgError(ConstantesCRM.SVAZIO);

		} catch (Exception e) {

			log.error("EditarParSistemaController:limpaSistemas" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="manterSistema.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaSistema(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SistemasForm form = (SistemasForm) formParam;

		log.debug("EditarParSistemaController:salvaSistema" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (sistemasForm == null) {
			sistemasForm = new SistemasForm();
		}
		sistemasForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			// Lista todos os sistemas existentes e armazena nas variaveis dos
			// formularios
			ListaSistemaUsuarioVO listaSistemaUsuarioVO;

			sistemasForm = form;

			SistemaManterUsuarioVO sistemaEditar = form.getSistema();

			sistemaEditar.setIdSistema(form.getIdSistema());
			sistemaEditar.setSgSistema(form.getSgSistema());
			sistemaEditar.setDsSistema(form.getDsSistema());
			sistemaEditar.setNmUrlBase(form.getNmUrlBase());
			sistemaEditar.setInAcessoControlado(form.getInAcessoControlado());

			// Se nao tiver um id setado é a insercao de um sistema
			if (sistemasForm.getIdSistema().equals(ConstantesCRM.SVAZIO)) {
				listaSistemaUsuarioVO = controlSistema.adicionaSistema(sistemaEditar, ConstantesCRM.getCurrentUser(request.getSession()));
			}
			// caso contrario e uma edicao
			else {
				listaSistemaUsuarioVO = controlSistema.salvaSistemaEditado(sistemaEditar, ConstantesCRM.getCurrentUser(request.getSession()));
			}

			// grava a lista de sistemas atualizada na variavel apropriada.
			sistemasForm.setListaSistemas(listaSistemaUsuarioVO.getSistemaManterUsuarioVOArray());
			// Armazena a qtidade de sistemas retornados
			sistemasForm.setListaSistemasLength(Integer.toString(sistemasForm.getListaSistemas().length));

			// limpa os dados do sistema editado ou criado
			sistemasForm.setIdSistema(ConstantesCRM.SVAZIO);
			sistemasForm.setDsSistema(ConstantesCRM.SVAZIO);
			sistemasForm.setSgSistema(ConstantesCRM.SVAZIO);
			sistemasForm.setNmUrlBase(ConstantesCRM.SVAZIO);
			SistemaManterUsuarioVO sistemaVazio = SistemaManterUsuarioVO.Factory.newInstance();
			sistemasForm.setSistema(sistemaVazio);
			sistemasForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("EditarParSistemaController:salvaSistema" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());

			sistemasForm.setDsSistema(ConstantesCRM.SVAZIO);
			sistemasForm.setIdSistema(ConstantesCRM.SVAZIO);
			sistemasForm.setSistema(SistemaManterUsuarioVO.Factory.newInstance());
			sistemasForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("EditarParSistemaController:salvaSistema" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="manterSistema.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removeSistema(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("EditarParSistemaController:removeSistema" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (sistemasForm == null) {
			sistemasForm = new SistemasForm();
		}

		sistemasForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			// Recupero o ID do sistema clicado e salvo no formulario
			sistemasForm.setIdSistema(request.getParameter("sistemaId"));
			SistemaIDVO sistemaIDVO = SistemaIDVO.Factory.newInstance();
			sistemaIDVO.setIdSistema(request.getParameter("sistemaId"));

			// remove o sistema
			ListaSistemaUsuarioVO listaSistemaUsuarioVO = controlSistema.removeSistema(sistemaIDVO, ConstantesCRM.getCurrentUser(request.getSession()));

			// carrega a nova lista de sistemas
			sistemasForm.setListaSistemas(listaSistemaUsuarioVO.getSistemaManterUsuarioVOArray());
			// Armazena a qtidade de sistemas retornados
			sistemasForm.setListaSistemasLength(Integer.toString(sistemasForm.getListaSistemas().length));
			sistemasForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("EditarParSistemaController:removeSistema" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )  ");
			sistemasForm.setDsSistema(ConstantesCRM.SVAZIO);
			sistemasForm.setIdSistema(ConstantesCRM.SVAZIO);
			sistemasForm.setSistema(SistemaManterUsuarioVO.Factory.newInstance());
			sistemasForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("EditarParSistemaController:removeSistema" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget("parent.frameApp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private FormArvore processamentoDeArvore(FormArvore form, HttpServletRequest request) throws TuxedoWarningException, Exception {
		item = ItemMenuVO.Factory.newInstance();
		String corpoArvore = ConstantesCRM.SVAZIO;

		if (request.getParameter("idSubSistemaEscolhido") != null) {
			form.setIdSubSistemaEscolhido(request.getParameter("idSubSistemaEscolhido"));
		}

		SistemaIDArvoreVO sistemaIDArvoreVO = SistemaIDArvoreVO.Factory.newInstance();
		sistemaIDArvoreVO.setIdSubSistema(form.getIdSubSistemaEscolhido());
		formArvore.setIdSubSistemaEscolhido(form.getIdSubSistemaEscolhido());
		item = controlSistema.carregaArvoreMenu(sistemaIDArvoreVO, ConstantesCRM.getCurrentUser(request.getSession()));

		SistemaIDVO sistemaIDVO = SistemaIDVO.Factory.newInstance();
		sistemaIDVO.setIdSistema(formArvore.getIdSistema());
		ListaSubSistemasPaginasVO listaSubSistemasPaginasVO = controlSistema.listaSubSistemasPaginas(sistemaIDVO, ConstantesCRM.getCurrentUser(request.getSession()));
		formArvore.setIdSubSistemaEscolhido(form.getIdSubSistemaEscolhido());
		String lista = montaListaSubSistemaEscolhido(listaSubSistemasPaginasVO);
		if (request.getSession().getAttribute("lista_menu_pagina") != null) {
			request.getSession().removeAttribute("lista_menu_pagina");
		}
		request.getSession().setAttribute("lista_menu_pagina", lista);

		/*
		 * do{ if(item.getIdItemMenu().equals("")) { ItemMenuInserirVO itemMenuInserirVO =
		 * ItemMenuInserirVO.Factory.newInstance(); itemMenuInserirVO.setDsHint(formArvore.getDsSubSistema());
		 * itemMenuInserirVO.setIdItemMenuPai(""); itemMenuInserirVO.setIdPagina("");
		 * itemMenuInserirVO.setInVisibilidade("1"); itemMenuInserirVO.setNmMenu(formArvore.getDsSubSistema());
		 * 
		 * itemMenuInserirVO.setIdSubSistema(form.getIdSubSistemaEscolhido());
		 * 
		 * controlSistema.inserirItemMenu(itemMenuInserirVO,globalApp.getCurrentUser (request.getSession(), this));
		 * 
		 * sistemaIDArvoreVO =SistemaIDArvoreVO.Factory.newInstance(); if (form.getIdSubSistemaEscolhido()!=null)
		 * sistemaIDArvoreVO.setIdSubSistema(form.getIdSubSistemaEscolhido()); else
		 * sistemaIDArvoreVO.setIdSubSistema((String)request.getParameter ("subSistema")); item =
		 * ItemMenuVO.Factory.newInstance(); item = controlSistema
		 * .carregaArvoreMenu(sistemaIDArvoreVO,globalApp.getCurrentUser (request.getSession(), this)); } else
		 * if(item.getNmMenu().equals(formArvore.getDsSubSistema())) { break; } else {
		 * 
		 * ItemMenuEditarVO itemMenuEditarVO = ItemMenuEditarVO.Factory.newInstance();
		 * itemMenuEditarVO.setDsHint(formArvore.getDsSubSistema()); itemMenuEditarVO.setIdItemMenuPai("");
		 * itemMenuEditarVO.setIdPagina(""); itemMenuEditarVO.setInVisibilidade("1");
		 * itemMenuEditarVO.setIdItemMenu(item.getIdItemMenu());
		 * itemMenuEditarVO.setIdSubSistema(form.getIdSubSistemaEscolhido());
		 * itemMenuEditarVO.setNmMenu(formArvore.getDsSubSistema()); controlSistema
		 * .salvarItemEditado(itemMenuEditarVO,globalApp.getCurrentUser (request.getSession(), this)); item =
		 * controlSistema.carregaArvoreMenu(sistemaIDArvoreVO ,ConstantesCRM.getCurrentUser(request.getSession()));
		 * 
		 * break; } }
		 */

		// while(item.getIdItemMenu().equals(""));

		String script = "if (document.getElementById) {var tree = new WebFXTree('" + trataStringRetorno(item.getNmMenu()) + "',\"Javascript:document.getElementById('btincluir').style.display='block';document.getElementById('btalterar').style.display='none';document.getElementById('btremover').style.display='none';document.getElementById('btup').style.display='none';document.getElementById('btdown').style.display='none';capturaRaiz('" + item.getIdItemMenu() + "' , '" + item.getNrNivel()
		+ "' , '" + item.getIdItemMenuPai() + "' , '" + item.getInVisibilidade() + "' , '" + item.getSqSequencia();

		if (item.getInFolha().trim().equals(FOLHA)) {
			if (item.getInVisibilidade().trim().equals(VISIVEL)) {
				script = script + "','" + item.getInFolha() + "');\",'','/FrontOfficeWeb/resources/images/file.png','/FrontOfficeWeb/resources/images/file.png');";
			} else if (item.getInVisibilidade().trim().equals(INVISIVEL)) {
				script = script + "','" + item.getInFolha() + "');\",'','/FrontOfficeWeb/resources/images/page_invisible.gif','/FrontOfficeWeb/resources/images/page_invisible.gif');";
			}
		} else if (item.getInFolha().trim().equals(PASTA)) {
			if (item.getInVisibilidade().trim().equals(VISIVEL)) {
				script = script + "','" + item.getInFolha() + "');\",'','/FrontOfficeWeb/resources/images/foldericon.png','/FrontOfficeWeb/resources/images/openfoldericon.png');";
			} else if (item.getInVisibilidade().trim().equals(INVISIVEL)) {
				script = script + "','" + item.getInFolha() + "');\",'','/FrontOfficeWeb/resources/images/foldericon_invisible.gif','/FrontOfficeWeb/resources/images/openfolder_invisible.gif');";
			}
		}

		corpoArvore = VerificaItemMenu(item, "tree");

		script = script + corpoArvore + "document.write(tree);}";

		if (request.getAttribute("arvore") != null) {
			request.removeAttribute("arvore");
		}
		request.setAttribute("arvore", script);

		SubSistemaPaginasVO subSistemaPaginasVO = SubSistemaPaginasVO.Factory.newInstance();
		subSistemaPaginasVO.setIdSubSistema("000");
		subSistemaPaginasVO.setDsSubSistema("Escolha uma opção...");
		listaSubSistemasPaginasVO.insertNewSubSistemaPaginasVO(0);
		listaSubSistemasPaginasVO.setSubSistemaPaginasVOArray(0, subSistemaPaginasVO);

		formArvore.setSubSistemaPaginasVO(listaSubSistemasPaginasVO.getSubSistemaPaginasVOArray());

		return form;
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="manterMenu.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward iniciaArvore(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormArvore form = (FormArvore) formParam;

		log.debug("EditarParSistemaController:iniciaArvore" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (formArvore == null) {
			formArvore = new FormArvore();
		}
		formArvore.setMsgError(ConstantesCRM.SVAZIO);
		try {
			form = processamentoDeArvore(form, request);

		} catch (TuxedoWarningException twe) {
			log.warn("EditarParSistemaController:iniciaArvore" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());

			formArvore.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {

			log.error("EditarParSistemaController:iniciaArvore" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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

	private String VerificaItemMenu(ItemMenuVO itens, String tree) {

		String node = ConstantesCRM.SVAZIO;

		if (itens.getItemMenuVOArray().length == 0) {
			return ConstantesCRM.SVAZIO;
		} else {
			for (int x = 0; x < itens.getItemMenuVOArray().length; x++) {
				if (itens.getItemMenuVOArray(x).getInFolha().trim().equals(FOLHA)) {
					node = node + "var " + tree + String.valueOf(x) + " = new WebFXTreeItem('" + trataStringRetorno(itens.getItemMenuVOArray(x).getNmMenu()) + "',\"Javascript:document.getElementById('btincluir').style.display='none';document.getElementById('btalterar').style.display='block';document.getElementById('btremover').style.display='block';document.getElementById('btup').style.display='block';document.getElementById('btdown').style.display='block';capturaParametrosComPagina('"
					+ itens.getItemMenuVOArray(x).getIdItemMenu() + "', '" + itens.getItemMenuVOArray(x).getNrNivel() + "' , '" + itens.getItemMenuVOArray(x).getIdItemMenuPai() + "' , '" + itens.getItemMenuVOArray(x).getInVisibilidade() + "' ,' " + itens.getItemMenuVOArray(x).getSqSequencia() + "' , '" + itens.getItemMenuVOArray(x).getSistemaPaginaVO().getIdPagina() + "' , '" + itens.getItemMenuVOArray(x).getSistemaPaginaVO().getIdSubSistema();

					if (itens.getItemMenuVOArray(x).getInVisibilidade().trim().equals(VISIVEL)) {
						node = node + "','" + item.getInFolha() + "');\",'','/FrontOfficeWeb/resources/images/file.png','/FrontOfficeWeb/resources/images/file.png');";
					} else if (itens.getItemMenuVOArray(x).getInVisibilidade().trim().equals(INVISIVEL)) {
						node = node + "','" + item.getInFolha() + "');\",'','/FrontOfficeWeb/resources/images/page_invisible.gif','/FrontOfficeWeb/resources/images/page_invisible.gif');";
					}

				} else {
					node = node + "var " + tree + String.valueOf(x) + " = new WebFXTreeItem('" + trataStringRetorno(itens.getItemMenuVOArray(x).getNmMenu()) + "',\"Javascript:document.getElementById('btincluir').style.display='block';document.getElementById('btalterar').style.display='block';document.getElementById('btremover').style.display='block';document.getElementById('btup').style.display='block';document.getElementById('btdown').style.display='block';capturaParametrosSemPagina('"
					+ itens.getItemMenuVOArray(x).getIdItemMenu() + "' , '" + itens.getItemMenuVOArray(x).getNrNivel() + "' , '" + itens.getItemMenuVOArray(x).getIdItemMenuPai() + "' , '" + itens.getItemMenuVOArray(x).getInVisibilidade() + "' , '" + itens.getItemMenuVOArray(x).getSqSequencia();

					if (itens.getItemMenuVOArray(x).getInVisibilidade().trim().equals(VISIVEL)) {
						node = node + "','" + item.getInFolha() + "');\",'','/FrontOfficeWeb/resources/images/foldericon.png','/FrontOfficeWeb/resources/images/openfoldericon.png');";
					} else if (itens.getItemMenuVOArray(x).getInVisibilidade().trim().equals(INVISIVEL)) {
						node = node + "','" + item.getInFolha() + "');\",'','/FrontOfficeWeb/resources/images/foldericon_invisible.gif','/FrontOfficeWeb/resources/images/openfolder_invisible.gif');";
					}

				}
				node = node + VerificaItemMenu(itens.getItemMenuVOArray(x), tree + String.valueOf(x));
				node = node + tree + ".add(" + tree + String.valueOf(x) + ");\n";
			}
		}
		return node;
	}

	private String montaListaSubSistemaEscolhido(ListaSubSistemasPaginasVO listaSubSistemasPaginasVO) {
		String texto = "<script language=\"Javascript\">";
		texto = texto + "function montaLista() {document.forms[0].paginaNova.options.length=0;";
		for (int i = 0; i < listaSubSistemasPaginasVO.getSubSistemaPaginasVOArray().length; i++) {
			if (listaSubSistemasPaginasVO.getSubSistemaPaginasVOArray(i).getPaginaSimplVOArray().length > 0) {
				if (listaSubSistemasPaginasVO.getSubSistemaPaginasVOArray(i).getPaginaSimplVOArray().length > 0) {
					for (int k = 0; k < listaSubSistemasPaginasVO.getSubSistemaPaginasVOArray().length; k++) {
						if (formArvore.getIdSubSistemaEscolhido().equalsIgnoreCase(listaSubSistemasPaginasVO.getSubSistemaPaginasVOArray(k).getIdSubSistema())) {
							formArvore.setDsSubSistema(listaSubSistemasPaginasVO.getSubSistemaPaginasVOArray(k).getDsSubSistema());
						}
					}
				}
				texto = texto + "if (document.forms[0].subSistemaNovo.value == '" + listaSubSistemasPaginasVO.getSubSistemaPaginasVOArray(i).getIdSubSistema() + "'){";
				texto = texto + "document.forms[0].paginaNova.options[document.forms[0].paginaNova.length] = new Option('" + trataStringRetorno1("Escolha uma opção...") + "','');";
				for (int j = 0; j < listaSubSistemasPaginasVO.getSubSistemaPaginasVOArray(i).getPaginaSimplVOArray().length; j++) {
					texto = texto + "document.forms[0].paginaNova.options[document.forms[0].paginaNova.length] = new Option('" + trataStringRetorno1(listaSubSistemasPaginasVO.getSubSistemaPaginasVOArray(i).getPaginaSimplVOArray(j).getNmPagina()) + "','" + listaSubSistemasPaginasVO.getSubSistemaPaginasVOArray(i).getPaginaSimplVOArray(j).getIdPagina() + "');";
				}
				texto = texto + "}";
			} else {
				texto = texto + "if (document.forms[0].subSistemaNovo.value == '" + listaSubSistemasPaginasVO.getSubSistemaPaginasVOArray(i).getIdSubSistema() + "'){";
				texto = texto + "document.forms[0].paginaNova.options[document.forms[0].paginaNova.length] = new Option('" + trataStringRetorno1("Escolha uma opção...") + "','');";
				texto = texto + "}";
			}
		}
		texto = texto + "}";
		return texto + "</script>";
	}

	@SuppressWarnings("unused")
	private String montaLista(ListaSubSistemasPaginasVO listaSubSistemasPaginasVO) {
		String texto = "<script language=\"Javascript\">";
		texto = texto + "function montaLista() {document.forms[0].paginaNova.options.length=0;";
		for (int i = 0; i < listaSubSistemasPaginasVO.getSubSistemaPaginasVOArray().length; i++) {
			if (listaSubSistemasPaginasVO.getSubSistemaPaginasVOArray(i).getIdSubSistema().equalsIgnoreCase(formArvore.getIdSubSistemaEscolhido()) && listaSubSistemasPaginasVO.getIdSistema().equalsIgnoreCase(formArvore.getIdSistema())) {
				if (listaSubSistemasPaginasVO.getSubSistemaPaginasVOArray(i).getPaginaSimplVOArray().length > 0) {

					formArvore.setSubSistemaPaginasVO(listaSubSistemasPaginasVO.getSubSistemaPaginasVOArray());
					String dsSubSistema = ConstantesCRM.SVAZIO;
					for (int k = 0; k < listaSubSistemasPaginasVO.getSubSistemaPaginasVOArray().length; k++) {
						if (formArvore.getIdSubSistemaEscolhido().equalsIgnoreCase(listaSubSistemasPaginasVO.getSubSistemaPaginasVOArray(k).getIdSubSistema())) {
							dsSubSistema = listaSubSistemasPaginasVO.getSubSistemaPaginasVOArray(k).getDsSubSistema();
						}
					}
					formArvore.setDsSubSistema(dsSubSistema);
					formArvore.setSubSistemaListaPaginas(listaSubSistemasPaginasVO.getSubSistemaPaginasVOArray(i).getPaginaSimplVOArray());

					texto = texto + "if (document.forms[0].subSistemaNovo.value == '" + listaSubSistemasPaginasVO.getSubSistemaPaginasVOArray(i).getIdSubSistema() + "'){";
					for (int j = 0; j < listaSubSistemasPaginasVO.getSubSistemaPaginasVOArray(i).getPaginaSimplVOArray().length; j++) {
						texto = texto + "document.forms[0].paginaNova.options[document.forms[0].paginaNova.length] = new Option('" + trataStringRetorno1(listaSubSistemasPaginasVO.getSubSistemaPaginasVOArray(i).getPaginaSimplVOArray(j).getNmPagina()) + "','" + listaSubSistemasPaginasVO.getSubSistemaPaginasVOArray(i).getPaginaSimplVOArray(j).getIdPagina() + "');";
					}
					texto = texto + "}else {document.forms[0].paginaNova.options[0] = new Option(\"Selecione uma opçao... \",\"000\")}";
				}
			}
		}
		texto = texto + "}";
		return texto + "</script>";
	}

	// Busca recursiva pela arvore para encontrar um item
	private ItemMenuVO EncontraItemMenu(ItemMenuVO itens, String itemMenu, ItemMenuVO itemMenuVO) {
		if (!(itens.getItemMenuVOArray().length == 0)) {
			for (int x = 0; x < itens.getItemMenuVOArray().length; x++) {
				if (itens.getItemMenuVOArray(x).getIdItemMenu().equals(itemMenu)) {
					itemMenuVO = ItemMenuVO.Factory.newInstance();
					itemMenuVO.setIdItemMenu(itens.getItemMenuVOArray(x).getIdItemMenu());
					itemMenuVO.setNmMenu(itens.getItemMenuVOArray(x).getNmMenu());
					itemMenuVO.setDsHint(itens.getItemMenuVOArray(x).getDsHint());
					itemMenuVO.setNrNivel(itens.getItemMenuVOArray(x).getNrNivel());
					itemMenuVO.setIdItemMenuPai(itens.getItemMenuVOArray(x).getIdItemMenuPai());
					itemMenuVO.setInVisibilidade(itens.getItemMenuVOArray(x).getInVisibilidade());
					itemMenuVO.setSqSequencia(itens.getItemMenuVOArray(x).getSqSequencia());
					itemMenuVO.setInFolha(itens.getItemMenuVOArray(x).getInFolha());
					if (itemMenuVO.getInFolha().equalsIgnoreCase(FOLHA)) {
						itemMenuVO.addNewSistemaPaginaVO();
						itemMenuVO.getSistemaPaginaVO().setIdPagina(itens.getItemMenuVOArray(x).getSistemaPaginaVO().getIdPagina());
						itemMenuVO.getSistemaPaginaVO().setIdSubSistema(itens.getItemMenuVOArray(x).getSistemaPaginaVO().getIdSubSistema());
						itemMenuVO.getSistemaPaginaVO().setNmPagina(itens.getItemMenuVOArray(x).getSistemaPaginaVO().getNmPagina());
						itemMenuVO.getSistemaPaginaVO().setNmURL(itens.getItemMenuVOArray(x).getSistemaPaginaVO().getNmURL());
					}
					break;
				} else {
					itemMenuVO = EncontraItemMenu(itens.getItemMenuVOArray(x), itemMenu, itemMenuVO);
				}
			}
		}
		return itemMenuVO;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterMenu.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward montaSubSistema(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("EditarParSistemaController:montaSubSistema" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (formArvore == null) {
			formArvore = new FormArvore();
		}
		formArvore.setMsgError(ConstantesCRM.SVAZIO);
		try {
			// instancia o id do sistema
			SistemaIDVO sistemaIdListar = SistemaIDVO.Factory.newInstance();
			sistemaIdListar.setIdSistema(request.getParameter("sistemaId"));
			formArvore.setIdSistema(request.getParameter("sistemaId"));
			formArvore.setDsSistema(request.getParameter("dsSistema"));

			// busca todos os Sub-sistemas relacionados ao id digitado
			SubSistemasUsuarioVO subSistemasUsuarioVO = controlSistema.listaSubSistemasPorSistema(sistemaIdListar, ConstantesCRM.getCurrentUser(request.getSession()));

			// se algum resultado foi retornado, guarda os valores no formulario
			if (subSistemasUsuarioVO.getSubSistemaUsuarioVOArray().length > 0) {
				SubSistemaUsuarioVO subSistemaUsuarioVO = SubSistemaUsuarioVO.Factory.newInstance();
				subSistemaUsuarioVO.setIdSubSistema("0000");
				subSistemaUsuarioVO.setDsSubSistema("Escolha uma opção...");
				subSistemasUsuarioVO.insertNewSubSistemaUsuarioVO(0);
				subSistemasUsuarioVO.setSubSistemaUsuarioVOArray(0, subSistemaUsuarioVO);
				formArvore.setSubSistemaUsuarioVO(subSistemasUsuarioVO.getSubSistemaUsuarioVOArray());

			} else {
				SubSistemaUsuarioVO subSistemaUsuarioVO = SubSistemaUsuarioVO.Factory.newInstance();
				subSistemaUsuarioVO.setIdSubSistema("0000");
				subSistemaUsuarioVO.setDsSubSistema("Não existe menus casdastrados para esse subsistema");
				subSistemasUsuarioVO.insertNewSubSistemaUsuarioVO(0);
				subSistemasUsuarioVO.setSubSistemaUsuarioVOArray(0, subSistemaUsuarioVO);
				formArvore.setSubSistemaUsuarioVO(subSistemasUsuarioVO.getSubSistemaUsuarioVOArray());
			}

		} catch (TuxedoWarningException twe) {
			log.warn("EditarParSistemaController:montaSubSistema" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formArvore.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("EditarParSistemaController:montaSubSistema" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="inserirItemMenu.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward inserirItemMenu(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("EditarParSistemaController:inserirItemMenu" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (formArvore == null) {
			formArvore = new FormArvore();
		}
		formArvore.setMsgError(ConstantesCRM.SVAZIO);
		try {
			// Recupero o ID do usuario clicado e converto p/ BigInteger
			String idItemMenu = request.getParameter("idItemMenu");
			SistemaIDArvoreVO sistemaIDArvoreVO = SistemaIDArvoreVO.Factory.newInstance();
			sistemaIDArvoreVO.setIdSubSistema(formArvore.getIdSubSistemaEscolhido());
			ItemMenuVO itemMenuVO = ItemMenuVO.Factory.newInstance();
			itemMenuVO = EncontraItemMenu(controlSistema.carregaArvoreMenu(sistemaIDArvoreVO, ConstantesCRM.getCurrentUser(request.getSession())), idItemMenu, itemMenuVO);

			if (itemMenuVO.getIdItemMenu() != null) {
				// Popula dados para a tela
				formArvore.setIdItemMenu(itemMenuVO.getIdItemMenu());
				formArvore.setIdItemMenuPai(itemMenuVO.getIdItemMenuPai());
				formArvore.setNmMenu(itemMenuVO.getNmMenu());
				formArvore.setNmMenuNovo(ConstantesCRM.SVAZIO);
				formArvore.setDsHint(itemMenuVO.getDsHint());
				formArvore.setDsHintNovo(ConstantesCRM.SVAZIO);
				formArvore.setNrNivel(itemMenuVO.getNrNivel());
				formArvore.setInVisibilidade(itemMenuVO.getInVisibilidade());
				formArvore.setVisivelNovo(ConstantesCRM.SVAZIO);
				formArvore.setSqSequencia(itemMenuVO.getSqSequencia());

			} else {
				// Popula dados para a tela
				formArvore.setIdItemMenu(idItemMenu);
				formArvore.setIdItemMenuPai(idItemMenu);
				formArvore.setNmMenu(formArvore.getDsSubSistema());
				formArvore.setNmMenuNovo(ConstantesCRM.SVAZIO);
				formArvore.setDsHint(ConstantesCRM.SVAZIO);
				formArvore.setDsHintNovo(ConstantesCRM.SVAZIO);
				formArvore.setNrNivel(ConstantesCRM.SZERO);
				formArvore.setInVisibilidade(ConstantesCRM.SVAZIO);
				formArvore.setVisivelNovo(ConstantesCRM.SVAZIO);
				formArvore.setSqSequencia(ConstantesCRM.SZERO);
			}

			formArvore.setInFolha(PASTA);
			formArvore.setTipoItem("P");
			formArvore.setPaginaNova("000");
			formArvore.setIdSubSistema(formArvore.getIdSubSistemaEscolhido());
			formArvore.setSubSistemaNovo(formArvore.getIdSubSistemaEscolhido());

			SistemaIDVO sistemaIDVO = SistemaIDVO.Factory.newInstance();
			sistemaIDVO.setIdSistema(formArvore.getIdSistema());
			ListaSubSistemasPaginasVO listaSubSistemasPaginasVO = controlSistema.listaSubSistemasPaginas(sistemaIDVO, ConstantesCRM.getCurrentUser(request.getSession()));
			montaListaSubSistemaEscolhido(listaSubSistemasPaginasVO);

			String lista = montaListaSubSistemaEscolhido(listaSubSistemasPaginasVO);
			if (request.getSession().getAttribute("lista_menu_pagina") != null) {
				request.getSession().removeAttribute("lista_menu_pagina");
			}
			request.getSession().setAttribute("lista_menu_pagina", lista);

		} catch (TuxedoWarningException twe) {
			log.warn("EditarParSistemaController:inserirItemMenu" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formArvore.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("EditarParSistemaController:inserirItemMenu" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
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
	 * @jpf:forward name="success" path="editarItemMenu.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward editarItemMenu(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("EditarParSistemaController:editarItemMenu" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (formArvore == null) {
			formArvore = new FormArvore();
		}
		formArvore.setMsgError(ConstantesCRM.SVAZIO);
		try {
			// Recupero o ID do usuario clicado e converto p/ BigInteger
			String idItemMenu = request.getParameter("idItemMenu");

			SistemaIDArvoreVO sistemaIDArvoreVO = SistemaIDArvoreVO.Factory.newInstance();
			sistemaIDArvoreVO.setIdSubSistema(formArvore.getIdSubSistemaEscolhido());
			ItemMenuVO itemMenuVO = ItemMenuVO.Factory.newInstance();
			itemMenuVO = EncontraItemMenu(controlSistema.carregaArvoreMenu(sistemaIDArvoreVO, ConstantesCRM.getCurrentUser(request.getSession())), idItemMenu, itemMenuVO);

			// Popula dados para a tela
			formArvore.setIdItemMenu(itemMenuVO.getIdItemMenu());
			formArvore.setNmMenu(itemMenuVO.getNmMenu());
			formArvore.setNmMenuNovo(itemMenuVO.getNmMenu());
			formArvore.setDsHint(itemMenuVO.getDsHint());
			formArvore.setDsHintNovo(itemMenuVO.getDsHint());
			formArvore.setNrNivel(itemMenuVO.getNrNivel());
			formArvore.setIdItemMenuPai(itemMenuVO.getIdItemMenuPai());
			formArvore.setInVisibilidade(itemMenuVO.getInVisibilidade());
			formArvore.setSqSequencia(itemMenuVO.getSqSequencia());
			formArvore.setInFolha(itemMenuVO.getInFolha());

			if (itemMenuVO.getInFolha().equalsIgnoreCase(FOLHA)) {
				formArvore.setIdPagina(itemMenuVO.getSistemaPaginaVO().getIdPagina());
				formArvore.setPaginaNova(itemMenuVO.getSistemaPaginaVO().getIdPagina());
				formArvore.setIdSubSistema(itemMenuVO.getSistemaPaginaVO().getIdSubSistema());
				formArvore.setNmPagina(itemMenuVO.getSistemaPaginaVO().getNmPagina());
				formArvore.setNmURL(itemMenuVO.getSistemaPaginaVO().getNmURL());

				if (formArvore.getIdSubSistemaEscolhido() == null) {
					formArvore.setIdSubSistemaEscolhido(formArvore.getIdSubSistema());
				}

				formArvore.setSubSistemaNovo(formArvore.getIdSubSistema());
				formArvore.setInFolha(FOLHA);
				formArvore.setTipoItem("F");

			} else {
				formArvore.setIdSubSistema(formArvore.getIdSubSistemaEscolhido());
				formArvore.setSubSistemaNovo(formArvore.getIdSubSistema());
				formArvore.setInFolha(PASTA);
				formArvore.setTipoItem("P");
			}

			SistemaIDVO sistemaIDVO = SistemaIDVO.Factory.newInstance();
			sistemaIDVO.setIdSistema(formArvore.getIdSistema());
			ListaSubSistemasPaginasVO listaSubSistemasPaginasVO = controlSistema.listaSubSistemasPaginas(sistemaIDVO, ConstantesCRM.getCurrentUser(request.getSession()));
			montaListaSubSistemaEscolhido(listaSubSistemasPaginasVO);

			String lista = montaListaSubSistemaEscolhido(listaSubSistemasPaginasVO);
			if (request.getSession().getAttribute("lista_menu_pagina") != null) {
				request.getSession().removeAttribute("lista_menu_pagina");
			}
			request.getSession().setAttribute("lista_menu_pagina", lista);

		} catch (TuxedoWarningException twe) {
			log.warn("EditarParSistemaController:editarItemMenu" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formArvore.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("EditarParSistemaController:editarItemMenu" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
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
	 * @jpf:forward name="success" path="manterMenu.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward insereItem(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormArvore form = (FormArvore) formParam;

		log.debug("EditarParSistemaController:insereItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (formArvore == null) {
			formArvore = new FormArvore();
		}
		formArvore.setMsgError(ConstantesCRM.SVAZIO);
		try {
			ItemMenuInserirVO itemMenuInserirVO = ItemMenuInserirVO.Factory.newInstance();
			itemMenuInserirVO.setDsHint(form.getDsHintNovo());
			itemMenuInserirVO.setIdItemMenuPai(form.getIdItemMenu());

			if (form.getInFolha().equals(FOLHA)) {
				itemMenuInserirVO.setIdPagina(form.getPaginaNova());
			} else if (form.getInFolha().equals(PASTA)) {
				itemMenuInserirVO.setIdPagina(ConstantesCRM.SVAZIO);
			}

			if (form.getVisivelNovo() != null && form.getVisivelNovo().equals(ConstantesCRM.SYES)) {
				itemMenuInserirVO.setInVisibilidade(VISIVEL);
			} else {
				itemMenuInserirVO.setInVisibilidade(INVISIVEL);
			}

			itemMenuInserirVO.setIdSubSistema(form.getIdSubSistemaEscolhido());
			formArvore.setIdSubSistemaEscolhido(itemMenuInserirVO.getIdSubSistema());
			itemMenuInserirVO.setNmMenu(form.getNmMenuNovo());

			request.setAttribute("aba", "abaSelected(btAba, bt02);CarregaAba('bt02');");
			request.setAttribute("arvore", "exibe");

			controlSistema.inserirItemMenu(itemMenuInserirVO, ConstantesCRM.getCurrentUser(request.getSession()));
		} catch (TuxedoWarningException twe) {
			log.warn("EditarParSistemaController:insereItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formArvore.setMsgError(twe.getXmlHeader().getStatusText());
			form.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			if (e.getMessage().substring(8, 12).equalsIgnoreCase("0000")) {
				log.warn("EditarParSistemaController:insereItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + e.getMessage().substring(e.getMessage().indexOf(']') + 1));
				formArvore.setMsgError(e.getMessage().substring(e.getMessage().indexOf(']') + 1));
			} else {
				log.error("EditarParSistemaController:insereItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
				FormError formError = globalApp.buildFormError(e, request);
				formError.setTarget("parent.frameApp");
				request.setAttribute(ConstantesCRM.SFORMERROR, formError);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SERROR);
			}
		}

		try {
			form = processamentoDeArvore(form,request);
			formArvore.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("EditarParSistemaController:insereItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formArvore.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("EditarParSistemaController:insereItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
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
	 * @jpf:forward name="success" path="manterMenu.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaItemEditado(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormArvore form = (FormArvore) formParam;

		log.debug("EditarParSistemaController:salvaItemEditado" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (formArvore == null) {
			formArvore = new FormArvore();
		}
		formArvore.setMsgError(ConstantesCRM.SVAZIO);
		try {
			ItemMenuEditarVO itemMenuEditarVO = ItemMenuEditarVO.Factory.newInstance();
			itemMenuEditarVO.setDsHint(form.getDsHintNovo());
			itemMenuEditarVO.setIdItemMenuPai(form.getIdItemMenuPai());

			if (form.getInFolha().equals(FOLHA)) {
				itemMenuEditarVO.setIdPagina(form.getPaginaNova());
			} else if (form.getInFolha().equals(PASTA)) {
				itemMenuEditarVO.setIdPagina(ConstantesCRM.SVAZIO);
			}
			form.setInVisibilidade(form.getVisivelNovo());

			if (form.getInVisibilidade().equals(ConstantesCRM.SYES)) {
				itemMenuEditarVO.setInVisibilidade(VISIVEL);
			} else {
				itemMenuEditarVO.setInVisibilidade(INVISIVEL);
			}

			itemMenuEditarVO.setIdItemMenu(form.getIdItemMenu());
			itemMenuEditarVO.setIdSubSistema(form.getIdSubSistemaEscolhido());
			formArvore.setIdSubSistemaEscolhido(itemMenuEditarVO.getIdSubSistema());
			itemMenuEditarVO.setNmMenu(form.getNmMenuNovo());
			request.setAttribute("aba", "abaSelected(btAba, bt02);CarregaAba('bt02');");
			request.setAttribute("arvore", "exibe");

			controlSistema.salvarItemEditado(itemMenuEditarVO, ConstantesCRM.getCurrentUser(request.getSession()));
			formArvore.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("EditarParSistemaController:salvaItemEditado" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formArvore.setMsgError(twe.getXmlHeader().getStatusText());
			form.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			if (e.getMessage().substring(8, 12).equalsIgnoreCase("0000")) {
				log.warn("EditarParSistemaController:salvaItemEditado" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + e.getMessage().substring(e.getMessage().indexOf(']') + 1));
				formArvore.setMsgError(e.getMessage().substring(e.getMessage().indexOf(']') + 1));

			} else {
				log.error("EditarParSistemaController:salvaItemEditado" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
				FormError formError = globalApp.buildFormError(e, request);
				formError.setTarget("parent.frameApp");
				request.setAttribute(ConstantesCRM.SFORMERROR, formError);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SERROR);
			}
		}

		try {
			form = processamentoDeArvore(form,request);

		} catch (TuxedoWarningException twe) {
			log.warn("EditarParSistemaController:salvaItemEditado" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formArvore.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("EditarParSistemaController:salvaItemEditado" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
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
	 * @jpf:forward name="success" path="manterMenu.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removeItem(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormArvore form = (FormArvore) formParam;

		log.debug("EditarParSistemaController:removeItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (formArvore == null) {
			formArvore = new FormArvore();
		}
		formArvore.setMsgError(ConstantesCRM.SVAZIO);
		try {
			ItemMenuRemoverVO itemMenuRemoverVO = ItemMenuRemoverVO.Factory.newInstance();
			itemMenuRemoverVO.setIdItemMenu(request.getParameter("idItemMenu"));

			String subSistemaNovo = form.getSubSistemaNovo();
			if ((subSistemaNovo == null) || (subSistemaNovo.equalsIgnoreCase("000"))) {
				formArvore.setIdSubSistema(form.getIdSubSistemaEscolhido());
			} else {
				formArvore.setIdSubSistema(subSistemaNovo);
			}
			form.setIdSubSistemaEscolhido(formArvore.getIdSubSistemaEscolhido());

			request.setAttribute("aba", "abaSelected(btAba, bt02);CarregaAba('bt02');");
			request.setAttribute("arvore", "exibe");

			if (form.getIdSubSistemaEscolhido() == null) {
				form.setIdSubSistemaEscolhido(subSistemaNovo);
			}

			controlSistema.removeItemArvore(itemMenuRemoverVO, ConstantesCRM.getCurrentUser(request.getSession()));
			formArvore.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("EditarParSistemaController:removeItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formArvore.setMsgError(twe.getXmlHeader().getStatusText());
			form.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			if (e.getMessage().substring(12, 13).equalsIgnoreCase(ConstantesCRM.SONE)) {
				log.warn("EditarParSistemaController:removeItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + e.getMessage().substring(e.getMessage().indexOf(']') + 1));
				formArvore.setMsgError(e.getMessage().substring(e.getMessage().indexOf(']') + 1));

			} else {
				log.error("EditarParSistemaController:removeItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
				FormError formError = globalApp.buildFormError(e, request);
				formError.setTarget("parent.frameApp");
				request.setAttribute(ConstantesCRM.SFORMERROR, formError);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SERROR);
			}
		}

		try {
			form = processamentoDeArvore(form,request);

		} catch (TuxedoWarningException twe) {
			log.warn("EditarParSistemaController:removeItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formArvore.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("EditarParSistemaController:removeItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
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
	 * @jpf:forward name="success" path="manterMenu.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward decrementaIncrementaSequencia(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormArvore form = (FormArvore) formParam;

		log.debug("EditarParSistemaController:decrementaIncrementaSequencia" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (formArvore == null) {
			formArvore = new FormArvore();
		}
		formArvore.setMsgError(ConstantesCRM.SVAZIO);
		try {
			ItemMenuMoverVO itemMenuMoverVO = ItemMenuMoverVO.Factory.newInstance();
			itemMenuMoverVO.setIdItemMenu(request.getParameter("idItemMenu"));
			itemMenuMoverVO.setInMoveUp(form.getInMoveUp());
			String subSistemaNovo = form.getSubSistemaNovo();

			if ((subSistemaNovo == null) || (subSistemaNovo.equalsIgnoreCase("000"))) {
				formArvore.setIdSubSistema(form.getIdSubSistemaEscolhido());
			} else {
				formArvore.setIdSubSistema(subSistemaNovo);
			}

			form.setIdSubSistemaEscolhido(formArvore.getIdSubSistemaEscolhido());
			request.setAttribute("aba", "abaSelected(btAba, bt02);CarregaAba('bt02');");
			request.setAttribute("arvore", "exibe");

			if (form.getIdSubSistemaEscolhido() == null) {
				form.setIdSubSistemaEscolhido(subSistemaNovo);
			}

			controlSistema.moverItemMenu(itemMenuMoverVO, ConstantesCRM.getCurrentUser(request.getSession()));

		} catch (TuxedoWarningException twe) {
			log.warn("EditarParSistemaController:decrementaIncrementaSequencia" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formArvore.setMsgError(twe.getXmlHeader().getStatusText());
			form.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("EditarParSistemaController:decrementaIncrementaSequencia" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget("parent.frameApp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		try {
			form = processamentoDeArvore(form,request);

		} catch (TuxedoWarningException twe) {
			log.warn("EditarParSistemaController:decrementaIncrementaSequencia" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formArvore.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("EditarParSistemaController:decrementaIncrementaSequencia" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget("parent.frameApp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class SistemasForm extends ActionForm {

		private static final long serialVersionUID = 8835705112047990612L;

		private String inAcessoControlado;
		private String nmUrlBase;
		private String sgSistema;
		private String msgError = ConstantesCRM.SVAZIO;
		private String dsSistema;
		private String idSistema;
		private SistemaManterUsuarioVO sistema;
		private SistemaManterUsuarioVO itemSistema;
		private SistemaManterUsuarioVO[] listaSistemas;
		private String listaSistemasLength;

		public SistemaManterUsuarioVO getSistemaById(String idSistema, SistemaManterUsuarioVO[] listaSistemas) {
			for (int i = 0; i < listaSistemas.length; i++) {
				if (listaSistemas[i].getIdSistema().equals(idSistema)) {
					return listaSistemas[i];
				}
			}
			return null;
		}

		public SistemasForm() {
			idSistema = ConstantesCRM.SVAZIO;
			sistema = SistemaManterUsuarioVO.Factory.newInstance();
			itemSistema = SistemaManterUsuarioVO.Factory.newInstance();
			listaSistemas = new SistemaManterUsuarioVO[0];
			listaSistemasLength = ConstantesCRM.SZERO;
		}

		public void setIdSistema(String idSistema) {
			this.idSistema = idSistema;
		}

		public String getIdSistema() {
			return this.idSistema;
		}

		public void setSistema(SistemaManterUsuarioVO sistema) {
			this.sistema = sistema;
		}

		public SistemaManterUsuarioVO getSistema() {
			return this.sistema;
		}

		public void setListaSistemas(SistemaManterUsuarioVO[] listaSistemas) {
			this.listaSistemas = listaSistemas;
		}

		public SistemaManterUsuarioVO[] getListaSistemas() {
			return this.listaSistemas;
		}

		public void setItemSistema(SistemaManterUsuarioVO itemSistema) {
			this.itemSistema = itemSistema;
		}

		public SistemaManterUsuarioVO getItemSistema() {
			return this.itemSistema;
		}

		public SistemaManterUsuarioVO getSistemaUsuarioVOById(String id, SistemaManterUsuarioVO[] sistemas) {
			for (int i = 0; i < sistemas.length; i++) {
				if (sistemas[i].getIdSistema().equals(id)) {
					return sistemas[i];
				}
			}
			return null;
		}

		public void setListaSistemasLength(String listaSistemasLength) {
			this.listaSistemasLength = listaSistemasLength;
		}

		public String getListaSistemasLength() {
			return this.listaSistemasLength;
		}

		public void setDsSistema(String dsSistema) {
			this.dsSistema = dsSistema;
		}

		public String getDsSistema() {
			return this.dsSistema;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}

		public void setSgSistema(String sgSistema) {
			this.sgSistema = sgSistema;
		}

		public String getSgSistema() {
			return this.sgSistema;
		}

		public void setNmUrlBase(String nmUrlBase) {
			this.nmUrlBase = nmUrlBase;
		}

		public String getNmUrlBase() {
			return this.nmUrlBase;
		}

		public void setInAcessoControlado(String inAcessoControlado) {
			this.inAcessoControlado = inAcessoControlado;
		}

		public String getInAcessoControlado() {
			return this.inAcessoControlado;
		}
	}

	public SistemasForm getSistemasForm() {
		return (this.sistemasForm);
	}

	public static class FormArvore extends ActionForm {

		private static final long serialVersionUID = 2628196558323092702L;

		private String inMoveUp;
		private String dsSubSistema;
		private PaginaSimplVO[] subSistemaListaPaginas;
		private String msgError;
		private String tipo;
		private String idSubSistemaEscolhido;
		private SubSistemaUsuarioVO[] subSistemaUsuarioVO;
		private String idItemMenu;
		private String paginaNova;
		private String tipoItem;
		private String idSubSistema;
		private String inFolha;
		private String dsSistema;
		private String subSistemaNovo;
		private String visivelNovo;
		private String dsHintNovo;
		private String nmMenuNovo;
		private String lista;
		private String idItemMenuPai;
		private SubSistemaPaginasVO[] subSistemaPaginasVO;
		private String nmURL = ConstantesCRM.SVAZIO;
		private String nmPagina = ConstantesCRM.SVAZIO;
		private String idSistema = ConstantesCRM.SVAZIO;
		private String idPagina = ConstantesCRM.SVAZIO;
		private String sqSequencia = ConstantesCRM.SVAZIO;
		private String inVisibilidade = ConstantesCRM.SVAZIO;
		private String nrNivel = ConstantesCRM.SVAZIO;
		private String dsHint = ConstantesCRM.SVAZIO;
		private String nmMenu = ConstantesCRM.SVAZIO;

		public FormArvore() {
			nmURL = ConstantesCRM.SVAZIO;
			nmPagina = ConstantesCRM.SVAZIO;
			idSistema = ConstantesCRM.SVAZIO;
			idPagina = ConstantesCRM.SVAZIO;
			sqSequencia = ConstantesCRM.SVAZIO;
			inVisibilidade = ConstantesCRM.SVAZIO;
			idItemMenuPai = ConstantesCRM.SVAZIO;
			nrNivel = ConstantesCRM.SVAZIO;
			dsHint = ConstantesCRM.SVAZIO;
			nmMenu = ConstantesCRM.SVAZIO;
			idItemMenu = ConstantesCRM.SVAZIO;
			subSistemaListaPaginas = new PaginaSimplVO[0];
			subSistemaPaginasVO = new SubSistemaPaginasVO[0];
			subSistemaUsuarioVO = new SubSistemaUsuarioVO[0];
		}

		public void setIdItemMenu(String idItemMenu) {
			this.idItemMenu = idItemMenu;
		}

		public String getIdItemMenu() {
			return this.idItemMenu;
		}

		public void setNmMenu(String nmMenu) {
			this.nmMenu = nmMenu;
		}

		public String getNmMenu() {
			return this.nmMenu;
		}

		public void setDsHint(String dsHint) {
			this.dsHint = dsHint;
		}

		public String getDsHint() {
			return this.dsHint;
		}

		public void setNrNivel(String nrNivel) {
			this.nrNivel = nrNivel;
		}

		public String getNrNivel() {
			return this.nrNivel;
		}

		public void setInVisibilidade(String inVisibilidade) {
			this.inVisibilidade = inVisibilidade;
		}

		public String getInVisibilidade() {
			return this.inVisibilidade;
		}

		public void setSqSequencia(String sqSequencia) {
			this.sqSequencia = sqSequencia;
		}

		public String getSqSequencia() {
			return this.sqSequencia;
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

		public void setNmPagina(String nmPagina) {
			this.nmPagina = nmPagina;
		}

		public String getNmPagina() {
			return this.nmPagina;
		}

		public void setNmURL(String nmURL) {
			this.nmURL = nmURL;
		}

		public String getNmURL() {
			return this.nmURL;
		}

		public void setSubSistemaPaginasVO(SubSistemaPaginasVO[] subSistemaPaginasVO) {
			this.subSistemaPaginasVO = subSistemaPaginasVO;
		}

		public SubSistemaPaginasVO[] getSubSistemaPaginasVO() {
			return this.subSistemaPaginasVO;
		}

		public void setIdItemMenuPai(String idItemMenuPai) {
			this.idItemMenuPai = idItemMenuPai;
		}

		public String getIdItemMenuPai() {
			return this.idItemMenuPai;
		}

		public void setLista(String lista) {
			this.lista = lista;
		}

		public String getLista() {
			return this.lista;
		}

		public void setNmMenuNovo(String nmMenuNovo) {
			this.nmMenuNovo = nmMenuNovo;
		}

		public String getNmMenuNovo() {
			return this.nmMenuNovo;
		}

		public void setDsHintNovo(String dsHintNovo) {
			this.dsHintNovo = dsHintNovo;
		}

		public String getDsHintNovo() {
			return this.dsHintNovo;
		}

		public void setVisivelNovo(String visivelNovo) {
			this.visivelNovo = visivelNovo;
		}

		public String getVisivelNovo() {
			return this.visivelNovo;
		}

		public void setSubSistemaNovo(String subSistemaNovo) {
			this.subSistemaNovo = subSistemaNovo;
		}

		public String getSubSistemaNovo() {
			return this.subSistemaNovo;
		}

		public void setDsSistema(String dsSistema) {
			this.dsSistema = dsSistema;
		}

		public String getDsSistema() {
			return this.dsSistema;
		}

		public void setInFolha(String inFolha) {
			this.inFolha = inFolha;
		}

		public String getInFolha() {
			return this.inFolha;
		}

		public void setIdSubSistema(String idSubSistema) {
			this.idSubSistema = idSubSistema;
		}

		public String getIdSubSistema() {
			return this.idSubSistema;
		}

		public void setTipoItem(String tipoItem) {
			this.tipoItem = tipoItem;
		}

		public String getTipoItem() {
			return this.tipoItem;
		}

		public void setPaginaNova(String paginaNova) {
			this.paginaNova = paginaNova;
		}

		public String getPaginaNova() {
			return this.paginaNova;
		}

		public void setSubSistemaUsuarioVO(SubSistemaUsuarioVO[] subSistemaUsuarioVO) {
			this.subSistemaUsuarioVO = subSistemaUsuarioVO;
		}

		public SubSistemaUsuarioVO[] getSubSistemaUsuarioVO() {
			return this.subSistemaUsuarioVO;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		public String getTipo() {
			return this.tipo;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}

		public void setSubSistemaListaPaginas(PaginaSimplVO[] subSistemaListaPaginas) {
			this.subSistemaListaPaginas = subSistemaListaPaginas;
		}

		public PaginaSimplVO[] getSubSistemaListaPaginas() {
			return this.subSistemaListaPaginas;
		}

		public void setDsSubSistema(String dsSubSistema) {
			this.dsSubSistema = dsSubSistema;
		}

		public String getDsSubSistema() {
			return this.dsSubSistema;
		}

		public void setInMoveUp(String inMoveUp) {
			this.inMoveUp = inMoveUp;
		}

		public String getInMoveUp() {
			return this.inMoveUp;
		}

		public void setIdSubSistemaEscolhido(String idSubSistemaEscolhido) {
			this.idSubSistemaEscolhido = idSubSistemaEscolhido;
		}

		public String getIdSubSistemaEscolhido() {
			return this.idSubSistemaEscolhido;
		}
	}

	public FormArvore getFormArvore() {
		return (this.formArvore);
	}

}
