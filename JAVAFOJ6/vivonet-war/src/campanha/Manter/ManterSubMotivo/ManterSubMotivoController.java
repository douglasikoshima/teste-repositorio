package campanha.Manter.ManterSubMotivo;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.campanha.vo.ApoioVODocument.ApoioVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

@SuppressWarnings({"rawtypes"})
public class ManterSubMotivoController extends AbstractAction {

	private static final long serialVersionUID = -5083217266154145119L;

	/*
	 * private final int CAMPANHA = 0; private final int TIPOCAMPANHA = 1; private final int MOTIVO = 2; private final
	 * int SUBMOTIVO = 3;
	 */

	private String user = ConstantesCRM.SVAZIO;

	protected global.Global globalApp = new global.Global();

	@EJB
	private br.com.vivo.fo.ctrls.campanha.manter.CampanhaFacade campanhaFacade;

	// private GrupoCampanhaApoioVO grupoCampanhaApoio = null;
	public ArrayList aSubMotivo = new ArrayList();
	// private static transient Logger log = new Logger("campanha");

	private ManterSubMotivoActionForm manterSubMotivoActionForm = new ManterSubMotivoActionForm();

	public ManterSubMotivoActionForm getManterSubMotivoActionForm() {
		return manterSubMotivoActionForm;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("ManterSubMotivoAction".equals(mapping.getParameter())) {
			return ManterSubMotivoAction(mapping, form, request, response);
		} else if ("alterarSubMotivo".equals(mapping.getParameter())) {
			return alterarSubMotivo(mapping, form, request, response);
		} else if ("incluirSubMotivo".equals(mapping.getParameter())) {
			return incluirSubMotivo(mapping, form, request, response);
		} else if ("excluirSubMotivo".equals(mapping.getParameter())) {
			return excluirSubMotivo(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="ManterSubMotivoAction.do"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		user = ConstantesCRM.getCurrentUser(request.getSession());
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="done" return-action="ManterSubMotivoDone"
	 */
	public ActionForward done(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("done");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ManterSubMotivoCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward ManterSubMotivoAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			ManterSubMotivoActionForm form = (ManterSubMotivoActionForm) formParam;

			form.setListaSubMotivo(manterSubMotivoActionForm.getListaSubMotivo());
			manterSubMotivoActionForm = form;
			form.setIdErro(new Integer(0));

			if ("editar".equals(request.getParameter(ConstantesCRM.SACTION))) {
				int iIndex = 0;

				if (request.getParameter("indice") != null) {
					iIndex = Integer.parseInt(request.getParameter("indice"));
				}

				manterSubMotivoActionForm.setIdSubMotivo(form.getListaSubMotivo()[iIndex].getCodigo());
				manterSubMotivoActionForm.setSgSubMotivo(form.getListaSubMotivo()[iIndex].getSigla());
				manterSubMotivoActionForm.setSgSubMotivoOld(form.getListaSubMotivo()[iIndex].getSigla());
				manterSubMotivoActionForm.setIdAcao(new Integer(1)); // Edição
				form.setIdSubMotivoEncontrado(ConstantesCRM.SZERO);

			} else {
				manterSubMotivoActionForm.setListaSubMotivo(campanhaFacade.getGrupoCampanhaApoio(user, ConstantesCRM.STHREE).getSubGrupoApoioVOArray(0).getApoioVOArray());
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
	 * @jpf:forward name="success" path="ManterSubMotivoCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward alterarSubMotivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ManterSubMotivoActionForm form = (ManterSubMotivoActionForm) formParam;

		form.setListaSubMotivo(manterSubMotivoActionForm.getListaSubMotivo());
		manterSubMotivoActionForm = form;
		form.setIdErro(new Integer(0));

		try {

			RetornoVO retorno = campanhaFacade.selApoioCampanhaSubMotivo(user, ConstantesCRM.SVAZIO, form.getSgSubMotivo(), ConstantesCRM.SVAZIO);

			if (ConstantesCRM.SZERO.equals(retorno.getValor())) {
				campanhaFacade.setApoioCampanhaSubMotivo(user, Integer.parseInt(form.getIdSubMotivo()), "", form.getSgSubMotivo());
				form.clear();
			} else {
				form.setIdSubMotivoEncontrado(retorno.getValor());
				form.setIdAcao(manterSubMotivoActionForm.getIdAcao());
			}

			manterSubMotivoActionForm.setListaSubMotivo(campanhaFacade.getGrupoCampanhaApoio(user, "3").getSubGrupoApoioVOArray(0).getApoioVOArray());
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			// formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ManterSubMotivoCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward incluirSubMotivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ManterSubMotivoActionForm form = (ManterSubMotivoActionForm) formParam;

		form.setListaSubMotivo(manterSubMotivoActionForm.getListaSubMotivo());
		manterSubMotivoActionForm = form;
		form.setIdErro(new Integer(0));
		try {
			RetornoVO retorno = campanhaFacade.selApoioCampanhaSubMotivo(user, ConstantesCRM.SVAZIO, form.getSgSubMotivo(), ConstantesCRM.SVAZIO);

			if (ConstantesCRM.SZERO.equals(retorno.getValor())) {
				campanhaFacade.addApoioCampanhaSubMotivo(user, ConstantesCRM.SVAZIO, form.getSgSubMotivo());

				form.clear();

				manterSubMotivoActionForm.setListaSubMotivo(campanhaFacade.getGrupoCampanhaApoio(user, ConstantesCRM.STHREE).getSubGrupoApoioVOArray(0).getApoioVOArray());
			} else {
				form.setIdSubMotivoEncontrado(retorno.getValor());
				form.setIdAcao(manterSubMotivoActionForm.getIdAcao());
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

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ManterSubMotivoCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward excluirSubMotivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ManterSubMotivoActionForm form = (ManterSubMotivoActionForm) formParam;

		form.setListaSubMotivo(manterSubMotivoActionForm.getListaSubMotivo());
		manterSubMotivoActionForm = form;
		form.setIdErro(new Integer(0));

		try {
			RetornoVO retorno = campanhaFacade.delApoioCampanhaSubMotivo(user, Integer.parseInt(request.getParameter("codigo")));

			if (ConstantesCRM.SZERO.equals(retorno.getValor())) {
				form.setIdErro(new Integer(1)); // Erro
			} else {
				form.clear();

				manterSubMotivoActionForm.setListaSubMotivo(campanhaFacade.getGrupoCampanhaApoio(user, ConstantesCRM.STHREE).getSubGrupoApoioVOArray(0).getApoioVOArray());
			}

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			// formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class ManterSubMotivoActionForm extends ActionForm {

		private static final long serialVersionUID = 2984460746201637962L;
		private String idSubMotivo;
		private String sgSubMotivo;
		private String idSubMotivoEncontrado = ConstantesCRM.SZERO;
		private String sgSubMotivoOld = ConstantesCRM.SVAZIO;
		private Integer idAcao = new Integer(0);
		private Integer idErro = new Integer(0);

		private ApoioVO[] listaSubMotivo = new ApoioVO[0];

		public void setListaSubMotivo(ApoioVO[] lista) {
			listaSubMotivo = lista;
		}

		public ApoioVO[] getListaSubMotivo() {
			return listaSubMotivo;
		}

		public void setSgSubMotivo(String sgSubMotivo) {
			this.sgSubMotivo = sgSubMotivo;
		}

		public String getSgSubMotivo() {
			return this.sgSubMotivo;
		}

		public void setIdSubMotivo(String idSubMotivo) {
			this.idSubMotivo = idSubMotivo;
		}

		public String getIdSubMotivo() {
			return this.idSubMotivo;
		}

		public void setIdSubMotivoEncontrado(String idSubMotivoEncontrado) {
			this.idSubMotivoEncontrado = idSubMotivoEncontrado;
		}

		public String getIdSubMotivoEncontrado() {
			return this.idSubMotivoEncontrado;
		}

		public void setIdAcao(Integer i) {
			this.idAcao = i;
		}

		public Integer getIdAcao() {
			return this.idAcao;
		}

		public void setSgSubMotivoOld(String s) {
			this.sgSubMotivoOld = s;
		}

		public String getSgSubMotivoOld() {
			return this.sgSubMotivoOld;
		}

		public void clear() {
			this.idSubMotivo = "-1";
			this.sgSubMotivo = ConstantesCRM.SVAZIO;
			this.idSubMotivoEncontrado = ConstantesCRM.SZERO;
			this.sgSubMotivoOld = ConstantesCRM.SVAZIO;
			this.idAcao = new Integer(0);
			this.idErro = new Integer(0);
		}

		public void setIdErro(Integer i) {
			this.idErro = i;
		}

		public Integer getIdErro() {
			return this.idErro;
		}
	}
}
