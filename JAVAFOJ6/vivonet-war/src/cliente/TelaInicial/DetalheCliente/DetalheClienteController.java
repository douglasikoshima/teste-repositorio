package cliente.TelaInicial.DetalheCliente;

import java.io.BufferedOutputStream;
import java.math.BigInteger;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import org.apache.axis.AxisFault;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import workflow.AtendimentoFila.Portabilidade.formBeans.GestorGerenteContaForm;
import br.com.vivo.fo.cliente.vo.ApoioParametroVODocument.ApoioParametroVO;
import br.com.vivo.fo.cliente.vo.CarregarNovoEnderecoVODocument.CarregarNovoEnderecoVO;
import br.com.vivo.fo.cliente.vo.ComunicacaoVODocument.ComunicacaoVO;
import br.com.vivo.fo.cliente.vo.ComunicacoesRecusadasVODocument.ComunicacoesRecusadasVO;
import br.com.vivo.fo.cliente.vo.ComunicacoesRecusadasVODocument.ComunicacoesRecusadasVO.MeioContatoRecusado;
import br.com.vivo.fo.cliente.vo.ConsultorRelacionamentoVODocument.ConsultorRelacionamentoVO;
import br.com.vivo.fo.cliente.vo.ConsultorRelacionamentoVODocument.ConsultorRelacionamentoVO.Lista.Selecionado;
import br.com.vivo.fo.cliente.vo.DocumentoVODocument.DocumentoVO;
import br.com.vivo.fo.cliente.vo.EnderecoBaseVODocument.EnderecoBaseVO;
import br.com.vivo.fo.cliente.vo.EnderecoVODocument.EnderecoVO;
import br.com.vivo.fo.cliente.vo.ImpedimentoVODocument.ImpedimentoVO;
import br.com.vivo.fo.cliente.vo.LinhasPorIdVODocument.LinhasPorIdVO;
import br.com.vivo.fo.cliente.vo.ListaTipoComunicacaoVODocument.ListaTipoComunicacaoVO;
import br.com.vivo.fo.cliente.vo.LupaClienteVODocument.LupaClienteVO;
import br.com.vivo.fo.cliente.vo.LupaClienteVODocument.LupaClienteVO.DadosAbaLupaCliente.ContasPontuacao;
import br.com.vivo.fo.cliente.vo.LupaClienteVODocument.LupaClienteVO.DadosLupaCliente.ListaImpedimentoVO;
import br.com.vivo.fo.cliente.vo.MeioContatoVODocument.MeioContatoVO;
import br.com.vivo.fo.cliente.vo.MeioContatoVODocument.MeioContatoVO.MeioContato;
import br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.cliente.vo.PesquisaEnderecoVODocument.PesquisaEnderecoVO;
import br.com.vivo.fo.cliente.vo.PesquisaEnderecoVODocument.PesquisaEnderecoVO.ListaEnderecos;
import br.com.vivo.fo.cliente.vo.TelaInicialVODocument.TelaInicialVO;
import br.com.vivo.fo.commons.utils.GetParametro;
import br.com.vivo.fo.commons.vo.IDValorRelacionamentoVODocument.IDValorRelacionamentoVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.cliente.associarGestor.RelacionarGestor;
import br.com.vivo.fo.ctrls.cliente.associarGestor.db.ClienteEspecial;
import br.com.vivo.fo.ctrls.cliente.associarGestor.db.PessoaConsultor;
import br.com.vivo.fo.ctrls.cliente.prePago.manutencaoPrePago.ManutencaoPrePagoFacade;
import br.com.vivo.fo.ctrls.cliente.telaInicial.TelaInicialFacade;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.CanaisExistentesVODocument.CanaisExistentesVO;
import br.com.vivo.fo.usuario.vo.CanaisExistentesVODocument.CanaisExistentesVO.CanaisExistentes;
import br.com.vivo.fo.usuario.vo.CanaisRelacionadosVODocument.CanaisRelacionadosVO;
import br.com.vivo.fo.usuario.vo.CanaisRelacionadosVODocument.CanaisRelacionadosVO.CanaisRelacionados;
import br.com.vivo.vol.menu.vo.ARVOREDocument.ARVORE;
import cliente.URLErro;
import cliente.TelaInicial.TelaInicialController.TIForm;
import com.indracompany.actions.AbstractAction;
import com.indracompany.ws.contatoservice.ContatoCorporativoBindingQSService;
import com.indracompany.ws.contatoservice.ContatoCorporativoPortType;
import com.indracompany.ws.contatoservice.Contatos;
import com.indracompany.ws.contatoservice.Documentos;
import com.indracompany.ws.contatoservice.Gestor;
import com.indracompany.ws.contatoservice.ParametrosManterContatoCorporativo;
import com.indracompany.ws.enderecoservice.EnderecoSOAPProxy;
import com.indracompany.ws.enderecoservice.ParametrosBuscarListaEnderecos;
import com.indracompany.ws.enderecoservice.SecurityHeaderHelper;
import com.indracompany.ws.enderecoservice.to.Endereco;
import com.indracompany.ws.enderecoservice.to.ErroInfo;
import com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe;
import commons.errors.FormError;

@SuppressWarnings({"unused","rawtypes"})
public class DetalheClienteController extends AbstractAction {

	private static final long serialVersionUID = 7907249897604345963L;
	private static final String targetNameSpace = "http://www.vivo.com.br/MC/Pessoa";

	@EJB
	private TelaInicialFacade lupaClienteFacadeControl;

	@EJB
	private ManutencaoPrePagoFacade prePagoFacade;

	@EJB
	private RelacionarGestor associarGestorFacade;

	private Boolean pesquisaEnderecoWebService = null;

	protected global.Global globalApp = new global.Global();

    private LupaClienteVO lupaCliente = null;
    private DocumentoVO[] documento = null;
    private ComunicacaoVO[] contato = null;
    private EnderecoVO[] endereco = null;
    private ImpedimentoVO[] impedimento = null;
    private ContasPontuacao[] contasPontuacao = null;

    private CanaisExistentesVO canaisExistentesVO;
    private CanaisRelacionadosVO canaisRelacionadosVO;
    private MeioContatoVO meioContatoVO;
    private ComunicacoesRecusadasVO comunicacoesRecusadasVO;

    private PermissoesForm permissoesForm;

    private String user = null;
    private String idPessoa = null;
    private String nrLinha = null;
    private String idContaSistemaOrigem = null;

    private final String PARAMETRO_PESQUISA_ENDERECO_NA_BASE = "IN_PESQUISA_ENDERECO_BASE";
    private final String PARAMETRO_ENDPOINT_PESQUISAENDERECO = "BUSCAR_LISTA_ENDERECOS_ENDPOINT";

    private static Logger logLupaCliente = new Logger("lupacliente");

    private ARVORE[] arvore;

    private GestorGerenteContaForm gestorGerenteContaForm;

	public static final String GESTOR_CONTA = "GC";
	public static final String GESTOR_MASTER = "GM";
	public static final String GERENTE_DE_CONTA = "GRC";

	private TIForm tiForm;
    private AbaContato abaContato;
    private AbaEndereco abaEndereco;
    private FormID formID;

	public TIForm getTiForm() {
		if (this.tiForm == null) {
			this.tiForm = new TIForm();
		}
		return this.tiForm;
	}

	public AbaEndereco getAbaEndereco() {
		return this.abaEndereco;
	}

	public void setAbaEndereco(AbaEndereco abaEndereco) {
		this.abaEndereco = abaEndereco;
	}

	public FormID getFormID() {
		return this.formID;
	}

	public void setFormID(FormID formID) {
		this.formID = formID;
	}

	public AbaContato getAbaContato() {
		return this.abaContato;
	}

	public void setAbaContato(AbaContato abaContato) {
		this.abaContato = abaContato;
	}

