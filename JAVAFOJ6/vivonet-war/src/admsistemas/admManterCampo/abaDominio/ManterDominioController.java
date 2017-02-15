package admsistemas.admManterCampo.abaDominio;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.AdmTabelaDominioVODocument.AdmTabelaDominioVO;
import br.com.vivo.fo.admsistemas.vo.AdmTabelaDominiosVODocument.AdmTabelaDominiosVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.admsistemas.dominio.DominioFacade;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ManterDominioController extends AbstractAction {

	private static final long serialVersionUID = 9137611387660249578L;

	@EJB
	private DominioFacade controlDominio;

	protected global.Global globalApp = new global.Global();

	private DominioForm dominioForm = null;

	private static transient Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("incluiAlteraDominio".equals(mapping.getParameter())) {
			return incluiAlteraDominio(mapping, form, request, response);
		} else if ("pesquisaDominio".equals(mapping.getParameter())) {
			return pesquisaDominio(mapping, form, request, response);
		} else if ("removeDominio".equals(mapping.getParameter())) {
			return removeDominio(mapping, form, request, response);
		} else if ("carregaAlterar".equals(mapping.getParameter())) {
			return carregaAlterar(mapping, form, request, response);
		} else if ("carregaIncluir".equals(mapping.getParameter())) {
			return carregaIncluir(mapping, form, request, response);
		} else if ("limpaForm".equals(mapping.getParameter())) {
			return limpaForm(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisaDominio.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterDominioController:begin(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisaDominio.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward incluiAlteraDominio(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterDominioController:incluiAlteraDominio(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		DominioForm form = (DominioForm) formParam;
		try {
			// Se existe ID, Altera, se não inseri.
			if (form.getDominioVO().getIdTabelaDominio() != null && !form.getDominioVO().getIdTabelaDominio().equals("")) {
				getDominioForm().setDominiosVO(controlDominio.updateDominio(form.getDominioVO(), ConstantesCRM.getCurrentUser(request.getSession())));
			} else {
				getDominioForm().setDominiosVO(controlDominio.addDominio(form.getDominioVO(), ConstantesCRM.getCurrentUser(request.getSession())));
			}

			// Indica tamanho do Array de retorno.
			getDominioForm().setDominiosVOLength(String.valueOf(getDominioForm().getDominiosVO().getAdmTabelaDominioVOArray().length));

			getDominioForm().setDominioVO(AdmTabelaDominioVO.Factory.newInstance());
			request.setAttribute("msgError", ConstantesCRM.SSucesso);
		} catch (TuxedoWarningException twe) {
			log.warn("ManterDominioController:incluiAlteraDominio" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgError", twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterClassificadorController:incluiAlteraDominio" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget("top.frameApp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisaDominio.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward pesquisaDominio(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterDominioController:pesquisaDominio(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		DominioForm form = (DominioForm) formParam;
		try {
			// Busca a lista de Dominio que atendem o critério de pesquisa.
			getDominioForm().setDominiosVO(controlDominio.listaDominios(form.getDominioVO(), ConstantesCRM.getCurrentUser(request.getSession())));

			// Indica tamanho do Array de retorno.
			getDominioForm().setDominiosVOLength(String.valueOf(getDominioForm().getDominiosVO().getAdmTabelaDominioVOArray().length));

		} catch (TuxedoWarningException twe) {
			log.warn("ManterDominioController:pesquisaDominio" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgError", twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterDominioController:pesquisaDominio" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget("top.frameApp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisaDominio.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removeDominio(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterDominioController:removeDominio(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		DominioForm form = (DominioForm) formParam;
		try {
			controlDominio.removeDominio(form.getDominioVO(), ConstantesCRM.getCurrentUser(request.getSession()));
			removeDominioById(form.getDominioVO().getIdTabelaDominio(), getDominioForm().getDominiosVO());

			// Indica tamanho do Array de retorno.
			if (getDominioForm().getDominiosVO().getAdmTabelaDominioVOArray().length == 0) {
				getDominioForm().setDominiosVOLength(ConstantesCRM.SVAZIO);

			} else {
				getDominioForm().setDominiosVOLength(String.valueOf(getDominioForm().getDominiosVO().getAdmTabelaDominioVOArray().length));
			}
			request.setAttribute("msgError", ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("ManterDominioController:removeDominio" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgError", twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterDominioController:removeDominio" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget("top.frameApp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluirAlterarDominio.jsp"
	 */
	public ActionForward carregaAlterar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterDominioController:carregaAlterar(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		DominioForm form = (DominioForm) formParam;
		getDominioForm().setDominioVO(getDominioById(form.getDominioVO().getIdTabelaDominio(), getDominioForm().getDominiosVO()));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluirAlterarDominio.jsp"
	 */
	public ActionForward carregaIncluir(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterDominioController:carregaIncluir(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		getDominioForm().setDominioVO(AdmTabelaDominioVO.Factory.newInstance());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisaDominio.jsp"
	 */
	public ActionForward limpaForm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterDominioController:limpaForm(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		getDominioForm().setDominioVO(AdmTabelaDominioVO.Factory.newInstance());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class DominioForm extends ActionForm {

		private static final long serialVersionUID = -5986890544864602113L;

		private String dominiosVOLength = ConstantesCRM.SVAZIO;
		private AdmTabelaDominiosVO dominiosVO;
		private AdmTabelaDominioVO dominioVO;

		public void setDominioVO(AdmTabelaDominioVO dominioVO) {
			this.dominioVO = dominioVO;
		}

		public AdmTabelaDominioVO getDominioVO() {
			if (this.dominioVO == null) {
				this.dominioVO = AdmTabelaDominioVO.Factory.newInstance();
			}

			return this.dominioVO;
		}

		public void setDominiosVO(AdmTabelaDominiosVO dominiosVO) {
			this.dominiosVO = dominiosVO;
		}

		public AdmTabelaDominiosVO getDominiosVO() {
			if (this.dominiosVO == null) {
				this.dominiosVO = AdmTabelaDominiosVO.Factory.newInstance();
			}

			return this.dominiosVO;
		}

		public void setDominiosVOLength(String dominiosVOLength) {
			this.dominiosVOLength = dominiosVOLength;
		}

		public String getDominiosVOLength() {
			return this.dominiosVOLength;
		}
	}

	public void setDominioForm(DominioForm form) {
		this.dominioForm = form;
	}

	public DominioForm getDominioForm() {
		if (this.dominioForm == null) {
			dominioForm = new DominioForm();
		}

		return this.dominioForm;
	}

	private AdmTabelaDominioVO getDominioById(String id, AdmTabelaDominiosVO array) {
		if (id == null) {
			return null;
		}

		for (int i = 0; i < array.getAdmTabelaDominioVOArray().length; i++) {
			if (id.equals(array.getAdmTabelaDominioVOArray(i).getIdTabelaDominio())) {
				return array.getAdmTabelaDominioVOArray(i);
			}
		}
		return null;
	}

	private AdmTabelaDominiosVO removeDominioById(String id, AdmTabelaDominiosVO array) {
		if (id == null || array == null) {
			return null;
		}

		for (int i = 0; i < array.getAdmTabelaDominioVOArray().length; i++) {
			if (id.equals(array.getAdmTabelaDominioVOArray(i).getIdTabelaDominio())) {
				array.removeAdmTabelaDominioVO(i);
				break;
			}
		}
		return array;
	}
}