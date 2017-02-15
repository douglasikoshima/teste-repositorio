package br.com.vivo.catalogoPRS.pageflows.shared.baseFlow;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.axis.AxisFault;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Node;
import pt.ptinovacao.sca.User;
import br.com.vivo.catalogoPRS.bo.UserCatalogo;
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.exception.ExecutionServiceException;
import br.com.vivo.catalogoPRS.exception.UsuarioInvalidoException;
import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;
import br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn.BuscarListaFabricantePorTipoProdutoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn.BuscarListaFabricantePorTipoProdutoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn.FabricantePortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn.ParametrosBuscarListaFabricantePorTipoProduto;
import br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn.ResultadoBuscarListaFabricantePorTipoProdutoFabricante;
import edu.emory.mathcs.backport.java.util.Arrays;

/*@Jpf.Controller(simpleActions = { @Jpf.SimpleAction(name = "begin", navigateTo = Jpf.NavigateTo.previousPage),
		@Jpf.SimpleAction(name = "baseAction", navigateTo = Jpf.NavigateTo.previousPage) }, catches = {
		@Jpf.Catch(type = ServiceControlException.class, method = "SOAPExceptionHandler"),
		@Jpf.Catch(type = ExecutionServiceException.class, method = "ExecutionServiceExceptionHandler"),
		@Jpf.Catch(type = CatalogoPRSException.class, method = "CatalogoPRSExceptionHandler"),
		@Jpf.Catch(type = UsuarioInvalidoException.class, method = "UsuarioInvalidoExceptionHandler")
// ,
// @Jpf.Catch(type = Exception.class, method = "UnexpectedExceptionHandler")
}, messageBundles = { @Jpf.MessageBundle(bundlePath = "catalogoprs_messages") })*/
public class BaseMappingDispatchAction extends MappingDispatchAction{

	private static Logger logger = Logger.getLogger(BaseMappingDispatchAction.class);

	protected Map<String, String> permissoesDoController = new HashMap<String, String>();
	
	protected List<String> autorizaVazio = new ArrayList<String>();

	protected String funcionalidade;

	private String[] idsTiposProdutoComTecnologiaOpcional = new String[] { "7", "9", "10", "17", "19", "20", "22", "23", "24" };



	
	protected ActionForward AxisFaultExceptionHandler(AxisFault ex, String actionName, String message, Object form, HttpServletResponse response) {
		
		String outMessage;
		if (message == null || message.trim().equals(""))
			outMessage = ex.getMessage();
		else
			outMessage = message;
		if (!outMessage.isEmpty() && outMessage.length() > 255)
			outMessage = outMessage.substring(0, 255);
		try {									
			
			if (ex.getFaultDetails().length > 0) {
				String codigo = null;
				String descricao = null;
				String dadosAdicionais = null;
				
				Node node = ex.getFaultDetails()[0];
				for (int i = 0; i < node.getChildNodes().getLength(); i++) {
					Node nodefilho = node.getChildNodes().item(i).getFirstChild();
					
					
					
					if (nodefilho != null && !nodefilho.hasChildNodes()) {
						if (node.getChildNodes().item(i).getNodeName().contentEquals("ger:codigo")) {
							codigo = nodefilho.getNodeValue();
						}
						if (node.getChildNodes().item(i).getNodeName().contains("descricao")){
							descricao = nodefilho.getNodeValue();							
						}																			
					} else {
						Node nodeFilhoFilho = nodefilho.getFirstChild();
						if (node.getChildNodes().item(i).getNodeName().contains("dadosAdicionais")) {
							dadosAdicionais = nodeFilhoFilho.getNodeValue();
						}						
					}
				}
				
				if (dadosAdicionais != null) { 
					if (dadosAdicionais.equalsIgnoreCase("2")) {
						if (actionName.equalsIgnoreCase("popupDetalheModelo"))
							return new ActionForward("popupDetalhesModelo");
						if (autorizaVazio.contains(actionName)) {
							return new ActionForward("success");
						}
						if(codigo != null && descricao != null){
							tratarErro(codigo + ": " + descricao, "", response);
							return null;
						}
					}else{
						if(codigo != null && descricao != null){
							tratarErro(codigo + ": " + descricao, dadosAdicionais, response);
							return null;
						}
					}
					
				} else {
					if(codigo != null && descricao != null){
						tratarException(codigo + ": " + descricao, ex, response);
						return null;
					}
				}
			}
			
		} catch (Exception e) {
			logger.debug("Extra&ccedil;&atilde;o do ErroInfo falhou. provalmente n&atilde;o era um ErroInfo.");
		}
		tratarException("Ocorreu um erro inesperado.", ex, response);
		return null; 				
	}
	
	
	
	
	
	
	
