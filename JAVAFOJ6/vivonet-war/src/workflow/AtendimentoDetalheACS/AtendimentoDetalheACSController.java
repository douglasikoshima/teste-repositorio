package workflow.AtendimentoDetalheACS;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.AtendimentoDetalheACSMigracaoVODocument.AtendimentoDetalheACSMigracaoVO;

import com.indracompany.actions.AbstractAction;

public class AtendimentoDetalheACSController extends AbstractAction {

	private static final long serialVersionUID = -5726277165403991140L;

	private static Logger log = new Logger("workflow");

	@EJB
	private br.com.vivo.fo.ctrls.workflow.atendimentoDetalheACS.AtendimentoDetalheACSFacade atendimentoDetalheACSFacade;

	private AtendimentoDetalheACSForm atendimentoDetalheACSForm = null;

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
	 * @jpf:forward name="success" path="atendimentoDetalheACS.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoDetalheACSController:begin.do - [Detalhe de Atendimento ACS- Inicio do Metodo]");
		String syes = new String(ConstantesCRM.SYES);
		String sno = new String("Não");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String idAtendimento = request.getParameter("IDATENDIMENTO");
		log.debug("AtendimentoDetalheController:begin.do - [idAtendimento = " + idAtendimento + "]");

		atendimentoDetalheACSForm = new AtendimentoDetalheACSForm();
		atendimentoDetalheACSForm.setAtendimentoDetalheACS(atendimentoDetalheACSFacade.obtemDetalheACS(user, idAtendimento));
		if (!atendimentoDetalheACSForm.getAtendimentoDetalheACS().getInClienteVIVO().equals(ConstantesCRM.SVAZIO)) {
			if (atendimentoDetalheACSForm.getAtendimentoDetalheACS().getInClienteVIVO() != null && atendimentoDetalheACSForm.getAtendimentoDetalheACS().getInClienteVIVO().equals("0")) {
				atendimentoDetalheACSForm.getAtendimentoDetalheACS().setInClienteVIVO(sno);
			} else {
				atendimentoDetalheACSForm.getAtendimentoDetalheACS().setInClienteVIVO(syes);
			}
		}

		if (atendimentoDetalheACSForm.getAtendimentoDetalheACS().getInProcessoTecnico() != null && !atendimentoDetalheACSForm.getAtendimentoDetalheACS().getInProcessoTecnico().equals("")) {
			if (atendimentoDetalheACSForm.getAtendimentoDetalheACS().getInProcessoTecnico().equals(ConstantesCRM.SZERO)) {
				atendimentoDetalheACSForm.getAtendimentoDetalheACS().setInProcessoTecnico(sno);
			} else {
				atendimentoDetalheACSForm.getAtendimentoDetalheACS().setInProcessoTecnico(syes);
			}
		}
		log.debug("AtendimentoDetalheACSController:begin.do - [Detalhe de Atendimento ACS- Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class AtendimentoDetalheACSForm extends ActionForm {

		private static final long serialVersionUID = -4722098997787114470L;

		private AtendimentoDetalheACSMigracaoVO atendimentoDetalheACS;

		public void setAtendimentoDetalheACS(AtendimentoDetalheACSMigracaoVO atendimentoDetalheACS) {
			this.atendimentoDetalheACS = atendimentoDetalheACS;
		}

		public AtendimentoDetalheACSMigracaoVO getAtendimentoDetalheACS() {
			return this.atendimentoDetalheACS;
		}
	}

	// getter para o ActionForm
	public AtendimentoDetalheACSForm getAtendimentoDetalheACSForm() {
		return this.atendimentoDetalheACSForm;
	}
}
