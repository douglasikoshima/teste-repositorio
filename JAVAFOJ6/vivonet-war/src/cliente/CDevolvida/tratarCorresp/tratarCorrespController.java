package cliente.CDevolvida.tratarCorresp;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.CorrespDevolvidaVODocument.CorrespDevolvidaVO;
import br.com.vivo.fo.cliente.vo.CorrespDevolvidaVODocument.CorrespDevolvidaVO.FiltroCorrespDevolvida;
import br.com.vivo.fo.cliente.vo.CorrespDevolvidaVODocument.CorrespDevolvidaVO.StatusCorrespondencia;
import br.com.vivo.fo.cliente.vo.EnderecoBaseVODocument.EnderecoBaseVO;
import br.com.vivo.fo.cliente.vo.ManterCorrespDevolvidaVODocument.ManterCorrespDevolvidaVO;
import br.com.vivo.fo.cliente.vo.ManterCorrespDevolvidaVODocument.ManterCorrespDevolvidaVO.BuscaCorrespPor;
import br.com.vivo.fo.cliente.vo.ManterCorrespDevolvidaVODocument.ManterCorrespDevolvidaVO.ListaCorrespCliente;
import br.com.vivo.fo.cliente.vo.MotivoDevolucaoVODocument.MotivoDevolucaoVO;
import br.com.vivo.fo.cliente.vo.PaisVODocument.PaisVO;
import br.com.vivo.fo.cliente.vo.PesquisaEnderecoVODocument.PesquisaEnderecoVO;
import br.com.vivo.fo.cliente.vo.TipoCorrespondenciaVODocument.TipoCorrespondenciaVO;
import br.com.vivo.fo.cliente.vo.TratarCorrespDevolvidaVODocument.TratarCorrespDevolvidaVO;
import br.com.vivo.fo.cliente.vo.TratarCorrespDevolvidaVODocument.TratarCorrespDevolvidaVO.ListaStatus;
import br.com.vivo.fo.cliente.vo.TratarCorrespDevolvidaVODocument.TratarCorrespDevolvidaVO.ListaStatusCorresp;
import br.com.vivo.fo.cliente.vo.UFVODocument.UFVO;
import br.com.vivo.fo.cliente.vo.impl.CorrespDevolvidaVODocumentImpl.CorrespDevolvidaVOImpl.StatusCorrespondenciaImpl;
import br.com.vivo.fo.cliente.vo.impl.ManterCorrespDevolvidaVODocumentImpl.ManterCorrespDevolvidaVOImpl.ListaCorrespClienteImpl;
import br.com.vivo.fo.cliente.vo.impl.MotivoDevolucaoVODocumentImpl.MotivoDevolucaoVOImpl;
import br.com.vivo.fo.cliente.vo.impl.PaisVODocumentImpl.PaisVOImpl;
import br.com.vivo.fo.cliente.vo.impl.TipoCorrespondenciaVODocumentImpl.TipoCorrespondenciaVOImpl;
import br.com.vivo.fo.cliente.vo.impl.TratarCorrespDevolvidaVODocumentImpl.TratarCorrespDevolvidaVOImpl.ListaStatusCorrespImpl;
import br.com.vivo.fo.cliente.vo.impl.TratarCorrespDevolvidaVODocumentImpl.TratarCorrespDevolvidaVOImpl.ListaStatusImpl;
import br.com.vivo.fo.cliente.vo.impl.UFVODocumentImpl.UFVOImpl;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class tratarCorrespController extends AbstractAction {

	private static final long serialVersionUID = -1911145809334540267L;

	protected global.Global globalApp = new global.Global();

	@EJB
	private br.com.vivo.fo.ctrls.cliente.correspondenciaDevolvida.CorrespondenciaDevolvida correspDevolvidaFacadeControl;

	@EJB
	private br.com.vivo.fo.ctrls.cliente.telaInicial.TelaInicialFacade telaIncialFacadeControl;

	private static Logger log = new Logger("clientes");

	private TratarCorrespDevolvidaVO tratarCorrespDevolvida;
	private TratarForm tratarForm;
	private String user = null;

	public TratarForm getTratarForm() {
		return this.tratarForm;
	}

	public void setTratar(TratarForm tratarForm) {
		this.tratarForm = tratarForm;
	}

	private CorrespDevolvidaVO correspDevolvida;
	private FiltroForm filtroForm;

	private ManterCorrespDevolvidaVO incluiCorresp;

	private IncluirForm incluirForm;

	public IncluirForm getIncluirForm() {
		return this.incluirForm;
	}

	public void setIncluirForm(IncluirForm incluirForm) {
		this.incluirForm = incluirForm;
	}

	public FiltroForm getFiltroForm() {
		return this.filtroForm;
	}

	public void setFiltroForm(FiltroForm filtroForm) {
		this.filtroForm = filtroForm;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("done".equals(mapping.getParameter())) {
			return done(mapping, form, request, response);
		} else if ("voltar".equals(mapping.getParameter())) {
			return voltar(mapping, form, request, response);
		} else if ("pesquisar".equals(mapping.getParameter())) {
			return pesquisar(mapping, form, request, response);
		} else if ("salvar".equals(mapping.getParameter())) {
			return salvar(mapping, form, request, response);
		} else if ("DetalhesTratar".equals(mapping.getParameter())) {
			return DetalhesTratar(mapping, form, request, response);
		} else if ("manterDevolvida".equals(mapping.getParameter())) {
			return manterDevolvida(mapping, form, request, response);
		} else if ("incluirCorrespDevolvida".equals(mapping.getParameter())) {
			return incluirCorrespDevolvida(mapping, form, request, response);
		} else if ("pesquisaIncluir".equals(mapping.getParameter())) {
			return pesquisaIncluir(mapping, form, request, response);
		} else if ("salvaDadosTratar".equals(mapping.getParameter())) {
			return salvaDadosTratar(mapping, form, request, response);
		} else if ("pesquisaEndereco".equals(mapping.getParameter())) {
			return pesquisaEndereco(mapping, form, request, response);
		} else if ("buscaEnderecoCD".equals(mapping.getParameter())) {
			return buscaEnderecoCD(mapping, form, request, response);
		} else if ("refresh".equals(mapping.getParameter())) {
			return refresh(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="manterDevolvida.do"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="done" return-action="CDevolvidaDone"
	 */
	public ActionForward done(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("done");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="done.do"
	 */
	public ActionForward voltar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="resultadoPesquisa.jsp"
	 * @jpf:forward name="alterar" path="DetalhesTratar.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward pesquisar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			FiltroForm form = (FiltroForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("tratarCorrespController:pesquisar(" + user + ") >> INICIALIZADO");

			String destino;

			if ((request.getParameter("destino") != null && request.getParameter("destino").equalsIgnoreCase("pesquisa")) || request.getAttribute("pesquisa") != null) {
				CorrespDevolvidaVO.FiltroCorrespDevolvida pesquisaFiltro = CorrespDevolvidaVO.FiltroCorrespDevolvida.Factory.newInstance();
				if (request.getAttribute("pesquisa") != null && request.getAttribute("pesquisa").toString().equalsIgnoreCase(ConstantesCRM.STRUE)) {
					pesquisaFiltro = correspDevolvida.getFiltroCorrespDevolvida();
				} else {
					pesquisaFiltro = form.filtroCorrespondencia;
				}

				correspDevolvida.setFiltroCorrespDevolvida(pesquisaFiltro);

				correspDevolvida = correspDevolvidaFacadeControl.getCorrespDevolvida(user, pesquisaFiltro);
				correspDevolvida.setFiltroCorrespDevolvida(pesquisaFiltro);
				correspDevolvida.setMotivoDevolucaoVOArray(filtroForm.getCorrespDevolvidaVO().getMotivoDevolucaoVOArray());
				correspDevolvida.setStatusCorrespondenciaArray(filtroForm.getCorrespDevolvidaVO().getStatusCorrespondenciaArray());
				correspDevolvida.setTipoCorrespondenciaVOArray(filtroForm.getCorrespDevolvidaVO().getTipoCorrespondenciaVOArray());
				filtroForm.setCorrespDevolvida(correspDevolvida);

				destino = ConstantesCRM.SUCCESS;

			} else {
				int indice = Integer.parseInt(request.getParameter("idArray"));
				String idCorresp = correspDevolvida.getListaCorrespDevolvidaArray(indice).getIdCorrespondencia();
				filtroForm.setId(idCorresp);

				destino = "alterar";
			}

			log.debug("tratarCorrespController:pesquisar(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);

		} catch (Exception e) {
			log.error("tratarCorrespController:pesquisar(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/CDevolvida/tratarCorresp/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);

			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="incluso" path="incluiCorrespDevolvida.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			IncluirForm form = (IncluirForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("tratarCorrespController:salvar(" + user + ") >> INICIALIZADO");

			String destino = ConstantesCRM.SUCCESS;

			log.debug("tratarCorrespController:salvar()");

			incluiCorresp = ManterCorrespDevolvidaVO.Factory.newInstance();

			incluiCorresp.addNewListaCorrespCliente();
			incluiCorresp.addNewTipoCorrespondenciaVO();
			incluiCorresp.addNewMotivoDevolucaoVO();

			form.getCorrespCliente().getEnderecoBaseVO().setDsBairro(form.getCorrespCliente().getEnderecoBaseVO().getDsBairro().trim());
			form.getCorrespCliente().getEnderecoBaseVO().setDsCidade(form.getCorrespCliente().getEnderecoBaseVO().getDsCidade().trim());
			form.getCorrespCliente().getEnderecoBaseVO().setDsComplemento(form.getCorrespCliente().getEnderecoBaseVO().getDsComplemento().trim());
			form.getCorrespCliente().getEnderecoBaseVO().setDsEndereco(form.getCorrespCliente().getEnderecoBaseVO().getDsEndereco().trim());
			form.getCorrespCliente().getEnderecoBaseVO().setNrEndereco(form.getCorrespCliente().getEnderecoBaseVO().getNrEndereco().trim());
			incluiCorresp.setListaCorrespClienteArray(0, form.getCorrespCliente());
			incluiCorresp.getListaCorrespClienteArray(0).getEnderecoBaseVO().addNewUFVO();
			incluiCorresp.getListaCorrespClienteArray(0).getEnderecoBaseVO().addNewPaisVO();
			incluiCorresp.getListaCorrespClienteArray(0).getEnderecoBaseVO().getUFVO().setIdUF(form.getIdUFSelecionado());
			incluiCorresp.getListaCorrespClienteArray(0).getEnderecoBaseVO().getPaisVO().setIdPais(form.getIdPaisSelecionado());
			incluiCorresp.getListaCorrespClienteArray(0).setIdPessoaUsuario(user);
			incluiCorresp.getListaCorrespClienteArray(0).setIdMotivoDevolucao(String.valueOf(form.getIdMotivoSelecionado()));
			incluiCorresp.getListaCorrespClienteArray(0).setIdTipoCorrespondencia(String.valueOf(form.getIdTipoSelecionado()));

			correspDevolvidaFacadeControl.setSalvarManterCorrespDevolvida(user, incluiCorresp);
			destino = "incluso";

			incluirForm.getIncluirCorresp().setListaCorrespClienteArray(new ListaCorrespCliente[0]);

			incluirForm.setIdMotivoSelecionado(0);
			incluirForm.setIdPaisSelecionado(ConstantesCRM.SZERO);
			incluirForm.setIdTipoSelecionado(0);
			incluirForm.setIdUFSelecionado(ConstantesCRM.SZERO);

			incluirForm.getBuscaPor().setNrLinha(ConstantesCRM.SVAZIO);
			incluirForm.getBuscaPor().setNrConta(ConstantesCRM.SVAZIO);
			incluirForm.getBuscaPor().setIdPessoa(ConstantesCRM.SVAZIO);

			log.debug("tratarCorrespController:salvar(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);

		} catch (Exception e) {
			log.error("tratarCorrespController:salvar(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/CDevolvida/tratarCorresp/incluirCorrespDevolvida.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="tratarCorrespDevolvida.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward DetalhesTratar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("tratarCorrespController:salvar(" + user + ") >> INICIALIZADO");

			tratarForm = new TratarForm();
			tratarForm.tratarCorrespDevolvida.setIdCorrespondenciaDevolvida(filtroForm.getId());
			tratarCorrespDevolvida = correspDevolvidaFacadeControl.getTratarCorrepDevolvida(user, tratarForm.tratarCorrespDevolvida);
			tratarForm.setTratarCorrespDevolvida(tratarCorrespDevolvida);
			tratarForm.setStatusAtual(tratarCorrespDevolvida.getIdStatusAtual());
			tratarForm.setIdStatus(tratarForm.getTratarCorrespDevolvida().getIdStatusAtual());
			tratarForm.setIdUFSelecionado(tratarCorrespDevolvida.getEnderecoBaseVO().getUFVO().getIdUF());
			tratarForm.setIdPaisSelecionado(tratarCorrespDevolvida.getEnderecoBaseVO().getPaisVO().getIdPais());

			log.debug("tratarCorrespController:salvar(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("tratarCorrespController:DetalhesTratar(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/CDevolvida/tratarCorresp/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterCorrespDevolvida.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward manterDevolvida(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("tratarCorrespController:manterDevolvida(" + user + ") >> INICIALIZADO");

			ActionForward forward = mapping.findForward(ConstantesCRM.SUCCESS);

			filtroForm = new FiltroForm();
			CorrespDevolvidaVO.FiltroCorrespDevolvida iniciarFiltro = CorrespDevolvidaVO.FiltroCorrespDevolvida.Factory.newInstance();

			correspDevolvida = correspDevolvidaFacadeControl.getCorrespDevolvida(user, iniciarFiltro);
			filtroForm.setCorrespDevolvida(correspDevolvida);

			// Insere elementos com ID 0, para não passar ao tuxedo.
			MotivoDevolucaoVO motDev = filtroForm.getCorrespDevolvidaVO().insertNewMotivoDevolucaoVO(0);
			motDev.setDescricao(ConstantesCRM.STODOS);
			motDev.setId(-1);
			TipoCorrespondenciaVO tpCorr = filtroForm.getCorrespDevolvidaVO().insertNewTipoCorrespondenciaVO(0);
			tpCorr.setDescricao(ConstantesCRM.STODOS);
			tpCorr.setId(-1);
			StatusCorrespondencia stCorr = filtroForm.getCorrespDevolvidaVO().insertNewStatusCorrespondencia(0);
			stCorr.setDsStatus(ConstantesCRM.STODOS);
			stCorr.setIdStatus("-1");

			log.debug("tratarCorrespController:manterDevolvida(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return forward;

		} catch (Exception e) {
			log.error("tratarCorrespController:manterDevolvida(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluiCorrespDevolvida.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward incluirCorrespDevolvida(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("tratarCorrespController:incluirCorrespDevolvida(" + user + ") >> INICIALIZADO");

			ActionForward forward = mapping.findForward(ConstantesCRM.SUCCESS);

			incluirForm = new IncluirForm();
			ManterCorrespDevolvidaVO iniciarInclui = ManterCorrespDevolvidaVO.Factory.newInstance();

			incluiCorresp = correspDevolvidaFacadeControl.getManterCorrespDevolvidaBusca(user, null, iniciarInclui);
			incluirForm.setIncluirCorresp(incluiCorresp);

			log.debug("tratarCorrespController:incluirCorrespDevolvida(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return forward;

		} catch (Exception e) {
			log.error("tratarCorrespController:incluirCorrespDevolvida(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluiCorrespDevolvida.jsp"
	 * @jpf:forward name="salvar" path="salvar.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward pesquisaIncluir(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			String destino;
			IncluirForm form = (IncluirForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("tratarCorrespController:pesquisaIncluir(" + user + ") >> INICIALIZADO");

			if (request.getParameter("destino").equalsIgnoreCase("pesquisa")) {
				log.debug("tratarCorrespController:pesquisaIncluir(" + user + ") >> Pesquisa");
				String buscaPor = "True";
				String tipoBusca = ConstantesCRM.SVAZIO;
				incluirForm.setTipoBusca(tipoBusca);

				ManterCorrespDevolvidaVO pesquisaInclui = ManterCorrespDevolvidaVO.Factory.newInstance();
				pesquisaInclui.addNewBuscaCorrespPor();
				pesquisaInclui.setBuscaCorrespPor(form.buscaPor);
				incluiCorresp = correspDevolvidaFacadeControl.getManterCorrespDevolvidaBusca(user, buscaPor, pesquisaInclui);

				if (incluiCorresp.getListaCorrespClienteArray().length == 0) {
					if (form.getBuscaPor().getIdPessoa() != null) {
						tipoBusca = "pessoa";
					}
					if (form.getBuscaPor().getNrConta() != null) {
						tipoBusca = "conta";
					}
					if (form.getBuscaPor().getNrLinha() != null) {
						tipoBusca = "linha";
					}
					incluirForm.setTipoBusca(tipoBusca);
					log.debug("tratarCorrespController:pesquisaIncluir(" + user + ") >> tipoBusca: " + tipoBusca);
				}

				incluiCorresp.setMotivoDevolucaoVOArray(incluirForm.getIncluirCorresp().getMotivoDevolucaoVOArray());
				incluiCorresp.setPaisVOArray(incluirForm.getIncluirCorresp().getPaisVOArray());
				incluiCorresp.setUFVOArray(incluirForm.getIncluirCorresp().getUFVOArray());
				incluiCorresp.setTipoCorrespondenciaVOArray(incluirForm.getIncluirCorresp().getTipoCorrespondenciaVOArray());
				incluirForm.setIncluirCorresp(incluiCorresp);
				incluirForm.setBuscaPor(form.getBuscaPor());

				destino = ConstantesCRM.SUCCESS;

			} else if (request.getParameter("destino").equalsIgnoreCase("selecionado")) {
				log.debug("tratarCorrespController:pesquisaIncluir(" + user + ") >> Item selecionado");
				int indice = Integer.parseInt(request.getParameter("indice"));

				ManterCorrespDevolvidaVO.ListaCorrespCliente[] lista;
				lista = incluirForm.incluiCorresp.getListaCorrespClienteArray();
				incluirForm.getCorrespCliente().getEnderecoBaseVO().setDsCidade(lista[indice].getEnderecoBaseVO().getDsCidade());
				incluirForm.getCorrespCliente().getEnderecoBaseVO().setDsBairro(lista[indice].getEnderecoBaseVO().getDsBairro());
				incluirForm.getCorrespCliente().getEnderecoBaseVO().setDsComplemento(lista[indice].getEnderecoBaseVO().getDsComplemento());
				incluirForm.getCorrespCliente().getEnderecoBaseVO().setDsEndereco(lista[indice].getEnderecoBaseVO().getDsEndereco());
				incluirForm.getCorrespCliente().getEnderecoBaseVO().setNrCEP(lista[indice].getEnderecoBaseVO().getNrCEP());
				incluirForm.getCorrespCliente().getEnderecoBaseVO().setNrEndereco(lista[indice].getEnderecoBaseVO().getNrEndereco());
				incluirForm.getCorrespCliente().setNmCliente(lista[indice].getNmCliente());
				incluirForm.getCorrespCliente().setNrDocumento(lista[indice].getNrDocumento());
				incluirForm.getCorrespCliente().setDsTipoDocumento(lista[indice].getDsTipoDocumento());
				incluirForm.getCorrespCliente().setIdPessoa(lista[indice].getIdPessoa());
				incluirForm.getCorrespCliente().setIdLinha(lista[indice].getIdLinha());
				incluirForm.getCorrespCliente().setIdConta(lista[indice].getIdConta());
				incluirForm.setIdPaisSelecionado(lista[indice].getEnderecoBaseVO().getPaisVO().getIdPais());
				incluirForm.setIdUFSelecionado(lista[indice].getEnderecoBaseVO().getUFVO().getIdUF());

				destino = ConstantesCRM.SUCCESS;

			} else {
				destino = "salvar";
			}

			log.debug("tratarCorrespController:pesquisaIncluir(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);

		} catch (Exception e) {
			log.error("tratarCorrespController:pesquisaIncluir(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/CDevolvida/tratarCorresp/incluirCorrespDevolvida.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="error" path="/error.jsp"
	 * @jpf:forward name="success" path="refresh.do"
	 */
	public ActionForward salvaDadosTratar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			TratarForm form = (TratarForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("tratarCorrespController:salvaDadosTratar(" + user + ") >> INICIALIZADO");

			String status = null;

			TratarCorrespDevolvidaVO alteraStatus = TratarCorrespDevolvidaVO.Factory.newInstance();
			alteraStatus.setIdCorrespondenciaDevolvida(form.tratarCorrespDevolvida.getIdCorrespondenciaDevolvida());

			if (form.getIdStatus().equalsIgnoreCase(tratarForm.getIdStatus().toString())) {
				status = "naoOK";
			} else {
				status = ConstantesCRM.SOK;

				alteraStatus.addNewListaStatus();
				alteraStatus.addNewListaStatusCorresp();
				alteraStatus.getListaStatusArray(0).setIdStatus(form.getIdStatus());
			}

			// Endereço
			form.tratarCorrespDevolvida.getEnderecoBaseVO().setDsBairro(form.tratarCorrespDevolvida.getEnderecoBaseVO().getDsBairro().trim());
			form.tratarCorrespDevolvida.getEnderecoBaseVO().setDsCidade(form.tratarCorrespDevolvida.getEnderecoBaseVO().getDsCidade().trim());
			form.tratarCorrespDevolvida.getEnderecoBaseVO().setDsComplemento(form.tratarCorrespDevolvida.getEnderecoBaseVO().getDsComplemento().trim());
			form.tratarCorrespDevolvida.getEnderecoBaseVO().setDsEndereco(form.tratarCorrespDevolvida.getEnderecoBaseVO().getDsEndereco().trim());
			form.tratarCorrespDevolvida.getEnderecoBaseVO().setNrCEP(form.tratarCorrespDevolvida.getEnderecoBaseVO().getNrCEP().trim());
			form.tratarCorrespDevolvida.getEnderecoBaseVO().setNrEndereco(form.tratarCorrespDevolvida.getEnderecoBaseVO().getNrEndereco().trim());
			alteraStatus.addNewEnderecoBaseVO();
			alteraStatus.setEnderecoBaseVO(form.tratarCorrespDevolvida.getEnderecoBaseVO());
			alteraStatus.getEnderecoBaseVO().addNewUFVO();
			alteraStatus.getEnderecoBaseVO().getUFVO().setIdUF(form.getIdUFSelecionado());
			alteraStatus.getEnderecoBaseVO().addNewPaisVO();
			alteraStatus.getEnderecoBaseVO().getPaisVO().setIdPais(form.getIdPaisSelecionado());

			// Chama registro no banco
			correspDevolvidaFacadeControl.setSalvarTratarCorresp(status, user, alteraStatus, null);

			request.setAttribute("pesquisa", ConstantesCRM.STRUE);
			log.debug("tratarCorrespController:salvaDadosTratar(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("tratarCorrespController:salvaDadosTratar(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/CDevolvida/tratarCorresp/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);

			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisaEndereco.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward pesquisaEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("tratarCorrespController:pesquisaEndereco(" + user + ") >> INICIALIZADO");

			incluirForm.setPesquisaEndereco(telaIncialFacadeControl.getPesquisaEnderecoIni(user));
			incluirForm.getPesquisaEndereco().addNewFiltroPesquisa();
			incluirForm.getPesquisaEndereco().addNewListaEnderecos();

			log.debug("tratarCorrespController:pesquisaEndereco(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("tratarCorrespController:pesquisaEndereco(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/CDevolvida/tratarCorresp/incluirCorrespDevolvida.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="selecionado" path="incluiCorrespDevolvida.jsp"
	 * @jpf:forward name="success" path="listaEnderecos.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward buscaEnderecoCD(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("tratarCorrespController:buscaEnderecoCD(" + user + ") >> INICIALIZADO");

			if (request.getParameter("selecionado") != null) {
				int idEndArray = 0;
				idEndArray = Integer.parseInt(request.getParameter("idArrayEndereco"));
				incluirForm.getCorrespCliente().getEnderecoBaseVO().setDsCidade(incluirForm.getPesquisaEndereco().getListaEnderecos().getEnderecoVOArray(idEndArray).getNmMunicipio());
				incluirForm.getCorrespCliente().getEnderecoBaseVO().setDsBairro(incluirForm.getPesquisaEndereco().getListaEnderecos().getEnderecoVOArray(idEndArray).getNmBairro());
				incluirForm.getCorrespCliente().getEnderecoBaseVO().setNrCEP(incluirForm.getPesquisaEndereco().getListaEnderecos().getEnderecoVOArray(idEndArray).getNrCEP());
				incluirForm.setIdPaisSelecionado(incluirForm.getPesquisaEndereco().getListaEnderecos().getEnderecoVOArray(idEndArray).getPaisVO().getIdPais());
				incluirForm.setIdUFSelecionado(incluirForm.getPesquisaEndereco().getListaEnderecos().getEnderecoVOArray(idEndArray).getUFVO().getIdUF());
				incluirForm.setIdPaisSelecionado(incluirForm.getPesquisaEndereco().getListaEnderecos().getEnderecoVOArray(idEndArray).getPaisVO().getIdPais());

				if (incluirForm.getPesquisaEndereco().getListaEnderecos().getEnderecoVOArray(idEndArray).getNmTipoLogradouro().equalsIgnoreCase(incluirForm.getPesquisaEndereco().getListaEnderecos().getEnderecoVOArray(idEndArray).getNmTituloLogradouro())) {
					incluirForm.getCorrespCliente().getEnderecoBaseVO().setDsEndereco(incluirForm.getPesquisaEndereco().getListaEnderecos().getEnderecoVOArray(idEndArray).getNmTituloLogradouro() + " " + incluirForm.getPesquisaEndereco().getListaEnderecos().getEnderecoVOArray(idEndArray).getNmLogradouro());
				} else {
					incluirForm.getCorrespCliente().getEnderecoBaseVO().setDsEndereco(incluirForm.getPesquisaEndereco().getListaEnderecos().getEnderecoVOArray(idEndArray).getNmTipoLogradouro() + " " + incluirForm.getPesquisaEndereco().getListaEnderecos().getEnderecoVOArray(idEndArray).getNmTituloLogradouro() + " " + incluirForm.getPesquisaEndereco().getListaEnderecos().getEnderecoVOArray(idEndArray).getNmLogradouro());
				}

				incluirForm.getCorrespCliente().getEnderecoBaseVO().setDsComplemento(ConstantesCRM.SVAZIO);
				incluirForm.getCorrespCliente().getEnderecoBaseVO().setNrEndereco(ConstantesCRM.SVAZIO);
				log.debug("tratarCorrespController:buscaEnderecoCD(" + user + ") >> FINALIZADO (incluiCorrespDevolvida)");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("selecionado");

			} else {
				PesquisaEnderecoVO filtroEndereco = PesquisaEnderecoVO.Factory.newInstance();
				filtroEndereco.addNewFiltroPesquisa();
				incluirForm.getPesquisaEndereco().addNewListaEnderecos();

				if (request.getParameter("iniciarTela") == null) {
					filtroEndereco.getFiltroPesquisa().setDsLogradouro(request.getParameter("filtroPesquisa.dsLogradouro").trim());
					filtroEndereco.getFiltroPesquisa().setDsBairro(request.getParameter("filtroPesquisa.dsBairro").trim());
					filtroEndereco.getFiltroPesquisa().setDsLocalidade(request.getParameter("filtroPesquisa.dsLocalidade").trim());
					filtroEndereco.getFiltroPesquisa().setNrCEP(request.getParameter("filtroPesquisa.nrCEP").trim());
					filtroEndereco.getFiltroPesquisa().setIdUFSelecionado(request.getParameter("filtroPesquisa.idUFSelecionado").trim());
					incluirForm.setPesquisaEndereco(telaIncialFacadeControl.getPesquisaEnderecoFil(user, filtroEndereco));
				}
				log.debug("tratarCorrespController:buscaEnderecoCD(" + user + ") >> FINALIZADO");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}
		} catch (Exception e) {
			log.error("tratarCorrespController:buscaEnderecoCD(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/CDevolvida/tratarCorresp/incluirCorrespDevolvida.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);

			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterCorrespDevolvida.jsp"
	 */
	public ActionForward refresh(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		filtroForm.setInRefresh(ConstantesCRM.STRUE);
		filtroForm.setFiltroCorrespondencia(filtroForm.getCorrespDevolvidaVO().getFiltroCorrespDevolvida());
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class FiltroForm extends ActionForm {

		private static final long serialVersionUID = -9160854537068267185L;

		private String inRefresh = ConstantesCRM.SFALSE;
		private String id = new String();
		private String idStatus = new String();
		private int idMotivoDevolucao;
		private int idTipoCorrespondencia;
		private StatusCorrespondencia[] statusCorrespondencia = new StatusCorrespondenciaImpl[0];;
		private TipoCorrespondenciaVO[] tipoCorrespondenciaVO = new TipoCorrespondenciaVOImpl[0];
		private MotivoDevolucaoVO[] motivoDevolucaoVO = new MotivoDevolucaoVOImpl[0];
		private FiltroCorrespDevolvida filtroCorrespondencia;
		private CorrespDevolvidaVO correspDevolvidaVO;

		public FiltroForm() {
			correspDevolvidaVO = CorrespDevolvidaVO.Factory.newInstance();
			filtroCorrespondencia = CorrespDevolvidaVO.FiltroCorrespDevolvida.Factory.newInstance();
			correspDevolvidaVO.setMotivoDevolucaoVOArray(motivoDevolucaoVO);
			correspDevolvidaVO.setTipoCorrespondenciaVOArray(tipoCorrespondenciaVO);
			correspDevolvidaVO.setStatusCorrespondenciaArray(statusCorrespondencia);
		}

		public CorrespDevolvidaVO getCorrespDevolvidaVO() {
			return this.correspDevolvidaVO;
		}

		public MotivoDevolucaoVO[] getMotivoDevolucaoVO() {
			return this.motivoDevolucaoVO;
		}

		public TipoCorrespondenciaVO[] getTipoCorrespondenciaVO() {
			return this.tipoCorrespondenciaVO;
		}

		public void setCorrespDevolvida(CorrespDevolvidaVO correspDevolvidaVO) {
			this.correspDevolvidaVO = correspDevolvidaVO;
		}

		public void setFiltroCorrespondencia(FiltroCorrespDevolvida filtroCorrespondencia) {
			this.filtroCorrespondencia = filtroCorrespondencia;
		}

		public FiltroCorrespDevolvida getFiltroCorrespondencia() {
			return this.filtroCorrespondencia;
		}

		public void setStatusCorrespondencia(StatusCorrespondenciaImpl[] statusCorrespondencia) {
			this.statusCorrespondencia = statusCorrespondencia;
		}

		public void setMotivoDevolucaoVO(MotivoDevolucaoVOImpl[] motivoDevolucaoVO) {
			this.motivoDevolucaoVO = motivoDevolucaoVO;
		}

		public void setTipoCorrespondenciaVO(TipoCorrespondenciaVOImpl[] tipoCorrespondenciaVO) {
			this.tipoCorrespondenciaVO = tipoCorrespondenciaVO;
		}

		public StatusCorrespondencia[] getStatusCorrespondencia() {
			return this.statusCorrespondencia;
		}

		public void setIdTipoCorrespondencia(int idTipoCorrespondencia) {
			this.idTipoCorrespondencia = idTipoCorrespondencia;
		}

		public int getIdTipoCorrespondencia() {
			return this.idTipoCorrespondencia;
		}

		public void setIdMotivoDevolucao(int idMotivoDevolucao) {
			this.idMotivoDevolucao = idMotivoDevolucao;
		}

		public int getIdMotivoDevolucao() {
			return this.idMotivoDevolucao;
		}

		public void setIdStatus(String idStatus) {
			this.idStatus = idStatus;
		}

		public String getIdStatus() {
			return this.idStatus;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getId() {
			return this.id;
		}

		public void setInRefresh(String inRefresh) {
			this.inRefresh = inRefresh;
		}

		public String getInRefresh() {
			return this.inRefresh;
		}
	}

	public static class IncluirForm extends ActionForm {

		private static final long serialVersionUID = 8355616390151233703L;

		private String tipoBusca;
		private PesquisaEnderecoVO pesquisaEndereco;
		private int idTipoSelecionado;
		private int idMotivoSelecionado;
		private String idPaisSelecionado = new String();
		private String idUFSelecionado = new String();
		private PaisVO[] paisVO = new PaisVOImpl[0];
		private BuscaCorrespPor buscaPor;
		private ManterCorrespDevolvidaVO incluiCorresp;
		private TipoCorrespondenciaVO[] tipoCorrespondenciaVO = new TipoCorrespondenciaVOImpl[0];
		private MotivoDevolucaoVO[] motivoDevolucaoVO = new MotivoDevolucaoVOImpl[0];
		private UFVO[] ufVO = new UFVOImpl[0];
		private ListaCorrespCliente[] listaCorrespCliente = new ListaCorrespClienteImpl[0];
		private ListaCorrespCliente correspCliente = ListaCorrespCliente.Factory.newInstance();
		private EnderecoBaseVO enderecoCorresp = EnderecoBaseVO.Factory.newInstance();

		public IncluirForm() {
			pesquisaEndereco = PesquisaEnderecoVO.Factory.newInstance();
			pesquisaEndereco.addNewFiltroPesquisa();
			incluiCorresp = ManterCorrespDevolvidaVO.Factory.newInstance();
			buscaPor = ManterCorrespDevolvidaVO.BuscaCorrespPor.Factory.newInstance();
			incluiCorresp.setUFVOArray(ufVO);
			incluiCorresp.setMotivoDevolucaoVOArray(motivoDevolucaoVO);
			incluiCorresp.setTipoCorrespondenciaVOArray(tipoCorrespondenciaVO);
			incluiCorresp.setPaisVOArray(paisVO);
			correspCliente.setEnderecoBaseVO(enderecoCorresp);
		}

		public ListaCorrespCliente getCorrespCliente() {
			return this.correspCliente;
		}

		public void setCorrespCliente(ListaCorrespCliente correspCliente) {
			this.correspCliente = correspCliente;
		}

		public EnderecoBaseVO getEnderecoCorresp() {
			return this.enderecoCorresp;
		}

		public void setEnderecoCorresp(EnderecoBaseVO enderecoCorresp) {
			this.enderecoCorresp = enderecoCorresp;
		}

		public ManterCorrespDevolvidaVO getIncluirCorresp() {
			return this.incluiCorresp;

		}

		public void setIncluirCorresp(ManterCorrespDevolvidaVO incluiCorresp) {
			this.incluiCorresp = incluiCorresp;
		}

		public UFVO[] getUfVO() {
			return this.ufVO;
		}

		public void setUfVO(UFVOImpl[] ufVO) {
			this.ufVO = ufVO;
		}

		public void setListaCorrespCliente(ListaCorrespClienteImpl[] listaCorrespCliente) {
			this.listaCorrespCliente = listaCorrespCliente;
		}

		public ListaCorrespCliente[] getListaCorrespCliente() {
			return this.listaCorrespCliente;
		}

		public void setMotivoDevolucaoVO(MotivoDevolucaoVOImpl[] motivoDevolucaoVO) {
			this.motivoDevolucaoVO = motivoDevolucaoVO;
		}

		public MotivoDevolucaoVO[] getMotivoDevolucaoVO() {
			return this.motivoDevolucaoVO;
		}

		public void setTipoCorrespondenciaVO(TipoCorrespondenciaVOImpl[] tipoCorrespondenciaVO) {
			this.tipoCorrespondenciaVO = tipoCorrespondenciaVO;
		}

		public TipoCorrespondenciaVO[] getTipoCorrespondenciaVO() {
			return this.tipoCorrespondenciaVO;
		}

		public void setBuscaPor(BuscaCorrespPor buscaPor) {
			this.buscaPor = buscaPor;
		}

		public BuscaCorrespPor getBuscaPor() {
			return this.buscaPor;
		}

		public void setPaisVO(PaisVOImpl[] paisVO) {
			this.paisVO = paisVO;
		}

		public PaisVO[] getPaisVO() {
			return this.paisVO;
		}

		public void setIdUFSelecionado(String idUFSelecionado) {
			this.idUFSelecionado = idUFSelecionado;
		}

		public String getIdUFSelecionado() {
			return this.idUFSelecionado;
		}

		public void setIdPaisSelecionado(String idPaisSelecionado) {
			this.idPaisSelecionado = idPaisSelecionado;
		}

		public String getIdPaisSelecionado() {
			return this.idPaisSelecionado;
		}

		public void setIdMotivoSelecionado(int idMotivoSelecionado) {
			this.idMotivoSelecionado = idMotivoSelecionado;
		}

		public int getIdMotivoSelecionado() {
			return this.idMotivoSelecionado;
		}

		public void setIdTipoSelecionado(int idTipoSelecionado) {
			this.idTipoSelecionado = idTipoSelecionado;
		}

		public int getIdTipoSelecionado() {
			return this.idTipoSelecionado;
		}

		public void setPesquisaEndereco(PesquisaEnderecoVO pesquisaEndereco) {
			this.pesquisaEndereco = pesquisaEndereco;
		}

		public PesquisaEnderecoVO getPesquisaEndereco() {
			return this.pesquisaEndereco;
		}

		public void setTipoBusca(String tipoBusca) {
			this.tipoBusca = tipoBusca;
		}

		public String getTipoBusca() {
			return this.tipoBusca;
		}
	}

	public static class TratarForm extends ActionForm {

		private static final long serialVersionUID = 1023331080744844533L;

		private String statusAtual;
		private String idPaisSelecionado = new String();
		private String idUFSelecionado = new String();
		private String idStatus = new String();
		private PaisVO[] paisVO = new PaisVOImpl[0];
		private UFVO[] ufVO = new UFVOImpl[0];
		private EnderecoBaseVO enderecoTratar = EnderecoBaseVO.Factory.newInstance();
		private ListaStatusCorresp[] listaStatusCorresp = new ListaStatusCorrespImpl[0];
		private ListaStatus[] listaStatus = new ListaStatusImpl[0];
		private TratarCorrespDevolvidaVO tratarCorrespDevolvida;

		public TratarForm() {
			tratarCorrespDevolvida = TratarCorrespDevolvidaVO.Factory.newInstance();
			tratarCorrespDevolvida.setUFVOArray(ufVO);
			tratarCorrespDevolvida.setPaisVOArray(paisVO);
			tratarCorrespDevolvida.setEnderecoBaseVO(enderecoTratar);
			tratarCorrespDevolvida.setListaStatusArray(listaStatus);
			tratarCorrespDevolvida.setListaStatusCorrespArray(listaStatusCorresp);
		}

		public void setTratarCorrespDevolvida(TratarCorrespDevolvidaVO tratarCorrespDevolvida) {
			this.tratarCorrespDevolvida = tratarCorrespDevolvida;
		}

		public TratarCorrespDevolvidaVO getTratarCorrespDevolvida() {
			return this.tratarCorrespDevolvida;
		}

		public void setListaStatus(ListaStatusImpl[] listaStatus) {
			this.listaStatus = listaStatus;
		}

		public ListaStatus[] getListaStatus() {
			return this.listaStatus;
		}

		public void setListaStatusCorresp(ListaStatusCorrespImpl[] listaStatusCorresp) {
			this.listaStatusCorresp = listaStatusCorresp;
		}

		public ListaStatusCorresp[] getListaStatusCorresp() {
			return this.listaStatusCorresp;
		}

		public void setEnderecoBaseVO(EnderecoBaseVO enderecoTratar) {
			this.enderecoTratar = enderecoTratar;
		}

		public EnderecoBaseVO getEnderecoBaseVO() {
			return this.enderecoTratar;
		}

		public void setUfVO(UFVOImpl[] ufVO) {
			this.ufVO = ufVO;
		}

		public UFVO[] getUfVO() {
			return this.ufVO;
		}

		public void setPaisVO(PaisVOImpl[] paisVO) {
			this.paisVO = paisVO;
		}

		public PaisVO[] getPaisVO() {
			return this.paisVO;
		}

		public void setIdStatus(String idStatus) {
			this.idStatus = idStatus;
		}

		public String getIdStatus() {
			return this.idStatus;
		}

		public void setIdUFSelecionado(String idUFSelecionado) {
			this.idUFSelecionado = idUFSelecionado;
		}

		public String getIdUFSelecionado() {
			return this.idUFSelecionado;
		}

		public void setIdPaisSelecionado(String idPaisSelecionado) {
			this.idPaisSelecionado = idPaisSelecionado;
		}

		public String getIdPaisSelecionado() {
			return this.idPaisSelecionado;
		}

		public void setStatusAtual(String statusAtual) {
			this.statusAtual = statusAtual;
		}

		public String getStatusAtual() {
			return this.statusAtual;
		}
	}
}