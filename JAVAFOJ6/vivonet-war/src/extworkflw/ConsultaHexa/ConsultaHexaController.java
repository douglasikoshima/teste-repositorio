package extworkflw.ConsultaHexa;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.ConsultaHexaVODocument.ConsultaHexaVO;
import br.com.vivo.fo.cliente.vo.MonitorarHexaVODocument.MonitorarHexaVO;
import br.com.vivo.fo.cliente.vo.MonitorarHexaVODocument.MonitorarHexaVO.FiltroHexa;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.cliente.consultaHexa.ConsultaHexaFacade;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ConsultaHexaController extends AbstractAction {

	private static final long serialVersionUID = -573237414835189194L;

	@EJB
	private ConsultaHexaFacade consultaHexaFacadeControl;

	private String user = null;
	private String idLinha = null;
	private MonitorarHexaForm monitorarHexaForm;
	private MonitorarHexaVO monitorarHexaVO;
	private ConsultaHexaForm consultaHexaForm;
	private ConsultaHexaVO consultaHexaVO;

	private static Logger log = new Logger("extworkflw");

	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);

		} else if ("consultaHexa".equals(mapping.getParameter())) {
			return consultaHexa(mapping, form, request, response);

		} else if ("monitorarHexa".equals(mapping.getParameter())) {
			return monitorarHexa(mapping, form, request, response);

		} else if ("voltar".equals(mapping.getParameter())) {
			return voltar(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="monitorarHexa.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("ConsultaHexaController:begin(" + user + ") >> INICIALIZADO");

		monitorarHexaForm = new MonitorarHexaForm();
		monitorarHexaVO = MonitorarHexaVO.Factory.newInstance();
		request.setAttribute("piConsHexa", monitorarHexaVO.getConsultasHexaArray());
		monitorarHexaForm.setInInicio(ConstantesCRM.STRUE);

		log.debug("ConsultaHexaController:begin(" + user + ") >> FINALIZADO");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="consultahexa.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward consultaHexa(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			ConsultaHexaForm form = (ConsultaHexaForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("ConsultaHexaController:consultaHexa(" + user + ") >> INICIALIZADO");

			idLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdLinha();
			log.debug("ConsultaHexaController:consultaHexa(" + user + ") >> idLinha: " + idLinha);

			String idTipoLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha();

			// Monta VO de Consulta Hexa
			consultaHexaVO = ConsultaHexaVO.Factory.newInstance();
			consultaHexaVO.setEnvio(ConsultaHexaVO.Envio.Factory.newInstance());
			consultaHexaVO.getEnvio().setIdLinha(idLinha); // recuperar ID linha
			consultaHexaVO.getEnvio().setIdPessoaUsuario(user);

			if (form.getConsHexa() == null) {
				log.debug("ConsultaHexaController:consultaHexa(" + user + ") >> Primeira execução ");

				// Primeira execução. Busca só batimento entre billing e HLR.
				consultaHexaForm = new ConsultaHexaForm();

				// Nao permite consulta caso seja POSCHIP ou PRECHIP
				if (ConstantesCRM.SFIVE.equals(idTipoLinha) || ConstantesCRM.SSIX.equals(idTipoLinha) || ConstantesCRM.SSEVEN.equals(idTipoLinha)) {
					consultaHexaForm.setResultadoBatimento("Consulta de batimento entre legados não é possivel para linhas WCDMA.");
					consultaHexaForm.setInErro(ConstantesCRM.STRUE);

				} else {
					consultaHexaVO = consultaHexaFacadeControl.getConsultaHexa(consultaHexaVO, user);

					if (consultaHexaVO.getRecebimento().getErro() != null) {
						consultaHexaForm.setResultadoBatimento("Houve um erro na recuperação do hexa em um dos sistemas legados. Descrição:\n" + consultaHexaVO.getRecebimento().getErro());
						consultaHexaForm.setInErro(ConstantesCRM.STRUE);
					} else if (consultaHexaVO.getRecebimento().getBillingHLR()) {
						consultaHexaForm.setResultadoBatimento("O batimento entre os sistemas legado confere.");
					} else {
						consultaHexaForm.setResultadoBatimento("O batimento entre os sistemas legado não confere.");
					}
				}

			} else {
				log.debug("ConsultaHexaController:consultaHexa(" + user + ") >> Consulta de hexa pelo usuario ");
				if (consultaHexaForm == null) {
					consultaHexaForm = new ConsultaHexaForm();
				}

				// Usuário já preencheu os dados para consulta de Hexa.
				consultaHexaVO.getEnvio().setCdHexaFront(form.getConsHexa());
				if (ConstantesCRM.SFIVE.equals(idTipoLinha) || ConstantesCRM.SSIX.equals(idTipoLinha) || ConstantesCRM.SSEVEN.equals(idTipoLinha)) {
					consultaHexaForm.setResultadoBatimento("Recuperação de hexa não é possivel para linhas WCDMA.");
					consultaHexaForm.setInErro(ConstantesCRM.STRUE);

				} else {
					consultaHexaVO = consultaHexaFacadeControl.getConsultaHexa(consultaHexaVO, user);
					// Monta o form de saída
					if (consultaHexaVO.getRecebimento().getErro() != null) {
						log.info("ConsultaHexaController:consultaHexa(" + user + ") >> Houve um erro na recuperação do hexa em um dos sistemas legados. ");
						consultaHexaForm.setResultadoBatimento("Houve um erro na recuperação do hexa em um dos sistemas legados. Descrição:\n" + consultaHexaVO.getRecebimento().getErro());
						consultaHexaForm.setInErro(ConstantesCRM.STRUE);
					} else if (consultaHexaVO.getRecebimento().getBillingFront() && consultaHexaVO.getRecebimento().getHLRFront()) {
						log.info("ConsultaHexaController:consultaHexa(" + user + ") >> O hexa consultado confere no billing e HLR.");
						consultaHexaForm.setResultadoBatimento("O hexa consultado confere no billing e HLR.");
						consultaHexaForm.setInErro(ConstantesCRM.SFALSE);
					} else if (!consultaHexaVO.getRecebimento().getBillingFront() && consultaHexaVO.getRecebimento().getHLRFront()) {
						log.info("ConsultaHexaController:consultaHexa(" + user + ") >> O hexa consultado não confere no billing, mas confere no HLR.");
						consultaHexaForm.setResultadoBatimento("O hexa consultado não confere no billing, mas confere no HLR.");
						consultaHexaForm.setInErro(ConstantesCRM.SFALSE);
					} else if (consultaHexaVO.getRecebimento().getBillingFront() && !consultaHexaVO.getRecebimento().getHLRFront()) {
						log.info("ConsultaHexaController:consultaHexa(" + user + ") >> O hexa consultado não confere no HLR, mas confere no billing.");
						consultaHexaForm.setResultadoBatimento("O hexa consultado não confere no HLR, mas confere no billing.");
						consultaHexaForm.setInErro(ConstantesCRM.SFALSE);
					} else {
						log.info("ConsultaHexaController:consultaHexa(" + user + ") >> O hexa consultado não confere no billing e não confere no HLR.");
						consultaHexaForm.setResultadoBatimento("O hexa consultado não confere no billing e não confere no HLR.");
						consultaHexaForm.setInErro(ConstantesCRM.SFALSE);
					}
				}
				consultaHexaForm.setPesquisaRealizada(ConstantesCRM.STRUE);
			}

			log.debug("ConsultaHexaController:consultaHexa(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			consultaHexaForm.setResultadoBatimento("Houve um erro na recuperação do hexa. Descrição:\nErro interno!");
			consultaHexaForm.setInErro(ConstantesCRM.STRUE);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="monitorarHexa.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward monitorarHexa(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			MonitorarHexaForm form = (MonitorarHexaForm) formParam;

			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("ConsultaHexaController:monitorarHexa(" + user + ") >> INICIALIZADO");

			monitorarHexaVO = MonitorarHexaVO.Factory.newInstance();
			monitorarHexaVO.setFiltroHexa(form.getFiltroHexa());

			log.debug("ConsultaHexaController:monitorarHexa(" + user + ") >> giltroHexa: " + form.getFiltroHexa());
			monitorarHexaVO = consultaHexaFacadeControl.getMonitoramentoHexa(monitorarHexaVO, user);

			request.setAttribute("piConsHexa",monitorarHexaVO.getConsultasHexaArray());
			monitorarHexaForm.setFiltroHexa(form.getFiltroHexa());
			monitorarHexaForm.setIdErro(monitorarHexaVO.getIdErro());
			monitorarHexaForm.setInInicio(ConstantesCRM.SFALSE);

			log.debug("ConsultaHexaController:monitorarHexa(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/ConsultaHexa/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" return-action="ConsultaHexaDone"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward voltar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class MonitorarHexaForm extends ActionForm {

		private static final long serialVersionUID = -2553644583499685197L;

		private String inInicio;
		private String idErro = ConstantesCRM.SVAZIO;
		private FiltroHexa filtroHexa;

		public MonitorarHexaForm() {
			this.filtroHexa = FiltroHexa.Factory.newInstance();
		}

		public void setFiltroHexa(FiltroHexa filtroHexa) {
			this.filtroHexa = filtroHexa;
		}

		public FiltroHexa getFiltroHexa() {
			return this.filtroHexa;
		}

		public void setIdErro(String idErro) {
			this.idErro = idErro;
		}

		public String getIdErro() {
			return this.idErro;
		}

		public void setInInicio(String inInicio) {
			this.inInicio = inInicio;
		}

		public String getInInicio() {
			return this.inInicio;
		}
	}

	public MonitorarHexaForm getMonitorarHexaForm() {
		return (this.monitorarHexaForm);
	}

	public static class ConsultaHexaForm extends ActionForm {

		private static final long serialVersionUID = 2483013541794825393L;

		private String pesquisaRealizada = ConstantesCRM.SVAZIO;
		private String inErro = ConstantesCRM.SFALSE;
		private String resultadoBatimento;
		private String consHexa;

		public void setConsHexa(String consHexa) {
			this.consHexa = consHexa;
		}

		public String getConsHexa() {
			return this.consHexa;
		}

		public void setResultadoBatimento(String resultadoBatimento) {
			this.resultadoBatimento = resultadoBatimento;
		}

		public String getResultadoBatimento() {
			return this.resultadoBatimento;
		}

		public void setInErro(String inErro) {
			this.inErro = inErro;
		}

		public String getInErro() {
			return this.inErro;
		}

		public void setPesquisaRealizada(String pesquisaRealizada) {
			this.pesquisaRealizada = pesquisaRealizada;
		}

		public String getPesquisaRealizada() {
			return this.pesquisaRealizada;
		}
	}

	public ConsultaHexaForm getConsultaHexaForm() {
		return (this.consultaHexaForm);
	}
}
