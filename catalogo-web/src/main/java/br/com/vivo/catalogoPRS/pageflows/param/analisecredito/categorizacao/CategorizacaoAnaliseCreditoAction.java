package br.com.vivo.catalogoPRS.pageflows.param.analisecredito.categorizacao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.indracompany.catalogo.to.AcaoTO;
import com.indracompany.catalogo.to.CategoriaScoreTO;
import com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO;
import com.indracompany.catalogo.to.ClassificacaoCategoriaScoreTO;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.vivo.catalogoPRS.bo.UserCatalogo;
import br.com.vivo.catalogoPRS.delegate.CategoriaScoreDelegate;
import br.com.vivo.catalogoPRS.delegate.CategorizacaoAnaliseCreditoDelegate;
import br.com.vivo.catalogoPRS.delegate.PlataformaDelegate;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.CategorizacaoAnaliseCreditoForm;

/**
 * @author Luiz Pereira
 */
public class CategorizacaoAnaliseCreditoAction  extends BaseMappingDispatchAction implements Serializable{

	private static final long serialVersionUID = 1L;
	private CategorizacaoAnaliseCreditoForm categorizacaoAnaliseCreditoForm;
	private List<AcaoTO> acaoList;

	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		CategorizacaoAnaliseCreditoForm categorizacaoAnaliseCreditoForm = (CategorizacaoAnaliseCreditoForm) form;
		
		carregar(categorizacaoAnaliseCreditoForm, request);
		this.categorizacaoAnaliseCreditoForm = categorizacaoAnaliseCreditoForm;
				
