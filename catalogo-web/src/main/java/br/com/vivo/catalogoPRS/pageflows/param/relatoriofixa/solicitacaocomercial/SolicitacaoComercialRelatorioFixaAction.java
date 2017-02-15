package br.com.vivo.catalogoPRS.pageflows.param.relatoriofixa.solicitacaocomercial;

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
import br.com.vivo.catalogoPRS.pageflows.shared.form.SolicitacaoComercialRelatorioFixaForm;

import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;
import com.indracompany.catalogo.to.TipoServicoTO;
import com.indracompany.catalogo.to.TipoSolicitacaoComercialTO;


public class SolicitacaoComercialRelatorioFixaAction extends MappingDispatchAction implements Serializable{

    private static final long serialVersionUID = 7464888682993900639L;
    private static final String SUCCESS = "success";
    private static final String EXPORTAR = "exportar.do#acaoForm";
    private static final String NOME_RELATORIO = "SolicitacaoComercial";
    private List<TipoServicoTO> tipoServicoTOList;
    private List<TipoSolicitacaoComercialTO> tipoSolicitacaoComercialTOList;
    private List<SolicitacaoComercialFixaTO> solicitacaoComercialRelatorioFixaTOList;
    
    public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        this.carregarDadosParaPagina(request, response);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	
    	SolicitacaoComercialRelatorioFixaForm solicitacaoComercialRelatorioFixaForm = (SolicitacaoComercialRelatorioFixaForm)form;
    	
        this.carregarDadosParaPagina(request, response);
        this.solicitacaoComercialRelatorioFixaTOList = new RelatorioFixaDelegate().pesquisarSolicitacaoComercial(solicitacaoComercialRelatorioFixaForm.buildTO());
        request.setAttribute("solicitacaoComercialRelatorioFixaTOList", this.solicitacaoComercialRelatorioFixaTOList);

        if (this.solicitacaoComercialRelatorioFixaTOList.size() == 0) {
            request.setAttribute("styleBotaoExportar", "btNavegacao74Inativo");
        }
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
        request.setAttribute("solicitacaoComercialRelatorioFixaTOList", this.solicitacaoComercialRelatorioFixaTOList);
        return mapping.findForward(SUCCESS);
    }

    private void carregarDadosParaPagina(HttpServletRequest request,
			HttpServletResponse response) {
        if ( this.tipoServicoTOList == null ) {
            this.tipoServicoTOList = new ServicoFixaDelegate().findAllTpServico();
        }
        request.setAttribute("tipoServicoTOList", this.tipoServicoTOList);
        
        if ( this.tipoSolicitacaoComercialTOList == null ) {
            this.tipoSolicitacaoComercialTOList = new RelatorioFixaDelegate().findAllTpSolicitacaoComercial();
        }
        request.setAttribute("tipoSolicitacaoComercialTOList", this.tipoSolicitacaoComercialTOList);
        request.setAttribute("styleBotaoExportar", "btNavegacao74");
        request.setAttribute("exportar", EXPORTAR);
    }
    
    private void gerarArquivoExportacao(HttpServletRequest request,
			HttpServletResponse response) {
        StringBuffer sb = new StringBuffer("Tipo de servico;Codigo servico origem;Codigo servico catalogo;Nome do servico catalogo;Nome do servico origem;Tipo de solicitacao;Codigo da solicitacao comercial;Nome da solicitacao comercial;Prazo maximo de vigencia;Prazo maximo de atendimento");
        for (SolicitacaoComercialFixaTO to : this.solicitacaoComercialRelatorioFixaTOList) {
            sb.append(String.format("\n%s;%s;%s;%s;%s;%s;%s;%s;%s;%s", 
                    to.getNmTipoServico(),
                    to.getCdServicoOrigem(),
                    to.getCdServicoCatalogo(),
                    to.getNmServicoCatalogo(),
                    to.getNmServicoOrigem(),
                    to.getNmTipoSolicitacaoComercial(),
                    to.getCdSolicitacaoComercial(),
                    to.getNmSolicitacaoComercial(),
                    to.getPrazoMaximoVigencia(),
                    to.getPrazoMaximoAtendimento()
                    ));
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

    public List<TipoSolicitacaoComercialTO> getTipoSolicitacaoComercialTOList() {
        return tipoSolicitacaoComercialTOList;
    }

    public void setTipoSolicitacaoComercialTOList(
            List<TipoSolicitacaoComercialTO> tipoSolicitacaoComercialTOList) {
        this.tipoSolicitacaoComercialTOList = tipoSolicitacaoComercialTOList;
    }

    public List<SolicitacaoComercialFixaTO> getSolicitacaoComercialRelatorioFixaTO() {
        return solicitacaoComercialRelatorioFixaTOList;
    }

    public void setSolicitacaoComercialRelatorioFixaTO(
            List<SolicitacaoComercialFixaTO> solicitacaoComercialRelatorioFixaTO) {
        this.solicitacaoComercialRelatorioFixaTOList = solicitacaoComercialRelatorioFixaTO;
    }
}
