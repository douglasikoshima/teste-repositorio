package br.com.vivo.catalogoPRS.pageflows.param.familiacategoria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.catalogoPRS.bo.UserCatalogo;
import br.com.vivo.catalogoPRS.delegate.CategoriaDelegate;
import br.com.vivo.catalogoPRS.delegate.FamiliaDelegate;
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.FamiliaCategoriaForm;

import com.indracompany.catalogo.to.CategoriaTO;
import com.indracompany.catalogo.to.FamiliaTO;

public class FamiliaCategoriaAction extends BaseMappingDispatchAction implements Serializable {

    private static final long serialVersionUID = 4164795337971652595L;
    private static final String SUCCESS = "success";
    private static final String editVisibility = "editVisibility";
    
    private List<FamiliaTO> familiaTOList;
    private List<FamiliaTO> familiaCategoriaTOList;
    private List<CategoriaTO> categoriaTOList;
    private FamiliaTO familiaTO;
    
    public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) {
        this.categoriaTOList = null;
        this.familiaTOList = null; 
        this.preencherComboPesquisa(null,request);
        return mapping.findForward(SUCCESS);
    }
    
    private void preencherComboPesquisa(FamiliaCategoriaForm familiaCategoriaForm, HttpServletRequest request) {
        try {
            request.setAttribute(editVisibility,"none");
            if (this.familiaCategoriaTOList == null) {
                FamiliaTO familiaTOSearch = new FamiliaCategoriaForm().buildFamiliaTOSearch();
                familiaTOSearch.setInDisponivel(null);
                this.familiaCategoriaTOList = new FamiliaDelegate().search(familiaTOSearch);
            }
            request.setAttribute("familiaCategoriaTOList", this.familiaCategoriaTOList);
    
            if (familiaCategoriaForm != null) {
            request.setAttribute("tipoPesquisa", familiaCategoriaForm.getTipoPesquisa());
            }
        } catch (CatalogoPRSException e) {
            request.setAttribute("erro", e.getMessage());
        }
    }

    public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) {
   
    	FamiliaCategoriaForm familiaCategoriaForm = (FamiliaCategoriaForm)form;
    	
    	this.familiaTO = null;
        request.setAttribute("familiaTO", this.familiaTO);
        preencherComboPesquisa(familiaCategoriaForm,request);
            if (familiaCategoriaForm.getTipoPesquisa().equalsIgnoreCase("familia")) {
                this.categoriaTOList = null;
                try {
                    this.familiaTOList = new FamiliaDelegate().search(familiaCategoriaForm.buildFamiliaTOSearch());
                } catch (CatalogoPRSException e) {
                    this.familiaTOList = new ArrayList<FamiliaTO>();
                }
                request.setAttribute("familiaTOList", this.familiaTOList);
            } else {
                this.familiaTOList = null;
                try {
                this.categoriaTOList = new CategoriaDelegate().search(familiaCategoriaForm.buildCategoriaTOSearch());
                } catch (CatalogoPRSException e) {
                    this.categoriaTOList = new ArrayList<CategoriaTO>();
                }
                request.setAttribute("categoriaTOList", this.categoriaTOList);
            }
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward findCategorias(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) {
   
    	FamiliaCategoriaForm familiaCategoriaForm = (FamiliaCategoriaForm)form;
    	
        this.familiaTO = new FamiliaDelegate().findById(Integer.valueOf(familiaCategoriaForm.getIdFamilia()));
        request.setAttribute("familiaTO", this.familiaTO);
        request.setAttribute("familiaTOList", this.familiaTOList);
        preencherComboPesquisa(familiaCategoriaForm,request);
        familiaCategoriaForm.setIdFamilia(null);
        return mapping.findForward(SUCCESS);
    }

    public ActionForward reccordFamilia(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) {
   
    	FamiliaCategoriaForm familiaCategoriaForm = (FamiliaCategoriaForm)form;
  
        preencherComboPesquisa(familiaCategoriaForm,request);
        try {
        	FamiliaTO to = new FamiliaDelegate().insertUpdate(familiaCategoriaForm.buildFamiliaTOInsertChange());
        	if (!StringUtils.isBlank(familiaCategoriaForm.getIdEditado())) {
        		request.setAttribute("labelSucess", "Fam&iacute;lia alterada com sucesso");
        		return this.search(mapping,form,request,response);
        	} else {
        		request.setAttribute("labelSucess", String.format("Fam&iacute;lia %s criada com sucesso", to.getCdFamilia()));
        		familiaCategoriaForm.clean();
        		familiaCategoriaForm.setCodigoFamilia(to.getCdFamilia());
        		this.familiaTOList = new ArrayList<FamiliaTO>();
        		this.familiaTOList.add(to);
        		request.setAttribute("familiaTOList", this.familiaTOList);
        	}
        } catch (CatalogoPRSException e) {
            request.setAttribute("erro", e.getMessage());
            if (!StringUtils.isBlank(familiaCategoriaForm.getIdEditado())) {
                return this.search(mapping,form,request,response);
            }
            request.setAttribute(editVisibility, "block");
        }
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward reccordCategoria(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) {
   
    	FamiliaCategoriaForm familiaCategoriaForm = (FamiliaCategoriaForm)form;

        preencherComboPesquisa(familiaCategoriaForm,request);
        UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
        familiaCategoriaForm.setUsuario(userCatalogo.getUser().getUsername());
        try {
	        CategoriaTO to = new CategoriaDelegate().insertUpdate(familiaCategoriaForm.buildCategoriaTOInsertChange());
	        if (!StringUtils.isBlank(familiaCategoriaForm.getIdEditado())) {
	            request.setAttribute("labelSucess", "Categoria alterada com sucesso");
	        } else {
	            request.setAttribute("labelSucess", String.format("Categoria %s criada com sucesso", to.getIdCategoria()));
	        }
	        
	        if (!StringUtils.isBlank(familiaCategoriaForm.getIdFamilia())) {
	            return this.findCategorias(mapping,form,request,response);
	        } else if (!StringUtils.isBlank(familiaCategoriaForm.getIdEditado())) {
	            return this.search(mapping,form,request,response);
	        } else {
	            familiaCategoriaForm.clean();
	            familiaCategoriaForm.setCodigoCategoria(to.getIdCategoria().toString());
	            this.categoriaTOList = new ArrayList<CategoriaTO>();
	            this.categoriaTOList.add(to);
	            request.setAttribute("familiaTOList", this.familiaTOList);
	        }
	    } catch (CatalogoPRSException e) {
	        request.setAttribute("erro", e.getMessage());
            if (!StringUtils.isBlank(familiaCategoriaForm.getIdFamilia())) {
                return this.findCategorias(mapping,form,request,response);
            } else if (!StringUtils.isBlank(familiaCategoriaForm.getIdEditado())) {
                return this.search(mapping,form,request,response);
            }
            request.setAttribute(editVisibility, "block");
	    }
        return mapping.findForward(SUCCESS);
    }
    
    public ActionForward removeFamilia(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) {
   
    	FamiliaCategoriaForm familiaCategoriaForm = (FamiliaCategoriaForm)form;
    	
    	preencherComboPesquisa(familiaCategoriaForm,request);
        try {
            new FamiliaDelegate().remove(Integer.valueOf(familiaCategoriaForm.getIdEditado()));
        } catch (CatalogoPRSException e) {
            request.setAttribute("erro", e.getMessage());
        }
        return this.search(mapping,form,request,response);
    }
    
    public ActionForward removeCategoria(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) {
   
    	FamiliaCategoriaForm familiaCategoriaForm = (FamiliaCategoriaForm)form;
    	
    	preencherComboPesquisa(familiaCategoriaForm,request);
        try {
            new CategoriaDelegate().remove(Integer.valueOf(familiaCategoriaForm.getIdEditado()));
        } catch (CatalogoPRSException e) {
            request.setAttribute("erro", e.getMessage());
        }
        return this.search(mapping,form,request,response);
    }
    
    public ActionForward changeStatusFamilia(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) {
   
    	FamiliaCategoriaForm familiaCategoriaForm = (FamiliaCategoriaForm)form;
    	
    	preencherComboPesquisa(familiaCategoriaForm,request);
        new FamiliaDelegate().changeStatus(Integer.valueOf(familiaCategoriaForm.getIdEditado()));
        if (familiaCategoriaForm.getStatusAtual().equalsIgnoreCase("ativo")) {
            request.setAttribute("labelSucess", "Configura&ccedil;&atilde;o Desativada com sucesso!");
        } else {
            request.setAttribute("labelSucess", "Configura&ccedil;&atilde;o Ativada com sucesso!");
        }
        return this.search(mapping,form,request,response);
    }
    
    public ActionForward changeStatusCategoria(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) {
   
    	FamiliaCategoriaForm familiaCategoriaForm = (FamiliaCategoriaForm)form;
    	
    	preencherComboPesquisa(familiaCategoriaForm,request);
        new CategoriaDelegate().changeStatus(Integer.valueOf(familiaCategoriaForm.getIdEditado()));
        if (familiaCategoriaForm.getStatusAtual().equalsIgnoreCase("ativo")) {
            request.setAttribute("labelSucess", "Configura&ccedil;&atilde;o Desativada com sucesso!");
        } else {
            request.setAttribute("labelSucess", "Configura&ccedil;&atilde;o Ativada com sucesso!");
        }
        if (this.familiaTO == null) {
        	return this.search(mapping,form,request,response);
        } else {
        	familiaCategoriaForm.setIdFamilia(String.format("%s", this.familiaTO.getIdFamilia()));
        	return this.findCategorias(mapping,form,request,response);
        }
    }    

    public void setCategoriaTOList(List<CategoriaTO> categoriaTOList) {
        this.categoriaTOList = categoriaTOList;
    }
    
    public List<CategoriaTO> getCategoriaTOList() {
        return this.categoriaTOList;
    }
    
    public void setFamiliaTOList(List<FamiliaTO> familiaTOList) {
        this.familiaTOList = familiaTOList;
    }
    
    public List<FamiliaTO> getFamiliaTOList() {
        return this.familiaTOList;
    }
    
    public void setFamiliaCategoriaTOList(List<FamiliaTO> familiaCategoriaTOList) {
        this.familiaCategoriaTOList = familiaCategoriaTOList;
    }
    
    public List<FamiliaTO> getFamiliaCategoriaTOList() {
        return this.familiaCategoriaTOList;
    }
    
    public void setFamiliaTO(FamiliaTO familiaTO) {
        this.familiaTO = familiaTO;
    }
    
    public FamiliaTO getFamiliaTO() {
        return this.familiaTO;
    }
}
