package cliente.PrePago;

import java.io.BufferedOutputStream;
import java.math.BigInteger;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.axis.AxisFault;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.brazilutils.br.uf.ie.InscricaoEstadual;
import br.com.vivo.fo.atendimento.vo.AbreProtocoloOutTODocument.AbreProtocoloOutTO;
import br.com.vivo.fo.atendimento.vo.AbreProtocoloTODocument.AbreProtocoloTO;
import br.com.vivo.fo.atendimento.vo.AlterProtocoloOutTODocument.AlterProtocoloOutTO;
import br.com.vivo.fo.atendimento.vo.GetDadosProtocoloOutTODocument.GetDadosProtocoloOutTO;
import br.com.vivo.fo.atendimento.vo.SETPPTMAVODocument.SETPPTMAVO;
import br.com.vivo.fo.cliente.ClienteUtils;
import br.com.vivo.fo.cliente.vo.AjaxErrorHandlerVODocument.AjaxErrorHandlerVO;
import br.com.vivo.fo.cliente.vo.AlterProtocoloTODocument.AlterProtocoloTO;
import br.com.vivo.fo.cliente.vo.CNAEVODocument.CNAEVO;
import br.com.vivo.fo.cliente.vo.DadosEnderecoDocument.DadosEndereco;
import br.com.vivo.fo.cliente.vo.EnderecoVODocument.EnderecoVO;
import br.com.vivo.fo.cliente.vo.ListasVODocument.ListasVO;
import br.com.vivo.fo.cliente.vo.LupaClienteVODocument.LupaClienteVO;
import br.com.vivo.fo.cliente.vo.PFDocument.PF;
import br.com.vivo.fo.cliente.vo.PJDocument.PJ;
import br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.cliente.vo.PesquisaEnderecoVODocument.PesquisaEnderecoVO;
import br.com.vivo.fo.cliente.vo.PesquisaEnderecoVODocument.PesquisaEnderecoVO.ListaEnderecos;
import br.com.vivo.fo.cliente.vo.PesquisaTIVODocument.PesquisaTIVO;
import br.com.vivo.fo.cliente.vo.PrePagoRetornoValidaLinhaDocument.PrePagoRetornoValidaLinha;
import br.com.vivo.fo.cliente.vo.PrePagoVODocument.PrePagoVO;
import br.com.vivo.fo.cliente.vo.PrePagoVODocument.PrePagoVO.PUP;
import br.com.vivo.fo.commons.utils.GetParametro;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.cliente.prePago.PrePagoFacade;
import br.com.vivo.fo.ctrls.cliente.protocolo.ProtocoloFacade;
import br.com.vivo.fo.ctrls.cliente.telaInicial.TelaInicialFacade;
import br.com.vivo.fo.ctrls.workflow.registrarContato.RegistrarContatoFacade;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO;
import com.indracompany.actions.AbstractAction;
import com.indracompany.ws.enderecoservice.EnderecoSOAPProxy;
import com.indracompany.ws.enderecoservice.ParametrosBuscarListaEnderecos;
import com.indracompany.ws.enderecoservice.SecurityHeaderHelper;
import com.indracompany.ws.enderecoservice.to.Endereco;
import com.indracompany.ws.enderecoservice.to.ErroInfo;
import commons.errors.FormError;

public class PrePagoController extends AbstractAction {

    private static final long      serialVersionUID                    = -8824226094230381462L;

    @EJB
    private PrePagoFacade          prePagoFacade;

    @EJB
    private RegistrarContatoFacade registrarContatoFacade;

    @EJB
    private TelaInicialFacade      telaInicialFacade;

    @EJB
    private ProtocoloFacade        protocoloFacade;

    private static Logger          log                                 = new Logger("clientes");

    protected global.Global        globalApp                           = new global.Global();

    // Declara formulários
    private ListasVO               listasVO;
    private PrePagoForm            prePagoForm;
    private PrePagoEnderecoForm    prePagoEnderecoForm;
    private ParametrosVO           parametrosVO;

    // Declara variaveis e objetos
    private String                 idUsuario;
    private String                 idPessoaCliente;
    private String                 nrLinha;
    private String                 inTipoPessoa;
    private String                 idTipoRelaciona;
    private String                 idTipoLinha;

    private boolean                isTransf                            = false;
    private boolean                isInclui                            = false;
    private boolean                isAltera                            = false;

    static final String            MENSAGEM_GERAL                      = "Houve um erro durante carregamento dos dados. Tente novamente dentro de alguns instantes ou contate o Helpdesk.";

    private final String           parametro_alt_dados                 = "PALITAGEM_PREPAGO_ALTERACAO_DADOS";
    private final String           parametro_inc_prepago               = "PALITAGEM_PREPAGO_NOVA_INCLUSAO";
    private final String           parametro_alt_endereco              = "PALITAGEM_PREPAGO_ALTERACAO_ENDERECO";
    private final String           parametro_end_default               = "PALITAGEM_PREPAGO_ENDERECO_DEFAULT";
    private final String           parametro_exc_endereco              = "PALITAGEM_PREPAGO_EXCLUSAO_ENDERECO";
    private final String           parametro_troca_tit                 = "PALITAGEM_PREPAGO_TROCA_TITULARIDADE";
    private final String           parametro_grp_abertura              = "PALITAGEM_PREPAGO_GRUPO_ABERTURA";
    private final String           parametro_insincronismo             = "PP_INSINCRONISMO";
    private final String           PARAMETRO_PESQUISA_ENDERECO_NA_BASE = "IN_PESQUISA_ENDERECO_BASE";
    private final String           PARAMETRO_ENDPOINT_PESQUISAENDERECO = "BUSCAR_LISTA_ENDERECOS_ENDPOINT";
    private final String           PF = "PF";
    private final String           PJ = "PJ";

