package cliente.TelaInicial.DetalhePrePago;

import java.text.SimpleDateFormat;
import java.util.Date;

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

import org.apache.axis.AxisFault;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.ClienteUtils;
import br.com.vivo.fo.cliente.vo.DetalheLinhaVODocument.DetalheLinhaVO;
import br.com.vivo.fo.cliente.vo.DetalhesSaldoItemDocument.DetalhesSaldoItem;
import br.com.vivo.fo.cliente.vo.DetalhesSaldoVODocument.DetalhesSaldoVO;
import br.com.vivo.fo.cliente.vo.ExtratoVODocument.ExtratoVO;
import br.com.vivo.fo.cliente.vo.FavoritosVODocument.FavoritosVO;
import br.com.vivo.fo.cliente.vo.HistoricoAtendimentoItemDocument.HistoricoAtendimentoItem;
import br.com.vivo.fo.cliente.vo.HistoricoAtendimentoVODocument.HistoricoAtendimentoVO;
import br.com.vivo.fo.cliente.vo.HistoricoMovimentosItemDocument.HistoricoMovimentosItem;
import br.com.vivo.fo.cliente.vo.HistoricoMovimentosVODocument.HistoricoMovimentosVO;
import br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.cliente.vo.PromocoesItemDocument.PromocoesItem;
import br.com.vivo.fo.cliente.vo.PromocoesVODocument.PromocoesVO;
import br.com.vivo.fo.commons.utils.GetParametro;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.cliente.prePago.ConsultasPrePagoFacade;
import br.com.vivo.fo.exception.TuxedoErrorException;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.servico.vo.ServicoVODocument.ServicoVO;
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

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

@SuppressWarnings({"deprecation"})
public class DetalhePrePagoController extends AbstractAction {

	private static final long serialVersionUID = -8002302848502601295L;

	@EJB
	private ConsultasPrePagoFacade consultasPrePagoFacadeControl;

	@EJB
	private br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ManterAgendamentoVIPFacade controlManterAgendamento;

	@EJB
	private br.com.vivo.fo.ctrls.cliente.telaInicial.TelaInicialFacade telaInicialFacadeControl;

	private DetalhesSaldoForm detalhesSaldoForm;
	private LupaPrePagoForm lupaPrePagoForm = new LupaPrePagoForm();
	private HistoricoMovimentosForm historicoMovimentosForm;
	private PromocoesForm promocoesForm;
	private FavoritosForm favoritosForm;
	private HistoricoAtendimentoForm historicoAtendimentoForm;
	private DetalheLinhaForm detalheLinhaForm;
	private ServicoForm servicoForm;
	private ExtratoForm extratoForm;
	private String user = null;

	private final String ERROR = "(*) Dados não recuperados pois Sistemas de LINHA temporariamente fora de serviço!";

	private ExtratoVO prePagoExtratoVO;

