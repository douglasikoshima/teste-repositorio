package fidelizacao.Manter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;

import com.indracompany.actions.AbstractAction;

public class ManterController extends AbstractAction{

	private static final long serialVersionUID = 5612472162816651109L;

	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("done".equals(mapping.getParameter())) {
			return done(mapping, form, request, response);
		} else if ("sair".equals(mapping.getParameter())) {
			return sair(mapping, form, request, response);
		} else if ("ManterDefItencaoDone".equals(mapping.getParameter())) {
			return ManterDefItencaoDone(mapping, form, request, response);
		} else if ("ManterAparelhoDone".equals(mapping.getParameter())) {
			return ManterAparelhoDone(mapping, form, request, response);
		} else if ("ManterBonusDone".equals(mapping.getParameter())) {
			return ManterBonusDone(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="done" return-action="ManterDone"
	 */
	public ActionForward done(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("done");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="/inicio.jsp"
	 */
	protected ActionForward sair(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}



	/**
	 * @jpf:action
	 */
	protected ActionForward ManterDefItencaoDone(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 */
	protected ActionForward ManterAparelhoDone(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}



	/**
	 * @jpf:action
	 */
	protected ActionForward ManterBonusDone(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response)
	{
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}
}
