package VOLTAV.manterDaemonDesativacaoComprovanteEmail;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import br.com.vivo.fo.cliente.vo.FilaComprovanteEmailVODocument.FilaComprovanteEmailVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.VOLTAV.manterDaemonDesativacaoComprovanteEmail.manterDaemonDesativacaoComprovanteEmail;
import com.indracompany.actions.AbstractAction;

public class ManterDaemonController extends AbstractAction {

    private static final long                       serialVersionUID       = 7062910325709604100L;

    @EJB
    private manterDaemonDesativacaoComprovanteEmail controlManterDaemon;

    private FormManterDaemon                        formManterDaemon       = null;

    FilaComprovanteEmailVO                          filaComprovanteEmailVO = null;

    private static int                              registrosPorPagina     = 50;

    protected global.Global                         globalApp              = new global.Global();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if ("begin".equals(mapping.getParameter())) {
            return begin(mapping, form, request, response);
        } else if ("beginConsultarFila".equals(mapping.getParameter())) {
            return beginConsultarFila(mapping, form, request, response);
        } else if ("ativarDaemon".equals(mapping.getParameter())) {
            return ativarDaemon(mapping, form, request, response);
        } else if ("desativarDaemon".equals(mapping.getParameter())) {
            return desativarDaemon(mapping, form, request, response);
        } else if ("gravarParametroDaemon".equals(mapping.getParameter())) {
            return gravarParametroDaemon(mapping, form, request, response);
        } else if ("consultaDetalheFila".equals(mapping.getParameter())) {
            return consultaDetalheFila(mapping, form, request, response);
        } else if ("deleteByCodigoErro".equals(mapping.getParameter())) {
            return deleteByCodigoErro(mapping, form, request, response);
        } else if ("atualizarByCodigoErro".equals(mapping.getParameter())) {
            return atualizarByCodigoErro(mapping, form, request, response);
        } else if ("deleteById".equals(mapping.getParameter())) {
            return deleteById(mapping, form, request, response);
        } else if ("atualizarById".equals(mapping.getParameter())) {
            return atualizarById(mapping, form, request, response);
        } else if ("recarregaParametrizacao".equals(mapping.getParameter())) {
            return recarregaParametrizacao(mapping, form, request, response);
        } else if ("paginarPesquisa".equals(mapping.getParameter())) {
            return paginarPesquisa(mapping, form, request, response);
        } else if ("getXml".equals(mapping.getParameter())) {
            return getXml(mapping, form, request, response);
        }
        return begin(mapping, form, request, response);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="parametrizarDaemon.jsp"
     */
    protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        StringBuffer xmlIn = new StringBuffer();

        xmlIn.append("<dsOperacao>").append("getStatusParametro").append("</dsOperacao>");

        FilaComprovanteEmailVO filaComprovanteEmailVO = controlManterDaemon.manterDaemon(xmlIn.toString(), ConstantesCRM.getCurrentUser(request.getSession()));

        getFormManterDaemon().setFilaComprovanteEmailVO(filaComprovanteEmailVO);

