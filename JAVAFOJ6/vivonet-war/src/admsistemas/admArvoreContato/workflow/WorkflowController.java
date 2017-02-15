package admsistemas.admArvoreContato.workflow;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;

import com.indracompany.actions.AbstractAction;

public class WorkflowController extends AbstractAction {

	private static final long serialVersionUID = -5440444166307182586L;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="configArvoreWorkflow.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DadosBasicosForm form = (DadosBasicosForm) formParam;
		request.setCharacterEncoding(ConstantesCRM.SISO);

		request.setAttribute("idContato", request.getParameter("idContato"));
		form.setIdContato(request.getParameter("idContato"));
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class DadosBasicosForm extends ActionForm {

		private static final long serialVersionUID = 6778440978672719637L;
		private String idContato;

		public void setIdContato(String idContato) {
			this.idContato = idContato;
		}

		public String getIdContato() {
			return this.idContato;
		}
	}

}
