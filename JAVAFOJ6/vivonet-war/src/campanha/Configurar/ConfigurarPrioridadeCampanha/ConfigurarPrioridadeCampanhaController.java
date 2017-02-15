package campanha.Configurar.ConfigurarPrioridadeCampanha;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.campanha.vo.ListaAtendimentoCampanhaVODocument.ListaAtendimentoCampanhaVO.CampanhasRelacionadas;
import br.com.vivo.fo.constantes.ConstantesCRM;

import com.indracompany.actions.AbstractAction;

public class ConfigurarPrioridadeCampanhaController extends AbstractAction {

	private static final long serialVersionUID = 7860121199665557800L;

	@EJB
	private br.com.vivo.fo.ctrls.campanha.configurar.ConfigurarCampanha configurarCampanha;

	@EJB
	private br.com.vivo.fo.ctrls.campanha.atendimento.ListaAtendimentoCampanhaFacade listaAtendimentoCampanhaFac;

	protected global.Global globalApp = new global.Global();

	private SalvarPrioridadeCampanhaActionForm salvarPrioridadeCampanhaActionForm = new SalvarPrioridadeCampanhaActionForm();

	private String user = ConstantesCRM.SVAZIO;

	@Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if ("begin".equals(mapping.getParameter())) {
            return begin(mapping, form, request, response);
        } else if ("salvarPrioridadeCampanhaAction".equals(mapping.getParameter())) {
            return salvarPrioridadeCampanhaAction(mapping, form, request, response);
        }
        return begin(mapping, form, request, response);
    }

	public SalvarPrioridadeCampanhaActionForm getSalvarPrioridadeCampanhaActionForm() {
		return this.salvarPrioridadeCampanhaActionForm;
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="configurarPrioridadeCampanha.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		user = ConstantesCRM.getCurrentUser(request.getSession());
		loadPage(request);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void loadPage(HttpServletRequest request) throws Exception {
		user = ConstantesCRM.getCurrentUser(request.getSession());
		salvarPrioridadeCampanhaActionForm.setListaCampanha(listaAtendimentoCampanhaFac.getListaPriorizacaoCampanha(user).getCampanhasRelacionadasArray());
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward salvarPrioridadeCampanhaAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			SalvarPrioridadeCampanhaActionForm form = (SalvarPrioridadeCampanhaActionForm) formParam;

			user = ConstantesCRM.getCurrentUser(request.getSession());

			salvarPrioridadeCampanhaActionForm = form;

			configurarCampanha.setPrioridadeCampanha(user, form.getCodigoSelecionado());
		} catch (Exception e) {
			throw e;
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class SalvarPrioridadeCampanhaActionForm extends ActionForm {

		private static final long serialVersionUID = -9025135408820337688L;

		// private ApoioVO[] listaPrioridade = new ApoioVO[0];
		// private ApoioVO[] listaCanal = new ApoioVO[0];
		private CampanhasRelacionadas[] listaCampanha = new CampanhasRelacionadas[0];

		// private String listaCanalSelecionado;

		private int idSubCampanha = 0;
		// private int idSubCampanhaFixa = 0;

		private String[] codigoSelecionado = new String[0];
		private String[] ordemSelecionada = new String[0];

		// private int idAcao = 0; // Não mostrar

		public CampanhasRelacionadas[] getListaCampanha() {
			return listaCampanha;
		}

		public void setListaCampanha(CampanhasRelacionadas[] lista) {
			this.listaCampanha = lista;
		}

		public void setCodigoSelecionado(String[] s) {
			this.codigoSelecionado = s;
		}

		public String[] getCodigoSelecionado() {
			return this.codigoSelecionado;
		}

		public void setOrdemSelecionada(String[] s) {
			this.ordemSelecionada = s;
		}

		public String[] getOrdemSelecionada() {
			return this.ordemSelecionada;
		}

		public int getIdSubCampanha() {
			return this.idSubCampanha;
		}

		public void setIdSubCampanha(int i) {
			this.idSubCampanha = i;
		}

		/*
		 * public int getIdSubCampanhaFixa() { return this.idSubCampanhaFixa; } public void setIdSubCampanhaFixa(int i)
		 * { this.idSubCampanhaFixa =i; }
		 */

		/*
		 * public void setListaPrioridade(ApoioVO[] listaPrioridade) { this.listaPrioridade = listaPrioridade; } public
		 * ApoioVO[] getListaPrioridade() { return this.listaPrioridade; }
		 */

		/*
		 * public void setListaCanalSelecionado(String s) { this.listaCanalSelecionado = s; } public String
		 * getListaCanalSelecionado() { return this.listaCanalSelecionado; }
		 */

		/*
		 * public void setListaCanal(ApoioVO[] lista) { this.listaCanal = lista; } public ApoioVO[] getListaCanal() {
		 * return this.listaCanal; }
		 */

		/*
		 * public int getIdAcao() { return this.idAcao; } public void setIdAcao(int i) { this.idAcao = i; }
		 * 
		 * 
		 * public void clear() { this.idAcao = 0; }
		 */

	}
}
