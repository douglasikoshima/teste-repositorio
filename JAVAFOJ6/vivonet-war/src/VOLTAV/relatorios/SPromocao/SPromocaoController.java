package VOLTAV.relatorios.SPromocao;

import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import br.com.vivo.fo.cliente.Pesquisar;
import br.com.vivo.fo.cliente.vo.PaginacaoDocument.Paginacao;
import br.com.vivo.fo.cliente.vo.ResultsetDocument.Resultset;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.fidelizacao.Fidelizacao;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista.Disponivel;
import br.com.vivo.fo.fidelizacao.vo.ItDocument.It;
import br.com.vivo.fo.log.Logger;
import com.indracompany.actions.AbstractAction;

public class SPromocaoController extends AbstractAction {

    private static final long serialVersionUID = 7167981264967961239L;

    @EJB
    public Fidelizacao        fidelizacao;

    protected global.Global   globalApp        = new global.Global();

    private transient Logger  log              = new Logger("VOLTAV");

    private RelatorioForm     relatorioForm;

    public RelatorioForm getRelatorioForm() {
        if (this.relatorioForm == null) {
            this.relatorioForm = new RelatorioForm();
        }
        return this.relatorioForm;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if ("begin".equals(mapping.getParameter())) {
            return begin(mapping, form, request, response);
        } else if ("done".equals(mapping.getParameter())) {
            return done(mapping, form, request, response);
        } else if ("pesquisar".equals(mapping.getParameter())) {
            return pesquisar(mapping, form, request, response);
        } else if ("exportar".equals(mapping.getParameter())) {
            return exportar(mapping, form, request, response);
        }
        return begin(mapping, form, request, response);
    }

