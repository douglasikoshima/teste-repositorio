package br.com.vivo.catalogoPRS.pageflows.param.detalheservicofixa;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.vivo.catalogoPRS.delegate.CategoriaDelegate;
import br.com.vivo.catalogoPRS.delegate.DetalheServicoFixaDelegate;
import br.com.vivo.catalogoPRS.delegate.FamiliaDelegate;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.ServicoFixaForm;

import com.indracompany.catalogo.to.CategoriaTO;
import com.indracompany.catalogo.to.DetalheServicoFixaTO;
import com.indracompany.catalogo.to.FamiliaTO;

public class DetalheServicoFixaAction extends BaseMappingDispatchAction implements Serializable{
    private static final long serialVersionUID = -8827667946362404482L;
    private static final String SUCCESS = "success";
    
    public ActionForward listaCategoriaServico(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	ServicoFixaForm servicoFixaForm = (ServicoFixaForm)form;
    	
        request.setAttribute("categoriaTOList", new CategoriaDelegate().search(new CategoriaTO(new FamiliaTO(Integer.valueOf(servicoFixaForm.getIdFamiliaForm())))));
		this.cleanHeader(response);
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	ServicoFixaForm servicoFixaForm = (ServicoFixaForm)form;
    	
    	DetalheServicoFixaDelegate detalheServicoFixaDelegate = new DetalheServicoFixaDelegate();
        if (request.getSession().getAttribute("idServico") != null) {
            request.setAttribute("idServicoSearch", request.getSession().getAttribute("idServico"));
            request.setAttribute("alterarServicoSearch", request.getSession().getAttribute("alterarServico"));
            servicoFixaForm.setIdServicoSearch(request.getSession().getAttribute("idServico").toString());
            servicoFixaForm.setAlterarServicoSearch((String) request.getSession().getAttribute("alterarServico"));
        }

        servicoFixaForm = doServicoFixaForm(servicoFixaForm, detalheServicoFixaDelegate.findDetalheServicoFixaById(doDetalheServicoFixaTO(servicoFixaForm)));
        this.preencherCombosFamiliaCategoria(servicoFixaForm,request);        
    	request.setAttribute("atributoTOList", servicoFixaForm.getAtributoTOList());
    	request.setAttribute("atributoPoliticaPrecificacaoTOList", servicoFixaForm.getAtributoPoliticaPrecificacaoTOList());
    	this.cleanHeader(response);    	
        return mapping.findForward(SUCCESS);
    }

    public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	DetalheServicoFixaDelegate detalheServicoFixaDelegate = new DetalheServicoFixaDelegate();
    	ServicoFixaForm servicoFixaForm = (ServicoFixaForm)form;
    	
