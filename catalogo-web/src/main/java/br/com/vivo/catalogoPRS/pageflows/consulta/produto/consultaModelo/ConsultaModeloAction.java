package br.com.vivo.catalogoPRS.pageflows.consulta.produto.consultaModelo;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.AxisFault;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.ConsultaModeloForm;
import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;
import br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.mc.geral.ErroInfo;
import br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.BuscarListaCaracteristicaComValorRequest;
import br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.BuscarListaCaracteristicaComValorResponse;
import br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.BuscarListaCaracteristicaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.BuscarListaCaracteristicaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.CaracteristicaPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ListaCaracteristicaCaracteristica;
import br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaCaracteristica;
import br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaCaracteristicaComValor;
import br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosBuscarListaCaracteristicaRaizCaracteristicaIn;
import br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ResultadoCaracteristica;
import br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn;
import br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscaCaracteristicaProdutoModelo;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarDetalhesModeloRequest;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarDetalhesModeloResponse;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarListaModeloRequest;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarListaModeloResponse;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarModeloListaTecnologiaTipoFrequenciaValorFrequenciaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarModeloListaTecnologiaTipoFrequenciaValorFrequenciaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.Caracteristica;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ExportarModeloRequest;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ExportarModeloResponse;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaSistemaProdutoSistemaProduto;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaTecnologiaTecnologia;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaTipoFrequenciaTipoFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaValorFrequenciaValorFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.Modelo;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ModeloPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarDetalhesModelo;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaModelo;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaModeloParametrosModeloIn;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOut;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarListaModelo;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarListaModeloListaModelosRetornoModelo;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarModeloModelo;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarModeloModeloListaCaracteristicasCaracteristica;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarModeloModeloListaProdutosProduto;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarModeloModeloListaTecnologiaTpFrequenciaVLTecnologiaTpFrequenciaVL;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ValorCaracteristica;
import br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.BuscarListaMultTMCorClassRequest;
import br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.BuscarListaMultTMCorClassResponse;
import br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.MultimidiaPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ParamBuscarListaMultTMCorClass;
import br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ResultBuscarListaMultTMCorClassMultimidia;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaTecnologiaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaTecnologiaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarListaTecnologiaTecnologia;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.TecnologiaPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.BuscarListaTipoProdutoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.BuscarListaTipoProdutoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.ResultadoListarTipoProdutoTipoProduto;
import br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.TipoProdutoPortTypeProxy;
import edu.emory.mathcs.backport.java.util.Arrays;

public class ConsultaModeloAction extends BaseMappingDispatchAction {
	
