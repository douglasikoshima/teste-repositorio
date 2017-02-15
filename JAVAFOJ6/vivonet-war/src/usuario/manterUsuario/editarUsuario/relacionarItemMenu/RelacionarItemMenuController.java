package usuario.manterUsuario.editarUsuario.relacionarItemMenu;

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
import br.com.vivo.fo.usuario.vo.ItemMenuExistentesVODocument.ItemMenuExistentesVO;
import br.com.vivo.fo.usuario.vo.ListaItensMenuVODocument.ListaItensMenuVO;
import br.com.vivo.fo.usuario.vo.ListaSistemaUsuarioVODocument.ListaSistemaUsuarioVO;
import br.com.vivo.fo.usuario.vo.MenuParamPesquisaItensVODocument.MenuParamPesquisaItensVO;
import br.com.vivo.fo.usuario.vo.SalvarItensMenuRelacionadosVODocument.SalvarItensMenuRelacionadosVO;
import br.com.vivo.fo.usuario.vo.SistemaIDVODocument.SistemaIDVO;
import br.com.vivo.fo.usuario.vo.SistemaManterUsuarioVODocument.SistemaManterUsuarioVO;
import br.com.vivo.fo.usuario.vo.SubSistemaUsuarioVODocument.SubSistemaUsuarioVO;
import br.com.vivo.fo.usuario.vo.SubSistemasUsuarioVODocument.SubSistemasUsuarioVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class RelacionarItemMenuController extends AbstractAction {

	private static final long serialVersionUID = -881652007588244755L;

	@EJB
	private ManterSistemaFacade controlSistema;

	private FormItemMenu formItemMenu = new FormItemMenu();
	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("usuario");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("obtemSubSistemas".equals(mapping.getParameter())) {
			return obtemSubSistemas(mapping, form, request, response);
		} else if ("obtemItemMenu".equals(mapping.getParameter())) {
			return obtemItemMenu(mapping, form, request, response);
		} else if ("salvaItem".equals(mapping.getParameter())) {
			return salvaItem(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relacionarUsuarioItemMenu.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("RelacionarItemMenuController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		try {
			formItemMenu = new FormItemMenu();

			String userID = request.getParameter("userId");
			if (userID != null) {
				formItemMenu.setIdUsuario(userID);
			}

			this.loadSistemas(request);

		} catch (TuxedoWarningException twe) {
			log.warn("RelacionarItemMenuController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formItemMenu.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("RelacionarItemMenuController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/index.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	protected void loadSistemas(HttpServletRequest request) throws Exception {

		// Retorna a lista de canais relacionados e não relacionados
		SistemaManterUsuarioVO sistemaManterUsuarioVO = SistemaManterUsuarioVO.Factory.newInstance();

		// Retorna a lista de canais relacionados e não relacionados
		ListaSistemaUsuarioVO listaSistemaUsuarioVO = ListaSistemaUsuarioVO.Factory.newInstance();
		listaSistemaUsuarioVO = controlSistema.listaSistemas(sistemaManterUsuarioVO, ConstantesCRM.getCurrentUser(request.getSession()));

		SistemaManterUsuarioVO[] sistemaManterUsuarioVOArray = new SistemaManterUsuarioVO[listaSistemaUsuarioVO.getSistemaManterUsuarioVOArray().length];
		sistemaManterUsuarioVOArray = listaSistemaUsuarioVO.getSistemaManterUsuarioVOArray();

		formItemMenu.setListaSistemas(sistemaManterUsuarioVOArray);
		formItemMenu.setExibeItem("nao");
		formItemMenu.setExibeSubSistema("nao");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relacionarUsuarioItemMenu.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward obtemSubSistemas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormItemMenu form = (FormItemMenu) formParam;

		log.debug("RelacionarItemMenuController:obtemSubSistemas" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		formItemMenu.setMsgError(ConstantesCRM.SVAZIO);

		try {
			SistemaIDVO sistemaIdListar = SistemaIDVO.Factory.newInstance();
			sistemaIdListar.setIdSistema(form.getIdSistema());
			SubSistemasUsuarioVO subSistemasUsuarioVO = SubSistemasUsuarioVO.Factory.newInstance();
			subSistemasUsuarioVO = controlSistema.listaSubSistemasPorSistema(sistemaIdListar, ConstantesCRM.getCurrentUser(request.getSession()));
			formItemMenu.setListaSubSistemas(subSistemasUsuarioVO.getSubSistemaUsuarioVOArray());
			formItemMenu.setExibeSubSistema("sim");
			formItemMenu.setIdSistema(form.getIdSistema());
			formItemMenu.setExibeItem("nao");

		} catch (TuxedoWarningException twe) {
			log.warn("RelacionarItemMenuController:obtemSubSistemas" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formItemMenu.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("RelacionarItemMenuController:obtemSubSistemas" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="relacionarUsuarioItemMenu.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward obtemItemMenu(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormItemMenu form = (FormItemMenu) formParam;

		log.debug("RelacionarItemMenuController:obtemItemMenu" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		formItemMenu.setMsgError(ConstantesCRM.SVAZIO);

		try {
			MenuParamPesquisaItensVO menuParamPesquisaItensVO = MenuParamPesquisaItensVO.Factory.newInstance();

			String userID = request.getParameter("userId");
			if (userID != null) {
				menuParamPesquisaItensVO.setIdUsuario(userID);
			}

			menuParamPesquisaItensVO.setIdSubSistema(form.getIdSubSistema());
			menuParamPesquisaItensVO.setIdGrupo(ConstantesCRM.SZERO);

			ListaItensMenuVO listaItensMenuVO = ListaItensMenuVO.Factory.newInstance();
			listaItensMenuVO = controlSistema.listaItensMenu(menuParamPesquisaItensVO, ConstantesCRM.getCurrentUser(request.getSession()));

			ItemMenuExistentesVO[] itemMenuExistentes = new ItemMenuExistentesVO[listaItensMenuVO.getItensExistentes().getItemMenuExistentesVOArray().length];
			ItemMenuExistentesVO[] itemMenuRelacionados = new ItemMenuExistentesVO[listaItensMenuVO.getItensMenuRelacionados().getItemMenuExistentesVOArray().length];

			itemMenuExistentes = listaItensMenuVO.getItensExistentes().getItemMenuExistentesVOArray();
			itemMenuRelacionados = listaItensMenuVO.getItensMenuRelacionados().getItemMenuExistentesVOArray();

			formItemMenu.setItemMenuExistentesVO(itemMenuExistentes);
			formItemMenu.setItemMenuRelacionadosVO(itemMenuRelacionados);
			formItemMenu.setIdSubSistema(form.getIdSubSistema());
			formItemMenu.setExibeItem("sim");

		} catch (TuxedoWarningException twe) {
			log.warn("RelacionarItemMenuController:obtemItemMenu" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formItemMenu.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("RelacionarItemMenuController:obtemItemMenu" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="relacionarUsuarioItemMenu.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaItem(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormItemMenu form = (FormItemMenu) formParam;

		log.debug("RelacionarItemMenuController:salvaItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		formItemMenu.setMsgError(ConstantesCRM.SVAZIO);
		try {
			String lista[] = null;
			if (form.getArrayItensRelacionados() != null) {
				lista = form.getArrayItensRelacionados();
			} else {
				lista = new String[0];
			}

			ItemMenuExistentesVO[] itemMenuExistentesVO = new ItemMenuExistentesVO[lista.length];

			for (int i = 0; i < form.getArrayItensRelacionados().length; i++) {
				itemMenuExistentesVO[i] = ItemMenuExistentesVO.Factory.newInstance();
				itemMenuExistentesVO[i].setIdItemMenu(lista[i]);
			}

			SalvarItensMenuRelacionadosVO salvarItensMenuRelacionadosVO = SalvarItensMenuRelacionadosVO.Factory.newInstance();

			String userID = request.getParameter("userId");
			if (userID != null) {
				salvarItensMenuRelacionadosVO.setIdUsuario(userID);
			}

			salvarItensMenuRelacionadosVO.setIdSubSistema(form.getIdSubSistema());
			salvarItensMenuRelacionadosVO.addNewItemMenuExistentesVO();
			salvarItensMenuRelacionadosVO.setItemMenuExistentesVOArray(itemMenuExistentesVO);
			salvarItensMenuRelacionadosVO.setIdGrupo(ConstantesCRM.SZERO);
			ListaItensMenuVO listaItensMenuVO = ListaItensMenuVO.Factory.newInstance();
			listaItensMenuVO = controlSistema.salvaItensRelacionados(salvarItensMenuRelacionadosVO, ConstantesCRM.getCurrentUser(request.getSession()));

			ItemMenuExistentesVO[] itemMenuExistentes = new ItemMenuExistentesVO[listaItensMenuVO.getItensExistentes().getItemMenuExistentesVOArray().length];
			ItemMenuExistentesVO[] itemMenuRelacionados = new ItemMenuExistentesVO[listaItensMenuVO.getItensMenuRelacionados().getItemMenuExistentesVOArray().length];

			itemMenuExistentes = listaItensMenuVO.getItensExistentes().getItemMenuExistentesVOArray();
			itemMenuRelacionados = listaItensMenuVO.getItensMenuRelacionados().getItemMenuExistentesVOArray();

			formItemMenu.setItemMenuExistentesVO(itemMenuExistentes);
			formItemMenu.setItemMenuRelacionadosVO(itemMenuRelacionados);
			formItemMenu.setIdSubSistema(form.getIdSubSistema());
			formItemMenu.setExibeItem("sim");
			formItemMenu.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("RelacionarItemMenuController:salvaItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formItemMenu.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("RelacionarItemMenuController:salvaItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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

	public static class FormItemMenu extends ActionForm {

		private static final long serialVersionUID = 1849141147611932169L;

		private String msgError = ConstantesCRM.SVAZIO;
		private String[] arrayItensRelacionados;
		private String[] arrayItensExistentes;
		private ItemMenuExistentesVO[] itemMenuRelacionadosVO;
		private ItemMenuExistentesVO[] itemMenuExistentesVO;
		private String idSubSistema;
		private SubSistemaUsuarioVO[] listaSubSistemas;
		private String idSistema;
		private String exibeSubSistema;
		private String exibeItem;
		private SistemaManterUsuarioVO[] listaSistemas;
		private String idUsuario;

		public FormItemMenu() {
			idSistema = ConstantesCRM.SVAZIO;
			exibeItem = ConstantesCRM.SVAZIO;
			exibeSubSistema = ConstantesCRM.SVAZIO;
			listaSistemas = new SistemaManterUsuarioVO[0];
			listaSubSistemas = new SubSistemaUsuarioVO[0];
			itemMenuExistentesVO = new ItemMenuExistentesVO[0];
			itemMenuRelacionadosVO = new ItemMenuExistentesVO[0];
		}

		public void setIdUsuario(String idUsuario) {
			this.idUsuario = idUsuario;
		}

		public String getIdUsuario() {
			return this.idUsuario;
		}

		public void setListaSistemas(SistemaManterUsuarioVO[] listaSistemas) {
			this.listaSistemas = listaSistemas;
		}

		public SistemaManterUsuarioVO[] getListaSistemas() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null. This type doesn't have
			// a default constructor, so Workshop cannot initialize it for you.

			// TODO: Initialize listaSistemas if it is null or length == 0.
			// if(this.listaSistemas == null || this.listaSistemas.length == 0)
			// {
			// this.listaSistemas = new SistemaPerfilUsuarioVO[1];
			// this.listaSistemas[0] = new SistemaPerfilUsuarioVO(?);
			// }

			return this.listaSistemas;
		}

		public void setExibeItem(String exibeItem) {
			this.exibeItem = exibeItem;
		}

		public String getExibeItem() {
			return this.exibeItem;
		}

		public void setExibeSubSistema(String exibeSubSistema) {
			this.exibeSubSistema = exibeSubSistema;
		}

		public String getExibeSubSistema() {
			return this.exibeSubSistema;
		}

		public void setIdSistema(String idSistema) {
			this.idSistema = idSistema;
		}

		public String getIdSistema() {
			return this.idSistema;
		}

		public void setListaSubSistemas(SubSistemaUsuarioVO[] listaSubSistemas) {
			this.listaSubSistemas = listaSubSistemas;
		}

		public SubSistemaUsuarioVO[] getListaSubSistemas() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null. This type doesn't have
			// a default constructor, so Workshop cannot initialize it for you.

			// TODO: Initialize listaSubSistemas if it is null or length == 0.
			// if(this.listaSubSistemas == null || this.listaSubSistemas.length == 0)
			// {
			// this.listaSubSistemas = new SubSistemasUsuarioVO[1];
			// this.listaSubSistemas[0] = new SubSistemasUsuarioVO(?);
			// }

			return this.listaSubSistemas;
		}

		public void setIdSubSistema(String idSubSistema) {
			this.idSubSistema = idSubSistema;
		}

		public String getIdSubSistema() {
			return this.idSubSistema;
		}

		public void setItemMenuExistentesVO(ItemMenuExistentesVO[] itemMenuExistentesVO) {
			this.itemMenuExistentesVO = itemMenuExistentesVO;
		}

		public ItemMenuExistentesVO[] getItemMenuExistentesVO() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null. This type doesn't have
			// a default constructor, so Workshop cannot initialize it for you.

			// TODO: Initialize itemMenuExistentesVO if it is null or length == 0.
			// if(this.itemMenuExistentesVO == null || this.itemMenuExistentesVO.length == 0)
			// {
			// this.itemMenuExistentesVO = new ItemMenuExistentesVO[1];
			// this.itemMenuExistentesVO[0] = new ItemMenuExistentesVO(?);
			// }

			return this.itemMenuExistentesVO;
		}

		public void setItemMenuRelacionadosVO(ItemMenuExistentesVO[] itemMenuRelacionadosVO) {
			this.itemMenuRelacionadosVO = itemMenuRelacionadosVO;
		}

		public ItemMenuExistentesVO[] getItemMenuRelacionadosVO() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null. This type doesn't have
			// a default constructor, so Workshop cannot initialize it for you.

			// TODO: Initialize itemMenuRelacionadosVO if it is null or length == 0.
			// if(this.itemMenuRelacionadosVO == null || this.itemMenuRelacionadosVO.length == 0)
			// {
			// this.itemMenuRelacionadosVO = new ItemMenuExistentesVO[1];
			// this.itemMenuRelacionadosVO[0] = new ItemMenuExistentesVO(?);
			// }

			return this.itemMenuRelacionadosVO;
		}

		public void setArrayItensExistentes(String[] arrayItensExistentes) {
			this.arrayItensExistentes = arrayItensExistentes;
		}

		public String[] getArrayItensExistentes() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.arrayItensExistentes == null || this.arrayItensExistentes.length == 0) {
				this.arrayItensExistentes = new String[1];
			}

			return this.arrayItensExistentes;
		}

		public void setArrayItensRelacionados(String[] arrayItensRelacionados) {
			this.arrayItensRelacionados = arrayItensRelacionados;
		}

		public String[] getArrayItensRelacionados() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.arrayItensRelacionados == null || this.arrayItensRelacionados.length == 0) {
				this.arrayItensRelacionados = new String[1];
			}

			return this.arrayItensRelacionados;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}
	}

	public FormItemMenu getFormItemMenu() {
		return (this.formItemMenu);
	}
}
