package br.com.vivo.catalogoPRS.pageflows.param.relatoriofixa.grupocomercial;

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
import br.com.vivo.catalogoPRS.pageflows.shared.form.GrupoComercialRelatorioFixaForm;

import com.indracompany.catalogo.to.GrupoComercialRelatorioFixaTO;

public class GrupoComercialRelatorioFixaAction extends MappingDispatchAction implements Serializable{

    private static final long serialVersionUID = 5537013624733714910L;
    private static final String SUCCESS = "success";
    private static final String EXPORTAR = "exportar.do#acaoForm";
    private static final String NOME_RELATORIO = "GrupoComercial";
    
    private List<GrupoComercialRelatorioFixaTO> grupoComercialRelatorioFixaTOList;
    
    public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        this.carregarDadosParaPagina(request, response);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    
    	GrupoComercialRelatorioFixaForm grupoComercialRelatorioFixaForm = (GrupoComercialRelatorioFixaForm)form;
    	
        this.carregarDadosParaPagina(request, response);
        this.carregarCampos(grupoComercialRelatorioFixaForm, request);
        this.grupoComercialRelatorioFixaTOList = new RelatorioFixaDelegate().pesquisarGrupoComercial(grupoComercialRelatorioFixaForm.buildTO());

        if (this.grupoComercialRelatorioFixaTOList.size() == 0) {
            request.setAttribute("styleBotaoExportar", "btNavegacao74Inativo");
        }
        request.setAttribute("grupoComercialRelatorioFixaTOList", this.grupoComercialRelatorioFixaTOList);
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
        request.setAttribute("grupoComercialRelatorioFixaTOList", this.grupoComercialRelatorioFixaTOList);
        return mapping.findForward(SUCCESS);
    }
    
    private void carregarDadosParaPagina(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("styleBotaoExportar", "btNavegacao74");
        request.setAttribute("exportar", EXPORTAR);
    }
    
    private void gerarArquivoExportacao(HttpServletRequest request,	HttpServletResponse response){
        StringBuffer sb = new StringBuffer("Codigo do Grupo; Nome do Grupo");
        for (GrupoComercialRelatorioFixaTO to : this.grupoComercialRelatorioFixaTOList) {
            sb.append(String.format("\n%s;%s", to.getCdGrupoComercial(), to.getDsGrupoComercial()));
        }
        String arquivo = sb.toString();
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
    
    private void carregarCampos(GrupoComercialRelatorioFixaForm grupoComercialRelatorioFixaForm, HttpServletRequest request){
    	
    	if(grupoComercialRelatorioFixaForm != null){
    		request.setAttribute("cdGrupoComercial", grupoComercialRelatorioFixaForm.getCdGrupoComercial());
    		request.setAttribute("dsGrupoComercial", grupoComercialRelatorioFixaForm.getDsGrupoComercial());		
    	}
    }     


    public List<GrupoComercialRelatorioFixaTO> getGrupoComercialRelatorioFixaTOList() {
        return grupoComercialRelatorioFixaTOList;
    }

    public void setGrupoComercialRelatorioFixaTOList(
            List<GrupoComercialRelatorioFixaTO> grupoComercialRelatorioFixaTOList) {
        this.grupoComercialRelatorioFixaTOList = grupoComercialRelatorioFixaTOList;
    }
}