	/*	protected ActionForward SOAPExceptionHandler(ServiceException ex, String actionName, String message, Object form) {
		String outMessage;
		if (message == null || message.trim().equals(""))
			outMessage = ex.getMessage();
		else
			outMessage = message;
		if (outMessage.length() > 255)
			outMessage = outMessage.substring(0, 255);
		try {
			if(ex.hasSoapFault()){
				String codigo = null;
				String descricao = null;
				String dadosAdicionais = null;
				NodeList childNodes = ex.getSoapFault().getDetail().getDomNode().getFirstChild().getChildNodes();
				for(int i = 0; i < childNodes.getLength(); i++){
					Node node = childNodes.item(i);
					ErroInfo erroInfo = ErroInfo.Factory.parse(node);
					if(erroInfo.getCodigo()!=null){
						codigo = erroInfo.getCodigo();
					}else if(erroInfo.getDescricao()!=null){
						descricao = erroInfo.getDescricao();
					}else if(erroInfo.getDadosAdicionais()!=null){
						dadosAdicionais = erroInfo.getDadosAdicionais().getDomNode().getFirstChild().getFirstChild().getNodeValue();
					}
				}
				if (dadosAdicionais != null) {
					if (dadosAdicionais.equalsIgnoreCase("2")) {
						if (actionName.equalsIgnoreCase("popupDetalheModelo"))
							return new Forward("popupDetalhesModelo");
						if (autorizaVazio.contains(actionName)) {
							return new Forward("success");
						}
						if(codigo != null && descricao != null){
							tratarErro(codigo + ": " + descricao, "");
							return null;
						}
					}else{
						if(codigo != null && descricao != null){
							tratarErro(codigo + ": " + descricao, dadosAdicionais);
							return null;
						}
					}
				}else{
					if(codigo != null && descricao != null){
						tratarException(codigo + ": " + descricao, ex);
						return null;
					}
				}
				
			}
		} catch (Exception e) {
			logger.debug("Extra��o do ErroInfo falhou. provalmente n�o era um ErroInfo.");
		}
		
		tratarException("Ocorreu um erro inesperado.", ex);
		return null;
	}*/
	
	protected ActionForward ExecutionServiceExceptionHandler(ExecutionServiceException ex, String actionName, String message, Object form, HttpServletResponse response) {
		String outMessage;
		if (message == null || message.trim().equals(""))
			outMessage = ex.getMessage();
		else
			outMessage = message;
		if (outMessage.length() > 255)
			outMessage = outMessage.substring(0, 255);
		try {
			/*if(ex.hasSoapFault()){
				String codigo = null;
				String descricao = null;
				String dadosAdicionais = null;
				NodeList childNodes = ex.getSoapFault().getDetail().getDomNode().getFirstChild().getChildNodes();
				for(int i = 0; i < childNodes.getLength(); i++){
					Node node = childNodes.item(i);
					ErroInfo erroInfo = ErroInfo.Factory.parse(node);
					if(erroInfo.getCodigo()!=null){
						codigo = erroInfo.getCodigo();
					}else if(erroInfo.getDescricao()!=null){
						descricao = erroInfo.getDescricao();
					}else if(erroInfo.getDadosAdicionais()!=null){
						dadosAdicionais = erroInfo.getDadosAdicionais().getDomNode().getFirstChild().getFirstChild().getNodeValue();
					}
				}
				if (dadosAdicionais != null) {
					if (dadosAdicionais.equalsIgnoreCase("2")) {
						if (actionName.equalsIgnoreCase("popupDetalheModelo"))
							return new Forward("popupDetalhesModelo");
						if (autorizaVazio.contains(actionName)) {
							return new Forward("success");
						}
						if(codigo != null && descricao != null){
							tratarErro(codigo + ": " + descricao, "");
							return null;
						}
					}else{
						if(codigo != null && descricao != null){
							tratarErro(codigo + ": " + descricao, dadosAdicionais);
							return null;
						}
					}
				}else{
					if(codigo != null && descricao != null){
						tratarException(codigo + ": " + descricao, ex);
						return null;
					}
				}
				
			}*/
		} catch (Exception e) {
			logger.debug("Extra&ccedil;&atilde;o do ErroInfo falhou. provalmente n&atilde;o era um ErroInfo.");
		}
		
		tratarServiceException("Ocorreu um erro inesperado.", ex, response);
		return null;
	}
	
