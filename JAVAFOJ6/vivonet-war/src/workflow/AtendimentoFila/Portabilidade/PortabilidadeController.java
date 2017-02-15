package workflow.AtendimentoFila.Portabilidade;

import java.io.BufferedOutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.apache.xmlbeans.XmlOptions;

import workflow.AtendimentoFila.Portabilidade.commons.CommonsPortabilidade;
import workflow.AtendimentoFila.Portabilidade.formBeans.DetalhesProcessoForm;
import workflow.AtendimentoFila.Portabilidade.formBeans.FilaPortabilidadeFiltrosForm;
import workflow.AtendimentoFila.Portabilidade.formBeans.GestorGerenteContaForm;
import br.com.vivo.fo.cliente.vo.EnderecoBaseVODocument.EnderecoBaseVO;
import br.com.vivo.fo.cliente.vo.LupaClienteVODocument.LupaClienteVO;
import br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.cliente.vo.PortabilidadeVODocument.PortabilidadeVO;
import br.com.vivo.fo.cliente.vo.TelaInicialVODocument.TelaInicialVO;
import br.com.vivo.fo.cliente.vo.UFVODocument.UFVO;
import br.com.vivo.fo.commons.utils.GetParametro;
import br.com.vivo.fo.commons.utils.StringUtilStatic;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.fidelizacao.vo.BonusVODocument.BonusVO;
import br.com.vivo.fo.fidelizacao.vo.DetalheHistoricoRetencaoVODocument.DetalheHistoricoRetencaoVO;
import br.com.vivo.fo.fidelizacao.vo.DetalheLinhaVODocument.DetalheLinhaVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralDescricaoVODocument.FidelizacaoListaGeralDescricaoVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO;
import br.com.vivo.fo.fidelizacao.vo.FinalizaRetencaoVODocument.FinalizaRetencaoVO;
import br.com.vivo.fo.fidelizacao.vo.FinalizaRetencaoVODocument.FinalizaRetencaoVO.Retencao.RetencaoBody.Analise.Endereco;
import br.com.vivo.fo.fidelizacao.vo.FinalizaRetencaoVODocument.FinalizaRetencaoVO.Retencao.RetencaoBody.Ofertas.Aparelhos;
import br.com.vivo.fo.fidelizacao.vo.FinalizaRetencaoVODocument.FinalizaRetencaoVO.Retencao.RetencaoBody.Ofertas.Bonus;
import br.com.vivo.fo.fidelizacao.vo.FinalizaRetencaoVODocument.FinalizaRetencaoVO.Retencao.RetencaoBody.Ofertas.Planos;
import br.com.vivo.fo.fidelizacao.vo.FinalizaRetencaoVODocument.FinalizaRetencaoVO.Retencao.RetencaoBody.Ofertas.Pontos;
import br.com.vivo.fo.fidelizacao.vo.ItemListaDescricaoVODocument.ItemListaDescricaoVO;
import br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument.ItemListaVO;
import br.com.vivo.fo.fidelizacao.vo.ItemOfertaAparelhoVODocument.ItemOfertaAparelhoVO;
import br.com.vivo.fo.fidelizacao.vo.ListaBonusVODocument.ListaBonusVO;
import br.com.vivo.fo.fidelizacao.vo.ListaDetalheLinhaVODocument.ListaDetalheLinhaVO;
import br.com.vivo.fo.fidelizacao.vo.ListaHistoricoRetencaoVODocument.ListaHistoricoRetencaoVO;
import br.com.vivo.fo.fidelizacao.vo.ListaMigracaoVODocument.ListaMigracaoVO;
import br.com.vivo.fo.fidelizacao.vo.ListaPlanoVODocument.ListaPlanoVO;
import br.com.vivo.fo.fidelizacao.vo.MensajeEncerraVODocument.MensajeEncerraVO;
import br.com.vivo.fo.fidelizacao.vo.OfAparelhoParamVODocument.OfAparelhoParamVO;
import br.com.vivo.fo.fidelizacao.vo.OfertaAparelhoVODocument.OfertaAparelhoVO;
import br.com.vivo.fo.fidelizacao.vo.PlanoVODocument.PlanoVO;
import br.com.vivo.fo.fidelizacao.vo.RetencaoSAPVODocument.RetencaoSAPVO;
import br.com.vivo.fo.fidelizacao.vo.RetencaoSAPVODocument.RetencaoSAPVO.CLIENTE;
import br.com.vivo.fo.fidelizacao.vo.RetencaoSAPVODocument.RetencaoSAPVO.ENDERECO;
import br.com.vivo.fo.fidelizacao.vo.RetencaoSAPVODocument.RetencaoSAPVO.PRODUTO;
import br.com.vivo.fo.log.FOPageFlowBusinessLogger;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.retornotux.vo.RetencaoRetornoSAPVODocument.RetencaoRetornoSAPVO;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoFilaPesquisaVODocument.AtendimentoFilaPesquisaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoHistoricoVODocument.AtendimentoHistoricoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoInformacaoVODocument.AtendimentoInformacaoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO;
import br.com.vivo.fo.workflow.vo.MonitoramentoPesquisaVODocument.MonitoramentoPesquisaVO;
import br.com.vivo.fo.workflow.vo.PessoaVODocument.PessoaVO;
import br.com.vivo.fo.workflow.vo.RDContatoVODocument.RDContatoVO;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO;
import br.com.vivo.fo.workflow.vo.WFEstadosVODocument.WFEstadosVO;
import br.com.vivo.ws.gestorconta.GestorContaBindingStub;
import br.com.vivo.ws.gestorconta.GestorContaLocator;
import br.com.vivo.ws.gestorconta.GestorContaPortType;
import br.com.vivo.ws.gestorconta.GestorContaPortTypeProxy;
import br.com.vivo.ws.gestorconta.ParametrosBuscarListaGestores;
import br.com.vivo.ws.gestorconta.ParametrosBuscarListaGestoresCdTipoOperacao;
import br.com.vivo.ws.gestorconta.ParametrosBuscarListaGestoresCdTipoRelacionamento;
import br.com.vivo.ws.gestorconta.ParametrosManterGestorContaEmpresa;
import br.com.vivo.ws.gestorconta.ParametrosManterGestorContaEmpresaCdSistemaOrigem;
import br.com.vivo.ws.gestorconta.ParametrosManterGestorContaEmpresaCdTipoGestor;
import br.com.vivo.ws.gestorconta.ParametrosManterGestorContaEmpresaCdTipoOperacao;
import br.com.vivo.ws.gestorconta.bean.geral.ErroInfo;
import br.com.vivo.ws.gestorconta.bean.pessoa.Gestor;
import br.com.vivo.ws.gestorconta.bean.pessoa.Gestores;

import com.indracompany.actions.AbstractAction;
import commons.errors.AjaxError;
import commons.errors.FormError;

@SuppressWarnings({"rawtypes","unchecked","deprecation","unused"})
public class PortabilidadeController extends AbstractAction {

	private static final long serialVersionUID = 54263655152807673L;

	protected global.Global globalApp = new global.Global();

	private FilaPortabilidadeFiltrosForm filaPortabilidadeFiltrosForm;

	private DetalhesProcessoForm detalhesProcessoForm;

	private GestorGerenteContaForm gestorGerenteContaForm;

	private FidelizacaoForm fidelizacaoForm;

	protected FinalizaRetencaoVO finalizaRetencaoVO = null;

	private final int TIPO_ENCERRAMENTO_RETIDO_PORTABILIDADE = 12;
	private final int TIPO_ENCERRAMENTO_NAO_RETIDO_PORTABILIDADE = 13;
	private final int TIPO_ENCERRAMENTO_VAIPENSAR_PORTABILIDADE = 14;

	private final String APARELHOS = "AP"; // APARELHOS
	private final String BONUS = "BN"; // BÔNUS
	private final String MIGRACAO = "MIG";// MIGRAÇÃO
	private final String ADEQUACAO_PLANO = "PL"; // ADEQUAÇÃO DE PLANOS
	private final String PONTOS = "PT"; // PONTOS
	private final String SUSPENSAO_TEMP = "SP"; // SUSPENSÃO TEMPORÁRIA
	private final String COMODATO = "APC";// APARELHO COMODATO

	private final int INICIAR_RETENCAO = 54;
	private final int DEVOLVER_FILA_PORTOUT = 50;
	private final int CLIENTE_NAO_ATENDEU = 59;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.monitoramento.MonitoramentoFacade monitoramentoFacade;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.atendimento.AtendimentoFacade atendimentoFacade;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.RAtendimento.RAtendimento rAtendimentoFacade;

	@EJB
	private br.com.vivo.fo.ctrls.cliente.telaInicial.TelaInicialFacade telaInicialFacade;

	@EJB
	private br.com.vivo.fo.ctrls.fidelizacao.realizarRetencao.RalizarRetencaoFacade fidelizacaoFacade;

	@EJB
	private br.com.vivo.fo.ctrls.fidelizacao.OfertaRealizada.OfertaRealizadaFacade ofertaRealizadaFacade;

	@EJB
	private br.com.vivo.fo.ctrls.fidelizacao.OfertaRealizada.OfertaAparelhoFacade ofertaAparelhoFacade;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.AtendimentoWorkflowFacade atendimentoWorkflowFacade;

	private FOPageFlowBusinessLogger businessLog = new FOPageFlowBusinessLogger();

