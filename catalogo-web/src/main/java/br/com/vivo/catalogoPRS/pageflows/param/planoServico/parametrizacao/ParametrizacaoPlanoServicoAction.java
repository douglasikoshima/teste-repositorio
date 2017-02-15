package br.com.vivo.catalogoPRS.pageflows.param.planoServico.parametrizacao;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.AxisFault;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.indracompany.catalogo.datalayer.Categoria;
import com.indracompany.catalogo.datalayer.Regional;
import com.indracompany.catalogo.datalayer.Uf;

import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.ServicoForm;
import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;
import br.com.vivo.catalogoPRS.util.PlataformaEnum;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.AlterarGrupoServicoCatalogoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.AlterarGrupoServicoCatalogoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarListaGrupoServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarListaGrupoServicoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarServicoPorGrupoServicoCatalogoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarServicoPorGrupoServicoCatalogoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.CriarGrupoServicoCatalogoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.CriarGrupoServicoCatalogoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ExcluirListaGrupoServicoCatalogoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.GrupoServicoPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ParametrosAlterarGrupoServicoCatalogo;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ParametrosBuscarListaServicoPorGrupoServicoCatalogo;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ParametrosExcluirListaGrupoServicoCatalogo;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ParametrosListarGrupoServico;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ParametroscriarGrupoServicoCatalogo;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ResultadoBuscarServicoPorGrupoServicoCatalogo;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ResultadoListarGrupoServicoCategoria;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.AlterarPlanoParametrizacaoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarListaPlanoParametrizacaoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarListaPlanoParametrizacaoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarListaPlanoPendValidacaoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarListaPlanoPendValidacaoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarListaTipoPlanoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarListaTipoPlanoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoOut;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametroAlterarPlano;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametroAlterarPlanoAtivaWiFi;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametroAlterarPlanoInDisponibilidadeCatalogo;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarListaPlanoParametrizacao;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarListaPlanoParametrizacaoListaRegionalRegional;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarListaTipoPlano;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PlanoPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoParametrizacao;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoPendValidacao;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlano;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaTipoPlano;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaTipoPlanoListaTipoPlanoTipoPlano;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ValidarListaPlanoPorIdRequest;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.BuscarListaPlataformaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.BuscarListaPlataformaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.PlataformaPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.ResultadoBuscarListaPlataformaPlataforma;
import br.com.vivo.catalogoPRS.ws.catalogoRegional.sn.BuscarListaRegionalComUFRequest;
import br.com.vivo.catalogoPRS.ws.catalogoRegional.sn.ListaRegionalUf;
import br.com.vivo.catalogoPRS.ws.catalogoRegional.sn.RegionalPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.AlterarAtivacaoWiFiRequest;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.AlterarCategoriaListaServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.AlterarCategoriaListaServicoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.AlterarServicoParametrizacaoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaServicoParametrizacaoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaServicoParametrizacaoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaServicoPendValidacaoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaServicoPendValidacaoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaTipoServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaTipoServicoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.PaginacaoIn;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParamAlterarAtivacaoWiFi;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParamAlterarAtivacaoWiFiStatus;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosAlterarCategoriaListaServico;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosAlterarServicoParametrizacao;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosAlterarServicoParametrizacaoAtivaWiFi;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosAlterarServicoParametrizacaoInDisponibilidadeCatalogo;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarListaServicoParametrizacao;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoParametrizacao;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoPendValidacao;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoPendValidacaoListaServicoServico;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaTipoServicoListaTipoServicoRetorno;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ServicoPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ValidarListaServicoPorIdRequest;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFPorIdPlanoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFPorIdPlanoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.ParametrosBuscarListaUfPorIdPlano;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.ParametrosBuscarListaUfPorIdPlanoListaUfPorIdPlano;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.ResultadoBuscarListaUFUF;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.UFPortTypeProxy;
import edu.emory.mathcs.backport.java.util.Arrays;

public class ParametrizacaoPlanoServicoAction extends BaseMappingDispatchAction {
	
	private static final String SUCCESS = "success";
	
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{						
		
		ServicoForm servicoForm = (ServicoForm) form;
		
		//BUSCA LISTA SERVICO
		ServicoPortTypeProxy servicoPortTypeProxy = new ServicoPortTypeProxy();
		BuscarListaServicoPendValidacaoRequest buscarListaServicoPendValidacaoRequest = new BuscarListaServicoPendValidacaoRequest();		
		BuscarListaServicoPendValidacaoResponse buscarListaServicoPendValidacaoResponse = null;
		List<ResultadoBuscarListaServicoPendValidacaoListaServicoServico> servicoList  = new ArrayList<ResultadoBuscarListaServicoPendValidacaoListaServicoServico>();
						
		try {
			buscarListaServicoPendValidacaoResponse = servicoPortTypeProxy.buscarListaServicoPendValidacao(buscarListaServicoPendValidacaoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), ex.getMessage(),
					servicoForm, response);
			return null;
		}
		
		ResultadoBuscarListaServicoPendValidacao resultadoBuscarListaServicoPendValidacao = buscarListaServicoPendValidacaoResponse.getResultadoBuscarListaServicoPendValidacao();
		
		ResultadoBuscarListaServicoPendValidacaoListaServicoServico[] resultadoBuscarListaServicoPendValidacaoListaServicoServicoArray = resultadoBuscarListaServicoPendValidacao.getListaServico(); 				
		
		if(resultadoBuscarListaServicoPendValidacaoListaServicoServicoArray != null)
		{
			servicoList = Arrays.asList(resultadoBuscarListaServicoPendValidacaoListaServicoServicoArray);
		}
				
		//BUSCA LISTA PLANOS
		PlanoPortTypeProxy planoPortTypeProxy = new PlanoPortTypeProxy();
		BuscarListaPlanoPendValidacaoRequest buscarListaPlanoPendValidacaoRequest = new BuscarListaPlanoPendValidacaoRequest();
		BuscarListaPlanoPendValidacaoResponse buscarListaPlanoPendValidacaoResponse = null;
		