	protected ActionForward UnexpectedExceptionHandler(Exception ex, String actionName, String message, Object form, HttpServletResponse response) {
		tratarException("Ocorreu um erro inesperado.", ex, response);
		return null;
	}

	public ActionForward CatalogoPRSExceptionHandler(CatalogoPRSException ex, String actionName, HttpServletResponse response) {
		tratarException(ex.getMessage(), ex, response);
		return null;
	}

	//@Jpf.ExceptionHandler(forwards = { @Jpf.Forward(name = "loginInvalido", path = "/catalogo/br/com/vivo/catalogoPRS/pageflows/login/LoginAction.do") })
	protected ActionForward UsuarioInvalidoExceptionHandler(UsuarioInvalidoException ex, String actionName, String message, Object form) throws IOException {
		//getSession().removeAttribute("logged_user");
		if (actionName.equals("goHome"))
			return new ActionForward("loginInvalido");
		writeJSOutput("window.location.href='/catalogo/br/com/vivo/catalogoPRS/pageflows/login/LoginAction.do'");
		return null;
	}


	@SuppressWarnings("unchecked")
	public ActionForward buscarFabricantes(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String result = new String();
		String idTipoProduto = request.getParameter("idTipoProduto");

		// Assign JSON array to result String
		JSONObject resultadoJSON = new JSONObject();
		try {
			if (ArrayUtils.contains(idsTiposProdutoComTecnologiaOpcional, idTipoProduto)) {
				resultadoJSON.put("tecnologiaObrigatoria", false);
			} else {
				resultadoJSON.put("tecnologiaObrigatoria", true);
			}
		} catch (JSONException e) {

		}

		try {

			FabricantePortTypeProxy fabricantePortTypeProxy = new FabricantePortTypeProxy();
			BuscarListaFabricantePorTipoProdutoRequest buscarListaFabricantePorTipoProdutoRequest = new BuscarListaFabricantePorTipoProdutoRequest();
			ParametrosBuscarListaFabricantePorTipoProduto parametrosBuscarListaFabricantePorTipoProduto = new ParametrosBuscarListaFabricantePorTipoProduto();
			BuscarListaFabricantePorTipoProdutoResponse buscarListaFabricantePorTipoProdutoResponse;
			
			
			parametrosBuscarListaFabricantePorTipoProduto.setIdTipoProduto(Long.valueOf(idTipoProduto));
			buscarListaFabricantePorTipoProdutoRequest.setParametrosBuscarListaFabricantePorTipoProduto(parametrosBuscarListaFabricantePorTipoProduto);
			buscarListaFabricantePorTipoProdutoResponse=fabricantePortTypeProxy.buscarListaFabricantePorTipoProduto(buscarListaFabricantePorTipoProdutoRequest, this.getUserName(), this.getPassword());
			
			
			@SuppressWarnings("unchecked")
			List<ResultadoBuscarListaFabricantePorTipoProdutoFabricante>lista=Arrays.asList(buscarListaFabricantePorTipoProdutoResponse.getResultadoBuscarListaFabricantePorTipoProduto());
			
			JSONArray jsonArrayFabricantes = new JSONArray();
			// Iterate over modelos entries
			for (ResultadoBuscarListaFabricantePorTipoProdutoFabricante fabricante : lista) {
				JSONObject jsonResult = new JSONObject();
				jsonResult.put("id", fabricante.getIdFabricante());
				jsonResult.put("nome", fabricante.getNmFabricante());
				jsonArrayFabricantes.put(jsonResult);
			}

			resultadoJSON.put("arrayFabricantes", jsonArrayFabricantes);

		} catch (Exception e) {
			try {
				JSONArray jsonArrayFabricantes = new JSONArray();
				JSONObject jsonResult = new JSONObject();
				jsonResult.put("id", "-1");
				jsonResult.put("nome", "DESCONHECIDO");
				jsonArrayFabricantes.put(jsonResult);
				resultadoJSON.put("arrayFabricantes", jsonArrayFabricantes);
			} catch (JSONException ex) {

			}
		}
		result = resultadoJSON.toString();
		PrintWriter out = response.getWriter();
		out.println(result);
		out.flush();

		return null;
	}