	private transient Logger logger = new Logger("Portabilidade");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("pesquisarProcessos".equals(mapping.getParameter())) {
			return pesquisarProcessos(mapping, form, request, response);
		} else if ("getAlerta".equals(mapping.getParameter())) {
			return getAlerta(mapping, form, request, response);
		} else if ("getDetalheProcesso".equals(mapping.getParameter())) {
			return getDetalheProcesso(mapping, form, request, response);
		} else if ("incluirAlterarGestorGerente".equals(mapping.getParameter())) {
			return incluirAlterarGestorGerente(mapping, form, request, response);
		} else if ("verificaNumeroVivo".equals(mapping.getParameter())) {
			return verificaNumeroVivo(mapping, form, request, response);
		} else if ("salvarGestorGerente".equals(mapping.getParameter())) {
			return salvarGestorGerente(mapping, form, request, response);
		} else if ("fidelizacaoCore".equals(mapping.getParameter())) {
			return fidelizacaoCore(mapping, form, request, response);
		} else if ("atualizaMensagem".equals(mapping.getParameter())) {
			return atualizaMensagem(mapping, form, request, response);
		} else if ("fidelizacaoRetencao".equals(mapping.getParameter())) {
			return fidelizacaoRetencao(mapping, form, request, response);
		} else if ("getListaAparelhos".equals(mapping.getParameter())) {
			return getListaAparelhos(mapping, form, request, response);
		} else if ("getListaDescontosParcelas".equals(mapping.getParameter())) {
			return getListaDescontosParcelas(mapping, form, request, response);
		} else if ("getListaDdescontosParcelas".equals(mapping.getParameter())) {
			return getListaDdescontosParcelas(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	public FilaPortabilidadeFiltrosForm getFilaPortabilidadeFiltrosForm() {
		if (this.filaPortabilidadeFiltrosForm == null) {
			this.filaPortabilidadeFiltrosForm = new FilaPortabilidadeFiltrosForm();
		}
		return this.filaPortabilidadeFiltrosForm;
	}

	public DetalhesProcessoForm getDetalhesProcessoForm() {
		if (this.detalhesProcessoForm == null) {
			this.detalhesProcessoForm = new DetalhesProcessoForm();
		}
		return this.detalhesProcessoForm;
	}

	public GestorGerenteContaForm getGestorGerenteContaForm() {
		if (this.gestorGerenteContaForm == null) {
			this.gestorGerenteContaForm = new GestorGerenteContaForm();
		}
		return this.gestorGerenteContaForm;
	}

	public FidelizacaoForm getFidelizacaoForm() {
		if (this.fidelizacaoForm == null) {
			this.fidelizacaoForm = new FidelizacaoForm();
		}
		return this.fidelizacaoForm;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			FilaPortabilidadeFiltrosForm form = (FilaPortabilidadeFiltrosForm) formParam;

			businessLog = new FOPageFlowBusinessLogger();
			String user = ConstantesCRM.getCurrentUser(request.getSession());
			getFilaPortabilidadeFiltrosForm().setInVoltar(false);

			// Para preenchimento inicial da tela caso parâmetros já estejam no bean
			// Exemplo: Botão voltar
			if (ConstantesCRM.STRUE.equalsIgnoreCase(request.getParameter("voltar"))) {

				if (ConstantesCRM.STRUE.equalsIgnoreCase(request.getParameter("inClienteNaoAtendeu"))) {

					AtendimentoVO[] atendimentoVO = new AtendimentoVO[1];
					atendimentoVO[0] = getAtendimentoVO(getDetalhesProcessoForm().getDadosProcesso().getIdAtendimento(), CLIENTE_NAO_ATENDEU);
					atendimentoWorkflowFacade.listaEncaminharGravar(user, atendimentoVO);
					businessLog.addNewLoggerItem("Cliente não atendeu - COREWORKFLOW", atendimentoVO[0].xmlText());

				} else if ("S".equals(getDetalhesProcessoForm().getWFAcaoRetornoVO().getAcaoExecucao())) {

					AtendimentoVO[] atendimentoVO = new AtendimentoVO[1];
					atendimentoVO[0] = getAtendimentoVO(getDetalhesProcessoForm().getDadosProcesso().getIdAtendimento(), DEVOLVER_FILA_PORTOUT);
					atendimentoWorkflowFacade.listaEncaminharGravar(user, atendimentoVO);
					businessLog.addNewLoggerItem("Processo devolvido à fila - COREWORKFLOW", atendimentoVO[0].xmlText());
				}

				getFilaPortabilidadeFiltrosForm().setInVoltar(true);
				if (getFilaPortabilidadeFiltrosForm().getFiltrosPesquisa().getWFRegionalVO() != null) {
					getFilaPortabilidadeFiltrosForm().setIdRegional(getFilaPortabilidadeFiltrosForm().getFiltrosPesquisa().getWFRegionalVO().getIdRegional());
				}
				if (getFilaPortabilidadeFiltrosForm().getFiltrosPesquisa().getTipoPessoaVO() != null) {
					getFilaPortabilidadeFiltrosForm().setIdTipoPessoa(String.valueOf(getFilaPortabilidadeFiltrosForm().getFiltrosPesquisa().getTipoPessoaVO().getIdtipopessoaArray(0)));
				}
				if (getFilaPortabilidadeFiltrosForm().getFiltrosPesquisa().getCarterizacaoVO() != null) {
					getFilaPortabilidadeFiltrosForm().setIdCarteira(String.valueOf(getFilaPortabilidadeFiltrosForm().getFiltrosPesquisa().getCarterizacaoVO().getIdTipoCarteira()));
				}
				if (getFilaPortabilidadeFiltrosForm().getFiltrosPesquisa().getSegmentacaoVO() != null) {
					getFilaPortabilidadeFiltrosForm().setIdSegmento(String.valueOf(getFilaPortabilidadeFiltrosForm().getFiltrosPesquisa().getSegmentacaoVO().getIdSegmentacao()));
				}
				if (getFilaPortabilidadeFiltrosForm().getFiltrosPesquisa().getWFEstadoVO() != null) {
					getFilaPortabilidadeFiltrosForm().setIdEstado(getFilaPortabilidadeFiltrosForm().getFiltrosPesquisa().getWFEstadoVO().getIdEstado());
				}
				if (getFilaPortabilidadeFiltrosForm().getFiltrosPesquisa().getAlertaVO() != null) {
					getFilaPortabilidadeFiltrosForm().setIdTipoAlerta(String.valueOf(getFilaPortabilidadeFiltrosForm().getFiltrosPesquisa().getAlertaVO().getIdAlerta()));
				}
				if (getFilaPortabilidadeFiltrosForm().getFiltrosPesquisa().getClassificacaoTipoLinhaVO() != null) {
					getFilaPortabilidadeFiltrosForm().setIdTipoLinha(getFilaPortabilidadeFiltrosForm().getFiltrosPesquisa().getClassificacaoTipoLinhaVO().getIdClassificacaoTipoLinhaVO());
				}
				if (!ConstantesCRM.STRUE.equalsIgnoreCase(request.getParameter("inClienteNaoAtendeu"))) {
					businessLog.addNewLoggerItem("Botão Voltar Clicado", getFilaPortabilidadeFiltrosForm().toString());
				}
			} else {
				this.filaPortabilidadeFiltrosForm = new FilaPortabilidadeFiltrosForm();
			}

			if (getFilaPortabilidadeFiltrosForm().getFiltrosListas().getWFRegionalVOArray().length == 0) {
				MonitoramentoPesquisaVO monitoramentoPesquisaVO = monitoramentoFacade.getMonitoramentoPesquisaVO(user, true, true, true, true, true);
				getFilaPortabilidadeFiltrosForm().setFiltrosListas(monitoramentoPesquisaVO);
				businessLog.addNewLoggerItem("getFilaPortabilidadeFiltrosForm().getFiltrosListas()", monitoramentoPesquisaVO.xmlText());
			}

			if (getFilaPortabilidadeFiltrosForm().getListaEstadosSubestados().getWFEstadoVOArray().length == 0) {
				WFEstadosVO WFEstadoVO = atendimentoFacade.getWFEstadosVO(user, true);
				getFilaPortabilidadeFiltrosForm().setListaEstadosSubestados(WFEstadoVO);
				businessLog.addNewLoggerItem("getFilaPortabilidadeFiltrosForm().getListaEstadosSubestados()", WFEstadoVO.xmlText());
			}

			request.setAttribute("debugMessage", businessLog);

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, ConstantesCRM.TELAINCIAL);
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
	protected ActionForward pesquisarProcessos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			FilaPortabilidadeFiltrosForm form = (FilaPortabilidadeFiltrosForm) formParam;

			String user = ConstantesCRM.getCurrentUser(request.getSession());

			AtendimentoFilaPesquisaVO filtrosPesquisa = AtendimentoFilaPesquisaVO.Factory.newInstance();
			filtrosPesquisa = form.getFiltrosPesquisa();
			filtrosPesquisa.setInPortout(1);
			filtrosPesquisa.addNewWFRegionalVO().setIdRegional(form.getIdRegional());

			if (!ConstantesCRM.SVAZIO.equals(form.getIdTipoPessoa())) {
				filtrosPesquisa.addNewTipoPessoaVO().addIdtipopessoa(Integer.parseInt(form.getIdTipoPessoa()));
			}

			if ("ABERTURA".equals(form.getInPeriodo())) {
				filtrosPesquisa.setDtFechamentoInicio(null);
				filtrosPesquisa.setDtFechamentoFim(null);
			} else if ("FECHAMENTO".equals(form.getInPeriodo())) {
				filtrosPesquisa.setDtAberturaInicio(null);
				filtrosPesquisa.setDtAberturaFim(null);
			}

			// Quando pesquisa é feita por nrLinha, todos os outros filtros de listas são desnecessários
			if (!ConstantesCRM.SVAZIO.equals(form.getFiltrosPesquisa().getNrLinha())) {
				form.getFiltrosPesquisa().setNrLinha(StringUtilStatic.unmask(form.getFiltrosPesquisa().getNrLinha(), "()-"));
			} else {
				if (!ConstantesCRM.SVAZIO.equals(form.getIdEstado())) {
					filtrosPesquisa.addNewWFEstadoVO().setIdEstado(form.getIdEstado());
				} else {
					if (filtrosPesquisa.getWFEstadoVO() != null) {
						filtrosPesquisa.unsetWFEstadoVO();
					}
				}
				if (!ConstantesCRM.SVAZIO.equals(form.getIdSubestado())) {
					filtrosPesquisa.addNewWFSubEstadoVO().setIdSubEstado(form.getIdSubestado());
				} else {
					if (filtrosPesquisa.getWFSubEstadoVO() != null) {
						filtrosPesquisa.unsetWFSubEstadoVO();
					}
				}
				if (!ConstantesCRM.SVAZIO.equals(form.getIdCarteira())) {
					filtrosPesquisa.addNewCarterizacaoVO().setIdTipoCarteira(Integer.parseInt(form.getIdCarteira()));
				} else {
					if (filtrosPesquisa.getCarterizacaoVO() != null) {
						filtrosPesquisa.unsetCarterizacaoVO();
					}
				}
				if (!ConstantesCRM.SVAZIO.equals(form.getIdSegmento())) {
					filtrosPesquisa.addNewSegmentacaoVO().setIdSegmentacao(Integer.parseInt(form.getIdSegmento()));
				} else {
					if (filtrosPesquisa.getSegmentacaoVO() != null) {
						filtrosPesquisa.unsetSegmentacaoVO();
					}
				}
				if (!ConstantesCRM.SVAZIO.equals(form.getIdTipoAlerta())) {
					filtrosPesquisa.addNewAlertaVO().setIdAlerta(Integer.parseInt(form.getIdTipoAlerta()));
				} else {
					if (filtrosPesquisa.getAlertaVO() != null) {
						filtrosPesquisa.unsetAlertaVO();
					}
				}
				if (!ConstantesCRM.SVAZIO.equals(form.getIdTipoLinha())) {
					filtrosPesquisa.addNewClassificacaoTipoLinhaVO().setIdClassificacaoTipoLinhaVO(form.getIdTipoLinha());
				} else {
					if (filtrosPesquisa.getClassificacaoTipoLinhaVO() != null) {
						filtrosPesquisa.unsetClassificacaoTipoLinhaVO();
					}
				}
			}

			getFilaPortabilidadeFiltrosForm().setFiltrosPesquisa(filtrosPesquisa);
			AtendimentoInformacaoVO resultadoPesquisa = atendimentoFacade.obtemAtendimentoInformacaoVO(user, filtrosPesquisa);

			if (ConstantesCRM.SONE.equals(filtrosPesquisa.getInTotalRegistros())) {
				response.setContentType(ConstantesCRM.SContentType);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
				bufferedOutputStream.write(resultadoPesquisa.getTotalRegistros().getBytes(ConstantesCRM.SISO));
				bufferedOutputStream.flush();
				bufferedOutputStream.close();
				return null;
			} else {

				businessLog = new FOPageFlowBusinessLogger();
				businessLog.addNewLoggerItem("filtrosPesquisa", filtrosPesquisa.xmlText());
				request.setAttribute("debugMessage", businessLog);

				getFilaPortabilidadeFiltrosForm().setResultadoPesquisa(resultadoPesquisa);

				request.setAttribute("filaPortabilidadeFiltrosForm", getFilaPortabilidadeFiltrosForm());
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}

		} catch (Exception e) {
			response.setStatus(AjaxError.STATUS_CODE_ERROR, AjaxError.MSG_ERRO);
			response.setHeader("stackTrace", AjaxError.getStackTrace(e));
			return null;
		}

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="listaAlertas.jsp"
	 */
	protected ActionForward getAlerta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			FilaPortabilidadeFiltrosForm form = (FilaPortabilidadeFiltrosForm) formParam;

			String user = ConstantesCRM.getCurrentUser(request.getSession());
			String idAtendimento = request.getParameter("idAtendimento");

			if (idAtendimento != null) {
				getFilaPortabilidadeFiltrosForm().setListaAlertas(atendimentoFacade.obtemAlertaVO(user, idAtendimento, ConstantesCRM.SONE));
			}

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, ConstantesCRM.TELAINCIAL);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute("filaPortabilidadeFiltrosForm", getFilaPortabilidadeFiltrosForm());
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="detalheProcesso.jsp"
	 * @jpf:forward name="detalheProcessoHistorico" path="detalheProcessoHistorico.jsp"
	 * @jpf:forward name="detalheProcessoCliente" path="detalheProcessoCliente.jsp"
	 * @jpf:forward name="detalheProcessoSolicitante" path="detalheProcessoSolicitante.jsp"
	 * @jpf:forward name="detalheProcessoGestorGerente" path="detalheProcessoGestorGerenteConta.jsp"
	 * @jpf:forward name="detalheProcessoRetencao" path="detalheProcessoRetencao.jsp"
	 * @jpf:forward name="detalheHistoricoPortabilidade" path="detalheHistoricoPortabilidade.jsp"
	 * @jpf:forward name="detalheHistoricoRetencao" path="detalheHistoricoRetencao.jsp"
	 * @jpf:forward name="detalhamentoHistoricoRetencao" path="detalheHistoricoRetencaoDetalhamento.jsp"
	 * @jpf:forward name="fluxoRetencao" path="fidelizacaoIndex.jsp"
	 * @jpf:forward name="mensagemGestor" path="mensagemGestor.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward getDetalheProcesso(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DetalhesProcessoForm form = (DetalhesProcessoForm) formParam;

		String destino = (request.getParameter("destino") == null) ? ConstantesCRM.SUCCESS : request.getParameter("destino");

