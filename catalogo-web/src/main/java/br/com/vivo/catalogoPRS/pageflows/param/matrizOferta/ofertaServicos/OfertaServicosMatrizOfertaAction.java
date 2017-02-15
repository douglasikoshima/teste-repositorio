 package br.com.vivo.catalogoPRS.pageflows.param.matrizOferta.ofertaServicos;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.AxisFault;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.OfertaServicosMatrizOfertaForm;
import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;
import br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn;
import br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarListaGrupoServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarListaGrupoServicoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.GrupoServicoPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ParametrosListarGrupoServico;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ResultadoListarGrupoServicoCategoria;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.AlterarOfertaServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.AssociarServicoOfertaServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.BuscarDadosServicoOfertaServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.BuscarDadosServicoOfertaServicoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.BuscarListaOfertaServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.BuscarListaOfertaServicoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.BuscarListaServicoComOfertaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.BuscarListaServicoComOfertaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.CriarOfertaServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ExcluirOfertaServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ExcluirServicoOfertaServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.OfertaServicoPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ParametrosAlterarOfertaServico;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ParametrosAssociarServicoOfertaServico;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ParametrosBuscarDadosServicoOfertaServico;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ParametrosBuscarListaOfertaServico;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ParametrosBuscarListaServicoComOferta;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ParametrosCriarOfertaServico;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ParametrosExcluirOfertaServico;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ParametrosExcluirServicoOfertaServico;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ParametrosValidarServicoOfertaServico;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarDadosServicoOfertaServicoListaServicoOfertaServicoPlataformaServicoOfertaServicoPlataforma;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarDadosServicoOfertaServicoListaServicoOfertaServicoTecnologiaServicoOfertaServicoTecnologia;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ValidarServicoOfertaServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ValidarServicoOfertaServicoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.BuscarListaPlataformaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.BuscarListaPlataformaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.PlataformaPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.ResultadoBuscarListaPlataformaPlataforma;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaServicoParametrizacaoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaServicoParametrizacaoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaTipoServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaTipoServicoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarListaServicoParametrizacao;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaTipoServicoListaTipoServicoRetorno;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ServicoPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaTecnologiaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaTecnologiaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarListaTecnologiaTecnologia;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.TecnologiaPortTypeProxy;
import edu.emory.mathcs.backport.java.util.Arrays;

