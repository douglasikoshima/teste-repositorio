package VOLTAV.parceiroSDP;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.VOLTAV.ordenarMenu.OrdenarMenuFacade;
import br.com.vivo.fo.ctrls.VOLTAV.parceiroSDP.ParceiroSDPFacade;
import br.com.vivo.fo.ctrls.VOLTAV.parceiroSDP.db.ParceiroSDPDB.FolhaContato;
import br.com.vivo.fo.ctrls.VOLTAV.parceiroSDP.db.ParceiroSDPDB.Parceiro;
import br.com.vivo.fo.voltav.vo.ListaMenuVODocument.ListaMenuVO;

import com.indracompany.actions.AbstractAction;

public class ParceiroSDPController extends AbstractAction {

	private static final long serialVersionUID = 4961204801943750564L;

	protected global.Global globalApp = new global.Global();

	@EJB
	private ParceiroSDPFacade parceiroSDPFacade;

	@EJB
	private OrdenarMenuFacade ordenarMenuFacade;

	private ParceiroSDPForm parceiroSDPForm = null;

	private ListaMenuVO comboSubSistema;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("pesquisa".equals(mapping.getParameter())) {
			return pesquisa(mapping, form, request, response);
		} else if ("manterParceiro".equals(mapping.getParameter())) {
			return manterParceiro(mapping, form, request, response);
		} else if ("cancelar".equals(mapping.getParameter())) {
			return cancelar(mapping, form, request, response);
		} else if ("novoParceiro".equals(mapping.getParameter())) {
			return novoParceiro(mapping, form, request, response);
		} else if ("alteraParceiro".equals(mapping.getParameter())) {
			return alteraParceiro(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward pesquisa(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ParceiroSDPForm form = (ParceiroSDPForm)formParam;

		if (form.getNomePesquisar().equals(ConstantesCRM.SVAZIO)) {
			getParceiroSDPForm().setParceiros(parceiroSDPFacade.getTodosParceiros());
		} else {
			getParceiroSDPForm().setParceiros(parceiroSDPFacade.getParceiros(form.getNomePesquisar()));
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="cadastroAlteracaoParceiroSDP.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 * @jpf:forward name="duplicado" return-to="currentPage"
	 */
	protected ActionForward manterParceiro(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ParceiroSDPForm form = (ParceiroSDPForm)formParam;
		String operacao = ConstantesCRM.SVAZIO;
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String menu = ConstantesCRM.SVAZIO;

		if (form.getOperacao().equals("ALTERACAO")) {

			String msg = "Alterado com sucesso!";
			request.setAttribute("msgStatus", msg);

			operacao = "alterarMenuParceiro";
			menu = form.getMenu();
		} else {

			if (parceiroSDPFacade.verificaDuplicidade(form.getNomeParceiro().toUpperCase())) {
				String msg = "Nome do parceiro já cadastrado, favor alterar o nome do parceiro.";
				request.setAttribute("msgErro", msg);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			} else {

				String msg = "Inserido com sucesso!";
				request.setAttribute("msgStatus", msg);

				operacao = "insereMenuParceiro";
				menu = form.getMenupai();
			}
		}

		parceiroSDPFacade.manterParceiroSDP(operacao,
				user,
				form.getNomeParceiro(),
				menu,
				form.getContato(),
				form.getIpparceiro(),
				form.getUrlfinal());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 */
	protected ActionForward cancelar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ParceiroSDPForm form = (ParceiroSDPForm)formParam;
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="cadastroAlteracaoParceiroSDP.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward novoParceiro(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ParceiroSDPForm form = (ParceiroSDPForm)formParam;

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		FolhaContato[] folhas = parceiroSDPFacade.getFolhasContatoDisponiveis(null);

		setComboSubSistema(ordenarMenuFacade.obterComboSubSistema(user, "15"));

		parceiroSDPForm = null;

		getParceiroSDPForm().setFolhaContato(folhas);
		getParceiroSDPForm().setOperacao("NOVO");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="cadastroAlteracaoParceiroSDP.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward alteraParceiro(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ParceiroSDPForm form = (ParceiroSDPForm)formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String idParceiro = request.getParameter("idParceiro");

		Parceiro parceiroCarregado = parceiroSDPFacade.carregaParceiro(idParceiro);

		FolhaContato[] folhas = parceiroSDPFacade.getFolhasContatoDisponiveis(parceiroCarregado.getIdcontato());
		setComboSubSistema(ordenarMenuFacade.obterComboSubSistema(user, "15"));

		getParceiroSDPForm().setFolhaContato(folhas);
		getParceiroSDPForm().setContato(parceiroCarregado.getIdcontato());
		getParceiroSDPForm().setNomeParceiro(parceiroCarregado.getNmparceiro());
		getParceiroSDPForm().setIpparceiro(parceiroCarregado.getDsipparceiro());
		getParceiroSDPForm().setUrlfinal(parceiroCarregado.getDsurlparceiro());
		getParceiroSDPForm().setMenu(parceiroCarregado.getIditemmenu());
		getParceiroSDPForm().setMenupai(parceiroCarregado.getIditemmenupai());
		getParceiroSDPForm().setOperacao("ALTERACAO");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class ParceiroSDPForm extends ActionForm {

		public String nomePesquisar;
		public Parceiro[] parceiros;
		public FolhaContato[] folhaContato;

		public String nomeParceiro;
		public String menu;
		public String contato;
		public String ipparceiro;
		public String urlfinal;
		public String operacao;
		public String menupai;

		public String getMenupai() {
			return this.menupai;
		}

		public void setMenupai(String menupai) {
			this.menupai = menupai;
		}

		public String getOperacao() {
			return this.operacao;
		}

		public void setOperacao(String operacao) {
			this.operacao = operacao;
		}

		public String getNomePesquisar() {
			return this.nomePesquisar;
		}

		public void setNomePesquisar(String nomePesquisar) {
			this.nomePesquisar = nomePesquisar;
		}


		public Parceiro[] getParceiros() {
			if(this.parceiros == null) {
				this.parceiros = new Parceiro[0];
			}
			return this.parceiros;
		}

		public void setParceiros(Parceiro[] parceiros) {
			this.parceiros = parceiros;
		}

		public FolhaContato[] getFolhaContato() {
			return this.folhaContato;
		}

		public void setFolhaContato(FolhaContato[] folhaContato) {
			this.folhaContato = folhaContato;
		}

		public String getNomeParceiro() {
			return this.nomeParceiro;
		}

		public void setNomeParceiro(String nomeParceiro) {
			this.nomeParceiro = nomeParceiro;
		}
		public String getMenu() {
			return this.menu;
		}

		public void setMenu(String menu) {
			this.menu = menu;
		}

		public String getContato() {
			return this.contato;
		}

		public void setContato(String contato) {
			this.contato = contato;
		}

		public String getIpparceiro() {
			return this.ipparceiro;
		}

		public void setIpparceiro(String ipparceiro) {
			this.ipparceiro = ipparceiro;
		}

		public String getUrlfinal() {
			return this.urlfinal;
		}

		public void setUrlfinal(String urlfinal) {
			this.urlfinal = urlfinal;
		}

	}

	public ListaMenuVO getComboSubSistema() {
		return this.comboSubSistema;
	}

	public void setComboSubSistema(ListaMenuVO value) {
		this.comboSubSistema = value;
	}

	public ParceiroSDPForm getParceiroSDPForm() {
		if(this.parceiroSDPForm == null) {
			this.parceiroSDPForm = new ParceiroSDPForm();
		}
		return this.parceiroSDPForm;
	}

}