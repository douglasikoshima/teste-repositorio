package br.com.vivo.catalogoPRS.pageflows.param.servicosadicionais;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.catalogoPRS.delegate.ServicosAdicionaisDelegate;
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.ServicosAdicionaisForm;

import com.indracompany.catalogo.to.ServicosAdicionaisTO;
import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;
import com.indracompany.catalogo.to.TipoServicoTO;

public class ServicosAdicionaisAction extends BaseMappingDispatchAction implements Serializable {

    private static final long serialVersionUID = 2920777024673407739L;
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";
    private static Logger log = Logger.getLogger(ServicosAdicionaisAction.class);
    private List<ServicosAdicionaisTO> servicosAdicionaisTOList;
    private List<TipoServicoTO> tipoServicoTOList;
    private List<SolicitacaoComercialFixaTO> solicitacaoComercialFixaTOList;
    private ServicosAdicionaisTO servicosAdicionaisTO;

    // Mensagens
    private static final String MENSAGEM_INSERIR_SUCESSO = "Servi&#231;o adicional gravado com sucesso.";
    private static final String MENSAGEM_INSERIR_ERRO = "N&atilde;o foi poss&#237;vel gravar o servi&#231;o adicional.";
    private static final String MENSAGEM_EXCLUIR_SUCESSO = "Servi&#231;o adicional removido com sucesso.";
    private static final String MENSAGEM_EXCLUIR_ERRO = "N&atilde;o foi poss&#237;vel excluir o servi&#231;o adicional.";
    private static final String MENSAGEM_VERIFICAR_ERRO = "N&atilde;o foi poss&iacute;vel verificar serviço adicional";
    private static final String MENSAGEM_GRAVAR_ERRO_EXISTE_NA_BASE = "Servi&#231;o adicional j&#225; existente na base.";

    
    public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
        this.carregarTipoServico(request);
        this.search(mapping,form,request,response);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
        
        this.servicosAdicionaisTOList = new ServicosAdicionaisDelegate().search();
        log.debug("Lista Servicos Adicionais tabela: " + servicosAdicionaisTOList.toString());
        request.setAttribute("servicosAdicionaisTOList", this.servicosAdicionaisTOList);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward load(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	ServicosAdicionaisForm servicosAdicionaisForm = (ServicosAdicionaisForm)form;
    	
        log.debug(String.format("idServicosAdicionais: %s", servicosAdicionaisForm.getIdServicosAdicionais()));
        this.servicosAdicionaisTO = new ServicosAdicionaisDelegate().load(servicosAdicionaisForm.getIdServicosAdicionais());
        request.setAttribute("servicosAdicionaisTO", this.servicosAdicionaisTO);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward novo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
        request.setAttribute("novo", "novo");
        this.carregarTipoServico(request);
        return mapping.findForward(SUCCESS);
    }
    
    private void carregarTipoServico(HttpServletRequest request) {
        // carrega combo tipo servico
        if (this.tipoServicoTOList == null) {
            this.tipoServicoTOList = new ServicosAdicionaisDelegate().loadTipoServico();
        }
        request.setAttribute("tipoServicoTOList", this.tipoServicoTOList);
    }

    public ActionForward carregarNomeServico(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	ServicosAdicionaisForm servicosAdicionaisForm = (ServicosAdicionaisForm)form;
    	
        // carrega combo nome servico
        log.debug("Form idTipoServico: " + servicosAdicionaisForm.getIdTipoServico());
        log.debug("Form id: " + servicosAdicionaisForm.getId());
        try {
            servicosAdicionaisForm.setIdTipoServico(servicosAdicionaisForm.getId());
            this.servicosAdicionaisTOList = new ServicosAdicionaisDelegate().findServicosByIdTipoServico(servicosAdicionaisForm.buildTO());
        } catch (CatalogoPRSException e) {
            log.error("Erro ao efetuar busca", e);
            this.servicosAdicionaisTOList = new ArrayList<ServicosAdicionaisTO>();
        }
        log.debug("Retornando para view.");
        log.debug("ResultList nomeServico: " + servicosAdicionaisTOList.toString());
        request.setAttribute("servicoTOList", this.servicosAdicionaisTOList);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward carregarSolicitacao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	
    	ServicosAdicionaisForm servicosAdicionaisForm = (ServicosAdicionaisForm)form;
    	
        log.debug("Form idServico: " + servicosAdicionaisForm.getIdServico());
        log.debug("Form id: " + servicosAdicionaisForm.getId());
        try {
            servicosAdicionaisForm.setIdServico(servicosAdicionaisForm.getId());
            this.solicitacaoComercialFixaTOList = new ServicosAdicionaisDelegate().findSolicitacaoComercial(servicosAdicionaisForm.buildTO());
        } catch (CatalogoPRSException e) {
            log.error("Erro ao efetuar busca", e);
            this.solicitacaoComercialFixaTOList = new ArrayList<SolicitacaoComercialFixaTO>();
        }
        log.debug("Retornando para view.");
        log.debug("ResultList nomeSolicitacao: " + solicitacaoComercialFixaTOList.toString());
        request.setAttribute("SolicitacaoTOList", this.solicitacaoComercialFixaTOList);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward ordenar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	ServicosAdicionaisForm servicosAdicionaisForm = (ServicosAdicionaisForm)form;
    	
        log.debug(servicosAdicionaisForm);
        this.carregarTipoServico(request);
        request.setAttribute("servicosAdicionaisTOList", this.servicosAdicionaisTOList);
        return mapping.findForward(SUCCESS);
    }

