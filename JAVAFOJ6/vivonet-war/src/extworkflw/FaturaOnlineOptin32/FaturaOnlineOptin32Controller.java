package extworkflw.FaturaOnlineOptin32;

import java.io.BufferedOutputStream;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.AxisFault;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.ClienteUtils;
import br.com.vivo.fo.cliente.vo.ComunicacaoVODocument.ComunicacaoVO;
import br.com.vivo.fo.cliente.vo.DocumentoVODocument.DocumentoVO;
import br.com.vivo.fo.cliente.vo.LupaClienteVODocument.LupaClienteVO;
import br.com.vivo.fo.cliente.vo.LupaClienteVODocument.LupaClienteVO.DadosAbaLupaCliente;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.commons.utils.EmailAddress;
import br.com.vivo.fo.commons.utils.GetParametro;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.VOLTAV.fatura.db.dbClasses.TipoComunicacao;
import br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.Conta;
import br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Geral.ErroInfo;
import br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.SN.ContaPos.ContaPosPortTypeProxy;
import br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.SN.ContaPos.ParametrosConsultarFaturaOnLine;
import br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.SN.ContaPos.ParametrosManterFaturaOnLine;
import br.com.vivo.fo.exception.FOException;
import br.com.vivo.fo.log.Logger;

import com.bea.wlw.netui.pageflow.NotLoggedInException;
import com.indracompany.actions.AbstractAction;
import commons.errors.AjaxError;

@SuppressWarnings({"unused","deprecation"})
public class FaturaOnlineOptin32Controller extends AbstractAction {

	private static final long serialVersionUID = 2637536893607782058L;

	@EJB
	private br.com.vivo.fo.ctrls.cliente.associarGestor.RelacionarGestor associarGestorFacade;

	protected global.Global globalApp = new global.Global();

	FaturaOnlineForm faturaOnlineForm;

	private static final String SERVICO_INDISPONIVEL = "No momento não foi possível realizar a sua solicitação. Por favor, tente mais tarde.";
	private static final String SESSAO_EXPIRADA = "Sessão expirada.";
	private static final String FATURAONLINE_ENDPOINT = "URL_FATURA_ONLINE";
	private static final String FATURAONLINE_USER = "USUARIO_FATURA_ONLINE";
	private static final String FATURAONLINE_PASSWORD = "SENHA_FATURA_ONLINE";
	private static final int STATUS_EMPROCESSAMENTO = 2;
	private static final int STATUS_INATIVO = 7;

	private String urlFaturaOnline = ConstantesCRM.SVAZIO;
	private String userFaturaOnline = ConstantesCRM.SVAZIO;
	private String passFaturaOnline = ConstantesCRM.SVAZIO;

	public static final String GESTOR_CONTA = "GC";
	public static final String GESTOR_MASTER = "GM";
	public static final String GERENTE_DE_CONTA = "GRC";

	@EJB
	private br.com.vivo.fo.ctrls.cliente.telaInicial.TelaInicialFacade telaInicialFacade;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.registrarContato.RegistrarContatoFacade registrarContatoFacade;

	@EJB
	private br.com.vivo.fo.ctrls.VOLTAV.fatura.FaturaFacade faturaFacade;

	private transient Logger log = new Logger("lupafatura");