public class OfertaServicosMatrizOfertaAction extends BaseMappingDispatchAction implements Serializable {
	private static final long serialVersionUID = 1L;
	private String SUCCESS = "success";
	private String ERROR = "error";

	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward(SUCCESS);
	}

	public ActionForward pesquisarOfertaServicosMatriz(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		OfertaServicosMatrizOfertaForm ofertaServicosMatrizOfertaForm = (OfertaServicosMatrizOfertaForm)form;
		
		BuscarListaOfertaServicoRequest buscarListaOfertaServicoRequest = new BuscarListaOfertaServicoRequest();
		ParametrosBuscarListaOfertaServico parametrosBuscarListaOfertaServico = new ParametrosBuscarListaOfertaServico();
		BuscarListaOfertaServicoResponse buscarListaOfertaServicoResponse = new BuscarListaOfertaServicoResponse();
		OfertaServicoPortTypeProxy ofertaServicoPortTypeProxy = new OfertaServicoPortTypeProxy();
		PaginacaoIn paginacaoIn = new PaginacaoIn();
		
		if (ofertaServicosMatrizOfertaForm.getPaginaSolicitada() != null && ofertaServicosMatrizOfertaForm.getPaginaSolicitada() > 0) {
			paginacaoIn.setPaginaSolicitada(ofertaServicosMatrizOfertaForm.getPaginaSolicitada());
		}
		else {
			paginacaoIn.setPaginaSolicitada(1);
		}
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		if (ofertaServicosMatrizOfertaForm.getCdOfertaServico() != null && ofertaServicosMatrizOfertaForm.getCdOfertaServico().length() > 0) {
			parametrosBuscarListaOfertaServico.setCdOfertaServico(ofertaServicosMatrizOfertaForm.getCdOfertaServico());
		}
		if (ofertaServicosMatrizOfertaForm.getNmOfertaServico() != null && ofertaServicosMatrizOfertaForm.getNmOfertaServico().length() > 0) {
			parametrosBuscarListaOfertaServico.setNmOfertaServico(ofertaServicosMatrizOfertaForm.getNmOfertaServico());
		}
		if (ofertaServicosMatrizOfertaForm.getCdCodigo() != null && ofertaServicosMatrizOfertaForm.getCdCodigo().length() > 0) {
			parametrosBuscarListaOfertaServico.setCdCodigo(ofertaServicosMatrizOfertaForm.getCdCodigo());
		}
		if (ofertaServicosMatrizOfertaForm.getNmComercial() != null && ofertaServicosMatrizOfertaForm.getNmComercial().length() > 0) {
			parametrosBuscarListaOfertaServico.setNmComercial(ofertaServicosMatrizOfertaForm.getNmComercial());
		}
		
		parametrosBuscarListaOfertaServico.setPaginacaoIn(paginacaoIn);
		buscarListaOfertaServicoRequest.setParametrosBuscarListaOfertaServico(parametrosBuscarListaOfertaServico);

		try {
			buscarListaOfertaServicoResponse = ofertaServicoPortTypeProxy.buscarListaOfertaServico(buscarListaOfertaServicoRequest,this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, OfertaServicosMatrizOfertaAction.class.getName(), ex.getMessage(), ofertaServicosMatrizOfertaForm, response );
			return null;
		}
		
		PaginacaoOut paginacaoOut = buscarListaOfertaServicoResponse.getResultadoBuscarListaOfertaServico().getPaginacaoOut();
		if (paginacaoOut != null) {
			tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(),request);
		}
		
		List<ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico> listaOfertaServicos = Arrays.asList(buscarListaOfertaServicoResponse.getResultadoBuscarListaOfertaServico().getListaOfertaServico());
		
		if(listaOfertaServicos != null && listaOfertaServicos.size() > 0) {
			//request.setAttribute("listaOfertaServicos", listaOfertaServico);
			ofertaServicosMatrizOfertaForm.setListaOfertaServicos(listaOfertaServicos);
		}

		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward criarOfertaServicosMatriz(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		OfertaServicosMatrizOfertaForm ofertaServicosMatrizOfertaForm = (OfertaServicosMatrizOfertaForm)form;
		
		CriarOfertaServicoRequest criarOfertaServicoRequest = new CriarOfertaServicoRequest();
		ParametrosCriarOfertaServico parametrosCriarOfertaServico = new ParametrosCriarOfertaServico();
		OfertaServicoPortTypeProxy ofertaServicoPortTypeProxy = new OfertaServicoPortTypeProxy(); 
		
		parametrosCriarOfertaServico.setCdOfertaServico(ofertaServicosMatrizOfertaForm.getCdOfertaServico());
		parametrosCriarOfertaServico.setNmOfertaServico(ofertaServicosMatrizOfertaForm.getNmOfertaServico());
		parametrosCriarOfertaServico.setDsNota(ofertaServicosMatrizOfertaForm.getDsNota());

		criarOfertaServicoRequest.setParametrosCriarOfertaServico(parametrosCriarOfertaServico);
		
		try {
			ofertaServicoPortTypeProxy.criarOfertaServico(criarOfertaServicoRequest,this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, OfertaServicosMatrizOfertaAction.class.getName(), ex.getMessage(), ofertaServicosMatrizOfertaForm, response );
			return null;
		}
		return null;
	}

	public ActionForward listarServicosAssociados(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		OfertaServicosMatrizOfertaForm ofertaServicosMatrizOfertaForm = (OfertaServicosMatrizOfertaForm)form;
		
		BuscarListaServicoComOfertaRequest buscarListaServicoComOfertaRequest = new BuscarListaServicoComOfertaRequest();
		BuscarListaServicoComOfertaResponse buscarListaServicoComOfertaResponse = new BuscarListaServicoComOfertaResponse();
		ParametrosBuscarListaServicoComOferta parametrosBuscarListaServicoComOferta = new ParametrosBuscarListaServicoComOferta();
		OfertaServicoPortTypeProxy ofertaServicoPortTypeProxy = new OfertaServicoPortTypeProxy();
		PaginacaoIn paginacaoIn = new PaginacaoIn();

		if (ofertaServicosMatrizOfertaForm.getPaginaSolicitada() != null && ofertaServicosMatrizOfertaForm.getPaginaSolicitada() > 0) {
			paginacaoIn.setPaginaSolicitada(ofertaServicosMatrizOfertaForm.getPaginaSolicitada());
		}
		else {
			paginacaoIn.setPaginaSolicitada(1);
		}
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		if (request.getParameter("id_ofertaServico") != null && request.getParameter("id_ofertaServico").trim().length() > 0) {
			parametrosBuscarListaServicoComOferta.setIdOfertaServico((Long.valueOf(request.getParameter("id_ofertaServico"))));
			request.setAttribute("id_ofertaServico", request.getParameter("id_ofertaServico"));
		}
		parametrosBuscarListaServicoComOferta.setPaginacaoIn(paginacaoIn);
		buscarListaServicoComOfertaRequest.setParametrosBuscarListaServicoComOferta(parametrosBuscarListaServicoComOferta);

		try {
			buscarListaServicoComOfertaResponse = ofertaServicoPortTypeProxy.buscarListaServicoComOferta(buscarListaServicoComOfertaRequest,this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, OfertaServicosMatrizOfertaAction.class.getName(), ex.getMessage(), ofertaServicosMatrizOfertaForm, response );
			return null;
		}

		List<ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta> servicoComOfertaList = Arrays.asList(buscarListaServicoComOfertaResponse.getResultadoBuscarListaServicoComOferta().getListaServicoComOferta());

		PaginacaoOut paginacaoOut = buscarListaServicoComOfertaResponse.getResultadoBuscarListaServicoComOferta().getPaginacaoOut();
		if (paginacaoOut != null) {
			tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(),request);
		}

		//request.setAttribute("servicos", servicoComOfertaList);
		if(servicoComOfertaList != null && servicoComOfertaList.size() > 0) {
			ofertaServicosMatrizOfertaForm.setServicos(servicoComOfertaList);
		}

		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward abrirAlterarOfertaServicosMatriz(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		if (request.getParameter("id_oferta_servico") != null && request.getParameter("id_oferta_servico").trim().length() > 0) {
			request.setAttribute("idOfertaServico", request.getParameter("id_oferta_servico"));
		}
		if(request.getParameter("cd_oferta_servico") != null && request.getParameter("cd_oferta_servico").trim().length() > 0) {
			request.setAttribute("cdOfertaServico", request.getParameter("cd_oferta_servico"));
		}
		if(request.getParameter("nm_oferta_servico") != null && request.getParameter("nm_oferta_servico").trim().length() > 0) {
			request.setAttribute("nmOfertaServico", request.getParameter("nm_oferta_servico"));
		}
		if(request.getParameter("ds_nota_servico") != null && request.getParameter("ds_nota_servico").trim().length() > 0) {
			request.setAttribute("dsNota", request.getParameter("ds_nota_servico"));
		}
		//String cdOfertaServico = request.getParameter("cd_oferta_servico");
		//String nmOfertaServico = request.getParameter("nm_oferta_servico");
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward alterarOfertaServicosMatriz(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		OfertaServicosMatrizOfertaForm ofertaServicosMatrizOfertaForm = (OfertaServicosMatrizOfertaForm)form;
		
		AlterarOfertaServicoRequest alterarOfertaServicoRequest = new AlterarOfertaServicoRequest();
		ParametrosAlterarOfertaServico parametrosAlterarOfertaServico = new ParametrosAlterarOfertaServico();
		OfertaServicoPortTypeProxy ofertaServicoPortTypeProxy = new OfertaServicoPortTypeProxy();
		
		parametrosAlterarOfertaServico.setIdOfertaServico(ofertaServicosMatrizOfertaForm.getIdOfertaServico());
		parametrosAlterarOfertaServico.setNmOfertaServico(ofertaServicosMatrizOfertaForm.getNmOfertaServico());
		parametrosAlterarOfertaServico.setDsNota(ofertaServicosMatrizOfertaForm.getDsNota());

		alterarOfertaServicoRequest.setParametrosAlterarOfertaServico(parametrosAlterarOfertaServico);
		
		try {
			ofertaServicoPortTypeProxy.alterarOfertaServico(alterarOfertaServicoRequest,this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, OfertaServicosMatrizOfertaAction.class.getName(), ex.getMessage(), ofertaServicosMatrizOfertaForm, response );
			return null;
		}

		return this.pesquisarOfertaServicosMatriz(mapping, form, request, response);
	}

	public ActionForward abrirPopupExcluirOfertaServico(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		OfertaServicosMatrizOfertaForm ofertaServicosMatrizOfertaForm = (OfertaServicosMatrizOfertaForm)form;
		
		BuscarListaServicoComOfertaRequest buscarListaServicoComOfertaRequest = new BuscarListaServicoComOfertaRequest();
		BuscarListaServicoComOfertaResponse buscarListaServicoComOfertaResponse = new BuscarListaServicoComOfertaResponse();
		ParametrosBuscarListaServicoComOferta parametrosBuscarListaServicoComOferta = new ParametrosBuscarListaServicoComOferta();
		OfertaServicoPortTypeProxy ofertaServicoPortTypeProxy = new OfertaServicoPortTypeProxy();
		PaginacaoIn paginacaoIn = new PaginacaoIn();
		
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPaginaNosPopups());
		if(ofertaServicosMatrizOfertaForm.getPaginaSolicitada() != null && ofertaServicosMatrizOfertaForm.getPaginaSolicitada() > 0) {
			paginacaoIn.setPaginaSolicitada(ofertaServicosMatrizOfertaForm.getPaginaSolicitada());
		}
		else{
			paginacaoIn.setPaginaSolicitada(1);
		}
		if (request.getParameter("id_ofertaServico") != null && request.getParameter("id_ofertaServico").trim().length() > 0) {
			parametrosBuscarListaServicoComOferta.setIdOfertaServico((Long.valueOf(request.getParameter("id_ofertaServico"))));
			request.setAttribute("idOfertaServico", request.getParameter("id_ofertaServico"));
		}
		
		parametrosBuscarListaServicoComOferta.setPaginacaoIn(paginacaoIn);
		buscarListaServicoComOfertaRequest.setParametrosBuscarListaServicoComOferta(parametrosBuscarListaServicoComOferta);
		
		try {
			buscarListaServicoComOfertaResponse = ofertaServicoPortTypeProxy.buscarListaServicoComOferta(buscarListaServicoComOfertaRequest,this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, OfertaServicosMatrizOfertaAction.class.getName(), ex.getMessage(), ofertaServicosMatrizOfertaForm, response );
			return null;
		}		

		List<ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta> servicoComOfertaList = Arrays.asList(buscarListaServicoComOfertaResponse.getResultadoBuscarListaServicoComOferta().getListaServicoComOferta());
		int totalServicosAssociados = servicoComOfertaList.size();

		request.setAttribute("totalServicosAssociados", totalServicosAssociados);
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward excluirOfertaServico(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		OfertaServicosMatrizOfertaForm ofertaServicosMatrizOfertaForm = (OfertaServicosMatrizOfertaForm)form;
		
		ExcluirOfertaServicoRequest excluirOfertaServicoRequest = new ExcluirOfertaServicoRequest();
		ParametrosExcluirOfertaServico parametrosExcluirOfertaServico = new ParametrosExcluirOfertaServico();
		OfertaServicoPortTypeProxy ofertaServicoPortTypeProxy = new OfertaServicoPortTypeProxy();
		
		parametrosExcluirOfertaServico.setIdOfertaServico(ofertaServicosMatrizOfertaForm.getIdOfertaServico());
		excluirOfertaServicoRequest.setParametrosExcluirOfertaServico(parametrosExcluirOfertaServico);
		
		try {
			ofertaServicoPortTypeProxy.excluirOfertaServico(excluirOfertaServicoRequest,this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, OfertaServicosMatrizOfertaAction.class.getName(), ex.getMessage(), ofertaServicosMatrizOfertaForm, response );
			return null;
		}	
		
		try {
			response.setContentType("text/javascript");
			PrintWriter out = response.getWriter();
			out.println("$('botao_listar_ofertas_servico').onclick();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward abrirServicosAssociadosOfertaServicosMatriz(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		OfertaServicosMatrizOfertaForm ofertaServicosMatrizOfertaForm = (OfertaServicosMatrizOfertaForm)form;
		
		BuscarListaServicoComOfertaRequest buscarListaServicoComOfertaRequest = new BuscarListaServicoComOfertaRequest();
		BuscarListaServicoComOfertaResponse buscarListaServicoComOfertaResponse = new BuscarListaServicoComOfertaResponse();
		ParametrosBuscarListaServicoComOferta parametrosBuscarListaServicoComOferta = new ParametrosBuscarListaServicoComOferta();
		OfertaServicoPortTypeProxy ofertaServicoPortTypeProxy = new OfertaServicoPortTypeProxy();
		PaginacaoIn paginacaoIn = new PaginacaoIn();
		
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		if (ofertaServicosMatrizOfertaForm.getPaginaSolicitada() != null && ofertaServicosMatrizOfertaForm.getPaginaSolicitada() > 0) {
			paginacaoIn.setPaginaSolicitada(ofertaServicosMatrizOfertaForm.getPaginaSolicitada());
		}
		else {
			paginacaoIn.setPaginaSolicitada(1);
		}
		if(request.getParameter("id_oferta_servico") != null && request.getParameter("id_oferta_servico").trim().length() > 0) {
			parametrosBuscarListaServicoComOferta.setIdOfertaServico(Long.valueOf(request.getParameter("id_oferta_servico")));
			request.setAttribute("idOfertaServico", request.getParameter("id_oferta_servico"));
		}
		
		parametrosBuscarListaServicoComOferta.setPaginacaoIn(paginacaoIn);
		buscarListaServicoComOfertaRequest.setParametrosBuscarListaServicoComOferta(parametrosBuscarListaServicoComOferta);
		
		try {
			buscarListaServicoComOfertaResponse = ofertaServicoPortTypeProxy.buscarListaServicoComOferta(buscarListaServicoComOfertaRequest,this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, OfertaServicosMatrizOfertaAction.class.getName(), ex.getMessage(), ofertaServicosMatrizOfertaForm, response );
			return null;
		}
		
		PaginacaoOut paginacaoOut = buscarListaServicoComOfertaResponse.getResultadoBuscarListaServicoComOferta().getPaginacaoOut();
		if (paginacaoOut != null) {
			tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(),request);
		}	
		
		List<ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta> servicoComOfertaList = Arrays.asList(buscarListaServicoComOfertaResponse.getResultadoBuscarListaServicoComOferta().getListaServicoComOferta());
		
		if(servicoComOfertaList != null && servicoComOfertaList.size() > 0) {
			//request.setAttribute("listaServicosAssociadosOfertaServicos", servicoComOfertaList);
			ofertaServicosMatrizOfertaForm.setListaServicosAssociadosOfertaServicos(servicoComOfertaList);
		}
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward abrirPopupExcluirAssociacaoServico(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		OfertaServicosMatrizOfertaForm ofertaServicosMatrizOfertaForm = (OfertaServicosMatrizOfertaForm)form;
		
		BuscarListaServicoComOfertaRequest buscarListaServicoComOfertaRequest = new BuscarListaServicoComOfertaRequest();
		BuscarListaServicoComOfertaResponse buscarListaServicoComOfertaResponse = new BuscarListaServicoComOfertaResponse();
		ParametrosBuscarListaServicoComOferta parametrosBuscarListaServicoComOferta = new ParametrosBuscarListaServicoComOferta();
		OfertaServicoPortTypeProxy ofertaServicoPortTypeProxy = new OfertaServicoPortTypeProxy();
		PaginacaoIn paginacaoIn = new PaginacaoIn();
		if (ofertaServicosMatrizOfertaForm.getPaginaSolicitada() != null && ofertaServicosMatrizOfertaForm.getPaginaSolicitada() > 0) {
			paginacaoIn.setPaginaSolicitada(ofertaServicosMatrizOfertaForm.getPaginaSolicitada());
		}
		else {
			paginacaoIn.setPaginaSolicitada(1);
		}
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPaginaNosPopups());
		if (request.getParameter("id_servicoOfertaServico") != null	&& request.getParameter("id_servicoOfertaServico").trim().length() > 0) {
			parametrosBuscarListaServicoComOferta.setIdOfertaServico((Long.valueOf(request.getParameter("id_servicoOfertaServico"))));
			request.setAttribute("idServicoOfertaServico", request.getParameter("id_servicoOfertaServico"));
		}
		
		parametrosBuscarListaServicoComOferta.setPaginacaoIn(paginacaoIn);
		buscarListaServicoComOfertaRequest.setParametrosBuscarListaServicoComOferta(parametrosBuscarListaServicoComOferta);
		
		try {
			buscarListaServicoComOfertaResponse = ofertaServicoPortTypeProxy.buscarListaServicoComOferta(buscarListaServicoComOfertaRequest,this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, OfertaServicosMatrizOfertaAction.class.getName(), ex.getMessage(), ofertaServicosMatrizOfertaForm, response );
			return null;
		}		

		List<ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta> servicoComOfertaList = Arrays.asList(buscarListaServicoComOfertaResponse.getResultadoBuscarListaServicoComOferta().getListaServicoComOferta());
		request.setAttribute("listaServicosAssociados", servicoComOfertaList);
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward excluirServicoOfertaServico(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		OfertaServicosMatrizOfertaForm ofertaServicosMatrizOfertaForm = (OfertaServicosMatrizOfertaForm)form;
		
		ExcluirServicoOfertaServicoRequest excluirServicoOfertaServicoRequest = new ExcluirServicoOfertaServicoRequest();
		ParametrosExcluirServicoOfertaServico parametrosExcluirServicoOfertaServico = new ParametrosExcluirServicoOfertaServico();
		OfertaServicoPortTypeProxy ofertaServicoPortTypeProxy = new OfertaServicoPortTypeProxy();
		
		parametrosExcluirServicoOfertaServico.setIdServicoOfertaServico(ofertaServicosMatrizOfertaForm.getIdServicoOfertaServico());
		excluirServicoOfertaServicoRequest.setParametrosExcluirServicoOfertaServico(parametrosExcluirServicoOfertaServico);
		
		try {
			ofertaServicoPortTypeProxy.excluirServicoOfertaServico(excluirServicoOfertaServicoRequest,this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, OfertaServicosMatrizOfertaAction.class.getName(), ex.getMessage(), ofertaServicosMatrizOfertaForm, response );
			return null;
		}

		try {
			response.setContentType("text/javascript");
			PrintWriter out = response.getWriter();
			out.println("$('botao_alterar_servicos').onclick();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward abrirPopupNovaAssociacao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		OfertaServicosMatrizOfertaForm ofertaServicosMatrizOfertaForm = (OfertaServicosMatrizOfertaForm)form;
		
		BuscarDadosServicoOfertaServicoRequest buscarDadosServicoOfertaServicoRequest = new BuscarDadosServicoOfertaServicoRequest();
		BuscarDadosServicoOfertaServicoResponse buscarDadosServicoOfertaServicoResponse = new BuscarDadosServicoOfertaServicoResponse();
		ParametrosBuscarDadosServicoOfertaServico parametrosBuscarDadosServicoOfertaServico = new ParametrosBuscarDadosServicoOfertaServico();
		OfertaServicoPortTypeProxy ofertaServicoPortTypeProxy = new OfertaServicoPortTypeProxy();
		
		parametrosBuscarDadosServicoOfertaServico.setIdOfertaServico(Long.valueOf(request.getParameter("id_ofertaServico")));
		buscarDadosServicoOfertaServicoRequest.setParametrosBuscarDadosServicoOfertaServico(parametrosBuscarDadosServicoOfertaServico);
		
		
		try {
			buscarDadosServicoOfertaServicoResponse = ofertaServicoPortTypeProxy.buscarDadosServicoOfertaServico(buscarDadosServicoOfertaServicoRequest,this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, OfertaServicosMatrizOfertaAction.class.getName(), ex.getMessage(), ofertaServicosMatrizOfertaForm, response );
			return null;
		}
		

		List<ResultadoBuscarDadosServicoOfertaServicoListaServicoOfertaServicoPlataformaServicoOfertaServicoPlataforma> servicoOfertaServicoPlataformaList = Arrays.asList(buscarDadosServicoOfertaServicoResponse.getResultadoBuscarDadosServicoOfertaServico().getListaServicoOfertaServicoPlataforma());
		List<ResultadoBuscarDadosServicoOfertaServicoListaServicoOfertaServicoTecnologiaServicoOfertaServicoTecnologia> servicoOfertaServicoTecnologiaList = Arrays.asList(buscarDadosServicoOfertaServicoResponse.getResultadoBuscarDadosServicoOfertaServico().getListaServicoOfertaServicoTecnologia());

		if (servicoOfertaServicoPlataformaList == null || servicoOfertaServicoPlataformaList.isEmpty()) {
			BuscarListaPlataformaRequest buscarListaPlataformaRequest = new BuscarListaPlataformaRequest();
			BuscarListaPlataformaResponse buscarListaPlataformaResponse = new BuscarListaPlataformaResponse();
			PlataformaPortTypeProxy plataformaPortTypeProxy = new PlataformaPortTypeProxy();
			
			try {
				buscarListaPlataformaResponse = plataformaPortTypeProxy.buscarListaPlataforma(buscarListaPlataformaRequest,this.getUserName(),this.getPassword());
			} catch (AxisFault ex) {		
				this.AxisFaultExceptionHandler(ex, OfertaServicosMatrizOfertaAction.class.getName(), ex.getMessage(), ofertaServicosMatrizOfertaForm, response );
				return null;
			}			

			List<ResultadoBuscarListaPlataformaPlataforma> plataformaList = Arrays.asList(buscarListaPlataformaResponse.getResultadoBuscarListaPlataforma());
			request.setAttribute("plataformas", plataformaList);
		} 
		else {
			request.setAttribute("plataformas", servicoOfertaServicoPlataformaList);
		}
		
		if (servicoOfertaServicoTecnologiaList == null || servicoOfertaServicoTecnologiaList.isEmpty()) {
			BuscarListaTecnologiaRequest buscarListaTecnologiaRequest = new BuscarListaTecnologiaRequest();
			BuscarListaTecnologiaResponse buscarListaTecnologiaResponse = new BuscarListaTecnologiaResponse();
			TecnologiaPortTypeProxy tecnologiaPortTypeProxy = new TecnologiaPortTypeProxy();

			try {
				buscarListaTecnologiaResponse = tecnologiaPortTypeProxy.buscarListaTecnologia(buscarListaTecnologiaRequest,this.getUserName(),this.getPassword());
			} catch (AxisFault ex) {		
				this.AxisFaultExceptionHandler(ex, OfertaServicosMatrizOfertaAction.class.getName(), ex.getMessage(), ofertaServicosMatrizOfertaForm, response );
				return null;
			}
			
			List<ResultadoBuscarListaTecnologiaTecnologia> tecnologiaList = Arrays.asList(buscarListaTecnologiaResponse.getResultadoBuscarListaTecnologia());
			request.setAttribute("listaTecnologia", tecnologiaList);
		} 
		else {
			request.setAttribute("listaTecnologia", servicoOfertaServicoTecnologiaList);
		}
		
		BuscarListaGrupoServicoRequest buscarListaGrupoServicoRequest = new BuscarListaGrupoServicoRequest();
		BuscarListaGrupoServicoResponse buscarListaGrupoServicoResponse = new BuscarListaGrupoServicoResponse();
		ParametrosListarGrupoServico parametrosListarGrupoServico = new ParametrosListarGrupoServico();
		GrupoServicoPortTypeProxy grupoServicoPortTypeProxy = new GrupoServicoPortTypeProxy();
		
		parametrosListarGrupoServico.setIndCatalogo(1);
		buscarListaGrupoServicoRequest.setParametrosListarGrupoServico(parametrosListarGrupoServico);
		
		try {
			buscarListaGrupoServicoResponse = grupoServicoPortTypeProxy.buscarListaGrupoServico(buscarListaGrupoServicoRequest,this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, OfertaServicosMatrizOfertaAction.class.getName(), ex.getMessage(), ofertaServicosMatrizOfertaForm, response );
			return null;
		}		

		List<ResultadoListarGrupoServicoCategoria> categoriaList = Arrays.asList(buscarListaGrupoServicoResponse.getResultadoListarGrupoServico());
		request.setAttribute("categoria", categoriaList);

		BuscarListaTipoServicoRequest buscarListaTipoServicoRequest = new BuscarListaTipoServicoRequest();
		BuscarListaTipoServicoResponse buscarListaTipoServicoResponse = new BuscarListaTipoServicoResponse();
		ServicoPortTypeProxy servicoPortTypeProxy = new ServicoPortTypeProxy();
		
		try {
			buscarListaTipoServicoResponse = servicoPortTypeProxy.buscarListaTipoServico(buscarListaTipoServicoRequest,this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, OfertaServicosMatrizOfertaAction.class.getName(), ex.getMessage(), ofertaServicosMatrizOfertaForm, response );
			return null;
		}
		
		
		List<ResultadoBuscarListaTipoServicoListaTipoServicoRetorno> retornoList = Arrays.asList(buscarListaTipoServicoResponse.getResultadoBuscarListaTipoServico().getListaTipoServico());
		request.setAttribute("tipoServico", retornoList);
		if (request.getParameter("id_ofertaServico") != null && request.getParameter("id_ofertaServico").trim().length() > 0) {
			request.setAttribute("idOfertaServico", request.getParameter("id_ofertaServico"));
		}
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward pesquisarServicosDisponiveisParaAssociacao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		OfertaServicosMatrizOfertaForm ofertaServicosMatrizOfertaForm = (OfertaServicosMatrizOfertaForm)form;
		
		BuscarListaServicoParametrizacaoRequest buscarListaServicoParametrizacaoRequest = new BuscarListaServicoParametrizacaoRequest();
		BuscarListaServicoParametrizacaoResponse buscarListaServicoParametrizacaoResponse = new BuscarListaServicoParametrizacaoResponse();
		ParametrosBuscarListaServicoParametrizacao parametrosBuscarListaServicoParametrizacao = new ParametrosBuscarListaServicoParametrizacao();
		br.com.vivo.catalogoPRS.ws.catalogoServico.sn.PaginacaoIn paginacaoIn = new br.com.vivo.catalogoPRS.ws.catalogoServico.sn.PaginacaoIn();
		ServicoPortTypeProxy servicoPortTypeProxy = new ServicoPortTypeProxy();
		
		if (ofertaServicosMatrizOfertaForm.getPaginaSolicitada() != null && ofertaServicosMatrizOfertaForm.getPaginaSolicitada() > 0) {
			paginacaoIn.setPaginaSolicitada(ofertaServicosMatrizOfertaForm.getPaginaSolicitada());
		}
		else {
			paginacaoIn.setPaginaSolicitada(1);
		}
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());

		if (ofertaServicosMatrizOfertaForm.getIdPlataforma() != null && ofertaServicosMatrizOfertaForm.getIdPlataforma() > 0) {
			parametrosBuscarListaServicoParametrizacao.setIdPlataforma(ofertaServicosMatrizOfertaForm.getIdPlataforma());
		}
		if (ofertaServicosMatrizOfertaForm.getIdTecnologia() != null && ofertaServicosMatrizOfertaForm.getIdTecnologia() > 0) {
			parametrosBuscarListaServicoParametrizacao.setIdTecnologia(ofertaServicosMatrizOfertaForm.getIdTecnologia());
		}
		if (ofertaServicosMatrizOfertaForm.getIdCategoria() != null && ofertaServicosMatrizOfertaForm.getIdCategoria() > 0) {
			parametrosBuscarListaServicoParametrizacao.setIdCategoriaCatalogo(ofertaServicosMatrizOfertaForm.getIdCategoria());
		}
		if (ofertaServicosMatrizOfertaForm.getIdTipoServico() != null && ofertaServicosMatrizOfertaForm.getIdTipoServico() > 0) {
			parametrosBuscarListaServicoParametrizacao.setIdTpServico(ofertaServicosMatrizOfertaForm.getIdTipoServico());
		}
		if (ofertaServicosMatrizOfertaForm.getNmServico() != null && ofertaServicosMatrizOfertaForm.getNmServico().trim().length() > 0) {
			parametrosBuscarListaServicoParametrizacao.setNmServico(ofertaServicosMatrizOfertaForm.getNmServico());
		}
		if (ofertaServicosMatrizOfertaForm.getCdServico() != null && ofertaServicosMatrizOfertaForm.getCdServico().trim().length() > 0) {
			parametrosBuscarListaServicoParametrizacao.setCdServico(ofertaServicosMatrizOfertaForm.getCdServico());
		}
		parametrosBuscarListaServicoParametrizacao.setPaginacaoIn(paginacaoIn);
		buscarListaServicoParametrizacaoRequest.setParametrosBuscarListaServicoParametrizacao(parametrosBuscarListaServicoParametrizacao);
		
		
		try {
			buscarListaServicoParametrizacaoResponse = servicoPortTypeProxy.buscarListaServicoParametrizacao(buscarListaServicoParametrizacaoRequest,this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, OfertaServicosMatrizOfertaAction.class.getName(), ex.getMessage(), ofertaServicosMatrizOfertaForm, response );
			return null;
		}
		
		
		List<ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao> servicosParaAssociar 
		= Arrays.asList(buscarListaServicoParametrizacaoResponse.getResultadoBuscarListaServicoParametrizacao().getListaBuscarListaServicoParametrizacao());
		
		br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoOut paginacaoOut = buscarListaServicoParametrizacaoResponse.getResultadoBuscarListaServicoParametrizacao().getPaginacaoOut();
		if (paginacaoOut != null) {
			tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(),request);
		}
		ofertaServicosMatrizOfertaForm.setServicosParaAssociar(servicosParaAssociar);
		
		//request.setAttribute("servicosParaAssociar", servicosParaAssociar);
		if (request.getParameter("id_ofertaServico") != null && request.getParameter("id_ofertaServico").trim().length() > 0) {
			request.setAttribute("idOfertaServico", request.getParameter("id_ofertaServico"));
		}else{
			ofertaServicosMatrizOfertaForm.setIdOfertaServico(Long.parseLong(request.getParameter("idOfertaServico")));
			request.setAttribute("idOfertaServico", request.getParameter("idOfertaServico"));
			request.setAttribute("id_ofertaServico", request.getParameter("idOfertaServico"));
		}
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward efetivarAssociasaoServicoOfertaServico(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		 request.getSession().removeAttribute("cdOfertaServico");
		 request.getSession().removeAttribute("idOfertaServico");
		 request.getSession().removeAttribute("listaIdServico");

		OfertaServicosMatrizOfertaForm ofertaServicosMatrizOfertaForm = (OfertaServicosMatrizOfertaForm)form;
		request.getParameter("idOfertaServico");
		AssociarServicoOfertaServicoRequest associarServicoOfertaServicoRequest = new AssociarServicoOfertaServicoRequest();
		ParametrosAssociarServicoOfertaServico parametrosAssociarServicoOfertaServico = new ParametrosAssociarServicoOfertaServico();
		OfertaServicoPortTypeProxy ofertaServicoPortTypeProxy = new OfertaServicoPortTypeProxy();
		
		if(ofertaServicosMatrizOfertaForm.getIdOfertaServico() != null && ofertaServicosMatrizOfertaForm.getIdOfertaServico() > 0) {
			parametrosAssociarServicoOfertaServico.setIdOfertaServico(ofertaServicosMatrizOfertaForm.getIdOfertaServico());
		}
		
		if (ofertaServicosMatrizOfertaForm.getListaIdServico() != null && !ofertaServicosMatrizOfertaForm.getListaIdServico().equals("")) {
			
			String[] idsServico = ofertaServicosMatrizOfertaForm.getListaIdServico().split(",");
			int count = idsServico.length;
			long[] listaIdServicos = new long[count];
			
			for (int i=0;i < count;i++) {
				listaIdServicos[i] = Long.parseLong(idsServico[i]);
			}
			parametrosAssociarServicoOfertaServico.setListaIdServico(listaIdServicos);
		}
		
		associarServicoOfertaServicoRequest.setParametrosAssociarServicoOfertaServico(parametrosAssociarServicoOfertaServico);
		
		try {
			ofertaServicoPortTypeProxy.associarServicoOfertaServico(associarServicoOfertaServicoRequest,this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, OfertaServicosMatrizOfertaAction.class.getName(), ex.getMessage(), ofertaServicosMatrizOfertaForm, response );
			return mapping.findForward(ERROR);
		}

		try {
			response.setContentType("text/javascript");
			PrintWriter out = response.getWriter();
			out.println("$('botao_alterar_servicos').onclick();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.cleanHeader(response);
		return this.pesquisarOfertaServicosMatriz(mapping, form, request, response);
	}
	
	public ActionForward validarServicoOfertaServico(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws CatalogoPRSException, Exception {
		
		request.getSession().removeAttribute("servicoCompativel1");
		request.getSession().removeAttribute("servicoCompativel2"); 
		
		OfertaServicosMatrizOfertaForm ofertaServicosMatrizOfertaForm = (OfertaServicosMatrizOfertaForm)form; 			
							
		ValidarServicoOfertaServicoRequest validarServicoOfertaServicoRequest = new ValidarServicoOfertaServicoRequest();
		ValidarServicoOfertaServicoResponse validarServicoOfertaServicoResponse = new ValidarServicoOfertaServicoResponse();
		ParametrosValidarServicoOfertaServico parametrosValidarServicoOfertaServico = new ParametrosValidarServicoOfertaServico();
		OfertaServicoPortTypeProxy ofertaServicoPortTypeProxy = new OfertaServicoPortTypeProxy();
		
		if(ofertaServicosMatrizOfertaForm.getIdOfertaServico() != null) {
			parametrosValidarServicoOfertaServico.setIdOfertaServico(ofertaServicosMatrizOfertaForm.getIdOfertaServico());
			
		}
		String listaIdServico = "";
		if(ofertaServicosMatrizOfertaForm.getIdsServico() !=null && ofertaServicosMatrizOfertaForm.getIdsServico().length > 0) {
			for(Long idServico : ofertaServicosMatrizOfertaForm.getIdsServico()){			
				listaIdServico = listaIdServico + "," + idServico;			
			}
			listaIdServico = listaIdServico.substring(1);
			parametrosValidarServicoOfertaServico.setListaIdServico(listaIdServico);
		}else {
			CatalogoPRSException ex = new CatalogoPRSException("Por favor, selecione um ou mais serviços a associar.");
			this.CatalogoPRSExceptionHandler(ex, OfertaServicosMatrizOfertaAction.class.getName(), response);
			return null;
		}
		validarServicoOfertaServicoRequest.setParametrosValidarServicoOfertaServico(parametrosValidarServicoOfertaServico);

		try {
			validarServicoOfertaServicoResponse = ofertaServicoPortTypeProxy.validarServicoOfertaServico(validarServicoOfertaServicoRequest,this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, OfertaServicosMatrizOfertaAction.class.getName(), ex.getMessage(), ofertaServicosMatrizOfertaForm, response );
			return null;
		}
		
		
		String indExclusividade = validarServicoOfertaServicoResponse.getResultadoValidarServicoOfertaServico().getIndExclusividade();
		
		if(indExclusividade.equals("0")) {
			PrintWriter writer = null;
			try {
				response.setContentType("text/javascript");
				writer = response.getWriter();
				request.getSession().setAttribute("idOfertaServico", ofertaServicosMatrizOfertaForm.getIdOfertaServico().toString());
				request.getSession().setAttribute("listaIdServico", listaIdServico);
				if(ofertaServicosMatrizOfertaForm.getCdOfertaServico() != null && ofertaServicosMatrizOfertaForm.getCdOfertaServico().trim().length() > 0) {
					request.getSession().setAttribute("cdOfertaServico", ofertaServicosMatrizOfertaForm.getCdOfertaServico());
				}				
				writer.println("$('idLinkPopUpConfirm').onclick();");
				writer.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				writer.close();
			}
		}else {
			String [] servicosIncompativeis = new String [1];
			servicosIncompativeis = indExclusividade.split(",");
			request.getSession().setAttribute("servicoIncompativel1", servicosIncompativeis[0]);
			request.getSession().setAttribute("servicoIncompativel2", servicosIncompativeis[1]);
			PrintWriter writer = null;
			try {
				response.setContentType("text/javascript");
				writer = response.getWriter();
				writer.println("$('idLinkPopUpError').onclick();");
				writer.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				writer.close();
			}
		}               

		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward incompatibilidadeAssociacaoServicoOfertaServico(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward confirmAssociacaoServicoOfertaServico(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward(SUCCESS);
	}
}