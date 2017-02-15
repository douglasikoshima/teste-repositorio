package VOLTAV.relatorios;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import weblogic.logging.NonCatalogLogger;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.VOLTAV.iPhone.db.ManterIphoneDB.LinhaRelatorioIphone;
import br.com.vivo.fo.ctrls.VOLTAV.relatorios.RelatoriosFacadeVol;
import br.com.vivo.fo.ctrls.workflow.relatorios.RelatorioFacadeWF;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.voltav.vo.VOLTAVOperacaoRecargaVODocument.VOLTAVOperacaoRecargaVO;
import br.com.vivo.fo.voltav.vo.VOLTAVOperacaoRecargaVODocument.VOLTAVOperacaoRecargaVO.VOLTAVOperacaoRecarga;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioAcessoDiarioDocument.VOLTAVRelatorioAcessoDiario;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioAcessoDiarioVODocument.VOLTAVRelatorioAcessoDiarioVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioAcessoFaixaHorarioDocument.VOLTAVRelatorioAcessoFaixaHorario;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioAcessoFaixaHorarioVODocument.VOLTAVRelatorioAcessoFaixaHorarioVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioAcessoNegadoDocument.VOLTAVRelatorioAcessoNegado;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioAcessoNegadoVODocument.VOLTAVRelatorioAcessoNegadoVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioAcessosIncidenciaDocument.VOLTAVRelatorioAcessosIncidencia;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioAcessosIncidenciaVODocument.VOLTAVRelatorioAcessosIncidenciaVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioAdesoesDocument.VOLTAVRelatorioAdesoes;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioAdesoesVODocument.VOLTAVRelatorioAdesoesVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioFinanceiroDocument.VOLTAVRelatorioFinanceiro;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioFinanceiroVODocument.VOLTAVRelatorioFinanceiroVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioLojasVODocument.VOLTAVRelatorioLojasVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioPrimeiroAcessoDocument.VOLTAVRelatorioPrimeiroAcesso;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioPrimeiroAcessoVODocument.VOLTAVRelatorioPrimeiroAcessoVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioServicosDisponveisDocument.VOLTAVRelatorioServicosDisponveis;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioServicosDisponveisVODocument.VOLTAVRelatorioServicosDisponveisVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioServicosEfetuadosDocument.VOLTAVRelatorioServicosEfetuados;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioServicosEfetuadosVODocument.VOLTAVRelatorioServicosEfetuadosVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioStatusVendaVODocument.VOLTAVRelatorioStatusVendaVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioTempoPermanenciaDocument.VOLTAVRelatorioTempoPermanencia;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioTempoPermanenciaVODocument.VOLTAVRelatorioTempoPermanenciaVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioTipoErroDocument.VOLTAVRelatorioTipoErro;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioTipoErroVODocument.VOLTAVRelatorioTipoErroVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatoriosFiltroLojaVODocument.VOLTAVRelatoriosFiltroLojaVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatoriosFiltroTerminalVODocument.VOLTAVRelatoriosFiltroTerminalVO;
import br.com.vivo.fo.workflow.vo.WFRelatoriosFiltroRegionalVODocument.WFRelatoriosFiltroRegionalVO;
import br.com.vivo.fo.workflow.vo.WFRelatoriosFiltrosVODocument;
import br.com.vivo.fo.workflow.vo.WFRelatoriosFiltrosVODocument.WFRelatoriosFiltrosVO;
import br.com.vivo.report.bean.BarChartBean;
import br.com.vivo.tav.creditos.LSTTIPORECARVODocument.LSTTIPORECARVO;
import br.com.vivo.tav.creditos.TipoRecargaDocument.TipoRecarga;
import br.com.vivo.vol.acessos.vo.RELACIONAMENTOSDocument.RELACIONAMENTOS;
import com.indracompany.actions.AbstractAction;

@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
public class RelatoriosController extends AbstractAction {

    private static final long                 serialVersionUID = 8455490051769115949L;

    private static transient NonCatalogLogger log              = new NonCatalogLogger(RelatoriosController.class.getName());

    protected global.Global                   globalApp        = new global.Global();

    @EJB
    private RelatorioFacadeWF                 relatorioFacade;

    @EJB
    private RelatoriosFacadeVol               relatoriosFacade;

    private RelatorioLayout                   relatorioLayout;

    private RelatorioAcessosForm relatorioAcessosForm = new RelatorioAcessosForm();

    public RelatorioAcessosForm getRelatorioAcessosForm() {
        return this.relatorioAcessosForm;
    }

