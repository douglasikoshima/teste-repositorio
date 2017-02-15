package br.com.vivo.catalogoPRS.pageflows.consulta.produto.consultaRestricaoModelo;


import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.AxisFault;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.ConsultaRestricaoModeloForm;
import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.mc.geral.ErroInfo;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscaCaracteristicaProdutoModelo;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarDetalhesModeloRequest;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarDetalhesModeloResponse;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarListaRestricoesModeloRequest;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarListaRestricoesModeloResponse;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarModeloListaTecnologiaTipoFrequenciaValorFrequenciaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarModeloListaTecnologiaTipoFrequenciaValorFrequenciaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.Caracteristica;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ExportarRestricoesModeloRequest;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ExportarRestricoesModeloResponse;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaSistemaProdutoSistemaProduto;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaTecnologiaTecnologia;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaTipoFrequenciaTipoFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaValorFrequenciaValorFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.Modelo;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ModeloPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.PaginacaoIn;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.PaginacaoOut;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarDetalhesModelo;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaRestricoesModelo;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarModeloListaTecnologiaTipoFrequenciaValorFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosExportarRestricoesModelo;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosExportarRestricoesModeloPaginacaoIn;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOut;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarListaRestricoesModelo;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModelo;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarRestricoesModelo;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarRestricoesModeloRestricaoModelo;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.TipoProduto;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ValorCaracteristica;
import br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.BuscarListaImagemPorModeloRequest;
import br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.BuscarListaImagemPorModeloResponse;
import br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.BuscarListaMultTMCorClassRequest;
import br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.BuscarListaMultTMCorClassResponse;
import br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.MultimidiaPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ParamBuscarListaMultTMCorClass;
import br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ParametrosBuscarListaImagemPorModelo;
import br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ParametrosBuscarListaImagemPorModeloParametrosImagemPorModeloIn;
import br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ResultBuscarListaMultTMCorClassMultimidia;
import br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ResultadoBuscarListaImagemPorModeloMultimidia;
import br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.BuscarListaTipoProdutoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.BuscarListaTipoProdutoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.ResultadoListarTipoProdutoTipoProduto;
import br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.TipoProdutoPortTypeProxy;


public class ConsultaRestricaoModeloAction extends BaseMappingDispatchAction {

	private static Logger logger = Logger.getLogger(ConsultaRestricaoModeloAction.class);
	
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException, ErroInfo, RemoteException  {
		
		TipoProdutoPortTypeProxy tipoProdutoPortTypeProxy = new TipoProdutoPortTypeProxy();
		BuscarListaTipoProdutoRequest buscarListaTipoProdutoRequest = new BuscarListaTipoProdutoRequest();
		BuscarListaTipoProdutoResponse buscarListaTipoProdutoResponse = new BuscarListaTipoProdutoResponse();
		
		List<TipoProduto> tipoProdutoList = new ArrayList<TipoProduto>();
		
		buscarListaTipoProdutoResponse = tipoProdutoPortTypeProxy.buscarListaTipoProduto(buscarListaTipoProdutoRequest, this.getUserName(), this.getPassword());
		ResultadoListarTipoProdutoTipoProduto[] resultado = buscarListaTipoProdutoResponse.getResultadoListarTipoProduto();
		
		if (resultado != null && resultado.length > 0 ) {
			for (int i = 0; i < resultado.length; i++) {
				TipoProduto tipoProduto = new TipoProduto();
				tipoProduto.setIdTipoProduto(resultado[i].getIdTipoProduto());
				tipoProduto.setNmTipoProduto(resultado[i].getNmTipoProduto());
				tipoProduto.setListaSgTipoProduto(resultado[i].getListaSgTipoProduto());
				tipoProduto.setNmUsuarioCriacao(resultado[i].getNmUsuarioCriacao());
				tipoProduto.setNmUsuarioAlteracao(resultado[i].getNmUsuarioAlteracao());
				tipoProduto.setDtCriacao(resultado[i].getDtCriacao());
				tipoProduto.setDtUltimaAlteracao(resultado[i].getDtUltimaAlteracao());
				
				tipoProdutoList.add(tipoProduto);				
			}					
		}

		request.setAttribute("tipos_produto", tipoProdutoList);
		return mapping.findForward("default");
	}