    	servicoFixaForm = doServicoFixaForm(servicoFixaForm, detalheServicoFixaDelegate.findDetalheServicoFixaById(doDetalheServicoFixaTO(servicoFixaForm)));
    	this.preencherCombosFamiliaCategoria(servicoFixaForm,request);
    	request.setAttribute("atributoTOList", servicoFixaForm.getAtributoTOList());
    	request.setAttribute("atributoPoliticaPrecificacaoTOList", servicoFixaForm.getAtributoPoliticaPrecificacaoTOList());
    	this.cleanHeader(response);
        return mapping.findForward(SUCCESS);
    }

    private void preencherCombosFamiliaCategoria(ServicoFixaForm form,HttpServletRequest request) {
        request.setAttribute("familiaTOList", new FamiliaDelegate().search(new FamiliaTO()));
        request.setAttribute("categoriaTOList", new CategoriaDelegate().search(new CategoriaTO(new FamiliaTO(Integer.valueOf(form.getIdFamiliaForm())))));
    }

    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
    	
    	DetalheServicoFixaDelegate detalheServicoFixaDelegate = new DetalheServicoFixaDelegate();
    	ServicoFixaForm servicoFixaForm = (ServicoFixaForm)form;
    	
    	try {
    	    DetalheServicoFixaTO detalheServicoFixaTO = doDetalheServicoFixaTO(servicoFixaForm);
    		detalheServicoFixaDelegate.updateDetalheServicoFixaTO(detalheServicoFixaTO);
    		servicoFixaForm = doServicoFixaForm(servicoFixaForm, detalheServicoFixaDelegate.findDetalheServicoFixaById(doDetalheServicoFixaTO(servicoFixaForm)));
        	request.setAttribute("atributoTOList", servicoFixaForm.getAtributoTOList());
        	request.setAttribute("atributoPoliticaPrecificacaoTOList", servicoFixaForm.getAtributoPoliticaPrecificacaoTOList());
            this.preencherCombosFamiliaCategoria(servicoFixaForm,request);
        	request.setAttribute("labelSucess", String.format("Servi&ccedil;o alterado com sucesso", ""));
    	} catch(BusinessException e){
    		request.setAttribute("labelError", e.getMessage());
    	}

    	this.cleanHeader(response);    	
    	return mapping.findForward(SUCCESS);
    }
    
    private DetalheServicoFixaTO doDetalheServicoFixaTO(ServicoFixaForm form) {
    	DetalheServicoFixaTO to = new DetalheServicoFixaTO();
    	String blank = "";
		
    	to.setIdServico(Integer.valueOf(form.getIdServicoSearch()));
    	
		to.setVlDescontoAbsoluto(form.getVlDescontoAbsoluto());
		to.setVlMinutoAdicionalFFLocal(form.getVlMinutoAdicionalFFLocal());
		to.setNuOrdemAtendimento(form.getNuOrdemAtendimento());
		to.setIdServico(Integer.valueOf(form.getIdServicoSearch()));
		to.setQtMinutoLivreFMLocal(form.getQtMinutoLivreFMLocal());
		to.setQtMinutoLivreFFLocal(form.getQtMinutoLivreFFLocal());
		to.setQtPercentualAuditoria(form.getQtPercentualAuditoria());
		to.setInDisponivel(form.getInDisponivelDetalheServico() != null && form.getInDisponivelDetalheServico() ? "S" : "N");
		to.setInPosFactibilidade(form.getInPosFactibilidade() != null && form.getInPosFactibilidade() ? "S" : "N");
		to.setInPreFactibilidade(form.getInPreFactibilidade() != null && form.getInPreFactibilidade() ? "S" : "N");
		to.setInVendaIsolada(form.getInVendaIsolada() != null && form.getInVendaIsolada() ? "S" : "N");
    	to.setCdClasseServicoAdicional(form.getCdClasseServicoAdicional() != null && form.getCdClasseServicoAdicional().equals(blank) ? null : form.getCdClasseServicoAdicional());
    	to.setCdClasseServicoPrincipal(form.getCdClasseServicoPrincipal() != null && form.getCdClasseServicoPrincipal().equals(blank) ? null : form.getCdClasseServicoPrincipal());
    	to.setCdCodigo(form.getCdCodigo() != null && form.getCdCodigo().equals(blank) ? null : form.getCdCodigo());
    	to.setCdPacote(form.getCdPacote() != null && form.getCdPacote().equals(blank) ? null : form.getCdPacote());
    	to.setCdSiscom(form.getCdSiscom() != null && form.getCdSiscom().equals(blank) ? null : form.getCdSiscom());
    	to.setCdTipoEquipamento(form.getCdTipoEquipamento() != null && form.getCdTipoEquipamento().equals(blank) ? null : form.getCdTipoEquipamento());
    	to.setCdTipoPrecoPacote(form.getCdTipoPrecoPacote() != null && form.getCdTipoPrecoPacote().equals(blank) ? null : form.getCdTipoPrecoPacote());
    	to.setCdTipoServico(form.getCdTipoServico() != null && form.getCdTipoServico().equals(blank) ? null : form.getCdTipoServico());
    	to.setDescricaoCatalogo(form.getDescricaoCatalogo() != null && form.getDescricaoCatalogo().equals(blank) ? null : form.getDescricaoCatalogo());
    	to.setDescricaoOrigem(form.getDescricaoOrigem() != null && form.getDescricaoOrigem().equals(blank) ? null : form.getDescricaoOrigem());
    	to.setNmComercialCatalogo(form.getNmComercialCatalogo() != null && form.getNmComercialCatalogo().equals(blank) ? null : form.getNmComercialCatalogo());
    	to.setNmComercialOrigem(form.getNmComercialOrigem() != null && form.getNmComercialOrigem().equals(blank) ? null : form.getNmComercialOrigem());
    	to.setNmSistema(form.getNmSistema() != null && form.getNmSistema().equals(blank) ? null : form.getNmSistema());
    	to.setNmTecnologia(form.getNmTecnologia() != null && form.getNmTecnologia().equals(blank) ? null : form.getNmTecnologia());
    	to.setNmTipoServico(form.getNmTipoServicoDetalheServico() != null && form.getNmTipoServicoDetalheServico().equals(blank) ? null : form.getNmTipoServicoDetalheServico());
    	to.setSgTipoAplicacaoDesconto(form.getSgTipoAplicacaoDesconto() != null && form.getSgTipoAplicacaoDesconto().equals(blank) ? null : form.getSgTipoAplicacaoDesconto());
    	
    	to.setInEspServicoPacote(form.getInEspServicoPacote() != null && form.getInEspServicoPacote() ? "S" : "N");
    	to.setInClasseServicoPrincipal(form.getInClasseServicoPrincipal() != null && form.getInClasseServicoPrincipal() ? "S" : "N");
    	to.setInEmpresaInstaladora(form.getInEmpresaInstaladora() != null && form.getInEmpresaInstaladora() ? "S" : "N");
    	to.setInAreaConcorrenciaDesconto(form.getInAreaConcorrenciaDesconto() != null && form.getInAreaConcorrenciaDesconto() ? "S" : "N");
    	to.setInPlanoMinutoDesconto(form.getInPlanoMinutoDesconto() != null && form.getInPlanoMinutoDesconto() ? "S" : "N");
    	to.setInPossuiVarDispDesconto(form.getInPossuiVarDispDesconto());
    	to.setInAreaConcorrenciaFinanciamento(form.getInAreaConcorrenciaFinanciamento() != null && form.getInAreaConcorrenciaFinanciamento() ? "S" : "N");
    	to.setInPlanoMinutoFinanciamento(form.getInPlanoMinutoFinanciamento() != null && form.getInPlanoMinutoFinanciamento() ? "S" : "N");
    	to.setInPossuiVarDispFinanciamento(form.getInPossuiVarDispFinanciamento());

    	to.setAtributoTOList(form.getAtributoTOList() != null ? form.getAtributoTOList() : null);
    	to.setAtributoPoliticaPrecificacaoTOList(form.getAtributoPoliticaPrecificacaoTOList() != null ? form.getAtributoPoliticaPrecificacaoTOList() : null);
        if (StringUtils.isNumeric(form.getIdCategoriaForm())) {
            to.setIdCategoria(Integer.valueOf(form.getIdCategoriaForm()));
        }
        to.setAlterarPoliticas(form.getAlterarPoliticas().equalsIgnoreCase("S") ? true : false);
    	
    	return to;
    }
    
    private ServicoFixaForm doServicoFixaForm(ServicoFixaForm form, DetalheServicoFixaTO to){
    	form.setIdServicoSearch(String.valueOf(to.getIdServico()));
    	
		form.setVlDescontoAbsoluto(to.getVlDescontoAbsoluto());
		form.setVlMinutoAdicionalFFLocal(to.getVlMinutoAdicionalFFLocal());
		form.setNuOrdemAtendimento(to.getNuOrdemAtendimento());
		form.setIdServicoSearch(String.valueOf(to.getIdServico()));
		form.setQtMinutoLivreFMLocal(to.getQtMinutoLivreFMLocal());
		form.setQtMinutoLivreFFLocal(to.getQtMinutoLivreFFLocal());
		form.setQtPercentualAuditoria(to.getQtPercentualAuditoria());
		form.setInDisponivelDetalheServico(to.getInDisponivel() != null && to.getInDisponivel().equalsIgnoreCase("S") ? Boolean.TRUE : Boolean.FALSE);
		form.setInPosFactibilidade(to.getInPosFactibilidade() != null && to.getInPosFactibilidade().equalsIgnoreCase("S") ? Boolean.TRUE : Boolean.FALSE);
		form.setInPreFactibilidade(to.getInPreFactibilidade() != null && to.getInPreFactibilidade().equalsIgnoreCase("S") ? Boolean.TRUE : Boolean.FALSE);
		form.setInVendaIsolada(to.getInVendaIsolada() != null && to.getInVendaIsolada().equalsIgnoreCase("S") ? Boolean.TRUE : Boolean.FALSE);
    	form.setCdClasseServicoAdicional(to.getCdClasseServicoAdicional());
    	form.setCdClasseServicoPrincipal(to.getCdClasseServicoPrincipal());
    	form.setCdCodigo(to.getCdCodigo());
    	form.setCdPacote(to.getCdPacote());
    	form.setCdSiscom(to.getCdSiscom());
    	form.setCdTipoEquipamento(to.getCdTipoEquipamento());
    	form.setCdTipoPrecoPacote(to.getCdTipoPrecoPacote());
    	form.setCdTipoServico(to.getCdTipoServico());
    	form.setDescricaoCatalogo(to.getDescricaoCatalogo());
    	form.setDescricaoOrigem(to.getDescricaoOrigem());
    	form.setNmComercialCatalogo(to.getNmComercialCatalogo());
    	form.setNmComercialOrigem(to.getNmComercialOrigem());
    	form.setNmSistema(to.getNmSistema());
    	form.setNmTecnologia(to.getNmTecnologia());
    	form.setNmTipoServicoDetalheServico(to.getNmTipoServico());
    	form.setSgTipoAplicacaoDesconto(to.getSgTipoAplicacaoDesconto());
    	
    	form.setInEspServicoPacote(to.getInEspServicoPacote() != null && to.getInEspServicoPacote().equalsIgnoreCase("S") ? Boolean.TRUE : Boolean.FALSE);
    	form.setInClasseServicoPrincipal(to.getInClasseServicoPrincipal() != null && to.getInClasseServicoPrincipal().equalsIgnoreCase("S") ? Boolean.TRUE : Boolean.FALSE);
    	form.setInEmpresaInstaladora(to.getInEmpresaInstaladora() != null && to.getInEmpresaInstaladora().equalsIgnoreCase("S") ? Boolean.TRUE : Boolean.FALSE);
    	form.setInAreaConcorrenciaDesconto(to.getInAreaConcorrenciaDesconto() != null && to.getInAreaConcorrenciaDesconto().equalsIgnoreCase("S") ? Boolean.TRUE : Boolean.FALSE);
    	form.setInPlanoMinutoDesconto(to.getInPlanoMinutoDesconto() != null && to.getInPlanoMinutoDesconto().equalsIgnoreCase("S") ? Boolean.TRUE : Boolean.FALSE);
    	form.setInPossuiVarDispDesconto(to.getInPossuiVarDispDesconto());
    	form.setInAreaConcorrenciaFinanciamento(to.getInAreaConcorrenciaFinanciamento() != null && to.getInAreaConcorrenciaFinanciamento().equalsIgnoreCase("S") ? Boolean.TRUE : Boolean.FALSE);
    	form.setInPlanoMinutoFinanciamento(to.getInPlanoMinutoFinanciamento() != null && to.getInPlanoMinutoFinanciamento().equalsIgnoreCase("S") ? Boolean.TRUE : Boolean.FALSE);
    	form.setInPossuiVarDispFinanciamento(to.getInPossuiVarDispFinanciamento());
        form.setIdFamiliaForm(to.getIdFamilia().toString());
        form.setIdCategoriaForm(to.getIdCategoria().toString());
    	
    	form.setAtributoTOList(to.getAtributoTOList() != null ? to.getAtributoTOList() : null);
    	form.setAtributoPoliticaPrecificacaoTOList(to.getAtributoPoliticaPrecificacaoTOList() != null ? to.getAtributoPoliticaPrecificacaoTOList() : null);
        form.setCdServicoOrigem(to.getCdServicoOrigem());
    	
    	return form;
    }
}
