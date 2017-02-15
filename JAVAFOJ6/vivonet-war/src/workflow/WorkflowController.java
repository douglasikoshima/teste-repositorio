package workflow;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

import com.indracompany.actions.AbstractAction;

public class WorkflowController extends AbstractAction {

	private static final long serialVersionUID = -3612361472922245227L;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("parametros".equals(mapping.getParameter())) {
			return parametros(mapping, form, request, response);
		} else {
			return begin(mapping, form, request, response);
		}
	}

	/**
	 * @common:control
	 */
	@EJB
	private br.com.vivo.fo.ctrls.workflow.parametros.Parametros parametrosFacade;

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="/workflow/RegistrarContato/RegistrarContatoController.jpf"
	 */
	protected ActionForward parametros(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ParametrosVO parametro = null;

		// [Fernando Gomes]
		parametro = null;
		parametro = parametrosFacade.retornarParametros(ConstantesCRM.SONE, ConstantesCRM.SONE);

		request.getSession().setAttribute(ConstantesCRM.SPARAMETROS, parametro);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}
}