	public ActionForward abrirpopupCaracteristicaModelo(ActionMapping mapping, ActionForm formulario, HttpServletRequest request,
			HttpServletResponse response) throws br.com.vivo.catalogoPRS.ws.catalogoModelo.mc.geral.ErroInfo, RemoteException {
		
		ConsultaRestricaoModeloForm form = (ConsultaRestricaoModeloForm)formulario;
		
		String idModeloString = request.getParameter("id_modelo");

		if (idModeloString != null && !idModeloString.trim().equals("")) {	

			MultimidiaPortTypeProxy multimidiaPortTypeProxy = new MultimidiaPortTypeProxy();
			BuscarListaImagemPorModeloRequest buscarListaImagemPorModeloRequest = new BuscarListaImagemPorModeloRequest();
			BuscarListaImagemPorModeloResponse buscarListaImagemPorModeloResponse = new BuscarListaImagemPorModeloResponse();
			
			
			ParametrosBuscarListaImagemPorModeloParametrosImagemPorModeloIn parametrosImagemPorModeloIn = new ParametrosBuscarListaImagemPorModeloParametrosImagemPorModeloIn();
			parametrosImagemPorModeloIn.setIdGrupoProduto(Long.valueOf(idModeloString));
	
			ParametrosBuscarListaImagemPorModelo parametrosBuscarListaImagemPorModelo = new ParametrosBuscarListaImagemPorModelo();
			parametrosBuscarListaImagemPorModelo.setParametrosImagemPorModeloIn(parametrosImagemPorModeloIn);
			
			
			buscarListaImagemPorModeloRequest.setParametrosBuscarListaImagemPorModelo(parametrosBuscarListaImagemPorModelo);
			buscarListaImagemPorModeloResponse = multimidiaPortTypeProxy.buscarListaImagemPorModelo(buscarListaImagemPorModeloRequest, this.getUserName(), this.getPassword());
			
					
			if(buscarListaImagemPorModeloResponse.getResultadoBuscarListaImagemPorModelo() != null){				
				ResultadoBuscarListaImagemPorModeloMultimidia[] resultadoListImgPorModeloMultimidia = buscarListaImagemPorModeloResponse.getResultadoBuscarListaImagemPorModelo();
				form.setResultadoListImgPorModeloMultimidia(resultadoListImgPorModeloMultimidia);
			}

			
			ModeloPortTypeProxy modeloPortTypeProxy = new ModeloPortTypeProxy();
			BuscarDetalhesModeloRequest buscarDetalhesModeloRequest = new BuscarDetalhesModeloRequest ();
			BuscarDetalhesModeloResponse buscarDetalhesModeloResponse = new BuscarDetalhesModeloResponse();
			
			
			ParametrosBuscarDetalhesModelo parametrosBuscarDetalhesModelo = new ParametrosBuscarDetalhesModelo();
			BuscaCaracteristicaProdutoModelo buscaCaracteristicaProdutoModelo = new BuscaCaracteristicaProdutoModelo();		
			buscaCaracteristicaProdutoModelo.setIdModelo(Long.valueOf(idModeloString));
			parametrosBuscarDetalhesModelo.setBuscaCaracteristicaProdutoModelo(buscaCaracteristicaProdutoModelo);
			
			
			buscarDetalhesModeloRequest.setParametrosBuscarDetalhesModelo(parametrosBuscarDetalhesModelo);		
			buscarDetalhesModeloResponse = modeloPortTypeProxy.buscarDetalhesModelo(buscarDetalhesModeloRequest, this.getUserName(), this.getPassword());
			
			
			ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOut resultado = null;
			if (buscarDetalhesModeloResponse.getResultadoBuscarDetalhesModelo() != null) {
				resultado = buscarDetalhesModeloResponse.getResultadoBuscarDetalhesModelo().getBuscaCaracteristicaCodigoProdutoPorModeloOut();
			}
			
			Caracteristica[] caracteristicaList = null;
			ListaSistemaProdutoSistemaProduto[] sistemaProdutoList = null;
			if (resultado != null) {
				caracteristicaList = resultado.getListaCaracteristica();
				if (caracteristicaList != null) {
					form.setCaracteristicaList(caracteristicaList);					
				}
				sistemaProdutoList = resultado.getListaSistemaProduto();	
				if (sistemaProdutoList != null) {
					form.setSistemaProdutoList(sistemaProdutoList);					
				}
			}				
			form.setCaracteristicaList(caracteristicaList);
		}
		return mapping.findForward("success");
	}

