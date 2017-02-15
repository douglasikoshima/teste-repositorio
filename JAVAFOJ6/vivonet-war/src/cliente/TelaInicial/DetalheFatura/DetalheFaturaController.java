package cliente.TelaInicial.DetalheFatura;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.zip.GZIPInputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.ejb.EJB;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.ws.BindingProvider;

import noNamespace.ARGDocument;
import noNamespace.ARGDocument.ARG;
import noNamespace.MsgDocument;

import org.apache.axis.AxisFault;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.xmlbeans.XmlException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import br.com.vivo.fo.cliente.ClienteUtils;
import br.com.vivo.fo.cliente.vo.LFAjustesDocument.LFAjustes;
import br.com.vivo.fo.cliente.vo.LFCobrancaDocument.LFCobranca;
import br.com.vivo.fo.cliente.vo.LFContaDetalhadaDocument.LFContaDetalhada;
import br.com.vivo.fo.cliente.vo.LFContasFaturamentoDocument.LFContasFaturamento;
import br.com.vivo.fo.cliente.vo.LFFaturamentoDocument.LFFaturamento;
import br.com.vivo.fo.cliente.vo.LFLinhasDocument.LFLinhas;
import br.com.vivo.fo.cliente.vo.LFPagamentoDocument.LFPagamento;
import br.com.vivo.fo.cliente.vo.LupaFaturamentoPosVODocument.LupaFaturamentoPosVO;
import br.com.vivo.fo.cliente.vo.LupaFaturamentoPosVODocument.LupaFaturamentoPosVO.Busca;
import br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.commons.utils.GetParametro;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.ListaDadosVODocument.ListaDadosVO;
import br.com.vivo.vol.dados.vo.ConsultarEmailsVODocument.ConsultarEmailsVO;
import br.com.vivo.vol.dados.vo.ConsultarTiposEmailVODocument.ConsultarTiposEmailVO;
import br.com.vivo.www.MC.Catalogo.Plano;
import br.com.vivo.www.MC.Geral.ErroInfo;
import br.com.vivo.www.SN.PlanoSimulado.ParametrosBuscarPlanoAtual;
import br.com.vivo.www.SN.PlanoSimulado.ParametrosConsultaPlanosSimulados;
import br.com.vivo.www.SN.PlanoSimulado.ParametrosDetalhesPlanoSimulado;
import br.com.vivo.www.SN.PlanoSimulado.PlanoSimuladoPortTypeProxy;
import br.com.vivo.www.SN.PlanoSimulado.SecurityHeaderHelper;
import cliente.URLErro;
import cliente.TelaInicial.DetalheFatura.formBeans.FiltrosFaturamentoForm;

import com.indracompany.actions.AbstractAction;
import com.indracompany.ws.docrequesterservice.DocumentRequesterProxy;
import com.indracompany.ws.faturaservice.BuscarHistoricoFaturaComFiltroRequest;
import com.indracompany.ws.faturaservice.Chamadas;
import com.indracompany.ws.faturaservice.ConsultarHistoricoRequest;
import com.indracompany.ws.faturaservice.ErroInfoFault;
import com.indracompany.ws.faturaservice.Fatura;
import com.indracompany.ws.faturaservice.FaturaPortType;
import com.indracompany.ws.faturaservice.Fatura_Service;
import com.indracompany.ws.faturaservice.Faturas;
import com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe;
import commons.errors.AjaxError;
import commons.errors.FormError;

@SuppressWarnings({"unused","deprecation","rawtypes","unchecked"})
public class DetalheFaturaController extends AbstractAction {

	private static final long serialVersionUID = -5898464320448643825L;

	@EJB
	private br.com.vivo.fo.ctrls.cliente.telaInicial.detalheFatura.DetalheFaturaFacade detalheFaturaFacade;

	@EJB
	private br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ManterAgendamentoVIPFacade controlManterAgendamento;

	@EJB
	private br.com.vivo.fo.ctrls.cliente.telaInicial.TelaInicialFacade telaInicialFacadeControl;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.registrarContato.RegistrarContatoFacade registrarContatoFacade;

	private static Logger log = new Logger("telainicial");
	// Variáveis e objetos
	private LupaFaturamentoPosForm lupaFaturamentoPosForm = null;
	private LupaFaturamentoPosForm detalheForm = null;
	private String user = null;
	private String idPessoa = null;
	private static Logger logLupaFaturamento = new Logger("lupafatura");
	protected global.Global globalApp = new global.Global();

