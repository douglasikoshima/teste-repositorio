package usuario.manterSkillUsuario.abaAssocSkillUsuario;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO;
import br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO.UsuarioExistenteVO;
import br.com.vivo.fo.commons.utils.EstruturaSkillTO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.usuario.manterUsuarioSkill.ManterUsuarioSkillFacade;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

@SuppressWarnings({"rawtypes","unused"})
public class ManterSkillUsuarioController extends AbstractAction {

	private static final long serialVersionUID = -8973534957699743513L;

	@EJB
	private ManterUsuarioSkillFacade controlSkillUsuario;

	private SkillUsuarioForm skillUsuarioForm = new SkillUsuarioForm();

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("usuario");

	private static final String filePathEnable = "/FrontOfficeWeb/resources/images/file.png";
	private static final String filePathDisabled = "/FrontOfficeWeb/resources/images/image_delete_file.gif";
	private static final String folderPathEnable = "/FrontOfficeWeb/resources/images/foldericon.png";
	private static final String folderPathDisabled = "/FrontOfficeWeb/resources/images/image_delete_folder.gif";
	private static final String folderPathDisabledOpen = "/FrontOfficeWeb/resources/images/openfoldericon.png";
	private static final String defaultError = "/FrontOfficeWeb/index.jsp";
	private static final String msgStatus = "msgStatus";
	private static final String folha = "1";
	private static final String disponivel = "1";
	private static final String GRAVAR = "0";
	private static final String RELACIONAR = "1";

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("beginSkillContato".equals(mapping.getParameter())) {
			return beginSkillContato(mapping, form, request, response);
		} else if ("filtraGrupo".equals(mapping.getParameter())) {
			return filtraGrupo(mapping, form, request, response);
		} else if ("filtraGrupoContato".equals(mapping.getParameter())) {
			return filtraGrupoContato(mapping, form, request, response);
		} else if ("gravaSkillUsuario".equals(mapping.getParameter())) {
			return gravaSkillUsuario(mapping, form, request, response);
		} else if ("gravaSkillContato".equals(mapping.getParameter())) {
			return gravaSkillContato(mapping, form, request, response);
		} else if ("pesquisaPorLogin".equals(mapping.getParameter())) {
			return pesquisaPorLogin(mapping, form, request, response);
		} else if ("pesquisaPorContato".equals(mapping.getParameter())) {
			return pesquisaPorContato(mapping, form, request, response);
		} else if ("filtraSkill".equals(mapping.getParameter())) {
			return filtraSkill(mapping, form, request, response);
		} else if ("filtraSkillContato".equals(mapping.getParameter())) {
			return filtraSkillContato(mapping, form, request, response);
		} else if ("filtraSkillRelacionar".equals(mapping.getParameter())) {
			return filtraSkillRelacionar(mapping, form, request, response);
		} else if ("listaUsuarios".equals(mapping.getParameter())) {
			return listaUsuarios(mapping, form, request, response);
		} else if ("avancarParaContato".equals(mapping.getParameter())) {
			return avancarParaContato(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterSkillUsuario.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterSkillUsuarioController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		try {
			if (getSkillUsuarioForm().getListaUsuariosExistentes() != null) {
				getSkillUsuarioForm().setListaUsuariosExistentes(null);
			}
			if (getSkillUsuarioForm().getListaUsuarioSkillAssociado() != null) {
				getSkillUsuarioForm().setListaUsuarioSkillAssociado(null);
			}

			AdmSkillUsuarioVO admSkillUsuarioVO = controlSkillUsuario.getUsuarioSkill(AdmSkillUsuarioVO.Factory.newInstance(), ConstantesCRM.getCurrentUser(request.getSession()));
			getSkillUsuarioForm().setAdmSkillUsuario(admSkillUsuarioVO);
			getSkillUsuarioForm().setInPesquisa((request.getParameter("inPesquisa") != null ? request.getParameter("inPesquisa") : ""));
			getSkillUsuarioForm().setFlagOperacao(GRAVAR);

		} catch (TuxedoWarningException twe) {
			log.warn("ManterSkillUsuarioController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgStatus", twe.getXmlHeader().getStatusText());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="skillContato.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward beginSkillContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterSkillUsuarioController:beginSkillContato" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		try {
			if (getSkillUsuarioForm().getListaContatoExistente() != null) {
				getSkillUsuarioForm().setListaContatoExistente(null);
			}

			if (getSkillUsuarioForm().getListaContatoAssociado() != null) {
				getSkillUsuarioForm().setListaContatoAssociado(null);
			}

			AdmSkillUsuarioVO admSkillUsuarioVO = controlSkillUsuario.getUsuarioSkill(AdmSkillUsuarioVO.Factory.newInstance(), ConstantesCRM.getCurrentUser(request.getSession()));
			getSkillUsuarioForm().setAdmSkillUsuario(admSkillUsuarioVO);
			getSkillUsuarioForm().setInPesquisa((request.getParameter("inPesquisa") != null ? request.getParameter("inPesquisa") : ""));

		} catch (TuxedoWarningException twe) {
			log.warn("ManterSkillUsuarioController:beginSkillContato" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgStatus", twe.getXmlHeader().getStatusText());
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterSkillUsuario.jsp"
	 */
	public ActionForward filtraGrupo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SkillUsuarioForm form = (SkillUsuarioForm) formParam;

		log.debug("ManterSkillUsuarioController:filtraGrupo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		try {
			EstruturaSkillTO estruturaSkillTO = new EstruturaSkillTO();
			form.getAdmSkillUsuario().setInOperacao(form.getInPesquisa());
			getSkillUsuarioForm().getAdmSkillUsuario().setGrupoSelecionado(form.getAdmSkillUsuario().getGrupoSelecionado());
			estruturaSkillTO = controlSkillUsuario.getSkillsByIdGrupo(form.getAdmSkillUsuario(), ConstantesCRM.getCurrentUser(request.getSession()));
			this.getSkillUsuarioForm().getAdmSkillUsuario().setSkillSelecionado(ConstantesCRM.SVAZIO);
			this.getSkillUsuarioForm().setListaUsuariosExistentes(null);
			this.getSkillUsuarioForm().setListaUsuarioSkillAssociado(null);
			this.getSkillUsuarioForm().getAdmSkillUsuario().setAdmSkillSimplVOArray(estruturaSkillTO.getAdmSkillUsuarioVO().getAdmSkillSimplVOArray());
			this.getSkillUsuarioForm().setFlagOperacao(GRAVAR);
		} catch (TuxedoWarningException twe) {
			log.warn("ManterSkillUsuarioController:filtraGrupo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());

			request.setAttribute("msgStatus", twe.getXmlHeader().getStatusText());
		}
		request.setAttribute("form", getSkillUsuarioForm());
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="skillContato.jsp"
	 */
	public ActionForward filtraGrupoContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SkillUsuarioForm form = (SkillUsuarioForm) formParam;

		log.debug("ManterSkillUsuarioController:filtraGrupoContato" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		try {
			EstruturaSkillTO estruturaSkillTO = new EstruturaSkillTO();
			getSkillUsuarioForm().getAdmSkillUsuario().setGrupoSelecionado(form.getAdmSkillUsuario().getGrupoSelecionado());
			estruturaSkillTO = controlSkillUsuario.getSkillsByIdGrupo(form.getAdmSkillUsuario(), ConstantesCRM.getCurrentUser(request.getSession()));
			this.getSkillUsuarioForm().getAdmSkillUsuario().setSkillSelecionado(ConstantesCRM.SVAZIO);
			this.getSkillUsuarioForm().setListaContatoExistente(null);
			this.getSkillUsuarioForm().setListaContatoAssociado(null);
			getSkillUsuarioForm().getAdmSkillUsuario().setAdmSkillSimplVOArray(estruturaSkillTO.getAdmSkillUsuarioVO().getAdmSkillSimplVOArray());

		} catch (TuxedoWarningException twe) {
			log.warn("ManterSkillUsuarioController:filtraGrupoContato" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());

			request.setAttribute("msgStatus", twe.getXmlHeader().getStatusText());
		}
		request.setAttribute("form", getSkillUsuarioForm());
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="error" path="/error.jsp"
	 * @jpf:forward name="success" path="filtraSkillRelacionar.do"
	 */
	public ActionForward gravaSkillUsuario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SkillUsuarioForm form = (SkillUsuarioForm) formParam;

		log.debug("ManterSkillUsuarioController:gravaSkillUsuario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		try {

			String[] usuarios = form.getUsuarioSelecionado();
			// form.getAdmSkillUsuario().setUsuariosSelecionadosArray(form.getUsuarioSelecionado());
			form.getAdmSkillUsuario().setSkillSelecionado(form.getAdmSkillUsuario().getSkillSelecionado());
			form.getAdmSkillUsuario().setGrupoSelecionado(form.getAdmSkillUsuario().getGrupoSelecionado());
			controlSkillUsuario.setUsuarioSkillMult(form.getAdmSkillUsuario(), ConstantesCRM.getCurrentUser(request.getSession()), usuarios);
			// controlSkillUsuario.setUsuarioSkill(form.getAdmSkillUsuario(),globalApp.getCurrentUser(request.getSession(),
			// this));
			form.getAdmSkillUsuario().setUsuariosSelecionadosArray(form.getUsuarioSelecionado());
			this.getSkillUsuarioForm().setListaUsuariosExistentes(null);
			this.getSkillUsuarioForm().setListaUsuarioSkillAssociado(null);
			request.setAttribute("msgStatus", ConstantesCRM.SSucesso);
			request.setAttribute("statusGravacao", ConstantesCRM.SONE);
			this.getSkillUsuarioForm().setFlagOperacao(RELACIONAR);

		} catch (TuxedoWarningException twe) {
			log.warn("ManterSkillUsuarioController:filtraGrupoContato" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgStatus", twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterSkillUsuarioController:gravaSkillUsuario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="error" path="/error.jsp"
	 * @jpf:forward name="success" path="filtraSkillContato.do"
	 */
	public ActionForward gravaSkillContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SkillUsuarioForm form = (SkillUsuarioForm) formParam;

		log.debug("ManterSkillUsuarioController:gravaSkillContato" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		try {
			String[] contatos = form.getContatoSelecionado();
			// form.getAdmSkillUsuario().setContatosSelecionadosArray(form.getContatoSelecionado());
			form.getAdmSkillUsuario().setSkillSelecionado(form.getAdmSkillUsuario().getSkillSelecionado());
			form.getAdmSkillUsuario().setGrupoSelecionado(form.getAdmSkillUsuario().getGrupoSelecionado());
			controlSkillUsuario.setContatoSkillMult(form.getAdmSkillUsuario(), ConstantesCRM.getCurrentUser(request.getSession()), contatos);
			// controlSkillUsuario.setContatoSkill(form.getAdmSkillUsuario(),globalApp.getCurrentUser(request.getSession(),
			// this));
			form.getAdmSkillUsuario().setUsuariosSelecionadosArray(form.getUsuarioSelecionado());
			this.getSkillUsuarioForm().setListaUsuariosExistentes(null);
			this.getSkillUsuarioForm().setListaUsuarioSkillAssociado(null);
			request.setAttribute("msgStatus", ConstantesCRM.SSucesso);
			request.setAttribute("statusGravacao", ConstantesCRM.SONE);
		} catch (TuxedoWarningException twe) {
			log.warn("ManterSkillUsuarioController:gravaSkillContato" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgStatus", twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterSkillUsuarioController:gravaSkillContato" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="innerBrowserUsuario.jsp"
	 * @jpf:forward name="success1" path="manterSkillUsuario.jsp"
	 *
	 */
	public ActionForward pesquisaPorLogin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SkillUsuarioForm form = (SkillUsuarioForm) formParam;

		log.debug("ManterSkillUsuarioController:pesquisaPorLogin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		try {
			AdmSkillUsuarioVO admSkillUsuarioVO = AdmSkillUsuarioVO.Factory.newInstance();
			EstruturaSkillTO estruturaSkillTO = new EstruturaSkillTO();
			admSkillUsuarioVO.setLogin(form.getAdmSkillUsuario().getLogin());
			form.getAdmSkillUsuario().setInOperacao(ConstantesCRM.STWO);
			estruturaSkillTO = controlSkillUsuario.getUsuarioSkillTunado(form.getAdmSkillUsuario(), ConstantesCRM.getCurrentUser(request.getSession()));
			this.getSkillUsuarioForm().setListaUsuariosExistentes(null);
			for (int i = 0; i < estruturaSkillTO.getListaUsuariosExistentes().size(); i++) {
				UsuarioExistenteVO[] u = (UsuarioExistenteVO[]) estruturaSkillTO.getListaUsuariosExistentes().get(i);
				for (int j = 0; j < u.length; j++) {
					admSkillUsuarioVO.insertNewUsuarioExistenteVO(admSkillUsuarioVO.getUsuarioExistenteVOArray().length);
					admSkillUsuarioVO.getUsuarioExistenteVOArray(admSkillUsuarioVO.getUsuarioExistenteVOArray().length - 1).setIdUsuario(u[j].getIdUsuario());
					admSkillUsuarioVO.getUsuarioExistenteVOArray(admSkillUsuarioVO.getUsuarioExistenteVOArray().length - 1).setNmUsuario(u[j].getNmUsuario());
				}
			}
			admSkillUsuarioVO = trataPesquisaPorLogin(admSkillUsuarioVO, form.getUsuarioSelecionado());
			this.getSkillUsuarioForm().getAdmSkillUsuario().setUsuarioExistenteVOArray(admSkillUsuarioVO.getUsuarioExistenteVOArray());
			this.getSkillUsuarioForm().setFlagOperacao(GRAVAR);
		} catch (TuxedoWarningException twe) {
			log.warn("ManterSkillUsuarioController:pesquisaPorLogin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgStatus", twe.getXmlHeader().getStatusText());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="innerBrowserUsuario.jsp"
	 * @jpf:forward name="success1" path="skillContato.jsp"
	 *
	 */
	public ActionForward pesquisaPorContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SkillUsuarioForm form = (SkillUsuarioForm) formParam;

		log.debug("ManterSkillUsuarioController:pesquisaPorContato" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		try {
			AdmSkillUsuarioVO admSkillUsuarioVO = AdmSkillUsuarioVO.Factory.newInstance();
			EstruturaSkillTO estruturaSkillTO = new EstruturaSkillTO();
			admSkillUsuarioVO.setLogin(form.getAdmSkillUsuario().getLogin());
			form.getAdmSkillUsuario().setInOperacao(ConstantesCRM.SNINE);
			estruturaSkillTO = controlSkillUsuario.getUsuarioSkillTunado(form.getAdmSkillUsuario(), ConstantesCRM.getCurrentUser(request.getSession()));
			this.getSkillUsuarioForm().setListaUsuariosExistentes(null);

			for (int i = 0; i < estruturaSkillTO.getListaUsuariosExistentes().size(); i++) {

				UsuarioExistenteVO[] u = (UsuarioExistenteVO[]) estruturaSkillTO.getListaUsuariosExistentes().get(i);

				for (int j = 0; j < u.length; j++) {

					admSkillUsuarioVO.insertNewUsuarioExistenteVO(admSkillUsuarioVO.getUsuarioExistenteVOArray().length);
					admSkillUsuarioVO.getUsuarioExistenteVOArray(admSkillUsuarioVO.getUsuarioExistenteVOArray().length - 1).setIdUsuario(u[j].getIdUsuario());
					admSkillUsuarioVO.getUsuarioExistenteVOArray(admSkillUsuarioVO.getUsuarioExistenteVOArray().length - 1).setNmUsuario(u[j].getNmUsuario());

				}
			}

			admSkillUsuarioVO = trataPesquisaPorLogin(admSkillUsuarioVO, form.getUsuarioSelecionado());
			getSkillUsuarioForm().getAdmSkillUsuario().setUsuarioExistenteVOArray(admSkillUsuarioVO.getUsuarioExistenteVOArray());

		} catch (TuxedoWarningException twe) {
			log.warn("ManterSkillUsuarioController:pesquisaPorContato" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgStatus", twe.getXmlHeader().getStatusText());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterSkillUsuario.jsp"
	 */
	public ActionForward filtraSkill(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SkillUsuarioForm form = (SkillUsuarioForm) formParam;

		log.debug("ManterSkillUsuarioController:filtraSkill" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		try {

			EstruturaSkillTO estruturaSkillTO = new EstruturaSkillTO();
			AdmSkillUsuarioVO admSkillUsuarioVO = AdmSkillUsuarioVO.Factory.newInstance();
			admSkillUsuarioVO.setSkillSelecionado(form.getAdmSkillUsuario().getSkillSelecionado());
			estruturaSkillTO = controlSkillUsuario.getUsuarioSkillExistente(admSkillUsuarioVO, ConstantesCRM.getCurrentUser(request.getSession()));
			estruturaSkillTO = controlSkillUsuario.getUsuarioSkillAssociado(admSkillUsuarioVO, ConstantesCRM.getCurrentUser(request.getSession()), estruturaSkillTO);
			this.getSkillUsuarioForm().setListaUsuariosExistentes(estruturaSkillTO.getListaUsuariosExistentes());
			this.getSkillUsuarioForm().setListaUsuarioSkillAssociado(estruturaSkillTO.getListaUsuarioSkillAssociado());
			this.getSkillUsuarioForm().getAdmSkillUsuario().setSkillSelecionado(form.getAdmSkillUsuario().getSkillSelecionado());
			this.getSkillUsuarioForm().getAdmSkillUsuario().setGrupoSelecionado(form.getAdmSkillUsuario().getGrupoSelecionado());
			this.getSkillUsuarioForm().setFlagOperacao(GRAVAR);
		} catch (TuxedoWarningException twe) {
			log.warn("ManterSkillUsuarioController:filtraSkill" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgStatus", twe.getXmlHeader().getStatusText());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="skillContato.jsp"
	 */
	public ActionForward filtraSkillContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SkillUsuarioForm form = (SkillUsuarioForm) formParam;

		log.debug("ManterSkillUsuarioController:filtraSkillContato" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		try {
			EstruturaSkillTO estruturaSkillTO = new EstruturaSkillTO();
			AdmSkillUsuarioVO admSkillUsuarioVO = AdmSkillUsuarioVO.Factory.newInstance();
			admSkillUsuarioVO.setSkillSelecionado(form.getAdmSkillUsuario().getSkillSelecionado());
			estruturaSkillTO = controlSkillUsuario.getContatoSkillExistente(admSkillUsuarioVO, ConstantesCRM.getCurrentUser(request.getSession()));
			estruturaSkillTO = controlSkillUsuario.getContatoSkillAssociado(admSkillUsuarioVO, ConstantesCRM.getCurrentUser(request.getSession()), estruturaSkillTO);
			this.getSkillUsuarioForm().setListaContatoExistente(estruturaSkillTO.getListaContatosExistentes());
			this.getSkillUsuarioForm().setListaContatoAssociado(estruturaSkillTO.getListaContatosAssociados());
			this.getSkillUsuarioForm().getAdmSkillUsuario().setSkillSelecionado(form.getAdmSkillUsuario().getSkillSelecionado());
			this.getSkillUsuarioForm().getAdmSkillUsuario().setGrupoSelecionado(form.getAdmSkillUsuario().getGrupoSelecionado());
		} catch (TuxedoWarningException twe) {
			log.warn("ManterSkillUsuarioController:filtraSkill" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgStatus", twe.getXmlHeader().getStatusText());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterSkillUsuario.jsp"
	 */
	public ActionForward filtraSkillRelacionar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SkillUsuarioForm form = (SkillUsuarioForm) formParam;

		log.debug("ManterSkillUsuarioController:filtraSkill" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		try {

			EstruturaSkillTO estruturaSkillTO = new EstruturaSkillTO();
			AdmSkillUsuarioVO admSkillUsuarioVO = AdmSkillUsuarioVO.Factory.newInstance();
			admSkillUsuarioVO.setSkillSelecionado(form.getAdmSkillUsuario().getSkillSelecionado());
			estruturaSkillTO = controlSkillUsuario.getUsuarioSkillExistente(admSkillUsuarioVO, ConstantesCRM.getCurrentUser(request.getSession()));
			estruturaSkillTO = controlSkillUsuario.getUsuarioSkillAssociado(admSkillUsuarioVO, ConstantesCRM.getCurrentUser(request.getSession()), estruturaSkillTO);
			this.getSkillUsuarioForm().setListaUsuariosExistentes(estruturaSkillTO.getListaUsuariosExistentes());
			this.getSkillUsuarioForm().setListaUsuarioSkillAssociado(estruturaSkillTO.getListaUsuarioSkillAssociado());
			this.getSkillUsuarioForm().getAdmSkillUsuario().setSkillSelecionado(form.getAdmSkillUsuario().getSkillSelecionado());
			this.getSkillUsuarioForm().getAdmSkillUsuario().setGrupoSelecionado(form.getAdmSkillUsuario().getGrupoSelecionado());
			this.getSkillUsuarioForm().setFlagOperacao(RELACIONAR);
		} catch (TuxedoWarningException twe) {
			log.warn("ManterSkillUsuarioController:filtraSkill" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgStatus", twe.getXmlHeader().getStatusText());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ManterUsuariosRelacionados.jsp"
	 */
	public ActionForward listaUsuarios(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SkillUsuarioForm form = (SkillUsuarioForm) formParam;

		log.debug("ManterSkillUsuarioController:listaUsuarios" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		EstruturaSkillTO estruturaSkillTO = new EstruturaSkillTO();

		form.getAdmSkillUsuario().setInOperacao(ConstantesCRM.STWO);
		AdmSkillUsuarioVO admSkillUsuario = form.getAdmSkillUsuario();
		admSkillUsuario.setContatosSelecionadosArray(form.getContatoSelecionado());
		admSkillUsuario.setUsuariosSelecionadosArray(form.getUsuarioSelecionado());
		estruturaSkillTO = controlSkillUsuario.getUsuarioSkillTunado(admSkillUsuario, ConstantesCRM.getCurrentUser(request.getSession()));

		this.getSkillUsuarioForm().setListaUsuariosExistentes(estruturaSkillTO.getListaUsuariosExistentes());
		this.getSkillUsuarioForm().setListaUsuarioSkillAssociado(estruturaSkillTO.getListaUsuarioSkillAssociado());
		this.getSkillUsuarioForm().setSkillUsuarioArrayLength(String.valueOf(estruturaSkillTO.getAdmSkillUsuarioVO().getUsuarioSkillAssociadoVOArray().length));
		this.getSkillUsuarioForm().setFlagOperacao(GRAVAR);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="filtraSkillContato.do"
	 */
	public ActionForward avancarParaContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SkillUsuarioForm form = (SkillUsuarioForm) formParam;

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class SkillUsuarioForm extends ActionForm {

		private static final long serialVersionUID = -2622452576260342353L;
		private String flagOperacao;
		private ArrayList listaContatoExistente;
		private ArrayList listaContatoAssociado;
		private String[] contatoExistente;
		private String paramChkUser;
		private String skillUsuarioArrayLength;
		private String[] contatoSelecionado;
		private String[] usuarioSelecionado;
		private String inPesquisa;
		private String inFolha;
		private String dsPath;
		private String idContato;
		private String[] usuarioExistente;
		private AdmSkillUsuarioVO admSkillUsuario;
		private ArrayList listaUsuariosExistentes;
		private ArrayList listaUsuarioSkillAssociado;

		public void setListaUsuariosExistentes(ArrayList lista) {
			listaUsuariosExistentes = lista;
		}

		public void setListaUsuarioSkillAssociado(ArrayList lista) {
			listaUsuarioSkillAssociado = lista;
		}

		public ArrayList getListaUsuariosExistentes() {
			if (this.listaUsuariosExistentes == null) {
				this.listaUsuariosExistentes = new ArrayList();
			}
			return this.listaUsuariosExistentes;
		}

		public ArrayList getListaUsuarioSkillAssociado() {
			if (this.listaUsuarioSkillAssociado == null) {
				this.listaUsuarioSkillAssociado = new ArrayList();
			}
			return this.listaUsuarioSkillAssociado;
		}

		public void setAdmSkillUsuario(AdmSkillUsuarioVO admSkillUsuario) {
			this.admSkillUsuario = admSkillUsuario;
		}

		public AdmSkillUsuarioVO getAdmSkillUsuario() {
			if (this.admSkillUsuario == null) {
				this.admSkillUsuario = AdmSkillUsuarioVO.Factory.newInstance();

				admSkillUsuario.addNewAdmContatoFolhaVO();
				admSkillUsuario.addNewAdmGrupoVO();
				// admSkillUsuario.addNewAdmSkillSimplVO();
				// admSkillUsuario.addNewUsuarioExistenteVO();
				// admSkillUsuario.addNewUsuarioSelecionadoVO();
				// admSkillUsuario.addNewUsuarioSkillAssociadoVO();
				admSkillUsuario.setLogin(ConstantesCRM.SVAZIO);
				admSkillUsuario.setGrupoSelecionado(ConstantesCRM.SVAZIO);
				admSkillUsuario.setSkillSelecionado(ConstantesCRM.SVAZIO);
				admSkillUsuario.setUsuariosSelecionadosArray(new String[0]);
				admSkillUsuario.setContatosSelecionadosArray(new String[0]);

			}

			return this.admSkillUsuario;
		}

		public void setUsuarioExistente(String[] usuarioExistente) {
			this.usuarioExistente = usuarioExistente;
		}

		public String[] getUsuarioExistente() {
			if (this.usuarioExistente == null) {
				this.usuarioExistente = new String[0];
			}

			return this.usuarioExistente;
		}

		public void setIdContato(String idContato) {
			this.idContato = idContato;
		}

		public String getIdContato() {
			if (this.idContato == null) {
				this.idContato = ConstantesCRM.SVAZIO;
			}
			return this.idContato;
		}

		public void setDsPath(String dsPath) {
			this.dsPath = dsPath;
		}

		public String getDsPath() {
			if (this.dsPath == null) {
				this.dsPath = ConstantesCRM.SVAZIO;
			}

			return this.dsPath;
		}

		public void setInFolha(String inFolha) {
			this.inFolha = inFolha;
		}

		public String getInFolha() {
			if (this.inFolha == null) {
				this.inFolha = ConstantesCRM.SVAZIO;
			}

			return this.inFolha;
		}

		public void setInPesquisa(String inPesquisa) {
			this.inPesquisa = inPesquisa;
		}

		public String getInPesquisa() {
			if (this.inPesquisa == null) {
				this.inPesquisa = ConstantesCRM.SVAZIO;
			}

			return this.inPesquisa;
		}

		public void setUsuarioSelecionado(String[] usuarioSelecionado) {
			this.usuarioSelecionado = usuarioSelecionado;
		}

		public String[] getUsuarioSelecionado() {
			if (this.usuarioSelecionado == null) {
				this.usuarioSelecionado = new String[0];
			}

			return this.usuarioSelecionado;
		}

		public void setContatoSelecionado(String[] contatoSelecionado) {
			this.contatoSelecionado = contatoSelecionado;
		}

		public String[] getContatoSelecionado() {
			if (this.contatoSelecionado == null) {
				this.contatoSelecionado = new String[0];
			}

			return this.contatoSelecionado;
		}

		public void setSkillUsuarioArrayLength(String skillUsuarioArrayLength) {
			this.skillUsuarioArrayLength = skillUsuarioArrayLength;
		}

		public String getSkillUsuarioArrayLength() {
			return this.skillUsuarioArrayLength;
		}

		public void setParamChkUser(String paramChkUser) {
			this.paramChkUser = paramChkUser;
		}

		public String getParamChkUser() {
			return this.paramChkUser;
		}

		public void setContatoExistente(String[] contatoExistente) {
			this.contatoExistente = contatoExistente;
		}

		public String[] getContatoExistente() {
			if (this.contatoExistente == null) {
				this.contatoExistente = new String[0];
			}

			return this.contatoExistente;
		}

		public void setListaContatoAssociado(ArrayList listaContatoAssociado) {
			this.listaContatoAssociado = listaContatoAssociado;
		}

		public ArrayList getListaContatoAssociado() {
			if (this.listaContatoAssociado == null) {
				this.listaContatoAssociado = new ArrayList();
			}
			return this.listaContatoAssociado;
		}

		public void setListaContatoExistente(ArrayList listaContatoExistente) {
			this.listaContatoExistente = listaContatoExistente;
		}

		public ArrayList getListaContatoExistente() {
			if (this.listaContatoExistente == null) {
				this.listaContatoExistente = new ArrayList();
			}

			return this.listaContatoExistente;
		}

		public void setFlagOperacao(String flagOperacao) {
			this.flagOperacao = flagOperacao;
		}

		public String getFlagOperacao() {
			return this.flagOperacao;
		}
	}

	public void setSkillUsuarioForm(SkillUsuarioForm form) {
		this.skillUsuarioForm = form;
	}

	public SkillUsuarioForm getSkillUsuarioForm() {
		if (this.skillUsuarioForm == null) {
			this.skillUsuarioForm = new SkillUsuarioForm();
		}

		return this.skillUsuarioForm;
	}

	private AdmSkillUsuarioVO trataPesquisaPorLogin(AdmSkillUsuarioVO admSkillUsuarioVO, String[] usuarioSelecionado) {
		if (admSkillUsuarioVO == null || usuarioSelecionado == null || admSkillUsuarioVO.getUsuarioExistenteVOArray() == null) {
			return null;
		}

		for (int i = 0; i < admSkillUsuarioVO.getUsuarioExistenteVOArray().length; i++) {
			for (int x = 0; x < usuarioSelecionado.length; x++) {
				if (usuarioSelecionado[x].equals(admSkillUsuarioVO.getUsuarioExistenteVOArray(i).getIdUsuario())) {
					admSkillUsuarioVO.removeUsuarioExistenteVO(i);
				}
			}
		}

		return admSkillUsuarioVO;
	}

}