	public ActionForward pesquisarModelo(ActionMapping mapping, ActionForm formulario, HttpServletRequest request, HttpServletResponse response) throws ErroInfo, RemoteException {				
		
		ConsultaRestricaoModeloForm form = (ConsultaRestricaoModeloForm)formulario;
		ActionForward actionForw = new ActionForward();
		
		ModeloPortTypeProxy modeloPortTypeProxy = new ModeloPortTypeProxy();
		
		BuscarListaRestricoesModeloRequest buscarListaRestricoesModeloRequest = new BuscarListaRestricoesModeloRequest();
		BuscarListaRestricoesModeloResponse buscarListaRestricoesModeloResponse = new BuscarListaRestricoesModeloResponse();
		ParametrosBuscarListaRestricoesModelo parametrosBuscarListaRestricoesModelo = new ParametrosBuscarListaRestricoesModelo();
	
		PaginacaoIn paginacaoIn = new PaginacaoIn(); 
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		
		
		if(form.getPaginaSolicitada() != null && form.getPaginaSolicitada().intValue() > 0)
			paginacaoIn.setPaginaSolicitada(form.getPaginaSolicitada());
		else
			paginacaoIn.setPaginaSolicitada(1);
		if(form.getModelo() != null && form.getModelo().trim().length() > 0){
			parametrosBuscarListaRestricoesModelo.setNmGrupoProduto(form.getModelo());
		}
		
		parametrosBuscarListaRestricoesModelo.setIdTipoProduto(form.getTipoProduto());
		parametrosBuscarListaRestricoesModelo.setPaginacaoIn(paginacaoIn);
		
		buscarListaRestricoesModeloRequest.setParametrosBuscarListaRestricoesModelo(parametrosBuscarListaRestricoesModelo);
		
		try {
			buscarListaRestricoesModeloResponse = modeloPortTypeProxy.buscarListaRestricoesModelo(buscarListaRestricoesModeloRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'pesquisarModelo' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, ConsultaRestricaoModeloAction.class.getName(), ex.getMessage(), formulario, response );
		}
		
		ResultadoBuscarListaRestricoesModelo resultadoBuscarListaRestricoesModelo = new ResultadoBuscarListaRestricoesModelo();
		PaginacaoOut paginacaoOut = new PaginacaoOut(); 
				
				
		
		if (buscarListaRestricoesModeloResponse.getResultadoBuscarListaRestricoesModelo() != null) {
			resultadoBuscarListaRestricoesModelo = buscarListaRestricoesModeloResponse.getResultadoBuscarListaRestricoesModelo();	
			
		}
		
		if (resultadoBuscarListaRestricoesModelo.getListaRestricoesModeloOut() != null) {
			paginacaoOut = resultadoBuscarListaRestricoesModelo.getListaRestricoesModeloOut().getPaginacaoOut();
		}

		this.tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(), request);
		
		ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModelo[] resultadoModeloList = null;
		if (buscarListaRestricoesModeloResponse.getResultadoBuscarListaRestricoesModelo() != null) {
			resultadoModeloList = buscarListaRestricoesModeloResponse.getResultadoBuscarListaRestricoesModelo().getListaRestricoesModeloOut().getListaModelo();			
		}				
		
		form.setResultadoModeloList(resultadoModeloList);
		