	public ActionForward buscarModelos() {
		/*BuscarListaModeloPorTipoProdutoFabricanteRequestDocument paramBuscarModeloPorTipoProdutoFabricante = BuscarListaModeloPorTipoProdutoFabricanteRequestDocument.Factory.newInstance();
		RaizModeloIn raizModeloIn = paramBuscarModeloPorTipoProdutoFabricante.addNewBuscarListaModeloPorTipoProdutoFabricanteRequest().addNewParametrosBuscarListaModeloPorTipoProdutoFabricante().addNewRaizModeloIn();

		String idFabricante = getRequest().getParameter("idFabricante");
		raizModeloIn.setIdFabricante(Long.parseLong(idFabricante));

		String idTipoProduto = getRequest().getParameter("idTipoProduto");
		raizModeloIn.setIdTipoProduto(Long.parseLong(idTipoProduto));

		try {
		prepareServiceControl(grupoProdutoSoapServiceControl);
		BuscarListaModeloPorTipoProdutoFabricanteResponseDocument modeloPorTipoProdutoFabricanteResponseDocument = grupoProdutoSoapServiceControl.buscarListaModeloPorTipoProdutoFabricante(paramBuscarModeloPorTipoProdutoFabricante);
		ResultadoBuscarListaModeloPorTipoProdutoFabricante resultadoBuscarListaModeloPorTipoProdutoFabricante = modeloPorTipoProdutoFabricanteResponseDocument.getBuscarListaModeloPorTipoProdutoFabricanteResponse().getResultadoBuscarListaModeloPorTipoProdutoFabricante();
		List<Modelo> modeloList = new ArrayList<Modelo>();
		if(resultadoBuscarListaModeloPorTipoProdutoFabricante != null){
			modeloList = resultadoBuscarListaModeloPorTipoProdutoFabricante.getModeloList();
		}

		JSONArray jsonArrayModelos = new JSONArray();
		// Iterate over modelos entries
		
		
		
		for (Modelo modelo : modeloList) {
			JSONObject jsonResult = new JSONObject();
			
				jsonResult.put("id", modelo.getIdModelo());
			jsonResult.put("nome", modelo.getNmModelo());
			jsonArrayModelos.put(jsonResult);
		}

		// Define placeholder for JSON response
		String result = new String();
		// Assign JSON array to result String
		result = new JSONObject().put("arrayModelos", jsonArrayModelos).toString();

		PrintWriter out = this.getResponse().getWriter();
		out.println(result);
		out.flush();
		} catch (JSONException e) {
			logger.error("error ao criar o objeto JSON de modelos.");
		} catch (IOException e) {
			logger.error("error ao escrever no output do response.");
		} catch (ServiceControlException e){
			//do nothing
		}*/
		return null;
	}

	public void tratarException(String message, Exception e, HttpServletResponse response) {
		try {
			//HttpServletResponse response = getResponse();
			PrintWriter out = response.getWriter();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			String outMessage = "<ul id='popup_erros_ul' style='word-break:break-all;'><li style='word-break:break-all;'>"; 
			outMessage += message;
			if (ApplicationConfiguration.isDebug()){
				String header = "&nbsp;<span style=\"color:gray;\" id=\"showDetails\" onclick=\"document.getElementById('detalhes').style.display='';document.getElementById('hideDetails').style.display='';document.getElementById('showDetails').style.display='none';\"}\">(+)</span><span id='hideDetails' style='display:none;color:gray;' onclick=\"document.getElementById('detalhes').style.display='none';document.getElementById('showDetails').style.display='';document.getElementById('hideDetails').style.display='none';\">(-)</span><div id='detalhes' style='display:none;position:relative;top:10;word-break:break-all;'>";
				String stackTrace = getStackTrace(e);
				String footer = "</div>";
				outMessage += header + stackTrace + footer;
			}
			outMessage += "</li></ul>";
			out.write(outMessage);
			out.println();
			out.flush();
		} catch (IOException ex) {
			logger.error("Erro ao tratar a Exception: " + e.getClass().getName());
		}
	}

