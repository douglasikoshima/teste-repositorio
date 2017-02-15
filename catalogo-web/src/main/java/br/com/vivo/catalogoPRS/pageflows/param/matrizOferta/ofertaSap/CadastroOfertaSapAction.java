package br.com.vivo.catalogoPRS.pageflows.param.matrizOferta.ofertaSap;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.AxisFault;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.indracompany.catalogo.datalayer.Uf;

import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.OfertaSapForm;
import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;
import br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn;
import br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmMatrizRequest;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmMatrizResponse;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.MatrizOfertaPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametroNomeMatriz;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoListaNomeMatrizListaNomeMatriz;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.mc.geral.ErroInfo;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.AlterarOfertaSapRequest;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.BuscarListaComposicaoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.BuscarListaComposicaoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.BuscarListaOfertaSapRequest;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.BuscarListaOfertaSapResponse;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.BuscarListaPlanosSemOfertaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.BuscarListaPlanosSemOfertaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.CriarComposicaoOfertaSapRequest;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.CriarComposicaoOfertaSapResponse;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.CriarNovaOfertaSapRequest;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.CriarNovaOfertaSapResponse;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.DeletarOfertaSapRequest;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ExcluirComposicaoOfertaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.OfertaSapPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.OfertaSapService;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosAlterarOfertaSap;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosBuscarListaComposicao;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosBuscarListaOfertaSap;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosBuscarListaPlanosSemOferta;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosCriarComposicaoOferta;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosCriarNovaOfertaSap;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosDeletarOfertaSap;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosExcluirComposicaoOferta;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosVerificarAssociacaoOfertaSap;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosVerificarExistenciaComposicao;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ResultadoBuscarListaComposicaoListaComposicaoComposicao;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ResultadoBuscarListaOfertaSapListaOfertaSapOfertaSap;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ResultadoCriarComposicaoOferta;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.VerificarAssociacaoOfertaSapRequest;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.VerificarAssociacaoOfertaSapResponse;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.VerificarExistenciaComposicaoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.VerificarExistenciaComposicaoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.BuscarListaNomeOfertaServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.BuscarListaNomeOfertaServicoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.OfertaServicoPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarListaNmOfertaServicoListaNomeOfertaServicoOfertaServico;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.BuscarListaPlataformaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.BuscarListaPlataformaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.PlataformaPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.ResultadoBuscarListaPlataformaPlataforma;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaTecnologiaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaTecnologiaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarListaTecnologiaTecnologia;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.TecnologiaPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn.BuscarListaTipoClienteRequest;
import br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn.BuscarListaTipoClienteResponse;
import br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn.ResultadoBuscarListaTipoClienteTipoCliente;
import br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn.TipoClientePortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFComDDDPorIdPlanoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFComDDDPorIdPlanoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFComDDDRequest;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFComDDDResponse;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFPorIdPlanoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFPorIdPlanoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFRequest;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFResponse;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.ParametrosBuscarListaUfComDDDPorIdPlano;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.ParametrosBuscarListaUfComDDDPorIdPlanoListaUfComDDDPorIdPlano;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.ParametrosBuscarListaUfPorIdPlano;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.ParametrosBuscarListaUfPorIdPlanoListaUfPorIdPlano;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.ResultadoBuscarListaUFUF;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.UFPortTypeProxy;
import edu.emory.mathcs.backport.java.util.Arrays;

public class CadastroOfertaSapAction extends BaseMappingDispatchAction {
	private static final long serialVersionUID = 1L;

