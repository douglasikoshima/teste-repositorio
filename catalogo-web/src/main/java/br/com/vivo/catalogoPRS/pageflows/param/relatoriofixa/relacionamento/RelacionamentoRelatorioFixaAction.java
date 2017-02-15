package br.com.vivo.catalogoPRS.pageflows.param.relatoriofixa.relacionamento;

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
import br.com.vivo.catalogoPRS.pageflows.shared.form.RelacionamentoRelatorioFixaForm;

import com.indracompany.catalogo.to.RelacionamentoRelatorioFixaTO;
import com.indracompany.catalogo.to.TipoRelacionamentoTO;
import com.indracompany.catalogo.to.TipoServicoTO;

public class RelacionamentoRelatorioFixaAction extends MappingDispatchAction implements Serializable{

    private static final long serialVersionUID = -9185386432404886693L;
    private static final String SUCCESS = "success";
    private static final String EXPORTAR = "exportar.do#acaoForm";
    private static final String NOME_RELATORIO = "relacionamentos";
    
    private List<TipoServicoTO> tipoServicoTOList;
    private List<TipoRelacionamentoTO> tipoRelacionamentoTOList;
    private List<RelacionamentoRelatorioFixaTO> relacionamentoRelatorioFixaTOList;
    
    public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        this.carregarDadosParaPagina(request,response);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        
    	RelacionamentoRelatorioFixaForm relacionamentoRelatorioFixaForm = (RelacionamentoRelatorioFixaForm)form;
    	
        this.carregarDadosParaPagina(request,response);
        this.relacionamentoRelatorioFixaTOList = new RelatorioFixaDelegate().pesquisarRelacionamentoServico(relacionamentoRelatorioFixaForm.buildTO());

        if (this.relacionamentoRelatorioFixaTOList.size() == 0) {
            request.setAttribute("styleBotaoExportar", "btNavegacao74Inativo");
        }
        request.setAttribute("relacionamentoRelatorioFixaTOList", this.relacionamentoRelatorioFixaTOList);
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
        this.carregarDadosParaPagina(request,response);
        request.setAttribute("relacionamentoRelatorioFixaTOList", this.relacionamentoRelatorioFixaTOList);
        return mapping.findForward(SUCCESS);
    }
    
    private void carregarDadosParaPagina(HttpServletRequest request, HttpServletResponse response) {
        if ( this.tipoServicoTOList == null ) {
            this.tipoServicoTOList = new ServicoFixaDelegate().findAllTpServico();
        }
        request.setAttribute("tipoServicoTOList", this.tipoServicoTOList);
        
        if ( this.tipoRelacionamentoTOList == null ) {
            this.tipoRelacionamentoTOList = new RelatorioFixaDelegate().findAllTpRelacionamento();
        }
        request.setAttribute("tipoRelacionamentoTOList", this.tipoRelacionamentoTOList);
        
        request.setAttribute("styleBotaoExportar", "btNavegacao74");
        request.setAttribute("exportar", EXPORTAR);
    }
    
    private void gerarArquivoExportacao(HttpServletRequest request,	HttpServletResponse response){
        StringBuffer sb = new StringBuffer("Tipo de servico Relacao;Nome do servico catalogo Relacao;Nome do servico origem Relacao;Codigo servico Relacao;Codigo servico origem Relacao;Nome do Tipo de Relacionamento;Sigla do Relacionamento;Tipo de servico relacionado;Nome do servico catalogo relacionado;Nome do servico origem relacionado;Codigo servico catalogo relacionado;Codigo servico origem relacionado;");
        for (RelacionamentoRelatorioFixaTO to : this.relacionamentoRelatorioFixaTOList) {
            sb.append(String.format("\n%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s", 
                    to.getNmTipoServicoMestre(),
                    to.getNmServicoCatalogoMestre(),
                    to.getNmServicoOrigemMestre(),
                    to.getCdServicoCatalogoMestre(),
                    to.getCdServicoOrigemMestre(),
                    to.getDscTipoRelacionamento(),
                    to.getSgTipoRelacionamento(),
                    to.getNmTipoServicoSubordinado(),
                    to.getNmServicoCatalogoSubordinado(),
                    to.getNmServicoOrigemSubordinado(),
                    to.getCdServicoCatalogoSubordinado(),
                    to.getCdServicoOrigemSubordinado()
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

    public List<TipoRelacionamentoTO> getTipoRelacionamentoTOList() {
        return tipoRelacionamentoTOList;
    }

    public void setTipoRelacionamentoTOList(
            List<TipoRelacionamentoTO> tipoRelacionamentoTOList) {
        this.tipoRelacionamentoTOList = tipoRelacionamentoTOList;
    }

    public List<RelacionamentoRelatorioFixaTO> getRelacionamentoRelatorioFixaTOList() {
        return relacionamentoRelatorioFixaTOList;
    }

    public void setRelacionamentoRelatorioFixaTOList(
            List<RelacionamentoRelatorioFixaTO> relacionamentoRelatorioFixaTOList) {
        this.relacionamentoRelatorioFixaTOList = relacionamentoRelatorioFixaTOList;
    }
}