package campanha.Manter.ManterTipoCampanha;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.campanha.vo.ApoioVODocument.ApoioVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;

@SuppressWarnings({"rawtypes"})
public class ManterTipoCampanhaController extends AbstractAction {

	private static final long serialVersionUID = 3800403357538152621L;

	protected global.Global globalApp = new global.Global();
	// private final int CAMPANHA = 0;
	// private final int TIPOCAMPANHA = 1;
	// private final int MOTIVO = 2;
	// private final int SUBMOTIVO = 3;
	private String user = ConstantesCRM.SVAZIO;

	@EJB
	private br.com.vivo.fo.ctrls.campanha.manter.CampanhaFacade campanhaFacade;

	// private GrupoCampanhaApoioVO grupoCampanhaApoio = null;
	public ArrayList aTipoCampanha = new ArrayList();
	private static transient Logger log = new Logger("campanha");

	private ManterTipoCampanhaActionForm manterTipoCampanhaActionForm = new ManterTipoCampanhaActionForm();

	public ManterTipoCampanhaActionForm getManterTipoCampanhaActionForm() {
		return manterTipoCampanhaActionForm;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("ManterTipoCampanhaAction".equals(mapping.getParameter())) {
			return ManterTipoCampanhaAction(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action login-required="true"
	 * @jpf:catch type="com.bea.wlw.netui.pageflow.NotLoggedInException" path="/Controller.jpf"
	 * @jpf:forward name="success" path="ManterTipoCampanhaAction.do"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		user = ConstantesCRM.getCurrentUser(request.getSession());
		request.setAttribute("btMostra", "INCLUIR");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action login-required="true"
	 * @jpf:catch type="com.bea.wlw.netui.pageflow.NotLoggedInException" path="/Controller.jpf"
	 * @jpf:forward name="done" return-action="ManterTipoCampanhaDone"
	 */
	public ActionForward done(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("done");
	}

	/**
	 * @jpf:action login-required="true"
	 * @jpf:catch type="com.bea.wlw.netui.pageflow.NotLoggedInException" path="/Controller.jpf"
	 * @jpf:forward name="success" path="ManterTipoCampanha.jsp"
	 */
	protected ActionForward ManterTipoCampanhaAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ManterTipoCampanhaActionForm form = (ManterTipoCampanhaActionForm) formParam;
		// Monta o log da operação se possível
		user = ConstantesCRM.getCurrentUser(request.getSession());
		// if( log.isDebugEnabled() )
		log.debug("ManterTipoCampanhaController:ManterTipoCampanhaAction(" + form + ")");

		String sForward = ConstantesCRM.SUCCESS;

		try {
			form.setListaCampanha(manterTipoCampanhaActionForm.getListaCampanha());
			manterTipoCampanhaActionForm = form;

			if ((request.getParameter(ConstantesCRM.SACTION) != null) && (request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("INCLUIR"))) {
				campanhaFacade.addApoioCampanhaTipoCampanha(user, form.getDsTipoCampanha(), form.getSgTipoCampanha());
				form.clear();
			} else if ((request.getParameter(ConstantesCRM.SACTION) != null) && (request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("SALVAR"))) {
				campanhaFacade.setApoioCampanhaTipoCampanha(user, Integer.parseInt(form.getIdTipoCampanha()), form.getDsTipoCampanha(), form.getSgTipoCampanha());
				form.clear();
				request.setAttribute("btMostra", "INCLUIR");
			} else if ((request.getParameter(ConstantesCRM.SACTION) != null) && (request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("EDITAR"))) {
				int iIndex = 0;
				if (request.getParameter("indice") != null) {
					iIndex = Integer.parseInt(request.getParameter("indice"));
				}

				manterTipoCampanhaActionForm.setDsTipoCampanha(form.getListaCampanha()[iIndex].getDescricao());
				manterTipoCampanhaActionForm.setIdTipoCampanha(form.getListaCampanha()[iIndex].getCodigo());
				manterTipoCampanhaActionForm.setSgTipoCampanha(form.getListaCampanha()[iIndex].getSigla());
				request.setAttribute("btMostra", "EDITAR");
			} else if ((request.getParameter(ConstantesCRM.SACTION) != null) && (request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("EXCLUIR"))) {
				campanhaFacade.delApoioCampanhaTipoCampanha(user, Integer.parseInt(request.getParameter("codigo")));
				form.clear();
			}

			manterTipoCampanhaActionForm.setListaCampanha(campanhaFacade.getGrupoCampanhaApoio("user", ConstantesCRM.SFOUR).getSubGrupoApoioVOArray(0).getApoioVOArray());

		} catch (Exception e) {
			log.debug("ManterTipoCampanhaController:ManterTipoCampanhaAction " + e.getMessage());
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(sForward);
	}

	public static class ManterTipoCampanhaActionForm extends ActionForm {

		private static final long serialVersionUID = 8277411587610382848L;

		private String idTipoCampanha;
		private String dsTipoCampanha;
		private String sgTipoCampanha;
		private ApoioVO[] listaCampanha = new ApoioVO[0];

		public void setListaCampanha(ApoioVO[] lista) {
			listaCampanha = lista;
		}

		public ApoioVO[] getListaCampanha() {
			return listaCampanha;
		}

		public void setSgTipoCampanha(String sgTipoCampanha) {
			this.sgTipoCampanha = sgTipoCampanha;
		}

		public String getSgTipoCampanha() {
			return this.sgTipoCampanha;
		}

		public void setDsTipoCampanha(String dsTipoCampanha) {
			this.dsTipoCampanha = dsTipoCampanha;
		}

		public String getDsTipoCampanha() {
			return this.dsTipoCampanha;
		}

		public void setIdTipoCampanha(String idTipoCampanha) {
			this.idTipoCampanha = idTipoCampanha;
		}

		public String getIdTipoCampanha() {
			return this.idTipoCampanha;
		}

		public void clear() {
			this.dsTipoCampanha = ConstantesCRM.SVAZIO;
			this.sgTipoCampanha = ConstantesCRM.SVAZIO;
			this.idTipoCampanha = "-1";
		}
	}
}
