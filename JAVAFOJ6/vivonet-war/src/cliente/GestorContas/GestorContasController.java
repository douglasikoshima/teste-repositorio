package cliente.GestorContas;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.GestorContasVODocument.GestorContasVO;
import br.com.vivo.fo.cliente.vo.GestorContasVODocument.GestorContasVO.GestorVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

@SuppressWarnings({"deprecation"})
public class GestorContasController extends AbstractAction {

	private static final long serialVersionUID = 8272068444391670226L;

	protected global.Global globalApp = new global.Global();

	@EJB
	private br.com.vivo.fo.ctrls.cliente.associarGestor.RelacionarGestor relacionarGestor;

	private GestorContasForm gestorContasForm;

	public GestorContasForm getGestorContasForm() {
		if (this.gestorContasForm == null) {
			this.gestorContasForm = new GestorContasForm();
		}
		return this.gestorContasForm;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("getPesquisa".equals(mapping.getParameter())) {
			return getPesquisa(mapping, form, request, response);
		} else if ("getDadosGestor".equals(mapping.getParameter())) {
			return getDadosGestor(mapping, form, request, response);
		} else if ("getPesquisaContas".equals(mapping.getParameter())) {
			return getPesquisaContas(mapping, form, request, response);
		} else if ("gravarGestor".equals(mapping.getParameter())) {
			return gravarGestor(mapping, form, request, response);
		} else if ("excluirGestor".equals(mapping.getParameter())) {
			return excluirGestor(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			this.gestorContasForm = new GestorContasForm();
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, ConstantesCRM.TELAINCIAL);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * Método utilizado para pesquisa de gestores.
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="resultadoPesquisa.jsp"
	 */
	public ActionForward getPesquisa(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		GestorContasForm form = (GestorContasForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			form.getGestorContasVO().setTpOperacao("pesquisarGestor");
			GestorContasVO gestorContasVO = relacionarGestor.getGestorContas(user, form.getGestorContasVO());
			getGestorContasForm().setGestorContasVO(gestorContasVO);
		} catch (Exception e) {

		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * Método utilizado para busca de dados de um gestor.
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="manutencaoGestorContas.jsp"
	 */
	public ActionForward getDadosGestor(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			GestorContasVO gestorContasVO = GestorContasVO.Factory.newInstance();
			gestorContasVO.setTpOperacao("select");
			gestorContasVO.addNewFiltros().addNewCombos().addNmSelect("UF");
			gestorContasVO = relacionarGestor.getGestorContas(user, gestorContasVO);
			getGestorContasForm().setListaUF(gestorContasVO.getListasVO().getListaArray(0).getDisponivel());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if (!ConstantesCRM.SVAZIO.equals(request.getParameter("nrCPF"))) {
			try {
				GestorContasVO gestorContasVO = GestorContasVO.Factory.newInstance();
				gestorContasVO.setTpOperacao("pesquisarDadosGestor");
				gestorContasVO.addNewFiltros().setNrCPF(request.getParameter("nrCPF"));
				gestorContasVO = relacionarGestor.getGestorContas(user, gestorContasVO);
				if (!gestorContasVO.isSetFiltros()) {
					gestorContasVO.addNewFiltros();
				}
				for (int i = 0; i < gestorContasVO.getGestorVO().getTelefoneVOArray().length; i++) {
					if ("COMERCIAL".equals(gestorContasVO.getGestorVO().getTelefoneVOArray(i).getTpTelefone())) {
						if ("".equals(getGestorContasForm().getNrTelComercial1())) {
							String nrTelefone = gestorContasVO.getGestorVO().getTelefoneVOArray(i).getNrDdd();
							nrTelefone += gestorContasVO.getGestorVO().getTelefoneVOArray(i).getNrTelefone();
							getGestorContasForm().setNrTelComercial1(nrTelefone);
							getGestorContasForm().setNrTelRamal1(gestorContasVO.getGestorVO().getTelefoneVOArray(i).getNrRamal());
						} else {
							String nrTelefone = gestorContasVO.getGestorVO().getTelefoneVOArray(i).getNrDdd();
							nrTelefone += gestorContasVO.getGestorVO().getTelefoneVOArray(i).getNrTelefone();
							getGestorContasForm().setNrTelComercial2(nrTelefone);
							getGestorContasForm().setNrTelRamal2(gestorContasVO.getGestorVO().getTelefoneVOArray(i).getNrRamal());
						}
					}
					if ("CELULAR".equals(gestorContasVO.getGestorVO().getTelefoneVOArray(i).getTpTelefone())) {
						String nrTelefone = gestorContasVO.getGestorVO().getTelefoneVOArray(i).getNrDdd();
						nrTelefone += gestorContasVO.getGestorVO().getTelefoneVOArray(i).getNrTelefone();
						getGestorContasForm().setNrTelCelular(nrTelefone);
					}
				}
				gestorContasVO.getGestorVO().setTelefoneVOArray(new GestorVO.TelefoneVO[0]);
				getGestorContasForm().setContasDisponiveis(GestorContasVO.ListasVO.Lista.Disponivel.Factory.newInstance());
				getGestorContasForm().setContasSelecionadas(gestorContasVO.getListasVO().getListaArray(0).getSelecionado());
				getGestorContasForm().setGestorContasVO(gestorContasVO);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			getGestorContasForm().initGestorContasVO();
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="resultadoPesquisaContas.jsp"
	 */
	public ActionForward getPesquisaContas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		GestorContasForm form = (GestorContasForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			GestorContasVO gestorContasVO = relacionarGestor.getGestorContas(user, form.getGestorContasVO());
			getGestorContasForm().setContasDisponiveis(gestorContasVO.getListasVO().getListaArray(0).getDisponivel());
		} catch (Exception e) {
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * Método utilizado para inclusão, alteração e exclusão de gestor.
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 */
	public ActionForward gravarGestor(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		GestorContasForm form = (GestorContasForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			if (!ConstantesCRM.SVAZIO.equals(form.getNrTelComercial1())) {
				GestorContasVO.GestorVO.TelefoneVO telefoneVO = form.getGestorContasVO().getGestorVO().addNewTelefoneVO();
				telefoneVO.setTpTelefone("COMERCIAL");
				telefoneVO.setNrDdd(form.getNrTelComercial1().substring(0, 2));
				telefoneVO.setNrTelefone(form.getNrTelComercial1().substring(2));
				telefoneVO.setNrRamal(form.getNrTelRamal1());
			}
			if (!ConstantesCRM.SVAZIO.equals(form.getNrTelComercial2())) {
				GestorContasVO.GestorVO.TelefoneVO telefoneVO = form.getGestorContasVO().getGestorVO().addNewTelefoneVO();
				telefoneVO.setTpTelefone("COMERCIAL");
				telefoneVO.setNrDdd(form.getNrTelComercial2().substring(0, 2));
				telefoneVO.setNrTelefone(form.getNrTelComercial2().substring(2));
				telefoneVO.setNrRamal(form.getNrTelRamal2());
			}
			if (!ConstantesCRM.SVAZIO.equals(form.getNrTelCelular())) {
				GestorContasVO.GestorVO.TelefoneVO telefoneVO = form.getGestorContasVO().getGestorVO().addNewTelefoneVO();
				telefoneVO.setTpTelefone("CELULAR");
				telefoneVO.setNrDdd(form.getNrTelCelular().substring(0, 2));
				telefoneVO.setNrTelefone(form.getNrTelCelular().substring(2));
			}
			GestorContasVO.ListasVO.Lista lista = form.getGestorContasVO().addNewListasVO().addNewLista();
			lista.setNmSelect("contas");
			GestorContasVO.ListasVO.Lista.Selecionado selecionado = lista.addNewSelecionado();
			for (int i = 0; i < form.getListaContasSelecionadas().length; i++) {
				selecionado.addNewIt().setId(form.getListaContasSelecionadas()[i]);
			}
			relacionarGestor.setGestorContas(user, form.getGestorContasVO());
		} catch (Exception e) {
			response.setStatus(503, e.toString());
			return null;
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="getPesquisa.do"
	 */
	public ActionForward excluirGestor(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			if (!ConstantesCRM.SVAZIO.equals(request.getParameter("nrCPF"))) {
				GestorContasVO gestorContasVO = GestorContasVO.Factory.newInstance();
				gestorContasVO.setTpOperacao("excluirGestor");
				gestorContasVO.addNewFiltros().setNrCPF(request.getParameter("nrCPF"));
				relacionarGestor.setGestorContas(user, gestorContasVO);
			}
		} catch (Exception e) {
			response.setStatus(503, e.toString());
			return null;
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class GestorContasForm extends ActionForm {

		private static final long serialVersionUID = -2002797657026728434L;

		private GestorContasVO gestorContasVO;
		private GestorContasVO.ListasVO.Lista.Disponivel contasDisponiveis;
		private GestorContasVO.ListasVO.Lista.Selecionado contasSelecionadas;
		private GestorContasVO.ListasVO.Lista.Disponivel listaUF;

		private String[] listaContasDisponiveis;
		private String[] listaContasSelecionadas;

		public GestorContasForm() {
			this.contasDisponiveis = null;
			initGestorContasVO();
		}

		public void initGestorContasVO() {
			this.gestorContasVO = GestorContasVO.Factory.newInstance();
			this.gestorContasVO.addNewFiltros();
			this.gestorContasVO.addNewTabela();
			this.gestorContasVO.addNewGestorVO();
			this.gestorContasVO.getGestorVO().addNewEnderecoVO();
		}

		public GestorContasVO getGestorContasVO() {
			if (this.gestorContasVO == null) {
				initGestorContasVO();
			}
			return this.gestorContasVO;
		}

		public void setGestorContasVO(GestorContasVO gestorContasVO) {
			this.gestorContasVO = gestorContasVO;
		}

		public GestorContasVO.GestorVO.EnderecoVO getEnderecoVO() {
			return this.gestorContasVO.getGestorVO().getEnderecoVO();
		}

		public void setEnderecoVO(GestorContasVO.GestorVO.EnderecoVO enderecoVO) {
			this.gestorContasVO.getGestorVO().setEnderecoVO(enderecoVO);
		}

		public GestorContasVO.ListasVO.Lista.Disponivel getContasDisponiveis() {
			if (this.contasDisponiveis == null) {
				this.contasDisponiveis = GestorContasVO.ListasVO.Lista.Disponivel.Factory.newInstance();
			}
			return this.contasDisponiveis;
		}

		public void setContasDisponiveis(GestorContasVO.ListasVO.Lista.Disponivel contasDisponiveis) {
			this.contasDisponiveis = contasDisponiveis;
		}

		public GestorContasVO.ListasVO.Lista.Disponivel getListaUF() {
			if (this.listaUF == null) {
				this.listaUF = GestorContasVO.ListasVO.Lista.Disponivel.Factory.newInstance();
			}
			return this.listaUF;
		}

		public void setListaUF(GestorContasVO.ListasVO.Lista.Disponivel listaUF) {
			this.listaUF = listaUF;
		}

		public GestorContasVO.ListasVO.Lista.Selecionado getContasSelecionadas() {
			if (this.contasSelecionadas == null) {
				this.contasSelecionadas = GestorContasVO.ListasVO.Lista.Selecionado.Factory.newInstance();
			}
			return this.contasSelecionadas;
		}

		public void setContasSelecionadas(GestorContasVO.ListasVO.Lista.Selecionado contasSelecionadas) {
			this.contasSelecionadas = contasSelecionadas;
		}

		public String[] getListaContasDisponiveis() {
			if (this.listaContasDisponiveis == null) {
				this.listaContasDisponiveis = new String[0];
			}
			return this.listaContasDisponiveis;
		}

		public void setListaContasDisponiveis(String[] listaContasDisponiveis) {
			this.listaContasDisponiveis = listaContasDisponiveis;
		}

		public String[] getListaContasSelecionadas() {
			if (this.listaContasSelecionadas == null) {
				this.listaContasSelecionadas = new String[0];
			}
			return this.listaContasSelecionadas;
		}

		public void setListaContasSelecionadas(String[] listaContasSelecionadas) {
			this.listaContasSelecionadas = listaContasSelecionadas;
		}

		private String nrTelComercial1 = ConstantesCRM.SVAZIO;

		public String getNrTelComercial1() {
			return this.nrTelComercial1;
		}

		public void setNrTelComercial1(String nrTelComercial1) {
			this.nrTelComercial1 = nrTelComercial1;
		}

		private String nrTelRamal1 = ConstantesCRM.SVAZIO;

		public String getNrTelRamal1() {
			return this.nrTelRamal1;
		}

		public void setNrTelRamal1(String nrTelRamal1) {
			this.nrTelRamal1 = nrTelRamal1;
		}

		private String nrTelComercial2 = ConstantesCRM.SVAZIO;

		public String getNrTelComercial2() {
			return this.nrTelComercial2;
		}

		public void setNrTelComercial2(String nrTelComercial2) {
			this.nrTelComercial2 = nrTelComercial2;
		}

		private String nrTelRamal2 = ConstantesCRM.SVAZIO;

		public String getNrTelRamal2() {
			return this.nrTelRamal2;
		}

		public void setNrTelRamal2(String nrTelRamal2) {
			this.nrTelRamal2 = nrTelRamal2;
		}

		private String nrTelCelular = ConstantesCRM.SVAZIO;

		public String getNrTelCelular() {
			return this.nrTelCelular;
		}

		public void setNrTelCelular(String nrTelCelular) {
			this.nrTelCelular = nrTelCelular;
		}
	}
}