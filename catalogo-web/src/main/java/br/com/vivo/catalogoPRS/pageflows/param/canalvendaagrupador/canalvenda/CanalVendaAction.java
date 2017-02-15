package br.com.vivo.catalogoPRS.pageflows.param.canalvendaagrupador.canalvenda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.vivo.catalogoPRS.delegate.CanalVendaAgrupadorDelegate;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.CanalVendaAgrupadorForm;

import com.indracompany.catalogo.to.CanalVendaTO;
import com.indracompany.catalogo.to.EpsTO;

public class CanalVendaAction extends BaseMappingDispatchAction implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<CanalVendaTO> canalVendaTOList;
	private Boolean pesquisaAtiva = Boolean.FALSE;
	private Boolean cadastroAtivo = Boolean.FALSE;
	private List<EpsTO> epsTOList;
	private String SUCCESS = "success";
	
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		carregarCombos();
		setPesquisaAtiva(Boolean.FALSE);
		request.setAttribute("pesquisaAtiva", Boolean.FALSE);
		setCadastroAtivo(Boolean.FALSE);
		request.setAttribute("cadastroAtivo", Boolean.FALSE);
		this.atualizarParametrosPagina(request,response);

		return mapping.findForward(SUCCESS);
	}

	public ActionForward pesquisar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CanalVendaAgrupadorForm canalVendaAgrupadorForm = (CanalVendaAgrupadorForm)form;

		canalVendaAgrupadorForm.setTabelaAgrupadoresAtiva(Boolean.FALSE);
		setPesquisaAtiva(Boolean.TRUE);
		setCadastroAtivo(Boolean.FALSE);
		setCanalVendaTOList(new CanalVendaAgrupadorDelegate().pesquisar(doCanalVendaTOSearch(canalVendaAgrupadorForm)));
		
		atualizarParametrosPagina(request,response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward criarNovo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CanalVendaAgrupadorForm canalVendaAgrupadorForm = (CanalVendaAgrupadorForm)form;

		setPesquisaAtiva(Boolean.FALSE);
		setCadastroAtivo(Boolean.TRUE);
		
		clearForm(canalVendaAgrupadorForm);
		atualizarParametrosPagina(request,response);

		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward editar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CanalVendaAgrupadorForm canalVendaAgrupadorForm = (CanalVendaAgrupadorForm)form;
		
		setCadastroAtivo(Boolean.TRUE);
		canalVendaAgrupadorForm = doCanalVendaForm(canalVendaAgrupadorForm, new CanalVendaAgrupadorDelegate().pesquisarPorId(doCanalVendaTO(canalVendaAgrupadorForm)));
		atualizarParametrosPagina(request,response);

		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward salvar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CanalVendaAgrupadorForm canalVendaAgrupadorForm = (CanalVendaAgrupadorForm)form;

		String mensagemSucesso = null;
		CanalVendaTO canalVendaTOForm,canalVendaTORes = null;
		
		canalVendaTOForm = doCanalVendaTO(canalVendaAgrupadorForm);
		setCadastroAtivo(Boolean.FALSE);
		
		try {
			if (canalVendaTOForm.getIdCanalVenda() == null) {
				canalVendaTOForm.setInCriacaoCatalogo("S");
				canalVendaTOForm.setInDisponivel("S");
				canalVendaTORes = new CanalVendaAgrupadorDelegate().createUpdate(canalVendaTOForm);
				mensagemSucesso = "Grupo Comercial "+canalVendaTORes.getCdCanalVenda().toString()+" criado com sucesso";
			} else {
				canalVendaTORes = new CanalVendaAgrupadorDelegate().createUpdate(canalVendaTOForm);
				mensagemSucesso = "Grupo Comercial alterado com sucesso";
			}
		} catch(BusinessException e){
			atualizarParametrosPagina(request,response);
			request.setAttribute("labelError", e.getMessage());
            setCadastroAtivo(Boolean.TRUE);
		}
		
		request.setAttribute("labelSucess", mensagemSucesso);
		canalVendaAgrupadorForm.setIdCanalVenda(null);

		if(!(canalVendaAgrupadorForm.getCdCanalVendaSearch() == null
		   && canalVendaAgrupadorForm.getNmCanalVendaSearch().equals("")
		   && canalVendaAgrupadorForm.getInCriacaoCatalogoSearch().equals("")
		   && canalVendaAgrupadorForm.getInDisponivelSearch().equals(""))){
			setCanalVendaTOList(new CanalVendaAgrupadorDelegate().pesquisar(doCanalVendaTOSearch(canalVendaAgrupadorForm)));
		}

		atualizarParametrosPagina(request,response);

		return mapping.findForward(SUCCESS);
	}

	public ActionForward alternarInDisponivel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CanalVendaAgrupadorForm canalVendaAgrupadorForm = (CanalVendaAgrupadorForm)form;
		
		String mensagemRetorno = null;
		
		CanalVendaTO canalVendaTO = new CanalVendaTO();
		canalVendaTO.setIdCanalVenda(canalVendaAgrupadorForm.getIdCanalVendaAlternarIndisponivel());
		
		new CanalVendaAgrupadorDelegate().alternarInDisponivel(canalVendaTO);
		
		setCanalVendaTOList(new CanalVendaAgrupadorDelegate().pesquisar(doCanalVendaTOSearch(canalVendaAgrupadorForm)));
		canalVendaTO = new CanalVendaAgrupadorDelegate().pesquisarPorId(canalVendaTO);
		canalVendaAgrupadorForm.setInDisponivel(canalVendaTO.getInDisponivel());
		if (canalVendaTO.getInDisponivel().equalsIgnoreCase("S")) {
			mensagemRetorno = String.format("Configura&ccedil;&atilde;o Ativada com sucesso!", "");
		} else {
			mensagemRetorno = "Configura&ccedil;&atilde;o Desativada com sucesso!";
		}
		request.setAttribute("labelSucess", mensagemRetorno);
		
		atualizarParametrosPagina(request,response);

		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward remover(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CanalVendaAgrupadorForm canalVendaAgrupadorForm = (CanalVendaAgrupadorForm)form;
		
		try {
			new CanalVendaAgrupadorDelegate().remover(doCanalVendaTO(canalVendaAgrupadorForm));
		} catch(BusinessException e){
			atualizarParametrosPagina(request,response);
			request.setAttribute("labelError", e.getMessage());
		}
		
		setCanalVendaTOList(new CanalVendaAgrupadorDelegate().pesquisar(doCanalVendaTOSearch(canalVendaAgrupadorForm)));
		canalVendaAgrupadorForm.setIdCanalVenda(null);
		atualizarParametrosPagina(request,response);

		return mapping.findForward(SUCCESS);
	}
	
	
	public ActionForward associarAgrupador(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CanalVendaAgrupadorForm canalVendaAgrupadorForm = (CanalVendaAgrupadorForm)form;

		new CanalVendaAgrupadorDelegate().associarAgrupador(getCanalVendaIdList(canalVendaAgrupadorForm), canalVendaAgrupadorForm.getIdEps());

		setPesquisaAtiva(Boolean.TRUE);
		setCanalVendaTOList(new CanalVendaAgrupadorDelegate().pesquisar(doCanalVendaTOSearch(canalVendaAgrupadorForm)));
	
		request.setAttribute("labelSucess", "Associado com sucesso.");
		atualizarParametrosPagina(request,response);

		return mapping.findForward(SUCCESS);
	}

	public ActionForward desassociarAgrupador(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		CanalVendaAgrupadorForm canalVendaAgrupadorForm = (CanalVendaAgrupadorForm)form;

		new CanalVendaAgrupadorDelegate().desassociarAgrupador(getCanalVendaIdList(canalVendaAgrupadorForm));
		
		setPesquisaAtiva(Boolean.TRUE);
		setCanalVendaTOList(new CanalVendaAgrupadorDelegate().pesquisar(doCanalVendaTOSearch(canalVendaAgrupadorForm)));
	
		request.setAttribute("labelSucess", "Desassociado com sucesso.");
		atualizarParametrosPagina(request,response);

		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward trocarPagina(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		atualizarParametrosPagina(request,response);

		return mapping.findForward(SUCCESS);
	}
	
	private List<Long> getCanalVendaIdList(CanalVendaAgrupadorForm canalVendaAgrupadorForm) {
		List<Long> result = new ArrayList<Long>();
		for(Long id : canalVendaAgrupadorForm.getIdCanalVendaCheckedList()){
			result.add(id);
		}
		return result;
	}
	
	private void carregarCombos(){
		this.setEpsTOList(new CanalVendaAgrupadorDelegate().getEpsList());
	}
	
	private CanalVendaTO doCanalVendaTO(CanalVendaAgrupadorForm canalVendaAgrupadorForm){
		CanalVendaTO canalVendaTO = new CanalVendaTO();
		
		if(canalVendaAgrupadorForm != null){
			if(canalVendaAgrupadorForm.getCdCanalVenda() == null || canalVendaAgrupadorForm.equals("")){
				canalVendaTO.setCdCanalVenda(null);
			} else {
				canalVendaTO.setCdCanalVenda(canalVendaAgrupadorForm.getCdCanalVenda());
			}
			//campo descricao carregado com nome imitando o comportamento do sincronismo entre catalogo360 e legados ATIS/CSO
			if(canalVendaAgrupadorForm.getNmCanalVenda() == null || canalVendaAgrupadorForm.getNmCanalVenda().equals("")){
				canalVendaTO.setDsCanalVenda(null);
			} else {
				canalVendaTO.setDsCanalVenda(canalVendaAgrupadorForm.getNmCanalVenda().trim());
			}
			canalVendaTO.setIdCanalVenda(canalVendaAgrupadorForm.getIdCanalVenda());
			if(canalVendaAgrupadorForm.getNmCanalVenda() == null || canalVendaAgrupadorForm.equals("")){
				canalVendaTO.setNmCanalVenda(null);
			} else {
				canalVendaTO.setNmCanalVenda(canalVendaAgrupadorForm.getNmCanalVenda().trim());
			}
			canalVendaTO.setInDisponivel(canalVendaAgrupadorForm.getInDisponivel());
			canalVendaTO.setInCriacaoCatalogo(canalVendaAgrupadorForm.getInCriacaoCatalogo());
			
		}
		return canalVendaTO;
	}
	
	private CanalVendaTO doCanalVendaTOSearch(CanalVendaAgrupadorForm canalVendaAgrupadorForm){
		CanalVendaTO canalVendaTO = new CanalVendaTO();
		
		if(canalVendaAgrupadorForm != null){
			
			canalVendaTO.setIdCanalVenda(canalVendaAgrupadorForm.getIdCanalVenda());
            if (StringUtils.isNotBlank(canalVendaAgrupadorForm.getCdCanalVendaSearch())) {
                canalVendaTO.setCdCanalVenda(canalVendaAgrupadorForm.getCdCanalVendaSearch());
            }
            if (StringUtils.isNotBlank(canalVendaAgrupadorForm.getNmCanalVendaSearch())) {
                canalVendaTO.setNmCanalVenda(canalVendaAgrupadorForm.getNmCanalVendaSearch());
            }
            if (StringUtils.isNotBlank(canalVendaAgrupadorForm.getInDisponivelSearch())) {
                canalVendaTO.setInDisponivel(canalVendaAgrupadorForm.getInDisponivelSearch());
            }
            if (StringUtils.isNotBlank(canalVendaAgrupadorForm.getInCriacaoCatalogoSearch())) {
                canalVendaTO.setInCriacaoCatalogo(canalVendaAgrupadorForm.getInCriacaoCatalogoSearch());
            }
            canalVendaTO.setEpsTO(new EpsTO(canalVendaAgrupadorForm.getIdEpsSearch()));
		}
		return canalVendaTO;
	}

	private CanalVendaAgrupadorForm doCanalVendaForm(CanalVendaAgrupadorForm canalVendaAgrupadorForm, CanalVendaTO canalVendaTO){
		
		if(canalVendaTO != null){
			canalVendaAgrupadorForm.setCdCanalVenda(canalVendaTO.getCdCanalVenda());
			canalVendaAgrupadorForm.setDsCanalVenda(canalVendaTO.getNmCanalVenda());
			canalVendaAgrupadorForm.setIdCanalVenda(canalVendaTO.getIdCanalVenda());
			canalVendaAgrupadorForm.setNmCanalVenda(canalVendaTO.getNmCanalVenda());
			canalVendaAgrupadorForm.setInDisponivel(canalVendaTO.getInDisponivel());
			canalVendaAgrupadorForm.setInCriacaoCatalogo(canalVendaTO.getInCriacaoCatalogo());
		}
		
		return canalVendaAgrupadorForm;
	}
	
	private void atualizarParametrosPagina(HttpServletRequest request, HttpServletResponse response) {
		this.cleanHeader(response);
		if(getPesquisaAtiva()){
			request.setAttribute("pesquisaAtiva", Boolean.TRUE);
			request.setAttribute("canalVendaTOList", getCanalVendaTOList());
		} else {
			request.setAttribute("pesquisaAtiva", Boolean.FALSE);
		}
		request.setAttribute("cadastroAtivo", getCadastroAtivo());
		request.setAttribute("epsTOList", this.getEpsTOList());
	}
	
	private void clearForm(CanalVendaAgrupadorForm canalVendaAgrupadorForm){
		
		canalVendaAgrupadorForm.setIdCanalVenda(null);
		canalVendaAgrupadorForm.setCdCanalVenda(null);
		canalVendaAgrupadorForm.setDsCanalVenda(null);
		canalVendaAgrupadorForm.setIdCanalVenda(null);
		canalVendaAgrupadorForm.setNmCanalVenda(null);
		canalVendaAgrupadorForm.setInDisponivel(null);
		canalVendaAgrupadorForm.setInCriacaoCatalogo(null);
		
	}

	public Boolean getCadastroAtivo() {
		return cadastroAtivo;
	}

	public void setCadastroAtivo(Boolean cadastroAtivo) {
		this.cadastroAtivo = cadastroAtivo;
	}

	public List<CanalVendaTO> getCanalVendaTOList() {
		return canalVendaTOList;
	}

	public void setCanalVendaTOList(List<CanalVendaTO> canalVendaTOList) {
		this.canalVendaTOList = canalVendaTOList;
	}

	public Boolean getPesquisaAtiva() {
		return pesquisaAtiva;
	}

	public void setPesquisaAtiva(Boolean pesquisaAtiva) {
		this.pesquisaAtiva = pesquisaAtiva;
	}

	public List<EpsTO> getEpsTOList() {
		return epsTOList;
	}

	public void setEpsTOList(List<EpsTO> epsTOList) {
		this.epsTOList = epsTOList;
	}
}
