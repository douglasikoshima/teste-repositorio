package br.com.vivo.catalogoPRS.pageflows.param.canalvendaagrupador.agrupador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.vivo.catalogoPRS.delegate.CanalVendaAgrupadorDelegate;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.CanalVendaAgrupadorForm;

import com.indracompany.catalogo.to.EpsTO;

public class AgrupadorAction extends BaseMappingDispatchAction implements Serializable {

	private static final long serialVersionUID = 8115207103624049929L;
	private static final String MENSAGEM_SUCESSO_REMOCAO_AGRUPADOR = "Agrupador removido com sucesso.";
	private static final String MENSAGEM_SUCESSO_CRIACAO_AGRUPADOR = "Agrupador criado com sucesso.";
	private static final String MENSAGEM_SUCESSO_ALTERACAO_AGRUPADOR = "Agrupador alterado com sucesso.";
	private String SUCCESS = "success";
	
	
	private List<EpsTO> epsTOList = new ArrayList<EpsTO>(); 
	
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		EpsTO epsTO = new EpsTO();
		epsTO.setNmEps("");
		this.setEpsTOList(new CanalVendaAgrupadorDelegate().pesquisar(epsTO));
		request.setAttribute("epsTOList", this.getEpsTOList());
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward pesquisar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CanalVendaAgrupadorForm canalVendaAgrupadorForm = (CanalVendaAgrupadorForm)form;
		
		this.setEpsTOList(new CanalVendaAgrupadorDelegate().pesquisar(createEpsTO(canalVendaAgrupadorForm)));
		request.setAttribute("epsTOList", this.getEpsTOList());
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward remover(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CanalVendaAgrupadorForm canalVendaAgrupadorForm = (CanalVendaAgrupadorForm)form;
		CanalVendaAgrupadorDelegate canalVendaAgrupadorDelegate = new CanalVendaAgrupadorDelegate();
		
		try {
			canalVendaAgrupadorDelegate.remover(createEpsTO(canalVendaAgrupadorForm));
			request.setAttribute("labelSucess", MENSAGEM_SUCESSO_REMOCAO_AGRUPADOR);
			this.setEpsTOList(canalVendaAgrupadorDelegate.pesquisar(new EpsTO("")));
			request.setAttribute("epsTOList", this.getEpsTOList());
		} catch (BusinessException e) {
			request.setAttribute("epsTOList", this.getEpsTOList());
			request.setAttribute("labelError", e.getMessage());
		}
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward salvar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CanalVendaAgrupadorForm canalVendaAgrupadorForm = (CanalVendaAgrupadorForm)form;

		try {
			new CanalVendaAgrupadorDelegate().merge(createEpsTO(canalVendaAgrupadorForm));
			if(canalVendaAgrupadorForm.getIdEps() == null || canalVendaAgrupadorForm.getIdEps() == 0)
				request.setAttribute("labelSucess", MENSAGEM_SUCESSO_CRIACAO_AGRUPADOR);
			else
				request.setAttribute("labelSucess", MENSAGEM_SUCESSO_ALTERACAO_AGRUPADOR);
			this.setEpsTOList(new CanalVendaAgrupadorDelegate().pesquisar(new EpsTO("")));
		} catch(BusinessException e){
			request.setAttribute("epsTOList", this.getEpsTOList());
			request.setAttribute("labelError", e.getMessage());
		}
		request.setAttribute("epsTOList", this.getEpsTOList());
		clearForm(canalVendaAgrupadorForm);
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward trocarPagina(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("epsTOList", this.getEpsTOList());
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	private EpsTO createEpsTO(CanalVendaAgrupadorForm form){
		EpsTO epsTO = new EpsTO();
		epsTO.setIdEps(form.getIdEps());
		epsTO.setNmEps(form.getNmEps());
		return epsTO;
	}
	
	private void clearForm(CanalVendaAgrupadorForm form){
		form.setIdEps(null);
		form.setNmEps(null);
	}
	public List<EpsTO> getEpsTOList() {
		return epsTOList;
	}

	public void setEpsTOList(List<EpsTO> epsTOList) {
		this.epsTOList = epsTOList;
	}
}