    /**
     * This method represents the point of entry into the pageflow
     * @jpf:action
     * @jpf:forward name="success" path="index.jsp"
     */
    protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        String user = ConstantesCRM.getCurrentUser(request.getSession());
        try {
            FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
            xml.addNewFiltro().addNewCombos().addNmSelect("REGIONAL");
            FidelizacaoVO listas = fidelizacao.getListas(user, xml);

            for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
                if ("REGIONAL".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
                    getRelatorioForm().setListaRegional(listas.getListasVO().getListaArray(i).getDisponivel());
                }
            }
            getRelatorioForm().setListaTpLinhas(getListaTipoLinha());
        } catch (Exception e) {
            log.error("SPromocaoController::begin", e);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="done" return-action="SPromocaoDone"
     */
    public ActionForward done(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward("done");
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="tabelaRelatorio.jsp"
     */
    protected ActionForward pesquisar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

        try {
            StringBuffer queryPrim = new StringBuffer(ConstantesCRM.SVAZIO);
            StringBuffer queryFrom = new StringBuffer(" from tibco_ow.sumarizarelatorio sr ");
            StringBuffer queryWher = new StringBuffer(" where ");

            queryWher.append(" sr.dtAtivacao >= to_date('").append(getRelatorioForm().getDtAtivacao()).append(" 00:00:00','dd/mm/yyyy hh24:mi:ss') ");
            queryWher.append(" and   sr.dtAtivacao <= to_date('").append(getRelatorioForm().getDtAtivacao()).append(" 23:59:59','dd/mm/yyyy hh24:mi:ss') ");

            queryPrim.append(" select to_char(sr.dtativacao,'dd/mm/yyyy') dtAtivacao, ");
            queryPrim.append("        nmRegional, ");
            queryPrim.append("        dsTipoLinha, ");
            queryPrim.append("        nrTotalAtivacoes ");

            if (getRelatorioForm().getIdRegional() != null && !ConstantesCRM.SVAZIO.equals(getRelatorioForm().getIdRegional())) {
                queryFrom.append("      , apoio.uf uf, ");
                queryFrom.append("      customer.ufoperadora ufo ");
                queryWher.append("       and ufo.idUfOperadora = ").append(getRelatorioForm().getIdRegional()).append(" ");
                queryWher.append("       and uf.idUF = ufo.idUF ");
                queryWher.append("       and sr.nmRegional = uf.nmUF ");
            }

            if (getRelatorioForm().getIdTpLinha() != null && !ConstantesCRM.SVAZIO.equals(getRelatorioForm().getIdTpLinha())) {
                queryFrom.append("      , apoio.classificacaotipolinha ctl ");
                queryWher.append("       and ctl.idclassificacaotipolinha = '").append(getRelatorioForm().getIdTpLinha()).append("' ");
                queryWher.append("       and sr.dstipolinha = ctl.dsclassificacaotipolinha ");
            }

            queryPrim.append(queryFrom).append(queryWher);
            log.debug(queryPrim);

            Pesquisar pesquisar = new Pesquisar();
            Resultset rsVO = pesquisar.executar(queryPrim.toString());
            if (rsVO == null || rsVO.getLinhas() == null) {
                rsVO = Resultset.Factory.newInstance();
                rsVO.addNewLinhas();
            }
            getRelatorioForm().setResultset(rsVO);
        } catch (Exception e) {
            log.error("SPromocaoController::pesquisar", e);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * This method represents the point of entry into the pageflow
     * @jpf:action
     * @jpf:forward name="success" path="index.jsp"
     */
    protected ActionForward exportar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

        RelatorioForm form = (RelatorioForm) formParam;

        try {
            StringBuffer queryPrim = new StringBuffer(ConstantesCRM.SVAZIO);
            StringBuffer queryFrom = new StringBuffer(" from tibco_ow.sumarizarelatorio sr ");
            StringBuffer queryWher = new StringBuffer(" where ");

            queryWher.append(" sr.dtAtivacao >= to_date('").append(form.getDtAtivacao()).append(" 00:00:00','dd/mm/yyyy hh24:mi:ss') ");
            queryWher.append(" and   sr.dtAtivacao <= to_date('").append(form.getDtAtivacao()).append(" 23:59:59','dd/mm/yyyy hh24:mi:ss') ");

            queryPrim.append(" select to_char(sr.dtativacao,'dd/mm/yyyy') dtAtivacao, ");
            queryPrim.append("        nmRegional, ");
            queryPrim.append("        dsTipoLinha, ");
            queryPrim.append("        nrTotalAtivacoes ");

            if (form.getIdRegional() != null && !ConstantesCRM.SVAZIO.equals(form.getIdRegional())) {
                queryFrom.append("      , apoio.uf uf, ");
                queryFrom.append("      customer.ufoperadora ufo ");
                queryWher.append("       and ufo.idUfOperadora = ").append(form.getIdRegional()).append(" ");
                queryWher.append("       and uf.idUF = ufo.idUF ");
                queryWher.append("       and sr.nmRegional = uf.nmUF ");
            }

            if (form.getIdTpLinha() != null && !ConstantesCRM.SVAZIO.equals(form.getIdTpLinha())) {
                queryFrom.append("      , apoio.classificacaotipolinha ctl ");
                queryWher.append("       and ctl.idclassificacaotipolinha = '").append(form.getIdTpLinha()).append("' ");
                queryWher.append("       and sr.dstipolinha = ctl.dsclassificacaotipolinha ");
            }

            queryPrim.append(queryFrom).append(queryWher);

            Pesquisar pesquisar = new Pesquisar();
            Resultset rsVO = pesquisar.executar(queryPrim.toString());
            if (rsVO == null || rsVO.getLinhas() == null) {
                rsVO = Resultset.Factory.newInstance();
                rsVO.addNewLinhas();
            }

            response.addHeader("Content-Disposition", "attachment; filename=relatorio_torpedo_premiado.csv");
            response.addHeader("Content-Type", "application/force-download");
            response.addHeader("Content-Transfer-Encoding", "binary");
            response.addHeader("Pragma", "no-cache");
            response.addHeader("Expires", ConstantesCRM.SZERO);

            PrintWriter out = response.getWriter();
            StringBuffer relatorioStr = new StringBuffer(1024);

            relatorioStr.append("Data Ativação|");
            relatorioStr.append("Regional|");
            relatorioStr.append("Tipo de Linha|");
            relatorioStr.append("Total Ativações c/ Sucesso|");
            relatorioStr.append("\n");

            for (int j = 0; j < rsVO.getLinhas().getLinhaArray().length; j++) {
                Resultset.Linhas.Linha linha = rsVO.getLinhas().getLinhaArray(j);
                for (int l = 0; l < linha.getValorArray().length; l++) {
                    relatorioStr.append(linha.getValorArray(l) + "|");
                }
                relatorioStr.append("\n");
            }

            out.print(relatorioStr);

        } catch (Exception e) {
            log.error("SPromocaoController::exportar", e);
        }
        return null;
    }

    private Disponivel getListaTipoLinha() {
        String query = "select idClassificacaoTipoLinha id, dsClassificacaoTipoLinha ds from apoio.classificacaotipolinha";
        Pesquisar pesquisar = new Pesquisar();
        Resultset rsVO = pesquisar.executar(query);
        Disponivel listaTpLinha = Disponivel.Factory.newInstance();
        for (int j = 0; j < rsVO.getLinhas().getLinhaArray().length; j++) {
            Resultset.Linhas.Linha linha = rsVO.getLinhas().getLinhaArray(j);
            It it = listaTpLinha.addNewIt();
            it.setId(linha.getValorArray(0));
            it.setDs(linha.getValorArray(1));
        }
        return listaTpLinha;
    }

    public static class RelatorioForm extends ActionForm {

        private static final long serialVersionUID = 5760300000995239611L;

        private int               regPorPagina;
        private int               pageNumber       = 0;
        private String            idRegional       = ConstantesCRM.SVAZIO;
        private String            dtAtivacao       = ConstantesCRM.SVAZIO;
        private String            idTpLinha        = ConstantesCRM.SVAZIO;
        private Disponivel        listaRegional;
        private Disponivel        listaTpLinhas;
        private Resultset         resultset;
        private Paginacao         paginacao;

        public void setDtAtivacao(String dtAtivacao) {
            this.dtAtivacao = dtAtivacao;
        }

        public String getDtAtivacao() {
            return this.dtAtivacao;
        }

        public void setIdRegional(String idRegional) {
            this.idRegional = idRegional;
        }

        public String getIdRegional() {
            return this.idRegional;
        }

        public void setIdTpLinha(String idTpLinha) {
            this.idTpLinha = idTpLinha;
        }

        public String getIdTpLinha() {
            return this.idTpLinha;
        }

        public void setListaRegional(Disponivel listaRegional) {
            this.listaRegional = listaRegional;
        }

        public Disponivel getListaRegional() {
            return this.listaRegional;
        }

        public void setListaTpLinhas(Disponivel listaTpLinhas) {
            this.listaTpLinhas = listaTpLinhas;
        }

        public Disponivel getListaTpLinhas() {
            return this.listaTpLinhas;
        }

        public void setResultset(Resultset resultset) {
            this.resultset = resultset;
        }

        public Resultset getResultset() {
            return this.resultset;
        }

        public void setPaginacao(Paginacao paginacao) {
            this.paginacao = paginacao;
        }

        public Paginacao getPaginacao() {
            return this.paginacao;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public int getPageNumber() {
            return this.pageNumber;
        }

        public void setRegPorPagina(int regPorPagina) {
            this.regPorPagina = regPorPagina;
        }

        public int getRegPorPagina() {
            return this.regPorPagina;
        }
    }
}
