package br.com.vivo.catalogoPRS.pageflows.param.produtos.formasCondicoesPagamento;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.axis.AxisFault;
import org.apache.commons.lang.ArrayUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*import weblogic.utils.ArrayUtils;*/
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.FormasCondicoesPagamentoForm;
import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;
import br.com.vivo.catalogoPRS.ws.catalogoCanal.sn.BuscarListaCanalRequest;
import br.com.vivo.catalogoPRS.ws.catalogoCanal.sn.BuscarListaCanalResponse;
import br.com.vivo.catalogoPRS.ws.catalogoCanal.sn.CanalPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoCanal.sn.ResultadoListaCanalCanal;
import br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.AlterarFormaCondPagtoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.BuscarListaFormaCondPagtoPorTipoProdutoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.BuscarListaFormaCondPagtoPorTipoProdutoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.BuscarListaFormaCondPagtoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.BuscarListaFormaCondPagtoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.CondicaoPagamento;
import br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.CondicaoPagamentoSelecionada;
import br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.FormaPagamentoPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.ListaTipoProdutoCondPagtExclusaoTipoProdutoCondPagtoExclusao;
import br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.ListaTipoProdutoCondPagtoCriacaoTipoProdutoCondPagtoCriacao;
import br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.ListaVlMininoParcelaTipoProdutoPagtCriacaoTipoProdutoFormaPagamentoVlMinimoParcelaCriacao;
import br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.ListaVlMininoParcelaTipoProdutoPagtExclusaoInTipoProdutoFormaPagamentoVlMinimoParcelaExclusaoIn;
import br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.ParametrosAlterarFormaCondPagto;
import br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.ParametrosBuscarListaFormaCondPagto;
import br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.ParametrosBuscarListaFormaCondPagtoPorTipoProduto;
import br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn.ResultadoBuscarListaFormaCondPagtoFormaPagamento;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.BuscarListaPlataformaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.BuscarListaPlataformaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.PlataformaPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.ResultadoBuscarListaPlataformaPlataforma;
public class FormasCondicoesPagamentoAction extends BaseMappingDispatchAction implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		FormasCondicoesPagamentoForm formasCondicoesPagamentoForm = (FormasCondicoesPagamentoForm) form;
		
		PlataformaPortTypeProxy plataformaPortTypeProxy = new PlataformaPortTypeProxy();
		BuscarListaPlataformaRequest buscarListaPlataformaRequest = new BuscarListaPlataformaRequest();
		BuscarListaPlataformaResponse buscarListaPlataformaResponse = new BuscarListaPlataformaResponse();
		
		try {
			buscarListaPlataformaResponse = plataformaPortTypeProxy.buscarListaPlataforma(buscarListaPlataformaRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, FormasCondicoesPagamentoAction.class.getName(), ex.getMessage(), formasCondicoesPagamentoForm, response );
			return null;
		}
		
		List<ResultadoBuscarListaPlataformaPlataforma> plataformaList = Arrays.asList(buscarListaPlataformaResponse.getResultadoBuscarListaPlataforma());
 		request.setAttribute("plataformas", plataformaList);
		
 		CanalPortTypeProxy canalPortTypeProxy = new CanalPortTypeProxy();
 		BuscarListaCanalRequest buscarListaCanalRequest = new BuscarListaCanalRequest();
 		BuscarListaCanalResponse buscarListaCanalResponse = new BuscarListaCanalResponse();
 		
 		try {
 			buscarListaCanalResponse = canalPortTypeProxy.buscarListaCanal(buscarListaCanalRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, FormasCondicoesPagamentoAction.class.getName(), ex.getMessage(), formasCondicoesPagamentoForm, response );
			return null;
		}
 		List<ResultadoListaCanalCanal> canalList = Arrays.asList(buscarListaCanalResponse.getResultadoListaCanal());
		
		List<String> listaTipoProduto = new ArrayList<String>(); 
		String[] tpProdutoSplit = ApplicationConfiguration.getDescricaoTipoProdutoPopup().split(";");
		for (String descricao : tpProdutoSplit) {
			listaTipoProduto.add(descricao);
		}
 		
 		request.setAttribute("desc_tipo_produto", ApplicationConfiguration.getDescricaoTipoProdutoCombo());
		request.setAttribute("ids_tipo_produto", ApplicationConfiguration.getIdsTipoProdutoCombo());
		request.setAttribute("popup_tp_produto", listaTipoProduto);
 		request.setAttribute("canais", canalList);
		
		this.cleanHeader(response);
		return mapping.findForward("success");
	}

	public ActionForward pesquisar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws CatalogoPRSException, Exception {
		
		FormasCondicoesPagamentoForm formasCondicoesPagamentoForm = (FormasCondicoesPagamentoForm) form;

		String[] idsTiposProdutos = formasCondicoesPagamentoForm.getIdsTiposProduto().split(";");

		BuscarListaFormaCondPagtoRequest buscarListaFormaCondPagtoRequest = new BuscarListaFormaCondPagtoRequest();
		ParametrosBuscarListaFormaCondPagto parametrosBuscarListaFormaCondPagto = new ParametrosBuscarListaFormaCondPagto();
		
		parametrosBuscarListaFormaCondPagto.setIdCanal(formasCondicoesPagamentoForm.getIdCanal());
		
		if(formasCondicoesPagamentoForm.getIdPlataforma() != null || formasCondicoesPagamentoForm.getIdPlataforma() != 0){
			parametrosBuscarListaFormaCondPagto.setIdPlataforma(formasCondicoesPagamentoForm.getIdPlataforma());
		}
		
		long [] tpProdutos = new long [idsTiposProdutos.length];
		for(int i=0; i<idsTiposProdutos.length; i++){
			tpProdutos[i] = Long.parseLong(idsTiposProdutos[i]);
		}
		
		parametrosBuscarListaFormaCondPagto.setListaTipoProduto(tpProdutos);
		
		if(formasCondicoesPagamentoForm.getIdPlataforma() != null || formasCondicoesPagamentoForm.getIdPlataforma() != 0){
			request.setAttribute("id_plataforma", formasCondicoesPagamentoForm.getIdPlataforma());
			request.setAttribute("id_canal", formasCondicoesPagamentoForm.getIdCanal());
			request.setAttribute("ids_tipos_produto", formasCondicoesPagamentoForm.getIdsTiposProduto());
		}
		
		buscarListaFormaCondPagtoRequest.setParametrosBuscarListaFormaCondPagto(parametrosBuscarListaFormaCondPagto);
		
		FormaPagamentoPortTypeProxy formaPagamentoPortTypeProxy = new FormaPagamentoPortTypeProxy();
		BuscarListaFormaCondPagtoResponse buscarListaFormaCondPagtoResponse = new BuscarListaFormaCondPagtoResponse();
		try {
			buscarListaFormaCondPagtoResponse = formaPagamentoPortTypeProxy.buscarListaFormaCondPagto(buscarListaFormaCondPagtoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, FormasCondicoesPagamentoAction.class.getName(), ex.getMessage(), formasCondicoesPagamentoForm, response );
			return null;
		}
		
		List<ResultadoBuscarListaFormaCondPagtoFormaPagamento> formaPagamentoList = null;
		if(buscarListaFormaCondPagtoResponse.getResultadoBuscarListaFormaCondPagto() != null){
			formaPagamentoList = Arrays.asList(buscarListaFormaCondPagtoResponse.getResultadoBuscarListaFormaCondPagto());
		}
		
		if(formaPagamentoList != null){
			request.setAttribute("formas_condicoes_pagamento", formaPagamentoList);
			formasCondicoesPagamentoForm.setFormaPagamentoList(formaPagamentoList);
			
			JSONArray formasSelecionadas = new JSONArray();
			JSONObject todasFormasECondicoes = new JSONObject();
			for (ResultadoBuscarListaFormaCondPagtoFormaPagamento formaPagamento : formaPagamentoList) {
				List<CondicaoPagamentoSelecionada> listaCondicaoPagamentoSelecionada = Arrays.asList(formaPagamento.getListaCondicaoPagamentoSelecionada());
				if (listaCondicaoPagamentoSelecionada != null) {
					for(CondicaoPagamentoSelecionada selecionada : listaCondicaoPagamentoSelecionada){
						formasSelecionadas.put(selecionada.getIdTipoProdutoCondPagto());
				   }
				}
				
				CondicaoPagamento[] listaCondicaoPagamento = formaPagamento.getListaCondicaoPagamento();
				JSONArray condicoes = new JSONArray();
				if(listaCondicaoPagamento != null){
					List<CondicaoPagamento> condicaoPagamentoList = Arrays.asList(listaCondicaoPagamento);
					for (CondicaoPagamento condicaoPagamento : condicaoPagamentoList) {
						condicoes.put(condicaoPagamento.getIdCondicaoPagamento());
					}
				}
				try {
					todasFormasECondicoes.put(String.valueOf(formaPagamento.getIdFormaPagamento()), condicoes);
				} catch (JSONException e) {
					throw new CatalogoPRSException("Ocorreu um erro ao listar as formas de pagamento");
				}
			}
			request.setAttribute("formas_condicoes_pagamento_persistidas", formasSelecionadas);
			request.setAttribute("todas_formas_condicoes_pagamento", todasFormasECondicoes);
		}
		
		this.cleanHeader(response);
		return mapping.findForward("success");
	}

	@SuppressWarnings("rawtypes")
	public ActionForward salvar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws CatalogoPRSException, Exception {
		
		FormasCondicoesPagamentoForm formasCondicoesPagamentoForm = (FormasCondicoesPagamentoForm) form;
		
		try {
			
			AlterarFormaCondPagtoRequest alterarFormaCondPagtoRequest = new AlterarFormaCondPagtoRequest();
			
			JSONArray formasCondicoesAntigas = new JSONArray(formasCondicoesPagamentoForm.getFormasCondicoesAntigas());
			
			ListaTipoProdutoCondPagtExclusaoTipoProdutoCondPagtoExclusao[] listaTipoProdutoCondPagtExclusaoArray = new 
					ListaTipoProdutoCondPagtExclusaoTipoProdutoCondPagtoExclusao[formasCondicoesAntigas.length()];
			
			for (int i = 0; i < formasCondicoesAntigas.length(); i++) {
				ListaTipoProdutoCondPagtExclusaoTipoProdutoCondPagtoExclusao listaTipoProdutoCondPagtExclusao = new ListaTipoProdutoCondPagtExclusaoTipoProdutoCondPagtoExclusao();
				listaTipoProdutoCondPagtExclusao.setIdTipoProdutoCondPagto(formasCondicoesAntigas.getLong(i));
				
				listaTipoProdutoCondPagtExclusaoArray[i] = listaTipoProdutoCondPagtExclusao;
			}

			String[] idsTiposProdutos = formasCondicoesPagamentoForm.getIdsTiposProduto().split(";");
			Long idCanal = formasCondicoesPagamentoForm.getIdCanal();
			
			Long idPlataforma = null;
			if (formasCondicoesPagamentoForm.getIdPlataforma() != null){
				idPlataforma = formasCondicoesPagamentoForm.getIdPlataforma();
			}
			
			String[] condicoesSelecionadas = formasCondicoesPagamentoForm.getCondicoesSelecionadas();
			String todasFormasCondicoesBruto = formasCondicoesPagamentoForm.getTodasFormasCondicoes();
			String[] formasSelecionadas = formasCondicoesPagamentoForm.getFormasSelecionadas();

			
			ParametrosAlterarFormaCondPagto parametrosAlterarFormaCondPagto = new ParametrosAlterarFormaCondPagto();
			
			ListaVlMininoParcelaTipoProdutoPagtCriacaoTipoProdutoFormaPagamentoVlMinimoParcelaCriacao[] vlMininoParcelaTipoProdutoPagtCriacao = null;
			if(formasSelecionadas == null){
				vlMininoParcelaTipoProdutoPagtCriacao = new ListaVlMininoParcelaTipoProdutoPagtCriacaoTipoProdutoFormaPagamentoVlMinimoParcelaCriacao[0];	
			}else{
				vlMininoParcelaTipoProdutoPagtCriacao = new ListaVlMininoParcelaTipoProdutoPagtCriacaoTipoProdutoFormaPagamentoVlMinimoParcelaCriacao[formasSelecionadas.length];
			}
			
			ListaTipoProdutoCondPagtoCriacaoTipoProdutoCondPagtoCriacao[] listaTipoProdutoCondPagtoCriacaoArray = null;
			
			List<ListaTipoProdutoCondPagtoCriacaoTipoProdutoCondPagtoCriacao> listaTipoProdutoCondPagtoCriacaoList = 
					new ArrayList<ListaTipoProdutoCondPagtoCriacaoTipoProdutoCondPagtoCriacao>();
			
			JSONObject todasFormasCondicoes = new JSONObject(todasFormasCondicoesBruto);
			if(formasSelecionadas != null){
			for (String idTipoProduto : idsTiposProdutos) {
				int indexFormas = 0;
				
				for (String formaSelecionada : formasSelecionadas) {
					int condPagto = 0;
					ListaVlMininoParcelaTipoProdutoPagtCriacaoTipoProdutoFormaPagamentoVlMinimoParcelaCriacao vlMinimoParcelaCriacao = new ListaVlMininoParcelaTipoProdutoPagtCriacaoTipoProdutoFormaPagamentoVlMinimoParcelaCriacao();
					if(idPlataforma != null){
						vlMinimoParcelaCriacao.setIdPlataforma(idPlataforma);
					}	
						vlMinimoParcelaCriacao.setIdCanal(idCanal);
						vlMinimoParcelaCriacao.setIdFormaPagamento(Long.parseLong(formaSelecionada));
						vlMinimoParcelaCriacao.setIdTipoProduto(Long.parseLong(idTipoProduto));
						Double vlMinimoParcela = Double.parseDouble((formasCondicoesPagamentoForm.getParcelasMinimas()[indexFormas]).replaceAll("\\.", "").replace(',','.'));
						vlMinimoParcelaCriacao.setVlMinimoParcela(vlMinimoParcela);

						vlMininoParcelaTipoProdutoPagtCriacao[indexFormas]= vlMinimoParcelaCriacao;
						
						indexFormas++;
						
						
					JSONArray condicoesDaForma = todasFormasCondicoes.getJSONArray(formaSelecionada);
					for (int i = 0; i < condicoesDaForma.length(); i++) {
						String condicao = condicoesDaForma.getString(i);
						
						ListaTipoProdutoCondPagtoCriacaoTipoProdutoCondPagtoCriacao criacao = new ListaTipoProdutoCondPagtoCriacaoTipoProdutoCondPagtoCriacao();
						criacao.setIdCanal(idCanal);
						
						long condicaoPl = Long.parseLong(condicao);
						criacao.setIdCondicaoPagamento(condicaoPl);
						if (idPlataforma != null){
							criacao.setIdPlataforma(idPlataforma);
						}
						criacao.setIdTipoProduto(Long.valueOf(idTipoProduto));
						
						if(condPagto <= condicoesDaForma.length()){
							listaTipoProdutoCondPagtoCriacaoList.add(criacao);
							condPagto++;
						}
						
						if (ArrayUtils.contains(condicoesSelecionadas, condicao)){
							break;
						}
					}
				}
			 }
			}
			
			listaTipoProdutoCondPagtoCriacaoArray = new ListaTipoProdutoCondPagtoCriacaoTipoProdutoCondPagtoCriacao[listaTipoProdutoCondPagtoCriacaoList.size()];
			for(int i = 0; i<listaTipoProdutoCondPagtoCriacaoList.size(); i++ ){
				listaTipoProdutoCondPagtoCriacaoArray[i] = listaTipoProdutoCondPagtoCriacaoList.get(i);
			}
			
			ListaVlMininoParcelaTipoProdutoPagtExclusaoInTipoProdutoFormaPagamentoVlMinimoParcelaExclusaoIn[] vlMininoParcelaTipoProdutoPagtExclusaoInArray = null;
			List<ListaVlMininoParcelaTipoProdutoPagtExclusaoInTipoProdutoFormaPagamentoVlMinimoParcelaExclusaoIn> vlMininoParcelaTipoProdutoPagtExclusaoInList = 
					new ArrayList<ListaVlMininoParcelaTipoProdutoPagtExclusaoInTipoProdutoFormaPagamentoVlMinimoParcelaExclusaoIn>();
			    
				for(Iterator formasIt = todasFormasCondicoes.keys(); formasIt.hasNext();){
					String forma = (String)formasIt.next();
					for (String idTpProduto : idsTiposProdutos) {
						ListaVlMininoParcelaTipoProdutoPagtExclusaoInTipoProdutoFormaPagamentoVlMinimoParcelaExclusaoIn parcelaExclusaoIn = new ListaVlMininoParcelaTipoProdutoPagtExclusaoInTipoProdutoFormaPagamentoVlMinimoParcelaExclusaoIn();
						parcelaExclusaoIn.setIdCanal(idCanal);
						if (idPlataforma != null){
							parcelaExclusaoIn.setIdPlataforma(idPlataforma);
						}
						parcelaExclusaoIn.setIdFormaPagamento(Long.parseLong(forma));
						parcelaExclusaoIn.setIdTipoProduto(Long.parseLong(idTpProduto));
						
						vlMininoParcelaTipoProdutoPagtExclusaoInList.add(parcelaExclusaoIn);
						
					}
				}
				
				vlMininoParcelaTipoProdutoPagtExclusaoInArray = new ListaVlMininoParcelaTipoProdutoPagtExclusaoInTipoProdutoFormaPagamentoVlMinimoParcelaExclusaoIn[vlMininoParcelaTipoProdutoPagtExclusaoInList.size()];
				for(int i = 0; i<vlMininoParcelaTipoProdutoPagtExclusaoInList.size(); i++){
					vlMininoParcelaTipoProdutoPagtExclusaoInArray[i] = vlMininoParcelaTipoProdutoPagtExclusaoInList.get(i);
				}
				
				parametrosAlterarFormaCondPagto.setListaTipoProdutoCondPagtExclusao(listaTipoProdutoCondPagtExclusaoArray);
				parametrosAlterarFormaCondPagto.setListaTipoProdutoCondPagtoCriacao(listaTipoProdutoCondPagtoCriacaoArray);
				parametrosAlterarFormaCondPagto.setListaVlMininoParcelaTipoProdutoPagtCriacao(vlMininoParcelaTipoProdutoPagtCriacao);
				parametrosAlterarFormaCondPagto.setListaVlMininoParcelaTipoProdutoPagtExclusaoIn(vlMininoParcelaTipoProdutoPagtExclusaoInArray);
				
				alterarFormaCondPagtoRequest.setParametrosAlterarFormaCondPagto(parametrosAlterarFormaCondPagto);
				
			FormaPagamentoPortTypeProxy formaPagamentoPortTypeProxy = new FormaPagamentoPortTypeProxy();
			
			try {
				formaPagamentoPortTypeProxy.alterarFormaCondPagto(alterarFormaCondPagtoRequest, this.getUserName(), this.getPassword());
			} catch (AxisFault ex) {	
				ex.printStackTrace();
				this.AxisFaultExceptionHandler(ex, FormasCondicoesPagamentoAction.class.getName(), ex.getMessage(), formasCondicoesPagamentoForm, response );
				return null;
			}
		} catch (JSONException e) {
			throw new CatalogoPRSException("Ocorreu um erro ao extrair os dados de formas e condi&ccedil;&otilde;es de pagamento.");
		}

		String reloadScript = "$('pesquisar_formas_condicoes_pagamento').onclick();";

		PrintWriter out;
		
		try {
			response.setContentType("text/javascript");
			out = response.getWriter();
			out.println(reloadScript);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.cleanHeader(response);
		return null;
	}

	public ActionForward exportar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		FormasCondicoesPagamentoForm formasCondicoesPagamentoForm = (FormasCondicoesPagamentoForm) form;

		StringBuffer sb = new StringBuffer();
		sb.append("Tipo de produto;Plataforma;Canal;Forma de pagamento;Condição de pagamento;Valor Minimo Parcela\n");

		BuscarListaFormaCondPagtoPorTipoProdutoRequest buscarListaFormaCondPagtoPorTipoProdutoRequest = new BuscarListaFormaCondPagtoPorTipoProdutoRequest();
		ParametrosBuscarListaFormaCondPagtoPorTipoProduto parametrosBuscarListaFormaCondPagtoPorTipoProduto = new ParametrosBuscarListaFormaCondPagtoPorTipoProduto();
		
		long[] listaTipoProduto = new long[]{1,9,10};
		
		parametrosBuscarListaFormaCondPagtoPorTipoProduto.setListaTipoProduto(listaTipoProduto);
		buscarListaFormaCondPagtoPorTipoProdutoRequest.setParametrosBuscarListaFormaCondPagtoPorTipoProduto(parametrosBuscarListaFormaCondPagtoPorTipoProduto);
		
		DecimalFormat formatter = (DecimalFormat)NumberFormat.getCurrencyInstance(new Locale ("pt", "BR"));
		
		FormaPagamentoPortTypeProxy formaPagamentoPortTypeProxy = new FormaPagamentoPortTypeProxy();
		BuscarListaFormaCondPagtoPorTipoProdutoResponse buscarListaFormaCondPagtoPorTipoProdutoResponse = new BuscarListaFormaCondPagtoPorTipoProdutoResponse();
		try {
			buscarListaFormaCondPagtoPorTipoProdutoResponse = formaPagamentoPortTypeProxy.buscarListaFormaCondPagtoPorTipoProduto(buscarListaFormaCondPagtoPorTipoProdutoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, FormasCondicoesPagamentoAction.class.getName(), ex.getMessage(), formasCondicoesPagamentoForm, response );
			return null;
		}
		ResultadoBuscarListaFormaCondPagtoFormaPagamento[] resultadoBuscarListaFormaCondPagto = buscarListaFormaCondPagtoPorTipoProdutoResponse.getResultadoBuscarListaFormaCondPagto();
		if(resultadoBuscarListaFormaCondPagto != null){
			List<ResultadoBuscarListaFormaCondPagtoFormaPagamento> formaPagamentoList = Arrays.asList(resultadoBuscarListaFormaCondPagto);
			for (ResultadoBuscarListaFormaCondPagtoFormaPagamento formaPagamento : formaPagamentoList) {
				if (formaPagamento.getNmTipoProduto().trim().toLowerCase().indexOf("aparelho") != -1){
					sb.append("APARELHO / CHIP / PLACA / MODEM / SMARTPHONE / NETBOOK");
				}else {
					sb.append(formaPagamento.getNmTipoProduto());
				}
					sb.append(";");
					sb.append(formaPagamento.getNmPlataforma());
					sb.append(";");
					sb.append(formaPagamento.getNmCanal());
					sb.append(";");
					sb.append(formaPagamento.getNmFormaPagamento());
					sb.append(";");
					for(CondicaoPagamentoSelecionada condicaoPagamentoSelecionada : formaPagamento.getListaCondicaoPagamentoSelecionada()){
						sb.append(condicaoPagamentoSelecionada.getNmCondicaoPagamento());
					}
					sb.append(";");
					if(formaPagamento.getVlMinimoParcela() != null){
						sb.append(formatter.format(formaPagamento.getVlMinimoParcela()).replaceFirst("R\\$ ", ""));
					}else{
						sb.append(0);
					}
					sb.append("\n");
			}
		}
		
		response.setContentType("application/vnd.ms-excel;charset=iso-8859-1");
		response.addHeader("Content-Disposition", "attachment;filename=FormaCondPagto.csv");
		response.setCharacterEncoding("ISO-8859-1");
		PrintWriter out = response.getWriter();

		out.write(sb.toString());
		out.flush();

		return null;
	}

	@Override
	protected void onDestroy(HttpSession session) {
	}

}