package br.com.vivo.catalogoPRS.pageflows.param.produtos.tecnologias;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.AxisFault;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.TecnologiaForm;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaModeloPorTecnologiaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaModeloPorTecnologiaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaTecnologiaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaTecnologiaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaTecnologiaSimplesRequest;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaTecnologiaSimplesResponse;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.CriarTecnologiaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ExcluirListaTecnologiaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosBuscarListaModeloPorTecnologia;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosCriarTecnologia;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosCriarTecnologiaTecnologiaCriacao;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosExcluirListaTecnologia;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosExcluirListaTecnologiaListaTecnologiaExclusaoTecnologiaExclusao;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarListaModeloPorTecnologiaListaModeloPorTecnologiaModeloPorTecnologia;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarListaTecnologiaSimplesTecnologia;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarListaTecnologiaTecnologia;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.TecnologiaPortTypeProxy;



public class TecnologiasAction extends BaseMappingDispatchAction implements Serializable {	
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		TecnologiaForm tecnologiaForm = (TecnologiaForm) form;

		TecnologiaPortTypeProxy tecnologiaPortTypeProxy = new TecnologiaPortTypeProxy();
		BuscarListaTecnologiaRequest buscarListaTecnologiaRequest = new BuscarListaTecnologiaRequest();
		BuscarListaTecnologiaResponse buscarListaTecnologiaResponse = null;

		try {
			buscarListaTecnologiaResponse = tecnologiaPortTypeProxy.buscarListaTecnologia(buscarListaTecnologiaRequest,	this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, TecnologiasAction.class.getName(), ex.getMessage(), tecnologiaForm, response);
			return null;
		}
		
		ResultadoBuscarListaTecnologiaTecnologia[] tecnologiaList = null;
		if (buscarListaTecnologiaResponse != null) {
			tecnologiaList = buscarListaTecnologiaResponse.getResultadoBuscarListaTecnologia();
			
		}
		
		request.setAttribute("listaTecnologias", tecnologiaList);
		request.setAttribute("size_tecnologias", tecnologiaList.length);

		this.cleanHeader(response);

		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward addTecnologia(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
										
		TecnologiaForm tecnologiaForm = (TecnologiaForm)form;	
								
		CriarTecnologiaRequest criarTecnologiaRequest = new CriarTecnologiaRequest();
		ParametrosCriarTecnologia parametrosCriarTecnologia = new ParametrosCriarTecnologia();	
		ParametrosCriarTecnologiaTecnologiaCriacao parametrosCriarTecnologiaTecnologiaCriacao = new ParametrosCriarTecnologiaTecnologiaCriacao();		
		parametrosCriarTecnologiaTecnologiaCriacao.setNmTecnologia(tecnologiaForm.getNmTecnologia());
		parametrosCriarTecnologiaTecnologiaCriacao.setIdTecnologiaPai(tecnologiaForm.getIdTecnologiaPai());
		
		parametrosCriarTecnologia.setTecnologiaCriacao(parametrosCriarTecnologiaTecnologiaCriacao);
		criarTecnologiaRequest.setParametrosCriarTecnologia(parametrosCriarTecnologia);			
      	TecnologiaPortTypeProxy tecnologiaPortTypeProxy = new TecnologiaPortTypeProxy();	
		try {
			tecnologiaPortTypeProxy.criarTecnologia(criarTecnologiaRequest, this.getUserName(), this.getPassword());
			tecnologiaForm.setNmTecnologia("");
			tecnologiaForm.setIdTecnologiaPai(null);
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, TecnologiasAction.class.getName(), ex.getMessage(), tecnologiaForm, response );
			return null;
		}
		
		BuscarListaTecnologiaSimplesRequest buscarListaTecnologiaSimplesRequest = new BuscarListaTecnologiaSimplesRequest();
		BuscarListaTecnologiaSimplesResponse buscarListaTecnologiaSimplesResponse = null;		
		
		
		try {
			buscarListaTecnologiaSimplesResponse = tecnologiaPortTypeProxy.buscarListaTecnologiaSimples(buscarListaTecnologiaSimplesRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, TecnologiasAction.class.getName(), ex.getMessage(), tecnologiaForm, response );
			return null;
		}			
		
		ResultadoBuscarListaTecnologiaSimplesTecnologia[] tecnologiaList = null;
		if (buscarListaTecnologiaSimplesResponse.getResultadoBuscarListaTecnologiaSimples() != null) {
			tecnologiaList = buscarListaTecnologiaSimplesResponse.getResultadoBuscarListaTecnologiaSimples();			
		} 	
		
