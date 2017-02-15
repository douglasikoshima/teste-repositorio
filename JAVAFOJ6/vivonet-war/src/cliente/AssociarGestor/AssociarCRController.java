package cliente.AssociarGestor;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.AssociarCRVODocument.AssociarCRVO;
import br.com.vivo.fo.cliente.vo.GrupoCRIVODocument.GrupoCRIVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class AssociarCRController extends AbstractAction {

	private static final long serialVersionUID = 3860524235502328801L;

	@EJB
	private br.com.vivo.fo.ctrls.cliente.associarGestor.RelacionarGestor relacionarGestorFacade;

	private ConsultorForm consultorForm;
	private GrupoCRIForm grupoCRIForm;

	protected global.Global globalApp = new global.Global();

	private static Logger log = new Logger("clientes");

	private String user = null;

	public void setConsultorForm(ConsultorForm consultorForm) {
		this.consultorForm = consultorForm;
	}

	public ConsultorForm getConsultorForm() {
		return this.consultorForm;
	}

	public void setGrupoCRIForm(GrupoCRIForm grupoCRIForm) {
		this.grupoCRIForm = grupoCRIForm;
	}

	public GrupoCRIForm getGrupoCRIForm() {
		return this.grupoCRIForm;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("montaListas".equals(mapping.getParameter())) {
			return montaListas(mapping, form, request, response);
		} else if ("pesquisaClientes".equals(mapping.getParameter())) {
			return pesquisaClientes(mapping, form, request, response);
		} else if ("inlcuirAlterarRelacionamento".equals(mapping.getParameter())) {
			return inlcuirAlterarRelacionamento(mapping, form, request, response);
		} else if ("salvar".equals(mapping.getParameter())) {
			return salvar(mapping, form, request, response);
		} else if ("beginCRI".equals(mapping.getParameter())) {
			return beginCRI(mapping, form, request, response);
		} else if ("pesquisarCRI".equals(mapping.getParameter())) {
			return pesquisarCRI(mapping, form, request, response);
		} else if ("salvarCRI".equals(mapping.getParameter())) {
			return salvarCRI(mapping, form, request, response);
		} else if ("irPesquisaNome".equals(mapping.getParameter())) {
			return irPesquisaNome(mapping, form, request, response);
		} else if ("limparPesquisa".equals(mapping.getParameter())) {
			return limparPesquisa(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="montaListas.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		user = ConstantesCRM.getCurrentUser(request.getSession());
		// user = (String)request.getSession().getAttribute(ConstantesCRM.USUARIO_IDENTIFICADOR_SESSION);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="keyAccount.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward montaListas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("AssociarCRController:montaListas(" + user + ") >> INICIALIZADO");

			consultorForm = new ConsultorForm();
			// Preenche o conteúdo dos Combos
			consultorForm.setAssociarCR(relacionarGestorFacade.getListasAssociar(user));
			consultorForm.getAssociarCR().addNewFiltroConsulta();
			log.debug("AssociarCRController:montaListas(" + user + ") >> FINALIZADO");

		} catch (Exception e) {
			log.error("AssociarCRController:montaListas(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="resultadoPesquisa.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward pesquisaClientes(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			ConsultorForm form = (ConsultorForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("AssociarCRController:pesquisaClientes(" + user + ") >> INICIALIZADO");

			form.getAssociarCR().getFiltroConsulta().setNmClienteFiltro(form.getAssociarCR().getFiltroConsulta().getNmClienteFiltro().trim());
			consultorForm.getAssociarCR().setFiltroConsulta(form.getAssociarCR().getFiltroConsulta());
			AssociarCRVO pesquisaAssociar = AssociarCRVO.Factory.newInstance();

			if (form.getAssociarCR().getFiltroConsulta().getIdCarteirizacaoFiltro().equals(ConstantesCRM.SZERO)) {
				form.getAssociarCR().getFiltroConsulta().setIdCarteirizacaoFiltro(ConstantesCRM.SVAZIO);
			}
			if (form.getAssociarCR().getFiltroConsulta().getIdConsultorFiltro().equals(ConstantesCRM.SZERO)) {
				form.getAssociarCR().getFiltroConsulta().setIdConsultorFiltro(ConstantesCRM.SVAZIO);
			}
			if (form.getAssociarCR().getFiltroConsulta().getIdSegmentacaoFiltro().equals(ConstantesCRM.SZERO)) {
				form.getAssociarCR().getFiltroConsulta().setIdSegmentacaoFiltro(ConstantesCRM.SVAZIO);
			}
			if (form.getAssociarCR().getFiltroConsulta().getIdTipoDocumentoFiltro().equals(ConstantesCRM.SZERO)) {
				form.getAssociarCR().getFiltroConsulta().setIdTipoDocumentoFiltro(ConstantesCRM.SVAZIO);
			}

			pesquisaAssociar.setFiltroConsulta(form.getAssociarCR().getFiltroConsulta());
			pesquisaAssociar = relacionarGestorFacade.getClientesPesquisa(user, pesquisaAssociar);
			consultorForm.getAssociarCR().setClientesArray(pesquisaAssociar.getClientesArray());
			consultorForm.getAssociarCR().setErro(pesquisaAssociar.getErro());

			log.debug("AssociarCRController:pesquisaClientes(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("AssociarCRController:pesquisaClientes(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/AssociarGestor/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluirRelacionamento.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward inlcuirAlterarRelacionamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			ConsultorForm form = (ConsultorForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("AssociarCRController:inlcuirAlterarRelacionamento(" + user + ") >> INICIALIZADO");

			consultorForm.setClientesSelecionados(form.getClientesSelecionados());
			if (form.getClientesSelecionados().length > 1) { // MÚLTIPLA ESCOLHA
				consultorForm.setIdConsultorSelecionado(ConstantesCRM.SVAZIO);
			} else { // ÚNICO CLIENTE
				for (int i = 0; i < consultorForm.associarCR.getClientesArray().length; i++) {
					if (consultorForm.associarCR.getClientesArray(i).getIdCliente().equalsIgnoreCase(form.getClientesSelecionados()[0])) {
						consultorForm.setIdConsultorSelecionado(consultorForm.getAssociarCR().getClientesArray(i).getIdConsultor());
						i = consultorForm.associarCR.getClientesArray().length + 1;
					}
				}
			}
		} catch (Exception e) {
			log.error("AssociarCRController:inlcuirAlterarRelacionamento(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/AssociarGestor/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		log.debug("AssociarCRController:inlcuirAlterarRelacionamento(" + user + ") >> FINALIZADO");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisaClientes.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			ConsultorForm form = (ConsultorForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("AssociarCRController:salvar(" + user + ") >> INICIALIZADO");

			String idConsultorSelecionado = form.getIdConsultorSelecionado();
			AssociarCRVO salvarAssociar = AssociarCRVO.Factory.newInstance();

			if (consultorForm.getClientesSelecionados()[0] == null) { // EM CASO DE EXCLUSÃO PRECISA REALIZAR SET´s NOS
				// SELECIONADOS
				consultorForm.setClientesSelecionados(form.getClientesSelecionados());
			}

			for (int i = 0; i < consultorForm.getClientesSelecionados().length; i++) {
				salvarAssociar.addNewClientes();
				salvarAssociar.getClientesArray(i).setIdCliente(consultorForm.getClientesSelecionados()[i]);
			}

			if (request.getParameter("exlcuirCR") != null && request.getParameter("exlcuirCR").equalsIgnoreCase(ConstantesCRM.STRUE)) { // ELIMINAR
				// ASSOCIAÇÃO
				log.debug("AssociarCRController:salvar(" + user + ") >> idConsultorSelecionado = NULL");
				idConsultorSelecionado = null;
			}

			relacionarGestorFacade.setGravarAssociar(user, salvarAssociar, idConsultorSelecionado);
			consultorForm.setClientesSelecionados(null);

		} catch (Exception e) {
			log.error("AssociarCRController:salvar(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/AssociarGestor/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		log.debug("AssociarCRController:salvar(" + user + ") >> FINALIZADO");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="consultorRelacionamento.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward beginCRI(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("AssociarCRController:beginCRI(" + user + ") >> INICIALIZADO");
			grupoCRIForm = new GrupoCRIForm();
			GrupoCRIVO grupo = GrupoCRIVO.Factory.newInstance();
			grupo = relacionarGestorFacade.getGruposCRI(user);
			grupoCRIForm.getGrupoCRI().setGruposArray(grupo.getGruposArray());

		} catch (Exception e) {
			log.error("AssociarCRController:beginCRI(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/AssociarGestor/beginCRI.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		log.debug("AssociarCRController:beginCRI(" + user + ") >> FINALIZADO");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="linhas" path="resultadoPesquisaCliente.jsp"
	 * @jpf:forward name="clientes" path="listaClientes.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward pesquisarCRI(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			GrupoCRIForm form = (GrupoCRIForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("AssociarCRController:pesquisarCRI(" + user + ") >> INICIALIZADO");

			String destino = ConstantesCRM.SVAZIO;
			GrupoCRIVO pesquisa = GrupoCRIVO.Factory.newInstance();

			if (form.getGrupoCRI().getPesquisa().getFiltro().getSgTipoPesquisa() != null) {
				grupoCRIForm.getGrupoCRI().setPesquisa(form.getGrupoCRI().getPesquisa());
			}
			pesquisa = relacionarGestorFacade.getPesquisaGruposCRI(user, grupoCRIForm.getGrupoCRI());
			if (grupoCRIForm.getGrupoCRI().getPesquisa().getFiltro().getSgTipoPesquisa().equalsIgnoreCase("CELULAR") || grupoCRIForm.getGrupoCRI().getPesquisa().getFiltro().getSgTipoPesquisa().equalsIgnoreCase("CONTA") || grupoCRIForm.getGrupoCRI().getPesquisa().getFiltro().getSgTipoPesquisa().equalsIgnoreCase("CLIENTE")) {
				grupoCRIForm.getGrupoCRI().setLinhasArray(pesquisa.getLinhasArray());
				if (pesquisa.getLinhasArray().length < 1) {
					grupoCRIForm.getGrupoCRI().getPesquisa().setInErro(ConstantesCRM.SONE);
				} else {
					grupoCRIForm.getGrupoCRI().getPesquisa().setInErro(ConstantesCRM.SZERO);
				}
				log.debug("AssociarCRController:pesquisarCRI(" + user + ") >> DESTINO = Linhas");
				destino = "linhas";

			} else {
				if (pesquisa.getPesquisa().getInErro() != null) {
					log.error("AssociarCRController:pesquisarCRI(" + user + ") >> Erro na pesquisa");
					pesquisa.getPesquisa().setInErro(null);
					grupoCRIForm.getGrupoCRI().getPesquisa().setInErro(ConstantesCRM.SONE);
					GrupoCRIVO novo = GrupoCRIVO.Factory.newInstance();
					novo.addNewPesquisa();
					pesquisa.getPesquisa().setPessoasArray(novo.getPesquisa().getPessoasArray());
				}
				grupoCRIForm.getGrupoCRI().getPesquisa().setPessoasArray(pesquisa.getPesquisa().getPessoasArray());
				destino = "clientes";
			}

			log.debug("AssociarCRController:pesquisarCRI(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);

		} catch (Exception e) {
			log.error("AssociarCRController:pesquisarCRI(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/AssociarGestor/beginCRI.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="error" path="/error.jsp"
	 * @jpf:forward name="success" path="pesquisarCRI.do"
	 */
	public ActionForward salvarCRI(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			GrupoCRIForm form = (GrupoCRIForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("AssociarCRController:salvarCRI(" + user + ") >> INICIALIZADO");
			GrupoCRIVO gravar = GrupoCRIVO.Factory.newInstance();
			for (int i = 0; i < form.getClientesSelecionados().length; i++) {
				gravar.addNewLinhas();
				gravar.getLinhasArray(i).setIdGrupoAssociado(form.getIdGrupoSelecionado());
				gravar.getLinhasArray(i).setIdLinha(form.getClientesSelecionados()[i]);
			}
			relacionarGestorFacade.setGravarGruposCRI(user, gravar);
			log.debug("AssociarCRController:salvarCRI(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("AssociarCRController:salvarCRI(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/AssociarGestor/beginCRI.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="popupPesquisaCliente.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward irPesquisaNome(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("AssociarCRController:irPesquisaNome(" + user + ") >> INICIALIZADO");
			grupoCRIForm.getGrupoCRI().getPesquisa().getFiltro().setSgTipoPesquisa(request.getParameter("pesquisa"));
			log.debug("AssociarCRController:irPesquisaNome(" + user + ") >> Tipo de pesquisa: " + request.getParameter("pesquisa").toString());

		} catch (Exception e) {
			log.error("AssociarCRController:irPesquisaNome(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/AssociarGestor/beginCRI.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		log.debug("AssociarCRController:irPesquisaNome(" + user + ") >> FINALIZADO");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="nulo.jsp"
	 */
	public ActionForward limparPesquisa(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AssociarCRController:limparPesquisa(" + user + ") >> INICIALIZADO");

		GrupoCRIVO limpo = GrupoCRIVO.Factory.newInstance();
		limpo.setGruposArray(grupoCRIForm.getGrupoCRI().getGruposArray());
		grupoCRIForm.setGrupoCRI(limpo);

		log.debug("AssociarCRController:limparPesquisa(" + user + ") >> FINALIZADO");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class ConsultorForm extends ActionForm {

		private static final long serialVersionUID = -9156945092093596679L;

		private String[] clientesSelecionados;
		private String idConsultorSelecionado = new String();
		private AssociarCRVO associarCR;

		public ConsultorForm() {
			associarCR = AssociarCRVO.Factory.newInstance();
			associarCR.addNewCarteirizacoes();
			associarCR.addNewClientes();
			associarCR.addNewConsultorRelacionamento();
			associarCR.addNewFiltroConsulta();
			associarCR.addNewSegmentacoes();
			associarCR.addNewTipoDocumento();
		}

		public void setAssociarCR(AssociarCRVO associarCR) {
			this.associarCR = associarCR;
		}

		public AssociarCRVO getAssociarCR() {
			return this.associarCR;
		}

		public void setIdConsultorSelecionado(String idConsultorSelecionado) {
			this.idConsultorSelecionado = idConsultorSelecionado;
		}

		public String getIdConsultorSelecionado() {
			return this.idConsultorSelecionado;
		}

		public void setClientesSelecionados(String[] clientesSelecionados) {
			this.clientesSelecionados = clientesSelecionados;
		}

		public String[] getClientesSelecionados() {
			if (this.clientesSelecionados == null || this.clientesSelecionados.length == 0) {
				this.clientesSelecionados = new String[1];
			}

			return this.clientesSelecionados;
		}
	}

	public static class GrupoCRIForm extends ActionForm {

		private static final long serialVersionUID = 3345930700835559006L;

		private String[] clientesSelecionados;
		private String inErro = ConstantesCRM.SVAZIO;
		private String idGrupoSelecionado = ConstantesCRM.SVAZIO;
		private GrupoCRIVO grupoCRI;

		public GrupoCRIForm() {
			grupoCRI = GrupoCRIVO.Factory.newInstance();
			grupoCRI.addNewPesquisa();
			grupoCRI.getPesquisa().addNewFiltro();
		}

		public void setGrupoCRI(GrupoCRIVO grupoCRI) {
			this.grupoCRI = grupoCRI;
		}

		public GrupoCRIVO getGrupoCRI() {
			return this.grupoCRI;
		}

		public void setInErro(String inErro) {
			this.inErro = inErro;
		}

		public String getInErro() {
			return this.inErro;
		}

		public void setIdGrupoSelecionado(String idGrupoSelecionado) {
			this.idGrupoSelecionado = idGrupoSelecionado;
		}

		public String getIdGrupoSelecionado() {
			return this.idGrupoSelecionado;
		}

		public void setClientesSelecionados(String[] clientesSelecionados) {
			this.clientesSelecionados = clientesSelecionados;
		}

		public String[] getClientesSelecionados() {
			if (this.clientesSelecionados == null || this.clientesSelecionados.length == 0) {
				this.clientesSelecionados = new String[1];
			}
			return this.clientesSelecionados;
		}
	}
}