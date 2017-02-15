package br.com.vivo.catalogoPRS.pageflows.consulta.servicos.consultaServicos;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.indracompany.catalogo.datalayer.ServicoTarifa;

import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.param.matrizOferta.cadastro.CadastroCabecalhoMatrizOfertaAction;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;
import br.com.vivo.catalogoPRS.util.PlataformaEnum;

import java.io.Serializable;
import org.apache.axis.AxisFault;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.catalogoPRS.pageflows.shared.form.ServicoForm;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.PaginacaoOut;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarDetalhesServico;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarListaAtributoPorIdServico;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarListaRestricoesPorServico;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarListaServicoTarifaPorIdServico;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarListaGrupoServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarListaGrupoServicoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.GrupoServicoPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ListaServicoServico;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ParametrosListarGrupoServico;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ResultadoListarGrupoServicoCategoria;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.BuscarListaPlataformaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.BuscarListaPlataformaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.PlataformaPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.ResultadoBuscarListaPlataformaPlataforma;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarDetalhesServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarDetalhesServicoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaAtributoPorIdServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaAtributoPorIdServicoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaRestricoesPorServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaRestricoesPorServicoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaServicoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaServicoTarifaPorIdServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaServicoTarifaPorIdServicoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ExportarServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ExportarServicoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ListaDetalhesServico;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ListaServicoTarifaServicoTarifa;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.PaginacaoIn;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosListarServico;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosListarServicoParametrosServicoIn;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarDetalhesServico;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaAtributoPorIdServicoAtributo;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServico;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoListaServicoRetornoServico;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoTarifaPorIdServico;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoExportarServicoServico;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoExportarServicoServicoListaAtributoAtributo;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoExportarServicoServicoListaAtributoAtributoListaAtributoValorAtributoValor;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoExportarServicoServicoListaPlanosPlano;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoRestricoesPorServico;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoRestricoesPorServicoListaRestricoesPorServicoRetornoRestricoesPorServico;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ServicoPortTypeProxy;
import edu.emory.mathcs.backport.java.util.Arrays;


