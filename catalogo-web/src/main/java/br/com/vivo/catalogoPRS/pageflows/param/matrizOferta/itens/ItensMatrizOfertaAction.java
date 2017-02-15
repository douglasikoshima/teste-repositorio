package br.com.vivo.catalogoPRS.pageflows.param.matrizOferta.itens;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import edu.emory.mathcs.backport.java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.indracompany.catalogo.datalayer.AreaRegistro;
import com.indracompany.catalogo.datalayer.CanalAtendimento;
import com.indracompany.catalogo.datalayer.OrganizacaoVenda;

import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.CadastroCabecalhoMatrizOfertaForm;
import br.com.vivo.catalogoPRS.pageflows.shared.form.ItensMatrizOfertaForm;
import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;
import br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.AcaoPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.BuscarListaAcaoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.BuscarListaAcaoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.ParametroAcao;
import br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.ParametroAcaoInDisponivel;
import br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.ResultadoAcaoListaAcaoAcao;
import br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn;
import br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.mc.geral.ErroInfo;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemDataRequest;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.AlterarItemPrecoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaItensRequest;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaItensResponse;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmMatrizRequest;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmMatrizResponse;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmOfSAPPorMatrizRequest;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.BuscarListaNmOfSAPPorMatrizResponse;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.MatrizOfertaPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ObterExistenciaItemPrecoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ObterExistenciaItemPrecoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ObterVLAVistaVL10VezesRequest;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ObterVLAVistaVL10VezesResponse;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametroNomeMatriz;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosAlterarItemData;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosAlterarItemPreco;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosBuscarListaItens;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosBuscarListaNmOfSAPPorMatriz;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosObterExistenciaItemPreco;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosObterVLAVistaVL10Vezes;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaItensListaItensItens;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaNmOfSAPPorMatrizOfertaSAP;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoListaNomeMatrizListaNomeMatriz;
import br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.BuscarListaOrgVendasComDDDRequest;
import br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.BuscarListaOrgVendasComDDDResponse;
import br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.OrganizacaoVendasPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda;
import br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVendaListaDDDDdd;


public class ItensMatrizOfertaAction extends BaseMappingDispatchAction {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Date dataVigencia;
	private String[] organizacoesSelecionadas;
	private String[] dddsSelecionados;
	private static Logger logger = Logger.getLogger(ItensMatrizOfertaAction.class);

	
	@SuppressWarnings({ "static-access", "unchecked" })
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		ActionForward actionForw = new ActionForward();
			
		Calendar dataVigencia = Calendar.getInstance();
		Date date = new Date();
		HttpSession session = request.getSession();
		
		Object formulario = session.getAttribute("formBean");
		if(formulario != null ) {
			CadastroCabecalhoMatrizOfertaForm formBean = (CadastroCabecalhoMatrizOfertaForm) formulario;
			request.setAttribute("idMatrizOferta", formBean.getIdMatrizOferta());
			dataVigencia.setTime(sdf.parse(formBean.getDtVigenciaInicial()));
			request.setAttribute("dataVigencia", sdf.format(dataVigencia.getTime()));
			session.removeAttribute("formBean");
		}else{
			dataVigencia.setTime(date);
			request.setAttribute("dataVigencia", sdf.format(date));
		}

		BuscarListaNmMatrizRequest buscarListaNmMatrizRequest = new BuscarListaNmMatrizRequest();
		ParametroNomeMatriz parametroNomeMatriz = new ParametroNomeMatriz();
		parametroNomeMatriz.setDataVigencia(dataVigencia);
		buscarListaNmMatrizRequest.setParametroNomeMatriz(parametroNomeMatriz);
		
		MatrizOfertaPortTypeProxy matrizOfertaPortTypeProxy = new MatrizOfertaPortTypeProxy();
		BuscarListaNmMatrizResponse buscarListaNmMatrizResponse = new BuscarListaNmMatrizResponse();
		
		try {
			buscarListaNmMatrizResponse = matrizOfertaPortTypeProxy.buscarListaNmMatriz(buscarListaNmMatrizRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'begin' da classe: " + getClass().getName());
			this.AxisFaultExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );
			return null;
		}
		
		ResultadoListaNomeMatrizListaNomeMatriz[] matrizOfertaLista = null;
		if (buscarListaNmMatrizResponse.getResultadoListaNomeMatriz() != null) {
			matrizOfertaLista = buscarListaNmMatrizResponse.getResultadoListaNomeMatriz();
		}
		
		request.setAttribute("matrizOferta", matrizOfertaLista);

		List<CanalAtendimento> listaCanal = (ArrayList<CanalAtendimento>) request.getSession().getAttribute("canalAtendimentoList");				
		Collections.sort(listaCanal);
		
		request.setAttribute("canalList", listaCanal);
		
		BuscarListaAcaoRequest buscarListaAcaoRequest = new BuscarListaAcaoRequest();
		BuscarListaAcaoResponse buscarListaAcaoResponse = new BuscarListaAcaoResponse();
		AcaoPortTypeProxy acaoPortTypeProxy = new AcaoPortTypeProxy();
		
		ParametroAcao parametroAcao = new ParametroAcao();
		ParametroAcaoInDisponivel inDisponivel = null;
		parametroAcao.setInDisponivel(inDisponivel.S);
		buscarListaAcaoRequest.setParametroAcao(parametroAcao);
		
		
		try {
			buscarListaAcaoResponse = acaoPortTypeProxy.buscarListaAcao(buscarListaAcaoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'begin' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );
		}
		
		ResultadoAcaoListaAcaoAcao[] acaoList = null;
		if (buscarListaAcaoResponse.getResultadoAcao() != null) {
			acaoList = buscarListaAcaoResponse.getResultadoAcao().getListaAcao();			
		} 
							
