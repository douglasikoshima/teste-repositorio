package usuario.manterUsuario.editarUsuario.relacionarOperadora;

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
import br.com.vivo.fo.usuario.vo.OperadoraUsuarioVODocument.OperadoraUsuarioVO;
import br.com.vivo.fo.usuario.vo.RelacionarUsuarioOperadoraSimplVODocument.RelacionarUsuarioOperadoraSimplVO;
import br.com.vivo.fo.usuario.vo.RelacionarUsuarioOperadoraVODocument.RelacionarUsuarioOperadoraVO;
import br.com.vivo.fo.usuario.vo.UsuarioIDVODocument.UsuarioIDVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class RelacionarOperadoraController extends AbstractAction {

	private static final long serialVersionUID = 3956332713403538575L;

	@EJB
	private ManterUsuarioFacade controlUsuario;

	private SalvaRelOperadoraForm salvaRelOperadoraForm = new SalvaRelOperadoraForm();

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("usuario");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("salvaRelOperadora".equals(mapping.getParameter())) {
			return salvaRelOperadora(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relacionarUsuarioOperadora.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("RelacionarOperadoraController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		try {
			salvaRelOperadoraForm = new SalvaRelOperadoraForm();
			// Grava o id do usuario
			UsuarioIDVO usuarioId = UsuarioIDVO.Factory.newInstance();

			String userID = request.getParameter("userId");
			if (userID != null) {
				usuarioId.setIdUsuario(new BigInteger(userID));
			}

			// Grava a lista de operadoras existentes e relacionados nas variaveis adequadas
			RelacionarUsuarioOperadoraVO relacionarUsuarioOperadoraVO = RelacionarUsuarioOperadoraVO.Factory.newInstance();
			relacionarUsuarioOperadoraVO = controlUsuario.relacionaUsuarioOperadora(usuarioId, ConstantesCRM.getCurrentUser(request.getSession()));
			salvaRelOperadoraForm.setOperadorasExistentesVO(relacionarUsuarioOperadoraVO.getOperadorasExistentes().getOperadoraUsuarioVOArray());
			salvaRelOperadoraForm.setOperadorasRelacionadosVO(relacionarUsuarioOperadoraVO.getOperadorasRelacionadas().getOperadoraUsuarioVOArray());
		} catch (TuxedoWarningException twe) {
			log.warn("RelacionarOperadoraController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			salvaRelOperadoraForm.setMsgError(twe.getXmlHeader().getStatusText());
		} catch (Exception e) {
			log.error("RelacionarOperadoraController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/index.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relacionarUsuarioOperadora.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaRelOperadora(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SalvaRelOperadoraForm form = (SalvaRelOperadoraForm) formParam;

		log.debug("RelacionarOperadoraController:salvaRelOperadora" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		salvaRelOperadoraForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			String[] operadorasRelacionados = null;
			if (form.getOperadorasRelacionados() != null) {
				operadorasRelacionados = new String[form.getOperadorasRelacionados().length];
			} else {
				operadorasRelacionados = new String[0];
			}
			if (form.getOperadorasRelacionados() != null) {
				operadorasRelacionados = form.getOperadorasRelacionados();
			}
			RelacionarUsuarioOperadoraSimplVO relacionarUsuarioOperadoraSimplVO = RelacionarUsuarioOperadoraSimplVO.Factory.newInstance();
			OperadoraUsuarioVO[] operadoraUsuarioVO = new OperadoraUsuarioVO[operadorasRelacionados.length];
			for (int i = 0; i < operadorasRelacionados.length; i++) {
				operadoraUsuarioVO[i] = OperadoraUsuarioVO.Factory.newInstance();
				operadoraUsuarioVO[i].setIdOperadora(operadorasRelacionados[i]);
			}
			relacionarUsuarioOperadoraSimplVO.addNewOperadorasRelacionadas();
			relacionarUsuarioOperadoraSimplVO.getOperadorasRelacionadas().setOperadoraUsuarioVOArray(operadoraUsuarioVO);
			String userID = request.getParameter("userId");
			if (userID != null) {
				relacionarUsuarioOperadoraSimplVO.setIdUsuario(userID);
			}

			RelacionarUsuarioOperadoraVO relacionarUsuarioOperadoraVO = RelacionarUsuarioOperadoraVO.Factory.newInstance();
			relacionarUsuarioOperadoraVO = controlUsuario.salvaUsuarioOperadoraRelacionado(relacionarUsuarioOperadoraSimplVO, ConstantesCRM.getCurrentUser(request.getSession()));

			salvaRelOperadoraForm.setOperadorasExistentesVO(relacionarUsuarioOperadoraVO.getOperadorasExistentes().getOperadoraUsuarioVOArray());
			salvaRelOperadoraForm.setOperadorasRelacionadosVO(relacionarUsuarioOperadoraVO.getOperadorasRelacionadas().getOperadoraUsuarioVOArray());
			salvaRelOperadoraForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			//log.warn("RelacionarItemMenuController:salvaRelOperadora" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			if(twe.getMessage().equals("Somente usuário DESLIGADO pode ficar sem regionais associadas.")){
				salvaRelOperadoraForm.setMsgError(twe.getMessage());
			}else{
				salvaRelOperadoraForm.setMsgError(twe.getXmlHeader().getStatusText());
			}

		} catch (Exception e) {
			log.error("RelacionarOperadoraController:salvaRelOperadora" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

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

	public static class SalvaRelOperadoraForm extends ActionForm {

		private static final long serialVersionUID = 1292126299890000363L;

		private String msgError = ConstantesCRM.SVAZIO;

		private OperadoraUsuarioVO[] operadorasRelacionadosVO;
		private String[] operadorasRelacionados;
		private OperadoraUsuarioVO[] operadorasExistentesVO;
		private String[] operadorasExistentes;
		private String idUsuario;

		public void setIdUsuario(String idUsuario) {
			this.idUsuario = idUsuario;
		}

		public String getIdUsuario() {
			return this.idUsuario;
		}

		public void setOperadorasExistentes(String[] operadorasExistentes) {
			this.operadorasExistentes = operadorasExistentes;
		}

		public String[] getOperadorasExistentes() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.operadorasExistentes == null || this.operadorasExistentes.length == 0) {
				this.operadorasExistentes = new String[1];
			}

			return this.operadorasExistentes;
		}

		public void setOperadorasExistentesVO(OperadoraUsuarioVO[] operadorasExistentesVO) {
			this.operadorasExistentesVO = operadorasExistentesVO;
		}

		public OperadoraUsuarioVO[] getOperadorasExistentesVO() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null. This type doesn't have
			// a default constructor, so Workshop cannot initialize it for you.

			// TODO: Initialize operadorasExistentesVO if it is null or length == 0.
			// if(this.operadorasExistentesVO == null || this.operadorasExistentesVO.length == 0)
			// {
			// this.operadorasExistentesVO = new OperadorasExistentesVO[1];
			// this.operadorasExistentesVO[0] = new OperadorasExistentesVO(?);
			// }

			return this.operadorasExistentesVO;
		}

		public void setOperadorasRelacionados(String[] operadorasRelacionados) {
			this.operadorasRelacionados = operadorasRelacionados;
		}

		public String[] getOperadorasRelacionados() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.operadorasRelacionados == null || this.operadorasRelacionados.length == 0) {
				this.operadorasRelacionados = new String[1];
			}

			return this.operadorasRelacionados;
		}

		public void setOperadorasRelacionadosVO(OperadoraUsuarioVO[] operadorasRelacionadosVO) {
			this.operadorasRelacionadosVO = operadorasRelacionadosVO;
		}

		public OperadoraUsuarioVO[] getOperadorasRelacionadosVO() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null. This type doesn't have
			// a default constructor, so Workshop cannot initialize it for you.

			// TODO: Initialize operadorasRelacionadosVO if it is null or length == 0.
			// if(this.operadorasRelacionadosVO == null || this.operadorasRelacionadosVO.length == 0)
			// {
			// this.operadorasRelacionadosVO = new GruposRelacionadosVO[1];
			// this.operadorasRelacionadosVO[0] = new GruposRelacionadosVO(?);
			// }

			return this.operadorasRelacionadosVO;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}
	}

	public SalvaRelOperadoraForm getSalvaRelOperadoraForm() {
		return (this.salvaRelOperadoraForm);
	}
}
