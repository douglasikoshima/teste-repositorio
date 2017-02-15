package br.com.vivo.catalogoPRS.pageflows.param.matrizOferta.importacao;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.AxisFault;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONException;
import org.json.JSONObject;

import com.indracompany.catalogo.datalayer.OrganizacaoVenda;

import br.com.vivo.catalogoPRS.bo.UserCatalogo;
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.ImportacaoMatrizOfertaForm;
import br.com.vivo.catalogoPRS.util.JMSUtil;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmMatrizRequest;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmMatrizResponse;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.MatrizOfertaPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametroNomeMatriz;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoListaNomeMatrizListaNomeMatriz;
import br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.BuscarListaOrgVendasComDDDRequest;
import br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.BuscarListaOrgVendasComDDDResponse;
import br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.OrganizacaoVendasPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda;
import edu.emory.mathcs.backport.java.util.Arrays;


public class ImportacaoMatrizOfertaAction extends BaseMappingDispatchAction implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String[] organizacoesSelecionadas;
	private String[] dddsSelecionados;
	private String mensagem;
	private String SUCCESS = "success";
	
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Calendar calendarNow = Calendar.getInstance();
		calendarNow.setTime(new Date());
		
		ImportacaoMatrizOfertaForm ImportacaoMatrizOfertaForm = (ImportacaoMatrizOfertaForm)form;
		
		BuscarListaNmMatrizRequest buscarListaNmMatrizRequest = new BuscarListaNmMatrizRequest();
		ParametroNomeMatriz parametroNomeMatriz = new ParametroNomeMatriz();
		buscarListaNmMatrizRequest.setParametroNomeMatriz(parametroNomeMatriz);
		parametroNomeMatriz.setDataVigencia(calendarNow);
		MatrizOfertaPortTypeProxy matrizOfertaPortTypeProxy = new MatrizOfertaPortTypeProxy();
		BuscarListaNmMatrizResponse buscarListaNmMatrizResponse = new BuscarListaNmMatrizResponse();
		
		try {
			 buscarListaNmMatrizResponse = matrizOfertaPortTypeProxy.buscarListaNmMatriz(buscarListaNmMatrizRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, ImportacaoMatrizOfertaAction.class.getName(), ex.getMessage(), ImportacaoMatrizOfertaForm, response );
			return null;
		}		
		ResultadoListaNomeMatrizListaNomeMatriz[] listaNomeMatrizList = buscarListaNmMatrizResponse.getResultadoListaNomeMatriz();

		request.setAttribute("listaMatrizOferta", listaNomeMatrizList);
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward listarOrganizacaoDeVendas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception, CatalogoPRSException {
		
		String organizacaoJSon = request.getParameter("hidden_org_vendas");
		if (organizacaoJSon != null && !organizacaoJSon.equalsIgnoreCase("")) {
			List<String> chaves = new ArrayList<String>();
			List<String> valores = new ArrayList<String>();
			@SuppressWarnings("unused")
			Map<String, String[]> organizacoesSelecionadas = extrairJSONChaveValor(organizacaoJSon, chaves, valores );
			this.organizacoesSelecionadas = chaves.toArray(new String[]{});
			this.dddsSelecionados = valores.toArray(new String[]{});
		}
		ImportacaoMatrizOfertaForm ImportacaoMatrizOfertaForm = (ImportacaoMatrizOfertaForm)form;
		
		BuscarListaOrgVendasComDDDRequest buscarListaOrgVendasComDDDRequest = new BuscarListaOrgVendasComDDDRequest();
		BuscarListaOrgVendasComDDDResponse buscarListaOrgVendasComDDDResponse = new BuscarListaOrgVendasComDDDResponse();
		OrganizacaoVendasPortTypeProxy organizacaoVendasPortTypeProxy = new OrganizacaoVendasPortTypeProxy();
		
		

		try {
			buscarListaOrgVendasComDDDResponse = organizacaoVendasPortTypeProxy.buscarListaOrgVendasComDDD(buscarListaOrgVendasComDDDRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, ImportacaoMatrizOfertaAction.class.getName(), ex.getMessage(), ImportacaoMatrizOfertaForm, response );
			return null;
		}
		
		ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda[] listaOrganizacaoVendaComDDD = buscarListaOrgVendasComDDDResponse.getResultadoBuscarListaOrgVendasComDDD().getListaOrganizacaoVendaComDDD();
		
		List<OrganizacaoVenda> organizacaoVendaList = Arrays.asList(listaOrganizacaoVendaComDDD);
		
		List<OrganizacaoVenda> listOrganizacaoVendasValida = new ArrayList<OrganizacaoVenda>();
		List<String> listSiglaOrganizacao = null;
		Object listaOrganizacaoSession = request.getSession().getAttribute("LISTA_SIGLA_ORGANIZACAO_VENDAS");
		if(listaOrganizacaoSession != null && !((ArrayList)listaOrganizacaoSession).isEmpty()) {
			listSiglaOrganizacao = (List<String>) request.getSession().getAttribute("LISTA_SIGLA_ORGANIZACAO_VENDAS");
			Collections.sort(listSiglaOrganizacao);
			for (String sessionSigla : listSiglaOrganizacao) {
				for(int i = 0; i < organizacaoVendaList.size(); i++) {
					if(sessionSigla.equalsIgnoreCase(organizacaoVendaList.get(i).getSgOrganizacaoVendas())) {
						listOrganizacaoVendasValida.add(organizacaoVendaList.get(i));
					}
				}
			}
			if(listOrganizacaoVendasValida.size() > 0 && !listOrganizacaoVendasValida.isEmpty()) {
				request.setAttribute("listaOrganizacaoVenda", listOrganizacaoVendasValida);
			}
			else{
				request.setAttribute("mensagem", "  Usuário não possui organização de vendas associada.");
			}
		}
		else {
			request.setAttribute("mensagem", "  Usuário não possui organização de vendas associada.");
		}
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	protected Map<String, String[]> extrairJSONChaveValor(String chaveValorJSON, List<String> chavesToReturn, List<String> valoresToReturn) {
		Map<String, String[]> resultado = new HashMap<String, String[]>();
		chaveValorJSON = chaveValorJSON.replace("N", "");
		if (chaveValorJSON == null || chaveValorJSON.trim().length() == 0)
			return resultado;
		try {
			JSONObject hashmap = new JSONObject(chaveValorJSON);
			for (Iterator chaves = hashmap.keys(); chaves.hasNext();) {
				String chave = (String) chaves.next();
				chavesToReturn.add(chave);
				if (hashmap.get(chave) != null && ((String) hashmap.get(chave)).trim().length() > 0){
					resultado.put(chave,splitAndClean((String) hashmap.get(chave)));
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
	
	public ActionForward confirmarValorSimCard(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("valorSimCard", request.getParameter("vlParameter"));
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward importarArquivoCsvMatrizOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ImportacaoMatrizOfertaForm ImportacaoMatrizOfertaForm = (ImportacaoMatrizOfertaForm)form;
		
		String arquivo = ImportacaoMatrizOfertaForm.getNmArquivo().getFileName();
		String extensao = arquivo.substring(arquivo.lastIndexOf(".", arquivo.length()));
		String tipoMensagem = "importacao_matriz_ofertas";
		int tmArquivo = ImportacaoMatrizOfertaForm.getNmArquivo().getFileSize();
		if(ImportacaoMatrizOfertaForm.getNmArquivo().toString().length() > 50) {
			throw new CatalogoPRSException("O nome do arquivo deve ser at&eacute; 50 caracteres.");
		}
		if(tmArquivo > 7340032) {
			throw new CatalogoPRSException("Tamanho de Arquivo Inv&aacute;lido. O limite m&aacute;ximo para o tamanho do arquivo &eacute;: 7 MB. ");
		}
		if(ImportacaoMatrizOfertaForm.getNmArquivo() == null || ImportacaoMatrizOfertaForm.getNmArquivo().getFileData() == null || ImportacaoMatrizOfertaForm.getNmArquivo().getFileData().length == 0) {
			throw new CatalogoPRSException("Por favor, selecione um Arquivo.");
		}
		if(!extensao.equalsIgnoreCase(".csv")) {
			throw new CatalogoPRSException(" Tipo de Arquivo Inválido.");
		}
		
		UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");

		try {
			JMSUtil.enviarArquivo(
					ImportacaoMatrizOfertaForm.getNmArquivo().getFileData(), 
						arquivo.toUpperCase(), 
						userCatalogo.getUser().getUsername(), 
						"35", 
						tipoMensagem, 
						new InitialContext(),
						ImportacaoMatrizOfertaForm.getIdCanalAtendimento()
						);
			
			this.setMensagem(" Arquivo enviado com Sucesso.");
			String reloadScript = "<input type='text' value='reload_popup_importar_arquivo'/>";
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println(reloadScript);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				response.setContentType("text/javascript");
				PrintWriter writer = response.getWriter();
				writer.println("$('botao_limpar_form_importacao_matriz').click();");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}catch (Exception ex) {
			throw new CatalogoPRSException("Erro ao enviar arquivo para processamento.");
		}
			
		return null;
	}
	
	public ActionForward popupSucessoUpload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("return", this.getMensagem());
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String[] getDddsSelecionados() {
		return dddsSelecionados;
	}
	public void setDddsSelecionados(String[] dddsSelecionados) {
		this.dddsSelecionados = dddsSelecionados;
	}	
	public String[] getOrganizacoesSelecionadas() {
		return organizacoesSelecionadas;
	}
	public void setOrganizacoesSelecionadas(String[] organizacoesSelecionadas) {
		this.organizacoesSelecionadas = organizacoesSelecionadas;
	}	
	
}