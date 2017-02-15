package br.com.vivo.catalogoPRS.pageflows.param.planoServico.restricaouf;

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
import br.com.vivo.catalogoPRS.delegate.PlanoServicoUfRestricaoDelegate;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.RestricaoPlanoServicoForm;

import com.indracompany.catalogo.to.PlanoServicoUfRestricaoTO;
import com.indracompany.catalogo.to.UfTO;

public class RestricaoUFPlanoServicoAction extends BaseMappingDispatchAction implements Serializable{

	private static final long serialVersionUID = 1L;

	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		return mapping.findForward("success");
	}
	
	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		RestricaoPlanoServicoForm restricaoPlanoServicoForm = (RestricaoPlanoServicoForm) form;
		
		try {
			pesquisaPrincipal(restricaoPlanoServicoForm);
		} catch (BusinessException e) {
			request.setAttribute("labelError", e.getMessage());
		}
		
		request.setAttribute("planoServicoList", restricaoPlanoServicoForm);
		
		return mapping.findForward("success");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		RestricaoPlanoServicoForm restricaoPlanoServicoForm = (RestricaoPlanoServicoForm) form;
		
		try {
			
			PlanoServicoUfRestricaoDelegate delegate = new PlanoServicoUfRestricaoDelegate();
			
			if (restricaoPlanoServicoForm.getTipoPesquisa().equals("P")) {
				delegate.configurarRestricaoPlano(doTOList(restricaoPlanoServicoForm.getRestricoesUf(), restricaoPlanoServicoForm.getSemRestricoesUf(), request));
			} else {
				delegate.configurarRestricaoServico(doTOList(restricaoPlanoServicoForm.getRestricoesUf(), restricaoPlanoServicoForm.getSemRestricoesUf(), request));
			}
			
			pesquisaPrincipal(restricaoPlanoServicoForm);
			
			request.setAttribute("labelSucess", "Configura&ccedil;&atilde;o Salva com Sucesso!");
		} catch (BusinessException e) {
			request.setAttribute("labelError", e.getMessage());
		}
		
		return mapping.findForward("success");
	}
	
	private void pesquisaPrincipal(RestricaoPlanoServicoForm restricaoPlanoServicoForm) throws BusinessException {

		List<PlanoServicoUfRestricaoTO> planoServicoList;
		List<UfTO> ufList;
		
		PlanoServicoUfRestricaoDelegate delegate = new PlanoServicoUfRestricaoDelegate();
		
		ufList = delegate.findAllUf();
		restricaoPlanoServicoForm.setUfList(ufList);
		
		if ( restricaoPlanoServicoForm.getTipoPesquisa().equals("P") ) {
			planoServicoList = delegate.searchPlano(doTO(restricaoPlanoServicoForm));
		} else {
			planoServicoList = delegate.searchServico(doTO(restricaoPlanoServicoForm));
		}
		
		restricaoPlanoServicoForm.setPlanoServicoList(planoServicoList);

	}
	
	private PlanoServicoUfRestricaoTO doTO(RestricaoPlanoServicoForm restricaoPlanoServicoForm) {
		
		PlanoServicoUfRestricaoTO to = new PlanoServicoUfRestricaoTO();
		
		to.setNome(restricaoPlanoServicoForm.getNome());
		to.setCodigo(restricaoPlanoServicoForm.getCodigo());
		to.setIn4g( (restricaoPlanoServicoForm.getInd4G() ? "S" : "N") );
		
		return to;
	}
	
	private List<PlanoServicoUfRestricaoTO> doTOList(String[] restricoes, Long[] semRestricoes, HttpServletRequest request) {
		
		List<PlanoServicoUfRestricaoTO> planoServicoUfTOList = null;
		PlanoServicoUfRestricaoTO planoServicoUfTO = null;
		UfTO uf = null;
		
		if (restricoes.length > 0) {
			
			planoServicoUfTOList = new ArrayList<PlanoServicoUfRestricaoTO>();
			
			for ( int i = 0; i < restricoes.length; ++i ) {
				String configuracao = restricoes[i];
				Long id = Long.valueOf( configuracao.split("\\|")[0] );
				Long idUf = Long.valueOf( configuracao.split("\\|")[1] );
				
				planoServicoUfTO = new PlanoServicoUfRestricaoTO();
				planoServicoUfTO.setId(id);
				planoServicoUfTO.setDtCriacaoAlteracao(new Date());
				UserCatalogo usuarioLogado = (UserCatalogo) request.getSession().getAttribute("logged_user");
				planoServicoUfTO.setNmUsuarioCriacaoAlteracao(usuarioLogado.getUser().getUsername());
				uf = new UfTO();
				uf.setIdUf(idUf);
				
				if (!planoServicoUfTOList.contains(planoServicoUfTO)) {
					planoServicoUfTO.getUfTOList().add(uf);
					planoServicoUfTOList.add(planoServicoUfTO);
				} else {
					if ( !planoServicoUfTOList.get( planoServicoUfTOList.indexOf(planoServicoUfTO) ).getUfTOList().contains(uf) ) {
						planoServicoUfTOList.get( planoServicoUfTOList.indexOf(planoServicoUfTO) ).getUfTOList().add(uf);
					}
				}
			}

		}

		if ( semRestricoes.length > 0 ) {

			if ( planoServicoUfTOList == null ) {
				planoServicoUfTOList = new ArrayList<PlanoServicoUfRestricaoTO>();
			}
			
			for ( int i = 0; i < semRestricoes.length; ++i ) {
				planoServicoUfTO = new PlanoServicoUfRestricaoTO();
				planoServicoUfTO.setId(semRestricoes[i]);
				
				if (!planoServicoUfTOList.contains(planoServicoUfTO)) {
					planoServicoUfTOList.add(planoServicoUfTO);
				}
			}
		}
		
		return planoServicoUfTOList;
	}
	
}