    private Boolean verificarServicosAdicionaisExistente(ServicosAdicionaisForm servicosAdicionaisForm,HttpServletRequest request) {
        // Verifica se o Serviço (Oferta Adicional) já existe na base.
        Boolean exisitsServicoAdicional = false;
        try {
            exisitsServicoAdicional = new ServicosAdicionaisDelegate().existsSolicitacaoComercial(servicosAdicionaisForm.buildTO());
        } catch (CatalogoPRSException e) {
            log.error("Erro ao verificar se existe solicitação comercial", e);
            request.setAttribute("msg_type", "error");
            request.setAttribute("msg_info", MENSAGEM_VERIFICAR_ERRO);
        }
        return exisitsServicoAdicional;
    }

    public ActionForward gravar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	ServicosAdicionaisForm servicosAdicionaisForm = (ServicosAdicionaisForm)form;
    	
        log.debug("Preparando dados para gravar um servico adicional.");
        log.debug("Form: " + servicosAdicionaisForm.getIdSolicitacaoComercial());
        log.debug("Form: " + servicosAdicionaisForm.getNomeSolicitacao());
        log.debug("Form: " + servicosAdicionaisForm.getDtInicio());
        log.debug("Form: " + servicosAdicionaisForm.getDtFim());
        log.debug("Form: " + servicosAdicionaisForm.getTempoVigencia());
        if (this.verificarServicosAdicionaisExistente(servicosAdicionaisForm,request) == false) { // False - Não existe na base.
            try {
                this.servicosAdicionaisTOList = new ServicosAdicionaisDelegate().recordSolicitacaoComercial(servicosAdicionaisForm.buildTO());
            } catch (CatalogoPRSException e) {
                log.error("Erro ao gravar", e);
                request.setAttribute("msg_type", "error");
                request.setAttribute("msg_info", MENSAGEM_INSERIR_ERRO);
            }

            log.debug("Retornando para view.");
            log.debug("ResultList nomeSolicitacao: " + servicosAdicionaisTOList.toString());
            this.carregarTipoServico(request);
            request.setAttribute("msg_type", "success");
            request.setAttribute("msg_info", MENSAGEM_INSERIR_SUCESSO);
            request.setAttribute("servicosAdicionaisTOList", this.servicosAdicionaisTOList);
        } else {
            log.error("Erro ao gravar, serviço já existe na base.");
            this.search(mapping,form,request,response);
            request.setAttribute("msg_type", "error");
            request.setAttribute("msg_info", MENSAGEM_GRAVAR_ERRO_EXISTE_NA_BASE);
            return mapping.findForward(SUCCESS);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward excluir(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	ServicosAdicionaisForm servicosAdicionaisForm = (ServicosAdicionaisForm)form;
    	
        log.debug("Preparando dados para um servico adicional.");
        log.debug("Form idSolicitacaoComercial: " + servicosAdicionaisForm.getIdSolicitacaoComercial());
        log.debug("Form id: " + servicosAdicionaisForm.getId());
        servicosAdicionaisForm.setIdServicosAdicionais(servicosAdicionaisForm.getId());
        try {
            servicosAdicionaisForm.setIdServicosAdicionais(servicosAdicionaisForm.getId());
            this.servicosAdicionaisTOList = new ServicosAdicionaisDelegate().deleteSolicitacaoComercial(servicosAdicionaisForm.buildTO());
        } catch (CatalogoPRSException e) {
            log.error("Erro ao gravar", e);
            request.setAttribute("msg_type", "error");
            request.setAttribute("msg_info", MENSAGEM_EXCLUIR_ERRO);
        }
        log.debug("Retornando para view.");
        log.debug("ResultList nomeSolicitacao: " + servicosAdicionaisTOList.toString());
        this.carregarTipoServico(request);
        request.setAttribute("servicosAdicionaisTOList", this.servicosAdicionaisTOList);
        request.setAttribute("msg_type", "success");
        request.setAttribute("msg_info", MENSAGEM_EXCLUIR_SUCESSO);
        return mapping.findForward(SUCCESS);
    }

    public ServicosAdicionaisTO getServicosAdicionaisTO() {
        return servicosAdicionaisTO;
    }

    public void setServicosAdicionaisTO(ServicosAdicionaisTO servicosAdicionaisTO) {
        this.servicosAdicionaisTO = servicosAdicionaisTO;
    }

}
