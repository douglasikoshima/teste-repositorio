package cliente.ConsultorRelacionamento;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.ConsultorRelacionamentoVODocument.ConsultorRelacionamentoVO;
import br.com.vivo.fo.cliente.vo.ConsultorRelacionamentoVODocument.ConsultorRelacionamentoVO.Lista.Disponivel;
import br.com.vivo.fo.cliente.vo.ConsultorRelacionamentoVODocument.ConsultorRelacionamentoVO.Lista.Selecionado;
import br.com.vivo.fo.commons.vo.IDValorRelacionamentoVODocument.IDValorRelacionamentoVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import cliente.URLErro;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ConsultorRelacionamentoController extends AbstractAction {

	private static final long serialVersionUID = 5226400326139399403L;

	protected global.Global globalApp = new global.Global();

	private transient Logger log = new Logger("vol");

	@EJB
	private br.com.vivo.fo.ctrls.cliente.associarGestor.RelacionarGestor associarGestorFacade;

	private ConsultorRelacionamentoForm consultorRelacionamentoForm;

	public ConsultorRelacionamentoForm getConsultorRelacionamentoForm() {
		if (this.consultorRelacionamentoForm == null) {
			this.consultorRelacionamentoForm = new ConsultorRelacionamentoForm();
		}
		return this.consultorRelacionamentoForm;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("loadConsultorCliente".equals(mapping.getParameter())) {
			return loadConsultorCliente(mapping, form, request, response);
		} else if ("loadClienteConsultor".equals(mapping.getParameter())) {
			return loadClienteConsultor(mapping, form, request, response);
		} else if ("getPesquisa".equals(mapping.getParameter())) {
			return getPesquisa(mapping, form, request, response);
		} else if ("setGestorRelacionamento".equals(mapping.getParameter())) {
			return setGestorRelacionamento(mapping, form, request, response);
		} else if ("gravarGestorRelacionamento".equals(mapping.getParameter())) {
			return gravarGestorRelacionamento(mapping, form, request, response);
		} else if ("gravarClienteGestorRelacionamento".equals(mapping.getParameter())) {
			return gravarClienteGestorRelacionamento(mapping, form, request, response);
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
			this.consultorRelacionamentoForm = new ConsultorRelacionamentoForm();
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaConsultorCliente.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadConsultorCliente(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			this.consultorRelacionamentoForm = new ConsultorRelacionamentoForm();
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaClienteConsultor.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadClienteConsultor(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			this.consultorRelacionamentoForm = new ConsultorRelacionamentoForm();
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="resultadoPesquisa.jsp"
	 */
	@SuppressWarnings("deprecation")
	public ActionForward getPesquisa(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		ConsultorRelacionamentoForm form = (ConsultorRelacionamentoForm) formParam;
		log.debug("[ConsultorRelacionamentoController.getPesquisa]pesquisa " + form);

		try {
			String user = ConstantesCRM.getCurrentUser(request.getSession());
			ConsultorRelacionamentoVO pesquisa = form.getConsultorRelacionamento();
			pesquisa.setTpOperacao(form.getTpOperacao());
			if ("pesquisarConsultorVO".equals(form.getTpOperacao())) {
				if (pesquisa.isSetClienteVO()) {
					pesquisa.unsetClienteVO();
				}

			} else if ("pesquisarClienteVO".equals(form.getTpOperacao())) {
				pesquisa.setInAssociado(form.getInAssociado());
				if (pesquisa.isSetConsultorVO()) {
					pesquisa.unsetConsultorVO();
				}

			} else if ("pesquisarClienteVOPorIDConsultor".equals(form.getTpOperacao())) {
				if (pesquisa.isSetConsultorVO()) {
					pesquisa.unsetConsultorVO();
				}

				if (pesquisa.isSetClienteVO()) {
					pesquisa.unsetClienteVO();
				}
				pesquisa.addNewConsultorVO().setIdConsultor(form.getIdConsultor());

			} else if ("pesquisarConsultorVOPorIDCliente".equals(form.getTpOperacao())) {
				if (pesquisa.isSetConsultorVO()) {
					pesquisa.unsetConsultorVO();
				}

				if (pesquisa.isSetClienteVO()) {
					pesquisa.unsetClienteVO();
				}
				pesquisa.addNewClienteVO().setNrCNPJ(form.getNrDocumentoSelecionado());
			}
			log.debug("[ConsultorRelacionamentoController.getPesquisa]pesquisa " + pesquisa);

			// Aqui faz-se a chamada ao Tuxedo
			pesquisa = associarGestorFacade.getConsultorRelacionamentoVO(user, pesquisa);
			log.debug("[ConsultorRelacionamentoController.getPesquisa]pesquisa " + pesquisa);
			getConsultorRelacionamentoForm().setTpOperacao(form.getTpOperacao());

			if ("pesquisarConsultorVO".equals(form.getConsultorRelacionamento().getTpOperacao())) {
				if ("ConsultorVO".equals(pesquisa.getListaArray(0).getNmSelect().toString())) {
					getConsultorRelacionamentoForm().setConsultoresDisponiveis(pesquisa.getListaArray(0).getDisponivel());
				}
			} else if ("pesquisarClienteVO".equals(form.getConsultorRelacionamento().getTpOperacao())) {
				if ("ClienteVO".equals(pesquisa.getListaArray(0).getNmSelect().toString())) {
					getConsultorRelacionamentoForm().setContasDisponiveis(pesquisa.getListaArray(0).getDisponivel());
				}
			} else if ("pesquisarClienteVOPorIDConsultor".equals(form.getConsultorRelacionamento().getTpOperacao())) {
				if ("ClienteVO".equals(pesquisa.getListaArray(0).getNmSelect().toString())) {
					getConsultorRelacionamentoForm().setContasSelecionadas(pesquisa.getListaArray(0).getSelecionado());
				}
			} else if ("pesquisarConsultorVOPorIDCliente".equals(form.getConsultorRelacionamento().getTpOperacao())) {
				if ("ConsultorVO".equals(pesquisa.getListaArray(0).getNmSelect().toString())) {
					Selecionado consultoresSelecionado = pesquisa.getListaArray(0).getSelecionado();
					getConsultorRelacionamentoForm().setConsultoresSelecionados(consultoresSelecionado);
					obterConsultoresRelacionamento(consultoresSelecionado);
				}
			}
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			response.setStatus(406, e.toString());
			return null;
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="mensagem" path="resultadoGravacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward setGestorRelacionamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		ConsultorRelacionamentoForm form = (ConsultorRelacionamentoForm) formParam;
		log.debug("[ConsultorRelacionamentoController.setGestorRelacionamento] form " + form);
		try {

			ConsultorRelacionamentoVO dados = form.getConsultorRelacionamento();
			if (dados.isSetClienteVO()) {
				dados.unsetClienteVO();
			}
			if (dados.isSetConsultorVO()) {
				dados.unsetConsultorVO();
			}

			dados.addNewLista().addNewSelecionado();
			if (form.getListaContasSelecionadas().length > 0) {
				for (int i = 0; i < form.getListaContasSelecionadas().length; i++) {
					log.debug("[ConsultorRelacionamentoController.setGestorRelacionamento] form.getListaContasSelecionadas()[" + i + "] " + form.getListaContasSelecionadas()[i]);
					dados.getListaArray(0).getSelecionado().addNewIDValorRelacionamentoVO().setId(form.getListaContasSelecionadas()[i]);
					dados.getListaArray(0).getSelecionado().getIDValorRelacionamentoVOArray(i).setIdRelacionamento(form.getNivelConsultorAlterar());
				}
			} else {

				String idConsultor = ConstantesCRM.SVAZIO;
				for (int i = 0; i < form.getListaContasDisponiveis().length; i++) {
					dados.getListaArray(0).getSelecionado().addNewIDValorRelacionamentoVO().setId(form.getListaContasDisponiveis()[i]);
					dados.getListaArray(0).getSelecionado().getIDValorRelacionamentoVOArray(i).setIdRelacionamento(form.getNivelConsultorAlterar());
				}

				for (int i = 0; i < form.getListaConsultoresRelacionamento1().length; i++) {
					idConsultor = form.getListaConsultoresRelacionamento1()[i];
				}

				for (int i = 0; i < form.getListaConsultoresRelacionamento2().length; i++) {
					idConsultor = form.getListaConsultoresRelacionamento2()[i];
				}

				log.debug("[ConsultorRelacionamentoController.setGestorRelacionamento] idConsultor " + idConsultor);
				form.setIdConsultor(idConsultor);
			}

			dados.setTpOperacao(form.getTpOperacao());
			dados.addNewConsultorVO().setIdConsultor(form.getIdConsultor());

			String user = ConstantesCRM.getCurrentUser(request.getSession());
			ConsultorRelacionamentoVO retornoVO = associarGestorFacade.setConsultorRelacionamentoVO(user, dados);
			log.debug("[ConsultorRelacionamentoController.setGestorRelacionamento] retornoVO" + retornoVO);

			if (retornoVO != null) {
				request.setAttribute("msgRetorno", retornoVO.getMsgRetorno());
				getConsultorRelacionamentoForm().setMostrarMensagemErro(ConstantesCRM.SONE);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("mensagem");

			} else {
				request.setAttribute("msgRetorno", "Operação realizada com sucesso!");
				getConsultorRelacionamentoForm().setMostrarMensagemErro(ConstantesCRM.SZERO);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("mensagem");
			}
		} catch (TuxedoWarningException te ) {
			log.error("[ConsultorRelacionamentoController.setGestorRelacionamento] TuxedoWarningException " + te.getMessage());
			request.setAttribute("msgRetorno", te.getXmlHeader().getStatusText());
			getConsultorRelacionamentoForm().setMostrarMensagemErro(ConstantesCRM.SONE);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("mensagem");
			
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, ConstantesCRM.TELAINCIAL);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="mensagem" path="resultadoGravacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward gravarGestorRelacionamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		ConsultorRelacionamentoForm form = (ConsultorRelacionamentoForm) formParam;
		log.debug("[ConsultorRelacionamentoController.gravarGestorRelacionamento] form " + form);
		try {
			ConsultorRelacionamentoVO dados = form.getConsultorRelacionamento();
			if (dados.isSetClienteVO()) {
				dados.unsetClienteVO();
			}
			if (dados.isSetConsultorVO()) {
				dados.unsetConsultorVO();
			}

			dados.addNewLista().addNewSelecionado();
			log.debug("[ConsultorRelacionamentoController.gravarGestorRelacionamento] form.getListaCnpjSelecionados()" + form.getListaCnpjSelecionados().length);
			for (int i = 0; i < form.getListaCnpjSelecionados().length; i++) {
				dados.getListaArray(0).getSelecionado().addNewIDValorRelacionamentoVO().setId(form.getListaCnpjSelecionados()[i]);
				dados.getListaArray(0).getSelecionado().getIDValorRelacionamentoVOArray(i).setIdRelacionamento(form.getNivelConsultorGravar());
			}

			dados.setTpOperacao(form.getTpOperacao());
			dados.addNewConsultorVO().setIdConsultor(form.getIdConsultorRelacionamentoGravar());

			String user = ConstantesCRM.getCurrentUser(request.getSession());
			ConsultorRelacionamentoVO retornoVO = associarGestorFacade.setConsultorRelacionamentoVO(user, dados);
			log.debug("[ConsultorRelacionamentoController.gravarClienteGestorRelacionamento] retornoVO " + retornoVO);

			if (retornoVO != null) {
				request.setAttribute("msgRetorno", retornoVO.getMsgRetorno());
				getConsultorRelacionamentoForm().setMostrarMensagemErro(ConstantesCRM.SONE);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("mensagem");

			} else {
				request.setAttribute("msgRetorno", "Operação realizada com sucesso!");
				getConsultorRelacionamentoForm().setMostrarMensagemErro(ConstantesCRM.SZERO);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("mensagem");
			}

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, ConstantesCRM.TELAINCIAL);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="mensagem" path="resultadoGravacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward gravarClienteGestorRelacionamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		ConsultorRelacionamentoForm form = (ConsultorRelacionamentoForm) formParam;
		log.debug("[ConsultorRelacionamentoController.gravarClienteGestorRelacionamento] form " + form);
		try {
			ConsultorRelacionamentoVO dados = form.getConsultorRelacionamento();
			if (dados.isSetClienteVO()) {
				dados.unsetClienteVO();
			}
			if (dados.isSetConsultorVO()) {
				dados.unsetConsultorVO();
			}

			dados.addNewLista().addNewSelecionado();
			for (int i = 0; i < form.getListaContasDisponiveis().length; i++) {
				dados.getListaArray(0).getSelecionado().addNewIDValorRelacionamentoVO().setId(form.getListaContasDisponiveis()[i]);
				dados.getListaArray(0).getSelecionado().getIDValorRelacionamentoVOArray(i).setIdRelacionamento(form.getNivelConsultorGravar());
			}

			dados.setTpOperacao(form.getTpOperacao());
			dados.addNewConsultorVO().setIdConsultor(form.getIdConsultor());

			String user = ConstantesCRM.getCurrentUser(request.getSession());
			ConsultorRelacionamentoVO retornoVO = associarGestorFacade.setConsultorRelacionamentoVO(user, dados);
			log.debug("[ConsultorRelacionamentoController.gravarClienteGestorRelacionamento] retornoVO " + retornoVO);

			if (retornoVO != null) {
				request.setAttribute("msgRetorno", retornoVO.getMsgRetorno());
				getConsultorRelacionamentoForm().setMostrarMensagemErro(ConstantesCRM.SONE);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("mensagem");

			} else {
				request.setAttribute("msgRetorno", "Operação realizada com sucesso!");
				getConsultorRelacionamentoForm().setMostrarMensagemErro(ConstantesCRM.SZERO);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("mensagem");
			}

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, ConstantesCRM.TELAINCIAL);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * Obtem separadamente os ids e nomes dos consultores de relacionamento
	 * 
	 * @return void
	 * @param Selecionado
	 */
	private void obterConsultoresRelacionamento(Selecionado consultoresSelecionado) {

		getConsultorRelacionamentoForm().setIdConsultorRelacionamento1(ConstantesCRM.SVAZIO);
		getConsultorRelacionamentoForm().setNomeConsultorRelacionamento1(ConstantesCRM.SVAZIO);
		getConsultorRelacionamentoForm().setIdConsultorRelacionamento2(ConstantesCRM.SVAZIO);
		getConsultorRelacionamentoForm().setNomeConsultorRelacionamento2(ConstantesCRM.SVAZIO);
		if (consultoresSelecionado != null) {
			/* Obtem separadamente os ids e nome dos Consultores */
			for (int i = 0; i < consultoresSelecionado.getIDValorRelacionamentoVOArray().length; i++) {
				IDValorRelacionamentoVO idValorRelacionamentoVO = consultoresSelecionado.getIDValorRelacionamentoVOArray(i);
				if ("R".equals(idValorRelacionamentoVO.getIdRelacionamento())) {
					getConsultorRelacionamentoForm().setIdConsultorRelacionamento1(idValorRelacionamentoVO.getId());
					getConsultorRelacionamentoForm().setNomeConsultorRelacionamento1(idValorRelacionamentoVO.getValor());
				}
				if ("R2".equals(idValorRelacionamentoVO.getIdRelacionamento())) {
					getConsultorRelacionamentoForm().setIdConsultorRelacionamento2(idValorRelacionamentoVO.getId());
					getConsultorRelacionamentoForm().setNomeConsultorRelacionamento2(idValorRelacionamentoVO.getValor());
				}
			}
		}
		log.debug("[ConsultorRelacionamentoController.obterConsultoresRelacionamento]Consultor 1 " + getConsultorRelacionamentoForm().getIdConsultorRelacionamento1() + " " + getConsultorRelacionamentoForm().getNomeConsultorRelacionamento1());
		log.debug("[ConsultorRelacionamentoController.obterConsultoresRelacionamento]Consultor 2 " + getConsultorRelacionamentoForm().getIdConsultorRelacionamento2() + " " + getConsultorRelacionamentoForm().getNomeConsultorRelacionamento2());
	}

	public static class ConsultorRelacionamentoForm extends ActionForm {

		private static final long serialVersionUID = 5430740383907950116L;

		private String tpOperacao;

		private ConsultorRelacionamentoVO consultorRelacionamento;
		private Disponivel consultoresDisponiveis;
		private Disponivel contasDisponiveis;
		private Selecionado consultoresSelecionados;
		private Selecionado contasSelecionadas;

		private String idConsultor;
		private String[] listaConsultoresSelecionados;
		private String[] listaContasDisponiveis;
		private String[] listaContasSelecionadas;
		private String[] listaCnpjSelecionados;

		private String nrCNPJ;
		private String nivelConsultorAlterar;
		private String nivelConsultorGravar;
		private String paginaOrigem;

		private String[] listaConsultoresRelacionamento1;
		private String[] listaConsultoresRelacionamento2;

		private String inAssociado;
		private String idConsultorRelacionamentoGravar;
		private String msgRetorno;
		private String nrDocumentoSelecionado;

		private String idConsultorRelacionamento1;
		private String idConsultorRelacionamento2;
		private String nomeConsultorRelacionamento1;
		private String nomeConsultorRelacionamento2;

		private String mostrarMensagemErro = "0";

		public ConsultorRelacionamentoForm() {
			this.consultorRelacionamento = ConsultorRelacionamentoVO.Factory.newInstance();
			this.consultorRelacionamento.addNewConsultorVO();
			this.consultorRelacionamento.addNewClienteVO();
		}

		public String getTpOperacao() {
			if (this.tpOperacao == null) {
				this.tpOperacao = ConstantesCRM.SVAZIO;
			}
			return this.tpOperacao;
		}

		public void setTpOperacao(String arg1) {
			this.tpOperacao = arg1;
		}

		public ConsultorRelacionamentoVO getConsultorRelacionamento() {
			if (this.consultorRelacionamento == null) {
				this.consultorRelacionamento = ConsultorRelacionamentoVO.Factory.newInstance();
			}
			return this.consultorRelacionamento;
		}

		public void setConsultorRelacionamento(ConsultorRelacionamentoVO consultorRelacionamento) {
			this.consultorRelacionamento = consultorRelacionamento;
		}

		public Disponivel getConsultoresDisponiveis() {
			if (this.consultoresDisponiveis == null) {
				this.consultoresDisponiveis = Disponivel.Factory.newInstance();
			}
			return this.consultoresDisponiveis;
		}

		public void setConsultoresDisponiveis(Disponivel consultoresDisponiveis) {
			this.consultoresDisponiveis = consultoresDisponiveis;
		}

		public Disponivel getContasDisponiveis() {
			if (this.contasDisponiveis == null) {
				this.contasDisponiveis = Disponivel.Factory.newInstance();
			}
			return this.contasDisponiveis;
		}

		public void setContasDisponiveis(Disponivel contasDisponiveis) {
			this.contasDisponiveis = contasDisponiveis;
		}

		public Selecionado getConsultoresSelecionados() {
			if (this.consultoresSelecionados == null) {
				this.consultoresSelecionados = Selecionado.Factory.newInstance();
			}
			return this.consultoresSelecionados;
		}

		public void setConsultoresSelecionados(Selecionado consultoresSelecionados) {
			this.consultoresSelecionados = consultoresSelecionados;
		}

		public Selecionado getContasSelecionadas() {
			if (this.contasSelecionadas == null) {
				this.contasSelecionadas = Selecionado.Factory.newInstance();
			}
			return this.contasSelecionadas;
		}

		public void setContasSelecionadas(Selecionado contasSelecionadas) {
			this.contasSelecionadas = contasSelecionadas;
		}

		public String getIdConsultor() {
			if (this.idConsultor == null) {
				this.idConsultor = ConstantesCRM.SVAZIO;
			}
			return this.idConsultor;
		}

		public void setIdConsultor(String arg1) {
			this.idConsultor = arg1;
		}

		public String[] getListaConsultoresSelecionados() {
			if (this.listaConsultoresSelecionados == null) {
				this.listaConsultoresSelecionados = new String[0];
			}
			return this.listaConsultoresSelecionados;
		}

		public void setListaConsultoresSelecionados(String[] listaConsultoresSelecionados) {
			this.listaConsultoresSelecionados = listaConsultoresSelecionados;
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

		public String[] getListaCnpjSelecionados() {
			if (this.listaCnpjSelecionados == null) {
				this.listaCnpjSelecionados = new String[0];
			}
			return this.listaCnpjSelecionados;
		}

		public void setListaCnpjSelecionados(String[] listaCnpjSelecionados) {
			this.listaCnpjSelecionados = listaCnpjSelecionados;
		}

		public String getNrCNPJ() {
			if (this.nrCNPJ == null) {
				this.nrCNPJ = ConstantesCRM.SVAZIO;
			}
			return this.nrCNPJ;
		}

		public void setNrCNPJ(String nrCNPJ) {
			this.nrCNPJ = nrCNPJ;
		}

		public String getNivelConsultorAlterar() {
			return this.nivelConsultorAlterar;
		}

		public void setNivelConsultorAlterar(String nivelConsultorAlterar) {
			this.nivelConsultorAlterar = nivelConsultorAlterar;
		}

		public String getPaginaOrigem() {
			return this.paginaOrigem;
		}

		public void setPaginaOrigem(String paginaOrigem) {
			this.paginaOrigem = paginaOrigem;
		}

		public String[] getListaConsultoresRelacionamento1() {
			if (this.listaConsultoresRelacionamento1 == null) {
				this.listaConsultoresRelacionamento1 = new String[0];
			}
			return this.listaConsultoresRelacionamento1;
		}

		public void setListaConsultoresRelacionamento1(String[] listaConsultoresRelacionamento1) {
			this.listaConsultoresRelacionamento1 = listaConsultoresRelacionamento1;
		}

		public String[] getListaConsultoresRelacionamento2() {
			if (this.listaConsultoresRelacionamento2 == null) {
				this.listaConsultoresRelacionamento2 = new String[0];
			}
			return this.listaConsultoresRelacionamento2;
		}

		public void setListaConsultoresRelacionamento2(String[] listaConsultoresRelacionamento2) {
			this.listaConsultoresRelacionamento2 = listaConsultoresRelacionamento2;
		}

		public String getInAssociado() {
			return this.inAssociado;
		}

		public void setInAssociado(String inAssociado) {
			this.inAssociado = inAssociado;
		}

		public String getNivelConsultorGravar() {
			return this.nivelConsultorGravar;
		}

		public void setNivelConsultorGravar(String nivelConsultorGravar) {
			this.nivelConsultorGravar = nivelConsultorGravar;
		}

		public String getIdConsultorRelacionamentoGravar() {
			return this.idConsultorRelacionamentoGravar;
		}

		public void setIdConsultorRelacionamentoGravar(String idConsultorRelacionamentoGravar) {
			this.idConsultorRelacionamentoGravar = idConsultorRelacionamentoGravar;
		}

		public String getMsgRetorno() {
			return this.msgRetorno != null ? msgRetorno : ConstantesCRM.SVAZIO;
		}

		public void setMsgRetorno(String msgRetorno) {
			this.msgRetorno = msgRetorno;
		}

		public String getNrDocumentoSelecionado() {
			return this.nrDocumentoSelecionado;
		}

		public void setNrDocumentoSelecionado(String nrDocumentoSelecionado) {
			this.nrDocumentoSelecionado = nrDocumentoSelecionado;
		}

		public String getIdConsultorRelacionamento1() {
			return this.idConsultorRelacionamento1;
		}

		public void setIdConsultorRelacionamento1(String idConsultorRelacionamento1) {
			this.idConsultorRelacionamento1 = idConsultorRelacionamento1;
		}

		public String getNomeConsultorRelacionamento1() {
			return this.nomeConsultorRelacionamento1;
		}

		public void setNomeConsultorRelacionamento1(String nomeConsultorRelacionamento1) {
			this.nomeConsultorRelacionamento1 = nomeConsultorRelacionamento1;
		}

		public String getIdConsultorRelacionamento2() {
			return this.idConsultorRelacionamento2;
		}

		public void setIdConsultorRelacionamento2(String idConsultorRelacionamento2) {
			this.idConsultorRelacionamento2 = idConsultorRelacionamento2;
		}

		public String getNomeConsultorRelacionamento2() {
			return this.nomeConsultorRelacionamento2;
		}

		public void setNomeConsultorRelacionamento2(String nomeConsultorRelacionamento2) {
			this.nomeConsultorRelacionamento2 = nomeConsultorRelacionamento2;
		}

		public String getMostrarMensagemErro() {
			return this.mostrarMensagemErro;
		}

		public void setMostrarMensagemErro(String mostrarMensagemErro) {
			this.mostrarMensagemErro = mostrarMensagemErro;
		}

		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			sb.append(" tpOperacao=").append(tpOperacao);
			sb.append(" idConsultor=").append(idConsultor);
			sb.append(" nrCNPJ=").append(nrCNPJ);
			sb.append(" nivelConsultorAlterar=").append(nivelConsultorAlterar);
			sb.append(" nivelConsultorGravar=").append(nivelConsultorGravar);
			sb.append(" paginaOrigem=").append(paginaOrigem);
			sb.append(" inAssociado=").append(inAssociado);
			sb.append(" nrDocumentoSelecionado=").append(nrDocumentoSelecionado);
			sb.append(" listaConsultorRelacionamento1[0]=").append(getListaConsultoresRelacionamento1());
			sb.append(" listaConsultorRelacionamento2[0]=").append(getListaConsultoresRelacionamento2());
			sb.append(" msgRetorno=").append(msgRetorno);
			sb.append(" mostrarMensagemErro=").append(mostrarMensagemErro);
			sb.append(" ]");
			return sb.toString();
		}
	}
}