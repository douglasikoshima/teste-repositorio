package usuario.manterUsuario.editarUsuario.relacionarSkill;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.AdmSkillVODocument.AdmSkillVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ManterSkillController extends AbstractAction {

	private static final long serialVersionUID = 6124110909749799673L;

	//@EJB private ManterUsuarioFacade controlUsuario;

	protected global.Global globalApp = new global.Global();

	private SkillForm skillForm = null;

	private static transient Logger log = new Logger("usuario");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("salvaSkill".equals(mapping.getParameter())) {
			return salvaSkill(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="error" path="/error.jsp"
	 * @jpf:forward name="success" path="manterSkill.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterSkillController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		try {
			AdmSkillVO admSkillVO = AdmSkillVO.Factory.newInstance();
			// getSkillForm().setAdmSkillVO(controlUsuario.relacionarSkillUsuario(admSkillVO,ConstantesCRM.getCurrentUser(request.getSession())));

		} catch (Exception e) {
			log.error("ManterSkillController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
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
	 * @jpf:forward name="error" path="/error.jsp"
	 * @jpf:forward name="success" path="manterSkill.jsp"
	 */
	public ActionForward salvaSkill(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SkillForm form = (SkillForm) formParam;
		log.debug("ManterSkillController:" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		String idPessoal = (request.getParameter("idUser") != null) ? request.getParameter("idUser") : "";
		form.getAdmSkillVO().setIdPessoa(idPessoal);

		try {
			//getSkillForm().setAdmSkillVO(controlUsuario.relacionarSkillUsuario(form.getAdmSkillVO(), ConstantesCRM.getCurrentUser(request.getSession())));
			request.setAttribute("msgStatus", ConstantesCRM.SSucesso);

			// }catch(TuxedoWarningException twe){
			// log.warn("ManterSkillController:salvaSkill"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) +
			// " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			// request.setAttribute("msgStatus",twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterSkillController:salvaSkill" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class SkillForm extends ActionForm {

		private static final long serialVersionUID = -4864704136094743166L;
		private String[] skillNAssociadoArray;
		private String[] skillAssociadoArray;
		private AdmSkillVO admSkillVO;

		public void setAdmSkillVO(AdmSkillVO admSkillVO) {
			this.admSkillVO = admSkillVO;
		}

		public AdmSkillVO getAdmSkillVO() {
			if (this.admSkillVO == null) {
				this.admSkillVO = AdmSkillVO.Factory.newInstance();
			}

			return this.admSkillVO;
		}

		public void setSkillAssociadoArray(String[] skillAssociadoArray) {
			this.skillAssociadoArray = skillAssociadoArray;
		}

		public String[] getSkillAssociadoArray() {
			if (this.skillAssociadoArray == null) {
				this.skillAssociadoArray = new String[0];
			}

			return this.skillAssociadoArray;
		}

		public void setSkillNAssociadoArray(String[] skillNAssociadoArray) {
			this.skillNAssociadoArray = skillNAssociadoArray;
		}

		public String[] getSkillNAssociadoArray() {
			if (this.skillNAssociadoArray == null) {
				this.skillNAssociadoArray = new String[0];
			}

			return this.skillNAssociadoArray;
		}
	}

	public SkillForm getSkillForm() {
		if (this.skillForm == null) {
			this.skillForm = new SkillForm();
		}

		return this.skillForm;
	}

	public void setSkillForm(SkillForm form) {
		this.skillForm = form;
	}
}