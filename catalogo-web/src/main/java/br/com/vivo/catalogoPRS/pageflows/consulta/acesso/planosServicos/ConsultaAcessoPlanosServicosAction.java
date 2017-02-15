package br.com.vivo.catalogoPRS.pageflows.consulta.acesso.planosServicos;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.AxisFault;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import edu.emory.mathcs.backport.java.util.Collections;

import pt.ptinovacao.sca.LoginProcess;
import br.com.vivo.catalogoPRS.bo.UserCatalogo;
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.ConsultaAcessoPlanosServicosForm;
import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;
import br.com.vivo.catalogoPRS.util.PerfilSCA;
import br.com.vivo.catalogoPRS.util.SCAUtils;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.mc.geral.ErroInfo;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.AcessoPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.BuscarListaAcessoPlanoPorIdRequest;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.BuscarListaAcessoPlanoPorIdResponse;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.BuscarListaAcessoPlanoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.BuscarListaAcessoPlanoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.BuscarListaAcessoServicoPorIdRequest;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.BuscarListaAcessoServicoPorIdResponse;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.BuscarListaAcessoServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.BuscarListaAcessoServicoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosBuscarListaAcessoPlano;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosBuscarListaAcessoPlanoPorId;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosBuscarListaAcessoServico;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosBuscarListaAcessoServicoPorId;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoPlano;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoPlanoListaAcessoPlanoAcessoPlano;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoPlanoPorIdListaAcessoPlanoPorIdAcessoPlano;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoServico;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarListaGrupoServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarListaGrupoServicoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.GrupoServicoPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ParametrosListarGrupoServico;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ResultadoListarGrupoServicoCategoria;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarListaPlanoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarListaPlanoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoOut;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarListaPlano;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PlanoPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoListaPlanoPlano;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.BuscarListaPlataformaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.BuscarListaPlataformaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.PlataformaPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.ResultadoBuscarListaPlataformaPlataforma;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaServicoParametrizacaoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaServicoParametrizacaoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaTipoServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaTipoServicoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.PaginacaoIn;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarListaServicoParametrizacao;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoParametrizacao;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaTipoServicoListaTipoServicoRetorno;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ServicoPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaTecnologiaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.BuscarListaTecnologiaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarListaTecnologiaTecnologia;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.TecnologiaPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn.BuscarListaTipoClienteRequest;
import br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn.BuscarListaTipoClienteResponse;
import br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn.ResultadoBuscarListaTipoClienteTipoCliente;
import br.com.vivo.catalogoPRS.ws.catalogoTipoCliente.sn.TipoClientePortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFRequest;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.BuscarListaUFResponse;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.ResultadoBuscarListaUFUF;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.UFPortTypeProxy;

