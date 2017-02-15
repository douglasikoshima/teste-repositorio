package br.com.vivo.catalogoPRS.pageflows.param.relatoriofixa;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

public class RelatorioFixaAction extends MappingDispatchAction implements Serializable{

    private static final long serialVersionUID = 7536462055615293566L;
    private static final String SUCCESS = "success";
    private static final String SERVICO = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/relatoriofixa/servico/ServicoRelatorioFixaAction.do";
    private static final String SOLICITACAO_COMERCIAL = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/relatoriofixa/solicitacaocomercial/SolicitacaoComercialRelatorioFixaAction.do";
    private static final String GRUPO_COMERCIAL = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/relatoriofixa/grupocomercial/GrupoComercialRelatorioFixaAction.do";
    private static final String RELACIONAMENTO = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/relatoriofixa/relacionamento/RelacionamentoRelatorioFixaAction.do";
    private static final String SC_X_GC_X_PM_X_AC = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/relatoriofixa/scxgcxpmxac/SCxGCxPMxACRelatorioFixaAction.do";
    private static final String SC_X_ENC_X_PF_X_GC_X_PM_X_AC = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/relatoriofixa/scxencxpfxgcxpmxac/SCxENCxPFxGCxPMxACRelatorioFixaAction.do";
    
    public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {     
        request.setAttribute("servico", SERVICO);
        request.setAttribute("solicitacaoComercial", SOLICITACAO_COMERCIAL);
        request.setAttribute("grupoComercial", GRUPO_COMERCIAL);
        request.setAttribute("relacionamento", RELACIONAMENTO);
        request.setAttribute("SCxGCxPMxAC", SC_X_GC_X_PM_X_AC);
        request.setAttribute("SCxENCxPFxGCxPMxAC", SC_X_ENC_X_PF_X_GC_X_PM_X_AC);
        return mapping.findForward(SUCCESS);
    }

}
