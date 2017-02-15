package VOLTAV.manterTerminal;

import java.util.StringTokenizer;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.MunicipioVODocument.MunicipioVO;
import br.com.vivo.fo.cliente.vo.EnderecoVODocument.EnderecoVO;
import br.com.vivo.fo.cliente.vo.ListaLojaVODocument.ListaLojaVO;
import br.com.vivo.fo.cliente.vo.ListaMunicipioVODocument.ListaMunicipioVO;
import br.com.vivo.fo.cliente.vo.LojaVODocument.LojaVO;
import br.com.vivo.fo.cliente.vo.PesquisaEnderecoVODocument.PesquisaEnderecoVO;
import br.com.vivo.fo.cliente.vo.UFVODocument.UFVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.VOLTAV.manterTerminal.ManterTerminalFacade;
import br.com.vivo.fo.ctrls.cliente.telaInicial.TelaInicialFacade;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.fidelizacao.vo.CorVODocument.CorVO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.voltav.vo.ListaTerminaisVODocument.ListaTerminaisVO;
import br.com.vivo.fo.voltav.vo.ManterTerminalVODocument.ManterTerminalVO;
import br.com.vivo.fo.voltav.vo.TerminalVODocument.TerminalVO;
import br.com.vivo.fo.voltav.vo.VOLTAVUFOperadoraVODocument.VOLTAVUFOperadoraVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ManterTerminalController extends AbstractAction {

	private static final long serialVersionUID = 2003542579393527769L;

	protected global.Global globalApp = new global.Global();

	@EJB
	private ManterTerminalFacade terminalFacade;

	@EJB
	private TelaInicialFacade telaInicialFacade;

	private static Logger log = new Logger("prepago");

	private CadastroTerminalForm cadastroTerminalForm;

	public CadastroTerminalForm getCadastroTerminalForm() {
		if (this.cadastroTerminalForm == null) {
			this.cadastroTerminalForm = new CadastroTerminalForm();
			this.cadastroTerminalForm.setComboMunicipio(ListaMunicipioVO.Factory.newInstance().getMunicipioVOArray());
			this.cadastroTerminalForm.setComboLoja(ListaLojaVO.Factory.newInstance().getLojaVOArray());
			this.cadastroTerminalForm.setComboTerminal(ListaTerminaisVO.Factory.newInstance().getTerminalVOArray());
			this.cadastroTerminalForm.setComboNomeLoja(ListaLojaVO.Factory.newInstance().getLojaVOArray());
		}
		return this.cadastroTerminalForm;
	}

	private PesquisarEnderecoForm pesqEnderecoForm;

	public PesquisarEnderecoForm getPesquisarEnderecoForm() {
		if (this.pesqEnderecoForm == null) {
			this.pesqEnderecoForm = new PesquisarEnderecoForm();
			this.pesqEnderecoForm.setEnderecoSelecionado(EnderecoVO.Factory.newInstance());
			this.pesqEnderecoForm.getEnderecoSelecionado().addNewUFVO().setIdUF("0");
			this.pesqEnderecoForm.getEnderecoSelecionado().addNewPaisVO().setIdPais("0");
		}
		return this.pesqEnderecoForm;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("obterComboMunicipio".equals(mapping.getParameter())) {
			return obterComboMunicipio(mapping, form, request, response);
		} else if ("obterComboLoja".equals(mapping.getParameter())) {
			return obterComboLoja(mapping, form, request, response);
		} else if ("obterComboTerminal".equals(mapping.getParameter())) {
			return obterComboTerminal(mapping, form, request, response);
		} else if ("pesquisarTerminais".equals(mapping.getParameter())) {
			return pesquisarTerminais(mapping, form, request, response);
		} else if ("reiniciarSenhaLojista".equals(mapping.getParameter())) {
			return reiniciarSenhaLojista(mapping, form, request, response);
		} else if ("removerTerminal".equals(mapping.getParameter())) {
			return removerTerminal(mapping, form, request, response);
		} else if ("incluirTerminal".equals(mapping.getParameter())) {
			return incluirTerminal(mapping, form, request, response);
		} else if ("incluirTerminalAction".equals(mapping.getParameter())) {
			return incluirTerminalAction(mapping, form, request, response);
		} else if ("alterarTerminal".equals(mapping.getParameter())) {
			return alterarTerminal(mapping, form, request, response);
		} else if ("alterarTerminalAction".equals(mapping.getParameter())) {
			return alterarTerminalAction(mapping, form, request, response);
		} else if ("pesquisarNomeLoja".equals(mapping.getParameter())) {
			return pesquisarNomeLoja(mapping, form, request, response);
		} else if ("selecionarEnderecoLoja".equals(mapping.getParameter())) {
			return selecionarEnderecoLoja(mapping, form, request, response);
		} else if ("pesquisarEndereco".equals(mapping.getParameter())) {
			return pesquisarEndereco(mapping, form, request, response);
		} else if ("loadPesquisaEndereco".equals(mapping.getParameter())) {
			return loadPesquisaEndereco(mapping, form, request, response);
		} else if ("recuperarEndereco".equals(mapping.getParameter())) {
			return recuperarEndereco(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisarTerminal.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		CadastroTerminalForm form = (CadastroTerminalForm) formParam;
		limpaForm(form, request);
		obterComboUF(form, request);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void obterComboUF(CadastroTerminalForm form, HttpServletRequest request) {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			getCadastroTerminalForm().setComboUF(terminalFacade.obterComboUF(user).getUFVOArray());
		} catch (FacadeException e) {
			getCadastroTerminalForm().setComboUF(new UFVO[0]);
			log.error("ManterTerminalController:obterComboUF(" + user + ") - [" + e.getMessage() + "]", e);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisarTerminal.jsp"
	 */
	protected void limpaForm(CadastroTerminalForm form, HttpServletRequest request) {

		form.setIdUF(null);
		form.setLojaSelecionada(null);
		form.setMunicipioSelecionado(null);
		form.setIdTerminalSelecionado(null);
		form.setFiltroRecarga(ConstantesCRM.SZERO);
		form.setFiltroPagamento(ConstantesCRM.SZERO);

		form.setNrPagina(ConstantesCRM.SONE);

		obterComboUF(form, request);
		getCadastroTerminalForm().setComboMunicipio(new MunicipioVO[0]);
		getCadastroTerminalForm().setComboLoja(new LojaVO[0]);
		getCadastroTerminalForm().setComboTerminal(new TerminalVO[0]);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisarTerminal.jsp"
	 * @jpf:forward name="error" path="pesquisarTerminal.jsp"
	 */
	protected ActionForward pesquisarTerminais(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		CadastroTerminalForm form = (CadastroTerminalForm) formParam;

		try {
			log.debug("ManterTerminalController:pesquisarTerminais(" + user + ") >> INICIALIZADO");
			ListaTerminaisVO listaVO = terminalFacade.pesquisarTerminais(user, form.getIdUF(), form.getMunicipioSelecionado(), form.getLojaSelecionada(), form.getIdTerminalSelecionado(), form.getFiltroRecarga(), form.getFiltroPagamento(), form.getNrPagina());

			getCadastroTerminalForm().setNrPagina(form.getNrPagina());

			request.setAttribute("listaTerminais", listaVO);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			log.debug("ManterTerminalController:pesquisarTerminais(" + user + ") >> FINALIZADO");

			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (FacadeException e) {
			log.error("ManterTerminalController:pesquisarTerminais(" + user + ") - [" + e.getMessage() + "]", e);
			request.setAttribute("msgError", e.getMessage());

		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisarTerminal.jsp"
	 * @jpf:forward name="error" path="pesquisarTerminal.jsp"
	 */
	protected ActionForward obterComboMunicipio(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		getCadastroTerminalForm().setComboLoja(new LojaVO[0]);
		getCadastroTerminalForm().setComboTerminal(new TerminalVO[0]);
		CadastroTerminalForm form = (CadastroTerminalForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			ListaMunicipioVO listaMunicipioVO = terminalFacade.obterComboMunicipio(user, form.getIdUF());
			getCadastroTerminalForm().setComboMunicipio(listaMunicipioVO.getMunicipioVOArray());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (FacadeException e) {
			log.error("ManterTerminalController:obterComboMunicipio(" + user + ") - [" + e.getMessage() + "]", e);
			request.setAttribute("msgError", e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisarTerminal.jsp"
	 * @jpf:forward name="error" path="pesquisarTerminal.jsp"
	 */
	protected ActionForward obterComboLoja(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		getCadastroTerminalForm().setComboTerminal(new TerminalVO[0]);
		CadastroTerminalForm form = (CadastroTerminalForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			ListaLojaVO listaLojaVO = terminalFacade.obterComboLoja(user, form.getIdUF(), form.getMunicipioSelecionado());
			getCadastroTerminalForm().setComboLoja(listaLojaVO.getLojaVOArray());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (FacadeException e) {
			log.error("ManterTerminalController:obterComboLoja(" + user + ") - [" + e.getMessage() + "]", e);
			request.setAttribute("msgError", e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisarTerminal.jsp"
	 * @jpf:forward name="error" path="pesquisarTerminal.jsp"
	 */
	protected ActionForward obterComboTerminal(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		CadastroTerminalForm form = (CadastroTerminalForm) formParam;
		try {
			ListaTerminaisVO listaTerminalVO = terminalFacade.obterComboTerminal(user, form.getLojaSelecionada());
			getCadastroTerminalForm().setComboTerminal(listaTerminalVO.getTerminalVOArray());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (FacadeException e) {
			log.error("ManterTerminalController:obterComboTerminal(" + user + ") - [" + e.getMessage() + "]", e);
			request.setAttribute("msgError", e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisarTerminal.jsp"
	 * @jpf:forward name="error" path="pesquisarTerminal.jsp"
	 */
	protected ActionForward reiniciarSenhaLojista(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		CadastroTerminalForm form = (CadastroTerminalForm) formParam;
		try {
			log.debug("ManterTerminalController:reiniciarSenhaLojista(" + user + ") >> INICIALIZADO");
			terminalFacade.reiniciarSenhaLojista(user, form.getIdTerminal());
			request.setAttribute("msgError", ConstantesCRM.SSucesso);
			log.debug("ManterTerminalController:reiniciarSenhaLojista(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			// return mapping.findForward(ConstantesCRM.SUCCESS);
			return pesquisarTerminais(mapping, formParam, request, response);
		} catch (FacadeException e) {
			log.error("ManterTerminalController:reiniciarSenhaLojista(" + user + ") - [" + e.getMessage() + "]", e);
			request.setAttribute("msgError", e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisarTerminal.jsp"
	 * @jpf:forward name="error" path="pesquisarTerminal.jsp"
	 */
	protected ActionForward removerTerminal(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		CadastroTerminalForm form = (CadastroTerminalForm) formParam;
		try {
			log.debug("ManterTerminalController:removerTerminal(" + user + ") >> INICIALIZADO");
			terminalFacade.excluirTerminal(user, form.getIdTerminal(), form.getIdPessoaDePara(), form.getNmMunicipio(), form.getNmPessoa());

			request.setAttribute("msgError", ConstantesCRM.SSucesso);
			log.debug("ManterTerminalController:removerTerminal(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return pesquisarTerminais(mapping, formParam, request, response);
		} catch (FacadeException e) {
			String s = getRootMessage(e);
			log.error("ManterTerminalController:removerTerminal(" + user + ") - [" + e.getMessage() + "]", e);
			request.setAttribute("msgError", e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		} catch (TuxedoWarningException twe) {
			log.error("ManterTerminalController:removerTerminal(" + user + ") - [" + twe.getMessage() + "]", twe);
			request.setAttribute("msgError", twe.getXmlHeader().getStatusText());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		} catch (Exception e) {
			String s = getRootMessage(e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluirAlterarTerminal.jsp"
	 */
	protected ActionForward incluirTerminal(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		CadastroTerminalForm form = (CadastroTerminalForm) formParam;
		getCadastroTerminalForm().setOperacao("I");
		getCadastroTerminalForm().setComboNomeLoja(ListaLojaVO.Factory.newInstance().getLojaVOArray());
		form.setLojaExistente(ConstantesCRM.SONE);
		form.setNmMunicipio(ConstantesCRM.SVAZIO);
		form.setIdPessoaDePara(null);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="retorno.jsp"
	 * @jpf:forward name="error" path="incluirAlterarTerminal.jsp"
	 */
	protected ActionForward incluirTerminalAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		CadastroTerminalForm form = (CadastroTerminalForm) formParam;
		try {
			log.debug("ManterTerminalController:incluirTerminalAction(" + user + ") >> INICIALIZADO");

			ManterTerminalVO terminalVO = ManterTerminalVO.Factory.newInstance();

			terminalVO.setIdTerminal(form.getIdTerminal());
			terminalVO.setNrTerminal(form.getNrTerminal());
			terminalVO.setNrIpTerminal(form.getNrIpTerminal());
			terminalVO.setCdLojaOperadoraCartao(form.getCdLojaOperadoraCartao());
			terminalVO.setIdUfOperadora(form.getIdUfOperadora());
			terminalVO.setCdSitefSenha(form.getCdSitefSenha());
			terminalVO.setIdCor(form.getIdCor());
			terminalVO.setInLiberadoRecarga(form.getInLiberadoRecarga());
			terminalVO.setInLiberadoPagamento(form.getInLiberadoPagamento());

			terminalVO.setIdPessoaDePara(form.getIdPessoaDePara());
			terminalVO.setIdPessoa(form.getIdPessoa());
			terminalVO.setNmPessoa(form.getNmPessoa());

			terminalVO.setIdPessoaEndereco(form.getIdPessoaEndereco());
			terminalVO.setNmMunicipio(form.getNmMunicipio());
			terminalVO.setNmLocalidade(form.getNmLocalidade());
			terminalVO.setNmBairro(form.getNmBairro());
			terminalVO.setNmTipoLogradouro(form.getNmTipoLogradouro());
			terminalVO.setNmTituloLogradouro(form.getNmTituloLogradouro());
			terminalVO.setNmLogradouro(form.getNmLogradouro());
			terminalVO.setNrEndereco(form.getNrEndereco());
			terminalVO.setDsEnderecoComplemento(form.getDsEnderecoComplemento());
			terminalVO.setNrCep(form.getNrCep());

			terminalVO.addNewPaisVO();
			terminalVO.addNewTipoEnderecoVO();
			terminalVO.addNewUFVO();
			terminalVO.getPaisVO().setIdPais(form.getIdPais());
			terminalVO.getTipoEnderecoVO().setIdTipoEndereco(form.getIdTipoEndereco());
			terminalVO.getUFVO().setIdUF(form.getIdUF());

			terminalFacade.incluirTerminal(user, terminalVO);
			request.setAttribute("msgError", ConstantesCRM.SSucesso);

			log.debug("ManterTerminalController:incluirTerminalAction(" + user + ") >> FINALIZADO");

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (FacadeException e) {
			log.error("ManterTerminalController:incluirTerminalAction(" + user + ") - [" + e.getMessage() + "]", e);
			request.setAttribute("msgError", e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		} catch (TuxedoWarningException twe) {
			log.error("ManterTerminalController:incluirTerminalAction(" + user + ") - [" + twe.getMessage() + "]", twe);
			request.setAttribute("msgError", twe.getXmlHeader().getStatusText());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		} catch (Exception e) {
			String s = getRootMessage(e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluirAlterarTerminal.jsp"
	 * @jpf:forward name="error" path="pesquisarTerminal.jsp"
	 */
	protected ActionForward alterarTerminal(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		getCadastroTerminalForm().setOperacao("A");
		CadastroTerminalForm form = (CadastroTerminalForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			log.debug("ManterTerminalController:alterarTerminal(" + user + ") >> INICIALIZADO");

			ManterTerminalVO terminalVO = terminalFacade.pesquisarTerminal(user, form.getIdTerminal(), form.getIdPessoaDePara());

			form.setIdTerminal(terminalVO.getIdTerminal());
			form.setNrTerminal(terminalVO.getNrTerminal());
			form.setNrIpTerminal(terminalVO.getNrIpTerminal());
			form.parseIpTerminal(); // Divide o número IP do terminal para edição
			form.setCdLojaOperadoraCartao(terminalVO.getCdLojaOperadoraCartao());
			form.setIdUF(terminalVO.getUFVO().getIdUF());
			obterDsUFOperadora(user, form);
			form.setIdUfOperadora(terminalVO.getIdUfOperadora());
			form.setCdSitefSenha(terminalVO.getCdSitefSenha());
			form.setIdCor(terminalVO.getIdCor());
			form.setInLiberadoRecarga(terminalVO.getInLiberadoRecarga());
			form.setInLiberadoPagamento(terminalVO.getInLiberadoPagamento());

			form.setIdPessoa(terminalVO.getIdPessoa());
			form.setIdPessoaDePara(terminalVO.getIdPessoaDePara());
			form.setIdPessoa(terminalVO.getIdPessoa());
			form.setNmPessoa(terminalVO.getNmPessoa());

			form.setIdPessoaEndereco(terminalVO.getIdPessoaEndereco());
			form.setNmMunicipio(terminalVO.getNmMunicipio());
			form.setNmLocalidade(terminalVO.getNmLocalidade());
			form.setNmBairro(terminalVO.getNmBairro());
			form.setNmTipoLogradouro(terminalVO.getNmTipoLogradouro());
			form.setNmTituloLogradouro(terminalVO.getNmTituloLogradouro());
			form.setNmLogradouro(terminalVO.getNmLogradouro());
			form.setNrEndereco(terminalVO.getNrEndereco());
			form.setDsEnderecoComplemento(terminalVO.getDsEnderecoComplemento());
			form.setNrCep(terminalVO.getNrCep());

			// form.setIdPais(terminalVO.getPaisVO().getIdPais());
			// form.setNmPais(terminalVO.getPaisVO().getNmPais());

			// form.setIdTipoEndereco(terminalVO.getTipoEnderecoVO().getIdTipoEndereco());
			// form.setNmTipoLogradouro(terminalVO.getTipoEnderecoVO().getDsTipoEndereco());

			form.setIdUF(terminalVO.getUFVO().getIdUF());
			form.setNmUF(terminalVO.getUFVO().getSgUF());

			log.debug("ManterTerminalController:alterarTerminal(" + user + ") >> FINALIZADO");

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (FacadeException e) {
			log.error("ManterTerminalController:alterarTerminal(" + user + ") - [" + e.getMessage() + "]", e);
			request.setAttribute("msgError", e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="retorno.jsp"
	 * @jpf:forward name="error" path="incluirAlterarTerminal.jsp"
	 */
	protected ActionForward alterarTerminalAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		CadastroTerminalForm form = (CadastroTerminalForm) formParam;
		try {
			log.debug("ManterTerminalController:alterarTerminalAction(" + user + ") >> INICIALIZADO");

			ManterTerminalVO terminalVO = ManterTerminalVO.Factory.newInstance();

			terminalVO.setIdTerminal(form.getIdTerminal());
			terminalVO.setNrTerminal(form.getNrTerminal());
			terminalVO.setNrIpTerminal(form.getNrIpTerminal());
			terminalVO.setCdLojaOperadoraCartao(form.getCdLojaOperadoraCartao());
			terminalVO.setIdUfOperadora(form.getIdUfOperadora());
			terminalVO.setCdSitefSenha(form.getCdSitefSenha());
			terminalVO.setIdCor(form.getIdCor());
			terminalVO.setInLiberadoRecarga(form.getInLiberadoRecarga());
			terminalVO.setInLiberadoPagamento(form.getInLiberadoPagamento());

			terminalVO.setIdPessoaDePara(form.getIdPessoaDePara());
			terminalVO.setIdPessoa(form.getIdPessoa());
			terminalVO.setNmPessoa(form.getNmPessoa());

			terminalVO.setIdPessoaEndereco(form.getIdPessoaEndereco());
			terminalVO.setNmMunicipio(form.getNmMunicipio());
			terminalVO.setNmLocalidade(form.getNmLocalidade());
			terminalVO.setNmBairro(form.getNmBairro());
			terminalVO.setNmTipoLogradouro(form.getNmTipoLogradouro());
			terminalVO.setNmTituloLogradouro(form.getNmTituloLogradouro());
			terminalVO.setNmLogradouro(form.getNmLogradouro());
			terminalVO.setNrEndereco(form.getNrEndereco());
			terminalVO.setDsEnderecoComplemento(form.getDsEnderecoComplemento());
			terminalVO.setNrCep(form.getNrCep());

			terminalVO.addNewPaisVO();
			terminalVO.addNewTipoEnderecoVO();
			terminalVO.addNewUFVO();
			terminalVO.getPaisVO().setIdPais(form.getIdPais());
			terminalVO.getTipoEnderecoVO().setIdTipoEndereco(form.getIdTipoEndereco());
			terminalVO.getUFVO().setIdUF(form.getIdUF());

			terminalFacade.alterarTerminal(user, terminalVO);
			request.setAttribute("msgError", ConstantesCRM.SSucesso);

			log.debug("ManterTerminalController:alterarTerminalAction(" + user + ") >> FINALIZADO");

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (FacadeException e) {
			log.error("ManterTerminalController:alterarTerminalAction(" + user + ") - [" + e.getMessage() + "]", e);
			request.setAttribute("msgError", e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		} catch (TuxedoWarningException twe) {
			log.error("ManterTerminalController:alterarTerminalAction(" + user + ") - [" + twe.getMessage() + "]", twe);
			request.setAttribute("msgError", twe.getXmlHeader().getStatusText());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		} catch (Exception e) {
			String s = getRootMessage(e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	private String getRootMessage(Throwable e) {
		if (e.getCause() == null) {
			return e.getMessage();
		} else {
			return getRootMessage(e.getCause());
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluirAlterarTerminal.jsp"
	 * @jpf:forward name="error" path="incluirAlterarTerminal.jsp"
	 */
	protected ActionForward pesquisarNomeLoja(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		CadastroTerminalForm form = (CadastroTerminalForm) formParam;
		try {
			ListaLojaVO nomesLoja = terminalFacade.pesquisarNomeLoja(user, form.getNomeLoja());
			getCadastroTerminalForm().setComboNomeLoja(nomesLoja.getLojaVOArray());

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (FacadeException e) {
			log.error("ManterTerminalController:pesquisarNomeLoja(" + user + ") - [" + e.getMessage() + "]", e);
			request.setAttribute("msgError", e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluirAlterarTerminal.jsp"
	 * @jpf:forward name="error" path="incluirAlterarTerminal.jsp"
	 */
	protected ActionForward selecionarEnderecoLoja(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		CadastroTerminalForm form = (CadastroTerminalForm) formParam;
		try {
			log.debug("ManterTerminalController:selecionarEnderecoLoja(" + user + ") >> INICIALIZADO");

			EnderecoVO enderecoVO = terminalFacade.selecionarEnderecoLoja(user, form.getIdNomeLoja());

			form.setIdPessoaEndereco(enderecoVO.getIdEndereco());
			form.setNmTipoLogradouro(enderecoVO.getNmTipoLogradouro());
			form.setNmTituloLogradouro(enderecoVO.getNmTituloLogradouro());
			form.setNmLogradouro(enderecoVO.getNmLogradouro());
			form.setNrEndereco(enderecoVO.getNrEndereco());
			form.setDsEnderecoComplemento(enderecoVO.getDsEnderecoComplemento());
			form.setNmBairro(enderecoVO.getNmBairro());
			form.setNmMunicipio(enderecoVO.getNmMunicipio());
			form.setNrCep(enderecoVO.getNrCEP());
			form.setIdUF(enderecoVO.getUFVO().getIdUF());
			form.setNmUF(enderecoVO.getUFVO().getSgUF());

			obterDsUFOperadora(user, form);
			form.setIdUfOperadora(enderecoVO.getIdUfOperadora());

			form.setCdLojaOperadoraCartao(enderecoVO.getCdLojaOperadoraCartao());
			form.setNmPessoa(enderecoVO.getNmPessoa());

			log.debug("ManterTerminalController:selecionarEnderecoLoja(" + user + ") >> FINALIZADO");

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (FacadeException e) {
			log.error("ManterTerminalController:selecionarEnderecoLoja(" + user + ") - [" + e.getMessage() + "]", e);
			request.setAttribute("msgError", e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisaEnderecoResultado.jsp"
	 * @jpf:forward name="error" path="pesquisaEnderecoResultado.jsp"
	 */
	protected ActionForward pesquisarEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		PesquisarEnderecoForm form = (PesquisarEnderecoForm) formParam;
		try {
			log.debug("ManterTerminalController:pesquisarEndereco(" + user + ") >> INICIALIZADO");

			// Prepara o filtro
			PesquisaEnderecoVO filtroVO = PesquisaEnderecoVO.Factory.newInstance();
			filtroVO.addNewFiltroPesquisa();
			filtroVO.getFiltroPesquisa().setDsLogradouro(form.getDsLogradouro());
			filtroVO.getFiltroPesquisa().setDsBairro(form.getDsBairro());
			filtroVO.getFiltroPesquisa().setDsLocalidade(form.getDsLocalidade());
			filtroVO.getFiltroPesquisa().setNrCEP(form.getNrCEP());
			filtroVO.getFiltroPesquisa().setIdUFSelecionado(form.getIdUFSelecionado());

			PesquisaEnderecoVO pesqEnderecoVO = telaInicialFacade.getPesquisaEnderecoFil(user, filtroVO);
			request.setAttribute("pesqEndereco", pesqEnderecoVO);
			getPesquisarEnderecoForm().setPesqEndereco(pesqEnderecoVO);

			if ((pesqEnderecoVO.getListaEnderecos() == null || pesqEnderecoVO.getListaEnderecos().getEnderecoVOArray().length == 0) && (pesqEnderecoVO.getErro() == null || pesqEnderecoVO.getErro().length() == 0)) {
				pesqEnderecoVO.addNewListaEnderecos();

				if (filtroVO.getFiltroPesquisa().getNrCEP() != null && filtroVO.getFiltroPesquisa().getNrCEP().length() > 0) {
					log.info("ManterTerminalController:pesquisarEndereco(" + user + ") >> CEP não encontrado");
					request.setAttribute("msgError", "CEP não encontrado!");
				} else {
					log.info("ManterTerminalController:pesquisarEndereco(" + user + ") >> Endereço não encontrado");
					request.setAttribute("msgError", "Nenhum endereço encontrado!");
				}
			}

			log.debug("ManterTerminalController:pesquisarEndereco(" + user + ") >> FINALIZADO");

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (TuxedoException e) {
			log.error("ManterTerminalController:pesquisarEndereco(" + user + ") - [" + e.getMessage() + "]", e);
			request.setAttribute("msgError", e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		} catch (FacadeException e) {
			log.error("ManterTerminalController:pesquisarEndereco(" + user + ") - [" + e.getMessage() + "]", e);
			request.setAttribute("msgError", e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisaEndereco.jsp"
	 */
	protected ActionForward loadPesquisaEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		PesquisarEnderecoForm form = (PesquisarEnderecoForm) formParam;
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluirAlterarTerminal.jsp"
	 */
	protected ActionForward recuperarEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws FacadeException {
		CadastroTerminalForm form = (CadastroTerminalForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		PesquisaEnderecoVO pesqEnderecoVO = getPesquisarEnderecoForm().getPesqEndereco();
		int index = Integer.parseInt(form.getIndexEndereco());
		EnderecoVO enderecoVO = pesqEnderecoVO.getListaEnderecos().getEnderecoVOArray(index);

		form.setIdPessoaEndereco(enderecoVO.getIdEndereco());
		form.setNmTipoLogradouro(enderecoVO.getNmTipoLogradouro());
		form.setNmTituloLogradouro(enderecoVO.getNmTituloLogradouro());
		form.setNmLogradouro(enderecoVO.getNmLogradouro());
		form.setNrEndereco(enderecoVO.getNrEndereco());
		form.setDsEnderecoComplemento(enderecoVO.getDsEnderecoComplemento());
		form.setNmBairro(enderecoVO.getNmBairro());
		form.setNmMunicipio(enderecoVO.getNmMunicipio());
		form.setNrCep(enderecoVO.getNrCEP());
		form.setIdUF(enderecoVO.getUFVO().getIdUF());
		form.setNmUF(enderecoVO.getUFVO().getSgUF());
		form.setIdPais(enderecoVO.getPaisVO().getIdPais());
		form.setNmPais(enderecoVO.getPaisVO().getNmPais());

		obterDsUFOperadora(user, form);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void obterDsUFOperadora(String user, CadastroTerminalForm form) {
		try {
			VOLTAVUFOperadoraVO operadora = terminalFacade.obterUFOperadora(user, form.getIdUF());
			form.setIdUfOperadora(operadora.getIdOperadora());
			form.setDsUfOperadora(operadora.getDsOperadora());
		} catch (FacadeException e) {
			form.setDsUfOperadora(ConstantesCRM.SVAZIO);
			log.error("ManterTerminalController:obterDsUFOperadora(" + user + ") - [" + e.getMessage() + "]", e);
		}
	}

	/**
	 * Retorna idUfOperadora a partir de um idUf informado
	 */
	private String getUfOperadora(String uf) {
		return "consultarOperadora";
	}

	/*
	 * protected void obterComboUfOperadora(CadastroTerminalForm form) { String user =
	 * ConstantesCRM.getCurrentUser(request.getSession()); try { ListaUFOperadoraVO lista =
	 * terminalFacade.obterComboUfOperadora(user); VOLTAVUFOperadoraVO[] vo = lista.getVOLTAVUFOperadoraVOArray();
	 * getCadastroTerminalForm().setComboUfOperadora(vo); } catch (FacadeException e) {
	 * log.error("ManterTerminalController:obterComboUfOperadora(" + user + ") - [" + e.getMessage() + "]", e); } }
	 */

	public static class CadastroTerminalForm extends ActionForm {

		private static final long serialVersionUID = -7677618478925100934L;

        private String nrPagina;

		private String operacao;

		private String lojaExistente;

		private String nrIp4;
		private String nrIp3;
		private String nrIp2;
		private String nrIp1;

		private String indexEndereco;

		private String idTerminalSelecionado;
		private String filtroRecarga;
		private String filtroPagamento;
		private String municipioSelecionado;
		private String lojaSelecionada;
		private String idTerminal;
		private String idUF;
		private String nmUF;
		private String idTipoEndereco;
		private String idPais;
		private String nmPais;
		private String nrCep;
		private String dsEnderecoComplemento;
		private String nmLogradouro;
		private String nmTituloLogradouro;
		private String nmTipoLogradouro;
		private String nmBairro;
		private String nmLocalidade;
		private String nmMunicipio;
		private String idPessoaEndereco;
		private String nmPessoa;
		private String idPessoa;
		private String idPessoaDePara;
		private String inLiberadoRecarga;
		private String inLiberadoPagamento;
		private String idCor;
		private String cdSitefSenha;
		private String idUfOperadora;
		private String dsUfOperadora;
		private String idUfOperadoraB;
		private String cdLojaOperadoraCartao;
		private String nrIpTerminal;
		private String nrTerminal;
		private String dsComplementoEndereco;
		private String nrEndereco;

		private String nomeLoja;
		private String idNomeLoja;

		private TerminalVO[] comboTerminal;
		private LojaVO[] comboLoja;
		private MunicipioVO[] comboMunicipio;
		private UFVO[] comboUF;
		private CorVO[] comboCor;
		private LojaVO[] comboNomeLoja;
		private VOLTAVUFOperadoraVO[] comboUfOperadora;

		public void parseIpTerminal() {
			StringTokenizer st = new StringTokenizer(this.nrIpTerminal, ".");
			this.nrIp1 = st.nextToken();
			this.nrIp2 = st.nextToken();
			this.nrIp3 = st.nextToken();
			this.nrIp4 = st.nextToken();
		}

		public String getNomeLoja() {
			return nomeLoja;
		}

		public TerminalVO[] getComboTerminal() {
			return comboTerminal;
		}

		public LojaVO[] getComboLoja() {
			return comboLoja;
		}

		public MunicipioVO[] getComboMunicipio() {
			return comboMunicipio;
		}

		public UFVO[] getComboUF() {
			return comboUF;
		}

		public CorVO[] getComboCor() {
			return comboCor;
		}

		public void setNomeLoja(String nomeLoja) {
			this.nomeLoja = nomeLoja;
		}

		public void setComboTerminal(TerminalVO[] comboTerminal) {
			this.comboTerminal = comboTerminal;
		}

		public void setComboLoja(LojaVO[] comboLoja) {
			this.comboLoja = comboLoja;
		}

		public void setComboMunicipio(MunicipioVO[] comboMunicipio) {
			this.comboMunicipio = comboMunicipio;
		}

		public void setComboUF(UFVO[] comboUF) {
			this.comboUF = comboUF;
		}

		public void setComboCor(CorVO[] comboCor) {
			this.comboCor = comboCor;
		}

		public void setIdTerminal(String idTerminal) {
			this.idTerminal = idTerminal;
		}

		public String getIdTerminal() {
			return this.idTerminal;
		}

		public void setComboNomeLoja(LojaVO[] comboNomeLoja) {
			this.comboNomeLoja = comboNomeLoja;
		}

		public LojaVO[] getComboNomeLoja() {
			return this.comboNomeLoja;
		}

		public void setComboUfOperadora(VOLTAVUFOperadoraVO[] comboUfOperadora) {
			this.comboUfOperadora = comboUfOperadora;
		}

		public VOLTAVUFOperadoraVO[] getComboUfOperadora() {
			return this.comboUfOperadora;
		}

		public void setNrEndereco(String nrEndereco) {
			this.nrEndereco = nrEndereco;
		}

		public String getNrEndereco() {
			return this.nrEndereco;
		}

		public void setNrTerminal(String nrTerminal) {
			this.nrTerminal = nrTerminal;
		}

		public String getNrTerminal() {
			return this.nrTerminal;
		}

		public void setNrIpTerminal(String nrIpTerminal) {
			this.nrIpTerminal = nrIpTerminal;
		}

		public String getNrIpTerminal() {
			return this.nrIpTerminal;
		}

		public void setCdLojaOperadoraCartao(String cdLojaOperadoraCartao) {
			this.cdLojaOperadoraCartao = cdLojaOperadoraCartao;
		}

		public String getCdLojaOperadoraCartao() {
			return this.cdLojaOperadoraCartao;
		}

		public void setIdUfOperadora(String idUfOperadora) {
			this.idUfOperadora = idUfOperadora;
		}

		public String getIdUfOperadora() {
			return this.idUfOperadora;
		}

		public void setDsUfOperadora(String dsUfOperadora) {
			this.dsUfOperadora = dsUfOperadora;
		}

		public String getDsUfOperadora() {
			return this.dsUfOperadora;
		}

		public void setCdSitefSenha(String cdSitefSenha) {
			this.cdSitefSenha = cdSitefSenha;
		}

		public String getCdSitefSenha() {
			return this.cdSitefSenha;
		}

		public void setIdCor(String idCor) {
			this.idCor = idCor;
		}

		public String getIdCor() {
			return this.idCor;
		}

		public void setInLiberadoRecarga(String inLiberadoRecarga) {
			this.inLiberadoRecarga = inLiberadoRecarga;
		}

		public String getInLiberadoRecarga() {
			return this.inLiberadoRecarga;
		}

		public void setInLiberadoPagamento(String inLiberadoPagamento) {
			this.inLiberadoPagamento = inLiberadoPagamento;
		}

		public String getInLiberadoPagamento() {
			return this.inLiberadoPagamento;
		}

		public void setIdPessoaDePara(String idPessoaDePara) {
			this.idPessoaDePara = idPessoaDePara;
		}

		public String getIdPessoaDePara() {
			return this.idPessoaDePara;
		}

		public void setIdPessoa(String idPessoa) {
			this.idPessoa = idPessoa;
		}

		public String getIdPessoa() {
			return this.idPessoa;
		}

		public void setNmPessoa(String nmPessoa) {
			this.nmPessoa = nmPessoa;
		}

		public String getNmPessoa() {
			return this.nmPessoa;
		}

		public void setIdPessoaEndereco(String idPessoaEndereco) {
			this.idPessoaEndereco = idPessoaEndereco;
		}

		public String getIdPessoaEndereco() {
			return this.idPessoaEndereco;
		}

		public void setNmMunicipio(String nmMunicipio) {
			this.nmMunicipio = nmMunicipio;
		}

		public String getNmMunicipio() {
			return this.nmMunicipio;
		}

		public void setNmLocalidade(String nmLocalidade) {
			this.nmLocalidade = nmLocalidade;
		}

		public String getNmLocalidade() {
			return this.nmLocalidade;
		}

		public void setNmBairro(String nmBairro) {
			this.nmBairro = nmBairro;
		}

		public String getNmBairro() {
			return this.nmBairro;
		}

		public void setNmTipoLogradouro(String nmTipoLogradouro) {
			this.nmTipoLogradouro = nmTipoLogradouro;
		}

		public String getNmTipoLogradouro() {
			return this.nmTipoLogradouro;
		}

		public void setNmTituloLogradouro(String nmTituloLogradouro) {
			this.nmTituloLogradouro = nmTituloLogradouro;
		}

		public String getNmTituloLogradouro() {
			return this.nmTituloLogradouro;
		}

		public void setNmLogradouro(String nmLogradouro) {
			this.nmLogradouro = nmLogradouro;
		}

		public String getNmLogradouro() {
			return this.nmLogradouro;
		}

		public void setDsEnderecoComplemento(String dsEnderecoComplemento) {
			this.dsEnderecoComplemento = dsEnderecoComplemento;
		}

		public String getDsEnderecoComplemento() {
			return this.dsEnderecoComplemento;
		}

		public void setNrCep(String nrCep) {
			this.nrCep = nrCep;
		}

		public String getNrCep() {
			return this.nrCep;
		}

		public void setIdPais(String idPais) {
			this.idPais = idPais;
		}

		public String getIdPais() {
			return this.idPais;
		}

		public void setNmPais(String nmPais) {
			this.nmPais = nmPais;
		}

		public String getNmPais() {
			return this.nmPais;
		}

		public void setIdTipoEndereco(String idTipoEndereco) {
			this.idTipoEndereco = idTipoEndereco;
		}

		public String getIdTipoEndereco() {
			return this.idTipoEndereco;
		}

		public void setIdUF(String idUF) {
			this.idUF = idUF;
		}

		public String getIdUF() {
			return this.idUF;
		}

		public void setNmUF(String nmUF) {
			this.nmUF = nmUF;
		}

		public String getNmUF() {
			return this.nmUF;
		}

		public void setIndexEndereco(String indexEndereco) {
			this.indexEndereco = indexEndereco;
		}

		public String getIndexEndereco() {
			return this.indexEndereco;
		}

		public void setNrIp1(String nrIp1) {
			this.nrIp1 = nrIp1;
		}

		public String getNrIp1() {
			return this.nrIp1;
		}

		public void setNrIp2(String nrIp2) {
			this.nrIp2 = nrIp2;
		}

		public String getNrIp2() {
			return this.nrIp2;
		}

		public void setNrIp3(String nrIp3) {
			this.nrIp3 = nrIp3;
		}

		public String getNrIp3() {
			return this.nrIp3;
		}

		public void setNrIp4(String nrIp4) {
			this.nrIp4 = nrIp4;
		}

		public String getNrIp4() {
			return this.nrIp4;
		}

		public void setIdTerminalSelecionado(String idTerminalSelecionado) {
			this.idTerminalSelecionado = idTerminalSelecionado;
		}

		public String getIdTerminalSelecionado() {
			return this.idTerminalSelecionado;
		}

		public void setLojaSelecionada(String lojaSelecionada) {
			this.lojaSelecionada = lojaSelecionada;
		}

		public String getLojaSelecionada() {
			return this.lojaSelecionada;
		}

		public void setLojaExistente(String lojaExistente) {
			this.lojaExistente = lojaExistente;
		}

		public String getLojaExistente() {
			return this.lojaExistente;
		}

		public void setIdNomeLoja(String idNomeLoja) {
			this.idNomeLoja = idNomeLoja;
		}

		public String getIdNomeLoja() {
			return this.idNomeLoja;
		}

		public void setFiltroRecarga(String filtroRecarga) {
			this.filtroRecarga = filtroRecarga;
		}

		public void setFiltroPagamento(String filtroPagamento) {
			this.filtroPagamento = filtroPagamento;
		}

		public String getFiltroRecarga() {
			return this.filtroRecarga;
		}

		public String getFiltroPagamento() {
			return this.filtroPagamento;
		}

		public void setOperacao(String operacao) {
			this.operacao = operacao;
		}

		public String getOperacao() {
			return this.operacao;
		}

		public void setMunicipioSelecionado(String municipioSelecionado) {
			this.municipioSelecionado = municipioSelecionado;
		}

		public String getMunicipioSelecionado() {
			return this.municipioSelecionado;
		}

		public void setNrPagina(String nrPagina) {
			this.nrPagina = nrPagina;
		}

		public String getNrPagina() {
			return this.nrPagina;
		}
	}

	public static class PesquisarEnderecoForm extends ActionForm {

		private static final long serialVersionUID = 1482459130604368803L;

        private String indexEndereco;

		private String idUFSelecionado;
		private String nrCEP;
		private String dsLocalidade;
		private String dsBairro;
		private String dsLogradouro;

		private PesquisaEnderecoVO pesqEndereco;
		private EnderecoVO enderecoSelecionado;

		public void setDsLogradouro(String dsLogradouro) {
			this.dsLogradouro = dsLogradouro;
		}

		public String getDsLogradouro() {
			return this.dsLogradouro;
		}

		public void setDsBairro(String dsBairro) {
			this.dsBairro = dsBairro;
		}

		public String getDsBairro() {
			return this.dsBairro;
		}

		public void setDsLocalidade(String dsLocalidade) {
			this.dsLocalidade = dsLocalidade;
		}

		public String getDsLocalidade() {
			return this.dsLocalidade;
		}

		public void setNrCEP(String nrCEP) {
			this.nrCEP = nrCEP;
		}

		public String getNrCEP() {
			return this.nrCEP;
		}

		public void setIdUFSelecionado(String idUFSelecionado) {
			this.idUFSelecionado = idUFSelecionado;
		}

		public String getIdUFSelecionado() {
			return this.idUFSelecionado;
		}

		public void setPesqEndereco(PesquisaEnderecoVO pesqEndereco) {
			this.pesqEndereco = pesqEndereco;
		}

		public PesquisaEnderecoVO getPesqEndereco() {
			return this.pesqEndereco;
		}

		public void setIndexEndereco(String indexEndereco) {
			this.indexEndereco = indexEndereco;
		}

		public String getIndexEndereco() {
			return this.indexEndereco;
		}

		public void setEnderecoSelecionado(EnderecoVO enderecoSelecionado) {
			this.enderecoSelecionado = enderecoSelecionado;
		}

		public EnderecoVO getEnderecoSelecionado() {
			return this.enderecoSelecionado;
		}
	}
}