	public void tratarServiceException(String message, ExecutionServiceException e, HttpServletResponse response) {
		try {
			//HttpServletResponse response = getResponse();
			PrintWriter out = response.getWriter();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			String outMessage = "<ul id='popup_erros_ul' style='word-break:break-all;'><li style='word-break:break-all;'>"; 
			outMessage += message;
			if (ApplicationConfiguration.isDebug()){
				String header = "&nbsp;<span style=\"color:gray;\" id=\"showDetails\" onclick=\"document.getElementById('detalhes').style.display='';document.getElementById('hideDetails').style.display='';document.getElementById('showDetails').style.display='none';\"}\">(+)</span><span id='hideDetails' style='display:none;color:gray;' onclick=\"document.getElementById('detalhes').style.display='none';document.getElementById('showDetails').style.display='';document.getElementById('hideDetails').style.display='none';\">(-)</span><div id='detalhes' style='display:none;position:relative;top:10;word-break:break-all;'>";
				String stackTrace = getStackTrace(e);
				String footer = "</div>";
				outMessage += header + "<br/><div style='width:710px; height:100px; overflow:scroll; border: 1px solid #9DC4ED;' ><pre>" + e.getMessageRequest().toString().replace("<", "&lt;") + "</pre></div><br/><div style='width:710px; height:100px; overflow:scroll; border: 1px solid #9DC4ED;'><pre>" + e.getMessageResponse().toString().replace("<", "&lt;") + "</pre></div><br/><div style='width:710px; height:100px; overflow:scroll; border: 1px solid #9DC4ED;' >Stack Trace: <br/> " + stackTrace + "</div>" + footer;
			}
			outMessage += "</li></ul>";
			out.write(outMessage);
			out.println();
			out.flush();
		} catch (IOException ex) {
			logger.error("Erro ao tratar a Exception: " + e.getClass().getName());
		}
	}
	
	public void tratarErro(String message, String dadosAdicionais, HttpServletResponse response) {
		try {
			//HttpServletResponse response = getResponse();
			PrintWriter out = response.getWriter();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			String outMessage = "<ul id='popup_erros_ul' style='word-break:break-all;'><li style='word-break:break-all;'>";
			outMessage += message;
			if (ApplicationConfiguration.isDebug() && (dadosAdicionais != null) && (dadosAdicionais.trim().length()>0) && !(dadosAdicionais.equals("3"))){
				String header = "&nbsp;<span style='color:gray;' id=\"showDetails\" onclick=\"document.getElementById('detalhes').style.display='';document.getElementById('hideDetails').style.display='';document.getElementById('showDetails').style.display='none';\">(+)</span><span id='hideDetails' style='display:none;color:gray;' onclick=\"document.getElementById('detalhes').style.display='none';document.getElementById('showDetails').style.display='';document.getElementById('hideDetails').style.display='none';\">(-)</span><div id='detalhes' style='display:none;position:relative;top:10;word-break:break-all;'>";
				String footer = "</div>";
				outMessage += dadosAdicionais + header + dadosAdicionais + footer;
			}
			outMessage += "</li></ul>";
			out.write(outMessage);
			out.println();
			out.flush();
		} catch (IOException ex) {
			logger.error("Erro ao tratar a Erro: " + message);
		}
	}
	
	private String getStackTrace(Throwable aThrowable) {
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		aThrowable.printStackTrace(printWriter);
		return result.toString();
	}

	/*
	 * protected JSONObject extrairMapaDeArray(String dadosBrutos){ JSONObject
	 * hashmap = new JSONObject(dadosBrutos); for (Iterator chaves =
	 * hashmap.keys(); chaves.hasNext();) { String chave = chaves.next(); Object
	 * arrayBruto = hashmap.get(chave);
	 * ArrayUtils.addAll(idsValoresCaracteristicas, splitAndClean((String)
	 * hashmap.get(idCaracteristica))); } }
	 */