	public FaturaOnlineForm getFaturaOnlineForm() {
		if (this.faturaOnlineForm == null) {
			this.faturaOnlineForm = new FaturaOnlineForm();
		}
		return this.faturaOnlineForm;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("desativacaoFaturaOnline".equals(mapping.getParameter())) {
			return desativacaoFaturaOnline(mapping, form, request, response);
		} else if ("manutencaoFaturaOnline".equals(mapping.getParameter())) {
			return manutencaoFaturaOnline(mapping, form, request, response);
		} else if ("ativacaoFaturaOnline".equals(mapping.getParameter())) {
			return ativacaoFaturaOnline(mapping, form, request, response);
		} else if ("manageContaOnline".equals(mapping.getParameter())) {
			return manageContaOnline(mapping, form, request, response);
		} else if ("submitServicos".equals(mapping.getParameter())) {
			return submitServicos(mapping, form, request, response);
		} else if ("manageCadastroEmail".equals(mapping.getParameter())) {
			return manageCadastroEmail(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="successManutencao" path="manageContaOnline.do"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String destino = ConstantesCRM.SUCCESS;
		try {
			getFaturaOnlineForm().setManutencao((request.getParameter("operacao") != null && "manutencao".equalsIgnoreCase(request.getParameter("operacao"))) ? true : false);

			String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
			getFaturaOnlineForm().setNrConta(getParametrosVO(request).getNrConta());

			getFaturaOnlineForm().setMsgErro(ConstantesCRM.SVAZIO);
			ContaPosPortTypeProxy proxy = getContaPosPortTypeProxy(request);

			ParametrosConsultarFaturaOnLine parametrosConsultarFaturaOnlineRequest = new ParametrosConsultarFaturaOnLine();
			parametrosConsultarFaturaOnlineRequest.setNrConta(getFaturaOnlineForm().getNrConta());

			Conta conta = proxy.consultarFaturaOnLine(parametrosConsultarFaturaOnlineRequest);

			if (conta.getEnviaBoleto().booleanValue()) {
				getFaturaOnlineForm().setFaturaOnlineAtiva(true);
				getFaturaOnlineForm().setAtivacao(false);

				if (conta.getEmailContaGerada() != null && conta.getEmailContaGerada().booleanValue()) {
					getFaturaOnlineForm().setAvisoFaturaDisponivelPorEmail(true);
				} else {
					getFaturaOnlineForm().setAvisoFaturaDisponivelPorEmail(false);
				}

				if (conta.getEnvioEletronicoFatura() != null && conta.getEnvioEletronicoFatura().booleanValue()) {
					getFaturaOnlineForm().setServicoEnvioFaturaPorEmail(true);
				} else {
					getFaturaOnlineForm().setServicoEnvioFaturaPorEmail(false);
				}

				if (conta.getSmsContaGerada() != null && conta.getSmsContaGerada().booleanValue()) {
					getFaturaOnlineForm().setServicoEnvioCodigoBarrasPorSMS(true);
				} else {
					getFaturaOnlineForm().setServicoEnvioCodigoBarrasPorSMS(false);
				}

			} else {
				getFaturaOnlineForm().setFaturaOnlineAtiva(false);
				getFaturaOnlineForm().setAtivacao(true);
			}

			if (getFaturaOnlineForm().isManutencao()) {

				if (!getFaturaOnlineForm().isFaturaOnlineAtiva()) {

					getFaturaOnlineForm().setMsgErro("Fatura on-line desativada. Por favor, acesse a funcionalidade de ativação da fatura on-line.");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(destino);

				} else {

					destino = "successManutencao";
					if ("PF".equalsIgnoreCase(getParametrosVO(request).getInTipoPessoa())) {
						request.setAttribute("form", getFaturaOnlineForm());
						request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
						return mapping.findForward(destino);

					} else if ("PJ".equalsIgnoreCase(getParametrosVO(request).getInTipoPessoa())) {
						getDadosGestorConta(request);
					}

				}

			}

		} catch (AxisFault axisFaultException) {
			if (axisFaultException instanceof ErroInfo) {
				ErroInfo erroInfo = (ErroInfo) axisFaultException;
				if (Integer.parseInt(erroInfo.getCodigo()) == STATUS_EMPROCESSAMENTO) {
					getFaturaOnlineForm().setFaturaOnlineEmProcessamento(true);
					getFaturaOnlineForm().setMsgErro("Existe uma solicitação em processamento. Por favor, aguarde finalização.");
				} else if (Integer.parseInt(erroInfo.getCodigo()) == STATUS_INATIVO) {
					if (getFaturaOnlineForm().isManutencao()) {
						getFaturaOnlineForm().setMsgErro("Fatura on-line desativada. Por favor, acesse a funcionalidade de ativação da fatura on-line.");
						getFaturaOnlineForm().setFaturaOnlineAtiva(false);
						getFaturaOnlineForm().setAvisoFaturaDisponivelPorEmail(false);
						getFaturaOnlineForm().setServicoEnvioFaturaPorEmail(false);
						getFaturaOnlineForm().setServicoEnvioCodigoBarrasPorSMS(false);
						request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
						return mapping.findForward(destino);
					} else {

						// Validação de gestor de contas para Pessoa Jurídica
						if (("PJ".equalsIgnoreCase(getParametrosVO(request).getInTipoPessoa()) && !getFaturaOnlineForm().isFaturaOnlineAtiva()) || (getFaturaOnlineForm().isManutencao()) && "PJ".equalsIgnoreCase(getParametrosVO(request).getInTipoPessoa())) {
							getDadosGestorConta(request);
						}

						getFaturaOnlineForm().setFaturaOnlineAtiva(false);
						getFaturaOnlineForm().setAvisoFaturaDisponivelPorEmail(false);
						getFaturaOnlineForm().setServicoEnvioFaturaPorEmail(false);
						getFaturaOnlineForm().setServicoEnvioCodigoBarrasPorSMS(false);
						getFaturaOnlineForm().setAtivacao(true);
					}
				} else {
					getFaturaOnlineForm().setMsgErroDetalhada(erroInfo.getDescricao());
					getFaturaOnlineForm().setMsgErro(SERVICO_INDISPONIVEL);
				}
			} else {
				getFaturaOnlineForm().setMsgErro(SERVICO_INDISPONIVEL);
				getFaturaOnlineForm().setMsgErroDetalhada(axisFaultException.getFaultString());
			}
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);
		} catch (FOException e) {
			getFaturaOnlineForm().setMsgErro(SERVICO_INDISPONIVEL);
			getFaturaOnlineForm().setMsgErroDetalhada(e.getMessage());
		} catch (NotLoggedInException e) {
			getFaturaOnlineForm().setMsgErro(SESSAO_EXPIRADA);
			getFaturaOnlineForm().setMsgErroDetalhada(e.toString());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);
		} catch (Exception e) {
			getFaturaOnlineForm().setMsgErro(SERVICO_INDISPONIVEL);
			getFaturaOnlineForm().setMsgErroDetalhada(e.toString());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);
		}

