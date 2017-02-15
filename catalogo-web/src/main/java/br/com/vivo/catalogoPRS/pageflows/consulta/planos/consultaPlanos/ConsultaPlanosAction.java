package br.com.vivo.catalogoPRS.pageflows.consulta.planos.consultaPlanos;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.axis.AxisFault;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.ConsultaPlanosForm;
import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;
import br.com.vivo.catalogoPRS.ws.catalogoCanal.sn.BuscarListaCanalRequest;
import br.com.vivo.catalogoPRS.ws.catalogoCanal.sn.BuscarListaCanalResponse;
import br.com.vivo.catalogoPRS.ws.catalogoCanal.sn.CanalPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoCanal.sn.ResultadoListaCanalCanal;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarDescricaoPlanoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarDescricaoPlanoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarListaPlanoPorIdRequest;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarListaPlanoPorIdResponse;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarListaVariaveisPorPlanoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarListaVariaveisPorPlanoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarPlanoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarPlanoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoIn;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarDescricaoPlano;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarListaVariaveisPorPlano;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarPlano;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.Plano;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PlanoPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlanoListaServicoServico;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaVariaveisPorPlano;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaVariaveisPorPlanoListaVariaveisPorPlanoRetornoVariaveisPlano;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarPlano;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarPlanoListaPlanoRetornoPlano;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.BuscarListaPlataformaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.BuscarListaPlataformaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.PlataformaPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.ResultadoBuscarListaPlataformaPlataforma;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarDetalhesServicoPorIdPlanoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarDetalhesServicoPorIdPlanoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ListaGrupoServicoCategoria;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarDetalhesServicoPorIdPlano;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ServicoPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaTecnologiaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaTecnologiaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarListaTecnologiaTecnologia;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.TecnologiaPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn.BuscarListaTipoClienteRequest;
import br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn.BuscarListaTipoClienteResponse;
import br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn.ResultadoBuscarListaTipoClienteTipoCliente;
import br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn.TipoClientePortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFComDDDPorIdPlanoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFComDDDPorIdPlanoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFComDDDRequest;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFComDDDResponse;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFPorIdPlanoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFPorIdPlanoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFRequest;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFResponse;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.ParametrosBuscarListaUfComDDDPorIdPlano;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.ParametrosBuscarListaUfComDDDPorIdPlanoListaUfComDDDPorIdPlano;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.ParametrosBuscarListaUfPorIdPlano;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.ParametrosBuscarListaUfPorIdPlanoListaUfPorIdPlano;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.ResultadoBuscarListaUFUF;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.UFPortTypeProxy;

