package br.com.vivo.catalogoPRS.pageflows.param.analiseCreditoLinha;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.catalogoPRS.delegate.AnaliseCreditoLinhaDelegate;
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.AnaliseCreditoLinhaForm;

import com.indracompany.catalogo.to.AnaliseCreditoLinhaTO;
import com.indracompany.catalogo.to.AnaliseCreditoTO;

public class AnaliseCreditoLinhaAction extends BaseMappingDispatchAction implements Serializable{

    private static final long serialVersionUID = 2920777124673407739L;
    private static final String SUCCESS = "success";
    private static Logger log = Logger.getLogger(AnaliseCreditoLinhaAction.class);
    private List<AnaliseCreditoLinhaTO> analiseCreditoLinhaTOList;
    private List<AnaliseCreditoLinhaTO> servicoLinhaList;
    private List<AnaliseCreditoTO> analiseCreditoTOListScore;

    // Mensagens - Configura&#231;&atilde;o gravada com sucesso!
    private static final String MENSAGEM_INSERIR_SUCESSO = "Configura&#231;&atilde;o gravada com sucesso!";
    private static final String MENSAGEM_INSERIR_ERRO = "N&atilde;o foi poss&#237;vel gravar a configura&#231;&atilde;o.";

    public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)  {
        this.carregarServicoLinha(request);
        this.carregarScore(request);
        this.cleanHeader(response);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward pesquisar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)  {
    	AnaliseCreditoLinhaForm analiseCreditoLinhaForm = (AnaliseCreditoLinhaForm)form;
    	
        this.carregarScore(request);
        try {
            this.analiseCreditoLinhaTOList = new AnaliseCreditoLinhaDelegate().search(analiseCreditoLinhaForm.buildTO(),
                    this.analiseCreditoTOListScore);
        } catch (CatalogoPRSException e) {
            log.error("Erro ao efetuar pesquisa", e);
            this.analiseCreditoLinhaTOList = new ArrayList<AnaliseCreditoLinhaTO>();
        }
        request.setAttribute("msg_type", "success");
        request.setAttribute("analiseCreditoLinhaTOList", this.analiseCreditoLinhaTOList);
        this.cleanHeader(response);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward gravar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)  {
    	AnaliseCreditoLinhaForm analiseCreditoLinhaForm = (AnaliseCreditoLinhaForm)form;
    	
        try {
            new AnaliseCreditoLinhaDelegate().gravar(analiseCreditoLinhaForm.buildTO());
        } catch (CatalogoPRSException e) {
            log.error("Erro ao efetuar pesquisa", e);
            this.analiseCreditoLinhaTOList = new ArrayList<AnaliseCreditoLinhaTO>();
            request.setAttribute("msg_type", "error");
            request.setAttribute("msg_info", MENSAGEM_INSERIR_ERRO);
            return mapping.findForward(SUCCESS);
        }
        request.setAttribute("msg_type", "success");
        request.setAttribute("msg_info", MENSAGEM_INSERIR_SUCESSO);
        this.pesquisar(mapping,form,request,response);
        this.cleanHeader(response);
        return mapping.findForward(SUCCESS);
    }

    private void carregarServicoLinha(HttpServletRequest request) {
        if (this.servicoLinhaList == null) {
            this.servicoLinhaList = new AnaliseCreditoLinhaDelegate().loadServicoLinha();
        }
        request.setAttribute("servicoLinhaTOList", this.servicoLinhaList);
    }

    private void carregarScore(HttpServletRequest request) {
        if (this.analiseCreditoTOListScore == null) {
            this.analiseCreditoTOListScore = new AnaliseCreditoLinhaDelegate().loadScore();
        }
        log.debug("combo Score: " + analiseCreditoTOListScore.toString());
        request.setAttribute("scoreTOList", this.analiseCreditoTOListScore);
    }

    public ActionForward ordenar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)  {
    	AnaliseCreditoLinhaForm analiseCreditoLinhaForm = (AnaliseCreditoLinhaForm)form;
    	
        log.debug("Ordenar: " + analiseCreditoLinhaForm);
        log.debug("this.listTO: " + this.analiseCreditoLinhaTOList);
        request.setAttribute("analiseCreditoLinhaTOList", this.analiseCreditoLinhaTOList);
        return mapping.findForward(SUCCESS);
    }

}
