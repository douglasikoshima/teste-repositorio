package br.com.vivo.catalogoPRS.pageflows.param.relatoriofixa.scxgcxpmxac;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import br.com.vivo.catalogoPRS.delegate.RelatorioFixaDelegate;
import br.com.vivo.catalogoPRS.pageflows.shared.form.SCxGCxPMxACRelatorioFixaForm;

import com.indracompany.catalogo.to.SCxGCxPMxACRelatorioFixaTO;
import com.indracompany.catalogo.to.TipoSolicitacaoComercialTO;

public class SCxGCxPMxACRelatorioFixaAction extends MappingDispatchAction implements Serializable{

    private static final long serialVersionUID = -4153256031076726011L;
    private static final String SUCCESS = "success";
    private static final String EXPORTAR = "exportar.do#acaoForm";
    private static final String NOME_RELATORIO = "SCxGCxPMxAC";
    private List<TipoSolicitacaoComercialTO> tipoSolicitacaoComercialTOList;
    private List<SCxGCxPMxACRelatorioFixaTO> sCxGCxPMxACRelatorioFixaTOList;
    
    public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {  
        this.carregarDadosParaPagina(request, response);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {  
    	
    	SCxGCxPMxACRelatorioFixaForm sCxGCxPMxACRelatorioFixaForm = (SCxGCxPMxACRelatorioFixaForm)form;
    	
        this.carregarDadosParaPagina(request, response);
        this.sCxGCxPMxACRelatorioFixaTOList = new RelatorioFixaDelegate().pesquisarSCxGCxPMxAC(sCxGCxPMxACRelatorioFixaForm.buildTO());

        if (this.sCxGCxPMxACRelatorioFixaTOList.size() == 0) {
            request.setAttribute("styleBotaoExportar", "btNavegacao74Inativo");
        }
        request.setAttribute("sCxGCxPMxACRelatorioFixaTOList", this.sCxGCxPMxACRelatorioFixaTOList);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward exportar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception { 
        this.carregarDadosParaPagina(request, response);
        this.gerarArquivoExportacao(request, response);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward ordenar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception { 
        this.carregarDadosParaPagina(request, response);
        request.setAttribute("sCxGCxPMxACRelatorioFixaTOList", this.sCxGCxPMxACRelatorioFixaTOList);
        return mapping.findForward(SUCCESS);
    }

    private void gerarArquivoExportacao(HttpServletRequest request,
			HttpServletResponse response){
        StringBuffer sb = new StringBuffer("Codigo do Servico;Nome do Servico;Nome tipo de solicitacao;Codigo da solicitacao comercial;Nome da solicitacao comercial;Codigo do grupo comercial;Nome do grupo comercial;Codigo do plano minutos;Nome do plano minutos;Codigo da area de concorrencia;Nome da area de concorrencia");
        for (SCxGCxPMxACRelatorioFixaTO to : this.sCxGCxPMxACRelatorioFixaTOList) {
            sb.append(String.format("\n%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s",
                    to.getCdServico(),
                    to.getNmServico(),
                    to.getNmTipoSolicitacaoComercial(),
                    to.getCdSolicitacaoComercial(),
                    to.getNmSolicitacaoComercial(),
                    to.getCdGrupoComercial(),
                    to.getNmGrupoComercial(),
                    to.getCdPlanoMinutos(),
                    to.getNmPlanoMinutos(),
                    to.getCdAreaConcorrencia(),
                    to.getNmAreaConcorrencia()
                    ));
        }
        String arquivo = sb.toString().replaceAll(";null", ";");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", String.format("attachment; filename=%s.csv", NOME_RELATORIO));
        response.setContentLength(arquivo.getBytes().length);
        try {
            OutputStream out = response.getOutputStream();
            out.write(arquivo.getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void carregarDadosParaPagina(HttpServletRequest request,
			HttpServletResponse response) {
        if ( this.tipoSolicitacaoComercialTOList == null ) {
            this.tipoSolicitacaoComercialTOList = new RelatorioFixaDelegate().findAllTpSolicitacaoComercial();
        }
        request.setAttribute("tipoSolicitacaoComercialTOList", this.tipoSolicitacaoComercialTOList);
        request.setAttribute("styleBotaoExportar", "btNavegacao74");
        request.setAttribute("exportar", EXPORTAR);
    }

    public List<TipoSolicitacaoComercialTO> getTipoSolicitacaoComercialTOList() {
        return tipoSolicitacaoComercialTOList;
    }

    public void setTipoSolicitacaoComercialTOList(
            List<TipoSolicitacaoComercialTO> tipoSolicitacaoComercialTOList) {
        this.tipoSolicitacaoComercialTOList = tipoSolicitacaoComercialTOList;
    }

    public List<SCxGCxPMxACRelatorioFixaTO> getSCxGCxPMxACRelatorioFixaTOList() {
        return sCxGCxPMxACRelatorioFixaTOList;
    }

    public void setSCxGCxPMxACRelatorioFixaTOList(
            List<SCxGCxPMxACRelatorioFixaTO> cxGCxPMxACRelatorioFixaTOList) {
        sCxGCxPMxACRelatorioFixaTOList = cxGCxPMxACRelatorioFixaTOList;
    }
}