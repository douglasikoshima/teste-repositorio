package br.com.vivo.catalogoPRS.pageflows.param.acesso.planosServicos;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.axis.AxisFault;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.catalogoPRS.bo.UserCatalogo;
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.ParametrizacaoAcessoForm;
import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;
import br.com.vivo.catalogoPRS.util.PerfilSCA;
import br.com.vivo.catalogoPRS.util.ProfileSys;
import br.com.vivo.catalogoPRS.util.SCAUtils;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.mc.geral.ErroInfo;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.AcessoPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.AlterarListaAcessoPlanoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.AlterarListaAcessoServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.BuscarListaAcessoPlanoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.BuscarListaAcessoPlanoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.BuscarListaAcessoServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.BuscarListaAcessoServicoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.CopiarPerfilRequest;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ExcluirAcessoPlanoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ExcluirAcessoServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.IncluirAcessoPlanoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.IncluirAcessoPlanoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.IncluirAcessoServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.IncluirAcessoServicoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoPlano;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlanoInRestricaoAtivacao;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlanoInRestricaoConsulta;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlanoInRestricaoDesativacao;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoServico;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoServicoListaAcessoServicoAcessoServico;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoServicoListaAcessoServicoAcessoServicoInRestricaoAtivacao;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoServicoListaAcessoServicoAcessoServicoInRestricaoConsulta;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarListaAcessoServicoListaAcessoServicoAcessoServicoInRestricaoDesativacao;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosBuscarListaAcessoPlano;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosBuscarListaAcessoServico;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosCopiarPerfil;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosExcluirAcessoPlano;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosExcluirAcessoServico;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoPlano;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoPlanoInRestricaoAtivacao;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoPlanoInRestricaoConsulta;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoPlanoInRestricaoDesativacao;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoServico;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoServicoInRestricaoAtivacao;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoServicoInRestricaoConsulta;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoServicoInRestricaoDesativacao;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoPlano;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoPlanoListaAcessoPlanoAcessoPlano;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoServico;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarListaGrupoServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.BuscarListaGrupoServicoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.GrupoServicoPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ParametrosListarGrupoServico;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ResultadoListarGrupoServicoCategoria;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarListaPlanoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.BuscarListaPlanoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ParametrosBuscarListaPlano;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PlanoPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoListaPlanoPlano;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.BuscarListaPlataformaRequest;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.BuscarListaPlataformaResponse;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.PlataformaPortTypeProxy;
import br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.ResultadoBuscarListaPlataformaPlataforma;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaServicoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaTipoServicoRequest;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.BuscarListaTipoServicoResponse;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.PaginacaoIn;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.PaginacaoOut;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosListarServico;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosListarServicoParametrosServicoIn;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoListaServicoRetornoServico;
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
import pt.ptinovacao.sca.LoginProcess;

public class ParametrizacaoAcessoPlanosServicosAction extends BaseMappingDispatchAction {
	
	private String[] idsPerfil;
	private Map<Long, ProfileSys> perfilScaMap;
	private Map<Long, String> perfilSCA;
	
	private static Logger logger = Logger.getLogger(ParametrizacaoAcessoPlanosServicosAction.class);
	
