package senha.validarCliente;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.senha.vo.RamaisUraVODocument.RamaisUraVO;
import br.com.vivo.fo.senha.vo.RamaisVODocument.RamaisVO;
import br.com.vivo.fo.senha.vo.RamalUraVODocument.RamalUraVO;
import br.com.vivo.fo.senha.vo.RamalVODocument.RamalVO;
import br.com.vivo.fo.workflow.vo.ChamadaTelefonicaVODocument.ChamadaTelefonicaVO;
import br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument.RetornoWFCTIVO;

import com.indracompany.actions.AbstractAction;

public class ValidarClienteController extends AbstractAction {

	private static final long serialVersionUID = -750095745737937400L;

	private ValidarClienteForm validarClienteForm = new ValidarClienteForm();

	private final String ID_CANAL = "1";

	@EJB
	private br.com.vivo.fo.ctrls.workflow.atendimento.AtendimentoFacade atendimentoFacade;

	@EJB
	private br.com.vivo.fo.ctrls.senha.cti.TransferirFacade transferirFacade;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.registrarContato.RegistrarContatoFacade registrarContatoFacade;

	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("carregaGrupo".equals(mapping.getParameter())) {
			return carregaGrupo(mapping, form, request, response);
		} else if ("receberAction".equals(mapping.getParameter())) {
			return receberAction(mapping, form, request, response);
		} else if ("carregarLigacao".equals(mapping.getParameter())) {
			return carregarLigacao(mapping, form, request, response);
		} else if ("transferirCarregar".equals(mapping.getParameter())) {
			return transferirCarregar(mapping, form, request, response);
		} else if ("idPosAction".equals(mapping.getParameter())) {
			return idPosAction(mapping, form, request, response);
		} else if ("transferirRamalAction".equals(mapping.getParameter())) {
			return transferirRamalAction(mapping, form, request, response);
		} else if ("transferirUraAction".equals(mapping.getParameter())) {
			return transferirUraAction(mapping, form, request, response);
		} else if ("terminarAction".equals(mapping.getParameter())) {
			return terminarAction(mapping, form, request, response);
		} else if ("iniciarChamada".equals(mapping.getParameter())) {
			return iniciarChamada(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="receberAction.do"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ValidarClienteForm form = (ValidarClienteForm) formParam;
		ParametrosVO paramVO = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS));

		// busca idGrupo do request
		// caso haja mudança de grupo
		if (request.getParameter("idGrupo") != null) {
			paramVO.setIdGrupo(request.getParameter("idGrupo"));
		}