public class ConsultaAcessoPlanosServicosAction extends BaseMappingDispatchAction implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Map<Long, String> listaPerfilSCA;

	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ConsultaAcessoPlanosServicosForm formulario = (ConsultaAcessoPlanosServicosForm)form;

		PlataformaPortTypeProxy plataformaPortTypeProxy = new PlataformaPortTypeProxy();
		BuscarListaPlataformaRequest buscarListaPlataformaRequest = new BuscarListaPlataformaRequest();
		BuscarListaPlataformaResponse buscarListaPlataformaResponse = new BuscarListaPlataformaResponse();
		
		try {
			buscarListaPlataformaResponse = plataformaPortTypeProxy.buscarListaPlataforma(buscarListaPlataformaRequest,this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, ConsultaAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );
			return null;
		}	
		
		List<ResultadoBuscarListaPlataformaPlataforma> plataformaList = Arrays.asList(buscarListaPlataformaResponse.getResultadoBuscarListaPlataforma());
		
		request.setAttribute("plataformas", plataformaList);
		return mapping.findForward("success");
	}	

	public ActionForward listarPerfilSCA(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException {
		
		ConsultaAcessoPlanosServicosForm formulario = (ConsultaAcessoPlanosServicosForm)form;

		String perfilListaCheck = request.getParameter("hidden_perfilSCACheck");

		if (perfilListaCheck != null && perfilListaCheck.trim().length() > 0) {
			formulario.setPerfilSelecionados(perfilListaCheck.split(","));
		}
		String perfilListaRadio = request.getParameter("hidden_perfilSCARadio");
		if (perfilListaRadio != null && perfilListaRadio.trim().length() > 0) {
			formulario.setPerfilSelecionados(perfilListaRadio.split(","));
		}
		UserCatalogo usuarioLogado = (UserCatalogo) request.getSession().getAttribute("logged_user");
		LoginProcess loginProcess = SCAUtils.verify(usuarioLogado);
		Map<Long, String> userProfilesByIdProfile = SCAUtils.obterPerfisVivo360(loginProcess, usuarioLogado);
		if (userProfilesByIdProfile != null && userProfilesByIdProfile.size() > 0) {
			
			this.setListaPerfilSCA(userProfilesByIdProfile);
			
		}
		Set<Long> keys = userProfilesByIdProfile.keySet();
		List<PerfilSCA> listaPerfil = new ArrayList<PerfilSCA>();
		for (Long key : keys) {
			PerfilSCA perfil = new PerfilSCA();
			perfil.setIdPerfilSCA(key);
			perfil.setNmPerfilSCA(userProfilesByIdProfile.get(key));
			listaPerfil.add(perfil);
		}
		Collections.sort(listaPerfil);
		
		PerfilSCA[] arrayPerfil = (PerfilSCA[])listaPerfil.toArray(new PerfilSCA[listaPerfil.size()]);
		formulario.setArrayPerfil(arrayPerfil);
		request.setAttribute("listaPerfil", listaPerfil);
		
		String parameter = request.getParameter("perfil");
		if (parameter.equals("1")) {
			return mapping.findForward("check");
		} else {
			return mapping.findForward("radio");
		}
	}

	public ActionForward preencheNmComercial(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException, ErroInfo, RemoteException {

		PlataformaPortTypeProxy plataformaPortTypeProxy = new PlataformaPortTypeProxy();
		BuscarListaPlataformaRequest buscarListaPlataformaRequest = new BuscarListaPlataformaRequest();
		BuscarListaPlataformaResponse buscarListaPlataformaResponse = new BuscarListaPlataformaResponse();
		ConsultaAcessoPlanosServicosForm formulario = (ConsultaAcessoPlanosServicosForm)form;
		
		try {
			buscarListaPlataformaResponse = plataformaPortTypeProxy.buscarListaPlataforma(buscarListaPlataformaRequest,this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, ConsultaAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );
			return null;
		}
		
		List<ResultadoBuscarListaPlataformaPlataforma> plataformaList = Arrays.asList(buscarListaPlataformaResponse.getResultadoBuscarListaPlataforma());
		request.setAttribute("plataformas", plataformaList);
		
		String parameter = request.getParameter("nmComercial");
		if (parameter.equals("1") || parameter.equals("3")) {
			
			TipoClientePortTypeProxy tipoClientePortTypeProxy = new TipoClientePortTypeProxy();
			BuscarListaTipoClienteRequest buscarListaTipoClienteRequest = new BuscarListaTipoClienteRequest();
			
			TecnologiaPortTypeProxy tecnologiaPortTypeProxy = new TecnologiaPortTypeProxy();
			BuscarListaTecnologiaRequest buscarListaTecnologiaRequest= new BuscarListaTecnologiaRequest();

			UFPortTypeProxy uFPortTypeProxy = new UFPortTypeProxy();
			BuscarListaUFRequest buscarListaUFRequest = new BuscarListaUFRequest();
			
			try {

				BuscarListaTipoClienteResponse buscarListaTipoClienteResponse = tipoClientePortTypeProxy.buscarListaTipoCliente(buscarListaTipoClienteRequest,this.getUserName(),this.getPassword());
				List<ResultadoBuscarListaTipoClienteTipoCliente> tipoClienteList = Arrays.asList(buscarListaTipoClienteResponse.getResultadoBuscarListaTipoCliente());
				request.setAttribute("tipoCliente", tipoClienteList);

				BuscarListaTecnologiaResponse tecnologiaResponse = tecnologiaPortTypeProxy.buscarListaTecnologia(buscarListaTecnologiaRequest,this.getUserName(),this.getPassword());
				List<ResultadoBuscarListaTecnologiaTecnologia> tecnologiaList = Arrays.asList(tecnologiaResponse.getResultadoBuscarListaTecnologia());
				request.setAttribute("listaTecnologia", tecnologiaList);

				BuscarListaUFResponse buscarListaUFResponse = uFPortTypeProxy.buscarListaUF(buscarListaUFRequest,this.getUserName(),this.getPassword());
				List<ResultadoBuscarListaUFUF> listUF = Arrays.asList(buscarListaUFResponse.getResultadoBuscarListaUF());
				request.setAttribute("listaUF", listUF);
				request.setAttribute("tipoNmComercial", parameter);
				
			} catch (AxisFault ex) {		
				this.AxisFaultExceptionHandler(ex, ConsultaAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );
				return null;
			}
			
			return mapping.findForward("plano");

		} else {
			GrupoServicoPortTypeProxy grupoServicoPortTypeProxy = new GrupoServicoPortTypeProxy();
			BuscarListaGrupoServicoRequest buscarListaGrupoServicoRequest = new BuscarListaGrupoServicoRequest();
			
			ServicoPortTypeProxy servicoPortTypeProxy = new ServicoPortTypeProxy();
			BuscarListaTipoServicoRequest buscarListaTipoServicoRequest = new BuscarListaTipoServicoRequest();	
			
			TecnologiaPortTypeProxy tecnologiaPortTypeProxy = new TecnologiaPortTypeProxy();
			BuscarListaTecnologiaRequest buscarListaTecnologiaRequest= new BuscarListaTecnologiaRequest();			
			
			try {
				
				ParametrosListarGrupoServico parametrosListarGrupoServico = new ParametrosListarGrupoServico();
				parametrosListarGrupoServico.setIndCatalogo(1);
				buscarListaGrupoServicoRequest.setParametrosListarGrupoServico(parametrosListarGrupoServico);
				
				BuscarListaGrupoServicoResponse buscarListaGrupoServicoResponse = grupoServicoPortTypeProxy.buscarListaGrupoServico(buscarListaGrupoServicoRequest,this.getUserName(),this.getPassword());
				List<ResultadoListarGrupoServicoCategoria> categoriaList = Arrays.asList(buscarListaGrupoServicoResponse.getResultadoListarGrupoServico());
				request.setAttribute("categoria", categoriaList);
				
				BuscarListaTipoServicoResponse buscarListaTipoServicoResponse = servicoPortTypeProxy.buscarListaTipoServico(buscarListaTipoServicoRequest,this.getUserName(),this.getPassword());
				List<ResultadoBuscarListaTipoServicoListaTipoServicoRetorno> retornoList = Arrays.asList(buscarListaTipoServicoResponse.getResultadoBuscarListaTipoServico().getListaTipoServico());
				request.setAttribute("tipoServico", retornoList);
				
				BuscarListaTecnologiaResponse tecnologiaResponse = tecnologiaPortTypeProxy.buscarListaTecnologia(buscarListaTecnologiaRequest,this.getUserName(),this.getPassword());
				List<ResultadoBuscarListaTecnologiaTecnologia> tecnologiaList = Arrays.asList(tecnologiaResponse.getResultadoBuscarListaTecnologia());
				request.setAttribute("listaTecnologia", tecnologiaList);
				
				request.setAttribute("tipoNmComercial", parameter);
				
			} catch (AxisFault ex) {		
				this.AxisFaultExceptionHandler(ex, ConsultaAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );
				return null;
			}
			
			return mapping.findForward("servico");
		}

	}

	public ActionForward pesquisarLocalizacaoPlanos(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException, ErroInfo, RemoteException {
		
		ConsultaAcessoPlanosServicosForm consultaAcessoPlanosServicosForm = (ConsultaAcessoPlanosServicosForm)form;

		ParametrosBuscarListaPlano parametrosBuscarListaPlano = new ParametrosBuscarListaPlano();
		
		if (consultaAcessoPlanosServicosForm.getIdPlataforma() != null) {
			parametrosBuscarListaPlano.setIdPlataforma(consultaAcessoPlanosServicosForm.getIdPlataforma());
		}
		if (consultaAcessoPlanosServicosForm.getIdTecnologia() != null) {
			parametrosBuscarListaPlano.setIdTecnologia(consultaAcessoPlanosServicosForm.getIdTecnologia());
		}
		if (consultaAcessoPlanosServicosForm.getIdUf() != null) {
			parametrosBuscarListaPlano.setIdUF(consultaAcessoPlanosServicosForm.getIdUf());
		}
		if (consultaAcessoPlanosServicosForm.getNmComercialPlano() != null && consultaAcessoPlanosServicosForm.getNmComercialPlano().length() > 0) {
			parametrosBuscarListaPlano.setNmComercial(consultaAcessoPlanosServicosForm.getNmComercialPlano());
		}
		if (consultaAcessoPlanosServicosForm.getNmTecnico() != null && consultaAcessoPlanosServicosForm.getNmTecnico().length() > 0) {
			parametrosBuscarListaPlano.setCdPlano(consultaAcessoPlanosServicosForm.getNmTecnico());
		}

		PlanoPortTypeProxy planoPortTypeProxy = new PlanoPortTypeProxy();
		
		BuscarListaPlanoRequest buscarListaPlanoRequest = new BuscarListaPlanoRequest();
		buscarListaPlanoRequest.setParametrosBuscarListaPlano(parametrosBuscarListaPlano);
		
		BuscarListaPlanoResponse buscarListaPlanoResponse = new BuscarListaPlanoResponse();
		
		try {
			buscarListaPlanoResponse = planoPortTypeProxy.buscarListaPlano(buscarListaPlanoRequest,this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, ConsultaAcessoPlanosServicosAction.class.getName(), ex.getMessage(), consultaAcessoPlanosServicosForm, response );
			return null;
		}
		
		ResultadoBuscarListaPlanoListaPlanoPlano[] arrayListaPlanos = buscarListaPlanoResponse.getResultadoBuscarListaPlano().getListaPlano();
		
		request.setAttribute("listaPlanos", arrayListaPlanos);
		consultaAcessoPlanosServicosForm.setArrayListaPlanos(arrayListaPlanos);

		if (consultaAcessoPlanosServicosForm.getTpNmComercial() == 1)
			return mapping.findForward("radio");
		else
			return mapping.findForward("check");
	}

	public ActionForward pesquisarLocalizacaoServicos(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException, ErroInfo, RemoteException {

		ConsultaAcessoPlanosServicosForm consultaAcessoPlanosServicosForm = (ConsultaAcessoPlanosServicosForm)form;
		
		ParametrosBuscarListaServicoParametrizacao parametrosBuscarListaServicoParametrizacao = new ParametrosBuscarListaServicoParametrizacao();
		PaginacaoIn paginacaoIn = new PaginacaoIn();
		
		if (consultaAcessoPlanosServicosForm.getPaginaSolicitada() != null) {
			paginacaoIn.setPaginaSolicitada(consultaAcessoPlanosServicosForm.getPaginaSolicitada());
		}
		else {
			paginacaoIn.setPaginaSolicitada(1);
		}
		
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		parametrosBuscarListaServicoParametrizacao.setPaginacaoIn(paginacaoIn);
		
		if (consultaAcessoPlanosServicosForm.getIdPlataforma() != null) {
			parametrosBuscarListaServicoParametrizacao.setIdPlataforma(consultaAcessoPlanosServicosForm.getIdPlataforma());
		}
		if (consultaAcessoPlanosServicosForm.getIdCategoria() != null) {
			parametrosBuscarListaServicoParametrizacao.setIdCategoriaCatalogo(consultaAcessoPlanosServicosForm.getIdCategoria());
		}
		if (consultaAcessoPlanosServicosForm.getIdTipoServico() != null) {
			parametrosBuscarListaServicoParametrizacao.setIdTpServico(consultaAcessoPlanosServicosForm.getIdTipoServico());
		}
		if (consultaAcessoPlanosServicosForm.getNmTecnico() != null && !consultaAcessoPlanosServicosForm.getNmComercialServico().equals("")) {
			parametrosBuscarListaServicoParametrizacao.setCdServico(consultaAcessoPlanosServicosForm.getNmTecnico());
		}
		if (consultaAcessoPlanosServicosForm.getNmComercialServico() != null && !consultaAcessoPlanosServicosForm.getNmComercialServico().equals("")) {
			parametrosBuscarListaServicoParametrizacao.setNmServico(consultaAcessoPlanosServicosForm.getNmComercialServico());
		}

		ServicoPortTypeProxy servicoPortTypeProxy = new ServicoPortTypeProxy();
		BuscarListaServicoParametrizacaoRequest buscarListaServicoParametrizacaoRequest = new BuscarListaServicoParametrizacaoRequest();
		buscarListaServicoParametrizacaoRequest.setParametrosBuscarListaServicoParametrizacao(parametrosBuscarListaServicoParametrizacao);
		
		BuscarListaServicoParametrizacaoResponse buscarListaServicoParametrizacaoResponse = new BuscarListaServicoParametrizacaoResponse();
		
		try {
			buscarListaServicoParametrizacaoResponse = servicoPortTypeProxy.buscarListaServicoParametrizacao(buscarListaServicoParametrizacaoRequest,this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			this.AxisFaultExceptionHandler(ex, ConsultaAcessoPlanosServicosAction.class.getName(), ex.getMessage(), consultaAcessoPlanosServicosForm, response );
			return null;
		}
		
		ResultadoBuscarListaServicoParametrizacao resultadoBuscarListaServicoParametrizacao = buscarListaServicoParametrizacaoResponse.getResultadoBuscarListaServicoParametrizacao();
		
		ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao[] arrayListaServicos = resultadoBuscarListaServicoParametrizacao.getListaBuscarListaServicoParametrizacao();
		
		PaginacaoOut paginacaoOut = resultadoBuscarListaServicoParametrizacao.getPaginacaoOut();
		if (paginacaoOut != null) {
			this.tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(),request);
		}
		
		request.setAttribute("listaServico", arrayListaServicos);
		consultaAcessoPlanosServicosForm.setArrayListaServicos(arrayListaServicos);

		if (consultaAcessoPlanosServicosForm.getTpNmComercial() == 2) {
			return mapping.findForward("radio");
		}
		else {
			return mapping.findForward("check");
		}

	}

	public ActionForward pesquisar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException, ErroInfo, RemoteException {
		
		ConsultaAcessoPlanosServicosForm consultaAcessoPlanosServicosForm = (ConsultaAcessoPlanosServicosForm)form;

		request.setAttribute("listaPerfilSCA", listaPerfilSCA);
		
		if (consultaAcessoPlanosServicosForm.getIdTpPesquisa() == 1 || consultaAcessoPlanosServicosForm.getIdTpPesquisa() == 3) {
			
			ParametrosBuscarListaAcessoPlano parametrosBuscarListaAcessoPlano = new ParametrosBuscarListaAcessoPlano();

			br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn = new br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn();
			if (consultaAcessoPlanosServicosForm.getPaginaSolicitada() != null) {
				paginacaoIn.setPaginaSolicitada(consultaAcessoPlanosServicosForm.getPaginaSolicitada());
			}
			else {
				paginacaoIn.setPaginaSolicitada(1);
			}
			paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
			if (consultaAcessoPlanosServicosForm.getNmTecnico() != null && !consultaAcessoPlanosServicosForm.getNmTecnico().equals("")) {
				parametrosBuscarListaAcessoPlano.setCdPlano(consultaAcessoPlanosServicosForm.getNmTecnico());
			}
			if (consultaAcessoPlanosServicosForm.getIdPlataforma() != null) {
				parametrosBuscarListaAcessoPlano.setIdPlataforma(consultaAcessoPlanosServicosForm.getIdPlataforma());
			}
			if (!consultaAcessoPlanosServicosForm.getNmComercialPlanoTextBox().equals("")) {
				parametrosBuscarListaAcessoPlano.setNmComercial(consultaAcessoPlanosServicosForm.getNmComercialPlanoTextBox());
			}
			if (consultaAcessoPlanosServicosForm.getNmComercialPlano() != null && !consultaAcessoPlanosServicosForm.getNmComercialPlano().equals("")) {
				parametrosBuscarListaAcessoPlano.setNmComercial(consultaAcessoPlanosServicosForm.getNmComercialPlano());
			}
			if (!consultaAcessoPlanosServicosForm.getNmComercialPlanoTextBox2().equals("")) {
				parametrosBuscarListaAcessoPlano.setNmComercial(consultaAcessoPlanosServicosForm.getNmComercialPlanoTextBox2());
			}
			if (consultaAcessoPlanosServicosForm.getIdPlano() != null && !consultaAcessoPlanosServicosForm.getIdPlano().equals("")) {
				
				String[] idsPlano = consultaAcessoPlanosServicosForm.getIdPlano().split(",");
				int count = idsPlano.length;
				long[] listaIdPlanos = new long[count];
				
				for (int i=0;i < count;i++) {
					listaIdPlanos[i] = Long.parseLong(idsPlano[i]);
				}
				parametrosBuscarListaAcessoPlano.setListaIdPlano(listaIdPlanos);
			}
			
			if (consultaAcessoPlanosServicosForm.getIdsPerfilCheck() != null && !consultaAcessoPlanosServicosForm.getIdsPerfilCheck().equals("")) {
				
				String[] perfilcheck = consultaAcessoPlanosServicosForm.getIdsPerfilCheck().split(",");
				int count = perfilcheck.length;
				long[] listaIdPerfils = new long[count];
				
				for (int i=0;i < count;i++) {
					listaIdPerfils[i] = Long.parseLong(perfilcheck[i]);
				}
				parametrosBuscarListaAcessoPlano.setListaIdPerfil(listaIdPerfils);
			}
			
			if (consultaAcessoPlanosServicosForm.getIdsPerfilRadio() != null && !consultaAcessoPlanosServicosForm.getIdsPerfilRadio().equals("")) {
				
				String[] perfilradio = consultaAcessoPlanosServicosForm.getIdsPerfilRadio().split(",");
				int count = perfilradio.length;
				long[] listaIdPerfils = new long[count];
				
				for (int i=0;i < count;i++) {
					listaIdPerfils[i] = Long.parseLong(perfilradio[i]);
				}
				parametrosBuscarListaAcessoPlano.setListaIdPerfil(listaIdPerfils);
			}
			
			AcessoPortTypeProxy acessoPortTypeProxy = new AcessoPortTypeProxy();
			BuscarListaAcessoPlanoRequest buscarListaAcessoPlanoRequest = new BuscarListaAcessoPlanoRequest();
			
			parametrosBuscarListaAcessoPlano.setPaginacaoIn(paginacaoIn);
			buscarListaAcessoPlanoRequest.setParametrosBuscarListaAcessoPlano(parametrosBuscarListaAcessoPlano);
			BuscarListaAcessoPlanoResponse buscarListaAcessoPlanoResponse = new BuscarListaAcessoPlanoResponse();
			
			try {
				buscarListaAcessoPlanoResponse = acessoPortTypeProxy.buscarListaAcessoPlano(buscarListaAcessoPlanoRequest,this.getUserName(),this.getPassword());
			} catch (AxisFault ex) {		
				this.AxisFaultExceptionHandler(ex, ConsultaAcessoPlanosServicosAction.class.getName(), ex.getMessage(), consultaAcessoPlanosServicosForm, response );
				return null;
			}
			
			ResultadoBuscarListaAcessoPlano resultadoBuscarListaAcessoPlano = buscarListaAcessoPlanoResponse.getResultadoBuscarListaAcessoPlano();
			ResultadoBuscarListaAcessoPlanoListaAcessoPlanoAcessoPlano[] acessoPlanoList = resultadoBuscarListaAcessoPlano.getListaAcessoPlano();
			br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut paginacaoOut = resultadoBuscarListaAcessoPlano.getPaginacaoOut();
			if (paginacaoOut != null) {
				this.tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(), request);
			}
			
			consultaAcessoPlanosServicosForm.setArrayListaAcessoPlano(acessoPlanoList);
			request.setAttribute("idTipoPesquisa", consultaAcessoPlanosServicosForm.getIdTpPesquisa());			
			
			return mapping.findForward("planos");

		} else if (consultaAcessoPlanosServicosForm.getIdTpPesquisa() == 2 || consultaAcessoPlanosServicosForm.getIdTpPesquisa() == 4) {
			
			ParametrosBuscarListaAcessoServico parametrosBuscarListaAcessoServico = new ParametrosBuscarListaAcessoServico();
			br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn = new br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn();
			
			if (consultaAcessoPlanosServicosForm.getPaginaSolicitada() != null) {
				paginacaoIn.setPaginaSolicitada(consultaAcessoPlanosServicosForm.getPaginaSolicitada());
			}
			else {
				paginacaoIn.setPaginaSolicitada(1);
			}
			paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
			if (consultaAcessoPlanosServicosForm.getNmTecnico() != null && !consultaAcessoPlanosServicosForm.getNmTecnico().equals("")) {
				parametrosBuscarListaAcessoServico.setCdServico(consultaAcessoPlanosServicosForm.getNmTecnico());
			}
			if (consultaAcessoPlanosServicosForm.getIdPlataforma() != null) {
				parametrosBuscarListaAcessoServico.setIdPlataforma(consultaAcessoPlanosServicosForm.getIdPlataforma());
			}
			if (!consultaAcessoPlanosServicosForm.getNmComercialServicoTextBox().equals("")) {
				parametrosBuscarListaAcessoServico.setNmComercial(consultaAcessoPlanosServicosForm.getNmComercialServicoTextBox());
			}
			if (consultaAcessoPlanosServicosForm.getNmComercialServico() != null && !consultaAcessoPlanosServicosForm.getNmComercialServico().equals("")) {
				parametrosBuscarListaAcessoServico.setNmComercial(consultaAcessoPlanosServicosForm.getNmComercialServico());
			}
			if (!consultaAcessoPlanosServicosForm.getNmComercialServicoTextBox2().equals("")) {
				parametrosBuscarListaAcessoServico.setNmComercial(consultaAcessoPlanosServicosForm.getNmComercialServicoTextBox2());
			}
			
			if (consultaAcessoPlanosServicosForm.getIdServico() != null && !consultaAcessoPlanosServicosForm.getIdServico().equals("")) {
				
				String[] idsServico = consultaAcessoPlanosServicosForm.getIdServico().split(",");
				int count = idsServico.length;
				long[] listaIdServicos = new long[count];
				
				for (int i=0;i < count;i++) {
					listaIdServicos[i] = Long.parseLong(idsServico[i]);
				}
				parametrosBuscarListaAcessoServico.setListaIdServico(listaIdServicos);
			}
			
			if (consultaAcessoPlanosServicosForm.getIdsPerfilCheck() != null && !consultaAcessoPlanosServicosForm.getIdsPerfilCheck().equals("")) {
				
				String[] perfilcheck = consultaAcessoPlanosServicosForm.getIdsPerfilCheck().split(",");
				int count = perfilcheck.length;
				long[] listaIdPerfils = new long[count];
				
				for (int i=0;i < count;i++) {
					listaIdPerfils[i] = Long.parseLong(perfilcheck[i]);
				}
				parametrosBuscarListaAcessoServico.setListaIdPerfil(listaIdPerfils);
			}
			
			if (consultaAcessoPlanosServicosForm.getIdsPerfilRadio() != null && !consultaAcessoPlanosServicosForm.getIdsPerfilRadio().equals("")) {
				
				String[] perfilradio = consultaAcessoPlanosServicosForm.getIdsPerfilRadio().split(",");
				int count = perfilradio.length;
				long[] listaIdPerfils = new long[count];
				
				for (int i=0;i < count;i++) {
					listaIdPerfils[i] = Long.parseLong(perfilradio[i]);
				}
				parametrosBuscarListaAcessoServico.setListaIdPerfil(listaIdPerfils);
			}
			
			AcessoPortTypeProxy acessoPortTypeProxy = new AcessoPortTypeProxy();
			BuscarListaAcessoServicoRequest buscarListaAcessoServicoRequest = new BuscarListaAcessoServicoRequest();
			BuscarListaAcessoServicoResponse buscarListaAcessoServicoResponse = new BuscarListaAcessoServicoResponse();
			
			parametrosBuscarListaAcessoServico.setPaginacaoIn(paginacaoIn);
			buscarListaAcessoServicoRequest.setParametrosBuscarListaAcessoServico(parametrosBuscarListaAcessoServico);
		
			try {
				buscarListaAcessoServicoResponse = acessoPortTypeProxy.buscarListaAcessoServico(buscarListaAcessoServicoRequest,this.getUserName(),this.getPassword());			

			} catch (AxisFault ex) {		
				this.AxisFaultExceptionHandler(ex, ConsultaAcessoPlanosServicosAction.class.getName(), ex.getMessage(), consultaAcessoPlanosServicosForm, response );
				return null;
			}	

			ResultadoBuscarListaAcessoServico resultadoBuscarListaAcessoServico = buscarListaAcessoServicoResponse.getResultadoBuscarListaAcessoServico();
			ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico[] acessoServicoList = resultadoBuscarListaAcessoServico.getListaAcessoServico();
			br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut paginacaoOut = resultadoBuscarListaAcessoServico.getPaginacaoOut();
			if (paginacaoOut != null) {
				this.tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(),request);
			}
			
			consultaAcessoPlanosServicosForm.setArrayListaAcessoServico(acessoServicoList);
			request.setAttribute("idTipoPesquisa", consultaAcessoPlanosServicosForm.getIdTpPesquisa());	
			
			return mapping.findForward("servicos");
		}
		return null;
	}

	public ActionForward exportar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws CatalogoPRSException, IOException, LoginException {
	
		ConsultaAcessoPlanosServicosForm consultaAcessoPlanosServicosForm = (ConsultaAcessoPlanosServicosForm)form;		
		
		if (consultaAcessoPlanosServicosForm.getIdExporte() == null || consultaAcessoPlanosServicosForm.getIdExporte().equals("")) {
			CatalogoPRSException ex = new CatalogoPRSException("Obrigat&oacute;rio selecionar dados para exporta&ccedil;&atilde;o.");
			this.CatalogoPRSExceptionHandler(ex, ConsultaAcessoPlanosServicosAction.class.getName(), response);
			return null;
			
		}
		
		UserCatalogo usuarioLogado = (UserCatalogo) request.getSession().getAttribute("logged_user");
		LoginProcess loginProcess = SCAUtils.verify(usuarioLogado);
		Map<Long, String> perfil = SCAUtils.obterPerfisVivo360(loginProcess, usuarioLogado);
		
		if (consultaAcessoPlanosServicosForm.getIdTpPesquisa() == 1) {
			BuscarListaAcessoPlanoPorIdRequest buscarListaAcessoPlanoPorIdRequest = new BuscarListaAcessoPlanoPorIdRequest();
			ParametrosBuscarListaAcessoPlanoPorId parametrosBuscarListaAcessoPlanoPorId = new ParametrosBuscarListaAcessoPlanoPorId();
			AcessoPortTypeProxy acessoPortTypeProxy = new AcessoPortTypeProxy();
			
			if (consultaAcessoPlanosServicosForm.getIdExporte() != null && !consultaAcessoPlanosServicosForm.getIdExporte().equals("")) {
				
				String[] exportePlano = consultaAcessoPlanosServicosForm.getIdExporte().split(",");
				int count = exportePlano.length;
				long[] listaIdExportePlano = new long[count];
				
				for (int i=0;i < count;i++) {
					listaIdExportePlano[i] = Long.parseLong(exportePlano[i]);
				}
				parametrosBuscarListaAcessoPlanoPorId.setListaAcessoPlano(listaIdExportePlano);
			}
			
			buscarListaAcessoPlanoPorIdRequest.setParametrosBuscarListaAcessoPlanoPorId(parametrosBuscarListaAcessoPlanoPorId);
			
			BuscarListaAcessoPlanoPorIdResponse buscarListaAcessoPlanoPorIdResponse = new BuscarListaAcessoPlanoPorIdResponse();
			
			try {
				buscarListaAcessoPlanoPorIdResponse = acessoPortTypeProxy.buscarListaAcessoPlanoPorId(buscarListaAcessoPlanoPorIdRequest,this.getUserName(),this.getPassword());

			} catch (AxisFault ex) {		
				this.AxisFaultExceptionHandler(ex, ConsultaAcessoPlanosServicosAction.class.getName(), ex.getMessage(), consultaAcessoPlanosServicosForm, response );
			}			

			List<ResultadoBuscarListaAcessoPlanoPorIdListaAcessoPlanoPorIdAcessoPlano> acessoPlanoList = new ArrayList<ResultadoBuscarListaAcessoPlanoPorIdListaAcessoPlanoPorIdAcessoPlano>();
			if (buscarListaAcessoPlanoPorIdResponse.getResultadoBuscarListaAcessoPlanoPorId() != null) {
				
				acessoPlanoList = Arrays.asList(buscarListaAcessoPlanoPorIdResponse.getResultadoBuscarListaAcessoPlanoPorId().getListaAcessoPlanoPorId());
				
			
			
			StringBuffer sb = new StringBuffer();
			sb.append("Nome Comercial;Perfil;Restrição para Consulta;Restrição para Ativação;Restrição para Desativação; Alterado por; Data da ultima Alteração\n\n");
			for (ResultadoBuscarListaAcessoPlanoPorIdListaAcessoPlanoPorIdAcessoPlano acesso : acessoPlanoList) {
				sb.append(acesso.getNmComercial());
				sb.append(";");
				sb.append(perfil.get(acesso.getIdPerfil()));
				sb.append(";");
				if (acesso.getInRestricaoConsulta().equalsIgnoreCase("S")) {
					sb.append("Sim");
				}
				else {
					sb.append("Não");
				}
				sb.append(";");
				if (acesso.getInRestricaoAtivacao().equalsIgnoreCase("S")) {
					sb.append("Sim");
				}
				else {
					sb.append("Não");
				}
				sb.append(";");

				if (acesso.getInRestricaoDesativacao().equalsIgnoreCase("S")) {
					sb.append("Sim");
				}
				else {
					sb.append("Não");
				}
				sb.append(";");
				if(acesso.getNmUsuarioAlteracao() == null) {
					sb.append("");
				}
				else {
					sb.append(acesso.getNmUsuarioAlteracao());
				}
				sb.append(";");
				if(acesso.getDtUltimaAlteracao() == null) {
					sb.append("");
				}
				else {
					sb.append(acesso.getDtUltimaAlteracao());
				}
				sb.append(";");
				sb.append("\n");
			}

			response.setContentType("application/vnd.ms-excel;charset=iso-8859-1");
			response.addHeader("Content-Disposition", "attachment;filename=PlanosPorPerfil.csv");
			response.setCharacterEncoding("ISO-8859-1");
			PrintWriter out = response.getWriter();
			out.write(sb.toString());			
			out.flush();
			}
			return null;
		} else if (consultaAcessoPlanosServicosForm.getIdTpPesquisa() == 2) {
			BuscarListaAcessoServicoPorIdRequest buscarListaAcessoServicoPorIdRequest = new BuscarListaAcessoServicoPorIdRequest();
			ParametrosBuscarListaAcessoServicoPorId parametrosBuscarListaAcessoServicoPorId = new ParametrosBuscarListaAcessoServicoPorId();
			AcessoPortTypeProxy acessoPortTypeProxy = new AcessoPortTypeProxy();
			
			if (consultaAcessoPlanosServicosForm.getIdExporte() != null && !consultaAcessoPlanosServicosForm.getIdExporte().equals("")) {
				
				String[] exportePlano = consultaAcessoPlanosServicosForm.getIdExporte().split(",");
				int count = exportePlano.length;
				long[] listaIdExportePlano = new long[count];
				
				for (int i=0;i < count;i++) {
					listaIdExportePlano[i] = Long.parseLong(exportePlano[i]);
				}
				parametrosBuscarListaAcessoServicoPorId.setListaAcessoServico(listaIdExportePlano);
			}
			
			buscarListaAcessoServicoPorIdRequest.setParametrosBuscarListaAcessoServicoPorId(parametrosBuscarListaAcessoServicoPorId);
			
			BuscarListaAcessoServicoPorIdResponse buscarListaAcessoServicoPorIdResponse = new BuscarListaAcessoServicoPorIdResponse();
			
			try {
				buscarListaAcessoServicoPorIdResponse = acessoPortTypeProxy.buscarListaAcessoServicoPorId(buscarListaAcessoServicoPorIdRequest,this.getUserName(),this.getPassword());

			} catch (AxisFault ex) {		
				this.AxisFaultExceptionHandler(ex, ConsultaAcessoPlanosServicosAction.class.getName(), ex.getMessage(), consultaAcessoPlanosServicosForm, response );
			}
			
			List<ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico> acessoServicoList = Arrays.asList(buscarListaAcessoServicoPorIdResponse.getResultadoBuscarListaAcessoServicoPorId().getListaAcessoServicoPorId());
			StringBuffer sb = new StringBuffer();
			sb.append("Nome Comercial;Perfil;Restrição para Consulta;Restrição para Ativação;Restrição para Desativação; Alterado por; Data da ultima Alteração\n\n");
			for (ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico acesso : acessoServicoList) {
				sb.append(acesso.getNmComercial());
				sb.append(";");
				sb.append(perfil.get(acesso.getIdPerfil()));
				sb.append(";");
				if (acesso.getInRestricaoConsulta().equalsIgnoreCase("S")) {
					sb.append("Sim");
				}
				else {
					sb.append("Não");
				}
				sb.append(";");
				if (acesso.getInRestricaoAtivacao().equalsIgnoreCase("S")) {
					sb.append("Sim");
				}
				else {
					sb.append("Não");
				}
				sb.append(";");

				if (acesso.getInRestricaoDesativacao().equalsIgnoreCase("S")) {
					sb.append("Sim");
				}
				else {
					sb.append("Não");
				}
				sb.append(";");
				if(acesso.getNmUsuarioAlteracao()==null) {
					sb.append("");
				}
				else {
					sb.append(acesso.getNmUsuarioAlteracao());
				}
				sb.append(";");
				if(acesso.getDtUltimaAlteracao()==null) {
					sb.append("");
				}
				else {
					sb.append(acesso.getDtUltimaAlteracao());
				}
				sb.append(";");
				sb.append("\n");
			}

			response.setContentType("application/vnd.ms-excel;charset=iso-8859-1");
			response.addHeader("Content-Disposition", "attachment;filename=ServicosPorPerfil.csv");
			response.setCharacterEncoding("ISO-8859-1");
			PrintWriter out = response.getWriter();
			out.write(sb.toString());
			out.flush();
			return null;
		} else if (consultaAcessoPlanosServicosForm.getIdTpPesquisa() == 3) {
			BuscarListaAcessoPlanoPorIdRequest buscarListaAcessoPlanoPorIdRequest = new BuscarListaAcessoPlanoPorIdRequest();
			ParametrosBuscarListaAcessoPlanoPorId parametrosBuscarListaAcessoPlanoPorId = new ParametrosBuscarListaAcessoPlanoPorId();
			AcessoPortTypeProxy acessoPortTypeProxy = new AcessoPortTypeProxy();
			
			if (consultaAcessoPlanosServicosForm.getIdExporte() != null && !consultaAcessoPlanosServicosForm.getIdExporte().equals("")) {
				
				String[] exportePlano = consultaAcessoPlanosServicosForm.getIdExporte().split(",");
				int count = exportePlano.length;
				long[] listaIdExportePlano = new long[count];
				
				for (int i=0;i < count;i++) {
					listaIdExportePlano[i] = Long.parseLong(exportePlano[i]);
				}
				parametrosBuscarListaAcessoPlanoPorId.setListaAcessoPlano(listaIdExportePlano);
			}

			buscarListaAcessoPlanoPorIdRequest.setParametrosBuscarListaAcessoPlanoPorId(parametrosBuscarListaAcessoPlanoPorId);
			
			BuscarListaAcessoPlanoPorIdResponse buscarListaAcessoPlanoPorIdResponse = new BuscarListaAcessoPlanoPorIdResponse();
			
			try {
				buscarListaAcessoPlanoPorIdResponse = acessoPortTypeProxy.buscarListaAcessoPlanoPorId(buscarListaAcessoPlanoPorIdRequest,this.getUserName(),this.getPassword());

			} catch (AxisFault ex) {		
				this.AxisFaultExceptionHandler(ex, ConsultaAcessoPlanosServicosAction.class.getName(), ex.getMessage(), consultaAcessoPlanosServicosForm, response );
			}
			
			List<ResultadoBuscarListaAcessoPlanoPorIdListaAcessoPlanoPorIdAcessoPlano> acessoPlanoList = Arrays.asList(buscarListaAcessoPlanoPorIdResponse.getResultadoBuscarListaAcessoPlanoPorId().getListaAcessoPlanoPorId());
			StringBuffer sb = new StringBuffer();
			sb.append("Perfil;Nome Comercial;Restrição para Consulta;Restrição para Ativação;Restrição para Desativação; Alterado por; Data da ultima Alteração\n\n");
			for (ResultadoBuscarListaAcessoPlanoPorIdListaAcessoPlanoPorIdAcessoPlano acesso : acessoPlanoList) {
				sb.append(perfil.get(acesso.getIdPerfil()));
				sb.append(";");
				sb.append(acesso.getNmComercial());
				sb.append(";");
				if (acesso.getInRestricaoConsulta().equalsIgnoreCase("S")) {
					sb.append("Sim");
				}
				else {
					sb.append("Não");
				}
				sb.append(";");
				if (acesso.getInRestricaoAtivacao().equalsIgnoreCase("S")) {
					sb.append("Sim");
				}
				else {
					sb.append("Não");
				}
				sb.append(";");

				if (acesso.getInRestricaoDesativacao().equalsIgnoreCase("S")) {
					sb.append("Sim");
				}
				else {
					sb.append("Não");
				}
				sb.append(";");
				if(acesso.getNmUsuarioAlteracao()==null) {
					sb.append("");
				}
				else {
					sb.append(acesso.getNmUsuarioAlteracao());
				}
				sb.append(";");
				if(acesso.getDtUltimaAlteracao()==null) {
					sb.append("");
				}
				else {
					sb.append(acesso.getDtUltimaAlteracao());
				}
				sb.append(";");
				sb.append("\n");
			}

			response.setContentType("application/vnd.ms-excel;charset=iso-8859-1");
			response.addHeader("Content-Disposition", "attachment;filename=PerfilPorPlano.csv");
			response.setCharacterEncoding("ISO-8859-1");
			PrintWriter out = response.getWriter();
			out.write(sb.toString());
			out.flush();
			return null;
		} else if (consultaAcessoPlanosServicosForm.getIdTpPesquisa() == 4) {
			BuscarListaAcessoServicoPorIdRequest buscarListaAcessoServicoPorIdRequest = new BuscarListaAcessoServicoPorIdRequest();
			ParametrosBuscarListaAcessoServicoPorId parametrosBuscarListaAcessoServicoPorId = new ParametrosBuscarListaAcessoServicoPorId();
			AcessoPortTypeProxy acessoPortTypeProxy = new AcessoPortTypeProxy();
			
			if (consultaAcessoPlanosServicosForm.getIdExporte() != null && !consultaAcessoPlanosServicosForm.getIdExporte().equals("")) {
				
				String[] exportePlano = consultaAcessoPlanosServicosForm.getIdExporte().split(",");
				int count = exportePlano.length;
				long[] listaIdExportePlano = new long[count];
				
				for (int i=0;i < count;i++) {
					listaIdExportePlano[i] = Long.parseLong(exportePlano[i]);
				}
				parametrosBuscarListaAcessoServicoPorId.setListaAcessoServico(listaIdExportePlano);
			}
			
			buscarListaAcessoServicoPorIdRequest.setParametrosBuscarListaAcessoServicoPorId(parametrosBuscarListaAcessoServicoPorId);
			
			BuscarListaAcessoServicoPorIdResponse buscarListaAcessoServicoPorIdResponse = new BuscarListaAcessoServicoPorIdResponse();
			
			try {
				buscarListaAcessoServicoPorIdResponse = acessoPortTypeProxy.buscarListaAcessoServicoPorId(buscarListaAcessoServicoPorIdRequest,this.getUserName(),this.getPassword());

			} catch (AxisFault ex) {		
				this.AxisFaultExceptionHandler(ex, ConsultaAcessoPlanosServicosAction.class.getName(), ex.getMessage(), consultaAcessoPlanosServicosForm, response );
			}
			
			List<ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico> acessoServicoList = Arrays.asList(buscarListaAcessoServicoPorIdResponse.getResultadoBuscarListaAcessoServicoPorId().getListaAcessoServicoPorId());
			StringBuffer sb = new StringBuffer();
			sb.append("Perfil;Nome Comercial;Restrição para Consulta;Restrição para Ativação;Restrição para Desativação; Alterado por; Data da ultima Alteração\n\n");
			for (ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico acesso : acessoServicoList) {
				sb.append(perfil.get(acesso.getIdPerfil()));
				sb.append(";");
				sb.append(acesso.getNmComercial());
				sb.append(";");
				if (acesso.getInRestricaoConsulta().equalsIgnoreCase("S")) {
					sb.append("Sim");
				}
				else {
					sb.append("Não");
				}
				sb.append(";");
				if (acesso.getInRestricaoAtivacao().equalsIgnoreCase("S")) {
					sb.append("Sim");
				}
				else {
					sb.append("Não");
				}
				sb.append(";");

				if (acesso.getInRestricaoDesativacao().equalsIgnoreCase("S")) {
					sb.append("Sim");
				}
				else {
					sb.append("Não");
				}
				sb.append(";");
				if(acesso.getNmUsuarioAlteracao()==null) {
					sb.append("");
				}
				else {
					sb.append(acesso.getNmUsuarioAlteracao());
				}
				sb.append(";");
				if(acesso.getDtUltimaAlteracao()==null) {
					sb.append("");
				}
				else {
					sb.append(acesso.getDtUltimaAlteracao());
				}
				sb.append(";");
				sb.append("\n");
			}

			response.setContentType("application/vnd.ms-excel;charset=iso-8859-1");
			response.addHeader("Content-Disposition", "attachment;filename=PerfilPorServico.csv");
			response.setCharacterEncoding("ISO-8859-1");
			PrintWriter out = response.getWriter();
			out.write(sb.toString());
			out.flush();
			return null;
		}
		return null;
	}
	
	public Map<Long, String> getListaPerfilSCA() {
		return listaPerfilSCA;
	}
	public void setListaPerfilSCA(Map<Long, String> listaPerfilSCA) {
		this.listaPerfilSCA = listaPerfilSCA;
	}	
}