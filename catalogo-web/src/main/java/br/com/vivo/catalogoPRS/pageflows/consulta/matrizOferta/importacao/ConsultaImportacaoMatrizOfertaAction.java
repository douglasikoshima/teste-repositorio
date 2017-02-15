package br.com.vivo.catalogoPRS.pageflows.consulta.matrizOferta.importacao;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.AxisFault;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.catalogoPRS.delegate.MatrizOfertaDelegate;
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.ConsultaImportacaoMatrizOfertaForm;
import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;
import br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn;
import br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaDetalhesImportacaoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaDetalhesImportacaoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaResultadoImportacaoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaResultadoImportacaoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaStatusImportacaoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaStatusImportacaoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExportarMatrizOfertaArquivoItemRequest;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ExportarMatrizOfertaArquivoItemResponse;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.MatrizOfertaPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParamProcessarMatrizOfertaArquivo;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosBuscarListaDetalhesImportacao;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosBuscarListaResultadoImportacao;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosExportarMatrizOfertaArquivoItem;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ProcessarMatrizOfertaArquivoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaDetalhesImportacao;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaDetalhesImportacaoListaDetalhesImportacaoDetalhesImportacao;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaResultadoImportacao;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaStatusImportacaoListaStatusImportacao;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoExportarMatrizOfertaArquivoItem;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoExportarMatrizOfertaArquivoItemExportarMatrizOfertaArquivoItemMatrizOfertaArquivoItem;
import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * @author calixto.macedo
 *
 */
public class ConsultaImportacaoMatrizOfertaAction extends BaseMappingDispatchAction implements Serializable {
	private static final long serialVersionUID = 1L;
	private String SUCCESS = "success";

	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ConsultaImportacaoMatrizOfertaForm consultaImportacaoMatrizOfertaForm = (ConsultaImportacaoMatrizOfertaForm)form;
		BuscarListaStatusImportacaoRequest buscarListaStatusImportacaoRequest =  new BuscarListaStatusImportacaoRequest();
		MatrizOfertaPortTypeProxy matrizOfertaPortTypeProxy = new MatrizOfertaPortTypeProxy();
		BuscarListaStatusImportacaoResponse buscarListaStatusImportacaoResponse = new BuscarListaStatusImportacaoResponse();
		
		try {
			buscarListaStatusImportacaoResponse = matrizOfertaPortTypeProxy.buscarListaStatusImportacao(buscarListaStatusImportacaoRequest,this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, ConsultaImportacaoMatrizOfertaAction.class.getName(), ex.getMessage(), consultaImportacaoMatrizOfertaForm, response );
			return null;
		}
		
		List<ResultadoBuscarListaStatusImportacaoListaStatusImportacao> statusImportacaoList = Arrays.asList(buscarListaStatusImportacaoResponse.getResultadoBuscarListaStatusImportacao());
		