		return mapping.findForward("success");
	}
	
	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		CategorizacaoAnaliseCreditoForm categorizacaoAnaliseCreditoForm = (CategorizacaoAnaliseCreditoForm) form;
		
		carregar(categorizacaoAnaliseCreditoForm, request);
		
		CategorizacaoAnaliseCreditoDelegate categorizacaoAnaliseCreditoDelegate = new CategorizacaoAnaliseCreditoDelegate();
		
		if (categorizacaoAnaliseCreditoForm.getOptPesquisa().equalsIgnoreCase("P")) {
			request.setAttribute("categorizacaoAnaliseCreditoList", categorizacaoAnaliseCreditoDelegate.searchPlano(doCategorizacaoAnaliseCreditoSearchTO(categorizacaoAnaliseCreditoForm)));
		} else if (categorizacaoAnaliseCreditoForm.getOptPesquisa().equalsIgnoreCase("S")) {
			request.setAttribute("categorizacaoAnaliseCreditoList", categorizacaoAnaliseCreditoDelegate.searchServico(doCategorizacaoAnaliseCreditoSearchTO(categorizacaoAnaliseCreditoForm)));
		} else if (categorizacaoAnaliseCreditoForm.getOptPesquisa().equalsIgnoreCase("O")) {
			request.setAttribute("categorizacaoAnaliseCreditoList", categorizacaoAnaliseCreditoDelegate.searchOfertaServico(doCategorizacaoAnaliseCreditoSearchTO(categorizacaoAnaliseCreditoForm)));
		} else if (categorizacaoAnaliseCreditoForm.getOptPesquisa().equalsIgnoreCase("F")) {
			request.setAttribute("categorizacaoAnaliseCreditoList", categorizacaoAnaliseCreditoDelegate.searchServicoFixa(doCategorizacaoAnaliseCreditoSearchTO(categorizacaoAnaliseCreditoForm)));
        }
        
				
		categorizacaoAnaliseCreditoForm.setTpPesquisa(categorizacaoAnaliseCreditoForm.getOptPesquisa());
		request.setAttribute("tipoPesquisa", categorizacaoAnaliseCreditoForm.getTpPesquisa());
		
		return mapping.findForward("success");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		CategorizacaoAnaliseCreditoForm categorizacaoAnaliseCreditoForm = (CategorizacaoAnaliseCreditoForm) form;
		
		UserCatalogo usuarioLogado = (UserCatalogo) request.getSession().getAttribute("logged_user");
		
		CategorizacaoAnaliseCreditoDelegate categorizacaoAnaliseCreditoDelegate = new CategorizacaoAnaliseCreditoDelegate();
		
		if (categorizacaoAnaliseCreditoForm.getTpPesquisa().equalsIgnoreCase("P")) {
			categorizacaoAnaliseCreditoDelegate.createPlanoCategoriaScore(doCategorizacaoAnaliseCreditoTO(categorizacaoAnaliseCreditoForm), usuarioLogado.getUser().getUsername(), Arrays.asList(categorizacaoAnaliseCreditoForm.getCheckRecord()));
		} else if (categorizacaoAnaliseCreditoForm.getTpPesquisa().equalsIgnoreCase("S") || categorizacaoAnaliseCreditoForm.getTpPesquisa().equalsIgnoreCase("F")) {
			categorizacaoAnaliseCreditoDelegate.createServicoCategoriaScore(doCategorizacaoAnaliseCreditoTO(categorizacaoAnaliseCreditoForm), usuarioLogado.getUser().getUsername(), Arrays.asList(categorizacaoAnaliseCreditoForm.getCheckRecord()));
		} else if (categorizacaoAnaliseCreditoForm.getTpPesquisa().equalsIgnoreCase("O")) {
			categorizacaoAnaliseCreditoDelegate.createOfertaServicoCategoriaScore(doCategorizacaoAnaliseCreditoTO(categorizacaoAnaliseCreditoForm), usuarioLogado.getUser().getUsername(), Arrays.asList(categorizacaoAnaliseCreditoForm.getCheckRecord()));
		}
		
		request.setAttribute("labelSucess", "Categorizado(s) com sucesso.");
		search(mapping, categorizacaoAnaliseCreditoForm, request, response);
		
		return mapping.findForward("success");
	}
	
	public ActionForward desassociar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		CategorizacaoAnaliseCreditoForm categorizacaoAnaliseCreditoForm = (CategorizacaoAnaliseCreditoForm) form;
		
		CategorizacaoAnaliseCreditoDelegate categorizacaoAnaliseCreditoDelegate = new CategorizacaoAnaliseCreditoDelegate();
		try {
			
			if (categorizacaoAnaliseCreditoForm.getTpPesquisa().equalsIgnoreCase("P")) {
				categorizacaoAnaliseCreditoDelegate.desassociarPlanoCategoriaScore(Arrays.asList(categorizacaoAnaliseCreditoForm.getCheckRecord()));
			} else if (categorizacaoAnaliseCreditoForm.getTpPesquisa().equalsIgnoreCase("S") || categorizacaoAnaliseCreditoForm.getTpPesquisa().equalsIgnoreCase("F")) {
				categorizacaoAnaliseCreditoDelegate.desassociarServicoCategoriaScore(Arrays.asList(categorizacaoAnaliseCreditoForm.getCheckRecord()));
			} else if (categorizacaoAnaliseCreditoForm.getTpPesquisa().equalsIgnoreCase("O")) {
				categorizacaoAnaliseCreditoDelegate.desassociarOfertaServicoCategoriaScore(Arrays.asList(categorizacaoAnaliseCreditoForm.getCheckRecord()));
			}
			
			request.setAttribute("labelSucess", "Desassociado(s) com sucesso.");
			search(mapping, categorizacaoAnaliseCreditoForm, request, response);
		
		} catch (BusinessException e) {
			request.setAttribute("labelError", e.getMessage());
			search(mapping, categorizacaoAnaliseCreditoForm, request, response);
		}
		
		return mapping.findForward("success");
	}
	
	private void carregar(CategorizacaoAnaliseCreditoForm categorizacaoAnaliseCreditoForm, HttpServletRequest request) {
		
		PlataformaDelegate plataformaDelegate = new PlataformaDelegate();
		request.setAttribute("plataformaList", plataformaDelegate.findAllWithExpections(new Integer[]{1}));
		
		CategoriaScoreTO categoriaScoreTO = new CategoriaScoreTO();
		ClassificacaoCategoriaScoreTO classificacaoCategoriaScoreTO = new ClassificacaoCategoriaScoreTO();
		
		if (categorizacaoAnaliseCreditoForm.getOptPesquisa() != null) {
			
			if (categorizacaoAnaliseCreditoForm.getOptPesquisa().equalsIgnoreCase("P")) {
				classificacaoCategoriaScoreTO.setIdClassificacaoCategoriaScore(1);
				categorizacaoAnaliseCreditoForm.setOptPesquisa("P");
			} else if (categorizacaoAnaliseCreditoForm.getOptPesquisa().equalsIgnoreCase("S")) {
				classificacaoCategoriaScoreTO.setIdClassificacaoCategoriaScore(2);
				categorizacaoAnaliseCreditoForm.setOptPesquisa("S");
			} else if (categorizacaoAnaliseCreditoForm.getOptPesquisa().equalsIgnoreCase("O")) {
				classificacaoCategoriaScoreTO.setIdClassificacaoCategoriaScore(3);
				categorizacaoAnaliseCreditoForm.setOptPesquisa("O");
			} else if (categorizacaoAnaliseCreditoForm.getOptPesquisa().equalsIgnoreCase("F")) {
                classificacaoCategoriaScoreTO.setIdClassificacaoCategoriaScore(4);
                categorizacaoAnaliseCreditoForm.setOptPesquisa("F");
            }
			
		} else {

			classificacaoCategoriaScoreTO.setIdClassificacaoCategoriaScore(1);
			categorizacaoAnaliseCreditoForm.setOptPesquisa("P");
			
		}
		categorizacaoAnaliseCreditoForm.setTpPesquisa(categorizacaoAnaliseCreditoForm.getOptPesquisa());
		request.setAttribute("tipoPesquisa", categorizacaoAnaliseCreditoForm.getTpPesquisa());
		
		categoriaScoreTO.setClassificacaoCategoriaScoreTO( classificacaoCategoriaScoreTO );
		CategoriaScoreDelegate categoriaScoreDelegate = new CategoriaScoreDelegate();
		request.setAttribute("categoriaScoreList", categoriaScoreDelegate.searchCategoriaScore(categoriaScoreTO));
	}
	

	public CategorizacaoAnaliseCreditoTO doCategorizacaoAnaliseCreditoSearchTO(CategorizacaoAnaliseCreditoForm categorizacaoAnaliseCreditoForm) {
		CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO = new CategorizacaoAnaliseCreditoTO();
		categorizacaoAnaliseCreditoTO.setNome(categorizacaoAnaliseCreditoForm.getNome());
		categorizacaoAnaliseCreditoTO.setIdPlataformas(categorizacaoAnaliseCreditoForm.getIdPlataformas());
		categorizacaoAnaliseCreditoTO.setIdCategoria(categorizacaoAnaliseCreditoForm.getIdCategoriaScoreSearch());
		return categorizacaoAnaliseCreditoTO;
	}
	

	public CategorizacaoAnaliseCreditoTO doCategorizacaoAnaliseCreditoTO(CategorizacaoAnaliseCreditoForm categorizacaoAnaliseCreditoForm) {
		CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO = new CategorizacaoAnaliseCreditoTO();
		categorizacaoAnaliseCreditoTO.setNome(categorizacaoAnaliseCreditoForm.getNome());
		categorizacaoAnaliseCreditoTO.setIdPlataformas(categorizacaoAnaliseCreditoForm.getIdPlataformas());
		categorizacaoAnaliseCreditoTO.setIdCategoria(categorizacaoAnaliseCreditoForm.getIdCategoriaScore());
		return categorizacaoAnaliseCreditoTO;
	}
	

	public CategorizacaoAnaliseCreditoForm resetForm(CategorizacaoAnaliseCreditoForm acaoForm) {
		if (acaoForm != null) {
		}
		return acaoForm;
	}

	public CategorizacaoAnaliseCreditoForm getAcaoForm() {
		return categorizacaoAnaliseCreditoForm;
	}
	
	public void setAcaoForm(CategorizacaoAnaliseCreditoForm acaoForm) {
		this.categorizacaoAnaliseCreditoForm = acaoForm;
	}
	
	public List<AcaoTO> getAcaoList() {
		return acaoList;
	}

	public void setAcaoList(List<AcaoTO> acaoList) {
		this.acaoList = acaoList;
	}
		
}

	