		form.setIdGrupo(paramVO.getIdGrupo());
		form.setIdUsuario(paramVO.getIdUsuario());
		form.setNumeroOrigem(paramVO.getNrTelefone());
		form.setNomeContato(paramVO.getNmContato());
		form.setIdPos(paramVO.getInPos());
		form.setTipoRelacionamento(paramVO.getIdTipoRelacionamento());
		form.setSenhaValidada((paramVO.getInSenhaValidada() != null ? paramVO.getInSenhaValidada() : ConstantesCRM.SVAZIO).equals("true"));
		form.setIdCallCenter(paramVO.getIdCallCenter());
		form.setIdUra(paramVO.getIdUra());
		String str = ((paramVO.getNrCodArea() != null ? paramVO.getNrCodArea() : ConstantesCRM.SVAZIO) + (paramVO.getNrLinha() != null ? paramVO.getNrLinha() : ""));
		form.setNumeroConsultado(str);
		form.setCliente(paramVO.getIdTipoRelacionamento() != null ? paramVO.getIdTipoRelacionamento().equals(ConstantesCRM.STWO) : false);
		form.setUsuario(paramVO.getIdTipoRelacionamento() != null ? paramVO.getIdTipoRelacionamento().equals(ConstantesCRM.SONE) : false);
		if (paramVO.getIdTipoRelacionamento() != null && paramVO.getIdTipoRelacionamento().equals(ConstantesCRM.STWO)) {
			form.setIdPessoa(paramVO.getIdPessoaCliente());
		}
		if (paramVO.getIdTipoRelacionamento() != null && paramVO.getIdTipoRelacionamento().equals(ConstantesCRM.SONE)) {
			form.setIdPessoa(paramVO.getIdPessoaUsuario());
		}
		// verifica se está sendo carregado a partir de mudança de grupo
		if (request.getParameter("idGrupo") == null) {
			form.setObservacao(paramVO.getObsAtend());
			form.setIndRedirTimeoutURA(paramVO.getIndRedirTimeoutURA());
		} else {
			form.setObservacao(ConstantesCRM.SVAZIO);
			paramVO.setObsAtend(ConstantesCRM.SVAZIO);
			form.setIndRedirTimeoutURA("N");
			paramVO.setIndRedirTimeoutURA("N");
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iCampanha.jsp"
	 */
	public ActionForward carregaGrupo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ValidarClienteForm form = (ValidarClienteForm) formParam;
		ParametrosVO paramVO = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS));
		// busca idGrupo do request
		// caso haja mudança de grupo
		if (request.getParameter("idGrupo") != null) {
			paramVO.setIdGrupo(request.getParameter("idGrupo"));
			form.setIdGrupo(request.getParameter("idGrupo"));
		}

		// Obtem idPessoaUsuario da Sessão
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		form.setIdUsuario(user);

		// Obtém campanhas a partir de grupo
		RetornoWFCTIVO[] campanhasVO = new RetornoWFCTIVO[0];
		campanhasVO = atendimentoFacade.obtemCampanhaGrupoVO(form.getIdUsuario(), form.getIdGrupo());
		// verifica se irá setar como campanha padrão
		if (campanhasVO.length == 1 && campanhasVO[0].getInPadrao().equals(ConstantesCRM.SONE)) {
			// Só tem a campanha padrão
			this.validarClienteForm.setCampanhaPadrao("S");
		}
		this.validarClienteForm.setCampanhasVO(campanhasVO);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="validarCliente.jsp"
	 */
	public ActionForward receberAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ValidarClienteForm form = (ValidarClienteForm) formParam;
		// deverá vir normalmente pelo form, pois já foi setado
		form.setTipoRelacionamento(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoRelacionamento());
		if (form.getTipoRelacionamento() != null) {
			form.setIdPos(ConstantesCRM.STWO);
		}
		this.validarClienteForm = form;
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="receberAction.do"
	 */
	public ActionForward carregarLigacao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ValidarClienteForm form = (ValidarClienteForm) formParam;
		ParametrosVO paramVO = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS));
		form.setIdGrupo(paramVO.getIdGrupo());
		form.setIdUsuario(paramVO.getIdUsuario());
		form.setNumeroOrigem(paramVO.getNrTelefone());
		form.setNomeContato(paramVO.getNmContato());
		form.setIdPos(paramVO.getInPos());
		form.setTipoRelacionamento(paramVO.getIdTipoRelacionamento());
		form.setSenhaValidada((paramVO.getInSenhaValidada() != null ? paramVO.getInSenhaValidada() : ConstantesCRM.SVAZIO).equals("true"));
		form.setIdCallCenter(paramVO.getIdCallCenter());
		form.setIdUra(paramVO.getIdUra());
		form.setNumeroConsultado(paramVO.getNrCodArea() + paramVO.getNrLinha());
		form.setCliente(paramVO.getIdTipoRelacionamento() != null ? paramVO.getIdTipoRelacionamento().equals(ConstantesCRM.STWO) : false);
		form.setUsuario(paramVO.getIdTipoRelacionamento() != null ? paramVO.getIdTipoRelacionamento().equals(ConstantesCRM.SONE) : false);
		if (paramVO.getIdTipoRelacionamento() != null && paramVO.getIdTipoRelacionamento().equals(ConstantesCRM.STWO)) {
			form.setIdPessoa(paramVO.getIdPessoaCliente());
		}
		if (paramVO.getIdTipoRelacionamento() != null && paramVO.getIdTipoRelacionamento().equals(ConstantesCRM.SONE)) {
			form.setIdPessoa(paramVO.getIdPessoaUsuario());
		}
		// verifica se está sendo carregado a partir de mudança de grupo
		if (request.getParameter("idGrupo") == null) {
			form.setObservacao(paramVO.getObsAtend());
			form.setIndRedirTimeoutURA(paramVO.getIndRedirTimeoutURA());
		} else {
			form.setObservacao(ConstantesCRM.SVAZIO);
			paramVO.setObsAtend(ConstantesCRM.SVAZIO);
			form.setIndRedirTimeoutURA("N");
			paramVO.setIndRedirTimeoutURA("N");
		}

		// seta variável para carregar nova ligaçao
		form.setNovaLigacao(true);
		// variável para carregamento dos parâmetros
		ValidarClienteForm formAux = new ValidarClienteForm();
		formAux.setNumeroOrigem(request.getParameter("nrTelefone"));
		formAux.setIdCallCenter(request.getParameter("idCallCenter"));
		formAux.setIdUra(request.getParameter("idUra"));
		formAux.setNumeroConsultado(request.getParameter("nrLinha"));
		formAux.setTipoRelacionamento(request.getParameter("idTipoRelacionamento"));
		formAux.setCliente(request.getParameter("idTipoRelacionamento") != null ? request.getParameter("idTipoRelacionamento").equals("2") : false);
		formAux.setUsuario(request.getParameter("idTipoRelacionamento") != null ? request.getParameter("idTipoRelacionamento").equals("1") : false);
		formAux.setObservacao(request.getParameter("observacao"));
		formAux.setIndRedirTimeoutURA(request.getParameter("timeoutURA"));
		formAux.setSenhaValidada(request.getParameter("inSenhaValidada").equals("true"));
		// seta no formulário dados da nova ligaçao
		form.setNovaLigacaoForm(formAux);

		// Obtem idPessoaUsuario da Sessão
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		form.setIdUsuario(user);

		// Obtém campanhas a partir de grupo
		RetornoWFCTIVO[] campanhasVO = new RetornoWFCTIVO[0];
		campanhasVO = atendimentoFacade.obtemCampanhaGrupoVO(form.getIdUsuario(), form.getIdGrupo());
		// verifica se irá setar como campanha padrão
		if (campanhasVO.length == 1 && campanhasVO[0].getInPadrao().equals(ConstantesCRM.SONE)) {
			// Só tem a campanha padrão
			form.setCampanhaPadrao("S");
		}
		form.setCampanhasVO(campanhasVO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iTransferir.jsp"
	 */
	public ActionForward transferirCarregar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ValidarClienteForm form = (ValidarClienteForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		this.validarClienteForm.setTipoRelacionamento(form.getTipoRelacionamento());
		this.validarClienteForm.setObservacao(form.getObservacao());
		this.validarClienteForm.setNumeroOrigem(form.getNumeroOrigem());
		this.validarClienteForm.setNumeroConsultado(form.getNumeroConsultado());
		this.validarClienteForm.setSenhaValidada(form.isSenhaValidada());
		this.validarClienteForm.setCliente(form.isCliente());
		this.validarClienteForm.setUsuario(form.isUsuario());
		this.validarClienteForm.setIndRedirTimeoutURA(form.getIndRedirTimeoutURA());
		this.validarClienteForm.setIdPos(form.getIdPos());

		this.validarClienteForm.setIdCallCenter(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdCallCenter());
		this.validarClienteForm.setIdUra(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdUra());

		this.validarClienteForm.setIdUsuario(user);

		if (!(this.validarClienteForm.getIdCallCenter().equals(ConstantesCRM.SVAZIO) || this.validarClienteForm.getIdUsuario().equals(ConstantesCRM.SVAZIO))) {
			// Obtém a lista de ramais para o callcenter
			RamaisVO ramaisVO = transferirFacade.ConsRamal(this.validarClienteForm.getIdCallCenter(), this.validarClienteForm.getIdUsuario());

			this.validarClienteForm.setRamalVO(ramaisVO.getRamalVOArray());
		}

		if (!(this.validarClienteForm.getIdUra().equals(ConstantesCRM.SVAZIO) || this.validarClienteForm.getIdUsuario().equals(ConstantesCRM.SVAZIO))) {
			// Obtém a lista de ramais para a URA
			RamaisUraVO ramaisUraVO = transferirFacade.ConsRamalURA(this.validarClienteForm.getIdUra(), this.validarClienteForm.getIdUsuario());

			this.validarClienteForm.setRamalUraVO(ramaisUraVO.getRamalUraVOArray());
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="validarCliente.jsp"
	 */
	public ActionForward idPosAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ValidarClienteForm form = (ValidarClienteForm) formParam;
		this.validarClienteForm = form;
		this.validarClienteForm.setIdentificacaoPositiva("S");

		// seta na sessão o idTipoRelacionamento
		ParametrosVO paramVO = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS));
		paramVO.setIdTipoRelacionamento(this.validarClienteForm.getTipoRelacionamento());
		paramVO.setInPos(this.validarClienteForm.getIdPos());
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="validarCliente.jsp"
	 */
	public ActionForward transferirRamalAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ValidarClienteForm form = (ValidarClienteForm) formParam;
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="validarCliente.jsp"
	 */
	public ActionForward transferirUraAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ValidarClienteForm form = (ValidarClienteForm) formParam;
		this.validarClienteForm = new ValidarClienteForm();
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="validarCliente.jsp"
	 */
	public ActionForward terminarAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ValidarClienteForm form = (ValidarClienteForm) formParam;
		ParametrosVO paramVO = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS));
		if (paramVO != null && paramVO.getIdChamadaTelefonica() != null && !paramVO.getIdChamadaTelefonica().equals(ConstantesCRM.SVAZIO)) {
			this.validarClienteForm.setIdUsuario((ConstantesCRM.getCurrentUser(request.getSession())));
			registrarContatoFacade.enviarProcessosCorrentes(this.validarClienteForm.getIdUsuario(), paramVO.getIdChamadaTelefonica(), ConstantesCRM.SVAZIO);
		}

		// verifica se irá carregar nova ligaçao ou não
		if (this.validarClienteForm.isNovaLigacao()) {
			this.validarClienteForm.setTerminar("N");
			paramVO.setNrTelefone(null);
		} else {
			this.validarClienteForm = new ValidarClienteForm();
			this.validarClienteForm.setTerminar("S");
			request.getSession().removeAttribute(ConstantesCRM.SPARAMETROS);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="validarCliente.jsp"
	 */
	public ActionForward iniciarChamada(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ValidarClienteForm form = (ValidarClienteForm) formParam;
		this.validarClienteForm = form;
		// Obtem idPessoaUsuario da Sessão
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		this.validarClienteForm.setIdUsuario(user);

		// Obtém o idChamadaTelefonica
		ChamadaTelefonicaVO chamadaTelefonicaVO = atendimentoFacade.obtemChamadaTelefonicaTdVO(this.validarClienteForm.getIdUsuario());
		// seta na sessão o idChamadaTelefonica
		ParametrosVO paramVO = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS));
		paramVO.setIdChamadaTelefonica(String.valueOf(chamadaTelefonicaVO.getIdChamadaTelefonica()));

		this.validarClienteForm.setIdChamadaTelefonica(String.valueOf(chamadaTelefonicaVO.getIdChamadaTelefonica()));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class ValidarClienteForm extends ActionForm {

		private static final long serialVersionUID = -7765490409771981732L;

		public ValidarClienteForm() {
		}

		// caso haja nova ligacao entrante
		private ValidarClienteForm novaLigacaoForm;

		public void setNovaLigacaoForm(ValidarClienteForm novaLigacaoForm) {
			this.novaLigacaoForm = novaLigacaoForm;
		}

		public ValidarClienteForm getNovaLigacaoForm() {
			return this.novaLigacaoForm;
		}

		// novaLigaçao
		private boolean novaLigacao;

		public void setNovaLigacao(boolean novaLigacao) {
			this.novaLigacao = novaLigacao;
		}

		public boolean isNovaLigacao() {
			return this.novaLigacao;
		}

		// senhaValidada
		private boolean senhaValidada;

		public void setSenhaValidada(boolean senhaValidada) {
			this.senhaValidada = senhaValidada;
		}

		public boolean isSenhaValidada() {
			return this.senhaValidada;
		}

		// cliente
		private boolean cliente;

		public void setCliente(boolean cliente) {
			this.cliente = cliente;
		}

		public boolean isCliente() {
			return this.cliente;
		}

		// usuario
		private boolean usuario;

		public void setUsuario(boolean usuario) {
			this.usuario = usuario;
		}

		public boolean isUsuario() {
			return this.usuario;
		}

		// numeroOrigem
		private String idGrauSatisfacao = ConstantesCRM.SVAZIO;

		public void setIdGrauSatisfacao(String idGrauSatisfacao) {
			this.idGrauSatisfacao = idGrauSatisfacao;
		}

		public String getIdGrauSatisfacao() {
			return this.idGrauSatisfacao;
		}

		// numeroOrigem
		private String numeroOrigem = ConstantesCRM.SVAZIO;

		public void setNumeroOrigem(String numeroOrigem) {
			this.numeroOrigem = numeroOrigem;
		}

		public String getNumeroOrigem() {
			return this.numeroOrigem;
		}

		// navegacaoURA
		private String navegacaoURA = ConstantesCRM.SVAZIO;

		public void setNavegacaoURA(String navegacaoURA) {
			this.navegacaoURA = navegacaoURA;
		}

		public String getNavegacaoURA() {
			return this.navegacaoURA;
		}

		// tipoProc
		private String tipoProc = ConstantesCRM.SVAZIO;

		public void setTipoProc(String tipoProc) {
			this.tipoProc = tipoProc;
		}

		public String getTipoProc() {
			return this.tipoProc;
		}

		// numeroProc
		private String numeroProc = ConstantesCRM.SVAZIO;

		public void setNumeroProc(String numeroProc) {
			this.numeroProc = numeroProc;
		}

		public String getNumeroProc() {
			return this.numeroProc;
		}

		// idPos
		// Default Não
		private String idPos = ConstantesCRM.SZERO;

		public void setIdPos(String idPos) {
			this.idPos = idPos;
		}

		public String getIdPos() {
			return this.idPos;
		}

		// nomeContato
		private String nomeContato = ConstantesCRM.SVAZIO;

		public void setNomeContato(String nomeContato) {
			this.nomeContato = nomeContato;
		}

		public String getNomeContato() {
			return this.nomeContato;
		}

		// tipoRelacionamento
		private String tipoRelacionamento = ConstantesCRM.SVAZIO;

		public void setTipoRelacionamento(String tipoRelacionamento) {
			this.tipoRelacionamento = tipoRelacionamento;
		}

		public String getTipoRelacionamento() {
			return this.tipoRelacionamento;
		}

		// idUsuario
		private String idUsuario = ConstantesCRM.SVAZIO;

		public void setIdUsuario(String idUsuario) {
			this.idUsuario = idUsuario;
		}

		public String getIdUsuario() {
			return this.idUsuario;
		}

		// idCallCenter
		private String idCallCenter = ConstantesCRM.SVAZIO;

		public void setIdCallCenter(String idCallCenter) {
			this.idCallCenter = idCallCenter;
		}

		public String getIdCallCenter() {
			return this.idCallCenter;
		}

		// idUra
		private String idUra = ConstantesCRM.SVAZIO;

		public void setIdUra(String idUra) {
			this.idUra = idUra;
		}

		public String getIdUra() {
			return this.idUra;
		}

		// idPessoa
		private String idPessoa = ConstantesCRM.SVAZIO;

		public void setIdPessoa(String idPessoa) {
			this.idPessoa = idPessoa;
		}

		public String getIdPessoa() {
			return this.idPessoa;
		}

		// idChamadaTelefonica
		private String idChamadaTelefonica = ConstantesCRM.SVAZIO;

		public void setIdChamadaTelefonica(String idChamadaTelefonica) {
			this.idChamadaTelefonica = idChamadaTelefonica;
		}

		public String getIdChamadaTelefonica() {
			return this.idChamadaTelefonica;
		}

		// observacao
		private String observacao = ConstantesCRM.SVAZIO;

		public void setObservacao(String observacao) {
			this.observacao = observacao;
		}

		public String getObservacao() {
			return this.observacao;
		}

		// destinoTransferencia
		private String destinoTransferencia = ConstantesCRM.SVAZIO;

		public void setDestinoTransferencia(String destinoTransferencia) {
			this.destinoTransferencia = destinoTransferencia;
		}

		public String getDestinoTransferencia() {
			return this.destinoTransferencia;
		}

		// indRedirTimeoutURA
		private String indRedirTimeoutURA = ConstantesCRM.SVAZIO;

		public void setIndRedirTimeoutURA(String indRedirTimeoutURA) {
			this.indRedirTimeoutURA = indRedirTimeoutURA;
		}

		public String getIndRedirTimeoutURA() {
			return this.indRedirTimeoutURA;
		}

		// idGrupo
		private String idGrupo = ConstantesCRM.SVAZIO;

		public void setIdGrupo(String idGrupo) {
			this.idGrupo = idGrupo;
		}

		public String getIdGrupo() {
			return this.idGrupo;
		}

		// ramal
		private String ramal = ConstantesCRM.SVAZIO;

		public void setRamal(String ramal) {
			this.ramal = ramal;
		}

		public String getRamal() {
			return this.ramal;
		}

		// numRamalSel
		private String numRamalSel = ConstantesCRM.SVAZIO;

		public void setNumRamalSel(String numRamalSel) {
			this.numRamalSel = numRamalSel;
		}

		public String getNumRamalSel() {
			return this.numRamalSel;
		}

		// numRamalUraSel
		private String numRamalUraSel = ConstantesCRM.SVAZIO;

		public void setNumRamalUraSel(String numRamalUraSel) {
			this.numRamalUraSel = numRamalUraSel;
		}

		public String getNumRamalUraSel() {
			return this.numRamalUraSel;
		}

		// numCampanhaSel
		private String numCampanhaSel = ConstantesCRM.SVAZIO;

		public void setNumCampanhaSel(String numCampanhaSel) {
			this.numCampanhaSel = numCampanhaSel;
		}

		public String getNumCampanhaSel() {
			return this.numCampanhaSel;
		}

		// terminar
		private String identificacaoPositiva = ConstantesCRM.SVAZIO;

		public void setIdentificacaoPositiva(String identificacaoPositiva) {
			this.identificacaoPositiva = identificacaoPositiva;
		}

		public String getIdentificacaoPositiva() {
			return this.identificacaoPositiva;
		}

		// terminar
		private String terminar = ConstantesCRM.SVAZIO;

		public void setTerminar(String terminar) {
			this.terminar = terminar;
		}

		public String getTerminar() {
			return this.terminar;
		}

		// numeroConsultado
		private String numeroConsultado = ConstantesCRM.SVAZIO;

		public void setNumeroConsultado(String numeroConsultado) {
			this.numeroConsultado = numeroConsultado;
		}

		public String getNumeroConsultado() {
			return this.numeroConsultado;
		}

		// ramalUraVO
		private RamalUraVO[] ramalUraVO = new RamalUraVO[0];

		public void setRamalUraVO(RamalUraVO[] ramalUraVO) {
			this.ramalUraVO = ramalUraVO;
		}

		public RamalUraVO[] getRamalUraVO() {
			return this.ramalUraVO;
		}

		// ramalVO
		private RamalVO[] ramalVO = new RamalVO[0];

		public void setRamalVO(RamalVO[] ramalVO) {
			this.ramalVO = ramalVO;
		}

		public RamalVO[] getRamalVO() {
			return this.ramalVO;
		}

		// campanhasVO
		private RetornoWFCTIVO[] campanhasVO = new RetornoWFCTIVO[0];

		public void setCampanhasVO(RetornoWFCTIVO[] campanhasVO) {
			this.campanhasVO = campanhasVO;
		}

		public RetornoWFCTIVO[] getCampanhasVO() {
			return this.campanhasVO;
		}

		private String campanhaPadrao = "N";

		public void setCampanhaPadrao(String campanhaPadrao) {
			this.campanhaPadrao = campanhaPadrao;
		}

		public String getCampanhaPadrao() {
			return this.campanhaPadrao;
		}

	}

	/**
	 * Getter para o FormBean
	 */
	public ValidarClienteForm getValidarClienteForm() {
		return this.validarClienteForm;
	}

	/**
	 * Setter para o FormBean
	 */
	public void setValidarClienteForm(ValidarClienteForm form) {
		this.validarClienteForm = form;
	}

}
