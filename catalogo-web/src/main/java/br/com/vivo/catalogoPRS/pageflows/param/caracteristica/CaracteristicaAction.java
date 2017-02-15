package br.com.vivo.catalogoPRS.pageflows.param.caracteristica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.vivo.catalogoPRS.bo.UserCatalogo;
import br.com.vivo.catalogoPRS.delegate.CaracteristicaDelegate;
import br.com.vivo.catalogoPRS.delegate.GrupoCaracteristicaDelegate;
import br.com.vivo.catalogoPRS.delegate.GrupoProdutoDelegate;
import br.com.vivo.catalogoPRS.delegate.ValorCaracteristicaDelegate;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.CaracteristicaForm;

import com.indracompany.catalogo.to.CaracteristicaTO;
import com.indracompany.catalogo.to.GrupoCaracteristicaTO;
import com.indracompany.catalogo.to.ValorCaracteristicaTO;

public class CaracteristicaAction extends BaseMappingDispatchAction implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<CaracteristicaTO> caracteristicaList;
	
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		carregarCombo(request);
		
		this.cleanHeader(response);
		return mapping.findForward("success");
	}
	
	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		CaracteristicaForm caracteristicaForm = (CaracteristicaForm) form;
		
		if ((caracteristicaForm.getNmCaracteristicaSearch() != null 
				&& !caracteristicaForm.getNmCaracteristicaSearch().equals(""))
				|| caracteristicaForm.getIndComparavelSearch() != null
				|| caracteristicaForm.getIndExibivelSearch() != null
				|| caracteristicaForm.getInDisponivelSearch() != null
				|| caracteristicaForm.getInSimuladorSearch() != null
				|| caracteristicaForm.getIdGrupoCaracteristicaSearch() != null
				) {
			CaracteristicaDelegate caracteristicaDelegate = new CaracteristicaDelegate();			
			caracteristicaList = caracteristicaDelegate.searchCaracteristica(doCaracteristicaTOSearch(caracteristicaForm));
			
			request.setAttribute("caracteristicaList", caracteristicaList);
		}
		carregarCombo(request);
		
		this.cleanHeader(response);
		return mapping.findForward("success");
	}
	
	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		CaracteristicaForm caracteristicaForm = (CaracteristicaForm) form;

		request.setAttribute("cadastrar", Boolean.TRUE);
		search(mapping, caracteristicaForm, request, response);
		
		resetForm(caracteristicaForm);
		
		this.cleanHeader(response);
		return mapping.findForward("success");
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		CaracteristicaForm caracteristicaForm = (CaracteristicaForm) form;

		request.setAttribute("cadastrar", Boolean.TRUE);
		search(mapping, caracteristicaForm, request, response);
		
		if(caracteristicaForm.getIdCaracteristica() != null){
			CaracteristicaDelegate caracteristicaDelegate = new CaracteristicaDelegate();
			doForm(caracteristicaDelegate.findById(doCaracteristicaTO(caracteristicaForm)), caracteristicaForm);
		}
		
		this.cleanHeader(response);
		return mapping.findForward("success");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		CaracteristicaForm caracteristicaForm = (CaracteristicaForm) form;
		
		UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
		
		try {
			
			CaracteristicaDelegate caracteristicaDelegate = new CaracteristicaDelegate();
			CaracteristicaTO caracteristicaTO = doCaracteristicaTO(caracteristicaForm);
			String mensagemRetorno = null;
			
			if (caracteristicaTO.getIdCaracteristica() == null || caracteristicaTO.getIdCaracteristica() == 0) {
				caracteristicaTO.setIdCaracteristica(null);
				caracteristicaTO.setDtCriacao(new Date());
				caracteristicaTO.setNmUsuarioCriacao(userCatalogo.getUser().getUsername());
				mensagemRetorno = "Caracter&iacute;stica cadastrada com sucesso";
			} else {
				caracteristicaTO.setDtUltimaAlteracao(new Date());
				caracteristicaTO.setNmUsuarioAlteracao(userCatalogo.getUser().getUsername());
				mensagemRetorno = "Altera&ccedil;&atilde;o realizada com sucesso";
			}
			
			caracteristicaDelegate.createUpdateCaracteristica(caracteristicaTO);
			request.setAttribute("labelSucess", mensagemRetorno);
			search(mapping, caracteristicaForm, request, response);
		} catch (BusinessException e) {
			edit(mapping, caracteristicaForm, request, response);
			request.setAttribute("labelError", e.getMessage());
		}
		
		this.cleanHeader(response);
		return mapping.findForward("success");
	}
	
	public ActionForward saveCaracteristicaByValores(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		CaracteristicaForm caracteristicaForm = (CaracteristicaForm) form;
		
		UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
		
		try {
			
			CaracteristicaDelegate caracteristicaDelegate = new CaracteristicaDelegate();
			CaracteristicaTO caracteristicaTO = doCaracteristicaTO(caracteristicaForm);
			String mensagemRetorno = null;
			
			if (caracteristicaTO.getIdCaracteristica() == null || caracteristicaTO.getIdCaracteristica() == 0) {
				caracteristicaTO.setIdCaracteristica(null);
				caracteristicaTO.setDtCriacao(new Date());
				caracteristicaTO.setNmUsuarioCriacao(userCatalogo.getUser().getUsername());
				mensagemRetorno = "Caracter&iacute;stica cadastrada com sucesso";
			} 
			
			edit(mapping, doForm(caracteristicaDelegate.createUpdateCaracteristica(caracteristicaTO), caracteristicaForm), request, response);
			request.setAttribute("labelSucess", mensagemRetorno);
			request.setAttribute("abrirPopUp", true);
		} catch (BusinessException e) {
			edit(mapping, caracteristicaForm, request, response);
			request.setAttribute("labelError", e.getMessage());
		}
		
		this.cleanHeader(response);
		return mapping.findForward("success");
	}

	
	public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		CaracteristicaForm caracteristicaForm = (CaracteristicaForm) form;
		
		try {
			
			CaracteristicaDelegate caracteristicaDelegate = new CaracteristicaDelegate();
			caracteristicaDelegate.removeCaracteristica(doCaracteristicaTO(caracteristicaForm));
		
		} catch (BusinessException e) {
			request.setAttribute("labelError", e.getMessage());
		}
		
		search(mapping, caracteristicaForm, request, response);
		
		this.cleanHeader(response);
		return mapping.findForward("success");
	}
	
	public ActionForward findValores(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		CaracteristicaForm caracteristicaForm = (CaracteristicaForm) form;
		
		ValorCaracteristicaDelegate valorCaracteristicaDelegate = new ValorCaracteristicaDelegate();
		ValorCaracteristicaTO valorCaracteristicaTO = new ValorCaracteristicaTO();
		valorCaracteristicaTO.setIdValorCaracteristica(caracteristicaForm.getIdCaracteristica());
		
		List<ValorCaracteristicaTO> valorCaracteristicaList = new ArrayList<ValorCaracteristicaTO>();
		
		request.setAttribute("valorCaracteristicaList", valorCaracteristicaDelegate.findByCaracteristica(valorCaracteristicaTO));
		
		valorCaracteristicaList = valorCaracteristicaDelegate.findByCaracteristica(valorCaracteristicaTO);
		caracteristicaForm.setValorCaracteristicaList(valorCaracteristicaList);
		
		this.cleanHeader(response);
		return mapping.findForward("success");
	}
	
	public ActionForward abrirValores(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		CaracteristicaForm caracteristicaForm = (CaracteristicaForm) form;
		
		List<ValorCaracteristicaTO> valorCaracteristicaList = new ArrayList<ValorCaracteristicaTO>();
		
		if (caracteristicaForm.getIdCaracteristica() != null) {
			ValorCaracteristicaDelegate valorCaracteristicaDelegate = new ValorCaracteristicaDelegate();
			ValorCaracteristicaTO valorCaracteristicaTO = new ValorCaracteristicaTO();
			valorCaracteristicaTO.setIdValorCaracteristica(caracteristicaForm.getIdCaracteristica());
			
			valorCaracteristicaList = valorCaracteristicaDelegate.findByCaracteristica(valorCaracteristicaTO);
			caracteristicaForm.setValorCaracteristicaList(valorCaracteristicaList);
			
			request.setAttribute("valorCaracteristicaList", valorCaracteristicaDelegate.findByCaracteristica(valorCaracteristicaTO));
		}
		
		this.cleanHeader(response);
		return mapping.findForward("success");
	}

	
	public ActionForward saveValores(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		CaracteristicaForm caracteristicaForm = (CaracteristicaForm) form;
		
		ValorCaracteristicaDelegate valorCaracteristicaDelegate = new ValorCaracteristicaDelegate();
		ValorCaracteristicaTO valorCaracteristicaTO = new ValorCaracteristicaTO();
		valorCaracteristicaTO.setIdValorCaracteristica(caracteristicaForm.getIdValorCaracteristica());
		if (caracteristicaForm.getValor() != null) {
			valorCaracteristicaTO.setValor(caracteristicaForm.getValor().toUpperCase());
		} else {
			valorCaracteristicaTO.setValor(caracteristicaForm.getValor());
		}
		valorCaracteristicaTO.setCaracteristicaTO(new CaracteristicaTO(caracteristicaForm.getIdCaracteristica()));
		
		try {
			valorCaracteristicaDelegate.createUpdateValorCaracteristica(valorCaracteristicaTO);
			caracteristicaForm.setValor(null);
			caracteristicaForm.setIdValorCaracteristica(null);
		} catch (BusinessException e) {
			request.setAttribute("labelError", e.getMessage());
		}
		
		abrirValores(mapping, caracteristicaForm, request, response);
		
		this.cleanHeader(response);
		return mapping.findForward("success");
	}
	
	public ActionForward loadValor(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		CaracteristicaForm caracteristicaForm = (CaracteristicaForm) form;
		
		ValorCaracteristicaDelegate valorCaracteristicaDelegate = new ValorCaracteristicaDelegate();
		doFormValorCaracteristica(valorCaracteristicaDelegate.find(new ValorCaracteristicaTO(caracteristicaForm.getIdValorCaracteristica())), caracteristicaForm);
		
		request.setAttribute("alterar", Boolean.TRUE);
		
		abrirValores(mapping, caracteristicaForm, request, response);
		
		this.cleanHeader(response);
		return mapping.findForward("success");
	}
	
	
	public ActionForward removeValores(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		CaracteristicaForm caracteristicaForm = (CaracteristicaForm) form;
		
		ValorCaracteristicaDelegate valorCaracteristicaDelegate = new ValorCaracteristicaDelegate();
		ValorCaracteristicaTO valorCaracteristicaTO = new ValorCaracteristicaTO();
		valorCaracteristicaTO.setIdValorCaracteristica(caracteristicaForm.getIdValorCaracteristica());
		valorCaracteristicaDelegate.deleteValorCaracteristica(valorCaracteristicaTO);
		
		abrirValores(mapping, caracteristicaForm, request, response);
		
		this.cleanHeader(response);
		return mapping.findForward("success");
	}
	
	public ActionForward verificarAssociacaoExclusao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		CaracteristicaForm caracteristicaForm = (CaracteristicaForm) form;
		
		GrupoProdutoDelegate grupoProdutoDelegate = new GrupoProdutoDelegate();
		
		request.setAttribute("grupoProdutoList", grupoProdutoDelegate.findByCaracteristica(caracteristicaForm.getIdCaracteristica()));
		
		this.cleanHeader(response);
		return mapping.findForward("success");
	}

	public ActionForward verificarAssociacaoAlteracao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		CaracteristicaForm caracteristicaForm = (CaracteristicaForm) form;
		
		GrupoProdutoDelegate grupoProdutoDelegate = new GrupoProdutoDelegate();
		
		request.setAttribute("grupoProdutoList", grupoProdutoDelegate.findByCaracteristica(caracteristicaForm.getIdCaracteristica()));
		
		this.cleanHeader(response);
		return mapping.findForward("success");
	}

	public ActionForward verificarAssociacaoExclusaoValorCaracteristica(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		CaracteristicaForm caracteristicaForm = (CaracteristicaForm) form;
		
		GrupoProdutoDelegate grupoProdutoDelegate = new GrupoProdutoDelegate();
		
		request.setAttribute("grupoProdutoList", grupoProdutoDelegate.findByValorCaracteristica(caracteristicaForm.getIdValorCaracteristica()));
		
		this.cleanHeader(response);
		return mapping.findForward("success");
	}

	public ActionForward verificarAssociacaoAlteracaoValorCaracteristica(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		CaracteristicaForm caracteristicaForm = (CaracteristicaForm) form;
		
		GrupoProdutoDelegate grupoProdutoDelegate = new GrupoProdutoDelegate();
		
		request.setAttribute("grupoProdutoList", grupoProdutoDelegate.findByValorCaracteristica(caracteristicaForm.getIdValorCaracteristica()));
		
		this.cleanHeader(response);
		return mapping.findForward("success");
	}

	
	public CaracteristicaTO doCaracteristicaTOSearch(CaracteristicaForm caracteristicaForm) {
		CaracteristicaTO caracteristicaTO = new CaracteristicaTO();
		
		if (caracteristicaForm != null) {
			caracteristicaTO.setNmCaracteristica(caracteristicaForm.getNmCaracteristicaSearch());
			caracteristicaTO.setIdCaracteristica(caracteristicaForm.getIdCaracteristica());
			
			if (caracteristicaForm.getIdGrupoCaracteristicaSearch() != null) {
				caracteristicaTO.setGrupoCaracteristicaTO(new GrupoCaracteristicaTO(caracteristicaForm.getIdGrupoCaracteristicaSearch()));
			}
			caracteristicaTO.setIndComparavel(caracteristicaForm.getIndComparavelSearch());
			caracteristicaTO.setIndExibivel(caracteristicaForm.getIndExibivelSearch());
			caracteristicaTO.setInDisponivel(caracteristicaForm.getInDisponivelSearch());
			caracteristicaTO.setInSimulador(caracteristicaForm.getInSimuladorSearch());
			
		}
		
		return caracteristicaTO;
	}
	
	public CaracteristicaTO doCaracteristicaTO(CaracteristicaForm caracteristicaForm) {
		CaracteristicaTO caracteristicaTO = new CaracteristicaTO();
		
		if (caracteristicaForm.getDescricaoCadastro() != null) {
			caracteristicaTO.setDescricao(caracteristicaForm.getDescricaoCadastro().toUpperCase());
		} else {
			caracteristicaTO.setDescricao(caracteristicaForm.getDescricaoCadastro());
		}
		caracteristicaTO.setIdCaracteristica(caracteristicaForm.getIdCaracteristica());
		caracteristicaTO.setIndComparavel(caracteristicaForm.getIndComparavelCadastro());
		caracteristicaTO.setIndExibivel(caracteristicaForm.getIndExibivelCadastro());
		caracteristicaTO.setInDisponivel(caracteristicaForm.getInDisponivelCadastro());
		caracteristicaTO.setInSimulador(caracteristicaForm.getInSimuladorCadastro());
		
		if (caracteristicaForm.getNmCaracteristicaCadastro() != null) {
			caracteristicaTO.setNmCaracteristica(caracteristicaForm.getNmCaracteristicaCadastro().toUpperCase());
		} else {
			caracteristicaTO.setNmCaracteristica(caracteristicaForm.getNmCaracteristicaCadastro());
		}
		if (caracteristicaForm.getIdGrupoCaracteristicaCadastro() != null) {
			caracteristicaTO.setGrupoCaracteristicaTO(new GrupoCaracteristicaTO(caracteristicaForm.getIdGrupoCaracteristicaCadastro()));
		}
		
		return caracteristicaTO;
	}

	public CaracteristicaForm doForm(CaracteristicaTO caracteristicaTO, CaracteristicaForm caracteristicaForm) {
		
		if (caracteristicaTO != null && caracteristicaForm != null) {
			caracteristicaForm.setDescricaoCadastro(caracteristicaTO.getDescricao());
			caracteristicaForm.setIdCaracteristica(caracteristicaTO.getIdCaracteristica());
			caracteristicaForm.setNmCaracteristicaCadastro(caracteristicaTO.getNmCaracteristica());
			
			if (caracteristicaTO.getGrupoCaracteristicaTO() != null) {
				caracteristicaForm.setIdGrupoCaracteristicaCadastro(caracteristicaTO.getGrupoCaracteristicaTO().getIdGrupoCaracteristica());
			}
			
			caracteristicaForm.setIndComparavelCadastro(caracteristicaTO.getIndComparavel());
			caracteristicaForm.setIndExibivelCadastro(caracteristicaTO.getIndExibivel());
			caracteristicaForm.setInDisponivelCadastro(caracteristicaTO.getInDisponivel());
			caracteristicaForm.setInSimuladorCadastro(caracteristicaTO.getInSimulador());
			caracteristicaForm.setExistGrupoProdutoCaracteristica(caracteristicaTO.getExistGrupoProdutoCaracteristica());
		}
		
		return caracteristicaForm;
	}
	
	public CaracteristicaForm resetForm(CaracteristicaForm caracteristicaForm) {
		if (caracteristicaForm != null) {
			caracteristicaForm.setDescricaoCadastro(null);
			caracteristicaForm.setIdCaracteristica(null);
			caracteristicaForm.setIdGrupoCaracteristicaCadastro(null);
			caracteristicaForm.setNmCaracteristicaCadastro(null);
			caracteristicaForm.setIndComparavelCadastro(null);
			caracteristicaForm.setIndExibivelCadastro(null);
			caracteristicaForm.setInDisponivelCadastro(null);
			caracteristicaForm.setInSimuladorCadastro(null);
			caracteristicaForm.setExistGrupoProdutoCaracteristica(null);
		}
		return caracteristicaForm;
	}
	
	private void doFormValorCaracteristica(ValorCaracteristicaTO valorCaracteristicaTO, CaracteristicaForm caracteristicaForm) {
		caracteristicaForm.setIdValorCaracteristica(valorCaracteristicaTO.getIdValorCaracteristica());
		caracteristicaForm.setValor(valorCaracteristicaTO.getValor());
		caracteristicaForm.setExistAssociacao((valorCaracteristicaTO.getExistGrupoProdutoCaracteristica() ? "S" : "N"));
	}
	
	private void carregarCombo(HttpServletRequest request) {
		
		GrupoCaracteristicaDelegate grupoCaracteristicaDelegate = new GrupoCaracteristicaDelegate();
		
		request.setAttribute("grupoCaracteristicaList", grupoCaracteristicaDelegate.findAll());

	}
	
	public List<CaracteristicaTO> getCaracteristicaList() {
		return caracteristicaList;
	}

	public void setCaracteristicaList(List<CaracteristicaTO> caracteristicaList) {
		this.caracteristicaList = caracteristicaList;
	}
	
}
	