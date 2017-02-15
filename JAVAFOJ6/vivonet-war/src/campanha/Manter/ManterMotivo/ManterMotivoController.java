package campanha.Manter.ManterMotivo;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.campanha.vo.ApoioVODocument.ApoioVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ManterMotivoController extends AbstractAction {

	/*
	 * private final int CAMPANHA = 0; private final int TIPOCAMPANHA = 1; private final int MOTIVO = 2; private final
	 * int SUBMOTIVO = 3;
	 */

	private static final long serialVersionUID = -5568982704634401816L;

	private String user = ConstantesCRM.SVAZIO;

	protected global.Global globalApp = new global.Global();

	@EJB
	private br.com.vivo.fo.ctrls.campanha.manter.CampanhaFacade campanhaFacade;

	@SuppressWarnings("unused")
	private static transient Logger log = new Logger("campanha");

	private ManterMotivoActionForm manterMotivoActionForm = new ManterMotivoActionForm();

	public ManterMotivoActionForm getManterMotivoActionForm() {
		return manterMotivoActionForm;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("ManterMotivoAction".equals(mapping.getParameter())) {
			return ManterMotivoAction(mapping, form, request, response);
		} else if ("excluirMotivo".equals(mapping.getParameter())) {
			return excluirMotivo(mapping, form, request, response);
		} else if ("editarMotivo".equals(mapping.getParameter())) {
			return editarMotivo(mapping, form, request, response);
		} else if ("incluirMotivo".equals(mapping.getParameter())) {
			return incluirMotivo(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="ManterMotivoAction.do"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		user = ConstantesCRM.getCurrentUser(request.getSession());
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="done" return-action="ManterMotivoDone"
	 */
	public ActionForward done(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("done");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ManterMotivoCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward ManterMotivoAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ManterMotivoActionForm form = (ManterMotivoActionForm) formParam;

		Integer aderiu;
		try {
			form.setListaMotivo(manterMotivoActionForm.getListaMotivo());
			manterMotivoActionForm = form;
			form.setIdErro(ConstantesCRM.SZERO);
			if ("editar".equals(request.getParameter(ConstantesCRM.SACTION))) {
				form.setListaMotivo(manterMotivoActionForm.getListaMotivo());
				manterMotivoActionForm = form;
				form.setIdErro(ConstantesCRM.SZERO);
				// if((request.getParameter(ConstantesCRM.SACTION) != null)&&(request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("EDITAR")))
				int iIndex = 0;

				if (request.getParameter("indice") != null) {
					iIndex = Integer.parseInt(request.getParameter("indice"));
				}

				manterMotivoActionForm.setIdMotivo(form.getListaMotivo()[iIndex].getCodigo());
				manterMotivoActionForm.setSgMotivo(form.getListaMotivo()[iIndex].getSigla());
				manterMotivoActionForm.setSgMotivoOld(form.getListaMotivo()[iIndex].getSigla());

				aderiu = new Integer(form.getListaMotivo()[iIndex].getInAderiu());
				manterMotivoActionForm.setIdAderiu(aderiu);

				manterMotivoActionForm.setIdAcao(ConstantesCRM.SONE); // Edição
				form.setIdMotivoEncontrado(ConstantesCRM.SZERO);
			} else {
				manterMotivoActionForm.setListaMotivo(campanhaFacade.getGrupoCampanhaApoio(user, ConstantesCRM.STWO).getSubGrupoApoioVOArray(0).getApoioVOArray());
			}
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");

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
	 * @jpf:forward name="success" path="ManterMotivoCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward excluirMotivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ManterMotivoActionForm form = (ManterMotivoActionForm) formParam;

		form.setListaMotivo(manterMotivoActionForm.getListaMotivo());
		manterMotivoActionForm = form;
		form.setIdErro(ConstantesCRM.SZERO);
		try {
			RetornoVO retorno = campanhaFacade.delApoioCampanhaMotivo(user, Integer.parseInt(request.getParameter("codigo")));
			if (ConstantesCRM.SZERO.equals(retorno.getValor())) {
				form.setIdErro(ConstantesCRM.SONE); // Erro
			} else {
				form.clear();
				manterMotivoActionForm.setListaMotivo(campanhaFacade.getGrupoCampanhaApoio(user, ConstantesCRM.STWO).getSubGrupoApoioVOArray(0).getApoioVOArray());
			}
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/*
	 * Avalia o motivo alterado, frente a lista existente.
	 */
	private boolean validaListaMotivo(ManterMotivoActionForm form) {
		boolean retorno = false;

		for (int i = 0; i < form.getListaMotivo().length; i++) {
			if (form.getListaMotivo()[i].getDescricao().equalsIgnoreCase(form.getSgMotivo())) {
				retorno = true;
				break;
			}
		}

		return retorno;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ManterMotivoCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward editarMotivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ManterMotivoActionForm form = (ManterMotivoActionForm) formParam;

		boolean inalteradoAderiu = manterMotivoActionForm.getIdAderiu().toString().equals(form.getIdAderiu().toString());
		form.setListaMotivo(manterMotivoActionForm.getListaMotivo());
		manterMotivoActionForm = form;
		form.setIdErro(ConstantesCRM.SZERO);
		try {
			if (inalteradoAderiu && validaListaMotivo(manterMotivoActionForm)) {
				request.setAttribute("msgError", "Motivo já cadastrado.");
			} else {
				campanhaFacade.setApoioCampanhaMotivo(user, Integer.parseInt(form.getIdMotivo()), ConstantesCRM.SVAZIO, form.getSgMotivo(), form.getIdAderiu().toString());
			}
			form.clear();
			manterMotivoActionForm.setListaMotivo(campanhaFacade.getGrupoCampanhaApoio(user, ConstantesCRM.STWO).getSubGrupoApoioVOArray(0).getApoioVOArray());
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ManterMotivoCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward incluirMotivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ManterMotivoActionForm form = (ManterMotivoActionForm) formParam;

		form.setListaMotivo(manterMotivoActionForm.getListaMotivo());
		manterMotivoActionForm = form;
		form.setIdErro(ConstantesCRM.SZERO);
		try {
			if (validaListaMotivo(manterMotivoActionForm)) {
				request.setAttribute("msgError", "Motivo já cadastrado.");
			} else {
				campanhaFacade.addApoioCampanhaMotivo(user, ConstantesCRM.SVAZIO, form.getSgMotivo(), form.getIdAderiu().toString());
			}
			form.clear();
			manterMotivoActionForm.setListaMotivo(campanhaFacade.getGrupoCampanhaApoio(user, ConstantesCRM.STWO).getSubGrupoApoioVOArray(0).getApoioVOArray());
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class ManterMotivoActionForm extends ActionForm {

		private static final long serialVersionUID = -5619524465181360007L;

		private String idMotivo;
		private String sgMotivo;
		private String idMotivoEncontrado;
		private String sgMotivoOld;
		private String idAcao = ConstantesCRM.SZERO;
		private String idErro = ConstantesCRM.SZERO;
		private Integer idAderiu = new Integer(1);

		private ApoioVO[] listaMotivo = new ApoioVO[0];

		public void setListaMotivo(ApoioVO[] lista) {
			listaMotivo = lista;
		}

		public ApoioVO[] getListaMotivo() {
			return listaMotivo;
		}

		public void setIdAderiu(Integer aderiu) {
			this.idAderiu = aderiu;
		}

		public Integer getIdAderiu() {
			return this.idAderiu;
		}

		public void setSgMotivo(String sgMotivo) {
			this.sgMotivo = sgMotivo;
		}

		public String getSgMotivo() {
			return this.sgMotivo;
		}

		public void setSgMotivoOld(String s) {
			this.sgMotivoOld = s;
		}

		public String getSgMotivoOld() {
			return this.sgMotivoOld;
		}

		public void setIdMotivo(String idMotivo) {
			this.idMotivo = idMotivo;
		}

		public String getIdMotivo() {
			return this.idMotivo;
		}

		public void setIdMotivoEncontrado(String i) {
			this.idMotivoEncontrado = i;
		}

		public String getIdMotivoEncontrado() {
			return this.idMotivoEncontrado;
		}

		public void setIdAcao(String i) {
			this.idAcao = i;
		}

		public String getIdAcao() {
			return this.idAcao;
		}

		public void clear() {
			this.idMotivo = "-1";
			this.sgMotivo = ConstantesCRM.SVAZIO;
			this.idMotivoEncontrado = ConstantesCRM.SZERO;
			this.sgMotivoOld = ConstantesCRM.SVAZIO;
			this.idAcao = ConstantesCRM.SZERO;
			this.idErro = ConstantesCRM.SZERO;
			this.idAderiu = new Integer(1);
		}

		public void setIdErro(String i) {
			this.idErro = i;
		}

		public String getIdErro() {
			return this.idErro;
		}

	}
}