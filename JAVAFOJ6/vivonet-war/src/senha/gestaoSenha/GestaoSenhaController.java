package senha.gestaoSenha;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.senha.vo.SenhaOperarVODocument.SenhaOperarVO;

import com.indracompany.actions.AbstractAction;

public class GestaoSenhaController extends AbstractAction {

	private static final long serialVersionUID = 5062527811513271212L;

	private static Logger log = new Logger("senha");
	public final String TP_USUARIO = "U";
	public final String TP_CLIENTE = "C";

	@EJB
	private br.com.vivo.fo.ctrls.senha.operarSenha.OperarSenhaFacade operarSenhaFacade;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("gerarSenhaAction".equals(mapping.getParameter())) {
			return gerarSenhaAction(mapping, form, request, response);
		} else if ("alterarSenhaAction".equals(mapping.getParameter())) {
			return alterarSenhaAction(mapping, form, request, response);
		} else if ("validarSenhaAction".equals(mapping.getParameter())) {
			return validarSenhaAction(mapping, form, request, response);
		} else if ("reinicializarSenhaAction".equals(mapping.getParameter())) {
			return reinicializarSenhaAction(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="gestaoSenha.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" return-to="currentPage"
	 */
	public ActionForward gerarSenhaAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		GestaoSenhaForm form = (GestaoSenhaForm) formParam;
		log.debug("\t" + this.getClass().getName() + ":gerarSenhaAction()");

		operarSenhaFacade.GeraSenha(form.getIdPessoa(), form.getIdUsuario());

		if (form.getTipoPessoa().equals(this.TP_USUARIO)) {
			form.setMessage("Senha do usuário gerada com sucesso!");
		}
		if (form.getTipoPessoa().equals(this.TP_CLIENTE)) {
			form.setMessage("Senha do cliente gerada com sucesso!");
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" return-to="currentPage"
	 */
	public ActionForward alterarSenhaAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		GestaoSenhaForm form = (GestaoSenhaForm) formParam;
		log.debug("\t" + this.getClass().getName() + ":alterarSenhaAction()");

		operarSenhaFacade.AlteraSenha(form.getFoneArea(), form.getFoneNumero(), form.getIdPessoa(), form.getTipoPessoa(), form.getSenha(), form.getIdCanal(), form.getMotivo(), form.getIdUsuario());

		if (form.getTipoPessoa().equals(this.TP_USUARIO)) {
			form.setMessage("Senha do usuário alterada com sucesso!");
		}
		if (form.getTipoPessoa().equals(this.TP_CLIENTE)) {
			form.setMessage("Senha do cliente alterada com sucesso!");
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" return-to="currentPage"
	 */
	public ActionForward validarSenhaAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		GestaoSenhaForm form = (GestaoSenhaForm) formParam;
		log.debug("\t" + this.getClass().getName() + ":validarSenhaAction()");

		String ret = operarSenhaFacade.ValidaSenha(form.getFoneArea(), form.getFoneNumero(), form.getSenha(), form.getIdCanal(), form.getIdUsuario());

		if (form.getTipoPessoa().equals(this.TP_USUARIO)) {
			form.setMessage("Senha do usuário validada com sucesso! [" + ret + "]");
		}
		if (form.getTipoPessoa().equals(this.TP_CLIENTE)) {
			form.setMessage("Senha do cliente validada com sucesso! [" + ret + "]");
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" return-to="currentPage"
	 */
	public ActionForward reinicializarSenhaAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		GestaoSenhaForm form = (GestaoSenhaForm) formParam;
		log.debug("\t" + this.getClass().getName() + ":reinicializaAction()");

		SenhaOperarVO senhaOperarVo = operarSenhaFacade.ReiniciaSenha(ConstantesCRM.SZERO, form.getFoneArea(), form.getFoneNumero(), form.getIdPessoa(), form.getIdPessoa(), form.getTipoPessoa(), form.getSenha(), form.getIdCanal(), form.getMotivo(), form.getIdUsuario());

		form.setMessage(senhaOperarVo.getMensagem());

		/*
		 * if(senhaOperarVo != null) { if(form.getTipoPessoa().equals(this.TP_USUARIO)) {
		 * form.setMessage("Senha do usuário reinicializada com sucesso!"); }
		 * if(form.getTipoPessoa().equals(this.TP_CLIENTE)) {
		 * form.setMessage("Senha do cliente reinicializada com sucesso!"); } } else {
		 * form.setMessage("Senha não foi reinicializada!"); }
		 */

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class GestaoSenhaForm extends ActionForm {

		private static final long serialVersionUID = -7799056303805256856L;
		private String foneArea = "11";
		private String foneNumero = "12345678";
		private String tipoPessoa = "C";
		private String senha = ConstantesCRM.SVAZIO;
		private String idCanal = ConstantesCRM.SNINE;
		private String idPessoa = "10";
		private String message = ConstantesCRM.SVAZIO;
		private String motivo = ConstantesCRM.SVAZIO;
		private String idUsuario = ConstantesCRM.STWO;

		public void setFoneArea(String foneArea) {
			this.foneArea = foneArea;
		}

		public String getFoneArea() {
			return this.foneArea;
		}

		public void setFoneNumero(String foneNumero) {
			this.foneNumero = foneNumero;
		}

		public String getFoneNumero() {
			return this.foneNumero;
		}

		public void setTipoPessoa(String tipoPessoa) {
			this.tipoPessoa = tipoPessoa;
		}

		public String getTipoPessoa() {
			return this.tipoPessoa;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		public String getSenha() {
			return this.senha;
		}

		public void setIdCanal(String idCanal) {
			this.idCanal = idCanal;
		}

		public String getIdCanal() {
			return this.idCanal;
		}

		public void setIdPessoa(String idPessoa) {
			this.idPessoa = idPessoa;
		}

		public String getIdPessoa() {
			return this.idPessoa;
		}

		public void setMotivo(String motivo) {
			this.motivo = motivo;
		}

		public String getMotivo() {
			return this.motivo;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getMessage() {
			return this.message;
		}

		public void setIdUsuario(String idUsuario) {
			this.idUsuario = idUsuario;
		}

		public String getIdUsuario() {
			return this.idUsuario;
		}

	}
}
