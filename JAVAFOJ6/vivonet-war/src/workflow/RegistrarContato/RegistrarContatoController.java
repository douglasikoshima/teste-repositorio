package workflow.RegistrarContato;

import java.io.BufferedOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import br.com.vivo.fo.admsistemas.vo.AdmCamposFormularioVODocument.AdmCamposFormularioVO;
import br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaVODocument.AdmContatoFolhaVO;
import br.com.vivo.fo.admsistemas.vo.AdmFolhaBaixaVODocument.AdmFolhaBaixaVO;
import br.com.vivo.fo.admsistemas.vo.AdmGrupoCamposDependentesVODocument.AdmGrupoCamposDependentesVO;
import br.com.vivo.fo.admsistemas.vo.AdmGrupoCamposVODocument.AdmGrupoCamposVO;
import br.com.vivo.fo.admsistemas.vo.ArvoreAtendimentoVODocument.ArvoreAtendimentoVO;
import br.com.vivo.fo.admsistemas.vo.CarterizacaoVODocument.CarterizacaoVO;
import br.com.vivo.fo.admsistemas.vo.FormularioCampoVODocument.FormularioCampoVO;
import br.com.vivo.fo.admsistemas.vo.FormularioCampoValorVODocument.FormularioCampoValorVO;
import br.com.vivo.fo.admsistemas.vo.FormularioProcessoVODocument.FormularioProcessoVO;
import br.com.vivo.fo.admsistemas.vo.FormularioVODocument.FormularioVO;
import br.com.vivo.fo.admsistemas.vo.ProcedenciaVODocument.ProcedenciaVO;
import br.com.vivo.fo.admsistemas.vo.SegmentacaoVODocument.SegmentacaoVO;
import br.com.vivo.fo.cliente.ClienteUtils;
import br.com.vivo.fo.cliente.vo.AjaxErrorHandlerVODocument.AjaxErrorHandlerVO;
import br.com.vivo.fo.cliente.vo.ComunicacaoVODocument.ComunicacaoVO;
import br.com.vivo.fo.cliente.vo.EnderecoVODocument.EnderecoVO;
import br.com.vivo.fo.cliente.vo.ListaTipoComunicacaoVODocument.ListaTipoComunicacaoVO;
import br.com.vivo.fo.cliente.vo.LupaClienteVODocument.LupaClienteVO;
import br.com.vivo.fo.cliente.vo.LupaLinhaVODocument.LupaLinhaVO;
import br.com.vivo.fo.cliente.vo.LupaLinhaVODocument.LupaLinhaVO.FiltroLinhaVO;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.cliente.vo.TipoComunicacaoVODocument.TipoComunicacaoVO;
import br.com.vivo.fo.cliente.vo.TipoEnderecoVODocument.TipoEnderecoVO;
import br.com.vivo.fo.cliente.vo.UsuarioLinhaVODocument.UsuarioLinhaVO;
import br.com.vivo.fo.commons.utils.EscapeUnescape;
import br.com.vivo.fo.commons.utils.StringUtil;
import br.com.vivo.fo.commons.utils.StringUtilStatic;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.admsistemas.camposFormulario.CamposFormulario;
import br.com.vivo.fo.ctrls.cliente.telaInicial.TelaInicialFacade;
import br.com.vivo.fo.ctrls.workflow.RAtendimento.RAtendimento;
import br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.AtendimentoWorkflowFacade;
import br.com.vivo.fo.ctrls.workflow.registrarContato.RegistrarContatoFacade;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.UsuarioVIVODocument.UsuarioVIVO;
import br.com.vivo.fo.workflow.vo.AtdWFVODocument.AtdWFVO;
import br.com.vivo.fo.workflow.vo.AtendimentoArvoreFiltrosVODocument.AtendimentoArvoreFiltrosVO;
import br.com.vivo.fo.workflow.vo.AtendimentoComunicacaoVODocument.AtendimentoComunicacaoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoRetornoVODocument.AtendimentoRetornoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoSituacaoVODocument.AtendimentoSituacaoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoTipoComunicacaoVODocument.AtendimentoTipoComunicacaoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO.AtendimentosCorrentes;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO.Contas;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO.LinhasAssociadasVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowComumVODocument.AtendimentoWorkflowComumVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowVODocument.AtendimentoWorkflowVO;
import br.com.vivo.fo.workflow.vo.CanalVODocument.CanalVO;
import br.com.vivo.fo.workflow.vo.ContaVODocument.ContaVO;
import br.com.vivo.fo.workflow.vo.FechamentoAtendimentoVODocument.FechamentoAtendimentoVO;
import br.com.vivo.fo.workflow.vo.InformeBuscaVODocument.InformeBuscaVO;
import br.com.vivo.fo.workflow.vo.LinhaVODocument.LinhaVO;
import br.com.vivo.fo.workflow.vo.ListaDadosVODocument.ListaDadosVO;
import br.com.vivo.fo.workflow.vo.PessoaVODocument.PessoaVO;
import br.com.vivo.fo.workflow.vo.RDContatoVODocument.RDContatoVO;
import br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO;
import br.com.vivo.fo.workflow.vo.WFGrupoVODocument.WFGrupoVO;
import br.com.vivo.fo.workflow.vo.WFListaContatosVODocument.WFListaContatosVO;
import br.com.vivo.fo.workflow.vo.WFMotivoVODocument.WFMotivoVO;
import br.com.vivo.fo.workflow.vo.impl.AtendimentoComunicacaoVODocumentImpl.AtendimentoComunicacaoVOImpl;
import br.com.vivo.fo.workflow.vo.impl.AtendimentoRetornoVODocumentImpl.AtendimentoRetornoVOImpl;
import br.com.vivo.fo.workflow.vo.impl.AtendimentoTipoComunicacaoVODocumentImpl.AtendimentoTipoComunicacaoVOImpl;
import br.com.vivo.fo.workflow.vo.impl.AtendimentoVODocumentImpl.AtendimentoVOImpl;
import br.com.vivo.fo.workflow.vo.impl.LinhaVODocumentImpl.LinhaVOImpl;
import com.indracompany.actions.AbstractAction;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class RegistrarContatoController extends AbstractAction {

    private static final long         serialVersionUID                     = -62258687932515260L;

    @EJB
    private RegistrarContatoFacade    registrarContatoFacade;

    @EJB
    private TelaInicialFacade         lupaClienteFacadeControl;

    @EJB
    private AtendimentoWorkflowFacade atendimentoWorkflowFacade;

    @EJB
    private RAtendimento              rAtendimentoFacade;

    @EJB
    private TelaInicialFacade         telaInicialFacadeControl;

    @EJB
    private CamposFormulario          camposFormularioTux;

    private static Logger             log                                  = new Logger("workflow");

    // variáveis e objetos
    private RegistrarContatoForm      registrarContatoForm                 = new RegistrarContatoForm();
    private AtendimentoVO             atendimentoVOFacade                  = null;
    private AtendimentoVO             atendimentoFechamentoVO              = AtendimentoVO.Factory.newInstance();
    private LupaClienteVO             lupaCliente                          = null;
    private ComunicacaoVO[]           contato                              = null;
    private AtendimentoVO             atendimentoVOLista                   = null;
    private boolean                   inBegin                              = true;
    private final int                 IDTIPOABERTURAPROTOCOLO_LINHACLIENTE = 3;
    private final int                 IDTIPOABERTURAPROTOCOLO_PESSOA       = 4;
    private final int                 IDTIPOABERTURAPROTOCOLO_CONTA        = 1;
    private final int                 IDTIPOABERTURAPROTOCOLO_LINHA        = 2;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if ("begin".equals(mapping.getParameter())) {
            return begin(mapping, form, request, response);
        
        } else if ("beginTeste".equals(mapping.getParameter())) {
            return beginTeste(mapping, form, request, response);
        
        } else if ("expandeArvoreContato".equals(mapping.getParameter())) {
            return expandeArvoreContato(mapping, form, request, response);
        
        } else if ("iniciaAtendimento".equals(mapping.getParameter())) {
            return iniciaAtendimento(mapping, form, request, response);
        
        } else if ("getLinhasByConta".equals(mapping.getParameter())) {
            return getLinhasByConta(mapping, form, request, response);
        
        } else if ("getContaByLinha".equals(mapping.getParameter())) {
            return getContaByLinha(mapping, form, request, response);
        
        } else if ("getDadosAparelhos".equals(mapping.getParameter())) {
            return getDadosAparelhos(mapping, form, request, response);
        
        } else if ("pesquisaArvore".equals(mapping.getParameter())) {
            return pesquisaArvore(mapping, form, request, response);
        
        } else if ("registrarInsistencia".equals(mapping.getParameter())) {
            return registrarInsistencia(mapping, form, request, response);
        
        } else if ("registrarComentario".equals(mapping.getParameter())) {
            return registrarComentario(mapping, form, request, response);
        
        } else if ("obterLinhas".equals(mapping.getParameter())) {
            return obterLinhas(mapping, form, request, response);
        
        } else if ("obterPesquisaFormulario".equals(mapping.getParameter())) {
            return obterPesquisaFormulario(mapping, form, request, response);
        
        } else if ("obterComunicacao".equals(mapping.getParameter())) {
            return obterComunicacao(mapping, form, request, response);
        
        } else if ("excluirProcessoCorrente".equals(mapping.getParameter())) {
            return excluirProcessoCorrente(mapping, form, request, response);
        
        } else if ("encaminharProcesso".equals(mapping.getParameter())) {
            return encaminharProcesso(mapping, form, request, response);
        
        } else if ("fechamentoProcesso".equals(mapping.getParameter())) {
            return fechamentoProcesso(mapping, form, request, response);
        
        } else if ("listarProcessos".equals(mapping.getParameter())) {
            return listarProcessos(mapping, form, request, response);
        
        } else if ("obterArvoreBaixa".equals(mapping.getParameter())) {
            return obterArvoreBaixa(mapping, form, request, response);
        
        } else if ("obtemArvoreBaixaParte".equals(mapping.getParameter())) {
            return obtemArvoreBaixaParte(mapping, form, request, response);
        
        } else if ("obterTipoComunicacao".equals(mapping.getParameter())) {
            return obterTipoComunicacao(mapping, form, request, response);
        
        } else if ("registrarContatoDone".equals(mapping.getParameter())) {
            return registrarContatoDone(mapping, form, request, response);
        
        } else if ("AtendimentoDetalheDone".equals(mapping.getParameter())) {
            return AtendimentoDetalheDone(mapping, form, request, response);
        
        } else if ("retornoAbaSolicitante".equals(mapping.getParameter())) {
            return retornoAbaSolicitante(mapping, form, request, response);
        
        } else if ("retornoAba".equals(mapping.getParameter())) {
            return retornoAba(mapping, form, request, response);
        
        } else if ("ConsultaHexaDone".equals(mapping.getParameter())) {
            return ConsultaHexaDone(mapping, form, request, response);
        
        } else if ("pesquisaFormulario".equals(mapping.getParameter())) {
            return pesquisaFormulario(mapping, form, request, response);
        
        } else if ("arvoreContato".equals(mapping.getParameter())) {
            return arvoreContato(mapping, form, request, response);
        
        } else if ("getValoresProximoNivel".equals(mapping.getParameter())) {
            return getValoresProximoNivel(mapping, form, request, response);
        
        } else if ("loadContato".equals(mapping.getParameter())) {
            return loadContato(mapping, form, request, response);
        
        } else if ("controlarContato".equals(mapping.getParameter())) {
            return controlarContato(mapping, form, request, response);
        
        } else if ("salvarContato".equals(mapping.getParameter())) {
            return salvarContato(mapping, form, request, response);
        
        } else if ("atendimentosCorrentes".equals(mapping.getParameter())) {
            return atendimentosCorrentes(mapping, form, request, response);
        
        } else {
            return begin(mapping, form, request, response);
        }
    }

    public RegistrarContatoForm getRegistrarContatoForm() {
        return this.registrarContatoForm;
    }

    public AtendimentoVO getAtendimentoVOLista() {
        return this.registrarContatoForm.getAtendimentoVOLista();
    }

    protected global.Global globalApp = new global.Global();

    /**
     * @jpf:action
     * @jpf:forward name="success" path="AtendimentoFrame.jsp"
     */
    protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        RegistrarContatoForm form = (RegistrarContatoForm) formParam;
        log.debug("RegistrarContatoController:begin.do - Inicio do Metodo]");

        String user = ConstantesCRM.getCurrentUser(request.getSession());
        inBegin = true;

        // verifica se o atributo fila está preenchido
        if ((request.getAttribute("fila") != null) && (ConstantesCRM.SONE.equals(request.getAttribute("fila")))) {
            form.setFila(ConstantesCRM.SONE);
        }

        /*
         * PARÂMETROS DA TELA INICIAL
         */
        ParametrosVO parametros = null;
        parametros = (ParametrosVO) request.getSession().getAttribute("parametrosWF");

        if (parametros == null) {
            parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
        
        } else {
            request.getSession().removeAttribute("parametrosWF");
        }

        StringUtil strUtil = new StringUtil();

        // INRESPONSAVELABERTURA -> 1-Usuario; 2-Cliente; 3-Prospect
        if ((parametros.getIdTipoRelacionamento() != null) && (ConstantesCRM.SONE.equals(parametros.getIdTipoRelacionamento()))) {
            form.setIdPessoa(parametros.getIdPessoaUsuario() == null ? ConstantesCRM.SONE : parametros.getIdPessoaUsuario());
            log.debug("RegistrarContatoController:begin.do - [idPessoaUsuario = " + form.getIdPessoa() + "]");
        
        } else {
            form.setIdPessoa(parametros.getIdPessoaCliente() == null ? ConstantesCRM.SONE : parametros.getIdPessoaCliente());
            log.debug("RegistrarContatoController:begin.do - [idPessoaCliente = " + form.getIdPessoa() + "]");
        }

        form.setInResponsavelAbertura(parametros.getIdTipoRelacionamento() == null ? ConstantesCRM.SONE : parametros.getIdTipoRelacionamento());
        log.debug("RegistrarContatoController:begin.do - [InResponsavelAbertura = " + form.getInResponsavelAbertura() + "]");
        form.setIdGrupoAbertura(parametros.getIdGrupo() == null ? ConstantesCRM.SONE : parametros.getIdGrupo());
        log.debug("RegistrarContatoController:begin.do - [idGrupoAbertura = " + form.getIdGrupoAbertura() + "]");

        if ((parametros.getIdTipoRelacionamento() != null) && ((ConstantesCRM.SONE.equals(parametros.getIdTipoRelacionamento())) || (ConstantesCRM.STWO.equals(parametros.getIdTipoRelacionamento())))) {
            form.setIdSegmentacao(parametros.getIdSegmentacao() == null || ConstantesCRM.SVAZIO.equals(parametros.getIdSegmentacao()) ? ConstantesCRM.SONE : parametros.getIdSegmentacao());
            form.setIdTipoCarteira(parametros.getIdTipoCarteira() == null || ConstantesCRM.SVAZIO.equals(parametros.getIdTipoCarteira()) ? ConstantesCRM.SONE : parametros.getIdTipoCarteira());
            form.setIdTipoLinha(parametros.getIdTipoLinha() == null || ConstantesCRM.SVAZIO.equals(parametros.getIdTipoLinha()) ? ConstantesCRM.SONE : parametros.getIdTipoLinha());
            form.setIdUfOperadora(parametros.getIdUfOperadora() == null || ConstantesCRM.SVAZIO.equals(parametros.getIdUfOperadora()) ? ConstantesCRM.SONE : parametros.getIdUfOperadora());
        
        } else {
            form.setIdSegmentacao("11");
            form.setIdTipoCarteira("13");
            form.setIdTipoLinha(ConstantesCRM.STHREE);
            form.setIdUfOperadora("21");
        }

        log.debug("RegistrarContatoController:begin.do - [IdSegmentacao = " + form.getIdSegmentacao() + "]");
        log.debug("RegistrarContatoController:begin.do - [IdTipoCarteira = " + form.getIdTipoCarteira() + "]");
        log.debug("RegistrarContatoController:begin.do - [IdTipoLinha = " + form.getIdTipoLinha() + "]");
        log.debug("RegistrarContatoController:begin.do - [IdUfOperadora = " + form.getIdUfOperadora() + "]");

        form.setNomeContato(parametros.getNmFalandoCom() == null || ConstantesCRM.SVAZIO.equals(parametros.getNmFalandoCom()) ? ConstantesCRM.SVAZIO : parametros.getNmFalandoCom());
        form.setNomeContatoAlterado(parametros.getNmContato() == null || ConstantesCRM.SVAZIO.equals(parametros.getNmContato()) ? ConstantesCRM.SVAZIO : parametros.getNmContato());
        form.setIdChamadaTelefonica(Long.parseLong(parametros.getIdChamadaTelefonica() == null || ConstantesCRM.SVAZIO.equals(parametros.getIdChamadaTelefonica()) ? ConstantesCRM.SONE : parametros.getIdChamadaTelefonica()));
        form.setIdFase((parametros.getIdFase() == null || ConstantesCRM.SVAZIO.equals(parametros.getIdFase()) ? ConstantesCRM.SONE : parametros.getIdFase()));

        log.debug("RegistrarContatoController:begin.do - [nomeContato = " + form.getNomeContato() + "]");
        log.debug("RegistrarContatoController:begin.do - [nomeContatoAlterado = " + form.getNomeContatoAlterado() + "]");
        log.debug("RegistrarContatoController:begin.do - [IdChamadoTelefonica = " + form.getIdChamadaTelefonica() + "]");
        log.debug("RegistrarContatoController:begin.do - [IdFase = " + form.getIdFase() + "]");

        String telContato = ConstantesCRM.SVAZIO;
        if (parametros.getNrLinha() == null || ConstantesCRM.SVAZIO.equals(parametros.getNrLinha())) {
            if (parametros.getNrTelefone() == null || ConstantesCRM.SVAZIO.equals(parametros.getNrTelefone())) {
                telContato = ConstantesCRM.SVAZIO;
            } else {
                telContato = parametros.getNrTelefone();
            }
        
        } else {
            telContato = parametros.getNrLinha();
        }

        form.setTelContatoFrm(telContato);
        log.debug("RegistrarContatoController:begin.do - [telContatoFrm = " + form.getTelContatoFrm() + "]");

        if (!ConstantesCRM.SVAZIO.equals(telContato) && telContato.length() < 10) {
            telContato = ConstantesCRM.mask(telContato, "(##)###-####");
        } else if (!ConstantesCRM.SVAZIO.equals(telContato)) {
            telContato = ConstantesCRM.formatPhoneNumber(telContato);
        }

        form.setTelContato(telContato);
        log.debug("RegistrarContatoController:begin.do - [telContato = " + form.getTelContato() + "]");

        form.setNrConta(parametros.getNrConta() == null || ConstantesCRM.SVAZIO.equals(parametros.getNrConta()) ? ConstantesCRM.SVAZIO : parametros.getNrConta());
        form.setIdConta(parametros.getIdConta() == null || ConstantesCRM.SVAZIO.equals(parametros.getIdConta()) ? ConstantesCRM.SONE : parametros.getIdConta());
        form.setContaSelecionada(parametros.getIdConta() == null || ConstantesCRM.SVAZIO.equals(parametros.getIdConta()) ? ConstantesCRM.SONE : parametros.getIdConta());
        form.setIdLinha(parametros.getIdLinha() == null || ConstantesCRM.SVAZIO.equals(parametros.getIdLinha()) ? ConstantesCRM.SZERO : parametros.getIdLinha());
        form.setLinhaSelecionada(parametros.getIdLinha() == null || ConstantesCRM.SVAZIO.equals(parametros.getIdLinha()) ? ConstantesCRM.SZERO : parametros.getIdLinha());
        form.setIdClienteDePara(parametros.getIdClienteDePara() == null || ConstantesCRM.SZERO.equals(parametros.getIdClienteDePara()) ? ConstantesCRM.SONE : parametros.getIdClienteDePara());

        log.debug("RegistrarContatoController:begin.do - [nrConta = " + form.getNrConta() + "]");
        log.debug("RegistrarContatoController:begin.do - [idConta = " + form.getIdConta() + "]");
        log.debug("RegistrarContatoController:begin.do - [contaSelecionada = " + form.getContaSelecionada() + "]");
        log.debug("RegistrarContatoController:begin.do - [idLinha = " + form.getIdLinha() + "]");
        log.debug("RegistrarContatoController:begin.do - [linhaSelecionada = " + form.getLinhaSelecionada() + "]");
        log.debug("RegistrarContatoController:begin.do - [idClienteDePara = " + form.getIdClienteDePara() + "]");

        String idLinhaAtendimento = ConstantesCRM.SVAZIO;
        if (parametros.getIdLinhaAtendimento() != null && !ConstantesCRM.SZERO.equals(parametros.getIdLinhaAtendimento())) {
            idLinhaAtendimento = parametros.getIdLinhaAtendimento();
        } else {
            idLinhaAtendimento = parametros.getIdLinha();
        }

        form.setIdLinhaAtendimento(idLinhaAtendimento);
        form.setIdLinha(idLinhaAtendimento);
        log.debug("RegistrarContatoController:begin.do - [idLinhaAtendimento = " + form.getIdLinhaAtendimento() + "]");
        log.debug("RegistrarContatoController:begin.do - [idLinha = " + form.getIdLinha() + "]");

        // Monta a pessoa(usuario) que foi informado
        form.setIdUsuarioDePara(parametros.getIdUsuarioDePara() == null || ConstantesCRM.SVAZIO.equals(parametros.getIdUsuarioDePara()) ? ConstantesCRM.SONE : parametros.getIdUsuarioDePara());
        form.setInTipoPessoa(parametros.getInTipoPessoa() == null || ConstantesCRM.SVAZIO.equals(parametros.getInTipoPessoa()) ? "PF" : parametros.getInTipoPessoa());
        log.debug("RegistrarContatoController:begin.do - [idUsuarioDePara = " + form.getIdUsuarioDePara() + "]");
        log.debug("RegistrarContatoController:begin.do - [inTipoPessoa = " + form.getInTipoPessoa() + "]");

        // caso seja uma reabertura
        form.setCarregaLinha(form.getInResponsavelAbertura());
        log.debug("RegistrarContatoController:begin.do - [carregaLinha = " + form.getCarregaLinha() + "]");

        if (parametros.getIdAtendimento() != null) {
            form.setIdAtendimento(parametros.getIdAtendimento());
            form.setNrProtocolo(parametros.getNrProtocolo());
            form.setIdAtendimentoSituacao(parametros.getIdAtendimento());
            form.setNrProtocoloSituacao(parametros.getNrProtocolo());
            form.setIdTipoReaberturaProcesso(Integer.parseInt(parametros.getIdTipoReaberturaProcesso() == null || ConstantesCRM.SVAZIO.equals(parametros.getIdTipoReaberturaProcesso()) ? ConstantesCRM.SZERO : parametros.getIdTipoReaberturaProcesso()));
            form.setNmTipo(parametros.getNmTipo());
            form.setQtDias(ConstantesCRM.SZERO);

            log.debug("RegistrarContatoController:begin.do - [idAtendimentoSituacao = " + form.getIdAtendimentoSituacao() + "]");
            log.debug("RegistrarContatoController:begin.do - [idTipoReaberturaProcesso = " + form.getIdTipoReaberturaProcesso() + "]");
            log.debug("RegistrarContatoController:begin.do - [idAtendimento = " + form.getIdAtendimento() + "]");
            log.debug("RegistrarContatoController:begin.do - [nmTipo = " + form.getNmTipo() + "]");
            log.debug("RegistrarContatoController:begin.do - [qtDias = " + form.getQtDias() + "]");

            if (ConstantesCRM.STWO.equals(form.getInResponsavelAbertura())) {
                form.setCarregaLinha(ConstantesCRM.SONE);
                log.debug("RegistrarContatoController:begin.do - [carregaLinha = " + form.getCarregaLinha() + "]");
            }
        }

        // Monta os elementos manipulação na sessão corrente
        form.setIdDiscador(ConstantesCRM.SZERO);
        form.setFila(form.getFila());

        AtendimentoArvoreFiltrosVO atendimentoArvoreFiltrosVO = AtendimentoArvoreFiltrosVO.Factory.newInstance();
        atendimentoArvoreFiltrosVO.setIdGrupo(form.getIdGrupoAbertura());
        atendimentoArvoreFiltrosVO.setIdSegmentacao(form.getIdSegmentacao());
        atendimentoArvoreFiltrosVO.setIdTipoCarteira(form.getIdTipoCarteira());
        atendimentoArvoreFiltrosVO.setIdTipoLinha(form.getIdTipoLinha());
        atendimentoArvoreFiltrosVO.setIdUFOperadora(form.getIdUfOperadora());
        atendimentoArvoreFiltrosVO.setIdTipoRelacionamento(form.getInResponsavelAbertura());
        atendimentoArvoreFiltrosVO.setIdTipoSequencia(form.getIdFase());

        log.debug("RegistrarContatoController:begin.do - [idGrupo = " + form.getIdGrupoAbertura() + "]");
        log.debug("RegistrarContatoController:begin.do - [idSegmentacao = " + form.getIdSegmentacao() + "]");
        log.debug("RegistrarContatoController:begin.do - [idTipoCarteira = " + form.getIdTipoCarteira() + "]");
        log.debug("RegistrarContatoController:begin.do - [idTipoLinha = " + form.getIdTipoLinha() + "]");
        log.debug("RegistrarContatoController:begin.do - [idUFOperadora = " + form.getIdUfOperadora() + "]");
        log.debug("RegistrarContatoController:begin.do - [idTipoRelacionamento = " + form.getInResponsavelAbertura() + "]");
        log.debug("RegistrarContatoController:begin.do - [idFase = " + form.getIdFase() + "]");

        // verifica se dado um idChamadaTelefonica há processos ligados a ele
        // form.setAtendimentoVOLista(registrarContatoFacade.verificaProcessosCorrentes(user, form.getIdChamadaTelefonica()));
        atendimentoVOLista = registrarContatoFacade.verificaProcessosCorrentes(user, form.getIdChamadaTelefonica());
        form.setAtendimentoVOLista(atendimentoVOLista);

        if (form.getAtendimentoVOLista().getAtendimentosCorrentes().getAtendimentoVOArray() != null && form.getAtendimentoVOLista().getAtendimentosCorrentes().getAtendimentoVOArray().length == 0) {
            form.setFlagAtendimentosCorrentes(false);
        } else {
            form.setFlagAtendimentosCorrentes(true);
        }

        String url = ConstantesCRM.SVAZIO;
        StringBuffer script = new StringBuffer();
        AdmContatoFolhaVO admContatoFolhaVO = registrarContatoFacade.carregaArvoreContato(user, atendimentoArvoreFiltrosVO);

        if (admContatoFolhaVO == null) {
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);
        }

        if (admContatoFolhaVO.getAdmContatoInformacaoVOArray() != null && admContatoFolhaVO.getAdmContatoInformacaoVOArray().length > 0) {
            url = admContatoFolhaVO.getAdmContatoInformacaoVOArray(0).getNmURLContatoInformacao();
        }

        script.append("if(document.getElementById) {\nvar tree = new WebFXTree('").append(StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getNmContato())).append("',\"Javascript:void('0 ', '").append(StringUtilStatic.escapaComillasJS2(url))
                .append("','").append("');\",'classic');\n").toString();
        String node = ConstantesCRM.SVAZIO;
        String strMensagemAlt[] = new String[admContatoFolhaVO.getAdmContatoFolhaVOArray().length];

        for (int i = 0; i < admContatoFolhaVO.getAdmContatoFolhaVOArray().length; i++) {
            AdmContatoFolhaVO itemContatoFolha = admContatoFolhaVO.getAdmContatoFolhaVOArray(i);
            boolean inFolha = false;

            if (itemContatoFolha.getAdmContatoInformacaoVOArray() != null && itemContatoFolha.getAdmContatoInformacaoVOArray().length > 0) {
                url = itemContatoFolha.getAdmContatoInformacaoVOArray(0).getNmURLContatoInformacao();
                url = url.replaceAll("[\\\"\\\'\\\\]", ConstantesCRM.SVAZIO);
            } else {
                url = ConstantesCRM.SVAZIO;
            }

            if (itemContatoFolha.getInFolha() != null && ConstantesCRM.SONE.equals(itemContatoFolha.getInFolha())) {
                inFolha = true;
            }

            if (inFolha) {
                String sText = strUtil.escapaComillasJS(itemContatoFolha.getNmContato());
                if (ConstantesCRM.SVAZIO.equals(itemContatoFolha.getUrlFuncionalidade())) {
                    String sAction = "javascript:iniciaAtendimento('" + itemContatoFolha.getIdContato() + "', '" + strUtil.escapaComillasJS2(url) + "', '" + itemContatoFolha.getInRelacionamento() + "', '');";
                    node = getWebFXTreeItem(i, false, sText, sAction, ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO, strUtil.escapaComillasJS2(url), ConstantesCRM.SVAZIO);

                } else {
                    String sAction = "javascript:loadPage('" + itemContatoFolha.getIdContato() + "', '" + strUtil.escapaComillasJS2(itemContatoFolha.getUrlFuncionalidade()) + "', '" + itemContatoFolha.getInRelacionamento() + "', '');";
                    node = getWebFXTreeItem(i, false, sText, sAction, ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO);
                }

            } else {
                String sAction = "javascript:expandirNo('" + itemContatoFolha.getIdContato() + "', '" + strUtil.escapaComillasJS2(url) + "', '');";
                node = getWebFXTreeItem(i, false, itemContatoFolha.getNmContato(), sAction, ConstantesCRM.SVAZIO, "/FrontOfficeWeb/resources/images/foldericon.png", "/FrontOfficeWeb/resources/images/openfoldericon.png", strUtil.escapaComillasJS2(url), ConstantesCRM.SVAZIO);
            }

            if (!ConstantesCRM.SVAZIO.equals(url)) {
                node += "arvore" + String.valueOf(i) + ".setShowLupaLink(true);\n";
            }

            script.append(node);
            script.append("tree.add(arvore").append(i).append(");\nvar idNoArvoreAnchor").append(i).append(" = arvore").append(i).append(".id+'-anchor';\n\n");
            if ((itemContatoFolha.getMensagem() != null) && (!ConstantesCRM.SVAZIO.equals(itemContatoFolha.getMensagem()))) {
                strMensagemAlt[i] = "document.getElementById(idNoArvoreAnchor" + String.valueOf(i) + ").title=\"" + strUtil.escapaComillasJS(itemContatoFolha.getMensagem()) + "\";\n\n";
            }
        }

        script.append("document.write(tree);}\n");
        // loop para carregamento das mensagem anexas
        String strMensagem = ConstantesCRM.SVAZIO;
        for (int i = 0; i < admContatoFolhaVO.getAdmContatoFolhaVOArray().length; i++) {
            if (strMensagemAlt[i] != null && !ConstantesCRM.SVAZIO.equals(strMensagemAlt[i])) {
                strMensagem += strMensagemAlt[i];
            }
        }

        script.append(strMensagem);
        form.setScriptArvore(script.toString());
        log.debug("RegistrarContatoController:begin.do - [scriptArvore = " + script.toString() + "]");
        registrarContatoForm = form;
        log.debug("RegistrarContatoController:begin.do - Fim do Metodo]");

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    protected ActionForward arvoreContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // RegistrarContatoForm form = (RegistrarContatoForm) formParam;

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="/workflow/RegistrarContato/pesquisaFormulario.jsp"
     */
    protected ActionForward pesquisaFormulario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // RegistrarContatoForm form = (RegistrarContatoForm) formParam;

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    private String getWebFXTreeItem(int nItem, boolean parent, String sText, String sAction, String eParent, String sIcon, String sOpenIcon, String sLink, String sDblAction) {
        StringBuffer itemContato = new StringBuffer(ConstantesCRM.SVAZIO);
        itemContato.append("var arvore").append(nItem).append(" = new ");
        if (parent) {
            itemContato.append("parent.");
        }
        itemContato.append("WebFXTreeItem('").append(sText).append("',").append("\"").append(sAction).append("\",").append("'").append(eParent).append("',").append("'").append(sIcon).append("',").append("'").append(sOpenIcon).append("',")
                .append("'").append(sLink).append("'").append(",'").append(sDblAction).append("'").append(");\n");
        return itemContato.toString();
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="AtendimentoFrame.jsp"
     */
    protected ActionForward beginTeste(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        RegistrarContatoForm form = (RegistrarContatoForm) formParam;
        log.debug("RegistrarContatoController:beginTeste.do - Inicio do Metodo]");

        // String user = ConstantesCRM.getCurrentUser(request.getSession());
        // verifica se o atributo fila está preenchido
        if ((request.getAttribute("fila") != null) && (ConstantesCRM.SONE.equals(request.getAttribute("fila")))) {
            form.setFila(ConstantesCRM.SONE);
            log.debug("RegistrarContatoController:beginTeste.do - [fila = " + form.getFila() + "]");
        }

        // seta parametro para sinalizar q foi chamado por reincidencia e reabertura de processos
        form.setAbreProcessoReaRei(ConstantesCRM.SONE);
        log.debug("RegistrarContatoController:beginTeste.do - [abreProcessoReaRei = " + form.getAbreProcessoReaRei() + "]");

        /*
         * PARÂMETROS DA TELA INICIAL
         */
        ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute("parametrosWF");

        // INRESPONSAVELABERTURA -> 1-Usuario; 2-Cliente; 3-Prospect
        if ((parametros.getIdTipoRelacionamento() != null) && (ConstantesCRM.SONE.equals(parametros.getIdTipoRelacionamento()))) {
            form.setIdPessoa(parametros.getIdPessoaUsuario() == null ? ConstantesCRM.SONE : parametros.getIdPessoaUsuario());
        } else {
            form.setIdPessoa(parametros.getIdPessoaCliente() == null ? ConstantesCRM.SONE : parametros.getIdPessoaCliente());
        }

        log.debug("RegistrarContatoController:beginTeste.do - [idPessoa = " + form.getIdPessoa() + "]");
        form.setInResponsavelAbertura(parametros.getIdTipoRelacionamento() == null ? ConstantesCRM.SONE : parametros.getIdTipoRelacionamento());
        log.debug("RegistrarContatoController:beginTeste.do - [inResponsavel Abertura = " + form.getInResponsavelAbertura() + "]");

        // grupoAndamento
        form.setIdGrupoAbertura(parametros.getIdGrupoAndamento() == null ? ConstantesCRM.SONE : parametros.getIdGrupoAndamento());
        log.debug("RegistrarContatoController:beginTeste.do - [idGrupoAbertura = " + form.getIdGrupoAbertura() + "]");

        if ((parametros.getIdTipoRelacionamento() != null) && ((ConstantesCRM.SONE.equals(parametros.getIdTipoRelacionamento())) || (ConstantesCRM.STWO.equals(parametros.getIdTipoRelacionamento())))) {
            form.setIdSegmentacao(parametros.getIdSegmentacao() == null || ConstantesCRM.SVAZIO.equals(parametros.getIdSegmentacao()) ? ConstantesCRM.SONE : parametros.getIdSegmentacao());
            form.setIdTipoCarteira(parametros.getIdTipoCarteira() == null || ConstantesCRM.SVAZIO.equals(parametros.getIdTipoCarteira()) ? ConstantesCRM.SONE : parametros.getIdTipoCarteira());
            form.setIdTipoLinha(parametros.getIdTipoLinha() == null || ConstantesCRM.SVAZIO.equals(parametros.getIdTipoLinha()) ? ConstantesCRM.SONE : parametros.getIdTipoLinha());
            form.setIdUfOperadora(parametros.getIdUfOperadora() == null || ConstantesCRM.SVAZIO.equals(parametros.getIdUfOperadora()) ? ConstantesCRM.SONE : parametros.getIdUfOperadora());
        
        } else {
            form.setIdSegmentacao("11");
            form.setIdTipoCarteira("13");
            form.setIdTipoLinha("3");
            form.setIdUfOperadora("21");
        }

        log.debug("RegistrarContatoController:beginTeste.do - [IdSegmentacao = " + form.getIdSegmentacao() + "]");
        log.debug("RegistrarContatoController:beginTeste.do - [IdTipoCarteira = " + form.getIdTipoCarteira() + "]");
        log.debug("RegistrarContatoController:beginTeste.do - [IdTipoLinha = " + form.getIdTipoLinha() + "]");
        log.debug("RegistrarContatoController:beginTeste.do - [IdUfOperadora = " + form.getIdUfOperadora() + "]");

        form.setNomeContato(parametros.getNmFalandoCom() == null || ConstantesCRM.SVAZIO.equals(parametros.getNmFalandoCom()) ? ConstantesCRM.SVAZIO : parametros.getNmFalandoCom());
        form.setNomeContatoAlterado(parametros.getNmContato() == null || ConstantesCRM.SVAZIO.equals(parametros.getNmContato()) ? ConstantesCRM.SVAZIO : parametros.getNmContato());
        form.setIdChamadaTelefonica(Long.parseLong(parametros.getIdChamadaTelefonica() == null || ConstantesCRM.SVAZIO.equals(parametros.getIdChamadaTelefonica()) || "(null)".equals(parametros.getIdChamadaTelefonica()) ? ConstantesCRM.SONE : parametros.getIdChamadaTelefonica()));
        form.setIdFase((parametros.getIdFase() == null || ConstantesCRM.SVAZIO.equals(parametros.getIdFase()) ? ConstantesCRM.SONE : parametros.getIdFase()));

        log.debug("RegistrarContatoController:beginTeste.do - [nomeContato = " + form.getNomeContato() + "]");
        log.debug("RegistrarContatoController:beginTeste.do - [nomeContatoAlterado = " + form.getNomeContatoAlterado() + "]");
        log.debug("RegistrarContatoController:beginTeste.do - [IdChamadoTelefonica = " + form.getIdChamadaTelefonica() + "]");
        log.debug("RegistrarContatoController:beginTeste.do - [IdFase = " + form.getIdFase() + "]");

        String telContato = ConstantesCRM.SVAZIO;
        if (parametros.getNrLinha() == null || ConstantesCRM.SVAZIO.equals(parametros.getNrLinha())) {
            if (parametros.getNrTelefone() == null || ConstantesCRM.SVAZIO.equals(parametros.getNrTelefone())) {
                telContato = ConstantesCRM.SVAZIO;
            } else {
                telContato = parametros.getNrTelefone();
            }
        } else {
            telContato = parametros.getNrLinha();
        }

        form.setTelContatoFrm(telContato);
        log.debug("RegistrarContatoController:beginTeste.do - [telContatoFrm = " + form.getTelContatoFrm() + "]");

        if (!ConstantesCRM.SVAZIO.equals(telContato) && telContato.length() < 10) {
            telContato = ConstantesCRM.mask(telContato, "(##)###-####");
        } else if (!telContato.equals(ConstantesCRM.SVAZIO)) {
            telContato = ConstantesCRM.formatPhoneNumber(telContato);
        }

        form.setTelContato(telContato);
        log.debug("RegistrarContatoController:beginTeste.do - [telContato = " + form.getTelContato() + "]");

        form.setNrConta(parametros.getNrConta() == null || ConstantesCRM.SVAZIO.equals(parametros.getNrConta()) ? ConstantesCRM.SVAZIO : parametros.getNrConta());
        form.setIdConta(parametros.getIdConta() == null || ConstantesCRM.SVAZIO.equals(parametros.getIdConta()) ? ConstantesCRM.SONE : parametros.getIdConta());
        form.setContaSelecionada(parametros.getIdConta() == null || ConstantesCRM.SVAZIO.equals(parametros.getIdConta()) ? ConstantesCRM.SONE : parametros.getIdConta());
        form.setIdLinha(parametros.getIdLinha() == null || ConstantesCRM.SVAZIO.equals(parametros.getIdLinha()) ? ConstantesCRM.SZERO : parametros.getIdLinha());
        form.setLinhaSelecionada(parametros.getIdLinha() == null || ConstantesCRM.SVAZIO.equals(parametros.getIdLinha()) ? ConstantesCRM.SZERO : parametros.getIdLinha());
        form.setIdClienteDePara(parametros.getIdClienteDePara() == null || ConstantesCRM.SZERO.equals(parametros.getIdClienteDePara()) ? ConstantesCRM.SONE : parametros.getIdClienteDePara());
        form.setIdContato(parametros.getIdContato() == null ? ConstantesCRM.SONE : parametros.getIdContato());

        log.debug("RegistrarContatoController:beginTeste.do - [nrConta = " + form.getNrConta() + "]");
        log.debug("RegistrarContatoController:beginTeste.do - [idConta = " + form.getIdConta() + "]");
        log.debug("RegistrarContatoController:beginTeste.do - [contaSelecionada = " + form.getContaSelecionada() + "]");
        log.debug("RegistrarContatoController:beginTeste.do - [idLinha = " + form.getIdLinha() + "]");
        log.debug("RegistrarContatoController:beginTeste.do - [linhaSelecionada = " + form.getLinhaSelecionada() + "]");
        log.debug("RegistrarContatoController:beginTeste.do - [idClienteDePara = " + form.getIdClienteDePara() + "]");
        log.debug("RegistrarContatoController:beginTeste.do - [idContato = " + form.getIdContato() + "]");

        String idLinhaAtendimento = ConstantesCRM.SVAZIO;
        if (parametros.getIdLinhaAtendimento() != null && !ConstantesCRM.SZERO.equals(parametros.getIdLinhaAtendimento())) {
            idLinhaAtendimento = parametros.getIdLinhaAtendimento();
        } else {
            idLinhaAtendimento = parametros.getIdLinha();
        }

        form.setIdLinhaAtendimento(idLinhaAtendimento);
        form.setIdLinha(idLinhaAtendimento);
        log.debug("RegistrarContatoController:beginTeste.do - [idLinhaAtendimento = " + form.getIdLinhaAtendimento() + "]");
        log.debug("RegistrarContatoController:beginTeste.do - [idLinha = " + form.getIdLinha() + "]");

        // Monta a pessoa(usuario) que foi informado
        form.setIdUsuarioDePara(parametros.getIdUsuarioDePara() == null || ConstantesCRM.SVAZIO.equals(parametros.getIdUsuarioDePara()) ? ConstantesCRM.SONE : parametros.getIdUsuarioDePara());
        form.setInTipoPessoa(parametros.getInTipoPessoa() == null || ConstantesCRM.SVAZIO.equals(parametros.getInTipoPessoa()) ? "PF" : parametros.getInTipoPessoa());
        log.debug("RegistrarContatoController:beginTeste.do - [idUsuarioDePara = " + form.getIdUsuarioDePara() + "]");
        log.debug("RegistrarContatoController:beginTeste.do - [inTipoPessoa = " + form.getInTipoPessoa() + "]");

        // caso seja uma reabertura
        form.setCarregaLinha(form.getInResponsavelAbertura());
        log.debug("RegistrarContatoController:beginTeste.do - [carregaLinha = " + form.getCarregaLinha() + "]");

        if (parametros.getIdAtendimento() != null) {
            form.setIdAtendimento(parametros.getIdAtendimento());
            form.setIdAtendimentoSituacao(parametros.getIdAtendimento());
            form.setIdTipoReaberturaProcesso(Integer.parseInt(parametros.getIdTipoReaberturaProcesso() == null || ConstantesCRM.SVAZIO.equals(parametros.getIdTipoReaberturaProcesso()) ? ConstantesCRM.SZERO : parametros.getIdTipoReaberturaProcesso()));
            form.setNmTipo(parametros.getNmTipo());
            form.setQtDias(ConstantesCRM.SZERO);
            form.setNrProtocolo(parametros.getNrProtocolo());
            log.debug("RegistrarContatoController:beginTeste.do - [idAtendimento = " + form.getIdAtendimento() + "]");
            log.debug("RegistrarContatoController:beginTeste.do - [idAtendimentoSituacao = " + form.getIdAtendimentoSituacao() + "]");
            log.debug("RegistrarContatoController:beginTeste.do - [idTipoReaberturaProcesso = " + form.getIdTipoReaberturaProcesso() + "]");
            log.debug("RegistrarContatoController:beginTeste.do - [nmTipo = " + form.getNmTipo() + "]");
            log.debug("RegistrarContatoController:beginTeste.do - [qtDias = " + form.getQtDias() + "]");

            if (ConstantesCRM.STWO.equals(form.getInResponsavelAbertura())) {
                form.setCarregaLinha(ConstantesCRM.SONE);
                log.debug("RegistrarContatoController:beginTeste.do - [carregaLinha = " + form.getCarregaLinha() + "]");
            }
        }

        // Monta os elementos manipulação na sessão corrente
        form.setIdDiscador(ConstantesCRM.SZERO);
        form.setFila(form.getFila());
        form.setFlagAtendimentosCorrentes(false);
        form.setScriptArvore(ConstantesCRM.SVAZIO);

        registrarContatoForm = form;

        log.debug("RegistrarContatoController:beginTeste.do - Fim do Metodo]");
        request.getSession().removeAttribute("parametrosWF");

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="iExpandeArvoreContato.jsp"
     */
    protected ActionForward expandeArvoreContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        RegistrarContatoForm form = (RegistrarContatoForm) formParam;

        log.debug("RegistrarContatoController:expandeArvoreContato.do - Inicio do Metodo]");
        String user = ConstantesCRM.getCurrentUser(request.getSession());

        // int pageNumber = 0;
        String script = ConstantesCRM.SVAZIO;
        String node = ConstantesCRM.SVAZIO;
        String url = ConstantesCRM.SVAZIO;
        StringUtil strUtil = new StringUtil();

        // formEntrada.getAtendimentoArvoreFiltrosVO().setIdContato(request.getParameter("IDCONTATO"));
        AtendimentoArvoreFiltrosVO atendimentoArvoreFiltrosVO = AtendimentoArvoreFiltrosVO.Factory.newInstance();
        atendimentoArvoreFiltrosVO.setIdGrupo(form.getIdGrupoAbertura());
        atendimentoArvoreFiltrosVO.setIdSegmentacao(form.getIdSegmentacao());
        atendimentoArvoreFiltrosVO.setIdTipoCarteira(form.getIdTipoCarteira());
        atendimentoArvoreFiltrosVO.setIdTipoLinha(form.getIdTipoLinha());
        atendimentoArvoreFiltrosVO.setIdUFOperadora(form.getIdUfOperadora());
        atendimentoArvoreFiltrosVO.setIdTipoRelacionamento(form.getInResponsavelAbertura());
        atendimentoArvoreFiltrosVO.setIdContato(request.getParameter("IDCONTATO"));
        atendimentoArvoreFiltrosVO.setIdTipoSequencia(form.getIdFase());

        // atendimentoArvoreFiltrosVO.addNewPaginacao().setPageNumber(pageNumber);
        log.debug("RegistrarContatoController:expandeArvoreContato.do - [idGrupo = " + form.getIdGrupoAbertura() + "]");
        log.debug("RegistrarContatoController:expandeArvoreContato.do - [idSegmentacao = " + form.getIdSegmentacao() + "]");
        log.debug("RegistrarContatoController:expandeArvoreContato.do - [idTipoCarteira = " + form.getIdTipoCarteira() + "]");
        log.debug("RegistrarContatoController:expandeArvoreContato.do - [idTipoLinha = " + form.getIdTipoLinha() + "]");
        log.debug("RegistrarContatoController:expandeArvoreContato.do - [idUFOperadora = " + form.getIdUfOperadora() + "]");
        log.debug("RegistrarContatoController:expandeArvoreContato.do - [idTipoRelacionamento = " + form.getInResponsavelAbertura() + "]");
        log.debug("RegistrarContatoController:expandeArvoreContato.do - [idContato = " + atendimentoArvoreFiltrosVO.getIdContato() + "]");
        log.debug("RegistrarContatoController:expandeArvoreContato.do - [idFase = " + form.getIdFase() + "]");
        
        AdmContatoFolhaVO admContatoFolhaVO = registrarContatoFacade.carregaArvoreContato(user, atendimentoArvoreFiltrosVO);

        // AdmContatoFolhaVO admContatoFolhaVOPaginacao = admContatoFolhaVO;
        for (int i = 0; i < admContatoFolhaVO.getAdmContatoFolhaVOArray().length; i++) {
            AdmContatoFolhaVO itemContatoFolha = admContatoFolhaVO.getAdmContatoFolhaVOArray(i);
            boolean inFolha = false;

            if (itemContatoFolha.getAdmContatoInformacaoVOArray() != null && itemContatoFolha.getAdmContatoInformacaoVOArray().length > 0) {
                url = itemContatoFolha.getAdmContatoInformacaoVOArray(0).getNmURLContatoInformacao();
                url = url.replaceAll("[\\\"\\\'\\\\]", ConstantesCRM.SVAZIO);
            } else {
                url = ConstantesCRM.SVAZIO;
            }

            if (ConstantesCRM.SONE.equals(itemContatoFolha.getInFolha())) {
                inFolha = true;
            }

            if (inFolha) {
                String sText = strUtil.escapaComillasJS(itemContatoFolha.getNmContato());
                if (ConstantesCRM.SVAZIO.equals(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getUrlFuncionalidade())) {
                    String sAction = "javascript:iniciaAtendimento('" + itemContatoFolha.getIdContato() + "', '" + strUtil.escapaComillasJS2(url) + "', '" + itemContatoFolha.getInRelacionamento() + "', '');";
                    node = getWebFXTreeItem(i, true, sText, sAction, ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO, strUtil.escapaComillasJS2(url), ConstantesCRM.SVAZIO);
                } else {
                    String sAction = "javascript:loadPage('" + itemContatoFolha.getIdContato() + "', '" + strUtil.escapaComillasJS2(itemContatoFolha.getUrlFuncionalidade()) + "', '" + itemContatoFolha.getInRelacionamento() + "', '');";
                    node = getWebFXTreeItem(i, true, sText, sAction, ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO);
                }

            } else {
                String sAction = "javascript:expandirNo('" + itemContatoFolha.getIdContato() + "', '" + strUtil.escapaComillasJS2(url) + "', '');";
                node = getWebFXTreeItem(i, true, itemContatoFolha.getNmContato(), sAction, ConstantesCRM.SVAZIO, "/FrontOfficeWeb/resources/images/foldericon.png", "/FrontOfficeWeb/resources/images/openfoldericon.png", strUtil.escapaComillasJS2(url), ConstantesCRM.SVAZIO);
            }

            if (!ConstantesCRM.SVAZIO.equals(url)) {
                node += "arvore" + String.valueOf(i) + ".setShowLupaLink(true);\n";
            }

            script += node;
            script += "parent.tree.getSelected().add(arvore" + String.valueOf(i) + ");\n";
            script += "parent.tree.getSelected().expand();\nvar idNoArvoreAnchor" + String.valueOf(i) + " = arvore" + String.valueOf(i) + ".id+'-anchor';\n\n";

            if (itemContatoFolha.getMensagem() != null && !ConstantesCRM.SVAZIO.equals(itemContatoFolha.getMensagem())) {
                script += "parent.document.getElementById(idNoArvoreAnchor" + String.valueOf(i) + ").title=\"" + strUtil.escapaComillasJS(itemContatoFolha.getMensagem()) + "\";\n\n";
            }
        }

        script += "parent.tree.getSelected().setAddEnabled(false);\n\n";
        form.setScriptArvore(script);
        log.debug("RegistrarContatoController:expandeArvoreContato.do - [script = " + form.getScriptArvore() + "]");
        registrarContatoForm = form;

        // seta nulo no objeto
        strUtil = null;
        log.debug("RegistrarContatoController:expandeArvoreContato.do - Fim do Metodo]");

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="successRegistry" path="InicioAbertura.jsp"
     * @jpf:forward name="successInsist" path="confirmaInsistencia.jsp"
     * @jpf:forward name="redirectErrorParam" path="redirectArvoreContato.jsp"
     */
    protected ActionForward iniciaAtendimento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        RegistrarContatoForm form = (RegistrarContatoForm) formParam;
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        log.debug("RegistrarContatoController:iniciaAtendimento.do - Inicio do Metodo]");

        ArvoreAtendimentoVO arvoreAtendimentoVO = ArvoreAtendimentoVO.Factory.newInstance();
        ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
        int idFaseProcesso = 1;
        int qtdeCamposGrupos = 0;
        String user = ConstantesCRM.getCurrentUser(request.getSession());

        /*
         * Verifica se dado um idChamadaTelefonica há processos ligados a ele O inBegin é utilizado para o serviço
         * ATDPQCHAATDUS nao ser chamado duas vezes logo quando a primeira folha for clicada, uma vez que ele ja e
         * chamado no begin().
         */
        
        if (inBegin) {
            inBegin = false;
        } else {
            atendimentoVOLista = registrarContatoFacade.verificaProcessosCorrentes(user, form.getIdChamadaTelefonica());
        }
        
        if(atendimentoVOLista == null){
        	log.warning("RegistrarContatoController:iniciaAtendimento.do - AtendimentoVO null!");
        	atendimentoVOLista = AtendimentoVO.Factory.newInstance();
        }

        form.setListaDadosVO(registrarContatoForm.getListaDadosVO());
        registrarContatoForm = form;

        // Determinamos si se procede de insistencia o no
        if (registrarContatoForm.getAbertura() == 0) {
            // Monta a árvore que foi informado
            registrarContatoForm.setIdContato(request.getParameter("IDCONTATO"));
            arvoreAtendimentoVO.setIdContato(Integer.parseInt(request.getParameter("IDCONTATO")));
        } else {
            arvoreAtendimentoVO.setIdContato(Integer.parseInt(registrarContatoForm.getIdContato()));
        }

        // StringUtil strUtil = new StringUtil();
        // Quando for uma linha de não cliente (não cadastrada no Vivonet), o IDTIPOLINHA deve ser 1.
        // Alteração feita para validação de funcionalidades acessadas pela árvore a partir de
        // relacionamento com a tabela CONTATOADM.CONTATOACAO
        if (parametros != null && parametros.getIdLinha() == null) {
            form.setIdTipoLinha(ConstantesCRM.SONE);
        }

        try {
            // Consultar contato
            atendimentoVOFacade = registrarContatoFacade.consultarContato(user, arvoreAtendimentoVO.getIdContato(), Long.parseLong(form.getIdClienteDePara()), idFaseProcesso, form.getIdTipoLinha(), form.getIdLinha(), form.getIdUfOperadora(),
                    form.getTelContatoFrm(), registrarContatoForm.getAbertura(), form.getIdGrupoAbertura(), form.getInTipoPessoa(), form.getIdTipoCarteira(), form.getIdSegmentacao(), form.getInResponsavelAbertura(), form.getIdPessoa(),
                    form.getIdConta(), false);
            log.debug("RegistrarContatoController:iniciaAtendimento.do - [idContato = " + arvoreAtendimentoVO.getIdContato() + "]");
            log.debug("RegistrarContatoController:iniciaAtendimento.do - [idClienteDePara = " + form.getIdClienteDePara() + "]");
            log.debug("RegistrarContatoController:iniciaAtendimento.do - [idTipoLinha = " + form.getIdTipoLinha() + "]");
            log.debug("RegistrarContatoController:iniciaAtendimento.do - [idUFOperadora = " + form.getIdUfOperadora() + "]");
            log.debug("RegistrarContatoController:iniciaAtendimento.do - [telContatoFrm = " + form.getTelContatoFrm() + "]");
            log.debug("RegistrarContatoController:iniciaAtendimento.do - [abertura = " + registrarContatoForm.getAbertura() + "]");
            log.debug("RegistrarContatoController:iniciaAtendimento.do - [idGrupo = " + form.getIdGrupoAbertura() + "]");
            log.debug("RegistrarContatoController:iniciaAtendimento.do - [inTipoPessoa = " + form.getInTipoPessoa() + "]");
            log.debug("RegistrarContatoController:iniciaAtendimento.do - [idTipoCarteira = " + form.getIdTipoCarteira() + "]");
            log.debug("RegistrarContatoController:iniciaAtendimento.do - [idSegmentacao = " + form.getIdSegmentacao() + "]");
            log.debug("RegistrarContatoController:iniciaAtendimento.do - [idTipoRelacionamento = " + form.getInResponsavelAbertura() + "]");
            log.debug("RegistrarContatoController:iniciaAtendimento.do - [idPessoa = " + form.getIdPessoa() + "]");
            log.debug("RegistrarContatoController:iniciaAtendimento.do - [idConta = " + form.getIdConta() + "]");

        } catch (TuxedoException e) {
            if (e.getMessage().matches("^.*0E000.*$")) {
                request.setAttribute("msgError", e.getXmlHeader().getStatusText());
                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                return mapping.findForward("redirectErrorParam");
            } else {
                throw (e);
            }
        }

        // Prepara apresentação de grupos de campos
        if (atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO() != null && atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getAdmGrupoCamposVOArray() != null) {
            registrarContatoForm.setAdmGrupoCamposVO(atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getAdmGrupoCamposVOArray());
            for (int i = 0; i < atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getAdmGrupoCamposVOArray().length; i++) {
                qtdeCamposGrupos += atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getAdmGrupoCamposVOArray(i).getFormularioCampoVOArray().length;
            }
        }

        // Prepara apresentação de funcionalidade de pesquisa de endereço
        if ("ENDERECOCOMPLETO".equals(atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getInFuncionalidade())) {
            registrarContatoForm.setEnderecoVO(atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getEnderecoVOArray() != null ? atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getEnderecoVOArray(0) : registrarContatoForm
                    .getEnderecoVO());
            registrarContatoForm.setSgUF(atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getEnderecoVOArray(0).getUFVO().getSgUF());
            registrarContatoForm.setNmPais(atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getEnderecoVOArray(0).getPaisVO().getNmPais());
        }

        registrarContatoForm.setAbertura(0);
        registrarContatoForm.setDescricaoCompleta(retornaAsc(atendimentoVOFacade.getArvoreAtendimentoVO().getDescricaoCompleta()));
        if (atendimentoVOFacade.getAtendimentoSituacaoVO() != null) {
            if (atendimentoVOFacade.getAtendimentoSituacaoVO().getIdTipoReaberturaProcesso() > 1) {
                if (form.getIdAtendimento() != null && form.getIdAtendimento().length() > 1) {
                    registrarContatoForm.setIdAtendimentoSituacao(form.getIdAtendimento());
                    registrarContatoForm.setNrProtocoloSituacao(atendimentoVOFacade.getAtendimentoSituacaoVO().getNrProtocolo());
                    registrarContatoForm.setIdTipoReaberturaProcesso(form.getIdTipoReaberturaProcesso());
                    registrarContatoForm.setNmTipo(form.getNmTipo());
                    registrarContatoForm.setQtDias(form.getQtDias());
                    log.debug("RegistrarContatoController:iniciaAtendimento.do - [idAtendimentoSituacao = " + form.getIdAtendimento() + "]");
                    log.debug("RegistrarContatoController:iniciaAtendimento.do - [idTipoReaberturaProcesso = " + form.getIdTipoReaberturaProcesso() + "]");
                    log.debug("RegistrarContatoController:iniciaAtendimento.do - [nmTipo = " + form.getNmTipo() + "]");
                    log.debug("RegistrarContatoController:iniciaAtendimento.do - [qtDias = " + form.getQtDias() + "]");

                } else {
                    registrarContatoForm.setIdAtendimentoSituacao(atendimentoVOFacade.getAtendimentoSituacaoVO().getIdAtendimento());
                    registrarContatoForm.setNrProtocoloSituacao(atendimentoVOFacade.getAtendimentoSituacaoVO().getNrProtocolo());
                    registrarContatoForm.setIdTipoReaberturaProcesso(atendimentoVOFacade.getAtendimentoSituacaoVO().getIdTipoReaberturaProcesso());
                    registrarContatoForm.setNmTipo(atendimentoVOFacade.getAtendimentoSituacaoVO().getNmTipo());
                    registrarContatoForm.setQtDias(atendimentoVOFacade.getAtendimentoSituacaoVO().getQtDias());
                    log.debug("RegistrarContatoController:iniciaAtendimento.do - [idAtendimentoSituacao = " + registrarContatoForm.getIdAtendimentoSituacao() + "]");
                    log.debug("RegistrarContatoController:iniciaAtendimento.do - [idTipoReaberturaProcesso = " + registrarContatoForm.getIdTipoReaberturaProcesso() + "]");
                    log.debug("RegistrarContatoController:iniciaAtendimento.do - [nmTipo = " + registrarContatoForm.getNmTipo() + "]");
                    log.debug("RegistrarContatoController:iniciaAtendimento.do - [qtDias = " + registrarContatoForm.getQtDias() + "]");
                }

            } else {
                registrarContatoForm.setIdAtendimentoSituacao(atendimentoVOFacade.getAtendimentoSituacaoVO().getIdAtendimento());
                registrarContatoForm.setNrProtocoloSituacao(atendimentoVOFacade.getAtendimentoSituacaoVO().getNrProtocolo());
                registrarContatoForm.setIdTipoReaberturaProcesso(atendimentoVOFacade.getAtendimentoSituacaoVO().getIdTipoReaberturaProcesso());
                registrarContatoForm.setNmTipo(atendimentoVOFacade.getAtendimentoSituacaoVO().getNmTipo());
                registrarContatoForm.setQtDias(atendimentoVOFacade.getAtendimentoSituacaoVO().getQtDias());
                log.debug("RegistrarContatoController:iniciaAtendimento.do - [idAtendimentoSituacao = " + registrarContatoForm.getIdAtendimentoSituacao() + "]");
                log.debug("RegistrarContatoController:iniciaAtendimento.do - [idTipoReaberturaProcesso = " + registrarContatoForm.getIdTipoReaberturaProcesso() + "]");
                log.debug("RegistrarContatoController:iniciaAtendimento.do - [nmTipo = " + registrarContatoForm.getNmTipo() + "]");
                log.debug("RegistrarContatoController:iniciaAtendimento.do - [qtDias = " + registrarContatoForm.getQtDias() + "]");
            }

        } else {
            if (form.getIdAtendimento() != null && form.getIdAtendimento().length() > 1) {
                registrarContatoForm.setIdAtendimentoSituacao(form.getIdAtendimento());
                registrarContatoForm.setNrProtocoloSituacao(form.getNrProtocolo());
                registrarContatoForm.setIdTipoReaberturaProcesso(form.getIdTipoReaberturaProcesso());
                registrarContatoForm.setNmTipo(form.getNmTipo());
                registrarContatoForm.setQtDias(form.getQtDias());
                log.debug("RegistrarContatoController:iniciaAtendimento.do - [idAtendimentoSituacao = " + registrarContatoForm.getIdAtendimentoSituacao() + "]");
                log.debug("RegistrarContatoController:iniciaAtendimento.do - [idTipoReaberturaProcesso = " + registrarContatoForm.getIdTipoReaberturaProcesso() + "]");
                log.debug("RegistrarContatoController:iniciaAtendimento.do - [nmTipo = " + registrarContatoForm.getNmTipo() + "]");
                log.debug("RegistrarContatoController:iniciaAtendimento.do - [qtDias = " + registrarContatoForm.getQtDias() + "]");
            }
        }

        // verifica se o tamanho dos campos do formulário é menor que um valor default. Se for, seta o valor default
        if (atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO() != null) {
            for (int i = 0; i < atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getFormularioCampoVOArray().length; i++) {
                if (atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getFormularioCampoVOArray(i).getTipoCampoVO().getNrTamanho() != null) {
                    if (ConstantesCRM.SZERO.equals(atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getFormularioCampoVOArray(i).getTipoCampoVO().getNrTamanho())) {
                        atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getFormularioCampoVOArray(i).getTipoCampoVO().setNrTamanho("10");
                    }
                }
            }

            if (atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getAdmGrupoCamposVOArray() != null) {
                for (int i = 0; i < atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getAdmGrupoCamposVOArray().length; i++) {
                    // Apenas para grupos de campos normais (idTipoGrupo 1)
                    if (ConstantesCRM.SONE.equals(atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getAdmGrupoCamposVOArray(i).getIdTipoGrupo())) {
                        for (int j = 0; j < atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getAdmGrupoCamposVOArray(i).getFormularioCampoVOArray().length; j++) {
                            if (atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getAdmGrupoCamposVOArray(i).getFormularioCampoVOArray(j).getTipoCampoVO().getNrTamanho() != null) {
                                if (ConstantesCRM.SZERO.equals(atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getAdmGrupoCamposVOArray(i).getFormularioCampoVOArray(j).getTipoCampoVO().getNrTamanho())) {
                                    atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getAdmGrupoCamposVOArray(i).getFormularioCampoVOArray(j).getTipoCampoVO().setNrTamanho("10");
                                }
                            }
                        }
                    }
                }
            }
        }

        if (atendimentoVOFacade.getNmURLContatoAcao() != null && !ConstantesCRM.SVAZIO.equals(atendimentoVOFacade.getNmURLContatoAcao().trim())) {
            atendimentoVOFacade.setNmURLContatoAcao(ConstantesCRM.unmask(atendimentoVOFacade.getNmURLContatoAcao(), "\n"));
            String scriptValidacao = "function validaFormulario(){return true;}";
            String scriptCarrega = ConstantesCRM.SVAZIO;
            registrarContatoForm.setScriptValidacao(scriptValidacao);
            registrarContatoForm.setScriptCarrega(scriptCarrega);

        } else {
            // atendimentoVOFacade.setNmURLContatoAcao("/workflow/RegistrarContato/FormularioDinamico.jsp");
            // String scriptValidacao = "function validaFormulario(){return true;}";
            // registrarContatoForm.setScriptValidacao(scriptValidacao);
            montaValidacao(atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO(), form.getAbreProcessoReaRei(), request);
        }

        registrarContatoForm.setAtendimentoVO(atendimentoVOFacade);

        // se houver insistencia somente registra senão, monta o questionário
        if ((atendimentoVOFacade.getAtendimentoSituacaoVO() != null) && ("-1".equals(atendimentoVOFacade.getAtendimentoSituacaoVO().getQtDias()))) {
            log.debug("RegistrarContatoController:iniciaAtendimento.do - Fim do Metodo]");
            request.getSession().setAttribute("atendimentoVOFacade", atendimentoVOFacade);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward("successInsist");

        } else {
            // une nrConta e nrDigitoConta para exibição
            for (int i = 0; i < atendimentoVOFacade.getContas().getContaVOArray().length; i++) {
                atendimentoVOFacade.getContas().getContaVOArray(i).setDsAuxiliar(atendimentoVOFacade.getContas().getContaVOArray(i).getNrDigitoConta() + " - " + atendimentoVOFacade.getContas().getContaVOArray(i).getNrConta());
            }

            // salva o atendimentoVO no ActionForm e faz o dispatcher
            registrarContatoForm.setAtendimentoVO(atendimentoVOFacade);
            registrarContatoForm.getAtendimentoVO().setAtendimentosCorrentes(AtendimentosCorrentes.Factory.newInstance());
            registrarContatoForm.getAtendimentoVO().getAtendimentosCorrentes().setAtendimentoVOArray(new AtendimentoVOImpl[0]);

            // Monta a sergmentação e carterização obtidas do cliente/usuário/prospect
            registrarContatoForm.getAtendimentoVO().setPessoaVO(atendimentoVOFacade.getPessoaVO());
            registrarContatoForm.setIdTipoComunicacaoAbertura(atendimentoVOFacade.getPessoaVO().getIdTipoComunicacao());

            // atualiza o numero de campos para a construção do formulário dinâmico
            registrarContatoForm.setNumeroCampos(atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getFormularioCampoVOArray().length + qtdeCamposGrupos);

            // registrarContatoForm.setNumeroCampos(atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getFormularioCampoVOArray().length);
            if (ConstantesCRM.SONE.equals(form.getAbreProcessoReaRei())) {
                obterTipoComunicacaoAtendimento(registrarContatoForm.getIdAtendimentoSituacao(), request);
            }

            log.debug("RegistrarContatoController:iniciaAtendimento.do - Fim do Metodo]");
            request.getSession().setAttribute("atendimentoVOFacade", atendimentoVOFacade);

            ListaDadosVO listaDadosVO = registrarContatoFacade.getConsultaConta(user, ConstantesCRM.SZERO, form.getIdPessoa(), ConstantesCRM.SVAZIO);
            registrarContatoForm.setListaDadosVO(listaDadosVO);

            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward("successRegistry");
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="pesquisaContato.jsp"
     */
    protected ActionForward getLinhasByConta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        RegistrarContatoForm form = (RegistrarContatoForm) formParam;

        // request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        StringBuffer xmlDados = new StringBuffer();
        String user = ConstantesCRM.getCurrentUser(request.getSession());
        try {
            String nrValor = request.getParameter("cdConta");
            ListaDadosVO listaDadosVO = registrarContatoFacade.getConsultaConta(user, ConstantesCRM.SONE, form.getIdPessoa(), nrValor);
            registrarContatoForm.setListaDadosVO(listaDadosVO);
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
     * @jpf:forward name="success" path="pesquisaContato.jsp"
     */
    protected ActionForward getContaByLinha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        RegistrarContatoForm form = (RegistrarContatoForm) formParam;

        // request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        StringBuffer xmlDados = new StringBuffer();
        String user = ConstantesCRM.getCurrentUser(request.getSession());

        try {
            String nrValor = request.getParameter("nrLinha");
            ListaDadosVO listaDadosVO = registrarContatoFacade.getConsultaConta(user, ConstantesCRM.STWO, form.getIdPessoa(), nrValor);
            registrarContatoForm.setListaDadosVO(listaDadosVO);
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
     * Método utilizado para carregamento dos valores do próximo nível de um determinado grupo de campos dependentes.
     * 
     * @jpf:action
     * @jpf:forward name="success" path="pesquisaContato.jsp"
     */
    protected ActionForward getValoresProximoNivel(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // RegistrarContatoForm form = (RegistrarContatoForm) formParam;
        // request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        StringBuffer xmlDados = new StringBuffer();
        String user = ConstantesCRM.getCurrentUser(request.getSession());

        try {
            String idResposta = request.getParameter("idResposta");
            String[] dadosGrupo = request.getParameter("dadosGrupo").split("\\;");
            String nmPath = ConstantesCRM.SVAZIO;
            String idGrupo = dadosGrupo[1];
            String idCampo = dadosGrupo[2];
            String nrNivel = dadosGrupo[3];

            if (dadosGrupo.length == 5) {
                nmPath = dadosGrupo[4];
            }

            nmPath = EscapeUnescape.unescape(nmPath);
            AdmGrupoCamposDependentesVO dadosCampoDependente = AdmGrupoCamposDependentesVO.Factory.newInstance();
            dadosCampoDependente.setNrNivel(nrNivel);
            dadosCampoDependente.setIdCampoDependente(idCampo);
            dadosCampoDependente.setNmPath(nmPath);
            dadosCampoDependente.addNewAdmCampoVO().setIdCampo(idResposta);
            AdmCamposFormularioVO admCamposFormularioVO = AdmCamposFormularioVO.Factory.newInstance();
            admCamposFormularioVO.setInOperacao("PROXIMONIVEL");
            admCamposFormularioVO.setIdGrupoCampos(idGrupo);
            admCamposFormularioVO.addNewAdmGruposCamposDependentesVO().addNewAdmGrupoCamposDependentesVO().set(dadosCampoDependente.copy());
            admCamposFormularioVO = camposFormularioTux.getCamposDependentes(admCamposFormularioVO, user);
            xmlDados.append(admCamposFormularioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

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


    /*
     * private String getAjaxTagsFormat(AdmGrupoCamposDependentesVO object) {
     * StringBuffer sb = new StringBuffer();
     * sb.append("<?xml version=\"1.0\" encoding=\"" + ConstantesCRM.SISO + "\"?>");
     * sb.append("<ajax-response>");
     * sb.append("<response>");
     * for (int i = 0; i < object.getAdmCampoVOArray().length; i++) {
     * sb.append("<item>");
     * sb.append("<name>").append(object.getAdmCampoVOArray(i).getNmCampo()).append("</name>");
     * sb.append("<value>").append(object.getAdmCampoVOArray(i).getIdCampo()).append("</value>");
     * sb.append("</item>");
     * }
     * sb.append("</response>");
     * sb.append("</ajax-response>");
     * return sb.toString();
     * }
     */


    /**
     * @jpf:action
     * @jpf:forward name="success" path="pesquisaContato.jsp"
     */
    protected ActionForward getDadosAparelhos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // RegistrarContatoForm form = (RegistrarContatoForm) formParam;
        // request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        StringBuffer xmlDados = new StringBuffer();
        String user = ConstantesCRM.getCurrentUser(request.getSession());

        try {
            ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
            int idTipoLinha = Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());
            String idPessoaSelecionada = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaCliente();
            String nrLinhaFiltro = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
            String dsTecnologia = ConstantesCRM.SVAZIO;

            switch (idTipoLinha) {
                case 1:
                    dsTecnologia = "PÓS-PAGO CDMA";
                    break;
                case 2:
                    dsTecnologia = "PRÉ-PAGO CDMA";
                    break;
                case 3:
                    dsTecnologia = "NÃO CLASSIFICADO";
                    break;
                case 4:
                    dsTecnologia = "CONTROLE CDMA";
                    break;
                case 5:
                    dsTecnologia = "PÓS-PAGO GSM";
                    break;
                case 6:
                    dsTecnologia = "PRÉ-PAGO GSM";
                    break;
                case 7:
                    dsTecnologia = "CONTROLE GSM";
                    break;
            }

            FiltroLinhaVO filtroLinhaVO = FiltroLinhaVO.Factory.newInstance();
            filtroLinhaVO.setIdPessoaFiltro(idPessoaSelecionada);
            filtroLinhaVO.setIdLinhaFiltro(parametros.getIdLinha());
            LupaLinhaVO pesquisaLinhaVO = LupaLinhaVO.Factory.newInstance();
            pesquisaLinhaVO = telaInicialFacadeControl.consultaLinhaVO(filtroLinhaVO, user, nrLinhaFiltro, idTipoLinha);
            pesquisaLinhaVO.getDadosLupaLinha().setDsTecnologia(dsTecnologia);
            xmlDados.append(pesquisaLinhaVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

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
     * @jpf:forward name="success" path="pesquisaContato.jsp"
     */
    protected ActionForward pesquisaArvore(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

        RegistrarContatoForm form = (RegistrarContatoForm) formParam;

        String user = ConstantesCRM.getCurrentUser(request.getSession());
        registrarContatoForm = form;

        InformeBuscaVO pesquisa = InformeBuscaVO.Factory.newInstance();
        pesquisa.setPesquisa(request.getParameter("txtPesquisa"));
        pesquisa.setPageNumber(ConstantesCRM.SZERO);
        pesquisa.addNewAtendimentoArvoreFiltrosVO();
        pesquisa.getAtendimentoArvoreFiltrosVO().setIdGrupo(form.getIdGrupoAbertura());
        pesquisa.getAtendimentoArvoreFiltrosVO().setIdSegmentacao(form.getIdSegmentacao());
        pesquisa.getAtendimentoArvoreFiltrosVO().setIdTipoCarteira(form.getIdTipoCarteira());
        pesquisa.getAtendimentoArvoreFiltrosVO().setIdTipoLinha(form.getIdTipoLinha());
        pesquisa.getAtendimentoArvoreFiltrosVO().setIdTipoRelacionamento(form.getInResponsavelAbertura());
        pesquisa.getAtendimentoArvoreFiltrosVO().setIdTipoSequencia(form.getIdFase());
        pesquisa.getAtendimentoArvoreFiltrosVO().setIdUFOperadora(form.getIdUfOperadora());

        try {
            WFListaContatosVO listaContatos = registrarContatoFacade.getListaPesquisaContatos(user, pesquisa);
            registrarContatoForm.setListaContatosVO(listaContatos);
            form.setListaContatosVO(listaContatos);

        } catch (Exception e) {
            log.error("Exception::pesquisaArvore", e);
        }

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }


    /**
     * Faz tratamento de string para caso seja necessário chamar uma função js preenchido pelo controller
     */
    public String retornaAsc(String strParam) {
        strParam = strParam.replaceAll("'", "&#39;");
        strParam = strParam.replaceAll("\"", "&#34;");
        // strParam = strParam.replaceAll("\\","&#92;");
        return strParam;
    }

    private void obterTipoComunicacaoAtendimento(String idAtendimentoSituacao, HttpServletRequest request) throws Exception {

        String user = ConstantesCRM.getCurrentUser(request.getSession());
        String idAtendimento = String.valueOf(idAtendimentoSituacao);

        RDContatoVO cvo = rAtendimentoFacade.getDadosContato(user, idAtendimento);

        // RDContatoVO cvo = (RDContatoVO)request.getAttribute("RDContatoAba");
        registrarContatoForm.getAtendimentoVO().setWFAtendimentoContatoComunicVOArray(cvo.getWFAtendimentoContatoComunicVOArray());

        if ((registrarContatoForm.getAtendimentoVO().getWFAtendimentoContatoComunicVOArray() != null) && (registrarContatoForm.getAtendimentoVO().getWFAtendimentoContatoComunicVOArray().length > 0)) {
            registrarContatoForm.setIdTipoComunicacaoAbertura(String.valueOf(registrarContatoForm.getAtendimentoVO().getWFAtendimentoContatoComunicVOArray(0).getIdTipoComunicacao()));
        } else {
            registrarContatoForm.setIdTipoComunicacaoAbertura(ConstantesCRM.SZERO);
        }
    }

    private void montaValidacao(FormularioVO formulario, String inProcessoReaRei, HttpServletRequest request) throws Exception {

        // Obtemos formulario Dinamico para el Atendimento caso ser necesario
        String scriptValidacao = "function validaFormulario(){";
        StringBuffer carregaCampos = new StringBuffer(ConstantesCRM.SVAZIO);

        try {
            String user = ConstantesCRM.getCurrentUser(request.getSession());
            FormularioProcessoVO fpvo = null;

            if (ConstantesCRM.SONE.equals(inProcessoReaRei)) {
                String idAtendimento = String.valueOf(registrarContatoForm.getIdAtendimentoSituacao());
                fpvo = rAtendimentoFacade.getFormularioProcessoVO(user, idAtendimento);
            }

            // Caso não exista cliente ou prospect carregados na tela de
            // atendimento não será permitida abertura de processo.
            ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
            String nrProtocolo = request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) != null ? (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) : ConstantesCRM.SVAZIO;
            if (ConstantesCRM.SVAZIO.equals(nrProtocolo) && parametros != null && "26".equals(parametros.getIdPessoaCliente())) {
                scriptValidacao += "alert('Não é possível abrir protocolo de atendimento. Por favor, cadastre um prospect.');return false;\n";
            }

            int tamanhoFormulario = formulario.getFormularioCampoVOArray().length;

            // if (formulario.getAdmGrupoCamposVOArray() != null) {
            // tamanhoFormularioGrupoCampos = formulario.getAdmGrupoCamposVOArray().length;
            // }
            for (int i = 0; i < tamanhoFormulario; i++) {

                carregaCampos.append(montaCarregaCampos(formulario.getFormularioCampoVOArray(i), i, fpvo));
                if (ConstantesCRM.SZERO.equals(formulario.getFormularioCampoVOArray(i).getInObrigatorio())) {
                    continue;
                }

                if (ConstantesCRM.CT_Text.equals(formulario.getFormularioCampoVOArray(i).getTipoCampoVO().getSgLayoutApresentacaoCampo()) || ConstantesCRM.CT_TextArea.equals(formulario.getFormularioCampoVOArray(i).getTipoCampoVO().getSgLayoutApresentacaoCampo())) {
                    scriptValidacao = scriptValidacao + "if(document.forms[0].all['valorCampo[" + i + "].valor'].value == ''){\n" + "alert('A questão \"" + formulario.getFormularioCampoVOArray(i).getNmCampo() + "\" deve ser respondida!');\n"
                            + "return false;\n}";

                } else if (ConstantesCRM.CT_Select.equals(formulario.getFormularioCampoVOArray(i).getTipoCampoVO().getSgLayoutApresentacaoCampo())) {
                    if (ConstantesCRM.SONE.equals(formulario.getFormularioCampoVOArray(i).getInPesquisa())) {
                        scriptValidacao = scriptValidacao + "if(document.forms[0].all['valorCampo[" + i + "].valor'].value == ''){\n" + "alert('A questão \"" + formulario.getFormularioCampoVOArray(i).getNmCampo() + "\" deve ser respondida!');\n"
                                + "return false;\n}";
                    } else {
                        if (formulario.getFormularioCampoVOArray(i).getFormularioCampoValorVOArray().length > 0) {
                            scriptValidacao = scriptValidacao + "if(document.forms[0].all['valorCampo[" + i + "].valor'].selectedIndex == 0){\n" + "alert('A questão \"" + formulario.getFormularioCampoVOArray(i).getNmCampo()
                                    + "\" deve ser respondida!');\n" + "return false;\n}";
                        }
                    }

                } else if (ConstantesCRM.CT_Radio.equals(formulario.getFormularioCampoVOArray(i).getTipoCampoVO().getSgLayoutApresentacaoCampo())) {
                    if (formulario.getFormularioCampoVOArray(i).getFormularioCampoValorVOArray().length > 0) {
                        scriptValidacao = scriptValidacao + "respondida = false;\n" + "if(document.forms[0].all['valorCampo[" + i + "].valor'].length != null){\n" + "for(i=0;i<document.forms[0].all['valorCampo[" + i + "].valor'].length;i++){"
                                + "if(document.forms[0].all['valorCampo[" + i + "].valor'][i].checked == true){\n" + "respondida = true;\n" + "}\n" + "}\n" + "}\n" + "else{\n" + "if(document.forms[0].all['valorCampo[" + i
                                + "].valor'].checked == true){\n" + "respondida = true;\n" + "}\n" + "}\n" + "if(!respondida){" + "alert('A questão \"" + formulario.getFormularioCampoVOArray(i).getNmCampo() + "\" deve ser respondida!');\n"
                                + "return false;}";
                    }

                } else if (ConstantesCRM.CT_CheckBox.equals(formulario.getFormularioCampoVOArray(i).getTipoCampoVO().getSgLayoutApresentacaoCampo())) {
                    if (formulario.getFormularioCampoVOArray(i).getFormularioCampoValorVOArray().length > 0) {
                        scriptValidacao = scriptValidacao + "respondida = false;\n" + "if(document.forms[0].all['valorCampo[" + i + "].valorArray'].length != null){\n" + "for(i=0;i<document.forms[0].all['valorCampo[" + i
                                + "].valorArray'].length;i++){\n" + "if(document.forms[0].all['valorCampo[" + i + "].valorArray'][i].checked == true){\n" + "respondida = true;\n" + "}\n" + "}\n" + "}\n" + "else{\n"
                                + "if(document.forms[0].all['valorCampo[" + i + "].valorArray'].checked == true){\n" + "respondida = true;\n" + "}\n" + "}\n" + "if(!respondida){" + "alert('A questão \""
                                + formulario.getFormularioCampoVOArray(i).getNmCampo() + "\" deve ser respondida!');\n" + "return false;}";
                    }
                }
            }

            // Contador para manutenção da contagem após término de tratamento de campos dinâmicos
            int contadorContinuado = tamanhoFormulario;

            // Tratamento da validação de campos de formulários membros de um determinado grupo (AdmGrupoCamposVO)
            for (int j = 0; j < formulario.getAdmGrupoCamposVOArray().length; j++) {

                for (int i = 0; i < formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray().length; i++) {

                    carregaCampos.append(montaCarregaCampos(formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i), contadorContinuado, fpvo));
                    if (!ConstantesCRM.SONE.equals(formulario.getAdmGrupoCamposVOArray(j).getIdTipoGrupo()) || ConstantesCRM.SZERO.equals(formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getInObrigatorio())) {
                        contadorContinuado++;
                        continue;
                    }

                    if (ConstantesCRM.CT_Text.equals(formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getTipoCampoVO().getSgLayoutApresentacaoCampo())
                            || ConstantesCRM.CT_TextArea.equals(formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getTipoCampoVO().getSgLayoutApresentacaoCampo())) {
                        scriptValidacao = scriptValidacao + "if(document.forms[0].all['valorCampo[" + contadorContinuado + "].valor'].value == ''){\n" + "alert('A questão \""
                                + formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getNmCampo() + "\" deve ser respondida!');\n" + "return false;\n}";
                        contadorContinuado++;

                    } else if (ConstantesCRM.CT_Select.equals(formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getTipoCampoVO().getSgLayoutApresentacaoCampo())) {
                        if (ConstantesCRM.SONE.equals(formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getInPesquisa())) {
                            scriptValidacao = scriptValidacao + "if(document.forms[0].all['valorCampo[" + contadorContinuado + "].valor'].value == ''){\n" + "alert('A questão \""
                                    + formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getNmCampo() + "\" deve ser respondida!');\n" + "return false;\n}";
                            contadorContinuado++;

                        } else {
                            if (formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getFormularioCampoValorVOArray().length > 0) {
                                scriptValidacao = scriptValidacao + "if(document.forms[0].all['valorCampo[" + contadorContinuado + "].valor'].selectedIndex == 0){\n" + "alert('A questão \""
                                        + formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getNmCampo() + "\" deve ser respondida!');\n" + "return false;\n}";
                                contadorContinuado++;
                            }
                        }

                    } else if (ConstantesCRM.CT_Radio.equals(formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getTipoCampoVO().getSgLayoutApresentacaoCampo())) {
                        if (formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getFormularioCampoValorVOArray().length > 0) {
                            scriptValidacao = scriptValidacao + "respondida = false;\n" + "if(document.forms[0].all['valorCampo[" + contadorContinuado + "].valor'].length != null){\n" + "for(i=0;i<document.forms[0].all['valorCampo["
                                    + contadorContinuado + "].valor'].length;i++){" + "if(document.forms[0].all['valorCampo[" + contadorContinuado + "].valor'][i].checked == true){\n" + "respondida = true;\n" + "}\n" + "}\n" + "}\n" + "else{\n"
                                    + "if(document.forms[0].all['valorCampo[" + contadorContinuado + "].valor'].checked == true){\n" + "respondida = true;\n" + "}\n" + "}\n" + "if(!respondida){" + "alert('A questão \""
                                    + formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getNmCampo() + "\" deve ser respondida!');\n" + "return false;}";
                            contadorContinuado++;
                        }

                    } else if (ConstantesCRM.CT_CheckBox.equals(formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getTipoCampoVO().getSgLayoutApresentacaoCampo())) {
                        if (formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getFormularioCampoValorVOArray().length > 0) {
                            scriptValidacao = scriptValidacao + "respondida = false;\n" + "if(document.forms[0].all['valorCampo[" + i + "].valorArray'].length != null){\n" + "for(i=0;i<document.forms[0].all['valorCampo[" + contadorContinuado
                                    + "].valorArray'].length;i++){\n" + "if(document.forms[0].all['valorCampo[" + contadorContinuado + "].valorArray'][i].checked == true){\n" + "respondida = true;\n" + "}\n" + "}\n" + "}\n" + "else{\n"
                                    + "if(document.forms[0].all['valorCampo[" + contadorContinuado + "].valorArray'].checked == true){\n" + "respondida = true;\n" + "}\n" + "}\n" + "if(!respondida){" + "alert('A questão \""
                                    + formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray(i).getNmCampo() + "\" deve ser respondida!');\n" + "return false;}";
                            contadorContinuado++;
                        }
                    }
                }
            }

        } catch (Exception e) {
            log.error("montaValidacao::", e);
        }

        // Validacao de pesquisa de Endereço
        if ("ENDERECO".equalsIgnoreCase(formulario.getInFuncionalidade()) || "ENDERECOCOMPLETO".equals(formulario.getInFuncionalidade())) {
            scriptValidacao = scriptValidacao + "if ($('nrCEP').value!=''){";
            scriptValidacao = scriptValidacao + "   if ($('nrEndereco').value==''){alert('Por favor, digite o número do logradouro.');\n return false;}\n";
            scriptValidacao = scriptValidacao + "}";
        }

        scriptValidacao = scriptValidacao + "return true;}";

        registrarContatoForm.setScriptValidacao(scriptValidacao);
        registrarContatoForm.setScriptCarrega(carregaCampos.toString());
    }


    private StringBuffer montaCarregaCampos(FormularioCampoVO formularioCampo, int indice, FormularioProcessoVO formularioAtd) {
        StringBuffer montagemCampos = new StringBuffer(ConstantesCRM.SVAZIO);
        int numRegistros = 0;
        try {
            if (formularioAtd != null) {
                int indAtd = -1;
                for (int i = 0; i < formularioAtd.getFormularioVO().getFormularioCampoVOArray().length; i++) {
                    if (formularioAtd.getFormularioVO().getFormularioCampoVOArray(i).getIdCampo() == formularioCampo.getIdCampo()) {
                        indAtd = i;
                        break;
                    }
                }
                if (indAtd >= 0) {
                    String valorCampo;
                    String valor;
                    if ((formularioCampo.getFormularioCampoValorVOArray() != null) && (formularioCampo.getFormularioCampoValorVOArray().length > 0)) {
                        numRegistros = formularioCampo.getFormularioCampoValorVOArray().length;
                        for (int j = 0; j < formularioCampo.getFormularioCampoValorVOArray().length; j++) {
                            valorCampo = formularioCampo.getFormularioCampoValorVOArray(j).getValor();
                            for (int k = 0; k < formularioAtd.getFormularioVO().getFormularioCampoVOArray(indAtd).getFormularioCampoValorVOArray().length; k++) {
                                valor = formularioAtd.getFormularioVO().getFormularioCampoVOArray(indAtd).getFormularioCampoValorVOArray(k).getValor();
                                if (valor.equals(valorCampo)) {
                                    montagemCampos.append(inserirCarregaCampo(formularioCampo.getInPesquisa(), formularioCampo.getTipoCampoVO().getSgLayoutApresentacaoCampo(), indice, j, valor, numRegistros));
                                    break;
                                }
                            }
                        }
                    } else {
                        valor = formularioAtd.getFormularioVO().getFormularioCampoVOArray(indAtd).getFormularioCampoValorVOArray(0).getValor();
                        montagemCampos.append(inserirCarregaCampo(formularioCampo.getInPesquisa(), formularioCampo.getTipoCampoVO().getSgLayoutApresentacaoCampo(), indice, -1, valor, 1));
                    }
                }
            }
        } catch (Exception e) {
            log.error("montaCarregaCampos::", e);
        }

        return montagemCampos;
    }


    private String inserirCarregaCampo(String inPesquisa, String sgLayoutApresentacaoCampo, int indice, int subindice, String valor, int numRegistros) {
        String textoCarregaCampo = ConstantesCRM.SVAZIO;
        try {
            if (ConstantesCRM.SONE.equals(inPesquisa)) {
                textoCarregaCampo = "document.all[\"valorCampo[" + indice + "].valor\"].value = '" + valor + "';";
            
            } else {
                if (ConstantesCRM.CT_Text.equals(sgLayoutApresentacaoCampo) || ConstantesCRM.CT_TextArea.equals(sgLayoutApresentacaoCampo)) {
                    textoCarregaCampo = "document.all[\"valorCampo[" + indice + "].valor\"].value = '" + valor + "';";
                
                } else if (ConstantesCRM.CT_Select.equals(sgLayoutApresentacaoCampo)) {
                    if (subindice >= 0) {
                        int subIndiceSelect = subindice + 1;
                        textoCarregaCampo = "document.all[\"valorCampo[" + indice + "].valor\"].selectedIndex = " + subIndiceSelect + ";";
                    }
                
                } else if (ConstantesCRM.CT_Radio.equals(sgLayoutApresentacaoCampo)) {
                    if (subindice >= 0) {
                        if (numRegistros > 1) {
                            textoCarregaCampo = "document.all[\"valorCampo[" + indice + "].valor\"][" + subindice + "].checked = true;";
                        } else {
                            textoCarregaCampo = "document.all[\"valorCampo[" + indice + "].valor\"].checked = true;";
                        }
                    }
                } else {
                    if (subindice >= 0) {
                        if (numRegistros > 1) {
                            textoCarregaCampo = "document.all[\"valorCampo[" + indice + "].valorArray\"][" + subindice + "].checked = true;";
                        } else {
                            textoCarregaCampo = "document.all[\"valorCampo[" + indice + "].valorArray\"].checked = true;";
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("inserirCarregaCampo::", e);
        }

        return textoCarregaCampo;
    }


    /**
     * @jpf:action
     * @jpf:forward name="success" path="confirmadaInsistencia.jsp"
     */
    protected ActionForward registrarInsistencia(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RegistrarContatoForm form = (RegistrarContatoForm) formParam;
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        log.debug("RegistrarContatoController:registrarInsistencia.do - Inicio do Metodo]");
        // Monta o log da operação se possível
        String user = ConstantesCRM.getCurrentUser(request.getSession());
        AtendimentoVO[] atendimentosVO = null;
        AtendimentoVO[] atendimentos = configuraAtendimentos(atendimentosVO, ConstantesCRM.SFOUR, ConstantesCRM.SZERO, form.getIdGrupoAbertura(), null, form.getObservacaoAtendimento(), this.registrarContatoForm.getIdAtendimentoSituacao());
        // WFAcaoRetornoVO wFAcaoRetornoVO =
        atendimentoWorkflowFacade.insistenciaGravar(user, atendimentos);
        log.debug("RegistrarContatoController:registrarInsistencia.do - Fim do Metodo]");
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }


    /**
     * @jpf:action
     * @jpf:forward name="success" path="confirmadoComentario.jsp"
     */
    protected ActionForward registrarComentario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RegistrarContatoForm form = (RegistrarContatoForm) formParam;
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        log.debug("RegistrarContatoController:registrarComentario.do - Inicio do Metodo]");
        String user = ConstantesCRM.getCurrentUser(request.getSession());
        AtendimentoVO[] atendimentosVO = null;
        AtendimentoVO[] atendimentos = configuraAtendimentos(atendimentosVO, "24", ConstantesCRM.SZERO, form.getIdGrupoAbertura(), null, form.getObservacaoAtendimento(), this.registrarContatoForm.getIdAtendimentoSituacao());
        // WFAcaoRetornoVO wFAcaoRetornoVO =
        atendimentoWorkflowFacade.insistenciaGravar(user, atendimentos);
        log.debug("RegistrarContatoController:registrarComentario.do - Fim do Metodo]");
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="ConsultaLinhas.jsp"
     */
    protected ActionForward obterLinhas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("RegistrarContatoController:obterLinhas: Inicio");
        RegistrarContatoForm form = (RegistrarContatoForm) formParam;
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        String user = ConstantesCRM.getCurrentUser(request.getSession());
        try {
            // registrarContatoForm = form;
            ContaVO contaVO = registrarContatoFacade.obterLinhas(user, form.getContaSelecionada(), form.getIdLinha());

            // une cdArea e nrLinha para exibição
            for (int i = 0; i < contaVO.getLinhaVOArray().length; i++) {
                String area = contaVO.getLinhaVOArray(i).getCdAreaRegistro();
                String fone = contaVO.getLinhaVOArray(i).getNrLinha();

                log.debug("RegistrarContatoController:obterLinhas.do - ] Telefone " + area + fone);
                String nrTelefoneCompleto = area.concat(fone);

                try {
                    nrTelefoneCompleto = ConstantesCRM.formatPhoneNumber(nrTelefoneCompleto);

                } catch (Exception e) {
                    log.error("RegistrarContatoController:obterLinhas.do Error ao formatar linha ", e);
                }

                contaVO.getLinhaVOArray(i).setDsAuxiliar(nrTelefoneCompleto);
            }

            registrarContatoForm.setContaSelecionadaVO(contaVO);
            registrarContatoForm.setContaSelecionada(form.getContaSelecionada());
            registrarContatoForm.setIdLinha(form.getIdLinha());

        } catch (Exception e) {
            log.error("RegistrarContatoController:obterLinhas.do] Erro ", e);
        }

        log.debug("RegistrarContatoController:obterLinhas.do - Fim do Metodo]");
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="iPesquisaFormulario.jsp"
     */
    protected ActionForward obterPesquisaFormulario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RegistrarContatoForm form = (RegistrarContatoForm) formParam;
        log.debug("RegistrarContatoController:obterPesquisaFormulario.do - Inicio do Metodo]");

        try {
            String user = ConstantesCRM.getCurrentUser(request.getSession());

            // registrarContatoForm = form;

            registrarContatoForm.setFormularioCampoVO(registrarContatoFacade.obtemPesquisaFormulario(user, form.getTextoPesquisa(), form.getIdCampo()));
            log.debug("RegistrarContatoController:obterPesquisaFormulario.do - Fim do Metodo]");

        } catch (Exception e) {
            log.error("obterPesquisaFormulario::", e);
        }

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="ConsultaComunicacao.jsp"
     */
    protected ActionForward obterComunicacao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        RegistrarContatoForm form = (RegistrarContatoForm) formParam;

        log.debug("RegistrarContatoController:obterComunicacao.do - Inicio do Metodo]");
        String user = ConstantesCRM.getCurrentUser(request.getSession());

        // obtem os tipos de comunicacao
        try {
            AtendimentoVO atendimentoVO = registrarContatoFacade.obtemComunicacao(form.getIdPessoa(), 
                                                                                  form.getTipoComunicacaoSelecionada() == null ? ConstantesCRM.SVAZIO : form.getTipoComunicacaoSelecionada(), 
                                                                                  user);
            
            if (atendimentoVO.getPessoaVO() != null) {
                AtendimentoTipoComunicacaoVO atenTipoComunicacaoVO = atendimentoVO.getPessoaVO().getAtendimentoTipoComunicacaoVOArray(0);
                StringBuffer dsAuxiliar = new StringBuffer();
                AtendimentoComunicacaoVO atendimentoComunicacaoVO = null;
                String strDescricao = ConstantesCRM.SVAZIO;
                List lst = new ArrayList();
                // Obtém as comunicações
                for (int j = 0, lengthC = atenTipoComunicacaoVO.getAtendimentoComunicacaoVOArray().length; j < lengthC; j++) {
                    if (atendimentoComunicacaoVO != null) {
                        strDescricao = atendimentoComunicacaoVO.getDescricao();
                        if (strDescricao.equals(atenTipoComunicacaoVO.getAtendimentoComunicacaoVOArray(j).getDescricao())) {
                            lst.add(new Integer(j));
                            continue;
                        }
                    }
                    atendimentoComunicacaoVO = atenTipoComunicacaoVO.getAtendimentoComunicacaoVOArray(j);
                    dsAuxiliar = new StringBuffer();
                    dsAuxiliar.append(form.getTipoComunicacaoSelecionada());
                    dsAuxiliar.append(",");
                    dsAuxiliar.append(atenTipoComunicacaoVO.getAtendimentoComunicacaoVOArray(j).getDescricao());
                    // Monta o código auxiliar com "idTipoComunicacao , idComunicacao"
                    atendimentoComunicacaoVO.setDsAuxiliar(dsAuxiliar.toString());
                }
                // Remove registros duplicados
                for (int i = lst.size(); i > 0; i--) {
                    atenTipoComunicacaoVO.removeAtendimentoComunicacaoVO(((Integer) lst.get(i - 1)).intValue());
                }

                // Atualiza ActionForm
                registrarContatoForm.setTipoComunicacaoSelecionadaVO(atenTipoComunicacaoVO);
                registrarContatoForm.setTipoComunicacaoSelecionada(form.getTipoComunicacaoSelecionada());
            }

        } catch (Exception e) {
            log.error("RegistrarContatoController:obterComunicacao.do Erro] ", e);
        }

        log.debug("RegistrarContatoController:obterComunicacao.do - Fim do Metodo]");

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="ProcessoCorrenteExcluir.jsp"
     */
    protected ActionForward excluirProcessoCorrente(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RegistrarContatoForm form = (RegistrarContatoForm) formParam;
        log.debug("RegistrarContatoController:excluirProcessoCorrente.do - Inicio do Metodo]");
        String user = ConstantesCRM.getCurrentUser(request.getSession());
        // excluir o processo
        String idAtendimento = form.getIdAtendimentoExclusao();
        registrarContatoFacade.excluirProcessoCorrente(user, idAtendimento);
        registrarContatoForm.setRowIndex(form.getRowIndex());
        log.debug("RegistrarContatoController:excluirProcessoCorrente.do - Fim do Metodo]");
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="CodigoProcesso.jsp"
     * @jpf:forward name="successReabertura" path="CodigoReabertura.jsp"
     * @jpf:forward name="successReincidencia" path="CodigoReincidencia.jsp"
     * @jpf:forward name="redirectErrorParam" path="redirectArvoreContato.jsp"
     */
    protected ActionForward encaminharProcesso(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RegistrarContatoForm form = (RegistrarContatoForm) formParam;
        log.debug("RegistrarContatoController:encaminharProcesso.do - Inicio do Metodo]");
        String user = ConstantesCRM.getCurrentUser(request.getSession());
        
        // String resposta = ConstantesCRM.SVAZIO;
        String idTipoComunicacao = ConstantesCRM.STHREE;
        ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
        registrarContatoForm = form;
        
        /*
         * SM517 - rotina executada se forma de contato "Celular" não existir na base. tipoOperacao -> 2 (Encaminhar)
         * idTipoRelacionamento 6 -> Prospect
         */
        if (ConstantesCRM.SFALSE.equals(form.getInCelular().toUpperCase()) && ConstantesCRM.STWO.equals(form.getTipoOperacao()) && parametros != null && !ConstantesCRM.SVAZIO.equals(parametros.getNrLinha())
                && !ConstantesCRM.SSIX.equals(parametros.getIdTipoRelacionamento())) {
            TipoComunicacaoVO tipoComunicacaoVO = TipoComunicacaoVO.Factory.newInstance();
            tipoComunicacaoVO = registrarContatoFacade.getIdTipoComunicacaoBySG(user, "CELULAR");
            idTipoComunicacao = tipoComunicacaoVO.getIdTipoComunicacao();
            LupaClienteVO lupaClienteVO = LupaClienteVO.Factory.newInstance();
            lupaClienteVO.addNewDadosLupaCliente();
            lupaClienteVO.getDadosLupaCliente().setIdPessoa(form.getIdPessoa());
            lupaClienteVO.addNewDadosAbaLupaCliente();
            lupaClienteVO.getDadosAbaLupaCliente().addNewComunicacaoVO();
            lupaClienteVO.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setDsContato(getTelMask(parametros.getNrLinha()));
            lupaClienteVO.getDadosAbaLupaCliente().getComunicacaoVOArray(0).addNewTipoComunicacaoVO();
            lupaClienteVO.getDadosAbaLupaCliente().getComunicacaoVOArray(0).getTipoComunicacaoVO().setIdTipoComunicacao(tipoComunicacaoVO.getIdTipoComunicacao());
            lupaClienteFacadeControl.setSalvarNovaComunicacao(user, lupaClienteVO);
        }
        
        // Definições
        AtendimentoVO atendimentoRegistroVO = AtendimentoVO.Factory.newInstance();
        AtendimentoRetornoVO[] atendimentoRetornoVO = null;
        AtendimentoTipoComunicacaoVO[] atendimentoTipoComunicacaoVO = null;
        ContaVO contaVO = null;
        LinhaVO linhaVO = null;
        
        // StringTokenizer stringTokenizer = null;
        int tamanho = 0;
        int contador = 0;
        
        if (form.getIdAtendimento() != null && !(ConstantesCRM.SVAZIO.equals(form.getIdAtendimento()))) {
            atendimentoRegistroVO.setIdAtendimento(form.getIdAtendimento());
            // atendimentoRegistroVO.setNrProtocolo(form.getNrProtocolo());
            log.debug("RegistrarContatoController:encaminharProcesso.do - [idAtendimento = " + form.getIdAtendimento() + "]");
        
        } else {
            if (form.getIdAtendimentoSituacao().length() > 1) {
                atendimentoRegistroVO.setIdAtendimento(form.getIdAtendimentoSituacao());
                // atendimentoRegistroVO.setNrProtocolo(form.getNrProtocoloSituacao());
                log.debug("RegistrarContatoController:encaminharProcesso.do - [idAtendimento = " + form.getIdAtendimentoSituacao() + "]");
            }
        }
        
        atendimentoRegistroVO.setCanalVO(CanalVO.Factory.newInstance());
        if (!ConstantesCRM.SVAZIO.equals(form.getIdCanal())) {
            atendimentoRegistroVO.getCanalVO().setIdCanal(Integer.parseInt(form.getIdCanal()));
            log.debug("RegistrarContatoController:encaminharProcesso.do - [idCanal = " + form.getIdCanal() + "]");
        
        } else {
            atendimentoRegistroVO.getCanalVO().setIdCanal(1);
            log.debug("RegistrarContatoController:encaminharProcesso.do - [idCanal = " + atendimentoRegistroVO.getCanalVO().getIdCanal() + "]");
        }
        
        atendimentoRegistroVO.setProcedenciaVO(ProcedenciaVO.Factory.newInstance());
        if (form.getIdProcedencia() != null && !ConstantesCRM.SVAZIO.equals(form.getIdProcedencia())) {
            atendimentoRegistroVO.getProcedenciaVO().setIdProcedencia(Integer.parseInt(form.getIdProcedencia()));
            log.debug("RegistrarContatoController:encaminharProcesso.do - [idProcedencia = " + form.getIdProcedencia() + "]");
        } else {
            atendimentoRegistroVO.getProcedenciaVO().setIdProcedencia(1);
            log.debug("RegistrarContatoController:encaminharProcesso.do - [idProcedencia = " + atendimentoRegistroVO.getProcedenciaVO().getIdProcedencia() + "]");
        }
        
        // atendimentoRegistroVO.setPessoaVO( this.formEntrada.getPessoaVO() );
        atendimentoRegistroVO.setPessoaVO(PessoaVO.Factory.newInstance());
        atendimentoRegistroVO.getPessoaVO().setIdPessoa(Long.parseLong(ConstantesCRM.SONE.equals(form.getIdClienteDePara()) ? form.getIdUsuarioDePara() : form.getIdClienteDePara()));
        log.debug("RegistrarContatoController:encaminharProcesso.do - [idClienteDePara = " + form.getIdClienteDePara() + "]");
        
        // atendimentoRegistroVO.setUsuarioLinhaVO( this.formEntrada.getUsuarioLinhaVO() );
        atendimentoRegistroVO.setUsuarioLinhaVO(UsuarioLinhaVO.Factory.newInstance());
        atendimentoRegistroVO.getUsuarioLinhaVO().setIdPessoa(ConstantesCRM.SONE.equals(form.getIdUsuarioDePara()) ? form.getIdClienteDePara() : form.getIdUsuarioDePara());
        log.debug("RegistrarContatoController:encaminharProcesso.do - [idPessoa = " + form.getIdUsuarioDePara() + "]");
        
        // atendimentoRegistroVO.setArvoreAtendimentoVO( this.formEntrada.getArvoreAtendimentoVO() );
        atendimentoRegistroVO.setArvoreAtendimentoVO(ArvoreAtendimentoVO.Factory.newInstance());
        atendimentoRegistroVO.getArvoreAtendimentoVO().setIdContato(Integer.parseInt(form.getIdContato()));
        log.debug("RegistrarContatoController:encaminharProcesso.do - [idContato = " + form.getIdContato() + "]");
        
        atendimentoRegistroVO.getArvoreAtendimentoVO().setCarterizacaoVO(CarterizacaoVO.Factory.newInstance());
        atendimentoRegistroVO.getArvoreAtendimentoVO().getCarterizacaoVO().setIdTipoCarteira(Integer.parseInt(form.getIdTipoCarteira()));
        log.debug("RegistrarContatoController:encaminharProcesso.do - [idTipoCarteira = " + form.getIdTipoCarteira() + "]");
        
        atendimentoRegistroVO.getArvoreAtendimentoVO().setSegmentacaoVO(SegmentacaoVO.Factory.newInstance());
        atendimentoRegistroVO.getArvoreAtendimentoVO().getSegmentacaoVO().setIdSegmentacao(Integer.parseInt(form.getIdSegmentacao()));
        log.debug("RegistrarContatoController:encaminharProcesso.do - [idSegmentacao = " + form.getIdSegmentacao() + "]");
        
        atendimentoRegistroVO.setIdDiscador(Integer.parseInt(form.getIdDiscador()));
        log.debug("RegistrarContatoController:encaminharProcesso.do - [idDiscador = " + form.getIdDiscador() + "]");
        
        atendimentoRegistroVO.setInTipoPessoa(form.getInTipoPessoa());
        log.debug("RegistrarContatoController:encaminharProcesso.do - [inTipoPessoa = " + form.getInTipoPessoa() + "]");
        
        // tpOperacao: 1=fechar, 2=encaminhar
        atendimentoRegistroVO.setTpOperacao(form.getTipoOperacao());
        log.debug("RegistrarContatoController:encaminharProcesso.do - [tpOperacao = " + form.getTipoOperacao() + "]");
        
        if ("-1".equals(form.getTipoComunicacaoSelecionada())) {
            tamanho = 0;
        } else {
            // Monta os retornos selecionado pelo usuário
            tamanho = form.getComunicacaoSelecionada().length;
        }
        
        atendimentoRetornoVO = new AtendimentoRetornoVOImpl[tamanho];
        for (contador = 0; contador < tamanho; contador++) {
            atendimentoRetornoVO[contador] = AtendimentoRetornoVO.Factory.newInstance();
        }
        
        for (contador = 0; contador < tamanho; contador++) {
            String[] tipoComunArray = form.getComunicacaoSelecionada()[contador].split(",");
            if (tipoComunArray.length == 1) {
                atendimentoRetornoVO[contador].setIdTipoComunicacao(Integer.parseInt(idTipoComunicacao));
                atendimentoRetornoVO[contador].setDsComunicacao(tipoComunArray[0]);
            } else {
                atendimentoRetornoVO[contador].setIdTipoComunicacao(Integer.parseInt(tipoComunArray[0]));
                atendimentoRetornoVO[contador].setDsComunicacao(tipoComunArray[1]);
            }
        }
        atendimentoRegistroVO.setAtendimentoRetornoVOArray(atendimentoRetornoVO);
        // Monta a observação
        atendimentoRegistroVO.setObservacao(form.getObservacaoAtendimento());
        log.debug("RegistrarContatoController:encaminharProcesso.do - [observacao = " + form.getObservacaoAtendimento() + "]");
        // monta o número de telefone informado (No. telefone contato)
        String nrLinha = ConstantesCRM.unmask(form.getTelContato(), "()- ");
        if (nrLinha.length() < 10) {
            nrLinha = ConstantesCRM.SZERO + nrLinha;
        }
        atendimentoRegistroVO.setNrTelefone(nrLinha);
        log.debug("RegistrarContatoController:encaminharProcesso.do - [nrTelefone = " + nrLinha + "]");
        
        // Monta a situação do contato
        if (form.getIdAtendimentoSituacao().length() > 0) {
            atendimentoRegistroVO.setAtendimentoSituacaoVO(AtendimentoSituacaoVO.Factory.newInstance());
            atendimentoRegistroVO.getAtendimentoSituacaoVO().setIdAtendimento(form.getIdAtendimentoSituacao());
            // atendimentoRegistroVO.getAtendimentoSituacaoVO().setNrProtocolo(form.getNrProtocoloSituacao());
            atendimentoRegistroVO.getAtendimentoSituacaoVO().setIdTipoReaberturaProcesso(form.getIdTipoReaberturaProcesso());
            atendimentoRegistroVO.getAtendimentoSituacaoVO().setNmTipo(form.getNmTipo());
            atendimentoRegistroVO.getAtendimentoSituacaoVO().setQtDias(form.getQtDias());
            log.debug("RegistrarContatoController:encaminharProcesso.do - [idAtendimento = " + form.getIdAtendimentoSituacao() + "]");
            log.debug("RegistrarContatoController:encaminharProcesso.do - [idTipoReaberturaProcesso = " + form.getIdTipoReaberturaProcesso() + "]");
            log.debug("RegistrarContatoController:encaminharProcesso.do - [nmTipo = " + form.getNmTipo() + "]");
            log.debug("RegistrarContatoController:encaminharProcesso.do - [qtDias = " + form.getQtDias() + "]");
        }
        
        // Monta o nome do contato
        if ((form.getNomeContatoAlterado() != null) && (form.getNomeContatoAlterado().length() > 0)) {
            atendimentoRegistroVO.setNmContato(form.getNomeContatoAlterado());
        } else {
            atendimentoRegistroVO.setNmContato(" ");
        }
        
        log.debug("RegistrarContatoController:encaminharProcesso.do - [nmContato = " + atendimentoRegistroVO.getNmContato() + "]");
        // Monta a tag nmFalandoCom com valor de NomeContato
        if ((form.getNomeContato() != null) && (form.getNomeContato().length() > 0)) {
            atendimentoRegistroVO.setNmFalandoCom(form.getNomeContato());
            log.debug("RegistrarContatoController:encaminharProcesso.do - [nmFalandoCom = " + form.getNomeContato() + "]");
        }
        
        // Monta o id da chamada telefônica
        atendimentoRegistroVO.setIdChamadaTelefonica(form.getIdChamadaTelefonica());
        log.debug("RegistrarContatoController:encaminharProcesso.do - [idChamadaTelefonica = " + form.getIdChamadaTelefonica() + "]");
        
        // Monta o nome do contato
        atendimentoRegistroVO.setIdGrupoAbertura(Long.parseLong(form.getIdGrupoAbertura()));
        log.debug("RegistrarContatoController:encaminharProcesso.do - [idGrupoAbertura = " + form.getIdGrupoAbertura() + "]");

        // Monta o nome do contato
        atendimentoRegistroVO.setInResponsavelAbertura(form.getInResponsavelAbertura());
        log.debug("RegistrarContatoController:encaminharProcesso.do - [inResponsavelAbertura = " + form.getInResponsavelAbertura() + "]");
        
        // Monta o tipo de comunicação
        atendimentoTipoComunicacaoVO = new AtendimentoTipoComunicacaoVOImpl[1];
        atendimentoTipoComunicacaoVO[0] = AtendimentoTipoComunicacaoVO.Factory.newInstance();
        atendimentoRegistroVO.getPessoaVO().setAtendimentoTipoComunicacaoVOArray(atendimentoTipoComunicacaoVO);
        atendimentoRegistroVO.getPessoaVO().getAtendimentoTipoComunicacaoVOArray(0).setAtendimentoComunicacaoVOArray(new AtendimentoComunicacaoVOImpl[0]);
        
        // Monta a conta selecionada se necessário
        atendimentoRegistroVO.setContas(Contas.Factory.newInstance());
        contaVO = ContaVO.Factory.newInstance();
        atendimentoRegistroVO.getContas().setContaVOArray(new ContaVO[] { contaVO });
        
        // Monta a linha se necessário
        linhaVO = LinhaVO.Factory.newInstance();
        atendimentoRegistroVO.getContas().getContaVOArray(0).setLinhaVOArray(new LinhaVO[] { linhaVO });
        if (form.getContaSelecionada() != null && !(ConstantesCRM.SVAZIO.equalsIgnoreCase(form.getContaSelecionada()))) {
            atendimentoRegistroVO.getContas().getContaVOArray(0).setIdConta(Integer.parseInt(form.getContaSelecionada()));
            log.debug("RegistrarContatoController:encaminharProcesso.do - [idConta = " + form.getContaSelecionada() + "]");
        }
        
        if (form.getLinhaSelecionada() != null && !(ConstantesCRM.SVAZIO.equalsIgnoreCase(form.getLinhaSelecionada()))) {
            atendimentoRegistroVO.getContas().getContaVOArray(0).getLinhaVOArray(0).setIdPessoaLinhaHistorico(Long.parseLong(form.getLinhaSelecionada()));
            log.debug("RegistrarContatoController:encaminharProcesso.do - [idPessoaLinhaHistorico = " + form.getLinhaSelecionada() + "]");
        }
        atendimentoRegistroVO.setIdBaixa(form.getIdBaixa());
        log.debug("RegistrarContatoController:encaminharProcesso.do - [idBaixa = " + form.getIdBaixa() + "]");
        
        atendimentoRegistroVO.setIdBaixaMensagem(form.getIdBaixaMensagem());
        log.debug("RegistrarContatoController:encaminharProcesso.do - [idBaixaMensagem = " + form.getIdBaixaMensagem() + "]");
        
        atendimentoRegistroVO.setObservacaoFechamento(form.getObservacaoFechamento());
        log.debug("RegistrarContatoController:encaminharProcesso.do - [observacaoFechamento = " + form.getObservacaoFechamento() + "]");
        
        atendimentoRegistroVO.setIdLinhaAtendimento(form.getIdLinhaAtendimento());
        log.debug("RegistrarContatoController:encaminharProcesso.do - [idLinhaAtendimento = " + form.getIdLinhaAtendimento() + "]");
        
        atendimentoRegistroVO.setIdUfOperadora(form.getIdUfOperadora());
        log.debug("RegistrarContatoController:encaminharProcesso.do - [idUfOperadora = " + form.getIdUfOperadora() + "]");
        
        atendimentoRegistroVO.setIdTipoLinha(form.getIdTipoLinha());
        log.debug("RegistrarContatoController:encaminharProcesso.do - [idTipoLinha = " + form.getIdTipoLinha() + "]");
        
        /************************************************/
        /* Processa a gravação das Linhas Associadas */
        /************************************************/
        if (form.getNrLinhasSelecionadas() != null && form.getNrLinhasSelecionadas().length > 0) {
            int tam = form.getNrLinhasSelecionadas().length;
            for (int x = 0; x < tam; x++) {
                String[] nrLinhasArray = form.getNrLinhasSelecionadas()[x].split("\\|");
                LinhasAssociadasVO linhasAssociadasVO = atendimentoRegistroVO.addNewLinhasAssociadasVO();
                linhasAssociadasVO.setCdConta(nrLinhasArray[0]);
                linhasAssociadasVO.setNrTelefone(nrLinhasArray[1]);
            }
        }
        
        /************************************************/
        /* Processa a gravação do formulário preenchido */
        /************************************************/
        if (atendimentoVOFacade == null) {
            atendimentoVOFacade = (AtendimentoVO) request.getSession().getAttribute("atendimentoVOFacade");
        }
        
        if (atendimentoVOFacade != null && atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO() != null) {
            FormularioVO formulario = (FormularioVO) atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().copy();
            // interage no array de campos, atualizando com o valor preenchido
            FormularioCampoVO[] FormularioCampoArray = formulario.getFormularioCampoVOArray();
            int contadorContinuado = FormularioCampoArray.length;
            
            for (int i = 0; i < FormularioCampoArray.length; i++) {
                // se for CHECKBOX trata como Array de elementos selecionáveis
                
                if (ConstantesCRM.CT_CheckBox.equalsIgnoreCase(FormularioCampoArray[i].getTipoCampoVO().getSgLayoutApresentacaoCampo())) {
                    if (form.getValorCampo()[i].getValorArray() != null) {
                        ArrayList list = new ArrayList();
                        for (int j = 0; j < form.getValorCampo()[i].valorArray.length; j++) {
                            FormularioCampoValorVO formularioCampoValorVO = FormularioCampoValorVO.Factory.newInstance();
                            formularioCampoValorVO.setValor(form.getValorCampo()[i].getValorArray()[j].trim());
                            if (FormularioCampoArray[i].getFormularioCampoValorVOArray() != null) {
                                for (int l = 0; l < FormularioCampoArray[i].getFormularioCampoValorVOArray().length; l++) {
                                    if (form.getValorCampo()[i].getValorArray()[j].equals(FormularioCampoArray[i].getFormularioCampoValorVOArray(l).getValor())) {
                                        formularioCampoValorVO.setIdFormularioCampoValor(FormularioCampoArray[i].getFormularioCampoValorVOArray(l).getIdFormularioCampoValor());
                                    }
                                }
                            }
                            list.add(formularioCampoValorVO);
                        }
                        FormularioCampoArray[i].setFormularioCampoValorVOArray((FormularioCampoValorVO[]) list.toArray(new FormularioCampoValorVO[0]));
                    }
                
                } // Senão, efetua tratamento TEXT, TEXTAREA, SELECT e RADIO
                else {
                    ArrayList list = new ArrayList();
                    FormularioCampoValorVO formularioCampoValorVO = FormularioCampoValorVO.Factory.newInstance();
                    if (form.getValorCampo()[i].getValor() != null) {
                        formularioCampoValorVO.setValor(form.getValorCampo()[i].getValor().trim());
                        if (FormularioCampoArray[i].getFormularioCampoValorVOArray() != null) {
                            for (int k = 0; k < FormularioCampoArray[i].getFormularioCampoValorVOArray().length; k++) {
                                if ((FormularioCampoArray[i].getFormularioCampoValorVOArray(k).getValor().equals(form.getValorCampo()[i].getValor())) && (FormularioCampoArray[i].getFormularioCampoValorVOArray(k).getIdFormularioCampoValor() != null)) {
                                    formularioCampoValorVO.setIdFormularioCampoValor(FormularioCampoArray[i].getFormularioCampoValorVOArray(k).getIdFormularioCampoValor());
                                }
                            }
                        }
                    
                    } else {
                        formularioCampoValorVO.setValor(ConstantesCRM.SVAZIO);
                        formularioCampoValorVO.setIdFormularioCampoValor(ConstantesCRM.SVAZIO);
                    }
                    
                    list.add(formularioCampoValorVO);
                    FormularioCampoArray[i].setFormularioCampoValorVOArray((FormularioCampoValorVO[]) list.toArray(new FormularioCampoValorVO[0]));
                }
            }
            
            // Tratamento dos campos pertencentes a grupos de campos
            FormularioCampoVO[] FormularioCampoGruposArray = new FormularioCampoVO[0];
            int qtdeGrupos = formulario.getAdmGrupoCamposVOArray().length;
            int qtdeLimiteCamposDependentes = 20;
            
            for (int j = 0; j < qtdeGrupos; j++) {
                // Apenas para grupos de campos normais e funcionalidades (Dados de Aparelho).
                // Esta condição inibe a utilização para Grupos de campos dependentes.
                
                if (!ConstantesCRM.STWO.equals(formulario.getAdmGrupoCamposVOArray(j).getIdTipoGrupo())) {
                    FormularioCampoGruposArray = formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray();
                    for (int i = 0; i < FormularioCampoGruposArray.length; i++) {
                        
                        // se for CHECKBOX trata como Array de elementos selecionáveis
                        if (ConstantesCRM.CT_CheckBox.equalsIgnoreCase(FormularioCampoGruposArray[i].getTipoCampoVO().getSgLayoutApresentacaoCampo())) {
                            if (form.getValorCampo()[contadorContinuado].getValorArray() != null) {
                                ArrayList list = new ArrayList();
                                for (int k = 0; k < form.getValorCampo()[contadorContinuado].valorArray.length; k++) {
                                    FormularioCampoValorVO formularioCampoValorVO = FormularioCampoValorVO.Factory.newInstance();
                                    formularioCampoValorVO.setValor(form.getValorCampo()[contadorContinuado].getValorArray()[k].trim());
                                    if (FormularioCampoGruposArray[i].getFormularioCampoValorVOArray() != null) {
                                        for (int l = 0; l < FormularioCampoGruposArray[i].getFormularioCampoValorVOArray().length; l++) {
                                            if (form.getValorCampo()[contadorContinuado].getValorArray()[k].equals(FormularioCampoGruposArray[i].getFormularioCampoValorVOArray(l).getValor())) {
                                                formularioCampoValorVO.setIdFormularioCampoValor(FormularioCampoGruposArray[i].getFormularioCampoValorVOArray(l).getIdFormularioCampoValor());
                                            }
                                        }
                                    }
                                    list.add(formularioCampoValorVO);
                                }
                                FormularioCampoGruposArray[i].setFormularioCampoValorVOArray((FormularioCampoValorVO[]) list.toArray(new FormularioCampoValorVO[0]));
                            }
                        } // Senão, efetua tratamento TEXT, TEXTAREA, SELECT e RADIO
                        else {
                            ArrayList list = new ArrayList();
                            FormularioCampoValorVO formularioCampoValorVO = FormularioCampoValorVO.Factory.newInstance();
                            if (form.getValorCampo()[contadorContinuado].getValor() != null) {
                                formularioCampoValorVO.setValor(form.getValorCampo()[contadorContinuado].getValor().trim());
                                if (FormularioCampoGruposArray[i].getFormularioCampoValorVOArray() != null) {
                                    for (int k = 0; k < FormularioCampoGruposArray[i].getFormularioCampoValorVOArray().length; k++) {
                                        if ((FormularioCampoGruposArray[i].getFormularioCampoValorVOArray(k).getValor().equals(form.getValorCampo()[contadorContinuado].getValor()))
                                                && (FormularioCampoGruposArray[i].getFormularioCampoValorVOArray(k).getIdFormularioCampoValor() != null)) {
                                            formularioCampoValorVO.setIdFormularioCampoValor(FormularioCampoGruposArray[i].getFormularioCampoValorVOArray(k).getIdFormularioCampoValor());
                                        }
                                    }
                                }
                            } else {
                                formularioCampoValorVO.setValor(ConstantesCRM.SVAZIO);
                                formularioCampoValorVO.setIdFormularioCampoValor(ConstantesCRM.SVAZIO);
                            }
                            list.add(formularioCampoValorVO);
                            FormularioCampoGruposArray[i].setFormularioCampoValorVOArray((FormularioCampoValorVO[]) list.toArray(new FormularioCampoValorVO[0]));
                        }
                        contadorContinuado++;
                    }
                }
                // Tratamento de grupos de campos dependentes
                else {
                    String nmGrupoCamposDependentes = "grupoCamposDependentes";
                    String idGrupoCamposDependentes = formulario.getAdmGrupoCamposVOArray(j).getIdGrupoCampos();
                    String nmParametroRequest = ConstantesCRM.SVAZIO;
                    String idCampo = ConstantesCRM.SVAZIO;
                    String idResposta = ConstantesCRM.SVAZIO;
                    AdmGrupoCamposVO grupoTratamento = AdmGrupoCamposVO.Factory.newInstance();
                    grupoTratamento.setIdTipoGrupo(ConstantesCRM.STWO);
                    grupoTratamento.setNmGrupoCampos(formulario.getAdmGrupoCamposVOArray(j).getNmGrupoCampos());
                    grupoTratamento.setIdGrupoCampos(formulario.getAdmGrupoCamposVOArray(j).getIdGrupoCampos());
                    
                    for (int i = 1; i <= qtdeLimiteCamposDependentes; i++) {
                        nmParametroRequest = nmGrupoCamposDependentes + ";" + idGrupoCamposDependentes + ";" + i;
                        if (request.getParameter(nmParametroRequest) != null) {
                            String[] valoresCampo = request.getParameter(nmParametroRequest).split("\\|");
                            idCampo = valoresCampo[0];
                            idResposta = valoresCampo.length == 2 ? valoresCampo[1] : ConstantesCRM.SVAZIO;
                            FormularioCampoVO campoDependente = FormularioCampoVO.Factory.newInstance();
                            campoDependente.setNrNivel(Integer.toString(i));
                            campoDependente.setIdContatoFolhaCampo(Integer.parseInt(form.getIdContato()));
                            campoDependente.setIdCampo(Integer.parseInt(idCampo));
                            
                            if (!ConstantesCRM.SVAZIO.equals(idCampo) && !ConstantesCRM.SVAZIO.equals(idResposta)) {
                                campoDependente.addNewFormularioCampoValorVO().setIdFormularioCampoValor(idResposta);
                            }
                            grupoTratamento.addNewFormularioCampoVO().set(campoDependente.copy());
                        
                        } else {
                            break;
                        }
                    }
                    formulario.getAdmGrupoCamposVOArray(j).set(grupoTratamento.copy());
                }
            }
            if ("ENDERECO".equalsIgnoreCase(atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getInFuncionalidade())
                    || "ENDERECOCOMPLETO".equalsIgnoreCase(atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getInFuncionalidade())) {
                EnderecoVO dadosEndereco = EnderecoVO.Factory.newInstance();
                dadosEndereco.set(form.getEnderecoVO().copy());
                dadosEndereco.addNewUFVO().setSgUF(form.getSgUF());
                dadosEndereco.addNewPaisVO().setNmPais(form.getNmPais());
                if ("ENDERECO".equalsIgnoreCase(atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getInFuncionalidade())) {
                    formulario.addNewEnderecoVO().set(dadosEndereco.copy());
                } else {
                    formulario.getEnderecoVOArray(0).set(dadosEndereco.copy());
                }
            }
            atendimentoRegistroVO.getArvoreAtendimentoVO().setFormularioVO(formulario);
            request.getSession().removeAttribute("atendimentoVOFacade");
        }
        /*************************************************/
        /* Grava a entrada do cliente no ambiente tuxedo */
        /*************************************************/
        try {
            String nrProtocolo = request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) != null ? (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) : ConstantesCRM.SVAZIO;
            atendimentoRegistroVO.addNewDadosProtocoloVO();
            if (!ConstantesCRM.SVAZIO.equals(nrProtocolo)) {
                atendimentoRegistroVO.setNrProtocolo(nrProtocolo);
                atendimentoRegistroVO.getDadosProtocoloVO().setNrProtocolo(nrProtocolo);
            }
            atendimentoRegistroVO.getDadosProtocoloVO().setIdSistemaOrigemProtocolo(ConstantesCRM.SSEVEN);
            atendimentoRegistroVO.getDadosProtocoloVO().setDddSMSProtocolo(nrLinha.replaceAll("[^0-9]", ConstantesCRM.SVAZIO).substring(0, 2));
            atendimentoRegistroVO.getDadosProtocoloVO().setNrLinhaSMSProtocolo(nrLinha.replaceAll("[^0-9]", ConstantesCRM.SVAZIO).substring(2));
            
            if ("RA".equals(request.getParameter("OPER"))) {
                atendimentoRegistroVO.getDadosProtocoloVO().setForceUsoProtocoloNaoAberto(ConstantesCRM.SONE);
                if (form.getNrProtocoloSituacao() != null && !ConstantesCRM.SVAZIO.equals(form.getNrProtocoloSituacao())) {
                    atendimentoRegistroVO.setNrProtocolo(form.getNrProtocoloSituacao());
                    atendimentoRegistroVO.getDadosProtocoloVO().setNrProtocolo(form.getNrProtocoloSituacao());
                }
            }
            
            int idTipoAberturaProtocolo = 0;
            if (form.getIdLinhaAtendimento() != null &&  !ConstantesCRM.SVAZIO.equals(form.getIdLinhaAtendimento())) {
                idTipoAberturaProtocolo = IDTIPOABERTURAPROTOCOLO_LINHACLIENTE;
            
            } else if (form.getIdClienteDePara() != null &&  !ConstantesCRM.SVAZIO.equals(form.getIdClienteDePara())) {
                idTipoAberturaProtocolo = IDTIPOABERTURAPROTOCOLO_PESSOA;
            
            } else if (parametros.getNrConta() != null &&  !ConstantesCRM.SVAZIO.equals(parametros.getNrConta())) {
                idTipoAberturaProtocolo = IDTIPOABERTURAPROTOCOLO_CONTA;
            
            } else {
                idTipoAberturaProtocolo = IDTIPOABERTURAPROTOCOLO_LINHA;
            }
            
            atendimentoRegistroVO.getDadosProtocoloVO().setIdTipoAberturaProtocolo(Integer.toString(idTipoAberturaProtocolo));
            registrarContatoForm.setAtendimentoVO(registrarContatoFacade.registrarAtendimento(user, atendimentoRegistroVO));
            String msgProtocolo = ConstantesCRM.SVAZIO;
            
            if (ConstantesCRM.SVAZIO.equals(nrProtocolo) || (!nrProtocolo.equals(registrarContatoForm.getAtendimentoVO().getNrProtocolo()))) {
                request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, registrarContatoForm.getAtendimentoVO().getNrProtocolo());
                registrarContatoForm.setNrProtocolo(registrarContatoForm.getAtendimentoVO().getNrProtocolo());
                
                if (!ConstantesCRM.SVAZIO.equals(nrProtocolo) && !nrProtocolo.equals(registrarContatoForm.getAtendimentoVO().getNrProtocolo())) {
                    msgProtocolo = "O protocolo anterior foi encerrado. O novo protocolo " + registrarContatoForm.getAtendimentoVO().getNrProtocolo() + " foi gerado.";
                } else {
                    msgProtocolo = "Protocolo " + registrarContatoForm.getAtendimentoVO().getNrProtocolo() + " gerado.\n";
                }
            }
            
            if (atendimentoRegistroVO.getAtendimentoSituacaoVO() == null && registrarContatoForm.getAtendimentoVO().getIdAtendimento().length() < 2) {
                msgProtocolo += "\nProcesso não pode ser aberto. Por favor, tente novamente.";
                registrarContatoForm.getAtendimentoVO().setInResponsavelAbertura(parametros.getIdTipoRelacionamento() == null ? ConstantesCRM.SONE : parametros.getIdTipoRelacionamento());
            } else
            
                // Reabertura de Processo
            if (atendimentoRegistroVO.getAtendimentoSituacaoVO() != null && !ConstantesCRM.SVAZIO.equals(atendimentoRegistroVO.getAtendimentoSituacaoVO().getQtDias()) && !"-1".equals(atendimentoRegistroVO.getAtendimentoSituacaoVO().getQtDias())) {
                registrarContatoForm.getAtendimentoVO().setQtInsistencia(form.getQtDias());
                if (registrarContatoForm.getAtendimentoVO().getIdAtendimento().length() < 2) {
                    msgProtocolo += "Processo não pode ser aberto. Por favor, tente novamente.";
                } else {
                    msgProtocolo += "O processo atual está relacionado a um processo idêntico aberto no protocolo <strong>" + registrarContatoForm.getNrProtocoloSituacao() + "</strong> na situação <strong>"
                            + atendimentoRegistroVO.getAtendimentoSituacaoVO().getNmTipo() + "</strong>.";
                }
            
            } else {
                msgProtocolo += ConstantesCRM.SSucesso;
            }
            
            if (!ConstantesCRM.SVAZIO.equals(msgProtocolo)) {
                registrarContatoForm.getAtendimentoVO().setMsgProtocolo(msgProtocolo);
            }
        
        } catch (TuxedoException e) {
            if (e.getMessage().matches("^.*07E.*$")) {
                request.setAttribute("msgError", e.getXmlHeader().getStatusText());
                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                return mapping.findForward("redirectErrorParam");
            
            } else if (e.getMessage().matches("^.*04E.*$")) {
                request.setAttribute("msgError", "Erro na base de dados. Contate o suporte.");
                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                return mapping.findForward("redirectErrorParam");
            
            } else {
                log.error("RegistrarContatoController:encaminharProcesso.do Erro");
                throw (e);
            }
        }
        
        /*************************************************************/
        /* Caso seja uma reabertura, apaga o idAtendimento da sessão */
        /*************************************************************/
        if (parametros != null && parametros.getIdAtendimento() != null) {
            parametros.setIdAtendimento(null);
        }
        
        /*************************************************************/
        /* Caso venha de alguma fila, a armazena */
        /*************************************************************/
        registrarContatoForm.setFila(form.getFila());
        if (ConstantesCRM.SONE.equals(form.getFila())) {
            registrarContatoForm.getAtendimentoVO().setObservacao(ConstantesCRM.SONE);
        }
        
        registrarContatoForm.getAtendimentoVO().setArvoreAtendimentoVO(ArvoreAtendimentoVO.Factory.newInstance());
        registrarContatoForm.getAtendimentoVO().getArvoreAtendimentoVO().setDescricaoCompleta(form.getDescricaoCompleta());
        log.debug("RegistrarContatoController:encaminharProcesso.do - Fim do Metodo]");
        
        if (request.getParameter("inAjax") != null && "true".equals(request.getParameter("inAjax"))) {
            response.setContentType(ConstantesCRM.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(ClienteUtils.getCleanXMLFromXSD(registrarContatoForm.getAtendimentoVO(), null).getBytes(ConstantesCRM.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        
        } else {
            if ((!(ConstantesCRM.SVAZIO.equals(form.getQtDias()))) && (!("-1".equals(form.getQtDias())))) {
                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                return mapping.findForward("successReabertura");
            }
            // Retorna com sucesso
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);
        }
    }

    private String getTelMask(String nrTelefone) {
        if (nrTelefone.length() <= 9) {
            nrTelefone = "(" + nrTelefone.substring(0, 2) + ")" + nrTelefone.substring(2, 6) + "-" + nrTelefone.substring(5, 9);
        
        } else{
        	nrTelefone = ConstantesCRM.formatPhoneNumber(nrTelefone);
        }
        return nrTelefone;
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="CodigoProcesso.jsp"
     * @jpf:forward name="redirectErrorParam" path="redirectArvoreContato.jsp"
     */
    protected ActionForward fechamentoProcesso(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RegistrarContatoForm form = (RegistrarContatoForm) formParam;
        log.debug("RegistrarContatoController:fechamentoProcesso.do - Inicio do Metodo]");
        // Monta o log da operação se possível
        String user = ConstantesCRM.getCurrentUser(request.getSession());
        registrarContatoForm = form;
        
        FechamentoAtendimentoVO fechamentoAtendimentoVO = FechamentoAtendimentoVO.Factory.newInstance();
        AtendimentoVO at = AtendimentoVO.Factory.newInstance();
        this.atendimentoFechamentoVO.setTpOperacao(ConstantesCRM.SONE);
        
        fechamentoAtendimentoVO.setAtendimentoVOArray(new AtendimentoVO[] { at });
        fechamentoAtendimentoVO.setAtendimentoVOArray(0, this.atendimentoFechamentoVO);
        
        fechamentoAtendimentoVO.setIdBaixa(form.getIdBaixa());
        log.debug("RegistrarContatoController:fechamentoProcesso.do - [idBaixa = " + form.getIdBaixa() + "]");
        
        fechamentoAtendimentoVO.setIdBaixaMensagem(form.getIdBaixaMensagem());
        log.debug("RegistrarContatoController:fechamentoProcesso.do - [idBaixaMensagem = " + form.getIdBaixaMensagem() + "]");
        
        fechamentoAtendimentoVO.setObservacaoFechamento(form.getObservacaoFechamento());
        log.debug("RegistrarContatoController:fechamentoProcesso.do - [observacaoFechamento = " + form.getObservacaoFechamento() + "]");
        /*************************************************/
        /* Grava a entrada do cliente no ambiente tuxedo */
        /*************************************************/
        try {
            form.setAtendimentoVO(registrarContatoFacade.fechamentoProcesso(user, fechamentoAtendimentoVO));
        
        } catch (TuxedoException e) {
            if (e.getMessage().matches("^.*07E.*$")) {
                request.setAttribute("msgError", e.getXmlHeader().getStatusText());
                log.error("RegistrarContatoController:fechamentoProcesso.do - Erro");
                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                return mapping.findForward("redirectErrorParam");
            
            } else if (e.getMessage().matches("^.*04E.*$")) {
                request.setAttribute("msgError", "Erro na base de dados. Contate o suporte.");
                log.error("RegistrarContatoController:fechamentoProcesso.do - Erro na base de dados;");
                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                return mapping.findForward("redirectErrorParam");
            
            } else {
                log.error("RegistrarContatoController:fechamentoProcesso.do Erro");
                throw (e);
            }
        }
        
        registrarContatoForm.setAtendimentoVO(form.getAtendimentoVO());
        
        /*************************************************************/
        /* Caso seja uma reabertura, apaga o idAtendimento da sessão */
        /*************************************************************/
        ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
        if (parametros.getIdAtendimento() != null) {
            parametros.setIdAtendimento(null);
        }
        
        /*************************************************************/
        /* Caso venha de alguma fila, a armazena */
        /*************************************************************/
        registrarContatoForm.setFila(form.getFila());
        log.debug("RegistrarContatoController:fechamentoProcesso.do - [fila = " + form.getFila() + "]");
        
        registrarContatoForm.getAtendimentoVO().setArvoreAtendimentoVO(ArvoreAtendimentoVO.Factory.newInstance());
        registrarContatoForm.getAtendimentoVO().getArvoreAtendimentoVO().setDescricaoCompleta(form.getDescricaoCompleta());
        log.debug("RegistrarContatoController:fechamentoProcesso.do - [descricaoCompleta = " + form.getDescricaoCompleta() + "]");
        log.debug("RegistrarContatoController:fechamentoProcesso.do - Fim do Metodo]");
        
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="ListaProcessos.jsp"
     */
    protected ActionForward listarProcessos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        RegistrarContatoForm form = (RegistrarContatoForm) formParam;
        log.debug("RegistrarContatoController:listarProcessos.do - Inicio do Metodo]");
        try {
            String user = ConstantesCRM.getCurrentUser(request.getSession());
            atendimentoVOLista = registrarContatoFacade.verificaProcessosCorrentes(user, new Long(request.getParameter("idChamadaTelefonica")).longValue());
            if(atendimentoVOLista == null){
            	log.warning("AtendimentoVO null!");
            	atendimentoVOLista = AtendimentoVO.Factory.newInstance();
            }
            form.setAtendimentoVOLista(atendimentoVOLista);
            // registrarContatoForm = form;
            log.debug("RegistrarContatoController:listarProcessos.do - Fim do Metodo]");

        } catch (Exception e) {
            log.error("listarProcessos::", e);
        }

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="ArvoreBaixa.jsp"
     * @jpf:forward name="semArvoreBaixa" path="fechamentoProcesso.do"
     */
    protected ActionForward obterArvoreBaixa(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RegistrarContatoForm form = (RegistrarContatoForm) formParam;
        log.debug("RegistrarContatoController:obterArvoreBaixa.do - Inicio do Metodo]");
        String user = ConstantesCRM.getCurrentUser(request.getSession());
        
        // ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
        // o default é 1 (telefone)
        int idTipoComunicacao = 3;
        StringUtil strUtil = new StringUtil();
        
        // Monta os retornos selecionado pelo usuário
        int tamanho = form.getComunicacaoSelecionada().length;
        // StringTokenizer stringTokenizer;
        if (tamanho > 0) {
            String[] tipoComunArr = form.getComunicacaoSelecionada()[0].split(",");
            if (tipoComunArr.length == 1) {
                TipoComunicacaoVO tipoComunicacaoVO = TipoComunicacaoVO.Factory.newInstance();
                tipoComunicacaoVO = registrarContatoFacade.getIdTipoComunicacaoBySG(user, "CELULAR");
                idTipoComunicacao = Integer.parseInt(tipoComunicacaoVO.getIdTipoComunicacao());
            
            } else {
                idTipoComunicacao = Integer.parseInt(tipoComunArr[0]);
            }
        }
        registrarContatoForm.setIdTipoComunicacao(String.valueOf(idTipoComunicacao));
        AdmFolhaBaixaVO admFolhaBaixaVO = atendimentoWorkflowFacade.obtemArvoreBaixaParte(user, ConstantesCRM.SZERO, ConstantesCRM.SVAZIO, form.getIdContato(), new Integer(idTipoComunicacao).toString());
        
        if (admFolhaBaixaVO.getIdBaixa() == null) {
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward("semArvoreBaixa");
        }
        
        if (admFolhaBaixaVO.getAdmFolhaBaixaVOArray().length == 0) {
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward("semArvoreBaixa");
        }
        
        String script = "if(document.getElementById) { var tree = new WebFXTree('" + strUtil.escapaComillasJS(admFolhaBaixaVO.getNmBaixa()) + "','','classic');\n";
        String node = ConstantesCRM.SVAZIO;
        String dsMensagem = ConstantesCRM.SVAZIO;
        String idBaixaMensagem = ConstantesCRM.SVAZIO;
        for (int i = 0; i < admFolhaBaixaVO.getAdmFolhaBaixaVOArray().length; i++) {
            boolean inFolha = false;
            if (admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getAdmMensagemBaixaVOArray() != null && admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getAdmMensagemBaixaVOArray().length > 0) {
                dsMensagem = admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getAdmMensagemBaixaVOArray(0).getAdmMensagemAvisoVO().getDsMensagemAviso();
                idBaixaMensagem = admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getAdmMensagemBaixaVOArray(0).getIdBaixaMensagem();
                inFolha = true;
            }
            
            if (inFolha) {
                node = "tmpArvore = new WebFXTreeItem('" + admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getNmBaixa() + "'," + "\"Javascript:mostraDadosArvore('" + admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getIdBaixa() + "'," + "'" + idBaixaMensagem
                        + "','" + strUtil.escapaComillasJS2(dsMensagem) + "');\");\n";
            
            } else {
                node = "tmpArvore = new WebFXTreeItem('" + strUtil.escapaComillasJS(admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getNmBaixa()) + "'," + "\"Javascript:expandirNo('" + admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getIdBaixa() + "');\","
                        + "'','/FrontOfficeWeb/resources/images/foldericon.png','/FrontOfficeWeb/resources/images/openfoldericon.png');\n";
            }
            
            script += node;
            script += "tree.add(tmpArvore);\n\n";
        }
        script += "\ndocument.write(tree);}\n\n";
        //registrarContatoForm = form;
        
        registrarContatoForm.setScriptArvoreBaixa(script);
        log.debug("RegistrarContatoController:obterArvoreBaixa.do - [scriptArvoreBaixa = " + script + "]");
        registrarContatoForm.getAtendimentoVO().setArvoreAtendimentoVO(ArvoreAtendimentoVO.Factory.newInstance());
        registrarContatoForm.getAtendimentoVO().getArvoreAtendimentoVO().setDescricaoCompleta(form.getDescricaoCompleta());
        // seta nulo no objeto
        strUtil = null;
        log.debug("RegistrarContatoController:obterArvoreBaixa.do - Fim do Metodo]");
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="iframeArvoreBaixa.jsp"
     */
    protected ActionForward obtemArvoreBaixaParte(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RegistrarContatoForm form = (RegistrarContatoForm) formParam;
        log.debug("RegistrarContatoController:obtemArvoreBaixaParte.do - Inicio do Metodo]");
        
        String user = ConstantesCRM.getCurrentUser(request.getSession());
        // o default é 1 (telefone)
        int idTipoComunicacao = 3;
        log.debug("RegistrarContatoController:obtemArvoreBaixaParte.do - [idTipoComunicacao = " + idTipoComunicacao + "]");
        
        String idBaixa = ConstantesCRM.SVAZIO;
        idBaixa = request.getParameter("IDBAIXA");
        log.debug("RegistrarContatoController:obtemArvoreBaixaParte.do - [idBaixa = " + idBaixa + "]");
        
        StringUtil strUtil = new StringUtil();
        if ((registrarContatoForm.getIdTipoComunicacao() != null) && ((registrarContatoForm.getIdTipoComunicacao().length() > 0))) {
            idTipoComunicacao = Integer.parseInt(registrarContatoForm.getIdTipoComunicacao());
        }
        
        AdmFolhaBaixaVO admFolhaBaixaVO = atendimentoWorkflowFacade.obtemArvoreBaixaParte(user, ConstantesCRM.SZERO, idBaixa, form.getIdContato(), new Integer(idTipoComunicacao).toString());
        String script = ConstantesCRM.SVAZIO;
        String node = ConstantesCRM.SVAZIO;
        String dsMensagem = ConstantesCRM.SVAZIO;
        String idBaixaMensagem = ConstantesCRM.SVAZIO;
        
        for (int i = 0; i < admFolhaBaixaVO.getAdmFolhaBaixaVOArray().length; i++) {
            boolean inFolha = false;
            if (admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getAdmMensagemBaixaVOArray() != null && admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getAdmMensagemBaixaVOArray().length > 0) {
                dsMensagem = admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getAdmMensagemBaixaVOArray(0).getAdmMensagemAvisoVO().getDsMensagemAviso();
                idBaixaMensagem = admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getAdmMensagemBaixaVOArray(0).getIdBaixaMensagem();
                inFolha = true;
            }
            
            if (inFolha) {
                node = "parent.tmpArvore = new parent.WebFXTreeItem('" + strUtil.escapaComillasJS(admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getNmBaixa()) + "'," + "\"Javascript:mostraDadosArvore('"
                        + admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getIdBaixa() + "'," + "'" + idBaixaMensagem + "','" + strUtil.escapaComillasJS2(dsMensagem) + "');\");\n";
            
            } else {
                node = "parent.tmpArvore = new parent.WebFXTreeItem('" + strUtil.escapaComillasJS(admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getNmBaixa()) + "'," + "\"Javascript:expandirNo('" + admFolhaBaixaVO.getAdmFolhaBaixaVOArray(i).getIdBaixa()
                        + "');\"," + "'','/FrontOfficeWeb/resources/images/foldericon.png','/FrontOfficeWeb/resources/images/openfoldericon.png');\n";
            }
            
            script += node;
            script += "parent.tree.getSelected().add(parent.tmpArvore);\n\n";
        }
        
        script += "parent.tree.getSelected().setAddEnabled(false);\n\n";
        script += "parent.tree.getSelected().expand()\n\n";
        registrarContatoForm.setScriptArvoreBaixa(script);
        log.debug("RegistrarContatoController:obtemArvoreBaixaParte.do - [scriptArvoreBaixa = " + script + "]");
        
        // seta nulo no objeto
        strUtil = null;
        log.debug("RegistrarContatoController:obtemArvoreBaixaParte.do - Fim do Metodo]");
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /*
     * private void obtemDadosContato(RegistrarContatoForm form, HttpServletRequest request) throws Exception {
     * // aponta para o mesmo endereço para obter as informações do processamento
     * registrarContatoForm = form;
     * String user = ConstantesCRM.getCurrentUser(request.getSession());
     * // Definições
     * AtendimentoVO atendimentoFechamentoVO = AtendimentoVO.Factory.newInstance();
     * FechamentoAtendimentoVO fechamentoAtendimentoVO = null;
     * AtendimentoRetornoVO[] atendimentoRetornoVO = null;
     * AtendimentoTipoComunicacaoVO[] atendimentoTipoComunicacaoVO = null;
     * ContaVO contaVO = null;
     * LinhaVO linhaVO = null;
     * StringTokenizer stringTokenizer = null;
     * int tamanho = 0;
     * int contador = 0;
     * if (form.getIdAtendimento() != null && !(form.getIdAtendimento().equals(ConstantesCRM.SVAZIO))) {
     * atendimentoFechamentoVO.setIdAtendimento(Long.parseLong(form.getIdAtendimento()));
     * atendimentoFechamentoVO.setNrProtocolo(form.getNrProtocolo());
     * }
     * atendimentoFechamentoVO.setCanalVO(CanalVO.Factory.newInstance());
     * atendimentoFechamentoVO.getCanalVO().setIdCanal(Integer.parseInt(form.getIdCanal()));
     * atendimentoFechamentoVO.setProcedenciaVO(ProcedenciaVO.Factory.newInstance());
     * atendimentoFechamentoVO.getProcedenciaVO().setIdProcedencia(Integer.parseInt(form.getIdProcedencia()));
     * // atendimentoFechamentoVO.setPessoaVO( this.formEntrada.getPessoaVO() );
     * atendimentoFechamentoVO.setPessoaVO(PessoaVO.Factory.newInstance());
     * atendimentoFechamentoVO.getPessoaVO().setIdPessoa(Long.parseLong(form.getIdClienteDePara()));
     * // atendimentoFechamentoVO.setUsuarioLinhaVO( this.formEntrada.getUsuarioLinhaVO() );
     * atendimentoFechamentoVO.setUsuarioLinhaVO(UsuarioLinhaVO.Factory.newInstance());
     * atendimentoFechamentoVO.getUsuarioLinhaVO().setIdPessoa(form.getIdUsuarioDePara());
     * // atendimentoFechamentoVO.setArvoreAtendimentoVO( this.formEntrada.getArvoreAtendimentoVO() );
     * atendimentoFechamentoVO.setArvoreAtendimentoVO(ArvoreAtendimentoVO.Factory.newInstance());
     * atendimentoFechamentoVO.getArvoreAtendimentoVO().setIdContato(Integer.parseInt(form.getIdContato()));
     * atendimentoFechamentoVO.getArvoreAtendimentoVO().setCarterizacaoVO(CarterizacaoVO.Factory.newInstance());
     * atendimentoFechamentoVO.getArvoreAtendimentoVO().getCarterizacaoVO().setIdTipoCarteira(Integer.parseInt(form.getIdTipoCarteira()));
     * atendimentoFechamentoVO.getArvoreAtendimentoVO().setSegmentacaoVO(SegmentacaoVO.Factory.newInstance());
     * atendimentoFechamentoVO.getArvoreAtendimentoVO().getSegmentacaoVO().setIdSegmentacao(Integer.parseInt(form.getIdSegmentacao()));
     * atendimentoFechamentoVO.setIdDiscador(Integer.parseInt(form.getIdDiscador()));
     * atendimentoFechamentoVO.setInTipoPessoa(form.getInTipoPessoa());
     * // Monta os retornos selecionado pelo usuário
     * tamanho = form.getComunicacaoSelecionada().length;
     * atendimentoRetornoVO = new AtendimentoRetornoVOImpl[tamanho];
     * for (contador = 0; contador < tamanho; contador++) {
     * atendimentoRetornoVO[contador] = AtendimentoRetornoVO.Factory.newInstance();
     * }
     * for (contador = 0; contador < tamanho; contador++) {
     * String[] tipoComunArr = form.getComunicacaoSelecionada()[contador].split(",");
     * if (tipoComunArr.length == 1) {
     * TipoComunicacaoVO tipoComunicacaoVO = TipoComunicacaoVO.Factory.newInstance();
     * tipoComunicacaoVO = registrarContatoFacade.getIdTipoComunicacaoBySG(user, "CELULAR");
     * atendimentoRetornoVO[contador].setIdTipoComunicacao(Integer.parseInt(tipoComunicacaoVO.getIdTipoComunicacao()));
     * atendimentoRetornoVO[contador].setDsComunicacao(tipoComunArr[0]);
     * } else {
     * atendimentoRetornoVO[contador].setIdTipoComunicacao(Integer.parseInt(tipoComunArr[0]));
     * atendimentoRetornoVO[contador].setDsComunicacao(tipoComunArr[1]);
     * }
     * }
     * atendimentoFechamentoVO.setAtendimentoRetornoVOArray(atendimentoRetornoVO);
     * // Monta a observação
     * atendimentoFechamentoVO.setObservacao(form.getObservacaoAtendimento());
     * // monta o número de telefone informado (No. telefone contato)
     * String nrLinha = ConstantesCRM.unmask(form.getTelContato(), "()- ");
     * if (nrLinha.length() < 10) {
     * nrLinha = ConstantesCRM.SZERO + nrLinha;
     * }
     * atendimentoFechamentoVO.setNrTelefone(nrLinha);
     * // Monta a situação do contato
     * if (form.getIdAtendimentoSituacao() != 0) {
     * atendimentoFechamentoVO.setAtendimentoSituacaoVO(AtendimentoSituacaoVO.Factory.newInstance());
     * atendimentoFechamentoVO.getAtendimentoSituacaoVO().setIdAtendimento(form.getIdAtendimentoSituacao());
     * // atendimentoFechamentoVO.getAtendimentoSituacaoVO().setNrProtocolo(form.getNrProtocoloSituacao());
     * atendimentoFechamentoVO.getAtendimentoSituacaoVO().setIdTipoReaberturaProcesso(form.getIdTipoReaberturaProcesso());
     * atendimentoFechamentoVO.getAtendimentoSituacaoVO().setNmTipo(form.getNmTipo());
     * atendimentoFechamentoVO.getAtendimentoSituacaoVO().setQtDias(form.getQtDias());
     * }
     * // if(atendimentoVOFacade.getAtendimentoSituacaoVO() != null)
     * // atendimentoFechamentoVO.setAtendimentoSituacaoVO(atendimentoVOFacade.getAtendimentoSituacaoVO());
     * // Monta o nome do contato
     * if ((form.getNomeContatoAlterado() != null) && (form.getNomeContatoAlterado().length() > 0)) {
     * atendimentoFechamentoVO.setNmContato(form.getNomeContatoAlterado());
     * } else {
     * atendimentoFechamentoVO.setNmContato("Contato VIVO");
     * }
     * // Monta o id da chamada telefônica
     * atendimentoFechamentoVO.setIdChamadaTelefonica(form.getIdChamadaTelefonica());
     * // Monta o nome do contato
     * atendimentoFechamentoVO.setIdGrupoAbertura(Long.parseLong(form.getIdGrupoAbertura()));
     * // Monta o nome do contato
     * atendimentoFechamentoVO.setInResponsavelAbertura(form.getInResponsavelAbertura());
     * // Monta o tipo de comunicação
     * atendimentoTipoComunicacaoVO = new AtendimentoTipoComunicacaoVOImpl[1];
     * atendimentoTipoComunicacaoVO[0] = AtendimentoTipoComunicacaoVO.Factory.newInstance();
     * atendimentoFechamentoVO.getPessoaVO().setAtendimentoTipoComunicacaoVOArray(atendimentoTipoComunicacaoVO);
     * atendimentoFechamentoVO.getPessoaVO().getAtendimentoTipoComunicacaoVOArray(0).setAtendimentoComunicacaoVOArray(new AtendimentoComunicacaoVOImpl[0]);
     * // Monta a conta selecionada se necessário
     * atendimentoFechamentoVO.setContas(Contas.Factory.newInstance());
     * contaVO = ContaVO.Factory.newInstance();
     * atendimentoFechamentoVO.getContas().setContaVOArray(new ContaVO[] { contaVO });
     * // Monta a linha se necessário
     * linhaVO = LinhaVO.Factory.newInstance();
     * atendimentoFechamentoVO.getContas().getContaVOArray(0).setLinhaVOArray(new LinhaVO[] { linhaVO });
     * if (form.getContaSelecionada() != null && !(form.getContaSelecionada().equalsIgnoreCase(ConstantesCRM.SVAZIO))) {
     * atendimentoFechamentoVO.getContas().getContaVOArray(0).setIdConta(Integer.parseInt(form.getContaSelecionada()));
     * }
     * if (form.getLinhaSelecionada() != null && !(form.getLinhaSelecionada().equalsIgnoreCase(ConstantesCRM.SVAZIO))) {
     * atendimentoFechamentoVO.getContas().getContaVOArray(0).getLinhaVOArray(0).setIdPessoaLinhaHistorico(Long.parseLong(form.getLinhaSelecionada()));
     * }
     * / ************************************************ /
     * / * Processa a gravação do formulário preenchido * /
     * / ************************************************ /
     * if (atendimentoVOFacade == null) {
     * atendimentoVOFacade = (AtendimentoVO) request.getSession().getAttribute("atendimentoVOFacade");
     * }
     * if (atendimentoVOFacade != null && atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO() != null) {
     * FormularioVO formulario = (FormularioVO) atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().copy();
     * // interage no array de campos, atualizando com o valor preenchido
     * FormularioCampoVO[] FormularioCampoArray = formulario.getFormularioCampoVOArray();
     * int contadorContinuado = FormularioCampoArray.length;
     * for (int i = 0; i < FormularioCampoArray.length; i++) {
     * // se for CHECKBOX trata como Array de elementos selecionáveis
     * if (FormularioCampoArray[i].getTipoCampoVO().getSgLayoutApresentacaoCampo().equalsIgnoreCase(ConstantesCRM.CT_CheckBox)) {
     * if (form.getValorCampo()[i].getValorArray() != null) {
     * ArrayList list = new ArrayList();
     * for (int j = 0; j < form.getValorCampo()[i].valorArray.length; j++) {
     * FormularioCampoValorVO formularioCampoValorVO = FormularioCampoValorVO.Factory.newInstance();
     * formularioCampoValorVO.setValor(form.getValorCampo()[i].getValorArray()[j].trim());
     * if (FormularioCampoArray[i].getFormularioCampoValorVOArray() != null) {
     * for (int l = 0; l < FormularioCampoArray[i].getFormularioCampoValorVOArray().length; l++) {
     * if (form.getValorCampo()[i].getValorArray()[j].equals(FormularioCampoArray[i].getFormularioCampoValorVOArray(l).getValor())) {
     * formularioCampoValorVO.setIdFormularioCampoValor(FormularioCampoArray[i].getFormularioCampoValorVOArray(l).getIdFormularioCampoValor());
     * }
     * }
     * }
     * list.add(formularioCampoValorVO);
     * }
     * FormularioCampoArray[i].setFormularioCampoValorVOArray((FormularioCampoValorVO[]) list.toArray(new FormularioCampoValorVO[0]));
     * }
     * } // Senão, efetua tratamento TEXT, TEXTAREA, SELECT e RADIO
     * else {
     * if (form.getValorCampo()[i].getValor() != null) {
     * ArrayList list = new ArrayList();
     * FormularioCampoValorVO formularioCampoValorVO = FormularioCampoValorVO.Factory.newInstance();
     * formularioCampoValorVO.setValor(form.getValorCampo()[i].getValor().trim());
     * if (FormularioCampoArray[i].getFormularioCampoValorVOArray() != null) {
     * for (int k = 0; k < FormularioCampoArray[i].getFormularioCampoValorVOArray().length; k++) {
     * if ((FormularioCampoArray[i].getFormularioCampoValorVOArray(k).getValor().equals(form.getValorCampo()[i].getValor())) &&
     * (FormularioCampoArray[i].getFormularioCampoValorVOArray(k).getIdFormularioCampoValor() != null)) {
     * formularioCampoValorVO.setIdFormularioCampoValor(FormularioCampoArray[i].getFormularioCampoValorVOArray(k).getIdFormularioCampoValor());
     * }
     * }
     * }
     * list.add(formularioCampoValorVO);
     * FormularioCampoArray[i].setFormularioCampoValorVOArray((FormularioCampoValorVO[]) list.toArray(new FormularioCampoValorVO[0]));
     * }
     * }
     * }
     * // Tratamento dos campos pertencentes a grupos de campos
     * FormularioCampoVO[] FormularioCampoGruposArray = new FormularioCampoVO[0];
     * int qtdeGrupos = formulario.getAdmGrupoCamposVOArray().length;
     * for (int j = 0; j < qtdeGrupos; j++) {
     * FormularioCampoGruposArray = formulario.getAdmGrupoCamposVOArray(j).getFormularioCampoVOArray();
     * for (int i = 0; i < FormularioCampoGruposArray.length; i++) {
     * // se for CHECKBOX trata como Array de elementos selecionáveis
     * if (FormularioCampoGruposArray[i].getTipoCampoVO().getSgLayoutApresentacaoCampo().equalsIgnoreCase(ConstantesCRM.CT_CheckBox)) {
     * if (form.getValorCampo()[contadorContinuado].getValorArray() != null) {
     * ArrayList list = new ArrayList();
     * for (int k = 0; k < form.getValorCampo()[contadorContinuado].valorArray.length; k++) {
     * FormularioCampoValorVO formularioCampoValorVO = FormularioCampoValorVO.Factory.newInstance();
     * formularioCampoValorVO.setValor(form.getValorCampo()[contadorContinuado].getValorArray()[k].trim());
     * if (FormularioCampoGruposArray[i].getFormularioCampoValorVOArray() != null) {
     * for (int l = 0; l < FormularioCampoGruposArray[i].getFormularioCampoValorVOArray().length; l++) {
     * if (form.getValorCampo()[contadorContinuado].getValorArray()[k].equals(FormularioCampoGruposArray[i].getFormularioCampoValorVOArray(l).getValor())) {
     * formularioCampoValorVO.setIdFormularioCampoValor(FormularioCampoGruposArray[i].getFormularioCampoValorVOArray(l).getIdFormularioCampoValor());
     * }
     * }
     * }
     * list.add(formularioCampoValorVO);
     * }
     * FormularioCampoGruposArray[i].setFormularioCampoValorVOArray((FormularioCampoValorVO[]) list.toArray(new FormularioCampoValorVO[0]));
     * }
     * } // Senão, efetua tratamento TEXT, TEXTAREA, SELECT e RADIO
     * else {
     * ArrayList list = new ArrayList();
     * FormularioCampoValorVO formularioCampoValorVO = FormularioCampoValorVO.Factory.newInstance();
     * if (form.getValorCampo()[contadorContinuado].getValor() != null) {
     * formularioCampoValorVO.setValor(form.getValorCampo()[contadorContinuado].getValor().trim());
     * if (FormularioCampoGruposArray[i].getFormularioCampoValorVOArray() != null) {
     * for (int k = 0; k < FormularioCampoGruposArray[i].getFormularioCampoValorVOArray().length; k++) {
     * if ((FormularioCampoGruposArray[i].getFormularioCampoValorVOArray(k).getValor().equals(form.getValorCampo()[contadorContinuado].getValor())) &&
     * (FormularioCampoGruposArray[i].getFormularioCampoValorVOArray(k).getIdFormularioCampoValor() != null)) {
     * formularioCampoValorVO.setIdFormularioCampoValor(FormularioCampoGruposArray[i].getFormularioCampoValorVOArray(k).getIdFormularioCampoValor());
     * }
     * }
     * }
     * } else {
     * formularioCampoValorVO.setValor(ConstantesCRM.SVAZIO);
     * formularioCampoValorVO.setIdFormularioCampoValor(ConstantesCRM.SVAZIO);
     * }
     * list.add(formularioCampoValorVO);
     * FormularioCampoGruposArray[i].setFormularioCampoValorVOArray((FormularioCampoValorVO[]) list.toArray(new FormularioCampoValorVO[0]));
     * }
     * contadorContinuado++;
     * }
     * }
     * if ("ENDERECO".equalsIgnoreCase(atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getInFuncionalidade()) ||
     * "ENDERECOCOMPLETO".equalsIgnoreCase(atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getInFuncionalidade())) {
     * EnderecoVO dadosEndereco = EnderecoVO.Factory.newInstance();
     * dadosEndereco.set(form.getEnderecoVO().copy());
     * dadosEndereco.addNewUFVO().setSgUF(form.getSgUF());
     * dadosEndereco.addNewPaisVO().setNmPais(form.getNmPais());
     * if ("ENDERECO".equalsIgnoreCase(atendimentoVOFacade.getArvoreAtendimentoVO().getFormularioVO().getInFuncionalidade())) {
     * formulario.addNewEnderecoVO().set(dadosEndereco.copy());
     * } else {
     * formulario.getEnderecoVOArray(0).set(dadosEndereco.copy());
     * }
     * }
     * // atendimentoFechamentoVO.setArvoreAtendimentoVO( ArvoreAtendimentoVO.Factory.newInstance() );
     * // atendimentoFechamentoVO.setArvoreAtendimentoVO( this.formEntrada.getArvoreAtendimentoVO() );
     * atendimentoFechamentoVO.getArvoreAtendimentoVO().setFormularioVO(formulario);
     * request.getSession().removeAttribute("atendimentoVOFacade");
     * }
     * this.atendimentoFechamentoVO = atendimentoFechamentoVO;
     * / ************************************************* /
     * / * Grava a entrada do cliente no ambiente tuxedo * /
     * / ************************************************* /
     * // form.setAtendimentoVO( registrarContatoFacade.registrarAtendimento(this.formEntrada.getUser(),
     * // atendimentoFechamentoVO) );
     * }
     */

    /**
     * @jpf:action
     * @jpf:forward name="success" path="ConsultaTipoComunicacao.jsp"
     */
    protected ActionForward obterTipoComunicacao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        RegistrarContatoForm form = (RegistrarContatoForm) formParam;
        try {
            log.debug("RegistrarContatoController:obterTipoComunicacao.do - Inicio do Metodo]");
            String user = ConstantesCRM.getCurrentUser(request.getSession());

            registrarContatoForm.setAtendimentoVO(AtendimentoVO.Factory.newInstance());
            registrarContatoForm.getAtendimentoVO().setPessoaVO(registrarContatoFacade.obtemTipoComunicacao(user, form.getIdPessoa()).getPessoaVO());

            log.debug("RegistrarContatoController:obterTipoComunicacao.do - Fim do Metodo]");

        } catch (Exception e) {
            log.error("obterTipoComunicacao::", e);
        }

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    private AtendimentoVO[] configuraAtendimentos(AtendimentoVO[] atendimentosVO, String idAtividade, String idMotivo, String idGrupo, String idUsuarioDestino, String comentario, String idAtendimento) {
        AtendimentoVO[] atendimentos;
        atendimentos = new AtendimentoVO[1];
        AtdWFVO[] atdWf;
        AtendimentoWorkflowComumVO atdWFComumVO = AtendimentoWorkflowComumVO.Factory.newInstance();
        atdWFComumVO.setDsObservacao(comentario);
        if ((atendimentosVO == null) || (atendimentosVO.length == 0)) {
            // id atendimento fake
            atdWf = new AtdWFVO[1];
            atdWf[0] = AtdWFVO.Factory.newInstance();
            atdWf[0].setIdAtendimento(idAtendimento);
            // fake
        
        } else {
            atdWf = new AtdWFVO[atendimentosVO.length];
            for (int i = 0; i < atendimentosVO.length; i++) {
                atdWf[i] = AtdWFVO.Factory.newInstance();
                atdWf[i].setIdAtendimento(atendimentosVO[i].getIdAtendimento());
            }
        }
        
        if (idMotivo != null) {
            WFMotivoVO[] wfMotivoVO = new WFMotivoVO[1];
            wfMotivoVO[0] = WFMotivoVO.Factory.newInstance();
            wfMotivoVO[0].setIdMotivo(idMotivo);
            atdWFComumVO.setWFMotivoVOArray(wfMotivoVO);
        }
        
        if (idGrupo != null) {
            WFGrupoVO[] wFGrupoVO = new WFGrupoVO[1];
            wFGrupoVO[0] = WFGrupoVO.Factory.newInstance();
            wFGrupoVO[0].setIdGrupo(idGrupo);
            atdWFComumVO.setWFGrupoVOArray(wFGrupoVO);
        }
        
        if (idUsuarioDestino != null) {
            if ("-1".equals(idUsuarioDestino)) {
                // nada
            } else {
                UsuarioVIVO[] usuarioVIVO = new UsuarioVIVO[1];
                usuarioVIVO[0] = UsuarioVIVO.Factory.newInstance();
                usuarioVIVO[0].setIdPessoaUsuario(idUsuarioDestino);
                atdWFComumVO.setUsuarioVIVOArray(usuarioVIVO);
            }
        }
        
        AtendimentoWorkflowVO atdWFVO = AtendimentoWorkflowVO.Factory.newInstance();
        atdWFVO.setAtendimentoWorkflowComumVO(atdWFComumVO);
        WFAcaoVO[] wFAcaoVO = new WFAcaoVO[1];
        wFAcaoVO[0] = WFAcaoVO.Factory.newInstance();
        wFAcaoVO[0].setIdAtividade(idAtividade);
        
        atendimentos[0] = AtendimentoVO.Factory.newInstance();
        atendimentos[0].setAtdWFVOArray(atdWf);
        atendimentos[0].setWFAcaoVOArray(wFAcaoVO);
        atendimentos[0].setAtendimentoWorkflowVO(atdWFVO);
        return atendimentos;
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" return-action="RegistrarContatoDone"
     */
    protected ActionForward registrarContatoDone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        log.debug("RegistrarContatoController:registrarContatoDone.do - Metodo]");
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="registrarContatoDone.do"
     */
    protected ActionForward AtendimentoDetalheDone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        log.debug("RegistrarContatoController:AtendimentoDetalheDone.do - Metodo]");
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     */
    protected ActionForward retornoAbaSolicitante(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        log.debug("RegistrarContatoController:retornoAbaSolicitante.do - Metodo]");
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     */
    protected ActionForward retornoAba(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        log.debug("RegistrarContatoController:retornoAba.do - Metodo]");
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="voltaRegistrarContato.jsp"
     */
    protected ActionForward ConsultaHexaDone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        log.debug("RegistrarContatoController:ConsultaHexaDone.do - Metodo]");
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="AtendimentoFrame.jsp"
     */
    protected ActionForward atendimentosCorrentes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        log.debug("RegistrarContatoController:atendimentosCorrentes.do - Metodo]");
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    public static class RegistrarContatoForm extends ActionForm {
        private static final long            serialVersionUID          = 7355652267311283679L;
        private String                       nmPais;
        private String                       sgUF;
        private AdmGrupoCamposVO[]           admGrupoCamposVO;
        /*
         * Variável responsável pela informação sobre existência ou ausência de forma de contato "Celular" presente na
         * base. valor 1: existe forma de contato "Celular" na base valor 0: não existe forma de contato "Celular" na
         * base Alteração referente à SM 517
         */
        private String                       inCelular;
        private String                       idLinhaAtendimento;
        private String                       user;
        // selecionados
        private String                       tipoComunicacaoSelecionada;
        private String                       idTipoComunicacaoAbertura;
        private String                       contaSelecionada;
        private String                       linhaSelecionada;
        private String[]                     comunicacaoDisponivel;
        private String[]                     comunicacaoSelecionada;
        private String                       observacaoAtendimento;
        private String                       observacaoInsistencia;
        private String                       nomeContatoAlterado;
        private String                       scriptArvoreBaixa;
        private boolean                      flagAtendimentosCorrentes = false;
        private String                       idBaixa;
        private String                       idBaixaMensagem;
        private String                       observacaoFechamento;
        private String                       idCanal                   = ConstantesCRM.SVAZIO;
        private String                       idProcedencia;
        private String                       nmURL;
        private String                       scriptValidacao           = ConstantesCRM.SVAZIO;
        private String                       scriptArvore              = ConstantesCRM.SVAZIO;
        private String                       inResponsavelAbertura     = null;
        private String                       inTipoPessoa              = null;
        private String                       idUfOperadora             = null;
        private String                       idSegmentacao             = null;
        private String                       idTipoCarteira            = null;
        private String                       idTipoLinha               = null;
        private String                       idGrupoAbertura           = null;
        private String                       idContato                 = null;
        private String                       nomeContato               = null;
        private long                         idChamadaTelefonica       = 0;
        private int                          abertura                  = 0;
        private String                       telContato                = null;
        private String                       telContatoFrm             = null;
        private String                       nrConta                   = null;
        private String                       nrLinha                   = null;
        private String                       idConta                   = null;
        private String                       idLinha                   = null;
        private String                       idClienteDePara           = null;
        private String                       idPessoa                  = null;
        private String                       idUsuarioDePara           = null;
        private String                       idDiscador                = null;
        private String                       idAtendimento             = null;
        private String                       nrProtocolo               = null;
        private String                       idAtendimentoSituacao;
        private String                       nrProtocoloSituacao       = ConstantesCRM.SVAZIO;
        private int                          idTipoReaberturaProcesso;
        private String                       nmTipo                    = null;
        private String                       qtDias                    = null;
        private String                       fila                      = ConstantesCRM.SVAZIO;
        private String                       abreProcessoReaRei        = null;
        private String                       idTipoComunicacao         = null;
        private String                       descricaoCompleta         = null;
        private String                       textoPesquisa             = null;
        private String                       idCampo                   = null;
        private String                       selecaoFormulario         = null;
        private String                       scriptCarrega             = null;
        private String                       carregaLinha              = null;
        private String                       tipoOperacao              = null;
        private String                       idFase                    = null;
        private String[]                     nrLinhasSelecionadas      = null;
        // VO's
        private AtendimentoVO                atendimentoVO;			   	
        private AtendimentoVO                atendimentoVOFacade;
        private WFListaContatosVO            listaContatosVO           = WFListaContatosVO.Factory.newInstance();
        // utilizado quando houver uma alteração no customer profile
        private AtendimentoVO                atendimentoVOComunicacao  = null;
        private FormularioVO                 formularioSalvarVO;
        private ContaVO                      contaSelecionadaVO;
        private AtendimentoTipoComunicacaoVO tipoComunicacaoSelecionadaVO;
        private AtendimentoVO                atendimentoVOLista        = null;
        private FormularioCampoVO            formularioCampoVO;
        private EnderecoVO                   enderecoVO;
        private TipoEnderecoVO[]             listaTipoEndereco;
        // idAtendimento do processo corrente a ser excluído
        private String                       idAtendimentoExclusao;
        // índice do array de processos correntes
        private int                          rowIndex;
        private int                          numeroCampos              = 20;
        private ValorCampo[]                 valorCampo;
        private ListaDadosVO                 listaDadosVO              = null;

        // default Constructor
        public RegistrarContatoForm() {
            contaSelecionadaVO = ContaVO.Factory.newInstance();
            contaSelecionadaVO.setLinhaVOArray(new LinhaVOImpl[0]);
            listaTipoEndereco = new TipoEnderecoVO[0];
            enderecoVO = EnderecoVO.Factory.newInstance();
            tipoComunicacaoSelecionadaVO = AtendimentoTipoComunicacaoVO.Factory.newInstance();
            tipoComunicacaoSelecionadaVO.setAtendimentoComunicacaoVOArray(new AtendimentoComunicacaoVOImpl[0]);
            comunicacaoSelecionada = new String[0];
            atendimentoVO = AtendimentoVO.Factory.newInstance();
            atendimentoVO.setWFAcaoVOArray(new WFAcaoVO[] { WFAcaoVO.Factory.newInstance() });
            listaDadosVO = ListaDadosVO.Factory.newInstance();
        }

        public void setEnderecoVO(EnderecoVO enderecoVO) {
            this.enderecoVO = enderecoVO;
        }

        public EnderecoVO getEnderecoVO() {
            if (this.enderecoVO == null) {
                this.enderecoVO = EnderecoVO.Factory.newInstance();
            }
            return this.enderecoVO;
        }

        public void setListaDadosVO(ListaDadosVO listaDadosVO) {
            this.listaDadosVO = listaDadosVO;
        }

        public ListaDadosVO getListaDadosVO() {
            return this.listaDadosVO;
        }

        public void setNrLinhasSelecionadas(String[] nrLinhasSelecionadas) {
            this.nrLinhasSelecionadas = nrLinhasSelecionadas;
        }

        public String[] getNrLinhasSelecionadas() {
            return this.nrLinhasSelecionadas;
        }

        public void setListaTipoEndereco(TipoEnderecoVO[] listaTipoEndereco) {
            this.listaTipoEndereco = listaTipoEndereco;
        }

        public TipoEnderecoVO[] getListaTipoEndereco() {
            return this.listaTipoEndereco;
        }

        public void setSelecaoFormulario(String selecaoFormulario) {
            this.selecaoFormulario = selecaoFormulario;
        }

        public String getSelecaoFormulario() {
            return this.selecaoFormulario;
        }

        public void setInTipoPessoa(String inTipoPessoa) {
            this.inTipoPessoa = inTipoPessoa;
        }

        public String getInTipoPessoa() {
            return this.inTipoPessoa;
        }

        public void setIdPessoa(String idPessoa) {
            this.idPessoa = idPessoa;
        }

        public String getIdPessoa() {
            return this.idPessoa;
        }

        public void setFormularioCampoVO(FormularioCampoVO formularioCampoVO) {
            this.formularioCampoVO = formularioCampoVO;
        }

        public FormularioCampoVO getFormularioCampoVO() {
            return this.formularioCampoVO;
        }

        public void setIdCampo(String idCampo) {
            this.idCampo = idCampo;
        }

        public String getIdCampo() {
            return this.idCampo;
        }

        public void setTextoPesquisa(String textoPesquisa) {
            this.textoPesquisa = textoPesquisa;
        }

        public String getTextoPesquisa() {
            return this.textoPesquisa;
        }

        public void setDescricaoCompleta(String descricaoCompleta) {
            this.descricaoCompleta = descricaoCompleta;
        }

        public String getDescricaoCompleta() {
            return this.descricaoCompleta;
        }

        public void setIdTipoComunicacao(String idTipoComunicacao) {
            this.idTipoComunicacao = idTipoComunicacao;
        }

        public String getIdTipoComunicacao() {
            return this.idTipoComunicacao;
        }

        public void setIdTipoComunicacaoAbertura(String idTipoComunicacaoAbertura) {
            this.idTipoComunicacaoAbertura = idTipoComunicacaoAbertura;
        }

        public String getIdTipoComunicacaoAbertura() {
            return this.idTipoComunicacaoAbertura;
        }

        public void setFila(String fila) {
            this.fila = fila;
        }

        public String getFila() {
            return this.fila;
        }

        public void setAbreProcessoReaRei(String abreProcessoReaRei) {
            this.abreProcessoReaRei = abreProcessoReaRei;
        }

        public String getAbreProcessoReaRei() {
            return this.abreProcessoReaRei;
        }

        public void setRowIndex(int rowIndex) {
            this.rowIndex = rowIndex;
        }

        public int getRowIndex() {
            return this.rowIndex;
        }

        public void setAtendimentoVOLista(AtendimentoVO atendimentoVOLista) {
            this.atendimentoVOLista = atendimentoVOLista;
        }

        public AtendimentoVO getAtendimentoVOLista() {
            return this.atendimentoVOLista;
        }

        public void setQtDias(String qtDias) {
            this.qtDias = qtDias;
        }

        public String getQtDias() {
            return this.qtDias;
        }

        public void setNmTipo(String nmTipo) {
            this.nmTipo = nmTipo;
        }

        public String getNmTipo() {
            return this.nmTipo;
        }

        public void setIdTipoReaberturaProcesso(int idTipoReaberturaProcesso) {
            this.idTipoReaberturaProcesso = idTipoReaberturaProcesso;
        }

        public int getIdTipoReaberturaProcesso() {
            return this.idTipoReaberturaProcesso;
        }

        public void setIdAtendimentoSituacao(String idAtendimentoSituacao) {
            this.idAtendimentoSituacao = idAtendimentoSituacao;
        }

        public String getIdAtendimentoSituacao() {
            return this.idAtendimentoSituacao;
        }

        public void setNrProtocoloSituacao(String nrProtocoloSituacao) {
            this.nrProtocoloSituacao = nrProtocoloSituacao;
        }

        public String getNrProtocoloSituacao() {
            return this.nrProtocoloSituacao;
        }

        public void setAbertura(int abertura) {
            this.abertura = abertura;
        }

        public int getAbertura() {
            return this.abertura;
        }

        public void setIdAtendimento(String idAtendimento) {
            this.idAtendimento = idAtendimento;
        }

        public String getIdAtendimento() {
            return this.idAtendimento;
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

        public void setIdDiscador(String idDiscador) {
            this.idDiscador = idDiscador;
        }

        public String getIdDiscador() {
            return this.idDiscador;
        }

        public void setIdUsuarioDePara(String idUsuarioDePara) {
            this.idUsuarioDePara = idUsuarioDePara;
        }

        public String getIdUsuarioDePara() {
            return this.idUsuarioDePara;
        }

        public void setIdClienteDePara(String idClienteDePara) {
            this.idClienteDePara = idClienteDePara;
        }

        public String getIdClienteDePara() {
            return this.idClienteDePara;
        }

        public void setIdLinha(String idLinha) {
            this.idLinha = idLinha;
        }

        public String getIdLinha() {
            return this.idLinha;
        }

        public void setIdConta(String idConta) {
            this.idConta = idConta;
        }

        public String getIdConta() {
            return this.idConta;
        }

        public void setTipoOperacao(String tipoOperacao) {
            this.tipoOperacao = tipoOperacao;
        }

        public String getTipoOperacao() {
            return this.tipoOperacao;
        }

        public void setCarregaLinha(String carregaLinha) {
            this.carregaLinha = carregaLinha;
        }

        public String getCarregaLinha() {
            return this.carregaLinha;
        }

        public void setNrLinha(String nrLinha) {
            this.nrLinha = nrLinha;
        }

        public String getNrLinha() {
            return this.nrLinha;
        }

        public void setNrConta(String nrConta) {
            this.nrConta = nrConta;
        }

        public String getNrConta() {
            return this.nrConta;
        }

        public void setTelContato(String telContato) {
            this.telContato = telContato;
        }

        public String getTelContato() {
            return this.telContato;
        }

        public void setTelContatoFrm(String telContatoFrm) {
            this.telContatoFrm = telContatoFrm;
        }

        public String getTelContatoFrm() {
            return this.telContatoFrm;
        }

        public void setIdChamadaTelefonica(long idChamadaTelefonica) {
            this.idChamadaTelefonica = idChamadaTelefonica;
        }

        public long getIdChamadaTelefonica() {
            return this.idChamadaTelefonica;
        }

        public void setNomeContato(String nomeContato) {
            this.nomeContato = nomeContato;
        }

        public String getNomeContato() {
            return this.nomeContato;
        }

        public void setIdContato(String idContato) {
            this.idContato = idContato;
        }

        public String getIdContato() {
            return this.idContato;
        }

        public void setIdUfOperadora(String idUfOperadora) {
            this.idUfOperadora = idUfOperadora;
        }

        public String getIdUfOperadora() {
            return this.idUfOperadora;
        }

        public void setIdGrupoAbertura(String idGrupoAbertura) {
            this.idGrupoAbertura = idGrupoAbertura;
        }

        public String getIdGrupoAbertura() {
            return this.idGrupoAbertura;
        }

        public void setIdTipoLinha(String idTipoLinha) {
            this.idTipoLinha = idTipoLinha;
        }

        public String getIdTipoLinha() {
            return this.idTipoLinha;
        }

        public void setIdTipoCarteira(String idTipoCarteira) {
            this.idTipoCarteira = idTipoCarteira;
        }

        public String getIdTipoCarteira() {
            return this.idTipoCarteira;
        }

        public void setIdSegmentacao(String idSegmentacao) {
            this.idSegmentacao = idSegmentacao;
        }

        public String getIdSegmentacao() {
            return this.idSegmentacao;
        }

        public void setInResponsavelAbertura(String inResponsavelAbertura) {
            this.inResponsavelAbertura = inResponsavelAbertura;
        }

        public String getInResponsavelAbertura() {
            return this.inResponsavelAbertura;
        }

        public void setScriptArvore(String scriptArvore) {
            this.scriptArvore = scriptArvore;
        }

        public String getScriptArvore() {
            return this.scriptArvore;
        }

        public void setScriptCarrega(String scriptCarrega) {
            this.scriptCarrega = scriptCarrega;
        }

        public String getScriptCarrega() {
            return this.scriptCarrega;
        }

        public void setScriptValidacao(String scriptValidacao) {
            this.scriptValidacao = scriptValidacao;
        }

        public String getScriptValidacao() {
            return this.scriptValidacao;
        }

        public void setNmURL(String nmURL) {
            this.nmURL = nmURL;
        }

        public String getNmURL() {
            return this.nmURL;
        }

        public void setIdProcedencia(String idProcedencia) {
            this.idProcedencia = idProcedencia;
        }

        public String getIdProcedencia() {
            return this.idProcedencia;
        }

        public void setIdCanal(String idCanal) {
            this.idCanal = idCanal;
        }

        public String getIdCanal() {
            return this.idCanal;
        }

        public AtendimentoVO getAtendimentoVOComunicacao() {
            return this.atendimentoVOComunicacao;
        }

        public void setAtendimentoVOComunicacao(AtendimentoVO atendimentoVOComunicacao) {
            this.atendimentoVOComunicacao = atendimentoVOComunicacao;
        }

        public void setIdBaixa(String idBaixa) {
            this.idBaixa = idBaixa;
        }

        public String getIdBaixa() {
            return this.idBaixa;
        }

        public void setIdBaixaMensagem(String idBaixaMensagem) {
            this.idBaixaMensagem = idBaixaMensagem;
        }

        public String getIdBaixaMensagem() {
            return this.idBaixaMensagem;
        }

        public void setObservacaoFechamento(String observacaoFechamento) {
            this.observacaoFechamento = observacaoFechamento;
        }

        public String getObservacaoFechamento() {
            return this.observacaoFechamento;
        }

        public void setFlagAtendimentosCorrentes(boolean flagAtendimentosCorrentes) {
            this.flagAtendimentosCorrentes = flagAtendimentosCorrentes;
        }

        public boolean getFlagAtendimentosCorrentes() {
            return this.flagAtendimentosCorrentes;
        }

        public void setScriptArvoreBaixa(String scriptArvoreBaixa) {
            this.scriptArvoreBaixa = scriptArvoreBaixa;
        }

        public String getScriptArvoreBaixa() {
            return this.scriptArvoreBaixa;
        }

        public void setNomeContatoAlterado(String nomeContatoAlterado) {
            this.nomeContatoAlterado = nomeContatoAlterado;
        }

        public String getNomeContatoAlterado() {
            return this.nomeContatoAlterado;
        }

        public void setObservacaoInsistencia(String observacaoInsistencia) {
            this.observacaoInsistencia = observacaoInsistencia;
        }

        public String getObservacaoInsistencia() {
            return this.observacaoInsistencia;
        }

        public void setIdAtendimentoExclusao(String idAtendimentoExclusao) {
            this.idAtendimentoExclusao = idAtendimentoExclusao;
        }

        public String getIdAtendimentoExclusao() {
            return this.idAtendimentoExclusao;
        }

        public String getObservacaoAtendimento() {
            return this.observacaoAtendimento;
        }

        public void setObservacaoAtendimento(String x) {
            this.observacaoAtendimento = x;
        }

        public AtendimentoVO getAtendimentoVO() {
            return this.atendimentoVO;
        }

        public void setAtendimentoVO(AtendimentoVO atendimentoVO) {
            this.atendimentoVO = atendimentoVO;
        }

        public AtendimentoVO getAtendimentoVOFacade() {
            return this.atendimentoVOFacade;
        }

        public void setAtendimentoVOFacade(AtendimentoVO atendimentoVOFacade) {
            this.atendimentoVOFacade = atendimentoVOFacade;
        }

        public AtendimentoTipoComunicacaoVO getTipoComunicacaoSelecionadaVO() {
            return this.tipoComunicacaoSelecionadaVO;
        }

        public void setTipoComunicacaoSelecionadaVO(AtendimentoTipoComunicacaoVO tipoComunicacaoVO) {
            this.tipoComunicacaoSelecionadaVO = tipoComunicacaoVO;
        }

        public String[] getComunicacaoDisponivel() {
            return this.comunicacaoDisponivel;
        }

        public void setComunicacaoDisponivel(String[] comunicacaoDisponivel) {
            this.comunicacaoDisponivel = comunicacaoDisponivel;
        }

        public String[] getComunicacaoSelecionada() {
            return this.comunicacaoSelecionada;
        }

        public void setComunicacaoSelecionada(String[] comunicacaoSelecionada) {
            this.comunicacaoSelecionada = comunicacaoSelecionada;
        }

        public String getTipoComunicacaoSelecionada() {
            return this.tipoComunicacaoSelecionada;
        }

        public void setTipoComunicacaoSelecionada(String tipoComunicacaoSelecionada) {
            this.tipoComunicacaoSelecionada = tipoComunicacaoSelecionada;
        }

        public String getContaSelecionada() {
            return this.contaSelecionada;
        }

        public void setContaSelecionada(String contaSelecionada) {
            this.contaSelecionada = contaSelecionada;
        }

        public ContaVO getContaSelecionadaVO() {
            return this.contaSelecionadaVO;
        }

        public void setContaSelecionadaVO(ContaVO contaSelecionadaVO) {
            this.contaSelecionadaVO = contaSelecionadaVO;
        }

        public FormularioVO getFormularioSalvarVO() {
            return this.formularioSalvarVO;
        }

        public void setFormularioSalvarVO(FormularioVO formularioSalvarVO) {
            this.formularioSalvarVO = formularioSalvarVO;
        }

        public String getLinhaSelecionada() {
            return this.linhaSelecionada;
        }

        public void setLinhaSelecionada(String linhaSelecionada) {
            this.linhaSelecionada = linhaSelecionada;
        }

        public String getIdFase() {
            return this.idFase;
        }

        public void setIdFase(String idFase) {
            this.idFase = idFase;
        }

        public int getNumeroCampos() {
            return this.numeroCampos;
        }

        public void setNumeroCampos(int numeroCampos) {
            this.numeroCampos = numeroCampos;
        }

        public ValorCampo[] getValorCampo() {
            int numCampos = this.getNumeroCampos();
            if (valorCampo == null) {
                valorCampo = new ValorCampo[numCampos];
                for (int i = 0; i < valorCampo.length; i++) {
                    valorCampo[i] = new ValorCampo();
                    valorCampo[i].setValorArray(new String[0]);
                }
            }
            return (this.valorCampo);
        }

        public void setValorCampo(ValorCampo[] valorCampo) {
            this.valorCampo = valorCampo;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getUser() {
            return this.user;
        }

        public void setIdLinhaAtendimento(String idLinhaAtendimento) {
            this.idLinhaAtendimento = idLinhaAtendimento;
        }

        public String getIdLinhaAtendimento() {
            return this.idLinhaAtendimento;
        }

        public void setInCelular(String inCelular) {
            this.inCelular = inCelular;
        }

        public String getInCelular() {
            if (this.inCelular == null) {
                return "true";
            }
            return this.inCelular;
        }

        public void setListaContatosVO(WFListaContatosVO value) {
            this.listaContatosVO = value;
        }

        public WFListaContatosVO getListaContatosVO() {
            return this.listaContatosVO;
        }

        public void setAdmGrupoCamposVO(AdmGrupoCamposVO[] admGrupoCamposVO) {
            this.admGrupoCamposVO = admGrupoCamposVO;
        }

        public AdmGrupoCamposVO[] getAdmGrupoCamposVO() {
            if (this.admGrupoCamposVO == null) {
                this.admGrupoCamposVO = new AdmGrupoCamposVO[this.getNumeroCampos()];
            }
            return this.admGrupoCamposVO;
        }

        public void setSgUF(String sgUF) {
            this.sgUF = sgUF;
        }

        public String getSgUF() {
            if (this.sgUF == null) {
                this.sgUF = ConstantesCRM.SVAZIO;
            }
            return this.sgUF;
        }

        public void setNmPais(String nmPais) {
            this.nmPais = nmPais;
        }

        public String getNmPais() {
            if (this.nmPais == null) {
                this.nmPais = ConstantesCRM.SVAZIO;
            }
            return this.nmPais;
        }
    }

    public static class ValorCampo implements Serializable {
        private static final long serialVersionUID = -2258128582081567961L;
        private String            valor;
        private String[]          valorArray = new String[0];

        public String getValor() {
            return valor;
        }

        public String[] getValorArray() {
            return valorArray;
        }

        public void setValor(String strings) {
            valor = strings;
        }

        public void setValorArray(String[] string) {
            valorArray = string;
        }
    }

    private AbaContato abaContato = new AbaContato();

    public AbaContato getAbaContato() {
        return this.abaContato;
    }

    public void setAbaContato(AbaContato abaContato) {
        this.abaContato = abaContato;
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="abaContato.jsp"
     * @jpf:forward name="controlar" path="controlarContato.do"
     */
    protected ActionForward loadContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AbaContato form = (AbaContato) formParam;
        log.debug("RegistrarContatoController:loadContato.do - Inicio do Metodo]");
        String user = ConstantesCRM.getCurrentUser(request.getSession());
        String destino = ConstantesCRM.SVAZIO;
        String idPessoa = ConstantesCRM.SVAZIO;
        
        if (request.getParameter("idPessoaCliente") == null) {
            idPessoa = form.getIdPessoa();
        } else {
            idPessoa = request.getParameter("idPessoaCliente");
        }
        
        if (idPessoa != null) {
            abaContato.setIdPessoa(idPessoa);
            abaContato.setAbaContato(registrarContatoFacade.obtemComunicacaoPessoa(idPessoa, user).getDadosAbaLupaCliente().getComunicacaoVOArray());
            form.setIdPessoa(idPessoa);
        }
        
        String param = request.getParameter("tipo");
        if ("ok".equals(request.getAttribute("reload"))) {
            lupaCliente = registrarContatoFacade.obtemComunicacaoPessoa(request.getParameter("idPessoa"), user);
            contato = lupaCliente.getDadosAbaLupaCliente().getComunicacaoVOArray();
            abaContato.setAbaContato(contato);
            destino = ConstantesCRM.SUCCESS;
        } else {
            if (param == null) {
                destino = ConstantesCRM.SUCCESS;
            } else {
                ActionRedirect redir = new ActionRedirect(mapping.findForward("controlar"));
                redir.addParameter(ConstantesCRM.SACTION, request.getParameter("tipo"));
                redir.addParameter("idArray", request.getParameter("idArray"));
                redir.addParameter("idComunicacao", request.getParameter("idComunicacao"));
                redir.addParameter("idPessoa", idPessoa);
                form.setAcao(request.getParameter("tipo"));
                form.setIdArray(request.getParameter("idArray"));
                form.setIdComunicacao(request.getParameter("idComunicacao"));
                abaContato.setAcao(request.getParameter("tipo"));
                abaContato.setIdArray(request.getParameter("idArray"));
                abaContato.setIdComunicacao(request.getParameter("idComunicacao"));
                return redir;
            }
        }
        
        log.debug("RegistrarContatoController:loadContato.do - Fim do Metodo]");
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(destino);
    }

    /**
     * @jpf:action
     * @jpf:forward name="novo" path="incluiContatoAt.jsp"
     * @jpf:forward name="alterar" path="alteraContatoAt.jsp"
     * @jpf:forward name="excluir" path="loadContato.do"
     */
    protected ActionForward controlarContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AbaContato form = (AbaContato) formParam;
        log.debug("RegistrarContatoController:controlarContato.do - Inicio do Metodo]");
        String user = ConstantesCRM.getCurrentUser(request.getSession());
        String destino = form.getAcao();
        
        if ("excluir".equalsIgnoreCase(destino)) {
            LupaClienteVO deleteComunicacao = LupaClienteVO.Factory.newInstance();
            deleteComunicacao.addNewDadosAbaLupaCliente();
            deleteComunicacao.getDadosAbaLupaCliente().addNewComunicacaoVO();
            deleteComunicacao.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setIdComunicacao(form.getIdComunicacao());
            request.setAttribute("reload", "ok");
            lupaClienteFacadeControl.setExcluirComunicacao(user, deleteComunicacao);
            
            log.debug("RegistrarContatoController:controlarContato.do - Fim do Metodo]");
            ActionRedirect redir = new ActionRedirect(mapping.findForward(destino));
            redir.addParameter("idPessoaCliente", form.getIdPessoa());
            
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return redir;
        
        } else if ("alterar".equalsIgnoreCase(destino)) {
            ComunicacaoVO alterarComunicacao = ComunicacaoVO.Factory.newInstance();
            alterarComunicacao.addNewTipoComunicacaoVO();
            alterarComunicacao = abaContato.abaContato[Integer.parseInt(form.getIdArray())];
            abaContato.setContato(alterarComunicacao);
            abaContato.setIdComunicacao(form.getIdComunicacao());
            String dsComunicacao = alterarComunicacao.getTipoComunicacaoVO().getIdTipoComunicacao() + "," + alterarComunicacao.getDsContato();
            abaContato.setDsComunicacao(dsComunicacao);
            abaContato.setIdPessoa(form.getIdPessoa());
            log.debug("RegistrarContatoController:controlarContato.do - Fim do Metodo]");
            
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(destino);
        
        } else {
            ComunicacaoVO incluirComunicacao = ComunicacaoVO.Factory.newInstance();
            incluirComunicacao.addNewTipoComunicacaoVO();
            abaContato.setContato(incluirComunicacao);
            abaContato.setDsComunicacao(ConstantesCRM.SVAZIO);
            abaContato.setListaTipos(registrarContatoFacade.obtemTipoComunicacaoAtendimento(user));
            abaContato.setIdPessoa(form.getIdPessoa());
            log.debug("RegistrarContatoController:controlarContato.do - Fim do Metodo]");
            
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(destino);
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="loadContato.do"
     * @jpf:forward name="executa" path="executa.jsp"
     */
    protected ActionForward salvarContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AbaContato form = (AbaContato) formParam;
        log.debug("RegistrarContatoController:salvarContato.do - Inicio do Metodo]");
        String user = ConstantesCRM.getCurrentUser(request.getSession());
        LupaClienteVO comunicacaoAlterada = LupaClienteVO.Factory.newInstance();
        comunicacaoAlterada.addNewDadosAbaLupaCliente().addNewComunicacaoVO();
        comunicacaoAlterada.addNewDadosLupaCliente();
        String resposta = ConstantesCRM.SVAZIO;
        
        if ("alteracao".equalsIgnoreCase(request.getParameter("tipo"))) {
            comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setIdComunicacao(form.getIdComunicacao());
            comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setDsContato(form.contato.getDsContato().trim());
            comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setNrSequencia(form.contato.getNrSequencia().trim());
            resposta = lupaClienteFacadeControl.setSalvarAlterarComunicacao(user, comunicacaoAlterada, String.valueOf(form.getIdPessoa()));
        
        } else {
            comunicacaoAlterada.getDadosLupaCliente().setIdPessoa(form.getIdPessoa());
            comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setDsContato(form.contato.getDsContato().trim());
            comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).setNrSequencia(form.contato.getNrSequencia().trim());
            comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).addNewTipoComunicacaoVO();
            comunicacaoAlterada.getDadosAbaLupaCliente().getComunicacaoVOArray(0).getTipoComunicacaoVO().setIdTipoComunicacao(form.getIdTipoSelecionado());
            resposta = lupaClienteFacadeControl.setSalvarNovaComunicacao(user, comunicacaoAlterada);
        }
        
        abaContato = new AbaContato();
        abaContato.setIdPessoa(form.getIdPessoa());
        abaContato.setDsComunicacao(form.getDsComunicacao());
        
        if (resposta.indexOf("DUPLICATE KEY") > 0) {
            abaContato.setInMsgRetorno("true");
        
        } else {
            request.setAttribute("reload", "ok");
            abaContato.setInMsgRetorno("false");
        }
        
        log.debug("RegistrarContatoController:salvarContato.do - Fim do Metodo]");
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward("executa");
    }

    public static class AbaContato extends ActionForm {
        private static final long      serialVersionUID  = -1914290554490748210L;
        private ListaTipoComunicacaoVO listaTipos        = ListaTipoComunicacaoVO.Factory.newInstance();
        private String                 idTipoSelecionado = ConstantesCRM.SVAZIO;
        private ComunicacaoVO          contato;
        private String                 idComunicacao     = ConstantesCRM.SVAZIO;
        private String                 idPessoa          = ConstantesCRM.SVAZIO;
        private String                 idArray           = ConstantesCRM.SVAZIO;
        private String                 acao              = ConstantesCRM.SVAZIO;
        private ComunicacaoVO[]        abaContato;
        private String                 inMsgRetorno      = ConstantesCRM.SVAZIO;
        private String                 dsComunicacao     = ConstantesCRM.SVAZIO;

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

        public void setDsComunicacao(String dsComunicacao) {
            this.dsComunicacao = dsComunicacao;
        }

        public String getDsComunicacao() {
            return this.dsComunicacao;
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
    }
}
