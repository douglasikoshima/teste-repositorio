package admsistemas.admManterCampo.abaCampoDinamico;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.AdmCampoCombosVODocument.AdmCampoCombosVO;
import br.com.vivo.fo.admsistemas.vo.AdmCampoContatoVODocument.AdmCampoContatoVO;
import br.com.vivo.fo.admsistemas.vo.AdmCampoDominioVODocument.AdmCampoDominioVO;
import br.com.vivo.fo.admsistemas.vo.AdmCamposContatoVODocument.AdmCamposContatoVO;
import br.com.vivo.fo.admsistemas.vo.AdmClassificadorCampoVODocument.AdmClassificadorCampoVO;
import br.com.vivo.fo.admsistemas.vo.AdmDominioComboVODocument.AdmDominioComboVO;
import br.com.vivo.fo.admsistemas.vo.AdmLayoutApresentacaoCampoVODocument.AdmLayoutApresentacaoCampoVO;
import br.com.vivo.fo.admsistemas.vo.AdmMascaraApresentacaoVODocument.AdmMascaraApresentacaoVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoDadoCampoVODocument.AdmTipoDadoCampoVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.admsistemas.campoDinamico.CampoDinamicoFacade;
import br.com.vivo.fo.ctrls.admsistemas.valorDominio.ValorDominioFacade;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ManterCampoDinamicoController extends AbstractAction {

	private static final long serialVersionUID = -1520251276409711953L;

	@EJB
	private CampoDinamicoFacade controlCampo;

	@EJB
	private ValorDominioFacade controlValorDominio;

	protected global.Global globalApp = new global.Global();

	private CampoDinamicoForm campoDinamicoForm = null;

	private static transient Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("pesquisaCampo".equals(mapping.getParameter())) {
			return pesquisaCampo(mapping, form, request, response);
		} else if ("incluiAlteraCampo".equals(mapping.getParameter())) {
			return incluiAlteraCampo(mapping, form, request, response);
		} else if ("limpaForm".equals(mapping.getParameter())) {
			return limpaForm(mapping, form, request, response);
		} else if ("carregaIncluir".equals(mapping.getParameter())) {
			return carregaIncluir(mapping, form, request, response);
		} else if ("carregaAlterar".equals(mapping.getParameter())) {
			return carregaAlterar(mapping, form, request, response);
		} else if ("removeCampo".equals(mapping.getParameter())) {
			return removeCampo(mapping, form, request, response);
		} else if ("persisteDadosParam".equals(mapping.getParameter())) {
			return persisteDadosParam(mapping, form, request, response);
		} else if ("carregaEdicaoAssoc".equals(mapping.getParameter())) {
			return carregaEdicaoAssoc(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisaCampoDinamico.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("ManterCampoDinamicoController:begin(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		CampoDinamicoForm form = (CampoDinamicoForm) formParam;

		getCampoDinamicoForm().setCampoComboVO(controlCampo.carregaCampoCombo(ConstantesCRM.getCurrentUser(request.getSession())));

		getCampoDinamicoForm().setCampoVO(form.getCampoVO());
		getCampoDinamicoForm().setCampoVOArrayLength(form.getCampoVOArrayLength());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisaCampoDinamico.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward pesquisaCampo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterCampoDinamicoController:pesquisaCampo(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		CampoDinamicoForm form = (CampoDinamicoForm) formParam;
		try {
			// Busca a lista de Classificador que atendem o critério de pesquisa.
			getCampoDinamicoForm().setCamposVO(controlCampo.listaCampos(form.getCampoVO(), ConstantesCRM.getCurrentUser(request.getSession())));
			getCampoDinamicoForm().setCampoVO(form.getCampoVO());

			// Indica tamanho do Array de retorno.
			getCampoDinamicoForm().setCampoVOArrayLength(String.valueOf(getCampoDinamicoForm().getCamposVO().getAdmCampoContatoVOArray().length));

		} catch (TuxedoWarningException twe) {
			log.warn("ManterCampoDinamicoController:pesquisaCampo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgError", twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("ManterCampoDinamicoController:pesquisaCampo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
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
	 * @jpf:forward name="success" path="pesquisaCampoDinamico.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward incluiAlteraCampo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterCampoDinamicoController:incluiAlteraCampo(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		CampoDinamicoForm form = (CampoDinamicoForm) formParam;
		try {
			// Se existe ID, Altera, se não inseri.
			if (form.getCampoVO().getIdCampo() != null && !form.getCampoVO().getIdCampo().equals("")) {
				getCampoDinamicoForm().setCamposVO(controlCampo.updateCampo(form.getCampoVO(), ConstantesCRM.getCurrentUser(request.getSession())));
			} else {
				getCampoDinamicoForm().setCamposVO(controlCampo.addCampo(form.getCampoVO(), ConstantesCRM.getCurrentUser(request.getSession())));
			}

			// Indica tamanho do Array de retorno.
			getCampoDinamicoForm().setCampoVOArrayLength(String.valueOf(getCampoDinamicoForm().getCamposVO().getAdmCampoContatoVOArray().length));

			getCampoDinamicoForm().setCampoVO(null);
			request.setAttribute("msgError", ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("ManterCampoDinamicoController:incluiAlteraCampo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgError", twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("ManterCampoDinamicoController:incluiAlteraCampo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
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
	 * @jpf:forward name="success" path="pesquisaCampoDinamico.jsp"
	 */
	public ActionForward limpaForm(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterCampoDinamicoController:limpaForm(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		getCampoDinamicoForm().setCampoVO(null);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluirAlterarCampoDinamico.jsp"
	 */
	public ActionForward carregaIncluir(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterCampoDinamicoController:carregaIncluir(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		getCampoDinamicoForm().setCampoVO(null);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluirAlterarCampoDinamico.jsp"
	 */
	public ActionForward carregaAlterar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterCampoDinamicoController:carregaAlterar(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		CampoDinamicoForm form = (CampoDinamicoForm) formParam;
		getCampoDinamicoForm().setCampoVO(getAdmCampoById(form.getCampoVO().getIdCampo(), getCampoDinamicoForm().getCamposVO()));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisaCampoDinamico.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removeCampo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterCampoDinamicoController:removeCampo(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		CampoDinamicoForm form = (CampoDinamicoForm) formParam;
		try {
			// Busca a lista de Classificador que atendem o critério de pesquisa.
			controlCampo.removeCampo(form.getCampoVO(), ConstantesCRM.getCurrentUser(request.getSession()));

			removeAdmCampoById(form.getCampoVO().getIdCampo(), getCampoDinamicoForm().getCamposVO());

			// Indica tamanho do Array de retorno.
			if (getCampoDinamicoForm().getCamposVO().getAdmCampoContatoVOArray().length == 0) {
				getCampoDinamicoForm().setCampoVOArrayLength(ConstantesCRM.SVAZIO);

			} else {
				getCampoDinamicoForm().setCampoVOArrayLength(String.valueOf(getCampoDinamicoForm().getCamposVO().getAdmCampoContatoVOArray().length));
			}

			request.setAttribute("msgError", ConstantesCRM.SSucesso);
		} catch (TuxedoWarningException twe) {
			log.warn("ManterCampoDinamicoController:removeCampo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgError", twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("ManterCampoDinamicoController:removeCampo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
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
	 * @jpf:forward name="success" path="pesquisaCampoDinamico.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward persisteDadosParam(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("ManterCampoDinamicoController:persisteDadosParam(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		CampoDinamicoForm form = (CampoDinamicoForm) formParam;
		try {
			getCampoDinamicoForm().setCamposVO(controlValorDominio.associaDominioCampo(form.getCampoDominioVO(), ConstantesCRM.getCurrentUser(request.getSession())));

			getCampoDinamicoForm().setCampoVO(null);
			request.setAttribute("msgError", ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("ManterCampoDinamicoController:persisteDadosParam" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgError", twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("ManterCampoDinamicoController:persisteDadosParam" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget("top.frameApp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute("formParam", formParam);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="editarParamCampo.jsp"
	 */
	public ActionForward carregaEdicaoAssoc(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("ManterCampoDinamicoController:carregaEdicaoAssoc(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		CampoDinamicoForm form = (CampoDinamicoForm) formParam;

		getCampoDinamicoForm().setCampoVO(getAdmCampoById(form.getCampoVO().getIdCampo(), getCampoDinamicoForm().getCamposVO()));
		getCampoDinamicoForm().setDominioComboVO(controlValorDominio.carregaComboDominio(ConstantesCRM.getCurrentUser(request.getSession())));
		getCampoDinamicoForm().setCampoDominioVO(controlValorDominio.carregaParametroCampoDominio(form.getCampoVO(), ConstantesCRM.getCurrentUser(request.getSession())));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class CampoDinamicoForm extends ActionForm {

		private static final long serialVersionUID = -3349747288672519448L;

		private AdmCampoDominioVO campoDominioVO;
		private AdmDominioComboVO dominioComboVO;
		private AdmCampoCombosVO campoComboVO;
		private AdmCamposContatoVO camposVO;
		private String campoVOArrayLength = ConstantesCRM.SVAZIO;
		private AdmCampoContatoVO campoVO;

		public void setCampoVO(AdmCampoContatoVO campoVO) {
			this.campoVO = campoVO;
		}

		public AdmCampoContatoVO getCampoVO() {
			if (this.campoVO == null) {
				this.campoVO = AdmCampoContatoVO.Factory.newInstance();
				this.campoVO.setAdmClassificadorCampoVO(AdmClassificadorCampoVO.Factory.newInstance());
				this.campoVO.setAdmMascaraApresentacaoVO(AdmMascaraApresentacaoVO.Factory.newInstance());
				this.campoVO.setAdmLayoutApresentacaoCampoVO(AdmLayoutApresentacaoCampoVO.Factory.newInstance());
				this.campoVO.setAdmTipoDadoCampoVO(AdmTipoDadoCampoVO.Factory.newInstance());
			}
			return this.campoVO;
		}

		public void setCampoVOArrayLength(String campoVOArrayLength) {
			this.campoVOArrayLength = campoVOArrayLength;
		}

		public String getCampoVOArrayLength() {
			return this.campoVOArrayLength;
		}

		public void setCamposVO(AdmCamposContatoVO camposVO) {
			this.camposVO = camposVO;
		}

		public AdmCamposContatoVO getCamposVO() {
			if (this.camposVO == null) {
				this.camposVO = AdmCamposContatoVO.Factory.newInstance();
			}
			return this.camposVO;
		}

		public void setCampoComboVO(AdmCampoCombosVO campoComboVO) {
			this.campoComboVO = campoComboVO;
		}

		public AdmCampoCombosVO getCampoComboVO() {
			if (this.campoComboVO == null) {
				this.campoComboVO = AdmCampoCombosVO.Factory.newInstance();
			}
			return this.campoComboVO;
		}

		public void setDominioComboVO(AdmDominioComboVO dominioComboVO) {
			this.dominioComboVO = dominioComboVO;
		}

		public AdmDominioComboVO getDominioComboVO() {
			if (this.dominioComboVO == null) {
				this.dominioComboVO = AdmDominioComboVO.Factory.newInstance();
			}
			return this.dominioComboVO;
		}

		public void setCampoDominioVO(AdmCampoDominioVO campoDominioVO) {
			this.campoDominioVO = campoDominioVO;
		}

		public AdmCampoDominioVO getCampoDominioVO() {
			if (this.campoDominioVO == null) {
				this.campoDominioVO = AdmCampoDominioVO.Factory.newInstance();
			}
			return this.campoDominioVO;
		}
	}

	public void setCampoDinamicoForm(CampoDinamicoForm form) {
		this.campoDinamicoForm = form;
	}

	public CampoDinamicoForm getCampoDinamicoForm() {
		if (this.campoDinamicoForm == null) {
			this.campoDinamicoForm = new CampoDinamicoForm();
		}
		return this.campoDinamicoForm;
	}

	private AdmCampoContatoVO getAdmCampoById(String id, AdmCamposContatoVO array) {
		if (id == null) {
			return null;
		}

		for (int i = 0; i < array.getAdmCampoContatoVOArray().length; i++) {
			if (id.equals(array.getAdmCampoContatoVOArray(i).getIdCampo())) {
				return array.getAdmCampoContatoVOArray(i);
			}
		}
		return null;
	}

	private AdmCamposContatoVO removeAdmCampoById(String id, AdmCamposContatoVO array) {
		if (id == null || array == null) {
			return null;
		}

		for (int i = 0; i < array.getAdmCampoContatoVOArray().length; i++) {
			if (id.equals(array.getAdmCampoContatoVOArray(i).getIdCampo())) {
				array.removeAdmCampoContatoVO(i);
				break;
			}
		}
		return array;
	}
}