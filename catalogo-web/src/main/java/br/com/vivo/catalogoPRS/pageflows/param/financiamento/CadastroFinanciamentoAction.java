package br.com.vivo.catalogoPRS.pageflows.param.financiamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.catalogoPRS.bo.UserCatalogo;
import br.com.vivo.catalogoPRS.delegate.FinanciamentoDelegate;
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.FinanciamentoForm;

import com.indracompany.catalogo.to.CondicaoPagamentoTO;

public class CadastroFinanciamentoAction extends BaseMappingDispatchAction implements Serializable {

	private static final long serialVersionUID = 98286388502486175L;
	
	private static final String SUCCESS = "success";

	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward gravar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		FinanciamentoForm financiamentoForm = (FinanciamentoForm)form;
		
        this.prepararFormulario(request);
        
        UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
        
		financiamentoForm.setUsuario(userCatalogo.getUser().getUsername());
		FinanciamentoDelegate financiamentoDelegate = new FinanciamentoDelegate();
		try {
			CondicaoPagamentoTO condicaoPagamentoTO = financiamentoDelegate.insertUpdate(financiamentoForm.buildTOInsertChange());
			if (financiamentoForm.getIdEditado() != null) {
                request.setAttribute("labelSucess", String.format("Financiamento alterado com sucesso", condicaoPagamentoTO.getCdCondicaoPagamento()));
				return this.search(mapping,form,request,response);
			} else {
			    request.setAttribute("labelSucess", String.format("Financiamento %s criado com sucesso", condicaoPagamentoTO.getCdCondicaoPagamento()));
				List<CondicaoPagamentoTO> condicaoPagamentoList = new ArrayList<CondicaoPagamentoTO>();
				condicaoPagamentoList.add(condicaoPagamentoTO);
				financiamentoForm = new FinanciamentoForm();
				financiamentoForm.setCodigoPlanoFinanciamento(condicaoPagamentoTO.getCdCondicaoPagamento());
				request.setAttribute("financiamentoList", condicaoPagamentoList );
			}			
		} catch (CatalogoPRSException e) {
			request.setAttribute("erro", e.getMessage());
            if (financiamentoForm.getIdEditado() != null) {
            	return this.search(mapping,form,request,response);
            }
            request.setAttribute("displayEditar", "block");
		}
		return mapping.findForward(SUCCESS);
	}

	public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		FinanciamentoForm financiamentoForm = (FinanciamentoForm)form;
		
        this.prepararFormulario(request);
		Integer id = financiamentoForm.getIdEditado();
		try {
			new FinanciamentoDelegate().remove(id);
		} catch (CatalogoPRSException e) {
			request.setAttribute("erro", e.getMessage());
		}
		return this.search(mapping,form,request,response);
	}
	
	public ActionForward changeStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		FinanciamentoForm financiamentoForm = (FinanciamentoForm)form;
		
        this.prepararFormulario(request);
        UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
		financiamentoForm.setUsuario(userCatalogo.getUser().getUsername());
		try {
			new FinanciamentoDelegate().changeStatus(financiamentoForm.getIdEditado());
	        if (financiamentoForm.getStatusAtual().equalsIgnoreCase("ativo")) {
	            request.setAttribute("labelSucess", "Configura&ccedil;&atilde;o Desativada com sucesso!");
	        } else {
	            request.setAttribute("labelSucess", "Configura&ccedil;&atilde;o Ativada com sucesso!");
	        }
		} catch (CatalogoPRSException e) {
			request.setAttribute("erro", e.getMessage());
		}
		return this.search(mapping,form,request,response);
	}	
	
	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		FinanciamentoForm financiamentoForm = (FinanciamentoForm)form;
				
        this.prepararFormulario(request);
        try {
            request.setAttribute("financiamentoList", new FinanciamentoDelegate().search(financiamentoForm.buildTO()));
        } catch (CatalogoPRSException e ){
            request.setAttribute("financiamentoList", new ArrayList<CondicaoPagamentoTO>());
        }
		return mapping.findForward(SUCCESS);
	}
    
    public void prepararFormulario(HttpServletRequest request) {
        request.setAttribute("displayEditar", "none");
    }
}