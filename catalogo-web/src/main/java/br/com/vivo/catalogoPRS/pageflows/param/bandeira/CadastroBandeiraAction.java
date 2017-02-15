package br.com.vivo.catalogoPRS.pageflows.param.bandeira;

import java.util.Date;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.indracompany.catalogo.to.BandeiraTO;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.vivo.catalogoPRS.bo.UserCatalogo;
import br.com.vivo.catalogoPRS.delegate.BandeiraDelegate;
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.BandeiraForm;
import br.com.vivo.catalogoPRS.util.ImagemUtil;

public class CadastroBandeiraAction extends BaseMappingDispatchAction {
	
	private TreeMap<Integer, BandeiraTO> list = new TreeMap<Integer, BandeiraTO>();
	
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		BandeiraForm formulario = (BandeiraForm) form;
		search(mapping, formulario, request, response);
		return mapping.findForward("success");
		
	}
	
	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		BandeiraForm formulario = (BandeiraForm) form;
		BandeiraDelegate bandeiraDelegate = new BandeiraDelegate();
		request.setAttribute("bandeiraList", bandeiraDelegate.searchBandeira(doBandeiraTOSearch(formulario)));
		return mapping.findForward("success");
		}
	
	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		BandeiraForm formulario = (BandeiraForm)form;
		request.setAttribute("cadastrar", Boolean.TRUE); 
		search(mapping,formulario, request, response);
		
		resetForm(formulario);
		return mapping.findForward("success");
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		BandeiraForm formulario = (BandeiraForm)form;
		request.setAttribute("cadastrar", Boolean.TRUE); 
		search(mapping, formulario, request, response);
		
		if (formulario.getIdBandeira() != null) {
			doForm(list.get(formulario.getIdBandeira()), formulario);
			return mapping.findForward("success");
		}
		
		return null;
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		BandeiraForm formulario = (BandeiraForm)form;
		try {
			
			if (formulario.getImgBandeira() != null && formulario.getImgBandeira().getFileName() != null && !formulario.getImgBandeira().getFileName().equals("")) {
				String newFileName = ImagemUtil.salvarImagem(formulario.getImgBandeira());
				formulario.setUrlImagem(newFileName);
			}
			
			BandeiraDelegate bandeiraDelegate = new BandeiraDelegate();
			bandeiraDelegate.createUpdateBandeira(doBandeiraTO(formulario,request));
			
			if (formulario.getIdBandeira() != null && formulario.getIdBandeira() > 0) {
				request.setAttribute("labelSucess", "Bandeira Alterada com sucesso.");
			} else {
				request.setAttribute("labelSucess", "Bandeira criada com sucesso.");
			}
		} catch (BusinessException e) {
			edit(mapping,formulario, request, response);
			request.setAttribute("labelError", e.getMessage());
		} catch (CatalogoPRSException e) {
			edit(mapping,formulario, request, response);
			request.setAttribute("labelError", e.getMessage());
		}
		
		search(mapping, formulario, request, response);
		
		return mapping.findForward("success");
	}
	
	public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		BandeiraForm formulario = (BandeiraForm)form;
		try {
			
			BandeiraDelegate bandeiraDelegate = new BandeiraDelegate();
			bandeiraDelegate.removeBandeira(doBandeiraTO(formulario, request));
			request.setAttribute("labelSucess", "Bandeira exclu&iacute;da com sucesso.");
			
		} catch (BusinessException e) {
			request.setAttribute("labelError", e.getMessage());
		}
		
		search(mapping, formulario, request, response);
		
		return mapping.findForward("success");
	}
	
	public BandeiraTO doBandeiraTOSearch(BandeiraForm bandeiraForm) {
		
		BandeiraTO bandeiraTO = new BandeiraTO();
		bandeiraTO.setNmBandeira(bandeiraForm.getNmBandeiraSearch());
		bandeiraTO.setCdBandeiraSAP(bandeiraForm.getSgBandeiraSAPSearch());
		
		return bandeiraTO;
	}
	
	public BandeiraTO doBandeiraTO(BandeiraForm bandeiraForm, HttpServletRequest request) {
		
		BandeiraTO bandeiraTO = new BandeiraTO();
		bandeiraTO.setIdBandeira(bandeiraForm.getIdBandeira());
		bandeiraTO.setNmBandeira(bandeiraForm.getNmBandeira());
		bandeiraTO.setUrlImagem(bandeiraForm.getUrlImagem());
		bandeiraTO.setCdBandeiraSAP(bandeiraForm.getSgBandeiraSAP());
		if (bandeiraForm.getValorMinimo() != null && !bandeiraForm.getValorMinimo().equals("")) {
			bandeiraTO.setVlParcelaMinima(Float.parseFloat(bandeiraForm.getValorMinimo().replace(".", "").replace(",", ".")));
		} else {
			bandeiraTO.setVlParcelaMinima(0f);
		}
		bandeiraTO.setCdInstituicaoFinanceira(bandeiraForm.getCdInstituicaoFinanceira());
		bandeiraTO.setDtCriacao(new Date());
		
		UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
		
		bandeiraTO.setNmUsuarioCriacao(userCatalogo.getUser().getUsername());
		
		if (bandeiraForm.getIdBandeira() != null) {
			bandeiraTO.setDtUltimaAlteracao(new Date());
			bandeiraTO.setNmUsuarioUltimaAlteracao(userCatalogo.getUser().getUsername());
		}
		
		return bandeiraTO;
	}
	
	public BandeiraForm doForm(BandeiraTO bandeiraTO, BandeiraForm bandeiraForm) {
		
		bandeiraForm.setIdBandeira(bandeiraTO.getIdBandeira());
		bandeiraForm.setNmBandeira(bandeiraTO.getNmBandeira());
		bandeiraForm.setSgBandeiraSAP(bandeiraTO.getCdBandeiraSAP());
		if (bandeiraTO.getVlParcelaMinima() != null) {
			bandeiraForm.setValorMinimo(bandeiraTO.getVlParcelaMinima().toString().replaceAll(".", ","));
		}
		bandeiraForm.setCdInstituicaoFinanceira(bandeiraTO.getCdInstituicaoFinanceira());
		
		return bandeiraForm;
	}
	
	public BandeiraForm resetForm(BandeiraForm bandeiraForm) {
		
		bandeiraForm.setIdBandeira(null);
		bandeiraForm.setNmBandeira(null);
		bandeiraForm.setImgBandeira(null);
		bandeiraForm.setSgBandeiraSAP(null);
		bandeiraForm.setValorMinimo(null);
		bandeiraForm.setCdInstituicaoFinanceira(null);
		
		return bandeiraForm;
	}
}

	