public class ConsultaPlanosAction extends BaseMappingDispatchAction implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		ConsultaPlanosForm consultaPlanosForm = (ConsultaPlanosForm) form;
		
		PlataformaPortTypeProxy plataformaPortTypeProxy = new PlataformaPortTypeProxy();
		TecnologiaPortTypeProxy tecnologiaPortTypeProxy = new TecnologiaPortTypeProxy();
		TipoClientePortTypeProxy tipoClientePortTypeProxy = new TipoClientePortTypeProxy();

		
		BuscarListaPlataformaRequest buscarListaPlataformaRequest = new BuscarListaPlataformaRequest();
		BuscarListaPlataformaResponse buscarListaPlataformaResponse = new BuscarListaPlataformaResponse();
		try {
			buscarListaPlataformaResponse = plataformaPortTypeProxy.buscarListaPlataforma(buscarListaPlataformaRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, ConsultaPlanosAction.class.getName(), ex.getMessage(), consultaPlanosForm, response );
			return null;
		}
		List<ResultadoBuscarListaPlataformaPlataforma> plataformaList = Arrays.asList(buscarListaPlataformaResponse.getResultadoBuscarListaPlataforma());
		
		
		BuscarListaTecnologiaRequest buscarListaTecnologiaRequest = new BuscarListaTecnologiaRequest();
		BuscarListaTecnologiaResponse buscarListaTecnologiaResponse = new BuscarListaTecnologiaResponse();
		try {
			buscarListaTecnologiaResponse = tecnologiaPortTypeProxy.buscarListaTecnologia(buscarListaTecnologiaRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, ConsultaPlanosAction.class.getName(), ex.getMessage(), consultaPlanosForm, response );
			return null;
		}
		List<ResultadoBuscarListaTecnologiaTecnologia> tecnologiaList = Arrays.asList(buscarListaTecnologiaResponse.getResultadoBuscarListaTecnologia());
		
		
		BuscarListaTipoClienteRequest buscarListaTipoClienteRequest = new BuscarListaTipoClienteRequest();
		BuscarListaTipoClienteResponse buscarListaTipoClienteResponse = new BuscarListaTipoClienteResponse();
		try {
			buscarListaTipoClienteResponse = tipoClientePortTypeProxy.buscarListaTipoCliente(buscarListaTipoClienteRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, ConsultaPlanosAction.class.getName(), ex.getMessage(), consultaPlanosForm, response );
			return null;
		}
		List<ResultadoBuscarListaTipoClienteTipoCliente> tipoClienteList = Arrays.asList(buscarListaTipoClienteResponse.getResultadoBuscarListaTipoCliente());
		
		request.setAttribute("tiposCliente", tipoClienteList);
		request.setAttribute("plataformas", plataformaList);
		request.setAttribute("tecnologias", tecnologiaList);
		
		return mapping.findForward("success");
	}

	public ActionForward pesquisaSimplesPlanos(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ConsultaPlanosForm consultaPlanosForm = (ConsultaPlanosForm) form;
						
		BuscarPlanoRequest buscarPlanoRequest = new BuscarPlanoRequest();
		ParametrosBuscarPlano parametrosBuscarPlano = new ParametrosBuscarPlano();
		
		if (consultaPlanosForm.getCodigoPlano() != null && !consultaPlanosForm.getCodigoPlano().equals(""))
			parametrosBuscarPlano.setCdCodigo(consultaPlanosForm.getCodigoPlano());
		if (consultaPlanosForm.getNomePlano() != null && !consultaPlanosForm.getNomePlano().equals(""))
			parametrosBuscarPlano.setNmPlano(consultaPlanosForm.getNomePlano());
		if (consultaPlanosForm.getIdPlataforma() != null && consultaPlanosForm.getIdPlataforma() != 0)
			parametrosBuscarPlano.setIdPlataforma(consultaPlanosForm.getIdPlataforma());
		if (consultaPlanosForm.getIdTecnologia() != null && consultaPlanosForm.getIdTecnologia() != 0)
			parametrosBuscarPlano.setIdTecnologia(consultaPlanosForm.getIdTecnologia());
		if(consultaPlanosForm.getIdSistema() != null && consultaPlanosForm.getIdSistema() != 0)
			parametrosBuscarPlano.setIdSistema(consultaPlanosForm.getIdSistema());
		if (consultaPlanosForm.getUfs() != null && consultaPlanosForm.getUfs().length() > 0) {
			List<String> ufs = new ArrayList<String>();
			List<String> ddds = new ArrayList<String>();
			extrairJSONChaveValor(consultaPlanosForm.getUfs(), ufs, ddds);
			
			String uf = null;
			for(int i=0; i<ufs.size(); i++) {		
				if(parametrosBuscarPlano.getListaUf() == null){
					uf = ufs.get(i);
				}
				long ufLong = Long.valueOf(uf);
				long [] ufLongArray = {ufLong};
				parametrosBuscarPlano.setListaUf(ufLongArray);
			}
			
			String ddd = null;
			for(int i=0; i<ddds.size(); i++){
				if(parametrosBuscarPlano.getListaDDD() == null)
				ddd = ddds.get(i);
			}
			String [] dddArray = {ddd};
			parametrosBuscarPlano.setListaDDD(dddArray);
			
		}
		
		request.setAttribute("idPlataforma", consultaPlanosForm.getIdPlataforma());
		request.setAttribute("idSistema", consultaPlanosForm.getIdSistema());
		
		PaginacaoIn paginacaoIn = new PaginacaoIn();
		if(consultaPlanosForm.getPaginaSolicitada()!=null && consultaPlanosForm.getPaginaSolicitada() != 0)
			paginacaoIn.setPaginaSolicitada(consultaPlanosForm.getPaginaSolicitada());
		else
			paginacaoIn.setPaginaSolicitada(1);
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		
		parametrosBuscarPlano.setPaginacaoIn(paginacaoIn);

		buscarPlanoRequest.setParametrosBuscarPlano(parametrosBuscarPlano);
		
		
		PlanoPortTypeProxy planoPortTypeProxy = new PlanoPortTypeProxy();
		BuscarPlanoResponse buscarPlanoResponse = new BuscarPlanoResponse();
		try{
			buscarPlanoResponse = planoPortTypeProxy.buscarPlano(buscarPlanoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, ConsultaPlanosAction.class.getName(), ex.getMessage(), consultaPlanosForm, response );
			return null;
		}
		ResultadoBuscarPlano buscarPlano = buscarPlanoResponse.getResultadoBuscarPlano();
		
		br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoOut paginacaoOut = new br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoOut();
		paginacaoOut = buscarPlanoResponse.getResultadoBuscarPlano().getPaginacaoOut();
		if (paginacaoOut != null)
			tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(), request);
		ResultadoBuscarPlanoListaPlanoRetornoPlano[] listaPlano = buscarPlano.getListaPlano();
		
		Map<String, String> mapaDataIncial = new HashMap<String, String>(); 
        Map<String, String> mapaDataFinal = new HashMap<String, String>();                      

		
		if (listaPlano != null) {
			
			for(int i=0; i<listaPlano.length; i++){
				
				String nmPlano = listaPlano[i].getNmComercial();
				
				//TimeZone timeZone;
                //timeZone = TimeZone.getTimeZone("GMT+0:00");
                //TimeZone.setDefault(timeZone);
                
                if(listaPlano[i].getDtInicial() != null){
                	//listaPlano[i].getDtInicial().setTimeZone(timeZone);
                	
                	Calendar dtInicial = listaPlano[i].getDtInicial();
                	Date dataIni = dtInicial.getTime();
                    DateFormat dtIni = new SimpleDateFormat("dd/MM/yyyy");
                    String reportDateIni = dtIni.format(dataIni);
                    mapaDataIncial.put(nmPlano, reportDateIni);
                }
                
                if(listaPlano[i].getDtFinal() != null){
                	//listaPlano[i].getDtFinal().setTimeZone(timeZone);
                	
                	Calendar dtFinal = listaPlano[i].getDtFinal();
                	Date dataFim = dtFinal.getTime();
                    DateFormat dtFim = new SimpleDateFormat("dd/MM/yyyy");
                    String reportDateFim = dtFim.format(dataFim);
                    mapaDataFinal.put(nmPlano, reportDateFim);
                }
			}
			
			
			List<ResultadoBuscarPlanoListaPlanoRetornoPlano> retornoPlanoList = Arrays.asList(listaPlano);
			
			consultaPlanosForm.setMapaDataIncial(mapaDataIncial);
			consultaPlanosForm.setMapaDataFinal(mapaDataFinal);
			consultaPlanosForm.setRetornoPlanoList(retornoPlanoList);
			
			request.setAttribute("planos", retornoPlanoList);
		}

		return mapping.findForward("success");
	}

	public ActionForward listarUFs(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ConsultaPlanosForm consultaPlanosForm = (ConsultaPlanosForm) form;

		String ufsJSon = request.getParameter("hiddenUFs");
		if (ufsJSon != null) {
			List<String> chaves = new ArrayList<String>();
			List<String> valores = new ArrayList<String>();
			extrairJSONChaveValor(ufsJSon, chaves, valores );
			consultaPlanosForm.setUfsSelecionados(chaves.toArray(new String[]{}));
			consultaPlanosForm.setDddsSelecionados(valores.toArray(new String[]{}));
		}
		
		UFPortTypeProxy uFPortTypeProxy = new UFPortTypeProxy();
		BuscarListaUFComDDDRequest buscarListaUFComDDDRequest = new BuscarListaUFComDDDRequest();
		BuscarListaUFComDDDResponse buscarListaUFComDDDResponse = new BuscarListaUFComDDDResponse();
		try{
			buscarListaUFComDDDResponse = uFPortTypeProxy.buscarListaUFComDDD(buscarListaUFComDDDRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, ConsultaPlanosAction.class.getName(), ex.getMessage(), consultaPlanosForm, response );
			return null;
		}
		List<ResultadoBuscarListaUFUF> ufsList = Arrays.asList(buscarListaUFComDDDResponse.getResultadoBuscarListaUF());
		
		consultaPlanosForm.setUfsList(ufsList);
		
		return mapping.findForward("success");
	}

	public ActionForward listarTiposCliente(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ConsultaPlanosForm consultaPlanosForm = (ConsultaPlanosForm) form;

		TipoClientePortTypeProxy tipoClientePortTypeProxy = new TipoClientePortTypeProxy();
		BuscarListaTipoClienteRequest buscarListaTipoClienteRequest = new BuscarListaTipoClienteRequest();
		BuscarListaTipoClienteResponse buscarListaTipoClienteResponse = new BuscarListaTipoClienteResponse();
		
		try {
			buscarListaTipoClienteResponse = tipoClientePortTypeProxy.buscarListaTipoCliente(buscarListaTipoClienteRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ConsultaPlanosAction.class.getName(), ex.getMessage(), consultaPlanosForm, response );
			return null;
		}
		List<ResultadoBuscarListaTipoClienteTipoCliente> tipoClienteList = Arrays.asList(buscarListaTipoClienteResponse.getResultadoBuscarListaTipoCliente());
		Option[] options = new Option[tipoClienteList.size()];
		for (int i = 0; i < options.length; i++) {
			ResultadoBuscarListaTipoClienteTipoCliente tipoCliente = (ResultadoBuscarListaTipoClienteTipoCliente) tipoClienteList.get(i);
			Option option = new Option(tipoCliente.getIdTipoCliente(), tipoCliente.getNmTipoCliente());
			options[i] = option;
		}
		
		prepararDadosPopup("hiddenTiposCliente", "Tipo Cliente:", options, request);
						
		return mapping.findForward("success");
	}

	public ActionForward listarPlataformas(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ConsultaPlanosForm consultaPlanosForm = (ConsultaPlanosForm) form;
		
		PlataformaPortTypeProxy plataformaPortTypeProxy = new PlataformaPortTypeProxy();
		BuscarListaPlataformaRequest buscarListaPlataformaRequest = new BuscarListaPlataformaRequest();
		BuscarListaPlataformaResponse buscarListaPlataformaResponse = new BuscarListaPlataformaResponse();

		
		try {
			buscarListaPlataformaResponse = plataformaPortTypeProxy.buscarListaPlataforma(buscarListaPlataformaRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ConsultaPlanosAction.class.getName(), ex.getMessage(), consultaPlanosForm, response );
			return null;
		}
		List<ResultadoBuscarListaPlataformaPlataforma> plataformaList = Arrays.asList(buscarListaPlataformaResponse.getResultadoBuscarListaPlataforma());
		
		Option[] options = new Option[plataformaList.size()];
		for (int i = 0; i < options.length; i++) {
			ResultadoBuscarListaPlataformaPlataforma plataforma = (ResultadoBuscarListaPlataformaPlataforma) plataformaList.get(i);
			Option option = new Option(plataforma.getIdPlataforma(), plataforma.getNmPlataforma());
			options[i] = option;
		}
		
		prepararDadosPopup("hiddenPlataformas", "Plataforma:", options, request);

		return mapping.findForward("success");
	}

	public ActionForward listarCanais(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ConsultaPlanosForm consultaPlanosForm = (ConsultaPlanosForm) form;
		
		CanalPortTypeProxy canalPortTypeProxy = new CanalPortTypeProxy();
		BuscarListaCanalRequest buscarListaCanalRequest = new BuscarListaCanalRequest();
		BuscarListaCanalResponse buscarListaCanalResponse = new BuscarListaCanalResponse();

		try {
			buscarListaCanalResponse = canalPortTypeProxy.buscarListaCanal(buscarListaCanalRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ConsultaPlanosAction.class.getName(), ex.getMessage(), consultaPlanosForm, response );
			return null;
		}
		List<ResultadoListaCanalCanal> canalList = Arrays.asList(buscarListaCanalResponse.getResultadoListaCanal());
		
		Option[] options = new Option[canalList.size()];
		for (int i = 0; i < options.length; i++) {
			ResultadoListaCanalCanal canal = (ResultadoListaCanalCanal) canalList.get(i);
			Option option = new Option(canal.getIdCanal(), canal.getNmCanal());
			options[i] = option;
		}

		prepararDadosPopup("hiddenCanais", "Canais:", options, request);

		return mapping.findForward("success");
	}

	public ActionForward listarCarteiras(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		return mapping.findForward("success");
	}

	public ActionForward listarSegmentos(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		return mapping.findForward("success");
	}

	public ActionForward listarUFSAvancada(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ConsultaPlanosForm consultaPlanosForm = (ConsultaPlanosForm) form;
		
		UFPortTypeProxy uFPortTypeProxy = new UFPortTypeProxy();
		BuscarListaUFRequest buscarListaUFRequest = new BuscarListaUFRequest();
		BuscarListaUFResponse buscarListaUFResponse = new BuscarListaUFResponse();

		try {
			buscarListaUFResponse = uFPortTypeProxy.buscarListaUF(buscarListaUFRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ConsultaPlanosAction.class.getName(), ex.getMessage(), consultaPlanosForm, response );
			return null;
		}
		List<ResultadoBuscarListaUFUF> ufs = Arrays.asList(buscarListaUFResponse.getResultadoBuscarListaUF());
		
		Option[] options = new Option[ufs.size()];
		for (int i = 0; i < options.length; i++) {
			ResultadoBuscarListaUFUF uf = (ResultadoBuscarListaUFUF) ufs.get(i);
			Option option = new Option(uf.getIdUf(), uf.getNmUF());
			options[i] = option;
		}
		
		prepararDadosPopup("ca_hiddenUFS", "UF:", options, request);
		
		return mapping.findForward("success");
	}

	public ActionForward listarDDDs(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		return mapping.findForward("success");
	}

	public ActionForward popupServicos(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ConsultaPlanosForm consultaPlanosForm = (ConsultaPlanosForm) form;

		ServicoPortTypeProxy servicoPortTypeProxy = new ServicoPortTypeProxy();
		ParametrosBuscarDetalhesServicoPorIdPlano listaDetalhesServicoPorIdPlano = new ParametrosBuscarDetalhesServicoPorIdPlano();
		BuscarDetalhesServicoPorIdPlanoRequest buscarDetalhesServicoPorIdPlanoRequest = new BuscarDetalhesServicoPorIdPlanoRequest();
		BuscarDetalhesServicoPorIdPlanoResponse buscarDetalhesServicoPorIdPlanoResponse = new BuscarDetalhesServicoPorIdPlanoResponse();
		
		listaDetalhesServicoPorIdPlano.setIdPlano(Long.valueOf(request.getParameter("id_plano")));
		buscarDetalhesServicoPorIdPlanoRequest.setParametrosBuscarDetalhesServicoPorIdPlano(listaDetalhesServicoPorIdPlano);

		try {
			buscarDetalhesServicoPorIdPlanoResponse = servicoPortTypeProxy.buscarDetalhesServicoPorIdPlano(buscarDetalhesServicoPorIdPlanoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ConsultaPlanosAction.class.getName(), ex.getMessage(), consultaPlanosForm, response );
			return null;
		}
		List<ListaGrupoServicoCategoria[]> listaGrupoServicoList = Arrays.asList(buscarDetalhesServicoPorIdPlanoResponse.getResultadoBuscarDetalhesServicoPorIdPlano());
		
		ListaGrupoServicoCategoria[] listaGrupoServico = null;
		if(listaGrupoServicoList != null && listaGrupoServicoList.size()>0)
			listaGrupoServico = listaGrupoServicoList.get(0);
		if (listaGrupoServico != null) {
			ListaGrupoServicoCategoria[] categoriaList = listaGrupoServico;
			request.setAttribute("servicos", categoriaList);
		}

		return mapping.findForward("success");
	}

	public ActionForward popupUFs(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ConsultaPlanosForm consultaPlanosForm = (ConsultaPlanosForm) form;
		
		Long idPlataforma = Long.valueOf(request.getParameter("id_plataforma"));
		if(idPlataforma == 1){
			UFPortTypeProxy uFPortTypeProxy = new UFPortTypeProxy();
			ParametrosBuscarListaUfPorIdPlano paramListarUfPorIdPlano = new ParametrosBuscarListaUfPorIdPlano();
			BuscarListaUFPorIdPlanoRequest buscarListaUFPorIdPlanoRequest = new BuscarListaUFPorIdPlanoRequest();
			BuscarListaUFPorIdPlanoResponse buscarListaUFPorIdPlanoResponse = new BuscarListaUFPorIdPlanoResponse();
			ParametrosBuscarListaUfPorIdPlanoListaUfPorIdPlano listaUfPorIdPlano = new ParametrosBuscarListaUfPorIdPlanoListaUfPorIdPlano();
			
			listaUfPorIdPlano.setIdPlano(Long.valueOf(request.getParameter("id_plano")));
			paramListarUfPorIdPlano.setListaUfPorIdPlano(listaUfPorIdPlano);
			buscarListaUFPorIdPlanoRequest.setParametrosBuscarListaUfPorIdPlano(paramListarUfPorIdPlano);
			
			try {
				buscarListaUFPorIdPlanoResponse = uFPortTypeProxy.buscarListaUFPorIdPlano(buscarListaUFPorIdPlanoRequest, this.getUserName(), this.getPassword());
			} catch (AxisFault ex) {
				this.AxisFaultExceptionHandler(ex, ConsultaPlanosAction.class.getName(), ex.getMessage(), consultaPlanosForm, response );
				return null;
			}
			ResultadoBuscarListaUFUF[] resultadoBuscarListaUF = buscarListaUFPorIdPlanoResponse.getResultadoBuscarListaUF();
			
			if (resultadoBuscarListaUF != null) {
				List<ResultadoBuscarListaUFUF> ufList = Arrays.asList(resultadoBuscarListaUF);
				
				consultaPlanosForm.setUfList(ufList);
				request.setAttribute("ufs", ufList);
			}
		}else{
			UFPortTypeProxy uFPortTypeProxy = new UFPortTypeProxy();
			ParametrosBuscarListaUfComDDDPorIdPlano parametrosBuscarListaUfComDDDPorIdPlano = new ParametrosBuscarListaUfComDDDPorIdPlano();
			BuscarListaUFComDDDPorIdPlanoRequest buscarListaUFComDDDPorIdPlanoRequest = new BuscarListaUFComDDDPorIdPlanoRequest();
			BuscarListaUFComDDDPorIdPlanoResponse buscarListaUFComDDDPorIdPlanoResponse = new BuscarListaUFComDDDPorIdPlanoResponse();
			ParametrosBuscarListaUfComDDDPorIdPlanoListaUfComDDDPorIdPlano listaUfComDDDPorIdPlano = new ParametrosBuscarListaUfComDDDPorIdPlanoListaUfComDDDPorIdPlano();
			
			listaUfComDDDPorIdPlano.setIdPlano(Long.valueOf(request.getParameter("id_plano")));
			parametrosBuscarListaUfComDDDPorIdPlano.setListaUfComDDDPorIdPlano(listaUfComDDDPorIdPlano);
			buscarListaUFComDDDPorIdPlanoRequest.setParametrosBuscarListaUfComDDDPorIdPlano(parametrosBuscarListaUfComDDDPorIdPlano);
			
			try {
				buscarListaUFComDDDPorIdPlanoResponse = uFPortTypeProxy.buscarListaUFComDDDPorIdPlano(buscarListaUFComDDDPorIdPlanoRequest, this.getUserName(), this.getPassword());
			} catch (AxisFault ex) {
				this.AxisFaultExceptionHandler(ex, ConsultaPlanosAction.class.getName(), ex.getMessage(), consultaPlanosForm, response );
				return null;
			}
			ResultadoBuscarListaUFUF[] resultadoBuscarListaUF = buscarListaUFComDDDPorIdPlanoResponse.getResultadoBuscarListaUF();
			
			if (resultadoBuscarListaUF != null) {
				List<ResultadoBuscarListaUFUF> ufList = Arrays.asList(resultadoBuscarListaUF);
				
				consultaPlanosForm.setUfList(ufList);
				request.setAttribute("ufs", ufList);
			}
		}

		return mapping.findForward("success");
	}

	public ActionForward exportarVariaveis(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, CatalogoPRSException {
		
		ConsultaPlanosForm consultaPlanosForm = (ConsultaPlanosForm) form;
		
		if(consultaPlanosForm.getIdsPlanos() == null || consultaPlanosForm.getIdsPlanos().length ==0)
			throw new CatalogoPRSException("Obrigat&oacute;rio selecionar dados para exporta&ccedil;&atilde;o.");
		
		BuscarListaVariaveisPorPlanoRequest buscarListaVariaveisPorPlanoRequest = new BuscarListaVariaveisPorPlanoRequest();
		
		long [] idPlano = new long[consultaPlanosForm.getIdsPlanos().length];		
		for(int i=0; i<consultaPlanosForm.getIdsPlanos().length; i++){			
			idPlano[i] = Long.parseLong(consultaPlanosForm.getIdsPlanos()[i].toString());
		}
		
		buscarListaVariaveisPorPlanoRequest.getParametrosBuscarListaVariaveisPorPlano().setIdPlano(idPlano);
		buscarListaVariaveisPorPlanoRequest.getParametrosBuscarListaVariaveisPorPlano().setIdPlataforma(consultaPlanosForm.getIdPlataforma());
		br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoIn paginacaoIn = new br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoIn();
		int linhasLidas = 0;
		paginacaoIn.setItensPorPagina(1000);
		int currentPage = 1;
		paginacaoIn.setPaginaSolicitada(currentPage++);
		StringBuffer sb = new StringBuffer();
		sb.append("Nome do Plano;Plataforma;Tecnologia;Tipo de Cliente;Canal;Segmento;Carteira;UF;DDD\n");
		
		PlanoPortTypeProxy planoPortTypeProxy = new PlanoPortTypeProxy();
		BuscarListaVariaveisPorPlanoResponse buscarListaVariaveisPorPlanoResponse = new BuscarListaVariaveisPorPlanoResponse();
		try {
			buscarListaVariaveisPorPlanoResponse = planoPortTypeProxy.buscarListaVariaveisPorPlano(buscarListaVariaveisPorPlanoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ConsultaPlanosAction.class.getName(), ex.getMessage(), consultaPlanosForm, response );
			return null;
		}
		List<ResultadoBuscarListaVariaveisPorPlanoListaVariaveisPorPlanoRetornoVariaveisPlano> listaVariaveisPorPlano = Arrays.asList(buscarListaVariaveisPorPlanoResponse.getResultadoBuscarListaVariaveisPorPlano().getListaVariaveisPorPlano());
		
		while(true){
			List<ResultadoBuscarListaVariaveisPorPlanoListaVariaveisPorPlanoRetornoVariaveisPlano> planoList = listaVariaveisPorPlano;
			linhasLidas += planoList.size();
			for (ResultadoBuscarListaVariaveisPorPlanoListaVariaveisPorPlanoRetornoVariaveisPlano plano : planoList) {
				sb.append(plano.getNmComercial());
				sb.append(";");
				sb.append(plano.getNmPlataforma());
				sb.append(";");
				sb.append(plano.getNmTecnologia());
				sb.append(";");
				sb.append(plano.getNmTipoCliente());
				sb.append(";");
				sb.append(plano.getNmCanal());
				sb.append(";");
				sb.append(plano.getSgSegmento());
				sb.append(";");
				sb.append(plano.getSgCarteira());
				sb.append(";");
				sb.append(plano.getNmUf());
				sb.append(";");
				sb.append(plano.getCodigoArea());
				sb.append(";");
				sb.append("\n");
			}
			
			if(linhasLidas >= buscarListaVariaveisPorPlanoResponse.getResultadoBuscarListaVariaveisPorPlano().getPaginacaoOut().getTotalRegistros()){
				break;
			}else{
				buscarListaVariaveisPorPlanoRequest.getParametrosBuscarListaVariaveisPorPlano().getPaginacaoIn().setPaginaSolicitada(currentPage++);
				listaVariaveisPorPlano = Arrays.asList(buscarListaVariaveisPorPlanoResponse.getResultadoBuscarListaVariaveisPorPlano().getListaVariaveisPorPlano());
			}
		}
		response.setContentType("application/vnd.ms-excel;charset=iso-8859-1");
		response.addHeader("Content-Disposition", "attachment;filename=Planos.csv");
		response.setCharacterEncoding("ISO-8859-1");
		PrintWriter out = response.getWriter();
		
		out.write(sb.toString());
		out.flush();

		return null;
	}

	public ActionForward listarVariaveisPlano(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws CatalogoPRSException, Exception {
		
		ConsultaPlanosForm consultaPlanosForm = (ConsultaPlanosForm) form;
		
		if(consultaPlanosForm.getIdsPlanos() == null || consultaPlanosForm.getIdsPlanos().length == 0 ){
			CatalogoPRSException ex = new CatalogoPRSException("N&atilde;o h&aacute; plano selecionado");
			this.CatalogoPRSExceptionHandler(ex, ConsultaPlanosAction.class.getName(), response);
			return null;
		}
		
		BuscarListaVariaveisPorPlanoRequest buscarListaVariaveisPorPlanoRequest = new BuscarListaVariaveisPorPlanoRequest();
		ParametrosBuscarListaVariaveisPorPlano parametrosBuscarListaVariaveisPorPlano = new ParametrosBuscarListaVariaveisPorPlano();
		br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoIn paginacaoIn = new br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoIn();
		
		parametrosBuscarListaVariaveisPorPlano.setIdPlataforma(consultaPlanosForm.getIdPlataforma());
		
		request.setAttribute("idPlataforma", consultaPlanosForm.getIdPlataforma());
		
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		if(consultaPlanosForm.getPaginaSolicitada()!=null)
			paginacaoIn.setPaginaSolicitada(consultaPlanosForm.getPaginaSolicitada());
		else
			paginacaoIn.setPaginaSolicitada(1);
		
		long [] idPlano = new long[consultaPlanosForm.getIdsPlanos().length];		
		for(int i=0; i<consultaPlanosForm.getIdsPlanos().length; i++){			
			idPlano[i] = Long.parseLong(consultaPlanosForm.getIdsPlanos()[i].toString());
		}
		
		parametrosBuscarListaVariaveisPorPlano.setPaginacaoIn(paginacaoIn);
		buscarListaVariaveisPorPlanoRequest.setParametrosBuscarListaVariaveisPorPlano(parametrosBuscarListaVariaveisPorPlano);
		buscarListaVariaveisPorPlanoRequest.getParametrosBuscarListaVariaveisPorPlano().setIdPlano(idPlano);
		
		PlanoPortTypeProxy planoPortTypeProxy = new PlanoPortTypeProxy();
		BuscarListaVariaveisPorPlanoResponse buscarListaVariaveisPorPlanoResponse = new BuscarListaVariaveisPorPlanoResponse();
		try {
			buscarListaVariaveisPorPlanoResponse = planoPortTypeProxy.buscarListaVariaveisPorPlano(buscarListaVariaveisPorPlanoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ConsultaPlanosAction.class.getName(), ex.getMessage(), consultaPlanosForm, response );
			return null;
		}
		ResultadoBuscarListaVariaveisPorPlano resultadoBuscarListaVariaveisPorPlano = buscarListaVariaveisPorPlanoResponse.getResultadoBuscarListaVariaveisPorPlano();
		
		tratarPaginacao(resultadoBuscarListaVariaveisPorPlano.getPaginacaoOut(), ApplicationConfiguration.getElementosPorPagina(), request);
		
		
		ResultadoBuscarListaVariaveisPorPlanoListaVariaveisPorPlanoRetornoVariaveisPlano[] listaVariaveisPorPlano = resultadoBuscarListaVariaveisPorPlano.getListaVariaveisPorPlano();
		
		if (listaVariaveisPorPlano != null) {
			List<ResultadoBuscarListaVariaveisPorPlanoListaVariaveisPorPlanoRetornoVariaveisPlano> retornoVariaveisPlanoList = Arrays.asList(listaVariaveisPorPlano);
			request.setAttribute("variaveis", retornoVariaveisPlanoList);
		}
		
		return mapping.findForward("success");
	}

	@SuppressWarnings("unchecked")
	public ActionForward exportarPlanos(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws CatalogoPRSException, IOException {
		
		ConsultaPlanosForm consultaPlanosForm = (ConsultaPlanosForm) form;
		
		if(consultaPlanosForm.getIdsPlanos() == null || consultaPlanosForm.getIdsPlanos().length == 0){
			CatalogoPRSException ex = new CatalogoPRSException("Obrigat&oacute;rio selecionar dados para exporta&ccedil;&atilde;o.");
			this.CatalogoPRSExceptionHandler(ex, ConsultaPlanosAction.class.getName(), response);
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		StringBuffer sb = new StringBuffer();

		BuscarListaPlanoPorIdRequest buscarListaPlanoPorIdRequest = new BuscarListaPlanoPorIdRequest();
		
		long [] idPlano = new long[consultaPlanosForm.getIdsPlanos().length];		
		for(int i=0; i<consultaPlanosForm.getIdsPlanos().length; i++){			
			idPlano[i] = Long.parseLong(consultaPlanosForm.getIdsPlanos()[i].toString());
		}
		
		buscarListaPlanoPorIdRequest.setParametrosBuscarListaPlanoPorId(idPlano);
		PlanoPortTypeProxy planoPortTypeProxy = new PlanoPortTypeProxy();
		BuscarListaPlanoPorIdResponse buscarListaPlanoPorIdResponse = new BuscarListaPlanoPorIdResponse();
		try {
			buscarListaPlanoPorIdResponse = planoPortTypeProxy.buscarListaPlanoPorId(buscarListaPlanoPorIdRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ConsultaPlanosAction.class.getName(), ex.getMessage(), consultaPlanosForm, response );
			return null;
		}
		
		List<ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano> planoList = Arrays.asList(buscarListaPlanoPorIdResponse.getResultadoBuscarListaPlanoPorId().getListaPlano());
		sb.append("Nome do Plano;Plano Titular;Quant. M&aacute;xima de Dependente;C&oacute;digo Sist&ecirc;mico do Plano;C&oacute;digo Anatel;Per&iacute;odo;Valor Assinatura Mensal;Franquia de Voz;Servi&ccedil;os Vinculados ao Plano;Disponibilidade\n");
		
		for (ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano plano : planoList) {	
			List<ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlanoListaServicoServico> servicos = new ArrayList<ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlanoListaServicoServico>();
			if(plano.getListaServico() != null && plano.getListaServico().length > 0) {
				servicos = (List<ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlanoListaServicoServico>) plano;
				
				for (ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlanoListaServicoServico servico : servicos) {
					sb.append(plano.getNmComercial());
					sb.append(";");
					sb.append(plano.getIndicadorTitularDependente().equalsIgnoreCase("S") ? "Sim" : "N&atilde;o");
					sb.append(";");
					sb.append(plano.getQtdeMaxDependCatalogo());
					sb.append(";");
					sb.append(plano.getCdCodigo());
					sb.append(";");
					sb.append(plano.getCdAnatel());
					sb.append(";");
					sb.append(sdf.format(plano.getDtInicial().getTime())+" a "+sdf.format(plano.getDtFinal().getTime()));
					sb.append(";");
					sb.append(plano.getValorAssinaturaMensal());
					sb.append(";");
					sb.append(plano.getQtdeMinFranquia());
					sb.append(";");
					sb.append(servico != null ? servico.getNmComercial() : "");
					sb.append(";");
					sb.append(plano.getInDisponivel().equalsIgnoreCase("S") ? "SIM" : "N&atilde;o");
					sb.append(";");
					sb.append("\n");
				}
				
			}else {
				servicos.add(null);
			}
		}
		
		response.setContentType("application/vnd.ms-excel;charset=iso-8859-1");
		response.addHeader("Content-Disposition", "attachment;filename=Planos.csv");
		response.setCharacterEncoding("ISO-8859-1");
		PrintWriter out = response.getWriter();

		out.write(sb.toString());
		out.flush();

		return null;
	}

	public ActionForward popupAlterarDescricaoPlano(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ConsultaPlanosForm consultaPlanosForm = (ConsultaPlanosForm) form;
		
		BuscarDescricaoPlanoRequest	buscarDescricaoPlanoRequest = new BuscarDescricaoPlanoRequest();
		ParametrosBuscarDescricaoPlano parametrosBuscarDescricaoPlano = new ParametrosBuscarDescricaoPlano();
		
		parametrosBuscarDescricaoPlano.setIdPlano(Long.valueOf(request.getParameter("id_plano")));
		
		buscarDescricaoPlanoRequest.setParametrosBuscarDescricaoPlano(parametrosBuscarDescricaoPlano);
		
		PlanoPortTypeProxy planoPortTypeProxy = new PlanoPortTypeProxy();
		BuscarDescricaoPlanoResponse buscarDescricaoPlanoResponse = new BuscarDescricaoPlanoResponse();
		try {
			buscarDescricaoPlanoResponse = planoPortTypeProxy.buscarDescricaoPlano(buscarDescricaoPlanoRequest, this.getUserName(), this.getPassword());
		} catch (AxisFault ex) {
			this.AxisFaultExceptionHandler(ex, ConsultaPlanosAction.class.getName(), ex.getMessage(), consultaPlanosForm, response );
			return null;
		}
		List<Plano> descricaoList = Arrays.asList(buscarDescricaoPlanoResponse.getResultadoBuscarDescricaoPlano().getListaPlanos());

		String descricao = descricaoList.get(0).getDescricao();
		
		request.setAttribute("desc_plano", descricao);
		
		return mapping.findForward("success");
	}

	public ActionForward alterarDescricaoPlano(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		return mapping.findForward("success");
	}

	private void prepararDadosPopup(String nomeHidden, String nomeBox, Option[] options, HttpServletRequest request) {
		String[] selecionados = new String[0];
		String canaisCSV = request.getParameter(nomeHidden);
		if (canaisCSV != null) {
			selecionados = canaisCSV.split(",");
		}
		request.setAttribute("lista", options);
		request.setAttribute("selecionados", selecionados);
		request.setAttribute("nomeBox", nomeBox);
		request.setAttribute("hiddenInput", nomeHidden);
	}

	@Override
	protected void onDestroy(HttpSession session) {
	}

	@SuppressWarnings("serial")
	public static class Option implements Serializable {

		private Long id;

		private String nome;

		public Option(Long id, String nome) {
			this.id = id;
			this.nome = nome;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

	}
	
}