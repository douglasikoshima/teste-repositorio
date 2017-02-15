package br.com.vivo.catalogoPRS.pageflows.param.acaoproduto;

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
import br.com.vivo.catalogoPRS.delegate.AcaoDelegate;
import br.com.vivo.catalogoPRS.delegate.FabricanteDelegate;
import br.com.vivo.catalogoPRS.delegate.GrupoProdutoDelegate;
import br.com.vivo.catalogoPRS.delegate.ProdutoDelegate;
import br.com.vivo.catalogoPRS.delegate.TipoLinhaDelegate;
import br.com.vivo.catalogoPRS.delegate.TipoProdutoDelegate;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.AssociacaoAcaoForm;

import com.indracompany.catalogo.to.AcaoTO;
import com.indracompany.catalogo.to.FabricanteTO;
import com.indracompany.catalogo.to.GrupoProdutoTO;
import com.indracompany.catalogo.to.ProdutoGrupoProdutoTO;
import com.indracompany.catalogo.to.ProdutoTO;
import com.indracompany.catalogo.to.SistemaProdutoTO;
import com.indracompany.catalogo.to.TipoLinhaTO;
import com.indracompany.catalogo.to.TipoProdutoTO;

public class AssociacaoAcaoProdutoAction extends BaseMappingDispatchAction {
	