    public RelatorioLayout getRelatorioLayout() {
        if (this.relatorioLayout == null) {
            this.relatorioLayout = new RelatorioLayout();
        }
        return this.relatorioLayout;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if ("begin".equals(mapping.getParameter())) {
            return begin(mapping, form, request, response);
        } else if ("relatorioIphone".equals(mapping.getParameter())) {
            return relatorioIphone(mapping, form, request, response);
        } else if ("gerarRelatorioIphone".equals(mapping.getParameter())) {
            return gerarRelatorioIphone(mapping, form, request, response);
        } else if ("relatorioAcessos".equals(mapping.getParameter())) {
            return relatorioAcessos(mapping, form, request, response);
        } else if ("relatorioAcessosNegado".equals(mapping.getParameter())) {
            return relatorioAcessosNegado(mapping, form, request, response);
        } else if ("relatorioUtilizacaoServicos".equals(mapping.getParameter())) {
            return relatorioUtilizacaoServicos(mapping, form, request, response);
        } else if ("relatorioBaseClientes".equals(mapping.getParameter())) {
            return relatorioBaseClientes(mapping, form, request, response);
        } else if ("relatorioFinanceiroDetalhado".equals(mapping.getParameter())) {
            return relatorioFinanceiroDetalhado(mapping, form, request, response);
        } else if ("relatorioFinanceiro".equals(mapping.getParameter())) {
            return relatorioFinanceiro(mapping, form, request, response);
        } else if ("relatorioPrimeiroAcesso".equals(mapping.getParameter())) {
            return relatorioPrimeiroAcesso(mapping, form, request, response);
        } else if ("relatorioAcessoIncidencia".equals(mapping.getParameter())) {
            return relatorioAcessoIncidencia(mapping, form, request, response);
        } else if ("relatorioTempoPermanencia".equals(mapping.getParameter())) {
            return relatorioTempoPermanencia(mapping, form, request, response);
        } else if ("gerarRelatorioAcessos".equals(mapping.getParameter())) {
            return gerarRelatorioAcessos(mapping, form, request, response);
        } else if ("gerarRelatorioAcessosNegado".equals(mapping.getParameter())) {
            return gerarRelatorioAcessosNegado(mapping, form, request, response);
        } else if ("gerarRelatorioBaseClientes".equals(mapping.getParameter())) {
            return gerarRelatorioBaseClientes(mapping, form, request, response);
        } else if ("gerarRelatorioAcessosCliente".equals(mapping.getParameter())) {
            return gerarRelatorioAcessosCliente(mapping, form, request, response);
        } else if ("gerarRelatorioUtilizacaoServicos".equals(mapping.getParameter())) {
            return gerarRelatorioUtilizacaoServicos(mapping, form, request, response);
        } else if ("gerarRelatorioFinanceiro".equals(mapping.getParameter())) {
            return gerarRelatorioFinanceiro(mapping, form, request, response);
        } else if ("gerarRelatorioFinanceiroDetalhadoCompleto".equals(mapping.getParameter())) {
            return gerarRelatorioFinanceiroDetalhadoCompleto(mapping, form, request, response);
        } else if ("gerarRelatorioFinanceiroDetalhado".equals(mapping.getParameter())) {
            return gerarRelatorioFinanceiroDetalhado(mapping, form, request, response);
        } else if ("gerarRelatorioAcessoIncidencia".equals(mapping.getParameter())) {
            return gerarRelatorioAcessoIncidencia(mapping, form, request, response);
        } else if ("gerarRelatorioTempoPermanencia".equals(mapping.getParameter())) {
            return gerarRelatorioTempoPermanencia(mapping, form, request, response);
        } else if ("obterRegionais".equals(mapping.getParameter())) {
            return obterRegionais(mapping, form, request, response);
        } else if ("obterRegionaisTerminal".equals(mapping.getParameter())) {
            return obterRegionaisTerminal(mapping, form, request, response);
        } else if ("obterLojas".equals(mapping.getParameter())) {
            return obterLojas(mapping, form, request, response);
        } else if ("obterTerminais".equals(mapping.getParameter())) {
            return obterTerminais(mapping, form, request, response);
        } else if ("obterRecargas".equals(mapping.getParameter())) {
            return obterRecargas(mapping, form, request, response);
        } else if ("relatorioAcessosCliente".equals(mapping.getParameter())) {
            return relatorioAcessosCliente(mapping, form, request, response);
        } else if ("gerarRelatorioPrimeiroAcesso".equals(mapping.getParameter())) {
            return gerarRelatorioPrimeiroAcesso(mapping, form, request, response);
        } else if ("relatorioBloqueioURA".equals(mapping.getParameter())) {
            return relatorioBloqueioURA(mapping, form, request, response);
        } else if ("gerarRelatorioBloqueioURA".equals(mapping.getParameter())) {
            return gerarRelatorioBloqueioURA(mapping, form, request, response);
        }
        return begin(mapping, form, request, response);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="relatorioAcessos.do"
     */
    protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="relatorioIphone.jsp"
     */
    protected ActionForward relatorioIphone(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;
        log.debug("\t" + this.getClass().getName() + ":relatorioiPhone()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        getRelatorioAcessosForm().setFiltroRelatorio(relatorioFacade.obtemWFRelatoriosFiltrosVO(user));
        getRelatorioAcessosForm().reset();

        getRelatorioLayout().clear();

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="resultadoRelatorioIphone.jsp"
     */
    protected ActionForward gerarRelatorioIphone(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;

        log.debug("\t" + this.getClass().getName() + ":gerarRelatorioIphone()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        /******* Obtendo valores dos filtros para montar header do relatorio **********************/
        buscaHeader(form);
        relatorioAcessosForm = form;

        LinhaRelatorioIphone[] relatorio = relatoriosFacade.consultaRelatorioIphone(user, form.getDataInicio(), form.getDataFim(), form.getNumeroLinha(), form.getCodigoArea(), form.getRegionalSelecionada(), form.getOperadoraSelecionada(), form.getOptIn());

        // VOLTAVRelatorioIphoneVO relatorio = relatoriosFacade.consultaRelatorioIphone(user,form.getDataInicio(),form.getDataFim(),form.getNumeroLinha(),form.getCodigoArea(),form.getRegionalSelecionada(),form.getOperadoraSelecionada(),form.getOptIn());
        if (relatorio != null) {
            form.setRelatorioIphone(relatorio);
            relatorioAcessosForm.setRelatorioIphone(relatorio);
        }

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="relatorioAcessos.jsp"
     */
    protected ActionForward relatorioAcessos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;

        log.debug("\t" + this.getClass().getName() + ":relatorioAcessos()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        getRelatorioAcessosForm().setFiltroRelatorio(relatorioFacade.obtemWFRelatoriosFiltrosVO(user));
        getRelatorioAcessosForm().reset();

        getRelatorioLayout().clear();

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="relatorioAcessosNegado.jsp"
     */
    protected ActionForward relatorioAcessosNegado(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;
        log.debug("\t" + this.getClass().getName() + ":relatorioAcessosNegado()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        getRelatorioAcessosForm().setFiltroRelatorio(relatorioFacade.obtemWFRelatoriosFiltrosVO(user));

        getRelatorioAcessosForm().reset();

        getRelatorioLayout().clear();

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);

    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="relatorioUtilizacaoServicos.jsp"
     */
    protected ActionForward relatorioUtilizacaoServicos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;
        log.debug("\t" + this.getClass().getName() + ":relatorioUtilizacaoServicos()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        getRelatorioAcessosForm().setFiltroRelatorio(relatorioFacade.obtemWFRelatoriosFiltrosVO(user));

        getRelatorioAcessosForm().reset();
        // VOLTAVRelatorioServicosDisponveisVO servicosVOL = relatoriosFacade.consultaServicosDisponiveis(user, "15");
        // form.setServicosDisponiveisVOL(servicosVOL.getVOLTAVRelatorioServicosDisponveisArray());
        // VOLTAVRelatorioServicosDisponveisVO servicosTAV = relatoriosFacade.consultaServicosDisponiveis(user, "13");
        // form.setServicosDisponiveisTAV(servicosTAV.getVOLTAVRelatorioServicosDisponveisArray());

        VOLTAVRelatorioServicosDisponveisVO servicosVOLTAV = relatoriosFacade.consultaServicosDisponiveis(user, false);
        getRelatorioAcessosForm().setServicosDisponiveis(servicosVOLTAV.getVOLTAVRelatorioServicosDisponveisArray());

        getRelatorioLayout().clear();

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);

    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="relatorioBaseClientes.jsp"
     */
    protected ActionForward relatorioBaseClientes(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;
        log.debug("\t" + this.getClass().getName() + ":relatorioBaseClientes()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        getRelatorioAcessosForm().setFiltroRelatorio(relatorioFacade.obtemWFRelatoriosFiltrosVO(user));

        getRelatorioAcessosForm().reset();

        getRelatorioLayout().clear();

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);

    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="relatorioFinanceiroDetalhado.jsp"
     */
    protected ActionForward relatorioFinanceiroDetalhado(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;
        log.debug("\t" + this.getClass().getName() + ":relatorioFinanceiroDetalhado()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        /**************** Filtro de Lojas ********************************************************/
        TreeMap tmLojas = new TreeMap();
        tmLojas.put("-1", "Todos");
        VOLTAVRelatorioLojasVO listaLojas = relatoriosFacade.consultaLojasDisponiveis(user);

        for (int i = 0; i < listaLojas.sizeOfVOLTAVRelatorioLojasArray(); i++) {
            tmLojas.put(listaLojas.getVOLTAVRelatorioLojasArray(i).getIdPessoaDePara(), listaLojas.getVOLTAVRelatorioLojasArray(i).getDsLoja());
        }

        form.setLojas(tmLojas);

        form.setFiltroRelatorio(relatorioFacade.obtemWFRelatoriosFiltrosVO(user));

        /*************** Filtro de Tipo de Serviço ***************************************************/
        TreeMap tmTipoServ = new TreeMap();
        tmTipoServ.put("-1", "Todos");

        tmTipoServ.put(ConstantesCRM.COD_TPSERV_PGCONTA, "Pagamento de Conta");
        tmTipoServ.put(ConstantesCRM.COD_TPSERV_RECARGA, "Recarga");

        form.setTipoServico(tmTipoServ);

        /**************** Filtro de Status da Venda ********************************************************/
        TreeMap tmStatusVenda = new TreeMap();
        tmStatusVenda.put("-1", "Todos");
        VOLTAVRelatorioStatusVendaVO listaStatusVenda = relatoriosFacade.consultaStatusVenda(user);

        for (int i = 0; i < listaStatusVenda.sizeOfVOLTAVRelatorioStatusVendaArray(); i++) {
            tmStatusVenda.put(listaStatusVenda.getVOLTAVRelatorioStatusVendaArray(i).getIdStatusVenda(), listaStatusVenda.getVOLTAVRelatorioStatusVendaArray(i).getDsStatusVenda());
        }

        form.setStatusVenda(tmStatusVenda);
        //

        // consultar estados
        TreeMap tmEstado = new TreeMap();
        tmEstado.put("-1", "Todos");
        tmEstado.put("0", "Operação com erro");
        tmEstado.put("1", "Operação com Sucesso");
        form.setEstado(tmEstado);

        // consultar tipos de erro Status Sitef Venda
        VOLTAVRelatorioTipoErroVO tipoErroVO = relatoriosFacade.consultaTipoErro(user);
        TreeMap tmTipoErro = new TreeMap();

        tmTipoErro.put("-1", "Todos");

        for (int i = 0; i < tipoErroVO.getVOLTAVRelatorioTipoErroArray().length; i++) {
            VOLTAVRelatorioTipoErro tipoerro = tipoErroVO.getVOLTAVRelatorioTipoErroArray(i);
            tmTipoErro.put(tipoerro.getIdStatusSitefVenda(), tipoerro.getDsStatusSitefVenda());
        }
        form.setTipoErro(tmTipoErro);
        form.reset();

        getRelatorioLayout().clear();

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);

    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="relatorioFinanceiro.jsp"
     */
    protected ActionForward relatorioFinanceiro(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;

        log.debug("\t" + this.getClass().getName() + ":relatorioFinanceiro()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        /**************** Filtro de Lojas ********************************************************/
        TreeMap tmLojas = new TreeMap();
        tmLojas.put("-1", "Todos");
        VOLTAVRelatorioLojasVO listaLojas = relatoriosFacade.consultaLojasDisponiveis(user);

        for (int i = 0; i < listaLojas.sizeOfVOLTAVRelatorioLojasArray(); i++) {
            tmLojas.put(listaLojas.getVOLTAVRelatorioLojasArray(i).getIdPessoaDePara(), listaLojas.getVOLTAVRelatorioLojasArray(i).getDsLoja());
        }

        getRelatorioAcessosForm().setLojas(tmLojas);

        getRelatorioAcessosForm().setFiltroRelatorio(relatorioFacade.obtemWFRelatoriosFiltrosVO(user));

        /*************** Filtro de Tipo de Serviço ***************************************************/
        TreeMap tmTipoServ = new TreeMap();
        tmTipoServ.put("-1", "Todos");

        tmTipoServ.put(ConstantesCRM.COD_TPSERV_PGCONTA, "Pagamento de Conta");
        tmTipoServ.put(ConstantesCRM.COD_TPSERV_RECARGA, "Recarga");

        getRelatorioAcessosForm().setTipoServico(tmTipoServ);

        /**************** Filtro de Status da Venda ********************************************************/
        TreeMap tmStatusVenda = new TreeMap();
        tmStatusVenda.put("-1", "Todos");
        VOLTAVRelatorioStatusVendaVO listaStatusVenda = relatoriosFacade.consultaStatusVenda(user);

        for (int i = 0; i < listaStatusVenda.sizeOfVOLTAVRelatorioStatusVendaArray(); i++) {
            tmStatusVenda.put(listaStatusVenda.getVOLTAVRelatorioStatusVendaArray(i).getIdStatusVenda(), listaStatusVenda.getVOLTAVRelatorioStatusVendaArray(i).getDsStatusVenda());
        }

        getRelatorioAcessosForm().setStatusVenda(tmStatusVenda);
        //

        getRelatorioAcessosForm().reset();

        getRelatorioLayout().clear();

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);

    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="relatorioPrimeiroAcesso.jsp"
     */
    protected ActionForward relatorioPrimeiroAcesso(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;

        log.debug("\t" + this.getClass().getName() + ":relatorioPrimeiroAcesso()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        getRelatorioAcessosForm().setFiltroRelatorio(relatorioFacade.obtemWFRelatoriosFiltrosVO(user));

        getRelatorioAcessosForm().reset();

        getRelatorioLayout().clear();

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);

    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="relatorioAcessoIncidencia.jsp"
     */
    protected ActionForward relatorioAcessoIncidencia(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("\t" + this.getClass().getName() + ":relatorioAcessoIncidencia()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        getRelatorioAcessosForm().setFiltroRelatorio(relatorioFacade.obtemWFRelatoriosFiltrosVO(user));
        getRelatorioAcessosForm().reset();

        getRelatorioLayout().clear();

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);

    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="relatorioTempoPermanencia.jsp"
     */
    protected ActionForward relatorioTempoPermanencia(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;

        log.debug("\t" + this.getClass().getName() + ":relatorioTempoPermanencia()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        getRelatorioAcessosForm().setFiltroRelatorio(relatorioFacade.obtemWFRelatoriosFiltrosVO(user));

        getRelatorioAcessosForm().reset();

        getRelatorioLayout().clear();

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);

    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="successDia" path="resultadoRelatorioAcessosDia.jsp"
     * @jpf:forward name="successFaixaHorario" path="resultadoRelatorioAcessosFaixaHorario.jsp"
     * @jpf:forward name="successFaixaHorarioTabela" path="resultadoRelatorioAcessosFaixaHorarioTabela.jsp"
     */
    protected ActionForward gerarRelatorioAcessos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;
        try {
            log.warning("\t" + this.getClass().getName() + ":gerarRelatorioAcessos() STARTED");
            String user = ConstantesCRM.getCurrentUser(request.getSession());

            String graphPath = ConstantesCRM.SVAZIO;

            /************ Adicionado por Decio 14/03/2005 *********************************************/
            /******* Obtendo valores dos filtros para montar header do relatorio **********************/
            buscaHeader(form);
            /******************* Fim ******************************************************************/
            relatorioAcessosForm = form;

            log.warning("gerarRelatorioAcessos() passou buscaHeader(form);");
            if ("D".equalsIgnoreCase(form.getSumarizacao())) {
                /***** Modificado por Decio 14/03/2005 ***/
            	getRelatorioAcessosForm().setNmSumarizado("Dia");
                // VOLTAVRelatorioAcessoDiarioVO relatorio = this.relatoriosFacade.consultaRelatorioAcessosDiario(user,
                // form.getDataInicio(), form.getDataFim(), form.getOperadoraSelecionada(),
                // form.getRegionalSelecionada(), form.getCarteiraSelecionada(), form.getSegmentoSelecionada());
                VOLTAVRelatorioAcessoDiarioVO relatorio = this.relatoriosFacade.consultaRelatorioAcessosDiario(user, form.getDataInicio(), form.getDataFim(), form.getOperadoraSelecionada(),
                        form.getRegionalSelecionada(), form.getCarteiraSelecionada(), form.getSegmentoSelecionada(), form.getCanalSelecionado(), form.getTipoLinhaSelecionado(), form.getCodigoArea()
                                + form.getNumeroLinha(), form.getIsAgrupado(), form.getTecnologiaSelecionada(), form.getLojaSelecionada(), form.getTerminalSelecionado());

                if (relatorio != null) {
                	getRelatorioAcessosForm().setRelatorioAcessoDiario(relatorio.getVOLTAVRelatorioAcessoDiarioArray());
                    relatorioAcessosForm.setRelatorioAcessoDiario(relatorio.getVOLTAVRelatorioAcessoDiarioArray());
                }

                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                return mapping.findForward("successDia");

            } else {
                /***** Modificado por Decio 14/03/2005 ***/
            	getRelatorioAcessosForm().setNmSumarizado("Faixa Horário");
                log.warning("gerarRelatorioAcessos() CHAMADA SERVIçO");
                // VOLTAVRelatorioAcessoFaixaHorarioVO relatorio =
                // this.relatoriosFacade.consultaRelatorioAcessosFaixaHorario(user, form.getDataInicio(),
                // form.getDataFim(), form.getOperadoraSelecionada(), form.getRegionalSelecionada(),
                // form.getCarteiraSelecionada(), form.getSegmentoSelecionada());
                VOLTAVRelatorioAcessoFaixaHorarioVO relatorio = this.relatoriosFacade.consultaRelatorioAcessosFaixaHorario(user, form.getDataInicio(), form.getDataFim(),
                        form.getOperadoraSelecionada(), form.getRegionalSelecionada(), form.getCarteiraSelecionada(), form.getSegmentoSelecionada(), form.getCanalSelecionado(),
                        form.getTipoLinhaSelecionado(), form.getCodigoArea() + form.getNumeroLinha(), form.getIsAgrupado(), form.getTecnologiaSelecionada(), form.getLojaSelecionada(),
                        form.getTerminalSelecionado());

                log.warning("gerarRelatorioAcessos() PASSOU CHAMADA SERVIÇO");
                if (relatorio != null) {
                    log.warning("gerarRelatorioAcessos() RETORNO SERVICO != NULL ");
                    if ("0".equals(form.getIsGrafico())) {
                    	getRelatorioAcessosForm().setRelatorioAcessoFaixaHorario(relatorio.getVOLTAVRelatorioAcessoFaixaHorarioArray());
                        relatorioAcessosForm.setRelatorioAcessoFaixaHorario(relatorio.getVOLTAVRelatorioAcessoFaixaHorarioArray());
                        log.warning("gerarRelatorioAcessos() END isGrafico = 0");
                        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                        return mapping.findForward("successFaixaHorarioTabela");

                    } else {
                    	getRelatorioAcessosForm().setRelatorioAcessoFaixaHorario(relatorio.getVOLTAVRelatorioAcessoFaixaHorarioArray(), graphPath);
                        relatorioAcessosForm.setRelatorioAcessoFaixaHorario(relatorio.getVOLTAVRelatorioAcessoFaixaHorarioArray(), graphPath);
                    }
                }

                request.setAttribute("graficoAcessoFaixaHorarioVOL", form.getGraficoAcessoFaixaHorarioVOL());
                request.setAttribute("graficoAcessoFaixaHorarioTAV", form.getGraficoAcessoFaixaHorarioTAV());
                log.warning("gerarRelatorioAcessos() END isGrafico != 0");
            }

        } catch (Exception e) {
            log.error("gerarRelatorioAcessos() Exception ->" + e.getMessage(), e);
            e.printStackTrace();
            throw e;
        }

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward("successFaixaHorario");
    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="resultadoRelatorioAcessosNegado.jsp"
     */
    protected ActionForward gerarRelatorioAcessosNegado(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;


        log.debug("\t" + this.getClass().getName() + ":gerarRelatorioAcessosNegado()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        /************ Adicionado por Decio 14/03/2005 *********************************************/
        /******* Obtendo valores dos filtros para montar header do relatorio **********************/
        buscaHeader(form);
        /******************* Fim ******************************************************************/
        relatorioAcessosForm = form;

        /***** Modificado por Decio 14/03/2005 ***/
        // VOLTAVRelatorioAcessoNegadoVO relatorio = this.relatoriosFacade.consultaRelatorioAcessosNegado(user,
        // form.getDataInicio(), form.getDataFim(), form.getOperadoraSelecionada(), form.getRegionalSelecionada(),
        // form.getCarteiraSelecionada(), form.getSegmentoSelecionada());
        VOLTAVRelatorioAcessoNegadoVO relatorio = this.relatoriosFacade.consultaRelatorioAcessosNegado(user, form.getDataInicio(), form.getDataFim(), form.getOperadoraSelecionada(),
                form.getRegionalSelecionada(), form.getCarteiraSelecionada(), form.getSegmentoSelecionada(), form.getCanalSelecionado(), form.getTipoLinhaSelecionado(),
                form.getCodigoArea() + form.getNumeroLinha(), form.getIsAgrupado(), form.getTecnologiaSelecionada(), form.getLojaSelecionada(), form.getTerminalSelecionado());

        if (relatorio != null) {
            form.setRelatorioAcessoNegado(relatorio.getVOLTAVRelatorioAcessoNegadoArray());
            relatorioAcessosForm.setRelatorioAcessoNegado(relatorio.getVOLTAVRelatorioAcessoNegadoArray());
        }

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);

    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="resultadoRelatorioBaseClientes.jsp"
     */
    protected ActionForward gerarRelatorioBaseClientes(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;

        log.debug("\t" + this.getClass().getName() + ":gerarRelatorioBaseClientes()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        /************ Adicionado por Decio 14/03/2005 *********************************************/
        /******* Obtendo valores dos filtros para montar header do relatorio **********************/
        buscaHeader(form);
        /******************* Fim ******************************************************************/
        relatorioAcessosForm = form;

        VOLTAVRelatorioAdesoesVO relatorio = this.relatoriosFacade.consultaRelatorioAdesoesBaseClientes(user, form.getDataInicio(), form.getDataFim(), form.getOperadoraSelecionada(),
                form.getRegionalSelecionada(), form.getCarteiraSelecionada(), form.getSegmentoSelecionada(), form.getCanalSelecionado(), form.getTipoLinhaSelecionado(),
                form.getTecnologiaSelecionada(), form.getLojaSelecionada(), form.getTerminalSelecionado());

        if (relatorio != null) {
            form.setRelatorioAdesoesBaseClientes(relatorio.getVOLTAVRelatorioAdesoesArray());
            relatorioAcessosForm.setRelatorioAdesoesBaseClientes(relatorio.getVOLTAVRelatorioAdesoesArray());
        }

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);

    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="resultadoRelatorioAcessosCliente.jsp"
     */
    protected ActionForward gerarRelatorioAcessosCliente(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;

        log.debug("\t" + this.getClass().getName() + ":gerarRelatorioAcessosCliente()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        /************ Adicionado por Decio 14/03/2005 *********************************************/
        /******* Obtendo valores dos filtros para montar header do relatorio **********************/
        form.setNmNumeroRegistro("(" + form.getCodigoArea() + ") " + form.getNumeroLinha());
        getRelatorioAcessosForm().setNmNumeroRegistro(form.getNmNumeroRegistro());

        java.util.Date hoje = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        getRelatorioAcessosForm().setDtAtual(formatter.format(hoje));
        /******************* Fim ******************************************************************/

        RELACIONAMENTOS[] relatorio = relatoriosFacade.consultaRelatorioAcessosCliente(user, form.getCodigoArea(), form.getNumeroLinha(), form.getDataInicio(), form.getDataFim());

        getRelatorioAcessosForm().setRelatorioAcessoCliente(relatorio);
        getRelatorioAcessosForm().setDataInicio(form.getDataInicio());
        getRelatorioAcessosForm().setDataFim(form.getDataFim());

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);

    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="resultadoRelatorioUtilizacaoServicos.jsp"
     */
    protected ActionForward gerarRelatorioUtilizacaoServicos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;

        log.debug("\t" + this.getClass().getName() + ":gerarRelatorioUtilizacaoServicos()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        String idContato = form.getServicoSelecionado();

        /************ Adicionado por Decio 14/03/2005 *********************************************/
        /******* Obtendo valores dos filtros para montar header do relatorio **********************/
        buscaHeader(form);
        /******************* Fim ******************************************************************/
        relatorioAcessosForm = form;

        /***** Modificado por Decio 14/03/2005 ***/
        // VOLTAVRelatorioServicosEfetuadosVO relatorio =
        // this.relatoriosFacade.consultaRelatorioUtilizacaoServicos(user, form.getDataInicio(), form.getDataFim(),
        // form.getOperadoraSelecionada(), form.getRegionalSelecionada(), form.getCarteiraSelecionada(),
        // form.getSegmentoSelecionada(), form.getCanalSelecionado(), idContato);
        VOLTAVRelatorioServicosEfetuadosVO relatorio = this.relatoriosFacade.consultaRelatorioUtilizacaoServicos(user, form.getDataInicio(), form.getDataFim(), form.getOperadoraSelecionada(),
                form.getRegionalSelecionada(), form.getCarteiraSelecionada(), form.getSegmentoSelecionada(), idContato, form.getCanalSelecionado(), form.getTipoLinhaSelecionado(),
                form.getCodigoArea() + form.getNumeroLinha(), form.getIsAgrupado(), form.getTecnologiaSelecionada(), form.getOperadoraTerminalSelecionada(), form.getRegionalTerminalSelecionada(),
                form.getLojaSelecionada(), form.getTerminalSelecionado());

        if (relatorio != null) {
            form.setRelatorioUtilizacaoServicos(relatorio.getVOLTAVRelatorioServicosEfetuadosArray());
            relatorioAcessosForm.setRelatorioUtilizacaoServicos(relatorio.getVOLTAVRelatorioServicosEfetuadosArray());

            if (relatorio.getVOLTAVRelatorioServicosEfetuadosArray() != null) {
                long vlrVOLTotalServicos = 0;
                long vlrTAVTotalServicos = 0;
                form.setVlrVOLTotalServicos(ConstantesCRM.SVAZIO);
                form.setVlrTAVTotalServicos(ConstantesCRM.SVAZIO);
                for (int i = 0; i < relatorio.sizeOfVOLTAVRelatorioServicosEfetuadosArray(); i++) {
                    vlrVOLTotalServicos += Long.parseLong(relatorio.getVOLTAVRelatorioServicosEfetuadosArray()[i].getQtVOL());
                    vlrTAVTotalServicos += Long.parseLong(relatorio.getVOLTAVRelatorioServicosEfetuadosArray()[i].getQtTAV());

                    form.setVlrVOLTotalServicos(String.valueOf(vlrVOLTotalServicos));
                    form.setVlrTAVTotalServicos(String.valueOf(vlrTAVTotalServicos));
                    // relatorio.getVOLTAVRelatorioServicosEfetuadosArray()[i].setVlrVOLTotalServicos("");

                }
            }
            form.setRelatorioUtilizacaoServicos(relatorio.getVOLTAVRelatorioServicosEfetuadosArray());

        }

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);

    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="resultadoRelatorioFinanceiro.jsp"
     */
    protected ActionForward gerarRelatorioFinanceiro(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;
        log.debug("\t" + this.getClass().getName() + ":gerarRelatorioFinanceiro()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        /************ Adicionado por Decio 14/03/2005 *********************************************/
        /******* Obtendo valores dos filtros para montar header do relatorio **********************/
        buscaHeader(form);
        /******************* Fim ******************************************************************/
        relatorioAcessosForm = form;

        VOLTAVRelatorioFinanceiroVO relatorio = this.relatoriosFacade.consultaRelatorioFinanceiro(user, form.getDataInicio(), form.getDataFim(), form.getOperadoraSelecionada(),
                form.getRegionalSelecionada(), form.getLojaSelecionada(), form.getServicoSelecionado(), form.getRecargaSelecionada(), form.getStatusVendaSelecionado(),
                form.getTerminalSelecionado());

        if (relatorio != null) {
            if (relatorio.getVOLTAVRelatorioFinanceiroArray() != null) {
                String vlrCartao = ConstantesCRM.SVAZIO;
                double vlrTotal = 0D;
                form.setVlrTotalFinanceiro(ConstantesCRM.SVAZIO);
                relatorioAcessosForm.setVlrTotalFinanceiro(ConstantesCRM.SVAZIO);
                for (int i = 0; i < relatorio.sizeOfVOLTAVRelatorioFinanceiroArray(); i++) {
                    vlrCartao = relatorio.getVOLTAVRelatorioFinanceiroArray()[i].getVlCartao();
                    vlrTotal += Double.parseDouble(vlrCartao);
                    form.setVlrTotalFinanceiro(formatarDecimal(String.valueOf(vlrTotal)));
                    relatorioAcessosForm.setVlrTotalFinanceiro(formatarDecimal(String.valueOf(vlrTotal)));
                    relatorio.getVOLTAVRelatorioFinanceiroArray()[i].setVlCartao(formatarDecimal(vlrCartao));
                }
            }
            form.setRelatorioFinanceiro(relatorio.getVOLTAVRelatorioFinanceiroArray());
            relatorioAcessosForm.setRelatorioFinanceiro(relatorio.getVOLTAVRelatorioFinanceiroArray());
        }

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);

    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="resultadoRelatorioFinanceiroDetalheCompleto.jsp"
     */
    protected ActionForward gerarRelatorioFinanceiroDetalhadoCompleto(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;

        log.debug("\t" + this.getClass().getName() + ":gerarRelatorioFinanceiroDetalhadoCompleto()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        /******* Obtendo valores dos filtros para montar header do relatorio **********************/
        buscaHeader(form);
        /******************* Fim ******************************************************************/
        relatorioAcessosForm = form;

        if (form.getIndex() != null && !ConstantesCRM.SVAZIO.equals(form.getIndex())) {

            form.setRelatorioFinanceiroDetalhe(form.getRelatorioFinanceiro()[Integer.parseInt(form.getIndex())]);

        }

        VOLTAVOperacaoRecargaVO relatorio = this.relatoriosFacade.consultaRelatorioFinanceiroDetalhadoOperacaoRecarga(user, form.getRelatorioFinanceiroDetalhe().getIdSitefVenda());

        form.setOperacaoRecargaVO(relatorio.getVOLTAVOperacaoRecargaArray());

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);

    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="resultadoRelatorioFinanceiroDetalhe.jsp"
     */
    protected ActionForward gerarRelatorioFinanceiroDetalhado(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;

        log.debug("\t" + this.getClass().getName() + ":gerarRelatorioFinanceiroDetalhado()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        String nrLinha = ConstantesCRM.SVAZIO;
        String cdAreaRegistro = ConstantesCRM.SVAZIO;

        if (form.getLinha() != null && form.getLinha().length() > 12) {

            // Separa ddd do telefone.
            nrLinha = form.getLinha().substring(4, 8);
            nrLinha = nrLinha + form.getLinha().substring(9);
            cdAreaRegistro = form.getLinha().substring(1, 3);

        }

        /******* Obtendo valores dos filtros para montar header do relatorio **********************/
        buscaHeader(form);
        /******************* Fim ******************************************************************/
        relatorioAcessosForm = form;

        VOLTAVRelatorioFinanceiroVO relatorio = this.relatoriosFacade.consultaRelatorioFinanceiroDetalhado(user, form.getDataInicio(), form.getDataFim(), form.getOperadoraSelecionada(),
                form.getRegionalSelecionada(), form.getLojaSelecionada(), form.getTipoServicoSelecionado(), form.getRecargaSelecionada(), form.getStatusVendaSelecionado(),
                form.getTerminalSelecionado(), form.getTipoErroSelecionado(), nrLinha, cdAreaRegistro);

        if (relatorio != null) {
            if (relatorio.getVOLTAVRelatorioFinanceiroArray() != null) {
                String vlrCartao = ConstantesCRM.SVAZIO;
                double vlrTotal = 0D;
                form.setVlrTotalFinanceiro(ConstantesCRM.SVAZIO);
                relatorioAcessosForm.setVlrTotalFinanceiro(ConstantesCRM.SVAZIO);
                for (int i = 0; i < relatorio.sizeOfVOLTAVRelatorioFinanceiroArray(); i++) {
                    vlrCartao = relatorio.getVOLTAVRelatorioFinanceiroArray()[i].getVlCartao();
                    vlrTotal += Double.parseDouble(vlrCartao);
                    form.setVlrTotalFinanceiro(formatarDecimal(String.valueOf(vlrTotal)));
                    relatorioAcessosForm.setVlrTotalFinanceiro(formatarDecimal(String.valueOf(vlrTotal)));
                    relatorio.getVOLTAVRelatorioFinanceiroArray()[i].setVlCartao(formatarDecimal(vlrCartao));
                }
            }
            form.setRelatorioFinanceiro(relatorio.getVOLTAVRelatorioFinanceiroArray());
            relatorioAcessosForm.setRelatorioFinanceiro(relatorio.getVOLTAVRelatorioFinanceiroArray());
        }

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);

    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="resultadoRelatorioAcessoIncidencia.jsp"
     */
    protected ActionForward gerarRelatorioAcessoIncidencia(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;

        log.debug("\t" + this.getClass().getName() + ":gerarRelatorioAcessoIncidencia()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        /************ Adicionado por Decio 14/03/2005 *********************************************/
        /******* Obtendo valores dos filtros para montar header do relatorio **********************/
        buscaHeader(form);
        /******************* Fim ******************************************************************/
        relatorioAcessosForm = form;

        VOLTAVRelatorioAcessosIncidenciaVO relatorio = this.relatoriosFacade.consultaRelatorioAcessoIncidencia(user, form.getDataInicio(), form.getDataFim(), form.getOperadoraSelecionada(),
                form.getRegionalSelecionada(), form.getCarteiraSelecionada(), form.getSegmentoSelecionada(), form.getCanalSelecionado(), form.getTipoLinhaSelecionado(),
                form.getTecnologiaSelecionada(), form.getLojaSelecionada(), form.getTerminalSelecionado());

        if (relatorio != null) {
            form.setRelatorioAcessoIncidencia(relatorio.getVOLTAVRelatorioAcessosIncidenciaArray());
        }

        relatorioAcessosForm = form;
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);

    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="resultadoRelatorioTempoPermanencia.jsp"
     */
    protected ActionForward gerarRelatorioTempoPermanencia(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;

        log.debug("\t" + this.getClass().getName() + ":gerarRelatorioTempoPermanencia()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        /************ Adicionado por Decio 14/03/2005 *********************************************/
        /******* Obtendo valores dos filtros para montar header do relatorio **********************/
        buscaHeader(form);
        /******************* Fim ******************************************************************/
        relatorioAcessosForm = form;

        VOLTAVRelatorioTempoPermanenciaVO relatorio = this.relatoriosFacade.consultaRelatorioTempoPermanencia(user, form.getDataInicio(), form.getDataFim(), form.getOperadoraSelecionada(),
                form.getRegionalSelecionada(), form.getCarteiraSelecionada(), form.getSegmentoSelecionada(), form.getCanalSelecionado(), form.getTipoLinhaSelecionado(),
                form.getTecnologiaSelecionada(), form.getLojaSelecionada(), form.getTerminalSelecionado());

        if (relatorio != null) {
            form.setRelatorioTempoPermanencia(relatorio.getVOLTAVRelatorioTempoPermanenciaArray());
            relatorioAcessosForm.setRelatorioTempoPermanencia(relatorio.getVOLTAVRelatorioTempoPermanenciaArray());
        }

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="obterRegionais.jsp"
     */
    protected ActionForward obterRegionais(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;

        log.debug("\t" + this.getClass().getName() + ":obterRegionais()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        if (getRelatorioAcessosForm().getFiltroRelatorio() == null) {
            getRelatorioAcessosForm().setFiltroRelatorio(WFRelatoriosFiltrosVODocument.WFRelatoriosFiltrosVO.Factory.newInstance());
        }

        if (form.getOperadoraSelecionada().equals("-1")) {
            getRelatorioAcessosForm().getFiltroRelatorio().setWFRelatoriosFiltroRegionalVOArray(new WFRelatoriosFiltroRegionalVO[0]);
        } else {
            getRelatorioAcessosForm().getFiltroRelatorio().setWFRelatoriosFiltroRegionalVOArray(relatorioFacade.obtemRegionais(user, form.getOperadoraSelecionada()));
        }

        String key = form.getOperadoraSelecionada();
        form.setNmOperadora((String) getRelatorioAcessosForm().getOperadoras().get(key));
        getRelatorioAcessosForm().setOperadoraSelecionada(form.getOperadoraSelecionada());

        request.setAttribute("nomeCampo", request.getParameter("nomeCampo"));
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);

    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="obterRegionaisTerminal.jsp"
     */
    protected ActionForward obterRegionaisTerminal(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;

        log.debug("\t" + this.getClass().getName() + ":obterRegionaisTerminal()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        if (getRelatorioAcessosForm().getFiltroRelatorioTerminal() == null) {
        	getRelatorioAcessosForm().setFiltroRelatorioTerminal(WFRelatoriosFiltrosVODocument.WFRelatoriosFiltrosVO.Factory.newInstance());
        }

        if (form.getOperadoraTerminalSelecionada().equals("-1")) {
        	getRelatorioAcessosForm().getFiltroRelatorioTerminal().setWFRelatoriosFiltroRegionalVOArray(new WFRelatoriosFiltroRegionalVO[0]);
        } else {
        	getRelatorioAcessosForm().getFiltroRelatorioTerminal().setWFRelatoriosFiltroRegionalVOArray(relatorioFacade.obtemRegionais(user, form.getOperadoraTerminalSelecionada()));
        }

        String keyTerminal = form.getOperadoraTerminalSelecionada();
        getRelatorioAcessosForm().setNmOperadoraTerminal((String) getRelatorioAcessosForm().getOperadoras().get(keyTerminal));

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);

    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="obterLojas.jsp"
     */
    protected ActionForward obterLojas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;

        log.debug("\t" + this.getClass().getName() + ":obterLojas()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        if (form.getFiltroRelatorio() == null) {
            form.setFiltroRelatorio(WFRelatoriosFiltrosVODocument.WFRelatoriosFiltrosVO.Factory.newInstance());
        }

        if (ConstantesCRM.COD_CANAL_TAV.equals(form.getCanalSelecionado())) {
            getRelatorioAcessosForm().setLojasVO(relatoriosFacade.obtemLojas(user, form.getRegionalTerminalSelecionada()));
        } else {
            getRelatorioAcessosForm().setLojasVO(new VOLTAVRelatoriosFiltroLojaVO[0]);
        }
        getRelatorioAcessosForm().setCanalSelecionado(form.getCanalSelecionado());

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="obterTerminais.jsp"
     */
    protected ActionForward obterTerminais(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;

        log.debug("\t" + this.getClass().getName() + ":obterTerminais()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        if (form.getFiltroRelatorio() == null) {
            form.setFiltroRelatorio(WFRelatoriosFiltrosVODocument.WFRelatoriosFiltrosVO.Factory.newInstance());
        }

        if (form.getLojaSelecionada() != null && form.getLojaSelecionada().equals("-1")) {
            getRelatorioAcessosForm().setTerminaisVO(new VOLTAVRelatoriosFiltroTerminalVO[0]);
        } else {
            getRelatorioAcessosForm().setTerminaisVO(relatoriosFacade.obtemTerminais(user, form.getLojaSelecionada()));
        }
        getRelatorioAcessosForm().setLojaSelecionada(form.getLojaSelecionada());

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="obterRecargas.jsp"
     */
    protected ActionForward obterRecargas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;

        log.debug("\t" + this.getClass().getName() + ":obterRegionais()");
        form.setIsErro("false");
        String user = ConstantesCRM.getCurrentUser(request.getSession());

        TreeMap tmRecargas = new TreeMap();
        tmRecargas.put("-1", "Todos");

        try {
            if (form.getRegionalSelecionada() != null) {

                LSTTIPORECARVO listaRecargas = relatoriosFacade.consultaRecargasDisponiveis(user, form.getRegionalSelecionada());
                getRelatorioAcessosForm().setRecargas(listaRecargas.getTipoRecargaArray());
            }
        } catch (TuxedoException e) {
            form.setIsErro("true");
            getRelatorioAcessosForm().setRecargas(new TipoRecarga[0]);
            if (e.getXmlHeader().getStatusCode().equals("11W0001")) {
                request.setAttribute("mensagem", "Não existem tipos de recargas cadastrados para essa regional. Por favor escolha outra regional. ");
            }
        } catch (Exception e) {
            throw e;
        }

        // Recupera tambem as lojas
        obterLojas(mapping, form, request, response);
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="relatorioAcessosCliente.jsp"
     */
    protected ActionForward relatorioAcessosCliente(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;

        log.debug("\t" + getRelatorioAcessosForm().getClass().getName() + ":relatorioAcessosCliente()");

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /***********
     * Criado por Decio 15/03/2005 *******************************************
     * Método responsável pela preenchimento do header do relatorio
     */
    private void buscaHeader(RelatorioAcessosForm form) {
    	String key = form.getOperadoraSelecionada();
    	if (this.getRelatorioAcessosForm() != null && this.getRelatorioAcessosForm().getOperadoras() != null)
        form.setNmOperadora((String) this.getRelatorioAcessosForm().getOperadoras().get(key));

        key = form.getOperadoraTerminalSelecionada();

        if (key != null) {
            form.setNmOperadoraTerminal((String) getRelatorioAcessosForm().getOperadoras().get(key));
        }

        if (("-1".equals(form.getRegionalTerminalSelecionada())) || (form.getRegionalTerminalSelecionada() == null)) {
        	form.setNmRegionalTerminal("Todos");
        } else {
            for (int i = 0; i < getRelatorioAcessosForm().getFiltroRelatorioTerminal().getWFRelatoriosFiltroRegionalVOArray().length; i++) {
                if (form.getRegionalTerminalSelecionada().equals(getRelatorioAcessosForm().getFiltroRelatorioTerminal().getWFRelatoriosFiltroRegionalVOArray()[i].getIdRegional())) {
                	form.setNmRegionalTerminal(getRelatorioAcessosForm().getFiltroRelatorioTerminal().getWFRelatoriosFiltroRegionalVOArray()[i].getDsRegional());
                    break;
                }
            }
        }

        if ((getRelatorioAcessosForm().getTipoErro() != null) && (form.getTipoErroSelecionado() != null)) {
            key = form.getTipoErroSelecionado();
            form.setNmTipoErro((String) getRelatorioAcessosForm().getTipoErro().get(key));
        }

        if ((getRelatorioAcessosForm().getCarteiras() != null) && (form.getCarteiraSelecionada() != null)) {
            key = form.getCarteiraSelecionada();
            form.setNmCarteira(((String) getRelatorioAcessosForm().getCarteiras().get(key)));
        }

        if ((getRelatorioAcessosForm().getSegmentos() != null) && (form.getSegmentoSelecionada() != null)) {
            key = form.getSegmentoSelecionada();
            form.setNmSegmento(((String) getRelatorioAcessosForm().getSegmentos().get(key)));
        }

        if ((getRelatorioAcessosForm().getCanais() != null) && (form.getCanalSelecionado() != null)) {
            key = form.getCanalSelecionado();
            form.setNmCanal(((String) getRelatorioAcessosForm().getCanais().get(key)));
        }

        if ((getRelatorioAcessosForm().getTipoLinha() != null) && (form.getTipoLinhaSelecionado() != null)) {
            key = form.getTipoLinhaSelecionado();
            form.setNmTipoLinha(((String) getRelatorioAcessosForm().getTipoLinha().get(key)));
        }

        if ((getRelatorioAcessosForm().getServicosDisponiveis() != null) && (form.getServicoSelecionado() != null)) {
            key = form.getServicoSelecionado();
            form.setNmServico((String) getRelatorioAcessosForm().getServicosDisponiveis().get(key));
        }

        if ((getRelatorioAcessosForm().getTecnologias() != null) && (form.getTecnologiaSelecionada() != null)) {
            key = form.getTecnologiaSelecionada();
            form.setNmTecnologia(((String) getRelatorioAcessosForm().getTecnologias().get(key)));
        }

        if ("1".equals(form.getIsAgrupado())) {
            form.setNmAgrupado(ConstantesCRM.SYES);
        } else {
            form.setNmAgrupado("Não");
        }

        if ((form.getCodigoArea() != null) && (form.getNumeroLinha() != null) && (!form.getCodigoArea().equals("")) && (!form.getNumeroLinha().equals(""))) {
            form.setNmNumeroRegistro("(" + form.getCodigoArea() + ") " + form.getNumeroLinha());
        }

        if (("-1".equals(form.getTerminalSelecionado())) || (form.getTerminalSelecionado() == null)) {
            form.setNmTerminal("Todos");
        } else {
            for (int i = 0; i <= getRelatorioAcessosForm().getTerminaisVO().length; i++) {
                if (form.getTerminalSelecionado().equals(getRelatorioAcessosForm().terminaisVO[i].getNrTerminal())) {
                    form.setNmTerminal(getRelatorioAcessosForm().terminaisVO[i].getNrTerminal());
                    break;
                }
            }
        }

        if (("-1".equals(form.getLojaSelecionada())) || (form.getLojaSelecionada() == null)) {
            form.setNmLoja("Todas");
        } else {
            if (getRelatorioAcessosForm().lojasVO != null) {
                for (int i = 0; i <= getRelatorioAcessosForm().lojasVO.length; i++) {
                    if (form.getLojaSelecionada().equals(getRelatorioAcessosForm().lojasVO[i].getIdPessoaDePara())) {
                        form.setNmLoja(getRelatorioAcessosForm().lojasVO[i].getDsLoja());
                        break;
                    }
                }
            } else { // Relatorio financeiro - pega do outro VO
            	form.setNmLoja((String) getRelatorioAcessosForm().getLojas().get(form.getLojaSelecionada()));
            }
        }

        /**** Relatorio Financeiro ****/
        if ((getRelatorioAcessosForm().getLojas() != null) && (form.getLojaSelecionada() != null)) {
            key = form.getLojaSelecionada();
            form.setNmLoja((String) getRelatorioAcessosForm().getLojas().get(key));
        }

        if ((getRelatorioAcessosForm().getTipoServico() != null) && (form.getServicoSelecionado() != null)) {
            key = form.getServicoSelecionado();
            form.setNmTipoServico((String) getRelatorioAcessosForm().getTipoServico().get(key));
        } else {
        	form.setNmTipoServico("Todos");
        }

        if (("-1".equals(form.getRecargaSelecionada())) || (form.getRecargaSelecionada() == null)) {
            form.setNmRecarga("Todos");
        } else {
            for (int i = 0; i <= getRelatorioAcessosForm().getRecargas().length; i++) {
                if (form.getRecargaSelecionada().equals(getRelatorioAcessosForm().getRecargas()[i].getIdTipoRecarga())) {
                    form.setNmRecarga(getRelatorioAcessosForm().getRecargas()[i].getDsTipoRecarga());
                    break;
                }
            }
        }

        java.util.Date hoje = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        form.setDtAtual(formatter.format(hoje));

        getRelatorioLayout().setApenasTAV("none");
        getRelatorioLayout().setColspanTotal("1");
        /********* Decidindo quais td deverão ser apresentadas ******************/
        if ("-1".equals(form.getCanalSelecionado())) {
            getRelatorioLayout().setDisplayVOL("");
            getRelatorioLayout().setDisplayTAV("");
            if ("-1".equals(form.getTipoLinhaSelecionado())) {
                getRelatorioLayout().setColspanTAV("6");
                getRelatorioLayout().setColspanVOL("6");
                getRelatorioLayout().setDisplayVOLPRE("");
                getRelatorioLayout().setDisplayVOLPOS("");
                getRelatorioLayout().setDisplayVOLCON("");
                getRelatorioLayout().setDisplayTAVPRE("");
                getRelatorioLayout().setDisplayTAVPOS("");
                getRelatorioLayout().setDisplayTAVCON("");
            } else if (ConstantesCRM.COD_TPLINHA_PRE.equals(form.getTipoLinhaSelecionado())) {
                getRelatorioLayout().setColspanTAV("2");
                getRelatorioLayout().setColspanVOL("2");
                getRelatorioLayout().setDisplayVOLPRE("");
                getRelatorioLayout().setDisplayTAVPRE("");
                getRelatorioLayout().setDisplayVOLPOS("none");
                getRelatorioLayout().setDisplayTAVPOS("none");
                getRelatorioLayout().setDisplayVOLCON("none");
                getRelatorioLayout().setDisplayTAVCON("none");
            } else if (ConstantesCRM.COD_TPLINHA_POS.equals(form.getTipoLinhaSelecionado())) {
                getRelatorioLayout().setColspanTAV("2");
                getRelatorioLayout().setColspanVOL("2");
                getRelatorioLayout().setDisplayVOLPRE("none");
                getRelatorioLayout().setDisplayTAVPRE("none");
                getRelatorioLayout().setDisplayVOLPOS("");
                getRelatorioLayout().setDisplayTAVPOS("");
                getRelatorioLayout().setDisplayVOLCON("none");
                getRelatorioLayout().setDisplayTAVCON("none");
            } else if (ConstantesCRM.COD_TPLINHA_CON.equals(form.getTipoLinhaSelecionado())) {
                getRelatorioLayout().setColspanTAV("2");
                getRelatorioLayout().setColspanVOL("2");
                getRelatorioLayout().setDisplayVOLPRE("none");
                getRelatorioLayout().setDisplayTAVPRE("none");
                getRelatorioLayout().setDisplayVOLPOS("none");
                getRelatorioLayout().setDisplayTAVPOS("none");
                getRelatorioLayout().setDisplayVOLCON("");
                getRelatorioLayout().setDisplayTAVCON("");
            }
        } else if (ConstantesCRM.COD_CANAL_VOL.equals(form.getCanalSelecionado())) {
            getRelatorioLayout().setDisplayVOL("");
            getRelatorioLayout().setDisplayTAV("none");
            if ("-1".equals(form.getTipoLinhaSelecionado())) {
                getRelatorioLayout().setColspanTAV("5");
                getRelatorioLayout().setColspanVOL("5");
                getRelatorioLayout().setDisplayVOLPRE("");
                getRelatorioLayout().setDisplayVOLPOS("");
                getRelatorioLayout().setDisplayVOLCON("");
                getRelatorioLayout().setDisplayTAVPRE("none");
                getRelatorioLayout().setDisplayTAVPOS("none");
                getRelatorioLayout().setDisplayTAVCON("none");
            } else if (ConstantesCRM.COD_TPLINHA_PRE.equals(form.getTipoLinhaSelecionado())) {
                getRelatorioLayout().setColspanTAV("2");
                getRelatorioLayout().setColspanVOL("2");
                getRelatorioLayout().setDisplayVOLPRE("");
                getRelatorioLayout().setDisplayTAVPRE("none");
                getRelatorioLayout().setDisplayVOLPOS("none");
                getRelatorioLayout().setDisplayTAVPOS("none");
                getRelatorioLayout().setDisplayVOLCON("none");
                getRelatorioLayout().setDisplayTAVCON("none");
            } else if (ConstantesCRM.COD_TPLINHA_POS.equals(form.getTipoLinhaSelecionado())) {
                getRelatorioLayout().setColspanTAV("2");
                getRelatorioLayout().setColspanVOL("2");
                getRelatorioLayout().setDisplayVOLPRE("none");
                getRelatorioLayout().setDisplayTAVPRE("none");
                getRelatorioLayout().setDisplayVOLPOS("");
                getRelatorioLayout().setDisplayTAVPOS("none");
                getRelatorioLayout().setDisplayVOLCON("none");
                getRelatorioLayout().setDisplayTAVCON("none");
            } else if (ConstantesCRM.COD_TPLINHA_CON.equals(form.getTipoLinhaSelecionado())) {
                getRelatorioLayout().setColspanTAV("2");
                getRelatorioLayout().setColspanVOL("2");
                getRelatorioLayout().setDisplayVOLPRE("none");
                getRelatorioLayout().setDisplayTAVPRE("none");
                getRelatorioLayout().setDisplayVOLPOS("none");
                getRelatorioLayout().setDisplayTAVPOS("none");
                getRelatorioLayout().setDisplayVOLCON("");
                getRelatorioLayout().setDisplayTAVCON("none");
            }
        } else if (ConstantesCRM.COD_CANAL_TAV.equals(form.getCanalSelecionado())) {
            getRelatorioLayout().setDisplayVOL("none");
            getRelatorioLayout().setDisplayTAV("");
            if ("-1".equals(form.getTipoLinhaSelecionado())) {
                getRelatorioLayout().setColspanTAV("5");
                getRelatorioLayout().setColspanVOL("5");
                getRelatorioLayout().setDisplayTAVPRE("");
                getRelatorioLayout().setDisplayTAVPOS("");
                getRelatorioLayout().setDisplayTAVCON("");
            } else if (ConstantesCRM.COD_TPLINHA_PRE.equals(form.getTipoLinhaSelecionado())) {
                getRelatorioLayout().setColspanTAV("2");
                getRelatorioLayout().setColspanVOL("2");
                getRelatorioLayout().setDisplayVOLPRE("none");
                getRelatorioLayout().setDisplayTAVPRE("");
                getRelatorioLayout().setDisplayVOLPOS("none");
                getRelatorioLayout().setDisplayTAVPOS("none");
                getRelatorioLayout().setDisplayVOLCON("none");
                getRelatorioLayout().setDisplayTAVCON("none");
            } else if (ConstantesCRM.COD_TPLINHA_POS.equals(form.getTipoLinhaSelecionado())) {
                getRelatorioLayout().setColspanTAV("2");
                getRelatorioLayout().setColspanVOL("2");
                getRelatorioLayout().setDisplayVOLPRE("none");
                getRelatorioLayout().setDisplayTAVPRE("none");
                getRelatorioLayout().setDisplayVOLPOS("none");
                getRelatorioLayout().setDisplayTAVPOS("");
                getRelatorioLayout().setDisplayVOLCON("none");
                getRelatorioLayout().setDisplayTAVCON("none");
            } else if (ConstantesCRM.COD_TPLINHA_CON.equals(form.getTipoLinhaSelecionado())) {
                getRelatorioLayout().setColspanTAV("2");
                getRelatorioLayout().setColspanVOL("2");
                getRelatorioLayout().setDisplayVOLPRE("none");
                getRelatorioLayout().setDisplayTAVPRE("none");
                getRelatorioLayout().setDisplayVOLPOS("none");
                getRelatorioLayout().setDisplayTAVPOS("none");
                getRelatorioLayout().setDisplayVOLCON("none");
                getRelatorioLayout().setDisplayTAVCON("");
            }
            getRelatorioLayout().setApenasTAV("");
            getRelatorioLayout().setColspanTotal("3");

        }

        /* Verifica ESN + CHIP */
        if (form.getTecnologiaSelecionada() != null) {
            if (ConstantesCRM.COD_TECNOLOGIA_ESN.equals(form.getTecnologiaSelecionada())) {
                getRelatorioLayout().setTipoTecnologia(ConstantesCRM.DES_TECNOLOGIA_ESN);
            } else if (ConstantesCRM.COD_TECNOLOGIA_CHIP.equals(form.getTecnologiaSelecionada())) {
                getRelatorioLayout().setTipoTecnologia(ConstantesCRM.DES_TECNOLOGIA_CHIP);
            } else {
                getRelatorioLayout().setTipoTecnologia(ConstantesCRM.DES_TECNOLOGIA_TODAS);
            }
        }
    }

    /**
     * Formata uma string para decimal de acordo com a localidade atual
     * @param valor
     *            String
     * @return String
     */
    private static String formatarDecimal(String valor) {
        // se a variáve l estiver nula, retorna vazio
        if (valor == null) {
            valor = "0";
        }

        DecimalFormatSymbols dSymbols = new DecimalFormatSymbols();
        dSymbols.setDecimalSeparator(',');
        dSymbols.setGroupingSeparator('.');

        DecimalFormat dFormat = new DecimalFormat("00", dSymbols);

        dFormat.isDecimalSeparatorAlwaysShown();
        dFormat.applyPattern("###,##0.00;(###,##0.00)");
        return dFormat.format(new Double(valor));
    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="resultadoRelatorioPrimeiroAcesso.jsp"
     */
    protected ActionForward gerarRelatorioPrimeiroAcesso(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;

        log.debug("\t" + this.getClass().getName() + ":gerarRelatorioPrimeiroAcesso()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        /************ Adicionado por Decio 14/03/2005 *********************************************/
        /******* Obtendo valores dos filtros para montar header do relatorio **********************/
        buscaHeader(form);
        /******************* Fim ******************************************************************/
        relatorioAcessosForm = form;

        VOLTAVRelatorioPrimeiroAcessoVO relatorio = this.relatoriosFacade.consultaRelatorioPrimeiroAcesso(user, form.getDataInicio(), form.getDataFim(), form.getOperadoraSelecionada(),
                form.getRegionalSelecionada(), form.getCarteiraSelecionada(), form.getSegmentoSelecionada(), form.getCanalSelecionado(), form.getTipoLinhaSelecionado(),
                form.getCodigoArea() + form.getNumeroLinha(), form.getTecnologiaSelecionada(), form.getLojaSelecionada(), form.getTerminalSelecionado());

        if (relatorio != null) {
            form.setRelatorioPrimeiroAcesso(relatorio.getVOLTAVRelatorioPrimeiroAcessoArray());
            relatorioAcessosForm.setRelatorioPrimeiroAcesso(relatorio.getVOLTAVRelatorioPrimeiroAcessoArray());
            if (relatorio.sizeOfVOLTAVRelatorioPrimeiroAcessoArray() > 0) {
                form.setVlrTotalPrimeiroAcessoTAV(relatorio.getVOLTAVRelatorioPrimeiroAcessoArray()[0].getQtTotalClientesAcumuladosTAV());
                form.setVlrTotalPrimeiroAcessoVOL(relatorio.getVOLTAVRelatorioPrimeiroAcessoArray()[0].getQtTotalClientesAcumuladosVOL());
                relatorioAcessosForm.setVlrTotalPrimeiroAcessoTAV(relatorio.getVOLTAVRelatorioPrimeiroAcessoArray()[0].getQtTotalClientesAcumuladosTAV());
                relatorioAcessosForm.setVlrTotalPrimeiroAcessoVOL(relatorio.getVOLTAVRelatorioPrimeiroAcessoArray()[0].getQtTotalClientesAcumuladosVOL());
            }
        }

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);

    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="relatorioBloqueioURA.jsp"
     */
    protected ActionForward relatorioBloqueioURA(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;

        log.debug("\t" + this.getClass().getName() + ":relatorioBloqueioURA()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        getRelatorioAcessosForm().setFiltroRelatorio(relatorioFacade.obtemWFRelatoriosFiltrosVO(user));

        getRelatorioAcessosForm().reset();
        getRelatorioAcessosForm().setCanalSelecionado(ConstantesCRM.COD_CANAL_URA);

        VOLTAVRelatorioServicosDisponveisVO servicosVOLTAV = relatoriosFacade.consultaServicosDisponiveis(user, true);
        getRelatorioAcessosForm().setServicosDisponiveis(servicosVOLTAV.getVOLTAVRelatorioServicosDisponveisArray());

        getRelatorioLayout().clear();

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);

    }

    /**
     * @jpf:action form="relatorioAcessosForm"
     * @jpf:forward name="success" path="resultadoRelatorioBloqueioURA.jsp"
     */
    protected ActionForward gerarRelatorioBloqueioURA(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        RelatorioAcessosForm form = (RelatorioAcessosForm) formParam;

        log.debug("\t" + this.getClass().getName() + ":gerarRelatorioUtilizacaoServicos()");

        String user = ConstantesCRM.getCurrentUser(request.getSession());

        String idContato = form.getServicoSelecionado();

        /************ Adicionado por Decio 14/03/2005 *********************************************/
        /******* Obtendo valores dos filtros para montar header do relatorio **********************/
        buscaHeader(form);
        /******************* Fim ******************************************************************/
        relatorioAcessosForm = form;

        /***** Modificado por Decio 14/03/2005 ***/
        // VOLTAVRelatorioServicosEfetuadosVO relatorio =
        // this.relatoriosFacade.consultaRelatorioUtilizacaoServicos(user, form.getDataInicio(), form.getDataFim(),
        // form.getOperadoraSelecionada(), form.getRegionalSelecionada(), form.getCarteiraSelecionada(),
        // form.getSegmentoSelecionada(), form.getCanalSelecionado(), idContato);
        VOLTAVRelatorioServicosEfetuadosVO relatorio = this.relatoriosFacade.consultaRelatorioBloqueioURA(user, form.getDataInicio(), form.getDataFim(), form.getOperadoraSelecionada(),
                form.getRegionalSelecionada(), form.getCarteiraSelecionada(), form.getSegmentoSelecionada(), idContato, form.getCanalSelecionado(), form.getTipoLinhaSelecionado(),
                form.getCodigoArea() + form.getNumeroLinha(), form.getIsAgrupado(), form.getTecnologiaSelecionada());

        if (relatorio != null) {
            form.setRelatorioUtilizacaoServicos(relatorio.getVOLTAVRelatorioServicosEfetuadosArray());
            relatorioAcessosForm.setRelatorioUtilizacaoServicos(relatorio.getVOLTAVRelatorioServicosEfetuadosArray());
            if (relatorio.getVOLTAVRelatorioServicosEfetuadosArray() != null) {
                long vlrURATotalServicos = 0;
                form.setVlrURATotalServicos(ConstantesCRM.SVAZIO);
                relatorioAcessosForm.setVlrURATotalServicos(ConstantesCRM.SVAZIO);
                for (int i = 0; i < relatorio.sizeOfVOLTAVRelatorioServicosEfetuadosArray(); i++) {
                    vlrURATotalServicos += Long.parseLong(relatorio.getVOLTAVRelatorioServicosEfetuadosArray()[i].getQtURA());

                    form.setVlrURATotalServicos(String.valueOf(vlrURATotalServicos));
                    relatorioAcessosForm.setVlrURATotalServicos(String.valueOf(vlrURATotalServicos));

                }
            }
            form.setRelatorioUtilizacaoServicos(relatorio.getVOLTAVRelatorioServicosEfetuadosArray());

        }

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);

    }

    public class RelatorioLayout implements Serializable {

        private static final long serialVersionUID = 3392259562261448370L;

        private String            displayVOL       = "none";
        private String            displayTAV       = "none";

        private String            displayVOLPRE    = "none";
        private String            displayVOLPOS    = "none";
        private String            displayVOLCON    = "none";

        private String            displayTAVPRE    = "none";
        private String            displayTAVPOS    = "none";
        private String            displayTAVCON    = "none";

        private String            colspanVOL       = "4";
        private String            colspanTAV       = "4";

        private String            apenasTAV        = "none";
        private String            colspanTotal     = "1";

        private String            tipoTecnologia;

        public void clear() {
            this.displayVOL = "none";
            this.displayTAV = "none";
            this.displayVOLPRE = "none";
            this.displayVOLPOS = "none";
            this.displayVOLCON = "none";
            this.displayTAVPRE = "none";
            this.displayTAVPOS = "none";
            this.displayTAVCON = "none";
        }

        public String getDisplayVOL() {
            return this.displayVOL;
        }

        public void setDisplayVOL(String displayVOL) {
            this.displayVOL = displayVOL;
        }

        public String getDisplayTAV() {
            return this.displayTAV;
        }

        public void setDisplayTAV(String displayTAV) {
            this.displayTAV = displayTAV;
        }

        public String getDisplayVOLCON() {
            return this.displayVOLCON;
        }

        public void setDisplayVOLCON(String displayVOLCON) {
            this.displayVOLCON = displayVOLCON;
        }

        public String getDisplayTAVCON() {
            return this.displayTAVCON;
        }

        public void setDisplayTAVCON(String displayTAVCON) {
            this.displayTAVCON = displayTAVCON;
        }

        public String getDisplayVOLPRE() {
            return this.displayVOLPRE;
        }

        public void setDisplayVOLPRE(String displayVOLPRE) {
            this.displayVOLPRE = displayVOLPRE;
        }

        public String getDisplayVOLPOS() {
            return this.displayVOLPOS;
        }

        public void setDisplayVOLPOS(String displayVOLPOS) {
            this.displayVOLPOS = displayVOLPOS;
        }

        public String getDisplayTAVPRE() {
            return this.displayTAVPRE;
        }

        public void setDisplayTAVPRE(String displayTAVPRE) {
            this.displayTAVPRE = displayTAVPRE;
        }

        public String getDisplayTAVPOS() {
            return this.displayTAVPOS;
        }

        public void setDisplayTAVPOS(String displayTAVPOS) {
            this.displayTAVPOS = displayTAVPOS;
        }

        public String getColspanVOL() {
            return this.colspanVOL;
        }

        public void setColspanVOL(String colspanVOL) {
            this.colspanVOL = colspanVOL;
        }

        public String getColspanTAV() {
            return this.colspanTAV;
        }

        public void setColspanTAV(String colspanTAV) {
            this.colspanTAV = colspanTAV;
        }

        public void setTipoTecnologia(String tecnologia) {
            this.tipoTecnologia = tecnologia;
        }

        public String getTipoTecnologia() {
            return this.tipoTecnologia;
        }

        public void setColspanTotal(String s) {
            this.colspanTotal = s;
        }

        public String getColspanTotal() {
            return this.colspanTotal;
        }

        public void setApenasTAV(String s) {
            this.apenasTAV = s;
        }

        public String getApenasTAV() {
            return this.apenasTAV;
        }

    }

    public static class RelatorioAcessosForm extends ActionForm {

        private static final long                   serialVersionUID = 7327381924033916820L;

        private VOLTAVOperacaoRecarga[]             operacaoRecargaVO;
        private VOLTAVRelatorioFinanceiro           relatorioFinanceiroDetalhe;
        private String                              index;
        private String                              nmTipoErro;
        private String                              nmOperadoraTerminal;
        private String                              nmRegionalTerminal;
        private String                              vlrTAVTotalServicos;
        private String                              vlrVOLTotalServicos;
        private String                              vlrURATotalServicos;
        private String                              dsStatusVenda;
        private TreeMap                             statusVenda;
        private String                              codigoArea;
        private String                              numeroLinha;
        private String                              dataFim;
        private String                              dataInicio;
        private RELACIONAMENTOS[]                   relatorioAcessoCliente;
        private VOLTAVRelatorioAcessoDiario[]       relatorioAcessoDiario;
        private VOLTAVRelatorioAcessoFaixaHorario[] relatorioAcessoFaixaHorario;
        private VOLTAVRelatorioAcessoNegado[]       relatorioAcessoNegado;
        private VOLTAVRelatorioAdesoes[]            relatorioAdesoesBaseClientes;
        private VOLTAVRelatorioServicosEfetuados[]  relatorioUtilizacaoServicos;

        /****************** Adicionado por Decio 16/03/2005 *********************************/
        /****** Relatorio Financeiro ********************************************************/
        private VOLTAVRelatorioFinanceiro[]         relatorioFinanceiro;
        private String                              vlrTotalFinanceiro;
        private VOLTAVRelatorioPrimeiroAcesso[]     relatorioPrimeiroAcesso;
        private String                              vlrTotalPrimeiroAcessoTAV;
        private String                              vlrTotalPrimeiroAcessoVOL;
        private VOLTAVRelatorioAcessosIncidencia[]  relatorioAcessoIncidencia;
        private VOLTAVRelatorioTempoPermanencia[]   relatorioTempoPermanencia;
        /********************** Fim **********************************************************/

        private WFRelatoriosFiltrosVO               filtroRelatorio;
        private WFRelatoriosFiltrosVO               filtroRelatorioTerminal;
        private String                              operadoraSelecionada;
        private String                              operadoraTerminalSelecionada;
        private String                              regionalSelecionada;
        private String                              regionalTerminalSelecionada;
        private String                              carteiraSelecionada;
        private String                              segmentoSelecionada;
        private String                              servicoSelecionado;
        private String                              canalSelecionado;
        private String                              tecnologiaSelecionada;
        private String                              terminalSelecionado;
        private String                              ipTerminalSelecionado;
        private TreeMap                             operadoras;
        private TreeMap                             carteiras;
        private TreeMap                             segmentos;
        private TreeMap                             tecnologias;
        private TreeMap                             terminais;
        private LinkedHashMap                       servicosDisponiveis;
        private String                              sumarizacao;
        BarChartBean                                graficoAcessoFaixaHorarioVOL;
        BarChartBean                                graficoAcessoFaixaHorarioTAV;
        private int[]                               totaisRelatorioAdesoesBaseClientes;
        private int[]                               totaisRelatorioAcessoDiario;
        private int[]                               totaisRelatorioAcessoNegado;

        /************ Modificado por Decio 14/03/2005 ************************************/
        private TreeMap                             canais;
        private TreeMap                             tipoLinha;
        private TreeMap                             lojas;
        private TreeMap                             tipoServico;
        private TreeMap                             estado;
        private TreeMap                             tipoErro;
        private TipoRecarga[]                       recargas         = new TipoRecarga[0];
        private WFRelatoriosFiltroRegionalVO[]      fnRegionais      = new WFRelatoriosFiltroRegionalVO[0];
        private String                              recargaSelecionada;
        private String                              tipoLinhaSelecionado;
        private String                              lojaSelecionada;
        private String                              tipoServicoSelecionado;
        private String                              statusVendaSelecionado;
        private String                              estadoSelecionado;
        private String                              isAgrupado;
        private String                              isGrafico;
        private String                              listaVOL[];
        private String                              listaTAV[];

        /***** Dados para o cabeçalho *****************/
        private String                              nmOperadora;
        private String                              nmRegional;
        private String                              nmCarteira;
        private String                              nmSegmento;
        private String                              nmCanal;
        private String                              nmTipoLinha;
        private String                              nmServico;
        private String                              nmSumarizado;
        private String                              nmAgrupado;
        private String                              nmNumeroRegistro;
        private String                              nmLoja;
        private String                              nmTipoServico;
        private String                              nmRecarga;
        private String                              nmEstado;
        private String                              nmTecnologia;
        private String                              nmTerminal;
        private String                              tipoErroSelecionado;
        private String                              dtAtual;
        private String                              isErro           = "false";
        private String                              linha;
        VOLTAVRelatoriosFiltroLojaVO[]              lojasVO;
        VOLTAVRelatoriosFiltroTerminalVO[]          terminaisVO;

        // Adicionado para iPhone
        private LinhaRelatorioIphone[]              relatorioIphone;
        private String                              optIn;

        public void setOptIn(String optIn) {
            this.optIn = optIn;
        }

        public String getOptIn() {
            return this.optIn;
        }

        public LinhaRelatorioIphone[] getRelatorioIphone() {
            return this.relatorioIphone;
        }

        public void setRelatorioIphone(LinhaRelatorioIphone[] relatorioIphone) {
            this.relatorioIphone = relatorioIphone;
        }

        public void setLinha(String linha) {
            this.linha = linha;
        }

        public String getLinha() {
            return this.linha;
        }

        public void setTipoErroSelecionado(String tipoErroSelecionado) {
            this.tipoErroSelecionado = tipoErroSelecionado;
        }

        public String getTipoErroSelecionado() {
            return this.tipoErroSelecionado;
        }

        public void setTipoErro(TreeMap tipoErro) {
            this.tipoErro = tipoErro;
        }

        public TreeMap getTipoErro() {
            return this.tipoErro;
        }

        public void setLojasVO(VOLTAVRelatoriosFiltroLojaVO[] valor) {
            lojasVO = valor;
        }

        public VOLTAVRelatoriosFiltroLojaVO[] getLojasVO() {
            return lojasVO;
        }

        public void setTerminaisVO(VOLTAVRelatoriosFiltroTerminalVO[] valor) {
            terminaisVO = valor;
        }

        public VOLTAVRelatoriosFiltroTerminalVO[] getTerminaisVO() {
            return terminaisVO;
        }

        public String getIsErro() {
            return this.isErro;
        }

        public void setIsErro(String isErro) {
            this.isErro = isErro;
        }

        public String getNmLoja() {
            return this.nmLoja;
        }

        public void setNmLoja(String s) {
            this.nmLoja = s;
        }

        public String getNmTerminal() {
            return this.nmTerminal;
        }

        public void setNmTerminal(String s) {
            this.nmTerminal = s;
        }

        public String getNmTecnologia() {
            return this.nmTecnologia;
        }

        public void setNmTecnologia(String s) {
            this.nmTecnologia = s;
        }

        public String getNmEstado() {
            return this.nmEstado;
        }

        public String getEstadoSelecionado() {
            return this.estadoSelecionado;
        }

        public String getNmTipoServico() {
            return this.nmTipoServico;
        }

        public void setNmTipoServico(String s) {
            this.nmTipoServico = s;
        }

        public String getNmRecarga() {
            return this.nmRecarga;
        }

        public void setNmRecarga(String s) {
            this.nmRecarga = s;
        }

        public String getNmServico() {
            return this.nmServico;
        }

        public void setNmServico(String s) {
            this.nmServico = s;
        }

        public String getNmOperadora() {
            return this.nmOperadora;
        }

        public void setNmOperadora(String s) {
            this.nmOperadora = s;
        }

        public String getNmRegional() {
            return this.nmRegional;
        }

        public void setNmRegional(String s) {
            this.nmRegional = s;
        }

        public String getNmCarteira() {
            return this.nmCarteira;
        }

        public void setNmCarteira(String s) {
            this.nmCarteira = s;
        }

        public String getNmSegmento() {
            return this.nmSegmento;
        }

        public void setNmSegmento(String s) {
            this.nmSegmento = s;
        }

        public String getNmCanal() {
            return this.nmCanal;
        }

        public void setNmCanal(String s) {
            this.nmCanal = s;
        }

        public String getNmTipoLinha() {
            return this.nmTipoLinha;
        }

        public void setNmTipoLinha(String s) {
            this.nmTipoLinha = s;
        }

        public String getNmSumarizado() {
            return this.nmSumarizado;
        }

        public void setNmSumarizado(String s) {
            this.nmSumarizado = s;
        }

        public String getNmAgrupado() {
            return this.nmAgrupado;
        }

        public void setNmAgrupado(String s) {
            this.nmAgrupado = s;
        }

        public String getNmNumeroRegistro() {
            return this.nmNumeroRegistro;
        }

        public void setNmNumeroRegistro(String s) {
            this.nmNumeroRegistro = s;
        }

        public String getDtAtual() {
            return this.dtAtual;
        }

        public void setDtAtual(String s) {
            this.dtAtual = s;
        }

        public String getIsAgrupado() {
            return this.isAgrupado;
        }

        public void setIsAgrupado(String s) {
            this.isAgrupado = s;
        }

        public String getIsGrafico() {
            return this.isGrafico;
        }

        public void setIsGrafico(String s) {
            this.isGrafico = s;
        }

        public TreeMap getCanais() {
            return this.canais;
        }

        public void setCanais(TreeMap s) {
            this.canais = s;
        }

        public TreeMap getTipoLinha() {
            return this.tipoLinha;
        }

        public void setTipoLinha(TreeMap s) {
            this.tipoLinha = s;
        }

        public String getTipoLinhaSelecionado() {
            return this.tipoLinhaSelecionado;
        }

        public void setTipoLinhaSelecionado(String s) {
            this.tipoLinhaSelecionado = s;
        }

        public String[] getListaVOL() {
            return this.listaVOL;
        }

        public String[] getListaTAV() {
            return this.listaTAV;
        }

        public TipoRecarga[] getRecargas() {
            return this.recargas;
        }

        public void setRecargas(TipoRecarga[] s) {
            this.recargas = s;
        }

        public String getRecargaSelecionada() {
            return this.recargaSelecionada;
        }

        public void setRecargaSelecionada(String s) {
            this.recargaSelecionada = s;
        }

        public WFRelatoriosFiltroRegionalVO[] getFnRegionais() {
            return this.fnRegionais;
        }

        public void setFnRegionais(WFRelatoriosFiltroRegionalVO[] s) {
            this.fnRegionais = s;
        }

        public TreeMap getLojas() {
            return this.lojas;
        }

        public void setLojas(TreeMap s) {
            this.lojas = s;
        }

        public String getLojaSelecionada() {
            return this.lojaSelecionada;
        }

        public void setLojaSelecionada(String s) {
            this.lojaSelecionada = s;
        }

        public String getStatusVendaSelecionado() {
            return this.statusVendaSelecionado;
        }

        public void setStatusVendaSelecionado(String s) {
            this.statusVendaSelecionado = s;
        }

        public TreeMap getEstado() {
            return this.estado;
        }

        public TreeMap getTipoServico() {
            return this.tipoServico;
        }

        public void setEstado(TreeMap s) {
            this.estado = s;
        }

        public void setTipoServico(TreeMap s) {
            this.tipoServico = s;
        }

        public String getTipoServicoSelecionado() {
            return this.tipoServicoSelecionado;
        }

        public void setTipoServicoSelecionado(String s) {
            this.tipoServicoSelecionado = s;
        }

        public VOLTAVRelatorioFinanceiro[] getRelatorioFinanceiro() {
            return this.relatorioFinanceiro;
        }

        public void setRelatorioFinanceiro(VOLTAVRelatorioFinanceiro[] relatorio) {
            this.relatorioFinanceiro = relatorio;
        }

        public String getVlrTotalFinanceiro() {
            return this.vlrTotalFinanceiro;
        }

        public void setVlrTotalFinanceiro(String vlrTotalFinanceiro) {
            this.vlrTotalFinanceiro = vlrTotalFinanceiro;
        }

        public VOLTAVRelatorioPrimeiroAcesso[] getRelatorioPrimeiroAcesso() {
            return this.relatorioPrimeiroAcesso;
        }

        public void setRelatorioPrimeiroAcesso(VOLTAVRelatorioPrimeiroAcesso[] relatorioPrimeiroAcesso) {
            this.relatorioPrimeiroAcesso = relatorioPrimeiroAcesso;
        }

        public String getVlrTotalPrimeiroAcessoTAV() {
            return this.vlrTotalPrimeiroAcessoTAV;
        }

        public void setVlrTotalPrimeiroAcessoTAV(String vlrTotalPrimeiroAcessoTAV) {
            this.vlrTotalPrimeiroAcessoTAV = vlrTotalPrimeiroAcessoTAV;
        }

        public String getVlrTotalPrimeiroAcessoVOL() {
            return this.vlrTotalPrimeiroAcessoVOL;
        }

        public void setVlrTotalPrimeiroAcessoVOL(String vlrTotalPrimeiroAcessoVOL) {
            this.vlrTotalPrimeiroAcessoVOL = vlrTotalPrimeiroAcessoVOL;
        }

        public VOLTAVRelatorioAcessosIncidencia[] getRelatorioAcessoIncidencia() {
            return this.relatorioAcessoIncidencia;
        }

        public void setRelatorioAcessoIncidencia(VOLTAVRelatorioAcessosIncidencia[] relatorioAcessoIncidencia) {
            this.relatorioAcessoIncidencia = relatorioAcessoIncidencia;
        }

        public VOLTAVRelatorioTempoPermanencia[] getRelatorioTempoPermanencia() {
            return this.relatorioTempoPermanencia;
        }

        public void setRelatorioTempoPermanencia(VOLTAVRelatorioTempoPermanencia[] relatorioTempoPermanencia) {
            this.relatorioTempoPermanencia = relatorioTempoPermanencia;
        }

        public void setTecnologiaSelecionada(String tecnologia) {
            this.tecnologiaSelecionada = tecnologia;
        }

        public String getTecnologiaSelecionada() {
            return this.tecnologiaSelecionada;
        }

        public void setTerminalSelecionado(String terminal) {
            this.terminalSelecionado = terminal;
        }

        public String getTerminalSelecionado() {
            return this.terminalSelecionado;
        }

        public void setIpTerminalSelecionado(String terminal) {
            this.ipTerminalSelecionado = terminal;
        }

        public String getIpTerminalSelecionado() {
        	if (this.ipTerminalSelecionado == null || ConstantesCRM.SVAZIO.equals(this.ipTerminalSelecionado)) {
        		this.ipTerminalSelecionado = "Todos"; 
        	}
            return this.ipTerminalSelecionado;
        }

        public void reset() {
            this.operadoraSelecionada = null;
            this.regionalSelecionada = null;
            this.operadoraTerminalSelecionada = null;
            this.regionalTerminalSelecionada = null;
            this.segmentoSelecionada = null;
            this.carteiraSelecionada = null;
            this.dataInicio = null;
            this.dataFim = null;
            this.canalSelecionado = null;
            this.tipoLinhaSelecionado = null;
            this.codigoArea = null;
            this.numeroLinha = null;
            this.nmNumeroRegistro = null;
            this.isAgrupado = ConstantesCRM.SZERO;
            this.isGrafico = ConstantesCRM.SONE;
            this.vlrTotalPrimeiroAcessoTAV = null;
            this.vlrTotalPrimeiroAcessoVOL = null;
            this.nmRecarga = null;
            this.recargaSelecionada = null;
            this.nmEstado = null;
            this.tecnologiaSelecionada = null;
            this.lojaSelecionada = null;
            this.terminalSelecionado = null;
            this.ipTerminalSelecionado = "Todos";
            this.sumarizacao = "D";
            this.optIn = ConstantesCRM.SZERO;
        }

        /************ Fim Modificação ******************************************************/

        public TreeMap getOperadoras() {
            return this.operadoras;
        }

        public TreeMap getCarteiras() {
            return this.carteiras;
        }

        public TreeMap getSegmentos() {
            return this.segmentos;
        }

        public TreeMap getTecnologias() {
            return this.tecnologias;
        }

        public TreeMap getTerminais() {
            return this.terminais;
        }

        public void setFiltroRelatorio(WFRelatoriosFiltrosVO filtroRelatorio) {

            this.filtroRelatorio = filtroRelatorio;
            if (filtroRelatorio != null) {
                this.operadoras = new TreeMap();
                this.operadoras.put("-1", "Todos");

                for (int i = 0; i < filtroRelatorio.sizeOfWFRelatoriosFiltroOperadoraVOArray(); i++) {
                    this.operadoras.put(filtroRelatorio.getWFRelatoriosFiltroOperadoraVOArray(i).getIdOperadora(), filtroRelatorio.getWFRelatoriosFiltroOperadoraVOArray(i).getDsOperadora());
                }

                this.carteiras = new TreeMap();
                this.carteiras.put("-1", "Todos");

                for (int i = 0; i < filtroRelatorio.sizeOfWFRelatoriosFiltroCarteiraVOArray(); i++) {
                    this.carteiras.put(filtroRelatorio.getWFRelatoriosFiltroCarteiraVOArray(i).getIdCarteira(), filtroRelatorio.getWFRelatoriosFiltroCarteiraVOArray(i).getDsCarteira());
                }

                this.segmentos = new TreeMap();
                this.segmentos.put("-1", "Todos");

                for (int i = 0; i < filtroRelatorio.sizeOfWFRelatoriosFiltroSegmentoVOArray(); i++) {
                    this.segmentos.put(filtroRelatorio.getWFRelatoriosFiltroSegmentoVOArray(i).getIdSegmento(), filtroRelatorio.getWFRelatoriosFiltroSegmentoVOArray(i).getDsSegmento());
                }

                /************ Modificado por Decio 14/03/2005 ************************************/
                this.canais = new TreeMap();
                this.canais.put("-1", "Todos");
                this.canais.put(ConstantesCRM.COD_CANAL_VOL, "VOL");
                this.canais.put(ConstantesCRM.COD_CANAL_TAV, "TAV");
                this.canais.put(ConstantesCRM.COD_CANAL_URA, "URA");

                this.tipoLinha = new TreeMap();
                this.tipoLinha.put("-1", "Todos");
                this.tipoLinha.put(ConstantesCRM.COD_TPLINHA_PRE, "Pré-Pago");
                this.tipoLinha.put(ConstantesCRM.COD_TPLINHA_POS, "Pós-Pago");
                this.tipoLinha.put(ConstantesCRM.COD_TPLINHA_CON, "Controle");

                /************ Fim Modificação ******************************************************/

                this.tecnologias = new TreeMap();
                this.tecnologias.put("-1", "Todas");
                this.tecnologias.put(ConstantesCRM.COD_TECNOLOGIA_ESN, ConstantesCRM.DES_TECNOLOGIA_ESN);
                this.tecnologias.put(ConstantesCRM.COD_TECNOLOGIA_CHIP, ConstantesCRM.DES_TECNOLOGIA_CHIP);
            }
        }

        public void setFiltroRelatorioTerminal(WFRelatoriosFiltrosVO filtroRelatorioTerminal) {
            this.filtroRelatorioTerminal = filtroRelatorioTerminal;
        }

        public WFRelatoriosFiltrosVO getFiltroRelatorioTerminal() {
            return this.filtroRelatorioTerminal;
        }

        public WFRelatoriosFiltrosVO getFiltroRelatorio() {
            return this.filtroRelatorio;
        }

        public void setDataInicio(String dataInicio) {
            this.dataInicio = dataInicio;
        }

        public String getDataInicio() {
            return this.dataInicio;
        }

        public void setDataFim(String dataFim) {
            this.dataFim = dataFim;
        }

        public String getDataFim() {
            return this.dataFim;
        }

        public void setRelatorioAcessoDiario(VOLTAVRelatorioAcessoDiario[] relatorioAcessoDiario) {

            this.relatorioAcessoDiario = relatorioAcessoDiario;
            if (relatorioAcessoDiario != null) {
                this.totaisRelatorioAcessoDiario = new int[13];
                for (int i = 0; i < relatorioAcessoDiario.length; i++) {
                    totaisRelatorioAcessoDiario[0] += Integer.parseInt(relatorioAcessoDiario[i].getQtTotal());
                    totaisRelatorioAcessoDiario[1] += Integer.parseInt(relatorioAcessoDiario[i].getQtVOLPreCliente());
                    totaisRelatorioAcessoDiario[2] += Integer.parseInt(relatorioAcessoDiario[i].getQtVOLPreUsuario());
                    totaisRelatorioAcessoDiario[3] += Integer.parseInt(relatorioAcessoDiario[i].getQtVOLPosCliente());
                    totaisRelatorioAcessoDiario[4] += Integer.parseInt(relatorioAcessoDiario[i].getQtVOLPosUsuario());
                    totaisRelatorioAcessoDiario[5] += Integer.parseInt(relatorioAcessoDiario[i].getQtVOLConCliente());
                    totaisRelatorioAcessoDiario[6] += Integer.parseInt(relatorioAcessoDiario[i].getQtVOLConUsuario());
                    totaisRelatorioAcessoDiario[7] += Integer.parseInt(relatorioAcessoDiario[i].getQtTAVPreCliente());
                    totaisRelatorioAcessoDiario[8] += Integer.parseInt(relatorioAcessoDiario[i].getQtTAVPreUsuario());
                    totaisRelatorioAcessoDiario[9] += Integer.parseInt(relatorioAcessoDiario[i].getQtTAVPosCliente());
                    totaisRelatorioAcessoDiario[10] += Integer.parseInt(relatorioAcessoDiario[i].getQtTAVPosUsuario());
                    totaisRelatorioAcessoDiario[11] += Integer.parseInt(relatorioAcessoDiario[i].getQtTAVConCliente());
                    totaisRelatorioAcessoDiario[12] += Integer.parseInt(relatorioAcessoDiario[i].getQtTAVConUsuario());
                }
            }
        }

        public VOLTAVRelatorioAcessoDiario[] getRelatorioAcessoDiario() {
            return this.relatorioAcessoDiario;
        }

        public void setRelatorioAcessoNegado(VOLTAVRelatorioAcessoNegado[] relatorioAcessoNegado) {
            this.relatorioAcessoNegado = relatorioAcessoNegado;
            if (relatorioAcessoNegado != null) {
                this.totaisRelatorioAcessoNegado = new int[13];
                for (int i = 0; i < relatorioAcessoNegado.length; i++) {
                    totaisRelatorioAcessoNegado[0] += Integer.parseInt(relatorioAcessoNegado[i].getQtTotal());
                    totaisRelatorioAcessoNegado[1] += Integer.parseInt(relatorioAcessoNegado[i].getQtVOLPreCliente());
                    totaisRelatorioAcessoNegado[2] += Integer.parseInt(relatorioAcessoNegado[i].getQtVOLPreUsuario());
                    totaisRelatorioAcessoNegado[3] += Integer.parseInt(relatorioAcessoNegado[i].getQtVOLPosCliente());
                    totaisRelatorioAcessoNegado[4] += Integer.parseInt(relatorioAcessoNegado[i].getQtVOLPosUsuario());
                    totaisRelatorioAcessoNegado[5] += Integer.parseInt(relatorioAcessoNegado[i].getQtVOLConCliente());
                    totaisRelatorioAcessoNegado[6] += Integer.parseInt(relatorioAcessoNegado[i].getQtVOLConUsuario());
                    totaisRelatorioAcessoNegado[7] += Integer.parseInt(relatorioAcessoNegado[i].getQtTAVPreCliente());
                    totaisRelatorioAcessoNegado[8] += Integer.parseInt(relatorioAcessoNegado[i].getQtTAVPreUsuario());
                    totaisRelatorioAcessoNegado[9] += Integer.parseInt(relatorioAcessoNegado[i].getQtTAVPosCliente());
                    totaisRelatorioAcessoNegado[10] += Integer.parseInt(relatorioAcessoNegado[i].getQtTAVPosUsuario());
                    totaisRelatorioAcessoNegado[11] += Integer.parseInt(relatorioAcessoNegado[i].getQtTAVConCliente());
                    totaisRelatorioAcessoNegado[12] += Integer.parseInt(relatorioAcessoNegado[i].getQtTAVConUsuario());
                }
            }
        }

        public VOLTAVRelatorioAcessoNegado[] getRelatorioAcessoNegado() {
            return this.relatorioAcessoNegado;
        }

        public void setRelatorioAcessoFaixaHorario(VOLTAVRelatorioAcessoFaixaHorario[] relatorioAcessoFaixaHorario, String graphPath) throws Exception {
            try {
                log.warning("setRelatorioAcessoFaixaHorario() STARTED");
                this.relatorioAcessoFaixaHorario = relatorioAcessoFaixaHorario;
                if (relatorioAcessoFaixaHorario != null) {
                    BarChartBean graficoVOL = new BarChartBean();
                    BarChartBean graficoTAV = new BarChartBean();

                    log.warning("setRelatorioAcessoFaixaHorario() 1");

                    double valListVOL[] = new double[24];
                    double valListTAV[] = new double[24];

                    String[] textidb = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" };

                    log.warning("setRelatorioAcessoFaixaHorario() 2");

                    graficoVOL.setIndentifiers(textidb);
                    graficoTAV.setIndentifiers(textidb);

                    log.warning("setRelatorioAcessoFaixaHorario() 3");

                    graficoVOL.setXAxis("Hora do Dia");
                    graficoVOL.setYAxis("Quantidade de Acessos");
                    graficoVOL.setLong(400);
                    graficoVOL.setWidth(600);
                    graficoVOL.setDir("");

                    log.warning("setRelatorioAcessoFaixaHorario() 4");

                    graficoTAV.setXAxis("Hora do Dia");
                    graficoTAV.setYAxis("Quantidade de Acessos");
                    graficoTAV.setLong(400);
                    graficoTAV.setWidth(600);

                    log.warning("setRelatorioAcessoFaixaHorario() 5");

                    for (int i = 0; i < relatorioAcessoFaixaHorario.length; i++) {
                        valListVOL[Integer.parseInt(relatorioAcessoFaixaHorario[i].getHrRegistroHistorico())] = Double.parseDouble(relatorioAcessoFaixaHorario[i].getQtVOL());
                        valListTAV[Integer.parseInt(relatorioAcessoFaixaHorario[i].getHrRegistroHistorico())] = Double.parseDouble(relatorioAcessoFaixaHorario[i].getQtTAV());
                    }

                    log.warning("setRelatorioAcessoFaixaHorario() 6");

                    graficoVOL.setTitle("Média de Acessos VOL");
                    graficoVOL.setFile("relatorioAcessosHoraVOL.png");
                    graficoVOL.setValues(valListVOL);

                    log.warning("setRelatorioAcessoFaixaHorario() 7");

                    graficoTAV.setTitle("Média de Acessos TAV");
                    graficoTAV.setFile("relatorioAcessosHoraTAV.png");
                    graficoTAV.setValues(valListTAV);

                    log.warning("setRelatorioAcessoFaixaHorario() 8");

                    this.graficoAcessoFaixaHorarioVOL = graficoVOL;
                    this.graficoAcessoFaixaHorarioTAV = graficoTAV;

                    log.warning("setRelatorioAcessoFaixaHorario() FIM");
                }

            } catch (Exception e) {
                log.error("setRelatorioAcessoFaixaHorario() -> Exception-> " + e.getMessage(), e);
                e.printStackTrace();
                throw e;
            }
        }

        /***********
         * Criado por Decio 15/03/2004
         * ******************************************************************************************** Permite gerar
         * uma tabela com os dados do gráfico
         */
        public void setRelatorioAcessoFaixaHorario(VOLTAVRelatorioAcessoFaixaHorario[] relatorioAcessoFaixaHorario) {

            this.relatorioAcessoFaixaHorario = relatorioAcessoFaixaHorario;
            if (relatorioAcessoFaixaHorario != null) {
                this.listaVOL = new String[24];
                this.listaTAV = new String[24];
                for (int a = 0; a < listaVOL.length; a++) {
                    this.listaVOL[a] = "0,00";
                    this.listaTAV[a] = "0,00";
                }
                String fmtValor;
                for (int i = 0; i < relatorioAcessoFaixaHorario.length; i++) {
                    fmtValor = formatarDecimal(relatorioAcessoFaixaHorario[i].getQtVOL());
                    listaVOL[Integer.parseInt(relatorioAcessoFaixaHorario[i].getHrRegistroHistorico())] = fmtValor;
                    fmtValor = formatarDecimal(relatorioAcessoFaixaHorario[i].getQtTAV());
                    listaTAV[Integer.parseInt(relatorioAcessoFaixaHorario[i].getHrRegistroHistorico())] = fmtValor;
                }
            }
        }

        public VOLTAVRelatorioAcessoFaixaHorario[] getRelatorioAcessoFaixaHorario() {
            return this.relatorioAcessoFaixaHorario;
        }

        public void setServicosDisponiveis(VOLTAVRelatorioServicosDisponveis[] servicos) {
            if (servicos != null) {
                /********* Modificado por Decio 14/03/2005 ***************************/
                this.servicosDisponiveis = new LinkedHashMap(servicos.length + 1);
                servicosDisponiveis.put("-1", "Todos");
                /********* Fim Modificação *******************************************/
                for (int i = 0; i < servicos.length; i++) {
                    System.out.println(servicos[i].getNmContato());
                    servicosDisponiveis.put(servicos[i].getIdContato(), servicos[i].getNmContato());
                }

            } else {
                this.servicosDisponiveis = new LinkedHashMap();
            }
        }

        public LinkedHashMap getServicosDisponiveis() {
            return this.servicosDisponiveis;
        }

        public void setOperadoraSelecionada(String sel) {
            this.operadoraSelecionada = sel;
        }

        public String getOperadoraSelecionada() {
            return this.operadoraSelecionada;
        }

        public void setRegionalSelecionada(String sel) {
            this.regionalSelecionada = sel;
        }

        public String getRegionalSelecionada() {
            return this.regionalSelecionada;
        }

        public void setOperadoraTerminalSelecionada(String sel) {
            this.operadoraTerminalSelecionada = sel;
        }

        public String getOperadoraTerminalSelecionada() {
            return this.operadoraTerminalSelecionada;
        }

        public void setRegionalTerminalSelecionada(String sel) {
            this.regionalTerminalSelecionada = sel;
        }

        public String getRegionalTerminalSelecionada() {
            return this.regionalTerminalSelecionada;
        }

        public void setCarteiraSelecionada(String sel) {
            this.carteiraSelecionada = sel;
        }

        public String getCarteiraSelecionada() {
            return this.carteiraSelecionada;
        }

        public void setSegmentoSelecionada(String sel) {
            this.segmentoSelecionada = sel;
        }

        public String getSegmentoSelecionada() {
            return this.segmentoSelecionada;
        }

        public void setCanalSelecionado(String sel) {
            this.canalSelecionado = sel;
        }

        public String getCanalSelecionado() {
            return this.canalSelecionado;
        }

        public void setServicoSelecionado(String sel) {
            this.servicoSelecionado = sel;
        }

        public String getServicoSelecionado() {
            return this.servicoSelecionado;
        }

        public void setCodigoArea(String codigo) {
            this.codigoArea = codigo;
        }

        public String getCodigoArea() {
            return this.codigoArea;
        }

        public void setNumeroLinha(String numero) {
            this.numeroLinha = numero;
        }

        public String getNumeroLinha() {
            return this.numeroLinha;
        }

        public void setRelatorioAcessoCliente(RELACIONAMENTOS[] relatorio) {
            this.relatorioAcessoCliente = relatorio;
        }

        public RELACIONAMENTOS[] getRelatorioAcessoCliente() {
            return this.relatorioAcessoCliente;
        }

        public void setSumarizacao(String select) {
            this.sumarizacao = select;
        }

        public String getSumarizacao() {
            return this.sumarizacao;
        }

        public BarChartBean getGraficoAcessoFaixaHorarioVOL() {
            return this.graficoAcessoFaixaHorarioVOL;
        }

        public BarChartBean getGraficoAcessoFaixaHorarioTAV() {
            return this.graficoAcessoFaixaHorarioTAV;
        }

        public void setRelatorioAdesoesBaseClientes(VOLTAVRelatorioAdesoes[] relatorioAdesoesBaseClientes) {
            this.relatorioAdesoesBaseClientes = relatorioAdesoesBaseClientes;
            if (relatorioAdesoesBaseClientes != null) {
                this.totaisRelatorioAdesoesBaseClientes = new int[13];
                for (int i = 0; i < relatorioAdesoesBaseClientes.length; i++) {
                    totaisRelatorioAdesoesBaseClientes[0] += Integer.parseInt(relatorioAdesoesBaseClientes[i].getQtTotal());
                    totaisRelatorioAdesoesBaseClientes[1] += Integer.parseInt(relatorioAdesoesBaseClientes[i].getQtVOLPreCliente());
                    totaisRelatorioAdesoesBaseClientes[2] += Integer.parseInt(relatorioAdesoesBaseClientes[i].getQtVOLPreUsuario());
                    totaisRelatorioAdesoesBaseClientes[3] += Integer.parseInt(relatorioAdesoesBaseClientes[i].getQtVOLPosCliente());
                    totaisRelatorioAdesoesBaseClientes[4] += Integer.parseInt(relatorioAdesoesBaseClientes[i].getQtVOLPosUsuario());
                    totaisRelatorioAdesoesBaseClientes[5] += Integer.parseInt(relatorioAdesoesBaseClientes[i].getQtVOLConCliente());
                    totaisRelatorioAdesoesBaseClientes[6] += Integer.parseInt(relatorioAdesoesBaseClientes[i].getQtVOLConUsuario());
                    totaisRelatorioAdesoesBaseClientes[7] += Integer.parseInt(relatorioAdesoesBaseClientes[i].getQtTAVPreCliente());
                    totaisRelatorioAdesoesBaseClientes[8] += Integer.parseInt(relatorioAdesoesBaseClientes[i].getQtTAVPreUsuario());
                    totaisRelatorioAdesoesBaseClientes[9] += Integer.parseInt(relatorioAdesoesBaseClientes[i].getQtTAVPosCliente());
                    totaisRelatorioAdesoesBaseClientes[10] += Integer.parseInt(relatorioAdesoesBaseClientes[i].getQtTAVPosUsuario());
                    totaisRelatorioAdesoesBaseClientes[11] += Integer.parseInt(relatorioAdesoesBaseClientes[i].getQtTAVConUsuario());
                    totaisRelatorioAdesoesBaseClientes[12] += Integer.parseInt(relatorioAdesoesBaseClientes[i].getQtTAVConUsuario());
                }
            }
        }

        public VOLTAVRelatorioAdesoes[] getRelatorioAdesoesBaseClientes() {
            return this.relatorioAdesoesBaseClientes;
        }

        public void setRelatorioUtilizacaoServicos(VOLTAVRelatorioServicosEfetuados[] relatorioUtilizacaoServicos) {
            this.relatorioUtilizacaoServicos = relatorioUtilizacaoServicos;
        }

        public VOLTAVRelatorioServicosEfetuados[] getRelatorioUtilizacaoServicos() {
            return this.relatorioUtilizacaoServicos;
        }

        public int[] getTotaisRelatorioAdesoesBaseClientes() {
            return this.totaisRelatorioAdesoesBaseClientes;
        }

        public int[] getTotaisRelatorioAcessoDiario() {
            return this.totaisRelatorioAcessoDiario;
        }

        public int[] getTotaisRelatorioAcessoNegado() {
            return this.totaisRelatorioAcessoNegado;
        }

        public void setStatusVenda(TreeMap statusVenda) {
            this.statusVenda = statusVenda;
        }

        public TreeMap getStatusVenda() {
            return this.statusVenda;
        }

        public void setDsStatusVenda(String dsStatusVenda) {
            this.dsStatusVenda = dsStatusVenda;
        }

        public String getDsStatusVenda() {
            return this.dsStatusVenda;
        }

        public void setVlrVOLTotalServicos(String vlrVOLTotalServicos) {
            this.vlrVOLTotalServicos = vlrVOLTotalServicos;
        }

        public String getVlrVOLTotalServicos() {
            return this.vlrVOLTotalServicos;
        }

        public void setVlrURATotalServicos(String vlrURATotalServicos) {
            this.vlrURATotalServicos = vlrURATotalServicos;
        }

        public String getVlrURATotalServicos() {
            return this.vlrURATotalServicos;
        }

        public void setVlrTAVTotalServicos(String vlrTAVTotalServicos) {
            this.vlrTAVTotalServicos = vlrTAVTotalServicos;
        }

        public String getVlrTAVTotalServicos() {
            return this.vlrTAVTotalServicos;
        }

        public void setNmRegionalTerminal(String nmRegionalTerminal) {
            this.nmRegionalTerminal = nmRegionalTerminal;
        }

        public String getNmRegionalTerminal() {
            return this.nmRegionalTerminal;
        }

        public void setNmOperadoraTerminal(String nmOperadoraTerminal) {
            this.nmOperadoraTerminal = nmOperadoraTerminal;
        }

        public String getNmOperadoraTerminal() {
            return this.nmOperadoraTerminal;
        }

        public void setNmTipoErro(String nmTipoErro) {
            this.nmTipoErro = nmTipoErro;
        }

        public String getNmTipoErro() {
            if (this.nmTipoErro == null) {
                this.nmTipoErro = ConstantesCRM.SVAZIO;
            }
            return this.nmTipoErro;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getIndex() {
            return this.index;
        }

        public void setRelatorioFinanceiroDetalhe(VOLTAVRelatorioFinanceiro relatorioFinanceiroDetalhe) {
            this.relatorioFinanceiroDetalhe = relatorioFinanceiroDetalhe;
        }

        public VOLTAVRelatorioFinanceiro getRelatorioFinanceiroDetalhe() {
            return this.relatorioFinanceiroDetalhe;
        }

        public void setOperacaoRecargaVO(VOLTAVOperacaoRecarga[] operacaoRecargaVO) {
            this.operacaoRecargaVO = operacaoRecargaVO;
        }

        public VOLTAVOperacaoRecarga[] getOperacaoRecargaVO() {
            return this.operacaoRecargaVO;
        }
    }
}