package usuario.manterUsuario.editarUsuario;

import java.io.BufferedOutputStream;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import netscape.ldap.LDAPAttribute;
import netscape.ldap.LDAPAttributeSet;
import netscape.ldap.LDAPException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.ClienteUtils;
import br.com.vivo.fo.cliente.vo.PaisVODocument.PaisVO;
import br.com.vivo.fo.cliente.vo.TipoDocumentoVODocument.TipoDocumentoVO;
import br.com.vivo.fo.cliente.vo.UFVODocument.UFVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.usuario.acesso.ControleAcessoFacade;
import br.com.vivo.fo.ctrls.usuario.manterUsuario.ManterUsuarioFacade;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.CargoSimplVODocument.CargoSimplVO;
import br.com.vivo.fo.usuario.vo.CargoUsuarioVODocument.CargoUsuarioVO;
import br.com.vivo.fo.usuario.vo.DDDVODocument.DDDVO;
import br.com.vivo.fo.usuario.vo.DocumentoSimpVODocument.DocumentoSimpVO;
import br.com.vivo.fo.usuario.vo.ListaCargoPorNivelVODocument;
import br.com.vivo.fo.usuario.vo.ListaCargoPorNivelVODocument.ListaCargoPorNivelVO;
import br.com.vivo.fo.usuario.vo.ListaDocumentoSimpVODocument.ListaDocumentoSimpVO;
import br.com.vivo.fo.usuario.vo.ListaNivelOrganogramaVODocument.ListaNivelOrganogramaVO;
import br.com.vivo.fo.usuario.vo.ListaOrganizacaoVODocument.ListaOrganizacaoVO;
import br.com.vivo.fo.usuario.vo.ListaUnidadePorOrganizacaoVODocument.ListaUnidadePorOrganizacaoVO;
import br.com.vivo.fo.usuario.vo.ListaUsuariosVODocument.ListaUsuariosVO;
import br.com.vivo.fo.usuario.vo.MotivoStatusUsuarioVODocument.MotivoStatusUsuarioVO;
import br.com.vivo.fo.usuario.vo.NivelCargoOrgDeptoRelacionamentoVODocument.NivelCargoOrgDeptoRelacionamentoVO;
import br.com.vivo.fo.usuario.vo.NivelCargoOrgVODocument.NivelCargoOrgVO;
import br.com.vivo.fo.usuario.vo.NivelOrganogramaSimplVODocument.NivelOrganogramaSimplVO;
import br.com.vivo.fo.usuario.vo.OrganizacaoSimplVODocument.OrganizacaoSimplVO;
import br.com.vivo.fo.usuario.vo.StatusUsuarioVODocument.StatusUsuarioVO;
import br.com.vivo.fo.usuario.vo.UFOperadoraUsuarioVODocument.UFOperadoraUsuarioVO;
import br.com.vivo.fo.usuario.vo.UnidadeOrganogramaVODocument.UnidadeOrganogramaVO;
import br.com.vivo.fo.usuario.vo.UsuarioLDAPVODocument.UsuarioLDAPVO;
import br.com.vivo.fo.usuario.vo.UsuarioSimplVODocument;
import br.com.vivo.fo.usuario.vo.UsuarioSimplVODocument.UsuarioSimplVO;
import br.com.vivo.fo.usuario.vo.UsuarioVODocument.UsuarioVO;
import br.com.vivo.fo.usuario.vo.UsuariosVODocument.UsuariosVO;
import br.com.vivo.ldap.LDAPUtil;

import com.indracompany.actions.AbstractAction;

import commons.errors.FormError;

public class EditarUsuarioController extends AbstractAction {

	private static final long serialVersionUID = 762360656413237132L;

	@EJB
	private ManterUsuarioFacade controlUsuario;

	@EJB
	private ControleAcessoFacade controlAcesso;

	private ListaUsuariosForm listaUsuariosForm = new ListaUsuariosForm();

	private static ListaUsuariosForm listaUsuariosPesquisaForm = new ListaUsuariosForm();

	private static final String REGISTROS_POR_PAGINA = "20";
	private static final String MSG_ERRO = "Ocorreu erro no acesso ao LDAP, por favor tente novamente.";

