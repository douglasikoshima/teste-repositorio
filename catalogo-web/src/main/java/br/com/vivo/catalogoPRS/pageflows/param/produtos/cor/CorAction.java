
package br.com.vivo.catalogoPRS.pageflows.param.produtos.cor;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.indracompany.catalogo.to.CorTO;

import br.com.vivo.catalogoPRS.bo.UserCatalogo;
import br.com.vivo.catalogoPRS.delegate.CorDelegate;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.CadastroCorForm;


public class CorAction extends BaseMappingDispatchAction implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		CadastroCorForm cadastroCorForm = (CadastroCorForm)form;
		carregarCombos(cadastroCorForm, request, response);
		return mapping.findForward("success");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ParseException {
		
		UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
		
		CadastroCorForm cadastroCorForm = (CadastroCorForm)form;
		cadastroCorForm.setRgb(cadastroCorForm.getRgbDisplay());
		CorDelegate corDelegate = new CorDelegate();
		CorTO corTO = doCorTO(cadastroCorForm);
		corTO.setNmUsuarioAlteracao(userCatalogo.getUser().getUsername());
		corTO.setDtAlteracao(new Date());
		corDelegate.createUpdateCor(corTO);
		request.setAttribute("labelSucess", "Altera&ccedil;&atilde;o realizada com sucesso");
		carregarCombos(cadastroCorForm, request, response);
		
		return mapping.findForward("success");
	}
	
	public ActionForward load(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		CadastroCorForm cadastroCorForm = (CadastroCorForm)form;
		CorDelegate corDelegate = new CorDelegate();
		if(cadastroCorForm.getIdCor() != null ) {
			doForm(cadastroCorForm, corDelegate.findById(doCorTO(cadastroCorForm)));
		}
		carregarCombos(cadastroCorForm, request, response);
		
		return mapping.findForward("success");
	}

	
	public CorTO doCorTO(CadastroCorForm cadastroCorForm) {
		CorTO corTO = new CorTO();
		corTO.setIdCor(cadastroCorForm.getIdCor());
		corTO.setNmCor(cadastroCorForm.getNmCor());
		if (cadastroCorForm.getRgb() != null && !cadastroCorForm.getRgb().equals("")) {
			corTO.setRgb(cadastroCorForm.getRgb().substring(1));
		}
		return corTO;
	}
	
	public CadastroCorForm doForm(CadastroCorForm cadastroCorForm, CorTO corTO) {
		cadastroCorForm.setIdCor(corTO.getIdCor());
		cadastroCorForm.setNmCor(corTO.getNmCor());
		cadastroCorForm.setRgb('#'+corTO.getRgb());
		
		return cadastroCorForm;
	}
	
	private void carregarCombos(CadastroCorForm form, HttpServletRequest request, HttpServletResponse response) {
		CorDelegate corDelegate = new CorDelegate(); 
		request.setAttribute("corList",corDelegate.findAll());
	}
}
