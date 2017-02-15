package campanha.Manter.ManterCampanha;

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

public class ManterCampanhaController extends AbstractAction {

	/*
	 * private final int CAMPANHA = 0; private final int TIPOCAMPANHA = 1; private final int MOTIVO = 2; private final
	 * int SUBMOTIVO = 3;
	 */

	private static final long serialVersionUID = 8052955326249038448L;

	@EJB
	private br.com.vivo.fo.ctrls.campanha.manter.CampanhaFacade campanhaFacade;

	private static transient Logger log = new Logger("campanha");

	private String user = ConstantesCRM.SVAZIO;

	private ManterCampanhaActionForm manterCampanhaActionForm = new ManterCampanhaActionForm();

	public ManterCampanhaActionForm getManterCampanhaActionForm() {
		return manterCampanhaActionForm;
	}

	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("editarCampanha".equals(mapping.getParameter())) {
			return editarCampanha(mapping, form, request, response);
		} else if ("incluirCampanha".equals(mapping.getParameter())) {
			return incluirCampanha(mapping, form, request, response);
		} else if ("excluirCampanha".equals(mapping.getParameter())) {
			return excluirCampanha(mapping, form, request, response);
		} else if ("ManterCampanhaAction".equals(mapping.getParameter())) {
			return ManterCampanhaAction(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="ManterCampanhaAction.do"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("btMostra", "INCLUIR");
		user = ConstantesCRM.getCurrentUser(request.getSession());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="done" return-action="ManterCampanhaDone"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward done(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("done");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ManterCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward editarCampanha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ManterCampanhaActionForm form = (ManterCampanhaActionForm) formParam;
			form.setListaCampanha(manterCampanhaActionForm.getListaCampanha());
			manterCampanhaActionForm = form;
			form.setIdErro(ConstantesCRM.SZERO);
			RetornoVO retorno = campanhaFacade.selApoioCampanha(user, form.getDsCampanha(), form.getSgCampanha(), form.getIdCampanha());
			form.setIdCampanhaEncontrada(retorno.getValor());

			if (Integer.parseInt(form.getIdCampanhaEncontrada()) <= 0) {
				campanhaFacade.setApoioCampanha(user, Integer.parseInt(form.getIdCampanha()), form.getDsCampanha(), form.getSgCampanha());
				form.clear();
				manterCampanhaActionForm.setListaCampanha(campanhaFacade.getGrupoCampanhaApoio(user, ConstantesCRM.SONE).getSubGrupoApoioVOArray(0).getApoioVOArray());
			} else {
				form.setIdAcao(manterCampanhaActionForm.getIdAcao());
			}

		} catch (Exception e) {
			log.debug("ManterCampanhaController:editarCampanha " + e.getMessage());
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
	 * @jpf:forward name="success" path="ManterCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward incluirCampanha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ManterCampanhaActionForm form = (ManterCampanhaActionForm) formParam;
			form.setListaCampanha(manterCampanhaActionForm.getListaCampanha());
			manterCampanhaActionForm = form;
			form.setIdErro(ConstantesCRM.SZERO);
			RetornoVO retorno = campanhaFacade.selApoioCampanha(user, form.getDsCampanha(), form.getSgCampanha(), form.getIdCampanha());
			form.setIdCampanhaEncontrada(retorno.getValor());

			if (Integer.parseInt(form.getIdCampanhaEncontrada()) <= 0) {
				campanhaFacade.addApoioCampanha(user, form.getDsCampanha(), form.getSgCampanha());
				form.clear();
				manterCampanhaActionForm.setListaCampanha(campanhaFacade.getGrupoCampanhaApoio(user, ConstantesCRM.SONE).getSubGrupoApoioVOArray(0).getApoioVOArray());
			} else {
				form.setIdAcao(manterCampanhaActionForm.getIdAcao());
			}

		} catch (Exception e) {
			log.debug("ManterCampanhaController:incluirCampanha " + e.getMessage());
			// FormError formError = globalApp.buildFormError(e,
			// "/FrontOfficeWeb/campanha/Manter/ManterCampanha/begin.do");
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
	 * @jpf:forward name="success" path="ManterCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward excluirCampanha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ManterCampanhaActionForm form = (ManterCampanhaActionForm) formParam;

			form.setListaCampanha(manterCampanhaActionForm.getListaCampanha());
			manterCampanhaActionForm = form;
			form.setIdErro(ConstantesCRM.SZERO);

			RetornoVO retorno = campanhaFacade.delApoioCampanha(user, Integer.parseInt(request.getParameter("codigo")));
			// int idApagados = 0;
			// idApagados = Integer.parseInt(retorno.getValor());
			if (ConstantesCRM.SZERO.equals(retorno.getValor())) {
				form.setIdErro(ConstantesCRM.SONE); // Erro
			} else {
				form.clear();
				manterCampanhaActionForm.setListaCampanha(campanhaFacade.getGrupoCampanhaApoio(user, "1").getSubGrupoApoioVOArray(0).getApoioVOArray());
			}
		} catch (Exception e) {
			log.debug("ManterCampanhaController:excluirCampanha " + e.getMessage());
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
	 * @jpf:forward name="success" path="ManterCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward ManterCampanhaAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterCampanhaActionForm form = (ManterCampanhaActionForm) formParam;
		// Monta o log da operação se possível
		// if( log.isDebugEnabled() )
		log.debug("ManterCampanhaController:ManterCampanhaAction(" + form + ")");
		try {
			form.setListaCampanha(manterCampanhaActionForm.getListaCampanha());
			manterCampanhaActionForm = form;
			form.setIdErro(ConstantesCRM.SZERO);
			if ("editar".equals(request.getParameter(ConstantesCRM.SACTION))) {
				int iIndex = 0;
				if (request.getParameter("indice") != null) {
					iIndex = Integer.parseInt(request.getParameter("indice"));
				}

				manterCampanhaActionForm.setIdCampanha(form.getListaCampanha()[iIndex].getCodigo());
				manterCampanhaActionForm.setSgCampanha(form.getListaCampanha()[iIndex].getSigla());
				manterCampanhaActionForm.setDsCampanha(form.getListaCampanha()[iIndex].getDescricao());
				manterCampanhaActionForm.setSgCampanhaOld(form.getListaCampanha()[iIndex].getSigla());
				manterCampanhaActionForm.setDsCampanhaOld(form.getListaCampanha()[iIndex].getDescricao());

				form.setIdAcao(ConstantesCRM.SONE); // Edicao
				form.setIdCampanhaEncontrada(ConstantesCRM.SZERO);
			} else {
				manterCampanhaActionForm.setListaCampanha(campanhaFacade.getGrupoCampanhaApoio(user, ConstantesCRM.SONE).getSubGrupoApoioVOArray(0).getApoioVOArray());
			}

		} catch (Exception e) {
			log.debug("ManterCampanhaController:ManterCampanhaAction " + e.getMessage());
			// FormError formError = globalApp.buildFormError(e,
			// "/FrontOfficeWeb/campanha/Manter/ManterCampanha/begin.do");
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class ManterCampanhaActionForm extends ActionForm {

		private static final long serialVersionUID = -3963978732908431750L;

		private String dsCampanha;
		private String sgCampanha;
		private String idCampanha;
		private String idAcao = ConstantesCRM.SZERO;
		private String idErro = ConstantesCRM.SZERO;
		private String sgCampanhaOld;
		private String dsCampanhaOld;

		private String idCampanhaEncontrada;

		private ApoioVO[] listaCampanha = new ApoioVO[0];

		public void setListaCampanha(ApoioVO[] lista) {
			listaCampanha = lista;
		}

		public ApoioVO[] getListaCampanha() {
			return listaCampanha;
		}

		public void setIdCampanha(String idCampanha) {
			this.idCampanha = idCampanha;
		}

		public String getIdCampanha() {
			return this.idCampanha;
		}

		public void setSgCampanha(String sCampanha) {
			this.sgCampanha = sCampanha;
		}

		public String getSgCampanha() {
			return this.sgCampanha;
		}

		public void setDsCampanha(String dsCampanha) {
			this.dsCampanha = dsCampanha;
		}

		public String getDsCampanha() {
			return this.dsCampanha;
		}

		public void setSgCampanhaOld(String s) {
			this.sgCampanhaOld = s;
		}

		public String getSgCampanhaOld() {
			return this.sgCampanhaOld;
		}

		public void setDsCampanhaOld(String s) {
			this.dsCampanhaOld = s;
		}

		public String getDsCampanhaOld() {
			return this.dsCampanhaOld;
		}

		public void clear() {
			this.dsCampanha = ConstantesCRM.SVAZIO;
			this.sgCampanha = ConstantesCRM.SVAZIO;
			this.idCampanha = "-1";
			this.idCampanhaEncontrada = ConstantesCRM.SZERO;
			this.sgCampanhaOld = ConstantesCRM.SVAZIO;
			this.dsCampanhaOld = ConstantesCRM.SVAZIO;
			this.idAcao = ConstantesCRM.SZERO;
			this.idErro = ConstantesCRM.SZERO;
		}

		public void setIdCampanhaEncontrada(String i) {
			this.idCampanhaEncontrada = i;
		}

		public String getIdCampanhaEncontrada() {
			return this.idCampanhaEncontrada;
		}

		public void setIdAcao(String i) {
			this.idAcao = i;
		}

		public String getIdAcao() {
			return this.idAcao;
		}

		public void setIdErro(String i) {
			this.idErro = i;
		}

		public String getIdErro() {
			return this.idErro;
		}

	}
}
