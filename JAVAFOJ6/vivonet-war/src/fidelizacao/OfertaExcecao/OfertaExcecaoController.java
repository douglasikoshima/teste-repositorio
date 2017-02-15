package fidelizacao.OfertaExcecao;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.fidelizacao.realizarRetencao.ConsultarOfertaExecaoFacade;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.fidelizacao.vo.ListaOfertaExecaoVODocument.ListaOfertaExecaoVO;
import br.com.vivo.fo.fidelizacao.vo.OfertaExcecaoDetalheVODocument.OfertaExcecaoDetalheVO;
import br.com.vivo.fo.fidelizacao.vo.OfertaExecaoVODocument.OfertaExecaoVO;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

@SuppressWarnings({"rawtypes","unchecked"})
public class OfertaExcecaoController extends AbstractAction {

	private static final long serialVersionUID = -7404784100449534135L;

    @EJB
	private ConsultarOfertaExecaoFacade listaOfertaExecaoFac;

	private static transient Logger log = new Logger("Fidelizacao");

    protected global.Global globalApp = new global.Global();

	private String user = null;
	private OfertaExecaoVO[] arrayOferta = null;
	private ListaOfertaExecaoVO listaOferta = null;
	public ArrayList lDetalhe = new ArrayList();

	public ArrayList getlDetalhe() {
        return lDetalhe;
    }

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.request = request;
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("sair".equals(mapping.getParameter())) {
			return sair(mapping, form, request, response);
		} else if ("VuelveDetalleOferta".equals(mapping.getParameter())) {
			return VuelveDetalleOferta(mapping, form, request, response);
		} else if ("OfertaExcecao".equals(mapping.getParameter())) {
			return OfertaExcecao(mapping, form, request, response);
		} else if ("showDetalheOferta".equals(mapping.getParameter())) {
			return showDetalheOferta(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="OfertaExcecao.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		user = ConstantesCRM.getCurrentUser(request.getSession());

		carregaLista();

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void carregaLista() throws Exception {
		// listaOferta = listaOfertaExecaoFac.getLista((String)request.getSession().getAttribute(ConstantesCRM.USUARIO_IDENTIFICADOR_SESSION));
		listaOferta = listaOfertaExecaoFac.getLista(user);
		arrayOferta = new OfertaExecaoVO[listaOferta.getOfertaExecaoVOArray().length];
		for (int i = 0; i < listaOferta.getOfertaExecaoVOArray().length; i++) {
			arrayOferta[i] = listaOferta.getOfertaExecaoVOArray(i);
		}
	}

	public OfertaExecaoVO[] getArrayOferta() {
		return this.arrayOferta;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="/index.jsp"
	 */
	protected ActionForward sair(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="OfertaExcecao.jsp"
	 */
	protected ActionForward VuelveDetalleOferta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="OfertaExcecao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward OfertaExcecao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		OfertaExcecaoForm form = (OfertaExcecaoForm) formParam;
		try {
			if (request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("ok")) {
				try {
					if (form.getOfertasVistadas() != null && form.getOfertasVistadas().length > 0) {
						// if( request.getParameter("id") != null && request.getParameter("id").length() > 0 ){
						listaOfertaExecaoFac.setLista(ConstantesCRM.getCurrentUser(request.getSession()), form.getOfertasVistadas());
					}

				} catch (Exception e) {
					throw e;
				}
				carregaLista();
			}

		} catch (TuxedoWarningException twe) {
			log.warn("OfertaExcecaoController:OfertaExcecao(" + twe.getMessage() + ")");

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
	 * @jpf:forward name="success" path="detalheOferta.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward showDetalheOferta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			// String destino = ConstantesCRM.SUCCESS;
			if (request.getParameter(ConstantesCRM.SACTION) != null) {
				if (request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("ok")) {
					// Busca o id da oferta aceita
					String idOferta = request.getParameter("id");

					// Chamando o serviço tuxedo que retorna detalhe para a oferta aceita
					lDetalhe.clear();
					try {
						OfertaExcecaoDetalheVO listaOfertaDetalhe = listaOfertaExecaoFac.getDetalheLista(ConstantesCRM.getCurrentUser(request.getSession()), idOferta);
						for (int i = 0; i < listaOfertaDetalhe.getItemListaOfertaDetalheVOArray().length; i++) {
							lDetalhe.add(listaOfertaDetalhe.getItemListaOfertaDetalheVOArray(i));
						}
					} catch (Exception e) {
						throw e;
					}
				}
			}

		} catch (TuxedoWarningException twe) {
			log.warn("OfertaExcecaoController:showDetalheOferta(" + twe.getMessage() + ")");

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class OfertaExcecaoForm extends ActionForm {

		private static final long serialVersionUID = -8791358841622122661L;
        private String[] ofertasVistadas;

		public void setOfertasVistadas(String[] idOferta) {
			this.ofertasVistadas = idOferta;
		}

		public String[] getOfertasVistadas() {
			return this.ofertasVistadas;
		}
	}
}