		try {
			buscarListaPlanoPendValidacaoResponse = planoPortTypeProxy.buscarListaPlanoPendValidacao(buscarListaPlanoPendValidacaoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), ex.getMessage(),
					servicoForm, response);
			return null;
		}
		
		ResultadoBuscarListaPlanoPendValidacao resultadoBuscarListaPlanoPendValidacao = new ResultadoBuscarListaPlanoPendValidacao();		
		resultadoBuscarListaPlanoPendValidacao = buscarListaPlanoPendValidacaoResponse.getResultadoBuscarListaPlanoPendValidacao();		
		List<ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlano> planoList = Arrays.asList(resultadoBuscarListaPlanoPendValidacao.getListaPlano());
		
		
		//BUSCA LISTA PLATAFORMAS		
		PlataformaPortTypeProxy plataformaPortTypeProxy = new PlataformaPortTypeProxy();
		BuscarListaPlataformaRequest buscarListaPlataformaRequest = new BuscarListaPlataformaRequest();
		BuscarListaPlataformaResponse buscarListaPlataformaResponse = null;		
		
		try {
			buscarListaPlataformaResponse = plataformaPortTypeProxy.buscarListaPlataforma(buscarListaPlataformaRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), ex.getMessage(),
					servicoForm, response);
			return null;
		}				
		
		ResultadoBuscarListaPlataformaPlataforma[] plataformaListArray = buscarListaPlataformaResponse.getResultadoBuscarListaPlataforma();		
		List<ResultadoBuscarListaPlataformaPlataforma> plataformaList =  Arrays.asList(plataformaListArray);
							
		request.setAttribute("servicoList", servicoList.size());
		request.setAttribute("planoList", planoList.size());
		request.setAttribute("plataformas", plataformaList);
		
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward listarGrupoServicos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{	
		ServicoForm formulario = (ServicoForm)form;
		Long idSistema = null;		
		String idPlataformaStr =  request.getParameter("idPlataforma");
		Long idPlataforma = Long.parseLong(idPlataformaStr);
		idSistema = (idPlataforma == 3) ? 2L : null;
		request.setAttribute("idSistema", idSistema);
		request.setAttribute("idPlataforma", idPlataforma);
		
		BuscarListaGrupoServicoRequest buscarListaGrupoServicoRequest = new BuscarListaGrupoServicoRequest();
		ParametrosListarGrupoServico parametrosListarGrupoServico = new ParametrosListarGrupoServico();
		if(PlataformaEnum.PREPAGO.equals(idPlataforma)){
			parametrosListarGrupoServico.setIndCatalogo(new Long(2));
		}else if(PlataformaEnum.POSPAGO.equals(idPlataforma)){
			parametrosListarGrupoServico.setIndCatalogo(new Long(1));
		}else if(PlataformaEnum.CONTROLE.equals(idPlataforma)){
			if(idSistema == 3) {
				parametrosListarGrupoServico.setIndCatalogo(new Long(2));
			}
			if(idSistema == 2) {
				parametrosListarGrupoServico.setIndCatalogo(new Long(1));
			}
		}		
		
		buscarListaGrupoServicoRequest.setParametrosListarGrupoServico(parametrosListarGrupoServico);
		
        GrupoServicoPortTypeProxy grupoServicoPortTypeProxy = new GrupoServicoPortTypeProxy();
        BuscarListaGrupoServicoResponse buscarListaGrupoServicoResponse = new BuscarListaGrupoServicoResponse();
        
        try{	
        	buscarListaGrupoServicoResponse = grupoServicoPortTypeProxy.buscarListaGrupoServico(buscarListaGrupoServicoRequest, this.getUserName(), this.getPassword());
        }catch(AxisFault ex){
        	this.AxisFaultExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), ex.getMessage(),
        			formulario, response);
			return null;
			
        }
        
        List<ResultadoListarGrupoServicoCategoria> listaResultadoListarGrupoServicoCategoria =  null;
        if (buscarListaGrupoServicoResponse.getResultadoListarGrupoServico() != null) {
        	listaResultadoListarGrupoServicoCategoria = Arrays.asList(buscarListaGrupoServicoResponse.getResultadoListarGrupoServico()); 
        }
        
                                
		
		JSONObject resultadoJSON = new JSONObject();
		String result = "";
		JSONArray jsonArrayCategorias = new JSONArray();
		try {
			for (ResultadoListarGrupoServicoCategoria categoria : listaResultadoListarGrupoServicoCategoria) {
				JSONObject jsonResult = new JSONObject();
				jsonResult.put("id", categoria.getIdCategoria());
				jsonResult.put("nome", categoria.getNmCategoria());
				jsonArrayCategorias.put(jsonResult);
			}
			resultadoJSON.put("arrayCategorias", jsonArrayCategorias);
		} catch (JSONException e) {
		}
		result = resultadoJSON.toString();
		PrintWriter out = response.getWriter();
		out.println(result);
		out.flush();
		return null;
	}
		
					
	
	public ActionForward pesquisarServicos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		ServicoForm servicoForm = (ServicoForm) form;
		BuscarListaServicoParametrizacaoRequest buscarListaServicoParametrizacaoRequest = new BuscarListaServicoParametrizacaoRequest();
		ParametrosBuscarListaServicoParametrizacao parametrosBuscarListaServicoParametrizacao = new ParametrosBuscarListaServicoParametrizacao();	
		PaginacaoIn paginacaoIn = new PaginacaoIn();
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());	
		
		if(servicoForm.getPaginaSolicitada() != null && servicoForm.getPaginaSolicitada() > 0) {
			paginacaoIn.setPaginaSolicitada(servicoForm.getPaginaSolicitada());
		}else {
			paginacaoIn.setPaginaSolicitada(1);
		}
		
		parametrosBuscarListaServicoParametrizacao.setPaginacaoIn(paginacaoIn);
		
		if(servicoForm.getIdPlataforma() != null) {
			parametrosBuscarListaServicoParametrizacao.setIdPlataforma(servicoForm.getIdPlataforma());
		}
		if(servicoForm.getIdCategoria() != null) {
			parametrosBuscarListaServicoParametrizacao.setIdCategoriaCatalogo(servicoForm.getIdCategoria());
		}
		if(servicoForm.getNmServico() != null && servicoForm.getNmServico().length() > 0) {
			parametrosBuscarListaServicoParametrizacao.setNmServico(servicoForm.getNmServico());
		}
		if(servicoForm.getCdServico() != null && servicoForm.getCdServico().length() > 0) {
			parametrosBuscarListaServicoParametrizacao.setCdServico(servicoForm.getCdServico());
		}
		if(servicoForm.getDisponibilidade() != null && !servicoForm.getDisponibilidade().equalsIgnoreCase("A")) {
			parametrosBuscarListaServicoParametrizacao.setIndisponivel(servicoForm.getDisponibilidade());
		}					
		
		ServicoPortTypeProxy servicoPortTypeProxy = new ServicoPortTypeProxy();
		BuscarListaServicoParametrizacaoResponse buscarListaServicoParametrizacaoResponse = null;
		
		buscarListaServicoParametrizacaoRequest.setParametrosBuscarListaServicoParametrizacao(parametrosBuscarListaServicoParametrizacao);
		
		try {
			buscarListaServicoParametrizacaoResponse = servicoPortTypeProxy.buscarListaServicoParametrizacao(buscarListaServicoParametrizacaoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), ex.getMessage(),
					servicoForm, response);
			return null;
		}
			
		ResultadoBuscarListaServicoParametrizacao resultadoBuscarListaServicoParametrizacao = buscarListaServicoParametrizacaoResponse.getResultadoBuscarListaServicoParametrizacao();
		
		PaginacaoOut paginacaoOut = resultadoBuscarListaServicoParametrizacao.getPaginacaoOut();
		this.tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(), request);
			
		List<ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao> listaServicoParametrizacao = 
				Arrays.asList(resultadoBuscarListaServicoParametrizacao.getListaBuscarListaServicoParametrizacao());
											
		if(listaServicoParametrizacao != null) {		
			servicoForm.setListaServicoParametrizacao(listaServicoParametrizacao);
			request.setAttribute("idPlataforma", servicoForm.getIdPlataforma());
		}
			
		return mapping.findForward("success");
	}
	
	public ActionForward pesquisarPlanos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws RemoteException {
		ServicoForm servicoForm = (ServicoForm) form;
		BuscarListaPlanoParametrizacaoRequest buscarListaPlanoParametrizacaoRequest = new BuscarListaPlanoParametrizacaoRequest();
		ParametrosBuscarListaPlanoParametrizacao parametrosBuscarListaPlanoParametrizacao = new ParametrosBuscarListaPlanoParametrizacao();
		br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoIn paginacaoIn = new br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoIn();
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		
		if(servicoForm.getPaginaSolicitada() != null && servicoForm.getPaginaSolicitada() > 0) {
			paginacaoIn.setPaginaSolicitada(servicoForm.getPaginaSolicitada());
		}else {
			paginacaoIn.setPaginaSolicitada(1);
		}
		if(servicoForm.getIdPlataforma() != null) {
			parametrosBuscarListaPlanoParametrizacao.setIdPlataforma(servicoForm.getIdPlataforma());
		}
		if(servicoForm.getRegionais() != null && servicoForm.getRegionais().length() > 0) {
			List<String> listaRegionais = new ArrayList<String>();
			List<String> listaUfs = new ArrayList<String>();
			Map<String, String[]> mapRegionais = extrairJSONChaveValor(servicoForm.getRegionais(), listaRegionais, listaUfs);
			
			for (String idRegional : listaRegionais) {
				if(parametrosBuscarListaPlanoParametrizacao.getListaRegional() == null) {
					ListaRegionalUf listaRegional = new ListaRegionalUf();
				}
				ParametrosBuscarListaPlanoParametrizacaoListaRegionalRegional[] listaRegional = parametrosBuscarListaPlanoParametrizacao.getListaRegional();
				Regional regional = new Regional();
				regional.setIdRegional(Integer.valueOf(idRegional));
				
				String[] arrayUfs = mapRegionais.get(idRegional);
				if(arrayUfs != null && arrayUfs.length > 0) {
					 Uf listaUf = new Uf();
					for (String idUf : arrayUfs) {
						if(idUf != null && idUf.length() > 0)
							listaUf.setIdUf(Integer.valueOf(idUf));
						
					}
				}
			}
		}

		if(servicoForm.getNmServico() != null && servicoForm.getNmServico().length() > 0) {
			parametrosBuscarListaPlanoParametrizacao.setNmComercial(servicoForm.getNmServico());
		}
		if(servicoForm.getCdServico() != null && servicoForm.getCdServico().length() > 0) {
			parametrosBuscarListaPlanoParametrizacao.setCdCodigo(servicoForm.getCdServico());
		}
		//Diferente de "A" porque o A é o A de Ambos.
		if(servicoForm.getDisponibilidade().trim().length() > 0 && !servicoForm.getDisponibilidade().equalsIgnoreCase("A")) {
			parametrosBuscarListaPlanoParametrizacao.setIndisponivel(servicoForm.getDisponibilidade());
		}
		
		if(servicoForm.getIndTitular()){
			parametrosBuscarListaPlanoParametrizacao.setIndTitDep("T");
		}else{
			parametrosBuscarListaPlanoParametrizacao.setIndTitDep("D");
		}
		
		parametrosBuscarListaPlanoParametrizacao.setPaginacaoIn(paginacaoIn);
		buscarListaPlanoParametrizacaoRequest.setParametrosBuscarListaPlanoParametrizacao(parametrosBuscarListaPlanoParametrizacao);
		
		PlanoPortTypeProxy planoPortTypeProxy = new PlanoPortTypeProxy();
		BuscarListaPlanoParametrizacaoResponse buscarListaPlanoParametrizacaoResponse = null;
		
		try{
			buscarListaPlanoParametrizacaoResponse = planoPortTypeProxy.buscarListaPlanoParametrizacao(buscarListaPlanoParametrizacaoRequest, this.getUserName(), this.getPassword());
		}catch(AxisFault ex){
			this.AxisFaultExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), ex.getMessage(),
					servicoForm, response);
			return null;
		}
		
		ResultadoBuscarListaPlanoParametrizacao resultadoBuscarListaPlanoParametrizacao = buscarListaPlanoParametrizacaoResponse.getResultadoBuscarListaPlanoParametrizacao();
		PaginacaoOut paginacaoOut = resultadoBuscarListaPlanoParametrizacao.getPaginacaoOut();
		tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(), request);
		
		ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao [] listaPlanoParametrizacao = resultadoBuscarListaPlanoParametrizacao.getListaBuscarListaPlanoParametrizacao();
		
		if(listaPlanoParametrizacao != null) {
			servicoForm.setListaPlanoParametrizacao(listaPlanoParametrizacao);
			request.setAttribute("idPlataforma", servicoForm.getIdPlataforma());
		}

		return mapping.findForward("success");
	}
	
	public ActionForward carregarAlterarServico(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		ServicoForm servicoForm = (ServicoForm) form;
		
		if(request.getParameter("id_plataforma") != null && request.getParameter("id_plataforma").trim().length() > 0) {
			request.setAttribute("idPlataforma", request.getParameter("id_plataforma").trim().toString());
		}
		if(request.getParameter("id_servico") != null && request.getParameter("id_servico").trim().length() > 0) {
			request.setAttribute("idServico", request.getParameter("id_servico").trim().toString());
		}
		if(request.getParameter("id_tpServico") != null && request.getParameter("id_tpServico").trim().length() > 0) {
			request.setAttribute("idTpServico", request.getParameter("id_tpServico").trim().toString());
		}
		if(request.getParameter("id_categoriaLegado") != null && request.getParameter("id_categoriaLegado").trim().length() > 0) {
			request.setAttribute("idCategoriaLegado", request.getParameter("id_categoriaLegado").trim().toString());
		}
		if(request.getParameter("id_categoriaCatalogo") != null && request.getParameter("id_categoriaCatalogo").trim().length() > 0) {
			request.setAttribute("idCategoriaCatalogo", request.getParameter("id_categoriaCatalogo").trim().toString());
		}
		if(request.getParameter("cd_servico") != null && request.getParameter("cd_servico").trim().length() > 0) {
			request.setAttribute("cdServico", request.getParameter("cd_servico"));
		}
		if(request.getParameter("nm_comercial") != null && request.getParameter("nm_comercial").trim().length() > 0) {
			request.setAttribute("nmComercial", request.getParameter("nm_comercial"));
		}
		if(request.getParameter("nm_categoriaCatalogo") != null && request.getParameter("nm_categoriaCatalogo").trim().length() > 0) {
			request.setAttribute("nmCategoriaCatalogo", request.getParameter("nm_categoriaCatalogo"));
		}
		if(request.getParameter("nm_categoriaLegado") != null && request.getParameter("nm_categoriaLegado").trim().length() > 0) {
			request.setAttribute("nmCategoriaLegado", request.getParameter("nm_categoriaLegado"));
		}
		if(request.getParameter("dsc_tpServico") != null && request.getParameter("dsc_tpServico").trim().length() > 0) {
			request.setAttribute("dscTpServico", request.getParameter("dsc_tpServico"));
		}
		if(request.getParameter("qtd_minAtivCatalogo") != null && request.getParameter("qtd_minAtivCatalogo").trim().length() > 0) {
			request.setAttribute("qtdMinAtivCatalogo", request.getParameter("qtd_minAtivCatalogo"));
		}
		if(request.getParameter("qtd_maxAtivCatalogo") != null && request.getParameter("qtd_maxAtivCatalogo").trim().length() > 0) {
			request.setAttribute("qtdMaxAtivCatalogo", request.getParameter("qtd_maxAtivCatalogo"));
		}
		if(request.getParameter("qtd_mimAtivLegado") != null && request.getParameter("qtd_mimAtivLegado").trim().length() > 0) {
			request.setAttribute("qtdMimAtivLegado", request.getParameter("qtd_mimAtivLegado"));
		}
		if(request.getParameter("qtd_maxAtivLegado") != null && request.getParameter("qtd_maxAtivLegado").trim().length() > 0) {
			request.setAttribute("qtdMaxAtivLegado", request.getParameter("qtd_maxAtivLegado"));
		}
		if(request.getParameter("dt_alteracao") != null && request.getParameter("dt_alteracao").trim().length() > 0) {
			request.setAttribute("dtAlteracaoFormat", request.getParameter("dt_alteracao"));
		}
		if(request.getParameter("nm_usuarioAlteracao") != null && request.getParameter("nm_usuarioAlteracao").trim().length() > 0) {
			request.setAttribute("nmUsuarioAlteracao", request.getParameter("nm_usuarioAlteracao"));
		}
		if(request.getParameter("indisponibilidade_catalogo") != null && request.getParameter("indisponibilidade_catalogo").trim().length() > 0) {
			request.setAttribute("inDispCatalogo", request.getParameter("indisponibilidade_catalogo"));
		}
		if(request.getParameter("indisponibilidade_legado") != null &&!request.getParameter("indisponibilidade_legado").trim().equals("")) {
			request.setAttribute("inDispLegado", request.getParameter("indisponibilidade_legado"));
		}
		if(request.getParameter("ativa_wifi") != null &&!request.getParameter("ativa_wifi").trim().equals("")) {
			request.setAttribute("ativaWiFi", request.getParameter("ativa_wifi"));
		}		
		                              
		BuscarListaTipoServicoRequest buscarListaTipoServicoRequest = new BuscarListaTipoServicoRequest();
		
		BuscarListaTipoServicoResponse buscarListaTipoServicoResponse = null;
		ServicoPortTypeProxy servicoPortTypeProxy = new ServicoPortTypeProxy();
		
		try {
			buscarListaTipoServicoResponse = servicoPortTypeProxy.buscarListaTipoServico(buscarListaTipoServicoRequest, this.getUserName(), this.getPassword());				
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), ex.getMessage(),
					servicoForm, response);
			return null;
		}
		
		List<ResultadoBuscarListaTipoServicoListaTipoServicoRetorno> listaTipoServico = Arrays.asList(buscarListaTipoServicoResponse.getResultadoBuscarListaTipoServico().getListaTipoServico());
				
		Long idSistema = null;
		Long idPlataforma = null;
		
		
		
		String idPlataformaStr =  request.getParameter("id_plataforma");
		if(idPlataformaStr != null && idPlataformaStr.trim().length() > 0) {
			idPlataforma = Long.parseLong(idPlataformaStr);
		}
		idSistema = (idPlataforma == 3) ? 2L : null;
		
		ParametrosListarGrupoServico parametrosListarGrupoServico = new ParametrosListarGrupoServico();
		
		if(PlataformaEnum.PREPAGO.equals(idPlataforma))
		{
		 parametrosListarGrupoServico.setIndCatalogo(new Long(2));		 
		}
		else if(PlataformaEnum.POSPAGO.equals(idPlataforma))
		{
		  parametrosListarGrupoServico.setIndCatalogo(new Long(1));
		}else if(PlataformaEnum.CONTROLE.equals(idPlataforma))
		{			
			//if(idSistema == null)
				//return new ArrayList<Categoria>();
			
			 if(idSistema == 3)
			    parametrosListarGrupoServico.setIndCatalogo(new Long(2));

			 if(idSistema == 2)
				parametrosListarGrupoServico.setIndCatalogo(new Long(1));
	   }
		
		BuscarListaGrupoServicoRequest buscarListaGrupoServicoRequest = new BuscarListaGrupoServicoRequest();
		

		
		buscarListaGrupoServicoRequest.setParametrosListarGrupoServico(parametrosListarGrupoServico);
		
		GrupoServicoPortTypeProxy grupoServicoPortTypeProxy = new GrupoServicoPortTypeProxy();
		BuscarListaGrupoServicoResponse buscarListaGrupoServicoResponse = null;		
		
		try{
			buscarListaGrupoServicoResponse = grupoServicoPortTypeProxy.buscarListaGrupoServico(buscarListaGrupoServicoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), ex.getMessage(),
					servicoForm, response);
			return null;
		}	
		
		List<ResultadoListarGrupoServicoCategoria> categoriaList = Arrays.asList(buscarListaGrupoServicoResponse.getResultadoListarGrupoServico());

		request.setAttribute("categoriaList", categoriaList);
		request.setAttribute("tpServico", listaTipoServico); 
				
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward popupVisualizaServicos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ServicoForm formulario = (ServicoForm)form;
		BuscarListaServicoPendValidacaoRequest buscarListaServicoPendValidacaoRequest = new BuscarListaServicoPendValidacaoRequest();
		BuscarListaServicoPendValidacaoResponse listaServicoPendValidacaoResponse = null;
		ServicoPortTypeProxy servicoPortTypeProxy = new ServicoPortTypeProxy(); 
		
		try{
			listaServicoPendValidacaoResponse = servicoPortTypeProxy.buscarListaServicoPendValidacao(buscarListaServicoPendValidacaoRequest, this.getUserName(), this.getPassword());
		}catch(AxisFault ex){
			this.AxisFaultExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), ex.getMessage(),
					formulario, response);
			return null;
		}
		
		ResultadoBuscarListaServicoPendValidacao resultadoBuscarListaServicoPendValidacao = listaServicoPendValidacaoResponse.getResultadoBuscarListaServicoPendValidacao();
		ResultadoBuscarListaServicoPendValidacaoListaServicoServico[] servicoList = resultadoBuscarListaServicoPendValidacao.getListaServico();
		List<ResultadoBuscarListaServicoPendValidacaoListaServicoServico> servicoLista = Arrays.asList(servicoList);
		if(servicoLista != null && servicoLista.size() > 0) {
			request.setAttribute("servicosList", servicoList);
		}
	
		return mapping.findForward("success");
	}
	
	public ActionForward popupVisualizaPlanos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ServicoForm formulario = (ServicoForm)form;
		PlanoPortTypeProxy planoPortTypeProxy = new PlanoPortTypeProxy();
		BuscarListaPlanoPendValidacaoRequest buscarListaPlanoPendValidacaoRequest = new BuscarListaPlanoPendValidacaoRequest();
		BuscarListaPlanoPendValidacaoResponse buscarListaPlanoPendValidacaoResponse = null;
		try{
			buscarListaPlanoPendValidacaoResponse = planoPortTypeProxy.buscarListaPlanoPendValidacao(buscarListaPlanoPendValidacaoRequest, this.getUserName(), this.getPassword());
		}catch(AxisFault ex){
			this.AxisFaultExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), ex.getMessage(),
					formulario, response);
			return null;
		}
		
		ResultadoBuscarListaPlanoPendValidacao resultadoBuscarListaPlanoPendValidacao = buscarListaPlanoPendValidacaoResponse.getResultadoBuscarListaPlanoPendValidacao();
		ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlano [] retornoPlanoList = resultadoBuscarListaPlanoPendValidacao.getListaPlano();
		List<ResultadoBuscarListaPlanoPendValidacaoListaPlanoRetornoPlano> retornoPlanoLista = Arrays.asList(retornoPlanoList);
		
		if(retornoPlanoLista != null && retornoPlanoLista.size() > 0) {
			request.setAttribute("planos", retornoPlanoList);
		}
		
		return mapping.findForward("success");
	}
	
	public ActionForward listarRegionais(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String regionaisJSON = request.getParameter("hiddenRegionais");
		
		ServicoForm servicoForm = (ServicoForm) form;				
		
		if (regionaisJSON != null) {
			List<String> chaves = new ArrayList<String>();
			List<String> valores = new ArrayList<String>();
		
			servicoForm.setRegionaisSelecionadas(chaves.toArray(new String[]{}));
			servicoForm.setUfsSelecionados(valores.toArray(new String[]{}));
		}
		
		BuscarListaRegionalComUFRequest buscarListaRegionalComUFRequest = new BuscarListaRegionalComUFRequest();					
		
		RegionalPortTypeProxy regionalPortTypeProxy = new RegionalPortTypeProxy();
		List<ListaRegionalUf> listaRegionalUfList = null;
		try{
			listaRegionalUfList = Arrays.asList(regionalPortTypeProxy.buscarListaRegionalComUF(buscarListaRegionalComUFRequest, this.getUserName(), this.getPassword()));
		}catch(AxisFault ex){
			this.AxisFaultExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), ex.getMessage(),
					servicoForm, response);
			return null;
		}	
		if(listaRegionalUfList != null) {
			servicoForm.setListaRegionalUf(listaRegionalUfList);			
		}
		return mapping.findForward(SUCCESS);	
	}
	
	public ActionForward popupUFs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ServicoForm formulario = (ServicoForm) form;
		Long idPlano = Long.valueOf(request.getParameter("id_plano"));
		BuscarListaUFPorIdPlanoRequest buscarListaUFPorIdPlanoRequest = new BuscarListaUFPorIdPlanoRequest();
		ParametrosBuscarListaUfPorIdPlano parametrosBuscarListaUfPorIdPlano = new ParametrosBuscarListaUfPorIdPlano();
		ParametrosBuscarListaUfPorIdPlanoListaUfPorIdPlano parametrosBuscarListaUfPorIdPlanoListaUfPorIdPlano = new ParametrosBuscarListaUfPorIdPlanoListaUfPorIdPlano();
		parametrosBuscarListaUfPorIdPlanoListaUfPorIdPlano.setIdPlano(Long.valueOf(idPlano));
		parametrosBuscarListaUfPorIdPlano.setListaUfPorIdPlano(parametrosBuscarListaUfPorIdPlanoListaUfPorIdPlano);
		buscarListaUFPorIdPlanoRequest.setParametrosBuscarListaUfPorIdPlano(parametrosBuscarListaUfPorIdPlano);
		
		UFPortTypeProxy uFPortTypeProxy = new UFPortTypeProxy();
		BuscarListaUFPorIdPlanoResponse buscarListaUFPorIdPlanoResponse = null;
		try{
			buscarListaUFPorIdPlanoResponse = uFPortTypeProxy.buscarListaUFPorIdPlano(buscarListaUFPorIdPlanoRequest, this.getUserName(), this.getPassword());
		}catch(AxisFault ex){
			this.AxisFaultExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), ex.getMessage(),
					formulario, response);
			return null;
		}
		ResultadoBuscarListaUFUF [] resultadoBuscarListaUF = buscarListaUFPorIdPlanoResponse.getResultadoBuscarListaUF();
		
		if(resultadoBuscarListaUF != null) {
			List<Uf> listUF = Arrays.asList(resultadoBuscarListaUF);
			request.setAttribute("ufs", listUF);
		}
		
		return mapping.findForward("success");
	}

	public ActionForward pesquisarCategorias(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ServicoForm servicoForm = (ServicoForm) form;
		Long idPlataforma = 1L;
		
		BuscarListaGrupoServicoRequest buscarListaGrupoServicoRequest = new BuscarListaGrupoServicoRequest();
		ParametrosListarGrupoServico parametrosListarGrupoServico = new ParametrosListarGrupoServico();
		
		if(idPlataforma != null) {
			parametrosListarGrupoServico.setIndCatalogo(idPlataforma);
		}
		
		GrupoServicoPortTypeProxy grupoServicoPortTypeProxy = new GrupoServicoPortTypeProxy();
		buscarListaGrupoServicoRequest.setParametrosListarGrupoServico(parametrosListarGrupoServico);
		
		BuscarListaGrupoServicoResponse buscarListaGrupoServicoResponse = null;
		try{
			buscarListaGrupoServicoResponse = grupoServicoPortTypeProxy.getGrupoServicoPortType().buscarListaGrupoServico(buscarListaGrupoServicoRequest, this.getUserName(), this.getPassword());
		}catch(AxisFault ex){
			this.AxisFaultExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), ex.getMessage(),
					servicoForm, response);
			return null;
		}			
		List<ResultadoListarGrupoServicoCategoria> categoriaLista = Arrays.asList(buscarListaGrupoServicoResponse.getResultadoListarGrupoServico());								
		
		servicoForm.setCategorias(categoriaLista);
		servicoForm.setIdPlataforma(idPlataforma);
		
		return mapping.findForward(SUCCESS);
	}
	
	
	public ActionForward adicionarCategorias(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ServicoForm servicoForm = (ServicoForm) form;
		CriarGrupoServicoCatalogoRequest criarGrupoServicoCatalogoRequest = new CriarGrupoServicoCatalogoRequest();
		ParametroscriarGrupoServicoCatalogo parametroscriarGrupoServicoCatalogo = new ParametroscriarGrupoServicoCatalogo();
		parametroscriarGrupoServicoCatalogo.setNomeGrupoServico(servicoForm.getNmCategoria().toUpperCase());
		parametroscriarGrupoServicoCatalogo.setIndisponivel(servicoForm.getIndisponivel());
		criarGrupoServicoCatalogoRequest.setParametroscriarGrupoServicoCatalogo(parametroscriarGrupoServicoCatalogo);
		
		CriarGrupoServicoCatalogoResponse criarGrupoServicoCatalogoResponse = null;
		GrupoServicoPortTypeProxy grupoServicoPortTypeProxy = new GrupoServicoPortTypeProxy();
				
		try {
			criarGrupoServicoCatalogoResponse = grupoServicoPortTypeProxy.criarGrupoServicoCatalogo(criarGrupoServicoCatalogoRequest, this.getUserName(), this.getPassword());					
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), ex.getMessage(),
					servicoForm, response);
			return null;
		}	
						
		try {			
			response.setContentType("text/javascript");
			PrintWriter out = response.getWriter();
			out.println("$('pesquisar_categorias').onclick();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public ActionForward abrirAlterarCategoria(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{									
		request.setAttribute("idCategoria", request.getParameter("id_categoria"));
		request.setAttribute("nmCategoria", request.getParameter("nm_categoria"));
		request.setAttribute("indisponivel", request.getParameter("indisponivel"));
				
		return mapping.findForward(SUCCESS);		

	}
	
	public ActionForward alterarValorCategoria(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ServicoForm servicoForm = (ServicoForm) form;
		
		AlterarGrupoServicoCatalogoRequest alterarGrupoServicoCatalogoRequest = new AlterarGrupoServicoCatalogoRequest();
		ParametrosAlterarGrupoServicoCatalogo parametrosAlterarGrupoServicoCatalogo = new ParametrosAlterarGrupoServicoCatalogo();
		parametrosAlterarGrupoServicoCatalogo.setIdGrupoServico(servicoForm.getIdCategoria());
		parametrosAlterarGrupoServicoCatalogo.setNmGrupoServico(servicoForm.getNmCategoria());
		parametrosAlterarGrupoServicoCatalogo.setIndisponivel(servicoForm.getIndisponivel());
		
		alterarGrupoServicoCatalogoRequest.setParametrosAlterarGrupoServicoCatalogo(parametrosAlterarGrupoServicoCatalogo);						

		AlterarGrupoServicoCatalogoResponse alterarGrupoServicoCatalogoResponse = null;
		GrupoServicoPortTypeProxy grupoServicoPortTypeProxy = new GrupoServicoPortTypeProxy();
				
		try {
			alterarGrupoServicoCatalogoResponse = grupoServicoPortTypeProxy.alterarGrupoServicoCatalogo(alterarGrupoServicoCatalogoRequest, this.getUserName(), this.getPassword());				
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), ex.getMessage(),
					servicoForm, response);
			return null;
		}	
		
		try {			
			response.setContentType("text/javascript");
			PrintWriter out = response.getWriter();
			out.println("$('pesquisar_categorias').onclick();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public ActionForward popupAlterarValorCategoria(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{					
		Long idCategoria = Long.valueOf(request.getParameter("id_categoria"));
		ServicoForm servicoForm = (ServicoForm)form;
		BuscarServicoPorGrupoServicoCatalogoRequest buscarServicoPorGrupoServicoCatalogoRequest = new BuscarServicoPorGrupoServicoCatalogoRequest();
		ParametrosBuscarListaServicoPorGrupoServicoCatalogo parametrosBuscarListaServicoPorGrupoServicoCatalogo = new ParametrosBuscarListaServicoPorGrupoServicoCatalogo();
		
		parametrosBuscarListaServicoPorGrupoServicoCatalogo.setIdGrupoServico(idCategoria);
		
		buscarServicoPorGrupoServicoCatalogoRequest.setParametrosBuscarListaServicoPorGrupoServicoCatalogo(parametrosBuscarListaServicoPorGrupoServicoCatalogo);
				
		GrupoServicoPortTypeProxy grupoServicoPortTypeProxy = new GrupoServicoPortTypeProxy();
		BuscarServicoPorGrupoServicoCatalogoResponse buscarServicoPorGrupoServicoCatalogoResponse = new BuscarServicoPorGrupoServicoCatalogoResponse();
		try{
			buscarServicoPorGrupoServicoCatalogoResponse = grupoServicoPortTypeProxy.buscarServicoPorGrupoServicoCatalogo(buscarServicoPorGrupoServicoCatalogoRequest, this.getUserName(), this.getPassword());
		}catch(AxisFault ex){
			this.AxisFaultExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), ex.getMessage(),
					servicoForm, response);
			return null;
		}
		ResultadoBuscarServicoPorGrupoServicoCatalogo resultadoBuscarServicoPorGrupoServicoCatalogo = buscarServicoPorGrupoServicoCatalogoResponse.getResultadoBuscarServicoPorGrupoServicoCatalogo();
		
		long qtdeServico = resultadoBuscarServicoPorGrupoServicoCatalogo.getQtdeServico();
		if(qtdeServico > 0) {
			request.setAttribute("qtdeServico", qtdeServico);
		}else {
			request.setAttribute("qtdeServico", 0);
		}
		return mapping.findForward(SUCCESS);	
	}
	
	public ActionForward abrirPopupApagarCategoria(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long idCategoria = Long.valueOf(request.getParameter("id_categoria"));
		String nmCategoria = request.getParameter("nm_categoria");
		BuscarServicoPorGrupoServicoCatalogoRequest servicoPorGrupoServicoCatalogoRequest = new BuscarServicoPorGrupoServicoCatalogoRequest();
		ParametrosBuscarListaServicoPorGrupoServicoCatalogo buscarListaServicoPorGrupoServicoCatalogo = new ParametrosBuscarListaServicoPorGrupoServicoCatalogo();
		buscarListaServicoPorGrupoServicoCatalogo.setIdGrupoServico(idCategoria);
		
		GrupoServicoPortTypeProxy grupoServicoPortTypeProxy = new GrupoServicoPortTypeProxy();
		servicoPorGrupoServicoCatalogoRequest.setParametrosBuscarListaServicoPorGrupoServicoCatalogo(buscarListaServicoPorGrupoServicoCatalogo);
		BuscarServicoPorGrupoServicoCatalogoResponse grupoServicoCatalogoResponse = null;
		try{
			grupoServicoCatalogoResponse = grupoServicoPortTypeProxy.buscarServicoPorGrupoServicoCatalogo(servicoPorGrupoServicoCatalogoRequest, this.getUserName(), this.getPassword());
		}catch(AxisFault ex){
			this.AxisFaultExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), ex.getMessage(), form, response);
			return null;
		} 
		ResultadoBuscarServicoPorGrupoServicoCatalogo resultadoBuscarServicoPorGrupoServicoCatalogo = grupoServicoCatalogoResponse.getResultadoBuscarServicoPorGrupoServicoCatalogo();
		long qtdeServico = resultadoBuscarServicoPorGrupoServicoCatalogo.getQtdeServico();
		
		if(qtdeServico > 0){
			request.setAttribute("qtdeServico", qtdeServico);
		}else{
			request.setAttribute("qtdeServico", 0);
		}
		
		
		request.setAttribute("nmCategoria", nmCategoria);
		request.setAttribute("idCategoria", idCategoria);
		return mapping.findForward("success");
	}
	
	public ActionForward deletarCategoria(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ExcluirListaGrupoServicoCatalogoRequest excluirListaGrupoServicoCatalogoRequest = new ExcluirListaGrupoServicoCatalogoRequest();
		ParametrosExcluirListaGrupoServicoCatalogo parametrosExcluirListaGrupoServicoCatalogo = new ParametrosExcluirListaGrupoServicoCatalogo();
		parametrosExcluirListaGrupoServicoCatalogo.setIdGrupoServico(Long.valueOf(request.getParameter("id_categoria")));
		excluirListaGrupoServicoCatalogoRequest.setParametrosExcluirListaGrupoServicoCatalogo(parametrosExcluirListaGrupoServicoCatalogo);
		GrupoServicoPortTypeProxy grupoServicoPortTypeProxy = new GrupoServicoPortTypeProxy();
		try{
			grupoServicoPortTypeProxy.excluirListaGrupoServicoCatalogo(excluirListaGrupoServicoCatalogoRequest, this.getUserName(), this.getPassword());
		}catch(AxisFault ex){
			ex.printStackTrace();
		}
		try {
			response.setContentType("text/javascript");
			PrintWriter out = response.getWriter();
			out.println("$('pesquisar_categorias').onclick();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ActionForward validarSicronizacaoServicos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException, Exception {
		ServicoForm formulario = (ServicoForm) form;
		ValidarListaServicoPorIdRequest validarListaServicoPorIdRequest = new ValidarListaServicoPorIdRequest();
		ServicoPortTypeProxy servicoPortTypeProxy = new ServicoPortTypeProxy();
		
		if(formulario.getIdsServico() != null && formulario.getIdsServico().toString().length() > 0) {
			Long idsServico[] = formulario.getIdsServico();
			int i = 0;
			long addServico[] = null;
			for (long idServico : idsServico) {
				addServico[i++] = idServico;
			}
			validarListaServicoPorIdRequest.setParametrosValidarListaServicoPorId(addServico);
		}
		
		try {
			servicoPortTypeProxy.validarListaServicoPorId(validarListaServicoPorIdRequest,this.getUserName(),this.getPassword());
		}catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), ex.getMessage(), form, response);
			return null;
		}
		
		return mapping.findForward("success");
	}
	
	public ActionForward validarSicronizacaoPlanos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ServicoForm formulario = (ServicoForm) form;
		ValidarListaPlanoPorIdRequest validarListaPlanoPorIdRequest = new ValidarListaPlanoPorIdRequest();
		PlanoPortTypeProxy planoPortTypeProxy = new PlanoPortTypeProxy();
	
		if(formulario.getIdPlano() != null) {
			Long idsPlano[] = formulario.getIdsPlanos();
			long addPlanoList[] = null;
			for(int i=0; i < idsPlano.length; i++) {
				addPlanoList[i] = idsPlano[i];
			}
			validarListaPlanoPorIdRequest.setParametrosValidarListaPlanoPorId(addPlanoList);
		}
		
		try {
			planoPortTypeProxy.validarListaPlanoPorId(validarListaPlanoPorIdRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), ex.getMessage(), formulario, response);
			return null;
		}
		
		return mapping.findForward("success");
	}
	
	public ActionForward alterarServico(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			ServicoForm servicoForm = (ServicoForm) form;
		
			if(servicoForm.getQtdMinAtivacaoCatalogo() > servicoForm.getQtdMinAtivLegado()) {
				CatalogoPRSException ex = new CatalogoPRSException("O limite para ativa&ccedil;&atilde;o padr&atilde;o no Cat&aacute;logo dever ser igual ou menor ao limite padr&atilde;o no Sistema Origem.");
				this.CatalogoPRSExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), response);
				return null;
			}
			else if(servicoForm.getQtdMaxAtivacaoCatalogo() > servicoForm.getQtdMaxAtivLegado()){
				CatalogoPRSException ex = new CatalogoPRSException("O limite para ativa&ccedil;&atilde;o m&aacute;xima no Cat&aacute;logo dever ser igual ou menor ao limite m&aacute;ximo no Sistema Origem.");
				this.CatalogoPRSExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), response);
				return null;
			}
			else {
				AlterarServicoParametrizacaoRequest alterarServicoParametrizacaoRequest = new AlterarServicoParametrizacaoRequest();
				ParametrosAlterarServicoParametrizacao parametrosAlterarServicoParametrizacao = new ParametrosAlterarServicoParametrizacao();
				if(servicoForm.getIdServico() != null) {
					parametrosAlterarServicoParametrizacao.setIdServico(servicoForm.getIdServico());
				}
				if(servicoForm.getIdTipoServico() != null) {
					parametrosAlterarServicoParametrizacao.setIdTpServico(servicoForm.getIdTipoServico());
				}
				if(servicoForm.getIdCategoriaCatalogo() != null) {
					parametrosAlterarServicoParametrizacao.setIdCategoriaCatalogo(servicoForm.getIdCategoriaCatalogo());
				}
				if(servicoForm.getQtdMinAtivacaoCatalogo() != null) {
					parametrosAlterarServicoParametrizacao.setQtdMinAtivacaoCatalogo(servicoForm.getQtdMinAtivacaoCatalogo());
				}
				if(servicoForm.getQtdMaxAtivacaoCatalogo() != null) {
					parametrosAlterarServicoParametrizacao.setQtdMaxAtivacaoCatalogo(servicoForm.getQtdMaxAtivacaoCatalogo());
				}
				
				if(servicoForm.getInDisponibilidadeCatalogo() != null && servicoForm.getInDisponibilidadeCatalogo().trim().length() > 0) {
					ParametrosAlterarServicoParametrizacaoInDisponibilidadeCatalogo InDisponibilidadeCatalogo = null;
					if(servicoForm.getInDisponibilidadeCatalogo().equals("S")){
						parametrosAlterarServicoParametrizacao.setInDisponibilidadeCatalogo(InDisponibilidadeCatalogo.S);
					} else{
						parametrosAlterarServicoParametrizacao.setInDisponibilidadeCatalogo(InDisponibilidadeCatalogo.N);
					}	
					
				}
				if(servicoForm.getOpAtivaWiFi() != null && servicoForm.getOpAtivaWiFi().trim().length() > 0) {
					ParametrosAlterarServicoParametrizacaoAtivaWiFi ativaWiFi = null;
					
					if (servicoForm.getOpAtivaWiFi().equals("S")) {
						parametrosAlterarServicoParametrizacao.setAtivaWiFi((ativaWiFi.S));
						
					} else {
						
						parametrosAlterarServicoParametrizacao.setAtivaWiFi((ativaWiFi.N));
					}
					
				}
				
				alterarServicoParametrizacaoRequest.setParametrosAlterarServicoParametrizacao(parametrosAlterarServicoParametrizacao);
				ServicoPortTypeProxy servicoPortTypeProxy = new ServicoPortTypeProxy();
				try{
					servicoPortTypeProxy.alterarServicoParametrizacao(alterarServicoParametrizacaoRequest, this.getUserName(), this.getPassword());
				}catch(AxisFault ex){
					this.AxisFaultExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), ex.getMessage(), servicoForm, response);
					return null;
				}
				try {
					response.setContentType("text/javascript");
					PrintWriter out = response.getWriter();
					out.println("$('botao_pesquisar_parametrizacao').onclick();");
					out.println("$('servicos_update').hide();");
					out.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		}
		
		
	public ActionForward abrirPoupuAssociarCategoria(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException, Exception {
			ServicoForm servicoForm = (ServicoForm) form;
			Long idPlataforma = Long.valueOf(request.getParameter("id_plataforma"));

			BuscarListaGrupoServicoRequest buscarListaGrupoServicoRequest = new BuscarListaGrupoServicoRequest();
			ParametrosListarGrupoServico parametrosListarGrupoServico = new ParametrosListarGrupoServico();
			
			if(PlataformaEnum.PREPAGO.equals(idPlataforma)){
				parametrosListarGrupoServico.setIndCatalogo(new Long(2));
			}else if(PlataformaEnum.POSPAGO.equals(idPlataforma)){
				parametrosListarGrupoServico.setIndCatalogo(new Long(1));
			}else if(PlataformaEnum.CONTROLE.equals(idPlataforma)){
				parametrosListarGrupoServico.setIndCatalogo(new Long(3));
			}
			
			GrupoServicoPortTypeProxy grupoServicoPortTypeProxy = new GrupoServicoPortTypeProxy();
			buscarListaGrupoServicoRequest.setParametrosListarGrupoServico(parametrosListarGrupoServico);
			BuscarListaGrupoServicoResponse listaGrupoServicoResponse = null;
		
			try{
				listaGrupoServicoResponse = grupoServicoPortTypeProxy.buscarListaGrupoServico(buscarListaGrupoServicoRequest, this.getUserName(), this.getPassword());
			}catch(AxisFault ex){
				this.AxisFaultExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), ex.getMessage(), servicoForm, response);
				return null;
			}
			ResultadoListarGrupoServicoCategoria[] resultadoListarGrupoServicoCategoria = listaGrupoServicoResponse.getResultadoListarGrupoServico();
			List<Categoria> categoriaList = Arrays.asList(resultadoListarGrupoServicoCategoria);
			
			request.setAttribute("listCategoria", categoriaList);
			return mapping.findForward("success");
		}
		
		public ActionForward associarNovaCategoria(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException {
			ServicoForm servicoForm = (ServicoForm) form;
			try {
				response.setContentType("text/javascript");
				PrintWriter out = response.getWriter();
				out.println("$('hidden_idCategoria').value = " + servicoForm.getIdCategoria()+";");
				out.println("$('salvar_associacao_categoria').onclick();");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public ActionForward gravarAssociacaoCategoria(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			ServicoForm servicoForm = (ServicoForm) form;
			
			AlterarCategoriaListaServicoRequest alterarCategoriaListaServicoRequest = new AlterarCategoriaListaServicoRequest();
			ParametrosAlterarCategoriaListaServico parametrosAlterarCategoriaListaServico = new ParametrosAlterarCategoriaListaServico();
					
			Long[] arrayServicos = servicoForm.getIdsServicos();
			int count = arrayServicos.length;
			
			long[] listaIdServico = new long[count];
			
			for (int i=0;i < count;i++) {
				listaIdServico[i] = arrayServicos[i];
			}		
						
			if(servicoForm.getIdCategoria() != null) {
				parametrosAlterarCategoriaListaServico.setIdCategoria(servicoForm.getIdCategoria());
			}
			
			if(listaIdServico.length > 0) {
				parametrosAlterarCategoriaListaServico.setListaIdServico(listaIdServico);
			}
			
			alterarCategoriaListaServicoRequest.setParametrosAlterarCategoriaListaServico(parametrosAlterarCategoriaListaServico);
			
			AlterarCategoriaListaServicoResponse alterarCategoriaListaServicoResponse = null;
			ServicoPortTypeProxy servicoPortTypeProxy = new ServicoPortTypeProxy();
					
			try {
				alterarCategoriaListaServicoResponse = servicoPortTypeProxy.alterarCategoriaListaServico(alterarCategoriaListaServicoRequest, this.getUserName(), this.getPassword());				
			} catch (AxisFault ex) {
				this.AxisFaultExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), ex.getMessage(),
						servicoForm, response);
				return null;
			}	
					
			
			try {
				response.setContentType("text/javascript");
				PrintWriter out = response.getWriter();
				out.println("$('botao_pesquisar_parametrizacao').onclick();");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
	public ActionForward carregarAlterarPlano(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			ServicoForm servicoForm = (ServicoForm) form;
			
			if(request.getParameter("ind_titDep").equalsIgnoreCase("T")) {
				request.setAttribute("indTitDep", true);
			}
			else if(request.getParameter("ind_titDep").equalsIgnoreCase("D") || request.getParameter("ind_titDep").equals("") ){
				request.setAttribute("indTitDep", false);
			}
			if(request.getParameter("id_plataforma") != null && request.getParameter("id_plataforma").trim().length() > 0) {
				request.setAttribute("idPlataforma", request.getParameter("id_plataforma"));
			}
			if(request.getParameter("id_plano") != null && request.getParameter("id_plano").trim().length() > 0) {
				request.setAttribute("idPlano", request.getParameter("id_plano"));
			}
			if(request.getParameter("id_tipo_plano") != null && request.getParameter("id_tipo_plano").trim().length() > 0) {
				request.setAttribute("idTipoPlano", request.getParameter("id_tipo_plano"));
			}
			if(request.getParameter("cd_codigo") != null && request.getParameter("cd_codigo").trim().length() > 0) {
				request.setAttribute("cdCodigo", request.getParameter("cd_codigo"));
			}
			if(request.getParameter("nm_comercial") != null && request.getParameter("nm_comercial").trim().length() > 0) {
				request.setAttribute("nmComercial", request.getParameter("nm_comercial"));
			}
			if(request.getParameter("qtd_maxDependenteCatalogo") != null && request.getParameter("qtd_maxDependenteCatalogo").trim().length() > 0) {
				request.setAttribute("qtdMaxDependenteCatalogo", request.getParameter("qtd_maxDependenteCatalogo"));
			}
			if(request.getParameter("qtd_maxDependenteLegado") != null && request.getParameter("qtd_maxDependenteLegado").trim().length() > 0) {
				request.setAttribute("qtdMaxDependenteLegado", request.getParameter("qtd_maxDependenteLegado"));
			}
			if(request.getParameter("dt_ultimaAlteracao") != null && request.getParameter("dt_ultimaAlteracao").trim().length() > 0) {
				request.setAttribute("dtUltimaAlteracaoFormat", request.getParameter("dt_ultimaAlteracao"));
			}
			if(request.getParameter("nm_usuarioAlteracao") != null && request.getParameter("nm_usuarioAlteracao").trim().length() > 0) {
				request.setAttribute("nmUsuarioAlteracao", request.getParameter("nm_usuarioAlteracao"));
			}
			if(request.getParameter("indisponibilidade_catalogo") != null && request.getParameter("indisponibilidade_catalogo").trim().length() > 0) {
				request.setAttribute("inDispCatalogo", request.getParameter("indisponibilidade_catalogo"));
			}
			if(request.getParameter("indisponibilidade_legado").trim().length() > 0 && request.getParameter("indisponibilidade_legado").equalsIgnoreCase("N")) {
				request.setAttribute("inDispLegado", "N&atilde;o");
			}
			else if(request.getParameter("indisponibilidade_legado").trim().length() > 0 && request.getParameter("indisponibilidade_legado").equalsIgnoreCase("S")) {
				request.setAttribute("inDispLegado", "Sim");
			}
			
			if(request.getParameter("ativa_wifi") != null && request.getParameter("ativa_wifi").trim().length() > 0) {
				request.setAttribute("ativaWiFi", request.getParameter("ativa_wifi"));
			}
			
			BuscarListaTipoPlanoRequest buscarListaTipoPlanoRequest = new BuscarListaTipoPlanoRequest();
			ParametrosBuscarListaTipoPlano parametrosBuscarListaTipoPlano = new ParametrosBuscarListaTipoPlano();
			if(request.getParameter("id_tipoPlano") != null && request.getParameter("id_tipoPlano").trim().length() > 0) {
				parametrosBuscarListaTipoPlano.setIdTipoPlano(Long.valueOf(request.getParameter("id_tipoPlano")));
			}
			if(request.getParameter("id_plataforma") != null && request.getParameter("id_plataforma").trim().length() > 0) {
				parametrosBuscarListaTipoPlano.setIdPlataforma(Long.valueOf(request.getParameter("id_plataforma")));
			}
			buscarListaTipoPlanoRequest.setParametrosBuscarListaTipoPlano(parametrosBuscarListaTipoPlano);
			BuscarListaTipoPlanoResponse buscarListaTipoPlanoResponse = null;
			PlanoPortTypeProxy planoPortTypeProxy = new PlanoPortTypeProxy();
			try{
				buscarListaTipoPlanoResponse = planoPortTypeProxy.buscarListaTipoPlano(buscarListaTipoPlanoRequest, this.getUserName(), this.getPassword());
			}catch(AxisFault ex){
				this.AxisFaultExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), ex.getMessage(),
						servicoForm, response);
				return null;
			}
			
			ResultadoBuscarListaTipoPlano resultadoBuscarListaTipoPlano = buscarListaTipoPlanoResponse.getResultadoBuscarListaTipoPlano();
			ResultadoBuscarListaTipoPlanoListaTipoPlanoTipoPlano[] tipoPlanoLista = resultadoBuscarListaTipoPlano.getListaTipoPlano();
			request.setAttribute("tipoPlano", tipoPlanoLista);
			
			return mapping.findForward("success");
		}
		
		
	public ActionForward alterarPlanos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException, Exception {
			ServicoForm formulario = (ServicoForm)form;
			if(formulario.getQtdMaxDependenteCatalogo() > formulario.getQtdMaxDependenteLegado()) {
				CatalogoPRSException ex = new CatalogoPRSException("O limite de dependentes no Cat&aacute;logo deve ser menor ou igual ao limite de dependentes no Sistema Origem.");
				this.CatalogoPRSExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), response);
				return null;
			}else {
				AlterarPlanoParametrizacaoRequest alterarPlanoParametrizacaoRequest = new AlterarPlanoParametrizacaoRequest();
				ParametroAlterarPlano parametroAlterarPlano = new ParametroAlterarPlano();
				
				if(formulario.getIdPlano() != null) {
					parametroAlterarPlano.setIdPlano(formulario.getIdPlano());
				}
				if(formulario.getQtdMaxDependenteCatalogo() != null) {
					parametroAlterarPlano.setQtdMaxDependentes(formulario.getQtdMaxDependenteCatalogo());
				}
				if(formulario.getIdTpPlano() != null && formulario.getIdTpPlano() > 0) {
					parametroAlterarPlano.setIdTipoPlano(formulario.getIdTpPlano());
				}
				if(formulario.getInDisponibilidadeCatalogo() != null && formulario.getInDisponibilidadeCatalogo().trim().length() > 0) {
					ParametroAlterarPlanoInDisponibilidadeCatalogo inDisponibilidadeCatalogo = null;
					if(formulario.getInDisponibilidadeCatalogo().equals("S")){
						parametroAlterarPlano.setInDisponibilidadeCatalogo(inDisponibilidadeCatalogo.S);
					}else{
						parametroAlterarPlano.setInDisponibilidadeCatalogo(inDisponibilidadeCatalogo.N);
					}
				}
				if(formulario.getOpAtivaWiFi() != null && formulario.getOpAtivaWiFi().trim().length() > 0) {
					ParametroAlterarPlanoAtivaWiFi ativaWifi = null;
					if(formulario.getOpAtivaWiFi().equals("S")){
						parametroAlterarPlano.setAtivaWiFi(ativaWifi.S);
					}else{
						parametroAlterarPlano.setAtivaWiFi(ativaWifi.N);
					}
				}
			
				alterarPlanoParametrizacaoRequest.setParametroAlterarPlano(parametroAlterarPlano);
				PlanoPortTypeProxy planoPortTypeProxy = new PlanoPortTypeProxy();
				try{
					planoPortTypeProxy.alterarPlanoParametrizacao(alterarPlanoParametrizacaoRequest, this.getUserName(), this.getPassword());
				}catch(AxisFault ex){
					this.AxisFaultExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), ex.getMessage(), form, response);
					return null;
				}
				try {
					response.setContentType("text/javascript");
					PrintWriter out = response.getWriter();
					out.println("$('botao_pesquisar_parametrizacao').onclick();");
					out.println("$('servicos_update').hide();");
					out.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		}
		
	public ActionForward gravarAtivacaoWiFi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ServicoForm servicoForm = (ServicoForm) form;
		
		AlterarAtivacaoWiFiRequest alterarAtivacaoWiFiRequest = new AlterarAtivacaoWiFiRequest();
		
		ParamAlterarAtivacaoWiFi paramAlterarAtivacaoWiFi = new ParamAlterarAtivacaoWiFi();
		
		Long[] arrayServicos = servicoForm.getIdsServicos();
		int count = arrayServicos.length;
		
		long[] listaIdServico = new long[count];
		
		for (int i=0;i < count;i++) {
			listaIdServico[i] = arrayServicos[i];
		}		
				
		String opAtivaWiFi = servicoForm.getOpAtivaWiFi();
		
		if(opAtivaWiFi.equals("s") || opAtivaWiFi.equals("S")){
			paramAlterarAtivacaoWiFi.setStatus(ParamAlterarAtivacaoWiFiStatus.S);
	   }
	   else {
		   paramAlterarAtivacaoWiFi.setStatus(ParamAlterarAtivacaoWiFiStatus.N);
	   }
					
		if(listaIdServico.length > 0) {
			paramAlterarAtivacaoWiFi.setListaIdServico(listaIdServico);
		}
		
		alterarAtivacaoWiFiRequest.setParamAlterarAtivacaoWiFi(paramAlterarAtivacaoWiFi);
		
		ServicoPortTypeProxy servicoPortTypeProxy = new ServicoPortTypeProxy();
				
		try {
			servicoPortTypeProxy.alterarAtivacaoWiFi(alterarAtivacaoWiFiRequest, this.getUserName(), this.getPassword());				
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), ex.getMessage(),
					servicoForm, response);
			return null;
		}					
		
		try {			
			response.setContentType("text/javascript");
			PrintWriter out = response.getWriter();
			out.println("$('botao_pesquisar_parametrizacao').onclick();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
		
	}
	
	public ActionForward gravarAtivacaoWiFiPlanos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ServicoForm formulario = (ServicoForm)form;
		br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.AlterarAtivacaoWiFiRequest alterarAtivacaoWiFiRequest = new br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.AlterarAtivacaoWiFiRequest();
		br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParamAlterarAtivacaoWiFi paramAlterarAtivacaoWiFi = new br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParamAlterarAtivacaoWiFi();
	
		Long[] arrayPlanos = formulario.getIdsPlanos();
		long [] addPlanos = new long[arrayPlanos.length];
		for (int i=0; i<arrayPlanos.length; i++) {
			 addPlanos[i] = arrayPlanos[i];
		}
		
		if(addPlanos.length > 0) {
			paramAlterarAtivacaoWiFi.setListaIdPlano(addPlanos);
		}
		
		if(formulario.getOpAtivaWiFi() != null && formulario.getOpAtivaWiFi().trim().length() > 0) {
			br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParamAlterarAtivacaoWiFiStatus opAtivaWifi = null;
			if(formulario.getOpAtivaWiFi().equals("S")){
				paramAlterarAtivacaoWiFi.setStatus(opAtivaWifi.S);
			}else{
				paramAlterarAtivacaoWiFi.setStatus(opAtivaWifi.N);
			}
		}
		
		alterarAtivacaoWiFiRequest.setParamAlterarAtivacaoWiFi(paramAlterarAtivacaoWiFi);
		PlanoPortTypeProxy planoPortTypeProxy = new PlanoPortTypeProxy();
		
		try {
			planoPortTypeProxy.alterarAtivacaoWiFi(alterarAtivacaoWiFiRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ParametrizacaoPlanoServicoAction.class.getName(), ex.getMessage(),
					formulario, response);
			return null;
		}
		try {
			response.setContentType("text/javascript");
			PrintWriter out = response.getWriter();
			out.println("$('botao_pesquisar_parametrizacao').onclick();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
				
	
	

	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	






	
	
	

	

	

	

	
	
	
	
	
	
	
	
	
	
	

	

	

	

}