		try {
			String user = ConstantesCRM.getCurrentUser(request.getSession());

			AtendimentoVO dadosProcesso = null;
			ListaDetalheLinhaVO listaLinhasAssociadas = null;
			DetalheLinhaVO dadosLinha = null;

			// Chamadas feitas pela tela de atendimento
			if (request.getParameter("idAtendimento") != null) {
				getDetalhesProcessoForm().setDsOrigem("TELAATENDIMENTO");
				AtendimentoFilaPesquisaVO filtrosPesquisa = AtendimentoFilaPesquisaVO.Factory.newInstance();
				filtrosPesquisa.setIdAtendimento(request.getParameter("idAtendimento"));
				AtendimentoInformacaoVO resultadoPesquisa = atendimentoFacade.obtemAtendimentoInformacaoVO(user, filtrosPesquisa);
				dadosProcesso = resultadoPesquisa.getAtendimentoFilaVOArray(0).getAtendimentoVO();
			} else

				// Chamadas feitas após seleção de "Nova Oferta" para processo agrupado
				if (request.getParameter("nrLinha") != null) {
					getDetalhesProcessoForm().setDsOrigem("NOVAOFERTA");
					String nrLinha = request.getParameter("nrLinha");
					String nrProtocoloPortabilidade = ConstantesCRM.SVAZIO;
					for (int i = 0; i < getDetalhesProcessoForm().getListaLinhasAssociadas().getDetalheLinhaVOArray().length; i++) {
						if (nrLinha.equals(getDetalhesProcessoForm().getListaLinhasAssociadas().getDetalheLinhaVOArray(i).getNumero())) {
							nrProtocoloPortabilidade = getDetalhesProcessoForm().getListaLinhasAssociadas().getDetalheLinhaVOArray(i).getNrProtocoloPortabilidade();
							break;
						}
					}
					AtendimentoFilaPesquisaVO filtrosPesquisa = AtendimentoFilaPesquisaVO.Factory.newInstance();
					filtrosPesquisa.setNrLinha(nrLinha);
					filtrosPesquisa.setNrProtocoloPortabilidade(nrProtocoloPortabilidade);
					AtendimentoInformacaoVO resultadoPesquisa = atendimentoFacade.obtemAtendimentoInformacaoVO(user, filtrosPesquisa);
					dadosProcesso = resultadoPesquisa.getAtendimentoFilaVOArray(0).getAtendimentoVO();
				}

			PessoaVO pessoa = PessoaVO.Factory.newInstance();
			RDContatoVO contato = RDContatoVO.Factory.newInstance();
			AtendimentoHistoricoVO[] historicoProcesso = new AtendimentoHistoricoVO[0];
			ListaHistoricoRetencaoVO historicoRetencao = ListaHistoricoRetencaoVO.Factory.newInstance();

			if (destino.equals(ConstantesCRM.SUCCESS)) {

				if (dadosProcesso == null) {
					String indexProcesso = request.getParameter("indexAtendimentoVO");
					dadosProcesso = getFilaPortabilidadeFiltrosForm().getResultadoPesquisa().getAtendimentoFilaVOArray(Integer.parseInt(indexProcesso)).getAtendimentoVO();
					getDetalhesProcessoForm().setDadosProcesso(dadosProcesso);
				}

				WFAcaoRetornoVO wFAcaoRetornoVO = WFAcaoRetornoVO.Factory.newInstance();
				getDetalhesProcessoForm().setWFAcaoRetornoVO(wFAcaoRetornoVO);

				/*
				 * if (!"CANCELADO".equals(getDetalhesProcessoForm().getDadosProcesso().getWFEstadoVO().getDsEstado())
				 * && !"FECHADO".equals(getDetalhesProcessoForm().getDadosProcesso().getWFEstadoVO().getDsEstado())) {
				 * AtendimentoVO[] atendimentoVO = new AtendimentoVO[1]; atendimentoVO[0] =
				 * getAtendimentoVO(getDetalhesProcessoForm().getDadosProcesso().getIdAtendimento(), INICIAR_RETENCAO);
				 * getDetalhesProcessoForm().setWFAcaoRetornoVO( atendimentoWorkflowFacade.listaEncaminharGravar(user,
				 * atendimentoVO) ); }
				 */

				pessoa = rAtendimentoFacade.getDadosPessoa(user, String.valueOf(getDetalhesProcessoForm().getDadosProcesso().getIdAtendimento()));
				getDetalhesProcessoForm().setDadosCliente(pessoa);
				listaLinhasAssociadas = fidelizacaoFacade.getLinha(user, String.valueOf(getDetalhesProcessoForm().getDadosCliente().getIdPessoa()), ConstantesCRM.SVAZIO, true, String.valueOf(getDetalhesProcessoForm().getDadosProcesso().getIdAtendimento()));
				getDetalhesProcessoForm().setListaLinhasAssociadas(listaLinhasAssociadas);
				getDetalhesProcessoForm().getDetalheProcesso().setNrProtocolo(dadosProcesso.getNrProtocolo());
			} else if (destino.equals("detalheProcessoCliente")) {
				contato = rAtendimentoFacade.getDadosContato(user, String.valueOf(getDetalhesProcessoForm().getDadosProcesso().getIdAtendimento()));
				ListaDetalheLinhaVO listaDetalheLinhaVO = fidelizacaoFacade.getLinha(user, String.valueOf(getDetalhesProcessoForm().getDadosCliente().getIdPessoa()), getDetalhesProcessoForm().getDadosProcesso().getNrTelefone().replaceAll("[^0-9]*", ConstantesCRM.SVAZIO).substring(1), false, ConstantesCRM.SVAZIO);

				// Dados insuficientes para início do processo de retenção
				if (listaDetalheLinhaVO.getDetalheLinhaVOArray().length == 0) {
					getDetalhesProcessoForm().getWFAcaoRetornoVO().setAcaoExecucao("N");
					dadosLinha = DetalheLinhaVO.Factory.newInstance();
				} else {
					dadosLinha = listaDetalheLinhaVO.getDetalheLinhaVOArray(0);
				}

				dadosLinha.setNrProtocoloPortabilidade(getDetalhesProcessoForm().getDadosProcesso().getNrProtocoloPortabilidade());
				getDetalhesProcessoForm().setDadosLinha(dadosLinha);
				getDetalhesProcessoForm().setDadosContato(contato);

			} else if ("detalheProcessoHistorico".equals(destino)) {
				historicoProcesso = atendimentoFacade.obtemAtendimentoHistoricoVO(user, String.valueOf(getDetalhesProcessoForm().getDadosProcesso().getIdAtendimento()), "-1", "-1", ConstantesCRM.SZERO, ConstantesCRM.SZERO);
				getDetalhesProcessoForm().setHistoricoProcesso(historicoProcesso);
			} else if ("detalheHistoricoPortabilidade".equals(destino)) {

				String nrLinha = getDetalhesProcessoForm().getDadosProcesso().getNrTelefone().replaceAll("[^0-9]*", "").substring(1);
				PortabilidadeVO portabilidadeVO = PortabilidadeVO.Factory.newInstance();

				try {
					portabilidadeVO.setCdAreaRegistro(Integer.parseInt(nrLinha.substring(0, 2)));
					portabilidadeVO.setNrLinha(Integer.parseInt(nrLinha.substring(2)));
					portabilidadeVO = telaInicialFacade.getPortabilidadeVO(user, portabilidadeVO);
					getDetalhesProcessoForm().setPortabilidade(portabilidadeVO);

				} catch (Exception e) {
					request.setAttribute("msgErro", "Houve um problema durante acesso ao histórico de portabilidade.");
				}

			} else if ("saltoTotalPontos".equals(destino)) {
				String nrLinha = getDetalhesProcessoForm().getDadosProcesso().getNrTelefone().replaceAll("[^0-9]*", "").substring(1);
				LupaClienteVO pontos = telaInicialFacade.getPontos(user, nrLinha);
				response.setContentType("text/plain;charset=ISO-8859-1");
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
				bufferedOutputStream.write(((pontos.getDadosAbaLupaCliente().getContasPontuacaoArray(0).getErro() != null) ? "Indisponível" : pontos.getDadosAbaLupaCliente().getContasPontuacaoArray(0).getSaldo()).getBytes(ConstantesCRM.SISO));
				bufferedOutputStream.flush();
				bufferedOutputStream.close();
				return null;

			} else if ("detalheHistoricoRetencao".equals(destino)) {
				String nrLinha = getDetalhesProcessoForm().getDadosProcesso().getNrTelefone().replaceAll("[^0-9]*", "").substring(1);
				ParametrosVO parametros = telaInicialFacade.getParametrosVO(user, nrLinha, ConstantesCRM.SONE);
				getDetalhesProcessoForm().setParametros(parametros);
				historicoRetencao = fidelizacaoFacade.getHistorico(user, getDetalhesProcessoForm().getDadosLinha().getIdLinha());
				getDetalhesProcessoForm().setHistoricoRetencao(historicoRetencao);

			} else if ("detalhamentoHistoricoRetencao".equals(destino)) {
				DetalheHistoricoRetencaoVO detalheHistorico = fidelizacaoFacade.getDetalheHistorico(user, request.getParameter("idRetencao"));
				getDetalhesProcessoForm().setDetalheHistoricoRetencao(detalheHistorico);

			} else if ("fluxoRetencao".equals(destino)) {
				request.setAttribute("destino", "fidelizacaoIntencao");

			} else if ("detalheProcessoGestorGerente".equals(destino)) {

				String cdarea = getDetalhesProcessoForm().getDadosContato().getCdAreaRegistro();
				String nrLinha = cdarea + getDetalhesProcessoForm().getDadosContato().getNrLinha();

				if (!isClientePJ(nrLinha, request)) {
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("mensagemGestor");
				}

				String headLog = "PortabilidadeController::getDetalheProcesso(detalheProcessoGestorGerente)::";

				getDetalhesProcessoForm().setGestorGerenteContaForm(new GestorGerenteContaForm[2]);

				logger.debug(headLog + "Busca Gestores");
				GetParametro parametro = GetParametro.getInstace();
				ParametroVO apoioParam = parametro.getParametroVO(user, "PARAMETRO_ENDPOINT_MANGESTOR");

				String endPoint = apoioParam.getDsValorParametro();
				logger.debug(headLog + "endpoint = " + endPoint);

				ParametrosBuscarListaGestores param = new ParametrosBuscarListaGestores();

				param.setCdTipoOperacao(ParametrosBuscarListaGestoresCdTipoOperacao.value1);
				logger.debug(headLog + "CdTipoOperacao = " + ParametrosBuscarListaGestoresCdTipoOperacao.value3.toString());

				String idAtend = String.valueOf(getDetalhesProcessoForm().getDadosProcesso().getIdAtendimento());
				param.setIdAtendimento(idAtend);
				logger.debug(headLog + "IdAtendimento = " + idAtend);

				GestorContaPortTypeProxy proxy = new GestorContaPortTypeProxy();
				proxy.setEndpoint(endPoint);

				GestorContaPortType type = proxy.getGestorContaPortType();
				((GestorContaBindingStub) type).setTimeout(20 * 1000);

				Gestores gestorConta = null;
				Gestores gerenteConta = null;
				try {
					// Busca Gestor de Conta
					logger.debug(headLog + "Buscando Gestor de Conta");
					param.setCdTipoRelacionamento(ParametrosBuscarListaGestoresCdTipoRelacionamento.GC);
					logger.debug(headLog + "CdTipoRelacionamento = " + ParametrosBuscarListaGestoresCdTipoRelacionamento.GC.toString());
					gestorConta = type.buscarListaGestores(param);
					if (gestorConta != null && gestorConta.getGestor() != null) {
						setDadosGestorGerenteContas(0, gestorConta.getGestor(0));
						logger.debug(headLog + "gestorConta.getGestor().length = " + gestorConta.getGestor().length);
					} else {
						logger.debug(headLog + "Nenhum gestor retornado.");
					}
				} catch (ErroInfo e) {
					logger.error(headLog + "ErroInfo::" + e.getCodigo());
					logger.error(headLog + "ErroInfo::" + e.getDescricao());
				} catch (Exception e) {
					logger.error(headLog + "Exception::" + e.getMessage());
					logger.error(headLog + "Exception::", e);
				}

				try {
					// Busca Gerente de Contas
					logger.debug(headLog + "Buscando Gerente de Empresa");
					param.setCdTipoRelacionamento(ParametrosBuscarListaGestoresCdTipoRelacionamento.GRC);
					logger.debug(headLog + "CdTipoRelacionamento = " + ParametrosBuscarListaGestoresCdTipoRelacionamento.GRC.toString());
					gerenteConta = type.buscarListaGestores(param);
					if (gerenteConta != null && gerenteConta.getGestor() != null) {
						setDadosGestorGerenteContas(1, gerenteConta.getGestor(0));
						logger.debug(headLog + "gerenteConta.getGestor().length = " + gerenteConta.getGestor().length);
					} else {
						logger.debug(headLog + "Nenhum gerente retornado.");
					}
				} catch (ErroInfo e) {
					logger.error(headLog + "ErroInfo::" + e.getCodigo());
					logger.error(headLog + "ErroInfo::" + e.getDescricao());
				} catch (Exception e) {
					logger.error(headLog + "Exception::" + e.getMessage());
					logger.error(headLog + "Exception::", e);
				}
			}
		
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);

		} catch (Exception e) {
			if (ConstantesCRM.SUCCESS.equals(destino)) {
				FormError formError = globalApp.buildFormError(e, ConstantesCRM.TELAINCIAL);
				formError.setTarget(ConstantesCRM.FRAMEAPP);
				request.setAttribute(ConstantesCRM.SFORMERROR, formError);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SERROR);
			} else {
				response.setStatus(AjaxError.STATUS_CODE_ERROR, AjaxError.MSG_ERRO);
				response.setHeader("stackTrace", AjaxError.getStackTrace(e));
				return null;
			}
		}
	}

	private void setDadosGestorGerenteContas(int x, Gestor gestor) {
		GestorGerenteContaForm[] gestorForm = getDetalhesProcessoForm().getGestorGerenteContaForm();
		gestorForm[x] = new GestorGerenteContaForm();
		if (x == 0) {
			gestorForm[x].setGerente(false);
			gestorForm[x].setGestor(true);
			gestorForm[x].setTpRelacao("GT");
		} else {
			gestorForm[x].setGerente(true);
			gestorForm[x].setGestor(false);
			gestorForm[x].setTpRelacao("GR");
		}
		gestorForm[x].setTpOperacao("A");
		gestorForm[x].setIdGestorGerente(gestor.getCodigo() != null ? gestor.getCodigo().longValue() : 0);
		gestorForm[x].setPrimeiroNome(gestor.getPrimeiroNome());
		gestorForm[x].setSobrenome(gestor.getSobrenome());

		if (gestor.getDocumentos() != null && gestor.getDocumentos().getDocumento() != null && gestor.getDocumentos().getDocumento().length > 0) {
			String nrCPF = gestor.getDocumentos().getDocumento(0).getNumeroDocumento();
			gestorForm[x].setNrCPF(StringUtilStatic.mask(nrCPF, "###.###.###-##"));
		}

		String nrTelefone = ConstantesCRM.SVAZIO;
		String nrTelefoneContato = ConstantesCRM.SVAZIO;
		String dsEmail = ConstantesCRM.SVAZIO;

		if (gestor.getContatos() != null && gestor.getContatos().getContato() != null && gestor.getContatos().getContato().length > 0) {
			nrTelefone = gestor.getContatos().getContato(0).getTextoContato();
			if (gestor.getContatos().getContato().length >= 2) {
				nrTelefoneContato = gestor.getContatos().getContato(1).getTextoContato();
				if (gestor.getContatos().getContato().length > 2) {
					dsEmail = gestor.getContatos().getContato(2).getTextoContato();
				}
			}
		}
		
		gestorForm[x].setNrTelefone(ConstantesCRM.formatPhoneNumber(nrTelefone));
		//gestorForm[x].setNrTelefone(StringUtilStatic.mask(nrTelefone, "(##)####-####"));
		gestorForm[x].setNrTelefoneContato(ConstantesCRM.formatPhoneNumber(nrTelefoneContato));
		//gestorForm[x].setNrTelefoneContato(StringUtilStatic.mask(nrTelefoneContato, "(##)####-####"));
		gestorForm[x].setDsEmail(dsEmail);
		gestorForm[x].setDtAlteracao((new SimpleDateFormat("dd/MM/yyyy")).format(gestor.getDataUltimaAlteracao().getTime()));
		gestorForm[x].setDsLoginResponsavel(gestor.getUsuarioManutencao().getLogin());
		gestorForm[x].setIdContact(gestor.getCodigoGestor());

		getDetalhesProcessoForm().setGestorGerenteContaForm(gestorForm);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluirAlterarGestorGerente.jsp"
	 */
	protected ActionForward incluirAlterarGestorGerente(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		GestorGerenteContaForm form = (GestorGerenteContaForm) formParam;

		String inGestorGerente = request.getParameter("inGestorGerente");
		String idGestorGerente = request.getParameter("idGestorGerente");
		this.gestorGerenteContaForm = new GestorGerenteContaForm();
		form = getGestorGerenteContaForm();

		if ("gestor".equals(inGestorGerente)) {
			form.setTpRelacao("GT");
		} else if ("gerente".equals(inGestorGerente)) {
			form.setTpRelacao("GR");
		}
		if (idGestorGerente != null && !ConstantesCRM.SVAZIO.equals(idGestorGerente) && !ConstantesCRM.SZERO.equals(idGestorGerente)) {
			GestorGerenteContaForm gestorForm = null;
			if ("GT".equals(form.getTpRelacao())) {
				gestorForm = getDetalhesProcessoForm().getGestorGerenteContaForm()[0];
			} else {
				gestorForm = getDetalhesProcessoForm().getGestorGerenteContaForm()[1];
			}
			gestorForm.setTpOperacao("A");
			this.gestorGerenteContaForm = gestorForm;
		} else {
			form.setTpOperacao("I");
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private boolean isClientePJ(String nrLinha, HttpServletRequest request) {
		boolean isPJ = false;
		try {
			String user = ConstantesCRM.getCurrentUser(request.getSession());
			if (nrLinha != null && nrLinha.length() > 0) {
				nrLinha = nrLinha.replaceAll("[^0-9]", "");
			}

			ParametrosVO resultadoVO = telaInicialFacade.getParametrosVO(user, nrLinha, ConstantesCRM.STWO);
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
	protected ActionForward verificaNumeroVivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		GestorGerenteContaForm form = (GestorGerenteContaForm) formParam;

		String retorno = ConstantesCRM.SVAZIO;
		String cdErro = ConstantesCRM.SZERO;
		String msErro = ConstantesCRM.SVAZIO;
		try {
			String user = ConstantesCRM.getCurrentUser(request.getSession());
			String nrLinha = request.getParameter("nrLinha");
			if (nrLinha != null && nrLinha.length() > 0) {
				nrLinha = nrLinha.replaceAll("[^0-9]", "");
			}

			ParametrosVO resultadoVO = telaInicialFacade.getParametrosVO(user, nrLinha, ConstantesCRM.STWO);
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
	 * @jpf:forward name="success" path="incluirAlterarGestorGerente.jsp"
	 */
	protected ActionForward salvarGestorGerente(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GestorGerenteContaForm form = (GestorGerenteContaForm) formParam;

		String headLog = "AtendimentoDetalheController::salvarGestorGerente::";
		String cdErro = ConstantesCRM.SVAZIO;
		String msErro = ConstantesCRM.SVAZIO;

		logger.debug(headLog + "Gravando dados de Gestor/Gerente");
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		String nrLinha = getDetalhesProcessoForm().getDadosContato().getNrLinha();
		logger.debug(headLog + "nrLinha = " + nrLinha);

		logger.debug(headLog + "Buscando dados da Linha pelo CarregaTI");
		TelaInicialVO telaInicial = telaInicialFacade.getTelaInicial(user, nrLinha);
		logger.debug(headLog + "CarregaTI - retorno OK");

		GetParametro parametro = GetParametro.getInstace();
		ParametroVO apoioParam = parametro.getParametroVO(user, "PARAMETRO_ENDPOINT_MANGESTOR");

		String endPoint = apoioParam.getDsValorParametro();
		logger.debug(headLog + "endPoint = " + endPoint);
		String nrConta = getDetalhesProcessoForm().getDadosContato().getNrConta();
		logger.debug(headLog + "nrConta = " + nrConta);
		String nrCNPJ = getDetalhesProcessoForm().getDadosCliente().getNrDocumento();
		logger.debug(headLog + "nrCNPJ = " + nrCNPJ);

		GestorContaLocator locator = new GestorContaLocator();
		GestorContaPortType type = locator.getGestorContaPort(new URL(endPoint));

		ParametrosManterGestorContaEmpresa param = new ParametrosManterGestorContaEmpresa();
		param.setNrConta(nrConta);
		param.setNrDocumentoClientePj(nrCNPJ);

		if ("GT".equals(form.getTpRelacao())) {
			param.setCdTipoGestor(ParametrosManterGestorContaEmpresaCdTipoGestor.E);
			logger.debug(headLog + "CdTipoGestor = " + ParametrosManterGestorContaEmpresaCdTipoGestor.E.toString());
		} else {
			param.setCdTipoGestor(ParametrosManterGestorContaEmpresaCdTipoGestor.A);
			logger.debug(headLog + "CdTipoGestor = " + ParametrosManterGestorContaEmpresaCdTipoGestor.A.toString());
		}
		param.setCdSistemaOrigem(ParametrosManterGestorContaEmpresaCdSistemaOrigem.FO);
		logger.debug(headLog + "CdSistemaOrigem = " + ParametrosManterGestorContaEmpresaCdSistemaOrigem.FO.toString());

		if (form.getIdGestorGerente() != 0) {
			param.setCdTipoOperacao(ParametrosManterGestorContaEmpresaCdTipoOperacao.value2);
			logger.debug(headLog + "CdTipoOperacao = " + ParametrosManterGestorContaEmpresaCdTipoOperacao.value2.toString());
			param.setIdPessoa(new BigDecimal(form.getIdGestorGerente()));
			logger.debug(headLog + "IdPessoa = " + form.getIdGestorGerente());
		} else {
			param.setCdTipoOperacao(ParametrosManterGestorContaEmpresaCdTipoOperacao.value1);
			logger.debug(headLog + "CdTipoOperacao = " + ParametrosManterGestorContaEmpresaCdTipoOperacao.value1.toString());
		}

		param.setNmNome(form.getPrimeiroNome());
		logger.debug(headLog + "Nome = " + form.getPrimeiroNome());
		param.setNmNomeMeio(form.getNomeMeio());
		logger.debug(headLog + "NmNomeMeio = " + form.getNomeMeio());
		param.setNmSobrenome(form.getSobrenome());
		logger.debug(headLog + "NmSobrenome = " + form.getSobrenome());

		String nome = form.getPrimeiroNome() + " " + form.getNomeMeio() + (!ConstantesCRM.SVAZIO.equals(form.getNomeMeio()) ? " " : ConstantesCRM.SVAZIO) + form.getSobrenome();

		param.setNmNomeCompleto(nome);
		logger.debug(headLog + "NmNomeCompleto = " + nome);
		param.setNrDocumentoGestor(form.getNrCPF().replaceAll("[^0-9]", ""));
		logger.debug(headLog + "NrDocumentoGestor = " + form.getNrCPF().replaceAll("[^0-9]", ""));
		param.setNrTelefoneCelular(form.getNrTelefone().replaceAll("[^0-9]", ""));
		logger.debug(headLog + "NrTelefoneCelular = " + form.getNrTelefone().replaceAll("[^0-9]", ""));
		param.setNrTelefoneContato(form.getNrTelefoneContato().replaceAll("[^0-9]", ""));
		logger.debug(headLog + "NrTelefoneContato = " + form.getNrTelefoneContato().replaceAll("[^0-9]", ""));
		param.setNmEmail(form.getDsEmail());
		logger.debug(headLog + "NmEmail = " + form.getDsEmail());

		if (telaInicial != null && telaInicial.getEnderecoBaseVO() != null) {
			logger.debug(headLog + "Dados de Endereço");
			EnderecoBaseVO end = telaInicial.getEnderecoBaseVO();
			param.setNmLogradouro(end.getDsEndereco());
			logger.debug(headLog + "NmLogradouro = " + end.getDsEndereco());
			param.setNmCidade(end.getDsCidade());
			logger.debug(headLog + "NmCidade = " + end.getDsCidade());
			param.setNmBairro(end.getDsBairro());
			logger.debug(headLog + "NmBairro = " + end.getDsBairro());
			param.setCdEstado(end.getUFVO().getSgUF());
			logger.debug(headLog + "CdEstado = " + end.getUFVO().getSgUF());
			param.setNrCep(end.getNrCEP());
			logger.debug(headLog + "NrCep = " + end.getNrCEP());
			param.setNrLogradouro(end.getNrEndereco());
			logger.debug(headLog + "NrLogradouro = " + end.getNrEndereco());
			param.setDsComplemento(end.getDsComplemento());
			logger.debug(headLog + "DsComplemento = " + end.getDsComplemento());
		}

		param.setIdUsuarioAlteracao(new BigDecimal(user));
		logger.debug(headLog + "IdUsuarioAlteracao = " + user);
		param.setCnctId(form.getIdContact());
		logger.debug(headLog + "CnctId = " + form.getIdContact());

		boolean retorno = true;
		try {
			logger.debug(headLog + "Chamando manterGestorContaEmpresa ");
			((GestorContaBindingStub) type).setTimeout(20 * 1000);
			Gestor gestor = type.manterGestorContaEmpresa(param);
		} catch (ErroInfo e) {
			retorno = false;
			cdErro = e.getCodigo();
			msErro = e.getDescricao();
			logger.error(headLog + "ErroInfo::" + e.getCodigo());
			logger.error(headLog + "ErroInfo::" + e.getDescricao());
		} catch (Exception e) {
			retorno = false;
			cdErro = "99";
			msErro = e.getMessage();
			logger.error(headLog + "Exception::" + e.getMessage());
			logger.error(headLog + "Exception::", e);
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
	 * @jpf:forward name="fidelizacaoIndex" path="fidelizacaoIndex.jsp"
	 * @jpf:forward name="fidelizacaoIntencaoCancelamento" path="fidelizacaoIntencaoDestino.jsp"
	 * @jpf:forward name="fidelizacaoDestinoPrevisto" path="fidelizacaoIntencaoDestino.jsp"
	 * @jpf:forward name="fidelizacaoMatrizOfertas" path="fidelizacaoMatrizOfertas.jsp"
	 * @jpf:forward name="fidelizacaoAdequacaoPlanos" path="fidelizacaoAdequacaoPlanos.jsp"
	 * @jpf:forward name="fidelizacaoBonus" path="fidelizacaoBonus.jsp"
	 * @jpf:forward name="fidelizacaoPontos" path="fidelizacaoPontos.jsp"
	 * @jpf:forward name="fidelizacaoAparelhos" path="fidelizacaoAparelhos.jsp"
	 * @jpf:forward name="fidelizacaoAparelhosFinalizacao" path="fidelizacaoAparelhosFinalizacao.jsp"
	 */
	protected ActionForward fidelizacaoCore(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FidelizacaoForm form = (FidelizacaoForm) formParam;

		String destino = request.getParameter("destino");
		getFidelizacaoForm().setDestino(destino);
		getFidelizacaoForm().setOperacaoAtual(form.getOperacaoAtual());

		try {

			if (form.getFlVoltar()) {
				if ("fidelizacaoMatrizOfertas".equals(destino)) {
					destino = "fidelizacaoIntencaoCancelamento";
				} else if ("fidelizacaoRedirecionamento".equals(destino)) {
					destino = "fidelizacaoDestinoPrevisto";
				} else if ("fidelizacaoAparelhosFinalizacao".equals(destino)) {
					destino = "fidelizacaoMatrizOfertas";
				} else if ("finalizacao".equals(destino) && "fidelizacaoAparelhosFinalizacao".equals(getFidelizacaoForm().getOperacaoAtual())) {
					destino = "fidelizacaoAparelhos";
				} else if ("finalizacao".equals(destino)) {
					destino = "fidelizacaoMatrizOfertas";
				}
				getFidelizacaoForm().setDestino(destino);
			}

			String user = ConstantesCRM.getCurrentUser(request.getSession());

			if ("fidelizacaoIntencaoCancelamento".equals(destino)) {

				if (form.getInInicioRetencao()) {
					if (!"CANCELADO".equals(getDetalhesProcessoForm().getDadosProcesso().getWFEstadoVO().getDsEstado()) && !"FECHADO".equals(getDetalhesProcessoForm().getDadosProcesso().getWFEstadoVO().getDsEstado()) && getDetalhesProcessoForm().getDadosLinha().getIdTipoLinha() != null) {
						AtendimentoVO[] atendimentoVO = new AtendimentoVO[1];
						atendimentoVO[0] = getAtendimentoVO(getDetalhesProcessoForm().getDadosProcesso().getIdAtendimento(), INICIAR_RETENCAO);
						getDetalhesProcessoForm().setWFAcaoRetornoVO(atendimentoWorkflowFacade.listaEncaminharGravar(user, atendimentoVO));
					}
					// Condição acessada quando o serviço GETLINHAS nao trouxer os dados da linha
					// necessários para a execuçao de um processo de retenção.
					if (getDetalhesProcessoForm().getDadosLinha().getIdTipoLinha() == null) {
						destino = "fidelizacaoIndex";
						request.setAttribute("msgRetorno", "Dados insuficientes para início de processo de retenção.");

						request.setAttribute("fidelizacaoForm", getFidelizacaoForm());
						request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
						return mapping.findForward(destino);
					}
					// Condição acessada quando o serviço COREWORKFLOW não permitir o tratamento do processo.
					if ("N".equals(getDetalhesProcessoForm().getWFAcaoRetornoVO().getAcaoExecucao())) {
						destino = "fidelizacaoIndex";
						request.setAttribute("msgRetorno", getDetalhesProcessoForm().getWFAcaoRetornoVO().getMensagem());
						request.setAttribute("fidelizacaoForm", getFidelizacaoForm());
						request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
						return mapping.findForward(destino);
					}
				}

				getFidelizacaoForm().setListaOfertasOriginal(null);
				if (!form.getFlVoltar()) {
					getFidelizacaoForm().setIdIntencaoSelecionada(ConstantesCRM.SVAZIO);
					getFidelizacaoForm().setIdDestinoSelecionado(ConstantesCRM.SVAZIO);
					getFidelizacaoForm().setListaOfertas(FidelizacaoListaGeralDescricaoVO.Factory.newInstance());
					getFidelizacaoForm().setListaOfertasRealizadas(FidelizacaoListaGeralDescricaoVO.Factory.newInstance());
					getFidelizacaoForm().setListaOfertasAceitas(FidelizacaoListaGeralDescricaoVO.Factory.newInstance());
					getFidelizacaoForm().setListaLinhasAssociadasTratadas(ListaDetalheLinhaVO.Factory.newInstance());
					getFidelizacaoForm().setMensagemEncerramentoVO(MensajeEncerraVO.Factory.newInstance());
					getFidelizacaoForm().setListaIntencoesCancelamento(fidelizacaoFacade.getIntencaoCancelamento(user, getDetalhesProcessoForm().getDadosLinha().getIdUFOperadora(), getDetalhesProcessoForm().getParametros().getInTipoPessoa(), getDetalhesProcessoForm().getDadosLinha().getIdSegmentacao(), getDetalhesProcessoForm().getListaLinhasAssociadas().getIdGrupoAbertura(), getDetalhesProcessoForm().getDadosLinha().getIdTipoLinha()));
				}
			} else if ("fidelizacaoDestinoPrevisto".equals(destino)) {
				if (!form.getFlVoltar()) {
					getFidelizacaoForm().setIdIntencaoSelecionada(form.getIdIntencaoSelecionada());
					getFidelizacaoForm().setListaDestinosPrevistos(fidelizacaoFacade.getDestinosPrevistos(user, getDetalhesProcessoForm().getDadosLinha().getIdUFOperadora(), getFidelizacaoForm().getIdIntencaoSelecionada(), getDetalhesProcessoForm().getParametros().getInTipoPessoa(), getDetalhesProcessoForm().getDadosLinha().getIdSegmentacao(), getDetalhesProcessoForm().getListaLinhasAssociadas().getIdGrupoAbertura(), getDetalhesProcessoForm().getDadosLinha().getIdTipoLinha()));
				}
			} else if ("fidelizacaoMatrizOfertas".equals(destino)) {
				if (!form.getFlVoltar()) {
					getFidelizacaoForm().setListaOfertasRealizadas(null);
					getFidelizacaoForm().setListaOfertasAceitas(null);
					getFidelizacaoForm().setIdDestinoSelecionado(form.getIdDestinoSelecionado());
					String[] dados = { getDetalhesProcessoForm().getDadosLinha().getIdUFOperadora(), getDetalhesProcessoForm().getDadosLinha().getIdSegmentacao(), (getDetalhesProcessoForm().getDadosCliente().getIdTipoPessoa() == 2) ? "PJ" : "PF", getFidelizacaoForm().getIdIntencaoSelecionada(), getFidelizacaoForm().getIdDestinoSelecionado(), getDetalhesProcessoForm().getListaLinhasAssociadas().getIdGrupoAbertura() };
					getFidelizacaoForm().setListaOfertasOriginal(ofertaRealizadaFacade.getOfertasDisponiveis(user, dados, getDetalhesProcessoForm().getDadosLinha().getIdTipoLinha()));
					FidelizacaoListaGeralDescricaoVO listaOfertasDisponiveis = FidelizacaoListaGeralDescricaoVO.Factory.newInstance();
					listaOfertasDisponiveis.set(getFidelizacaoForm().getListaOfertasOriginal().copy());
					getFidelizacaoForm().setListaOfertas(listaOfertasDisponiveis);
				}

			} else if ("fidelizacaoAparelhos".equals(destino)) {
				if (!form.getFlVoltar()) {
					getFidelizacaoForm().setAparelhoSelecionado(Aparelhos.Factory.newInstance());
					getFidelizacaoForm().setInExcecao(form.getInExcecao());
				}
			} else if ("fidelizacaoRedirecionamento".equals(destino)) {

				String[] indexesOfertasDisponiveis = form.getOfertasDisponiveis();
				String[] indexesOfertasRealizadas = form.getOfertasRealizadas();
				String[] indexesOfertasAceitas = form.getOfertasAceitas();

				getFidelizacaoForm().setListaOfertas(getFidelizacaoListaGeralDescricaoVO(indexesOfertasDisponiveis));
				getFidelizacaoForm().setListaOfertasRealizadas(getFidelizacaoListaGeralDescricaoVO(indexesOfertasRealizadas));
				getFidelizacaoForm().setListaOfertasAceitas(getFidelizacaoListaGeralDescricaoVO(indexesOfertasAceitas));

				destino = manageFormularioMatriz(getFidelizacaoForm().getListaOfertasAceitas().getItemListaDescricaoVOArray(0).getSigla(), user);
			} else if ("fidelizacaoAparelhosFinalizacao".equals(destino)) {

				getFidelizacaoForm().setAparelhoSelecionado(form.getAparelhoSelecionado());
				getFidelizacaoForm().setInExcecao(form.getInExcecao());
				getFidelizacaoForm().setDsTipoPagamento(form.getDsTipoPagamento());

				if (COMODATO.equals(getFidelizacaoForm().getListaOfertasAceitas().getItemListaDescricaoVOArray(0).getSigla())) {
					getFidelizacaoForm().getAparelhoSelecionado().setNrParcelas(ConstantesCRM.SZERO);
					getFidelizacaoForm().getAparelhoSelecionado().setVlPrecoAbs((new DecimalFormat("###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")))).format(new Double(getFidelizacaoForm().getAparelhoSelecionado().getVlPrecoAbs())));
				}

				TelaInicialVO telaInicial = telaInicialFacade.getTelaInicial(user, getDetalhesProcessoForm().getDadosLinha().getNumero());

				getFidelizacaoForm().getEnderecoEntrega().setDsEndereco(telaInicial.getEnderecoBaseVO().getDsEndereco().trim());
				getFidelizacaoForm().getEnderecoEntrega().setNrEndereco(telaInicial.getEnderecoBaseVO().getNrEndereco().trim());
				getFidelizacaoForm().getEnderecoEntrega().setDsComplemento(telaInicial.getEnderecoBaseVO().getDsComplemento().trim());
				getFidelizacaoForm().getEnderecoEntrega().setDsBairro(telaInicial.getEnderecoBaseVO().getDsBairro().trim());
				getFidelizacaoForm().getEnderecoEntrega().setDsCidade(telaInicial.getEnderecoBaseVO().getDsCidade().trim());
				getFidelizacaoForm().getEnderecoEntrega().setDsUF(telaInicial.getEnderecoBaseVO().getUFVO().getSgUF());
				getFidelizacaoForm().getEnderecoEntrega().setDsCEP(CommonsPortabilidade.formatarCep(telaInicial.getEnderecoBaseVO().getNrCEP()));

				getFidelizacaoForm().setListaRegionais(ofertaAparelhoFacade.getPesquisaEnderecoIni(user).getUFVOArray());

			}

			getFidelizacaoForm().setDestino(destino);

			request.setAttribute("fidelizacaoForm", getFidelizacaoForm());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);

		} catch (Exception e) {
			response.setStatus(AjaxError.STATUS_CODE_ERROR, AjaxError.MSG_ERRO);
			response.setHeader("stackTrace", AjaxError.getStackTrace(e));
			return null;
		}
	}

	private AtendimentoVO getAtendimentoVO(String idAtendimento, int idAtividade) {
		AtendimentoVO atendimentoVO = AtendimentoVO.Factory.newInstance();
		atendimentoVO.addNewAtendimentoWorkflowVO().addNewAtendimentoWorkflowComumVO().setDsObservacao(ConstantesCRM.SVAZIO);
		atendimentoVO.getAtendimentoWorkflowVO().getAtendimentoWorkflowComumVO().addNewWFGrupoVO();
		atendimentoVO.getAtendimentoWorkflowVO().getAtendimentoWorkflowComumVO().addNewWFMotivoVO();
		atendimentoVO.addNewAtdWFVO().setIdAtendimento(idAtendimento);
		atendimentoVO.addNewWFAcaoVO().setIdAtividade(String.valueOf(idAtividade));
		atendimentoVO.getWFAcaoVOArray(0).setDsTipoAtividade("PORTOUT");
		atendimentoVO.getWFAcaoVOArray(0).setInCRI(ConstantesCRM.SZERO);

		return atendimentoVO;
	}

	private FidelizacaoListaGeralDescricaoVO getFidelizacaoListaGeralDescricaoVO(String[] idListaDescricaoVO) {
		FidelizacaoListaGeralDescricaoVO flgdVO = FidelizacaoListaGeralDescricaoVO.Factory.newInstance();
		for (int i = 0; i < idListaDescricaoVO.length; i++) {
			for (int j = 0; j < getFidelizacaoForm().getListaOfertasOriginal().getItemListaDescricaoVOArray().length; j++) {
				if (getFidelizacaoForm().getListaOfertasOriginal().getItemListaDescricaoVOArray(j).getId().equals(idListaDescricaoVO[i])) {
					flgdVO.addNewItemListaDescricaoVO().set(getFidelizacaoForm().getListaOfertasOriginal().getItemListaDescricaoVOArray(j).copy());
					break;
				}
			}
		}
		return flgdVO;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 */
	protected ActionForward atualizaMensagem(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FidelizacaoForm form = (FidelizacaoForm) formParam;

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		ActionRedirect f = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));

		if (!ConstantesCRM.SVAZIO.equals(form.getMensagemEncerramentoVO().getDsObservacao())) {
			getFidelizacaoForm().setMensagemEncerramentoVO(form.getMensagemEncerramentoVO());
			getFidelizacaoForm().getMensagemEncerramentoVO().setIdRetencao(getFidelizacaoForm().getRetornoVO().getValor());
			try {
				ofertaRealizadaFacade.setObservacao(user, getFidelizacaoForm().getMensagemEncerramentoVO());
			} catch (Exception e) {
			}
		}

		f.addParameter("voltar", "false");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return f;

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="mensagemEncerramento.jsp"
	 * @jpf:forward name="novaOferta" path="getDetalheProcesso.do"
	 */
	protected ActionForward fidelizacaoRetencao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FidelizacaoForm form = (FidelizacaoForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			ActionRedirect forward = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
			RetornoVO retornoVO = RetornoVO.Factory.newInstance();
			FinalizaRetencaoVO finalizaRetencaoVO = FinalizaRetencaoVO.Factory.newInstance();

			// Usuário selecionou opção "Nova Oferta" para processo agrupado
			if ("true".equals(request.getParameter("flNovaOfertaProcessosAgrupados"))) {
				// Atualiza observação se for necessário
				if (!ConstantesCRM.SVAZIO.equals(form.getMensagemEncerramentoVO().getDsObservacao())) {
					getFidelizacaoForm().setMensagemEncerramentoVO(form.getMensagemEncerramentoVO());
					getFidelizacaoForm().getMensagemEncerramentoVO().setIdRetencao(getFidelizacaoForm().getRetornoVO().getValor());
					try {
						ofertaRealizadaFacade.setObservacao(user, getFidelizacaoForm().getMensagemEncerramentoVO());
					} catch (Exception e) {
					}
				}
				forward = new ActionRedirect(mapping.findForward("novaOferta"));
				forward.addParameter("nrLinha", request.getParameter("nrLinha"));
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return forward;
			}

			if (form.getInMesmaOferta()) {

				// Insere comentario apenas na primeira chamada
				if (getFidelizacaoForm().getListaLinhasAssociadasTratadas().getDetalheLinhaVOArray().length == 0) {
					getFidelizacaoForm().setMensagemEncerramentoVO(form.getMensagemEncerramentoVO());
					getFidelizacaoForm().getMensagemEncerramentoVO().setIdRetencao(getFidelizacaoForm().getRetornoVO().getValor());
					try {
						ofertaRealizadaFacade.setObservacao(user, getFidelizacaoForm().getMensagemEncerramentoVO());
					} catch (Exception e) {
					}
				}

				// Tratamento para protocolos agrupados
				for (int i = 0; i < form.getNrLinhasMesmaOferta().length; i++) {

					finalizaRetencaoVO.set(getFidelizacaoForm().getFinalizaRetencaoVO().copy());
					setDadosLinhaAssociadaByNrLinha(form.getNrLinhasMesmaOferta()[i]);

					finalizaRetencaoVO.getCliente().setIdPessoaDePara(String.valueOf(getDetalhesProcessoForm().getDadosLinha().getIdPessoaDePara()));
					finalizaRetencaoVO.getCliente().setIdUFOperadora(getDetalhesProcessoForm().getDadosLinha().getIdUFOperadora());
					finalizaRetencaoVO.getCliente().setIdLinhaTelefonica(getDetalhesProcessoForm().getDadosLinha().getIdLinha());
					finalizaRetencaoVO.getCliente().setNrLinha(getDetalhesProcessoForm().getDadosLinha().getNumero());
					finalizaRetencaoVO.getCliente().setIdSegmentacao(getDetalhesProcessoForm().getDadosLinha().getIdSegmentacao());
					finalizaRetencaoVO.getRetencao().getRetencaoHeader().setNrProtocoloPortabilidade(Long.parseLong(getDetalhesProcessoForm().getDadosLinha().getNrProtocoloPortabilidade()));

					retornoVO = ofertaRealizadaFacade.finalizaRetencao(user, finalizaRetencaoVO);
					getFidelizacaoForm().getListaLinhasAssociadasTratadas().addNewDetalheLinhaVO().setNumero(finalizaRetencaoVO.getCliente().getNrLinha());

				}

			} else {
				finalizaRetencaoVO = getFinalizaRetencaoVO(request);
			}

			if (!form.getInMesmaOferta()) {

				finalizaRetencaoVO.getRetencao().getRetencaoHeader().setInExcecao(form.getInExcecao() ? ConstantesCRM.SONE : ConstantesCRM.SZERO);

				finalizaRetencaoVO.getRetencao().getRetencaoHeader().setIdTipoEncerramento(String.valueOf(TIPO_ENCERRAMENTO_RETIDO_PORTABILIDADE));

				if ("fidelizacaoAdequacaoPlanos".equals(form.getDestino())) {

					getFidelizacaoForm().setPlanoSelecionado(getFidelizacaoForm().getListaPlanoVO().getPlanoVOArray(Integer.parseInt(form.getPlanoSelecionado().getId())));

					Planos dadosPlanos = Planos.Factory.newInstance();
					dadosPlanos.setIdNovoPlano(getFidelizacaoForm().getPlanoSelecionado().getId());
					dadosPlanos.setNovoPlano(getFidelizacaoForm().getPlanoSelecionado().getDescricao());
					dadosPlanos.setPlanoAtual(getDetalhesProcessoForm().getDadosLinha().getPlano());

					finalizaRetencaoVO.getRetencao().getRetencaoBody().addNewOfertas().setPlanos(dadosPlanos);

				} else if ("fidelizacaoBonus".equals(form.getDestino())) {

					getFidelizacaoForm().setDtInicioVigencia(form.getDtInicioVigencia());
					getFidelizacaoForm().setDtFimVigencia(form.getDtFimVigencia());

					getFidelizacaoForm().setBonusSelecionado(getFidelizacaoForm().getListaBonusVO().getBonusVOArray(Integer.parseInt(form.getBonusSelecionado().getIdBonus())));

					Bonus dadosBonus = Bonus.Factory.newInstance();
					dadosBonus.setIdMatrizBonus(getFidelizacaoForm().getBonusSelecionado().getIdBonus());
					dadosBonus.setNmBonus(getFidelizacaoForm().getBonusSelecionado().getDescricao());
					dadosBonus.setDtInicioVigencia(getFidelizacaoForm().getDtInicioVigencia());
					dadosBonus.setDtFinalVigencia(getFidelizacaoForm().getDtFimVigencia());

					finalizaRetencaoVO.getRetencao().getRetencaoBody().addNewOfertas().setBonus(dadosBonus);

				} else if ("fidelizacaoPontos".equals(form.getDestino())) {

					getFidelizacaoForm().setDadosPontos(form.getDadosPontos());
					finalizaRetencaoVO.getRetencao().getRetencaoBody().addNewOfertas().setPontos(getFidelizacaoForm().getDadosPontos());

				} else if ("fidelizacaoAparelhosFinalizacao".equals(form.getDestino())) {

					if (form.getEnderecoEntrega().getDsEndereco() != null) {
						String endereco = new StringBuffer(form.getEnderecoEntrega().getDsEndereco()).append(";").append(form.getEnderecoEntrega().getNrEndereco()).append(";").append(form.getEnderecoEntrega().getDsComplemento()).append(";").append(form.getEnderecoEntrega().getDsBairro()).append(";").append(form.getEnderecoEntrega().getDsCEP()).append(";").append(form.getEnderecoEntrega().getDsCidade()).append(";").append(form.getEnderecoEntrega().getDsUF()).toString();
						getFidelizacaoForm().getAparelhoSelecionado().setDsEnderecoEntrega(endereco);
						getFidelizacaoForm().getAparelhoSelecionado().setNmTerceiro(form.getAparelhoSelecionado().getNmTerceiro());
						getFidelizacaoForm().getAparelhoSelecionado().setNrContatoTelefone(form.getAparelhoSelecionado().getNrContatoTelefone());
						getFidelizacaoForm().getAparelhoSelecionado().setDsDocumentoTerceiro(form.getAparelhoSelecionado().getDsDocumentoTerceiro());

						// Dados de endereco necessários. Apesar de esta no node de Analise, neste momento
						// os dados não são utilizados para análise.
						finalizaRetencaoVO.getRetencao().getRetencaoBody().addNewAnalise().addNewEndereco();
						finalizaRetencaoVO.getRetencao().getRetencaoBody().getAnalise().getEndereco().setDsEndereco(form.getEnderecoEntrega().getDsEndereco());
						finalizaRetencaoVO.getRetencao().getRetencaoBody().getAnalise().getEndereco().setNrEndereco(form.getEnderecoEntrega().getNrEndereco());
						finalizaRetencaoVO.getRetencao().getRetencaoBody().getAnalise().getEndereco().setDsComplemento(form.getEnderecoEntrega().getDsComplemento());
						finalizaRetencaoVO.getRetencao().getRetencaoBody().getAnalise().getEndereco().setDsBairro(form.getEnderecoEntrega().getDsBairro());
						finalizaRetencaoVO.getRetencao().getRetencaoBody().getAnalise().getEndereco().setDsCidade(form.getEnderecoEntrega().getDsCidade());
						finalizaRetencaoVO.getRetencao().getRetencaoBody().getAnalise().getEndereco().setDsUF(form.getEnderecoEntrega().getDsUF());
						finalizaRetencaoVO.getRetencao().getRetencaoBody().getAnalise().getEndereco().setDsCEP(form.getEnderecoEntrega().getDsCEP());

					} else {

						getFidelizacaoForm().getAparelhoSelecionado().setDtRetirada(form.getAparelhoSelecionado().getDtRetirada());
						getFidelizacaoForm().getAparelhoSelecionado().setNmLoja(form.getAparelhoSelecionado().getNmLoja());
						getFidelizacaoForm().getAparelhoSelecionado().setQtdEstoqueLoja(form.getAparelhoSelecionado().getQtdEstoqueLoja());
					}

					finalizaRetencaoVO.getRetencao().getRetencaoBody().addNewOfertas().setAparelhos(getFidelizacaoForm().getAparelhoSelecionado());

				} else {

					// Para outros tipos de ofertas cujo formulário é inexistente
					// Ex: Migração, Argumentação e Suspensão Temporária

					if (request.getParameter("vaiPensarExtra") == null) {

						String[] indexesOfertasDisponiveis = form.getOfertasDisponiveis();
						String[] indexesOfertasRealizadas = form.getOfertasRealizadas();
						String[] indexesOfertasAceitas = form.getOfertasAceitas();

						getFidelizacaoForm().setListaOfertas(getFidelizacaoListaGeralDescricaoVO(indexesOfertasDisponiveis));
						getFidelizacaoForm().setListaOfertasRealizadas(getFidelizacaoListaGeralDescricaoVO(indexesOfertasRealizadas));
						getFidelizacaoForm().setListaOfertasAceitas(getFidelizacaoListaGeralDescricaoVO(indexesOfertasAceitas));

					}

					finalizaRetencaoVO = getFinalizaRetencaoVO(request);
					finalizaRetencaoVO.getRetencao().getRetencaoHeader().setInExcecao(ConstantesCRM.SZERO);

					int idTipoEncerramento;
					if ("fidelizacaoVaiPensar".equals(form.getDestino())) {
						idTipoEncerramento = TIPO_ENCERRAMENTO_VAIPENSAR_PORTABILIDADE;
					} else if ("fidelizacaoNaoRetido".equals(form.getDestino())) {
						idTipoEncerramento = TIPO_ENCERRAMENTO_NAO_RETIDO_PORTABILIDADE;
					} else {
						idTipoEncerramento = TIPO_ENCERRAMENTO_RETIDO_PORTABILIDADE;
					}

					finalizaRetencaoVO.getRetencao().getRetencaoHeader().setIdTipoEncerramento(String.valueOf(idTipoEncerramento));

				}

				if (getFidelizacaoForm().getAparelhoSelecionado().getDsEnderecoEntrega() != null) {
					enviarDadosSAP(getDetalhesProcessoForm().getDadosLinha().getNumero(), request);
				}

				retornoVO = ofertaRealizadaFacade.finalizaRetencao(user, finalizaRetencaoVO);
				getFidelizacaoForm().setRetornoVO(retornoVO);
				getFidelizacaoForm().setFinalizaRetencaoVO(finalizaRetencaoVO);
				carregarMsgEcerramento(user, String.valueOf(TIPO_ENCERRAMENTO_RETIDO_PORTABILIDADE), getDetalhesProcessoForm().getDadosLinha().getIdUFOperadora());

			}

			if (!form.getInMesmaOferta()) {
				getFidelizacaoForm().setOperacaoAtual(form.getDestino());
				getFidelizacaoForm().setDestino("fidelizacaoMensagemEncerramento");
				getFidelizacaoForm().setListaLinhasAssociadas(getDetalhesProcessoForm().getListaLinhasAssociadas());
			}

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			response.setStatus(AjaxError.STATUS_CODE_ERROR, AjaxError.MSG_ERRO);
			response.setHeader("stackTrace", AjaxError.getStackTrace(e));
			return null;
		}
	}

	private RetencaoRetornoSAPVO enviarDadosSAP(String nrLinha, HttpServletRequest request) throws Exception {

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		try {

			RetencaoSAPVO rSAPVO = RetencaoSAPVO.Factory.newInstance();
			CLIENTE sapCliente = CLIENTE.Factory.newInstance();
			ENDERECO sapEndereco = ENDERECO.Factory.newInstance();
			PRODUTO sapProduto = PRODUTO.Factory.newInstance();

			// inExecutar 0-Procedimento normal; 1->Baixa; 2-Estorno;
			rSAPVO.setInExecutar(ConstantesCRM.SZERO);

			// inRetornoOld 0-Procedimento normal; 1->Estoque ja realizado
			rSAPVO.setInRetornoOld(ConstantesCRM.SZERO);

			sapCliente.setIDPESSOA(String.valueOf(getDetalhesProcessoForm().getDadosCliente().getIdPessoa()));
			sapCliente.setIDGRUPO(getDetalhesProcessoForm().getListaLinhasAssociadas().getIdGrupoAbertura());
			sapCliente.setDOCUMENTO(getFidelizacaoForm().getAparelhoSelecionado().getDsDocumentoTerceiro());
			sapCliente.setNOME(getDetalhesProcessoForm().getDadosCliente().getNome());
			sapCliente.setTELEFONE(getDetalhesProcessoForm().getDadosLinha().getNumero());
			sapCliente.setTIPODOC("RG");
			sapCliente.setIDSEGMENTACAO(getDetalhesProcessoForm().getDadosLinha().getIdSegmentacao());
			sapCliente.setIDTIPOCARTEIRA(String.valueOf(getDetalhesProcessoForm().getDadosCliente().getCarterizacaoVO().getIdTipoCarteira()));
			sapCliente.setSGTIPOPESSOA((getDetalhesProcessoForm().getDadosCliente().getIdTipoPessoa() == 2) ? "PJ" : "PF");

			sapEndereco.setNOME(getDetalhesProcessoForm().getDadosCliente().getNome());
			sapEndereco.setRUACLI(new StringBuffer(getFidelizacaoForm().getEnderecoEntrega().getDsEndereco()).append(", ").append(getFidelizacaoForm().getEnderecoEntrega().getNrEndereco()).append(testarValor(getFidelizacaoForm().getEnderecoEntrega().getDsComplemento()) ? " - " : "").append(testarValor(getFidelizacaoForm().getEnderecoEntrega().getDsComplemento()) ? getFidelizacaoForm().getEnderecoEntrega().getDsComplemento() : "").toString());
			sapEndereco.setBAIRROCLI(getFidelizacaoForm().getEnderecoEntrega().getDsBairro());
			sapEndereco.setCIDADECLI(getFidelizacaoForm().getEnderecoEntrega().getDsCidade());
			sapEndereco.setCEPCLI(getFidelizacaoForm().getEnderecoEntrega().getDsCEP());
			sapEndereco.setESTADOCLI(getFidelizacaoForm().getEnderecoEntrega().getDsUF());
			sapEndereco.setPAISCLI(ConstantesCRM.SONE);

			sapEndereco.setBAIRROENT(getFidelizacaoForm().getEnderecoEntrega().getDsBairro());
			sapEndereco.setCEPENT(getFidelizacaoForm().getEnderecoEntrega().getDsCEP());
			sapEndereco.setCIDADEENT(getFidelizacaoForm().getEnderecoEntrega().getDsCidade());
			sapEndereco.setESTADOENT(getFidelizacaoForm().getEnderecoEntrega().getDsUF());
			sapEndereco.setPAISENT(ConstantesCRM.SONE);
			sapEndereco.setRUAENT(sapEndereco.getRUACLI());

			sapProduto.setIDAPARELHO(getFidelizacaoForm().getAparelhoSelecionado().getIdAparelhoCor());
			sapProduto.setCONDPGTO(getFidelizacaoForm().getAparelhoSelecionado().getNrParcelas());
			sapProduto.setDATAREMESSA(new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date()));
			sapProduto.setMEIOPAGTO(getFidelizacaoForm().getAparelhoSelecionado().getIdTipoPagamentoAparelho());
			sapProduto.setPRZVIGENCIA(getFidelizacaoForm().getAparelhoSelecionado().getPrzVigencia());
			sapProduto.setSGOFERTA(getFidelizacaoForm().getListaOfertasAceitas().getItemListaDescricaoVOArray(0).getSigla());
			sapProduto.setNUMEROPEDIDO(ConstantesCRM.SVAZIO);

			sapProduto.setQUANTIDADE(ConstantesCRM.SONE);
			sapProduto.setVALOR(ConstantesCRM.STHREE.equals(getFidelizacaoForm().getAparelhoSelecionado().getIdTipoPagamentoAparelho()) ? "" : isEmpty(getFidelizacaoForm().getAparelhoSelecionado().getVlPrecoAbs()) ? "" : formatarDecimal(getFidelizacaoForm().getAparelhoSelecionado().getVlPrecoAbs(), 1));
			sapProduto.setMATERIAL(getFidelizacaoForm().getAparelhoSelecionado().getCdSapAparelho());

			sapProduto.setNUMEROPEDIDO(getDetalhesProcessoForm().getDetalheProcesso().getNrProtocolo());
			sapProduto.setHORAMANHAINI(ConstantesCRM.SVAZIO);
			sapProduto.setHORAMANHAFIM(ConstantesCRM.SVAZIO);
			sapProduto.setHORATARDEINI(ConstantesCRM.SVAZIO);
			sapProduto.setHORATARDEFIM(ConstantesCRM.SVAZIO);
			sapProduto.setPONTOS(ConstantesCRM.SVAZIO);
			sapProduto.setOBSERVACAO(testarValor(getFidelizacaoForm().getAparelhoSelecionado().getNmTerceiro()) ? new StringBuffer(getFidelizacaoForm().getAparelhoSelecionado().getNmTerceiro()).append(";RG:").append(getFidelizacaoForm().getAparelhoSelecionado().getDsDocumentoTerceiro()).append(";").append(getFidelizacaoForm().getAparelhoSelecionado().getNrContatoTelefone()).toString() : "");
			sapProduto.setLINHA(getDetalhesProcessoForm().getDadosLinha().getNumero());

			rSAPVO.setCLIENTE(sapCliente);
			rSAPVO.setENDERECO(sapEndereco);
			rSAPVO.setPRODUTO(sapProduto);

			return ofertaAparelhoFacade.getComSAP(user, rSAPVO);

		} catch (Exception e) {
			RetencaoRetornoSAPVO retorno = RetencaoRetornoSAPVO.Factory.newInstance();
			retorno.setErro(ConstantesCRM.SZERO);
			retorno.setDescErro("SAP indisponível, por favor tentar via sistema legado.");
			return retorno;
		}
	}

	private boolean isEmpty(String valor) {
		return (null == valor || ConstantesCRM.SVAZIO.equals(valor));
	}

	private boolean testarValor(String valor) {
		return (valor != null && !ConstantesCRM.SVAZIO.equals(valor));
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="fidelizacaoListaAparelhos.jsp"
	 */
	protected ActionForward getListaAparelhos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		FidelizacaoForm form = (FidelizacaoForm) formParam;

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		try {

			OfAparelhoParamVO ofAparelhoParamVO = getOfAparelhoParamVO(user, getFidelizacaoForm().getListaOfertasAceitas().getItemListaDescricaoVOArray(0).getSigla(), false);

			getFidelizacaoForm().setOfertaAparelhoVO(ofertaAparelhoFacade.getAparelho(user, ofAparelhoParamVO));

			for (int i = 0; i < getFidelizacaoForm().getOfertaAparelhoVO().getItemOfertaAparelhoVOArray().length; i++) {
				getFidelizacaoForm().getOfertaAparelhoVO().getItemOfertaAparelhoVOArray(i).setPreco(formatarDecimal(getFidelizacaoForm().getOfertaAparelhoVO().getItemOfertaAparelhoVOArray(i).getPreco(), 0));
				getFidelizacaoForm().getOfertaAparelhoVO().getItemOfertaAparelhoVOArray(i).setMulta(formatarDecimal(getFidelizacaoForm().getOfertaAparelhoVO().getItemOfertaAparelhoVOArray(i).getMulta(), 0));
			}

		} catch (Exception e) {
			response.setStatus(AjaxError.STATUS_CODE_ERROR, AjaxError.MSG_ERRO);
			response.setHeader("stackTrace", AjaxError.getStackTrace(e));
			return null;
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="fidelizacaoListaAparelhos.jsp"
	 */
	protected ActionForward getListaDescontosParcelas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		try {
			FidelizacaoForm form = (FidelizacaoForm) formParam;

			ItemOfertaAparelhoVO dadosAparelhoSelecionado;
			String user = ConstantesCRM.getCurrentUser(request.getSession());
			FidelizacaoListaGeralVO listaParcelas;
			FidelizacaoListaGeralVO listaDescontos;

			StringBuffer xmlOut = new StringBuffer();
			int indexAparelho = Integer.parseInt(request.getParameter("indexAparelho").toString());
			dadosAparelhoSelecionado = getFidelizacaoForm().getOfertaAparelhoVO().getItemOfertaAparelhoVOArray(indexAparelho);

			getFidelizacaoForm().getAparelhoSelecionado().setVlPercentualDesconto(ConstantesCRM.SVAZIO);
			getFidelizacaoForm().getAparelhoSelecionado().setVlParcela(ConstantesCRM.SVAZIO);
			getFidelizacaoForm().getAparelhoSelecionado().setNrParcelas(ConstantesCRM.SVAZIO);
			getFidelizacaoForm().getAparelhoSelecionado().setVlPrecoAbs(ConstantesCRM.SVAZIO);

			getFidelizacaoForm().getAparelhoSelecionado().setNmModeloAparelho(dadosAparelhoSelecionado.getModelo());
			getFidelizacaoForm().getAparelhoSelecionado().setNmCor(dadosAparelhoSelecionado.getCor());
			getFidelizacaoForm().getAparelhoSelecionado().setCdSapAparelho(dadosAparelhoSelecionado.getSAP());
			getFidelizacaoForm().getAparelhoSelecionado().setIdAparelhoCor(dadosAparelhoSelecionado.getIdAparelhoCor());

			xmlOut.append("<xmlObject>");
			xmlOut.append("<idMatrizAparelho>").append(dadosAparelhoSelecionado.getIdMatrizAparelho()).append("</idMatrizAparelho>");
			xmlOut.append("<nmModeloAparelho>").append(dadosAparelhoSelecionado.getModelo()).append("</nmModeloAparelho>");
			xmlOut.append("<nmCor>").append(dadosAparelhoSelecionado.getCor()).append("</nmCor>");
			xmlOut.append("<cdSapAparelho>").append(dadosAparelhoSelecionado.getSAP()).append("</cdSapAparelho>");
			xmlOut.append("<idAparelhoCor>").append(dadosAparelhoSelecionado.getIdAparelhoCor()).append("</idAparelhoCor>");
			xmlOut.append("<vlCalcularDesconto>").append(dadosAparelhoSelecionado.getPreco().substring(0, dadosAparelhoSelecionado.getPreco().indexOf(",") == -1 ? dadosAparelhoSelecionado.getPreco().indexOf(".") : dadosAparelhoSelecionado.getPreco().indexOf(","))).append("</vlCalcularDesconto>");

			if (!COMODATO.equals(getFidelizacaoForm().getListaOfertasAceitas().getItemListaDescricaoVOArray(0).getSigla())) {

				listaParcelas = ofertaAparelhoFacade.getParcelas(user, dadosAparelhoSelecionado.getIdMatrizAparelho(), form.getInExcecao() ? ConstantesCRM.SZERO : ConstantesCRM.SONE, getDetalhesProcessoForm().getParametros().getInTipoPessoa());

				ParametrosVO parametrosVO = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
				int idSegmentacao = 0;
				try {
					if (parametrosVO == null || parametrosVO.getIdSegmentacao() == null || ConstantesCRM.SVAZIO.equals(parametrosVO.getIdSegmentacao())) {
						idSegmentacao = Integer.parseInt(getDetalhesProcessoForm().getDadosLinha() != null && getDetalhesProcessoForm().getDadosLinha().getIdSegmentacao() != null && !ConstantesCRM.SVAZIO.equals(getDetalhesProcessoForm().getDadosLinha().getIdSegmentacao()) ? getDetalhesProcessoForm().getDadosLinha().getIdSegmentacao() : ConstantesCRM.SZERO);
					} else {
						idSegmentacao = Integer.parseInt(parametrosVO.getIdSegmentacao());
					}
				} catch (Exception e) {
				}

				listaDescontos = ofertaAparelhoFacade.getPercDesconto(user, dadosAparelhoSelecionado.getIdMatrizAparelho(), form.getInExcecao() ? ConstantesCRM.SZERO : ConstantesCRM.SONE, idSegmentacao);

				getFidelizacaoForm().setListaParcelas(listaParcelas.getItemListaVOArray());
				getFidelizacaoForm().setListaPercentuaisDesconto(listaDescontos.getItemListaVOArray());

				XmlOptions xmlOptions = new XmlOptions();
				xmlOptions.setSaveAggresiveNamespaces();
				Map map = new HashMap();
				map.put("fidelizacao.fo.vivo.com.br/vo", ConstantesCRM.SVAZIO);
				xmlOptions.setSaveImplicitNamespaces(map);

				xmlOut.append("<listaParcelas>").append(listaParcelas.xmlText(xmlOptions));
				xmlOut.append("</listaParcelas>");

				xmlOut.append("<listaDescontos>").append(listaDescontos.xmlText(xmlOptions));
				xmlOut.append("</listaDescontos>");

			}

			xmlOut.append("</xmlObject>");
			response.setContentType(ConstantesCRM.SContentType);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			bufferedOutputStream.write(xmlOut.toString().replaceAll("xml-fragment", "xmlFragment").replaceAll("tns:", "").replaceAll("undefined", "xmlObject").getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();

		} catch (Exception e) {
			response.setStatus(AjaxError.STATUS_CODE_ERROR, AjaxError.MSG_ERRO);
			response.setHeader("stackTrace", AjaxError.getStackTrace(e));
			return null;
		}

		return null;

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="selecaoProcessosAgrupados.jsp"
	 */
	protected ActionForward getListaDdescontosParcelas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		FidelizacaoForm form = (FidelizacaoForm) formParam;

		return null;
	}

	private void carregarMsgEcerramento(String user, String tipoEncerramento, String idUFOperadora) throws Exception {

		try {

			getFidelizacaoForm().setListaMensagensEncerramento(ofertaRealizadaFacade.getMsgEncerramento(user, idUFOperadora, tipoEncerramento));

		} catch (Exception e) {
			throw e;
		}
	}

	private void setDadosLinhaAssociadaByNrLinha(String nrLinha) {
		for (int i = 0; i < getDetalhesProcessoForm().getListaLinhasAssociadas().getDetalheLinhaVOArray().length; i++) {
			if (getDetalhesProcessoForm().getListaLinhasAssociadas().getDetalheLinhaVOArray(i).getNumero().equals(nrLinha)) {
				getDetalhesProcessoForm().setDadosLinha(getDetalhesProcessoForm().getListaLinhasAssociadas().getDetalheLinhaVOArray(i));
				break;
			}
		}
	}

	private String manageFormularioMatriz(String sgOferta, String user) throws Exception {

		String destino = ConstantesCRM.SVAZIO;

		try {

			if (BONUS.equals(sgOferta)) {
				destino = "fidelizacaoBonus";
				getFidelizacaoForm().setDtInicioVigencia(ConstantesCRM.SVAZIO);
				getFidelizacaoForm().setDtFimVigencia(ConstantesCRM.SVAZIO);
				getFidelizacaoForm().setListaBonusVO(ofertaRealizadaFacade.getBonus(user, getDetalhesProcessoForm().getDadosLinha().getIdUFOperadora(), getFidelizacaoForm().getListaOfertasAceitas().getItemListaDescricaoVOArray(0).getId(), (getDetalhesProcessoForm().getDadosCliente().getIdTipoPessoa() == 2) ? "PJ" : "PF", getDetalhesProcessoForm().getDadosLinha().getIdSegmentacao(), getDetalhesProcessoForm().getDadosLinha().getIdTipoLinha()));
			} else if (PONTOS.equals(sgOferta)) {
				destino = "fidelizacaoPontos";
			} else if (MIGRACAO.equals(sgOferta)) {
				getFidelizacaoForm().setListaMigracaoVO(ofertaRealizadaFacade.getListaMigracao(user, getDetalhesProcessoForm().getDadosLinha().getIdUFOperadora(), (getDetalhesProcessoForm().getDadosCliente().getIdTipoPessoa() == 2) ? "PJ" : "PF", ""));
			} else if (ADEQUACAO_PLANO.equals(sgOferta)) {
				destino = "fidelizacaoAdequacaoPlanos";
				getFidelizacaoForm().setDestino(destino);
				getFidelizacaoForm().setListaPlanoVO(ofertaRealizadaFacade.getPlano(user, getDetalhesProcessoForm().getDadosLinha().getIdUFOperadora(), (getDetalhesProcessoForm().getDadosCliente().getIdTipoPessoa() == 2) ? "PJ" : "PF", getDetalhesProcessoForm().getDadosLinha().getIdTipoLinha()));
			} else if (SUSPENSAO_TEMP.equals(sgOferta)) {
				getFidelizacaoForm().setDiasSuspensaoTemp(ofertaRealizadaFacade.getDiasSuspTemp(user).getValor());
			} else if (APARELHOS.equals(sgOferta) || COMODATO.equals(sgOferta)) {
				destino = "fidelizacaoAparelhos";

				getFidelizacaoForm().setInExcecao(false);
				getFidelizacaoForm().setAparelhoSelecionado(Aparelhos.Factory.newInstance());
				getFidelizacaoForm().setListaParcelas(new ItemListaVO[0]);
				getFidelizacaoForm().setListaPercentuaisDesconto(new ItemListaVO[0]);

				OfAparelhoParamVO ofAparelhoParamVO = OfAparelhoParamVO.Factory.newInstance();
				ofAparelhoParamVO = getOfAparelhoParamVO(user, sgOferta, true);
				getFidelizacaoForm().setDadosParametrosAparelho(ofAparelhoParamVO);

				getFidelizacaoForm().setOfertaAparelhoVO(ofertaAparelhoFacade.getAparelho(user, ofAparelhoParamVO));
			}

			return destino;

		} catch (Exception e) {
			throw e;
		}
	}

	private OfAparelhoParamVO getOfAparelhoParamVO(String user, String sgOferta, boolean inExcecao) {

		OfAparelhoParamVO ofAparelhoParamVO = OfAparelhoParamVO.Factory.newInstance();
		ofAparelhoParamVO.setIdUfOperadora(getDetalhesProcessoForm().getDadosLinha().getIdUFOperadora());
		ofAparelhoParamVO.setIdTipoPessoa(getDetalhesProcessoForm().getDadosCliente().getIdTipoPessoa() == 1 ? "PF" : "PJ");
		ofAparelhoParamVO.setIdTipoLinha(Integer.parseInt(getDetalhesProcessoForm().getDadosLinha().getIdTipoLinha()));
		ofAparelhoParamVO.setIdSegmentacao(getDetalhesProcessoForm().getDadosLinha().getIdSegmentacao());
		ofAparelhoParamVO.setIdGrupo(getDetalhesProcessoForm().getListaLinhasAssociadas().getIdGrupoAbertura());
		ofAparelhoParamVO.setInExcecao(inExcecao ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
		ofAparelhoParamVO.setIdLinhaTelefonica(getDetalhesProcessoForm().getDadosLinha().getIdLinha());
		ofAparelhoParamVO.setSgOferta(sgOferta);

		return ofAparelhoParamVO;

	}

	/*
	 * Método responsável pelo preenchimento com dados padrão para todos os fluxos de retenção.
	 */
	private FinalizaRetencaoVO getFinalizaRetencaoVO(HttpServletRequest request) {

		FinalizaRetencaoVO frVO = FinalizaRetencaoVO.Factory.newInstance();

		if (this.finalizaRetencaoVO != null) {
			frVO.set(this.finalizaRetencaoVO.copy());
		}

		if (frVO.getCliente() == null) {
			frVO.addNewCliente();
		}

		frVO.getCliente().setIdTipoLinha(getDetalhesProcessoForm().getDadosLinha().getIdTipoLinha());
		frVO.getCliente().setIdPessoaDePara(String.valueOf(getDetalhesProcessoForm().getDadosLinha().getIdPessoaDePara()));
		frVO.getCliente().setIdUFOperadora(getDetalhesProcessoForm().getDadosLinha().getIdUFOperadora());
		frVO.getCliente().setIdLinhaTelefonica(getDetalhesProcessoForm().getDadosLinha().getIdLinha());
		frVO.getCliente().setNrLinha(getDetalhesProcessoForm().getDadosLinha().getNumero());
		frVO.getCliente().setIdSegmentacao(getDetalhesProcessoForm().getDadosLinha().getIdSegmentacao());
		frVO.getCliente().setSgTipoPessoa((getDetalhesProcessoForm().getDadosCliente().getIdTipoPessoa() == 2) ? "PJ" : "PF");

		if (frVO.getRetencao() == null) {
			frVO.addNewRetencao();
		}

		if (frVO.getRetencao().getRetencaoHeader() == null) {
			frVO.getRetencao().addNewRetencaoHeader();
		}

		frVO.getRetencao().getRetencaoHeader().setInPortabilidade(ConstantesCRM.SONE);
		frVO.getRetencao().getRetencaoHeader().setNrProtocoloPortabilidade(Long.parseLong(getDetalhesProcessoForm().getDadosLinha().getNrProtocoloPortabilidade()));
		frVO.getRetencao().getRetencaoHeader().setIdGrupo(getDetalhesProcessoForm().getListaLinhasAssociadas().getIdGrupoAbertura());
		frVO.getRetencao().getRetencaoHeader().setNmLoginUsuario(request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN).toString());

		if (!ConstantesCRM.SVAZIO.equals(getFidelizacaoForm().getIdIntencaoSelecionada())) {
			frVO.getRetencao().getRetencaoHeader().setIdRespostaIntencao(getFidelizacaoForm().getIdIntencaoSelecionada());
		}

		if (!ConstantesCRM.SVAZIO.equals(getFidelizacaoForm().getIdDestinoSelecionado())) {
			frVO.getRetencao().getRetencaoHeader().setIdRespostaDestino(getFidelizacaoForm().getIdDestinoSelecionado());
		}

		if (frVO.getRetencao().getRetencaoBody() == null) {
			frVO.getRetencao().addNewRetencaoBody();
		}

		if (frVO.getRetencao().getRetencaoBody().getStatus() == null) {
			frVO.getRetencao().getRetencaoBody().addNewStatus();
		}

		if (frVO.getRetencao().getRetencaoBody().getStatus().getOfertasRealizadas() == null) {
			frVO.getRetencao().getRetencaoBody().getStatus().addNewOfertasRealizadas();
			if (getFidelizacaoForm().getListaOfertasAceitas().sizeOfItemListaDescricaoVOArray() > 0) {
				frVO.getRetencao().getRetencaoBody().getStatus().setIdOfertaAceita(getFidelizacaoForm().getListaOfertasAceitas().getItemListaDescricaoVOArray(0).getId());
				frVO.getRetencao().getRetencaoBody().getStatus().setSgOfertaAceita(getFidelizacaoForm().getListaOfertasAceitas().getItemListaDescricaoVOArray(0).getSigla());
			}
			if (getFidelizacaoForm().getListaOfertasRealizadas().getItemListaDescricaoVOArray().length > 0) {
				for (int i = 0; i < getFidelizacaoForm().getListaOfertasRealizadas().getItemListaDescricaoVOArray().length; i++) {
					frVO.getRetencao().getRetencaoBody().getStatus().getOfertasRealizadas().addIdOfertaRealizada(getFidelizacaoForm().getListaOfertasRealizadas().getItemListaDescricaoVOArray(i).getId());
				}
			}
		}

		return frVO;

	}

	/**
	 * Formata uma string para decimal de acordo com a localidade atual
	 *
	 * @param valor
	 *            String
	 * @param tipo
	 *            int 0 = nacional; 1 = internacional
	 * @return String
	 */
	private String formatarDecimal(String valor, int tipo) {

		String pattern = "###,##0.00";
		char sepDecimal = ',';
		char sepGrupo = '.';

		// se a variável estiver nula, retorna vazio
		if (valor == null) {
			valor = ConstantesCRM.SZERO;
		}

		// Formata no estilo 4999,00
		if (tipo == 1) {
			pattern = "##0.00";
			sepDecimal = '.';
			sepGrupo = ',';
			valor = valor.replace('.', 'X').replaceAll("X", "").replace(',', '.');
		}
		// Formata no estilo 4999.00
		else if (tipo == 2) {
			pattern = "##0.00";
			sepDecimal = '.';
			sepGrupo = ' ';
			valor = valor.replace('.', 'X').replaceAll("X", "").replace('.', ',');
		}

		DecimalFormatSymbols dSymbols = new DecimalFormatSymbols();
		dSymbols.setDecimalSeparator(sepDecimal);
		dSymbols.setGroupingSeparator(sepGrupo);

		DecimalFormat dFormat = new DecimalFormat("00", dSymbols);

		dFormat.isDecimalSeparatorAlwaysShown();
		dFormat.applyPattern(pattern + ";(" + pattern + ")");
		return dFormat.format(new Double(valor));
	}

	public static class FidelizacaoForm extends ActionForm {

		private static final long serialVersionUID = -124957699459624789L;

		private FidelizacaoListaGeralVO listaIntencoesCancelamento;
		private FidelizacaoListaGeralVO listaDestinosPrevistos;
		private FidelizacaoListaGeralVO listaMensagensEncerramento;
		private FidelizacaoListaGeralDescricaoVO listaOfertasOriginal;
		private FidelizacaoListaGeralDescricaoVO listaOfertas;
		private FidelizacaoListaGeralDescricaoVO listaOfertasRealizadas;
		private FidelizacaoListaGeralDescricaoVO listaOfertasAceitas;

		private ListaDetalheLinhaVO listaLinhasAssociadas;
		private ListaDetalheLinhaVO listaLinhasAssociadasTratadas;
		private String[] nrLinhasMesmaOferta;

		private String destino;
		private String operacaoAtual;
		private String idIntencaoSelecionada;
		private String idDestinoSelecionado;
		private boolean flVoltar;
		private boolean inInicioRetencao;

		private String[] ofertasDisponiveis;
		private String[] ofertasRealizadas;
		private String[] ofertasAceitas;

		private ItemListaDescricaoVO ofertaAceita;

		private ListaBonusVO listaBonusVO;
		private ListaMigracaoVO listaMigracaoVO;
		private ListaPlanoVO listaPlanoVO;
		private String diasSuspensaoTemp;

		private OfertaAparelhoVO ofertaAparelhoVO;
		private String vlCalcularDesconto;

		private OfAparelhoParamVO dadosParametrosAparelho;
		private boolean inExcecao;
		private boolean inMesmaOferta = false;

		private PlanoVO planoSelecionado;
		private BonusVO bonusSelecionado;
		private Pontos dadosPontos;
		private Aparelhos aparelhoSelecionado;

		private String dtInicioVigencia;
		private String dtFimVigencia;

		private FinalizaRetencaoVO finalizaRetencaoVO;
		private RetornoVO retornoVO;

		private UFVO[] listaRegionais;
		private ItemListaVO[] listaParcelas;
		private ItemListaVO[] listaPercentuaisDesconto;

		private Endereco enderecoEntrega;
		private String dsTipoPagamento;

		private MensajeEncerraVO mensagemEncerramentoVO;

		public Endereco getEnderecoEntrega() {
			if (this.enderecoEntrega == null) {
				this.enderecoEntrega = Endereco.Factory.newInstance();
			}
			return this.enderecoEntrega;
		}

		public void setEnderecoEntrega(Endereco arg1) {
			this.enderecoEntrega = arg1;
		}

		public UFVO[] getListaRegionais() {
			if (this.listaRegionais == null) {
				this.listaRegionais = new UFVO[0];
			}
			return this.listaRegionais;
		}

		public void setListaRegionais(UFVO[] arg1) {
			this.listaRegionais = arg1;
		}

		public ItemListaVO[] getListaParcelas() {
			if (this.listaParcelas == null) {
				this.listaParcelas = new ItemListaVO[0];
			}
			return this.listaParcelas;
		}

		public void setListaParcelas(ItemListaVO[] arg1) {
			this.listaParcelas = arg1;
		}

		public ItemListaVO[] getListaPercentuaisDesconto() {
			if (this.listaPercentuaisDesconto == null) {
				this.listaPercentuaisDesconto = new ItemListaVO[0];
			}
			return this.listaPercentuaisDesconto;
		}

		public void setListaPercentuaisDesconto(ItemListaVO[] arg1) {
			this.listaPercentuaisDesconto = arg1;
		}

		public FidelizacaoListaGeralVO getListaIntencoesCancelamento() {
			if (this.listaIntencoesCancelamento == null) {
				this.listaIntencoesCancelamento = FidelizacaoListaGeralVO.Factory.newInstance();
			}
			return this.listaIntencoesCancelamento;
		}

		public void setListaIntencoesCancelamento(FidelizacaoListaGeralVO arg1) {
			this.listaIntencoesCancelamento = arg1;
		}

		public FidelizacaoListaGeralVO getListaDestinosPrevistos() {
			if (this.listaDestinosPrevistos == null) {
				this.listaDestinosPrevistos = FidelizacaoListaGeralVO.Factory.newInstance();
			}
			return this.listaDestinosPrevistos;
		}

		public void setListaDestinosPrevistos(FidelizacaoListaGeralVO arg1) {
			this.listaDestinosPrevistos = arg1;
		}

		public FidelizacaoListaGeralVO getListaMensagensEncerramento() {
			if (this.listaMensagensEncerramento == null) {
				this.listaMensagensEncerramento = FidelizacaoListaGeralVO.Factory.newInstance();
			}
			return this.listaMensagensEncerramento;
		}

		public void setListaMensagensEncerramento(FidelizacaoListaGeralVO arg1) {
			this.listaMensagensEncerramento = arg1;
		}

		public FidelizacaoListaGeralDescricaoVO getListaOfertas() {
			if (this.listaOfertas == null) {
				this.listaOfertas = FidelizacaoListaGeralDescricaoVO.Factory.newInstance();
			}
			return this.listaOfertas;
		}

		public void setListaOfertas(FidelizacaoListaGeralDescricaoVO arg1) {
			this.listaOfertas = arg1;
		}

		public ListaDetalheLinhaVO getListaLinhasAssociadasTratadas() {
			if (this.listaLinhasAssociadasTratadas == null) {
				this.listaLinhasAssociadasTratadas = ListaDetalheLinhaVO.Factory.newInstance();
			}
			return this.listaLinhasAssociadasTratadas;
		}

		public void setListaLinhasAssociadasTratadas(ListaDetalheLinhaVO arg1) {
			this.listaLinhasAssociadasTratadas = arg1;
		}

		public ListaDetalheLinhaVO getListaLinhasAssociadas() {
			if (this.listaLinhasAssociadas == null) {
				this.listaLinhasAssociadas = ListaDetalheLinhaVO.Factory.newInstance();
			}
			return this.listaLinhasAssociadas;
		}

		public void setListaLinhasAssociadas(ListaDetalheLinhaVO arg1) {
			this.listaLinhasAssociadas = arg1;
		}

		public FidelizacaoListaGeralDescricaoVO getListaOfertasOriginal() {
			return this.listaOfertasOriginal;
		}

		public void setListaOfertasOriginal(FidelizacaoListaGeralDescricaoVO arg1) {
			this.listaOfertasOriginal = arg1;
		}

		public FidelizacaoListaGeralDescricaoVO getListaOfertasRealizadas() {
			if (this.listaOfertasRealizadas == null) {
				this.listaOfertasRealizadas = FidelizacaoListaGeralDescricaoVO.Factory.newInstance();
			}
			return this.listaOfertasRealizadas;
		}

		public void setListaOfertasRealizadas(FidelizacaoListaGeralDescricaoVO arg1) {
			this.listaOfertasRealizadas = arg1;
		}

		public FidelizacaoListaGeralDescricaoVO getListaOfertasAceitas() {
			if (this.listaOfertasAceitas == null) {
				this.listaOfertasAceitas = FidelizacaoListaGeralDescricaoVO.Factory.newInstance();
			}
			return this.listaOfertasAceitas;
		}

		public void setListaOfertasAceitas(FidelizacaoListaGeralDescricaoVO arg1) {
			this.listaOfertasAceitas = arg1;
		}

		public ItemListaDescricaoVO getOfertaAceita() {
			if (this.ofertaAceita == null) {
				this.ofertaAceita = ItemListaDescricaoVO.Factory.newInstance();
			}
			return this.ofertaAceita;
		}

		public void setOfertaAceita(ItemListaDescricaoVO arg1) {
			this.ofertaAceita = arg1;
		}

		public String getDestino() {
			if (this.destino == null) {
				this.destino = ConstantesCRM.SVAZIO;
			}
			return this.destino;
		}

		public void setDestino(String arg1) {
			this.destino = arg1;
		}

		public String getOperacaoAtual() {
			if (this.operacaoAtual == null) {
				this.operacaoAtual = ConstantesCRM.SVAZIO;
			}
			return this.operacaoAtual;
		}

		public void setOperacaoAtual(String arg1) {
			this.operacaoAtual = arg1;
		}

		public boolean getFlVoltar() {
			return this.flVoltar;
		}

		public void setFlVoltar(boolean arg1) {
			this.flVoltar = arg1;
		}

		public boolean getInInicioRetencao() {
			return this.inInicioRetencao;
		}

		public void setInInicioRetencao(boolean arg1) {
			this.inInicioRetencao = arg1;
		}

		public String getIdIntencaoSelecionada() {
			if (this.idIntencaoSelecionada == null) {
				this.idIntencaoSelecionada = ConstantesCRM.SVAZIO;
			}
			return this.idIntencaoSelecionada;
		}

		public void setIdIntencaoSelecionada(String arg1) {
			this.idIntencaoSelecionada = arg1;
		}

		public String getIdDestinoSelecionado() {
			if (this.idDestinoSelecionado == null) {
				this.idDestinoSelecionado = ConstantesCRM.SVAZIO;
			}
			return this.idDestinoSelecionado;
		}

		public void setIdDestinoSelecionado(String arg1) {
			this.idDestinoSelecionado = arg1;
		}

		public String[] getOfertasRealizadas() {
			if (this.ofertasRealizadas == null) {
				this.ofertasRealizadas = new String[0];
			}
			return this.ofertasRealizadas;
		}

		public void setOfertasRealizadas(String[] arg1) {
			this.ofertasRealizadas = arg1;
		}

		public String[] getOfertasDisponiveis() {
			if (this.ofertasDisponiveis == null) {
				this.ofertasDisponiveis = new String[0];
			}
			return this.ofertasDisponiveis;
		}

		public void setOfertasDisponiveis(String[] arg1) {
			this.ofertasDisponiveis = arg1;
		}

		public String[] getNrLinhasMesmaOferta() {
			if (this.nrLinhasMesmaOferta == null) {
				this.nrLinhasMesmaOferta = new String[0];
			}
			return this.nrLinhasMesmaOferta;
		}

		public void setNrLinhasMesmaOferta(String[] arg1) {
			this.nrLinhasMesmaOferta = arg1;
		}

		public String[] getOfertasAceitas() {
			if (this.ofertasAceitas == null) {
				this.ofertasAceitas = new String[0];
			}
			return this.ofertasAceitas;
		}

		public void setOfertasAceitas(String[] arg1) {
			this.ofertasAceitas = arg1;
		}

		public ListaBonusVO getListaBonusVO() {
			if (this.listaBonusVO == null) {
				this.listaBonusVO = ListaBonusVO.Factory.newInstance();
			}
			return this.listaBonusVO;
		}

		public void setListaBonusVO(ListaBonusVO arg1) {
			this.listaBonusVO = arg1;
		}

		public ListaMigracaoVO getListaMigracaoVO() {
			if (this.listaMigracaoVO == null) {
				this.listaMigracaoVO = ListaMigracaoVO.Factory.newInstance();
			}
			return this.listaMigracaoVO;
		}

		public void setListaMigracaoVO(ListaMigracaoVO arg1) {
			this.listaMigracaoVO = arg1;
		}

		public ListaPlanoVO getListaPlanoVO() {
			if (this.listaPlanoVO == null) {
				this.listaPlanoVO = ListaPlanoVO.Factory.newInstance();
			}
			return this.listaPlanoVO;
		}

		public void setListaPlanoVO(ListaPlanoVO arg1) {
			this.listaPlanoVO = arg1;
		}

		public String getDiasSuspensaoTemp() {
			if (this.diasSuspensaoTemp == null) {
				this.diasSuspensaoTemp = ConstantesCRM.SVAZIO;
			}
			return this.diasSuspensaoTemp;
		}

		public void setDiasSuspensaoTemp(String arg1) {
			this.diasSuspensaoTemp = arg1;
		}

		public OfertaAparelhoVO getOfertaAparelhoVO() {
			if (this.ofertaAparelhoVO == null) {
				this.ofertaAparelhoVO = OfertaAparelhoVO.Factory.newInstance();
			}
			return this.ofertaAparelhoVO;
		}

		public void setOfertaAparelhoVO(OfertaAparelhoVO arg1) {
			if (arg1.getFidelizacaoListaGeralVO() == null) {
				arg1.addNewFidelizacaoListaGeralVO();
			}
			this.ofertaAparelhoVO = arg1;
		}

		public OfAparelhoParamVO getDadosParametrosAparelho() {
			if (this.dadosParametrosAparelho == null) {
				this.dadosParametrosAparelho = OfAparelhoParamVO.Factory.newInstance();
			}
			return this.dadosParametrosAparelho;
		}

		public void setDadosParametrosAparelho(OfAparelhoParamVO arg1) {
			this.dadosParametrosAparelho = arg1;
		}

		public boolean getInExcecao() {
			return this.inExcecao;
		}

		public void setInExcecao(boolean arg1) {
			this.inExcecao = arg1;
		}

		public String getDsTipoPagamento() {
			if (this.dsTipoPagamento == null) {
				this.dsTipoPagamento = ConstantesCRM.SVAZIO;
			}
			return this.dsTipoPagamento;
		}

		public void setDsTipoPagamento(String arg1) {
			this.dsTipoPagamento = arg1;
		}

		public boolean getInMesmaOferta() {
			return this.inMesmaOferta;
		}

		public void setInMesmaOferta(boolean arg1) {
			this.inMesmaOferta = arg1;
		}

		public PlanoVO getPlanoSelecionado() {
			if (this.planoSelecionado == null) {
				this.planoSelecionado = PlanoVO.Factory.newInstance();
			}
			return this.planoSelecionado;
		}

		public void setPlanoSelecionado(PlanoVO arg1) {
			this.planoSelecionado = arg1;
		}

		public RetornoVO getRetornoVO() {
			if (this.retornoVO == null) {
				this.retornoVO = RetornoVO.Factory.newInstance();
			}
			return this.retornoVO;
		}

		public void setRetornoVO(RetornoVO arg1) {
			this.retornoVO = arg1;
		}

		public FinalizaRetencaoVO getFinalizaRetencaoVO() {
			if (this.finalizaRetencaoVO == null) {
				this.finalizaRetencaoVO = FinalizaRetencaoVO.Factory.newInstance();
			}
			return this.finalizaRetencaoVO;
		}

		public void setFinalizaRetencaoVO(FinalizaRetencaoVO arg1) {
			this.finalizaRetencaoVO = arg1;
		}

		public BonusVO getBonusSelecionado() {
			if (this.bonusSelecionado == null) {
				this.bonusSelecionado = BonusVO.Factory.newInstance();
			}
			return this.bonusSelecionado;
		}

		public void setBonusSelecionado(BonusVO arg1) {
			this.bonusSelecionado = arg1;
		}

		public Pontos getDadosPontos() {
			if (this.dadosPontos == null) {
				this.dadosPontos = Pontos.Factory.newInstance();
			}
			return this.dadosPontos;
		}

		public void setDadosPontos(Pontos arg1) {
			this.dadosPontos = arg1;
		}

		public Aparelhos getAparelhoSelecionado() {
			if (this.aparelhoSelecionado == null) {
				aparelhoSelecionado = Aparelhos.Factory.newInstance();
			}
			return this.aparelhoSelecionado;
		}

		public void setAparelhoSelecionado(Aparelhos arg1) {
			this.aparelhoSelecionado = arg1;
		}

		public String getDtInicioVigencia() {
			return this.dtInicioVigencia;
		}

		public void setDtInicioVigencia(String arg1) {
			this.dtInicioVigencia = arg1;
		}

		public String getDtFimVigencia() {
			return this.dtFimVigencia;
		}

		public void setDtFimVigencia(String arg1) {
			this.dtFimVigencia = arg1;
		}

		public String getVlCalcularDesconto() {
			if (this.vlCalcularDesconto == null) {
				this.vlCalcularDesconto = ConstantesCRM.SVAZIO;
			}
			return this.vlCalcularDesconto;
		}

		public void setVlCalcularDesconto(String arg1) {
			this.vlCalcularDesconto = arg1;
		}

		public MensajeEncerraVO getMensagemEncerramentoVO() {
			if (this.mensagemEncerramentoVO == null) {
				this.mensagemEncerramentoVO = MensajeEncerraVO.Factory.newInstance();
			}
			return this.mensagemEncerramentoVO;
		}

		public void setMensagemEncerramentoVO(MensajeEncerraVO arg1) {
			this.mensagemEncerramentoVO = arg1;
		}

	}

}