		request.setAttribute("listaStatusImportacao", statusImportacaoList);
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward pesquisarImportacaoMatrizOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception, CatalogoPRSException {
	
	    ConsultaImportacaoMatrizOfertaForm consultaImportacaoMatrizOfertaForm = (ConsultaImportacaoMatrizOfertaForm)form;
		Calendar dateInicial = Calendar.getInstance();
		Calendar dateFinal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if(consultaImportacaoMatrizOfertaForm.getPeriodoInicio() != null && !consultaImportacaoMatrizOfertaForm.getPeriodoInicio().equals("")){
			try {
				dateInicial.setTime(df.parse(consultaImportacaoMatrizOfertaForm.getPeriodoInicio()));
			} catch (ParseException e) {
				throw new CatalogoPRSException(e.getMessage());
			}
		}else{
			dateInicial = null;
		}
		if(consultaImportacaoMatrizOfertaForm.getPeriodoFim() != null && !consultaImportacaoMatrizOfertaForm.getPeriodoFim().equals("")){
			try {
				dateFinal.setTime(df.parse(consultaImportacaoMatrizOfertaForm.getPeriodoFim()));
			} catch (ParseException e) {
				throw new CatalogoPRSException(e.getMessage());
			}
		}else{
			dateFinal = null;
		}
		if(dateInicial != null && dateFinal != null){
			if(!(dateInicial.compareTo(dateFinal)  <= 0)){
				throw new CatalogoPRSException("O período inicial deve ser menor ou igual ao período final.");
			}
		}
		
		BuscarListaResultadoImportacaoRequest buscarListaResultadoImportacaoRequest = new BuscarListaResultadoImportacaoRequest();
		ParametrosBuscarListaResultadoImportacao parametrosBuscarListaResultadoImportacao = new ParametrosBuscarListaResultadoImportacao();
		BuscarListaResultadoImportacaoResponse buscarListaResultadoImportacaoResponse = new BuscarListaResultadoImportacaoResponse();
		BuscarListaStatusImportacaoRequest document =  new BuscarListaStatusImportacaoRequest();
		BuscarListaStatusImportacaoResponse importacaoResponseDocument = new BuscarListaStatusImportacaoResponse();
		PaginacaoIn paginacaoIn = new PaginacaoIn();
		MatrizOfertaPortTypeProxy matrizOfertaPortTypeProxy = new MatrizOfertaPortTypeProxy();
		
		if(consultaImportacaoMatrizOfertaForm.getIdStatusArquivoImportacao() != null && consultaImportacaoMatrizOfertaForm.getIdStatusArquivoImportacao() > 0) {
			parametrosBuscarListaResultadoImportacao.setIdStatusArquivoImportacao(consultaImportacaoMatrizOfertaForm.getIdStatusArquivoImportacao());	
		}
		if(consultaImportacaoMatrizOfertaForm.getNmArquivo() != null && consultaImportacaoMatrizOfertaForm.getNmArquivo().length() > 0) {
			parametrosBuscarListaResultadoImportacao.setNmArquivo(consultaImportacaoMatrizOfertaForm.getNmArquivo());
		}
		if(consultaImportacaoMatrizOfertaForm.getUserImportacao() != null && !consultaImportacaoMatrizOfertaForm.getUserImportacao().equals("")) {
			parametrosBuscarListaResultadoImportacao.setUserImportacao(consultaImportacaoMatrizOfertaForm.getUserImportacao());		
		}
		if(consultaImportacaoMatrizOfertaForm.getPeriodoInicio() != null && !"".equals(consultaImportacaoMatrizOfertaForm.getPeriodoInicio())) {
			parametrosBuscarListaResultadoImportacao.setDtImportacaoInicial(dateInicial);
		}
		if(consultaImportacaoMatrizOfertaForm.getPeriodoFim() != null && !"".equals(consultaImportacaoMatrizOfertaForm.getPeriodoFim())) {
			parametrosBuscarListaResultadoImportacao.setDtImportacaoFinal(dateFinal);		
		}
		if(consultaImportacaoMatrizOfertaForm.getPaginaSolicitada() != null) {
			paginacaoIn.setPaginaSolicitada(consultaImportacaoMatrizOfertaForm.getPaginaSolicitada());
		}
		else {
			paginacaoIn.setPaginaSolicitada(1);
		}
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		
		parametrosBuscarListaResultadoImportacao.setPaginacaoIn(paginacaoIn);
		buscarListaResultadoImportacaoRequest.setParametrosBuscarListaResultadoImportacao(parametrosBuscarListaResultadoImportacao);
		
		
		try {
			buscarListaResultadoImportacaoResponse = matrizOfertaPortTypeProxy.buscarListaResultadoImportacao(buscarListaResultadoImportacaoRequest,this.getUserName(),this.getPassword());
			importacaoResponseDocument = matrizOfertaPortTypeProxy.buscarListaStatusImportacao(document,this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, ConsultaImportacaoMatrizOfertaAction.class.getName(), ex.getMessage(), consultaImportacaoMatrizOfertaForm, response );
			return null;
		}

		PaginacaoOut paginacaoOut = buscarListaResultadoImportacaoResponse.getResultadoBuscarListaResultadoImportacao().getPaginacaoOut();
		ResultadoBuscarListaResultadoImportacao listaResultadoImportacao = null;
		if(paginacaoOut != null) {
			tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(),request);
			listaResultadoImportacao = buscarListaResultadoImportacaoResponse.getResultadoBuscarListaResultadoImportacao();
		}
		if(listaResultadoImportacao != null) {
			consultaImportacaoMatrizOfertaForm.setResultadoImportacaoList(Arrays.asList(listaResultadoImportacao.getListaResultadoImportacao()));
		}
		
		List<ResultadoBuscarListaStatusImportacaoListaStatusImportacao> statusImportacao = Arrays.asList(importacaoResponseDocument.getResultadoBuscarListaStatusImportacao());
		Map<String, Long> idStatusByDescStatus = new HashMap<String, Long>();
		for (ResultadoBuscarListaStatusImportacaoListaStatusImportacao importacao : statusImportacao) {
			idStatusByDescStatus.put(importacao.getDscStatusImportacao(), importacao.getIdStatusImportacao());
		}

