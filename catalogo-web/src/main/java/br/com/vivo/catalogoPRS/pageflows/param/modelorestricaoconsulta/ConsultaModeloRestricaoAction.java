package br.com.vivo.catalogoPRS.pageflows.param.modelorestricaoconsulta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.vivo.catalogoPRS.delegate.AssociacaoModeloRestricaoDelegate;
import br.com.vivo.catalogoPRS.delegate.FabricanteDelegate;
import br.com.vivo.catalogoPRS.delegate.GrupoProdutoDelegate;
import br.com.vivo.catalogoPRS.delegate.TipoProdutoDelegate;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.AssociacaoModeloRestricaoForm;

import com.indracompany.catalogo.to.FabricanteTO;
import com.indracompany.catalogo.to.GrupoProdutoTO;
import com.indracompany.catalogo.to.ModeloTipoProdutoCompativelTO;
import com.indracompany.catalogo.to.TipoProdutoTO;

public class ConsultaModeloRestricaoAction extends BaseMappingDispatchAction {
		
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		AssociacaoModeloRestricaoForm associacaoModeloRestricaoForm = (AssociacaoModeloRestricaoForm)form;
		
		carregarCombos(associacaoModeloRestricaoForm, request, response);
		
		return mapping.findForward("success");
	}
	
	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,	HttpServletResponse response) {
		
		AssociacaoModeloRestricaoForm associacaoModeloRestricaoForm = (AssociacaoModeloRestricaoForm)form;
		GrupoProdutoDelegate grupoProdutoDelegate = new GrupoProdutoDelegate();
		
		request.setAttribute("grupoProdutoResultList", grupoProdutoDelegate.search(doGrupoProdutoTOSearch(associacaoModeloRestricaoForm)));
		carregarCombos(associacaoModeloRestricaoForm, request, response);
		
		return mapping.findForward("success");
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		AssociacaoModeloRestricaoForm associacaoModeloRestricaoForm = (AssociacaoModeloRestricaoForm)form;

		search(mapping, form, request, response);
		
		GrupoProdutoDelegate grupoProdutoDelegate = new GrupoProdutoDelegate();
		doForm(grupoProdutoDelegate.findById(doGrupoProdutoTO(associacaoModeloRestricaoForm)), associacaoModeloRestricaoForm, request, response);
		
		request.setAttribute("cadastrar", Boolean.TRUE);
		return mapping.findForward("success");
	}

	public ActionForward carregar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		AssociacaoModeloRestricaoForm associacaoModeloRestricaoForm = (AssociacaoModeloRestricaoForm)form;
		
		carregarCombos(associacaoModeloRestricaoForm, request, response);
		
		return mapping.findForward("success");
	}

	public ActionForward carregarTipoRestricao(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		AssociacaoModeloRestricaoForm associacaoModeloRestricaoForm = (AssociacaoModeloRestricaoForm)form;
		
		TipoProdutoDelegate tipoProdutoDelegate = new TipoProdutoDelegate();
		
		List<TipoProdutoTO> tipoProdutoSearch = new ArrayList<TipoProdutoTO>();
		
		for (int i = 0; i < associacaoModeloRestricaoForm.getIdTipoRestricaoArray().length; i++) {
			tipoProdutoSearch.add(tipoProdutoDelegate.findById(new TipoProdutoTO(associacaoModeloRestricaoForm.getIdTipoRestricaoArray()[i])));
		}
		
		request.setAttribute("tipoProdutoSearch", tipoProdutoSearch);
		request.setAttribute("idTipoCompativeisSelecionados", associacaoModeloRestricaoForm.getIdTipoCompativeisSelecionados());
		
		search(mapping, form, request, response);
		request.setAttribute("cadastrar", Boolean.TRUE);
		
		return mapping.findForward("success");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		AssociacaoModeloRestricaoForm associacaoModeloRestricaoForm = (AssociacaoModeloRestricaoForm)form;
		Map<Integer, List<ModeloTipoProdutoCompativelTO>> map = new TreeMap<Integer, List<ModeloTipoProdutoCompativelTO>>();
		
		List<ModeloTipoProdutoCompativelTO> list = null;
		
		try {
		
			for (int i = 0; i < associacaoModeloRestricaoForm.getIdTipoRestricaoArray().length; i++) {
				Integer tipoRestricao = associacaoModeloRestricaoForm.getIdTipoRestricaoArray()[i];
				list = new ArrayList<ModeloTipoProdutoCompativelTO>();
				
				String[] tipoCompativeis =  associacaoModeloRestricaoForm.getIdTipoCompativeisSelecionados()[i].split(";");
	
				for (int j = 0; j < tipoCompativeis.length; j++) {
					if (tipoCompativeis[j] != null && !tipoCompativeis[j].equals("")) {
						list.add(new ModeloTipoProdutoCompativelTO(null, null, new TipoProdutoTO(Integer.parseInt(tipoCompativeis[j]))));
					}
				}
				
				map.put(tipoRestricao, list);
			}
			
			AssociacaoModeloRestricaoDelegate associacaoModeloRestricaoDelegate = new AssociacaoModeloRestricaoDelegate();
			associacaoModeloRestricaoDelegate.createUpdateRestricoes(doGrupoProdutoTO(associacaoModeloRestricaoForm), map, this.getUserName());
			
		} catch (BusinessException e) {
			edit(mapping, associacaoModeloRestricaoForm, request, response);
			//getRequest().setAttribute("labelError", e.getMessage());
			request.setAttribute("labelError", e.getMessage());
		}
		
		//getRequest().setAttribute("labelSucess", "Restrições salvas com sucesso!");
		request.setAttribute("labelSucess", "Restri&ccedil;&otilde;es salvas com sucesso!");
		
		search(mapping, form, request, response);
		
		//return new Forward("success");
		return mapping.findForward("success");
	}

	private void carregarCombos(AssociacaoModeloRestricaoForm associacaoModeloRestricaoForm, HttpServletRequest request, HttpServletResponse response) {

		TipoProdutoDelegate tipoProdutoDelegate = new TipoProdutoDelegate();
		request.setAttribute("tipoProdutoList", tipoProdutoDelegate.findAll());
		
		List<FabricanteTO> fabricanteList = new ArrayList<FabricanteTO>();
		
		if (associacaoModeloRestricaoForm.getIdTipoProdutoSearch() != null && associacaoModeloRestricaoForm.getIdTipoProdutoSearch() > 0) {	
			FabricanteDelegate fabricanteDelegate = new FabricanteDelegate();
			FabricanteTO fabricanteTO = new FabricanteTO();
			fabricanteTO.setIdTipoProduto(associacaoModeloRestricaoForm.getIdTipoProdutoSearch());
			fabricanteList = fabricanteDelegate.search(fabricanteTO);
		}
		
		request.setAttribute("fabricanteList", fabricanteList);
		
		List<GrupoProdutoTO> grupoProdutoList = new ArrayList<GrupoProdutoTO>();
		
		if (associacaoModeloRestricaoForm.getIdFabricanteSearch() != null && associacaoModeloRestricaoForm.getIdFabricanteSearch() > 0) {
			GrupoProdutoDelegate grupoProdutoDelegate = new GrupoProdutoDelegate();
			GrupoProdutoTO grupoProdutoTO = new GrupoProdutoTO();
			grupoProdutoTO.setFabricanteTO(new FabricanteTO(associacaoModeloRestricaoForm.getIdFabricanteSearch()));
			grupoProdutoTO.setTipoProdutoTO(new TipoProdutoTO(associacaoModeloRestricaoForm.getIdTipoProdutoSearch()));			
			
			grupoProdutoList = grupoProdutoDelegate.search(grupoProdutoTO);
			request.setAttribute("grupoProdutoList", grupoProdutoList);
		}
		
		request.setAttribute("grupoProdutoList", grupoProdutoList);
	}
	
	public GrupoProdutoTO doGrupoProdutoTOSearch(AssociacaoModeloRestricaoForm associacaoModeloRestricaoForm) {
		
		GrupoProdutoTO grupoProdutoTO = new GrupoProdutoTO();
		
		grupoProdutoTO.setFabricanteTO(new FabricanteTO(associacaoModeloRestricaoForm.getIdFabricanteSearch()));
		grupoProdutoTO.setTipoProdutoTO(new TipoProdutoTO(associacaoModeloRestricaoForm.getIdTipoProdutoSearch()));
		if (associacaoModeloRestricaoForm.getIdModeloSearch() > 0) {
			grupoProdutoTO.setIdGrupoProduto(associacaoModeloRestricaoForm.getIdModeloSearch());
		} else {
			grupoProdutoTO.setIdGrupoProduto(null);			
		}
		
		return grupoProdutoTO;
	}
	
	public GrupoProdutoTO doGrupoProdutoTO(AssociacaoModeloRestricaoForm associacaoModeloRestricaoForm) {
		
		GrupoProdutoTO grupoProdutoTO = new GrupoProdutoTO();
		grupoProdutoTO.setIdGrupoProduto(associacaoModeloRestricaoForm.getIdGrupoProduto());
		
		return grupoProdutoTO;
	}

	public AssociacaoModeloRestricaoForm doForm(GrupoProdutoTO grupoProdutoTO, AssociacaoModeloRestricaoForm associacaoModeloRestricaoForm, HttpServletRequest request,	HttpServletResponse response) {
		
		associacaoModeloRestricaoForm.setIdGrupoProduto(grupoProdutoTO.getIdGrupoProduto());
		request.setAttribute("nmModelo", grupoProdutoTO.getNmGrupoProduto());
		
		if (grupoProdutoTO.getModeloTipoProdutoRestricaoTOList() != null && !grupoProdutoTO.getModeloTipoProdutoRestricaoTOList().isEmpty()) {
			
			List<TipoProdutoTO> tipoProdutoSearch = new ArrayList<TipoProdutoTO>();
			
			Integer idTipoRestricaoArray[] = new Integer[grupoProdutoTO.getModeloTipoProdutoRestricaoTOList().size()];
			String idTipoCompativelSelecionados[] = new String[grupoProdutoTO.getModeloTipoProdutoRestricaoTOList().size()];
			
			for (int i = 0; i < grupoProdutoTO.getModeloTipoProdutoRestricaoTOList().size(); i++) {
				idTipoRestricaoArray[i] = grupoProdutoTO.getModeloTipoProdutoRestricaoTOList().get(i).getTipoProdutoTO().getIdTipoProduto();
				idTipoCompativelSelecionados[i] = obterTiposCompativeis(grupoProdutoTO.getModeloTipoProdutoRestricaoTOList().get(i).getModeloTipoProdutoCompativelTOList());
				tipoProdutoSearch.add(grupoProdutoTO.getModeloTipoProdutoRestricaoTOList().get(i).getTipoProdutoTO());
			}
			
			associacaoModeloRestricaoForm.setIdTipoCompativeisSelecionados(idTipoCompativelSelecionados);
			associacaoModeloRestricaoForm.setIdTipoRestricaoArray(idTipoRestricaoArray);
			
			request.setAttribute("idTipoCompativeisSelecionados", idTipoCompativelSelecionados);
			request.setAttribute("tipoProdutoSearch", tipoProdutoSearch);
			request.setAttribute("modeloTipoProdutoRestricaoTOList", grupoProdutoTO.getModeloTipoProdutoRestricaoTOList());
		
		}
		return associacaoModeloRestricaoForm;
	}
	
	private String obterTiposCompativeis(List<ModeloTipoProdutoCompativelTO> modeloTipoProdutoCompativelTOList) {
		
		String idsModeloTipoProdutoCompativelStr = "";
		
		for (ModeloTipoProdutoCompativelTO modeloTipoProdutoCompativelTO : modeloTipoProdutoCompativelTOList) {
			idsModeloTipoProdutoCompativelStr = idsModeloTipoProdutoCompativelStr + ";" + modeloTipoProdutoCompativelTO.getTipoProdutoTO().getIdTipoProduto();
		}
		
		return idsModeloTipoProdutoCompativelStr;
	}
	
	public AssociacaoModeloRestricaoForm resetForm(AssociacaoModeloRestricaoForm associacaoModeloRestricaoForm, HttpServletRequest request) {
		
		associacaoModeloRestricaoForm.setIdFabricanteSearch(null);
		associacaoModeloRestricaoForm.setIdGrupoProduto(null);
		associacaoModeloRestricaoForm.setIdModeloSearch(null);
		associacaoModeloRestricaoForm.setIdTipoProdutoSearch(null);
		associacaoModeloRestricaoForm.setIdTipocompativelArray(null);
		associacaoModeloRestricaoForm.setIdTipoRestricaoArray(null);
		
		request.setAttribute("nmModelo", null);
		request.setAttribute("modeloTipoRestricaoList", null);
		
		return associacaoModeloRestricaoForm;
	}
}

	