	private static Logger logger = Logger.getLogger(ConsultaModeloAction.class);


	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	HttpServletResponse response) throws Exception {
		
		ActionForward actionForw = new ActionForward();
		
		TipoProdutoPortTypeProxy tipoProdutoPortTypeProxy = new TipoProdutoPortTypeProxy();
		BuscarListaTipoProdutoRequest buscarListaTipoProdutoRequest = new BuscarListaTipoProdutoRequest();
		BuscarListaTipoProdutoResponse buscarListaTipoProdutoResponse = null;
		
		try{
			buscarListaTipoProdutoResponse = tipoProdutoPortTypeProxy.buscarListaTipoProduto(buscarListaTipoProdutoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'begin' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, ConsultaModeloAction.class.getName(), ex.getMessage(), form, response );	
		}
		
		ResultadoListarTipoProdutoTipoProduto[] lista = null;
		if (buscarListaTipoProdutoResponse.getResultadoListarTipoProduto() != null) {
			lista = buscarListaTipoProdutoResponse.getResultadoListarTipoProduto();
			
		}
		
		
		
		request.setAttribute("tipos_produto", lista);
		
		if (actionForw == null) {
			return actionForw;			
		} else {
			return mapping.findForward("success");
		}
		
	}
	
	public ActionForward abrirpopupTecnologiaModelo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	HttpServletResponse response) throws Exception {
		
		ActionForward actionForw = new ActionForward();
		
		ConsultaModeloForm formPesquisa = (ConsultaModeloForm)form;
		String tecnologiasCSV = request.getParameter("hiddenTecnologias");
		if (tecnologiasCSV != null) {
			formPesquisa.setTecnologiasSelecionadas( tecnologiasCSV.split(","));
			
		}
		
		TecnologiaPortTypeProxy tecnologiaPortTypeProxy = new TecnologiaPortTypeProxy();
		BuscarListaTecnologiaRequest buscarListaTecnologiaRequest = new BuscarListaTecnologiaRequest();
		BuscarListaTecnologiaResponse buscarListaTecnologiaResponse = null;
		
		try{
			buscarListaTecnologiaResponse = tecnologiaPortTypeProxy.buscarListaTecnologia(buscarListaTecnologiaRequest, this.getUserName(), this.getPassword());	
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'abrirpopupTecnologiaModelo' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, ConsultaModeloAction.class.getName(), ex.getMessage(), formPesquisa, response );	
		}
			
		
		
		ResultadoBuscarListaTecnologiaTecnologia[] lista = buscarListaTecnologiaResponse.getResultadoBuscarListaTecnologia();
		
		formPesquisa.setTecnologiasEncontradas(lista);
		
		if (actionForw == null) {
			return actionForw;			
		} else {
			return mapping.findForward("success");
		}
		
		//return mapping.findForward("success");
	}
	
	
	public ActionForward abrirpopupCaracteristicaModelo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception {		

		int elementosPorPagina = ApplicationConfiguration.getElementosPorPaginaNosPopups();
		ConsultaModeloForm formPesquisa = (ConsultaModeloForm)form;
		
		
		String caracteristicasCSV = request.getParameter("hiddenCaracteristicas");
		if (caracteristicasCSV != null) {
			
			formPesquisa.setCaracteristicasSelecionadas(caracteristicasCSV.split(","));
		}
		String valoresCaracteristicasCSV = request.getParameter("hiddenValoresCaracteristicas");
		if (valoresCaracteristicasCSV != null) {
			
			formPesquisa.setValoresCaracteristicasSelecionadas(valoresCaracteristicasCSV.split(","));
		}

		int paginaSolicitada = 1;
		String paginaSolicitadaString = request.getParameter("pagina_solicitada");
		if (paginaSolicitadaString != null && !paginaSolicitadaString.trim().equals("")) {
			paginaSolicitada = Integer.valueOf(paginaSolicitadaString);
		}
		
		ResultadoCaracteristica  resultadoCaracteristica = buscarCaracteristicas(paginaSolicitada, elementosPorPagina);
		
		ListaCaracteristicaCaracteristica[] lista = resultadoCaracteristica.getListaCaracteristica();
		
		List<Caracteristica> vlcaracteristicaList;
		if (lista != null)
			vlcaracteristicaList =  Arrays.asList(lista);
		else
			vlcaracteristicaList = new ArrayList<Caracteristica>();
		
		
		PaginacaoOut paginacaoOut = resultadoCaracteristica.getPaginacaoOut();
		int paginaAtual = paginacaoOut.getPaginaAtual();
		int totalRegistros = paginacaoOut.getTotalRegistros();
		long totalPagina = Math.round((totalRegistros / elementosPorPagina) + 0.49d);
		
		formPesquisa.setListaCaracteristicas(vlcaracteristicaList);
		
		request.setAttribute("listaCaracteristicas", vlcaracteristicaList);
		request.setAttribute("size_caracteristicas", vlcaracteristicaList.size());
		request.setAttribute("paginaAtual", paginaAtual);
		request.setAttribute("totalPagina", totalPagina);
		
		return mapping.findForward("success");
	}	
	
	private ResultadoCaracteristica buscarCaracteristicas(int paginaSolicitada, int elementosPorPagina) throws ErroInfo, RemoteException {
		
				
		CaracteristicaPortTypeProxy caracteristicaPortTypeProxy = new CaracteristicaPortTypeProxy();
		BuscarListaCaracteristicaComValorRequest buscarListaCaracteristicaComValorRequest = new BuscarListaCaracteristicaComValorRequest();
		ParametrosBuscarListaCaracteristicaComValor parametrosBuscarListaCaracteristicaComValor = new ParametrosBuscarListaCaracteristicaComValor();
		PaginacaoIn paginacaoIn = new PaginacaoIn();
		
		
		paginacaoIn.setItensPorPagina(elementosPorPagina);
		paginacaoIn.setPaginaSolicitada(paginaSolicitada);
		
		parametrosBuscarListaCaracteristicaComValor.setPaginacaoIn(paginacaoIn);
		
		buscarListaCaracteristicaComValorRequest.setParametrosBuscarListaCaracteristicaComValor(parametrosBuscarListaCaracteristicaComValor);
		
		BuscarListaCaracteristicaComValorResponse buscarListaCaracteristicaComValorResponse;
		
		buscarListaCaracteristicaComValorResponse = caracteristicaPortTypeProxy.buscarListaCaracteristicaComValor(buscarListaCaracteristicaComValorRequest,this.getUserName(), this.getPassword());
		
		ResultadoCaracteristica resultadoCaracteristica = buscarListaCaracteristicaComValorResponse.getResultadoCaracteristica();
		
		return resultadoCaracteristica;
	}	
	
	
	
	
	public ActionForward pesquisarModelo(ActionMapping mapping, ActionForm formPesquisa, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ConsultaModeloForm form = (ConsultaModeloForm)formPesquisa;
		
		
		if(form.getNomesTecnologias()!=null && form.getNomesTecnologias().length() > 0){
			request.setAttribute("nomes_tecnologias", form.getNomesTecnologias());
		}
		if(form.getNomesCaracteristicasValores() != null && form.getNomesCaracteristicasValores().length() > 0){
			try {
				StringBuffer sb = new StringBuffer();
				JSONObject hashCaracteristicas = new JSONObject(form.getNomesCaracteristicasValores());
				boolean first = true;
				for(Iterator itCaracteristica = hashCaracteristicas.keys(); itCaracteristica.hasNext();){
					if(first){
						first=false;
					}else{
						sb.append(", ");
					}
					String caracteristica = (String)itCaracteristica.next();
					sb.append(caracteristica);
					String valoresCaracteristica = hashCaracteristicas.getString(caracteristica);
					if(valoresCaracteristica != null && valoresCaracteristica.length() >0){
						sb.append("(");
						sb.append(valoresCaracteristica);
						sb.append(")");
					}
				}
				request.setAttribute("nomes_valores_caracteristicas", sb.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}
		

		String[] idsTecnologias = splitAndClean(form.getTecnologias());
		String[] idsCaracteristicas = splitAndClean(form.getCaracteristicas());
		String[] idsValorescaracteristicas = splitAndClean(form.getValoresCaracteristicas());
		
		BuscarListaModeloRequest buscarListaModeloRequest = new BuscarListaModeloRequest();
		ParametrosBuscarListaModelo parametrosBuscarListaModelo = new ParametrosBuscarListaModelo();
		ParametrosBuscarListaModeloParametrosModeloIn parametrosBuscarListaModeloParametrosModeloIn = new ParametrosBuscarListaModeloParametrosModeloIn();
		br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.PaginacaoIn paginacaoIn = new br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.PaginacaoIn();

		
		int elementosPorPagina = ApplicationConfiguration.getElementosPorPagina();
		paginacaoIn.setItensPorPagina(elementosPorPagina);
		if(form.getPaginaSolicitada() == null){
			paginacaoIn.setPaginaSolicitada(1);
		}else{
			paginacaoIn.setPaginaSolicitada(form.getPaginaSolicitada());
		}
		parametrosBuscarListaModeloParametrosModeloIn.setPaginacaoIn(paginacaoIn);
		
		if (form.getTipoProduto() != null){
			parametrosBuscarListaModeloParametrosModeloIn.setIdTipoProduto(form.getTipoProduto());
		}
		
		
		if (form.getModelo() != null && form.getModelo().length() > 0){
			parametrosBuscarListaModeloParametrosModeloIn.setNmModelo(form.getModelo());
		}
		
		if (form.getFabricante() != null && form.getFabricante().intValue()!=0){
			parametrosBuscarListaModeloParametrosModeloIn.setIdFabricante(form.getFabricante());
		}
		
		
		if (idsCaracteristicas != null && idsCaracteristicas.length > 0) {
			long[] listaCaracteristicas = new long[idsCaracteristicas.length];
			for(int i=0;i<idsCaracteristicas.length;i++){
				listaCaracteristicas[i]=Long.valueOf(idsCaracteristicas[i]);
			}

			parametrosBuscarListaModeloParametrosModeloIn.setListaCaracteristica(listaCaracteristicas);

		}
		if (idsValorescaracteristicas != null && idsValorescaracteristicas.length > 0) {
			long[] listaValorCaracteristicas = new long[idsValorescaracteristicas.length];
			for(int i=0;i<idsValorescaracteristicas.length;i++){
				listaValorCaracteristicas[i]= Long.valueOf(idsValorescaracteristicas[i]);
				
			}
			parametrosBuscarListaModeloParametrosModeloIn.setListaValorCaracteristica(listaValorCaracteristicas);
		}

		
		if (idsTecnologias != null && idsTecnologias.length > 0) {

			long[] listaTecnologia = new long[idsTecnologias.length];
			for(int i=0;i<idsTecnologias.length;i++){
				listaTecnologia[i]= Long.valueOf(idsTecnologias[i]);
			}
			parametrosBuscarListaModeloParametrosModeloIn.setListaTecnologia(listaTecnologia);
		}

		if (form.getPeriodoInicio() != null && form.getPeriodoInicio().length() > 0){
			parametrosBuscarListaModeloParametrosModeloIn.setInicio(form.getPeriodoInicio());
		}
			

		if (form.getPeriodoFim() != null && form.getPeriodoFim().length() > 0){
			parametrosBuscarListaModeloParametrosModeloIn.setFim(form.getPeriodoFim());
		}
			
		if(form.getPeriodoInicio() != null && !form.getPeriodoInicio().trim().equals("") && form.getPeriodoFim() != null && !form.getPeriodoFim().trim().equals("")){
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			try {
				Date inicio = dateFormat.parse(form.getPeriodoInicio());
				Date fim = dateFormat.parse(form.getPeriodoFim());
				if(inicio.after(fim)){
					CatalogoPRSException ex = new CatalogoPRSException("Data de in�cio deve ser inferior a data de fim.");					
					this.CatalogoPRSExceptionHandler(ex, ConsultaModeloAction.class.getName(), response);
					return null;
				}
			} catch (ParseException e) {
				CatalogoPRSException ex = new CatalogoPRSException("Formato da data : DD/MM/AAAA");
				this.CatalogoPRSExceptionHandler(ex, ConsultaModeloAction.class.getName(), response);
				return null;				
			}
		}
		

		ModeloPortTypeProxy modeloPortTypeProxy = new ModeloPortTypeProxy();
		
		parametrosBuscarListaModelo.setParametrosModeloIn(parametrosBuscarListaModeloParametrosModeloIn);
		buscarListaModeloRequest.setParametrosBuscarListaModelo(parametrosBuscarListaModelo);
		BuscarListaModeloResponse buscarListaModeloResponse = null;
		ActionForward actionForw = new ActionForward();
		
		try{
			buscarListaModeloResponse = modeloPortTypeProxy.buscarListaModelo(buscarListaModeloRequest,this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'pesquisarModelo' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, ConsultaModeloAction.class.getName(), ex.getMessage(), formPesquisa, response );	
		}
		
		
		ResultadoBuscarListaModelo resultadoBuscarListaModelo =   buscarListaModeloResponse.getResultadoBuscarListaModelo();
		


		tratarPaginacao(resultadoBuscarListaModelo.getPaginacaoOut(), ApplicationConfiguration.getElementosPorPagina(),request);
		ResultadoBuscarListaModeloListaModelosRetornoModelo[] resultadoBuscarListaModeloListaModelosRetornoModelo = resultadoBuscarListaModelo.getListaModelos();
		
		
		List modeloList = Arrays.asList(resultadoBuscarListaModeloListaModelosRetornoModelo);

		form.setModelos(modeloList);
		
		request.setAttribute("modelos", modeloList);

		if (actionForw == null) {
			return actionForw;			
		} else {
			return mapping.findForward("success");
		}
		

		//return mapping.findForward("success");
	}
	
	
	public ActionForward exportar(ActionMapping mapping, ActionForm formExportar, HttpServletRequest request,
			HttpServletResponse response) throws CatalogoPRSException, IOException, LoginException {
		
		
		ConsultaModeloForm form = (ConsultaModeloForm)formExportar;
		
		if(form.getIdGrupoProdutoSelecionados()==null || form.getIdGrupoProdutoSelecionados().length==0) {
			CatalogoPRSException ex = new CatalogoPRSException("Obrigat&oacute;rio selecionar dados para exporta&ccedil;&atilde;o.");
			this.CatalogoPRSExceptionHandler(ex, ConsultaModeloAction.class.getName(), response);
			return null;
		}
		
				
		StringBuffer sb = new StringBuffer();
		
		BuscarListaCaracteristicaRequest buscarListaCaracteristicaRequest = new BuscarListaCaracteristicaRequest();
		BuscarListaCaracteristicaResponse buscarListaCaracteristicaResponse = null;
		PaginacaoIn paginacaoIn = new PaginacaoIn();
		paginacaoIn.setItensPorPagina(1000000);
		paginacaoIn.setPaginaSolicitada(1);
		
		ParametrosBuscarListaCaracteristica parametrosBuscarListaCaracteristica = new ParametrosBuscarListaCaracteristica();
		ParametrosBuscarListaCaracteristicaRaizCaracteristicaIn parametrosBuscarListaCaracteristicaRaizCaracteristicaIn = new ParametrosBuscarListaCaracteristicaRaizCaracteristicaIn();
		
		parametrosBuscarListaCaracteristicaRaizCaracteristicaIn.setPaginacaoIn(paginacaoIn);
		parametrosBuscarListaCaracteristica.setRaizCaracteristicaIn(parametrosBuscarListaCaracteristicaRaizCaracteristicaIn);
		buscarListaCaracteristicaRequest.setParametrosBuscarListaCaracteristica(parametrosBuscarListaCaracteristica);
		
		
		CaracteristicaPortTypeProxy caracteristicaPortTypeProxy = new CaracteristicaPortTypeProxy();
		try{
			buscarListaCaracteristicaResponse = caracteristicaPortTypeProxy.buscarListaCaracteristica(buscarListaCaracteristicaRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'exportar' da classe: " + getClass().getName());
			this.AxisFaultExceptionHandler(ex, ConsultaModeloAction.class.getName(), ex.getMessage(), form, response );
			return null;
		}
		
		ListaCaracteristicaCaracteristica[] todasCaracteristicaList = buscarListaCaracteristicaResponse.getResultadoCaracteristica().getListaCaracteristica();
		
		if(todasCaracteristicaList!=null && todasCaracteristicaList.length>0){
			for(int i=0;i<todasCaracteristicaList.length;i++){
				sb.append(todasCaracteristicaList[i].getNmCaracteristica()).append(";");
			}
		}
		sb.append("\n");

		ExportarModeloRequest exportarModeloRequest = new ExportarModeloRequest();
		
		long[]parametrosExportarModelos = new long[form.getIdGrupoProdutoSelecionados().length];
		
		for(int i=0;i<form.getIdGrupoProdutoSelecionados().length;i++){
			parametrosExportarModelos[i]=Long.valueOf(form.getIdGrupoProdutoSelecionados()[i]);
		}
		exportarModeloRequest.setParametrosExportarModelo(parametrosExportarModelos);
		
	
		sb.append("Tipo de Produto;Fabricante;Modelo;Modelo dispon�vel para o sistema externo (sim ou n�o);Fim de Vida (flag) se consta a informa��o ou n�o selecionada;Quantas imagens associadas;Tecnologia/Tipo de Frequencia/Frequencia;Cod do produto associado ao modelo / gama;caracteristicas;\n");

		
		ModeloPortTypeProxy modeloPortTypeProxy = new ModeloPortTypeProxy();
		ExportarModeloResponse exportarModeloResponse = null;
		
		try{
			exportarModeloResponse = modeloPortTypeProxy.exportarModelo(exportarModeloRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'exportar' da classe: " + getClass().getName());
			this.AxisFaultExceptionHandler(ex, ConsultaModeloAction.class.getName(), ex.getMessage(), form, response );	
			return null;
		}
		
		ResultadoExportarModeloModelo[] resultadoExportarModeloModelo = exportarModeloResponse.getResultadoExportarModelo();
		
		for(int i=0;i<resultadoExportarModeloModelo.length;i++){
			sb.append(resultadoExportarModeloModelo[i].getTipoProduto());
			sb.append(";");
			sb.append(resultadoExportarModeloModelo[i].getFabricante());
			sb.append(";");
			sb.append(resultadoExportarModeloModelo[i].getNmGrupoProduto());
			sb.append(";");
			sb.append(resultadoExportarModeloModelo[i].getInDisponivel() != null && resultadoExportarModeloModelo[i].getInDisponivel().equalsIgnoreCase("S")?"Sim":"N�O");
			sb.append(";");
			sb.append(resultadoExportarModeloModelo[i].getInFimVida() != null && resultadoExportarModeloModelo[i].getInFimVida().equalsIgnoreCase("S")?"Sim":"N�O");
			sb.append(";");
			sb.append(resultadoExportarModeloModelo[i].getQtdImagensAssociadas());
			sb.append(";");
			
			if(resultadoExportarModeloModelo[i].getListaTecnologiaTpFrequenciaVL()!= null ){
				ResultadoExportarModeloModeloListaTecnologiaTpFrequenciaVLTecnologiaTpFrequenciaVL[] modelo = resultadoExportarModeloModelo[i].getListaTecnologiaTpFrequenciaVL();
				String tecnologia = "";
				String tipoFrequencia = "";
				int indexVlFq = 0;
				for(int j=0;j<modelo.length;j++){
					
					
					if(!modelo[j].getTecnologia().equals(tecnologia)){
						if(indexVlFq>0)
							sb.append(")");
						indexVlFq = 0;
						tecnologia = modelo[j].getTecnologia();
						sb.append(tecnologia);
						sb.append(" ");
						if(modelo[j].getTipoFrequencia()!=null && modelo[j].getTipoFrequencia().trim().length()>0){
							tipoFrequencia=modelo[j].getTipoFrequencia();
							sb.append(tipoFrequencia);
							if(modelo[j].getVlFrequencia()!=null && modelo[j].getVlFrequencia().trim().length()>0){
								indexVlFq++;
								sb.append("(");
								sb.append(modelo[j].getVlFrequencia());
							}
						}
						
					}else{
						if(modelo[j].getTipoFrequencia()!=null && !modelo[j].getTipoFrequencia().equalsIgnoreCase(tipoFrequencia)){
							if(indexVlFq>0){
								sb.append(");");
							}
							indexVlFq =0;
							sb.append(" - ");
							tipoFrequencia = modelo[j].getTipoFrequencia();
							sb.append(tecnologia);
							sb.append(" ");
							sb.append(tipoFrequencia);
							if(modelo[j].getVlFrequencia() != null && modelo[j].getVlFrequencia().trim().length()>0){
								indexVlFq++;
								sb.append("(");
								sb.append(modelo[j].getVlFrequencia());
							}
						}else{
							if(modelo[j].getTipoFrequencia() != null && !modelo[j].getTipoFrequencia().equalsIgnoreCase(tipoFrequencia)){
								if(indexVlFq>0){
									sb.append(");");
								}
								indexVlFq =0;
								sb.append(" - ");
								tipoFrequencia = modelo[j].getTipoFrequencia();
								sb.append(tecnologia);
								sb.append(" ");
								sb.append(tipoFrequencia);
								if(modelo[j].getVlFrequencia() != null && modelo[j].getVlFrequencia().trim().length()>0){
									indexVlFq++;
									sb.append("(");
									sb.append(modelo[j].getVlFrequencia());
									
								}
							}else{
								if(modelo[j].getVlFrequencia() != null && modelo[j].getVlFrequencia().trim().length()>0){
									indexVlFq++;
									sb.append(", ");
									sb.append(modelo[j].getVlFrequencia());
								}
							}
						}
					}
					
				}
				if(indexVlFq>0)
					sb.append(")");
			}
			sb.append(";");
			
			//Lista de Produtos
			
			if(resultadoExportarModeloModelo[i].getListaProdutos() != null && resultadoExportarModeloModelo[i].getListaProdutos().length>0){
				ResultadoExportarModeloModeloListaProdutosProduto[] produtos = resultadoExportarModeloModelo[i].getListaProdutos();
				int index = 0;
				for (int k=0;k<produtos.length;k++) {
					if(index > 0){
						sb.append(" - ");
					}
					index++;
					sb.append(produtos[k].getCdCodigo());
					if(produtos[k].getNmGama() != null && produtos[k].getNmGama().trim().length()>0){
						sb.append("(");
						sb.append(produtos[k].getNmGama());
						sb.append(")");
					}
				}
			}
			sb.append(";");
			
			if(resultadoExportarModeloModelo[i].getListaCaracteristicas()!=null && resultadoExportarModeloModelo[i].getListaCaracteristicas().length>0){
				ResultadoExportarModeloModeloListaCaracteristicasCaracteristica[] caracteristica = resultadoExportarModeloModelo[i].getListaCaracteristicas();
				String caracteristicaAtual = "";
				int indexValor =0 ;
				
				for (int l=0;l<caracteristica.length;l++) {
					if(!caracteristica[l].getCaracteristica().equals(caracteristicaAtual)){
						if(indexValor>0)
							sb.append(")");
						if(!caracteristicaAtual.equalsIgnoreCase(""))
							sb.append(";");
						if(caracteristica[l].getInDisponivel() != null && caracteristica[l].getInDisponivel().equalsIgnoreCase("S")){
							if(caracteristica[l].getInSimulador() != null && caracteristica[l].getInSimulador().equalsIgnoreCase("S")){
								sb.append("!");
							}else{
								sb.append("*");
							}
						}
						caracteristicaAtual = caracteristica[l].getCaracteristica();
						sb.append(caracteristicaAtual);
						if(caracteristica[l].getValor() != null && caracteristica[l].getValor().trim().length()>0){
							sb.append("(");
							sb.append(caracteristica[l].getValor());
							indexValor++;
						}
					}else{
						if(indexValor>0){
							sb.append(", ");
						}
						sb.append(caracteristica[l].getValor());
						indexValor++;
					}
				}
				if(indexValor > 0)
					sb.append(")");
				
			}
			sb.append("\n");
			
		}
		
		response.setContentType("application/vnd.ms-excel;charset=iso-8859-1");
		response.addHeader("Content-Disposition", "attachment;filename=Modelos.csv");
		response.setCharacterEncoding("ISO-8859-1");
		PrintWriter out = response.getWriter();

		out.write(sb.toString());
		out.flush();

		return null;
	}
	
	public ActionForward popupDetalheModelo(ActionMapping mapping, ActionForm formulario, HttpServletRequest request,HttpServletResponse response) throws CatalogoPRSException, ErroInfo, RemoteException {
		
		ConsultaModeloForm form = (ConsultaModeloForm)formulario;

		ActionForward actionForw = new ActionForward();
		String idModeloString = request.getParameter("id_modelo");
		
		String caminho_link_imagens_modelo = ApplicationConfiguration.getCaminhoLinkImagensModelo();
		form.setCaminho_link_imagens_modelo(caminho_link_imagens_modelo);

		if (idModeloString != null && !idModeloString.trim().equals("")) {
			
			//Buscar no Novo servi�o as Multimidias
			BuscarListaMultTMCorClassRequest multTMCorClassRequestDocument = new BuscarListaMultTMCorClassRequest();
									
			ParamBuscarListaMultTMCorClass paramBuscarListaMultTMCorClass = new ParamBuscarListaMultTMCorClass();
			paramBuscarListaMultTMCorClass.setIdGrupoProduto(Long.valueOf(idModeloString));
			
			paramBuscarListaMultTMCorClass.setListaSgTipoMultimidia(new String[]{"IMG"});
			multTMCorClassRequestDocument.setParamBuscarListaMultTMCorClass(paramBuscarListaMultTMCorClass);
			
			ParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia parametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia = new ParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia();
			BuscaCaracteristicaProdutoModelo buscaCaracteristicaProdutoModelo = new BuscaCaracteristicaProdutoModelo();
			buscaCaracteristicaProdutoModelo.setIdModelo(Long.valueOf(idModeloString));
			
			parametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia.setBuscaCaracteristicaProdutoModelo(buscaCaracteristicaProdutoModelo);
			
			BuscarModeloListaTecnologiaTipoFrequenciaValorFrequenciaRequest paramBuscarModeloTecnologiaTpFrequenciaVlFrequencia = new BuscarModeloListaTecnologiaTipoFrequenciaValorFrequenciaRequest();
			paramBuscarModeloTecnologiaTpFrequenciaVlFrequencia.setParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia(parametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia);
			
			ModeloPortTypeProxy modeloPortTypeProxy1 = new ModeloPortTypeProxy();
			BuscarModeloListaTecnologiaTipoFrequenciaValorFrequenciaResponse buscarModeloListaTecnologiaTipoFrequenciaValorFrequenciaResponseDocument = new BuscarModeloListaTecnologiaTipoFrequenciaValorFrequenciaResponse();
			
			
			
			try {
				buscarModeloListaTecnologiaTipoFrequenciaValorFrequenciaResponseDocument = modeloPortTypeProxy1.buscarModeloListaTecnologiaTipoFrequenciaValorFrequencia(paramBuscarModeloTecnologiaTpFrequenciaVlFrequencia, this.getUserName(), this.getPassword());
			} catch (AxisFault ex) {
				logger.debug("Exception no method: 'popupDetalheModelo' da classe: " + getClass().getName());
				actionForw = this.AxisFaultExceptionHandler(ex, BaseMappingDispatchAction.class.getName(), ex.getMessage(), null, response );
			}
			
			Modelo[] arrModelo = buscarModeloListaTecnologiaTipoFrequenciaValorFrequenciaResponseDocument.getResultadoBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia();
			
			if (arrModelo == null || arrModelo.length == 0) {		
				CatalogoPRSException ex = new CatalogoPRSException("Modelo n&atilde;o encontrado.");
				this.CatalogoPRSExceptionHandler(ex, BaseMappingDispatchAction.class.getName(), response);
				return null;
				
			}
			
			Modelo modelo = arrModelo[0];
			
			// listaTecnologia
			ListaTecnologiaTecnologia[] listaTecnologia = null;
			// listaTipoFrequencia
			ListaTipoFrequenciaTipoFrequencia[] listaTipoFrequencia = null;
			// listaValorFrequencia
			ListaValorFrequenciaValorFrequencia[] listaValorFrequencia = null;
			
			
			if (modelo.getListaTecnologia() != null) {
				listaTecnologia = modelo.getListaTecnologia();	
				for (int i = 0; i < listaTecnologia.length; i++) {
					if (listaTecnologia[i].getListaTipoFrequencia() != null) {
						listaTipoFrequencia = listaTecnologia[i].getListaTipoFrequencia();					
					}					
				}
			}
			
			if (listaTipoFrequencia != null) {
				form.setListaTipoFrequencia(listaTipoFrequencia);
				if (listaTipoFrequencia != null) {
					for (int i = 0; i < listaTipoFrequencia.length; i++) {
						listaValorFrequencia = listaTipoFrequencia[i].getListaValorFrequencia();						
					}					
				}				
			}
			
			if (listaValorFrequencia != null) {
				form.setListaValorFrequencia(listaValorFrequencia);
				
			}
			
			form.setListaTecnologia(listaTecnologia);
			request.setAttribute("modelo", modelo);
			
			
			
			BuscarDetalhesModeloRequest detalhesModeloRequest = new BuscarDetalhesModeloRequest();
			BuscaCaracteristicaProdutoModelo buscaCaracteristicaProdutoModelo2 = new BuscaCaracteristicaProdutoModelo();
			buscaCaracteristicaProdutoModelo2.setIdModelo(Long.valueOf(idModeloString));
			
			ParametrosBuscarDetalhesModelo parametrosBuscarDetalhesModelo = new ParametrosBuscarDetalhesModelo();
			parametrosBuscarDetalhesModelo.setBuscaCaracteristicaProdutoModelo(buscaCaracteristicaProdutoModelo2);
			detalhesModeloRequest.setParametrosBuscarDetalhesModelo(parametrosBuscarDetalhesModelo);
			
			
			ModeloPortTypeProxy modeloPortTypeProxy = new ModeloPortTypeProxy();
			BuscarDetalhesModeloResponse buscarDetalhesModeloResponseDocument = new BuscarDetalhesModeloResponse();
			
			try {
				buscarDetalhesModeloResponseDocument = modeloPortTypeProxy.buscarDetalhesModelo(detalhesModeloRequest, this.getUserName(), this.getPassword());
			} catch (AxisFault ex) {
				logger.debug("Exception no method: 'popupDetalheModelo' da classe: " + getClass().getName());
				actionForw = this.AxisFaultExceptionHandler(ex, BaseMappingDispatchAction.class.getName(), ex.getMessage(), null, response );				
			}
			
			ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOut buscaCaracteristicaCodigoProdutoPorModeloOut = new ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOut();
			
			
			if (buscarDetalhesModeloResponseDocument.getResultadoBuscarDetalhesModelo() != null) {
				
				buscaCaracteristicaCodigoProdutoPorModeloOut = buscarDetalhesModeloResponseDocument.getResultadoBuscarDetalhesModelo().getBuscaCaracteristicaCodigoProdutoPorModeloOut();
													
				// caracteristicaList
				Caracteristica[] caracteristicaList = buscaCaracteristicaCodigoProdutoPorModeloOut.getListaCaracteristica();
				
				//listaValorCaracteristica
				ValorCaracteristica[] listaValorCaracteristica = null;
				if (caracteristicaList != null) {
					form.setCaracteristicaList(caracteristicaList);
					for (int i = 0; i < caracteristicaList.length; i++) {
						listaValorCaracteristica = caracteristicaList[i].getListaValorCaracteristica();										
					}								
				}
				
				if (listaValorCaracteristica != null) {
					form.setListaValorCaracteristica(listaValorCaracteristica);
				}
				
				ListaSistemaProdutoSistemaProduto[] sistemaProdutoList = buscaCaracteristicaCodigoProdutoPorModeloOut.getListaSistemaProduto();
				
									
				// listaSistemaProduto
				if (sistemaProdutoList != null) {
					form.setSistemaProdutoList(sistemaProdutoList);				
				}			
				form.setNmFabricante(buscaCaracteristicaCodigoProdutoPorModeloOut.getModelo().getNmModelo());

			}			
			
			MultimidiaPortTypeProxy multimidiaPortTypeProxy = new MultimidiaPortTypeProxy();
			BuscarListaMultTMCorClassResponse multTMCorClassResponseDocument = new BuscarListaMultTMCorClassResponse();			
			
			try {
				multTMCorClassResponseDocument = multimidiaPortTypeProxy.buscarListaMultTMCorClass(multTMCorClassRequestDocument, this.getUserName(), this.getPassword());
			} catch (AxisFault ex) {
				logger.debug("Exception no method: 'popupDetalheModelo' da classe: " + getClass().getName());
				actionForw = this.AxisFaultExceptionHandler(ex, BaseMappingDispatchAction.class.getName(), ex.getMessage(), null, response );	
			}
			
			// multimidiaList
			ResultBuscarListaMultTMCorClassMultimidia[] multimidiaList = null;
			if (multTMCorClassResponseDocument.getResultBuscarListaMultTMCorClass() != null) {
				multimidiaList = multTMCorClassResponseDocument.getResultBuscarListaMultTMCorClass();
				
			}
			
			if (multimidiaList != null) {
				form.setMultimidiaList(multimidiaList);
			}
			
		}

		if (actionForw == null) {
			return actionForw;			
		} else {
			return mapping.findForward("popupDetalhesModelo");
		}
	}
	
}
