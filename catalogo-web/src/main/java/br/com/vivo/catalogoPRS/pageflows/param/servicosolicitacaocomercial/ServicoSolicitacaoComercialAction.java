package br.com.vivo.catalogoPRS.pageflows.param.servicosolicitacaocomercial;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.vivo.catalogoPRS.delegate.ServicoSolicitacaoComercialDelegate;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.ServicoSolicitacaoComercialForm;

import com.indracompany.catalogo.to.AreaConcorrenciaTO;
import com.indracompany.catalogo.to.CanalVendaSolicitacaoComercialTO;
import com.indracompany.catalogo.to.CanalVendaTO;
import com.indracompany.catalogo.to.CondicaoPagamentoEncargoTO;
import com.indracompany.catalogo.to.CondicaoPagamentoTO;
import com.indracompany.catalogo.to.EspServicoPlanoMinutoTO;
import com.indracompany.catalogo.to.PoliticaDispSlctComercialTO;
import com.indracompany.catalogo.to.ServicoSolicitacaoComercialTO;

public class ServicoSolicitacaoComercialAction extends BaseMappingDispatchAction implements Serializable{
    private static final String SUCCESS = "success";

	private static final long serialVersionUID = -5957647200215456205L;
	
	private ServicoSolicitacaoComercialDelegate servicoSolicitacaoComercialTODelegate = new ServicoSolicitacaoComercialDelegate();
	private ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTOCt = new ServicoSolicitacaoComercialTO();
	private List<ServicoSolicitacaoComercialTO> servicoSolicitacaoComercialTOListCt;
	
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	
    	ServicoSolicitacaoComercialForm servicoSolicitacaoComercialForm = (ServicoSolicitacaoComercialForm)form;

    	ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO = new ServicoSolicitacaoComercialTO();
    	
        if (request.getSession().getAttribute("idServico") != null) {
            request.setAttribute("idServico", request.getSession().getAttribute("idServico"));
            request.setAttribute("nmTipoServico", request.getSession().getAttribute("nmTipoServico"));
            servicoSolicitacaoComercialForm.setIdServico(Integer.valueOf((String) request.getSession().getAttribute("idServico")));
            servicoSolicitacaoComercialForm.setNmTipoServico((String) request.getSession().getAttribute("nmTipoServico"));
            servicoSolicitacaoComercialTO.setIdServico(servicoSolicitacaoComercialForm.getIdServico());
            servicoSolicitacaoComercialForm.setIdSistema(servicoSolicitacaoComercialTODelegate.findSistemaByIdServico(servicoSolicitacaoComercialTO).getIdSistema());
            servicoSolicitacaoComercialTO.setIdSistema(servicoSolicitacaoComercialForm.getIdSistema());
        }
        
		servicoSolicitacaoComercialTO = getCombos(servicoSolicitacaoComercialTO);
		
		this.setServicoSolicitacaoComercialTOCt(servicoSolicitacaoComercialTO);
		request.setAttribute("servicoSolicitacaoComercialTOCt",servicoSolicitacaoComercialTO);
		
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward searchSolicitacaoComercial(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	
    	ServicoSolicitacaoComercialForm servicoSolicitacaoComercialForm = (ServicoSolicitacaoComercialForm)form;
		
		
		List<ServicoSolicitacaoComercialTO> servicoSolicitacaoComercialTOList = new ArrayList<ServicoSolicitacaoComercialTO>();
		servicoSolicitacaoComercialTOList = servicoSolicitacaoComercialTODelegate.search(doSolicitacaoComercialTOSearch(servicoSolicitacaoComercialForm));
		
		this.setServicoSolicitacaoComercialTOListCt(servicoSolicitacaoComercialTOList);
		
		request.setAttribute("servicoSolicitacaoComercialTOListCt",this.getServicoSolicitacaoComercialTOListCt());
		request.setAttribute("servicoSolicitacaoComercialTOCt",this.getServicoSolicitacaoComercialTOCt());
		
		setRqDivFlags(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, request);
		
		
		return mapping.findForward(SUCCESS);
	}

