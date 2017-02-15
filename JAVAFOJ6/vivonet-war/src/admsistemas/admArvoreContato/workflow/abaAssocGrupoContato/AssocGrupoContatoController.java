package admsistemas.admArvoreContato.workflow.abaAssocGrupoContato;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.AdmGrupoContatoContainerVODocument.AdmGrupoContatoContainerVO;
import br.com.vivo.fo.admsistemas.vo.AdmGrupoVODocument.AdmGrupoVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class AssocGrupoContatoController extends AbstractAction {

	private static final long serialVersionUID = 8169545805611275611L;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.configArvoreContato.ArvoreContato controlArvore;

	protected global.Global globalApp = new global.Global();

	private AssocGrupoContatoForm assocGrupoContatoForm = new AssocGrupoContatoForm();

	private AdmGrupoContatoContainerVO admGrupoContatoContainerVO = null;

	private final String MSG_SEM_CONTATO = "Não há Grupo(s) selecionado(s) para o contato";

	private static transient Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("gravaAssocGrupoContato".equals(mapping.getParameter())) {
			return gravaAssocGrupoContato(mapping, form, request, response);
		} else if ("listaAssocGrupoContato".equals(mapping.getParameter())) {
			return listaAssocGrupoContato(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="listaAssocGrupoContato.do"
	 * @jpf:forward name="error" path="assocGrupoContato.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("AssocGrupoContatoController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (admGrupoContatoContainerVO == null) {
			admGrupoContatoContainerVO = AdmGrupoContatoContainerVO.Factory.newInstance();
		}

		assocGrupoContatoForm.setCurrentUser(ConstantesCRM.getCurrentUser(request.getSession()));
		if (request.getParameter("idContato") != null) {
			assocGrupoContatoForm.setIdContato(request.getParameter("idContato"));
		} else {
			// gera erro dizendo que não há nehum contato para para associar
			assocGrupoContatoForm.setMsgError(this.MSG_SEM_CONTATO);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return this.listaAssocGrupoContato(mapping, formParam, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="listaAssocGrupoContato.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward gravaAssocGrupoContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("AssocGrupoContatoController:gravaAssocGrupoContato" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		AssocGrupoContatoForm form = (AssocGrupoContatoForm) formParam;
		try {

			this.assocGrupoContatoForm.setMsgError(ConstantesCRM.SVAZIO);
			StringBuffer buffer = new StringBuffer("<salvar><idContato>" + this.assocGrupoContatoForm.getIdContato() + "</idContato><gruposSelecionados>");
			if (form.getaGruposAssociados() != null && form.getaGruposAssociados().length > 0) {
				for (int i = 0; i < form.getaGruposAssociados().length; i++) {
					buffer.append("<idGrupo>" + form.getaGruposAssociados()[i] + "</idGrupo>");
				}
				buffer.append("</gruposSelecionados></salvar>");
				controlArvore.salvaAssocGrupoContato(buffer.toString(), this.assocGrupoContatoForm.getCurrentUser());
			} else {
				this.assocGrupoContatoForm.setMsgError(this.MSG_SEM_CONTATO);
			}

		} catch (Exception e) {
			log.error("AssocGrupoContatoController:gravaAssocGrupoContato" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		this.assocGrupoContatoForm.setMsgError(ConstantesCRM.SSucesso);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return this.listaAssocGrupoContato(mapping, formParam, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="assocGrupoContato.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward listaAssocGrupoContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			log.debug("AssocGrupoContatoController:listaAssocGrupoContato" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
			this.admGrupoContatoContainerVO = controlArvore.getListaAssocGrupoContato(this.assocGrupoContatoForm.getIdContato(), this.assocGrupoContatoForm.getCurrentUser());
			this.assocGrupoContatoForm.setGruposDisponiveis(admGrupoContatoContainerVO.getGruposExistentes().getAdmGrupoVOArray());
			this.assocGrupoContatoForm.setGruposAssociados(admGrupoContatoContainerVO.getGruposAssociados().getAdmGrupoVOArray());

		} catch (Exception e) {
			log.error("AssocGrupoContatoController:listaAssocGrupoContato" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class AssocGrupoContatoForm extends ActionForm {

		private static final long serialVersionUID = 4998071789671083806L;

		private String currentUser;
		private String[] aGruposDisponiveis;
		private String[] aGruposAssociados;
		private AdmGrupoVO[] gruposAssociados;
		private AdmGrupoVO[] gruposDisponiveis;
		private String msgError;
		private String idContato;

		public AssocGrupoContatoForm() {
			aGruposDisponiveis = new String[0];
			aGruposAssociados = new String[0];
			gruposAssociados = new AdmGrupoVO[0];
			gruposDisponiveis = new AdmGrupoVO[0];
			msgError = ConstantesCRM.SVAZIO;
			idContato = ConstantesCRM.SVAZIO;
		}

		public void setIdContato(String idContato) {
			this.idContato = idContato;
		}

		public String getIdContato() {
			return this.idContato;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}

		public void setGruposDisponiveis(AdmGrupoVO[] gruposDisponiveis) {
			this.gruposDisponiveis = gruposDisponiveis;
		}

		public AdmGrupoVO[] getGruposDisponiveis() {
			return this.gruposDisponiveis;
		}

		public void setGruposAssociados(AdmGrupoVO[] gruposAssociados) {
			this.gruposAssociados = gruposAssociados;
		}

		public AdmGrupoVO[] getGruposAssociados() {
			return this.gruposAssociados;
		}

		public void setaGruposAssociados(String[] aGruposAssociados) {
			this.aGruposAssociados = aGruposAssociados;
		}

		public String[] getaGruposAssociados() {
			if (this.aGruposAssociados == null || this.aGruposAssociados.length == 0) {
				this.aGruposAssociados = new String[1];
			}
			return this.aGruposAssociados;
		}

		public void setaGruposDisponiveis(String[] aGruposDisponiveis) {
			this.aGruposDisponiveis = aGruposDisponiveis;
		}

		public String[] getaGruposDisponiveis() {
			if (this.aGruposDisponiveis == null || this.aGruposDisponiveis.length == 0) {
				this.aGruposDisponiveis = new String[1];
			}
			return this.aGruposDisponiveis;
		}

		public void setCurrentUser(String currentUser) {
			this.currentUser = currentUser;
		}

		public String getCurrentUser() {
			return this.currentUser;
		}

	}

	public AssocGrupoContatoForm getAssocGrupoContatoForm() {
		return this.assocGrupoContatoForm;
	}

	public void setAssocGrupoContatoForm(AssocGrupoContatoForm assocGrupoContatoForm) {
		this.assocGrupoContatoForm = assocGrupoContatoForm;
	}

}
