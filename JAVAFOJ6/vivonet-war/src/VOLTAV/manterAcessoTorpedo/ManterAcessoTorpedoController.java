package VOLTAV.manterAcessoTorpedo;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import weblogic.logging.NonCatalogLogger;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.VOLTAV.manterAcessoTorpedoWeb.ManterAcessoTorpedoWebFacade;
import br.com.vivo.fo.ctrls.VOLTAV.manterAcessoTorpedoWeb.db.ManterAcessoTorpedoDB.ItemAcessoTorpedo;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ManterAcessoTorpedoController extends AbstractAction {

	private static final long serialVersionUID = -3220170021068203211L;

	protected global.Global globalApp = new global.Global();

	private static transient NonCatalogLogger logger = new NonCatalogLogger(ManterAcessoTorpedoController.class.getName());

	FormManterAcessoTorpedo formManterAcessoTorpedo = null;

	@EJB
	private ManterAcessoTorpedoWebFacade manterAcessoTorpedoWebFacade;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("consultarAcessoTorpedo".equals(mapping.getParameter())) {
			return consultarAcessoTorpedo(mapping, form, request, response);
		} else if ("ativarAcessoTorpedo".equals(mapping.getParameter())) {
			return ativarAcessoTorpedo(mapping, form, request, response);
		} else if ("desativarAcessoTorpedo".equals(mapping.getParameter())) {
			return desativarAcessoTorpedo(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="consultarAcessoTorpedo.do"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public void setFormManterAcessoTorpedo(FormManterAcessoTorpedo form) {
		this.formManterAcessoTorpedo = form;
	}

	public FormManterAcessoTorpedo getFormManterAcessoTorpedo() {
		if (this.formManterAcessoTorpedo == null) {
			this.formManterAcessoTorpedo = new FormManterAcessoTorpedo();
		}

		return this.formManterAcessoTorpedo;
	}

	public String getUser(HttpServletRequest request) {
		String sUser = ConstantesCRM.getCurrentUser(request.getSession());
		NumberFormat nf = new DecimalFormat(ConstantesCRM.SZERO);
		nf.setMinimumIntegerDigits(15);
		long l = Long.valueOf(sUser).longValue();
		sUser = nf.format(l);
		return sUser;
	}

	/**
	 * @jpf:action form="formManterAcessoTorpedo"
	 * @jpf:forward name="success" path="manterAcessoTorpedo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward consultarAcessoTorpedo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		FormManterAcessoTorpedo form = (FormManterAcessoTorpedo) formParam;
		logger.info("ManterAcessoTorpedoController:consultarAcessoTorpedo");
		try {
			ItemAcessoTorpedo[] listaItem = getListaAcessoTorpedoWeb();
			getFormManterAcessoTorpedo().setListaItemAcesso(listaItem);

		} catch (Exception e) {
			logger.error("ManterAcessoTorpedoController:consultarAcessoTorpedo(" + this.getUser(request) + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action form="formManterAcessoTorpedo"
	 * @jpf:forward name="success" path="manterAcessoTorpedo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward ativarAcessoTorpedo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		FormManterAcessoTorpedo form = (FormManterAcessoTorpedo) formParam;
		logger.info("ManterAcessoTorpedoController:ativarAcessoTorpedo " + form.getIdAcessoSelecionado());
		try {
			if (form.getIdAcessoSelecionado() != null && form.getIdAcessoSelecionado().length() > 0) {
				manterAcessoTorpedoWebFacade.ativarAcessoTorpedoWeb(form.getIdAcessoSelecionado());
				ItemAcessoTorpedo[] listaItem = getListaAcessoTorpedoWeb();
				getFormManterAcessoTorpedo().setListaItemAcesso(listaItem);
			}

		} catch (Exception e) {
			logger.error("ManterAcessoTorpedoController:ativarAcessoTorpedo(" + this.getUser(request) + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action form="formManterAcessoTorpedo"
	 * @jpf:forward name="success" path="manterAcessoTorpedo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward desativarAcessoTorpedo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		FormManterAcessoTorpedo form = (FormManterAcessoTorpedo) formParam;
		logger.info("ManterAcessoTorpedoController:desativarAcessoTorpedo " + form.getIdAcessoSelecionado());
		try {
			if (form.getIdAcessoSelecionado() != null && form.getIdAcessoSelecionado().length() > 0) {
				manterAcessoTorpedoWebFacade.desativarAcessoTorpedoWeb(form.getIdAcessoSelecionado());
				ItemAcessoTorpedo[] listaItem = getListaAcessoTorpedoWeb();
				getFormManterAcessoTorpedo().setListaItemAcesso(listaItem);
			}

		} catch (Exception e) {
			logger.error("ManterAcessoTorpedoController:desativarAcessoTorpedo(" + this.getUser(request) + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private ItemAcessoTorpedo[] getListaAcessoTorpedoWeb() throws Exception {

		logger.info("ManterAcessoTorpedoController:getListaAcessoTorpedoWeb");
		ItemAcessoTorpedo[] listaItem = null;
		try {
			listaItem = manterAcessoTorpedoWebFacade.consultarAcessoTorpedoWeb();
			logger.info("ManterAcessoTorpedoController:getListaAcessoTorpedoWeb:Total Linhas " + listaItem.length);
			return listaItem;
		} catch (Exception e) {
			throw e;
		}

	}

	public static class FormManterAcessoTorpedo extends ActionForm {

		private ItemAcessoTorpedo[] listaItemAcesso = null;
		private String idAcessoSelecionado = ConstantesCRM.SVAZIO;

		public ItemAcessoTorpedo[] getListaItemAcesso() {
			return this.listaItemAcesso;
		}

		public void setListaItemAcesso(ItemAcessoTorpedo[] lista) {
			this.listaItemAcesso = lista;
		}

		public String getIdAcessoSelecionado() {
			return this.idAcessoSelecionado;
		}

		public void setIdAcessoSelecionado(String idAcessoSelecionado) {
			this.idAcessoSelecionado = idAcessoSelecionado;
		}
	}
}
