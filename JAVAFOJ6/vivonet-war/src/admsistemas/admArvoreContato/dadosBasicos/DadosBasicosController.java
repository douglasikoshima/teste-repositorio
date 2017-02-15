package admsistemas.admArvoreContato.dadosBasicos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;

public class DadosBasicosController extends AbstractAction {

	private static final long serialVersionUID = -284585501333466755L;

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="configArvoreContato.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("DadosBasicosController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		DadosBasicosForm form = (DadosBasicosForm) formParam;

		request.setCharacterEncoding(ConstantesCRM.SISO);

		request.setAttribute("idContato", request.getParameter("idContato"));
		form.setIdContato(request.getParameter("idContato").toString());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class DadosBasicosForm extends ActionForm {

		private static final long serialVersionUID = 7001790221742409186L;

		private String idContato;

		public void setIdContato(String idContato) {
			this.idContato = idContato;
		}

		public String getIdContato() {
			return this.idContato;
		}

		private String nmContato;

		public void setNmContato(String nmContato) {
			this.nmContato = nmContato;
		}

		public String getNmContato() {
			return this.nmContato;
		}
	}
}