package br.com.vivo.catalogoPRS.pageflows.param.servicofixa;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.catalogoPRS.delegate.ServicoFixaDelegate;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.ServicoFixaForm;

import com.indracompany.catalogo.to.ServicoFixaTO;

public class ServicoFixaAction extends BaseMappingDispatchAction implements Serializable {
 
    private static final long serialVersionUID = -8827667946362404482L;
    private static final String SUCCESS = "success";
    
    private List<ServicoFixaTO> servicoFixaTOList;
    
    public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		this.cleanHeader(response);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)  {
    	ServicoFixaForm servicoFixaForm = (ServicoFixaForm)form;
    	
        servicoFixaForm.setIdServico(null);
        request.getSession().setAttribute("idServico", servicoFixaForm.getIdServico());
        this.servicoFixaTOList = new ServicoFixaDelegate().search(servicoFixaForm.buildTO());
        request.setAttribute("servicoFixaTOList", this.servicoFixaTOList);
        request.setAttribute("servicoFixaTOListSize", this.servicoFixaTOList.size());
		this.cleanHeader(response);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward addIdServico(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)  {
    	
    	ServicoFixaForm servicoFixaForm = (ServicoFixaForm)form;
    	
        String idServico = servicoFixaForm.getIdServico();
        if (this.servicoFixaTOList == null) {
            this.search(mapping,form,request,response);
            servicoFixaForm.setIdServico(idServico);
        } else {
            request.setAttribute("servicoFixaTOList", this.servicoFixaTOList);
            request.setAttribute("servicoFixaTOListSize", this.servicoFixaTOList.size());
        }
        request.setAttribute("servicoSelecionado", String.format("C&oacute;digo do Servi&ccedil;o %s", servicoFixaForm.getCdServicoSelecionado()));
        request.getSession().setAttribute("idServico", idServico);
        request.getSession().setAttribute("nmTipoServico", servicoFixaForm.getNmTipoServico());
        request.getSession().setAttribute("alterarServico", servicoFixaForm.getAlterar());
		this.cleanHeader(response);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward changeStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)  {
    	
    	ServicoFixaForm servicoFixaForm = (ServicoFixaForm)form;
    	
        String newStatus = new ServicoFixaDelegate().changeStatus(Integer.valueOf(servicoFixaForm.getIdServico()));
        if (newStatus.equalsIgnoreCase("S")) {
            request.setAttribute("labelSucess", "Configura&ccedil;&atilde;o Ativada com sucesso!");
        } else {
            request.setAttribute("labelSucess", "Configura&ccedil;&atilde;o Desativada com sucesso!");
        }
        return this.search(mapping,form,request,response);
    }
    
    public List<ServicoFixaTO> getServicoFixaTOList() {
        return servicoFixaTOList;
    }

    public void setServicoTOList(List<ServicoFixaTO> servicoFixaTOList) {
        this.servicoFixaTOList = servicoFixaTOList;
    }
}
