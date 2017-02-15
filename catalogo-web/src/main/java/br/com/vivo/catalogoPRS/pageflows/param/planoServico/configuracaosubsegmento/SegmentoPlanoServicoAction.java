package br.com.vivo.catalogoPRS.pageflows.param.planoServico.configuracaosubsegmento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.indracompany.catalogo.to.CategoriaTO;
import com.indracompany.catalogo.to.FamiliaTO;
import com.indracompany.catalogo.to.PlanoSegmentoTO;
import com.indracompany.catalogo.to.PlanoTO;
import com.indracompany.catalogo.to.PlataformaTO;
import com.indracompany.catalogo.to.SegmentoTO;
import com.indracompany.catalogo.to.SegmentoTO.TipoPesquisaSegmento;
import com.indracompany.catalogo.to.ServicoFixaTO;
import com.indracompany.catalogo.to.ServicoSegmentoTO;
import com.indracompany.catalogo.to.TecnologiaTO;
import com.indracompany.catalogo.to.TipoServicoTO;

import br.com.vivo.catalogoPRS.bo.UserCatalogo;
import br.com.vivo.catalogoPRS.delegate.SegmentoPlanoServicoDelegate;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.SegmentoPlanoServicoForm;


public class SegmentoPlanoServicoAction extends BaseMappingDispatchAction implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String BLANK = "";
	private static final String SGTITULAR = "T";
	private static final String SGDEPENDENTE = "D";
	private static final String MENSAGEM_GRAVAR_SUCESSO = "Registro(s) configurado(s) com sucesso";
	private static final String MENSAGEM_DESASSOCIAR_SUCESSO = "Registro(s) desassociado(s) com sucesso";
	private static final String MENSAGEM_CHECKLIST_VAZIA = "Favor selecionar ao menos um registro";
	private List<PlataformaTO> plataformaTOList;
	private List<CategoriaTO> categoriaPlanoTOList;
	private List<SegmentoTO> segmentoTOList;
	private List<TipoServicoTO> tipoServicoTOList;
	private List<FamiliaTO> familiaTOList;
	private List<TecnologiaTO> tecnologiaTOList;
	private List<CategoriaTO> categoriaServicoFixaTOList;
	private List<PlanoTO> planoTOList;
	private List<ServicoFixaTO> servicoFixaTOList;
	

	
	@SuppressWarnings("unchecked")
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		
		SegmentoPlanoServicoForm segmentoPlanoServicoForm = new SegmentoPlanoServicoForm();
		
		SegmentoPlanoServicoDelegate delegate = new SegmentoPlanoServicoDelegate();
		
		@SuppressWarnings("rawtypes")
		Map<String, List> combosMap = delegate.loadCombos();
		
		this.setPlataformaTOList(combosMap.get("plataformaTOList"));
		this.setCategoriaPlanoTOList(combosMap.get("categoriaPlanoTOList"));
		this.setSegmentoTOList(combosMap.get("segmentoTOList"));
		this.setTipoServicoTOList(combosMap.get("tipoServicoTOList"));
		this.setFamiliaTOList(combosMap.get("familiaTOList"));
		this.setTecnologiaTOList(combosMap.get("tecnologiaTOList"));
		this.setCategoriaServicoFixaTOList(combosMap.get("categoriaServicoFixaTOList"));
		segmentoPlanoServicoForm.setSearchType("movel");
		this.setPlanoTOList(null);
		this.setServicoFixaTOList(null);
		this.loadRequestObjects(segmentoPlanoServicoForm, request,response);
		
		return mapping.findForward("success");
	}
	
	public ActionForward openPageMovel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		
		SegmentoPlanoServicoForm segmentoPlanoServicoForm = (SegmentoPlanoServicoForm) form;
		
		segmentoPlanoServicoForm.setSearchType("movel");
		this.setPlanoTOList(null);
		loadRequestObjects(segmentoPlanoServicoForm, request, response);
		
		return mapping.findForward("success");
	}

	public ActionForward openPageFixa(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		
		SegmentoPlanoServicoForm segmentoPlanoServicoForm = (SegmentoPlanoServicoForm) form;
		
		segmentoPlanoServicoForm.setSearchType("fixa");
		this.setServicoFixaTOList(null);
		loadRequestObjects(segmentoPlanoServicoForm, request, response);
		
		return mapping.findForward("success");
	}
	
	public ActionForward searchPlano(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		
		SegmentoPlanoServicoForm segmentoPlanoServicoForm = (SegmentoPlanoServicoForm) form;
		
		this.setPlanoTOList(new SegmentoPlanoServicoDelegate().searchPlano(createPlanoTO(segmentoPlanoServicoForm)));
		
		loadRequestObjects(segmentoPlanoServicoForm, request, response);
		
		return mapping.findForward("success");
	}

	public ActionForward savePlanoSegmento(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		
		SegmentoPlanoServicoForm segmentoPlanoServicoForm = (SegmentoPlanoServicoForm) form;
		
		if(segmentoPlanoServicoForm.getIdPlanoCheckedList() != null){
			validateMinElemCheckList(segmentoPlanoServicoForm.getIdPlanoCheckedList());
			new SegmentoPlanoServicoDelegate().savePlanoSegmento(createPlanoSegmentoTO(segmentoPlanoServicoForm, request));
			this.setPlanoTOList(new SegmentoPlanoServicoDelegate().searchPlano(createPlanoTO(segmentoPlanoServicoForm)));
			this.clearPlanoConfigFormBean(segmentoPlanoServicoForm);
			request.setAttribute("labelSucess",MENSAGEM_GRAVAR_SUCESSO);
		} else {
			request.setAttribute("labelError",MENSAGEM_CHECKLIST_VAZIA);
		}
		
		loadRequestObjects(segmentoPlanoServicoForm, request, response);
		
		return mapping.findForward("success");
	}
	
	public ActionForward disassociatePlanoSegmento(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		
		SegmentoPlanoServicoForm segmentoPlanoServicoForm = (SegmentoPlanoServicoForm) form;
	
		if(segmentoPlanoServicoForm.getIdPlanoCheckedList() != null){
			validateMinElemCheckList(segmentoPlanoServicoForm.getIdPlanoCheckedList());
			new SegmentoPlanoServicoDelegate().disassociatePlanoSegmento(createPlanoSegmentoTO(segmentoPlanoServicoForm, request));
			this.setPlanoTOList(new SegmentoPlanoServicoDelegate().searchPlano(createPlanoTO(segmentoPlanoServicoForm)));
			this.clearPlanoConfigFormBean(segmentoPlanoServicoForm);
			request.setAttribute("labelSucess",MENSAGEM_DESASSOCIAR_SUCESSO);
		} else {
			request.setAttribute("labelError",MENSAGEM_CHECKLIST_VAZIA);
		}
		
		loadRequestObjects(segmentoPlanoServicoForm, request, response);
		
		return mapping.findForward("success");
	}

	public ActionForward changePage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		
		SegmentoPlanoServicoForm segmentoPlanoServicoForm = (SegmentoPlanoServicoForm) form;
		
		loadRequestObjects(segmentoPlanoServicoForm, request, response);
		
		return mapping.findForward("success");
	}

	public ActionForward searchServicoFixa(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		
		SegmentoPlanoServicoForm segmentoPlanoServicoForm = (SegmentoPlanoServicoForm) form;
		
		this.setServicoFixaTOList(new SegmentoPlanoServicoDelegate().searchServicoFixa(createServicoFixaTO(segmentoPlanoServicoForm)));
		
		loadRequestObjects(segmentoPlanoServicoForm, request, response);
		
		return mapping.findForward("success");
	}

	public ActionForward saveServicoSegmento(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		
		SegmentoPlanoServicoForm segmentoPlanoServicoForm = (SegmentoPlanoServicoForm) form;

		if(segmentoPlanoServicoForm.getIdServicoCheckedList() != null){
			validateMinElemCheckList(segmentoPlanoServicoForm.getIdServicoCheckedList());
			new SegmentoPlanoServicoDelegate().saveServicoSegmento(createServicoSegmentoTO(segmentoPlanoServicoForm, request));
			this.setServicoFixaTOList(new SegmentoPlanoServicoDelegate().searchServicoFixa(createServicoFixaTO(segmentoPlanoServicoForm)));
			this.clearServicoConfigForm(segmentoPlanoServicoForm);
			request.setAttribute("labelSucess", MENSAGEM_GRAVAR_SUCESSO);
		} else {
			request.setAttribute("labelError",MENSAGEM_CHECKLIST_VAZIA);
		}
		
		loadRequestObjects(segmentoPlanoServicoForm, request, response);
		
		return mapping.findForward("success");
	}

	public ActionForward disassociateServicoSegmento(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		
		SegmentoPlanoServicoForm segmentoPlanoServicoForm = (SegmentoPlanoServicoForm) form;

		if(segmentoPlanoServicoForm.getIdServicoCheckedList() != null){
			validateMinElemCheckList(segmentoPlanoServicoForm.getIdServicoCheckedList());
			new SegmentoPlanoServicoDelegate().disassociateServicoSegmento(createServicoSegmentoTO(segmentoPlanoServicoForm, request));
			this.setServicoFixaTOList(new SegmentoPlanoServicoDelegate().searchServicoFixa(createServicoFixaTO(segmentoPlanoServicoForm)));
			this.clearServicoConfigForm(segmentoPlanoServicoForm);
			request.setAttribute("labelSucess", MENSAGEM_DESASSOCIAR_SUCESSO);
		} else {
			request.setAttribute("labelError",MENSAGEM_CHECKLIST_VAZIA);
		}
		
		loadRequestObjects(segmentoPlanoServicoForm, request, response);
		
		return mapping.findForward("success");
	}
	
	@SuppressWarnings("unused")
	private Boolean validateMinElemCheckList(Integer[] checkList){
		for(Integer elem: checkList){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	private void loadRequestObjects(SegmentoPlanoServicoForm segmentoPlanoServicoForm, HttpServletRequest request, HttpServletResponse response){
		
		this.cleanHeader(response);
		request.setAttribute("plataformaTOList",this.getPlataformaTOList());
		request.setAttribute("categoriaPlanoTOList",this.getCategoriaPlanoTOList());
		request.setAttribute("segmentoTOList",this.getSegmentoTOList());
		request.setAttribute("tipoServicoTOList",this.getTipoServicoTOList());
		request.setAttribute("familiaTOList",this.getFamiliaTOList());
		request.setAttribute("categoriaServicoFixaTOList",this.getCategoriaServicoFixaTOList());
		request.setAttribute("tecnologiaTOList",this.getTecnologiaTOList());
		request.setAttribute("planoTOList",this.getPlanoTOList());
		request.setAttribute("servicoFixaTOList",this.getServicoFixaTOList());
	}
	
	private PlanoTO createPlanoTO(SegmentoPlanoServicoForm segmentoPlanoServicoForm){

		PlanoTO planoTO = new PlanoTO();
		
		planoTO.setIdPlano(segmentoPlanoServicoForm.getIdPlano());
		planoTO.setCdPlano(segmentoPlanoServicoForm.getCdPlano().equals(BLANK) ? null : segmentoPlanoServicoForm.getCdPlano());
		planoTO.setNmComercial(segmentoPlanoServicoForm.getNmPlano().equals(BLANK) ? null : segmentoPlanoServicoForm.getNmPlano());
		planoTO.setPlataformaTO(segmentoPlanoServicoForm.getIdPlataforma() != null && !segmentoPlanoServicoForm.getIdPlataforma().equals(new Integer(0)) ? new PlataformaTO(segmentoPlanoServicoForm.getIdPlataforma()): null);
		planoTO.setCategoriaTO(segmentoPlanoServicoForm.getIdCategoria() != null && !segmentoPlanoServicoForm.getIdCategoria().equals(new Integer(0)) ? new CategoriaTO(segmentoPlanoServicoForm.getIdCategoria()): null);
		if(segmentoPlanoServicoForm.getInTitular() != null && !segmentoPlanoServicoForm.getInTitular().equals("")){
			if(segmentoPlanoServicoForm.getInTitular().equals("S")){
				planoTO.setSgTitularidade(SGTITULAR);
			} else {
				planoTO.setSgTitularidade(SGDEPENDENTE);
			}
		}
		
		int idSegmentoForm = segmentoPlanoServicoForm.getIdSegmento() != null ? segmentoPlanoServicoForm.getIdSegmento().intValue() : 0;
		SegmentoTO segmentoTO = new SegmentoTO();
		if(segmentoPlanoServicoForm.getInInfancia() != null && !segmentoPlanoServicoForm.getInInfancia().equals("")){
			idSegmentoForm = -1;
			segmentoTO.setInInfancia(segmentoPlanoServicoForm.getInInfancia() != null ? segmentoPlanoServicoForm.getInInfancia() : null);
		}
		switch(idSegmentoForm){
		case 0:
			segmentoTO = null;
			break;
		case -1:
			segmentoTO.setTipoPesquisaSegmento(TipoPesquisaSegmento.SEGMENTADOS);
			planoTO.setSegmentoTO(segmentoTO);
			break;
		case -2:
			segmentoTO.setTipoPesquisaSegmento(TipoPesquisaSegmento.NAO_SEGMENTADOS);
			planoTO.setSegmentoTO(segmentoTO);
			break;
		default:
			segmentoTO.setIdSegmento(segmentoPlanoServicoForm.getIdSegmento());
			segmentoTO.setTipoPesquisaSegmento(TipoPesquisaSegmento.SEGMENTO);
			planoTO.setSegmentoTO(segmentoTO);
		}

		return planoTO;
	}
	
	private PlanoSegmentoTO createPlanoSegmentoTO(SegmentoPlanoServicoForm segmentoPlanoServicoForm, HttpServletRequest request){

		PlanoSegmentoTO planoSegmentoTO = new PlanoSegmentoTO();
		if(segmentoPlanoServicoForm.getInInfanciaNew() == null){
			planoSegmentoTO.setInInfancia("N");
		}else{
			planoSegmentoTO.setInInfancia("S");
		}
		
		SegmentoTO segmentoTO = new SegmentoTO();
		if(segmentoPlanoServicoForm.getIdSegmentoNew() != null){
			segmentoTO.setIdSegmento(segmentoPlanoServicoForm.getIdSegmentoNew());
		planoSegmentoTO.setSegmentoTO(segmentoTO);
		}
		
		List<PlanoTO> planoTOList = new ArrayList<PlanoTO>();
		for(Integer idPlano: segmentoPlanoServicoForm.getIdPlanoCheckedList()){
			planoTOList.add(new PlanoTO(idPlano));
		}
		planoSegmentoTO.setPlanoTOList(planoTOList);
		UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
		planoSegmentoTO.setNmUsuarioCriacao(userCatalogo.getUser().getUsername());
		
		return planoSegmentoTO;
	}
	
	private ServicoFixaTO createServicoFixaTO(SegmentoPlanoServicoForm segmentoPlanoServicoForm){
		ServicoFixaTO servicoFixaTO = new ServicoFixaTO();
		
		servicoFixaTO.setIdServico(segmentoPlanoServicoForm.getIdServico() != null && !segmentoPlanoServicoForm.getIdServico().equals(new Integer(0)) ? segmentoPlanoServicoForm.getIdServico() : null);
		servicoFixaTO.setCdServico(segmentoPlanoServicoForm.getCdServico() != null && !segmentoPlanoServicoForm.getCdServico().equals(BLANK) ? segmentoPlanoServicoForm.getCdServico() : null);
		servicoFixaTO.setNmComercial(segmentoPlanoServicoForm.getNmServico() != null && !segmentoPlanoServicoForm.getNmServico().equals(BLANK) ? segmentoPlanoServicoForm.getNmServico() : null);
		servicoFixaTO.setIdTipoServico(segmentoPlanoServicoForm.getIdTipoServico() != null && !segmentoPlanoServicoForm.getIdTipoServico().equals(new Integer(0)) ? segmentoPlanoServicoForm.getIdTipoServico() : null);
		servicoFixaTO.setFamiliaTO(segmentoPlanoServicoForm.getIdFamilia() != null && !segmentoPlanoServicoForm.getIdFamilia().equals(new Integer(0)) ? new FamiliaTO(segmentoPlanoServicoForm.getIdFamilia()) : null);
		servicoFixaTO.setCdCategoria(segmentoPlanoServicoForm.getCdCategoria() != null && !segmentoPlanoServicoForm.getCdCategoria().equals(BLANK) ? segmentoPlanoServicoForm.getCdCategoria() : null);
		servicoFixaTO.setTecnologiaTO(segmentoPlanoServicoForm.getIdTecnologia() != null && !segmentoPlanoServicoForm.getIdTecnologia().equals(new Integer(0)) ? new TecnologiaTO(segmentoPlanoServicoForm.getIdTecnologia()) : null);
		//servicoFixaTO.setSegmentoTO(formBean.idSegmento != null && !formBean.idSegmento.equals(new Long(0)) ? new SegmentoTO(formBean.idSegmento) : null);
		
		int idSegmentoForm = segmentoPlanoServicoForm.getIdSegmento() != null ? segmentoPlanoServicoForm.getIdSegmento().intValue() : 0;
		SegmentoTO segmentoTO = new SegmentoTO();
		if(segmentoPlanoServicoForm.getInInfancia() != null && !segmentoPlanoServicoForm.getInInfancia().equals("")){
			idSegmentoForm = -1;
			segmentoTO.setInInfancia(segmentoPlanoServicoForm.getInInfancia() != null ? segmentoPlanoServicoForm.getInInfancia() : null);
		}
		switch(idSegmentoForm){
		case 0:
			segmentoTO = null;
			break;
		case -1:
			segmentoTO.setTipoPesquisaSegmento(TipoPesquisaSegmento.SEGMENTADOS);
			servicoFixaTO.setSegmentoTO(segmentoTO);
			break;
		case -2:
			segmentoTO.setTipoPesquisaSegmento(TipoPesquisaSegmento.NAO_SEGMENTADOS);
			servicoFixaTO.setSegmentoTO(segmentoTO);
			break;
		default:
			segmentoTO.setIdSegmento(segmentoPlanoServicoForm.getIdSegmento());
			segmentoTO.setTipoPesquisaSegmento(TipoPesquisaSegmento.SEGMENTO);
			servicoFixaTO.setSegmentoTO(segmentoTO);
		}
		
		return servicoFixaTO;
	}
	
	private ServicoSegmentoTO createServicoSegmentoTO(SegmentoPlanoServicoForm segmentoPlanoServicoForm, HttpServletRequest request){

		ServicoSegmentoTO servicoSegmentoTO = new ServicoSegmentoTO();
		if(segmentoPlanoServicoForm.getInInfanciaNew() == null){
			servicoSegmentoTO.setInInfancia("N");
		}else{
			servicoSegmentoTO.setInInfancia("S");
		}
		
		SegmentoTO segmentoTO = new SegmentoTO();
		if(segmentoPlanoServicoForm.getIdSegmentoNew() != null){
			segmentoTO.setIdSegmento(segmentoPlanoServicoForm.getIdSegmentoNew());
			servicoSegmentoTO.setSegmentoTO(segmentoTO);
		}
		
		List<ServicoFixaTO> servicoTOList = new ArrayList<ServicoFixaTO>();
		for(Integer idServico: segmentoPlanoServicoForm.getIdServicoCheckedList()){
			servicoTOList.add(new ServicoFixaTO(idServico));
		}
		servicoSegmentoTO.setServicoTOList(servicoTOList);
		UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
		servicoSegmentoTO.setNmUsuarioCriacao(userCatalogo.getUser().getUsername());
		
		return servicoSegmentoTO;
	}
	
	private SegmentoPlanoServicoForm clearPlanoConfigFormBean(SegmentoPlanoServicoForm segmentoPlanoServicoForm){
		
		segmentoPlanoServicoForm.setEnabledCfgSegmento(Boolean.FALSE);
		segmentoPlanoServicoForm.setIdPlanoCheckedList(null); 
		segmentoPlanoServicoForm.setInInfancia("");
		segmentoPlanoServicoForm.setIdSegmentoNew(new Long(0));
		return segmentoPlanoServicoForm;
	}
	
	private SegmentoPlanoServicoForm clearServicoConfigForm(SegmentoPlanoServicoForm segmentoPlanoServicoForm){ 
		
		segmentoPlanoServicoForm.setEnabledCfgSegmento(Boolean.FALSE);
		segmentoPlanoServicoForm.setIdServicoCheckedList(null);
		segmentoPlanoServicoForm.setInInfancia("");
		segmentoPlanoServicoForm.setIdSegmentoNew(new Long(0));
		return segmentoPlanoServicoForm;
	}
	
	
	public List<CategoriaTO> getCategoriaPlanoTOList() {
		return categoriaPlanoTOList;
	}

	public void setCategoriaPlanoTOList(List<CategoriaTO> categoriaPlanoTOList) {
		this.categoriaPlanoTOList = categoriaPlanoTOList;
	}

	public List<CategoriaTO> getCategoriaServicoFixaTOList() {
		return categoriaServicoFixaTOList;
	}

	public void setCategoriaServicoFixaTOList(
			List<CategoriaTO> categoriaServicoFixaTOList) {
		this.categoriaServicoFixaTOList = categoriaServicoFixaTOList;
	}

	public List<FamiliaTO> getFamiliaTOList() {
		return familiaTOList;
	}

	public void setFamiliaTOList(List<FamiliaTO> familiaTOList) {
		this.familiaTOList = familiaTOList;
	}

	public List<TecnologiaTO> getTecnologiaTOList() {
		return tecnologiaTOList;
	}

	public void setTecnologiaTOList(List<TecnologiaTO> tecnologiaTOList) {
		this.tecnologiaTOList = tecnologiaTOList;
	}

	public List<PlanoTO> getPlanoTOList() {
		return planoTOList;
	}

	public void setPlanoTOList(List<PlanoTO> planoTOList) {
		this.planoTOList = planoTOList;
	}

	public List<PlataformaTO> getPlataformaTOList() {
		return plataformaTOList;
	}

	public void setPlataformaTOList(List<PlataformaTO> plataformaTOList) {
		this.plataformaTOList = plataformaTOList;
	}

	public List<SegmentoTO> getSegmentoTOList() {
		return segmentoTOList;
	}

	public void setSegmentoTOList(List<SegmentoTO> segmentoTOList) {
		this.segmentoTOList = segmentoTOList;
	}

	public List<TipoServicoTO> getTipoServicoTOList() {
		return tipoServicoTOList;
	}

	public void setTipoServicoTOList(List<TipoServicoTO> tipoServicoTOList) {
		this.tipoServicoTOList = tipoServicoTOList;
	}

	public List<ServicoFixaTO> getServicoFixaTOList() {
		return servicoFixaTOList;
	}

	public void setServicoFixaTOList(List<ServicoFixaTO> servicoFixaTOList) {
		this.servicoFixaTOList = servicoFixaTOList;
	}
}