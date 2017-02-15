package br.com.vivo.catalogoPRS.pageflows.param.programaPontos.produtos;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.axis.AxisFault;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.vivo.catalogoPRS.bo.UserCatalogo;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.ParametrizacaoProdutosPPForm;
import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;
import br.com.vivo.catalogoPRS.ws.catalogoCanalAtendimento.sn.CanalAtendimentoPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoCanalAtendimento.sn.ParametroBuscarCanalAtendimento;
import br.com.vivo.catalogoPRS.ws.catalogoCanalAtendimento.sn.ResultadoBuscarCanalAtendimentoCanalAtendimento;
import br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn.BuscarListaFabricantePorTipoProdutoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn.BuscarListaFabricantePorTipoProdutoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn.FabricantePortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn.ParametrosBuscarListaFabricantePorTipoProduto;
import br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn.ResultadoBuscarListaFabricantePorTipoProdutoFabricante;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarListaModeloPorTipoProdutoFabricanteRequest;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.BuscarListaModeloPorTipoProdutoFabricanteResponse;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.Modelo;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ModeloPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaModeloPorTipoProdutoFabricante;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaModeloPorTipoProdutoFabricanteRaizModeloIn;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.TipoProduto;
import br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.BuscarListaOrgVendasComDDDRequest;
import br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.BuscarListaOrgVendasComDDDResponse;
import br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.OrganizacaoVendasPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoOrganizacaoVendas.sn.ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda;
import br.com.vivo.catalogoPRS.ws.catalogoPontos.sn.ParametrosBuscarAcaoMarketing;
import br.com.vivo.catalogoPRS.ws.catalogoPontos.sn.PontosPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoPontos.sn.ResultadoBuscarAcaoMarketing;
import br.com.vivo.catalogoPRS.ws.catalogoPontos.sn.ResultadoBuscarAcaoMarketingAcoesIncentivoAcaoIncentivo;
import br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.AlterarDispProdutoPPRequest;
import br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarConfigProdutoPPRequest;
import br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarConfigProdutoPPResponse;
import br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarListaPrecoProdutoPPRequest;
import br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.BuscarListaPrecoProdutoPPResponse;
import br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.CarregarDispProdutoPPRequest;
import br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.PaginacaoIn;
import br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.PaginacaoOut;
import br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ParamAlterarDispProdutoPP;
import br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ParamAlterarDispProdutoPPInDisponivel;
import br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ParamBuscarConfigProdutoPP;
import br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ParamBuscarPrecoProdutoPP;
import br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ParamCarregarDispProdutoPP;
import br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ProgramaPontosPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ResultBuscarPrecoProdutoPPListaProdutoProduto;
import br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.BuscarListaTipoProdutoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.BuscarListaTipoProdutoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn.TipoProdutoPortTypeProxy;
import edu.emory.mathcs.backport.java.util.Arrays;

public class ParametrizacaoProdutosPPAction extends BaseMappingDispatchAction implements Serializable{
	
	private static Logger logger = Logger.getLogger(ParametrizacaoProdutosPPAction.class);
	
	private static final long serialVersionUID = 1L;
	
	private Long idTipoProdutoToModelo;
	
	List<ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda> orgVendasLista;
	
	List<ResultBuscarPrecoProdutoPPListaProdutoProduto> produtoListaOrgVenda;
	
	public List<ResultBuscarPrecoProdutoPPListaProdutoProduto> getProdutoListaOrgVenda() {
		return produtoListaOrgVenda;
	}

