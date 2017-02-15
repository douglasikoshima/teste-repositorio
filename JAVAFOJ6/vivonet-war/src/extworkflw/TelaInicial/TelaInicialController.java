package extworkflw.TelaInicial;

import java.io.BufferedOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer.Form;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import noNamespace.MsgDocument;

import org.apache.axis.AxisFault;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.xmlbeans.XmlOptions;

import senha.validarCliente.ValidarClienteController.ValidarClienteForm;
import sun.text.Normalizer;
import weblogic.wtc.jatmi.TPReplyException;
import workflow.Relacionamento.RelacionamentoController.RelacionamentoForm;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.listapup.webservice.ConsultarLinhaPUPServiceHttpBindingStub;
import br.com.indrasistemas.vivoservices.listapup.webservice.ConsultarLinhaPUPServiceLocator;
import br.com.indrasistemas.vivoservices.listapup.webservice.GravarLinhaPUPServicePortTypeProxy;
import br.com.indrasistemas.vivoservices.listapup.webservice.to.ResultadoLinhaPUPTO;
import br.com.indrasistemas.vivoservices.to.LinhaPUPWSTO;
import br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaVODocument.AdmContatoFolhaVO;
import br.com.vivo.fo.atendimento.vo.AbreProtocoloOutTODocument.AbreProtocoloOutTO;
import br.com.vivo.fo.atendimento.vo.AbreProtocoloTODocument.AbreProtocoloTO;
import br.com.vivo.fo.atendimento.vo.AlterProtocoloOutTODocument.AlterProtocoloOutTO;
import br.com.vivo.fo.atendimento.vo.FechaProtocoloTODocument.FechaProtocoloTO;
import br.com.vivo.fo.atendimento.vo.GetDadosProtocoloOutTODocument.GetDadosProtocoloOutTO;
import br.com.vivo.fo.cliente.ClienteUtils;
import br.com.vivo.fo.cliente.vo.AbaServicosFiltroVODocument.AbaServicosFiltroVO;
import br.com.vivo.fo.cliente.vo.AjaxErrorHandlerVODocument.AjaxErrorHandlerVO;
import br.com.vivo.fo.cliente.vo.AlterProtocoloTODocument.AlterProtocoloTO;
import br.com.vivo.fo.cliente.vo.ComunicacaoVODocument.ComunicacaoVO;
import br.com.vivo.fo.cliente.vo.DadosChipVODocument;
import br.com.vivo.fo.cliente.vo.DadosChipVODocument.DadosChipVO;
import br.com.vivo.fo.cliente.vo.DesbloqueioGsmVODocument.DesbloqueioGsmVO;
import br.com.vivo.fo.cliente.vo.DetalheLinhaVODocument.DetalheLinhaVO;
import br.com.vivo.fo.cliente.vo.DetalhesSaldoItemDocument.DetalhesSaldoItem;
import br.com.vivo.fo.cliente.vo.DetalhesSaldoVODocument.DetalhesSaldoVO;
import br.com.vivo.fo.cliente.vo.DocumentoVODocument.DocumentoVO;
import br.com.vivo.fo.cliente.vo.EnderecoBaseVODocument.EnderecoBaseVO;
import br.com.vivo.fo.cliente.vo.FavoritosVODocument.FavoritosVO;
import br.com.vivo.fo.cliente.vo.HistoricoAtendimentoItemDocument.HistoricoAtendimentoItem;
import br.com.vivo.fo.cliente.vo.HistoricoAtendimentoVODocument.HistoricoAtendimentoVO;
import br.com.vivo.fo.cliente.vo.HistoricoServicosVODocument.HistoricoServicosVO;
import br.com.vivo.fo.cliente.vo.LFContasFaturamentoDocument.LFContasFaturamento;
import br.com.vivo.fo.cliente.vo.LFLinhasDocument.LFLinhas;
import br.com.vivo.fo.cliente.vo.LupaCarteirizacaoPFVODocument.LupaCarteirizacaoPFVO;
import br.com.vivo.fo.cliente.vo.LupaCarteirizacaoPJVODocument.LupaCarteirizacaoPJVO;
import br.com.vivo.fo.cliente.vo.LupaClienteVODocument.LupaClienteVO;
import br.com.vivo.fo.cliente.vo.LupaFaturamentoPosVODocument.LupaFaturamentoPosVO;
import br.com.vivo.fo.cliente.vo.LupaLinhaAbasGSMVODocument.LupaLinhaAbasGSMVO;
import br.com.vivo.fo.cliente.vo.LupaLinhaVODocument.LupaLinhaVO;
import br.com.vivo.fo.cliente.vo.LupaLinhaVODocument.LupaLinhaVO.DadosLupaLinha;
import br.com.vivo.fo.cliente.vo.LupaLinhaVODocument.LupaLinhaVO.FiltroLinhaVO;
import br.com.vivo.fo.cliente.vo.LupaLinhaVODocument.LupaLinhaVO.ListaDadosLupaLinha;
import br.com.vivo.fo.cliente.vo.LupaSegmentacaoVODocument.LupaSegmentacaoVO;
import br.com.vivo.fo.cliente.vo.LupaSegmentacaoVODocument.LupaSegmentacaoVO.ListaSegmentacao;
import br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.cliente.vo.PesquisaTIVODocument.PesquisaTIVO;
import br.com.vivo.fo.cliente.vo.PortabilidadeVODocument.PortabilidadeVO;
import br.com.vivo.fo.cliente.vo.PrePagoRetornoValidaLinhaDocument.PrePagoRetornoValidaLinha;
import br.com.vivo.fo.cliente.vo.PromocoesVODocument.PromocoesVO;
import br.com.vivo.fo.cliente.vo.ReducaoVelocidadeItemDocument.ReducaoVelocidadeItem;
import br.com.vivo.fo.cliente.vo.ReducaoVelocidadeVODocument.ReducaoVelocidadeVO;
import br.com.vivo.fo.cliente.vo.ReloadLinhaVODocument.ReloadLinhaVO;
import br.com.vivo.fo.cliente.vo.StatusLinhaVODocument.StatusLinhaVO;
import br.com.vivo.fo.cliente.vo.TIClienteDocument.TICliente;
import br.com.vivo.fo.cliente.vo.TIDocumentoDocument.TIDocumento;
import br.com.vivo.fo.cliente.vo.TIFaturamentoDocument.TIFaturamento;
import br.com.vivo.fo.cliente.vo.TILinhaDocument.TILinha;
import br.com.vivo.fo.cliente.vo.TelaInicialVODocument.TelaInicialVO;
import br.com.vivo.fo.cliente.vo.TipoLinhaVODocument.TipoLinhaVO;
import br.com.vivo.fo.cliente.vo.TrackingAparelhosVODocument.TrackingAparelhosVO;
import br.com.vivo.fo.cliente.vo.TrackingAparelhosVODocument.TrackingAparelhosVO.ComparaTrackingAparelhoVO;
import br.com.vivo.fo.cliente.vo.TrackingAparelhosVODocument.TrackingAparelhosVO.DetalheTrackingAparelhoVO;
import br.com.vivo.fo.cliente.vo.TrackingAparelhosVODocument.TrackingAparelhosVO.ListaTrackingAparelhoVO;
import br.com.vivo.fo.cliente.vo.UsuarioLinhaVODocument.UsuarioLinhaVO;
import br.com.vivo.fo.commons.utils.GetParametro;
import br.com.vivo.fo.commons.utils.StringUtilStatic;
import br.com.vivo.fo.commons.utils.businessDelegate.JATMIBusinessDelegate;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.cliente.ativacaoServico.AtivacaoServicoFacade;
import br.com.vivo.fo.ctrls.cliente.devicemanager.DeviceManager;
import br.com.vivo.fo.ctrls.cliente.devicemanager.dbclasses.LogAtualizacaoParametrosAparelho;
import br.com.vivo.fo.ctrls.cliente.prePago.ConsultasPrePagoFacade;
import br.com.vivo.fo.ctrls.cliente.prePago.PrePagoFacade;
import br.com.vivo.fo.ctrls.cliente.protocolo.ProtocoloFacade;
import br.com.vivo.fo.ctrls.cliente.servico.ServicoFacade;
import br.com.vivo.fo.ctrls.cliente.telaInicial.TelaInicialFacade;
import br.com.vivo.fo.ctrls.cliente.telaInicial.detalheFatura.DetalheFaturaFacade;
import br.com.vivo.fo.ctrls.senha.cti.TransferirFacade;
import br.com.vivo.fo.ctrls.workflow.atendimento.AtendimentoFacade;
import br.com.vivo.fo.ctrls.workflow.monitoramento.MonitoramentoFacade;
import br.com.vivo.fo.ctrls.workflow.registrarContato.RegistrarContatoFacade;
import br.com.vivo.fo.ctrls.workflow.relacionamento.RelacionamentoFacade;
import br.com.vivo.fo.exception.TuxedoErrorException;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.senha.vo.RamaisUraVODocument.RamaisUraVO;
import br.com.vivo.fo.senha.vo.RamaisVODocument.RamaisVO;
import br.com.vivo.fo.servico.vo.ServicoVODocument.ServicoVO;
import br.com.vivo.fo.usuario.vo.ControleAcessoEnvioVODocument.ControleAcessoEnvioVO;
import br.com.vivo.fo.usuario.vo.RuleVODocument;
import br.com.vivo.fo.usuario.vo.RuleVODocument.RuleVO;
import br.com.vivo.fo.workflow.vo.AtendimentoArvoreFiltrosVODocument.AtendimentoArvoreFiltrosVO;
import br.com.vivo.fo.workflow.vo.AtendimentoPesquisaVODocument.AtendimentoPesquisaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoPesquisaVODocument.AtendimentoPesquisaVO.GrupoPesquisa;
import br.com.vivo.fo.workflow.vo.AtendimentoRelacionamentosVODocument;
import br.com.vivo.fo.workflow.vo.AtendimentoRelacionamentosVODocument.AtendimentoRelacionamentosVO;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO;
import br.com.vivo.fo.workflow.vo.ChamadaTelefonicaVODocument.ChamadaTelefonicaVO;
import br.com.vivo.fo.workflow.vo.LinhaVODocument.LinhaVO;
import br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument.RetornoWFCTIVO;
import br.com.vivo.fo.workflow.vo.WFExecucaoDocument.WFExecucao;
import br.com.vivo.fo.workflow.vo.WFGrupoVODocument.WFGrupoVO;
import br.com.vivo.fo.xml.XmlManager;
import br.com.vivo.www.SN.DesbloqueioIPhone.ConfirmarDesbloqueioIPhoneRequest;
import br.com.vivo.www.SN.DesbloqueioIPhone.ConfirmarDesbloqueioIPhoneResponse;
import br.com.vivo.www.SN.DesbloqueioIPhone.DesbloquearIPhoneRequest;
import br.com.vivo.www.SN.DesbloqueioIPhone.DesbloqueioIPhoneBindingStub;
import br.com.vivo.www.SN.DesbloqueioIPhone.DesbloqueioIPhonePortType;
import br.com.vivo.www.SN.DesbloqueioIPhone.DesbloqueioIPhonePortTypeProxy;
import cliente.URLErro;
import cliente.TelaInicial.services.IntegradorPortais;

import com.indracompany.actions.AbstractAction;
import com.indracompany.ws.aparelhoservice.AparelhoPortTypeProxy;
import com.indracompany.ws.aparelhoservice.ParametrosAtualizarParametrosAparelho;
import com.indracompany.ws.aparelhoservice.ParametrosConsultarHistoricoAparelhos;
import com.indracompany.ws.aparelhoservice.ParametrosConsultarModeloAparelho;
import com.indracompany.ws.aparelhoservice.SecurityHeaderHelper;
import com.indracompany.ws.aparelhoservice.to.AparelhoLinha;
import com.indracompany.ws.aparelhoservice.to.Produto;
import commons.errors.AjaxError;
import commons.errors.FormError;

@SuppressWarnings({"deprecation"})
public class TelaInicialController extends AbstractAction {

	private static final long serialVersionUID = 2744827741440730881L;

	private global.Global globalApp = new global.Global();

	private final String RAIZ_PRINCIPAL = "PAI";
	private final String TRACKING_FOLHA = "VIVO/SERVIÇOS/APARELHOS/CONSULTA DE ENTREGA DE APARELHO";
	private final String PATH_SIMBROWSING = "VIVO/SERVIÇOS/SERVIÇOS DE VALOR AGREGADO/CONFIGURAÇÃO VIVO CHIP";
	private final String PARAMETRO_ENDPOINT_UNLOCKIPHONE = "PARAMETRO_ENDPOINT_UNLOCKIPHONE";
	private final String PALITAGEM_PESQUISA_UNLOCKIPHONE = "PALITAGEM_PESQUISA_UNLOCKIPHONE";
	private final String PALITAGEM_DESBLOQUEIO_UNLOCKIPHONE = "PALITAGEM_DESBLOQUEIO_UNLOCKIPHONE";
	private final String PARAMETRO_ENDPOINT_APARELHO = "APARELHO_CONSULTAMODELO_ENDPOINT";

	private final int ID_SISTEMA_ORIGEM_VIVONET = 7;
	private final int IDTIPOABERTURAPROTOCOLO_LINHACLIENTE = 3;
	private final int IDTIPOABERTURAPROTOCOLO_PESSOA = 4;
	private final int IDTIPOABERTURAPROTOCOLO_CONTA = 1;
	private final int IDTIPOABERTURAPROTOCOLO_LINHA = 2;

	private static final NumberFormat DEFAULT_DECIMAL_FORMAT = new DecimalFormat("#.0#################", new DecimalFormatSymbols(java.util.Locale.US));
	private static final double MEGABYTE_1005 = 5.90;
	private static final long MEGABYTE_1006 = 1024L * 1024L;
	private static final String  PARAM_WSEP_PUP_CONSULTA = "WSEP_PUP_CONSULTA";
	private static final String  PARAM_WSEP_PUP_GRAVACAO = "WSEP_PUP_GRAVACAO";

	@EJB
	private RelacionamentoFacade relacionamentoFacade;

	@EJB
	private AtendimentoFacade atendimentoFacade;

	@EJB
	private TelaInicialFacade telaInicialFacadeControl;

	@EJB
	private PrePagoFacade prePagoFacade;

	@EJB
	private RegistrarContatoFacade registrarContatoFacade;

	@EJB
	private TransferirFacade transferirFacade;

	@EJB
	private ConsultasPrePagoFacade consultasPrePagoFacadeControl;

	@EJB
	private AtivacaoServicoFacade consultaServicosFacadeControl;

	@EJB
	private MonitoramentoFacade monitoramentoFacade;

	@EJB
	private ServicoFacade servicoFacade;

	@EJB
	private DetalheFaturaFacade detalheFaturaFacade;

	@EJB
	private ProtocoloFacade protocoloFacade;

	@EJB
	private DeviceManager deviceManager;

	private TelaInicialVO telaInicial = null;

	static final String NULO_VALUE = "NULO";
	static final String CARTEIRIZACAO_NULO_DEFAULT_MSG = "SEM CARTEIRIZAÇÃO";
	static final String SEGMENTACAO_NULO_DEFAULT_MSG = "SEM SEGMENTAÇÃO";
	static final String MENSAGEM_GERAL = "Houve um erro durante carregamento dos dados. Tente novamente dentro de alguns instantes ou contate o Helpdesk.";
	static final String PATH_PALITAGEM_BUSCA_IMEI = ConstantesCRM.SVAZIO;

	private static final int idPessoaProspect = 26;

	// ALTERAR PARA CHAMADA DE USUARIO DO SISTEMA
	private String user = null;
	// ALTERAR PARA CHAMADA DE USUARIO DO SISTEMA
	private AbaServico abaServ;

	public AbaServico getAbaServ() {
		return this.abaServ;
	}

	public void setAbaServ(AbaServico value) {
		this.abaServ = value;
	}

	private AbaDadosChip abaDadosChip;

	public void setAbaDadosChip(AbaDadosChip value) {
		this.abaDadosChip = value;
	}

	public AbaDadosChip getAbaDadosChip() {
		return this.abaDadosChip;
	}

	private AbaDesbloqueioAparelho desbloqueioImei;

	public void setDesbloqueioImei(AbaDesbloqueioAparelho value) {
		this.desbloqueioImei = value;
	}

	public AbaDesbloqueioAparelho getDesbloqueioImei() {
		return this.desbloqueioImei;
	}

	private HistoricoAtendimentoVO servicos;

	public HistoricoAtendimentoVO getServicos() {
		return this.servicos;
	}

	public void setServicos(HistoricoAtendimentoVO value) {
		this.servicos = value;
	}

	private TIForm tiForm;

	public TIForm getTiForm() {
		if (this.tiForm == null) {
			this.tiForm = new TIForm();
		}
		return this.tiForm;
	}

	public void setTiForm(TIForm tiForm) {
		this.tiForm = tiForm;
	}

	private ValidarClienteForm validarClienteForm;

	public ValidarClienteForm getValidarClienteForm() {
		return this.validarClienteForm;
	}

	public void setValidarClienteForm(ValidarClienteForm form) {
		this.validarClienteForm = form;
	}

	private PesquisaForm pesquisaForm;

	public PesquisaForm getPesquisaForm() {
		return this.pesquisaForm;
	}

	public void setPesquisaForm(PesquisaForm pesquisaForm) {
		this.pesquisaForm = pesquisaForm;
	}

	private LupaSegmentacaoVO lupaSegmentacaoVO;
	private SegmentacaoForm segmentacaoForm;
	private LupaLinhaVO lupaLinhaVO;
	private FiltroLinhaVO filtroLinhaVO;

	private LupaLinhaForm lupaLinhaForm;

	public LupaLinhaForm getLupaLinhaForm() {
		if (this.lupaLinhaForm == null) {
			this.lupaLinhaForm = new LupaLinhaForm();
		}
		return this.lupaLinhaForm;
	}

	public SegmentacaoForm getSegmentacaoForm() {
		return this.segmentacaoForm;
	}

	public void setSegmentacaoForm(SegmentacaoForm segmentacaoForm) {
		this.segmentacaoForm = segmentacaoForm;
	}

	private LupaCarteirizacaoPFVO lupaCarteirizacaoPFVO;
	private CarteiraPFForm carteiraPFForm;

	public CarteiraPFForm getCarteiraPFForm() {
		return this.carteiraPFForm;
	}

	public void setCarteiraPFForm(CarteiraPFForm carteiraPFForm) {
		this.carteiraPFForm = carteiraPFForm;
	}

	private LupaCarteirizacaoPJVO lupaCarteirizacaoPJVO;
	private CarteiraPJForm carteiraPJForm;

	public CarteiraPJForm getCarteiraPJForm() {
		return this.carteiraPJForm;
	}

	public void setCarteiraPJForm(CarteiraPJForm carteiraPJForm) {
		this.carteiraPJForm = carteiraPJForm;
	}

	private DetalhesSaldoForm detalhesSaldoForm;

	public DetalhesSaldoForm getDetalhesSaldoForm() {
		return this.detalhesSaldoForm;
	}

	public void setDetalhesSaldoForm(DetalhesSaldoForm detalhesSaldoForm) {
		this.detalhesSaldoForm = detalhesSaldoForm;
	}

	private HistoricoAtendimentoForm historicoAtendimentoForm;

	public HistoricoAtendimentoForm getHistoricoAtendimentoForm() {
		return this.historicoAtendimentoForm;
	}

	public void seHistoricoAtendimentoForm(HistoricoAtendimentoForm historicoAtendimentoForm) {
		this.historicoAtendimentoForm = historicoAtendimentoForm;
	}

	private RelacionamentoForm relacionamentoForm;

	public RelacionamentoForm getRelacionamentoForm() {
		if (this.relacionamentoForm == null) {
			this.relacionamentoForm = new RelacionamentoForm();
		}
		return this.relacionamentoForm;
	}

	public void setRelacionamentoForm(RelacionamentoForm form) {
		this.relacionamentoForm = form;
	}

	private TrackingForm trackingForm;

	public TrackingForm getTrackingForm() {
		if (this.trackingForm == null) {
			this.trackingForm = new TrackingForm();
		}
		return this.trackingForm;
	}

	public void setTrackingForm(TrackingForm form) {
		this.trackingForm = form;
	}

	private AlteracaoNumeroSMSForm alteracaoNumeroSMSForm;

	public AlteracaoNumeroSMSForm getAlteracaoNumeroSMSForm() {
		if (this.alteracaoNumeroSMSForm == null) {
			this.alteracaoNumeroSMSForm = new AlteracaoNumeroSMSForm();
		}
		return this.alteracaoNumeroSMSForm;
	}

	public void setAlteracaoNumeroSMSForm(AlteracaoNumeroSMSForm arg) {
		this.alteracaoNumeroSMSForm = arg;
	}

	private LupaLinhaAbasGSMForm lupaLinhaAbasGSMForm;

	public LupaLinhaAbasGSMForm getLupaLinhaAbasGSMForm() {
		if (this.lupaLinhaAbasGSMForm == null) {
			this.lupaLinhaAbasGSMForm = new LupaLinhaAbasGSMForm();
		}
		return this.lupaLinhaAbasGSMForm;
	}

	public void setLupaLinhaAbasGSMForm(LupaLinhaAbasGSMForm form) {
		this.lupaLinhaAbasGSMForm = form;
	}

	private transient Logger logTelaInicial = new Logger("telainicial");
	private transient Logger logLupaLinha = new Logger("lupalinha");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("getPesquisaHistorico".equals(mapping.getParameter())) {
			return getPesquisaHistorico(mapping, form, request, response);
		} else if ("pesquisarSubEstado".equals(mapping.getParameter())) {
			return pesquisarSubEstado(mapping, form, request, response);
		} else if ("obterArvoreContato".equals(mapping.getParameter())) {
			return obterArvoreContato(mapping, form, request, response);
		} else if ("expandeArvoreContato".equals(mapping.getParameter())) {
			return expandeArvoreContato(mapping, form, request, response);
		} else if ("getProcessosByProtocolo".equals(mapping.getParameter())) {
			return getProcessosByProtocolo(mapping, form, request, response);
		} else if ("carregaParametrosCTI".equals(mapping.getParameter())) {
			return carregaParametrosCTI(mapping, form, request, response);
		} else if ("carregarLigacao".equals(mapping.getParameter())) {
			return carregarLigacao(mapping, form, request, response);
		} else if ("recebeLigacao".equals(mapping.getParameter())) {
			return recebeLigacao(mapping, form, request, response);
		} else if ("TelaInicial".equals(mapping.getParameter())) {
			return TelaInicial(mapping, form, request, response);
		} else if ("transferirUraAction".equals(mapping.getParameter())) {
			return transferirUraAction(mapping, form, request, response);
		} else if ("idPosAction".equals(mapping.getParameter())) {
			return idPosAction(mapping, form, request, response);
		} else if ("carregaGrupo".equals(mapping.getParameter())) {
			return carregaGrupo(mapping, form, request, response);
		} else if ("transferirCarregar".equals(mapping.getParameter())) {
			return transferirCarregar(mapping, form, request, response);
		} else if ("transferirRamalAction".equals(mapping.getParameter())) {
			return transferirRamalAction(mapping, form, request, response);
		} else if ("terminarAction".equals(mapping.getParameter())) {
			return terminarAction(mapping, form, request, response);
		} else if ("iniciarChamada".equals(mapping.getParameter())) {
			return iniciarChamada(mapping, form, request, response);
		} else if ("salvar".equals(mapping.getParameter())) {
			return salvar(mapping, form, request, response);
		} else if ("ClienteDone".equals(mapping.getParameter())) {
			return ClienteDone(mapping, form, request, response);
		} else if ("loadCarteirizacao".equals(mapping.getParameter())) {
			return loadCarteirizacao(mapping, form, request, response);
		} else if ("loadSegmentacao".equals(mapping.getParameter())) {
			return loadSegmentacao(mapping, form, request, response);
		} else if ("loadCarteiraPF".equals(mapping.getParameter())) {
			return loadCarteiraPF(mapping, form, request, response);
		} else if ("DetalheLinha".equals(mapping.getParameter())) {
			return DetalheLinha(mapping, form, request, response);
		} else if ("getQuantidadeLinhas".equals(mapping.getParameter())) {
			return getQuantidadeLinhas(mapping, form, request, response);
		} else if ("DetalheFaturaDone".equals(mapping.getParameter())) {
			return DetalheFaturaDone(mapping, form, request, response);
		} else if ("pesquisaCliente".equals(mapping.getParameter())) {
			return pesquisaCliente(mapping, form, request, response);
		} else if ("linha".equals(mapping.getParameter())) {
			return linha(mapping, form, request, response);
		} else if ("fatura".equals(mapping.getParameter())) {
			return fatura(mapping, form, request, response);
		} else if ("loadDados".equals(mapping.getParameter())) {
			return loadDados(mapping, form, request, response);
		} else if ("pesquisarLinha".equals(mapping.getParameter())) {
			return pesquisarLinha(mapping, form, request, response);
		} else if ("loadAbaGeral".equals(mapping.getParameter())) {
			return loadAbaGeral(mapping, form, request, response);
		} else if ("pesquisarDetalheLinha".equals(mapping.getParameter())) {
			return pesquisarDetalheLinha(mapping, form, request, response);
		} else if ("modeloAparelho".equals(mapping.getParameter())) {
			return modeloAparelho(mapping, form, request, response);
		} else if ("consultarLinhaPUP".equals(mapping.getParameter())) {
			return consultarLinhaPUP(mapping, form, request, response);
		} else if ("gravarLinhaPUP".equals(mapping.getParameter())) {
			return gravarLinhaPUP(mapping, form, request, response);
		} else if ("lupaLinhaAbaHistAparelho".equals(mapping.getParameter())) {
			return lupaLinhaAbaHistAparelho(mapping, form, request, response);
		} else if ("lupaLinhaAbaParametros".equals(mapping.getParameter())) {
			return lupaLinhaAbaParametros(mapping, form, request, response);
		} else if ("lupaLinhaAbaParametrosAtualiza".equals(mapping.getParameter())) {
			return lupaLinhaAbaParametrosAtualiza(mapping, form, request, response);
		} else if ("personalizaChip".equals(mapping.getParameter())) {
			return personalizaChip(mapping, form, request, response);
		} else if ("loadAbaServico".equals(mapping.getParameter())) {
			return loadAbaServico(mapping, form, request, response);
		} else if ("getDadosChip".equals(mapping.getParameter())) {
			return getDadosChip(mapping, form, request, response);
		} else if ("showAbaIMEI".equals(mapping.getParameter())) {
			return showAbaIMEI(mapping, form, request, response);
		} else if ("desbloqueioAparelhoGsm".equals(mapping.getParameter())) {
			return desbloqueioAparelhoGsm(mapping, form, request, response);
		} else if ("pesquisarIphone".equals(mapping.getParameter())) {
			return pesquisarIphone(mapping, form, request, response);
		} else if ("showAbaImeiIphone".equals(mapping.getParameter())) {
			return showAbaImeiIphone(mapping, form, request, response);
		} else if ("desbloqueioAparelhoIphone".equals(mapping.getParameter())) {
			return desbloqueioAparelhoIphone(mapping, form, request, response);
		} else if ("loadLinha".equals(mapping.getParameter())) {
			return loadLinha(mapping, form, request, response);
		} else if ("filtroAbaServico".equals(mapping.getParameter())) {
			return filtroAbaServico(mapping, form, request, response);
		} else if ("loadFaturaIni".equals(mapping.getParameter())) {
			return loadFaturaIni(mapping, form, request, response);
		} else if ("loadFaturamento".equals(mapping.getParameter())) {
			return loadFaturamento(mapping, form, request, response);
		} else if ("loadPrePago".equals(mapping.getParameter())) {
			return loadPrePago(mapping, form, request, response);
		} else if ("pesquisarHistoricoAtendimento".equals(mapping.getParameter())) {
			return pesquisarHistoricoAtendimento(mapping, form, request, response);
		} else if ("irPesquisaNome".equals(mapping.getParameter())) {
			return irPesquisaNome(mapping, form, request, response);
		} else if ("irCadastroProspect".equals(mapping.getParameter())) {
			return irCadastroProspect(mapping, form, request, response);
		} else if ("limpaFiltro".equals(mapping.getParameter())) {
			return limpaFiltro(mapping, form, request, response);
		} else if ("carregaNaoClienteXml".equals(mapping.getParameter())) {
			return carregaNaoClienteXml(mapping, form, request, response);
		} else if ("carregaTelaInicialXml".equals(mapping.getParameter())) {
			return carregaTelaInicialXml(mapping, form, request, response);
		} else if ("verificaLinhaPreAtiva".equals(mapping.getParameter())) {
			return verificaLinhaPreAtiva(mapping, form, request, response);
		} else if ("prosseguirBlindagem".equals(mapping.getParameter())) {
			return prosseguirBlindagem(mapping, form, request, response);
		} else if ("chamaRelacionamento".equals(mapping.getParameter())) {
			return chamaRelacionamento(mapping, form, request, response);
		} else if ("getHistoricoServicos".equals(mapping.getParameter())) {
			return getHistoricoServicos(mapping, form, request, response);
		} else if ("refreshCombos".equals(mapping.getParameter())) {
			return refreshCombos(mapping, form, request, response);
		} else if ("gravarComentarioTracking".equals(mapping.getParameter())) {
			return gravarComentarioTracking(mapping, form, request, response);
		} else if ("trackingLista".equals(mapping.getParameter())) {
			return trackingLista(mapping, form, request, response);
		} else if ("trackingDetalhe".equals(mapping.getParameter())) {
			return trackingDetalhe(mapping, form, request, response);
		} else if ("trackingComparar".equals(mapping.getParameter())) {
			return trackingComparar(mapping, form, request, response);
		} else if ("alterarNumeroSMS".equals(mapping.getParameter())) {
			return alterarNumeroSMS(mapping, form, request, response);
		} else if ("getHistoricoPortabilidade".equals(mapping.getParameter())) {
			return getHistoricoPortabilidade(mapping, form, request, response);
		} else if ("abrirProtocolo".equals(mapping.getParameter())) {
			return abrirProtocolo(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="successAtual" path="indexAtual.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// VARIÁVEIS
		user = ConstantesCRM.getCurrentUser(request.getSession());
		logTelaInicial.debug("TelaInicialController:begin(" + user + ") >> INICIALIZADO");
		tiForm = new TIForm();
		pesquisaForm = new PesquisaForm();

		// carrega parametro do número da linha
		if (request.getParameter("nrLinha") != null) {
			logTelaInicial.debug("TelaInicialController:begin(" + user + ") >> CAPTURADO -> nrLinha: " + request.getParameter("nrLinha").toString());
			tiForm.setNrLinha(request.getParameter("nrLinha").toString());
		}

		if (request.getParameter("nrProtocolo") != null && request.getParameter("protocoloInativo") == null) {
			String nrProtocolo = request.getParameter("nrProtocolo");
			logTelaInicial.debug("TelaInicialController:begin(" + user + ") >> CAPTURADO -> nrProtocolo: " + nrProtocolo);
			request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, nrProtocolo);
			getTiForm().setNrProtocolo(nrProtocolo);
			getTiForm().setProtocoloScreenPop(true);

		} else if (request.getParameter("protocoloInativo") != null) {
			request.setAttribute("protocoloInativo", "true");
			getTiForm().setProtocoloScreenPop(true);
		}

		// carrega o tipo de atendimento
		if (request.getParameter("tipoAtendimento") != null) {
			logTelaInicial.debug("TelaInicialController:begin(" + user + ") >> CAPTURADO -> tipoAtendimento -> " + request.getParameter("tipoAtendimento").toString());
			tiForm.setTipoAtendimento(request.getParameter("tipoAtendimento").toString());
		}

		// carrega o tipo de pesquisa
		if ((request.getParameter("pesquisa") != null) && (!(request.getParameter("pesquisa").equals(ConstantesCRM.SVAZIO)))) {
			logTelaInicial.debug("TelaInicialController:begin(" + user + ") >> CAPTURADO -> pesquisa: " + request.getParameter("pesquisa").toString());
			tiForm.setPesquisa(request.getParameter("pesquisa").toString());
		}

		tiForm.setNmLogin((String) (request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN) != null ? request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN) : ConstantesCRM.SVAZIO));
		// Caso ja exista na sessao o idTipoLinha, ja sera setado
		tiForm.getParametros().setIdTipoLinha(request.getSession().getAttribute(ConstantesCRM.SPARAMETROS) != null && ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha() != null ? ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha() : ConstantesCRM.SVAZIO);
		// BUSCA GRUPOS DE USUÁRIO
		tiForm.setGruposVO(monitoramentoFacade.obtemGrupos(user).getWFGrupoVOArray());
		// Simulacao de inexistencia de grupo
		// MonitoramentoPesquisaVO testeGrupoVO = MonitoramentoPesquisaVO.Factory.newInstance();
		// tiForm.setGruposVO(testeGrupoVO.getWFGrupoVOArray());

		if (tiForm.getGruposVO().length == 0) {
			request.setAttribute("dsMensagem", "Não existe nenhum grupo válido para abertura de atendimento!");

		} else {
			// seta o primeiro grupo na sessão
			tiForm.getParametros().setIdGrupo(tiForm.getGruposVO()[0].getIdGrupo().toString());
			// seta o grupo selecionado
			tiForm.setGrupoSel(tiForm.getGruposVO()[0].getIdGrupo().toString());
		}

		request.getSession().setAttribute(ConstantesCRM.SPARAMETROS, tiForm.getParametros());
		detalhesSaldoForm = new DetalhesSaldoForm();
		detalhesSaldoForm.setBalanco(ConstantesCRM.SVAZIO);
		detalhesSaldoForm.getDetalhesSaldoVO().setCdSeguranca(ConstantesCRM.SVAZIO);
		detalhesSaldoForm.getDetalhesSaldoVO().setEstadoLinha(ConstantesCRM.SVAZIO);
		detalhesSaldoForm.getDetalhesSaldoVO().setPrValidadeReal(ConstantesCRM.SVAZIO);

		this.validarClienteForm = new ValidarClienteForm();
		this.validarClienteForm.setIdGrupo(tiForm.getGrupoSel());
		this.validarClienteForm.setIdUsuario(user);

		if ((request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN_CTI) != null) && (!request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN_CTI).equals(ConstantesCRM.SVAZIO))) {
			setCampanhas();
		}

		// ABA RELACIOANMENTO
		this.relacionamentoForm = new RelacionamentoForm();
		this.relacionamentoForm.setIdPessoaDePara("21");
		this.relacionamentoForm.setTpRelacionamento(ConstantesCRM.SONE);
		this.relacionamentoForm.setIdPessoaLinhaHistorico(ConstantesCRM.SONE);
		this.relacionamentoForm.setUser(user);
		// this.relacionamentoForm.setWfEstados(relacionamentoFacade.obterEstados(user).getWFEstados());
		this.relacionamentoForm.setAtendimentoRelacionamentosVO(AtendimentoRelacionamentosVODocument.Factory.newInstance().getAtendimentoRelacionamentosVO());
		logTelaInicial.debug("TelaInicialController:begin(" + user + ") >> FINALIZADO");

		if ("atual".equals(request.getParameter("go"))) {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("successAtual");
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RelacionamentoHistorico.jsp"
	 * @jpf:forward name="successMG" path="RelacionamentoHistoricoMG.jsp"
	 */
	public ActionForward getPesquisaHistorico(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelacionamentoForm form = (RelacionamentoForm) formParam;
		user = ConstantesCRM.getCurrentUser(request.getSession());

		String destino = "true".equals(request.getParameter("inHistoricoMG")) ? "successMG" : ConstantesCRM.SUCCESS;

		logTelaInicial.debug("TelaInicialController:pesquisarRelacionamento(" + user + ") >> INICIALIZADO");
		relacionamentoForm = form;

		if ("successMG".equals(destino)) {
			getRelacionamentoForm().setHistoricoRelacionamento(false);
			getRelacionamentoForm().setHistoricoRelacionamentoMG(true);
		} else {
			getRelacionamentoForm().setHistoricoRelacionamento(true);
			getRelacionamentoForm().setHistoricoRelacionamentoMG(false);
		}

		ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);

		if (parametros != null) {
			form.setIdPessoaDePara(parametros.getIdPessoaCliente() == null ? "21" : parametros.getIdPessoaCliente());
			form.setTpRelacionamento((parametros.getIdTipoRelacionamento() == null ? ConstantesCRM.SONE : parametros.getIdTipoRelacionamento()));
			form.setIdLinha((parametros.getIdLinha() == null) || (parametros.getIdLinha().equals(ConstantesCRM.SVAZIO)) ? ConstantesCRM.SONE : parametros.getIdLinha());
		}

		AtendimentoPesquisaVO atPesquisaVOgravar = AtendimentoPesquisaVO.Factory.newInstance();

		atPesquisaVOgravar.setInPrimeiraChamada(form.getInPrimeiraChamada());
		atPesquisaVOgravar.setDtAberturaInicio(form.getDtAberturaInicio());
		atPesquisaVOgravar.setDtAberturaFim(form.getDtAberturaFim());
		atPesquisaVOgravar.setDtFechamentoInicio(form.getDtFechamentoInicio());
		atPesquisaVOgravar.setDtFechamentoFim(form.getDtFechamentoFim());
		atPesquisaVOgravar.setIdAtendimento(form.getIdAtendimento());
		atPesquisaVOgravar.setNrProtocolo(form.getNrProtocolo());

		GrupoPesquisa grupoPesquisa = GrupoPesquisa.Factory.newInstance();
		grupoPesquisa.setLinhaVO(LinhaVO.Factory.newInstance());

		if (parametros != null) {
			grupoPesquisa.setTpRelacionamento(parametros.getIdTipoRelacionamento() == null ? ConstantesCRM.SONE : parametros.getIdTipoRelacionamento());
			if (form.getTpPesquisa() != null) {
				grupoPesquisa.setTpPesquisa(form.getTpPesquisa());
				if (relacionamentoForm.getTpPesquisa().equals(ConstantesCRM.SONE)) {
					grupoPesquisa.setIdPessoaDePara(parametros.getIdPessoaCliente() == null ? "21" : parametros.getIdPessoaCliente());
				} else {
					if ((parametros.getIdTipoRelacionamento() == null) || (parametros.getIdTipoRelacionamento().equals(ConstantesCRM.SONE))) {
						grupoPesquisa.setIdPessoaDePara(parametros.getIdPessoaUsuario() == null ? "21" : parametros.getIdPessoaUsuario());
					} else {
						grupoPesquisa.setIdPessoaDePara(parametros.getIdClienteDePara() == null ? "21" : parametros.getIdClienteDePara());
					}
				}
			}
		} else {
			grupoPesquisa.setTpRelacionamento(ConstantesCRM.SONE);
		}

		grupoPesquisa.getLinhaVO().setIdPessoaLinhaHistorico(Long.parseLong(form.getIdLinha()));
		grupoPesquisa.getLinhaVO().setNrLinha(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha());
		grupoPesquisa.setNrConta(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrConta());

		atPesquisaVOgravar.setGrupoPesquisa(grupoPesquisa);
		atPesquisaVOgravar.setIdContato(form.getIdContato());
		atPesquisaVOgravar.setDsStatusProtocolo(form.getEstadoSelecionado());

		logTelaInicial.debug("TelaInicialController:pesquisarRelacionamento(" + user + ") >> FINALIZADO");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(destino);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iSubEstado.jsp"
	 */
	public ActionForward pesquisarSubEstado(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelacionamentoForm form = (RelacionamentoForm) formParam;
		user = ConstantesCRM.getCurrentUser(request.getSession());
		relacionamentoForm = form;
		if (relacionamentoForm == null) {
			relacionamentoForm = new RelacionamentoForm();
		}
		relacionamentoForm.setAtendimentoPesquisaVO(relacionamentoFacade.pesquisarSubEstado(user, form.getEstadoSelecionado()));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iArvoreContato.jsp"
	 */
	public ActionForward obterArvoreContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//RelacionamentoForm form = (RelacionamentoForm) formParam;
		user = ConstantesCRM.getCurrentUser(request.getSession());
		logTelaInicial.debug("TelaInicialController:obterArvoreContato(" + user + ") >> INICIALIZADO");
		AdmContatoFolhaVO admContatoFolhaVO = registrarContatoFacade.carregaArvoreContato(user, null);
		if (admContatoFolhaVO == null) {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}
		StringBuffer sbScript = new StringBuffer(ConstantesCRM.SVAZIO);
		sbScript.append("if (document.getElementById) {\n\nvar tree = new WebFXTree('");
		sbScript.append(StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getNmContato()));
		sbScript.append("',\"Javascript:selecionaContato('");
		sbScript.append(admContatoFolhaVO.getIdContato());
		sbScript.append("', '");
		sbScript.append("','','" + RAIZ_PRINCIPAL + "');\",'classic');\n\n");
		// sbScript.append("', '");
		// sbScript.append("');\",'classic');\n\n");
		StringBuffer sbNode = new StringBuffer(1024);
		for (int i = 0; i < admContatoFolhaVO.getAdmContatoFolhaVOArray().length; i++) {
			sbNode.setLength(0);
			String idContato = admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getIdContato();
			String sInFolha = admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getInFolha();
			String nmContato = StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getNmContato());
			String dsPath = StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getDsPath());
			String ico1 = ConstantesCRM.SVAZIO;
			String ico2 = ConstantesCRM.SVAZIO;
			String dblClickScript = "Javascript:itemDblClick('" + idContato + "', '" + sInFolha + "', '" + dsPath + "')";
			if (!admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getInFolha().equals(ConstantesCRM.SONE)) {
				ico1 = "/FrontOfficeWeb/resources/images/foldericon.png";
				ico2 = "/FrontOfficeWeb/resources/images/openfoldericon.png";
				dblClickScript = ConstantesCRM.SVAZIO;
			}
			sbNode.append("var arvore = new WebFXTreeItem('");
			sbNode.append(nmContato);
			sbNode.append("',\"Javascript:selecionaContato('");
			sbNode.append(idContato);
			sbNode.append("','");
			sbNode.append(sInFolha);
			sbNode.append("','");
			sbNode.append(dsPath);
			sbNode.append("','");
			sbNode.append("');\",'','").append(ico1).append("','").append(ico2).append("','',\"");
			sbNode.append(dblClickScript).append("\");\n");
			sbScript.append(sbNode.toString());
			sbScript.append("tree.add(arvore);\n");
		}
		sbScript.append("document.write(tree);\n}\n");
		// RelacionamentoForm relacionamentoForm = this.relacionamentoForm;
		this.relacionamentoForm = new RelacionamentoForm();
		this.relacionamentoForm.setScriptArvore(sbScript.toString());
		logTelaInicial.debug("TelaInicialController:obterArvoreContato(" + user + ") >> FINALIZADO");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iExpandeArvoreContatoRel.jsp"
	 */
	public ActionForward expandeArvoreContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//RelacionamentoForm form = (RelacionamentoForm) formParam;
		user = ConstantesCRM.getCurrentUser(request.getSession());
		logTelaInicial.debug("TelaInicialController:expandeArvoreContato(" + user + ") >> INICIALIZADO");
		StringBuffer sbScript = new StringBuffer(1024);
		StringBuffer sbNode = new StringBuffer(1024);
		AtendimentoArvoreFiltrosVO atFiltros = AtendimentoArvoreFiltrosVO.Factory.newInstance();
		atFiltros.setIdContato(request.getParameter("IDCONTATO"));
		AdmContatoFolhaVO admContatoFolhaVO = registrarContatoFacade.carregaArvoreContato(user, atFiltros);
		for (int i = 0; i < admContatoFolhaVO.getAdmContatoFolhaVOArray().length; i++) {
			sbNode.setLength(0);
			String idContato = admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getIdContato();
			String sInFolha = admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getInFolha();
			String nmContato = StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getNmContato());
			String dsPath = StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getDsPath());
			String ico1 = ConstantesCRM.SVAZIO;
			String ico2 = ConstantesCRM.SVAZIO;
			String dblClickScript = "Javascript:itemDblClick('" + idContato + "', '" + sInFolha + "', '" + dsPath + "')";
			if (!admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getInFolha().equals(ConstantesCRM.SONE)) {
				ico1 = "/FrontOfficeWeb/resources/images/foldericon.png";
				ico2 = "/FrontOfficeWeb/resources/images/openfoldericon.png";
				dblClickScript = ConstantesCRM.SVAZIO;
			}
			sbNode.append("var arvore = new parent.WebFXTreeItem('");
			sbNode.append(nmContato);
			sbNode.append("',\"Javascript:selecionaContato('");
			sbNode.append(idContato);
			sbNode.append("','");
			sbNode.append(sInFolha);
			sbNode.append("','");
			sbNode.append(dsPath);
			sbNode.append("','");
			sbNode.append("');\",'','").append(ico1).append("','").append(ico2).append("','',\"");
			sbNode.append(dblClickScript).append("\");");
			sbScript.append(sbNode.toString());
			sbScript.append("parent.tree.getSelected().add(arvore);\n\n");
		}
		sbScript.append("parent.tree.getSelected().setAddEnabled(false);\n\n");
		sbScript.append("parent.tree.getSelected().expand();\n\n");
		this.relacionamentoForm.setScriptArvore(sbScript.toString());
		logTelaInicial.debug("TelaInicialController:expandeArvoreContato(" + user + ") >> FINALIZADO");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="listaProcessosDeProtocolo.jsp"
	 */
	public ActionForward getProcessosByProtocolo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//RelacionamentoForm form = (RelacionamentoForm) formParam;
		String nrProtocolo = request.getParameter("nrProtocolo");
		String cdOperacao = "true".equals(request.getParameter("inHistoricoMG")) ? "processosPorProtocoloHistoricoMG" : "processosPorProtocolo";
		String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());

		AtendimentoPesquisaVO atendimentoPesquisaVO = AtendimentoPesquisaVO.Factory.newInstance();
		atendimentoPesquisaVO.setNrProtocolo(nrProtocolo);
		atendimentoPesquisaVO.setCdOperacao(cdOperacao);

		try {
			getRelacionamentoForm().setProcessosDeProtocolo(relacionamentoFacade.pesquisarRelacionamento(idUsuario, atendimentoPesquisaVO));
		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:getProcessosByProtocolo(" + user + ") - [" + e.getMessage() + "] - Erro em busca de processos de protocolo.", e);
			request.setAttribute("msgErro", "Houve um problema durante carregamento dos processos.");
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}


	/*
	 * Fim das action's relacionamento
	 */
	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="carregaLigacaoPendente.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward carregaParametrosCTI(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		//ValidarClienteForm form = (ValidarClienteForm) formParam;
		try {
			request.setCharacterEncoding(ConstantesCRM.SISO);
			String nrLinha = null;
			String idChamadaTelefonica = null;
			String idURA = null;
			String idCallCenter = null;
			String inSenhaValidada = null;
			String inPos = null;
			String idTipoRelacionamento = null;
			//String idSatisfacao = null;
			String nrTelefone = null;
			String observacao = null;
			String timeoutURA = null;
			//String idPessoaEspecial = null;
			String navegacaoURA = null;
			String tipProc = null;
			String numProc = null;
			//String idTipoRelProspect = null;

			user = ConstantesCRM.getCurrentUser(request.getSession());

			logTelaInicial.debug("TelaInicialController:carregaParametrosCTI(" + user + ") >> INICIALIZADO");

			this.validarClienteForm = new ValidarClienteForm();
			this.validarClienteForm.setIdUsuario(user);
			if ((request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN_CTI) != null) && (!request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN_CTI).equals(ConstantesCRM.SVAZIO))) {
				setCampanhas();
			}

			nrLinha = request.getParameter("nrLinha");
			this.validarClienteForm.setNumeroConsultado(nrLinha);
			idURA = request.getParameter("idUra");
			this.validarClienteForm.setIdUra(idURA);
			idCallCenter = request.getParameter("idCallCenter");
			this.validarClienteForm.setIdCallCenter(idCallCenter);
			// fazer lógica para saber se está com senha validada
			// se "S" marcar como true
			inSenhaValidada = request.getParameter("inSenhaValidada");
			this.validarClienteForm.setSenhaValidada(inSenhaValidada.equals("true"));
			inPos = request.getParameter("inPos");
			this.validarClienteForm.setIdPos(inPos);
			//idSatisfacao = request.getParameter("idSatisfacao");
			idTipoRelacionamento = request.getParameter("idTipoRelacionamento");
			this.validarClienteForm.setTipoRelacionamento(idTipoRelacionamento);
			this.validarClienteForm.setUsuario(idTipoRelacionamento.equals(ConstantesCRM.SONE));
			this.validarClienteForm.setCliente(idTipoRelacionamento.equals(ConstantesCRM.STWO));
			nrTelefone = request.getParameter("nrTelefone");
			this.validarClienteForm.setNumeroOrigem(nrTelefone);
			observacao = request.getParameter("observacao") != null ? request.getParameter("observacao") : ConstantesCRM.SVAZIO;
			this.validarClienteForm.setObservacao(observacao);
			timeoutURA = request.getParameter("timeoutURA") != null ? request.getParameter("timeoutURA") : "N";
			this.validarClienteForm.setIndRedirTimeoutURA(timeoutURA);
			((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setIndRedirTimeoutURA(timeoutURA);
			// chamada ao serviço para carregamento de idChamadaTelefonica

			idChamadaTelefonica = Integer.toString(atendimentoFacade.obtemChamadaTelefonicaTdVO(user).getIdChamadaTelefonica());
			this.validarClienteForm.setIdChamadaTelefonica(idChamadaTelefonica);
			((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setIdChamadaTelefonica(idChamadaTelefonica);

			navegacaoURA = request.getParameter("navegacaoURA") != null ? request.getParameter("navegacaoURA") : ConstantesCRM.SVAZIO;
			this.validarClienteForm.setNavegacaoURA(navegacaoURA);
			((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setNavCliUra(navegacaoURA);
			tipProc = request.getParameter("tipProc") != null ? request.getParameter("tipProc") : ConstantesCRM.SVAZIO;
			this.validarClienteForm.setTipoProc(tipProc);
			numProc = request.getParameter("numProc") != null ? request.getParameter("numProc") : ConstantesCRM.SVAZIO;
			this.validarClienteForm.setNumeroProc(numProc);
			// seta variável para não carregar nova ligaçao
			this.validarClienteForm.setNovaLigacao(false);
			logTelaInicial.debug("TelaInicialController:carregaParametrosCTI(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:carregaParametrosCTI(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, ConstantesCRM.TELAINCIAL);
			formError.setTarget(ConstantesCRM.FRAMEAPP);

			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="carregaLigacaoPendente.jsp"
	 */
	public ActionForward carregarLigacao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//ValidarClienteForm form = (ValidarClienteForm) formParam;
		logTelaInicial.debug("TelaInicialController:carregarLigacao(" + user + ") >> INICIALIZADO");
		this.validarClienteForm = new ValidarClienteForm();
		// seta variável para carregar nova ligaçao
		this.validarClienteForm.setNovaLigacao(true);
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
		formAux.setNavegacaoURA(request.getParameter("navegacaoURA"));
		formAux.setSenhaValidada(request.getParameter("inSenhaValidada").equals("true"));
		formAux.setTipoProc(request.getParameter("tipProc"));
		formAux.setNumeroProc(request.getParameter("numProc"));
		// seta no formulário dados da nova ligaçao
		this.validarClienteForm.setNovaLigacaoForm(formAux);
		logTelaInicial.debug("TelaInicialController:carregarLigacao(" + user + ") >> FINALIZADO");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="TelaInicial.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward recebeLigacao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			//TIForm form = (TIForm) formParam;
			logTelaInicial.debug("TelaInicialController:recebeLigacao(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") >> INICIALIZADO");
			// remove paremetros da sessão
			request.getSession().removeAttribute(ConstantesCRM.SPARAMETROS);
			// parametros
			String nrLinha = request.getParameter("nrLinha") != null ? request.getParameter("nrLinha") : ConstantesCRM.SVAZIO;
			// inicializa o form
			this.tiForm = new TIForm();
			this.tiForm.getParametros().setNrLinha(nrLinha);
			request.getSession().setAttribute(ConstantesCRM.SPARAMETROS, tiForm.getParametros());
			logTelaInicial.debug("TelaInicialController:recebeLigacao(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:recebeLigacao(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, ConstantesCRM.TELAINCIAL);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward TelaInicial(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			//TIForm form = (TIForm) formParam;
			
			if (request.getParameter("idPositiva") == null && request.getSession().getAttribute(ConstantesCRM.SPARAMETROS) != null) {
				((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setInPos(null);
				((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setIdTipoRelacionamento(ConstantesCRM.SVAZIO);
			}
			
			// VARIÁVEIS
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logTelaInicial.debug("TelaInicialController:TelaInicial(" + user + ") >> INICIALIZADO");
			request.setAttribute("tiForm", tiForm);
			ActionForward forward = mapping.findForward(ConstantesCRM.SUCCESS);
			tiForm = new TIForm();
			tiForm.setNmLogin((String) (request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN) != null ? request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN) : ConstantesCRM.SVAZIO));
			String nrLinha = null;
			String idChamadaTelefonica = null;
			String idURA = null;
			String idCallCenter = null;
			String inSenhaValidada = null;
			String inPos = null;
			String idTipoRelacionamento = null;
			//String idSatisfacao = null;
			String nrTelefone = null;
			String observacao = null;
			String timeoutURA = null;
			String idPessoaEspecial = null;
			String navegacaoURA = null;
			String idTipoRelProspect = null;
			
			if (request.getParameter("inicioTela") != null || request.getAttribute("inicioTela") != null) { // ACESSO PELO MENU PRINCIPAL
				logTelaInicial.debug("TelaInicialController:TelaInicial(" + user + ") >> Tela de Atendimento > Origem: Menu Principal");
				request.getSession().setAttribute("inicioTela", ConstantesCRM.STRUE);
			
			} else { // ACESSO PELA URA OU PELA PESQUISA
				if (request.getAttribute("nrLinha") == null) { // ORIGEM - URA
					logTelaInicial.debug("TelaInicialController:TelaInicial(" + user + ") >> Tela de Atendimento > Origem: URA");
					nrLinha = request.getParameter("nrLinha");
				
				} else { // ORIGEM - PESQUISA
					logTelaInicial.debug("TelaInicialController:TelaInicial(" + user + ") >> Tela de Atendimento > Origem: Pesquisa");
					nrLinha = request.getAttribute("nrLinha").toString();
				}
				
				if (request.getSession().getAttribute("idProspect") == null && request.getParameter("naoCliente") == null) { // CLIENTE
					// OU
					// USUÁRIO
					logTelaInicial.debug("TelaInicialController:TelaInicial(" + user + ") >> Tela de Atendimento > Cliente ou Usuário");
					// CRIAÇÃO DOS PARÂMETROS
					if (request.getSession().getAttribute(ConstantesCRM.SPARAMETROS) != null) {
						idURA = request.getParameter("idUra");
						idCallCenter = request.getParameter("idCallCenter");
						inSenhaValidada = request.getParameter("inSenhaValidada");
						inPos = request.getParameter("inPos");
						if (inPos == null) {
							inPos = request.getSession().getAttribute(ConstantesCRM.SPARAMETROS) != null ? ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getInPos() : null;
						}
						
						//idSatisfacao = request.getParameter("idSatisfacao");
						idTipoRelacionamento = request.getParameter("idTipoRelacionamento");
						if (idTipoRelacionamento == null) {
							idTipoRelacionamento = request.getSession().getAttribute(ConstantesCRM.SPARAMETROS) != null ? ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoRelacionamento() : null;
						}
						
						nrTelefone = request.getParameter("nrTelefone");
						observacao = request.getParameter("observacao") != null ? request.getParameter("observacao") : ConstantesCRM.SVAZIO;
						timeoutURA = request.getParameter("timeoutURA") != null ? request.getParameter("timeoutURA") : "N";
						
						if (nrTelefone != null) {
							// PARAMETRO ENTREGUE POR MÓDULO WORKFLOW
							idChamadaTelefonica = Integer.toString(atendimentoFacade.obtemChamadaTelefonicaTdVO(user).getIdChamadaTelefonica());
						
						} else {
							idChamadaTelefonica = ConstantesCRM.SVAZIO;
						}
						
						navegacaoURA = request.getParameter("navegacaoURA") != null ? request.getParameter("navegacaoURA") : ConstantesCRM.SVAZIO;

						if (nrLinha == null) {
							nrLinha = request.getSession().getAttribute(ConstantesCRM.SPARAMETROS) != null ? ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha() : null;
						}
						
						// PARAMETROS ENTREGUE PELO SERVICO TUXEDO
						ParametrosVO pTemp = ParametrosVO.Factory.newInstance();
						if (idTipoRelacionamento == null || ConstantesCRM.SVAZIO.equals(idTipoRelacionamento)) {
							pTemp = telaInicialFacadeControl.getParametrosVO(user, nrLinha, ConstantesCRM.STWO);
						} else {
							pTemp = telaInicialFacadeControl.getParametrosVO(user, nrLinha, idTipoRelacionamento);
						}
						
						tiForm.setParametros(pTemp);
						if (((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrTelefone() == null) { // CASO PARAMETROS JÁ ESTIVEREM PREENCHIDOS PULA ETAPA
							tiForm.getParametros().setIdUra(idURA);
							tiForm.getParametros().setIdChamadaTelefonica(idChamadaTelefonica);
							tiForm.getParametros().setIdCallCenter(idCallCenter);
							tiForm.getParametros().setIdUsuario(user);
							tiForm.getParametros().setInSenhaValidada(inSenhaValidada);
							tiForm.getParametros().setInPos(inPos);
							tiForm.getParametros().setNrTelefone(nrTelefone);
							tiForm.getParametros().setObsAtend(observacao);
							tiForm.getParametros().setIndRedirTimeoutURA(timeoutURA);
							tiForm.getParametros().setNavCliUra(navegacaoURA);
						}
						tiForm.getParametros().setNrLinha(nrLinha);
						tiForm.getParametros().setIdTipoRelacionamento(idTipoRelacionamento);
						/*
						 * }else{ tiForm.getParametros().setNrLinha(nrLinha); }
						 */
					}
				
				} else { // NAO CLIENTE E PROSPECT
					logTelaInicial.debug("TelaInicialController:TelaInicial(" + user + ") >> Tela de Atendimento > Não Cliente ou Prospect");
					if (request.getParameter("naoCliente") != null) {
						request.getSession().setAttribute("naoCliente", ConstantesCRM.STRUE);
						idPessoaEspecial = Integer.toString(idPessoaProspect);
						if (request.getSession().getAttribute("idProspect") != null) {
							idTipoRelProspect = ConstantesCRM.SSIX;
							request.getSession().removeAttribute("idProspect");
						}
					} else {
						idPessoaEspecial = request.getSession().getAttribute("idProspect").toString();
					}
					
					idURA = request.getParameter("idUra");
					idCallCenter = request.getParameter("idCallCenter");
					inSenhaValidada = request.getParameter("inSenhaValidada");
					inPos = request.getParameter("inPos");
					
					if (inPos == null) {
						inPos = request.getSession().getAttribute(ConstantesCRM.SPARAMETROS) != null ? ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getInPos() : null;
					}
					
					//idSatisfacao = request.getParameter("idSatisfacao");
					idTipoRelacionamento = request.getParameter("idTipoRelacionamento");
					
					if (idTipoRelacionamento == null) {
						idTipoRelacionamento = request.getSession().getAttribute(ConstantesCRM.SPARAMETROS) != null ? ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoRelacionamento() : null;
					}
					
					nrTelefone = request.getParameter("nrTelefone");
					observacao = request.getParameter("observacao") != null ? request.getParameter("observacao") : ConstantesCRM.SVAZIO;
					timeoutURA = request.getParameter("timeoutURA") != null ? request.getParameter("timeoutURA") : "N";
					// PARAMETRO ENTREGUE POR MÓDULO WORKFLOW
					idChamadaTelefonica = ConstantesCRM.SVAZIO;// Integer.toString(atendimentoFacade.obtemChamadaTelefonicaVO(user).getIdChamadaTelefonica());
					navegacaoURA = request.getParameter("navegacaoURA") != null ? request.getParameter("navegacaoURA") : ConstantesCRM.SVAZIO;
					
					if (((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrTelefone() == null) {
						// CASO PARAMETROS JÁ ESTIVEREM PREENCHIDOS PULA ETAPA
						tiForm.getParametros().setIdUra(idURA);
						tiForm.getParametros().setIdChamadaTelefonica(idChamadaTelefonica);
						tiForm.getParametros().setIdCallCenter(idCallCenter);
						tiForm.getParametros().setIdUsuario(user);
						tiForm.getParametros().setInSenhaValidada(inSenhaValidada);
						tiForm.getParametros().setInPos(inPos);
						tiForm.getParametros().setNrTelefone(nrTelefone);
						tiForm.getParametros().setObsAtend(observacao);
						tiForm.getParametros().setIndRedirTimeoutURA(timeoutURA);
						tiForm.getParametros().setNavCliUra(navegacaoURA);
					}
					tiForm.getParametros().setIdTipoRelacionamento(idTipoRelacionamento);
				}
			}
			// BUSCA GRUPOS DE USUÁRIO
			tiForm.setGruposVO(monitoramentoFacade.obtemGrupos(user).getWFGrupoVOArray());
			if (request.getSession().getAttribute(ConstantesCRM.SPARAMETROS) == null) { // VERIFICA SE JÁ POSSUI idGrupo
				// EM PARÂMETROS
				// seta o primeiro grupo na sessão
				tiForm.getParametros().setIdGrupo(tiForm.getGruposVO()[0].getIdGrupo().toString());
				// seta o grupo selecionado
				tiForm.setGrupoSel(tiForm.getGruposVO()[0].getIdGrupo().toString());
			} else {
				// seta o primeiro grupo na sessão
				tiForm.getParametros().setIdGrupo(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdGrupo());
				// seta o grupo selecionado
				tiForm.setGrupoSel(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdGrupo());
			}
			// COLOCA PARÂMETROS NA SESSAO
			if (request.getSession().getAttribute(ConstantesCRM.SPARAMETROS) != null && ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrTelefone() != null) { // REAPROVEITA
				// OS
				// PARÂMETROS
				// ANTERIORES
				tiForm.getParametros().setNrTelefone(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrTelefone());
				tiForm.getParametros().setIdCallCenter(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdCallCenter());
				tiForm.getParametros().setIdChamadaTelefonica(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdChamadaTelefonica());
				tiForm.getParametros().setIdSatisfacao(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdSatisfacao());
				tiForm.getParametros().setIdUra(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdUra());
				tiForm.getParametros().setIndRedirTimeoutURA(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIndRedirTimeoutURA());
				tiForm.getParametros().setInPos(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getInPos());
				tiForm.getParametros().setObsAtend(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getObsAtend());
				tiForm.getParametros().setNmContato(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNmContato());
				tiForm.getParametros().setInSenhaValidada(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getInSenhaValidada());
			}
			request.getSession().removeAttribute(ConstantesCRM.SPARAMETROS);
			tiForm.getParametros().setIdUsuario(user);
			if (tiForm.getParametros().getIdTipoRelacionamento() != null && tiForm.getParametros().getIdTipoRelacionamento().equals(ConstantesCRM.SVAZIO)) {
				tiForm.getParametros().setIdTipoRelacionamento(null);
			}
			request.getSession().setAttribute(ConstantesCRM.SPARAMETROS, tiForm.getParametros());
			if (idPessoaEspecial != null && idPessoaEspecial.length() > 0) {
				((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setIdPessoaCliente(idPessoaEspecial);
				((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setIdPessoaUsuario(idPessoaEspecial);
			}
			this.validarClienteForm = new ValidarClienteForm();
			setValidarClienteForm(request);
			setCampanhas();
			if ((idTipoRelProspect != null && idTipoRelProspect.equals(ConstantesCRM.SSIX)) || nrLinha == null) {// PROSPECT
				((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setIdTipoRelacionamento(ConstantesCRM.SSIX);
			}
			logTelaInicial.debug("TelaInicialController:TelaInicial(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return forward;

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:TelaInicial(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, ConstantesCRM.TELAINCIAL);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/*
	 * Action's do validarClienteForm
	 */
	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="validarCliente.jsp"
	 */
	public ActionForward transferirUraAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 */
	public ActionForward idPosAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ValidarClienteForm form = (ValidarClienteForm) formParam;

		user = ConstantesCRM.getCurrentUser(request.getSession());
		logTelaInicial.debug("TelaInicialController:idPosAction(" + user + ") >> INICIALIZADO");
		StringBuffer xmlDadosTI = new StringBuffer();
		try {
			String valor = request.getParameter("valor").toString();
			carregaParametrosSessao(valor, form.getTipoRelacionamento(), request);
			ParametrosVO tmpVO = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
			tmpVO.setIdTipoRelacionamento(form.getTipoRelacionamento());
			tmpVO.setInPos(form.getIdPos());
			logTelaInicial.debug("TelaInicialController:idPosAction(" + user + ") >> valor: " + valor);

			xmlDadosTI.append("<xmlDadosTI><parametros>");
			xmlDadosTI.append("<tipoRelacionamento>").append(trataNull(tmpVO.getIdTipoRelacionamento())).append("</tipoRelacionamento>");
			xmlDadosTI.append("<idPessoaCliente>").append(trataNull(tmpVO.getIdPessoaCliente())).append("</idPessoaCliente>");
			xmlDadosTI.append("<idProspect>").append(trataNull((String) request.getSession().getAttribute("idProspect"))).append("</idProspect>");
			xmlDadosTI.append("<idTipoLinha>").append(trataNull(tmpVO.getIdTipoLinha())).append("</idTipoLinha>");
			xmlDadosTI.append("<inLegado>").append(trataNull(tmpVO.getInLegado())).append("</inLegado>");
			xmlDadosTI.append("<nrConta>").append(trataNull(tmpVO.getNrConta())).append("</nrConta>");
			xmlDadosTI.append("<inCorporativo>").append(trataNull(tmpVO.getInCorporativo())).append("</inCorporativo>");
			xmlDadosTI.append("<inCorrespondencia>").append(trataNull(tmpVO.getInCorrespDevolvida())).append("</inCorrespondencia>");
			xmlDadosTI.append("<inTipoPessoa>").append(trataNull(tmpVO.getInTipoPessoa())).append("</inTipoPessoa>");
			xmlDadosTI.append("<nrLinha>").append(trataNull(tmpVO.getNrLinha())).append("</nrLinha>");
			// Obtém o idChamadaTelefonica caso já possua chamadaTelefonica usa a mesma
			String inChamada = null;
			String idPessoaDePara;
			ChamadaTelefonicaVO chamadaTelefonicaVO;
			if ((tmpVO.getIdTipoRelacionamento() != null) && (tmpVO.getIdTipoRelacionamento().equals(ConstantesCRM.SONE))) {
				idPessoaDePara = tmpVO.getIdUsuarioDePara() == null ? ConstantesCRM.SONE : tmpVO.getIdUsuarioDePara();
			} else {
				idPessoaDePara = tmpVO.getIdClienteDePara() == null ? ConstantesCRM.SONE : tmpVO.getIdClienteDePara();
			}
			String tipoPessoa = ConstantesCRM.SVAZIO;
			if (tmpVO.getIdTipoRelacionamento() != null) {
				switch (Integer.parseInt(form.getTipoRelacionamento())) {
				case 1:
					tipoPessoa = "U";
					break;
				case 2:
					tipoPessoa = "C";
					break;
				}
			} else {
				tipoPessoa = "C";
			}
			logTelaInicial.debug("TelaInicialController:idPosAction(" + user + ") >> tipoPessoa: " + tipoPessoa);
			if ((form.getIdChamadaTelefonica() != null) && (form.getIdChamadaTelefonica().equals(ConstantesCRM.SVAZIO))) {
				chamadaTelefonicaVO = atendimentoFacade.obtemChamadaTelefonicaVO(user, idPessoaDePara, tipoPessoa, inChamada);
				tmpVO.setIdChamadaTelefonica(String.valueOf(chamadaTelefonicaVO.getIdChamadaTelefonica()));
			} else {
				chamadaTelefonicaVO = atendimentoFacade.obtemChamadaTelefonicaVO(user, idPessoaDePara, tipoPessoa, ConstantesCRM.SONE);
				tmpVO.setIdChamadaTelefonica(String.valueOf(chamadaTelefonicaVO.getIdChamadaTelefonica()));
			}
			logTelaInicial.debug("TelaInicialController:idPosAction(" + user + ") >> idChamadaTelefonica: " + chamadaTelefonicaVO.getIdChamadaTelefonica());

			xmlDadosTI.append("<idSatisfacao>" + trataNull(String.valueOf(chamadaTelefonicaVO.getIdGrauSatisfacao())) + "</idSatisfacao>");
			xmlDadosTI.append("<idChamadaTelefonica>" + trataNull(tmpVO.getIdChamadaTelefonica()) + "</idChamadaTelefonica>");
			xmlDadosTI.append("</parametros>");
			xmlDadosTI.append("</xmlDadosTI>");

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:idPosAction(" + user + ") - [" + e.getMessage() + "]", e);
			AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
			ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
			ajaxErrorHandlerVO.setFriendlyMessage(MENSAGEM_GERAL);
			xmlDadosTI = new StringBuffer();
			xmlDadosTI.append(ajaxErrorHandlerVO.toString());

		} finally {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			response.setContentType(ConstantesCRM.SContentType);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			bufferedOutputStream.write(xmlDadosTI.toString().getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
			logTelaInicial.debug("TelaInicialController:idPosAction(" + user + ") >> FINALIZADO");
		}
		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iCampanha.jsp"
	 */
	public ActionForward carregaGrupo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ValidarClienteForm form = (ValidarClienteForm) formParam;
		ParametrosVO paramVO = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS));
		if (request.getParameter("idGrupo") != null) {
			paramVO.setIdGrupo(request.getParameter("idGrupo"));
			form.setIdGrupo(request.getParameter("idGrupo"));
		}
		// Verifica se irá carregar as campanhas a partir do grupo
		if (!ConstantesCRM.SVAZIO.equals(request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN_CTI))) {
			// Obtém idPessoaUsuario da sessão
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logTelaInicial.debug("TelaInicialController:carregaGrupo(" + user + ") >> INICIALIZADO");

			form.setIdUsuario(user);

			// Obtém campanhas a partir de grupo
			RetornoWFCTIVO[] campanhasVO = new RetornoWFCTIVO[0];
			campanhasVO = atendimentoFacade.obtemCampanhaGrupoVO(form.getIdUsuario(), form.getIdGrupo());
			if (this.validarClienteForm == null) {
				this.validarClienteForm = new ValidarClienteForm();
			}
			// Verifica se irá setar como campanha padrão
			if (campanhasVO.length == 1 && campanhasVO[0].getInPadrao().equals(ConstantesCRM.SONE)) {
				// Só tem a campanha padrão
				this.validarClienteForm.setCampanhaPadrao("S");
			}

			this.validarClienteForm.setCampanhasVO(campanhasVO);
			logTelaInicial.debug("TelaInicialController:carregaGrupo(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);

			return mapping.findForward(ConstantesCRM.SUCCESS);

		} else {
			return null;
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iTransferir.jsp"
	 */
	public ActionForward transferirCarregar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ValidarClienteForm form = (ValidarClienteForm) formParam;

		logTelaInicial.debug("TelaInicialController:transferirCarregar(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") >> INICIALIZADO");
		request.setCharacterEncoding(ConstantesCRM.SISO);

		this.validarClienteForm = new ValidarClienteForm();
		this.validarClienteForm.setTipoRelacionamento(request.getParameter("relacionamento"));
		this.validarClienteForm.setObservacao(form.getObservacao());
		this.validarClienteForm.setNumeroOrigem(form.getNumeroOrigem());
		this.validarClienteForm.setNumeroConsultado(form.getNumeroConsultado());
		this.validarClienteForm.setSenhaValidada(request.getParameter("senha").equals("true"));
		this.validarClienteForm.setCliente(form.isCliente());
		this.validarClienteForm.setUsuario(form.isUsuario());
		this.validarClienteForm.setIndRedirTimeoutURA(form.getIndRedirTimeoutURA());
		this.validarClienteForm.setIdPos(request.getParameter("identificacao"));
		this.validarClienteForm.setIdCallCenter(form.getIdCallCenter());
		this.validarClienteForm.setIdUra(form.getIdUra());
		this.validarClienteForm.setIdUsuario((ConstantesCRM.getCurrentUser(request.getSession())));
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
		logTelaInicial.debug("TelaInicialController:transferirCarregar(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") >> FINALIZADO");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="validarCliente.jsp"
	 */
	public ActionForward transferirRamalAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="validarCliente.jsp"
	 */
	public ActionForward terminarAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		//ValidarClienteForm form = (ValidarClienteForm) formParam;
		StringBuffer xmlDadosTI = new StringBuffer();
		//String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		fechaProtocolo(request);

		try {
			logTelaInicial.debug("TelaInicialController:terminarAction(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") >> INICIALIZADO");
			ParametrosVO paramVO = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS));
			String strGrupo = paramVO.getIdGrupo();
			if (this.validarClienteForm == null) {
				this.validarClienteForm = new ValidarClienteForm();
			}
			if (!ConstantesCRM.SVAZIO.equals(paramVO.getIdChamadaTelefonica())) {
				this.validarClienteForm.setIdUsuario((ConstantesCRM.getCurrentUser(request.getSession())));
				registrarContatoFacade.enviarProcessosCorrentes(this.validarClienteForm.getIdUsuario(), paramVO.getIdChamadaTelefonica(), request.getParameter("idGrauSatisfacao"));
			}
			// verifica se irá carregar nova ligaçao ou não
			if (this.validarClienteForm.isNovaLigacao()) {
				this.validarClienteForm.setTerminar("N");
				paramVO.setNrTelefone(null);
			} else {
				this.validarClienteForm = new ValidarClienteForm();
				this.validarClienteForm.setTerminar("S");
				paramVO = ParametrosVO.Factory.newInstance();
				paramVO.setIdGrupo(strGrupo);
				request.getSession().setAttribute(ConstantesCRM.SPARAMETROS, paramVO);
			}
			xmlDadosTI.append("<xmlDadosTI/>");

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:terminarAction(" + user + ") - [" + e.getMessage() + "]", e);
			AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
			ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
			ajaxErrorHandlerVO.setFriendlyMessage(MENSAGEM_GERAL);

			xmlDadosTI = new StringBuffer();
			xmlDadosTI.append(ajaxErrorHandlerVO.toString());

		} finally {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			response.setContentType(ConstantesCRM.SContentType);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			bufferedOutputStream.write(xmlDadosTI.toString().getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
			logTelaInicial.debug("TelaInicialController:terminarAction(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") >> FINALIZADO");
		}
		return null;
	}

	private void fechaProtocolo(HttpServletRequest request) {
		String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			String nrProtocolo = request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) != null ? (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) : ConstantesCRM.SVAZIO;

			if (!ConstantesCRM.SVAZIO.equals(nrProtocolo)) {
				FechaProtocoloTO fechaProtocoloTO = FechaProtocoloTO.Factory.newInstance();
				fechaProtocoloTO.setNrProtocolo(nrProtocolo);
				fechaProtocoloTO.setIdSistemaOrigem(Integer.toString(ID_SISTEMA_ORIGEM_VIVONET));

				protocoloFacade.setFechaProtocolo(idUsuario, fechaProtocoloTO);
				getTiForm().setProtocoloScreenPop(false);
				request.getSession().removeAttribute(ConstantesCRM.NRPROTOCOLO);
				getTiForm().setNrProtocolo(ConstantesCRM.SVAZIO);
			}
		} catch (Exception e) {
		}
	}

	/**
	 * @jpf:action
	 */
	public ActionForward iniciarChamada(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ValidarClienteForm form = (ValidarClienteForm) formParam;
		StringBuffer xmlDadosTI = new StringBuffer();
		try {
			// Obtem idPessoaUsuario da Sessão
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logTelaInicial.debug("TelaInicialController:iniciarChamada(" + user + ") >> INICIALIZADO");
			ParametrosVO paramVO = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS));
			// INRESPONSAVELABERTURA -> 1-Usuario; 2-Cliente; 3-Prospect
			String idPessoaDePara;
			String inChamada = null;
			if ((paramVO.getIdTipoRelacionamento() != null) && (paramVO.getIdTipoRelacionamento().equals(ConstantesCRM.SONE))) {
				idPessoaDePara = paramVO.getIdUsuarioDePara() == null ? ConstantesCRM.SONE : paramVO.getIdUsuarioDePara();
			} else {
				idPessoaDePara = paramVO.getIdClienteDePara() == null ? ConstantesCRM.SONE : paramVO.getIdClienteDePara();
			}
			String tipoPessoa = ConstantesCRM.SVAZIO;
			if (form.getTipoRelacionamento() != null && !form.getTipoRelacionamento().equals(ConstantesCRM.SVAZIO)) {
				switch (Integer.parseInt(form.getTipoRelacionamento())) {
				case 1:
					tipoPessoa = "U";
					break;
				case 2:
					tipoPessoa = "C";
					break;
				}
			} else {
				tipoPessoa = "C";
			}
			logTelaInicial.debug("TelaInicialController:iniciarChamada(" + user + ") >> tipoPessoa: " + tipoPessoa);
			// Obtém o idChamadaTelefonica
			ChamadaTelefonicaVO chamadaTelefonicaVO = atendimentoFacade.obtemChamadaTelefonicaVO(user, idPessoaDePara, tipoPessoa, inChamada);
			// seta na sessão o idChamadaTelefonica
			paramVO.setIdChamadaTelefonica(String.valueOf(chamadaTelefonicaVO.getIdChamadaTelefonica()));
			logTelaInicial.debug("TelaInicialController:iniciarChamada(" + user + ") >> idChamadaTelefonica: " + chamadaTelefonicaVO.getIdChamadaTelefonica());
			if (this.validarClienteForm == null) {
				this.validarClienteForm = new ValidarClienteForm();
			}
			this.validarClienteForm.setIdChamadaTelefonica(String.valueOf(chamadaTelefonicaVO.getIdChamadaTelefonica()));
			xmlDadosTI.append("<xmlDadosTI>");
			xmlDadosTI.append("<idChamadaTelefonica>" + trataNull(paramVO.getIdChamadaTelefonica()) + "</idChamadaTelefonica>");
			xmlDadosTI.append("<idGrauSatisfacao>" + trataNull(String.valueOf(chamadaTelefonicaVO.getIdGrauSatisfacao())) + "</idGrauSatisfacao>");
			xmlDadosTI.append("</xmlDadosTI>");

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:iniciarChamada(" + user + ") - [" + e.getMessage() + "]", e);
			AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
			ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
			ajaxErrorHandlerVO.setFriendlyMessage(MENSAGEM_GERAL);
			xmlDadosTI = new StringBuffer();
			xmlDadosTI.append(ajaxErrorHandlerVO.toString());

		} finally {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			response.setContentType(ConstantesCRM.SContentType);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			bufferedOutputStream.write(xmlDadosTI.toString().getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
			logTelaInicial.debug("TelaInicialController:iniciarChamada(" + user + ") >> FINALIZADO");
		}
		return null;
	}

	/**
	 * Método responsável por carregar o form validarClienteForm
	 */
	public void setValidarClienteForm(HttpServletRequest request) throws Exception {
		ParametrosVO paramVO = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS));
		this.validarClienteForm.setIdGrupo(paramVO.getIdGrupo());
		this.validarClienteForm.setIdUsuario(paramVO.getIdUsuario());
		this.validarClienteForm.setNumeroOrigem(paramVO.getNrTelefone());
		this.validarClienteForm.setNomeContato(paramVO.getNmContato());
		this.validarClienteForm.setIdPos(paramVO.getInPos());
		this.validarClienteForm.setTipoRelacionamento(paramVO.getIdTipoRelacionamento());
		this.validarClienteForm.setSenhaValidada((paramVO.getInSenhaValidada() != null ? paramVO.getInSenhaValidada() : ConstantesCRM.SVAZIO).equals(ConstantesCRM.STRUE));
		this.validarClienteForm.setIdCallCenter(paramVO.getIdCallCenter());
		this.validarClienteForm.setIdUra(paramVO.getIdUra());
		String strNumeroConsultado = ((paramVO.getNrCodArea() != null ? paramVO.getNrCodArea() : ConstantesCRM.SVAZIO) + (paramVO.getNrLinha() != null ? paramVO.getNrLinha() : ConstantesCRM.SVAZIO));
		this.validarClienteForm.setNumeroConsultado(strNumeroConsultado);
		this.validarClienteForm.setCliente(paramVO.getIdTipoRelacionamento() != null ? paramVO.getIdTipoRelacionamento().equals(ConstantesCRM.STWO) : false);
		this.validarClienteForm.setUsuario(paramVO.getIdTipoRelacionamento() != null ? paramVO.getIdTipoRelacionamento().equals(ConstantesCRM.SONE) : false);
		if (paramVO.getIdTipoRelacionamento() != null && paramVO.getIdTipoRelacionamento().equals(ConstantesCRM.STWO)) {
			this.validarClienteForm.setIdPessoa(paramVO.getIdPessoaCliente());
		}
		if (paramVO.getIdTipoRelacionamento() != null && paramVO.getIdTipoRelacionamento().equals(ConstantesCRM.SONE)) {
			this.validarClienteForm.setIdPessoa(paramVO.getIdPessoaUsuario());
		}
		this.validarClienteForm.setObservacao(paramVO.getObsAtend());
		this.validarClienteForm.setIndRedirTimeoutURA(paramVO.getIndRedirTimeoutURA());
	}

	/**
	 * Método responsável por setar a campanha a partir do grupo
	 */
	protected void setCampanhas() throws Exception {
		// Obtém campanhas a partir de grupo
		RetornoWFCTIVO[] campanhasVO = new RetornoWFCTIVO[0];
		campanhasVO = atendimentoFacade.obtemCampanhaGrupoVO(user, this.validarClienteForm.getIdGrupo());
		// verifica se irá setar como campanha padrão
		if (campanhasVO.length == 1 && campanhasVO[0].getInPadrao().equals(ConstantesCRM.SONE)) {
			// Só tem a campanha padrão
			this.validarClienteForm.setCampanhaPadrao("S");
		}
		this.validarClienteForm.setCampanhasVO(campanhasVO);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="DetalheLinha.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			LupaLinhaForm form = (LupaLinhaForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logLupaLinha.debug("TelaInicialController:salvar(" + user + ") >> INICIALIZADO");

			String idPessoaSelecionada = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaCliente();
			filtroLinhaVO = FiltroLinhaVO.Factory.newInstance();
			filtroLinhaVO.setIdPessoaFiltro(idPessoaSelecionada);
			filtroLinhaVO.setIdLinhaFiltro(form.getFormIDLinhaFiltro());

			if (form.getFormInDivulgaNumeroFiltro().equalsIgnoreCase(ConstantesCRM.SYES)) {
				filtroLinhaVO.setInDivulgaNumeroFiltro(ConstantesCRM.SONE);
			} else {
				filtroLinhaVO.setInDivulgaNumeroFiltro(ConstantesCRM.SZERO);
			}

			telaInicialFacadeControl.salvarLinhaVO(filtroLinhaVO, user);
			lupaLinhaForm.setFormListaDadosLupaLinha(lupaLinhaVO.getListaDadosLupaLinhaArray());

			for (int i = 0; i < lupaLinhaForm.getFormListaDadosLupaLinha().length; i++) {
				if (lupaLinhaForm.getFormListaDadosLupaLinha()[i].getInDivulgaNumero().equalsIgnoreCase(ConstantesCRM.SZERO)) {
					lupaLinhaForm.getFormListaDadosLupaLinha()[i].setInDivulgaNumero(ConstantesCRM.SNO);
				} else {
					lupaLinhaForm.getFormListaDadosLupaLinha()[i].setInDivulgaNumero(ConstantesCRM.SYES);
				}
			}

			lupaLinhaForm.setFormDadosLupaLinha(lupaLinhaVO.getDadosLupaLinha());

			if (lupaLinhaVO.getDadosLupaLinha().getInDivulgaNumero().equalsIgnoreCase(ConstantesCRM.SZERO)) {
				lupaLinhaForm.getFormDadosLupaLinha().setInDivulgaNumero(ConstantesCRM.SNO);
				lupaLinhaForm.setFormInDivulgaNumeroFiltro(ConstantesCRM.SNO);

			} else {
				lupaLinhaForm.getFormDadosLupaLinha().setInDivulgaNumero(ConstantesCRM.SYES);
				lupaLinhaForm.setFormInDivulgaNumeroFiltro(ConstantesCRM.SYES);
			}

			lupaLinhaForm.setFormQtLinhasLocalizadas(lupaLinhaVO.getQtLinhasLocalizadas());
			lupaLinhaForm.setFormQtLinhasRetornadas(lupaLinhaVO.getQtLinhasRetornadas());
			logLupaLinha.debug("TelaInicialController:salvar(" + user + ") >> FINALIZADO");

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:salvar(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 */
	public ActionForward ClienteDone(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="lupaCarteirizacaoPJ.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadCarteirizacao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			//CarteiraPJForm form = (CarteiraPJForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());

			logTelaInicial.debug("TelaInicialController:loadCarteirizacao(" + user + ") >> INICIALIZADO");
			carteiraPJForm = new CarteiraPJForm();
			String idPessoa = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaCliente();

			lupaCarteirizacaoPJVO = LupaCarteirizacaoPJVO.Factory.newInstance();
			lupaCarteirizacaoPJVO.addNewLupaCarteirizacaoPFVO();
			lupaCarteirizacaoPJVO = telaInicialFacadeControl.getLupaCarteirizacaoPJVO(user, idPessoa);
			carteiraPJForm.setLupaCarteiraPJ(lupaCarteirizacaoPJVO);
			carteiraPJForm.setListaPF(lupaCarteirizacaoPJVO.getLupaCarteirizacaoPFVOArray());

			if (carteiraPJForm.getListaPF().length == 0) {
				carteiraPJForm.setInVazio(ConstantesCRM.STRUE);
			}

			logTelaInicial.debug("TelaInicialController:loadCarteirizacao(" + user + ") >> FINALIZADO");

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:loadCarteirizacao(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);

			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="lupaSegmentacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadSegmentacao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			//SegmentacaoForm form = (SegmentacaoForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());

			logTelaInicial.debug("TelaInicialController:loadSegmentacao(" + user + ") >> INICIALIZADO");
			segmentacaoForm = new SegmentacaoForm();
			String idPessoa = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaCliente();

			lupaSegmentacaoVO = LupaSegmentacaoVO.Factory.newInstance();
			lupaSegmentacaoVO.addNewListaSegmentacao();
			lupaSegmentacaoVO = telaInicialFacadeControl.getLupaSegmentacaoVO(user, idPessoa);

			segmentacaoForm.setSegmentacao(lupaSegmentacaoVO);
			segmentacaoForm.setListaSegmentacao(lupaSegmentacaoVO.getListaSegmentacaoArray());

			logTelaInicial.debug("TelaInicialController:loadSegmentacao(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);

			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:loadSegmentacao(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="lupaCarteirizacaoPF.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadCarteiraPF(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			//CarteiraPFForm form = (CarteiraPFForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logTelaInicial.debug("TelaInicialController:loadCarteiraPF(" + user + ") >> INICIALIZADO");
			carteiraPFForm = new CarteiraPFForm();
			String idPessoa = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaCliente();
			lupaCarteirizacaoPFVO = LupaCarteirizacaoPFVO.Factory.newInstance();
			lupaCarteirizacaoPFVO.addNewTipoRelacionamento();
			lupaCarteirizacaoPFVO = telaInicialFacadeControl.getLupaCarteirizacaoPFVO(user, idPessoa);
			carteiraPFForm.setLupaCarteira(lupaCarteirizacaoPFVO);
			if (lupaCarteirizacaoPFVO.getNmNome() == null || lupaCarteirizacaoPFVO.getNmNome().trim().equals(ConstantesCRM.SVAZIO)) {
				carteiraPFForm.setInVazio(ConstantesCRM.STRUE);
			}
			logTelaInicial.debug("TelaInicialController:loadCarteiraPF(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:loadCarteiraPF(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	private void setLupaLinhaForm(HttpServletRequest request) throws Exception {
		user = ConstantesCRM.getCurrentUser(request.getSession());
		String idLinhaSelecionada = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdLinha();
		String idPessoaSelecionada = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaCliente();
		String idLinhaSO = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdLinhaSistemaOrigem();
		String idTipoLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha();
		String nrLinhaAtual = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
		lupaLinhaForm = new LupaLinhaForm();
		filtroLinhaVO = FiltroLinhaVO.Factory.newInstance();
		filtroLinhaVO.setPaginaAtual(ConstantesCRM.SZERO);
		filtroLinhaVO.setIdPessoaFiltro(idPessoaSelecionada);
		filtroLinhaVO.setIdLinhaFiltro(idLinhaSelecionada);
		filtroLinhaVO.setIdLinhaSistemaOrigem(idLinhaSO);
		lupaLinhaVO = telaInicialFacadeControl.getLupaLinhaVO(filtroLinhaVO, user, nrLinhaAtual.trim(), idLinhaSelecionada, Integer.parseInt(idTipoLinha));
		lupaLinhaVO.getFiltroLinhaVO().insertNewStatusLinhaVO(0).setDescricao(ConstantesCRM.STODOS);
		lupaLinhaVO.getFiltroLinhaVO().getStatusLinhaVOArray(0).setId(ConstantesCRM.SVAZIO);
		lupaLinhaVO.getFiltroLinhaVO().insertNewTipoLinhaVO(0).setDescricao(ConstantesCRM.STODOS);
		lupaLinhaVO.getFiltroLinhaVO().getTipoLinhaVOArray(0).setId(ConstantesCRM.SVAZIO);
		lupaLinhaForm.setFormTotalPagina(lupaLinhaVO.getTotalPagina());
		lupaLinhaForm.setFormTipoLinhaVO(lupaLinhaVO.getFiltroLinhaVO().getTipoLinhaVOArray());
		lupaLinhaForm.setFormStatusLinhaVO(lupaLinhaVO.getFiltroLinhaVO().getStatusLinhaVOArray());
		lupaLinhaForm.setFormListaDadosLupaLinha(lupaLinhaVO.getListaDadosLupaLinhaArray());
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="lupaLinha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward DetalheLinha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			LupaLinhaForm form = (LupaLinhaForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logLupaLinha.debug("TelaInicialController:DetalheLinha(" + user + ") >> INICIALIZADO");

			String idLinhaSelecionada = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdLinha();
			String idPessoaSelecionada = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaCliente();
			String idLinhaSO = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdLinhaSistemaOrigem();
			String idTipoLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha();
			String nrLinhaAtual = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
			lupaLinhaForm = new LupaLinhaForm();

			lupaLinhaVO = LupaLinhaVO.Factory.newInstance();
			filtroLinhaVO = FiltroLinhaVO.Factory.newInstance();

			if (ConstantesCRM.SVAZIO.equals(form.getFormPaginaAtual())) {
				filtroLinhaVO.setPaginaAtual(ConstantesCRM.SZERO);
				lupaLinhaForm.setFormPaginaAtual(ConstantesCRM.SZERO);

			} else {
				if ("proximo".equals(request.getParameter("pagOperacao"))) {
					lupaLinhaForm.setFormPaginaAtual(String.valueOf(Integer.parseInt(form.getFormPaginaAtual()) + 1));
				} else {
					lupaLinhaForm.setFormPaginaAtual(form.getFormPaginaAtual());
				}
				filtroLinhaVO.setPaginaAtual(lupaLinhaForm.getFormPaginaAtual());
			}

			filtroLinhaVO.setIdPessoaFiltro(idPessoaSelecionada);
			filtroLinhaVO.setIdLinhaFiltro(idLinhaSelecionada);
			filtroLinhaVO.setIdLinhaSistemaOrigem(idLinhaSO);

			logLupaLinha.debug("TelaInicialController:DetalheLinha(" + user + ") >> idPessoaSelecionada: " + idPessoaSelecionada);
			logLupaLinha.debug("TelaInicialController:DetalheLinha(" + user + ") >> idLinhaSelecionada: " + idLinhaSelecionada);
			logLupaLinha.debug("TelaInicialController:DetalheLinha(" + user + ") >> idLinhaSO: " + idLinhaSO);

			lupaLinhaVO = telaInicialFacadeControl.getLupaLinhaVO(filtroLinhaVO, user, nrLinhaAtual.trim(), idLinhaSelecionada, Integer.parseInt(idTipoLinha));
			lupaLinhaForm.setInProxima(lupaLinhaVO.getInProxima());
			lupaLinhaForm.setFormTotalPagina(lupaLinhaVO.getTotalPagina());
			lupaLinhaForm.setQtdLinhasAtivas(lupaLinhaVO.getQtdLinhasAtivas());
			lupaLinhaForm.setQtdLinhasCanceladas(lupaLinhaVO.getQtdLinhasInativas());
			lupaLinhaForm.setQtdLinhasTotal(lupaLinhaVO.getQtdLinhas());

			if (lupaLinhaVO.getDadosLupaLinha() != null) {
				lupaLinhaForm.setFormDadosLupaLinha(lupaLinhaVO.getDadosLupaLinha());
			}

			if (lupaLinhaVO.getDadosLupaLinha().getErro() != null && lupaLinhaVO.getDadosLupaLinha().getErro().length() > 0) {
				lupaLinhaForm.setErro(ConstantesCRM.STRUE);
			} else {
				lupaLinhaForm.setErro(ConstantesCRM.SFALSE);
			}

			lupaLinhaVO.getFiltroLinhaVO().insertNewStatusLinhaVO(0).setDescricao(ConstantesCRM.STODOS);
			lupaLinhaVO.getFiltroLinhaVO().getStatusLinhaVOArray(0).setId(ConstantesCRM.SVAZIO);
			lupaLinhaVO.getFiltroLinhaVO().insertNewTipoLinhaVO(0).setDescricao(ConstantesCRM.STODOS);
			lupaLinhaVO.getFiltroLinhaVO().getTipoLinhaVOArray(0).setId(ConstantesCRM.SVAZIO);
			lupaLinhaForm.setFormTipoLinhaVO(lupaLinhaVO.getFiltroLinhaVO().getTipoLinhaVOArray());
			lupaLinhaForm.setFormStatusLinhaVO(lupaLinhaVO.getFiltroLinhaVO().getStatusLinhaVOArray());
			lupaLinhaForm.setFormListaDadosLupaLinha(lupaLinhaVO.getListaDadosLupaLinhaArray());

			for (int i = 0; i < lupaLinhaForm.getFormListaDadosLupaLinha().length; i++) {
				if (lupaLinhaForm.getFormListaDadosLupaLinha()[i].getInDivulgaNumero().equalsIgnoreCase(ConstantesCRM.SZERO)) {
					lupaLinhaForm.getFormListaDadosLupaLinha()[i].setInDivulgaNumero(ConstantesCRM.SNO);
				} else {
					lupaLinhaForm.getFormListaDadosLupaLinha()[i].setInDivulgaNumero(ConstantesCRM.SYES);
				}
			}

			lupaLinhaForm.setFormIDTipoLinha(idTipoLinha);
			lupaLinhaForm.setFormDadosLupaLinha(lupaLinhaVO.getDadosLupaLinha());

			if (lupaLinhaVO.getDadosLupaLinha().getInDivulgaNumero().equalsIgnoreCase(ConstantesCRM.SZERO)) {
				lupaLinhaForm.getFormDadosLupaLinha().setInDivulgaNumero(ConstantesCRM.SNO);
				lupaLinhaForm.setFormInDivulgaNumeroFiltro(ConstantesCRM.SNO);
			} else {
				lupaLinhaForm.getFormDadosLupaLinha().setInDivulgaNumero(ConstantesCRM.SYES);
				lupaLinhaForm.setFormInDivulgaNumeroFiltro(ConstantesCRM.SYES);
			}

			lupaLinhaForm.setFormIDLinhaFiltro(idLinhaSelecionada);
			lupaLinhaForm.setFormQtLinhasLocalizadas(lupaLinhaVO.getQtLinhasLocalizadas());
			lupaLinhaForm.setFormQtLinhasRetornadas(lupaLinhaVO.getQtLinhasRetornadas());
			lupaLinhaForm.getFormDadosLupaLinha().setDsAparelho(lupaLinhaForm.getFormDadosLupaLinha().getDescricao());
			request.getSession().setAttribute("lupaLinhaForm", lupaLinhaForm);
			logLupaLinha.debug("TelaInicialController:DetalheLinha(" + user + ") >> FINALIZADO");

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:DetalheLinha(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 */
	public ActionForward getQuantidadeLinhas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		//LupaLinhaForm form = (LupaLinhaForm) formParam;
		response.setContentType(ConstantesCRM.SContentType);

		ParametrosVO parametrosVO = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);

		String idTipoLinha = parametrosVO.getIdTipoLinha();
		String nrLinhaAtual = parametrosVO.getNrLinha();
		String idLinhaSO = parametrosVO.getIdLinhaSistemaOrigem();
		String idLinha = parametrosVO.getIdLinha();

		filtroLinhaVO.setDsOperacao("getQuantidadeLinhas");
		filtroLinhaVO.setIdLinhaSistemaOrigem(idLinhaSO);
		filtroLinhaVO.setIdLinhaFiltro(idLinha);

		LupaLinhaVO olupaLinhaVO = telaInicialFacadeControl.getLupaLinhaVO(filtroLinhaVO, user, nrLinhaAtual.trim(), idLinha, Integer.parseInt(idTipoLinha));
		String output = ClienteUtils.getCleanXMLFromXSD(olupaLinhaVO, null);
		response.getOutputStream().write(output.getBytes(ConstantesCRM.SISO));
		filtroLinhaVO.setDsOperacao(null);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 */
	public ActionForward DetalheFaturaDone(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="dados" path="loadDados.do"
	 * @jpf:forward name="success" path="begin.do"
	 * @jpf:forward name="linhas" path="listaLinhas.jsp"
	 * @jpf:forward name="clientes" path="listaClientes.jsp"
	 * @jpf:forward name="protocolos" path="listaClientesProtocolo.jsp"
	 * @jpf:forward name="redireciona" path="redirecionaLinha.jsp"
	 * @jpf:forward name="nao" path="TelaInicial.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward pesquisaCliente(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		PesquisaForm form = (PesquisaForm) formParam;
		String valor = ConstantesCRM.SVAZIO;
		ParametrosVO parametrosVO = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
		String idGrupo = parametrosVO.getIdGrupo();
		String idChamadaTelefonica = parametrosVO.getIdChamadaTelefonica();

		ParametrosVO parametros = ParametrosVO.Factory.newInstance();
		if (idChamadaTelefonica != null && !idChamadaTelefonica.equals(ConstantesCRM.SVAZIO)) {
			getTiForm().getParametros().setIdChamadaTelefonica(idChamadaTelefonica);
			parametros.setIdChamadaTelefonica(idChamadaTelefonica);
		}

		parametros.setIdGrupo(idGrupo);
		request.getSession().setAttribute(ConstantesCRM.SPARAMETROS, parametros);

		try {
			request.getSession().removeAttribute(ConstantesCRM.NRPROTOCOLO);
			getTiForm().setNrProtocolo(ConstantesCRM.SVAZIO);

			String msgLogDebug = "TelaInicialController:pesquisaCliente(" + user + ") >>";
			String pageNumber = ConstantesCRM.SVAZIO;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logTelaInicial.debug(msgLogDebug + " INICIALIZADO");
			String destino = "redireciona";
			String pesquisa = (request.getParameter("pesquisa") != null ? request.getParameter("pesquisa").trim().toLowerCase() : ConstantesCRM.SVAZIO);
			int offset = request.getParameter("offset") == null ? 0 : request.getParameter("offset").equals(ConstantesCRM.SVAZIO) ? 0 : Integer.parseInt(request.getParameter("offset"));
			pesquisaForm = new PesquisaForm();
			pesquisaForm.setTipoPesquisa(pesquisa);

			if ("clientes".equals(request.getParameter("tipoLista")) && "protocolo".equals(pesquisa)) {
				try {
					valor = request.getParameter("valor");
					pesquisaForm.setNome(valor);
					pesquisaForm.setNmMeio(ConstantesCRM.SVAZIO);
					pesquisaForm.setSobrenome(ConstantesCRM.SVAZIO);
					pageNumber = ConstantesCRM.SVAZIO;
					destino = "redireciona";

					if (offset > 0) {
						pageNumber = (request.getParameter("pageNumber") != null ? String.valueOf(Integer.parseInt(request.getParameter("pageNumber")) + 1) : "1");
					} else {
						pageNumber = request.getParameter("pageNumber") != null ? String.valueOf(Integer.parseInt(request.getParameter("pageNumber")) - 1) : "1";
					}

					PesquisaTIVO listaClientes = PesquisaTIVO.Factory.newInstance();

					try {
						listaClientes = telaInicialFacadeControl.getPesquisaTIClientes(user, pesquisa, pageNumber, StringEscapeUtils.escapeXml(valor), ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO);

					} catch (Exception e) {
						if (e.getMessage().indexOf("no data found") > 0) {
							listaClientes = PesquisaTIVO.Factory.newInstance();
							listaClientes.addNewPaginacao();
							listaClientes.getPaginacao().setPageNumber(0);
							listaClientes.getPaginacao().setHasNext(0);
						} else {
							logTelaInicial.error("TelaInicialController::pesquisaCliente.do::", e);
							throw e;
						}
					}

					pesquisaForm.setListaClientes(listaClientes);
					pesquisaForm.setHasNext(String.valueOf(listaClientes.getPaginacao().getHasNext()));
					pesquisaForm.setPageNumber(String.valueOf(listaClientes.getPaginacao().getPageNumber()));

					if (listaClientes.getLimite() != null) {
						pesquisaForm.setInError(ConstantesCRM.STRUE);
					}

					if (listaClientes.getClientesPesquisadosArray().length == 0) {
						request.setAttribute("inicioTela", ConstantesCRM.SONE);
						destino = "redireciona";
						logTelaInicial.debug(msgLogDebug + " FINALIZADO: nenhum cliente encontrado");

					} else if (listaClientes.getClientesPesquisadosArray().length > 1) {
						request.getSession().removeAttribute("idProspect");
						request.getSession().removeAttribute("inicioTela");
						request.setAttribute("nrLinha", valor);
						destino = "protocolos";
						logTelaInicial.debug(msgLogDebug + " FINALIZADO: Listagem de Clientes");

					} else {
						destino = "redireciona";
						String nrLinha = listaClientes.getClientesPesquisadosArray(0).getNrLinha();
						String idPessoa = listaClientes.getClientesPesquisadosArray(0).getIdPessoa();
						String inCliente = listaClientes.getClientesPesquisadosArray(0).getInClienteUsuario();
						String isProtAtiv = listaClientes.getClientesPesquisadosArray(0).getIsProtocoloAtivo();

						if (Integer.toString(idPessoaProspect).equals(idPessoa)) { // Não Cliente
							request.getSession().removeAttribute("nrLinha");
							request.getSession().removeAttribute("idProspect");
							request.setAttribute("nrLinhaNaoCliente", nrLinha);
							request.setAttribute("nrProtocolo", valor);

						} else if ("N".equals(inCliente)) {
							request.getSession().removeAttribute("nrLinha");
							request.getSession().setAttribute("idProspect", idPessoa);
							request.setAttribute("nrProtocolo", valor);

						} else if ("C".equals(inCliente)) {
							request.getSession().removeAttribute("idProspect");
							request.setAttribute("nrLinha", nrLinha);
						}

						if (ConstantesCRM.SONE.equals(isProtAtiv)) {
							request.setAttribute("isProtocoloAtivo", "true");
							request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, valor);
							getTiForm().setPesquisaProtocolo(true);
						} else {
							getTiForm().setPesquisaProtocolo(false);
						}
					}
				} catch (Exception e) {
					logTelaInicial.error("TelaInicialController::pesquisaCliente.do::", e);
				}

				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(destino);
				// Final consulta por Protocolo

			} else if (request.getParameter("tipoLista").equalsIgnoreCase("clientes") && !pesquisa.equals("conta")) {
				logTelaInicial.debug(msgLogDebug + " pesquisa: " + pesquisa);
				valor = ConstantesCRM.SVAZIO;

				if (!pesquisa.equalsIgnoreCase("nome") || !pesquisa.equals("razao")) {
					if (form.getNome() == null || form.getNome().equals(ConstantesCRM.SVAZIO)) {
						valor = request.getParameter("valor");
					} else {
						valor = form.getNome();
					}
				} else {
					valor = form.getNome();
				}

				String nmSobreNome = form.getSobrenome();
				String nmMeio = form.getNmMeio();
				logTelaInicial.debug(msgLogDebug + " valor: " + valor);

				if (null != request.getParameter("prospect")) {
					valor = request.getParameter("prNome");
					nmMeio = request.getParameter("nmNome");
					nmSobreNome = request.getParameter("ulNome");
				}

				pesquisaForm.setNome(valor);
				pesquisaForm.setNmMeio(nmMeio);
				pesquisaForm.setSobrenome(nmSobreNome);
				pesquisaForm.setTipoPesquisa(pesquisa);
				PesquisaTIVO listaClientes = PesquisaTIVO.Factory.newInstance();
				pageNumber = ConstantesCRM.SVAZIO;

				if (offset > 0) {
					pageNumber = (request.getParameter("pageNumber") != null ? String.valueOf(Integer.parseInt(request.getParameter("pageNumber")) + 1) : "1");
				} else {
					pageNumber = request.getParameter("pageNumber") != null ? String.valueOf(Integer.parseInt(request.getParameter("pageNumber")) - 1) : "1";
				}

				listaClientes = telaInicialFacadeControl.getPesquisaTIClientes(user, pesquisa, pageNumber, StringEscapeUtils.escapeXml(valor), StringEscapeUtils.escapeXml(nmMeio), StringEscapeUtils.escapeXml(nmSobreNome), ConstantesCRM.SVAZIO);
				pesquisaForm.setListaClientes(listaClientes);
				pesquisaForm.setHasNext(String.valueOf(listaClientes.getPaginacao().getHasNext()));
				pesquisaForm.setPageNumber(String.valueOf(listaClientes.getPaginacao().getPageNumber()));

				if (listaClientes.getLimite() != null) {
					pesquisaForm.setInError(ConstantesCRM.STRUE);
				}

				if ((pesquisa.equalsIgnoreCase("celular")) && (listaClientes.getClientesPesquisadosArray().length > 0)) {
					request.setAttribute("nrLinha", valor);
					// destino = ConstantesCRM.SUCCESS;
					destino = "dados";
					request.getSession().removeAttribute("idProspect");
					request.getSession().removeAttribute("inicioTela");
					logTelaInicial.debug(msgLogDebug + " FINALIZADO: Listagem de Clientes");

					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(destino);

				} else if ((pesquisa.equalsIgnoreCase("celular")) && (listaClientes.getClientesPesquisadosArray().length == 0)) {
					request.setAttribute("inicioTela", ConstantesCRM.STRUE);
					destino = "nao";
					logTelaInicial.debug(msgLogDebug + " FINALIZADO: Um cliente encontrado");

					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(destino);

				} else {
					if (pesquisa.equalsIgnoreCase("nome") || pesquisa.equalsIgnoreCase("razao")) {
						destino = "clientes";

					} else {

						if (listaClientes.getClientesPesquisadosArray().length == 0) {
							request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
							return mapping.findForward("clientes");
						}

						PesquisaTIVO listaLinhas = PesquisaTIVO.Factory.newInstance();
						if (listaClientes.getClientesPesquisadosArray().length > 1) {
							request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
							return mapping.findForward("clientes");

						} else {
							if (Integer.parseInt(pesquisaForm.getPageNumber()) > 1) {
								request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
								return mapping.findForward("clientes");

							} else {
								listaLinhas = telaInicialFacadeControl.getPesquisaTILinhas(user, listaClientes.getClientesPesquisadosArray(0).getIdPessoa());
								pesquisaForm.setListaLinhas(listaLinhas);
								if (pesquisaForm.getListaLinhas().getLinhasPesquisadasArray().length == 1) {
									request.setAttribute("nrLinha", pesquisaForm.getListaLinhas().getLinhasPesquisadasArray(0).getNrLinha());
									destino = "redireciona";
									request.getSession().removeAttribute("idProspect");
									request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
									return mapping.findForward(destino);

								} else if (pesquisaForm.getListaLinhas().getLinhasPesquisadasArray().length > 1) {
									request.getSession().removeAttribute("idProspect");
									destino = "linhas";
									pesquisaForm.setPageNumber(ConstantesCRM.SONE);
									pesquisaForm.setHasNext(ConstantesCRM.SZERO);

								} else {
									request.getSession().setAttribute("idProspect", listaClientes.getClientesPesquisadosArray(0).getIdPessoa());
									destino = "redireciona";
								}
							}
						}
					}
				}

			} else {
				PesquisaTIVO listaLinhas = PesquisaTIVO.Factory.newInstance();
				pageNumber = ConstantesCRM.SVAZIO;

				if (offset > 0) {
					pageNumber = (request.getParameter("pageNumber") != null ? String.valueOf(Integer.parseInt(request.getParameter("pageNumber")) + 1) : "1");
				} else {
					pageNumber = request.getParameter("pageNumber") != null ? String.valueOf(Integer.parseInt(request.getParameter("pageNumber")) - 1) : "1";
				}

				if (request.getParameter("pesquisa") != null && request.getParameter("pesquisa").trim().equals("conta")) {
					String cdConta = request.getParameter("valor");
					listaLinhas = telaInicialFacadeControl.getPesquisaTILinhasContaPaginada(user, cdConta, pageNumber);
					pesquisaForm.setListaLinhas(listaLinhas);
					pesquisaForm.setHasNext(String.valueOf(listaLinhas.getPaginacao().getHasNext()));
					pesquisaForm.setPageNumber(String.valueOf(listaLinhas.getPaginacao().getPageNumber()));
					pesquisaForm.setTipoPesquisa(pesquisa);
					request.setAttribute("valor", cdConta);
					if (pesquisaForm.getListaLinhas().getLinhasPesquisadasArray().length == 1 && !(Integer.parseInt(pesquisaForm.getPageNumber()) > 1)) {
						request.setAttribute("nrLinha", pesquisaForm.getListaLinhas().getLinhasPesquisadasArray(0).getNrLinha());
						request.setAttribute("inBloqueado", pesquisaForm.getListaLinhas().getLinhasPesquisadasArray(0).getInBloqueado());
						destino = "redireciona";
						request.getSession().removeAttribute("idProspect");
						request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
						return mapping.findForward(destino);

					} else if (pesquisaForm.getListaLinhas().getLinhasPesquisadasArray().length >= 1 && (Integer.parseInt(pesquisaForm.getPageNumber()) >= 1)) {
						request.getSession().removeAttribute("idProspect");
						destino = "linhas";
						request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
						return mapping.findForward(destino);

					} else { // PROSPECT
						if (request.getParameter("idPessoa") != null) {
							request.getSession().setAttribute("idProspect", request.getParameter("idPessoa"));
							destino = "redireciona";
							request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
							return mapping.findForward(destino);

						} else {
							request.getSession().removeAttribute("idProspect");
							destino = "linhas";
							request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
							return mapping.findForward(destino);
						}
					}

				} else {
					listaLinhas = telaInicialFacadeControl.getPesquisaTILinhas(user, request.getParameter("idPessoa"));
					pesquisaForm.setListaLinhas(listaLinhas);
					pesquisaForm.setPageNumber(ConstantesCRM.SONE);
					pesquisaForm.setHasNext(ConstantesCRM.SZERO);
				}

				if (pesquisaForm.getListaLinhas().getLinhasPesquisadasArray().length == 1) {
					request.setAttribute("nrLinha", pesquisaForm.getListaLinhas().getLinhasPesquisadasArray(0).getNrLinha());
					request.setAttribute("inBloqueado", pesquisaForm.getListaLinhas().getLinhasPesquisadasArray(0).getInBloqueado());
					destino = "redireciona";
					request.getSession().removeAttribute("idProspect");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(destino);

				} else if (pesquisaForm.getListaLinhas().getLinhasPesquisadasArray().length > 1) {
					request.getSession().removeAttribute("idProspect");
					destino = "linhas";

				} else { // PROSPECT
					if (request.getParameter("idPessoa") != null) {
						request.getSession().setAttribute("idProspect", request.getParameter("idPessoa"));
						destino = "redireciona";
					} else {
						request.getSession().removeAttribute("idProspect");
						destino = "linhas";
					}
				}
			}
			logTelaInicial.debug(msgLogDebug + " FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:pesquisaCliente(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, ConstantesCRM.TELAINCIAL);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * Método para carregamento de parametros na sessão
	 */
	protected void carregaParametrosSessao(String nrLinha, String tipoRelacionamento, HttpServletRequest request) throws Exception {
		if (tiForm == null) {
			tiForm = new TIForm();
		}
		user = ConstantesCRM.getCurrentUser(request.getSession());
		// recupera grupo setado na sessão
		String idGrupo = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdGrupo();
		// Caso ja exista na sessao o idTipoLinha, ja sera setado
		tiForm.getParametros().setIdTipoLinha(request.getSession().getAttribute(ConstantesCRM.SPARAMETROS) != null && ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha() != null ? ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha() : ConstantesCRM.SVAZIO);
		// verifica se existe chamadaTelefonica
		String idChamadaTelefonica = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdChamadaTelefonica();

		ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
		String idRelacionamento = parametros.getIdTipoRelacionamento();
		String idTipoLinha = parametros.getIdTipoLinha();
		String inPos = parametros.getInPos();
		String idPessoaCliente = parametros.getIdPessoaCliente();
		String idClienteDePara = parametros.getIdClienteDePara();
		//String isPortada = parametros.getIsPortada();
		//String nrLinhaParam = parametros.getNrLinha();
		String nrProtocolo = parametros.getNrProtocolo();

		String nrProtocoloSessao = (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO);
		if (nrProtocoloSessao != null && !ConstantesCRM.SVAZIO.equals(nrProtocoloSessao)) {
			if (!nrProtocoloSessao.equals(nrProtocolo)) {
				nrProtocolo = nrProtocoloSessao;
			}
		}

		// PARAMETROS ENTREGUE PELO SERVICO TUXEDO
		ParametrosVO pTemp = ParametrosVO.Factory.newInstance();
		pTemp = telaInicialFacadeControl.getParametrosVO(user, nrLinha, tipoRelacionamento);

		if ("1".equals(tiForm.getLinha().getIsPortada())) {
			pTemp.setIdTipoRelacionamento(idRelacionamento);
			pTemp.setIdTipoLinha(idTipoLinha);
			pTemp.setInPos(inPos);
			pTemp.setNrConta(ConstantesCRM.SVAZIO);
			pTemp.setIdPessoaCliente(idPessoaCliente);
			pTemp.setIdPessoaUsuario(idPessoaCliente);
			pTemp.setIdClienteDePara(idClienteDePara);
			pTemp.setIdUsuarioDePara(idClienteDePara);
		}

		pTemp.setNrProtocolo(nrProtocolo);
		tiForm.setParametros(pTemp);
		tiForm.getParametros().setIdGrupo(idGrupo);
		tiForm.getParametros().setNrLinha(nrLinha);
		tiForm.getParametros().setIdUsuario(user);
		// parametro idFase, utilizado para carregar a arvore de contato corretamente Denys Sene - 08/01/2004 - 18h00
		tiForm.getParametros().setIdFase(ConstantesCRM.SONE);
		if ((idChamadaTelefonica != null) && !(idChamadaTelefonica.equals(ConstantesCRM.SVAZIO))) {
			tiForm.getParametros().setIdChamadaTelefonica(idChamadaTelefonica);
		}
		// removendo o idProspect da sessão
		request.getSession().removeAttribute("idProspect");
		request.getSession().setAttribute(ConstantesCRM.SPARAMETROS, tiForm.getParametros());
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="dadosLinha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward linha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			tiForm.setLinha(telaInicialFacadeControl.getDADLINHATI(user, tiForm.getNrLinha()));
			tiForm.getLinha().setNrLinha(tiForm.getNrLinha());
			tiForm.setQtdLinhasTotal(Integer.parseInt(tiForm.getLinha().getQtdLinhas()));
			tiForm.setQtdLinhasCanceladas(tiForm.getQtdLinhasTotal() - Integer.parseInt(tiForm.getLinha().getQtdLinhasAtivas()));

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:linha(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, ConstantesCRM.TELAINCIAL);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="dadosFaturamento.jsp"
	 * @jpf:forward name="prepago" path="dadosPrePago.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward fatura(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			ParametrosVO param = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
			if ("1".equals(param.getIdTipoLinha()) || "5".equals(param.getIdTipoLinha()) || "4".equals(param.getIdTipoLinha()) || "7".equals(param.getIdTipoLinha())) {
				String nrConta = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrConta();
				tiForm.getFatura().setNrConta(nrConta);
				tiForm.getFatura().setDsCicloFatura(ConstantesCRM.SVAZIO);
				tiForm.getFatura().setSaldo(ConstantesCRM.SVAZIO);
				tiForm.getFatura().setDtVencimento(ConstantesCRM.SVAZIO);
				tiForm.getFatura().setDsFormaPagamento(ConstantesCRM.SVAZIO);
				tiForm.getFatura().setInContaCobranca(ConstantesCRM.SZERO);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);

			} else {
				// Pré-Pago
				// INICIAR BEAN DE PRE-PAGO PARA dadosPrePago.jsp
				detalhesSaldoForm = new DetalhesSaldoForm();
				detalhesSaldoForm.setBalanco(ConstantesCRM.SVAZIO);
				detalhesSaldoForm.getDetalhesSaldoVO().setCdSeguranca(ConstantesCRM.SVAZIO);
				detalhesSaldoForm.getDetalhesSaldoVO().setEstadoLinha(ConstantesCRM.SVAZIO);
				detalhesSaldoForm.getDetalhesSaldoVO().setPrValidadeReal(ConstantesCRM.SVAZIO);

				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("prepago");
			}
		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:fatura(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, ConstantesCRM.TELAINCIAL);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="dados.jsp"
	 * @jpf:forward name="inicio" path="nada.html"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadDados(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			//TIForm form = (TIForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			tiForm = new TIForm();
			logTelaInicial.debug("TelaInicialController:loadDados(" + user + ") >> INICIADO -> Carregamento de dados");
			// Pré-Pago
			// INICIAR BEAN DE PRE-PAGO PARA dadosPrePago.jsp
			detalhesSaldoForm = new DetalhesSaldoForm();
			detalhesSaldoForm.setBalanco(ConstantesCRM.SVAZIO);
			detalhesSaldoForm.getDetalhesSaldoVO().setCdSeguranca(ConstantesCRM.SVAZIO);
			detalhesSaldoForm.getDetalhesSaldoVO().setEstadoLinha(ConstantesCRM.SVAZIO);
			detalhesSaldoForm.getDetalhesSaldoVO().setPrValidadeReal(ConstantesCRM.SVAZIO);
			// NAO CLIENTE
			if (request.getSession().getAttribute("naoCliente") != null) {
				/*
				 * logTelaInicial.debug("TelaInicialController:loadDados("+user+") >> DETECTADO -> Não cliente");
				 * request.getSession().removeAttribute("naoCliente"); String idNaoCliente = "26"; telaInicial =
				 * TelaInicialVO.Factory.newInstance(); telaInicial.addNewTICliente();
				 * telaInicial.getTICliente().addNewCarterizacaoVO(); telaInicial.getTICliente().addNewSegmentacaoVO();
				 * telaInicial.addNewEnderecoBaseVO(); telaInicial.getEnderecoBaseVO().addNewPaisVO();
				 * telaInicial.getEnderecoBaseVO().addNewUFVO(); telaInicial.addNewTIDocumento();
				 * telaInicial.addNewTIFaturamento(); telaInicial.addNewTILinha();
				 * telaInicial.getTILinha().addNewUsuarioLinhaVO();
				 * telaInicial.getTICliente().setNmNome("Não Identificado"); ((ParametrosVO)
				 * request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setIdTipoLinha(ConstantesCRM.SONE);
				 * ((ParametrosVO)
				 * request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setIdPessoaCliente(idNaoCliente);
				 * ((ParametrosVO)
				 * request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setIdClienteDePara(idNaoCliente);
				 * ((ParametrosVO)
				 * request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setIdUsuarioDePara(idNaoCliente);
				 * ((ParametrosVO)
				 * request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setIdPessoaUsuario(idNaoCliente);
				 * ((ParametrosVO)
				 * request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setNrConta(ConstantesCRM.SVAZIO);
				 * ((ParametrosVO)
				 * request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setIdTipoRelacionamento("7");
				 */
			} else {
				// CLIENTE OU USUARIO
				if (request.getSession().getAttribute("idProspect") == null) {
					logTelaInicial.debug("TelaInicialController:loadDados(" + user + ") >> DETECTADO -> Cliente ou Usuário");
					String nrLinha = request.getParameter("valor").toString();
					carregaParametrosSessao(nrLinha, ConstantesCRM.STWO, request);
					telaInicial = TelaInicialVO.Factory.newInstance();
					telaInicial = telaInicialFacadeControl.getTelaInicial(user, ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha());
					if ("1".equals(telaInicial.getTILinha().getInBloqueado())) {
						request.setAttribute("inBloqueado", ConstantesCRM.SONE);
					}
					telaInicial.getTICliente().getSegmentacaoVO().setDescricao(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getDsSegmentacao());
					telaInicial.getTICliente().getSegmentacaoVO().setIdSegmentacao(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdSegmentacao());
					tiForm.setFatura(telaInicial.getTIFaturamento());
					tiForm.setLinha(telaInicial.getTILinha());
					tiForm.setNrLinha(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha());
					tiForm.getLinha().setNrLinha(tiForm.getNrLinha());
					tiForm.setQtdLinhasTotal(Integer.parseInt(tiForm.getLinha().getQtdLinhas()));
					tiForm.getLinha().setQtdLinhasAtivas(tiForm.getLinha().getQtdLinhasAtivas());
					tiForm.setQtdLinhasCanceladas(Integer.parseInt(tiForm.getLinha().getQtdLinhasInativas()));
				} // PROSPECT
				else {
					logTelaInicial.debug("TelaInicialController:loadDados(" + user + ") >> DETECTADO -> Prospect");
					String idProspect = request.getSession().getAttribute("idProspect").toString();
					telaInicial = telaInicialFacadeControl.getTelaInicialProspect(user, idProspect);
					telaInicial.getTICliente().addNewCarterizacaoVO();
					telaInicial.getTICliente().addNewSegmentacaoVO();
					telaInicial.addNewEnderecoBaseVO();
					telaInicial.getEnderecoBaseVO().addNewPaisVO();
					telaInicial.getEnderecoBaseVO().addNewUFVO();
					telaInicial.addNewTIDocumento();
					telaInicial.addNewTIFaturamento();
					telaInicial.addNewTILinha();
					// seta na sessão dados para carregamento do faturamento ou pré-pago
					((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setIdTipoRelacionamento("6");
					((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setInPos(ConstantesCRM.STWO);
					((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setNrConta(ConstantesCRM.SVAZIO);
					((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setIdPessoaCliente(idProspect);
					((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setIdPessoaUsuario(idProspect);
					((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setIdClienteDePara(telaInicial.getTICliente().getIdPessoaDePara());
					((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setIdUsuarioDePara(telaInicial.getTICliente().getIdPessoaDePara());
					((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setIdTipoLinha(ConstantesCRM.SVAZIO);
				}
			}
			tiForm.setCliente(telaInicial.getTICliente());
			tiForm.setEndereco(telaInicial.getEnderecoBaseVO());
			tiForm.setDocumento(telaInicial.getTIDocumento());
			request.getSession().setAttribute("TIFORM", tiForm);

			BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			response.setContentType(ConstantesCRM.SContentType);
			outputStream.write(telaInicial.toString().replaceAll("vo:", ConstantesCRM.SVAZIO).getBytes(ConstantesCRM.SISO));
			outputStream.flush();
			outputStream.close();

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return null;

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:loadDados(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, ConstantesCRM.TELAINCIAL);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="lupaLinha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward pesquisarLinha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			LupaLinhaForm form = (LupaLinhaForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logTelaInicial.debug("TelaInicialController:pesquisarLinha(" + user + ") >> INICIALIZADO -> Pesquisa de linha");
			String idPessoaSelecionada = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaCliente();
			lupaLinhaVO = LupaLinhaVO.Factory.newInstance();
			filtroLinhaVO = FiltroLinhaVO.Factory.newInstance();
			filtroLinhaVO.setIdPessoaFiltro(idPessoaSelecionada);
			filtroLinhaVO.setNrLinhaFiltro(form.getFormNrLinhaFiltro().trim());
			filtroLinhaVO.setNrLinhaFiltroAte(form.getFormNrLinhaFiltroAte().trim());
			filtroLinhaVO.setIdTipoLinhaFiltro(form.getFormIDTipoLinhaFiltro().trim());
			filtroLinhaVO.setIdStatusLinhaFiltro(form.getFormIDStatusLinhaFiltro().trim());

			if (ConstantesCRM.SVAZIO.equals(form.getFormPaginaAtual())) {
				filtroLinhaVO.setPaginaAtual(ConstantesCRM.SZERO);
				lupaLinhaForm.setFormPaginaAtual(ConstantesCRM.SZERO);

			} else {
				if ("proximo".equals(request.getParameter("pagOperacao"))) {
					lupaLinhaForm.setFormPaginaAtual(String.valueOf(Integer.parseInt(form.getFormPaginaAtual()) + 1));
				} else {
					try {
						if (lupaLinhaForm == null) {
							setLupaLinhaForm(request);
						}
					} catch (Exception e) {
					}
					lupaLinhaForm.setFormPaginaAtual(form.getFormPaginaAtual());
				}
				filtroLinhaVO.setPaginaAtual(lupaLinhaForm.getFormPaginaAtual());
			}

			ParametrosVO parametrosVO = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
			if (filtroLinhaVO.getIdLinhaFiltro() == null || ConstantesCRM.SVAZIO.equals(filtroLinhaVO.getIdLinhaFiltro()) || ConstantesCRM.SZERO.equals(filtroLinhaVO.getIdLinhaFiltro())) {
				filtroLinhaVO.setIdLinhaFiltro(parametrosVO.getIdLinha());
			}

			lupaLinhaVO = telaInicialFacadeControl.obterLinhaVO(filtroLinhaVO, user);
			lupaLinhaForm.setInProxima(lupaLinhaVO.getInProxima());
			lupaLinhaForm.setFormTotalPagina(lupaLinhaVO.getTotalPagina());

			if (lupaLinhaForm == null) {
				lupaLinhaForm = new LupaLinhaForm();
				lupaLinhaForm = (LupaLinhaForm) request.getSession().getAttribute("lupaLinhaForm");
				request.getSession().removeAttribute("lupaLinhaForm");
			}

			lupaLinhaForm.setFormNrLinhaFiltro(form.getFormNrLinhaFiltro());
			lupaLinhaForm.setFormNrLinhaFiltroAte(form.getFormNrLinhaFiltroAte());
			lupaLinhaForm.setFormIDStatusLinhaFiltro(form.getFormIDStatusLinhaFiltro());
			lupaLinhaForm.setFormIDTipoLinhaFiltro(form.getFormIDTipoLinhaFiltro());
			lupaLinhaForm.setFormIDTipoLinha(form.getFormIDTipoLinhaFiltro());
			lupaLinhaForm.setFormListaDadosLupaLinha(lupaLinhaVO.getListaDadosLupaLinhaArray());

			for (int i = 0; i < lupaLinhaForm.getFormListaDadosLupaLinha().length; i++) {
				if (lupaLinhaForm.getFormListaDadosLupaLinha()[i].getInDivulgaNumero().equalsIgnoreCase(ConstantesCRM.SZERO)) {
					lupaLinhaForm.getFormListaDadosLupaLinha()[i].setInDivulgaNumero(ConstantesCRM.SNO);
				} else {
					lupaLinhaForm.getFormListaDadosLupaLinha()[i].setInDivulgaNumero(ConstantesCRM.SYES);
				}
			}

			lupaLinhaForm.setFormQtLinhasLocalizadas(lupaLinhaVO.getQtLinhasLocalizadas());
			lupaLinhaForm.setFormQtLinhasRetornadas(lupaLinhaVO.getQtLinhasRetornadas());
			lupaLinhaForm.setFormIDLinhaFiltro(ConstantesCRM.SVAZIO);

			if (!lupaLinhaForm.getFormIDTipoLinha().equals(ConstantesCRM.STWO)) { // Não documentada o porque desta
				// verificação.
				lupaLinhaForm.setFormDadosLupaLinha(DadosLupaLinha.Factory.newInstance());
			}

			logTelaInicial.debug("TelaInicialController:pesquisarLinha(" + user + ") >> FINALIZADO -> Pesquisa de linha");

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:pesquisarLinha(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="error" path="/error.jsp"
	 * @jpf:forward name="GERAL" path="lupaLinhaiFrame.jsp"
	 */
	public ActionForward loadAbaGeral(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			LupaLinhaForm form = (LupaLinhaForm) formParam;
			String tecnologia = ConstantesCRM.SVAZIO;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logTelaInicial.debug("TelaInicialController:pesquisarDetalheLinha(" + user + ") >> INICIALIZADO -> Pesquisa de Detalhes da Linha");
			//String idPessoaSelecionada = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaCliente();
			int idTipoLinha = Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());

			if (idTipoLinha == 5 || idTipoLinha == 6 || idTipoLinha == 7) {
				tecnologia = ConstantesCRM.DES_TECNOLOGIA_CHIP;
			} else if (idTipoLinha == 1 || idTipoLinha == 2 || idTipoLinha == 4) {
				tecnologia = ConstantesCRM.DES_TECNOLOGIA_ESN;
			}

			if (lupaLinhaForm == null) {
				lupaLinhaForm = new LupaLinhaForm();
				lupaLinhaVO = LupaLinhaVO.Factory.newInstance();
			}

			lupaLinhaForm.setFormNrLinhaFiltro(form.getFormNrLinhaFiltro());
			lupaLinhaForm.setFormNrLinhaFiltroAte(form.getFormNrLinhaFiltroAte());
			lupaLinhaForm.setFormIDStatusLinhaFiltro(form.getFormIDStatusLinhaFiltro());
			lupaLinhaForm.setFormIDTipoLinhaFiltro(form.getFormIDTipoLinhaFiltro());
			lupaLinhaForm.setFormIDLinhaFiltro(form.getFormIDLinhaFiltro());
			lupaLinhaForm.setFormIDTipoLinha(Integer.toString(idTipoLinha));
			lupaLinhaForm.getFormDadosLupaLinha().setDsTecnologia(tecnologia);
			lupaLinhaForm.getFormDadosLupaLinha().setIdLinha(filtroLinhaVO.getIdLinhaFiltro());

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:pesquisarDetalheLinha(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("GERAL");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="lupaLinha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 * @jpf:forward name="GERAL" path="lupaLinhaiFrame.jsp"
	 * @jpf:forward name="SERVICO" path="Servico.jsp"
	 * @jpf:forward name="PERMISSOES" path="abaPermissoesMensagens.jsp"
	 * @jpf:forward name="TRAFEGODADOS" path="abaTrafegoDados.jsp"
	 */
	public ActionForward pesquisarDetalheLinha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		try {
			LupaLinhaForm form = (LupaLinhaForm) formParam;
			String tecnologia = ConstantesCRM.SVAZIO;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logTelaInicial.debug("TelaInicialController:pesquisarDetalheLinha(" + user + ") >> INICIALIZADO -> Pesquisa de Detalhes da Linha");
			String idPessoaSelecionada = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaCliente();
			int idTipoLinha = Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());
			if (idTipoLinha == 5 || idTipoLinha == 6 || idTipoLinha == 7) {
				tecnologia = ConstantesCRM.DES_TECNOLOGIA_CHIP;
			} else if (idTipoLinha == 1 || idTipoLinha == 2 || idTipoLinha == 4) {
				tecnologia = ConstantesCRM.DES_TECNOLOGIA_ESN;
			}
			if (request.getParameter("first") == null) {
				LupaLinhaVO pesquisaLinhaVO;
				pesquisaLinhaVO = LupaLinhaVO.Factory.newInstance();
				filtroLinhaVO = FiltroLinhaVO.Factory.newInstance();
				// lupaLinhaForm.setFormQtLinhasLocalizadas(lupaLinhaVO.getQtLinhasLocalizadas());
				// lupaLinhaForm.setFormQtLinhasRetornadas(lupaLinhaVO.getQtLinhasRetornadas());
				if (lupaLinhaForm == null) {
					lupaLinhaForm = new LupaLinhaForm();
					lupaLinhaVO = LupaLinhaVO.Factory.newInstance();
				}

				filtroLinhaVO.setIdPessoaFiltro(idPessoaSelecionada);
				if (null != form.getFormIDLinhaFiltro() && !form.getFormIDLinhaFiltro().equals(ConstantesCRM.SVAZIO)) {
					filtroLinhaVO.setIdLinhaFiltro(form.getFormIDLinhaFiltro());
				} else {
					filtroLinhaVO.setIdLinhaFiltro(request.getParameter("idLinha"));
				}

				String nrLinhaFiltro = ConstantesCRM.SVAZIO;
				if (request.getParameter("nrLinhaAG") != null) {
					nrLinhaFiltro = request.getParameter("nrLinhaAG");
				}

				// Lupa Linha -> Aba Serviços (Linha Pre-Paga CDMA/GSM)
				if ((idTipoLinha == 2 || idTipoLinha == 6 /* || idTipoLinha==4 || idTipoLinha==7 */) && "SERVICO".equalsIgnoreCase(request.getParameter("redirect"))) {
					ServicoVO servicosPrePago = consultaServicosFacadeControl.getServico(user, nrLinhaFiltro, ConstantesCRM.SVAZIO, idTipoLinha);
					lupaLinhaForm.setFormServicoVO(servicosPrePago);
					if (!ConstantesCRM.SVAZIO.equals(servicosPrePago.getMsgErro())) {
						lupaLinhaForm.getFormServicoVO().setCodErro(servicosPrePago.getCodErro());
						lupaLinhaForm.getFormServicoVO().setMsgErro(servicosPrePago.getMsgErro());
					}

				} else if ("PERMISSOES".equals(request.getParameter("redirect"))) {
					logLupaLinha.info("TelaInicialController:pesquisarDetalheLinha(" + user + ") >> Inicio Aba Permissoes ");
					ParametrosVO parametrosVO = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS));
					ResultadoLinhaPUPTO pesquisaLinhaPUP = new ResultadoLinhaPUPTO();
					RequestInfoTO requestInfo = getRequestInfoTO(request);
					if (ConstantesCRM.SVAZIO.equals(nrLinhaFiltro)) {
						nrLinhaFiltro = parametrosVO.getNrTelefone();
					}

					ParametroVO parametroVO;
					try {
						parametroVO = telaInicialFacadeControl.getParametro(user, PARAM_WSEP_PUP_CONSULTA);
					} catch(Exception e) {
						logLupaLinha.error("TelaInicialController:pesquisarDetalheLinha(" + user + ") Erro " , e);
						parametroVO = ParametroVO.Factory.newInstance();
					}
					String dsEndPoint = parametroVO.getDsValorParametro();
					logLupaLinha.debug("pesquisarDetalheLinha::endpoint = " + dsEndPoint);

					try {
						URL endPoint = new URL(dsEndPoint);

						Integer cdCanal = new Integer(Integer.toString(ConstantesCRM.COD_CANAL_CRC));
						Integer cdProcedencia = new Integer(Integer.toString(ConstantesCRM.COD_PROCEDENCIA_TELEFONE));

                        ConsultarLinhaPUPServiceHttpBindingStub proxy = new ConsultarLinhaPUPServiceHttpBindingStub(endPoint, new ConsultarLinhaPUPServiceLocator());
                        proxy.setTimeout(20000);
                        pesquisaLinhaPUP = proxy.consultarLinhaPUP(requestInfo, cdCanal, cdProcedencia, null, null, new Integer(nrLinhaFiltro.substring(0, 2)), new Integer(nrLinhaFiltro.substring(2)));

						pesquisaLinhaPUP.getLinhaPUPWSTO().setNrTelefone(new Integer(nrLinhaFiltro.substring(2)));
						pesquisaLinhaPUP.getLinhaPUPWSTO().setCdDDD(new Integer(nrLinhaFiltro.substring(0, 2)));

					} catch (Exception e) {
						logTelaInicial.error("TelaInicialController:pesquisarDetalheLinha (AbaPermissoes)(" + user + ") - [" + e.getMessage() + " - " + e.getStackTrace().toString() + "]", e);
						pesquisaLinhaPUP = new ResultadoLinhaPUPTO();
						pesquisaLinhaPUP.setCdRetorno("01");
						pesquisaLinhaPUP.setStatus(ConstantesCRM.SNOK);
						pesquisaLinhaPUP.setReason("Não foi possível acessar o Web Service.");
					}

					logLupaLinha.info("TelaInicialController:pesquisarDetalheLinha(" + user + ") >> Fim Aba Permissoes");
					lupaLinhaForm.setFormLinhaPUPTO(pesquisaLinhaPUP);

				} else if ("TRAFEGODADOS".equals(request.getParameter("redirect"))) {
					logLupaLinha.info("TelaInicialController:pesquisarDetalheLinha(" + user + ") >> Inicio Aba Tráfego de Dados.");
					ReducaoVelocidadeItem reducaoVelocidadeItem1005 = null;
					ReducaoVelocidadeItem reducaoVelocidadeItem1006 = null;

					getLupaLinhaForm().setErro(ConstantesCRM.SVAZIO);
					getLupaLinhaForm().setReducaoVelocidadeItem1005(reducaoVelocidadeItem1005);
					getLupaLinhaForm().setReducaoVelocidadeItem1006(reducaoVelocidadeItem1006);
					ReducaoVelocidadeVO reducaoVelocidadeVO = null;
					try {
						reducaoVelocidadeVO = servicoFacade.getConsultaVelocidade(user, nrLinhaFiltro);

						int contador = 0;
						for (int i = 0; i < reducaoVelocidadeVO.getReducaoVelocidadeItemArray().length; i++) {
							if ("1005".equals(reducaoVelocidadeVO.getReducaoVelocidadeItemArray(i).getBalanceType()) && !ConstantesCRM.SZERO.equals(reducaoVelocidadeVO.getReducaoVelocidadeItemArray(i).getBytes())) {
								reducaoVelocidadeItem1005 = reducaoVelocidadeVO.getReducaoVelocidadeItemArray(i);
								contador++;
							}
							if ("1006".equals(reducaoVelocidadeVO.getReducaoVelocidadeItemArray(i).getBalanceType()) && !ConstantesCRM.SZERO.equals(reducaoVelocidadeVO.getReducaoVelocidadeItemArray(i).getBytes())) {
								reducaoVelocidadeItem1006 = reducaoVelocidadeVO.getReducaoVelocidadeItemArray(i);
								contador++;
							}
							if (contador == 2) {
								break;
							}
						}
					} catch (Exception e) {
						logTelaInicial.error("TelaInicialController:pesquisarDetalheLinha (TRAFEGODADOS)(" + user + ") - [" + e.getMessage() + " - " + e.getStackTrace().toString() + "]", e);
						getLupaLinhaForm().setErro("No momento esse serviço não está disponível.");

						request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
						return mapping.findForward("TRAFEGODADOS");
					}

					/* Buscar variável controle de apresentaçao do tipo de trafego 1005 */
					ParametroVO parametroVO = telaInicialFacadeControl.getParametro(user, ConstantesCRM.IN_TRAFEGO_DADOS_1005);
					String inMostrarTrafego1005 = (parametroVO != null && parametroVO.getDsValorParametro() != null) ? parametroVO.getDsValorParametro() : "0";
					if (reducaoVelocidadeItem1005 != null || reducaoVelocidadeItem1006 != null) {
						if (reducaoVelocidadeItem1005 != null) {
							String s = DEFAULT_DECIMAL_FORMAT.format(MEGABYTE_1005);
							BigDecimal bd = new BigDecimal(s);
							BigDecimal bytesBD = new BigDecimal(reducaoVelocidadeItem1005.getBytes());
							bytesBD = bytesBD.divide(bd, 0);
							reducaoVelocidadeItem1005.setBytes(bytesBD.toString() + " mb");
							getLupaLinhaForm().setReducaoVelocidadeItem1005(reducaoVelocidadeItem1005);
						}

						if (reducaoVelocidadeItem1006 != null) {
							String s = DEFAULT_DECIMAL_FORMAT.format(MEGABYTE_1006);
							BigDecimal bd = new BigDecimal(s);
							BigDecimal bytesBD = new BigDecimal(reducaoVelocidadeItem1006.getBytes());
							bytesBD = bytesBD.divide(bd, 0);
							reducaoVelocidadeItem1006.setBytes(bytesBD.toString() + " mb");
							getLupaLinhaForm().setReducaoVelocidadeItem1006(reducaoVelocidadeItem1006);
						}
						
						/* Caso inMostrarTrafego1005 = 1 nao apresentar mensagem trafego 1005 */
						logLupaLinha.debug("TelaInicialController:pesquisarDetalheLinha >> inMostrarTrafego1005 " + inMostrarTrafego1005);
						if ("1".equals(inMostrarTrafego1005)) {
							if (reducaoVelocidadeItem1005 != null || reducaoVelocidadeItem1006 != null) {
								reducaoVelocidadeItem1005 = null;
							} else if (reducaoVelocidadeItem1005 != null || reducaoVelocidadeItem1006 == null) {
								getLupaLinhaForm().setErro("No momento esse serviço não está disponível.");
							} else {
								reducaoVelocidadeItem1005 = null;
							}
						}

					} else {
						getLupaLinhaForm().setErro("Não foi identificado uso de dados na sua linha.");
					}
					logLupaLinha.info("TelaInicialController:pesquisarDetalheLinha(" + user + ") >> Fim Aba Tráfego de Dados.");

				} else {
					ParametrosVO parametrosVO = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
					if (filtroLinhaVO.getIdLinhaFiltro() == null || ConstantesCRM.SVAZIO.equals(filtroLinhaVO.getIdLinhaFiltro()) || ConstantesCRM.SZERO.equals(filtroLinhaVO.getIdLinhaFiltro())) {
						filtroLinhaVO.setIdLinhaFiltro(parametrosVO.getIdLinha());
					}

					pesquisaLinhaVO = telaInicialFacadeControl.consultaLinhaVO(filtroLinhaVO, user, nrLinhaFiltro, idTipoLinha);
					if (pesquisaLinhaVO.getDadosLupaLinha().getDsAparelho() == null || ConstantesCRM.SVAZIO.equals(pesquisaLinhaVO.getDadosLupaLinha().getDsAparelho())) {
						pesquisaLinhaVO.getDadosLupaLinha().setDsAparelho(pesquisaLinhaVO.getDadosLupaLinha().getDescricao());
					}

					if (pesquisaLinhaVO.getDadosLupaLinha().getErro() != null && pesquisaLinhaVO.getDadosLupaLinha().getErro().length() > 0) {
						lupaLinhaForm.setErro(ConstantesCRM.STRUE);
					} else {
						lupaLinhaForm.setErro(ConstantesCRM.SFALSE);
					}
					lupaLinhaForm.setFormDadosLupaLinha(pesquisaLinhaVO.getDadosLupaLinha());
					lupaLinhaVO.setDadosLupaLinha(pesquisaLinhaVO.getDadosLupaLinha());
					if (pesquisaLinhaVO.getDadosLupaLinha().getInDivulgaNumero().equals(ConstantesCRM.SZERO)) {
						lupaLinhaForm.getFormDadosLupaLinha().setInDivulgaNumero(ConstantesCRM.SNO);
						lupaLinhaForm.setFormInDivulgaNumeroFiltro(ConstantesCRM.SNO);
					} else {
						lupaLinhaForm.getFormDadosLupaLinha().setInDivulgaNumero(ConstantesCRM.SYES);
						lupaLinhaForm.setFormInDivulgaNumeroFiltro(ConstantesCRM.SYES);
					}
				}
				lupaLinhaForm.setFormNrLinhaFiltro(form.getFormNrLinhaFiltro());
				lupaLinhaForm.setFormNrLinhaFiltroAte(form.getFormNrLinhaFiltroAte());
				lupaLinhaForm.setFormIDStatusLinhaFiltro(form.getFormIDStatusLinhaFiltro());
				lupaLinhaForm.setFormIDTipoLinhaFiltro(form.getFormIDTipoLinhaFiltro());
				lupaLinhaForm.setFormIDLinhaFiltro(form.getFormIDLinhaFiltro());
				lupaLinhaForm.setFormIDTipoLinha(Integer.toString(idTipoLinha));
				lupaLinhaForm.getFormDadosLupaLinha().setDsTecnologia(tecnologia);
				lupaLinhaForm.getFormDadosLupaLinha().setIdLinha(filtroLinhaVO.getIdLinhaFiltro());

				if (ConstantesCRM.DES_TECNOLOGIA_CHIP.equals(tecnologia) && "GERAL".equals(request.getParameter("redirect"))) {
					getLupaLinhaForm().getFormDadosLupaLinha().setMarca(ConstantesCRM.SVAZIO);
					getLupaLinhaForm().getFormDadosLupaLinha().setDsAparelho(ConstantesCRM.SVAZIO);

					ParametroVO parametro = GetParametro.getInstace().getParametroVO(user, PARAMETRO_ENDPOINT_APARELHO);
					String endPoint = parametro.getDsValorParametro();
					logLupaLinha.debug("pesquisarDetalheLinha::endpoint = " + endPoint);
					getLupaLinhaForm().setUrlAparelhoWebService(endPoint);
					
	                String usuario = ConstantesCRM.SVAZIO;
	                String senha = ConstantesCRM.SVAZIO;
	                try {
	                    usuario = br.com.vivo.fo.commons.utils.GetParametro.getInstace().getParametroVO(user, ConstantesCRM.ALSB3SOA_SECURITY_USER).getDsValorParametro();
	                    senha = br.com.vivo.fo.commons.utils.GetParametro.getInstace().getParametroVO(user, ConstantesCRM.ALSB3SOA_SECURITY_PASSWORD).getDsValorParametro();
	                } catch (Exception e) {
	                	logLupaLinha.error("TelaInicialController:pesquisarDetalheLinha(" + user + ")Erro ao buscar usuário e senha :", e);
	                }
					
					AparelhoPortTypeProxy proxy = new AparelhoPortTypeProxy();
					proxy.setEndpoint(endPoint);
					SecurityHeaderHelper header = new SecurityHeaderHelper();
					header.setAddWSSecurity(true);
					header.setAddCabecalhoVivo(true);
					header.setUserTimeout(20 * 1000);
					header.setSecurityUserName(usuario);
					header.setSecurityPassword(senha);
					proxy.setSecurityHeaderHelper(header);
					
					logLupaLinha.info("TelaInicialController:pesquisarDetalheLinha(" + user + ") :" + header.toString());
					
					Produto modeloAparelho = new Produto();
					try {
						ParametrosConsultarModeloAparelho parametrosRequest = new ParametrosConsultarModeloAparelho();
						parametrosRequest.setNrLinha(nrLinhaFiltro);
						modeloAparelho = proxy.consultarModeloAparelho(parametrosRequest);

					} catch(com.indracompany.ws.aparelhoservice.to.ErroInfo errorInfo){
						logLupaLinha.error("TelaInicialController:pesquisarDetalheLinha ErroInfo [" + errorInfo.getCodigo() + "] " + errorInfo.getDescricao());
						throw errorInfo;
					
					} catch(AxisFault axisFaultException) {
						System.out.println("TelaInicialController::pesquisarDetalheLinha AxisFault [" + axisFaultException.getFaultString() + "]");
						throw new cliente.TelaInicial.DetalheFatura.WebService.ErroInfo(0, "Serviço indisponível no momento", axisFaultException.getFaultString());
					
					} catch (Exception ex) {
						System.out.println("TelaInicialController::pesquisarDetalheLinha Exception [" + ex.toString() + "]");
						throw new cliente.TelaInicial.DetalheFatura.WebService.ErroInfo(0, "Serviço indisponível no momento", ex.toString());
					}

					try {
						getLupaLinhaForm().setModeloAparelho(modeloAparelho);
						getLupaLinhaForm().getFormDadosLupaLinha().setMarca(modeloAparelho.getModeloProduto().getFabricante().getNome());
						getLupaLinhaForm().getFormDadosLupaLinha().setDsAparelho(modeloAparelho.getModeloProduto().getNome());
						getLupaLinhaForm().setErro(ConstantesCRM.SFALSE);

						// Registro de palitagem
						String nrProtocoloSessao = request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) != null ? (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) : ConstantesCRM.SVAZIO;

						// Registro de Palitagem
						String nrProtocolo = ClienteUtils.palitagemPorCDPalito(user, getParametrosVO(request), getParametrosVO(request).getIdLinha(), getParametrosVO(request).getNrLinha(), "DM_MODELO_APARELHO", ConstantesCRM.SVAZIO);

						boolean isProtocolo = true;
						try {
							Long.parseLong(nrProtocolo);
						} catch (Exception e) {
							isProtocolo = false;
						}

						if (!nrProtocoloSessao.equals(nrProtocolo) && isProtocolo) {
							request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, nrProtocolo);
							getParametrosVO(request).setNrProtocolo(nrProtocolo);
							request.setAttribute(ConstantesCRM.NRPROTOCOLO, nrProtocolo);
						}

					} catch (Exception e) {
						String msgErro = "Ocorreu um erro ao acessar o serviço de consulta do fabricante e modelo do aparelho. ";
						msgErro += "Caso o problema persista, entre em contato com a Central de Serviços.";

						if (e instanceof TPReplyException) {
							msgErro = ConstantesCRM.SVAZIO;

						} else if (e instanceof com.indracompany.ws.aparelhoservice.to.ErroInfo) {
							com.indracompany.ws.aparelhoservice.to.ErroInfo erroInfo = (com.indracompany.ws.aparelhoservice.to.ErroInfo) e;
							if (ConstantesCRM.STHREE.equals(erroInfo.getCodigo()) || ConstantesCRM.SFIVE.equals(erroInfo.getCodigo())) {
								msgErro = erroInfo.getDescricao();
							}
						}

						if (!ConstantesCRM.SVAZIO.equals(msgErro)) {
							request.setAttribute("erroAPI", msgErro);
						}
					}
				}

				if ("GERAL".equals(request.getParameter("redirect"))) {
					logTelaInicial.debug("TelaInicialController:pesquisarDetalheLinha(" + user + ") >> FINALIZADO -> Pesquisa de Detalhes da Linha: Aba Geral");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("GERAL");

				} else if ("SERVICO".equals(request.getParameter("redirect"))) {
					logTelaInicial.debug("TelaInicialController:pesquisarDetalheLinha(" + user + ") >> FINALIZADO -> Pesquisa de Detalhes da Linha: Aba Serviço");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("SERVICO");

				} else if ("PERMISSOES".equals(request.getParameter("redirect"))) {
					logTelaInicial.debug("TelaInicialController:pesquisarDetalheLinha(" + user + ") >> FINALIZADO -> Pesquisa de Detalhes da Linha: Aba Permissões de Mensagens");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("PERMISSOES");

				} else if ("TRAFEGODADOS".equals(request.getParameter("redirect"))) {
					logTelaInicial.debug("TelaInicialController:pesquisarDetalheLinha(" + user + ") >> FINALIZADO -> Pesquisa de Detalhes da Linha: Aba Tráfego de Dados");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("TRAFEGODADOS");

				} else {
					logTelaInicial.debug("TelaInicialController:pesquisarDetalheLinha(" + user + ") >> FINALIZADO -> Pesquisa de Detalhes da Linha: Lupa Linha");
					// request.setAttribute("first",ConstantesCRM.STRUE);
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(ConstantesCRM.SUCCESS);
				}
			} else {// if (idTipoLinha==1)
				logTelaInicial.debug("TelaInicialController:pesquisarDetalheLinha(" + user + ") >> FINALIZADO -> Pesquisa de Detalhes da Linha: Aba Geral");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("GERAL");
			}

		} catch (com.indracompany.ws.aparelhoservice.to.ErroInfo erroInfo) {
			String msgErro = "Ocorreu um erro ao acessar o serviço de consulta do fabricante e modelo do aparelho. ";
			msgErro += "Caso o problema persista, entre em contato com a Central de Serviços.";
			if (ConstantesCRM.STHREE.equals(erroInfo.getCodigo()) || ConstantesCRM.SFIVE.equals(erroInfo.getCodigo())) {
				msgErro = erroInfo.getDescricao();
			}
			request.setAttribute("erroAPI", msgErro);
			request.setAttribute("erroInfoLog", erroInfo.getDescricao());
			getLupaLinhaForm().setErroAPI(msgErro);

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("GERAL");

		} catch (Exception e) {
			if (e instanceof TPReplyException) {
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("GERAL");

			} else {
				logTelaInicial.error("TelaInicialController:pesquisarDetalheLinha(" + user + ") - [" + e.getMessage() + "]", e);
				FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
				formError.setTarget(ConstantesCRM.FRAMEAPP);
				request.setAttribute(ConstantesCRM.SFORMERROR, formError);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SERROR);
			}
		}
	}

	/*private String fixXMLFault(String xmlArg) {
		if (xmlArg.indexOf("<faultCode>") >= 0) {
			xmlArg = xmlArg.replaceAll("faultCode", "soapenv:faultCode");
		}
		if (xmlArg.indexOf("<faultString>") >= 0) {
			xmlArg = xmlArg.replaceAll("faultString", "soapenv:faultString");
		}
		if (xmlArg.indexOf("<detail>") >= 0) {
			xmlArg = xmlArg.replaceAll("detail", "soapenv:detail");
		}
		return xmlArg;
	}*/

	/**
	 * @jpf:action
	 * @jpf:forward name="historico" path="historicoAparelhos.jsp"
	 * @jpf:forward name="configuracoes" path="configuracoesAparelho.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward modeloAparelho(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		//LupaLinhaForm form = (LupaLinhaForm) formParam;
		String nmOperacao = request.getParameter("operacao") != null ? request.getParameter("operacao") : ConstantesCRM.SVAZIO;
		String cdPalito = ConstantesCRM.SVAZIO;
		String nrLinha = request.getParameter("nrLinha");
		String msgErro = ConstantesCRM.SVAZIO;
		String erroInfoLog = ConstantesCRM.SVAZIO;

		try {
			String endPoint = getLupaLinhaForm().getUrlAparelhoWebService();
			AparelhoPortTypeProxy proxy = new AparelhoPortTypeProxy();
			proxy.setEndpoint(endPoint);
			

			String usuario = ConstantesCRM.SVAZIO;
            String senha = ConstantesCRM.SVAZIO;
            try {
                usuario = br.com.vivo.fo.commons.utils.GetParametro.getInstace().getParametroVO(user, ConstantesCRM.ALSB3SOA_SECURITY_USER).getDsValorParametro();
                senha = br.com.vivo.fo.commons.utils.GetParametro.getInstace().getParametroVO(user, ConstantesCRM.ALSB3SOA_SECURITY_PASSWORD).getDsValorParametro();
            } catch (Exception e) {
            	logLupaLinha.error("TelaInicialController:modeloAparelho(" + user + ")Erro ao buscar usuário e senha :", e);
            }
			
			SecurityHeaderHelper header = new SecurityHeaderHelper();
			header.setAddWSSecurity(false);
			header.setAddCabecalhoVivo(true);
			header.setUserTimeout(20 * 1000);
			header.setSecurityUserName(usuario);
			header.setSecurityPassword(senha);
			
			logTelaInicial.error("TelaInicialController:modeloAparelho(" + user + ")header " + header);
			proxy.setSecurityHeaderHelper(header);

			try {
				if ("historico".equals(nmOperacao)) {
					cdPalito = "DM_HIST_APARELHOS";
					ParametrosConsultarHistoricoAparelhos parametroHistorico = new ParametrosConsultarHistoricoAparelhos();
					parametroHistorico.setNrLinha(nrLinha);
					
					AparelhoLinha[] historicoAparelho = proxy.consultarHistoricoAparelhos(parametroHistorico);
					getLupaLinhaForm().setHistoricoAparelho(historicoAparelho);

				} else if ("configuracoes".equals(nmOperacao)) {
					request.setAttribute("nrLinha", nrLinha);
					try {
						LogAtualizacaoParametrosAparelho log = deviceManager.getDataAtualizacaoParametrosApareho(user, nrLinha);
						if (log != null && log.getDtUltimaAlteracao() != null) {
							request.setAttribute("ultimaAtualizacao", log.getDtUltimaAlteracao());
						}
					} catch (Exception e) {
						logTelaInicial.error("TelaInicialController:modeloAparelho(" + user + ") - [" + e.getMessage() + "] -  deviceManager.getDataAtualizacaoParametrosApareho", e);
					}
					ParametrosConsultarModeloAparelho parametroModeloRequest = new ParametrosConsultarModeloAparelho();
					parametroModeloRequest.setNrLinha(nrLinha);
					Produto modeloAparelho = proxy.consultarModeloAparelho(parametroModeloRequest);
					getLupaLinhaForm().setModeloAparelho(modeloAparelho);

				} else if ("atualizar".equals(nmOperacao)) {
					cdPalito = "DM_ATT_APARELHO";
					ParametrosAtualizarParametrosAparelho parametroAtualizar = new ParametrosAtualizarParametrosAparelho();
					parametroAtualizar.setNrLinha(nrLinha);
					parametroAtualizar.setCodigoUsuarioAlteracao(new BigInteger(user));
					proxy.atualizarParametrosAparelho(parametroAtualizar);
					deviceManager.incluiLogDeviceManager(user, nrLinha);
				}
			} catch(com.indracompany.ws.aparelhoservice.to.ErroInfo errorInfo){
				logTelaInicial.error("TelaInicialController:modeloAparelho ErroInfo [" + errorInfo.getCodigo() + "] " + errorInfo.getDescricao());
				throw errorInfo;
			} catch(AxisFault axisFaultException) {
				logTelaInicial.error("TelaInicialController:modeloAparelho AxisFault [" + axisFaultException.getFaultString() + "]");
				String msgConexao = "";
				if ("historico".equals(nmOperacao)) {
					msgConexao = "O serviço de consulta de histórico de aparelhos não está respondendo. Caso o problema persista, entre em contato com a Central de Serviços.";
					getLupaLinhaForm().setHistoricoAparelho(null);

				} else if ("configuracoes".equals(nmOperacao)) {
					msgConexao = "O serviço de consulta do fabricante e modelo do aparelho não está respondendo. Caso o problema persista, entre em contato com a Central de Serviços.";

				} else if ("atualizar".equals(nmOperacao)) {
					msgConexao = "O serviço de atualização de parâmetros do aparelho não está respondendo. Caso o problema persista, entre em contato com a Central de Serviços.";
				}
				throw new com.indracompany.ws.aparelhoservice.to.ErroInfo("3", msgConexao, Calendar.getInstance(), axisFaultException.getFaultString(),  new BigInteger("0"), axisFaultException.getFaultString());

				
			} catch (Exception e) {
				logTelaInicial.error("TelaInicialController:modeloAparelho Exception "  ,e);
				throw new com.indracompany.ws.aparelhoservice.to.ErroInfo("0", "Serviço indisponível no momento. ", Calendar.getInstance(), e.getMessage(),  new BigInteger("0"), e.getMessage());
			}

			if (!ConstantesCRM.SVAZIO.equals(cdPalito)) {
				// Registro de palitagem
				String nrProtocoloSessao = request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) != null ? (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) : ConstantesCRM.SVAZIO;

				// Registro de Palitagem
				String nrProtocolo = ClienteUtils.palitagemPorCDPalito(user, getParametrosVO(request), getParametrosVO(request).getIdLinha(), getParametrosVO(request).getNrLinha(), cdPalito, ConstantesCRM.SVAZIO);

				boolean isProtocolo = true;
				try {
					Long.parseLong(nrProtocolo);
				} catch (Exception e) {
					isProtocolo = false;
				}

				if (!nrProtocoloSessao.equals(nrProtocolo) && isProtocolo) {
					request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, nrProtocolo);
					getParametrosVO(request).setNrProtocolo(nrProtocolo);
					request.setAttribute(ConstantesCRM.NRPROTOCOLO, nrProtocolo);
				}
			}

		} catch (com.indracompany.ws.aparelhoservice.to.ErroInfo erroInfo) {
			erroInfoLog = erroInfo.getFaultString();
			if (ConstantesCRM.STHREE.equals(erroInfo.getCodigo()) || ConstantesCRM.SFIVE.equals(erroInfo.getCodigo())) {
				msgErro = erroInfo.getDescricao();

			} else {
				if ("configuracoes".equals(nmOperacao) || "historico".equals(nmOperacao)) {
					msgErro = "Ocorreu um erro ao acessar o serviço de consulta do fabricante e modelo do aparelho. ";
					msgErro += "Caso o problema persista, entre em contato com a Central de Serviços.";

				} else if ("atualizar".equals(nmOperacao)) {
					msgErro = "Ocorreu um erro ao acessar o serviço de atualização de parâmetros do aparelho.";
					msgErro += "Caso o problema persista, entre em contato com a Central de Serviços.";
				}
			}

			request.setAttribute("erroAPI", msgErro);
			request.setAttribute("erroInfoLog", erroInfoLog);
			getLupaLinhaForm().setErroAPI(msgErro);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(nmOperacao);

		} catch (Exception e) {
			request.setAttribute("erroGenerico", e.getMessage());

		} finally {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			if ("atualizar".equals(nmOperacao)) {
				StringBuffer out = new StringBuffer();
				out.append("<retorno>");
				out.append("<erro>").append(ConstantesCRM.SVAZIO.equals(msgErro) ? "false" : "true").append("</erro>");
				out.append("<msgErro>").append(ConstantesCRM.SVAZIO.equals(msgErro) ? ConstantesCRM.SVAZIO : msgErro).append("</msgErro>");
				out.append("<erroInfoLog>").append(ConstantesCRM.SVAZIO.equals(erroInfoLog) ? ConstantesCRM.SVAZIO : erroInfoLog).append("</erroInfoLog>");
				out.append("</retorno>");

				response.setContentType(ConstantesCRM.SContentType);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
				bufferedOutputStream.write(out.toString().getBytes(ConstantesCRM.SISO));
				bufferedOutputStream.flush();
				bufferedOutputStream.close();
				return null;
			}
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(nmOperacao);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaPermissoesMensagens.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward consultarLinhaPUP(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//LupaLinhaForm form = (LupaLinhaForm) formParam;
		logLupaLinha.info("TelaInicialController:pesquisarDetalheLinha(" + user + ") >> Inicio Aba Permissoes ");
		ParametrosVO parametrosVO = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS));

		if (parametrosVO.getIdLinha() != null && !parametrosVO.getIdLinha().equals(ConstantesCRM.SVAZIO) && request.getParameter("inAjax") == null) {
			getLupaLinhaForm().getFormLinhaPUPTO().setStatus(ConstantesCRM.SNOK);
			getLupaLinhaForm().getFormLinhaPUPTO().setCdRetorno("02");
			getLupaLinhaForm().setErro("Para cadastrar esta opção para o cliente, é necessário acessar através da lupa linha.");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}

		String nrLinha = parametrosVO.getNrLinha();
		ResultadoLinhaPUPTO pesquisaLinhaPUP = null;
		RequestInfoTO requestInfo = getRequestInfoTO(request);

		ParametroVO parametroVO;
		try {
			parametroVO = telaInicialFacadeControl.getParametro(user, PARAM_WSEP_PUP_CONSULTA);
		} catch(Exception e) {
			logLupaLinha.error("TelaInicialController:pesquisarDetalheLinha(" + user + ") Erro " , e);
			parametroVO = ParametroVO.Factory.newInstance();
		}
		String dsEndPoint = parametroVO.getDsValorParametro();
		logLupaLinha.debug("endpoint = " + dsEndPoint);

		try {
			URL endPoint = new URL(dsEndPoint);

			Integer cdCanal = new Integer(Integer.toString(ConstantesCRM.COD_CANAL_CRC));
			Integer cdProcedencia = new Integer(Integer.toString(ConstantesCRM.COD_PROCEDENCIA_TELEFONE));

			ConsultarLinhaPUPServiceHttpBindingStub proxy = new ConsultarLinhaPUPServiceHttpBindingStub(endPoint, new ConsultarLinhaPUPServiceLocator());
			proxy.setTimeout(20000);
			pesquisaLinhaPUP = proxy.consultarLinhaPUP(requestInfo, cdCanal, cdProcedencia, null, null, new Integer(nrLinha.substring(0, 2)), new Integer(nrLinha.substring(2)));

			if (pesquisaLinhaPUP == null) {
				pesquisaLinhaPUP = new ResultadoLinhaPUPTO();
				pesquisaLinhaPUP.setLinhaPUPWSTO(new LinhaPUPWSTO());
			}
			pesquisaLinhaPUP.getLinhaPUPWSTO().setNrTelefone(new Integer(nrLinha.substring(2)));
			pesquisaLinhaPUP.getLinhaPUPWSTO().setCdDDD(new Integer(nrLinha.substring(0, 2)));

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:consultarLinhaPUP(" + user + ") - [" + e.getMessage() + " - " + e.getStackTrace().toString() + "]", e);
			pesquisaLinhaPUP = new ResultadoLinhaPUPTO();
			pesquisaLinhaPUP.setCdRetorno("01");
			pesquisaLinhaPUP.setStatus(ConstantesCRM.SNOK);
			pesquisaLinhaPUP.setReason("Não foi possível acessar o Web Service.");
		}

		logLupaLinha.info("TelaInicialController:pesquisarDetalheLinha(" + user + ") >> Fim Aba Permissoes");
		getLupaLinhaForm().setFormLinhaPUPTO(pesquisaLinhaPUP);
		request.setAttribute("naoCliente", ConstantesCRM.STRUE);

		if (ConstantesCRM.STRUE.equalsIgnoreCase(request.getParameter("inAjax"))) {
			ParametroVO parametro = ParametroVO.Factory.newInstance();
			try {
				parametro = GetParametro.getInstace().getParametroVO(user, "EXIBIR_ALERTA_PUP_INICIO");
			} catch (Exception e) {
			}
			boolean apresentaMensagens = ConstantesCRM.SONE.equals(parametro.getDsValorParametro()) || "SIM".equalsIgnoreCase(parametro.getDsValorParametro()) ? true : false;

			response.setContentType(ConstantesCRM.SContentType);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			bufferedOutputStream.write(pesquisaLinhaPUP.toXMLString(apresentaMensagens).getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();

			return null;

		} else {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaPermissoesMensagens.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward gravarLinhaPUP(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		LupaLinhaForm form = (LupaLinhaForm) formParam;
		user = ConstantesCRM.getCurrentUser(request.getSession());
		String chave = "PUP_CONTATO_" + form.getCdPermissao();
		String endChave = ConstantesCRM.SVAZIO;

		Integer inSMSProtocolos = null;
		Integer inSMSPromocoes = null;
		Integer inSMSProdutos = null;
		Integer inSMSParceiros = null;
		Integer inIVRPromocoes = null;
		Integer inIVRProdutos = null;
		Integer inIVRParceiros = null;
		Integer inTelmktMsgVoz = null;

		lupaLinhaForm.getFormLinhaPUPTO().setStatus(ConstantesCRM.SVAZIO);
		lupaLinhaForm.getFormLinhaPUPTO().setReason(ConstantesCRM.SVAZIO);

		ResultadoLinhaPUPTO tratamento = new ResultadoLinhaPUPTO(lupaLinhaForm.getFormLinhaPUPTO().getLinhaPUPWSTO());

		try {
			String cdParametro = "PUP_PRAZO_EXPIRACAO";
			if ("BLKTEAT".equals(form.getCdPermissao())) {
				cdParametro = "PUP_PRAZO_EXPIRACAO_TLMKT";
			}
			ParametroVO parametro = telaInicialFacadeControl.getParametro(user, cdParametro);
			lupaLinhaForm.setQtdeDiasExpiracaoPUP(Integer.parseInt(parametro.getDsValorParametro()));
		} catch (Exception e) {
		}

		String DATE_FORMAT = "dd/MM/yyyy";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		Calendar c1 = Calendar.getInstance();
		Date d1 = new Date();
		c1.add(Calendar.DATE, lupaLinhaForm.getQtdeDiasExpiracaoPUP());
		String dtExpiracao = sdf.format(c1.getTime());

		if (tratamento.getLinhaPUPWSTO().getDtInclusao() == null) {
			tratamento.getLinhaPUPWSTO().setDtInclusao(sdf.format(d1));
		}
		tratamento.getLinhaPUPWSTO().setDtUltimaAlteracao(sdf.format(d1));

		// Data de expiração só existe para não aceitação de permissão
		Integer aceitacao = new Integer(form.getInAceitacaoPermissao());
		if (aceitacao.intValue() == 1) {
			dtExpiracao = ConstantesCRM.SVAZIO;
			endChave = "_ACEITO";
		} else {
			endChave = "_NAO_ACEITO";
		}
		chave += endChave;

		// [OS 1351] Captura de Optin (Solicitada remoção de data de expiração)
		dtExpiracao = ConstantesCRM.SVAZIO;

		if ("SMSPROT".equals(form.getCdPermissao())) {
			inSMSProtocolos = new Integer(form.getInAceitacaoPermissao());
			tratamento.getLinhaPUPWSTO().setInSMSProtocolos(aceitacao);
			tratamento.getLinhaPUPWSTO().setDtExpSMSProtocolos(dtExpiracao);

		} else if ("SMSPROM".equals(form.getCdPermissao())) {
			inSMSPromocoes = new Integer(form.getInAceitacaoPermissao());
			tratamento.getLinhaPUPWSTO().setInSMSPromocoes(aceitacao);
			tratamento.getLinhaPUPWSTO().setDtExpSMSPromocoes(dtExpiracao);

		} else if ("SMSPROD".equals(form.getCdPermissao())) {
			inSMSProdutos = new Integer(form.getInAceitacaoPermissao());
			tratamento.getLinhaPUPWSTO().setInSMSProdutos(aceitacao);
			tratamento.getLinhaPUPWSTO().setDtExpSMSProdutos(dtExpiracao);

		} else if ("SMSPARC".equals(form.getCdPermissao())) {
			inSMSParceiros = new Integer(form.getInAceitacaoPermissao());
			tratamento.getLinhaPUPWSTO().setInSMSParceiros(aceitacao);
			tratamento.getLinhaPUPWSTO().setDtExpSMSParceiros(dtExpiracao);

		} else if ("IVRPROM".equals(form.getCdPermissao())) {
			inIVRPromocoes = new Integer(form.getInAceitacaoPermissao());
			tratamento.getLinhaPUPWSTO().setInIVRProdutos(aceitacao);
			tratamento.getLinhaPUPWSTO().setDtExpIVRProdutos(dtExpiracao);

		} else if ("IVRPROD".equals(form.getCdPermissao())) {
			inIVRProdutos = new Integer(form.getInAceitacaoPermissao());
			tratamento.getLinhaPUPWSTO().setInIVRProdutos(aceitacao);
			tratamento.getLinhaPUPWSTO().setDtExpIVRProdutos(dtExpiracao);

		} else if ("IVRPARC".equals(form.getCdPermissao())) {
			inIVRParceiros = new Integer(form.getInAceitacaoPermissao());
			tratamento.getLinhaPUPWSTO().setInIVRParceiros(aceitacao);
			tratamento.getLinhaPUPWSTO().setDtExpIVRParceiros(dtExpiracao);

		} else if ("BLKTEAT".equals(form.getCdPermissao())) {
			inTelmktMsgVoz = new Integer(form.getInAceitacaoPermissao());
			tratamento.getLinhaPUPWSTO().setInTelmktMsgVoz(aceitacao);
			tratamento.getLinhaPUPWSTO().setDtExpTelmktMsgVoz(dtExpiracao);
		}

		//ParametrosVO parametrosVO = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS));
		ResultadoLinhaPUPTO pesquisaLinhaPUP = new ResultadoLinhaPUPTO();
		RequestInfoTO requestInfo = getRequestInfoTO(request);

		long[] nrMIN = new long[1];
		nrMIN[0] = Long.valueOf(lupaLinhaForm.getFormLinhaPUPTO().getLinhaPUPWSTO().getCdDDD().toString() + lupaLinhaForm.getFormLinhaPUPTO().getLinhaPUPWSTO().getNrTelefone().toString());

		/*
		 * Possíveis valores para cdOperacao. No Front Office apenas a operação 0 é utilizada. As outras operações são
		 * utilizadas por outras APIs que acessam o Web Service. 0: Manutenção ou novo cadastro 1: Exclusão de
		 * Manutenção/Troca de Titularidade 2: Exclusão/Cancelamento de Linha
		 */
		Integer cdOperacao = new Integer(0);
		
		ParametroVO parametroVO;
		try {
			parametroVO = telaInicialFacadeControl.getParametro(user, PARAM_WSEP_PUP_GRAVACAO);
		} catch(Exception e) {
			logLupaLinha.error("TelaInicialController:pesquisarDetalheLinha(" + user + ") Erro " , e);
			parametroVO = ParametroVO.Factory.newInstance();
		}
		String dsEndPoint = parametroVO.getDsValorParametro();
		logLupaLinha.debug("endpoint = " + dsEndPoint);

		try {
			
			
			GravarLinhaPUPServicePortTypeProxy gravarPUPWSControl = new GravarLinhaPUPServicePortTypeProxy();
			gravarPUPWSControl.setEndpoint(dsEndPoint);
			gravarPUPWSControl.setUserTimeout(20000);

			//URL endPoint = new URL(dsEndPoint);
			// gravarPUPWSControl.setEndPoint(endPoint);

			Integer cdCanal = new Integer(Integer.toString(ConstantesCRM.COD_CANAL_CRC));
			Integer cdProcedencia = new Integer(Integer.toString(ConstantesCRM.COD_PROCEDENCIA_TELEFONE));

			// pesquisaLinhaPUP = gravarPUPWSControl.gravarLinhaPUP(requestInfo, cdCanal, cdProcedencia, null, null,
			// nrMIN, cdOperacao, inSMSProtocolos, inSMSPromocoes, inSMSProdutos, inSMSParceiros, inIVRPromocoes,
			// inIVRProdutos, inIVRParceiros, inTelmktMsgVoz);
			pesquisaLinhaPUP = gravarPUPWSControl.gravarLinhaPUP(requestInfo, cdCanal,
					cdProcedencia, null, null, nrMIN, cdOperacao,
					inSMSProtocolos, inSMSPromocoes, inSMSProdutos,
					inSMSParceiros, inIVRPromocoes, inIVRProdutos,
					inIVRParceiros, inTelmktMsgVoz);

			pesquisaLinhaPUP.setReason(ConstantesCRM.SVAZIO);

			if (ConstantesCRM.SOK.equals(pesquisaLinhaPUP.getStatus())) {
				pesquisaLinhaPUP.setLinhaPUPWSTO(tratamento.getLinhaPUPWSTO());

				String nrProtocoloSessao = request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) != null ? (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) : ConstantesCRM.SVAZIO;

				// Registro de Palitagem
				String nrProtocolo = ClienteUtils.palitagemPorCDPalito(user, getParametrosVO(request), getParametrosVO(request).getIdLinha(), getParametrosVO(request).getNrLinha(), GetParametro.getInstace().getParametroVO(user, chave).getDsValorParametro(), ConstantesCRM.SVAZIO);

				boolean isProtocolo = true;
				try {
					Long.parseLong(nrProtocolo);
				} catch (Exception e) {
					isProtocolo = false;
				}

				if (!nrProtocoloSessao.equals(nrProtocolo) && isProtocolo) {
					request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, nrProtocolo);
					getParametrosVO(request).setNrProtocolo(nrProtocolo);
					request.setAttribute(ConstantesCRM.NRPROTOCOLO, nrProtocolo);
				}
				request.setAttribute("msgSucesso", "Cadastro realizado com sucesso!");

			} else {
				pesquisaLinhaPUP.setStatus(ConstantesCRM.SOK);
				lupaLinhaForm.getFormLinhaPUPTO().setStatus(pesquisaLinhaPUP.getStatus());
				lupaLinhaForm.getFormLinhaPUPTO().setReason(pesquisaLinhaPUP.getReason());
				pesquisaLinhaPUP = lupaLinhaForm.getFormLinhaPUPTO();
			}

		} catch (Exception e) {
			pesquisaLinhaPUP = lupaLinhaForm.getFormLinhaPUPTO();
			pesquisaLinhaPUP.setCdRetorno("02");
			pesquisaLinhaPUP.setStatus(ConstantesCRM.SOK);
			pesquisaLinhaPUP.setReason("Não foi possível acessar o Web Service.");
		}

		getLupaLinhaForm().setFormLinhaPUPTO(pesquisaLinhaPUP);
		request.setAttribute("naoCliente", request.getParameter("naoCliente"));
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/*private String getSoapHeader() {
		StringBuffer stringHeader = new StringBuffer("<?xml version=\"1.0\"?>").append("<SOAP-ENV:Header xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">").append("	<m:cabecalhoVivo xmlns:m=\"http://www.vivo.com.br/MC/Geral\">").append("		<m:loginUsuario>Vivo360</m:loginUsuario>").append("		<m:canalAtendimento>1</m:canalAtendimento>").append("		<m:codigoSessao>1</m:codigoSessao>").append("		<m:nomeAplicacao>Vivo360</m:nomeAplicacao>")
		.append("		<m:enderecoIP>0.0.0.0</m:enderecoIP>").append("		<m:codigoFuncionalidade/>").append("		</m:cabecalhoVivo>").append("</SOAP-ENV:Header>");
		return stringHeader.toString();
	}*/

	private RequestInfoTO getRequestInfoTO(HttpServletRequest request) {
		//ParametrosVO parametrosVO = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS));
		String headerIP = request.getHeader("X-Forwarded-For");
		String ip = request.getRemoteAddr();
		InetAddress ipAddr;
		try {
			if (headerIP != null) {
				ipAddr = InetAddress.getByName(headerIP);
				ip = ipAddr.getHostAddress();
			} else {
				ipAddr = InetAddress.getByName(ip);
				ip = ipAddr.getHostAddress();
			}
		} catch (Exception exc) {
		}

		//ResultadoLinhaPUPTO pesquisaLinhaPUP = new ResultadoLinhaPUPTO();

		RequestInfoTO requestInfo = new RequestInfoTO();
		requestInfo.setIp(ip);

		String nmUsuario = (String) request.getSession().getAttribute(ConstantesCRM.USUARIO_NOME);
		// nmUsuario = Normalizer.decompose(nmUsuario, false, 0).replaceAll("\\p{InCombiningDiacriticalMarks}+", ConstantesCRM.SVAZIO);
		nmUsuario = Normalizer.normalize(nmUsuario, Form.NFC, 0).replaceAll("\\p{InCombiningDiacriticalMarks}+", ConstantesCRM.SVAZIO);

		requestInfo.setUserName(nmUsuario);
		requestInfo.setCallId(ConstantesCRM.SONE);
		requestInfo.setClientTarget(new Integer(ConstantesCRM.SONE));
		requestInfo.setUserId(new Long(ConstantesCRM.getCurrentUser(request.getSession())));
		requestInfo.setSystemModule("VIVONET");

		return requestInfo;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="llaHistAparelho.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward lupaLinhaAbaHistAparelho(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			//LupaLinhaAbasGSMForm form = (LupaLinhaAbasGSMForm) formParam;
			logTelaInicial.debug("TelaInicialController:lupaLinhaAbaHistAparelho(" + user + ") >> INICIALIZADO -> Pesquisa de Historico de Aparelhos em Lupa Linha GSM");
			user = ConstantesCRM.getCurrentUser(request.getSession());
			String nrLinha = request.getParameter("nrLinhaAG");

			String headerIP = request.getHeader("X-Forwarded-For");
			if (headerIP == null) {
				headerIP = request.getRemoteAddr();
			}

			if (nrLinha != null && !ConstantesCRM.SVAZIO.equals(nrLinha)) {
				// LupaLinhaAbasGSMVO lupaLinhaAbasGSMVO =
				// telaInicialFacadeControl.getLupaLinhaAbasGSM(user,"2",idLinha,nrLinha,headerIP,null);
				// getLupaLinhaAbasGSMForm().setLupaLinhaAbasGSMVO(lupaLinhaAbasGSMVO);
			}

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:lupaLinhaAbaHistAparelho(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="llaParametros.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward lupaLinhaAbaParametros(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			//LupaLinhaAbasGSMForm form = (LupaLinhaAbasGSMForm) formParam;
			logTelaInicial.debug("TelaInicialController:lupaLinhaAbaParametros(" + user + ") >> INICIALIZADO -> Pesquisa de Parametros de Aparelhos em Lupa Linha GSM");
			user = ConstantesCRM.getCurrentUser(request.getSession());
			String nrLinha = request.getParameter("nrLinhaAG");
			String idLinha = request.getParameter("idLinha");

			String headerIP = request.getHeader("X-Forwarded-For");
			if (headerIP == null) {
				headerIP = request.getRemoteAddr();
			}

			if (nrLinha != null && !ConstantesCRM.SVAZIO.equals(nrLinha)) {
				// LupaLinhaAbasGSMVO lupaLinhaAbasGSMVO =
				// telaInicialFacadeControl.getLupaLinhaAbasGSM(user,"1",idLinha,nrLinha,headerIP,null);
				// getLupaLinhaAbasGSMForm().setLupaLinhaAbasGSMVO(lupaLinhaAbasGSMVO);
				getLupaLinhaAbasGSMForm().setNrLinha(nrLinha);
				getLupaLinhaAbasGSMForm().setIdLinha(idLinha);
			}

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:lupaLinhaAbaParametros(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="lupaLinhaAbaParametros.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward lupaLinhaAbaParametrosAtualiza(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			LupaLinhaAbasGSMForm form = (LupaLinhaAbasGSMForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			String nmPath = "VIVO/SERVIÇOS/SERVIÇOS DE VALOR AGREGADO/ATUALIZAÇÃO DE PARAMÊTROS";
			String dsObservacao = ConstantesCRM.SVAZIO;

			String nrLinha = request.getParameter("nrLinhaAG");
			if (nrLinha == null) {
				nrLinha = form.getNrLinha();
			}
			String idLinha = request.getParameter("idLinha");
			if (idLinha == null) {
				idLinha = form.getIdLinha();
			}
			String nmParam = request.getParameter("nmParam");
			if (nmParam == null) {
				dsObservacao = "Atualização de Todos os parâmetros";
				nmParam = ConstantesCRM.SVAZIO;
			} else {
				dsObservacao = "Atualizado o Parâmetro " + nmParam;
			}

			String headerIP = request.getHeader("X-Forwarded-For");
			if (headerIP == null) {
				headerIP = request.getRemoteAddr();
			}

			ClienteUtils.registrarPalitagem(user, getParametrosVO(request), idLinha, nrLinha, nmPath, dsObservacao);

			if (nrLinha != null && !ConstantesCRM.SVAZIO.equals(nrLinha)) {
				/*
				 * LupaLinhaAbasGSMVO lupaLinhaAbasGSMVO = telaInicialFacadeControl.getLupaLinhaAbasGSM(user,"3",idLinha,nrLinha,headerIP,nmParam);
				 * if("0".equals(lupaLinhaAbasGSMVO.getCodErro())){ 
				 * //lupaLinhaAbasGSMVO = telaInicialFacadeControl.getLupaLinhaAbasGSM(user,"1",idLinha,nrLinha,headerIP,null);
				 * lupaLinhaAbasGSMVO.setMsgErro("Requisição de atualização de parâmetros efetuada, a atualização será"); }
				 * getLupaLinhaAbasGSMForm().setLupaLinhaAbasGSMVO(lupaLinhaAbasGSMVO);
				 */
			}

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:lupaLinhaAbaParametros(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="DetalheLinha.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward personalizaChip(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			//LupaLinhaForm form = (LupaLinhaForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			String idTipoLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha();
			String idLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdLinha();
			String nrLinhaAtual = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();

			String parametro_autenticador = "HOST_CONEXAO_INTEGRADOR_VIVOCHIP";
			String parametro_url_destino = "HOST_APLICACAO_SELF_CARE";
			String parametro_api_login = "HOST_APLICACAO_SELF_CARE_LOGIN";
			String parametro_api_senha = "HOST_APLICACAO_SELF_CARE_SENHA";

			String parametro_plataforma = (ConstantesCRM.SONE.equals(idTipoLinha) || ConstantesCRM.SFIVE.equals(idTipoLinha)) ? ConstantesCRM.SONE : ConstantesCRM.STWO;

			ParametroVO parametroVO = telaInicialFacadeControl.getParametro(user, parametro_autenticador);
			String urlAutenticador = parametroVO.getDsValorParametro();

			parametroVO = telaInicialFacadeControl.getParametro(user, parametro_url_destino);
			String urlDestino = parametroVO.getDsValorParametro();

			parametroVO = telaInicialFacadeControl.getParametro(user, parametro_api_login);
			String apiLogin = parametroVO.getDsValorParametro();

			parametroVO = telaInicialFacadeControl.getParametro(user, parametro_api_senha);
			String apiSenha = parametroVO.getDsValorParametro();

			IntegradorPortais integrador = new IntegradorPortais();
			integrador.setApiLogin(apiLogin);
			integrador.setApiSenha(apiSenha);
			integrador.setPlataform(parametro_plataforma);
			integrador.setSubscriber(nrLinhaAtual);
			integrador.setURL(urlAutenticador);

			String token = integrador.getToken();
			urlDestino += token;

			ClienteUtils.registrarPalitagem(user, getParametrosVO(request), idLinha, nrLinhaAtual, PATH_SIMBROWSING, ConstantesCRM.SVAZIO);
			ActionForward forward = mapping.findForward(new URL(urlDestino).toString());

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return forward;

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:lupaLinhaAbaParametros(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaServico.jsp"
	 * @jpf:forward name="prePago" path="pesquisarHistoricoAtendimento.do"
	 * @jpf:forward name="erro" path="erroAtlys.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadAbaServico(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logTelaInicial.debug("TelaInicialController:loadAbaServico(" + user + ") >> INICIALIZADO -> Aba Serviço");
			String idContaSO;
			String nrLinha;
			int idTipoLinha = Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());
			// Checa se é pré ou pós
			if (((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha().equalsIgnoreCase(ConstantesCRM.SONE)) {
				// Pós.
				idContaSO = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdContaSistemaOrigem();
				nrLinha = ConstantesCRM.SVAZIO;
			} else {
				// Pré
				idContaSO = ConstantesCRM.SVAZIO;
				nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
			}
			String destino = ConstantesCRM.SUCCESS;
			// Busca os dados de serviços.
			try {
				logTelaInicial.debug("TelaInicialController:loadAbaServico(" + user + ") >> CAPTURADO -> idContaSO: " + idContaSO);
				logTelaInicial.debug("TelaInicialController:loadAbaServico(" + user + ") >> CAPTURADO -> nrLinhaAba: " + nrLinha);
				logTelaInicial.debug("TelaInicialController:loadAbaServico(" + user + ") >> CAPTURADO -> idTipoLinha: " + idTipoLinha);
				servicos = telaInicialFacadeControl.getAbaServicos(user, idContaSO, ConstantesCRM.SVAZIO, nrLinha, idTipoLinha);
				abaServ = new AbaServico();
				abaServ.setFiltroServicos(AbaServicosFiltroVO.Factory.newInstance());
			} catch (Exception e) {
				logTelaInicial.error("TelaInicialController:loadAbaServico(" + user + ") - [" + e.getMessage() + "]", e);
				destino = "erro";
			}
			logTelaInicial.debug("TelaInicialController:loadAbaServico(" + user + ") >> FINALIZADO -> Aba Serviço");

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:loadAbaServico(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaDadosChip.jsp"
	 * @jpf:forward name="erro" path="erroAtlys.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward getDadosChip(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logTelaInicial.debug("TelaInicialController:getDadosChip(" + user + ") >> INICIALIZADO");
			if (request.getParameter("nrICCID") != null) {
				response.setContentType(ConstantesCRM.SContentType);
				BufferedOutputStream saida = new BufferedOutputStream(response.getOutputStream());
				StringBuffer xmlPUK = new StringBuffer();
				xmlPUK.append("<DadosChipVO>");
				xmlPUK.append("<nrPUK1>").append(abaDadosChip.getAbaDadosChip().getNrPUK1()).append("</nrPUK1>");
				xmlPUK.append("<nrPUK2>").append(abaDadosChip.getAbaDadosChip().getNrPUK2()).append("</nrPUK2>");
				xmlPUK.append("</DadosChipVO>");
				saida.write(xmlPUK.toString().getBytes(ConstantesCRM.SISO));
				saida.flush();
				saida.close();
				logTelaInicial.debug("TelaInicialController:getDadosChip(" + user + ") >> FINALIZADO ");

				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return null;

			} else {
				String destino = ConstantesCRM.SUCCESS;
				abaDadosChip = new AbaDadosChip();
				DadosChipVO dadosChipVO = DadosChipVODocument.DadosChipVO.Factory.newInstance();

				// RICARDO INICIO
				String headerIP = request.getHeader("X-Forwarded-For");
				System.out.println("HEADER_IP from X-Forwarded-For = " + headerIP);
				String ip = request.getRemoteAddr();
				System.out.println("IP from RemoteAddr = " + ip);
				InetAddress ipAddr;
				try {
					if (headerIP != null) {
						ipAddr = InetAddress.getByName(headerIP);
						ip = ipAddr.getHostAddress();
						System.out.println("*IP from InetAddress.HostAddress = " + ip);
					} else {
						ipAddr = InetAddress.getByName(ip);
						ip = ipAddr.getHostAddress();
						System.out.println("**IP from InetAddress.HostAddress = " + ip);
					}
				} catch (Exception exc) {
				}
				// Retirar COmentario
				dadosChipVO.setNrIP(ip);
				// RICARDO FIM
				// dadosChipVO.setNrIP(request.getHeader("X-Forwarded-For"));
				dadosChipVO.setNrLinha(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha());
				dadosChipVO.setIdLinha(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdLinha());
				// Retrieve GSM Chip details (ICCID number and ICCID Status) and PUK1 PUK2 numbers
				DadosChipVO dadosChipRetorno = DadosChipVO.Factory.newInstance();
				dadosChipRetorno = telaInicialFacadeControl.getDadosChip(user, dadosChipVO);
				abaDadosChip.setAbaDadosChip(dadosChipRetorno);
				logTelaInicial.debug("TelaInicialController:getDadosChip(" + user + ") >> FINALIZADO ");

				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(destino);
			}
		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:filtroAbaServico(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaIMEI.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward showAbaIMEI(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			//AbaDesbloqueioAparelho form = (AbaDesbloqueioAparelho) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logTelaInicial.debug("TelaInicialController:showAbaIMEI(" + user + ") >> INICIALIZADO");

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:showAbaIMEI(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaDesbloqueio.jsp"
	 * @jpf:forward name="erro" path="erroAtlys.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward desbloqueioAparelhoGsm(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			//AbaDesbloqueioAparelho form = (AbaDesbloqueioAparelho) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logTelaInicial.debug("TelaInicialController:desbloqueioAparelhoGsm(" + user + ") >> INICIALIZADO");
			if (request.getParameter("imei") != null) {
				ParametrosVO param = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
				getDesbloqueioImei().getDesbloqueioGsmVO().setNrIMEI(request.getParameter("imei"));

				// RICARDO INICIO
				String headerIP = request.getHeader("X-Forwarded-For");
				String ip = request.getRemoteAddr();
				InetAddress ipAddr;
				try {
					if (headerIP != null) {
						ipAddr = InetAddress.getByName(headerIP);
						ip = ipAddr.getHostAddress();
					} else {
						ipAddr = InetAddress.getByName(ip);
						ip = ipAddr.getHostAddress();
					}
				} catch (Exception exc) {
				}
				// RICARDO FIM
				getDesbloqueioImei().getDesbloqueioGsmVO().setNrIP(ip);
				// RETIRAR COMENTARIO
				// getDesbloqueioImei().getDesbloqueioGsmVO().setNrIP(request.getHeader("X-Forwarded-For"));
				getDesbloqueioImei().getDesbloqueioGsmVO().setIdTipoRelacionamento(param.getIdTipoRelacionamento());
				getDesbloqueioImei().getDesbloqueioGsmVO().setIdGrupoAbertura(param.getIdGrupo());
				getDesbloqueioImei().getDesbloqueioGsmVO().setCdAreaRegistro(param.getNrCodArea());
				String olinha = ConstantesCRM.SVAZIO;
				if (param.getNrLinha() != null) {
					if (!ConstantesCRM.SVAZIO.equals(param.getNrLinha())) {
						olinha = param.getNrLinha().substring(2);
					}
				}
				getDesbloqueioImei().getDesbloqueioGsmVO().setNrLinha(olinha);
				getDesbloqueioImei().getDesbloqueioGsmVO().setIdPessoa("2".equals(param.getIdTipoRelacionamento()) ? param.getIdPessoaCliente() : param.getIdPessoaUsuario());
				DesbloqueioGsmVO dadosVORetorno = telaInicialFacadeControl.getSimlockAparelho(user, getDesbloqueioImei().getDesbloqueioGsmVO());
				getDesbloqueioImei().setDesbloqueioGsmVO(dadosVORetorno);
				logTelaInicial.debug("TelaInicialController:desbloqueioAparelhoGsm(" + user + ") >> FINALIZADO");

				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);

			} else {
				desbloqueioImei = new AbaDesbloqueioAparelho();
				DesbloqueioGsmVO dadosVO = DesbloqueioGsmVO.Factory.newInstance();
				desbloqueioImei.setDesbloqueioGsmVO(dadosVO);
				logTelaInicial.debug("TelaInicialController:desbloqueioAparelhoGsm(" + user + ") >> FINALIZADO");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}
		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:desbloqueioAparelhoGsm(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaDesbloqueioIphone.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward pesquisarIphone(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		//AbaDesbloqueioAparelho form = (AbaDesbloqueioAparelho) formParam;
		StringBuffer xmlDados = new StringBuffer(ConstantesCRM.SVAZIO);
		user = ConstantesCRM.getCurrentUser(request.getSession());
		String nrSerial = request.getParameter("nrSerial");
		AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
		String msgError = "Serviço indisponível no momento";
		ConfirmarDesbloqueioIPhoneResponse resposta = null;

		try {
			if (nrSerial == null || ConstantesCRM.SVAZIO.equals(nrSerial)) {
				logTelaInicial.error("Número Serial não informado!");
				throw new Exception("Favor informar o Serial");
			}
			ParametroVO parametro = GetParametro.getInstace().getParametroVO(user, PARAMETRO_ENDPOINT_UNLOCKIPHONE);
			logTelaInicial.debug("Chave para busca de parametro Desbloqueio Iphone: " + parametro.getCdParametro());
			logTelaInicial.debug("End Point para Desbloqueio Iphone: " + parametro.getDsValorParametro());

			DesbloqueioIPhonePortTypeProxy proxy = new DesbloqueioIPhonePortTypeProxy();
			proxy.setEndpoint(parametro.getDsValorParametro());

			DesbloqueioIPhonePortType type = proxy.getDesbloqueioIPhonePortType();
			((DesbloqueioIPhoneBindingStub) type).setTimeout(20 * 1000); // 20 segundos...
			logTelaInicial.debug("Definido tempo limite de 20 segundos para WS Desbloqueio Iphone");

			ConfirmarDesbloqueioIPhoneRequest confDesbloqueio = new ConfirmarDesbloqueioIPhoneRequest(nrSerial);
			logTelaInicial.debug("Chamando WS para o IMEI: " + nrSerial);

			try {
				resposta = type.confirmarDesbloqueioIPhone(confDesbloqueio);
				if (!ConstantesCRM.SVAZIO.equals(resposta.getDataDesbloqueio())) {
					// Formatação para apresentar apenas a data que Retorna como String mes/dia/ano hora:min:seg locale
					if (resposta.getDataDesbloqueio().indexOf("/") > -1) {
						String data = resposta.getDataDesbloqueio().substring(0, 11);
						resposta.setDataDesbloqueio(data.split("/")[1] + "/" + data.split("/")[0] + "/" + data.split("/")[2]);
					}
					ajaxErrorHandlerVO.setSeverity(0);
					ajaxErrorHandlerVO.setExceptionMessage(ConstantesCRM.SVAZIO);
					ajaxErrorHandlerVO.setFriendlyMessage("Aparelho desbloqueado em " + resposta.getDataDesbloqueio());
					xmlDados.append(ajaxErrorHandlerVO.xmlText());
				} else {
					ajaxErrorHandlerVO.setSeverity(0);
					ajaxErrorHandlerVO.setExceptionMessage(ConstantesCRM.SVAZIO);
					ajaxErrorHandlerVO.setFriendlyMessage("Aparelho desbloqueado ");
					xmlDados.append(ajaxErrorHandlerVO.xmlText());
				}
				try {
					parametro = GetParametro.getInstace().getParametroVO(user, PALITAGEM_PESQUISA_UNLOCKIPHONE);
					if (parametro != null && !ConstantesCRM.SVAZIO.equals(parametro.getDsValorParametro())) {
						String idLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdLinha();
						String nrLinhaAtual = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
						
						ClienteUtils.registrarPalitagem(user, getParametrosVO(request), idLinha, nrLinhaAtual, parametro.getDsValorParametro(), ConstantesCRM.SVAZIO);
					}
				} catch (Exception e) {
				}

			} catch (br.com.vivo.www.MC.Geral.ErroInfo e) {
				if ("04".equals(e.getCodigo())) {
					ajaxErrorHandlerVO.setSeverity(4);
					ajaxErrorHandlerVO.setFriendlyMessage("O Dispositivo não foi desbloqueado");
					ajaxErrorHandlerVO.setExceptionMessage(e.getDescricao());

				} else if ("01".equals(e.getCodigo())) {
					ajaxErrorHandlerVO.setSeverity(1);
					ajaxErrorHandlerVO.setFriendlyMessage("Não foi possível realizar a consulta, por favor tente novamente");
					ajaxErrorHandlerVO.setExceptionMessage(e.getDescricao());

				} else if ("02".equals(e.getCodigo())) {
					ajaxErrorHandlerVO.setSeverity(2);
					ajaxErrorHandlerVO.setFriendlyMessage("IMEI informado inválido");
					ajaxErrorHandlerVO.setExceptionMessage(e.getDescricao());

				} else if ("03".equals(e.getCodigo())) {
					ajaxErrorHandlerVO.setSeverity(3);
					ajaxErrorHandlerVO.setFriendlyMessage("Não foi possível identificar e autenticar o cliente");
					ajaxErrorHandlerVO.setExceptionMessage(e.getDescricao());
				} else if ("05".equals(e.getCodigo())) {
					ajaxErrorHandlerVO.setSeverity(5);
					ajaxErrorHandlerVO.setFriendlyMessage("IMEI não encontrado");
					ajaxErrorHandlerVO.setExceptionMessage(e.getDescricao());
				} else {
					throw e;
				}
				xmlDados.append(ajaxErrorHandlerVO.xmlText());
			}
		} catch (br.com.vivo.www.MC.Geral.ErroInfo e) {
			if (e.getFaultString().indexOf("time") > -1) {
				msgError += "...(Time Out)";
			}
			logTelaInicial.error("ErroInfo: " + e.getFaultString(), e);
			ajaxErrorHandlerVO.setExceptionMessage(e.getFaultString());
			ajaxErrorHandlerVO.setFriendlyMessage(e.getDescricao());
			xmlDados = new StringBuffer();
			xmlDados.append(ajaxErrorHandlerVO.xmlText());

		} catch (org.apache.axis.AxisFault e) {
			if (e.getFaultString().indexOf("time") > -1) {
				msgError += "...(Time Out)";
			}
			logTelaInicial.error("AxisFault: " + e.getFaultString(), e);
			ajaxErrorHandlerVO.setExceptionMessage(e.getFaultString());
			ajaxErrorHandlerVO.setFriendlyMessage(msgError);
			xmlDados = new StringBuffer();
			xmlDados.append(ajaxErrorHandlerVO.xmlText());

		} catch (RemoteException e) {
			if (e.getMessage().indexOf("time") > -1) {
				msgError += "...(Time Out)";
			}
			logTelaInicial.error("RemoteException: " + e.getMessage(), e);
			ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
			ajaxErrorHandlerVO.setFriendlyMessage(msgError);
			xmlDados = new StringBuffer();
			xmlDados.append(ajaxErrorHandlerVO.xmlText());

		} catch (Exception e) {
			logTelaInicial.error("Exception: " + e.getMessage(), e);
			ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
			ajaxErrorHandlerVO.setFriendlyMessage(msgError);
			xmlDados = new StringBuffer();
			xmlDados.append(ajaxErrorHandlerVO.xmlText());

		} finally {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			try {
				response.setContentType(ConstantesCRM.SContentType);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
				bufferedOutputStream.write(xmlDados.toString().getBytes(ConstantesCRM.SISO));
				bufferedOutputStream.flush();
				bufferedOutputStream.close();
			} catch (Exception e) {
			}
		}
		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaDesbloqueioIphone.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward showAbaImeiIphone(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			//AbaDesbloqueioAparelho form = (AbaDesbloqueioAparelho) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logTelaInicial.debug("TelaInicialController:showAbaImeiIphone(" + user + ") >> INICIALIZADO");

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:showAbaImeiIphone(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaDesbloqueioIphone.jsp"
	 * @jpf:forward name="erro" path="erroAtlys.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward desbloqueioAparelhoIphone(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		//AbaDesbloqueioAparelho form = (AbaDesbloqueioAparelho) formParam;
		StringBuffer xmlDados = new StringBuffer(ConstantesCRM.SVAZIO);
		user = ConstantesCRM.getCurrentUser(request.getSession());
		String nrSerial = request.getParameter("nrSerial");
		AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
		String msgError = "Serviço indisponível no momento";

		try {
			if (nrSerial == null || ConstantesCRM.SVAZIO.equals(nrSerial)) {
				logTelaInicial.error("Número Serial não informado!");
				throw new Exception("Favor informar o Serial");
			}
			ParametroVO parametro = GetParametro.getInstace().getParametroVO(user, PARAMETRO_ENDPOINT_UNLOCKIPHONE);
			logTelaInicial.debug("Chave para busca de parametro Desbloqueio Iphone: " + parametro.getCdParametro());
			logTelaInicial.debug("End Point para Desbloqueio Iphone: " + parametro.getDsValorParametro());

			DesbloqueioIPhonePortTypeProxy proxy = new DesbloqueioIPhonePortTypeProxy();
			proxy.setEndpoint(parametro.getDsValorParametro());

			DesbloqueioIPhonePortType type = proxy.getDesbloqueioIPhonePortType();
			((DesbloqueioIPhoneBindingStub) type).setTimeout(20 * 1000); // 20 segundos...
			logTelaInicial.debug("Definido tempo limite de 20 segundos para WS Desbloqueio Iphone");

			DesbloquearIPhoneRequest desbloquearIPhoneRequest = new DesbloquearIPhoneRequest(nrSerial);
			logTelaInicial.debug("Chamando WS para o IMEI: " + nrSerial);

			try {
				type.desbloquearIPhone(desbloquearIPhoneRequest);
				try {
					parametro = GetParametro.getInstace().getParametroVO(user, PALITAGEM_DESBLOQUEIO_UNLOCKIPHONE);
					if (parametro != null && !ConstantesCRM.SVAZIO.equals(parametro.getDsValorParametro())) {
						String idLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdLinha();
						String nrLinhaAtual = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();

						ClienteUtils.registrarPalitagem(user, getParametrosVO(request), idLinha, nrLinhaAtual, parametro.getDsValorParametro(), ConstantesCRM.SVAZIO);
					}
				} catch (Exception e) {
				}

				ajaxErrorHandlerVO.setExceptionMessage(ConstantesCRM.SVAZIO);
				ajaxErrorHandlerVO.setFriendlyMessage("Processo de desbloqueio de aparelho em andamento. Para dar prosseguimento, favor instruir o Cliente a conectar fisicamente ('por cabo') o seu IPhone ao ITunes.  Após essa ação, o aparelho estará desbloqueado.");
				xmlDados.append(ajaxErrorHandlerVO.xmlText());

			} catch (br.com.vivo.www.MC.Geral.ErroInfo e) {
				throw e;
			}

		} catch (br.com.vivo.www.MC.Geral.ErroInfo e) {
			if (e.getFaultString().indexOf("time") > -1) {
				msgError += "...(Time Out)";
			}
			logTelaInicial.error("ErroInfo: " + e.getFaultString(), e);
			ajaxErrorHandlerVO.setExceptionMessage(e.getFaultString());
			ajaxErrorHandlerVO.setFriendlyMessage(e.getDescricao());
			xmlDados = new StringBuffer();
			xmlDados.append(ajaxErrorHandlerVO.xmlText());

		} catch (org.apache.axis.AxisFault e) {
			if (e.getFaultString().indexOf("time") > -1) {
				msgError += "...(Time Out)";
			}
			logTelaInicial.error("AxisFault: " + e.getFaultString(), e);
			ajaxErrorHandlerVO.setExceptionMessage(e.getFaultString());
			ajaxErrorHandlerVO.setFriendlyMessage(msgError);
			xmlDados = new StringBuffer();
			xmlDados.append(ajaxErrorHandlerVO.xmlText());

		} catch (RemoteException e) {
			if (e.getMessage().indexOf("time") > -1) {
				msgError += "...(Time Out)";
			}
			logTelaInicial.error("RemoteException: " + e.getMessage(), e);
			ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
			ajaxErrorHandlerVO.setFriendlyMessage(msgError);
			xmlDados = new StringBuffer();
			xmlDados.append(ajaxErrorHandlerVO.xmlText());

		} catch (Exception e) {
			logTelaInicial.error("Exception: " + e.getMessage(), e);
			ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
			ajaxErrorHandlerVO.setFriendlyMessage(msgError);
			xmlDados = new StringBuffer();
			xmlDados.append(ajaxErrorHandlerVO.xmlText());

		} finally {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			try {
				response.setContentType(ConstantesCRM.SContentType);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
				bufferedOutputStream.write(xmlDados.toString().getBytes(ConstantesCRM.SISO));
				bufferedOutputStream.flush();
				bufferedOutputStream.close();
			} catch (Exception e) {
			}
		}
		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="dadosLinha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadLinha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			logTelaInicial.debug("TelaInicialController:loadLinha(" + user + ") >> INICIALIZADO -> Dados da Linha");
			ActionForward forward = mapping.findForward(ConstantesCRM.SUCCESS);
			if ((((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoRelacionamento() != null && ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoRelacionamento().equalsIgnoreCase("6"))
					|| (((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoRelacionamento() != null && ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoRelacionamento().equalsIgnoreCase("7")) || request.getSession().getAttribute("idProspect") != null) {
				logTelaInicial.debug("TelaInicialController:loadLinha(" + user + ") >> DETECTADO -> Prospect");
				tiForm = new TIForm();
				tiForm.setLinha(TILinha.Factory.newInstance());
				tiForm.getLinha().addNewUsuarioLinhaVO();
			} else {
				if (request.getParameter("reloadLinha") != null) {
					logTelaInicial.debug("TelaInicialController:loadLinha(" + user + ") >> reloadLinha: " + request.getParameter("reloadLinha").toString());
					tiForm = new TIForm();
					String idLinhaSelecionada = request.getParameter("idLinhaSelecionada");
					String idPessoaDePara = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdClienteDePara();
					ReloadLinhaVO linhaPesquisada = ReloadLinhaVO.Factory.newInstance();
					logTelaInicial.debug("TelaInicialController:loadLinha(" + user + ") >> idLinhaSelecionada: " + idLinhaSelecionada);
					logTelaInicial.debug("TelaInicialController:loadLinha(" + user + ") >> idPessoaDePara: " + idPessoaDePara);
					linhaPesquisada = telaInicialFacadeControl.getLinhaPesquisada(user, idLinhaSelecionada, idPessoaDePara);
					tiForm.getLinha().setNrLinhaFormat(linhaPesquisada.getNrLinhaFormat());
					tiForm.getLinha().setNrLinha(linhaPesquisada.getNrCodArea() + linhaPesquisada.getNrLinha());
					linhaPesquisada.addNewUsuarioLinhaVO();
					tiForm.getLinha().setUsuarioLinhaVO(linhaPesquisada.getUsuarioLinhaVO());
					tiForm.getLinha().setDsEstadoLinha(linhaPesquisada.getDsEstadoLinha());
					tiForm.getLinha().setDsPlanoServico(linhaPesquisada.getDsPlanoServico());
					tiForm.getLinha().setDsTipoLinha(linhaPesquisada.getDsTipoLinha());
					tiForm.getLinha().setIdLinha(idLinhaSelecionada);
					tiForm.getLinha().setNrCodArea(linhaPesquisada.getNrCodArea());
					tiForm.getLinha().setQtdLinhasAtivas(linhaPesquisada.getQtdLinhasAtivas());
					tiForm.setQtdLinhasTotal(Integer.parseInt(linhaPesquisada.getQtdLinhasPos()) + Integer.parseInt(linhaPesquisada.getQtdLinhasPre()));
					// tiForm.setQtdLinhasCanceladas(tiForm.getQtdLinhasTotal() -
					// Integer.parseInt(linhaPesquisada.getQtdLinhasAtivas()));
					tiForm.setQtdLinhasCanceladas(Integer.parseInt(linhaPesquisada.getQtdLinhasInativas()));
					((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setIdConta(linhaPesquisada.getIdConta());
					((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setIdContaSistemaOrigem(linhaPesquisada.getIdContaSistemaOrigem());
					((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setIdLinha(idLinhaSelecionada);
					((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setIdLinhaSistemaOrigem(linhaPesquisada.getIdLinhaSistemaOrigem());
					((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setIdPessoaLinhaHistorico(linhaPesquisada.getIdPessoaLinhaHistorico());
					((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setIdPessoaUsuario(linhaPesquisada.getIdPessoaUsuario());
					((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setIdTipoLinha(linhaPesquisada.getIdTipoLinha());
					((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setIdUsuarioDePara(linhaPesquisada.getIdUsuarioDePara());
					((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setNrCodArea(linhaPesquisada.getNrCodArea());
					((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setNrLinha(linhaPesquisada.getNrCodArea() + linhaPesquisada.getNrLinha());
					((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).setNrConta(linhaPesquisada.getNrConta());
					request.getSession().setAttribute("reloadLinha", ConstantesCRM.STRUE);
				} else {
					/*
					 * tiForm = ((TIForm)request.getSession().getAttribute("TIFORM"));
					 * request.getSession().removeAttribute("TIFORM");
					 * tiForm.getLinha().setNrLinha(tiForm.getNrLinha());
					 * tiForm.setQtdLinhasTotal(Integer.parseInt(tiForm.linha.getQtdLinhasPos()) +
					 * Integer.parseInt(tiForm.linha.getQtdLinhasPre()));
					 * tiForm.setQtdLinhasCanceladas(tiForm.getQtdLinhasTotal() -
					 * Integer.parseInt(tiForm.linha.getQtdLinhasAtivas()));
					 */
				}
			}

			// forward.addPageInput("piTIForm", tiForm);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return forward;

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:loadLinha(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, ConstantesCRM.TELAINCIAL);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaServico.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward filtroAbaServico(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			AbaServico form = (AbaServico) formParam;
			AbaServicosFiltroVO filt;
			// Busca as informações de ID linha e ID conta na tela.
			String nrConta = form.getFiltroServicos().getNrConta().trim();
			String nrLinha = form.getFiltroServicos().getNrLinha().trim();
			String idPessoa = ConstantesCRM.SVAZIO;
			String idTipoRelacionamento = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoRelacionamento();
			int idTipoLinha = Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());
			if (idTipoRelacionamento.equals(ConstantesCRM.STWO)) {
				logTelaInicial.debug("TelaInicialController:filtroAbaServico() >> DETECTADO -> Cliente");
				idPessoa = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaCliente();
			} else {
				logTelaInicial.debug("TelaInicialController:filtroAbaServico() >> DETECTADO -> Usuário");
				idPessoa = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaUsuario();
			}
			filt = telaInicialFacadeControl.getAbaServicosFiltro(user, nrConta, nrLinha, idPessoa, idTipoRelacionamento);
			abaServ.setFiltroServicos(filt);
			if ((null != filt.getIdLinhaSistemaOrigem() && filt.getIdLinhaSistemaOrigem().equalsIgnoreCase("P")) && (nrConta != null && !nrConta.equalsIgnoreCase(ConstantesCRM.SVAZIO))) {
				servicos = HistoricoAtendimentoVO.Factory.newInstance();
				abaServ.getFiltroServicos().setMensagem("Linhas Pré-Pagas não possuem conta!");

				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);

			} else {
				if (filt.getMensagem() != null && !filt.getMensagem().trim().equals(ConstantesCRM.SVAZIO)) {
					servicos = HistoricoAtendimentoVO.Factory.newInstance();
				} else {
					servicos = null;
					if (filt.getIdLinhaSistemaOrigem() != null && filt.getIdLinhaSistemaOrigem().equalsIgnoreCase("P")) {
						servicos = telaInicialFacadeControl.getAbaServicos(user, ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO, nrLinha, idTipoLinha);
					} else {
						servicos = telaInicialFacadeControl.getAbaServicos(user, filt.getIdContaSistemaOrigem(), filt.getIdLinhaSistemaOrigem(), ConstantesCRM.SVAZIO, idTipoLinha);
					}
				}

				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:filtroAbaServico(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="dadosFaturamento.jsp"
	 * @jpf:forward name="prepago" path="dadosPrePago.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadFaturaIni(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		//TIForm form = (TIForm) formParam;
		logTelaInicial.debug("TelaInicialController:loadFaturaIni() >> INICIALIZADO");
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			String destino = null;
			if (((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha().equalsIgnoreCase(ConstantesCRM.SONE)) {
				logTelaInicial.debug("TelaInicialController:loadFaturaIni() >> DETECTADO -> Linha Pós-paga");
				if (tiForm == null) {
					tiForm = new TIForm();
				}
				String nrConta = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrConta();
				tiForm.getFatura().setNrConta(nrConta);
				tiForm.getFatura().setDsCicloFatura(ConstantesCRM.SVAZIO);
				tiForm.getFatura().setSaldo(ConstantesCRM.SVAZIO);
				tiForm.getFatura().setDtVencimento(ConstantesCRM.SVAZIO);
				tiForm.getFatura().setDsFormaPagamento(ConstantesCRM.SVAZIO);
				tiForm.getFatura().setInContaCobranca(ConstantesCRM.SZERO);
				destino = ConstantesCRM.SUCCESS;

				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(destino);

			} else {
				logTelaInicial.debug("TelaInicialController:loadFaturaIni() >> DETECTADO -> Linha Pré-paga");
				// Pré-Pago
				// INICIAR BEAN DE PRE-PAGO PARA dadosPrePago.jsp
				detalhesSaldoForm = new DetalhesSaldoForm();
				detalhesSaldoForm.setBalanco(ConstantesCRM.SVAZIO);
				detalhesSaldoForm.getDetalhesSaldoVO().setCdSeguranca(ConstantesCRM.SVAZIO);
				detalhesSaldoForm.getDetalhesSaldoVO().setEstadoLinha(ConstantesCRM.SVAZIO);
				detalhesSaldoForm.getDetalhesSaldoVO().setPrValidadeReal(ConstantesCRM.SVAZIO);
				destino = "prepago";

				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(destino);
			}

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:loadFaturaIni(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, ConstantesCRM.TELAINCIAL);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 */
	public ActionForward loadFaturamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//TIForm form = (TIForm) formParam;
		user = ConstantesCRM.getCurrentUser(request.getSession());
		tiForm = new TIForm();
		String idContaSO = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdContaSistemaOrigem();
		String nrConta = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrConta();
		int idTipoLinha = Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());
		tiForm.setDsErro(ConstantesCRM.SVAZIO);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
		try {
			tiForm.setFatura(telaInicialFacadeControl.getFaturamento(user, idContaSO, idTipoLinha));
			tiForm.getFatura().setNrConta(nrConta);
			response.setContentType(ConstantesCRM.SContentType);
			bufferedOutputStream.write(tiForm.getFatura().xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO).replaceAll("[\n]", ConstantesCRM.SVAZIO).getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();

		} catch (Exception ex) {
			logTelaInicial.error("TelaInicialController:loadFaturamento(" + user + ") - [" + ex.getMessage() + "]", ex);
			response.setContentType("text/plain;charset=ISO-8859-1");
			bufferedOutputStream.write("erro".getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
		}
		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path=ConstantesCRM.SVAZIO
	 */
	public ActionForward loadPrePago(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		user = ConstantesCRM.getCurrentUser(request.getSession());
		String nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
		int idTipoLinha = Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());
		BufferedOutputStream saida = new BufferedOutputStream(response.getOutputStream());

		try {
			DetalhesSaldoVO dSaldoVO = consultasPrePagoFacadeControl.getDetalhesSaldo(user, nrLinha, idTipoLinha);
			DetalhesSaldoItem[] itens = dSaldoVO.getDetalhesSaldoItemArray();

			//String saldoTotal = "0.00";
			//if (!ConstantesCRM.SVAZIO.equals(dSaldoVO.getSaldoTotal())) {
				//saldoTotal = dSaldoVO.getSaldoTotal();
			//}
			SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantesCRM.formatoData);
			String data = dSaldoVO.getPrValidadeReal();
			data = data.replaceAll("-", "/");

			if (!ConstantesCRM.SVAZIO.equals(toString())) {
				data = formataData(data);
			}
			dSaldoVO.setPrValidadeReal(data);
			dSaldoVO.setDetalhesSaldoItemArray(itens);

			response.setContentType(ConstantesCRM.SContentType);
			saida.write(dSaldoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO).replaceAll("[\n]", ConstantesCRM.SVAZIO).getBytes(ConstantesCRM.SISO));
			saida.flush();
			saida.close();

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:loadPrePago(" + user + ") - [" + e.getMessage() + "]", e);
			response.setContentType("text/plain;charset=ISO-8859-1");
			saida.write("erro".getBytes(ConstantesCRM.SISO));
			saida.flush();
			saida.close();
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="abaServicoPrePago.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward pesquisarHistoricoAtendimento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			HistoricoAtendimentoForm form = (HistoricoAtendimentoForm) formParam;
			String nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
			if (historicoAtendimentoForm == null) {
				historicoAtendimentoForm = new HistoricoAtendimentoForm();
				historicoAtendimentoForm.setNrLinha(nrLinha);
			} else {
				SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantesCRM.formatoAnoMesDiaHoraMinutoSegundo);
				Date dtUtil = new Date(form.getDtIni());
				String dtInicial = dateFormat.format(dtUtil);
				dtUtil = new Date(form.getDtFin());
				String dtFinal = dateFormat.format(dtUtil);
				int idTipoLinha = Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());
				// ppaula
				historicoAtendimentoForm = form;
				try {
					HistoricoAtendimentoVO historicoVO = consultasPrePagoFacadeControl.getHistoricoAtendimento(user, nrLinha, dtInicial, dtFinal, idTipoLinha);
					HistoricoAtendimentoItem[] itens = historicoVO.getHistoricoAtendimentoItemArray();
					for (int i = 0; i < itens.length; i++) {
						dateFormat = new SimpleDateFormat(ConstantesCRM.formatoData);
						String dt = itens[i].getData();
						dt = dt.replaceAll("-", "/");
						dtUtil = new Date(dt);
						dt = dateFormat.format(dtUtil);
						itens[i].setData(dt);
					}
					historicoVO.setHistoricoAtendimentoItemArray(itens);
					historicoAtendimentoForm.setHistoricoAtendimentoVO(historicoVO);

				} catch (TuxedoErrorException tee) {
					request.setAttribute(ConstantesCRM.SERROR, "(*) Dados não recuperados pois Sistema Legado temporariamente fora de serviço!");

				} catch (TuxedoWarningException twe) {
					request.setAttribute(ConstantesCRM.SERROR, "(*) Dados não recuperados pois Sistema Legado temporariamente fora de serviço!");
				}
			}
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:pesquisarHistoricoAtendimento(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 *
	 * Objetivo: obter do servico os dados referentes a uma linha pre-paga
	 */
	protected void loadDetalheLinhaPrePago(ActionForm formParam, HttpServletRequest request) throws Exception {
		LupaLinhaForm form = (LupaLinhaForm) formParam;

		int idTipoLinha = Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());
		String nrLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
		DetalheLinhaVO detalheLinha = consultasPrePagoFacadeControl.getDetalheLinha(user, nrLinha, idTipoLinha);
		form.setFormDetalheLinhaVO(detalheLinha);
		ServicoVO servico = consultasPrePagoFacadeControl.getServico(user, nrLinha, idTipoLinha);
		form.setFormServicoVO(servico);
		PromocoesVO promocoesVO = consultasPrePagoFacadeControl.getPromocoes(user, nrLinha, idTipoLinha);
		/*
		 * PromocoesItem[] itens = promocoesVO.getPromocoesItemArray(); SimpleDateFormat dateFormat = new
		 * SimpleDateFormat(ConstantesCRM.formatoData); String dtAtual = dateFormat.format(new Date()); for(int i=0;
		 * i<itens.length; i++){ if(dtAtual.compareTo(itens[i].getDataFim()) <= 0) itens[i].setAtivo("ATIVO"); else
		 * itens[i].setAtivo("INATIVO"); }
		 */
		form.setFormPromocoesVO(promocoesVO);
		FavoritosVO favoritosVO = consultasPrePagoFacadeControl.getFavoritos(user, nrLinha, idTipoLinha);
		form.setFormFavoritosVO(favoritosVO);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="popupPesquisaCliente.jsp"
	 */
	public ActionForward irPesquisaNome(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		//PesquisaForm form = (PesquisaForm) formParam;
		request.getSession().removeAttribute(ConstantesCRM.NRPROTOCOLO);
		getTiForm().setNrProtocolo(ConstantesCRM.SVAZIO);
		pesquisaForm = new PesquisaForm();
		pesquisaForm.setTipoPesquisa(request.getParameter("tipo"));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="/cliente/Prospects/ProspectsController.jpf"
	 */
	public ActionForward irCadastroProspect(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		PesquisaForm form = (PesquisaForm) formParam;
		//ActionForward f = mapping.findForward(ConstantesCRM.SUCCESS);
		/* Chamada via Screen Pop de linha não Vivo */
		if (request.getParameter("screenPop") != null) {
			//ActionRedirect redirect = new ActionRedirect(mapping.findForwardConfig(ConstantesCRM.SUCCESS));
			request.getSession().setAttribute("screenPop", ConstantesCRM.STRUE);
			request.getSession().setAttribute("nrLinhaProspect", request.getParameter("nrLinhaProspect"));
		} else {
			String[] nome = new String[4];
			nome[0] = form.getNome();
			nome[1] = form.getNmMeio();
			nome[2] = form.getSobrenome();
			nome[3] = form.getTipoPesquisa();
			request.getSession().setAttribute("dadosProspect", nome);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="lupaLinha.jsp"
	 */
	public ActionForward limpaFiltro(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		//LupaLinhaForm form = (LupaLinhaForm) formParam;
		try {
			if (lupaLinhaForm == null) {
				setLupaLinhaForm(request);
			}
		} catch (Exception e) {
		}
		lupaLinhaForm.setFormListaDadosLupaLinha(new ListaDadosLupaLinha[0]);
		lupaLinhaForm.setFormNrLinhaFiltro(ConstantesCRM.SVAZIO);
		lupaLinhaForm.setFormNrLinhaFiltroAte(ConstantesCRM.SVAZIO);
		lupaLinhaForm.setFormIDTipoLinhaFiltro(ConstantesCRM.SVAZIO);
		lupaLinhaForm.setFormIDStatusLinhaFiltro(ConstantesCRM.SVAZIO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 */
	public ActionForward carregaNaoClienteXml(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//TIForm form = (TIForm) formParam;
		ParametrosVO tmpVO = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
		tmpVO.setIdTipoLinha(ConstantesCRM.SONE);
		tmpVO.setIdPessoaCliente(Integer.toString(idPessoaProspect));
		tmpVO.setIdClienteDePara(Integer.toString(idPessoaProspect));
		tmpVO.setIdUsuarioDePara(Integer.toString(idPessoaProspect));
		tmpVO.setIdPessoaUsuario(Integer.toString(idPessoaProspect));
		tmpVO.setNrConta(ConstantesCRM.SVAZIO);
		tmpVO.setIdConta(ConstantesCRM.SONE);
		tmpVO.setInTipoPessoa("PF");
		tmpVO.setIdTipoRelacionamento(ConstantesCRM.SSEVEN);
		tmpVO.setIdLinha(ConstantesCRM.SVAZIO);

		StringBuffer xmlDadosTI = new StringBuffer("<xmlDadosTI tipo = 'naoCliente'>");
		xmlDadosTI.append("<nome>Não Identificado</nome>");
		xmlDadosTI.append("<parametros>");

		String nrProtocolo = ConstantesCRM.SVAZIO;
		if (getTiForm().isProtocoloScreenPop()) {
			nrProtocolo = (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO);
			xmlDadosTI.append("<isScreenPop>1</isScreenPop>");
		} else {
			tmpVO.setNrLinha(ConstantesCRM.SVAZIO);
			nrProtocolo = ConstantesCRM.SVAZIO;
			xmlDadosTI.append("<isScreenPop>0</isScreenPop>");
		}

		xmlDadosTI.append("<tipoRelacionamento>7</tipoRelacionamento>");
		xmlDadosTI.append("<idPessoaCliente>").append(Integer.toString(idPessoaProspect)).append("</idPessoaCliente>");
		xmlDadosTI.append("<idProspect/>");
		xmlDadosTI.append("<idTipoLinha>1</idTipoLinha>");
		xmlDadosTI.append("<inLegado/>");
		xmlDadosTI.append("<nrConta/>");
		xmlDadosTI.append("<inPortada>0</inPortada>");
		xmlDadosTI.append("<nrProtocolo>").append(nrProtocolo).append("</nrProtocolo>");
		xmlDadosTI.append("<inCorporativo/>");
		xmlDadosTI.append("</parametros>");
		xmlDadosTI.append("</xmlDadosTI>");
		response.setContentType(ConstantesCRM.SContentType);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
		bufferedOutputStream.write(xmlDadosTI.toString().getBytes(ConstantesCRM.SISO));
		bufferedOutputStream.flush();
		bufferedOutputStream.close();

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="sessaoInvalida" path="/session.jsp"
	 * @jpf:forward name="acessoNegado" path="/acessoNegado.jsp"
	 */
	public ActionForward carregaTelaInicialXml(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TIForm form = (TIForm) formParam;
		String forward = validaAcesso(request);
		getTiForm().setProtocoloScreenPop(new Boolean(request.getParameter("pesquisaScreenPop")).booleanValue());

		if (!getTiForm().isProtocoloScreenPop() && !getTiForm().isPesquisaProtocolo()) {
			request.getSession().removeAttribute(ConstantesCRM.NRPROTOCOLO);
			getTiForm().setNrProtocolo(ConstantesCRM.SVAZIO);
		}

		if (!forward.equals(ConstantesCRM.SVAZIO)) {
			StringBuffer xmlDadosTI = new StringBuffer();
			xmlDadosTI.append("<forward>").append(forward).append("</forward>");

			response.setContentType(ConstantesCRM.SContentType);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			bufferedOutputStream.write(xmlDadosTI.toString().getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();

		} else {
			StringBuffer xmlDadosTI = new StringBuffer();
			try {
				request.setCharacterEncoding(ConstantesCRM.SISO);
				String valor = form.getValor();
				//String idGrupo = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdGrupo();

				carregaParametrosSessao(valor, ConstantesCRM.STWO, request);
				ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);

				telaInicial = telaInicialFacadeControl.getTelaInicial(user, valor);

				String nrProtocolo = request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) != null ? (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) : ConstantesCRM.SVAZIO;

				if (telaInicial.getProtocoloVO() == null) {
					telaInicial.addNewProtocoloVO();
				}
				telaInicial.getProtocoloVO().setNrProtocolo(nrProtocolo);

				if (telaInicial.getTICliente().getSegmentacaoVO() == null) {
					telaInicial.getTICliente().addNewSegmentacaoVO();
				}
				if (telaInicial.getTICliente().getCarterizacaoVO() == null) {
					telaInicial.getTICliente().addNewCarterizacaoVO();
				}

				telaInicial.getTICliente().getSegmentacaoVO().setDescricao(parametros.getDsSegmentacao());
				telaInicial.getTICliente().getSegmentacaoVO().setIdSegmentacao(parametros.getIdSegmentacao());
				String segmentDescTmp = telaInicial.getTICliente().getSegmentacaoVO().getDescricao();
				String cartDescTmp = telaInicial.getTICliente().getCarterizacaoVO().getDescricao();

				if (request.getSession().getAttribute("idProspect") != null) {
					String idProspect = request.getSession().getAttribute("idProspect").toString();
					parametros.setIdTipoRelacionamento(ConstantesCRM.SSIX);
					parametros.setInPos(ConstantesCRM.STWO);
					parametros.setNrConta(ConstantesCRM.SVAZIO);
					parametros.setIdPessoaCliente(idProspect);
					parametros.setIdPessoaUsuario(idProspect);
					parametros.setIdClienteDePara(telaInicial.getTICliente().getIdPessoaDePara());
					parametros.setIdUsuarioDePara(telaInicial.getTICliente().getIdPessoaDePara());
					parametros.setIdTipoLinha(ConstantesCRM.SONE);
				}

				// Se o CarregaParam não tem os dados de ID de cliente, não tem a linha cadastrada e assume
				// portabilidade
				if ("1".equals(telaInicial.getTILinha().getIsPortada())) {
					parametros.setIsPortada(ConstantesCRM.SONE);
					request.getSession().setAttribute("idProspect", telaInicial.getTICliente().getIdPessoa());
					parametros.setIdTipoRelacionamento(ConstantesCRM.SSIX);
					parametros.setIdTipoLinha(telaInicial.getTILinha().getIdTipoLinha());
					parametros.setInPos(ConstantesCRM.STWO);
					parametros.setNrConta(ConstantesCRM.SVAZIO);
					parametros.setIdPessoaCliente(telaInicial.getTICliente().getIdPessoa());
					parametros.setIdPessoaUsuario(telaInicial.getTICliente().getIdPessoa());
					parametros.setIdClienteDePara(telaInicial.getTICliente().getIdPessoaDePara());
					parametros.setIdUsuarioDePara(telaInicial.getTICliente().getIdPessoaDePara());
					telaInicial.getTILinha().setQtdLinhas(ConstantesCRM.SZERO);
					telaInicial.getTILinha().setQtdLinhasAtivas(ConstantesCRM.SZERO);
					telaInicial.getTILinha().setQtdLinhasInativas(ConstantesCRM.SZERO);
					telaInicial.getTILinha().addNewUsuarioLinhaVO();
				}

				if (telaInicial.getTILinha() != null) {
					parametros.setNrLinha(telaInicial.getTILinha().getNrLinha().length() == 8 ? telaInicial.getTILinha().getNrCodArea() + telaInicial.getTILinha().getNrLinha() : telaInicial.getTILinha().getNrLinha());
				}

				if (segmentDescTmp == null) {
					telaInicial.getTICliente().getSegmentacaoVO().setDescricao(SEGMENTACAO_NULO_DEFAULT_MSG);
				} else {
					if (ConstantesCRM.SVAZIO.equals(segmentDescTmp) || segmentDescTmp.equals(NULO_VALUE)) {
						telaInicial.getTICliente().getSegmentacaoVO().setDescricao(SEGMENTACAO_NULO_DEFAULT_MSG);
					}
				}
				if (cartDescTmp == null) {
					telaInicial.getTICliente().getCarterizacaoVO().setDescricao(CARTEIRIZACAO_NULO_DEFAULT_MSG);
				} else {
					if (ConstantesCRM.SVAZIO.equals(cartDescTmp) || NULO_VALUE.equalsIgnoreCase(cartDescTmp)) {
						telaInicial.getTICliente().getCarterizacaoVO().setDescricao(CARTEIRIZACAO_NULO_DEFAULT_MSG);
					}
				}
				TICliente tiCliente = telaInicial.getTICliente();

				UsuarioLinhaVO tiCliUsuarioVO = telaInicial.getTILinha().getUsuarioLinhaVO();
				xmlDadosTI.append("<xmlDadosTI tipo='linha'><cliente>");
				xmlDadosTI.append("<nome>").append(StringEscapeUtils.escapeHtml(tiCliente.getNmNome())).append("</nome>");
				xmlDadosTI.append("<inTipoPessoa>").append(tiCliente.getInTipoPessoa()).append("</inTipoPessoa>");
				xmlDadosTI.append("<nrTelefone>").append(tiCliente.getNrTelefone()).append("</nrTelefone>");
				xmlDadosTI.append("<dsChurn>").append(tiCliente.getDsChurn()).append("</dsChurn>");

				xmlDadosTI.append("<isBlindagem>").append(tiCliente.getIsBlindagem()).append("</isBlindagem>");
				xmlDadosTI.append("<nrProtocolo>").append(nrProtocolo).append("</nrProtocolo>");

				xmlDadosTI.append("<gestor>");
				String nmGestor = (tiCliente.getGestor() != null && tiCliente.getGestor().getNmGestor() != null) ? tiCliente.getGestor().getNmGestor() : ConstantesCRM.SVAZIO;
				String nrTelGestor = (tiCliente.getGestor() != null && tiCliente.getGestor().getNrTelefone() != null) ? tiCliente.getGestor().getNrTelefone() : ConstantesCRM.SVAZIO;
				xmlDadosTI.append("<nmGestor>").append(StringEscapeUtils.escapeHtml(nmGestor)).append("</nmGestor>");
				xmlDadosTI.append("<nrTelefone>").append(StringEscapeUtils.escapeHtml(nrTelGestor)).append("</nrTelefone>");
				xmlDadosTI.append("</gestor>");

				xmlDadosTI.append("<carterizacaoVO>");
				xmlDadosTI.append("<descricao>").append(StringEscapeUtils.escapeHtml(tiCliente.getCarterizacaoVO().getDescricao())).append("</descricao>");
				xmlDadosTI.append("<sgTipoCarteira>").append(StringEscapeUtils.escapeHtml(tiCliente.getCarterizacaoVO().getSgTipoCarteira())).append("</sgTipoCarteira>");
				xmlDadosTI.append("</carterizacaoVO>");

				xmlDadosTI.append("<segmentacaoVO>");
				xmlDadosTI.append("<descricao>").append(StringEscapeUtils.escapeHtml(tiCliente.getSegmentacaoVO().getDescricao())).append("</descricao>");
				xmlDadosTI.append("</segmentacaoVO>");

				xmlDadosTI.append("<documento>");
				xmlDadosTI.append("<dsTipoDocumento>").append(telaInicial.getTIDocumento().getDsTipoDocumento()).append("</dsTipoDocumento>");
				xmlDadosTI.append("<nrDocumento>").append(telaInicial.getTIDocumento().getNrDocumento()).append("</nrDocumento>");
				xmlDadosTI.append("</documento>");

				xmlDadosTI.append("<endereco>");
				xmlDadosTI.append("<dsEndereco>").append(StringEscapeUtils.escapeHtml(telaInicial.getEnderecoBaseVO().getDsEndereco())).append("</dsEndereco>");
				xmlDadosTI.append("<nrEndereco>").append(telaInicial.getEnderecoBaseVO().getNrEndereco()).append("</nrEndereco>");
				xmlDadosTI.append("<dsBairro>").append(StringEscapeUtils.escapeHtml(telaInicial.getEnderecoBaseVO().getDsBairro())).append("</dsBairro>");
				xmlDadosTI.append("<dsCidade>").append(StringEscapeUtils.escapeHtml(telaInicial.getEnderecoBaseVO().getDsCidade())).append("</dsCidade>");

				xmlDadosTI.append("<UFVO>");
				xmlDadosTI.append("<nmUF>").append(telaInicial.getEnderecoBaseVO().getUFVO().getNmUF()).append("</nmUF>");
				xmlDadosTI.append("</UFVO>");

				xmlDadosTI.append("<nrCEP>").append(telaInicial.getEnderecoBaseVO().getNrCEP()).append("</nrCEP>");
				xmlDadosTI.append("</endereco>");

				xmlDadosTI.append("</cliente>");

				xmlDadosTI.append("<linha>");
				xmlDadosTI.append("<nrLinhaFormat>").append(ConstantesCRM.formatPhoneNumber(telaInicial.getTILinha().getNrLinha())).append("</nrLinhaFormat>");
				xmlDadosTI.append("<inBloqueado>").append(telaInicial.getTILinha().getInBloqueado()).append("</inBloqueado>");
				xmlDadosTI.append("<dsTipoLinha>").append(telaInicial.getTILinha().getDsTipoLinha()).append("</dsTipoLinha>");
				xmlDadosTI.append("<dsPlanoServico>").append(telaInicial.getTILinha().getDsPlanoServico()).append("</dsPlanoServico>");
				xmlDadosTI.append("<dsEstadoLinha>").append(telaInicial.getTILinha().getDsEstadoLinha()).append("</dsEstadoLinha>");

				int total = Integer.parseInt(ConstantesCRM.SVAZIO.equals(telaInicial.getTILinha().getQtdLinhas()) ? ConstantesCRM.SZERO : telaInicial.getTILinha().getQtdLinhas());
				int ativas = Integer.parseInt(ConstantesCRM.SVAZIO.equals(telaInicial.getTILinha().getQtdLinhasAtivas()) ? ConstantesCRM.SZERO : telaInicial.getTILinha().getQtdLinhasAtivas());
				int inativas = Integer.parseInt(ConstantesCRM.SVAZIO.equals(telaInicial.getTILinha().getQtdLinhasInativas()) ? ConstantesCRM.SZERO : telaInicial.getTILinha().getQtdLinhasInativas());

				xmlDadosTI.append("<qtdLinhasTotal>").append(total).append("</qtdLinhasTotal>");
				xmlDadosTI.append("<qtdLinhasAtivas>").append(ativas).append("</qtdLinhasAtivas>");
				xmlDadosTI.append("<qtdLinhasCanceladas>").append(inativas).append("</qtdLinhasCanceladas>");

				xmlDadosTI.append("<usuarioLinhaVO>");
				xmlDadosTI.append("<nmUsuario>").append(StringEscapeUtils.escapeHtml(tiCliUsuarioVO.getNmUsuario())).append("</nmUsuario>");
				xmlDadosTI.append("<dsTipoDocumento>").append(tiCliUsuarioVO.getDsTipoDocumento()).append("</dsTipoDocumento>");
				xmlDadosTI.append("<nrDocumento>").append(tiCliUsuarioVO.getNrDocumento()).append("</nrDocumento>");
				xmlDadosTI.append("<dsCargo>").append(tiCliUsuarioVO.getDsCargo()).append("</dsCargo>");
				xmlDadosTI.append("<nrContato>").append(tiCliUsuarioVO.getNrContato()).append("</nrContato>");
				xmlDadosTI.append("</usuarioLinhaVO>");

				if (telaInicial.getTILinha().getItaucardVO() != null && ConstantesCRM.SONE.equals(telaInicial.getTILinha().getItaucardVO().getInItaucard())) {
					xmlDadosTI.append("<ItaucardVO>");
					xmlDadosTI.append("<sgTipoLinha>").append(telaInicial.getTILinha().getItaucardVO().getSgTipoLinha()).append("</sgTipoLinha>");
					xmlDadosTI.append("</ItaucardVO>");
				}

				xmlDadosTI.append("</linha>");

				xmlDadosTI.append("<parametros>");
				if (getTiForm().isProtocoloScreenPop()) {
					xmlDadosTI.append("<isScreenPop>1</isScreenPop>");
				} else {
					xmlDadosTI.append("<isScreenPop>0</isScreenPop>");
				}
				xmlDadosTI.append("<tipoRelacionamento>").append(trataNull(parametros.getIdTipoRelacionamento())).append("</tipoRelacionamento>");
				xmlDadosTI.append("<idPessoaCliente>").append(trataNull(parametros.getIdPessoaCliente())).append("</idPessoaCliente>");
				xmlDadosTI.append("<idProspect>").append(trataNull((String) request.getSession().getAttribute("idProspect"))).append("</idProspect>");
				xmlDadosTI.append("<idTipoLinha>").append(trataNull(parametros.getIdTipoLinha())).append("</idTipoLinha>");
				xmlDadosTI.append("<idChamadaTelefonica>").append(trataNull(parametros.getIdChamadaTelefonica())).append("</idChamadaTelefonica>");
				xmlDadosTI.append("<inLegado>").append(trataNull(parametros.getInLegado())).append("</inLegado>");
				xmlDadosTI.append("<nrConta>").append(trataNull(parametros.getNrConta())).append("</nrConta>");
				xmlDadosTI.append("<inCorporativo>").append(trataNull(parametros.getInCorporativo())).append("</inCorporativo>");
				xmlDadosTI.append("<inPortada>").append(telaInicial.getTILinha().getIsPortada()).append("</inPortada>");
				xmlDadosTI.append("<inPortabilidade>").append(telaInicial.getTILinha().getInPortabilidade()).append("</inPortabilidade>");
				if (telaInicial.getTILinha().getInPortabilidade() == 1) {
					xmlDadosTI.append("<tpPortada>").append(telaInicial.getTILinha().getTpPortada()).append("</tpPortada>");
					xmlDadosTI.append("<dsPortada>").append(telaInicial.getTILinha().getDsPortada()).append("</dsPortada>");
				}
				xmlDadosTI.append("</parametros>");
				xmlDadosTI.append("</xmlDadosTI>");

				// Codigo para poder atender a OS 586 e realizar a palitagem do cadastramento de PrePago
				// Para não Prejudicar algum erro, possivelmente será necessario retirar o codigo dento da clausula
				// catch
				/*
				 * try{ String palitaCadPrePago = (String) request.getSession().getAttribute("INCPREPAGO");
				 * if(palitaCadPrePago!=null && !ConstantesCRM.SVAZIO.equals(palitaCadPrePago) ){ ParametroVO apioParam
				 * = telaInicialFacadeControl.getParametro(user, palitaCadPrePago); String result =
				 * registraPalitagem(telaInicial.getTILinha().getIdLinha(), telaInicial.getTILinha().getNrLinha(),
				 * apioParam.getDsValorParametro(),ConstantesCRM.SVAZIO);
				 * request.getSession().removeAttribute("INCPREPAGO"); } }catch(Exception e){ AjaxErrorHandlerVO
				 * ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
				 * ajaxErrorHandlerVO.setExceptionMessage(e.getMessage()); ajaxErrorHandlerVO.setFriendlyMessage(
				 * "Problema encontrado ao realizar a Palitagem de Cadastramento de PrePago!");
				 * ajaxErrorHandlerVO.setSeverity(0); xmlDadosTI = new StringBuffer();
				 * xmlDadosTI.append(ajaxErrorHandlerVO.xmlText()); logTelaInicial.error(
				 * "TelaInicialController:carregaTelaInicialXml():: Erro ao realizar a Palitagem de PrePago.");
				 * logTelaInicial.error("TelaInicialController:carregaTelaInicialXml(" + user + ") - [" + e.getMessage()
				 * + "]", e); request.getSession().removeAttribute("INCPREPAGO"); }
				 */
				// -- FIM
			} catch (Exception e) {
				logTelaInicial.error("TelaInicialController:carregaTelaInicialXml(" + user + ") - [" + e.getMessage() + "]", e);
				AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
				ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
				ajaxErrorHandlerVO.setFriendlyMessage(MENSAGEM_GERAL);
				xmlDadosTI = new StringBuffer();
				xmlDadosTI.append(ajaxErrorHandlerVO.xmlText());

			} finally {
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				response.setContentType(ConstantesCRM.SContentType);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
				bufferedOutputStream.write(xmlDadosTI.toString().getBytes(ConstantesCRM.SISO));
				bufferedOutputStream.flush();
				bufferedOutputStream.close();
			}
		}
		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="sessaoInvalida" path="/session.jsp"
	 * @jpf:forward name="acessoNegado" path="/acessoNegado.jsp"
	 */
	public ActionForward verificaLinhaPreAtiva(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//TIForm form = (TIForm) formParam;
		StringBuffer xmlDados = new StringBuffer();
		try {
			String nrLinha = request.getParameter("nrLinha");
			PrePagoRetornoValidaLinha prePagoRetornoValidaLinha = prePagoFacade.validarLinha(user, nrLinha);
			xmlDados.append(prePagoRetornoValidaLinha.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
		} catch (Exception e) {
			AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
			ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
			ajaxErrorHandlerVO.setFriendlyMessage(ConstantesCRM.SVAZIO);
			xmlDados = new StringBuffer();
			xmlDados.append(ajaxErrorHandlerVO.xmlText());
		} finally {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			response.setContentType(ConstantesCRM.SContentType);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			bufferedOutputStream.write(xmlDados.toString().getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
		}
		return null;
	}

	private String trataNull(String valor) {
		if (valor == null) {
			return ConstantesCRM.SVAZIO;
		}
		return valor;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="mensagemBlindagem.jsp"
	 */
	public ActionForward prosseguirBlindagem(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			if (request.getParameter("inAtendimento") == null) {
				try {
					GetParametro getParametro = GetParametro.getInstace();

					ParametroVO parametro = getParametro.getParametroVO(user, "BLINDAGEM_CLIENTES_AVISO_RESGATE");
					String blindagemAviso = parametro.getDsValorParametro();

					parametro = getParametro.getParametroVO(user, "BLINDAGEM_CLIENTES_URL_INSTRUCAO");
					String blindagemURL = parametro.getDsValorParametro();

					request.setAttribute("blindagemAviso", blindagemAviso);
					request.setAttribute("blindagemURL", blindagemURL);
				} catch (Exception e) {
				}
			} else {
				if (request.getParameter("inibeMsg") != null && ConstantesCRM.SONE.equals(request.getParameter("inibeMsg"))) {
					ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
					//RetornoVO retorno = 
					telaInicialFacadeControl.inibeMsgPontos(user, parametros.getNrLinha());
				}
				request.setAttribute("fechar", ConstantesCRM.SONE);
			}
		} catch (Exception e) {
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="/workflow/Relacionamento/RelacionamentoController.jpf"
	 */
	public ActionForward chamaRelacionamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="errorAPI" path="erroAtlys.jsp"
	 * @jpf:forward name="success" path="HistoricoServicos.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward getHistoricoServicos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			//AbaServico form = (AbaServico) formParam;
			int idTipoLinha = Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());
			abaServ = new AbaServico();
			HistoricoServicosVO historicoServicos = HistoricoServicosVO.Factory.newInstance();
			historicoServicos.addNewFiltro();
			historicoServicos.getFiltro().setNrLinha(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha());
			historicoServicos.getFiltro().setIdLinha(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdLinha());
			historicoServicos.getFiltro().setIdLinhaSistemaOrigem(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdLinhaSistemaOrigem());
			historicoServicos.getFiltro().setIdTipoLinha(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());
			user = ConstantesCRM.getCurrentUser(request.getSession());
			historicoServicos = telaInicialFacadeControl.getHistoricoServicos(user, historicoServicos, idTipoLinha);
			abaServ.setHistoricoServicos(historicoServicos);

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			if (historicoServicos.getErrorAPI() != null && historicoServicos.getErrorAPI().equalsIgnoreCase("1")) {
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("errorAPI");
			} else {
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}
		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:getHistoricoServicos(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="nada.html"
	 */
	public ActionForward refreshCombos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		//TIForm tiForm = (TIForm) formParam;
		String idRelacionamento = request.getParameter("idRelacionamentoTroca");
		String idPos = request.getParameter("idPosTroca");
		String idGrupo = request.getParameter("idGrupoTroca");
		request.getSession().setAttribute("idRelacionamentoTroca", idRelacionamento);
		request.getSession().setAttribute("idPosTroca", idPos);
		request.getSession().setAttribute("idGrupoTroca", idGrupo);

		ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
		if (!"-1".equals(idRelacionamento)) {
			parametros.setIdTipoRelacionamento(idRelacionamento);
		}
		parametros.setIdGrupo(idGrupo);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return null;
	}

	protected String validaAcesso(HttpServletRequest request) {
		user = ConstantesCRM.SVAZIO;
		//String pagina = ConstantesCRM.SVAZIO;
		String xmlIN;
		String xmlOUT;
		String xmlRetorno[];
		RuleVO ruleVO;
		ControleAcessoEnvioVO controleAcessoEnvioVO = ControleAcessoEnvioVO.Factory.newInstance();
		controleAcessoEnvioVO.setLogin(ConstantesCRM.getCurrentUser(request.getSession()));
		String BLOQUEADO = ConstantesCRM.SZERO;
		String EXPIRADA = ConstantesCRM.STWO;
		String INVALIDA = ConstantesCRM.STHREE;

		if (ConstantesCRM.getCurrentUser(request.getSession()) == null) {
			return "/session.jsp";
		} else {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			try {
				String timeSession = (String) request.getSession().getAttribute(ConstantesCRM.PERFIL_USR_MIN);
				long result = comparaDataLogin(request);

				if (result >= Integer.parseInt(timeSession)) {
					JATMIBusinessDelegate jatmi = new JATMIBusinessDelegate();
					controleAcessoEnvioVO.setInAtivo("1");
					controleAcessoEnvioVO.setSessionId(request.getSession().getId());

					xmlIN = controleAcessoEnvioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
					xmlOUT = jatmi.executeCommnad(user,"UsrAcesso", xmlIN);

					xmlRetorno = XmlManager.ParseXmlOut(xmlOUT);
					ruleVO = RuleVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getRuleVO();

					if (ruleVO == null || ruleVO.getUs().equals(BLOQUEADO)) {
						request.getSession().setAttribute("statusText", "Acesso Negado");
						return "/acessoNegado.jsp";

					} else if (ruleVO == null || ruleVO.getUs().equals(EXPIRADA)) {
						request.getSession().setAttribute("statusText", xmlRetorno[3]);
						return "/acessoNegado.jsp";

					} else if (ruleVO == null || ruleVO.getUs().equals(INVALIDA)) {
						request.getSession().setAttribute("statusText", xmlRetorno[3]);
						return "/acessoNegado.jsp";

					} else {
						request.getSession().setAttribute("dt_login_usr", Calendar.getInstance());
						return ConstantesCRM.SVAZIO;
					}
				}
			} catch (Exception ex) {
				//XmlHeader header = new XmlHeader("UsrAcesso", user, "00", 'E', "0000", "Acesso não autorizado [TI]");
				//TuxedoErrorException tex = new TuxedoErrorException(header, ex);
			}
		}
		return ConstantesCRM.SVAZIO;
	}

	private long comparaDataLogin(HttpServletRequest request) {
		Calendar dtLoginUsr = (Calendar) request.getSession().getAttribute("dt_login_usr");
		Calendar dtAtual = Calendar.getInstance();
		long result = dtAtual.getTimeInMillis() - dtLoginUsr.getTimeInMillis();
		if (result > 0) {
			result = result / 1000 / 60;
		}
		return result;
	}

	/*private String registraPalitagem(String idLinha, String nrLinha, String nmPath, String dsObservacao, HttpServletRequest request) {
		user = ConstantesCRM.getCurrentUser(request.getSession());
		AtendimentoVO atendimentoVO = AtendimentoVO.Factory.newInstance();
		AtendimentoVO retornoRegContato = AtendimentoVO.Factory.newInstance();
		ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);

		atendimentoVO.setIdLinhaAtendimento((parametros.getIdLinhaAtendimento() != null && !parametros.getIdLinhaAtendimento().equals(ConstantesCRM.SZERO)) ? parametros.getIdLinhaAtendimento() : parametros.getIdLinha());
		atendimentoVO.setIdUfOperadora(parametros.getIdUfOperadora() == null || parametros.getIdUfOperadora().equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SONE : parametros.getIdUfOperadora());
		atendimentoVO.setIdTipoLinha(parametros.getIdTipoLinha() == null || parametros.getIdTipoLinha().equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SONE : parametros.getIdTipoLinha());
		atendimentoVO.setIdChamadaTelefonica(Long.parseLong(parametros.getIdChamadaTelefonica() == null || parametros.getIdChamadaTelefonica().equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SONE : parametros.getIdChamadaTelefonica()));
		atendimentoVO.setIdGrupoAbertura(Long.parseLong(parametros.getIdGrupo() == null ? ConstantesCRM.SONE : parametros.getIdGrupo()));
		atendimentoVO.setInResponsavelAbertura(parametros.getIdTipoRelacionamento() == null ? ConstantesCRM.SONE : parametros.getIdTipoRelacionamento());
		atendimentoVO.setInTipoPessoa(parametros.getInTipoPessoa() == null || parametros.getInTipoPessoa().equals(ConstantesCRM.SVAZIO) ? "PF" : parametros.getInTipoPessoa());
		if (nrLinha != null && !ConstantesCRM.SVAZIO.equals(nrLinha)) {
			atendimentoVO.setNrTelefone(nrLinha);
		} else {
			atendimentoVO.setNrTelefone(parametros.getNrLinha() == null ? "1100000000" : parametros.getNrLinha());
		}
		atendimentoVO.setTpOperacao(ConstantesCRM.SONE);/ * tpOperacao: 1=fechar, 2=encaminhar * /
		atendimentoVO.addNewProcedenciaVO().setIdProcedencia(1);/ * Procedência: TELEFONE - ID 1 * /
		atendimentoVO.addNewCanalVO().setIdCanal(1);/ * Canal: CRC - ID 1 * /

		if (idLinha != null && !ConstantesCRM.SVAZIO.equals(idLinha)) {
			atendimentoVO.addNewContas().addNewContaVO().addNewLinhaVO().setIdPessoaLinhaHistorico(Long.parseLong(idLinha));
		} else if (parametros.getIdLinha() != null) {
			if (!ConstantesCRM.SVAZIO.equals(parametros.getIdLinha())) {
				atendimentoVO.addNewContas().addNewContaVO().addNewLinhaVO().setIdPessoaLinhaHistorico(Long.parseLong(parametros.getIdLinha()));
			}
		}
		atendimentoVO.addNewPessoaVO().setIdPessoa(Long.parseLong(parametros.getIdClienteDePara() == null ? "21" : parametros.getIdClienteDePara()));
		atendimentoVO.addNewUsuarioLinhaVO().setIdPessoa(parametros.getIdUsuarioDePara() == null ? "21" : parametros.getIdUsuarioDePara());
		atendimentoVO.addNewArvoreAtendimentoVO().setNmPath(nmPath);
		atendimentoVO.getArvoreAtendimentoVO().addNewCarterizacaoVO().setIdTipoCarteira(Integer.parseInt(parametros.getIdTipoCarteira() == null || parametros.getIdTipoCarteira().equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SONE : parametros.getIdTipoCarteira()));
		atendimentoVO.getArvoreAtendimentoVO().addNewSegmentacaoVO().setIdSegmentacao(Integer.parseInt((parametros.getIdSegmentacao() == null || parametros.getIdSegmentacao().equals(ConstantesCRM.SVAZIO)) ? ConstantesCRM.SONE : parametros.getIdSegmentacao()));
		atendimentoVO.setNrProtocolo(ConstantesCRM.SVAZIO);
		atendimentoVO.setObservacao(dsObservacao);
		try {
			retornoRegContato = registrarContatoFacade.registrarAtendimento(user, atendimentoVO);
		} catch (Exception e) {
			return (e.getMessage());
		}
		return String.valueOf(retornoRegContato.getIdAtendimento());
	}/*

	/*
	 * Método responsável por execução de palitagem no momento em que o detalhe de um processo tracking é acionado.
	 */
	private String[] getTrackingProcesso(String nrPedido, HttpServletRequest request) {

		String[] retorno = new String[2];
		user = ConstantesCRM.getCurrentUser(request.getSession());
		AtendimentoVO atendimentoVO = AtendimentoVO.Factory.newInstance();
		AtendimentoVO retornoRegContato = AtendimentoVO.Factory.newInstance();
		ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);

		atendimentoVO.setIdLinhaAtendimento((parametros.getIdLinhaAtendimento() != null && !parametros.getIdLinhaAtendimento().equals(ConstantesCRM.SZERO)) ? parametros.getIdLinhaAtendimento() : parametros.getIdLinha());
		atendimentoVO.setIdUfOperadora(parametros.getIdUfOperadora() == null || parametros.getIdUfOperadora().equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SONE : parametros.getIdUfOperadora());
		atendimentoVO.setIdTipoLinha(parametros.getIdTipoLinha() == null || parametros.getIdTipoLinha().equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SONE : parametros.getIdTipoLinha());
		atendimentoVO.setIdChamadaTelefonica(Long.parseLong(parametros.getIdChamadaTelefonica() == null || parametros.getIdChamadaTelefonica().equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SONE : parametros.getIdChamadaTelefonica()));
		atendimentoVO.setIdGrupoAbertura(Long.parseLong(parametros.getIdGrupo() == null ? ConstantesCRM.SONE : parametros.getIdGrupo()));
		atendimentoVO.setInResponsavelAbertura(parametros.getIdTipoRelacionamento() == null ? ConstantesCRM.SONE : parametros.getIdTipoRelacionamento());
		atendimentoVO.setInTipoPessoa(parametros.getInTipoPessoa() == null || parametros.getInTipoPessoa().equals(ConstantesCRM.SVAZIO) ? "PF" : parametros.getInTipoPessoa());

		atendimentoVO.setNrTelefone(parametros.getNrLinha() == null ? "1100000000" : parametros.getNrLinha());

		/* tpOperacao: 1=fechar, 2=encaminhar */
		atendimentoVO.setTpOperacao(ConstantesCRM.SONE);

		/* Procedência: TELEFONE - ID 1 */
		atendimentoVO.addNewProcedenciaVO().setIdProcedencia(1);
		/* Canal: CRC - ID 1 */
		atendimentoVO.addNewCanalVO().setIdCanal(1);

		if (parametros.getIdLinha() != null) {
			if (!ConstantesCRM.SVAZIO.equals(parametros.getIdLinha())) {
				atendimentoVO.addNewContas().addNewContaVO().addNewLinhaVO().setIdPessoaLinhaHistorico(Long.parseLong(parametros.getIdLinha()));
			}
		}

		atendimentoVO.addNewPessoaVO().setIdPessoa(Long.parseLong(parametros.getIdClienteDePara() == null ? "21" : parametros.getIdClienteDePara()));
		atendimentoVO.addNewUsuarioLinhaVO().setIdPessoa(parametros.getIdUsuarioDePara() == null ? "21" : parametros.getIdUsuarioDePara());
		atendimentoVO.addNewArvoreAtendimentoVO().setNmPath(TRACKING_FOLHA);
		atendimentoVO.getArvoreAtendimentoVO().addNewCarterizacaoVO().setIdTipoCarteira(Integer.parseInt(parametros.getIdTipoCarteira() == null || parametros.getIdTipoCarteira().equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SONE : parametros.getIdTipoCarteira()));
		atendimentoVO.getArvoreAtendimentoVO().addNewSegmentacaoVO().setIdSegmentacao(Integer.parseInt((parametros.getIdSegmentacao() == null || parametros.getIdSegmentacao().equals(ConstantesCRM.SVAZIO)) ? ConstantesCRM.SONE : parametros.getIdSegmentacao()));

		if (nrPedido == null) {
			nrPedido = ConstantesCRM.SVAZIO;
		}
		atendimentoVO.setObservacao("Número do Pedido: " + nrPedido);

		String nrProtocolo = request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) != null ? (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) : ConstantesCRM.SVAZIO;
		atendimentoVO.addNewDadosProtocoloVO();

		if (!ConstantesCRM.SVAZIO.equals(nrProtocolo)) {
			atendimentoVO.setNrProtocolo(nrProtocolo);
			atendimentoVO.getDadosProtocoloVO().setNrProtocolo(nrProtocolo);
		}

		atendimentoVO.getDadosProtocoloVO().setIdSistemaOrigemProtocolo("7");
		atendimentoVO.getDadosProtocoloVO().setDddSMSProtocolo(atendimentoVO.getNrTelefone().replaceAll("[^0-9]", ConstantesCRM.SVAZIO).substring(0, 2));
		atendimentoVO.getDadosProtocoloVO().setNrLinhaSMSProtocolo(atendimentoVO.getNrTelefone().replaceAll("^0-9", ConstantesCRM.SVAZIO).substring(2, atendimentoVO.getNrTelefone().replaceAll("^0-9", ConstantesCRM.SVAZIO).length()));

		int idTipoAberturaProtocolo = 0;
		if (parametros.getIdLinha() != null && !ConstantesCRM.SVAZIO.equals(parametros.getIdLinha()) && ("PF".equals(parametros.getInTipoPessoa()) || "PJ".equals(parametros.getInTipoPessoa()))) {
			idTipoAberturaProtocolo = IDTIPOABERTURAPROTOCOLO_LINHACLIENTE;

		} else if (parametros.getIdClienteDePara() != null && !ConstantesCRM.SVAZIO.equals(parametros.getIdClienteDePara())) {
			idTipoAberturaProtocolo = IDTIPOABERTURAPROTOCOLO_PESSOA;

		} else if (parametros.getNrConta() != null && !ConstantesCRM.SVAZIO.equals(parametros.getNrConta())) {
			idTipoAberturaProtocolo = IDTIPOABERTURAPROTOCOLO_CONTA;

		} else {
			idTipoAberturaProtocolo = IDTIPOABERTURAPROTOCOLO_LINHA;
		}

		atendimentoVO.getDadosProtocoloVO().setIdTipoAberturaProtocolo(Integer.toString(idTipoAberturaProtocolo));

		try {
			retornoRegContato = registrarContatoFacade.registrarAtendimento(user, atendimentoVO);
			String msgProtocolo = ConstantesCRM.SVAZIO;
			if (ConstantesCRM.SVAZIO.equals(nrProtocolo) || (!nrProtocolo.equals(retornoRegContato.getNrProtocolo()))) {
				request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, retornoRegContato.getNrProtocolo());
				getTiForm().setNrProtocolo(retornoRegContato.getNrProtocolo());
				if (!ConstantesCRM.SVAZIO.equals(nrProtocolo) && !nrProtocolo.equals(retornoRegContato.getNrProtocolo())) {
					msgProtocolo = "O protocolo anterior foi encerrado. O novo protocolo " + retornoRegContato.getNrProtocolo() + " foi gerado.";
				} else {
					msgProtocolo = "Protocolo " + retornoRegContato.getNrProtocolo() + " gerado.";
				}
			}
			retorno[0] = String.valueOf(retornoRegContato.getIdAtendimento());
			retorno[1] = msgProtocolo;

		} catch (Exception e) {
			retorno[0] = "[Exception]" + e.getMessage();
		}
		return retorno;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="inserirComentarioTracking.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward gravarComentarioTracking(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			//TrackingForm trkForm = (TrackingForm) formParam;
			logTelaInicial.debug("TelaInicialController:inserirComentarioTracking(" + user + ") >> INICIADO");
			ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
			user = ConstantesCRM.getCurrentUser(request.getSession());

			WFExecucao dadosComentario = WFExecucao.Factory.newInstance();
			dadosComentario.addNewScriptExecucaoVO().setIdAtendimento(getTrackingForm().getIdProcesso());
			dadosComentario.getScriptExecucaoVO().setIdAtividade("24");
			dadosComentario.getScriptExecucaoVO().setIdAtividadeMassa("24");
			dadosComentario.getScriptExecucaoVO().setIdPessoaUsuario(parametros.getIdPessoaUsuario() == null ? "21" : parametros.getIdPessoaUsuario());
			dadosComentario.addNewAtendimentoWorkflowVO().addNewAtendimentoWorkflowComumVO().setDsObservacao(request.getParameter("dsComentarioTracking"));
			dadosComentario.getAtendimentoWorkflowVO().getAtendimentoWorkflowComumVO().addNewWFGrupoVO().setIdGrupo(parametros.getIdGrupo() == null ? ConstantesCRM.SVAZIO : parametros.getIdGrupo());

			telaInicialFacadeControl.setComentarioTracking(user, dadosComentario);
			request.setAttribute("msg", "Comentário inserido com sucesso!");
			logTelaInicial.debug("TelaInicialController:gravarComentarioTracking(" + user + ") >> FINALIZADO");

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:gravarComentarioTracking(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, ConstantesCRM.TELAINCIAL);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="trackingLista.jsp"
	 * @jpf:forward name="inputDocumento" path="trackingInputDocumento.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward trackingLista(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			TrackingForm trkForm = (TrackingForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			getTrackingForm().clear();
			ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
			String idPessoaCliente = parametros.getIdPessoaCliente();
			String nrDocumento = null;
			String dsTipoDocumento = null;

			// Pesquisa realizada por CPF ou CNPJ
			if (!ConstantesCRM.SVAZIO.equals(trkForm.getDsTipoDocumento())) {
				nrDocumento = trkForm.getNrDoc().replaceAll("[^0-9]", ConstantesCRM.SVAZIO).trim();
				dsTipoDocumento = trkForm.getDsTipoDocumento().trim();
				getTrackingForm().setDsTipoDocumento(dsTipoDocumento);

			} else {
				LupaClienteVO lupaCliente = null;
				lupaCliente = telaInicialFacadeControl.getLupaClienteVO(user, idPessoaCliente, parametros.getNrLinha(), parametros.getIdContaSistemaOrigem(), (parametros.getIdTipoLinha() == null || ConstantesCRM.SVAZIO.equals(parametros.getIdTipoLinha())) ? 0 : Integer.parseInt(parametros.getIdTipoLinha()));

				boolean hasCPForCNPJ = false;
				DocumentoVO[] documentoVOArray = lupaCliente.getDadosAbaLupaCliente().getDocumentoVOArray();
				if (documentoVOArray != null && documentoVOArray.length > 0) {
					for (int i = 0; i < documentoVOArray.length; i++) {
						if (documentoVOArray[i].getTipoDocumentoVO() != null && ("CPF".equals(documentoVOArray[i].getTipoDocumentoVO().getDsTipoDocumento()) || "CNPJ".equals(documentoVOArray[i].getTipoDocumentoVO().getDsTipoDocumento()))) {
							hasCPForCNPJ = true;
							nrDocumento = documentoVOArray[i].getNrDocumento();
							dsTipoDocumento = documentoVOArray[i].getTipoDocumentoVO().getDsTipoDocumento();
							break;
						}
					}
				}

				if (!hasCPForCNPJ) {
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("inputDocumento");
				}
			}

			/*
			 * if (!this.telaInicial.getTIDocumento().getDsTipoDocumento().equals("CPF") &&
			 * !this.telaInicial.getTIDocumento().getDsTipoDocumento().equals("CNPJ")) { }
			 */

			String pageNumber = request.getParameter("pageNumber");
			if (pageNumber != null || !ConstantesCRM.SVAZIO.equals(pageNumber)) {
				try {
					Integer.parseInt(pageNumber);
				} catch (Exception e) {
					pageNumber = ConstantesCRM.SZERO;
				}
			} else {
				pageNumber = ConstantesCRM.SZERO;
			}

			TrackingAparelhosVO tracking = telaInicialFacadeControl.getAbaTrackingLista(user, idPessoaCliente, pageNumber, nrDocumento, dsTipoDocumento);
			getTrackingForm().setTpRetorno(tracking.getTpRetorno());

			if ("L".equals(tracking.getTpRetorno())) {
				ListaTrackingAparelhoVO listaTracking = tracking.getListaTrackingAparelhoVO();
				getTrackingForm().setListaTrackingAparelhoVO(listaTracking);
				getTrackingForm().setNrDoc(listaTracking.getNrDoc());
				getTrackingForm().setPageNumber(String.valueOf(listaTracking.getPaginacao().getPageNumber()));
				getTrackingForm().setHasNext(String.valueOf(listaTracking.getPaginacao().getHasNext()));

			} else if ("E".equals(tracking.getTpRetorno())) {
				getTrackingForm().clear();
				ListaTrackingAparelhoVO listaTracking = tracking.getListaTrackingAparelhoVO();
				if (listaTracking != null) {
					getTrackingForm().setListaTrackingAparelhoVO(listaTracking);
					getTrackingForm().setNrDoc(listaTracking.getNrDoc());
				}
				getTrackingForm().setTpRetorno(tracking.getTpRetorno());
				getTrackingForm().setCodError(tracking.getCodErro());
				getTrackingForm().setMsgError(tracking.getMsgErro());
				getTrackingForm().setPageNumber(ConstantesCRM.SZERO);
				getTrackingForm().setHasNext(ConstantesCRM.SZERO);
			}

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:filtroAbaServico(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="trackingDetalhe.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward trackingDetalhe(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			//TrackingForm trkForm = (TrackingForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			String nrDoc = request.getParameter("nrDoc");
			String nrPedido = request.getParameter("nrPedido");
			String idSistemaOrigem = request.getParameter("idSistemaOrigem");
			String nmSistemaOrigem = request.getParameter("nmSistemaOrigem");
			String nrOrdemVenda = request.getParameter("nrOrdemVenda");
			String nrFornecimento = request.getParameter("nrFornecimento");
			String nrPicking = request.getParameter("nrPicking");
			String nrNotaFiscal = request.getParameter("nrNotaFiscal");
			String sgUF = request.getParameter("sgUF");

			TrackingAparelhosVO tracking = telaInicialFacadeControl.getAbaTrackingDetalhe(user, nrDoc, nrPedido, idSistemaOrigem, nmSistemaOrigem, nrOrdemVenda, nrFornecimento, nrPicking, nrNotaFiscal, sgUF);
			getTrackingForm().setTrackingAparelhosVO(tracking);

			String[] regContato = getTrackingProcesso(nrPedido, request);
			String idProcesso = regContato[0];
			String msgProtocolo = regContato[1];

			if (idProcesso.substring(0, 1).equals("[")) {
				tracking.setTpRetorno("E");
				tracking.setMsgErro(idProcesso.substring((idProcesso.indexOf("]") + 1), idProcesso.length()));
			} else {
				getTrackingForm().setIdProcesso(idProcesso);
				if (!ConstantesCRM.SVAZIO.equals(msgProtocolo)) {
					request.setAttribute("msgProtocolo", msgProtocolo);
				}
			}
			getTrackingForm().setTpRetorno(tracking.getTpRetorno());

			// Detalhe
			if ("D".equals(tracking.getTpRetorno())) {
				DetalheTrackingAparelhoVO detalheTracking = tracking.getDetalheTrackingAparelhoVO();
				getTrackingForm().setDetalheTrackingAparelhoVO(detalheTracking);
				if (getTrackingForm().getDetalheTrackingAparelhoVO().getListaDadosOrdem() == null) {
					getTrackingForm().getDetalheTrackingAparelhoVO().addNewListaDadosOrdem();
				}
				if (getTrackingForm().getDetalheTrackingAparelhoVO().getListaDadosOrdem().getDadosOrdemArray() == null) {
					getTrackingForm().getDetalheTrackingAparelhoVO().getListaDadosOrdem().addNewDadosOrdem().addNewDadosEntrega();
					getTrackingForm().getDetalheTrackingAparelhoVO().getListaDadosOrdem().addNewDadosOrdem().addNewDadosEtapa();
				} else {
					int tam = getTrackingForm().getDetalheTrackingAparelhoVO().getListaDadosOrdem().getDadosOrdemArray().length;
					for (int x = 0; x < tam; x++) {
						if (getTrackingForm().getDetalheTrackingAparelhoVO().getListaDadosOrdem().getDadosOrdemArray(x).getDadosEntrega() == null) {
							getTrackingForm().getDetalheTrackingAparelhoVO().getListaDadosOrdem().getDadosOrdemArray(x).addNewDadosEntrega();
						}
						if (getTrackingForm().getDetalheTrackingAparelhoVO().getListaDadosOrdem().getDadosOrdemArray(x).getDadosEtapaArray() == null) {
							getTrackingForm().getDetalheTrackingAparelhoVO().getListaDadosOrdem().getDadosOrdemArray(x).addNewDadosEtapa();
						}
					}
				}
			} else // Erro
				if ("E".equals(tracking.getTpRetorno())) {
					DetalheTrackingAparelhoVO detalheTracking = DetalheTrackingAparelhoVO.Factory.newInstance();
					detalheTracking.addNewDadosPedido().addNewListaItens().addNewItens();
					detalheTracking.addNewListaDadosOrdem().addNewDadosOrdem().addNewDadosEntrega();
					detalheTracking.getListaDadosOrdem().getDadosOrdemArray(0).addNewDadosEtapa();
					detalheTracking.getDadosPedido().getListaItens().getItensArray();
					getTrackingForm().setDetalheTrackingAparelhoVO(detalheTracking);
					getTrackingForm().setCodError(tracking.getCodErro());
					getTrackingForm().setMsgError(StringEscapeUtils.escapeJavaScript(tracking.getMsgErro()));
				}
		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:filtroAbaServico(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="trackingComparar.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward trackingComparar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			//TrackingForm trkForm = (TrackingForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			String nrDoc = request.getParameter("nrDoc");
			String nrPedido = request.getParameter("nrPedido");
			String idSistemaOrigem = request.getParameter("idSistemaOrigem");
			String nrOrdemVenda = request.getParameter("nrOrdemVenda");
			String nrFornecimento = request.getParameter("nrFornecimento");
			String nrPicking = request.getParameter("nrPicking");
			String nrNotaFiscal = request.getParameter("nrNotaFiscal");

			TrackingAparelhosVO tracking = telaInicialFacadeControl.getAbaTrackingComparar(user, nrDoc, nrPedido, idSistemaOrigem, nrOrdemVenda, nrFornecimento, nrPicking, nrNotaFiscal);
			getTrackingForm().setTpRetorno(tracking.getTpRetorno());

			if ("C".equals(tracking.getTpRetorno())) {
				ComparaTrackingAparelhoVO comparaTracking = tracking.getComparaTrackingAparelhoVO();
				getTrackingForm().setComparaTrackingAparelhoVO(comparaTracking);

			} else if ("E".equals(tracking.getTpRetorno())) {
				getTrackingForm().setCodError(tracking.getCodErro());
				getTrackingForm().setMsgError(tracking.getMsgErro());
				getTrackingForm().setPageNumber(ConstantesCRM.SZERO);
				getTrackingForm().setHasNext(ConstantesCRM.SZERO);
			}
		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:filtroAbaServico(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/*private ListaTrackingAparelhoVO agrupaListaTracking(ListaTrackingAparelhoVO listaTracking) {
		ListaTrackingAparelhoVO listaTrackingOrdenada = ListaTrackingAparelhoVO.Factory.newInstance();
		listaTrackingOrdenada.setNmCliente(listaTracking.getNmCliente());
		listaTrackingOrdenada.setTpDoc(listaTracking.getTpDoc());
		listaTrackingOrdenada.setNrDoc(listaTracking.getNrDoc());
		listaTrackingOrdenada.setPaginacao(listaTracking.getPaginacao());
		listaTrackingOrdenada.addNewListaPedidoAparelhosVO();
		for (int i = 0; i < listaTracking.getListaPedidoAparelhosVOArray().length; i++) {
			String isOVParcial = listaTracking.getListaPedidoAparelhosVOArray(i).getIsOVParcial();
			if ("1".equals(isOVParcial) || "2".equals(isOVParcial)) {
				String dsOA = listaTracking.getListaPedidoAparelhosVOArray(i).getDsOrigem();
				String sgUA = listaTracking.getListaPedidoAparelhosVOArray(i).getSgUF();
				String dtPA = listaTracking.getListaPedidoAparelhosVOArray(i).getDtPedido();
				String nrPA = listaTracking.getListaPedidoAparelhosVOArray(i).getNrPedido();
				String nrOA = listaTracking.getListaPedidoAparelhosVOArray(i).getNrOrdemVenda();
				for (int j = 0; j < listaTrackingOrdenada.getListaPedidoAparelhosVOArray().length; j++) {
					String dsOB = listaTrackingOrdenada.getListaPedidoAparelhosVOArray(j).getDsOrigem();
					String sgUB = listaTrackingOrdenada.getListaPedidoAparelhosVOArray(j).getSgUF();
					String dtPB = listaTrackingOrdenada.getListaPedidoAparelhosVOArray(j).getDtPedido();
					String nrPB = listaTrackingOrdenada.getListaPedidoAparelhosVOArray(j).getNrPedido();
					String nrOB = listaTrackingOrdenada.getListaPedidoAparelhosVOArray(j).getNrOrdemVenda();
					if (!(dsOA.equals(dsOB) && sgUA.endsWith(sgUB) && dtPA.equals(dtPB) && nrPA.equals(nrPB) && nrOA.equals(nrOB))) {
						int pos = listaTrackingOrdenada.getListaPedidoAparelhosVOArray().length;
						listaTrackingOrdenada.insertNewListaPedidoAparelhosVO(pos);
						listaTrackingOrdenada.setListaPedidoAparelhosVOArray(pos, listaTracking.getListaPedidoAparelhosVOArray(j));
					}
				}
			} else {
				int pos = listaTrackingOrdenada.getListaPedidoAparelhosVOArray().length;
				listaTrackingOrdenada.insertNewListaPedidoAparelhosVO(pos);
				listaTrackingOrdenada.setListaPedidoAparelhosVOArray(pos, listaTracking.getListaPedidoAparelhosVOArray(i));
			}
		}
		return listaTrackingOrdenada;
	}*/

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="alterarNumeroSMS.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward alterarNumeroSMS(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		AlteracaoNumeroSMSForm form = (AlteracaoNumeroSMSForm) formParam;
		String nrLinhaSMS = ConstantesCRM.SVAZIO;
		String action = (request.getParameter("action") != null) ? request.getParameter("action") : ConstantesCRM.SVAZIO;

		String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		ParametrosVO parametrosVO = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);

		try {
			if (ConstantesCRM.SVAZIO.equals(action)) {
				boolean protocoloInvalido = false;
				String idCliente = parametrosVO.getIdPessoaCliente();
				String nrProtocolo = request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) != null ? (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) : ConstantesCRM.SVAZIO;

				GetDadosProtocoloOutTO out = protocoloFacade.getDadosProtocolo(user, parametrosVO.getNrLinha() != null ? parametrosVO.getNrLinha() : ConstantesCRM.SVAZIO, nrProtocolo, Integer.toString(ID_SISTEMA_ORIGEM_VIVONET));

				if (out != null) {
					String nrLinha = out.getCdAreaRegistro() != null ? out.getCdAreaRegistro() : ConstantesCRM.SVAZIO;
					nrLinha += out.getNrTelefone() != null ? out.getNrTelefone() : ConstantesCRM.SVAZIO;
					nrLinhaSMS = out.getCdAreaRegistroSMS() != null ? out.getCdAreaRegistroSMS() : ConstantesCRM.SVAZIO;
					nrLinhaSMS += out.getNrTelefoneSMS() != null ? out.getNrTelefoneSMS() : ConstantesCRM.SVAZIO;
					nrLinhaSMS = ConstantesCRM.formatPhoneNumber(nrLinhaSMS);

					String idPesDePara = parametrosVO.getIdClienteDePara() != null ? parametrosVO.getIdClienteDePara() : ConstantesCRM.SVAZIO;

					if (!nrLinha.equals(parametrosVO.getNrLinha()) && (Integer.toString(IDTIPOABERTURAPROTOCOLO_LINHACLIENTE).equals(out.getIdTipoAberturaProtocolo()) || Integer.toString(IDTIPOABERTURAPROTOCOLO_LINHA).equals(out.getIdTipoAberturaProtocolo()))) {
						protocoloInvalido = true;
					}
					if (!idPesDePara.equals(out.getIdPessoaDePara()) && Integer.parseInt(idPesDePara) != idPessoaProspect) {
						protocoloInvalido = true;
					}
				}

				if (ConstantesCRM.SSIX.equals(parametrosVO.getIdTipoRelacionamento())) {
					getAlteracaoNumeroSMSForm().setProspect(true);
					getAlteracaoNumeroSMSForm().setNrLinhaSMS(nrLinhaSMS);

				} else {
					getAlteracaoNumeroSMSForm().setProspect(false);
					LupaFaturamentoPosVO lupaFaturamentoPosVO = LupaFaturamentoPosVO.Factory.newInstance();
					lupaFaturamentoPosVO = detalheFaturaFacade.buscaContaLinha(lupaFaturamentoPosVO, idCliente, idUsuario);
					getAlteracaoNumeroSMSForm().setListaContas(lupaFaturamentoPosVO.getLFContasFaturamentoArray());
					getAlteracaoNumeroSMSForm().setNrLinhaSMS(nrLinhaSMS);
				}

				// Se o número de protocolo for inválido um novo será gerado
				if (protocoloInvalido) {
					AbreProtocoloTO abreProtocoloTO = AbreProtocoloTO.Factory.newInstance();
					//String msg = ConstantesCRM.SVAZIO;
					String nrLinha = parametrosVO.getNrLinha();
					int idTipoAberturaProtocolo = Integer.parseInt(out.getIdTipoAberturaProtocolo());

					switch (idTipoAberturaProtocolo) {
					case 1: // Conta
						abreProtocoloTO.setCdConta(out.getCdConta());
						break;
					case 2: // Linha
						abreProtocoloTO.setCdAreaRegistro(nrLinha.substring(0, 2));
						abreProtocoloTO.setNrTelefone(nrLinha.substring(2));
						break;
					case 3: // Linha de cliente
						abreProtocoloTO.setIdPessoaDePara(out.getIdPessoaDePara());
						abreProtocoloTO.setCdAreaRegistro(nrLinha.substring(0, 2));
						abreProtocoloTO.setNrTelefone(nrLinha.substring(2));
						break;
					case 4: // Pessoa
						abreProtocoloTO.setIdPessoaDePara(parametrosVO.getIdClienteDePara());
						break;
					}

					abreProtocoloTO.setIdSistemaOrigem(Integer.toString(ID_SISTEMA_ORIGEM_VIVONET));
					abreProtocoloTO.setIdTipoAberturaProtocolo(out.getIdTipoAberturaProtocolo());
					abreProtocoloTO.setCdAreaRegistroSMS(nrLinha.substring(0, 2));
					abreProtocoloTO.setNrTelefoneSMS(nrLinha.substring(2));
					AbreProtocoloOutTO abreProtocoloOutTO = protocoloFacade.setAbreValidaProtocolo(user, abreProtocoloTO);

					if (ConstantesCRM.SZERO.equals(abreProtocoloOutTO.getCdError())) {
						request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, abreProtocoloOutTO.getNrProtocolo());
						parametrosVO.setNrProtocolo(abreProtocoloOutTO.getNrProtocolo());
						getTiForm().setProtocoloScreenPop(false);
						request.setAttribute("msgNovoProtocolo", "O número de protocolo informado é inválido. Um novo protocolo - " + abreProtocoloOutTO.getNrProtocolo() + " - foi gerado.");
					}
				}

			} else if ("getListaLinhas".equals(action)) {
				int indexConta = Integer.parseInt(request.getParameter("indexConta"));
				XmlOptions xmlOptions = new XmlOptions();
				xmlOptions.setSaveAggresiveNamespaces();

				response.setContentType(ConstantesCRM.SContentType);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
				//String xmlRetorno = getAlteracaoNumeroSMSForm().getListaContas()[indexConta].xmlText(xmlOptions);
				bufferedOutputStream.write(ClienteUtils.getCleanXMLFromXSD(getAlteracaoNumeroSMSForm().getListaContas()[indexConta], null).getBytes(ConstantesCRM.SISO));
				bufferedOutputStream.flush();
				bufferedOutputStream.close();
				return null;

			} else if ("searchLinhas".equals(action)) {
				int indexConta = Integer.parseInt(request.getParameter("indexConta"));
				int contador = 0;
				int contadorAtual = 0;
				String sNeedle = request.getParameter("query").replaceAll("[^0-9]", ConstantesCRM.SVAZIO);
				String format = request.getParameter("format");
				String nrTel;
				StringBuffer out = new StringBuffer();

				LFLinhas[] listaLinhas = getAlteracaoNumeroSMSForm().getListaContas()[indexConta].getLFLinhasArray();
				for (int i = 0; i < listaLinhas.length; i++) {
					nrTel = listaLinhas[i].getNrCodArea() + listaLinhas[i].getNrLinha();
					if (nrTel.indexOf(sNeedle) >= 0) {
						contador++;
					}
				}

				if ("JSON".equalsIgnoreCase(format)) {
					out.append("{");
					out.append("query:'").append(sNeedle).append("',");
					out.append("suggestions:[");
					for (int i = 0; i < listaLinhas.length; i++) {
						nrTel = listaLinhas[i].getNrCodArea() + listaLinhas[i].getNrLinha();
						if (nrTel.indexOf(sNeedle) >= 0) {
							out.append("'").append(getFormattedTelNumber(listaLinhas[i])).append("'");
							if (contadorAtual < contador - 1) {
								out.append(",");
							}
							contadorAtual++;
						}
					}
					out.append("],").append("data:[");

					contadorAtual = 0;
					for (int i = 0; i < listaLinhas.length; i++) {
						nrTel = listaLinhas[i].getNrCodArea() + listaLinhas[i].getNrLinha();
						if (nrTel.indexOf(sNeedle) >= 0) {
							out.append(i);
							if (contadorAtual < contador - 1) {
								out.append(",");
							}
							contadorAtual++;
						}
					}
					out.append("]").append("}");

				} else if ("XML".equalsIgnoreCase(format)) {
					out = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\" ?><results>");
					for (int i = 0; i < listaLinhas.length; i++) {
						nrTel = listaLinhas[i].getNrCodArea() + listaLinhas[i].getNrLinha();
						if (nrTel.indexOf(sNeedle) >= 0) {
							out.append("<rs id=\"ID do objeto\" info=\"Informacao\">").append(getFormattedTelNumber(listaLinhas[i])).append("</rs>");
						}
					}
					out.append("</results>");
				}
				response.setContentType(ConstantesCRM.SContentType);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
				bufferedOutputStream.write(out.toString().getBytes(ConstantesCRM.SISO));
				bufferedOutputStream.flush();
				bufferedOutputStream.close();
				return null;

			} else if ("setAlterarNumeroSMS".equals(action)) {
				StringBuffer out = new StringBuffer();
				try {
					AlterProtocoloTO alterProtocoloTO = AlterProtocoloTO.Factory.newInstance();
					String nrProtocolo = (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO);
					if (getAlteracaoNumeroSMSForm().isProspect()) {
						String nrTelefone = form.getNrLinha().replaceAll("[^0-9]", ConstantesCRM.SVAZIO);
						alterProtocoloTO.setCdAreaRegistroSMS(nrTelefone.substring(0, 2));
						alterProtocoloTO.setNrTelefoneSMS(nrTelefone.substring(2, nrTelefone.length()));

					} else {
						int indexConta = Integer.parseInt(request.getParameter("indexConta"));
						int indexLinha = Integer.parseInt(request.getParameter("indexLinha"));
						alterProtocoloTO.setCdAreaRegistroSMS(getAlteracaoNumeroSMSForm().getListaContas()[indexConta].getLFLinhasArray(indexLinha).getNrCodArea().replaceAll("[^0-9]", ConstantesCRM.SVAZIO));
						alterProtocoloTO.setNrTelefoneSMS(getAlteracaoNumeroSMSForm().getListaContas()[indexConta].getLFLinhasArray(indexLinha).getNrLinha().replaceAll("[^0-9]", ConstantesCRM.SVAZIO));
					}
					AlterProtocoloOutTO alterProtocoloOutTO;
					alterProtocoloOutTO = protocoloFacade.setAlteracaoProtocolo(idUsuario, alterProtocoloTO, "alterar", nrProtocolo, 0, 0);
					out.append(ClienteUtils.getCleanXMLFromXSD(alterProtocoloOutTO, null));

				} catch (Exception e) {
					response.setStatus(AjaxError.STATUS_CODE_ERROR, AjaxError.MSG_ERRO);
					response.setHeader("stackTrace", e.getLocalizedMessage());
					return null;
				}
				response.setContentType(ConstantesCRM.SContentType);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
				bufferedOutputStream.write(out.toString().getBytes(ConstantesCRM.SISO));
				bufferedOutputStream.flush();
				bufferedOutputStream.close();
				return null;
			}

		} catch (Exception e) {
			logTelaInicial.error("TelaInicialController:alterarNumeroSMS(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="historicoPortabilidade.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward getHistoricoPortabilidade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//TIForm form = (TIForm) formParam;
		String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		ParametrosVO parametrosVO = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
		PortabilidadeVO portabilidadeVO = PortabilidadeVO.Factory.newInstance();

		try {
			portabilidadeVO.setCdAreaRegistro(Integer.parseInt(parametrosVO.getNrLinha().replaceAll("[^0-9]", ConstantesCRM.SVAZIO).substring(0, 2)));
			portabilidadeVO.setNrLinha(Integer.parseInt(parametrosVO.getNrLinha().replaceAll("[^0-9]", ConstantesCRM.SVAZIO).substring(2, parametrosVO.getNrLinha().replaceAll("[^0-9]", ConstantesCRM.SVAZIO).length())));

			portabilidadeVO = telaInicialFacadeControl.getPortabilidadeVO(idUsuario, portabilidadeVO);
			getTiForm().setPortabilidade(portabilidadeVO);

		} catch (Exception e) {
			request.setAttribute("msgErro", MENSAGEM_GERAL);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 */
	public ActionForward abrirProtocolo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//TIForm form = (TIForm) formParam;
		StringBuffer xmlDadosTI = new StringBuffer();
		String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		Boolean hasNrTelefone = null;
		XmlOptions xmlOptions = new XmlOptions();
		AbreProtocoloOutTO abreProtocoloOutTO;
		ParametrosVO parametrosVO = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);

		try {
			AbreProtocoloTO abreProtocoloTO = AbreProtocoloTO.Factory.newInstance();
			abreProtocoloTO.setIdSistemaOrigem(Integer.toString(ID_SISTEMA_ORIGEM_VIVONET));

			// Prospect
			if (ConstantesCRM.SSIX.equals(parametrosVO.getIdTipoRelacionamento())) {
				// Verifica se prospect possui algum contato telefonico
				ComunicacaoVO[] contato = telaInicialFacadeControl.getContato(idUsuario, ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaCliente()).getDadosAbaLupaCliente().getComunicacaoVOArray();
				hasNrTelefone = new Boolean(false);
				for (int i = 0; i < contato.length; i++) {
					if ("TELEFONE".equalsIgnoreCase(contato[i].getTipoComunicacaoVO().getSgClassificacao()) || "SMS".equalsIgnoreCase(contato[i].getTipoComunicacaoVO().getSgClassificacao())) {
						hasNrTelefone = new Boolean(true);
						abreProtocoloTO.setCdAreaRegistro(contato[i].getDsContato().replaceAll("[^0-9]", ConstantesCRM.SVAZIO).substring(0, 2));
						abreProtocoloTO.setNrTelefone(contato[i].getDsContato().replaceAll("[^0-9]", ConstantesCRM.SVAZIO).substring(2, contato[i].getDsContato().replaceAll("[^0-9]", ConstantesCRM.SVAZIO).length()));
						abreProtocoloTO.setCdAreaRegistroSMS(abreProtocoloTO.getCdAreaRegistro());
						abreProtocoloTO.setNrTelefoneSMS(abreProtocoloTO.getNrTelefone());
						break;
					}
				}

				if (hasNrTelefone != null && hasNrTelefone.booleanValue() == false) {
					throw new Exception();
				}

				abreProtocoloTO.setIdTipoAberturaProtocolo(ConstantesCRM.SFOUR);
				abreProtocoloTO.setIdPessoaDePara(parametrosVO.getIdClienteDePara());

			} else {
				abreProtocoloTO.setIdTipoAberturaProtocolo(ConstantesCRM.STHREE);
				abreProtocoloTO.setCdAreaRegistro(parametrosVO.getNrLinha().substring(0, 2));
				abreProtocoloTO.setNrTelefone(parametrosVO.getNrLinha().substring(2, parametrosVO.getNrLinha().length()));
			}

			abreProtocoloOutTO = protocoloFacade.setAbreValidaProtocolo(idUsuario, abreProtocoloTO);
			if (ConstantesCRM.SZERO.equals(abreProtocoloOutTO.getCdError())) {
				request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, abreProtocoloOutTO.getNrProtocolo());
				parametrosVO.setNrProtocolo(abreProtocoloOutTO.getNrProtocolo());
				getTiForm().setProtocoloScreenPop(false);
			}

			xmlOptions.setSaveAggresiveNamespaces();
			xmlDadosTI.append(ClienteUtils.getCleanXMLFromXSD(abreProtocoloOutTO, null));

		} catch (Exception e) {
			if (hasNrTelefone != null && hasNrTelefone.booleanValue() == false && ConstantesCRM.SSIX.equals(parametrosVO.getIdTipoRelacionamento())) {
				xmlDadosTI = new StringBuffer();
				abreProtocoloOutTO = AbreProtocoloOutTO.Factory.newInstance();
				abreProtocoloOutTO.setCdError(ConstantesCRM.SONE);
				abreProtocoloOutTO.setMsgError("Não é possível abrir protocolo de atendimento. Por favor, cadastre um contato telefônico para envio de SMS.");
				xmlOptions.setSaveAggresiveNamespaces();
				xmlDadosTI.append(ClienteUtils.getCleanXMLFromXSD(abreProtocoloOutTO, null));

			} else {
				logTelaInicial.error("TelaInicialController:abrirProtocolo(" + user + ") - [" + e.getMessage() + "]", e);
				response.setStatus(AjaxError.STATUS_CODE_ERROR, AjaxError.MSG_ERRO);
				response.setHeader("stackTrace", "Não foi possível abrir um protocolo.");
				return null;
			}
		}

		response.setContentType(ConstantesCRM.SContentType);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
		bufferedOutputStream.write(xmlDadosTI.toString().getBytes(ConstantesCRM.SISO));
		bufferedOutputStream.flush();
		bufferedOutputStream.close();
		logTelaInicial.debug("TelaInicialController:abrirProtocolo(" + idUsuario + ") >> FINALIZADO");
		return null;
	}

	private ParametrosVO getParametrosVO(HttpServletRequest request) {
		ParametrosVO parametrosVO = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
		String nrProtocolo = (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO);
		parametrosVO.setNrProtocolo(nrProtocolo != null ? nrProtocolo : ConstantesCRM.SVAZIO);
		return parametrosVO;
	}

	private String getFormattedTelNumber(LFLinhas lFLinhas) {
		return '(' + lFLinhas.getNrCodArea() + ")" + lFLinhas.getNrLinha().substring(0, 4) + "-" + lFLinhas.getNrLinha().substring(4);
	}

	/**
	 * Objetivo: Formatar data no formato yyyy-mm-dd hh:mm:ss
	 * 
	 */
	protected String formataData(String data) {
		if (data != null && !equals("")) {
			SimpleDateFormat dateFormat = null;
			try {
				dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date dtUtil = new Date(data);
				data = dateFormat.format(dtUtil);
			} catch (Exception e) {
				logTelaInicial.error("[TelaInicialController.formataData] Erro ao formatar data dd/MM/yyyy " + data);
				logTelaInicial.warn("Tentando Formatar a Data formato: yyyy-mm-dd hh:mm:ss " + data);
				try {
					dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
					Date dtUtil = new Date(data);
					data = dateFormat.format(dtUtil);
				}catch (Exception ex) {
					logTelaInicial.error("[TelaInicialController.formataData] Erro ao formatar data yyyy-mm-dd hh:mm:ss " + data);
				}
			}
		}
		return data;
	}
	
	public static class AlteracaoNumeroSMSForm extends ActionForm {

		private static final long serialVersionUID = 1L;
		private String nrLinha;
		private String nrLinhaPesquisa;
		private String nrLinhaSMS;
		private int idConta;
		private LFContasFaturamento[] listaContas;
		private LFLinhas[] listaLinhas;
		private boolean prospect = false;

		public AlteracaoNumeroSMSForm() {
		}

		public String getNrLinha() {
			if (this.nrLinha == null) {
				this.nrLinha = ConstantesCRM.SVAZIO;
			}
			return this.nrLinha;
		}

		public void setNrLinha(String arg) {
			this.nrLinha = arg;
		}

		public String getNrLinhaSMS() {
			if (this.nrLinhaSMS == null) {
				this.nrLinhaSMS = ConstantesCRM.SVAZIO;
			}
			return this.nrLinhaSMS;
		}

		public void setNrLinhaSMS(String arg) {
			this.nrLinhaSMS = arg;
		}

		public String getNrLinhaPesquisa() {
			if (this.nrLinhaPesquisa == null) {
				this.nrLinhaPesquisa = ConstantesCRM.SVAZIO;
			}
			return this.nrLinhaPesquisa;
		}

		public void setNrLinhaPesquisa(String arg) {
			this.nrLinhaPesquisa = arg;
		}

		public int getIdConta() {
			return this.idConta;
		}

		public void setIdConta(int arg) {
			this.idConta = arg;
		}

		public LFContasFaturamento[] getListaContas() {
			if (listaContas == null) {
				this.listaContas = new LFContasFaturamento[0];
			}
			return this.listaContas;
		}

		public void setListaContas(LFContasFaturamento[] arg) {
			this.listaContas = arg;
		}

		public LFLinhas[] getListaLinhas() {
			if (listaLinhas == null) {
				this.listaLinhas = new LFLinhas[0];
			}
			return this.listaLinhas;
		}

		public void setListaLinhas(LFLinhas[] arg) {
			this.listaLinhas = arg;
		}

		public boolean isProspect() {
			return this.prospect;
		}

		public void setProspect(boolean arg) {
			this.prospect = arg;
		}
	}

	/**
	 * ActionForm get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class TrackingForm extends ActionForm {

		private static final long serialVersionUID = 1L;
		private TrackingAparelhosVO trackingAparelhosVO;
		private ListaTrackingAparelhoVO listaTrackingAparelhoVO;
		private DetalheTrackingAparelhoVO detalheTrackingAparelhoVO;
		private ComparaTrackingAparelhoVO comparaTrackingAparelhoVO;
		private String pageNumber;
		private String hasNext;
		private String nrDoc;
		private String tpRetorno;
		private String msgError;
		private String codError;
		private String idProcesso;
		//private String nmNome;
		private String dsTipoDocumento;

		public void clear() {
			trackingAparelhosVO = TrackingAparelhosVO.Factory.newInstance();
			listaTrackingAparelhoVO = ListaTrackingAparelhoVO.Factory.newInstance();
			detalheTrackingAparelhoVO = DetalheTrackingAparelhoVO.Factory.newInstance();
			comparaTrackingAparelhoVO = ComparaTrackingAparelhoVO.Factory.newInstance();
			pageNumber = ConstantesCRM.SVAZIO;
			hasNext = ConstantesCRM.SVAZIO;
			nrDoc = ConstantesCRM.SVAZIO;
			msgError = ConstantesCRM.SVAZIO;
			codError = ConstantesCRM.SVAZIO;
			idProcesso = ConstantesCRM.SVAZIO;
			//nmNome = ConstantesCRM.SVAZIO;
			dsTipoDocumento = ConstantesCRM.SVAZIO;
		}

		public TrackingForm() {
			clear();
			/*
			 * trackingAparelhosVO = TrackingAparelhosVO.Factory.newInstance(); listaTrackingAparelhoVO =
			 * ListaTrackingAparelhoVO.Factory.newInstance(); detalheTrackingAparelhoVO =
			 * DetalheTrackingAparelhoVO.Factory.newInstance(); comparaTrackingAparelhoVO =
			 * ComparaTrackingAparelhoVO.Factory.newInstance(); pageNumber = ConstantesCRM.SVAZIO; hasNext =
			 * ConstantesCRM.SVAZIO; nrDoc = ConstantesCRM.SVAZIO; idProcesso = ConstantesCRM.SVAZIO; nmNome =
			 * ConstantesCRM.SVAZIO;
			 */
		}

		public void setTrackingAparelhosVO(TrackingAparelhosVO vo) {
			this.trackingAparelhosVO = vo;
		}

		public TrackingAparelhosVO getTrackingAparelhosVO() {
			return this.trackingAparelhosVO;
		}

		public void setListaTrackingAparelhoVO(ListaTrackingAparelhoVO vo) {
			this.listaTrackingAparelhoVO = vo;
		}

		public ListaTrackingAparelhoVO getListaTrackingAparelhoVO() {
			return this.listaTrackingAparelhoVO;
		}

		public void setDetalheTrackingAparelhoVO(DetalheTrackingAparelhoVO vo) {
			this.detalheTrackingAparelhoVO = vo;
		}

		public DetalheTrackingAparelhoVO getDetalheTrackingAparelhoVO() {
			return this.detalheTrackingAparelhoVO;
		}

		public void setComparaTrackingAparelhoVO(ComparaTrackingAparelhoVO vo) {
			this.comparaTrackingAparelhoVO = vo;
		}

		public ComparaTrackingAparelhoVO getComparaTrackingAparelhoVO() {
			return this.comparaTrackingAparelhoVO;
		}

		public void setPageNumber(String valor) {
			this.pageNumber = valor;
		}

		public String getPageNumber() {
			return this.pageNumber;
		}

		public void setHasNext(String valor) {
			this.hasNext = valor;
		}

		public String getHasNext() {
			return this.hasNext;
		}

		public void setNrDoc(String valor) {
			this.nrDoc = valor;
		}

		public String getNrDoc() {
			if (this.nrDoc == null) {
				this.nrDoc = ConstantesCRM.SVAZIO;
			}
			return this.nrDoc;
		}

		public void setDsTipoDocumento(String arg) {
			this.dsTipoDocumento = arg;
		}

		public String getDsTipoDocumento() {
			if (this.dsTipoDocumento == null) {
				this.dsTipoDocumento = ConstantesCRM.SVAZIO;
			}
			return this.dsTipoDocumento;
		}

		public void setMsgError(String valor) {
			this.msgError = valor;
		}

		public String getMsgError() {
			return this.msgError;
		}

		public void setCodError(String valor) {
			this.codError = valor;
		}

		public String getCodError() {
			return this.codError;
		}

		public void setIdProcesso(String valor) {
			this.idProcesso = valor;
		}

		public String getIdProcesso() {
			return this.idProcesso;
		}

		public void setTpRetorno(String valor) {
			this.tpRetorno = valor;
		}

		public String getTpRetorno() {
			return this.tpRetorno;
		}
	}

	/**
	 * ActionForm get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class PesquisaForm extends ActionForm {

		private static final long serialVersionUID = 1L;
		private String inError;
		//private String inErro;
		private String tipoPesquisa = ConstantesCRM.SVAZIO;
		private String nmMeio;
		private String sobrenome;
		private String nome;
		private PesquisaTIVO listaLinhas;
		private PesquisaTIVO listaClientes;
		private String pageNumber;
		private String hasNext;

		public PesquisaForm() {
			listaLinhas = PesquisaTIVO.Factory.newInstance();
			listaClientes = PesquisaTIVO.Factory.newInstance();
		}

		public void setListaClientes(PesquisaTIVO listaClientes) {
			this.listaClientes = listaClientes;
		}

		public PesquisaTIVO getListaClientes() {
			return this.listaClientes;
		}

		public void setListaLinhas(PesquisaTIVO listaLinhas) {
			this.listaLinhas = listaLinhas;
		}

		public PesquisaTIVO getListaLinhas() {
			return this.listaLinhas;
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

		public void setNmMeio(String nmMeio) {
			this.nmMeio = nmMeio;
		}

		public String getNmMeio() {
			return this.nmMeio;
		}

		public void setTipoPesquisa(String tipoPesquisa) {
			this.tipoPesquisa = tipoPesquisa;
		}

		public String getTipoPesquisa() {
			return this.tipoPesquisa;
		}

		public void setInError(String inError) {
			this.inError = inError;
		}

		public String getInError() {
			return this.inError;
		}

		public void setPageNumber(String pageNumber) {
			this.pageNumber = pageNumber;
		}

		public String getPageNumber() {
			return this.pageNumber;
		}

		public void setHasNext(String hasNext) {
			this.hasNext = hasNext;
		}

		public String getHasNext() {
			return this.hasNext;
		}
	}

	/**
	 * ActionForm get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class CarteiraPFForm extends ActionForm {

		private static final long serialVersionUID = 1L;
		private String inVazio = ConstantesCRM.SVAZIO;
		private LupaCarteirizacaoPFVO lupaCarteira;

		public CarteiraPFForm() {
			lupaCarteira = LupaCarteirizacaoPFVO.Factory.newInstance();
			lupaCarteira.addNewTipoRelacionamento();
		}

		public void setLupaCarteira(LupaCarteirizacaoPFVO lupaCarteira) {
			this.lupaCarteira = lupaCarteira;
		}

		public LupaCarteirizacaoPFVO getLupaCarteira() {
			return this.lupaCarteira;
		}

		public void setInVazio(String inVazio) {
			this.inVazio = inVazio;
		}

		public String getInVazio() {
			return this.inVazio;
		}
	}

	/**
	 * ActionForm get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class CarteiraPJForm extends ActionForm {

		private static final long serialVersionUID = 1L;
		private String inVazio = ConstantesCRM.SVAZIO;
		private LupaCarteirizacaoPJVO lupaCarteiraPJ;
		private LupaCarteirizacaoPFVO[] listaPF = new LupaCarteirizacaoPFVO[0];

		public CarteiraPJForm() {
			lupaCarteiraPJ = LupaCarteirizacaoPJVO.Factory.newInstance();
			lupaCarteiraPJ.addNewLupaCarteirizacaoPFVO();
			lupaCarteiraPJ.setLupaCarteirizacaoPFVOArray(listaPF);
		}

		public void setLupaCarteiraPJ(LupaCarteirizacaoPJVO lupaCarteiraPJ) {
			this.lupaCarteiraPJ = lupaCarteiraPJ;
		}

		public LupaCarteirizacaoPJVO getLupaCarteiraPJ() {
			return this.lupaCarteiraPJ;
		}

		public void setListaPF(LupaCarteirizacaoPFVO[] listaPF) {
			this.listaPF = listaPF;
		}

		public LupaCarteirizacaoPFVO[] getListaPF() {
			return this.listaPF;
		}

		public void setInVazio(String inVazio) {
			this.inVazio = inVazio;
		}

		public String getInVazio() {
			return this.inVazio;
		}
	}

	/**
	 * ActionForm get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class SegmentacaoForm extends ActionForm {

		private static final long serialVersionUID = 1L;
		private LupaSegmentacaoVO.ListaSegmentacao[] listaSegmentacao = new LupaSegmentacaoVO.ListaSegmentacao[0];
		private LupaSegmentacaoVO segmentacao;

		public SegmentacaoForm() {
			segmentacao = LupaSegmentacaoVO.Factory.newInstance();
			segmentacao.addNewListaSegmentacao();
			segmentacao.setListaSegmentacaoArray(listaSegmentacao);
		}

		public void setSegmentacao(LupaSegmentacaoVO segmentacao) {
			this.segmentacao = segmentacao;
		}

		public LupaSegmentacaoVO getSegmentacao() {
			return this.segmentacao;
		}

		public void setListaSegmentacao(ListaSegmentacao[] listaSegmentacao) {
			this.listaSegmentacao = listaSegmentacao;
		}

		public ListaSegmentacao[] getListaSegmentacao() {
			return this.listaSegmentacao;
		}
	}

	/**
	 * ActionForm get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class LupaLinhaForm extends ActionForm {

		private static final long serialVersionUID = 1L;
		private String inProxima;
		private String formTotalPagina;
		private String formPaginaAtual;
		private String erro;
		private String formNrLinhaFiltroAte;
		//private String formIDLinhaFiltroAte;
		private String formQtLinhasRetornadas;
		private String formQtLinhasLocalizadas;
		private DadosLupaLinha formDadosLupaLinha;
		private ListaDadosLupaLinha[] formListaDadosLupaLinha;
		private String formDtUltimaAtualizacaoFiltro;
		private String formInDivulgaNumeroFiltro;
		private String formIDLinhaFiltro;
		private String formIDPessoaFiltro;
		private String formNrLinhaFiltro;
		private StatusLinhaVO[] formStatusLinhaVO;
		private String formIDStatusLinhaFiltro;
		private TipoLinhaVO[] formTipoLinhaVO;
		private String formIDTipoLinhaFiltro;
		private DetalheLinhaVO formDetalheLinhaVO;
		private ServicoVO formServicoVO;
		private FavoritosVO formFavoritosVO;
		private PromocoesVO formPromocoesVO;
		private String formIDTipoLinha;
		private ResultadoLinhaPUPTO formLinhaPUPTO;
		private int qtdeDiasExpiracaoPUP = 0;
		private String cdPermissao;
		private String inAceitacaoPermissao;
		private String qtdLinhasTotal = ConstantesCRM.SVAZIO;
		private String qtdLinhasAtivas = ConstantesCRM.SVAZIO;
		private String qtdLinhasCanceladas = ConstantesCRM.SVAZIO;
		private String urlAparelhoWebService = ConstantesCRM.SVAZIO;
		private Produto modeloAparelho;
		private AparelhoLinha[] historicoAparelho;
		private String erroAPI;
		private ReducaoVelocidadeItem reducaoVelocidadeItem1005;
		private ReducaoVelocidadeItem reducaoVelocidadeItem1006;

		public LupaLinhaForm() {
			formQtLinhasRetornadas = ConstantesCRM.SVAZIO;
			formQtLinhasLocalizadas = ConstantesCRM.SVAZIO;
			formDadosLupaLinha = DadosLupaLinha.Factory.newInstance();
			formListaDadosLupaLinha = new ListaDadosLupaLinha[0];
			formDtUltimaAtualizacaoFiltro = ConstantesCRM.SVAZIO;
			formInDivulgaNumeroFiltro = ConstantesCRM.SVAZIO;
			formIDLinhaFiltro = ConstantesCRM.SVAZIO;
			//formIDLinhaFiltroAte = ConstantesCRM.SVAZIO;
			formIDPessoaFiltro = ConstantesCRM.SVAZIO;
			formNrLinhaFiltro = ConstantesCRM.SVAZIO;
			formStatusLinhaVO = new StatusLinhaVO[0];
			formIDStatusLinhaFiltro = ConstantesCRM.SVAZIO;
			formTipoLinhaVO = new TipoLinhaVO[0];
			formIDTipoLinhaFiltro = ConstantesCRM.SVAZIO;
			formDetalheLinhaVO = DetalheLinhaVO.Factory.newInstance();
			formServicoVO = ServicoVO.Factory.newInstance();
			formFavoritosVO = FavoritosVO.Factory.newInstance();
			formPromocoesVO = PromocoesVO.Factory.newInstance();
			formIDTipoLinha = ConstantesCRM.SVAZIO;
		}

		public String getQtdLinhasTotal() {
			return this.qtdLinhasTotal;
		}

		public void setQtdLinhasTotal(String arg) {
			this.qtdLinhasTotal = arg;
		}

		public String getQtdLinhasAtivas() {
			return this.qtdLinhasAtivas;
		}

		public void setQtdLinhasAtivas(String arg) {
			this.qtdLinhasAtivas = arg;
		}

		public String getQtdLinhasCanceladas() {
			return this.qtdLinhasCanceladas;
		}

		public void setQtdLinhasCanceladas(String arg) {
			this.qtdLinhasCanceladas = arg;
		}

		public String getUrlAparelhoWebService() {
			return this.urlAparelhoWebService;
		}

		public void setUrlAparelhoWebService(String arg) {
			this.urlAparelhoWebService = arg;
		}

		public AparelhoLinha[] getHistoricoAparelho() {
			if (this.historicoAparelho == null) {
				this.historicoAparelho = new AparelhoLinha[0];
			}
			return this.historicoAparelho;
		}

		public void setHistoricoAparelho(AparelhoLinha[] arg) {
			this.historicoAparelho = arg;
		}

		public String getErroAPI() {
			if (this.erroAPI == null) {
				this.erroAPI = ConstantesCRM.SVAZIO;
			}
			return this.erroAPI;
		}

		public void setErroAPI(String arg) {
			this.erroAPI = arg;
		}

		public Produto getModeloAparelho() {
			if (this.modeloAparelho == null) {
				this.modeloAparelho = new Produto();
			}
			return this.modeloAparelho;
		}

		public void setModeloAparelho(Produto arg) {
			this.modeloAparelho = arg;
		}

		public void setFormLinhaPUPTO(ResultadoLinhaPUPTO formLinhaPUPTO) {
			this.formLinhaPUPTO = formLinhaPUPTO;
		}

		public ResultadoLinhaPUPTO getFormLinhaPUPTO() {
			if (this.formLinhaPUPTO == null) {
				this.formLinhaPUPTO = new ResultadoLinhaPUPTO();
			}
			return this.formLinhaPUPTO;
		}

		public void setFormDetalheLinhaVO(DetalheLinhaVO formDetalheLinhaVO) {
			this.formDetalheLinhaVO = formDetalheLinhaVO;
		}

		public DetalheLinhaVO getFormDetalheLinhaVO() {
			return this.formDetalheLinhaVO;
		}

		public void setQtdeDiasExpiracaoPUP(int qtdeDiasExpiracaoPUP) {
			this.qtdeDiasExpiracaoPUP = qtdeDiasExpiracaoPUP;
		}

		public int getQtdeDiasExpiracaoPUP() {
			return this.qtdeDiasExpiracaoPUP;
		}

		public void setFormFavoritosVO(FavoritosVO formFavoritosVO) {
			this.formFavoritosVO = formFavoritosVO;
		}

		public FavoritosVO getFormFavoritosVO() {
			return this.formFavoritosVO;
		}

		public void setFormPromocoesVO(PromocoesVO formPromocoesVO) {
			this.formPromocoesVO = formPromocoesVO;
		}

		public PromocoesVO getFormPromocoesVO() {
			return this.formPromocoesVO;
		}

		public void setFormIDTipoLinhaFiltro(String formIDTipoLinhaFiltro) {
			this.formIDTipoLinhaFiltro = formIDTipoLinhaFiltro;
		}

		public String getFormIDTipoLinhaFiltro() {
			return this.formIDTipoLinhaFiltro;
		}

		public void setFormTipoLinhaVO(TipoLinhaVO[] formTipoLinhaVO) {
			this.formTipoLinhaVO = formTipoLinhaVO;
		}

		public void setFormIDTipoLinha(String formIDTipoLinha) {
			this.formIDTipoLinha = formIDTipoLinha;
		}

		public String getFormIDTipoLinha() {
			return this.formIDTipoLinha;
		}

		public void setFormServicoVO(ServicoVO formServicoVO) {
			this.formServicoVO = formServicoVO;
		}

		public ServicoVO getFormServicoVO() {
			return this.formServicoVO;
		}

		public TipoLinhaVO[] getFormTipoLinhaVO() {
			return this.formTipoLinhaVO;
		}

		public void setFormIDStatusLinhaFiltro(String formIDStatusLinhaFiltro) {
			this.formIDStatusLinhaFiltro = formIDStatusLinhaFiltro;
		}

		public String getFormIDStatusLinhaFiltro() {
			return this.formIDStatusLinhaFiltro;
		}

		public void setFormStatusLinhaVO(StatusLinhaVO[] formStatusLinhaVO) {
			this.formStatusLinhaVO = formStatusLinhaVO;
		}

		public StatusLinhaVO[] getFormStatusLinhaVO() {
			return this.formStatusLinhaVO;
		}

		public void setFormNrLinhaFiltro(String formNrLinhaFiltro) {
			this.formNrLinhaFiltro = formNrLinhaFiltro;
		}

		public String getFormNrLinhaFiltro() {
			return this.formNrLinhaFiltro;
		}

		public void setFormIDPessoaFiltro(String formIDPessoaFiltro) {
			this.formIDPessoaFiltro = formIDPessoaFiltro;
		}

		public String getFormIDPessoaFiltro() {
			return this.formIDPessoaFiltro;
		}

		public void setFormIDLinhaFiltro(String formIDLinhaFiltro) {
			this.formIDLinhaFiltro = formIDLinhaFiltro;
		}

		public String getFormIDLinhaFiltro() {
			return this.formIDLinhaFiltro;
		}

		public void setFormInDivulgaNumeroFiltro(String formInDivulgaNumeroFiltro) {
			this.formInDivulgaNumeroFiltro = formInDivulgaNumeroFiltro;
		}

		public String getFormInDivulgaNumeroFiltro() {
			return this.formInDivulgaNumeroFiltro;
		}

		public void setFormDtUltimaAtualizacaoFiltro(String formDtUltimaAtualizacaoFiltro) {
			this.formDtUltimaAtualizacaoFiltro = formDtUltimaAtualizacaoFiltro;
		}

		public String getFormDtUltimaAtualizacaoFiltro() {
			return this.formDtUltimaAtualizacaoFiltro;
		}

		public void setFormListaDadosLupaLinha(br.com.vivo.fo.cliente.vo.LupaLinhaVODocument.LupaLinhaVO.ListaDadosLupaLinha[] formListaDadosLupaLinha) {
			this.formListaDadosLupaLinha = formListaDadosLupaLinha;
		}

		public br.com.vivo.fo.cliente.vo.LupaLinhaVODocument.LupaLinhaVO.ListaDadosLupaLinha[] getFormListaDadosLupaLinha() {
			return this.formListaDadosLupaLinha;
		}

		public void setFormDadosLupaLinha(DadosLupaLinha formDadosLupaLinha) {
			this.formDadosLupaLinha = formDadosLupaLinha;
		}

		public DadosLupaLinha getFormDadosLupaLinha() {
			return this.formDadosLupaLinha;
		}

		public void setFormQtLinhasLocalizadas(String formQtLinhasLocalizadas) {
			this.formQtLinhasLocalizadas = formQtLinhasLocalizadas;
		}

		public String getFormQtLinhasLocalizadas() {
			return this.formQtLinhasLocalizadas;
		}

		public void setFormQtLinhasRetornadas(String formQtLinhasRetornadas) {
			this.formQtLinhasRetornadas = formQtLinhasRetornadas;
		}

		public String getFormQtLinhasRetornadas() {
			return this.formQtLinhasRetornadas;
		}

		public void setFormNrLinhaFiltroAte(String formNrLinhaFiltroAte) {
			this.formNrLinhaFiltroAte = formNrLinhaFiltroAte;
		}

		public String getFormNrLinhaFiltroAte() {
			return this.formNrLinhaFiltroAte;
		}

		public void setErro(String erro) {
			this.erro = erro;
		}

		public String getErro() {
			return this.erro;
		}

		public void setFormPaginaAtual(String formPaginaAtual) {
			this.formPaginaAtual = formPaginaAtual;
		}

		public String getFormPaginaAtual() {
			if (this.formPaginaAtual == null) {
				this.formPaginaAtual = "0";
			}
			return this.formPaginaAtual;
		}

		public void setFormTotalPagina(String formTotalPagina) {
			this.formTotalPagina = formTotalPagina;
		}

		public String getFormTotalPagina() {
			if (this.formTotalPagina == null) {
				this.formTotalPagina = ConstantesCRM.SVAZIO;
			}
			return this.formTotalPagina;
		}

		public void setInProxima(String inProxima) {
			this.inProxima = inProxima;
		}

		public String getInProxima() {
			if (this.inProxima == null) {
				this.inProxima = ConstantesCRM.SVAZIO;
			}
			return this.inProxima;
		}

		public void setInAceitacaoPermissao(String inAceitacaoPermissao) {
			this.inAceitacaoPermissao = inAceitacaoPermissao;
		}

		public String getInAceitacaoPermissao() {
			if (this.inAceitacaoPermissao == null) {
				this.inAceitacaoPermissao = ConstantesCRM.SVAZIO;
			}
			return this.inAceitacaoPermissao;
		}

		public void setCdPermissao(String cdPermissao) {
			this.cdPermissao = cdPermissao;
		}

		public String getCdPermissao() {
			if (this.cdPermissao == null) {
				this.cdPermissao = ConstantesCRM.SVAZIO;
			}
			return this.cdPermissao;
		}

		public ReducaoVelocidadeItem getReducaoVelocidadeItem1005() {
			return this.reducaoVelocidadeItem1005;
		}

		public void setReducaoVelocidadeItem1005(ReducaoVelocidadeItem arg) {
			this.reducaoVelocidadeItem1005 = arg;
		}

		public ReducaoVelocidadeItem getReducaoVelocidadeItem1006() {
			return this.reducaoVelocidadeItem1006;
		}

		public void setReducaoVelocidadeItem1006(ReducaoVelocidadeItem arg) {
			this.reducaoVelocidadeItem1006 = arg;
		}
	}

	/**
	 * ActionForm get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class TIForm extends ActionForm {

		private static final long serialVersionUID = 1L;
		private String dsErro;
		private int qtdLinhasTotal;
		private int qtdLinhasCanceladas;
		private ParametrosVO parametros;
		private String nrLinha;
		private String nrProtocolo;
		private String pesquisa;
		private String valor;
		private TIDocumento documento;
		private PortabilidadeVO portabilidade;
		private EnderecoBaseVO endereco;
		private TIFaturamento fatura;
		private TICliente cliente;
		private TILinha linha;
		private String tipoAtendimento;
		private boolean protocoloScreenPop;
		private boolean pesquisaProtocolo;

		public TIForm() {
			linha = TILinha.Factory.newInstance();
			linha.addNewUsuarioLinhaVO();
			fatura = TIFaturamento.Factory.newInstance();
			endereco = EnderecoBaseVO.Factory.newInstance();
			endereco.addNewUFVO();
			endereco.addNewPaisVO();
			documento = TIDocumento.Factory.newInstance();
			parametros = ParametrosVO.Factory.newInstance();
			cliente = TICliente.Factory.newInstance();
			portabilidade = PortabilidadeVO.Factory.newInstance();
			cliente.addNewCarterizacaoVO();
			cliente.addNewSegmentacaoVO();
			nrLinha = new String();
			protocoloScreenPop = false;
			pesquisaProtocolo = false;
		}

		public boolean isProtocoloScreenPop() {
			return this.protocoloScreenPop;
		}

		public void setProtocoloScreenPop(boolean arg) {
			this.protocoloScreenPop = arg;
		}

		public boolean isPesquisaProtocolo() {
			return this.pesquisaProtocolo;
		}

		public void setPesquisaProtocolo(boolean arg) {
			this.pesquisaProtocolo = arg;
		}

		private String grupoSel; // -1 é o item em branco default

		public String getGrupoSel() {
			return this.grupoSel;
		}

		public void setGrupoSel(String grupoSel) {
			this.grupoSel = grupoSel;
		}

		private String nmLogin = ConstantesCRM.SVAZIO;

		public String getNmLogin() {
			return this.nmLogin;
		}

		public void setNmLogin(String nmLogin) {
			this.nmLogin = nmLogin;
		}

		public String getValor() {
			return this.valor;
		}

		public void setValor(String valor) {
			this.valor = valor;
		}

		// WFGrupoVO
		private WFGrupoVO[] gruposVO = new WFGrupoVO[0];

		public void setGruposVO(WFGrupoVO[] gruposVO) {
			this.gruposVO = gruposVO;
		}

		public WFGrupoVO[] getGruposVO() {
			return this.gruposVO;
		}

		public void setLinha(TILinha linha) {
			this.linha = linha;
		}

		public TILinha getLinha() {
			return this.linha;
		}

		public void setCliente(TICliente cliente) {
			this.cliente = cliente;
		}

		public TICliente getCliente() {
			return this.cliente;
		}

		public void setFatura(TIFaturamento fatura) {
			this.fatura = fatura;
		}

		public TIFaturamento getFatura() {
			return this.fatura;
		}

		public void setEndereco(EnderecoBaseVO endereco) {
			this.endereco = endereco;
		}

		public EnderecoBaseVO getEndereco() {
			return this.endereco;
		}

		public void setDocumento(TIDocumento documento) {
			this.documento = documento;
		}

		public TIDocumento getDocumento() {
			return this.documento;
		}

		public void setNrLinha(String nrLinha) {
			this.nrLinha = nrLinha;
		}

		public String getNrLinha() {
			return this.nrLinha;
		}

		public void setNrProtocolo(String nrProtocolo) {
			this.nrProtocolo = nrProtocolo;
		}

		public String getNrProtocolo() {
			if (this.nrProtocolo == null) {
				this.nrProtocolo = ConstantesCRM.SVAZIO;
			}
			return this.nrProtocolo;
		}

		public void setPesquisa(String pesquisa) {
			this.pesquisa = pesquisa;
		}

		public String getPesquisa() {
			return this.pesquisa;
		}

		public void setTipoAtendimento(String tipoAtendimento) {
			this.tipoAtendimento = tipoAtendimento;
		}

		public String getTipoAtendimento() {
			return this.tipoAtendimento;
		}

		public void setParametros(ParametrosVO parametros) {
			this.parametros = parametros;
		}

		public ParametrosVO getParametros() {
			return this.parametros;
		}

		public void setQtdLinhasCanceladas(int qtdLinhasCanceladas) {
			this.qtdLinhasCanceladas = qtdLinhasCanceladas;
		}

		public int getQtdLinhasCanceladas() {
			return this.qtdLinhasCanceladas;
		}

		public void setQtdLinhasTotal(int qtdLinhasTotal) {
			this.qtdLinhasTotal = qtdLinhasTotal;
		}

		public int getQtdLinhasTotal() {
			return this.qtdLinhasTotal;
		}

		public void setDsErro(String dsErro) {
			this.dsErro = dsErro;
		}

		public String getDsErro() {
			return this.dsErro;
		}

		public PortabilidadeVO getPortabilidade() {
			if (this.portabilidade == null) {
				this.portabilidade = PortabilidadeVO.Factory.newInstance();
			}
			return this.portabilidade;
		}

		public void setPortabilidade(PortabilidadeVO arg) {
			this.portabilidade = arg;
		}
	}

	/**
	 * ActionForm get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class AbaServico extends ActionForm {

		private static final long serialVersionUID = 1L;
		private HistoricoServicosVO historicoServicos;
		private br.com.vivo.fo.cliente.vo.AbaServicosFiltroVODocument.AbaServicosFiltroVO filtroServicos;

		public AbaServico() {
			historicoServicos = HistoricoServicosVO.Factory.newInstance();
			this.filtroServicos = AbaServicosFiltroVO.Factory.newInstance();
		}

		public void setFiltroServicos(br.com.vivo.fo.cliente.vo.AbaServicosFiltroVODocument.AbaServicosFiltroVO filtroServicos) {
			this.filtroServicos = filtroServicos;
		}

		public br.com.vivo.fo.cliente.vo.AbaServicosFiltroVODocument.AbaServicosFiltroVO getFiltroServicos() {
			return this.filtroServicos;
		}

		public void setHistoricoServicos(HistoricoServicosVO historicoServicos) {
			this.historicoServicos = historicoServicos;
		}

		public HistoricoServicosVO getHistoricoServicos() {
			return this.historicoServicos;
		}
	}

	/**
	 * ActionForm get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class DetalhesSaldoForm extends ActionForm {

		private static final long serialVersionUID = 1L;
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

	public static class HistoricoAtendimentoForm extends ActionForm {

		private static final long serialVersionUID = 1L;
		private HistoricoAtendimentoVO historicoAtendimentoVO;
		private String nrLinha;
		private String dtIni;
		private String dtFin;

		public HistoricoAtendimentoForm() {
			historicoAtendimentoVO = HistoricoAtendimentoVO.Factory.newInstance();
			dtIni = ConstantesCRM.SVAZIO;
			dtFin = ConstantesCRM.SVAZIO;
			nrLinha = ConstantesCRM.SVAZIO;
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

		public String getNrLinha() {
			return this.nrLinha;
		}

		public void setNrLinha(String nrLinha) {
			this.nrLinha = nrLinha;
		}
	}

	public static class AbaDadosChip extends ActionForm {

		private static final long serialVersionUID = 1L;
		private br.com.vivo.fo.cliente.vo.DadosChipVODocument.DadosChipVO abaDadosChip;

		public void setAbaDadosChip(br.com.vivo.fo.cliente.vo.DadosChipVODocument.DadosChipVO abaDadosChip) {
			this.abaDadosChip = abaDadosChip;
		}

		public br.com.vivo.fo.cliente.vo.DadosChipVODocument.DadosChipVO getAbaDadosChip() {
			return this.abaDadosChip;
		}
	}

	public static class AbaDesbloqueioAparelho extends ActionForm {

		private static final long serialVersionUID = 1L;
		private String imei = ConstantesCRM.SVAZIO;
		private DesbloqueioGsmVO desbloqueioGsmVO;

		public void setImei(String imei) {
			this.imei = imei;
		}

		public String getImei() {
			return this.imei;
		}

		public void setDesbloqueioGsmVO(DesbloqueioGsmVO desbloqueioGsmVO) {
			this.desbloqueioGsmVO = desbloqueioGsmVO;
		}

		public DesbloqueioGsmVO getDesbloqueioGsmVO() {
			return this.desbloqueioGsmVO;
		}
	}

	public static class LupaLinhaAbasGSMForm extends ActionForm {

		private static final long serialVersionUID = 1L;
		private LupaLinhaAbasGSMVO lupaLinhaAbasGSMVO;
		private String nrLinha;
		private String idLinha;

		public LupaLinhaAbasGSMForm() {
			lupaLinhaAbasGSMVO = LupaLinhaAbasGSMVO.Factory.newInstance();
			nrLinha = ConstantesCRM.SVAZIO;
			idLinha = ConstantesCRM.SVAZIO;
		}

		public void setLupaLinhaAbasGSMVO(LupaLinhaAbasGSMVO valor) {
			this.lupaLinhaAbasGSMVO = valor;
		}

		public LupaLinhaAbasGSMVO getLupaLinhaAbasGSMVO() {
			return this.lupaLinhaAbasGSMVO;
		}

		public void setNrLinha(String nrLinha) {
			this.nrLinha = nrLinha;
		}

		public String getNrLinha() {
			return this.nrLinha;
		}

		public void setIdLinha(String idLinha) {
			this.idLinha = idLinha;
		}

		public String getIdLinha() {
			return this.idLinha;
		}
	}
}