	public ActionForward switchDisponibilidadeSolicitacaoComercial(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	
    	ServicoSolicitacaoComercialForm servicoSolicitacaoComercialForm = (ServicoSolicitacaoComercialForm)form;
		
		String mensagemRetorno = null;
		
		if(servicoSolicitacaoComercialForm.getInDisponivelNew().equalsIgnoreCase("S")){
			mensagemRetorno = "Configura&ccedil;&atilde;o desativada com sucesso!";
		} else{
			mensagemRetorno = "Configura&ccedil;&atilde;o ativada com sucesso!";
		}
		
		servicoSolicitacaoComercialTODelegate.switchDisponibilidadeSlctCmrl(doSolicitacaoComercialTO(servicoSolicitacaoComercialForm));
		
		this.setServicoSolicitacaoComercialTOListCt(servicoSolicitacaoComercialTODelegate.search(doSolicitacaoComercialTOSearch(servicoSolicitacaoComercialForm)));
		
		request.setAttribute("servicoSolicitacaoComercialTOListCt", this.getServicoSolicitacaoComercialTOListCt());
		request.setAttribute("servicoSolicitacaoComercialTOCt",this.getServicoSolicitacaoComercialTOCt());
		
		setRqDivFlags(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, request);

		request.setAttribute("labelSucess", mensagemRetorno);
		
		
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward switchDisponibilidadeCanalVendaSlctCmrl(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	
    	ServicoSolicitacaoComercialForm servicoSolicitacaoComercialForm = (ServicoSolicitacaoComercialForm)form;
		
		String mensagemRetorno = null;
		
		if(servicoSolicitacaoComercialForm.getInDisponivelNew().equalsIgnoreCase("S")){
			mensagemRetorno = "Configura&ccedil;&atilde;o desativada com sucesso!";
		} else{
			mensagemRetorno = "Configura&ccedil;&atilde;o ativada com sucesso!";
		}
		
		servicoSolicitacaoComercialTODelegate.switchDisponibilidadeCanalVendaSlctCmrl(doSolicitacaoComercialTO(servicoSolicitacaoComercialForm));
		
		ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO = servicoSolicitacaoComercialTODelegate.findCanalVendaBySolicitacaoComercial(doSolicitacaoComercialTOSearch(servicoSolicitacaoComercialForm));
		this.getServicoSolicitacaoComercialTOCt().setCanalVendaSolicitacaoComercialTOList(servicoSolicitacaoComercialTO.getCanalVendaSolicitacaoComercialTOList());
		this.getServicoSolicitacaoComercialTOCt().setCanalVendaSelecionavelTOList(servicoSolicitacaoComercialTO.getCanalVendaSelecionavelTOList());
		
		request.setAttribute("servicoSolicitacaoComercialTOCt", this.getServicoSolicitacaoComercialTOCt());
		request.setAttribute("servicoSolicitacaoComercialTOListCt",this.getServicoSolicitacaoComercialTOListCt());		
		
		setRqDivFlags(Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,request);

		request.setAttribute("labelSucess", mensagemRetorno);
		
		
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward findCanalVendaBySolicitacaoComercial(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	
    	ServicoSolicitacaoComercialForm servicoSolicitacaoComercialForm = (ServicoSolicitacaoComercialForm)form;
		
		
		ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO = servicoSolicitacaoComercialTODelegate.findCanalVendaBySolicitacaoComercial(doSolicitacaoComercialTOSearch(servicoSolicitacaoComercialForm));
		this.getServicoSolicitacaoComercialTOCt().setCanalVendaSolicitacaoComercialTOList(servicoSolicitacaoComercialTO.getCanalVendaSolicitacaoComercialTOList());
		this.getServicoSolicitacaoComercialTOCt().setCanalVendaSelecionavelTOList(servicoSolicitacaoComercialTO.getCanalVendaSelecionavelTOList());
		this.getServicoSolicitacaoComercialTOCt().setPoliticaDispSlctComercialTO(servicoSolicitacaoComercialTO.getPoliticaDispSlctComercialTO());
		
		request.setAttribute("servicoSolicitacaoComercialTOCt", this.getServicoSolicitacaoComercialTOCt());
		request.setAttribute("servicoSolicitacaoComercialTOListCt",this.getServicoSolicitacaoComercialTOListCt());
		
		setRqDivFlags(Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,request);
		
		
		return mapping.findForward(SUCCESS);
	}	

	public ActionForward createUpdateCanalVendaSlctCmrlList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	
    	ServicoSolicitacaoComercialForm servicoSolicitacaoComercialForm = (ServicoSolicitacaoComercialForm)form;
		
		
		ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO = doSolicitacaoComercialTO(servicoSolicitacaoComercialForm);
		servicoSolicitacaoComercialTODelegate.createUpdateCanalVendaSlctCmrlList(servicoSolicitacaoComercialTO);
		
		this.findCanalVendaBySolicitacaoComercial(mapping,form,request,response);
		
		request.setAttribute("servicoSolicitacaoComercialTOCt", this.getServicoSolicitacaoComercialTOCt());
		request.setAttribute("servicoSolicitacaoComercialTOListCt",this.getServicoSolicitacaoComercialTOListCt());
		
		request.setAttribute("labelSucess", "Grupos configurados com sucesso!");
		
		
		return mapping.findForward(SUCCESS);
	}	
	
	public ActionForward findDispArConcPlMinByIdCnVendaSlct(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	
    	ServicoSolicitacaoComercialForm servicoSolicitacaoComercialForm = (ServicoSolicitacaoComercialForm)form;
		
		
		ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO = doSolicitacaoComercialTOSearch(servicoSolicitacaoComercialForm);
		servicoSolicitacaoComercialTO = servicoSolicitacaoComercialTODelegate.findDispArConcPlMinByIdCnVendaSlct(servicoSolicitacaoComercialTO);
		this.getServicoSolicitacaoComercialTOCt().setDisponibilidadeSlctComercialTOList(servicoSolicitacaoComercialTO.getDisponibilidadeSlctComercialTOList());
		this.getServicoSolicitacaoComercialTOCt().setAreaConcorrenciaSlctSelecionavelTOList(servicoSolicitacaoComercialTO.getAreaConcorrenciaSlctSelecionavelTOList());
		this.getServicoSolicitacaoComercialTOCt().setAreaConcorrenciaSlctSelecionadaTOList(servicoSolicitacaoComercialTO.getAreaConcorrenciaSlctSelecionadaTOList());
		this.getServicoSolicitacaoComercialTOCt().setEspServicoPlanoMinutoSlctSelecionavelTOList(servicoSolicitacaoComercialTO.getEspServicoPlanoMinutoSlctSelecionavelTOList());
		this.getServicoSolicitacaoComercialTOCt().setEspServicoPlanoMinutoSlctSelecionadoTOList(servicoSolicitacaoComercialTO.getEspServicoPlanoMinutoSlctSelecionadoTOList());
		
		request.setAttribute("servicoSolicitacaoComercialTOCt", this.getServicoSolicitacaoComercialTOCt());
		request.setAttribute("servicoSolicitacaoComercialTOListCt",this.getServicoSolicitacaoComercialTOListCt());
		
		setRqDivFlags(Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,request);

		
		return mapping.findForward(SUCCESS);
	}

	public ActionForward createUpdateDispArConcPlMinByIdCnVendaSlct(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	
    	ServicoSolicitacaoComercialForm servicoSolicitacaoComercialForm = (ServicoSolicitacaoComercialForm)form;
		
		
		ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO = doSolicitacaoComercialTO(servicoSolicitacaoComercialForm);
		servicoSolicitacaoComercialTO = servicoSolicitacaoComercialTODelegate.createUpdateDispArConcPlMinByIdCnVendaSlct(servicoSolicitacaoComercialTO);
		
		this.findCanalVendaBySolicitacaoComercial(mapping,form,request,response);
		
		request.setAttribute("servicoSolicitacaoComercialTOCt", this.getServicoSolicitacaoComercialTOCt());
		request.setAttribute("labelSucess", "Registro configurado com sucesso!");
		
		setRqDivFlags(Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,request);
		
		
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward searchEncargoBySolicitacaoComercial(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	
    	ServicoSolicitacaoComercialForm servicoSolicitacaoComercialForm = (ServicoSolicitacaoComercialForm)form;
		
		
		ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO = servicoSolicitacaoComercialTODelegate.searchEncargoBySolicitacaoComercial(doSolicitacaoComercialTOSearch(servicoSolicitacaoComercialForm));
		this.getServicoSolicitacaoComercialTOCt().setEncargoPoliticaPrecificacaoTOList(servicoSolicitacaoComercialTO.getEncargoPoliticaPrecificacaoTOList()); 
		
		request.setAttribute("servicoSolicitacaoComercialTOCt", this.getServicoSolicitacaoComercialTOCt());
		request.setAttribute("servicoSolicitacaoComercialTOListCt",this.getServicoSolicitacaoComercialTOListCt());
		
		setRqDivFlags(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,request);
		
		
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward searchCndcPgtoEncargo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	
    	ServicoSolicitacaoComercialForm servicoSolicitacaoComercialForm = (ServicoSolicitacaoComercialForm)form;
		
		
		ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO = servicoSolicitacaoComercialTODelegate.searchCndcPgtoEncargo(doSolicitacaoComercialTOSearch(servicoSolicitacaoComercialForm));
		this.getServicoSolicitacaoComercialTOCt().setCondicaoPagamentoEncargoTOList(servicoSolicitacaoComercialTO.getCondicaoPagamentoEncargoTOList()); 
		
		request.setAttribute("servicoSolicitacaoComercialTOCt", this.getServicoSolicitacaoComercialTOCt());
		request.setAttribute("servicoSolicitacaoComercialTOListCt",this.getServicoSolicitacaoComercialTOListCt());
		
		setRqDivFlags(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE,request);
		
		
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward findDispArConcPlMinByIdCndcPgtoEnc(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	
    	ServicoSolicitacaoComercialForm servicoSolicitacaoComercialForm = (ServicoSolicitacaoComercialForm)form;
		
		
		ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO = doSolicitacaoComercialTOSearch(servicoSolicitacaoComercialForm);
		servicoSolicitacaoComercialTO = servicoSolicitacaoComercialTODelegate.findDispArConcPlMinByIdCndcPgtoEnc(servicoSolicitacaoComercialTO);
		this.getServicoSolicitacaoComercialTOCt().setDisponibilidadeCndcPagamentoTOList(servicoSolicitacaoComercialTO.getDisponibilidadeCndcPagamentoTOList());
		this.getServicoSolicitacaoComercialTOCt().setAreaConcorrenciaCndcSelecionavelTOList(servicoSolicitacaoComercialTO.getAreaConcorrenciaCndcSelecionavelTOList());
		this.getServicoSolicitacaoComercialTOCt().setAreaConcorrenciaCndcSelecionadaTOList(servicoSolicitacaoComercialTO.getAreaConcorrenciaCndcSelecionadaTOList());
		this.getServicoSolicitacaoComercialTOCt().setEspServicoPlanoMinutoCndcSelecionavelTOList(servicoSolicitacaoComercialTO.getEspServicoPlanoMinutoCndcSelecionavelTOList());
		this.getServicoSolicitacaoComercialTOCt().setEspServicoPlanoMinutoCndcSelecionadoTOList(servicoSolicitacaoComercialTO.getEspServicoPlanoMinutoCndcSelecionadoTOList());
		
		request.setAttribute("servicoSolicitacaoComercialTOCt", this.getServicoSolicitacaoComercialTOCt());
		request.setAttribute("servicoSolicitacaoComercialTOListCt",this.getServicoSolicitacaoComercialTOListCt());
		
		setRqDivFlags(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, Boolean.TRUE,request);

		
		return mapping.findForward(SUCCESS);
	}

	public ActionForward findCanalVendaCndcPgtoByIdEncargo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	
    	ServicoSolicitacaoComercialForm servicoSolicitacaoComercialForm = (ServicoSolicitacaoComercialForm)form;
		
		
		ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO = doSolicitacaoComercialTOSearch(servicoSolicitacaoComercialForm);
		servicoSolicitacaoComercialTO = servicoSolicitacaoComercialTODelegate.findCanalVendaCndcPgtoByIdEncargo(servicoSolicitacaoComercialTO);
		this.getServicoSolicitacaoComercialTOCt().setCanalVendaCndcPgtoEncargoTOList(servicoSolicitacaoComercialTO.getCanalVendaCndcPgtoEncargoTOList());
		
		request.setAttribute("servicoSolicitacaoComercialTOCt", this.getServicoSolicitacaoComercialTOCt());
		request.setAttribute("servicoSolicitacaoComercialTOListCt",this.getServicoSolicitacaoComercialTOListCt());
		
		setRqDivFlags(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,request);

		
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward createUpdateDispArConcPlMinByIdCndcPgtoEnc(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	
    	ServicoSolicitacaoComercialForm servicoSolicitacaoComercialForm = (ServicoSolicitacaoComercialForm)form;
		
		
		ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO = doSolicitacaoComercialTO(servicoSolicitacaoComercialForm);
		servicoSolicitacaoComercialTODelegate.createUpdateDispArConcPlMinByIdCndcPgtoEnc(servicoSolicitacaoComercialTO);
		
		request.setAttribute("labelSucess", "Registro configurado com sucesso!");
		
		this.searchCndcPgtoEncargo(mapping,form,request,response);
		
		request.setAttribute("servicoSolicitacaoComercialTOCt", this.getServicoSolicitacaoComercialTOCt());
		
		setRqDivFlags(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE,request);

		
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward switchDisponibilidadeCndcPgtoEncargo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	
    	ServicoSolicitacaoComercialForm servicoSolicitacaoComercialForm = (ServicoSolicitacaoComercialForm)form;
		
		
		ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO = doSolicitacaoComercialTO(servicoSolicitacaoComercialForm);
		servicoSolicitacaoComercialTO = servicoSolicitacaoComercialTODelegate.switchDisponibilidadeCndcPgtoEncargo(servicoSolicitacaoComercialTO);
		
		request.setAttribute("servicoSolicitacaoComercialTOCt", this.getServicoSolicitacaoComercialTOCt());
		request.setAttribute("labelSucess",servicoSolicitacaoComercialTO.getMensagemRetorno());
		
		this.searchCndcPgtoEncargo(mapping,form,request,response);

		
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward createUpdateCndcPgtoEncargo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	
    	ServicoSolicitacaoComercialForm servicoSolicitacaoComercialForm = (ServicoSolicitacaoComercialForm)form;
		
		
		ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO = doSolicitacaoComercialTO(servicoSolicitacaoComercialForm);
		try {
			servicoSolicitacaoComercialTODelegate.createUpdateCndcPgtoEncargo(servicoSolicitacaoComercialTO);
			this.searchEncargoBySolicitacaoComercial(mapping,form,request,response);

			setRqDivFlags(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,request);
			
			if(servicoSolicitacaoComercialForm.getIdCondicaoPagamentoEncargoNew() != null){
				request.setAttribute("labelSucess", "Plano de Financiamento alterado com sucesso.");
			} else {
				request.setAttribute("labelSucess", "Plano de Financiamento associado com sucesso.");
			}
		} catch(BusinessException e){
			request.setAttribute("labelError", e.getMessage());
			setRqDivFlags(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE,request);
		} finally {
			request.setAttribute("servicoSolicitacaoComercialTOCt", this.getServicoSolicitacaoComercialTOCt());
		}
		
		
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward openAssociacaoFinanciamento(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
 
		request.setAttribute("servicoSolicitacaoComercialTOCt", this.getServicoSolicitacaoComercialTOCt());
		request.setAttribute("servicoSolicitacaoComercialTOListCt",this.getServicoSolicitacaoComercialTOListCt());

		setRqDivFlags(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE,request);

		
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward openEditAssociacaoFinanciamento(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	
    	ServicoSolicitacaoComercialForm servicoSolicitacaoComercialForm = (ServicoSolicitacaoComercialForm)form;
		
		
		servicoSolicitacaoComercialForm.setIdCanalVendaNew(servicoSolicitacaoComercialForm.getIdCanalVendaEdit());
		servicoSolicitacaoComercialForm.setIdCondicaoPagamentoNew(servicoSolicitacaoComercialForm.getIdCondicaoPagamentoEdit());
		
		request.setAttribute("servicoSolicitacaoComercialTOCt", this.getServicoSolicitacaoComercialTOCt());
		request.setAttribute("servicoSolicitacaoComercialTOListCt",this.getServicoSolicitacaoComercialTOListCt());

		setRqDivFlags(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE,request);

		
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward openSlctc1Dialog1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){

		request.setAttribute("servicoSolicitacaoComercialTOCt", this.getServicoSolicitacaoComercialTOCt());
		request.setAttribute("servicoSolicitacaoComercialTOListCt",this.getServicoSolicitacaoComercialTOListCt());

		setRqDivFlags(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,request);

		
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward saveSolicitacaoInadimplente(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	
    	ServicoSolicitacaoComercialForm servicoSolicitacaoComercialForm = (ServicoSolicitacaoComercialForm)form;
    	
		String mensagemRetorno = "Dados gravados com Sucesso";
		List<ServicoSolicitacaoComercialTO> servicoSolicitacaoComercialTOListSave = new ArrayList<ServicoSolicitacaoComercialTO>();
		
		for(long idSolicitacaoComercial: servicoSolicitacaoComercialForm.getIdsOfertaClienteInadimplenteSelecionados()){
			servicoSolicitacaoComercialTOListSave.add(new ServicoSolicitacaoComercialTO(
				new Long(idSolicitacaoComercial)
				,servicoSolicitacaoComercialForm.getInOfertaClienteInadimplenteNew()
			));
		}
		
		this.getServicoSolicitacaoComercialTODelegate().saveSolicitacaoOfertaClienteInadimplente(servicoSolicitacaoComercialTOListSave);
		List<ServicoSolicitacaoComercialTO> servicoSolicitacaoComercialTOList = new ArrayList<ServicoSolicitacaoComercialTO>();
		servicoSolicitacaoComercialTOList = servicoSolicitacaoComercialTODelegate.search(doSolicitacaoComercialTOSearch(servicoSolicitacaoComercialForm));
		this.setServicoSolicitacaoComercialTOListCt(servicoSolicitacaoComercialTOList);
		
		request.setAttribute("labelSucess", mensagemRetorno);
		request.setAttribute("servicoSolicitacaoComercialTOCt", this.getServicoSolicitacaoComercialTOCt());
		request.setAttribute("servicoSolicitacaoComercialTOListCt",this.getServicoSolicitacaoComercialTOListCt());

		setRqDivFlags(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, request);
		
		servicoSolicitacaoComercialForm.setIdsOfertaClienteInadimplenteSelecionados(null);
		return mapping.findForward(SUCCESS);
	}

	private ServicoSolicitacaoComercialTO getCombos(ServicoSolicitacaoComercialTO to){
		ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO = servicoSolicitacaoComercialTODelegate.getCombos();
		to.setTipoSolicitacaoComercialTOList(servicoSolicitacaoComercialTO.getTipoSolicitacaoComercialTOList());
		to.setTipoEncargoTOList(servicoSolicitacaoComercialTO.getTipoEncargoTOList());
		to.setCanalVendaFullTOList(servicoSolicitacaoComercialTO.getCanalVendaFullTOList());
		to.setCondicaoPagamentoFullTOList(servicoSolicitacaoComercialTO.getCondicaoPagamentoFullTOList());
		
		return to;
	}

	private ServicoSolicitacaoComercialTO doSolicitacaoComercialTOSearch(ServicoSolicitacaoComercialForm servicoSolicitacaoComercialForm){
		String blank = "";
		ServicoSolicitacaoComercialTO to = new ServicoSolicitacaoComercialTO();
		List<Integer> idSistemaList = new ArrayList<Integer>();
		
		idSistemaList.add(new Integer(8));
		idSistemaList.add(new Integer(9));
		to.setIdSistemaList(idSistemaList);
		
		to.setIdSistema(servicoSolicitacaoComercialForm.getIdSistema());
		
		to.setInDisponivel(servicoSolicitacaoComercialForm.getInDisponivel() == null || servicoSolicitacaoComercialForm.getInDisponivel().equals(blank) ? null : servicoSolicitacaoComercialForm.getInDisponivel());
		to.setInOfertaClienteInadimplente(servicoSolicitacaoComercialForm.getInOfertaClienteInadimplente() == null || servicoSolicitacaoComercialForm.getInOfertaClienteInadimplente().equals(blank) ? null : servicoSolicitacaoComercialForm.getInOfertaClienteInadimplente());
		to.setIdServico(servicoSolicitacaoComercialForm.getIdServico());
		
		to.setIdSolicitacaoComercial(servicoSolicitacaoComercialForm.getIdSolicitacaoComercial());
		
		to.setPoliticaDispSlctComercialTO(servicoSolicitacaoComercialForm.getIdPoliticaDispSlctComercial() != null && servicoSolicitacaoComercialForm.getIdPoliticaDispSlctComercial() != 0 ? new PoliticaDispSlctComercialTO(servicoSolicitacaoComercialForm.getIdPoliticaDispSlctComercial()) : null);
		to.setPzMaximoVigencia(servicoSolicitacaoComercialForm.getPzMaximoVigencia() !=null && !servicoSolicitacaoComercialForm.getPzMaximoVigencia().equals(blank) ? Long.valueOf(servicoSolicitacaoComercialForm.getPzMaximoVigencia()) : null);
		to.setPzMaximoAtendimento(servicoSolicitacaoComercialForm.getPzMaximoAtendimento() !=null && !servicoSolicitacaoComercialForm.getPzMaximoAtendimento().equals(blank) ? Long.valueOf(servicoSolicitacaoComercialForm.getPzMaximoAtendimento()) : null);
		to.setCdSolicitacaoComercial(servicoSolicitacaoComercialForm.getCdSolicitacaoComercial() != null && !servicoSolicitacaoComercialForm.getCdSolicitacaoComercial().equals(blank) ? servicoSolicitacaoComercialForm.getCdSolicitacaoComercial() : null);
		to.setNmSolicitacaoComercial(servicoSolicitacaoComercialForm.getNmSolicitacaoComercial() != null && !servicoSolicitacaoComercialForm.getNmSolicitacaoComercial().equals(blank) ? servicoSolicitacaoComercialForm.getNmSolicitacaoComercial() : null);
		to.setIdTipoSolicitacaoComercial(servicoSolicitacaoComercialForm.getIdTipoSolicitacaoComercial() != null && servicoSolicitacaoComercialForm.getIdTipoSolicitacaoComercial() != 0 ? servicoSolicitacaoComercialForm.getIdTipoSolicitacaoComercial() : null);
		
		to.setIdCanalVendaSolicitacaoComercial(servicoSolicitacaoComercialForm.getIdCanalVendaSolicitacaoComercial());
		if (!StringUtils.isBlank(servicoSolicitacaoComercialForm.getDsEncargo())) {
		    to.setDsEncargo(servicoSolicitacaoComercialForm.getDsEncargo());
        }
		to.setNmPacote(servicoSolicitacaoComercialForm.getNmPacote() != null && !servicoSolicitacaoComercialForm.getNmPacote().equals(blank) ? servicoSolicitacaoComercialForm.getNmPacote() : null);
		to.setPzGratuidade(servicoSolicitacaoComercialForm.getPzGratuidade() !=null  && !servicoSolicitacaoComercialForm.getPzGratuidade().equals(blank)? Long.valueOf(servicoSolicitacaoComercialForm.getPzGratuidade()) : null);
		to.setIdTipoEncargo(servicoSolicitacaoComercialForm.getIdTipoEncargo() != null && servicoSolicitacaoComercialForm.getIdTipoEncargo() != 0 ? servicoSolicitacaoComercialForm.getIdTipoEncargo() : null);
		to.setIdEncargo(servicoSolicitacaoComercialForm.getIdEncargo());
		to.setIdCanalVendaEncargo(servicoSolicitacaoComercialForm.getIdCanalVendaEncargo() != null && servicoSolicitacaoComercialForm.getIdCanalVendaEncargo() != 0 ? servicoSolicitacaoComercialForm.getIdCanalVendaEncargo() : null);
		to.setIdCondicaoPagamentoEncargo(servicoSolicitacaoComercialForm.getIdCondicaoPagamentoEncargo() != null && servicoSolicitacaoComercialForm.getIdCondicaoPagamentoEncargo() != 0 ? servicoSolicitacaoComercialForm.getIdCondicaoPagamentoEncargo() : null);

		return to;
	}
	
	
	private ServicoSolicitacaoComercialTO doSolicitacaoComercialTO(ServicoSolicitacaoComercialForm servicoSolicitacaoComercialForm){
		Boolean match;

		String blank = "";
		ServicoSolicitacaoComercialTO to = new ServicoSolicitacaoComercialTO();
		List<Integer> idSistemaList = new ArrayList<Integer>();
		
		idSistemaList.add(new Integer(8));
		idSistemaList.add(new Integer(9));
		to.setIdSistemaList(idSistemaList);
		
		to.setIdServico(servicoSolicitacaoComercialForm.getIdServico());
		to.setIdSistema(servicoSolicitacaoComercialForm.getIdSistema());
		to.setIdSolicitacaoComercial(servicoSolicitacaoComercialForm.getIdSolicitacaoComercial());
		to.setPoliticaDispSlctComercialTO(servicoSolicitacaoComercialForm.getIdPoliticaDispSlctComercial() != null && servicoSolicitacaoComercialForm.getIdPoliticaDispSlctComercial() != 0 ? new PoliticaDispSlctComercialTO(servicoSolicitacaoComercialForm.getIdPoliticaDispSlctComercial()) : null);
		to.setPzMaximoVigencia(servicoSolicitacaoComercialForm.getPzMaximoVigencia().equals(blank)? 0: Long.valueOf(servicoSolicitacaoComercialForm.getPzMaximoVigencia()));
		to.setPzMaximoAtendimento(servicoSolicitacaoComercialForm.getPzMaximoAtendimento().equals(blank)? 0: Long.valueOf(servicoSolicitacaoComercialForm.getPzMaximoAtendimento()));
		to.setCdSolicitacaoComercial(servicoSolicitacaoComercialForm.getCdSolicitacaoComercial() != null && !servicoSolicitacaoComercialForm.getCdSolicitacaoComercial().equals(blank) ? servicoSolicitacaoComercialForm.getCdSolicitacaoComercial() : null);
		to.setNmSolicitacaoComercial(servicoSolicitacaoComercialForm.getNmSolicitacaoComercial() != null && !servicoSolicitacaoComercialForm.getNmSolicitacaoComercial().equals(blank) ? servicoSolicitacaoComercialForm.getNmSolicitacaoComercial() : null);
		to.setIdTipoSolicitacaoComercial(servicoSolicitacaoComercialForm.getIdTipoSolicitacaoComercial() != null && servicoSolicitacaoComercialForm.getIdTipoSolicitacaoComercial() != 0 ? servicoSolicitacaoComercialForm.getIdTipoSolicitacaoComercial() : null);
		
		to.setInDisponivel(servicoSolicitacaoComercialForm.getInDisponivelNew() != null ? servicoSolicitacaoComercialForm.getInDisponivelNew() : servicoSolicitacaoComercialForm.getInDisponivelNew());
		to.setIdCondicaoPagamentoEncargo(servicoSolicitacaoComercialForm.getIdCondicaoPagamentoEncargo());
		to.setIdCanalVendaSolicitacaoComercial(servicoSolicitacaoComercialForm.getIdCanalVendaSolicitacaoComercial());
		
		if(servicoSolicitacaoComercialForm.getIdCondicaoPagamentoEncargoNew() != null){
			to.setCondicaoPagamentoEncargoTO(new CondicaoPagamentoEncargoTO(
					servicoSolicitacaoComercialForm.getIdCondicaoPagamentoEncargoNew()
					,new BigDecimal(0)
					,new Date()
					,new CanalVendaTO(servicoSolicitacaoComercialForm.getIdCanalVendaNew())
					,new CondicaoPagamentoTO(servicoSolicitacaoComercialForm.getIdCondicaoPagamentoNew())
					,servicoSolicitacaoComercialForm.getIdEncargo()
					,"S"
			));
		} else {
			to.setCondicaoPagamentoEncargoTO(new CondicaoPagamentoEncargoTO(
					new BigDecimal(0)
					,new Date()
					,new CanalVendaTO(servicoSolicitacaoComercialForm.getIdCanalVendaNew())
					,new CondicaoPagamentoTO(servicoSolicitacaoComercialForm.getIdCondicaoPagamentoNew())
					,servicoSolicitacaoComercialForm.getIdEncargo()
					,"S"
			));
		}
		
		if(servicoSolicitacaoComercialForm.getIdAreaConcorrenciaSlctSelecionadoList() != null){
			List<AreaConcorrenciaTO> areaConcorrenciaSlctSelecionadaTOList = new ArrayList<AreaConcorrenciaTO>();
			for(Long id : servicoSolicitacaoComercialForm.getIdAreaConcorrenciaSlctSelecionadoList()){
				areaConcorrenciaSlctSelecionadaTOList.add(new AreaConcorrenciaTO(id));
			}
			to.setAreaConcorrenciaSlctSelecionadaTOList(areaConcorrenciaSlctSelecionadaTOList);
		} else {
			to.setAreaConcorrenciaSlctSelecionadaTOList(new ArrayList<AreaConcorrenciaTO>());
		}

		if(servicoSolicitacaoComercialForm.getIdPlanoDeMinutosSlctSelecionadoList() != null){
			List<EspServicoPlanoMinutoTO> espServicoPlanoMinutoSlctSelecionadoTOList = new ArrayList<EspServicoPlanoMinutoTO>();
			for(Long id : servicoSolicitacaoComercialForm.getIdPlanoDeMinutosSlctSelecionadoList()){
				espServicoPlanoMinutoSlctSelecionadoTOList.add(new EspServicoPlanoMinutoTO(id));
			}
			to.setEspServicoPlanoMinutoSlctSelecionadoTOList(espServicoPlanoMinutoSlctSelecionadoTOList);
		} else {
			to.setEspServicoPlanoMinutoSlctSelecionadoTOList(new ArrayList<EspServicoPlanoMinutoTO>());
		}
		
		if(servicoSolicitacaoComercialForm.getIdAreaConcorrenciaCndcSelecionadoList() != null){
			List<AreaConcorrenciaTO> areaConcorrenciaCndcSelecionadaTOList = new ArrayList<AreaConcorrenciaTO>();
			for(Long id : servicoSolicitacaoComercialForm.getIdAreaConcorrenciaCndcSelecionadoList()){
				areaConcorrenciaCndcSelecionadaTOList.add(new AreaConcorrenciaTO(id));
			}
			to.setAreaConcorrenciaCndcSelecionadaTOList(areaConcorrenciaCndcSelecionadaTOList);
		} else {
			to.setAreaConcorrenciaCndcSelecionadaTOList(new ArrayList<AreaConcorrenciaTO>());
		}

		if(servicoSolicitacaoComercialForm.getIdPlanoDeMinutosCndcSelecionadoList() != null){
			List<EspServicoPlanoMinutoTO> espServicoPlanoMinutoCndcSelecionadoTOList = new ArrayList<EspServicoPlanoMinutoTO>();
			for(Long id : servicoSolicitacaoComercialForm.getIdPlanoDeMinutosCndcSelecionadoList()){
				espServicoPlanoMinutoCndcSelecionadoTOList.add(new EspServicoPlanoMinutoTO(id));
			}
			to.setEspServicoPlanoMinutoCndcSelecionadoTOList(espServicoPlanoMinutoCndcSelecionadoTOList);
		} else {
			to.setEspServicoPlanoMinutoCndcSelecionadoTOList(new ArrayList<EspServicoPlanoMinutoTO>());
		}

		if(servicoSolicitacaoComercialForm.getIdCanalVendaSelecionadoList()!= null){
			List<CanalVendaSolicitacaoComercialTO> canalVendaSolicitacaoComercialSelecTOList = new ArrayList<CanalVendaSolicitacaoComercialTO>();
			CanalVendaSolicitacaoComercialTO canalVendaSolicitacaoComercialSelecTO = null;
			for(Long id : servicoSolicitacaoComercialForm.getIdCanalVendaSelecionadoList()){
				match = Boolean.FALSE;
				for(CanalVendaSolicitacaoComercialTO cscTO : this.getServicoSolicitacaoComercialTOCt().getCanalVendaSolicitacaoComercialTOList()){
					if(cscTO.getCanalVendaTO().getIdCanalVenda().equals(id)) {
						match = Boolean.TRUE;
						canalVendaSolicitacaoComercialSelecTO = cscTO;
					}
				}
				if(match){
					canalVendaSolicitacaoComercialSelecTOList.add(canalVendaSolicitacaoComercialSelecTO);
				} else {
					canalVendaSolicitacaoComercialSelecTOList.add(new CanalVendaSolicitacaoComercialTO(
							new CanalVendaTO(id), servicoSolicitacaoComercialForm.getIdSolicitacaoComercial(), "S"
					));
				}
			}
			to.setCanalVendaSolicitacaoComercialTOList(canalVendaSolicitacaoComercialSelecTOList);
		}
		return to;
	}
	
	private void setRqDivFlags(Boolean slct, Boolean slctc_1,Boolean slctc_1_dialog1, Boolean slctc_1_dialog2, Boolean slcte_1, Boolean slcte_2, Boolean slcte_3, Boolean slcte_4_1, Boolean slcte_4_2, Boolean slcte_4_2_dialog1,HttpServletRequest request){
		request.setAttribute("slct", slct);
		request.setAttribute("slctc_1", slctc_1);
		request.setAttribute("slctc_1_dialog1", slctc_1_dialog1);
		request.setAttribute("slctc_1_dialog2", slctc_1_dialog2);
		request.setAttribute("slcte_1", slcte_1);
		request.setAttribute("slcte_2", slcte_2);
		request.setAttribute("slcte_3", slcte_3);
		request.setAttribute("slcte_4_1", slcte_4_1);
		request.setAttribute("slcte_4_2", slcte_4_2);
		request.setAttribute("slcte_4_2_dialog1", slcte_4_2_dialog1);
	}


	public ServicoSolicitacaoComercialTO getServicoSolicitacaoComercialTOCt() {
		return servicoSolicitacaoComercialTOCt;
	}

	public void setServicoSolicitacaoComercialTOCt(
			ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTOCt) {
		this.servicoSolicitacaoComercialTOCt = servicoSolicitacaoComercialTOCt;
	}

	public ServicoSolicitacaoComercialDelegate getServicoSolicitacaoComercialTODelegate() {
		return servicoSolicitacaoComercialTODelegate;
	}

	public void setServicoSolicitacaoComercialTODelegate(
			ServicoSolicitacaoComercialDelegate servicoSolicitacaoComercialTODelegate) {
		this.servicoSolicitacaoComercialTODelegate = servicoSolicitacaoComercialTODelegate;
	}

	public List<ServicoSolicitacaoComercialTO> getServicoSolicitacaoComercialTOListCt() {
		return servicoSolicitacaoComercialTOListCt;
	}

	public void setServicoSolicitacaoComercialTOListCt(
			List<ServicoSolicitacaoComercialTO> servicoSolicitacaoComercialTOListCt) {
		this.servicoSolicitacaoComercialTOListCt = servicoSolicitacaoComercialTOListCt;
	}

}
