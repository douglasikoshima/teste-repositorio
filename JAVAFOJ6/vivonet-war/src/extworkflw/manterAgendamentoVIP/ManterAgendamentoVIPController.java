package extworkflw.manterAgendamentoVIP;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import noNamespace.MsgDocument.Msg;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import weblogic.logging.NonCatalogLogger;
import br.com.vivo.fo.cliente.ClienteUtils;
import br.com.vivo.fo.cliente.vo.ListaUFVODocument.ListaUFVO;
import br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.cliente.vo.UFVODocument.UFVO;
import br.com.vivo.fo.commons.utils.GetParametro;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.AssuntoBean;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.CampoBean;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ManterAgendamentoVIPFacade;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ValorBean;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.db.ManterAgendamentoDB.LinhaPremmiun;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaAgendamentoPorLinha.output.AgendamentoType;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaArvoreAssuntos.output.AssuntoType;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaLojas.output.LojaType;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.registraAgendamento.input.RegistraAgendamentoInputType;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.registraAgendamento.output.RegistraAgendamentoOutputType;
import br.com.vivo.fo.ctrls.VOLTAV.manterTerminal.ManterTerminalFacade;
import br.com.vivo.fo.ctrls.cliente.telaInicial.TelaInicialFacade;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.vol.dados.vo.ConsultarTiposEmailVODocument.ConsultarTiposEmailVO;
import br.com.vivo.vol.dados.vo.EmailsDocument.Emails;
import br.com.vivo.vol.dados.vo.ListaAlertaDocument.ListaAlerta;
import br.com.vivo.vol.dados.vo.TiposEmailDocument.TiposEmail;
import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ManterAgendamentoVIPController extends AbstractAction {

    protected global.Global                   globalApp                                = new global.Global();

    private String                            user;

    private static transient NonCatalogLogger log                                      = new NonCatalogLogger(ManterAgendamentoVIPController.class.getName());

    private FormManterAgendamentoVIP          formManterAgendamentoVIP                 = null;

    private String                            REGISTROS_POR_PAGINA                     = "50";
    private int                               REGISTROS_POR_PAGINA_INT                 = 50;

    private static final String               PALITAGEM_ATENDIMENTO_AGENDADO           = "PALITAGEM_ATENDIMENTO_AGENDADO";
    private static final String               PALITAGEM_CANCELA_ATENDIMENTO_AGENDADO   = "PALITAGEM_CANCELA_ATENDIMENTO_AGENDADO";
    private static final String               PALITAGEM_ALTERACAO_ATENDIMENTO_AGENDADO = "PALITAGEM_ALTERACAO_ATENDIMENTO_AGENDADO";
    private static final String               PALITAGEM_ALTERACAO_ALERTA_AGENDADO      = "PALITAGEM_ALTERACAO_ALERTA_AGENDADO";
    private static final String               PALITAGEM_CONSULTA_ATENDIMENTO_AGENDADO  = "PALITAGEM_CONSULTA_ATENDIMENTO_AGENDADO";
    private static final String               PALITAGEM_FALHA_AGENDAMENTO              = "PALITAGEM_FALHA_AGENDAMENTO";
    private static final String               PALITAGEM_FALHA_CANCELA_AGENDAMENTO      = "PALITAGEM_FALHA_CANCELA_AGENDAMENTO";
    private static final String               PALITAGEM_FALHA_ALTERACAO_AGENDAMENTO    = "PALITAGEM_FALHA_ALTERACAO_AGENDAMENTO";

    @EJB
    private TelaInicialFacade                 telaInicialFacadeControl;

    @EJB
    private ManterAgendamentoVIPFacade        controlManterAgendamento2;

    @EJB
    private ManterTerminalFacade              controlManterTerminal;

    private AssuntoType[]                     arvoreAssuntos;

    private AssuntoBean                       assunto1;

    public AssuntoBean getAssunto1() {
        return assunto1;
    }

    private AssuntoBean assunto2;

    public AssuntoBean getAssunto2() {
        return assunto2;
    }

    private AssuntoBean assunto3;

    public AssuntoBean getAssunto3() {
        return assunto3;
    }

    private AssuntoBean assunto4;

    public AssuntoBean getAssunto4() {
        return assunto4;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if ("begin".equals(mapping.getParameter())) {
            return begin(mapping, form, request, response);
        } else if ("uploadArquivo".equals(mapping.getParameter())) {
            return uploadArquivo(mapping, form, request, response);
        } else if ("obterAssunto2".equals(mapping.getParameter())) {
            return obterAssunto2(mapping, form, request, response);
        } else if ("obterAssunto3".equals(mapping.getParameter())) {
            return obterAssunto3(mapping, form, request, response);
        } else if ("obterAssunto4".equals(mapping.getParameter())) {
            return obterAssunto4(mapping, form, request, response);
        } else if ("buscaAlteracaoAgendamento".equals(mapping.getParameter())) {
            return buscaAlteracaoAgendamento(mapping, form, request, response);
        } else if ("excluirAgendamento".equals(mapping.getParameter())) {
            return excluirAgendamento(mapping, form, request, response);
        } else if ("buscaAlertaAgendamento".equals(mapping.getParameter())) {
            return buscaAlertaAgendamento(mapping, form, request, response);
        } else if ("paginacao".equals(mapping.getParameter())) {
            return paginacao(mapping, form, request, response);
        } else if ("incluirLinha".equals(mapping.getParameter())) {
            return incluirLinha(mapping, form, request, response);
        } else if ("consultaAcessoAgendamentoVIP".equals(mapping.getParameter())) {
            return consultaAcessoAgendamentoVIP(mapping, form, request, response);
        } else if ("excluirLinha".equals(mapping.getParameter())) {
            return excluirLinha(mapping, form, request, response);
        } else if ("buscaMunicipioPorUf".equals(mapping.getParameter())) {
            return buscaMunicipioPorUf(mapping, form, request, response);
        } else if ("buscaLojaPorUfMunicipio".equals(mapping.getParameter())) {
            return buscaLojaPorUfMunicipio(mapping, form, request, response);
        } else if ("buscarHorarios".equals(mapping.getParameter())) {
            return buscarHorarios(mapping, form, request, response);
        } else if ("agendar".equals(mapping.getParameter())) {
            return agendar(mapping, form, request, response);
        } else if ("gravarAlerta".equals(mapping.getParameter())) {
            return gravarAlerta(mapping, form, request, response);
        }
        return begin(mapping, form, request, response);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="manterUpLoadAgendamentoVIP.jsp"
     */
    protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FormManterAgendamentoVIP form = (FormManterAgendamentoVIP) formParam;
        this.getFormManterAgendamentoVIP().setSemRegistros("false");

        String linhaParam = null;
        String ddd = null;
        String totalRegistro = ConstantesCRM.SZERO;

        this.getFormManterAgendamentoVIP().setDsTipoPesquisa(form.getDsTipoPesquisa());
        this.getFormManterAgendamentoVIP().setQueryPesquisa(form.getQueryPesquisa());

        if (form.getQueryPesquisa() != null && !ConstantesCRM.SVAZIO.equals(form.getQueryPesquisa()) && !ConstantesCRM.SZERO.equals(form.getQueryPesquisa())) {
            if ("linha".equals(form.getDsTipoPesquisa())) {
                linhaParam = form.getQueryPesquisa();
            }
            if ("ddd".equals(form.getDsTipoPesquisa())) {
                ddd = form.getQueryPesquisa();
            }
        }

        LinhaPremmiun[] linhasPremmiun = controlManterAgendamento2.getLinhasPremmiun(ConstantesCRM.SONE, REGISTROS_POR_PAGINA, ddd, linhaParam);

        this.getFormManterAgendamentoVIP().setLinhasPremmiun(linhasPremmiun);
        this.getFormManterAgendamentoVIP().setPaginaAtual(ConstantesCRM.SONE);

        LinhaPremmiun linha = controlManterAgendamento2.getTotalLinhasPremiun(ddd, linhaParam);
        totalRegistro = linha.getTotalRegistros();

        String totalPagina = String.valueOf((Integer.parseInt(totalRegistro) / Integer.parseInt(REGISTROS_POR_PAGINA)));
        int mod = (Integer.parseInt(totalRegistro) % Integer.parseInt(REGISTROS_POR_PAGINA));
        if (mod > 0) {
            totalPagina = String.valueOf((Integer.parseInt(totalPagina) + 1));
        }
        if (linhasPremmiun.length < 1) {
            // this.getFormManterAgendamentoVIP().setSemRegistros("true");
            totalPagina = ConstantesCRM.SZERO;
            getFormManterAgendamentoVIP().setPaginaAtual(ConstantesCRM.SZERO);
        }
        this.getFormManterAgendamentoVIP().setTotalPagina(totalPagina);
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="manterUpLoadAgendamentoVIP.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward uploadArquivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception, FileNotFoundException, IOException {
        FormManterAgendamentoVIP form = (FormManterAgendamentoVIP) formParam;
        try {
            String nmArquivo = new StringBuffer("FO_AVIP_").append(getData()).append("_").append(this.getUser(request)).append(".txt").toString();

            InputStream stream = form.getFile().getInputStream();
            int size = form.getFile().getFileSize();
            if (size == 0) {
                request.setAttribute("msgStatus", "Arquivo inválido.");
                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                return mapping.findForward(ConstantesCRM.SUCCESS);
            }
            ParametroVO parametro = telaInicialFacadeControl.getParametro(this.getUser(request), "PATH_AGENDAMENTO_VIP");

            log.info("!!!!!!!!!! PATH_AGENDAMENTO_VIP -> " + parametro.getDsValorParametro());

            String path = parametro.getDsValorParametro();
            if (path.charAt(path.length() - 1) != '/') {
                path += "/";
            }

            OutputStream bos = new FileOutputStream(path + nmArquivo);

            byte[] buffer = new byte[size];
            int bytesRead = 0;
            while ((bytesRead = stream.read(buffer, 0, size)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            bos.close();
            stream.close();

            request.setAttribute("msgStatus", "Arquivo enviado com sucesso.");
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);

        } catch (Exception e) {
            log.error("PontosContatoController:uploadArquivo(" + this.getUser(request) + ") - [" + e.getMessage() + "]", e);
            FormError formError = globalApp.buildFormError(e, request);
            formError.setTarget(ConstantesCRM.FRAMEAPP);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

    private String getData() {
        String DATE_FORMAT = "yyyyMMddHHmm";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Calendar c1 = Calendar.getInstance();
        return sdf.format(c1.getTime());
    }

    public String getUser(HttpServletRequest request) {
        String sUser = ConstantesCRM.getCurrentUser(request.getSession());
        NumberFormat nf = new DecimalFormat(ConstantesCRM.SZERO);
        nf.setMinimumIntegerDigits(15);
        long l = Long.valueOf(sUser).longValue();
        sUser = nf.format(l);
        return sUser;
    }

    /**
     * Monta a arvore de assuntos
     */
    private void obterArvoreAssuntos(HttpServletRequest request) throws FacadeException, Exception {
        arvoreAssuntos = controlManterAgendamento2.consultarAssuntos(this.getUser(request));
        assunto2 = null;
        assunto2 = null;
        assunto4 = null;

        // Obtém o assunto raiz
        assunto1 = new AssuntoBean();
        assunto1.setTipo("P");
        for (int i = 0; i < arvoreAssuntos.length; i++) {
            if (arvoreAssuntos[i].getCodigoPai() == null) {
                assunto1 = new AssuntoBean(arvoreAssuntos[i]);
                break;
            }
        }

        // Obtém o campo do assunto principal
        CampoBean campo = new CampoBean();
        campo.setTipo(assunto1.getTipo()); // P
        for (int j = 0; j < arvoreAssuntos.length; j++) {
            if (arvoreAssuntos[j].getCodigoPai() != null && arvoreAssuntos[j].getCodigoPai().equals(assunto1.getCodigo())) {
                campo.addValor(new ValorBean(arvoreAssuntos[j].getCodigo(), arvoreAssuntos[j].getDescricao()));
            }
        }
        assunto1.addCampo(campo);
        request.getSession().setAttribute("assunto1", assunto1);
    }

    private void lerAssunto(AssuntoBean assunto, int iAss, HttpServletRequest request) {
        for (int iCampo = 0;; iCampo++) {
            String pName = "assunto" + iAss + "{" + iCampo + "}";
            String pVal = request.getParameter(pName);
            if (pVal == null || pVal.length() == 0) {
                break;
            }
            ((CampoBean) assunto.getCampos().get(iCampo)).setValorSelecionado(pVal);
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="manterAgendamentoVIP.jsp"
     */
    protected ActionForward obterAssunto2(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        FormManterAgendamentoVIP form = (FormManterAgendamentoVIP) formParam;
        this.getFormManterAgendamentoVIP().setIdHorario(form.getIdHorario());

        lerAssunto(assunto1, 1, request);
        String codPai = ((CampoBean) assunto1.getCampos().get(0)).getValorSelecionado();

        // limpar variaveis de sessao
        request.getSession().setAttribute("assunto3", null);
        request.getSession().setAttribute("assunto4", null);

        assunto2 = null;
        assunto3 = null;
        assunto4 = null;

        if (thereIsFilho(codPai)) {
            assunto2 = new AssuntoBean();
            createArvoreAssunto(assunto2, codPai);
        }
        assunto2.setCodigo(((CampoBean) assunto2.getCampos().get(0)).getCodigo());

        request.getSession().setAttribute("assunto2", assunto2);

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @description Agrupa os campos no assuntoBean
     * @param
     * @return
     */
    public void createArvoreAssunto(AssuntoBean assunto, String codPai) {
        boolean hasChild = false;
        for (int i = 0; i < arvoreAssuntos.length; i++) {
            if (arvoreAssuntos[i].getCodigoPai() != null && arvoreAssuntos[i].getCodigoPai().equals(codPai)) {
                if ("P".equals(arvoreAssuntos[i].getTipo())) {
                    CampoBean campo = new CampoBean();
                    campo.setTipo("P");
                    campo.setNome(arvoreAssuntos[i].getDescricao());
                    campo.setCodigo(arvoreAssuntos[i].getCodigo());
                    if (thereIsFilho(campo.getCodigo())) {
                        hasChild = true;
                    }
                    assunto.addCampo(campo);
                }
            }
        }
        for (int i = 0; i < arvoreAssuntos.length; i++) {
            if (arvoreAssuntos[i].getCodigoPai() != null && arvoreAssuntos[i].getCodigoPai().equals(codPai)) {
                if ("C".equals(arvoreAssuntos[i].getTipo())) {
                    CampoBean campo = new CampoBean();
                    campo.setTipo("C");
                    campo.setNome(arvoreAssuntos[i].getDescricao());
                    if ("C".equals(arvoreAssuntos[i].getTipo())) {
                        String[] valores = arvoreAssuntos[i].getValores();
                        for (int j = 0; j < valores.length; j++) {
                            campo.addValor(new ValorBean(valores[j], valores[j]));
                        }
                    }
                    assunto.addCampo(campo);
                }
                /*
                 * else
                 * if("U".equals(arvoreAssuntos[i].getTipo())){
                 * CampoBean campo = new CampoBean();
                 * campo.setTipo("U");
                 * campo.addValor(new ValorBean(arvoreAssuntos[i].getCodigo(), arvoreAssuntos[i].getDescricao()));
                 * assunto.addCampo(campo);
                 * }
                 */
                else if ("T".equals(arvoreAssuntos[i].getTipo())) {
                    CampoBean campo = new CampoBean();
                    campo.setTipo("T");
                    campo.addValor(new ValorBean(arvoreAssuntos[i].getCodigo(), arvoreAssuntos[i].getDescricao()));
                    assunto.addCampo(campo);
                } else if ("A".equals(arvoreAssuntos[i].getTipo())) {
                    CampoBean campo = new CampoBean();
                    campo.setTipo("A");
                    campo.addValor(new ValorBean(arvoreAssuntos[i].getCodigo(), arvoreAssuntos[i].getDescricao()));
                    assunto.addCampo(campo);
                }
            }
        }
        // achar o último elemento e marcar o continuar
        try {
            ((CampoBean) assunto.getCampos().get(assunto.getCampos().size() - 1)).setContinuar(hasChild);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="manterAgendamentoVIP.jsp"
     */
    protected ActionForward obterAssunto3(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        lerAssunto(assunto2, 2, request);
        String codPai = ((CampoBean) assunto2.getCampos().get(0)).getValorSelecionado();
        FormManterAgendamentoVIP form = (FormManterAgendamentoVIP) formParam;

        // limpar variaveis
        request.getSession().setAttribute("assunto4", null);

        assunto3 = null;
        assunto4 = null;

        if (thereIsFilho(codPai)) {
            assunto3 = new AssuntoBean();
            createArvoreAssunto(assunto3, codPai);
        }
        assunto3.setCodigo(((CampoBean) assunto3.getCampos().get(0)).getCodigo());

        request.getSession().setAttribute("assunto3", assunto3);

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="manterAgendamentoVIP.jsp"
     */
    protected ActionForward obterAssunto4(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        FormManterAgendamentoVIP form = (FormManterAgendamentoVIP) formParam;
        lerAssunto(assunto3, 3, request);
        String codPai = ((CampoBean) assunto3.getCampos().get(0)).getValorSelecionado();

        assunto4 = null;

        if (thereIsFilho(codPai)) {
            assunto4 = new AssuntoBean();
            createArvoreAssunto(assunto4, codPai);
        }
        assunto4.setCodigo(((CampoBean) assunto4.getCampos().get(0)).getCodigo());

        request.getSession().setAttribute("assunto4", assunto4);

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    private CampoBean createCampoP(String codPai) {
        CampoBean campo = new CampoBean();
        campo.setTipo("P");
        for (int i = 0; i < arvoreAssuntos.length; i++) {
            if (arvoreAssuntos[i].getCodigoPai() != null && arvoreAssuntos[i].getCodigoPai().equals(codPai)) {
                if ("P".equals(arvoreAssuntos[i].getTipo())) {
                    campo.setNome(arvoreAssuntos[i].getDescricao());
                    campo.setCodigo(arvoreAssuntos[i].getCodigo());
                }
            }
        }
        return campo;
    }

    private CampoBean createCampoU(String codPai) {
        CampoBean campo = new CampoBean();
        campo.setTipo("U");
        for (int i = 0; i < arvoreAssuntos.length; i++) {
            if (arvoreAssuntos[i].getCodigoPai() != null && arvoreAssuntos[i].getCodigoPai().equals(codPai)) {
                if ("U".equals(arvoreAssuntos[i].getTipo())) {
                    campo.addValor(new ValorBean(arvoreAssuntos[i].getCodigo(), arvoreAssuntos[i].getDescricao()));
                }
            }
        }
        return campo;
    }

    private CampoBean createCampoT(String codPai) {
        CampoBean campo = new CampoBean();
        campo.setTipo("T");
        for (int i = 0; i < arvoreAssuntos.length; i++) {
            if (arvoreAssuntos[i].getCodigoPai() != null && arvoreAssuntos[i].getCodigoPai().equals(codPai)) {
                if ("T".equals(arvoreAssuntos[i].getTipo())) {
                    campo.addValor(new ValorBean(arvoreAssuntos[i].getCodigo(), arvoreAssuntos[i].getDescricao()));
                }
            }
        }
        return campo;
    }

    private CampoBean createCampoC(String codPai) {
        CampoBean campo = new CampoBean();
        campo.setTipo("C");
        for (int i = 0; i < arvoreAssuntos.length; i++) {
            if (arvoreAssuntos[i].getCodigoPai() != null && arvoreAssuntos[i].getCodigoPai().equals(codPai)) {
                if ("C".equals(arvoreAssuntos[i].getTipo())) {
                    String[] valores = arvoreAssuntos[i].getValores();
                    for (int j = 0; j < valores.length; j++) {
                        campo.addValor(new ValorBean(valores[j], valores[j]));
                    }
                }
            }
        }
        return campo;
    }

    private boolean thereIsFilho(String codPai) {
        for (int i = 0; i < arvoreAssuntos.length; i++) {
            if (arvoreAssuntos[i].getCodigoPai() != null && arvoreAssuntos[i].getCodigoPai().equals(codPai)) {
                return true;
            }
        }
        return false;
    }

    private boolean temApenasUmaPergunta(String codPai) {
        int j = 0;
        for (int i = 0; i < arvoreAssuntos.length; i++) {
            if (arvoreAssuntos[i].getCodigoPai() != null && arvoreAssuntos[i].getCodigoPai().equals(codPai)) {
                j++;
            }
        }
        if (j == 1) {
            return true;
        }
        return false;
    }

    private boolean thereIsCampo(String tipoCampo, String codPai) {
        for (int i = 0; i < arvoreAssuntos.length; i++) {
            if (arvoreAssuntos[i].getCodigoPai() != null && arvoreAssuntos[i].getCodigoPai().equals(codPai)) {
                if (tipoCampo.equals(arvoreAssuntos[i].getTipo())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="manterAgendamentoVIP.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward buscaAlteracaoAgendamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FormManterAgendamentoVIP form = (FormManterAgendamentoVIP) formParam;
        // ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
        // *** alterar sucess notetion.

        this.getFormManterAgendamentoVIP().setIdHorario(ConstantesCRM.SVAZIO);
        this.getFormManterAgendamentoVIP().setDtAgendamento(ConstantesCRM.SVAZIO);
        this.getFormManterAgendamentoVIP().setIdLoja(ConstantesCRM.SVAZIO);
        this.getFormManterAgendamentoVIP().setHorarios(null);
        this.getFormManterAgendamentoVIP().setIdMunicipio(ConstantesCRM.SVAZIO);
        this.getFormManterAgendamentoVIP().setListaLojas(null);

        ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);

        String tipoOperacao = request.getParameter("tipoOperacao");
        String idAgendamento = ConstantesCRM.SVAZIO;

        if (tipoOperacao != null && "alterar".equals(tipoOperacao)) {

            idAgendamento = form.getIdAgendamento();
            this.getFormManterAgendamentoVIP().setIdAgendamento(idAgendamento);
            this.getFormManterAgendamentoVIP().setAgendamento(getAgendamento(this.getFormManterAgendamentoVIP().getListaAgendamentos(), idAgendamento));

            // RECUPERA UF
            String uf = this.getFormManterAgendamentoVIP().getAgendamento().getUf();
            this.getFormManterAgendamentoVIP().setIdUF(uf);

            String municipio = this.getFormManterAgendamentoVIP().getAgendamento().getMunicipio();
            this.getFormManterAgendamentoVIP().setIdMunicipio(municipio);

            // ListaUFVO listaUf = controlManterTerminal.obterComboUF(this.getUser(request));

            // this.getFormManterAgendamentoVIP().setListaUf(listaUf);

            // RECUPERA MUNICIPIO
            this.getFormManterAgendamentoVIP().setListaMunicipios(controlManterAgendamento2.consultarMunicipios(uf, this.getUser(request)));

            // RECUPERA LOJA
            this.getFormManterAgendamentoVIP().setListaLojas(controlManterAgendamento2.consultarLojas(uf, municipio, this.getUser(request)));
            this.getFormManterAgendamentoVIP().setIdLoja(this.getFormManterAgendamentoVIP().getAgendamento().getIdLoja());
            this.getFormManterAgendamentoVIP().setDtAgendamento(this.getFormManterAgendamentoVIP().getAgendamento().getData());

            // RECUPERA HORARIOS
            this.getFormManterAgendamentoVIP().setHorarios(
                    controlManterAgendamento2.consultarHorarios(this.getFormManterAgendamentoVIP().getAgendamento().getIdLoja(), this.getFormManterAgendamentoVIP().getAgendamento().getData(),
                            this.getUser(request)));
            this.getFormManterAgendamentoVIP().setIdHorario(this.getFormManterAgendamentoVIP().getAgendamento().getHora());

            request.getSession().setAttribute("assunto1", null);
            request.getSession().setAttribute("assunto2", null);
            request.getSession().setAttribute("assunto3", null);
            request.getSession().setAttribute("assunto4", null);

            // BUSCA ASSUNTOS
            obterArvoreAssuntos(request);

        } else if (tipoOperacao != null && "incluir".equals(tipoOperacao)) {

            String linha = parametros.getNrLinha().substring(2);
            String ddd = parametros.getNrCodArea();

            String uf = controlManterAgendamento2.getUfByDddLinha(ddd, linha);

            this.getFormManterAgendamentoVIP().setIdUF(uf);

            // RECUPERA MUNICIPIO
            this.getFormManterAgendamentoVIP().setListaMunicipios(controlManterAgendamento2.consultarMunicipios(uf, this.getUser(request)));

            // ListaUFVO listaUf = controlManterTerminal.obterComboUF(this.getUser(request));

            // this.getFormManterAgendamentoVIP().setListaUf(listaUf);

            // VERIFICA PRIMEIRO ACESSO
            this.getFormManterAgendamentoVIP().setIsPrimeiroAcesso(String.valueOf(controlManterAgendamento2.primeiroAgendamento(ddd, linha, this.getUser(request))));

            request.getSession().setAttribute("assunto1", null);
            request.getSession().setAttribute("assunto2", null);
            request.getSession().setAttribute("assunto3", null);
            request.getSession().setAttribute("assunto4", null);

            // BUSCA ASSUNTOS
            obterArvoreAssuntos(request);

        }

        this.getFormManterAgendamentoVIP().setTipoOperacao(tipoOperacao);

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    private boolean verificaDataAgendamentoAlteracaoCancelamento(String data, String hora) {

        // tratamento data.
        Format fmt = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        String dtAg = data + " " + hora;
        Date dtAgenda = null;
        try {
            dtAgenda = (Date) fmt.parseObject(dtAg);
        } catch (ParseException e) {
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dtAgenda);
        cal.add(Calendar.HOUR, -1);
        dtAgenda = cal.getTime();

        Date hoje = new Date();

        return !(hoje.after(dtAgenda));

    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="manterListaAgendamentoVIP.jsp"
     * @jpf:forward name="erro" path="mensagemSistemaIndiponivel.jsp"
     */
    protected ActionForward excluirAgendamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FormManterAgendamentoVIP form = (FormManterAgendamentoVIP) formParam;
        ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
        this.getFormManterAgendamentoVIP().setNrProtocolo(ConstantesCRM.SVAZIO);

        if (form.getIdAgendamento() != null && !ConstantesCRM.SVAZIO.equals(form.getIdAgendamento())) {

            RegistraAgendamentoInputType agendamento = new RegistraAgendamentoInputType();

            String linha = parametros.getNrLinha();
            String linhaParam = parametros.getNrLinha().substring(2);
            String ddd = parametros.getNrCodArea();

            agendamento.setIdAgendamento(form.getIdAgendamento());
            agendamento.setNumeroTelefone(linhaParam);
            agendamento.setDdd(ddd);
            agendamento.setIdLoja(form.getIdLoja());
            agendamento.setData(form.getDtAgendamento());

            if (!verificaDataAgendamentoAlteracaoCancelamento(form.getDtAgendamento(), form.getIdHorario())) {

                request.setAttribute("msgStatus", "Falta menos de uma hora, não é possível cancelar este atendimento em loja.");
                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                return mapping.findForward(ConstantesCRM.SUCCESS);

            }

            String dsNomeLoja = form.getDsNomeLoja();
            String dtVisitaLoja = agendamento.getData() + " " + form.getIdHorario();

            if (form.getIdHorario() != null && form.getIdHorario().length() > 4) {
                agendamento.setHora(form.getIdHorario().substring(0, 5));
            }

            try {

                controlManterAgendamento2.cancelarAgendamento(agendamento, ConstantesCRM.SVAZIO, this.getUser(request));

                // controlManterAgendamento2.registrarContato(this.getUser(request),linhaParam, ddd,
                // parametros.getIdTipoRelacionamento(),"1", CONTATO_CANCELA_ATENDIMENTO_AGENDADO);
                registrarContato(PALITAGEM_CANCELA_ATENDIMENTO_AGENDADO, request);

                controlManterAgendamento2.enviarSMS(ddd, linhaParam, dtVisitaLoja, dsNomeLoja, "excluir", this.getUser(request));

            } catch (Exception e) {

                // controlManterAgendamento2.registrarContato(this.getUser(request),linhaParam, ddd,
                // parametros.getIdTipoRelacionamento(),"1", CONTATO_FALHA_CANCELA_AGENDAMENTO);
                registrarContato(PALITAGEM_FALHA_CANCELA_AGENDAMENTO, request);

                log.error(e.getMessage(), e);
                request.setAttribute("msgErro", "Sistema indisponível no momento, tente mais tarde.");
                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                return mapping.findForward("erro");

            }
            // EXCLUIR ALERTA
            ListaAlerta[] alertas = new ListaAlerta[] { ListaAlerta.Factory.newInstance() };

            alertas[0].setOperacao("excluir");
            alertas[0].setCdAreaRegistro(ddd);
            alertas[0].setNrLinha(linhaParam);
            alertas[0].setSgTipoComunicacao("SMS");

            controlManterAgendamento2.incluirAlerta(alertas, this.getUser(request));

            // FIM EXCLUIR ALERTA

            ListaAlerta[] listaAlerta = controlManterAgendamento2.consultarAlerta(parametros.getNrCodArea(), linhaParam, this.getUser(request));

            listaAlerta = trataDataListaAlerta(listaAlerta);

            if (listaAlerta == null || listaAlerta.length == 0) {
                this.getFormManterAgendamentoVIP().setSemAlerta("true");
            } else {
                this.getFormManterAgendamentoVIP().setSemAlerta("false");
            }

            calcularDataLimite(this.getFormManterAgendamentoVIP());

            // OBTEM DATA
            Format fmt = new SimpleDateFormat("dd/MM/yyyy");
            Calendar cal = Calendar.getInstance();
            Date hoje = new Date();

            cal.setTime(hoje);
            cal.add(Calendar.DAY_OF_MONTH, -30);
            String dataInicio = fmt.format(cal.getTime());

            cal.setTime(hoje);
            cal.add(Calendar.DAY_OF_MONTH, 30);
            String dataFim = fmt.format(cal.getTime());
            // FIM OBTEM DATA

            this.getFormManterAgendamentoVIP().setListaAgendamentos(controlManterAgendamento2.listarAgendamentos(ddd, linhaParam, dataInicio, dataFim, this.getUser(request)));

            if (this.getFormManterAgendamentoVIP().getListaAgendamentos().length == 0) {

                this.getFormManterAgendamentoVIP().setListaAgendamentoSemDados("true");

            } else {

                this.getFormManterAgendamentoVIP().setListaAgendamentoSemDados("false");
            }

            request.setAttribute("listaAlerta", listaAlerta);

        }

        request.setAttribute("msgStatus", "Agendamento cancelado com sucesso.");

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    private ListaAlerta[] trataDataListaAlerta(ListaAlerta[] lista) {

        if (lista == null) {
            return null;
        }

        ListaAlerta[] listaNova = lista;

        for (int i = 0; i < listaNova.length; i++) {
            if (listaNova[i].getDtAlerta() != null && listaNova[i].getDtAlerta().length() > 16) {
                listaNova[i].setDtAlerta(listaNova[i].getDtAlerta().substring(0, 16));
            }
        }

        return listaNova;

    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="manterDetalheAgendamentoVIP.jsp"
     */
    protected ActionForward visualizarDetalhes(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FormManterAgendamentoVIP form = (FormManterAgendamentoVIP) formParam;
        String idAgendamento = request.getParameter("idAgendamento");
        ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
        String ddd = ConstantesCRM.SVAZIO;
        String linhaParam = ConstantesCRM.SVAZIO;
        this.getFormManterAgendamentoVIP().setNrProtocolo(ConstantesCRM.SVAZIO);

        if (idAgendamento == null || ConstantesCRM.SVAZIO.equals(idAgendamento)) {

            if (parametros.getNrLinha() != null) {
                ddd = parametros.getNrCodArea();
                linhaParam = parametros.getNrLinha().substring(2, 10);
            }

            // BUSCA LISTA AGENDAMENTOS.
            this.getFormManterAgendamentoVIP().setListaAgendamentos(
                    controlManterAgendamento2.listarAgendamentos(ddd, linhaParam, this.getFormManterAgendamentoVIP().getDataMinima(), this.getFormManterAgendamentoVIP().getDataMaxima(),
                            this.getUser(request)));

            // BUSCA AGENDAMENTO POR STATUS DO AGENDAMENTO.
            this.getFormManterAgendamentoVIP().setAgendamento(this.getAgendamentoByStatusAgendado(this.getFormManterAgendamentoVIP().getListaAgendamentos()));

            ListaAlerta[] listaAlerta = controlManterAgendamento2.consultarAlerta(parametros.getNrCodArea(), linhaParam, this.getUser(request));

            listaAlerta = trataDataListaAlerta(listaAlerta);

            request.setAttribute("listaAlerta", listaAlerta);

            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);
        }

        String linha = parametros.getNrLinha().substring(2);

        if (idAgendamento != null && !ConstantesCRM.SVAZIO.equalsIgnoreCase(idAgendamento)) {

            this.getFormManterAgendamentoVIP().setAgendamento(getAgendamento(this.getFormManterAgendamentoVIP().getListaAgendamentos(), idAgendamento));

            ListaAlerta[] listaAlerta = controlManterAgendamento2.consultarAlerta(parametros.getNrCodArea(), linha, this.getUser(request));

            listaAlerta = trataDataListaAlerta(listaAlerta);

            request.setAttribute("listaAlerta", listaAlerta);

        }

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    private AgendamentoType getAgendamentoByStatusAgendado(AgendamentoType[] agendamentosParam) {

        if (agendamentosParam == null) {
            return null;
        }

        for (int i = 0; i < agendamentosParam.length; i++) {
            if ("Agendado".equalsIgnoreCase(agendamentosParam[i].getStatus())) {
                return agendamentosParam[i];
            }
        }

        return null;

    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="manterAlertaAgendamento.jsp"
     */
    protected ActionForward buscaAlertaAgendamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FormManterAgendamentoVIP form = (FormManterAgendamentoVIP) formParam;
        this.getFormManterAgendamentoVIP().setIdHorario(ConstantesCRM.SVAZIO);

        ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
        String user = ConstantesCRM.getCurrentUser(request.getSession());

        String idAgendamento = request.getParameter("idAgendamento");

        if (idAgendamento != null && !ConstantesCRM.SVAZIO.equals(idAgendamento)) {
            this.getFormManterAgendamentoVIP().setIdAgendamento(idAgendamento);
        }

        // CARREGA TIPOS DE EMAIL
        ConsultarTiposEmailVO consultarTiposEmailVO = ConsultarTiposEmailVO.Factory.newInstance();
        consultarTiposEmailVO = controlManterAgendamento2.consultaTiposEmail(this.getUser(request));

        TiposEmail tiposEmail[] = consultarTiposEmailVO.getTiposEmailArray();

        this.getFormManterAgendamentoVIP().setTiposEmail(tiposEmail);

        // FIM CARREGA TIPOS DE EMAIL

        AgendamentoType agendamento = getAgendamento(this.getFormManterAgendamentoVIP().getListaAgendamentos(), this.getFormManterAgendamentoVIP().getIdAgendamento());

        if (agendamento != null) {
            this.getFormManterAgendamentoVIP().setAgendamento(agendamento);
        }

        // this.getFormManterAgendamentoVIP().setHorarios(controlManterAgendamento2.consultarHorarios(this.getFormManterAgendamentoVIP().getAgendamento().getIdLoja(),this.getFormManterAgendamentoVIP().getAgendamento().getData(),
        // this.getUser(request)));

        this.getFormManterAgendamentoVIP().setEmails(controlManterAgendamento2.consultaEmail(parametros.getIdPessoaCliente(), user).getEmailsArray());

        String linha = parametros.getNrLinha().substring(2);

        String idPessoa = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaCliente();

        ListaAlerta[] listaAlerta = controlManterAgendamento2.consultarAlerta(parametros.getNrCodArea(), linha, this.getUser(request));

        listaAlerta = trataDataListaAlerta(listaAlerta);

        if (listaAlerta == null || listaAlerta.length == 0) {
            this.getFormManterAgendamentoVIP().setSemAlerta("true");
        } else {
            this.getFormManterAgendamentoVIP().setSemAlerta("false");

            for (int i = 0; i < listaAlerta.length; i++) {
                if ("SMS".equalsIgnoreCase(listaAlerta[i].getSgTipoComunicacao())) {
                    this.getFormManterAgendamentoVIP().setDtAlertaSMS(listaAlerta[i].getDtAlerta().substring(0, 10));
                    this.getFormManterAgendamentoVIP().setIdHorarioSMS(listaAlerta[i].getDtAlerta().substring(11, 16));
                    this.getFormManterAgendamentoVIP().setIdTipoEnvio("SMS");
                }
                if ("EMAIL".equalsIgnoreCase(listaAlerta[i].getSgTipoComunicacao())) {
                    this.getFormManterAgendamentoVIP().setIdEmail(listaAlerta[i].getDsContato());
                    this.getFormManterAgendamentoVIP().setIdHorario(listaAlerta[i].getDtAlerta().substring(11, 16));
                    this.getFormManterAgendamentoVIP().setDtAlerta(listaAlerta[i].getDtAlerta().substring(0, 10));
                    this.getFormManterAgendamentoVIP().setIdTipoEnvio("EMAIL");

                }

            }
            if (listaAlerta.length > 1) {
                this.getFormManterAgendamentoVIP().setIdTipoEnvio("AMBOS");
            }
            request.setAttribute("listaAlerta", listaAlerta);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    private AgendamentoType getAgendamento(AgendamentoType[] agendamentosParam, String idAgendamento) {
        if (idAgendamento == null || agendamentosParam == null) {
            return null;
        }
        for (int i = 0; i < agendamentosParam.length; i++) {
            if (idAgendamento.equals(agendamentosParam[i].getIdAgendamento())) {
                return agendamentosParam[i];
            }
        }
        return null;
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="manterUpLoadAgendamentoVIP.jsp"
     */
    protected ActionForward paginacao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FormManterAgendamentoVIP form = (FormManterAgendamentoVIP) formParam;
        int paginaAtual = Integer.parseInt(form.getPaginaAtual());

        int paginaInicial = 0;
        int paginaFinal = 0;

        String linhaParam = null;
        String ddd = null;

        this.getFormManterAgendamentoVIP().setDsTipoPesquisa(form.getDsTipoPesquisa());
        this.getFormManterAgendamentoVIP().setQueryPesquisa(form.getQueryPesquisa());

        if (form.getQueryPesquisa() != null && !ConstantesCRM.SVAZIO.equals(form.getQueryPesquisa()) && !ConstantesCRM.SZERO.equals(form.getQueryPesquisa())) {
            if ("linha".equals(form.getDsTipoPesquisa())) {
                linhaParam = form.getQueryPesquisa();
            }
            if ("ddd".equals(form.getDsTipoPesquisa())) {
                ddd = form.getQueryPesquisa();
            }
        }

        this.getFormManterAgendamentoVIP().setPaginaAtual(form.getPaginaAtual());
        paginaFinal = paginaAtual * this.REGISTROS_POR_PAGINA_INT;
        paginaInicial = paginaFinal + 1 - (this.REGISTROS_POR_PAGINA_INT);

        LinhaPremmiun[] linhasPremmiun = controlManterAgendamento2.getLinhasPremmiun(String.valueOf(paginaInicial), String.valueOf(paginaFinal), ddd, linhaParam);
        this.getFormManterAgendamentoVIP().setLinhasPremmiun(linhasPremmiun);
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="begin.do"
     */
    protected ActionForward incluirLinha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        FormManterAgendamentoVIP form = (FormManterAgendamentoVIP) formParam;
        try {
            controlManterAgendamento2.incluirLinha(form.getDdd(), form.getLinha());
            request.setAttribute("msgStatus", "Inclusão da Linha realizada com sucesso.");
        } catch (Exception e) {
            if (e.getMessage().indexOf("ORA-00001") > 0) {
                request.setAttribute("msgStatus", "Não foi possível incluir a linha solicitada. Registro duplicado.");
            } else if (e.getMessage().indexOf("00002") > 0) {
                request.setAttribute("msgStatus", "Não foi possível incluir a linha solicitada. Linha inexistente.");
            } else {
                request.setAttribute("msgStatus", "Ocorreu um erro na inclusão da Linha, tente novamente.");
            }
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="semacessos" path="semAcesso.jsp"
     * @jpf:forward name="lista" path="manterListaAgendamentoVIP.jsp"
     * @jpf:forward name="erro" path="mensagemSistemaIndiponivel.jsp"
     */
    protected ActionForward consultaAcessoAgendamentoVIP(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FormManterAgendamentoVIP form = (FormManterAgendamentoVIP) formParam;
        ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
        boolean isLinhaPremium = false;

        String linha = ConstantesCRM.SVAZIO;
        linha = parametros.getNrLinha().substring(2);
        String ddd = parametros.getNrCodArea();

        isLinhaPremium = controlManterAgendamento2.isPremium(ddd, linha);

        if (isLinhaPremium) {

            ParametroVO parametro = telaInicialFacadeControl.getParametro(this.getUser(request), "QTD_AGENDAMENTO_PERMITIDO");

            log.info("!!!!!!!!!! QTD_AGENDAMENTO_PERMITIDO -> " + parametro.getDsValorParametro());

            this.getFormManterAgendamentoVIP().setQuantidadeAgendamento(parametro.getDsValorParametro());

            calcularDataLimite(this.getFormManterAgendamentoVIP());

            // OBTEM DATA
            Format fmt = new SimpleDateFormat("dd/MM/yyyy");
            Calendar cal = Calendar.getInstance();
            Date hoje = new Date();

            cal.setTime(hoje);
            cal.add(Calendar.DAY_OF_MONTH, -30);
            String dataInicio = fmt.format(cal.getTime());

            cal.setTime(hoje);
            cal.add(Calendar.DAY_OF_MONTH, 30);
            String dataFim = fmt.format(cal.getTime());
            // FIM OBTEM DATA

            try {

                this.getFormManterAgendamentoVIP().setListaAgendamentos(controlManterAgendamento2.listarAgendamentos(ddd, linha, dataInicio, dataFim, this.getUser(request)));

            } catch (Exception e) {

                log.error(e.getMessage(), e);
                request.setAttribute("msgErro", "Sistema indisponível no momento, tente mais tarde.");
                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                return mapping.findForward("erro");
            }

            if (this.getFormManterAgendamentoVIP().getListaAgendamentos().length == 0) {

                this.getFormManterAgendamentoVIP().setListaAgendamentoSemDados("true");

            } else {

                this.getFormManterAgendamentoVIP().setListaAgendamentoSemDados("false");

                ListaAlerta[] listaAlerta = controlManterAgendamento2.consultarAlerta(parametros.getNrCodArea(), linha, this.getUser(request));

                listaAlerta = trataDataListaAlerta(listaAlerta);

                if (listaAlerta == null || listaAlerta.length == 0) {
                    this.getFormManterAgendamentoVIP().setSemAlerta("true");
                } else {
                    this.getFormManterAgendamentoVIP().setSemAlerta("false");

                    request.setAttribute("listaAlerta", listaAlerta);
                }
            }

            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward("lista");

        } else {

            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward("semacessos");
        }

        // request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        // return mapping.findForward("semacessos");

    }

    private void calcularDataLimite(FormManterAgendamentoVIP form) {

        // Calcula o limite de trinta dias a partir da data atual
        Date dtHoje = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dtHoje);
        cal.add(Calendar.DAY_OF_MONTH, 2);
        Date dtMin = cal.getTime();
        cal.setTime(dtHoje);
        cal.add(Calendar.DAY_OF_MONTH, 30);
        Date dtMax = cal.getTime();

        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        form.setDataMinima(fmt.format(dtMin));
        form.setDataMaxima(fmt.format(dtMax));
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="begin.do"
     */
    protected ActionForward excluirLinha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        FormManterAgendamentoVIP form = (FormManterAgendamentoVIP) formParam;
        log.info("ManterAgendamentoVIPController.excluirLinha() BEGIN");

        try {

            String ddd = form.getDddLinha().substring(0, 2);
            String linha = form.getDddLinha().substring(2, 10);

            controlManterAgendamento2.excluirLinha(ddd, linha);

            request.setAttribute("msgStatus", "Exclusão da Linha realizada com sucesso.");

        } catch (Exception e) {

            log.error("ManterAgendamentoVIPController.excluirLinha", e);

            request.setAttribute("msgStatus", "Ocorreu um erro na exclusão da Linha, tente novamente.");
        }

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="manterAgendamentoVIP.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward buscaMunicipioPorUf(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FormManterAgendamentoVIP form = (FormManterAgendamentoVIP) formParam;
        this.getFormManterAgendamentoVIP().setListaLojas(null);
        this.getFormManterAgendamentoVIP().setDtAgendamento(ConstantesCRM.SVAZIO);
        this.getFormManterAgendamentoVIP().setHorarios(null);
        this.getFormManterAgendamentoVIP().setIdMunicipio(ConstantesCRM.SVAZIO);
        this.getFormManterAgendamentoVIP().setIdHorario(ConstantesCRM.SVAZIO);
        this.getFormManterAgendamentoVIP().setIdLoja(ConstantesCRM.SVAZIO);

        this.getFormManterAgendamentoVIP().setIdUF(form.getIdUF());
        String uf = form.getIdUF();
        this.getFormManterAgendamentoVIP().setListaMunicipios(controlManterAgendamento2.consultarMunicipios(uf, this.getUser(request)));

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);

    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="manterAgendamentoVIP.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward buscaLojaPorUfMunicipio(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FormManterAgendamentoVIP form = (FormManterAgendamentoVIP) formParam;
        this.getFormManterAgendamentoVIP().setIdLoja(ConstantesCRM.SVAZIO);
        this.getFormManterAgendamentoVIP().setDtAgendamento(ConstantesCRM.SVAZIO);
        this.getFormManterAgendamentoVIP().setHorarios(null);

        // GET UF
        this.getFormManterAgendamentoVIP().setIdUF(form.getIdUF());
        String uf = form.getIdUF();

        // GET MUNIPICIO
        this.getFormManterAgendamentoVIP().setIdMunicipio(form.getIdMunicipio());
        String municipio = form.getIdMunicipio();

        this.getFormManterAgendamentoVIP().setListaLojas(controlManterAgendamento2.consultarLojas(uf, municipio, this.getUser(request)));

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);

    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="manterAgendamentoVIP.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward buscarHorarios(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FormManterAgendamentoVIP form = (FormManterAgendamentoVIP) formParam;
        this.getFormManterAgendamentoVIP().setIdLoja(form.getIdLoja());
        String loja = form.getIdLoja();

        this.getFormManterAgendamentoVIP().setDtAgendamento(form.getDtAgendamento());
        String dtAgendamento = form.getDtAgendamento();

        this.getFormManterAgendamentoVIP().setHorarios(controlManterAgendamento2.consultarHorarios(loja, dtAgendamento, this.getUser(request)));

        if (this.getFormManterAgendamentoVIP().getHorarios() == null || this.getFormManterAgendamentoVIP().getHorarios().length == 0) {
            this.getFormManterAgendamentoVIP().setHorarios(null);
            request.setAttribute("msgStatus", "Não existem horários disponíveis para esta data.");
        }

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    private String getNomeLoja(LojaType[] lojaArray, String idLoja) {

        if (lojaArray == null || idLoja == null) {
            return null;
        }

        for (int i = 0; i < lojaArray.length; i++) {
            if (idLoja.equals(lojaArray[i].getId())) {
                return lojaArray[i].getNome();
            }
        }

        return null;

    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="manterListaAgendamentoVIP.jsp"
     * @jpf:forward name="erro" path="mensagemSistemaIndiponivel.jsp"
     */
    protected ActionForward agendar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FormManterAgendamentoVIP form = (FormManterAgendamentoVIP) formParam;
        ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
        String mensagem = ConstantesCRM.SVAZIO;
        String linhaParam = parametros.getNrLinha().substring(2);
        String ddd = parametros.getNrCodArea();

        if (!verificaDataAgendamentoAlteracaoCancelamento(form.getDtAgendamento(), form.getIdHorario())) {

            request.setAttribute("msgStatus", "Falta menos de uma hora, não é possível cancelar este atendimento em loja.");
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);

        }

        RegistraAgendamentoInputType ag = new RegistraAgendamentoInputType();

        // INICIO ASSUNTO
        lerAssunto(assunto1, 1, request);
        lerAssunto(assunto2, 2, request);
        lerAssunto(assunto3, 3, request);
        lerAssunto(assunto4, 4, request);

        ag.setCodigoAssunto(((CampoBean) assunto1.getCampos().get(0)).getValorSelecionado());

        if (assunto2 != null) {
            ag.setCodigoDetalhe1(assunto2.getCodigo());
            if (assunto2.getCampos().size() > 2) {
                ag.setTipoDetalhe1("O");
            } else {
                ag.setTipoDetalhe1("T");
            }
            ag.setValorDetalhe1(assunto2.gerarValorDetalhe());

        }

        if (assunto3 != null) {
            ag.setCodigoDetalhe2(assunto3.getCodigo());
            if (assunto3.getCampos().size() > 2) {
                ag.setTipoDetalhe2("O");
            } else {
                ag.setTipoDetalhe2("T");
            }
            ag.setValorDetalhe2(assunto3.gerarValorDetalhe());
        }

        if (assunto4 != null) {
            ag.setCodigoDetalhe3(assunto4.getCodigo());
            if (assunto4.getCampos().size() > 2) {
                ag.setTipoDetalhe3("O");
            } else {
                ag.setTipoDetalhe3("T");
            }
            ag.setValorDetalhe3(assunto4.gerarValorDetalhe());
        }

        // fim ASSUNTO

        String linha = parametros.getNrLinha();

        ag.setIdAgendamento(null);
        ag.setData(form.getDtAgendamento());
        ag.setHora(form.getIdHorario());
        ag.setIdLoja(form.getIdLoja());
        // ag.setMunicipio(form.getIdMunicipio());
        // ag.setUf(form.getIdUF());
        ag.setNumeroTelefone(linhaParam);
        ag.setDdd(ddd);
        ag.setObservacoes(form.getRadioQuestionario());

        String dtVisitaLoja = ag.getData() + " " + ag.getHora();

        String dsNomeLoja = getNomeLoja(this.getFormManterAgendamentoVIP().getListaLojas(), form.getIdLoja());

        // CARREGA PARAMETROS PARA RECUPERAR NO CADASTRO DE ALERTA
        this.getFormManterAgendamentoVIP().getAgendamento().setData(ag.getData());
        this.getFormManterAgendamentoVIP().getAgendamento().setHora(ag.getHora());
        this.getFormManterAgendamentoVIP().getAgendamento().setNome(dsNomeLoja);

        // agendamento.assunto = form.getAssunto1Form();

        if ("alterar".equals(this.getFormManterAgendamentoVIP().getTipoOperacao())) {
            ag.setIdAgendamento(this.getFormManterAgendamentoVIP().getIdAgendamento());
            if (this.assunto2 == null) {
                ag.setCodigoAssunto(null);
                ag.setCodigoDetalhe1(null);
                ag.setCodigoDetalhe2(null);
                ag.setCodigoDetalhe3(null);
                ag.setValorDetalhe1(null);
                ag.setValorDetalhe2(null);
                ag.setValorDetalhe3(null);
            }

            try {
                controlManterAgendamento2.alterarAgendamento(ag, "", this.getUser(request));
                // controlManterAgendamento2.registrarContato(this.getUser(request), linhaParam, ddd,
                // parametros.getIdTipoRelacionamento(),"1", CONTATO_ALTERACAO_ATENDIMENTO_AGENDADO),
                // parametros.getNrProtocolo(), idTipoAberturaProtocolo);
                registrarContato(PALITAGEM_ALTERACAO_ATENDIMENTO_AGENDADO, request);
                controlManterAgendamento2.enviarSMS(ddd, linhaParam, dtVisitaLoja, dsNomeLoja, "alterar", this.getUser(request));

            } catch (Exception e) {
                // controlManterAgendamento2.registrarContato(this.getUser(request), linhaParam, ddd,
                // parametros.getIdTipoRelacionamento(),"1", CONTATO_FALHA_ALTERACAO_AGENDAMENTO);
                registrarContato(PALITAGEM_FALHA_ALTERACAO_AGENDAMENTO, request);
                log.error(e.getMessage(), e);
                request.setAttribute("msgErro", "Sistema indisponível no momento, tente mais tarde.");
                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                return mapping.findForward("erro");
            }

        } else if ("incluir".equals(this.getFormManterAgendamentoVIP().getTipoOperacao())) {
            try {
                mensagem = "Atendimento em loja agendado com sucesso, deseja cadastrar um horário para receber um alerta por E-mail ou SMS sobre o agendamento?";
                RegistraAgendamentoOutputType out = controlManterAgendamento2.incluirAgendamento(ag, "", this.getUser(request));
                // controlManterAgendamento2.registrarContato(this.getUser(request), linhaParam, ddd,
                // parametros.getIdTipoRelacionamento(),"1", );
                registrarContato(PALITAGEM_ATENDIMENTO_AGENDADO, request);
                controlManterAgendamento2.enviarSMS(ddd, linhaParam, dtVisitaLoja, dsNomeLoja, "incluir", this.getUser(request));

            } catch (Exception e) {
                // controlManterAgendamento2.registrarContato(this.getUser(request), linhaParam, ddd,
                // parametros.getIdTipoRelacionamento(),"1", CONTATO_FALHA_AGENDAMENTO);
                registrarContato(PALITAGEM_FALHA_AGENDAMENTO, request);
                log.error(e.getMessage(), e);
                request.setAttribute("msgErro", "Sistema indisponível no momento, tente mais tarde.");
                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                return mapping.findForward("erro");
            }
        }

        // controlManterAgendamento2.incluirAgendamento(agendamento, assunto2Array, assunto3Array, assunto4Array,
        // this.getUser(request));

        // OBTEM DATA
        Format fmt = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        Date hoje = new Date();

        cal.setTime(hoje);
        cal.add(Calendar.DAY_OF_MONTH, -30);
        String dataInicio = fmt.format(cal.getTime());

        cal.setTime(hoje);
        cal.add(Calendar.DAY_OF_MONTH, 30);
        String dataFim = fmt.format(cal.getTime());
        // FIM OBTEM DATA

        this.getFormManterAgendamentoVIP().setListaAgendamentos(controlManterAgendamento2.listarAgendamentos(ddd, linhaParam, dataInicio, dataFim, this.getUser(request)));

        if (this.getFormManterAgendamentoVIP().getListaAgendamentos().length == 0) {

            this.getFormManterAgendamentoVIP().setListaAgendamentoSemDados("true");

        } else {

            this.getFormManterAgendamentoVIP().setListaAgendamentoSemDados("false");
        }

        ListaAlerta[] listaAlerta = controlManterAgendamento2.consultarAlerta(parametros.getNrCodArea(), linhaParam, this.getUser(request));

        listaAlerta = trataDataListaAlerta(listaAlerta);

        if (listaAlerta == null || listaAlerta.length == 0) {
            this.getFormManterAgendamentoVIP().setSemAlerta("true");

            if ("alterar".equals(this.getFormManterAgendamentoVIP().getTipoOperacao())) {
                mensagem = "Agendamento alterado com sucesso, deseja cadastrar um horário para que o sistema envie um lembrete por E-mail ou SMS sobre o agendamento?";
            }

        } else {
            if ("alterar".equals(this.getFormManterAgendamentoVIP().getTipoOperacao())) {
                mensagem = "Atendimento em loja alterado com sucesso.Por Favor confirme a data e hora de envio do seu lembrete.";
            }

            this.getFormManterAgendamentoVIP().setSemAlerta("false");

            request.setAttribute("listaAlerta", listaAlerta);
        }

        request.setAttribute("fechaJanela", "true");

        if ("alterar".equals(this.getFormManterAgendamentoVIP().getTipoOperacao())) {

            request.setAttribute("msgPerguntaAlteracao", mensagem);

        } else {

            request.setAttribute("msgPergunta", mensagem);

        }

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="manterListaAgendamentoVIP.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward gravarAlerta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FormManterAgendamentoVIP form = (FormManterAgendamentoVIP) formParam;
        ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
        this.getFormManterAgendamentoVIP().setNrProtocolo("");

        ListaAlerta[] alertas = new ListaAlerta[] { ListaAlerta.Factory.newInstance(), ListaAlerta.Factory.newInstance() };

        String linha = parametros.getNrLinha().substring(2);
        String ddd = parametros.getNrCodArea();
        boolean emailSMS = false;

        if ("SMS".equals(form.getIdTipoEnvio()) || "AMBOS".equals(form.getIdTipoEnvio())) {
            alertas[0].setOperacao("incluir");
            alertas[0].setSgTipoComunicacao("SMS");
            alertas[0].setCdAreaRegistro(ddd);
            alertas[0].setNrLinha(linha);
            alertas[0].setDsLoja(this.getFormManterAgendamentoVIP().getAgendamento().getNome());
            alertas[0].setDtAlerta(form.getDtAlertaSMS() + " " + form.getIdHorarioSMS() + ":00");
            alertas[0].setDtVisitaLoja(this.getFormManterAgendamentoVIP().getAgendamento().getData() + " " + this.getFormManterAgendamentoVIP().getAgendamento().getHora());
            alertas[0].setDsContato(ConstantesCRM.SVAZIO);
            emailSMS = true;
        }

        if ("EMAIL".equals(form.getIdTipoEnvio()) || "AMBOS".equals(form.getIdTipoEnvio())) {

            if ("NOVO".equals(form.getIdEmail())) {

                try {

                    Msg msg = controlManterAgendamento2.incluiEmail(this.getUser(request), parametros.getIdPessoaCliente(), form.getEmail(), form.getIdTipoEmail(), ddd, linha, null);

                } catch (FacadeException te) {
                    // TODO:
                }

                alertas[1].setDsContato(form.getEmail());

                // EMAIL JA EXISTEM
            } else {
                alertas[1].setDsContato(form.getIdEmail());
            }

            alertas[1].setOperacao("incluir");
            alertas[1].setSgTipoComunicacao("EMAIL");
            alertas[1].setCdAreaRegistro(ddd);
            alertas[1].setNrLinha(linha);
            alertas[1].setDsLoja(this.getFormManterAgendamentoVIP().getAgendamento().getNome());
            alertas[1].setDtAlerta(form.getDtAlerta() + " " + form.getIdHorario() + ":00");
            alertas[1].setDtVisitaLoja(this.getFormManterAgendamentoVIP().getAgendamento().getData() + " " + this.getFormManterAgendamentoVIP().getAgendamento().getHora());

            emailSMS = true;
        }

        // EXCLUIR ALERTA
        if (emailSMS == false) {
            alertas[0].setOperacao("excluir");
            alertas[0].setCdAreaRegistro(ddd);
            alertas[0].setNrLinha(linha);
            alertas[0].setSgTipoComunicacao("SMS");
            // controlManterAgendamento2.incluirAlerta(alertas, this.getUser(request));
        }

        controlManterAgendamento2.incluirAlerta(alertas, this.getUser(request));

        // controlManterAgendamento2.registrarContato(this.getUser(request),linha,ddd,parametros.getIdTipoRelacionamento(),
        // "1", CONTATO_ALTERACAO_ALERTA_AGENDADO);
        registrarContato(PALITAGEM_ALTERACAO_ALERTA_AGENDADO, request);

        // BUSCA LISTA AGENDAMENTOS.
        this.getFormManterAgendamentoVIP()
                .setListaAgendamentos(
                        controlManterAgendamento2.listarAgendamentos(ddd, linha, this.getFormManterAgendamentoVIP().getDataMinima(), this.getFormManterAgendamentoVIP().getDataMaxima(),
                                this.getUser(request)));

        // BUSCA AGENDAMENTO POR STATUS DO AGENDAMENTO.
        this.getFormManterAgendamentoVIP().setAgendamento(this.getAgendamentoByStatusAgendado(this.getFormManterAgendamentoVIP().getListaAgendamentos()));

        // this.getFormManterAgendamentoVIP().setListaAgendamentos(controlManterAgendamento2.listarAgendamentos(ddd,linha
        // ,this.getFormManterAgendamentoVIP().getDataMinima(),this.getFormManterAgendamentoVIP().getDataMaxima(),
        // this.getUser(request)));

        if (this.getFormManterAgendamentoVIP().getListaAgendamentos().length == 0) {
            this.getFormManterAgendamentoVIP().setListaAgendamentoSemDados("true");

        } else {
            this.getFormManterAgendamentoVIP().setListaAgendamentoSemDados("false");
        }

        ListaAlerta[] listaAlerta = controlManterAgendamento2.consultarAlerta(parametros.getNrCodArea(), linha, this.getUser(request));
        listaAlerta = trataDataListaAlerta(listaAlerta);

        if (listaAlerta == null || listaAlerta.length == 0) {
            this.getFormManterAgendamentoVIP().setSemAlerta("true");
        } else {
            this.getFormManterAgendamentoVIP().setSemAlerta("false");
            request.setAttribute("listaAlerta", listaAlerta);
        }

        request.setAttribute("fechaJanela", "true");
        request.setAttribute("msgSucessoAlerta", "Alerta cadastrado com sucesso!");
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    private String registrarContato(String palito, HttpServletRequest request) {

        String nrProtocolo = ConstantesCRM.SVAZIO;
        try {
            String nrProtocoloSessao = request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO) != null ? (String) request.getSession().getAttribute(ConstantesCRM.NRPROTOCOLO)
                    : ConstantesCRM.SVAZIO;

            ParametroVO parametro = GetParametro.getInstace().getParametroVO(this.getUser(request), palito);
            if (parametro != null && !ConstantesCRM.SVAZIO.equals(parametro.getDsValorParametro())) {
                String idLinha = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdLinha();
                String nrLinhaAtual = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getNrLinha();
                nrProtocolo = ClienteUtils.registrarPalitagem(this.getUser(request), getParametrosVO(request), idLinha, nrLinhaAtual, parametro.getDsValorParametro(), ConstantesCRM.SVAZIO);

            }

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
                this.getFormManterAgendamentoVIP().setNrProtocolo(nrProtocolo);
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return nrProtocolo;

    }

    private ParametrosVO getParametrosVO(HttpServletRequest request) {
        return (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
    }

    public static class FormManterAgendamentoVIP extends ActionForm {

        private static final long serialVersionUID = -8808923830530267894L;

        private String                           dsNomeLoja;
        private String                           hora;
        private String                           data;
        private String                           alertaDataHoje;
        private String                           tipoOperacao;
        private TiposEmail[]                     tiposEmail;
        private String                           quantidadeAgendamento;
        private String                           radioQuestionario;
        private String[]                         assunto4FormDisponivel;
        private String[]                         assunto4FormAssociado;
        private String[]                         assunto3FormDisponivel;
        private String[]                         assunto3FormAssociado;
        private String[]                         assunto2FormDisponivel;
        private String[]                         assunto2FormAssociado;
        private String[]                         assunto1FormDisponivel;
        private String[]                         assunto1FormAssociado;
        private String                           assunto4Form;
        private String                           assunto3Form;
        private String                           assunto2Form;
        private String                           assunto1Form;
        private AssuntoType[]                    arvoreAssuntos;
        private AssuntoType                      assunto4;
        private AssuntoType                      assunto3;
        private AssuntoType                      assunto2;
        private AssuntoType                      assunto1;
        private String                           isPrimeiroAcesso;
        private String                           dtAgendamento;
        private LojaType[]                       listaLojas;
        private String[]                         listaMunicipios;
        private ListaUFVO                        listaUf;
        private String                           idHorarioSMS;
        private String[]                         horarios;
        private AgendamentoType                  agendamento;
        private String                           semAlerta;
        private String                           listaAgendamentoSemDados;
        private AgendamentoType[]                listaAgendamentos;
        private String                           dataMaxima;
        private String                           dataMinima;
        private String                           dddLinha;
        private String                           semRegistros;
        private String                           ddd;
        private String                           linha;
        private String                           totalPagina;
        private String                           paginaAtual;
        private LinhaPremmiun[]                  linhasPremmiun;
        private String                           queryPesquisa;
        private String                           dsTipoPesquisa;
        private String                           idEmail;
        private String                           dtAlertaSMS;
        private Emails[]                         emails;
        private String                           excecaoAgendamento;
        private String[]                         assunto4Existentes;
        private String[]                         assunto4Selecionados;
        private String                           idLoja;
        private String                           idMunicipio;
        private String                           idUF;
        private String                           idAgendamento;
        private String                           idHorario;
        private String                           dtAlerta;
        private String                           email;
        private String                           idTipoEmail;
        private String                           idTipoEnvio;
        private FormFile                         file;
        private String                           nrProtocolo;

        public String getNrProtocolo() {
            return this.nrProtocolo;
        }

        public void setNrProtocolo(String nrProtocolo) {
            this.nrProtocolo = nrProtocolo;
        }

        public void setFile(FormFile file) {
            this.file = file;
        }

        public FormFile getFile() {
            // if(this.file == null)
            // {
            // this.file = new FormFile.;
            // }

            return this.file;
        }

        public void setIdTipoEnvio(String idTipoEnvio) {
            this.idTipoEnvio = idTipoEnvio;
        }

        public String getIdTipoEnvio() {
            if (this.idTipoEnvio == null) {
                this.idTipoEnvio = ConstantesCRM.SVAZIO;
            }
            return this.idTipoEnvio;
        }

        public void setIdTipoEmail(String idTipoEmail) {
            this.idTipoEmail = idTipoEmail;
        }

        public String getIdTipoEmail() {
            if (this.idTipoEmail == null) {
                this.idTipoEmail = ConstantesCRM.SVAZIO;
            }
            return this.idTipoEmail;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEmail() {
            if (this.email == null) {
                this.email = ConstantesCRM.SVAZIO;
            }
            return this.email;
        }

        public void setDtAlerta(String dtAlerta) {
            this.dtAlerta = dtAlerta;
        }

        public String getDtAlerta() {
            if (this.dtAlerta == null) {
                this.dtAlerta = ConstantesCRM.SVAZIO;
            }
            return this.dtAlerta;
        }

        public void setIdHorario(String idHorario) {
            this.idHorario = idHorario;
        }

        public String getIdHorario() {
            if (this.idHorario == null) {
                this.idHorario = ConstantesCRM.SVAZIO;
            }
            return this.idHorario;
        }

        public void setIdAgendamento(String idAgendamento) {
            this.idAgendamento = idAgendamento;
        }

        public String getIdAgendamento() {
            if (this.idAgendamento == null) {
                this.idAgendamento = ConstantesCRM.SVAZIO;
            }
            return this.idAgendamento;
        }

        public void setIdUF(String idUF) {
            this.idUF = idUF;
        }

        public String getIdUF() {
            if (this.idUF == null) {
                this.idUF = ConstantesCRM.SVAZIO;
            }
            return this.idUF;
        }

        public void setIdMunicipio(String idMunicipio) {
            this.idMunicipio = idMunicipio;
        }

        public String getIdMunicipio() {
            if (this.idMunicipio == null) {
                this.idMunicipio = ConstantesCRM.SVAZIO;
            }
            return this.idMunicipio;
        }

        public void setIdLoja(String idLoja) {
            this.idLoja = idLoja;
        }

        public String getIdLoja() {
            if (this.idLoja == null) {
                this.idLoja = ConstantesCRM.SVAZIO;
            }
            return this.idLoja;
        }

        public void setAssunto4Selecionados(String[] assunto4Selecionados) {
            this.assunto4Selecionados = assunto4Selecionados;
        }

        public String[] getAssunto4Selecionados() {
            if (this.assunto4Selecionados == null) {
                this.assunto4Selecionados = new String[0];
            }

            return this.assunto4Selecionados;
        }

        public void setAssunto4Existentes(String[] assunto4Existentes) {
            this.assunto4Existentes = assunto4Existentes;
        }

        public String[] getAssunto4Existentes() {
            return this.assunto4Existentes;
        }

        public void setExcecaoAgendamento(String excecaoAgendamento) {
            this.excecaoAgendamento = excecaoAgendamento;
        }

        public String getExcecaoAgendamento() {
            if (this.excecaoAgendamento == null) {
                this.excecaoAgendamento = ConstantesCRM.SVAZIO;
            }
            return this.excecaoAgendamento;
        }

        public void setEmails(Emails[] emails) {
            this.emails = emails;
        }

        public Emails[] getEmails() {
            return this.emails;
        }

        public void setDtAlertaSMS(String dtAlertaSMS) {
            this.dtAlertaSMS = dtAlertaSMS;
        }

        public String getDtAlertaSMS() {
            if (this.dtAlertaSMS == null) {
                this.dtAlertaSMS = ConstantesCRM.SVAZIO;
            }
            return this.dtAlertaSMS;
        }

        public void setIdEmail(String idEmail) {
            this.idEmail = idEmail;
        }

        public String getIdEmail() {
            if (this.idEmail == null) {
                this.idEmail = ConstantesCRM.SVAZIO;
            }
            return this.idEmail;
        }

        public void setDsTipoPesquisa(String dsTipoPesquisa) {
            this.dsTipoPesquisa = dsTipoPesquisa;
        }

        public String getDsTipoPesquisa() {
            return this.dsTipoPesquisa;
        }

        public void setQueryPesquisa(String queryPesquisa) {
            this.queryPesquisa = queryPesquisa;
        }

        public String getQueryPesquisa() {
            return this.queryPesquisa;
        }

        public void setLinhasPremmiun(LinhaPremmiun[] linhasPremmiun) {
            this.linhasPremmiun = linhasPremmiun;
        }

        public LinhaPremmiun[] getLinhasPremmiun() {
            return this.linhasPremmiun;
        }

        public void setPaginaAtual(String paginaAtual) {
            this.paginaAtual = paginaAtual;
        }

        public String getPaginaAtual() {
            if (this.paginaAtual == null) {
                this.paginaAtual = ConstantesCRM.SVAZIO;
            }
            return this.paginaAtual;
        }

        public void setTotalPagina(String totalPagina) {
            this.totalPagina = totalPagina;
        }

        public String getTotalPagina() {
            if (this.totalPagina == null) {
                this.totalPagina = ConstantesCRM.SVAZIO;
            }
            return this.totalPagina;
        }

        public void setLinha(String linha) {
            this.linha = linha;
        }

        public String getLinha() {
            if (this.linha == null) {
                this.linha = ConstantesCRM.SVAZIO;
            }
            return this.linha;
        }

        public void setDdd(String ddd) {
            this.ddd = ddd;
        }

        public String getDdd() {
            if (this.ddd == null) {
                this.ddd = "";
            }
            return this.ddd;
        }

        public void setSemRegistros(String semRegistros) {
            this.semRegistros = semRegistros;
        }

        public String getSemRegistros() {
            if (this.semRegistros == null) {
                this.semRegistros = ConstantesCRM.SVAZIO;
            }
            return this.semRegistros;
        }

        public void setDddLinha(String dddLinha) {
            this.dddLinha = dddLinha;
        }

        public String getDddLinha() {
            return this.dddLinha;
        }

        public void setDataMinima(String dataMinima) {
            this.dataMinima = dataMinima;
        }

        public String getDataMinima() {
            if (this.dataMinima == null) {
                this.dataMinima = ConstantesCRM.SVAZIO;
            }
            return this.dataMinima;
        }

        public void setDataMaxima(String dataMaxima) {
            this.dataMaxima = dataMaxima;
        }

        public String getDataMaxima() {
            if (this.dataMaxima == null) {
                this.dataMaxima = ConstantesCRM.SVAZIO;
            }
            return this.dataMaxima;
        }

        public void setListaAgendamentos(AgendamentoType[] listaAgendamentos) {
            this.listaAgendamentos = listaAgendamentos;
        }

        public AgendamentoType[] getListaAgendamentos() {
            if (this.listaAgendamentos == null) {
                this.listaAgendamentos = new AgendamentoType[0];
            }

            return this.listaAgendamentos;
        }

        public void setListaAgendamentoSemDados(String listaAgendamentoSemDados) {
            this.listaAgendamentoSemDados = listaAgendamentoSemDados;
        }

        public String getListaAgendamentoSemDados() {
            if (this.listaAgendamentoSemDados == null) {
                this.listaAgendamentoSemDados = ConstantesCRM.SVAZIO;
            }
            return this.listaAgendamentoSemDados;
        }

        public void setSemAlerta(String semAlerta) {
            this.semAlerta = semAlerta;
        }

        public String getSemAlerta() {
            if (this.semAlerta == null) {
                this.semAlerta = ConstantesCRM.SVAZIO;
            }
            return this.semAlerta;
        }

        public void setAgendamento(AgendamentoType agendamento) {
            this.agendamento = agendamento;
        }

        public AgendamentoType getAgendamento() {
            if (this.agendamento == null) {
                this.agendamento = new AgendamentoType();
            }

            return this.agendamento;
        }

        public void setHorarios(String[] horarios) {
            this.horarios = horarios;
        }

        public String[] getHorarios() {
            if (this.horarios == null) {
                // this.horarios = new String[0];
            }

            return this.horarios;
        }

        public void setIdHorarioSMS(String idHorarioSMS) {
            this.idHorarioSMS = idHorarioSMS;
        }

        public String getIdHorarioSMS() {
            if (this.idHorarioSMS == null) {
                this.idHorarioSMS = ConstantesCRM.SVAZIO;
            }
            return this.idHorarioSMS;
        }

        public void setListaUf(ListaUFVO listaUf) {
            this.listaUf = listaUf;
        }

        public ListaUFVO getListaUf() {
            if (this.listaUf == null) {
                UFVO SP = UFVO.Factory.newInstance();
                SP.setIdUF("SP");
                SP.setSgUF("SP");

                UFVO RJ = UFVO.Factory.newInstance();
                RJ.setIdUF("RJ");
                RJ.setSgUF("RJ");

                UFVO ES = UFVO.Factory.newInstance();
                ES.setIdUF("ES");
                ES.setSgUF("ES");

                UFVO BA = UFVO.Factory.newInstance();
                BA.setIdUF("BA");
                BA.setSgUF("BA");

                UFVO SE = UFVO.Factory.newInstance();
                SE.setIdUF("SE");
                SE.setSgUF("SE");

                UFVO RS = UFVO.Factory.newInstance();
                RS.setIdUF("RS");
                RS.setSgUF("RS");

                UFVO PR = UFVO.Factory.newInstance();
                PR.setIdUF("PR");
                PR.setSgUF("PR");

                UFVO SC = UFVO.Factory.newInstance();
                SC.setIdUF("SC");
                SC.setSgUF("SC");

                UFVO DF = UFVO.Factory.newInstance();
                DF.setIdUF("DF");
                DF.setSgUF("DF");

                UFVO GO = UFVO.Factory.newInstance();
                GO.setIdUF("GO");
                GO.setSgUF("GO");

                UFVO MS = UFVO.Factory.newInstance();
                MS.setIdUF("MS");
                MS.setSgUF("MS");

                UFVO MT = UFVO.Factory.newInstance();
                MT.setIdUF("MT");
                MT.setSgUF("MT");

                UFVO TO = UFVO.Factory.newInstance();
                TO.setIdUF("TO");
                TO.setSgUF("TO");

                UFVO AM = UFVO.Factory.newInstance();
                AM.setIdUF("AM");
                AM.setSgUF("AM");

                UFVO MA = UFVO.Factory.newInstance();
                MA.setIdUF("MA");
                MA.setSgUF("MA");

                UFVO PA = UFVO.Factory.newInstance();
                PA.setIdUF("PA");
                PA.setSgUF("PA");

                UFVO MG = UFVO.Factory.newInstance();
                MG.setIdUF("MG");
                MG.setSgUF("MG");

                UFVO[] res = new UFVO[] { AM, BA, DF, ES, GO, MA, MG, MS, MT, PA, PR, RJ, RS, SC, SE, SP, TO };

                ListaUFVO listaUF = ListaUFVO.Factory.newInstance();

                listaUF.setUFVOArray(res);

                this.listaUf = listaUF;

            }

            return this.listaUf;
        }

        public void setListaMunicipios(String[] listaMunicipios) {
            this.listaMunicipios = listaMunicipios;
        }

        public String[] getListaMunicipios() {
            if (this.listaMunicipios == null) {
                this.listaMunicipios = new String[0];
            }

            return this.listaMunicipios;
        }

        public void setListaLojas(LojaType[] listaLojas) {
            this.listaLojas = listaLojas;
        }

        public LojaType[] getListaLojas() {

            return this.listaLojas;
        }

        public void setDtAgendamento(String dtAgendamento) {
            this.dtAgendamento = dtAgendamento;
        }

        public String getDtAgendamento() {
            if (this.dtAgendamento == null) {
                this.dtAgendamento = ConstantesCRM.SVAZIO;
            }

            return this.dtAgendamento;
        }

        public void setIsPrimeiroAcesso(String isPrimeiroAcesso) {
            this.isPrimeiroAcesso = isPrimeiroAcesso;
        }

        public String getIsPrimeiroAcesso() {
            return this.isPrimeiroAcesso;
        }

        public void setAssunto1(AssuntoType assunto1) {
            this.assunto1 = assunto1;
        }

        public AssuntoType getAssunto1() {
            return this.assunto1;
        }

        public void setAssunto2(AssuntoType assunto2) {
            this.assunto2 = assunto2;
        }

        public AssuntoType getAssunto2() {
            return this.assunto2;
        }

        public void setAssunto3(AssuntoType assunto3) {
            this.assunto3 = assunto3;
        }

        public AssuntoType getAssunto3() {
            return this.assunto3;
        }

        public void setAssunto4(AssuntoType assunto4) {
            this.assunto4 = assunto4;
        }

        public AssuntoType getAssunto4() {
            return this.assunto4;
        }

        public void setArvoreAssuntos(AssuntoType[] arvoreAssuntos) {
            this.arvoreAssuntos = arvoreAssuntos;
        }

        public AssuntoType[] getArvoreAssuntos() {
            return this.arvoreAssuntos;
        }

        public void setAssunto1Form(String assunto1Form) {
            this.assunto1Form = assunto1Form;
        }

        public String getAssunto1Form() {
            return this.assunto1Form;
        }

        public void setAssunto2Form(String assunto2Form) {
            this.assunto2Form = assunto2Form;
        }

        public String getAssunto2Form() {
            return this.assunto2Form;
        }

        public void setAssunto3Form(String assunto3Form) {
            this.assunto3Form = assunto3Form;
        }

        public String getAssunto3Form() {
            return this.assunto3Form;
        }

        public void setAssunto4Form(String assunto4Form) {
            this.assunto4Form = assunto4Form;
        }

        public String getAssunto4Form() {
            return this.assunto4Form;
        }

        public void setAssunto1FormAssociado(String[] assunto1FormAssociado) {
            this.assunto1FormAssociado = assunto1FormAssociado;
        }

        public String[] getAssunto1FormAssociado() {
            if (this.assunto1FormAssociado == null || this.assunto1FormAssociado.length == 0) {
                this.assunto1FormAssociado = new String[1];
            }
            return this.assunto1FormAssociado;
        }

        public void setAssunto1FormDisponivel(String[] assunto1FormDisponivel) {
            this.assunto1FormDisponivel = assunto1FormDisponivel;
        }

        public String[] getAssunto1FormDisponivel() {
            if (this.assunto1FormDisponivel == null || this.assunto1FormDisponivel.length == 0) {
                this.assunto1FormDisponivel = new String[1];
            }
            return this.assunto1FormDisponivel;
        }

        public void setAssunto2FormAssociado(String[] assunto2FormAssociado) {
            this.assunto2FormAssociado = assunto2FormAssociado;
        }

        public String[] getAssunto2FormAssociado() {
            if (this.assunto2FormAssociado == null || this.assunto2FormAssociado.length == 0) {
                this.assunto2FormAssociado = new String[1];
            }
            return this.assunto2FormAssociado;
        }

        public void setAssunto2FormDisponivel(String[] assunto2FormDisponivel) {
            this.assunto2FormDisponivel = assunto2FormDisponivel;
        }

        public String[] getAssunto2FormDisponivel() {
            if (this.assunto2FormDisponivel == null || this.assunto2FormDisponivel.length == 0) {
                this.assunto2FormDisponivel = new String[1];
            }
            return this.assunto2FormDisponivel;
        }

        public void setAssunto3FormAssociado(String[] assunto3FormAssociado) {
            this.assunto3FormAssociado = assunto3FormAssociado;
        }

        public String[] getAssunto3FormAssociado() {
            if (this.assunto3FormAssociado == null || this.assunto3FormAssociado.length == 0) {
                this.assunto3FormAssociado = new String[1];
            }
            return this.assunto3FormAssociado;
        }

        public void setAssunto3FormDisponivel(String[] assunto3FormDisponivel) {
            this.assunto3FormDisponivel = assunto3FormDisponivel;
        }

        public String[] getAssunto3FormDisponivel() {
            if (this.assunto3FormDisponivel == null || this.assunto3FormDisponivel.length == 0) {
                this.assunto3FormDisponivel = new String[1];
            }
            return this.assunto3FormDisponivel;
        }

        public void setAssunto4FormAssociado(String[] assunto4FormAssociado) {
            this.assunto4FormAssociado = assunto4FormAssociado;
        }

        public String[] getAssunto4FormAssociado() {
            if (this.assunto4FormAssociado == null || this.assunto4FormAssociado.length == 0) {
                this.assunto4FormAssociado = new String[1];
            }
            return this.assunto4FormAssociado;
        }

        public void setAssunto4FormDisponivel(String[] assunto4FormDisponivel) {
            this.assunto4FormDisponivel = assunto4FormDisponivel;
        }

        public String[] getAssunto4FormDisponivel() {
            if (this.assunto4FormDisponivel == null || this.assunto4FormDisponivel.length == 0) {
                this.assunto4FormDisponivel = new String[1];
            }
            return this.assunto4FormDisponivel;
        }

        public void setRadioQuestionario(String radioQuestionario) {
            this.radioQuestionario = radioQuestionario;
        }

        public String getRadioQuestionario() {
            return this.radioQuestionario;
        }

        public void setQuantidadeAgendamento(String quantidadeAgendamento) {
            this.quantidadeAgendamento = quantidadeAgendamento;
        }

        public String getQuantidadeAgendamento() {
            return this.quantidadeAgendamento;
        }

        public void setTiposEmail(TiposEmail[] tiposEmail) {
            this.tiposEmail = tiposEmail;
        }

        public TiposEmail[] getTiposEmail() {
            return this.tiposEmail;
        }

        public void setTipoOperacao(String tipoOperacao) {
            this.tipoOperacao = tipoOperacao;
        }

        public String getTipoOperacao() {
            return this.tipoOperacao;
        }

        public void setAlertaDataHoje(String alertaDataHoje) {
            this.alertaDataHoje = alertaDataHoje;
        }

        public String getAlertaDataHoje() {
            if (this.alertaDataHoje == null || ConstantesCRM.SVAZIO.equals(alertaDataHoje)) {
                Format fmt = new SimpleDateFormat("yyyyMMddHHmm");
                this.alertaDataHoje = fmt.format(new Date());
            }

            return this.alertaDataHoje;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getData() {
            return this.data;
        }

        public void setHora(String hora) {
            this.hora = hora;
        }

        public String getHora() {
            return this.hora;
        }

        public void setDsNomeLoja(String dsNomeLoja) {
            this.dsNomeLoja = dsNomeLoja;
        }

        public String getDsNomeLoja() {
            return this.dsNomeLoja;
        }

    }

    public void setFormManterAgendamentoVIP(FormManterAgendamentoVIP formParam) {

        this.formManterAgendamentoVIP = formParam;
    }

    public FormManterAgendamentoVIP getFormManterAgendamentoVIP() {

        if (this.formManterAgendamentoVIP == null) {
            this.formManterAgendamentoVIP = new FormManterAgendamentoVIP();
        }

        return this.formManterAgendamentoVIP;
    }

}
