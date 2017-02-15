package br.com.vivo.catalogoPRS.pageflows.param.categoria;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.indracompany.catalogo.to.CategoriaScoreTO;
import com.indracompany.catalogo.to.ClassificacaoCategoriaScoreTO;

import br.com.vivo.catalogoPRS.bo.UserCatalogo;
import br.com.vivo.catalogoPRS.delegate.CategoriaScoreDelegate;
import br.com.vivo.catalogoPRS.delegate.ClassificacaoCategoriaScoreDelegate;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.CategoriaScoreForm;

public class CategoriaScoreAction extends BaseMappingDispatchAction implements Serializable{
	
	private List<CategoriaScoreTO> categoriaScoreTOList;
	private CategoriaScoreDelegate categoriaScoreDelegate = new CategoriaScoreDelegate();

	private static final long serialVersionUID = 1L;

	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		this.categoriaScoreTOList = categoriaScoreDelegate.findAll();
		request.setAttribute("categoriaScoreTOList", this.categoriaScoreTOList);
		carregarCombos(request);
		this.cleanHeader(response);
		return mapping.findForward("success");
		
	}

	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		CategoriaScoreForm categoriaScoreForm = (CategoriaScoreForm)form;
		
		this.categoriaScoreTOList = categoriaScoreDelegate.searchCategoriaScore(doCategoriaScoreTOSearch(categoriaScoreForm));
		categoriaScoreForm.setCadastrar(false);
		request.setAttribute("categoriaScoreTOList", this.categoriaScoreTOList);
		carregarCombos(request);
		this.cleanHeader(response);
		return mapping.findForward("success");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		
		CategoriaScoreForm categoriaScoreForm = (CategoriaScoreForm) form;
		String mensagemErro = null;
		
		CategoriaScoreTO categoriaScoreTO = doCategoriaScoreTO(categoriaScoreForm,request);
		
		mensagemErro = validarCategoriaScoreTO(categoriaScoreTO);
		
		if(mensagemErro == null){
			categoriaScoreDelegate.createUpdateCategoriaScore(categoriaScoreTO);
			if(categoriaScoreTO.getIdCategoriaScore() == null){
				request.setAttribute("labelSucess","Categoria cadastrada com sucesso.");				
			}else{
				request.setAttribute("labelSucess","Categoria alterada com sucesso.");
			}
			clearForm(categoriaScoreForm);
			this.categoriaScoreTOList = categoriaScoreDelegate.searchCategoriaScore(doCategoriaScoreTOSearch(categoriaScoreForm));
			request.setAttribute("categoriaScoreTOList", this.categoriaScoreTOList);
			categoriaScoreForm.setCadastrar(false);
		} else{
			request.setAttribute("labelError",mensagemErro);
			request.setAttribute("categoriaScoreTOList", this.categoriaScoreTOList);
			categoriaScoreForm.setCadastrar(true);
		}
		
		carregarCombos(request);
		this.cleanHeader(response);
		return mapping.findForward("success");
	}
	
	public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		
		CategoriaScoreForm categoriaScoreForm =  (CategoriaScoreForm) form;
		
		CategoriaScoreTO categoriaScoreTO = doCategoriaScoreTO(categoriaScoreForm,request);
		
		categoriaScoreDelegate.removeCategoriaScore(categoriaScoreTO);
		this.categoriaScoreTOList = categoriaScoreDelegate.searchCategoriaScore(doCategoriaScoreTOSearch(categoriaScoreForm));
		request.setAttribute("categoriaScoreTOList", this.categoriaScoreTOList);
		
		carregarCombos(request);
		this.cleanHeader(response);
		return mapping.findForward("success");
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		
		CategoriaScoreForm categoriaScoreForm = (CategoriaScoreForm)form;
		
		if (categoriaScoreForm.getIdCategoriaScore() != null) {
			categoriaScoreForm = doForm(categoriaScoreDelegate.findById(doCategoriaScoreTO(categoriaScoreForm,request)),categoriaScoreForm);
		}

		categoriaScoreForm.setCadastrar(true);
		this.cleanHeader(response);
		carregarCombos(request);
		
		return mapping.findForward("success");
	}
	
	public ActionForward clearSearch(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		
		CategoriaScoreForm categoriaScoreForm = (CategoriaScoreForm)form;
		
		categoriaScoreForm.setIdClassificacaoCategoriaScoreSearch(null);
		categoriaScoreForm.setNmCategoriaScoreSearch(null);

		request.setAttribute("categoriaScoreTOList", categoriaScoreDelegate.findAll());
		
		carregarCombos(request);
		this.cleanHeader(response);
		return mapping.findForward("success");
	}
	

	public ActionForward clearPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		
		CategoriaScoreForm categoriaScoreForm = (CategoriaScoreForm)form;
		
		carregarCombos(request);
		request.setAttribute("categoriaScoreTOList", categoriaScoreDelegate.findAll());
		clearForm(categoriaScoreForm);
		clearSearch(mapping, form, request, response);
		categoriaScoreForm.setCadastrar(false);
		this.cleanHeader(response);
		return mapping.findForward("success");
	}

	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		
		CategoriaScoreForm categoriaScoreForm = (CategoriaScoreForm)form;
		
		carregarCombos(request);
		categoriaScoreForm.setCadastrar(true);
		
		clearForm(categoriaScoreForm);
		this.cleanHeader(response);
		return mapping.findForward("success");
	}
	
	private String validarCategoriaScoreTO(CategoriaScoreTO categoriaScoreTO){
		
		CategoriaScoreTO categoriaScoreTO2 = new CategoriaScoreTO();
		
		if((categoriaScoreTO.getNmCategoriaScore() == null) 
		|| categoriaScoreTO.getCdCategoriaScore() == null 
		|| categoriaScoreTO.getClassificacaoCategoriaScoreTO().getIdClassificacaoCategoriaScore() == null 
		|| categoriaScoreTO.getClassificacaoCategoriaScoreTO().getIdClassificacaoCategoriaScore() == 0){
			if(categoriaScoreTO.getIdCategoriaScore() == null) 
				return "N\u00e3o foi poss\u00edvel criar a categoria. Todos os campos devem ser preenchidos.";
			else 
				return "N\u00e3o foi poss\u00edvel alterar a categoria. Todos os campos devem ser preenchidos.";
		}
		if(possuiCaracterInvalido(categoriaScoreTO.getCdCategoriaScore()+categoriaScoreTO.getNmCategoriaScore())){
			if(categoriaScoreTO.getIdCategoriaScore() == null)
				return "N\u00e3o foi poss\u00edvel criar a categoria. Caractere inv\u00e1lido.";
			else
				return "N\u00e3o foi poss\u00edvel alterar a categoria. Caractere inv\u00e1lido.";
		}
		
		if(categoriaScoreTO.getIdCategoriaScore() == null){
			if(categoriaScoreDelegate.existCdCategoriaScore(categoriaScoreTO)){
				return "N\u00e3o foi poss\u00edvel criar a categoria. J\u00e1 existe categoria com este C\u00f3digo.";
			}
		}else{
			categoriaScoreTO2 = categoriaScoreDelegate.findByCdCategoria(categoriaScoreTO);
			if(categoriaScoreTO2 != null){
				if(!categoriaScoreTO2.getIdCategoriaScore().equals(categoriaScoreTO.getIdCategoriaScore())){
					return "N\u00e3o foi poss\u00edvel alterar a categoria. J\u00e1 existe categoria com este C\u00f3digo.";
				}
			}
		}
		
		return null;

	}
	
	private CategoriaScoreForm clearForm(CategoriaScoreForm categoriaScoreForm){
		categoriaScoreForm.setCdCategoriaScore(null);
		categoriaScoreForm.setIdCategoriaScore(null);
		categoriaScoreForm.setIdClassificacaoCategoriaScore(null);
		categoriaScoreForm.setNmCategoriaScore(null);
		categoriaScoreForm.setNmClassificacaoCategoriaScore(null);
		
		return categoriaScoreForm;
	}

	private boolean possuiCaracterInvalido(String s){
		int i;
		boolean retorno = false;
		
		for(i = 0; i < s.length(); i++){
			if( !( ( (int)s.charAt(i) >= 65 && (int)s.charAt(i) <= 90 ) 
			|| ( (int)s.charAt(i) >= 97 && (int)s.charAt(i) <= 122 ) 
			|| ( (int)s.charAt(i) >= 48 && (int)s.charAt(i) <= 57 )  
			|| ( (int)s.charAt(i) == 32 ) )
			){
				retorno = true;
			}
		}
		
		return retorno;
	}
	
	private CategoriaScoreForm doForm(CategoriaScoreTO categoriaScoreTO, CategoriaScoreForm categoriaScoreForm){
		
		categoriaScoreForm.setCdCategoriaScore(categoriaScoreTO.getCdCategoriaScore());
		categoriaScoreForm.setIdCategoriaScore(categoriaScoreTO.getIdCategoriaScore());
		categoriaScoreForm.setNmCategoriaScore(categoriaScoreTO.getNmCategoriaScore());
		if(categoriaScoreTO.getClassificacaoCategoriaScoreTO() != null){
			categoriaScoreForm.setIdClassificacaoCategoriaScore(categoriaScoreTO.getClassificacaoCategoriaScoreTO().getIdClassificacaoCategoriaScore());
			categoriaScoreForm.setNmClassificacaoCategoriaScore(categoriaScoreTO.getClassificacaoCategoriaScoreTO().getNmClassificacaoCategoriaScore());
		}
		
		return categoriaScoreForm;
	}
	
	private CategoriaScoreTO doCategoriaScoreTO(CategoriaScoreForm categoriaScoreForm,HttpServletRequest request){
		CategoriaScoreTO categoriaScoreTO = new CategoriaScoreTO();
		ClassificacaoCategoriaScoreTO classificacaoCategoriaScoreTO = new ClassificacaoCategoriaScoreTO();

		categoriaScoreTO.setIdCategoriaScore(categoriaScoreForm.getIdCategoriaScore());
		if(categoriaScoreForm.getCdCategoriaScore() != null){
			if(categoriaScoreForm.getCdCategoriaScore().equals("")){
				categoriaScoreTO.setCdCategoriaScore(null);
			}else{
				categoriaScoreTO.setCdCategoriaScore(categoriaScoreForm.getCdCategoriaScore().trim());	
			}
		}
		if(categoriaScoreForm.getNmCategoriaScore() != null){
			if(categoriaScoreForm.getNmCategoriaScore().equals("")){
				categoriaScoreTO.setNmCategoriaScore(null);
			}else{
				categoriaScoreTO.setNmCategoriaScore(categoriaScoreForm.getNmCategoriaScore().trim());
			}
		}
		categoriaScoreTO.setDtCriacao(new Date());
		UserCatalogo usuarioLogado = (UserCatalogo) request.getSession().getAttribute("logged_user");
		categoriaScoreTO.setNmUsuarioCriacao(usuarioLogado.getUser().getUsername());
		
		classificacaoCategoriaScoreTO.setIdClassificacaoCategoriaScore(categoriaScoreForm.getIdClassificacaoCategoriaScore());
		classificacaoCategoriaScoreTO.setNmClassificacaoCategoriaScore(categoriaScoreForm.getNmClassificacaoCategoriaScore());
		
		categoriaScoreTO.setClassificacaoCategoriaScoreTO(classificacaoCategoriaScoreTO);
		
		return categoriaScoreTO;
	}
	
	private CategoriaScoreTO doCategoriaScoreTOSearch(CategoriaScoreForm categoriaScoreForm){
		CategoriaScoreTO categoriaScoreTO = new CategoriaScoreTO();
		ClassificacaoCategoriaScoreTO classificacaoCategoriaScoreTO = new ClassificacaoCategoriaScoreTO();
		
		categoriaScoreTO.setNmCategoriaScore(categoriaScoreForm.getNmCategoriaScoreSearch());
		if(categoriaScoreForm.getIdClassificacaoCategoriaScoreSearch() != null){
			if(!categoriaScoreForm.getIdClassificacaoCategoriaScoreSearch().equals(new Integer(0))){
				classificacaoCategoriaScoreTO.setIdClassificacaoCategoriaScore(categoriaScoreForm.getIdClassificacaoCategoriaScoreSearch());
			} else {
				classificacaoCategoriaScoreTO.setIdClassificacaoCategoriaScore(null);
			}
		}
		categoriaScoreTO.setClassificacaoCategoriaScoreTO(classificacaoCategoriaScoreTO);
		
		return categoriaScoreTO;
	}
	
	private void carregarCombos(HttpServletRequest request){
		ClassificacaoCategoriaScoreDelegate classificacaoCategoriaScoreDelegate = new ClassificacaoCategoriaScoreDelegate();
		request.setAttribute("classificacaoCategoriaScoreList",classificacaoCategoriaScoreDelegate.findAll()); 
	}

}
