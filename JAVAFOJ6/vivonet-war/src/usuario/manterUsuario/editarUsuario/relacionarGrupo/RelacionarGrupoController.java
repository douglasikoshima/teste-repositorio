package usuario.manterUsuario.editarUsuario.relacionarGrupo;

import java.math.BigInteger;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.usuario.manterUsuario.ManterUsuarioFacade;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.GrupoUsuarioVODocument.GrupoUsuarioVO;
import br.com.vivo.fo.usuario.vo.RelacionarUsuarioGrupoSimplVODocument.RelacionarUsuarioGrupoSimplVO;
import br.com.vivo.fo.usuario.vo.RelacionarUsuarioGrupoVODocument.RelacionarUsuarioGrupoVO;
import br.com.vivo.fo.usuario.vo.UsuarioIDVODocument.UsuarioIDVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class RelacionarGrupoController extends AbstractAction {

	private static final long serialVersionUID = -801501050861859536L;

	@EJB
	private ManterUsuarioFacade controlUsuario;

	private SalvaRelGrupoForm salvaRelGrupoForm = new SalvaRelGrupoForm();
	private SetSupervisorForm setSupervisorForm = new SetSupervisorForm();

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("usuario");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("salvaRelGrupo".equals(mapping.getParameter())) {
			return salvaRelGrupo(mapping, form, request, response);
		} else if ("supervisor".equals(mapping.getParameter())) {
			return supervisor(mapping, form, request, response);
		} else if ("setSupervisor".equals(mapping.getParameter())) {
			return setSupervisor(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relacionarUsuarioGrupo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SalvaRelGrupoForm form = (SalvaRelGrupoForm) formParam;

		log.debug("RelacionarGrupoController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		try {
			String strMsgError = ConstantesCRM.SVAZIO;
			strMsgError = form.getMsgError();
			salvaRelGrupoForm = new SalvaRelGrupoForm();
			salvaRelGrupoForm.setMsgError(strMsgError);
			// Grava o id do usuario
			UsuarioIDVO usuarioId = UsuarioIDVO.Factory.newInstance();
			String userID = request.getParameter("userId");
			if (userID != null) {
				usuarioId.setIdUsuario(new BigInteger(userID));
			}

			// Grava a lista de grupos existentes e relacionados nas variaveis
			// adequadas
			RelacionarUsuarioGrupoVO relacionarUsuarioGrupoVO = RelacionarUsuarioGrupoVO.Factory.newInstance();
			relacionarUsuarioGrupoVO = controlUsuario.relacionaUsuarioGrupo(usuarioId, ConstantesCRM.getCurrentUser(request.getSession()));
			salvaRelGrupoForm.setGruposExistentesVO(relacionarUsuarioGrupoVO.getGruposExistentes().getGrupoUsuarioVOArray());
			salvaRelGrupoForm.setGruposRelacionadosVO(relacionarUsuarioGrupoVO.getGruposRelacionados().getGrupoUsuarioVOArray());

		} catch (TuxedoWarningException twe) {
			log.warn("RelacionarGrupoController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			salvaRelGrupoForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("RelacionarGrupoController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
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
	 * @jpf:forward name="success" path="relacionarUsuarioGrupo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaRelGrupo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SalvaRelGrupoForm form = (SalvaRelGrupoForm) formParam;

		log.debug("RelacionarGrupoController:salvaRelGrupo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		salvaRelGrupoForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			RelacionarUsuarioGrupoSimplVO relacionarUsuarioGrupoVO = RelacionarUsuarioGrupoSimplVO.Factory.newInstance();
			GrupoUsuarioVO[] listaGrupos = new GrupoUsuarioVO[form.gruposRelacionados.length];
			// Percorre o array de ids de gruposRelacionadosVO e grava no
			// relacionarUsuarioGrupoVO
			for (int i = 0; i < form.gruposRelacionados.length; i++) {
				listaGrupos[i] = GrupoUsuarioVO.Factory.newInstance();
				listaGrupos[i].setIdGrupo(form.gruposRelacionados[i]);
			}
			relacionarUsuarioGrupoVO.addNewGruposRelacionados();
			relacionarUsuarioGrupoVO.getGruposRelacionados().setGrupoUsuarioVOArray(listaGrupos);

			String userID = request.getParameter("userId");
			if (userID != null) {
				relacionarUsuarioGrupoVO.setIdUsuario(userID);
			}

			RelacionarUsuarioGrupoVO relacionarUsuarioGrupoVORetorno = RelacionarUsuarioGrupoVO.Factory.newInstance();
			relacionarUsuarioGrupoVORetorno = controlUsuario.salvaUsuarioGrupoRelacionado(relacionarUsuarioGrupoVO, ConstantesCRM.getCurrentUser(request.getSession()));
			salvaRelGrupoForm.setGruposExistentesVO(relacionarUsuarioGrupoVORetorno.getGruposExistentes().getGrupoUsuarioVOArray());
			salvaRelGrupoForm.setGruposRelacionadosVO(relacionarUsuarioGrupoVORetorno.getGruposRelacionados().getGrupoUsuarioVOArray());
			salvaRelGrupoForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {

			if (twe.getXmlHeader() != null) {
				log.warn("RelacionarGrupoController:salvaRelGrupo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
				salvaRelGrupoForm.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));
			} else {
				salvaRelGrupoForm.setMsgError(twe.getMessage());
			}

		} catch (Exception e) {
			log.error("RelacionarGrupoController:salvaRelGrupo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
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
	 * @jpf:forward name="success" path="setSupervisor.jsp"
	 */
	public ActionForward supervisor(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("RelacionarGrupoController:supervisor" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		setSupervisorForm = new SetSupervisorForm();

		setSupervisorForm.setIdGrupo(request.getParameter("idGrupo"));
		setSupervisorForm.setIdUsuario(request.getParameter("idUsuario"));

		for (int i = 0; i < salvaRelGrupoForm.getGruposRelacionadosVO().length; i++) {
			if (salvaRelGrupoForm.getGruposRelacionadosVO()[i].getIdGrupo().equals(setSupervisorForm.getIdGrupo())) {
				setSupervisorForm.setDsGrupo(salvaRelGrupoForm.getGruposRelacionadosVO()[i].getDsGrupo());
				setSupervisorForm.setInSupervisor(salvaRelGrupoForm.getGruposRelacionadosVO()[i].getInSupervisor());
				break;
			}
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 */
	public ActionForward setSupervisor(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SetSupervisorForm form = (SetSupervisorForm) formParam;

		log.debug("RelacionarGrupoController:setSupervisor" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		try {
			controlUsuario.setSupervisor(ConstantesCRM.getCurrentUser(request.getSession()), setSupervisorForm.getIdGrupo(), setSupervisorForm.getIdUsuario(), form.getInSupervisor());
			salvaRelGrupoForm.setMsgError(ConstantesCRM.SSucesso);
		} catch (TuxedoWarningException twe) {
			log.warn("RelacionarGrupoController:setSupervisor" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			salvaRelGrupoForm.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));
		} catch (Exception e) {
			log.error("RelacionarGrupoController:setSupervisor" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			// formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		ActionRedirect forward = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
		forward.addParameter("userId", setSupervisorForm.getIdUsuario());
		return forward;
	}

	public static class SalvaRelGrupoForm extends ActionForm {

		private static final long serialVersionUID = -7361737853185495725L;

		private String msgError = ConstantesCRM.SVAZIO;

		private String idUsuario;
		private GrupoUsuarioVO[] gruposRelacionadosVO;
		private GrupoUsuarioVO[] gruposExistentesVO;
		private String[] gruposExistentes;
		private String[] gruposRelacionados;

		public SalvaRelGrupoForm() {

			// //////////////////////////////////
			// Bloco de Inicializacao - Begin //
			// //////////////////////////////////
			idUsuario = "";
			gruposRelacionadosVO = new GrupoUsuarioVO[0];
			gruposExistentesVO = new GrupoUsuarioVO[0];

			gruposExistentes = new String[0];
			gruposRelacionados = new String[0];
			// ////////////////////////////////
			// Bloco de Inicializacao - End //
			// ////////////////////////////////
		}

		public void setGruposExistentesVO(GrupoUsuarioVO[] gruposExistentesVO) {
			this.gruposExistentesVO = gruposExistentesVO;
		}

		public GrupoUsuarioVO[] getGruposExistentesVO() {
			return this.gruposExistentesVO;
		}

		public void setGruposRelacionadosVO(GrupoUsuarioVO[] gruposRelacionadosVO) {
			this.gruposRelacionadosVO = gruposRelacionadosVO;
		}

		public GrupoUsuarioVO[] getGruposRelacionadosVO() {
			return this.gruposRelacionadosVO;
		}

		public void setIdUsuario(String idUsuario) {
			this.idUsuario = idUsuario;
		}

		public String getIdUsuario() {
			return this.idUsuario;
		}

		public void setGruposExistentes(String[] gruposExistentes) {
			this.gruposExistentes = gruposExistentes;
		}

		public String[] getGruposExistentes() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.gruposExistentes == null || this.gruposExistentes.length == 0) {
				this.gruposExistentes = new String[1];
			}

			return this.gruposExistentes;
		}

		public void setGruposRelacionados(String[] gruposRelacionados) {
			this.gruposRelacionados = gruposRelacionados;
		}

		public String[] getGruposRelacionados() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.gruposRelacionados == null || this.gruposRelacionados.length == 0) {
				this.gruposRelacionados = new String[1];
			}

			return this.gruposRelacionados;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}
	}

	public SalvaRelGrupoForm getSalvaRelGrupoForm() {
		return (this.salvaRelGrupoForm);
	}

	public static class SetSupervisorForm extends ActionForm {

		private static final long serialVersionUID = 8186802666982531610L;

		private String idUsuario;
		private String dsGrupo;
		private String inSupervisor;
		private String idGrupo;

		public void setIdGrupo(String idGrupo) {
			this.idGrupo = idGrupo;
		}

		public String getIdGrupo() {
			return this.idGrupo;
		}

		public void setInSupervisor(String inSupervisor) {
			this.inSupervisor = inSupervisor;
		}

		public String getInSupervisor() {
			return this.inSupervisor;
		}

		public void setDsGrupo(String dsGrupo) {
			this.dsGrupo = dsGrupo;
		}

		public String getDsGrupo() {
			return this.dsGrupo;
		}

		public void setIdUsuario(String idUsuario) {
			this.idUsuario = idUsuario;
		}

		public String getIdUsuario() {
			return this.idUsuario;
		}

	}

	public SetSupervisorForm getSetSupervisorForm() {
		return this.setSupervisorForm;
	}
}