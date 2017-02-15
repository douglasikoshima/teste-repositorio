package usuario.manterUsuario.editarUsuario.relacionarPerfil;

import java.math.BigInteger;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.usuario.manterUsuario.ManterUsuarioFacade;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.PerfilUsuarioVODocument.PerfilUsuarioVO;
import br.com.vivo.fo.usuario.vo.RelacionarUsuarioPerfilSimplVODocument.RelacionarUsuarioPerfilSimplVO;
import br.com.vivo.fo.usuario.vo.RelacionarUsuarioPerfilVODocument.RelacionarUsuarioPerfilVO;
import br.com.vivo.fo.usuario.vo.UsuarioIDVODocument.UsuarioIDVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class RelacionarPerfilController extends AbstractAction {

	private static final long serialVersionUID = 7578233571134146495L;

	@EJB
	private ManterUsuarioFacade controlUsuario;

	private SalvaRelPerfilForm salvaRelPerfilForm = new SalvaRelPerfilForm();
	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("usuario");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("salvaRelPerfil".equals(mapping.getParameter())) {
			return salvaRelPerfil(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relacionarUsuarioPerfil.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("RelacionarPerfilController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		try {
			salvaRelPerfilForm = new SalvaRelPerfilForm();
			// Grava o id do usuario
			UsuarioIDVO usuarioId = UsuarioIDVO.Factory.newInstance();

			String userID = request.getParameter("userId");
			if (userID != null) {
				usuarioId.setIdUsuario(new BigInteger(userID));
			}

			// Grava a lista de perfis existentes e relacionados nas variaveis adequadas
			RelacionarUsuarioPerfilVO relacionarUsuarioPerfilVO = RelacionarUsuarioPerfilVO.Factory.newInstance();
			relacionarUsuarioPerfilVO = controlUsuario.relacionaUsuarioPerfil(usuarioId, ConstantesCRM.getCurrentUser(request.getSession()));
			salvaRelPerfilForm.setPerfisExistentesVO(relacionarUsuarioPerfilVO.getPerfisExistentes().getPerfilUsuarioVOArray());
			salvaRelPerfilForm.setPerfisRelacionadosVO(relacionarUsuarioPerfilVO.getPerfisRelacionados().getPerfilUsuarioVOArray());

		} catch (TuxedoWarningException twe) {
			log.warn("RelacionarPerfilController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			salvaRelPerfilForm.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("RelacionarPerfilController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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
	 * @jpf:forward name="success" path="relacionarUsuarioPerfil.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaRelPerfil(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SalvaRelPerfilForm form = (SalvaRelPerfilForm) formParam;

		log.debug("RelacionarPerfilController:salvaRelPerfil" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		salvaRelPerfilForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			String[] perfisRelacionados = null;
			if (form.getPerfisRelacionados() != null) {
				perfisRelacionados = new String[form.getPerfisRelacionados().length];
			} else {
				perfisRelacionados = new String[0];
			}

			if (form.getPerfisRelacionados() != null) {
				perfisRelacionados = form.getPerfisRelacionados();
			}

			RelacionarUsuarioPerfilSimplVO relacionarUsuarioPerfilSimplVO = RelacionarUsuarioPerfilSimplVO.Factory.newInstance();
			PerfilUsuarioVO[] perfilUsuarioVO = new PerfilUsuarioVO[perfisRelacionados.length];

			for (int i = 0; i < perfisRelacionados.length; i++) {
				perfilUsuarioVO[i] = PerfilUsuarioVO.Factory.newInstance();
				perfilUsuarioVO[i].setIdPerfil(perfisRelacionados[i]);
			}

			relacionarUsuarioPerfilSimplVO.addNewPerfisRelacionados();
			relacionarUsuarioPerfilSimplVO.getPerfisRelacionados().setPerfilUsuarioVOArray(perfilUsuarioVO);

			String userID = request.getParameter("userId");
			if (userID != null) {
				relacionarUsuarioPerfilSimplVO.setIdUsuario(userID);
			}

			RelacionarUsuarioPerfilVO relacionarUsuarioPerfilVO = RelacionarUsuarioPerfilVO.Factory.newInstance();
			relacionarUsuarioPerfilVO = controlUsuario.salvaUsuarioPerfilRelacionado(relacionarUsuarioPerfilSimplVO, ConstantesCRM.getCurrentUser(request.getSession()));

			salvaRelPerfilForm.setPerfisExistentesVO(relacionarUsuarioPerfilVO.getPerfisExistentes().getPerfilUsuarioVOArray());
			salvaRelPerfilForm.setPerfisRelacionadosVO(relacionarUsuarioPerfilVO.getPerfisRelacionados().getPerfilUsuarioVOArray());
			salvaRelPerfilForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("RelacionarPerfilController:salvaRelPerfil" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			salvaRelPerfilForm.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("RelacionarPerfilController:salvaRelPerfil" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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

	public static class SalvaRelPerfilForm extends ActionForm {

		private static final long serialVersionUID = 9198105256009612536L;

		private String msgError = ConstantesCRM.SVAZIO;

		private PerfilUsuarioVO[] perfisRelacionadosVO;
		private String[] perfisRelacionados;
		private PerfilUsuarioVO[] perfisExistentesVO;
		private String[] perfisExistentes;
		private String idUsuario;

		public void setIdUsuario(String idUsuario) {
			this.idUsuario = idUsuario;
		}

		public String getIdUsuario() {
			return this.idUsuario;
		}

		public void setPerfisExistentes(String[] perfisExistentes) {
			this.perfisExistentes = perfisExistentes;
		}

		public String[] getPerfisExistentes() {
			return this.perfisExistentes;
		}

		public void setPerfisExistentesVO(PerfilUsuarioVO[] perfisExistentesVO) {
			this.perfisExistentesVO = perfisExistentesVO;
		}

		public PerfilUsuarioVO[] getPerfisExistentesVO() {
			return this.perfisExistentesVO;
		}

		public void setPerfisRelacionados(String[] perfisRelacionados) {
			this.perfisRelacionados = perfisRelacionados;
		}

		public String[] getPerfisRelacionados() {
			return this.perfisRelacionados;
		}

		public void setPerfisRelacionadosVO(PerfilUsuarioVO[] perfisRelacionadosVO) {
			this.perfisRelacionadosVO = perfisRelacionadosVO;
		}

		public PerfilUsuarioVO[] getPerfisRelacionadosVO() {
			return this.perfisRelacionadosVO;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}
	}

	public SalvaRelPerfilForm getSalvaRelPerfilForm() {
		return (this.salvaRelPerfilForm);
	}

}
