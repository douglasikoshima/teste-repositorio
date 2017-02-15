package br.com.vivo.catalogoPRS.pageflows.param.relatoriofixa.servico;

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
import br.com.vivo.catalogoPRS.delegate.ServicoFixaDelegate;
import br.com.vivo.catalogoPRS.pageflows.shared.form.ServicoRelatorioFixaForm;
import com.indracompany.catalogo.to.ServicoRelatorioFixaTO;
import com.indracompany.catalogo.to.TipoServicoTO;

public class ServicoRelatorioFixaAction extends MappingDispatchAction implements Serializable{

    private static final long serialVersionUID = 7536462055615293566L;
    private static final String SUCCESS = "success";
    private static final String EXPORTAR = "exportar.do#acaoForm";
    private static final String NOME_RELATORIO = "servico";
    
    private List<TipoServicoTO> tipoServicoTOList;
    private List<ServicoRelatorioFixaTO> servicoRelatorioFixaTOList;
    
    public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        this.carregarDadosParaPagina(request,response);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    
    	ServicoRelatorioFixaForm servicoRelatorioFixaForm =(ServicoRelatorioFixaForm)form;
    	
        this.carregarDadosParaPagina(request, response);
        this.servicoRelatorioFixaTOList = new RelatorioFixaDelegate().pesquisarServico(servicoRelatorioFixaForm.buildTO());

        if (this.servicoRelatorioFixaTOList.size() == 0) {
        	request.setAttribute("styleBotaoExportar", "btNavegacao74Inativo");
        }
        request.setAttribute("servicoRelatorioFixaTOList", this.servicoRelatorioFixaTOList);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward exportar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {     
        this.carregarDadosParaPagina(request,response);
        this.gerarArquivoExportacao(request,response);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward ordenar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {       
        this.carregarDadosParaPagina(request, response);
        request.setAttribute("servicoRelatorioFixaTOList", this.servicoRelatorioFixaTOList);
        return mapping.findForward(SUCCESS);
    }
    
    private void carregarDadosParaPagina(HttpServletRequest request,
			HttpServletResponse response) {
        if ( this.tipoServicoTOList == null ) {
            this.tipoServicoTOList = new ServicoFixaDelegate().findAllTpServico();
        }
        request.setAttribute("tipoServicoTOList", this.tipoServicoTOList);
        request.setAttribute("styleBotaoExportar", "btNavegacao74");
        request.setAttribute("exportar", EXPORTAR);
    }
    
    private void gerarArquivoExportacao(HttpServletRequest request,
			HttpServletResponse response){
        StringBuffer sb = new StringBuffer("Tipo de servico;Nome do servico catalogo;Nome do servico origem;Codigo servico Catalogo;Codigo servico origem;Nome da Categoria;Disponibilidade");
        for (ServicoRelatorioFixaTO to : this.servicoRelatorioFixaTOList) {
            sb.append(String.format(
                    "\n%s;%s;%s;%s;%s;%s;%s", 
                    to.getCdTipoServico(), 
                    to.getNmServicoCatalogo(), 
                    to.getNmServicoOrigem(), 
                    to.getCdServicoCatalogo(), 
                    to.getCdServicoOrigem(),
                    to.getNmCategoria(),
                    to.getDisponibilidade())
            );
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

    public List<TipoServicoTO> getTipoServicoTOList() {
        return tipoServicoTOList;
    }

    public void setTipoServicoTOList(List<TipoServicoTO> tipoServicoTOList) {
        this.tipoServicoTOList = tipoServicoTOList;
    }

    public List<ServicoRelatorioFixaTO> getServicoRelatorioFixaTOList() {
        return servicoRelatorioFixaTOList;
    }

    public void setServicoRelatorioFixaTOList(
            List<ServicoRelatorioFixaTO> servicoRelatorioFixaTOList) {
        this.servicoRelatorioFixaTOList = servicoRelatorioFixaTOList;
    }
}