	public void setProdutoListaOrgVenda(List<ResultBuscarPrecoProdutoPPListaProdutoProduto> produtoListaOrgVenda) {
		this.produtoListaOrgVenda = produtoListaOrgVenda;
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ParametrizacaoProdutosPPForm parametrizacaoProdutosPPForm = (ParametrizacaoProdutosPPForm) form;
		
		TipoProdutoPortTypeProxy tipoProdutoPortTypeProxy = new TipoProdutoPortTypeProxy();
		OrganizacaoVendasPortTypeProxy organizacaoVendasPortTypeProxy = new OrganizacaoVendasPortTypeProxy();
		
		BuscarListaTipoProdutoRequest buscarListaTipoProdutoRequest = new BuscarListaTipoProdutoRequest();
		BuscarListaTipoProdutoResponse buscarListaTipoProdutoResponse = new BuscarListaTipoProdutoResponse();
		try {
			buscarListaTipoProdutoResponse = tipoProdutoPortTypeProxy.buscarListaTipoProduto(buscarListaTipoProdutoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, ParametrizacaoProdutosPPAction.class.getName(), ex.getMessage(), parametrizacaoProdutosPPForm, response );
			return null;
		}
		List<TipoProduto> tipoProdutoLista = Arrays.asList(buscarListaTipoProdutoResponse.getResultadoListarTipoProduto());
		
		BuscarListaOrgVendasComDDDRequest buscarListaOrgVendasComDDDRequest = new BuscarListaOrgVendasComDDDRequest();
		BuscarListaOrgVendasComDDDResponse buscarListaOrgVendasComDDDResponse = new BuscarListaOrgVendasComDDDResponse();
		try {
			buscarListaOrgVendasComDDDResponse = organizacaoVendasPortTypeProxy.buscarListaOrgVendasComDDD(buscarListaOrgVendasComDDDRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, ParametrizacaoProdutosPPAction.class.getName(), ex.getMessage(), parametrizacaoProdutosPPForm, response );
			return null;
		}
		orgVendasLista = Arrays.asList(buscarListaOrgVendasComDDDResponse.getResultadoBuscarListaOrgVendasComDDD().getListaOrganizacaoVendaComDDD());
		
		
		ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda orgVendasTotal = orgVendasLista.get(0);
		orgVendasLista.set(0, orgVendasTotal);
		orgVendasLista.get(0).setIdOrganizacaoVendas((long) 99);
		orgVendasLista.get(0).setSgOrganizacaoVendas("TODAS");
		
		request.setAttribute("tipoProdutoLista", tipoProdutoLista);
		request.setAttribute("orgVendasLista", orgVendasLista);
		
		this.cleanHeader(response);
		return mapping.findForward("success");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward pesquisarProdutosPP(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		ParametrizacaoProdutosPPForm parametrizacaoProdutosPPForm = (ParametrizacaoProdutosPPForm) form;
		
		BuscarListaPrecoProdutoPPRequest buscarListaPrecoProdutoPPRequest = new BuscarListaPrecoProdutoPPRequest();
		ParamBuscarPrecoProdutoPP paramBuscarPrecoProdutoPP = new ParamBuscarPrecoProdutoPP();
		paramBuscarPrecoProdutoPP.setIdTipoProduto(parametrizacaoProdutosPPForm.getIdTipoProduto());
		paramBuscarPrecoProdutoPP.setIdFabricante(parametrizacaoProdutosPPForm.getIdFabricante());
		paramBuscarPrecoProdutoPP.setIdModelo(parametrizacaoProdutosPPForm.getIdModelo());
		paramBuscarPrecoProdutoPP.setIdOrgVendas(parametrizacaoProdutosPPForm.getIdOrgVendas());
		
		buscarListaPrecoProdutoPPRequest.setParamBuscarPrecoProdutoPP(paramBuscarPrecoProdutoPP);
		
		if(parametrizacaoProdutosPPForm.getIdOrgVendas() != 99){
			PaginacaoIn paginacaoIn = new PaginacaoIn();
			paginacaoIn.setPaginaSolicitada(parametrizacaoProdutosPPForm.getPaginaSolicitada());
			paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		}
		
		ProgramaPontosPortTypeProxy programaPontosPortTypeProxy = new ProgramaPontosPortTypeProxy();
		BuscarListaPrecoProdutoPPResponse buscarListaPrecoProdutoPPResponse = new BuscarListaPrecoProdutoPPResponse();
		try {
			buscarListaPrecoProdutoPPResponse = programaPontosPortTypeProxy.buscarListaPrecoProdutoPP(buscarListaPrecoProdutoPPRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, ParametrizacaoProdutosPPAction.class.getName(), ex.getMessage(), parametrizacaoProdutosPPForm, response );
			return null;
		}
		List<ResultBuscarPrecoProdutoPPListaProdutoProduto> produtoLista = Arrays.asList(buscarListaPrecoProdutoPPResponse.getResultBuscarPrecoProdutoPP().getListaProduto());
		
		PaginacaoOut paginacaoOut = buscarListaPrecoProdutoPPResponse.getResultBuscarPrecoProdutoPP().getPaginacaoOut();
		
		if(parametrizacaoProdutosPPForm.getIdOrgVendas() == 99){
			
			List<ResultBuscarPrecoProdutoPPListaProdutoProduto> produtoSet = new ArrayList<ResultBuscarPrecoProdutoPPListaProdutoProduto>();
			boolean insereProduto;
			
			produtoSet.add(produtoLista.get(0));
			
			/*ResultBuscarPrecoProdutoPPListaProdutoProduto produtoTotal = produtoLista.get(0);
			produtoSet.add(0, produtoTotal);*/
			
			for(ResultBuscarPrecoProdutoPPListaProdutoProduto pLista : produtoLista){
				
				insereProduto = true;
				
				for(ResultBuscarPrecoProdutoPPListaProdutoProduto pSet : produtoSet){
					if (pLista.getIdProduto() == pSet.getIdProduto()) {
						
						if (pLista.getInDisponivel().equals("S") && pSet.getInDisponivel().equals("N")) {
							produtoSet.remove(pSet);
							break;
						} else {
							insereProduto = false;
							break;
						}
					}
				}
				if (insereProduto) {
					produtoSet.add(pLista);
				}
			}
			
			paginacaoOut.setTotalRegistros(produtoSet.size());
			tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(), request);
			request.setAttribute("produtoLista", produtoSet);
			parametrizacaoProdutosPPForm.setProdutoLista(produtoSet);
		}else{
			tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(), request);
			request.setAttribute("produtoLista", produtoLista);
			parametrizacaoProdutosPPForm.setProdutoLista(produtoLista);
		}
		