		if (getFaturaOnlineForm().isManutencao()) {
			request.setAttribute("form", getFaturaOnlineForm());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);
		} else {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);
		}
	}

	private void getDadosGestorConta(HttpServletRequest request) throws Exception {

		log.debug("[FaturaOnlineOptin32Controller.getDadosGestorConta.] Inicio ");
		String nrCNPJ = ConstantesCRM.SVAZIO;
		String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		int idTipoLinha = ConstantesCRM.SVAZIO.equals(getParametrosVO(request).getIdTipoLinha()) ? 0 : Integer.parseInt(getParametrosVO(request).getIdTipoLinha());

		DocumentoVO[] documento = telaInicialFacade.getLupaClienteVO(idUsuario, getParametrosVO(request).getIdPessoaCliente(), getParametrosVO(request).getNrLinha(), getParametrosVO(request).getIdConta(), idTipoLinha).getDadosAbaLupaCliente().getDocumentoVOArray();

		for (int i = 0; i < documento.length; i++) {
			if ("CNPJ".equals(documento[i].getTipoDocumentoVO().getSgTipoDocumento())) {
				nrCNPJ = documento[i].getNrDocumento();
				break;
			}
		}

		br.com.vivo.fo.ctrls.cliente.associarGestor.db.Gestor[] gestores = null;
		try {
			String nrConta = getParametrosVO(request).getNrConta() != null ? getParametrosVO(request).getNrConta() : getParametrosVO(request).getIdContaSistemaOrigem();
			log.debug("[FaturaOnlineOptin32Controller.getDadosGestorConta.] Buscar Gestores nrCNPJ " + nrCNPJ + " nrConta: " + nrConta);
			gestores = associarGestorFacade.getGestoresConta(nrCNPJ, nrConta);
			log.debug("[FaturaOnlineOptin32Controller.getDadosGestorConta.] Gestores " + gestores);
		} catch (Exception e) {
			getFaturaOnlineForm().setMsgErro(SERVICO_INDISPONIVEL);
			getFaturaOnlineForm().setMsgErroDetalhada(e.toString());
		}

		br.com.vivo.fo.ctrls.cliente.associarGestor.db.Gestor gestorContas = null;

		if (gestores != null && gestores.length > 0) {
			for (int i = 0; i < gestores.length; i++) {
				br.com.vivo.fo.ctrls.cliente.associarGestor.db.Gestor gestor = gestores[i];
				if (GESTOR_CONTA.equalsIgnoreCase(gestor.getSgTipoRelacionamento())) {
					gestorContas = gestor;
				}
			}

			if (gestorContas != null) {
				getFaturaOnlineForm().setPossuiGestor(true);

				getFaturaOnlineForm().setGestorEmail(EmailAddress.isValidText(gestorContas.getEmail()));
				getFaturaOnlineForm().setDsEmailGestor(EmailAddress.isValidText(gestorContas.getEmail()) ? gestorContas.getEmail() : null);

				log.debug("[FaturaOnlineOptin32Controller.getDadosGestorConta.] Gestor Email ? " + getFaturaOnlineForm().isGestorEmail());
				log.debug("[FaturaOnlineOptin32Controller.getDadosGestorConta.] Gestor Email  " + getFaturaOnlineForm().getDsEmailGestor());

				getFaturaOnlineForm().setGestorTelefone(gestorContas.getNrTelefoneCelVivo() == null || ConstantesCRM.SVAZIO.equals(gestorContas.getNrTelefoneCelVivo()) ? false : true);
				getFaturaOnlineForm().setNrTelefoneGestor((gestorContas.getNrTelefoneCelVivo() == null || ConstantesCRM.SVAZIO.equals(gestorContas.getNrTelefoneCelVivo())) ? null : gestorContas.getNrTelefoneCelVivoFormatado());

				log.debug("[FaturaOnlineOptin32Controller.getDadosGestorConta.] Gestor Tel ? " + getFaturaOnlineForm().isGestorTelefone());
				log.debug("[FaturaOnlineOptin32Controller.getDadosGestorConta.] Gestor Telefone  " + getFaturaOnlineForm().getNrTelefoneGestor());
				if (getFaturaOnlineForm().isGestorTelefone() && getFaturaOnlineForm().isGestorEmail()) {
					ComunicacaoVO emailGestor = ComunicacaoVO.Factory.newInstance();
					emailGestor.setDsContato(getFaturaOnlineForm().getDsEmailGestor());
					emailGestor.addNewTipoComunicacaoVO().setDsTipoComunicacao("E-MAIL");
					getFaturaOnlineForm().setListaEmails(null);
					getFaturaOnlineForm().getListaEmails().addNewComunicacaoVO().set(emailGestor.copy());
				}

			}
		}

		if (gestores == null || gestores.length == 0) {
			getFaturaOnlineForm().setMsgErro("Operação não pode ser efetivada. O cliente não possui Gestor de Contas cadastrado. Deseja cadastrá-lo agora?");
			getFaturaOnlineForm().setRedirect(true);
		}

	}

	/**
	 * @jpf:action
	 */
	public ActionForward desativacaoFaturaOnline(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		FaturaOnlineForm form = (FaturaOnlineForm) formParam;
		String idUsuario = ConstantesCRM.SVAZIO;

		try {
			idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
			String cdParametro = ConstantesCRM.SVAZIO;
			String msgRetorno = ConstantesCRM.SVAZIO;

			log.debug("TelaInicialController:desativacaoFaturaOnline(" + idUsuario + ") >> INICIALIZADO");

			if (form.isAceitar()) {

				msgRetorno = "O serviço foi desativado com sucesso!";
				cdParametro = "NMPATH_FATURAONLINE_OPT32_DESATIVACAO_ACEITOU";
				;

				int idTipoLinha = Integer.parseInt(getParametrosVO(request).getIdTipoLinha());
				boolean isPrePago = (idTipoLinha == 2 || idTipoLinha == 6) ? true : false;

				if ("PJ".equalsIgnoreCase(getParametrosVO(request).getInTipoPessoa())) {
					faturaFacade.desativarFaturaEmailPJ(idUsuario, getFaturaOnlineForm().getNrConta());
					log.debug("TelaInicialController:desativacaoFaturaOnline(" + idUsuario + ") >> desativarFaturaEmailPJ OK");
				} else {
					faturaFacade.desativarFaturaEmailPF(idUsuario, getParametrosVO(request).getNrLinha(), isPrePago);
					log.debug("TelaInicialController:desativacaoFaturaOnline(" + idUsuario + ") >> desativarFaturaEmailPF OK");
				}

				ContaPosPortTypeProxy proxy = getContaPosPortTypeProxy(request);
				ParametrosManterFaturaOnLine parametrosManterFaturaOnLineRequest = new ParametrosManterFaturaOnLine();
				parametrosManterFaturaOnLineRequest.setNrConta(getFaturaOnlineForm().getNrConta());
				parametrosManterFaturaOnLineRequest.setIndExpiracao(new Boolean(true));

				try {
					proxy.manterFaturaOnLine(parametrosManterFaturaOnLineRequest);
					log.debug("TelaInicialController:desativacaoFaturaOnline(" + idUsuario + ") >> manterFaturaOnLine OK");

				} catch (ErroInfo e) {
					log.error("TelaInicialController:desativacaoFaturaOnline(" + idUsuario + ") >> ErroInfo: " + e);
					response.setStatus(AjaxError.STATUS_CODE_ERROR, e.getDescricao());
					response.setHeader("stackTrace", AjaxError.getStackTrace(e));
					return null;

				} catch (Exception ex) {
					log.error("TelaInicialController:desativacaoFaturaOnline(" + idUsuario + ") >> Exception: " + ex);
					response.setStatus(AjaxError.STATUS_CODE_ERROR, ex.toString());
					response.setHeader("stackTrace", AjaxError.getStackTrace(ex));
					return null;
				}

			} else {
				cdParametro = "NMPATH_FATURAONLINE_OPT32_DESATIVACAO_NAOACEITOU";
			}

			String xmlOUT = registraPalitagem(idUsuario, cdParametro, msgRetorno, request);
			response.setContentType(ConstantesCRM.SContentType);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			bufferedOutputStream.write(xmlOUT.getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();

			log.debug("TelaInicialController:desativacaoFaturaOnline(" + idUsuario + ") >> FINALIZADO");

			return null;

		} catch (Exception e) {
			log.error("TelaInicialController:desativacaoFaturaOnline(" + idUsuario + ") >> " + e.toString());
			response.setStatus(AjaxError.STATUS_CODE_ERROR, e.toString());
			response.setHeader("stackTrace", AjaxError.getStackTrace(e));
			return null;
		}

	}

	private ContaPosPortTypeProxy getContaPosPortTypeProxy(HttpServletRequest request) throws FOException {

		String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());

		try {

			if (ConstantesCRM.SVAZIO.equals(this.urlFaturaOnline) || ConstantesCRM.SVAZIO.equals(this.userFaturaOnline) || ConstantesCRM.SVAZIO.equals(this.passFaturaOnline)) {
				this.urlFaturaOnline = GetParametro.getInstace().getParametroVO(idUsuario, FATURAONLINE_ENDPOINT).getDsValorParametro();
				this.userFaturaOnline = GetParametro.getInstace().getParametroVO(idUsuario, FATURAONLINE_USER).getDsValorParametro();
				this.passFaturaOnline = GetParametro.getInstace().getParametroVO(idUsuario, FATURAONLINE_PASSWORD).getDsValorParametro();
			}

		} catch (Exception e) {
			throw new FOException(e.getMessage());
		}

		if (ConstantesCRM.SVAZIO.equals(this.urlFaturaOnline)) {
			throw new FOException("Parâmetro necessário não encontrado: " + FATURAONLINE_ENDPOINT);
		} else if (ConstantesCRM.SVAZIO.equals(this.userFaturaOnline)) {
			throw new FOException("Parâmetro necessário não encontrado: " + FATURAONLINE_USER);
		} else if (ConstantesCRM.SVAZIO.equals(this.passFaturaOnline)) {
			throw new FOException("Parâmetro necessário não encontrado: " + FATURAONLINE_PASSWORD);
		}

		ContaPosPortTypeProxy proxy = new ContaPosPortTypeProxy();
		proxy.setEndpoint(this.urlFaturaOnline);
		proxy.setUserName(this.userFaturaOnline);
		proxy.setPassword(this.passFaturaOnline);

		return proxy;

	}

	/**
	 * @jpf:action
	 */
	public ActionForward manutencaoFaturaOnline(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			FaturaOnlineForm form = (FaturaOnlineForm) formParam;
			String cdParametro = "NMPATH_FATURAONLINE_OPT32_ALTERAR_SERVICOS";
			String msgRetorno = ConstantesCRM.SVAZIO;
			String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());

			int idTipoLinha = Integer.parseInt(getParametrosVO(request).getIdTipoLinha());
			boolean isPrePago = (idTipoLinha == 2 || idTipoLinha == 6) ? true : false;

			if (getFaturaOnlineForm().isServicoEnvioFaturaPorEmail() || getFaturaOnlineForm().isAvisoFaturaDisponivelPorEmail()) {

				String dsEmail = request.getParameter("dsEmail") != null ? request.getParameter("dsEmail") : ConstantesCRM.SVAZIO;

				if (!getFaturaOnlineForm().isServicoEnvioFaturaPorEmail() && getFaturaOnlineForm().isAvisoFaturaDisponivelPorEmail()) {
					// Selecionado apenas serviço de envio de disponibilidade de e-mail
					faturaFacade.ativarSomenteAvisoDisponibilidadeEmail(idUsuario, getParametrosVO(request).getNrLinha(), dsEmail, ConstantesCRM.SVAZIO, new Integer(getParametrosVO(request).getIdUfOperadora()), isPrePago, "PJ".equalsIgnoreCase(getParametrosVO(request).getInTipoPessoa()) ? true : false, getFaturaOnlineForm().getNrConta());

				} else {
					faturaFacade.ativaEmail(idUsuario, getParametrosVO(request).getNrLinha(), dsEmail, ConstantesCRM.SVAZIO, new Integer(getParametrosVO(request).getIdUfOperadora()), isPrePago, "PJ".equalsIgnoreCase(getParametrosVO(request).getInTipoPessoa()) ? true : false, getFaturaOnlineForm().getNrConta());
				}

			} else {

				if ("PJ".equalsIgnoreCase(getParametrosVO(request).getInTipoPessoa())) {
					faturaFacade.desativarFaturaEmailPJ(idUsuario, getFaturaOnlineForm().getNrConta());
				} else {
					faturaFacade.desativarFaturaEmailPF(idUsuario, getParametrosVO(request).getNrLinha(), isPrePago);
				}

			}

			ContaPosPortTypeProxy proxy = getContaPosPortTypeProxy(request);
			ParametrosManterFaturaOnLine parametrosManterFaturaOnLineRequest = new ParametrosManterFaturaOnLine();
			parametrosManterFaturaOnLineRequest.setNrConta(getFaturaOnlineForm().getNrConta());
			parametrosManterFaturaOnLineRequest.setIndEnvioBoleto(new Boolean(true));
			parametrosManterFaturaOnLineRequest.setIndEnvioEmailNotificacao(new Boolean(getFaturaOnlineForm().isAvisoFaturaDisponivelPorEmail()));
			parametrosManterFaturaOnLineRequest.setIndEnvioEmailFatura(new Boolean(getFaturaOnlineForm().isServicoEnvioFaturaPorEmail()));
			parametrosManterFaturaOnLineRequest.setIndEnvioSms(new Boolean(getFaturaOnlineForm().isServicoEnvioCodigoBarrasPorSMS()));

			boolean isAssincrono;
			try {
				Conta conta = new Conta();
				conta = proxy.manterFaturaOnLine(parametrosManterFaturaOnLineRequest);

				isAssincrono = conta.getHierarquiaConta().getProcAssincrono().booleanValue();
				if (isAssincrono) {
					msgRetorno = "A sua solicitação foi realizada com sucesso. Após 48h essa solicitação será concluída em nossos sistemas.";
				} else {
					msgRetorno = "O serviço foi ativado com sucesso!";
				}

			} catch (ErroInfo e) {
				response.setStatus(AjaxError.STATUS_CODE_ERROR, e.getDescricao());
				response.setHeader("stackTrace", AjaxError.getStackTrace(e));
				return null;
			}

			String xmlOUT = registraPalitagem(idUsuario, cdParametro, msgRetorno, request);
			response.setContentType(ConstantesCRM.SContentType);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			bufferedOutputStream.write(xmlOUT.getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();

			return null;

		} catch (Exception e) {
			response.setStatus(AjaxError.STATUS_CODE_ERROR, e.getMessage());
			response.setHeader("stackTrace", AjaxError.getStackTrace(e));
			return null;
		}

	}

	/**
	 * @jpf:action
	 */
	public ActionForward ativacaoFaturaOnline(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			FaturaOnlineForm form = (FaturaOnlineForm) formParam;
			String cdParametro = ConstantesCRM.SVAZIO;
			String msgRetorno = ConstantesCRM.SVAZIO;
			String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());

			// Ativação de Fatura Online
			if (getFaturaOnlineForm().isAceitar()) {

				if (getFaturaOnlineForm().isServicoEnvioFaturaPorEmail() || getFaturaOnlineForm().isAvisoFaturaDisponivelPorEmail()) {

					String dsEmail = request.getParameter("dsEmail") != null ? request.getParameter("dsEmail") : ConstantesCRM.SVAZIO;
					int idTipoLinha = Integer.parseInt(getParametrosVO(request).getIdTipoLinha());
					boolean isPrePago = (idTipoLinha == 2 || idTipoLinha == 6) ? true : false;

					if (!getFaturaOnlineForm().isServicoEnvioFaturaPorEmail() && getFaturaOnlineForm().isAvisoFaturaDisponivelPorEmail()) {
						// Selecionado apenas serviço de envio de disponibilidade de e-mail
						faturaFacade.ativarSomenteAvisoDisponibilidadeEmail(idUsuario, getParametrosVO(request).getNrLinha(), dsEmail, ConstantesCRM.SVAZIO, new Integer(getParametrosVO(request).getIdUfOperadora()), isPrePago, "PJ".equalsIgnoreCase(getParametrosVO(request).getInTipoPessoa()) ? true : false, getFaturaOnlineForm().getNrConta());

					} else {
						faturaFacade.ativaEmail(idUsuario, getParametrosVO(request).getNrLinha(), dsEmail, ConstantesCRM.SVAZIO, new Integer(getParametrosVO(request).getIdUfOperadora()), isPrePago, "PJ".equalsIgnoreCase(getParametrosVO(request).getInTipoPessoa()) ? true : false, getFaturaOnlineForm().getNrConta());
					}

				}

				ContaPosPortTypeProxy proxy = getContaPosPortTypeProxy(request);

				ParametrosManterFaturaOnLine parametrosManterFaturaOnLineRequest = new ParametrosManterFaturaOnLine();
				parametrosManterFaturaOnLineRequest.setNrConta(getFaturaOnlineForm().getNrConta());
				parametrosManterFaturaOnLineRequest.setIndEnvioBoleto(new Boolean(true));
				parametrosManterFaturaOnLineRequest.setIndEnvioEmailNotificacao(new Boolean(getFaturaOnlineForm().isAvisoFaturaDisponivelPorEmail()));
				parametrosManterFaturaOnLineRequest.setIndEnvioEmailFatura(new Boolean(getFaturaOnlineForm().isServicoEnvioFaturaPorEmail()));
				parametrosManterFaturaOnLineRequest.setIndEnvioSms(new Boolean(getFaturaOnlineForm().isServicoEnvioCodigoBarrasPorSMS()));

				boolean isAssincrono;
				try {
					Conta conta = new Conta();
					conta = proxy.manterFaturaOnLine(parametrosManterFaturaOnLineRequest);

					isAssincrono = conta.getHierarquiaConta().getProcAssincrono().booleanValue();

				} catch (ErroInfo e) {
					response.setStatus(AjaxError.STATUS_CODE_ERROR, e.getDescricao());
					response.setHeader("stackTrace", AjaxError.getStackTrace(e));
					return null;
				}

				cdParametro = "NMPATH_FATURAONLINE_OPT32_ATIVACAO_ACEITOU";

				if (isAssincrono) {
					msgRetorno = "A sua solicitação foi realizada com sucesso. Após 48h essa solicitação será concluída em nossos sistemas.";
				} else {
					msgRetorno = "O serviço foi ativado com sucesso!";
				}

			} else {
				cdParametro = "NMPATH_FATURAONLINE_OPT32_ATIVACAO_NAOACEITOU";
			}

			String xmlOUT = registraPalitagem(idUsuario, cdParametro, msgRetorno, request);
			response.setContentType(ConstantesCRM.SContentType);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			bufferedOutputStream.write(xmlOUT.getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();

			return null;

		} catch (Exception e) {
			response.setStatus(AjaxError.STATUS_CODE_ERROR, e.getMessage());
			response.setHeader("stackTrace", AjaxError.getStackTrace(e));
			return null;
		}
	}

	private String registraPalitagem(String idUsuario, String cdParametro, String msgRetorno, HttpServletRequest request) {

		StringBuffer xmlOUT = new StringBuffer("<xmlObject>");

		// Registro de palitagem apenas se valor do path do contato for obtido na APOIO.PARAMETRO
		if (!cdParametro.equals(ConstantesCRM.SVAZIO)) {

			String nmPath = ConstantesCRM.SVAZIO;
			String nrProtocoloSessao = request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) != null ? (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) : ConstantesCRM.SVAZIO;

			try {
				nmPath = GetParametro.getInstace().getParametroVO(idUsuario, cdParametro).getDsValorParametro();
			} catch (Exception e) {
				msgRetorno += "\nEntretando, não foi possível registrar a palitagem devido à ausência do parâmetro " + cdParametro + ".";

				xmlOUT.append("<msgRetorno>").append(msgRetorno).append("</msgRetorno>");
				xmlOUT.append("</xmlObject>");

				return xmlOUT.toString();

			}

			String nrProtocolo = ConstantesCRM.SVAZIO;
			try {
				nrProtocolo = ClienteUtils.registrarPalitagem(idUsuario, getParametrosVO(request), getParametrosVO(request).getIdLinha(), getParametrosVO(request).getNrLinha(), GetParametro.getInstace().getParametroVO(idUsuario, cdParametro).getDsValorParametro(), ConstantesCRM.SVAZIO);
			} catch (Exception e) {
				nrProtocolo = ConstantesCRM.SVAZIO;
			}

			boolean isProtocolo = true;
			try {
				long nrLongProtocolo = Long.parseLong(nrProtocolo);
				xmlOUT.append("<nrProtocolo>").append(nrProtocolo).append("</nrProtocolo>");
			} catch (Exception e) {
				isProtocolo = false;
			}

			if (!nrProtocoloSessao.equals(nrProtocolo) && isProtocolo) {
				request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, nrProtocolo);
				getParametrosVO(request).setNrProtocolo(nrProtocolo);
			}

		}
		xmlOUT.append("<msgRetorno>").append(msgRetorno).append("</msgRetorno>");
		xmlOUT.append("</xmlObject>");

		return xmlOUT.toString();

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="selecaoServicos" path="selecaoServicos.jsp"
	 * @jpf:forward name="selecaoEmail" path="selecaoEmail.jsp"
	 */
	public ActionForward manageContaOnline(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		FaturaOnlineForm form = (FaturaOnlineForm) formParam;
		String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		String destino = ConstantesCRM.SVAZIO;
		getFaturaOnlineForm().setMsgErro(ConstantesCRM.SVAZIO);
		getFaturaOnlineForm().setOperacao(form.getOperacao());

		if (ConstantesCRM.SVAZIO.equals(getFaturaOnlineForm().getOperacao())) {
			getFaturaOnlineForm().setOperacao("getServicos");
		}

		try {

			if ("getServicos".equals(getFaturaOnlineForm().getOperacao())) {
				destino = "selecaoServicos";
				if ("PF".equalsIgnoreCase(getParametrosVO(request).getInTipoPessoa())) {
					getFaturaOnlineForm().setClienteDadosZap(faturaFacade.isClienteDadosZap(idUsuario, getParametrosVO(request).getNrLinha().substring(0, 2), getParametrosVO(request).getNrLinha().substring(2)));
				}
			}

			else if ("getEmails".equals(getFaturaOnlineForm().getOperacao())) {
				destino = "selecaoEmail";
				if (form.isAvisoFaturaDisponivelPorEmail() || form.isServicoEnvioFaturaPorEmail()) {
					// Verificação de e-mails de um cliente PF
					if ("PF".equalsIgnoreCase(getParametrosVO(request).getInTipoPessoa())) {
						getFaturaOnlineForm().setListaEmails(null);
						ComunicacaoVO[] contato = telaInicialFacade.getContato(idUsuario, getParametrosVO(request).getIdPessoaCliente()).getDadosAbaLupaCliente().getComunicacaoVOArray();
						getFaturaOnlineForm().setQtdeContatos(contato.length);
						for (int i = 0; i < contato.length; i++) {
							if (EmailAddress.isValidText(contato[i].getDsContato())) {
								getFaturaOnlineForm().getListaEmails().addNewComunicacaoVO().set(contato[i].copy());
							}
						}
					}
				}
			}

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);

		} catch (Exception e) {
			if (getFaturaOnlineForm().isManutencao()) {
				getFaturaOnlineForm().setMsgErro(SERVICO_INDISPONIVEL);
				getFaturaOnlineForm().setMsgErroDetalhada(e.getMessage());
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(destino);
			} else {
				if (form.isAceitar()) {
					getFaturaOnlineForm().setMsgErro(SERVICO_INDISPONIVEL);
					getFaturaOnlineForm().setMsgErroDetalhada(e.getMessage());
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(destino);
				} else {
					response.setStatus(AjaxError.STATUS_CODE_ERROR, SERVICO_INDISPONIVEL);
					response.setHeader("stackTrace", AjaxError.getStackTrace(e));
					return null;
				}
			}
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="contextoPerdido" path="index.jsp"
	 * @jpf:forward name="success" path="selecaoEmail.jsp"
	 * @jpf:forward name="successAtivacao" path="ativacaoFaturaOnline.do"
	 * @jpf:forward name="successManutencao" path="manutencaoFaturaOnline.do"
	 */
	public ActionForward submitServicos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		FaturaOnlineForm form = (FaturaOnlineForm) formParam;
		String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		String destino = ConstantesCRM.SVAZIO;

		if (ConstantesCRM.SVAZIO.equals(getFaturaOnlineForm().getNrConta())) {
			destino = "contextoPerdido";
			getFaturaOnlineForm().setMsgErro("Fluxo de Conta Online deve ser reiniciado.");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);
		}

		getFaturaOnlineForm().setAvisoFaturaDisponivelPorEmail(form.isAvisoFaturaDisponivelPorEmail());
		getFaturaOnlineForm().setServicoEnvioCodigoBarrasPorSMS(form.isServicoEnvioCodigoBarrasPorSMS());
		getFaturaOnlineForm().setServicoEnvioFaturaPorEmail(form.isServicoEnvioFaturaPorEmail());
		getFaturaOnlineForm().setAceitar(form.isAceitar());

		// Selecionada algumas das opções de envio de e-mail
		if (getFaturaOnlineForm().isAvisoFaturaDisponivelPorEmail() || getFaturaOnlineForm().isServicoEnvioFaturaPorEmail()) {

			destino = ConstantesCRM.SUCCESS;
			// Verificação de e-mails de um cliente PF
			if ("PF".equalsIgnoreCase(getParametrosVO(request).getInTipoPessoa())) {
				getFaturaOnlineForm().setListaEmails(null);
				ComunicacaoVO[] contato = telaInicialFacade.getContato(idUsuario, getParametrosVO(request).getIdPessoaCliente()).getDadosAbaLupaCliente().getComunicacaoVOArray();
				getFaturaOnlineForm().setQtdeContatos(contato.length);
				for (int i = 0; i < contato.length; i++) {
					if (EmailAddress.isValidText(contato[i].getDsContato())) {
						getFaturaOnlineForm().getListaEmails().addNewComunicacaoVO().set(contato[i].copy());
					}
				}
			}

		} else {
			if (getFaturaOnlineForm().isManutencao()) {
				destino = "successManutencao";
			} else {
				destino = "successAtivacao";
			}
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(destino);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="cadastroEmail.jsp"
	 * @jpf:forward name="successFinal" path="selecaoEmail.jsp"
	 */
	public ActionForward manageCadastroEmail(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		FaturaOnlineForm form = (FaturaOnlineForm) formParam;
		String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		String retorno = ConstantesCRM.SVAZIO;
		String destino = ConstantesCRM.SUCCESS;

		if (form.getCadastroEmailPF().getDsContato() == null || ConstantesCRM.SVAZIO.equals(form.getCadastroEmailPF().getDsContato())) {

			TipoComunicacao[] listaTiposComunicacao = faturaFacade.getListaTiposComunicacao(idUsuario, "E-MAIL");
			int contador = 0;
			for (int i = 0; i < listaTiposComunicacao.length; i++) {
				if (listaTiposComunicacao[i].getDsTipoComunicacao().indexOf("GESTOR") < 0) {
					contador++;
				}
			}
			TipoComunicacao[] listaTiposComunicacaoFinal = new TipoComunicacao[contador];
			contador = 0;
			for (int i = 0; i < listaTiposComunicacao.length; i++) {
				if (listaTiposComunicacao[i].getDsTipoComunicacao().indexOf("GESTOR") < 0) {
					listaTiposComunicacaoFinal[contador] = listaTiposComunicacao[i];
					contador++;
				}
			}

			getFaturaOnlineForm().setListaTiposComunicacao(listaTiposComunicacaoFinal);
			ComunicacaoVO cadastro = ComunicacaoVO.Factory.newInstance();
			cadastro.addNewTipoComunicacaoVO().setIdTipoComunicacao(ConstantesCRM.SVAZIO);
			getFaturaOnlineForm().setCadastroEmailPF(cadastro);

		} else {

			LupaClienteVO dadosGravacaoEmail = LupaClienteVO.Factory.newInstance();
			dadosGravacaoEmail.addNewDadosLupaCliente().setIdPessoa(getParametrosVO(request).getIdPessoaCliente());
			dadosGravacaoEmail.addNewDadosAbaLupaCliente().addNewComunicacaoVO().setNrSequencia(String.valueOf(getFaturaOnlineForm().getQtdeContatos() + 1));
			dadosGravacaoEmail.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setDsContato(form.getCadastroEmailPF().getDsContato());
			dadosGravacaoEmail.getDadosAbaLupaCliente().getComunicacaoVOArray(0).addNewTipoComunicacaoVO().setIdTipoComunicacao(form.getIdTipoComunicacao());
			dadosGravacaoEmail.getDadosAbaLupaCliente().getComunicacaoVOArray(0).getTipoComunicacaoVO().setDsTipoComunicacao(request.getParameter("dsTipoComunicacao"));

			retorno = telaInicialFacade.setSalvarNovaComunicacao(idUsuario, dadosGravacaoEmail);
			destino = "successFinal";

			getFaturaOnlineForm().getListaEmails().addNewComunicacaoVO().set(dadosGravacaoEmail.getDadosAbaLupaCliente().getComunicacaoVOArray(0));

		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(destino);

	}

	private ParametrosVO getParametrosVO(HttpServletRequest request) {
		return (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
	}

	public static class FaturaOnlineForm extends ActionForm {

		private static final long serialVersionUID = 6009462526249751498L;

		public FaturaOnlineForm() {
		}

		private String operacao;
		private String nrConta;
		private boolean faturaOnlineAtiva;
		private boolean faturaOnlineEmProcessamento = false;
		private boolean ativacao;
		private boolean aceitar;
		private boolean redirect = false;
		private boolean possuiGestor = false;
		private boolean gestorEmail = false;
		private boolean gestorTelefone = false;
		private boolean manutencao = false;

		private String dsEmailGestor;
		private String nrTelefoneGestor;

		private String msgErro;
		private String msgErroDetalhada;

		private String idTipoComunicacao;

		private boolean clienteDadosZap;

		private boolean avisoFaturaDisponivelPorEmail;
		private boolean servicoEnvioFaturaPorEmail;
		private boolean servicoEnvioCodigoBarrasPorSMS;

		private int qtdeContatos = 0;
		private DadosAbaLupaCliente listaEmails;
		private TipoComunicacao[] listaTiposComunicacao;

		private ComunicacaoVO cadastroEmailPF;

		public String getNrConta() {
			if (this.nrConta == null) {
				this.nrConta = ConstantesCRM.SVAZIO;
			}
			return this.nrConta;
		}

		public void setNrConta(String arg) {
			this.nrConta = arg;
		}

		public String getDsEmailGestor() {
			if (this.dsEmailGestor == null) {
				this.dsEmailGestor = ConstantesCRM.SVAZIO;
			}
			return this.dsEmailGestor;
		}

		public void setDsEmailGestor(String arg) {
			this.dsEmailGestor = arg;
		}

		public String getNrTelefoneGestor() {
			if (this.nrTelefoneGestor == null) {
				this.nrTelefoneGestor = ConstantesCRM.SVAZIO;
			}
			return this.nrTelefoneGestor;
		}

		public void setNrTelefoneGestor(String arg) {
			this.nrTelefoneGestor = arg;
		}

		public String getOperacao() {
			if (this.operacao == null) {
				this.operacao = ConstantesCRM.SVAZIO;
			}
			return this.operacao;
		}

		public void setOperacao(String arg) {
			this.operacao = arg;
		}

		public boolean isFaturaOnlineAtiva() {
			return this.faturaOnlineAtiva;
		}

		public void setFaturaOnlineAtiva(boolean arg) {
			this.faturaOnlineAtiva = arg;
		}

		public boolean isFaturaOnlineEmProcessamento() {
			return this.faturaOnlineEmProcessamento;
		}

		public void setFaturaOnlineEmProcessamento(boolean arg) {
			this.faturaOnlineEmProcessamento = arg;
		}

		public boolean isAtivacao() {
			return this.ativacao;
		}

		public void setAtivacao(boolean arg) {
			this.ativacao = arg;
		}

		public boolean isAceitar() {
			return this.aceitar;
		}

		public void setAceitar(boolean arg) {
			this.aceitar = arg;
		}

		public boolean isRedirect() {
			return this.redirect;
		}

		public void setRedirect(boolean arg) {
			this.redirect = arg;
		}

		public boolean isPossuiGestor() {
			return this.possuiGestor;
		}

		public void setPossuiGestor(boolean arg) {
			this.possuiGestor = arg;
		}

		public boolean isGestorEmail() {
			return this.gestorEmail;
		}

		public void setGestorEmail(boolean arg) {
			this.gestorEmail = arg;
		}

		public boolean isGestorTelefone() {
			return this.gestorTelefone;
		}

		public void setGestorTelefone(boolean arg) {
			this.gestorTelefone = arg;
		}

		public boolean isManutencao() {
			return this.manutencao;
		}

		public void setManutencao(boolean arg) {
			this.manutencao = arg;
		}

		public boolean isClienteDadosZap() {
			return this.clienteDadosZap;
		}

		public void setClienteDadosZap(boolean arg) {
			this.clienteDadosZap = arg;
		}

		public boolean isAvisoFaturaDisponivelPorEmail() {
			return this.avisoFaturaDisponivelPorEmail;
		}

		public void setAvisoFaturaDisponivelPorEmail(boolean arg) {
			this.avisoFaturaDisponivelPorEmail = arg;
		}

		public boolean isServicoEnvioFaturaPorEmail() {
			return this.servicoEnvioFaturaPorEmail;
		}

		public void setServicoEnvioFaturaPorEmail(boolean arg) {
			this.servicoEnvioFaturaPorEmail = arg;
		}

		public boolean isServicoEnvioCodigoBarrasPorSMS() {
			return this.servicoEnvioCodigoBarrasPorSMS;
		}

		public void setServicoEnvioCodigoBarrasPorSMS(boolean arg) {
			this.servicoEnvioCodigoBarrasPorSMS = arg;
		}

		public String getMsgErro() {
			if (this.msgErro == null) {
				this.msgErro = ConstantesCRM.SVAZIO;
			}
			return this.msgErro;
		}

		public void setMsgErro(String arg) {
			this.msgErro = arg;
		}

		public String getMsgErroDetalhada() {
			if (this.msgErroDetalhada == null) {
				this.msgErroDetalhada = ConstantesCRM.SVAZIO;
			}
			return this.msgErroDetalhada;
		}

		public void setMsgErroDetalhada(String arg) {
			this.msgErroDetalhada = arg;
		}

		public int getQtdeContatos() {
			return this.qtdeContatos;
		}

		public void setQtdeContatos(int arg) {
			this.qtdeContatos = arg;
		}

		public DadosAbaLupaCliente getListaEmails() {
			if (this.listaEmails == null) {
				this.listaEmails = DadosAbaLupaCliente.Factory.newInstance();
			}
			return this.listaEmails;
		}

		public void setListaEmails(DadosAbaLupaCliente arg) {
			this.listaEmails = arg;
		}

		public TipoComunicacao[] getListaTiposComunicacao() {
			if (this.listaTiposComunicacao == null) {
				this.listaTiposComunicacao = new TipoComunicacao[0];
			}
			return this.listaTiposComunicacao;
		}

		public void setListaTiposComunicacao(TipoComunicacao[] arg) {
			this.listaTiposComunicacao = arg;
		}

		public ComunicacaoVO getCadastroEmailPF() {
			if (this.cadastroEmailPF == null) {
				this.cadastroEmailPF = ComunicacaoVO.Factory.newInstance();
				this.cadastroEmailPF.addNewTipoComunicacaoVO().setIdTipoComunicacao(ConstantesCRM.SVAZIO);
			}
			return this.cadastroEmailPF;
		}

		public void setCadastroEmailPF(ComunicacaoVO arg) {
			this.cadastroEmailPF = arg;
		}

		public String getIdTipoComunicacao() {
			if (this.idTipoComunicacao == null) {
				this.idTipoComunicacao = ConstantesCRM.SVAZIO;
			}
			return this.idTipoComunicacao;
		}

		public void setIdTipoComunicacao(String arg) {
			this.idTipoComunicacao = arg;
		}

	}

}