	protected String[] splitAndClean(String csv) {
		String[] splitted = null;
		if (csv != null) {
			splitted = csv.split(",");
			if (splitted.length == 1 && splitted[0].trim().equals(""))
				splitted = null;
		}
		return splitted;
	}
	
	protected String arrayToCSV(String[] array) {
		StringBuilder sb = new StringBuilder();
		if (array != null && array.length > 0) {
			boolean first = true;
			for (String valor : array) {
				if(first){
					sb.append(valor);
					first = false;
				}else{
					sb.append(",");
					sb.append(valor);
				}
			};
		}
		return sb.toString();
	}

	public void tratarPaginacao(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoOut paginacaoOut, int elementosPorPagina, HttpServletRequest request) {
		if (paginacaoOut == null)
			return;
		int paginaAtual = paginacaoOut.getPaginaAtual();
		int totalRegistros = paginacaoOut.getTotalRegistros();
		long totalPagina = Math.round((totalRegistros / elementosPorPagina));
		if (((totalRegistros / (double) elementosPorPagina) - totalPagina) > 0)
			totalPagina++;

		request.setAttribute("paginaAtual", paginaAtual);
		request.setAttribute("totalPagina", totalPagina);
		request.setAttribute("totalRegistros", paginacaoOut.getTotalRegistros());
	}
	
	public void tratarPaginacao(br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.PaginacaoOut paginacaoOut, int elementosPorPagina, HttpServletRequest request) {
		if (paginacaoOut == null)
			return;
		int paginaAtual = paginacaoOut.getPaginaAtual();
		int totalRegistros = paginacaoOut.getTotalRegistros();
		long totalPagina = Math.round((totalRegistros / elementosPorPagina));
		if (((totalRegistros / (double) elementosPorPagina) - totalPagina) > 0)
			totalPagina++;

		request.setAttribute("paginaAtual", paginaAtual);
		request.setAttribute("totalPagina", totalPagina);
		request.setAttribute("totalRegistros", paginacaoOut.getTotalRegistros());
	}

	public void tratarPaginacao(br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut paginacaoOut, int elementosPorPagina, HttpServletRequest request) {
		if (paginacaoOut == null)
			return;
		int paginaAtual = paginacaoOut.getPaginaAtual();
		int totalRegistros = paginacaoOut.getTotalRegistros();
		long totalPagina = Math.round((totalRegistros / elementosPorPagina));
		if (((totalRegistros / (double) elementosPorPagina) - totalPagina) > 0)
			totalPagina++;

		request.setAttribute("paginaAtual", paginaAtual);
		request.setAttribute("totalPagina", totalPagina);
		request.setAttribute("totalRegistros", paginacaoOut.getTotalRegistros());
	}
	
	public void tratarPaginacao(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.PaginacaoOut paginacaoOut, int elementosPorPagina, HttpServletRequest request) {
		if (paginacaoOut == null)
			return;
		int paginaAtual = paginacaoOut.getPaginaAtual();
		int totalRegistros = paginacaoOut.getTotalRegistros();
		long totalPagina = Math.round((totalRegistros / elementosPorPagina));
		if (((totalRegistros / (double) elementosPorPagina) - totalPagina) > 0)
			totalPagina++;

		request.setAttribute("paginaAtual", paginaAtual);
		request.setAttribute("totalPagina", totalPagina);
		request.setAttribute("totalRegistros", paginacaoOut.getTotalRegistros());
	}
	
	public void tratarPaginacao(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.PaginacaoOut paginacaoOut, int elementosPorPagina, HttpServletRequest request) {
		if (paginacaoOut == null)
			return;
		int paginaAtual = paginacaoOut.getPaginaAtual();
		int totalRegistros = paginacaoOut.getTotalRegistros();
		long totalPagina = Math.round((totalRegistros / elementosPorPagina));
		if (((totalRegistros / (double) elementosPorPagina) - totalPagina) > 0)
			totalPagina++;

		request.setAttribute("paginaAtual", paginaAtual);
		request.setAttribute("totalPagina", totalPagina);
		request.setAttribute("totalRegistros", paginacaoOut.getTotalRegistros());
	}
	
