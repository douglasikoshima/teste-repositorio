package br.com.vivo.catalogoPRS.pageflows.param.matrizOferta.cadastro;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.axis.AxisFault;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.CadastroCabecalhoMatrizOfertaForm;
import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;
import br.com.vivo.catalogoPRS.util.SOAPUtil;
import br.com.vivo.catalogoPRS.ws.catalogoCanalAtendimento.sn.CanalAtendimentoPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoCanalAtendimento.sn.ParametroBuscarCanalAtendimento;
import br.com.vivo.catalogoPRS.ws.catalogoCanalAtendimento.sn.ResultadoBuscarCanalAtendimentoCanalAtendimento;
import br.com.vivo.catalogoPRS.ws.catalogoCanalCarteira.sn.BuscarListaCarteiraRequest;
import br.com.vivo.catalogoPRS.ws.catalogoCanalCarteira.sn.BuscarListaCarteiraResponse;
import br.com.vivo.catalogoPRS.ws.catalogoCanalCarteira.sn.CarteiraPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoCanalCarteira.sn.ParametrosBuscarListaCarteira;
import br.com.vivo.catalogoPRS.ws.catalogoCanalCarteira.sn.ResultadoBuscarListaCarteiraListaCarteiraCarteira;
import br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn;
import br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarCabecalhoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AssociarVariaveisRequest;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaCabecalhoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaCabecalhoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaTipoMatrizRequest;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaTipoMatrizResponse;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaVariaveisRequest;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaVariaveisResponse;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.CriarCabecalhoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExcluirCabecalhoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExcluirVariaveisRequest;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.MatrizOfertaPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosAlterarCabecalho;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosAssociarVariaveis;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosBuscarListaCabecalho;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosBuscarListaVariaveis;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosCriarCabecalho;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosExcluirCabecalho;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosExcluirVariavel;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaCabecalho;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaCabecalhoListaCabecalhoCabecalho;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaCanalCanal;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaPlataformaPlataforma;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaTipoOperacaoTipoOperacao;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaTipoMatrizResultadoListaTipoMatrizTipoMatriz;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaVariaveisListaVariaveisVariaveis;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.BuscarListaPlataformaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.BuscarListaPlataformaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.PlataformaPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.ResultadoBuscarListaPlataformaPlataforma;
import br.com.vivo.catalogoPRS.ws.catalogoSegmento.sn.BuscarListaSegmentacaoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoSegmento.sn.BuscarListaSegmentacaoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoSegmento.sn.ResultadoBuscarListaSegmentoListaSegmentoSegmento;
import br.com.vivo.catalogoPRS.ws.catalogoSegmento.sn.SegmentoPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn.BuscarListaTipoClienteRequest;
import br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn.BuscarListaTipoClienteResponse;
import br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn.ResultadoBuscarListaTipoClienteTipoCliente;
import br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn.TipoClientePortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoTipoOperacao.sn.BuscarListaTipoOperacaoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoTipoOperacao.sn.BuscarListaTipoOperacaoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoTipoOperacao.sn.ResultadoBuscarListaTipoOperacaoListaTipoOperacaoTipoOperacao;
import br.com.vivo.catalogoPRS.ws.catalogoTipoOperacao.sn.TipoOperacaoPortTypeProxy;


public class CadastroCabecalhoMatrizOfertaAction extends BaseMappingDispatchAction {
	
	private static final java.util.logging.Logger logging = java.util.logging.Logger.getLogger(CadastroCabecalhoMatrizOfertaAction.class.getName());
	private static Logger logger = Logger.getLogger(CadastroCabecalhoMatrizOfertaAction.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static final long CODIGOERROBUSCARLISTAVARIAVEIS_LISTA_VAZIA = Long.valueOf(ApplicationConfiguration.getCdErroBuscarListaVariaveisListaVazia());
	
	private List<ResultadoBuscarListaCabecalhoListaCabecalhoCabecalho> cabecalhoMatrizOfertaLista;
	
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		this.setCabecalhoMatrizOfertaLista(null);
		Calendar calendar = Calendar.getInstance();
		request.setAttribute("currentDate", calendar.getTime());
		return mapping.findForward("success");
		
		
	}
	