	private static Logger logLupaPrePago = new Logger("lupaprepago");

	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("loadPrePago".equals(mapping.getParameter())) {
			return loadPrePago(mapping, form, request, response);
		} else if ("loadPromocoes".equals(mapping.getParameter())) {
			return loadPromocoes(mapping, form, request, response);
		} else if ("loadHistoricoMovimentos".equals(mapping.getParameter())) {
			return loadHistoricoMovimentos(mapping, form, request, response);
		} else if ("pesquisarHistoricoMovimentos".equals(mapping.getParameter())) {
			return pesquisarHistoricoMovimentos(mapping, form, request, response);
		} else if ("loadFavoritos".equals(mapping.getParameter())) {
			return loadFavoritos(mapping, form, request, response);
		} else if ("loadHistoricoAtendimento".equals(mapping.getParameter())) {
			return loadHistoricoAtendimento(mapping, form, request, response);
		} else if ("pesquisarHistoricoAtendimento".equals(mapping.getParameter())) {
			return pesquisarHistoricoAtendimento(mapping, form, request, response);
		} else if ("loadDetalheLinha".equals(mapping.getParameter())) {
			return loadDetalheLinha(mapping, form, request, response);
		} else if ("loadServico".equals(mapping.getParameter())) {
			return loadServico(mapping, form, request, response);
		} else if ("loadExtrato".equals(mapping.getParameter())) {
			return loadExtrato(mapping, form, request, response);
		} else if ("pesquisarExtrato".equals(mapping.getParameter())) {
			return pesquisarExtrato(mapping, form, request, response);
		} else if ("loadSimulador".equals(mapping.getParameter())) {
			return loadSimulador(mapping, form, request, response);
		} else if ("loadDetalheSimulador".equals(mapping.getParameter())) {
			return loadDetalheSimulador(mapping, form, request, response);
		} else if ("loadContatoEnviaEmailSimulador".equals(mapping.getParameter())) {
			return loadContatoEnviaEmailSimulador(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="loadPrePago.do"
	 * @jpf:forward name="promocoes" path="loadPromocoes.do"
	 * @jpf:forward name="favoritos" path="loadFavoritos.do"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		// isto é para quando as abas de promoções e favoritos que vem
		// de outro contoller, possam para iniciar este controller
		user = ConstantesCRM.getCurrentUser(request.getSession());
		logLupaPrePago.debug("DetalhePrePagoController:begin(" + user + ") >> INICIALIZADO");
		if (null != request.getParameter("redirect")) {
			if ((request.getParameter("redirect")).equalsIgnoreCase("Promocoes")) {
				logLupaPrePago.debug("DetalhePrePagoController:begin(" + user + ") >> FINALIZADO (PROMOCOES)");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("promocoes");
			} else {
				logLupaPrePago.debug("DetalhePrePagoController:begin(" + user + ") >> FINALIZADO (FAVORITOS)");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("favoritos");
			}
		} else {
			logLupaPrePago.debug("DetalhePrePagoController:begin(" + user + ") >> FINALIZADO (DETALHES PRE-PAGO)");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="saldosPrePago.jsp"
	 */
	public ActionForward loadPrePago(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		user = ConstantesCRM.getCurrentUser(request.getSession());
		logLupaPrePago.debug("DetalhePrePagoController:loadPrePago(" + user + ") >> INICIALIZADO");
		detalhesSaldoForm = new DetalhesSaldoForm();

		String nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
		int idTipoLinha = Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());

		try {
			logLupaPrePago.debug("DetalhePrePagoController:loadPrePago(" + user + ") >> nrLinha" + nrLinha);
			logLupaPrePago.debug("DetalhePrePagoController:loadPrePago(" + user + ") >> idTipoLinha" + idTipoLinha);
			DetalhesSaldoVO dSaldoVO = consultasPrePagoFacadeControl.getDetalhesSaldo(user, nrLinha, idTipoLinha);

			float balanco = 0;
			DetalhesSaldoItem[] itens = dSaldoVO.getDetalhesSaldoItemArray();
			for (int i = 0; i < itens.length; i++) {
				itens[i].setPrValidade(formataData(itens[i].getPrValidade()));
				Float saldo = new Float(itens[i].getSaldo());
				balanco += saldo.floatValue();
			}
			Float saldoTotal = new Float(balanco);
			dSaldoVO.setDetalhesSaldoItemArray(itens);
			detalhesSaldoForm.setBalanco(saldoTotal.toString());
			detalhesSaldoForm.setDetalhesSaldoVO(dSaldoVO);
		} catch (TuxedoErrorException tee) {
			logLupaPrePago.error("DetalhePrePagoController:loadPrePago(" + user + ") - [" + tee.getMessage() + "]", tee);
			request.setAttribute(ConstantesCRM.SERROR, ERROR);
		} catch (TuxedoWarningException tee) {
			logLupaPrePago.warn("DetalhePrePagoController:loadPrePago(" + user + ") - [" + tee.getMessage() + "]", tee);
			request.setAttribute(ConstantesCRM.SERROR, ERROR);
		} catch (Exception tee) {
			logLupaPrePago.warn("DetalhePrePagoController:loadPrePago(" + user + ") - [" + tee.getMessage() + "]", tee);
			request.setAttribute(ConstantesCRM.SERROR, ERROR);
		}

		logLupaPrePago.debug("DetalhePrePagoController:loadPrePago(" + user + ") >> FINALIZADO");
		request.setAttribute("detalhesSaldoForm", detalhesSaldoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="promocoesPrePago.jsp"
	 */
	public ActionForward loadPromocoes(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		promocoesForm = new PromocoesForm();
		user = ConstantesCRM.getCurrentUser(request.getSession());
		logLupaPrePago.debug("DetalhePrePagoController:loadPromocoes(" + user + ") >> INICIALIZADO");
		try {

			String nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
			int idTipoLinha = Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());

			PromocoesVO promocoesVO = consultasPrePagoFacadeControl.getPromocoes(user, nrLinha, idTipoLinha);

			PromocoesItem[] itens = promocoesVO.getPromocoesItemArray();

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			String dtAtual = dateFormat.format(new Date());

			for (int i = 0; i < itens.length; i++) {
				if (ConstantesCRM.SVAZIO.equals(itens[i].getDataFim()) || itens[i].getDataFim() == null || dtAtual.compareTo(itens[i].getDataFim()) <= 0) {
					itens[i].setAtivo("ATIVO");
				} else {
					itens[i].setAtivo("INATIVO");
				}
			}
			promocoesVO.setPromocoesItemArray(itens);
			promocoesForm.setPromocoesVO(promocoesVO);
			promocoesForm.setErro(ConstantesCRM.SFALSE);

		} catch (TuxedoErrorException e) {
			logLupaPrePago.error("DetalhePrePagoController:loadPromocoes(" + user + ") - [" + e.getMessage() + "]", e);
			promocoesForm.setErro(ConstantesCRM.STRUE);

		} catch (TuxedoWarningException e) {
			logLupaPrePago.warn("DetalhePrePagoController:loadPromocoes(" + user + ") - [" + e.getMessage() + "]", e);
			promocoesForm.setErro(ConstantesCRM.STRUE);

		} catch (Exception e) {
			logLupaPrePago.error("DetalhePrePagoController:loadPromocoes(" + user + ") - [" + e.getMessage() + "]", e);
			promocoesForm.setErro(ConstantesCRM.STRUE);

		} finally {
			logLupaPrePago.debug("DetalhePrePagoController:loadPromocoes(" + user + ") >> FINALIZADO");
			request.setAttribute("promocoesForm", promocoesForm);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		}
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="historicoPrePago.jsp"
	 */
	public ActionForward loadHistoricoMovimentos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (historicoMovimentosForm == null) {
			historicoMovimentosForm = new HistoricoMovimentosForm();
		}

		request.setAttribute("historicoMovimentosForm", historicoMovimentosForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="historicoPrePago.jsp"
	 */
	public ActionForward pesquisarHistoricoMovimentos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HistoricoMovimentosForm form = (HistoricoMovimentosForm) formParam;
		logLupaPrePago.debug("DetalhePrePagoController:pesquisarHistoricoMovimentos(" + user + ") >> INICIALIZADO");
		String nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dtUtilIni = dateFormat.parse(form.getDtIni());
		Date dtUtilEnd = dateFormat.parse(form.getDtFin());

		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dtInicial = dateFormat.format(dtUtilIni);
		String dtFinal = dateFormat.format(dtUtilEnd);
		int idTipoLinha = Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());

		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			HistoricoMovimentosVO historicoVO = consultasPrePagoFacadeControl.getHistoricoMovimentos(user, nrLinha, dtInicial, dtFinal, idTipoLinha);

			HistoricoMovimentosItem[] itens = historicoVO.getHistoricoMovimentosItemArray();

			for (int i = 0; i < itens.length; i++) {
				itens[i].setRecargaData(itens[i].getRecargaData() != null && !itens[i].getRecargaData().equals("") ? formataData(itens[i].getRecargaData()) : "");
				itens[i].setRecargaDataProc(itens[i].getRecargaDataProc() != null && !itens[i].getRecargaDataProc().equals("") ? formataData(itens[i].getRecargaDataProc()) : "");
			}
			historicoVO.setHistoricoMovimentosItemArray(itens);

			historicoMovimentosForm = form;

			historicoMovimentosForm.setHistoricoMovimentosVO(historicoVO);

		} catch (TuxedoErrorException tee) {
			logLupaPrePago.error("DetalhePrePagoController:pesquisarHistoricoMovimentos(" + user + ") - [" + tee.getMessage() + "]", tee);
			request.setAttribute(ConstantesCRM.SERROR, ERROR);
		} catch (TuxedoWarningException tee) {
			logLupaPrePago.warn("DetalhePrePagoController:pesquisarHistoricoMovimentos(" + user + ") - [" + tee.getMessage() + "]", tee);
			request.setAttribute(ConstantesCRM.SERROR, ERROR);
		} catch (Exception tee) {
			logLupaPrePago.warn("DetalhePrePagoController:pesquisarHistoricoMovimentos(" + user + ") - [" + tee.getMessage() + "]", tee);
			request.setAttribute(ConstantesCRM.SERROR, ERROR);
		}
		logLupaPrePago.debug("DetalhePrePagoController:pesquisarHistoricoMovimentos(" + user + ") >> FINALIZADO");
		request.setAttribute("historicoMovimentosForm", historicoMovimentosForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * Objetivo: Formatar data no formato yyyy-mm-dd hh:mm:ss
	 * 
	 */
	protected String formataData(String data) {
		SimpleDateFormat dateFormat = null;
		if (data != null && !ConstantesCRM.SVAZIO.equals(data)) {
			try {
				dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date dtData = dateFormat.parse(data);
				data = dateFormat.format(dtData);
			} catch (Exception e) {
				logLupaPrePago.error("[DetalhePrePagoController.formataData] Erro ao formatar data dd/MM/yyyy " + data);
				logLupaPrePago.warn("Tentando Formatar a Data formato: yyyy-mm-dd hh:mm:ss " + data);
				try {
					dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Date dtData = dateFormat.parse(data);
					dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					data = dateFormat.format(dtData);
					
				}catch (Exception ex) {
					logLupaPrePago.error("[DetalhePrePagoController.formataData] Erro ao formatar data yyyy-mm-dd hh:mm:ss " + data);
				}
			}
		}
		
		return data;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="favoritosPrePago.jsp"
	 */
	public ActionForward loadFavoritos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		user = ConstantesCRM.getCurrentUser(request.getSession());
		logLupaPrePago.debug("DetalhePrePagoController:loadFavoritos(" + user + ") >> INICIALIZADO");

		try {
			favoritosForm = new FavoritosForm();
			String nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
			int idTipoLinha = Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());
			favoritosForm.setFavoritosVO(consultasPrePagoFacadeControl.getFavoritos(user, nrLinha, idTipoLinha));
			favoritosForm.setErro(ConstantesCRM.SFALSE);

		} catch (TuxedoErrorException tee) {
			logLupaPrePago.error("DetalhePrePagoController:loadFavoritos(" + user + ") - [" + tee.getMessage() + "]", tee);
			favoritosForm.setErro(ConstantesCRM.STRUE);

		} catch (TuxedoWarningException tee) {
			logLupaPrePago.warn("DetalhePrePagoController:loadFavoritos(" + user + ") - [" + tee.getMessage() + "]", tee);
			favoritosForm.setErro(ConstantesCRM.STRUE);

		} catch (Exception e) {
			logLupaPrePago.error("DetalhePrePagoController:loadFavoritos(" + user + ") - [" + e.getMessage() + "]", e);
			favoritosForm.setErro(ConstantesCRM.STRUE);

		} finally {
			logLupaPrePago.debug("DetalhePrePagoController:loadFavoritos(" + user + ") >> FINALIZADO");
			request.setAttribute("favoritosForm", favoritosForm);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		}
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaServicoPrePago.jsp"
	 */
	public ActionForward loadHistoricoAtendimento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (historicoAtendimentoForm == null) {
			historicoAtendimentoForm = new HistoricoAtendimentoForm();
		}

		request.setAttribute("historicoAtendimentoForm", historicoAtendimentoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaServicoPrePago.jsp"
	 */
	public ActionForward pesquisarHistoricoAtendimento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HistoricoAtendimentoForm form = (HistoricoAtendimentoForm) formParam;
		user = ConstantesCRM.getCurrentUser(request.getSession());
		logLupaPrePago.debug("DetalhePrePagoController:pesquisarHistoricoAtendimento(" + user + ") >> INICIALIZADO");
		String nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
		int idTipoLinha = Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dtUtil = new Date(form.getDtIni());
		String dtInicial = dateFormat.format(dtUtil);

		dtUtil = new Date(form.getDtFin());
		String dtFinal = dateFormat.format(dtUtil);

		historicoAtendimentoForm = form;

		try {
			HistoricoAtendimentoVO historicoVO = consultasPrePagoFacadeControl.getHistoricoAtendimento(user, nrLinha, dtInicial, dtFinal, idTipoLinha);

			HistoricoAtendimentoItem[] itens = historicoVO.getHistoricoAtendimentoItemArray();

			for (int i = 0; i < itens.length; i++) {
				itens[i].setData(formataData(itens[i].getData()));
			}

			historicoVO.setHistoricoAtendimentoItemArray(itens);
			historicoAtendimentoForm.setHistoricoAtendimentoVO(historicoVO);

		} catch (TuxedoErrorException tee) {
			logLupaPrePago.error("DetalhePrePagoController:pesquisarHistoricoAtendimento(" + user + ") - [" + tee.getMessage() + "]", tee);
			request.setAttribute(ConstantesCRM.SERROR, ERROR);

		} catch (TuxedoWarningException tee) {
			logLupaPrePago.warn("DetalhePrePagoController:pesquisarHistoricoAtendimento(" + user + ") - [" + tee.getMessage() + "]", tee);
			request.setAttribute(ConstantesCRM.SERROR, ERROR);
		}

		logLupaPrePago.debug("DetalhePrePagoController:pesquisarHistoricoAtendimento(" + user + ") >> FINALIZADO");
		request.setAttribute("historicoAtendimentoForm", historicoAtendimentoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="detalheLinhaPrePago.jsp"
	 */
	public ActionForward loadDetalheLinha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		user = ConstantesCRM.getCurrentUser(request.getSession());
		logLupaPrePago.debug("DetalhePrePagoController:loadDetalheLinha(" + user + ") >> INICIALIZADO");
		detalheLinhaForm = new DetalheLinhaForm();
		String nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
		int idTipoLinha = Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());

		try {
			detalheLinhaForm.setDetalheLinhaVO(consultasPrePagoFacadeControl.getDetalheLinha(user, nrLinha, idTipoLinha));

		} catch (TuxedoErrorException tee) {
			logLupaPrePago.error("DetalhePrePagoController:loadDetalheLinha(" + user + ") - [" + tee.getMessage() + "]", tee);
			request.setAttribute(ConstantesCRM.SERROR, ERROR);

		} catch (TuxedoWarningException tee) {
			logLupaPrePago.warn("DetalhePrePagoController:loadDetalheLinha(" + user + ") - [" + tee.getMessage() + "]", tee);
			request.setAttribute(ConstantesCRM.SERROR, ERROR);
		}

		logLupaPrePago.debug("DetalhePrePagoController:loadDetalheLinha(" + user + ") >> FINALIZADO");
		request.setAttribute("detalheLinhaForm", detalheLinhaForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="servicoPrePago.jsp"
	 */
	public ActionForward loadServico(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		servicoForm = new ServicoForm();

		String nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
		int idTipoLinha = Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());

		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logLupaPrePago.debug("DetalhePrePagoController:loadServico(" + user + ") >> INICIALIZADO");
			servicoForm.setServicoVO(consultasPrePagoFacadeControl.getServico(user, nrLinha, idTipoLinha));

		} catch (TuxedoErrorException tee) {
			logLupaPrePago.error("DetalhePrePagoController:loadServico(" + user + ") - [" + tee.getMessage() + "]", tee);
			request.setAttribute(ConstantesCRM.SERROR, ERROR);

		} catch (TuxedoWarningException tee) {
			logLupaPrePago.error("DetalhePrePagoController:loadServico(" + user + ") - [" + tee.getMessage() + "]", tee);
			request.setAttribute(ConstantesCRM.SERROR, ERROR);
		}

		logLupaPrePago.debug("DetalhePrePagoController:loadServico(" + user + ") >> FINALIZADO");
		request.setAttribute("servicoForm", servicoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="extratoPrePago.jsp"
	 */
	public ActionForward loadExtrato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (extratoForm == null) {
			extratoForm = new ExtratoForm();
		}

		//String codArea = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrCodArea();

		// NÃO EXISTE MAIS DESVIO POR SE TRATAR SEMPRE D NGIM
		/*
		 * if(!codArea.startsWith("4")){ request.setAttribute("idSistemaOrigem","2");
		 * request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this); return
		 * mapping.findForward(ConstantesCRM.SUCCESS, extratoForm); }
		 * 
		 * System.out.println("******************");
		 * System.out.println(((ParametrosVO)request.getSession().getAttribute(
		 * ConstantesCRM.SPARAMETROS)).getIdLinhaSistemaOrigem().equals("2")); System.out.println("******************");
		 */
		//String nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
		request.setAttribute("extratoForm", extratoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="detalheExtratoPrePago.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 * @jpf:forward name="erroMBO" path="erroMBO.jsp"
	 */
	public ActionForward pesquisarExtrato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ExtratoForm form = (ExtratoForm) formParam;
		String nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
		int idTipoLinha = Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());
		user = ConstantesCRM.getCurrentUser(request.getSession());
		logLupaPrePago.debug("DetalhePrePagoController:pesquisarExtrato(" + user + ") >> INICIALIZADO");

		extratoForm = form;

		//SimpleDateFormat dtFormato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String[] arrdataInicial = form.getDtInicial().split("/");
		String[] arrdataFinal = form.getDtFinal().split("/");

		String dataInicial = arrdataInicial[2] + "-" + arrdataInicial[1] + "-" + arrdataInicial[0];
		String dataFinal = arrdataFinal[2] + "-" + arrdataFinal[1] + "-" + arrdataFinal[0];

		try {
			prePagoExtratoVO = consultasPrePagoFacadeControl.getExtrato(user, nrLinha, dataInicial, dataFinal, idTipoLinha);
			extratoForm.setExtratoVO(prePagoExtratoVO);

		} catch (Exception e) {

			if (e.getMessage().indexOf("MBO") != -1) {
				logLupaPrePago.error("DetalhePrePagoController:pesquisarExtrato(" + user + ") - [Erro no NGIN] [" + e.getMessage() + "]", e);
				request.setAttribute("msgErro", "Erro ao acessar NGIN. Tente novamente mais tarde.");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("erroMBO");
			} else {
				logLupaPrePago.error("DetalhePrePagoController:pesquisarExtrato(" + user + ") - [" + e.getMessage() + "]", e);
				FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
				formError.setTarget(ConstantesCRM.FRAMEAPP);
				request.setAttribute(ConstantesCRM.SFORMERROR, formError);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SERROR);
			}
		}
		logLupaPrePago.debug("DetalhePrePagoController:pesquisarExtrato(" + user + ") >> FINALIZADO");
		request.setAttribute("extratoForm", extratoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaSimulador.jsp"
	 * @jpf:forward name="erro" path="erroIntegracao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadSimulador(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		logLupaPrePago.debug("DetalhePrePagoController:loadSimulador() >> INICIALIZADO");
		ParametroVO parametro = null;
		try {
			String nrTelefone = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();

			try {
				parametro = telaInicialFacadeControl.getParametro(user, "ENDPOINT_SIMULADOR_PLANOS");
			} catch (Exception e) {
				logLupaPrePago.error("DetalhePrePagoController::loadSimulador()::Erro ao realizar busca de parametro ENDPOINT do Simulador de Planos", e);
				throw e;
			}
			
			PlanoSimuladoPortTypeProxy planoSimuladoProxy = new PlanoSimuladoPortTypeProxy();
			SecurityHeaderHelper securityHeader = getSecurityHeader();
			planoSimuladoProxy.setSecurityHeaderHelper(securityHeader);
			/* REMOVER */
			//nrTelefone= "1196291163";
			
			planoSimuladoProxy.setEndpoint(parametro.getDsValorParametro());
			

			logLupaPrePago.debug("DetalhePrePagoController:loadSimulador() >> nrTelefone " + nrTelefone);
			ParametrosBuscarPlanoAtual parametrosBuscarPlanoAtual = new ParametrosBuscarPlanoAtual();
			parametrosBuscarPlanoAtual.setNrLinha(nrTelefone);

			Plano plano = planoSimuladoProxy.buscarPlanoAtual(parametrosBuscarPlanoAtual);
			getLupaPrePagoForm().setPlanoAtual(plano);

			ParametrosConsultaPlanosSimulados parametrosConsultaPlanosSimulados = new ParametrosConsultaPlanosSimulados();
			parametrosConsultaPlanosSimulados.setNrLinha(nrTelefone);

			Plano[] listaPlanos = planoSimuladoProxy.consultaPlanosSimulados(parametrosConsultaPlanosSimulados);

			logLupaPrePago.debug("DetalhePrePagoController:loadSimulador() :: Total Planos Simulados " + listaPlanos.length);
			getLupaPrePagoForm().setListaPlanoSimulado(listaPlanos);
		} catch (ErroInfo erroInfo) {
			logLupaPrePago.error("[DetalhePrePagoController.loadSimulador]  ErroInfo: [" + erroInfo.getCodigo() + "] " + erroInfo.getDescricao());
			getLupaPrePagoForm().setMsgErro("Serviço indisponível no momento");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("erro");
		} catch (AxisFault axisFaultException) {
			logLupaPrePago.error("[DetalhePrePagoController.loadSimulador] AxisFault [" + axisFaultException + "]");
			getLupaPrePagoForm().setMsgErro("Serviço indisponível no momento");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("erro");
		} catch (Exception e) {
			logLupaPrePago.error("DetalhePrePagoController:loadSimulador(" + user + ") - [" + e.getMessage() + "]", e);
			getLupaPrePagoForm().setMsgErro("Serviço indisponível no momento");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("erro");
		}
		logLupaPrePago.debug("DetalhePrePagoController:loadSimulador() >> FINALIZADO");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaSimuladorDetalhe.jsp"
	 * @jpf:forward name="erro" path="erroIntegracao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadDetalheSimulador(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		logLupaPrePago.debug("DetalhePrePagoController:loadDetalheSimulador() >> INICIALIZADO");
		try {
			String nmSelPlano = request.getParameter("nmSelPlano");
			ParametroVO parametro = null;
			String nrTelefone = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();

			try {
				parametro = telaInicialFacadeControl.getParametro(user, "ENDPOINT_SIMULADOR_PLANOS");
			} catch (Exception e) {
				logLupaPrePago.error("DetalhePrePagoController::loadDetalheSimulador()::Erro ao realizar busca de parametro ENDPOINT do Simulador de Planos", e);
			}

			PlanoSimuladoPortTypeProxy planoSimuladoProxy = new PlanoSimuladoPortTypeProxy();
			SecurityHeaderHelper securityHeader = getSecurityHeader();
			planoSimuladoProxy.setSecurityHeaderHelper(securityHeader);
			/* REMOVER */
			//nrTelefone= "1196291163";
			
			planoSimuladoProxy.setEndpoint(parametro.getDsValorParametro());

			logLupaPrePago.debug("DetalhePrePagoController:loadDetalheSimulador() >> nrTelefone " + nrTelefone);
			ParametrosDetalhesPlanoSimulado parametrosDetalhesPlanoSimulado = new ParametrosDetalhesPlanoSimulado();
			parametrosDetalhesPlanoSimulado.setNrLinha(nrTelefone);
			parametrosDetalhesPlanoSimulado.setIdPlanoSimulado(nmSelPlano);

			Plano planoEscolhido = planoSimuladoProxy.detalhesPlanoSimulado(parametrosDetalhesPlanoSimulado);
			getLupaPrePagoForm().setPlanoEscolhido(planoEscolhido);

		} catch (ErroInfo erroInfo) {
			logLupaPrePago.error("[DetalhePrePagoController.loadDetalheSimulador]  ErroInfo: [" + erroInfo.getCodigo() + "] " + erroInfo.getDescricao());
			getLupaPrePagoForm().setMsgErro("Serviço indisponível no momento");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("erro");
		} catch (AxisFault axisFaultException) {
			logLupaPrePago.error("[DetalhePrePagoController.loadDetalheSimulador] AxisFault [" + axisFaultException + "]");
			getLupaPrePagoForm().setMsgErro("Serviço indisponível no momento");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("erro");
		} catch (Exception e) {
			logLupaPrePago.error("DetalhePrePagoController:loadDetalheSimulador(" + user + ") - [" + e.getMessage() + "]", e);
			getLupaPrePagoForm().setMsgErro("Serviço indisponível no momento");
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
			logLupaPrePago.error("DetalhePrePagoController::loadDetalheSimulador()::Erro ao realizar a palitagem de Simulador de Planos", e);
		}

		boolean isProtocolo = true;
		try {
			Long.parseLong(nrProtocolo);
		} catch (Exception e) {
			isProtocolo = false;
		}

		if (!nrProtocoloSessao.equals(nrProtocolo) && isProtocolo) {
			request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, nrProtocolo);
			((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setNrProtocolo(nrProtocolo);
			getLupaPrePagoForm().setAtualizacaoProtocolo(true);
			getLupaPrePagoForm().setNrProtocolo(nrProtocolo);
		} else {
			getLupaPrePagoForm().setAtualizacaoProtocolo(false);
		}

		logLupaPrePago.debug("DetalhePrePagoController:loadDetalheSimulador() >> FINALIZADO");
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
	 * @jpf:forward name="erro" path="erroIntegracao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadContatoEnviaEmailSimulador(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		logLupaPrePago.debug("DetalhePrePagoController:loadContatoEnviaEmailSimulador() >> INICIALIZADO");
		try {
			ConsultarTiposEmailVO consultarTiposEmailVO = controlManterAgendamento.consultaTiposEmail(user);
			getLupaPrePagoForm().setConsultarTiposEmailVO(consultarTiposEmailVO);

			ConsultarEmailsVO consultarEmailsVO = controlManterAgendamento.consultaEmail(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaCliente(), user);
			getLupaPrePagoForm().setConsultarEmailsVO(consultarEmailsVO);
		} catch (Exception e) {
			logLupaPrePago.error("DetalhePrePagoController:loadContatoEnviaEmailSimulador(" + user + ") - [" + e.getMessage() + "]", e);
			getLupaPrePagoForm().setMsgErro(e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("erro");
		}
		logLupaPrePago.debug("DetalheFaturaController:loadContatoEnviaEmailSimulador() >> FINALIZADO");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="loadSimulador.do"
	 * @jpf:forward name="erro" path="erroIntegracao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadEnviaMailSimulador(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		logLupaPrePago.debug("DetalhePrePagoController:loadEnviaMailSimulador() >> INICIALIZADO");
		try {
			ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
			String linha = parametros.getNrLinha().substring(2);
			String ddd = parametros.getNrCodArea();

			String dsEmail = request.getParameter("dsEmail");
			String idTipoEmail = request.getParameter("idTipoEmail");
			String dsNovoEmail = request.getParameter("dsNovoEmail");
			String email = ConstantesCRM.SVAZIO;
			if ("NOVO".equals(dsEmail)) {
				controlManterAgendamento.incluiEmail(user, parametros.getIdPessoaCliente(), dsNovoEmail, idTipoEmail, ddd, linha, null);
				email = dsNovoEmail;
			} else {
				email = dsEmail;
			}
			StringBuffer corpoEmail = new StringBuffer(ConstantesCRM.SVAZIO);
			corpoEmail.append("<html><head><style>");
			corpoEmail.append(".FontBlack9px { color: Black; font-family: Verdana; font-size: 9px; }");
			corpoEmail.append(".FontLightGray { color: #686868; font-family: Verdana; font-size: 10px; }");
			corpoEmail.append("</style></head><body><table border='0'>");
			corpoEmail.append("<tr bgcolor='#ffffff'><td><img src='cid:vivogif' border='0'></td></tr>");
			corpoEmail.append("<tr><td>&nbsp;</td></tr></table><table cellpadding='1' class='FontLightGray' bgcolor='#cccccc' cellspacing='1' width='450'>");
			corpoEmail.append("<tr><td bgcolor='#eeeeee' align='left' height='20'><b>Meu Plano:</b> ");
			if (getLupaPrePagoForm().getPlanoAtual().getNome() != null) {
				corpoEmail.append(getLupaPrePagoForm().getPlanoAtual().getNome());
			}
			corpoEmail.append("</td></tr>");
			corpoEmail.append("<tr bgcolor='#ffffff' class='FontLightGray'><td><table class='FontLightGray' width='450' border='0'><tr>");
			corpoEmail.append("<td width='400' align='center'><b>Detalhes do Plano</b></td><td width='100' align='center'><b>Valor Total da conta (m&eacute;dia/m&ecirc;s)</b></td>");
			corpoEmail.append("<td width='100' align='center'><b>Minutos totais incluso no plano</b></td></tr><tr>");
			corpoEmail.append("<td width='400' align='left'>");
			corpoEmail.append(getLupaPrePagoForm().getPlanoAtual().getDescricao());
			corpoEmail.append("</td><td width='100' align='center'>R$&nbsp;");
			corpoEmail.append(getLupaPrePagoForm().getPlanoAtual().getValorAssinaturaFormatado());
			corpoEmail.append("</td><td width='100' align='center'>");
			corpoEmail.append(getLupaPrePagoForm().getPlanoAtual().getStringQtdMinutosFranquia());
			corpoEmail.append("</td></tr></table></td></tr></table><br>");
			corpoEmail.append("<table cellpadding='1' class='FontLightGray' bgcolor='#cccccc' cellspacing='1' width='450'>");
			corpoEmail.append("<tr><td bgcolor='#eeeeee' align='left' height='20'><b>Comparar meu plano com:</b>");
			if (getLupaPrePagoForm().getPlanoEscolhido().getNome() != null) {
				corpoEmail.append(getLupaPrePagoForm().getPlanoEscolhido().getNome());
			}
			corpoEmail.append("</td></tr>");
			corpoEmail.append("<tr bgcolor='#ffffff' class='FontLightGray'><td><table class='FontLightGray' width='450' border='0'><tr>");
			corpoEmail.append("<td width='400' align='center'><b>Detalhes do Plano</b></td><td width='100' align='center'><b>Valor Total da conta*</b></td>");
			corpoEmail.append("<td width='100' align='center'><b>Minutos totais incluso no plano</b></td></tr><tr>");
			corpoEmail.append("<td width='400' align='left'>");
			corpoEmail.append(getLupaPrePagoForm().getPlanoEscolhido().getDescricao());
			corpoEmail.append("</td><td width='100' align='center'>");
			corpoEmail.append(getLupaPrePagoForm().getPlanoEscolhido().getValorAssinaturaFormatado());
			corpoEmail.append("</td><td width='100' align='center'>");
			corpoEmail.append(getLupaPrePagoForm().getPlanoEscolhido().getStringQtdMinutosFranquia());
			corpoEmail.append("</td></tr></table></td></tr></table>");
			corpoEmail.append("<span class='FontBlack9px' align='left'><b>*Estimativa de conta utilizando a m&eacute;dia de consumo (minuto e servi&ccedil;os) dos &uacute;ltimos 3 meses.</b></span>");
			corpoEmail.append("</body></html>");

			logLupaPrePago.debug("DetalhePrePagoController:loadEnviaMailSimulador:: Email " + corpoEmail.toString());
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
			logLupaPrePago.error("DetalhePrePagoController:loadEnviaMailSimulador(" + user + ") - [" + e.getMessage() + "]", e);
			getLupaPrePagoForm().setMsgErro(e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("erro");
		}
		logLupaPrePago.debug("DetalhePrePagoController:loadEnviaMailSimulador() >> FINALIZADO");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}
	
	private SecurityHeaderHelper getSecurityHeader() {
		
		SecurityHeaderHelper securityHeader = new SecurityHeaderHelper();
		securityHeader.setAddWSSecurity(false);
		securityHeader.setAddCabecalhoVivo(true);
		securityHeader.setLoginUsuario("VivoVol/Tav");
		securityHeader.setNomeAplicacao("VivoVivonet");
		securityHeader.setCanalAtendimento(ConstantesCRM.SONE);
		securityHeader.setCodigoSessao(ConstantesCRM.SONE);
		//securityHeader.setUserTimeout(new Integer(10000));
		
		return securityHeader;
	}
    
   

	public static class DetalhesSaldoForm extends ActionForm {

		private static final long serialVersionUID = 1700326158822425131L;

		private DetalhesSaldoVO detalhesSaldoVO;
		private String balanco;

		public DetalhesSaldoForm() {
			detalhesSaldoVO = DetalhesSaldoVO.Factory.newInstance();
			balanco = ConstantesCRM.SVAZIO;
		}

		public void setDetalhesSaldoVO(DetalhesSaldoVO detalhesSaldoVO) {
			this.detalhesSaldoVO = detalhesSaldoVO;
		}

		public DetalhesSaldoVO getDetalhesSaldoVO() {
			return this.detalhesSaldoVO;
		}

		public void setBalanco(String balanco) {
			this.balanco = balanco;
		}

		public String getBalanco() {
			return this.balanco;
		}
	}

	public static class HistoricoMovimentosForm extends ActionForm {

		private static final long serialVersionUID = -5135509317723158947L;

		private HistoricoMovimentosVO historicoMovimentosVO;
		private String dtIni;
		private String dtFin;

		public HistoricoMovimentosForm() {
			dtIni = ConstantesCRM.SVAZIO;
			dtFin = ConstantesCRM.SVAZIO;
			historicoMovimentosVO = HistoricoMovimentosVO.Factory.newInstance();
		}

		public void setHistoricoMovimentosVO(HistoricoMovimentosVO historicoMovimentosVO) {
			this.historicoMovimentosVO = historicoMovimentosVO;
		}

		public HistoricoMovimentosVO getHistoricoMovimentosVO() {
			return this.historicoMovimentosVO;
		}

		public String getDtIni() {
			return this.dtIni;
		}

		public void setDtIni(String dtIni) {
			this.dtIni = dtIni;
		}

		public String getDtFin() {
			return this.dtFin;
		}

		public void setDtFin(String dtFin) {
			this.dtFin = dtFin;
		}
	}

	public static class PromocoesForm extends ActionForm {

		private static final long serialVersionUID = -8389913950636496680L;

		private PromocoesVO promocoesVO;
		private String erro;

		public PromocoesForm() {
			promocoesVO = PromocoesVO.Factory.newInstance();
		}

		public void setPromocoesVO(PromocoesVO promocoesVO) {
			this.promocoesVO = promocoesVO;
		}

		public PromocoesVO getPromocoesVO() {
			return this.promocoesVO;
		}

		public void setErro(String erro) {
			this.erro = erro;
		}

		public String getErro() {
			return this.erro;
		}
	}

	public static class FavoritosForm extends ActionForm {

		private static final long serialVersionUID = 1133556085958396483L;

		private FavoritosVO favoritosVO;
		private String erro;

		public FavoritosForm() {
			favoritosVO = FavoritosVO.Factory.newInstance();
		}

		public void setFavoritosVO(FavoritosVO favoritosVO) {
			this.favoritosVO = favoritosVO;
		}

		public FavoritosVO getFavoritosVO() {
			return this.favoritosVO;
		}

		public void setErro(String erro) {
			this.erro = erro;
		}

		public String getErro() {
			return this.erro;
		}
	}

	public static class HistoricoAtendimentoForm extends ActionForm {

		private static final long serialVersionUID = -5683188806346494915L;

		private HistoricoAtendimentoVO historicoAtendimentoVO;
		private String dtIni;
		private String dtFin;

		public HistoricoAtendimentoForm() {
			historicoAtendimentoVO = HistoricoAtendimentoVO.Factory.newInstance();
			dtIni = ConstantesCRM.SVAZIO;
			dtFin = ConstantesCRM.SVAZIO;
		}

		public void setHistoricoAtendimentoVO(HistoricoAtendimentoVO historicoAtendimentoVO) {
			this.historicoAtendimentoVO = historicoAtendimentoVO;
		}

		public HistoricoAtendimentoVO getHistoricoAtendimentoVO() {
			return this.historicoAtendimentoVO;
		}

		public String getDtIni() {
			return this.dtIni;
		}

		public void setDtIni(String dtIni) {
			this.dtIni = dtIni;
		}

		public String getDtFin() {
			return this.dtFin;
		}

		public void setDtFin(String dtFin) {
			this.dtFin = dtFin;
		}
	}

	public static class DetalheLinhaForm extends ActionForm {

		private static final long serialVersionUID = 5862261638930639006L;

		private DetalheLinhaVO detalheLinhaVO;

		public DetalheLinhaForm() {
			detalheLinhaVO = DetalheLinhaVO.Factory.newInstance();
		}

		public void setDetalheLinhaVO(DetalheLinhaVO detalheLinhaVO) {
			this.detalheLinhaVO = detalheLinhaVO;
		}

		public DetalheLinhaVO getDetalheLinhaVO() {
			return this.detalheLinhaVO;
		}
	}

	public static class ServicoForm extends ActionForm {

		private static final long serialVersionUID = -8962499317115004818L;

		private ServicoVO servicoVO;

		public ServicoForm() {
			servicoVO = ServicoVO.Factory.newInstance();
		}

		public void setServicoVO(ServicoVO servicoVO) {
			this.servicoVO = servicoVO;
		}

		public ServicoVO getServicoVO() {
			return this.servicoVO;
		}
	}

	public static class ExtratoForm extends ActionForm {

		private static final long serialVersionUID = -2876938126846464625L;

		private ExtratoVO extratoVO;
		private String dtInicial;
		private String dtFinal;

		public ExtratoForm() {
			dtInicial = ConstantesCRM.SVAZIO;
			dtFinal = ConstantesCRM.SVAZIO;
			extratoVO = ExtratoVO.Factory.newInstance();
			extratoVO.addNewBuffer();
			extratoVO.getBuffer().addNewEXTRACTODETALHADO();
			extratoVO.getBuffer().getEXTRACTODETALHADO().addNewCABECALHO();
			extratoVO.getBuffer().getEXTRACTODETALHADO().addNewMENSAGENS();
			extratoVO.getBuffer().getEXTRACTODETALHADO().addNewTABELA();
		}

		public void setExtratoVO(ExtratoVO extratoVO) {
			this.extratoVO = extratoVO;
		}

		public ExtratoVO getExtratoVO() {
			return this.extratoVO;
		}

		public String getDtInicial() {
			return this.dtInicial;
		}

		public void setDtInicial(String dtInicial) {
			this.dtInicial = dtInicial;
		}

		public String getDtFinal() {
			return this.dtFinal;
		}

		public void setDtFinal(String dtFinal) {
			this.dtFinal = dtFinal;
		}
	}

	public static class LupaPrePagoForm extends ActionForm {

		private static final long serialVersionUID = -8903489922062581782L;

		private String msgErro;
		private ConsultarTiposEmailVO consultarTiposEmailVO = null;
		private ConsultarEmailsVO consultarEmailsVO = null;
		private boolean atualizacaoProtocolo = false;
		private String nrProtocolo;

		/* Paramentros Simulador Plano */
		private Plano planoAtual;
		private Plano[] listaPlanoSimulado;
		private Plano planoEscolhido;

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

	}

	public LupaPrePagoForm getLupaPrePagoForm() {
		return this.lupaPrePagoForm;
	}

	public DetalhesSaldoForm getDetalhesSaldoForm() {
		return this.detalhesSaldoForm;
	}

	public void setDetalhesSaldoForm(DetalhesSaldoForm detalhesSaldoForm) {
		this.detalhesSaldoForm = detalhesSaldoForm;
	}

	public HistoricoMovimentosForm getHistoricoMovimentosForm() {
		return this.historicoMovimentosForm;
	}

	public void setHistoricoMovimentosForm(HistoricoMovimentosForm historicoMovimentosForm) {
		this.historicoMovimentosForm = historicoMovimentosForm;
	}

	public PromocoesForm getPromocoesForm() {
		return this.promocoesForm;
	}

	public void setPromocoesForm(PromocoesForm promocoesForm) {
		this.promocoesForm = promocoesForm;
	}

	public FavoritosForm getFavoritosForm() {
		return this.favoritosForm;
	}

	public void setFavoritosForm(FavoritosForm favoritosForm) {
		this.favoritosForm = favoritosForm;
	}

	public HistoricoAtendimentoForm getHistoricoAtendimentoForm() {
		return this.historicoAtendimentoForm;
	}

	public void setFavoritosForm(HistoricoAtendimentoForm historicoAtendimentoForm) {
		this.historicoAtendimentoForm = historicoAtendimentoForm;
	}

	public DetalheLinhaForm getDetalheLinhaForm() {
		return this.detalheLinhaForm;
	}

	public void setDetalheLinhaForm(DetalheLinhaForm detalheLinhaForm) {
		this.detalheLinhaForm = detalheLinhaForm;
	}

	public ServicoForm getServicoForm() {
		return this.servicoForm;
	}

	public void setServicoForm(ServicoForm servicoForm) {
		this.servicoForm = servicoForm;
	}

	public ExtratoForm getExtratoForm() {
		return this.extratoForm;
	}

	public void setExtratoForm(ExtratoForm extratoForm) {
		this.extratoForm = extratoForm;
	}

	public ExtratoVO getPrePagoExtratoVO() {
		return this.prePagoExtratoVO;
	}

	public void setPrePagoExtratoVO(ExtratoVO getPrePagoExtratoVO) {
		this.prePagoExtratoVO = getPrePagoExtratoVO;
	}
}