		request.setAttribute("descStatusMap", idStatusByDescStatus);
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	
	public ActionForward openPopupDetalhesArquivoItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	    ConsultaImportacaoMatrizOfertaForm consultaImportacaoMatrizOfertaForm = (ConsultaImportacaoMatrizOfertaForm)form;
	    
		String paginaSolicitada = request.getParameter("pagina_solicitada");
		String idMatrizOfertaArquivoIcon = request.getParameter("idMatrizOfertaArquivoIcon");
		String idMatrizOfertaArquivoPag = request.getParameter("idMatrizOfertaArquivoPag");
		
		if(idMatrizOfertaArquivoIcon != null && !idMatrizOfertaArquivoIcon.equalsIgnoreCase("")) {
			request.setAttribute("idMatrizOfertaArquivoIcon", idMatrizOfertaArquivoIcon);
		}
		if((idMatrizOfertaArquivoIcon == null || idMatrizOfertaArquivoIcon.equals("")) &&
				(idMatrizOfertaArquivoPag != null && !idMatrizOfertaArquivoPag.equals(""))) {
			request.setAttribute("idMatrizOfertaArquivoIcon", idMatrizOfertaArquivoPag);
		}
				
		BuscarListaDetalhesImportacaoRequest buscarListaDetalhesImportacaoRequest = new BuscarListaDetalhesImportacaoRequest();
		ParametrosBuscarListaDetalhesImportacao parametrosBuscarListaDetalhesImportacao = new ParametrosBuscarListaDetalhesImportacao();
		BuscarListaDetalhesImportacaoResponse buscarListaDetalhesImportacaoResponse = new BuscarListaDetalhesImportacaoResponse();
		PaginacaoIn paginacaoIn = new PaginacaoIn();
		MatrizOfertaPortTypeProxy matrizOfertaPortTypeProxy = new MatrizOfertaPortTypeProxy();
		
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPaginaNosPopups());
		if(paginaSolicitada != null && !paginaSolicitada.equalsIgnoreCase("")) {
			paginacaoIn.setPaginaSolicitada(Long.valueOf(paginaSolicitada));
		}else {
			paginacaoIn.setPaginaSolicitada(1);
		}
		if(idMatrizOfertaArquivoIcon != null && !idMatrizOfertaArquivoIcon.equalsIgnoreCase("")) {
			parametrosBuscarListaDetalhesImportacao.setIdMatrizOfertaAquivo(Long.valueOf(idMatrizOfertaArquivoIcon));
		}
		if(idMatrizOfertaArquivoPag != null && !idMatrizOfertaArquivoPag.equalsIgnoreCase("")) {
			parametrosBuscarListaDetalhesImportacao.setIdMatrizOfertaAquivo(Long.valueOf(idMatrizOfertaArquivoPag));
		}
		parametrosBuscarListaDetalhesImportacao.setPaginacaoIn(paginacaoIn);
		buscarListaDetalhesImportacaoRequest.setParametrosBuscarListaDetalhesImportacao(parametrosBuscarListaDetalhesImportacao);
		try {
			buscarListaDetalhesImportacaoResponse = matrizOfertaPortTypeProxy.buscarListaDetalhesImportacao(buscarListaDetalhesImportacaoRequest,this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, ConsultaImportacaoMatrizOfertaAction.class.getName(), ex.getMessage(), consultaImportacaoMatrizOfertaForm, response );
			return null;
		}
		
		
		ResultadoBuscarListaDetalhesImportacao resultadoBuscarListaDetalhesImportacao = buscarListaDetalhesImportacaoResponse.getResultadoBuscarListaDetalhesImportacao();
		PaginacaoOut paginacaoOut = resultadoBuscarListaDetalhesImportacao.getPaginacaoOut();
		if(paginacaoOut != null) {
			tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPaginaNosPopups(),request);
		}
		List<ResultadoBuscarListaDetalhesImportacaoListaDetalhesImportacaoDetalhesImportacao> detalhesImportacaoLista = Arrays.asList(resultadoBuscarListaDetalhesImportacao.getListaDetalhesImportacao());
		
		Map<Long, ResultadoBuscarListaDetalhesImportacaoListaDetalhesImportacaoDetalhesImportacao> mapDetalhesImportacao = new HashMap<Long, ResultadoBuscarListaDetalhesImportacaoListaDetalhesImportacaoDetalhesImportacao>();
		for (ResultadoBuscarListaDetalhesImportacaoListaDetalhesImportacaoDetalhesImportacao detalhe : detalhesImportacaoLista) {
			mapDetalhesImportacao.put(detalhe.getIdMatrizOfertaArquivoItem(), detalhe);
            detalhe.setDsStatus(new MatrizOfertaDelegate().obterTodasCriticasPorIdMatrizOfertaArquivoItem(detalhe.getIdMatrizOfertaArquivoItem()));
		}
		
		//request.setAttribute("detalhesImportacao", detalhesImportacaoLista);
		consultaImportacaoMatrizOfertaForm.setDetalhesImportacao(detalhesImportacaoLista);
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward exportarDetalhesImportacao(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception,CatalogoPRSException, IOException {
	
	        ConsultaImportacaoMatrizOfertaForm consultaImportacaoMatrizOfertaForm = (ConsultaImportacaoMatrizOfertaForm)form;
			ExportarMatrizOfertaArquivoItemRequest exportarMatrizOfertaArquivoItemRequest = new ExportarMatrizOfertaArquivoItemRequest();
			ExportarMatrizOfertaArquivoItemResponse exportarMatrizOfertaArquivoItemResponse = new ExportarMatrizOfertaArquivoItemResponse();
			ParametrosExportarMatrizOfertaArquivoItem parametrosExportarMatrizOfertaArquivoItem = new ParametrosExportarMatrizOfertaArquivoItem();
			MatrizOfertaPortTypeProxy matrizOfertaPortTypeProxy = new MatrizOfertaPortTypeProxy();
			
			if(consultaImportacaoMatrizOfertaForm.getIdMatrizOfertaAquivo() != null) {
				parametrosExportarMatrizOfertaArquivoItem.setIdMatrizOfertaAquivo(consultaImportacaoMatrizOfertaForm.getIdMatrizOfertaAquivo());
			}
			exportarMatrizOfertaArquivoItemRequest.setParametrosExportarMatrizOfertaArquivoItem(parametrosExportarMatrizOfertaArquivoItem);
			try {
				exportarMatrizOfertaArquivoItemResponse = matrizOfertaPortTypeProxy.exportarMatrizOfertaArquivoItem(exportarMatrizOfertaArquivoItemRequest,this.getUserName(),this.getPassword());
			} catch (AxisFault ex) {		
				this.AxisFaultExceptionHandler(ex, ConsultaImportacaoMatrizOfertaAction.class.getName(), ex.getMessage(), consultaImportacaoMatrizOfertaForm, response );
				return null;
			}			
			
			ResultadoExportarMatrizOfertaArquivoItem resultadoExportarMatrizOfertaArquivoItem = exportarMatrizOfertaArquivoItemResponse.getResultadoExportarMatrizOfertaArquivoItem();
			List<ResultadoExportarMatrizOfertaArquivoItemExportarMatrizOfertaArquivoItemMatrizOfertaArquivoItem> arquivoItemLista = Arrays.asList(resultadoExportarMatrizOfertaArquivoItem.getExportarMatrizOfertaArquivoItem());
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("Org.Vendas; Canal Distrib.; Setor de Atividade; Material; Utiliza��o; Escrit�rio de vendas; Grupo de vendedores; Grupo de clientes; Grupo de clientes 1;");
			buffer.append("Grupo de clientes 2; Grupo Classif. Cont�bil Material; Cond de Pgto; Cliente; Pa�s; Grupo de mercadorias; Unid.medida b�sica; Unidade de Venda; Regi�o centro Fornecedor;");
			buffer.append("Estado; Pre�oo; Dt In�cio; Dt Final; DDD; CanalVendas; Acao; Descri��o da Cr�tica \n");
			String nmArquivo = null;
			if(arquivoItemLista != null && !arquivoItemLista.isEmpty()){
				for (ResultadoExportarMatrizOfertaArquivoItemExportarMatrizOfertaArquivoItemMatrizOfertaArquivoItem item : arquivoItemLista) {
					buffer.append(item.getSgOrganizacaoVendas() != null ? item.getSgOrganizacaoVendas() : "");
					buffer.append(";");
					buffer.append(item.getCanalDistribuicao() != null ? item.getCanalDistribuicao() : "");
					buffer.append(";");
					buffer.append(";");
					buffer.append(item.getSgMaterial() != null ? item.getSgMaterial() : "");
					buffer.append(";");
					buffer.append(item.getSgUtilizacao() != null ? item.getSgUtilizacao() : "");
					buffer.append(";");
					buffer.append(item.getSgEscritorioVenda() != null ? item.getSgEscritorioVenda() : "");
					buffer.append(";");
					buffer.append(";");
					buffer.append(";");
					buffer.append(item.getGpClienteUm() != null ? item.getGpClienteUm() : "");
					buffer.append(";");
					buffer.append(";");
					buffer.append(";");
					buffer.append(";");
					buffer.append(";");
					buffer.append(";");
					buffer.append(";");
					buffer.append(";");
					buffer.append(";");
					buffer.append(";");
					buffer.append(";");
					buffer.append(item.getDscPreco() != null ? item.getDscPreco() : "");
					buffer.append(";");
					buffer.append(item.getDtInicial() != null ? item.getDtInicial() : "");
					buffer.append(";");
					buffer.append(item.getDtFinal() != null ? item.getDtFinal() : "");
					buffer.append(";");
					buffer.append(item.getDdd() != null ? item.getDdd() : "");
					buffer.append(";");
					buffer.append(item.getSgCanalVendas() != null ? item.getSgCanalVendas() : "");
					buffer.append(";");
					buffer.append(item.getAcao() != null ? item.getAcao() : "");
					buffer.append(";");
					if(item.getIdMatrizOfertaArquivoItem() !=null){
						MatrizOfertaDelegate matrizOfertaDelegate = new MatrizOfertaDelegate();
	                    buffer.append(matrizOfertaDelegate.obterTodasCriticasPorIdMatrizOfertaArquivoItemExportacao(Long.valueOf(item.getIdMatrizOfertaArquivoItem())));
					}

					buffer.append("\n");
					nmArquivo = item.getNmArquivo();
				}
			}
			
			response.setContentType("application/vnd.ms-excel;charset=iso-8859-1");
			response.addHeader("Content-Disposition", "attachment;filename="+nmArquivo);
			response.setCharacterEncoding("ISO-8859-1");
			
			PrintWriter out = response.getWriter();
			out.write(buffer.toString());
			out.flush();
			return null;
	}

	public ActionForward openPopupDetalhesErroProcessamento(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String descErroProcessamentoArquivo = request.getParameter("descErroProcessamentoArquivo");
		request.setAttribute("descErro", descErroProcessamentoArquivo);
        this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward processarMatrizOfertaArquivo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        ConsultaImportacaoMatrizOfertaForm consultaImportacaoMatrizOfertaForm = (ConsultaImportacaoMatrizOfertaForm)form;
		
		System.out.println(">>>RequestIdMatrizOfertaArquivo:"+request.getParameter("idMatrizOfertaArquivo"));
		System.out.println(">>>FormIdMatrizOfertaArquivo:"+consultaImportacaoMatrizOfertaForm.getIdMatrizOfertaAquivo());
		
		ProcessarMatrizOfertaArquivoRequest requestDocument = new ProcessarMatrizOfertaArquivoRequest();
		ParamProcessarMatrizOfertaArquivo paramProcessarMatrizOfertaArquivo = new ParamProcessarMatrizOfertaArquivo();
		paramProcessarMatrizOfertaArquivo.setIdMatrizOfertaArquivo(consultaImportacaoMatrizOfertaForm.getIdMatrizOfertaAquivo());
		MatrizOfertaPortTypeProxy matrizOfertaPortTypeProxy = new MatrizOfertaPortTypeProxy();
		
		requestDocument.setParamProcessarMatrizOfertaArquivo(paramProcessarMatrizOfertaArquivo);
		
		//Execução assíncrona da operação processarMatrizOfertaArquivo
		try {
			matrizOfertaPortTypeProxy.processarMatrizOfertaArquivo(requestDocument,this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, ConsultaImportacaoMatrizOfertaAction.class.getName(), ex.getMessage(), consultaImportacaoMatrizOfertaForm, response );
			return null;
		}
		
/*		try {
			writeJSOutput("$('botao_listar_arquivos_importados').onclick();");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		PrintWriter writer = null;
		try {
			response.setContentType("text/javascript");
			writer = response.getWriter();
			writer.println("$('botao_listar_arquivos_importados').onclick();");
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			writer.close();
		}		
		return null;
	}
}