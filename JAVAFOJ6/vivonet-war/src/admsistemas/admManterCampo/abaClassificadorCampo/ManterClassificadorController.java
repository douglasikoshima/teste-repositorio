package admsistemas.admManterCampo.abaClassificadorCampo;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.AdmClassificadorCampoVODocument.AdmClassificadorCampoVO;
import br.com.vivo.fo.admsistemas.vo.AdmClassificadorCamposVODocument.AdmClassificadorCamposVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.admsistemas.classificador.ClassificadorFacade;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ManterClassificadorController extends AbstractAction {

	private static final long serialVersionUID = -4285021804915551025L;

	@EJB
	private ClassificadorFacade controlCladdificador;

	protected global.Global globalApp = new global.Global();

	private FormClassificador formClassificador = null;

	private static transient Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("limpaForm".equals(mapping.getParameter())) {
			return limpaForm(mapping, form, request, response);
		} else if ("carregaIncluir".equals(mapping.getParameter())) {
			return carregaIncluir(mapping, form, request, response);
		} else if ("carregaAlterar".equals(mapping.getParameter())) {
			return carregaAlterar(mapping, form, request, response);
		} else if ("pesquisaClassificador".equals(mapping.getParameter())) {
			return pesquisaClassificador(mapping, form, request, response);
		} else if ("removeClassificador".equals(mapping.getParameter())) {
			return removeClassificador(mapping, form, request, response);
		} else if ("incluiAlteraClassificador".equals(mapping.getParameter())) {
			return incluiAlteraClassificador(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisaClassificador.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterClassificadorController:begin(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisaClassificador.jsp"
	 */
	public ActionForward limpaForm(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterClassificadorController:limpaForm(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		getFormClassificador().setAdmClassificardorVO(AdmClassificadorCampoVO.Factory.newInstance());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluirAlterarClassificador.jsp"
	 */
	public ActionForward carregaIncluir(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterClassificadorController:carregaIncluir(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		getFormClassificador().setAdmClassificardorVO(AdmClassificadorCampoVO.Factory.newInstance());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluirAlterarClassificador.jsp"
	 */
	public ActionForward carregaAlterar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterClassificadorController:carregaAlterar(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		FormClassificador form = (FormClassificador) formParam;

		getFormClassificador().setAdmClassificardorVO(getAdmClassificadorById(form.getAdmClassificardorVO().getIdClassificadorCampo(), getFormClassificador().getAdmClassificadorVOArray()));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisaClassificador.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward pesquisaClassificador(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterClassificadorController:pesquisaClassificador(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		try {
			FormClassificador form = (FormClassificador) formParam;
			// Busca a lista de Classificador que atendem o critério de pesquisa.
			getFormClassificador().setAdmClassificadorVOArray(controlCladdificador.listaClassificador(form.getAdmClassificardorVO(), ConstantesCRM.getCurrentUser(request.getSession())));

			// Indica tamanho do Array de retorno.
			getFormClassificador().setArrayClassificadorLength(String.valueOf(getFormClassificador().getAdmClassificadorVOArray().getAdmClassificadorCampoVOArray().length));

		} catch (TuxedoWarningException twe) {
			log.warn("ManterClassificadorController:pesquisaClassificador" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());

			request.setAttribute("msgError", twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterClassificadorController:pesquisaClassificador" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
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
	 * @jpf:forward name="success" path="pesquisaClassificador.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removeClassificador(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterClassificadorController:removeClassificador(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		try {
			FormClassificador form = (FormClassificador) formParam;
			// Busca a lista de Classificador que atendem o critério de pesquisa.
			controlCladdificador.removeClassificador(form.getAdmClassificardorVO(), ConstantesCRM.getCurrentUser(request.getSession()));

			removeAdmClassificadorById(form.getAdmClassificardorVO().getIdClassificadorCampo(), getFormClassificador().getAdmClassificadorVOArray());

			// Indica tamanho do Array de retorno.
			if (getFormClassificador().getAdmClassificadorVOArray().getAdmClassificadorCampoVOArray().length == 0) {
				getFormClassificador().setArrayClassificadorLength(ConstantesCRM.SVAZIO);
			} else {
				getFormClassificador().setArrayClassificadorLength(String.valueOf(getFormClassificador().getAdmClassificadorVOArray().getAdmClassificadorCampoVOArray().length));
			}
			request.setAttribute("msgError", ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("ManterClassificadorController:removeClassificador" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());

			request.setAttribute("msgError", twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("ManterClassificadorController:removeClassificador" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
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
	 * @jpf:forward name="success" path="pesquisaClassificador.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward incluiAlteraClassificador(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterClassificadorController:incluiAlteraClassificador(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		try {
			FormClassificador form = (FormClassificador) formParam;
			// Se existe ID, Altera, se não inseri.
			if (form.getAdmClassificardorVO().getIdClassificadorCampo() != null && !form.getAdmClassificardorVO().getIdClassificadorCampo().equals("")) {
				getFormClassificador().setAdmClassificadorVOArray(controlCladdificador.updateClassificador(form.getAdmClassificardorVO(), ConstantesCRM.getCurrentUser(request.getSession())));
			} else {
				getFormClassificador().setAdmClassificadorVOArray(controlCladdificador.addClassificador(form.getAdmClassificardorVO(), ConstantesCRM.getCurrentUser(request.getSession())));
			}

			// Indica tamanho do Array de retorno.
			getFormClassificador().setArrayClassificadorLength(String.valueOf(getFormClassificador().getAdmClassificadorVOArray().getAdmClassificadorCampoVOArray().length));

			getFormClassificador().setAdmClassificardorVO(AdmClassificadorCampoVO.Factory.newInstance());
			request.setAttribute("msgError", ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("ManterClassificadorController:incluiAlteraClassificador" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());

			request.setAttribute("msgError", twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("ManterClassificadorController:incluiAlteraClassificador" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget("top.frameApp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class FormClassificador extends ActionForm {

		private static final long serialVersionUID = 2904944620723335598L;

		private String arrayClassificadorLength;
		private AdmClassificadorCampoVO admClassificardorVO;
		private AdmClassificadorCamposVO admClassificadorVOArray;

		public void setAdmClassificadorVOArray(AdmClassificadorCamposVO admClassificadorVOArray) {
			this.admClassificadorVOArray = admClassificadorVOArray;
		}

		public AdmClassificadorCamposVO getAdmClassificadorVOArray() {
			if (this.admClassificadorVOArray == null) {
				this.admClassificadorVOArray = AdmClassificadorCamposVO.Factory.newInstance();
			}
			return this.admClassificadorVOArray;
		}

		public void setAdmClassificardorVO(AdmClassificadorCampoVO admClassificardorVO) {
			this.admClassificardorVO = admClassificardorVO;
		}

		public AdmClassificadorCampoVO getAdmClassificardorVO() {
			if (this.admClassificardorVO == null) {
				this.admClassificardorVO = AdmClassificadorCampoVO.Factory.newInstance();
			}
			return this.admClassificardorVO;
		}

		public void setArrayClassificadorLength(String arrayClassificadorLength) {
			this.arrayClassificadorLength = arrayClassificadorLength;
		}

		public String getArrayClassificadorLength() {
			return this.arrayClassificadorLength;
		}
	}

	public void setFormClassificador(FormClassificador form) {
		this.formClassificador = form;
	}

	public FormClassificador getFormClassificador() {
		if (this.formClassificador == null) {
			formClassificador = new FormClassificador();
		}
		return this.formClassificador;
	}

	private AdmClassificadorCampoVO getAdmClassificadorById(String id, AdmClassificadorCamposVO array) {
		if (id == null) {
			return null;
		}

		for (int i = 0; i < array.getAdmClassificadorCampoVOArray().length; i++) {
			if (id.equals(array.getAdmClassificadorCampoVOArray(i).getIdClassificadorCampo())) {
				return array.getAdmClassificadorCampoVOArray(i);
			}
		}
		return null;
	}

	private AdmClassificadorCamposVO removeAdmClassificadorById(String id, AdmClassificadorCamposVO array) {
		if (id == null || array == null) {
			return null;
		}

		for (int i = 0; i < array.getAdmClassificadorCampoVOArray().length; i++) {
			if (id.equals(array.getAdmClassificadorCampoVOArray(i).getIdClassificadorCampo())) {
				array.removeAdmClassificadorCampoVO(i);
				break;
			}
		}
		return array;
	}
}