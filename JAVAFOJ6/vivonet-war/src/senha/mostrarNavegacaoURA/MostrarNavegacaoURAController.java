package senha.mostrarNavegacaoURA;

import java.util.StringTokenizer;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.senha.vo.ArvoreNavegacaoUraVODocument.ArvoreNavegacaoUraVO;

import com.indracompany.actions.AbstractAction;

public class MostrarNavegacaoURAController extends AbstractAction {

	private static final long serialVersionUID = -8672725351046655294L;

	@EJB
	private br.com.vivo.fo.ctrls.senha.cti.TransferirFacade transferirFacade;

	private MostrarNavegacaoURAForm mostrarNavegacaoURA;

	private String user = ConstantesCRM.SVAZIO;

	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="navegacaoURA.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MostrarNavegacaoURAForm form = (MostrarNavegacaoURAForm) formParam;

		user = ConstantesCRM.getCurrentUser(request.getSession());
		// inicializa bean
		this.mostrarNavegacaoURA = new MostrarNavegacaoURAForm();

		String strIndTimeoutRedirURA = request.getParameter("IndTimeoutRedirURA");
		String strNavegacaoURA = request.getParameter("navegacaoURA");

		// ParametrosVO paramVO = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS));
		// if(paramVO.getIndRedirTimeoutURA()!=null && paramVO.getIndRedirTimeoutURA().equals("S"))
		if (strIndTimeoutRedirURA != null && strIndTimeoutRedirURA.equals("S")) {
			this.mostrarNavegacaoURA.setIndTimeoutRedirURA("S");
		}
		// if( paramVO.getNavCliUra()!=null)
		if (strNavegacaoURA != null && !strNavegacaoURA.equals(ConstantesCRM.SVAZIO)) {
			// String navCliUra = paramVO.getNavCliUra();
			// Monta XML ArvoreNavegacaoUraVO com os parâmetros do CTI
			// StringTokenizer st = new StringTokenizer(navCliUra,";");
			StringTokenizer st = new StringTokenizer(strNavegacaoURA, ";");
			String xml = "<ArvoreNavegacaoUraVO>";
			while (st.hasMoreTokens()) {
				xml = xml + "<itemArvoreNavegacaoUraVO>" + "<codigo>" + st.nextToken() + "</codigo>" + "</itemArvoreNavegacaoUraVO>";
			}
			xml = xml + "</ArvoreNavegacaoUraVO>";
			this.mostrarNavegacaoURA.arvoreNavegacaoURA = transferirFacade.ConsArvoreNavegacaoUra(xml, ConstantesCRM.SONE);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class MostrarNavegacaoURAForm extends ActionForm {

		private static final long serialVersionUID = -403995880071250348L;

		private String indTimeoutRedirURA;
		private ArvoreNavegacaoUraVO arvoreNavegacaoURA;

		public void setIndTimeoutRedirURA(String indTimeoutRedirURA) {
			this.indTimeoutRedirURA = indTimeoutRedirURA;
		}

		public String getIndTimeoutRedirURA() {
			return this.indTimeoutRedirURA;
		}

		public void setarvoreNavegacaoURA(ArvoreNavegacaoUraVO arvoreNavegacaoURA) {
			this.arvoreNavegacaoURA = arvoreNavegacaoURA;
		}

		public ArvoreNavegacaoUraVO getArvoreNavegacaoURA() {
			return this.arvoreNavegacaoURA;
		}
	}

	/**
	 * Getter para o FormBean
	 */
	public MostrarNavegacaoURAForm getMostrarNavegacaoURAForm() {
		return this.mostrarNavegacaoURA;
	}
}
