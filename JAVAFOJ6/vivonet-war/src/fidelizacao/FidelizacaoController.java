package fidelizacao;

import java.io.BufferedOutputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import br.com.vivo.fo.atendimento.vo.AbreProtocoloOutTODocument.AbreProtocoloOutTO;
import br.com.vivo.fo.atendimento.vo.AbreProtocoloTODocument.AbreProtocoloTO;
import br.com.vivo.fo.cliente.vo.AjaxErrorHandlerVODocument.AjaxErrorHandlerVO;
import br.com.vivo.fo.cliente.vo.EnderecoVODocument.EnderecoVO;
import br.com.vivo.fo.cliente.vo.LupaClienteVODocument.LupaClienteVO;
import br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.cliente.vo.PesquisaEnderecoVODocument.PesquisaEnderecoVO;
import br.com.vivo.fo.cliente.vo.TelaInicialVODocument.TelaInicialVO;
import br.com.vivo.fo.commons.utils.GetParametro;
import br.com.vivo.fo.commons.utils.Moeda;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.cliente.prePago.manutencaoPrePago.ManutencaoPrePagoFacade;
import br.com.vivo.fo.ctrls.cliente.protocolo.ProtocoloFacade;
import br.com.vivo.fo.ctrls.cliente.telaInicial.TelaInicialFacade;
import br.com.vivo.fo.ctrls.fidelizacao.AgendamentoContato.AgendamentoContatoFacade;
import br.com.vivo.fo.ctrls.fidelizacao.OfertaRealizada.OfertaAparelhoFacade;
import br.com.vivo.fo.ctrls.fidelizacao.OfertaRealizada.OfertaRealizadaFacade;
import br.com.vivo.fo.ctrls.fidelizacao.realizarRetencao.RalizarRetencaoFacade;
import br.com.vivo.fo.ctrls.fidelizacao.retencaoRestricao.RetencaoRestricaoFacade;
import br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Geral.ErroInfo;
import br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.NotaFiscalEletronicaPortTypeProxy;
import br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.ParametrosConsultarInformacoesConfianca;
import br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.ParametrosConsultarInformacoesConfiancaTipoConsulta;
import br.com.vivo.fo.ctrls.workflow.registrarContato.RegistrarContatoFacade;
import br.com.vivo.fo.exception.FOException;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.fidelizacao.vo.ConsultaAdimplenciaVODocument;
import br.com.vivo.fo.fidelizacao.vo.ConsultaAdimplenciaVODocument.ConsultaAdimplenciaVO;
import br.com.vivo.fo.fidelizacao.vo.DetalheHistoricoRetencaoVODocument.DetalheHistoricoRetencaoVO;
import br.com.vivo.fo.fidelizacao.vo.DetalheLinhaVODocument;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralDescricaoVODocument.FidelizacaoListaGeralDescricaoVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO;
import br.com.vivo.fo.fidelizacao.vo.FinalizaRetencaoVODocument.FinalizaRetencaoVO;
import br.com.vivo.fo.fidelizacao.vo.FinalizaRetencaoVODocument.FinalizaRetencaoVO.Retencao.RetencaoBody.Analise.Adimplencia;
import br.com.vivo.fo.fidelizacao.vo.FinalizaRetencaoVODocument.FinalizaRetencaoVO.Retencao.RetencaoBody.Analise.Endereco;
import br.com.vivo.fo.fidelizacao.vo.FinalizaRetencaoVODocument.FinalizaRetencaoVO.Retencao.RetencaoBody.Ofertas.Aparelhos;
import br.com.vivo.fo.fidelizacao.vo.FinalizaRetencaoVODocument.FinalizaRetencaoVO.Retencao.RetencaoBody.Ofertas.Bonus;
import br.com.vivo.fo.fidelizacao.vo.FinalizaRetencaoVODocument.FinalizaRetencaoVO.Retencao.RetencaoBody.Ofertas.Migracao;
import br.com.vivo.fo.fidelizacao.vo.FinalizaRetencaoVODocument.FinalizaRetencaoVO.Retencao.RetencaoBody.Ofertas.Planos;
import br.com.vivo.fo.fidelizacao.vo.ListaBonusVODocument.ListaBonusVO;
import br.com.vivo.fo.fidelizacao.vo.ListaDetalheLinhaVODocument.ListaDetalheLinhaVO;
import br.com.vivo.fo.fidelizacao.vo.ListaHistoricoAgendamentoVODocument.ListaHistoricoAgendamentoVO;
import br.com.vivo.fo.fidelizacao.vo.ListaHistoricoRetencaoVODocument.ListaHistoricoRetencaoVO;
import br.com.vivo.fo.fidelizacao.vo.ListaMigracaoVODocument.ListaMigracaoVO;
import br.com.vivo.fo.fidelizacao.vo.ListaPlanoVODocument.ListaPlanoVO;
import br.com.vivo.fo.fidelizacao.vo.MensajeEncerraVODocument.MensajeEncerraVO;
import br.com.vivo.fo.fidelizacao.vo.OfAparelhoParamVODocument.OfAparelhoParamVO;
import br.com.vivo.fo.fidelizacao.vo.OfertaAparelhoVODocument.OfertaAparelhoVO;
import br.com.vivo.fo.fidelizacao.vo.RetencaoAnaliseCreditoVODocument;
import br.com.vivo.fo.fidelizacao.vo.RetencaoAnaliseCreditoVODocument.RetencaoAnaliseCreditoVO;
import br.com.vivo.fo.fidelizacao.vo.RetencaoSAPVODocument;
import br.com.vivo.fo.fidelizacao.vo.RetencaoSAPVODocument.RetencaoSAPVO;
import br.com.vivo.fo.fidelizacao.vo.RetencaoSAPVODocument.RetencaoSAPVO.CLIENTE;
import br.com.vivo.fo.fidelizacao.vo.RetencaoSAPVODocument.RetencaoSAPVO.ENDERECO;
import br.com.vivo.fo.fidelizacao.vo.RetencaoSAPVODocument.RetencaoSAPVO.PRODUTO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.retornotux.vo.RetencaoRetornoSAPVODocument.RetencaoRetornoSAPVO;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoPesquisaVODocument.AtendimentoPesquisaVO;
import br.com.vivo.fo.workflow.vo.ListaDadosVODocument.ListaDadosVO;
import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;
import fidelizacao.formBeans.ActionRetencaoFormBean;
import fidelizacao.formBeans.CadastrarBonusFormBean;
import fidelizacao.formBeans.CadastrarMigracaoFormBean;
import fidelizacao.formBeans.CancelarLinhasFormBean;
import fidelizacao.formBeans.ConsultaAdimplenciaFormBean;
import fidelizacao.formBeans.ConsultaMatrizOfertaFormBean;
import fidelizacao.formBeans.DadosClienteFormBean;
import fidelizacao.formBeans.OfertaAparelhoFormBean;
import fidelizacao.formBeans.PesquisaDestinoPrevistoFormBean;
import fidelizacao.formBeans.ShowDetalheHistoricoFormBean;
import fidelizacao.formBeans.ShowMatrizOfertaFormBean;
import fidelizacao.formBeans.SuspensaoTemporariaFormBean;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class FidelizacaoController extends AbstractAction {

    private static final long                  serialVersionUID                       = 9055182019968545104L;

    /********************************** FACADES ************************************************/

    @EJB
    private AgendamentoContatoFacade           agendarContatoFac;

    @EJB
    private RalizarRetencaoFacade              retencaoFAC;

    @EJB
    private OfertaRealizadaFacade              ofertaRealizadaFac;

    @EJB
    private OfertaAparelhoFacade               ofertaAparelhoFac;

    @EJB
    private TelaInicialFacade                  telaInicialFac;

    @EJB
    private ManutencaoPrePagoFacade            prePagoFac;

    @EJB
    private RetencaoRestricaoFacade            retencaoRestricaoFacade;

    @EJB
    private RegistrarContatoFacade             registrarContatoFacade;

    @EJB
    private ProtocoloFacade                    protocoloFacade;

    /********************************************************************************************/

    private static Logger                      log                                    = new Logger("Fidelizacao");

    private String                             idUsuario;
    private String                             idGrupo;
    private ParametrosVO                       parametrosVO;
    protected ListaDetalheLinhaVO              listaLinhasVO;
    protected ListaHistoricoRetencaoVO         listaHistoricoVO;
    protected String                           diasSuspensaoTemp;

    protected ListaDetalheLinhaVO              listaLinhas;
    protected FidelizacaoListaGeralVO          listaIntencaoCancelamentoVO;
    protected FidelizacaoListaGeralVO          listaDestinoPrevistoVO;
    protected FidelizacaoListaGeralDescricaoVO listaOfertasVO;
    protected FidelizacaoListaGeralDescricaoVO listaOfertasDisponiveisVO;
    protected FidelizacaoListaGeralDescricaoVO listaOfertasRealizadasVO;
    protected FidelizacaoListaGeralDescricaoVO listaOfertasAceitasVO;
    protected ListaBonusVO                     listaBonusVO;
    protected ListaMigracaoVO                  listaMigracaoVO;
    protected ListaPlanoVO                     listaPlanosVO;
    protected OfertaAparelhoVO                 listaAparelhosVO;
    protected FidelizacaoListaGeralVO          listaMensagensVO;
    protected ListaHistoricoAgendamentoVO      listaHistoricoAgendamentoVO;
    protected FidelizacaoListaGeralVO          listaLigacaoIndevidaVO;
    protected FinalizaRetencaoVO               finalizaRetencaoVO                     = null;

    protected ConsultaMatrizOfertaForm         consultaMatrizOfertaForm;
    protected CadastrarBonusForm               cadastrarBonusForm;
    protected CadastrarMigracaoForm            cadastrarMigracaoForm;
    protected SuspensaoTemporariaForm          suspensaoTemporariaForm;
    protected OfertaAparelhoForm               ofertaAparelhoForm;
    protected CancelarLinhasForm               cancelarLinhasForm;
    protected DadosClienteForm                 dadosClienteForm;
    protected ActionRetencaoForm               actionRetencaoForm;
    protected ShowDetalheHistoricoForm         showDetalheHistoricoForm;
    protected PesquisaDestinoPrevistoForm      pesquisaDestinoPrevistoForm;
    protected ShowMatrizOfertaForm             showMatrizOfertaForm;
    protected ConsultaAdimplenciaForm          consultaAdimplenciaForm;

    private final String                       TARGET_FORM_ERROR                      = "_top";                                   // "frmQuestionario"
	private final String                       TIPO_ENCERRAMENTO = ConstantesCRM.STHREE;// "frmQuestionario"
    private final String                       APARELHOS                              = "AP";                                    // APARELHOS
    private final String                       BONUS                                  = "BN";                                    // BONUS
    private final String                       MIGRACAO                               = "MIG";                                    // MIGRACAO
    private final String                       ADEQUACAO_PLANO                        = "PL";                                    // ADEQUACAO_PLANO
    private final String                       PONTOS                                 = "PT";                                    // PONTOS
    private final String                       SUSPENCAO_TEMP                         = "SP";                                    // PONTOS
    private final String                       COMODATO                               = "APC";                                    // APARELHO COMODATO
    private final String                       IDRETIDOPORRESTRICAO                   = "9";
    private final String                       IDRETIDOPARAANALISE                    = "10";
    private final String                       IDENCAMINHARANALISEENDERECO            = "11";
	private final String                       IDAPROVADOANALISTABKO = ConstantesCRM.STWO;
	private final String                       IDREPROVADOANALISTABKO = ConstantesCRM.STHREE;
    private final String                       IDENDERECOREPROVADPORBKO               = "5";
    private final String                       PARAMETRO_ENDPOINT_WEBSERVICE_NFE      = "URL_WEBSERVICE_NFE";
    private final String                       PARAMETRO_TIPO_CONSULTA_WEBSERVICE_NFE = "PARAMETRO_TIPO_CONSULTA_WEBSERVICE_NFE";
    private final String                       PARAMETRO_ATIVAR_WEBSERVICE_SERASA     = "PARAMETRO_ATIVAR_WEBSERVICE_SERASA";

    protected global.Global                    globalApp                              = new global.Global();

    /************************************* GETTERS E SETTER ***************************************************/
    /***********************************************************************************************************/
    private String getIdUsuario(HttpServletRequest request) {
        idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
        return idUsuario;
    }

    private String getIdGrupo() {
        return idGrupo;
    }

    private void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }

    public ParametrosVO getParametrosVO() {
        parametrosVO = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
        return parametrosVO;
    }

    public String getNrProtocolo() {
        String nrProtocolo = (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO);
        return nrProtocolo != null ? nrProtocolo : ConstantesCRM.SVAZIO;
    }

    public ListaDetalheLinhaVO getListaLinhasVO() {
        if (listaLinhasVO == null) {
            listaLinhasVO = ListaDetalheLinhaVO.Factory.newInstance();
        }
        return listaLinhasVO;
    }

    private void setListaLinhasVO(ListaDetalheLinhaVO listaLinhas) {
        listaLinhasVO = listaLinhas;
    }

    public ListaHistoricoRetencaoVO getListaHistoricoVO() {
        if (listaHistoricoVO == null) {
            listaHistoricoVO = ListaHistoricoRetencaoVO.Factory.newInstance();
        }
        return listaHistoricoVO;
    }

    private void setListaHistoricoVO(ListaHistoricoRetencaoVO listaHistorico) {
        listaHistoricoVO = listaHistorico;
    }

    public String getDiasSuspensaoTemp() {
        return diasSuspensaoTemp;
    }

    public void setDiasSuspensaoTemp(String valor) {
        diasSuspensaoTemp = valor;
    }

    public FidelizacaoListaGeralVO getListaIntencaoCancelamentoVO() {
        if (listaIntencaoCancelamentoVO == null) {
            listaIntencaoCancelamentoVO = FidelizacaoListaGeralVO.Factory.newInstance();
        }
        return listaIntencaoCancelamentoVO;
    }

    private void setListaIntencaoCancelamentoVO(FidelizacaoListaGeralVO listaVO) {
        listaIntencaoCancelamentoVO = listaVO;
    }

    public FidelizacaoListaGeralVO getListaDestinoPrevistoVO() {
        if (listaDestinoPrevistoVO == null) {
            listaDestinoPrevistoVO = FidelizacaoListaGeralVO.Factory.newInstance();
        }
        return listaDestinoPrevistoVO;
    }

    private void setListaDestinoPrevistoVO(FidelizacaoListaGeralVO listaVO) {
        listaDestinoPrevistoVO = listaVO;
    }

    public FidelizacaoListaGeralDescricaoVO getListaOfertasVO() {
        if (listaOfertasVO == null) {
            listaOfertasVO = FidelizacaoListaGeralDescricaoVO.Factory.newInstance();
        }
        return listaOfertasVO;
    }

    private void setListaOfertasVO(FidelizacaoListaGeralDescricaoVO ofertasVO) {
        listaOfertasVO = ofertasVO;
    }

    public FidelizacaoListaGeralDescricaoVO getListaOfertasDisponiveisVO() {
        if (listaOfertasDisponiveisVO == null) {
            listaOfertasDisponiveisVO = FidelizacaoListaGeralDescricaoVO.Factory.newInstance();
        }
        return listaOfertasDisponiveisVO;
    }

    private void setListaOfertasDisponiveisVO(FidelizacaoListaGeralDescricaoVO ofertasVO) {
        listaOfertasDisponiveisVO = ofertasVO;
    }

    public FidelizacaoListaGeralDescricaoVO getListaOfertasRealizadasVO() {
        if (listaOfertasRealizadasVO == null) {
            listaOfertasRealizadasVO = FidelizacaoListaGeralDescricaoVO.Factory.newInstance();
        }
        return listaOfertasRealizadasVO;
    }

    private void setListaOfertasRealizadasVO(FidelizacaoListaGeralDescricaoVO ofertasVO) {
        listaOfertasRealizadasVO = ofertasVO;
    }

    public FidelizacaoListaGeralDescricaoVO getListaOfertasAceitasVO() {
        if (listaOfertasAceitasVO == null) {
            listaOfertasAceitasVO = FidelizacaoListaGeralDescricaoVO.Factory.newInstance();
        }
        return listaOfertasAceitasVO;
    }

    private void setListaOfertasAceitasVO(FidelizacaoListaGeralDescricaoVO ofertasVO) {
        listaOfertasAceitasVO = ofertasVO;
    }

    public ListaBonusVO getListaBonusVO() {
        if (listaBonusVO == null) {
            listaBonusVO = ListaBonusVO.Factory.newInstance();
        }
        return listaBonusVO;
    }

    public FidelizacaoListaGeralVO getListaLigacaoIndevidaVO() throws Exception {
        log.debug("FidelizacaoController:getListaLigacaoIndevidaVO()");
        try {
            testParameters();
            if (listaLigacaoIndevidaVO == null) {
                setListaLigacaoIndevidaVO(retencaoFAC.getLigacaoIndevida(getIdUsuario(request)));
            }
        } catch (Exception e) {
            throw e;
        }
        return listaLigacaoIndevidaVO;
    }

    private void setListaBonusVO(ListaBonusVO bonusVO) {
        listaBonusVO = bonusVO;
    }

    public ListaMigracaoVO getListaMigracaoVO() {
        if (listaMigracaoVO == null) {
            listaMigracaoVO = ListaMigracaoVO.Factory.newInstance();
        }
        return listaMigracaoVO;
    }

    private void setListaMigracaoVO(ListaMigracaoVO migracaoVO) {
        listaMigracaoVO = migracaoVO;
    }

    public ListaPlanoVO getListaPlanosVO() {
        if (listaPlanosVO == null) {
            listaPlanosVO = ListaPlanoVO.Factory.newInstance();
        }
        return listaPlanosVO;
    }

    private void setListaPlanosVO(ListaPlanoVO planosVO) {
        listaPlanosVO = planosVO;
    }

    public OfertaAparelhoVO getListaAparelhosVO() {
        if (listaAparelhosVO == null) {
            listaAparelhosVO = OfertaAparelhoVO.Factory.newInstance();
        }
        return listaAparelhosVO;
    }

    private void setListaAparelhosVO(OfertaAparelhoVO aparelhos) {
        listaAparelhosVO = aparelhos;
    }

    public FidelizacaoListaGeralVO getListaMensagensVO() {
        if (listaMensagensVO == null) {
            listaMensagensVO = FidelizacaoListaGeralVO.Factory.newInstance();
        }
        return listaMensagensVO;
    }

    private void setListaMensagensVO(FidelizacaoListaGeralVO listaVO) {
        listaMensagensVO = listaVO;
    }

    public ListaHistoricoAgendamentoVO getListaHistoricoAgendamentoVO() {
        if (listaHistoricoAgendamentoVO == null) {
            listaHistoricoAgendamentoVO = ListaHistoricoAgendamentoVO.Factory.newInstance();
        }
        return listaHistoricoAgendamentoVO;
    }

    private void setListaHistoricoAgendamentoVO(ListaHistoricoAgendamentoVO listaVO) {
        listaHistoricoAgendamentoVO = listaVO;
    }

    private String getNrLinha(String nrLinha) {
        return testarValor(nrLinha) // verifica se existe valor em nr linha
		? nrLinha.replace('(', ' ').replace(')', ' ').replace('-', ' ').replaceAll(" ", ConstantesCRM.SVAZIO) : ConstantesCRM.SVAZIO;
    }

    private void setListaLigacaoIndevidaVO(FidelizacaoListaGeralVO listaVO) {
        this.listaLigacaoIndevidaVO = listaVO;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.request = request;
        this.response = response;
        this.mapping = mapping;
        log.debug("FidelizacaoController:execute()::inicio");
        if ("begin".equals(mapping.getParameter())) {
            return begin(mapping, form, request, response);
        } else if ("getLigacaoIndevida".equals(mapping.getParameter())) {
            return getLigacaoIndevida(mapping, form, request, response);
        } else if ("getLinhas".equals(mapping.getParameter())) {
            return getLinhas(mapping, form, request, response);
        } else if ("getQuestionarioCampanha".equals(mapping.getParameter())) {
            return getQuestionarioCampanha(mapping, form, request, response);
        } else if ("getListaHistorico".equals(mapping.getParameter())) {
            return getListaHistorico(mapping, form, request, response);
        } else if ("getHistorico".equals(mapping.getParameter())) {
            return getHistorico(mapping, form, request, response);
        } else if ("analisarCredito".equals(mapping.getParameter())) {
            return analisarCredito(mapping, form, request, response);
        } else if ("analisarEndereco".equals(mapping.getParameter())) {
            return analisarEndereco(mapping, form, request, response);
        } else if ("efetivarRetencaoAnaliseCreditoAprovada".equals(mapping.getParameter())) {
            return efetivarRetencaoAnaliseCreditoAprovada(mapping, form, request, response);
        } else if ("finalizarAnaliseCredito".equals(mapping.getParameter())) {
            return finalizarAnaliseCredito(mapping, form, request, response);
        } else if ("finalizarAnaliseEndereco".equals(mapping.getParameter())) {
            return finalizarAnaliseEndereco(mapping, form, request, response);
        } else if ("getIntencaoCancelamento".equals(mapping.getParameter())) {
            return getIntencaoCancelamento(mapping, form, request, response);
        } else if ("getDestinoPrevisto".equals(mapping.getParameter())) {
            return getDestinoPrevisto(mapping, form, request, response);
        } else if ("getMatrizOferta".equals(mapping.getParameter())) {
            return getMatrizOferta(mapping, form, request, response);
        } else if ("redirectMatrizOferta".equals(mapping.getParameter())) {
            return redirectMatrizOferta(mapping, form, request, response);
        } else if ("getTodosAparelhos".equals(mapping.getParameter())) {
            return getTodosAparelhos(mapping, form, request, response);
        } else if ("reterBonus".equals(mapping.getParameter())) {
            return reterBonus(mapping, form, request, response);
        } else if ("reterMigracao".equals(mapping.getParameter())) {
            return reterMigracao(mapping, form, request, response);
        } else if ("reterPontos".equals(mapping.getParameter())) {
            return reterPontos(mapping, form, request, response);
        } else if ("reterAdequacaoPlano".equals(mapping.getParameter())) {
            return reterAdequacaoPlano(mapping, form, request, response);
        } else if ("reterSuspensaoTemporaria".equals(mapping.getParameter())) {
            return reterSuspensaoTemporaria(mapping, form, request, response);
        } else if ("reterAparelho".equals(mapping.getParameter())) {
            return reterAparelho(mapping, form, request, response);
        } else if ("getMotivosAlteracaoEndereco".equals(mapping.getParameter())) {
            return getMotivosAlteracaoEndereco(mapping, form, request, response);
        } else if ("getDadosAparelho".equals(mapping.getParameter())) {
            return getDadosAparelho(mapping, form, request, response);
        } else if ("reterPorRestricao".equals(mapping.getParameter())) {
            return reterPorRestricao(mapping, form, request, response);
        } else if ("encaminhar".equals(mapping.getParameter())) {
            return encaminhar(mapping, form, request, response);
        } else if ("encaminharAlteracaoEndereco".equals(mapping.getParameter())) {
            return encaminharAlteracaoEndereco(mapping, form, request, response);
        } else if ("finalizarRetencao".equals(mapping.getParameter())) {
            return finalizarRetencao(mapping, form, request, response);
        } else if ("getPercDescontoParcelas".equals(mapping.getParameter())) {
            return getPercDescontoParcelas(mapping, form, request, response);
        } else if ("cancelarAtendimento".equals(mapping.getParameter())) {
            return cancelarAtendimento(mapping, form, request, response);
        } else if ("reterCMO".equals(mapping.getParameter())) {
            return reterCMO(mapping, form, request, response);
        } else if ("getLinhasByConta".equals(mapping.getParameter())) {
            return getLinhasByConta(mapping, form, request, response);
        } else if ("filtroFimRetencao".equals(mapping.getParameter())) {
            return filtroFimRetencao(mapping, form, request, response);
        } else if ("pesquisaEndereco".equals(mapping.getParameter())) {
            return pesquisaEndereco(mapping, form, request, response);
        } else if ("buscaEnderecoCD".equals(mapping.getParameter())) {
            return buscaEnderecoCD(mapping, form, request, response);
        } else if ("getDadosAgendarContato".equals(mapping.getParameter())) {
            return getDadosAgendarContato(mapping, form, request, response);
        } else if ("agendarContato".equals(mapping.getParameter())) {
            return agendarContato(mapping, form, request, response);
        } else if ("voltarIC".equals(mapping.getParameter())) {
            return voltarIC(mapping, form, request, response);
        } else if ("voltarDEA".equals(mapping.getParameter())) {
            return voltarDEA(mapping, form, request, response);
        } else if ("voltarDPrevisto".equals(mapping.getParameter())) {
            return voltarDPrevisto(mapping, form, request, response);
        } else if ("voltarCMO".equals(mapping.getParameter())) {
            return voltarCMO(mapping, form, request, response);
        } else if ("voltarOfertas".equals(mapping.getParameter())) {
            return voltarOfertas(mapping, form, request, response);
        } else if ("getSaldoPontos".equals(mapping.getParameter())) {
            return getSaldoPontos(mapping, form, request, response);
        } else if ("sair".equals(mapping.getParameter())) {
            return sair(mapping, form, request, response);
        } else if ("ConsultaMatrizOfertaDone".equals(mapping.getParameter())) {
            return ConsultaMatrizOfertaDone(mapping, form, request, response);
        } else if ("QuestionarioDone".equals(mapping.getParameter())) {
            return QuestionarioDone(mapping, form, request, response);
        }
        log.debug("FidelizacaoController:execute()::" + mapping.getParameter());
        return begin(mapping, form, request, response);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="getLigacaoIndevida.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward getLigacaoIndevida(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        log.debug("FidelizacaoController:execute()::inicio");
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        log.debug("FidelizacaoController:execute()::fim");
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="realizarRetencao.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward getLinhas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        log.debug("FidelizacaoController:getLinhas()::inicio");
        ActionRetencaoForm form = (ActionRetencaoForm) formParam;
        try {
            testParameters();
            // carrega dados do cliente no formulario caso ainda nao estejam
            carregarDadosClienteForm(request);
            setListaLinhasVO(retencaoFAC.getLinha(getIdUsuario(request), getParametrosVO().getIdPessoaCliente(), getNrLinha(form.getLinhaPesquisa()), false, ConstantesCRM.SVAZIO));
            getActionRetencaoForm().setTotalLinhas(String.valueOf(getListaLinhasVO().sizeOfDetalheLinhaVOArray()));

            if (getListaLinhasVO().sizeOfDetalheLinhaVOArray() == 0) {
                request.setAttribute(ConstantesCRM.SERROR, "N„o existe(m) linha(s) para retenÁ„o.");
            }

            finalizaRetencaoVO = null;

        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:getLinhas()", e);
            FormError formError = globalApp.buildFormError(e, request);
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute("form", formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        log.debug("FidelizacaoController:getLinhas()::fim");
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success2" path="getIntencaoCancelamento.do"
     */
    protected ActionForward getQuestionarioCampanha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("FidelizacaoController:getQuestionarioCampanha()::inicio");
        ActionRetencaoForm form = (ActionRetencaoForm) formParam;
        testParameters();

        int indiceLinha = Integer.parseInt(form.getLinhaSelecionada());
        // recuperar dados da linha selecionada
        if (getListaLinhasVO().getDetalheLinhaVOArray().length != 0) {
            getDadosClienteForm().setIdUfOperadora(getListaLinhasVO().getDetalheLinhaVOArray(indiceLinha).getIdUFOperadora());
            getDadosClienteForm().setIdPessoaDePara(getListaLinhasVO().getDetalheLinhaVOArray(indiceLinha).getIdPessoaDePara());
            getDadosClienteForm().setValorMultaContrato(getListaLinhasVO().getDetalheLinhaVOArray(indiceLinha).getValorMulta());
            getDadosClienteForm().setDetalheLinha(getListaLinhasVO().getDetalheLinhaVOArray(indiceLinha));
            getDadosClienteForm().setIdSegmentaco(getListaLinhasVO().getDetalheLinhaVOArray(indiceLinha).getIdSegmentacao());
        }

        getActionRetencaoForm().setLinhaSelecionada(form.getLinhaSelecionada());

        request.setAttribute("retencao_DadosClienteForm", getDadosClienteForm());
        request.setAttribute("retencao_ActionRetencaoForm", getActionRetencaoForm());
        // parametroz exigidos para a execuÁ„o do questionario.
        request.setAttribute("urlRetorno", new StringBuffer(request.getContextPath()).append("/fidelizacao/getLinhas.do").toString());
        request.getSession().setAttribute("idListaConteudo", ConstantesCRM.STHREE);
        request.getSession().setAttribute("idCanalCampanha", "1184");
        request.setAttribute("idCliente", ConstantesCRM.SONE);
        request.getSession().setAttribute("idSubCampanhaHistorico", ConstantesCRM.SZERO);
        request.setAttribute("idMotivoCampanha", ConstantesCRM.SONE);
        request.setAttribute("idSubMotivoCampanha", ConstantesCRM.SZERO);
        request.setAttribute("idTipoMotivoCampanha", ConstantesCRM.SZERO);
        request.setAttribute("idTipoSubMotivoCampanha", ConstantesCRM.SZERO);
        // Parametro que identifica que o questionario esta vindo de FidelizaÁao
        request.setAttribute("operacao", ConstantesCRM.SONE);
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        log.debug("FidelizacaoController:getQuestionarioCampanha()::fim");
        return mapping.findForward("success2");
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="consultaHistorico.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward getListaHistorico(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            request.getSession().removeAttribute("sgOferta");
            request.getSession().removeAttribute("dsIntencao");
            request.getSession().removeAttribute("dsDestinoPrevisto");
            request.getSession().removeAttribute("inNovaOferta");
            request.getSession().removeAttribute("idRetencaoOld");
            request.getSession().removeAttribute("inAdimplente");
            request.getSession().removeAttribute("idRetencao");
            testParameters();

            log.debug("FidelizacaoController:getListaHistorico()");

            if (getListaLinhasVO().sizeOfDetalheLinhaVOArray() > 0) {
				setListaHistoricoVO(retencaoFAC.getHistorico(getIdUsuario(request), getListaLinhasVO().getDetalheLinhaVOArray(Integer.parseInt(request.getParameter("idLinha").toString()))
						.getIdLinha()));
            }

            setShowDetalheHistoricoForm(new ShowDetalheHistoricoForm());
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:getListaHistorico()", e);
            FormError formError = globalApp.buildFormError(e, request.getContextPath() + "/fidelizacao/getLinhas.do");
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="historicoSelecionado.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward getHistorico(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("FidelizacaoController:getHistorico()");
        ShowDetalheHistoricoForm form = (ShowDetalheHistoricoForm) formParam;
        try {
            testParameters();

            DetalheHistoricoRetencaoVO detalheHistorico = retencaoFAC.getDetalheHistorico(getIdUsuario(request), request.getParameter("idRetencao"));

            form.setDestino(detalheHistorico.getDestino());
            form.setIntencao(detalheHistorico.getIntencao());
            form.setOfertaAceita(detalheHistorico.getOfertaAceita());
            form.setObservacao(formatarObsHistorico(detalheHistorico.getComentario()));
            form.setOfertaRecusadaArray(detalheHistorico.getListaOfertaRealizadaVO().getOfertaRealizadaVOArray());
            form.setCaractOfertasAceitas(detalheHistorico.getCaractOfertasAceitas());
            form.setCaractLength(detalheHistorico.getCaractOfertasAceitas() != null && detalheHistorico.getCaractOfertasAceitas().getCaracteristicaArray().length > 1 ? detalheHistorico.getCaractOfertasAceitas().getCaracteristicaArray().length / 2
                    : 0);

            setShowDetalheHistoricoForm(form);

            detalheHistorico = null;
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:getHistorico()", e);
            FormError formError = globalApp.buildFormError(e, new StringBuffer(request.getContextPath()).append("/fidelizacao/getLinhas.do").toString());
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="analiseCredito.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward analisarCredito(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {

            String idRetencao = request.getParameter("idRetencao");
            String acao = request.getParameter(ConstantesCRM.SACTION);

            RetencaoAnaliseCreditoVO retencaoAnaliseCreditoVO = RetencaoAnaliseCreditoVODocument.RetencaoAnaliseCreditoVO.Factory.newInstance();
            retencaoAnaliseCreditoVO.setIdRetencao(idRetencao);

            retencaoAnaliseCreditoVO = retencaoRestricaoFacade.getDadosAnaliseCredito(getIdUsuario(request), retencaoAnaliseCreditoVO);

            getDadosClienteForm().setNomeCliente(retencaoAnaliseCreditoVO.getNmPessoa());

            getConsultaAdimplenciaForm().setNrDocumento(retencaoAnaliseCreditoVO.getNrDocumento());
            getConsultaAdimplenciaForm().setDsDestinoPrevisto(retencaoAnaliseCreditoVO.getDsRespostaDestino());
            getConsultaAdimplenciaForm().setDsIntencaoCancelamento(retencaoAnaliseCreditoVO.getDsRespostaIntencao());
            getConsultaAdimplenciaForm().setDsHistorico(retencaoAnaliseCreditoVO.getDsRetornoConsulta());
            getConsultaAdimplenciaForm().setDsModelo(retencaoAnaliseCreditoVO.getDsModelo());
            getConsultaAdimplenciaForm().setNmCor(retencaoAnaliseCreditoVO.getNmCor());
            getConsultaAdimplenciaForm().setDsMeioPagamento(retencaoAnaliseCreditoVO.getDsTipoPagamentoAparelho());
            getConsultaAdimplenciaForm().setIdTipoPagamento(retencaoAnaliseCreditoVO.getIdTipoPagamentoAparelho());
            getConsultaAdimplenciaForm().setVlPercentualDesconto(retencaoAnaliseCreditoVO.getVlPercentualDesconto());
            getConsultaAdimplenciaForm().setNrParcelas(retencaoAnaliseCreditoVO.getNrParcelas());
            getConsultaAdimplenciaForm().setVlParcela(Moeda.mascaraDinheiro(Double.parseDouble(retencaoAnaliseCreditoVO.getVlParcela()), Moeda.DINHEIRO_REAL, false));
            getConsultaAdimplenciaForm().setIdRetencao(idRetencao);
            getConsultaAdimplenciaForm().setVlPreco(Moeda.mascaraDinheiro(Double.parseDouble(retencaoAnaliseCreditoVO.getVlPrecoReal()), Moeda.DINHEIRO_REAL, false));
            getConsultaAdimplenciaForm().setVlPrecoComDesconto(Moeda.mascaraDinheiro(Double.parseDouble(retencaoAnaliseCreditoVO.getVlPrecoAbs()), Moeda.DINHEIRO_REAL, false));

            if ("REPROVADA".equals(acao)) {
                getConsultaAdimplenciaForm().setInBloquearEdicao(true);
                getConsultaAdimplenciaForm().setDsObsAnalista(retencaoAnaliseCreditoVO.getDsObsAnalista());
            } else {
                getConsultaAdimplenciaForm().setInBloquearEdicao(false);
                getConsultaAdimplenciaForm().setDsObsAnalista(ConstantesCRM.SVAZIO);
            }

            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:getHistorico()", e);
            FormError formError = globalApp.buildFormError(e, new StringBuffer(request.getContextPath()).append("/fidelizacao/getLinhas.do").toString());
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="analiseEndereco.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward analisarEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String idRetencao = request.getParameter("idRetencao").toString();
            String acao = request.getParameter(ConstantesCRM.SACTION);
            String msgErro = request.getParameter("msgErro");

            RetencaoAnaliseCreditoVO retencaoAnaliseCreditoVO = RetencaoAnaliseCreditoVODocument.RetencaoAnaliseCreditoVO.Factory.newInstance();
            retencaoAnaliseCreditoVO.setIdRetencao(idRetencao);

            retencaoAnaliseCreditoVO = retencaoRestricaoFacade.getDadosAnaliseEndereco(getIdUsuario(request), retencaoAnaliseCreditoVO);

            getDadosClienteForm().setNomeCliente(retencaoAnaliseCreditoVO.getNmPessoa());

            getConsultaAdimplenciaForm().setNrDocumento(retencaoAnaliseCreditoVO.getNrDocumento());
            getConsultaAdimplenciaForm().setDsDestinoPrevisto(retencaoAnaliseCreditoVO.getDsRespostaDestino());
            getConsultaAdimplenciaForm().setDsIntencaoCancelamento(retencaoAnaliseCreditoVO.getDsRespostaIntencao());
            getConsultaAdimplenciaForm().setDsModelo(retencaoAnaliseCreditoVO.getDsModelo());
            getConsultaAdimplenciaForm().setNmCor(retencaoAnaliseCreditoVO.getNmCor());
            getConsultaAdimplenciaForm().setVlPreco(Moeda.mascaraDinheiro(Double.parseDouble(retencaoAnaliseCreditoVO.getVlPrecoReal()), Moeda.DINHEIRO_REAL, false));
            getConsultaAdimplenciaForm().setDsMeioPagamento(retencaoAnaliseCreditoVO.getDsTipoPagamentoAparelho());
            getConsultaAdimplenciaForm().setDsMotivoAlteracaoEndereco(retencaoAnaliseCreditoVO.getDsMotivoAlteracaoEndereco());
            getConsultaAdimplenciaForm().setVlPercentualDesconto(retencaoAnaliseCreditoVO.getVlPercentualDesconto());
            getConsultaAdimplenciaForm().setNrParcelas(retencaoAnaliseCreditoVO.getNrParcelas());
            getConsultaAdimplenciaForm().setVlParcela(Moeda.mascaraDinheiro(Double.parseDouble(retencaoAnaliseCreditoVO.getVlParcela()), Moeda.DINHEIRO_REAL, false));
            getConsultaAdimplenciaForm().setDsObsAnalista(ConstantesCRM.SVAZIO);

            getConsultaAdimplenciaForm().setVlPrecoComDesconto(Moeda.mascaraDinheiro(Double.parseDouble(retencaoAnaliseCreditoVO.getVlPrecoAbs()), Moeda.DINHEIRO_REAL, false));
            getConsultaAdimplenciaForm().setIdRetencao(idRetencao);

            request.setAttribute("nmLoginUsuario", retencaoAnaliseCreditoVO.getNmLoginUsuario());
            request.setAttribute("nmPessoa", retencaoAnaliseCreditoVO.getNmPessoa());
            request.setAttribute("idGrupo", retencaoAnaliseCreditoVO.getIdGrupo());
            request.getSession().setAttribute("idGrupoEndereco", retencaoAnaliseCreditoVO.getIdGrupo());

            getOfertaAparelhoForm().setRua(removeCaracteresEspeciais(retencaoAnaliseCreditoVO.getDadosEndereco().getDsEndereco()));
            getOfertaAparelhoForm().setNumero(retencaoAnaliseCreditoVO.getDadosEndereco().getNrEndereco());
            getOfertaAparelhoForm().setComplemento(removeCaracteresEspeciais(retencaoAnaliseCreditoVO.getDadosEndereco().getDsComplemento()));
            getOfertaAparelhoForm().setBairro(removeCaracteresEspeciais(retencaoAnaliseCreditoVO.getDadosEndereco().getDsBairro()));
            getOfertaAparelhoForm().setCidade(removeCaracteresEspeciais(retencaoAnaliseCreditoVO.getDadosEndereco().getDsCidade()));
            getOfertaAparelhoForm().setEstado(retencaoAnaliseCreditoVO.getDadosEndereco().getDsUF());
            getOfertaAparelhoForm().setCep(retencaoAnaliseCreditoVO.getDadosEndereco().getDsCEP());

            getOfertaAparelhoForm().setIdAparelho(retencaoAnaliseCreditoVO.getIdAparelho());
            getOfertaAparelhoForm().setDsMaterial(retencaoAnaliseCreditoVO.getDsMaterial());

            if (msgErro != null) {

                // ofertaAparelhoForm = (OfertaAparelhoForm)
                // this.getPreviousActionInfo().getForm();

                request.setAttribute("msgErro", msgErro);
                getOfertaAparelhoForm().setRua(removeCaracteresEspeciais(ofertaAparelhoForm.getRua()));
                getOfertaAparelhoForm().setNumero(ofertaAparelhoForm.getNumero());
                getOfertaAparelhoForm().setComplemento(removeCaracteresEspeciais(ofertaAparelhoForm.getComplemento()));
                getOfertaAparelhoForm().setBairro(removeCaracteresEspeciais(ofertaAparelhoForm.getBairro()));
                getOfertaAparelhoForm().setCidade(removeCaracteresEspeciais(ofertaAparelhoForm.getCidade()));
                getOfertaAparelhoForm().setEstado(request.getParameter("sgUF") != null ? request.getParameter("sgUF") : ConstantesCRM.SVAZIO);
                getOfertaAparelhoForm().setCep(ofertaAparelhoForm.getCep());
            }

            getOfertaAparelhoForm().setRegionais(ofertaAparelhoFac.getPesquisaEnderecoIni(getIdUsuario(request)).getUFVOArray());

            if ("REPROVADA".equals(acao)) {
                getConsultaAdimplenciaForm().setInBloquearEdicao(true);
                getConsultaAdimplenciaForm().setDsObsAnalista(retencaoAnaliseCreditoVO.getDsObsAnalista());
            } else {
                getConsultaAdimplenciaForm().setInBloquearEdicao(false);
            }
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:analisarEndereco()", e);
            FormError formError = globalApp.buildFormError(e, new StringBuffer(request.getContextPath()).append("/fidelizacao/analisarEndereco.do").toString());
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="finalizacaoAnaliseCreditoAprovada.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward efetivarRetencaoAnaliseCreditoAprovada(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String idRetencao = request.getParameter("idRetencao").toString();

            RetencaoAnaliseCreditoVO retencaoAnaliseCreditoVO = RetencaoAnaliseCreditoVODocument.RetencaoAnaliseCreditoVO.Factory.newInstance();
            retencaoAnaliseCreditoVO.setIdRetencao(idRetencao);

            retencaoAnaliseCreditoVO = retencaoRestricaoFacade.getDadosAnaliseCredito(getIdUsuario(request), retencaoAnaliseCreditoVO);

            getDadosClienteForm().setNomeCliente(retencaoAnaliseCreditoVO.getNmPessoa());
            getDadosClienteForm().setNumeroLinha(retencaoAnaliseCreditoVO.getNrLinha());
            getDadosClienteForm().setIdLinhaTelefonica(retencaoAnaliseCreditoVO.getIdLinha());

            getConsultaAdimplenciaForm().setDsMaterial(retencaoAnaliseCreditoVO.getDsMaterial());
            getConsultaAdimplenciaForm().setNrDocumento(retencaoAnaliseCreditoVO.getNrDocumento());
            getConsultaAdimplenciaForm().setDsDestinoPrevisto(retencaoAnaliseCreditoVO.getDsRespostaDestino());
            getConsultaAdimplenciaForm().setDsIntencaoCancelamento(retencaoAnaliseCreditoVO.getDsRespostaIntencao());
            getConsultaAdimplenciaForm().setDsHistorico(retencaoAnaliseCreditoVO.getDsRetornoConsulta());
            getConsultaAdimplenciaForm().setDsObsAnalista(retencaoAnaliseCreditoVO.getDsObsAnalista());
            getConsultaAdimplenciaForm().setSgOferta(retencaoAnaliseCreditoVO.getSgOferta());
            getConsultaAdimplenciaForm().setDsModelo(retencaoAnaliseCreditoVO.getDsModelo());
            getConsultaAdimplenciaForm().setIdTipoPagamento(retencaoAnaliseCreditoVO.getIdTipoPagamentoAparelho());
            getConsultaAdimplenciaForm().setNmCor(retencaoAnaliseCreditoVO.getNmCor());
            getConsultaAdimplenciaForm().setVlPreco(Moeda.mascaraDinheiro(Double.parseDouble(retencaoAnaliseCreditoVO.getVlPrecoReal()), Moeda.DINHEIRO_REAL, false));
            getConsultaAdimplenciaForm().setDsMeioPagamento(retencaoAnaliseCreditoVO.getDsTipoPagamentoAparelho());
            getConsultaAdimplenciaForm().setDsMotivoAlteracaoEndereco(retencaoAnaliseCreditoVO.getDsMotivoAlteracaoEndereco());
            getConsultaAdimplenciaForm().setVlPercentualDesconto(retencaoAnaliseCreditoVO.getVlPercentualDesconto());
            getConsultaAdimplenciaForm().setNrParcelas(retencaoAnaliseCreditoVO.getNrParcelas());
            getConsultaAdimplenciaForm().setVlParcela(Moeda.mascaraDinheiro(Double.parseDouble(retencaoAnaliseCreditoVO.getVlParcela()), Moeda.DINHEIRO_REAL, false));
            getConsultaAdimplenciaForm().setVlPrecoComDesconto(Moeda.mascaraDinheiro(Double.parseDouble(retencaoAnaliseCreditoVO.getVlPrecoAbs()), Moeda.DINHEIRO_REAL, false));
            getConsultaAdimplenciaForm().setIdRetencao(idRetencao);

            request.setAttribute("nmLoginUsuario", retencaoAnaliseCreditoVO.getNmLoginUsuario());
            request.setAttribute("nmPessoa", retencaoAnaliseCreditoVO.getNmPessoa());

            getOfertaAparelhoForm().setIdAparelho(retencaoAnaliseCreditoVO.getIdAparelho());
            getOfertaAparelhoForm().setIdAparelhoCor(retencaoAnaliseCreditoVO.getFinalizaRetencaoVO().getRetencao().getRetencaoBody().getOfertas().getAparelhos().getIdAparelhoCor());
            getOfertaAparelhoForm().setDsMaterial(retencaoAnaliseCreditoVO.getDsMaterial());
            getOfertaAparelhoForm().setIdSegmentacaoLinha(retencaoAnaliseCreditoVO.getIdSegmentacaoLinha());

            getDadosClienteForm().setNumeroLinha(retencaoAnaliseCreditoVO.getNrLinha());
            getDadosClienteForm().setIdDestinoPrevisto(retencaoAnaliseCreditoVO.getIdRespostaDestino());
            getDadosClienteForm().setIdIntencaoCancelamento(retencaoAnaliseCreditoVO.getIdRespostaIntencao());

            request.setAttribute("idRetencao", idRetencao);

            if (ConstantesCRM.SONE.equals(request.getParameter("inVoltar"))) {
                request.setAttribute("inVoltar", request.getParameter("inVoltar"));
            }
            if (retencaoAnaliseCreditoVO.getFinalizaRetencaoVO() != null) {
                this.finalizaRetencaoVO = FinalizaRetencaoVO.Factory.newInstance();
                this.finalizaRetencaoVO.set(retencaoAnaliseCreditoVO.getFinalizaRetencaoVO().copy());
            }
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:analisarEndereco()", e);
            FormError formError = globalApp.buildFormError(e, new StringBuffer(request.getContextPath()).append("/fidelizacao/analisarEndereco.do").toString());
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    /**
     * Metodo que gerencia o status de analise de credito por um analista BKO.
     * Ele pode aprovar ou reprovar o processo de retencao retido por restricao
     * financeira.
     * 
     * @jpf:action
     * @jpf:forward name="success" path="mensagemInadimplencia.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward finalizarAnaliseCredito(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        ConsultaAdimplenciaForm form = (ConsultaAdimplenciaForm) formParam;
        try {
            String acao = request.getParameter(ConstantesCRM.SACTION);
            String idAtendimento = request.getParameter("idAtendimento");
            RetencaoAnaliseCreditoVO retencaoAnaliseCreditoVO = RetencaoAnaliseCreditoVODocument.RetencaoAnaliseCreditoVO.Factory.newInstance();

            if ("APROVAR".equals(acao)) {
                retencaoAnaliseCreditoVO.setIdTipoAnalise(IDAPROVADOANALISTABKO);
                getConsultaAdimplenciaForm().setStatusAvaliacao("An·lise de crÈdito aprovada.");
                getOfertaAparelhoForm().setDsMaterial(request.getParameter("dsMaterial"));
                getOfertaAparelhoForm().setSAP(request.getParameter("dsMaterial"));
            } else if ("REPROVAR".equals(acao)) {
                retencaoAnaliseCreditoVO.setIdTipoAnalise(IDREPROVADOANALISTABKO);
                getConsultaAdimplenciaForm().setStatusAvaliacao("An·lise de crÈdito reprovada.");
            }
            retencaoAnaliseCreditoVO.setDsObsAnalista(form.getDsObsAnalista());
            retencaoAnaliseCreditoVO.setIdAtendimento(idAtendimento);
            retencaoAnaliseCreditoVO.setIdRetencao(form.getIdRetencao());

            retencaoRestricaoFacade.finalizarAnaliseCredito(getIdUsuario(request), retencaoAnaliseCreditoVO);

            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:getHistorico()", e);
            FormError formError = globalApp.buildFormError(e, new StringBuffer(request.getContextPath()).append("/fidelizacao/getLinhas.do").toString());
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    /**
     * Metodo que gerencia o status de analise de endereco por um analista BKO.
     * Ele pode aprovar ou reprovar o processo de solciitacao de alteracao de
     * endereco.
     * 
     * @jpf:action
     * @jpf:forward name="success" path="mensagemInadimplencia.jsp"
     * @jpf:forward name="analiseEndereco" path="analisarEndereco.do"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward finalizarAnaliseEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

        String acao = request.getParameter(ConstantesCRM.SACTION);
        String idAtendimento = request.getParameter("idAtendimento");
        String idGrupo = (String) request.getSession().getAttribute("idGrupoEndereco");
        RetencaoAnaliseCreditoVO retencaoAnaliseCreditoVO = RetencaoAnaliseCreditoVODocument.RetencaoAnaliseCreditoVO.Factory.newInstance();

        OfertaAparelhoForm form = (OfertaAparelhoForm) formParam;

        try {

            TelaInicialVO telaInicial = telaInicialFac.getTelaInicial(getIdUsuario(request), getParametrosVO().getNrLinha());

            // Chama COMSAP para fazer estorno (necessario enviar dsUF e
            // idAparelho)
            RetencaoSAPVO retensaoSAPVO = RetencaoSAPVODocument.RetencaoSAPVO.Factory.newInstance();

            retensaoSAPVO.addNewCLIENTE().setNOME(getParametrosVO().getNmCliente());
            retensaoSAPVO.getCLIENTE().setDOCUMENTO(request.getParameter("nrCPF").toString());
            retensaoSAPVO.getCLIENTE().setTELEFONE(getParametrosVO().getNrLinha());
            retensaoSAPVO.getCLIENTE().setIDPESSOA(getParametrosVO().getIdPessoaCliente());
            // retensaoSAPVO.getCLIENTE().setIDGRUPO(retencaoAnaliseCreditoVO.getIdGrupo());
            retensaoSAPVO.getCLIENTE().setIDGRUPO(idGrupo);
            retensaoSAPVO.getCLIENTE().setIDSEGMENTACAO(getParametrosVO().getIdSegmentacao());

            retensaoSAPVO.addNewENDERECO();
            retensaoSAPVO.getENDERECO().setESTADOCLI(telaInicial.getEnderecoBaseVO().getUFVO().getIdUF());
            retensaoSAPVO.getENDERECO().setRUACLI(telaInicial.getEnderecoBaseVO().getDsEndereco() + ", " + telaInicial.getEnderecoBaseVO().getNrEndereco() + " - " + telaInicial.getEnderecoBaseVO().getDsComplemento());
            retensaoSAPVO.getENDERECO().setBAIRROCLI(telaInicial.getEnderecoBaseVO().getDsBairro());
            retensaoSAPVO.getENDERECO().setCIDADECLI(telaInicial.getEnderecoBaseVO().getDsCidade());
            retensaoSAPVO.getENDERECO().setCEPCLI(telaInicial.getEnderecoBaseVO().getNrCEP());

            retensaoSAPVO.addNewENDERECO().setESTADOENT(getOfertaAparelhoForm().getEstado());
            retensaoSAPVO.addNewPRODUTO().setIDAPARELHO(getOfertaAparelhoForm().getIdAparelho());

            retensaoSAPVO.addNewPRODUTO().setIDAPARELHO(form.getIdAparelho());
            retensaoSAPVO.getPRODUTO().setDATAREMESSA(new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date()));
            retensaoSAPVO.getPRODUTO().setVALOR(formatarDecimal(getConsultaAdimplenciaForm().getVlPrecoComDesconto(), 1));
            retensaoSAPVO.getPRODUTO().setMATERIAL(getOfertaAparelhoForm().getDsMaterial());
            retensaoSAPVO.getPRODUTO().setQUANTIDADE(ConstantesCRM.SONE);
            retensaoSAPVO.getPRODUTO().setMEIOPAGTO(getConsultaAdimplenciaForm().getDsMeioPagamento());
            retensaoSAPVO.getPRODUTO().setCONDPGTO(getConsultaAdimplenciaForm().getNrParcelas());

            if ("REPROVAR".equals(acao)) {

                // inExecutar 0-Procedimento normal; 1->Baixa; 2-Estorno;
                retensaoSAPVO.setInExecutar(ConstantesCRM.STWO);

                // inRetornoOld 0-Procedimento normal; 1->Estoque ja realizado
                retensaoSAPVO.setInRetornoOld(ConstantesCRM.SONE);

                String xmlText = removeAcentuacaoXML(retensaoSAPVO.xmlText(), "RetencaoSAPVO", "fidelizacao.fo.vivo.com.br/vo");
                retensaoSAPVO = RetencaoSAPVODocument.Factory.parse(xmlText).getRetencaoSAPVO();

                retencaoRestricaoFacade.executarBaixa(getIdUsuario(request), retensaoSAPVO);

                retencaoAnaliseCreditoVO.setIdTipoAnalise(IDENDERECOREPROVADPORBKO);
                retencaoAnaliseCreditoVO.setDsObsAnalista(request.getParameter("dsObsAnalista"));
                retencaoAnaliseCreditoVO.setIdAtendimento(idAtendimento);
                retencaoAnaliseCreditoVO.setIdRetencao(getConsultaAdimplenciaForm().getIdRetencao());

                retencaoRestricaoFacade.finalizarAnaliseEndereco(getIdUsuario(request), retencaoAnaliseCreditoVO);

                getConsultaAdimplenciaForm().setStatusAvaliacao("An·lise de endereÁo reprovada.");

            } else if ("APROVAR".equals(acao)) {

                // inExecutar 0-Procedimento normal; 1->Baixa; 2-Estorno;
                retensaoSAPVO.setInExecutar(ConstantesCRM.SZERO);

                // inRetornoOld 0-Procedimento normal; 1->Estoque ja realizado
                retensaoSAPVO.setInRetornoOld(ConstantesCRM.SONE);

                retensaoSAPVO.getENDERECO().setESTADOENT(request.getParameter("sgUF"));
                retensaoSAPVO.getENDERECO().setRUAENT(request.getParameter("rua") + ", " + request.getParameter("numero") + " - " + request.getParameter("complemento"));
                retensaoSAPVO.getENDERECO().setBAIRROENT(request.getParameter("bairro"));
                retensaoSAPVO.getENDERECO().setCIDADEENT(request.getParameter("cidade"));
                retensaoSAPVO.getENDERECO().setCEPENT(request.getParameter("cep"));

                retensaoSAPVO.getPRODUTO().setNUMEROPEDIDO(getConsultaAdimplenciaForm().getIdRetencao());

                String xmlText = removeAcentuacaoXML(retensaoSAPVO.xmlText(), "RetencaoSAPVO", "fidelizacao.fo.vivo.com.br/vo");
                retensaoSAPVO = RetencaoSAPVODocument.Factory.parse(xmlText).getRetencaoSAPVO();

                // RetencaoRetornoSAPVO retornoSAP =
                // RetencaoRetornoSAPVO.Factory.newInstance();
                RetencaoRetornoSAPVO retornoSAP = ofertaAparelhoFac.getComSAP(getIdUsuario(request), retensaoSAPVO);

                if (null != retornoSAP.getOrdemServico() && !ConstantesCRM.SVAZIO.equals(retornoSAP.getOrdemServico())) {
                    retencaoAnaliseCreditoVO.setIdTipoAnalise(ConstantesCRM.SZERO);
                    retencaoAnaliseCreditoVO.setDsObsAnalista(request.getParameter("dsObsAnalista"));
                    retencaoAnaliseCreditoVO.setIdAtendimento(idAtendimento);
                    retencaoAnaliseCreditoVO.setIdRetencao(request.getParameter("idRetencao"));

                    retencaoAnaliseCreditoVO.addNewDadosEndereco();
                    retencaoAnaliseCreditoVO.getDadosEndereco().setDsEndereco(request.getParameter("rua"));
                    retencaoAnaliseCreditoVO.getDadosEndereco().setNrEndereco(request.getParameter("numero"));
                    retencaoAnaliseCreditoVO.getDadosEndereco().setDsComplemento(limitaCaracteres(request.getParameter("complemento"), 20));
                    retencaoAnaliseCreditoVO.getDadosEndereco().setDsBairro(request.getParameter("bairro"));
                    retencaoAnaliseCreditoVO.getDadosEndereco().setDsCidade(request.getParameter("cidade"));
                    retencaoAnaliseCreditoVO.getDadosEndereco().setDsUF(request.getParameter("estado"));
                    retencaoAnaliseCreditoVO.getDadosEndereco().setDsCEP(request.getParameter("cep"));
                    retencaoAnaliseCreditoVO.setNrOrdemVenda(retornoSAP.getOrdemServico());
                    retencaoAnaliseCreditoVO.setIdTipoEncerramento(TIPO_ENCERRAMENTO);
                    retencaoRestricaoFacade.finalizarAnaliseEndereco(getIdUsuario(request), retencaoAnaliseCreditoVO);

                    getConsultaAdimplenciaForm().setStatusAvaliacao("An·lise de endereÁo aprovada.");
                } else {
                    ActionRedirect forward = new ActionRedirect(mapping.findForward("analiseEndereco"));
                    forward.addParameter("idRetencao", getConsultaAdimplenciaForm().getIdRetencao());
                    forward.addParameter("msgErro", retornoSAP.getDescErro());
                    forward.addParameter("sgUF", request.getParameter("sgUF"));
                    request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                    return forward;
                }
            }
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:getHistorico()", e);
            FormError formError = globalApp.buildFormError(e, new StringBuffer(request.getContextPath()).append("/fidelizacao/getLinhas.do").toString());
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    private String formatarObsHistorico(String obs) {
        String[] arrayString;
        if (obs.indexOf(" ") > 0) {
            arrayString = obs.split(" ");
        } else {
            arrayString = new String[] { obs };
        }

        return formatarArrayString(arrayString);
    }

    private String formatarArrayString(String[] arrayString) {
        StringBuffer retorno = new StringBuffer();
        int contador = 0;
        for (int i = 0; i < arrayString.length; i++) {
            for (int y = 0; y < arrayString[i].length(); y++) {
                if (contador == 40) {
                    retorno.append(" ");
                    contador = 0;
                }
                // if(arrayString[i].charAt(y) == ' '){
                // contador = 0;
                // }
                retorno.append(arrayString[i].charAt(y));
                contador++;
            }
            retorno.append(" ");
        }

        return retorno.toString();
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="intencaoCanelamento.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward getIntencaoCancelamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("FidelizacaoController:getIntencaoCancelamento()::inicio");
        try {
            request.getSession().removeAttribute("sgOferta");
            request.getSession().removeAttribute("dsIntencao");
            request.getSession().removeAttribute("dsDestinoPrevisto");
            request.getSession().removeAttribute("inNovaOferta");

            testParameters();

            setListaIntencaoCancelamentoVO(retencaoFAC.getIntencaoCancelamento(getIdUsuario(request), getDadosClienteForm().getDetalheLinha().getIdUFOperadora(), getDadosClienteForm().getIdTipoCliente(), getDadosClienteForm().getDetalheLinha()
                    .getIdSegmentacao(), getParametrosVO().getIdGrupo(), getDadosClienteForm().getDetalheLinha().getIdTipoLinha()));

        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:getIntencaoCancelamento()", e);
            FormError formError = globalApp.buildFormError(e, new StringBuffer(request.getContextPath()).append("/fidelizacao/getLinhas.do").toString());
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        log.debug("FidelizacaoController:getIntencaoCancelamento()::fim");
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="destinoPrevisto.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward getDestinoPrevisto(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("FidelizacaoController:getDestinoPrevisto()::inicio");
        PesquisaDestinoPrevistoForm form = (PesquisaDestinoPrevistoForm) formParam;
        try {
            testParameters();
            log.error("Exception - FidelizacaoController:getDestinoPrevisto() ApÛs testParameters");

            getDadosClienteForm().setIdIntencaoCancelamento(form.getIntencaoSelecionada());
            getPesquisaDestinoPrevistoForm().setIntencaoSelecionada(form.getIntencaoSelecionada());

            log.error("Exception - FidelizacaoController:getDestinoPrevisto() SetDestino ");
            setListaDestinoPrevistoVO(retencaoFAC.getDestinosPrevistos(getIdUsuario(request), getDadosClienteForm().getDetalheLinha().getIdUFOperadora(), form.getIntencaoSelecionada(), getDadosClienteForm().getIdTipoCliente(), getDadosClienteForm()
                    .getDetalheLinha().getIdSegmentacao(), getParametrosVO().getIdGrupo(), getDadosClienteForm().getDetalheLinha().getIdTipoLinha()));

            request.getSession().setAttribute("dsIntencao", request.getParameter("dsIntencao"));
            log.error("Exception - FidelizacaoController:getDestinoPrevisto() dsIntencao " + request.getParameter("dsIntencao"));

        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:getDestinoPrevisto()", e);
            FormError formError = globalApp.buildFormError(e, new StringBuffer(request.getContextPath()).append("/fidelizacao/getLinhas.do").toString());
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        log.debug("FidelizacaoController:getDestinoPrevisto()::fim");
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="ConsultaMatrizOferta.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward getMatrizOferta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        log.debug("FidelizacaoController:getMatrizOferta()::inicio");
        String acao = request.getParameter(ConstantesCRM.SACTION);
        ShowMatrizOfertaForm form = (ShowMatrizOfertaForm) formParam;
        try {
            testParameters();
            // Caso, apos analise de inadimplencia, seja selecionada a opcao
            // Nova Oferta
            if ("ANALISEAPROVADA_NOVAOFERTA".equals(acao)) {
                request.getSession().setAttribute(ConstantesCRM.SACTION, "analiseCreditoAprovada");
                request.getSession().setAttribute("idRetencao", request.getParameter("idRetencao"));
                request.getSession().setAttribute("idRetencaoOld", request.getParameter("idRetencao"));
                request.getSession().setAttribute("dsIntencao", request.getParameter("dsIntencao"));
                request.getSession().setAttribute("inNovaOferta", ConstantesCRM.SONE);
                request.getSession().setAttribute("inAdimplente", ConstantesCRM.SONE);
                request.getSession().setAttribute("sgOferta", request.getParameter("sgOferta"));

                getDadosClienteForm().setIdDestinoPrevisto(request.getParameter("idDestino"));
                getDadosClienteForm().setIdIntencaoCancelamento(request.getParameter("idIntencao"));
                getShowMatrizOfertaForm().setIdDestinoPrevisto(request.getParameter("idDestino"));
                getOfertaAparelhoForm().setDsMaterial(request.getParameter("dsMaterial"));
                getOfertaAparelhoForm().setSAP(request.getParameter("dsMaterial"));
                String[] dados = { getParametrosVO().getIdUfOperadora(), request.getParameter("idSegmentacaoLinha"),
                        // COmentario È necess·rio colocar o parametro da
                        // pesquisa
                        // getParametrosVO().getInTipoPessoa(),
                        "PF", // PESSOA FISICA
                        request.getParameter("idIntencao"), request.getParameter("idDestino"), getParametrosVO().getIdGrupo() };

                setListaOfertasDisponiveisVO(ofertaRealizadaFac.getOfertasDisponiveis(getIdUsuario(request), dados, getDadosClienteForm().getDetalheLinha().getIdTipoLinha()));
                setListaOfertasVO(getListaOfertasDisponiveisVO());

                ListaDetalheLinhaVO listaDetalheLinhaVO = retencaoFAC.getLinha(getIdUsuario(request), getParametrosVO().getIdPessoaCliente(), request.getParameter("nrLinha"), false, ConstantesCRM.SVAZIO);

                DetalheLinhaVODocument.DetalheLinhaVO detalheLinha = listaDetalheLinhaVO.getDetalheLinhaVOArray(0);
                getDadosClienteForm().setDetalheLinha(DetalheLinhaVODocument.DetalheLinhaVO.Factory.newInstance());
                getDadosClienteForm().getDetalheLinha().setNumero(detalheLinha.getNumero());
                getDadosClienteForm().getDetalheLinha().setSegmentacao(detalheLinha.getSegmentacao());
                getDadosClienteForm().getDetalheLinha().setRentabilidade(detalheLinha.getRentabilidade());
                getDadosClienteForm().getDetalheLinha().setContrato(detalheLinha.getContrato());
                getDadosClienteForm().getDetalheLinha().setValorMulta(detalheLinha.getValorMulta());
                getDadosClienteForm().getDetalheLinha().setDtFimContrato(detalheLinha.getDtFimContrato());
                getDadosClienteForm().getDetalheLinha().setPlano(detalheLinha.getPlano());
                getDadosClienteForm().getDetalheLinha().setDtHabilitacao(detalheLinha.getDtHabilitacao());
                getDadosClienteForm().getDetalheLinha().setValorMulta(detalheLinha.getValorMulta());
                getDadosClienteForm().getDetalheLinha().setValorProRata(detalheLinha.getValorProRata());
                getDadosClienteForm().getDetalheLinha().setIdUFOperadora(detalheLinha.getIdUFOperadora());
                getDadosClienteForm().getDetalheLinha().setIdSegmentacao(detalheLinha.getIdSegmentacao());
                getDadosClienteForm().getDetalheLinha().setIdLinha(detalheLinha.getIdLinha());
                getDadosClienteForm().getDetalheLinha().setIdTipoLinha(detalheLinha.getIdTipoLinha());
                getDadosClienteForm().getDetalheLinha().setIdPessoaDePara(detalheLinha.getIdPessoaDePara());
                request.getSession().setAttribute("idPessoaDePara", getDadosClienteForm().getDetalheLinha().getIdPessoaDePara());

            } else {
                request.getSession().removeAttribute("analiseAprovada");
                request.getSession().removeAttribute("idRetencao");
                getDadosClienteForm().setIdDestinoPrevisto(form.getIdDestinoPrevisto());
                getShowMatrizOfertaForm().setIdDestinoPrevisto(form.getIdDestinoPrevisto());
                String[] dados = { getDadosClienteForm().getDetalheLinha().getIdUFOperadora(), getDadosClienteForm().getDetalheLinha().getIdSegmentacao(), getDadosClienteForm().getIdTipoCliente(), getDadosClienteForm().getIdIntencaoCancelamento(),
                        getDadosClienteForm().getIdDestinoPrevisto(), getParametrosVO().getIdGrupo() };
                setListaOfertasDisponiveisVO(ofertaRealizadaFac.getOfertasDisponiveis(getIdUsuario(request), dados, getDadosClienteForm().getDetalheLinha().getIdTipoLinha()));
                setListaOfertasVO(getListaOfertasDisponiveisVO());
            }
            request.getSession().setAttribute("dsDestinoPrevisto", request.getParameter("dsDestinoPrevisto"));

            // Se cliente possuir mais de uma linha, flag È setado para
            // tratamento
            // especial caso o cliente solicite o cancelamento da linha.
            if (getListaLinhasVO().getDetalheLinhaVOArray().length > 1) {
                getConsultaMatrizOfertaForm().setInCancelamentoLinhasAssociadas(true);
            }

        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:getMatrizOferta()", e);
            FormError formError = globalApp.buildFormError(e, request.getContextPath() + "/fidelizacao/getLinhas.do");
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        log.debug("FidelizacaoController:getMatrizOferta()::fim");
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="ConsultaMatrizOferta.jsp"
     * @jpf:forward name="bonus" path="Bonus.jsp"
     * @jpf:forward name="migracao" path="MigracaoPrePago.jsp"
     * @jpf:forward name="pontos" path="Pontos.jsp"
     * @jpf:forward name="suspensao" path="SuspensaoTemp.jsp"
     * @jpf:forward name="adequacao" path="AdequacaoPlano.jsp"
     * @jpf:forward name="aparelho" path="ofertaAparelho.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward redirectMatrizOferta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("FidelizacaoController:redirectMatrizOferta()");
        request.getSession().removeAttribute("idRetencao");
        testParameters();

        ConsultaMatrizOfertaForm form = (ConsultaMatrizOfertaForm) formParam;

        setListaOfertasDisponiveisVO(getListaOfertas(form.getOfertasDisp()));
        setListaOfertasRealizadasVO(getListaOfertas(form.getOfertasReal()));
        setListaOfertasAceitasVO(getListaOfertas(form.getOfertasAceita()));

        String destino = new String(ConstantesCRM.SUCCESS);
        String oferta = getListaOfertasAceitasVO().getItemListaDescricaoVOArray(0).getSigla().toUpperCase();

        if (oferta.equals(this.BONUS)) {
            /************ BONUS ********************/
            destino = "bonus";
            carregarListaBonus(request);

        } else if (oferta.equals(this.MIGRACAO)) {
            /************ MIGRACAO *****************/
            destino = "migracao";
            carregarListaMigracao(request);
            request.setAttribute("ddd", getDadosClienteForm().getDetalheLinha().getNumero().substring(0, 2));

        } else if (oferta.equals(this.PONTOS)) {
            /************ PONTOS *******************/
            destino = "pontos";

        } else if (oferta.equals(this.ADEQUACAO_PLANO)) {
            /************ ADEQUACAO PLANOS *********/
            destino = "adequacao";
            carregarListaPlanos(request);

        } else if (oferta.equals(this.SUSPENCAO_TEMP)) {
            /************ SUSPENSAO TEMPORARIA *****/
            destino = "suspensao";
            carregarDiasSuspensaoTemp(request);

        } else if (oferta.equals(this.APARELHOS)) {
            /************ APARELHOS ***************/
            destino = "aparelho";
            FidelizacaoListaGeralVO fidelizacaoListaGeralVO = FidelizacaoListaGeralVO.Factory.newInstance();
            fidelizacaoListaGeralVO.addNewItemListaVO();
            getConsultaMatrizOfertaForm().setSgOfertaAceita(oferta);
            getOfertaAparelhoForm().setListaDescontoVO(fidelizacaoListaGeralVO.getItemListaVOArray());
            getOfertaAparelhoForm().setListaParcelaVO(fidelizacaoListaGeralVO.getItemListaVOArray());

            // Validar valor de parcelamento minimo
            try {
                String user = ConstantesCRM.getCurrentUser(request.getSession());
                String key = ConstantesCRM.SVAZIO;
                if ("PF".equals(getDadosClienteForm().getIdTipoCliente())) {
                    key = "PF_VLR_PARC_MIN";
                } else if ("PJ".equals(getDadosClienteForm().getIdTipoCliente())) {
                    key = "PJ_VLR_PARC_MIN";
                }
                ParametroVO parametro = GetParametro.getInstace().getParametroVO(user, key);
                String valor = parametro.getDsValorParametro();

                getConsultaMatrizOfertaForm().setVlMinimoParcela(valor);

            } catch (Exception e) {
                log.error("FidelizacaoController::redirectMatrizOferta::Exception", e);
            }

            getConsultaMatrizOfertaForm().setPercDesconto(ConstantesCRM.SVAZIO);
            getConsultaMatrizOfertaForm().setVlParcela(ConstantesCRM.SVAZIO);
            getConsultaMatrizOfertaForm().setNroParcela(ConstantesCRM.SVAZIO);
            getConsultaMatrizOfertaForm().setPercDesconto(ConstantesCRM.SVAZIO);
            getConsultaMatrizOfertaForm().setVlDesconto(ConstantesCRM.SVAZIO);
            getConsultaMatrizOfertaForm().setIndex(ConstantesCRM.SVAZIO);
            getConsultaMatrizOfertaForm().setMeioPagamento(ConstantesCRM.SVAZIO);
            getConsultaMatrizOfertaForm().setIdMeioPagamento(ConstantesCRM.SVAZIO);
            getConsultaMatrizOfertaForm().setInExcecao(false);

            carregarAparelho(ConstantesCRM.SONE, this.APARELHOS, request);

        } else if (oferta.equals(this.COMODATO)) {
            destino = "aparelho";
            getConsultaMatrizOfertaForm().setSgOfertaAceita(oferta);
            getConsultaMatrizOfertaForm().setInTipoPessoa(getDadosClienteForm().getIdTipoCliente());
            getConsultaMatrizOfertaForm().setIdTipoCarteira(getDadosClienteForm().getIdCarteirizacao());
            carregarAparelho(ConstantesCRM.SONE, this.COMODATO, request);
        }
        request.getSession().setAttribute("sgOferta", oferta);
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(destino);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="ofertaAparelho.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward getTodosAparelhos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("FidelizacaoController:getTodosAparelhos()");
        ConsultaMatrizOfertaForm form = (ConsultaMatrizOfertaForm) formParam;
        try {
            testParameters();
            String oferta = getListaOfertasAceitasVO().getItemListaDescricaoVOArray(0).getSigla().toUpperCase();
            form.setPercDesconto(ConstantesCRM.SVAZIO);
            form.setVlParcela(ConstantesCRM.SVAZIO);
            form.setNroParcela(ConstantesCRM.SVAZIO);
            form.setPercDesconto(ConstantesCRM.SVAZIO);
            form.setVlDesconto(ConstantesCRM.SVAZIO);
            form.setIndex(ConstantesCRM.SVAZIO);
            form.setMeioPagamento(ConstantesCRM.SVAZIO);
            form.setIdMeioPagamento(ConstantesCRM.SVAZIO);
            carregarAparelho(form.getInExcecao() ? ConstantesCRM.SZERO : ConstantesCRM.SONE, oferta, request);
            getConsultaMatrizOfertaForm().setInExcecao(form.getInExcecao());
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:getTodosAparelhos()", e);
            FormError formError = globalApp.buildFormError(e, request.getContextPath() + "/fidelizacao/getLinhas.do");
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="bonus" path="Bonus.jsp"
     * @jpf:forward name="success" path="mensagemEncerramento.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward reterBonus(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        log.debug("FidelizacaoController:reterBonus()");
        request.getSession().removeAttribute("idRetencao");
        ConsultaMatrizOfertaForm form = (ConsultaMatrizOfertaForm) formParam;
        try {
            testParameters();

            FinalizaRetencaoVO finalizaRetencaoVO = getFinalizaRetencaoComDadosGerais(request);
            finalizaRetencaoVO.getRetencao().getRetencaoHeader().setInExcecao(form.getInExcecao() ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
            finalizaRetencaoVO.getRetencao().getRetencaoHeader().setIdTipoEncerramento(TIPO_ENCERRAMENTO);

            finalizaRetencaoVO.getRetencao().getRetencaoBody().addNewOfertas();
            finalizaRetencaoVO.getRetencao().getRetencaoBody().getOfertas().addNewBonus();
            Bonus bonus = finalizaRetencaoVO.getRetencao().getRetencaoBody().getOfertas().getBonus();

            bonus.setIdMatrizBonus(form.getBonusSelecionado());
            bonus.setNmBonus(getBonusDescricao(form.getBonusSelecionado()));
            bonus.setDtInicioVigencia(form.getDtInicio());
            bonus.setDtFinalVigencia(form.getDtFim());

            getConsultaMatrizOfertaForm().setTipoEncerramento(TIPO_ENCERRAMENTO);
            getConsultaMatrizOfertaForm().setBonusSelecionado(form.getBonusSelecionado());
            getConsultaMatrizOfertaForm().setDtInicio(form.getDtInicio());
            getConsultaMatrizOfertaForm().setDtFim(form.getDtFim());
            getConsultaMatrizOfertaForm().setInExcecao(form.getInExcecao());

            RetornoVO retorno = ofertaRealizadaFac.finalizaRetencao(getIdUsuario(request), finalizaRetencaoVO);
            /*
             * Verifica se ser· necess·ria atualizaÁ„o de contato do cliente
             * (mais de 30 dias sem atualizaÁ„o)
             */
            if (retorno.getFidelizacaoContatoVO() != null && retorno.getFidelizacaoContatoVO().getIdPessoa() != null) {
                request.setAttribute("inAtualizacaoContato", ConstantesCRM.SONE);
                request.setAttribute("dsContato", retorno.getFidelizacaoContatoVO().getDsContato());
                request.setAttribute("idPessoa", retorno.getFidelizacaoContatoVO().getIdPessoa());
            }

            request.getSession().removeAttribute("sgOferta");
            request.getSession().removeAttribute("dsIntencao");
            request.getSession().removeAttribute("dsDestinoPrevisto");

            getConsultaMatrizOfertaForm().setIdRetencao(retorno.getValor());
            request.setAttribute("idRetencao", retorno.getValor());

            String nrRetProtocolo = retorno.getNrProtocolo() != null ? retorno.getNrProtocolo() : "";
            if (!getNrProtocolo().equals(nrRetProtocolo)) {
                String msgProtocolo = "Foi gerado para a linha " + ConstantesCRM.formatPhoneNumber(finalizaRetencaoVO.getCliente().getNrLinha()) + " o n˙mero de protocolo " + nrRetProtocolo + " referente ‡ aÁ„o RETER.";
                request.setAttribute("nrProtocolo", msgProtocolo);
                request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, nrRetProtocolo);
            }

            carregarMsgEcerramento(TIPO_ENCERRAMENTO, getDadosClienteForm().getDetalheLinha().getIdUFOperadora(), request);

        } catch (TuxedoWarningException twe) {
            log.warn("ConsultaMatrizOfertaController:reterBonus(" + twe.getMessage() + ")");
        
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:reterBonus()", e);
            FormError formError = globalApp.buildFormError(e, request.getContextPath() + "/fidelizacao/getLinhas.do");
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="migracao" path="MigracaoPrePago.jsp"
     * @jpf:forward name="success" path="mensagemEncerramento.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward reterMigracao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        log.debug("FidelizacaoController:reterMigracao()");
        request.getSession().removeAttribute("idRetencao");
        CadastrarMigracaoForm form = (CadastrarMigracaoForm) formParam;
        try {
            testParameters();

            if (!prePagoFac.validarLinha(getIdUsuario(request), form.getNrNovoPre().replace(')', 'X').replace('(', 'X').replace('-', 'X').replaceAll("X", ConstantesCRM.SVAZIO)).getResult().equalsIgnoreCase(ConstantesCRM.SOK)) {
                request.setAttribute(ConstantesCRM.SERROR, "Numero de linha PrÈ-Paga inv·lido.\\nPor favor informe outro n˙mero.");
                request.setAttribute("ddd", getDadosClienteForm().getDetalheLinha().getNumero().substring(0, 2));
                getCadastrarMigracaoForm().setDtInicio(form.getDtInicio());
                getCadastrarMigracaoForm().setDtFim(form.getDtFim());
                getCadastrarMigracaoForm().setMigracaoSelecionada(form.getMigracaoSelecionada());
                getCadastrarMigracaoForm().setValor(form.getValor());
                getCadastrarMigracaoForm().setDisValor(form.getValor());
                getCadastrarMigracaoForm().setInExcecao(form.getInExcecao());
                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                return mapping.findForward("migracao");
            }

            FinalizaRetencaoVO finalizaRetencaoVO = getFinalizaRetencaoComDadosGerais(request);
            finalizaRetencaoVO.getRetencao().getRetencaoHeader().setInExcecao(testarValor(form.getInExcecao()) ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
            finalizaRetencaoVO.getRetencao().getRetencaoHeader().setIdTipoEncerramento(TIPO_ENCERRAMENTO);

            finalizaRetencaoVO.getRetencao().getRetencaoBody().addNewOfertas();
            finalizaRetencaoVO.getRetencao().getRetencaoBody().getOfertas().addNewMigracao();
            Migracao migracao = finalizaRetencaoVO.getRetencao().getRetencaoBody().getOfertas().getMigracao();

            migracao.setNrLinhaAtual(getDadosClienteForm().getDetalheLinha().getNumero());
            migracao.setNrLinhaNova(form.getNrNovoPre());
            migracao.setVlSaldo(formatarDecimal(form.getValor(), 1));
            migracao.setDtInicioVigencia(form.getDtInicio());
            migracao.setDtFinalVigencia(form.getDtFim());

            RetornoVO retorno = ofertaRealizadaFac.finalizaRetencao(getIdUsuario(request), finalizaRetencaoVO);
            /*
             * Verifica se ser· necess·ria atualizaÁ„o de contato do cliente
             * (mais de 30 dias sem atualizaÁ„o)
             */
            if (retorno.getFidelizacaoContatoVO() != null && retorno.getFidelizacaoContatoVO().getIdPessoa() != null) {
                request.setAttribute("inAtualizacaoContato", ConstantesCRM.SONE);
                request.setAttribute("dsContato", retorno.getFidelizacaoContatoVO().getDsContato());
                request.setAttribute("idPessoa", retorno.getFidelizacaoContatoVO().getIdPessoa());
            }

            request.getSession().removeAttribute("sgOferta");
            request.getSession().removeAttribute("dsIntencao");
            request.getSession().removeAttribute("dsDestinoPrevisto");

            getConsultaMatrizOfertaForm().setIdRetencao(retorno.getValor());
            request.setAttribute("idRetencao", retorno.getValor());

            String nrRetProtocolo = retorno.getNrProtocolo() != null ? retorno.getNrProtocolo() : ConstantesCRM.SVAZIO;
            if (!getNrProtocolo().equals(nrRetProtocolo)) {
                String msgProtocolo = "Foi gerado para a linha " + ConstantesCRM.formatPhoneNumber(finalizaRetencaoVO.getCliente().getNrLinha()) + " o n˙mero de protocolo " + nrRetProtocolo + " referente ‡ aÁ„o RETER.";
                request.setAttribute("nrProtocolo", msgProtocolo);
                request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, nrRetProtocolo);
            }

            carregarMsgEcerramento(TIPO_ENCERRAMENTO, getDadosClienteForm().getDetalheLinha().getIdUFOperadora(), request);
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:reterMigracao()", e);
            FormError formError = globalApp.buildFormError(e, request.getContextPath() + "/fidelizacao/getLinhas.do");
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="pontos" path="Pontos.jsp"
     * @jpf:forward name="success" path="mensagemEncerramento.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward reterPontos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        log.debug("FidelizacaoController:reterPontos()");
        request.getSession().removeAttribute("idRetencao");
        ConsultaMatrizOfertaForm form = (ConsultaMatrizOfertaForm) formParam;
        try {
            testParameters();

            FinalizaRetencaoVO finalizaRetencaoVO = getFinalizaRetencaoComDadosGerais(request);
            finalizaRetencaoVO.getRetencao().getRetencaoHeader().setInExcecao(form.getInExcecao() ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
            finalizaRetencaoVO.getRetencao().getRetencaoHeader().setIdTipoEncerramento(TIPO_ENCERRAMENTO);

            finalizaRetencaoVO.getRetencao().getRetencaoBody().addNewOfertas();
            finalizaRetencaoVO.getRetencao().getRetencaoBody().getOfertas().addNewPontos();
            finalizaRetencaoVO.getRetencao().getRetencaoBody().getOfertas().getPontos().setQtPontos(form.getQuantidade());

            RetornoVO retorno = ofertaRealizadaFac.finalizaRetencao(getIdUsuario(request), finalizaRetencaoVO);
            /*
             * Verifica se ser· necess·ria atualizaÁ„o de contato do cliente
             * (mais de 30 dias sem atualizaÁ„o)
             */
            if (retorno.getFidelizacaoContatoVO() != null && retorno.getFidelizacaoContatoVO().getIdPessoa() != null) {
                request.setAttribute("inAtualizacaoContato", ConstantesCRM.SONE);
                request.setAttribute("dsContato", retorno.getFidelizacaoContatoVO().getDsContato());
                request.setAttribute("idPessoa", retorno.getFidelizacaoContatoVO().getIdPessoa());
            }

            request.getSession().removeAttribute("sgOferta");
            request.getSession().removeAttribute("dsIntencao");
            request.getSession().removeAttribute("dsDestinoPrevisto");

            getConsultaMatrizOfertaForm().setIdRetencao(retorno.getValor());
            request.setAttribute("idRetencao", retorno.getValor());

            String nrRetProtocolo = retorno.getNrProtocolo() != null ? retorno.getNrProtocolo() : ConstantesCRM.SVAZIO;
            if (!getNrProtocolo().equals(nrRetProtocolo)) {
                String msgProtocolo = "Foi gerado para a linha " + ConstantesCRM.formatPhoneNumber(finalizaRetencaoVO.getCliente().getNrLinha()) + " o n˙mero de protocolo " + nrRetProtocolo + " referente ‡ aÁ„o RETER.";
                request.setAttribute("nrProtocolo", msgProtocolo);
                request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, nrRetProtocolo);
            }

            carregarMsgEcerramento(TIPO_ENCERRAMENTO, getDadosClienteForm().getDetalheLinha().getIdUFOperadora(), request);
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:reterPontos()", e);
            FormError formError = globalApp.buildFormError(e, request.getContextPath() + "/fidelizacao/getLinhas.do");
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="adequacao" path="AdequacaoPlano.jsp"
     * @jpf:forward name="success" path="mensagemEncerramento.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward reterAdequacaoPlano(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("FidelizacaoController:reterAdequacaoPlano()");
        request.getSession().removeAttribute("idRetencao");
        ConsultaMatrizOfertaForm form = (ConsultaMatrizOfertaForm) formParam;
        try {
            testParameters();

            getConsultaMatrizOfertaForm().setPlanoSelecionado(form.getPlanoSelecionado());
            // getConsultaMatrizOfertaForm().setTipoEncerramento(form.getTipoEncerramento());
            getConsultaMatrizOfertaForm().setTipoEncerramento(TIPO_ENCERRAMENTO);
            form = getConsultaMatrizOfertaForm();

            FinalizaRetencaoVO finalizaRetencaoVO = getFinalizaRetencaoComDadosGerais(request);
            finalizaRetencaoVO.getRetencao().getRetencaoHeader().setInExcecao(form.getInExcecao() ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
            finalizaRetencaoVO.getRetencao().getRetencaoHeader().setIdTipoEncerramento(TIPO_ENCERRAMENTO);

            finalizaRetencaoVO.getRetencao().getRetencaoBody().addNewOfertas();
            finalizaRetencaoVO.getRetencao().getRetencaoBody().getOfertas().addNewPlanos();
            Planos planos = finalizaRetencaoVO.getRetencao().getRetencaoBody().getOfertas().getPlanos();

            planos.setIdNovoPlano(form.getPlanoSelecionado());
            planos.setNovoPlano(getPlanoDescricao(form.getPlanoSelecionado()));
            planos.setPlanoAtual(getDadosClienteForm().getDetalheLinha().getPlano());

            RetornoVO retorno = ofertaRealizadaFac.finalizaRetencao(getIdUsuario(request), finalizaRetencaoVO);
            /*
             * Verifica se ser· necess·ria atualizaÁ„o de contato do cliente
             * (mais de 30 dias sem atualizaÁ„o)
             */
            if (retorno.getFidelizacaoContatoVO() != null && retorno.getFidelizacaoContatoVO().getIdPessoa() != null) {
                request.setAttribute("inAtualizacaoContato", ConstantesCRM.SONE);
                request.setAttribute("dsContato", retorno.getFidelizacaoContatoVO().getDsContato());
                request.setAttribute("idPessoa", retorno.getFidelizacaoContatoVO().getIdPessoa());
            }

            request.getSession().removeAttribute("sgOferta");
            request.getSession().removeAttribute("dsIntencao");
            request.getSession().removeAttribute("dsDestinoPrevisto");

            getConsultaMatrizOfertaForm().setIdRetencao(retorno.getValor());
            request.setAttribute("idRetencao", retorno.getValor());

            String nrRetProtocolo = retorno.getNrProtocolo() != null ? retorno.getNrProtocolo() : ConstantesCRM.SVAZIO;
            if (!getNrProtocolo().equals(nrRetProtocolo)) {
                String msgProtocolo = "Foi gerado para a linha " + ConstantesCRM.formatPhoneNumber(finalizaRetencaoVO.getCliente().getNrLinha()) + " o n˙mero de protocolo " + nrRetProtocolo + " referente ‡ aÁ„o RETER.";
                request.setAttribute("nrProtocolo", msgProtocolo);
                request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, nrRetProtocolo);
            }

            carregarMsgEcerramento(TIPO_ENCERRAMENTO, getDadosClienteForm().getDetalheLinha().getIdUFOperadora(), request);
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:reterAdequacaoPlano()", e);
            // ObservaÁ„o: Monta URL de retorno e desvia para o erro
            FormError formError = globalApp.buildFormError(e, request.getContextPath() + "/fidelizacao/getLinhas.do");
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="suspensao" path="SuspensaoTemp.jsp"
     * @jpf:forward name="success" path="mensagemEncerramento.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward reterSuspensaoTemporaria(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("idRetencao");
        log.debug("FidelizacaoController:reterSuspensaoTemporaria()");
        SuspensaoTemporariaForm form = (SuspensaoTemporariaForm) formParam;
        try {
            testParameters();

            FinalizaRetencaoVO finalizaRetencaoVO = getFinalizaRetencaoComDadosGerais(request);
            finalizaRetencaoVO.getRetencao().getRetencaoHeader().setInExcecao(form.getInExcecao() ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
            finalizaRetencaoVO.getRetencao().getRetencaoHeader().setIdTipoEncerramento(TIPO_ENCERRAMENTO);

            finalizaRetencaoVO.getRetencao().getRetencaoBody().addNewOfertas();
            finalizaRetencaoVO.getRetencao().getRetencaoBody().getOfertas().addNewSuspencao();
            finalizaRetencaoVO.getRetencao().getRetencaoBody().getOfertas().getSuspencao().setDtInicio(form.getDtInicio());
            finalizaRetencaoVO.getRetencao().getRetencaoBody().getOfertas().getSuspencao().setDtFim(form.getDtFim());

            RetornoVO retorno = ofertaRealizadaFac.finalizaRetencao(getIdUsuario(request), finalizaRetencaoVO);
            /*
             * Verifica se ser· necess·ria atualizaÁ„o de contato do cliente
             * (mais de 30 dias sem atualizaÁ„o)
             */
            if (retorno.getFidelizacaoContatoVO() != null && retorno.getFidelizacaoContatoVO().getIdPessoa() != null) {
                request.setAttribute("inAtualizacaoContato", ConstantesCRM.SONE);
                request.setAttribute("dsContato", retorno.getFidelizacaoContatoVO().getDsContato());
                request.setAttribute("idPessoa", retorno.getFidelizacaoContatoVO().getIdPessoa());
            }

            request.getSession().removeAttribute("sgOferta");
            request.getSession().removeAttribute("dsIntencao");
            request.getSession().removeAttribute("dsDestinoPrevisto");

            getConsultaMatrizOfertaForm().setIdRetencao(retorno.getValor());
            request.setAttribute("idRetencao", retorno.getValor());

            String nrRetProtocolo = retorno.getNrProtocolo() != null ? retorno.getNrProtocolo() : ConstantesCRM.SVAZIO;
            if (!getNrProtocolo().equals(nrRetProtocolo)) {
                String msgProtocolo = "Foi gerado para a linha " + ConstantesCRM.formatPhoneNumber(finalizaRetencaoVO.getCliente().getNrLinha()) + " o n˙mero de protocolo " + nrRetProtocolo + " referente ‡ aÁ„o RETER.";
                request.setAttribute("nrProtocolo", msgProtocolo);
                request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, nrRetProtocolo);
            }

            carregarMsgEcerramento(TIPO_ENCERRAMENTO, getDadosClienteForm().getDetalheLinha().getIdUFOperadora(), request);
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:reterSuspensaoTemporaria()", e);
            // ObservaÁ„o: Monta URL de retorno e desvia para o erro
            FormError formError = globalApp.buildFormError(e, request.getContextPath() + "/fidelizacao/getLinhas.do");
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="mensagemEncerramento.jsp"
     * @jpf:forward name="erroSAP" path="ofertaAparelho.jsp"
     * @jpf:forward name="erroFatalSAP" path="voltarOfertas.do"
     * @jpf:forward name="erroFatalSAPAnaliseCreditoAprovada"
     *              path="dadosEntregaAparelho.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward reterAparelho(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("FidelizacaoController:reterAparelho()");
        log.debug("FidelizacaoController:reterAparelho()***************AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA***************");
        String erroFatalSAPForward = "erroFatalSAP";
        String erroSAPForward = "erroSAP";
        OfertaAparelhoForm form = (OfertaAparelhoForm) formParam;
        try {
            testParameters();

            removeAcentuacao(form);
            String endereco = new StringBuffer(form.getRua()).append(";").append(form.getNumero()).append(";").append(form.getComplemento()).append(";").append(form.getBairro()).append(";").append(form.getCep()).append(";").append(form.getCidade())
                    .append(";").append(form.getIdUFSelecionado()).toString();

            FinalizaRetencaoVO finalizaRetencaoVO = getFinalizaRetencaoComDadosGerais(request);

            if (request.getSession().getAttribute("idRetencaoOld") != null) {
                erroFatalSAPForward = "erroFatalSAPAnaliseCreditoAprovada";
                erroSAPForward = "erroFatalSAPAnaliseCreditoAprovada";
                // Botao Prosseguir
                if (ConstantesCRM.SONE.equals(request.getSession().getAttribute("inNovaOferta"))) {
                    finalizaRetencaoVO.getRetencao().getRetencaoHeader().setInNovaOferta(request.getSession().getAttribute("inNovaOferta").toString());
                }
            }
            finalizaRetencaoVO.getRetencao().getRetencaoHeader().setInExcecao(form.getInExcecao() ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
            finalizaRetencaoVO.getRetencao().getRetencaoHeader().setIdTipoEncerramento(TIPO_ENCERRAMENTO);

            if (finalizaRetencaoVO.getRetencao().getRetencaoBody().getOfertas() == null) {
                finalizaRetencaoVO.getRetencao().getRetencaoBody().addNewOfertas();
            }
            if (finalizaRetencaoVO.getRetencao().getRetencaoBody().getOfertas().getAparelhos() == null) {
                finalizaRetencaoVO.getRetencao().getRetencaoBody().getOfertas().addNewAparelhos();
            }

            Aparelhos aparelhos = finalizaRetencaoVO.getRetencao().getRetencaoBody().getOfertas().getAparelhos();

            aparelhos.setNmModeloAparelho(form.getModelo());
            aparelhos.setNmCor(form.getCor());
            aparelhos.setIdTipoPagamentoAparelho(getOfertaAparelhoForm().getIdMeioPagamento());
            aparelhos.setVlPrecoReal(!isEmpty(form.getPreco()) ? formatarDecimal(form.getPreco(), 1) : ConstantesCRM.SZERO);
            aparelhos.setVlPrecoAbs(!isEmpty(form.getVlDesconto()) ? formatarDecimal(form.getVlDesconto(), 1) : ConstantesCRM.SVAZIO);
            aparelhos.setNrParcelas(!isEmpty(form.getParcelaSelecionada()) ? form.getParcelaSelecionada() : ConstantesCRM.SVAZIO);
            aparelhos.setVlPercentualDesconto(!isEmpty(form.getDescontoSelecionado()) ? form.getDescontoSelecionado() : ConstantesCRM.SVAZIO);
            aparelhos.setVlParcela(!isEmpty(form.getVlParcela()) ? formatarDecimal(form.getVlParcela(), 1) : ConstantesCRM.SVAZIO);
            aparelhos.setNmTerceiro(form.getNmContato());
            aparelhos.setNrContatoTelefone(form.getTelContato());
            aparelhos.setIdMatrizAparelho(form.getIdAparelho());
            aparelhos.setIdAparelhoCor(form.getIdAparelhoCor());
            aparelhos.setPrzVigencia(form.getPrzVigencia());

            if (form.getIdEntrega() > 0) {
                aparelhos.setInTipoEntrega(ConstantesCRM.SONE);
                aparelhos.setNmLoja(form.getNmLoja());
                aparelhos.setDsEnderecoEntrega(endereco);
                aparelhos.setDsDocumentoTerceiro(form.getRgContato());

            } else {
                if (form.getIdEntrega() == 0) {
                    aparelhos.setInTipoEntrega(ConstantesCRM.STWO);

                    RetencaoRetornoSAPVO retornoSAP = enviarDadosSAP(getIdUsuario(request), getConsultaMatrizOfertaForm().getIdRetencao(), form, request);

                    log.debug("retornoSAP := " + retornoSAP.xmlText());

                    finalizaRetencaoVO.getRetencao().getRetencaoHeader().setIdRetencao(retornoSAP.getIdRetencao());
                    getConsultaMatrizOfertaForm().setIdRetencao(retornoSAP.getIdRetencao());

                    if (retornoSAP.getOrdemServico() != null && !ConstantesCRM.SVAZIO.equals(retornoSAP.getOrdemServico())) {
                        aparelhos.setNrOrdemVenda(retornoSAP.getOrdemServico());
                        request.setAttribute("msgSAP", "Ordem de Venda: " + retornoSAP.getOrdemServico());

                    } else if (retornoSAP.getCodErro() != null && Integer.parseInt(retornoSAP.getCodErro()) > 0) {
                        request.setAttribute("msgSAP", "Erro ao tentar finalizar oferta de aparelho. Por favor tentar novamente.\\nCodigo: " + retornoSAP.getCodErro() + "\\nDescriÁ„o: " + retornoSAP.getDescErro());
                        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                        return mapping.findForward(erroSAPForward);

                    } else if (retornoSAP.getErro() != null && Integer.parseInt(retornoSAP.getErro()) == 0) {
                        request.setAttribute("msgSAP", "Erro ao tentar finalizar oferta de aparelho.\\nDescriÁ„o: " + retornoSAP.getDescErro() + "\\nPor favor tente novamente ou selecione outra oferta.");
                        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                        return mapping.findForward(erroFatalSAPForward);

                    } else if (ConstantesCRM.SONE.equals(retornoSAP.getErro())) {
                        request.setAttribute("msgSAP", retornoSAP.getDescErro());
                        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                        return mapping.findForward(erroFatalSAPForward);
                    }
                } else {
                    aparelhos.setInTipoEntrega(ConstantesCRM.SONE);
                }
            }
            aparelhos.setIdMatrizAparelho(form.getIdAparelho());
            aparelhos.setCdSapAparelho(form.getSAP());

            RetornoVO retorno = ofertaRealizadaFac.finalizaRetencao(getIdUsuario(request), finalizaRetencaoVO);
            /*
             * Verifica se ser· necess·ria atualizaÁ„o de contato do cliente
             * (mais de 30 dias sem atualizaÁ„o)
             */
            if (retorno.getFidelizacaoContatoVO() != null && retorno.getFidelizacaoContatoVO().getIdPessoa() != null) {
                request.setAttribute("inAtualizacaoContato", ConstantesCRM.SONE);
                request.setAttribute("dsContato", retorno.getFidelizacaoContatoVO().getDsContato());
                request.setAttribute("idPessoa", retorno.getFidelizacaoContatoVO().getIdPessoa());
            }

            request.getSession().removeAttribute("sgOferta");
            request.getSession().removeAttribute("dsIntencao");
            request.getSession().removeAttribute("dsDestinoPrevisto");
            request.getSession().removeAttribute("inNovaOferta");

            getConsultaMatrizOfertaForm().setIdRetencao(retorno.getValor());
            request.setAttribute("idRetencao", retorno.getValor());

            String nrRetProtocolo = retorno.getNrProtocolo() != null ? retorno.getNrProtocolo() : ConstantesCRM.SVAZIO;
            if (!getNrProtocolo().equals(nrRetProtocolo)) {
                String msgProtocolo = "Foi gerado para a linha " + ConstantesCRM.formatPhoneNumber(finalizaRetencaoVO.getCliente().getNrLinha()) + " o n˙mero de protocolo " + nrRetProtocolo + " referente ‡ aÁ„o RETER.";
                request.setAttribute("nrProtocolo", msgProtocolo);
                request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, nrRetProtocolo);
            }

            carregarMsgEcerramento(TIPO_ENCERRAMENTO, getDadosClienteForm().getDetalheLinha().getIdUFOperadora(), request);
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:reterAparelho()", e);
            FormError formError = globalApp.buildFormError(e, request.getContextPath() + "/fidelizacao/getLinhas.do");
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    private boolean isEmpty(String valor) {
        return (null == valor || ConstantesCRM.SVAZIO.equals(valor));
    }

    /**
     * @jpf:action
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward getMotivosAlteracaoEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        try {
            String xmlMotivos = ConstantesCRM.SVAZIO;
            xmlMotivos = retencaoRestricaoFacade.getMotivosAlteracaoEndereco(getIdUsuario(request));
            response.setContentType(ConstantesCRM.SContentType);
            BufferedOutputStream saida = new BufferedOutputStream(response.getOutputStream());
            saida.write(xmlMotivos.getBytes(ConstantesCRM.SISO));
            saida.flush();
            saida.close();
            return null;
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:getMotivosAlteracaoEndereco()", e);
            FormError formError = globalApp.buildFormError(e, request.getContextPath() + "/fidelizacao/getMotivosAlteracaoEndereco.do");
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    protected String validaSerasa(String cnpj, String ie, String uf, HttpServletRequest request) throws Exception {
        log.debug("FiscalizacaoController:validaSerasa.do - Inicio do Metodo]");

        // 000000 - SUCESSO
        // 150001 - CNPJ e IE <=========== VALIDAR
        // 150002 - CNPJ do destinat·rio inv·lido.
        // 150003 - IE do destinat·rio inv·lida.

        String retorno = "000000";
        String user = ConstantesCRM.getCurrentUser(request.getSession());
        ParametroVO url = GetParametro.getInstace().getParametroVO(user, PARAMETRO_ENDPOINT_WEBSERVICE_NFE);
        ParametroVO tipoConsulta = GetParametro.getInstace().getParametroVO(user, PARAMETRO_TIPO_CONSULTA_WEBSERVICE_NFE);

        log.debug("FiscalizacaoController:validaSerasa.do - End Point: " + url.getDsValorParametro());

        NotaFiscalEletronicaPortTypeProxy proxy = new NotaFiscalEletronicaPortTypeProxy();
        ParametrosConsultarInformacoesConfianca requestConfianca = new ParametrosConsultarInformacoesConfianca();

        try {
            proxy.setEndpoint(url.getDsValorParametro());

            log.debug("FiscalizacaoController:validaSerasa.do - Inscricao Estadual: " + ie);
            log.debug("FiscalizacaoController:validaSerasa.do - Numero Documento: " + cnpj);
            log.debug("FiscalizacaoController:validaSerasa.do - UF: " + uf);
            log.debug("FiscalizacaoController:validaSerasa.do - Tipo Consulta: " + tipoConsulta);

            requestConfianca.setInscricaoEstadual(ie);
            requestConfianca.setNumeroDocumento(cnpj);
            requestConfianca.setUF(uf);

            if (tipoConsulta.getDsValorParametro().equals(ConstantesCRM.SONE)) {
                requestConfianca.setTipoConsulta(ParametrosConsultarInformacoesConfiancaTipoConsulta.value1);
            } else if (tipoConsulta.getDsValorParametro().equals(ConstantesCRM.STWO)) {
                requestConfianca.setTipoConsulta(ParametrosConsultarInformacoesConfiancaTipoConsulta.value2);
            } else if (tipoConsulta.getDsValorParametro().equals(ConstantesCRM.STHREE)) {
                requestConfianca.setTipoConsulta(ParametrosConsultarInformacoesConfiancaTipoConsulta.value3);
            } else if (tipoConsulta.getDsValorParametro().equals(ConstantesCRM.SFOUR)) {
                requestConfianca.setTipoConsulta(ParametrosConsultarInformacoesConfiancaTipoConsulta.value4);
            }

            proxy.consultarInformacoesConfianca(requestConfianca);

        } catch (ErroInfo e) {
            log.error("FiscalizacaoController:validaSerasa.do - Erro ao Validar CNPJ/IE Serasa: " + e.getCodigo() + " - " + e.getDescricao());
            retorno = e.getCodigo();
        }
        log.debug("FiscalizacaoController:validaSerasa.do - Fim do Metodo]");
        return retorno;
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="dadosEntregaAparelho.jsp"
     * @jpf:forward name="inadimplente" path="inadimplencia.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     * @jpf:forward name="serasa" return-to="currentPage"
     */
    protected ActionForward getDadosAparelho(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("FidelizacaoController:getDadosAparelho()");
        ConsultaMatrizOfertaForm form = (ConsultaMatrizOfertaForm) formParam;
        try {
            testParameters();

            String tipoPessoa = getParametrosVO().getInTipoPessoa();
            String idTipoLinha = getParametrosVO().getIdTipoLinha();
            String nrCPF = request.getParameter("nrCPF");
            String acao = request.getParameter(ConstantesCRM.SACTION);

            // Inicio - Validacao SERASA
            try {
                String user = ConstantesCRM.getCurrentUser(request.getSession());
                TelaInicialVO telaInicialSerasa = telaInicialFac.getTelaInicial(getIdUsuario(request), getParametrosVO().getNrLinha());
                ParametroVO parametroAtivarWebService = GetParametro.getInstace().getParametroVO(user, PARAMETRO_ATIVAR_WEBSERVICE_SERASA);

                log.debug("FiscalizacaoController:getDadosAparelho.do - Validacao Externa[Ativar(1)/Desativar(0)]: " + parametroAtivarWebService.getDsParametro());

                if (parametroAtivarWebService.getDsParametro().equals(ConstantesCRM.SONE)) {

                    String retornoValidacaoSerasa = validaSerasa(telaInicialSerasa.getTIDocumento().getNrDocumento(), ConstantesCRM.SVAZIO, telaInicialSerasa.getEnderecoBaseVO().getUFVO().getSgUF(), request);

                    if (retornoValidacaoSerasa.equals("150001")) {
                        request.setAttribute("msgSERASA", "A(s) informaÁ„o(ıes) abaixo est„o inconsistentes conforme encaminhada pelo SERASA : CNPJ e InscriÁ„o Estadual. Favor alterar.");
                    } else if (retornoValidacaoSerasa.equals("150002")) {
                        request.setAttribute("msgSERASA", "A informaÁ„o abaixo est· inconsistente conforme encaminhada o SERASA: InscriÁ„o Estadual. Favor alterar.");
                    } else if (retornoValidacaoSerasa.equals("150003")) {
                        request.setAttribute("msgSERASA", "A informaÁ„o abaixo est· inconsistente conforme encaminhada pelo SERASA : CNPJ. Favor alterar.");
                    }

                    if (!retornoValidacaoSerasa.equals("000000")) {
                        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                        return mapping.findForward("serasa");
                    }
                }
            } catch (Exception e) {
                log.error("FiscalizacaoController:getDadosAparelho.do - Erro durante tentativa de validacao SERASA: " + e);
            }
            // Fim - Validacao SERASA

            /*
             * Consulta de adimplencia somente sera realizada se cliente for PF,
             * se a linha for Pos-paga (CDMA(1) ou GSM(5)) e se o meio de
             * pagamento for boleto bancario (1) ou desconto em fatura (2).
             * "ANALISEAPROVADA" -> soh prossegue se nao vier de processo ja
             * realizado de analise de adimplencia (finalizarAnaliseCredito.do)
             */
            if ("PF".equals(tipoPessoa) && !"ANALISEAPROVADA".equals(acao) && (ConstantesCRM.SONE.equals(idTipoLinha) || ConstantesCRM.SFIVE.equals(idTipoLinha) || ConstantesCRM.SFOUR.equals(idTipoLinha) || ConstantesCRM.SSEVEN.equals(idTipoLinha))
                    && (ConstantesCRM.SONE.equals(form.getMeioPagamento()) || ConstantesCRM.STWO.equals(form.getMeioPagamento()))) {

                ConsultaAdimplenciaVO inService = ConsultaAdimplenciaVODocument.ConsultaAdimplenciaVO.Factory.newInstance();
                inService.setIdClienteLegado(getParametrosVO().getIdPessoaCliente());
                inService.setDocumento(nrCPF);

                // Inicio de verificacao de adimplencia
                ConsultaAdimplenciaVO consultaAdimplenciaVO = ConsultaAdimplenciaVODocument.ConsultaAdimplenciaVO.Factory.newInstance();

                // Evita que o servico de verificacao de adimplencia seja
                // chamado novamente
                if (ConstantesCRM.SONE.equals(request.getSession().getAttribute("inAdimplente")) || request.getSession().getAttribute("idRetencao") != null) {
                    consultaAdimplenciaVO.setStatusAvaliacao(ConstantesCRM.SZERO);
                } else {
                    consultaAdimplenciaVO = retencaoRestricaoFacade.validarAdimplencia(getIdUsuario(request), inService);
                }

                getOfertaAparelhoForm().setNmContato(ConstantesCRM.SVAZIO);
                getOfertaAparelhoForm().setTelContato(ConstantesCRM.SVAZIO);
                getOfertaAparelhoForm().setRgContato(ConstantesCRM.SVAZIO);

                // Apenas se nao houve timeout no acesso ao legado e cliente
                // verificado como inadimplente
                if (ConstantesCRM.SZERO.equals(consultaAdimplenciaVO.getInTimeOutAPI()) && !ConstantesCRM.SZERO.equals(consultaAdimplenciaVO.getStatusAvaliacao())) {

                    getConsultaAdimplenciaForm().setNrDocumento(nrCPF);
                    getConsultaAdimplenciaForm().setDsHistorico(consultaAdimplenciaVO.getHistorico().replaceAll("; ", ";").replaceAll(" ;", ";").replaceAll(";", "\n"));
                    getConsultaAdimplenciaForm().setDtRequisicao(consultaAdimplenciaVO.getDataRequisicao());
                    getConsultaAdimplenciaForm().setDtResposta(consultaAdimplenciaVO.getDataResposta());
                    getConsultaAdimplenciaForm().setDtInterrupcao(consultaAdimplenciaVO.getDtInterrupcao());
                    getConsultaAdimplenciaForm().setDsMotivoErro(consultaAdimplenciaVO.getDsMotivoErro());

                    if (form.getIndex() != null) {
                        int index = (Integer.parseInt(form.getIndex()));
                        getOfertaAparelhoForm().setIdAparelho(getListaAparelhosVO().getItemOfertaAparelhoVOArray(index).getIdMatrizAparelho());
                        getOfertaAparelhoForm().setIdAparelhoCor(getListaAparelhosVO().getItemOfertaAparelhoVOArray(index).getIdAparelhoCor());
                        getOfertaAparelhoForm().setCor(getListaAparelhosVO().getItemOfertaAparelhoVOArray(index).getCor());
                        getOfertaAparelhoForm().setSAP(getListaAparelhosVO().getItemOfertaAparelhoVOArray(index).getSAP());
                        getOfertaAparelhoForm().setModelo(getListaAparelhosVO().getItemOfertaAparelhoVOArray(index).getModelo());
                    }

                    switch (Integer.parseInt(consultaAdimplenciaVO.getStatusAvaliacao())) {
                        case 1:
                            getConsultaAdimplenciaForm().setStatusAvaliacao("An·lise de crÈdito suspensa");
                            break;
                        case 2:
                            getConsultaAdimplenciaForm().setStatusAvaliacao("CrÈdito ruim");
                            break;
                    }
                    request.setAttribute("dsMeioPagamento", request.getParameter("dsMeioPagamento"));
                    request.setAttribute("actionForm", getOfertaAparelhoForm());
                    request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                    return mapping.findForward("inadimplente");

                } else if (ConstantesCRM.SONE.equals(consultaAdimplenciaVO.getInTimeOutAPI())) {
                    getConsultaAdimplenciaForm().setDtInterrupcao(consultaAdimplenciaVO.getDtInterrupcao());
                } else {
                    request.getSession().setAttribute("inAdimplente", ConstantesCRM.SONE);
                }
            }

            // Caso tenha vindo de aprovacao de analise de credito
            if ("ANALISEAPROVADA".equals(acao)) {

                request.getSession().setAttribute("inAdimplente", ConstantesCRM.SONE);
                request.getSession().removeAttribute("inNovaOferta");
                request.getSession().setAttribute("sgOferta", request.getParameter("sgOferta"));
                getOfertaAparelhoForm().setRegionais(ofertaAparelhoFac.getPesquisaEnderecoIni(getIdUsuario(request)).getUFVOArray());

                ListaDetalheLinhaVO listaDetalheLinhaVO = retencaoFAC.getLinha(getIdUsuario(request), getParametrosVO().getIdPessoaCliente(), request.getParameter("nrLinha"), false, ConstantesCRM.SVAZIO);
                DetalheLinhaVODocument.DetalheLinhaVO detalheLinha = listaDetalheLinhaVO.getDetalheLinhaVOArray(0);
                getDadosClienteForm().setDetalheLinha(DetalheLinhaVODocument.DetalheLinhaVO.Factory.newInstance());
                getDadosClienteForm().getDetalheLinha().setNumero(detalheLinha.getNumero());
                getDadosClienteForm().getDetalheLinha().setSegmentacao(detalheLinha.getSegmentacao());
                getDadosClienteForm().getDetalheLinha().setRentabilidade(detalheLinha.getRentabilidade());
                getDadosClienteForm().getDetalheLinha().setContrato(detalheLinha.getContrato());
                getDadosClienteForm().getDetalheLinha().setValorMulta(detalheLinha.getValorMulta());
                getDadosClienteForm().getDetalheLinha().setDtFimContrato(detalheLinha.getDtFimContrato());
                getDadosClienteForm().getDetalheLinha().setPlano(detalheLinha.getPlano());
                getDadosClienteForm().getDetalheLinha().setDtHabilitacao(detalheLinha.getDtHabilitacao());
                getDadosClienteForm().getDetalheLinha().setIdLinha(detalheLinha.getIdLinha());
                getDadosClienteForm().getDetalheLinha().setIdPessoaDePara(detalheLinha.getIdPessoaDePara());

                getDadosClienteForm().setIdIntencaoCancelamento(request.getParameter("idIntencao"));
                getDadosClienteForm().setIdDestinoPrevisto(request.getParameter("idDestino"));

                carregarDadosClienteForm(request);

                getOfertaAparelhoForm().setIdAparelho(request.getParameter("idAparelho"));
                getOfertaAparelhoForm().setIdAparelhoCor(request.getParameter("idAparelhoCor"));
                getOfertaAparelhoForm().setModelo(getConsultaAdimplenciaForm().getDsModelo());
                getOfertaAparelhoForm().setCor(getConsultaAdimplenciaForm().getNmCor());
                getOfertaAparelhoForm().setPreco(getConsultaAdimplenciaForm().getVlPreco());
                getOfertaAparelhoForm().setSAP(request.getParameter("dsMaterial"));

                getOfertaAparelhoForm().setDescontoSelecionado(getConsultaAdimplenciaForm().getVlPercentualDesconto());
                getOfertaAparelhoForm().setVlDesconto(getConsultaAdimplenciaForm().getVlPrecoComDesconto());
                getOfertaAparelhoForm().setParcelaSelecionada(getConsultaAdimplenciaForm().getNrParcelas());
                getOfertaAparelhoForm().setVlParcela(getConsultaAdimplenciaForm().getVlParcela());
                getOfertaAparelhoForm().setIdMeioPagamento(request.getParameter("idTipoPagamento"));
                getOfertaAparelhoForm().setMeioPagamento(request.getParameter("dsMeioPagamento"));
                getOfertaAparelhoForm().setInExcecao(getOfertaAparelhoForm().getInExcecao());

                getOfertaAparelhoForm().setDsMaterial(request.getParameter("dsMaterial"));
                getOfertaAparelhoForm().setSAP(request.getParameter("dsMaterial"));

                getConsultaMatrizOfertaForm().setIdRetencao(form.getIdRetencao());
                getConsultaMatrizOfertaForm().setPercDesconto(getConsultaAdimplenciaForm().getVlPercentualDesconto());
                getConsultaMatrizOfertaForm().setVlDesconto(getConsultaAdimplenciaForm().getVlPrecoComDesconto());
                getConsultaMatrizOfertaForm().setNroParcela(getConsultaAdimplenciaForm().getNrParcelas());
                getConsultaMatrizOfertaForm().setVlParcela(getConsultaAdimplenciaForm().getVlParcela());
                getConsultaMatrizOfertaForm().setIdMeioPagamento(request.getParameter("idTipoPagamento"));
                getConsultaMatrizOfertaForm().setMeioPagamento(request.getParameter("dsMeioPagamento"));
                getConsultaMatrizOfertaForm().setInExcecao(getOfertaAparelhoForm().getInExcecao());

                getOfertaAparelhoForm().setNmContato(ConstantesCRM.SVAZIO);
                getOfertaAparelhoForm().setTelContato(ConstantesCRM.SVAZIO);
                getOfertaAparelhoForm().setRgContato(ConstantesCRM.SVAZIO);

                request.getSession().setAttribute(ConstantesCRM.SACTION, "analiseCreditoAprovada_Prosseguir");
                request.getSession().setAttribute("idRetencao", request.getParameter("idRetencao"));
                request.getSession().setAttribute("idRetencaoOld", request.getParameter("idRetencao"));
                request.getSession().setAttribute("dsIntencao", request.getParameter("dsIntencao"));
                request.getSession().setAttribute("dsDestinoPrevisto", request.getParameter("dsDestinoPrevisto"));
            } // Procedimento normal
            else {

                if (form.getIndex() != null && !ConstantesCRM.SVAZIO.equals(form.getIndex())) {
                    int index = (Integer.parseInt(form.getIndex()));
                    getOfertaAparelhoForm().setIdAparelho(getListaAparelhosVO().getItemOfertaAparelhoVOArray(index).getIdMatrizAparelho());
                    getOfertaAparelhoForm().setIdAparelhoCor(getListaAparelhosVO().getItemOfertaAparelhoVOArray(index).getIdAparelhoCor());
                    getOfertaAparelhoForm().setModelo(getListaAparelhosVO().getItemOfertaAparelhoVOArray(index).getModelo());
                    getOfertaAparelhoForm().setCor(getListaAparelhosVO().getItemOfertaAparelhoVOArray(index).getCor());
                    getOfertaAparelhoForm().setPreco(getListaAparelhosVO().getItemOfertaAparelhoVOArray(index).getPreco());
                    getOfertaAparelhoForm().setSAP(getListaAparelhosVO().getItemOfertaAparelhoVOArray(index).getSAP());
                }

                getOfertaAparelhoForm().setRegionais(ofertaAparelhoFac.getPesquisaEnderecoIni(getIdUsuario(request)).getUFVOArray());

                if (request.getParameter("meioPgto") != null && request.getParameter("meioPgto").equals(ConstantesCRM.STHREE)) {
                    form.setMeioPagamento(request.getParameter("meioPgto"));
                    form.setPercDesconto("100");
                    form.setVlDesconto(ConstantesCRM.SZERO);
                    form.setNroParcela(ConstantesCRM.SZERO);
                    form.setVlParcela(ConstantesCRM.SZERO);
                }

                getOfertaAparelhoForm().setIndex(form.getIndex());
                getOfertaAparelhoForm().setDescontoSelecionado(form.getPercDesconto());
                getOfertaAparelhoForm().setVlDesconto(form.getVlDesconto());
                getOfertaAparelhoForm().setParcelaSelecionada(form.getNroParcela());
                getOfertaAparelhoForm().setVlParcela(form.getVlParcela());
                getOfertaAparelhoForm().setIdMeioPagamento(form.getMeioPagamento());
                getOfertaAparelhoForm().setMeioPagamento(getDescMeioPagamento(form.getMeioPagamento()));
                getOfertaAparelhoForm().setPrzVigencia(form.getPrzVigencia());
                getOfertaAparelhoForm().setInExcecao(form.getInExcecao());

                getConsultaMatrizOfertaForm().setIdRetencao(form.getIdRetencao());
                getConsultaMatrizOfertaForm().setIndex(form.getIndex());
                getConsultaMatrizOfertaForm().setPercDesconto(form.getPercDesconto());
                getConsultaMatrizOfertaForm().setVlDesconto(form.getVlDesconto());
                getConsultaMatrizOfertaForm().setNroParcela(form.getNroParcela());
                getConsultaMatrizOfertaForm().setVlParcela(form.getVlParcela());
                getConsultaMatrizOfertaForm().setIdMeioPagamento(form.getMeioPagamento());
                getConsultaMatrizOfertaForm().setMeioPagamento(form.getMeioPagamento());
                getConsultaMatrizOfertaForm().setPrzVigencia(form.getPrzVigencia());
                getConsultaMatrizOfertaForm().setInExcecao(form.getInExcecao());
            }

            TelaInicialVO telaInicial = telaInicialFac.getTelaInicial(getIdUsuario(request), getDadosClienteForm().getNumeroLinha());

            getOfertaAparelhoForm().setRua(removeCaracteresEspeciais(telaInicial.getEnderecoBaseVO().getDsEndereco()).trim());
            getOfertaAparelhoForm().setBairro(removeCaracteresEspeciais(telaInicial.getEnderecoBaseVO().getDsBairro()).trim());
            getOfertaAparelhoForm().setComplemento(removeCaracteresEspeciais(telaInicial.getEnderecoBaseVO().getDsComplemento()).trim());
            getOfertaAparelhoForm().setNumero(telaInicial.getEnderecoBaseVO().getNrEndereco().trim());
            getOfertaAparelhoForm().setCidade(removeCaracteresEspeciais(telaInicial.getEnderecoBaseVO().getDsCidade()).trim());
            getOfertaAparelhoForm().setCep(formatarCep(telaInicial.getEnderecoBaseVO().getNrCEP()));
            getOfertaAparelhoForm().setIdUFSelecionado(telaInicial.getEnderecoBaseVO().getUFVO().getSgUF());
            getOfertaAparelhoForm().setEnderecoVO(EnderecoVO.Factory.newInstance());
            getOfertaAparelhoForm().setInTipoPessoa(getParametrosVO().getInTipoPessoa());

        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:getDadosAparelho()", e);
            FormError formError = globalApp.buildFormError(e, request.getContextPath() + "/fidelizacao/getLinhas.do");
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }

        request.setAttribute("actionForm", getOfertaAparelhoForm());
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="mensagemInadimplencia.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward reterPorRestricao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

        try {
            // consultaMatrizOfertaForm = (ConsultaMatrizOfertaForm)
            // this.getPreviousActionInfo().getForm();

            FinalizaRetencaoVO finalizaRetencaoVO = getFinalizaRetencaoComDadosGerais(request);
            finalizaRetencaoVO.getCliente().setNrDocumento(request.getParameter("nrCPF"));
            finalizaRetencaoVO.getRetencao().getRetencaoHeader().setInExcecao(getConsultaMatrizOfertaForm().getInExcecao() ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
            finalizaRetencaoVO.getRetencao().getRetencaoHeader().setIdTipoEncerramento(IDRETIDOPORRESTRICAO);

            finalizaRetencaoVO.getRetencao().getRetencaoBody().addNewOfertas().addNewAparelhos();
            Aparelhos aparelhos = finalizaRetencaoVO.getRetencao().getRetencaoBody().getOfertas().getAparelhos();

            aparelhos.setNmModeloAparelho(getOfertaAparelhoForm().getModelo());
            aparelhos.setNmCor(getOfertaAparelhoForm().getCor());
            aparelhos.setIdTipoPagamentoAparelho(consultaMatrizOfertaForm.getMeioPagamento());
            aparelhos.setVlPrecoReal(consultaMatrizOfertaForm.getVlCalcularDesconto() != null ? formatarDecimal(consultaMatrizOfertaForm.getVlCalcularDesconto(), 1) : ConstantesCRM.SZERO);
            aparelhos.setVlPrecoAbs(formatarDecimal(consultaMatrizOfertaForm.getVlDesconto(), 1));
            aparelhos.setNrParcelas(consultaMatrizOfertaForm.getNroParcela());
            aparelhos.setVlPercentualDesconto(consultaMatrizOfertaForm.getPercDesconto());
            aparelhos.setVlParcela(formatarDecimal(consultaMatrizOfertaForm.getVlParcela(), 1));
            aparelhos.setInTipoEntrega(ConstantesCRM.SZERO);
            aparelhos.setIdMatrizAparelho(getOfertaAparelhoForm().getIdAparelho());

            aparelhos.setCdSapAparelho(getOfertaAparelhoForm().getSAP());

            finalizaRetencaoVO.getRetencao().getRetencaoBody().addNewAnalise().addNewAdimplencia();
            finalizaRetencaoVO.getRetencao().getRetencaoBody().getAnalise().setIdTipoAnalise(ConstantesCRM.SSIX);
            Adimplencia adimplencia = finalizaRetencaoVO.getRetencao().getRetencaoBody().getAnalise().getAdimplencia();

            adimplencia.setInStatusComunicacao(getConsultaAdimplenciaForm().getStatusAvaliacao());
            adimplencia.setInAprovadoLegado(ConstantesCRM.SZERO.equals(getConsultaAdimplenciaForm().getStatusAvaliacao()) ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
            adimplencia.setDsRetornoConsulta(getConsultaAdimplenciaForm().getDsHistorico());
            adimplencia.setNrDocumento(getConsultaAdimplenciaForm().getNrDocumento());
            adimplencia.setDsMotivoErro(getConsultaAdimplenciaForm().getDsMotivoErro());
            adimplencia.setDtInterrupcao(getConsultaAdimplenciaForm().getDtInterrupcao());
            adimplencia.setDsMotivoErro(getConsultaAdimplenciaForm().getDsMotivoErro());

            RetornoVO retorno = ofertaRealizadaFac.finalizaRetencao(getIdUsuario(request), finalizaRetencaoVO);
            /*
             * Verifica se ser· necess·ria atualizaÁ„o de contato do cliente
             * (mais de 30 dias sem atualizaÁ„o)
             */
            if (retorno.getFidelizacaoContatoVO() != null && retorno.getFidelizacaoContatoVO().getIdPessoa() != null) {
                request.setAttribute("inAtualizacaoContato", ConstantesCRM.SONE);
                request.setAttribute("dsContato", retorno.getFidelizacaoContatoVO().getDsContato());
                request.setAttribute("idPessoa", retorno.getFidelizacaoContatoVO().getIdPessoa());
            }

            request.getSession().removeAttribute("sgOferta");
            request.getSession().removeAttribute("dsIntencao");
            request.getSession().removeAttribute("dsDestinoPrevisto");
            request.setAttribute("msgRestricao", "RetenÁ„o fechada por inadimplÍncia\\nN∫ de Protocolo: " + retorno.getNrProtocolo());
            request.setAttribute("nrLinha", getParametrosVO().getNrLinha());

            getConsultaMatrizOfertaForm().setIdRetencao(retorno.getValor());
            request.setAttribute("idRetencao", retorno.getValor());

            String nrRetProtocolo = retorno.getNrProtocolo() != null ? retorno.getNrProtocolo() : ConstantesCRM.SVAZIO;
            if (!getNrProtocolo().equals(nrRetProtocolo)) {
                String msgProtocolo = "Foi gerado para a linha " + ConstantesCRM.formatPhoneNumber(finalizaRetencaoVO.getCliente().getNrLinha()) + " o n˙mero de protocolo " + nrRetProtocolo + " referente ‡ aÁ„o RETER.";
                request.setAttribute("nrProtocolo", msgProtocolo);
                request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, nrRetProtocolo);
            }

            request.setAttribute("actionForm", getOfertaAparelhoForm());
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);

        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:reterPorRestricao()", e);
            FormError formError = globalApp.buildFormError(e, request.getContextPath() + "/fidelizacao/reterPorRestricao.do");
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    /*
     * private void setDadosAdimplencia(RetencaoFinalizarVO finalizarVO) {
     * finalizarVO.addNewDadosAdimplencia();
     * finalizarVO.getDadosAdimplencia().setInStatusComunicacao
     * (getConsultaAdimplenciaForm().getStatusAvaliacao()); finalizarVO
     * .getDadosAdimplencia
     * ().setInAprovadoLegado(ConstantesCRM.SZERO.equals(getConsultaAdimplenciaForm
     * ().getStatusAvaliacao ())?ConstantesCRM.SONE:ConstantesCRM.SZERO);
     * finalizarVO
     * .getDadosAdimplencia().setDsRetornoConsulta(getConsultaAdimplenciaForm
     * ().getDsHistorico());
     * finalizarVO.getDadosAdimplencia().setNrDocumento(getConsultaAdimplenciaForm
     * ().getNrDocumento());
     * finalizarVO.getDadosAdimplencia().setDsMotivoErro(getConsultaAdimplenciaForm
     * ().getDsMotivoErro());
     * finalizarVO.getDadosAdimplencia().setDtInterrupcao(
     * getConsultaAdimplenciaForm().getDtInterrupcao());
     * finalizarVO.getDadosAdimplencia
     * ().setDsMotivoErro(getConsultaAdimplenciaForm().getDsMotivoErro()); }
     */
    /**
     * Processo de retencao eh encaminhado para analise de credito por analista
     * BKO.
     * 
     * @jpf:action
     * @jpf:forward name="success" path="mensagemInadimplencia.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward encaminhar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        try {
            RetencaoSAPVO retensaoSAPVO = RetencaoSAPVODocument.RetencaoSAPVO.Factory.newInstance();
            TelaInicialVO telaInicial = telaInicialFac.getTelaInicial(getIdUsuario(request), getParametrosVO().getNrLinha());

            // consultaMatrizOfertaForm = (ConsultaMatrizOfertaForm)
            // this.getPreviousActionInfo().getForm();

            // inExecutar 0-Procedimento normal; 1->Baixa; 2-Estorno;
            retensaoSAPVO.setInExecutar(ConstantesCRM.SONE);

            // inRetornoOld 0-Procedimento normal; 1->Estoque ja realizado
            retensaoSAPVO.setInRetornoOld(ConstantesCRM.SZERO);

            retensaoSAPVO.addNewCLIENTE();
            retensaoSAPVO.getCLIENTE().setNOME(getParametrosVO().getNmCliente());
            retensaoSAPVO.getCLIENTE().setDOCUMENTO(request.getParameter("nrCPF").toString());
            retensaoSAPVO.getCLIENTE().setTELEFONE(getDadosClienteForm().getNumeroLinha());
            retensaoSAPVO.getCLIENTE().setIDPESSOA(getDadosClienteForm().getDetalheLinha().getIdPessoaDePara());
            retensaoSAPVO.getCLIENTE().setIDGRUPO(getParametrosVO().getIdGrupo());
            retensaoSAPVO.getCLIENTE().setIDSEGMENTACAO(getDadosClienteForm().getDetalheLinha().getIdSegmentacao());

            retensaoSAPVO.addNewENDERECO();
            retensaoSAPVO.getENDERECO().setESTADOCLI(telaInicial.getEnderecoBaseVO().getUFVO().getSgUF());
            retensaoSAPVO.getENDERECO().setRUACLI(telaInicial.getEnderecoBaseVO().getDsEndereco() + ", " + telaInicial.getEnderecoBaseVO().getNrEndereco() + " - " + telaInicial.getEnderecoBaseVO().getDsComplemento());
            retensaoSAPVO.getENDERECO().setBAIRROCLI(telaInicial.getEnderecoBaseVO().getDsBairro());
            retensaoSAPVO.getENDERECO().setCIDADECLI(telaInicial.getEnderecoBaseVO().getDsCidade());
            retensaoSAPVO.getENDERECO().setCEPCLI(telaInicial.getEnderecoBaseVO().getNrCEP());

            retensaoSAPVO.addNewPRODUTO();
            retensaoSAPVO.getPRODUTO().setDATAREMESSA(new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date()));
            retensaoSAPVO.getPRODUTO().setIDAPARELHO(getOfertaAparelhoForm().getIdAparelho());
            retensaoSAPVO.getPRODUTO().setVALOR(formatarDecimal(consultaMatrizOfertaForm.getVlDesconto(), 1));
            retensaoSAPVO.getPRODUTO().setMATERIAL(getOfertaAparelhoForm().getSAP());
            retensaoSAPVO.getPRODUTO().setQUANTIDADE(ConstantesCRM.SONE);
            retensaoSAPVO.getPRODUTO().setMEIOPAGTO(consultaMatrizOfertaForm.getMeioPagamento());
            retensaoSAPVO.getPRODUTO().setCONDPGTO(consultaMatrizOfertaForm.getNroParcela());

            String xmlText = removeAcentuacaoXML(retensaoSAPVO.xmlText(), "RetencaoSAPVO", "fidelizacao.fo.vivo.com.br/vo");
            retensaoSAPVO = RetencaoSAPVODocument.Factory.parse(xmlText).getRetencaoSAPVO();

            retencaoRestricaoFacade.executarBaixa(getIdUsuario(request), retensaoSAPVO);
            // consultaMatrizOfertaForm = (ConsultaMatrizOfertaForm)
            // this.getPreviousActionInfo().getForm();

            FinalizaRetencaoVO finalizaRetencaoVO = getFinalizaRetencaoComDadosGerais(request);
            finalizaRetencaoVO.getCliente().setNrDocumento(request.getParameter("nrCPF"));
            finalizaRetencaoVO.getRetencao().getRetencaoHeader().setInExcecao(getConsultaMatrizOfertaForm().getInExcecao() ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
            finalizaRetencaoVO.getRetencao().getRetencaoHeader().setIdTipoEncerramento(IDRETIDOPARAANALISE);

            finalizaRetencaoVO.getRetencao().getRetencaoBody().addNewOfertas().addNewAparelhos();
            Aparelhos aparelhos = finalizaRetencaoVO.getRetencao().getRetencaoBody().getOfertas().getAparelhos();

            aparelhos.setNmModeloAparelho(getOfertaAparelhoForm().getModelo());
            aparelhos.setInTipoEntrega(ConstantesCRM.SZERO);
            aparelhos.setNmCor(getOfertaAparelhoForm().getCor());
            aparelhos.setIdTipoPagamentoAparelho(consultaMatrizOfertaForm.getMeioPagamento());
            aparelhos.setVlPrecoReal(consultaMatrizOfertaForm.getVlCalcularDesconto() != null ? formatarDecimal(consultaMatrizOfertaForm.getVlCalcularDesconto(), 1) : ConstantesCRM.SZERO);
            aparelhos.setVlPrecoAbs(formatarDecimal(consultaMatrizOfertaForm.getVlDesconto(), 1));
            aparelhos.setNrParcelas(consultaMatrizOfertaForm.getNroParcela());
            aparelhos.setVlPercentualDesconto(consultaMatrizOfertaForm.getPercDesconto());
            aparelhos.setVlParcela(formatarDecimal(consultaMatrizOfertaForm.getVlParcela(), 1));
            aparelhos.setIdMatrizAparelho(getOfertaAparelhoForm().getIdAparelho());
            aparelhos.setCdSapAparelho(getOfertaAparelhoForm().getSAP());

            finalizaRetencaoVO.getRetencao().getRetencaoBody().addNewAnalise().addNewAdimplencia();
            finalizaRetencaoVO.getRetencao().getRetencaoBody().getAnalise().setIdTipoAnalise(ConstantesCRM.SONE);
            Adimplencia adimplencia = finalizaRetencaoVO.getRetencao().getRetencaoBody().getAnalise().getAdimplencia();

            adimplencia.setInStatusComunicacao(getConsultaAdimplenciaForm().getStatusAvaliacao());
            adimplencia.setInAprovadoLegado(ConstantesCRM.SZERO.equals(getConsultaAdimplenciaForm().getStatusAvaliacao()) ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
            adimplencia.setDsRetornoConsulta(getConsultaAdimplenciaForm().getDsHistorico());
            adimplencia.setNrDocumento(getConsultaAdimplenciaForm().getNrDocumento());
            adimplencia.setDsMotivoErro(getConsultaAdimplenciaForm().getDsMotivoErro());
            adimplencia.setDtInterrupcao(getConsultaAdimplenciaForm().getDtInterrupcao());
            adimplencia.setDsMotivoErro(getConsultaAdimplenciaForm().getDsMotivoErro());

            RetornoVO retorno = ofertaRealizadaFac.finalizaRetencao(getIdUsuario(request), finalizaRetencaoVO);
            /*
             * Verifica se ser· necess·ria atualizaÁ„o de contato do cliente
             * (mais de 30 dias sem atualizaÁ„o)
             */
            if (retorno.getFidelizacaoContatoVO() != null && retorno.getFidelizacaoContatoVO().getIdPessoa() != null) {
                request.setAttribute("inAtualizacaoContato", ConstantesCRM.SONE);
                request.setAttribute("dsContato", retorno.getFidelizacaoContatoVO().getDsContato());
                request.setAttribute("idPessoa", retorno.getFidelizacaoContatoVO().getIdPessoa());
            }

            if ("ERRO".equals(retorno.getDescricao())) {
                request.setAttribute("msgErro", retorno.getValor());
            } else {
                request.setAttribute("nrProcesso", retorno.getValor());
            }

            String nrRetProtocolo = retorno.getNrProtocolo() != null ? retorno.getNrProtocolo() : ConstantesCRM.SVAZIO;
            if (!getNrProtocolo().equals(nrRetProtocolo)) {
                request.setAttribute("nrProtocolo", "Protocolo " + nrRetProtocolo + " gerado.");
                request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, nrRetProtocolo);
            }

            request.getSession().removeAttribute("sgOferta");
            request.getSession().removeAttribute("dsIntencao");
            request.getSession().removeAttribute("dsDestinoPrevisto");
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);

        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:encaminhar()", e);
            FormError formError = globalApp.buildFormError(e, request.getContextPath() + "/fidelizacao/encaminhar.do");
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="mensagemInadimplencia.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward encaminharAlteracaoEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        OfertaAparelhoForm form = (OfertaAparelhoForm) formParam;
        try {
            RetencaoSAPVO retensaoSAPVO = RetencaoSAPVODocument.RetencaoSAPVO.Factory.newInstance();
            // consultaMatrizOfertaForm = (ConsultaMatrizOfertaForm)
            // this.getPreviousActionInfo().getForm();
            TelaInicialVO telaInicial = telaInicialFac.getTelaInicial(getIdUsuario(request), getParametrosVO().getNrLinha());

            // inExecutar 0-Procedimento normal; 1->Baixa; 2-Estorno;
            retensaoSAPVO.setInExecutar(ConstantesCRM.SONE);

            // inRetornoOld 0-Procedimento normal; 1->Estoque ja realizado
            retensaoSAPVO.setInRetornoOld(ConstantesCRM.SZERO);

            retensaoSAPVO.addNewCLIENTE();
            retensaoSAPVO.getCLIENTE().setNOME(getParametrosVO().getNmCliente());
            retensaoSAPVO.getCLIENTE().setDOCUMENTO(request.getParameter("nrCPF").toString());
            retensaoSAPVO.getCLIENTE().setTELEFONE(getDadosClienteForm().getNumeroLinha());
            retensaoSAPVO.getCLIENTE().setIDPESSOA(getParametrosVO().getIdPessoaCliente());
            retensaoSAPVO.getCLIENTE().setIDGRUPO(getParametrosVO().getIdGrupo());
            retensaoSAPVO.getCLIENTE().setIDSEGMENTACAO(getParametrosVO().getIdSegmentacao());

            retensaoSAPVO.addNewENDERECO();
            retensaoSAPVO.getENDERECO().setESTADOCLI(telaInicial.getEnderecoBaseVO().getUFVO().getSgUF());
            retensaoSAPVO.getENDERECO().setRUACLI(telaInicial.getEnderecoBaseVO().getDsEndereco() + ", " + telaInicial.getEnderecoBaseVO().getNrEndereco() + " - " + telaInicial.getEnderecoBaseVO().getDsComplemento());
            retensaoSAPVO.getENDERECO().setBAIRROCLI(telaInicial.getEnderecoBaseVO().getDsBairro());
            retensaoSAPVO.getENDERECO().setCIDADECLI(telaInicial.getEnderecoBaseVO().getDsCidade());
            retensaoSAPVO.getENDERECO().setCEPCLI(telaInicial.getEnderecoBaseVO().getNrCEP());

            retensaoSAPVO.getENDERECO().setESTADOENT(form.getIdUFSelecionado());
            retensaoSAPVO.getENDERECO().setRUAENT(form.getRua() + ", " + form.getNumero() + " - " + form.getComplemento());
            retensaoSAPVO.getENDERECO().setBAIRROENT(form.getBairro());
            retensaoSAPVO.getENDERECO().setCIDADEENT(form.getCidade());
            retensaoSAPVO.getENDERECO().setCEPENT(form.getCep());

            retensaoSAPVO.addNewPRODUTO();
            retensaoSAPVO.getPRODUTO().setDATAREMESSA(new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date()));
            retensaoSAPVO.getPRODUTO().setIDAPARELHO(getOfertaAparelhoForm().getIdAparelho());
            retensaoSAPVO.getPRODUTO().setVALOR(formatarDecimal(form.getVlDesconto(), 1));
            retensaoSAPVO.getPRODUTO().setMATERIAL(getOfertaAparelhoForm().getSAP());
            retensaoSAPVO.getPRODUTO().setQUANTIDADE(ConstantesCRM.SONE);
            retensaoSAPVO.getPRODUTO().setMEIOPAGTO(form.getMeioPagamento());
            retensaoSAPVO.getPRODUTO().setCONDPGTO(form.getParcelaSelecionada());

            String xmlText = removeAcentuacaoXML(retensaoSAPVO.xmlText(), "RetencaoSAPVO", "fidelizacao.fo.vivo.com.br/vo");
            retensaoSAPVO = RetencaoSAPVODocument.Factory.parse(xmlText).getRetencaoSAPVO();

            retencaoRestricaoFacade.executarBaixa(getIdUsuario(request), retensaoSAPVO);

            String stEndereco = new StringBuffer(form.getRua()).append(";").append(form.getNumero()).append(";").append(form.getComplemento()).append(";").append(form.getBairro()).append(";").append(form.getCep()).append(";")
                    .append(form.getCidade()).append(";").append(form.getIdUFSelecionado()).toString();

            FinalizaRetencaoVO finalizaRetencaoVO = getFinalizaRetencaoComDadosGerais(request);

            if (request.getSession().getAttribute("idRetencaoOld") != null) {
                if (request.getSession().getAttribute("inNovaOferta") != null) {
                    finalizaRetencaoVO.getRetencao().getRetencaoHeader().setInNovaOferta(request.getSession().getAttribute("inNovaOferta").toString());
                }
            }

            finalizaRetencaoVO.getCliente().setNrDocumento(request.getParameter("nrCPF"));
            finalizaRetencaoVO.getRetencao().getRetencaoHeader().setInExcecao(form.getInExcecao() ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
            finalizaRetencaoVO.getRetencao().getRetencaoHeader().setIdTipoEncerramento(IDENCAMINHARANALISEENDERECO);

            finalizaRetencaoVO.getRetencao().getRetencaoBody().addNewOfertas().addNewAparelhos();
            Aparelhos aparelhos = finalizaRetencaoVO.getRetencao().getRetencaoBody().getOfertas().getAparelhos();

            aparelhos.setNmModeloAparelho(request.getParameter("{actionForm.modelo}"));
            aparelhos.setInTipoEntrega(ConstantesCRM.SFOUR);
            aparelhos.setNmCor(form.getCor());
            aparelhos.setIdTipoPagamentoAparelho(form.getMeioPagamento());
            aparelhos.setVlPrecoReal(form.getPreco() != null ? formatarDecimal(form.getPreco(), 1) : ConstantesCRM.SZERO);
            aparelhos.setVlPrecoAbs(formatarDecimal(form.getVlDesconto(), 1));
            aparelhos.setNrParcelas(form.getParcelaSelecionada());
            aparelhos.setVlPercentualDesconto(form.getDescontoSelecionado());
            aparelhos.setVlParcela(formatarDecimal(form.getVlParcela(), 1));
            aparelhos.setIdMatrizAparelho(getOfertaAparelhoForm().getIdAparelho());
            aparelhos.setCdSapAparelho(getOfertaAparelhoForm().getSAP());
            aparelhos.setDsEnderecoEntrega(stEndereco);
            aparelhos.setNmTerceiro(form.getNmContato());
            aparelhos.setNrContatoTelefone(form.getTelContato());
            aparelhos.setDsDocumentoTerceiro(form.getRgContato());

            finalizaRetencaoVO.getRetencao().getRetencaoBody().addNewAnalise().addNewAdimplencia();
            finalizaRetencaoVO.getRetencao().getRetencaoBody().getAnalise().setIdTipoAnalise(ConstantesCRM.SONE);
            Adimplencia adimplencia = finalizaRetencaoVO.getRetencao().getRetencaoBody().getAnalise().getAdimplencia();

            adimplencia.setInStatusComunicacao(getConsultaAdimplenciaForm().getStatusAvaliacao());
            adimplencia.setInAprovadoLegado(ConstantesCRM.SZERO.equals(getConsultaAdimplenciaForm().getStatusAvaliacao()) ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
            adimplencia.setDsRetornoConsulta(getConsultaAdimplenciaForm().getDsHistorico());
            adimplencia.setNrDocumento(getConsultaAdimplenciaForm().getNrDocumento());
            adimplencia.setDsMotivoErro(getConsultaAdimplenciaForm().getDsMotivoErro());
            adimplencia.setDtInterrupcao(getConsultaAdimplenciaForm().getDtInterrupcao());
            adimplencia.setDsMotivoErro(getConsultaAdimplenciaForm().getDsMotivoErro());

            finalizaRetencaoVO.getRetencao().getRetencaoBody().getAnalise().addNewEndereco();
            Endereco endereco = finalizaRetencaoVO.getRetencao().getRetencaoBody().getAnalise().getEndereco();

            endereco.setIdMotivoAlteracaoEndereco(request.getParameter("idMotivoAlteracaoEndereco"));
            endereco.setDsEndereco(form.getRua());
            endereco.setNrEndereco(form.getNumero());
            endereco.setDsComplemento(limitaCaracteres(form.getComplemento(), 20));
            endereco.setDsBairro(form.getBairro());
            endereco.setDsCidade(form.getCidade());
            endereco.setDsUF(form.getIdUFSelecionado());
            endereco.setDsCEP(form.getCep());

            RetornoVO retorno = ofertaRealizadaFac.finalizaRetencao(getIdUsuario(request), finalizaRetencaoVO);
            /*
             * Verifica se ser· necess·ria atualizaÁ„o de contato do cliente
             * (mais de 30 dias sem atualizaÁ„o)
             */
            if (retorno.getFidelizacaoContatoVO() != null && retorno.getFidelizacaoContatoVO().getIdPessoa() != null) {
                request.setAttribute("inAtualizacaoContato", ConstantesCRM.SONE);
                request.setAttribute("dsContato", retorno.getFidelizacaoContatoVO().getDsContato());
                request.setAttribute("idPessoa", retorno.getFidelizacaoContatoVO().getIdPessoa());
            }

            request.setAttribute("nrProcesso", retorno.getValor());

            String nrRetProtocolo = retorno.getNrProtocolo() != null ? retorno.getNrProtocolo() : ConstantesCRM.SVAZIO;
            if (!getNrProtocolo().equals(nrRetProtocolo)) {
                request.setAttribute("nrProtocolo", "Protocolo " + nrRetProtocolo + " gerado.");
                request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, nrRetProtocolo);
            }

            request.getSession().removeAttribute("sgOferta");
            request.getSession().removeAttribute("dsIntencao");
            request.getSession().removeAttribute("dsDestinoPrevisto");
            request.getSession().removeAttribute("inNovaOferta");
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);

        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:encaminharAlteracaoEndereco()", e);
            FormError formError = globalApp.buildFormError(e, request.getContextPath() + "/fidelizacao/encaminharAlteracaoEndereco.do");
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="getLinhas.do"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward finalizarRetencao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        log.debug("FidelizacaoController:finalizarRetencao()");
        ConsultaMatrizOfertaForm form = (ConsultaMatrizOfertaForm) formParam;
        try {
            testParameters();

            MensajeEncerraVO mensajeEncerraVO = MensajeEncerraVO.Factory.newInstance();
            mensajeEncerraVO.setIdRetencao(form.getIdRetencao());
            mensajeEncerraVO.setDsObservacao(form.getObservacao());

            if (null != request.getParameter("dsContato") && !ConstantesCRM.SVAZIO.equals(request.getParameter("dsContato"))) {
                mensajeEncerraVO.addNewFidelizacaoContatoVO().setDsContato(request.getParameter("dsContato"));
                mensajeEncerraVO.getFidelizacaoContatoVO().setIdPessoa(request.getParameter("idPessoa"));
            }

            ofertaRealizadaFac.setObservacao(getIdUsuario(request), mensajeEncerraVO);

            setListaOfertasDisponiveisVO(FidelizacaoListaGeralDescricaoVO.Factory.newInstance());
            setListaOfertasRealizadasVO(FidelizacaoListaGeralDescricaoVO.Factory.newInstance());
            setListaOfertasAceitasVO(FidelizacaoListaGeralDescricaoVO.Factory.newInstance());

            setConsultaMatrizOfertaForm(new ConsultaMatrizOfertaForm());
            setCadastrarMigracaoForm(new CadastrarMigracaoForm());
            setOfertaAparelhoForm(new OfertaAparelhoForm());
            setSuspensaoTemporariaForm(new SuspensaoTemporariaForm());

            getDadosClienteForm().setIdIntencaoCancelamento(ConstantesCRM.SVAZIO);
            getDadosClienteForm().setIdDestinoPrevisto(ConstantesCRM.SVAZIO);

            request.getSession().removeAttribute("retencao_ActionRetencaoForm");
            request.getSession().removeAttribute("retencao_DadosClienteForm");

        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:finalizarRetencao()", e);
            FormError formError = globalApp.buildFormError(e, request.getContextPath() + "/fidelizacao/finalizarRetencao.do");
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="ofertaAparelho.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward getPercDescontoParcelas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        log.debug("FidelizacaoController:getPercDescontoParcelas()");
        ConsultaMatrizOfertaForm form = (ConsultaMatrizOfertaForm) formParam;
        try {
            testParameters();
            // verifica se tem algum aparelho selecionado. Se sim faz a busca pelas parcelas e descontos
            if (form.getIndex() != null && !ConstantesCRM.SVAZIO.equals(form.getIndex())) {
                getConsultaMatrizOfertaForm().setVlParcela(ConstantesCRM.SVAZIO);
                getConsultaMatrizOfertaForm().setNroParcela(ConstantesCRM.SVAZIO);
                getConsultaMatrizOfertaForm().setPercDesconto(ConstantesCRM.SVAZIO);
                getConsultaMatrizOfertaForm().setIndex(form.getIndex());
                getConsultaMatrizOfertaForm().setInExcecao(form.getInExcecao());
                if (!this.COMODATO.equals(getConsultaMatrizOfertaForm().getSgOfertaAceita())) {
                    getConsultaMatrizOfertaForm().setVlDesconto(getListaAparelhosVO().getItemOfertaAparelhoVOArray(Integer.parseInt(form.getIndex())).getPreco());
                    carregarPercDesconto(getListaAparelhosVO().getItemOfertaAparelhoVOArray(Integer.parseInt(form.getIndex())).getIdMatrizAparelho(), form.getInExcecao() ? ConstantesCRM.SONE : ConstantesCRM.SZERO, request);
                    carregarParcelas(getListaAparelhosVO().getItemOfertaAparelhoVOArray(Integer.parseInt(form.getIndex())).getIdMatrizAparelho(), form.getInExcecao() ? ConstantesCRM.SZERO : ConstantesCRM.SONE, request);
                    getConsultaMatrizOfertaForm().setVlCalcularDesconto(getConsultaMatrizOfertaForm().getVlDesconto());
                }
            }

        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:getPercDescontoParcelas()", e);
            FormError formError = globalApp.buildFormError(e, request.getContextPath() + "/fidelizacao/getPercDescontoParcelas.do");
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="mensagemEncerramento.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward cancelarAtendimento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RetornoVO retorno = null;
        boolean finaliza = false;
        String intencao = ConstantesCRM.SVAZIO;
        String idUfOperadora = ConstantesCRM.SVAZIO;
        String destino = ConstantesCRM.SVAZIO;
        String msgErro = "Dados n„o suficientes para realizar o Cancelamento.";
        log.debug("FidelizacaoController:cancelarAtendimento()");

        testParameters();

        ActionRetencaoForm form = (ActionRetencaoForm) formParam;

        if (request.getParameter("progresso").equals(ConstantesCRM.SZERO)) {
            if (form.getLinhaSelecionada() != null && form.getLinhaSelecionada().length() > 0) {
                int indiceLinha = Integer.parseInt(form.getLinhaSelecionada());
                getDadosClienteForm().setDetalheLinha(getListaLinhasVO().getDetalheLinhaVOArray(indiceLinha));
                getConsultaMatrizOfertaForm().setTipoEncerramento(form.getIdTipoEncerramento());
                finaliza = true;
            } else {
                msgErro = "Nenhuma linha Selecionada para o Cancelamento.";
            }
        } else if (request.getParameter("progresso").equals(ConstantesCRM.SONE)) {
            intencao = request.getParameter("wlw-radio_button_group_key:{actionForm.intencaoSelecionada}");
            finaliza = true;
        } else if (request.getParameter("progresso").equals(ConstantesCRM.STWO)) {
            destino = request.getParameter("wlw-radio_button_group_key:{actionForm.idDestinoPrevisto}");
            finaliza = true;
        }
        idUfOperadora = testarValor(getDadosClienteForm().getDetalheLinha().getIdUFOperadora()) ? getDadosClienteForm().getDetalheLinha().getIdUFOperadora() : getDadosClienteForm().getIdUfOperadora();
        if (finaliza) {
            finalizaRetencaoVO = FinalizaRetencaoVO.Factory.newInstance();
            finalizaRetencaoVO.addNewCliente();

            finalizaRetencaoVO.getCliente().setIdPessoaDePara(getParametrosVO().getIdClienteDePara());
            finalizaRetencaoVO.getCliente().setIdUFOperadora(idUfOperadora);
            finalizaRetencaoVO.getCliente().setIdLinhaTelefonica(getDadosClienteForm().getDetalheLinha().getIdLinha());
            finalizaRetencaoVO.getCliente().setNrLinha(getDadosClienteForm().getDetalheLinha().getNumero());
            finalizaRetencaoVO.getCliente().setIdSegmentacao(getParametrosVO().getIdSegmentacao());
            finalizaRetencaoVO.getCliente().setSgTipoPessoa(getParametrosVO().getInTipoPessoa());

            finalizaRetencaoVO.addNewRetencao().addNewRetencaoHeader();
            finalizaRetencaoVO.getRetencao().getRetencaoHeader().setIdTipoEncerramento(form.getIdTipoEncerramento());
            finalizaRetencaoVO.getRetencao().getRetencaoHeader().setIdGrupo(getParametrosVO().getIdGrupo());
            finalizaRetencaoVO.getRetencao().getRetencaoHeader().setNmLoginUsuario(request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN).toString());

            if (request.getParameter("progresso").equals(ConstantesCRM.SONE)) {
                finalizaRetencaoVO.getRetencao().getRetencaoHeader().setIdRespostaIntencao(intencao);

            } else if (request.getParameter("progresso").equals(ConstantesCRM.STWO)) {
                finalizaRetencaoVO.getRetencao().getRetencaoHeader().setIdRespostaIntencao(getDadosClienteForm().getIdIntencaoCancelamento());
                finalizaRetencaoVO.getRetencao().getRetencaoHeader().setIdRespostaDestino(destino);
            }

            if (!ConstantesCRM.SVAZIO.equals(getNrProtocolo())) {
                if (!finalizaRetencaoVO.getRetencao().getRetencaoHeader().isSetProtocoloVO()) {
                    finalizaRetencaoVO.getRetencao().getRetencaoHeader().addNewProtocoloVO().setNrProtocolo(getNrProtocolo());
                } else {
                    finalizaRetencaoVO.getRetencao().getRetencaoHeader().getProtocoloVO().setNrProtocolo(getNrProtocolo());
                }
            }

            retorno = ofertaRealizadaFac.finalizaRetencao(getIdUsuario(request), finalizaRetencaoVO);
            /*
             * Verifica se ser· necess·ria atualizaÁ„o de contato do cliente
             * (mais de 30 dias sem atualizaÁ„o)
             */
            if (retorno.getFidelizacaoContatoVO() != null && retorno.getFidelizacaoContatoVO().getIdPessoa() != null) {
                request.setAttribute("inAtualizacaoContato", ConstantesCRM.SONE);
                request.setAttribute("dsContato", retorno.getFidelizacaoContatoVO().getDsContato());
                request.setAttribute("idPessoa", retorno.getFidelizacaoContatoVO().getIdPessoa());
            }
            getConsultaMatrizOfertaForm().setIdRetencao(retorno.getValor());
            request.setAttribute("idRetencao", retorno.getValor());

            String nrRetProtocolo = retorno.getNrProtocolo() != null ? retorno.getNrProtocolo() : ConstantesCRM.SVAZIO;
            if (!getNrProtocolo().equals(nrRetProtocolo)) {
                String msgProtocolo = "Foi gerado para a linha " + ConstantesCRM.formatPhoneNumber(finalizaRetencaoVO.getCliente().getNrLinha()) + " o n˙mero de protocolo " + nrRetProtocolo + " referente ‡ aÁ„o CANCELAR.";
                request.setAttribute("nrProtocolo", msgProtocolo);
                request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, nrRetProtocolo);
            }

            carregarMsgEcerramento(form.getIdTipoEncerramento(), getDadosClienteForm().getDetalheLinha().getIdUFOperadora(), request);
        } else {
            log.error("Exception - FidelizacaoController:filtroFimRetencao()");
            FormError formError = globalApp.buildFormError(new Exception(msgErro), request.getContextPath() + "/fidelizacao/getLinhas.do");
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="cancelamentoLinha.jsp"
     * @jpf:forward name="finalRetencao" path="filtroFimRetencao.do"
     */
    protected ActionForward reterCMO(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ConsultaMatrizOfertaForm form = (ConsultaMatrizOfertaForm) formParam;

        log.debug("FidelizacaoController:reterCMO()");

        if (request.getSession().getAttribute("sgOferta") == null) {
            request.getSession().setAttribute("sgOferta", request.getParameter("sgOferta"));
        }

        if (ConstantesCRM.SONE.equals(request.getSession().getAttribute("inNovaOferta"))) {
            setIdGrupo(getParametrosVO().getIdGrupo());
            getDadosClienteForm().setIdCliente(getParametrosVO().getIdPessoaCliente());
            getDadosClienteForm().setIdUfOperadora(getParametrosVO().getIdUfOperadora());
            getDadosClienteForm().setNomeCliente(getParametrosVO().getNmCliente());
            getDadosClienteForm().setIdSegmentaco(getParametrosVO().getIdSegmentacao() == null ? ConstantesCRM.SVAZIO : getParametrosVO().getIdSegmentacao());
            getDadosClienteForm().setSegmentacao(getParametrosVO().getDsSegmentacao());
            getDadosClienteForm().setIdCarteirizacao(getParametrosVO().getIdTipoCarteira());
            getDadosClienteForm().setCarteirizacao(getParametrosVO().getDsTipoCarteira());
            getDadosClienteForm().setIdTipoCliente(getParametrosVO().getInTipoPessoa());
            getDadosClienteForm().setNumeroLinha(getParametrosVO().getNrLinha());
            getDadosClienteForm().setIdLinhaTelefonica(getParametrosVO().getIdLinha());
            getDadosClienteForm().getDetalheLinha().setIdPessoaDePara(request.getSession().getAttribute("idPessoaDePara") == null ? ConstantesCRM.SVAZIO : request.getSession().getAttribute("idPessoaDePara").toString());
        }
        testParameters();
        setListaOfertasDisponiveisVO(getListaOfertas(form.getOfertasDisp()));
        setListaOfertasRealizadasVO(getListaOfertas(form.getOfertasReal()));
        setListaOfertasAceitasVO(getListaOfertas(form.getOfertasAceita()));

        if (request.getParameter("inLinhasAssociadas") != null && getListaLinhasVO().sizeOfDetalheLinhaVOArray() > 1) {

            boolean inLinhasAssociadas = "true".equals(request.getParameter("inLinhasAssociadas")) ? true : false;
            getConsultaMatrizOfertaForm().setInCancelamentoLinhasAssociadas(inLinhasAssociadas);

            AtendimentoPesquisaVO atendimentoPesquisaVO = AtendimentoPesquisaVO.Factory.newInstance();
            atendimentoPesquisaVO.addNewGrupoPesquisa().setIdPessoaDePara(getParametrosVO().getIdClienteDePara() != null ? getParametrosVO().getIdClienteDePara() : ConstantesCRM.SVAZIO);
            getCancelarLinhasForm().setListaPalitagens(ofertaRealizadaFac.getListaPalitagens(getIdUsuario(request), atendimentoPesquisaVO));

            if (inLinhasAssociadas) {
                ListaDadosVO listaDadosVO = registrarContatoFacade.getConsultaConta(getIdUsuario(request), ConstantesCRM.SZERO, getParametrosVO().getIdPessoaCliente(), ConstantesCRM.SVAZIO);
                getCancelarLinhasForm().setListaDadosVO(listaDadosVO);
            }

            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);

        } else {
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward("finalRetencao");

        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="index.jsp"
     */
    protected ActionForward getLinhasByConta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        StringBuffer xmlDados = new StringBuffer();
        try {
            String nrValor = request.getParameter("cdConta");
            ListaDadosVO listaDadosVO = registrarContatoFacade.getConsultaConta(getIdUsuario(request), ConstantesCRM.SONE, getParametrosVO().getIdPessoaCliente(), nrValor);
            getCancelarLinhasForm().setListaDadosVO(listaDadosVO);
            xmlDados.append(listaDadosVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

        } catch (Exception e) {
            AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
            ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
            ajaxErrorHandlerVO.setFriendlyMessage(ConstantesCRM.SVAZIO);
            xmlDados = new StringBuffer();
            xmlDados.append(ajaxErrorHandlerVO.xmlText());

        } finally {
            response.setContentType(ConstantesCRM.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(xmlDados.toString().getBytes(ConstantesCRM.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        return null;
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="mensagemEncerramento.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward filtroFimRetencao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        log.debug("FidelizacaoController:filtroFimRetencao()");
        ConsultaMatrizOfertaForm form = (ConsultaMatrizOfertaForm) formParam;
        try {
            testParameters();
            // String[] idOfertaAceita = new String[0];
            getConsultaMatrizOfertaForm().setIdUfOperadora(getDadosClienteForm().getDetalheLinha().getIdUFOperadora() != null ? getDadosClienteForm().getDetalheLinha().getIdUFOperadora() : getDadosClienteForm().getIdUfOperadora());
            getDadosClienteForm().setIdUfOperadora(getDadosClienteForm().getDetalheLinha().getIdUFOperadora());

            finalizaRetencaoVO = getFinalizaRetencaoComDadosGerais(request);
            if (ConstantesCRM.STWO.equals(form.getTipoEncerramento()) && form.getNrLinhasSelecionadas().length > 0) {
                finalizaRetencaoVO.getRetencao().getRetencaoHeader().setIdPalitagem(form.getIdPalitagem());
                for (int i = 0; i < form.getNrLinhasSelecionadas().length; i++) {
                    finalizaRetencaoVO.getCliente().addNewLinhasAssociadas();
                    finalizaRetencaoVO.getCliente().getLinhasAssociadasArray(i).setNrConta(form.getNrLinhasSelecionadas()[i].split("\\|")[0]);
                    finalizaRetencaoVO.getCliente().getLinhasAssociadasArray(i).setNrLinha(form.getNrLinhasSelecionadas()[i].split("\\|")[1]);
                }
            }
            finalizaRetencaoVO.getRetencao().getRetencaoHeader().setIdTipoEncerramento(form.getTipoEncerramento());
            if (ConstantesCRM.SONE.equals(request.getSession().getAttribute("inNovaOferta"))) {
                finalizaRetencaoVO.getRetencao().getRetencaoHeader().setInNovaOferta((String) request.getSession().getAttribute("inNovaOferta"));
            }

            RetornoVO retorno = ofertaRealizadaFac.finalizaRetencao(getIdUsuario(request), finalizaRetencaoVO);
            /*
             * Verifica se ser· necess·ria atualizaÁ„o de contato do cliente
             * (mais de 30 dias sem atualizaÁ„o)
             */
            if (retorno.getFidelizacaoContatoVO() != null && retorno.getFidelizacaoContatoVO().getIdPessoa() != null) {
                request.setAttribute("inAtualizacaoContato", ConstantesCRM.SONE);
                request.setAttribute("dsContato", retorno.getFidelizacaoContatoVO().getDsContato());
                request.setAttribute("idPessoa", retorno.getFidelizacaoContatoVO().getIdPessoa());
            }

            request.getSession().removeAttribute("sgOferta");
            request.getSession().removeAttribute("dsIntencao");
            request.getSession().removeAttribute("dsDestinoPrevisto");

            getConsultaMatrizOfertaForm().setIdRetencao(retorno.getValor());
            request.setAttribute("idRetencao", retorno.getValor());

            String nrRetProtocolo = retorno.getNrProtocolo() != null ? retorno.getNrProtocolo() : ConstantesCRM.SVAZIO;
            if (!getNrProtocolo().equals(nrRetProtocolo)) {
                String acao = "vaipensar".equalsIgnoreCase(request.getParameter(ConstantesCRM.SACTION)) ? "VAI PENSAR" : "cancelar".equalsIgnoreCase(request.getParameter(ConstantesCRM.SACTION)) ? "CANCELAR" : "RETER";
                String msgProtocolo = "Foi gerado para a linha " + ConstantesCRM.formatPhoneNumber(finalizaRetencaoVO.getCliente().getNrLinha()) + " o n˙mero de protocolo " + nrRetProtocolo + " referente ‡ aÁ„o " + acao + ".";
                request.setAttribute("nrProtocolo", msgProtocolo);
                request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, nrRetProtocolo);
            }

            carregarMsgEcerramento(form.getTipoEncerramento(), getDadosClienteForm().getDetalheLinha().getIdUFOperadora(), request);

        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:filtroFimRetencao()", e);
            FormError formError = globalApp.buildFormError(e, request.getContextPath() + "/fidelizacao/filtroFimRetencao.do");
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="pesquisaEndereco.jsp"
     * @jpf:forward name="successAnaliseEndereco" path="pesquisaEnderecoAnalise.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward pesquisaEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        log.debug("FidelizacaoController:pesquisaEndereco()");
        String destino = ConstantesCRM.SUCCESS;
        try {
            testParameters();
            ofertaAparelhoForm.getPesquisaEndereco().addNewFiltroPesquisa();
            ofertaAparelhoForm.getPesquisaEndereco().addNewListaEnderecos();
            request.setAttribute("idMotivoAlteracaoEndereco", request.getParameter("idMotivoAlteracaoEndereco"));
            // Retorna as UFs v·lidas
            ofertaAparelhoForm.setRegionais(ofertaAparelhoFac.getPesquisaEnderecoIni(getIdUsuario(request)).getUFVOArray());
            if ("analiseEndereco".equals(request.getParameter("source"))) {
                destino = "successAnaliseEndereco";
            }
        } catch (TuxedoWarningException twe) {
            log.warn("ConsultaMatrizOfertaController:pesquisaEndereco(" + twe.getMessage() + ")");
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:pesquisaEndereco()", e);
            FormError formError = globalApp.buildFormError(e, request.getContextPath() + "/fidelizacao/getLinhas.do");
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(destino);
    }

    /**
     * @jpf:action
     * @jpf:forward name="selecionado" path="dadosEntregaAparelho.jsp"
     * @jpf:forward name="success" path="listaEnderecos.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward buscaEnderecoCD(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        log.debug("FidelizacaoController:buscaEnderecoCD()");
        try {
            testParameters();
            if (request.getParameter("selecionado") != null) {
                int idEndArray = 0;
                idEndArray = Integer.parseInt(request.getParameter("idArrayEndereco"));
                EnderecoVO enderecoVO = ofertaAparelhoForm.getPesquisaEndereco().getListaEnderecos().getEnderecoVOArray(idEndArray);
                enderecoVO.setNrCEP(formatarCep(enderecoVO.getNrCEP()));

                enderecoVO.setDsEnderecoComplemento(enderecoVO.getDsEnderecoComplemento().trim());

                ofertaAparelhoForm.setEnderecoVO(enderecoVO);
                ofertaAparelhoForm.loadEndereco(enderecoVO);

                if (ConstantesCRM.STRUE.equals(request.getParameter("inAnalise"))) {
                    String xmlOUT = enderecoVO.xmlText();
                    xmlOUT = xmlOUT.replaceAll("vo:", ConstantesCRM.SVAZIO);
                    xmlOUT = xmlOUT.replaceAll(" xmlns:vo=\"cliente.fo.vivo.com.br/vo\"", ConstantesCRM.SVAZIO);
                    xmlOUT = xmlOUT.replaceAll("xml-fragment", "EnderecoVO");
                    response.setContentType(ConstantesCRM.SContentType);
                    BufferedOutputStream saida = new BufferedOutputStream(response.getOutputStream());
                    saida.write(xmlOUT.getBytes(ConstantesCRM.SISO));
                    saida.flush();
                    saida.close();
                    return null;
                } else {
                    request.setAttribute("idMotivoAlteracaoSelecionado", request.getParameter("idMotivoAlteracaoSelecionado"));
                    ofertaAparelhoForm.setDelivery(true);
                    ofertaAparelhoForm.setIdEntrega(0); // Delivery
                    request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                    return mapping.findForward("selecionado");
                }
            } else {
                PesquisaEnderecoVO filtroEndereco = PesquisaEnderecoVO.Factory.newInstance();
                filtroEndereco.addNewFiltroPesquisa();
                ofertaAparelhoForm.getPesquisaEndereco().addNewListaEnderecos();
                if (request.getParameter("iniciarTela") == null) {
                    filtroEndereco.getFiltroPesquisa().setDsLogradouro(request.getParameter("filtroPesquisa.dsLogradouro").trim());
                    filtroEndereco.getFiltroPesquisa().setDsBairro(request.getParameter("filtroPesquisa.dsBairro").trim());
                    filtroEndereco.getFiltroPesquisa().setDsLocalidade(request.getParameter("filtroPesquisa.dsLocalidade").trim());
                    filtroEndereco.getFiltroPesquisa().setNrCEP(request.getParameter("filtroPesquisa.nrCEP").trim());
                    filtroEndereco.getFiltroPesquisa().setIdUFSelecionado(request.getParameter("filtroPesquisa.idUFSelecionado").trim());
                    ofertaAparelhoForm.setPesquisaEndereco(ofertaAparelhoFac.getPesquisaEnderecoFil(getIdUsuario(request), filtroEndereco));
                }
                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                return mapping.findForward(ConstantesCRM.SUCCESS);
            }
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:buscaEnderecoCD()", e);
            FormError formError = globalApp.buildFormError(e, request.getContextPath() + "/fidelizacao/getLinhas.do");
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="AgendaContato.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward getDadosAgendarContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("FidelizacaoController:getDadosAgendarContato()");
        ConsultaMatrizOfertaForm form = (ConsultaMatrizOfertaForm) formParam;
        try {
            testParameters();

            setListaOfertasRealizadasVO(getListaOfertasRealizadasVO().sizeOfItemListaDescricaoVOArray() == 0 ? getListaOfertas(form.getOfertasReal()) : getListaOfertasRealizadasVO());
            setListaOfertasAceitasVO(getListaOfertasAceitasVO().sizeOfItemListaDescricaoVOArray() == 0 ? getListaOfertas(form.getOfertasAceita()) : getListaOfertasAceitasVO());
            setListaOfertasDisponiveisVO(getListaOfertasDisponiveisVO().sizeOfItemListaDescricaoVOArray() == 0 ? getListaOfertas(form.getOfertasDisp()) : getListaOfertasDisponiveisVO());

            getDadosClienteForm().setNomeContato(getDadosClienteForm().getNomeCliente());
            getDadosClienteForm().setTelefoneContato(
                    new StringBuffer("(").append(getDadosClienteForm().getNumeroLinha().substring(0, 2)).append(")").append(getDadosClienteForm().getNumeroLinha().substring(2, 6)).append("-")
                            .append(getDadosClienteForm().getNumeroLinha().substring(6, getDadosClienteForm().getNumeroLinha().length())).toString());

            if (request.getParameter("intencaoCancelamento") != null) {
                getDadosClienteForm().setIdIntencaoCancelamento(request.getParameter("intencaoCancelamento").toString());
            }

            if (request.getParameter("destinoPrevisto") != null) {
                getDadosClienteForm().setIdDestinoPrevisto(request.getParameter("destinoPrevisto").toString());
            }

            carregarHistorico(request);
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:getDadosAgendarContato()", e);
            // ObservaÁ„o: Monta URL de retorno e desvia para o erro
            FormError formError = globalApp.buildFormError(e, request.getContextPath() + "/fidelizacao/getLinhas.do");
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="mensagemEncerramento.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward agendarContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("FidelizacaoController:agendarContato()");
        DadosClienteForm form = (DadosClienteForm) formParam;
        try {
            testParameters();
            String[] dados = new String[12];
            dados[0] = getDadosClienteForm().getDetalheLinha().getIdPessoaDePara();
            dados[1] = getDadosClienteForm().getIdIntencaoCancelamento() != null && !ConstantesCRM.SVAZIO.equals(getDadosClienteForm().getIdIntencaoCancelamento()) ? getDadosClienteForm().getIdIntencaoCancelamento() : "null";
            dados[2] = getDadosClienteForm().getIdDestinoPrevisto() != null && !ConstantesCRM.SVAZIO.equals(getDadosClienteForm().getIdDestinoPrevisto()) ? getDadosClienteForm().getIdDestinoPrevisto() : "null";
            dados[3] = ConstantesCRM.SONE;/*
                                           * Tipo de Encerramento. O Agendamento
                                           * de Contato sempre grava 1*
                                           */
            dados[4] = null;
            /** ObservaÁ„o **/
            dados[5] = getDadosClienteForm().getIdLinhaTelefonica();
            dados[6] = getDadosClienteForm().getNumeroLinha();
            dados[7] = form.getNomeContato();
            dados[8] = form.getTelefoneContato();
            dados[9] = new StringBuffer(form.getData()).append(" ").append(form.getHoras()).append(":").append(form.getMinutos()).toString();
            dados[10] = form.getComentario();
            dados[11] = getParametrosVO().getIdGrupo();

            RetornoVO retorno = agendarContatoFac.agendarContato2(getIdUsuario(request), dados, getListaOfertasRealizadasVO(), getListaOfertasAceitasVO().sizeOfItemListaDescricaoVOArray() > 0 ? getListaOfertasAceitasVO()
                    .getItemListaDescricaoVOArray(0).getId() : ConstantesCRM.SVAZIO);
            getConsultaMatrizOfertaForm().setIdRetencao(retorno.getValor());
            getConsultaMatrizOfertaForm().setTipoEncerramento(ConstantesCRM.SONE);

            String nrRetProtocolo = retorno.getNrProtocolo() != null ? retorno.getNrProtocolo() : ConstantesCRM.SVAZIO;
            if (!getNrProtocolo().equals(nrRetProtocolo)) {
                request.setAttribute("nrProtocolo", "Protocolo " + nrRetProtocolo + " gerado.");
                request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, nrRetProtocolo);
            }

            carregarMsgEcerramento(getConsultaMatrizOfertaForm().getTipoEncerramento(), getDadosClienteForm().getDetalheLinha().getIdUFOperadora(), request);

        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:agendarContato()", e);
            // ObservaÁ„o: Monta URL de retorno e desvia para o erro
            FormError formError = globalApp.buildFormError(e, request.getContextPath() + "/fidelizacao/getLinhas.do");
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="realizarRetencao.jsp"
     */
    protected ActionForward voltarIC(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("FidelizacaoController:voltarIC()");
        testParameters();
        request.getSession().removeAttribute("retencao_ActionRetencaoForm");
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="ofertaAparelho.jsp"
     */
    protected ActionForward voltarDEA(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("FidelizacaoController:voltarDEA()");
        if (!ConstantesCRM.SONE.equals(request.getParameter("inProcedimentoNormal"))) {
            // consultaMatrizOfertaForm = (ConsultaMatrizOfertaForm)
            // this.getPreviousActionInfo().getForm();
            getConsultaMatrizOfertaForm().setIdMeioPagamento(consultaMatrizOfertaForm.getIdMeioPagamento());
            getConsultaMatrizOfertaForm().setNroParcela(consultaMatrizOfertaForm.getNroParcela());
            getConsultaMatrizOfertaForm().setMeioPagamento(consultaMatrizOfertaForm.getMeioPagamento());
            getConsultaMatrizOfertaForm().setPercDesconto(consultaMatrizOfertaForm.getPercDesconto());
            getConsultaMatrizOfertaForm().setVlParcela(consultaMatrizOfertaForm.getVlParcela());
            getConsultaMatrizOfertaForm().setVlDesconto(consultaMatrizOfertaForm.getVlDesconto());
            getConsultaMatrizOfertaForm().setVlCalcularDesconto(consultaMatrizOfertaForm.getVlCalcularDesconto());
        }
        if (getListaAparelhosVO().getMsgErro() == null) {
            getListaAparelhosVO().addNewMsgErro();
        }
        testParameters();
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="intencaoCanelamento.jsp"
     */
    protected ActionForward voltarDPrevisto(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("FidelizacaoController:voltarDPrevisto()");
        testParameters();
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="destinoPrevisto.jsp"
     */
    protected ActionForward voltarCMO(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("FidelizacaoController:voltarCMO() Inicio");

        testParameters();

        setListaOfertasDisponiveisVO(FidelizacaoListaGeralDescricaoVO.Factory.newInstance());
        setListaOfertasRealizadasVO(FidelizacaoListaGeralDescricaoVO.Factory.newInstance());
        setListaOfertasAceitasVO(FidelizacaoListaGeralDescricaoVO.Factory.newInstance());
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        
        log.debug("FidelizacaoController:voltarCMO() Fim ");
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="ConsultaMatrizOferta.jsp"
     * @jpf:forward name="error" path="erro.jsp"
     */
    protected ActionForward voltarOfertas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("FidelizacaoController:voltarOfertas()");

        testParameters();

        setConsultaMatrizOfertaForm(new ConsultaMatrizOfertaForm());
        setCadastrarMigracaoForm(new CadastrarMigracaoForm());
        setOfertaAparelhoForm(new OfertaAparelhoForm());
        setSuspensaoTemporariaForm(new SuspensaoTemporariaForm());
        request.setAttribute("inVoltar", ConstantesCRM.SONE);
        if (request.getSession().getAttribute("idRetencao") != null) {
            getDadosClienteForm().getDetalheLinha().setValorMulta(ConstantesCRM.SVAZIO);
        }
        if ("true".equals(request.getParameter("inCancelamentoLinhasAssociadas")) && getListaLinhasVO().getDetalheLinhaVOArray().length > 1) {
            getConsultaMatrizOfertaForm().setInCancelamentoLinhasAssociadas(true);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="dadosCliente.jsp"
     * @jpf:forward name="error" path="erro.jsp"
     */
    protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("FidelizacaoController:begin()::inicio");
        try {
            setDadosClienteForm(new DadosClienteForm());

            request.getSession().removeAttribute("inAdimplente");
            request.getSession().removeAttribute(ConstantesCRM.SACTION);
            request.getSession().removeAttribute("dsIntencao");
            request.getSession().removeAttribute("idRetencao");

            carregarDadosClienteForm(request);

        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:begin()", e);
            request.setAttribute(ConstantesCRM.SERROR, e.getMessage());
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        log.debug("FidelizacaoController:begin()::fim");
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="saldoPontos.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     * @jpf:forward name="getDetalheProcessoPortabilidade"
     *              return-action="getDetalheProcesso"
     *              return-form="dadosClienteForm"
     */
    protected ActionForward getSaldoPontos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("FidelizacaoController:getSaldoPontos()");
        String nrLinha;
        ActionRedirect f;

        try {

            if (request.getParameter("nrLinha") != null) {
                nrLinha = request.getParameter("nrLinha");
                f = new ActionRedirect(mapping.findForward("getDetalheProcessoPortabilidade"));
            } else {
                nrLinha = getParametrosVO().getNrLinha();
                f = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
            }

            String saldoPontos = getPontuacao(nrLinha, request);
            getDadosClienteForm().setSaldoPontos(saldoPontos);
            if (request.getParameter("nrLinha") != null) {
                f.addParameter("saldoPontos", saldoPontos);
            }

        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:getSaldoPontos()", e);
            FormError formError = globalApp.buildFormError(e, request.getContextPath() + "/fidelizacao/getLinhas.do");
            formError.setTarget(TARGET_FORM_ERROR);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        // return f;
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /************************************* METODOS AUXILIARES *************************************************/
    /***********************************************************************************************************/
    /**************************************************************************************************/
    /************************************ CARREGAR LISTAS *********************************************/
    /**************************************************************************************************/
    /**
     * Carrega lista de Descontos que podem ser oferecidos na Oferta de
     * Aparelhos
     * 
     * @param idAparelho
     *            String
     * @param acao
     *            String
     */
    private void carregarPercDesconto(String idAparelho, String acao, HttpServletRequest request) throws Exception {

        log.debug("FidelizacaoController:carregarPercDesconto()");

        int idSegmentacao = 0;
        try {
            // idSegmentacao = Integer.parseInt(getParametrosVO().getIdSegmentacao());//Corrigido para enviar o ID SegmentaÁao da Linha
            idSegmentacao = Integer.parseInt(getDadosClienteForm().getDetalheLinha().getIdSegmentacao());
        } catch (Exception e) {
        }

        try {
            getOfertaAparelhoForm().setListaDescontoVO(ofertaAparelhoFac.getPercDesconto(getIdUsuario(request), idAparelho, acao, idSegmentacao).getItemListaVOArray());
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:carregarPercDesconto()", e);
            throw e;
        }
    }

    /* *
     * Carrega lista de LigaÁıes indevidas que foram regitradas pelo operador
     * @param idLigacao String
     */
    /*
     * private void carregarLigacaoIndevida() throws Exception{
     * log.debug("FidelizacaoController:carregarLigacaoIndevida()"); try{
     * setListaLigacaoIndevidaVO
     * (retencaoFAC.getLigacaoIndevida(getIdUsuario(request))); }catch(Exception
     * e ){
     * log.error("Exception - FidelizacaoController:carregarLigacaoIndevida()"
     * ,e); throw e ; } }
     */
    /**
     * Carrega lista de Parcelas que podem ser oferecidas par o pagamentos de um
     * aparelho em Oferta de Aparelhos
     * 
     * @param idAparelho
     *            String
     */
    private void carregarParcelas(String idAparelho, String acao, HttpServletRequest request) throws Exception {
        log.debug("FidelizacaoController:carregarParcelas()");
        try {
            String idTipoPessoa = getParametrosVO().getInTipoPessoa();
            getOfertaAparelhoForm().setListaParcelaVO(ofertaAparelhoFac.getParcelas(getIdUsuario(request), idAparelho, acao, idTipoPessoa).getItemListaVOArray());
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:carregarParcelas()", e);
            throw e;
        }
    }

    /**
     * Carrega lista de Bonus
     */
    private void carregarListaBonus(HttpServletRequest request) throws Exception {
        log.debug("FidelizacaoController:carregarListaBonus()");
        try {
            setListaBonusVO(ofertaRealizadaFac.getBonus(getIdUsuario(request), getDadosClienteForm().getDetalheLinha().getIdUFOperadora(), getListaOfertasAceitasVO().getItemListaDescricaoVOArray(0).getId(), getDadosClienteForm().getIdTipoCliente(),
                    getDadosClienteForm().getDetalheLinha().getIdSegmentacao(), getDadosClienteForm().getDetalheLinha().getIdTipoLinha()));
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:carregarListaBonus()", e);
            throw e;
        }
    }

    /**
     * Carrega lista de migracao
     */
    private void carregarListaMigracao(HttpServletRequest request) throws Exception {
        log.debug("FidelizacaoController:carregarListaMigracao()");
        try {
            setListaMigracaoVO(ofertaRealizadaFac.getListaMigracao(getIdUsuario(request), getDadosClienteForm().getDetalheLinha().getIdUFOperadora(), getDadosClienteForm().getIdTipoCliente(), getDadosClienteForm().getDetalheLinha().getIdTipoLinha()));
            if (getDadosClienteForm().getDetalheLinha() != null) {
                getCadastrarMigracaoForm().setNrAtual(getDadosClienteForm().getDetalheLinha().getNumero());
            }
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:carregarListaMigracao()", e);
            throw e;
        }
    }

    /**
     * Carrega lista de Planos
     */
    private void carregarListaPlanos(HttpServletRequest request) throws Exception {
        log.debug("FidelizacaoController:carregarListaPlanos()");
        try {
            setListaPlanosVO(ofertaRealizadaFac.getPlano(getIdUsuario(request), getDadosClienteForm().getDetalheLinha().getIdUFOperadora(), getDadosClienteForm().getIdTipoCliente(), getDadosClienteForm().getDetalheLinha().getIdTipoLinha()));
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:carregarListaPlanos()", e);
            throw e;
        }
    }

    /**
     * Carrega qtde de dias para uma Suspensao Temporaria
     */
    private void carregarDiasSuspensaoTemp(HttpServletRequest request) throws Exception {
        log.debug("FidelizacaoController:carregarDiasSuspensaoTemp()");
        try {
            setDiasSuspensaoTemp(ofertaRealizadaFac.getDiasSuspTemp(getIdUsuario(request)).getValor());
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:carregarDiasSuspensaoTemp()", e);
            throw e;
        }
    }

    /**
     * Carrega lista de aparaelhos, com preÁco e multa ja formatados para
     * Decimal
     * 
     * @param acao
     *            String
     */
    public void carregarAparelho(String acao, String sgOferta, HttpServletRequest request) throws Exception {
        log.debug("FidelizacaoController:carregarAparelho()");
        // recupera dados do cliente
        OfAparelhoParamVO ofaparelhoparam = OfAparelhoParamVO.Factory.newInstance();
        ofaparelhoparam.setIdUfOperadora(getDadosClienteForm().getDetalheLinha().getIdUFOperadora());
        ofaparelhoparam.setIdTipoPessoa(getDadosClienteForm().getIdTipoCliente() != null && !ConstantesCRM.SVAZIO.equals(getDadosClienteForm().getIdTipoCliente()) ? getDadosClienteForm().getIdTipoCliente() : ConstantesCRM.SZERO);
        ofaparelhoparam.setIdSegmentacao(getDadosClienteForm().getDetalheLinha().getIdSegmentacao() != null && !ConstantesCRM.SVAZIO.equals(getDadosClienteForm().getDetalheLinha().getIdSegmentacao()) ? getDadosClienteForm().getDetalheLinha().getIdSegmentacao()
                : ConstantesCRM.SZERO);
        ofaparelhoparam.setIdGrupo(getParametrosVO().getIdGrupo());
        ofaparelhoparam.setInExcecao(acao);
        ofaparelhoparam.setIdLinhaTelefonica(getDadosClienteForm().getDetalheLinha().getIdLinha());
        ofaparelhoparam.setIdTipoLinha(Integer.parseInt(getDadosClienteForm().getDetalheLinha().getIdTipoLinha()));
        ofaparelhoparam.setSgOferta(sgOferta);
        /*
         * String[] dados = new String[6]; dados[0] =
         * getDadosClienteForm().getDetalheLinha().getIdUFOperadora(); dados[1]
         * = getDadosClienteForm().getIdTipoCliente()!=null &&
         * !ConstantesCRM.SVAZIO.equals(getDadosClienteForm().getIdTipoCliente())?
         * getDadosClienteForm().getIdTipoCliente(): ConstantesCRM.SZERO;
         * dados[2] =
         * getDadosClienteForm().getDetalheLinha().getIdSegmentacao()!=null &&
         * !ConstantesCRM.SVAZIO
         * .equals(getDadosClienteForm().getDetalheLinha().getIdSegmentacao())?
         * getDadosClienteForm().getDetalheLinha().getIdSegmentacao():
         * ConstantesCRM.SZERO; dados[3] =
         * getParametrosVO().getIdGrupo(); dados[4] = acao; dados[5] =
         * getDadosClienteForm().getDetalheLinha().getIdLinha();
         */
        try {
            setListaAparelhosVO(ofertaAparelhoFac.getAparelho(getIdUsuario(request), ofaparelhoparam));
            for (int i = 0; i < getListaAparelhosVO().getItemOfertaAparelhoVOArray().length; i++) {
                // formata preco e multa para decimal
                getListaAparelhosVO().getItemOfertaAparelhoVOArray(i).setPreco(formatarDecimal(getListaAparelhosVO().getItemOfertaAparelhoVOArray(i).getPreco(), 0));
                // getListaAparelhosVO().getItemOfertaAparelhoVOArray(i).setMulta(formatarDecimal(getListaAparelhosVO().getItemOfertaAparelhoVOArray(i).getMulta(),0));
            }
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:carregarAparelho()", e);
            throw e;
        }
    }

    /**
     * Carrega lista de historicos
     */
    public void carregarHistorico(HttpServletRequest request) throws Exception {
        log.debug("FidelizacaoController:carregarHistorico()");
        try {
            setListaHistoricoAgendamentoVO(agendarContatoFac.getHistoricoAgendamento(getIdUsuario(request), getDadosClienteForm().getDetalheLinha().getIdLinha()));
        } catch (Exception ex) {
            log.error("Exception - FidelizacaoController:carregarHistorico()", ex);
            throw ex;
        }
    }

    /**
     * Carrega lista de Mensagens de encerramento
     * 
     * @param tipoEncerramento
     *            String
     * @param idUfOperadora
     *            String
     */
    private void carregarMsgEcerramento(String tipoEncerramento, String idUfOperadora, HttpServletRequest request) throws Exception {
        log.debug("FidelizacaoController:carregarMsgEcerramento()");
        try {
            setListaMensagensVO(ofertaRealizadaFac.getMsgEncerramento(getIdUsuario(request), idUfOperadora, tipoEncerramento));
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:carregarMsgEcerramento()", e);
            throw e;
        }
    }

    /**
     * Atribui ao formulario DadosClienteFormos os dados do cliente que est„o na
     * sess„o e
     */
    private void carregarDadosClienteForm(HttpServletRequest request) throws Exception {
        log.debug("FidelizacaoController:carregarDadosClienteForm()");
        StringBuffer error = new StringBuffer("Cliente n„o possui dados b·sicos para efetuar retenÁ„o.\\n");
        try {
            // carrega dados do cliente no formulario caso ainda n„o estejam
            if (!testarValor(getDadosClienteForm().getIdCliente())) {
                if (!testarValor(getParametrosVO().getIdPessoaCliente())) {
                    error.append("Cliente n„o informado.");
                    error.append("\\nA aba de retenÁ„o ser· cancelada.");
                    throw new Exception(error.toString());
                }

                if (!testarValor(getParametrosVO().getNrLinha())) {
                    error.append("N˙mero da linha n„o informado!");
                    error.append("\\nA aba de retenÁ„o ser· cancelada.");
                    throw new Exception(error.toString());
                }

                if (!testarValor(getParametrosVO().getInTipoPessoa())) {
                    error.append("Tipo de pessoa n„o informado!");
                    error.append("\\nA aba de retenÁ„o ser· cancelada.");
                    throw new Exception(error.toString());
                }

                setIdGrupo(getParametrosVO().getIdGrupo());
                getDadosClienteForm().setIdCliente(getParametrosVO().getIdPessoaCliente());
                getDadosClienteForm().setIdUfOperadora(getParametrosVO().getIdUfOperadora());
                getDadosClienteForm().setNomeCliente(getParametrosVO().getNmCliente());
                getDadosClienteForm().setIdSegmentaco(getParametrosVO().getIdSegmentacao() == null ? ConstantesCRM.SVAZIO : getParametrosVO().getIdSegmentacao());
                getDadosClienteForm().setSegmentacao(getParametrosVO().getDsSegmentacao());
                getDadosClienteForm().setIdCarteirizacao(getParametrosVO().getIdTipoCarteira());
                getDadosClienteForm().setCarteirizacao(getParametrosVO().getDsTipoCarteira());
                getDadosClienteForm().setIdTipoCliente(getParametrosVO().getInTipoPessoa());
                getDadosClienteForm().setNumeroLinha(getParametrosVO().getNrLinha());
                getDadosClienteForm().setIdLinhaTelefonica(getParametrosVO().getIdLinha());
                // getDadosClienteForm().setSaldoPontos
                // (getPontuacao(getParametrosVO().getNrLinha()));
            }
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:carregarDadosClienteForm()", e);
            throw e;
        }
    }

    /**
     * Retorna lista de ofertas de acordo com o array de ids de ofertas
     * informado
     * 
     * @param arrayOfertas
     *            String[]
     * @return FidelizacaoListaGeralDescricaoVO
     */
    private FidelizacaoListaGeralDescricaoVO getListaOfertas(String[] arrayOfertas) {
        log.debug("FidelizacaoController:getListaOfertas()");
        FidelizacaoListaGeralDescricaoVO objListaOfertas = FidelizacaoListaGeralDescricaoVO.Factory.newInstance();
        Vector vec = new Vector();
        for (int aOfertas = 0; aOfertas < arrayOfertas.length; aOfertas++) {
            // confere se j· existe um a oferta com mesmo id, para que nao haja
            // repetiÁıes
            if (!vec.contains(arrayOfertas[aOfertas])) {
                vec.add(aOfertas, arrayOfertas[aOfertas]);
                // percorre a lista de ofertas para encontrar a oferta referente
                // ao id
                for (int lOfertas = 0; lOfertas < getListaOfertasVO().sizeOfItemListaDescricaoVOArray(); lOfertas++) {
                    // se os ids das ofertas forem equivalentes, adiciona a
                    // oferta a lista de ofertas que sera retornada
                    if (getListaOfertasVO().getItemListaDescricaoVOArray(lOfertas).getId().equals(arrayOfertas[aOfertas])) {
                        objListaOfertas.addNewItemListaDescricaoVO();// adiciona
                                                                     // um
                                                                     // novo
                                                                     // item
                                                                     // a
                                                                     // lista
                        objListaOfertas.getItemListaDescricaoVOArray(objListaOfertas.sizeOfItemListaDescricaoVOArray() - 1).setId(getListaOfertasVO().getItemListaDescricaoVOArray(lOfertas).getId()); // adiciona
                        // id
                        // ao
                        // novo
                        // item
                        // da
                        // lista
                        objListaOfertas.getItemListaDescricaoVOArray(objListaOfertas.sizeOfItemListaDescricaoVOArray() - 1).setDescricao1(getListaOfertasVO().getItemListaDescricaoVOArray(lOfertas).getDescricao1()); // adiciona
                        // descricao
                        // ao
                        // novo
                        // item
                        // da
                        // lista
                        objListaOfertas.getItemListaDescricaoVOArray(objListaOfertas.sizeOfItemListaDescricaoVOArray() - 1).setSigla(getListaOfertasVO().getItemListaDescricaoVOArray(lOfertas).getSigla()); // adiciona
                        // descricao
                        // ao
                        // novo
                        // item
                        // da
                        // lista
                        break;
                    }
                }
            }
        }
        return objListaOfertas;
    }

    /**************************************************************************************************/
    /************************************ RECUPERAR DESCRICAO *****************************************/
    /**************************************************************************************************/
    /**
     * recupera descriÁ„o do meio de pagamento
     * 
     * @param idMeioPagamento
     *            String
     * @return String
     */
    private String getDescMeioPagamento(String idMeioPagamento) {
        for (int i = 0; i < getListaAparelhosVO().getFidelizacaoListaGeralVO().sizeOfItemListaVOArray(); i++) {
            if (idMeioPagamento.equals(getListaAparelhosVO().getFidelizacaoListaGeralVO().getItemListaVOArray(i).getId())) {
                return getListaAparelhosVO().getFidelizacaoListaGeralVO().getItemListaVOArray(i).getDescricao();
            }
        }
        return ConstantesCRM.SVAZIO;
    }

    /**
     * Retorna Descricao de um determinado bonus
     * 
     * @param id
     *            String
     * @return String
     */
    private String getBonusDescricao(String id) {
        log.debug("FidelizacaoController:getBonusDescricao()");

        // percorre a lista de bonus para encontrar a descricao referente ao id
        // informado
        for (int b = 0; b < getListaBonusVO().sizeOfBonusVOArray(); b++) {
            if (getListaBonusVO().getBonusVOArray(b).getIdBonus().equals(id)) {
                // return
                // StringEscapeUtils.escapeXml(getListaBonusVO().getBonusVOArray(b).getDescricao().toString());
                return getListaBonusVO().getBonusVOArray(b).getDescricao().toString();
            }
        }
        return ConstantesCRM.SVAZIO;
    }

    /**
     * Retorna Descricao de um determinado plano
     * 
     * @param id
     *            String
     * @return String
     */
    private String getPlanoDescricao(String id) {
        log.debug("FidelizacaoController:getPlanoDescricao()");

        // percorre a lista de planos para encontrar a descricao referente ao id
        // informado
        for (int p = 0; p < getListaPlanosVO().sizeOfPlanoVOArray(); p++) {
            if (getListaPlanosVO().getPlanoVOArray(p).getId().equals(id)) {
                // return
                // StringEscapeUtils.escapeXml(getListaPlanosVO().getPlanoVOArray(p).getDescricao().toString());
                return getListaPlanosVO().getPlanoVOArray(p).getDescricao().toString();
            }
        }
        return ConstantesCRM.SVAZIO;
    }

    /**************************************************************************************************/
    /************************************ OUTROS ******************************************************/
    /**************************************************************************************************/
    /**
     * O usu·io depois que inicia uma retenÁ„o n„o pode alterar parametros da
     * TelaInicial, porÈm o sistema o permiti fazer isso. Quando parametros na
     * TelaIncial s„o alterados, o controller da TelaIncial È chamado fazendo
     * com que os dados armazenados no controller de FidelizaÁao se percam. Esse
     * mÈtodo verifica se os parametros da tela inicial foram alterados e nvia
     * uma mensagem de erro caso seja verdadeiro.
     * 
     * @throws FOException
     */
    private void testParameters() throws FOException {
        if (getIdGrupo() == null) {
            log.error("Exception - FidelizacaoController:testParameters()\nAlteracao ilegal de parametros na tela inicial.\n Os parametros da tela inicial devem ser configurados antes de iniciar retencao.");
            throw new FOException(StringEscapeUtils.unescapeJava("ALTERACAO ILEGAL DE PARAMETROS NA TELA INICIAL.\nOS PARAMETROS DA TELA INICIAL DEVEM SER CONFIGURADOS ANTES DE INICIAR RETENCAO!"));
        }
    }

    /**
     * Formata o CEP
     * 
     * @param cep
     *            String
     * @return String
     */
    private String formatarCep(String cep) {
        if (cep != null && cep.indexOf("-") == -1 && cep.length() >= 5) {
            cep = cep.substring(0, 5) + "-" + cep.substring(5, cep.length());
            cep = cep.length() > 9 ? cep.substring(0, 9) : cep;
        }
        return cep;
    }

    /**
     * Verifica se o valor de uma String È nulo ou vazio
     * 
     * @return boolean
     */
    private boolean testarValor(String valor) {
        log.debug("FidelizacaoController:testarValor()");
        // verifica se o valor esta nulo ou vazio
        return (valor != null && !ConstantesCRM.SVAZIO.equals(valor));
    }

    /* *
     * Retorna Dados do cliente para adicionar uma oferta
     * @return String[]
     */
    /*
     * private String[] getDadosGerais(){
     * log.debug("FidelizacaoController:getDadosGerais()"); return new
     * String[]{getDadosClienteForm().getDetalheLinha().getIdPessoaDePara(),
     * getDadosClienteForm().getIdIntencaoCancelamento(),
     * getDadosClienteForm().getIdDestinoPrevisto(), ConstantesCRM.SVAZIO,
     * getDadosClienteForm().getDetalheLinha().getIdLinha(),
     * getDadosClienteForm().getDetalheLinha().getNumero(),
     * getDadosClienteForm().getDetalheLinha().getIdUFOperadora(),
     * getListaOfertasAceitasVO().sizeOfItemListaDescricaoVOArray() > 0 ?
     * getListaOfertasAceitasVO().getItemListaDescricaoVOArray(0).getId() : ConstantesCRM.SVAZIO,
     * getParametrosVO().getIdGrupo() }; }
     */
    private FinalizaRetencaoVO getFinalizaRetencaoComDadosGerais(HttpServletRequest request) {
        FinalizaRetencaoVO finalizaRetencaoVO = FinalizaRetencaoVO.Factory.newInstance();
        if (this.finalizaRetencaoVO != null) {
            finalizaRetencaoVO.set(this.finalizaRetencaoVO.copy());
        }

        if (finalizaRetencaoVO.getCliente() == null) {
            finalizaRetencaoVO.addNewCliente();
        }
        if (finalizaRetencaoVO.getRetencao() == null) {
            finalizaRetencaoVO.addNewRetencao();
        }
        if (finalizaRetencaoVO.getRetencao().getRetencaoHeader() == null) {
            finalizaRetencaoVO.getRetencao().addNewRetencaoHeader();
        }
        if (finalizaRetencaoVO.getRetencao().getRetencaoBody() == null) {
            finalizaRetencaoVO.getRetencao().addNewRetencaoBody();
        }

        finalizaRetencaoVO.getRetencao().getRetencaoHeader().setNrLinhaTI(getParametrosVO().getNrLinha());
        if (!ConstantesCRM.SVAZIO.equals(getNrProtocolo())) {
            if (!finalizaRetencaoVO.getRetencao().getRetencaoHeader().isSetProtocoloVO()) {
                finalizaRetencaoVO.getRetencao().getRetencaoHeader().addNewProtocoloVO().setNrProtocolo(getNrProtocolo());
            } else {
                finalizaRetencaoVO.getRetencao().getRetencaoHeader().getProtocoloVO().setNrProtocolo(getNrProtocolo());
            }
        }

        finalizaRetencaoVO.getCliente().setIdPessoaDePara(testarValor(getDadosClienteForm().getIdPessoaDePara()) ? getDadosClienteForm().getIdPessoaDePara() : getParametrosVO().getIdClienteDePara());
        finalizaRetencaoVO.getCliente().setIdUFOperadora(testarValor(getDadosClienteForm().getDetalheLinha().getIdUFOperadora()) ? getDadosClienteForm().getDetalheLinha().getIdUFOperadora() : getDadosClienteForm().getIdUfOperadora());
        finalizaRetencaoVO.getCliente().setIdLinhaTelefonica(getDadosClienteForm().getDetalheLinha().getIdLinha());
        finalizaRetencaoVO.getCliente().setNrLinha(getDadosClienteForm().getDetalheLinha().getNumero());
        finalizaRetencaoVO.getCliente().setIdSegmentacao(getDadosClienteForm().getIdSegmentaco());
        finalizaRetencaoVO.getCliente().setSgTipoPessoa(getParametrosVO().getInTipoPessoa());
        finalizaRetencaoVO.getCliente().setIdTipoLinha(getDadosClienteForm().getDetalheLinha().getIdTipoLinha());
        finalizaRetencaoVO.getRetencao().getRetencaoHeader().setIdGrupo(getParametrosVO().getIdGrupo());
        finalizaRetencaoVO.getRetencao().getRetencaoHeader().setNmLoginUsuario(request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN).toString());

        if (testarValor(getDadosClienteForm().getIdIntencaoCancelamento())) {
            finalizaRetencaoVO.getRetencao().getRetencaoHeader().setIdRespostaIntencao(getDadosClienteForm().getIdIntencaoCancelamento());
        }
        if (testarValor(getDadosClienteForm().getIdDestinoPrevisto())) {
            finalizaRetencaoVO.getRetencao().getRetencaoHeader().setIdRespostaDestino(getDadosClienteForm().getIdDestinoPrevisto());
        }

        if (finalizaRetencaoVO.getRetencao().getRetencaoBody().getStatus() == null) {
            finalizaRetencaoVO.getRetencao().getRetencaoBody().addNewStatus();
        }

        if (finalizaRetencaoVO.getRetencao().getRetencaoBody().getStatus().getOfertasRealizadas() == null) {
            finalizaRetencaoVO.getRetencao().getRetencaoBody().getStatus().addNewOfertasRealizadas();
            if (getListaOfertasAceitasVO().sizeOfItemListaDescricaoVOArray() > 0) {
                finalizaRetencaoVO.getRetencao().getRetencaoBody().getStatus().setIdOfertaAceita(getListaOfertasAceitasVO().getItemListaDescricaoVOArray(0).getId());
                finalizaRetencaoVO.getRetencao().getRetencaoBody().getStatus().setSgOfertaAceita(getListaOfertasAceitasVO().getItemListaDescricaoVOArray(0).getSigla());
            }
            if (getListaOfertasRealizadasVO().getItemListaDescricaoVOArray().length > 0) {
                for (int x = 0; x < getListaOfertasRealizadasVO().getItemListaDescricaoVOArray().length; x++) {
                    finalizaRetencaoVO.getRetencao().getRetencaoBody().getStatus().getOfertasRealizadas().addIdOfertaRealizada(getListaOfertasRealizadasVO().getItemListaDescricaoVOArray(x).getId());
                }
            }
        }
        return finalizaRetencaoVO;
    }

    /**
     * Envia dados da Oferta Aparelho e retorna mensagem do Sistema Legado SAP
     * 
     * @param idUsuario
     *            String
     * @param idRetencao
     *            String
     * @param form
     *            OfertaAparelhoForm
     * @return string
     */
    private RetencaoRetornoSAPVO enviarDadosSAP(String idUsuario, String idRetencao, OfertaAparelhoForm form, HttpServletRequest request) throws Exception {
        log.debug("FidelizacaoController:enviarDadosSAP()");
        try {
            RetencaoSAPVO rSAPVO = RetencaoSAPVO.Factory.newInstance();
            CLIENTE sapCliente = CLIENTE.Factory.newInstance();
            ENDERECO sapEndereco = ENDERECO.Factory.newInstance();
            PRODUTO sapProduto = PRODUTO.Factory.newInstance();

            log.debug("FidelizacaoController:enviarDadosSAP()->idUsuario=" + idUsuario);
            log.debug("FidelizacaoController:enviarDadosSAP()->idRetencao=" + idRetencao);
            log.debug("FidelizacaoController:enviarDadosSAP()->form=" + form.toString());

            // inExecutar 0-Procedimento normal; 1->Baixa; 2-Estorno;
            rSAPVO.setInExecutar(ConstantesCRM.SZERO);

            // inRetornoOld 0-Procedimento normal; 1->Estoque ja realizado
            rSAPVO.setInRetornoOld(ConstantesCRM.SZERO);

            sapCliente.setIDPESSOA(getParametrosVO().getIdPessoaCliente());
            log.debug("FidelizacaoController:enviarDadosSAP()->idPessoaCliente=" + getParametrosVO().getIdPessoaCliente());
            sapCliente.setIDGRUPO(getParametrosVO().getIdGrupo());
            log.debug("FidelizacaoController:enviarDadosSAP()->idGrupo=" + getParametrosVO().getIdGrupo());
            sapCliente.setDOCUMENTO(form.getRgContato());
            log.debug("FidelizacaoController:enviarDadosSAP()->rgContato=" + form.getRgContato());
            sapCliente.setNOME(getDadosClienteForm().getNomeCliente());
            log.debug("FidelizacaoController:enviarDadosSAP()->NomeCliente=" + getDadosClienteForm().getNomeCliente());
            sapCliente.setTELEFONE(getDadosClienteForm().getNumeroLinha());
            log.debug("FidelizacaoController:enviarDadosSAP()->getNumeroLinha=" + getDadosClienteForm().getNumeroLinha());
            sapCliente.setTIPODOC("RG");
            sapCliente.setIDSEGMENTACAO(getDadosClienteForm().getIdSegmentaco());
            log.debug("FidelizacaoController:enviarDadosSAP()->getIdSegmentaco=" + getDadosClienteForm().getIdSegmentaco());
            sapCliente.setIDTIPOCARTEIRA(getParametrosVO().getIdTipoCarteira());
            sapCliente.setSGTIPOPESSOA(getParametrosVO().getInTipoPessoa());

            sapEndereco.setNOME(getDadosClienteForm().getNomeCliente());
            log.debug("FidelizacaoController:enviarDadosSAP()->getNomeCliente=" + getDadosClienteForm().getNomeCliente());
            sapEndereco.setRUACLI(new StringBuffer(form.getRua()).append(", ").append(form.getNumero()).append(testarValor(form.getComplemento()) ? " - " : ConstantesCRM.SVAZIO).append(testarValor(form.getComplemento()) ? form.getComplemento() : ConstantesCRM.SVAZIO).toString());
            sapEndereco.setBAIRROCLI(form.getBairro());
            log.debug("FidelizacaoController:enviarDadosSAP()->getBairro=" + form.getBairro());
            sapEndereco.setCIDADECLI(form.getCidade());
            log.debug("FidelizacaoController:enviarDadosSAP()->getCidade=" + form.getCidade());
            sapEndereco.setCEPCLI(form.getCep());
            log.debug("FidelizacaoController:reterAparelho()->getCep=" + form.getCep());
            sapEndereco.setESTADOCLI(form.getIdUFSelecionado());
            log.debug("FidelizacaoController:enviarDadosSAP()->getIdUFSelecionado=" + form.getIdUFSelecionado());
            sapEndereco.setPAISCLI(ConstantesCRM.SONE);

            sapEndereco.setBAIRROENT(form.getBairro());
            log.debug("FidelizacaoController:enviarDadosSAP()->getBairro=" + form.getBairro());
            sapEndereco.setCEPENT(form.getCep());
            log.debug("FidelizacaoController:enviarDadosSAP()->getCep=" + form.getCep());
            sapEndereco.setCIDADEENT(form.getCidade());
            log.debug("FidelizacaoController:enviarDadosSAP()->getCidade=" + form.getCidade());
            sapEndereco.setESTADOENT(form.getIdUFSelecionado());
            log.debug("FidelizacaoController:enviarDadosSAP()->getIdUFSelecionado=" + form.getIdUFSelecionado());
            sapEndereco.setPAISENT(ConstantesCRM.SONE);
            sapEndereco.setRUAENT(sapEndereco.getRUACLI());

            // sapProduto.setIDAPARELHO (form.getIdAparelho());
            sapProduto.setIDAPARELHO(form.getIdAparelhoCor());
            log.debug("FidelizacaoController:enviarDadosSAP()->getIdAparelhoCor=" + form.getIdAparelhoCor());
            sapProduto.setCONDPGTO(form.getParcelaSelecionada());
            log.debug("FidelizacaoController:enviarDadosSAP()->getParcelaSelecionada=" + form.getParcelaSelecionada());
            sapProduto.setDATAREMESSA(new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date()));
            log.debug("FidelizacaoController:enviarDadosSAP()->getDtRetirada=" + form.getDtRetirada());
            sapProduto.setMEIOPAGTO(getConsultaMatrizOfertaForm().getMeioPagamento());
            log.debug("FidelizacaoController:enviarDadosSAP()->getMeioPagamento=" + getConsultaMatrizOfertaForm().getMeioPagamento());
            sapProduto.setPRZVIGENCIA(form.getPrzVigencia());
            sapProduto.setSGOFERTA(getConsultaMatrizOfertaForm().getSgOfertaAceita());

            if (ConstantesCRM.SONE.equals(request.getSession().getAttribute("inAdimplente"))) {
                sapProduto.setNUMEROPEDIDO(ConstantesCRM.SVAZIO);
            } else {
                sapProduto.setNUMEROPEDIDO(idRetencao);
            }

            sapProduto.setQUANTIDADE(ConstantesCRM.SONE);
            sapProduto.setVALOR(ConstantesCRM.STHREE.equals(getConsultaMatrizOfertaForm().getMeioPagamento()) ? ConstantesCRM.SVAZIO : isEmpty(form.getVlDesconto()) ? ConstantesCRM.SVAZIO : formatarDecimal(form.getVlDesconto(), 1));
            log.debug("FidelizacaoController:enviarDadosSAP()->valor=" + form.getVlDesconto());
            sapProduto.setMATERIAL(form.getSAP());

            log.debug("FidelizacaoController:enviarDadosSAP()->getSAP=" + form.getSAP());

            sapProduto.setHORAMANHAINI(ConstantesCRM.SVAZIO);
            sapProduto.setHORAMANHAFIM(ConstantesCRM.SVAZIO);
            sapProduto.setHORATARDEINI(ConstantesCRM.SVAZIO);
            sapProduto.setHORATARDEFIM(ConstantesCRM.SVAZIO);
            sapProduto.setPONTOS(ConstantesCRM.SVAZIO);
            sapProduto.setOBSERVACAO(testarValor(form.getNmContato()) ? new StringBuffer(form.getNmContato()).append(";RG:").append(form.getRgContato()).append(";").append(form.getTelContato()).toString() : ConstantesCRM.SVAZIO);
            sapProduto.setLINHA(getDadosClienteForm().getDetalheLinha().getNumero());
            log.debug("FidelizacaoController:reterAparelho()->inExcecao=" + getOfertaAparelhoForm().getInExcecao());

            log.debug("FidelizacaoController:reterAparelho()->semSimCard=" + form.isSemSimCard());

            if (form.isSemSimCard()) {
                sapProduto.setSEMSIMCARD(ConstantesCRM.SONE);
            } else {
                sapProduto.setSEMSIMCARD(ConstantesCRM.SZERO);
            }

            rSAPVO.setCLIENTE(sapCliente);
            rSAPVO.setENDERECO(sapEndereco);
            rSAPVO.setPRODUTO(sapProduto);

            return ofertaAparelhoFac.getComSAP(idUsuario, rSAPVO);
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:enviarDadosSAP()", e);
            RetencaoRetornoSAPVO retorno = RetencaoRetornoSAPVO.Factory.newInstance();
            retorno.setErro(ConstantesCRM.SZERO);
            retorno.setDescErro("SAP indisponÌvel, por favor tentar via sistema legado.");
            return retorno;
        }
    }

    private String removeAcentuacaoXML(String xmlInput, String nomeVO, String pacote) {
        String retornoXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        retornoXML += "<" + nomeVO + " xmlns=\"" + pacote + "\">";
        /*
         * xmlInput =
         * xmlInput.replaceAll("[‰·‡‚„™]","a").replaceAll("[¡¿¬√ƒ]","A"
         * ).replaceAll("[ÈËÍÎ]","e") .replaceAll(
         * "[…» À]","E").replaceAll("[ÌÏ]"
         * ,"i").replaceAll("[ÕÃ]","I").replaceAll("[ÛÚÙıˆ∫]","o").replaceAll(
         * "[”“‘’÷]","O")
         * .replaceAll("[˙˘˚¸]","u").replaceAll("[⁄U€‹]","U").replaceAll
         * ("«","C").replaceAll("Á","c").replaceAll ("Ò","n")
         * .replaceAll("—","N").replaceAll("&","E").replaceAll(
         * "[\\!\\@\\#\\$\\®\\%\\*\\(\\)\\+\\;\\?\\~\\^\\{\\}\\¥\\`\\~\\^]",ConstantesCRM.SVAZIO);
         */
        xmlInput = xmlInput.replaceAll("<xmlfragment>", ConstantesCRM.SVAZIO);
        xmlInput = xmlInput.replaceAll("</xmlfragment>", ConstantesCRM.SVAZIO);
        xmlInput = xmlInput.replaceAll("vo:", ConstantesCRM.SVAZIO);

        retornoXML += xmlInput;
        retornoXML += "</" + nomeVO + ">";
        return retornoXML;
    }

    private String removeCaracteresEspeciais(String input) {
        String stringTrat = input.replaceAll("[‰·‡‚„™]", "a").replaceAll("[¡¿¬√ƒ]", "A").replaceAll("[ÈËÍÎ]", "e").replaceAll("[…» À]", "E").replaceAll("[ÌÏ]", "i").replaceAll("[ÕÃ]", "I").replaceAll("[ÛÚÙıˆ∫]", "o").replaceAll("[”“‘’÷]", "O")
                .replaceAll("[˙˘˚¸]", "u").replaceAll("[⁄U€‹]", "U").replaceAll("«", "C").replaceAll("Á", "c").replaceAll("Ò", "n").replaceAll("—", "N").replaceAll("&", "E")
                .replaceAll("[\\!\\@\\#\\$\\®\\%\\*\\(\\)\\-\\_\\=\\+\\,\\<\\.\\>\\;\\:\\/\\?\\~\\^\\{\\}\\¥\\`\\~\\^]", ConstantesCRM.SVAZIO);
        return stringTrat;
    }

    private void removeAcentuacao(OfertaAparelhoForm form) {
        StringBuffer camposForm = new StringBuffer();
        camposForm.append(form.getRua()).append("|").append(form.getNumero()).append("|").append(form.getCidade()).append("|").append(form.getComplemento()).append("|").append(form.getIdUFSelecionado()).append("|").append(form.getBairro())
                .append("|").append(form.getCep()).append("|").append(form.getNmContato()).append("|").append(getDadosClienteForm().getNomeCliente());

        String camposFormStr = camposForm.toString().replaceAll("[‰·‡‚„™]", "a").replaceAll("[¡¿¬√ƒ]", "A").replaceAll("[ÈËÍÎ]", "e").replaceAll("[…» À]", "E").replaceAll("[ÌÏ]", "i").replaceAll("[ÕÃ]", "I").replaceAll("[ÛÚÙıˆ∫]", "o")
                .replaceAll("[”“‘’÷]", "O").replaceAll("[˙˘˚¸]", "u").replaceAll("[⁄U€‹]", "U").replaceAll("«", "C").replaceAll("Á", "c").replaceAll("Ò", "n").replaceAll("—", "N").replaceAll("&", "E")
                .replaceAll("[\\!\\@\\#\\$\\®\\%\\*\\(\\)\\-\\_\\=\\+\\,\\<\\.\\>\\;\\:\\/\\?\\~\\^\\{\\}\\¥\\`\\~\\^]", ConstantesCRM.SVAZIO);

        camposFormStr = removeDuplicateWhitespace(camposFormStr).toString();
        camposFormStr.trim();

        String[] campos = camposFormStr.split("\\|");
        form.setRua(campos[0]);
        form.setNumero(campos[1]);
        form.setCidade(campos[2]);
        form.setComplemento(campos[3]);
        form.setIdUFSelecionado(campos[4]);
        form.setBairro(campos[5]);
        form.setCep(campos[6]);
        form.setNmContato(campos[7]);
        getDadosClienteForm().setNomeCliente(campos[8]);
    }

    public static CharSequence removeDuplicateWhitespace(CharSequence inputStr) {
        String patternStr = "\\s+";
        String replaceStr = " ";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.replaceAll(replaceStr);
    }

    /**
     * Retorna qtde de pontos de determinada linha
     * 
     * @param nrLinha
     *            String
     * @return String
     */
    protected String getPontuacao(String nrLinha, HttpServletRequest request) throws Exception {
        log.debug("FidelizacaoController:getPontuacao()");
        try {
            LupaClienteVO abaPontos = LupaClienteVO.Factory.newInstance();
            abaPontos = telaInicialFac.getPontos(getIdUsuario(request), nrLinha);
            if (abaPontos.getDadosAbaLupaCliente().getContasPontuacaoArray(0).getErro() != null) {
                return "indisponÌvel";
            } else {
                return abaPontos.getDadosAbaLupaCliente().getContasPontuacaoArray(0).getSaldo();
            }
        } catch (Exception e) {
            log.error("Exception - FidelizacaoController:getPontuacao()", e);
            return "indisponÌvel";
        }
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
        log.debug("FidelizacaoController:formatarDecimal()");

        String pattern = "###,##0.00";
        char sepDecimal = ',';
        char sepGrupo = '.';

        // se a vari·vel estiver nula, retorna vazio
        if (valor == null) {
            valor = ConstantesCRM.SZERO;
        }

        // Formata no estilo 4999,00
        if (tipo == 1) {
            pattern = "##0.00";
            sepDecimal = '.';
            sepGrupo = ',';
            valor = valor.replace('.', 'X').replaceAll("X", ConstantesCRM.SVAZIO).replace(',', '.');
        } // Formata no estilo 4999.00
        else if (tipo == 2) {
            pattern = "##0.00";
            sepDecimal = '.';
            sepGrupo = ' ';
            valor = valor.replace('.', 'X').replaceAll("X", ConstantesCRM.SVAZIO).replace('.', ',');
        }

        DecimalFormatSymbols dSymbols = new DecimalFormatSymbols();
        dSymbols.setDecimalSeparator(sepDecimal);
        dSymbols.setGroupingSeparator(sepGrupo);

        DecimalFormat dFormat = new DecimalFormat("00", dSymbols);

        dFormat.isDecimalSeparatorAlwaysShown();
        dFormat.applyPattern(pattern + ";(" + pattern + ")");

        return dFormat.format(new Double(valor));
    }

    @SuppressWarnings("unused")
    private void abrirProtocolo(HttpServletRequest request) throws Exception {

        AbreProtocoloTO abreProtocoloTO = AbreProtocoloTO.Factory.newInstance();
        String msg = ConstantesCRM.SVAZIO;
        String nrLinha = getParametrosVO().getNrLinha();

        abreProtocoloTO.setIdPessoaDePara(getParametrosVO().getIdClienteDePara());
        abreProtocoloTO.setCdAreaRegistro(nrLinha.substring(0, 2));
        abreProtocoloTO.setCdAreaRegistroSMS(nrLinha.substring(0, 2));
        abreProtocoloTO.setNrTelefone(nrLinha.substring(2));
        abreProtocoloTO.setNrTelefoneSMS(nrLinha.substring(2));
        abreProtocoloTO.setIdTipoAberturaProtocolo(ConstantesCRM.STHREE);
        abreProtocoloTO.setIdSistemaOrigem(Integer.toString(7));

        AbreProtocoloOutTO abreProtocoloOutTO = protocoloFacade.setAbreValidaProtocolo(getIdUsuario(request), abreProtocoloTO);

        if (ConstantesCRM.SZERO.equals(abreProtocoloOutTO.getCdError())) {
            request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, abreProtocoloOutTO.getNrProtocolo());
            getParametrosVO().setNrProtocolo(abreProtocoloOutTO.getNrProtocolo());
            request.setAttribute("msgNovoProtocolo", "O n˙mero de protocolo informado È inv·lido. Um novo protocolo - " + abreProtocoloOutTO.getNrProtocolo() + " - foi gerado.");
        }

    }

    /************************************* METODOS SAIR E DONE ************************************************/
    /***********************************************************************************************************/
    /**
     * @jpf:action
     * @jpf:forward name="finalizar" path="Finaliza.jsp"
     * @jpf:forward name="inicio" path="begin.do"
     */
    protected ActionForward sair(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        log.debug("FidelizacaoController:sair()");
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward("inicio");
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="begin.do"
     */
    protected ActionForward ConsultaMatrizOfertaDone(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        log.debug("FidelizacaoController:ConsultaMatrizOfertaDone()");
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="getIntencaoCancelamento.do"
     */
    protected ActionForward QuestionarioDone(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

        log.debug("FidelizacaoController:QuestionarioDone()");
        setDadosClienteForm((DadosClienteForm) request.getAttribute("retencao_DadosClienteForm"));
        setActionRetencaoForm((ActionRetencaoForm) request.getAttribute("retencao_ActionRetencaoForm"));
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /************************************* FORMULARIOS *******************************************************/
    /***********************************************************************************************************/
    /**
     * ACTION RETENCAO FORM
     */
    public static class ActionRetencaoForm extends ActionRetencaoFormBean {
        private static final long serialVersionUID = -8420951899495794279L;
    }

    public ActionRetencaoForm getActionRetencaoForm() {
        if (actionRetencaoForm == null) {
            actionRetencaoForm = new ActionRetencaoForm();
        }
        return actionRetencaoForm;
    }

    public void setActionRetencaoForm(ActionRetencaoForm _actionRetencaoForm) {
        this.actionRetencaoForm = _actionRetencaoForm;
    }

    /*******************************************************************************************************************/
    /**
     * SHOW DETALHE HISTORICO FORM
     */
    public static class ShowDetalheHistoricoForm extends ShowDetalheHistoricoFormBean {

        private static final long serialVersionUID = 7553950405394889853L;
    }

    public ShowDetalheHistoricoForm getShowDetalheHistoricoForm() {
        log.debug("FidelizacaoController:getShowDetalheHistoricoForm()");
        if (showDetalheHistoricoForm == null) {
            showDetalheHistoricoForm = new ShowDetalheHistoricoForm();
        }
        return showDetalheHistoricoForm;
    }

    public void setShowDetalheHistoricoForm(ShowDetalheHistoricoForm _showDetalheHistoricoForm) {
        log.debug("FidelizacaoController:setShowDetalheHistoricoForm()");
        this.showDetalheHistoricoForm = _showDetalheHistoricoForm;
    }

    /*******************************************************************************************************************/
    /**
     * PESQUISA DESTINO FORM
     */
    public static class PesquisaDestinoPrevistoForm extends PesquisaDestinoPrevistoFormBean {

        private static final long serialVersionUID = -974397062853706859L;
    }

    public PesquisaDestinoPrevistoForm getPesquisaDestinoPrevistoForm() {
        log.debug("FidelizacaoController:getPesquisaDestinoPrevistoForm()");
        if (pesquisaDestinoPrevistoForm == null) {
            pesquisaDestinoPrevistoForm = new PesquisaDestinoPrevistoForm();
        }
        return pesquisaDestinoPrevistoForm;
    }

    public void setPesquisaDestinoPrevistoForm(PesquisaDestinoPrevistoForm _pesquisaDestinoPrevistoForm) {
        log.debug("FidelizacaoController:setPesquisaDestinoPrevistoForm()");
        pesquisaDestinoPrevistoForm = _pesquisaDestinoPrevistoForm;
    }

    /*******************************************************************************************************************/
    /**
     * DADOS CLIENTE FORM
     */
    public static class DadosClienteForm extends DadosClienteFormBean {

        private static final long serialVersionUID = -6790676525269712162L;
    }

    public DadosClienteForm getDadosClienteForm() {
        log.debug("FidelizacaoController:getDadosClienteForm()");
        if (dadosClienteForm == null) {
            dadosClienteForm = new DadosClienteForm();
        }
        return this.dadosClienteForm;
    }

    public void setDadosClienteForm(DadosClienteForm _dadosClienteForm) {
        log.debug("FidelizacaoController:setDadosClienteForm()");
        this.dadosClienteForm = _dadosClienteForm;
    }

    /*******************************************************************************************************************/
    /**
     * SHOW MATRIZ OFERTA FORM
     */
    public static class ShowMatrizOfertaForm extends ShowMatrizOfertaFormBean {

        private static final long serialVersionUID = -7755028345866288141L;
    }

    public ShowMatrizOfertaForm getShowMatrizOfertaForm() {
        log.debug("FidelizacaoController:getShowMatrizOfertaForm()");
        if (showMatrizOfertaForm == null) {
            showMatrizOfertaForm = new ShowMatrizOfertaForm();
        }
        return showMatrizOfertaForm;
    }

    public void setShowMatrizOfertaForm(ShowMatrizOfertaForm _showMatrizOfertaForm) {
        log.debug("FidelizacaoController:setShowMatrizOfertaForm()");
        this.showMatrizOfertaForm = _showMatrizOfertaForm;
    }

    /*******************************************************************************************************************/
    /**
     * CONSULTA MATRIZ OFERTA FORM
     */
    public static class ConsultaMatrizOfertaForm extends ConsultaMatrizOfertaFormBean {

        private static final long serialVersionUID = -3931535324348044669L;
    }

    public ConsultaMatrizOfertaForm getConsultaMatrizOfertaForm() {
        log.debug("FidelizacaoController:getConsultaMatrizOfertaForm()");
        if (consultaMatrizOfertaForm == null) {
            consultaMatrizOfertaForm = new ConsultaMatrizOfertaForm();
        }
        return consultaMatrizOfertaForm;
    }

    public void setConsultaMatrizOfertaForm(ConsultaMatrizOfertaForm form) {
        log.debug("FidelizacaoController:setConsultaMatrizOfertaForm()");
        consultaMatrizOfertaForm = form;
    }

    /*******************************************************************************************************************/
    /**
     * CADASTRAR BONUS FORM
     */
    public static class CadastrarBonusForm extends CadastrarBonusFormBean {

        private static final long serialVersionUID = -1876776718470579379L;
    }

    public CadastrarBonusForm getCadastrarBonusForm() {
        log.debug("FidelizacaoController:getCadastrarBonusForm()");
        if (cadastrarBonusForm == null) {
            cadastrarBonusForm = new CadastrarBonusForm();
        }
        return cadastrarBonusForm;
    }

    /*******************************************************************************************************************/
    /**
     * CADASTRAR MIGRACAO FORM
     */
    public static class CadastrarMigracaoForm extends CadastrarMigracaoFormBean {

        private static final long serialVersionUID = -1411432014577566937L;
    }

    public CadastrarMigracaoForm getCadastrarMigracaoForm() {
        log.debug("FidelizacaoController:getCadastrarMigracaoForm()");
        if (cadastrarMigracaoForm == null) {
            cadastrarMigracaoForm = new CadastrarMigracaoForm();
        }
        return cadastrarMigracaoForm;
    }

    public void setCadastrarMigracaoForm(CadastrarMigracaoForm form) {
        log.debug("FidelizacaoController:setCadastrarMigracaoForm()");
        cadastrarMigracaoForm = form;
    }

    /*******************************************************************************************************************/
    /**
     * SUSPENSAO TEMPORARIA FORM
     */
    public static class SuspensaoTemporariaForm extends SuspensaoTemporariaFormBean {

        private static final long serialVersionUID = -6944617410077526833L;
    }

    public SuspensaoTemporariaForm getSuspensaoTemporariaForm() {
        log.debug("FidelizacaoController:getSuspensaoTemporariaForm()");
        if (suspensaoTemporariaForm == null) {
            suspensaoTemporariaForm = new SuspensaoTemporariaForm();
        }
        return suspensaoTemporariaForm;
    }

    public void setSuspensaoTemporariaForm(SuspensaoTemporariaForm form) {
        log.debug("FidelizacaoController:setSuspensaoTemporariaForm()");
        suspensaoTemporariaForm = form;
    }

    /*******************************************************************************************************************/
    public static class CancelarLinhasForm extends CancelarLinhasFormBean {

        private static final long serialVersionUID = 3668462857901154001L;
    }

    public CancelarLinhasForm getCancelarLinhasForm() {
        log.debug("FidelizacaoController:getCancelarLinhasForm()");
        if (cancelarLinhasForm == null) {
            cancelarLinhasForm = new CancelarLinhasForm();
        }
        return cancelarLinhasForm;
    }

    public void setCancelarLinhasForm(CancelarLinhasForm form) {
        log.debug("FidelizacaoController:setCancelarLinhasForm()");
        cancelarLinhasForm = form;
    }

    /**
     * OFERTA APARELHO FORM
     */
    public static class OfertaAparelhoForm extends OfertaAparelhoFormBean {

        private static final long serialVersionUID = -3545384586404236250L;
    }

    public OfertaAparelhoForm getOfertaAparelhoForm() {
        log.debug("FidelizacaoController:getOfertaAparelhoForm()");
        if (ofertaAparelhoForm == null) {
            ofertaAparelhoForm = new OfertaAparelhoForm();
        }
        return ofertaAparelhoForm;
    }

    public void setOfertaAparelhoForm(OfertaAparelhoForm form) {
        log.debug("FidelizacaoController:setOfertaAparelhoForm()");
        ofertaAparelhoForm = form;
    }

    /**
     * Consulta Adimplencia Form
     */
    public static class ConsultaAdimplenciaForm extends ConsultaAdimplenciaFormBean {

        private static final long serialVersionUID = 4978698468017853696L;
    }

    public ConsultaAdimplenciaForm getConsultaAdimplenciaForm() {
        log.debug("FidelizacaoController:getConsultaAdimplenciaForm()");
        if (consultaAdimplenciaForm == null) {
            consultaAdimplenciaForm = new ConsultaAdimplenciaForm();
        }
        return consultaAdimplenciaForm;
    }

    public void setConsultaAdimplenciaForm(ConsultaAdimplenciaForm consultaAdimplenciaForm) {
        log.debug("FidelizacaoController:setConsultaAdimplenciaForm()");
        this.consultaAdimplenciaForm = consultaAdimplenciaForm;
    }

    private String limitaCaracteres(String argumento, int qtdeCaracteres) {
        if (argumento.length() > qtdeCaracteres) {
            return argumento.substring(0, qtdeCaracteres);
        }
        return argumento;
    }
}
