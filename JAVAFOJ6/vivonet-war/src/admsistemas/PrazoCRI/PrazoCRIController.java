package admsistemas.PrazoCRI;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO;

import com.indracompany.actions.AbstractAction;

public class PrazoCRIController extends AbstractAction {

	private static final long serialVersionUID = 2728426965803315707L;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.perfilCRI.PerfilCRI perfilCRIFacade;

	private String user = ConstantesCRM.SVAZIO;

	private PrazoCRIForm prazoCRIForm = null;

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("gravaPrazo".equals(mapping.getParameter())) {
			return gravaPrazo(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="prazoCRI.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("PrazoCRIController:begin(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		user = ConstantesCRM.getCurrentUser(request.getSession());

		prazoCRIForm = new PrazoCRIForm();

		WFAcaoRetornoVO wfRetAcao = WFAcaoRetornoVO.Factory.newInstance();
		wfRetAcao.setAcaoExecucao(ConstantesCRM.SONE);

		wfRetAcao = this.perfilCRIFacade.prazoCRI(user, wfRetAcao);
		prazoCRIForm.setMensagem(wfRetAcao.getMensagem());
		prazoCRIForm.setIdRetorno(wfRetAcao.getAcaoExecucao());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="Resultado.jsp"
	 */
	public ActionForward gravaPrazo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("PrazoCRIController:gravaPrazo(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		PrazoCRIForm form = (PrazoCRIForm) formParam;
		user = ConstantesCRM.getCurrentUser(request.getSession());

		WFAcaoRetornoVO wfRetAcao = WFAcaoRetornoVO.Factory.newInstance();
		wfRetAcao.setAcaoExecucao(ConstantesCRM.STWO);
		wfRetAcao.setMensagem(form.getMensagem());
		wfRetAcao = this.perfilCRIFacade.prazoCRI(user, wfRetAcao);
		prazoCRIForm.setMensagem(wfRetAcao.getMensagem());
		prazoCRIForm.setIdRetorno(wfRetAcao.getAcaoExecucao());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class PrazoCRIForm extends ActionForm {

		private static final long serialVersionUID = 4908446449459526993L;

		private String strMensagem = ConstantesCRM.SVAZIO;

		public void setMensagem(String strMensagem) {
			this.strMensagem = strMensagem;
		}

		public String getMensagem() {
			return this.strMensagem;
		}

		private String idRetorno = ConstantesCRM.SVAZIO;

		public void setIdRetorno(String idRetorno) {
			this.idRetorno = idRetorno;
		}

		public String getIdRetorno() {
			return this.idRetorno;
		}
	}

	// Getter para o Form perfilVariaveisVOForm
	public PrazoCRIForm getPrazoCRIForm() {
		return this.prazoCRIForm;
	}
}