    private Boolean                pesquisaEnderecoWebService          = null;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if ("begin".equals(mapping.getParameter())) {
            return begin(mapping, form, request, response);
        } else if ("loadCadastro".equals(mapping.getParameter())) {
            return loadCadastro(mapping, form, request, response);
        } else if ("validaIE".equals(mapping.getParameter())) {
            return validaIE(mapping, form, request, response);
        } else if ("execCadastro".equals(mapping.getParameter())) {
            return execCadastro(mapping, form, request, response);
        } else if ("loadAlteracao".equals(mapping.getParameter())) {
            return loadAlteracao(mapping, form, request, response);
        } else if ("loadDadosAlteracao".equals(mapping.getParameter())) {
            return loadDadosAlteracao(mapping, form, request, response);
        } else if ("limparDadosEndereco".equals(mapping.getParameter())) {
            return limparDadosEndereco(mapping, form, request, response);
        } else if ("execAlteracao".equals(mapping.getParameter())) {
            return execAlteracao(mapping, form, request, response);
        } else if ("loadTitularidade".equals(mapping.getParameter())) {
            return loadTitularidade(mapping, form, request, response);
        } else if ("loadDadosTitularidade".equals(mapping.getParameter())) {
            return loadDadosTitularidade(mapping, form, request, response);
        } else if ("execTitularidade".equals(mapping.getParameter())) {
            return execTitularidade(mapping, form, request, response);
        } else if ("incluirEndereco".equals(mapping.getParameter())) {
            return incluirEndereco(mapping, form, request, response);
        } else if ("loadDadosAlteraEndereco".equals(mapping.getParameter())) {
            return loadDadosAlteraEndereco(mapping, form, request, response);
        } else if ("alterarEndereco".equals(mapping.getParameter())) {
            return alterarEndereco(mapping, form, request, response);
        } else if ("excluirEndereco".equals(mapping.getParameter())) {
            return excluirEndereco(mapping, form, request, response);
        } else if ("tabListaEnderecos".equals(mapping.getParameter())) {
            return tabListaEnderecos(mapping, form, request, response);
        } else if ("validarLinha".equals(mapping.getParameter())) {
            return validarLinha(mapping, form, request, response);
        } else if ("validarCNAE".equals(mapping.getParameter())) {
            return validarCNAE(mapping, form, request, response);
        } else if ("tabListaCEP".equals(mapping.getParameter())) {
            return tabListaCEP(mapping, form, request, response);
        } else if ("loadDadosPrePago".equals(mapping.getParameter())) {
            return loadDadosPrePago(mapping, form, request, response);
        } else if ("loadPesquisaEndereco".equals(mapping.getParameter())) {
            return loadPesquisaEndereco(mapping, form, request, response);
        } else if ("getListaClientes".equals(mapping.getParameter())) {
            return getListaClientes(mapping, form, request, response);
        } else if ("pesquisaCliente".equals(mapping.getParameter())) {
            return pesquisaCliente(mapping, form, request, response);
        } else if ("getAjaxEndereco".equals(mapping.getParameter())) {
            return getAjaxEndereco(mapping, form, request, response);
        } else if ("pesquisarEndereco".equals(mapping.getParameter())) {
            return pesquisarEndereco(mapping, form, request, response);
        } else if ("detalhesEndereco".equals(mapping.getParameter())) {
            return detalhesEndereco(mapping, form, request, response);
        }
        return begin(mapping, form, request, response);
    }

    /**
     * Inicia o controller. Recupera as variaveis da sessão
     * 
     * @jpf:action
     * @jpf:forward name="successCad" path="loadCadastro.do"
     * @jpf:forward name="successAlt" path="loadAlteracao.do"
     * @jpf:forward name="successTit" path="loadTitularidade.do"
     * @jpf:forward name="validacao" path="validacaoMenu.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            log.debug("PrePagoController:begin()");
            setPrePagoForm(new PrePagoForm());
            setPrePagoEnderecoForm(new PrePagoEnderecoForm());

            getPrePagoForm().setIsNrLinhaValid("F");
            this.parametrosVO = getParametrosVO(request);

            // Verifica atraves do parametro enviado, qual o tipo de operação a ser realizada
            idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
            isTransf = request.getParameter("transf") != null ? true : false;
            isInclui = request.getParameter("incluir") != null ? true : false;
            isAltera = request.getParameter("alter") != null ? true : false;

            if (isInclui) {
                this.parametrosVO = ParametrosVO.Factory.newInstance();
                String source = request.getParameter("source");

                if (source != null && "TI".equals(source)) {
                    String nrLinhaPre = request.getParameter("nrLinha");
                    PrePagoRetornoValidaLinha validarLinha = validarLinha(nrLinhaPre, false, request);
                    getPrePagoForm().setIsNrLinhaValid("T");
                    getPrePagoForm().setNrLinha(nrLinhaPre);
                    getPrePagoForm().setCdSeguranca(validarLinha.getDigito());
                    getPrePagoForm().setIdLinha(validarLinha.getIdlinhaTelefonica());

                } else {
                    getPrePagoForm().setIsNrLinhaValid("F"); // Informa que a linha ainda nao foi validada.
                }

                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                return mapping.findForward("successCad");

            } else if (isAltera || isTransf) {
                if (ConstantesCRM.SONE.equals(parametrosVO.getInBloqueado())) {
                    String tmpIdGrupo = parametrosVO.getIdGrupo();
                    parametrosVO = ParametrosVO.Factory.newInstance();
                    parametrosVO.setIdGrupo(tmpIdGrupo);
                }

                nrLinha = (isAltera || isTransf) ? parametrosVO.getNrLinha() : parametrosVO.getNrTelefone();
                idPessoaCliente = ConstantesCRM.SVAZIO.equals(parametrosVO.getIdPessoaCliente()) ? null : parametrosVO.getIdPessoaCliente();
                inTipoPessoa = parametrosVO.getInTipoPessoa(); // PF ou PJ
                idTipoRelaciona = parametrosVO.getIdTipoRelacionamento(); // 1 = cliente, 2 = usuario ou 3 = outros
                idTipoLinha = parametrosVO.getIdTipoLinha();// 1=pos pago 2=pre pago 5=pos chip 6=pre chip

                // Efetua as validações principais para a Operação de PrePago.
                boolean isProspect = idTipoRelaciona != null && ConstantesCRM.SSIX.equals(idTipoRelaciona) ? true : false;
                boolean isTipoLinhaPos = ConstantesCRM.SONE.equals(idTipoLinha) || ConstantesCRM.SFIVE.equals(idTipoLinha);
                boolean isTipoLinhaCtrl = ConstantesCRM.SFOUR.equals(idTipoLinha) || ConstantesCRM.SSEVEN.equals(idTipoLinha);
                boolean isTipoLinhaInv = (isTipoLinhaPos || isTipoLinhaCtrl);
                boolean isAlteracaoInv = isAltera && (idPessoaCliente == null || isProspect);
                boolean isTrocaTitInv = isTransf && (idPessoaCliente == null || isProspect);
                boolean isNaoIdentificado = idPessoaCliente != null && "26".equals(idPessoaCliente);
                boolean isSemCliente = nrLinha == null;

                // Haverá 3 tipos de retorno na validação da Linha
                // Não tem usuario/cliente associado a Linha, não encontrou nada e possui usuario/cliente associado a
                // linha.
                PrePagoRetornoValidaLinha validarLinha = validarLinha(nrLinha, isAltera, request);

                // Validações básicas para a linha.
                if (isNaoIdentificado || isTipoLinhaInv || isAlteracaoInv || isSemCliente) {
                    if (isNaoIdentificado) {
                        request.setAttribute("msg", nrLinha);
                        request.setAttribute("naoIdentificado", "true");

                    } else if ((isTransf || isAltera) && isSemCliente) {
                        request.setAttribute("semCliente", "true");

                    } else if (isAlteracaoInv || (isTipoLinhaInv && !isProspect) || (isAltera && isProspect)) {
                        if (isTipoLinhaInv && !isProspect) {
                            request.setAttribute("nrLinha", nrLinha);
                            if (isTipoLinhaPos) {
                                request.setAttribute("posPago", "true");
                            } else if (isTipoLinhaCtrl) {
                                request.setAttribute("linCtrl", "true");
                            } else {
                                if (isTrocaTitInv) {
                                    request.setAttribute("tranf", "true");
                                } else {
                                    request.setAttribute("geral", "true");
                                }
                            }
                        } else {
                            request.setAttribute("geral", "true");
                        }
                    }
                }
                if ("BLOQ".equals(validarLinha.getResult())) {
                    request.setAttribute("msg", " DDD " + nrLinha.substring(0, 2) + " bloqueado temporariamente. Não é possivel seguir com o cadastro.");
                    request.setAttribute("bloq", "true");
                }
                if (isAltera && ConstantesCRM.SOK.equals(validarLinha.getResult()) && !ConstantesCRM.SVAZIO.equals(validarLinha.getIdPessoa())) {
                    getPrePagoForm().setNrLinha(nrLinha);
                    getPrePagoForm().setIdLinha(validarLinha.getIdlinhaTelefonica());
                    getPrePagoForm().setIdPessoa(validarLinha.getIdPessoa());
                    getPrePagoForm().setInTipoPessoa(ConstantesCRM.SONE.equals(validarLinha.getIdTipoPessoa()) ? PF : ConstantesCRM.STWO.equals(validarLinha.getIdTipoPessoa()) ? PJ : ConstantesCRM.SVAZIO);
                    request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                    return mapping.findForward("successAlt");
                }
                if (isTransf && ConstantesCRM.SOK.equals(validarLinha.getResult()) && !ConstantesCRM.SVAZIO.equals(validarLinha.getIdPessoa())) {
                    getPrePagoForm().setNrLinha(nrLinha);
                    getPrePagoForm().setIdLinha(validarLinha.getIdlinhaTelefonica());
                    getPrePagoForm().setIdPessoa(validarLinha.getIdPessoa());
                    getPrePagoForm().setInTipoPessoa(ConstantesCRM.SONE.equals(validarLinha.getIdTipoPessoa()) ? PF : ConstantesCRM.STWO.equals(validarLinha.getIdTipoPessoa()) ? PJ : ConstantesCRM.SVAZIO);
                    getPrePagoForm().setNmCliente(getParametrosVO(request).getNmCliente());
                    request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                    return mapping.findForward("successTit");
                }
            }
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward("validacao");

        } catch (Exception e) {
            FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
            formError.setTarget(ConstantesCRM.FRAMEAPP);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    private ParametrosVO getParametrosVO(HttpServletRequest request) {
        ParametrosVO parametrosVO = request.getSession().getAttribute(ConstantesCRM.SPARAMETROS) != null ? (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS) : ParametrosVO.Factory.newInstance();
        String nrProtocolo = (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO);
        parametrosVO.setNrProtocolo(nrProtocolo);
        return parametrosVO;
    }

    /* ************************
     * Cadastramento de Clientes Pre Pago ************************
     */

    /**
     * @jpf:action
     * @jpf:forward name="success" path="index.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward loadCadastro(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            getPrePagoForm().setTpOperacao("C");
            ListasVO listasVO = prePagoFacade.getListasVO(idUsuario);
            setListasVO(listasVO);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);

        } catch (Exception e) {
            FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
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
    public ActionForward validaIE(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String nrIE = request.getParameter("nrIE");
        String cdUF = request.getParameter("cdUF");
        boolean isValid = false;
        String xmlDados = "<out><validIE>";

        if (!ConstantesCRM.SVAZIO.equals(cdUF) && !"--".equals(cdUF)) {
            org.brazilutils.br.uf.UF validacaoIE = org.brazilutils.br.uf.UF.valueOf(cdUF);
            InscricaoEstadual iE = validacaoIE.getInscricaoEstadual();
            iE.setNumber(nrIE);

            try {
                isValid = iE.isValid();
            } catch (Exception e) {
                isValid = true;
            }
        }

        xmlDados += isValid;
        xmlDados += "</validIE></out>";

        response.setContentType(ConstantesCRM.SContentType);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
        bufferedOutputStream.write(xmlDados.toString().getBytes(ConstantesCRM.SISO));
        bufferedOutputStream.flush();
        bufferedOutputStream.close();

        return null;
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="index.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward execCadastro(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            PrePagoForm form = (PrePagoForm) formParam;
            form.setTpOperacao("C"); // C - CADASTRAR T - TITULARIDADE A - ALTERAR
            getPrePagoForm().setTpOperacao("C");

            idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
            PrePagoVO prePagoVO = PrePagoVO.Factory.newInstance();
            prePagoVO.setIdLinhaTelefonica(getPrePagoForm().getIdLinha());
            prePagoVO.setNrLinha(getPrePagoForm().getNrLinha());
            prePagoVO.setTpOperacao("C");

            if (PF.equals(form.getInTipoPessoa())) {
                prePagoVO.setInTipoPessoa(ConstantesCRM.SONE);
                PF pf = prePagoVO.addNewPF();
                // if (!ConstantesCRM.SVAZIO.equals(form.getIdPessoaCli())) pf.setIdPessoa(form.getIdPessoaCli());

                prePagoVO.setInUsuario(form.getInUsuario());
                pf.setNmPessoa(form.getNmPessoaCli());
                pf.setIdSexo(form.getIdSexoCli());

                pf.addNewDocumento();
                // if (!ConstantesCRM.SVAZIO.equals(form.getIdDocumentoCliA()))
                // pf.getDocumentoArray(0).setIdDocumento(form.getIdDocumentoCliA());
                pf.getDocumentoArray(0).setIdTipoDocumento(form.getIdTipoDocumentoCliA());
                pf.getDocumentoArray(0).setNrDocumento(form.getNrDocumentoCliA());

                if (!ConstantesCRM.SZERO.equals(form.getIdTipoDocumentoCliB()) && !ConstantesCRM.SVAZIO.equals(form.getIdTipoDocumentoCliB())) {
                    pf.addNewDocumento();
                    // if (!ConstantesCRM.SVAZIO.equals(form.getIdDocumentoCliB()))
                    // pf.getDocumentoArray(1).setIdDocumento(form.getIdDocumentoCliB());
                    pf.getDocumentoArray(1).setIdTipoDocumento(form.getIdTipoDocumentoCliB());
                    pf.getDocumentoArray(1).setNrDocumento(form.getNrDocumentoCliB());
                }

                // Se foi enviado o indicador de Documento RG, preenche os dados de RG, pois deve haver um unico RG para
                // um cliente.
                if (!ConstantesCRM.SVAZIO.equals(form.getInDocumentoRGCli())) {
                    int pos = Integer.parseInt(form.getInDocumentoRGCli()); // Recebe a posição dos dados do RG se é
                    // para o primeiro documento ou para o
                    // segundo.
                    pf.getDocumentoArray(pos).setIdUFDocumento(form.getIdUFDocumentoCli());
                    pf.getDocumentoArray(pos).setDsOrgaoEmissor(form.getDsOrgaoEmissorCli());
                    pf.getDocumentoArray(pos).setDtExpedicao(form.getDtExpedicaoCli());
                }
                pf.setDtNascimento(form.getDtNascimentoCli());

                if (!ConstantesCRM.SVAZIO.equals(form.getIdTipoTelefoneCliA()) && !ConstantesCRM.SZERO.equals(form.getIdTipoTelefoneCliA())) {
                    pf.addNewTelefone();
                    // if (!ConstantesCRM.SVAZIO.equals(form.getIdContatoCliA()))
                    // pf.getTelefoneArray(0).setIdContato(form.getIdContatoCliA());
                    pf.getTelefoneArray(0).setIdTipoTelefone(form.getIdTipoTelefoneCliA());
                    pf.getTelefoneArray(0).setNrTelefone(form.getNrTelefoneCliA());
                    pf.getTelefoneArray(0).setNmContato(form.getNmContatoCliA());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdTipoTelefoneCliB()) && !ConstantesCRM.SZERO.equals(form.getIdTipoTelefoneCliB())) {
                    pf.addNewTelefone();
                    // if (!ConstantesCRM.SVAZIO.equals(form.getIdContatoCliB()))
                    // pf.getTelefoneArray(1).setIdContato(form.getIdContatoCliB());
                    pf.getTelefoneArray(1).setIdTipoTelefone(form.getIdTipoTelefoneCliB());
                    pf.getTelefoneArray(1).setNrTelefone(form.getNrTelefoneCliB());
                    pf.getTelefoneArray(1).setNmContato(form.getNmContatoCliB());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdTipoTelefoneCliC()) && !ConstantesCRM.SZERO.equals(form.getIdTipoTelefoneCliC())) {
                    pf.addNewTelefone();
                    // if (!ConstantesCRM.SVAZIO.equals(form.getIdContatoCliC()))
                    // pf.getTelefoneArray(2).setIdContato(form.getIdContatoCliC());
                    pf.getTelefoneArray(2).setIdTipoTelefone(form.getIdTipoTelefoneCliC());
                    pf.getTelefoneArray(2).setNrTelefone(form.getNrTelefoneCliC());
                    pf.getTelefoneArray(2).setNmContato(form.getNmContatoCliC());
                }

                if (!ConstantesCRM.SVAZIO.equals(form.getNmEmailParticularCli())) {
                    pf.setNmEmailParticular(form.getNmEmailParticularCli());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getNmEmailComercialCli())) {
                    pf.setNmEmailComercial(form.getNmEmailComercialCli());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdEstadoCivilCli()) && !ConstantesCRM.SZERO.equals(form.getIdEstadoCivilCli())) {
                    pf.setIdEstadoCivil(form.getIdEstadoCivilCli());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdEscolaridadeCli()) && !ConstantesCRM.SZERO.equals(form.getIdEscolaridadeCli())) {
                    pf.setIdEscolaridade(form.getIdEscolaridadeCli());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getDsProfissaoCli())) {
                    pf.setDsProfissao(form.getDsProfissaoCli());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdNatOcupacaoCli()) && !ConstantesCRM.SZERO.equals(form.getIdNatOcupacaoCli())) {
                    pf.setIdNatOcupacao(form.getIdNatOcupacaoCli());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getNrCPRCli())) {
                    pf.setNrCPR(form.getNrCPRCli());
                }

                if (getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray() != null) {
                    pf.setDadosEnderecoArray(preparaEnderecoPrincipal(getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).getDadosEnderecoArray()));
                }

            } else if (PJ.equals(form.getInTipoPessoa())) {
                prePagoVO.setInTipoPessoa(ConstantesCRM.STWO);
                prePagoVO.setInUsuario(form.getInUsuario());

                PJ pj = prePagoVO.addNewPJ();
                // if (!ConstantesCRM.SVAZIO.equals(form.getIdPessoaPJ())) pj.setIdPessoa(form.getIdPessoaPJ());
                pj.setNrCNPJ(form.getNrCNPJ());
                pj.setNmFantasia(form.getNmFantasia());
                pj.setNmRazaoSocial(form.getNmRazaoSocial());
                pj.setNrCNAE(form.getNrCNAE());
                pj.setIdTipoInscricao(form.getIdTipoInscricao());
                pj.setNrInscricao(form.getNrInscricao());
                pj.setNrCCM(form.getNrCCM());
                pj.setIdClassEmpresa(form.getIdClassEmpresa());
                pj.setIdClassTributaria(form.getIdClassTributaria());
                pj.setDtFundacao(form.getDtFundacao());

                pj.addNewTelefone();
                // if (!ConstantesCRM.SVAZIO.equals(form.getIdContatoPJA()))
                // pj.getTelefone().setIdContato(form.getIdContatoPJA());
                pj.getTelefone().setIdTipoTelefone(form.getIdTipoTelefonePJ());
                pj.getTelefone().setNrTelefone(form.getNrTelefonePJ());
                pj.getTelefone().setNmContato(form.getNmContatoPJ());

                if (getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray() != null && getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray().length == 1) {
                    pj.setDadosEnderecoArray(preparaEnderecoPrincipal(getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray(0).getDadosEnderecoArray()));
                }
            }
            if (form.getInSMSCli() != null) {
                PUP pup = prePagoVO.addNewPUP();
                // pup.setInProtocolo(form.getInSMSCli());
                pup.setInPromocoes(form.getInSMSCli());
                pup.setInProdutos(form.getInSMSCli());
                pup.setInParceiros(form.getInSMSCli());
            }

            // Se o Cliente não for o Usuario da Linha, preenche os dados referente ao Usuario.
            if (ConstantesCRM.SZERO.equals(form.getInUsuario())) {
                PF pf = prePagoVO.addNewPF();

                // if (!ConstantesCRM.SVAZIO.equals(form.getIdPessoaUsu())) pf.setIdPessoa(form.getIdPessoaUsu());
                pf.setNmPessoa(form.getNmPessoaUsu());
                pf.setIdSexo(form.getIdSexoUsu());

                pf.addNewDocumento();
                // if (!ConstantesCRM.SVAZIO.equals(form.getIdDocumentoUsuA()))
                // pf.getDocumentoArray(0).setIdDocumento(form.getIdDocumentoUsuA());
                pf.getDocumentoArray(0).setIdTipoDocumento(form.getIdTipoDocumentoUsuA());
                pf.getDocumentoArray(0).setNrDocumento(form.getNrDocumentoUsuA());

                if (!ConstantesCRM.SVAZIO.equals(form.getIdTipoDocumentoUsuB()) && !ConstantesCRM.SZERO.equals(form.getIdTipoDocumentoUsuB())) {
                    pf.addNewDocumento();
                    // if (!ConstantesCRM.SVAZIO.equals(form.getIdDocumentoUsuB()))
                    // pf.getDocumentoArray(1).setIdDocumento(form.getIdDocumentoUsuB());
                    pf.getDocumentoArray(1).setIdTipoDocumento(form.getIdTipoDocumentoUsuB());
                    pf.getDocumentoArray(1).setNrDocumento(form.getNrDocumentoUsuB());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getInDocumentoRGUsu())) {
                    int pos = Integer.parseInt(form.getInDocumentoRGUsu());
                    pf.getDocumentoArray(pos).setIdUFDocumento(form.getIdUFDocumentoUsu());
                    pf.getDocumentoArray(pos).setDsOrgaoEmissor(form.getDsOrgaoEmissorUsu());
                    pf.getDocumentoArray(pos).setDtExpedicao(form.getDtExpedicaoUsu());
                }
                pf.setDtNascimento(form.getDtNascimentoUsu());

                if (!ConstantesCRM.SVAZIO.equals(form.getIdTipoTelefoneUsuA()) && !ConstantesCRM.SZERO.equals(form.getIdTipoTelefoneUsuA())) {
                    pf.addNewTelefone();
                    // if (!ConstantesCRM.SVAZIO.equals(form.getIdContatoUsuA()))
                    // pf.getTelefoneArray(0).setIdContato(form.getIdContatoUsuA());
                    pf.getTelefoneArray(0).setIdTipoTelefone(form.getIdTipoTelefoneUsuA());
                    pf.getTelefoneArray(0).setNrTelefone(form.getNrTelefoneUsuA());
                    pf.getTelefoneArray(0).setNmContato(form.getNmContatoUsuA());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdTipoTelefoneUsuB()) && !ConstantesCRM.SZERO.equals(form.getIdTipoTelefoneUsuB())) {
                    pf.addNewTelefone();
                    // if (!ConstantesCRM.SVAZIO.equals(form.getIdContatoUsuC()))
                    // pf.getTelefoneArray(1).setIdContato(form.getIdContatoUsuC());
                    pf.getTelefoneArray(1).setIdTipoTelefone(form.getIdTipoTelefoneUsuB());
                    pf.getTelefoneArray(1).setNrTelefone(form.getNrTelefoneUsuB());
                    pf.getTelefoneArray(1).setNmContato(form.getNmContatoUsuB());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdTipoTelefoneUsuC()) && !ConstantesCRM.SZERO.equals(form.getIdTipoTelefoneUsuC())) {
                    pf.addNewTelefone();
                    // if (!ConstantesCRM.SVAZIO.equals(form.getIdContatoUsuC()))
                    // pf.getTelefoneArray(2).setIdContato(form.getIdContatoUsuC());
                    pf.getTelefoneArray(2).setIdTipoTelefone(form.getIdTipoTelefoneUsuC());
                    pf.getTelefoneArray(2).setNrTelefone(form.getNrTelefoneUsuC());
                    pf.getTelefoneArray(2).setNmContato(form.getNmContatoUsuC());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getNmEmailParticularUsu())) {
                    pf.setNmEmailParticular(form.getNmEmailParticularUsu());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getNmEmailComercialUsu())) {
                    pf.setNmEmailComercial(form.getNmEmailComercialUsu());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdEstadoCivilUsu()) && !ConstantesCRM.SZERO.equals(form.getIdEstadoCivilUsu())) {
                    pf.setIdEstadoCivil(form.getIdEstadoCivilUsu());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdEscolaridadeUsu()) && !ConstantesCRM.SZERO.equals(form.getIdEscolaridadeUsu())) {
                    pf.setIdEscolaridade(form.getIdEscolaridadeUsu());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getDsProfissaoUsu())) {
                    pf.setDsProfissao(form.getDsProfissaoUsu());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdNatOcupacaoUsu()) && !ConstantesCRM.SZERO.equals(form.getIdNatOcupacaoUsu())) {
                    pf.setIdNatOcupacao(form.getIdNatOcupacaoUsu());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getNrCPRUsu())) {
                    pf.setNrCPR(form.getNrCPRUsu());
                }

                if (ConstantesCRM.SONE.equals(form.getInUtilEndCliente())) {
                    if (PJ.equals(form.getInTipoPessoa())) {
                        pf.setDadosEnderecoArray(preparaEnderecoPrincipal(getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray(0).getDadosEnderecoArray()));
                    } else {
                        pf.setDadosEnderecoArray(preparaEnderecoPrincipal(getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).getDadosEnderecoArray()));
                    }
                } else {
                    if (getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray() != null) {
                        int pos = PJ.equals(form.getInTipoPessoa()) ? 0 : 1;
                        pf.setDadosEnderecoArray(preparaEnderecoPrincipal(getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(pos).getDadosEnderecoArray()));
                    }
                }
            }

            // Regra solicitada: Para linhas pesquisadas do tipo PÓS nunca enviar idPessoa
            if (ConstantesCRM.STRUE.equalsIgnoreCase(request.getParameter("clientePesquisado"))) {

                if ("POS".equalsIgnoreCase(request.getParameter("cdTipoLinha")) || "POSCHIP".equalsIgnoreCase(request.getParameter("cdTipoLinha")) || "CONT".equalsIgnoreCase(request.getParameter("cdTipoLinha"))
                        || "CONTCHIP".equalsIgnoreCase(request.getParameter("cdTipoLinha"))) {

                    if (PJ.equals(form.getInTipoPessoa())) {
                        if (prePagoVO.getPJArray(0).getIdPessoa() != null) {
                            prePagoVO.getPJArray(0).unsetIdPessoa();
                        }
                    } else if (PF.equals(form.getInTipoPessoa())) {
                        if (prePagoVO.getPFArray(0).getIdPessoa() != null) {
                            prePagoVO.getPFArray(0).unsetIdPessoa();
                        }
                    }
                } else {
                    if (PJ.equals(form.getInTipoPessoa())) {
                        prePagoVO.getPJArray(0).setIdPessoa(request.getParameter("idPessoa"));
                    } else if (PF.equals(form.getInTipoPessoa())) {
                        prePagoVO.getPFArray(0).setIdPessoa(request.getParameter("idPessoa"));
                    }
                }

                // Flag em banco que determina criação de novo usuário ao invés de atualizar existente
                ParametroVO apoioParametro = null;
                try {
                    apoioParametro = telaInicialFacade.getParametro(idUsuario, parametro_insincronismo);
                } catch (Exception e) {
                    apoioParametro = ParametroVO.Factory.newInstance();
                }

                if (ConstantesCRM.SZERO.equals(apoioParametro.getDsValorParametro())) {

                    if (PJ.equals(form.getInTipoPessoa())) {
                        if (prePagoVO.getPJArray(0).getIdPessoa() != null) {
                            prePagoVO.getPJArray(0).unsetIdPessoa();
                        }
                    } else if (PF.equals(form.getInTipoPessoa())) {
                        if (prePagoVO.getPFArray(0).getIdPessoa() != null) {
                            prePagoVO.getPFArray(0).unsetIdPessoa();
                        }
                    }
                }
            }

            try {
                PrePagoVO prePagoVOCopia = PrePagoVO.Factory.newInstance();
                prePagoVOCopia.set(prePagoVO.copy());
                removeEndByDefault(prePagoVO);
                // validaEnderecoPrincipal(form.getInTipoPessoa(), prePagoVO);

                limitaQtdeCaracteres(prePagoVO);
                prePagoFacade.setPrePagoVO(idUsuario, prePagoVO);

                finalizaCadastro(prePagoVOCopia, prePagoVOCopia.getNrLinha(), request);
                getPrePagoForm().setInSucesso("T");

            } catch (Exception e) {
                getPrePagoForm().setInSucesso("F");
                request.setAttribute("msg", "Erro no Cadastramento dos Dados.[" + e.getMessage() + "]");
            }

            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);

        } catch (Exception e) {
            FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
            formError.setTarget(ConstantesCRM.FRAMEAPP);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    /*
     * Regra solicitada pelo Cassio: Verificar todos os endereços do cliente PF ou PJ. Caso nenhum endereço seja o
     * endereço principal (inEndPrincipal=1), o primeiro item do array de endereços deve ser setado como principal.
     */
    @SuppressWarnings("unused")
    private void validaEnderecoPrincipal(String inTipoPessoa, PrePagoVO prePagoVO) {
        boolean endPrincipalEncontrado = false;
        if (PF.equals(inTipoPessoa)) {
            if (prePagoVO.getPFArray() != null && prePagoVO.getPFArray().length > 0) {
                for (int i = 0; i < prePagoVO.getPFArray()[0].getDadosEnderecoArray().length; i++) {
                    if (ConstantesCRM.SONE.equals(prePagoVO.getPFArray()[0].getDadosEnderecoArray()[i].getInEndPrincipal())) {
                        endPrincipalEncontrado = true;
                        break;
                    }
                }
            }
            if (!endPrincipalEncontrado) {
                if (prePagoVO.getPFArray()[0].getDadosEnderecoArray() != null && prePagoVO.getPFArray()[0].getDadosEnderecoArray()[0] != null) {
                    prePagoVO.getPFArray()[0].getDadosEnderecoArray()[0].setInEndPrincipal(ConstantesCRM.SONE);
                }
            }
        } else if (PJ.equals(inTipoPessoa)) {
            if (prePagoVO.getPJArray() != null && prePagoVO.getPJArray().length > 0) {
                for (int i = 0; i < prePagoVO.getPJArray()[0].getDadosEnderecoArray().length; i++) {
                    if (ConstantesCRM.SONE.equals(prePagoVO.getPJArray()[0].getDadosEnderecoArray()[i].getInEndPrincipal())) {
                        endPrincipalEncontrado = true;
                        break;
                    }
                }
            }
            if (!endPrincipalEncontrado) {
                if (prePagoVO.getPJArray()[0].getDadosEnderecoArray() != null && prePagoVO.getPJArray()[0].getDadosEnderecoArray()[0] != null) {
                    prePagoVO.getPJArray()[0].getDadosEnderecoArray()[0].setInEndPrincipal(ConstantesCRM.SONE);
                }
            }
        }
    }

    private DadosEndereco[] preparaEnderecoPrincipal(DadosEndereco[] dadosEndereco) {
        boolean hasEndPrincipal = false;
        if (dadosEndereco != null && dadosEndereco.length > 0) {
            for (int i = 0; i < dadosEndereco.length; i++) {
                if (ConstantesCRM.SONE.equals(dadosEndereco[i].getInEndPrincipal())) {
                    hasEndPrincipal = true;
                }
                if (dadosEndereco[i].getIdTipoEndereco() == null || ConstantesCRM.SVAZIO.equals(dadosEndereco[i].getIdTipoEndereco())) {
                    dadosEndereco[i].setIdTipoEndereco(ConstantesCRM.SONE);
                }
                if (dadosEndereco[i].getNmTipoLogradouro() == null || ConstantesCRM.SVAZIO.equals(dadosEndereco[i].getNmTipoLogradouro())) {
                    dadosEndereco[i].setNmTipoLogradouro("RUA");
                }
                if (dadosEndereco[i].getNmLogradouro() == null || ConstantesCRM.SVAZIO.equals(dadosEndereco[i].getNmLogradouro())) {
                    dadosEndereco[i].setNmLogradouro("null");
                }
                if (dadosEndereco[i].getNrLogradouro() == null || ConstantesCRM.SVAZIO.equals(dadosEndereco[i].getNrLogradouro())) {
                    dadosEndereco[i].setNrLogradouro("00");
                }
                if (dadosEndereco[i].getNrCEP() == null || ConstantesCRM.SVAZIO.equals(dadosEndereco[i].getNrCEP())) {
                    dadosEndereco[i].setNrCEP("44444444");
                }
                if (dadosEndereco[i].getNmBairro() == null || ConstantesCRM.SVAZIO.equals(dadosEndereco[i].getNmBairro())) {
                    dadosEndereco[i].setNmBairro("null");
                }
                if (dadosEndereco[i].getNmMunicipio() == null || ConstantesCRM.SVAZIO.equals(dadosEndereco[i].getNmMunicipio())) {
                    dadosEndereco[i].setNmMunicipio("null");
                }
                if (dadosEndereco[i].getIdUF() == null || ConstantesCRM.SVAZIO.equals(dadosEndereco[i].getIdUF())) {
                    dadosEndereco[i].setIdUF(ConstantesCRM.SZERO);
                }
            }
            if (!hasEndPrincipal) {
                dadosEndereco[0].setInEndPrincipal(ConstantesCRM.SONE);
            }
        }
        return dadosEndereco;
    }

    private void finalizaCadastro(PrePagoVO prePagoVO, String nrLinha, HttpServletRequest request) {
        String nrProtocolo = ConstantesCRM.SVAZIO;
        try {
            String user = ConstantesCRM.getCurrentUser(request.getSession());
            ParametrosVO pTemp = telaInicialFacade.getParametrosVO(ConstantesCRM.getCurrentUser(request.getSession()), nrLinha, ConstantesCRM.STWO);
            request.getSession().setAttribute(ConstantesCRM.SPARAMETROS, pTemp);
            ParametroVO apioParam = telaInicialFacade.getParametro(idUsuario, parametro_inc_prepago);

            nrProtocolo = (request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) != null) ? (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) : ConstantesCRM.SVAZIO;

            AlterProtocoloTO alterProtocoloTO = AlterProtocoloTO.Factory.newInstance();
            alterProtocoloTO.setIdSistemaOrigem(ConstantesCRM.SSEVEN);
            alterProtocoloTO.setIdTipoAberturaProtocolo(ConstantesCRM.STHREE);
            alterProtocoloTO.setCdAreaRegistro(prePagoVO.getNrLinha().substring(0, 2));
            alterProtocoloTO.setNrTelefone(prePagoVO.getNrLinha().substring(2));
            alterProtocoloTO.setIdLinhaTelefonica(prePagoVO.getIdLinhaTelefonica());
            alterProtocoloTO.setIdPessoaDePara(pTemp.getIdClienteDePara());

            AlterProtocoloOutTO out = protocoloFacade.setAlteracaoProtocolo(user, alterProtocoloTO, "alterar", nrProtocolo, 0, 0);
            if (out != null) {
                if (out.getNrProtocolo() != null && !ConstantesCRM.SVAZIO.equals(out.getNrProtocolo())) {
                    nrProtocolo = out.getNrProtocolo();
                }
            }
            request.setAttribute(ConstantesCRM.NRPROTOCOLO, nrProtocolo);
            pTemp.setNrProtocolo(nrProtocolo);

            ClienteUtils.registrarPalitagem(user, pTemp, prePagoVO.getIdLinhaTelefonica(), prePagoVO.getNrLinha(), apioParam.getDsValorParametro(), ConstantesCRM.SVAZIO);

            trataEnderecoDefault(prePagoVO, request);

        } catch (Exception e) {
        }
    }

    /* ************************
     * Alteração de Clientes Pre Pago ************************
     */

    /**
     * @jpf:action
     * @jpf:forward name="success" path="index.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward loadAlteracao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            getPrePagoForm().setIsNrLinhaValid("T");
            getPrePagoForm().setTpOperacao("A");
            ListasVO listasVO = prePagoFacade.getListasVO(idUsuario);
            setListasVO(listasVO);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);

        } catch (Exception e) {
            FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
            formError.setTarget(ConstantesCRM.FRAMEAPP);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    /*
     * Método criado para limitação de quantidade de caracteres nos campos estipulada na OS 1174 - Validação Cadastral,
     * de janeiro de 2010.
     */
    private void limitaQtdeCaracteres(PrePagoVO prePagoVO) {
        // Tratamento de caracteres
        int maxCaracteres = 60;
        if (prePagoVO.getPFArray() != null) {
            for (int i = 0; i < prePagoVO.getPFArray().length; i++) {
                PF pf = prePagoVO.getPFArray()[i];
                if (pf.getNmPessoa().length() > maxCaracteres) {
                    pf.setNmPessoa(pf.getNmPessoa().substring(0, maxCaracteres));
                }
                if (pf.getDadosEnderecoArray() != null) {
                    for (int j = 0; j < pf.getDadosEnderecoArray().length; j++) {
                        DadosEndereco de = pf.getDadosEnderecoArray()[j];
                        if (de.getNmLogradouro().length() > maxCaracteres) {
                            de.setNmLogradouro(de.getNmLogradouro().substring(0, maxCaracteres));
                        }
                    }
                }
            }
        }
        if (prePagoVO.getPJArray() != null) {
            for (int i = 0; i < prePagoVO.getPJArray().length; i++) {
                PJ pj = prePagoVO.getPJArray()[i];
                if (pj.getNmRazaoSocial().length() > maxCaracteres) {
                    pj.setNmRazaoSocial(pj.getNmRazaoSocial().substring(0, maxCaracteres));
                }
                if (pj.getDadosEnderecoArray() != null) {
                    for (int j = 0; j < pj.getDadosEnderecoArray().length; j++) {
                        DadosEndereco de = pj.getDadosEnderecoArray()[j];
                        if (de.getNmLogradouro().length() > maxCaracteres) {
                            de.setNmLogradouro(de.getNmLogradouro().substring(0, maxCaracteres));
                        }
                    }
                }
            }
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="index.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward loadDadosAlteracao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StringBuffer xmlDados = new StringBuffer();
        String user = ConstantesCRM.getCurrentUser(request.getSession());
        try {
            String idLinha = getPrePagoForm().getIdLinha();
            String inTipoPessoa = getPrePagoForm().getInTipoPessoa();
            String idPessoa = getPrePagoForm().getIdPessoa();

            PrePagoVO prePagoVO = prePagoFacade.getPrePagoVOById(user, inTipoPessoa, idLinha, idPessoa);

            if (ConstantesCRM.SONE.equals(prePagoVO.getCodError())) {
                AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
                ajaxErrorHandlerVO.setExceptionMessage(prePagoVO.getMsgError());
                ajaxErrorHandlerVO.setFriendlyMessage("Nenhum Dado do Cliente Encontrado para a linha informada!");
                xmlDados = new StringBuffer();
                xmlDados.append(ClienteUtils.getCleanXMLFromXSD(ajaxErrorHandlerVO, null));
            } else {
                if (prePagoVO.getPJArray() != null && prePagoVO.getPJArray().length == 1 && prePagoVO.getPJArray(0).getNrCNPJ() != null) {
                    getPrePagoForm().setIdPessoaPJ(idPessoa);
                    if (prePagoVO.getPFArray() != null && prePagoVO.getPFArray().length > 0) {
                        getPrePagoForm().setIdPessoaUsu(prePagoVO.getPFArray(0).getIdPessoa());
                    }
                } else {
                    getPrePagoForm().setIdPessoa(idPessoa);
                    getPrePagoForm().setIdPessoaCli(idPessoa);
                    if (prePagoVO.getPFArray() != null && prePagoVO.getPFArray().length > 1) {
                        getPrePagoForm().setIdPessoaUsu(prePagoVO.getPFArray(1).getIdPessoa());
                    }
                }
                trataEnderecosPrePagoVO(prePagoVO, inTipoPessoa, "C");
                getPrePagoForm().setNmPessoaCli((prePagoVO.getPJArray() == null || prePagoVO.getPJArray().length == 0) ? prePagoVO.getPFArray(0).getNmPessoa() : ConstantesCRM.SVAZIO);
                getPrePagoForm().setDtNascimentoCli((prePagoVO.getPJArray() == null || prePagoVO.getPJArray().length == 0) ? prePagoVO.getPFArray(0).getDtNascimento() : ConstantesCRM.SVAZIO);
                getPrePagoForm().setInUsuario((prePagoVO.getPJArray() == null || prePagoVO.getPJArray().length == 0) ? prePagoVO.getInUsuario() : ConstantesCRM.SVAZIO);
                getPrePagoForm().setIdSexoCli((prePagoVO.getPJArray() == null || prePagoVO.getPJArray().length == 0) ? prePagoVO.getPFArray(0).getIdSexo() : ConstantesCRM.SVAZIO);
                xmlDados.append(ClienteUtils.getCleanXMLFromXSD(prePagoVO, null));

            }
        } catch (Exception e) {

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
     * @jpf:forward name="success" path="index.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward limparDadosEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StringBuffer xmlDados = new StringBuffer();
        try {
            if (!"A".equals(getPrePagoForm().getTpOperacao())) {
                if (getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray() != null && getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray().length > 0) {
                    int nPF = getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray().length;
                    for (int i = 0; i < nPF; i++) {
                        int nEnd = getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(i).getDadosEnderecoArray().length;
                        for (int j = 0; j < nEnd; j++) {
                            getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(i).removeDadosEndereco(0);
                        }
                    }
                }
                if (getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray() != null && getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray().length == 1
                        && getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray(0).getDadosEnderecoArray() != null) {
                    int nEnd = getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray(0).getDadosEnderecoArray().length;
                    for (int i = 0; i < nEnd; i++) {
                        getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray(0).removeDadosEndereco(0);
                    }
                }
            }
            xmlDados.append("<PrePagoVO><result>OK<result></PrePagoVO>");
        } catch (Exception e) {
            AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
            ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
            ajaxErrorHandlerVO.setFriendlyMessage("Nenhum Endereco do Encontrado!");
            xmlDados = new StringBuffer();
            xmlDados.append(ClienteUtils.getCleanXMLFromXSD(ajaxErrorHandlerVO, null));
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
     * @jpf:forward name="success" path="index.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward execAlteracao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            PrePagoForm form = (PrePagoForm) formParam;
            form.setTpOperacao("A"); // T - TITULARIDADE A - ALTERAR C - CADASTRAR

            idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
            PrePagoVO prePagoVO = PrePagoVO.Factory.newInstance();
            prePagoVO.setIdLinhaTelefonica(getPrePagoForm().getIdLinha());
            prePagoVO.setNrLinha(getPrePagoForm().getNrLinha());
            prePagoVO.setTpOperacao("A");

            if (PF.equals(form.getInTipoPessoa())) {
                prePagoVO.setInTipoPessoa(ConstantesCRM.SONE);
                PF pf = prePagoVO.addNewPF();
                if (!ConstantesCRM.SVAZIO.equals(form.getIdPessoaCli())) {
                    pf.setIdPessoa(form.getIdPessoaCli());
                }

                prePagoVO.setInUsuario(form.getInUsuario());
                pf.setNmPessoa(form.getNmPessoaCli());
                pf.setIdSexo(form.getIdSexoCli());

                pf.addNewDocumento();
                if (!ConstantesCRM.SVAZIO.equals(form.getIdDocumentoCliA())) {
                    pf.getDocumentoArray(0).setIdDocumento(form.getIdDocumentoCliA());
                }
                pf.getDocumentoArray(0).setIdTipoDocumento(form.getIdTipoDocumentoCliA());
                pf.getDocumentoArray(0).setNrDocumento(form.getNrDocumentoCliA());

                if (!ConstantesCRM.SZERO.equals(form.getIdTipoDocumentoCliB()) && !ConstantesCRM.SVAZIO.equals(form.getIdTipoDocumentoCliB())) {
                    pf.addNewDocumento();
                    if (!ConstantesCRM.SVAZIO.equals(form.getIdDocumentoCliB())) {
                        pf.getDocumentoArray(1).setIdDocumento(form.getIdDocumentoCliB());
                    }
                    pf.getDocumentoArray(1).setIdTipoDocumento(form.getIdTipoDocumentoCliB());
                    pf.getDocumentoArray(1).setNrDocumento(form.getNrDocumentoCliB());
                }

                if (!ConstantesCRM.SVAZIO.equals(form.getInDocumentoRGCli())) {
                    int pos = Integer.parseInt(form.getInDocumentoRGCli());
                    pf.getDocumentoArray(pos).setIdUFDocumento(form.getIdUFDocumentoCli());
                    pf.getDocumentoArray(pos).setDsOrgaoEmissor(form.getDsOrgaoEmissorCli());
                    pf.getDocumentoArray(pos).setDtExpedicao(form.getDtExpedicaoCli());
                }
                pf.setDtNascimento(form.getDtNascimentoCli());

                if (!ConstantesCRM.SVAZIO.equals(form.getIdTipoTelefoneCliA()) && !ConstantesCRM.SZERO.equals(form.getIdTipoTelefoneCliA())) {
                    pf.addNewTelefone();
                    if (!ConstantesCRM.SVAZIO.equals(form.getIdContatoCliA())) {
                        pf.getTelefoneArray(0).setIdContato(form.getIdContatoCliA());
                    }
                    pf.getTelefoneArray(0).setIdTipoTelefone(form.getIdTipoTelefoneCliA());
                    pf.getTelefoneArray(0).setNrTelefone(form.getNrTelefoneCliA());
                    pf.getTelefoneArray(0).setNmContato(form.getNmContatoCliA());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdTipoTelefoneCliB()) && !ConstantesCRM.SZERO.equals(form.getIdTipoTelefoneCliB())) {
                    pf.addNewTelefone();
                    if (!ConstantesCRM.SVAZIO.equals(form.getIdContatoCliB())) {
                        pf.getTelefoneArray(1).setIdContato(form.getIdContatoCliB());
                    }
                    pf.getTelefoneArray(1).setIdTipoTelefone(form.getIdTipoTelefoneCliB());
                    pf.getTelefoneArray(1).setNrTelefone(form.getNrTelefoneCliB());
                    pf.getTelefoneArray(1).setNmContato(form.getNmContatoCliB());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdTipoTelefoneCliC()) && !ConstantesCRM.SZERO.equals(form.getIdTipoTelefoneCliC())) {
                    pf.addNewTelefone();
                    if (!ConstantesCRM.SVAZIO.equals(form.getIdContatoCliC())) {
                        pf.getTelefoneArray(2).setIdContato(form.getIdContatoCliC());
                    }
                    pf.getTelefoneArray(2).setIdTipoTelefone(form.getIdTipoTelefoneCliC());
                    pf.getTelefoneArray(2).setNrTelefone(form.getNrTelefoneCliC());
                    pf.getTelefoneArray(2).setNmContato(form.getNmContatoCliC());
                }

                if (!ConstantesCRM.SVAZIO.equals(form.getNmEmailParticularCli())) {
                    pf.setNmEmailParticular(form.getNmEmailParticularCli());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getNmEmailComercialCli())) {
                    pf.setNmEmailComercial(form.getNmEmailComercialCli());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getDsProfissaoCli())) {
                    pf.setDsProfissao(form.getDsProfissaoCli());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getNrCPRCli())) {
                    pf.setNrCPR(form.getNrCPRCli());
                }
                if (!ConstantesCRM.SZERO.equals(form.getIdEstadoCivilCli()) && !ConstantesCRM.SVAZIO.equals(form.getIdEstadoCivilCli())) {
                    pf.setIdEstadoCivil(form.getIdEstadoCivilCli());
                }
                if (!ConstantesCRM.SZERO.equals(form.getIdEscolaridadeCli()) && !ConstantesCRM.SVAZIO.equals(form.getIdEscolaridadeCli())) {
                    pf.setIdEscolaridade(form.getIdEscolaridadeCli());
                }
                if (!ConstantesCRM.SZERO.equals(form.getIdNatOcupacaoCli()) && !ConstantesCRM.SVAZIO.equals(form.getIdNatOcupacaoCli())) {
                    pf.setIdNatOcupacao(form.getIdNatOcupacaoCli());
                }

                // Como as alterações de Endereço serão realizadas para cada solicitação, não há necessidade de enviar
                // os dados
                // Pois precisam passar por algumas validações importantes
                if (getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray() != null) {
                    pf.setDadosEnderecoArray(preparaEnderecoPrincipal(getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).getDadosEnderecoArray()));
                }

            } else if (PJ.equals(form.getInTipoPessoa())) {
                prePagoVO.setInTipoPessoa(ConstantesCRM.STWO);
                prePagoVO.setInUsuario(form.getInUsuario());

                PJ pj = prePagoVO.addNewPJ();
                if (!ConstantesCRM.SVAZIO.equals(form.getIdPessoaPJ())) {
                    pj.setIdPessoa(form.getIdPessoaPJ());
                }
                pj.setNrCNPJ(form.getNrCNPJ());
                pj.setNmFantasia(form.getNmFantasia());
                pj.setNmRazaoSocial(form.getNmRazaoSocial());
                pj.setNrCNAE(form.getNrCNAE());
                pj.setIdTipoInscricao(form.getIdTipoInscricao());
                pj.setNrInscricao(form.getNrInscricao());
                pj.setNrCCM(form.getNrCCM());
                pj.setIdClassEmpresa(form.getIdClassEmpresa());
                pj.setIdClassTributaria(form.getIdClassTributaria());
                pj.setDtFundacao(form.getDtFundacao());

                pj.addNewTelefone();
                pj.getTelefone().setIdContato(form.getIdContatoPJA());
                pj.getTelefone().setIdTipoTelefone(form.getIdTipoTelefonePJ());
                pj.getTelefone().setNrTelefone(form.getNrTelefonePJ());
                pj.getTelefone().setNmContato(form.getNmContatoPJ());

                if (getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray() != null && getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray().length == 1) {
                    pj.setDadosEnderecoArray(preparaEnderecoPrincipal(getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray(0).getDadosEnderecoArray()));
                }

            }

            if (ConstantesCRM.SZERO.equals(form.getInUsuario())) {
                PF pf = prePagoVO.addNewPF();

                if (!ConstantesCRM.SVAZIO.equals(form.getIdPessoaUsu())) {
                    pf.setIdPessoa(form.getIdPessoaUsu());
                }
                pf.setNmPessoa(form.getNmPessoaUsu());
                pf.setIdSexo(form.getIdSexoUsu());

                pf.addNewDocumento();
                if (!ConstantesCRM.SVAZIO.equals(form.getIdDocumentoUsuA())) {
                    pf.getDocumentoArray(0).setIdDocumento(form.getIdDocumentoUsuA());
                }
                pf.getDocumentoArray(0).setIdTipoDocumento(form.getIdTipoDocumentoUsuA());
                pf.getDocumentoArray(0).setNrDocumento(form.getNrDocumentoUsuA());

                if (!ConstantesCRM.SZERO.equals(form.getIdTipoDocumentoUsuB()) && !ConstantesCRM.SVAZIO.equals(form.getIdTipoDocumentoUsuB())) {
                    pf.addNewDocumento();
                    if (!ConstantesCRM.SVAZIO.equals(form.getIdDocumentoUsuB())) {
                        pf.getDocumentoArray(1).setIdDocumento(form.getIdDocumentoUsuB());
                    }
                    pf.getDocumentoArray(1).setIdTipoDocumento(form.getIdTipoDocumentoUsuB());
                    pf.getDocumentoArray(1).setNrDocumento(form.getNrDocumentoUsuB());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getInDocumentoRGUsu())) {
                    int pos = Integer.parseInt(form.getInDocumentoRGUsu());
                    pf.getDocumentoArray(pos).setIdUFDocumento(form.getIdUFDocumentoUsu());
                    pf.getDocumentoArray(pos).setDsOrgaoEmissor(form.getDsOrgaoEmissorUsu());
                    pf.getDocumentoArray(pos).setDtExpedicao(form.getDtExpedicaoUsu());
                }

                pf.setDtNascimento(form.getDtNascimentoUsu());

                if (!ConstantesCRM.SVAZIO.equals(form.getIdTipoTelefoneUsuA()) && !ConstantesCRM.SZERO.equals(form.getIdTipoTelefoneUsuA())) {
                    pf.addNewTelefone();
                    if (!ConstantesCRM.SVAZIO.equals(form.getIdContatoUsuA())) {
                        pf.getTelefoneArray(0).setIdContato(form.getIdContatoUsuA());
                    }
                    pf.getTelefoneArray(0).setIdTipoTelefone(form.getIdTipoTelefoneUsuA());
                    pf.getTelefoneArray(0).setNrTelefone(form.getNrTelefoneUsuA());
                    pf.getTelefoneArray(0).setNmContato(form.getNmContatoUsuA());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdTipoTelefoneUsuB()) && !ConstantesCRM.SZERO.equals(form.getIdTipoTelefoneUsuB())) {
                    pf.addNewTelefone();
                    if (!ConstantesCRM.SVAZIO.equals(form.getIdContatoUsuB())) {
                        pf.getTelefoneArray(1).setIdContato(form.getIdContatoUsuB());
                    }
                    pf.getTelefoneArray(1).setIdTipoTelefone(form.getIdTipoTelefoneUsuB());
                    pf.getTelefoneArray(1).setNrTelefone(form.getNrTelefoneUsuB());
                    pf.getTelefoneArray(1).setNmContato(form.getNmContatoUsuB());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdTipoTelefoneUsuC()) && !ConstantesCRM.SZERO.equals(form.getIdTipoTelefoneUsuC())) {
                    pf.addNewTelefone();
                    if (!ConstantesCRM.SVAZIO.equals(form.getIdContatoUsuC())) {
                        pf.getTelefoneArray(2).setIdContato(form.getIdContatoUsuC());
                    }
                    pf.getTelefoneArray(2).setIdTipoTelefone(form.getIdTipoTelefoneUsuC());
                    pf.getTelefoneArray(2).setNrTelefone(form.getNrTelefoneUsuC());
                    pf.getTelefoneArray(2).setNmContato(form.getNmContatoUsuC());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getNmEmailParticularUsu())) {
                    pf.setNmEmailParticular(form.getNmEmailParticularUsu());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getNmEmailComercialUsu())) {
                    pf.setNmEmailComercial(form.getNmEmailComercialUsu());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdEstadoCivilUsu()) && !ConstantesCRM.SZERO.equals(form.getIdEstadoCivilUsu())) {
                    pf.setIdEstadoCivil(form.getIdEstadoCivilUsu());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdEscolaridadeUsu()) && !ConstantesCRM.SZERO.equals(form.getIdEscolaridadeUsu())) {
                    pf.setIdEscolaridade(form.getIdEscolaridadeUsu());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getDsProfissaoUsu())) {
                    pf.setDsProfissao(form.getDsProfissaoUsu());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdNatOcupacaoUsu()) && !ConstantesCRM.SZERO.equals(form.getIdNatOcupacaoUsu())) {
                    pf.setIdNatOcupacao(form.getIdNatOcupacaoUsu());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getNrCPRUsu())) {
                    pf.setNrCPR(form.getNrCPRUsu());
                }

                if (form.getIdPessoaUsu() == null || ConstantesCRM.SVAZIO.equals(form.getIdPessoaUsu())) {
                    if (ConstantesCRM.SONE.equals(form.getInUtilEndCliente())) {
                        if (PJ.equals(form.getInTipoPessoa())) {
                            if (getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray() != null && getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray().length == 1) {
                                pf.setDadosEnderecoArray(preparaEnderecoPrincipal(getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray(0).getDadosEnderecoArray()));
                            }
                        } else {
                            if (getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray() != null) {
                                pf.setDadosEnderecoArray(preparaEnderecoPrincipal(getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).getDadosEnderecoArray()));
                            }
                        }
                    } else {
                        if (getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray() != null) {
                            int pos = PJ.equals(form.getInTipoPessoa()) ? 0 : 1;
                            pf.setDadosEnderecoArray(preparaEnderecoPrincipal(getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(pos).getDadosEnderecoArray()));
                        }
                    }
                }
            }

            // Regra solicitada: Para linhas pesquisadas do tipo PÓS nunca enviar idPessoa
            if (ConstantesCRM.STRUE.equalsIgnoreCase(request.getParameter("clientePesquisado"))) {

                if ("POS".equalsIgnoreCase(request.getParameter("cdTipoLinha")) || "POSCHIP".equalsIgnoreCase(request.getParameter("cdTipoLinha")) || "CONT".equalsIgnoreCase(request.getParameter("cdTipoLinha"))
                        || "CONTCHIP".equalsIgnoreCase(request.getParameter("cdTipoLinha"))) {

                    if (PJ.equals(form.getInTipoPessoa())) {
                        if (prePagoVO.getPJArray(0).getIdPessoa() != null) {
                            prePagoVO.getPJArray(0).unsetIdPessoa();
                        }
                    } else if (PF.equals(form.getInTipoPessoa())) {
                        if (prePagoVO.getPFArray(0).getIdPessoa() != null) {
                            prePagoVO.getPFArray(0).unsetIdPessoa();
                        }
                    }
                } else {
                    if (PJ.equals(form.getInTipoPessoa())) {
                        prePagoVO.getPJArray(0).setIdPessoa(request.getParameter("idPessoa"));
                    } else if (PF.equals(form.getInTipoPessoa())) {
                        prePagoVO.getPFArray(0).setIdPessoa(request.getParameter("idPessoa"));
                    }
                }

                // Flag em banco que determina criação de novo usuário ao invés de atualizar existente
                ParametroVO apoioParametro = null;
                try {
                    apoioParametro = telaInicialFacade.getParametro(idUsuario, parametro_insincronismo);
                } catch (Exception e) {
                    apoioParametro = ParametroVO.Factory.newInstance();
                }

                if (ConstantesCRM.SZERO.equals(apoioParametro.getDsValorParametro())) {

                    if (PJ.equals(form.getInTipoPessoa())) {
                        if (prePagoVO.getPJArray(0).getIdPessoa() != null) {
                            prePagoVO.getPJArray(0).unsetIdPessoa();
                        }
                    } else if (PF.equals(form.getInTipoPessoa())) {
                        if (prePagoVO.getPFArray(0).getIdPessoa() != null) {
                            prePagoVO.getPFArray(0).unsetIdPessoa();
                        }
                    }

                }

            }

            // validaEnderecoPrincipal(form.getInTipoPessoa(), prePagoVO);
            limitaQtdeCaracteres(prePagoVO);
            SETPPTMAVO pptmaVO = prePagoFacade.setPrePagoVO(idUsuario, prePagoVO);

            String dadosAlterados = getDadosAlterados(form);
            if (dadosAlterados.length() > 1) {
                ParametrosVO parametroVO = getParametrosVO(request);
                parametroVO.setIdClienteDePara(pptmaVO.getIdPessoaDepara());
                ParametroVO apioParam = telaInicialFacade.getParametro(idUsuario, this.parametro_alt_dados);
                String result = ClienteUtils.registrarPalitagem(idUsuario, parametroVO, getPrePagoForm().getIdLinha(), getPrePagoForm().getNrLinha(), apioParam.getDsValorParametro(), dadosAlterados);
                request.setAttribute("nrProtocolo", result);
            } else {
                request.setAttribute("nrProtocolo", ConstantesCRM.SVAZIO);
            }
            getPrePagoForm().setInSucesso("T");
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);
        } catch (Exception e) {
            FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
            formError.setTarget(ConstantesCRM.FRAMEAPP);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    private String getDadosAlterados(PrePagoForm form) {
        StringBuffer dados = new StringBuffer(ConstantesCRM.SVAZIO);
        if (!ConstantesCRM.SVAZIO.equals(getPrePagoForm().getNmPessoaCli()) && !form.getNmPessoaCli().equals(getPrePagoForm().getNmPessoaCli())) {
            dados.append("Nome Pessoa: ").append(getPrePagoForm().getNmPessoaCli()).append(" ");
        }
        if (!ConstantesCRM.SVAZIO.equals(getPrePagoForm().getIdSexoCli()) && !form.getIdSexoCli().equals(getPrePagoForm().getIdSexoCli())) {
            dados.append("Sexo: ").append(getTextListById(getPrePagoForm().getIdSexoCli(), "LS")).append(" ");
        }
        if (!ConstantesCRM.SVAZIO.equals(getPrePagoForm().getDtNascimentoCli()) && !form.getDtNascimentoCli().equals(getPrePagoForm().getDtNascimentoCli())) {
            dados.append("Data Nascimento: ").append(getPrePagoForm().getDtNascimentoCli()).append(" ");
        }
        if (!ConstantesCRM.SVAZIO.equals(getPrePagoForm().getInUsuario()) && !form.getInUsuario().equals(getPrePagoForm().getInUsuario())) {
            dados.append("Cliente da Linha: ").append(ConstantesCRM.SONE.equals(getPrePagoForm().getInUsuario()) ? "SIM" : "NÃO");
        }
        return dados.toString().trim();
    }

    /* ************************
     * Troca de Titularidade de Clientes Pre Pago ************************
     */

    /**
     * @jpf:action
     * @jpf:forward name="success" path="index.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward loadTitularidade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            getPrePagoForm().setIsNrLinhaValid("T");
            getPrePagoForm().setTpOperacao("T");
            ListasVO listasVO = prePagoFacade.getListasVO(idUsuario);
            setListasVO(listasVO);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);

        } catch (Exception e) {
            FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
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
    public ActionForward loadDadosTitularidade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StringBuffer xmlDados = new StringBuffer();
        String user = ConstantesCRM.getCurrentUser(request.getSession());
        try {
            String idLinha = getPrePagoForm().getIdLinha();
            String inTipoPessoa = getPrePagoForm().getInTipoPessoa();
            String idPessoa = getPrePagoForm().getIdPessoa();

            PrePagoVO prePagoVO = prePagoFacade.getPrePagoVOById(user, inTipoPessoa, idLinha, idPessoa);
            if (ConstantesCRM.SONE.equals(prePagoVO.getCodError())) {
                AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
                ajaxErrorHandlerVO.setExceptionMessage(prePagoVO.getMsgError());
                ajaxErrorHandlerVO.setFriendlyMessage("Nenhum Dado do Cliente Encontrado para a linha informada!");
                xmlDados = new StringBuffer();
                xmlDados.append(ClienteUtils.getCleanXMLFromXSD(ajaxErrorHandlerVO, null));
            } else {

                if (prePagoVO.getPJArray() != null && prePagoVO.getPJArray().length == 1 && prePagoVO.getPJArray(0).getNrCNPJ() != null) {
                    getPrePagoForm().setIdPessoa(idPessoa);
                    getPrePagoForm().setIdPessoaPJ(idPessoa);
                    getPrePagoForm().setNmCliente(prePagoVO.getPJArray(0).getNmRazaoSocial());
                    getPrePagoForm().setTpDocumento("CNPJ");
                    getPrePagoForm().setNrDocumento(prePagoVO.getPJArray(0).getNrCNPJ());

                    if (prePagoVO.getPFArray() != null && prePagoVO.getPFArray().length > 0) {
                        getPrePagoForm().setIdPessoaUsu(prePagoVO.getPFArray(0).getIdPessoa());
                    }

                } else {
                    getPrePagoForm().setIdPessoa(idPessoa);
                    getPrePagoForm().setIdPessoaCli(idPessoa);
                    try {
                        getPrePagoForm().setNmCliente(prePagoVO.getPFArray(0).getNmPessoa());
                        getPrePagoForm().setNrDocumento(prePagoVO.getPFArray(0).getDocumentoArray(0).getNrDocumento());
                        getPrePagoForm().setTpDocumento(getTextListById(prePagoVO.getPFArray(0).getDocumentoArray(0).getIdTipoDocumento(), "LTD"));
                    } catch (Exception e) {
                    }
                    if (prePagoVO.getPFArray() != null && prePagoVO.getPFArray().length > 1) {
                        getPrePagoForm().setIdPessoaUsu(prePagoVO.getPFArray(1).getIdPessoa());
                    }
                }
                trataEnderecosPrePagoVO(prePagoVO, inTipoPessoa, "C");
                xmlDados.append(ClienteUtils.getCleanXMLFromXSD(prePagoVO, null));

            }
        } catch (Exception e) {

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
     * @jpf:forward name="success" path="index.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward execTitularidade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            PrePagoForm form = (PrePagoForm) formParam;
            form.setTpOperacao("T"); // T - TITULARIDADE A - ALTERAR C - CADASTRAR

            idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
            PrePagoVO prePagoVO = PrePagoVO.Factory.newInstance();
            prePagoVO.setIdLinhaTelefonica(getPrePagoForm().getIdLinha());
            prePagoVO.setNrLinha(getPrePagoForm().getNrLinha());
            prePagoVO.setTpOperacao("T");

            if (PF.equals(form.getInTipoPessoa())) {
                prePagoVO.setInTipoPessoa(ConstantesCRM.SONE);
                PF pf = prePagoVO.addNewPF();
                // if (!ConstantesCRM.SVAZIO.equals(form.getIdPessoaCli())) pf.setIdPessoa(form.getIdPessoaCli());

                prePagoVO.setInUsuario(form.getInUsuario());
                pf.setNmPessoa(form.getNmPessoaCli());
                pf.setIdSexo(form.getIdSexoCli());

                pf.addNewDocumento();
                // if (!ConstantesCRM.SVAZIO.equals(form.getIdDocumentoCliA()))
                // pf.getDocumentoArray(0).setIdDocumento(form.getIdDocumentoCliA());
                pf.getDocumentoArray(0).setIdTipoDocumento(form.getIdTipoDocumentoCliA());
                pf.getDocumentoArray(0).setNrDocumento(form.getNrDocumentoCliA());

                if (!ConstantesCRM.SZERO.equals(form.getIdTipoDocumentoCliB()) && !ConstantesCRM.SVAZIO.equals(form.getIdTipoDocumentoCliB())) {
                    pf.addNewDocumento();
                    // if (!ConstantesCRM.SVAZIO.equals(form.getIdDocumentoCliB()))
                    // pf.getDocumentoArray(1).setIdDocumento(form.getIdDocumentoCliB());
                    pf.getDocumentoArray(1).setIdTipoDocumento(form.getIdTipoDocumentoCliB());
                    pf.getDocumentoArray(1).setNrDocumento(form.getNrDocumentoCliB());
                }

                if (!ConstantesCRM.SVAZIO.equals(form.getInDocumentoRGCli())) {
                    int pos = Integer.parseInt(form.getInDocumentoRGCli());
                    pf.getDocumentoArray(pos).setIdUFDocumento(form.getIdUFDocumentoCli());
                    pf.getDocumentoArray(pos).setDsOrgaoEmissor(form.getDsOrgaoEmissorCli());
                    pf.getDocumentoArray(pos).setDtExpedicao(form.getDtExpedicaoCli());
                }
                pf.setDtNascimento(form.getDtNascimentoCli());

                if (!ConstantesCRM.SVAZIO.equals(form.getIdTipoTelefoneCliA()) && !ConstantesCRM.SZERO.equals(form.getIdTipoTelefoneCliA())) {
                    pf.addNewTelefone();
                    pf.getTelefoneArray(0).setIdTipoTelefone(form.getIdTipoTelefoneCliA());
                    pf.getTelefoneArray(0).setNrTelefone(form.getNrTelefoneCliA());
                    pf.getTelefoneArray(0).setNmContato(form.getNmContatoCliA());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdTipoTelefoneCliB()) && !ConstantesCRM.SZERO.equals(form.getIdTipoTelefoneCliB())) {
                    pf.addNewTelefone();
                    pf.getTelefoneArray(1).setIdTipoTelefone(form.getIdTipoTelefoneCliB());
                    pf.getTelefoneArray(1).setNrTelefone(form.getNrTelefoneCliB());
                    pf.getTelefoneArray(1).setNmContato(form.getNmContatoCliB());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdTipoTelefoneCliC()) && !ConstantesCRM.SZERO.equals(form.getIdTipoTelefoneCliC())) {
                    pf.addNewTelefone();
                    pf.getTelefoneArray(2).setIdTipoTelefone(form.getIdTipoTelefoneCliC());
                    pf.getTelefoneArray(2).setNrTelefone(form.getNrTelefoneCliC());
                    pf.getTelefoneArray(2).setNmContato(form.getNmContatoCliC());
                }

                if (!ConstantesCRM.SVAZIO.equals(form.getNmEmailParticularCli())) {
                    pf.setNmEmailParticular(form.getNmEmailParticularCli());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getNmEmailComercialCli())) {
                    pf.setNmEmailComercial(form.getNmEmailComercialCli());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdEstadoCivilCli()) && !ConstantesCRM.SZERO.equals(form.getIdEstadoCivilCli())) {
                    pf.setIdEstadoCivil(form.getIdEstadoCivilCli());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdEscolaridadeCli()) && !ConstantesCRM.SZERO.equals(form.getIdEscolaridadeCli())) {
                    pf.setIdEscolaridade(form.getIdEscolaridadeCli());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getDsProfissaoCli())) {
                    pf.setDsProfissao(form.getDsProfissaoCli());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdNatOcupacaoCli()) && !ConstantesCRM.SZERO.equals(form.getIdNatOcupacaoCli())) {
                    pf.setIdNatOcupacao(form.getIdNatOcupacaoCli());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getNrCPRCli())) {
                    pf.setNrCPR(form.getNrCPRCli());
                }

                if (getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray() != null) {
                    pf.setDadosEnderecoArray(preparaEnderecoPrincipal(getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).getDadosEnderecoArray()));
                }

            } else if (PJ.equals(form.getInTipoPessoa())) {
                prePagoVO.setInTipoPessoa(ConstantesCRM.STWO);
                prePagoVO.setInUsuario(form.getInUsuario());

                PJ pj = prePagoVO.addNewPJ();
                // if (!ConstantesCRM.SVAZIO.equals(form.getIdPessoaPJ())) pj.setIdPessoa(form.getIdPessoaPJ());
                pj.setNrCNPJ(form.getNrCNPJ());
                pj.setNmFantasia(form.getNmFantasia());
                pj.setNmRazaoSocial(form.getNmRazaoSocial());
                pj.setNrCNAE(form.getNrCNAE());
                pj.setIdTipoInscricao(form.getIdTipoInscricao());
                pj.setNrInscricao(form.getNrInscricao());
                pj.setNrCCM(form.getNrCCM());
                pj.setIdClassEmpresa(form.getIdClassEmpresa());
                pj.setIdClassTributaria(form.getIdClassTributaria());
                pj.setDtFundacao(form.getDtFundacao());

                pj.addNewTelefone();
                pj.getTelefone().setIdTipoTelefone(form.getIdTipoTelefonePJ());
                pj.getTelefone().setNrTelefone(form.getNrTelefonePJ());
                pj.getTelefone().setNmContato(form.getNmContatoPJ());

                if (getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray() != null && getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray().length == 1) {
                    pj.setDadosEnderecoArray(preparaEnderecoPrincipal(getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray(0).getDadosEnderecoArray()));
                }
            }
            if (form.getInSMSCli() != null) {
                PUP pup = prePagoVO.addNewPUP();
                // pup.setInProtocolo(form.getInSMSCli());
                pup.setInPromocoes(form.getInSMSCli());
                pup.setInProdutos(form.getInSMSCli());
                pup.setInParceiros(form.getInSMSCli());
            }

            if (ConstantesCRM.SZERO.equals(form.getInUsuario())) {
                PF pf = prePagoVO.addNewPF();

                // if (!ConstantesCRM.SVAZIO.equals(form.getIdPessoaUsu())) pf.setIdPessoa(form.getIdPessoaUsu());
                pf.setNmPessoa(form.getNmPessoaUsu());
                pf.setIdSexo(form.getIdSexoUsu());

                pf.addNewDocumento();
                // if (!ConstantesCRM.SVAZIO.equals(form.getIdDocumentoUsuA()))
                // pf.getDocumentoArray(0).setIdDocumento(form.getIdDocumentoUsuA());
                pf.getDocumentoArray(0).setIdTipoDocumento(form.getIdTipoDocumentoUsuA());
                pf.getDocumentoArray(0).setNrDocumento(form.getNrDocumentoUsuA());

                if (!ConstantesCRM.SVAZIO.equals(form.getIdTipoDocumentoUsuB()) && !ConstantesCRM.SZERO.equals(form.getIdTipoDocumentoUsuB())) {
                    pf.addNewDocumento();
                    // if (!ConstantesCRM.SVAZIO.equals(form.getIdDocumentoUsuB()))
                    // pf.getDocumentoArray(1).setIdDocumento(form.getIdDocumentoUsuB());
                    pf.getDocumentoArray(1).setIdTipoDocumento(form.getIdTipoDocumentoUsuB());
                    pf.getDocumentoArray(1).setNrDocumento(form.getNrDocumentoUsuB());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getInDocumentoRGUsu())) {
                    int pos = Integer.parseInt(form.getInDocumentoRGUsu());
                    pf.getDocumentoArray(pos).setIdUFDocumento(form.getIdUFDocumentoUsu());
                    pf.getDocumentoArray(pos).setDsOrgaoEmissor(form.getDsOrgaoEmissorUsu());
                    pf.getDocumentoArray(pos).setDtExpedicao(form.getDtExpedicaoUsu());
                }
                pf.setDtNascimento(form.getDtNascimentoUsu());

                if (!ConstantesCRM.SVAZIO.equals(form.getIdTipoTelefoneUsuA()) && !ConstantesCRM.SZERO.equals(form.getIdTipoTelefoneUsuA())) {
                    pf.addNewTelefone();
                    pf.getTelefoneArray(0).setIdTipoTelefone(form.getIdTipoTelefoneUsuA());
                    pf.getTelefoneArray(0).setNrTelefone(form.getNrTelefoneUsuA());
                    pf.getTelefoneArray(0).setNmContato(form.getNmContatoUsuA());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdTipoTelefoneUsuB()) && !ConstantesCRM.SZERO.equals(form.getIdTipoTelefoneUsuB())) {
                    pf.addNewTelefone();
                    pf.getTelefoneArray(1).setIdTipoTelefone(form.getIdTipoTelefoneUsuB());
                    pf.getTelefoneArray(1).setNrTelefone(form.getNrTelefoneUsuB());
                    pf.getTelefoneArray(1).setNmContato(form.getNmContatoUsuB());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdTipoTelefoneUsuC()) && !ConstantesCRM.SZERO.equals(form.getIdTipoTelefoneUsuC())) {
                    pf.addNewTelefone();
                    pf.getTelefoneArray(2).setIdTipoTelefone(form.getIdTipoTelefoneUsuC());
                    pf.getTelefoneArray(2).setNrTelefone(form.getNrTelefoneUsuC());
                    pf.getTelefoneArray(2).setNmContato(form.getNmContatoUsuC());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getNmEmailParticularUsu())) {
                    pf.setNmEmailParticular(form.getNmEmailParticularUsu());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getNmEmailComercialUsu())) {
                    pf.setNmEmailComercial(form.getNmEmailComercialUsu());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdEstadoCivilUsu()) && !ConstantesCRM.SZERO.equals(form.getIdEstadoCivilUsu())) {
                    pf.setIdEstadoCivil(form.getIdEstadoCivilUsu());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdEscolaridadeUsu()) && !ConstantesCRM.SZERO.equals(form.getIdEscolaridadeUsu())) {
                    pf.setIdEscolaridade(form.getIdEscolaridadeUsu());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getDsProfissaoUsu())) {
                    pf.setDsProfissao(form.getDsProfissaoUsu());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getIdNatOcupacaoUsu()) && !ConstantesCRM.SZERO.equals(form.getIdNatOcupacaoUsu())) {
                    pf.setIdNatOcupacao(form.getIdNatOcupacaoUsu());
                }
                if (!ConstantesCRM.SVAZIO.equals(form.getNrCPRUsu())) {
                    pf.setNrCPR(form.getNrCPRUsu());
                }

                if (ConstantesCRM.SONE.equals(form.getInUtilEndCliente())) {
                    if (PJ.equals(form.getInTipoPessoa())) {
                        pf.setDadosEnderecoArray(preparaEnderecoPrincipal(getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray(0).getDadosEnderecoArray()));
                    } else {
                        pf.setDadosEnderecoArray(preparaEnderecoPrincipal(getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).getDadosEnderecoArray()));
                    }
                } else {
                    if (getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray() != null) {
                        int pos = PJ.equals(form.getInTipoPessoa()) ? 0 : 1;
                        pf.setDadosEnderecoArray(preparaEnderecoPrincipal(getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(pos).getDadosEnderecoArray()));
                    }
                }
            }

            // Regra solicitada: Para linhas pesquisadas do tipo PÓS nunca enviar idPessoa
            if (ConstantesCRM.STRUE.equalsIgnoreCase(request.getParameter("clientePesquisado"))) {

                if ("POS".equalsIgnoreCase(request.getParameter("cdTipoLinha")) || "POSCHIP".equalsIgnoreCase(request.getParameter("cdTipoLinha")) || "CONT".equalsIgnoreCase(request.getParameter("cdTipoLinha"))
                        || "CONTCHIP".equalsIgnoreCase(request.getParameter("cdTipoLinha"))) {

                    if (PJ.equals(form.getInTipoPessoa())) {
                        if (prePagoVO.getPJArray(0).getIdPessoa() != null) {
                            prePagoVO.getPJArray(0).unsetIdPessoa();
                        }
                    } else if (PF.equals(form.getInTipoPessoa())) {
                        if (prePagoVO.getPFArray(0).getIdPessoa() != null) {
                            prePagoVO.getPFArray(0).unsetIdPessoa();
                        }
                    }
                } else {
                    if (PJ.equals(form.getInTipoPessoa())) {
                        prePagoVO.getPJArray(0).setIdPessoa(request.getParameter("idPessoa"));
                    } else if (PF.equals(form.getInTipoPessoa())) {
                        prePagoVO.getPFArray(0).setIdPessoa(request.getParameter("idPessoa"));
                    }
                }

                // Flag em banco que determina criação de novo usuário ao invés de atualizar existente
                ParametroVO apoioParametro = null;
                try {
                    apoioParametro = telaInicialFacade.getParametro(idUsuario, parametro_insincronismo);
                } catch (Exception e) {
                    apoioParametro = ParametroVO.Factory.newInstance();
                }

                if (ConstantesCRM.SZERO.equals(apoioParametro.getDsValorParametro())) {

                    if (PJ.equals(form.getInTipoPessoa())) {
                        if (prePagoVO.getPJArray(0).getIdPessoa() != null) {
                            prePagoVO.getPJArray(0).unsetIdPessoa();
                        }
                    } else if (PF.equals(form.getInTipoPessoa())) {
                        if (prePagoVO.getPFArray(0).getIdPessoa() != null) {
                            prePagoVO.getPFArray(0).unsetIdPessoa();
                        }
                    }

                }

            }

            // validaEnderecoPrincipal(form.getInTipoPessoa(), prePagoVO);
            limitaQtdeCaracteres(prePagoVO);
            SETPPTMAVO pptmaVO = prePagoFacade.setPrePagoVO(idUsuario, prePagoVO);

            ParametrosVO parametroVO = getParametrosVO(request);
            parametroVO.setIdClienteDePara(pptmaVO.getIdPessoaDepara());
            ParametroVO apioParam = telaInicialFacade.getParametro(idUsuario, this.parametro_troca_tit);

            String result = ClienteUtils.registrarPalitagem(idUsuario, parametroVO, getPrePagoForm().getIdLinha(), getPrePagoForm().getNrLinha(), apioParam.getDsValorParametro(), getDadosTrocaTit());

            request.setAttribute("nrProtocolo", result);
            getPrePagoForm().setInSucesso("T");
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);

        } catch (Exception e) {
            FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
            formError.setTarget(ConstantesCRM.FRAMEAPP);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    private String getDadosTrocaTit() {
        StringBuffer dados = new StringBuffer();
        dados.append("Nome:").append(getPrePagoForm().getNmCliente()).append(" ").append("Tipo Documento:").append(getPrePagoForm().getTpDocumento()).append(" ").append("Número do Documento:").append(getPrePagoForm().getNrDocumento());
        return dados.toString();
    }

    /* ************************
     * Manipulacao de Endereços de Clientes Pre Pago ************************
     */

    private void trataEnderecoDefault(PrePagoVO prePagoVO, HttpServletRequest request) {
        String user = ConstantesCRM.getCurrentUser(request.getSession());
        ParametroVO apoioParam = null;
        try {
            apoioParam = telaInicialFacade.getParametro(user, parametro_end_default);
        } catch (Exception e) {
            apoioParam = ParametroVO.Factory.newInstance();
        }
        if (ConstantesCRM.SONE.equals(prePagoVO.getInTipoPessoa())) {
            for (int i = 0; i < prePagoVO.getPFArray().length; i++) {
                for (int j = 0; j < prePagoVO.getPFArray(i).getDadosEnderecoArray().length; j++) {
                    DadosEndereco dadosEndereco = prePagoVO.getPFArray(i).getDadosEnderecoArray(j);
                    setDadosEndDefault(user, dadosEndereco, apoioParam.getDsValorParametro(), prePagoVO.getIdLinhaTelefonica(), prePagoVO.getNrLinha(), request);
                }
            }
        } else if (ConstantesCRM.STWO.equals(prePagoVO.getInTipoPessoa())) {
            if (prePagoVO.getPFArray() != null && prePagoVO.getPFArray().length == 1) {
                if (prePagoVO.getPFArray(0).getDadosEnderecoArray() != null) {
                    for (int j = 0; j < prePagoVO.getPFArray(0).getDadosEnderecoArray().length; j++) {
                        DadosEndereco dadosEndereco = prePagoVO.getPFArray(0).getDadosEnderecoArray(j);
                        setDadosEndDefault(user, dadosEndereco, apoioParam.getDsValorParametro(), prePagoVO.getIdLinhaTelefonica(), prePagoVO.getNrLinha(), request);
                    }
                }
            }
            if (prePagoVO.getPJArray() != null && prePagoVO.getPJArray().length == 1 && prePagoVO.getPJArray(0).getNrCNPJ() != null) {
                if (prePagoVO.getPJArray(0).getDadosEnderecoArray() != null) {
                    for (int j = 0; j < prePagoVO.getPJArray(0).getDadosEnderecoArray().length; j++) {
                        DadosEndereco dadosEndereco = prePagoVO.getPJArray(0).getDadosEnderecoArray(j);
                        setDadosEndDefault(user, dadosEndereco, apoioParam.getDsValorParametro(), prePagoVO.getIdLinhaTelefonica(), prePagoVO.getNrLinha(), request);
                    }
                }
            }
        }
    }

    private void setDadosEndDefault(String user, DadosEndereco dadosEndereco, String paramPath, String idLinha, String nrLinha, HttpServletRequest request) {
        DadosEndereco dadosEndCopia = DadosEndereco.Factory.newInstance();
        if (ConstantesCRM.SONE.equals(dadosEndereco.getInEndDefault())) {
            dadosEndCopia.set(dadosEndereco.copy());
            setEndDefault(dadosEndereco);
            try {
                String dsObservacao = getEndereco2String(dadosEndCopia);
                registraPalitagemEncaminhamento(idLinha, nrLinha, paramPath, dsObservacao, request);
                dadosEndereco.set(dadosEndCopia.copy());

            } catch (Exception e) {
                dadosEndereco.set(dadosEndCopia.copy());
            }
        }
    }

    private void removeEndByDefault(PrePagoVO prePagoVO) {
        if (ConstantesCRM.SONE.equals(prePagoVO.getInTipoPessoa())) {
            for (int i = 0; i < prePagoVO.getPFArray().length; i++) {
                for (int j = 0; j < prePagoVO.getPFArray(i).getDadosEnderecoArray().length; j++) {
                    DadosEndereco dadosEndereco = prePagoVO.getPFArray(i).getDadosEnderecoArray(j);
                    if (ConstantesCRM.SONE.equals(dadosEndereco.getInEndDefault())) {
                        setEndDefault(dadosEndereco);
                    }
                }
            }
        } else if (ConstantesCRM.STWO.equals(prePagoVO.getInTipoPessoa())) {
            if (prePagoVO.getPFArray() != null && prePagoVO.getPFArray().length == 1) {
                if (prePagoVO.getPFArray(0).getDadosEnderecoArray() != null) {
                    for (int j = 0; j < prePagoVO.getPFArray(0).getDadosEnderecoArray().length; j++) {
                        DadosEndereco dadosEndereco = prePagoVO.getPFArray(0).getDadosEnderecoArray(j);
                        if (ConstantesCRM.SONE.equals(dadosEndereco.getInEndDefault())) {
                            setEndDefault(dadosEndereco);
                        }
                    }
                }
            }
            if (prePagoVO.getPJArray() != null && prePagoVO.getPJArray().length == 1 && prePagoVO.getPJArray(0).getNrCNPJ() != null) {
                if (prePagoVO.getPJArray(0).getDadosEnderecoArray() != null) {
                    for (int j = 0; j < prePagoVO.getPJArray(0).getDadosEnderecoArray().length; j++) {
                        DadosEndereco dadosEndereco = prePagoVO.getPJArray(0).getDadosEnderecoArray(j);
                        if (ConstantesCRM.SONE.equals(dadosEndereco.getInEndDefault())) {
                            setEndDefault(dadosEndereco);
                        }
                    }
                }
            }
        }
    }

    private void setEndDefault(DadosEndereco dadosEndereco) {
        dadosEndereco.setIdTipoEndereco(ConstantesCRM.SONE);
        dadosEndereco.setNrCEP("44444444");
        dadosEndereco.setNmTipoLogradouro("null");
        dadosEndereco.setNmTitLogradouro("null");
        dadosEndereco.setNmLogradouro("não localizado");
        dadosEndereco.setNrLogradouro("00");
        dadosEndereco.setNmComplemento(ConstantesCRM.SVAZIO);
        dadosEndereco.setNmBairro("não localizado");
        dadosEndereco.setNmMunicipio("não localizado");
        dadosEndereco.setIdUF(ConstantesCRM.SZERO);
    }

    private void preparaPrePagoVOEndereco() {
        if (getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray() == null) {
            getPrePagoEnderecoForm().getPrePagoVOEndereco().addNewPF();
            getPrePagoEnderecoForm().getPrePagoVOEndereco().addNewPF();
        } else if (getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray().length == 1) {
            getPrePagoEnderecoForm().getPrePagoVOEndereco().addNewPF();
        }
        if (getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray() == null || getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray().length == 0) {
            getPrePagoEnderecoForm().getPrePagoVOEndereco().addNewPJ();
        }
    }

    private void trataEnderecoPrincipal(String inTipoPessoa, String inTipoRelaciona, PrePagoEnderecoForm endereco) {
        DadosEndereco[] dadosEnderecos = null;
        if (PF.equals(inTipoPessoa) && "C".equals(inTipoRelaciona)) {
            dadosEnderecos = getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).getDadosEnderecoArray();
        } else if (PF.equals(inTipoPessoa) && "U".equals(inTipoRelaciona)) {
            dadosEnderecos = getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(1).getDadosEnderecoArray();
        } else if (PJ.equals(inTipoPessoa) && "C".equals(inTipoRelaciona)) {
            dadosEnderecos = getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray(0).getDadosEnderecoArray();
        } else if (PJ.equals(inTipoPessoa) && "U".equals(inTipoRelaciona)) {
            dadosEnderecos = getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).getDadosEnderecoArray();
        }
        if (dadosEnderecos != null && dadosEnderecos.length > 0) {
            if (ConstantesCRM.SONE.equals(endereco.getInEndPrincipal())) {
                for (int i = 0; i < dadosEnderecos.length; i++) {
                    if (ConstantesCRM.SONE.equals(dadosEnderecos[i].getInEndPrincipal())) {
                        dadosEnderecos[i].setInEndPrincipal(ConstantesCRM.SZERO);
                    }
                }
            } else {
                boolean hasEndPrin = false;
                for (int i = 0; i < dadosEnderecos.length; i++) {
                    if (ConstantesCRM.SONE.equals(dadosEnderecos[i].getInEndPrincipal())) {
                        hasEndPrin = true;
                    }
                }
                if (!hasEndPrin) {
                    if (dadosEnderecos.length > 0 && dadosEnderecos[0].getIdTipoEndereco() != null) {
                        dadosEnderecos[0].setInEndPrincipal(ConstantesCRM.SONE);
                    } else {
                        endereco.setInEndPrincipal(ConstantesCRM.SONE);
                    }
                }
            }
        }
    }

    /**
     * Inclui um endereco do Cliente/Usuario
     * 
     * @jpf:action
     * @jpf:forward name="success" path="tabListaEnderecos.do"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward incluirEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrePagoEnderecoForm form = (PrePagoEnderecoForm) formParam;
        String inTipoPessoa = request.getParameter("inTipoPessoa");
        String inTipoRelaciona = request.getParameter("inTipoRelaciona");
        idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
        DadosEndereco dadosEndereco = null;
        boolean isAdd = false;
        preparaPrePagoVOEndereco();
        try {
            if (PF.equals(inTipoPessoa)) {
                if ("C".equals(inTipoRelaciona)) {
                    dadosEndereco = getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).addNewDadosEndereco();
                    isAdd = true;
                } else if ("U".equals(inTipoRelaciona)) {
                    dadosEndereco = getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(1).addNewDadosEndereco();
                    form.setInEndPrincipal(ConstantesCRM.SZERO);
                    isAdd = true;
                }
            } else if (PJ.equals(inTipoPessoa)) {
                if ("C".equals(inTipoRelaciona)) {
                    dadosEndereco = getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray(0).addNewDadosEndereco();
                    isAdd = true;
                } else if ("U".equals(inTipoRelaciona)) {
                    dadosEndereco = getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).addNewDadosEndereco();
                    form.setInEndPrincipal(ConstantesCRM.SZERO);
                    isAdd = true;
                }
            }
            if (isAdd) {
                trataEnderecoPrincipal(inTipoPessoa, inTipoRelaciona, form);
                if (!ConstantesCRM.SVAZIO.equals(form.getIdEndereco())) {
                    dadosEndereco.setIdEndereco(form.getIdEndereco());
                }
                dadosEndereco.setInEndDefault(form.getInEndDefault());
                dadosEndereco.setIdTipoEndereco(form.getIdTipoEndereco());
                dadosEndereco.setNrCEP(form.getNrCEP());
                dadosEndereco.setInEndPrincipal(ConstantesCRM.SONE.equals(form.getInEndPrincipal()) ? ("U".equals(inTipoRelaciona) ? ConstantesCRM.SZERO : ConstantesCRM.SONE) : ConstantesCRM.SZERO);
                dadosEndereco.setNmTipoLogradouro(form.getTpLogradouro());
                if (!ConstantesCRM.SVAZIO.equals(form.getNmTitLogradouro())) {
                    dadosEndereco.setNmTitLogradouro(form.getNmTitLogradouro());
                }
                dadosEndereco.setNmLogradouro(form.getNmLogradouro());
                dadosEndereco.setNrLogradouro(form.getNrLogradouro());
                if (!ConstantesCRM.SVAZIO.equals(form.getNmComplemento())) {
                    dadosEndereco.setNmComplemento(form.getNmComplemento());
                }
                dadosEndereco.setNmBairro(form.getNmBairro());
                dadosEndereco.setNmMunicipio(form.getNmMunicipio());
                dadosEndereco.setIdUF(form.getIdEndUF());
                dadosEndereco.setInCnl(form.getInCnl());
                dadosEndereco.setCodLogradouro(form.getCodLogradouro());
                dadosEndereco.setInCodigoIBGE(form.getInCodigoIBGE());
            }
            if (!("U".equals(inTipoRelaciona) && ConstantesCRM.SVAZIO.equals(getPrePagoForm().getIdPessoaUsu())) && "A".equals(getPrePagoForm().getTpOperacao())) {
                processaManterEndereco("I", form, dadosEndereco, inTipoRelaciona, 0, request);
            }
            if ("A".equals(getPrePagoForm().getTpOperacao())) {
                PrePagoVO prePagoVO = prePagoFacade.getPrePagoVOById(idUsuario, inTipoPessoa, getPrePagoForm().getIdLinha(), getPrePagoForm().getIdPessoa());
                trataEnderecosPrePagoVO(prePagoVO, inTipoPessoa, inTipoRelaciona);
            }
        } catch (Exception e) {
            StringBuffer xmlDados = new StringBuffer();
            AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
            ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
            ajaxErrorHandlerVO.setFriendlyMessage(e.getMessage().indexOf("]") > -1 ? e.getMessage().substring(e.getMessage().indexOf("]") + 1) : e.getMessage());
            xmlDados = new StringBuffer();
            xmlDados.append(ClienteUtils.getCleanXMLFromXSD(ajaxErrorHandlerVO, null));
            response.setContentType(ConstantesCRM.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(xmlDados.toString().getBytes(ConstantesCRM.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * Retorna os dados de um endereco do Cliente/Usuario para alteracao
     * 
     * @jpf:action
     * @jpf:forward name="success" path="tabListaEnderecos.do"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward loadDadosAlteraEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StringBuffer xmlDados = new StringBuffer();
        try {
            DadosEndereco dadosEndereco = null;
            String tpCliente = request.getParameter("inTipoPessoa"); // PF ou PJ
            String tpVinculo = request.getParameter("inTipoRelaciona"); // Cliente ou Usuario
            String nrPosicao = request.getParameter("nrPosicao"); // Indice do elemento
            if (nrPosicao != null && !ConstantesCRM.SVAZIO.equals(nrPosicao)) {
                int nPos = Integer.parseInt(nrPosicao);
                if (PF.equals(tpCliente)) {
                    if ("C".equals(tpVinculo)) {
                        dadosEndereco = getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).getDadosEnderecoArray(nPos);
                    } else if ("U".equals(tpVinculo)) {
                        dadosEndereco = getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(1).getDadosEnderecoArray(nPos);
                    }
                } else if (PJ.equals(tpCliente)) {
                    if ("C".equals(tpVinculo)) {
                        dadosEndereco = getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray(0).getDadosEnderecoArray(nPos);
                    } else if ("U".equals(tpVinculo)) {
                        dadosEndereco = getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).getDadosEnderecoArray(nPos);
                    }
                }
                xmlDados.append(ClienteUtils.getCleanXMLFromXSD(dadosEndereco, null));

            }
        } catch (Exception e) {
            AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
            ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
            ajaxErrorHandlerVO.setFriendlyMessage(ConstantesCRM.SVAZIO);
            xmlDados = new StringBuffer();
            xmlDados.append(ClienteUtils.getCleanXMLFromXSD(ajaxErrorHandlerVO, null));

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
     * Altera um dos Enderecos do Cliente/Usuario
     * 
     * @jpf:action
     * @jpf:forward name="success" path="tabListaEnderecos.do"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward alterarEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        PrePagoEnderecoForm form = (PrePagoEnderecoForm) formParam;
        String inTipoPessoa = request.getParameter("inTipoPessoa");
        String inTipoRelaciona = request.getParameter("inTipoRelaciona");
        String nrPosAltEnd = request.getParameter("nrPosAltEnd");
        StringBuffer xmlDados = new StringBuffer();

        idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
        DadosEndereco dadosEndereco = DadosEndereco.Factory.newInstance();

        try {
            preparaPrePagoVOEndereco();

            if (nrPosAltEnd != null && !ConstantesCRM.SVAZIO.equals(nrPosAltEnd)) {
                int nrPos = Integer.parseInt(nrPosAltEnd);
                trataEnderecoPrincipal(inTipoPessoa, inTipoRelaciona, form);

                if (!ConstantesCRM.SVAZIO.equals(form.getIdEndereco())) {
                    dadosEndereco.setIdEndereco(form.getIdEndereco());
                }
                dadosEndereco.setIdTipoEndereco(form.getIdTipoEndereco());
                dadosEndereco.setInEndDefault(form.getInEndDefault());
                dadosEndereco.setNrCEP(form.getNrCEP());
                dadosEndereco.setInEndPrincipal(ConstantesCRM.SONE.equals(form.getInEndPrincipal()) ? ("U".equals(inTipoRelaciona) ? ConstantesCRM.SZERO : ConstantesCRM.SONE) : ConstantesCRM.SZERO);
                dadosEndereco.setNmTipoLogradouro(form.getTpLogradouro());
                if (!ConstantesCRM.SVAZIO.equals(form.getNmTitLogradouro())) {
                    dadosEndereco.setNmTitLogradouro(form.getNmTitLogradouro());
                }
                dadosEndereco.setNmLogradouro(form.getNmLogradouro());
                dadosEndereco.setNrLogradouro(form.getNrLogradouro());
                if (!ConstantesCRM.SVAZIO.equals(form.getNmComplemento())) {
                    dadosEndereco.setNmComplemento(form.getNmComplemento());
                }
                dadosEndereco.setNmBairro(form.getNmBairro());
                dadosEndereco.setNmMunicipio(form.getNmMunicipio());
                dadosEndereco.setIdUF(form.getIdEndUF());

                if (!("U".equals(inTipoRelaciona) && ConstantesCRM.SVAZIO.equals(getPrePagoForm().getIdPessoaUsu())) && "A".equals(getPrePagoForm().getTpOperacao())) {
                    processaManterEndereco("A", form, dadosEndereco, inTipoRelaciona, nrPos, request);
                }

                if ("A".equals(getPrePagoForm().getTpOperacao())) {
                    PrePagoVO prePagoVO = prePagoFacade.getPrePagoVOById(idUsuario, inTipoPessoa, getPrePagoForm().getIdLinha(), getPrePagoForm().getIdPessoa());
                    trataEnderecosPrePagoVO(prePagoVO, inTipoPessoa, inTipoRelaciona);
                }

                if (PF.equals(inTipoPessoa)) {
                    if ("C".equals(inTipoRelaciona)) {
                        getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).setDadosEnderecoArray(nrPos, dadosEndereco);
                    } else if ("U".equals(inTipoRelaciona)) {
                        getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(1).setDadosEnderecoArray(nrPos, dadosEndereco);
                    }
                } else if (PJ.equals(inTipoPessoa)) {
                    if ("C".equals(inTipoRelaciona)) {
                        getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray(0).setDadosEnderecoArray(nrPos, dadosEndereco);
                    } else if ("U".equals(inTipoRelaciona)) {
                        getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).setDadosEnderecoArray(nrPos, dadosEndereco);
                    }
                }

            } else {
                xmlDados = new StringBuffer();
                AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
                ajaxErrorHandlerVO.setExceptionMessage(ConstantesCRM.SVAZIO);
                ajaxErrorHandlerVO.setFriendlyMessage("Não foi possivel recuperar a posição do Endereço para alteração");
                xmlDados.append(ClienteUtils.getCleanXMLFromXSD(ajaxErrorHandlerVO, null));
            }

        } catch (Exception e) {
            xmlDados = new StringBuffer();
            AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
            ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
            ajaxErrorHandlerVO.setFriendlyMessage(e.getMessage().indexOf("]") > -1 ? e.getMessage().substring(e.getMessage().indexOf("]") + 1) : e.getMessage());
            xmlDados.append(ClienteUtils.getCleanXMLFromXSD(ajaxErrorHandlerVO, null));

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
     * Retorna um VO EnderecoVO para um dado Nó de DadosEndereco, a ser utilizado no serviço da Tela Inicial, Lupa
     * Cliente.
     */
    private EnderecoVO getEnderecosVO(DadosEndereco dadosEndereco) {
        EnderecoVO enderecoVO = EnderecoVO.Factory.newInstance();
        enderecoVO.setIdEndereco(dadosEndereco.getIdEndereco());
        enderecoVO.setInEnderecoPrincipal(dadosEndereco.getInEndPrincipal());
        enderecoVO.setNmTipoLogradouro(dadosEndereco.getNmTipoLogradouro());
        enderecoVO.setNmTituloLogradouro(dadosEndereco.getNmTitLogradouro());
        enderecoVO.setNmLogradouro(dadosEndereco.getNmLogradouro());
        enderecoVO.setNrEndereco(dadosEndereco.getNrLogradouro());
        enderecoVO.setDsEnderecoComplemento(dadosEndereco.getNmComplemento());
        enderecoVO.setNmBairro(dadosEndereco.getNmBairro());
        enderecoVO.setNmMunicipio(dadosEndereco.getNmMunicipio());
        enderecoVO.setNrCEP(dadosEndereco.getNrCEP());
        enderecoVO.addNewTipoEnderecoVO().setIdTipoEndereco(dadosEndereco.getIdTipoEndereco());
        enderecoVO.addNewUFVO().setIdUF(dadosEndereco.getIdUF());
        enderecoVO.addNewPaisVO().setIdPais(ConstantesCRM.SONE);
        enderecoVO.setInCnl(dadosEndereco.getInCnl());
        enderecoVO.setCodLogradouro(dadosEndereco.getCodLogradouro());
        enderecoVO.setInCodigoIBGE(dadosEndereco.getInCodigoIBGE());
        return enderecoVO;
    }

    /**
     * Exclui um dos Enderecos do Cliente/Usuario
     * 
     * @jpf:action
     * @jpf:forward name="success" path="tabListaEnderecos.do"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward excluirEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrePagoEnderecoForm form = (PrePagoEnderecoForm) formParam;
        String inTipoPessoa = request.getParameter("inTipoPessoa");
        String inTipoRelaciona = request.getParameter("inTipoRelaciona");
        String nrPosicao = request.getParameter("nrPosicao");
        DadosEndereco dadosEndereco = DadosEndereco.Factory.newInstance();
        preparaPrePagoVOEndereco();
        if (nrPosicao != null && !ConstantesCRM.SVAZIO.equals(nrPosicao)) {
            try {
                int nrPos = Integer.parseInt(nrPosicao);
                if (PF.equals(inTipoPessoa)) {
                    if ("C".equals(inTipoRelaciona)) {
                        dadosEndereco.set(getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).getDadosEnderecoArray(nrPos).copy());
                    } else if ("U".equals(inTipoRelaciona)) {
                        dadosEndereco.set(getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(1).getDadosEnderecoArray(nrPos).copy());
                    }
                } else if (PJ.equals(inTipoPessoa)) {
                    if ("C".equals(inTipoRelaciona)) {
                        dadosEndereco.set(getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray(0).getDadosEnderecoArray(nrPos).copy());
                    } else if ("U".equals(inTipoRelaciona)) {
                        dadosEndereco.set(getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).getDadosEnderecoArray(nrPos).copy());
                    }
                }
                processaManterEndereco("E", form, dadosEndereco, inTipoRelaciona, nrPos, request);
                if (PF.equals(inTipoPessoa)) {
                    if ("C".equals(inTipoRelaciona)) {
                        getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).removeDadosEndereco(nrPos);
                    } else if ("U".equals(inTipoRelaciona)) {
                        getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(1).removeDadosEndereco(nrPos);
                    }
                } else if (PJ.equals(inTipoPessoa)) {
                    if ("C".equals(inTipoRelaciona)) {
                        getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray(0).removeDadosEndereco(nrPos);
                    } else if ("U".equals(inTipoRelaciona)) {
                        getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).removeDadosEndereco(nrPos);
                    }
                }
            } catch (Exception e) {
                StringBuffer xmlDados = new StringBuffer();
                AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
                ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
                ajaxErrorHandlerVO.setFriendlyMessage(e.getMessage().indexOf("]") > -1 ? e.getMessage().substring(e.getMessage().indexOf("]") + 1) : e.getMessage());
                xmlDados = new StringBuffer();
                xmlDados.append(ClienteUtils.getCleanXMLFromXSD(ajaxErrorHandlerVO, null));
                response.setContentType(ConstantesCRM.SContentType);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
                bufferedOutputStream.write(xmlDados.toString().getBytes(ConstantesCRM.SISO));
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                return null;
            }
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    private void processaManterEndereco(String tpManter, PrePagoEnderecoForm form, DadosEndereco dadosEndereco, String tpRelacao, int nrPos, HttpServletRequest request) throws Exception {

        idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
        DadosEndereco dadosEnderecoCopia = DadosEndereco.Factory.newInstance();
        dadosEnderecoCopia.set(dadosEndereco.copy());
        if ("C".equals(getPrePagoForm().getTpOperacao())) {
            form.setIdEndereco(ConstantesCRM.SVAZIO);
            dadosEndereco.setIdEndereco(ConstantesCRM.SVAZIO);
        }
        if ("A".equals(getPrePagoForm().getTpOperacao())) {
            String resposta = ConstantesCRM.SVAZIO;
            LupaClienteVO lupaClienteVO = LupaClienteVO.Factory.newInstance();
            lupaClienteVO.addNewDadosLupaCliente().setNrLinha(nrLinha);
            lupaClienteVO.getDadosLupaCliente().setIdPessoa("U".equals(tpRelacao) ? getPrePagoForm().getIdPessoaUsu() : getPrePagoForm().getIdPessoa());
            lupaClienteVO.addNewDadosAbaLupaCliente().addNewEnderecoVO();

            if (ConstantesCRM.SONE.equals(dadosEndereco.getInEndDefault())) {
                setEndDefault(dadosEndereco);
            }
            EnderecoVO enderecoVO = getEnderecosVO(dadosEndereco);

            lupaClienteVO.getDadosAbaLupaCliente().setEnderecoVOArray(0, enderecoVO);
            if ("I".equals(tpManter)) {
                if (ConstantesCRM.SONE.equals(dadosEndereco.getInEndDefault())) {
                    ParametroVO apoioParam = null;
                    try {
                        apoioParam = telaInicialFacade.getParametro(idUsuario, this.parametro_end_default);
                    } catch (Exception e) {
                        apoioParam = ParametroVO.Factory.newInstance();
                    }

                    resposta = telaInicialFacade.setSalvarNovoEndereco(idUsuario, lupaClienteVO);
                    dadosEndereco.set(dadosEnderecoCopia.copy());
                    if (resposta.length() != 0) {
                        String msgErro = resposta.indexOf("]") > -1 ? resposta.substring(resposta.indexOf("]") + 1) : resposta;
                        throw (new Exception(msgErro));
                    } else {
                        setDadosEndDefault(idUsuario, dadosEnderecoCopia, apoioParam.getDsValorParametro(), getPrePagoForm().getIdLinha(), getPrePagoForm().getNrLinha(), request);
                    }
                } else {
                    resposta = telaInicialFacade.setSalvarNovoEndereco(idUsuario, lupaClienteVO);
                    if (resposta.length() != 0) {
                        String msgErro = resposta.indexOf("]") > -1 ? resposta.substring(resposta.indexOf("]") + 1) : resposta;
                        throw (new Exception(msgErro));
                    }
                }
            } else if ("A".equals(tpManter)) {
                if (PF.equals(inTipoPessoa)) {
                    if ("C".equals(tpRelacao)) {
                        // dadosEnderecoCopia =
                        // getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).getDadosEnderecoArray(nrPos);
                    } else if ("U".equals(tpRelacao)) {
                        dadosEnderecoCopia = getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(1).getDadosEnderecoArray(nrPos);
                    }
                } else if (PJ.equals(inTipoPessoa)) {
                    if ("C".equals(tpRelacao)) {
                        // dadosEnderecoCopia =
                        // getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJ().getDadosEnderecoArray(nrPos);
                    } else if ("U".equals(tpRelacao)) {
                        dadosEnderecoCopia = getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).getDadosEnderecoArray(nrPos);
                    }
                }
                if (ConstantesCRM.SONE.equals(dadosEndereco.getInEndDefault())) {
                    ParametroVO apoioParam = null;
                    try {
                        apoioParam = telaInicialFacade.getParametro(idUsuario, parametro_end_default);
                    } catch (Exception e) {
                        apoioParam = ParametroVO.Factory.newInstance();
                    }
                    resposta = telaInicialFacade.setSalvarAlterarEndereco(idUsuario, lupaClienteVO);
                    dadosEndereco.set(dadosEnderecoCopia.copy());
                    if (resposta.length() != 0) {
                        String msgErro = resposta.indexOf("]") > -1 ? resposta.substring(resposta.indexOf("]") + 1) : resposta;
                        throw (new Exception(msgErro));
                    } else {
                        ParametroVO apioParam = telaInicialFacade.getParametro(idUsuario, this.parametro_alt_endereco);
                        ClienteUtils.registrarPalitagem(idUsuario, getParametrosVO(request), getPrePagoForm().getIdLinha(), getPrePagoForm().getNrLinha(), apioParam.getDsValorParametro(), getEndereco2String(dadosEnderecoCopia));
                        setDadosEndDefault(idUsuario, dadosEnderecoCopia, apoioParam.getDsValorParametro(), getPrePagoForm().getIdLinha(), getPrePagoForm().getNrLinha(), request);
                    }

                } else {
                    resposta = telaInicialFacade.setSalvarAlterarEndereco(idUsuario, lupaClienteVO);
                    dadosEndereco.set(dadosEnderecoCopia.copy());
                    if (resposta.length() != 0) {
                        String msgErro = resposta.indexOf("]") > -1 ? resposta.substring(resposta.indexOf("]") + 1) : resposta;
                        throw (new Exception(msgErro));
                    } else {
                        ParametroVO apioParam = telaInicialFacade.getParametro(idUsuario, this.parametro_alt_endereco);
                        ClienteUtils.registrarPalitagem(idUsuario, getParametrosVO(request), getPrePagoForm().getIdLinha(), getPrePagoForm().getNrLinha(), apioParam.getDsValorParametro(), getEndereco2String(dadosEnderecoCopia));
                    }
                }
            } else if ("E".equals(tpManter)) {
                if (PF.equals(inTipoPessoa)) {
                    if ("C".equals(tpRelacao)) {
                        dadosEnderecoCopia = getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).getDadosEnderecoArray(nrPos);
                    } else if ("U".equals(tpRelacao)) {
                        dadosEnderecoCopia = getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(1).getDadosEnderecoArray(nrPos);
                    }
                } else if (PJ.equals(inTipoPessoa)) {
                    if ("C".equals(tpRelacao)) {
                        dadosEnderecoCopia = getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray(0).getDadosEnderecoArray(nrPos);
                    } else if ("U".equals(tpRelacao)) {
                        dadosEnderecoCopia = getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).getDadosEnderecoArray(nrPos);
                    }
                }
                telaInicialFacade.setExcluirEndereco(idUsuario, lupaClienteVO);
                ParametroVO apioParam = telaInicialFacade.getParametro(idUsuario, this.parametro_exc_endereco);
                ClienteUtils.registrarPalitagem(idUsuario, getParametrosVO(request), getPrePagoForm().getIdLinha(), getPrePagoForm().getNrLinha(), apioParam.getDsValorParametro(), getEndereco2String(dadosEnderecoCopia));
            }
        }
    }

    private String getEndereco2String(DadosEndereco dadosEndereco) {
        StringBuffer dados = new StringBuffer();
        dados.append("Tipo Endereço: ").append(getTextListById(dadosEndereco.getIdTipoEndereco(), "LTE")).append("Tipo Logradouro: ").append(dadosEndereco.getNmTipoLogradouro()).append("; Titulo do Logradouro: ")
                .append(dadosEndereco.getNmTitLogradouro()).append("; Logradouro: ").append(dadosEndereco.getNmLogradouro()).append("; Número: ").append(dadosEndereco.getNrLogradouro()).append("; Complemento: ")
                .append(dadosEndereco.getNmComplemento()).append("; Bairro: ").append(dadosEndereco.getNmBairro()).append("; Município: ").append(dadosEndereco.getNmMunicipio()).append("; UF: ")
                .append(getTextListById(dadosEndereco.getIdUF(), "LUF")).append("; CEP: ").append(dadosEndereco.getNrCEP());
        return dados.toString();
    }

    private String getTextListById(String idItem, String tpLista) {
        String result = ConstantesCRM.SVAZIO;
        if ("LTE".equals(tpLista)) { // Lista Tipo Endereco
            for (int i = 0; i < getListasVO().getListaTipoEnderecoArray().length; i++) {
                if (idItem.equals(getListasVO().getListaTipoEnderecoArray(i).getIdTipoEndereco())) {
                    result = getListasVO().getListaTipoEnderecoArray(i).getDsTipoEndereco();
                    break;
                }
            }
        } else if ("LUF".equals(tpLista)) { // Lista UF
            for (int i = 0; i < getListasVO().getListaUFArray().length; i++) {
                if (idItem.equals(getListasVO().getListaUFArray(i).getIdUF())) {
                    result = getListasVO().getListaUFArray(i).getSgUF();
                    break;
                }
            }
        } else if ("LS".equals(tpLista)) {// Lista Sexo
            for (int i = 0; i < getListasVO().getListaTipoSexoArray().length; i++) {
                if (idItem.equals(getListasVO().getListaTipoSexoArray(i).getIdSexo())) {
                    result = getListasVO().getListaTipoSexoArray(i).getDsSexo();
                    break;
                }
            }
        } else if ("LTD".equals(tpLista)) {// Lista Tipo Documento
            for (int i = 0; i < getListasVO().getListaTipoDocumentoArray().length; i++) {
                if (idItem.equals(getListasVO().getListaTipoDocumentoArray(i).getIdTipoDocumento())) {
                    result = getListasVO().getListaTipoDocumentoArray(i).getDsTipoDocumento();
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Lista os Enderecos do Cliente/Usuario
     * 
     * @jpf:action
     * @jpf:forward name="success" path="tabListaEnderecos.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward tabListaEnderecos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String tpCliente = request.getParameter("inTipoPessoa"); // PF ou PJ
        String tpVinculo = request.getParameter("inTipoRelaciona"); // Cliente ou Usuario

        if (PF.equals(tpCliente)) {
            if ("C".equals(tpVinculo)) {
                getPrePagoEnderecoForm().setDadosEndereco(getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).getDadosEnderecoArray());
            } else if ("U".equals(tpVinculo)) {
                getPrePagoEnderecoForm().setDadosEndereco(getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(1).getDadosEnderecoArray());
            }
        } else if (PJ.equals(tpCliente)) {
            if ("C".equals(tpVinculo)) {
                getPrePagoEnderecoForm().setDadosEndereco(getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray(0).getDadosEnderecoArray());
            } else if ("U".equals(tpVinculo)) {
                getPrePagoEnderecoForm().setDadosEndereco(getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).getDadosEnderecoArray());
            }
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * Verifica se o numero do celular fornecido é um numero válido.
     * 
     * @jpf:action
     * @jpf:forward name="success" path="index.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward validarLinha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("PrePagoController:validarLinha()");
        PrePagoForm form = (PrePagoForm) formParam;
        StringBuffer xmlDados = new StringBuffer();
        try {
            PrePagoRetornoValidaLinha resultado = validarLinha(form.getNrLinha(), false, request);
            log.debug("Prepago::Validar linha:=" + resultado.xmlText());
            if (ConstantesCRM.SNOK.equals(resultado.getResult())) {
                AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
                ajaxErrorHandlerVO.setFriendlyMessage("Número de linha origem inválido. Forneça um número de linha válido ou pré-ativo.");
                ajaxErrorHandlerVO.setExceptionMessage(ConstantesCRM.SVAZIO);
                xmlDados = new StringBuffer();
                xmlDados.append(ClienteUtils.getCleanXMLFromXSD(ajaxErrorHandlerVO, null));
            }
            if (ConstantesCRM.SOK.equals(resultado.getResult()) && !ConstantesCRM.SVAZIO.equals(resultado.getIdPessoa())) {
                AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
                ajaxErrorHandlerVO.setFriendlyMessage("Número de linha origem já associado a um cliente. Forneça um número de linha pré-ativo.");
                ajaxErrorHandlerVO.setExceptionMessage(ConstantesCRM.SVAZIO);
                xmlDados = new StringBuffer();
                xmlDados.append(ClienteUtils.getCleanXMLFromXSD(ajaxErrorHandlerVO, null));
            }
            if (ConstantesCRM.SOK.equals(resultado.getResult()) && ConstantesCRM.SVAZIO.equals(resultado.getIdPessoa())) {
                String nrProtSessao = (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO);
                xmlDados.append("<PrePagoVO>").append("<nrLinha>").append(form.getNrLinha()).append("</nrLinha>").append("<cdSeguranca>").append(resultado.getDigito()).append("</cdSeguranca>").append("<nrProtocolo>").append(nrProtSessao)
                        .append("</nrProtocolo>").append("</PrePagoVO>");
                getPrePagoForm().setNrLinha(form.getNrLinha());
                getPrePagoForm().setCdSeguranca(resultado.getDigito());
                getPrePagoForm().setIdLinha(resultado.getIdlinhaTelefonica());
            }
            if ("BLOQ".equals(resultado.getResult())) {
                AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
                ajaxErrorHandlerVO.setFriendlyMessage(" DDD " + form.getNrLinha().substring(0, 2) + " bloqueado temporariamente. Não é possivel seguir com o cadastro.");
                ajaxErrorHandlerVO.setExceptionMessage(ConstantesCRM.SVAZIO);
                xmlDados = new StringBuffer();
                xmlDados.append(ClienteUtils.getCleanXMLFromXSD(ajaxErrorHandlerVO, null));
            }
        } catch (Exception e) {
            log.error("PrepagoController::validarLinha", e);
            AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
            ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
            ajaxErrorHandlerVO.setFriendlyMessage(MENSAGEM_GERAL);
            xmlDados = new StringBuffer();
            xmlDados.append(ClienteUtils.getCleanXMLFromXSD(ajaxErrorHandlerVO, null));

        } finally {
            response.setContentType(ConstantesCRM.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(xmlDados.toString().getBytes(ConstantesCRM.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        return null;
    }

    private PrePagoRetornoValidaLinha validarLinha(String nrLinha, boolean isAlter, HttpServletRequest request) throws Exception {

        String user = ConstantesCRM.getCurrentUser(request.getSession());
        if (user == null) {
            throw new Exception("Usuário não encontrado na Sessão.");
        }

        PrePagoRetornoValidaLinha resultadoVO = PrePagoRetornoValidaLinha.Factory.newInstance();
        nrLinha = (nrLinha == null || nrLinha == ConstantesCRM.SVAZIO) ? ConstantesCRM.SONE : nrLinha;
        if (nrLinha.equals(ConstantesCRM.SONE)) {
            resultadoVO.setResult(ConstantesCRM.SNOK);

        } else {
            nrLinha = nrLinha.replaceAll("[^0-9]", ConstantesCRM.SVAZIO);
            resultadoVO = prePagoFacade.validarLinha(idUsuario, nrLinha);
        }

        String idPessoa = null;
        if (ConstantesCRM.SOK.equals(resultadoVO.getResult())) {
            idPessoa = (resultadoVO.getIdPessoa() != null && !ConstantesCRM.SVAZIO.equals(resultadoVO.getIdPessoa())) ? resultadoVO.getIdPessoa() : null;

            if ((isInclui && idPessoa != null) || isTransf == false && idPessoa != null && (null == idPessoaCliente || !idPessoaCliente.equals(idPessoa))) {
                // envia mensagem informando que o cliente associado a linha não é o mesmo que está no sistema.
                request.setAttribute("msg", " Cliente já possui cadastro. Favor realizar alteração.");
            } else {
                String nrProtocolo = ConstantesCRM.SVAZIO;
                try {
                    GetDadosProtocoloOutTO dadosProtocoloOutTO = protocoloFacade.getDadosProtocolo(user, nrLinha, ConstantesCRM.SVAZIO, "7");
                    if (dadosProtocoloOutTO.getNrProtocolo() != null && !ConstantesCRM.SVAZIO.equals(dadosProtocoloOutTO.getNrProtocolo())) {
                        nrProtocolo = dadosProtocoloOutTO.getNrProtocolo();
                    } else {
                        AbreProtocoloTO abreProtocoloTO = AbreProtocoloTO.Factory.newInstance();
                        abreProtocoloTO.setIdSistemaOrigem("7");
                        abreProtocoloTO.setIdTipoAberturaProtocolo(ConstantesCRM.STWO);
                        abreProtocoloTO.setCdAreaRegistro(nrLinha.substring(0, 2));
                        abreProtocoloTO.setNrTelefone(nrLinha.substring(2));

                        AbreProtocoloOutTO out = protocoloFacade.setAbreValidaProtocolo(user, abreProtocoloTO);
                        nrProtocolo = out.getNrProtocolo();
                    }
                } catch (Exception e) {
                    nrProtocolo = ConstantesCRM.SVAZIO;
                }
                request.getSession().setAttribute(ConstantesCRM.NRPROTOCOLO, (nrProtocolo != null ? nrProtocolo : ConstantesCRM.SVAZIO));

                this.nrLinha = nrLinha;
                getPrePagoForm().setNrLinha(nrLinha);
                getPrePagoForm().setIdLinha(resultadoVO.getIdlinhaTelefonica());
                if (isInclui && ConstantesCRM.SVAZIO.equals(idPessoa)) {
                    this.idPessoaCliente = ConstantesCRM.SVAZIO;
                    getPrePagoForm().setInTipoPessoa(ConstantesCRM.SVAZIO);
                } else {
                    this.idPessoaCliente = this.idPessoaCliente == null ? idPessoa : this.idPessoaCliente;
                    getPrePagoForm().setInTipoPessoa(
                            idPessoaCliente != null ? this.inTipoPessoa : ((resultadoVO.getIdTipoPessoa() != null && !resultadoVO.getIdTipoPessoa().equals(ConstantesCRM.SVAZIO)) ? (resultadoVO.getIdTipoPessoa().equals(ConstantesCRM.SONE) ? PF
                                    : PJ) : null));
                }
                getPrePagoForm().setIdPessoa(this.idPessoaCliente);
                getPrePagoForm().setCdSeguranca(resultadoVO.getDigito());
                if (getPrePagoForm().getInTipoPessoa() == null) {
                    if (resultadoVO.getIdTipoPessoa() != null && ConstantesCRM.SONE.equals(resultadoVO.getIdTipoPessoa())) {
                        getPrePagoForm().setInTipoPessoa(PF);
                    } else {
                        if (null != resultadoVO.getIdTipoPessoa()) {
                            getPrePagoForm().setInTipoPessoa(PJ);
                        }
                    }
                }
            }
        }
        return resultadoVO;
    }

    /**
     * Verifica se o numero do celular fornecido é um numero válido.
     * 
     * @jpf:action
     * @jpf:forward name="success" path="index.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward validarCNAE(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        StringBuffer xmlDados = new StringBuffer();

        try {
            idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
            String receiveCNAE = request.getParameter("nrCNAE");
            CNAEVO cnaeVO = prePagoFacade.validaCNAE(idUsuario, receiveCNAE);

            if (ConstantesCRM.SVAZIO.equals(cnaeVO.getIdCNAE())) {
                AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
                ajaxErrorHandlerVO.setExceptionMessage(ConstantesCRM.SVAZIO);
                ajaxErrorHandlerVO.setFriendlyMessage("Número CNAE Não cadastrado!");
                xmlDados = new StringBuffer();
                xmlDados.append(ClienteUtils.getCleanXMLFromXSD(ajaxErrorHandlerVO, null));
            } else {
                xmlDados.append(ClienteUtils.getCleanXMLFromXSD(cnaeVO, null));
                request.setAttribute("dsCNAE", cnaeVO.getDsCNAE());
            }

        } catch (Exception e) {
            AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
            ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
            ajaxErrorHandlerVO.setFriendlyMessage("Número CNAE Não encontrado!");
            xmlDados = new StringBuffer();
            xmlDados.append(ClienteUtils.getCleanXMLFromXSD(ajaxErrorHandlerVO, null));

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
     * Verifica se o número do celular fornecido é válido.
     * 
     * @jpf:action
     * @jpf:forward name="success" path="tabListaCEP.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward tabListaCEP(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            PrePagoEnderecoForm form = (PrePagoEnderecoForm) formParam;
            String user = ConstantesCRM.getCurrentUser(request.getSession());
            PesquisaEnderecoVO pesquisaEnderecoVO = PesquisaEnderecoVO.Factory.newInstance();
            pesquisaEnderecoVO.addNewFiltroPesquisa();
            pesquisaEnderecoVO.getFiltroPesquisa().setNrCEP(form.getNrCEP());
            pesquisaEnderecoVO.getFiltroPesquisa().setIdUFSelecionado(form.getIdEndUF());
            pesquisaEnderecoVO.getFiltroPesquisa().setDsLocalidade(form.getNmMunicipio());
            pesquisaEnderecoVO.getFiltroPesquisa().setDsLogradouro(form.getNmLogradouro());

            PesquisaEnderecoVO resultPesquisaEnderecoVO = telaInicialFacade.getPesquisaEnderecoFil(user, pesquisaEnderecoVO);

            if ((resultPesquisaEnderecoVO.getListaEnderecos() == null || resultPesquisaEnderecoVO.getListaEnderecos().getEnderecoVOArray().length == 0)
                    && (resultPesquisaEnderecoVO.getErro() == null || ConstantesCRM.SVAZIO.equals(resultPesquisaEnderecoVO.getErro()))) {
                resultPesquisaEnderecoVO.addNewListaEnderecos();
                if (resultPesquisaEnderecoVO.getNoCep() != null) {
                    request.setAttribute("msg", "CEP não encontrado!");
                } else {
                    request.setAttribute("msg", "Nenhum endereço encontrado!");
                }
            } else if ((resultPesquisaEnderecoVO.getErro() == null || ConstantesCRM.SVAZIO.equals(resultPesquisaEnderecoVO.getErro()))) {
                resultPesquisaEnderecoVO = PesquisaEnderecoVO.Factory.newInstance();
                resultPesquisaEnderecoVO.addNewListaEnderecos();
            } else {
                request.setAttribute("erro", resultPesquisaEnderecoVO.getErro());
                resultPesquisaEnderecoVO = PesquisaEnderecoVO.Factory.newInstance();
                resultPesquisaEnderecoVO.addNewListaEnderecos();
            }

            getPrePagoEnderecoForm().setPesquisaEnderecoVO(resultPesquisaEnderecoVO);

            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);

        } catch (Exception e) {
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward loadDadosPrePago(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        StringBuffer xmlDados = new StringBuffer();
        String inTipoPessoa = request.getParameter("tpPes");
        String idTipoDocumento = request.getParameter("tpDoc");
        String idLinhaTelefonica = request.getParameter("idLinhaTelefonica");
        String idPessoa = request.getParameter("idPessoa");
        String nrDocumento = request.getParameter("nrDoc");
        String tpRelaciona = request.getParameter("tpRel");

        try {
            String user = ConstantesCRM.getCurrentUser(request.getSession());
            PrePagoVO prePagoVO = prePagoFacade.getPrePagoVOById(user, inTipoPessoa, idLinhaTelefonica, idPessoa);

            if (ConstantesCRM.SONE.equals(prePagoVO.getCodError())) {
                limparEnderecoUsuario(inTipoPessoa);
                AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
                ajaxErrorHandlerVO.setExceptionMessage(prePagoVO.getMsgError());
                ajaxErrorHandlerVO.setFriendlyMessage("Nenhum Cliente Encontrado com o documento informado!");
                xmlDados = new StringBuffer();
                xmlDados.append(ClienteUtils.getCleanXMLFromXSD(ajaxErrorHandlerVO, null));

            } else {
                // Pesquisa por CNPJ
                if (prePagoVO.getPJArray() != null && prePagoVO.getPJArray().length == 1 && prePagoVO.getPJArray(0).getNrCNPJ() != null) {
                    getPrePagoForm().setIdPessoa(prePagoVO.getPJArray(0).getIdPessoa());
                    getPrePagoForm().setIdPessoaPJ(prePagoVO.getPJArray(0).getIdPessoa());

                    if (prePagoVO.getPFArray() != null && prePagoVO.getPFArray().length > 0) {
                        getPrePagoForm().setIdPessoaUsu(prePagoVO.getPFArray(0).getIdPessoa());
                    }

                } else {
                    // Setar idTipoDocumento retornado para o idTipoDocumento pesquisado.
                    // Necessário pois pesquisa pode retornar valores diferentes de ID
                    // para o mesmo tipo de documento. (Ex.: CPF, KCPF, UCPF etc)
                    if (prePagoVO.getPFArray() != null) {
                        for (int i = 0; i < prePagoVO.getPFArray().length; i++) {
                            for (int j = 0; j < prePagoVO.getPFArray()[i].getDocumentoArray().length; j++) {
                                if (nrDocumento.equals(prePagoVO.getPFArray()[i].getDocumentoArray()[j].getNrDocumento())) {
                                    prePagoVO.getPFArray()[i].getDocumentoArray()[j].setIdTipoDocumento(idTipoDocumento);
                                }
                            }
                        }
                    }

                    getPrePagoForm().setClientesPesquisados(null);
                    getPrePagoForm().setIdPessoa(prePagoVO.getPFArray(0).getIdPessoa());
                    getPrePagoForm().setIdPessoaCli(prePagoVO.getPFArray(0).getIdPessoa());

                    if (prePagoVO.getPFArray() != null && prePagoVO.getPFArray().length > 1) {
                        getPrePagoForm().setIdPessoaUsu(prePagoVO.getPFArray(1).getIdPessoa());
                    }
                }

                limparEnderecoUsuario(inTipoPessoa);
                trataEnderecosPrePagoVO(prePagoVO, inTipoPessoa, tpRelaciona);
                xmlDados.append(ClienteUtils.getCleanXMLFromXSD(prePagoVO, null));
            }

        } catch (Exception e) {
            System.out.println("Ainda retornou Exception " + e.getMessage());
        } finally {
            response.setContentType(ConstantesCRM.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(xmlDados.toString().getBytes(ConstantesCRM.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        return null;
    }

    private PrePagoVO trataEnderecosPrePagoVO(PrePagoVO prePagoVO, String tpPessoa, String tpRelaciona) {
        if (prePagoVO.getPFArray() != null && prePagoVO.getPFArray().length > 0) {
            for (int i = 0; i < prePagoVO.getPFArray().length; i++) {
                if (prePagoVO.getPFArray(i).getDadosEnderecoArray() != null && prePagoVO.getPFArray(i).getDadosEnderecoArray().length > 0) {
                    DadosEndereco[] dadosEndereco = prePagoVO.getPFArray(i).getDadosEnderecoArray().clone();
                    for (int j = 0; j < dadosEndereco.length; j++) {
                        if (!"A".equals(getPrePagoForm().getTpOperacao())) {
                            dadosEndereco[j].setIdEndereco(ConstantesCRM.SVAZIO);
                        }
                    }
                    if (prePagoVO.getPFArray().length == 1 && PF.equals(tpPessoa) && "U".equals(tpRelaciona)) {
                        if (getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(1).getDadosEnderecoArray() != null && getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(1).getDadosEnderecoArray().length > 0) {
                            getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(1).setDadosEnderecoArray(dadosEndereco);
                        } else {
                            getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(1).addNewDadosEndereco();
                            getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(1).setDadosEnderecoArray(dadosEndereco);
                        }
                    } else if (prePagoVO.getPFArray().length == 1 && PJ.equals(tpPessoa) && "U".equals(tpRelaciona)) {
                        if (getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).getDadosEnderecoArray() != null && getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).getDadosEnderecoArray().length > 0) {
                            getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).setDadosEnderecoArray(dadosEndereco);
                        } else {
                            getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).addNewDadosEndereco();
                            getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).setDadosEnderecoArray(dadosEndereco);
                        }
                    } else {
                        if (getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(i).getDadosEnderecoArray() != null && getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(i).getDadosEnderecoArray().length > 0) {
                            getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(i).setDadosEnderecoArray(dadosEndereco);
                        } else {
                            getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(i).addNewDadosEndereco();
                            getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(i).setDadosEnderecoArray(dadosEndereco);
                        }
                    }
                }
            }
        }
        if (prePagoVO.getPJArray() != null && prePagoVO.getPJArray().length == 1 && prePagoVO.getPJArray(0).getNrCNPJ() != null) {
            if (prePagoVO.getPJArray(0).getDadosEnderecoArray() != null && prePagoVO.getPJArray(0).getDadosEnderecoArray().length > 0) {
                DadosEndereco[] dadosEndereco = prePagoVO.getPJArray(0).getDadosEnderecoArray();
                for (int j = 0; j < dadosEndereco.length; j++) {
                    if (!"A".equals(getPrePagoForm().getTpOperacao())) {
                        dadosEndereco[j].setIdEndereco(ConstantesCRM.SVAZIO);
                    }
                }

                if (getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray(0).getDadosEnderecoArray() != null || getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray(0).getDadosEnderecoArray().length > 0) {
                    getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray(0).setDadosEnderecoArray(dadosEndereco);
                } else {
                    getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray(0).addNewDadosEndereco();
                    getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray(0).setDadosEnderecoArray(dadosEndereco);
                }
            }
        }
        return prePagoVO;
    }

    private void limparEnderecoUsuario(String tpPessoa) {
        if (PF.equals(tpPessoa)) {
            if (getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray() != null && getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray().length > 1) {
                int nEnd = getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(1).getDadosEnderecoArray().length;
                for (int j = 0; j < nEnd; j++) {
                    getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(1).removeDadosEndereco(0);
                }
            }
        } else if (PJ.equals(tpPessoa)) {

            if (getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray() != null && getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray().length == 1) {
                if (getPrePagoEnderecoForm().getPrePagoVOEndereco().getPJArray(0).getDadosEnderecoArray() != null) {
                    int nEnd = getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).getDadosEnderecoArray().length;
                    for (int j = 0; j < nEnd; j++) {
                        getPrePagoEnderecoForm().getPrePagoVOEndereco().getPFArray(0).removeDadosEndereco(0);
                    }
                }
            }
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="pesquisaEndereco.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward loadPesquisaEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

        try {
            String user = ConstantesCRM.getCurrentUser(request.getSession());

            // Flag em banco que determina criação de novo usuário ao invés de atualizar existente
            ParametroVO parametro;
            try {
                parametro = GetParametro.getInstace().getParametroVO(user, PARAMETRO_PESQUISA_ENDERECO_NA_BASE);
            } catch (Exception e) {
                parametro = ParametroVO.Factory.newInstance();
            }

            pesquisaEnderecoWebService = new Boolean(ConstantesCRM.SZERO.equals(parametro.getDsValorParametro()) ? true : false);

            if (pesquisaEnderecoWebService.booleanValue()) {
                getPrePagoEnderecoForm().setPesquisaEnderecoBaseFO(false);
            } else {
                getPrePagoEnderecoForm().setPesquisaEnderecoBaseFO(true);
            }

            if (request.getParameter("origem") != null) {
                request.setAttribute("origem", request.getParameter("origem"));
                getPrePagoEnderecoForm().setPesquisaEnderecoVO(telaInicialFacade.getPesquisaEnderecoIni(user));
            }

            if (getPrePagoEnderecoForm().getPesquisaEnderecoVO().getFiltroPesquisa() == null) {
                getPrePagoEnderecoForm().getPesquisaEnderecoVO().addNewFiltroPesquisa();
            }
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);

        } catch (Exception ex) {
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="listaClientes.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward getListaClientes(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

        boolean inPaginacao = request.getParameter("nrPag") != null ? true : false;

        if (inPaginacao) {
            String inTipoPessoa = request.getParameter("tpPes");
            String idTipoDocumento = request.getParameter("tpDoc");
            String nrDocumento = request.getParameter("nrDoc");
            String tpRelaciona = request.getParameter("tpRel");

            String nrLinha = request.getParameter("nrLin");
            String nrConta = request.getParameter("nrCon");

            int nrPagina = Integer.parseInt(request.getParameter("nrPag"));
            String user = ConstantesCRM.getCurrentUser(request.getSession());

            try {
                PrePagoVO prePagoVO = null;
                if ((nrLinha != null && !ConstantesCRM.SVAZIO.equals(nrLinha)) || (nrConta != null && !ConstantesCRM.SVAZIO.endsWith(nrConta))) {
                    prePagoVO = prePagoFacade.getPessoaByDocumentoFiltro(user, "U".equals(tpRelaciona) ? PF : inTipoPessoa, idTipoDocumento, nrDocumento, true, nrPagina, nrLinha, nrConta);
                } else {
                    prePagoVO = prePagoFacade.getPessoaByDocumento(user, "U".equals(tpRelaciona) ? PF : inTipoPessoa, idTipoDocumento, nrDocumento, true, nrPagina);
                }
                getPrePagoForm().setClientesPesquisados(prePagoVO);
                getPrePagoForm().setPageNumber(Integer.toString(nrPagina));

            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }
        }

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);

    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="detalhesEndereco.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward detalhesEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        log.debug("PrePagoController::detalhesEndereco inicio");
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        request.setAttribute("prePagoForm", getPrePagoForm());
        ActionRedirect action = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
        if (this.getPrePagoForm() == null) {
            log.debug("form PrePagoForm está vazio");
        } else {
            log.debug(this.getPrePagoForm().getClientesPesquisados().toString());
        }
        action.addParameter("index", request.getParameter("index"));
        action.addParameter("inTipoPessoa", request.getParameter("inTipoPessoa"));
        request.setAttribute("index", request.getParameter("index"));
        request.setAttribute("inTipoPessoa", request.getParameter("inTipoPessoa"));
        return mapping.findForward(ConstantesCRM.SUCCESS);

    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="listaClientes.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward pesquisaCliente(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        StringBuffer xmlDados = new StringBuffer();
        String inTipoPessoa = request.getParameter("tpPes");
        String idTipoDocumento = request.getParameter("tpDoc");
        String nrDocumento = request.getParameter("nrDoc");
        String tpRelaciona = request.getParameter("tpRel");

        try {

            String user = ConstantesCRM.getCurrentUser(request.getSession());

            PrePagoVO prePagoVO = prePagoFacade.getPessoaByDocumento(user, "U".equals(tpRelaciona) ? PF : inTipoPessoa, idTipoDocumento, nrDocumento, true, 1);

            if (ConstantesCRM.SONE.equals(prePagoVO.getCodError())) {
                limparEnderecoUsuario(inTipoPessoa);
                AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
                ajaxErrorHandlerVO.setExceptionMessage(prePagoVO.getMsgError());
                ajaxErrorHandlerVO.setFriendlyMessage("Nenhum Cliente Encontrado com o documento informado!");
                xmlDados = new StringBuffer();
                xmlDados.append(ClienteUtils.getCleanXMLFromXSD(ajaxErrorHandlerVO, null));

            } else {
                // Pesquisa por CNPJ
                if (prePagoVO.getPJArray() != null && prePagoVO.getPJArray().length == 1 && prePagoVO.getPJArray(0).getNrCNPJ() != null) {
                    getPrePagoForm().setIdPessoa(prePagoVO.getPJArray(0).getIdPessoa());
                    getPrePagoForm().setIdPessoaPJ(prePagoVO.getPJArray(0).getIdPessoa());
                    trataEnderecosPrePagoVO(prePagoVO, inTipoPessoa, tpRelaciona);

                    if (prePagoVO.getPFArray() != null && prePagoVO.getPFArray().length > 0) {
                        getPrePagoForm().setIdPessoaUsu(prePagoVO.getPFArray(0).getIdPessoa());
                    }

                } else {
                    // Setar idTipoDocumento retornado para o idTipoDocumento pesquisado.
                    // Necessário pois pesquisa pode retornar valores diferentes de ID
                    // para o mesmo tipo de documento. (Ex.: CPF, KCPF, UCPF etc)
                    if (prePagoVO.getPFArray() != null) {
                        for (int i = 0; i < prePagoVO.getPFArray().length; i++) {
                            if (prePagoVO.getPFArray()[i].getDocumentoArray(0) != null) {
                                prePagoVO.getPFArray()[i].getDocumentoArray(0).setIdTipoDocumento(idTipoDocumento);
                            }
                        }
                    }

                    getPrePagoForm().setClientesPesquisados(prePagoVO);

                    if (prePagoVO.getPFArray() != null && prePagoVO.getPFArray().length == 1) {
                        getPrePagoEnderecoForm().getPrePagoVOEndereco().setPFArray(0, prePagoVO.getPFArray(0));
                        getPrePagoForm().setIdPessoa(prePagoVO.getPFArray(0).getIdPessoa());
                        getPrePagoForm().setIdPessoaCli(prePagoVO.getPFArray(0).getIdPessoa());
                        trataEnderecosPrePagoVO(prePagoVO, inTipoPessoa, tpRelaciona);

                    } else if (prePagoVO.getPJArray() != null && prePagoVO.getPJArray().length == 1) {
                        getPrePagoEnderecoForm().getPrePagoVOEndereco().setPJArray(0, prePagoVO.getPJArray(0));
                        getPrePagoForm().setIdPessoa(prePagoVO.getPJArray(0).getIdPessoa());
                        getPrePagoForm().setIdPessoaCli(prePagoVO.getPJArray(0).getIdPessoa());
                        trataEnderecosPrePagoVO(prePagoVO, inTipoPessoa, tpRelaciona);
                    }

                }
                limparEnderecoUsuario(inTipoPessoa);
                xmlDados.append(ClienteUtils.getCleanXMLFromXSD(prePagoVO, null));
            }

        } catch (Exception e) {
            if (e.getMessage().indexOf("expirada") >= 0) {
                AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
                ajaxErrorHandlerVO.setExceptionMessage(e.toString());
                ajaxErrorHandlerVO.setFriendlyMessage("Sessão expirada.");
                xmlDados = new StringBuffer();
                xmlDados.append(ClienteUtils.getCleanXMLFromXSD(ajaxErrorHandlerVO, null));
            }else if(e.getMessage().indexOf("XML") >= 0){
            	AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
                ajaxErrorHandlerVO.setExceptionMessage(e.toString());
                ajaxErrorHandlerVO.setFriendlyMessage("Por favor, preencher os dados abaixo para um novo cadastro do cliente. Nao foi possivel avaliar se ja existe cadastro em tempo rapido para reaproveitamento.");
                xmlDados = new StringBuffer();
                xmlDados.append(ClienteUtils.getCleanXMLFromXSD(ajaxErrorHandlerVO, null));
            }

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
     * @jpf:forward name="success" path="pesquisaEnderecoResultado.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward getAjaxEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        EnderecoVO pesq = getPrePagoEnderecoForm().getPesquisaEnderecoVO().getListaEnderecos().getEnderecoVOArray()[Integer.valueOf(request.getParameter("indexEndereco")).intValue()];
        response.setContentType(ConstantesCRM.SContentType);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
        bufferedOutputStream.write(pesq.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO).getBytes(ConstantesCRM.SISO));
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        return null;
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="pesquisaEnderecoResultado.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward pesquisarEndereco(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

        PrePagoEnderecoForm form = (PrePagoEnderecoForm) formParam;
        String user = ConstantesCRM.getCurrentUser(request.getSession());
        PesquisaEnderecoVO pesquisaEnderecoVO = PesquisaEnderecoVO.Factory.newInstance();
        ParametroVO parametro;
        int segundosTimeout = 20;

        try {
            form.getPesquisaEnderecoVO().getFiltroPesquisa().setNrCEP(form.getNrCEP());
            form.getPesquisaEnderecoVO().getFiltroPesquisa().setDsLogradouro(form.getNmLogradouro());
            form.getPesquisaEnderecoVO().getFiltroPesquisa().setDsLocalidade(form.getNmMunicipio());
            form.getPesquisaEnderecoVO().getFiltroPesquisa().setIdUFSelecionado(form.getIdEndUF());

            if (pesquisaEnderecoWebService == null) {
                // Flag em banco que determina criação de novo usuário ao invés de atualizar existente
                try {
                    parametro = GetParametro.getInstace().getParametroVO(user, PARAMETRO_PESQUISA_ENDERECO_NA_BASE);
                } catch (Exception e) {
                    parametro = ParametroVO.Factory.newInstance();
                }
                pesquisaEnderecoWebService = new Boolean(ConstantesCRM.SZERO.equals(parametro.getDsValorParametro()) ? true : false);
            }

            if (pesquisaEnderecoWebService.booleanValue()) {
                getPrePagoEnderecoForm().setPesquisaEnderecoBaseFO(false);
                String sgUF = ConstantesCRM.SVAZIO.equals(request.getParameter("sgUF")) ? null : request.getParameter("sgUF");

                parametro = GetParametro.getInstace().getParametroVO(user, PARAMETRO_ENDPOINT_PESQUISAENDERECO);

                EnderecoSOAPProxy proxy = new EnderecoSOAPProxy();
                proxy.setEndpoint(new URL(parametro.getDsValorParametro()).toString());

                String usuario = ConstantesCRM.SVAZIO;
                String senha = ConstantesCRM.SVAZIO;
                try {
                    usuario = br.com.vivo.fo.commons.utils.GetParametro.getInstace().getParametroVO(user, ConstantesCRM.ALSB3SOA_SECURITY_USER).getDsValorParametro();
                    senha = br.com.vivo.fo.commons.utils.GetParametro.getInstace().getParametroVO(user, ConstantesCRM.ALSB3SOA_SECURITY_PASSWORD).getDsValorParametro();
                } catch (Exception e) {
                }

                SecurityHeaderHelper securityHeader = new SecurityHeaderHelper();
                securityHeader.setUserTimeout(new Integer(segundosTimeout * 1000));
                securityHeader.setSecurityUserName(usuario);
                securityHeader.setSecurityPassword(senha);

                log.debug("PrePagoController::pesquisarEndereco:: securityHeader: (" + user + ") securityHeader : " + securityHeader);
                proxy.setSecurityHeaderHelper(securityHeader);

                Endereco[] retorno = null;
                try {
                    BigInteger numeroPaginaBI = new BigInteger(ConstantesCRM.SONE);
                    BigInteger qtdRegistroBI = new BigInteger("50");

                    String dataHora = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
                    log.debug("PrePagoController::pesquisarEndereco: (user=" + user + ") begin : " + dataHora);

                    ParametrosBuscarListaEnderecos parametrosBuscarListaEnderecos = new ParametrosBuscarListaEnderecos();
                    parametrosBuscarListaEnderecos.setNumeroCEP(ConstantesCRM.SVAZIO.equals(form.getNrCEP()) ? null : form.getNrCEP().trim());
                    parametrosBuscarListaEnderecos.setDescricaoLogradouro(ConstantesCRM.SVAZIO.equals(form.getNmLogradouro()) ? null : form.getNmLogradouro());
                    parametrosBuscarListaEnderecos.setDescricaoCidade(ConstantesCRM.SVAZIO.equals(form.getNmMunicipio()) ? null : form.getNmMunicipio().trim());
                    parametrosBuscarListaEnderecos.setSiglaUf(sgUF);
                    parametrosBuscarListaEnderecos.setNumeroPagina(numeroPaginaBI);
                    parametrosBuscarListaEnderecos.setQtdRegistro(qtdRegistroBI);

                    retorno = proxy.buscarListaEnderecos(parametrosBuscarListaEnderecos);

                    dataHora = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
                    log.debug("PrePagoController::pesquisarEndereco: (user=" + user + ") end : " + dataHora);

                } catch (ErroInfo e) {
                    log.debug("PrePagoController::pesquisarEndereco:: " + e);
                    retorno = null;
                    pesquisaEnderecoVO.addNewListaEnderecos();
                    getPrePagoEnderecoForm().getPesquisaEnderecoVO().setListaEnderecos(pesquisaEnderecoVO.getListaEnderecos());
                    if ("31072".equals(e.getCodigo())) {
                        request.setAttribute("erro", e.getDescricao());
                    } else {
                        request.setAttribute("erro", "Serviço de Busca de Endereço por CEP indisponível no momento. [" + e.getDescricao() + "]");
                    }

                } catch (AxisFault axisFault) {
                    log.debug("PrePagoController::pesquisarEndereco:: " + axisFault);
                    retorno = null;
                    pesquisaEnderecoVO.addNewListaEnderecos();
                    getPrePagoEnderecoForm().getPesquisaEnderecoVO().setListaEnderecos(pesquisaEnderecoVO.getListaEnderecos());
                    request.setAttribute("erro", "Serviço de Busca de Endereço por CEP indisponível no momento. [" + axisFault.getFaultString() + "]");

                } catch (Exception e) {
                    log.debug("PrePagoController::pesquisarEndereco:: " + e);
                    retorno = null;

                    pesquisaEnderecoVO.addNewListaEnderecos();
                    getPrePagoEnderecoForm().getPesquisaEnderecoVO().setListaEnderecos(pesquisaEnderecoVO.getListaEnderecos());

                    request.setAttribute("erro", "Serviço de Busca de Endereço por CEP indisponível no momento.");
                    request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                    return mapping.findForward(ConstantesCRM.SUCCESS);
                }

                if (retorno == null || retorno.length == 0) {
                    pesquisaEnderecoVO.addNewListaEnderecos();
                    if (pesquisaEnderecoVO.getNoCep() != null) {
                        request.setAttribute("msg", "CEP não encontrado!");
                    } else {
                        request.setAttribute("msg", "Nenhum endereço encontrado!");
                    }

                } else {
                    ListaEnderecos listaEnderecos = ListaEnderecos.Factory.newInstance();
                    for (int i = 0; i < retorno.length; i++) {
                        listaEnderecos.addNewEnderecoVO();
                        listaEnderecos.getEnderecoVOArray(i).setNmTipoLogradouro(retorno[i].getTipoLogradouro() == null || retorno[i].getTipoLogradouro().getDescricao() == null ? ConstantesCRM.SVAZIO : retorno[i].getTipoLogradouro().getDescricao());

                        listaEnderecos.getEnderecoVOArray(i).setNmTituloLogradouro(
                                retorno[i].getTituloLogradouro() == null || retorno[i].getTituloLogradouro().getDescricao() == null ? ConstantesCRM.SVAZIO : retorno[i].getTituloLogradouro().getDescricao());

                        listaEnderecos.getEnderecoVOArray(i).setDsEnderecoComplemento(ConstantesCRM.SVAZIO);
                        listaEnderecos.getEnderecoVOArray(i).setNrEndereco(ConstantesCRM.SVAZIO);
                        listaEnderecos.getEnderecoVOArray(i).setNmLogradouro(retorno[i].getLogradouro() == null ? ConstantesCRM.SVAZIO : retorno[i].getLogradouro());
                        listaEnderecos.getEnderecoVOArray(i).setNmBairro(retorno[i].getBairro() == null ? ConstantesCRM.SVAZIO : retorno[i].getBairro());
                        listaEnderecos.getEnderecoVOArray(i).setNmMunicipio(retorno[i].getCidade() == null ? ConstantesCRM.SVAZIO : retorno[i].getCidade());
                        listaEnderecos.getEnderecoVOArray(i).setNrCEP(retorno[i].getCep() == null ? ConstantesCRM.SVAZIO : retorno[i].getCep());
                        listaEnderecos.getEnderecoVOArray(i).setDsLado(retorno[i].getDescricaoLado() == null ? ConstantesCRM.SVAZIO : retorno[i].getDescricaoLado());

                        listaEnderecos.getEnderecoVOArray(i).addNewUFVO();
                        listaEnderecos.getEnderecoVOArray(i).getUFVO().setIdUF(retorno[i].getUf() == null || retorno[i].getUf().getCodigo() == null ? ConstantesCRM.SVAZIO : retorno[i].getUf().getCodigo().toString());
                        listaEnderecos.getEnderecoVOArray(i).getUFVO().setSgUF(retorno[i].getUf() == null || retorno[i].getUf().getSigla() == null ? ConstantesCRM.SVAZIO : retorno[i].getUf().getSigla());
                        listaEnderecos.getEnderecoVOArray(i).getUFVO().setNmUF(retorno[i].getUf() == null || retorno[i].getUf().getNome() == null ? ConstantesCRM.SVAZIO : retorno[i].getUf().getNome());

                        listaEnderecos.getEnderecoVOArray(i).addNewPaisVO();
                        listaEnderecos.getEnderecoVOArray(i).getPaisVO().setIdPais(retorno[i].getPais() == null || retorno[i].getPais().getCodigo() == null ? ConstantesCRM.SVAZIO : retorno[i].getPais().getCodigo().toString());
                        listaEnderecos.getEnderecoVOArray(i).getPaisVO().setSgPais(retorno[i].getPais() == null || retorno[i].getPais().getSigla() == null ? ConstantesCRM.SVAZIO : retorno[i].getPais().getSigla());
                        listaEnderecos.getEnderecoVOArray(i).getPaisVO().setNmPais(retorno[i].getPais() == null || retorno[i].getPais().getNome() == null ? ConstantesCRM.SVAZIO : retorno[i].getPais().getNome());

                        listaEnderecos.getEnderecoVOArray(i).setCodLogradouro(retorno[i].getCodigo() == null ? ConstantesCRM.SVAZIO : retorno[i].getCodigo().toString());
                        listaEnderecos.getEnderecoVOArray(i).setInCnl(
                                retorno[i].getEnderecoFiscal() == null || retorno[i].getEnderecoFiscal().getCodigoNacLocalidade() == null ? ConstantesCRM.SVAZIO : retorno[i].getEnderecoFiscal().getCodigoNacLocalidade());

                        listaEnderecos.getEnderecoVOArray(i).setInCodigoIBGE(retorno[i].getEnderecoFiscal() == null || retorno[i].getEnderecoFiscal().getCodigoIBGE() == null ? ConstantesCRM.SVAZIO : retorno[i].getEnderecoFiscal().getCodigoIBGE());
                    }
                    pesquisaEnderecoVO.setListaEnderecos(listaEnderecos);
                    getPrePagoEnderecoForm().getPesquisaEnderecoVO().setListaEnderecos(pesquisaEnderecoVO.getListaEnderecos());
                }

            } else {
                getPrePagoEnderecoForm().setPesquisaEnderecoBaseFO(true);
                pesquisaEnderecoVO = telaInicialFacade.getPesquisaEnderecoFil(user, form.getPesquisaEnderecoVO());
            }

            if ((pesquisaEnderecoVO.getListaEnderecos() == null || pesquisaEnderecoVO.getListaEnderecos().getEnderecoVOArray().length == 0) && (pesquisaEnderecoVO.getErro() == null || ConstantesCRM.SVAZIO.equals(pesquisaEnderecoVO.getErro()))) {
                pesquisaEnderecoVO.addNewListaEnderecos();
                if (pesquisaEnderecoVO.getNoCep() != null) {
                    request.setAttribute("msg", "CEP não encontrado!");
                } else {
                    request.setAttribute("msg", "Nenhum endereço encontrado!");
                }

            } else if ((pesquisaEnderecoVO.getErro() == null || ConstantesCRM.SVAZIO.equals(pesquisaEnderecoVO.getErro()))) {
                getPrePagoEnderecoForm().getPesquisaEnderecoVO().setListaEnderecos(pesquisaEnderecoVO.getListaEnderecos());

            } else {
                request.setAttribute("erro", pesquisaEnderecoVO.getErro());
                pesquisaEnderecoVO.addNewListaEnderecos();
                getPrePagoEnderecoForm().getPesquisaEnderecoVO().setListaEnderecos(pesquisaEnderecoVO.getListaEnderecos());
            }

            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);

        } catch (Exception ex) {
            pesquisaEnderecoVO.addNewListaEnderecos();
            request.setAttribute("erro", "Erro na pesquisa! Tente refinar os parâmetros!");
            getPrePagoEnderecoForm().getPesquisaEnderecoVO().setListaEnderecos(pesquisaEnderecoVO.getListaEnderecos());
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);
        }
    }

    // sets e gets para o form
    public PrePagoForm getPrePagoForm() {
        if (this.prePagoForm == null) {
            this.prePagoForm = new PrePagoForm();
        }
        return this.prePagoForm;
    }

    public void setPrePagoForm(PrePagoForm prePagoForm) {
        this.prePagoForm = prePagoForm;
    }

    public PrePagoEnderecoForm getPrePagoEnderecoForm() {
        if (this.prePagoEnderecoForm == null) {
            this.prePagoEnderecoForm = new PrePagoEnderecoForm();
        }
        return this.prePagoEnderecoForm;
    }

    public void setPrePagoEnderecoForm(PrePagoEnderecoForm prePagoEnderecoForm) {
        this.prePagoEnderecoForm = prePagoEnderecoForm;
    }

    public ListasVO getListasVO() {
        if (this.listasVO == null) {
            this.listasVO = ListasVO.Factory.newInstance();
        }
        return this.listasVO;
    }

    public void setListasVO(ListasVO listasVO) {
        this.listasVO = listasVO;
    }

    /*
     * private String registraPalitagem(String idLinha, String nrLinha, String nmPath, String dsObservacao) { String
     * user = ConstantesCRM.getCurrentUser(request.getSession()); AtendimentoVO atendimentoVO =
     * AtendimentoVO.Factory.newInstance(); AtendimentoVO retornoRegContato = AtendimentoVO.Factory.newInstance();
     * ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
     * atendimentoVO.setIdLinhaAtendimento((parametros.getIdLinhaAtendimento() !=
     * null&&!parametros.getIdLinhaAtendimento
     * ().equals(ConstantesCRM.SZERO))?parametros.getIdLinhaAtendimento():parametros.getIdLinha());
     * atendimentoVO.setIdUfOperadora(parametros.getIdUfOperadora() == null ||
     * parametros.getIdUfOperadora().equals(ConstantesCRM.SVAZIO)?ConstantesCRM.SONE:parametros.getIdUfOperadora());
     * atendimentoVO.setIdTipoLinha(parametros.getIdTipoLinha() == null ||
     * parametros.getIdTipoLinha().equals(ConstantesCRM.SVAZIO)?ConstantesCRM.SONE:parametros.getIdTipoLinha());
     * atendimentoVO.setIdChamadaTelefonica(Integer.parseInt(parametros.getIdChamadaTelefonica() == null ||
     * parametros.getIdChamadaTelefonica
     * ().equals(ConstantesCRM.SVAZIO)?ConstantesCRM.SONE:parametros.getIdChamadaTelefonica()));
     * atendimentoVO.setIdGrupoAbertura(Integer.parseInt(parametros.getIdGrupo() ==
     * null?ConstantesCRM.SONE:parametros.getIdGrupo()));
     * atendimentoVO.setInResponsavelAbertura(parametros.getIdTipoRelacionamento()==
     * null?ConstantesCRM.SONE:parametros.getIdTipoRelacionamento());
     * atendimentoVO.setInTipoPessoa(parametros.getInTipoPessoa() == null ||
     * parametros.getInTipoPessoa().equals(ConstantesCRM.SVAZIO)?PF:parametros.getInTipoPessoa()); if (nrLinha!=null
     * && !ConstantesCRM.SVAZIO.equals(nrLinha)) { atendimentoVO.setNrTelefone(nrLinha); } else {
     * atendimentoVO.setNrTelefone(parametros.getNrLinha() == null?"1100000000":parametros.getNrLinha()); }
     * atendimentoVO.setTpOperacao(ConstantesCRM.SONE);/ * tpOperacao: 1=fechar, 2=encaminhar * /
     * atendimentoVO.addNewProcedenciaVO().setIdProcedencia(1);/ * Procedência: TELEFONE - ID 1 * /
     * atendimentoVO.addNewCanalVO().setIdCanal(1);/ * Canal: CRC - ID 1 * /
     * if (idLinha!=null && !ConstantesCRM.SVAZIO.equals(idLinha)) {
     * atendimentoVO.addNewContas().addNewContaVO().addNewLinhaVO().setIdPessoaLinhaHistorico
     * (Integer.parseInt(idLinha)); } else if (parametros.getIdLinha() != null) { if
     * (!ConstantesCRM.SVAZIO.equals(parametros.getIdLinha())) {
     * atendimentoVO.addNewContas().addNewContaVO().addNewLinhaVO().setIdPessoaLinhaHistorico
     * (Integer.parseInt(parametros.getIdLinha())); } }
     * atendimentoVO.addNewPessoaVO().setIdPessoa(Integer.parseInt(parametros.getIdClienteDePara() ==
     * null?"21":parametros.getIdClienteDePara()));
     * atendimentoVO.addNewUsuarioLinhaVO().setIdPessoa(parametros.getIdUsuarioDePara() ==
     * null?"21":parametros.getIdUsuarioDePara()); atendimentoVO.addNewArvoreAtendimentoVO().setNmPath(nmPath);
     * atendimentoVO
     * .getArvoreAtendimentoVO().addNewCarterizacaoVO().setIdTipoCarteira(Integer.parseInt(parametros.getIdTipoCarteira
     * () == null ||
     * parametros.getIdTipoCarteira().equals(ConstantesCRM.SVAZIO)?ConstantesCRM.SONE:parametros.getIdTipoCarteira()));
     * atendimentoVO
     * .getArvoreAtendimentoVO().addNewSegmentacaoVO().setIdSegmentacao(Integer.parseInt((parametros.getIdSegmentacao()
     * == null ||
     * parametros.getIdSegmentacao().equals(ConstantesCRM.SVAZIO))?ConstantesCRM.SONE:parametros.getIdSegmentacao()));
     * String nrProtocolo = (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO);
     * DadosProtocoloVO dadosProtocoloVO = DadosProtocoloVO.Factory.newInstance();
     * dadosProtocoloVO.setIdTipoAberturaProtocolo(ConstantesCRM.STWO); dadosProtocoloVO.setIdSistemaOrigemProtocolo("7");
     * dadosProtocoloVO.setDddSMSProtocolo(nrLinha.substring(0,2));
     * dadosProtocoloVO.setNrLinhaSMSProtocolo(nrLinha.substring(2));
     * dadosProtocoloVO.setNrProtocolo(nrProtocolo!=null?nrProtocolo:ConstantesCRM.SVAZIO);
     * atendimentoVO.setDadosProtocoloVO(dadosProtocoloVO); atendimentoVO.setNrProtocolo(ConstantesCRM.SVAZIO);
     * atendimentoVO.setObservacao(dsObservacao); try { retornoRegContato =
     * registrarContatoFacade.registrarAtendimento(user, atendimentoVO); } catch(Exception e) { return (e.getMessage());
     * } return retornoRegContato.getNrProtocolo(); }
     */

    private String registraPalitagemEncaminhamento(String idLinha, String nrLinha, String nmPath, String dsObservacao, HttpServletRequest request) {
        String user = ConstantesCRM.getCurrentUser(request.getSession());
        AtendimentoVO atendimentoVO = AtendimentoVO.Factory.newInstance();
        AtendimentoVO retornoRegContato = AtendimentoVO.Factory.newInstance();
        ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);

        try {
            ParametroVO apoioParam = telaInicialFacade.getParametro(user, parametro_grp_abertura);
            String grupoAbertura = apoioParam.getDsValorParametro();
            atendimentoVO.setIdGrupoAbertura(Long.parseLong(grupoAbertura));
        
        } catch (Exception e) {
            atendimentoVO.setIdGrupoAbertura(Long.parseLong(parametros.getIdGrupo() == null ? ConstantesCRM.SONE : parametros.getIdGrupo()));
        }
        
        atendimentoVO.setIdLinhaAtendimento((parametros.getIdLinhaAtendimento() != null && !parametros.getIdLinhaAtendimento().equals(ConstantesCRM.SZERO)) ? parametros.getIdLinhaAtendimento() : parametros.getIdLinha());
        atendimentoVO.setIdUfOperadora(parametros.getIdUfOperadora() == null || parametros.getIdUfOperadora().equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SONE : parametros.getIdUfOperadora());
        atendimentoVO.setIdTipoLinha(parametros.getIdTipoLinha() == null || parametros.getIdTipoLinha().equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SONE : parametros.getIdTipoLinha());
        atendimentoVO.setIdChamadaTelefonica(Long.parseLong(parametros.getIdChamadaTelefonica() == null || parametros.getIdChamadaTelefonica().equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SONE : parametros.getIdChamadaTelefonica()));
        // atendimentoVO.setIdGrupoAbertura(Integer.parseInt(parametros.getIdGrupo() ==
        // null?ConstantesCRM.SONE:parametros.getIdGrupo()));
        atendimentoVO.setInResponsavelAbertura(parametros.getIdTipoRelacionamento() == null ? ConstantesCRM.SONE : parametros.getIdTipoRelacionamento());
        atendimentoVO.setInTipoPessoa(parametros.getInTipoPessoa() == null || parametros.getInTipoPessoa().equals(ConstantesCRM.SVAZIO) ? PF : parametros.getInTipoPessoa());
        
        if (nrLinha != null && !ConstantesCRM.SVAZIO.equals(nrLinha)) {
            atendimentoVO.setNrTelefone(nrLinha);
        } else {
            atendimentoVO.setNrTelefone(parametros.getNrLinha() == null ? "1100000000" : parametros.getNrLinha());
        }
        
        atendimentoVO.setTpOperacao(ConstantesCRM.STWO);/* tpOperacao: 1=fechar, 2=encaminhar */
        atendimentoVO.addNewProcedenciaVO().setIdProcedencia(1);/* Procedência: TELEFONE - ID 1 */
        atendimentoVO.addNewCanalVO().setIdCanal(1);/* Canal: CRC - ID 1 */

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
        atendimentoVO.getArvoreAtendimentoVO().addNewCarterizacaoVO()
                .setIdTipoCarteira(Integer.parseInt(parametros.getIdTipoCarteira() == null || parametros.getIdTipoCarteira().equals(ConstantesCRM.SVAZIO) ? ConstantesCRM.SONE : parametros.getIdTipoCarteira()));
        atendimentoVO.getArvoreAtendimentoVO().addNewSegmentacaoVO()
                .setIdSegmentacao(Integer.parseInt((parametros.getIdSegmentacao() == null || parametros.getIdSegmentacao().equals(ConstantesCRM.SVAZIO)) ? ConstantesCRM.SONE : parametros.getIdSegmentacao()));
        atendimentoVO.setNrProtocolo(ConstantesCRM.SVAZIO);
        atendimentoVO.setObservacao(dsObservacao);
        
        try {
            retornoRegContato = registrarContatoFacade.registrarAtendimento(user, atendimentoVO);
        
        } catch (Exception e) {
            return (e.getMessage());
        }
        return retornoRegContato.getNrProtocolo();
    }

    public static class PrePagoForm extends ActionForm {

        private static final long serialVersionUID     = 3802964101312675316L;

        private PrePagoVO         prePagoVO            = null;
        private String            tpOperacao           = ConstantesCRM.SVAZIO;
        private String            inSucesso            = ConstantesCRM.SVAZIO;
        private String            isNrLinhaValid       = ConstantesCRM.SVAZIO;

        private PrePagoVO         clientesPesquisados  = PrePagoVO.Factory.newInstance();

        // Dados Cliente PF
        private String            idPessoaCli          = ConstantesCRM.SVAZIO;
        private String            nmPessoaCli          = ConstantesCRM.SVAZIO;
        private String            idSexoCli            = ConstantesCRM.SVAZIO;
        private String            idDocumentoCliA      = ConstantesCRM.SVAZIO;
        private String            idDocumentoCliB      = ConstantesCRM.SVAZIO;
        private String            idTipoDocumentoCliA  = ConstantesCRM.SVAZIO;
        private String            idTipoDocumentoCliB  = ConstantesCRM.SVAZIO;
        private String            nrDocumentoCliA      = ConstantesCRM.SVAZIO;
        private String            nrDocumentoCliB      = ConstantesCRM.SVAZIO;
        private String            inDocumentoRGCli     = ConstantesCRM.SVAZIO;
        private String            idUFDocumentoCli     = ConstantesCRM.SVAZIO;
        private String            dsOrgaoEmissorCli    = ConstantesCRM.SVAZIO;
        private String            dtExpedicaoCli       = ConstantesCRM.SVAZIO;
        private String            dtNascimentoCli      = ConstantesCRM.SVAZIO;

        // Outros Dados (Cliente)
        private String            idContatoCliA        = ConstantesCRM.SVAZIO;
        private String            idContatoCliB        = ConstantesCRM.SVAZIO;
        private String            idContatoCliC        = ConstantesCRM.SVAZIO;

        private String            idTipoTelefoneCliA   = ConstantesCRM.SVAZIO;
        private String            idTipoTelefoneCliB   = ConstantesCRM.SVAZIO;
        private String            idTipoTelefoneCliC   = ConstantesCRM.SVAZIO;
        private String            nrTelefoneCliA       = ConstantesCRM.SVAZIO;
        private String            nrTelefoneCliB       = ConstantesCRM.SVAZIO;
        private String            nrTelefoneCliC       = ConstantesCRM.SVAZIO;
        private String            nmContatoCliA        = ConstantesCRM.SVAZIO;
        private String            nmContatoCliB        = ConstantesCRM.SVAZIO;
        private String            nmContatoCliC        = ConstantesCRM.SVAZIO;
        private String            nmEmailComercialCli  = ConstantesCRM.SVAZIO;
        private String            nmEmailParticularCli = ConstantesCRM.SVAZIO;
        private String            idEstadoCivilCli     = ConstantesCRM.SVAZIO;
        private String            idEscolaridadeCli    = ConstantesCRM.SVAZIO;
        private String            dsProfissaoCli       = ConstantesCRM.SVAZIO;
        private String            idNatOcupacaoCli     = ConstantesCRM.SVAZIO;
        private String            nrCPRCli             = ConstantesCRM.SVAZIO;

        // Dados Usuario PF
        private String            idPessoaUsu          = ConstantesCRM.SVAZIO;
        private String            nmPessoaUsu          = ConstantesCRM.SVAZIO;
        private String            idSexoUsu            = ConstantesCRM.SVAZIO;
        private String            idDocumentoUsuA      = ConstantesCRM.SVAZIO;
        private String            idDocumentoUsuB      = ConstantesCRM.SVAZIO;
        private String            idTipoDocumentoUsuA  = ConstantesCRM.SVAZIO;
        private String            idTipoDocumentoUsuB  = ConstantesCRM.SVAZIO;
        private String            nrDocumentoUsuA      = ConstantesCRM.SVAZIO;
        private String            nrDocumentoUsuB      = ConstantesCRM.SVAZIO;
        private String            inDocumentoRGUsu     = ConstantesCRM.SVAZIO;
        private String            idUFDocumentoUsu     = ConstantesCRM.SVAZIO;
        private String            dsOrgaoEmissorUsu    = ConstantesCRM.SVAZIO;
        private String            dtExpedicaoUsu       = ConstantesCRM.SVAZIO;
        private String            dtNascimentoUsu      = ConstantesCRM.SVAZIO;
        private String            inUtilEndCliente     = ConstantesCRM.SVAZIO;

        // Outros Dados do Usuario
        private String            idContatoUsuA        = ConstantesCRM.SVAZIO;
        private String            idContatoUsuB        = ConstantesCRM.SVAZIO;
        private String            idContatoUsuC        = ConstantesCRM.SVAZIO;

        private String            idTipoTelefoneUsuA   = ConstantesCRM.SVAZIO;
        private String            idTipoTelefoneUsuB   = ConstantesCRM.SVAZIO;
        private String            idTipoTelefoneUsuC   = ConstantesCRM.SVAZIO;
        private String            nrTelefoneUsuA       = ConstantesCRM.SVAZIO;
        private String            nrTelefoneUsuB       = ConstantesCRM.SVAZIO;
        private String            nrTelefoneUsuC       = ConstantesCRM.SVAZIO;
        private String            nmContatoUsuA        = ConstantesCRM.SVAZIO;
        private String            nmContatoUsuB        = ConstantesCRM.SVAZIO;
        private String            nmContatoUsuC        = ConstantesCRM.SVAZIO;
        private String            nmEmailParticularUsu = ConstantesCRM.SVAZIO;
        private String            nmEmailComercialUsu  = ConstantesCRM.SVAZIO;
        private String            idEstadoCivilUsu     = ConstantesCRM.SVAZIO;
        private String            idEscolaridadeUsu    = ConstantesCRM.SVAZIO;
        private String            dsProfissaoUsu       = ConstantesCRM.SVAZIO;
        private String            idNatOcupacaoUsu     = ConstantesCRM.SVAZIO;
        private String            nrCPRUsu             = ConstantesCRM.SVAZIO;

        // Dados Cliente PJ
        private String            idPessoaPJ           = ConstantesCRM.SVAZIO;
        private String            nrCNPJ               = ConstantesCRM.SVAZIO;
        private String            nmFantasia           = ConstantesCRM.SVAZIO;
        private String            nmRazaoSocial        = ConstantesCRM.SVAZIO;
        private String            nrCNAE               = ConstantesCRM.SVAZIO;
        private String            idTipoInscricao      = ConstantesCRM.SVAZIO;
        private String            nrInscricao          = ConstantesCRM.SVAZIO;
        private String            idClassEmpresa       = ConstantesCRM.SVAZIO;
        private String            idClassTributaria    = ConstantesCRM.SVAZIO;
        private String            dtFundacao           = ConstantesCRM.SVAZIO;
        private String            nrCCM                = ConstantesCRM.SVAZIO;

        private String            idContatoPJA         = ConstantesCRM.SVAZIO;
        private String            idTipoTelefonePJ     = ConstantesCRM.SVAZIO;
        private String            nrTelefonePJ         = ConstantesCRM.SVAZIO;
        private String            nmContatoPJ          = ConstantesCRM.SVAZIO;

        // Controle
        private String            idLinha              = ConstantesCRM.SVAZIO;
        private String            nrLinha              = ConstantesCRM.SVAZIO;
        private String            idPessoa             = ConstantesCRM.SVAZIO;
        private String            inTipoPessoa         = ConstantesCRM.SVAZIO;
        private String            inUsuario            = ConstantesCRM.SVAZIO;
        private String            cdSeguranca          = ConstantesCRM.SVAZIO;
        private String            nmCliente            = ConstantesCRM.SVAZIO;
        private String            tpDocumento          = ConstantesCRM.SVAZIO;
        private String            nrDocumento          = ConstantesCRM.SVAZIO;
        private String            inSMSCli             = ConstantesCRM.SVAZIO;

        // IDs Listas
        private String            idSexo               = ConstantesCRM.SVAZIO;
        private String            idTipoDocumentoA     = ConstantesCRM.SVAZIO;
        private String            idTipoDocumentoB     = ConstantesCRM.SVAZIO;
        private String            idUFDocumento        = ConstantesCRM.SVAZIO;
        private String            idTipoEndereco       = ConstantesCRM.SVAZIO;
        private String            idUFEndereco         = ConstantesCRM.SVAZIO;
        private String            idTipoTelefoneA      = ConstantesCRM.SVAZIO;
        private String            idTipoTelefoneB      = ConstantesCRM.SVAZIO;
        private String            idTipoTelefoneC      = ConstantesCRM.SVAZIO;
        private String            idEstadoCivil        = ConstantesCRM.SVAZIO;
        private String            idEscolaridade       = ConstantesCRM.SVAZIO;
        private String            idNatOcupacao        = ConstantesCRM.SVAZIO;

        private PesquisaTIVO      listaClientes;
        private String            hasNext;
        private String            pageNumber;

        public PrePagoForm() {
            this.prePagoVO = PrePagoVO.Factory.newInstance();
            listaClientes = PesquisaTIVO.Factory.newInstance();
        }

        public PrePagoVO getClientesPesquisados() {
            if (this.clientesPesquisados == null) {
                this.clientesPesquisados = PrePagoVO.Factory.newInstance();
            }
            return this.clientesPesquisados;
        }

        public void setClientesPesquisados(PrePagoVO arg) {
            this.clientesPesquisados = arg;
        }

        public void setIsNrLinhaValid(String isNrLinhaValid) {
            this.isNrLinhaValid = isNrLinhaValid;
        }

        public String getIsNrLinhaValid() {
            return this.isNrLinhaValid;
        }

        public void setInSMSCli(String inSMSCli) {
            this.inSMSCli = inSMSCli;
        }

        public String getInSMSCli() {
            return this.inSMSCli;
        }

        public void setTpOperacao(String tpOperacao) {
            this.tpOperacao = tpOperacao;
        }

        public String getTpOperacao() {
            return this.tpOperacao;
        }

        public void setInSucesso(String inSucesso) {
            this.inSucesso = inSucesso;
        }

        public String getInSucesso() {
            return this.inSucesso;
        }

        public void setIdLinha(String idLinha) {
            this.idLinha = idLinha;
        }

        public String getIdLinha() {
            return this.idLinha;
        }

        public void setNrLinha(String nrLinha) {
            this.nrLinha = nrLinha;
        }

        public String getNrLinha() {
            return this.nrLinha;
        }

        public void setIdPessoa(String idPessoa) {
            this.idPessoa = idPessoa;
        }

        public String getIdPessoa() {
            return this.idPessoa;
        }

        public void setInTipoPessoa(String inTipoPessoa) {
            this.inTipoPessoa = inTipoPessoa;
        }

        public String getInTipoPessoa() {
            return this.inTipoPessoa;
        }

        public void setInUsuario(String inUsuario) {
            this.inUsuario = inUsuario;
        }

        public String getInUsuario() {
            return this.inUsuario;
        }

        public void setCdSeguranca(String cdSeguranca) {
            this.cdSeguranca = cdSeguranca;
        }

        public String getCdSeguranca() {
            return this.cdSeguranca;
        }

        public void setNmCliente(String nmCliente) {
            this.nmCliente = nmCliente;
        }

        public String getNmCliente() {
            return this.nmCliente;
        }

        public void setTpDocumento(String tpDocumento) {
            this.tpDocumento = tpDocumento;
        }

        public String getTpDocumento() {
            return this.tpDocumento;
        }

        public void setNrDocumento(String nrDocumento) {
            this.nrDocumento = nrDocumento;
        }

        public String getNrDocumento() {
            return this.nrDocumento;
        }

        // Metodos Get/Set IDs Listas
        public void setIdSexo(String idSexo) {
            this.idSexo = idSexo;
        }

        public String getIdSexo() {
            return this.idSexo;
        }

        public void setIdTipoDocumentoA(String idTipoDocumentoA) {
            this.idTipoDocumentoA = idTipoDocumentoA;
        }

        public String getIdTipoDocumentoA() {
            return this.idTipoDocumentoA;
        }

        public void setIdTipoDocumentoB(String idTipoDocumentoB) {
            this.idTipoDocumentoB = idTipoDocumentoB;
        }

        public String getIdTipoDocumentoB() {
            return this.idTipoDocumentoB;
        }

        public void setIdUFDocumento(String idUFDocumento) {
            this.idUFDocumento = idUFDocumento;
        }

        public String getIdUFDocumento() {
            return this.idUFDocumento;
        }

        public void setIdTipoEndereco(String idTipoEndereco) {
            this.idTipoEndereco = idTipoEndereco;
        }

        public String getIdTipoEndereco() {
            return this.idTipoEndereco;
        }

        public void setIdUFEndereco(String idUFEndereco) {
            this.idUFEndereco = idUFEndereco;
        }

        public String getIdUFEndereco() {
            return this.idUFEndereco;
        }

        public void setIdTipoTelefoneA(String idTipoTelefoneA) {
            this.idTipoTelefoneA = idTipoTelefoneA;
        }

        public String getIdTipoTelefoneA() {
            return this.idTipoTelefoneA;
        }

        public void setIdTipoTelefoneB(String idTipoTelefoneB) {
            this.idTipoTelefoneB = idTipoTelefoneB;
        }

        public String getIdTipoTelefoneB() {
            return this.idTipoTelefoneB;
        }

        public void setIdTipoTelefoneC(String idTipoTelefoneC) {
            this.idTipoTelefoneC = idTipoTelefoneC;
        }

        public String getIdTipoTelefoneC() {
            return this.idTipoTelefoneC;
        }

        public void setIdEstadoCivil(String idEstadoCivil) {
            this.idEstadoCivil = idEstadoCivil;
        }

        public String getIdEstadoCivil() {
            return this.idEstadoCivil;
        }

        public void setIdEscolaridade(String idEscolaridade) {
            this.idEscolaridade = idEscolaridade;
        }

        public String getIdEscolaridade() {
            return this.idEscolaridade;
        }

        public void setIdNatOcupacao(String idNatOcupacao) {
            this.idNatOcupacao = idNatOcupacao;
        }

        public String getIdNatOcupacao() {
            return this.idNatOcupacao;
        }

        // Metodos Get/Set Pessoa Juridica (PJ)
        public void setIdPessoaPJ(String idPessoaPJ) {
            this.idPessoaPJ = idPessoaPJ;
        }

        public String getIdPessoaPJ() {
            return this.idPessoaPJ;
        }

        public void setNrCNPJ(String nrCNPJ) {
            this.nrCNPJ = nrCNPJ;
        }

        public String getNrCNPJ() {
            return this.nrCNPJ;
        }

        public void setNmFantasia(String nmFantasia) {
            this.nmFantasia = nmFantasia;
        }

        public String getNmFantasia() {
            return this.nmFantasia;
        }

        public void setNmRazaoSocial(String nmRazaoSocial) {
            this.nmRazaoSocial = nmRazaoSocial;
        }

        public String getNmRazaoSocial() {
            return this.nmRazaoSocial;
        }

        public void setNrCNAE(String nrCNAE) {
            this.nrCNAE = nrCNAE;
        }

        public String getNrCNAE() {
            return this.nrCNAE;
        }

        public void setIdTipoInscricao(String idTipoInscricao) {
            this.idTipoInscricao = idTipoInscricao;
        }

        public String getIdTipoInscricao() {
            return this.idTipoInscricao;
        }

        public void setNrInscricao(String nrInscricao) {
            this.nrInscricao = nrInscricao;
        }

        public String getNrInscricao() {
            return this.nrInscricao;
        }

        public void setIdClassTributaria(String idClassTributaria) {
            this.idClassTributaria = idClassTributaria;
        }

        public String getIdClassTributaria() {
            return this.idClassTributaria;
        }

        public void setIdClassEmpresa(String idClassEmpresa) {
            this.idClassEmpresa = idClassEmpresa;
        }

        public String getIdClassEmpresa() {
            return this.idClassEmpresa;
        }

        public void setDtFundacao(String dtFundacao) {
            this.dtFundacao = dtFundacao;
        }

        public String getDtFundacao() {
            return this.dtFundacao;
        }

        public void setNrCCM(String nrCCM) {
            this.nrCCM = nrCCM;
        }

        public String getNrCCM() {
            return this.nrCCM;
        }

        public void setIdContatoPJA(String idContatoPJA) {
            this.idContatoPJA = idContatoPJA;
        }

        public String getIdContatoPJA() {
            return this.idContatoPJA;
        }

        public void setIdTipoTelefonePJ(String idTipoTelefonePJ) {
            this.idTipoTelefonePJ = idTipoTelefonePJ;
        }

        public String getIdTipoTelefonePJ() {
            return this.idTipoTelefonePJ;
        }

        public void setNrTelefonePJ(String nrTelefonePJ) {
            this.nrTelefonePJ = nrTelefonePJ;
        }

        public String getNrTelefonePJ() {
            return this.nrTelefonePJ;
        }

        public void setNmContatoPJ(String nmContatoPJ) {
            this.nmContatoPJ = nmContatoPJ;
        }

        public String getNmContatoPJ() {
            return this.nmContatoPJ;
        }

        // Metodos Get/Set Pessoa Fisica Cliente
        public void setIdPessoaCli(String idPessoaCli) {
            this.idPessoaCli = idPessoaCli;
        }

        public String getIdPessoaCli() {
            return this.idPessoaCli;
        }

        public void setNmPessoaCli(String nmPessoaCli) {
            this.nmPessoaCli = nmPessoaCli;
        }

        public String getNmPessoaCli() {
            return this.nmPessoaCli;
        }

        public void setIdSexoCli(String idSexoCli) {
            this.idSexoCli = idSexoCli;
        }

        public String getIdSexoCli() {
            return this.idSexoCli;
        }

        public void setIdDocumentoCliA(String idDocumentoCliA) {
            this.idDocumentoCliA = idDocumentoCliA;
        }

        public String getIdDocumentoCliA() {
            return this.idDocumentoCliA;
        }

        public void setIdDocumentoCliB(String idDocumentoCliB) {
            this.idDocumentoCliB = idDocumentoCliB;
        }

        public String getIdDocumentoCliB() {
            return this.idDocumentoCliB;
        }

        public void setIdTipoDocumentoCliA(String idTipoDocumentoCliA) {
            this.idTipoDocumentoCliA = idTipoDocumentoCliA;
        }

        public String getIdTipoDocumentoCliA() {
            return this.idTipoDocumentoCliA;
        }

        public void setIdTipoDocumentoCliB(String idTipoDocumentoCliB) {
            this.idTipoDocumentoCliB = idTipoDocumentoCliB;
        }

        public String getIdTipoDocumentoCliB() {
            return this.idTipoDocumentoCliB;
        }

        public void setNrDocumentoCliA(String nrDocumentoCliA) {
            this.nrDocumentoCliA = nrDocumentoCliA;
        }

        public String getNrDocumentoCliA() {
            return this.nrDocumentoCliA;
        }

        public void setNrDocumentoCliB(String nrDocumentoCliB) {
            this.nrDocumentoCliB = nrDocumentoCliB;
        }

        public String getNrDocumentoCliB() {
            return this.nrDocumentoCliB;
        }

        public void setInDocumentoRGCli(String inDocumentoRGCli) {
            this.inDocumentoRGCli = inDocumentoRGCli;
        }

        public String getInDocumentoRGCli() {
            return this.inDocumentoRGCli;
        }

        public void setDtExpedicaoCli(String dtExpedicao) {
            this.dtExpedicaoCli = dtExpedicao;
        }

        public String getDtExpedicaoCli() {
            return this.dtExpedicaoCli;
        }

        public void setDsOrgaoEmissorCli(String dsOrgaoEmissor) {
            this.dsOrgaoEmissorCli = dsOrgaoEmissor;
        }

        public String getDsOrgaoEmissorCli() {
            return this.dsOrgaoEmissorCli;
        }

        public void setIdUFDocumentoCli(String idUFDocumentoCli) {
            this.idUFDocumentoCli = idUFDocumentoCli;
        }

        public String getIdUFDocumentoCli() {
            return this.idUFDocumentoCli;
        }

        public void setDtNascimentoCli(String dtNascimentoCli) {
            this.dtNascimentoCli = dtNascimentoCli;
        }

        public String getDtNascimentoCli() {
            return this.dtNascimentoCli;
        }

        // Metodos Get/Set Pessoa Fisica Usuario
        public void setIdPessoaUsu(String idPessoaUsu) {
            this.idPessoaUsu = idPessoaUsu;
        }

        public String getIdPessoaUsu() {
            return this.idPessoaUsu;
        }

        public void setNmPessoaUsu(String nmPessoaUsu) {
            this.nmPessoaUsu = nmPessoaUsu;
        }

        public String getNmPessoaUsu() {
            return this.nmPessoaUsu;
        }

        public void setIdSexoUsu(String idSexoUsu) {
            this.idSexoUsu = idSexoUsu;
        }

        public String getIdSexoUsu() {
            return this.idSexoUsu;
        }

        public void setIdDocumentoUsuA(String idDocumentoUsuA) {
            this.idDocumentoUsuA = idDocumentoUsuA;
        }

        public String getIdDocumentoUsuA() {
            return this.idDocumentoUsuA;
        }

        public void setIdDocumentoUsuB(String idDocumentoUsuB) {
            this.idDocumentoUsuB = idDocumentoUsuB;
        }

        public String getIdDocumentoUsuB() {
            return this.idDocumentoUsuB;
        }

        public void setIdTipoDocumentoUsuA(String idTipoDocumentoUsuA) {
            this.idTipoDocumentoUsuA = idTipoDocumentoUsuA;
        }

        public String getIdTipoDocumentoUsuA() {
            return this.idTipoDocumentoUsuA;
        }

        public void setIdTipoDocumentoUsuB(String idTipoDocumentoUsuB) {
            this.idTipoDocumentoUsuB = idTipoDocumentoUsuB;
        }

        public String getIdTipoDocumentoUsuB() {
            return this.idTipoDocumentoUsuB;
        }

        public void setNrDocumentoUsuA(String nrDocumentoUsuA) {
            this.nrDocumentoUsuA = nrDocumentoUsuA;
        }

        public String getNrDocumentoUsuA() {
            return this.nrDocumentoUsuA;
        }

        public void setNrDocumentoUsuB(String nrDocumentoUsuB) {
            this.nrDocumentoUsuB = nrDocumentoUsuB;
        }

        public String getNrDocumentoUsuB() {
            return this.nrDocumentoUsuB;
        }

        public void setInDocumentoRGUsu(String inDocumentoRGUsu) {
            this.inDocumentoRGUsu = inDocumentoRGUsu;
        }

        public String getInDocumentoRGUsu() {
            return this.inDocumentoRGUsu;
        }

        public void setDtExpedicaoUsu(String dtExpedicao) {
            this.dtExpedicaoUsu = dtExpedicao;
        }

        public String getDtExpedicaoUsu() {
            return this.dtExpedicaoUsu;
        }

        public void setDsOrgaoEmissorUsu(String dsOrgaoEmissor) {
            this.dsOrgaoEmissorUsu = dsOrgaoEmissor;
        }

        public String getDsOrgaoEmissorUsu() {
            return this.dsOrgaoEmissorUsu;
        }

        public void setIdUFDocumentoUsu(String idUFDocumentoUsu) {
            this.idUFDocumentoUsu = idUFDocumentoUsu;
        }

        public String getIdUFDocumentoUsu() {
            return this.idUFDocumentoUsu;
        }

        public void setDtNascimentoUsu(String dtNascimentoUsu) {
            this.dtNascimentoUsu = dtNascimentoUsu;
        }

        public String getDtNascimentoUsu() {
            return this.dtNascimentoUsu;
        }

        public void setInUtilEndCliente(String inUtilEndCliente) {
            this.inUtilEndCliente = inUtilEndCliente;
        }

        public String getInUtilEndCliente() {
            return this.inUtilEndCliente;
        }

        // Outros Dados (Cliente)
        public void setIdContatoCliA(String idContatoCliA) {
            this.idContatoCliA = idContatoCliA;
        }

        public String getIdContatoCliA() {
            return this.idContatoCliA;
        }

        public void setIdContatoCliB(String idContatoCliB) {
            this.idContatoCliB = idContatoCliB;
        }

        public String getIdContatoCliB() {
            return this.idContatoCliB;
        }

        public void setIdContatoCliC(String idContatoCliC) {
            this.idContatoCliC = idContatoCliC;
        }

        public String getIdContatoCliC() {
            return this.idContatoCliC;
        }

        public void setIdTipoTelefoneCliA(String idTipoTelefoneCliA) {
            this.idTipoTelefoneCliA = idTipoTelefoneCliA;
        }

        public String getIdTipoTelefoneCliA() {
            return this.idTipoTelefoneCliA;
        }

        public void setIdTipoTelefoneCliB(String idTipoTelefoneCliB) {
            this.idTipoTelefoneCliB = idTipoTelefoneCliB;
        }

        public String getIdTipoTelefoneCliB() {
            return this.idTipoTelefoneCliB;
        }

        public void setIdTipoTelefoneCliC(String idTipoTelefoneCliC) {
            this.idTipoTelefoneCliC = idTipoTelefoneCliC;
        }

        public String getIdTipoTelefoneCliC() {
            return this.idTipoTelefoneCliC;
        }

        public void setNrTelefoneCliA(String nrTelefoneCliA) {
            this.nrTelefoneCliA = nrTelefoneCliA;
        }

        public String getNrTelefoneCliA() {
            return this.nrTelefoneCliA;
        }

        public void setNrTelefoneCliB(String nrTelefoneCliB) {
            this.nrTelefoneCliB = nrTelefoneCliB;
        }

        public String getNrTelefoneCliB() {
            return this.nrTelefoneCliB;
        }

        public void setNrTelefoneCliC(String nrTelefoneCliC) {
            this.nrTelefoneCliC = nrTelefoneCliC;
        }

        public String getNrTelefoneCliC() {
            return this.nrTelefoneCliC;
        }

        public void setNmContatoCliA(String nmContatoCliA) {
            this.nmContatoCliA = nmContatoCliA;
        }

        public String getNmContatoCliA() {
            return this.nmContatoCliA;
        }

        public void setNmContatoCliB(String nmContatoCliB) {
            this.nmContatoCliB = nmContatoCliB;
        }

        public String getNmContatoCliB() {
            return this.nmContatoCliB;
        }

        public void setNmContatoCliC(String nmContatoCliC) {
            this.nmContatoCliC = nmContatoCliC;
        }

        public String getNmContatoCliC() {
            return this.nmContatoCliC;
        }

        public void setNmEmailParticularCli(String nmEmailParticularCli) {
            this.nmEmailParticularCli = nmEmailParticularCli;
        }

        public String getNmEmailParticularCli() {
            return this.nmEmailParticularCli;
        }

        public void setNmEmailComercialCli(String nmEmailComercialCli) {
            this.nmEmailComercialCli = nmEmailComercialCli;
        }

        public String getNmEmailComercialCli() {
            return this.nmEmailComercialCli;
        }

        public void setDsProfissaoCli(String dsProfissaoCli) {
            this.dsProfissaoCli = dsProfissaoCli;
        }

        public String getDsProfissaoCli() {
            return this.dsProfissaoCli;
        }

        public void setIdEstadoCivilCli(String idEstadoCivilCli) {
            this.idEstadoCivilCli = idEstadoCivilCli;
        }

        public String getIdEstadoCivilCli() {
            return this.idEstadoCivilCli;
        }

        public void setIdEscolaridadeCli(String idEscolaridadeCli) {
            this.idEscolaridadeCli = idEscolaridadeCli;
        }

        public String getIdEscolaridadeCli() {
            return this.idEscolaridadeCli;
        }

        public void setIdNatOcupacaoCli(String idNatOcupacaoCli) {
            this.idNatOcupacaoCli = idNatOcupacaoCli;
        }

        public String getIdNatOcupacaoCli() {
            return this.idNatOcupacaoCli;
        }

        public void setNrCPRCli(String nrCPRCli) {
            this.nrCPRCli = nrCPRCli;
        }

        public String getNrCPRCli() {
            return this.nrCPRCli;
        }

        // Outros Dados do Usuario
        public void setIdContatoUsuA(String idContatoUsuA) {
            this.idContatoUsuA = idContatoUsuA;
        }

        public String getIdContatoUsuA() {
            return this.idContatoUsuA;
        }

        public void setIdContatoUsuB(String idContatoUsuB) {
            this.idContatoUsuB = idContatoUsuB;
        }

        public String getIdContatoUsuB() {
            return this.idContatoUsuB;
        }

        public void setIdContatoUsuC(String idContatoUsuC) {
            this.idContatoUsuC = idContatoUsuC;
        }

        public String getIdContatoUsuC() {
            return this.idContatoUsuC;
        }

        public void setIdTipoTelefoneUsuA(String idTipoTelefoneUsuA) {
            this.idTipoTelefoneUsuA = idTipoTelefoneUsuA;
        }

        public String getIdTipoTelefoneUsuA() {
            return this.idTipoTelefoneUsuA;
        }

        public void setIdTipoTelefoneUsuB(String idTipoTelefoneUsuB) {
            this.idTipoTelefoneUsuB = idTipoTelefoneUsuB;
        }

        public String getIdTipoTelefoneUsuB() {
            return this.idTipoTelefoneUsuB;
        }

        public void setIdTipoTelefoneUsuC(String idTipoTelefoneUsuC) {
            this.idTipoTelefoneUsuC = idTipoTelefoneUsuC;
        }

        public String getIdTipoTelefoneUsuC() {
            return this.idTipoTelefoneUsuC;
        }

        public void setNrTelefoneUsuA(String nrTelefoneUsuA) {
            this.nrTelefoneUsuA = nrTelefoneUsuA;
        }

        public String getNrTelefoneUsuA() {
            return this.nrTelefoneUsuA;
        }

        public void setNrTelefoneUsuB(String nrTelefoneUsuB) {
            this.nrTelefoneUsuB = nrTelefoneUsuB;
        }

        public String getNrTelefoneUsuB() {
            return this.nrTelefoneUsuB;
        }

        public void setNrTelefoneUsuC(String nrTelefoneUsuC) {
            this.nrTelefoneUsuC = nrTelefoneUsuC;
        }

        public String getNrTelefoneUsuC() {
            return this.nrTelefoneUsuC;
        }

        public void setNmContatoUsuA(String nmContatoUsuA) {
            this.nmContatoUsuA = nmContatoUsuA;
        }

        public String getNmContatoUsuA() {
            return this.nmContatoUsuA;
        }

        public void setNmContatoUsuB(String nmContatoUsuB) {
            this.nmContatoUsuB = nmContatoUsuB;
        }

        public String getNmContatoUsuB() {
            return this.nmContatoUsuB;
        }

        public void setNmContatoUsuC(String nmContatoUsuC) {
            this.nmContatoUsuC = nmContatoUsuC;
        }

        public String getNmContatoUsuC() {
            return this.nmContatoUsuC;
        }

        public void setNmEmailComercialUsu(String nmEmailComercialUsu) {
            this.nmEmailComercialUsu = nmEmailComercialUsu;
        }

        public String getNmEmailComercialUsu() {
            return this.nmEmailComercialUsu;
        }

        public void setNmEmailParticularUsu(String nmEmailParticularUsu) {
            this.nmEmailParticularUsu = nmEmailParticularUsu;
        }

        public String getNmEmailParticularUsu() {
            return this.nmEmailParticularUsu;
        }

        public void setIdEstadoCivilUsu(String idEstadoCivilUsu) {
            this.idEstadoCivilUsu = idEstadoCivilUsu;
        }

        public String getIdEstadoCivilUsu() {
            return this.idEstadoCivilUsu;
        }

        public void setIdEscolaridadeUsu(String idEscolaridadeUsu) {
            this.idEscolaridadeUsu = idEscolaridadeUsu;
        }

        public String getIdEscolaridadeUsu() {
            return this.idEscolaridadeUsu;
        }

        public void setDsProfissaoUsu(String dsProfissaoUsu) {
            this.dsProfissaoUsu = dsProfissaoUsu;
        }

        public String getDsProfissaoUsu() {
            return this.dsProfissaoUsu;
        }

        public void setIdNatOcupacaoUsu(String idNatOcupacaoUsu) {
            this.idNatOcupacaoUsu = idNatOcupacaoUsu;
        }

        public String getIdNatOcupacaoUsu() {
            return this.idNatOcupacaoUsu;
        }

        public void setNrCPRUsu(String nrCPRUsu) {
            this.nrCPRUsu = nrCPRUsu;
        }

        public String getNrCPRUsu() {
            return this.nrCPRUsu;
        }

        // Metodo Get/Set Objeto Dados dos Clientes
        public void setPrePagoVO(PrePagoVO prePagoVO) {
            this.prePagoVO = prePagoVO;
        }

        public PrePagoVO getPrePagoVO() {
            if (this.prePagoVO == null) {
                this.prePagoVO = PrePagoVO.Factory.newInstance();
            }
            return this.prePagoVO;
        }

        public PesquisaTIVO getListaClientes() {
            return this.listaClientes;
        }

        public void setListaClientes(PesquisaTIVO arg) {
            this.listaClientes = arg;
        }

        public String getHasNext() {
            return this.hasNext;
        }

        public void setHasNext(String arg) {
            this.hasNext = arg;
        }

        public String getPageNumber() {
            if (this.pageNumber == null || ConstantesCRM.SVAZIO.equals(this.pageNumber)) {
                this.pageNumber = ConstantesCRM.SONE;
            }
            return this.pageNumber;
        }

        public void setPageNumber(String arg) {
            this.pageNumber = arg;
        }
    }

    public static class PrePagoEnderecoForm extends ActionForm {

        private static final long  serialVersionUID   = -2192404146965113796L;

        private String             inCodigoIBGE       = ConstantesCRM.SVAZIO;
        private String             inCnl              = ConstantesCRM.SVAZIO;
        private String             codLogradouro      = ConstantesCRM.SVAZIO;
        private String             idEndereco         = ConstantesCRM.SVAZIO;
        private String             inEndDefault       = ConstantesCRM.SVAZIO;
        private String             idTipoEndereco     = ConstantesCRM.SVAZIO;
        private String             nrCEP              = ConstantesCRM.SVAZIO;
        private String             inEndPrincipal     = ConstantesCRM.SVAZIO;
        private String             tpLogradouro       = ConstantesCRM.SVAZIO;
        private String             nmTitLogradouro    = ConstantesCRM.SVAZIO;
        private String             nmLogradouro       = ConstantesCRM.SVAZIO;
        private String             nrLogradouro       = ConstantesCRM.SVAZIO;
        private String             nmComplemento      = ConstantesCRM.SVAZIO;
        private String             nmBairro           = ConstantesCRM.SVAZIO;
        private String             nmMunicipio        = ConstantesCRM.SVAZIO;
        private String             idEndUF            = ConstantesCRM.SVAZIO;

        private PesquisaEnderecoVO pesquisaEnderecoVO = null;
        private PrePagoVO          prePagoVOEndereco  = null;
        private DadosEndereco[]    dadosEndereco      = null;

        private boolean            pesquisaEnderecoBaseFO;

        public PrePagoEnderecoForm() {
            this.idEndereco = ConstantesCRM.SVAZIO;
            this.inEndDefault = ConstantesCRM.SVAZIO;
            this.idTipoEndereco = ConstantesCRM.SVAZIO;
            this.nrCEP = ConstantesCRM.SVAZIO;
            this.inEndPrincipal = ConstantesCRM.SVAZIO;
            this.tpLogradouro = ConstantesCRM.SVAZIO;
            this.nmTitLogradouro = ConstantesCRM.SVAZIO;
            this.nmLogradouro = ConstantesCRM.SVAZIO;
            this.nrLogradouro = ConstantesCRM.SVAZIO;
            this.nmComplemento = ConstantesCRM.SVAZIO;
            this.nmBairro = ConstantesCRM.SVAZIO;
            this.nmMunicipio = ConstantesCRM.SVAZIO;
            this.idEndUF = ConstantesCRM.SVAZIO;
            this.codLogradouro = ConstantesCRM.SVAZIO;
            this.inCnl = ConstantesCRM.SVAZIO;
            this.inCodigoIBGE = ConstantesCRM.SVAZIO;
            this.pesquisaEnderecoBaseFO = true;

            this.pesquisaEnderecoVO = PesquisaEnderecoVO.Factory.newInstance();
            this.pesquisaEnderecoVO.addNewFiltroPesquisa();
            this.pesquisaEnderecoVO.addNewListaEnderecos();

            this.prePagoVOEndereco = PrePagoVO.Factory.newInstance();
            this.prePagoVOEndereco.addNewPF();
            this.prePagoVOEndereco.addNewPF();
            this.prePagoVOEndereco.addNewPJ();
        }

        public void setPrePagoVOEndereco(PrePagoVO prePagoVOEndereco) {
            this.prePagoVOEndereco = prePagoVOEndereco;
        }

        public PrePagoVO getPrePagoVOEndereco() {
            if (this.prePagoVOEndereco == null) {
                this.prePagoVOEndereco = PrePagoVO.Factory.newInstance();
                this.prePagoVOEndereco.addNewPF();
                this.prePagoVOEndereco.addNewPF();
                this.prePagoVOEndereco.addNewPJ();
            }
            return this.prePagoVOEndereco;
        }

        public void setPesquisaEnderecoBaseFO(boolean arg) {
            this.pesquisaEnderecoBaseFO = arg;
        }

        public boolean isPesquisaEnderecoBaseFO() {
            return this.pesquisaEnderecoBaseFO;
        }

        public void setDadosEndereco(DadosEndereco[] dadosEndereco) {
            this.dadosEndereco = dadosEndereco;
        }

        public DadosEndereco[] getDadosEndereco() {
            return this.dadosEndereco;
        }

        public void setPesquisaEnderecoVO(PesquisaEnderecoVO pesquisaEnderecoVO) {
            this.pesquisaEnderecoVO = pesquisaEnderecoVO;
        }

        public PesquisaEnderecoVO getPesquisaEnderecoVO() {
            if (this.pesquisaEnderecoVO == null) {
                this.pesquisaEnderecoVO = PesquisaEnderecoVO.Factory.newInstance();
            }
            return this.pesquisaEnderecoVO;
        }

        public void setIdEndereco(String idEndereco) {
            this.idEndereco = idEndereco;
        }

        public String getIdEndereco() {
            return this.idEndereco;
        }

        public void setInEndDefault(String inEndDefault) {
            this.inEndDefault = inEndDefault;
        }

        public String getInEndDefault() {
            return this.inEndDefault;
        }

        public void setIdTipoEndereco(String idTipoEndereco) {
            this.idTipoEndereco = idTipoEndereco;
        }

        public String getIdTipoEndereco() {
            return this.idTipoEndereco;
        }

        public void setNrCEP(String nrCEP) {
            this.nrCEP = nrCEP;
        }

        public String getNrCEP() {
            return this.nrCEP;
        }

        public void setInEndPrincipal(String inEndPrincipal) {
            this.inEndPrincipal = inEndPrincipal;
        }

        public String getInEndPrincipal() {
            return this.inEndPrincipal;
        }

        public void setTpLogradouro(String tpLogradouro) {
            this.tpLogradouro = tpLogradouro;
        }

        public String getTpLogradouro() {
            return this.tpLogradouro;
        }

        public void setNmTitLogradouro(String nmTitLogradouro) {
            this.nmTitLogradouro = nmTitLogradouro;
        }

        public String getNmTitLogradouro() {
            return this.nmTitLogradouro;
        }

        public void setNmLogradouro(String nmLogradouro) {
            this.nmLogradouro = nmLogradouro;
        }

        public String getNmLogradouro() {
            return this.nmLogradouro;
        }

        public void setNrLogradouro(String nrLogradouro) {
            this.nrLogradouro = nrLogradouro;
        }

        public String getNrLogradouro() {
            return this.nrLogradouro;
        }

        public void setNmComplemento(String nmComplemento) {
            this.nmComplemento = nmComplemento;
        }

        public String getNmComplemento() {
            return this.nmComplemento;
        }

        public void setNmBairro(String nmBairro) {
            this.nmBairro = nmBairro;
        }

        public String getNmBairro() {
            return this.nmBairro;
        }

        public void setNmMunicipio(String nmMunicipio) {
            this.nmMunicipio = nmMunicipio;
        }

        public String getNmMunicipio() {
            return this.nmMunicipio;
        }

        public void setIdEndUF(String idEndUF) {
            this.idEndUF = idEndUF;
        }

        public String getIdEndUF() {
            return this.idEndUF;
        }

        public void setCodLogradouro(String codLogradouro) {
            this.codLogradouro = codLogradouro;
        }

        public String getCodLogradouro() {
            return this.codLogradouro;
        }

        public void setInCnl(String inCnl) {
            this.inCnl = inCnl;
        }

        public String getInCnl() {
            return this.inCnl;
        }

        public void setInCodigoIBGE(String inCodigoIBGE) {
            this.inCodigoIBGE = inCodigoIBGE;
        }

        public String getInCodigoIBGE() {
            return this.inCodigoIBGE;
        }
    }

}
