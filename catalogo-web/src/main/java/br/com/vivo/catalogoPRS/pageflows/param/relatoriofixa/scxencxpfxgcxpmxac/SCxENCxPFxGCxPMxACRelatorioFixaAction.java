package br.com.vivo.catalogoPRS.pageflows.param.relatoriofixa.scxencxpfxgcxpmxac;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import br.com.vivo.catalogoPRS.delegate.RelatorioFixaDelegate;
import br.com.vivo.catalogoPRS.pageflows.shared.form.SCxENCxPFxGCxPMxACRelatorioFixaForm;

import com.indracompany.catalogo.to.SCxENCxPFxGCxPMxACRelatorioFixaTO;
import com.indracompany.catalogo.to.TipoSolicitacaoComercialTO;

public class SCxENCxPFxGCxPMxACRelatorioFixaAction extends MappingDispatchAction implements java.io.Serializable {

	private static final long serialVersionUID = -8627867865742014716L;
    private static final String SUCCESS = "success";
    private static final String EXPORTAR = "exportar.do#acaoForm";
    private static final String NOME_RELATORIO = "SCxENCxPFxGCxPMxAC";
    private List<TipoSolicitacaoComercialTO> tipoSolicitacaoComercialTOList;
    private List<SCxENCxPFxGCxPMxACRelatorioFixaTO> sCxENCxPFxGCxPMxACRelatorioFixaTOList;
	
	
    public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		carregarDadosParaPagina(request,response);
        return mapping.findForward(SUCCESS);
    }

	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	
    	SCxENCxPFxGCxPMxACRelatorioFixaForm sCxENCxPFxGCxPMxACRelatorioFixaForm = (SCxENCxPFxGCxPMxACRelatorioFixaForm)form;
    	
		this.carregarDadosParaPagina(request,response);
		this.sCxENCxPFxGCxPMxACRelatorioFixaTOList = new RelatorioFixaDelegate().pesquisarSCxENCxPFxGCxPMxAC(doSCxENCxPFxGCxPMxACRelatorioFixaTO(sCxENCxPFxGCxPMxACRelatorioFixaForm));
        if (this.sCxENCxPFxGCxPMxACRelatorioFixaTOList.isEmpty()) {
            request.setAttribute("styleBotaoExportar", "btNavegacao74Inativo");
        }
        request.setAttribute("sCxENCxPFxGCxPMxACRelatorioFixaTOList", this.sCxENCxPFxGCxPMxACRelatorioFixaTOList);
        return mapping.findForward(SUCCESS);
    }
	
    public ActionForward exportar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
    	
    	SCxENCxPFxGCxPMxACRelatorioFixaForm sCxENCxPFxGCxPMxACRelatorioFixaForm = (SCxENCxPFxGCxPMxACRelatorioFixaForm)form;
    	
        this.carregarDadosParaPagina(request,response);
        this.gerarArquivoExportacao(request,response);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward ordenar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        this.carregarDadosParaPagina(request,response);
        request.setAttribute("sCxENCxPFxGCxPMxACRelatorioFixaTOList", this.sCxENCxPFxGCxPMxACRelatorioFixaTOList);
        return mapping.findForward(SUCCESS);
    }

    private void gerarArquivoExportacao(HttpServletRequest request,
			HttpServletResponse response){
        StringBuffer sb = new StringBuffer("Codigo do Servico;Nome do Servico;Nome do tipo de solicitacao;Codigo da solicitacao comercial;Nome da solicitacao comercial;Codigo do encargo;Nome do encargo;Codigo do plano de financiamento;Nome do plano de financiamento;Codigo do grupo comercial;Nome do grupo comercial;Codigo do plano minutos;Nome do plano minutos;Codigo da area de concorrencia;Nome da area de concorrencia");
        for (SCxENCxPFxGCxPMxACRelatorioFixaTO to : this.sCxENCxPFxGCxPMxACRelatorioFixaTOList) {
            sb.append(String.format("\n%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s",
                    to.getCdServico(),
                    to.getNmServico(),
                    to.getNmTipoSolicitacaoComercial(),
                    to.getCdSolicitacaoComercial(),
                    to.getNmSolicitacaoComercial(),
                    to.getCdEncargo(),
                    to.getDsEncargo(),
                    to.getCdPlanoFinanciamento(),
                    to.getNmPlanoFinanciamento(),
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
    
    private SCxENCxPFxGCxPMxACRelatorioFixaTO doSCxENCxPFxGCxPMxACRelatorioFixaTO(SCxENCxPFxGCxPMxACRelatorioFixaForm form){
        SCxENCxPFxGCxPMxACRelatorioFixaTO to = new SCxENCxPFxGCxPMxACRelatorioFixaTO();
        to.setCdServico(form.getCdServico());
        to.setCdAreaConcorrencia(form.getCdAreaConcorrencia());
        to.setCdEncargo(form.getCdEncargo());
        to.setCdGrupoComercial(form.getCdGrupoComercial());
        to.setCdPlanoFinanciamento(form.getCdPlanoFinanciamento());
        to.setCdPlanoMinutos(form.getCdPlanoMinutos());
        to.setCdSolicitacaoComercial(form.getCdSolicitacaoComercial());
        to.setCdTipoSolicitacaoComercial(form.getCdTipoSolicitacaoComercial());
        to.setDsEncargo(form.getDsEncargo());
        to.setNmGrupoComercial(form.getNmGrupoComercial());
        to.setNmPlanoFinanciamento(form.getNmPlanoFinanciamento());
        to.setNmServico(form.getNmServico());
        to.setNmSolicitacaoComercial(form.getNmSolicitacaoComercial());
        return to;
    }    
	
	public List<SCxENCxPFxGCxPMxACRelatorioFixaTO> getSCxENCxPFxGCxPMxACRelatorioFixaTOList() {
		return sCxENCxPFxGCxPMxACRelatorioFixaTOList;
	}

	public void setSCxENCxPFxGCxPMxACRelatorioFixaTOList(
			List<SCxENCxPFxGCxPMxACRelatorioFixaTO> cxENCxPFxGCxPMxACRelatorioFixaTOList) {
		sCxENCxPFxGCxPMxACRelatorioFixaTOList = cxENCxPFxGCxPMxACRelatorioFixaTOList;
	}

	public List<TipoSolicitacaoComercialTO> getTipoSolicitacaoComercialTOList() {
		return tipoSolicitacaoComercialTOList;
	}

	public void setTipoSolicitacaoComercialTOList(
			List<TipoSolicitacaoComercialTO> tipoSolicitacaoComercialTOList) {
		this.tipoSolicitacaoComercialTOList = tipoSolicitacaoComercialTOList;
	}

}