	public void tratarPaginacao(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.PaginacaoOut paginacaoOut, int elementosPorPagina, HttpServletRequest request) {
		if (paginacaoOut == null)
			return;
		int paginaAtual = paginacaoOut.getPaginaAtual();
		int totalRegistros = paginacaoOut.getTotalRegistros();
		long totalPagina = Math.round((totalRegistros / elementosPorPagina));
		if (((totalRegistros / (double) elementosPorPagina) - totalPagina) > 0)
			totalPagina++;

		request.setAttribute("paginaAtual", paginaAtual);
		request.setAttribute("totalPagina", totalPagina);
		request.setAttribute("totalRegistros", paginacaoOut.getTotalRegistros());
	}
	

	/**
	 * Callback that is invoked when this controller instance is created.
	 */
	/*protected void onCreate() {
		fabricanteSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.FABRICANTE));
		grupoProdutoSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.GRUPO_PRODUTO));
		multimidiaSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.MULTIMIDIA));
	}*/

	/**
	 * Callback that is invoked when this controller instance is destroyed.
	 */
	protected void onDestroy(HttpSession session) {
	}

	protected void beforeAction() throws UsuarioInvalidoException, LoginException, CatalogoPRSException {
		/*HttpServletResponse response = getResponse();
		

		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must- revalidate");

		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		//response.addHeader("Cache-Control", "post-check=0, pre-check=0");

		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		
		// Set to expire far in the past.
		response.setDateHeader("Expires", 0);
		
		if (false)
			return;
		if (getDisplayName().equals("/catalogo/br/com/vivo/catalogoPRS/pageflows/login/LoginAction.do")) {
			if (getCurrentActionName().equals("doLogin") || getCurrentActionName().equals("begin") || getCurrentActionName().equals("carregarAlterarSenha")
					|| getCurrentActionName().equals("alterarSenha")) {
				getSession().removeAttribute("logged_user");
				return;
			}
			if (getCurrentActionName().equals("doLogout"))
				return;
		}
		if (getSession().getAttribute("logged_user") == null) {
			throw new UsuarioInvalidoException(getDisplayName() + "::Usu�rio n�o autenticado.");
		}

		if (!(getSession().getAttribute("logged_user") instanceof UserCatalogo)) {
			getSession().removeAttribute("logged_user");
			throw new UsuarioInvalidoException(getDisplayName() + "::Usu�rio n�o autenticado.");
		}
		UserCatalogo loggedUser = (UserCatalogo) getSession().getAttribute("logged_user");
		if(SCAUtils.hasExpired(loggedUser)){
			SCAUtils.logout(loggedUser);
			throw new UsuarioInvalidoException("Sess�o expirada.");
		}
		loggedUser.setUltimaAtividade(new Date());
		LoginProcess loginProcess = SCAUtils.verify(loggedUser);
		if (!loginProcess.isUserAuthenticated())
			throw new UsuarioInvalidoException(getDisplayName() + "::Usu�rio inv�lido.");

		if (permissoesDoController.get(getCurrentActionName()) != null) {
			if (!SCAUtils.hasPermissoes(getLoggedUser(), (String) permissoesDoController.get(getCurrentActionName()))) {
				throw new CatalogoPRSException("Usu�rio nem tem permiss�o suficiente.");
			}
		} else {
			// throw new CatalogoPRSException("Usu�rio nem tem permiss�o
			// suficiente.");
		}*/

	}

	protected UserCatalogo getLoggedUser() {
		//return (UserCatalogo) getSession().getAttribute("logged_user");
		return null;
	}

	protected void forwardParameter(HttpServletRequest request, String parameterName) {
		//HttpServletRequest request = getRequest();
		request.setAttribute(parameterName, request.getParameter(parameterName));
	}

	protected void writeJSOutput(String js) throws IOException {
		//HttpServletResponse response = getResponse();
		//response.setContentType("text/javascript");
		//PrintWriter out = this.getResponse().getWriter();
		//out.println(js);
		//out.flush();
	}

	protected Long[] extrairIdsComPrefix(String[] idsComPrefix, String prefix) {
		List<Long> ids = new ArrayList<Long>();
		for (String idComPrefix : idsComPrefix) {
			if (idComPrefix.startsWith(prefix))
				ids.add(Long.valueOf(idComPrefix.substring(prefix.length())));
		}
		Long[] idsArray = ids.toArray(new Long[ids.size()]);
		return idsArray;
	}
	