		request.setAttribute("listaTecnologias", tecnologiaList);
		request.setAttribute("size_tecnologias", tecnologiaList.length);

		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);				
	}
	
	public ActionForward popupApagarTecnologia(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
						
		String idTecnologiaString = (String) request.getParameter("id_tecnologia");
		request.setAttribute("id_tecnologia", idTecnologiaString);

		if (idTecnologiaString != null && !idTecnologiaString.trim().equals("")) {
			Long idTecnologia = Long.valueOf(idTecnologiaString);

			BuscarListaModeloPorTecnologiaRequest buscarListaModeloPorTecnologiaRequest = new BuscarListaModeloPorTecnologiaRequest();
			ParametrosBuscarListaModeloPorTecnologia parametrosBuscarListaModeloPorTecnologia = new ParametrosBuscarListaModeloPorTecnologia();
			parametrosBuscarListaModeloPorTecnologia.setIdTecnologia(idTecnologia);
			buscarListaModeloPorTecnologiaRequest.setParametrosBuscarListaModeloPorTecnologia(parametrosBuscarListaModeloPorTecnologia);

			TecnologiaPortTypeProxy tecnologiaPortTypeProxy = new TecnologiaPortTypeProxy();
			BuscarListaModeloPorTecnologiaResponse buscarListaModeloPorTecnologiaResponse = new BuscarListaModeloPorTecnologiaResponse();
			try {
				buscarListaModeloPorTecnologiaResponse = tecnologiaPortTypeProxy.buscarListaModeloPorTecnologia(buscarListaModeloPorTecnologiaRequest, this.getUserName(), this.getPassword());
			} catch (AxisFault ex) {
				ex.printStackTrace();
			}

			ResultadoBuscarListaModeloPorTecnologiaListaModeloPorTecnologiaModeloPorTecnologia[] modeloPorTecnologiaList = null;
			
			if (buscarListaModeloPorTecnologiaResponse.getResultadoBuscarListaModeloPorTecnologia() != null) {
				modeloPorTecnologiaList = buscarListaModeloPorTecnologiaResponse.getResultadoBuscarListaModeloPorTecnologia().getListaModeloPorTecnologia();
			}
			request.setAttribute("modelos_afetados", modeloPorTecnologiaList);
		}

		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
		
	public ActionForward apagarTecnologia(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		TecnologiaForm tecnologiaForm = (TecnologiaForm) form;
		Long idTecnologia = tecnologiaForm.getIdEntidade();

		ExcluirListaTecnologiaRequest excluirListaTecnologiaRequest = new ExcluirListaTecnologiaRequest();
		ParametrosExcluirListaTecnologia parametrosExcluirListaTecnologia = new ParametrosExcluirListaTecnologia();
		ParametrosExcluirListaTecnologiaListaTecnologiaExclusaoTecnologiaExclusao listaTecnologiaExclusao = new ParametrosExcluirListaTecnologiaListaTecnologiaExclusaoTecnologiaExclusao(idTecnologia, tecnologiaForm.getJustificativa());

		ParametrosExcluirListaTecnologiaListaTecnologiaExclusaoTecnologiaExclusao[] listaTecnologiaExclusaoArray = new ParametrosExcluirListaTecnologiaListaTecnologiaExclusaoTecnologiaExclusao[1];
		listaTecnologiaExclusaoArray[0] = listaTecnologiaExclusao;
		parametrosExcluirListaTecnologia.setListaTecnologiaExclusao(listaTecnologiaExclusaoArray);
		excluirListaTecnologiaRequest.setParametrosExcluirListaTecnologia(parametrosExcluirListaTecnologia);

		TecnologiaPortTypeProxy tecnologiaPortTypeProxy = new TecnologiaPortTypeProxy();
		
		if (tecnologiaForm.getJustificativa() != null && !tecnologiaForm.getJustificativa().trim().equalsIgnoreCase("")) {		
			listaTecnologiaExclusao.setJustificativa(tecnologiaForm.getJustificativa());
		}
		excluirListaTecnologiaRequest.setParametrosExcluirListaTecnologia(parametrosExcluirListaTecnologia);
		
		try {
			tecnologiaPortTypeProxy.excluirListaTecnologia(excluirListaTecnologiaRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, TecnologiasAction.class.getName(), ex.getMessage(),form, response);
			return null;
		}

		BuscarListaTecnologiaSimplesRequest buscarListaTecnologiaSimplesRequest = new BuscarListaTecnologiaSimplesRequest();
		BuscarListaTecnologiaSimplesResponse buscarListaTecnologiaSimplesResponse = null;				
		try {
			buscarListaTecnologiaSimplesResponse = tecnologiaPortTypeProxy.buscarListaTecnologiaSimples(buscarListaTecnologiaSimplesRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, TecnologiasAction.class.getName(), ex.getMessage(), tecnologiaForm, response );
			return null;
		}		
		
		ResultadoBuscarListaTecnologiaSimplesTecnologia[] tecnologiaList = null;
		if (buscarListaTecnologiaSimplesResponse != null) {
			tecnologiaList = buscarListaTecnologiaSimplesResponse.getResultadoBuscarListaTecnologiaSimples();
			
		}
		
		//List<ResultadoBuscarListaTecnologiaSimplesTecnologia> tecnologiaList = Arrays.asList(buscarListaTecnologiaSimplesResponse.getResultadoBuscarListaTecnologiaSimples());		
		request.setAttribute("listaTecnologias", tecnologiaList);
		request.setAttribute("size_tecnologias", tecnologiaList.length);

		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward pesquisar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		return mapping.findForward(SUCCESS);

	}
}

