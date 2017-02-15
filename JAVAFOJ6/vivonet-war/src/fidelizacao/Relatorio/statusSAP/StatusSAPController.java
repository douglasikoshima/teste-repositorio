package fidelizacao.Relatorio.statusSAP;

import java.io.BufferedOutputStream;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.dbclasses.RetencaoStatusSAP;

import com.indracompany.actions.AbstractAction;
import commons.errors.AjaxError;

@SuppressWarnings("deprecation")
public class StatusSAPController extends AbstractAction {

    private static final long serialVersionUID = -3005239829124632783L;

    protected global.Global globalApp = new global.Global();

	@EJB
	private br.com.vivo.fo.ctrls.fidelizacao.relatorios.RelatorioFacadeFid relatorioFacadeControl;

	private StatusSAPForm statusSAPForm;

	public StatusSAPForm getStatusSAPForm() {
		if (this.statusSAPForm == null) {
			this.statusSAPForm = new StatusSAPForm();
		}
		return this.statusSAPForm;
	}

	public void setStatusSAPForm(StatusSAPForm arg) {
		this.statusSAPForm = arg;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
		    setStatusSAPForm(new StatusSAPForm());
			return begin(mapping, form, request, response);
		} else if ("buscarListaStatusSAP".equals(mapping.getParameter())) {
			return buscarListaStatusSAP(mapping, form, request, response);
		} else if ("showXML".equals(mapping.getParameter())) {
			return showXML(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="listaStatusSAP.jsp"
	 */
    protected ActionForward buscarListaStatusSAP(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		StatusSAPForm form = (StatusSAPForm) formParam;

		String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());

		try {

			RetencaoStatusSAP[] arrayStatusSAP = relatorioFacadeControl.buscarStatusSAP(idUsuario, form.getNrLinha().replaceAll("[^0-9]", ConstantesCRM.SVAZIO));
			getStatusSAPForm().setListaStatusSAP(arrayStatusSAP);

		} catch (Exception e) {
			response.setStatus(AjaxError.STATUS_CODE_ERROR, AjaxError.MSG_ERRO);
			response.setHeader("stackTrace", AjaxError.getStackTrace(e));
			return null;
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 */
	protected ActionForward showXML(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		//StatusSAPForm form = (StatusSAPForm) formParam;
		int index = Integer.parseInt(request.getParameter("index"));

		try {

			String xml = getStatusSAPForm().getListaStatusSAP()[index].getXml();

			response.setContentType(ConstantesCRM.SContentType);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			bufferedOutputStream.write(xml.getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();

			return null;

		} catch (Exception e) {
			response.setStatus(AjaxError.STATUS_CODE_ERROR, AjaxError.MSG_ERRO);
			response.setHeader("stackTrace", AjaxError.getStackTrace(e));
			return null;
		}

	}

	public static class StatusSAPForm extends ActionForm {

        private static final long serialVersionUID = 8652062089431064045L;

        private String nrLinha;
		private RetencaoStatusSAP[] listaStatusSAP;

		public String getNrLinha() {
			if (this.nrLinha == null) {
				this.nrLinha = new String();
			}
			return this.nrLinha;
		}

		public void setNrLinha(String arg) {
			this.nrLinha = arg;
		}

		public RetencaoStatusSAP[] getListaStatusSAP() {
			if (this.listaStatusSAP == null) {
				this.listaStatusSAP = new RetencaoStatusSAP[0];
			}
			return this.listaStatusSAP;
		}

		public void setListaStatusSAP(RetencaoStatusSAP[] arg) {
			this.listaStatusSAP = arg;
		}

	}

}