public class ConsultaServicosAction extends BaseMappingDispatchAction implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";				
	
	@SuppressWarnings("unchecked")
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		ServicoForm servicoForm = (ServicoForm) form;
		
		PlataformaPortTypeProxy plataformaPortTypeProxy = new PlataformaPortTypeProxy();
		BuscarListaPlataformaRequest buscarListaPlataformaRequest = new BuscarListaPlataformaRequest();
		BuscarListaPlataformaResponse buscarListaPlataformaResponse = null;		
		
		try {
			buscarListaPlataformaResponse = plataformaPortTypeProxy.buscarListaPlataforma(buscarListaPlataformaRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ConsultaServicosAction.class.getName(), ex.getMessage(), servicoForm, response);
			return null;
		}
		
		List<ResultadoBuscarListaPlataformaPlataforma> plataformaList = Arrays.asList(buscarListaPlataformaResponse.getResultadoBuscarListaPlataforma());
		request.setAttribute("plataformas", plataformaList);
			
		return mapping.findForward(SUCCESS);
	}	

	public ActionForward ConsultaServicos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {				
			
		return mapping.findForward(SUCCESS);
	}
		
	public ActionForward pesquisar(ActionMapping mapping, ActionForm form, HttpServletRequest request,	HttpServletResponse response) throws Exception {	
				
		ServicoForm servicoForm = (ServicoForm)form;
		
		ParametrosListarServicoParametrosServicoIn parametrosListarServicoParametrosServicoIn = new ParametrosListarServicoParametrosServicoIn();
		PaginacaoIn paginacaoIn = new PaginacaoIn();
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
				
		if(servicoForm.getPaginaSolicitada()==null)
			paginacaoIn.setPaginaSolicitada(1);
		else
			paginacaoIn.setPaginaSolicitada(servicoForm.getPaginaSolicitada());		
		
		parametrosListarServicoParametrosServicoIn.setPaginacaoIn(paginacaoIn);
		
		if (servicoForm.getCodigoServico() != null) {
			parametrosListarServicoParametrosServicoIn.setCdcodigo(servicoForm.getCodigoServico());
		}
		if (servicoForm.getIdGrupoServico() != null) {
			parametrosListarServicoParametrosServicoIn.setIdCategoria(servicoForm.getIdGrupoServico());
		}
		if (servicoForm.getIdPlataforma() != null) {
			parametrosListarServicoParametrosServicoIn.setIdPlataforma(servicoForm.getIdPlataforma());
		}
		if (servicoForm.getNomePlano() != null) {
			parametrosListarServicoParametrosServicoIn.setNmPlano(servicoForm.getNomePlano());
		}
		if (servicoForm.getNomeServico() != null) {
			parametrosListarServicoParametrosServicoIn.setNmServico(servicoForm.getNomeServico());
		}
		if(servicoForm.getIdSistema() != null) {
			parametrosListarServicoParametrosServicoIn.setIdSistema(servicoForm.getIdSistema().toString());
		}
				
		request.setAttribute("idPlataforma", servicoForm.getIdPlataforma());
		request.setAttribute("idSistema", servicoForm.getIdSistema());				
				
		ServicoPortTypeProxy servicoPortTypeProxy = new ServicoPortTypeProxy();
		BuscarListaServicoRequest buscarListaServicoRequest = new BuscarListaServicoRequest();
		
		ParametrosListarServico parametrosListarServico = new ParametrosListarServico();
		parametrosListarServico.setParametrosServicoIn(parametrosListarServicoParametrosServicoIn);
		
		
		buscarListaServicoRequest.setParametrosListarServico(parametrosListarServico);																
						
		BuscarListaServicoResponse buscarListaServicoResponse = null;
						
		try {
			buscarListaServicoResponse = servicoPortTypeProxy.buscarListaServico(buscarListaServicoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ConsultaServicosAction.class.getName(), ex.getMessage(),	servicoForm, response);
			return null;
		}		
		
		ResultadoBuscarListaServico resultadoBuscarListaServico = new ResultadoBuscarListaServico();
		resultadoBuscarListaServico = buscarListaServicoResponse.getResultadoBuscarListaServico();
				
		PaginacaoOut paginacaoOut = resultadoBuscarListaServico.getPaginacaoOut();
		if (paginacaoOut != null) {
			this.tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(),request);
		}
				
		if (buscarListaServicoResponse != null) {			
			ResultadoBuscarListaServicoListaServicoRetornoServico[] arrayListaServicos = resultadoBuscarListaServico.getListaServico();
			
			Map<String, String> mapaDataIncial = new HashMap<String, String>(); 
			Map<String, String> mapaDataFinal = new HashMap<String, String>(); 			
			
			if (arrayListaServicos != null && arrayListaServicos.length > 0) {
				
				for (int i = 0; i < arrayListaServicos.length; i++ ) {								
					
					String nmComercial = arrayListaServicos[i].getNmComercial();	
					
					
					if(arrayListaServicos[i].getDtInicial() != null)	
					{		
						Calendar dtInicial = arrayListaServicos[i].getDtInicial();
						Date dataIni = dtInicial.getTime();
						DateFormat dfIni = new SimpleDateFormat("dd/MM/yyyy");
						String reportDateIni = dfIni.format(dataIni);
						mapaDataIncial.put(nmComercial, reportDateIni);
					}
					
					if(arrayListaServicos[i].getDtFinal() != null)	
					{
						Calendar dtFinal = arrayListaServicos[i].getDtFinal();
						Date dataFim = dtFinal.getTime();
						DateFormat dfFim = new SimpleDateFormat("dd/MM/yyyy");
						String reportDateFim = dfFim.format(dataFim);
						mapaDataFinal.put(nmComercial, reportDateFim);
					}
				}			
			}
			
			servicoForm.setMapaDataIncial(mapaDataIncial);
			servicoForm.setMapaDataFinal(mapaDataFinal);
			servicoForm.setArrayListaServicos(arrayListaServicos);
		}

										
		return mapping.findForward(SUCCESS);
	}
		
	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupAtributosServico.jsp") })
	@SuppressWarnings("unchecked")
	public ActionForward abrirPopupAtributosServico(ActionMapping mapping, ActionForm form, HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		ServicoForm servicoForm = (ServicoForm)form;
		
		String idServico = request.getParameter("id_servico");
		forwardParameter(request, "nome_servico");
		
		BuscarListaAtributoPorIdServicoRequest buscarListaAtributoPorIdServicoRequest = new BuscarListaAtributoPorIdServicoRequest();
		ParametrosBuscarListaAtributoPorIdServico parametrosBuscarListaAtributoPorIdServico = new ParametrosBuscarListaAtributoPorIdServico();
		
		ListaDetalhesServico listaDetalhesServico = new ListaDetalhesServico();
		listaDetalhesServico.setIdServico(Long.valueOf(idServico));
		
		parametrosBuscarListaAtributoPorIdServico.setListaDetalhesServico(listaDetalhesServico);
		buscarListaAtributoPorIdServicoRequest.setParametrosBuscarListaAtributoPorIdServico(parametrosBuscarListaAtributoPorIdServico);
		
			
		BuscarListaAtributoPorIdServicoResponse buscarListaAtributoPorIdServicoResponse = null;
		
		ServicoPortTypeProxy servicoPortTypeProxy = new ServicoPortTypeProxy();
		
		try {
			buscarListaAtributoPorIdServicoResponse = servicoPortTypeProxy.buscarListaAtributoPorIdServico(buscarListaAtributoPorIdServicoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ConsultaServicosAction.class.getName(), ex.getMessage(), servicoForm, response);
			return null;
		}		
		
		if (buscarListaAtributoPorIdServicoResponse != null) {				 
			List<ResultadoBuscarListaAtributoPorIdServicoAtributo> atributoList = Arrays.asList(buscarListaAtributoPorIdServicoResponse.getResultadoBuscarListaAtributoPorIdServico());
			servicoForm.setAtributoList(atributoList);			
		}
		
		return mapping.findForward(SUCCESS);
	}
		
	@SuppressWarnings("unchecked")
	public ActionForward abrirPopupDetalhesServico(ActionMapping mapping, ActionForm form, HttpServletRequest request,	HttpServletResponse response) throws Exception {	
							
		ServicoForm servicoForm = (ServicoForm)form;
		
		String idServico = request.getParameter("id_servico");
		
		BuscarDetalhesServicoRequest buscarDetalhesServicoRequest = new BuscarDetalhesServicoRequest();
		ListaDetalhesServico listaDetalhesServico = new ListaDetalhesServico();
		listaDetalhesServico.setIdServico(Long.parseLong(idServico));		
		
		
		ParametrosBuscarDetalhesServico parametrosBuscarDetalhesServico = new ParametrosBuscarDetalhesServico();
		
		parametrosBuscarDetalhesServico.setListaDetalhesServico(listaDetalhesServico);
		buscarDetalhesServicoRequest.setParametrosBuscarDetalhesServico(parametrosBuscarDetalhesServico);
				
		BuscarDetalhesServicoResponse buscarDetalhesServicoResponse = null;		
		ServicoPortTypeProxy servicoPortTypeProxy = new ServicoPortTypeProxy();							
		
		try {
			buscarDetalhesServicoResponse = servicoPortTypeProxy.buscarDetalhesServico(buscarDetalhesServicoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ConsultaServicosAction.class.getName(), ex.getMessage(),	servicoForm, response);
			return null;
		}	
			
		if (buscarDetalhesServicoResponse.getResultadoBuscarDetalhesServico() != null) {				 
			ResultadoBuscarDetalhesServico listaServico = buscarDetalhesServicoResponse.getResultadoBuscarDetalhesServico();
			List<ListaServicoServico> detalhesServicoList = Arrays.asList(listaServico.getListaServico());
			
			servicoForm.setDetalhesServicoList(detalhesServicoList);
			//request.setAttribute("detalhesServico", listaServico.getListaServico());
		}					
		return mapping.findForward(SUCCESS);
	}
		
	@SuppressWarnings("unchecked")
	public ActionForward exportar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ServicoForm servicoForm = (ServicoForm)form;
		
		if(servicoForm.getIdServicoSelecionados() == null || servicoForm.getIdServicoSelecionados().length == 0)
		{			
			CatalogoPRSException ex = new CatalogoPRSException("Obrigat&oacute;rio selecionar dados para exporta&ccedil;&atilde;o.");
			this.CatalogoPRSExceptionHandler(ex, ConsultaServicosAction.class.getName(), response);
			return null;
		}
						
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		StringBuffer sb = new StringBuffer();
		String periodo;
		String atributos;
				
		ExportarServicoRequest exportarServicoRequest = new ExportarServicoRequest();				
		
		long[]parametrosExportarServico = new long[servicoForm.getIdServicoSelecionados().length];
		
		for(int i=0;i<servicoForm.getIdServicoSelecionados().length;i++){
			parametrosExportarServico[i]=Long.valueOf(servicoForm.getIdServicoSelecionados()[i]);
		}
		exportarServicoRequest.setParametrosExportarServico(parametrosExportarServico);
		
		ExportarServicoResponse exportarServicoResponse = null;
		
		ServicoPortTypeProxy servicoPortTypeProxy = new ServicoPortTypeProxy();
		
			try {
				exportarServicoResponse = servicoPortTypeProxy.exportarServico(exportarServicoRequest, this.getUserName(), this.getPassword());
			} catch (AxisFault ex) {
				this.AxisFaultExceptionHandler(ex, ConsultaServicosAction.class.getName(), ex.getMessage(),	servicoForm, response);				
				return null;
			}					
			
		List<ResultadoExportarServicoServico> servicoList = Arrays.asList(exportarServicoResponse.getResultadoExportarServico());
		
		
		
		        // importar dados Plataforma Pos-Pago.
				if(servicoForm.getIdPlataforma() != 1 && servicoForm.getIdSistema() == null) {
					sb.append("Nome Comercial;Nome Técnico;Categoria Catálogo;Categoria Legado;Tipo de Serviço no Catálogo;Quant. Min. de Ativação no Catálogo;Quant. Min. de Ativação no Legado;Quant. Max. de Ativação no Catálogo;");
					sb.append("Quant. Max. de Ativação no Legado;Serviço Mestre;Código Anatel;Descrição da Tarifa;Tipo de Débito;Valor de Tarifa;Unidade da Tarifa;Período;Atributos;Disponibilidade\n");
					
					for (ResultadoExportarServicoServico servico : servicoList) {
						List<ServicoTarifa>  servicoTarifa = new ArrayList<ServicoTarifa>();
						if(servico.getListaServicoTarifa() != null) {
							servicoTarifa = Arrays.asList(servico.getListaServicoTarifa());
						}else {
							servicoTarifa.add(null);
						}
						for (ServicoTarifa tarifa : servicoTarifa) {
							sb.append(servico.getServico() != null ? servico.getServico() : "");
							sb.append(";");
							sb.append(servico.getCdCodigo());
							sb.append(";");
							//sb.append(servico.getCategoriaCatalogo());
							sb.append(";");
							sb.append(servico.getCategoria());
							sb.append(";");
							sb.append(servico.getServico());
							sb.append(";");
							sb.append(servico.getQtdMinNativCatalogo() != null ? servico.getQtdMinNativCatalogo() : "");
							sb.append(";");
							sb.append(servico.getQtdMinNativLegado() != null ? servico.getQtdMinNativLegado() : "");
							sb.append(";");
							sb.append(servico.getQtdMaxNativCatalogo() != null ? servico.getQtdMaxNativCatalogo() : "");
							sb.append(";");
							sb.append(servico.getQtdMaxNativLegado() != null ? servico.getQtdMaxNativLegado() : "");
							sb.append(";");
							sb.append(servico.getIsServicoMestre());
							sb.append(";");
							sb.append(servico.getCdAnatel());
							sb.append(";");
							sb.append(tarifa != null ? tarifa.getDscTarifa() : "");
							sb.append(";");
							sb.append(tarifa != null ? tarifa.getTpDebitoTarifa() : "");
							sb.append(";");
							sb.append(tarifa != null ? tarifa.getPrecoBase() : "");
							sb.append(";");
							sb.append(tarifa != null ? tarifa.getTpUnidadeTarifa() : "");
							sb.append(";");
							if(servico.getDtInicial() != null || servico.getDtFinal() != null){
								periodo = (servico.getDtInicial() != null) ? sdf.format(servico.getDtInicial().getTime()) : "";
								periodo += " a ";
								periodo += (servico.getDtFinal() != null) ? sdf.format(servico.getDtFinal().getTime()) : "";
								sb.append(periodo);
							}
							sb.append(";");
							atributos = "";
							if(servico.getListaAtributo() != null){
								int i = 0;
								for (ResultadoExportarServicoServicoListaAtributoAtributo atributo : servico.getListaAtributo()) {
									if(i++ > 0)
										atributos += " / ";
									atributos +=atributo.getSvcattrnm();
									if(atributo.getListaAtributoValor()!=null){
										atributos += "(";
										int j = 0;
										List<ResultadoExportarServicoServicoListaAtributoAtributoListaAtributoValorAtributoValor> atributoValorList = Arrays.asList(atributo.getListaAtributoValor());
										for (ResultadoExportarServicoServicoListaAtributoAtributoListaAtributoValorAtributoValor valor : atributoValorList) {
											if(j++>0)
												atributos += ",";
											atributos += valor.getValor();
										}
										atributos +=")";
									}
									
								}
								sb.append(atributos);
							}
							sb.append(";");
							sb.append((servico.getInDisponivel() != null) ? (servico.getInDisponivel().equalsIgnoreCase("S") ? "Sim" : "Não") : "Não");  
							sb.append(";");
							sb.append("\n");
						}
					}
				}
				// importar dados Plataforma Pré-Pago.
				else {
					sb.append("Categoria Legado;Categoria Catálogo;Nome do Serviço;Tipo de Serviço;Descrição do Serviço;Código do Serviço;");
					sb.append("Codigo Anatel;Valor da Tarifa;Tipo Tarifa;Período;Atributos;Nome do Plano;Disponibilidade\n");
					for (ResultadoExportarServicoServico servico : servicoList) {
						List<ResultadoExportarServicoServicoListaPlanosPlano>  planosList = new ArrayList<ResultadoExportarServicoServicoListaPlanosPlano>();
						if(servico.getListaPlanos() != null && servico.getListaPlanos().length > 0) {
							planosList = Arrays.asList(servico.getListaPlanos());
						}else {
							planosList.add(null);
						}
						for (ResultadoExportarServicoServicoListaPlanosPlano plano : planosList) {
							sb.append(servico.getCategoria());
							sb.append(";");
							//sb.append(servico.getCategoriaCatalogo());
							sb.append(";");
							sb.append(servico.getServico() != null ? servico.getServico() : "");
							sb.append(";");
							//sb.append(servico.getDescTipoServico());
							sb.append(";");
							sb.append(servico.getDescricao());
							sb.append(";");
							sb.append(servico.getCdCodigo());
							sb.append(";");
							sb.append(servico.getCdAnatel());
							sb.append(";");
							sb.append(servico.getValor());
							sb.append(";");
							sb.append(servico.getTpTarifa() != null ? servico.getTpTarifa() : "");
							sb.append(";");
							if(servico.getDtInicial() != null || servico.getDtFinal() != null){
								periodo = (servico.getDtInicial() != null) ? sdf.format(servico.getDtInicial().getTime()) : "";
								periodo += " a ";
								periodo += (servico.getDtFinal() != null) ? sdf.format(servico.getDtFinal().getTime()) : "";
								sb.append(periodo);
							}
							sb.append(";");
							atributos = "";
							if(servico.getListaAtributo() != null){
								int i = 0;
								for (ResultadoExportarServicoServicoListaAtributoAtributo atributo : servico.getListaAtributo()) {
									if(i++ > 0)
										atributos += " / ";
									atributos +=atributo.getSvcattrnm();
									if(atributo.getListaAtributoValor()!=null && atributo.getListaAtributoValor().length > 0){
										atributos += "(";
										int j = 0;
										List<ResultadoExportarServicoServicoListaAtributoAtributoListaAtributoValorAtributoValor> atributoValorList = Arrays.asList(atributo.getListaAtributoValor());
										for (ResultadoExportarServicoServicoListaAtributoAtributoListaAtributoValorAtributoValor valor : atributoValorList) {
											if(j++>0)
												atributos += ",";
											atributos += valor.getValor();
										}
										atributos +=")";
									}
								}
								sb.append(atributos);
							}
							sb.append(";");
							sb.append(plano != null ? plano.getNmPlano() : "");
							sb.append(";");
							sb.append((servico.getInDisponivel() != null) ? (servico.getInDisponivel().equalsIgnoreCase("S") ? "Sim" : "Não") : "Não");  
							sb.append(";");
							sb.append("\n");
						}
					}
				}
	
				
				response.setContentType("application/vnd.ms-excel;charset=iso-8859-1");
				response.addHeader("Content-Disposition", "attachment;filename=Servicos.csv");
				response.setCharacterEncoding("ISO-8859-1");
				PrintWriter out = response.getWriter();

				out.write(sb.toString());
				out.flush();

				return null;							
	}
			
	public ActionForward abrirPopupTarifaServico(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {		
		
		ServicoForm servicoForm = (ServicoForm)form;
		
		String idServicoString = (String)request.getParameter("id_servico");
		
		
		if(!idServicoString.equals(null)){
			Long idServico = Long.parseLong(idServicoString);			
			ServicoPortTypeProxy servicoPortTypeProxy = new ServicoPortTypeProxy();
			
			BuscarListaServicoTarifaPorIdServicoRequest buscarListaServicoTarifaPorIdServicoRequest = new BuscarListaServicoTarifaPorIdServicoRequest();
			ParametrosBuscarListaServicoTarifaPorIdServico parametrosBuscarListaServicoTarifaPorIdServico = new ParametrosBuscarListaServicoTarifaPorIdServico();
			parametrosBuscarListaServicoTarifaPorIdServico.setIdServico(idServico);
			
			buscarListaServicoTarifaPorIdServicoRequest.setParametrosBuscarListaServicoTarifaPorIdServico(parametrosBuscarListaServicoTarifaPorIdServico);
			BuscarListaServicoTarifaPorIdServicoResponse buscarListaServicoTarifaPorIdServicoResponse = null;			
			
			try {
				buscarListaServicoTarifaPorIdServicoResponse = servicoPortTypeProxy.buscarListaServicoTarifaPorIdServico(buscarListaServicoTarifaPorIdServicoRequest,this.getUserName(), this.getPassword());
			} catch (AxisFault ex) {
				this.AxisFaultExceptionHandler(ex, ConsultaServicosAction.class.getName(), ex.getMessage(),	servicoForm, response);
				return null;
			}					
			
			ResultadoBuscarListaServicoTarifaPorIdServico resultadoBuscarListaServicoTarifaPorIdServico = buscarListaServicoTarifaPorIdServicoResponse.getResultadoBuscarListaServicoTarifaPorIdServico();
			
			ListaServicoTarifaServicoTarifa[] listaServicoTarifa = resultadoBuscarListaServicoTarifaPorIdServico.getListaServicoTarifa();
			
			if(buscarListaServicoTarifaPorIdServicoResponse!=null){
				//request.setAttribute("tarifas", listaServicoTarifa);
				servicoForm.setListaServicoTarifa(listaServicoTarifa);
			}						
		}			
		return mapping.findForward(SUCCESS);
	}
		
	@SuppressWarnings("unchecked")
	public ActionForward listarCategorias(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ServicoForm servicoForm = (ServicoForm)form;
		
		String idPlataformaString = request.getParameter("idPlataforma");
		String idSistemaString = request.getParameter("idSistema");
		Long idPlataforma = Long.parseLong(idPlataformaString);
		Long idSistema = (idSistemaString != null && idSistemaString.trim().length() != 0) ? Long.parseLong(idSistemaString) : null;
		
		GrupoServicoPortTypeProxy grupoServicoPortTypeProxy = new GrupoServicoPortTypeProxy();
		BuscarListaGrupoServicoRequest buscarListaGrupoServicoRequest = new BuscarListaGrupoServicoRequest();		
		BuscarListaGrupoServicoResponse buscarListaGrupoServicoResponse = null;
			
		ParametrosListarGrupoServico parametrosListarGrupoServico = new ParametrosListarGrupoServico();
												
		if(PlataformaEnum.PREPAGO.equals(idPlataforma)){
            parametrosListarGrupoServico.setIndCatalogo(new Long(2));
	      }else if(PlataformaEnum.POSPAGO.equals(idPlataforma)){
	            parametrosListarGrupoServico.setIndCatalogo(new Long(1));
	      }else if(PlataformaEnum.CONTROLE.equals(idPlataforma)){
            if(idSistema == null)
                  return null;
            if(idSistema == 3)
                  parametrosListarGrupoServico.setIndCatalogo(new Long(2));
            if(idSistema == 2)
                  parametrosListarGrupoServico.setIndCatalogo(new Long(1));
      }
		buscarListaGrupoServicoRequest.setParametrosListarGrupoServico(parametrosListarGrupoServico);	
								
		try {
			buscarListaGrupoServicoResponse  = grupoServicoPortTypeProxy.buscarListaGrupoServico(buscarListaGrupoServicoRequest,
					this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ConsultaServicosAction.class.getName(), ex.getMessage(),
					servicoForm, response);
			return null;
		}
		
		List<ResultadoListarGrupoServicoCategoria> resultadoListarGrupoServicoCategoria =  Arrays.asList(buscarListaGrupoServicoResponse.getResultadoListarGrupoServico());
		
		
		JSONObject resultadoJSON = new JSONObject();
		String result = "";
		JSONArray jsonArrayCategorias = new JSONArray();
		try {
			for (ResultadoListarGrupoServicoCategoria categoria : resultadoListarGrupoServicoCategoria) {
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

	@SuppressWarnings("unchecked")
	public ActionForward listarRestricoes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		ServicoForm servicoForm = (ServicoForm)form;
		
		if(servicoForm.getIdServicoSelecionados() == null || servicoForm.getIdServicoSelecionados().length == 0 ) {
			CatalogoPRSException ex = new CatalogoPRSException("N&atilde;o h&aacute; Servi&ccedil;os selecionados");
			this.CatalogoPRSExceptionHandler(ex, CadastroCabecalhoMatrizOfertaAction.class.getName(), response);
			return null;
		}
			
		
		
		request.setAttribute("idPlataforma", servicoForm.getIdPlataforma());
		request.setAttribute("idSistema", servicoForm.getIdSistema());
		
		BuscarListaRestricoesPorServicoRequest buscarListaRestricoesPorServicoRequest = new BuscarListaRestricoesPorServicoRequest();			
		
		ParametrosBuscarListaRestricoesPorServico parametrosBuscarListaRestricoesPorServico = new ParametrosBuscarListaRestricoesPorServico();
		parametrosBuscarListaRestricoesPorServico.setIdPlataforma(servicoForm.getIdPlataforma());
				
		br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoIn paginacaoIn = new br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoIn();
					
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		if(servicoForm.getPaginaSolicitada() != null) {
			paginacaoIn.setPaginaSolicitada(servicoForm.getPaginaSolicitada());
		}else {
			paginacaoIn.setPaginaSolicitada(1);
		}			
				
		ServicoPortTypeProxy servicoPortTypeProxy = new ServicoPortTypeProxy();
		BuscarListaRestricoesPorServicoResponse buscarListaRestricoesPorServicoResponse = null;
				
		long[]parametrosListarRestricoes = new long[servicoForm.getIdServicoSelecionados().length];
		
		for(int i=0;i<servicoForm.getIdServicoSelecionados().length;i++){
			parametrosListarRestricoes[i]=Long.valueOf(servicoForm.getIdServicoSelecionados()[i]);
		}
									
		parametrosBuscarListaRestricoesPorServico.setIdServico(parametrosListarRestricoes);		
		
		parametrosBuscarListaRestricoesPorServico.setPaginacaoIn(paginacaoIn);
							
		buscarListaRestricoesPorServicoRequest.setParametrosBuscarListaRestricoesPorServico(parametrosBuscarListaRestricoesPorServico);
		
		try {
			buscarListaRestricoesPorServicoResponse = servicoPortTypeProxy.buscarListaRestricoesPorServico(buscarListaRestricoesPorServicoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ConsultaServicosAction.class.getName(), ex.getMessage(), servicoForm, response);
			return null;
		}		
		
		
		PaginacaoOut paginacaoOut = new PaginacaoOut();		
		this.tratarPaginacao(paginacaoOut,  ApplicationConfiguration.getElementosPorPagina(), request);
		
		ResultadoRestricoesPorServico resultadoRestricoesPorServico = buscarListaRestricoesPorServicoResponse.getResultadoRestricoesPorServico();
		
		if(buscarListaRestricoesPorServicoResponse != null) {
			List<ResultadoRestricoesPorServicoListaRestricoesPorServicoRetornoRestricoesPorServico> restricoesPorServicoList = Arrays.asList(resultadoRestricoesPorServico.getListaRestricoesPorServico());
			request.setAttribute("variaveis",  restricoesPorServicoList);
		}
					
		return mapping.findForward(SUCCESS);
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward exportarRestricoes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {				
			
		ServicoForm servicoForm = (ServicoForm)form;
		if(servicoForm.getIdsServicos() == null || servicoForm.getIdsServicos().length ==0) {
			CatalogoPRSException ex = new CatalogoPRSException("Obrigat&oacute;rio selecionar dados para exporta&ccedil;&atilde;o.");
			this.CatalogoPRSExceptionHandler(ex, ConsultaServicosAction.class.getName(), response);
			return null;
			
		}			
		
		BuscarListaRestricoesPorServicoRequest buscarListaRestricoesPorServicoRequest = new BuscarListaRestricoesPorServicoRequest();
		
		ParametrosBuscarListaRestricoesPorServico parametrosBuscarListaRestricoesPorServico = new ParametrosBuscarListaRestricoesPorServico();
		br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoIn paginacaoIn = new br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoIn();
		
		
	    long[] parametrosBuscarListaRestricoes = new long[servicoForm.getIdServicoSelecionados().length];
		
		for(int i=0;i<servicoForm.getIdServicoSelecionados().length;i++){
			parametrosBuscarListaRestricoes[i]=Long.valueOf(servicoForm.getIdServicoSelecionados()[i]);
		}
				
		parametrosBuscarListaRestricoesPorServico.setIdPlataforma(servicoForm.getIdPlataforma());	
		parametrosBuscarListaRestricoesPorServico.setIdServico(parametrosBuscarListaRestricoes);
		int linhasLidas = 0;
		paginacaoIn.setItensPorPagina(1000);
		int currentPage = 1;
		paginacaoIn.setPaginaSolicitada(currentPage++);
		StringBuffer sb = new StringBuffer();
		sb.append("Nome do Serviço no Catálogo;Segemento;Tipo de Cliente;Tipo de Assinatura;Plataforma;Tecnologia;Canal;UF;DDD\n");
		
		parametrosBuscarListaRestricoesPorServico.setPaginacaoIn(paginacaoIn);
		
		buscarListaRestricoesPorServicoRequest.setParametrosBuscarListaRestricoesPorServico(parametrosBuscarListaRestricoesPorServico);
		
		ServicoPortTypeProxy servicoPortTypeProxy = new ServicoPortTypeProxy();
		BuscarListaRestricoesPorServicoResponse buscarListaRestricoesPorServicoResponse = null;
		
		try {
			buscarListaRestricoesPorServicoResponse = servicoPortTypeProxy.buscarListaRestricoesPorServico(buscarListaRestricoesPorServicoRequest,
					this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ConsultaServicosAction.class.getName(), ex.getMessage(),
					servicoForm, response);
			return null;
		}	
		while(true) {		
		ResultadoRestricoesPorServico resultadoRestricoesPorServico = buscarListaRestricoesPorServicoResponse.getResultadoRestricoesPorServico();				
		List<ResultadoRestricoesPorServicoListaRestricoesPorServicoRetornoRestricoesPorServico> restricoesPorServicoList = Arrays.asList(resultadoRestricoesPorServico.getListaRestricoesPorServico());
		linhasLidas += restricoesPorServicoList.size();
		for (ResultadoRestricoesPorServicoListaRestricoesPorServicoRetornoRestricoesPorServico servico : restricoesPorServicoList) {
			sb.append(servico.getNmComercial());
			sb.append(";");
			sb.append(servico.getSgSegmento());
			sb.append(";");
			sb.append(servico.getNmTipoCliente());
			sb.append(";");
			sb.append(servico.getDescTpAssinatura());
			sb.append(";");
			sb.append(servico.getNmPlataforma());
			sb.append(";");
			sb.append(servico.getNmTecnologia());
			sb.append(";");
			sb.append(servico.getNmCanal());
			sb.append(";");
			sb.append(servico.getNmUf());
			sb.append(";");
			sb.append(servico.getCodigoArea());
			sb.append(";");
			sb.append("\n");
		}
		
		if(linhasLidas >= resultadoRestricoesPorServico.getPaginacaoOut().getTotalRegistros()) {
			break;
		}else {
			
			buscarListaRestricoesPorServicoRequest.getParametrosBuscarListaRestricoesPorServico().getPaginacaoIn().setPaginaSolicitada(currentPage++);
			
			try {
				buscarListaRestricoesPorServicoResponse = servicoPortTypeProxy.buscarListaRestricoesPorServico(buscarListaRestricoesPorServicoRequest,
						this.getUserName(), this.getPassword());
			} catch (AxisFault ex) {
				this.AxisFaultExceptionHandler(ex, ConsultaServicosAction.class.getName(), ex.getMessage(),
						servicoForm, response);
				return null;
			}	
		  }
		}
		
		response.setContentType("application/vnd.ms-excel;charset=iso-8859-1");
		response.addHeader("Content-Disposition", "attachment;filename=Restricoes.csv");
		response.setCharacterEncoding("ISO-8859-1");
		PrintWriter out = response.getWriter();

		out.write(sb.toString());
		out.flush();

		return null;				
	}
}