	private Long idPlano;	
	private static final String SUCCESS = "success";
	private static final String ERROR ="error";
	
	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "parametrizacaoAcessoPlanosServicos.jsp") })
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException, ErroInfo, RemoteException {
		
		ParametrizacaoAcessoForm formulario = (ParametrizacaoAcessoForm)form;
		ActionForward actionForw = new ActionForward();
		
		PlataformaPortTypeProxy plataformaPortTypeProxy = new PlataformaPortTypeProxy();
		BuscarListaPlataformaRequest buscarListaPlataformaRequest = new BuscarListaPlataformaRequest();

		BuscarListaPlataformaResponse buscarListaPlataformaResponse = new BuscarListaPlataformaResponse();
				
		try {
			buscarListaPlataformaResponse = plataformaPortTypeProxy.buscarListaPlataforma(buscarListaPlataformaRequest, this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'begin' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );	
		}
		
		List<ResultadoBuscarListaPlataformaPlataforma> plataformaList = null;
		if (buscarListaPlataformaResponse != null) {
			plataformaList = Arrays.asList(buscarListaPlataformaResponse.getResultadoBuscarListaPlataforma());
			
		}
		
		 
		
		request.setAttribute("plataformas", plataformaList);
		
		if (actionForw == null) {
			return actionForw;			
		} else {
			return mapping.findForward(SUCCESS);
		}
		
	}
	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupPerfilSCAPlanoPerfilServicoPerfil.jsp") })
	public ActionForward buscarListaPerfilSCAPlanoPerfilServicoPerfil(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException {
		
		ParametrizacaoAcessoForm formulario = (ParametrizacaoAcessoForm)form;
		
		String perfilLista = formulario.getHidden_lista_perfil_plano_perfil();
		
		if (perfilLista != null && perfilLista.trim().length() > 0) {
			formulario.setPerfilSelecionados(perfilLista.split(","));
		}
		
        UserCatalogo usuarioLogado = (UserCatalogo) request.getSession().getAttribute("logged_user");
		LoginProcess loginProcess = SCAUtils.verify(usuarioLogado);
		Map<Long, String> userProfilesByIdProfile = SCAUtils.obterPerfisVivo360(loginProcess, usuarioLogado);

		if(userProfilesByIdProfile != null && userProfilesByIdProfile.size() > 0) {
			formulario.setListaPerfilSCA(userProfilesByIdProfile);
		}
		Set<Long> keys = userProfilesByIdProfile.keySet();
		ArrayList<PerfilSCA> listaPerfil = new ArrayList<PerfilSCA>();
		for (Long key : keys) {
			PerfilSCA perfil = new PerfilSCA();
			perfil.setIdPerfilSCA(key);
			perfil.setNmPerfilSCA(userProfilesByIdProfile.get(key));
			listaPerfil.add(perfil);
		}
		Collections.sort(listaPerfil);
	
		PerfilSCA[] arrayPerfil = (PerfilSCA[])listaPerfil.toArray(new PerfilSCA[listaPerfil.size()]);
		
		formulario.setArrayPerfil(arrayPerfil);
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupPerfilSCANovaRestricaoPlanoServico.jsp") })
	public ActionForward buscarListaPerfilSCANovaRestricaoPlanoServico(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException {
		
		ParametrizacaoAcessoForm formulario = (ParametrizacaoAcessoForm)form;
		
		String perfilLista = request.getParameter("hidden_lista_perfil_nova_restricao_plano_servico");
		if (perfilLista != null && perfilLista.trim().length() > 0) {
			formulario.setPerfilSelecionados(perfilLista.split(","));
		}
		UserCatalogo usuarioLogado = (UserCatalogo) request.getSession().getAttribute("logged_user");
		LoginProcess loginProcess = SCAUtils.verify(usuarioLogado);
		Map<Long, ProfileSys> userProfilesByIdProfile = SCAUtils.obterPerfisVivo360CallCenter(loginProcess, usuarioLogado);
		
		if(userProfilesByIdProfile != null && userProfilesByIdProfile.size() > 0) {
			this.setPerfilScaMap(userProfilesByIdProfile);
			formulario.setPerfilScaMap(userProfilesByIdProfile);
		}
		Set<Long> keys = userProfilesByIdProfile.keySet();
		List<PerfilSCA> listaPerfil = new ArrayList<PerfilSCA>();
		for (Long key : keys) {
			PerfilSCA perfil = new PerfilSCA();
			ProfileSys profileSys = userProfilesByIdProfile.get(key);
			perfil.setIdPerfilSCA(profileSys.getId());
			perfil.setIdSistema(profileSys.getIdSistema());
			perfil.setNmPerfilSCA(profileSys.getName());
			listaPerfil.add(perfil);
		}
		Collections.sort(listaPerfil);
		PerfilSCA[] arrayPerfil = (PerfilSCA[])listaPerfil.toArray(new PerfilSCA[listaPerfil.size()]);
		
		formulario.setArrayPerfil(arrayPerfil);
		request.setAttribute("listaPerfil", listaPerfil);
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupPerfilSCAPerfilPlanoPerfilServico.jsp") })
	public ActionForward buscarListaPerfilSCAPerfilPlanoPerfilServico(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException {
		ParametrizacaoAcessoForm formulario = (ParametrizacaoAcessoForm)form;
		
		String perfilLista = request.getParameter("hidden_lista_perfil_servico_perfil");
		if (perfilLista != null && perfilLista.trim().length() > 0) {
			formulario.setPerfilSelecionados(perfilLista.split(","));
		}
		
		UserCatalogo usuarioLogado = (UserCatalogo) request.getSession().getAttribute("logged_user");
		LoginProcess loginProcess = SCAUtils.verify(usuarioLogado);
		Map<Long, String> userProfilesByIdProfile = SCAUtils.obterPerfisVivo360(loginProcess, usuarioLogado);

		if(userProfilesByIdProfile != null && userProfilesByIdProfile.size() > 0) {
			this.setPerfilSCA(userProfilesByIdProfile);
			
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
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

/*	@Jpf.Action(forwards = { @Jpf.Forward(name = "successPerfilPlano", path = "resultadoPesquisaPerfilPlano.jsp"),
			 @Jpf.Forward(name = "successPerfilServico", path = "resultadoPesquisaPerfilServico.jsp"),
			 @Jpf.Forward(name = "successPlanoPerfil", path = "resultadoPesquisaPlanoPerfil.jsp"),
			 @Jpf.Forward(name = "successServicoPerfil", path = "resultadoPesquisaServicoPerfil.jsp")})*/
	public ActionForward pesquisarAcessoPlanosServicos(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException, ErroInfo, RemoteException {
		ActionForward retorno;
		ParametrizacaoAcessoForm formulario = (ParametrizacaoAcessoForm)form;
		request.setAttribute("perfilSCA", perfilSCA);
		request.setAttribute("perfilScaMap", perfilScaMap);
		
		String mappingForward = "";
		
		if(formulario.getTpPesquisa().trim().length() > 0 && formulario.getTpPesquisa().equalsIgnoreCase("planoPerfil")) {
			retorno = pesquisarAcessoPlanosPorPlanoPerfil(formulario, request, response);
			
			if (retorno != null) {
				mappingForward = "successPlanoPerfil";
			} else {
				return null;
			}			
		}
		
		if(formulario.getTpPesquisa().trim().length() > 0 && formulario.getTpPesquisa().equalsIgnoreCase("servicoPerfil")) {
			retorno = pesquisarAcessoServicosPorServicoPerfil(formulario, request, response);
			
			if (retorno != null) {
				mappingForward = "successServicoPerfil";
			} else {
				return null;
			}			
		}
		
		if(formulario.getTpPesquisa().trim().length() > 0 && formulario.getTpPesquisa().equalsIgnoreCase("perfilPlano")) {
			retorno = pesquisarAcessoPlanosPorPerfilPlano(formulario, request, response);
			
			if (retorno != null) { 
				mappingForward = "successPerfilPlano";
			} else {
				return null;
			}			
			
		}
		
		if(formulario.getTpPesquisa().trim().length() > 0 && formulario.getTpPesquisa().equalsIgnoreCase("perfilServico")) {
			retorno = pesquisarAcessoServicosPorPerfilServico(formulario, request, response);
			
			if (retorno != null) {
				mappingForward = "successPerfilServico";				
			} else {
				return null;				
			}			
		}
						
		return mapping.findForward(mappingForward);
		
	}
	
/*	@Jpf.Action(forwards = {@Jpf.Forward(name = "planoPerfil", path = "popupNmComercialPlanoPerfil.jsp"),
			@Jpf.Forward(name = "servicoPerfil", path = "popupNmComercialServicoPerfil.jsp"),
			@Jpf.Forward(name = "perfilPlano", path = "popupNmComercialPerfilPlano.jsp"),
			@Jpf.Forward(name = "perfilServico", path = "popupNmComercialPerfilServico.jsp") })*/
	public ActionForward buscarListaNmComercial(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ParametrizacaoAcessoForm formulario = (ParametrizacaoAcessoForm)form;		
		ActionForward retorno = new ActionForward();
		
		
		String mappingForward = "";			
		String parameter = request.getParameter("tp_pesquisa");
		if(parameter.trim().length() > 0 && parameter.equalsIgnoreCase("planoPerfil")) {						
			retorno = buscarListaNmComercialPlanosPerfilComRadio(request, response, formulario);
			
			if (retorno == null) {
				return null;
			} else {
				mappingForward = "planoPerfil";					
			}
					
		}
		if(parameter.trim().length() > 0 && parameter.equalsIgnoreCase("servicoPerfil")) {
			retorno = buscarListaNmComercialServicoPerfilComRadio(request, response, formulario);
			
			if (retorno == null) {
				return null;
			} else {
				mappingForward = "servicoPerfil";
			}
		}
		if(parameter.trim().length() > 0 && parameter.equalsIgnoreCase("perfilPlano")) {
			retorno = buscarListaNmComercialPerfilPlanosComCheckBox(request, response, formulario);
			
			if (retorno == null) {
				return null;
			} else {
				mappingForward = "perfilPlano";	
			}
					
		}
		if(parameter.trim().length() > 0 && parameter.equalsIgnoreCase("perfilServico")) {
			retorno = buscarListaNmComercialPerfilServicoComCheckBox(request, response, formulario);
			
			if (retorno == null) {
				return null;
			} else {
				mappingForward = "perfilServico";
			}
		}
		
		return mapping.findForward(mappingForward);
		
	}

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "resultadoPesquisaNmComercialNoPopupPlanoPerfil.jsp") })
	public ActionForward pesquisarNmComercialNoPopupPlanoPerfil(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws ServerException, LoginException, br.com.vivo.catalogoPRS.ws.catalogoPlano.mc.geral.ErroInfo, RemoteException {
	
		ActionForward actionForw = new ActionForward();
		
		ParametrizacaoAcessoForm formulario = (ParametrizacaoAcessoForm)form;
		
		PlanoPortTypeProxy planoPortTypeProxy = new PlanoPortTypeProxy();		
		BuscarListaPlanoRequest buscarListaPlanoRequest = new BuscarListaPlanoRequest();
		
		
		ParametrosBuscarListaPlano parametrosBuscarListaPlano = new ParametrosBuscarListaPlano();
		if(formulario.getIdPlataforma() != null && formulario.getIdPlataforma() != 0) {
			parametrosBuscarListaPlano.setIdPlataforma(formulario.getIdPlataforma());
		}
		if(formulario.getIdTecnologia() != null && formulario.getIdTecnologia() != 0) {
			parametrosBuscarListaPlano.setIdTecnologia(formulario.getIdTecnologia());
		}
		if(formulario.getIdUf() != null && formulario.getIdUf() != 0 ) {
			parametrosBuscarListaPlano.setIdUF(formulario.getIdUf());
		}
		if(formulario.getNmComPlanoPerfilPop() != null && formulario.getNmComPlanoPerfilPop().trim().length() > 0) {
			parametrosBuscarListaPlano.setNmComercial(formulario.getNmComPlanoPerfilPop());
		}
		if(formulario.getNmTecnico() != null && formulario.getNmTecnico().trim().length() > 0) {
			parametrosBuscarListaPlano.setCdPlano(formulario.getNmTecnico());
		}
		
		buscarListaPlanoRequest.setParametrosBuscarListaPlano(parametrosBuscarListaPlano);
		
		BuscarListaPlanoResponse buscarListaPlanoResponse = new BuscarListaPlanoResponse();
		try {
			buscarListaPlanoResponse = planoPortTypeProxy.buscarListaPlano(buscarListaPlanoRequest,this.getUserName(),this.getPassword());
		}  catch (AxisFault ex) {
			logger.debug("Exception no method: 'pesquisarNmComercialNoPopupPlanoPerfil' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );	
		}
		

		ResultadoBuscarListaPlanoListaPlanoPlano[] planoLista = null;
		if (buscarListaPlanoResponse.getResultadoBuscarListaPlano() != null) {
			planoLista = buscarListaPlanoResponse.getResultadoBuscarListaPlano().getListaPlano();
		}
		
		request.setAttribute("planos", planoLista);
		formulario.setPlanoLista(planoLista);
		
		if (actionForw == null) {
			return actionForw;
		} else {
			return mapping.findForward(SUCCESS);
		}
				
	}
	
	
	
	@SuppressWarnings("static-access")
	public ActionForward salvarAlteracoesRestricoesAcessoPlano(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException, ErroInfo, RemoteException {
		
		ParametrizacaoAcessoForm formulario = (ParametrizacaoAcessoForm)form;		
		
		AlterarListaAcessoPlanoRequest alterarListaAcessoPlanoRequest = new AlterarListaAcessoPlanoRequest();
		ParametrosAlterarListaAcessoPlano parametrosAlterarListaAcessoPlano = new ParametrosAlterarListaAcessoPlano();		
		ResultadoBuscarListaAcessoPlanoListaAcessoPlanoAcessoPlano[] acessoPlanoLista = formulario.getAcessoPlanoLista();
		ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano[] listaAcessoPlano = new ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano[acessoPlanoLista.length];
		ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano parametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano;
		
		if (acessoPlanoLista.length > 0) {	
			
			for (int i = 0; i < acessoPlanoLista.length; i++) {
				ResultadoBuscarListaAcessoPlanoListaAcessoPlanoAcessoPlano resultado = acessoPlanoLista[i];
				parametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano = new ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano();
				parametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano.setIdAcessoPlano(resultado.getIdAcessoPlano());
				
				if (formulario.getInRestricaoConsulta()[i].equals("S")) {
					ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlanoInRestricaoConsulta inRestricaoConsulta = null;
					parametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano.setInRestricaoConsulta(inRestricaoConsulta.S);
				} else {
					ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlanoInRestricaoConsulta inRestricaoConsulta = null;
					parametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano.setInRestricaoConsulta(inRestricaoConsulta.N);
				}
				
				
				if (formulario.getInRestricaoAtivacao()[i].equals("S")) {				
					ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlanoInRestricaoAtivacao inRestricaoAtivacao = null;					 
					parametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano.setInRestricaoAtivacao(inRestricaoAtivacao.S);					
				} else {
					ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlanoInRestricaoAtivacao inRestricaoAtivacao = null;					 
					parametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano.setInRestricaoAtivacao(inRestricaoAtivacao.N);	
				}
				 

				if (formulario.getInRestricaoDesativacao()[i].equals("S")) {
					ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlanoInRestricaoDesativacao inRestricaoDesativacao = null;
					parametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano.setInRestricaoDesativacao(inRestricaoDesativacao.S);
				} else {
					ParametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlanoInRestricaoDesativacao inRestricaoDesativacao = null;
					parametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano.setInRestricaoDesativacao(inRestricaoDesativacao.N);
				}
				
				listaAcessoPlano[i] = parametrosAlterarListaAcessoPlanoListaAcessoPlanoAcessoPlano;
			}
		}
		
		parametrosAlterarListaAcessoPlano.setListaAcessoPlano(listaAcessoPlano);
		AcessoPortTypeProxy acessoPortTypeProxy = new AcessoPortTypeProxy();
		alterarListaAcessoPlanoRequest.setParametrosAlterarListaAcessoPlano(parametrosAlterarListaAcessoPlano);
		acessoPortTypeProxy.alterarListaAcessoPlano(alterarListaAcessoPlanoRequest, this.getUserName(),this.getPassword());
		
		PrintWriter writer = null;
		try {
			response.setContentType("text/javascript");
			writer = response.getWriter();
			writer.println("$('botao_pesquisar_acesso_plano_servico').onclick();");
			writer.flush();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			writer.close();
		}
		return null;
	}
	
	@SuppressWarnings("static-access")
	public ActionForward salvarAlteracoesRestricoesAcessoServico(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException, ErroInfo, RemoteException {
		
		ParametrizacaoAcessoForm formulario = (ParametrizacaoAcessoForm)form;

		AlterarListaAcessoServicoRequest alterarListaAcessoServicoRequest = new AlterarListaAcessoServicoRequest();
		ParametrosAlterarListaAcessoServico parametrosAlterarListaAcessoServico = new ParametrosAlterarListaAcessoServico();
		
		ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico[] acessoServicoLista = formulario.getAcessoServicoLista();
		
		ParametrosAlterarListaAcessoServicoListaAcessoServicoAcessoServico[] listaAcessoServico = new ParametrosAlterarListaAcessoServicoListaAcessoServicoAcessoServico[acessoServicoLista.length];
		
		ParametrosAlterarListaAcessoServicoListaAcessoServicoAcessoServico parametrosAlterarListaAcessoServicoListaAcessoServicoAcessoServico ;
		
		if (acessoServicoLista.length > 0 ) {
			for (int i = 0; i < acessoServicoLista.length; i++) {
				ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico resultado = acessoServicoLista[i];
				parametrosAlterarListaAcessoServicoListaAcessoServicoAcessoServico = new ParametrosAlterarListaAcessoServicoListaAcessoServicoAcessoServico();
				parametrosAlterarListaAcessoServicoListaAcessoServicoAcessoServico.setIdAcessoServico(resultado.getIdAcessoServico());
				
				if (formulario.getInRestricaoConsulta()[i].equals("S")) {										
					ParametrosAlterarListaAcessoServicoListaAcessoServicoAcessoServicoInRestricaoConsulta inRestricaoConsulta = null;
					parametrosAlterarListaAcessoServicoListaAcessoServicoAcessoServico.setInRestricaoConsulta(inRestricaoConsulta.S);
				} else {
					ParametrosAlterarListaAcessoServicoListaAcessoServicoAcessoServicoInRestricaoConsulta inRestricaoConsulta = null;
					parametrosAlterarListaAcessoServicoListaAcessoServicoAcessoServico.setInRestricaoConsulta(inRestricaoConsulta.N);
				}
								
				
				if (formulario.getInRestricaoAtivacao()[i].equals("S")) {
					ParametrosAlterarListaAcessoServicoListaAcessoServicoAcessoServicoInRestricaoAtivacao inRestricaoAtivacao = null;
					parametrosAlterarListaAcessoServicoListaAcessoServicoAcessoServico.setInRestricaoAtivacao(inRestricaoAtivacao.S);
					
				} else {
					ParametrosAlterarListaAcessoServicoListaAcessoServicoAcessoServicoInRestricaoAtivacao inRestricaoAtivacao = null;
					parametrosAlterarListaAcessoServicoListaAcessoServicoAcessoServico.setInRestricaoAtivacao(inRestricaoAtivacao.N);
				}
				
								
				if (formulario.getInRestricaoDesativacao()[i].equals("S")) {
					ParametrosAlterarListaAcessoServicoListaAcessoServicoAcessoServicoInRestricaoDesativacao inRestricaoDesativacao = null;
					parametrosAlterarListaAcessoServicoListaAcessoServicoAcessoServico.setInRestricaoDesativacao(inRestricaoDesativacao.S);
				} else {
					ParametrosAlterarListaAcessoServicoListaAcessoServicoAcessoServicoInRestricaoDesativacao inRestricaoDesativacao = null;
					parametrosAlterarListaAcessoServicoListaAcessoServicoAcessoServico.setInRestricaoDesativacao(inRestricaoDesativacao.N);
				}
				
				listaAcessoServico[i] = parametrosAlterarListaAcessoServicoListaAcessoServicoAcessoServico;				
			}			
		}
		
		parametrosAlterarListaAcessoServico.setListaAcessoServico(listaAcessoServico);		
		AcessoPortTypeProxy acessoPortTypeProxy = new AcessoPortTypeProxy();
		alterarListaAcessoServicoRequest.setParametrosAlterarListaAcessoServico(parametrosAlterarListaAcessoServico);
		acessoPortTypeProxy.alterarListaAcessoServico(alterarListaAcessoServicoRequest, this.getUserName(),this.getPassword());
		
		PrintWriter writer = null;
		try {
			response.setContentType("text/javascript");
			writer = response.getWriter();
			writer.println("$('botao_pesquisar_acesso_plano_servico').onclick();");
			writer.flush();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			writer.close();
		}
		return null;
	}
	
	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupConfirmExclusaoRestricoesAcessoPlano.jsp") })
	public ActionForward abrirPopupConfirmExclusaoRestricoesAcessoPlano(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException  {
			
		
		String idAcessoPlano = request.getParameter("id_acesso_plano");
		String inRestricaoConsulta = request.getParameter("in_restricao_consulta");
		String inRestricaoAtivacao = request.getParameter("in_restricao_ativacao");
		String inRestricaoDesativacao = request.getParameter("in_restricao_desativacao");
		if(idAcessoPlano != null && !idAcessoPlano.trim().equalsIgnoreCase("")) {
			request.setAttribute("idAcessoPlano", idAcessoPlano);
		}
		if(!inRestricaoConsulta.trim().equalsIgnoreCase("") && inRestricaoConsulta.equalsIgnoreCase("S")) {
			request.setAttribute("inRestricaoConsulta", "N");
		}else {
			request.setAttribute("inRestricaoConsulta", inRestricaoConsulta);
		}
		if(!inRestricaoAtivacao.trim().equalsIgnoreCase("") && inRestricaoAtivacao.equalsIgnoreCase("S")) {
			request.setAttribute("inRestricaoAtivacao", "N");
		}else {
			request.setAttribute("inRestricaoAtivacao", inRestricaoAtivacao);
		}
		if(!inRestricaoDesativacao.trim().equalsIgnoreCase("") && inRestricaoDesativacao.equalsIgnoreCase("S")) {
			request.setAttribute("inRestricaoDesativacao", "N");
		}else {
			request.setAttribute("inRestricaoDesativacao", inRestricaoDesativacao);
		}
		return mapping.findForward(SUCCESS);
	}

	public ActionForward excluirRestricoesAcessoPlano(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException, ErroInfo, RemoteException {
	
		ParametrizacaoAcessoForm formulario = (ParametrizacaoAcessoForm)form;
		
		ExcluirAcessoPlanoRequest excluirAcessoPlanoRequest = new ExcluirAcessoPlanoRequest();
		ParametrosExcluirAcessoPlano parametrosExcluirAcessoPlano = new ParametrosExcluirAcessoPlano();
		
		if(formulario.getIdAcessoPlanoExcluirPlano() != null ) {
			parametrosExcluirAcessoPlano.setIdAcessoPlano(formulario.getIdAcessoPlanoExcluirPlano());
		}
		
		
		AcessoPortTypeProxy acessoPortTypeProxy = new AcessoPortTypeProxy();
		excluirAcessoPlanoRequest.setParametrosExcluirAcessoPlano(parametrosExcluirAcessoPlano);
		acessoPortTypeProxy.excluirAcessoPlano(excluirAcessoPlanoRequest, this.getUserName(),this.getPassword());
		PrintWriter writer = null;
		try {
			response.setContentType("text/javascript");
			writer = response.getWriter();
			writer.println("$('botao_pesquisar_acesso_plano_servico').onclick();");
			writer.flush();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			writer.close();
		}
		return null;
	}

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupConfirmExclusaoRestricoesAcessoServico.jsp") })
	public ActionForward abrirPopupConfirmExclusaoRestricoesAcessoServico(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException {
		
		String idAcessoServico = request.getParameter("id_acesso_servico");
		String inRestricaoConsulta = request.getParameter("in_restricao_consulta");
		String inRestricaoAtivacao = request.getParameter("in_restricao_ativacao");
		String inRestricaoDesativacao = request.getParameter("in_restricao_desativacao");
		if(idAcessoServico != null && !idAcessoServico.trim().equalsIgnoreCase("")) {
			request.setAttribute("idAcessoServico", idAcessoServico);
		}
		if(!inRestricaoConsulta.trim().equalsIgnoreCase("") && inRestricaoConsulta.equalsIgnoreCase("S")) {
			request.setAttribute("inRestricaoConsulta", "N");
		}else {
			request.setAttribute("inRestricaoConsulta", inRestricaoConsulta);
		}
		if(!inRestricaoAtivacao.trim().equalsIgnoreCase("") && inRestricaoAtivacao.equalsIgnoreCase("S")) {
			request.setAttribute("inRestricaoAtivacao", "N");
		}else {
			request.setAttribute("inRestricaoAtivacao", inRestricaoAtivacao);
		}
		if(!inRestricaoDesativacao.trim().equalsIgnoreCase("") && inRestricaoDesativacao.equalsIgnoreCase("S")) {
			request.setAttribute("inRestricaoDesativacao", "N");
		}else {
			request.setAttribute("inRestricaoDesativacao", inRestricaoDesativacao);
		}
		
		
		return mapping.findForward(SUCCESS);
	}

	public ActionForward excluirRestricoesAcessoServico(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException, ErroInfo, RemoteException {
		
		ParametrizacaoAcessoForm formulario = (ParametrizacaoAcessoForm)form;

		ExcluirAcessoServicoRequest requestDocument = new ExcluirAcessoServicoRequest();
		ParametrosExcluirAcessoServico parametrosExcluirAcessoServico = new ParametrosExcluirAcessoServico();
		
		if(formulario.getIdAcessoServicoExcluirServico() != null ) {
			parametrosExcluirAcessoServico.setIdAcessoServico(formulario.getIdAcessoServicoExcluirServico());
		}

		
		AcessoPortTypeProxy acessoPortTypeProxy = new AcessoPortTypeProxy();
		acessoPortTypeProxy.excluirAcessoServico(requestDocument, this.getUserName(),this.getPassword());
		PrintWriter writer = null;
		try {
			response.setContentType("text/javascript");
			writer = response.getWriter();
			writer.println("$('botao_pesquisar_acesso_plano_servico').onclick();");
			writer.flush();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			writer.close();
		}
		return null;
	}

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "resultadoPesquisaNmComercialNoPopupServicoPerfil.jsp") })
	public ActionForward pesquisarNmComercialNoPopupServicoPerfil(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException, br.com.vivo.catalogoPRS.ws.catalogoServico.mc.geral.ErroInfo, RemoteException {		
		
		ParametrizacaoAcessoForm formulario = (ParametrizacaoAcessoForm)form;		
		ActionForward actionForw = new ActionForward();
		
		BuscarListaServicoRequest buscarListaServicoRequest = new BuscarListaServicoRequest();
		
		ParametrosListarServicoParametrosServicoIn parametrosServicoIn = new ParametrosListarServicoParametrosServicoIn();
		
		br.com.vivo.catalogoPRS.ws.catalogoServico.sn.PaginacaoIn paginacaoIn = new br.com.vivo.catalogoPRS.ws.catalogoServico.sn.PaginacaoIn();
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		parametrosServicoIn.setPaginacaoIn(paginacaoIn);
		
		
		if(formulario.getPaginaSolicitada() != null) {
			paginacaoIn.setPaginaSolicitada(formulario.getPaginaSolicitada());
		}else {
			paginacaoIn.setPaginaSolicitada(1);
		}
		
		
		if(formulario.getIdPlataforma() != null && formulario.getIdPlataforma() != 0) {
			parametrosServicoIn.setIdPlataforma(formulario.getIdPlataforma());
		}
		if(formulario.getIdGrupoServico() != null && formulario.getIdGrupoServico() != 0) {
			parametrosServicoIn.setIdCategoria(formulario.getIdGrupoServico());
		}
		if(formulario.getIdTipoServico() != null && formulario.getIdTipoServico() != 0) {
			parametrosServicoIn.setNmServico(formulario.getIdTipoServico().toString());
		}
		if(formulario.getNmComServicoPerfilPop() != null && formulario.getNmComServicoPerfilPop().trim().length() > 0) {
			parametrosServicoIn.setNmPlano(formulario.getNmComServicoPerfilPop());
		}
		if(formulario.getNmTecnico() != null && formulario.getNmTecnico().trim().length() > 0) {
			parametrosServicoIn.setCdcodigo(formulario.getNmTecnico());
		}
	
		ServicoPortTypeProxy servicoPortTypeProxy = new ServicoPortTypeProxy();
		ParametrosListarServico parametrosListarServico = new ParametrosListarServico();
		
		parametrosListarServico.setParametrosServicoIn(parametrosServicoIn);
		buscarListaServicoRequest.setParametrosListarServico(parametrosListarServico);

	    BuscarListaServicoResponse buscarListaServicoResponse = new BuscarListaServicoResponse();
	    		
	    		
	    try {
			buscarListaServicoResponse = servicoPortTypeProxy.buscarListaServico(buscarListaServicoRequest, this.getUserName(),this.getPassword() );
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'pesquisarNmComercialNoPopupServicoPerfil' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );	
		}
	    
	    
		PaginacaoOut paginacaoOut = new PaginacaoOut();
		this.tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(),request);

		
		ResultadoBuscarListaServicoListaServicoRetornoServico[] retornoServicoLista = null;
		
		if (buscarListaServicoResponse.getResultadoBuscarListaServico() != null) {			
			retornoServicoLista = buscarListaServicoResponse.getResultadoBuscarListaServico().getListaServico();
		}
		
		request.setAttribute("servicos", retornoServicoLista);
		formulario.setRetornoServicoLista(retornoServicoLista);		
		
		if (actionForw == null) {
			return actionForw;
		} else {
			return mapping.findForward(SUCCESS);
		}
	}

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "resultadoPesquisaNmComercialNoPopupPerfilPlano.jsp") })
	public ActionForward pesquisarNmComercialNoPopupPerfilPlano(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException, br.com.vivo.catalogoPRS.ws.catalogoPlano.mc.geral.ErroInfo, RemoteException {
	
		ParametrizacaoAcessoForm formulario = (ParametrizacaoAcessoForm)form;
		ActionForward actionForw = new ActionForward();
		
		BuscarListaPlanoRequest buscarListaPlanoRequest = new BuscarListaPlanoRequest();
		ParametrosBuscarListaPlano parametrosBuscarListaPlano = new ParametrosBuscarListaPlano();
		
		if(formulario.getIdPlataforma() != null && formulario.getIdPlataforma() != 0) {
			parametrosBuscarListaPlano.setIdPlataforma(formulario.getIdPlataforma());
		}
		if(formulario.getIdTecnologia() != null && formulario.getIdTecnologia() != 0) {
			parametrosBuscarListaPlano.setIdTecnologia(formulario.getIdTecnologia());
		}
		if(formulario.getIdUf() != null && formulario.getIdUf() != 0) {
			parametrosBuscarListaPlano.setIdUF(formulario.getIdUf());
		}
		if(formulario.getNmComPerfilPlanoPop() != null && formulario.getNmComPerfilPlanoPop().trim().length() > 0) {
			parametrosBuscarListaPlano.setNmComercial(formulario.getNmComPerfilPlanoPop());
		}
		if(formulario.getNmTecnico() != null && formulario.getNmTecnico().trim().length() > 0) {
			parametrosBuscarListaPlano.setCdPlano(formulario.getNmTecnico());
		}
		
		PlanoPortTypeProxy planoPortTypeProxy = new PlanoPortTypeProxy();
		buscarListaPlanoRequest.setParametrosBuscarListaPlano(parametrosBuscarListaPlano);
		BuscarListaPlanoResponse buscarListaPlanoResponse = new BuscarListaPlanoResponse();
				
		try {
			buscarListaPlanoResponse = planoPortTypeProxy.buscarListaPlano(buscarListaPlanoRequest,  this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'pesquisarNmComercialNoPopupPerfilPlano' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );	
		}
		
		
		ResultadoBuscarListaPlanoListaPlanoPlano[] planoLista = null;
		if (buscarListaPlanoResponse.getResultadoBuscarListaPlano() != null) {
			planoLista = buscarListaPlanoResponse.getResultadoBuscarListaPlano().getListaPlano();
		} 
				
		request.setAttribute("planos", planoLista);
		formulario.setPlanoLista(planoLista);
		
		if (actionForw == null) {
			return actionForw;
		} else {
			return mapping.findForward(SUCCESS);
		}
	}

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "resultadoPesquisaNmComercialNoPopupPerfilServico.jsp") })
	public ActionForward pesquisarNmComercialNoPopupPerfilServico(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException, br.com.vivo.catalogoPRS.ws.catalogoServico.mc.geral.ErroInfo, RemoteException {
	
		ParametrizacaoAcessoForm formulario = (ParametrizacaoAcessoForm)form;
		ActionForward actionForw = new ActionForward();
		
		BuscarListaServicoRequest buscarListaServicoRequest = new BuscarListaServicoRequest();
		ParametrosListarServicoParametrosServicoIn parametrosServico = new ParametrosListarServicoParametrosServicoIn();
		
		br.com.vivo.catalogoPRS.ws.catalogoServico.sn.PaginacaoIn paginacaoIn = new br.com.vivo.catalogoPRS.ws.catalogoServico.sn.PaginacaoIn();		
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		parametrosServico.setPaginacaoIn(paginacaoIn);
		
		if(formulario.getPaginaSolicitada() != null) {
			paginacaoIn.setPaginaSolicitada(formulario.getPaginaSolicitada());
		}else {
			paginacaoIn.setPaginaSolicitada(1);
		}
		
		
		if(formulario.getIdPlataforma() != null && formulario.getIdPlataforma() != 0) {
			parametrosServico.setIdPlataforma(formulario.getIdPlataforma());
		}
		if(formulario.getIdGrupoServico() != null && formulario.getIdGrupoServico() != 0) {
			parametrosServico.setIdCategoria(formulario.getIdGrupoServico());
		}
		if(formulario.getIdTipoServico() != null && formulario.getIdTipoServico() != 0 ) {
			parametrosServico.setNmServico(formulario.getIdTipoServico().toString());
		}
		if(formulario.getNmComPerfilServicoPop() != null && formulario.getNmComPerfilServicoPop().trim().length() > 0) {
			parametrosServico.setNmPlano(formulario.getNmComPerfilServicoPop());
		}
		if(formulario.getNmTecnico() != null && formulario.getNmTecnico().trim().length() > 0) {
			parametrosServico.setCdcodigo(formulario.getNmTecnico());
		}
		
		
		ServicoPortTypeProxy servicoPortTypeProxy = new ServicoPortTypeProxy();
		ParametrosListarServico parametrosListarServico = new ParametrosListarServico();
		
		parametrosListarServico.setParametrosServicoIn(parametrosServico);		
		buscarListaServicoRequest.setParametrosListarServico(parametrosListarServico);
		
		BuscarListaServicoResponse buscarListaServicoResponse = new BuscarListaServicoResponse();
				
		try {
			buscarListaServicoResponse = servicoPortTypeProxy.buscarListaServico(buscarListaServicoRequest, this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'pesquisarNmComercialNoPopupPerfilServico' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );	
		}
		
		PaginacaoOut paginacaoOut = new PaginacaoOut();		
		this.tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(),request);
		
		ResultadoBuscarListaServicoListaServicoRetornoServico[] retornoServicoLista = null; 

		if (buscarListaServicoResponse.getResultadoBuscarListaServico() != null) {
			retornoServicoLista = buscarListaServicoResponse.getResultadoBuscarListaServico().getListaServico();			
		}
						
		request.setAttribute("servicos", retornoServicoLista);
		formulario.setRetornoServicoLista(retornoServicoLista);

		if (actionForw == null) {
			return actionForw;
		} else {
			return mapping.findForward(SUCCESS);
		}
	}

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "incluirNovasRestricoesAcessoPlanoServico.jsp") })
	@SuppressWarnings("unused")
	public ActionForward incluirNovasRestricoesAcesso(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException {
	
		ParametrizacaoAcessoForm formulario = (ParametrizacaoAcessoForm)form;
		
		return mapping.findForward(SUCCESS);
	}
	
	@SuppressWarnings("static-access")
	public ActionForward gravarNovasRestricoesAcessoPlanoServico(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException, ErroInfo, RemoteException, CatalogoPRSException {
		
		ParametrizacaoAcessoForm formulario = (ParametrizacaoAcessoForm)form;
				
		if(formulario.getRadioPlano() != null && formulario.getRadioPlano().trim().equalsIgnoreCase("plano")) {
			request.getSession().setAttribute("tpPesquisa", "planoPerfil");
			
			IncluirAcessoPlanoRequest incluirAcessoPlanoRequest = new IncluirAcessoPlanoRequest();
			ParametrosIncluirAcessoPlano parametrosIncluirAcessoPlano = new ParametrosIncluirAcessoPlano();
						
			if(formulario.getIdsPerfilPpSp() != null && formulario.getIdsPerfilPpSp().trim().length() > 0) {
				
				String[] idsPerfil = formulario.getIdsPerfilPpSp().split(",");
				StringBuffer idsPerfilSCA = new StringBuffer();
				
				long[] listaIdPerfil =  new long[idsPerfil.length]; 
				long[] listaIdSistema =  new long[idsPerfil.length]; 
								
				for (int i = 0; i < idsPerfil.length; i ++) {
					String perfil = idsPerfil[i];
					StringTokenizer tokenizer = new StringTokenizer(perfil, "_");
					
					while(tokenizer.hasMoreTokens()) {
						String idPerfilSCA = tokenizer.nextToken();
						listaIdPerfil[i] = Long.valueOf(idPerfilSCA);
						
						String idSistemaOrigem = (String) tokenizer.nextElement();															
						listaIdSistema[i] = Long.valueOf(idSistemaOrigem);
						
 						idsPerfilSCA.append(idPerfilSCA+",");
					}
				}
				
				if(listaIdPerfil.length > 0) {
					parametrosIncluirAcessoPlano.setListaIdPerfil(listaIdPerfil);
				}
				
				if(listaIdSistema.length > 0) {
					parametrosIncluirAcessoPlano.setListaIdSistema(listaIdSistema);								
				} 
				request.getSession().setAttribute("idsPerfil", idsPerfilSCA.toString());
												
			}
			if(formulario.getNmComPlanoPerfilPop() != null && !formulario.getNmComPlanoPerfilPop().trim().equalsIgnoreCase("")) {
				parametrosIncluirAcessoPlano.setIdPlano(Long.valueOf(formulario.getNmComPlanoPerfilPop()));
				request.getSession().setAttribute("idPlano", Long.valueOf(formulario.getNmComPlanoPerfilPop()));
			}
			if(formulario.getConsulta() != null && !formulario.getConsulta().trim().equalsIgnoreCase("")) {
				parametrosIncluirAcessoPlano.setInRestricaoConsulta(ParametrosIncluirAcessoPlanoInRestricaoConsulta.fromString(formulario.getConsulta()));
			} else {
				ParametrosIncluirAcessoPlanoInRestricaoConsulta inRestricaoConsulta = null;
				parametrosIncluirAcessoPlano.setInRestricaoConsulta(inRestricaoConsulta.N);
			}
			
			
			if(formulario.getAtivacao() != null && !formulario.getAtivacao().trim().equalsIgnoreCase("")) {
				parametrosIncluirAcessoPlano.setInRestricaoAtivacao(ParametrosIncluirAcessoPlanoInRestricaoAtivacao.fromString(formulario.getAtivacao()));
			} else {
				ParametrosIncluirAcessoPlanoInRestricaoAtivacao inRestricaoAtivacao = null;								
				parametrosIncluirAcessoPlano.setInRestricaoAtivacao(inRestricaoAtivacao.N);
			}
			
			
			if(formulario.getDesativacao() != null && !formulario.getDesativacao().trim().equalsIgnoreCase("")) {				
				parametrosIncluirAcessoPlano.setInRestricaoDesativacao(ParametrosIncluirAcessoPlanoInRestricaoDesativacao.fromString(formulario.getDesativacao()));
			} else {
				ParametrosIncluirAcessoPlanoInRestricaoDesativacao inRestricaoDesativacao = null;				
				parametrosIncluirAcessoPlano.setInRestricaoDesativacao(inRestricaoDesativacao.N);
			}
								
			AcessoPortTypeProxy acessoPortTypeProxy = new AcessoPortTypeProxy();
			incluirAcessoPlanoRequest.setParametrosIncluirAcessoPlano(parametrosIncluirAcessoPlano);
			
			IncluirAcessoPlanoResponse incluirAcessoPlanoResponse = new IncluirAcessoPlanoResponse(); 
					
			try {
				incluirAcessoPlanoResponse = acessoPortTypeProxy.incluirAcessoPlano(incluirAcessoPlanoRequest, this.getUserName(),this.getPassword());
			} catch (AxisFault ex) {
				logger.debug("Exception no method: 'gravarNovasRestricoesAcessoPlanoServico' da classe: " + getClass().getName());
				this.AxisFaultExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );
				return null;
			}
			
			Long idAcessoPlano = incluirAcessoPlanoResponse.getResultadoIncluirAcessoPlano().getIdAcessoPlano();
			PrintWriter writer = null;
			
			if(idAcessoPlano != null && idAcessoPlano != 0){
				try {
					response.setContentType("text/javascript");
					writer = response.getWriter();
					writer.println("$('idLinkPopUpSuccess').onclick();");
					writer.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					writer.close();
				}
			}
			else {
				try {
					response.setContentType("text/javascript");
					writer = response.getWriter();
					writer.println("$('idLinkPopUpError').onclick();");
					writer.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					writer.close();
				}
			}
			try {
				response.setContentType("text/javascript");
				writer = response.getWriter();
				writer.println("$('botao_cancelar_restricoes_plano_servico').onclick();");
				writer.flush();
			}catch (IOException e) {
				e.printStackTrace();
			}finally {
				writer.close();
			}
			
		}
		else if(formulario.getTipoRestricao() != null && formulario.getTipoRestricao().trim().equalsIgnoreCase("servico")){
			request.getSession().setAttribute("tpPesquisa", "servicoPerfil");
			
			IncluirAcessoServicoRequest incluirAcessoServicoRequest = new IncluirAcessoServicoRequest();
			ParametrosIncluirAcessoServico parametrosIncluirAcessoServico = new ParametrosIncluirAcessoServico();
			HttpSession session = request.getSession();
			
			if(formulario.getIdsPerfilPpSp() != null && formulario.getIdsPerfilPpSp().trim().length() > 0) {
				String[] idsPerfil = formulario.getIdsPerfilPpSp().split(",");				
				StringBuffer idsPerfilSCA = new StringBuffer();
				
				long[] listaIdPerfil =  new long[idsPerfil.length]; 
				long[] listaIdSistema =  new long[idsPerfil.length]; 
				
				for (int i = 0; i < idsPerfil.length; i ++) {
					String perfil = idsPerfil[i];
					StringTokenizer tokenizer = new StringTokenizer(perfil, "_");
					while(tokenizer.hasMoreTokens()) {
						String idPerfilSCA = tokenizer.nextToken();
						listaIdPerfil[i] = Long.valueOf(idPerfilSCA);
						
						String idSistemaOrigem = (String) tokenizer.nextElement();
						listaIdSistema[i] = Long.valueOf(idSistemaOrigem);
						
						idsPerfilSCA.append(idPerfilSCA+",");
					}
					request.setAttribute("idsPerfil", idsPerfilSCA.toString());
				}
				
				if(listaIdPerfil.length > 0) {
					parametrosIncluirAcessoServico.setListaIdPerfil(listaIdPerfil);
				}
				
				if(listaIdSistema.length > 0) {
					parametrosIncluirAcessoServico.setListaIdSistema(listaIdSistema);								
				} 
				request.setAttribute("idsPerfil", idsPerfilSCA.toString());
			}
			if(formulario.getNmComServicoPerfilPop() != null && !formulario.getNmComServicoPerfilPop().trim().equalsIgnoreCase("")) {
				parametrosIncluirAcessoServico.setIdServico(Long.valueOf(formulario.getNmComServicoPerfilPop()));
				session.setAttribute("idServico", Long.valueOf(formulario.getNmComServicoPerfilPop()));
				//request.setAttribute("idServico", Long.valueOf(formulario.getNmComServicoPerfilPop()));
			}
			if(formulario.getConsulta() != null && !formulario.getConsulta().trim().equalsIgnoreCase("")) {
				parametrosIncluirAcessoServico.setInRestricaoConsulta(ParametrosIncluirAcessoServicoInRestricaoConsulta.fromString(formulario.getConsulta()));
			} else {
				ParametrosIncluirAcessoServicoInRestricaoConsulta inRestricaoConsulta = null;							
				parametrosIncluirAcessoServico.setInRestricaoConsulta(inRestricaoConsulta.N);
			}
			
			if(formulario.getAtivacao() != null && !formulario.getAtivacao().trim().equalsIgnoreCase("")) {
				parametrosIncluirAcessoServico.setInRestricaoAtivacao(ParametrosIncluirAcessoServicoInRestricaoAtivacao.fromString(formulario.getAtivacao()));
			} else {
				ParametrosIncluirAcessoServicoInRestricaoAtivacao inRestricaoAtivacao = null;				
				parametrosIncluirAcessoServico.setInRestricaoAtivacao(inRestricaoAtivacao.N);
			}
			
			if(formulario.getDesativacao() != null && !formulario.getDesativacao().trim().equalsIgnoreCase("")) {
				parametrosIncluirAcessoServico.setInRestricaoDesativacao(ParametrosIncluirAcessoServicoInRestricaoDesativacao.fromString(formulario.getDesativacao()));
			} else {
				ParametrosIncluirAcessoServicoInRestricaoDesativacao inRestricaoDesativacao = null;
				parametrosIncluirAcessoServico.setInRestricaoDesativacao(inRestricaoDesativacao.N);
			}
			
			AcessoPortTypeProxy acessoPortTypeProxy = new AcessoPortTypeProxy();
			incluirAcessoServicoRequest.setParametrosIncluirAcessoServico(parametrosIncluirAcessoServico);
			
			IncluirAcessoServicoResponse incluirAcessoServicoResponse = acessoPortTypeProxy.incluirAcessoServico(incluirAcessoServicoRequest, this.getUserName(),this.getPassword());
			Long idAcessoServico = incluirAcessoServicoResponse.getResultadoIncluirAcessoServico().getIdAcessoServico();
			PrintWriter writer = null;
			
			if(idAcessoServico != null && idAcessoServico != 0){
				try {
					response.setContentType("text/javascript");
					writer = response.getWriter();
					writer.println("$('idLinkPopUpSuccess').onclick();");
					writer.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					writer.close();
				}
			}
			else {
				try {
					response.setContentType("text/javascript");
					writer = response.getWriter();
					writer.println("$('idLinkPopUpError').onclick();");
					writer.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					writer.close();
				}
			}
			try {
				response.setContentType("text/javascript");
				writer = response.getWriter();
				writer.println("$('botao_cancelar_restricoes_plano_servico').onclick();");
				writer.flush();
			}catch (IOException e) {
				e.printStackTrace();
			}finally {
				writer.close();
			}
			
		}
		else {
			CatalogoPRSException ex = new CatalogoPRSException("&Eacute; Obrigat&oacute;rio informar o campo Tipo da Restri&ccedil;&atilde;o");
			this.CatalogoPRSExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), response);
			return null;
		}
		return null;
	}
	
	// @Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupConfirmInclusaoRestricoesAcessoPlanoServico.jsp") })
	public ActionForward openPopupConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException {
		String idPlataforma = request.getParameter("id_plataforma");
		request.setAttribute("idPlataforma", idPlataforma);
		return mapping.findForward(SUCCESS);
	}

	// @Jpf.Action(forwards = { @Jpf.Forward(name = "error", path = "popupErrorInclusaoRestricoesAcessoPlanoServico.jsp") })
	public ActionForward openPopupConfirmError(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException {
		return mapping.findForward(ERROR);
	}
	
/*	@Jpf.Action(forwards = {@Jpf.Forward(name = "planoPerfil", path = "popupNmComercialNovaRestricaoPlano.jsp"),
            @Jpf.Forward(name = "servicoPerfil", path = "popupNmComercialNovaRestricaoServico.jsp") })*/
	public ActionForward buscarListaNmComercialRestricaoPlanoServico(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException, br.com.vivo.catalogoPRS.ws.catalogoUF.mc.geral.ErroInfo, RemoteException {
		ParametrizacaoAcessoForm formulario = (ParametrizacaoAcessoForm)form;
		
		String mappingForward = "";
		String parameter = request.getParameter("tp_pesquisa");
		if(parameter.trim().length() > 0 && parameter.equalsIgnoreCase("planoPerfil")) {
			buscarListaNmComercialPlanosPerfilComRadio(request, response, formulario);
			mappingForward = "planoPerfil";
		}
		if(parameter.trim().length() > 0 && parameter.equalsIgnoreCase("servicoPerfil")) {
			buscarListaNmComercialServicoPerfilComRadio(request, response, formulario);
			mappingForward = "servicoPerfil";
		}
		
		if(mappingForward != null && !mappingForward.isEmpty()) {			
			return mapping.findForward(mappingForward);
		} else {
			return mapping.findForward(ERROR);
			
		}
		
		
	}

	// @Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "resultadoPesquisaNmComercialNovaRestricaoPlano.jsp") })
	public ActionForward pesquisarNmComercialNovaRestricaoPlano(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException, ErroInfo, RemoteException {
	
		ParametrizacaoAcessoForm formulario = (ParametrizacaoAcessoForm)form;	
		ActionForward actionForw = new ActionForward();
	
				
		ParametrosBuscarListaPlano parametrosBuscarListaPlano = new ParametrosBuscarListaPlano();
		HttpSession session = request.getSession();
		if(formulario.getIdPlataforma() != null && formulario.getIdPlataforma() != 0 ) {
			parametrosBuscarListaPlano.setIdPlataforma(formulario.getIdPlataforma());
			session.setAttribute("idPlataforma", formulario.getIdPlataforma());
		}
		if(formulario.getIdTecnologia() != null && formulario.getIdTecnologia() != 0 ) {
			parametrosBuscarListaPlano.setIdTecnologia(formulario.getIdTecnologia());
		}
		if(formulario.getIdUf() != null && formulario.getIdUf() != 0) {
			parametrosBuscarListaPlano.setIdUF(formulario.getIdUf());
		}
		if(formulario.getNmComPlanoPerfilPop() != null && formulario.getNmComPlanoPerfilPop().trim().length() > 0) {
			parametrosBuscarListaPlano.setNmComercial(formulario.getNmComPlanoPerfilPop());
		}
		if(formulario.getNmTecnico() != null && formulario.getNmTecnico().trim().length() > 0) {
			parametrosBuscarListaPlano.setCdPlano(formulario.getNmTecnico());
		}
		
		PlanoPortTypeProxy planoPortTypeProxy = new PlanoPortTypeProxy();
		BuscarListaPlanoRequest buscarListaPlanoRequest = new BuscarListaPlanoRequest();
		buscarListaPlanoRequest.setParametrosBuscarListaPlano(parametrosBuscarListaPlano);
		BuscarListaPlanoResponse buscarListaPlanoResponse = new BuscarListaPlanoResponse();
		
		try {
			buscarListaPlanoResponse = planoPortTypeProxy.buscarListaPlano(buscarListaPlanoRequest,  this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'pesquisarNmComercialNovaRestricaoPlano' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );	
		}
		
		ResultadoBuscarListaPlanoListaPlanoPlano[] planoLista = null; 

		if (buscarListaPlanoResponse.getResultadoBuscarListaPlano() != null) {
			
			planoLista = buscarListaPlanoResponse.getResultadoBuscarListaPlano().getListaPlano();
		}
		
		request.setAttribute("planos", planoLista);
		formulario.setPlanoLista(planoLista);
		
		
		if (actionForw == null) {
			return actionForw;
		} else {
			return mapping.findForward(SUCCESS);
		}
	}
	
	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "resultadoPesquisaNmComercialNovaRestricaoServico.jsp") })
	public ActionForward pesquisarNmComercialNovaRestricaoServico(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException, ErroInfo, RemoteException {
	
		ParametrizacaoAcessoForm formulario = (ParametrizacaoAcessoForm)form;
		ActionForward actionForw = new ActionForward();
		
		ParametrosListarServicoParametrosServicoIn parametrosServico = new ParametrosListarServicoParametrosServicoIn();
		HttpSession session = request.getSession();
		PaginacaoIn paginacaoIn = new PaginacaoIn();
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		parametrosServico.setPaginacaoIn(paginacaoIn);
		
		if(formulario.getPaginaSolicitada() != null) {
			paginacaoIn.setPaginaSolicitada(formulario.getPaginaSolicitada());
		}else {
			paginacaoIn.setPaginaSolicitada(1);
		}
		
		
		if(formulario.getIdPlataforma() != null && formulario.getIdPlataforma() != 0) {
			parametrosServico.setIdPlataforma(formulario.getIdPlataforma());
			session.setAttribute("idPlataforma", formulario.getIdPlataforma());
		}
		if(formulario.getIdGrupoServico() != null && formulario.getIdGrupoServico() != 0) {
			parametrosServico.setIdCategoria(formulario.getIdGrupoServico());
		}
		if(formulario.getIdTipoServico() != null && formulario.getIdTipoServico() != 0) {
			parametrosServico.setNmServico(formulario.getIdTipoServico().toString());
		}
		if(formulario.getNmComServicoPerfilPop() != null && formulario.getNmComServicoPerfilPop().trim().length() > 0) {
			parametrosServico.setNmPlano(formulario.getNmComServicoPerfilPop());
		}
		if(formulario.getNmTecnico() != null && formulario.getNmTecnico().trim().length() > 0) {
			parametrosServico.setCdcodigo(formulario.getNmTecnico());
		}
	
		ServicoPortTypeProxy servicoPortTypeProxy = new ServicoPortTypeProxy();
		BuscarListaServicoRequest buscarListaServicoRequest = new BuscarListaServicoRequest();
		
		ParametrosListarServico parametrosListarServico = new ParametrosListarServico();
		parametrosListarServico.setParametrosServicoIn(parametrosServico);		
		buscarListaServicoRequest.setParametrosListarServico(parametrosListarServico);
		

		
		BuscarListaServicoResponse buscarListaServicoResponse = new BuscarListaServicoResponse();
		try {
			buscarListaServicoResponse = servicoPortTypeProxy.buscarListaServico(buscarListaServicoRequest,  this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'pesquisarNmComercialNovaRestricaoServico' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );	
		}
		
		PaginacaoOut paginacaoOut = new PaginacaoOut();
		
		this.tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(),request);
		
		ResultadoBuscarListaServicoListaServicoRetornoServico[] retornoServicoLista = null;
		if (buscarListaServicoResponse.getResultadoBuscarListaServico() != null) {
			retornoServicoLista = buscarListaServicoResponse.getResultadoBuscarListaServico().getListaServico();			
		} 
		
		request.setAttribute("servicos", retornoServicoLista);	
		formulario.setRetornoServicoLista(retornoServicoLista);
		
		if (actionForw == null) {
			return actionForw;
		} else {
			return mapping.findForward(SUCCESS);
		}
		
	}

	// @Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupCopiarResticaoAcesso.jsp") })
	public ActionForward abrirPopupCopiarRestricoes(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException, ErroInfo, RemoteException {
		
		ParametrizacaoAcessoForm formulario = (ParametrizacaoAcessoForm)form;
				
		UserCatalogo usuarioLogado = (UserCatalogo) request.getSession().getAttribute("logged_user");
		LoginProcess loginProcess = SCAUtils.verify(usuarioLogado);
		Map<Long, ProfileSys> userProfilesByIdProfile = SCAUtils.obterPerfisVivo360CallCenter(loginProcess, usuarioLogado);
		if(userProfilesByIdProfile != null && userProfilesByIdProfile.size() > 0) {
			this.setPerfilScaMap(userProfilesByIdProfile);			
		}
		Set<Long> keys = userProfilesByIdProfile.keySet();
		List<PerfilSCA> listaPerfil = new ArrayList<PerfilSCA>();
		for (Long key : keys) {
			PerfilSCA perfil = new PerfilSCA();
			perfil.setIdPerfilSCA(key);
			perfil.setIdSistema(userProfilesByIdProfile.get(key).getIdSistema());
			perfil.setNmPerfilSCA(userProfilesByIdProfile.get(key).getName());
			listaPerfil.add(perfil);
		}
		Collections.sort(listaPerfil);
		if(listaPerfil.size() > 0) {
			request.getSession().setAttribute("listaPerfil", listaPerfil);
			request.getSession().setAttribute("listaIdSistemaSession", listaPerfil);			
			
		}
		
		PerfilSCA[] idPerfilOrigem = (PerfilSCA[])listaPerfil.toArray(new PerfilSCA[listaPerfil.size()]);
		PerfilSCA[] idPerfilDestino = (PerfilSCA[])listaPerfil.toArray(new PerfilSCA[listaPerfil.size()]);
		formulario.setIdPerfilOrigem(idPerfilOrigem);
		formulario.setIdPerfilDestino(idPerfilDestino);
		
		return mapping.findForward(SUCCESS);
	}

	@SuppressWarnings("unchecked")
	public ActionForward copiarRestricoesAcesso(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginException, ErroInfo, RemoteException, CatalogoPRSException {
			
		ParametrizacaoAcessoForm formulario = (ParametrizacaoAcessoForm)form;
			
		if(formulario.getIdPerfilOrigemCopiarRestrAcesso().equals(formulario.getIdPerfilDestinoCopiarRestrAcesso())) {
			CatalogoPRSException ex = new CatalogoPRSException("O Perfil de Destino deve ser diferente do Perfil de Origem.");
			this.CatalogoPRSExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), response);
			return null;
		}else {
			CopiarPerfilRequest copiarPerfilRequest = new CopiarPerfilRequest();
			ParametrosCopiarPerfil parametrosCopiarPerfil = new ParametrosCopiarPerfil();
			if(formulario.getIdPerfilOrigemCopiarRestrAcesso() != null) {
				long idPerfilOrigem = formulario.getIdPerfilOrigemCopiarRestrAcesso();
				parametrosCopiarPerfil.setIdPerfilOrigem(idPerfilOrigem);
			}
			if(formulario.getIdPerfilDestinoCopiarRestrAcesso() != null) {
				long idPerfilDestino = formulario.getIdPerfilDestinoCopiarRestrAcesso();
				parametrosCopiarPerfil.setIdPerfilDestino(idPerfilDestino);
			}
			
			List<PerfilSCA> perfilSession = (List<PerfilSCA>) request.getSession().getAttribute("listaIdSistemaSession");
			PerfilSCA perfilSCA = new PerfilSCA();
			perfilSCA.setIdPerfilSCA(formulario.getIdPerfilOrigemCopiarRestrAcesso());
			int index = perfilSession.indexOf(perfilSCA);
			parametrosCopiarPerfil.setIdSistema(perfilSession.get(index).getIdSistema());
			request.removeAttribute("listaIdSistemaSession");						
			
			AcessoPortTypeProxy acessoPortTypeProxy = new AcessoPortTypeProxy();
			copiarPerfilRequest.setParametrosCopiarPerfil(parametrosCopiarPerfil);
			acessoPortTypeProxy.copiarPerfil(copiarPerfilRequest, this.getUserName(),this.getPassword());
			PrintWriter writer = null;
			try {
				response.setContentType("text/javascript");
				writer = response.getWriter();
				writer.println("$('botao_fechar_popup_copiar_restricao').onclick();");
				writer.flush();
			}catch (IOException e) {
				e.printStackTrace();
			}finally {
				writer.close();
			}
		}
		return null;
	}

	private ActionForward pesquisarAcessoPlanosPorPlanoPerfil(ParametrizacaoAcessoForm formulario, HttpServletRequest request, HttpServletResponse response ) throws ErroInfo, RemoteException {
		
		BuscarListaAcessoPlanoRequest buscarListaAcessoPlanoRequest = new BuscarListaAcessoPlanoRequest();
		ActionForward actionforw = new ActionForward();
		
		ParametrosBuscarListaAcessoPlano parametrosBuscarListaAcessoPlano = new ParametrosBuscarListaAcessoPlano();		
		br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn = new br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn();
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		parametrosBuscarListaAcessoPlano.setPaginacaoIn(paginacaoIn);
		
			
		if(formulario.getPaginaSolicitada() != null) {
			paginacaoIn.setPaginaSolicitada(formulario.getPaginaSolicitada());
		}else {
			paginacaoIn.setPaginaSolicitada(1);
		}
		if(formulario.getIdsPerfilPpSp() != null && formulario.getIdsPerfilPpSp().trim().length() > 0) {
			String[] idsPerfil = formulario.getIdsPerfilPpSp().split(",");
			
			long[] listaIdPerfil =  new long[idsPerfil.length];
			
			for (int i = 0; i < idsPerfil.length; i ++) {
				String perfil = idsPerfil[i];
				listaIdPerfil[i] = Long.valueOf(perfil); 
			}
			
			if(listaIdPerfil.length > 0) {
				parametrosBuscarListaAcessoPlano.setListaIdPerfil(listaIdPerfil);
			}
		}
		if(formulario.getIdPlataforma() != null && formulario.getIdPlataforma() > 0) {
			parametrosBuscarListaAcessoPlano.setIdPlataforma(formulario.getIdPlataforma());
		}
		if(formulario.getNmTecnico() != null && formulario.getNmTecnico().trim().length() > 0) {
			parametrosBuscarListaAcessoPlano.setCdPlano(formulario.getNmTecnico());
		}
		if(formulario.getIdPlano() != null) {
			Long idPlano = formulario.getIdPlano();						
			long[] listaIdPlano =  new long[1];
			listaIdPlano[0] = idPlano;
			//parametrosBuscarListaAcessoPlano.addNewListaIdPlano();
			//ListaIdPlano listaIdPlano = parametrosBuscarListaAcessoPlano.getListaIdPlano();
			//listaIdPlano.addIdPlano(idPlano);
			parametrosBuscarListaAcessoPlano.setListaIdPlano(listaIdPlano);
		}
		if(formulario.getNmComPlanoPerfilText().trim().equalsIgnoreCase("") && !formulario.getNmComPlanoPerfilPop().trim().equalsIgnoreCase("")) {
			parametrosBuscarListaAcessoPlano.setNmComercial(formulario.getNmComPlanoPerfilPop());
		}
		if(formulario.getNmComPlanoPerfilPop().trim().equalsIgnoreCase("") && !formulario.getNmComPlanoPerfilText().trim().equalsIgnoreCase("")) {
			parametrosBuscarListaAcessoPlano.setNmComercial(formulario.getNmComPlanoPerfilText());
		}
		
		AcessoPortTypeProxy acessoPortTypeProxy = new AcessoPortTypeProxy();
		buscarListaAcessoPlanoRequest.setParametrosBuscarListaAcessoPlano(parametrosBuscarListaAcessoPlano);
		BuscarListaAcessoPlanoResponse buscarListaAcessoPlanoResponse = new BuscarListaAcessoPlanoResponse();
		
		
		try {
			buscarListaAcessoPlanoResponse = acessoPortTypeProxy.buscarListaAcessoPlano(buscarListaAcessoPlanoRequest, this.getUserName(),this.getPassword());

		} catch (AxisFault ex) {		
			logger.debug("Exception no method: 'pesquisarAcessoPlanosPorPlanoPerfil' da classe: " + getClass().getName());
			actionforw = this.AxisFaultExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );
		}

		ResultadoBuscarListaAcessoPlano resultadoBuscarListaAcessoPlano = buscarListaAcessoPlanoResponse.getResultadoBuscarListaAcessoPlano();
		br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut paginacaoOut = new br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut();
		
		ResultadoBuscarListaAcessoPlanoListaAcessoPlanoAcessoPlano[] acessoPlanoLista = null;		
		if (resultadoBuscarListaAcessoPlano != null) {
			paginacaoOut = resultadoBuscarListaAcessoPlano.getPaginacaoOut();
			acessoPlanoLista = resultadoBuscarListaAcessoPlano.getListaAcessoPlano();
		}
									
		this.tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(), request);
		formulario.setAcessoPlanoLista(acessoPlanoLista);
		
		if (actionforw == null) {
			return actionforw;
		} else {			
			ActionForward action = new ActionForward();
			return action;
		}
	}
	
	private ActionForward pesquisarAcessoServicosPorServicoPerfil(ParametrizacaoAcessoForm formulario, HttpServletRequest request, HttpServletResponse response) throws ErroInfo, RemoteException {
		
		ActionForward actionforw = new ActionForward();
		BuscarListaAcessoServicoRequest buscarListaAcessoServicoRequest = new BuscarListaAcessoServicoRequest();		
		ParametrosBuscarListaAcessoServico parametrosBuscarListaAcessoServico = new ParametrosBuscarListaAcessoServico();
		
		br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn = new br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn();		
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		parametrosBuscarListaAcessoServico.setPaginacaoIn(paginacaoIn);
		
		if(formulario.getPaginaSolicitada() != null) {
			paginacaoIn.setPaginaSolicitada(formulario.getPaginaSolicitada());
		}else {
			paginacaoIn.setPaginaSolicitada(1);
		}
		if(formulario.getIdsPerfilPpSp() != null && formulario.getIdsPerfilPpSp().trim().length() > 0) {
			String[] idsPerfil = formulario.getIdsPerfilPpSp().split(",");
			
			long[] listaIdPerfil =  new long[idsPerfil.length];
									
			for (int i = 0; i < idsPerfil.length; i ++) {
				String perfil = idsPerfil[i];
				listaIdPerfil[i] = Long.valueOf(perfil); 
			}
			
			if(listaIdPerfil.length > 0) {
				parametrosBuscarListaAcessoServico.setListaIdPerfil(listaIdPerfil);
			}
		}
		if(formulario.getIdPlataforma() != null && formulario.getIdPlataforma() != 0) {
			parametrosBuscarListaAcessoServico.setIdPlataforma(formulario.getIdPlataforma());
		}
		if(formulario.getNmTecnico() != null && formulario.getNmTecnico().trim().length() > 0) {
			parametrosBuscarListaAcessoServico.setCdServico(formulario.getNmTecnico());
		}
		if(formulario.getIdServico() != null) {
			Long idServico = formulario.getIdServico();
			long[] listaIdServico =  new long[1];
			listaIdServico[0] = idServico;
			parametrosBuscarListaAcessoServico.setListaIdServico(listaIdServico);
			
			//Long idServico = form.getIdServico();
			//parametrosBuscarListaAcessoServico.addNewListaIdServico();
			//ListaIdServico listaIdServico = parametrosBuscarListaAcessoServico.getListaIdServico();
			//listaIdServico.addIdServico(idServico);


		}
		if(formulario.getNmComServicoPerfilText().trim().equalsIgnoreCase("") && !formulario.getNmComServicoPerfilPop().trim().equalsIgnoreCase("")) {
			parametrosBuscarListaAcessoServico.setNmComercial(formulario.getNmComServicoPerfilPop());
		}
		if(formulario.getNmComServicoPerfilPop().trim().equalsIgnoreCase("") && !formulario.getNmComServicoPerfilText().trim().equalsIgnoreCase("")) {
			parametrosBuscarListaAcessoServico.setNmComercial(formulario.getNmComServicoPerfilText());
		}
	
		
		AcessoPortTypeProxy acessoPortTypeProxy = new AcessoPortTypeProxy();
		buscarListaAcessoServicoRequest.setParametrosBuscarListaAcessoServico(parametrosBuscarListaAcessoServico);
		BuscarListaAcessoServicoResponse buscarListaAcessoServicoResponse = new BuscarListaAcessoServicoResponse(); 
				
		try {
			buscarListaAcessoServicoResponse = acessoPortTypeProxy.buscarListaAcessoServico(buscarListaAcessoServicoRequest, this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'pesquisarAcessoServicosPorServicoPerfil' da classe: " + getClass().getName());
			actionforw = this.AxisFaultExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );
		} 
		
		ResultadoBuscarListaAcessoServico resultadoBuscarListaAcessoServico = buscarListaAcessoServicoResponse.getResultadoBuscarListaAcessoServico();
		br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut paginacaoOut = new br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut();
		
		ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico[] acessoServicoLista = null;
		if (resultadoBuscarListaAcessoServico != null) {
			paginacaoOut = resultadoBuscarListaAcessoServico.getPaginacaoOut();
			acessoServicoLista = resultadoBuscarListaAcessoServico.getListaAcessoServico();
		}
				
		this.tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(), request);			
		formulario.setAcessoServicoLista(acessoServicoLista);
		
		if (actionforw == null) {
			return actionforw;			
		} else {
			ActionForward action = new ActionForward();
			return action;
		}
		
	}
	
	private ActionForward pesquisarAcessoPlanosPorPerfilPlano(ParametrizacaoAcessoForm formulario, HttpServletRequest request, HttpServletResponse response) throws ErroInfo, RemoteException{
				
		ActionForward actionforw = new ActionForward();
		BuscarListaAcessoPlanoRequest buscarListaAcessoPlanoRequest = new BuscarListaAcessoPlanoRequest();
		ParametrosBuscarListaAcessoPlano parametrosBuscarListaAcessoPlano = new ParametrosBuscarListaAcessoPlano();
		
		br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn = new br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn();
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		parametrosBuscarListaAcessoPlano.setPaginacaoIn(paginacaoIn);
		
		if(formulario.getPaginaSolicitada() != null) {
			paginacaoIn.setPaginaSolicitada(formulario.getPaginaSolicitada());
		}else {
			paginacaoIn.setPaginaSolicitada(1);
		}
		if(formulario.getIdsPerfilPpPs() != null && formulario.getIdsPerfilPpPs().trim().length() > 0) {
			String[] idsPerfil = formulario.getIdsPerfilPpPs().split(",");
			
			long[] listaIdPerfil =  new long[idsPerfil.length];
			
			for (int i = 0; i < idsPerfil.length; i ++) {
				String perfil = idsPerfil[i];
				listaIdPerfil[i] = Long.valueOf(perfil);
			}
			
			if(listaIdPerfil.length > 0) {
				parametrosBuscarListaAcessoPlano.setListaIdPerfil(listaIdPerfil);
			}
						
		}
		if(formulario.getIdPlataforma() != null && formulario.getIdPlataforma() != 0) {
			parametrosBuscarListaAcessoPlano.setIdPlataforma(formulario.getIdPlataforma());
		}
		if(formulario.getNmTecnico() != null && formulario.getNmTecnico().trim().length() > 0) {
			parametrosBuscarListaAcessoPlano.setCdPlano(formulario.getNmTecnico());
		}
		
		if(formulario.getNmComPerfilPlanoText().trim().equalsIgnoreCase("") && !formulario.getNmComPerfilPlanoPop().trim().equalsIgnoreCase("")) {
			String[] idsPlano = formulario.getNmComPerfilPlanoPop().split(",");
			
			long[] listaIdPlano =  new long[idsPlano.length];
			
			for (int i = 0; i < idsPlano.length; i ++) {
				String plano = idsPlano[i];
				listaIdPlano[i] = Long.valueOf(plano);
			}
			
			if (listaIdPlano.length > 0) {
				parametrosBuscarListaAcessoPlano.setListaIdPlano(listaIdPlano);				
			}
		}
		if(formulario.getNmComPerfilPlanoPop().trim().equalsIgnoreCase("") && !formulario.getNmComPerfilPlanoText().trim().equalsIgnoreCase("")) {
			parametrosBuscarListaAcessoPlano.setNmComercial(formulario.getNmComPerfilPlanoText());
		}
		
		
		AcessoPortTypeProxy acessoPortTypeProxy = new AcessoPortTypeProxy();
		buscarListaAcessoPlanoRequest.setParametrosBuscarListaAcessoPlano(parametrosBuscarListaAcessoPlano);
		BuscarListaAcessoPlanoResponse buscarListaAcessoPlanoResponse = new BuscarListaAcessoPlanoResponse();
				
		try {
			buscarListaAcessoPlanoResponse = acessoPortTypeProxy.buscarListaAcessoPlano(buscarListaAcessoPlanoRequest, this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'pesquisarAcessoPlanosPorPerfilPlano' da classe: " + getClass().getName());
			actionforw = this.AxisFaultExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );					
		}
		
		ResultadoBuscarListaAcessoPlano resultadoBuscarListaAcessoPlano = new ResultadoBuscarListaAcessoPlano();
		//br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut paginacaoOut = resultadoBuscarListaAcessoPlano.getPaginacaoOut();
		br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut paginacaoOut = new br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut();
		
		if (buscarListaAcessoPlanoResponse.getResultadoBuscarListaAcessoPlano() != null) {			
			resultadoBuscarListaAcessoPlano = buscarListaAcessoPlanoResponse.getResultadoBuscarListaAcessoPlano();
		}
						
		ResultadoBuscarListaAcessoPlanoListaAcessoPlanoAcessoPlano[] acessoPlanoLista = null;
		if (resultadoBuscarListaAcessoPlano != null) {
			acessoPlanoLista = resultadoBuscarListaAcessoPlano.getListaAcessoPlano();
			paginacaoOut = resultadoBuscarListaAcessoPlano.getPaginacaoOut();
		}
		
		
		this.tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(),request);				 
		formulario.setAcessoPlanoLista(acessoPlanoLista);
		
		if (actionforw == null) {			
			return actionforw;
		} else {
			ActionForward action = new ActionForward();
			return action;
		}
		
	}

	private ActionForward pesquisarAcessoServicosPorPerfilServico(ParametrizacaoAcessoForm formulario, HttpServletRequest request, HttpServletResponse response) throws ErroInfo, RemoteException{
		
		ActionForward actionforw = new ActionForward();
		BuscarListaAcessoServicoRequest buscarListaAcessoServicoRequest = new BuscarListaAcessoServicoRequest();
		ParametrosBuscarListaAcessoServico parametrosBuscarListaAcessoServico = new ParametrosBuscarListaAcessoServico();
		br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn = new br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn();
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		parametrosBuscarListaAcessoServico.setPaginacaoIn(paginacaoIn);		
		
		if(formulario.getPaginaSolicitada() != null) {
			paginacaoIn.setPaginaSolicitada(formulario.getPaginaSolicitada());
		}else {
			paginacaoIn.setPaginaSolicitada(1);
		}
		if(formulario.getIdsPerfilPpPs() != null && formulario.getIdsPerfilPpPs().trim().length() > 0) {
			String[] idsPerfil = formulario.getIdsPerfilPpPs().split(",");
			
			long[] listaIdPerfil =  new long[idsPerfil.length];
			
			for (int i = 0; i < idsPerfil.length; i ++) {
				String perfil = idsPerfil[i];
				listaIdPerfil[i] = Long.valueOf(perfil);
			}
			
			if(listaIdPerfil.length > 0) {
				parametrosBuscarListaAcessoServico.setListaIdPerfil(listaIdPerfil);				
			}
		}
		if(formulario.getIdPlataforma() != null && formulario.getIdPlataforma() != 0 ) {
			parametrosBuscarListaAcessoServico.setIdPlataforma(formulario.getIdPlataforma());
		}
		if(formulario.getNmTecnico() != null && formulario.getNmTecnico().trim().length() > 0) {
			parametrosBuscarListaAcessoServico.setCdServico(formulario.getNmTecnico());
		}
		if(formulario.getNmComPerfilServicoText().trim().equalsIgnoreCase("") && !formulario.getNmComPerfilServicoPop().trim().equalsIgnoreCase("")) {
			String[] idsServico = formulario.getNmComPerfilServicoPop().split(",");
			
			long[] listaIdServico =  new long[idsServico.length];
			
			for (int i = 0; i < idsServico.length; i++) {
				String servico = idsServico[i];
				listaIdServico[i] = Long.valueOf(servico);
			}
			
			if (listaIdServico.length > 0) {
				parametrosBuscarListaAcessoServico.setListaIdServico(listaIdServico);				
			}						
		}
		if(formulario.getNmComPerfilServicoPop().trim().equalsIgnoreCase("") && !formulario.getNmComPerfilServicoText().trim().equalsIgnoreCase("")) {
			parametrosBuscarListaAcessoServico.setNmComercial(formulario.getNmComPerfilServicoText());
		}
		
		AcessoPortTypeProxy acessoPortTypeProxy = new AcessoPortTypeProxy();
		buscarListaAcessoServicoRequest.setParametrosBuscarListaAcessoServico(parametrosBuscarListaAcessoServico);		
		BuscarListaAcessoServicoResponse buscarListaAcessoServicoResponse = new BuscarListaAcessoServicoResponse(); 
				
		try {
			buscarListaAcessoServicoResponse = acessoPortTypeProxy.buscarListaAcessoServico(buscarListaAcessoServicoRequest, this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {		
			logger.debug("Exception no method: 'pesquisarAcessoServicosPorPerfilServico' da classe: " + getClass().getName());
			actionforw = this.AxisFaultExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );
		}
		
		br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut paginacaoOut = new br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoOut();
		ResultadoBuscarListaAcessoServico resultadoBuscarListaAcessoServico = buscarListaAcessoServicoResponse.getResultadoBuscarListaAcessoServico();
		
		ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico[] acessoServicoLista = null;
		if (resultadoBuscarListaAcessoServico != null) {
			paginacaoOut = resultadoBuscarListaAcessoServico.getPaginacaoOut();
			acessoServicoLista = buscarListaAcessoServicoResponse.getResultadoBuscarListaAcessoServico().getListaAcessoServico();
		}
		
		this.tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina(),request);				
		formulario.setAcessoServicoLista(acessoServicoLista);
		
		if (actionforw == null) {
			return actionforw;
		} else {
			ActionForward action = new ActionForward();
			return action;			
		}		
	}
	
	/**
	 * Metodo utilizado para buscar os nomes comercial dos Planos. Utilizando RadioGroup na lista.
	 * @param request
	 * @throws RemoteException 
	 * @throws br.com.vivo.catalogoPRS.ws.catalogoUF.mc.geral.ErroInfo 
	 */
	private ActionForward buscarListaNmComercialPlanosPerfilComRadio(HttpServletRequest request, HttpServletResponse response, ParametrizacaoAcessoForm formulario) throws br.com.vivo.catalogoPRS.ws.catalogoUF.mc.geral.ErroInfo, RemoteException {

		ActionForward actionForw = new ActionForward();
		
		// plataformaLista
		PlataformaPortTypeProxy plataformaPortTypeProxy = new PlataformaPortTypeProxy();
		BuscarListaPlataformaRequest buscarListaPlataformaRequest = new BuscarListaPlataformaRequest();		
		BuscarListaPlataformaResponse buscarListaPlataformaResponse = new BuscarListaPlataformaResponse();
				
		try {
			buscarListaPlataformaResponse = plataformaPortTypeProxy.buscarListaPlataforma(buscarListaPlataformaRequest, this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'buscarListaNmComercialPlanosPerfilComRadio' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );	
		}		
		List<ResultadoBuscarListaPlataformaPlataforma> plataformaLista = null;
		if (buscarListaPlataformaResponse.getResultadoBuscarListaPlataforma() != null) {
			plataformaLista = Arrays.asList(buscarListaPlataformaResponse.getResultadoBuscarListaPlataforma());
		}								
		request.setAttribute("plataformas", plataformaLista);
		
		// tipoClienteLista
		TipoClientePortTypeProxy tipoClientePortTypeProxy = new TipoClientePortTypeProxy();
		BuscarListaTipoClienteRequest BuscarListaTipoClienteRequest = new BuscarListaTipoClienteRequest();		
		BuscarListaTipoClienteResponse buscarListaTipoClienteResponse = new BuscarListaTipoClienteResponse();
				
		try {
			buscarListaTipoClienteResponse = tipoClientePortTypeProxy.buscarListaTipoCliente(BuscarListaTipoClienteRequest, this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'buscarListaNmComercialPlanosPerfilComRadio' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );
		}
		
		List<ResultadoBuscarListaTipoClienteTipoCliente> tipoClienteLista = null;
		if (buscarListaTipoClienteResponse.getResultadoBuscarListaTipoCliente() != null) {
			tipoClienteLista = Arrays.asList(buscarListaTipoClienteResponse.getResultadoBuscarListaTipoCliente());
		}			
		request.setAttribute("tipoCliente", tipoClienteLista);
		
		
		// tecnologiaLista
		TecnologiaPortTypeProxy tecnologiaPortTypeProxy = new TecnologiaPortTypeProxy();
		BuscarListaTecnologiaRequest tecnologiaRequestDocument = new BuscarListaTecnologiaRequest();
		BuscarListaTecnologiaResponse buscarListaTecnologiaResponse = new BuscarListaTecnologiaResponse();
				
		try {
			buscarListaTecnologiaResponse = tecnologiaPortTypeProxy.buscarListaTecnologia(tecnologiaRequestDocument, this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'buscarListaNmComercialPlanosPerfilComRadio' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );		
		}
		
		List<ResultadoBuscarListaTecnologiaTecnologia> tecnologiaLista = null;
		if (buscarListaTecnologiaResponse.getResultadoBuscarListaTecnologia() != null) {
			tecnologiaLista = Arrays.asList(buscarListaTecnologiaResponse.getResultadoBuscarListaTecnologia());			
		}				 	
		request.setAttribute("tecnologia", tecnologiaLista);
		
		
		// ufLista
		UFPortTypeProxy uFPortTypeProxy = new UFPortTypeProxy(); 
		BuscarListaUFRequest ufRequestDocument = new BuscarListaUFRequest();		
		BuscarListaUFResponse buscarListaUFResponse = new BuscarListaUFResponse();
				
		try {
			buscarListaUFResponse = uFPortTypeProxy.buscarListaUF(ufRequestDocument, this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'buscarListaNmComercialPlanosPerfilComRadio' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );
		}
		
		List<ResultadoBuscarListaUFUF> ufLista = Arrays.asList(buscarListaUFResponse.getResultadoBuscarListaUF());		
		request.setAttribute("ufs", ufLista);
		
		if (actionForw == null) {
			return actionForw;
			
		} else {
			ActionForward action = new ActionForward();
			return action;
			
		}
	}
	
	/**
	 * Metodo utilizado para buscar os nomes comercial dos Serviços. Utilizando RadioGroup na lista.
	 * @param request
	 * @throws RemoteException 
	 * @throws br.com.vivo.catalogoPRS.ws.catalogoPlataforma.mc.geral.ErroInfo 
	 */
	private ActionForward buscarListaNmComercialServicoPerfilComRadio(HttpServletRequest request, HttpServletResponse response, ParametrizacaoAcessoForm formulario) throws br.com.vivo.catalogoPRS.ws.catalogoPlataforma.mc.geral.ErroInfo, RemoteException {
		
		ActionForward actionForw = new ActionForward();
		
		// plataformaLista
		PlataformaPortTypeProxy plataformaPortTypeProxy = new PlataformaPortTypeProxy();
		BuscarListaPlataformaRequest buscarListaPlataformaRequest = new BuscarListaPlataformaRequest();		
		BuscarListaPlataformaResponse buscarListaPlataformaResponse = new BuscarListaPlataformaResponse();
				
		try {
			buscarListaPlataformaResponse = plataformaPortTypeProxy.buscarListaPlataforma(buscarListaPlataformaRequest,this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'buscarListaNmComercialServicoPerfilComRadio' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );
		}
		
		List<ResultadoBuscarListaPlataformaPlataforma> plataformaLista = null;
		if (buscarListaPlataformaResponse.getResultadoBuscarListaPlataforma() != null) {
			plataformaLista = Arrays.asList(buscarListaPlataformaResponse.getResultadoBuscarListaPlataforma());			
		}								
		request.setAttribute("plataformas", plataformaLista);
		
		
		// categoriaLista
		GrupoServicoPortTypeProxy grupoServicoPortTypeProxy = new GrupoServicoPortTypeProxy();
		BuscarListaGrupoServicoRequest buscarListaGrupoServicoRequest = new BuscarListaGrupoServicoRequest();
		
		
		ParametrosListarGrupoServico parametrosListarGrupoServico = new ParametrosListarGrupoServico();
		parametrosListarGrupoServico.setIndCatalogo(Long.valueOf(1));
		buscarListaGrupoServicoRequest.setParametrosListarGrupoServico(parametrosListarGrupoServico);
		
		
		BuscarListaGrupoServicoResponse buscarListaGrupoServicoResponse = new BuscarListaGrupoServicoResponse();
				
		try {
			buscarListaGrupoServicoResponse = grupoServicoPortTypeProxy.buscarListaGrupoServico(buscarListaGrupoServicoRequest, this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'buscarListaNmComercialServicoPerfilComRadio' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );
		}
		
		List<ResultadoListarGrupoServicoCategoria> categoriaLista = null;
		if (buscarListaGrupoServicoResponse.getResultadoListarGrupoServico() != null) {
			categoriaLista = Arrays.asList(buscarListaGrupoServicoResponse.getResultadoListarGrupoServico());
			
		}				
		request.setAttribute("categorias", categoriaLista);
		
		
		// tipoServicoLista
		ServicoPortTypeProxy servicoPortTypeProxy = new ServicoPortTypeProxy();
		BuscarListaTipoServicoRequest buscarListaTipoServicoRequest = new BuscarListaTipoServicoRequest();		
		BuscarListaTipoServicoResponse buscarListaTipoServicoResponse = new BuscarListaTipoServicoResponse();
				
		try {
			buscarListaTipoServicoResponse = servicoPortTypeProxy.buscarListaTipoServico(buscarListaTipoServicoRequest, this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'buscarListaNmComercialServicoPerfilComRadio' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );
		}		
		ResultadoBuscarListaTipoServicoListaTipoServicoRetorno[] tipoServicoLista = null;
		if (buscarListaTipoServicoResponse.getResultadoBuscarListaTipoServico() != null) {
			tipoServicoLista = buscarListaTipoServicoResponse.getResultadoBuscarListaTipoServico().getListaTipoServico();
			
		}						
		request.setAttribute("tipoServico", tipoServicoLista);
		
		if (actionForw == null) {
			return actionForw;
			
		} else {
			ActionForward action = new ActionForward();
			return action;			
		}
	}
	
	/**
	 * Metodo utilizado para buscar os nomes comercial dos Planos. Utilizando CheckBox na lista.
	 * @param request
	 */
	private ActionForward buscarListaNmComercialPerfilPlanosComCheckBox(HttpServletRequest request, HttpServletResponse response, ParametrizacaoAcessoForm formulario) throws br.com.vivo.catalogoPRS.ws.catalogoPlataforma.mc.geral.ErroInfo, RemoteException {
		
		ActionForward actionForw = new ActionForward();
		
		//plataformaLista
		PlataformaPortTypeProxy plataformaPortTypeProxy = new PlataformaPortTypeProxy();
		BuscarListaPlataformaRequest buscarListaPlataformaRequest = new BuscarListaPlataformaRequest();		
		BuscarListaPlataformaResponse buscarListaPlataformaResponse = new BuscarListaPlataformaResponse();
				
		try {
			buscarListaPlataformaResponse =	plataformaPortTypeProxy.buscarListaPlataforma(buscarListaPlataformaRequest, this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'buscarListaNmComercialPerfilPlanosComCheckBox' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );
		}
		List<ResultadoBuscarListaPlataformaPlataforma> plataformaLista = null;
		if (buscarListaPlataformaResponse.getResultadoBuscarListaPlataforma() != null) {
			plataformaLista = Arrays.asList(buscarListaPlataformaResponse.getResultadoBuscarListaPlataforma());			
		}
		request.setAttribute("plataformas", plataformaLista);
		
		
		//tipoClienteLista
		BuscarListaTipoClienteRequest buscarListaTipoClienteRequest = new BuscarListaTipoClienteRequest();		
		TipoClientePortTypeProxy tipoClientePortTypeProxy = new TipoClientePortTypeProxy(); 		
		BuscarListaTipoClienteResponse buscarListaTipoClienteResponse = new BuscarListaTipoClienteResponse();
				
		try {
			buscarListaTipoClienteResponse = tipoClientePortTypeProxy.buscarListaTipoCliente(buscarListaTipoClienteRequest, this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'buscarListaNmComercialPerfilPlanosComCheckBox' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );
		}
		
		List<ResultadoBuscarListaTipoClienteTipoCliente> tipoClienteLista = null;
		if (buscarListaTipoClienteResponse.getResultadoBuscarListaTipoCliente() != null) {
			tipoClienteLista = Arrays.asList(buscarListaTipoClienteResponse.getResultadoBuscarListaTipoCliente());					
		}								
		request.setAttribute("tipoCliente", tipoClienteLista);
		
		
		// tecnologiaLista
		BuscarListaTecnologiaRequest buscarListaTecnologiaRequest = new BuscarListaTecnologiaRequest();
		TecnologiaPortTypeProxy  tecnologiaPortTypeProxy = new TecnologiaPortTypeProxy();		
		BuscarListaTecnologiaResponse buscarListaTecnologiaResponse = new BuscarListaTecnologiaResponse();
								
		try {
			buscarListaTecnologiaResponse =	tecnologiaPortTypeProxy.buscarListaTecnologia(buscarListaTecnologiaRequest, this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'buscarListaNmComercialPerfilPlanosComCheckBox' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );			
		}
		
		List<ResultadoBuscarListaTecnologiaTecnologia> tecnologiaLista = null;
		if (buscarListaTecnologiaResponse.getResultadoBuscarListaTecnologia() != null) {
			tecnologiaLista = Arrays.asList(buscarListaTecnologiaResponse.getResultadoBuscarListaTecnologia());			
		}		
		request.setAttribute("tecnologia", tecnologiaLista);
		
		
		// ufLista
		BuscarListaUFRequest ufRequestDocument = new BuscarListaUFRequest();
		UFPortTypeProxy uFPortTypeProxy = new UFPortTypeProxy();	
		BuscarListaUFResponse buscarListaUFResponse = new BuscarListaUFResponse();
				
		try {
			buscarListaUFResponse =	uFPortTypeProxy.buscarListaUF(ufRequestDocument, this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'buscarListaNmComercialPerfilPlanosComCheckBox' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );		
		}
		
		List<ResultadoBuscarListaUFUF> ufLista = null; 
		if (buscarListaUFResponse.getResultadoBuscarListaUF() != null) {
			ufLista = Arrays.asList(buscarListaUFResponse.getResultadoBuscarListaUF());			
		}				
		request.setAttribute("ufs", ufLista);
		
		if (actionForw == null) {
			return actionForw;
			
		} else {
			ActionForward action = new ActionForward();
			return action;			
		}
	}
	
	/**
	 * Metodo utilizado para buscar os nomes comercial dos Serviçoc. Utilizando CheckBox na lista.
	 * @param request
	 * @throws RemoteException 
	 * @throws br.com.vivo.catalogoPRS.ws.catalogoPlataforma.mc.geral.ErroInfo 
	 */
	private ActionForward buscarListaNmComercialPerfilServicoComCheckBox(HttpServletRequest request, HttpServletResponse response, ParametrizacaoAcessoForm formulario) throws br.com.vivo.catalogoPRS.ws.catalogoPlataforma.mc.geral.ErroInfo, RemoteException {

		ActionForward actionForw = new ActionForward();
		
		//plataformaLista
		PlataformaPortTypeProxy plataformaPortTypeProxy = new PlataformaPortTypeProxy();
		BuscarListaPlataformaRequest buscarListaPlataformaRequest = new BuscarListaPlataformaRequest();
		
		BuscarListaPlataformaResponse buscarListaPlataformaResponse = new BuscarListaPlataformaResponse();
				
		try {
			buscarListaPlataformaResponse =	plataformaPortTypeProxy.buscarListaPlataforma(buscarListaPlataformaRequest,  this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'buscarListaNmComercialPerfilServicoComCheckBox' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );	
		}
		
		List<ResultadoBuscarListaPlataformaPlataforma> plataformaLista = null; 
		if (buscarListaPlataformaResponse.getResultadoBuscarListaPlataforma() != null) {
			plataformaLista = Arrays.asList(buscarListaPlataformaResponse.getResultadoBuscarListaPlataforma());
		}
				
		request.setAttribute("plataformas", plataformaLista);
		
		//categoriaLista
		BuscarListaGrupoServicoRequest buscarListaGrupoServicoRequest = new BuscarListaGrupoServicoRequest();
		ParametrosListarGrupoServico parametrosListarGrupoServico = new ParametrosListarGrupoServico();
		parametrosListarGrupoServico.setIndCatalogo(Long.valueOf(1));
		buscarListaGrupoServicoRequest.setParametrosListarGrupoServico(parametrosListarGrupoServico);
		
		GrupoServicoPortTypeProxy grupoServicoPortTypeProxy = new GrupoServicoPortTypeProxy();		
		BuscarListaGrupoServicoResponse buscarListaGrupoServicoResponse = new BuscarListaGrupoServicoResponse();
				
		try {
			buscarListaGrupoServicoResponse = grupoServicoPortTypeProxy.buscarListaGrupoServico(buscarListaGrupoServicoRequest,  this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'buscarListaNmComercialPerfilServicoComCheckBox' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );	
		}
		
		List<ResultadoListarGrupoServicoCategoria> categoriaLista = null;
		if (buscarListaGrupoServicoResponse.getResultadoListarGrupoServico() != null) {
			categoriaLista = Arrays.asList(buscarListaGrupoServicoResponse.getResultadoListarGrupoServico());			
		}								
		request.setAttribute("categorias", categoriaLista);		
				
		//tipoServicoLista
		BuscarListaTipoServicoRequest buscarListaTipoServicoRequest = new BuscarListaTipoServicoRequest();		
		ServicoPortTypeProxy servicoPortTypeProxy = new ServicoPortTypeProxy();	
		BuscarListaTipoServicoResponse buscarListaTipoServicoResponse = new BuscarListaTipoServicoResponse();
				
		try {
			buscarListaTipoServicoResponse = servicoPortTypeProxy.buscarListaTipoServico(buscarListaTipoServicoRequest,  this.getUserName(),this.getPassword());
		} catch (AxisFault ex) {
			logger.debug("Exception no method: 'buscarListaNmComercialPerfilServicoComCheckBox' da classe: " + getClass().getName());
			actionForw = this.AxisFaultExceptionHandler(ex, ParametrizacaoAcessoPlanosServicosAction.class.getName(), ex.getMessage(), formulario, response );	
		}
		ResultadoBuscarListaTipoServicoListaTipoServicoRetorno[] tipoServicoLista = null; 
		if (buscarListaTipoServicoResponse.getResultadoBuscarListaTipoServico() != null) {
			tipoServicoLista = buscarListaTipoServicoResponse.getResultadoBuscarListaTipoServico().getListaTipoServico();			
		}				
		request.setAttribute("tipoServico", tipoServicoLista);		
		
		
		if (actionForw == null) {
			return actionForw;
			
		} else {
			ActionForward action = new ActionForward();
			return action;
			
		}
	}

	public Map<Long, ProfileSys> getPerfilScaMap() {
		return perfilScaMap;
	}

	public void setPerfilScaMap(Map<Long, ProfileSys> perfilScaMap) {
		this.perfilScaMap = perfilScaMap;
	}

	public Map<Long, String> getPerfilSCA() {
		return perfilSCA;
	}

	public void setPerfilSCA(Map<Long, String> perfilSCA) {
		this.perfilSCA = perfilSCA;
	}
	
	public String[] getIdsPerfil() {
		return idsPerfil;
	}
	public void setIdsPerfil(String[] idsPerfil) {
		this.idsPerfil = idsPerfil;
	}
	public Long getIdPlano() {
		return idPlano;
	}
	public void setIdPlano(Long idPlano) {
		this.idPlano = idPlano;
	}
	
		
}