	public GestorGerenteContaForm getGestorGerenteContaForm() {
		if (this.gestorGerenteContaForm == null) {
			this.gestorGerenteContaForm = new GestorGerenteContaForm();
		}
		return this.gestorGerenteContaForm;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("done".equals(mapping.getParameter())) {
			return done(mapping, form, request, response);
		} else if ("salvar".equals(mapping.getParameter())) {
			return salvar(mapping, form, request, response);
		} else if ("voltar".equals(mapping.getParameter())) {
			return voltar(mapping, form, request, response);
		} else if ("loadEndereco".equals(mapping.getParameter())) {
			return loadEndereco(mapping, form, request, response);
		} else if ("loadPermissoes".equals(mapping.getParameter())) {
			return loadPermissoes(mapping, form, request, response);
		} else if ("loadDocumento".equals(mapping.getParameter())) {
			return loadDocumento(mapping, form, request, response);
		} else if ("loadContato".equals(mapping.getParameter())) {
			return loadContato(mapping, form, request, response);
		} else if ("DetalheCliente".equals(mapping.getParameter())) {
			return DetalheCliente(mapping, form, request, response);
		} else if ("loadDadosProfiler".equals(mapping.getParameter())) {
			return loadDadosProfiler(mapping, form, request, response);
		} else if ("loadPontuacao".equals(mapping.getParameter())) {
			return loadPontuacao(mapping, form, request, response);
		} else if ("verificaNumeroVivo".equals(mapping.getParameter())) {
			return verificaNumeroVivo(mapping, form, request, response);
		} else if ("salvarEndereco".equals(mapping.getParameter())) {
			return salvarEndereco(mapping, form, request, response);
		} else if ("controlarEndereco".equals(mapping.getParameter())) {
			return controlarEndereco(mapping, form, request, response);
		} else if ("controlarContato".equals(mapping.getParameter())) {
			return controlarContato(mapping, form, request, response);
		} else if ("salvarContato".equals(mapping.getParameter())) {
			return salvarContato(mapping, form, request, response);
		} else if ("pesquisaEndereco".equals(mapping.getParameter())) {
			return pesquisaEndereco(mapping, form, request, response);
		} else if ("buscaEndereco".equals(mapping.getParameter())) {
			return buscaEndereco(mapping, form, request, response);
		} else if ("alterarEmailLegado".equals(mapping.getParameter())) {
			return alterarEmailLegado(mapping, form, request, response);
		} else if ("controlaSequencia".equals(mapping.getParameter())) {
			return controlaSequencia(mapping, form, request, response);
		} else if ("getGestorGerenteConta".equals(mapping.getParameter())) {
			return getGestorGerenteConta(mapping, form, request, response);
		} else if ("setGestorGerenteConta".equals(mapping.getParameter())) {
			return setGestorGerenteConta(mapping, form, request, response);
		} else if ("getClienteEspecial".equals(mapping.getParameter())) {
			return getClienteEspecial(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="DetalheCliente.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logLupaCliente.debug("DetalheClienteController:begin(" + user + ") >> INICIALIZADO");
			idPessoa = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaCliente();
			nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
			idContaSistemaOrigem = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdContaSistemaOrigem();
			abaContato = new AbaContato();
			logLupaCliente.debug("DetalheClienteController:begin(" + user + ") >> idPessoa: " + idPessoa);
			logLupaCliente.debug("DetalheClienteController:begin(" + user + ") >> nrLinha: " + nrLinha);
			logLupaCliente.debug("DetalheClienteController:begin(" + user + ") >> idContaSistemaOrigem: " + idContaSistemaOrigem);
			logLupaCliente.debug("DetalheClienteController:begin(" + user + ") >> FINALIZADO");
			ActionRedirect d = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
			d.addParameter("aba", request.getParameter("aba"));
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return d;

		} catch (Exception e) {
			logLupaCliente.error("DetalheClienteController:begin(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="done" return-action="ClienteDone"
	 */
	public ActionForward done(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("done");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="permissoes.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			PermissoesForm form = (PermissoesForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logLupaCliente.debug("DetalheClienteController:salvar(" + user + ") >> INICIALIZADO");
			canaisRelacionadosVO = CanaisRelacionadosVO.Factory.newInstance();
			canaisRelacionadosVO.setIdPessoa(lupaCliente.getDadosLupaCliente().getIdPessoa());

			comunicacoesRecusadasVO = ComunicacoesRecusadasVO.Factory.newInstance();
			comunicacoesRecusadasVO.setIdPessoa(lupaCliente.getDadosLupaCliente().getIdPessoa());

			// Percorre o array de ids de formCanaisRelacionados e grava no canaisRelacionadosVO
			for (int i = 0; i < form.formCanaisRelacionados.length; i++) {
				canaisRelacionadosVO.insertNewCanaisRelacionados(i);
				CanaisRelacionados idCanais = CanaisRelacionados.Factory.newInstance();
				idCanais.setIdCanal(form.formCanaisRelacionados[i]);
				for (int x = 0; x < permissoesForm.formCanaisExistentesVO.length; x++) {
					if (permissoesForm.formCanaisExistentesVO[x].getIdCanal().equalsIgnoreCase(form.formCanaisRelacionados[i])) {
						idCanais.setNmCanal(permissoesForm.formCanaisExistentesVO[x].getNmCanal());
						x = permissoesForm.formCanaisExistentesVO.length + 1;
					}
				}

				canaisRelacionadosVO.setCanaisRelacionadosArray(i, idCanais);
			}

			// Percorre o array de ids de formCanaisRelacionados e grava no canaisRelacionadosVO
			for (int i = 0; i < form.formMeioContatoRecusado.length; i++) {
				comunicacoesRecusadasVO.insertNewMeioContatoRecusado(i);
				MeioContatoRecusado idMeio = MeioContatoRecusado.Factory.newInstance();
				idMeio.setIdMeioContato(form.formMeioContatoRecusado[i]);
				for (int x = 0; x < permissoesForm.formMeioContatoVO.length; x++) {
					if (permissoesForm.formMeioContatoVO[x].getIdMeioContato().equalsIgnoreCase(form.formMeioContatoRecusado[i])) {
						idMeio.setDsMeioContato(permissoesForm.formMeioContatoVO[x].getDsMeioContato());
						x = permissoesForm.formMeioContatoVO.length + 1;
					}
				}
				comunicacoesRecusadasVO.setMeioContatoRecusadoArray(i, idMeio);
			}

			lupaClienteFacadeControl.salvarCanaisRelacionados(canaisRelacionadosVO, user);
			lupaClienteFacadeControl.salvarComunicacoesRecusadas(comunicacoesRecusadasVO, user);

			permissoesForm.setFormMeioContatoRecusado(form.getFormMeioContatoRecusado());
			permissoesForm.setFormMeioContatoRecusadoVO(comunicacoesRecusadasVO.getMeioContatoRecusadoArray());

			permissoesForm.setFormCanaisRelacionados(form.getFormCanaisRelacionados());
			permissoesForm.setFormCanaisRelacionadosVO(canaisRelacionadosVO.getCanaisRelacionadosArray());
			logLupaCliente.debug("DetalheClienteController:salvar(" + user + ") >> FINALIZADO");

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			logLupaCliente.error("DetalheClienteController:salvar(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
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
	 * @jpf:forward name="success" path="abaEndereco.jsp"
	 * @jpf:forward name="controlar" path="controlarEndereco.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			AbaEndereco form = (AbaEndereco) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logLupaCliente.debug("DetalheClienteController:loadEndereco(" + user + ") >> INICIALIZADO");
			String destino = ConstantesCRM.SVAZIO;
			String idPessoa = ConstantesCRM.SVAZIO;

			if (!form.getInReload().equals(ConstantesCRM.STRUE) && abaEndereco != null && abaEndereco.getInReload() != null) {
				form.setInReload(abaEndereco.getInReload());
				abaEndereco.setInReload(ConstantesCRM.SVAZIO);
			}

			if (request.getParameter("idPessoaCliente") == null) {
				idPessoa = form.getIdPessoa();
			} else {
				idPessoa = request.getParameter("idPessoaCliente");
			}

			if (idPessoa != null) {
				form.setAbaEndereco(lupaClienteFacadeControl.getEndereco(user, idPessoa).getDadosAbaLupaCliente().getEnderecoVOArray());
				form.setIdPessoa(idPessoa);
			}

			String param = request.getParameter("tipo");

			if (request.getParameter("reload") == "ok") {
				lupaCliente = lupaClienteFacadeControl.getEndereco(user, request.getParameter("idPessoa"));
				endereco = lupaCliente.getDadosAbaLupaCliente().getEnderecoVOArray();
				form.setAbaEndereco(endereco);
				destino = ConstantesCRM.SUCCESS;
			}

			if (request.getAttribute("reload") == "ok") {
				lupaCliente = lupaClienteFacadeControl.getEndereco(user, request.getParameter("idPessoa"));
				endereco = lupaCliente.getDadosAbaLupaCliente().getEnderecoVOArray();
				form.setAbaEndereco(endereco);
				destino = ConstantesCRM.SUCCESS;
			} else {
				if (param == null) {
					destino = ConstantesCRM.SUCCESS;
				} else {
					form.setAcao(request.getParameter("tipo"));
					form.setIdArray(request.getParameter("idArray"));
					form.setIdEndereco(request.getParameter("idEndereco"));
					form.getEndereco().setInSincronismo(request.getParameter("sincronizado"));
					destino = "controlar";
				}
			}
			logLupaCliente.debug("DetalheClienteController:loadEndereco(" + user + ") >> FINALIZADO");

			request.setAttribute("form", form);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);

		} catch (Exception e) {
			logLupaCliente.error("DetalheClienteController:loadEndereco(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="permissoes.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadPermissoes(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			// Monta o log da operação se possível

			user = ConstantesCRM.getCurrentUser(request.getSession());
			logLupaCliente.debug("DetalheClienteController:loadPermissoes(" + user + ") >> INICIALIZADO");
			ActionForward forward = mapping.findForward(ConstantesCRM.SUCCESS);
			permissoesForm = new PermissoesForm();

			canaisExistentesVO = lupaClienteFacadeControl.getCanaisExistentesVO(user);
			permissoesForm.setFormCanaisExistentesVO(canaisExistentesVO.getCanaisExistentesArray());

			canaisRelacionadosVO = CanaisRelacionadosVO.Factory.newInstance();
			canaisRelacionadosVO.setIdPessoa(lupaCliente.getDadosLupaCliente().getIdPessoa());
			canaisRelacionadosVO = lupaClienteFacadeControl.getCanaisRelacionadosVO(canaisRelacionadosVO, user);
			permissoesForm.setFormCanaisRelacionadosVO(canaisRelacionadosVO.getCanaisRelacionadosArray());

			meioContatoVO = lupaClienteFacadeControl.getMeioContatoVO(user);
			permissoesForm.setFormMeioContatoVO(meioContatoVO.getMeioContatoArray());

			comunicacoesRecusadasVO = ComunicacoesRecusadasVO.Factory.newInstance();
			comunicacoesRecusadasVO.setIdPessoa(lupaCliente.getDadosLupaCliente().getIdPessoa());
			comunicacoesRecusadasVO = lupaClienteFacadeControl.getComunicacoesRecusadasVO(comunicacoesRecusadasVO, user);
			permissoesForm.setFormMeioContatoRecusadoVO(comunicacoesRecusadasVO.getMeioContatoRecusadoArray());
			logLupaCliente.debug("DetalheClienteController:loadPermissoes(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return forward;

		} catch (Exception e) {
			logLupaCliente.error("DetalheClienteController:loadPermissoes(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaDocumento.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadDocumento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logLupaCliente.debug("DetalheClienteController:loadDocumento(" + user + ") >> INICIALIZADO");
			ActionForward forward = mapping.findForward(ConstantesCRM.SUCCESS);
			if (documento == null) {
				documento = (DocumentoVO[]) request.getSession().getAttribute("documentos");
				request.getSession().removeAttribute("documentos");
			}

			request.setAttribute("piDocumento", documento);
			logLupaCliente.debug("DetalheClienteController:loadDocumento(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return forward;

		} catch (Exception e) {
			logLupaCliente.error("DetalheClienteController:loadDocumento(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaContato.jsp"
	 * @jpf:forward name="controlar" path="controlarContato.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			AbaContato form = (AbaContato) formParam;
			String destino = ConstantesCRM.SVAZIO;
			String idPessoa = ConstantesCRM.SVAZIO;

			user = ConstantesCRM.getCurrentUser(request.getSession());
			logLupaCliente.debug("DetalheClienteController:loadContato(" + user + ") >> INICIALIZADO");
			if (!form.getInReload().equals(ConstantesCRM.STRUE) && abaContato != null && abaContato.getInReload() != null) {
				form.setInReload(abaContato.getInReload());
				abaContato.setInReload(ConstantesCRM.SVAZIO);
			}

			if (request.getParameter("idPessoaCliente") == null) {
				idPessoa = form.getIdPessoa();
			} else {
				idPessoa = request.getParameter("idPessoaCliente");
			}

			if (idPessoa != null) {
				form.setAbaContato(lupaClienteFacadeControl.getContato(user, idPessoa).getDadosAbaLupaCliente().getComunicacaoVOArray());
				if (abaContato == null) {
					abaContato = new AbaContato();
				}
				abaContato.setAbaContato(form.getAbaContato());
				abaContato.setIdPessoa(idPessoa);
				form.setIdPessoa(idPessoa);
			}

			String param = request.getParameter("tipo");

			if (request.getAttribute("reload") == "ok") {
				lupaCliente = lupaClienteFacadeControl.getContato(user, request.getParameter("idPessoa"));
				contato = lupaCliente.getDadosAbaLupaCliente().getComunicacaoVOArray();
				form.setAbaContato(contato);
				abaContato.setAbaContato(form.getAbaContato());
				destino = ConstantesCRM.SUCCESS;

			} else {
				if (param == null) {
					destino = ConstantesCRM.SUCCESS;
				} else {
					form.setAcao(request.getParameter("tipo"));
					form.setIdArray(request.getParameter("idArray"));
					form.setIdComunicacao(request.getParameter("idComunicacao"));
					abaContato.setAcao(request.getParameter("tipo"));
					abaContato.setIdArray(request.getParameter("idArray"));
					abaContato.setIdComunicacao(request.getParameter("idComunicacao"));
					destino = "controlar";
				}
			}
			logLupaCliente.debug("DetalheClienteController:loadContato(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);

		} catch (Exception e) {
			logLupaCliente.error("DetalheClienteController:loadContato(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="lupaCliente.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward DetalheCliente(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			FormID form = (FormID) formParam;
			formID = new FormID();
			int idTipoLinha = 0;

			user = ConstantesCRM.getCurrentUser(request.getSession());
			logLupaCliente.debug("DetalheClienteController:DetalheCliente(" + user + ") >> INICIALIZADO");
			idTipoLinha = ConstantesCRM.SVAZIO.equals(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha()) ? 0 : Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());

			lupaCliente = lupaClienteFacadeControl.getLupaClienteVO(user, idPessoa, nrLinha, idContaSistemaOrigem, idTipoLinha);
			lupaCliente.getDadosLupaCliente().setIdPessoa(idPessoa);
			documento = lupaCliente.getDadosAbaLupaCliente().getDocumentoVOArray();
			String tpLin = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha();

			if (tpLin != null && (ConstantesCRM.SONE.equals(tpLin) || "5".equals(tpLin))) {
				if (lupaCliente.getDadosLupaCliente().getListaImpedimentoVO() != null && lupaCliente.getDadosLupaCliente().getListaImpedimentoVO().getErro() != null && lupaCliente.getDadosLupaCliente().getListaImpedimentoVO().getErro().length() > 0) {
					lupaCliente.getDadosLupaCliente().getListaImpedimentoVO().addNewImpedimentoVO();
					impedimento = lupaCliente.getDadosLupaCliente().getListaImpedimentoVO().getImpedimentoVOArray();
					impedimento[0].setDsImpedimento("Sistema de IMPEDIMENTOS está temporariamente fora de serviço!");

				} else {
					impedimento = lupaCliente.getDadosLupaCliente().getListaImpedimentoVO().getImpedimentoVOArray();
				}

			} else {
				lupaCliente.getDadosLupaCliente().setListaImpedimentoVO(ListaImpedimentoVO.Factory.newInstance());
			}

			formID.setIdPessoaCliente(lupaCliente.getDadosLupaCliente().getIdPessoa());

			request.setAttribute("piLupaCliente", lupaCliente);
			request.setAttribute("piImpedimentos", impedimento);

			logLupaCliente.debug("DetalheClienteController:DetalheCliente(" + user + ") >> FINALIZADO");

			request.setAttribute("aba", request.getParameter("aba"));
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			logLupaCliente.error("DetalheClienteController:DetalheCliente(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="/cliente/TelaInicial/DetalheCliente/customer/CustomerController.jpf"
	 */
	public ActionForward loadDadosProfiler(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute("documentos", documento);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaPontos.jsp"
	 * @jpf:forward name="erroPontos" path="erroPontos.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadPontuacao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logLupaCliente.debug("DetalheClienteController:loadPontuacao(" + user + ") >> INICIALIZADO");
			int conta = 0;
			if (request.getParameter("conta") != null) {
				conta = Integer.parseInt(request.getParameter("conta"));
			}

			LupaClienteVO abaPontos = LupaClienteVO.Factory.newInstance();

			if (nrLinha == null) {
				nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
			}
			abaPontos = lupaClienteFacadeControl.getPontos(user, nrLinha);
			contasPontuacao = abaPontos.getDadosAbaLupaCliente().getContasPontuacaoArray();
			request.setAttribute("dtVigenciaP", abaPontos.getDadosAbaLupaCliente().getDtVigencia());
			request.setAttribute("dtVigenciaP", abaPontos.getDadosAbaLupaCliente().getDtVigencia());
			request.setAttribute("piPontuacao", contasPontuacao);

			if (contasPontuacao[0].getErro() != null) {
				logLupaCliente.debug("DetalheClienteController:loadPontuacao(" + user + ") >> FINALIZADO (" + contasPontuacao[0].getErro() + ")");
				ActionForward forward = mapping.findForward("erroPontos");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return forward;

			} else {
				ActionForward forward = mapping.findForward(ConstantesCRM.SUCCESS);
				if (conta >= contasPontuacao.length) {
					conta = contasPontuacao.length - 1;
				}
				request.setAttribute("piLinPontos", contasPontuacao[conta].getLinhaPontosArray());
				logLupaCliente.debug("DetalheClienteController:loadPontuacao(" + user + ") >> FINALIZADO");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return forward;
			}
		} catch (Exception e) {
			logLupaCliente.error("DetalheClienteController:loadPontuacao(" + user + ") - [" + e.getMessage() + "]", e);
			ActionForward forward = mapping.findForward("erroPontos");
			request.setAttribute("dtVigenciaP", ConstantesCRM.SVAZIO);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return forward;
		}
	}

	private boolean isClientePJ(String nrLinha, HttpServletRequest request) {
		boolean isPJ = false;
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			if (nrLinha != null && nrLinha.length() > 0) {
				nrLinha = nrLinha.replaceAll("[^0-9]", ConstantesCRM.SVAZIO);
			}

			ParametrosVO resultadoVO = lupaClienteFacadeControl.getParametrosVO(user, nrLinha, ConstantesCRM.STWO);
			if (resultadoVO != null && "PJ".equals(resultadoVO.getInTipoPessoa()) && (ConstantesCRM.SONE.equals(resultadoVO.getIdTipoLinha()) || "5".equals(resultadoVO.getIdTipoLinha()))) {
				isPJ = true;
			} else {
				isPJ = false;
			}
		} catch (Exception e) {
		}
		return isPJ;
	}

	/**
	 * @jpf:action
	 */
	public ActionForward verificaNumeroVivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		AbaEndereco form = (AbaEndereco) formParam;
		String retorno = ConstantesCRM.SVAZIO;
		String cdErro = ConstantesCRM.SZERO;
		String msErro = ConstantesCRM.SVAZIO;
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			String nrLinha = request.getParameter("nrLinha");
			if (nrLinha != null && nrLinha.length() > 0) {
				nrLinha = nrLinha.replaceAll("[^0-9]", ConstantesCRM.SVAZIO);
			}

			ParametrosVO resultadoVO = lupaClienteFacadeControl.getParametrosVO(user, nrLinha, ConstantesCRM.STWO);
			if (resultadoVO != null && resultadoVO.getIdLinha() != null && !ConstantesCRM.SVAZIO.equals(resultadoVO.getIdLinha().trim())) {
				retorno = ConstantesCRM.SOK;
			} else {
				retorno = ConstantesCRM.SNOK;
			}

		} catch (Exception e) {
			retorno = ConstantesCRM.SNOK;
			cdErro = ConstantesCRM.SONE;
			msErro = e.getMessage();

		} finally {
			try {
				String xmlOUT = "<msg><retorno>" + retorno + "</retorno><cdErro>" + cdErro + "</cdErro><msErro>" + msErro + "</msErro></msg>";
				response.setContentType("text/plain;charset=ISO-8859-1");
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
				bufferedOutputStream.write(xmlOUT.getBytes(ConstantesCRM.SISO));
				bufferedOutputStream.flush();
				bufferedOutputStream.close();
			} catch (Exception e) {
			}
		}
		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="loadEndereco.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvarEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			AbaEndereco form = (AbaEndereco) formParam;
			LupaClienteVO enderecoAlterado = LupaClienteVO.Factory.newInstance();
			nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
			enderecoAlterado.addNewDadosAbaLupaCliente().addNewComunicacaoVO();
			enderecoAlterado.addNewDadosLupaCliente().setNrLinha(nrLinha);
			enderecoAlterado.getDadosAbaLupaCliente().addNewEnderecoVO();
			String resposta = ConstantesCRM.SVAZIO;

			user = ConstantesCRM.getCurrentUser(request.getSession());
			logLupaCliente.debug("DetalheClienteController:salvarEndereco(" + user + ") >> INICIALIZADO");

			if (request.getParameter("tipo").equalsIgnoreCase("alteracao")) {
				form.endereco.setDsEnderecoComplemento(form.endereco.getDsEnderecoComplemento().trim());
				form.endereco.setNmBairro(form.endereco.getNmBairro().trim());
				form.endereco.setNmLogradouro(form.endereco.getNmLogradouro().trim());
				form.endereco.setNmMunicipio(form.endereco.getNmMunicipio().trim());
				form.endereco.setNmTipoLogradouro(form.endereco.getNmTipoLogradouro().trim());
				form.endereco.setNmTituloLogradouro(form.endereco.getNmTituloLogradouro().trim());
				// BREDES
				form.endereco.setInCnl(form.endereco.getInCnl().trim());
				form.endereco.setCodLogradouro(form.endereco.getCodLogradouro().trim());
				form.endereco.setInCodigoIBGE(form.endereco.getInCodigoIBGE().trim());

				form.endereco.setNrEndereco(form.endereco.getNrEndereco().trim());
				enderecoAlterado.getDadosAbaLupaCliente().setEnderecoVOArray(0, form.endereco);
				enderecoAlterado.getDadosAbaLupaCliente().getEnderecoVOArray(0).addNewPaisVO();
				enderecoAlterado.getDadosAbaLupaCliente().getEnderecoVOArray(0).getPaisVO().setIdPais(form.idPaisSelecionado);
				enderecoAlterado.getDadosAbaLupaCliente().getEnderecoVOArray(0).addNewUFVO();
				enderecoAlterado.getDadosAbaLupaCliente().getEnderecoVOArray(0).getUFVO().setIdUF(form.idUFSelecionado);
				enderecoAlterado.getDadosAbaLupaCliente().getEnderecoVOArray(0).addNewTipoEnderecoVO();
				enderecoAlterado.getDadosAbaLupaCliente().getEnderecoVOArray(0).getTipoEnderecoVO().setIdTipoEndereco(form.getIdTipoSelecionado());
				enderecoAlterado.getDadosAbaLupaCliente().getEnderecoVOArray(0).setIdEndereco(form.getIdEndereco());

				if (enderecoAlterado.getDadosLupaCliente() != null && (ConstantesCRM.SVAZIO.equals(enderecoAlterado.getDadosLupaCliente().getNrLinha()) || enderecoAlterado.getDadosLupaCliente().getNrLinha() == null)) {
					enderecoAlterado.getDadosLupaCliente().setNrLinha("99999999");
				}

				resposta = lupaClienteFacadeControl.setSalvarAlterarEndereco(user, enderecoAlterado);
				if (abaEndereco == null) {
					abaEndereco = new AbaEndereco();
				}
				abaEndereco.setInReload(ConstantesCRM.STRUE);

			} else {
				form.endereco.setDsEnderecoComplemento(form.endereco.getDsEnderecoComplemento().trim());
				form.endereco.setNmBairro(form.endereco.getNmBairro().trim());
				form.endereco.setNmLogradouro(form.endereco.getNmLogradouro().trim());
				form.endereco.setNmMunicipio(form.endereco.getNmMunicipio().trim());
				form.endereco.setNmTipoLogradouro(form.endereco.getNmTipoLogradouro().trim());
				form.endereco.setNmTituloLogradouro(form.endereco.getNmTituloLogradouro().trim());
				form.endereco.setNrEndereco(form.endereco.getNrEndereco().trim());
				// BREDES
				form.endereco.setInCnl(form.endereco.getInCnl().trim());
				form.endereco.setCodLogradouro(form.endereco.getCodLogradouro().trim());
				form.endereco.setInCodigoIBGE(form.endereco.getInCodigoIBGE().trim());

				enderecoAlterado.getDadosLupaCliente().setIdPessoa(form.getIdPessoa());
				enderecoAlterado.getDadosAbaLupaCliente().setEnderecoVOArray(0, form.endereco);
				enderecoAlterado.getDadosAbaLupaCliente().getEnderecoVOArray(0).addNewPaisVO();
				enderecoAlterado.getDadosAbaLupaCliente().getEnderecoVOArray(0).getPaisVO().setIdPais(form.idPaisSelecionado);
				enderecoAlterado.getDadosAbaLupaCliente().getEnderecoVOArray(0).addNewUFVO();
				enderecoAlterado.getDadosAbaLupaCliente().getEnderecoVOArray(0).getUFVO().setIdUF(form.idUFSelecionado);
				enderecoAlterado.getDadosAbaLupaCliente().getEnderecoVOArray(0).addNewTipoEnderecoVO();
				enderecoAlterado.getDadosAbaLupaCliente().getEnderecoVOArray(0).getTipoEnderecoVO().setIdTipoEndereco(form.getIdTipoSelecionado());
				resposta = lupaClienteFacadeControl.setSalvarNovoEndereco(user, enderecoAlterado);
				if (abaEndereco == null) {
					abaEndereco = new AbaEndereco();
				}
				abaEndereco.setInReload(ConstantesCRM.STRUE);
			}

			if (resposta.length() != 0) {
				form.setInMsgRetorno("true");
				String msgErro = resposta.indexOf("]") > -1 ? resposta.substring(resposta.indexOf("]") + 1) : resposta;
				form.setDsMsgRetorno(msgErro);
			} else {
				form.setInMsgRetorno("false");
			}

			request.setAttribute("reload", "ok");
			logLupaCliente.debug("DetalheClienteController:salvarEndereco(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			logLupaCliente.error("DetalheClienteController:salvarEndereco(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="excluir" path="loadEndereco.do"
	 * @jpf:forward name="novo" path="incluiEndereco.jsp"
	 * @jpf:forward name="alterar" path="alteraEndereco.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward controlarEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			AbaEndereco form = (AbaEndereco) formParam;
			String destino = form.getAcao();
			String sincrozinado = form.getEndereco().getInSincronismo();
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logLupaCliente.debug("DetalheClienteController:controlarEndereco(" + user + ") >> INICIALIZADO");
			abaEndereco = form;
			abaEndereco.setInMsgRetorno("false");
			try {
				if (destino.equalsIgnoreCase("excluir")) {
					LupaClienteVO deleteEndereco = LupaClienteVO.Factory.newInstance();
					deleteEndereco.addNewDadosAbaLupaCliente();
					deleteEndereco.getDadosAbaLupaCliente().addNewEnderecoVO();
					deleteEndereco.getDadosAbaLupaCliente().getEnderecoVOArray(0).setIdEndereco(form.getIdEndereco());
					request.setAttribute("reload", "ok");
					lupaClienteFacadeControl.setExcluirEndereco(user, deleteEndereco);
					abaEndereco.setInReload(ConstantesCRM.STRUE);
					logLupaCliente.debug("DetalheClienteController:controlarEndereco(" + user + ") >> FINALIZADO (EXCLUSÃO)");
				} else if (destino.equalsIgnoreCase("alterar")) {
					EnderecoVO alterarEndereco = EnderecoVO.Factory.newInstance();
					alterarEndereco.addNewPaisVO();
					alterarEndereco.addNewTipoEnderecoVO();
					alterarEndereco.addNewUFVO();
					alterarEndereco = form.abaEndereco[Integer.parseInt(form.getIdArray())];
					abaEndereco.setEndereco(alterarEndereco);
					abaEndereco.setListas(lupaClienteFacadeControl.getIncluirEndereco(user));
					abaEndereco.setIdPaisSelecionado(abaEndereco.endereco.getPaisVO().getIdPais());
					abaEndereco.setIdTipoSelecionado(abaEndereco.endereco.getTipoEnderecoVO().getIdTipoEndereco());
					abaEndereco.setIdUFSelecionado(abaEndereco.endereco.getUFVO().getIdUF());
					abaEndereco.setIdEndereco(form.getIdEndereco());
					abaEndereco.setIdPessoa(form.getIdPessoa());
					abaEndereco.getEndereco().setInSincronismo(sincrozinado);
					logLupaCliente.debug("DetalheClienteController:controlarEndereco(" + user + ") >> FINALIZADO (ALTERAÇÃO)");
				} else {
					EnderecoVO incluirEnderecoVO = EnderecoVO.Factory.newInstance();
					incluirEnderecoVO.addNewPaisVO();
					incluirEnderecoVO.addNewTipoEnderecoVO();
					incluirEnderecoVO.addNewUFVO();
					abaEndereco.setEndereco(incluirEnderecoVO);
					abaEndereco.setListas(lupaClienteFacadeControl.getIncluirEndereco(user));
					abaEndereco.setIdPessoa(form.getIdPessoa());
					logLupaCliente.debug("DetalheClienteController:controlarEndereco(" + user + ") >> FINALIZADO (INCLUSÃO)");
				}
			} catch (Exception e) {
				abaEndereco.setInMsgRetorno("true");
				String msgErro = e.getMessage().indexOf("]") > -1 ? e.getMessage().substring(e.getMessage().indexOf("]") + 1) : e.getMessage();
				abaEndereco.setDsMsgRetorno(msgErro);
			}
			request.setAttribute("abaEndereco", abaEndereco);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);

		} catch (Exception e) {
			logLupaCliente.error("DetalheClienteController:controlarEndereco(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="novo" path="incluiContato.jsp"
	 * @jpf:forward name="alterar" path="alteraContato.jsp"
	 * @jpf:forward name="excluir" path="loadContato.do"
	 * @jpf:forward name="executa" path="executa.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward controlarContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			AbaContato form = (AbaContato) formParam;
			String destino = form.getAcao();
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logLupaCliente.debug("DetalheClienteController:controlarContato(" + user + ") >> INICIALIZADO");

			if ("excluir".equalsIgnoreCase(destino)) {
				logLupaCliente.debug("DetalheClienteController:controlarContato(" + user + ") >> AÇAO: EXCLUSÃO");
				abaContato = new AbaContato();

				if (form.abaContato[Integer.parseInt(form.getIdArray())].getTipoComunicacaoVO().getSgTipoComunicacao().indexOf("EM PART") == 0 || form.abaContato[Integer.parseInt(form.getIdArray())].getTipoComunicacaoVO().getSgTipoComunicacao().indexOf("EM COM") == 0) {
					abaContato.setDsOldEmail(form.abaContato[Integer.parseInt(form.getIdArray())].getDsContato());
					abaContato.setInEmail(true);
				} else {
					abaContato.setDsOldEmail(ConstantesCRM.SVAZIO);
					abaContato.setInEmail(false);
				}

				logLupaCliente.debug("DetalheClienteController:controlarContato(" + user + ") >> AÇAO: EXCLUSÃO DE CONTATOS");
				LupaClienteVO deleteComunicacao = LupaClienteVO.Factory.newInstance();
				deleteComunicacao.addNewDadosAbaLupaCliente();
				deleteComunicacao.getDadosAbaLupaCliente().addNewComunicacaoVO();
				deleteComunicacao.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setIdComunicacao(form.getIdComunicacao());
				request.setAttribute("reload", "ok");
				lupaClienteFacadeControl.setExcluirComunicacao(user, deleteComunicacao);

				/*
				 * if (abaContato.inEmail){ //Executa para exclusao de e-mail
				 * logLupaCliente.debug("DetalheClienteController:controlarContato("
				 * +user+") >> AÇAO: EXCLUSÃO DE E-MAIL"); //String tmpEmail =
				 * form.abaContato[Integer.parseInt(form.getIdArray())].getDsContato();
				 *
				 * if(checaEmail(tmpEmail, "delete", ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO)){ // nao encontrou e-mail no legado
				 * logLupaCliente.debug
				 * ("DetalheClienteController:controlarContato("+user+") >> E-mail não encontrado no legado");
				 * LupaClienteVO deleteComunicacao = LupaClienteVO.Factory.newInstance();
				 * deleteComunicacao.addNewDadosAbaLupaCliente();
				 * deleteComunicacao.getDadosAbaLupaCliente().addNewComunicacaoVO();
				 * deleteComunicacao.getDadosAbaLupaCliente
				 * ().getComunicacaoVOArray(0).setIdComunicacao(form.getIdComunicacao());
				 * request.setAttribute("reload","ok"); lupaClienteFacadeControl.setExcluirComunicacao(user,
				 * deleteComunicacao); }else{ // Encontrou email no legado
				 * logLupaCliente.debug("DetalheClienteController:controlarContato("
				 * +user+") >> E-mail encontrado no legado"); abaContato.setIdPessoa(form.getIdPessoa());
				 * destino="executa"; } }else{ //Executa para exclusao de outros tipos de contato
				 * logLupaCliente.debug("DetalheClienteController:controlarContato("
				 * +user+") >> AÇAO: EXCLUSÃO DE CONTATOS"); LupaClienteVO deleteComunicacao =
				 * LupaClienteVO.Factory.newInstance(); deleteComunicacao.addNewDadosAbaLupaCliente();
				 * deleteComunicacao.getDadosAbaLupaCliente().addNewComunicacaoVO();
				 * deleteComunicacao.getDadosAbaLupaCliente
				 * ().getComunicacaoVOArray(0).setIdComunicacao(form.getIdComunicacao());
				 * request.setAttribute("reload","ok"); lupaClienteFacadeControl.setExcluirComunicacao(user,
				 * deleteComunicacao); }
				 */

				abaContato.setInReload(ConstantesCRM.STRUE);
				logLupaCliente.debug("DetalheClienteController:controlarContato(" + user + ") >> FINALIZADO");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(destino);
			} else if (destino.equalsIgnoreCase("alterar")) {
				// AbaContato abaContato = new AbaContato();
				logLupaCliente.debug("DetalheClienteController:controlarContato(" + user + ") >> AÇAO: ALTERAÇÃO");
				if (form.abaContato[Integer.parseInt(form.getIdArray())].getTipoComunicacaoVO().getSgTipoComunicacao().indexOf("EM PART") == 0 || form.abaContato[Integer.parseInt(form.getIdArray())].getTipoComunicacaoVO().getSgTipoComunicacao().indexOf("EM COM") == 0) {
					abaContato.setDsOldEmail(form.abaContato[Integer.parseInt(form.getIdArray())].getDsContato());
					abaContato.setInEmail(true);
				} else {
					abaContato.setDsOldEmail(ConstantesCRM.SVAZIO);
					abaContato.setInEmail(false);
				}
				ComunicacaoVO alterarComunicacao = ComunicacaoVO.Factory.newInstance();
				alterarComunicacao.addNewTipoComunicacaoVO();
				alterarComunicacao = form.abaContato[Integer.parseInt(form.getIdArray())];
				abaContato.setContato(alterarComunicacao);
				abaContato.setIdComunicacao(form.getIdComunicacao());
				abaContato.setIdPessoa(form.getIdPessoa());
				logLupaCliente.debug("DetalheClienteController:controlarContato(" + user + ") >> FINALIZADO");

				request.setAttribute("abaContato", abaContato);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(destino);

			} else {
				AbaContato abaContato = new AbaContato();
				ComunicacaoVO incluirComunicacao = ComunicacaoVO.Factory.newInstance();
				incluirComunicacao.addNewTipoComunicacaoVO();

				String nrSeqLast = ConstantesCRM.SVAZIO;
				// Pega o numero de sequencia do ultimo item do Array
				if (this.abaContato.getAbaContato() != null && this.abaContato.getAbaContato().length > 0) {
					nrSeqLast = this.abaContato.getAbaContato()[this.abaContato.getAbaContato().length - 1].getNrSequencia();
				}
				// Se numero de sequencia vier vazio, setar como numero alto para não acontecer duplicação (tratamento
				// sera feito quando alterar-se sequencia, no metodo controlarSequencia())
				if (nrSeqLast == ConstantesCRM.SVAZIO) {
					nrSeqLast = "999";
				}
				// Seta com nrSequencia do ultimo item do Array + 1 (para entrar no final da lista)
				incluirComunicacao.setNrSequencia(Integer.toString(Integer.parseInt(nrSeqLast) + 1));
				abaContato.setContato(incluirComunicacao);
				abaContato.setListaTipos(lupaClienteFacadeControl.getIncluirComunicacao(user));
				abaContato.setIdPessoa(form.getIdPessoa());
				// abaContato.getAbaContato()[0].setNrSequencia("50");
				logLupaCliente.debug("DetalheClienteController:controlarContato(" + user + ") >> FINALIZADO");
				request.setAttribute("abaContato", abaContato);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(destino);
			}
		} catch (Exception e) {
			logLupaCliente.error("DetalheClienteController:controlarContato(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="loadContato.do"
	 * @jpf:forward name="executa" path="executa.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvarContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			AbaContato form = (AbaContato) formParam;
			LupaClienteVO comunicacaoAlterada = LupaClienteVO.Factory.newInstance();
			comunicacaoAlterada.addNewDadosAbaLupaCliente().addNewComunicacaoVO();
			comunicacaoAlterada.addNewDadosLupaCliente();
			String resposta = ConstantesCRM.SVAZIO;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logLupaCliente.debug("DetalheClienteController:salvarContato(" + user + ") >> INICIALIZADO");
			if (request.getParameter("tipo").equalsIgnoreCase("alteracao")) {
				String tmpEmail = form.contato.getDsContato().trim();
				logLupaCliente.debug("DetalheClienteController:salvarContato(" + user + ") >> AÇAO: ALTERACAO");
				if (form.inEmail == true) {
					if (checaEmail(tmpEmail, "update", form.getIdComunicacao(), form.contato.getDsContato().trim(), form.contato.getNrSequencia().trim(), request)) {
						comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setIdComunicacao(form.getIdComunicacao());
						comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setDsContato(form.contato.getDsContato().trim());
						comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setNrSequencia(form.contato.getNrSequencia().trim());
						resposta = lupaClienteFacadeControl.setSalvarAlterarComunicacao(user, comunicacaoAlterada, form.getIdPessoa());
						form.setInReload(ConstantesCRM.STRUE);
					} else {
						request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
						return mapping.findForward("executa");
					}
				} else {
					comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setIdComunicacao(form.getIdComunicacao());
					comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setDsContato(form.contato.getDsContato().trim());
					comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setNrSequencia(form.contato.getNrSequencia().trim());
					resposta = lupaClienteFacadeControl.setSalvarAlterarComunicacao(user, comunicacaoAlterada, form.getIdPessoa());
					form.setInReload(ConstantesCRM.STRUE);
				}
			} else {
				logLupaCliente.debug("DetalheClienteController:salvarContato(" + user + ") >> AÇAO: INCLUSAO");
				comunicacaoAlterada.getDadosLupaCliente().setIdPessoa(form.getIdPessoa());
				comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setDsContato(form.contato.getDsContato().trim());
				comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setNrSequencia(form.contato.getNrSequencia().trim());
				comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).addNewTipoComunicacaoVO();
				comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).getTipoComunicacaoVO().setIdTipoComunicacao(form.getIdTipoSelecionado());
				resposta = lupaClienteFacadeControl.setSalvarNovaComunicacao(user, comunicacaoAlterada);
				form.setInReload(ConstantesCRM.STRUE);
			}
			abaContato = new AbaContato();
			abaContato.setIdPessoa(form.getIdPessoa());
			abaContato.setInReload(form.getInReload());

			if (resposta.indexOf("DUPLICATE KEY") > 0) {
				abaContato.setInMsgRetorno("true");
				abaContato.setMsgAlerta("Contato não pôde ser incluído/alterado pois já existe um contato do mesmo tipo com essa descrição ou uma seqüencia de contato com este valor!");
			} else {
				request.setAttribute("reload", "ok");
				abaContato.setInMsgRetorno("false");
			}
			logLupaCliente.debug("DetalheClienteController:salvarContato(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("executa");
		} catch (Exception e) {
			logLupaCliente.error("DetalheClienteController:salvarContato(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	protected boolean checaEmail(String contato, String acao, String id, String ds, String nr, HttpServletRequest request) throws Exception {

		try {
			logLupaCliente.debug("DetalheClienteController:checaEmail(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") >> INICIALIZADO");
			LinhasPorIdVO filtro = LinhasPorIdVO.Factory.newInstance();
			filtro.setIdPessoa(idPessoa);
			if (acao.equalsIgnoreCase("delete")) {
				logLupaCliente.debug("DetalheClienteController:checaEmail(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") >> AÇAO: EXCLUSAO");
				// Retorna todoas as linhas do cliente(id e linha)
				abaContato.setLinhas(lupaClienteFacadeControl.getLinhasPorId(user, filtro));
				if (abaContato.getLinhas().getLinhasArray().length > 0) {

					LinhasPorIdVO linhasId = abaContato.getLinhas();

					// Gera Array arvore[] com linhas para ser enviado para o Facade que
					// retornara se email existe no legado
					arvore = new ARVORE[abaContato.getLinhas().getLinhasArray().length];
					for (int i = 0; i < abaContato.getLinhas().getLinhasArray().length; i++) { // Executa conforme qtde
						// de linhas
						arvore[i] = ARVORE.Factory.newInstance();
						arvore[i].setCdAreaRegistro(linhasId.getLinhasArray(i).getNrLinha().substring(0, 2));
						arvore[i].setIdTipoLinha(linhasId.getLinhasArray(i).getIdTipoLinha());
						arvore[i].setNrLinha(linhasId.getLinhasArray(i).getNrLinha().substring(2, nrLinha.length()));
						arvore[i].setStatus(ConstantesCRM.SVAZIO);
					}

					ApoioParametroVO apoio = ApoioParametroVO.Factory.newInstance();
					apoio.setCdParametro(ConstantesCRM.CD_PARAM_URL_SERVICE_ROUTER);
					apoio = prePagoFacade.getApoioParametro(user, apoio);

					List receivePesquisa = prePagoFacade.pesquisaEmailLegado(user, abaContato.getDsOldEmail(), arvore, apoio.getDsValorParametro());

					// Devolve numero de vezes em que o email foi encontrado nas linhas do cliente no legado.
					// Se maior que 1, informa existencia de vinculo entre emails no FO e legado e nao permite exclusao.
					if (receivePesquisa.size() > 0) {

						String nrLinhas[] = new String[receivePesquisa.size()];
						// Todo: verificar se eh somente uma linha... se for uma, informa uma linha
						// se for mais que uma, informar todas as linhas
						String linha = "";
						if (receivePesquisa.size() == 1) {
							ARVORE arvoreLinhas = (ARVORE) receivePesquisa.get(0);
							linha = ConstantesCRM.formatPhoneNumber(arvoreLinhas.getNrLinha());
							//nrLinhas[0] = "(" + arvoreLinhas.getCdAreaRegistro() + ")" + arvoreLinhas.getNrLinha().substring(0, 4) + "-" + arvoreLinhas.getNrLinha().substring(4, 8);
							nrLinhas[0] = "(" + arvoreLinhas.getCdAreaRegistro() + ")" + linha;
							abaContato.setMsgAlerta("E-mail vinculado ao envio de comprovante/conta para a linha " + nrLinhas[0] + ".\\nPara excluí-lo é necessário desvinculá-lo do envio!");
						} else {
							String formataLinhas = ConstantesCRM.SVAZIO;
							for (int i = 0; i < receivePesquisa.size(); i++) {
								ARVORE arvoreLinhas = (ARVORE) receivePesquisa.get(i);
								linha = ConstantesCRM.formatPhoneNumber(arvoreLinhas.getNrLinha());
								nrLinhas[i] = "(" + arvoreLinhas.getCdAreaRegistro() + ")" + linha;
							}
							for (int j = 0; j < receivePesquisa.size(); j++) {
								formataLinhas += nrLinhas[j] + ", ";
							}
							formataLinhas = formataLinhas.substring(0, formataLinhas.length() - 2);
							formataLinhas = formataLinhas.substring(0, formataLinhas.lastIndexOf(",")) + " e" + formataLinhas.substring(formataLinhas.lastIndexOf(",") + 1);
							abaContato.setMsgAlerta("E-mail vinculado ao envio de comprovante/conta para as linhas " + formataLinhas + ".\\nPara excluí-lo é necessário desvinculá-lo do envio!");
						}

						abaContato.setIdComunicacao(id);
						abaContato.getContato().setDsContato(ds);
						abaContato.getContato().setNrSequencia(nr);
						abaContato.setInMsgRetorno("naoPermiteExclusao");
						return false;
					} else { // Nao encontrou e-mail igual no legado
						abaContato.setInMsgRetorno("false");
						return true;
					}

				} else {
					abaContato.setInMsgRetorno("false");
					return true;
				}
			} else {
				if (contato.equalsIgnoreCase(abaContato.getDsOldEmail())) {
					abaContato.setInMsgRetorno("false");
					return true;
				} else { // Executa caso seja alteracao
					logLupaCliente.debug("DetalheClienteController:checaEmail(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") >> AÇAO: ALTERAÇÃO");
					// Retorna todoas as linhas do cliente(id e linha)
					abaContato.setLinhas(lupaClienteFacadeControl.getLinhasPorId(user, filtro));
					if (abaContato.getLinhas().getLinhasArray().length > 0) {

						LinhasPorIdVO linhasId = abaContato.getLinhas();

						// Gera Array arvore[] com linhas para ser enviado para o Facade que
						// retornara se email existe no legado
						arvore = new ARVORE[abaContato.getLinhas().getLinhasArray().length];
						for (int i = 0; i < abaContato.getLinhas().getLinhasArray().length; i++) { // Executa conforme
							// qtde de linhas
							arvore[i] = ARVORE.Factory.newInstance();
							arvore[i].setCdAreaRegistro(linhasId.getLinhasArray(i).getNrLinha().substring(0, 2));
							arvore[i].setIdTipoLinha(linhasId.getLinhasArray(i).getIdTipoLinha());
							arvore[i].setNrLinha(linhasId.getLinhasArray(i).getNrLinha().substring(2, nrLinha.length()));
							arvore[i].setStatus(ConstantesCRM.SVAZIO);
						}

						ApoioParametroVO apoio = ApoioParametroVO.Factory.newInstance();
						apoio.setCdParametro(ConstantesCRM.CD_PARAM_URL_SERVICE_ROUTER);
						apoio = prePagoFacade.getApoioParametro(user, apoio);
						List receivePesquisa = prePagoFacade.pesquisaEmailLegado(user, abaContato.getDsOldEmail(), arvore, apoio.getDsValorParametro());

						// Devolve numero de vezes em que o email foi encontrado nas linhas do cliente no legado.
						// Se maior que 1, pede confirmacao para alterar tambem no legado
						if (receivePesquisa.size() > 0) {
							abaContato.setIdComunicacao(id);
							abaContato.getContato().setDsContato(ds);
							abaContato.getContato().setNrSequencia(nr);
							abaContato.setInMsgRetorno("confirm");
							return false;
						} else {
							abaContato.setInMsgRetorno("false");
							return true;
						}

					} else {
						abaContato.setInMsgRetorno("false");
						return true;
					}
				}
				// }
			}

		} catch (Exception e) {
			logLupaCliente.error("DetalheClienteController:checaEmail(" + user + ") - [" + e.getMessage() + "]", e);
			abaContato.setIdComunicacao(id);
			abaContato.getContato().setDsContato(ds);
			abaContato.getContato().setNrSequencia(nr);
			abaContato.setInMsgRetorno("erroLegado");
			if (acao.equals("delete")) {
				abaContato.setMsgAlerta("Houve um problema durante consulta ao sistema legado.\\nTemporariamente este endereço de e-mail não poderá ser excluído.");
			} else if (acao.equals("update")) {
				abaContato.setMsgAlerta("Houve um problema durante consulta ao sistema legado.\\nTemporariamente este endereço de e-mail não poderá ser alterado.");
			}
			return false;
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="cepinclui" path="incluiEndereco.jsp"
	 * @jpf:forward name="cepaltera" path="alteraEndereco.jsp"
	 * @jpf:forward name="pesquisar" path="pesquisaEndereco.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward pesquisaEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			AbaEndereco form = (AbaEndereco) formParam;
			String destino = ConstantesCRM.SVAZIO;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logLupaCliente.debug("DetalheClienteController:pesquisaEndereco(" + user + ") >> INICIALIZADO");
			if (request.getParameter("pagina").equalsIgnoreCase("inicioPesquisa")) {
				AbaEndereco tempForm = abaEndereco;

				abaEndereco = new AbaEndereco();
				PesquisaEnderecoVO pesquisaEndereco = PesquisaEnderecoVO.Factory.newInstance();
				pesquisaEndereco = lupaClienteFacadeControl.getPesquisaEnderecoIni(user);
				abaEndereco.setPesquisaEndereco(pesquisaEndereco);
				abaEndereco.getPesquisaEndereco().addNewFiltroPesquisa();
				abaEndereco.getPesquisaEndereco().addNewListaEnderecos();

				if (request.getParameter("limpar") != null && request.getParameter("limpar").equalsIgnoreCase(ConstantesCRM.STRUE)) {
					String idEndereco = tempForm.getIdEndereco();
					String idPessoa = tempForm.getIdPessoa();
					abaEndereco.setIdEndereco(idEndereco);
					abaEndereco.setIdPessoa(idPessoa);
				} else {
					request.getSession().setAttribute("retorno", request.getParameter("retorno").toString());
					abaEndereco.setIdEndereco(request.getParameter("idEndereco"));
					abaEndereco.setIdPessoa(request.getParameter("idPessoa"));
				}

				destino = "pesquisar";
				abaEndereco.setIdTipoSelecionado(request.getParameter("idTipoSelecionado"));

				request.setAttribute("abaEndereco", abaEndereco);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(destino);

			} else {

				String idArray = ConstantesCRM.SVAZIO;
				EnderecoVO endereco = EnderecoVO.Factory.newInstance();
				abaEndereco.setListas(lupaClienteFacadeControl.getIncluirEndereco(user));

				if (request.getParameter("idArray") != null) {
					idArray = request.getParameter("idArray");
				} else {
					idArray = ConstantesCRM.SVAZIO;
				}

				abaEndereco.setEndereco(abaEndereco.getPesquisaEndereco().getListaEnderecos().getEnderecoVOArray(Integer.parseInt(idArray)));
				abaEndereco.getEndereco().getTipoEnderecoVO().setIdTipoEndereco(form.getEndereco().getTipoEnderecoVO().getIdTipoEndereco());
				abaEndereco.setIdUFSelecionado(abaEndereco.getPesquisaEndereco().getListaEnderecos().getEnderecoVOArray(Integer.parseInt(idArray)).getUFVO().getIdUF());
				abaEndereco.setIdPaisSelecionado(abaEndereco.getPesquisaEndereco().getListaEnderecos().getEnderecoVOArray(Integer.parseInt(idArray)).getPaisVO().getIdPais());
				abaEndereco.getEndereco().setIdEndereco(abaEndereco.getIdEndereco());

				if (request.getSession().getAttribute("retorno").toString().equalsIgnoreCase("inclui")) {
					request.getSession().removeAttribute("retorno");
					destino = "cepinclui";
				} else {
					request.getSession().removeAttribute("retorno");
					destino = "cepaltera";
				}
				logLupaCliente.debug("DetalheClienteController:pesquisaEndereco(" + user + ") >> FINALIZADO");

				request.setAttribute("abaEndereco", abaEndereco);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(destino);
			}
		} catch (Exception e) {
			logLupaCliente.error("DetalheClienteController:pesquisaEndereco(" + user + ") - [" + e.getMessage() + "]", e);
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
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward buscaEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		try {
			AbaEndereco form = (AbaEndereco) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			PesquisaEnderecoVO filtroEndereco = PesquisaEnderecoVO.Factory.newInstance();
			filtroEndereco.addNewFiltroPesquisa();
			abaEndereco.getPesquisaEndereco().addNewListaEnderecos();
			logLupaCliente.debug("DetalheClienteController:buscaEndereco(" + user + ") >> INICIALIZADO");
	        int segundosTimeout = 20;

			if (request.getParameter("iniciarTela") == null) {
				filtroEndereco.getFiltroPesquisa().setDsLogradouro(request.getParameter("filtroPesquisa.dsLogradouro").trim());
				filtroEndereco.getFiltroPesquisa().setDsBairro(request.getParameter("filtroPesquisa.dsBairro").trim());
				filtroEndereco.getFiltroPesquisa().setDsLocalidade(request.getParameter("filtroPesquisa.dsLocalidade").trim());
				filtroEndereco.getFiltroPesquisa().setNrCEP(request.getParameter("filtroPesquisa.nrCEP").trim());
				filtroEndereco.getFiltroPesquisa().setIdUFSelecionado(request.getParameter("filtroPesquisa.idUFSelecionado").trim());

				PesquisaEnderecoVO pesquisaEnderecoVO = PesquisaEnderecoVO.Factory.newInstance();
				ParametroVO parametro;

				try {
					parametro = GetParametro.getInstace().getParametroVO(user, PARAMETRO_PESQUISA_ENDERECO_NA_BASE);
				} catch (Exception e) {
					parametro = ParametroVO.Factory.newInstance();
				}
				pesquisaEnderecoWebService = new Boolean(ConstantesCRM.SZERO.equals(parametro.getDsValorParametro()) ? true : false);
				abaEndereco.setPesquisaBaseFO(pesquisaEnderecoWebService.booleanValue() ? false : true);

				if (pesquisaEnderecoWebService.booleanValue()) {
					parametro = GetParametro.getInstace().getParametroVO(user, PARAMETRO_ENDPOINT_PESQUISAENDERECO);
					String sgUF = ConstantesCRM.SVAZIO.equals(request.getParameter("sgUF")) ? null : request.getParameter("sgUF");
	                EnderecoSOAPProxy proxy = new EnderecoSOAPProxy();
	                proxy.setEndpoint(new URL(parametro.getDsValorParametro()).toString());


	                String usuario = ConstantesCRM.SVAZIO;
	                String senha = ConstantesCRM.SVAZIO;
	                try {
	                    usuario = br.com.vivo.fo.commons.utils.GetParametro.getInstace().getParametroVO(user, ConstantesCRM.ALSB3SOA_SECURITY_USER).getDsValorParametro();
	                    senha = br.com.vivo.fo.commons.utils.GetParametro.getInstace().getParametroVO(user, ConstantesCRM.ALSB3SOA_SECURITY_PASSWORD).getDsValorParametro();
	                } catch (Exception e) {
	                	logLupaCliente.error("DetalheClienteController:buscaEndereco(" + user + ")Erro ao buscar usuário e senha :", e);
	                }

	                SecurityHeaderHelper securityHeader = new SecurityHeaderHelper();
	    	        securityHeader.setUserTimeout(new Integer(segundosTimeout * 1000));
	                securityHeader.setSecurityUserName(usuario);
	    	        securityHeader.setSecurityPassword(senha);
	    	        proxy.setSecurityHeaderHelper(securityHeader);

	    	        logLupaCliente.debug("DetalheClienteController:buscaEndereco:: securityHeader: (" + user + ") securityHeader : " + securityHeader);

	                Endereco[] retorno = null;
					try {
						BigInteger numeroPaginaBI = new BigInteger(ConstantesCRM.SONE);
						BigInteger qtdRegistroBI = new BigInteger("50");

						String dataHora = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
						logLupaCliente.debug("DetalheClienteController:buscaEndereco: buscarListaEnderecosControl.buscarListaEnderecos: (user=" + user + ") begin : " + dataHora);

	                    ParametrosBuscarListaEnderecos parametrosBuscarListaEnderecos = new ParametrosBuscarListaEnderecos();
	                    parametrosBuscarListaEnderecos.setNumeroCEP(ConstantesCRM.SVAZIO.equals(request.getParameter("filtroPesquisa.nrCEP"))? null: request.getParameter("filtroPesquisa.nrCEP").trim());
	                    parametrosBuscarListaEnderecos.setDescricaoLogradouro(ConstantesCRM.SVAZIO.equals(request.getParameter("filtroPesquisa.dsLogradouro"))? null:request.getParameter("filtroPesquisa.dsLogradouro").trim());
	                    parametrosBuscarListaEnderecos.setDescricaoCidade(ConstantesCRM.SVAZIO.equals(request.getParameter("filtroPesquisa.dsLocalidade"))? null: request.getParameter("filtroPesquisa.dsLocalidade").trim());
	                    parametrosBuscarListaEnderecos.setDescricaoBairro(ConstantesCRM.SVAZIO.equals(request.getParameter("filtroPesquisa.dsBairro"))? null: request.getParameter("filtroPesquisa.dsBairro").trim());
	                    parametrosBuscarListaEnderecos.setSiglaUf(sgUF);
	                    parametrosBuscarListaEnderecos.setNumeroPagina(numeroPaginaBI);
	                    parametrosBuscarListaEnderecos.setQtdRegistro(qtdRegistroBI);

	                    retorno = proxy.buscarListaEnderecos(parametrosBuscarListaEnderecos);

	                    dataHora = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
	                    logLupaCliente.debug("DetalheClienteController:buscaEndereco: proxy.buscarListaEnderecos(user=" + user + ") end : " + dataHora);

	                } catch (ErroInfo e) {
	                    logLupaCliente.debug("DetalheClienteController:buscaEndereco: " + e);
	                    retorno = null;
	                    pesquisaEnderecoVO.addNewListaEnderecos();
	                    abaEndereco.getPesquisaEndereco().setListaEnderecos(pesquisaEnderecoVO.getListaEnderecos());
	                    if ("31072".equals(e.getCodigo())) {
	                    	request.setAttribute("erro", e.getDescricao());
	                    } else {
	                    	request.setAttribute("erro", "Serviço de Busca de Endereço por CEP indisponível no momento. [" + e.getDescricao() + "]");
	                    }

	                } catch (AxisFault axisFault) {
	                    logLupaCliente.debug("DetalheClienteController:buscaEndereco: " + axisFault);
	                    retorno = null;
	                    pesquisaEnderecoVO.addNewListaEnderecos();
	                    abaEndereco.getPesquisaEndereco().setListaEnderecos(pesquisaEnderecoVO.getListaEnderecos());
	                    request.setAttribute("erro", "Serviço de Busca de Endereço por CEP indisponível no momento. [" + axisFault.getFaultString() + "]");

					} catch (Exception e) {
						logLupaCliente.debug("DetalheClienteController:buscaEndereco: " + e);
						ListaEnderecos listaEnderecos = ListaEnderecos.Factory.newInstance();
						pesquisaEnderecoVO.setListaEnderecos(listaEnderecos);
						abaEndereco.setPesquisaEndereco(pesquisaEnderecoVO);
						abaEndereco.setIsAddrPresent(false);
						request.setAttribute("abaEndereco", abaEndereco);
						request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
						return mapping.findForward(ConstantesCRM.SUCCESS);
					}

					logLupaCliente.debug("DetalheClienteController:buscaEndereco:retorno " + retorno);
					if(retorno != null) {
						ListaEnderecos listaEnderecos = ListaEnderecos.Factory.newInstance();
						for (int i = 0; i < retorno.length; i++) {
							listaEnderecos.addNewEnderecoVO();

							listaEnderecos.getEnderecoVOArray(i).addNewTipoEnderecoVO();
							listaEnderecos.getEnderecoVOArray(i).setIdEndereco(ConstantesCRM.SVAZIO);
							listaEnderecos.getEnderecoVOArray(i).setNmTipoLogradouro(retorno[i].getTipoLogradouro() == null || retorno[i].getTipoLogradouro().getDescricao() == null ? ConstantesCRM.SVAZIO : retorno[i].getTipoLogradouro().getDescricao());
							listaEnderecos.getEnderecoVOArray(i).setNmTituloLogradouro(retorno[i].getTituloLogradouro() == null || retorno[i].getTituloLogradouro().getDescricao() == null ? ConstantesCRM.SVAZIO : retorno[i].getTituloLogradouro().getDescricao());

							listaEnderecos.getEnderecoVOArray(i).setDsEnderecoComplemento(ConstantesCRM.SVAZIO);
							listaEnderecos.getEnderecoVOArray(i).setNrEndereco(ConstantesCRM.SVAZIO);
							listaEnderecos.getEnderecoVOArray(i).setNmLogradouro(retorno[i].getLogradouro() == null ? ConstantesCRM.SVAZIO : retorno[i].getLogradouro());
							listaEnderecos.getEnderecoVOArray(i).setNmBairro(retorno[i].getBairro() == null ? ConstantesCRM.SVAZIO : retorno[i].getBairro());
							listaEnderecos.getEnderecoVOArray(i).setNmMunicipio(retorno[i].getCidade() == null ? ConstantesCRM.SVAZIO : retorno[i].getCidade());
							listaEnderecos.getEnderecoVOArray(i).setNrCEP(retorno[i].getCep() == null ? ConstantesCRM.SVAZIO : retorno[i].getCep());
							listaEnderecos.getEnderecoVOArray(i).setDsLado(retorno[i].getDescricaoLado() == null ? ConstantesCRM.SVAZIO : retorno[i].getDescricaoLado());

							listaEnderecos.getEnderecoVOArray(i).addNewUFVO();
							listaEnderecos.getEnderecoVOArray(i).getUFVO().setIdUF(retorno[i].getUf() == null || retorno[i].getUf().getCodigo() == null ? ConstantesCRM.SVAZIO : retorno[i].getUf().getCodigo().toString());
							listaEnderecos.getEnderecoVOArray(i).getUFVO().setSgUF(retorno[i].getUf() == null || retorno[i].getUf().getSigla() == null ? ConstantesCRM.SVAZIO : retorno[i].getUf().getSigla());
							listaEnderecos.getEnderecoVOArray(i).getUFVO().setNmUF(retorno[i].getUf() == null || retorno[i].getUf().getNome() == null ? ConstantesCRM.SVAZIO : retorno[i].getUf().getNome());

							listaEnderecos.getEnderecoVOArray(i).addNewPaisVO();
							listaEnderecos.getEnderecoVOArray(i).getPaisVO().setIdPais(retorno[i].getPais() == null || retorno[i].getPais().getCodigo() == null ? ConstantesCRM.SVAZIO : retorno[i].getPais().getCodigo().toString());
							listaEnderecos.getEnderecoVOArray(i).getPaisVO().setSgPais(retorno[i].getPais() == null || retorno[i].getPais().getSigla() == null ? ConstantesCRM.SVAZIO : retorno[i].getPais().getSigla());
							listaEnderecos.getEnderecoVOArray(i).getPaisVO().setNmPais(retorno[i].getPais() == null || retorno[i].getPais().getNome() == null ? ConstantesCRM.SVAZIO : retorno[i].getPais().getNome());

							listaEnderecos.getEnderecoVOArray(i).setCodLogradouro(retorno[i].getCodigo() == null ? ConstantesCRM.SVAZIO : retorno[i].getCodigo().toString());
							listaEnderecos.getEnderecoVOArray(i).setInCnl(retorno[i].getEnderecoFiscal() == null || retorno[i].getEnderecoFiscal().getCodigoNacLocalidade() == null ? ConstantesCRM.SVAZIO : retorno[i].getEnderecoFiscal().getCodigoNacLocalidade());
							listaEnderecos.getEnderecoVOArray(i).setInCodigoIBGE(retorno[i].getEnderecoFiscal() == null || retorno[i].getEnderecoFiscal().getCodigoIBGE() == null ? ConstantesCRM.SVAZIO : retorno[i].getEnderecoFiscal().getCodigoIBGE());
						}
						pesquisaEnderecoVO.setListaEnderecos(listaEnderecos);
					}

				} else {
					pesquisaEnderecoVO = lupaClienteFacadeControl.getPesquisaEnderecoFil(user, filtroEndereco);
				}

				abaEndereco.setPesquisaEndereco(pesquisaEnderecoVO);
				if (abaEndereco.getPesquisaEndereco().getListaEnderecos().getEnderecoVOArray().length == 0) {
					abaEndereco.setIsAddrPresent(false);
				} else {
					abaEndereco.setIsAddrPresent(true);
				}
			}

			logLupaCliente.debug("DetalheClienteController:buscaEndereco(" + user + ") >> FINALIZADO");

			request.setAttribute("abaEndereco", abaEndereco);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			logLupaCliente.error("DetalheClienteController:buscaEndereco(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	private String toUTF8(String content) throws Exception {
		return new String(content.getBytes(ConstantesCRM.SISO), ConstantesCRM.SUTF8);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="executa.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward alterarEmailLegado(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			AbaContato form = (AbaContato) formParam;
			LupaClienteVO comunicacaoAlterada = LupaClienteVO.Factory.newInstance();
			comunicacaoAlterada.addNewDadosAbaLupaCliente().addNewComunicacaoVO();
			comunicacaoAlterada.addNewDadosLupaCliente();
			String resposta = ConstantesCRM.SVAZIO;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logLupaCliente.debug("DetalheClienteController:alterarEmailLegado(" + user + ") >> INICIALIZADO");
			form.setIdComunicacao(request.getParameter("idEmail"));

			if (form.getInMsgRetorno().equals("erroFO")) {
				form.getContato().setDsContato(abaContato.getDsOldEmail());
				form.setInMsgRetorno(null);
			} else {
				form.getContato().setDsContato(request.getParameter("dsEmail"));
				form.setInMsgRetorno(null);
			}

			form.getContato().setNrSequencia(request.getParameter("nrSequencia"));
			form.setIdPessoa(abaContato.getIdPessoa());

			comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setIdComunicacao(form.getIdComunicacao());
			comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setDsContato(form.contato.getDsContato().trim());
			comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setNrSequencia(form.contato.getNrSequencia().trim());

			int persisteLegado = 0;

			ApoioParametroVO apoio = ApoioParametroVO.Factory.newInstance();
			apoio.setCdParametro(ConstantesCRM.CD_PARAM_URL_SERVICE_ROUTER);
			apoio = prePagoFacade.getApoioParametro(user, apoio);

			// CHAMADA DE ALTERAÇÃO DE E-MAIL NO LEGADO
			try {
				for (int i = 0; i < arvore.length; i++) {
					prePagoFacade.persisteContaEmail(user, arvore[i].getCdAreaRegistro(), arvore[i].getNrLinha(), arvore[i].getIdTipoLinha(), form.contato.getDsContato().trim(), apoio.getDsValorParametro());
				}

				persisteLegado = 1;
				resposta = lupaClienteFacadeControl.setSalvarAlterarComunicacao(user, comunicacaoAlterada, form.getIdPessoa());
				form.setInReload(ConstantesCRM.STRUE);

				abaContato = new AbaContato();
				abaContato.setIdPessoa(form.getIdPessoa());
				abaContato.setInReload(form.getInReload());

				if (resposta.indexOf("DUPLICATE KEY") > 0) {
					abaContato.setInMsgRetorno("true");
					abaContato.setMsgAlerta("Contato não pôde ser incluído/alterado pois já existe um contato do mesmo tipo com essa descrição ou uma seqüencia de contato com este valor!");
				} else {
					request.setAttribute("reload", "ok");
					abaContato.setInMsgRetorno("false");
				}
				logLupaCliente.debug("DetalheClienteController:alterarEmailLegado(" + user + ") >> FINALIZADO");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);

			} catch (Exception e) {

				if (persisteLegado == 1) { // erro foi na base FO
					logLupaCliente.error("DetalheClienteController:alterarEmailLegado(" + user + ") - [Erro no FO] [" + e.getMessage() + "]", e);
					// precisa chamar alteracao de legado novamente passando o email antigo
					form.contato.setDsContato(abaContato.getDsOldEmail());
					// alterarEmailLegado(form); -> quem chama eh o proprio jsp
					abaContato.setInMsgRetorno("erroFO");
					abaContato.setMsgAlerta("Houve um problema na alteração do Contato na base do FO. Alteração não realizada.");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(ConstantesCRM.SUCCESS);

				} else { // erro foi no legado
					logLupaCliente.error("DetalheClienteController:alterarEmailLegado(" + user + ") - [Erro no legado] [" + e.getMessage() + "]", e);
					abaContato = new AbaContato();
					abaContato.setIdPessoa(form.getIdPessoa());
					abaContato.setInReload(form.getInReload());
					abaContato.setDsOldEmail(form.getDsOldEmail());
					abaContato.setInMsgRetorno("true");
					abaContato.setMsgAlerta("Houve um problema na alteração do Contato na base do Legado. Alteração não realizada.");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(ConstantesCRM.SUCCESS);
				}
			}
		} catch (Exception e) {
			logLupaCliente.error("DetalheClienteController:alterarEmailLegado(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaContato.jsp"
	 * @jpf:forward name="reload" path="loadContato.do"
	 * @jpf:forward name="executa" path="executa.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward controlaSequencia(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			AbaContato form = (AbaContato) formParam;
			LupaClienteVO comunicacaoAlterada = LupaClienteVO.Factory.newInstance();
			comunicacaoAlterada.addNewDadosAbaLupaCliente();
			comunicacaoAlterada.addNewDadosLupaCliente();
			user = ConstantesCRM.getCurrentUser(request.getSession());
			ComunicacaoVO[] contatos = this.abaContato.getAbaContato();
			abaContato.setIdPessoa(form.idPessoa);
			logLupaCliente.debug("DetalheClienteController:controlaSequencia(" + user + ") >> INICIALIZADO");
			String resposta = ConstantesCRM.SVAZIO;
			String idComunicacaoSelecionada = form.idComunicacao;
			int idArray = Integer.parseInt(form.idArray);
			String direcao = request.getParameter("direcao").toString();

			if (direcao.equals("up")) {

				if (idArray == 0) {
					abaContato.setInMsgRetorno("alteracaoSequencia");
					abaContato.setMsgAlerta("Este contato já é o primeiro da lista");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("executa");
				} else {

					for (int i = 0; i < contatos.length; i++) {
						comunicacaoAlterada.getDadosAbaLupaCliente().addNewComunicacaoVO();
						comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(i).setIdComunicacao(contatos[i].getIdComunicacao());
						comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(i).setDsContato(contatos[i].getDsContato().trim());
						comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(i).setNrSequencia(Integer.toString(i + 1));
					}
					comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(idArray).setNrSequencia(comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(idArray - 1).getNrSequencia());
					comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(idArray - 1).setNrSequencia(Integer.toString(Integer.parseInt(comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(idArray - 1).getNrSequencia()) + 1));
				}

			} else if (direcao.equals("down")) {

				if (idArray == contatos.length - 1) {
					abaContato.setInMsgRetorno("alteracaoSequencia");
					abaContato.setMsgAlerta("Este contato já é o último da lista");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("executa");
				} else {

					for (int i = 0; i < contatos.length; i++) {
						comunicacaoAlterada.getDadosAbaLupaCliente().addNewComunicacaoVO();
						comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(i).setIdComunicacao(contatos[i].getIdComunicacao());
						comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(i).setDsContato(contatos[i].getDsContato());
						comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(i).setNrSequencia(Integer.toString(i + 1));
					}
					comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(idArray).setNrSequencia(comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(idArray + 1).getNrSequencia());
					comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(idArray + 1).setNrSequencia(Integer.toString(Integer.parseInt(comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(idArray).getNrSequencia()) - 1));
				}
			}

			resposta = lupaClienteFacadeControl.setSalvarAlterarComunicacao(user, comunicacaoAlterada, form.getIdPessoa());

			form.setInReload(ConstantesCRM.STRUE);
			abaContato = new AbaContato();
			abaContato.setIdPessoa(form.getIdPessoa());
			abaContato.setInReload(form.getInReload());
			request.setAttribute("reload", "ok");
			abaContato.setInMsgRetorno("false");
			logLupaCliente.debug("DetalheClienteController:controlaSequencia(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("reload");

		} catch (Exception e) {
			logLupaCliente.error("DetalheClienteController:controlaSequencia(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * Obtém as informações consultor relacionamento
	 *
	 * @return void
	 * @param GestorGerenteContaForm
	 */
	private void setConsultorRelacionamento(GestorGerenteContaForm gestorForm, HttpServletRequest request) {

		String headLog = "LupaCliente::setConsultorRelacionamento::";
		logLupaCliente.debug(headLog + "Início ");

		try {

			logLupaCliente.debug(headLog + "Início busca pelo Consultor Relacionamento ");
			ConsultorRelacionamentoVO pesquisarConsultor = ConsultorRelacionamentoVO.Factory.newInstance();

			if (pesquisarConsultor.isSetConsultorVO()) {
				pesquisarConsultor.unsetConsultorVO();
			}
			if (pesquisarConsultor.isSetClienteVO()) {
				pesquisarConsultor.unsetClienteVO();
			}

			String nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
			TelaInicialVO telaInicial = lupaClienteFacadeControl.getTelaInicial(user, nrLinha);
			String nrCNPJ = telaInicial.getTIDocumento().getNrDocumento();
			logLupaCliente.debug(headLog + "nrCNPJ:: " + nrCNPJ);
			user = ConstantesCRM.getCurrentUser(request.getSession());

			pesquisarConsultor.setTpOperacao("pesquisarConsultorVOPorIDCliente");
			pesquisarConsultor.addNewClienteVO().setNrCNPJ(nrCNPJ);
			pesquisarConsultor = associarGestorFacade.getConsultorRelacionamentoVO(user, pesquisarConsultor);
			if (pesquisarConsultor != null) {
				if ("ConsultorVO".equals(pesquisarConsultor.getListaArray(0).getNmSelect().toString())) {
					Selecionado consultoresSelecionados = pesquisarConsultor.getListaArray(0).getSelecionado();
					for (int i = 0; i < consultoresSelecionados.getIDValorRelacionamentoVOArray().length; i++) {
						IDValorRelacionamentoVO idValorRelacionamentoVO = consultoresSelecionados.getIDValorRelacionamentoVOArray(i);
						logLupaCliente.debug(headLog + "idValorRelacionamentoVO.getIdRelacionamento():: " + idValorRelacionamentoVO.getIdRelacionamento());
						if ("R".equals(idValorRelacionamentoVO.getIdRelacionamento())) {
							gestorForm.setConsultoRelacionamento1(idValorRelacionamentoVO.getValor());
						}
						if ("R2".equals(idValorRelacionamentoVO.getIdRelacionamento())) {
							gestorForm.setConsultoRelacionamento2(idValorRelacionamentoVO.getValor());
						}
					}
				}
			} else {
				logLupaCliente.warn(headLog + "Erro ao buscar consultor Relacionamento:: " + pesquisarConsultor);
			}
		} catch (Exception e) {
			logLupaCliente.error(headLog + " Erro ao buscar consultor Relacionamento Exception:: " + e.getMessage());
		}

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="detalheProcessoGestorGerente" path="detalheGestorGerenteConta.jsp"
	 * @jpf:forward name="incluirAlterarGestorGerente" path="incluirAlterarGestorGerente.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward getGestorGerenteConta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			AbaContato form = (AbaContato) formParam;
			String headLog = "LupaCliente::getGestorGerenteConta::";
			String cdErro = ConstantesCRM.SVAZIO;
			String msErro = ConstantesCRM.SVAZIO;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			String forward = "detalheProcessoGestorGerente";
			String nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
			TelaInicialVO telaInicial = lupaClienteFacadeControl.getTelaInicial(user, nrLinha);

			logLupaCliente.debug(headLog + "Operacao " + request.getParameter("operacao"));
			if ("getInclusaoAlteracaoForm".equals(request.getParameter("operacao"))) {
				forward = "incluirAlterarGestorGerente";
				String inGestorGerente = request.getParameter("inGestorGerente");
				logLupaCliente.debug(headLog + "inGestorGerente " + inGestorGerente);
				if ("gestor".equalsIgnoreCase(inGestorGerente)) {
					this.gestorGerenteContaForm = carregarGestorGerenteContaForm(getAbaContato().getGestorContas());
					getGestorGerenteContaForm().setGestor(true);
					getGestorGerenteContaForm().setGerente(false);
				} else {
					this.gestorGerenteContaForm = carregarGestorGerenteContaForm(getAbaContato().getGerenteContas());
					getGestorGerenteContaForm().setGestor(false);
					getGestorGerenteContaForm().setGerente(true);
				}

				String idGestorGerente = request.getParameter("idGestorGerente");
				logLupaCliente.debug(headLog + "idGestorGerente " + idGestorGerente);
				if (request.getParameter("idGestorGerente") != null && !ConstantesCRM.SZERO.equals(request.getParameter("idGestorGerente"))) {
					getGestorGerenteContaForm().setIdGestorGerente(Long.parseLong(request.getParameter("idGestorGerente")));
				}

				/* Verifica se existe endereço associado ao gestor */
				boolean hasEnderecoGestor = false;
				boolean isComplementoGestorCorreto = true;
				if (getGestorGerenteContaForm().isGestor()) {
					if (telaInicial != null && telaInicial.getEnderecoBaseVO() != null) {
						logLupaCliente.debug(headLog + "Dados de Endereço");
						EnderecoBaseVO end = telaInicial.getEnderecoBaseVO();
						if (end.getDsEndereco() != null && !ConstantesCRM.SVAZIO.equals(end.getDsEndereco()) && end.getDsEndereco().trim().length() > 0) {
							hasEnderecoGestor = true;
						}
						if (end.getDsComplemento() != null && !ConstantesCRM.SVAZIO.equals(end.getDsComplemento()) && end.getDsComplemento().trim().length() > 0 && end.getDsComplemento().length() > 50) {
							isComplementoGestorCorreto = false;
						}

						logLupaCliente.debug(headLog + "hasEnderecoGestor " + hasEnderecoGestor + " isComplementoGestorCorreto " + isComplementoGestorCorreto);
						if (!hasEnderecoGestor) {
							getGestorGerenteContaForm().setMensagemEndereco("true");
						} else {
							getGestorGerenteContaForm().setMensagemEndereco("false");
							/* Verificar se o Complemento atende o limite de 50 caracteres */
							if (!isComplementoGestorCorreto) {
								getGestorGerenteContaForm().setMensagemComplemento("true");
							} else {
								getGestorGerenteContaForm().setMensagemComplemento("false");
							}

						}
					}
				}

			}
			// Carregamento inicial
			else {
				logLupaCliente.debug(headLog + "Busca Gestores");
				getAbaContato().setConsultorRelacionamento1(new PessoaConsultor());
				getAbaContato().setConsultorRelacionamento2(new PessoaConsultor());

				try {
					// Busca Consultor de Relacionamento
					logLupaCliente.debug(headLog + "Consultor Relacionamento");
					String nrCNPJ = telaInicial.getTIDocumento().getNrDocumento();
					logLupaCliente.debug(headLog + "nrCNPJ = " + nrCNPJ);

					PessoaConsultor[] consultorRelacionamento = associarGestorFacade.getConsultorRelacionamento(nrCNPJ);
					if (consultorRelacionamento != null) {
						logLupaCliente.debug(headLog + "consultorRelacionamento size " + consultorRelacionamento.length);
						for (int i = 0; i < consultorRelacionamento.length; i++) {
							PessoaConsultor consultor = consultorRelacionamento[i];
							if ("R".equalsIgnoreCase(consultor.getSgTipoRelacionamento())) {
								getAbaContato().setConsultorRelacionamento1(consultor);
							} else if ("R2".equalsIgnoreCase(consultor.getSgTipoRelacionamento())) {
								getAbaContato().setConsultorRelacionamento2(consultor);
							}
						}
					}
				} catch (Exception e) {
					logLupaCliente.error(headLog + "Exception::" + e.getMessage());
					logLupaCliente.error(headLog + "Exception::", e);
				}

				try {
					// Busca Gerente de Contas, Gestor Contas e Gestor Master
					logLupaCliente.debug(headLog + "Gestores");
					String nrCNPJ = telaInicial.getTIDocumento().getNrDocumento();
					logLupaCliente.debug(headLog + "nrCNPJ = " + nrCNPJ + " idConta " + idContaSistemaOrigem);

					br.com.vivo.fo.ctrls.cliente.associarGestor.db.Gestor[] gestores = associarGestorFacade.getGestoresConta(nrCNPJ, idContaSistemaOrigem);

					if (gestores != null) {
						logLupaCliente.debug(headLog + "Gestores size " + gestores.length);
						for (int i = 0; i < gestores.length; i++) {
							br.com.vivo.fo.ctrls.cliente.associarGestor.db.Gestor gestor = gestores[i];
							logLupaCliente.debug(headLog + "SgTipoRelacionamento " + gestor.getSgTipoRelacionamento());
							if (GERENTE_DE_CONTA.equalsIgnoreCase(gestor.getSgTipoRelacionamento())) {
								getAbaContato().setGerenteContas(gestor);
							} else if (GESTOR_CONTA.equalsIgnoreCase(gestor.getSgTipoRelacionamento())) {
								getAbaContato().setGestorContas(gestor);
							} else if (GESTOR_MASTER.equalsIgnoreCase(gestor.getSgTipoRelacionamento())) {
								getAbaContato().setGestorMaster(gestor);
							}
						}
					}
				} catch (Exception e) {
					logLupaCliente.error(headLog + "Exception::" + e.getMessage());
					logLupaCliente.error(headLog + "Exception::", e);
				}

			}

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(forward);
		} catch (Exception e) {
			logLupaCliente.error("DetalheClienteController:getGestorGerenteConta(" + user + ") - [" + e.getMessage() + "]", e);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 */
	public ActionForward setGestorGerenteConta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		GestorGerenteContaForm form = (GestorGerenteContaForm) formParam;
		String headLog = "LupaCliente::setGestorGerenteConta::";
		String cdErro = ConstantesCRM.SVAZIO;
		String msErro = ConstantesCRM.SVAZIO;
		boolean isComplementoCorreto = true;
		boolean isEndedecoCorreto = true;

		user = ConstantesCRM.getCurrentUser(request.getSession());
		logLupaCliente.debug(headLog + "Gravando dados de Gestor/Gerente");
		String nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
		logLupaCliente.debug(headLog + "nrLinha = " + nrLinha);
		ParametrosVO parametrosVO = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS));
		logLupaCliente.debug(headLog + "Buscando dados da Linha pelo CarregaTI");
		TelaInicialVO telaInicial = lupaClienteFacadeControl.getTelaInicial(user, nrLinha);
		logLupaCliente.debug(headLog + "CarregaTI - retorno OK");
		GetParametro parametro = GetParametro.getInstace();

		String nrConta = parametrosVO.getNrConta();
		String nrCNPJ = telaInicial.getTIDocumento().getNrDocumento();

		ParametrosManterContatoCorporativo manterContatoCorporativo = new ParametrosManterContatoCorporativo();
		Gestor gestorCorporativo = new Gestor();

		manterContatoCorporativo.setGestor(gestorCorporativo);

		manterContatoCorporativo.getGestor().setPrimeiroNome(createJAXBElement("primeiroNome", form.getPrimeiroNome()));
		logLupaCliente.info(headLog + "PrimeiroNome = [" + form.getPrimeiroNome()+ "]");
		manterContatoCorporativo.getGestor().setSobrenome(createJAXBElement("sobrenome", form.getNomeMeio() + " " + form.getSobrenome()));
		logLupaCliente.info(headLog + "Sobrenome = [" + form.getNomeMeio() + " " + form.getSobrenome()+ "]");

		String nome = form.getPrimeiroNome() + " " + form.getNomeMeio() + (!ConstantesCRM.SVAZIO.equals(form.getNomeMeio()) ? " " : ConstantesCRM.SVAZIO) + form.getSobrenome();
		manterContatoCorporativo.getGestor().setNomeCompleto(createJAXBElement("nomeCompleto",nome));
		logLupaCliente.info(headLog + "NomeCompleto = [" + nome+ "]");

		/* DATA NASCIMENTO */
		if (form.getDataNascimento() != null && !ConstantesCRM.SVAZIO.equals(form.getDataNascimento())) {
			try {
					Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(form.getDataNascimento());
			        String strData =  new SimpleDateFormat("yyyy-MM-dd").format(dataNascimento);
			        XMLGregorianCalendar xmlgcData = DatatypeFactory.newInstance().newXMLGregorianCalendar(strData);
					JAXBElement<XMLGregorianCalendar> jaxbXmlDataNascimento = new JAXBElement<XMLGregorianCalendar>(new QName(targetNameSpace, "dataNascimento"),XMLGregorianCalendar.class, xmlgcData);
					manterContatoCorporativo.getGestor().setDataNascimento(jaxbXmlDataNascimento);
			} catch (Exception e) {
				logLupaCliente.warn(headLog + "Houve um problema na formatção da data nascimento = ["+  nrConta + "]" + form.getDataNascimento());
			}
			logLupaCliente.info(headLog + "DataNascimento = [" + form.getDataNascimento()+ "]");
		} else {
			msErro = "A Data de nascimento não foi preenchida.";
		}

		manterContatoCorporativo.getGestor().setNomeGestor(createJAXBElement("nomeGestor",form.getPrimeiroNome()));
		logLupaCliente.info(headLog + "NomeGestor = [" + form.getPrimeiroNome()+ "]");

		if (telaInicial != null && telaInicial.getEnderecoBaseVO() != null) {
			logLupaCliente.info(headLog + "Dados de Endereço");
			EnderecoBaseVO end = telaInicial.getEnderecoBaseVO();

			com.indracompany.ws.contatoservice.Endereco endereco = new com.indracompany.ws.contatoservice.Endereco();
			endereco.setLogradouro(createJAXBElement("logradouro", end.getDsEndereco()));
			logLupaCliente.info(headLog + "Logradouro = [" + end.getDsEndereco() + "]");

			endereco.setNumero(createJAXBElement("numero", end.getNrEndereco()));
			logLupaCliente.info(headLog + "Numero = [" + end.getNrEndereco() + "]");

			endereco.setComplemento(createJAXBElement("complemento", end.getDsComplemento()));
			if (end.getDsComplemento() != null && end.getDsComplemento().length() > 50) {
				isComplementoCorreto = false;
			}
			logLupaCliente.info(headLog + "Complemento = [" + end.getDsComplemento() + "]");

			endereco.setBairro(createJAXBElement("bairro",end.getDsBairro()));
			logLupaCliente.info(headLog + "Bairro = [" + end.getDsBairro() + "]");

			endereco.setCidade(createJAXBElement("cidade", end.getDsCidade()));
			logLupaCliente.info(headLog + "Cidade = [" + end.getDsCidade() + "]");

		    endereco.setCep(createJAXBElement("cep", end.getNrCEP()));
			logLupaCliente.info(headLog + "NrCep = [" + end.getNrCEP() + "]");

			com.indracompany.ws.contatoservice.Uf uf = new com.indracompany.ws.contatoservice.Uf();
			uf.setSigla(end.getUFVO().getSgUF());
			logLupaCliente.debug(headLog + "Uf = [" + end.getUFVO().getSgUF() + "]");
		    endereco.setUf(uf);

			com.indracompany.ws.contatoservice.Enderecos enderecos = new com.indracompany.ws.contatoservice.Enderecos();
			enderecos.getEndereco().add(endereco);
			JAXBElement<com.indracompany.ws.contatoservice.Enderecos> xmlEnderecos = new JAXBElement<com.indracompany.ws.contatoservice.Enderecos>(new QName(targetNameSpace, "enderecos"),com.indracompany.ws.contatoservice.Enderecos.class, enderecos);
			manterContatoCorporativo.getGestor().setEnderecos(xmlEnderecos);

		} else {
			isEndedecoCorreto = false;
			logLupaCliente.info(headLog + "isEndedecoCorreto = [" + isEndedecoCorreto + "]");
		}


		com.indracompany.ws.contatoservice.Contato contatoCel =  new com.indracompany.ws.contatoservice.Contato();
        contatoCel.setTextoContato(form.getNrTelefone().replaceAll("[^0-9]", ConstantesCRM.SVAZIO));
        logLupaCliente.info(headLog + "[0]TextoContato Cel = [" + form.getNrTelefone().replaceAll("[^0-9]", ConstantesCRM.SVAZIO)+ "]");

        com.indracompany.ws.contatoservice.Contato contatoFixo =  new com.indracompany.ws.contatoservice.Contato();
        contatoFixo.setTextoContato(form.getNrTelefoneContato().replaceAll("[^0-9]", ConstantesCRM.SVAZIO));
        logLupaCliente.info(headLog + "[1]TextoContato Fixo = [" + form.getNrTelefoneContato().replaceAll("[^0-9]", ConstantesCRM.SVAZIO)+ "]");

        com.indracompany.ws.contatoservice.Contatos contatos = new com.indracompany.ws.contatoservice.Contatos();
        contatos.getContato().add(contatoCel);
        contatos.getContato().add(contatoFixo);

        JAXBElement<Contatos> xmlContatos = new JAXBElement<Contatos>(new QName(targetNameSpace, "contatos"),Contatos.class, contatos);
        manterContatoCorporativo.getGestor().setContatos(xmlContatos);

        com.indracompany.ws.contatoservice.Documento numeroCpf = new com.indracompany.ws.contatoservice.Documento();
        numeroCpf.setNumeroDocumento(form.getNrCPF().replaceAll("[^0-9]", ConstantesCRM.SVAZIO));
		logLupaCliente.info(headLog + "[0]NumeroDocumento = [" + form.getNrCPF().replaceAll("[^0-9]", ConstantesCRM.SVAZIO)+ "]");

		com.indracompany.ws.contatoservice.Documentos documentos = new com.indracompany.ws.contatoservice.Documentos();
        documentos.getDocumento().add(numeroCpf);
        JAXBElement<Documentos> xmlDocumentos = new JAXBElement<Documentos>(new QName(targetNameSpace, "documentos"),com.indracompany.ws.contatoservice.Documentos.class, documentos);
        manterContatoCorporativo.getGestor().setDocumentos(xmlDocumentos);

        com.indracompany.ws.contatoservice.Conta conta = new com.indracompany.ws.contatoservice.Conta();
        conta.setCodigoContaSistemOrigem(nrConta);
        logLupaCliente.info(headLog + "CodigoContaSistemOrigem = [" + nrConta+ "]");
        com.indracompany.ws.contatoservice.Contas contas = new com.indracompany.ws.contatoservice.Contas();
        contas.getConta().add(conta);
        JAXBElement<com.indracompany.ws.contatoservice.Contas> xmlContas = new JAXBElement<com.indracompany.ws.contatoservice.Contas>(new QName(targetNameSpace, "contas"),com.indracompany.ws.contatoservice.Contas.class, contas);
        manterContatoCorporativo.getGestor().setContas(xmlContas);

        com.indracompany.ws.contatoservice.Usuario usuario = new com.indracompany.ws.contatoservice.Usuario();
        JAXBElement<BigInteger> xmlBigInteger = new JAXBElement<BigInteger>(new QName(targetNameSpace, "codigo"),BigInteger.class, new BigInteger(user));
        xmlBigInteger.setValue(new BigInteger(user));
		logLupaCliente.info(headLog + "UsuarioManutencao.Codigo = [" + user+ "]");
        usuario.setCodigo(xmlBigInteger);
		manterContatoCorporativo.getGestor().setUsuarioManutencao(usuario);


		manterContatoCorporativo.getGestor().setTipoRelacionamento(new com.indracompany.ws.contatoservice.TipoRelacionamento());
		logLupaCliente.info(headLog + "isGestor = [" + form.isGestor()+ "]");
		if (form.isGestor()) {
			manterContatoCorporativo.getGestor().getTipoRelacionamento().setSigla("GE");
			logLupaCliente.info(headLog + "tipoRelacionamento.sigla = " + "GE -  Gestor Contas");
		} else {
			manterContatoCorporativo.getGestor().getTipoRelacionamento().setSigla("GC");
			logLupaCliente.info(headLog + "tipoRelacionamento.sigla = " + "GC -  Gerente Contas");
		}


		manterContatoCorporativo.setNumeroDocumentoCliente(nrCNPJ);
		logLupaCliente.info(headLog + "numeroDocumentoCliente = [" + nrCNPJ+ "]");

		manterContatoCorporativo.setNmEmail(form.getDsEmail().trim());
		logLupaCliente.info(headLog + "NmEmail = [" + form.getDsEmail() + "]");

		boolean retorno = true;
		try {
			logLupaCliente.debug(headLog + "Chamando manterGestorContaEmpresa ");

			ParametroVO apoioParam = parametro.getParametroVO(user, "URL_CONTATO_CORPORATIVO");
			String endPoint = apoioParam.getDsValorParametro();

			ContatoCorporativoBindingQSService service = (ContatoCorporativoBindingQSService) new ContatoCorporativoBindingQSService();
			HttpTransportPipe.dump = true;
			com.indracompany.ws.contatoservice.HeaderHandlerResolver handlerResolver = new com.indracompany.ws.contatoservice.HeaderHandlerResolver();
			service.setHandlerResolver(handlerResolver);
			ContatoCorporativoPortType proxy = service.getContatoCorporativoBindingQSPort();
			((BindingProvider)proxy).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);



			String strUsuario = ConstantesCRM.SVAZIO;
            String strSenha = ConstantesCRM.SVAZIO;
            try {
            	strUsuario = br.com.vivo.fo.commons.utils.GetParametro.getInstace().getParametroVO(user, ConstantesCRM.ALSB3SOA_SECURITY_USER).getDsValorParametro();
            	strSenha = br.com.vivo.fo.commons.utils.GetParametro.getInstace().getParametroVO(user, ConstantesCRM.ALSB3SOA_SECURITY_PASSWORD).getDsValorParametro();
            } catch (Exception e) {
            	logLupaCliente.error(headLog +  "Erro (" + user + ")Erro ao buscar usuário e senha :", e);
            }


	        proxy.manterContatoCorporativo(manterContatoCorporativo);

		} catch (com.indracompany.ws.contatoservice.ErroInfoFault errorInfo) {
				logLupaCliente.error(headLog + "errorInfo::" + errorInfo);
				if ("999".equals(errorInfo.getFaultInfo().getCodigo())) {
					cdErro = "999";
					msErro = "Ocorreu um erro inesperado na operação manterContatoCorporativo do serviço ContatoCorporativo.\nCaso o problema persista, entre em contato com a Central de Serviços.";
					retorno = false;
				} else if ("142001".equals(errorInfo.getFaultInfo().getCodigo()) && form.isGestor() && !isEndedecoCorreto) {
					cdErro = "142001";
					msErro = "Esta conta não possui um endereço associado no Vivonet.\nPara a inclusão / alteração de gestores é necessário que exista um endereço associado a conta.\nPor favor, solicite a correção, via chamado, para o Atlys.";
					retorno = false;
				} else if ("142001".equals(errorInfo.getFaultInfo().getCodigo()) && !isComplementoCorreto) {
					cdErro = "142001";
					msErro = "O campo complemento do endereço desta conta ultrapassou o limite de 50 caracteres permitido para o cadastro do gestor.\nPor favor, solicite a correção, via chamado, para o Atlys.";
					retorno = false;
				} else {
					cdErro = errorInfo.getFaultInfo().getCodigo();
					msErro = errorInfo.getFaultInfo().getDescricao();
					retorno = false;
				}

		} catch(AxisFault axisFaultException) {
				System.out.println(headLog + "AxisFault [" + axisFaultException.getFaultString() + "]");
				cdErro = "99";
				msErro = "Ocorreu um erro inesperado na operação manterContatoCorporativo do serviço ContatoCorporativo.\nCaso o problema persista, entre em contato com a Central de Serviços.";
				retorno = false;
		} catch (Exception ex) {
				cdErro = "-1";
				msErro = "Serviço de Cadastro Gestor indisponível no momento.";
				retorno = false;
		}

		String xmlOUT = "<msg><retorno>" + retorno + "</retorno><cdErro>" + cdErro + "</cdErro><msErro>" + msErro + "</msErro></msg>";
		response.setContentType("text/plain;charset=ISO-8859-1");
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
		bufferedOutputStream.write(xmlOUT.getBytes(ConstantesCRM.SISO));
		bufferedOutputStream.flush();
		bufferedOutputStream.close();
		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="detalheClienteEspecial" path="detalheClienteEspecial.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward getClienteEspecial(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			AbaContato form = (AbaContato) formParam;
			String headLog = "LupaCliente::getClienteEspecial::";
			String cdErro = ConstantesCRM.SVAZIO;
			String msErro = ConstantesCRM.SVAZIO;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			String forward = "detalheClienteEspecial";
			String nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
			TelaInicialVO telaInicial = lupaClienteFacadeControl.getTelaInicial(user, nrLinha);

			try {
				// Busca Gerente de Contas, Gestor Contas e Gestor Master
				logLupaCliente.debug(headLog + "Gestores");
				String nrCNPJ = telaInicial.getTIDocumento().getNrDocumento();
				logLupaCliente.debug(headLog + "nrCNPJ = " + nrCNPJ + " idConta " + idContaSistemaOrigem);

				ClienteEspecial[] listaClienteEspecial = associarGestorFacade.getClienteEspecial(nrCNPJ);
				if (listaClienteEspecial != null) {
					logLupaCliente.debug(headLog + "listaClienteEspecial size " + listaClienteEspecial.length);
					getAbaContato().setListaClienteEspecial(listaClienteEspecial);
				}
			} catch (Exception e) {
				logLupaCliente.error(headLog + "Exception::" + e.getMessage());
				logLupaCliente.error(headLog + "Exception::", e);
			}

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(forward);

		} catch (Exception e) {
			logLupaCliente.error("DetalheClienteController:getClienteEspecial(" + user + ") - [" + e.getMessage() + "]", e);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @return GestorGerenteContaForm Metodo responsável por traduzir o obejto GestorGerente em GestorGerenteContaForm
	 */
	public GestorGerenteContaForm carregarGestorGerenteContaForm(br.com.vivo.fo.ctrls.cliente.associarGestor.db.Gestor gestorGerente) {

		GestorGerenteContaForm gestorGerenteContaForm = new GestorGerenteContaForm();
		if (gestorGerente != null) {

			gestorGerenteContaForm.setPrimeiroNome(gestorGerente.getNmGestor());
			String sobrenome = gestorGerente.getNmSobreNomeGestor();
			String nomeMeio = null;
			if (nomeMeio == null || nomeMeio.length() == 0) {
				if (sobrenome != null && sobrenome.length() > 0 && sobrenome.indexOf(" ") > 0) {
					nomeMeio = gestorGerente.getNmSobreNomeGestor().substring(0, gestorGerente.getNmSobreNomeGestor().indexOf(" "));
					sobrenome = gestorGerente.getNmSobreNomeGestor().substring(gestorGerente.getNmSobreNomeGestor().indexOf(" ") + 1);
				}
			}
			gestorGerenteContaForm.setNomeMeio(nomeMeio);
			gestorGerenteContaForm.setSobrenome(sobrenome);
			gestorGerenteContaForm.setNrCPF(gestorGerente.getNrDocumentoFormatado());
			gestorGerenteContaForm.setDsEmail(gestorGerente.getEmail());
			gestorGerenteContaForm.setDataNascimento(gestorGerente.getDtNascimentoFormatado());
			gestorGerenteContaForm.setNrTelefone(gestorGerente.getNrTelefoneCelVivoFormatado());
			gestorGerenteContaForm.setNrTelefoneContato(gestorGerente.getNrTelefoneFixoFormatado());
			gestorGerenteContaForm.setIdContact(gestorGerente.getIdPessoaSistemaOrigem());
		}
		return gestorGerenteContaForm;
	}

	private JAXBElement<String> createJAXBElement(String campo, String valor) {
		JAXBElement<String> xmlString = new JAXBElement<String>(new QName(targetNameSpace, campo), String.class, campo);
		xmlString.setValue(valor);

		return xmlString;
	}

	public static class AbaContato extends ActionForm {

		private static final long serialVersionUID = 4213621661531873976L;

		private String msgAlerta;
		private boolean inEmail;
		private String dsOldEmail;
		private LinhasPorIdVO linhas;
		private String inReload = ConstantesCRM.SVAZIO;

		private GestorGerenteContaForm[] gestorGerenteContaForm;

		private br.com.vivo.fo.ctrls.cliente.associarGestor.db.PessoaConsultor consultorRelacionamento1;
		private br.com.vivo.fo.ctrls.cliente.associarGestor.db.PessoaConsultor consultorRelacionamento2;
		private br.com.vivo.fo.ctrls.cliente.associarGestor.db.Gestor gestorContas;
		private br.com.vivo.fo.ctrls.cliente.associarGestor.db.Gestor gerenteContas;
		private br.com.vivo.fo.ctrls.cliente.associarGestor.db.Gestor gestorMaster;
		private br.com.vivo.fo.ctrls.cliente.associarGestor.db.ClienteEspecial[] listaClienteEspecial;

		public GestorGerenteContaForm[] getGestorGerenteContaForm() {
			if (this.gestorGerenteContaForm == null) {
				this.gestorGerenteContaForm = new GestorGerenteContaForm[2];
			}
			return this.gestorGerenteContaForm;
		}

		public void setGestorGerenteContaForm(GestorGerenteContaForm[] arg) {
			this.gestorGerenteContaForm = arg;
		}

		public br.com.vivo.fo.ctrls.cliente.associarGestor.db.PessoaConsultor getConsultorRelacionamento1() {
			return this.consultorRelacionamento1;
		}

		public void setConsultorRelacionamento1(br.com.vivo.fo.ctrls.cliente.associarGestor.db.PessoaConsultor consultorRelacionamento) {
			this.consultorRelacionamento1 = consultorRelacionamento;
		}

		public br.com.vivo.fo.ctrls.cliente.associarGestor.db.PessoaConsultor getConsultorRelacionamento2() {
			return this.consultorRelacionamento2;
		}

		public void setConsultorRelacionamento2(br.com.vivo.fo.ctrls.cliente.associarGestor.db.PessoaConsultor consultorRelacionamento) {
			this.consultorRelacionamento2 = consultorRelacionamento;
		}

		public br.com.vivo.fo.ctrls.cliente.associarGestor.db.Gestor getGestorContas() {
			return this.gestorContas;
		}

		public void setGestorContas(br.com.vivo.fo.ctrls.cliente.associarGestor.db.Gestor gestorContas) {
			this.gestorContas = gestorContas;
		}

		public br.com.vivo.fo.ctrls.cliente.associarGestor.db.Gestor getGerenteContas() {
			return this.gerenteContas;
		}

		public void setGerenteContas(br.com.vivo.fo.ctrls.cliente.associarGestor.db.Gestor gerenteContas) {
			this.gerenteContas = gerenteContas;
		}

		public br.com.vivo.fo.ctrls.cliente.associarGestor.db.Gestor getGestorMaster() {
			return this.gestorMaster;
		}

		public void setGestorMaster(br.com.vivo.fo.ctrls.cliente.associarGestor.db.Gestor gestorMaster) {
			this.gestorMaster = gestorMaster;
		}

		public boolean isClienteEspecial() {
			return this.listaClienteEspecial != null && listaClienteEspecial.length > 0;
		}

		public br.com.vivo.fo.ctrls.cliente.associarGestor.db.ClienteEspecial[] getListaClienteEspecial() {
			if (this.listaClienteEspecial == null) {
				this.listaClienteEspecial = new br.com.vivo.fo.ctrls.cliente.associarGestor.db.ClienteEspecial[0];
			}
			return this.listaClienteEspecial;
		}

		public void setListaClienteEspecial(br.com.vivo.fo.ctrls.cliente.associarGestor.db.ClienteEspecial[] listaClienteEspecial) {
			this.listaClienteEspecial = listaClienteEspecial;
		}

		private String inMsgRetorno = ConstantesCRM.SVAZIO;
		private ListaTipoComunicacaoVO listaTipos = ListaTipoComunicacaoVO.Factory.newInstance();
		private String idTipoSelecionado = ConstantesCRM.SVAZIO;
		private ComunicacaoVO contato;
		private String idComunicacao = ConstantesCRM.SVAZIO;
		private String idPessoa = ConstantesCRM.SVAZIO;
		private String idArray = ConstantesCRM.SVAZIO;
		private String acao = ConstantesCRM.SVAZIO;
		private ComunicacaoVO[] abaContato;

		public AbaContato() {
			contato = ComunicacaoVO.Factory.newInstance();
			contato.addNewTipoComunicacaoVO();
		}

		public void setAbaContato(ComunicacaoVO[] abaContato) {
			this.abaContato = abaContato;
		}

		public ComunicacaoVO[] getAbaContato() {
			return this.abaContato;
		}

		public void setAcao(String acao) {
			this.acao = acao;
		}

		public String getAcao() {
			return this.acao;
		}

		public void setIdArray(String idArray) {
			this.idArray = idArray;
		}

		public String getIdArray() {
			return this.idArray;
		}

		public void setIdPessoa(String idPessoa) {
			this.idPessoa = idPessoa;
		}

		public String getIdPessoa() {
			return this.idPessoa;
		}

		public void setIdComunicacao(String idComunicacao) {
			this.idComunicacao = idComunicacao;
		}

		public String getIdComunicacao() {
			return this.idComunicacao;
		}

		public void setContato(ComunicacaoVO contato) {
			this.contato = contato;
		}

		public ComunicacaoVO getContato() {
			return this.contato;
		}

		public void setIdTipoSelecionado(String idTipoSelecionado) {
			this.idTipoSelecionado = idTipoSelecionado;
		}

		public String getIdTipoSelecionado() {
			return this.idTipoSelecionado;
		}

		public void setListaTipos(ListaTipoComunicacaoVO listaTipos) {
			this.listaTipos = listaTipos;
		}

		public ListaTipoComunicacaoVO getListaTipos() {
			return this.listaTipos;
		}

		public void setInMsgRetorno(String inMsgRetorno) {
			this.inMsgRetorno = inMsgRetorno;
		}

		public String getInMsgRetorno() {
			return this.inMsgRetorno;
		}

		public void setInReload(String inReload) {
			this.inReload = inReload;
		}

		public String getInReload() {
			return this.inReload;
		}

		public void setLinhas(LinhasPorIdVO linhas) {
			this.linhas = linhas;
		}

		public LinhasPorIdVO getLinhas() {
			return this.linhas;
		}

		public void setDsOldEmail(String dsOldEmail) {
			this.dsOldEmail = dsOldEmail;
		}

		public String getDsOldEmail() {
			return this.dsOldEmail;
		}

		public void setInEmail(boolean inEmail) {
			this.inEmail = inEmail;
		}

		public boolean isInEmail() {
			return this.inEmail;
		}

		public void setMsgAlerta(String msgAlerta) {
			this.msgAlerta = msgAlerta;
		}

		public String getMsgAlerta() {
			return this.msgAlerta;
		}
	}

	public static class AbaEndereco extends ActionForm {

		private static final long serialVersionUID = 345494226012063519L;

		private boolean isAddrPresent;
		private String inReload = ConstantesCRM.SVAZIO;
		private PesquisaEnderecoVO pesquisaEndereco;
		private String inMsgRetorno = ConstantesCRM.SVAZIO;
		private String dsMsgRetorno = ConstantesCRM.SVAZIO;
		private String acao = ConstantesCRM.SVAZIO;
		private String idEndereco = ConstantesCRM.SVAZIO;
		private String idArray = ConstantesCRM.SVAZIO;
		private EnderecoVO[] abaEndereco;
		private EnderecoVO endereco;
		private String idTipoSelecionado = ConstantesCRM.SVAZIO;
		private String idUFSelecionado = ConstantesCRM.SVAZIO;
		private String idPaisSelecionado = ConstantesCRM.SVAZIO;
		private String idPessoa = ConstantesCRM.SVAZIO;
		private boolean pesquisaBaseFO = false;
		private CarregarNovoEnderecoVO listas = CarregarNovoEnderecoVO.Factory.newInstance();

		public AbaEndereco() {
			endereco = EnderecoVO.Factory.newInstance();
			endereco.addNewTipoEnderecoVO();
			endereco.addNewPaisVO();
			endereco.addNewUFVO();
			pesquisaEndereco = PesquisaEnderecoVO.Factory.newInstance();
			pesquisaEndereco.addNewFiltroPesquisa();
		}

		public boolean isPesquisaBaseFO() {
			return this.pesquisaBaseFO;
		}

		public void setPesquisaBaseFO(boolean arg) {
			this.pesquisaBaseFO = arg;
		}

		public EnderecoVO getEndereco() {
			return this.endereco;
		}

		public void setEndereco(EnderecoVO endereco) {
			this.endereco = endereco;
		}

		public void setAbaEndereco(EnderecoVO[] abaEndereco) {
			this.abaEndereco = abaEndereco;
		}

		public EnderecoVO[] getAbaEndereco() {
			return this.abaEndereco;
		}

		public void setIdArray(String idArray) {
			this.idArray = idArray;
		}

		public String getIdArray() {
			return this.idArray;
		}

		public void setIdEndereco(String idEndereco) {
			this.idEndereco = idEndereco;
		}

		public String getIdEndereco() {
			return this.idEndereco;
		}

		public void setAcao(String acao) {
			this.acao = acao;
		}

		public String getAcao() {
			return this.acao;
		}

		public void setIdPessoa(String idPessoa) {
			this.idPessoa = idPessoa;
		}

		public String getIdPessoa() {
			return this.idPessoa;
		}

		public void setListas(CarregarNovoEnderecoVO listas) {
			this.listas = listas;
		}

		public CarregarNovoEnderecoVO getListas() {
			return this.listas;
		}

		public void setIdTipoSelecionado(String idTipoSelecionado) {
			this.idTipoSelecionado = idTipoSelecionado;
		}

		public String getIdTipoSelecionado() {
			return this.idTipoSelecionado;
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

		public void setInMsgRetorno(String inMsgRetorno) {
			this.inMsgRetorno = inMsgRetorno;
		}

		public String getInMsgRetorno() {
			return this.inMsgRetorno;
		}

		public void setDsMsgRetorno(String dsMsgRetorno) {
			this.dsMsgRetorno = dsMsgRetorno;
		}

		public String getDsMsgRetorno() {
			return this.dsMsgRetorno;
		}

		public void setPesquisaEndereco(PesquisaEnderecoVO pesquisaEndereco) {
			this.pesquisaEndereco = pesquisaEndereco;
		}

		public PesquisaEnderecoVO getPesquisaEndereco() {
			return this.pesquisaEndereco;
		}

		public void setInReload(String inReload) {
			this.inReload = inReload;
		}

		public String getInReload() {
			return this.inReload;
		}

		public void setIsAddrPresent(boolean isAddrPresent) {
			this.isAddrPresent = isAddrPresent;
		}

		public boolean isIsAddrPresent() {
			return this.isAddrPresent;
		}
	}

	public static class PermissoesForm extends ActionForm {

		private static final long serialVersionUID = 240120547011545368L;

		private br.com.vivo.fo.cliente.vo.MeioContatoVODocument.MeioContatoVO.MeioContato[] formMeioContatoVO;
		private String[] formMeioContatoRecusado;
		private String[] formMeioContato;
		private br.com.vivo.fo.cliente.vo.ComunicacoesRecusadasVODocument.ComunicacoesRecusadasVO.MeioContatoRecusado[] formMeioContatoRecusadoVO;
		private br.com.vivo.fo.usuario.vo.CanaisRelacionadosVODocument.CanaisRelacionadosVO.CanaisRelacionados[] formCanaisRelacionadosVO;
		private String[] formCanaisRelacionados;
		private br.com.vivo.fo.usuario.vo.CanaisExistentesVODocument.CanaisExistentesVO.CanaisExistentes[] formCanaisExistentesVO;
		private String[] formCanaisExistentes;

		public PermissoesForm() {
			formMeioContatoVO = new MeioContato[0];
			formMeioContato = new String[0];
			formMeioContatoRecusadoVO = new MeioContatoRecusado[0];
			formMeioContatoRecusado = new String[0];
			formCanaisRelacionadosVO = new CanaisRelacionados[0];
			formCanaisRelacionados = new String[0];
			formCanaisExistentesVO = new CanaisExistentes[0];
			formCanaisExistentes = new String[0];
		}

		public void setFormCanaisExistentes(String[] formCanaisExistentes) {
			this.formCanaisExistentes = formCanaisExistentes;
		}

		public String[] getFormCanaisExistentes() {
			if (this.formCanaisExistentes == null || this.formCanaisExistentes.length == 0) {
				this.formCanaisExistentes = new String[1];
			}

			return this.formCanaisExistentes;
		}

		public void setFormCanaisExistentesVO(br.com.vivo.fo.usuario.vo.CanaisExistentesVODocument.CanaisExistentesVO.CanaisExistentes[] formCanaisExistentesVO) {
			this.formCanaisExistentesVO = formCanaisExistentesVO;
		}

		public br.com.vivo.fo.usuario.vo.CanaisExistentesVODocument.CanaisExistentesVO.CanaisExistentes[] getFormCanaisExistentesVO() {
			return this.formCanaisExistentesVO;
		}

		public void setFormCanaisRelacionados(String[] formCanaisRelacionados) {
			this.formCanaisRelacionados = formCanaisRelacionados;
		}

		public String[] getFormCanaisRelacionados() {
			return this.formCanaisRelacionados;
		}

		public void setFormCanaisRelacionadosVO(br.com.vivo.fo.usuario.vo.CanaisRelacionadosVODocument.CanaisRelacionadosVO.CanaisRelacionados[] formCanaisRelacionadosVO) {
			this.formCanaisRelacionadosVO = formCanaisRelacionadosVO;
		}

		public br.com.vivo.fo.usuario.vo.CanaisRelacionadosVODocument.CanaisRelacionadosVO.CanaisRelacionados[] getFormCanaisRelacionadosVO() {
			return this.formCanaisRelacionadosVO;
		}

		public void setFormMeioContatoRecusadoVO(br.com.vivo.fo.cliente.vo.ComunicacoesRecusadasVODocument.ComunicacoesRecusadasVO.MeioContatoRecusado[] formMeioContatoRecusadoVO) {
			this.formMeioContatoRecusadoVO = formMeioContatoRecusadoVO;
		}

		public br.com.vivo.fo.cliente.vo.ComunicacoesRecusadasVODocument.ComunicacoesRecusadasVO.MeioContatoRecusado[] getFormMeioContatoRecusadoVO() {
			return this.formMeioContatoRecusadoVO;
		}

		public void setFormMeioContato(String[] formMeioContato) {
			this.formMeioContato = formMeioContato;
		}

		public String[] getFormMeioContato() {
			if (this.formMeioContato == null || this.formMeioContato.length == 0) {
				this.formMeioContato = new String[1];
			}

			return this.formMeioContato;
		}

		public void setFormMeioContatoRecusado(String[] formMeioContatoRecusado) {
			this.formMeioContatoRecusado = formMeioContatoRecusado;
		}

		public String[] getFormMeioContatoRecusado() {
			if (this.formMeioContatoRecusado == null || this.formMeioContatoRecusado.length == 0) {
				this.formMeioContatoRecusado = new String[1];
			}

			return this.formMeioContatoRecusado;
		}

		public void setFormMeioContatoVO(br.com.vivo.fo.cliente.vo.MeioContatoVODocument.MeioContatoVO.MeioContato[] formMeioContatoVO) {
			this.formMeioContatoVO = formMeioContatoVO;
		}

		public br.com.vivo.fo.cliente.vo.MeioContatoVODocument.MeioContatoVO.MeioContato[] getFormMeioContatoVO() {

			return this.formMeioContatoVO;
		}
	}

	public PermissoesForm getPermissoesForm() {
		return this.permissoesForm;

	}

	public static class FormID extends ActionForm {

		private static final long serialVersionUID = -464395483531501215L;

		private String idPessoaCliente = ConstantesCRM.SVAZIO;

		public void setIdPessoaCliente(String idPessoaCliente) {
			this.idPessoaCliente = idPessoaCliente;
		}

		public String getIdPessoaCliente() {
			return this.idPessoaCliente;
		}
	}
}