	private static final String SUCCESS = "success";

	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,	HttpServletResponse response) throws Exception {
		search(mapping, form, request, response);
		this.cleanHeader(response);
		return mapping.findForward("success");
	}
	
	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception  {
		
		AssociacaoAcaoForm formulario = (AssociacaoAcaoForm)form;
		
		AcaoDelegate acaoDelegate = new AcaoDelegate();
		request.setAttribute("acaoList", acaoDelegate.searchAcao(doAcaoTOSearch(formulario)));
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward searchProduto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception  {
		
		AssociacaoAcaoForm formulario = (AssociacaoAcaoForm)form;
		
		ProdutoDelegate produtoDelegate = new ProdutoDelegate();
		request.setAttribute("produtoList", produtoDelegate.searchSemAssociacaoAcao(doProdutoTOSearch(formulario), formulario.getIdAcao()));
		
		edit(mapping, form, request, response );
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception  {

		AssociacaoAcaoForm formulario = (AssociacaoAcaoForm)form;
		
		carregarCombos(formulario, request, response);
		
		search(mapping, form, request, response );
		
		AcaoDelegate acaoDelegate = new AcaoDelegate();
		request.setAttribute("nmAcaoVenda", acaoDelegate.findById(new AcaoTO(formulario.getIdAcao())).getNmAcao());
		
		ProdutoDelegate produtoDelegate = new ProdutoDelegate();
		request.setAttribute("produtosAdicionadosList", produtoDelegate.searchComAssociacaoAcao( formulario.getIdAcao()));
		
		request.setAttribute("cadastrar", Boolean.TRUE);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward adicionar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AssociacaoAcaoForm formulario = (AssociacaoAcaoForm)form;
		
		try {
			
			AcaoDelegate acaoDelegate = new AcaoDelegate();
			acaoDelegate.createAssociacaoAcaoProduto(doAcaoTO(formulario, request), formulario.getIdProdutosCheck());
			
		} catch (BusinessException e) {
			request.setAttribute("labelError", e.getMessage());
		}
		
		searchProduto(mapping, form, request, response);
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AssociacaoAcaoForm formulario = (AssociacaoAcaoForm)form;
		
		try {
			
			AcaoDelegate acaoDelegate = new AcaoDelegate();
			acaoDelegate.removeAssociacaoAcaoProduto(doAcaoTO(formulario, request), formulario.getIdProdutosAdicionadosCheck());
		
		} catch (BusinessException e) {
			request.setAttribute("labelError", e.getMessage());
		}
		
		searchProduto(mapping, form, request, response);
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward carregar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception  {
		
		edit(mapping, form, request, response );
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	public void carregarCombos(AssociacaoAcaoForm acaoForm,  HttpServletRequest request, HttpServletResponse response) {
		
		TipoLinhaDelegate tipoLinhaDelegate = new TipoLinhaDelegate();
		request.setAttribute("tipoLinhaList", tipoLinhaDelegate.findAll());
		
		List<FabricanteTO> fabricanteList = new ArrayList<FabricanteTO>();
		
		if (acaoForm.getIdTipoProdutoSearch() != null) {	
			FabricanteDelegate fabricanteDelegate = new FabricanteDelegate();
			FabricanteTO fabricanteTO = new FabricanteTO();
			fabricanteTO.setIdTipoProduto(acaoForm.getIdTipoProdutoSearch());
			fabricanteList = fabricanteDelegate.search(fabricanteTO);
		}
		
		request.setAttribute("fabricanteList", fabricanteList);
		
		TipoProdutoDelegate tipoProdutoDelegate = new TipoProdutoDelegate();
		request.setAttribute("tipoProdutoList", tipoProdutoDelegate.findAllButSimCards());
		
		List<GrupoProdutoTO> grupoProdutoList = new ArrayList<GrupoProdutoTO>();
		
		if (acaoForm.getIdFabricanteSearch() != null) {
			GrupoProdutoDelegate grupoProdutoDelegate = new GrupoProdutoDelegate();
			GrupoProdutoTO grupoProdutoTO = new GrupoProdutoTO();
			grupoProdutoTO.setFabricanteTO(new FabricanteTO(acaoForm.getIdFabricanteSearch()));
			grupoProdutoTO.setTipoProdutoTO(new TipoProdutoTO(acaoForm.getIdTipoProdutoSearch()));		
			grupoProdutoTO.setInDisponivel("S");
			grupoProdutoList = grupoProdutoDelegate.search(grupoProdutoTO);
			request.setAttribute("grupoProdutoList", grupoProdutoList);
		}
		this.cleanHeader(response);
		request.setAttribute("grupoProdutoList", grupoProdutoList);
	}
	
	/**
	 * @param acaoForm
	 * @return
	 * 
	 * Método responsável em criar um TO a partir de um Form para pesquisa.
	 */
	public AcaoTO doAcaoTOSearch(AssociacaoAcaoForm acaoForm) {
		AcaoTO acaoTO = new AcaoTO();
		acaoTO.setIsVigente("S");
		
		return acaoTO;
	}
	
	/**
	 * @param acaoForm
	 * @return
	 * 
	 * Método responsável em criar um TO a partir de um Form.
	 */
	public AcaoTO doAcaoTO(AssociacaoAcaoForm acaoForm, HttpServletRequest request) {
		
		UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
		AcaoTO acaoTO = new AcaoTO();
		acaoTO.setIdAcao(acaoForm.getIdAcao());
		acaoTO.setNmUsuarioCriacao(userCatalogo.getUser().getUsername());
		acaoTO.setDtCriacao(new Date());
		
		return acaoTO;
	}
	
	public ProdutoTO doProdutoTOSearch(AssociacaoAcaoForm acaoForm) {
		ProdutoTO produtoTO = new ProdutoTO();
		
		TipoProdutoTO tipoProdutoTO = new TipoProdutoTO();
		tipoProdutoTO.setTipoLinhaTO(new TipoLinhaTO(acaoForm.getIdTipoLinhaSearch()));
		tipoProdutoTO.setIdTipoProduto(acaoForm.getIdTipoProdutoSearch());
		
		produtoTO.setTipoProdutoTO(tipoProdutoTO);
		produtoTO.setFabricanteTO(new FabricanteTO(acaoForm.getIdFabricanteSearch()));
		produtoTO.setSistemaProdutoTO(new SistemaProdutoTO(acaoForm.getCdProdutoSearch()));
		
		ProdutoGrupoProdutoTO produtoGrupoProdutoTO = new ProdutoGrupoProdutoTO();
		produtoGrupoProdutoTO.setGrupoProdutoTO(new GrupoProdutoTO(acaoForm.getIdModeloSearch()));
		produtoTO.setProdutoGrupoProdutoTO(produtoGrupoProdutoTO);
		
		return produtoTO;
	}
	
	/**
	 * @param acaoTO
	 * @return
	 * 
	 * Método responsável em criar um Form a partir de um TO.
	 */
	public AssociacaoAcaoForm doForm(AcaoTO acaoTO, AssociacaoAcaoForm acaoForm) {
		acaoForm.setIdAcao(acaoTO.getIdAcao());
		
		return acaoForm;
	}
	
	/**
	 * @param acaoForm
	 * @return
	 * 
	 * Método responsável em resetar o form.
	 */
	public AssociacaoAcaoForm resetForm(AssociacaoAcaoForm acaoForm) {
		
		return acaoForm;
	}
}

	