		if (actionForw == null) {
			return actionForw;
			
		} else {
			return mapping.findForward("success");
		}
	}

	public ActionForward exportar(ActionMapping mapping, ActionForm formulario, HttpServletRequest request, HttpServletResponse response) throws java.io.IOException, CatalogoPRSException {
		
		ConsultaRestricaoModeloForm form = (ConsultaRestricaoModeloForm)formulario;
		
		
		if(form.getIdsModelos() == null || form.getIdsModelos().length ==0) {
			CatalogoPRSException ex = new CatalogoPRSException("Obrigat&oacute;rio selecionar dados para exporta&ccedil;&atilde;o.");
			this.CatalogoPRSExceptionHandler(ex, ConsultaRestricaoModeloAction.class.getName(), response);
			return null;
		}

		response.addHeader("CONNECTION", "Keep-Alive");
		response.addHeader("KEEP-ALIVE", "timeout=600, max=100");
		
		StringBuffer sb = new StringBuffer();
		
		ExportarRestricoesModeloRequest exportarRestricoesModeloRequestDocument = new ExportarRestricoesModeloRequest();
		ParametrosExportarRestricoesModelo parametrosExportarRestricoesModelo = new ParametrosExportarRestricoesModelo();
		
		long[] idModelo = new long[form.getIdsModelos().length];
		for (int i = 0; i < form.getIdsModelos().length; i++) {
			idModelo[i] = form.getIdsModelos()[i].longValue();
			parametrosExportarRestricoesModelo.setIdModelo(idModelo);
		}
			
		ParametrosExportarRestricoesModeloPaginacaoIn paginacaoIn = new ParametrosExportarRestricoesModeloPaginacaoIn();
		parametrosExportarRestricoesModelo.setPaginacaoIn(paginacaoIn);
		int linhasLidas = 0;
		paginacaoIn.setItensPorPagina(1000);
		int currentPage = 1;
		paginacaoIn.setPaginaSolicitada(currentPage++);
		
		ModeloPortTypeProxy modeloPortTypeProxy = new ModeloPortTypeProxy();
	
		
		ExportarRestricoesModeloResponse exportarRestricoesModeloResponseDocument = new ExportarRestricoesModeloResponse();
		exportarRestricoesModeloRequestDocument.setParametrosExportarRestricoesModelo(parametrosExportarRestricoesModelo);		
		exportarRestricoesModeloResponseDocument = modeloPortTypeProxy.exportarRestricoesModelo(exportarRestricoesModeloRequestDocument, this.getUserName(), this.getPassword());
				
		ResultadoExportarRestricoesModelo resultadoExportarRestricoesModelo = new ResultadoExportarRestricoesModelo();
		resultadoExportarRestricoesModelo = exportarRestricoesModeloResponseDocument.getResultadoExportarRestricoesModelo();
		
		response.setContentType("application/vnd.ms-excel;charset=iso-8859-1");
		response.addHeader("Content-Disposition", "attachment;filename=RestricoesModelo.csv");
		response.setCharacterEncoding("ISO-8859-1");
		PrintWriter out = response.getWriter();
		out.write("Modelo;UF;Tipo de Cliente;Segmento;Carteira;Canal\n");
		boolean processed = false;
		while(!processed){
			sb = new StringBuffer();
			ResultadoExportarRestricoesModeloRestricaoModelo[] list = resultadoExportarRestricoesModelo.getRestricaoModelo();
			linhasLidas += list.length;
			for (int i = 0; i < list.length; i++) {
				sb.append(list[i].getNmGrupoProduto());
				sb.append(";");
				sb.append(list[i].getNmUf());
				sb.append(";");
				sb.append(list[i].getNmTipoCliente());
				sb.append(";");
				sb.append(list[i].getSgSegmento());
				sb.append(";");
				sb.append(list[i].getSgCarteira());
				sb.append(";");
				sb.append(list[i].getNmCanal());
				sb.append(";");
				sb.append("\n");
			}
			out.write(sb.toString());
			if( linhasLidas >= resultadoExportarRestricoesModelo.getPaginacaoOut().getTotalRegistros()){
				processed = true;
			}else{
				ExportarRestricoesModeloResponse exportarRestricoesModeloResponse =  new ExportarRestricoesModeloResponse();
				exportarRestricoesModeloRequestDocument.getParametrosExportarRestricoesModelo().getPaginacaoIn().setPaginaSolicitada(currentPage++);				
				exportarRestricoesModeloResponse = modeloPortTypeProxy.exportarRestricoesModelo(exportarRestricoesModeloRequestDocument, this.getUserName(), this.getPassword());							
			}
		}

		out.flush();
		return null;
	}
	
	public ActionForward popupDetalheModelo(ActionMapping mapping, ActionForm formulario, HttpServletRequest request,HttpServletResponse response) throws CatalogoPRSException, ErroInfo, RemoteException {
		
		ConsultaRestricaoModeloForm	form = (ConsultaRestricaoModeloForm)formulario;

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