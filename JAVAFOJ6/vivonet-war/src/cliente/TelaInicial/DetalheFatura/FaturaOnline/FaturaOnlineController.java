package cliente.TelaInicial.DetalheFatura.FaturaOnline;

import java.io.BufferedOutputStream;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.ctrls.cliente.contaonline.ws.MC.Conta.Conta;
import br.com.vivo.ctrls.cliente.contaonline.ws.MC.Geral.ErroInfo;
import br.com.vivo.ctrls.cliente.contaonline.ws.SN.Conta.AlterarStatusFaturaRequest;
import br.com.vivo.ctrls.cliente.contaonline.ws.SN.Conta.AlterarStatusFaturaRequestCdTipoPessoa;
import br.com.vivo.ctrls.cliente.contaonline.ws.SN.Conta.ConsultarStatusFaturaRequest;
import br.com.vivo.ctrls.cliente.contaonline.ws.SN.Conta.ContaPortTypeProxy;
import br.com.vivo.fo.cliente.ClienteUtils;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.commons.utils.GetParametro;
import br.com.vivo.fo.constantes.ConstantesCRM;

import com.indracompany.actions.AbstractAction;
import commons.errors.AjaxError;

public class FaturaOnlineController extends AbstractAction {

	private static final long serialVersionUID = -6083960439946719760L;

	protected global.Global globalApp = new global.Global();

	FaturaOnlineForm faturaOnlineForm;

	@EJB
	private br.com.vivo.fo.ctrls.cliente.telaInicial.TelaInicialFacade telaInicialFacadeControl;

	private static final String SERVICO_INDISPONIVEL = "Serviço indisponível no momento. Por favor, tente mais tarde.";
	private static final String CONTAONLINE_ENDPOINT = "CONTAONLINE_CONTA_ENDPOINT";

	@EJB
	private br.com.vivo.fo.ctrls.cliente.telaInicial.TelaInicialFacade telaInicialFacade;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.registrarContato.RegistrarContatoFacade registrarContatoFacade;