	protected String[] extrairIdsComPrefixS(String[] idsComPrefix, String prefix) {
		List<String> ids = new ArrayList<String>();
		for (String idComPrefix : idsComPrefix) {
			if (idComPrefix.startsWith(prefix))
				ids.add(idComPrefix.substring(prefix.length()));
		}
		String[] idsArray = ids.toArray(new String[ids.size()]);
		return idsArray;
	}
	
	protected Map<String, String[]> extrairJSONChaveValor(String chaveValorJSON, List<String> chavesToReturn, List<String> valoresToReturn) {
		Map<String, String[]> resultado = new HashMap<String, String[]>();
		if (chaveValorJSON == null || chaveValorJSON.trim().length() == 0)
			return resultado;
		try {
			JSONObject hashmap = new JSONObject(chaveValorJSON);
			for (Iterator chaves = hashmap.keys(); chaves.hasNext();) {
				String chave = (String) chaves.next();
				chavesToReturn.add(chave);
				if (hashmap.get(chave) != null && ((String) hashmap.get(chave)).trim().length() > 0){
					resultado.put(chave,splitAndClean((String) hashmap.get(chave)));
					
					String [] valores = splitAndClean((String) hashmap.get(chave));
					
					for(int i=0; i<valores.length; i++){
						valoresToReturn.add(valores[i]);
					}
					//ArrayUtils.addAll(valoresToReturn, splitAndClean((String) hashmap.get(chave)));
				}
				else
					resultado.put(chave, null);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	protected String converterMapToJSON(Map<String, String[]> map) {
		String resultado = "";
		if (map == null)
			return resultado;
		try {
			JSONObject hashmap = new JSONObject();
			for (Iterator chaves = map.keySet().iterator(); chaves.hasNext();) {
				String chave = (String) chaves.next();
				if (map.get(chave) != null && map.get(chave).length > 0)
					hashmap.put(chave,   arrayToCSV(map.get(chave)));
				else
					hashmap.put(chave, "");

			}
		resultado = hashmap.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	/*protected void prepareServiceControl(ServiceControl control, HttpServletRequest request) {
		control.setMessageUsername(ApplicationConfiguration.getWebserviceSecurityUsername());
		control.setMessagePassword(ApplicationConfiguration.getWebserviceSecurityPassword());
		String nomeServico = ApplicationConfiguration.getNomeServico();
		control.setOutputHeaders(SOAPUtil.createOutputHeader(getLoggedUser().getUser().getUsername(), funcionalidade, getLoggedUser().getClientIP(), request, nomeServico));
	}*/

	protected String getUserName() {
		return ApplicationConfiguration.getWebserviceSecurityUsername();
	}
	
	protected String getPassword() {
		return ApplicationConfiguration.getWebserviceSecurityPassword();
	}
	
	protected void cleanHeader(HttpServletResponse response){
		response.setHeader("Cache-Control", "no-store, no-cache, must- revalidate");
		response.setHeader("Pragma", "no-cache");
	}
	
	protected User getUserLogged(HttpServletRequest request) {
		UserCatalogo userCatalogo = (UserCatalogo) request.getSession().getAttribute("logged_user");
		return userCatalogo.getUser();
	}
	
	protected void exportFile(String data, String header, String contentType, String nameFile, HttpServletResponse response) {
		
		if (data != null) {
			response.setContentType(contentType);
			response.setHeader("Content-Disposition", "attachment; filename=" + nameFile);
	        
	        try {
				OutputStream out = response.getOutputStream();
				String dataStr = StringUtils.join(new String[]{header == null ? "" : header + "\n", data});
				response.setContentLength(dataStr.getBytes().length);
				out.write(dataStr.getBytes());
		        out.flush();
		        out.close();
			} catch (IOException e) {
				logger.error(e);
			}
		}
	}
	
	protected void exportFile(List<String> data, String header, String contentType, String nameFile, HttpServletResponse response) {
		
        StringBuffer retorno = new StringBuffer();
        
        if (data != null) {
			for (String contact : data) {
				retorno.append(contact + "\n");
			} 
			
			exportFile(retorno.toString(), header, contentType, nameFile, response);
		}
	}	
}