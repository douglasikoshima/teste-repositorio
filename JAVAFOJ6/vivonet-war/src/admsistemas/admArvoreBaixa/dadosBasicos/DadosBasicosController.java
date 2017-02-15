package admsistemas.admArvoreBaixa.dadosBasicos;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;

public class DadosBasicosController extends AbstractAction {

	private static final long serialVersionUID = -8776682590344872462L;

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="configArvoreBaixa.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		DadosBasicosForm form = (DadosBasicosForm) formParam;
		log.debug("DadosBasicosController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		try {
			request.setCharacterEncoding(ConstantesCRM.SISO);
			request.setAttribute("idBaixa", form.getIdBaixa());
			request.setAttribute("nmBaixa", form.getNmBaixa());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class DadosBasicosForm extends ActionForm {

		private static final long serialVersionUID = 9005130610656029245L;
		private String nmBaixa;
		private String idBaixa;

		public void setIdBaixa(String idBaixa) {
			this.idBaixa = idBaixa;
		}

		public String getIdBaixa() {
			return this.idBaixa;
		}

		public void setNmBaixa(String nmBaixa) {
			this.nmBaixa = nmBaixa;
		}

		public String getNmBaixa() {
			return this.nmBaixa;
		}
	}
}