	private String[] ufsSelecionados;
	private String[] dddsSelecionados;
	private static final String SUCCESS = "success";

	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		return mapping.findForward(SUCCESS);
	}

	public ActionForward pesquisarOfertaSapMatrizOferta(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws RemoteException {
		OfertaSapForm formulario = (OfertaSapForm) form;
		ParametrosBuscarListaOfertaSap parametrosBuscarListaOfertaSap = new ParametrosBuscarListaOfertaSap();
		BuscarListaOfertaSapRequest buscarListaOfertaSapRequest = new BuscarListaOfertaSapRequest();
		PaginacaoIn paginacaoIn = new PaginacaoIn();
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());

		if (formulario.getPaginaSolicitada() != null && !formulario.getPaginaSolicitada().equals("")) {
			paginacaoIn.setPaginaSolicitada(formulario.getPaginaSolicitada());
		} else {
			paginacaoIn.setPaginaSolicitada(1L);
		}
		
		if (formulario.getIdOfertaSap() != null) {
			parametrosBuscarListaOfertaSap.setIdOfertaSap(formulario.getIdOfertaSap());
		}
		if (formulario.getCdOfertaSap() != null && !formulario.getCdOfertaSap().equals("")) {
			parametrosBuscarListaOfertaSap.setCdOfertaSap(formulario.getCdOfertaSap());
		}
		if (formulario.getDescOfertaSap() != null && !formulario.getDescOfertaSap().equals("")) {
			parametrosBuscarListaOfertaSap.setDscOfertaSap(formulario.getDescOfertaSap());
		}
		if (formulario.getSgUtilizacao() != null && !formulario.getSgUtilizacao().equals("")) {
			parametrosBuscarListaOfertaSap.setSgUtilizacao(formulario.getSgUtilizacao());
		}
		if (formulario.getInDisponivel() != null && !formulario.getInDisponivel().equals("")) {
			parametrosBuscarListaOfertaSap.setInDisponivel(formulario.getInDisponivel());
		}

		OfertaSapPortTypeProxy ofertaSapPortTypeProxy = new OfertaSapPortTypeProxy();
		buscarListaOfertaSapRequest.setParametrosBuscarListaOfertaSap(parametrosBuscarListaOfertaSap);
		BuscarListaOfertaSapResponse buscarListaOfertaSapResponse = new BuscarListaOfertaSapResponse();
		buscarListaOfertaSapRequest.getParametrosBuscarListaOfertaSap().setPaginacaoIn(paginacaoIn);
		try {
			buscarListaOfertaSapResponse = ofertaSapPortTypeProxy.buscarListaOfertaSap(buscarListaOfertaSapRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, CadastroOfertaSapAction.class.getName(), ex.getMessage(), formulario, response );
			return null;
		}	
		
		PaginacaoOut paginacaoOut = buscarListaOfertaSapResponse.getResultadoBuscarListaOfertaSap().getPaginacaoOut();
		tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(), request);
		ResultadoBuscarListaOfertaSapListaOfertaSapOfertaSap[] resultadoBuscarListaOfertaSapListaOfertaSapOfertaSap = buscarListaOfertaSapResponse
				.getResultadoBuscarListaOfertaSap().getListaOfertaSap();

		List<ResultadoBuscarListaOfertaSapListaOfertaSapOfertaSap> ofertaSapLista = Arrays
				.asList(resultadoBuscarListaOfertaSapListaOfertaSapOfertaSap);
		formulario.setOfertaSapLista(ofertaSapLista);
		request.setAttribute("ofertaSap", ofertaSapLista);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward cadastrarNovaOfertaSapMatrizOferta(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward salvarNovaOfertaSapMatrizOferta(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws ErroInfo, RemoteException {
		OfertaSapForm formulario = (OfertaSapForm) form;

		CriarNovaOfertaSapRequest criarNovaOfertaSapRequest = new CriarNovaOfertaSapRequest();
		ParametrosCriarNovaOfertaSap parametrosCriarNovaOfertaSap = new ParametrosCriarNovaOfertaSap();
		
		if (formulario.getCdOfertaSap() != null && !formulario.getCdOfertaSap().equals("")) {
			parametrosCriarNovaOfertaSap.setCdOfertaSap(formulario.getCdOfertaSap().toUpperCase());
		}
		if (formulario.getDescOfertaSap() != null && !formulario.getCdOfertaSap().equals("")) {
			parametrosCriarNovaOfertaSap.setDscOfertaSap(formulario.getDescOfertaSap());
		}
		if (formulario.getSgUtilizacao() != null && !formulario.getSgUtilizacao().equals("")) {
			parametrosCriarNovaOfertaSap.setSgUtilizacao(formulario.getSgUtilizacao().toUpperCase());
		}
		if (formulario.getInDisponivel() != null && !formulario.getInDisponivel().equals("")) {
			parametrosCriarNovaOfertaSap.setInDisponivel(formulario.getInDisponivel().toUpperCase());
		}

		OfertaSapPortTypeProxy ofertaSapPortTypeProxy = new OfertaSapPortTypeProxy();
		criarNovaOfertaSapRequest.setParametrosCriarNovaOfertaSap(parametrosCriarNovaOfertaSap);
		CriarNovaOfertaSapResponse criarNovaOfertaSapResponse = new CriarNovaOfertaSapResponse();
		try {
			criarNovaOfertaSapResponse = ofertaSapPortTypeProxy.criarNovaOfertaSap(criarNovaOfertaSapRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, CadastroOfertaSapAction.class.getName(), ex.getMessage(), form, response );
			return null;
		}
		Long idOfertaSap = criarNovaOfertaSapResponse.getResultadoCriarNovaOfertaSap().getIdOfertaSap();

		request.setAttribute("idOfertaSap", idOfertaSap);
		return mapping.findForward("success");
	}

	public ActionForward openForEditOfertaSap(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws ErroInfo, RemoteException {
		String idOfertaSap = request.getParameter("id_oferta_sap");
		VerificarExistenciaComposicaoRequest verificarExistenciaComposicaoRequest = new VerificarExistenciaComposicaoRequest();
		ParametrosVerificarExistenciaComposicao parametrosVerificarExistenciaComposicao = new ParametrosVerificarExistenciaComposicao();

		if (idOfertaSap != null && !idOfertaSap.equals("")) {
			request.setAttribute("idOfertaSap", idOfertaSap);
			parametrosVerificarExistenciaComposicao.setIdOfertaSap(Long.valueOf(idOfertaSap));
		}
		String cdOfertaSap = request.getParameter("cd_ofertaSap");
		if (cdOfertaSap != null && !cdOfertaSap.equals("")) {
			request.setAttribute("cdOfertaSap", cdOfertaSap);
		}
		String dscOfertaSap = request.getParameter("dsc_ofertaSap");
		if (dscOfertaSap != null && !dscOfertaSap.equals("")) {
			request.setAttribute("dscOfertaSap", dscOfertaSap);
		}
		String sgUtilizacao = request.getParameter("sg_utilizacao");
		if (sgUtilizacao != null && !sgUtilizacao.equals("")) {
			request.setAttribute("sgUtilizacao", sgUtilizacao);
		}
		String inDisponivel = request.getParameter("in_disponivel");
		if (inDisponivel != null && !inDisponivel.equals("")) {
			request.setAttribute("inDisponivel", inDisponivel);
		}

		verificarExistenciaComposicaoRequest.setParametrosVerificarExistenciaComposicao(parametrosVerificarExistenciaComposicao);
		OfertaSapPortTypeProxy ofertaSapPortTypeProxy = new OfertaSapPortTypeProxy();
		VerificarExistenciaComposicaoResponse verificarExistenciaComposicaoResponse = new VerificarExistenciaComposicaoResponse();
		try {
			verificarExistenciaComposicaoResponse = ofertaSapPortTypeProxy.verificarExistenciaComposicao(verificarExistenciaComposicaoRequest, this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, CadastroOfertaSapAction.class.getName(), ex.getMessage(), form, response );
			return null;
		}
		
		Long qtdComposicao = verificarExistenciaComposicaoResponse.getResultadoVerificarExistenciaComposicao().getQtdComposicao();
		if (qtdComposicao != null && qtdComposicao > 0) {
			request.setAttribute("labelBotton", "btnCompsicao");
		} else {
			request.setAttribute("labelBotton", "btnComporOferta");
		}

		return mapping.findForward("success");
	}
	
	public ActionForward openConfirmDeleteOfertaSap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ErroInfo, RemoteException {
		VerificarAssociacaoOfertaSapRequest verificarAssociacaoOfertaSapRequest = new VerificarAssociacaoOfertaSapRequest();
		ParametrosVerificarAssociacaoOfertaSap parametrosVerificarAssociacaoOfertaSap = new ParametrosVerificarAssociacaoOfertaSap();
		
		String idOfertaSap = request.getParameter("id_oferta_sap");
		String dscOfertaSap = request.getParameter("dsc_ofertaSap");
		
		if (idOfertaSap != null && !idOfertaSap.equals("")) {
			parametrosVerificarAssociacaoOfertaSap.setIdOfertaSap(Long.valueOf(idOfertaSap));
			request.setAttribute("idOfertaSap", idOfertaSap);
		}
		if (dscOfertaSap != null && !dscOfertaSap.equals("")) {
			request.setAttribute("dscOfertaSap", dscOfertaSap);
		}
		
		verificarAssociacaoOfertaSapRequest.setParametrosVerificarAssociacaoOfertaSap(parametrosVerificarAssociacaoOfertaSap);
		OfertaSapPortTypeProxy ofertaSapPortTypeProxy = new OfertaSapPortTypeProxy();
		VerificarAssociacaoOfertaSapResponse verificarAssociacaoOfertaSapResponse = new VerificarAssociacaoOfertaSapResponse();
		try {
			verificarAssociacaoOfertaSapResponse = ofertaSapPortTypeProxy.verificarAssociacaoOfertaSap(verificarAssociacaoOfertaSapRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, CadastroOfertaSapAction.class.getName(), ex.getMessage(), form, response );
			return null;
		}	
		
		Long qtdeRegistros = verificarAssociacaoOfertaSapResponse.getResultadoVerificarAssociacaoOfertaSap().getQtdeRegistros();
		if (qtdeRegistros != null && qtdeRegistros > 0) {
			return mapping.findForward("message");
		}
		return mapping.findForward("success");
	}

	public ActionForward deleteOfertaSap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ErroInfo, RemoteException {
		OfertaSapForm formulario = (OfertaSapForm)form;
		DeletarOfertaSapRequest deletarOfertaSapRequest = new DeletarOfertaSapRequest();
		ParametrosDeletarOfertaSap parametrosDeletarOfertaSap = new ParametrosDeletarOfertaSap();
		
		if (formulario.getIdOfertaSap() != null) {
			parametrosDeletarOfertaSap.setIdOfertaSap(formulario.getIdOfertaSap());
		}
		
		deletarOfertaSapRequest.setParametrosDeletarOfertaSap(parametrosDeletarOfertaSap);
		OfertaSapPortTypeProxy ofertaSapPortTypeProxy = new OfertaSapPortTypeProxy();
		try {
			ofertaSapPortTypeProxy.deletarOfertaSap(deletarOfertaSapRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, CadastroOfertaSapAction.class.getName(), ex.getMessage(), form, response );
			return null;
		}	
		
		PrintWriter writer = null;
		try {
			response.setContentType("text/javascript");
			writer = response.getWriter();
			writer.println("$('botao_pesquisar_oferta_sap').onclick();");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
		return null;
	}

	public ActionForward salvarAlteracaoOfertaSap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ErroInfo, RemoteException {
		OfertaSapForm formulario = (OfertaSapForm)form;
		AlterarOfertaSapRequest alterarOfertaSapRequest = new AlterarOfertaSapRequest();
		ParametrosAlterarOfertaSap parametrosAlterarOfertaSap = new ParametrosAlterarOfertaSap();
		
		if (formulario.getIdOfertaSap() != null) {
			parametrosAlterarOfertaSap.setIdOfertaSap(formulario.getIdOfertaSap());
		}
		if (formulario.getCdOfertaSap() != null && !formulario.getCdOfertaSap().equals("")) {
			parametrosAlterarOfertaSap.setCdOfertaSap(formulario.getCdOfertaSap().toUpperCase());
		}
		if (formulario.getDescOfertaSap() != null && !formulario.getDescOfertaSap().equals("")) {
			parametrosAlterarOfertaSap.setDscOfertaSap(formulario.getDescOfertaSap());
		}
		if (formulario.getSgUtilizacao() != null && !formulario.getSgUtilizacao().equals("")) {
			parametrosAlterarOfertaSap.setSgUtilizacao(formulario.getSgUtilizacao().toUpperCase());
		}
		if (formulario.getInDisponivel() != null && !formulario.getInDisponivel().equals("")) {
			parametrosAlterarOfertaSap.setInDisponivel(formulario.getInDisponivel().toUpperCase());
		}
		alterarOfertaSapRequest.setParametrosAlterarOfertaSap(parametrosAlterarOfertaSap);
		OfertaSapPortTypeProxy ofertaSapPortTypeProxy = new OfertaSapPortTypeProxy();
		try {
			ofertaSapPortTypeProxy.alterarOfertaSap(alterarOfertaSapRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, CadastroOfertaSapAction.class.getName(), ex.getMessage(), form, response );
			return null;
		}	
		
		PrintWriter writer = null;
		try {
			response.setContentType("text/javascript");
			writer = response.getWriter();
			writer.println("$('botao_pesquisar_oferta_sap').onclick();");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
		return null;
	}

	public ActionForward selecionarMatrizOfertaComposicao(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws RemoteException {
		
		if (request.getParameter("id_oferta_sap") != null && !request.getParameter("id_oferta_sap").trim().equals("")) {
			request.setAttribute("idOfertaSap", request.getParameter("id_oferta_sap"));
		}
		
		BuscarListaNmMatrizRequest buscarListaNmMatrizRequest =  new BuscarListaNmMatrizRequest();
		ParametroNomeMatriz parametroNomeMatriz = new ParametroNomeMatriz();
		parametroNomeMatriz.setDataVigencia(Calendar.getInstance());
		buscarListaNmMatrizRequest.setParametroNomeMatriz(parametroNomeMatriz);
		MatrizOfertaPortTypeProxy matrizOfertaPortTypeProxy = new MatrizOfertaPortTypeProxy();
		BuscarListaNmMatrizResponse buscarListaNmMatrizResponse = new BuscarListaNmMatrizResponse();
		try {
			buscarListaNmMatrizResponse = matrizOfertaPortTypeProxy.buscarListaNmMatriz(buscarListaNmMatrizRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, CadastroOfertaSapAction.class.getName(), ex.getMessage(), form, response );
			return null;
		}	
		ResultadoListaNomeMatrizListaNomeMatriz[] resultadoListaNomeMatrizListaNomeMatriz  = buscarListaNmMatrizResponse.getResultadoListaNomeMatriz();
		List<ResultadoListaNomeMatrizListaNomeMatriz> matrizOferta = Arrays.asList(resultadoListaNomeMatrizListaNomeMatriz);
		
		request.setAttribute("matrizOferta", matrizOferta);
		return mapping.findForward("success");
	}

	public ActionForward addMatrizOfertaSession(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws RemoteException {
		OfertaSapForm formulario = (OfertaSapForm)form;
		
			if (formulario.getIdMatrizOferta() != null) {
				request.setAttribute("idMatrizOferta", formulario.getIdMatrizOferta());
			}
			if (formulario.getIdOfertaSap() != null) {
				request.setAttribute("idOfertaSap", formulario.getIdOfertaSap());
			}
		
		BuscarListaTipoClienteRequest buscarListaTipoClienteRequest = new BuscarListaTipoClienteRequest();
		TipoClientePortTypeProxy tipoClientePortTypeProxy = new TipoClientePortTypeProxy();
		BuscarListaTipoClienteResponse buscarListaTipoClienteResponse = new BuscarListaTipoClienteResponse();
		try {
			 buscarListaTipoClienteResponse = tipoClientePortTypeProxy.buscarListaTipoCliente(buscarListaTipoClienteRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, CadastroOfertaSapAction.class.getName(), ex.getMessage(), formulario, response );
			return null;
		}	
		ResultadoBuscarListaTipoClienteTipoCliente[] resultadoBuscarListaTipoClienteTipoCliente = buscarListaTipoClienteResponse.getResultadoBuscarListaTipoCliente();
		List<ResultadoBuscarListaTipoClienteTipoCliente> tipoClienteLista = Arrays.asList(resultadoBuscarListaTipoClienteTipoCliente);
		request.setAttribute("tipoCliente", tipoClienteLista);

		BuscarListaPlataformaRequest buscarListaPlataformaRequest = new BuscarListaPlataformaRequest();
		PlataformaPortTypeProxy plataformaPortTypeProxy = new PlataformaPortTypeProxy();
		BuscarListaPlataformaResponse buscarListaPlataformaResponse = new BuscarListaPlataformaResponse();
		try {
			buscarListaPlataformaResponse = plataformaPortTypeProxy.buscarListaPlataforma(buscarListaPlataformaRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, CadastroOfertaSapAction.class.getName(), ex.getMessage(), formulario, response );
			return null;
		}	
		ResultadoBuscarListaPlataformaPlataforma[] resultadoBuscarListaPlataformaPlataforma = buscarListaPlataformaResponse.getResultadoBuscarListaPlataforma();
		List<ResultadoBuscarListaPlataformaPlataforma> plataformaLista = Arrays.asList(resultadoBuscarListaPlataformaPlataforma);
		request.setAttribute("plataforma", plataformaLista);

		BuscarListaTecnologiaRequest buscarListaTecnologiaRequest = new BuscarListaTecnologiaRequest();
		TecnologiaPortTypeProxy tecnologiaPortTypeProxy = new TecnologiaPortTypeProxy();
		BuscarListaTecnologiaResponse buscarListaTecnologiaResponse = new BuscarListaTecnologiaResponse();
		try {
			buscarListaTecnologiaResponse = tecnologiaPortTypeProxy.buscarListaTecnologia(buscarListaTecnologiaRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, CadastroOfertaSapAction.class.getName(), ex.getMessage(), formulario, response );
			return null;
		}	
		ResultadoBuscarListaTecnologiaTecnologia[] resultadoBuscarListaTecnologiaTecnologia = buscarListaTecnologiaResponse.getResultadoBuscarListaTecnologia();
		List<ResultadoBuscarListaTecnologiaTecnologia> tecnologiaLista = Arrays.asList(resultadoBuscarListaTecnologiaTecnologia);
		request.setAttribute("tecnologia", tecnologiaLista);

		BuscarListaUFRequest buscarListaUFRequest = new BuscarListaUFRequest();
		UFPortTypeProxy ufPortType = new UFPortTypeProxy();
		BuscarListaUFResponse buscarListaUFResponse = new BuscarListaUFResponse();
		try {
			buscarListaUFResponse = ufPortType.buscarListaUF(buscarListaUFRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, CadastroOfertaSapAction.class.getName(), ex.getMessage(), formulario, response );
			return null;
		}	
		
		ResultadoBuscarListaUFUF[] resultadoBuscarListaUFUF = buscarListaUFResponse.getResultadoBuscarListaUF();
		List<ResultadoBuscarListaUFUF> listUF = Arrays.asList(resultadoBuscarListaUFUF);
		formulario.setListUF(resultadoBuscarListaUFUF);
		request.setAttribute("listaUF", resultadoBuscarListaUFUF);
		return mapping.findForward("success");
	}

	public ActionForward pesquisarListaPlanos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws RemoteException {
		OfertaSapForm formulario = (OfertaSapForm)form;
		OfertaSapPortTypeProxy ofertaSapPortTypeProxy = new OfertaSapPortTypeProxy();
		OfertaServicoPortTypeProxy ofertaServicoPortTypeProxy = new OfertaServicoPortTypeProxy();
		BuscarListaPlanosSemOfertaRequest buscarListaPlanosSemOfertaRequest = new BuscarListaPlanosSemOfertaRequest();
		ParametrosBuscarListaPlanosSemOferta parametrosBuscarListaPlanosSemOferta = new ParametrosBuscarListaPlanosSemOferta();
		PaginacaoIn paginacaoIn = new PaginacaoIn();
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		
			if(formulario.getPaginaSolicitada() != null && formulario.getPaginaSolicitada() > 0) {
				paginacaoIn.setPaginaSolicitada(formulario.getPaginaSolicitada());
			} else {
				paginacaoIn.setPaginaSolicitada(1);
			}
			if (formulario.getNmPlano() != null && !formulario.getNmPlano().trim().equals("")) {
				parametrosBuscarListaPlanosSemOferta.setNmPlano(formulario.getNmPlano());
			}
			if (formulario.getCdPlano() != null && !formulario.getCdPlano().trim().equals("")) {
				parametrosBuscarListaPlanosSemOferta.setCdPlano(formulario.getCdPlano());
			}
			if (formulario.getIdsUf() != null && !formulario.getIdsUf().trim().equals("")) {
				List<String> ufs = new ArrayList<String>();
				List<String> ddds = new ArrayList<String>();
				extrairJSONChaveValor(formulario.getIdsUf(), ufs, ddds);

				String uf = null;
				for(int i=0; i<ufs.size(); i++) {		
					if(parametrosBuscarListaPlanosSemOferta.getListaUf() == null){
						uf = ufs.get(i);
					}
					long ufLong = Long.valueOf(uf);
					long [] ufLongArray = {ufLong};
					parametrosBuscarListaPlanosSemOferta.setListaUf(ufLongArray);
				}
				
				String ddd = null;
				for(int i=0; i<ddds.size(); i++){
					if(parametrosBuscarListaPlanosSemOferta.getListaDDD() == null)
					ddd = ddds.get(i);
				}
				String [] dddArray = {ddd};
				parametrosBuscarListaPlanosSemOferta.setListaDDD(dddArray);
			}
			
			if (formulario.getIdPlataforma() != null && formulario.getIdPlataforma() > 0)  {
				parametrosBuscarListaPlanosSemOferta.setIdPlataforma(formulario.getIdPlataforma());
			}
			
			if (formulario.getIdTecnologia() > 0 && formulario.getIdTecnologia()!= null) {
				parametrosBuscarListaPlanosSemOferta.setIdTecnologia(formulario.getIdTecnologia());
			}
			
			buscarListaPlanosSemOfertaRequest.setParametrosBuscarListaPlanosSemOferta(parametrosBuscarListaPlanosSemOferta);
			buscarListaPlanosSemOfertaRequest.getParametrosBuscarListaPlanosSemOferta().setPaginacaoIn(paginacaoIn);
			BuscarListaPlanosSemOfertaResponse buscarListaPlanosSemOfertaResponse = new BuscarListaPlanosSemOfertaResponse();
			try {
				buscarListaPlanosSemOfertaResponse = ofertaSapPortTypeProxy.buscarListaPlanosSemOferta(buscarListaPlanosSemOfertaRequest, this.getUserName(), this.getPassword());
			} catch (AxisFault ex) {		
				this.AxisFaultExceptionHandler(ex, CadastroOfertaSapAction.class.getName(), ex.getMessage(), formulario, response );
				return null;
			}	
			PaginacaoOut paginacaoOut = new PaginacaoOut();
			ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta[] resultadoBuscarListaPlanosSemOferta = null;
			if (buscarListaPlanosSemOfertaResponse.getResultadoBuscarListaPlanosSemOferta() != null) {
				resultadoBuscarListaPlanosSemOferta = buscarListaPlanosSemOfertaResponse.getResultadoBuscarListaPlanosSemOferta().getListaPlanosSemOferta();
				paginacaoOut = buscarListaPlanosSemOfertaResponse.getResultadoBuscarListaPlanosSemOferta().getPaginacaoOut();				
			}
			
		this.tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(), request);
		
		BuscarListaNomeOfertaServicoRequest buscarListaNomeOfertaServicoRequest = new BuscarListaNomeOfertaServicoRequest();
		BuscarListaNomeOfertaServicoResponse buscarListaNomeOfertaServicoResponse = new BuscarListaNomeOfertaServicoResponse();
		try {
			buscarListaNomeOfertaServicoResponse = ofertaServicoPortTypeProxy.buscarListaNomeOfertaServico(buscarListaNomeOfertaServicoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, CadastroOfertaSapAction.class.getName(), ex.getMessage(), formulario, response );
			return null;
		}
		ResultadoBuscarListaNmOfertaServicoListaNomeOfertaServicoOfertaServico[] ofertaServicoLista = null;
		if (buscarListaNomeOfertaServicoResponse.getResultadoBuscarListaNmOfertaServico() != null) {
			ofertaServicoLista = buscarListaNomeOfertaServicoResponse.getResultadoBuscarListaNmOfertaServico().getListaNomeOfertaServico();
		}

		if (formulario.getIdMatrizOferta() != null) {
			request.setAttribute("idMatrizOferta", formulario.getIdMatrizOferta());
		}
		if (formulario.getIdOfertaSap() != null) {
			request.setAttribute("idOfertaSap", formulario.getIdOfertaSap());
		}
		
		
		formulario.setPlanoLista(resultadoBuscarListaPlanosSemOferta);
		request.setAttribute("ofertaServicoLista", ofertaServicoLista);
		request.setAttribute("idPlataforma", formulario.getIdPlataforma());
		return mapping.findForward("success");
		
	}

	public ActionForward pesquisarListaUfs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws RemoteException {
		OfertaSapForm formulario = (OfertaSapForm)form;
		String ufsJSon = request.getParameter("hiddenUFs");
		if (ufsJSon != null) {
			List<String> chaves = new ArrayList<String>();
			List<String> valores = new ArrayList<String>();
			Map<String, String[]> ufsSelecionados = extrairJSONChaveValor(ufsJSon, chaves, valores);
			this.setUfsSelecionados(chaves.toArray(new String[] {}));
			this.setDddsSelecionados(valores.toArray(new String[] {}));
		}
		BuscarListaUFComDDDRequest buscarListaUFComDDDRequest = new BuscarListaUFComDDDRequest();
		UFPortTypeProxy uFPortTypeProxy = new UFPortTypeProxy();
		BuscarListaUFComDDDResponse buscarListaUFComDDDResponse = new BuscarListaUFComDDDResponse();
		try {
			buscarListaUFComDDDResponse = uFPortTypeProxy.buscarListaUFComDDD(buscarListaUFComDDDRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, CadastroOfertaSapAction.class.getName(), ex.getMessage(), form, response );
			return null;
		}
		
		ResultadoBuscarListaUFUF[] listUF = buscarListaUFComDDDResponse.getResultadoBuscarListaUF();
		formulario.setListUF(listUF);
		return mapping.findForward("success");
	}

	public ActionForward showListaUFs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws RemoteException {
		Long idPlataforma = null;
		OfertaSapForm formulario = (OfertaSapForm) form;
		UFPortTypeProxy uFPortTypeProxy = new UFPortTypeProxy();
		if (request.getParameter("id_plataforma") != null && !request.getParameter("id_plataforma").trim().equals("")) {
			idPlataforma = Long.valueOf(request.getParameter("id_plataforma"));
		}
		if (idPlataforma == 1) {
			BuscarListaUFPorIdPlanoRequest listaUFPorIdPlanoRequest= new BuscarListaUFPorIdPlanoRequest();
			ParametrosBuscarListaUfPorIdPlano paramListarUfPorIdPlano = new ParametrosBuscarListaUfPorIdPlano();
			ParametrosBuscarListaUfPorIdPlanoListaUfPorIdPlano listaUfPorIdPlano = new ParametrosBuscarListaUfPorIdPlanoListaUfPorIdPlano();
			listaUfPorIdPlano.setIdPlano(Long.valueOf(request.getParameter("id_plano")));
			paramListarUfPorIdPlano.setListaUfPorIdPlano(listaUfPorIdPlano);
			listaUFPorIdPlanoRequest.setParametrosBuscarListaUfPorIdPlano(paramListarUfPorIdPlano);
			BuscarListaUFPorIdPlanoResponse buscarListaUFPorIdPlanoResponse = new BuscarListaUFPorIdPlanoResponse();
			
			try {
				buscarListaUFPorIdPlanoResponse = uFPortTypeProxy.buscarListaUFPorIdPlano(listaUFPorIdPlanoRequest, this.getUserName(), this.getPassword());
			} catch (AxisFault ex) {		
				this.AxisFaultExceptionHandler(ex, CadastroOfertaSapAction.class.getName(), ex.getMessage(), form, response );
				return null;
			}
			ResultadoBuscarListaUFUF[] resultadoBuscarListaUFUF = buscarListaUFPorIdPlanoResponse.getResultadoBuscarListaUF();
			if (resultadoBuscarListaUFUF != null) {
				formulario.setListUF(resultadoBuscarListaUFUF);
				
			}
		
		} else {
			
			BuscarListaUFComDDDPorIdPlanoRequest listaUFComDDDPorIdPlanoRequest = new BuscarListaUFComDDDPorIdPlanoRequest();
			ParametrosBuscarListaUfComDDDPorIdPlano listaUfComDDDPorIdPlano = new ParametrosBuscarListaUfComDDDPorIdPlano();
			ParametrosBuscarListaUfComDDDPorIdPlanoListaUfComDDDPorIdPlano listaUfComDDDPorId = new ParametrosBuscarListaUfComDDDPorIdPlanoListaUfComDDDPorIdPlano();
			listaUfComDDDPorId.setIdPlano(Long.valueOf(request.getParameter("id_plano")));
			listaUfComDDDPorIdPlano.setListaUfComDDDPorIdPlano(listaUfComDDDPorId);
			listaUFComDDDPorIdPlanoRequest.setParametrosBuscarListaUfComDDDPorIdPlano(listaUfComDDDPorIdPlano);
			BuscarListaUFComDDDPorIdPlanoResponse resultadoBuscarListaUFComDDD = new BuscarListaUFComDDDPorIdPlanoResponse();
			try {
				resultadoBuscarListaUFComDDD = uFPortTypeProxy.buscarListaUFComDDDPorIdPlano(listaUFComDDDPorIdPlanoRequest, this.getUserName(), this.getPassword());
			} catch (AxisFault ex) {		
				this.AxisFaultExceptionHandler(ex, CadastroOfertaSapAction.class.getName(), ex.getMessage(), form, response );
				return null;
			}
			
			ResultadoBuscarListaUFUF[] resultadoBuscarListaUFUF = resultadoBuscarListaUFComDDD.getResultadoBuscarListaUF();
			if (resultadoBuscarListaUFUF != null) {
				formulario.setListUF(resultadoBuscarListaUFUF);
			}
		}
		
		return mapping.findForward("success");
	}

	public ActionForward gravarComposicaoOfertaSap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws CatalogoPRSException, RemoteException {
		OfertaSapForm formulario = (OfertaSapForm)form;
		CriarComposicaoOfertaSapRequest criarComposicaoOfertaSapRequest = new CriarComposicaoOfertaSapRequest();
		ParametrosCriarComposicaoOferta parametrosCriarComposicaoOferta = new ParametrosCriarComposicaoOferta();
		
		if (formulario.getIdOfertaSap() != null) {
			parametrosCriarComposicaoOferta.setIdOfertaSap(formulario.getIdOfertaSap());
		}

		if (formulario.getIdsPlanos() != null && !formulario.getIdsPlanos().equals("")) {
			int count = formulario.getIdsPlanos().length;
			long[] listaIdPlanos = new long[count];
			
			for (int i=0;i < count;i++) {
				if(!formulario.getIdsPlanos()[i].equals(""))
					listaIdPlanos[i] = Long.parseLong(formulario.getIdsPlanos()[i]);
			}
			parametrosCriarComposicaoOferta.setListaIdSistemaPlano(listaIdPlanos);
		} else {
			CatalogoPRSException ex = new CatalogoPRSException("Obrigat&oacute;rio selecionar ao menos um plano para a Oferta SAP");
			this.CatalogoPRSExceptionHandler(ex, CadastroOfertaSapAction.class.getName(), response);
		}
		if (formulario.getIdOfertaServico() != null) {
			parametrosCriarComposicaoOferta.setIdOfertaServico(formulario.getIdOfertaServico());
		}
		if (formulario.getIdMatrizOferta() != null) {
			parametrosCriarComposicaoOferta.setIdMatrizOferta(formulario.getIdMatrizOferta());
		}
		criarComposicaoOfertaSapRequest.setParametrosCriarComposicaoOferta(parametrosCriarComposicaoOferta);
		OfertaSapPortTypeProxy ofertaSapPortTypeProxy = new OfertaSapPortTypeProxy();
		CriarComposicaoOfertaSapResponse criarComposicaoOfertaSapResponse = new CriarComposicaoOfertaSapResponse();
		try {
			criarComposicaoOfertaSapResponse = ofertaSapPortTypeProxy
					.criarComposicaoOfertaSap(criarComposicaoOfertaSapRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, CadastroOfertaSapAction.class.getName(), ex.getMessage(), formulario, response );
			return null;
		}
		
		ResultadoCriarComposicaoOferta resultadoCriarComposicaoOferta = criarComposicaoOfertaSapResponse.getResultadoCriarComposicaoOferta();
		Long indException = resultadoCriarComposicaoOferta.getIndException();
				
		if (indException == -1) {
			CatalogoPRSException ex = new CatalogoPRSException("Composi&ccedil;&atilde;o de Oferta Sap deve ser &uacute;nica entre Matriz Oferta, Oferta SAP, Oferta Complementar e Plano.");
			this.CatalogoPRSExceptionHandler(ex, CadastroOfertaSapAction.class.getName(), response);
		}

		PrintWriter writer = null;
	
		try {
			response.setContentType("text/javascript");
			writer = response.getWriter();
			writer.println("$('botao_pesquisar_planos_oferta').onclick();");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
		return null;
	}

	public ActionForward visualizarComposicao(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws RemoteException {
		OfertaSapForm formulario = (OfertaSapForm)form;
		
		BuscarListaComposicaoRequest buscarListaComposicaoRequest = new BuscarListaComposicaoRequest();
		ParametrosBuscarListaComposicao parametrosBuscarListaComposicao = new ParametrosBuscarListaComposicao();
		
		if (request.getParameter("dsc_oferta_sap") != null && !request.getParameter("dsc_oferta_sap").trim().equals("")) {
			request.setAttribute("dscOfertaSap", request.getParameter("dsc_oferta_sap"));
		}
		
		if (request.getParameter("id_oferta_sap") != null && !request.getParameter("id_oferta_sap").trim().equals("")) {
			parametrosBuscarListaComposicao.setIdOfertaSap(Long.valueOf(request.getParameter("id_oferta_sap")));
			request.setAttribute("idOfertaSap", request.getParameter("id_oferta_sap"));
		}
		
		buscarListaComposicaoRequest.setParametrosBuscarListaComposicao(parametrosBuscarListaComposicao);
		OfertaSapPortTypeProxy ofertaSapPortTypeProxy = new OfertaSapPortTypeProxy();
		BuscarListaComposicaoResponse buscarListaComposicaoResponse = new BuscarListaComposicaoResponse();
		try {
			buscarListaComposicaoResponse = ofertaSapPortTypeProxy.buscarListaComposicao(buscarListaComposicaoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, CadastroOfertaSapAction.class.getName(), ex.getMessage(), form, response );
			return null;
		}	
		ResultadoBuscarListaComposicaoListaComposicaoComposicao[] composicaoLista = buscarListaComposicaoResponse
				.getResultadoBuscarListaComposicao().getListaComposicao();
		
		request.setAttribute("composicao", composicaoLista);
		formulario.setComposicaoLista(composicaoLista);
		return mapping.findForward("success");
	}

	public ActionForward excluirComposicao(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws RemoteException {
		ExcluirComposicaoOfertaRequest excluirComposicaoOfertaRequest = new ExcluirComposicaoOfertaRequest();
		ParametrosExcluirComposicaoOferta parametrosExcluirComposicaoOferta = new ParametrosExcluirComposicaoOferta();
		
		if (request.getParameter("id_oferta_sap") != null && !request.getParameter("id_oferta_sap").trim().equals("")) {
			request.setAttribute("id_oferta_sap", request.getParameter("id_oferta_sap"));
			parametrosExcluirComposicaoOferta.setIdOfertaSap(Long.valueOf(request.getParameter("id_oferta_sap")));
		}
		if (request.getParameter("id_ofertaServico") != null
				&& !request.getParameter("id_ofertaServico").trim().equals("")) {
			parametrosExcluirComposicaoOferta
					.setIdOfertaServico(Long.valueOf(request.getParameter("id_ofertaServico")));
		}
		if (request.getParameter("id_sistemaPlano") != null
				&& !request.getParameter("id_sistemaPlano").trim().equals("")) {
			parametrosExcluirComposicaoOferta.setIdSistemaPlano(Long.valueOf(request.getParameter("id_sistemaPlano")));
		}
		if (request.getParameter("id_matrizOferta") != null
				&& !request.getParameter("id_matrizOferta").trim().equals("")) {
			parametrosExcluirComposicaoOferta.setIdMatrizOferta(Long.valueOf(request.getParameter("id_matrizOferta")));
		}
		if (request.getParameter("dsc_oferta_sap") != null
				&& !request.getParameter("dsc_oferta_sap").trim().equals("")) {
			request.setAttribute("dsc_oferta_sap", request.getParameter("dsc_oferta_sap"));
		}
		
		excluirComposicaoOfertaRequest.setParametrosExcluirComposicaoOferta(parametrosExcluirComposicaoOferta);
		OfertaSapPortTypeProxy ofertaSapPortTypeProxy = new OfertaSapPortTypeProxy();
		try {
			ofertaSapPortTypeProxy.excluirComposicaoOferta(excluirComposicaoOfertaRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, CadastroOfertaSapAction.class.getName(), ex.getMessage(), form, response );
			return null;
		}	
		ofertaSapPortTypeProxy.excluirComposicaoOferta(excluirComposicaoOfertaRequest, this.getUserName(), this.getPassword());
		return mapping.findForward("success");
	}

	public String[] getDddsSelecionados() {
		return dddsSelecionados;
	}

	public void setDddsSelecionados(String[] dddsSelecionados) {
		this.dddsSelecionados = dddsSelecionados;
	}

	public String[] getUfsSelecionados() {
		return ufsSelecionados;
	}

	public void setUfsSelecionados(String[] ufsSelecionados) {
		this.ufsSelecionados = ufsSelecionados;
	}
}


	