	private transient Logger log = new Logger("usuario");

	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("carregaComboOrgano".equals(mapping.getParameter())) {
			return carregaComboOrgano(mapping, form, request, response);
		} else if ("pesquisaUsuarios".equals(mapping.getParameter())) {
			return pesquisaUsuarios(mapping, form, request, response);
		} else if ("manageSuperioresImediatos".equals(mapping.getParameter())) {
			return manageSuperioresImediatos(mapping, form, request, response);
		} else if ("mostraDetalhe".equals(mapping.getParameter())) {
			return mostraDetalhe(mapping, form, request, response);
		} else if ("editaUsuario".equals(mapping.getParameter())) {
			return editaUsuario(mapping, form, request, response);
		} else if ("editaRelUsuario".equals(mapping.getParameter())) {
			return editaRelUsuario(mapping, form, request, response);
		} else if ("salvaUsuario".equals(mapping.getParameter())) {
			return salvaUsuario(mapping, form, request, response);
		} else if ("incluiDocumento".equals(mapping.getParameter())) {
			return incluiDocumento(mapping, form, request, response);
		} else if ("limpaCadastro".equals(mapping.getParameter())) {
			return limpaCadastro(mapping, form, request, response);
		} else if ("excluiDocumento".equals(mapping.getParameter())) {
			return excluiDocumento(mapping, form, request, response);
		} else if ("bloqueiaUsuario".equals(mapping.getParameter())) {
			return bloqueiaUsuario(mapping, form, request, response);
		} else if ("desbloqueiaUsuario".equals(mapping.getParameter())) {
			return desbloqueiaUsuario(mapping, form, request, response);
		} else if ("reinicializaSenha".equals(mapping.getParameter())) {
			return reinicializaSenha(mapping, form, request, response);
		} else if ("limpaPesquisa".equals(mapping.getParameter())) {
			return limpaPesquisa(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	protected void listaSelectsUsuarios(NivelCargoOrgVO paramOrgano, HttpServletRequest request) throws Exception {

		UsuarioSimplVO usuarioSimplVO = UsuarioSimplVO.Factory.newInstance();
		UsuariosVO listaSelectsUsuarioVO = controlUsuario.listaSelectsUsuario(usuarioSimplVO, ConstantesCRM.getCurrentUser(request.getSession()));

		if (paramOrgano == null) {
			paramOrgano = NivelCargoOrgVO.Factory.newInstance();
		}

		if (paramOrgano.getListaCargoSimplVO() == null) {
			paramOrgano.addNewListaCargoSimplVO().addNewCargoSimplVO();
		}

		if (paramOrgano.getListaOrganizacaoVO() == null) {
			paramOrgano.addNewListaOrganizacaoVO().addNewOrganizacaoSimplVO();
		}

		if (paramOrgano.getListaUnidadeOrganogramaVO() == null) {
			paramOrgano.addNewListaUnidadeOrganogramaVO().addNewUnidadeOrganogramaVO();
		}

		if (paramOrgano.getListaNivelOrganogramaVO() == null) {
			paramOrgano.addNewListaNivelOrganogramaVO().addNewNivelOrganogramaSimplVO();
		}

		// Chamada serviço Tuxedo.
		paramOrgano = controlUsuario.listaOrganograma(paramOrgano, ConstantesCRM.getCurrentUser(request.getSession()));

		// Carrega Combo de Organização.
		if (paramOrgano.getListaOrganizacaoVO().getOrganizacaoSimplVOArray().length > 0) {
			listaUsuariosForm.setListaOrganizacao(paramOrgano.getListaOrganizacaoVO());
		} else {
			listaUsuariosForm.setListaOrganizacao(ListaOrganizacaoVO.Factory.newInstance());
		}

		// Carrega Combo Nivel Organização.
		if (paramOrgano.getListaNivelOrganogramaVO().getNivelOrganogramaSimplVOArray().length > 0) {
			listaUsuariosForm.setListaNivelOrganograma(paramOrgano.getListaNivelOrganogramaVO());
		} else {
			listaUsuariosForm.setListaNivelOrganograma(ListaNivelOrganogramaVO.Factory.newInstance());
		}

		if (paramOrgano.getDadosAtuais() != null && paramOrgano.getDadosAtuais().getIdCargo() != null && !paramOrgano.getDadosAtuais().getIdCargo().trim().equals(ConstantesCRM.SVAZIO)) {

			// carrega combo NIvel / Cargo.
			ListaCargoPorNivelVO listaCargoPorNivelVO = ListaCargoPorNivelVO.Factory.newInstance();

			// Se retornou dados vindos da tela.
			listaCargoPorNivelVO.setIdNivel(paramOrgano.getDadosAtuais().getIdCargo());

			listaCargoPorNivelVO = controlUsuario.listaCargoPorNivel(listaCargoPorNivelVO, ConstantesCRM.getCurrentUser(request.getSession()));

			// Se existem dados.
			if (listaCargoPorNivelVO.getCargoVOArray().length > 0) {
				listaUsuariosForm.setListaCargo(listaCargoPorNivelVO);
			} else {
				listaUsuariosForm.setListaCargo(ListaCargoPorNivelVO.Factory.newInstance());
			}
		}

		if (paramOrgano.getDadosAtuais() != null && paramOrgano.getDadosAtuais().getIdOrganizacao() != null && !paramOrgano.getDadosAtuais().getIdOrganizacao().trim().equals(ConstantesCRM.SVAZIO)) {
			ListaUnidadePorOrganizacaoVO listaUnidadePorOrganizacaoVO = ListaUnidadePorOrganizacaoVO.Factory.newInstance();
			listaUnidadePorOrganizacaoVO.setIdOrganizacao(paramOrgano.getDadosAtuais().getIdOrganizacao());
			listaUnidadePorOrganizacaoVO = controlUsuario.listaUnidadePorOrganizacao(listaUnidadePorOrganizacaoVO, ConstantesCRM.getCurrentUser(request.getSession()));

			// Se existem dados.
			if (listaUnidadePorOrganizacaoVO.getUnidadeOrganogramaVOArray().length > 0) {
				listaUsuariosForm.setListaUnidadeOrganograma(listaUnidadePorOrganizacaoVO);
			} else {
				listaUsuariosForm.setListaUnidadeOrganograma(ListaUnidadePorOrganizacaoVO.Factory.newInstance());
			}
		}

		if (paramOrgano.getDadosAtuais() != null) {
			listaUsuariosForm.setDadosAtuais(paramOrgano.getDadosAtuais());
		} else {
			listaUsuariosForm.setDadosAtuais(NivelCargoOrgVO.Factory.newInstance().addNewDadosAtuais());
		}

		// Fim Organograma *****************
		if (getListaUsuariosForm().getListaSelects().getPerfilConsultorAtdVOArray() != null) {
			listaSelectsUsuarioVO.setPerfilConsultorAtdVOArray(getListaUsuariosForm().getListaSelects().getPerfilConsultorAtdVOArray());
		}

		if (getListaUsuariosForm().getListaSelects().getFornecedorConsultorAtdVOArray() != null) {
			listaSelectsUsuarioVO.setFornecedorConsultorAtdVOArray(getListaUsuariosForm().getListaSelects().getFornecedorConsultorAtdVOArray());
		}

		if (getListaUsuariosForm().getListaSelects().getSiteConsultorAtdVOArray() != null) {
			listaSelectsUsuarioVO.setSiteConsultorAtdVOArray(getListaUsuariosForm().getListaSelects().getSiteConsultorAtdVOArray());
		}

		if ((listaSelectsUsuarioVO.sizeOfCargoUsuarioVOArray() > 0) || (listaSelectsUsuarioVO.sizeOfDDDVOArray() > 0) || (listaSelectsUsuarioVO.sizeOfMotivoStatusUsuarioVOArray() > 0) || (listaSelectsUsuarioVO.sizeOfPaisVOArray() > 0) || (listaSelectsUsuarioVO.sizeOfStatusUsuarioVOArray() > 0) || (listaSelectsUsuarioVO.sizeOfTipoDocumentoVOArray() > 0) || (listaSelectsUsuarioVO.sizeOfUFOperadoraUsuarioVOArray() > 0) || (listaSelectsUsuarioVO.sizeOfUFVOArray() > 0)) {

			listaUsuariosForm.setListaSelects(listaSelectsUsuarioVO);
		}

		if (listaUsuariosForm.getListaUnidadeOrganograma() == null) {
			listaUsuariosForm.setListaUnidadeOrganograma(ListaUnidadePorOrganizacaoVO.Factory.newInstance());
		}

		ListaCargoPorNivelVO listaCargoPorNivelVO = ListaCargoPorNivelVO.Factory.newInstance();

		if (listaUsuariosForm.getListaCargo() == null) {
			listaUsuariosForm.setListaCargo(listaCargoPorNivelVO);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="cadastro" path="cadastrarUsuario.jsp"
	 * @jpf:forward name="edicao" path="editarUsuario.jsp"
	 * @jpf:forward name="pesquisa" path="pesquisarUsuario.jsp"
	 * @jpf:forward name="pesquisaH" path="innerBrowserHFuncional.jsp"
	 * @jpf:forward name="pesquisaO" path="innerBrowserOrganizacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward carregaComboOrgano(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ListaUsuariosForm form = (ListaUsuariosForm) formParam;
		log.debug("EditarUsuarioController:carregaComboOrgano" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		String acao = ConstantesCRM.SVAZIO;
		listaUsuariosForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			acao = (request.getParameter(ConstantesCRM.SACTION) != null) ? request.getParameter(ConstantesCRM.SACTION) : ConstantesCRM.SVAZIO;
			ListaUnidadePorOrganizacaoVO listaUnidadePorOrganizacaoVO = ListaUnidadePorOrganizacaoVO.Factory.newInstance();
			ListaCargoPorNivelVO listaCargoPorNivelVO = ListaCargoPorNivelVO.Factory.newInstance();

			// Carrega combo cargo
			if ("pesquisaH".equalsIgnoreCase(acao)) {
				if (!ConstantesCRM.SVAZIO.equals(form.getNivelOrganogramaAtual())) {
					listaCargoPorNivelVO.setIdNivel(form.getNivelOrganogramaAtual());
					listaUsuariosForm.setListaCargo(controlUsuario.listaCargoPorNivel(listaCargoPorNivelVO, ConstantesCRM.getCurrentUser(request.getSession())));
				}
				// Carrega combo departamento
			} else if ("pesquisaO".equalsIgnoreCase(acao)) {
				if (!ConstantesCRM.SVAZIO.equals(form.getOrganizacaoAtual())) {
					listaUnidadePorOrganizacaoVO.setIdOrganizacao(form.getOrganizacaoAtual());
					listaUsuariosForm.setListaUnidadeOrganograma(controlUsuario.listaUnidadePorOrganizacao(listaUnidadePorOrganizacaoVO, ConstantesCRM.getCurrentUser(request.getSession())));
				}
			}
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(acao);

		} catch (TuxedoWarningException twe) {
			log.warn("EditarUsuarioController:carregaComboOrgano" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			listaUsuariosForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("EditarUsuarioController:carregaComboOrgano" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("cadastro");
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="cadastro" path="cadastrarUsuario.jsp"
	 * @jpf:forward name="pesquisa" path="limpaPesquisa.do"
	 * @jpf:forward name="manterFiltro" path="pesquisarUsuario.jsp"
	 * @jpf:forward name="resultado" path="resultadoPesquisa.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("EditarUsuarioController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		ActionForward retorno = mapping.findForward("pesquisa");
		try {
			if (listaUsuariosForm == null) {
				listaUsuariosForm = new ListaUsuariosForm();
			}

			if (listaUsuariosForm.getArrayUsuariosVO() != null && listaUsuariosForm.getArrayUsuariosVO().getUsuarioVOArray().length > 0) {
				listaUsuariosForm.setArrayUsuariosLength(String.valueOf(listaUsuariosForm.getArrayUsuariosVO().getUsuarioVOArray().length));

			} else {
				if (request.getSession().getAttribute("formUser") != null) {
					// necessario para voltar de ediçao de parametros, onde chama outro jpf, perdendo a session.
					listaUsuariosForm = (ListaUsuariosForm) request.getSession().getAttribute("formUser");
				}
			}

			String tipo = request.getParameter("tipo");
			getListaUsuariosForm().setListaSuperioresImediatos(null);

			listaUsuariosForm.setMsgError(ConstantesCRM.SVAZIO);
			listaUsuariosForm.setDsDocAtual(ConstantesCRM.SVAZIO);
			if (tipo == null) {
				tipo = "pesquisa";
			}

			if (tipo.equals("cadastro")) {
				listaUsuariosForm = new ListaUsuariosForm();
				listaUsuariosForm.setIdUsuario(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setTipo("inclusao");
				listaUsuariosForm.setIdMotivoAtual(ConstantesCRM.SZERO);

				retorno = mapping.findForward("cadastro");

				// Busca listas de perfis de atendimento, Fornecedores e Sites
				UsuarioSimplVO usuarioPesquisa = UsuarioSimplVO.Factory.newInstance();
				ListaUsuariosVO aUsuariosVO = controlUsuario.pesquisaUsuarios(usuarioPesquisa, ConstantesCRM.getCurrentUser(request.getSession()));

				getListaUsuariosForm().getListaSelects().setPerfilConsultorAtdVOArray(aUsuariosVO.getPerfilConsultorAtdVOArray());
				getListaUsuariosForm().getListaSelects().setFornecedorConsultorAtdVOArray(aUsuariosVO.getFornecedorConsultorAtdVOArray());
				getListaUsuariosForm().getListaSelects().setSiteConsultorAtdVOArray(aUsuariosVO.getSiteConsultorAtdVOArray());

			} else if (tipo.equals("pesquisa")) {
				retorno = mapping.findForward("pesquisa");

			} else if (tipo.equals("manterFiltro")) {
				BeanUtils.copyProperties(listaUsuariosForm, listaUsuariosPesquisaForm);
				retorno = mapping.findForward("manterFiltro");

			} else if (tipo.equals("resultado")) {
				retorno = mapping.findForward("resultado");

			} else {
				retorno = mapping.findForward("cadastro");
			}

			// Servico para popular os selects dos combos da tela
			listaSelectsUsuarios(null, request);
			// request.getSession().removeAttribute("formUser");

		} catch (TuxedoWarningException twe) {
			log.warn("EditarUsuarioController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			listaUsuariosForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("EditarUsuarioController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/index.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return retorno;
	}

	/*
	 * Esta acao retorna para a pagina de pesquisa, a lista dos usuario encontrados de acordo com os parametros passados
	 * pela busca name="buscaVazia" path="EditarUsuarioController.jpf?tipo=pesquisa" name="buscaVazia"
	 * path="resultadoPesquisa.jsp"
	 */
	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="resultadoPesquisa.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward pesquisaUsuarios(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ListaUsuariosForm form = (ListaUsuariosForm) formParam;
		log.debug("EditarUsuarioController:pesquisaUsuarios" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		listaUsuariosForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			listaUsuariosForm.setTipoDocAtual(form.getTipoDocAtual());
			listaUsuariosPesquisaForm.setTipoDocAtual(form.getTipoDocAtual());
			// Objeto para armazenar o usuario (entrada da pesquisa)
			UsuarioSimplVO usuarioPesquisa = UsuarioSimplVO.Factory.newInstance();

			if (form.getPagina() == null || form.getPagina().equals(ConstantesCRM.SZERO) || form.getPagina().equals(ConstantesCRM.SVAZIO)) {
				form.setPagina(ConstantesCRM.SONE);
				listaUsuariosForm.setPagina(ConstantesCRM.SONE);
			}

			usuarioPesquisa.setNome(form.getNome());
			usuarioPesquisa.setSobrenome(form.getSobrenome());
			usuarioPesquisa.setNomeMeio(form.getNomeMeio());
			usuarioPesquisa.setLogin(form.getLogin());
			usuarioPesquisa.setLoginCti(form.getLoginCti());
			usuarioPesquisa.setLoginChefe(form.getLoginChefe());
			usuarioPesquisa.setIdCargoAtual(form.getIdCargoAtual());
			usuarioPesquisa.setIdUFOperadora(form.getIdUFOperadora());
			usuarioPesquisa.setIdStatusAtual(form.getIdStatusAtual());

			usuarioPesquisa.addNewOrganogramaUsuarioVO();
			usuarioPesquisa.getOrganogramaUsuarioVO().setIdCargo(form.getCargoAtual());
			usuarioPesquisa.getOrganogramaUsuarioVO().setIdNivel(form.getNivelOrganogramaAtual());
			usuarioPesquisa.getOrganogramaUsuarioVO().setIdOrganizacao(form.getOrganizacaoAtual());
			usuarioPesquisa.getOrganogramaUsuarioVO().setIdUnidade(form.getUnidadeOrganizacaoAtual());

			if (form.getRegistrosPPagina() == null || form.getRegistrosPPagina().equals(ConstantesCRM.SZERO) || form.getRegistrosPPagina().equals(ConstantesCRM.SVAZIO)) {
				usuarioPesquisa.setRegistrosPPagina(REGISTROS_POR_PAGINA);
			} else {
				usuarioPesquisa.setRegistrosPPagina(form.getRegistrosPPagina());
			}

			usuarioPesquisa.setPaginaAtual(form.getPaginaAtual());

			if (form.getTipoDocAtual() == null) {
				usuarioPesquisa.setIdDocAtual(ConstantesCRM.SVAZIO);
				usuarioPesquisa.setDsDocAtual(ConstantesCRM.SVAZIO);
			} else {
				usuarioPesquisa.setIdDocAtual(form.getTipoDocAtual());
				usuarioPesquisa.setDsDocAtual(form.getDsDocAtual());
			}

			usuarioPesquisa.addNewTipoDocumentoVO();
			usuarioPesquisa.getTipoDocumentoVO().setSgTipoDocumento(ConstantesCRM.SVAZIO);
			usuarioPesquisa.getTipoDocumentoVO().setDsTipoDocumento(ConstantesCRM.SVAZIO);
			usuarioPesquisa.setIdDocAtual(ConstantesCRM.SVAZIO);

			if (form.getTipoDocAtual() == null) {
				usuarioPesquisa.getTipoDocumentoVO().setIdTipoDocumento(ConstantesCRM.SVAZIO);
				usuarioPesquisa.setDsDocAtual(ConstantesCRM.SVAZIO);
			} else {
				usuarioPesquisa.getTipoDocumentoVO().setSgTipoDocumento(form.getTipoDocAtual());
				usuarioPesquisa.setDsDocAtual(form.getDsDocAtual());
			}

			// Objeto para armazenar a lista de usuarios (retorno da pesquisa)
			ListaUsuariosVO aUsuariosVO = ListaUsuariosVO.Factory.newInstance();

			// Chama a funcao do Controle que retorna a lista de usuarios.
			aUsuariosVO = controlUsuario.pesquisaUsuarios(usuarioPesquisa, ConstantesCRM.getCurrentUser(request.getSession()));
			/*
			 * for (int i = 0; i < aUsuariosVO.getUsuarioVOArray().length; i++) {
			 * aUsuariosVO.getUsuarioVOArray(i).setLoginChefe(aUsuariosVO. getUsuarioVOArray(i).getNomeChefe() + " (" +
			 * aUsuariosVO.getUsuarioVOArray(i).getLoginChefe() + ")"); }
			 */

			// Grava os dados de pesquisa
			listaUsuariosForm.setUsuarioPesquisa(usuarioPesquisa);
			// listaUsuariosForm.setTipoDocAtual(usuarioPesquisa.getTipoDocumentoVO().getIdTipoDocumento());
			listaUsuariosForm.setDsDocAtual(usuarioPesquisa.getDsDocAtual());
			listaUsuariosForm.setRegistrosPPagina(aUsuariosVO.getRegistrosPPagina());
			listaUsuariosForm.setPaginaAtual(aUsuariosVO.getPaginaAtual());

			listaUsuariosForm.setNome(form.getNome());
			listaUsuariosForm.setSobrenome(form.getSobrenome());
			listaUsuariosForm.setLogin(form.getLogin());
			listaUsuariosForm.setLoginCti(form.getLoginCti());
			listaUsuariosForm.setLoginChefe(form.getLoginChefe());
			listaUsuariosForm.setIdCargoAtual(form.getIdCargoAtual());
			listaUsuariosForm.setIdUFOperadora(form.getIdUFOperadora());
			listaUsuariosForm.setIdStatusAtual(form.getIdStatusAtual());
			listaUsuariosForm.setPagina(form.getPagina());

			listaUsuariosForm.setNivelOrganogramaAtual(form.getNivelOrganogramaAtual());
			listaUsuariosForm.setCargoAtual(form.getCargoAtual());
			listaUsuariosForm.setOrganizacaoAtual(form.getOrganizacaoAtual());
			listaUsuariosForm.setUnidadeOrganizacaoAtual(form.getUnidadeOrganizacaoAtual());

			if (form.getRegistrosPPagina() == null || form.getRegistrosPPagina().equals(ConstantesCRM.SZERO) || form.getRegistrosPPagina().equals(ConstantesCRM.SVAZIO)) {
				listaUsuariosForm.setRegistrosPPagina(REGISTROS_POR_PAGINA);
			} else {
				listaUsuariosForm.setRegistrosPPagina(form.getRegistrosPPagina());
			}

			if (getListaUsuariosForm().getListaSelects() == null) {
				getListaUsuariosForm().setListaSelects(UsuariosVO.Factory.newInstance());
			}

			if (aUsuariosVO.getPerfilConsultorAtdVOArray() != null && aUsuariosVO.getPerfilConsultorAtdVOArray().length > 0) {
				getListaUsuariosForm().getListaSelects().setPerfilConsultorAtdVOArray(aUsuariosVO.getPerfilConsultorAtdVOArray());
			}

			if (aUsuariosVO.getFornecedorConsultorAtdVOArray() != null && aUsuariosVO.getFornecedorConsultorAtdVOArray().length > 0) {
				getListaUsuariosForm().getListaSelects().setFornecedorConsultorAtdVOArray(aUsuariosVO.getFornecedorConsultorAtdVOArray());
			}

			if (aUsuariosVO.getSiteConsultorAtdVOArray() != null && aUsuariosVO.getSiteConsultorAtdVOArray().length > 0) {
				getListaUsuariosForm().getListaSelects().setSiteConsultorAtdVOArray(aUsuariosVO.getSiteConsultorAtdVOArray());
			}

			if (aUsuariosVO.getUsuarioVOArray().length > 0) {
				listaUsuariosForm.setArrayUsuariosVO(aUsuariosVO);
				listaUsuariosForm.setArrayUsuariosLength(String.valueOf(aUsuariosVO.getUsuarioVOArray().length));
			} else {
				listaUsuariosForm.setArrayUsuariosLength("-99");
			}

			request.getSession().setAttribute("formUser", listaUsuariosForm);
			BeanUtils.copyProperties(listaUsuariosPesquisaForm, listaUsuariosForm);

		} catch (TuxedoWarningException twe) {
			log.warn("EditarUsuarioController:pesquisaUsuarios" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			listaUsuariosForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("EditarUsuarioController:pesquisaUsuarios" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 */
	public ActionForward manageSuperioresImediatos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ListaUsuariosForm form = (ListaUsuariosForm) formParam;

		try {
			String user = ConstantesCRM.getCurrentUser(request.getSession());
			String operacao = request.getParameter("operacao");
			String out = ConstantesCRM.SVAZIO;
			int registrosPorPagina = 100;
			int paginaAtual = 1;

			if ("getListaSuperioresImediatos".equals(operacao)) {

				UsuarioSimplVO usuarioSimplVO = UsuarioSimplVO.Factory.newInstance();
				usuarioSimplVO.setIdPerfilConsultorAtdAtual(request.getParameter("idPerfil"));
				usuarioSimplVO.setRegistrosPPagina(Integer.toString(registrosPorPagina));
				ListaUsuariosVO listaSuperiores = ListaUsuariosVO.Factory.newInstance();

				// Paginação para busca de Superiores Imediatos em comunicação
				// com Tuxedo
				while (paginaAtual != 0) {

					usuarioSimplVO.setPaginaAtual(Integer.toString(paginaAtual));
					ListaUsuariosVO listaSuperioresImediatos = controlUsuario.pesquisaUsuarios(usuarioSimplVO, user);
					for (int i = 0; i < listaSuperioresImediatos.getUsuarioVOArray().length; i++) {
						listaSuperiores.addNewUsuarioVO().set(listaSuperioresImediatos.getUsuarioVOArray(i).copy());
					}

					if (ConstantesCRM.SZERO.equals(listaSuperioresImediatos.getPaginaAtual())) {
						paginaAtual = 0;
					} else {
						paginaAtual++;
					}
				}
				getListaUsuariosForm().setListaSuperioresImediatos(listaSuperiores);

			} else if ("isSuperiorImediatoAvailable".equals(operacao)) {
				String inputSuperiorImediato = request.getParameter("dsLoginSuperiorImediato");
				UsuarioVO retorno = UsuarioVO.Factory.newInstance();

				if ("true".equals(request.getParameter("inConsultor"))) {
					boolean mesmoFornecedor = false;
					UsuarioVO usuarioVO = null;
					try {
						usuarioVO = controlAcesso.carregaDadosUsuarioSessao("<login>" + inputSuperiorImediato + "</login>");
					} catch (Exception e) {
					}
					out = "<out>";
					if (usuarioVO != null) {
						String nmMeio = usuarioVO.getNomeMeio() != null && !ConstantesCRM.SVAZIO.equals(usuarioVO.getNomeMeio()) ? " " + usuarioVO.getNomeMeio() : ConstantesCRM.SVAZIO;
						String dsCampo = usuarioVO.getNome() + nmMeio + " " + usuarioVO.getSobrenome() + " (" + inputSuperiorImediato + ")";
						out += "<usuarioEncontrado>true</usuarioEncontrado>";
						out += "<nome>" + dsCampo + "</nome>";
					} else {
						out += "<usuarioEncontrado>false</usuarioEncontrado>";
					}

					if (usuarioVO != null) {
						if (usuarioVO.getIdFornecedorConsultorAtdAtual().equals(form.getIdFornecedor())) {
							mesmoFornecedor = true;
						}
					}
					out += "<mesmoFornecedor>" + mesmoFornecedor + "</mesmoFornecedor>";
					out += "</out>";

				} else {
					UsuarioSimplVO usuarioSimplVO = UsuarioSimplVO.Factory.newInstance();
					usuarioSimplVO.setIdPerfilConsultorAtdAtual(request.getParameter("idPerfil"));
					usuarioSimplVO.setRegistrosPPagina(Integer.toString(registrosPorPagina));
					usuarioSimplVO.setLogin(inputSuperiorImediato);

					ListaUsuariosVO listaSuperiores = controlUsuario.pesquisaUsuarios(usuarioSimplVO, user);
					getListaUsuariosForm().setListaSuperioresImediatos(listaSuperiores);

					for (int i = 0; i < getListaUsuariosForm().getListaSuperioresImediatos().getUsuarioVOArray().length; i++) {
						if (inputSuperiorImediato.equals(getListaUsuariosForm().getListaSuperioresImediatos().getUsuarioVOArray(i).getLogin())) {
							retorno = getListaUsuariosForm().getListaSuperioresImediatos().getUsuarioVOArray(i);
							break;
						}
					}
				}

				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
				response.setContentType(ConstantesCRM.SContentType);
				String retornoToXML = ClienteUtils.getCleanXMLFromXSD(retorno, null);
				retornoToXML = out.equals(ConstantesCRM.SVAZIO) ? retornoToXML : out;
				bufferedOutputStream.write(retornoToXML.getBytes(ConstantesCRM.SISO));
				bufferedOutputStream.flush();
				bufferedOutputStream.close();
			}

		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="mostraUser" path="detalheUsuario.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward mostraDetalhe(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ListaUsuariosForm form = (ListaUsuariosForm) formParam;

		log.debug("EditarUsuarioController:mostraDetalhe" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		listaUsuariosForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			// Recupero o ID do usuario clicado e converto p/ BigInteger
			String userId = request.getParameter("userId");

			// Recupero o array de usuarios do Form
			UsuarioVO[] arrayUsuarios = listaUsuariosForm.getArrayUsuariosVO().getUsuarioVOArray();

			// Procura o usuario correspondente ao id recebido
			UsuarioVO usuario = listaUsuariosForm.getUsuarioVOById(userId, arrayUsuarios);

			usuario.getDocumentoSimpVOArray(0).getTipoDocumentoVO().getSgTipoDocumento();

			// Joga para o formulario (atributo item Usuario) os dados do
			// usuário a ser editado
			if (usuario != null) {
				listaUsuariosForm.setUsuarioVO(usuario);
			}

			if (usuario != null && usuario.getDocumentoSimpVOArray() != null && usuario.getDocumentoSimpVOArray().length > 0) {
				listaUsuariosForm.getDocsUsuario().setDocumentoSimpVOArray(usuario.getDocumentoSimpVOArray());
			}

			// Popula usuario
			populaUsuario(request, usuario, userId);

			if (usuario.getDsTelefone() != null && usuario.getDsTelefone().length() > 4) {
				usuario.setDsTelefone(usuario.getDsTelefone().replaceAll("-", ConstantesCRM.SVAZIO));
				usuario.setDsTelefone(usuario.getDsTelefone().substring(0, usuario.getDsTelefone().length() - 4) + "-" + usuario.getDsTelefone().substring(usuario.getDsTelefone().length() - 4));
			}

			// Carrega Campos Organograma.
			NivelCargoOrgVO nivelCargoOrgVOParam = NivelCargoOrgVO.Factory.newInstance();
			nivelCargoOrgVOParam.setIdPessoa(userId);

			NivelCargoOrgVO nivelCargoOrgVO = controlUsuario.listaOrganograma(nivelCargoOrgVOParam, ConstantesCRM.getCurrentUser(request.getSession()));

			// Nivel
			if (nivelCargoOrgVO != null && nivelCargoOrgVO.getListaNivelOrganogramaVO() != null && nivelCargoOrgVO.getListaNivelOrganogramaVO().getNivelOrganogramaSimplVOArray() != null && nivelCargoOrgVO.getDadosAtuais() != null) {
				NivelOrganogramaSimplVO nivelOrganogramaSimplVO = getNivelById(nivelCargoOrgVO.getDadosAtuais().getIdNivel(), nivelCargoOrgVO.getListaNivelOrganogramaVO().getNivelOrganogramaSimplVOArray());
				if (nivelOrganogramaSimplVO != null && nivelOrganogramaSimplVO.getDsNivel() != null) {
					listaUsuariosForm.setDsNivel(nivelOrganogramaSimplVO.getDsNivel());
				} else {
					listaUsuariosForm.setDsNivel(ConstantesCRM.SVAZIO);
				}
			}

			// Cargo
			if (nivelCargoOrgVO != null && nivelCargoOrgVO.getListaCargoSimplVO() != null && nivelCargoOrgVO.getListaCargoSimplVO().getCargoSimplVOArray() != null && nivelCargoOrgVO.getDadosAtuais() != null) {
				CargoSimplVO cargoSimplVO = getCargoById(nivelCargoOrgVO.getDadosAtuais().getIdCargo(), nivelCargoOrgVO.getListaCargoSimplVO().getCargoSimplVOArray());
				if (cargoSimplVO != null && cargoSimplVO.getNmCargo() != null) {
					listaUsuariosForm.setDsCargo(cargoSimplVO.getNmCargo());
				} else {
					listaUsuariosForm.setDsCargo(ConstantesCRM.SVAZIO);
				}
			}

			// Unidade ( Dapartamento)
			if (nivelCargoOrgVO != null && nivelCargoOrgVO.getListaUnidadeOrganogramaVO() != null && nivelCargoOrgVO.getListaUnidadeOrganogramaVO().getUnidadeOrganogramaVOArray() != null && nivelCargoOrgVO.getDadosAtuais() != null) {
				UnidadeOrganogramaVO unidadeOrganogramaVO = getUnidadeById(nivelCargoOrgVO.getDadosAtuais().getIdUnidade(), nivelCargoOrgVO.getListaUnidadeOrganogramaVO().getUnidadeOrganogramaVOArray());
				if (unidadeOrganogramaVO != null && unidadeOrganogramaVO.getNmUnidade() != null) {
					listaUsuariosForm.setDsUnidade(unidadeOrganogramaVO.getNmUnidade());
				} else {
					listaUsuariosForm.setDsUnidade(ConstantesCRM.SVAZIO);
				}
			}

			// Organizaçao.
			if (nivelCargoOrgVO != null && nivelCargoOrgVO.getListaOrganizacaoVO() != null && nivelCargoOrgVO.getListaOrganizacaoVO().getOrganizacaoSimplVOArray() != null && nivelCargoOrgVO.getDadosAtuais() != null) {
				OrganizacaoSimplVO organizacaoSimplVO = getOrganizacaoById(nivelCargoOrgVO.getDadosAtuais().getIdOrganizacao(), nivelCargoOrgVO.getListaOrganizacaoVO().getOrganizacaoSimplVOArray());
				if (organizacaoSimplVO != null && organizacaoSimplVO.getDsTipoOrganizacao() != null) {
					listaUsuariosForm.setDsOrganizacao(organizacaoSimplVO.getDsTipoOrganizacao());
				} else {
					listaUsuariosForm.setDsOrganizacao(ConstantesCRM.SVAZIO);
				}
			}

		} catch (Exception e) {

			log.error("EditarUsuarioController:mostraDetalhe" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("mostraUser");
	}

	/*
	 * Acao que leva os dados do usuario escolhido para edicao, para a pagina de edicao.
	 */
	/**
	 * @jpf:action
	 * @jpf:forward name="editaUser" path="editarUsuario.jsp"
	 * @jpf:forward name="incluiUser" path="cadastrarUsuario.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward editaUsuario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ListaUsuariosForm form = (ListaUsuariosForm) formParam;

		log.debug("EditarUsuarioController:editaUsuario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		getListaUsuariosForm().setMsgError(ConstantesCRM.SVAZIO);
		String tipo = null;
		try {
			// Recupero o ID do usuario clicado
			String userId = (request.getParameter("userId") != null) ? request.getParameter("userId") : ConstantesCRM.SVAZIO;

			listaUsuariosForm.setPessoa((request.getParameter("pessoa") != null) ? request.getParameter("pessoa") : ConstantesCRM.SVAZIO);

			// Recupero o array de usuarios do Form
			UsuarioVO[] arrayUsuarios = listaUsuariosForm.getArrayUsuariosVO().getUsuarioVOArray();

			// Procura o usuario correspondente ao id recebido
			UsuarioVO usuario = listaUsuariosForm.getUsuarioVOById(userId, arrayUsuarios);
			// Servico para popular os selects dos combos das telas
			// carrega para VO, para carga dos combos de organograma.
			listaUsuariosForm.setLoginFOWAntigo(usuario.getLogin());

			// listaUsuariosForm.setIdUFAtual(usuario.getIdUF());

			NivelCargoOrgVO nivelCargoOrgVOParam = NivelCargoOrgVO.Factory.newInstance();
			nivelCargoOrgVOParam.setIdPessoa(userId);

			// listaSelectsUsuarios(nivelCargoOrgVOParam);
			NivelCargoOrgVO nivelCargoOrgVO = controlUsuario.listaOrganograma(nivelCargoOrgVOParam, ConstantesCRM.getCurrentUser(request.getSession()));

			listaSelectsUsuarios(nivelCargoOrgVO, request);

			// Joga para o formulario (atributo item Usuario) os dados do
			// usuário a ser editado
			listaUsuariosForm.setUsuarioVO(usuario);

			getListaUsuariosForm().setIdPerfilAtendimento(usuario.getIdPerfilConsultorAtdAtual());
			getListaUsuariosForm().setIdFornecedor(usuario.getIdFornecedorConsultorAtdAtual());
			getListaUsuariosForm().setIdSite(usuario.getIdSiteConsultorAtdAtual());
			getListaUsuariosForm().setDsLoginRoteamento(usuario.getDsLoginRoteamento());

			listaUsuariosForm.setInConsultor(usuario.getInConsultor());
			// listaUsuariosForm.setIdRegionalAtual(usuario.getIdUF());
			if (usuario != null && listaUsuariosForm.getDocsUsuario() != null) {
				listaUsuariosForm.getDocsUsuario().setDocumentoSimpVOArray(usuario.getDocumentoSimpVOArray());
			}

			if (listaUsuariosForm.getDocsUsuario().getDocumentoSimpVOArray() != null && listaUsuariosForm.getDocsUsuario().getDocumentoSimpVOArray().length > 0) {

				for (int i = 0; i < listaUsuariosForm.getDocsUsuario().getDocumentoSimpVOArray().length; i++) {

					if (listaUsuariosForm.getDocsUsuario().getDocumentoSimpVOArray(i).getTipoDocumentoVO().getSgTipoDocumento() != null && listaUsuariosForm.getDocsUsuario().getDocumentoSimpVOArray(i).getTipoDocumentoVO().getSgTipoDocumento().equals("CPF")) {
						listaUsuariosForm.setTipoDoc0(listaUsuariosForm.getDocsUsuario().getDocumentoSimpVOArray(i).getTipoDocumentoVO().getIdTipoDocumento());
						listaUsuariosForm.setSgDoc0(listaUsuariosForm.getDocsUsuario().getDocumentoSimpVOArray(i).getTipoDocumentoVO().getSgTipoDocumento());
						listaUsuariosForm.setIdDoc0(ConstantesCRM.SVAZIO);
						listaUsuariosForm.setDsDoc0(form.getDsDocAtual());
						listaUsuariosForm.setIdufDoc0(listaUsuariosForm.getDocsUsuario().getDocumentoSimpVOArray(i).getUFVO().getIdUF());
						listaUsuariosForm.setUfDoc0(listaUsuariosForm.getDocsUsuario().getDocumentoSimpVOArray(i).getUFVO().getSgUF());
						listaUsuariosForm.setIdpaisDoc0(form.getIdPaisDocAtual());
						listaUsuariosForm.setPaisDoc0(listaUsuariosForm.getDocsUsuario().getDocumentoSimpVOArray(i).getPaisVO().getNmPais());

					} else {
						listaUsuariosForm.setTipoDoc0(ConstantesCRM.SVAZIO);
						listaUsuariosForm.setSgDoc0(ConstantesCRM.SVAZIO);
						listaUsuariosForm.setIdDoc0(ConstantesCRM.SVAZIO);
						listaUsuariosForm.setDsDoc0(ConstantesCRM.SVAZIO);
						listaUsuariosForm.setIdufDoc0(ConstantesCRM.SVAZIO);
						listaUsuariosForm.setUfDoc0(ConstantesCRM.SVAZIO);
						listaUsuariosForm.setIdpaisDoc0(ConstantesCRM.SVAZIO);
						listaUsuariosForm.setPaisDoc0(ConstantesCRM.SVAZIO);

					}

					if (listaUsuariosForm.getDocsUsuario().getDocumentoSimpVOArray(i).getTipoDocumentoVO().getSgTipoDocumento() != null && listaUsuariosForm.getDocsUsuario().getDocumentoSimpVOArray(i).getTipoDocumentoVO().getSgTipoDocumento().equals("RG")) {
						listaUsuariosForm.setTipoDoc1(listaUsuariosForm.getDocsUsuario().getDocumentoSimpVOArray(i).getTipoDocumentoVO().getIdTipoDocumento());
						listaUsuariosForm.setSgDoc1(listaUsuariosForm.getDocsUsuario().getDocumentoSimpVOArray(i).getTipoDocumentoVO().getSgTipoDocumento());
						listaUsuariosForm.setIdDoc1(ConstantesCRM.SVAZIO);
						listaUsuariosForm.setDsDoc1(form.getDsDocAtual());
						listaUsuariosForm.setIdufDoc1(listaUsuariosForm.getDocsUsuario().getDocumentoSimpVOArray(i).getUFVO().getIdUF());
						listaUsuariosForm.setUfDoc1(listaUsuariosForm.getDocsUsuario().getDocumentoSimpVOArray(i).getUFVO().getSgUF());
						listaUsuariosForm.setIdpaisDoc1(form.getIdPaisDocAtual());
						listaUsuariosForm.setPaisDoc1(listaUsuariosForm.getDocsUsuario().getDocumentoSimpVOArray(i).getPaisVO().getNmPais());

					} else {
						listaUsuariosForm.setTipoDoc1(ConstantesCRM.SVAZIO);
						listaUsuariosForm.setSgDoc1(ConstantesCRM.SVAZIO);
						listaUsuariosForm.setIdDoc1(ConstantesCRM.SVAZIO);
						listaUsuariosForm.setDsDoc1(ConstantesCRM.SVAZIO);
						listaUsuariosForm.setIdufDoc1(ConstantesCRM.SVAZIO);
						listaUsuariosForm.setUfDoc1(ConstantesCRM.SVAZIO);
						listaUsuariosForm.setIdpaisDoc1(ConstantesCRM.SVAZIO);
						listaUsuariosForm.setPaisDoc1(ConstantesCRM.SVAZIO);

					}

					if (listaUsuariosForm.getDocsUsuario().getDocumentoSimpVOArray(i).getTipoDocumentoVO().getSgTipoDocumento() != null && listaUsuariosForm.getDocsUsuario().getDocumentoSimpVOArray(i).getTipoDocumentoVO().getSgTipoDocumento().equals("RE")) {
						listaUsuariosForm.setTipoDoc2(listaUsuariosForm.getDocsUsuario().getDocumentoSimpVOArray(i).getTipoDocumentoVO().getIdTipoDocumento());
						listaUsuariosForm.setSgDoc2(listaUsuariosForm.getDocsUsuario().getDocumentoSimpVOArray(i).getTipoDocumentoVO().getSgTipoDocumento());
						listaUsuariosForm.setIdDoc2(ConstantesCRM.SVAZIO);
						listaUsuariosForm.setDsDoc2(form.getDsDocAtual());
						listaUsuariosForm.setIdufDoc2(listaUsuariosForm.getDocsUsuario().getDocumentoSimpVOArray(i).getUFVO().getIdUF());
						listaUsuariosForm.setUfDoc2(listaUsuariosForm.getDocsUsuario().getDocumentoSimpVOArray(i).getUFVO().getSgUF());
						listaUsuariosForm.setIdpaisDoc2(form.getIdPaisDocAtual());
						listaUsuariosForm.setPaisDoc2(listaUsuariosForm.getDocsUsuario().getDocumentoSimpVOArray(i).getPaisVO().getNmPais());

					} else {
						listaUsuariosForm.setTipoDoc2(ConstantesCRM.SVAZIO);
						listaUsuariosForm.setSgDoc2(ConstantesCRM.SVAZIO);
						listaUsuariosForm.setIdDoc2(ConstantesCRM.SVAZIO);
						listaUsuariosForm.setDsDoc2(ConstantesCRM.SVAZIO);
						listaUsuariosForm.setIdufDoc2(ConstantesCRM.SVAZIO);
						listaUsuariosForm.setUfDoc2(ConstantesCRM.SVAZIO);
						listaUsuariosForm.setIdpaisDoc2(ConstantesCRM.SVAZIO);
						listaUsuariosForm.setPaisDoc2(ConstantesCRM.SVAZIO);
					}
				}
			}

			if (nivelCargoOrgVO.getDadosAtuais() != null) {
				listaUsuariosForm.setDadosAtuais(nivelCargoOrgVO.getDadosAtuais());
			}

			listaUsuariosForm.setListaCargo(ListaCargoPorNivelVODocument.Factory.newInstance().addNewListaCargoPorNivelVO());
			if (nivelCargoOrgVO.getListaCargoSimplVO() != null && nivelCargoOrgVO.getListaCargoSimplVO().getCargoSimplVOArray() != null) {
				for (int i = 0; i < nivelCargoOrgVO.getListaCargoSimplVO().getCargoSimplVOArray().length; i++) {
					listaUsuariosForm.getListaCargo().addNewCargoVO();
					listaUsuariosForm.getListaCargo().getCargoVOArray(i).setIdCargo(nivelCargoOrgVO.getListaCargoSimplVO().getCargoSimplVOArray(i).getIdCargo());
					listaUsuariosForm.getListaCargo().getCargoVOArray(i).setNmCargo(nivelCargoOrgVO.getListaCargoSimplVO().getCargoSimplVOArray(i).getNmCargo());
				}
			}

			// Recupero o tipo se edicao ou inclusao
			tipo = request.getParameter("tipo");
			if (tipo == null) {
				tipo = "inclusao";
			}

			usuario.setIdUsuario(userId);
			if (usuario != null && usuario.getUFOperadoraPessoaVOArray() != null && usuario.getUFOperadoraPessoaVOArray().length > 0) {
				listaUsuariosForm.setIdRegionalAtual(usuario.getUFOperadoraPessoaVOArray(0).getIdUFOperadora());
			}

			listaUsuariosForm.setIdUsuario(userId);
			listaUsuariosForm.setTipo(tipo);
			listaUsuariosForm.getUsuarioVO().setLoginCti(usuario.getLoginCti());

			// Popula usuario
			populaUsuario(request, usuario, userId);

			listaUsuariosForm.setIdUFAtual(usuario.getIdUF());
			// listaUsuariosForm.setIdUFAtual(usuario.getNmUF());
			listaUsuariosForm.setDataExpiracao(getDataExpiracaoLDAP(usuario.getLogin(), request));

		} catch (TuxedoWarningException twe) {
			log.warn("EditarUsuarioController:editaUsuario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			listaUsuariosForm.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("EditarUsuarioController:listaPaginas" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("editaUser");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="editaAbas" path="editarRelUsuario.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward editaRelUsuario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ListaUsuariosForm form = (ListaUsuariosForm) formParam;
		log.debug("EditarUsuarioController:editaRelUsuario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		try {
			// Recupero o ID do usuario clicado e converto p/ BigInteger
			String userId = (request.getParameter("userId") != null) ? request.getParameter("userId") : ConstantesCRM.SVAZIO;

			// Joga para o formulario (atributo item Usuario) os dados do
			// usuário a ser editado
			listaUsuariosForm.setUsuarioVO(listaUsuariosForm.getUsuarioVOById(userId, listaUsuariosForm.getArrayUsuariosVO().getUsuarioVOArray()));

			// Popula usuario
			populaUsuario(request, listaUsuariosForm.getUsuarioVOById(userId, listaUsuariosForm.getArrayUsuariosVO().getUsuarioVOArray()), (request.getParameter("userId") != null) ? request.getParameter("userId") : ConstantesCRM.SVAZIO);

			// necessario para voltar de ediçao de parametros, onde chama outro
			// jpf, perdendo a session.
			request.getSession().setAttribute("formUser", listaUsuariosForm);

		} catch (Exception e) {
			log.error("EditarUsuarioController:editaRelUsuario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("editaAbas");
	}

	protected void populaUsuario(HttpServletRequest request, UsuarioVO usuario, String userId) {
		listaUsuariosForm.setFlgDoc01(ConstantesCRM.SVAZIO);
		listaUsuariosForm.setFlgDoc02(ConstantesCRM.SVAZIO);
		listaUsuariosForm.setFlgDoc03(ConstantesCRM.SVAZIO);

		// Monta o objeto usuario que sera salvo
		listaUsuariosForm.setIdUsuario(usuario.getIdUsuario());
		listaUsuariosForm.setNome(usuario.getNome());
		listaUsuariosForm.setSobrenome(usuario.getSobrenome());
		listaUsuariosForm.setEmail(usuario.getEmail());
		listaUsuariosForm.setLogin(usuario.getLogin());
		listaUsuariosForm.setLoginCti(usuario.getLoginCti());

		String loginChefe = usuario.getLoginChefe();
		if (usuario.getLoginChefe().indexOf("(") >= 0) {
			loginChefe = loginChefe.substring(loginChefe.indexOf("(") + 1, loginChefe.indexOf(")"));
		}

		listaUsuariosForm.setDsLoginRoteamento(usuario.getDsLoginRoteamento());
		listaUsuariosForm.setLoginChefe(loginChefe);
		listaUsuariosForm.setNomeChefe(usuario.getNomeChefe());
		listaUsuariosForm.setDtInclusao(usuario.getDtInclusao());
		listaUsuariosForm.setDtExclusao(usuario.getDtExclusao());
		listaUsuariosForm.setDtInicio(usuario.getDtInicio());
		listaUsuariosForm.setDtRetorno(usuario.getDtRetorno());
		listaUsuariosForm.setDsTelefone(usuario.getDsTelefone());
		listaUsuariosForm.setIdDDDAtual(usuario.getIdDDD());
		listaUsuariosForm.setIdMotivoAtual(usuario.getIdMotivo());
		listaUsuariosForm.setIdUFAtual(usuario.getIdUF());
		listaUsuariosForm.setIdStatusAtual(usuario.getIdStatusAtual());
		listaUsuariosForm.setIdCargoAtual(usuario.getIdCargoAtual());


		for (int aux = 0; aux < usuario.getDocumentoSimpVOArray().length; aux++) {
			if (usuario.getDocumentoSimpVOArray(aux).getTipoDocumentoVO().getSgTipoDocumento().equals("CPF")) {
				DocumentoSimpVO documentoSimpVO = usuario.getDocumentoSimpVOArray(aux);
				listaUsuariosForm.setIdDoc0(documentoSimpVO.getIdDocumento());
				listaUsuariosForm.setDsDoc0(documentoSimpVO.getDsDocumento());
				listaUsuariosForm.setSgDoc0(documentoSimpVO.getTipoDocumentoVO().getSgTipoDocumento());
				listaUsuariosForm.setTipoDoc0(documentoSimpVO.getTipoDocumentoVO().getIdTipoDocumento());
				listaUsuariosForm.setUfDoc0(documentoSimpVO.getUFVO().getNmUF());
				listaUsuariosForm.setIdufDoc0(documentoSimpVO.getUFVO().getIdUF());
				listaUsuariosForm.setPaisDoc0(documentoSimpVO.getPaisVO().getNmPais());
				listaUsuariosForm.setIdpaisDoc0(documentoSimpVO.getPaisVO().getIdPais());
				listaUsuariosForm.setFlgDoc01("CPF");
			}
			if (usuario.getDocumentoSimpVOArray(aux).getTipoDocumentoVO().getSgTipoDocumento().equals("RG")) {
				DocumentoSimpVO documentoSimpVO = usuario.getDocumentoSimpVOArray(aux);
				listaUsuariosForm.setIdDoc1(documentoSimpVO.getIdDocumento());
				listaUsuariosForm.setDsDoc1(documentoSimpVO.getDsDocumento());
				listaUsuariosForm.setSgDoc1(documentoSimpVO.getTipoDocumentoVO().getSgTipoDocumento());
				listaUsuariosForm.setTipoDoc1(documentoSimpVO.getTipoDocumentoVO().getIdTipoDocumento());
				listaUsuariosForm.setUfDoc1(documentoSimpVO.getUFVO().getNmUF());
				listaUsuariosForm.setIdufDoc1(documentoSimpVO.getUFVO().getIdUF());
				listaUsuariosForm.setPaisDoc1(documentoSimpVO.getPaisVO().getNmPais());
				listaUsuariosForm.setIdpaisDoc1(documentoSimpVO.getPaisVO().getIdPais());
				listaUsuariosForm.setFlgDoc02("RG");

			}
			if (usuario.getDocumentoSimpVOArray(aux).getTipoDocumentoVO().getSgTipoDocumento().equals("RE")) {
				DocumentoSimpVO documentoSimpVO = usuario.getDocumentoSimpVOArray(aux);
				listaUsuariosForm.setIdDoc2(documentoSimpVO.getIdDocumento());
				listaUsuariosForm.setDsDoc2(documentoSimpVO.getDsDocumento());
				listaUsuariosForm.setSgDoc2(documentoSimpVO.getTipoDocumentoVO().getSgTipoDocumento());
				listaUsuariosForm.setTipoDoc2(documentoSimpVO.getTipoDocumentoVO().getIdTipoDocumento());
				listaUsuariosForm.setUfDoc2(documentoSimpVO.getUFVO().getNmUF());
				listaUsuariosForm.setIdufDoc2(documentoSimpVO.getUFVO().getIdUF());
				listaUsuariosForm.setPaisDoc2(documentoSimpVO.getPaisVO().getNmPais());
				listaUsuariosForm.setIdpaisDoc2(documentoSimpVO.getPaisVO().getIdPais());
				listaUsuariosForm.setFlgDoc03("RE");

			}
		}
	}

	protected UsuarioVO recuperaUsuario(String userId, ListaUsuariosForm form) {

		// Monta o objeto usuario que será salvo
		UsuarioVO usuario = UsuarioVO.Factory.newInstance();

		// variavel auxiliar para utilizar no 'loop for'
		int aux;

		usuario.setIdUsuario(userId);
		usuario.setNome(form.getNome());
		// usuario.setSobrenome(form.getSobrenome());
		usuario.setEmail(form.getEmail());
		usuario.setLogin(form.getLogin());
		usuario.setLoginCti(form.getLoginCti());

		String loginChefe = form.getLoginChefe();
		String nomeChefe = ConstantesCRM.SVAZIO;
		if (loginChefe.indexOf("(") >= 0 && loginChefe.indexOf("(") >= 0) {
			loginChefe = form.getLoginChefe().substring(form.getLoginChefe().indexOf("(") + 1, form.getLoginChefe().indexOf(")"));
			nomeChefe = form.getLoginChefe().substring(0, form.getLoginChefe().indexOf("(") - 1);
		}

		usuario.setLoginChefe(loginChefe);
		usuario.setNomeChefe(nomeChefe);
		usuario.setDsLoginRoteamento(form.getDsLoginRoteamento());
		usuario.setIdPerfilConsultorAtdAtual(form.getIdPerfilAtendimento());
		usuario.setIdSiteConsultorAtdAtual(form.getIdSite());
		usuario.setIdFornecedorConsultorAtdAtual(form.getIdFornecedor());
		usuario.setDsLoginRoteamento(form.getDsLoginRoteamento());
		listaUsuariosForm.setIdPerfilAtendimento(form.getIdPerfilAtendimento());
		listaUsuariosForm.setIdSite(form.getIdSite());
		listaUsuariosForm.setIdFornecedor(form.getIdFornecedor());
		listaUsuariosForm.setLoginChefe(loginChefe);
		listaUsuariosForm.setNomeChefe(nomeChefe);
		listaUsuariosForm.setDsLoginRoteamento(form.getDsLoginRoteamento());
		listaUsuariosForm.setInConsultor(form.getInConsultor());

		usuario.setDtInclusao(form.getDtInclusao());
		usuario.setDtExclusao(form.getDtExclusao());
		usuario.setDtInicio(form.getDtInicio());
		usuario.setDtRetorno(form.getDtRetorno());
		usuario.setDsTelefone(form.getDsTelefone());
		usuario.setInConsultor(form.getInConsultor());
		usuario.setIdStatusAtual(form.getIdStatusAtual());

		usuario.setIdDDD(form.getIdDDDAtual());
		for (aux = 0; aux < listaUsuariosForm.getListaSelects().getDDDVOArray().length; aux++) {
			if (listaUsuariosForm.getListaSelects().getDDDVOArray(aux).getIdDDD().equals(usuario.getIdDDD())) {
				DDDVO dddVO = DDDVO.Factory.newInstance();
				dddVO = listaUsuariosForm.getListaSelects().getDDDVOArray(aux);
				usuario.setDsDDD(dddVO.getDsDDD());
			}
		}

		usuario.setIdMotivo(form.getIdMotivoAtual());
		for (aux = 0; aux < listaUsuariosForm.getListaSelects().getMotivoStatusUsuarioVOArray().length; aux++) {
			if (listaUsuariosForm.getListaSelects().getMotivoStatusUsuarioVOArray(aux).getIdMotivo().equals(usuario.getIdMotivo())) {
				MotivoStatusUsuarioVO motivoStatusUsuarioVO = MotivoStatusUsuarioVO.Factory.newInstance();
				motivoStatusUsuarioVO = listaUsuariosForm.getListaSelects().getMotivoStatusUsuarioVOArray(aux);
				usuario.setDsMotivo(motivoStatusUsuarioVO.getDsMotivo());
			}
		}

		usuario.setIdUF(form.getIdUFAtual());
		for (aux = 0; aux < listaUsuariosForm.getListaSelects().getUFVOArray().length; aux++) {
			if (listaUsuariosForm.getListaSelects().getUFVOArray(aux).getIdUF().equals(usuario.getIdUF())) {
				UFVO ufVO = UFVO.Factory.newInstance();
				ufVO = listaUsuariosForm.getListaSelects().getUFVOArray(aux);
				usuario.setDsMotivo(ufVO.getNmUF());
				if (ufVO.getNmUF() != null && !ufVO.getNmUF().equals(ConstantesCRM.SVAZIO)) {
					usuario.setNmUF(ufVO.getNmUF());
				}
				listaUsuariosForm.setNmUF(ufVO.getNmUF());
			}
		}

		usuario.setIdStatusAtual(form.getIdStatusAtual());
		for (aux = 0; aux < listaUsuariosForm.getListaSelects().getStatusUsuarioVOArray().length; aux++) {
			if (listaUsuariosForm.getListaSelects().getStatusUsuarioVOArray(aux).getIdStatus().equals(usuario.getIdStatusAtual())) {
				StatusUsuarioVO statusUsuarioVO = StatusUsuarioVO.Factory.newInstance();
				statusUsuarioVO = listaUsuariosForm.getListaSelects().getStatusUsuarioVOArray(aux);
				usuario.setDescricaoStatusAtual(statusUsuarioVO.getNmStatus());
			}
		}

		usuario.setIdCargoAtual(form.getCargoAtual());
		listaUsuariosForm.getDadosAtuais().setIdCargo(form.getCargoAtual());
		for (aux = 0; aux < listaUsuariosForm.getListaSelects().getCargoUsuarioVOArray().length; aux++) {
			if (listaUsuariosForm.getListaSelects().getCargoUsuarioVOArray(aux).getIdCargo().equals(usuario.getIdCargoAtual())) {
				CargoUsuarioVO cargoUsuarioVO = CargoUsuarioVO.Factory.newInstance();
				cargoUsuarioVO = listaUsuariosForm.getListaSelects().getCargoUsuarioVOArray(aux);
				usuario.setDescricaoCargoAtual(cargoUsuarioVO.getNmCargo());
			}
		}

		int indice = 0;
		if ((listaUsuariosForm.getTipoDoc0() != null) && (!(listaUsuariosForm.getTipoDoc0().equals(ConstantesCRM.SVAZIO)))) {
			DocumentoSimpVO documentoSimpVO = DocumentoSimpVO.Factory.newInstance();
			documentoSimpVO.addNewTipoDocumentoVO();
			for (aux = 0; aux < listaUsuariosForm.getListaSelects().getTipoDocumentoVOArray().length; aux++) {
				if (listaUsuariosForm.getListaSelects().getTipoDocumentoVOArray(aux).getSgTipoDocumento().equals(listaUsuariosForm.getTipoDoc0()) || listaUsuariosForm.getListaSelects().getTipoDocumentoVOArray(aux).getIdTipoDocumento().equals(listaUsuariosForm.getTipoDoc0())) {
					TipoDocumentoVO tipoDocumentoVO = TipoDocumentoVO.Factory.newInstance();
					tipoDocumentoVO = listaUsuariosForm.getListaSelects().getTipoDocumentoVOArray(aux);
					documentoSimpVO.setIdDocumento(listaUsuariosForm.getIdDoc0());
					documentoSimpVO.setDsDocumento(listaUsuariosForm.getDsDoc0());
					documentoSimpVO.setTipoDocumentoVO((tipoDocumentoVO));
				}
			}

			documentoSimpVO.addNewUFVO();
			for (aux = 0; aux < listaUsuariosForm.getListaSelects().getUFVOArray().length; aux++) {
				if (listaUsuariosForm.getListaSelects().getUFVOArray(aux).getIdUF().equals(form.getIdufDoc0())) {
					UFVO ufVO = UFVO.Factory.newInstance();
					ufVO = listaUsuariosForm.getListaSelects().getUFVOArray(aux);
					documentoSimpVO.setUFVO(ufVO);
				}
			}

			documentoSimpVO.addNewPaisVO();
			for (aux = 0; aux < listaUsuariosForm.getListaSelects().getPaisVOArray().length; aux++) {
				if (listaUsuariosForm.getListaSelects().getPaisVOArray(aux).getIdPais().equals(listaUsuariosForm.getIdpaisDoc0())) {
					PaisVO paisVO = PaisVO.Factory.newInstance();
					paisVO = listaUsuariosForm.getListaSelects().getPaisVOArray(aux);
					documentoSimpVO.setPaisVO(paisVO);
				}
			}

			usuario.addNewDocumentoSimpVO();
			usuario.setDocumentoSimpVOArray(indice, documentoSimpVO);
			indice++;
		}

		if ((listaUsuariosForm.getTipoDoc1() != null) && (!(listaUsuariosForm.getTipoDoc1().equals(ConstantesCRM.SVAZIO)))) {
			DocumentoSimpVO documentoSimpVO = DocumentoSimpVO.Factory.newInstance();
			documentoSimpVO.addNewTipoDocumentoVO();
			for (aux = 0; aux < listaUsuariosForm.getListaSelects().getTipoDocumentoVOArray().length; aux++) {
				if (listaUsuariosForm.getListaSelects().getTipoDocumentoVOArray(aux).getSgTipoDocumento().equals(listaUsuariosForm.getTipoDoc1()) || listaUsuariosForm.getListaSelects().getTipoDocumentoVOArray(aux).getIdTipoDocumento().equals(listaUsuariosForm.getTipoDoc1())) {
					TipoDocumentoVO tipoDocumentoVO = TipoDocumentoVO.Factory.newInstance();
					tipoDocumentoVO = listaUsuariosForm.getListaSelects().getTipoDocumentoVOArray(aux);
					documentoSimpVO.setIdDocumento(listaUsuariosForm.getIdDoc1());
					documentoSimpVO.setDsDocumento(listaUsuariosForm.getDsDoc1());
					documentoSimpVO.setTipoDocumentoVO((tipoDocumentoVO));
				}
			}

			documentoSimpVO.addNewUFVO();
			for (aux = 0; aux < listaUsuariosForm.getListaSelects().getUFVOArray().length; aux++) {
				if (listaUsuariosForm.getListaSelects().getUFVOArray(aux).getIdUF().equals(listaUsuariosForm.getIdufDoc1())) {
					UFVO ufVO = UFVO.Factory.newInstance();
					ufVO = listaUsuariosForm.getListaSelects().getUFVOArray(aux);
					documentoSimpVO.setUFVO(ufVO);
				}
			}

			documentoSimpVO.addNewPaisVO();
			for (aux = 0; aux < listaUsuariosForm.getListaSelects().getPaisVOArray().length; aux++) {
				if (listaUsuariosForm.getListaSelects().getPaisVOArray(aux).getIdPais().equals(listaUsuariosForm.getIdpaisDoc1())) {
					PaisVO paisVO = PaisVO.Factory.newInstance();
					paisVO = listaUsuariosForm.getListaSelects().getPaisVOArray(aux);
					documentoSimpVO.setPaisVO(paisVO);
				}
			}
			usuario.addNewDocumentoSimpVO();
			usuario.setDocumentoSimpVOArray(indice, documentoSimpVO);
			indice++;
		}

		if ((listaUsuariosForm.getTipoDoc2() != null) && (!(listaUsuariosForm.getTipoDoc2().equals(ConstantesCRM.SVAZIO)))) {
			DocumentoSimpVO documentoSimpVO = DocumentoSimpVO.Factory.newInstance();
			documentoSimpVO.addNewTipoDocumentoVO();
			for (aux = 0; aux < listaUsuariosForm.getListaSelects().getTipoDocumentoVOArray().length; aux++) {
				if (listaUsuariosForm.getListaSelects().getTipoDocumentoVOArray(aux).getSgTipoDocumento().equals(listaUsuariosForm.getTipoDoc2()) || listaUsuariosForm.getListaSelects().getTipoDocumentoVOArray(aux).getIdTipoDocumento().equals(listaUsuariosForm.getTipoDoc2())) {
					TipoDocumentoVO tipoDocumentoVO = TipoDocumentoVO.Factory.newInstance();
					tipoDocumentoVO = listaUsuariosForm.getListaSelects().getTipoDocumentoVOArray(aux);
					documentoSimpVO.setIdDocumento(listaUsuariosForm.getIdDoc2());
					documentoSimpVO.setDsDocumento(listaUsuariosForm.getDsDoc2());
					documentoSimpVO.setTipoDocumentoVO((tipoDocumentoVO));
				}
			}

			documentoSimpVO.addNewUFVO();
			for (aux = 0; aux < listaUsuariosForm.getListaSelects().getUFVOArray().length; aux++) {
				if (listaUsuariosForm.getListaSelects().getUFVOArray(aux).getIdUF().equals(listaUsuariosForm.getIdufDoc2())) {
					UFVO ufVO = UFVO.Factory.newInstance();
					ufVO = listaUsuariosForm.getListaSelects().getUFVOArray(aux);
					documentoSimpVO.setUFVO(ufVO);
				}
			}

			documentoSimpVO.addNewPaisVO();
			for (aux = 0; aux < listaUsuariosForm.getListaSelects().getPaisVOArray().length; aux++) {
				if (listaUsuariosForm.getListaSelects().getPaisVOArray(aux).getIdPais().equals(listaUsuariosForm.getIdpaisDoc2())) {
					PaisVO paisVO = PaisVO.Factory.newInstance();
					paisVO = listaUsuariosForm.getListaSelects().getPaisVOArray(aux);
					documentoSimpVO.setPaisVO(paisVO);
				}
			}

			usuario.addNewDocumentoSimpVO();
			usuario.setDocumentoSimpVOArray(indice, documentoSimpVO);
			indice++;
		}
		return usuario;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="salvaUser" path="resultadoPesquisa.jsp"
	 * @jpf:forward name="editaErro" path="editarUsuario.jsp"
	 * @jpf:forward name="inseriErro" path="cadastrarUsuario.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	/*
	 * Chama a funcao do Controle que salva os dados do usuario. name="salvaUser" path="pesquisarUsuario.jsp"
	 */
	public ActionForward salvaUsuario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ListaUsuariosForm form = (ListaUsuariosForm) formParam;

		log.debug("EditarUsuarioController:salvaUsuario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		listaUsuariosForm.setMsgError(ConstantesCRM.SVAZIO);
		UsuarioLDAPVO usuarioLDAPVO = null;
		String tipo = null;
		String acao = null;
		UsuarioVO usuario = null;
		String usuarioLogin = form.getLogin();
		// String idUsuario = ConstantesCRM.SVAZIO;
		boolean erroLDAP = false;
		LDAPUtil ldap = null;
		try {
			ldap = new LDAPUtil(request);
			// listaUsuariosForm = form;
			// boolean indicativoLdap = false;
			// boolean inAltera = false;
			// boolean inInclui = false;
			// Recupero o ID do usuario clicado no caso de edicao
			String userId = (form.getIdUsuario() != null) ? form.getIdUsuario() : ConstantesCRM.SVAZIO;

			// Recupero o tipo se edicao ou inclusao
			tipo = (form.getTipo() != null) ? form.getTipo() : ConstantesCRM.SVAZIO;
			acao = (request.getParameter(ConstantesCRM.SACTION) != null) ? request.getParameter(ConstantesCRM.SACTION) : ConstantesCRM.SVAZIO;
			listaUsuariosForm.getDadosAtuais().setIdUnidade(form.getUnidadeOrganizacaoAtual());
			listaUsuariosForm.getDadosAtuais().setIdCargo(form.getCargoAtual());
			listaUsuariosForm.setIdUFAtual(form.getIdUFAtual());
			// Servico para popular os selects dos combos da tela
			// listaSelectsUsuarios(null);

			// Correçao erro as 4:00.
			if (form.getDsDoc0() != null && !form.getDsDoc0().equals(ConstantesCRM.SVAZIO)) {
				String strDsDoc0 = form.getDsDoc0();
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < strDsDoc0.length(); i++) {
					if (!strDsDoc0.substring(i, i + 1).equals(".") && !strDsDoc0.substring(i, i + 1).equals("-")) {
						sb.append(strDsDoc0.substring(i, i + 1));
					}
				}
				form.setDsDoc0(sb.toString());
				listaUsuariosForm.setDsDoc0(sb.toString());
			}

			// Recupera informaçoes do form para popular o UsuarioVO
			usuario = recuperaUsuario(userId, form);

			// #### Alterar.
			if (form.getNome() != null && form.getNome().trim().length() > 0) {
				if (form.getNome().trim().indexOf(" ") != -1) {
					usuario.setNome(form.getNome().trim().substring(0, form.getNome().trim().indexOf(" ")));
					usuario.setSobrenome(form.getNome().trim().substring(form.getNome().trim().indexOf(" ")));

				} else {
					usuario.setNome(form.getNome().trim());
					usuario.setSobrenome(".");
				}
			}

			listaUsuariosForm.setUsuarioVO(usuario);
			usuario.addNewUFOperadoraPessoaVO().setIdUFOperadora(form.getIdRegionalAtual());

			// ################################################## LDAP INICIO

			if (tipo.equals("inclusao")) {
				log.debug("EditarUsuarioController:salvaUsuario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> Tipo Inclusão de Usuario.");
				try {
					UsuarioLDAPVO userLDAPVO = UsuarioLDAPVO.Factory.newInstance();
					userLDAPVO.setLogin(form.getLogin());
					userLDAPVO.setIdUsuario(form.getIdUsuario());
					userLDAPVO.setNome(usuario.getNome());
					userLDAPVO.setSobrenome(usuario.getSobrenome());

					inseriLDAP(userLDAPVO, usuario.getEmail(), request);

				} catch (Exception ex) {
					log.error("EditarUsuarioController:salvaUsuario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", ex);
					erroLDAP = true;
					request.setAttribute("msgError", MSG_ERRO);
					listaUsuariosForm.setMsgError(MSG_ERRO);
				}
			} else

				// Habilita / Desabilita Usuario LDAP.
				if (!erroLDAP && tipo.equals("edicao")) {
					log.debug("EditarUsuarioController:salvaUsuario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> Tipo Ediçao de Usuario.");
					try {
						// Se houve alteraçao no login ForntOfficeWeb.
						if ((form.getLogin() != null && form.getLoginFOWAntigo() != null) && (!form.getLogin().trim().equals(form.getLoginFOWAntigo().trim()))) {
							ldap.apagarUsuario(form.getLoginFOWAntigo());

							UsuarioLDAPVO userLDAPVO = UsuarioLDAPVO.Factory.newInstance();

							userLDAPVO.setLogin(form.getLogin());
							userLDAPVO.setIdUsuario(form.getIdUsuario());
							userLDAPVO.setNome(usuario.getNome());
							userLDAPVO.setSobrenome(usuario.getSobrenome());

							inseriLDAP(userLDAPVO, usuario.getEmail(), request);
						}

						if (listaUsuariosForm != null && listaUsuariosForm.getListaSelects() != null && listaUsuariosForm.getListaSelects().getStatusUsuarioVOArray().length > 0) {
							StatusUsuarioVO statusVO = getStatusById(form.getIdStatusAtual(), listaUsuariosForm.getListaSelects().getStatusUsuarioVOArray());

							if (statusVO != null && statusVO.getSgStatus() != null && statusVO.getSgStatus().trim().equalsIgnoreCase("ATIVO")) {
								desbloqueiaUsuario(form.getLogin(), request);
							} else {
								bloqueiaUsuario(form.getLogin(), request);
							}
						}

					} catch (Exception e) {
						erroLDAP = true;
						log.error("EditarUsuarioController:salvaUsuario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
						request.setAttribute("msgError", MSG_ERRO);
					}
				}

			if (!erroLDAP && tipo.equals("inclusao")) {
				try {
					if (listaUsuariosForm != null && listaUsuariosForm.getListaSelects() != null && listaUsuariosForm.getListaSelects().getStatusUsuarioVOArray().length > 0) {
						StatusUsuarioVO statusVO = getStatusById(form.getIdStatusAtual(), listaUsuariosForm.getListaSelects().getStatusUsuarioVOArray());
						if (statusVO != null && statusVO.getSgStatus() != null && statusVO.getSgStatus().trim().equals("ATIVO")) {
							desbloqueiaUsuario(form.getLogin(), request);
						} else {
							bloqueiaUsuario(form.getLogin(), request);
						}
					}
				} catch (Exception e) {
					erroLDAP = true;
					log.error("EditarUsuarioController:salvaUsuario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
					request.setAttribute("msgError", MSG_ERRO);
				}
			}
			// ################################################## LDAP FIM

			if (!erroLDAP) {
				if (tipo.equals("inclusao")) {
					usuarioLDAPVO = controlUsuario.salvaUsuario(usuario, ConstantesCRM.getCurrentUser(request.getSession()));

				} else if (tipo.equals("edicao")) {
					controlUsuario.salvarUsuarioEditado(usuario, ConstantesCRM.getCurrentUser(request.getSession()));
				}

				// prepara dados para envio.
				NivelCargoOrgDeptoRelacionamentoVO nivelCargoOrgDeptoRelacionamentoVO = NivelCargoOrgDeptoRelacionamentoVO.Factory.newInstance();

				if (usuarioLDAPVO != null && !usuarioLDAPVO.getIdUsuario().trim().equals(ConstantesCRM.SVAZIO)) {
					nivelCargoOrgDeptoRelacionamentoVO.setIdPessoa(usuarioLDAPVO.getIdUsuario());
				} else {
					nivelCargoOrgDeptoRelacionamentoVO.setIdPessoa(usuario.getIdUsuario());
				}

				nivelCargoOrgDeptoRelacionamentoVO.addNewDadosAtuais();
				nivelCargoOrgDeptoRelacionamentoVO.getDadosAtuais().setIdCargo(form.getCargoAtual());
				nivelCargoOrgDeptoRelacionamentoVO.getDadosAtuais().setIdNivel(form.getNivelOrganogramaAtual());
				nivelCargoOrgDeptoRelacionamentoVO.getDadosAtuais().setIdOrganizacao(form.getOrganizacaoAtual());
				nivelCargoOrgDeptoRelacionamentoVO.getDadosAtuais().setIdUnidade(form.getUnidadeOrganizacaoAtual());

				// inseri relacionamento em HierarquiaDeptoPessoa.
				controlUsuario.salvaHierarquiaDeptoPessoa(nivelCargoOrgDeptoRelacionamentoVO, ConstantesCRM.getCurrentUser(request.getSession()));
				request.setAttribute("msgError", ConstantesCRM.SSucesso);
			}
		} catch (TuxedoWarningException twe) {
			if (acao.equals("inclusao")) {
				try {
					ldap.apagarUsuario(usuarioLogin);
				} catch (Exception e) {
					log.error("EditarUsuarioController:salvaUsuario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
				}
			}
			if (twe != null && twe.getMessage() != null && twe.getMessage().indexOf(']') >= 0) {
				listaUsuariosForm.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));
			} else {
				listaUsuariosForm.setMsgError(twe.getMessage());
			}

			if (form.getNmUF() != null && !form.getNmUF().trim().equals(ConstantesCRM.SVAZIO)) {
				usuario.setNmUF(form.getNmUF());
			}

			usuario.setNome(form.getNome());
			listaUsuariosForm.setIdRegionalAtual(form.getIdRegionalAtual());

			log.warn("EditarUsuarioController:salvaUsuario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MSG --> " + twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

			if (acao.equals("edicao")) {
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("editaErro");
			} else {
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("inseriErro");
			}

		} catch (Exception e) {
			log.error("EditarUsuarioController:salvaUsuario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		if (tipo.equals("inclusao") && !erroLDAP) {
			usuario = recuperaUsuario(usuarioLDAPVO.getIdUsuario(), form);
		} else if (erroLDAP) {
			listaUsuariosForm.setMsgError(MSG_ERRO);
		}

		ListaUsuariosVO listaUsuariosVO = ListaUsuariosVO.Factory.newInstance();
		UsuarioVO[] user = new UsuarioVO[1];

		if (form.getNmUF() != null && !form.getNmUF().trim().equals(ConstantesCRM.SVAZIO)) {
			usuario.setNmUF(form.getNmUF());
		}

		usuario.setIdStatusAtual(form.getIdStatusAtual());
		usuario.setNome(form.getNome());
		user[0] = usuario;
		listaUsuariosVO.setUsuarioVOArray(user);
		listaUsuariosForm.setArrayUsuariosVO(listaUsuariosVO);
		listaUsuariosForm.setPagina("1");
		listaUsuariosForm.setPaginaAtual("0");
		listaUsuariosForm.getDocsUsuario().setDocumentoSimpVOArray(usuario.getDocumentoSimpVOArray());

		if (erroLDAP) {
			if (tipo.equals("inclusao")) {
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("inseriErro");
			}
			else if (tipo.equals("edicao")) {
				listaUsuariosForm.setMsgError(MSG_ERRO);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("editaErro");
			}
		}

		if (request.getParameter("dsSite") != null) {
			request.getSession().setAttribute(ConstantesCRM.USUARIO_SITE, request.getParameter("dsSite"));
		}

		if (request.getParameter("dsFornecedor") != null) {
			request.getSession().setAttribute(ConstantesCRM.USUARIO_FORNECEDOR, request.getParameter("dsFornecedor"));
		}
		
		if(!erroLDAP && tipo.equals("edicao") && form.getDataExpiracao() != null){
			String data = form.dataExpiracao;
			
			if(data.contains("/")){
				data = data.trim().replace("/", "");
			}
			
			try{
				alterarDataExpiracaoLDAP(form.login, data, "0", request);
			}catch(LDAPException le){
				listaUsuariosForm.setMsgError(MSG_ERRO);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("editaErro");
			}
		}
		
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("salvaUser");
	}

	private void inseriLDAP(UsuarioLDAPVO usuarioLDAPVO, String correio,HttpServletRequest request ) throws Exception {

		log.debug("EditarUsuarioController:inseriLDAP" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		try {
			LDAPUtil ldap = new LDAPUtil(request);
			LDAPAttribute attr1 = new LDAPAttribute("cn", usuarioLDAPVO.getNome());
			LDAPAttribute attr2 = new LDAPAttribute("sn", usuarioLDAPVO.getSobrenome());
			LDAPAttribute attr3 = new LDAPAttribute("objectclass", "top");
			LDAPAttribute attr4 = new LDAPAttribute("objectclass", "person");
			LDAPAttribute attr5 = new LDAPAttribute("objectclass", "organizationalPerson");
			LDAPAttribute attr6 = new LDAPAttribute("objectclass", "inetOrgPerson");
			LDAPAttribute attr7 = new LDAPAttribute("mail", correio);
			LDAPAttribute attr8 = new LDAPAttribute("userPassword", ConstantesCRM.PASSWORD_RESET);
			LDAPAttribute attr9 = new LDAPAttribute("title", "true");

			LDAPAttributeSet myAttrs = new LDAPAttributeSet();
			myAttrs.add(attr1);
			myAttrs.add(attr2);
			myAttrs.add(attr3);
			myAttrs.add(attr4);
			myAttrs.add(attr5);
			myAttrs.add(attr6);
			myAttrs.add(attr7);
			myAttrs.add(attr8);
			myAttrs.add(attr9);

			ldap.setAdicionaUsuario(usuarioLDAPVO.getLogin(), myAttrs);
			ldap.adicionaUsuarioGrupo("uniquemember", usuarioLDAPVO.getLogin());

		} catch (Exception e) {
			log.error("EditarUsuarioController:inseriLDAP( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
		}
	}
	
	public boolean alterarDataExpiracaoLDAP(String login, String date, String setWarn, HttpServletRequest request) throws LDAPException {
		log.debug("Inicio EditarUsuarioController.inseriLDAP");
		boolean retorno = false;
				
		try{			
			if(date.length() != 8 ){
				throw new Exception();
			}else{
				String day = date.substring(0, 2);
				String month = date.substring(2, 4);
				String year = date.substring(4);
				
				if(Integer.parseInt(day) > 31 || Integer.parseInt(day) == 0){
					throw new Exception();
				}else{
					if(Integer.parseInt(month) > 12 || Integer.parseInt(month) == 0){
						throw new Exception();
					}else{
						if(Integer.parseInt(year) == 0){
							throw new Exception();
						}else{
							LDAPUtil ldap = new LDAPUtil(request);
							ldap.modificaAtributo(login, "passwordexpirationtime", String.format("%s%s%s000001Z", year, month, day));
							ldap.modificaAtributo(login, "passwordexpwarned", setWarn);
							ldap.modificaAtributo(login, "title", "false");
						}
					}
				}
			}
			
			retorno = true;
		} catch(Exception e){
			if(e instanceof LDAPException){
				log.error("EditarUsuarioController:alterarDataExpiracaoLDAP( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			}else{
				log.error("EditarUsuarioController:alterarDataExpiracaoLDAP( " + ConstantesCRM.getCurrentUser(request.getSession()) + " - Data informada fora do padrao.)", e);
			}
		}

		log.debug("Fim EditarUsuarioController.inseriLDAP");
		return retorno;
	}
	
	public String getDataExpiracaoLDAP(String login, HttpServletRequest request) throws LDAPException {
		log.debug("Inicio EditarUsuarioController.getDataExpiracaoLDAP");
		String retorno = null;
				
		try{
			LDAPUtil ldap = new LDAPUtil(request);
			String date = ldap.getAtributoValor(login, "passwordexpirationtime");
				
			if(date != null && date.length() < 8 ){
				throw new Exception();
			}else{
				String day = date.substring(6, 8);
				String month = date.substring(4, 6);
				String year = date.substring(0, 4);
				
				retorno = String.format("%s/%s/%s", day, month, year);
			}
		} catch(Exception e){
				if(e instanceof LDAPException){
					log.error("EditarUsuarioController:getDataExpiracaoLDAP( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
				}else{
					log.error("EditarUsuarioController:getDataExpiracaoLDAP( " + ConstantesCRM.getCurrentUser(request.getSession()) + " - Data informada fora do padrao.)", e);
				}
		}

		log.debug("Fim EditarUsuarioController.getDataExpiracaoLDAPP");
		return retorno;
	}

	private void bloqueiaUsuario(String login, HttpServletRequest request) throws Exception {
		log.debug("EditarUsuarioController:bloqueiaUsuario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) (LDAP)");
		LDAPUtil ldap = new LDAPUtil(request);
		ldap.modificaAtributo(login, "nsaccountlock", "true");
	}

	private void desbloqueiaUsuario(String login, HttpServletRequest request) throws Exception {
		log.debug("EditarUsuarioController:desbloqueiaUsuario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) (LDAP)");
		LDAPUtil ldap = new LDAPUtil(request);
		ldap.modificaAtributo(login, "nsaccountlock", "false");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="incluiAlteraDoc" path="cadastrarUsuario.jsp"
	 * @jpf:forward name="edita" path="editarUsuario.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward incluiDocumento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ListaUsuariosForm form = (ListaUsuariosForm) formParam;

		log.debug("EditarUsuarioController:incluiDocumento" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		listaUsuariosForm.setMsgError(ConstantesCRM.SVAZIO);
		String tipo = (request.getParameter("tipo") != null) ? request.getParameter("tipo") : ConstantesCRM.SVAZIO;
		try {
			// Servico para popular os selects dos combos da tela
			// carregaComboOrgano(form);
			// Recupera informaçoes do form para popular o UsuarioVO
			UsuarioVO usuario = recuperaUsuario(ConstantesCRM.SVAZIO, form);

			String nmUf = (request.getParameter("nmUF") != null) ? request.getParameter("nmUF") : ConstantesCRM.SVAZIO;
			usuario.setNmUF(nmUf);

			listaUsuariosForm.getDadosAtuais().setIdUnidade(form.getUnidadeOrganizacaoAtual());
			listaUsuariosForm.getDadosAtuais().setIdCargo(form.getCargoAtual());
			listaUsuariosForm.getDadosAtuais().setIdNivel(form.getNivelOrganogramaAtual());
			listaUsuariosForm.getDadosAtuais().setIdOrganizacao(form.getOrganizacaoAtual());

			listaUsuariosForm.setIdDDDAtual(form.getIdDDDAtual());
			listaUsuariosForm.setInConsultor(form.getInConsultor());
			listaUsuariosForm.setIdRegionalAtual(form.getIdRegionalAtual());
			listaUsuariosForm.setIdUFAtual(form.getIdUFAtual());
			listaUsuariosForm.setUsuarioVO(usuario);

			int aux;
			String sg = ConstantesCRM.SVAZIO;
			String uf = ConstantesCRM.SVAZIO;
			String pais = ConstantesCRM.SVAZIO;
			for (aux = 0; aux < listaUsuariosForm.getListaSelects().getTipoDocumentoVOArray().length; aux++) {
				if (listaUsuariosForm.getListaSelects().getTipoDocumentoVOArray(aux).getIdTipoDocumento().equals(form.getTipoDocAtual()) || listaUsuariosForm.getListaSelects().getTipoDocumentoVOArray(aux).getSgTipoDocumento().equals(form.getTipoDocAtual())) {
					TipoDocumentoVO tipoDocumentoVO = TipoDocumentoVO.Factory.newInstance();
					tipoDocumentoVO = listaUsuariosForm.getListaSelects().getTipoDocumentoVOArray(aux);
					sg = (tipoDocumentoVO.getSgTipoDocumento() != null) ? tipoDocumentoVO.getSgTipoDocumento() : ConstantesCRM.SVAZIO;
					break;
				}
			}
			for (aux = 0; aux < listaUsuariosForm.getListaSelects().getUFVOArray().length; aux++) {
				if (listaUsuariosForm.getListaSelects().getUFVOArray(aux).getIdUF().equals(form.getIdUFDocAtual())) {
					UFVO ufVO = UFVO.Factory.newInstance();
					ufVO = listaUsuariosForm.getListaSelects().getUFVOArray(aux);
					uf = (ufVO.getSgUF() != null) ? ufVO.getSgUF() : ConstantesCRM.SVAZIO;
					break;
				}
			}
			for (aux = 0; aux < listaUsuariosForm.getListaSelects().getPaisVOArray().length; aux++) {
				if (listaUsuariosForm.getListaSelects().getPaisVOArray(aux).getIdPais().equals(form.getIdPaisDocAtual())) {
					PaisVO paisVO = PaisVO.Factory.newInstance();
					paisVO = listaUsuariosForm.getListaSelects().getPaisVOArray(aux);
					pais = (paisVO.getNmPais() != null) ? paisVO.getNmPais() : ConstantesCRM.SVAZIO;
					break;
				}
			}

			if (form.getTipoDocAtual() != null && !form.getTipoDocAtual().equals(ConstantesCRM.SVAZIO) && sg.equals(ConstantesCRM.SVAZIO)) {
				sg = form.getTipoDocAtual();
			}

			if (sg.equals("CPF")) {
				listaUsuariosForm.setTipoDoc0(form.getTipoDocAtual());
				listaUsuariosForm.setSgDoc0(sg);
				listaUsuariosForm.setIdDoc0(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setDsDoc0(form.getDsDocAtual());
				listaUsuariosForm.setIdufDoc0(form.getIdUFDocAtual());
				listaUsuariosForm.setUfDoc0(uf);
				listaUsuariosForm.setIdpaisDoc0(form.getIdPaisDocAtual());
				listaUsuariosForm.setPaisDoc0(pais);
			}
			if (sg.equals("RG")) {
				listaUsuariosForm.setTipoDoc1(form.getTipoDocAtual());
				listaUsuariosForm.setSgDoc1(sg);
				listaUsuariosForm.setIdDoc1(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setDsDoc1(form.getDsDocAtual());
				listaUsuariosForm.setIdufDoc1(form.getIdUFDocAtual());
				listaUsuariosForm.setUfDoc1(uf);
				listaUsuariosForm.setIdpaisDoc1(form.getIdPaisDocAtual());
				listaUsuariosForm.setPaisDoc1(pais);
			}
			if (sg.equals("RE")) {
				listaUsuariosForm.setTipoDoc2(form.getTipoDocAtual());
				listaUsuariosForm.setSgDoc2(sg);
				listaUsuariosForm.setIdDoc2(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setDsDoc2(form.getDsDocAtual());
				listaUsuariosForm.setIdufDoc2(form.getIdUFDocAtual());
				listaUsuariosForm.setUfDoc2(uf);
				listaUsuariosForm.setIdpaisDoc2(form.getIdPaisDocAtual());
				listaUsuariosForm.setPaisDoc2(pais);
			}

		} catch (Exception e) {
			log.error("EditarUsuarioController:incluiDocumento" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		if (tipo.equals("edita")) {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("edita");
		} else {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("incluiAlteraDoc");
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="limpaUser" path="cadastrarUsuario.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward limpaCadastro(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("EditarUsuarioController:limpaCadastro" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		listaUsuariosForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			listaUsuariosForm = new ListaUsuariosForm();
			// Servico para popular os selects dos combos da tela
			listaSelectsUsuarios(null, request);

		} catch (TuxedoWarningException twe) {
			listaUsuariosForm.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("EditarUsuarioController:limpaCadastro" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("limpaUser");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="excluiDoc" path="cadastrarUsuario.jsp"
	 * @jpf:forward name="excluiDocAlt" path="editarUsuario.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward excluiDocumento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ListaUsuariosForm form = (ListaUsuariosForm) formParam;

		log.debug("EditarUsuarioController:excluiDocumento" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		listaUsuariosForm.setMsgError(ConstantesCRM.SVAZIO);
		String tipo = ConstantesCRM.SVAZIO;
		try {
			// listaUsuariosForm = form;
			// Servico para popular os selects dos combos da tela
			// carregaComboOrganograma(form);
			UsuarioVO usuario = recuperaUsuario(ConstantesCRM.SVAZIO, form);

			String nmUf = (request.getParameter("nmUF") != null) ? request.getParameter("nmUF") : ConstantesCRM.SVAZIO;
			usuario.setNmUF(nmUf);

			listaUsuariosForm.getDadosAtuais().setIdUnidade(form.getUnidadeOrganizacaoAtual());
			listaUsuariosForm.setUsuarioVO(usuario);

			tipo = (request.getParameter(ConstantesCRM.SACTION) != null) ? request.getParameter(ConstantesCRM.SACTION) : ConstantesCRM.SVAZIO;
			int aux;
			String sg = new String();
			for (aux = 0; aux < listaUsuariosForm.getListaSelects().getTipoDocumentoVOArray().length; aux++) {
				if (listaUsuariosForm.getListaSelects().getTipoDocumentoVOArray(aux).getSgTipoDocumento().equals(form.getTipoDocAtual())) {
					TipoDocumentoVO tipoDocumentoVO = TipoDocumentoVO.Factory.newInstance();
					tipoDocumentoVO = listaUsuariosForm.getListaSelects().getTipoDocumentoVOArray(aux);
					sg = tipoDocumentoVO.getSgTipoDocumento();
				}
			}

			if ((form.getTipoDocAtualEdita() != null && tipo.equals("edicao")) || (form.getTipoDocAtual() != null && !form.getTipoDocAtual().equals(ConstantesCRM.SVAZIO) && sg.equals(ConstantesCRM.SVAZIO))) {
				sg = form.getTipoDocAtualEdita();
			}

			if (sg.equals("CPF")) {
				listaUsuariosForm.setTipoDoc0(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setSgDoc0(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setIdDoc0(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setDsDoc0(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setIdufDoc0(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setUfDoc0(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setIdpaisDoc0(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setPaisDoc0(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setFlgDoc01(ConstantesCRM.SVAZIO);
			}
			if (sg.equals("RG")) {
				listaUsuariosForm.setTipoDoc1(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setSgDoc1(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setIdDoc1(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setDsDoc1(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setIdufDoc1(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setUfDoc1(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setIdpaisDoc1(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setPaisDoc1(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setFlgDoc02(ConstantesCRM.SVAZIO);
			}
			if (sg.equals("RE")) {
				listaUsuariosForm.setTipoDoc2(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setSgDoc2(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setIdDoc2(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setDsDoc2(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setIdufDoc2(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setUfDoc2(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setIdpaisDoc2(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setPaisDoc2(ConstantesCRM.SVAZIO);
				listaUsuariosForm.setFlgDoc03(ConstantesCRM.SVAZIO);
			}

		} catch (Exception e) {
			log.error("EditarUsuarioController:excluiDocumento" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		if (tipo.equals("edicao")) {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("excluiDocAlt");
		} else {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("excluiDoc");
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="resultadoPesquisa.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward bloqueiaUsuario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ListaUsuariosForm form = (ListaUsuariosForm) formParam;

		log.debug("EditarUsuarioController:bloqueiaUsuario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		listaUsuariosForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			LDAPUtil ldap = new LDAPUtil(request);
			ldap.modificaAtributo(form.getLogin(), "nsaccountlock", "true");
			ldap.modificaAtributo(form.getLogin(), "passwordRetryCount", "0");
		} catch (Exception twe) {
			log.error("EditarUsuarioController:bloqueiaUsuario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", twe);
			listaUsuariosForm.setMsgError("Falha na comunicação com host.");
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="resultadoPesquisa.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward desbloqueiaUsuario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ListaUsuariosForm form = (ListaUsuariosForm) formParam;

		log.debug("EditarUsuarioController:desbloqueiaUsuario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		listaUsuariosForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			LDAPUtil ldap = new LDAPUtil(request);
			ldap.modificaAtributo(form.getLogin(), "nsaccountlock", "false");
			ldap.modificaAtributo(form.getLogin(), "passwordRetryCount", "0");
		} catch (Exception ex) {
			log.error("EditarUsuarioController:desbloqueiaUsuario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", ex);
			listaUsuariosForm.setMsgError("Falha na comunicação com host.");
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="resultadoPesquisa.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward reinicializaSenha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ListaUsuariosForm form = (ListaUsuariosForm) formParam;

		log.debug("EditarUsuarioController:reinicializaSenha" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		listaUsuariosForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			LDAPUtil ldap = new LDAPUtil(request);
			ldap.modificaAtributo(form.getLogin(), "userPassword", ConstantesCRM.PASSWORD_RESET);
			try {
				ldap.modificaAtributo(form.getLogin(), "title", "true");
			} catch (Exception e) {
				ldap.addAtributo(form.getLogin(), "title", "true");
			}
		} catch (Exception ex) {
			log.error("EditarUsuarioController:reinicializaSenha" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", ex);
			listaUsuariosForm.setMsgError("Falha na comunicação com host.");
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisarUsuario.jsp"
	 */
	public ActionForward limpaPesquisa(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ListaUsuariosForm form = (ListaUsuariosForm) formParam;

		log.debug("EditarUsuarioController:limpaPesquisa" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		listaUsuariosForm.setNome(ConstantesCRM.SVAZIO);
		listaUsuariosForm.setSobrenome(ConstantesCRM.SVAZIO);
		listaUsuariosForm.setCargoAtual(ConstantesCRM.SVAZIO);
		listaUsuariosForm.setOrganizacaoAtual(ConstantesCRM.SVAZIO);
		listaUsuariosForm.setCargoAtual(ConstantesCRM.SVAZIO);
		listaUsuariosForm.setUnidadeOrganizacaoAtual(ConstantesCRM.SVAZIO);
		listaUsuariosForm.setNivelOrganogramaAtual(ConstantesCRM.SVAZIO);
		listaUsuariosForm.setLogin(ConstantesCRM.SVAZIO);
		listaUsuariosForm.setLoginChefe(ConstantesCRM.SVAZIO);
		listaUsuariosForm.setLoginCti(ConstantesCRM.SVAZIO);
		listaUsuariosForm.setRegistrosPPagina(ConstantesCRM.SVAZIO);
		listaUsuariosForm.setIdRegionalAtual(ConstantesCRM.SVAZIO);
		listaUsuariosForm.setIdStatusAtual(ConstantesCRM.SVAZIO);
		listaUsuariosForm.setDsDocAtual(ConstantesCRM.SVAZIO);
		listaUsuariosForm.setUsuarioPesquisa(UsuarioSimplVODocument.Factory.newInstance().addNewUsuarioSimplVO());
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class ListaUsuariosForm extends ActionForm {

		private static final long serialVersionUID = 7746422404442787642L;

		private String pessoa;
		private String nomeMeio;
		private String loginFOWAntigo;
		private String nmUF;
		private String inConsultor;
		private String tipoDocAtualEdita;
		private String flgDoc03;
		private String flgDoc02;
		private String flgDoc01;
		private String dsOrganizacao;
		private String dsUnidade;
		private String dsNivel;
		private String dsCargo;
		private String msgError = ConstantesCRM.SVAZIO;
		private br.com.vivo.fo.usuario.vo.NivelCargoOrgVODocument.NivelCargoOrgVO.DadosAtuais dadosAtuais;
		private String nivelOrganogramaAtual;
		private String unidadeOrganizacaoAtual;
		private String organizacaoAtual;
		private String cargoAtual;
		private ListaUnidadePorOrganizacaoVO listaUnidadeOrganograma;
		private ListaOrganizacaoVO listaOrganizacao;
		private ListaNivelOrganogramaVO listaNivelOrganograma;
		private ListaCargoPorNivelVO listaCargo;
		private String pagina;
		private String arrayUsuariosLength;
		private String paginaAtual;
		private String registrosPPagina;
		private String dsTelefone;
		private String idUsuario;
		private String sgDoc2;
		private String sgDoc1;
		private String sgDoc0;
		private String nomeChefe;
		private String dtExclusao;
		private String dtInclusao;
		private String idDoc2;
		private String idDoc1;
		private String idDoc0;
		private String tipo;
		private String email;
		private String idpaisDoc2;
		private String idpaisDoc1;
		private String idpaisDoc0;
		private String idufDoc2;
		private String idufDoc1;
		private String idufDoc0;
		private String paisDoc2;
		private String paisDoc1;
		private String paisDoc0;
		private String ufDoc2;
		private String ufDoc1;
		private String ufDoc0;
		private String dsDoc2;
		private String dsDoc1;
		private String dsDoc0;
		private String tipoDoc2;
		private String tipoDoc1;
		private String tipoDoc0;
		private String idUFDocAtual;
		private String idPaisDocAtual;
		private String idDDDAtual;
		private String idUFAtual;
		private String descricaoCargoAtual;
		private String descricaoStatusAtual;
		private String sobrenome;
		private String nome;
		private String loginChefe;
		private String loginCti;
		private String login;
		private String dtRetorno;
		private String dtInicio;
		private String tipoDocAtual;
		private String dsDocAtual;
		// armazena a lista de documentos de um usuário
		private ListaDocumentoSimpVO docsUsuario;
		// variaveis que armazenam os retornos das chamadas ao tuxedo
		private ListaUsuariosVO arrayUsuariosVO;
		// variavel que armazena todos os dados do Usuario
		private UsuarioVO usuarioVO;
		// variavel que armazena os dados de pesquisa
		private UsuarioSimplVO usuarioPesquisa;
		// armazena as lista de selects de um usuário em uma tela
		private UsuariosVO listaSelects;
		// variaveis que armazenam os objetos tratados dentro das paginas jsps
		private String idStatusAtual;
		private String idRegionalAtual;
		private String idMotivoAtual;
		private String idCargoAtual;
		private String idUFOperadora;
		private String idSite;
		private String idPerfilAtendimento;
		private String idFornecedor;
		private ListaUsuariosVO listaSuperioresImediatos;
		private String dsLoginRoteamento;
		private String dataExpiracao;

		public ListaUsuariosForm() {
			this.arrayUsuariosVO = ListaUsuariosVO.Factory.newInstance();
			this.docsUsuario = ListaDocumentoSimpVO.Factory.newInstance();
			this.usuarioVO = UsuarioVO.Factory.newInstance();
			this.usuarioPesquisa = UsuarioSimplVO.Factory.newInstance();

			this.listaSelects = UsuariosVO.Factory.newInstance();
			this.listaSelects.setStatusUsuarioVOArray(new StatusUsuarioVO[0]);
			this.listaSelects.setMotivoStatusUsuarioVOArray(new MotivoStatusUsuarioVO[0]);
			this.listaSelects.setCargoUsuarioVOArray(new CargoUsuarioVO[0]);
			this.listaSelects.setUFOperadoraUsuarioVOArray(new UFOperadoraUsuarioVO[0]);
			this.listaSelects.setTipoDocumentoVOArray(new TipoDocumentoVO[0]);
			this.listaSelects.setPaisVOArray(new PaisVO[0]);
		}

		public void setUsuarioVO(UsuarioVO usuarioVO) {
			this.usuarioVO = usuarioVO;
		}

		public UsuarioVO getUsuarioVO() {
			return this.usuarioVO;
		}

		public void setArrayUsuariosVO(ListaUsuariosVO arrayUsuariosVO) {
			this.arrayUsuariosVO = arrayUsuariosVO;
		}

		public ListaUsuariosVO getArrayUsuariosVO() {
			return this.arrayUsuariosVO;
		}

		public void setListaSuperioresImediatos(ListaUsuariosVO arg) {
			this.listaSuperioresImediatos = arg;
		}

		public ListaUsuariosVO getListaSuperioresImediatos() {
			return this.listaSuperioresImediatos;
		}

		public UsuarioVO getUsuarioVOById(String id, UsuarioVO[] usuarios) {
			for (int i = 0; i < usuarios.length; i++) {
				if (usuarios[i].getIdUsuario().equals(id)) {
					return usuarios[i];
				}
			}
			return null;
		}

		public DocumentoSimpVO getDocumentoSimpVOById(String id, DocumentoSimpVO[] documentoSimps) {
			for (int i = 0; i < documentoSimps.length; i++) {
				if (documentoSimps[i].getTipoDocumentoVO().getIdTipoDocumento().equals(id)) {
					return documentoSimps[i];
				}
			}
			return null;
		}

		public ListaDocumentoSimpVO getDocsUsuario() {
			if (this.docsUsuario == null || this.docsUsuario.getDocumentoSimpVOArray().length == 0) {
				this.docsUsuario = ListaDocumentoSimpVO.Factory.newInstance();
			}
			return this.docsUsuario;
		}

		public void setDocsUsuario(ListaDocumentoSimpVO docsUsuario) {
			this.docsUsuario = docsUsuario;
		}

		public void setListaSelects(UsuariosVO listaSelects) {
			this.listaSelects = listaSelects;
		}

		public UsuariosVO getListaSelects() {
			return this.listaSelects;
		}

		public void setTipoDocAtual(String tipoDocAtual) {
			this.tipoDocAtual = tipoDocAtual;
		}

		public String getTipoDocAtual() {
			return this.tipoDocAtual;
		}

		public void setDtInicio(String dtInicio) {
			this.dtInicio = dtInicio;
		}

		public String getDtInicio() {
			return this.dtInicio;
		}

		public void setDtRetorno(String dtRetorno) {
			this.dtRetorno = dtRetorno;
		}

		public String getDtRetorno() {
			return this.dtRetorno;
		}

		public void setUsuarioPesquisa(UsuarioSimplVO usuarioPesquisa) {
			this.usuarioPesquisa = usuarioPesquisa;
		}

		public UsuarioSimplVO getUsuarioPesquisa() {
			return this.usuarioPesquisa;
		}

		public void setDsDocAtual(String dsDocAtual) {
			this.dsDocAtual = dsDocAtual;
		}

		public String getDsDocAtual() {
			return this.dsDocAtual;
		}

		public void setIdCargoAtual(String idCargoAtual) {
			this.idCargoAtual = idCargoAtual;
		}

		public String getIdCargoAtual() {
			return this.idCargoAtual;
		}

		public void setIdMotivoAtual(String idMotivoAtual) {
			this.idMotivoAtual = idMotivoAtual;
		}

		public String getIdMotivoAtual() {
			return this.idMotivoAtual;
		}

		public void setIdRegionalAtual(String idRegionalAtual) {
			this.idRegionalAtual = idRegionalAtual;
		}

		public String getIdRegionalAtual() {
			return this.idRegionalAtual;
		}

		public void setIdStatusAtual(String idStatusAtual) {
			this.idStatusAtual = idStatusAtual;
		}

		public String getIdStatusAtual() {
			return this.idStatusAtual;
		}

		public void setIdUFOperadora(String idUFOperadora) {
			this.idUFOperadora = idUFOperadora;
		}

		public String getIdUFOperadora() {
			return this.idUFOperadora;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public String getLogin() {
			return this.login;
		}

		public void setLoginCti(String loginCti) {
			this.loginCti = loginCti;
		}

		public String getLoginCti() {
			return this.loginCti;
		}

		public void setLoginChefe(String loginChefe) {
			this.loginChefe = loginChefe;
		}

		public String getLoginChefe() {
			return this.loginChefe;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getNome() {
			return this.nome;
		}

		public void setSobrenome(String sobrenome) {
			this.sobrenome = sobrenome;
		}

		public String getSobrenome() {
			return this.sobrenome;
		}

		public void setDescricaoStatusAtual(String descricaoStatusAtual) {
			this.descricaoStatusAtual = descricaoStatusAtual;
		}

		public String getDescricaoStatusAtual() {
			return this.descricaoStatusAtual;
		}

		public void setDescricaoCargoAtual(String descricaoCargoAtual) {
			this.descricaoCargoAtual = descricaoCargoAtual;
		}

		public String getDescricaoCargoAtual() {
			return this.descricaoCargoAtual;
		}

		public void setIdUFAtual(String idUFAtual) {
			this.idUFAtual = idUFAtual;
		}

		public String getIdUFAtual() {
			return this.idUFAtual;
		}

		public void setIdDDDAtual(String idDDDAtual) {
			this.idDDDAtual = idDDDAtual;
		}

		public String getIdDDDAtual() {
			return this.idDDDAtual;
		}

		public void setIdPaisDocAtual(String idPaisDocAtual) {
			this.idPaisDocAtual = idPaisDocAtual;
		}

		public String getIdPaisDocAtual() {
			return this.idPaisDocAtual;
		}

		public void setIdUFDocAtual(String idUFDocAtual) {
			this.idUFDocAtual = idUFDocAtual;
		}

		public String getIdUFDocAtual() {
			return this.idUFDocAtual;
		}

		public void setTipoDoc0(String tipoDoc0) {
			this.tipoDoc0 = tipoDoc0;
		}

		public String getTipoDoc0() {
			return this.tipoDoc0;
		}

		public void setTipoDoc1(String tipoDoc1) {
			this.tipoDoc1 = tipoDoc1;
		}

		public String getTipoDoc1() {
			return this.tipoDoc1;
		}

		public void setTipoDoc2(String tipoDoc2) {
			this.tipoDoc2 = tipoDoc2;
		}

		public String getTipoDoc2() {
			return this.tipoDoc2;
		}

		public void setDsDoc0(String dsDoc0) {
			this.dsDoc0 = dsDoc0;
		}

		public String getDsDoc0() {
			return this.dsDoc0;
		}

		public void setDsDoc1(String dsDoc1) {
			this.dsDoc1 = dsDoc1;
		}

		public String getDsDoc1() {
			return this.dsDoc1;
		}

		public void setDsDoc2(String dsDoc2) {
			this.dsDoc2 = dsDoc2;
		}

		public String getDsDoc2() {
			return this.dsDoc2;
		}

		public void setUfDoc0(String ufDoc0) {
			this.ufDoc0 = ufDoc0;
		}

		public String getUfDoc0() {
			return this.ufDoc0;
		}

		public void setUfDoc1(String ufDoc1) {
			this.ufDoc1 = ufDoc1;
		}

		public String getUfDoc1() {
			return this.ufDoc1;
		}

		public void setUfDoc2(String ufDoc2) {
			this.ufDoc2 = ufDoc2;
		}

		public String getUfDoc2() {
			return this.ufDoc2;
		}

		public void setPaisDoc0(String paisDoc0) {
			this.paisDoc0 = paisDoc0;
		}

		public String getPaisDoc0() {
			return this.paisDoc0;
		}

		public void setPaisDoc1(String paisDoc1) {
			this.paisDoc1 = paisDoc1;
		}

		public String getPaisDoc1() {
			return this.paisDoc1;
		}

		public void setPaisDoc2(String paisDoc2) {
			this.paisDoc2 = paisDoc2;
		}

		public String getPaisDoc2() {
			return this.paisDoc2;
		}

		public void setIdufDoc0(String idufDoc0) {
			this.idufDoc0 = idufDoc0;
		}

		public String getIdufDoc0() {
			return this.idufDoc0;
		}

		public void setIdufDoc1(String idufDoc1) {
			this.idufDoc1 = idufDoc1;
		}

		public String getIdufDoc1() {
			return this.idufDoc1;
		}

		public void setIdufDoc2(String idufDoc2) {
			this.idufDoc2 = idufDoc2;
		}

		public String getIdufDoc2() {
			return this.idufDoc2;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getEmail() {
			return this.email;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		public String getTipo() {
			return this.tipo;
		}

		public void setIdDoc0(String idDoc0) {
			this.idDoc0 = idDoc0;
		}

		public String getIdDoc0() {
			return this.idDoc0;
		}

		public void setIdDoc1(String idDoc1) {
			this.idDoc1 = idDoc1;
		}

		public String getIdDoc1() {
			return this.idDoc1;
		}

		public void setIdDoc2(String idDoc2) {
			this.idDoc2 = idDoc2;
		}

		public String getIdDoc2() {
			return this.idDoc2;
		}

		public void setDtInclusao(String dtInclusao) {
			this.dtInclusao = dtInclusao;
		}

		public String getDtInclusao() {
			return this.dtInclusao;
		}

		public void setDtExclusao(String dtExclusao) {
			this.dtExclusao = dtExclusao;
		}

		public String getDtExclusao() {
			return this.dtExclusao;
		}

		public void setNomeChefe(String nomeChefe) {
			this.nomeChefe = nomeChefe;
		}

		public String getNomeChefe() {
			return this.nomeChefe;
		}

		public void setSgDoc0(String sgDoc0) {
			this.sgDoc0 = sgDoc0;
		}

		public String getSgDoc0() {
			return this.sgDoc0;
		}

		public void setSgDoc1(String sgDoc1) {
			this.sgDoc1 = sgDoc1;
		}

		public String getSgDoc1() {
			return this.sgDoc1;
		}

		public void setSgDoc2(String sgDoc2) {
			this.sgDoc2 = sgDoc2;
		}

		public String getSgDoc2() {
			return this.sgDoc2;
		}

		public void setIdpaisDoc0(String idpaisDoc0) {
			this.idpaisDoc0 = idpaisDoc0;
		}

		public String getIdpaisDoc0() {
			return this.idpaisDoc0;
		}

		public void setIdpaisDoc1(String idpaisDoc1) {
			this.idpaisDoc1 = idpaisDoc1;
		}

		public String getIdpaisDoc1() {
			return this.idpaisDoc1;
		}

		public void setIdpaisDoc2(String idpaisDoc2) {
			this.idpaisDoc2 = idpaisDoc2;
		}

		public String getIdpaisDoc2() {
			return this.idpaisDoc2;
		}

		public void setIdUsuario(String idUsuario) {
			this.idUsuario = idUsuario;
		}

		public String getIdUsuario() {
			return this.idUsuario;
		}

		public void setDsTelefone(String dsTelefone) {
			this.dsTelefone = dsTelefone;
		}

		public String getDsTelefone() {
			return this.dsTelefone;
		}

		public void setRegistrosPPagina(String registrosPPagina) {
			this.registrosPPagina = registrosPPagina;
		}

		public String getRegistrosPPagina() {
			return this.registrosPPagina;
		}

		public void setPaginaAtual(String paginaAtual) {
			this.paginaAtual = paginaAtual;
		}

		public String getPaginaAtual() {
			return this.paginaAtual;
		}

		public void setArrayUsuariosLength(String arrayUsuariosLength) {
			this.arrayUsuariosLength = arrayUsuariosLength;
		}

		public String getArrayUsuariosLength() {
			return this.arrayUsuariosLength;
		}

		public void setPagina(String pagina) {
			this.pagina = pagina;
		}

		public String getPagina() {
			return this.pagina;
		}

		public void setListaCargo(ListaCargoPorNivelVO listaCargo) {
			this.listaCargo = listaCargo;
		}

		public ListaCargoPorNivelVO getListaCargo() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null. This type doesn't have
			// a default constructor, so Workshop cannot initialize it for you.

			// Initialize listaCargo if it is null.
			// if(this.listaCargo == null)
			// {
			// this.listaCargo = new CargoVODocument(?);
			// }

			return this.listaCargo;
		}

		public void setListaNivelOrganograma(ListaNivelOrganogramaVO listaNivelOrganograma) {
			this.listaNivelOrganograma = listaNivelOrganograma;
		}

		public ListaNivelOrganogramaVO getListaNivelOrganograma() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null. This type doesn't have
			// a default constructor, so Workshop cannot initialize it for you.

			// Initialize listaNivelOrganograma if it is null or length == 0.
			// if(this.listaNivelOrganograma == null ||
			// this.listaNivelOrganograma.length == 0)
			// {
			// this.listaNivelOrganograma = new
			// ListaNivelOrganogramaVODocument[1];
			// this.listaNivelOrganograma[0] = new
			// ListaNivelOrganogramaVODocument(?);
			// }

			return this.listaNivelOrganograma;
		}

		public void setListaOrganizacao(ListaOrganizacaoVO listaOrganizacao) {
			this.listaOrganizacao = listaOrganizacao;
		}

		public ListaOrganizacaoVO getListaOrganizacao() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null. This type doesn't have
			// a default constructor, so Workshop cannot initialize it for you.

			// Initialize listaOrganizacao if it is null or length == 0.
			// if(this.listaOrganizacao == null || this.listaOrganizacao.length
			// == 0)
			// {
			// this.listaOrganizacao = new ListaOrganizacaoVODocument[1];
			// this.listaOrganizacao[0] = new ListaOrganizacaoVODocument(?);
			// }

			return this.listaOrganizacao;
		}

		public void setListaUnidadeOrganograma(ListaUnidadePorOrganizacaoVO listaUnidadeOrganograma) {
			this.listaUnidadeOrganograma = listaUnidadeOrganograma;
		}

		public ListaUnidadePorOrganizacaoVO getListaUnidadeOrganograma() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null. This type doesn't have
			// a default constructor, so Workshop cannot initialize it for you.

			// Initialize listaUnidadeOrganograma if it is null or length == 0.
			// if(this.listaUnidadeOrganograma == null ||
			// this.listaUnidadeOrganograma.length == 0)
			// {
			// this.listaUnidadeOrganograma = new
			// ListaUnidadeOrganogramaVODocument[1];
			// this.listaUnidadeOrganograma[0] = new
			// ListaUnidadeOrganogramaVODocument(?);
			// }

			return this.listaUnidadeOrganograma;
		}

		public void setCargoAtual(String cargoAtual) {
			this.cargoAtual = cargoAtual;
		}

		public String getCargoAtual() {
			return this.cargoAtual;
		}

		public void setOrganizacaoAtual(String organizacaoAtual) {
			this.organizacaoAtual = organizacaoAtual;
		}

		public String getOrganizacaoAtual() {
			return this.organizacaoAtual;
		}

		public void setUnidadeOrganizacaoAtual(String unidadeOrganizacaoAtual) {
			this.unidadeOrganizacaoAtual = unidadeOrganizacaoAtual;
		}

		public String getUnidadeOrganizacaoAtual() {
			return this.unidadeOrganizacaoAtual;
		}

		public void setNivelOrganogramaAtual(String nivelOrganogramaAtual) {
			this.nivelOrganogramaAtual = nivelOrganogramaAtual;
		}

		public String getNivelOrganogramaAtual() {
			return this.nivelOrganogramaAtual;
		}

		public void setDadosAtuais(br.com.vivo.fo.usuario.vo.NivelCargoOrgVODocument.NivelCargoOrgVO.DadosAtuais dadosAtuais) {
			this.dadosAtuais = dadosAtuais;
		}

		public br.com.vivo.fo.usuario.vo.NivelCargoOrgVODocument.NivelCargoOrgVO.DadosAtuais getDadosAtuais() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null. This type doesn't have
			// a default constructor, so Workshop cannot initialize it for you.

			// Initialize dadosAtuais if it is null.
			// if(this.dadosAtuais == null)
			// {
			// this.dadosAtuais = new
			// br.com.vivo.fo.usuario.vo.NivelCargoOrgVODocument.NivelCargoOrgVO.DadosAtuais(?);
			// }

			return this.dadosAtuais;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}

		public void setDsCargo(String dsCargo) {
			this.dsCargo = dsCargo;
		}

		public String getDsCargo() {
			return this.dsCargo;
		}

		public void setDsNivel(String dsNivel) {
			this.dsNivel = dsNivel;
		}

		public String getDsNivel() {
			return this.dsNivel;
		}

		public void setDsUnidade(String dsUnidade) {
			this.dsUnidade = dsUnidade;
		}

		public String getDsUnidade() {
			return this.dsUnidade;
		}

		public void setDsOrganizacao(String dsOrganizacao) {
			this.dsOrganizacao = dsOrganizacao;
		}

		public String getDsOrganizacao() {
			return this.dsOrganizacao;
		}

		public void setFlgDoc01(String flgDoc01) {
			this.flgDoc01 = flgDoc01;
		}

		public String getFlgDoc01() {
			return this.flgDoc01;
		}

		public void setFlgDoc02(String flgDoc02) {
			this.flgDoc02 = flgDoc02;
		}

		public String getFlgDoc02() {
			return this.flgDoc02;
		}

		public void setFlgDoc03(String flgDoc03) {
			this.flgDoc03 = flgDoc03;
		}

		public String getFlgDoc03() {
			return this.flgDoc03;
		}

		public void setTipoDocAtualEdita(String tipoDocAtualEdita) {
			this.tipoDocAtualEdita = tipoDocAtualEdita;
		}

		public String getTipoDocAtualEdita() {
			return this.tipoDocAtualEdita;
		}

		public void setInConsultor(String inConsultor) {
			this.inConsultor = inConsultor;
		}

		public String getInConsultor() {
			return this.inConsultor;
		}

		public void setNmUF(String nmUF) {
			this.nmUF = nmUF;
		}

		public String getNmUF() {
			return this.nmUF;
		}

		public void setLoginFOWAntigo(String loginFOWAntigo) {
			this.loginFOWAntigo = loginFOWAntigo;
		}

		public String getLoginFOWAntigo() {
			return this.loginFOWAntigo;
		}

		public void setNomeMeio(String nomeMeio) {
			this.nomeMeio = nomeMeio;
		}

		public String getNomeMeio() {
			return this.nomeMeio;
		}

		public void setPessoa(String pessoa) {
			this.pessoa = pessoa;
		}

		public String getPessoa() {
			return this.pessoa;
		}

		public void setIdSite(String arg) {
			this.idSite = arg;
		}

		public String getIdSite() {
			if (this.idSite == null) {
				this.idSite = ConstantesCRM.SVAZIO;
			}
			return this.idSite;
		}

		public void setIdPerfilAtendimento(String arg) {
			this.idPerfilAtendimento = arg;
		}

		public String getIdPerfilAtendimento() {
			if (this.idPerfilAtendimento == null) {
				this.idPerfilAtendimento = ConstantesCRM.SVAZIO;
			}
			return this.idPerfilAtendimento;
		}

		public void setIdFornecedor(String arg) {
			this.idFornecedor = arg;
		}

		public String getIdFornecedor() {
			if (this.idFornecedor == null) {
				this.idFornecedor = ConstantesCRM.SVAZIO;
			}
			return this.idFornecedor;
		}

		public void setDsLoginRoteamento(String arg) {
			this.dsLoginRoteamento = arg;
		}

		public String getDsLoginRoteamento() {
			if (this.dsLoginRoteamento == null) {
				this.dsLoginRoteamento = ConstantesCRM.SVAZIO;
			}
			return this.dsLoginRoteamento;
		}
		
		public void setDataExpiracao(String arg) {
			this.dataExpiracao = arg;
		}
		
		public String getDataExpiracao() {
			if(this.dataExpiracao == null) {
				this.dataExpiracao = ConstantesCRM.SVAZIO;
			}
			
			return this.dataExpiracao;
		}
	}

	public ListaUsuariosForm getListaUsuariosPesquisaForm() {
		return (listaUsuariosPesquisaForm);
	}

	public ListaUsuariosForm getListaUsuariosForm() {
		return (this.listaUsuariosForm);
	}

	private CargoSimplVO getCargoById(String id, CargoSimplVO[] listaCargo) {
		for (int i = 0; i < listaCargo.length; i++) {
			if (listaCargo[i].getIdCargo().equals(id)) {
				return listaCargo[i];
			}
		}
		return null;
	}

	private NivelOrganogramaSimplVO getNivelById(String id, NivelOrganogramaSimplVO[] listaNivel) {
		for (int i = 0; i < listaNivel.length; i++) {
			if (listaNivel[i].getIdNivel().equals(id)) {
				return listaNivel[i];
			}
		}
		return null;
	}

	private OrganizacaoSimplVO getOrganizacaoById(String id, OrganizacaoSimplVO[] listaOrganizacao) {
		for (int i = 0; i < listaOrganizacao.length; i++) {
			if (listaOrganizacao[i].getIdOrganizacao().equals(id)) {
				return listaOrganizacao[i];
			}
		}
		return null;
	}

	private UnidadeOrganogramaVO getUnidadeById(String id, UnidadeOrganogramaVO[] listaUnidade) {
		for (int i = 0; i < listaUnidade.length; i++) {
			if (listaUnidade[i].getIdUnidade().equals(id)) {
				return listaUnidade[i];
			}
		}
		return null;
	}

	private StatusUsuarioVO getStatusById(String id, StatusUsuarioVO[] listaStatus) {
		for (int i = 0; i < listaStatus.length; i++) {
			if (listaStatus[i].getIdStatus().equals(id)) {
				return listaStatus[i];
			}
		}
		return null;
	}
}