		request.setAttribute("acaoList", acaoList);
		
		
		if (actionForw == null) {
			return actionForw;			
		} else {
			return mapping.findForward("success");
		}
	}

	public ActionForward pesquisarItensMatrizOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ParseException, ErroInfo, RemoteException {

		ItensMatrizOfertaForm formulario = (ItensMatrizOfertaForm)form;
		
		/*
		 * configura o servi�o a ser chamado
		 */
		
		BuscarListaItensRequest buscarListaItensRequest = new BuscarListaItensRequest();
		ParametrosBuscarListaItens parametrosBuscarListaItens = new ParametrosBuscarListaItens();
		PaginacaoIn paginacaoIn = new PaginacaoIn();
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		parametrosBuscarListaItens.setPaginacaoIn(paginacaoIn);
		/*
		 * configura o parametros de entrada para o servi�o
		 */
		
		if(formulario.getSgOrganizacaoVendas() != null && formulario.getSgOrganizacaoVendas().length() > 0) { //configura a organizacao de vendas e DDDs
			List<String> idOrganizacaoVendas = new ArrayList<String>();
			List<String> dddLista = new ArrayList<String>();
			extrairJSONChaveValor(formulario.getSgOrganizacaoVendas(), idOrganizacaoVendas, dddLista);
			for (String idOrganizacao : idOrganizacaoVendas) {
				if(idOrganizacao != null && !idOrganizacao.equals("")) {
					parametrosBuscarListaItens.setIdOrganizacaoVendas(Long.valueOf(idOrganizacao));
				}
			}
			if(dddLista.size() > 0 && dddLista != null) {
				if(parametrosBuscarListaItens.getListaDDD() == null) {
					long[] listaDDD = new long[dddLista.size()];
					
					for(int i = 0; i < dddLista.size(); i++) {
						if (dddLista != null && dddLista.size() > 0) {	
							listaDDD[i] = Long.valueOf(dddLista.get(i));							
						}
					}
				}
			}
		}
		Calendar dataVigencia = Calendar.getInstance(); // configura a data de vigencia
		if(formulario.getDtVigenciaForm() != null && !formulario.getDtVigenciaForm().equals("")) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dataVigencia.setTime(dateFormat.parse(formulario.getDtVigenciaForm()));
			parametrosBuscarListaItens.setDtVigencia(dataVigencia);
		}
		if(formulario.getCdCodigo() !=null && !formulario.getCdCodigo().equals("")) { // configura o codigo do produto
			parametrosBuscarListaItens.setCdProduto(formulario.getCdCodigo());
		}
		if(formulario.getIdOfertaSap() != null && formulario.getIdOfertaSap() > 0) { // configura a oferta sap
			parametrosBuscarListaItens.setIdOfertaSAP(formulario.getIdOfertaSap());
		}
		if(formulario.getCanalVendas() != null && !formulario.getCanalVendas().equals("")) { // configura o cdadabas/canal de vendas
			parametrosBuscarListaItens.setCanalVendas(formulario.getCanalVendas());
		}
		if(formulario.getPaginaSolicitada() != null && !formulario.getPaginaSolicitada().equals("")) { 
			paginacaoIn.setPaginaSolicitada(formulario.getPaginaSolicitada());
		}else {
			paginacaoIn.setPaginaSolicitada(1);
		}
		
		parametrosBuscarListaItens.setIdCanalAtendimento(formulario.getIdCanalAtendimento());			

		if(formulario.getSgAcao() != null && !formulario.getSgAcao().equals("")) {
			parametrosBuscarListaItens.setSgAcao(formulario.getSgAcao());
		}
		
		parametrosBuscarListaItens.setIdMatrizOferta(formulario.getIdMatrizOferta());
		
		MatrizOfertaPortTypeProxy matrizOfertaPortTypeProxy = new MatrizOfertaPortTypeProxy();
		BuscarListaItensResponse buscarListaItensResponse = new BuscarListaItensResponse();
		PaginacaoOut paginacaoOut = new PaginacaoOut();
		
		buscarListaItensRequest.setParametrosBuscarListaItens(parametrosBuscarListaItens);
		
		try {
			buscarListaItensResponse = matrizOfertaPortTypeProxy.buscarListaItens(buscarListaItensRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'pesquisarItensMatrizOferta' da classe: " + getClass().getName());
			this.AxisFaultExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );
			return null;
		}		
		
		ResultadoBuscarListaItensListaItensItens[] itensLista = null;
		if (buscarListaItensResponse.getResultadoBuscarListaItens() != null) {
			itensLista = buscarListaItensResponse.getResultadoBuscarListaItens().getListaItens();
			paginacaoOut = buscarListaItensResponse.getResultadoBuscarListaItens().getPaginacaoOut();
		} else {
			
			request.setAttribute("mensagem", " LISTA VAZIA");
		}
		
		
		Map<String, String> mapaDataIncial = new HashMap<String, String>(); 
		Map<String, String> mapaDataFinal = new HashMap<String, String>(); 
		
		if (itensLista != null) {
			for(int i = 0; i < itensLista.length; i++) {
				//TimeZone timeZone;
				//timeZone = TimeZone.getTimeZone("GMT+0:00");
				//TimeZone.setDefault(timeZone);
				
				if (itensLista[i].getDtInicial() != null) {
					//itensLista[i].getDtInicial().setTimeZone(timeZone);
					
					Calendar dtInicial = itensLista[i].getDtInicial();
					Date dataIni = dtInicial.getTime();
					DateFormat dfIni = new SimpleDateFormat("dd/MM/yyyy");
					String reportDateIni = dfIni.format(dataIni);
					mapaDataIncial.put(itensLista[i].getCdCodigo(), reportDateIni);
					
				}
				
				if (itensLista[i].getDtFinal() != null) {
					//itensLista[i].getDtFinal().setTimeZone(timeZone);
					
					Calendar dtFinal = itensLista[i].getDtFinal();
					Date dataFim = dtFinal.getTime();
					DateFormat dfFim = new SimpleDateFormat("dd/MM/yyyy");
					String reportDateFim = dfFim.format(dataFim);
					mapaDataFinal.put(itensLista[i].getCdCodigo(), reportDateFim);
				}
			}	
		}
		
		this.tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(), request);
		
		request.setAttribute("itens", itensLista);
		formulario.setItensLista(itensLista);
		formulario.setMapaDataIncial(mapaDataIncial);
		formulario.setMapaDataFinal(mapaDataFinal);
		
		return mapping.findForward("success");
	}

	@SuppressWarnings("unchecked")
	public ActionForward buscarListaOrganizacaoDeVendas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ErroInfo, RemoteException {

		ItensMatrizOfertaForm formulario = (ItensMatrizOfertaForm)form;
		
		String organizacao = request.getParameter("hidden_org_vendas");
		if (organizacao != null && !organizacao.equalsIgnoreCase("")) {
			List<String> chaves = new ArrayList<String>();
			List<String> valores = new ArrayList<String>();
			@SuppressWarnings("unused")
			Map<String, String[]> organizacoesSelecionadas = extrairJSONChaveValor(organizacao, chaves, valores );
			this.organizacoesSelecionadas = chaves.toArray(new String[]{});
			this.dddsSelecionados = valores.toArray(new String[]{});
		}
		
		BuscarListaOrgVendasComDDDRequest buscarListaOrgVendasComDDDRequest = new BuscarListaOrgVendasComDDDRequest();
		BuscarListaOrgVendasComDDDResponse buscarListaOrgVendasComDDDResponse = new BuscarListaOrgVendasComDDDResponse();
		OrganizacaoVendasPortTypeProxy organizacaoVendasPortTypeProxy = new OrganizacaoVendasPortTypeProxy();
		
		try {
			buscarListaOrgVendasComDDDResponse = organizacaoVendasPortTypeProxy.buscarListaOrgVendasComDDD(buscarListaOrgVendasComDDDRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'buscarListaOrganizacaoDeVendas' da classe: " + getClass().getName());
			this.AxisFaultExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );
			return null;
		}
		
		
		ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda[] listaOrganizacaoVendaComDDD = null;
		if (buscarListaOrgVendasComDDDResponse.getResultadoBuscarListaOrgVendasComDDD() != null) {
			
			listaOrganizacaoVendaComDDD = buscarListaOrgVendasComDDDResponse.getResultadoBuscarListaOrgVendasComDDD().getListaOrganizacaoVendaComDDD();
		}
		
		List<OrganizacaoVenda> listOrganizacaoVendasValida = new ArrayList<OrganizacaoVenda>();
		if (listaOrganizacaoVendaComDDD != null) {
			List<String> listSiglaOrganizacao = (List<String>) request.getSession().getAttribute("LISTA_SIGLA_ORGANIZACAO_VENDAS");
			//FIX MANTIS 11332
			if(listSiglaOrganizacao != null && listSiglaOrganizacao.size() > 0) {
				Collections.sort(listSiglaOrganizacao);
				for (String sessionSigla : listSiglaOrganizacao) {
					for(int i = 0; i < listaOrganizacaoVendaComDDD.length; i++) {
						if(sessionSigla.equalsIgnoreCase(listaOrganizacaoVendaComDDD[i].getSgOrganizacaoVendas())) {
							int idOrganizacaoVendas = listaOrganizacaoVendaComDDD[i].getIdOrganizacaoVendas().intValue();
							String nmOrganizacaoVendas = listaOrganizacaoVendaComDDD[i].getNmOrganizacaoVendas();
							String sgOrganizacaoVendas = listaOrganizacaoVendaComDDD[i].getSgOrganizacaoVendas();
							ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVendaListaDDDDdd[] listaDDD = listaOrganizacaoVendaComDDD[i].getListaDDD();
				
							OrganizacaoVenda ov = new OrganizacaoVenda();
							if (listaDDD != null) {
								
								List<AreaRegistro> listaAreaRegistro = new ArrayList<AreaRegistro>();
								for (int j = 0; j < listaDDD.length; j++) {																
									int codigoArea = listaDDD[j].getCodigoArea().intValue();
									
									AreaRegistro codArea = new AreaRegistro();
									codArea.setCodigoArea(Integer.toString(codigoArea));
									listaAreaRegistro.add(codArea);																									
								}
								
								ov.setIdOrganizacaoVendas(idOrganizacaoVendas);
								ov.setNmOrganizacaoVendas(nmOrganizacaoVendas);
								ov.setSgOrganizacaoVendas(sgOrganizacaoVendas);
								ov.setAreaRegistroList(listaAreaRegistro);
									
								listOrganizacaoVendasValida.add(ov);
							}						
						}
					}
				}
			}//FIX MANTIS 11332
		
		}
		
		if (listOrganizacaoVendasValida.size() > 0) {
			
			request.setAttribute("listaOrganizacaoVenda", listOrganizacaoVendasValida);			
			formulario.setListOrganizacaoVendasValida(listOrganizacaoVendasValida);
		}
		else {
			request.setAttribute("mensagem", "  Usu&aacute;rio n&atilde;o possui organiza&ccedil;&atilde;o de vendas associada.");
		}
		
		return mapping.findForward("success");
	}

	public ActionForward buscarListaNomeMatrizOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		ItensMatrizOfertaForm formulario = (ItensMatrizOfertaForm)form;
		
		ResultadoListaNomeMatrizListaNomeMatriz[] matrizOfertaListaEmpty = null;
		request.setAttribute("matrizOferta", matrizOfertaListaEmpty);
		String dtVigencia = request.getParameter("dtVigencia");
		if(dtVigencia != null && dtVigencia.trim().length() > 0) {
			this.setDataVigencia(sdf.parse(dtVigencia));
		}
		Calendar dataVigencia = Calendar.getInstance();
		dataVigencia.setTime(this.getDataVigencia());
		String result = new String();
		JSONObject resultadoJSON = new JSONObject();
		
		try {
			
			BuscarListaNmMatrizRequest buscarListaNmMatrizRequest = new BuscarListaNmMatrizRequest();
			BuscarListaNmMatrizResponse buscarListaNmMatrizResponse = new BuscarListaNmMatrizResponse();
			ParametroNomeMatriz parametroNomeMatriz = new ParametroNomeMatriz();
			parametroNomeMatriz.setDataVigencia(dataVigencia);
			
			buscarListaNmMatrizRequest.setParametroNomeMatriz(parametroNomeMatriz);			
			MatrizOfertaPortTypeProxy matrizOfertaPortTypeProxy = new MatrizOfertaPortTypeProxy();
			try {
				buscarListaNmMatrizResponse = matrizOfertaPortTypeProxy.buscarListaNmMatriz(buscarListaNmMatrizRequest, this.getUserName(), this.getPassword());
			} catch (AxisFault ex) {
				logger.debug("Exception no method: 'buscarListaNomeMatrizOferta' da classe: " + getClass().getName());
				this.AxisFaultExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );
				return null;
			}
			
			ResultadoListaNomeMatrizListaNomeMatriz[] matrizOfertaLista = null;
			if (buscarListaNmMatrizResponse.getResultadoListaNomeMatriz() != null) {
				matrizOfertaLista = buscarListaNmMatrizResponse.getResultadoListaNomeMatriz();
			}
			
			JSONArray jsonArrayMatrizOferta = new JSONArray();
			for (int i = 0; i < matrizOfertaLista.length; i++ ) {									
			
				JSONObject jsonResult = new JSONObject();
				jsonResult.put("id", matrizOfertaLista[i].getIdMatrizOferta());
				jsonResult.put("nome", matrizOfertaLista[i].getNmMatrizOferta());
				jsonArrayMatrizOferta.put(jsonResult);
			}
			resultadoJSON.put("arrayMatrizOferta", jsonArrayMatrizOferta);
			
		} catch (Exception e) {
			try {
				JSONArray jsonArrayMatrizOferta = new JSONArray();
				JSONObject jsonResult = new JSONObject();
				jsonResult.put("id", "");
				jsonResult.put("nome", "");
				jsonArrayMatrizOferta.put(jsonResult);
				resultadoJSON.put("arrayMatrizOferta", jsonArrayMatrizOferta);
			} catch (JSONException ex) {

			}
		}
		result = resultadoJSON.toString();
		
		PrintWriter out = response.getWriter();
		out.println(result);
		out.flush();
		return null;
	}

	public ActionForward abrirParaAlterarItensMatrizOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		if(request.getParameter("cd_codigo") != null && request.getParameter("cd_codigo").trim().length() > 0) {
			request.setAttribute("cdCodigo", request.getParameter("cd_codigo"));
		}
		if(request.getParameter("ds_produto") != null && request.getParameter("ds_produto").trim().length() > 0) {
			request.setAttribute("dsProduto", request.getParameter("ds_produto"));
		}
		if(request.getParameter("cd_oferta_sap") != null && request.getParameter("cd_oferta_sap").trim().length() > 0) {
			request.setAttribute("cdOfertaSAP", request.getParameter("cd_oferta_sap"));
		}
		if(request.getParameter("dsc_oferta_sap") != null && request.getParameter("dsc_oferta_sap").trim().length() > 0) {
			request.setAttribute("dscOfertaSAP", request.getParameter("dsc_oferta_sap"));
		}
		if(request.getParameter("sg_utilizacao") != null && request.getParameter("sg_utilizacao").trim().length() > 0) {
			request.setAttribute("sgUtilizacao", request.getParameter("sg_utilizacao"));
		}
		if(request.getParameter("sg_organizacao_vendas") != null && request.getParameter("sg_organizacao_vendas").trim().length() > 0) {
			request.setAttribute("sgOrganizacaoVendas", request.getParameter("sg_organizacao_vendas"));
		}
		if(request.getParameter("codigo_area") != null && request.getParameter("codigo_area").trim().length() > 0) {
			if(request.getParameter("codigo_area").equals("0")) {
				request.setAttribute("codigoArea", "");
			} else {
				request.setAttribute("codigoArea", request.getParameter("codigo_area"));
			}
		}
		if(request.getParameter("canal_vendas") != null && request.getParameter("canal_vendas").trim().length() > 0) {
			request.setAttribute("canalVendas", request.getParameter("canal_vendas"));
		}
		
		if(request.getParameter("nm_acao") != null && request.getParameter("nm_acao").trim().length() > 0) {
			request.setAttribute("nmAcao", request.getParameter("nm_acao"));
		}
		
		if(request.getParameter("valor_base_a_vista") != null && request.getParameter("valor_base_a_vista").trim().length() > 0) {
			request.setAttribute("valorBaseAVista", request.getParameter("valor_base_a_vista"));
		}
		if(request.getParameter("valor_item_a_vista") != null && request.getParameter("valor_item_a_vista").trim().length() > 0) {
			request.setAttribute("valorItemAVista", request.getParameter("valor_item_a_vista").replace('.', ','));
		}
		if(request.getParameter("valor_chip_a_vista") != null && request.getParameter("valor_chip_a_vista").trim().length() > 0) {
			request.setAttribute("valorChipAVista", request.getParameter("valor_chip_a_vista"));
		}
		if(request.getParameter("valor_chip_a_vista") != null && request.getParameter("valor_chip_a_vista").trim().length() > 0){
			if(request.getParameter("valor_item_a_vista") != null && request.getParameter("valor_item_a_vista").trim().length() > 0) {
				request.setAttribute("valorTotalAVista", 
						Double.parseDouble(request.getParameter("valor_item_a_vista"))+Double.parseDouble(request.getParameter("valor_chip_a_vista")));
			}
		}
		if(request.getParameter("valor_base") != null && request.getParameter("valor_base").trim().length() > 0) {
			request.setAttribute("valorBase", request.getParameter("valor_base"));
		}
		if(request.getParameter("valor_item") != null && request.getParameter("valor_item").trim().length() > 0) {
			request.setAttribute("valorItem", request.getParameter("valor_item").replace('.', ','));
		}
		if(request.getParameter("valor_chip") != null && request.getParameter("valor_chip").trim().length() > 0) {
			request.setAttribute("valorChip", request.getParameter("valor_chip"));
		}
		if(request.getParameter("valor_chip") != null && request.getParameter("valor_chip").trim().length() > 0) {
			if(request.getParameter("valor_item") != null && request.getParameter("valor_item").trim().length() > 0) {
				request.setAttribute("valorTotal", 
						Double.parseDouble(request.getParameter("valor_item"))+Double.parseDouble(request.getParameter("valor_chip")));
			}
		}
		if(request.getParameter("dt_inicial") != null && request.getParameter("dt_inicial").trim().length() > 0) {
			request.setAttribute("dtInicial", request.getParameter("dt_inicial"));
		}
		if(request.getParameter("dt_final") != null && request.getParameter("dt_final").trim().length() > 0) {
			request.setAttribute("dtFinal", request.getParameter("dt_final"));
		}
		if(request.getParameter("id_Matriz_oferta_item_preco") != null && request.getParameter("id_Matriz_oferta_item_preco").trim().length() > 0) {
			request.setAttribute("idMatrizOfertaItemPreco", request.getParameter("id_Matriz_oferta_item_preco"));
		}
		
		if(request.getParameter("idCanalDistribuicao") != null && request.getParameter("idCanalDistribuicao").trim().length() > 0) {
			request.setAttribute("idCanalDistribuicao", request.getParameter("idCanalDistribuicao"));
		}
		
		if(request.getParameter("idAcao") != null && request.getParameter("idAcao").trim().length() > 0  && !request.getParameter("idAcao").equals("0")) {
			request.setAttribute("idAcao", request.getParameter("idAcao"));
		}
		
		forwardParameter(request, "idEscritorioVenda");
		forwardParameter(request, "nmEscritorioVenda");

		return mapping.findForward("success");
	}

	public ActionForward alterarItensMatrizOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException, ParseException, ErroInfo, RemoteException {

		ItensMatrizOfertaForm formulario = (ItensMatrizOfertaForm)form;
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		format.setLenient(false);
		Calendar dtIniAtual = null;
		Calendar dtIniNova = null;
		Calendar dtFimAtual = null;
		Calendar dtFimNova = null;
		Calendar aux = new GregorianCalendar();
		Calendar hoje = new GregorianCalendar();
		BigDecimal precoFinal = new BigDecimal(0);
		hoje.clear();
		hoje.set(aux.get(Calendar.YEAR), aux.get(Calendar.MONTH), aux.get(Calendar.DAY_OF_MONTH));
		long idMatrizItemPrecoExist = 0;
		
		// valida a existencia dos campos necessarios para data.
		if(formulario.getDtInicioAtual() != null){
			dtIniAtual = new GregorianCalendar();
			
			try {
				dtIniAtual.setTime(format.parse(formulario.getDtInicioAtual()));
			} catch (ParseException e) {
				CatalogoPRSException ex = new CatalogoPRSException("Data inicial de vig&ecirc;ncia inv&aacute;lida.");
				this.CatalogoPRSExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), response);
				return null;
			}
			
		} else {
			CatalogoPRSException ex = new CatalogoPRSException("Data inicial de vig&ecirc;ncia inv&aacute;lida.");
			this.CatalogoPRSExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), response);
			return null;
		}
		
		if(formulario.getDtInicioNova() != null){
			dtIniNova = new GregorianCalendar();
			
			try {
				dtIniNova.setTime(format.parse(formulario.getDtInicioNova()));
			} catch (ParseException e) {
				CatalogoPRSException ex = new CatalogoPRSException("Data inicial de vig&ecirc;ncia inv&aacute;lida.");
				this.CatalogoPRSExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), response);
				return null;
			}
			
		} else {
			CatalogoPRSException ex = new CatalogoPRSException("Data inicial de vig&ecirc;ncia inv&aacute;lida.");
			this.CatalogoPRSExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), response);
			return null;
		}
		
		if(formulario.getDtFimAtual() != null){
			dtFimAtual = new GregorianCalendar();
			
			try {
				dtFimAtual.setTime(format.parse(formulario.getDtFimAtual()));
			} catch (ParseException e) {
				CatalogoPRSException ex = new CatalogoPRSException("Data final de vig&ecirc;ncia inv&aacute;lida.");
				this.CatalogoPRSExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), response);
				return null;
			}
			
		} else {
			CatalogoPRSException ex = new CatalogoPRSException("Data final de vig&ecirc;ncia inv&aacute;lida.");
			this.CatalogoPRSExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), response);
			return null;
		}
		
		if(formulario.getDtFimNova() != null){
			dtFimNova = new GregorianCalendar();
			
			try {
				dtFimNova.setTime(format.parse(formulario.getDtFimNova()));
			} catch (ParseException e) {
				CatalogoPRSException ex = new CatalogoPRSException("Data final de vig&ecirc;ncia inv&aacute;lida.");
				this.CatalogoPRSExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), response);
				return null;
			}
			
		} else {
			CatalogoPRSException ex = new CatalogoPRSException("Data final de vig&ecirc;ncia inv&aacute;lida.");
			this.CatalogoPRSExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), response);
			return null;
		}
		
		if(dtFimNova.compareTo(dtIniNova) < 0) {
			CatalogoPRSException ex = new CatalogoPRSException("A data final da vig&ecirc;ncia deve ser maior ou igual a data inicial.");
			this.CatalogoPRSExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), response);
			return null;
		}
		
		if(formulario.getIdMatrizOfertaItemPreco() == null){
			CatalogoPRSException ex = new CatalogoPRSException("Identificador de pre&ccedil;o do item inv&aacute;lido.");
			this.CatalogoPRSExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), response);
			return null;
		}
		
		// convertendo o pre�o
		if(formulario.getPrecificacao() != null) {
			
			if(formulario.getPrecificacao().equals("Parcelado")){
				
				if(formulario.getValorItemNovo() != null || !formulario.getValorItemNovo().equals("")){
					
					precoFinal = BigDecimal.valueOf(getMonValue(formulario.getValorItemNovo().replace(',', '.')));
					BigDecimal precoBase = BigDecimal.valueOf(getMonValue(formulario.getValorBase().substring(3).replace(".", "").replace(",", ".")));
					
					if(precoFinal.compareTo(precoBase) > 0) {
						CatalogoPRSException ex = new CatalogoPRSException("Pre&ccedil;o do item deve ser menor ou igual ao pre&ccedil;o base do produto.");
						this.CatalogoPRSExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), response);
						return null;
					}
					
				} else {
					CatalogoPRSException ex = new CatalogoPRSException("Pre&ccedil;o do item n&atilde;o especificado.");
					this.CatalogoPRSExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), response);
					return null;
				}
				
			} else {
				
				if(formulario.getPrecificacao().equals("À vista")) {
					
					if(formulario.getValorItemAVistaNovo() == null || formulario.getValorItemAVistaNovo().equals("")) {
						CatalogoPRSException ex = new CatalogoPRSException("Pre&ccedil;o do item n&atilde;o especificado.");
						this.CatalogoPRSExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), response);
						return null;
					}
					
					BigDecimal precoBase = BigDecimal.valueOf(getMonValue(formulario.getValorBaseAVista().substring(3).replace(".", "").replace(",", ".")));
					BigDecimal precoAVista = BigDecimal.valueOf(getMonValue(formulario.getValorItemAVistaNovo().replace(',', '.')));
					
					if(precoAVista.compareTo(precoBase) > 0) {
						CatalogoPRSException ex = new CatalogoPRSException("Pre&ccedil;o do item deve ser menor ou igual ao pre&ccedil;o base do produto.");
						this.CatalogoPRSExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), response);
						return null;
					}
					
					MatrizOfertaPortTypeProxy matrizOfertaPortTypeProxy = new MatrizOfertaPortTypeProxy();
					
					ObterVLAVistaVL10VezesRequest obterVLAVistaVL10VezesRequest = new ObterVLAVistaVL10VezesRequest();
					ObterVLAVistaVL10VezesResponse obterVLAVistaVL10VezesResponse = new ObterVLAVistaVL10VezesResponse();
					ParametrosObterVLAVistaVL10Vezes parametrosObterVLAVistaVL10Vezes = new ParametrosObterVLAVistaVL10Vezes();
					
					
					parametrosObterVLAVistaVL10Vezes.setValorAVista(precoAVista);
					parametrosObterVLAVistaVL10Vezes.setIdCanalDistribuicao(formulario.getIdCanalDistricuicao());
					obterVLAVistaVL10VezesRequest.setParametrosObterVLAVistaVL10Vezes(parametrosObterVLAVistaVL10Vezes);
					
					try {
						obterVLAVistaVL10VezesResponse = matrizOfertaPortTypeProxy.obterVLAVistaVL10Vezes(obterVLAVistaVL10VezesRequest, this.getUserName(), this.getPassword());
					} catch (AxisFault ex) {						
						logger.debug("Exception no method: 'alterarItensMatrizOferta' da classe: " + getClass().getName());
						this.AxisFaultExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );
						return null;
					}
					
					if (obterVLAVistaVL10VezesResponse.getResultadoObterVLAVistaVL10Vezes() != null) {
						precoFinal = obterVLAVistaVL10VezesResponse.getResultadoObterVLAVistaVL10Vezes().getValorParcelado();
					}
					
				} else {
					CatalogoPRSException ex = new CatalogoPRSException("Nenhum tipo de forma de pagamento especificada.");
					this.CatalogoPRSExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), response);
					return null;
				}
			}

		} else {
			CatalogoPRSException ex = new CatalogoPRSException("Forma de pagamento nula.");
			this.CatalogoPRSExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), response);
			return null;
		}
		
		if(this.obterExistenciaItemPreco(mapping
				, form
				, request
				, response
				, dtIniNova
				, precoFinal
				, formulario.getSgOrganizacaoVendas()
				, formulario.getCodigoArea()
				, formulario.getCanalVendas()
				, formulario.getIdCanalDistricuicao()
				, formulario.getIdMatrizOfertaItemPreco()
				, formulario.getIdAcao()
				, dtFimNova
				, formulario.getIdEscritorioVenda()) != 0){
			
			CatalogoPRSException ex = new CatalogoPRSException("Pre&ccedil;o j&aacute; existente.");
			this.CatalogoPRSExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), response);
			return null;
		}
		
		idMatrizItemPrecoExist = this.obterExistenciaItemPreco(mapping
				, form
				, request
				, response
				, dtIniNova
				, null
				, formulario.getSgOrganizacaoVendas()
				, formulario.getCodigoArea()
				, formulario.getCanalVendas()
				, formulario.getIdCanalDistricuicao()
				, formulario.getIdMatrizOfertaItemPreco()
				, formulario.getIdAcao()
				, dtFimNova
				, formulario.getIdEscritorioVenda());

		String retornoAlterarItens = "";
		
		if(idMatrizItemPrecoExist != 0){
			
			if (dtIniNova.compareTo(hoje) == 0) {
				this.alterarItemPrecoAudit(mapping, formulario, request, response, idMatrizItemPrecoExist, precoFinal);
			} else { // Data inicial maior que a data atual, pois o sistema barra data inicial menor que data atual
				
				String retorno = "";
				retorno = this.alterarItemPreco(mapping, formulario, request, response, idMatrizItemPrecoExist, precoFinal);
				
				if (retorno.equals("erro")) {
					return null;					
				}
				
			}

		} else { // Não encontrado registro de preço  equivalente o sistema cria um novo registro de preço
			retornoAlterarItens = alterarItemData(mapping, form, request, response, formulario.getIdMatrizOfertaItemPreco(),	dtIniNova, dtFimNova, precoFinal);
		}

		if (retornoAlterarItens != null && !retornoAlterarItens.isEmpty()) {
			return null;
			
		} else {
			
			PrintWriter writer = null;
			
			try {
				response.setContentType("text/javascript");
				writer = response.getWriter();
				writer.println("$('botao_pesquisar_itens_matriz_oferta').onclick();");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				writer.close();
			}
			
		}
		

		return null;
	}
	
	public ActionForward popupConfirmacaoGravar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException, ParseException, ErroInfo, RemoteException {
		
		return mapping.findForward("success");
	}
	
	private String alterarItemPreco(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Long idItempreco, BigDecimal valorFinal) throws ErroInfo, RemoteException{
		
		ItensMatrizOfertaForm formulario = (ItensMatrizOfertaForm)form;
		
		MatrizOfertaPortTypeProxy matrizOfertaPortTypeProxy = new MatrizOfertaPortTypeProxy();
		AlterarItemPrecoRequest alterarItemPrecoRequest = new AlterarItemPrecoRequest();
		ParametrosAlterarItemPreco parametrosAlterarItemPreco = new ParametrosAlterarItemPreco();
		parametrosAlterarItemPreco.setIdMatrizOfertaItemPreco(idItempreco);
		parametrosAlterarItemPreco.setValor(valorFinal.doubleValue());
		parametrosAlterarItemPreco.setNmUsuarioAlteracao("");
		
		alterarItemPrecoRequest.setParametrosAlterarItemPreco(parametrosAlterarItemPreco);
		
		try {
			matrizOfertaPortTypeProxy.alterarItemPreco(alterarItemPrecoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'alterarItemPreco' da classe: " + getClass().getName());
			this.AxisFaultExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );
			return "erro";
		}
		
		return "success";
	}

	private void alterarItemPrecoAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Long idItempreco, BigDecimal valorFinal) throws ErroInfo, RemoteException{
		this.alterarItemPreco(mapping, form, request, response, idItempreco, valorFinal);
	}
	
	private long obterExistenciaItemPreco(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Calendar dtIniNova, BigDecimal precoFinal, String sgOrgVendas, String codigoArea, String cdAdabas, Long idCanalDitribuicao, Long idMatrizOfertaItemPreco, Long idAcao, Calendar dtFimNova, Long idEscritorioVenda ) throws ErroInfo, RemoteException {
		
		ItensMatrizOfertaForm formulario = (ItensMatrizOfertaForm)form;
		
		ObterExistenciaItemPrecoRequest obterExistenciaItemPrecoRequest = new ObterExistenciaItemPrecoRequest();
		ParametrosObterExistenciaItemPreco parametrosObterExistenciaItemPreco = new ParametrosObterExistenciaItemPreco();
		parametrosObterExistenciaItemPreco.setDtInicial(dtIniNova);
		parametrosObterExistenciaItemPreco.setDtFinal(dtFimNova);
		
		if (precoFinal != null) {
			parametrosObterExistenciaItemPreco.setValor(precoFinal);
		}
		
		if (cdAdabas != null && !cdAdabas.trim().equals("")) {
			parametrosObterExistenciaItemPreco.setCdAdabas(cdAdabas);
		}
		if (codigoArea != null && !codigoArea.trim().equals("")) {
			parametrosObterExistenciaItemPreco.setCodigoArea(Long.parseLong(codigoArea));
		}
		if (idAcao != null && idAcao.longValue() != 0) {
			parametrosObterExistenciaItemPreco.setIdAcao(idAcao);
		}
		if (idEscritorioVenda != null && idEscritorioVenda.longValue() != 0) {
			parametrosObterExistenciaItemPreco.setIdEscritorioVenda(idEscritorioVenda);
		}
		parametrosObterExistenciaItemPreco.setIdCanalDistribuicao(idCanalDitribuicao);
		parametrosObterExistenciaItemPreco.setIdMatrizOfertaItemPreco(idMatrizOfertaItemPreco);
		parametrosObterExistenciaItemPreco.setSgOrgVendas(sgOrgVendas);
		
		obterExistenciaItemPrecoRequest.setParametrosObterExistenciaItemPreco(parametrosObterExistenciaItemPreco);
		
		
		MatrizOfertaPortTypeProxy matrizOfertaPortTypeProxy = new MatrizOfertaPortTypeProxy();
		ObterExistenciaItemPrecoResponse obterExistenciaItemPrecoResponse = new ObterExistenciaItemPrecoResponse();
		
		
		try {
			obterExistenciaItemPrecoResponse = matrizOfertaPortTypeProxy.obterExistenciaItemPreco(obterExistenciaItemPrecoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {			
			logger.debug("Exception no method: 'obterExistenciaItemPreco' da classe: " + getClass().getName());
			this.AxisFaultExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );
		}
		
		long qtdItemPreco = obterExistenciaItemPrecoResponse.getResultadoObterExistenciaItemPreco().getQtdItemPreco();
		
		
		return qtdItemPreco;
	}

	private String alterarItemData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, Long idMatrizOfertaItemPreco, Calendar dtIniNova, Calendar dtFimNova, BigDecimal precoFinal) throws CatalogoPRSException, ErroInfo, RemoteException {
		
		ItensMatrizOfertaForm formulario = (ItensMatrizOfertaForm)form;
		
		AlterarItemDataRequest alterarItemDataRequest = new AlterarItemDataRequest();
		ParametrosAlterarItemData parametrosAlterarItemData = new ParametrosAlterarItemData();
		parametrosAlterarItemData.setIdMatrizOfertaItemPreco(idMatrizOfertaItemPreco);
		parametrosAlterarItemData.setDtInicial(dtIniNova);
		parametrosAlterarItemData.setDtFinal(dtFimNova);
		parametrosAlterarItemData.setValor(precoFinal);
		parametrosAlterarItemData.setNmUsuarioAlteracao("");
		
		alterarItemDataRequest.setParametrosAlterarItemData(parametrosAlterarItemData);
				
		MatrizOfertaPortTypeProxy matrizOfertaPortTypeProxy = new MatrizOfertaPortTypeProxy();
		
		try {
			matrizOfertaPortTypeProxy.alterarItemData(alterarItemDataRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'alterarItemData' da classe: " + getClass().getName());
			this.AxisFaultExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );
			return "error";
		}		
		
		return "success";
		
	}

	@SuppressWarnings("unused")
	public ActionForward buscarListaOfertaSapPorMatriz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		ItensMatrizOfertaForm formulario = (ItensMatrizOfertaForm)form;
		ActionForward actionForw = new ActionForward();
		
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			String idMatrizOferta = request.getParameter("idMatrizOferta");

			BuscarListaNmOfSAPPorMatrizRequest buscarListaNmOfSAPPorMatrizRequest = new BuscarListaNmOfSAPPorMatrizRequest();
			ParametrosBuscarListaNmOfSAPPorMatriz parametrosBuscarListaNmOfSAPPorMatriz = new ParametrosBuscarListaNmOfSAPPorMatriz();
			
			
			
			
			if(idMatrizOferta != null && !idMatrizOferta.equals("")) {
				parametrosBuscarListaNmOfSAPPorMatriz.setIdMatrizOferta(Long.parseLong(idMatrizOferta));
			}
			
			MatrizOfertaPortTypeProxy matrizOfertaPortTypeProxy = new MatrizOfertaPortTypeProxy();
			BuscarListaNmOfSAPPorMatrizResponse buscarListaNmOfSAPPorMatrizResponse = new BuscarListaNmOfSAPPorMatrizResponse();
			buscarListaNmOfSAPPorMatrizRequest.setParametrosBuscarListaNmOfSAPPorMatriz(parametrosBuscarListaNmOfSAPPorMatriz);
			
			
			try {
				buscarListaNmOfSAPPorMatrizResponse = matrizOfertaPortTypeProxy.buscarListaNmOfSAPPorMatriz(buscarListaNmOfSAPPorMatrizRequest, this.getUserName(), this.getPassword());
			} catch (AxisFault ex) {
				logger.debug("Exception no method: 'buscarListaOfertaSapPorMatriz' da classe: " + getClass().getName());
				actionForw = this.AxisFaultExceptionHandler(ex, ItensMatrizOfertaAction.class.getName(), ex.getMessage(), formulario, response );
				return null;
			}
			
			ResultadoBuscarListaNmOfSAPPorMatrizOfertaSAP[] resultadoBuscarListaNmOfSAPPorMatriz = null;
			if (buscarListaNmOfSAPPorMatrizResponse.getResultadoBuscarListaNmOfSAPPorMatriz() != null) {
				resultadoBuscarListaNmOfSAPPorMatriz = buscarListaNmOfSAPPorMatrizResponse.getResultadoBuscarListaNmOfSAPPorMatriz();
			}
			
			JSONArray jsonArrayOfertaSAP = new JSONArray();
			for (int i = 0; i < resultadoBuscarListaNmOfSAPPorMatriz.length; i++) {
				JSONObject jsonResult = new JSONObject();
				jsonResult.put("id", resultadoBuscarListaNmOfSAPPorMatriz[i].getIdOfertaSAP());
				jsonResult.put("nome", resultadoBuscarListaNmOfSAPPorMatriz[i].getDscOfertaSAP());
				jsonArrayOfertaSAP.put(jsonResult);
			}
			
			String result = new JSONObject().put("arrayOfertas", jsonArrayOfertaSAP).toString();
			writer.write(result);
			writer.flush();
		} catch (JSONException e) {	
			logger.error("error ao criar o objeto JSON de OfertaSAP.");
		} catch (IOException e) {
			logger.error("error ao criar o stream de OfertaSAP.");
		} finally {
			writer.close();
		}
		return null;
	}
	
	private Double getMonValue(String arg) throws CatalogoPRSException{
		try {
			return Double.parseDouble(arg);
		} catch (NumberFormatException e) {
			throw new CatalogoPRSException("Pre&ccedil;o de item inv&aacute;lido", e);
		}
	}

    public Date getDataVigencia() {
		return dataVigencia;
	}
	public void setDataVigencia(Date dataVigencia) {
		this.dataVigencia = dataVigencia;
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