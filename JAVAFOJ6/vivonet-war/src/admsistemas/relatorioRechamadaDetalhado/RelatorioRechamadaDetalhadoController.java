package admsistemas.relatorioRechamadaDetalhado;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionListener;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import br.com.vivo.fo.cliente.Pesquisar;
import br.com.vivo.fo.cliente.vo.ResultsetDocument.Resultset;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista.Disponivel;
import br.com.vivo.fo.fidelizacao.vo.ItDocument.It;
import com.indracompany.actions.AbstractAction;

public class RelatorioRechamadaDetalhadoController extends AbstractAction {

    private static final long serialVersionUID = 6540632713363659711L;

    private RelatorioForm     relatorioForm    = new RelatorioForm();

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        if ("begin".equals(mapping.getParameter())) {
            return begin(mapping, form, request, response);
        } else if ("clean".equals(mapping.getParameter())) {
            return clean(mapping, form, request, response);
        } else if ("pesquisar".equals(mapping.getParameter())) {
            return pesquisar(mapping, form, request, response);
        } else if ("exportar".equals(mapping.getParameter())) {
            return exportar(mapping, form, request, response);
        } else if ("montarPaginacao".equals(mapping.getParameter())) {
            montarPaginacao(mapping, form, request, response);
        } else if ("sessionAlive".equals(mapping.getParameter())) {
            sessionAlive(mapping, form, request, response);
        }
        return begin(mapping, form, request, response);
    }

    public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

        getRelatorioForm().setDataInicio(ConstantesCRM.SVAZIO);
        getRelatorioForm().setDataTermino(ConstantesCRM.SVAZIO);
        getRelatorioForm().setListaDDD(getListaDDD());
        getRelatorioForm().setListaFornecedor(getListaFornecedor());
        getRelatorioForm().setListaTipoLinha(getListaTipoLinha());
        getRelatorioForm().setListaMotivoRecontato(getListaMotivoRecontato());
        getRelatorioForm().setListaSegmento(getListaSegmento());
        getRelatorioForm().setListaSite(getListaSite());
        getRelatorioForm().setListaGrupo(getListaGrupo());

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    private Disponivel getListaGrupo() {
        String query = "select idgrupo, nmgrupo from  acesso.grupo order by nmgrupo";
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

    private Disponivel getListaSegmento() {
        String query = "select idsegmentacao, dssegmentacao from apoio.segmentacao";
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

    private Disponivel getListaDDD() {
        String query = "select idarearegistro, CDAREAREGISTRO||' - '||nmarearegistro from apoio.arearegistro";
        Pesquisar pesquisar = new Pesquisar();
        Resultset rsVO = pesquisar.executar(query);
        Disponivel listaTpLinha = Disponivel.Factory.newInstance();
        for (int j = 0; j < rsVO.getLinhas().getLinhaArray().length; j++) {
            Resultset.Linhas.Linha linha = rsVO.getLinhas().getLinhaArray(j);
            It it = listaTpLinha.addNewIt();
            it.setId(linha.getValorArray(1).substring(0, 2));
            it.setDs(linha.getValorArray(1));
        }
        return listaTpLinha;
    }

    private Disponivel getListaTipoLinha() {
        String query = "select idtipolinha, dstipolinha from apoio.tipolinha order by dstipolinha";
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

    private Disponivel getListaMotivoRecontato() {
        String query = "select idmotivorecontato, nmmotivorecontato from contatoadm.motivorecontato";
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

    private Disponivel getListaFornecedor() {
        String query = "select idfornecedorconsultoratd, DSFORNECEDORCONSULTORATD from apoio.fornecedorconsultoratd ";
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

    private Disponivel getListaSite() {
        String query = "select idsiteconsultoratd, dssiteconsultoratd from apoio.siteconsultoratd";
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

    public ActionForward clean(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        // Tempo de atualização do relatorio

        Resultset rsVO = Resultset.Factory.newInstance();
        rsVO.addNewLinhas();

        getRelatorioForm().setDataInicio(ConstantesCRM.SVAZIO);
        getRelatorioForm().setDataTermino(ConstantesCRM.SVAZIO);
        getRelatorioForm().setResultset(rsVO);

        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    public ActionForward exportar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String dataInicio = request.getParameter("dataInicio");
        String dataTermino = request.getParameter("dataTermino");
        String ddd = request.getParameter("ddd");
        String numeroTelefone = request.getParameter("numeroTelefone").replace("-", "");
        //String grupo = request.getParameter("grupo");
        String fornecedor = request.getParameter("fornecedor");
        String site = request.getParameter("site");
        String tipoLinha = request.getParameter("tipoLinha");
        String motivoRecontato = request.getParameter("motivoRecontato");
        String segmentacao = request.getParameter("segmentacao");

        StringBuffer query = new StringBuffer();

        query.append(" select rownum, ");
        query.append("        canal.nmcanal, ");
        query.append("        to_char(recontato.dtabertura, 'dd/mm/yyyy hh24:mi:ss') as data, ");
        query.append("        grupo.nmgrupo, ");
        query.append("        fornecedor.dsfornecedorconsultoratd, ");
        query.append("        site.dssiteconsultoratd,    ");
        query.append("        usuario.nmloginusuario, ");
        query.append("        motivo.nmmotivorecontato, ");
        query.append("        submotivo.nmsubmotivorecontato, ");
        query.append("        atendimentoprotocolo.cdarearegistro||'-'||atendimentoprotocolo.nrtelefone, ");
        query.append("        tipolinha.dstipolinha, ");
        query.append("        segmentacao.dssegmentacao, ");
        query.append("        respostanotasatd.nota, ");
        query.append("        recontato.idatendimentoprotocolo ");
        query.append("   from atendimento.dadosrecontato       recontato, ");
        query.append("        apoio.canal                      canal, ");
        query.append("        contatoadm.motivorecontato       motivo, ");
        query.append("        contatoadm.submotivorecontato    submotivo, ");
        query.append("        apoio.fornecedorconsultoratd     fornecedor, ");
        query.append("        acesso.grupo                     grupo, ");
        query.append("        apoio.siteconsultoratd           site, ");
        query.append("        apoio.segmentacao                segmentacao, ");
        query.append("        apoio.tipolinha                  tipolinha, ");
        query.append("        acesso.usuario                   usuario, ");
        query.append("        atendimento.atendimentoprotocolo atendimentoprotocolo, ");
        query.append("        customer.respostanotasatd        respostanotasatd ");
        query.append("  where recontato.idatendimentoprotocolo    = atendimentoprotocolo.idatendimentoprotocolo ");
        query.append("    and recontato.idsegmentacao           = segmentacao.idsegmentacao ");
        query.append("    and recontato.idtipolinha             = tipolinha.idtipolinha ");
        query.append("    and recontato.idcanal                   = canal.idcanal(+) ");
        query.append("    and recontato.idmotivorecontato         = motivo.idmotivorecontato(+)    ");
        query.append("    and recontato.idsubmotivorecontato      = submotivo.idsubmotivorecontato(+) ");
        query.append("    and recontato.idfornecedorconsultoratd  = fornecedor.idfornecedorconsultoratd(+) ");
        query.append("    and recontato.idgrupo              = grupo.idgrupo(+) ");
        query.append("    and recontato.idsiteconsultoratd        = site.idsiteconsultoratd(+) ");
		query.append("    and recontato.idpessoausuario      = usuario.idpessoausuario(+) ");
        query.append("    and recontato.idatendimentoprotocolo    = respostanotasatd.idatendimentoprotocolo (+) ");

        if (dataInicio != null && dataTermino != null) {
            try {
                query.append(" and recontato.dtabertura >= TO_DATE('").append(dataInicio).append(" 00:00:00','dd/mm/yyyy hh24:mi:ss') ");
                query.append(" and recontato.dtabertura <= TO_DATE('").append(dataTermino).append(" 23:59:59','dd/mm/yyyy hh24:mi:ss') ");
            } catch (Exception e) {
            }
        }

        if (ddd != null && ddd.length() > 0) {
            query.append(" and atendimentoprotocolo.cdarearegistro = ").append(ddd);
        }

        if (numeroTelefone != null && numeroTelefone.length() > 0) {
            query.append(" and atendimentoprotocolo.nrtelefone = ").append(numeroTelefone);
        }

        if (tipoLinha != null && tipoLinha.length() > 0) {
            query.append(" and recontato.idtipolinha = ").append(tipoLinha);
        }

        if (motivoRecontato != null && motivoRecontato.length() > 0) {
            query.append(" and recontato.idmotivorecontato = ").append(motivoRecontato);
        }

        if (segmentacao != null && segmentacao.length() > 0) {
            query.append(" and recontato.idsegmentacao = ").append(segmentacao);
        }

        if (fornecedor != null && fornecedor.length() > 0) {
            query.append(" and recontato.idfornecedorconsultoratd = '").append(fornecedor).append("'");
        }

        if (site != null && site.length() > 0) {
            query.append(" and recontato.idsiteconsultoratd  = '").append(site).append("'");
        }

        query.append(" order by recontato.dtabertura desc ");
        
        Pesquisar pesquisar = new Pesquisar();
        Resultset rsVO = pesquisar.executar(query.toString());

        if (rsVO == null || rsVO.getLinhas() == null) {
            rsVO = Resultset.Factory.newInstance();
            rsVO.addNewLinhas();
        }

        response.addHeader("Content-Disposition", "attachment; filename=ConsultaContato.csv");
        response.addHeader("Content-Type", "application/force-download");
        response.addHeader("Content-Transfer-Encoding", "binary");
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Expires", "0");

        PrintWriter out = response.getWriter();
        StringBuffer relatorioStr = new StringBuffer(1024);

        relatorioStr.append("Sequencia|");
        relatorioStr.append("Canal|");
        relatorioStr.append("Data/Hora|");
        relatorioStr.append("Grupo Anterior|");
        relatorioStr.append("Fornecedor Anterior|");
        relatorioStr.append("Site Anterior|");
        relatorioStr.append("Login Anterior|");
        relatorioStr.append("Motivo Recontato|");
        relatorioStr.append("Submotivo Recontato|");
        relatorioStr.append("Numero Linha|");
        relatorioStr.append("Tipo Linha|");
        relatorioStr.append("Segmentacao|");
        relatorioStr.append("Nota da URA|");
        relatorioStr.append("Numero Protocolo|");
        relatorioStr.append("\n");

        for (int j = 0; j < rsVO.getLinhas().getLinhaArray().length; j++) {
            Resultset.Linhas.Linha linha = rsVO.getLinhas().getLinhaArray(j);
            for (int l = 0; l < linha.getValorArray().length; l++) {
                relatorioStr.append(linha.getValorArray(l) != null ? linha.getValorArray(l) + "|" : "" + "|");
            }
            relatorioStr.append("\n");
        }

        out.print(relatorioStr);
        out.flush();
        out.close();

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);

        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    public String montarQuery(int operacao, long pagInicial, long pagFinal, String dataInicio, String dataTermino, String ddd, String numeroTelefone, String tipoLinha, String motivoRecontato, String segmentacao, String site, String fornecedor) {
        StringBuffer query = new StringBuffer();
        StringBuffer queryIni = new StringBuffer();
        StringBuffer queryFim = new StringBuffer();

        if (operacao == 0) {
            queryIni.append(" select count(1) from ( ");
            queryFim.append(" ) ");
        } else {
            queryIni.append(" select * from ( ");
            queryFim.append(" ) ");
            queryFim.append(" where rlinha >= ").append(pagInicial).append(" and rlinha <= ").append(pagFinal);
        }

        query.append(" select rownum rlinha, ");
        query.append("        canal.nmcanal, ");
        query.append("        to_char(recontato.dtabertura, 'dd/mm/yyyy hh24:mi:ss') as data, ");
        query.append("        grupo.nmgrupo,        ");
        query.append("        fornecedor.dsfornecedorconsultoratd, ");
        query.append("        site.dssiteconsultoratd,    ");
        query.append("        usuario.nmloginusuario, ");
        query.append("        motivo.nmmotivorecontato, ");
        query.append("        submotivo.nmsubmotivorecontato, ");
        query.append("        atendimentoprotocolo.cdarearegistro||'-'||atendimentoprotocolo.nrtelefone nrtelefone, ");
        query.append("        tipolinha.dstipolinha, ");
        query.append("        segmentacao.dssegmentacao, ");
        query.append("        respostanotasatd.nota, ");
        query.append("        recontato.idatendimentoprotocolo                      ");
        query.append("   from atendimento.dadosrecontato       recontato, ");
        query.append("        apoio.canal                      canal, ");
        query.append("        contatoadm.motivorecontato       motivo, ");
        query.append("        contatoadm.submotivorecontato    submotivo, ");
        query.append("        apoio.fornecedorconsultoratd     fornecedor, ");
        query.append("        acesso.grupo                     grupo, ");
        query.append("        apoio.siteconsultoratd           site, ");
        query.append("        apoio.segmentacao                segmentacao, ");
        query.append("        apoio.tipolinha                  tipolinha, ");
        query.append("        acesso.usuario                   usuario, ");
        query.append("        atendimento.atendimentoprotocolo atendimentoprotocolo, ");
        query.append("        customer.respostanotasatd        respostanotasatd ");
        query.append("  where recontato.idatendimentoprotocolo    = atendimentoprotocolo.idatendimentoprotocolo ");
        query.append("    and recontato.idsegmentacao           = segmentacao.idsegmentacao ");
        query.append("    and recontato.idtipolinha             = tipolinha.idtipolinha ");
        query.append("    and recontato.idcanal                   = canal.idcanal(+) ");
        query.append("    and recontato.idmotivorecontato         = motivo.idmotivorecontato(+)    ");
        query.append("    and recontato.idsubmotivorecontato      = submotivo.idsubmotivorecontato(+) ");
        query.append("    and recontato.idfornecedorconsultoratd  = fornecedor.idfornecedorconsultoratd(+) ");
        query.append("    and recontato.idgrupo              = grupo.idgrupo(+) ");
        query.append("    and recontato.idsiteconsultoratd        = site.idsiteconsultoratd(+) ");
        query.append("    and recontato.idpessoausuario      = usuario.idpessoausuario(+) ");
        query.append("    and recontato.idatendimentoprotocolo    = respostanotasatd.idatendimentoprotocolo (+) ");

        if (dataInicio != null && dataTermino != null) {
            try {
                query.append(" and recontato.dtabertura >= TO_DATE('").append(dataInicio).append(" 00:00:00','dd/mm/yyyy hh24:mi:ss') ");
                query.append(" and recontato.dtabertura <= TO_DATE('").append(dataTermino).append(" 23:59:59','dd/mm/yyyy hh24:mi:ss') ");
            } catch (Exception e) {
            }
        }

        if (ddd != null && ddd.length() > 0) {
            query.append(" and atendimentoprotocolo.cdarearegistro = ").append(ddd);
        }

        if (numeroTelefone != null && numeroTelefone.length() > 0) {
            query.append(" and atendimentoprotocolo.nrtelefone = ").append(numeroTelefone);
        }

        if (tipoLinha != null && tipoLinha.length() > 0) {
            query.append(" and recontato.idtipolinha = ").append(tipoLinha);
        }

        if (motivoRecontato != null && motivoRecontato.length() > 0) {
            query.append(" and recontato.idmotivorecontato   = ").append(motivoRecontato);
        }

        if (segmentacao != null && segmentacao.length() > 0) {
            query.append(" and recontato.idsegmentacao   = ").append(segmentacao);
        }

        if (fornecedor != null && fornecedor.length() > 0) {
            query.append(" and recontato.idfornecedorconsultoratd   = '").append(fornecedor).append("'");
        }

        if (site != null && site.length() > 0) {
            query.append(" and recontato.idsiteconsultoratd  = '").append(site).append("'");
        }

        query.append(" order by recontato.dtabertura desc ");
        
        return queryIni.append(query).append(queryFim).toString();
    }

    public ActionForward pesquisar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

        long totalPaginas = 0;
        long resto = 0;
        long pagInicial = 1;
        long qtdRegPaginas = 20;

        String dataInicio = request.getParameter("dataInicio");
        String dataTermino = request.getParameter("dataTermino");
        String ddd = request.getParameter("ddd");
        String numeroTelefone = request.getParameter("numeroTelefone").replace("-", "");
        String tipoLinha = request.getParameter("tipoLinha");
        String motivoRecontato = request.getParameter("motivoRecontato");
        String segmentacao = request.getParameter("segmentacao");
        String site = request.getParameter("site");
        String fornecedor = request.getParameter("fornecedor");

        String queryCount = montarQuery(0, 0, 0, dataInicio, dataTermino, ddd, numeroTelefone, tipoLinha, motivoRecontato, segmentacao, site, fornecedor);

        Pesquisar pesquisar = new Pesquisar();
        Resultset rsVO = pesquisar.executar(queryCount);

        if (rsVO != null || rsVO.getLinhas() != null) {
            try {
                totalPaginas = Long.parseLong(rsVO.getLinhas().getLinhaArray(0).getValorArray(0)) / qtdRegPaginas;
                resto = Long.parseLong(rsVO.getLinhas().getLinhaArray(0).getValorArray(0)) % qtdRegPaginas; 
                if(resto != 0){
                	totalPaginas += 1;
                }
                totalPaginas = (long) Math.floor(totalPaginas);
            } catch (Exception e) {
                totalPaginas = 0;
            }
        }

        request.getSession().setAttribute("totalPaginasRecontato", totalPaginas);

        String query = montarQuery(1, pagInicial, qtdRegPaginas, dataInicio, dataTermino, ddd, numeroTelefone, tipoLinha, motivoRecontato, segmentacao, site, fornecedor);

        rsVO = pesquisar.executar(query.toString());

        if (rsVO == null || rsVO.getLinhas() == null) {
            rsVO = Resultset.Factory.newInstance();
            rsVO.addNewLinhas();
        }

        getRelatorioForm().setDataInicio(dataInicio);
        getRelatorioForm().setDataTermino(dataTermino);
        getRelatorioForm().setResultset(rsVO);

        request.setAttribute("session", request.getSession());
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);

        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    public ActionForward montarPaginacao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

        String dataInicio = request.getParameter("dataInicio");
        String dataTermino = request.getParameter("dataTermino");
        String ddd = request.getParameter("ddd");
        String numeroTelefone = request.getParameter("numeroTelefone").replace("-", "");
        String tipoLinha = request.getParameter("tipoLinha");
        String motivoRecontato = request.getParameter("motivoRecontato");
        String segmentacao = request.getParameter("segmentacao");
        String site = request.getParameter("site");
        String fornecedor = request.getParameter("fornecedor");
        String paginaNum = request.getParameter("pagNum");

        long pagNum = Long.parseLong(paginaNum);
        long qtdRegPaginas = 20;
        long pagInicial = (pagNum * qtdRegPaginas) - qtdRegPaginas + 1;
        long pagFinal = (pagNum * qtdRegPaginas);

        String query = montarQuery(1, pagInicial, pagFinal, dataInicio, dataTermino, ddd, numeroTelefone, tipoLinha, motivoRecontato, segmentacao, site, fornecedor);

        Pesquisar pesquisar = new Pesquisar();
        Resultset rsVO = pesquisar.executar(query);

        if (rsVO == null || rsVO.getLinhas() == null) {
            rsVO = Resultset.Factory.newInstance();
            rsVO.addNewLinhas();
        }

        System.out.println(rsVO.getLinhas().getLinhaArray(0).getValorArray(2));

        getRelatorioForm().setDataInicio(dataInicio);
        getRelatorioForm().setDataTermino(dataTermino);
        getRelatorioForm().setResultset(rsVO);

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);

        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    public ActionForward sessionAlive(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

        String isAlive = request.getParameter("isAlive");

    	if(ConstantesCRM.STRUE.equalsIgnoreCase(isAlive)) request.setAttribute("msg", "Sessão Ativa!");
    	else request.setAttribute("msg", "Falha na Sessão!");

        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    public RelatorioForm getRelatorioForm() {
        if (this.relatorioForm == null) this.relatorioForm = new RelatorioForm();
        return relatorioForm;
    }

    public static class RelatorioForm extends ActionForm {

        private static final long serialVersionUID = -220661981086897156L;

        private Resultset         resultset;

        private String            dataInicio       = ConstantesCRM.SVAZIO;
        private String            dataTermino      = ConstantesCRM.SVAZIO;
        private String            ddd              = ConstantesCRM.SVAZIO;
        private String            numeroTelefone   = ConstantesCRM.SVAZIO;
        private String            tipoLinha        = ConstantesCRM.SVAZIO;
        private String            motivoRecontato  = ConstantesCRM.SVAZIO;
        private String            segmentacao      = ConstantesCRM.SVAZIO;
        private String            fornecedor       = ConstantesCRM.SVAZIO;
        private String            site             = ConstantesCRM.SVAZIO;
        private String            grupo            = ConstantesCRM.SVAZIO;

        private Disponivel        listaDDD;
        private Disponivel        listaTipoLinha;
        private Disponivel        listaMotivoRecontato;
        private Disponivel        listaSegmento;
        private Disponivel        listaFornecedor;
        private Disponivel        listaSite;
        private Disponivel        listaGrupo;

        // Inicio Listas
        public void setListaDDD(Disponivel listaDDD) {
            this.listaDDD = listaDDD;
        }

        public Disponivel getListaDDD() {
            return this.listaDDD;
        }

        public void setListaTipoLinha(Disponivel listaTipoLinha) {
            this.listaTipoLinha = listaTipoLinha;
        }

        public Disponivel getListaTipoLinha() {
            return this.listaTipoLinha;
        }

        public void setListaMotivoRecontato(Disponivel listaMotivoRecontato) {
            this.listaMotivoRecontato = listaMotivoRecontato;
        }

        public Disponivel getListaMotivoRecontato() {
            return this.listaMotivoRecontato;
        }

        public void setListaSegmento(Disponivel listaSegmento) {
            this.listaSegmento = listaSegmento;
        }

        public Disponivel getListaSegmento() {
            return this.listaSegmento;
        }

        public void setListaFornecedor(Disponivel listaFornecedor) {
            this.listaFornecedor = listaFornecedor;
        }

        public Disponivel getListaFornecedor() {
            return this.listaFornecedor;
        }

        public void setListaSite(Disponivel listaSite) {
            this.listaSite = listaSite;
        }

        public Disponivel getListaSite() {
            return this.listaSite;
        }

        public void setListaGrupo(Disponivel listaGrupo) {
            this.listaGrupo = listaGrupo;
        }

        public Disponivel getListaGrupo() {
            return this.listaGrupo;
        }

        // Termino Listas

        public String getGrupo() {
            return grupo;
        }

        public void setGrupo(String grupo) {
            this.grupo = grupo;
        }

        public String getDataInicio() {
            return dataInicio;
        }

        public void setDataInicio(String dataInicio) {
            this.dataInicio = dataInicio;
        }

        public String getDataTermino() {
            return dataTermino;
        }

        public void setDataTermino(String dataTermino) {
            this.dataTermino = dataTermino;
        }

        public String getDdd() {
            return ddd;
        }

        public void setDdd(String ddd) {
            this.ddd = ddd;
        }

        public String getNumeroTelefone() {
            return numeroTelefone;
        }

        public void setNumeroTelefone(String numeroTelefone) {
            this.numeroTelefone = numeroTelefone;
        }

        public String getTipoLinha() {
            return tipoLinha;
        }

        public void setTipoLinha(String tipoLinha) {
            this.tipoLinha = tipoLinha;
        }

        public String getMotivoRecontato() {
            return motivoRecontato;
        }

        public void setMotivoRecontato(String motivoRecontato) {
            this.motivoRecontato = motivoRecontato;
        }

        public String getSegmentacao() {
            return segmentacao;
        }

        public void setSegmentacao(String segmentacao) {
            this.segmentacao = segmentacao;
        }

        public String getFornecedor() {
            return fornecedor;
        }

        public void setFornecedor(String fornecedor) {
            this.fornecedor = fornecedor;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public void setResultset(Resultset resultset) {
            this.resultset = resultset;
        }

        public Resultset getResultset() {
            return this.resultset;
        }
    }

}