        getFormManterDaemon().setLengthLista(String.valueOf(filaComprovanteEmailVO.getGrupoErroVOArray().length));
        getFormManterDaemon().setQtdRegistrosLidos(filaComprovanteEmailVO.getRegistrosCursor());
        getFormManterDaemon().setTmpRegistroLeitura(filaComprovanteEmailVO.getTempoEntreExecucao());
        getFormManterDaemon().setTempoInativo(filaComprovanteEmailVO.getTempoInativo());
        getFormManterDaemon().setTempoReprocessar(filaComprovanteEmailVO.getTempoReprocessar());
        getFormManterDaemon().setQtTentativas(filaComprovanteEmailVO.getQtTentativas());

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="consultarFilaDaemon.jsp"
     */
    protected ActionForward beginConsultarFila(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StringBuffer xmlIn = new StringBuffer();

        xmlIn.append("<dsOperacao>").append("consultaFila").append("</dsOperacao>");

        FilaComprovanteEmailVO filaComprovanteEmailVO = controlManterDaemon.manterDaemon(xmlIn.toString(), ConstantesCRM.getCurrentUser(request.getSession()));

        getFormManterDaemon().setLengthLista(String.valueOf(filaComprovanteEmailVO.getGrupoErroVOArray().length));
        getFormManterDaemon().setPrePagoFilaPorGrupo(filaComprovanteEmailVO);

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="recarregaParametrizacao.do"
     */
    protected ActionForward ativarDaemon(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        FormManterDaemon form = (FormManterDaemon) formParam;

        StringBuffer xmlIn = new StringBuffer();
        xmlIn.append("<dsOperacao>").append("startDaemonParametro").append("</dsOperacao>");

        controlManterDaemon.manterDaemon(xmlIn.toString(), ConstantesCRM.getCurrentUser(request.getSession()));

        getFormManterDaemon().getFilaComprovanteEmailVO().setAtivo(ConstantesCRM.SONE);

        request.setAttribute("msgStatus", "Daemon ativado realizada com sucesso.");

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="recarregaParametrizacao.do"
     */
    protected ActionForward desativarDaemon(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FormManterDaemon form = (FormManterDaemon) formParam;
        StringBuffer xmlIn = new StringBuffer();
        xmlIn.append("<dsOperacao>").append("stopDaemonParametro").append("</dsOperacao>");

        controlManterDaemon.manterDaemon(xmlIn.toString(), ConstantesCRM.getCurrentUser(request.getSession()));

        getFormManterDaemon().getFilaComprovanteEmailVO().setAtivo(ConstantesCRM.SZERO);

        request.setAttribute("msgStatus", "Daemon desativado realizada com sucesso.");

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="recarregaParametrizacao.do"
     */
    protected ActionForward gravarParametroDaemon(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FormManterDaemon form = (FormManterDaemon) formParam;
        StringBuffer xmlIn = new StringBuffer();
        xmlIn.append("<dsOperacao>").append("setStatusParametro").append("</dsOperacao>");

        if (form.getTmpRegistroLeitura() != null && !ConstantesCRM.SVAZIO.equals(form.getTmpRegistroLeitura())) {
            xmlIn.append("<tempoEntreExecucao>").append(form.getTmpRegistroLeitura()).append("</tempoEntreExecucao>");
        }

        if (form.getQtdRegistrosLidos() != null && !ConstantesCRM.SVAZIO.equals(form.getQtdRegistrosLidos())) {
            xmlIn.append("<registrosCursor>").append(form.getQtdRegistrosLidos()).append("</registrosCursor>");
        }

        if (form.getQtTentativas() != null && !ConstantesCRM.SVAZIO.equals(form.getQtTentativas())) {
            xmlIn.append("<qtTentativas>").append(form.getQtTentativas()).append("</qtTentativas>");
        }

        if (form.getTempoInativo() != null && !ConstantesCRM.SVAZIO.equals(form.getTempoInativo())) {
            xmlIn.append("<tempoInativo>").append(form.getTempoInativo()).append("</tempoInativo>");
        }

        if (form.getTempoReprocessar() != null && !ConstantesCRM.SVAZIO.equals(form.getTempoReprocessar())) {
            xmlIn.append("<tempoReprocessar>").append(form.getTempoReprocessar()).append("</tempoReprocessar>");
        }

        controlManterDaemon.manterDaemon(xmlIn.toString(), ConstantesCRM.getCurrentUser(request.getSession()));

        request.setAttribute("msgStatus", "Gravação realizada com sucesso. Para efetivar as alterações, é necessário que o Daemon seja reiniciado.");

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="innerBrowserFilaDetalhada.jsp"
     */
    protected ActionForward consultaDetalheFila(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FormManterDaemon form = (FormManterDaemon) formParam;
        StringBuffer xmlIn = null;

        FilaComprovanteEmailVO.GrupoErroVO grupoVO = getGrupoErroByCodigoErro(form.getCdErro(), getFormManterDaemon().getFilaComprovanteEmailPorGrupoVO().getGrupoErroVOArray());

        getFormManterDaemon().setCodigoErro(grupoVO.getCdErro());
        getFormManterDaemon().setDescricaoErro(grupoVO.getDsErro());

        this.filaComprovanteEmailVO = FilaComprovanteEmailVO.Factory.newInstance();

        int regInicio = 1;
        int regFim = 0;
        boolean primeiraChamada = true;
        int totalPagina = 0;

        boolean proximaPagina = true;

        while (proximaPagina) {
            totalPagina++;

            regFim = regFim + registrosPorPagina;

            xmlIn = new StringBuffer();
            xmlIn.append("<dsOperacao>").append("consultaDetalheFila").append("</dsOperacao>");
            xmlIn.append("<regIni>").append(regInicio).append("</regIni>");
            xmlIn.append("<regFim>").append(regFim).append("</regFim>");

            if (form.getCdErro() != null && !ConstantesCRM.SVAZIO.equals(form.getCdErro())) {
                xmlIn.append("<cdErro>").append(form.getCdErro()).append("</cdErro>");
            }

            FilaComprovanteEmailVO filaComprovanteEmailVO = controlManterDaemon.manterDaemon(xmlIn.toString(), ConstantesCRM.getCurrentUser(request.getSession()));

            if (primeiraChamada) {
                getFormManterDaemon().setFilaComprovanteEmailVO(filaComprovanteEmailVO);
                primeiraChamada = false;
            }

            regInicio = regInicio + registrosPorPagina;

            carregaArrayTodosRegistro(filaComprovanteEmailVO);

            if (filaComprovanteEmailVO.getGrupoErroVOArray().length < registrosPorPagina) {
                proximaPagina = false;
            }

        }

        getFormManterDaemon().setTotalPaginas(String.valueOf(totalPagina));
        getFormManterDaemon().setPaginaAtual(ConstantesCRM.SONE);

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    private FilaComprovanteEmailVO.GrupoErroVO getGrupoErroByCodigoErro(String cdErro, FilaComprovanteEmailVO.GrupoErroVO[] grupos) {

        if (cdErro == null) {
            return null;
        }

        for (int i = 0; i < grupos.length; i++) {

            if (grupos[i].getCdErro().equals(cdErro)) {
                return grupos[i];
            }
        }

        return null;
    }

    private void carregaArrayTodosRegistro(FilaComprovanteEmailVO fila) {

        if (this.filaComprovanteEmailVO == null) {
            this.filaComprovanteEmailVO = FilaComprovanteEmailVO.Factory.newInstance();
        }

        for (int i = 0; i < fila.getGrupoErroVOArray().length; i++) {
            int auxTamanho = filaComprovanteEmailVO.getGrupoErroVOArray().length;
            this.filaComprovanteEmailVO.addNewGrupoErroVO();

            this.filaComprovanteEmailVO.setGrupoErroVOArray(auxTamanho, fila.getGrupoErroVOArray(i));
        }

    }

    private FilaComprovanteEmailVO getFilaComprovanteEmailVO() {
        if (this.filaComprovanteEmailVO == null) {
            this.filaComprovanteEmailVO = FilaComprovanteEmailVO.Factory.newInstance();
        }

        return this.filaComprovanteEmailVO;
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="beginConsultarFila.do"
     */
    protected ActionForward deleteByCodigoErro(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FormManterDaemon form = (FormManterDaemon) formParam;
        StringBuffer xmlIn = new StringBuffer();
        xmlIn.append("<dsOperacao>").append("apagaGrupoErroFila").append("</dsOperacao>");

        if (form.getCdErro() != null && !ConstantesCRM.SVAZIO.equals(form.getCdErro())) {
            xmlIn.append("<cdErro>").append(form.getCdErro()).append("</cdErro>");
        }

        controlManterDaemon.manterDaemon(xmlIn.toString(), ConstantesCRM.getCurrentUser(request.getSession()));

        request.setAttribute("msgStatus", "Registro(s) deletado(s) com sucesso.");

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="beginConsultarFila.do"
     */
    protected ActionForward atualizarByCodigoErro(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FormManterDaemon form = (FormManterDaemon) formParam;
        StringBuffer xmlIn = new StringBuffer();
        xmlIn.append("<dsOperacao>").append("atualizaGrupoErroFila").append("</dsOperacao>");

        if (form.getCdErro() != null && !ConstantesCRM.SVAZIO.equals(form.getCdErro())) {
            xmlIn.append("<cdErro>").append(form.getCdErro()).append("</cdErro>");
        }

        controlManterDaemon.manterDaemon(xmlIn.toString(), ConstantesCRM.getCurrentUser(request.getSession()));

        request.setAttribute("msgStatus", "Atualização realizada com sucesso.");

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="beginConsultarFila.do"
     */
    protected ActionForward deleteById(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FormManterDaemon form = (FormManterDaemon) formParam;
        StringBuffer xmlIn = new StringBuffer();
        xmlIn.append("<dsOperacao>").append("apagaIdErroFila").append("</dsOperacao>");

        String[] arrayIdFila = form.getArrayIdFila().split(";");

        for (int i = 0; i < arrayIdFila.length; i++) {
            xmlIn.append("<idcompservicodesativado>").append(arrayIdFila[i]).append("</idcompservicodesativado>");
        }

        controlManterDaemon.manterDaemon(xmlIn.toString(), ConstantesCRM.getCurrentUser(request.getSession()));

        request.setAttribute("msgStatus", "Registro(s) deletado(s) com sucesso.");

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="beginConsultarFila.do"
     */
    protected ActionForward atualizarById(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FormManterDaemon form = (FormManterDaemon) formParam;
        StringBuffer xmlIn = new StringBuffer();
        xmlIn.append("<dsOperacao>").append("atualizaIdErroFila").append("</dsOperacao>");

        String[] arrayIdFila = form.getArrayIdFila().split(";");

        for (int i = 0; i < arrayIdFila.length; i++) {
            xmlIn.append("<idcompservicodesativado>").append(arrayIdFila[i]).append("</idcompservicodesativado>");
        }

        controlManterDaemon.manterDaemon(xmlIn.toString(), ConstantesCRM.getCurrentUser(request.getSession()));

        request.setAttribute("msgStatus", "Atualização realizada com sucesso.");

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="innerBrowserParametrizacao.jsp"
     */
    protected ActionForward recarregaParametrizacao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FormManterDaemon form = (FormManterDaemon) formParam;
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);

    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="innerBrowserFilaDetalhada.jsp"
     */
    protected ActionForward paginarPesquisa(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FormManterDaemon form = (FormManterDaemon) formParam;
        int paginaAtual = 0;
        int registroInicial = 1;
        int count = 0;

        FilaComprovanteEmailVO filaVO = FilaComprovanteEmailVO.Factory.newInstance();

        if (form.getPaginaAtual() != null && !ConstantesCRM.SVAZIO.equals(form.getPaginaAtual())) {
            paginaAtual = Integer.parseInt(form.getPaginaAtual());
        }

        int registroFinal = registrosPorPagina * paginaAtual;
        registroInicial = registroInicial + (registroFinal - registrosPorPagina);

        for (int i = registroInicial; i < registroFinal + 1; i++) {

            if (this.getFilaComprovanteEmailVO().getGrupoErroVOArray().length > i - 1) {

                filaVO.addNewGrupoErroVO();
                filaVO.setGrupoErroVOArray(count, this.getFilaComprovanteEmailVO().getGrupoErroVOArray(i - 1));
                count++;

            }
        }

        getFormManterDaemon().setFilaComprovanteEmailVO(filaVO);
        getFormManterDaemon().setPaginaAtual(form.getPaginaAtual());

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="xmlErro.jsp"
     */
    protected ActionForward getXml(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        FormManterDaemon form = (FormManterDaemon) formParam;
        getFormManterDaemon().setXml(getXml(form.getIdcompservicodesativado(), getFormManterDaemon().getFilaComprovanteEmailVO().getGrupoErroVOArray()));

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    private String getXml(String id, FilaComprovanteEmailVO.GrupoErroVO[] grupos) {

        if (id == null || grupos == null) {
            return null;
        }

        for (int i = 0; i < grupos.length; i++) {

            if (id.equals(grupos[i].getIdcompservicodesativado())) {
                return grupos[i].getXml();
            }
        }

        return null;
    }

    public static class FormManterDaemon extends ActionForm {

        private static final long serialVersionUID = 2358579881109248709L;

        private String                 idcompservicodesativado;
        private String                 qtTentativas;
        private String                 tempoReprocessar;
        private String                 tempoInativo;
        private String                 xml;
        private FilaComprovanteEmailVO filaComprovanteEmailPorGrupoVO;
        private String                 descricaoErro;
        private String                 codigoErro;
        private String                 lengthLista;
        private String                 arrayIdFila;
        private String                 totalPaginas;
        private String                 paginaAtual;
        private String                 cdErro;
        private FilaComprovanteEmailVO filaComprovanteEmailVO;
        private String                 qtdRegistrosLidos;
        private String                 tmpRegistroLeitura;

        public void setTmpRegistroLeitura(String tmpRegistroLeitura) {
            this.tmpRegistroLeitura = tmpRegistroLeitura;
        }

        public String getTmpRegistroLeitura() {
            if (this.tmpRegistroLeitura == null) {
                this.tmpRegistroLeitura = ConstantesCRM.SVAZIO;
            }
            return this.tmpRegistroLeitura;
        }

        public void setQtdRegistrosLidos(String qtdRegistrosLidos) {
            this.qtdRegistrosLidos = qtdRegistrosLidos;
        }

        public String getQtdRegistrosLidos() {

            if (this.qtdRegistrosLidos == null) {
                this.qtdRegistrosLidos = ConstantesCRM.SVAZIO;
            }

            return this.qtdRegistrosLidos;
        }

        public void setFilaComprovanteEmailVO(FilaComprovanteEmailVO filaComprovanteEmailVO) {
            this.filaComprovanteEmailVO = filaComprovanteEmailVO;
        }

        public FilaComprovanteEmailVO getFilaComprovanteEmailVO() {
            if (this.filaComprovanteEmailVO == null) {
                this.filaComprovanteEmailVO = FilaComprovanteEmailVO.Factory.newInstance();
            }

            return this.filaComprovanteEmailVO;
        }

        public void setCdErro(String cdErro) {
            this.cdErro = cdErro;
        }

        public String getCdErro() {
            if (this.cdErro == null) {
                this.cdErro = ConstantesCRM.SVAZIO;
            }
            return this.cdErro;
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

        public void setTotalPaginas(String totalPaginas) {
            this.totalPaginas = totalPaginas;
        }

        public String getTotalPaginas() {
            if (this.totalPaginas == null) {
                this.totalPaginas = ConstantesCRM.SVAZIO;
            }
            return this.totalPaginas;
        }

        public void setArrayIdFila(String arrayIdFila) {
            this.arrayIdFila = arrayIdFila;
        }

        public String getArrayIdFila() {
            if (this.arrayIdFila == null) {
                this.arrayIdFila = ConstantesCRM.SVAZIO;
            }
            return this.arrayIdFila;
        }

        public void setLengthLista(String lengthLista) {
            this.lengthLista = lengthLista;
        }

        public String getLengthLista() {
            if (this.lengthLista == null || ConstantesCRM.SVAZIO.equals(this.lengthLista)) {
                this.lengthLista = ConstantesCRM.SZERO;
            }
            return this.lengthLista;
        }

        public void setCodigoErro(String codigoErro) {
            this.codigoErro = codigoErro;
        }

        public String getCodigoErro() {
            if (this.codigoErro == null) {
                this.codigoErro = ConstantesCRM.SVAZIO;
            }
            return this.codigoErro;
        }

        public void setDescricaoErro(String descricaoErro) {
            this.descricaoErro = descricaoErro;
        }

        public String getDescricaoErro() {
            if (this.descricaoErro == null) {
                this.descricaoErro = ConstantesCRM.SVAZIO;
            }
            return this.descricaoErro;
        }

        public void setPrePagoFilaPorGrupo(FilaComprovanteEmailVO prePagoFilaPorGrupo) {
            this.filaComprovanteEmailPorGrupoVO = prePagoFilaPorGrupo;
        }

        public FilaComprovanteEmailVO getPrePagoFilaPorGrupo() {
            if (this.filaComprovanteEmailPorGrupoVO == null) {
                this.filaComprovanteEmailPorGrupoVO = FilaComprovanteEmailVO.Factory.newInstance();
            }

            return this.filaComprovanteEmailPorGrupoVO;
        }

        public FilaComprovanteEmailVO getFilaComprovanteEmailPorGrupoVO() {
            if (this.filaComprovanteEmailPorGrupoVO == null) {
                this.filaComprovanteEmailPorGrupoVO = FilaComprovanteEmailVO.Factory.newInstance();
            }

            return this.filaComprovanteEmailPorGrupoVO;
        }

        public void setXml(String xml) {
            this.xml = xml;
        }

        public String getXml() {
            if (this.xml == null) {
                this.xml = ConstantesCRM.SVAZIO;
            }
            return this.xml;
        }

        public void setTempoInativo(String tempoInativo) {
            this.tempoInativo = tempoInativo;
        }

        public String getTempoInativo() {
            return this.tempoInativo;
        }

        public void setTempoReprocessar(String tempoReprocessar) {
            this.tempoReprocessar = tempoReprocessar;
        }

        public String getTempoReprocessar() {
            return this.tempoReprocessar;
        }

        public void setQtTentativas(String qtTentativas) {
            this.qtTentativas = qtTentativas;
        }

        public String getQtTentativas() {
            return this.qtTentativas;
        }

        public void setIdcompservicodesativado(String idcompservicodesativado) {
            this.idcompservicodesativado = idcompservicodesativado;
        }

        public String getIdcompservicodesativado() {
            return this.idcompservicodesativado;
        }
    }

    public FormManterDaemon getFormManterDaemon() {
        if (formManterDaemon == null) {
            formManterDaemon = new FormManterDaemon();
        }

        return formManterDaemon;
    }

    public void setFormManterDaemon(FormManterDaemon form) {
        this.formManterDaemon = form;
    }

}
