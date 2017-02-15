package admsistemas.admManterCampo.abaValorDominio;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.AdmDominioComboVODocument.AdmDominioComboVO;
import br.com.vivo.fo.admsistemas.vo.AdmDominioVODocument.AdmDominioVO;
import br.com.vivo.fo.admsistemas.vo.AdmDominioValorIncluiVODocument.AdmDominioValorIncluiVO;
import br.com.vivo.fo.admsistemas.vo.AdmDominiosVODocument.AdmDominiosVO;
import br.com.vivo.fo.admsistemas.vo.AdmTabelaDominioCopiaVODocument.AdmTabelaDominioCopiaVO;
import br.com.vivo.fo.admsistemas.vo.AdmTabelaDominioVODocument.AdmTabelaDominioVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoLinhaSimplVODocument.AdmTipoLinhaSimplVO;
import br.com.vivo.fo.admsistemas.vo.AdmUFOperadoraSimplVODocument.AdmUFOperadoraSimplVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.admsistemas.valorDominio.ValorDominioFacade;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ManterValorDominioController extends AbstractAction {

	private static final long serialVersionUID = -8223926712771188724L;

	@EJB
	private ValorDominioFacade controlValorDominio;

	protected global.Global globalApp = new global.Global();

	private ValorDominioForm valorDominioForm = null;

	private final static String MSG_ERROR = "msgError";

	private static transient Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("limpaForm".equals(mapping.getParameter())) {
			return limpaForm(mapping, form, request, response);
		} else if ("pesquisaValorDominio".equals(mapping.getParameter())) {
			return pesquisaValorDominio(mapping, form, request, response);
		} else if ("carregaAlterar".equals(mapping.getParameter())) {
			return carregaAlterar(mapping, form, request, response);
		} else if ("carregaIncluir".equals(mapping.getParameter())) {
			return carregaIncluir(mapping, form, request, response);
		} else if ("removeValorDominio".equals(mapping.getParameter())) {
			return removeValorDominio(mapping, form, request, response);
		} else if ("alteraValorDominio".equals(mapping.getParameter())) {
			return alteraValorDominio(mapping, form, request, response);
		} else if ("incluiValorDominio".equals(mapping.getParameter())) {
			return incluiValorDominio(mapping, form, request, response);
		} else if ("carregaCopia".equals(mapping.getParameter())) {
			return carregaCopia(mapping, form, request, response);
		} else if ("persisteCopia".equals(mapping.getParameter())) {
			return persisteCopia(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisaValorDominio.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("ManterValorDominioController:begin(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		getValorDominioForm().setValorDominioCombo(controlValorDominio.carregaComboValorDominio(ConstantesCRM.getCurrentUser(request.getSession())));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisaValorDominio.jsp"
	 */
	public ActionForward limpaForm(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterValorDominioController:limpaForm(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		getValorDominioForm().setDominioVO(null);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisaValorDominio.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward pesquisaValorDominio(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterValorDominioController:pesquisaValorDominio(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		try {
			ValorDominioForm form = (ValorDominioForm) formParam;
			// Busca a lista de Classificador que atendem o critério de pesquisa.
			getValorDominioForm().setDominiosVO(controlValorDominio.listaValorDominios(form.getDominioVO(), ConstantesCRM.getCurrentUser(request.getSession())));
			getValorDominioForm().setDominioVO(form.getDominioVO());

			// Indica tamanho do Array de retorno.
			getValorDominioForm().setArrayValorDominioLength(String.valueOf(getValorDominioForm().getDominiosVO().getAdmDominioVOArray().length));

		} catch (TuxedoWarningException twe) {
			log.warn("ManterValorDominioController:pesquisaValorDominio" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			request.setAttribute(MSG_ERROR, twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterValorDominioController:pesquisaValorDominio" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
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
	 * @jpf:forward name="success" path="alterarValorDominio.jsp"
	 */
	public ActionForward carregaAlterar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterValorDominioController:carregaAlterar(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		ValorDominioForm form = (ValorDominioForm) formParam;
		getValorDominioForm().setDominioVO(getDominioById(form.getDominioVO().getIdDominio(), getValorDominioForm().getDominiosVO()));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluirDominio.jsp"
	 */
	public ActionForward carregaIncluir(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterValorDominioController:carregaIncluir(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		getValorDominioForm().setDominioVO(null);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisaValorDominio.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removeValorDominio(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterValorDominioController:removeValorDominio(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		try {
			ValorDominioForm form = (ValorDominioForm) formParam;
			// Busca a lista de Classificador que atendem o critério de pesquisa.
			controlValorDominio.removeValorDominio(form.getDominioVO(), ConstantesCRM.getCurrentUser(request.getSession()));

			removeDominioById(form.getDominioVO().getIdDominio(), getValorDominioForm().getDominiosVO());

			// Indica tamanho do Array de retorno.
			if (getValorDominioForm().getDominiosVO().getAdmDominioVOArray().length == 0) {
				getValorDominioForm().setArrayValorDominioLength(ConstantesCRM.SVAZIO);

			} else {
				getValorDominioForm().setArrayValorDominioLength(String.valueOf(getValorDominioForm().getDominiosVO().getAdmDominioVOArray().length));
			}
			request.setAttribute("msgError", ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("ManterValorDominioController:removeValorDominio" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			request.setAttribute(MSG_ERROR, twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterValorDominioController:removeValorDominio" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
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
	 * @jpf:forward name="success" path="pesquisaValorDominio.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward alteraValorDominio(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterValorDominioController:alteraValorDominio(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		try {
			ValorDominioForm form = (ValorDominioForm) formParam;
			form.getDominioVO().getAdmTabelaDominioVO().setIdTabelaDominio(getValorDominioForm().getDominioVO().getAdmTabelaDominioVO().getIdTabelaDominio());

			// Altera registro existente.
			getValorDominioForm().setDominiosVO(controlValorDominio.updateValorDominio(form.getDominioVO(), ConstantesCRM.getCurrentUser(request.getSession())));

			// Indica tamanho do Array de retorno.
			if (getValorDominioForm().getDominiosVO().getAdmDominioVOArray().length == 0) {
				getValorDominioForm().setArrayValorDominioLength(ConstantesCRM.SVAZIO);

			} else {
				getValorDominioForm().setArrayValorDominioLength(String.valueOf(getValorDominioForm().getDominiosVO().getAdmDominioVOArray().length));
			}

			getValorDominioForm().setDominioVO(AdmDominioVO.Factory.newInstance());
			request.setAttribute("msgError", ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("ManterValorDominioController:alteraValorDominio" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			request.setAttribute(MSG_ERROR, twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterValorDominioController:alteraValorDominio" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
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
	 * @jpf:forward name="success" path="pesquisaValorDominio.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward incluiValorDominio(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterValorDominioController:incluiValorDominio(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		try {
			ValorDominioForm form = (ValorDominioForm) formParam;
			form.getDominioValorIncluiVO().setOperadoraArray1Array(form.getOperadoraArrayAssoc());
			form.getDominioValorIncluiVO().setTipoLinhaArray1Array(form.getTipoLinhaArrayAssoc());

			// inseri novo registro.
			getValorDominioForm().setDominiosVO(controlValorDominio.addValorDominio(form.getDominioValorIncluiVO(), ConstantesCRM.getCurrentUser(request.getSession())));
			// Indica tamanho do Array de retorno.
			if (getValorDominioForm().getDominiosVO().getAdmDominioVOArray().length == 0) {
				getValorDominioForm().setArrayValorDominioLength(ConstantesCRM.SVAZIO);

			} else {
				getValorDominioForm().setArrayValorDominioLength(String.valueOf(getValorDominioForm().getDominiosVO().getAdmDominioVOArray().length));
			}

			getValorDominioForm().setDominioVO(AdmDominioVO.Factory.newInstance());
			request.setAttribute("msgError", ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("ManterValorDominioController:incluiValorDominio" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			request.setAttribute(MSG_ERROR, twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterValorDominioController:incluiValorDominio" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
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
	 * @jpf:forward name="success" path="copiaDominio.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward carregaCopia(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("ManterValorDominioController:carregaCopia(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		try {
			// controlValorDominio.carregaCopiaValorDominio(ConstantesCRM.getCurrentUser(request.getSession());
			/*
			 * }catch(TuxedoWarningException twe) { log.warn("ManterValorDominioController:carregaCopia"+"( "+
			 * ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " +
			 * twe.getXmlHeader().getStatusText()); request.setAttribute(MSG_ERROR,twe.getXmlHeader().getStatusText());
			 */
		} catch (Exception e) {
			log.error("ManterValorDominioController:carregaCopia" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
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
	 * @jpf:forward name="success" path="copiaDominio.jsp"
	 */
	public ActionForward persisteCopia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterValorDominioController:persisteCopia(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class ValorDominioForm extends ActionForm {

		private static final long serialVersionUID = -4412882907411334257L;

		private String[] tipoLinhaArrayAssoc;
		private String[] operadoraArrayAssoc;
		private AdmTabelaDominioCopiaVO admCopiaVO;
		private String[] tipoLinhaArray;
		private String[] operadoraArray;
		private AdmDominioValorIncluiVO dominioValorIncluiVO;
		private AdmDominiosVO dominiosVO;
		private AdmDominioVO dominioVO;
		private AdmDominioComboVO valorDominioCombo;
		private String arrayValorDominioLength = ConstantesCRM.SVAZIO;

		public void setArrayValorDominioLength(String arrayValorDominioLength) {
			this.arrayValorDominioLength = arrayValorDominioLength;
		}

		public String getArrayValorDominioLength() {
			return this.arrayValorDominioLength;
		}

		public void setValorDominioCombo(AdmDominioComboVO valorDominioCombo) {
			this.valorDominioCombo = valorDominioCombo;
		}

		public AdmDominioComboVO getValorDominioCombo() {
			if (this.valorDominioCombo == null) {
				this.valorDominioCombo = AdmDominioComboVO.Factory.newInstance();
			}

			return this.valorDominioCombo;
		}

		public void setDominioVO(AdmDominioVO dominioVO) {
			this.dominioVO = dominioVO;
		}

		public AdmDominioVO getDominioVO() {
			if (this.dominioVO == null) {
				this.dominioVO = AdmDominioVO.Factory.newInstance();
			}

			if (this.dominioVO.getAdmTabelaDominioVO() == null) {
				this.dominioVO.setAdmTabelaDominioVO(AdmTabelaDominioVO.Factory.newInstance());
			}

			if (this.dominioVO.getAdmTipoLinhaSimplVO() == null) {
				this.dominioVO.setAdmTipoLinhaSimplVO(AdmTipoLinhaSimplVO.Factory.newInstance());
			}

			if (this.dominioVO.getAdmUFOperadoraSimplVO() == null) {
				this.dominioVO.setAdmUFOperadoraSimplVO(AdmUFOperadoraSimplVO.Factory.newInstance());
			}

			return this.dominioVO;
		}

		public void setDominiosVO(AdmDominiosVO dominiosVO) {
			this.dominiosVO = dominiosVO;
		}

		public AdmDominiosVO getDominiosVO() {
			if (this.dominiosVO == null) {
				this.dominiosVO = AdmDominiosVO.Factory.newInstance();
			}

			return this.dominiosVO;
		}

		public void setDominioValorIncluiVO(AdmDominioValorIncluiVO dominioValorIncluiVO) {
			this.dominioValorIncluiVO = dominioValorIncluiVO;
		}

		public AdmDominioValorIncluiVO getDominioValorIncluiVO() {
			if (this.dominioValorIncluiVO == null) {
				this.dominioValorIncluiVO = AdmDominioValorIncluiVO.Factory.newInstance();
			}

			if (this.dominioValorIncluiVO.getOperadoraArray1Array() == null) {
				this.dominioValorIncluiVO.setOperadoraArray1Array(new String[0]);
			}

			if (this.dominioValorIncluiVO.getTipoLinhaArray1Array() == null) {
				this.dominioValorIncluiVO.setTipoLinhaArray1Array(new String[0]);
			}

			if (this.dominioValorIncluiVO.getNmDominio() == null) {
				this.dominioValorIncluiVO.setNmDominio(ConstantesCRM.SVAZIO);
			}

			if (this.dominioValorIncluiVO.getInDisponibilidade() == null) {
				this.dominioValorIncluiVO.setInDisponibilidade(ConstantesCRM.SVAZIO);
			}

			if (this.dominioValorIncluiVO.getIdDominio() == null) {
				this.dominioValorIncluiVO.setIdDominio(ConstantesCRM.SVAZIO);
			}

			if (this.dominioValorIncluiVO.getIdTabelaDominio() == null) {
				this.dominioValorIncluiVO.setIdTabelaDominio(ConstantesCRM.SVAZIO);
			}

			return this.dominioValorIncluiVO;
		}

		public void setOperadoraArray(String[] operadoraArray) {
			this.operadoraArray = operadoraArray;
		}

		public String[] getOperadoraArray() {
			if (this.operadoraArray == null) {
				this.operadoraArray = new String[0];
			}

			return this.operadoraArray;
		}

		public void setTipoLinhaArray(String[] tipoLinhaArray) {
			this.tipoLinhaArray = tipoLinhaArray;
		}

		public String[] getTipoLinhaArray() {
			if (this.tipoLinhaArray == null) {
				this.tipoLinhaArray = new String[0];
			}

			return this.tipoLinhaArray;
		}

		public void setAdmCopiaVO(AdmTabelaDominioCopiaVO admCopiaVO) {
			this.admCopiaVO = admCopiaVO;
		}

		public AdmTabelaDominioCopiaVO getAdmCopiaVO() {
			if (this.admCopiaVO == null) {
				this.admCopiaVO = AdmTabelaDominioCopiaVO.Factory.newInstance();
				this.admCopiaVO.addNewAdmDominioVO();
				this.admCopiaVO.addNewAdmTabelaComDominioVO();
				this.admCopiaVO.addNewAdmTabelaSemDominioVO();
				this.admCopiaVO.addNewDadosAtuais();
			}

			return this.admCopiaVO;
		}

		public void setOperadoraArrayAssoc(String[] operadoraArrayAssoc) {
			this.operadoraArrayAssoc = operadoraArrayAssoc;
		}

		public String[] getOperadoraArrayAssoc() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.operadoraArrayAssoc == null) {
				this.operadoraArrayAssoc = new String[0];
			}

			return this.operadoraArrayAssoc;
		}

		public void setTipoLinhaArrayAssoc(String[] tipoLinhaArrayAssoc) {
			this.tipoLinhaArrayAssoc = tipoLinhaArrayAssoc;
		}

		public String[] getTipoLinhaArrayAssoc() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.tipoLinhaArrayAssoc == null) {
				this.tipoLinhaArrayAssoc = new String[0];
			}

			return this.tipoLinhaArrayAssoc;
		}
	}

	public void setValorDominioForm(ValorDominioForm form) {
		this.valorDominioForm = form;
	}

	public ValorDominioForm getValorDominioForm() {
		if (this.valorDominioForm == null) {
			valorDominioForm = new ValorDominioForm();
		}
		return this.valorDominioForm;
	}

	private AdmDominioVO getDominioById(String id, AdmDominiosVO array) {
		if (id == null) {
			return null;
		}

		for (int i = 0; i < array.getAdmDominioVOArray().length; i++) {
			if (id.equals(array.getAdmDominioVOArray(i).getIdDominio())) {
				return array.getAdmDominioVOArray(i);
			}
		}
		return null;
	}

	private AdmDominiosVO removeDominioById(String id, AdmDominiosVO array) {
		if (id == null || array == null) {
			return null;
		}

		for (int i = 0; i < array.getAdmDominioVOArray().length; i++) {
			if (id.equals(array.getAdmDominioVOArray(i).getIdDominio())) {
				array.removeAdmDominioVO(i);
				break;
			}
		}
		return array;
	}
}