		if (orgVendasLista != null && !orgVendasLista.isEmpty()) {
			for (ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda orgVenda : orgVendasLista) {
				
				if (orgVenda.getIdOrganizacaoVendas().equals(parametrizacaoProdutosPPForm.getIdOrgVendas())) {
					request.setAttribute("orgVendas", orgVenda.getSgOrganizacaoVendas());
					request.setAttribute("paramIdOrgVendas", parametrizacaoProdutosPPForm.getIdOrgVendas());
					break;
				}
			}
		}
		setProdutoListaOrgVenda(produtoLista);
		
		return mapping.findForward("success");
	}
	
	@SuppressWarnings({ "unchecked", "static-access" })
	public ActionForward atualizarStatusProdutosPP(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		ParametrizacaoProdutosPPForm parametrizacaoProdutosPPForm = (ParametrizacaoProdutosPPForm) form;
		
		//logger.debug("Opcao selecionada: " + form.getOpcaoSelect());
		
		//for (String number : form.getIdsProdutosPP()) {
		//	logger.debug("Valores selecionados: " + number);
		//}
		
		//logger.debug("ID Organização Vendas: " + this.getRequest().getParameter("paramIdOrgVendas"));
		
		PontosPortTypeProxy pontosPortTypeProxy = new PontosPortTypeProxy();
		ParametrosBuscarAcaoMarketing buscarAcaoMarketingRequest = new ParametrosBuscarAcaoMarketing();
		
		ResultadoBuscarAcaoMarketing buscarAcaoMarketingResponse = new ResultadoBuscarAcaoMarketing();
		try {
			buscarAcaoMarketingResponse = pontosPortTypeProxy.buscarAcaoMarketing(buscarAcaoMarketingRequest, this.getUserName(), this.getPassword());		
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, ParametrizacaoProdutosPPAction.class.getName(), ex.getMessage(), parametrizacaoProdutosPPForm, response );
			return null;
		}
		List<ResultadoBuscarAcaoMarketingAcoesIncentivoAcaoIncentivo> acaoLista = Arrays.asList(buscarAcaoMarketingResponse.getAcoesIncentivo());
		
		AlterarDispProdutoPPRequest alterarDispProdutoPPRequest = new AlterarDispProdutoPPRequest();
		ParamAlterarDispProdutoPP paramAlterarDispProdutoPP = new ParamAlterarDispProdutoPP();
		String listaIdProduto = null;
				
		for (String number : parametrizacaoProdutosPPForm.getIdsProdutosPP()) {
			listaIdProduto = number;
		}
		String[] listaIdProdutoArray = {listaIdProduto};
		paramAlterarDispProdutoPP.setListaIdProduto(listaIdProdutoArray);
		
		ParamAlterarDispProdutoPPInDisponivel inDisponivel = null;
		String opcaoSelect = parametrizacaoProdutosPPForm.getOpcaoSelect();
		if (opcaoSelect.equals("s") || opcaoSelect.equals("S")) {
			paramAlterarDispProdutoPP.setInDisponivel(inDisponivel.S);
		} else {
			paramAlterarDispProdutoPP.setInDisponivel(inDisponivel.N);
		}
		
		paramAlterarDispProdutoPP.setIdOrgVendas(Long.valueOf(request.getParameter("paramIdOrgVendas")) );
		
		String[] acoesConfig = new String[acaoLista.size()];
		for ( int i = 0; i < acaoLista.size(); ++i ) {
			acoesConfig[i] = acaoLista.get(i).getSigla();
		}
		
		paramAlterarDispProdutoPP.setListaSgAcao(acoesConfig);
		
		UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
		paramAlterarDispProdutoPP.setNmUsuario(userCatalogo.getUser().getUsername());
		
		alterarDispProdutoPPRequest.setParamAlterarDispProdutoPP(paramAlterarDispProdutoPP);
		
		ProgramaPontosPortTypeProxy programaPontosPortTypeProxy = new ProgramaPontosPortTypeProxy();
		try {
			programaPontosPortTypeProxy.alterarDispProdutoPP(alterarDispProdutoPPRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, ParametrizacaoProdutosPPAction.class.getName(), ex.getMessage(), parametrizacaoProdutosPPForm, response );
			return null;
		}
		
		return null;
	}
	
	public ActionForward popupDispPrecoOrgVendas(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		request.setAttribute("produtoListaOrgVenda", getProdutoListaOrgVenda());
		
		forwardParameter(request, "id_produto");
		
		//for (Produto p : getProdutoListaOrgVenda()) {
		//	if (p.getIdProduto() == Long.valueOf(this.getRequest().getParameter("id_produto"))) {
		//		logger.debug(p.getIdProduto() + "-" + p.getSgOrgVendas());
		//	}
		//}
		
		return mapping.findForward("success");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward disponibilidadeAcaoCanal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		ParametrizacaoProdutosPPForm parametrizacaoProdutosPPForm = (ParametrizacaoProdutosPPForm) form;
		
		forwardParameter(request, "id_produto");
		forwardParameter(request, "nm_produto");
		
		BuscarConfigProdutoPPRequest buscarConfigProdutoPPRequest = new BuscarConfigProdutoPPRequest();
		ParamBuscarConfigProdutoPP paramBuscarConfigProdutoPP = new ParamBuscarConfigProdutoPP();
		
		paramBuscarConfigProdutoPP.setIdProduto(Long.valueOf(request.getParameter("id_produto")));
		
		buscarConfigProdutoPPRequest.setParamBuscarConfigProdutoPP(paramBuscarConfigProdutoPP);
		
		ProgramaPontosPortTypeProxy programaPontosPortTypeProxy = new ProgramaPontosPortTypeProxy();
		BuscarConfigProdutoPPResponse buscarConfigProdutoPPResponse = new BuscarConfigProdutoPPResponse();
		try {
			buscarConfigProdutoPPResponse = programaPontosPortTypeProxy.buscarConfigProdutoPP(buscarConfigProdutoPPRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, ParametrizacaoProdutosPPAction.class.getName(), ex.getMessage(), parametrizacaoProdutosPPForm, response );
			return null;
		}
		
		String[] listaSgAcao = buscarConfigProdutoPPResponse.getResultBuscarConfigProdutoPP().getListaSgAcao();
		long[] listaIdOrgVendas = buscarConfigProdutoPPResponse.getResultBuscarConfigProdutoPP().getListaIdOrgVendas();
		long[] listaIdCanal = buscarConfigProdutoPPResponse.getResultBuscarConfigProdutoPP().getListaIdCanal();
		
		Long[] listaIdOrgVendasLongObj = ArrayUtils.toObject(listaIdOrgVendas);
		Long[] listaIdCanalLongObj = ArrayUtils.toObject(listaIdCanal);
		
		List<String> sgAcaoConfigLista = Arrays.asList(listaSgAcao);
		List<Long> idOrgVendasConfigLista = Arrays.asList(listaIdOrgVendasLongObj);
		List<Long> idCanalConfigLista = Arrays.asList(listaIdCanalLongObj);
		
		PontosPortTypeProxy pontosPortTypeProxy = new PontosPortTypeProxy();
		ParametrosBuscarAcaoMarketing buscarAcaoMarketingRequest = new ParametrosBuscarAcaoMarketing();
		ResultadoBuscarAcaoMarketing buscarAcaoMarketingResponse = new ResultadoBuscarAcaoMarketing();
		try {
			buscarAcaoMarketingResponse = pontosPortTypeProxy.buscarAcaoMarketing(buscarAcaoMarketingRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, ParametrizacaoProdutosPPAction.class.getName(), ex.getMessage(), parametrizacaoProdutosPPForm, response );
			return null;
		}
		List<ResultadoBuscarAcaoMarketingAcoesIncentivoAcaoIncentivo> acaoLista = Arrays.asList(buscarAcaoMarketingResponse.getAcoesIncentivo());
		
		//logger.debug(acaoLista.get(0).getDescricao());
		
		List<ResultadoBuscarAcaoMarketingAcoesIncentivoAcaoIncentivo> acaoDisp = new ArrayList<ResultadoBuscarAcaoMarketingAcoesIncentivoAcaoIncentivo>();
		List<ResultadoBuscarAcaoMarketingAcoesIncentivoAcaoIncentivo> acaoConfig = new ArrayList<ResultadoBuscarAcaoMarketingAcoesIncentivoAcaoIncentivo>();
		boolean insereConfig = false;
		
		for (ResultadoBuscarAcaoMarketingAcoesIncentivoAcaoIncentivo acao : acaoLista) {
			
			for (String sgAcao : sgAcaoConfigLista) {
			
				if (acao.getSigla().equals(sgAcao)) {
					insereConfig = true;
					break;
				} else {
					insereConfig = false;
				}
			}
			
			if (insereConfig) {
				acaoConfig.add(acao);
			} else {
				acaoDisp.add(acao);
			}			
		}
		
		request.setAttribute("acaoDisp", acaoDisp);
		request.setAttribute("acaoConfig", acaoConfig);
		
		List<ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda> orgVendasDisp = new ArrayList<ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda>();
		List<ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda> orgVendasConfig = new ArrayList<ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda>();
		insereConfig = false;

		for (ResultadoBuscarListaOrgVendasComDDDListaOrganizacaoVendaComDDDOrganizacaoVenda ov : orgVendasLista) {
			
			for (Long idOrg : idOrgVendasConfigLista) {
			
				if (ov.getIdOrganizacaoVendas().equals(idOrg)) {
					insereConfig = true;
					break;
				} else {
					insereConfig = false;
				}
			}
			
			if (ov.getIdOrganizacaoVendas() != 99) {
				if (insereConfig) {
					orgVendasConfig.add(ov);
				} else {
					orgVendasDisp.add(ov);
				}
			}
		}
		
		//if (orgVendasDisp.isEmpty()) {
		//	logger.debug("Organizações Disponiveis é null");
		//	orgVendasDisp = null;
		//} else {
		//	if (orgVendasConfig.isEmpty()) {
		//		logger.debug("Organizações Configuradas é null ");
		//		orgVendasConfig = null;
		//	}
		//}
		
		request.setAttribute("orgVendasDisp", orgVendasDisp);
		request.setAttribute("orgVendasConfig", orgVendasConfig);
		
		//if (! orgVendasDisp.isEmpty()) {
		//	
		//	logger.debug("Organizações Disponiveis: ");
		//	for (OrganizacaoVenda ov : orgVendasDisp) {
		//		logger.debug(ov.getSgOrganizacaoVendas());
		//	}
		//	
		//	System.out.println();
		//	
		//}
		//
		//if (! orgVendasConfig.isEmpty() ) {
		//	logger.debug("Organizações Configuradas: ");
		//	for (OrganizacaoVenda ov : orgVendasConfig) {
		//		logger.debug(ov.getSgOrganizacaoVendas());
		//	}
		//}
		
		CanalAtendimentoPortTypeProxy canalAtendimentoPortTypeProxy = new CanalAtendimentoPortTypeProxy();
		ParametroBuscarCanalAtendimento buscarCanalAtendimentoRequest = new ParametroBuscarCanalAtendimento();
		
		buscarCanalAtendimentoRequest.setInVigencia((long) 1);
		
		ResultadoBuscarCanalAtendimentoCanalAtendimento[] buscarCanalAtendimentoResponse = null;
		try {
			buscarCanalAtendimentoResponse = canalAtendimentoPortTypeProxy.buscarCanalAtendimento(buscarCanalAtendimentoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, ParametrizacaoProdutosPPAction.class.getName(), ex.getMessage(), parametrizacaoProdutosPPForm, response );
			return null;
		}
		List<ResultadoBuscarCanalAtendimentoCanalAtendimento> canalLista = Arrays.asList(buscarCanalAtendimentoResponse);
		
		List<ResultadoBuscarCanalAtendimentoCanalAtendimento> canalAtendDisp = new ArrayList<ResultadoBuscarCanalAtendimentoCanalAtendimento>();
		List<ResultadoBuscarCanalAtendimentoCanalAtendimento> canalAtendConfig = new ArrayList<ResultadoBuscarCanalAtendimentoCanalAtendimento>();
		insereConfig = false;

		for (ResultadoBuscarCanalAtendimentoCanalAtendimento ca : canalLista) {
			
			for (Long idCanalAtend : idCanalConfigLista) {
				
				if (ca.getIdCanal() == idCanalAtend) {
					insereConfig = true;
					break;
				} else {
					insereConfig = false;
				}
			}
			
			if (insereConfig) {
				canalAtendConfig.add(ca);
			} else {
				canalAtendDisp.add(ca);
			}
		}
		
		request.setAttribute("canalAtendDisp", canalAtendDisp);
		request.setAttribute("canalAtendConfig", canalAtendConfig);
		
		return mapping.findForward("success");
	}
	
	public ActionForward parametrizarDispAcaoCanal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		ParametrizacaoProdutosPPForm parametrizacaoProdutosPPForm = (ParametrizacaoProdutosPPForm) form;
		
		//logger.debug(this.getRequest().getParameter("id_produto"));
		
		CarregarDispProdutoPPRequest carregarDispProdutoPPRequest = new CarregarDispProdutoPPRequest();
		ParamCarregarDispProdutoPP paramCarregarDispProdutoPP = new ParamCarregarDispProdutoPP();
		
		paramCarregarDispProdutoPP.setIdProduto(Long.valueOf(request.getParameter("id_produto")));
		
		if((parametrizacaoProdutosPPForm.getIdsCanaisAtend() != null) && (parametrizacaoProdutosPPForm.getIdsOrgVendas() != null) && (parametrizacaoProdutosPPForm.getSgsAcoes() != null)){
			
			paramCarregarDispProdutoPP.setListaAcao(parametrizacaoProdutosPPForm.getSgsAcoes());
			paramCarregarDispProdutoPP.setListaOrgVendas(parametrizacaoProdutosPPForm.getIdsOrgVendas());
			paramCarregarDispProdutoPP.setListaCanalAtendimento(parametrizacaoProdutosPPForm.getIdsCanaisAtend());
		}
		
		UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
		paramCarregarDispProdutoPP.setNmUsuario(userCatalogo.getUser().getUsername());
		
		carregarDispProdutoPPRequest.setParamCarregarDispProdutoPP(paramCarregarDispProdutoPP);
		
		ProgramaPontosPortTypeProxy programaPontosPortTypeProxy = new ProgramaPontosPortTypeProxy();
		try {
			programaPontosPortTypeProxy.carregarDispProdutoPP(carregarDispProdutoPPRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, ParametrizacaoProdutosPPAction.class.getName(), ex.getMessage(), parametrizacaoProdutosPPForm, response );
			return null;
		}
		
		return null;
	}

	public ActionForward buscarListaFabricantePorTipoProduto(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		PrintWriter out = null;
		String idTipoProduto = request.getParameter("idSelectOrigem");
		BuscarListaFabricantePorTipoProdutoRequest buscarListaFabricantePorTipoProdutoRequest = new BuscarListaFabricantePorTipoProdutoRequest();
		ParametrosBuscarListaFabricantePorTipoProduto parametrosBuscarListaFabricantePorTipoProduto = new ParametrosBuscarListaFabricantePorTipoProduto();
		
		if(idTipoProduto != null && !idTipoProduto.equals("")) {
			parametrosBuscarListaFabricantePorTipoProduto.setIdTipoProduto(Long.valueOf(idTipoProduto));
			idTipoProdutoToModelo = Long.valueOf(idTipoProduto);
		}
		buscarListaFabricantePorTipoProdutoRequest.setParametrosBuscarListaFabricantePorTipoProduto(parametrosBuscarListaFabricantePorTipoProduto);
		
		FabricantePortTypeProxy fabricantePortTypeProxy = new FabricantePortTypeProxy();
		BuscarListaFabricantePorTipoProdutoResponse buscarListaFabricantePorTipoProdutoResponse = new BuscarListaFabricantePorTipoProdutoResponse();
		try {
			buscarListaFabricantePorTipoProdutoResponse = fabricantePortTypeProxy.buscarListaFabricantePorTipoProduto(buscarListaFabricantePorTipoProdutoRequest, this.getUserName(), this.getPassword());
			
			ResultadoBuscarListaFabricantePorTipoProdutoFabricante[] fabricanteLista = null;
			fabricanteLista = buscarListaFabricantePorTipoProdutoResponse.getResultadoBuscarListaFabricantePorTipoProduto();
			try {
				out = response.getWriter();
				JSONArray jsonArrayFabricante = new JSONArray();
				for (ResultadoBuscarListaFabricantePorTipoProdutoFabricante fabricante : fabricanteLista) {
					JSONObject jsonResult = new JSONObject();
					jsonResult.put("id", fabricante.getIdFabricante());
					jsonResult.put("descricao", fabricante.getNmFabricante());
					jsonArrayFabricante.put(jsonResult);
				}
				String result = new JSONObject().put("arrayDestino", jsonArrayFabricante).toString();
				out.write(result);
				out.flush();
			} catch (JSONException e) {
				logger.error("Erro ao criar o objeto JSON de Fabricante.");
			} catch (IOException e) {
				logger.error("Error ao criar o Stream de Fabricante.");
			} finally {
				out.close();
			}
		} catch (Exception e) {
			logger.warn("Erro somente capturado quando n&atilde;o existir nenhum Fabricante para o Tipo de Produto informado!");
		}
		
		return null;
	}
	
	public ActionForward buscarListaModeloPorTpProdutoFabricante(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		PrintWriter out = null;
		String idFabricante = request.getParameter("idSelectOrigem");
		BuscarListaModeloPorTipoProdutoFabricanteRequest buscarListaModeloPorTipoProdutoFabricanteRequest = new BuscarListaModeloPorTipoProdutoFabricanteRequest();
		ParametrosBuscarListaModeloPorTipoProdutoFabricante parametrosBuscarListaModeloPorTipoProdutoFabricante = new ParametrosBuscarListaModeloPorTipoProdutoFabricante();
		
		ParametrosBuscarListaModeloPorTipoProdutoFabricanteRaizModeloIn in = new ParametrosBuscarListaModeloPorTipoProdutoFabricanteRaizModeloIn();
		
		Long idTipoProduto = idTipoProdutoToModelo;
		
		if( (idFabricante != null && !idFabricante.equals("")) && (idTipoProduto != null) ) {
			in.setIdTipoProduto(idTipoProduto);
			in.setIdFabricante(Long.valueOf(idFabricante));
		}
		
		parametrosBuscarListaModeloPorTipoProdutoFabricante.setRaizModeloIn(in);
		buscarListaModeloPorTipoProdutoFabricanteRequest.setParametrosBuscarListaModeloPorTipoProdutoFabricante(parametrosBuscarListaModeloPorTipoProdutoFabricante);
		
		ModeloPortTypeProxy modeloPortTypeProxy = new ModeloPortTypeProxy();
		BuscarListaModeloPorTipoProdutoFabricanteResponse buscarListaModeloPorTipoProdutoFabricanteResponse = new BuscarListaModeloPorTipoProdutoFabricanteResponse();
		try {
			buscarListaModeloPorTipoProdutoFabricanteResponse = modeloPortTypeProxy.buscarListaModeloPorTipoProdutoFabricante(buscarListaModeloPorTipoProdutoFabricanteRequest, this.getUserName(), this.getPassword());
			
			Modelo[] modeloLista = null;
			modeloLista = buscarListaModeloPorTipoProdutoFabricanteResponse.getResultadoBuscarListaModeloPorTipoProdutoFabricante();
			try {
				out = response.getWriter();
				JSONArray jsonArrayModelo = new JSONArray();
				for (Modelo modelo : modeloLista) {
					JSONObject jsonResult = new JSONObject();
					jsonResult.put("id", modelo.getIdModelo());
					jsonResult.put("descricao", modelo.getNmModelo());
					jsonArrayModelo.put(jsonResult);
				}
				String result = new JSONObject().put("arrayDestino", jsonArrayModelo).toString();
				out.write(result);
				out.flush();
			} catch (JSONException e) {
				logger.error("Erro ao criar o objeto JSON de Modelo.");
			} catch (IOException e) {
				logger.error("Erro ao criar o Stream de Modelo.");
			} finally {
				out.close();
			}
		} catch (Exception e) {
			logger.warn("Erro somente capturado quando n&atilde;o existir nenhum Modelo para o Tipo de Produto e Fabricante informado!");
		}
		
		return null;
	}

	@Override
	protected void onDestroy(HttpSession session) {
	}
	
	public ActionForward popupConfirmacaoCancel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		return mapping.findForward("success");
	}
	
	public ActionForward popupConfirmacaoGravarInd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		return mapping.findForward("success");
	}
	
	public ActionForward popupConfirmacaoGravar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		return mapping.findForward("success");
	}

}