	public ActionForward pesquisarCabecalhosMatrizOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ParseException, ErroInfo, RemoteException {
		CadastroCabecalhoMatrizOfertaForm formulario = (CadastroCabecalhoMatrizOfertaForm)form;
		ActionForward actionForw = new ActionForward();
		
		BuscarListaCabecalhoRequest buscarListaCabecalhoRequest =  new BuscarListaCabecalhoRequest();
		MatrizOfertaPortTypeProxy matrizOfertaPortTypeProxy = new MatrizOfertaPortTypeProxy();
		
		ParametrosBuscarListaCabecalho parametrosBuscarListaCabecalho = new ParametrosBuscarListaCabecalho();
			
		PaginacaoIn paginacaoIn = new PaginacaoIn();		
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		if(formulario.getPaginaSolicitada() != null && formulario.getPaginaSolicitada() != 0) {
			paginacaoIn.setPaginaSolicitada(formulario.getPaginaSolicitada());
		}else {
			paginacaoIn.setPaginaSolicitada(1);
		}
		if(formulario.getDtVigencia() != null && !formulario.getDtVigencia().equals("")) {
			parametrosBuscarListaCabecalho.setDtVigencia(formulario.getDataVigencia());
		}
		if(formulario.getNmMatrizOferta() != null && formulario.getNmMatrizOferta().trim().length() > 0) {
			parametrosBuscarListaCabecalho.setNmMatrizOferta(formulario.getNmMatrizOferta());
		}
		
		buscarListaCabecalhoRequest.setParametrosBuscarListaCabecalho(parametrosBuscarListaCabecalho);				
		buscarListaCabecalhoRequest.getParametrosBuscarListaCabecalho().setPaginacaoIn(paginacaoIn);
				
		BuscarListaCabecalhoResponse buscarListaCabecalhoResponse = new BuscarListaCabecalhoResponse();
		
		try {
			buscarListaCabecalhoResponse = matrizOfertaPortTypeProxy.buscarListaCabecalho(buscarListaCabecalhoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {			
			logger.debug("Exception no method: 'pesquisarCabecalhosMatrizOferta' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );	
		}
		
		ResultadoBuscarListaCabecalho resultadoBuscarListaCabecalho = null;
		ResultadoBuscarListaCabecalhoListaCabecalhoCabecalho[] listaCabecalho = null;
		
		List<ResultadoBuscarListaCabecalhoListaCabecalhoCabecalho> cabecalhoMatrizOfertaLista = new ArrayList<ResultadoBuscarListaCabecalhoListaCabecalhoCabecalho>();
		PaginacaoOut paginacaoOut = new PaginacaoOut();
		if (buscarListaCabecalhoResponse.getResultadoBuscarListaCabecalho() != null) {
			resultadoBuscarListaCabecalho = buscarListaCabecalhoResponse.getResultadoBuscarListaCabecalho();
			listaCabecalho = resultadoBuscarListaCabecalho.getListaCabecalho();
			paginacaoOut = resultadoBuscarListaCabecalho.getPaginacaoOut();
			cabecalhoMatrizOfertaLista = Arrays.asList(listaCabecalho);
		}
		
		Map<String, String> mapaDataIncial = new HashMap<String, String>(); 
		Map<String, String> mapaDataFinal = new HashMap<String, String>(); 
		
		
		if (listaCabecalho != null && listaCabecalho.length > 0) {
			
			for (int i = 0; i < listaCabecalho.length; i++ ) {
				
				String nomeMatrizOfer = listaCabecalho[i].getNmMatrizOferta();	
				
				if (listaCabecalho[i].getDtInicial() != null) {
					listaCabecalho[i].getDtInicial();
					
					Calendar dtInicial = listaCabecalho[i].getDtInicial();
					Date dataIni = dtInicial.getTime();
					DateFormat dfIni = new SimpleDateFormat("dd/MM/yyyy");
					String reportDateIni = dfIni.format(dataIni);
					mapaDataIncial.put(nomeMatrizOfer, reportDateIni);
					
				}
				
				if (listaCabecalho[i].getDtFinal() != null) {
					listaCabecalho[i].getDtFinal();
					
					Calendar dtFinal = listaCabecalho[i].getDtFinal();
					Date dataFim = dtFinal.getTime();
					DateFormat dfFim = new SimpleDateFormat("dd/MM/yyyy");
					String reportDateFim = dfFim.format(dataFim);
					mapaDataFinal.put(nomeMatrizOfer, reportDateFim);					
				}
			}
			
			formulario.setMapaDataIncial(mapaDataIncial);
			formulario.setMapaDataFinal(mapaDataFinal);
			this.setCabecalhoMatrizOfertaLista(cabecalhoMatrizOfertaLista);
		} else {
			
			this.setCabecalhoMatrizOfertaLista(null);
		}
		
		this.tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(), request);
		
		formulario.setCabecalhoMatrizOfertaLista(cabecalhoMatrizOfertaLista);
		
		if (actionForw == null) {
			return actionForw;			
		} else {
			return mapping.findForward("success");
		}
	}

	public ActionForward abrirPopupConfirmExclusaoCabecalho(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		if(request.getParameter("id_matriz_oferta") != null && request.getParameter("id_matriz_oferta").trim().length() > 0) {
			request.setAttribute("idMatrizOferta", request.getParameter("id_matriz_oferta"));
		}
		if(request.getParameter("nm_matriz_oferta") != null && request.getParameter("nm_matriz_oferta").trim().length() > 0) {
			request.setAttribute("nmMatrizOferta", request.getParameter("nm_matriz_oferta"));
		}
		return mapping.findForward("success");
	}

	public ActionForward excluirCabecalhoMatrizOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ErroInfo, RemoteException {
		
		CadastroCabecalhoMatrizOfertaForm formulario = (CadastroCabecalhoMatrizOfertaForm)form;
		ActionForward actionForw = new ActionForward();
		
		ExcluirCabecalhoRequest excluirCabecalhoRequest = new ExcluirCabecalhoRequest();
		ParametrosExcluirCabecalho parametrosExcluirCabecalho = new ParametrosExcluirCabecalho();

		if(formulario.getIdMatrizOferta() != null){
			parametrosExcluirCabecalho.setIdMatrizOferta(formulario.getIdMatrizOferta());
		}
		
		MatrizOfertaPortTypeProxy matrizOfertaPortTypeProxy = new MatrizOfertaPortTypeProxy();
		excluirCabecalhoRequest.setParametrosExcluirCabecalho(parametrosExcluirCabecalho);
		
		try {
			matrizOfertaPortTypeProxy.excluirCabecalho(excluirCabecalhoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'excluirCabecalhoMatrizOferta' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );				
		}
		
		if (actionForw == null) {
			return actionForw;			
		} 
		
		PrintWriter writer = null;
		try {
			response.setContentType("text/javascript");
			writer = response.getWriter();
			writer.println("$('botao_pesquisar_cabecalho_matriz_oferta').onclick();");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
		return null;
	}

	public ActionForward cadastrarNovoCabecalhoMatriz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws RemoteException {
		CadastroCabecalhoMatrizOfertaForm formulario = (CadastroCabecalhoMatrizOfertaForm)form;
		ActionForward actionForw = new ActionForward();
		
		BuscarListaTipoMatrizRequest buscarListaTipoMatrizRequest = new BuscarListaTipoMatrizRequest();
		BuscarListaTipoMatrizResponse buscarListaTipoMatrizResponse = new BuscarListaTipoMatrizResponse();
		MatrizOfertaPortTypeProxy matrizOfertaPortTypeProxy = new MatrizOfertaPortTypeProxy();
		
		try {
			buscarListaTipoMatrizResponse = matrizOfertaPortTypeProxy.buscarListaTipoMatriz(buscarListaTipoMatrizRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'cadastrarNovoCabecalhoMatriz' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );	
			
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		
		ResultadoBuscarListaTipoMatrizResultadoListaTipoMatrizTipoMatriz[] tipoMatrizLista = null;
		if (buscarListaTipoMatrizResponse.getResultadoBuscarListaTipoMatriz() != null) {
			tipoMatrizLista = buscarListaTipoMatrizResponse.getResultadoBuscarListaTipoMatriz().getResultadoListaTipoMatriz();
		}
		
		BuscarListaPlataformaRequest buscarListaPlataformaRequest = new BuscarListaPlataformaRequest();
		BuscarListaPlataformaResponse buscarListaPlataformaResponse = new BuscarListaPlataformaResponse();
		PlataformaPortTypeProxy plataformaPortTypeProxy = new PlataformaPortTypeProxy();
		
		try {
			buscarListaPlataformaResponse = plataformaPortTypeProxy.buscarListaPlataforma(buscarListaPlataformaRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'cadastrarNovoCabecalhoMatriz' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );	
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		
		ResultadoBuscarListaPlataformaPlataforma[] plataformaLista = null;
		if (buscarListaPlataformaResponse.getResultadoBuscarListaPlataforma() != null) {
			plataformaLista = buscarListaPlataformaResponse.getResultadoBuscarListaPlataforma();
			
		}
		
		BuscarListaTipoOperacaoRequest buscarListaTipoOperacaoRequest = new BuscarListaTipoOperacaoRequest();
		BuscarListaTipoOperacaoResponse buscarListaTipoOperacaoResponse = new BuscarListaTipoOperacaoResponse();
		TipoOperacaoPortTypeProxy tipoOperacaoPortTypeProxy = new TipoOperacaoPortTypeProxy();
		
		try {
			buscarListaTipoOperacaoResponse = tipoOperacaoPortTypeProxy.buscarListaTipoOperacao(buscarListaTipoOperacaoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'cadastrarNovoCabecalhoMatriz' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );	

		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		
		ResultadoBuscarListaTipoOperacaoListaTipoOperacaoTipoOperacao[] tipoOperacaoLista = null;
		if (buscarListaTipoOperacaoResponse.getResultadoBuscarListaTipoOperacao() != null) {
			tipoOperacaoLista = buscarListaTipoOperacaoResponse.getResultadoBuscarListaTipoOperacao().getListaTipoOperacao();			
		}
		
		ParametroBuscarCanalAtendimento parametroBuscarCanalAtendimento = new ParametroBuscarCanalAtendimento();
		parametroBuscarCanalAtendimento.setInVigencia(1L);
		CanalAtendimentoPortTypeProxy canalAtendimentoPortTypeProxy = new CanalAtendimentoPortTypeProxy();
		
		ResultadoBuscarCanalAtendimentoCanalAtendimento[] listaCanal = null;
		
		
		try {
			listaCanal = canalAtendimentoPortTypeProxy.buscarCanalAtendimento(parametroBuscarCanalAtendimento, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'abrirParaAlterarCabecalhoMatrizOferta' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );	
		}
		
		request.setAttribute("tipoOperacao", tipoOperacaoLista);
		formulario.setTipoOperacaoLista(tipoOperacaoLista);
		
		request.setAttribute("plataformas", plataformaLista);
		formulario.setPlataformaLista(plataformaLista);
		
		request.setAttribute("canais", listaCanal);
		formulario.setListaCanal(listaCanal);
		
		request.setAttribute("tipoMatriz", tipoMatrizLista);
		formulario.setTipoMatrizLista(tipoMatrizLista);
		
		
		if (actionForw == null) {
			return actionForw;			
		} else {
			return mapping.findForward("success");
		}
	}
	
	public ActionForward salvarNovoCabecalhoMatrizOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException, ParseException {
		
		CadastroCabecalhoMatrizOfertaForm formulario = (CadastroCabecalhoMatrizOfertaForm)form;
		ActionForward actionForw = new ActionForward();
		
		Calendar vigenciaInicial = Calendar.getInstance();
		Calendar vigenciaFinal = Calendar.getInstance();
		String stringDate = sdf.format(new Date());
		Date parseDate = sdf.parse(stringDate);
		Calendar currentDate = Calendar.getInstance();
		currentDate.setTime(parseDate);
		
		if(formulario.getIdsPlataforma() == null || formulario.getIdsPlataforma().length == 0) {
			CatalogoPRSException ex = new CatalogoPRSException("&Eacute; Obrigat&oacute;rio informar o campo Plataforma");
			this.CatalogoPRSExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), response);
			return null;
		}
		if(formulario.getIdsTipoOperacao() == null || formulario.getIdsTipoOperacao().length == 0) {
			CatalogoPRSException ex = new CatalogoPRSException("&Eacute; Obrigat&oacute;rio informar o campo Tipo Opera&ccedil;&atilde;o");
			this.CatalogoPRSExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), response);
			return null;
		}
		if(formulario.getIdsCanal() == null || formulario.getIdsCanal().length == 0) {
			CatalogoPRSException ex = new CatalogoPRSException("&Eacute; Obrigat&oacute;rio informar o campo Canal");
			this.CatalogoPRSExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), response);
			return null;
		}
		
		CriarCabecalhoRequest criarCabecalhoRequest = new CriarCabecalhoRequest();
		ParametrosCriarCabecalho parametrosCriarCabecalho = new ParametrosCriarCabecalho();

		if(formulario.getNmMatrizOferta() != null && formulario.getNmMatrizOferta().trim().length() > 0) {
			parametrosCriarCabecalho.setNmMatrizOferta(formulario.getNmMatrizOferta().toUpperCase());
		}else {
			CatalogoPRSException ex = new CatalogoPRSException("&Eacute; Obrigat&oacute;rio informar o campo Nome da Matriz");
			this.CatalogoPRSExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), response);
			return null;						
		}
		
		if(formulario.getIdMatrizOfertaTipo() != null && formulario.getIdMatrizOfertaTipo() > 0) {
			parametrosCriarCabecalho.setIdTipoMatriz(formulario.getIdMatrizOfertaTipo());
		}else {
			CatalogoPRSException ex = new CatalogoPRSException("&Eacute; Obrigat&oacute;rio informar o campo Tipo de Matriz");
			this.CatalogoPRSExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), response);
			return null;
		}
		
		if(formulario.getIdsPlataforma() != null) {
			long[] listaIdPlataforma = new long[formulario.getIdsPlataforma().length];
			
			for (int i = 0; i < formulario.getIdsPlataforma().length; i++) {
				listaIdPlataforma[i] = formulario.getIdsPlataforma()[i].longValue();
				parametrosCriarCabecalho.setListaIdPlataforma(listaIdPlataforma);				
			}
		}
		
		if(formulario.getIdsTipoOperacao() != null) {			
			long[] listaIdTipoOperacao = new long[formulario.getIdsTipoOperacao().length];
			
			for(int i = 0; i < formulario.getIdsTipoOperacao().length; i++) {
				listaIdTipoOperacao[i] = formulario.getIdsTipoOperacao()[i].longValue();
				parametrosCriarCabecalho.setListaIdTipoOperacao(listaIdTipoOperacao);
			}
		}
		
		if(formulario.getIdsCanal() != null) {
			long[] listaIdCanal = new long[formulario.getIdsCanal().length];
			
			for(int i = 0; i < formulario.getIdsCanal().length; i++) {
				listaIdCanal[i] = formulario.getIdsCanal()[i].longValue();
				parametrosCriarCabecalho.setListaIdCanal(listaIdCanal);
			}
		}
		
		//TimeZone timeZone;
		//timeZone = TimeZone.getTimeZone("GMT+0:00");
		//TimeZone.setDefault(timeZone);
		
		
		if(formulario.getDtVigenciaInicial() != null && !formulario.getDtVigenciaInicial().equals("")) {
			logging.log(Level.INFO, "### data inicial formulario (tipo String): {0} ###", new String []{formulario.getDtVigenciaInicial()});
			logging.log(Level.INFO, "### data inicial parseada (tipo Date): {0} ###", new Date [] {sdf.parse(formulario.getDtVigenciaInicial())});
			
			
			
			Date dataIni = (Date)sdf.parse(formulario.getDtVigenciaInicial());
			vigenciaInicial.setTime(dataIni);
			logging.log(Level.INFO, "### vigenciaInicial (tipo Calendar): \n {0} ###", new Calendar [] {vigenciaInicial});			
			
		}
		if(formulario.getDtVigenciaFinal() != null && !formulario.getDtVigenciaFinal().equals("")) {
			logging.log(Level.INFO, "### data final formulario (tipo String): {0} ###", new String []{formulario.getDtVigenciaFinal()});
			logging.log(Level.INFO, "### data final parseada (tipo Date): {0} ###", new Date [] {sdf.parse(formulario.getDtVigenciaFinal())});
			
			Date dataFim = (Date)sdf.parse(formulario.getDtVigenciaFinal());
			
			vigenciaFinal.setTime(dataFim);
			logging.log(Level.INFO, "### vigenciaFinal (tipo Calendar): \n {0} ###", new Calendar [] {vigenciaFinal});
		}
		
		if(vigenciaInicial.compareTo(currentDate) >= 0) {
			//parametrosCriarCabecalho.setDtInicial(formulario.getVigenciaInicial());
			parametrosCriarCabecalho.setDtInicial(vigenciaInicial);
		}else {
			CatalogoPRSException ex = new CatalogoPRSException("A Vig&ecirc;ncia Inicial deve ser maior ou igual a data corrente.");
			this.CatalogoPRSExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), response);
			return null;
			
		}
		if(vigenciaFinal.compareTo(vigenciaInicial) >= 0) {
			//parametrosCriarCabecalho.setDtFinal(formulario.getVigenciaFinal());
			parametrosCriarCabecalho.setDtFinal(vigenciaFinal);
		}else {
			CatalogoPRSException ex = new CatalogoPRSException("A Vig&ecirc;ncia Final deve ser maior ou igual a Vig&ecirc;ncia Inicial.");
			this.CatalogoPRSExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), response);
			return null;
		}
		
		MatrizOfertaPortTypeProxy matrizOfertaPortTypeProxy = new MatrizOfertaPortTypeProxy();
		criarCabecalhoRequest.setParametrosCriarCabecalho(parametrosCriarCabecalho);
		
		//criarCabecalhoRequest.getParametrosCriarCabecalho().getDtInicial().setTimeZone(timeZone);
		//criarCabecalhoRequest.getParametrosCriarCabecalho().getDtFinal().setTimeZone(timeZone);
		
		logging.log(Level.INFO, "****** DATAS NO ParametrosCriarCabecalho ANTES DE SER ENVIADAS AO SERVICO criarCabecalho  ******");
		
		logging.log(Level.INFO, "getDtInicial (tipo Calendar) : \n {0}", new Calendar [] {criarCabecalhoRequest.getParametrosCriarCabecalho().getDtInicial()});
		logging.log(Level.INFO, "getDtInicial (tipo Calendar): \n {0}", new Calendar [] {criarCabecalhoRequest.getParametrosCriarCabecalho().getDtFinal()});
		
		
		
		//System.out.println("ParametrosCriarCabecalho().getDtInicial(): " + criarCabecalhoRequest.getParametrosCriarCabecalho().getDtInicial().getTime());
		//System.out.println("ParametrosCriarCabecalho().getDtFinal(): " + criarCabecalhoRequest.getParametrosCriarCabecalho().getDtFinal().getTime());
		
		try {
			matrizOfertaPortTypeProxy.criarCabecalho(criarCabecalhoRequest,  this.getUserName(), this.getPassword());
		} 
		catch (AxisFault ex) {
			logger.debug("Exception no method: 'salvarNovoCabecalhoMatrizOferta' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );

		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		
		
		formulario.setDtVigencia(formulario.getDtVigenciaInicial());	
		
		if (actionForw == null) {
			return actionForw;			
		} else {
			return mapping.findForward("success");
		}
	}

	@SuppressWarnings("unused")
	public ActionForward abrirParaAlterarCabecalhoMatrizOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ParseException, br.com.vivo.catalogoPRS.ws.catalogoPlataforma.mc.geral.ErroInfo, RemoteException {
		CadastroCabecalhoMatrizOfertaForm formulario = (CadastroCabecalhoMatrizOfertaForm)form;
		ActionForward actionForw = new ActionForward();
		
		if(request.getParameter("id_matriz_oferta") != null && request.getParameter("id_matriz_oferta").trim().length() > 0) {
			request.setAttribute("idMatrizOferta", request.getParameter("id_matriz_oferta"));
		}
		if(request.getParameter("nm_matriz_oferta") != null && request.getParameter("nm_matriz_oferta").trim().length() > 0) {
			request.setAttribute("nmMatrizOferta", request.getParameter("nm_matriz_oferta"));
		}
		if(request.getParameter("id_tipo_matriz_oferta") != null && request.getParameter("id_tipo_matriz_oferta").trim().length() > 0) {
			request.setAttribute("idTipoMatrizOferta", request.getParameter("id_tipo_matriz_oferta"));
		}
		if(request.getParameter("vigencia_inicial") != null && request.getParameter("vigencia_inicial").trim().length() > 0) {
			request.setAttribute("vigenciaInicial", request.getParameter("vigencia_inicial"));
		}
		if(request.getParameter("vigencia_final") != null && request.getParameter("vigencia_final").trim().length() > 0) {
			request.setAttribute("vigenciaFinal", request.getParameter("vigencia_final"));
		}
		
		Integer indice = null;
		if(request.getParameter("indice") != null && request.getParameter("indice").trim().length() > 0) {
			indice = Integer.parseInt(request.getParameter("indice"));
		}
		
		Long[] idsPlataforma = null;
		Long[] idsTipoOperacao = null;
		Long[] idsCanal = null;
		
		ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaPlataformaPlataforma[] listaPlataforma = null;
		ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaTipoOperacaoTipoOperacao[] listaTipoOperacao = null;
		ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaCanalCanal[] listaCanal = null;
		
		
		if (cabecalhoMatrizOfertaLista != null) {
			for (int i = 0; i < cabecalhoMatrizOfertaLista.size(); i++) {								
				String id_matriz_oferta = request.getParameter("id_matriz_oferta");
				if (cabecalhoMatrizOfertaLista.get(i).getIdMatrizOferta().equals(Long.parseLong(id_matriz_oferta))) {
					listaPlataforma = cabecalhoMatrizOfertaLista.get(i).getListaPlataforma();
					listaTipoOperacao = cabecalhoMatrizOfertaLista.get(i).getListaTipoOperacao();	
					listaCanal = cabecalhoMatrizOfertaLista.get(i).getListaCanal();
					break;
				}
			}
			
			if (listaPlataforma != null) {
				idsPlataforma = new Long[listaPlataforma.length];
				for (int j = 0; j < listaPlataforma.length; j++ ) {
					idsPlataforma[j] = listaPlataforma[j].getIdPlataforma();					
				}
			}
			
			if (listaTipoOperacao != null) {
				idsTipoOperacao = new Long[listaTipoOperacao.length];
				for (int j = 0; j < listaTipoOperacao.length; j++) {
					idsTipoOperacao[j] = listaTipoOperacao[j].getIdTipoOperacao();
				}				
			}
			
			if (listaCanal != null) {
				idsCanal = new Long[listaCanal.length];
				for (int j = 0; j < listaCanal.length; j++) {
					idsCanal[j] = listaCanal[j].getIdCanal();
				}				
			}
		}
		 
		formulario.setIdsPlataforma(idsPlataforma);
		formulario.setIdsTipoOperacao(idsTipoOperacao);
		formulario.setIdsCanal(idsCanal);
		
		BuscarListaTipoMatrizRequest buscarListaTipoMatrizRequest = new BuscarListaTipoMatrizRequest();
		BuscarListaTipoMatrizResponse buscarListaTipoMatrizResponse = new BuscarListaTipoMatrizResponse();
		MatrizOfertaPortTypeProxy matrizOfertaPortTypeProxy = new MatrizOfertaPortTypeProxy();
		
		
		try {
			buscarListaTipoMatrizResponse = matrizOfertaPortTypeProxy.buscarListaTipoMatriz(buscarListaTipoMatrizRequest, this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'abrirParaAlterarCabecalhoMatrizOferta' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );			
		}
		
		ResultadoBuscarListaTipoMatrizResultadoListaTipoMatrizTipoMatriz[] tipoMatrizLista = null;
		if (buscarListaTipoMatrizResponse.getResultadoBuscarListaTipoMatriz() != null) {
			tipoMatrizLista = buscarListaTipoMatrizResponse.getResultadoBuscarListaTipoMatriz().getResultadoListaTipoMatriz();			
		}
		
		PlataformaPortTypeProxy plataformaPortTypeProxy = new PlataformaPortTypeProxy();
		BuscarListaPlataformaRequest buscarListaPlataformaRequest = new BuscarListaPlataformaRequest();
		BuscarListaPlataformaResponse buscarListaPlataformaResponse = new BuscarListaPlataformaResponse();
		
		try {
			buscarListaPlataformaResponse = plataformaPortTypeProxy.buscarListaPlataforma(buscarListaPlataformaRequest, this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'abrirParaAlterarCabecalhoMatrizOferta' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );				
		}
		
		ResultadoBuscarListaPlataformaPlataforma[]  plataformaLista = null;
		if (buscarListaPlataformaResponse.getResultadoBuscarListaPlataforma() != null) {
			plataformaLista = buscarListaPlataformaResponse.getResultadoBuscarListaPlataforma();			
		}
		
		TipoOperacaoPortTypeProxy tipoOperacaoPortTypeProxy = new TipoOperacaoPortTypeProxy();
		BuscarListaTipoOperacaoRequest buscarListaTipoOperacaoRequest = new BuscarListaTipoOperacaoRequest();
		BuscarListaTipoOperacaoResponse buscarListaTipoOperacaoResponse = new BuscarListaTipoOperacaoResponse();
		
		
		try {
			buscarListaTipoOperacaoResponse = tipoOperacaoPortTypeProxy.buscarListaTipoOperacao(buscarListaTipoOperacaoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'abrirParaAlterarCabecalhoMatrizOferta' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );		
		}
		
		ResultadoBuscarListaTipoOperacaoListaTipoOperacaoTipoOperacao[] tipoOperacaoLista = null;
		if (buscarListaTipoOperacaoResponse.getResultadoBuscarListaTipoOperacao() != null) {
			tipoOperacaoLista = buscarListaTipoOperacaoResponse.getResultadoBuscarListaTipoOperacao().getListaTipoOperacao();						
		}
		
		CanalAtendimentoPortTypeProxy canalAtendimentoPortTypeProxy = new CanalAtendimentoPortTypeProxy();
		ParametroBuscarCanalAtendimento parametroBuscarCanalAtendimento = new ParametroBuscarCanalAtendimento();				
		parametroBuscarCanalAtendimento.setInVigencia(1L);
		
		ResultadoBuscarCanalAtendimentoCanalAtendimento[] listaCanalResponse = null;
		
		
		try {
			listaCanalResponse = canalAtendimentoPortTypeProxy.buscarCanalAtendimento(parametroBuscarCanalAtendimento, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'abrirParaAlterarCabecalhoMatrizOferta' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );	
		}
	
		request.setAttribute("tipoMatriz", tipoMatrizLista);
		formulario.setTipoMatrizLista(tipoMatrizLista);
		
		request.setAttribute("plataforma", plataformaLista);
		formulario.setPlataformaLista(plataformaLista);
		
		request.setAttribute("tipoOperacao", tipoOperacaoLista);
		formulario.setTipoOperacaoLista(tipoOperacaoLista);
		
		request.setAttribute("canal", listaCanalResponse);
		formulario.setListaCanal(listaCanalResponse);
		
		if (actionForw == null) {
			return actionForw;			
		} else {
			return mapping.findForward("success");
		}
	}
	
	public ActionForward alterarCabecalhoMatrizOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException, ParseException {
		
		CadastroCabecalhoMatrizOfertaForm formulario = (CadastroCabecalhoMatrizOfertaForm)form;
		ActionForward actionForw = new ActionForward();
		
		Calendar vigenciaInicial = Calendar.getInstance();
		Calendar vigenciaFinal = Calendar.getInstance();
		String stringDate = sdf.format(new Date());
		Date parseDate = sdf.parse(stringDate);
		Calendar currentDate = Calendar.getInstance();
		currentDate.setTime(parseDate);
		
		if(formulario.getIdsPlataforma() == null || formulario.getIdsPlataforma().length == 0) {
			CatalogoPRSException ex = new CatalogoPRSException("&Eacute; Obrigat&oacute;rio informar o campo Plataforma");
			this.CatalogoPRSExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), response);
			return null;
		}
		if(formulario.getIdsTipoOperacao() == null || formulario.getIdsTipoOperacao().length == 0) {
			CatalogoPRSException ex = new CatalogoPRSException("&Eacute; Obrigat&oacute;rio informar o campo Tipo Opera&ccedil;&atilde;o");
			this.CatalogoPRSExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(),  response);
			return null;
		}
		if(formulario.getIdsCanal() == null || formulario.getIdsCanal().length == 0) {
			CatalogoPRSException ex = new CatalogoPRSException("&Eacute; Obrigat&oacute;rio informar o campo Canal");
			this.CatalogoPRSExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), response);
			return null;
		}
				
		AlterarCabecalhoRequest alterarCabecalhoRequest = new AlterarCabecalhoRequest();
		ParametrosAlterarCabecalho parametrosAlterarCabecalho = new ParametrosAlterarCabecalho();
		
		
		if(formulario.getIdMatrizOferta() != null && formulario.getIdMatrizOferta() > 0) {
			parametrosAlterarCabecalho.setIdMatrizOferta(formulario.getIdMatrizOferta());
		}
		
		if(formulario.getNmMatrizOferta() != null && formulario.getNmMatrizOferta().trim().length() > 0) {
			parametrosAlterarCabecalho.setNmMatrizOferta(formulario.getNmMatrizOferta().toUpperCase());
		} else {
			CatalogoPRSException ex = new CatalogoPRSException("&Eacute; Obrigat&oacute;rio informar o campo Nome da Matriz");
			this.CatalogoPRSExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), response);
			return null;
		}
		
		if(formulario.getIdMatrizOfertaTipo() != null && formulario.getIdMatrizOfertaTipo() > 0) {
			parametrosAlterarCabecalho.setIdTipoMatriz(formulario.getIdMatrizOfertaTipo());
		} else {
			CatalogoPRSException ex = new CatalogoPRSException("&Eacute; Obrigat&oacute;rio informar o campo Tipo de Matriz");
			this.CatalogoPRSExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), response);
			return null;
		}
		
		if(formulario.getIdsPlataforma() != null) {
			long[] listaIdPlataforma = new long [formulario.getIdsPlataforma().length];
			
			for (int i = 0; i < formulario.getIdsPlataforma().length; i++) {
				listaIdPlataforma[i] = formulario.getIdsPlataforma()[i].longValue();
				parametrosAlterarCabecalho.setListaIdPlataforma(listaIdPlataforma);
				
			}
		}
		
		if(formulario.getIdsTipoOperacao() != null) {
			long[] listaIdTipoOperacao = new long [formulario.getIdsTipoOperacao().length];
			
			for (int i = 0; i < formulario.getIdsTipoOperacao().length; i++) {
				listaIdTipoOperacao[i] = formulario.getIdsTipoOperacao()[i].longValue();
				parametrosAlterarCabecalho.setListaIdTipoOperacao(listaIdTipoOperacao);
			}
		}
		
		if(formulario.getIdsCanal() != null) {
			parametrosAlterarCabecalho.getListaIdCanal();
			long[] listaIdCanal = new long [formulario.getIdsCanal().length];
			
			for (int i = 0; i < formulario.getIdsCanal().length; i++) {
				listaIdCanal[i] = formulario.getIdsCanal()[i].longValue();
				parametrosAlterarCabecalho.setListaIdCanal(listaIdCanal);				
			}
		}
		
		if(formulario.getDtVigenciaInicial() != null && !formulario.getDtVigenciaInicial().equals("")) {
			vigenciaInicial.setTime(sdf.parse(formulario.getDtVigenciaInicial()));
		}
		if(formulario.getDtVigenciaFinal() != null && !formulario.getDtVigenciaFinal().equals("")) {
			vigenciaFinal.setTime(sdf.parse(formulario.getDtVigenciaFinal()));
		}
		if((vigenciaInicial.compareTo(currentDate) >= 0) || (formulario.getDtVigenciaInicial().equalsIgnoreCase(formulario.getInicialVigencia()))) {
			parametrosAlterarCabecalho.setDtInicial(formulario.getVigenciaInicial());
		} else {
			CatalogoPRSException ex = new CatalogoPRSException("A Vig&ecirc;ncia Inicial deve ser maior ou igual a data corrente.");
			this.CatalogoPRSExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), response);
			return null;
		}
		if((vigenciaFinal.compareTo(vigenciaInicial) >= 0) || (formulario.getDtVigenciaFinal().equalsIgnoreCase(formulario.getFinalVigencia()))) {
			parametrosAlterarCabecalho.setDtFinal(formulario.getVigenciaFinal());
		} else {
			CatalogoPRSException ex = new CatalogoPRSException("A Vig&ecirc;ncia Final deve ser maior ou igual a Vig&ecirc;ncia Inicial.");
			this.CatalogoPRSExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), response);
			return null;
		}
				
		MatrizOfertaPortTypeProxy matrizOfertaPortTypeProxy = new MatrizOfertaPortTypeProxy();
		alterarCabecalhoRequest.setParametrosAlterarCabecalho(parametrosAlterarCabecalho);
		
		
		try {
			matrizOfertaPortTypeProxy.alterarCabecalho(alterarCabecalhoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'abrirParaAlterarCabecalhoMatrizOferta' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );	
		} catch (RemoteException e1) {			
			e1.printStackTrace();
		}
		
		if (actionForw == null) {
			return actionForw;			
		}
		
		PrintWriter writer = null;
		try {
			response.setContentType("text/javascript");
			writer = response.getWriter();
			writer.println("$('botao_pesquisar_cabecalho_matriz_oferta').onclick();");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
		
		
		return null;
		
	}

	public ActionForward pesquisarListaVariaveisMatrizOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws RemoteException {
		CadastroCabecalhoMatrizOfertaForm formulario = (CadastroCabecalhoMatrizOfertaForm)form;
		
		Integer pagina_solicitada = null;
		if(request.getParameter("pagina_solicitada") != null && request.getParameter("pagina_solicitada").trim().length() > 0) {
			pagina_solicitada = Integer.valueOf(request.getParameter("pagina_solicitada"));
		}
		
		BuscarListaVariaveisRequest buscarListaVariaveisRequest = new BuscarListaVariaveisRequest();
		ParametrosBuscarListaVariaveis parametrosBuscarListaVariaveis = new ParametrosBuscarListaVariaveis();
		PaginacaoIn paginacaoIn = new PaginacaoIn();
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		parametrosBuscarListaVariaveis.setPaginacaoIn(paginacaoIn);
		buscarListaVariaveisRequest.setParametrosBuscarListaVariaveis(parametrosBuscarListaVariaveis);
		
		if(pagina_solicitada != null && !pagina_solicitada.equals("")) {
			paginacaoIn.setPaginaSolicitada(pagina_solicitada);
		}else {
			paginacaoIn.setPaginaSolicitada(1);
		}
		if(request.getParameter("id_matriz_oferta") != null && request.getParameter("id_matriz_oferta").trim().length() > 0) {
			parametrosBuscarListaVariaveis.setIdMatrizOferta(Long.valueOf(request.getParameter("id_matriz_oferta")));
			request.setAttribute("idMatrizOferta", request.getParameter("id_matriz_oferta"));
		}
		
			
		MatrizOfertaPortTypeProxy matrizOfertaPortTypeProxy = new MatrizOfertaPortTypeProxy();
		BuscarListaVariaveisResponse buscarListaVariaveisResponse = new BuscarListaVariaveisResponse();
		

		ResultadoBuscarListaVariaveisListaVariaveisVariaveis[] listaVariaveis = null;
		try {
			buscarListaVariaveisResponse = matrizOfertaPortTypeProxy.buscarListaVariaveis(buscarListaVariaveisRequest, this.getUserName(),this.getPassword());
			
			br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut paginacaoOut = new PaginacaoOut();
			
		
			if (buscarListaVariaveisResponse.getResultadoBuscarListaVariaveis() != null) {
				listaVariaveis = buscarListaVariaveisResponse.getResultadoBuscarListaVariaveis().getListaVariaveis();	
				paginacaoOut = buscarListaVariaveisResponse.getResultadoBuscarListaVariaveis().getPaginacaoOut();
			}			
			this.tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(), request);
		} catch (AxisFault e) {
			String codigoErro = SOAPUtil.obterCodigoErroAxisFault(e);
			Long codErro = Long.parseLong(codigoErro);
							
			if (codErro != null && codErro == CODIGOERROBUSCARLISTAVARIAVEIS_LISTA_VAZIA) {
				listaVariaveis = null;
			}
		}
		
		request.setAttribute("variaveis", listaVariaveis);
		formulario.setListaVariaveis(listaVariaveis);
		return mapping.findForward("success");
	}
	
	public ActionForward abrirPopupConfirmExclusaoVariavel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		if(request.getParameter("id_matriz_oferta_variaveis") != null && !request.getParameter("id_matriz_oferta_variaveis").equalsIgnoreCase("")) {
			request.setAttribute("idMatrizOfertaVariaveis", request.getParameter("id_matriz_oferta_variaveis"));
		}
		return mapping.findForward("success");
	}
	
	public ActionForward excluirVariavelMatrizOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		CadastroCabecalhoMatrizOfertaForm formulario = (CadastroCabecalhoMatrizOfertaForm)form;
		ActionForward actionForw = new ActionForward();
		
		ExcluirVariaveisRequest excluirVariaveisRequest = new ExcluirVariaveisRequest();
		ParametrosExcluirVariavel parametrosExcluirVariavel = new ParametrosExcluirVariavel();
		
		if(formulario.getIdMatrizOfertaVariaveis() != null && !formulario.getIdMatrizOfertaVariaveis().equals("")) {
			parametrosExcluirVariavel.setIdMatrizOfertaVariaveis(formulario.getIdMatrizOfertaVariaveis());
		}
		
		MatrizOfertaPortTypeProxy matrizOfertaPortTypeProxy = new MatrizOfertaPortTypeProxy();
		excluirVariaveisRequest.setParametrosExcluirVariavel(parametrosExcluirVariavel);
		
		try {
			matrizOfertaPortTypeProxy.excluirVariaveis(excluirVariaveisRequest, this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'excluirVariavelMatrizOferta' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );	

		} catch (RemoteException e1) {
			
			e1.printStackTrace();
		}
		
		if (actionForw == null) {
			return actionForw;			
		}
		
		PrintWriter writer = null;
		try {
			response.setContentType("text/javascript");
			writer = response.getWriter();
			writer.println("$('botao_pesquisar_variaveis_matriz_oferta').onclick();");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
		return null;
	}
	
	public ActionForward redirectForItens(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ParseException {

		CadastroCabecalhoMatrizOfertaForm formulario = (CadastroCabecalhoMatrizOfertaForm)form;
		
		HttpSession session = request.getSession();
		Date dtVigencia;
		if(request.getParameter("id_matriz_oferta") != null && request.getParameter("id_matriz_oferta").trim().length() > 0) {
			formulario.setIdMatrizOferta(Long.valueOf(request.getParameter("id_matriz_oferta")));
		}
		if(request.getParameter("vigencia_inicial") != null && request.getParameter("vigencia_inicial").trim().length() > 0) {
			dtVigencia = sdf.parse(request.getParameter("vigencia_inicial"));
			formulario.setDtVigenciaInicial(sdf.format(dtVigencia));
		}
		session.setAttribute("formBean", formulario);
		PrintWriter writer = null;
		try {
			response.setContentType("text/javascript");
			writer = response.getWriter();
			writer.println("$('link_itens_matriz_oferta').onclick();");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			writer.close();
		}
		return null;
	}

	public List<ResultadoBuscarListaCabecalhoListaCabecalhoCabecalho> getCabecalhoMatrizOfertaLista() {
		return cabecalhoMatrizOfertaLista;
	}

	public void setCabecalhoMatrizOfertaLista(
			List<ResultadoBuscarListaCabecalhoListaCabecalhoCabecalho> cabecalhoMatrizOfertaLista) {
		this.cabecalhoMatrizOfertaLista = cabecalhoMatrizOfertaLista;
	}

	public ActionForward cadastrarNovaVariavelCabecalhoMatriz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		CadastroCabecalhoMatrizOfertaForm formulario = (CadastroCabecalhoMatrizOfertaForm)form;
		ActionForward actionForw = new ActionForward();
		
		if(request.getParameter("id_matriz_oferta") != null && request.getParameter("id_matriz_oferta").trim().length() > 0) {
			formulario.setIdMatrizOferta(Long.valueOf(request.getParameter("id_matriz_oferta")));
		}

		BuscarListaTipoClienteRequest buscarListaTipoClienteRequest = new BuscarListaTipoClienteRequest();
		BuscarListaTipoClienteResponse buscarListaTipoClienteResponse = new BuscarListaTipoClienteResponse();
		TipoClientePortTypeProxy tipoClientePortTypeProxy = new TipoClientePortTypeProxy();
		
		
		try {
			buscarListaTipoClienteResponse = tipoClientePortTypeProxy.buscarListaTipoCliente(buscarListaTipoClienteRequest, this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'cadastrarNovaVariavelCabecalhoMatriz' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );	
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		
		ResultadoBuscarListaTipoClienteTipoCliente[] tipoClienteLista = null;
		if (buscarListaTipoClienteResponse.getResultadoBuscarListaTipoCliente() != null) {
			tipoClienteLista = buscarListaTipoClienteResponse.getResultadoBuscarListaTipoCliente();
			
		}
		
		BuscarListaCarteiraRequest buscarListaCarteiraRequest = new BuscarListaCarteiraRequest();
		BuscarListaCarteiraResponse buscarListaCarteiraResponse = new BuscarListaCarteiraResponse();
		CarteiraPortTypeProxy carteiraPortTypeProxy = new CarteiraPortTypeProxy();
		
		ParametrosBuscarListaCarteira parametrosBuscarListaCarteira = new ParametrosBuscarListaCarteira();
		buscarListaCarteiraRequest.setParametrosBuscarListaCarteira(parametrosBuscarListaCarteira);
		
		try {
			buscarListaCarteiraResponse = carteiraPortTypeProxy.buscarListaCarteira(buscarListaCarteiraRequest, this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'cadastrarNovaVariavelCabecalhoMatriz' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );	
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		
		ResultadoBuscarListaCarteiraListaCarteiraCarteira[] carteiraLista = null;
		if (buscarListaCarteiraResponse.getResultadoBuscarListaCarteira() != null) {
			carteiraLista = buscarListaCarteiraResponse.getResultadoBuscarListaCarteira().getListaCarteira();
		}
		
		BuscarListaSegmentacaoRequest buscarListaSegmentacaoRequest = new BuscarListaSegmentacaoRequest();
		BuscarListaSegmentacaoResponse buscarListaSegmentacaoResponse = new BuscarListaSegmentacaoResponse();
		SegmentoPortTypeProxy segmentoPortTypeProxy = new SegmentoPortTypeProxy();
		
		
		try {
			buscarListaSegmentacaoResponse = segmentoPortTypeProxy.buscarListaSegmentacao(buscarListaSegmentacaoRequest, this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'cadastrarNovaVariavelCabecalhoMatriz' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );	
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		
		ResultadoBuscarListaSegmentoListaSegmentoSegmento[] segmentoLista = null;
		if (buscarListaSegmentacaoResponse.getResultadoBuscarListaSegmento() != null) {
			segmentoLista = buscarListaSegmentacaoResponse.getResultadoBuscarListaSegmento().getListaSegmento();
		}
		
		
		if(request.getParameter("id_matriz_oferta") != null && request.getParameter("id_matriz_oferta").trim().length() > 0) {
			request.setAttribute("idMatrizOferta", request.getParameter("id_matriz_oferta"));
		}
		request.setAttribute("tipoCliente", tipoClienteLista);
		request.setAttribute("carteira", carteiraLista);
		request.setAttribute("segmento", segmentoLista);
		
				
		
		if (actionForw == null) {
			return actionForw;			
		} else {
			return mapping.findForward("success");
		}
	}
	
	public ActionForward salvarNovaVariavelCabecalhoMatriz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		CadastroCabecalhoMatrizOfertaForm formulario = (CadastroCabecalhoMatrizOfertaForm)form;
		ActionForward actionForw = new ActionForward();
		
		AssociarVariaveisRequest associarVariaveisRequest = new AssociarVariaveisRequest();
		ParametrosAssociarVariaveis parametrosAssociarVariaveis = new ParametrosAssociarVariaveis();
		
		if(formulario.getIdMatrizOferta() != null && !formulario.getIdMatrizOferta().equals("")) {
			parametrosAssociarVariaveis.setIdMatrizOferta(formulario.getIdMatrizOferta());
		}
		if(formulario.getIdTipoCliente() != null && !formulario.getIdTipoCliente().equals("")) {
			parametrosAssociarVariaveis.setIdTipoCliente(formulario.getIdTipoCliente());
		}
		if(!formulario.getSgCarteira().equalsIgnoreCase("todos") && formulario.getSgCarteira().trim().length() > 0) {
			parametrosAssociarVariaveis.setSgCarteira(formulario.getSgCarteira());
		}
		if(!formulario.getSgSegmento().equalsIgnoreCase("todos") && formulario.getSgSegmento().trim().length() > 0) {
			parametrosAssociarVariaveis.setSegmento(formulario.getSgSegmento());
		}
		
		MatrizOfertaPortTypeProxy matrizOfertaPortTypeProxy = new MatrizOfertaPortTypeProxy();
		associarVariaveisRequest.setParametrosAssociarVariaveis(parametrosAssociarVariaveis);
		
		try {
			matrizOfertaPortTypeProxy.associarVariaveis(associarVariaveisRequest, this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'salvarNovaVariavelCabecalhoMatriz' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );	
			
		} catch (RemoteException e1) {
			
			e1.printStackTrace();
		}
		
		if (actionForw == null) {
			return actionForw;			
		}
		
		
		PrintWriter writer = null;
		try {
			response.setContentType("text/javascript");
			writer = response.getWriter();
			writer.println("$('botao_pesquisar_variaveis_matriz_oferta').onclick();");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			writer.close();
		}
		return null;
	}

	public ActionForward buscarListaCarteira(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		CadastroCabecalhoMatrizOfertaForm formulario = (CadastroCabecalhoMatrizOfertaForm)form;
		ActionForward actionForw = new ActionForward();
		
		BuscarListaCarteiraRequest buscarListaCarteiraRequest = new BuscarListaCarteiraRequest();
		ParametrosBuscarListaCarteira parametrosBuscarListaCarteira = new ParametrosBuscarListaCarteira();
		
		if(!request.getParameter("idTipoCliente").equalsIgnoreCase("null") && request.getParameter("idTipoCliente").trim().length() > 0) {
			parametrosBuscarListaCarteira.setIdTipoCliente(Long.valueOf(request.getParameter("idTipoCliente")));
		}
		PrintWriter writer = null;
		try {
			
			CarteiraPortTypeProxy carteiraPortTypeProxy = new CarteiraPortTypeProxy();
			BuscarListaCarteiraResponse buscarListaCarteiraResponse = new BuscarListaCarteiraResponse();
			buscarListaCarteiraRequest.setParametrosBuscarListaCarteira(parametrosBuscarListaCarteira);
			
			try {
				buscarListaCarteiraResponse = carteiraPortTypeProxy.buscarListaCarteira(buscarListaCarteiraRequest, this.getUserName(),this.getPassword());
			} catch (AxisFault ex) {
				logger.debug("Exception no method: 'buscarListaCarteira' da classe: " + getClass().getName());
				actionForw = this.AxisFaultExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );	

			}			
			
			if (actionForw == null) {
				return actionForw;			
			}
			
			ResultadoBuscarListaCarteiraListaCarteiraCarteira[] listaCarteira = null;
			if (buscarListaCarteiraResponse.getResultadoBuscarListaCarteira() != null) {
				listaCarteira = buscarListaCarteiraResponse.getResultadoBuscarListaCarteira().getListaCarteira();
				
			}
						
			JSONArray arrayCarteiras = new JSONArray();
			
			if (listaCarteira.length > 0) {
				for (int i = 0; i < listaCarteira.length; i++) {
					JSONObject objectJson = new JSONObject();
					
					objectJson.put("id", listaCarteira[i].getSgCarteira());
					objectJson.put("descricao", listaCarteira[i].getDsCarteira());
					arrayCarteiras.put(objectJson);
				}								
			}
			
			String result = new String();
			JSONObject resultadoJSON = new JSONObject();
			result = resultadoJSON.put("carteiras", arrayCarteiras).toString();
			writer = response.getWriter();
			writer.println(result);
			writer.flush();
			
		}catch (JSONException e) {
			logger.error("error ao criar o objeto JSON de carteira.");
		} catch (IOException e) {
			logger.error("error ao escrever no output do response.");
		} finally {
			writer.close();
		}
		return null;
	}

	
	
}