	private static final String IMAGEM_ENDPOINT = "CONTAONLINE_IMAGEM_ENDPOINT";
	private static final String FATURA_ENDPOINT = "CONTAONLINE_FATURA_ENDPOINT";
	private static final String BOLETO_DATA_PITNEY1 = "BOLETO_DATA_PITNEY1";

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("pesquisar".equals(mapping.getParameter())) {
			return pesquisar(mapping, form, request, response);
		} else if ("loadFatura".equals(mapping.getParameter())) {
			return loadFatura(mapping, form, request, response);
		} else if ("loadAjustes".equals(mapping.getParameter())) {
			return loadAjustes(mapping, form, request, response);
		} else if ("loadPagamento".equals(mapping.getParameter())) {
			return loadPagamento(mapping, form, request, response);
		} else if ("loadCobranca".equals(mapping.getParameter())) {
			return loadCobranca(mapping, form, request, response);
		} else if ("loadDetalhada".equals(mapping.getParameter())) {
			return loadDetalhada(mapping, form, request, response);
		} else if ("loadDetalhadaLoad".equals(mapping.getParameter())) {
			return loadDetalhadaLoad(mapping, form, request, response);
		} else if ("faturaDetalhada".equals(mapping.getParameter())) {
			return faturaDetalhada(mapping, form, request, response);
		} else if ("faturaDetalhadaPJ".equals(mapping.getParameter())) {
			return faturaDetalhadaPJ(mapping, form, request, response);
		} else if ("buscaNumeroOrigem".equals(mapping.getParameter())) {
			return buscaNumeroOrigem(mapping, form, request, response);
		} else if ("pesquisaFaturaDetalhada".equals(mapping.getParameter())) {
			return pesquisaFaturaDetalhada(mapping, form, request, response);
		} else if ("pesquisaFaturaDetalhadaPJ".equals(mapping.getParameter())) {
			return pesquisaFaturaDetalhadaPJ(mapping, form, request, response);
		} else if ("consultarImagemFatura".equals(mapping.getParameter())) {
			return consultarImagemFatura(mapping, form, request, response);
		} else if ("loadSimulador".equals(mapping.getParameter())) {
			return loadSimulador(mapping, form, request, response);
		} else if ("loadDetalheSimulador".equals(mapping.getParameter())) {
			return loadDetalheSimulador(mapping, form, request, response);
		} else if ("loadContatoEnviaEmailSimulador".equals(mapping.getParameter())) {
			return loadContatoEnviaEmailSimulador(mapping, form, request, response);
		} else if ("loadEnviaMailSimulador".equals(mapping.getParameter())) {
			return loadEnviaMailSimulador(mapping, form, request, response);
		} else if ("mostraLinhas".equals(mapping.getParameter())) {
			return mostraLinhas(mapping, form, request, response);
		} else if ("loadLinhaIntraGrupo".equals(mapping.getParameter())) {
			return loadLinhaIntraGrupo(mapping, form, request, response);
		} else if ("loadEstimativa".equals(mapping.getParameter())) {
			return loadEstimativa(mapping, form, request, response);
		} else if ("voltar".equals(mapping.getParameter())) {
			return voltar(mapping, form, request, response);
		} else if ("loadLupaFaturamento".equals(mapping.getParameter())) {
			return loadLupaFaturamento(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="loadLupaFaturamento.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			log.debug("DetalheFaturaController:begin()");
			user = ConstantesCRM.getCurrentUser(request.getSession());
			idPessoa = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaCliente();
			logLupaFaturamento.debug("DetalheFaturaController:begin(" + user + ") >> INICIALIZADO -> idPessoa: " + idPessoa);
			lupaFaturamentoPosForm = new LupaFaturamentoPosForm();
			logLupaFaturamento.debug("DetalheFaturaController:begin(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			logLupaFaturamento.error("DetalheFaturaController:begin(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="lupaFaturamento.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward pesquisar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			LupaFaturamentoPosForm form = (LupaFaturamentoPosForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logLupaFaturamento.debug("DetalheFaturaController:pesquisar(" + user + ") >> INICIALIZADO");
			String idCliente = ConstantesCRM.SVAZIO;
			if (((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)) != null) {
				idCliente = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaCliente();
			}

			// Monta VO e faz pesquisa via TUXEDO.
			lupaFaturamentoPosForm.getLupaFaturamentoPosVO().setBusca(form.getLupaFaturamentoPosVO().getBusca());
			if (lupaFaturamentoPosForm.getLupaFaturamentoPosVO().getLFContasFaturamentoArray() != null && lupaFaturamentoPosForm.getLupaFaturamentoPosVO().getLFContasFaturamentoArray().length > 0) {
				lupaFaturamentoPosForm.getLupaFaturamentoPosVO().setLFContasFaturamentoArray(new LFContasFaturamento[0]);
				lupaFaturamentoPosForm.setLinhasArray(new LFLinhas[0]);
			}
			lupaFaturamentoPosForm.getLupaFaturamentoPosVO().setLFContasFaturamentoArray(detalheFaturaFacade.buscaContaLinha(form.getLupaFaturamentoPosVO(), idCliente, user).getLFContasFaturamentoArray());
			lupaFaturamentoPosForm.setIdContaSelecionada("-1");
			lupaFaturamentoPosForm.setNdxContaSelecionada("-1");
			if (request.getParameter("inicio") != null && request.getParameter("inicio").equalsIgnoreCase(ConstantesCRM.STRUE)) {
				lupaFaturamentoPosForm.setInInicio(ConstantesCRM.STRUE);
			}
			logLupaFaturamento.debug("DetalheFaturaController:pesquisar(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (Exception e) {
			logLupaFaturamento.error("DetalheFaturaController:pesquisar(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);

			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

    /**
     * @jpf:action
     * @jpf:forward name="success" path="abaFaturamento.jsp"
     * @jpf:forward name="erro" path="erroAtlys.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
	public ActionForward loadFatura(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			LupaFaturamentoPosForm form = (LupaFaturamentoPosForm) formParam;
			detalheForm = new LupaFaturamentoPosForm();

			user = ConstantesCRM.getCurrentUser(request.getSession());
			LupaFaturamentoPosVO lupaFaturTux;
			logLupaFaturamento.debug("DetalheFaturaController:loadFatura(" + user + ") >> INICIALIZADO");
			// Monta uma VO só com dados de busca e envia ao TUXEDO.
			String idContaSO = montaBuscaDetalhe();
			String destino = ConstantesCRM.SUCCESS;
			int idTipoLinha = Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());
			logLupaFaturamento.debug("DetalheFaturaController:loadFatura(" + user + ") >> idContaSO: " + idContaSO);
			// Vai no TUXEDO.
			try {
				lupaFaturTux = detalheFaturaFacade.buscaDetalhes(idContaSO, "getHistoricoFaturamentos", user, idTipoLinha);
				lupaFaturamentoPosForm.getLupaFaturamentoPosVO().setLFFaturamentoArray(lupaFaturTux.getLFFaturamentoArray());
			} catch (Exception e) {
				logLupaFaturamento.error("DetalheFaturaController:loadFatura(" + user + ") - [" + e.getMessage() + "]", e);
				detalheForm.setMsgErro("O sistema de FATURAMENTO está temporariamente fora de serviço");
				destino = "erro";
			}

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);
		} catch (Exception e) {
			logLupaFaturamento.error("DetalheFaturaController:loadFatura(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);

			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaAjustes.jsp"
	 * @jpf:forward name="erro" path="erroAtlys.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadAjustes(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			LupaFaturamentoPosForm form = (LupaFaturamentoPosForm) formParam;
			logLupaFaturamento.debug("DetalheFaturaController:loadAjustes(" + user + ") >> INICIALIZADO");
			LupaFaturamentoPosVO lupaFaturTux;
			// Monta uma VO só com dados de busca e envia ao TUXEDO.
			String idContaSO = montaBuscaDetalhe();
			String destino = ConstantesCRM.SUCCESS;
			int idTipoLinha = Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());
			logLupaFaturamento.debug("DetalheFaturaController:loadFatura(" + user + ") >> idContaSO: " + idContaSO);
			logLupaFaturamento.debug("DetalheFaturaController:loadFatura(" + user + ") >> idTipoLinha: " + idTipoLinha);
			// Vai no TUXEDO.
			try {
				lupaFaturTux = detalheFaturaFacade.buscaDetalhes(idContaSO, "getAjustes", user, idTipoLinha);
				lupaFaturamentoPosForm.getLupaFaturamentoPosVO().setLFAjustesArray(lupaFaturTux.getLFAjustesArray());
			} catch (Exception e) {
				logLupaFaturamento.error("DetalheFaturaController:loadAjustes(" + user + ") - [" + e.getMessage() + "]", e);
				destino = "erro";
			}
			logLupaFaturamento.debug("DetalheFaturaController:loadAjustes(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);
		} catch (Exception e) {
			logLupaFaturamento.error("DetalheFaturaController:loadAjustes(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);

			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaPagamento.jsp"
	 * @jpf:forward name="erro" path="erroAtlys.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadPagamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			LupaFaturamentoPosForm form = (LupaFaturamentoPosForm) formParam;
			LupaFaturamentoPosVO lupaFaturTux;
			// Monta uma VO só com dados de busca e envia ao TUXEDO.
			String idContaSO = montaBuscaDetalhe();
			String destino = ConstantesCRM.SUCCESS;
			int idTipoLinha = Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());
			logLupaFaturamento.debug("DetalheFaturaController:loadPagamento(" + user + ") >> idContaSO: " + idContaSO);
			logLupaFaturamento.debug("DetalheFaturaController:loadPagamento(" + user + ") >> idTipoLinha: " + idTipoLinha);
			// Vai no TUXEDO.
			try {
				lupaFaturTux = detalheFaturaFacade.buscaDetalhes(idContaSO, "getPagamentos", user, idTipoLinha);
				lupaFaturamentoPosForm.getLupaFaturamentoPosVO().setLFPagamentoArray(lupaFaturTux.getLFPagamentoArray());
			} catch (Exception e) {
				logLupaFaturamento.error("DetalheFaturaController:loadPagamento(" + user + ") - [" + e.getMessage() + "]", e);
				destino = "erro";
			}

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);
		} catch (Exception e) {
			logLupaFaturamento.error("DetalheFaturaController:loadPagamento(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);

			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaCobranca.jsp"
	 * @jpf:forward name="erro" path="erroAtlys.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadCobranca(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			LupaFaturamentoPosForm form = (LupaFaturamentoPosForm) formParam;
			LupaFaturamentoPosVO lupaFaturTux;
			// Monta uma VO só com dados de busca e envia ao TUXEDO.
			String idContaSO = montaBuscaDetalhe();
			String destino = ConstantesCRM.SUCCESS;
			int idTipoLinha = Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());
			logLupaFaturamento.debug("DetalheFaturaController:loadCobranca(" + user + ") >> idContaSO: " + idContaSO);
			logLupaFaturamento.debug("DetalheFaturaController:loadCobranca(" + user + ") >> idTipoLinha: " + idTipoLinha);
			// Vai no TUXEDO.
			try {
				lupaFaturTux = detalheFaturaFacade.buscaDetalhes(idContaSO, "getInfoContaCobranca", user, idTipoLinha);
				lupaFaturamentoPosForm.getLupaFaturamentoPosVO().setLFCobrancaArray(lupaFaturTux.getLFCobrancaArray());
			} catch (Exception e) {
				logLupaFaturamento.error("DetalheFaturaController:loadCobranca(" + user + ") - [" + e.getMessage() + "]", e);
				destino = "erro";
			}
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);

		} catch (Exception e) {
			logLupaFaturamento.error("DetalheFaturaController:loadCobranca(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);

			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaDetalhadaLoader.jsp"
	 * @jpf:forward name="erro" path="erroAtlys.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadDetalhada(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		LupaFaturamentoPosForm form = (LupaFaturamentoPosForm) formParam;
		String ddd = ConstantesCRM.SVAZIO;
		String nrLinha = ConstantesCRM.SVAZIO;
		String destino = ConstantesCRM.SUCCESS;
		String date = ConstantesCRM.SVAZIO;
		logLupaFaturamento.debug("DetalheFaturaController:loadDetalhada() >> INICIALIZADO");
		try {
			detalheForm = new LupaFaturamentoPosForm();
			detalheForm.setNdxContaSelecionada(form.getNdxContaSelecionada());
			String idContaSO = lupaFaturamentoPosForm.getLupaFaturamentoPosVO().getLFContasFaturamentoArray(Integer.parseInt(form.getNdxContaSelecionada())).getIdcontasistemaorigem();
			ddd = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrCodArea();
			//nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha().substring(2, 10);
			nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha().substring(2);
			logLupaFaturamento.debug("DetalheFaturaController:loadDetalhada() >> nrLinha: " + ddd + " " + nrLinha);

			// Recupera datas disponiveis
			String xmlBillDates = detalheFaturaFacade.getAcctImage(ddd, nrLinha, ConstantesCRM.SVAZIO, idContaSO, user);
			ByteArrayInputStream bais = new ByteArrayInputStream(xmlBillDates.getBytes());
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);
			NodeList dates = document.getElementsByTagName("billDt");

			if (dates.getLength() == 0) {
				detalheForm.setMsgErro("Não há nenhuma conta detalhada disponível.");
				destino = "erro";
			} else {
				date = dates.item(0).getFirstChild().getNodeValue();
				request.setAttribute("data", date);
			}
		} catch (Exception e) {
			logLupaFaturamento.error("DetalheFaturaController:loadDetalhada(" + user + ") - [" + e.getMessage() + "]", e);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("erro");
		}

		request.setAttribute("detalheForm", detalheForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(destino);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="responseFatura.jsp"
	 */
	public ActionForward loadDetalhadaLoad(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		LupaFaturamentoPosForm form = (LupaFaturamentoPosForm) formParam;
		String ddd = ConstantesCRM.SVAZIO;
		String nrLinha = ConstantesCRM.SVAZIO;
		String destino = ConstantesCRM.SUCCESS;
		String date = request.getParameter("data");
		logLupaFaturamento.debug("DetalheFaturaController:loadDetalhada() >> INICIALIZADO");
		try {
			// detalheForm = new LupaFaturamentoPosForm();
			String idContaSO = lupaFaturamentoPosForm.getLupaFaturamentoPosVO().getLFContasFaturamentoArray(Integer.parseInt(detalheForm.getNdxContaSelecionada())).getIdcontasistemaorigem();
			ddd = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrCodArea();
			//nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha().substring(2, 10);
			nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha().substring(2);
			logLupaFaturamento.debug("DetalheFaturaController:loadDetalhadaLoad() >> nrLinha: " + ddd + " " + nrLinha);

			// Recupera datas disponiveis
			// String xmlBillDates = detalheFaturaFacade.getAcctImage(ddd, nrLinha, "", idContaSO, user);
			// ByteArrayInputStream bais = new ByteArrayInputStream(xmlBillDates.getBytes());
			// Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);
			// NodeList dates = document.getElementsByTagName("billDt");

			if (date.length() == 0) {
				detalheForm.setMsgErro("Não há nenhuma conta detalhada disponível.");
				request.setAttribute("status", ConstantesCRM.STHREE);
			} else {
				String strTmp = detalheFaturaFacade.getAcctImage(ddd, nrLinha, date, idContaSO, user);
				String path = null;
				ByteArrayInputStream bais = new ByteArrayInputStream(strTmp.getBytes());
				Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);
				NodeList statusCodeNode = document.getElementsByTagName("statusCode");
				String statusCode = statusCodeNode.item(0).getFirstChild().getNodeValue();
				if ("00I0001".equalsIgnoreCase(statusCode)) {
					MsgDocument doc = MsgDocument.Factory.parse(strTmp);
					ARG vo = ARGDocument.Factory.parse(doc.getMsg().getMsgBody().toString()).getARG();
					path = vo.getDADOS().getPATH();
					String textoHTML = getTextoCompactado(new File(path));
					if (textoHTML == null) {
						logLupaFaturamento.debug("DetalheFaturaController:loadDetalhada() >> FINALIZADO");
						detalheForm.setMsgErro("O sistema de FATURAMENTO está temporariamente fora de serviço");
						request.setAttribute("status", ConstantesCRM.SFOUR);
					} else {
						detalheForm.setDetalheHTML(textoHTML);
						request.setAttribute("status", ConstantesCRM.SONE);
					}
				}
				if ("00W0004".equalsIgnoreCase(statusCode)) {
					detalheForm.setMsgErro("Nenhuma conta detalhada foi encontrada.");
					request.setAttribute("status", ConstantesCRM.SFIVE);
				}
				if ("00W0006".equalsIgnoreCase(statusCode)) {
					detalheForm.setMsgErro("No momento esta conta detalhada encontra-se indisponível para visualização.");
					request.setAttribute("status", ConstantesCRM.SSIX);
				}
				if ("00W0013".equalsIgnoreCase(statusCode)) {
					detalheForm.setMsgErro("No momento esta conta detalhada encontra-se indisponível para visualização.");
					request.setAttribute("status", ConstantesCRM.STWO);
				}
			}
		} catch (Exception e) {
			logLupaFaturamento.error("DetalheFaturaController:loadDetalhada(" + user + ") - [" + e.getMessage() + "]", e);
			request.setAttribute("status", ConstantesCRM.SEIGHT);
		}
		logLupaFaturamento.debug("DetalheFaturaController:loadDetalhada() >> FINALIZADO");

		request.setAttribute("detalheForm", detalheForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(destino);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="mesVencimento" path="faturaMesVencimento.jsp"
	 * @jpf:forward name="pesquisaDetalhada" path="faturaPesquisaDetalhada.jsp"
	 * @jpf:forward name="pesquisaDetalhadaPJ" path="faturaPesquisaDetalhadaPJ.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward faturaDetalhada(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		LupaFaturamentoPosForm form = (LupaFaturamentoPosForm) formParam;
		String destino = ConstantesCRM.SVAZIO;
		String nrConta = ConstantesCRM.SVAZIO;
		user = ConstantesCRM.getCurrentUser(request.getSession());
		int segundosTimeout = 10;

		try {

			ParametrosVO parametrosVO = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
			if ("getMesVencimento".equals(request.getParameter("operacao"))) {
				destino = "mesVencimento";
			}

			// Máscara para o mês de referência
			SimpleDateFormat sdf = new SimpleDateFormat("MMyy");
			SimpleDateFormat sdf2 = new SimpleDateFormat("MM/yyyy");
			SimpleDateFormat dataVenc = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dataVenc2 = new SimpleDateFormat("dd/MM/yyyy");

			nrConta = montaBuscaDetalhe();
			
			String endpoint = getParametroApoio(user, FATURA_ENDPOINT);
			if (ConstantesCRM.SVAZIO.equals(endpoint)) {
				request.setAttribute("msgErro", "Parâmetro necessário não encontrado: " + FATURA_ENDPOINT);
				request.setAttribute("detalheForm", detalheForm);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(destino);
			}
		
			Fatura_Service service = new Fatura_Service();  
			HttpTransportPipe.dump = true;
		    FaturaPortType faturaProxy = service.getFaturaPort();

		    ((BindingProvider)faturaProxy).getRequestContext().put("com.sun.xml.internal.ws.request.timeout", segundosTimeout * 1000);
		    ((BindingProvider)faturaProxy).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
			
			ConsultarHistoricoRequest consultarRequest = new ConsultarHistoricoRequest();
			consultarRequest.setNrConta(nrConta);

			
			Faturas retorno = null;
			logLupaFaturamento.debug("DetalheFaturaController:faturaDetalhada() >> ConsultarHistoricoRequest: " + consultarRequest.getNrConta());
			ArrayList faturas = new ArrayList();
		
			try {
				retorno = faturaProxy.consultarHistorico(consultarRequest);
			} catch (Exception e) {
				e.printStackTrace();
				logLupaFaturamento.error("DetalheFaturaController:faturaDetalhada() >> Erro ", e);
				if ("getPesquisaDetalhada".equals(request.getParameter("operacao"))) {
					String msgErro = "Erro ao obter as datas de vencimento das faturas.";
					getLupaFaturamentoPos().setFaturaVencimentosDisponiveis(new String[0]);
					request.setAttribute("msgErro", msgErro);
				}
			}

			if (retorno != null) {
				logLupaFaturamento.debug("DetalheFaturaController:faturaDetalhada() >> Fatura[] retorno: " + retorno.toString());
			}

			if (retorno != null && retorno.getFatura().size() > 0) {
				Fatura fatura = null;
				lupaFaturamentoPosForm.setFaturaVencimentosDisponiveis(new String[retorno.getFatura().size()]);
				int contador = 0;
				for (int i = retorno.getFatura().size() - 1; i >= 0; i--) {
					fatura = retorno.getFatura().get(i);
					/* Data Retornada formato: AAAA-MM-DD  */
					XMLGregorianCalendar xmlData = fatura.getDataVencimentoFatura(); 
					Date dtDataVencimento = new Date(xmlData.getYear()-1900, xmlData.getMonth()-1, xmlData.getDay());
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					String data = formatter.format(dtDataVencimento);

					lupaFaturamentoPosForm.getFaturaVencimentosDisponiveis()[contador] = data;

					if (contador == 0) {
						Date dtFim = formatter.parse(data);
						getLupaFaturamentoPos().getFiltrosFaturamentoForm().setDtFimPadrao(dtFim);
					} else if (i == 0) {
						Date dtInicio = formatter.parse(data);
						getLupaFaturamentoPos().getFiltrosFaturamentoForm().setDtInicioPadrao(dtInicio);
					}
					contador++;
				}
			} else{
	                lupaFaturamentoPosForm.setFaturaVencimentosDisponiveis(new String[0]);
	        }

		} catch (Exception e) {
			e.printStackTrace();
			logLupaFaturamento.error("DetalheFaturaController:faturaDetalhada() >> Erro ", e);
			if (e instanceof ErroInfoFault) {
				logLupaFaturamento.error("DetalheFaturaController:faturaDetalhada() >> Exception (ErroInfo): " + e);
				ErroInfoFault erroInfoFault = (ErroInfoFault) e;
				request.setAttribute("msgErro", erroInfoFault.getFaultInfo().getDescricao());
			} else {
				logLupaFaturamento.error("DetalheFaturaController:faturaDetalhada() >> Exception: " + e);
				getDetalheForm().setMsgErro(e.getMessage());
				FormError formError = globalApp.buildFormError(e, ConstantesCRM.TELAINCIAL);
				formError.setTarget(ConstantesCRM.FRAMEAPP);
				request.setAttribute(ConstantesCRM.SFORMERROR, formError);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SERROR);
			}
		}

		// Prepara formulário para pesquisa Avançada
		if ("getPesquisaDetalhada".equals(request.getParameter("operacao"))) {
			destino = "pesquisaDetalhada";
			if (request.getSession().getAttribute(ConstantesCRM.SPARAMETROS) != null && ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getInTipoPessoa().equals("PJ")) {
				destino = "pesquisaDetalhadaPJ";
			}

			try {
				ListaDadosVO listaDadosVO = registrarContatoFacade.getConsultaConta(user, ConstantesCRM.SONE, ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaCliente(), nrConta);
				getLupaFaturamentoPos().getFiltrosFaturamentoForm().setListaNrTelefoneOrigem(listaDadosVO.getVlItemArray());

			} catch (Exception e) {
				getDetalheForm().setMsgErro(e.getMessage());
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("erro");
			}
		}
		request.setAttribute("detalheForm", detalheForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(destino);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="mesVencimento" path="faturaMesVencimento.jsp"
	 * @jpf:forward name="pesquisaDetalhada" path="faturaPesquisaDetalhadaPJ.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward faturaDetalhadaPJ(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		LupaFaturamentoPosForm form = (LupaFaturamentoPosForm) formParam;
		String destino = ConstantesCRM.SVAZIO;
		String nrConta = ConstantesCRM.SVAZIO;
		user = ConstantesCRM.getCurrentUser(request.getSession());
		int segundosTimeout = 10;

		try {

			ParametrosVO parametrosVO = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
			if ("getMesVencimento".equals(request.getParameter("operacao"))) {
				destino = "mesVencimento";
			}

			// Máscara para o mês de referência
			ArrayList faturas = new ArrayList();
			Collection retorno = new ArrayList();
			try {
				retorno = detalheFaturaFacade.getMesesCarregadosContaOnline(montaBuscaDetalhe());
			} catch (Exception e) {
				if ("getPesquisaDetalhada".equals(request.getParameter("operacao"))) {
					String msgErro = "Erro ao obter as datas de vencimento das faturas.";
					getLupaFaturamentoPos().setFaturaVencimentosDisponiveis(new String[0]);
					request.setAttribute("msgErro", msgErro);
				}
			}

			if (retorno != null) {
				logLupaFaturamento.debug("DetalheFaturaController:faturaDetalhada() >> Fatura[] retorno: " + retorno.toString());
			}

			if (retorno != null && retorno.size() > 0) {
				Fatura fatura = null;
				lupaFaturamentoPosForm.setFaturaVencimentosDisponiveis(new String[retorno.size()]);
				int contador = 0;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

				for (int i = retorno.size() - 1; i >= 0; i--) {

					lupaFaturamentoPosForm.getFaturaVencimentosDisponiveis()[contador] = sdf.format(sdf.parse(retorno.toArray()[i] + ""));

					if (contador == 0) {
						getLupaFaturamentoPos().getFiltrosFaturamentoForm().setDtFimPadrao(sdf.parse(retorno.toArray()[i] + ""));
					} else if (i == 0) {
						getLupaFaturamentoPos().getFiltrosFaturamentoForm().setDtInicioPadrao(sdf.parse(retorno.toArray()[i] + ""));
					}

					contador++;
				}
			 } else {
	            lupaFaturamentoPosForm.setFaturaVencimentosDisponiveis(new String[0]);
	         }

		} catch (Exception e) {
			if (e instanceof ErroInfoFault) {
				logLupaFaturamento.error("DetalheFaturaController:faturaDetalhada() >> Exception (ErroInfo): " + e);
				ErroInfoFault erroInfoFault = (ErroInfoFault) e;
				request.setAttribute("msgErro", erroInfoFault.getFaultInfo().getDescricao());
			} else {
				logLupaFaturamento.error("DetalheFaturaController:faturaDetalhada() >> Exception: " + e);
				getDetalheForm().setMsgErro(e.getMessage());
				FormError formError = globalApp.buildFormError(e, ConstantesCRM.TELAINCIAL);
				formError.setTarget(ConstantesCRM.FRAMEAPP);
				request.setAttribute(ConstantesCRM.SFORMERROR, formError);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SERROR);
			}

		}

		// Prepara formulário para pesquisa Avançada
		if ("getPesquisaDetalhada".equals(request.getParameter("operacao"))) {

			destino = "pesquisaDetalhada";

			try {
				ListaDadosVO listaDadosVO = registrarContatoFacade.getConsultaConta(user, ConstantesCRM.SONE, ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaCliente(), nrConta);
				getLupaFaturamentoPos().getFiltrosFaturamentoForm().setListaNrTelefoneOrigem(listaDadosVO.getVlItemArray());

			} catch (Exception e) {
				getDetalheForm().setMsgErro(e.getMessage());
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("erro");
			}

		}

		request.setAttribute("detalheForm", detalheForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(destino);
	}

	/**
	 * @jpf:action
	 */
	public ActionForward buscaNumeroOrigem(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		LupaFaturamentoPosForm form = (LupaFaturamentoPosForm) formParam;
		String sNeedle = request.getParameter("query").replaceAll("[^0-9]", "");
		StringBuffer out = new StringBuffer();
		String nrTel;
		int contadorAtual = 0;
		int contador = 0;
		String[] listaLinhas = getLupaFaturamentoPos().getFiltrosFaturamentoForm().getListaNrTelefoneOrigem();

		for (int i = 0; i < listaLinhas.length; i++) {
			if (listaLinhas[i].indexOf(sNeedle) >= 0) {
				contador++;
			}
		}

		out.append("{");
		out.append("query:'").append(sNeedle).append("',");
		out.append("suggestions:[");

		for (int i = 0; i < listaLinhas.length; i++) {
			nrTel = listaLinhas[i];
			if (nrTel.indexOf(sNeedle) >= 0) {
				out.append("'").append(nrTel).append("'");
				if (contadorAtual < contador - 1) {
					out.append(",");
				}
				contadorAtual++;
			}
		}
		out.append("],").append("data:[");

		contadorAtual = 0;
		for (int i = 0; i < listaLinhas.length; i++) {
			if (listaLinhas[i].indexOf(sNeedle) >= 0) {
				out.append(i);
				if (contadorAtual < contador - 1) {
					out.append(",");
				}
				contadorAtual++;
			}
		}
		out.append("]").append("}");

		response.setContentType(ConstantesCRM.SContentType);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
		bufferedOutputStream.write(out.toString().getBytes(ConstantesCRM.SISO));
		bufferedOutputStream.flush();
		bufferedOutputStream.close();

		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="pesquisaDetalhada" path="faturaResultadoPesquisaDetalhada.jsp"
	 */
	public ActionForward pesquisaFaturaDetalhada(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("DetalheFaturaController::pesquisaFaturaDetalhada():: Inicio  (" +  user +")");
		try {
			LupaFaturamentoPosForm form = (LupaFaturamentoPosForm) formParam;
			int segundosTimeout = 10;
			if ("CSV".equals(form.getFiltrosFaturamentoForm().getCdFormatoExportacao())) {
				segundosTimeout = 15;
			}

			BuscarHistoricoFaturaComFiltroRequest filtroRequest = new BuscarHistoricoFaturaComFiltroRequest();
			filtroRequest.setNrConta(montaBuscaDetalhe());
			log.debug("DetalheFaturaController::pesquisaFaturaDetalhada():: Pesuisar Conta  (" +  user +") " + filtroRequest.getNrConta());

			// Tipo de pesquisa (Por fatura ou por período)
			if (!ConstantesCRM.SVAZIO.equals(form.getFiltrosFaturamentoForm().getDtVencimento())) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat formatterDtRef = new SimpleDateFormat("yyyyMM");
				Date dtReferencia = formatter.parse(form.getFiltrosFaturamentoForm().getDtVencimento());
				filtroRequest.setDtReferencia(formatterDtRef.format(dtReferencia));
				filtroRequest.setDtInicio(null);
				filtroRequest.setDtTermino(null);
			} else if (!ConstantesCRM.SVAZIO.equals(form.getFiltrosFaturamentoForm().getDtInicio()) && !ConstantesCRM.SVAZIO.equals(form.getFiltrosFaturamentoForm().getDtFim())) {
				filtroRequest.setDtInicio(getXMLGregorianCalendar(form.getFiltrosFaturamentoForm().getDtInicio()));
				filtroRequest.setDtTermino(getXMLGregorianCalendar(form.getFiltrosFaturamentoForm().getDtFim()));
			} else {
				filtroRequest.setDtInicio(getXMLGregorianCalendar(getLupaFaturamentoPos().getFiltrosFaturamentoForm().getDtInicioPadrao()));
				filtroRequest.setDtTermino(getXMLGregorianCalendar(getLupaFaturamentoPos().getFiltrosFaturamentoForm().getDtFimPadrao()));
				filtroRequest.setDtReferencia(null);
			}

			// Número de telefone de origem
			if (!ConstantesCRM.SVAZIO.equals(form.getFiltrosFaturamentoForm().getNrTelefoneOrigem())) {
				filtroRequest.setNrLinhaOrigem(form.getFiltrosFaturamentoForm().getNrTelefoneOrigem().replaceAll("[^0-9]*", ConstantesCRM.SVAZIO));
			}

			// Número de telefone de destino
			if (!ConstantesCRM.SVAZIO.equals(form.getFiltrosFaturamentoForm().getNrTelefoneDestino())) {
				filtroRequest.setNrLinhaDestino(form.getFiltrosFaturamentoForm().getNrTelefoneDestino().replaceAll("[^0-9]*", ConstantesCRM.SVAZIO));
			}

			/**
			 * Área Registro D = Dentro da área de registro F = Fora da área de registro T = Todas
			 */
			if (form.getFiltrosFaturamentoForm().getCdAreaRegistro().equals("D")) {
				filtroRequest.setTpArea("D");
			} else if (form.getFiltrosFaturamentoForm().getCdAreaRegistro().equals("F")) {
				filtroRequest.setTpArea("F");
			} else if (form.getFiltrosFaturamentoForm().getCdAreaRegistro().equals("T")) {
				filtroRequest.setTpArea("T");
			}

			/**
			 * Tipo de Serviço V = Voz A = Valor Agregado O = Outros Serviços T = Todas
			 */
			if (form.getFiltrosFaturamentoForm().getCdTipoServico().equals("A")) {
				filtroRequest.setTpServico("A");
			} else if (form.getFiltrosFaturamentoForm().getCdTipoServico().equals("V")) {
				filtroRequest.setTpServico("V");
			} else if (form.getFiltrosFaturamentoForm().getCdTipoServico().equals("T")) {
				filtroRequest.setTpServico("T");
			} else if (form.getFiltrosFaturamentoForm().getCdTipoServico().equals("O")) {
				filtroRequest.setTpServico("O");
			}

			/**
			 * Tipo de Chamada (Origem) O = Originada R = Recebida T = Todas
			 */
			if (form.getFiltrosFaturamentoForm().getCdTipoChamada().equals("O")) {
				filtroRequest.setTpOrigemChamada("O");
			} else if (form.getFiltrosFaturamentoForm().getCdTipoChamada().equals("R")) {
				filtroRequest.setTpOrigemChamada("R");
			} else if (form.getFiltrosFaturamentoForm().getCdTipoChamada().equals("T")) {
				filtroRequest.setTpOrigemChamada("T");
			}

			/**
			 * Detalhe da chamada L = Local N = Longa Distância Nacional I = Longa Distância Internacional T = Todas
			 */
			if (form.getFiltrosFaturamentoForm().getCdDetalheChamada().equals("L")) {
				filtroRequest.setTpDetalheChamada("L");
			} else if (form.getFiltrosFaturamentoForm().getCdDetalheChamada().equals("N")) {
				filtroRequest.setTpDetalheChamada("N");
			} else if (form.getFiltrosFaturamentoForm().getCdDetalheChamada().equals("I")) {
				filtroRequest.setTpDetalheChamada("I");
			} else if (form.getFiltrosFaturamentoForm().getCdDetalheChamada().equals("T")) {
				filtroRequest.setTpDetalheChamada("T");
			}

			/**
			 * Destino F = Fixo V = Vivo O = Outro
			 */
			if (form.getFiltrosFaturamentoForm().getCdTipoDestino().equals("F")) {
				filtroRequest.setTpDestino("F");
			} else if (form.getFiltrosFaturamentoForm().getCdTipoDestino().equals("V")) {
				filtroRequest.setTpDestino("V");
			} else if (form.getFiltrosFaturamentoForm().getCdTipoDestino().equals("O")) {
				filtroRequest.setTpDestino("O");
			}

			SimpleDateFormat formatDatasWs = new SimpleDateFormat("yyyy-MM-dd");
			com.indracompany.ws.faturaservice.Chamadas chamadas = new Chamadas();
			
			String endPoint = getParametroApoio(user, FATURA_ENDPOINT);
			if (ConstantesCRM.SVAZIO.equals(endPoint)) {
				request.setAttribute("msgErro", "Parâmetro necessário não encontrado: " + FATURA_ENDPOINT);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("pesquisaDetalhada");
			}
			
			 Fatura_Service service = new Fatura_Service();  
			 HttpTransportPipe.dump = true;
		     FaturaPortType faturaProxy = service.getFaturaPort();
		     ((BindingProvider)faturaProxy).getRequestContext().put("com.sun.xml.internal.ws.request.timeout", segundosTimeout * 1000);
		     ((BindingProvider)faturaProxy).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);
			
		     SimpleDateFormat formatHora = new SimpleDateFormat("hh:mm:ss");
			try {
				log.info("DetalheFaturaController::pesquisaFaturaDetalhada():: Incio chamada Filtros Conta " + filtroRequest.getNrConta() + " : " + formatHora.format(new Date()));
				chamadas = faturaProxy.buscarHistoricoFaturaComFiltro(filtroRequest);
				log.info("DetalheFaturaController::pesquisaFaturaDetalhada():: Fim chamada Filtros " + filtroRequest.getNrConta() + " : " + formatHora.format(new Date()));
				formatHora = null;
			} catch (ErroInfoFault erroInfoFault) {
				log.error("DetalheFaturaController::pesquisaFaturaDetalhada():: ErroInfo " + erroInfoFault.getFaultInfo().getCodigo() + " " + erroInfoFault.getFaultInfo().getDescricao());
				throw erroInfoFault;
			} catch(Exception exception) {
				log.error("DetalheFaturaController::pesquisaFaturaDetalhada()::AxisFault [" + exception.getMessage() + "]");
				throw exception;
			}
			
			double valorTotalFatura = 0.0d;
			if (chamadas == null || chamadas.getChamada().size() == 0) {
				log.warn("DetalheFaturaController::pesquisaFaturaDetalhada():: Sem chamadas  (" +  user +") " + chamadas);
				request.setAttribute("msgErro", "Não foram encontrados resultados para sua pesquisa.");
			} else {
				
				List<com.indracompany.ws.faturaservice.Chamada> listChamada = chamadas.getChamada();
				
				com.indracompany.ws.faturaservice.Chamada[] arrChamada = (com.indracompany.ws.faturaservice.Chamada[]) listChamada.toArray(new com.indracompany.ws.faturaservice.Chamada[listChamada.size()]);

				Arrays.sort(arrChamada);

				SimpleDateFormat formataHora = new SimpleDateFormat("hh:mm");
				SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");

				Date dtDataChamada = null;
				BigDecimal bdValor = null;
				BigInteger biDuracao = null;
				
				
				for (int i = 0; i < arrChamada.length; i++) {
					
					com.indracompany.ws.faturaservice.Chamada item = arrChamada[i];

					bdValor = item.getValor();
					valorTotalFatura += bdValor.doubleValue();
					
					biDuracao = item.getDuracao();
					
					// Formatacao da saida - Area Chamada
					if (item.getAreaChamada() != null && item.getAreaChamada().getDescricao() != null && item.getAreaChamada().getDescricao().equalsIgnoreCase("dentro da area de registro")) {
						item.getAreaChamada().setDescricao("Dentro do seu DDD");
					} else if (item.getAreaChamada() != null && item.getAreaChamada().getDescricao() != null && item.getAreaChamada().getDescricao().equalsIgnoreCase("Fora da area de registro")) {
						item.getAreaChamada().setDescricao("Em Roaming");
					}

					// Formatacao da saida - Tipo Chamada
					if (item.getTipoChamada() != null && item.getTipoChamada().getDescricao() != null && item.getTipoChamada().getDescricao().equalsIgnoreCase("Originada")) {
						item.getTipoChamada().setDescricao("Realizada");
					}

					// Formatacao da saida - Destino Chamada
					if (item.getDestinoChamada() != null && item.getDestinoChamada().getDescricao() != null && item.getDestinoChamadaDescricao().equalsIgnoreCase("Fixo")) {
						item.setDestinoChamadaDescricao("Para Fixo");
					} else if (item.getDestinoChamada() != null && item.getDestinoChamada().getDescricao() != null && item.getDestinoChamadaDescricao().equalsIgnoreCase("Vivo")) {
						item.setDestinoChamadaDescricao("Para Vivo");
					} else if (item.getDestinoChamada() != null && item.getDestinoChamada().getDescricao() != null && item.getDestinoChamadaDescricao().equalsIgnoreCase("Outras")) {
						item.setDestinoChamadaDescricao("Para Outra Operadora");
					}
				}
				
				
			}

			NumberFormat nf = new DecimalFormat("00.00");
			getLupaFaturamentoPos().setValorTotalFaturamento("R$" +nf.format(valorTotalFatura));
			
			List<com.indracompany.ws.faturaservice.Chamada> listChamada = chamadas.getChamada();
			com.indracompany.ws.faturaservice.Chamada[] arrChamada = (com.indracompany.ws.faturaservice.Chamada[]) listChamada.toArray(new com.indracompany.ws.faturaservice.Chamada[listChamada.size()]);
			Arrays.sort(arrChamada);

			getLupaFaturamentoPos().setPesquisaDetalhada(arrChamada);

			if ("CSV".equals(form.getFiltrosFaturamentoForm().getCdFormatoExportacao())) {
				response.addHeader("Content-Disposition", "attachment; filename=RelatorioFaturaDetalhamento.csv");
				response.addHeader("Content-Type", "application/force-download");
				response.addHeader("Content-Transfer-Encoding", "binary");
				response.addHeader("Pragma", "no-cache");
				response.addHeader("Expires", "0");

				PrintWriter out = response.getWriter();
				out.println(getPesquisaDetalhadaCSV(arrChamada, ","));
				out.flush();
				out.close();
				return null;
			}

		} catch (ErroInfoFault erroInfoFault) {
			erroInfoFault.printStackTrace();
			logLupaFaturamento.error("DetalheFaturaController:faturaDetalhada() >> Erro ", erroInfoFault);
			String msgErro = ConstantesCRM.SVAZIO;
			response.setHeader("stackTrace", erroInfoFault.getFaultInfo().getDescricao());
			if (ConstantesCRM.SFOUR.equals(erroInfoFault.getFaultInfo().getCodigo())) {
				msgErro = "Sua pesquisa excedeu o tamanho permitido. Por favor, selecione outras opções para refinar sua pesquisa.";
			} else {
				msgErro = "Ocorreu um erro ao acessar o serviço de pesquisa dos dados da fatura. Caso o problema persista, entre em contato com a Central de Serviços.";
			}
			response.setHeader("stackTrace", erroInfoFault.getFaultInfo().getDescricao());
			response.setHeader("msgErro", msgErro);
			response.setStatus(AjaxError.STATUS_CODE_ERROR);
			return null;

		} catch (Exception e) {
			e.printStackTrace();
			logLupaFaturamento.error("DetalheFaturaController:faturaDetalhada() >> Erro ", e);
			String msgErro = "O serviço de pesquisa de dados da fatura não está respondendo. Caso o problema persista, entre em contato com a Central de Serviços.";
			response.setHeader("stackTrace", e.toString().replaceAll("[\t]", "").replaceAll("[\n]", "").replaceAll("[\r]", ""));
			response.setHeader("msgErro", msgErro);
			response.setStatus(AjaxError.STATUS_CODE_ERROR);
			return null;
		}

		String idLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdLinha();
		String nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
		String nrProtocoloSessao = request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) != null ? (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) : ConstantesCRM.SVAZIO;

		String nrProtocolo = ConstantesCRM.SVAZIO;
		try {
			nrProtocolo = ClienteUtils.registrarPalitagem(user, ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)), idLinha, nrLinha, GetParametro.getInstace().getParametroVO(user, "NMPATH_FATURAONLINE_FILTROSAVANCADOS").getDsValorParametro(), ConstantesCRM.SVAZIO);
		} catch (Exception e) {
			log.error("DetalheFaturaController::pesquisaFaturaDetalhada():: Erro ao realizar palitagem", e);
		}

		boolean isProtocolo = true;
		try {
			long nrLongProtocolo = Long.parseLong(nrProtocolo);
		} catch (Exception e) {
			isProtocolo = false;
		}

		if (!nrProtocoloSessao.equals(nrProtocolo) && isProtocolo) {
			request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, nrProtocolo);
			((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setNrProtocolo(nrProtocolo);
			getLupaFaturamentoPos().setAtualizacaoProtocolo(true);
			getLupaFaturamentoPos().setNrProtocolo(nrProtocolo);
		} else {
			getLupaFaturamentoPos().setAtualizacaoProtocolo(false);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("pesquisaDetalhada");
	}

	private String getPesquisaDetalhadaCSV(com.indracompany.ws.faturaservice.Chamada[] chamada, String chDelimitador) {

		StringBuffer csvReturn = new StringBuffer("Seqüência").append(chDelimitador).append("Celular Origem").append(chDelimitador).append("Data").append(chDelimitador).append("Hora início").append(chDelimitador).append("Classificação").append(chDelimitador).append("Tipo Serviço").append(chDelimitador).append("Tipo Chamada").append(chDelimitador).append("Destino").append(chDelimitador).append("Número chamado").append(chDelimitador).append("Tarifa").append(chDelimitador)
		.append("Duração (minutos)").append(chDelimitador).append("Valor\n");

		NumberFormat nf = new DecimalFormat("000");
		int idTipoDetalheChamada, idDestinoChamada, idTipoTarifa;
		String cdTipoServico, cdTipoChamada;

		SimpleDateFormat formatterData = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		SimpleDateFormat formatterHora = new SimpleDateFormat("hh:mm:ss");
		for (int i = 0; i < chamada.length; i++) {
			csvReturn.append(nf.format((i + 1))).append(chDelimitador);
			csvReturn.append(chamada[i].getNumeroOrigem()).append(chDelimitador);
			csvReturn.append(formatterData.format(chamada[i].getDataChamada())).append(chDelimitador);
			csvReturn.append(formatterHora.format(chamada[i].getDataChamada())).append(chDelimitador);

			idTipoDetalheChamada = chamada[i].getTipoDetalheChamada().getCodigo().intValue();
			switch (idTipoDetalheChamada) {
			case 0:
				csvReturn.append("Todas");
				break;
			case 1:
				csvReturn.append("Originada");
				break;
			case 2:
				csvReturn.append("Local");
				break;
			case 3:
				csvReturn.append("Longa distância nacional");
				break;
			case 4:
				csvReturn.append("Longa distância internacional");
				break;
			case 5:
				csvReturn.append("A cobrar");
				break;
			}
			csvReturn.append(chDelimitador);

			cdTipoServico = chamada[i].getTipoServicoChamada().getCodigo();
			if ("D".equals(cdTipoServico)) {
				csvReturn.append("Dados");
			} else if ("O".equals(cdTipoServico)) {
				csvReturn.append("Outros");
			} else if ("A".equals(cdTipoServico)) {
				csvReturn.append("Todos");
			}
			csvReturn.append(chDelimitador);

			cdTipoChamada = chamada[i].getTipoChamada().getCodigo();
			if ("R".equals(cdTipoChamada)) {
				csvReturn.append("Recebida");
			} else if ("O".equals(cdTipoChamada)) {
				csvReturn.append("Originada");
			} else if ("A".equals(cdTipoChamada)) {
				csvReturn.append("Todas");
			}
			csvReturn.append(chDelimitador);

			idDestinoChamada = chamada[i].getDestinoChamada().getCodigo().intValue();
			switch (idDestinoChamada) {
			case 0:
				csvReturn.append("Todos");
				break;
			case 1:
				csvReturn.append("Fixo");
				break;
			case 2:
				csvReturn.append("Vivo");
				break;
			case 3:
				csvReturn.append("Outras operadoras");
				break;
			}
			csvReturn.append(chDelimitador);

			csvReturn.append(chamada[i].getNumeroDestino()).append(chDelimitador);

			idTipoTarifa = chamada[i].getTipoTarifa().getCodigo().intValue();
			switch (idTipoTarifa) {
			case 0:
				csvReturn.append("Normal");
				break;
			case 1:
				csvReturn.append("Reduzida");
				break;
			}
			csvReturn.append(chDelimitador);
			csvReturn.append(chamada[i].getDuracao().toString()).append(chDelimitador);
			csvReturn.append(chamada[i].getValor().toString()).append("\n");
		}

		return csvReturn.toString();

	}
	
	public String formataValor(BigDecimal valor) {
	    if (valor == null || valor.equals(ConstantesCRM.SVAZIO)) valor = new BigDecimal("0");
	    DecimalFormatSymbols dSymbols = new DecimalFormatSymbols();
	    dSymbols.setDecimalSeparator(',');
	    dSymbols.setGroupingSeparator('.');
	    DecimalFormat dFormat = new DecimalFormat("00",dSymbols);
	    dFormat.isDecimalSeparatorAlwaysShown();
	    dFormat.applyPattern("###,#00.00;(###,#00.00)");
	    return dFormat.format(valor);
	}
	
	public String formataDuracao(BigInteger duracao) {
		
		  int duracaoSegundos = duracao.intValue();
          int hours = duracaoSegundos / 3600;
          int remainder = duracaoSegundos % 3600;
          int minutes = remainder / 60;
          int seconds = remainder % 60;
          String duracaoFormatada = ( (hours < 10 ? "0" : "")
              + hours
              + "h"
              + (minutes < 10 ? "0" : "")
              + minutes
              + "m"
              + (seconds < 10 ? "0" : "")
              + seconds
              + "s");
              return duracaoFormatada;
	}


	/**
	 * @jpf:action
	 * @jpf:forward name="pesquisaDetalhada" path="faturaResultadoPesquisaDetalhadaPJ.jsp"
	 */
	public ActionForward pesquisaFaturaDetalhadaPJ(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		try {
			LupaFaturamentoPosForm form = (LupaFaturamentoPosForm) formParam;
			BuscarHistoricoFaturaComFiltroRequest buscarHistoricoFaturaComFiltroRequest = new BuscarHistoricoFaturaComFiltroRequest();
			buscarHistoricoFaturaComFiltroRequest.setNrConta(montaBuscaDetalhe());

			// Tipo de pesquisa (Por fatura ou por período)
			if (!ConstantesCRM.SVAZIO.equals(form.getFiltrosFaturamentoForm().getDtVencimento())) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat formatterDtRef = new SimpleDateFormat("yyyyMM");
				Date dtReferencia = formatter.parse(form.getFiltrosFaturamentoForm().getDtVencimento());
				buscarHistoricoFaturaComFiltroRequest.setDtReferencia(formatterDtRef.format(dtReferencia));
				buscarHistoricoFaturaComFiltroRequest.setDtInicio(null);
				buscarHistoricoFaturaComFiltroRequest.setDtTermino(null);

			} else if (!ConstantesCRM.SVAZIO.equals(form.getFiltrosFaturamentoForm().getDtInicio()) && !ConstantesCRM.SVAZIO.equals(form.getFiltrosFaturamentoForm().getDtFim())) {
				buscarHistoricoFaturaComFiltroRequest.setDtInicio(getXMLGregorianCalendar(form.getFiltrosFaturamentoForm().getDtInicio()));
				buscarHistoricoFaturaComFiltroRequest.setDtTermino(getXMLGregorianCalendar(form.getFiltrosFaturamentoForm().getDtFim()));

			} else {
				buscarHistoricoFaturaComFiltroRequest.setDtInicio(getXMLGregorianCalendar(getLupaFaturamentoPos().getFiltrosFaturamentoForm().getDtInicioPadrao()));
				buscarHistoricoFaturaComFiltroRequest.setDtTermino(getXMLGregorianCalendar(getLupaFaturamentoPos().getFiltrosFaturamentoForm().getDtFimPadrao()));
				buscarHistoricoFaturaComFiltroRequest.setDtReferencia(null);
			}

			// Número de telefone de origem
			if (!ConstantesCRM.SVAZIO.equals(form.getFiltrosFaturamentoForm().getNrTelefoneOrigem())) {
				buscarHistoricoFaturaComFiltroRequest.setNrLinhaOrigem(form.getFiltrosFaturamentoForm().getNrTelefoneOrigem().replaceAll("[^0-9]*", ConstantesCRM.SVAZIO));
			}

			// Número de telefone de destino
			if (!ConstantesCRM.SVAZIO.equals(form.getFiltrosFaturamentoForm().getNrTelefoneDestino())) {
				buscarHistoricoFaturaComFiltroRequest.setNrLinhaDestino(form.getFiltrosFaturamentoForm().getNrTelefoneDestino().replaceAll("[^0-9]*", ConstantesCRM.SVAZIO));
			}

			/**
			 * Área Registro D = Dentro da área de registro F = Fora da área de registro T = Todas
			 */
			String tpArea = "T";
			if (form.getFiltrosFaturamentoForm().getCdAreaRegistro().equals("D")) {
				tpArea = ConstantesCRM.SONE;
			} else if (form.getFiltrosFaturamentoForm().getCdAreaRegistro().equals("F")) {
				tpArea = ConstantesCRM.STWO;
			} else if (form.getFiltrosFaturamentoForm().getCdAreaRegistro().equals("T")) {
				tpArea = "T";
			}

			/**
			 * Tipo de Serviço V = Voz A = Valor Agregado O = Outros Serviços T = Todas
			 */
			String tpServico = "T";
			if (form.getFiltrosFaturamentoForm().getCdTipoServico().equals("A")) {
				tpServico = ConstantesCRM.STWO;
			} else if (form.getFiltrosFaturamentoForm().getCdTipoServico().equals("V")) {
				tpServico = ConstantesCRM.SONE;
			} else if (form.getFiltrosFaturamentoForm().getCdTipoServico().equals("T")) {
				tpServico = "T";
			} else if (form.getFiltrosFaturamentoForm().getCdTipoServico().equals("O")) {
				tpServico = ConstantesCRM.STHREE;
			}

			/**
			 * Tipo de Chamada (Origem) O = Originada R = Recebida T = Todas
			 */
			String tpChamada = "T";
			if (form.getFiltrosFaturamentoForm().getCdTipoChamada().equals("O")) {
				tpChamada = ConstantesCRM.SONE;
			} else if (form.getFiltrosFaturamentoForm().getCdTipoChamada().equals("R")) {
				tpChamada = ConstantesCRM.STWO;
			} else if (form.getFiltrosFaturamentoForm().getCdTipoChamada().equals("EX")) {
				tpChamada = ConstantesCRM.STHREE;
			} else if (form.getFiltrosFaturamentoForm().getCdTipoChamada().equals("T")) {
				tpChamada = "T";
			}

			/**
			 * Detalhe da chamada L = Local N = Longa Distância Nacional I = Longa Distância Internacional T = Todas
			 */
			String tpDetalheChamada = "T";
			if (form.getFiltrosFaturamentoForm().getCdDetalheChamada().equals("L")) {
				tpDetalheChamada = ConstantesCRM.SONE;
			} else if (form.getFiltrosFaturamentoForm().getCdDetalheChamada().equals("N")) {
				tpDetalheChamada = ConstantesCRM.STWO;
			} else if (form.getFiltrosFaturamentoForm().getCdDetalheChamada().equals("I")) {
				tpDetalheChamada = ConstantesCRM.STHREE;
			} else if (form.getFiltrosFaturamentoForm().getCdDetalheChamada().equals("T")) {
				tpDetalheChamada = "T";
			}

			/**
			 * Destino F = Fixo V = Vivo O = Outro
			 *
			 */
			String tpDestino = "T";
			if (form.getFiltrosFaturamentoForm().getCdTipoDestino().equals("F")) {
				tpDestino = ConstantesCRM.SONE;
			} else if (form.getFiltrosFaturamentoForm().getCdTipoDestino().equals("V")) {
				tpDestino = ConstantesCRM.STWO;
			} else if (form.getFiltrosFaturamentoForm().getCdTipoDestino().equals("O")) {
				tpDestino = ConstantesCRM.STHREE;
			} else if (form.getFiltrosFaturamentoForm().getCdTipoDestino().equals("IG")) {
				tpDestino = ConstantesCRM.SFOUR;
			} else if (form.getFiltrosFaturamentoForm().getCdTipoDestino().equals("OUTRAS")) {
				tpDestino = ConstantesCRM.SFIVE;
			}

			SimpleDateFormat formatDatasBD = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Collection retornoPesquisa = new ArrayList();

			try {
				int totalPorPagina = 100;
				int totalRows = 0;

				DisplayTagHelper displayTagHelper = new DisplayTagHelper("listaXPTO");
				boolean primeiraPesquisa = false;

				int paginaInicial = 0;
				int paginaFinal = 0;
				int pagina = 0;

				try {
					java.util.Enumeration e = request.getParameterNames();
					String parameter = null;
					while (e.hasMoreElements()) {
						parameter = (String) e.nextElement();
						if (parameter.startsWith("d-") && parameter.endsWith("-p")) {
							pagina = (Integer.parseInt(request.getParameter(parameter)));
							break;
						}
					}
					// Nao encontrou o parametro na sessao
					if (pagina == 0) {
						throw new NumberFormatException();
					}

				} catch (NumberFormatException e) {
					pagina = 1;
					primeiraPesquisa = true;
				}

				request.setAttribute("paginaAtualPesquisa", pagina + "");

				boolean buscarRegistros = false;
				String intervalo[] = new String[2];

				// Verifica se es´ta sendo solicitado uma nova pesquisa, para nao pegar cache
				if (request.getParameter("paginacao") == null) {
					buscarRegistros = true;
				}
				// Verifica se já há paginas em cache
				else if (request.getSession().getAttribute("paginasCache") != null && request.getSession().getAttribute("retornoPesquisa") != null && ((java.util.Collection) request.getSession().getAttribute("retornoPesquisa")).size() > 0) {
					// Verifica se a pagina solicitada está em cache
					String paginas = request.getSession().getAttribute("paginasCache") + "";
					intervalo = paginas.split(",");

					if (pagina >= Integer.parseInt(intervalo[0]) && pagina <= Integer.parseInt(intervalo[1])) {
						buscarRegistros = false;
					} else {
						buscarRegistros = true;
					}
				} else {
					// Nenhuma pagina em cache, buscar paginacao
					buscarRegistros = true;
				}

				// Busca os registros no banco conforme a paginacao
				if (buscarRegistros) {
					// Monta o intervalo de paginas que irá ficar em cache
					String paginasCache = this.montarPaginasCache(pagina);
					intervalo = paginasCache.split(",");

					// Pagina Inicial
					if (intervalo[0] == null || Integer.parseInt(intervalo[0]) == 1) {
						paginaInicial = 1;
					} else {
						paginaInicial = ((Integer.parseInt(intervalo[0]) - 1) * totalPorPagina) + 1;
					}

					// Pagina Final
					if (intervalo[1] == null) {
						paginaFinal = totalPorPagina * 3;
					} else {
						paginaFinal = (Integer.parseInt(intervalo[1])) * totalPorPagina;
					}

					retornoPesquisa = detalheFaturaFacade.getPesquisaFiltros(buscarHistoricoFaturaComFiltroRequest.getNrConta(), buscarHistoricoFaturaComFiltroRequest.getDtReferencia(), buscarHistoricoFaturaComFiltroRequest.getDtInicio() != null ? formatDatasBD.format(buscarHistoricoFaturaComFiltroRequest.getDtInicio()) : null, buscarHistoricoFaturaComFiltroRequest.getDtTermino() != null ? formatDatasBD.format(buscarHistoricoFaturaComFiltroRequest.getDtTermino()) : null, buscarHistoricoFaturaComFiltroRequest.getNrLinhaOrigem() != null ? buscarHistoricoFaturaComFiltroRequest.getNrLinhaOrigem() : null, buscarHistoricoFaturaComFiltroRequest.getNrLinhaDestino() != null ? buscarHistoricoFaturaComFiltroRequest.getNrLinhaDestino() : null, !tpChamada.equals("T") ? tpChamada : null,!tpArea.equals("T") ? tpArea : null, !tpDetalheChamada.equals("T") ? tpDetalheChamada : null, !tpServico.equals("T") ? tpServico : null, !tpDestino.equals("T") ? tpDestino : null, paginaInicial + "", paginaFinal + "");
					request.getSession().setAttribute("paginasCache", paginasCache);
					// Seta o resultado da pesquisa na sessao
					request.getSession().setAttribute("retornoPesquisa", retornoPesquisa);
					// Verifica se a pesquisa retornou dados
					if (retornoPesquisa == null || retornoPesquisa.size() == 0) {
						request.setAttribute("msgErro", "Não foram encontrados resultados para sua pesquisa.");
					}
				}

				if (primeiraPesquisa) {
					totalRows = detalheFaturaFacade.getPesquisaFiltrosQtdRegistros(buscarHistoricoFaturaComFiltroRequest.getNrConta(), buscarHistoricoFaturaComFiltroRequest.getDtReferencia(), buscarHistoricoFaturaComFiltroRequest.getDtInicio() != null ? formatDatasBD.format(buscarHistoricoFaturaComFiltroRequest.getDtInicio()) : null, buscarHistoricoFaturaComFiltroRequest.getDtTermino() != null ? formatDatasBD.format(buscarHistoricoFaturaComFiltroRequest.getDtTermino()) : null, buscarHistoricoFaturaComFiltroRequest.getNrLinhaOrigem() != null ? buscarHistoricoFaturaComFiltroRequest.getNrLinhaOrigem() : null, buscarHistoricoFaturaComFiltroRequest.getNrLinhaDestino() != null ? buscarHistoricoFaturaComFiltroRequest.getNrLinhaDestino() : null, !tpChamada.equals("T") ? tpChamada : null, !tpArea.equals("T") ? tpArea : null, !tpDetalheChamada.equals("T") ? tpDetalheChamada : null, !tpServico.equals("T") ? tpServico : null, !tpDestino.equals("T") ? tpDestino : null, paginaInicial + "", paginaFinal + "");
					request.getSession().setAttribute("totalRows", new Integer(totalRows));
				}

				// Seta a lista somente com a pagina solicitada
				if (request.getSession().getAttribute("retornoPesquisa") != null) {
					java.util.Collection col = (java.util.Collection) request.getSession().getAttribute("retornoPesquisa");
					java.util.Iterator it = col.iterator();
					br.com.vivo.fo.cliente.detalheFatura.Chamada chamada = null;
					paginaInicial = ((pagina - 1) * totalPorPagina) + 1;
					paginaFinal = pagina * totalPorPagina;
					java.util.Collection colPaginado = new ArrayList();
					double total = 0.0;
					while (it.hasNext()) {
						chamada = (br.com.vivo.fo.cliente.detalheFatura.Chamada) it.next();
						if (chamada.getIdChamada() >= paginaInicial && chamada.getIdChamada() <= paginaFinal) {
							// Formatacao da saida - Area Chamada
							if (chamada.getAreaChamada() != null && chamada.getAreaChamada().getDescricao() != null && chamada.getAreaChamada().getDescricao().equalsIgnoreCase("dentro da area de registro")) {
								chamada.getAreaChamada().setDescricao("Dentro do seu DDD");
							} else if (chamada.getAreaChamada() != null && chamada.getAreaChamada().getDescricao() != null && chamada.getAreaChamada().getDescricao().equalsIgnoreCase("Fora da area de registro")) {
								chamada.getAreaChamada().setDescricao("Em Roaming");
							}

							// Formatacao da saida - Tipo Chamada
							if (chamada.getTipoChamada() != null && chamada.getTipoChamada().getDescricao() != null) {
								if (chamada.getTipoChamada().getDescricao().equals("1")) {
									chamada.getTipoChamada().setDescricao("Realizada");
								} else if (chamada.getTipoChamada().getDescricao().equals("2")) {
									chamada.getTipoChamada().setDescricao("Recebida");
								} else if (form.getFiltrosFaturamentoForm().getCdTipoChamada().equals("3")) {
									chamada.getTipoChamada().setDescricao("Chamadas no Exterior");
								} else {
									chamada.getTipoChamada().setDescricao("Outro");
								}
							}

							// Formatacao da saida - Destino Chamada
							if (chamada.getDestinoChamada() != null && chamada.getDestinoChamada().getDescricao() != null && chamada.getDestinoChamada().getDescricao().equalsIgnoreCase("1")) {
								chamada.getDestinoChamada().setDescricao("Para Fixo");
							} else if (chamada.getDestinoChamada() != null && chamada.getDestinoChamada().getDescricao() != null && chamada.getDestinoChamada().getDescricao().equalsIgnoreCase("2")) {
								chamada.getDestinoChamada().setDescricao("Para Vivo");
							} else if (chamada.getDestinoChamada() != null && chamada.getDestinoChamada().getDescricao() != null && chamada.getDestinoChamada().getDescricao().equalsIgnoreCase("3")) {
								chamada.getDestinoChamada().setDescricao("Para Outra Operadora");
							} else if (chamada.getDestinoChamada() != null && chamada.getDestinoChamada().getDescricao() != null && chamada.getDestinoChamada().getDescricao().equalsIgnoreCase("4")) {
								chamada.getDestinoChamada().setDescricao("Chamadas para Intragrupo");
							} else if (chamada.getDestinoChamada() != null && chamada.getDestinoChamada().getDescricao() != null && chamada.getDestinoChamada().getDescricao().equalsIgnoreCase("5")) {
								chamada.getDestinoChamada().setDescricao("Outras");
							} else {
								chamada.getDestinoChamada().setDescricao("Outro");
							}

							colPaginado.add(chamada);
							total = total + Double.parseDouble(chamada.getValor().toString());
						}
					}
					NumberFormat nf = new DecimalFormat("00.00");
					String totalString = nf.format(total);

					request.setAttribute("total", totalString + "");
					request.getSession().setAttribute("retornoPaginado", colPaginado);
				}

				request.setAttribute("retornoPaginado", request.getSession().getAttribute("retornoPaginado"));
				request.setAttribute("totalRows", request.getSession().getAttribute("totalRows"));

			} catch (Exception e) {
				/*RemoteException re = (RemoteException) e.getUndeclaredThrowable();
				ServiceControlException sfe = (ServiceControlException) re.getCause();
				SoapFault sFault = sfe.getSoapFault();
				TXmlFragment xfd = TXmlFragment.Factory.parse(sFault.get11Fault().toString());
				throw new ErroInfo(xfd.getDetail().getErroInfo().getCodigo(), xfd.getDetail().getErroInfo().getDescricao(), sFault.get11Fault().toString());
				*/
			}

			/*
			 * else {
			 *
			 * Arrays.sort(chamadas);
			 *
			 * // Converte do formato YYYY-MM-DDTHH:MM:DD.0Z > dd/MM/yyyy hh:mm // for (int i=0;i<chamadas.length;i++){
			 * chamadas[i].setDataChamada(chamadas[i].getDataChamada().substring(8, 10) + "/" +
			 * chamadas[i].getDataChamada().substring(5, 7) + "/" + chamadas[i].getDataChamada().substring(0, 4) + " " +
			 * chamadas[i].getDataChamada().substring(11, 16));
			 *
			 * // Formatacao da saida - Area Chamada if (chamadas[i].getAreaChamada() != null &&
			 * chamadas[i].getAreaChamada().getDescricao() != null &&
			 * chamadas[i].getAreaChamada().getDescricao().equalsIgnoreCase("dentro da area de registro")) {
			 * chamadas[i].getAreaChamada().setDescricao("Dentro do seu DDD"); } else if (chamadas[i].getAreaChamada()
			 * != null && chamadas[i].getAreaChamada().getDescricao() != null &&
			 * chamadas[i].getAreaChamada().getDescricao().equalsIgnoreCase("Fora da area de registro")) {
			 * chamadas[i].getAreaChamada().setDescricao("Em Roaming"); }
			 *
			 * // Formatacao da saida - Tipo Chamada if (chamadas[i].getTipoChamada() != null &&
			 * chamadas[i].getTipoChamada().getDescricao() != null &&
			 * chamadas[i].getTipoChamada().getDescricao().equalsIgnoreCase("Originada")) {
			 * chamadas[i].getTipoChamada().setDescricao("Realizada"); }
			 *
			 * // Formatacao da saida - Destino Chamada if (chamadas[i].getDestinoChamada() != null &&
			 * chamadas[i].getDestinoChamada().getDescricao() != null &&
			 * chamadas[i].getDestinoChamada().getDescricao().equalsIgnoreCase("Fixo")) {
			 * chamadas[i].getDestinoChamada().setDescricao("Para Fixo"); } else if (chamadas[i].getDestinoChamada() !=
			 * null && chamadas[i].getDestinoChamada().getDescricao() != null &&
			 * chamadas[i].getDestinoChamada().getDescricao().equalsIgnoreCase("Vivo")) {
			 * chamadas[i].getDestinoChamada().setDescricao("Para Vivo"); } else if (chamadas[i].getDestinoChamada() !=
			 * null && chamadas[i].getDestinoChamada().getDescricao() != null &&
			 * chamadas[i].getDestinoChamada().getDescricao().equalsIgnoreCase("Outras")) {
			 * chamadas[i].getDestinoChamada().setDescricao("Para Outra Operadora"); } } }
			 */

		/*} catch (ErroInfo eInfo) {

			String msgErro = ConstantesCRM.SVAZIO;
			response.setHeader("stackTrace", eInfo.getDescricao());
			if (ConstantesCRM.SFOUR.equals(eInfo.getCodigo())) {
				msgErro = "Sua pesquisa excedeu o tamanho permitido. Por favor, selecione outras opções para refinar sua pesquisa.";
			} else {
				msgErro = "Ocorreu um erro ao acessar o serviço de pesquisa dos dados da fatura. Caso o problema persista, entre em contato com a Central de Serviços.";
			}
			response.setHeader("stackTrace", eInfo.getXmlLog());
			response.setHeader("msgErro", msgErro);
			response.setStatus(AjaxError.STATUS_CODE_ERROR);
			return null;
		*/

		} catch (Exception e) {

			String msgErro = "O serviço de pesquisa de dados da fatura não está respondendo. Caso o problema persista, entre em contato com a Central de Serviços.";
			response.setHeader("stackTrace", e.toString().replaceAll("[\t]", "").replaceAll("[\n]", "").replaceAll("[\r]", ""));
			response.setHeader("msgErro", msgErro);
			response.setStatus(AjaxError.STATUS_CODE_ERROR);
			return null;

		}

		String idLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdLinha();
		String nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
		String nrProtocoloSessao = request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) != null ? (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) : ConstantesCRM.SVAZIO;

		String nrProtocolo = ConstantesCRM.SVAZIO;
		try {
			nrProtocolo = ClienteUtils.registrarPalitagem(user, ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)), idLinha, nrLinha, GetParametro.getInstace().getParametroVO(user, "NMPATH_FATURAONLINE_FILTROSAVANCADOS").getDsValorParametro(), ConstantesCRM.SVAZIO);
		} catch (Exception e) {
			log.error("DetalheFaturaController::pesquisaFaturaDetalhada():: Erro ao realizar palitagem", e);
		}

		boolean isProtocolo = true;
		try {
			long nrLongProtocolo = Long.parseLong(nrProtocolo);
		} catch (Exception e) {
			isProtocolo = false;
		}

		if (!nrProtocoloSessao.equals(nrProtocolo) && isProtocolo) {
			request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, nrProtocolo);
			((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setNrProtocolo(nrProtocolo);
			getLupaFaturamentoPos().setAtualizacaoProtocolo(true);
			getLupaFaturamentoPos().setNrProtocolo(nrProtocolo);
		} else {
			getLupaFaturamentoPos().setAtualizacaoProtocolo(false);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("pesquisaDetalhada");
	}

	private String getPesquisaDetalhadaCSV(br.com.vivo.fo.cliente.detalheFatura.Chamada[] chamada, String chDelimitador) {

		StringBuffer csvReturn = new StringBuffer("Seqüência").append(chDelimitador).append("Celular Origem").append(chDelimitador).append("Data").append(chDelimitador).append("Hora início").append(chDelimitador).append("Classificação").append(chDelimitador).append("Uso").append(chDelimitador).append("Tipo de ligação").append(chDelimitador).append("Operadora").append(chDelimitador).append("Número Discado").append(chDelimitador).append("Tarifa").append(chDelimitador).append("Duração (minutos)")
		.append(chDelimitador).append("Valor\n");

		NumberFormat nf = new DecimalFormat("000");
		int idTipoDetalheChamada, idDestinoChamada, idTipoTarifa;
		String cdTipoServico, cdTipoChamada;

		for (int i = 0; i < chamada.length; i++) {
			csvReturn.append(nf.format((i + 1))).append(chDelimitador);
			csvReturn.append(chamada[i].getNumeroOrigem()).append(chDelimitador);
			csvReturn.append(chamada[i].getDataChamada().substring(0, 10)).append(chDelimitador);
			csvReturn.append(chamada[i].getDataChamada().substring(chamada[i].getDataChamada().length() - 5, chamada[i].getDataChamada().length())).append(chDelimitador);

			idTipoDetalheChamada = chamada[i].getTipoDetalheChamada().getCodigo().intValue();
			switch (idTipoDetalheChamada) {
			case 0:
				csvReturn.append("Todas");
				break;
			case 1:
				csvReturn.append("Realizada");
				break;
			case 2:
				csvReturn.append("Local");
				break;
			case 3:
				csvReturn.append("Longa distância nacional");
				break;
			case 4:
				csvReturn.append("Longa distância internacional");
				break;
			case 5:
				csvReturn.append("A cobrar");
				break;
			}
			csvReturn.append(chDelimitador);

			cdTipoServico = chamada[i].getTipoServicoChamada().getCodigo();
			if ("D".equals(cdTipoServico)) {
				csvReturn.append("Dados");
			} else if ("O".equals(cdTipoServico)) {
				csvReturn.append("Outros");
			} else if ("A".equals(cdTipoServico)) {
				csvReturn.append("Todos");
			}
			csvReturn.append(chDelimitador);

			cdTipoChamada = chamada[i].getTipoChamada().getCodigo();
			if ("R".equals(cdTipoChamada)) {
				csvReturn.append("Recebida");
			} else if ("O".equals(cdTipoChamada)) {
				csvReturn.append("Realizada");
			} else if ("A".equals(cdTipoChamada)) {
				csvReturn.append("Todas");
			}
			csvReturn.append(chDelimitador);

			idDestinoChamada = chamada[i].getDestinoChamada().getCodigo().intValue();
			switch (idDestinoChamada) {
			case 0:
				csvReturn.append("Todos");
				break;
			case 1:
				csvReturn.append("Para Fixo");
				break;
			case 2:
				csvReturn.append("Para Vivo");
				break;
			case 3:
				csvReturn.append("Para Outra Operadoras");
				break;
			}
			csvReturn.append(chDelimitador);

			csvReturn.append(chamada[i].getNumeroDestino()).append(chDelimitador);

			idTipoTarifa = chamada[i].getTipoTarifa().getCodigo().intValue();
			switch (idTipoTarifa) {
			case 0:
				csvReturn.append("Normal");
				break;
			case 1:
				csvReturn.append("Reduzida");
				break;
			}
			csvReturn.append(chDelimitador);
			csvReturn.append(chamada[i].getDuracao().toString()).append(chDelimitador);
			csvReturn.append(chamada[i].getValor().toString()).append("\n");
		}

		return csvReturn.toString();

	}

	private String montarPaginasCache(int paginaSolicitada) {
		int primeiraPaginaCache = 0;
		int ultimaPaginaCache = 0;

		// Verifica a primeira pagina de cache
		if ((paginaSolicitada - 2) <= 0) {
			primeiraPaginaCache = 1;
		} else {
			primeiraPaginaCache = paginaSolicitada - 2;
		}

		// Ultima pagina de cache
		ultimaPaginaCache = paginaSolicitada + 2;

		return primeiraPaginaCache + "," + ultimaPaginaCache;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="visualizarFatura" path="visualizarFaturaContaOnline.jsp"
	 */
	public ActionForward consultarImagemFatura(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HashMap hash = null;
		int segundosTimeout = 10;
		LupaFaturamentoPosForm form = (LupaFaturamentoPosForm) formParam;
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");

			String dataVencimento = request.getParameter("dataVencimento");
			request.setAttribute("dataVencimento", dataVencimento);

			String nrPagina = request.getParameter("nrPagina") != null ? request.getParameter("nrPagina") : "1";
			String tipoArquivo = "GIF";

			String nrTelefone = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
			String[] params = new String[6];

			String nrConta = montaBuscaDetalhe();

			params[0] = new String();
			params[0] = "tp_formato:" + tipoArquivo;

			params[1] = new String();
			params[1] = "cd_contrato:" + nrConta;

			params[2] = new String();
			params[2] = "dt_vencimento:" + sdf2.format(sdf.parse(dataVencimento));

			params[3] = new String();
			params[3] = "cd_sistema:VIVONET";

			params[4] = new String();
			params[4] = "tp_documento:R";

			if (request.getParameter("terminal_origem") != null) {
				params[5] = new String();
				params[5] = "terminal_origem:" + request.getParameter("terminal_origem");
			} else {
				params[5] = new String();
				params[5] = "nm_pagina:" + nrPagina;
			}

			String endpoint = getParametroApoio(user, IMAGEM_ENDPOINT);
			if (ConstantesCRM.SVAZIO.equals(endpoint)) {
				request.setAttribute("msgErro", "Parâmetro necessário não encontrado: " + IMAGEM_ENDPOINT);
				request.setAttribute("detalheForm", detalheForm);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("visualizarFatura");
			}

			String[] retorno = new String[0];
			DocumentRequesterProxy documentProxy = new DocumentRequesterProxy();
			documentProxy.setEndpoint(endpoint);
			documentProxy.setUserTimeout(segundosTimeout * 1000);
			retorno = documentProxy.getDocument(params);
			String[] valor = null;
			hash = new HashMap();

			if (retorno != null && retorno.length > 0) {
				for (int i = 0; i < retorno.length; i++) {
					valor = retorno[i].split(":");
					if (valor.length > 1) {
						hash.put(valor[0], valor[1]);
					} else {
						hash.put(valor[0], ConstantesCRM.SVAZIO);
					}
				}
			}

			if (request.getParameter("terminal_origem") != null) {
				boolean resultadoEncontrado = true;
				if (hash.get("nm_pagina") != null) {
					if (ConstantesCRM.SZERO.equals(hash.get("nm_pagina"))) {
						resultadoEncontrado = false;
					}
				} else {
					resultadoEncontrado = false;
				}
				if (!resultadoEncontrado) {
					request.setAttribute("msgRetorno", "Telefone não encontrado na fatura.");
					request.setCharacterEncoding(ConstantesCRM.SISO);
					if (hash.get("st_arquivo") == null || ConstantesCRM.SVAZIO.equals(hash.get("st_arquivo"))) {
						hash.put("st_arquivo", getLupaFaturamentoPos().getFaturaBufferDownload());
						hash.put("tt_paginas", Integer.toString(getLupaFaturamentoPos().getFaturaQtdePaginas()));
						hash.put("nm_pagina", Integer.toString(getLupaFaturamentoPos().getFaturaPaginaAtual()));
					}
				}
			}

			// Verifica se veio o buffer da conta
			if (hash.get("st_arquivo") != null && !hash.get("st_arquivo").equals(ConstantesCRM.SVAZIO)) {
				// Verifica se e visualização ou download da fatura
				if (tipoArquivo.equals("GIF")) {
					getLupaFaturamentoPos().setFaturaQtdePaginas(Integer.parseInt(hash.get("tt_paginas").toString()));
					getLupaFaturamentoPos().setFaturaCicloSelecionado(dataVencimento);
					getLupaFaturamentoPos().setFaturaPaginaAtual(Integer.parseInt(hash.get("nm_pagina").toString()));
					getLupaFaturamentoPos().setFaturaBufferDownload(hash.get("st_arquivo").toString());
					request.getSession().setAttribute("bufferContaOnline", hash.get("st_arquivo").toString());
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					
					getLupaFaturamentoPos().setFaturaQtdePaginas(Integer.parseInt(hash.get("tt_paginas").toString()));
                    getLupaFaturamentoPos().setFaturaCicloSelecionado(dataVencimento);
                    getLupaFaturamentoPos().setFaturaPaginaAtual(Integer.parseInt(hash.get("nm_pagina").toString()));
                    getLupaFaturamentoPos().setFaturaBufferDownload(hash.get("st_arquivo").toString());
                    request.getSession().setAttribute("bufferContaOnline", hash.get("st_arquivo").toString());
                    if (isNovaFatura(dataVencimento)) {
                    	request.setAttribute("isNovaFatura", ConstantesCRM.SONE);
                    } else {
                    	request.removeAttribute("isNovaFatura");
                    }

					return mapping.findForward("visualizarFatura");

				}

				// PDF não implementado no Vivonet
				else if (tipoArquivo.equals("PDF")) {
					String file = nrTelefone + "_" + sdf2.format(sdf.parse(dataVencimento)) + ".pdf";
					getLupaFaturamentoPos().setFaturaTipoArquivo(tipoArquivo);
					getLupaFaturamentoPos().setFaturaNomeArquivo(file);
					getLupaFaturamentoPos().setFaturaBufferDownload(hash.get("st_arquivo").toString());
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("downloadFatura");
				}

			} else {
				if (tipoArquivo.equals("GIF")) {
					request.setAttribute("msgErro", "No momento não foi possível realizar essa ação. Por favor, tente mais tarde.");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("visualizarFatura");
				}
			}

		} catch (Exception e) {
			logLupaFaturamento.debug("DetalheFaturaController:consultarImagemFatura() >> Exception: " + e);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("visualizarFatura");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaSimulador.jsp"
	 * @jpf:forward name="erro" path="erroAtlys.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadSimulador(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		LupaFaturamentoPosForm form = (LupaFaturamentoPosForm) formParam;
		logLupaFaturamento.debug("DetalheFaturaController:loadSimulador() >> INICIALIZADO");
		ParametroVO parametro = null;
		try {
			String nrTelefone = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();

			try {
				parametro = telaInicialFacadeControl.getParametro(user, "ENDPOINT_SIMULADOR_PLANOS");
			} catch (Exception e) {
				logLupaFaturamento.error("DetalheFaturaController::loadSimulador()::Erro ao realizar busca de parametro ENDPOINT do Simulador de Planos", e);
				throw e;
			}
			PlanoSimuladoPortTypeProxy planoSimuladoProxy = new PlanoSimuladoPortTypeProxy();
			SecurityHeaderHelper securityHeader = getSecurityHeader();
			planoSimuladoProxy.setSecurityHeaderHelper(securityHeader);
			//nrTelefone= "1196291163";
			
			planoSimuladoProxy.setEndpoint(parametro.getDsValorParametro());
			

			logLupaFaturamento.debug("DetalheFaturaController:loadSimulador() >> nrTelefone " + nrTelefone);
			ParametrosBuscarPlanoAtual parametrosBuscarPlanoAtual = new ParametrosBuscarPlanoAtual();
			parametrosBuscarPlanoAtual.setNrLinha(nrTelefone);

			Plano plano = planoSimuladoProxy.buscarPlanoAtual(parametrosBuscarPlanoAtual);
			getLupaFaturamentoPos().setPlanoAtual(plano);

			ParametrosConsultaPlanosSimulados parametrosConsultaPlanosSimulados = new ParametrosConsultaPlanosSimulados();
			parametrosConsultaPlanosSimulados.setNrLinha(nrTelefone);

			Plano[] listaPlanos = planoSimuladoProxy.consultaPlanosSimulados(parametrosConsultaPlanosSimulados);

			logLupaFaturamento.debug("DetalheFaturaController:loadSimulador() :: Total Planos Simulados " + listaPlanos.length);
			getLupaFaturamentoPos().setListaPlanoSimulado(listaPlanos);
		} catch (br.com.vivo.www.MC.Geral.ErroInfo erroInfo) {
			logLupaFaturamento.error("[DetalheFaturaController.loadSimulador]  ErroInfo: [" + erroInfo.getCodigo() + "] " + erroInfo.getDescricao());
			getDetalheForm().setMsgErro("Serviço indisponível no momento");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("erro");
		} catch (AxisFault axisFaultException) {
			logLupaFaturamento.error("[DetalheFaturaController.loadSimulador] AxisFault [" + axisFaultException + "]");
			getDetalheForm().setMsgErro("Serviço indisponível no momento");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("erro");
		} catch (Exception e) {
			logLupaFaturamento.error("DetalheFaturaController:loadSimulador(" + user + ") - [" + e.getMessage() + "]", e);
			getDetalheForm().setMsgErro("Serviço indisponível no momento");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("erro");
		}
		logLupaFaturamento.debug("DetalheFaturaController:loadSimulador() >> FINALIZADO");

		request.setAttribute("detalheForm", detalheForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaSimuladorDetalhe.jsp"
	 * @jpf:forward name="erro" path="erroAtlys.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadDetalheSimulador(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		LupaFaturamentoPosForm form = (LupaFaturamentoPosForm) formParam;
		logLupaFaturamento.debug("DetalheFaturaController:loadDetalheSimulador() >> INICIALIZADO");
		try {
			String nmSelPlano = request.getParameter("nmSelPlano");
			ParametroVO parametro = null;
			String nrTelefone = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();

			try {
				parametro = telaInicialFacadeControl.getParametro(user, "ENDPOINT_SIMULADOR_PLANOS");
			} catch (Exception e) {
				logLupaFaturamento.error("DetalheFaturaController::loadDetalheSimulador()::Erro ao realizar busca de parametro ENDPOINT do Simulador de Planos", e);
			}
			logLupaFaturamento.debug("DetalheFaturaController:loadDetalheSimulador() >> nrTelefone " + nrTelefone);
			PlanoSimuladoPortTypeProxy planoSimuladoProxy = new PlanoSimuladoPortTypeProxy();
			SecurityHeaderHelper securityHeader = getSecurityHeader();
			planoSimuladoProxy.setSecurityHeaderHelper(securityHeader);
			/* REMOVER */
			//nrTelefone= "1196291163";
			
			planoSimuladoProxy.setEndpoint(parametro.getDsValorParametro());
			

			ParametrosDetalhesPlanoSimulado parametrosDetalhesPlanoSimulado = new ParametrosDetalhesPlanoSimulado();
			parametrosDetalhesPlanoSimulado.setNrLinha(nrTelefone);
			parametrosDetalhesPlanoSimulado.setIdPlanoSimulado(nmSelPlano);

			Plano planoEscolhido = planoSimuladoProxy.detalhesPlanoSimulado(parametrosDetalhesPlanoSimulado);
			getLupaFaturamentoPos().setPlanoEscolhido(planoEscolhido);

		} catch (ErroInfo erroInfo) {
			logLupaFaturamento.error("[DetalheFaturaController.loadDetalheSimulador]  ErroInfo: [" + erroInfo.getCodigo() + "] " + erroInfo.getDescricao());
			getDetalheForm().setMsgErro("Serviço indisponível no momento");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("erro");
		} catch (AxisFault axisFaultException) {
			logLupaFaturamento.error("[DetalheFaturaController.loadDetalheSimulador] AxisFault [" + axisFaultException + "]");
			getDetalheForm().setMsgErro("Serviço indisponível no momento");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("erro");
		} catch (Exception e) {
			logLupaFaturamento.error("DetalheFaturaController:loadDetalheSimulador(" + user + ") - [" + e.getMessage() + "]", e);
			getDetalheForm().setMsgErro("Serviço indisponível no momento");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("erro");
		}

		String nrProtocoloSessao = request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) != null ? (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) : ConstantesCRM.SVAZIO;

		String nrProtocolo = ConstantesCRM.SVAZIO;
		try {
			ParametroVO parametro = GetParametro.getInstace().getParametroVO(user, "PALITAGEM_SIMULADOR_PLANOS");
			if (parametro != null && !ConstantesCRM.SVAZIO.equals(parametro.getDsValorParametro())) {
				String idLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdLinha();
				String nrLinhaAtual = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
				nrProtocolo = ClienteUtils.registrarPalitagem(user, getParametrosVO(request), idLinha, nrLinhaAtual, parametro.getDsValorParametro(), ConstantesCRM.SVAZIO);
			}
		} catch (Exception e) {
			logLupaFaturamento.error("DetalheFaturaController::loadDetalheSimulador()::Erro ao realizar a palitagem de Simulador de Planos", e);
		}

		boolean isProtocolo = true;
		try {
			long nrLongProtocolo = Long.parseLong(nrProtocolo);
		} catch (Exception e) {
			isProtocolo = false;
		}

		if (!nrProtocoloSessao.equals(nrProtocolo) && isProtocolo) {
			request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, nrProtocolo);
			((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setNrProtocolo(nrProtocolo);
			getLupaFaturamentoPos().setAtualizacaoProtocolo(true);
			getLupaFaturamentoPos().setNrProtocolo(nrProtocolo);
		} else {
			getLupaFaturamentoPos().setAtualizacaoProtocolo(false);
		}

		logLupaFaturamento.debug("DetalheFaturaController:loadDetalheSimulador() >> FINALIZADO");

		request.setAttribute("detalheForm", detalheForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private ParametrosVO getParametrosVO(HttpServletRequest request) {
		ParametrosVO parametrosVO = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
		String nrProtocolo = (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO);
		parametrosVO.setNrProtocolo(nrProtocolo != null ? nrProtocolo : ConstantesCRM.SVAZIO);
		return parametrosVO;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaSimuladorEmail.jsp"
	 * @jpf:forward name="erro" path="erroAtlys.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadContatoEnviaEmailSimulador(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		LupaFaturamentoPosForm form = (LupaFaturamentoPosForm) formParam;
		logLupaFaturamento.debug("DetalheFaturaController:loadContatoEnviaEmailSimulador() >> INICIALIZADO");
		try {
			ConsultarTiposEmailVO consultarTiposEmailVO = controlManterAgendamento.consultaTiposEmail(user);
			getLupaFaturamentoPos().setConsultarTiposEmailVO(consultarTiposEmailVO);

			ConsultarEmailsVO consultarEmailsVO = controlManterAgendamento.consultaEmail(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaCliente(), user);
			getLupaFaturamentoPos().setConsultarEmailsVO(consultarEmailsVO);

		} catch (Exception e) {
			logLupaFaturamento.error("DetalheFaturaController:loadContatoEnviaEmailSimulador(" + user + ") - [" + e.getMessage() + "]", e);
			getDetalheForm().setMsgErro(e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("erro");
		}
		logLupaFaturamento.debug("DetalheFaturaController:loadContatoEnviaEmailSimulador() >> FINALIZADO");

		request.setAttribute("detalheForm", detalheForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="loadSimulador.do"
	 * @jpf:forward name="erro" path="erroAtlys.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadEnviaMailSimulador(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		LupaFaturamentoPosForm form = (LupaFaturamentoPosForm) formParam;
		logLupaFaturamento.debug("DetalheFaturaController:loadEnviaMailSimulador() >> INICIALIZADO");
		try {
			ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
			String linha = parametros.getNrLinha().substring(2);
			String ddd = parametros.getNrCodArea();

			String dsEmail = request.getParameter("dsEmail");
			String idTipoEmail = request.getParameter("idTipoEmail");
			String dsNovoEmail = request.getParameter("dsNovoEmail");
			String email = "";
			if ("NOVO".equals(dsEmail)) {
				controlManterAgendamento.incluiEmail(user, parametros.getIdPessoaCliente(), dsNovoEmail, idTipoEmail, ddd, linha, null);
				email = dsNovoEmail;
			} else {
				email = dsEmail;
			}
			StringBuffer corpoEmail = new StringBuffer("");

			corpoEmail.append("<html><head><style>");
			corpoEmail.append(".FontBlack9px { color: Black; font-family: Verdana; font-size: 9px; }");
			corpoEmail.append(".FontLightGray { color: #686868; font-family: Verdana; font-size: 10px; }");
			corpoEmail.append("</style></head><body><table border='0'>");
			corpoEmail.append("<tr bgcolor='#ffffff'><td><img src='cid:vivogif' border='0'></td></tr>");
			corpoEmail.append("<tr><td>&nbsp;</td></tr></table><table cellpadding='1' class='FontLightGray' bgcolor='#cccccc' cellspacing='1' width='450'>");
			corpoEmail.append("<tr><td bgcolor='#eeeeee' align='left' height='20'><b>Meu Plano:</b> ");
			if (getLupaFaturamentoPos().getPlanoAtual().getNome() != null) {
				corpoEmail.append(getLupaFaturamentoPos().getPlanoAtual().getNome());
			}
			corpoEmail.append("</td></tr>");
			corpoEmail.append("<tr bgcolor='#ffffff' class='FontLightGray'><td><table class='FontLightGray' width='450' border='0'><tr>");
			corpoEmail.append("<td width='400' align='center'><b>Detalhes do Plano</b></td><td width='100' align='center'><b>Valor Total da conta (m&eacute;dia/m&ecirc;s)</b></td>");
			corpoEmail.append("<td width='100' align='center'><b>Minutos totais incluso no plano</b></td></tr><tr>");
			corpoEmail.append("<td width='400' align='left'>");
			corpoEmail.append(getLupaFaturamentoPos().getPlanoAtual().getDescricao());
			corpoEmail.append("</td><td width='100' align='center'>R$&nbsp;");
			corpoEmail.append(getLupaFaturamentoPos().getPlanoAtual().getValorAssinaturaFormatado());
			corpoEmail.append("</td><td width='100' align='center'>");
			corpoEmail.append(getLupaFaturamentoPos().getPlanoAtual().getStringQtdMinutosFranquia());
			corpoEmail.append("</td></tr></table></td></tr></table><br>");
			corpoEmail.append("<table cellpadding='1' class='FontLightGray' bgcolor='#cccccc' cellspacing='1' width='450'>");
			corpoEmail.append("<tr><td bgcolor='#eeeeee' align='left' height='20'><b>Comparar meu plano com:</b>");
			if (getLupaFaturamentoPos().getPlanoEscolhido().getNome() != null) {
				corpoEmail.append(getLupaFaturamentoPos().getPlanoEscolhido().getNome());
			}
			corpoEmail.append("</td></tr>");
			corpoEmail.append("<tr bgcolor='#ffffff' class='FontLightGray'><td><table class='FontLightGray' width='450' border='0'><tr>");
			corpoEmail.append("<td width='400' align='center'><b>Detalhes do Plano</b></td><td width='100' align='center'><b>Valor Total da conta*</b></td>");
			corpoEmail.append("<td width='100' align='center'><b>Minutos totais incluso no plano</b></td></tr><tr>");
			corpoEmail.append("<td width='400' align='left'>");
			corpoEmail.append(getLupaFaturamentoPos().getPlanoEscolhido().getDescricao());
			corpoEmail.append("</td><td width='100' align='center'>");
			corpoEmail.append(getLupaFaturamentoPos().getPlanoEscolhido().getValorAssinaturaFormatado());
			corpoEmail.append("</td><td width='100' align='center'>");
			corpoEmail.append(getLupaFaturamentoPos().getPlanoEscolhido().getStringQtdMinutosFranquia());
			corpoEmail.append("</td></tr></table></td></tr></table>");
			corpoEmail.append("<span class='FontBlack9px' align='left'><b>*Estimativa de conta utilizando a m&eacute;dia de consumo (minuto e servi&ccedil;os) dos &uacute;ltimos 3 meses.</b></span>");
			corpoEmail.append("</body></html>");

			logLupaFaturamento.debug("DetalheFaturaController:loadEnviaMailSimulador:: Email " + corpoEmail.toString());

			InitialContext ic = new InitialContext();
			Session session = (Session) ic.lookup("VivoMail");

			Message msg = new MimeMessage(session);
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
			msg.setSubject("Vivo - Simulador de Planos");
			msg.setSentDate(new Date());

			MimeBodyPart mbp = new MimeBodyPart();
			mbp.setContent(corpoEmail.toString(), "text/html");

			Multipart mp = new MimeMultipart("related");
			mp.addBodyPart(mbp);

			MimeBodyPart mbpa = new MimeBodyPart();
			DataSource fds = new FileDataSource(this.getServlet().getServletContext().getRealPath("/resources/images/vivo.gif"));
			mbpa.setDataHandler(new DataHandler(fds));
			mbpa.setHeader("Content-ID", "<vivogif>");

			mp.addBodyPart(mbpa);

			msg.setContent(mp);

			Transport.send(msg);
		} catch (Exception e) {
			logLupaFaturamento.error("DetalheFaturaController:loadEnviaMailSimulador(" + user + ") - [" + e.getMessage() + "]", e);
			getDetalheForm().setMsgErro(e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("erro");
		}
		logLupaFaturamento.debug("DetalheFaturaController:loadEnviaMailSimulador() >> FINALIZADO");

		request.setAttribute("detalheForm", detalheForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public Object outputInnerBody(String xmlOut) throws XmlException {
		MsgDocument doc = MsgDocument.Factory.parse(xmlOut);
		return doc.getMsg().getMsgBody().getInnerBody();
	}

	/**
	 * Descompacta o arquivo recebido por parâmetro e retorna, em formato de String, o conteúdo obtido
	 *
	 * @param file
	 *            Arquivo compactado contendo apenas um arquivo interno, com conteúdo do tipo texto.
	 * @return O conteúdo do arquivo descompactado
	 */
	private String getTextoCompactado(File file) {

		try {
			/* Descompacta arquivo */
			FileInputStream fileStream = new FileInputStream(file);
			GZIPInputStream gzipStream = new GZIPInputStream(fileStream);
			/* Lê o arquivo descompactado */
			StringBuffer stringBuffer = new StringBuffer();
			int data;
			while ((data = gzipStream.read()) != -1) {
				stringBuffer.append((char) data);
			}
			fileStream.close();
			gzipStream.close();
			return stringBuffer.toString();
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="lupaFaturamento.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward mostraLinhas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		LupaFaturamentoPosForm form = (LupaFaturamentoPosForm) formParam;
		logLupaFaturamento.debug("DetalheFaturaController:mostraLinhas() >> INICIALIZADO");
		try {
			// Recupera a lista de linhas baseado no índice
			int ndx = Integer.parseInt(form.getNdxContaSelecionada());
			LFLinhas[] lfLinhasArray = lupaFaturamentoPosForm.getLupaFaturamentoPosVO().getLFContasFaturamentoArray(ndx).getLFLinhasArray();
			lupaFaturamentoPosForm.setLinhasArray(lfLinhasArray);
			lupaFaturamentoPosForm.setIdContaSelecionada(form.getIdContaSelecionada());
			lupaFaturamentoPosForm.setNdxContaSelecionada(form.getNdxContaSelecionada());
			logLupaFaturamento.debug("DetalheFaturaController:mostraLinhas() >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (Exception e) {
			logLupaFaturamento.error("DetalheFaturaController:mostraLinhas(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * Monta parte de busca do VO.
	 */
	private String montaBuscaDetalhe() {

		/*
		 * LupaFaturamentoPosVO lupaFaturTux = LupaFaturamentoPosVO.Factory.newInstance();
		 * lupaFaturTux.setBusca(Busca.Factory.newInstance()); lupaFaturTux.getBusca().setIdPessoa(idPessoa);
		 * lupaFaturTux
		 * .getBusca().setNrConta(lupaFaturamentoPosForm.getLupaFaturamentoPosVO().getLFContasFaturamentoArray
		 * (Integer.parseInt(lupaFaturamentoPosForm.getNdxContaSelecionada())).getNrConta());
		 * lupaFaturTux.getBusca().setDsCicloFatura
		 * (lupaFaturamentoPosForm.getLupaFaturamentoPosVO().getLFContasFaturamentoArray
		 * (Integer.parseInt(lupaFaturamentoPosForm.getNdxContaSelecionada())).getDsCicloFatura());
		 * lupaFaturTux.getBusca().setTpDetalhe(tpDetalhe);
		 */
		String idContaSO = (lupaFaturamentoPosForm.getLupaFaturamentoPosVO().getLFContasFaturamentoArray(Integer.parseInt(lupaFaturamentoPosForm.getNdxContaSelecionada())).getIdcontasistemaorigem());
		return idContaSO;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaLinhaIntraGrupo.jsp"
	 * @jpf:forward name="erro" path="erroAtlys.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadLinhaIntraGrupo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			LupaFaturamentoPosForm form = (LupaFaturamentoPosForm) formParam;
			logLupaFaturamento.debug("DetalheFaturaController:loadLinhaIntraGrupo() >> INICIALIZADO");
			String idContaSO = (lupaFaturamentoPosForm.getLupaFaturamentoPosVO().getLFContasFaturamentoArray(Integer.parseInt(lupaFaturamentoPosForm.getNdxContaSelecionada())).getIdcontasistemaorigem());

			LupaFaturamentoPosVO lupaFaturTux = LupaFaturamentoPosVO.Factory.newInstance();
			logLupaFaturamento.debug("DetalheFaturaController:loadLinhaIntraGrupo() >> idContaSO: " + idContaSO);
			// Vai no TUXEDO.
			lupaFaturTux = detalheFaturaFacade.getLinhaIntraGrupo(user, idContaSO);
			lupaFaturamentoPosForm.getLupaFaturamentoPosVO().setLFLinhaIntraGrupoArray(lupaFaturTux.getLFLinhaIntraGrupoArray());
			logLupaFaturamento.debug("DetalheFaturaController:loadLinhaIntraGrupo() >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (Exception e) {
			logLupaFaturamento.error("DetalheFaturaController:loadLinhaIntraGrupo(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaEstimativa.jsp"
	 * @jpf:forward name="erro" path="erroAtlys.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadEstimativa(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			LupaFaturamentoPosForm form = (LupaFaturamentoPosForm) formParam;
			logLupaFaturamento.debug("DetalheFaturaController:loadEstimativa() >> INICIALIZADO");
			LupaFaturamentoPosVO lupaFaturTux;
			// Monta uma VO só com dados de busca e envia ao TUXEDO.
			String idContaSO = montaBuscaDetalhe();
			String destino = ConstantesCRM.SUCCESS;
			int idTipoLinha = Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());

			// Vai no TUXEDO.
			try {
				logLupaFaturamento.debug("DetalheFaturaController:loadEstimativa() >> idTipoLinha: " + idTipoLinha);
				lupaFaturTux = detalheFaturaFacade.buscaDetalhes(idContaSO, "getEstimativaSaldo", user, idTipoLinha);
				lupaFaturamentoPosForm.getLupaFaturamentoPosVO().setLFEstimativaSaldo(lupaFaturTux.getLFEstimativaSaldo());
				lupaFaturTux = detalheFaturaFacade.buscaDetalhes(idContaSO, "getFormaPagamento", user, idTipoLinha);
				lupaFaturamentoPosForm.getLupaFaturamentoPosVO().setLFFormaPagamento(lupaFaturTux.getLFFormaPagamento());
			} catch (Exception e) {
				logLupaFaturamento.error("DetalheFaturaController:loadEstimativa(" + user + ") - [" + e.getMessage() + "]", e);
				destino = "erro";
			}
			logLupaFaturamento.debug("DetalheFaturaController:loadEstimativa() >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);
		} catch (Exception e) {
			logLupaFaturamento.error("DetalheFaturaController:loadEstimativa(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" return-action="DetalheFaturaDone"
	 */
	public ActionForward voltar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="lupaFaturamento.jsp"
	 */
	public ActionForward loadLupaFaturamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("inicioFatura", ConstantesCRM.STRUE);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}
	
	private boolean isNovaFatura(String dtVencimento) {

        ParametroVO parametro;

        // Flag em banco que determina criação de novo usuário ao invés de atualizar existente
        try {
            parametro = GetParametro.getInstace().getParametroVO(user, BOLETO_DATA_PITNEY1);
            if (parametro != null) {
                String dtCorte = parametro.getDsValorParametro();
                Calendar data1 = stringToCalendar("dd/MM/yyyy", dtVencimento);
                Calendar data2 = stringToCalendar("dd/MM/yyyy", dtCorte);
                if ((data1.getTimeInMillis() < data2.getTimeInMillis())) {
                     return false;
                }

            }
        } catch(Exception e) {

        }

        return true;
    }
	
	 private Calendar stringToCalendar(String format, String date) throws ParseException {

	        SimpleDateFormat inputFormat = new SimpleDateFormat(format);
	        Calendar calendar = Calendar.getInstance();
	        try {
	            calendar.setTime(inputFormat.parse(date));
	        } catch (ParseException pe) {
	            log.error("não foi possível converter a string "
	                        + date
	                        + " para calendar.", pe);

	            throw pe;
	        }
	        return calendar;
	}
	 
	private SecurityHeaderHelper getSecurityHeader() {
			
			SecurityHeaderHelper securityHeader = new SecurityHeaderHelper();
			securityHeader.setAddWSSecurity(false);
			securityHeader.setAddCabecalhoVivo(true);
			securityHeader.setLoginUsuario("VivoVivonet");
			securityHeader.setNomeAplicacao("VivoVivonet");
			securityHeader.setCanalAtendimento(ConstantesCRM.SONE);
			securityHeader.setCodigoSessao(ConstantesCRM.SONE);
			//securityHeader.setUserTimeout(new Integer(10000));
			
			return securityHeader;
	}
	
	private XMLGregorianCalendar getXMLGregorianCalendar(String strData) {
		
		try {
			
			SimpleDateFormat dtFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date dtData = dtFormat.parse(strData);
			GregorianCalendar gcData = new GregorianCalendar();
			gcData.setTime(dtData);
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(gcData);
			
		} catch(Exception e) {
			log.error("[DetalheFaturaController.getXMLGregorianCalendar] Erro parse Data [" + strData + "]: ", e);
		}
		
		return null;
	}

	private XMLGregorianCalendar getXMLGregorianCalendar(Date dtData) {
		
		try {
			GregorianCalendar gcData = new GregorianCalendar();
			gcData.setTime(dtData);
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(gcData);
			
		} catch(Exception e) {
			log.error("[DetalheFaturaController.getXMLGregorianCalendar] Erro parse Data [" + dtData + "]: ", e);
		}
		
		return null;
	}
	
	private String getStringFromXMLGregorianCalendar(XMLGregorianCalendar xmlData, String pattern) {
		try {
			System.out.println("xmlData " + xmlData);
			Date data = new Date(xmlData.getYear()-1900, xmlData.getMonth()-1, xmlData.getDay());
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			return formatter.format(data);
		} catch(Exception e) {
			log.error("[DetalheFaturaController.getStringFromXMLGregorianCalendar] Erro parse Data [" + xmlData + "]: ", e);
		}
		return null;
	}
	
	public static class LupaFaturamentoPosForm extends ActionForm {

		private static final long serialVersionUID = 2944417482088638793L;
		private String msgErro;
		private String inInicio;
		private String detalheHTML = new String();
		private String ndxContaSelecionada;
		private String idContaSelecionada;
		private String[] faturaVencimentosDisponiveis;
		private LFLinhas[] linhasArray;
		private br.com.vivo.fo.cliente.vo.LupaFaturamentoPosVODocument.LupaFaturamentoPosVO lupaFaturamentoPosVO;
		private ConsultarTiposEmailVO consultarTiposEmailVO = null;
		private ConsultarEmailsVO consultarEmailsVO = null;
		private FiltrosFaturamentoForm filtrosFaturamentoForm;
		private com.indracompany.ws.faturaservice.Chamada[] pesquisaDetalhada;
		private boolean atualizacaoProtocolo = false;
		private String nrProtocolo;

		// Variáveis para Conta Online
		private int faturaQtdePaginas;
		private String faturaCicloSelecionado;
		private int faturaPaginaAtual;
		private String faturaBuffer;
		private String faturaTipoArquivo;
		private String faturaNomeArquivo;
		private String faturaBufferDownload;
		private String nrCPFCNPJ;
		private List listaEmails = new ArrayList();

		/* Paramentros Simulador Plano */
		private Plano planoAtual;
		private Plano[] listaPlanoSimulado;
		private Plano planoEscolhido;
		
		private String valorTotalFaturamento;

		public LupaFaturamentoPosForm() {
			this.lupaFaturamentoPosVO = LupaFaturamentoPosVO.Factory.newInstance();

			this.setIdContaSelecionada("-1");
			this.setNdxContaSelecionada("-1");

			this.lupaFaturamentoPosVO.setBusca(Busca.Factory.newInstance());
			this.lupaFaturamentoPosVO.setLFAjustesArray(new LFAjustes[0]);
			this.lupaFaturamentoPosVO.setLFCobrancaArray(new LFCobranca[0]);
			this.lupaFaturamentoPosVO.setLFContaDetalhadaArray(new LFContaDetalhada[0]);
			this.lupaFaturamentoPosVO.setLFContasFaturamentoArray(new LFContasFaturamento[0]);
			this.lupaFaturamentoPosVO.setLFFaturamentoArray(new LFFaturamento[0]);
			this.lupaFaturamentoPosVO.setLFPagamentoArray(new LFPagamento[0]);
			this.linhasArray = new LFLinhas[0];
			this.faturaVencimentosDisponiveis = new String[0];
			this.listaEmails = new ArrayList();
			this.valorTotalFaturamento = "R$0,00";
		}

		public String getValorTotalFaturamento() {
			return valorTotalFaturamento;
		}

		public void setValorTotalFaturamento(String valorTotalFaturamento) {
			this.valorTotalFaturamento = valorTotalFaturamento;
		}

		public String[] getFaturaVencimentosDisponiveis() {
			if (this.faturaVencimentosDisponiveis == null) {
				this.faturaVencimentosDisponiveis = new String[0];
			}
			return this.faturaVencimentosDisponiveis;
		}

		public void setFaturaVencimentosDisponiveis(String[] arg1) {
			this.faturaVencimentosDisponiveis = arg1;
		}

		public ConsultarTiposEmailVO getConsultarTiposEmailVO() {
			return this.consultarTiposEmailVO;
		}

		public void setConsultarTiposEmailVO(ConsultarTiposEmailVO consultarTiposEmailVO) {
			this.consultarTiposEmailVO = consultarTiposEmailVO;
		}

		public ConsultarEmailsVO getConsultarEmailsVO() {
			return this.consultarEmailsVO;
		}

		public void setConsultarEmailsVO(ConsultarEmailsVO consultarEmailsVO) {
			this.consultarEmailsVO = consultarEmailsVO;
		}

		public FiltrosFaturamentoForm getFiltrosFaturamentoForm() {
			if (this.filtrosFaturamentoForm == null) {
				this.filtrosFaturamentoForm = new FiltrosFaturamentoForm();
			}
			return this.filtrosFaturamentoForm;
		}

		public void setFiltrosFaturamentoForm(FiltrosFaturamentoForm arg1) {
			this.filtrosFaturamentoForm = arg1;
		}

		public com.indracompany.ws.faturaservice.Chamada[] getPesquisaDetalhada() {
			if (this.pesquisaDetalhada == null) {
				this.pesquisaDetalhada = new com.indracompany.ws.faturaservice.Chamada[0];
			}
			return this.pesquisaDetalhada;
		}

		public void setPesquisaDetalhada(com.indracompany.ws.faturaservice.Chamada[] arg1) {
			this.pesquisaDetalhada = arg1;
		}

		public void setLupaFaturamentoPosVO(br.com.vivo.fo.cliente.vo.LupaFaturamentoPosVODocument.LupaFaturamentoPosVO lupaFaturamentoPosVO) {
			this.lupaFaturamentoPosVO = lupaFaturamentoPosVO;
		}

		public br.com.vivo.fo.cliente.vo.LupaFaturamentoPosVODocument.LupaFaturamentoPosVO getLupaFaturamentoPosVO() {
			return this.lupaFaturamentoPosVO;
		}

		public void setLinhasArray(LFLinhas[] linhasArray) {
			this.linhasArray = linhasArray;
		}

		public LFLinhas[] getLinhasArray() {
			return this.linhasArray;
		}

		public void setIdContaSelecionada(String idContaSelecionada) {
			this.idContaSelecionada = idContaSelecionada;
		}

		public String getIdContaSelecionada() {
			return this.idContaSelecionada;
		}

		public void setNdxContaSelecionada(String ndxContaSelecionada) {
			this.ndxContaSelecionada = ndxContaSelecionada;
		}

		public String getNdxContaSelecionada() {
			return this.ndxContaSelecionada;
		}

		public void setDetalheHTML(String detalheHTML) {
			this.detalheHTML = detalheHTML;
		}

		public String getDetalheHTML() {
			return this.detalheHTML;
		}

		public void setInInicio(String inInicio) {
			this.inInicio = inInicio;
		}

		public String getInInicio() {
			return this.inInicio;
		}

		public void setMsgErro(String msgErro) {
			this.msgErro = msgErro;
		}

		public String getMsgErro() {
			if (this.msgErro == null) {
				return ConstantesCRM.SVAZIO;
			} else {
				return this.msgErro;
			}
		}

		public void setFaturaQtdePaginas(int arg1) {
			this.faturaQtdePaginas = arg1;
		}

		public int getFaturaQtdePaginas() {
			return this.faturaQtdePaginas;
		}

		public void setFaturaCicloSelecionado(String arg1) {
			this.faturaCicloSelecionado = arg1;
		}

		public String getFaturaCicloSelecionado() {
			if (this.faturaCicloSelecionado == null) {
				return ConstantesCRM.SVAZIO;
			}
			return this.faturaCicloSelecionado;
		}

		public void setFaturaPaginaAtual(int arg1) {
			this.faturaPaginaAtual = arg1;
		}

		public int getFaturaPaginaAtual() {
			return this.faturaPaginaAtual;
		}

		public void setFaturaBuffer(String arg1) {
			this.faturaBuffer = arg1;
		}

		public String getFaturaBuffer() {
			if (this.faturaBuffer == null) {
				return ConstantesCRM.SVAZIO;
			}
			return this.faturaBuffer;
		}

		public void setFaturaTipoArquivo(String arg1) {
			this.faturaTipoArquivo = arg1;
		}

		public String getFaturaTipoArquivo() {
			if (this.faturaTipoArquivo == null) {
				return ConstantesCRM.SVAZIO;
			}
			return this.faturaTipoArquivo;
		}

		public void setFaturaNomeArquivo(String arg1) {
			this.faturaNomeArquivo = arg1;
		}

		public String getFaturaNomeArquivo() {
			if (this.faturaNomeArquivo == null) {
				return ConstantesCRM.SVAZIO;
			}
			return this.faturaNomeArquivo;
		}

		public void setFaturaBufferDownload(String arg1) {
			this.faturaBufferDownload = arg1;
		}

		public String getFaturaBufferDownload() {
			if (this.faturaBufferDownload == null) {
				return ConstantesCRM.SVAZIO;
			}
			return this.faturaBufferDownload;
		}

		public void setNrCPFCNPJ(String arg1) {
			this.nrCPFCNPJ = arg1;
		}

		public String getNrCPFCNPJ() {
			if (this.nrCPFCNPJ == null) {
				return ConstantesCRM.SVAZIO;
			}
			return this.nrCPFCNPJ;
		}

		public void setListaEmails(List arg1) {
			this.listaEmails = arg1;
		}

		public List getListaEmails() {
			return this.listaEmails;
		}

		public boolean isAtualizacaoProtocolo() {
			return this.atualizacaoProtocolo;
		}

		public void setAtualizacaoProtocolo(boolean arg) {
			this.atualizacaoProtocolo = arg;
		}

		public String getNrProtocolo() {
			if (this.nrProtocolo == null) {
				this.nrProtocolo = ConstantesCRM.SVAZIO;
			}
			return this.nrProtocolo;
		}

		public void setNrProtocolo(String arg) {
			this.nrProtocolo = arg;
		}

		public Plano getPlanoAtual() {
			return this.planoAtual;
		}

		public void setPlanoAtual(Plano plano) {
			this.planoAtual = plano;
		}

		public Plano[] getListaPlanoSimulado() {
			if (listaPlanoSimulado == null) {
				return new Plano[0];
			}
			return this.listaPlanoSimulado;
		}

		public void setListaPlanoSimulado(Plano[] lista) {
			this.listaPlanoSimulado = lista;
		}

		public Plano getPlanoEscolhido() {
			return this.planoEscolhido;
		}

		public void setPlanoEscolhido(Plano plano) {
			this.planoEscolhido = plano;
		}

	}

	public LupaFaturamentoPosForm getLupaFaturamentoPos() {
		if (this.lupaFaturamentoPosForm == null) {
			this.lupaFaturamentoPosForm = new LupaFaturamentoPosForm();
		}
		return this.lupaFaturamentoPosForm;
	}

	public LupaFaturamentoPosForm getDetalheForm() {
		return detalheForm;
	}

	public void setDetalheForm(LupaFaturamentoPosForm detalheForm) {
		this.detalheForm = detalheForm;
	}

	private String getParametroApoio(String idUsuario, String cdParametro) throws Exception {
		ParametroVO parametroVO = ParametroVO.Factory.newInstance();
		try {
			parametroVO = telaInicialFacadeControl.getParametro(idUsuario, cdParametro);
		} catch (Exception e) {
			return ConstantesCRM.SVAZIO;
		}
		return parametroVO.getDsValorParametro();
	}
	
	
}

 