	@EJB
	private br.com.vivo.fo.ctrls.VOLTAV.fatura.FaturaFacade faturaFacade;

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
		} else if ("manageContaOnline".equals(mapping.getParameter())) {
			return manageContaOnline(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {

			String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
			getFaturaOnlineForm().setNrConta(getParametrosVO(request).getNrConta());
			getFaturaOnlineForm().setMsgErro(ConstantesCRM.SVAZIO);

			if (faturaFacade.isHierarquia(idUsuario, getFaturaOnlineForm().getNrConta())) {
				getFaturaOnlineForm().setMsgErro("Serviço indisponível para clientes com hierarquia de contas. Favor orientar o gestor. Fatura Online não será ativada.");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}

			ConsultarStatusFaturaRequest consultarStatusSMSRequest = new ConsultarStatusFaturaRequest();
			consultarStatusSMSRequest.setNrConta(getFaturaOnlineForm().getNrConta());

			ContaPortTypeProxy contaPortTypeProxy = new ContaPortTypeProxy();

			String url = GetParametro.getInstace().getParametroVO(idUsuario, CONTAONLINE_ENDPOINT).getDsValorParametro();
			if (ConstantesCRM.SVAZIO.equals(url)) {
				getFaturaOnlineForm().setMsgErro("Parâmetro necessário não encontrado: " + CONTAONLINE_ENDPOINT);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}

			contaPortTypeProxy.setEndpoint(url);
			Conta conta = contaPortTypeProxy.consultarStatusFatura(consultarStatusSMSRequest);

			getFaturaOnlineForm().setFaturaOnlineAtiva(conta.getEnvioEletronicoFatura().booleanValue());
			getFaturaOnlineForm().setAtivacao(conta.getEnvioEletronicoFatura().booleanValue() == true ? false : true);

		} catch (Exception e) {
			getFaturaOnlineForm().setMsgErro(SERVICO_INDISPONIVEL);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 */
	public ActionForward manageContaOnline(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		FaturaOnlineForm form = (FaturaOnlineForm) formParam;
		String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		StringBuffer xmlOUT = new StringBuffer("<xmlObject>");
		String cdParametro;
		String msgRetorno = ConstantesCRM.SVAZIO;
		getFaturaOnlineForm().setMsgErro(ConstantesCRM.SVAZIO);

		try {

			// Cliente aceitou ativação ou desativação de Fatura Online
			if (form.isAceitar()) {

				AlterarStatusFaturaRequest alterarStatusFaturaRequest = new AlterarStatusFaturaRequest();
				alterarStatusFaturaRequest.setIndEnvio(getFaturaOnlineForm().isAtivacao());
				alterarStatusFaturaRequest.setNrConta(getFaturaOnlineForm().getNrConta());
				alterarStatusFaturaRequest.setReasonCode("FO");

				if ("PJ".equals(getParametrosVO(request).getInTipoPessoa())) {
					alterarStatusFaturaRequest.setCdTipoPessoa(AlterarStatusFaturaRequestCdTipoPessoa.PJ);
				} else {
					alterarStatusFaturaRequest.setCdTipoPessoa(AlterarStatusFaturaRequestCdTipoPessoa.PF);
				}

				String url = GetParametro.getInstace().getParametroVO(idUsuario, CONTAONLINE_ENDPOINT).getDsValorParametro();
				if (ConstantesCRM.SVAZIO.equals(url)) {
					throw new Exception("Parâmetro necessário não encontrado: " + CONTAONLINE_ENDPOINT);
				}

				ContaPortTypeProxy contaPortTypeProxy = new ContaPortTypeProxy();
				contaPortTypeProxy.setEndpoint(url);
				Conta conta = contaPortTypeProxy.alterarStatusFatura(alterarStatusFaturaRequest);

				// Ativação de Fatura Online
				if (getFaturaOnlineForm().isAtivacao()) {
					cdParametro = "NMPATH_FATURAONLINE_ATIVACAO_ACEITOU";
					msgRetorno = "Ativação de Fatura Online realizada com sucesso.";
				}
				// Desativação de Fatura Online
				else {
					cdParametro = "NMPATH_FATURAONLINE_DESATIVACAO_ACEITOU";
					msgRetorno = "Desativação de Fatura Online realizada com sucesso.";
				}

			}

			// Cliente não aceitou ativação ou desativação de Fatura Online
			else {
				if (getFaturaOnlineForm().isAtivacao()) {
					cdParametro = "NMPATH_FATURAONLINE_ATIVACAO_NAOACEITOU";
				} else {
					cdParametro = "NMPATH_FATURAONLINE_DESATIVACAO_NAOACEITOU";
				}
			}

			// Registro de palitagem apenas se valor do path do contato for obtido na APOIO.PARAMETRO
			if (!cdParametro.equals(ConstantesCRM.SVAZIO)) {

				String nrProtocoloSessao = request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) != null ? (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) : ConstantesCRM.SVAZIO;

				String nrProtocolo = ClienteUtils.registrarPalitagem(idUsuario, getParametrosVO(request), getParametrosVO(request).getIdLinha(), getParametrosVO(request).getNrLinha(), GetParametro.getInstace().getParametroVO(idUsuario, cdParametro).getDsValorParametro(), ConstantesCRM.SVAZIO);
				xmlOUT.append(nrProtocolo);

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

			response.setContentType(ConstantesCRM.SContentType);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			bufferedOutputStream.write(xmlOUT.toString().getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
			return null;

		} catch (ErroInfo e) {
			response.setStatus(AjaxError.STATUS_CODE_ERROR, e.getDescricao());
			response.setHeader("stackTrace", AjaxError.getStackTrace(e));

		} catch (Exception e) {
			response.setStatus(AjaxError.STATUS_CODE_ERROR, SERVICO_INDISPONIVEL);
			response.setHeader("stackTrace", AjaxError.getStackTrace(e));
		}
		return null;
	}

	private ParametrosVO getParametrosVO(HttpServletRequest request) {
		return (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
	}

	public static class FaturaOnlineForm extends ActionForm {

		private static final long serialVersionUID = -4248689796541016989L;

		public FaturaOnlineForm() {
		}

		private String nrConta;
		private boolean faturaOnlineAtiva;
		private boolean ativacao;
		private boolean aceitar;
		private String msgErro;

		public String getNrConta() {
			if (this.nrConta == null) {
				this.nrConta = ConstantesCRM.SVAZIO;
			}
			return this.nrConta;
		}

		public void setNrConta(String arg) {
			this.nrConta = arg;
		}

		public boolean isFaturaOnlineAtiva() {
			return this.faturaOnlineAtiva;
		}

		public void setFaturaOnlineAtiva(boolean arg) {
			this.faturaOnlineAtiva = arg;
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

		public String getMsgErro() {
			if (this.msgErro == null) {
				this.msgErro = ConstantesCRM.SVAZIO;
			}
			return this.msgErro;
		}

		public void setMsgErro(String arg) {
			this.msgErro = arg;
		}

	}

}