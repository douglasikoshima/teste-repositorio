package br.com.vivo.catalogoPRS.pageflows.param.planoServico.parametrizacao;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.services.GrupoServicoService;
import br.com.vivo.catalogoPRS.services.PlataformaService;
import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;
import br.com.vivo.catalogoPRS.util.PlataformaEnum;
import br.com.vivo.catalogoPRS.util.WebServicesConfiguration;
/*import br.com.vivo.mc.geral.ErroInfo;
import br.com.vivo.sn.catalogoGeral.PaginacaoInDocument.PaginacaoIn;
import br.com.vivo.sn.catalogoGrupoServico.AlterarGrupoServicoCatalogoRequestDocument;
import br.com.vivo.sn.catalogoGrupoServico.BuscarListaGrupoServicoRequestDocument;
import br.com.vivo.sn.catalogoGrupoServico.BuscarListaGrupoServicoResponseDocument;
import br.com.vivo.sn.catalogoGrupoServico.BuscarServicoPorGrupoServicoCatalogoRequestDocument;
import br.com.vivo.sn.catalogoGrupoServico.BuscarServicoPorGrupoServicoCatalogoResponseDocument;
import br.com.vivo.sn.catalogoGrupoServico.CriarGrupoServicoCatalogoRequestDocument;
import br.com.vivo.sn.catalogoGrupoServico.ExcluirListaGrupoServicoCatalogoRequestDocument;
import br.com.vivo.sn.catalogoGrupoServico.ParametrosAlterarGrupoServicoCatalogoDocument.ParametrosAlterarGrupoServicoCatalogo;
import br.com.vivo.sn.catalogoGrupoServico.ParametrosBuscarListaServicoPorGrupoServicoCatalogoDocument.ParametrosBuscarListaServicoPorGrupoServicoCatalogo;
import br.com.vivo.sn.catalogoGrupoServico.ParametrosExcluirListaGrupoServicoCatalogoDocument.ParametrosExcluirListaGrupoServicoCatalogo;
import br.com.vivo.sn.catalogoGrupoServico.ParametrosListarGrupoServicoDocument.ParametrosListarGrupoServico;
import br.com.vivo.sn.catalogoGrupoServico.ParametroscriarGrupoServicoCatalogoDocument.ParametroscriarGrupoServicoCatalogo;
import br.com.vivo.sn.catalogoGrupoServico.ResultadoBuscarServicoPorGrupoServicoCatalogoDocument.ResultadoBuscarServicoPorGrupoServicoCatalogo;
import br.com.vivo.sn.catalogoGrupoServico.ResultadoListarGrupoServicoDocument.ResultadoListarGrupoServico.Categoria;
import br.com.vivo.sn.catalogoPlano.AlterarPlanoParametrizacaoRequestDocument;
import br.com.vivo.sn.catalogoPlano.BuscarListaPlanoParametrizacaoRequestDocument;
import br.com.vivo.sn.catalogoPlano.BuscarListaPlanoParametrizacaoResponseDocument;
import br.com.vivo.sn.catalogoPlano.BuscarListaPlanoPendValidacaoRequestDocument;
import br.com.vivo.sn.catalogoPlano.BuscarListaPlanoPendValidacaoResponseDocument;
import br.com.vivo.sn.catalogoPlano.BuscarListaTipoPlanoRequestDocument;
import br.com.vivo.sn.catalogoPlano.BuscarListaTipoPlanoResponseDocument;
import br.com.vivo.sn.catalogoPlano.ValidarListaPlanoPorIdRequestDocument;
import br.com.vivo.sn.catalogoPlano.PaginacaoOutDocument.PaginacaoOut;
import br.com.vivo.sn.catalogoPlano.ParamAlterarAtivacaoWiFiDocument.ParamAlterarAtivacaoWiFi.ListaIdPlano;
import br.com.vivo.sn.catalogoPlano.ParametroAlterarPlanoDocument.ParametroAlterarPlano;
import br.com.vivo.sn.catalogoPlano.ParametroAlterarPlanoDocument.ParametroAlterarPlano.InDisponibilidadeCatalogo.Enum;
import br.com.vivo.sn.catalogoPlano.ParametrosBuscarListaPlanoParametrizacaoDocument.ParametrosBuscarListaPlanoParametrizacao;
import br.com.vivo.sn.catalogoPlano.ParametrosBuscarListaPlanoParametrizacaoDocument.ParametrosBuscarListaPlanoParametrizacao.ListaRegional;
import br.com.vivo.sn.catalogoPlano.ParametrosBuscarListaPlanoParametrizacaoDocument.ParametrosBuscarListaPlanoParametrizacao.ListaRegional.Regional;
import br.com.vivo.sn.catalogoPlano.ParametrosBuscarListaPlanoParametrizacaoDocument.ParametrosBuscarListaPlanoParametrizacao.ListaRegional.Regional.ListaUf;
import br.com.vivo.sn.catalogoPlano.ParametrosBuscarListaTipoPlanoDocument.ParametrosBuscarListaTipoPlano;
import br.com.vivo.sn.catalogoPlano.ParametrosValidarListaPlanoPorIdDocument.ParametrosValidarListaPlanoPorId;
import br.com.vivo.sn.catalogoPlano.ResultadoBuscarListaPlanoParametrizacaoDocument.ResultadoBuscarListaPlanoParametrizacao;
import br.com.vivo.sn.catalogoPlano.ResultadoBuscarListaPlanoParametrizacaoDocument.ResultadoBuscarListaPlanoParametrizacao.ListaBuscarListaPlanoParametrizacao;
import br.com.vivo.sn.catalogoPlano.ResultadoBuscarListaPlanoParametrizacaoDocument.ResultadoBuscarListaPlanoParametrizacao.ListaBuscarListaPlanoParametrizacao.RetornoBuscarListaPlanoParametrizacao;
import br.com.vivo.sn.catalogoPlano.ResultadoBuscarListaPlanoPendValidacaoDocument.ResultadoBuscarListaPlanoPendValidacao;
import br.com.vivo.sn.catalogoPlano.ResultadoBuscarListaPlanoPendValidacaoDocument.ResultadoBuscarListaPlanoPendValidacao.ListaPlano.RetornoPlano;
import br.com.vivo.sn.catalogoPlano.ResultadoBuscarListaTipoPlanoDocument.ResultadoBuscarListaTipoPlano.ListaTipoPlano.TipoPlano;
import br.com.vivo.sn.catalogoPlataforma.ResultadoBuscarListaPlataformaDocument.ResultadoBuscarListaPlataforma.Plataforma;
import br.com.vivo.sn.catalogoRegional.BuscarListaRegionalComUFRequestDocument;
import br.com.vivo.sn.catalogoRegional.BuscarListaRegionalComUFResponseDocument;
import br.com.vivo.sn.catalogoRegional.ListaRegionalUfDocument.ListaRegionalUf;
import br.com.vivo.sn.catalogoServico.AlterarAtivacaoWiFiRequestDocument;
import br.com.vivo.sn.catalogoServico.AlterarCategoriaListaServicoRequestDocument;
import br.com.vivo.sn.catalogoServico.AlterarServicoParametrizacaoRequestDocument;
import br.com.vivo.sn.catalogoServico.BuscarListaServicoParametrizacaoRequestDocument;
import br.com.vivo.sn.catalogoServico.BuscarListaServicoParametrizacaoResponseDocument;
import br.com.vivo.sn.catalogoServico.BuscarListaServicoPendValidacaoRequestDocument;
import br.com.vivo.sn.catalogoServico.BuscarListaServicoPendValidacaoResponseDocument;
import br.com.vivo.sn.catalogoServico.BuscarListaTipoServicoRequestDocument;
import br.com.vivo.sn.catalogoServico.BuscarListaTipoServicoResponseDocument;
import br.com.vivo.sn.catalogoServico.ValidarListaServicoPorIdRequestDocument;
import br.com.vivo.sn.catalogoServico.ParamAlterarAtivacaoWiFiDocument.ParamAlterarAtivacaoWiFi;
import br.com.vivo.sn.catalogoServico.ParamAlterarAtivacaoWiFiDocument.ParamAlterarAtivacaoWiFi.Status;
import br.com.vivo.sn.catalogoServico.ParametrosAlterarCategoriaListaServicoDocument.ParametrosAlterarCategoriaListaServico;
import br.com.vivo.sn.catalogoServico.ParametrosAlterarCategoriaListaServicoDocument.ParametrosAlterarCategoriaListaServico.ListaIdServico;
import br.com.vivo.sn.catalogoServico.ParametrosAlterarServicoParametrizacaoDocument.ParametrosAlterarServicoParametrizacao;
import br.com.vivo.sn.catalogoServico.ParametrosAlterarServicoParametrizacaoDocument.ParametrosAlterarServicoParametrizacao.AtivaWiFi;
import br.com.vivo.sn.catalogoServico.ParametrosBuscarListaServicoParametrizacaoDocument.ParametrosBuscarListaServicoParametrizacao;
import br.com.vivo.sn.catalogoServico.ParametrosValidarListaServicoPorIdDocument.ParametrosValidarListaServicoPorId;
import br.com.vivo.sn.catalogoServico.ResultadoBuscarListaServicoParametrizacaoDocument.ResultadoBuscarListaServicoParametrizacao;
import br.com.vivo.sn.catalogoServico.ResultadoBuscarListaServicoParametrizacaoDocument.ResultadoBuscarListaServicoParametrizacao.ListaBuscarListaServicoParametrizacao;
import br.com.vivo.sn.catalogoServico.ResultadoBuscarListaServicoParametrizacaoDocument.ResultadoBuscarListaServicoParametrizacao.ListaBuscarListaServicoParametrizacao.RetornoBuscarListaServicoParametrizacao;
import br.com.vivo.sn.catalogoServico.ResultadoBuscarListaServicoPendValidacaoDocument.ResultadoBuscarListaServicoPendValidacao;
import br.com.vivo.sn.catalogoServico.ResultadoBuscarListaServicoPendValidacaoDocument.ResultadoBuscarListaServicoPendValidacao.ListaServico.Servico;
import br.com.vivo.sn.catalogoServico.ResultadoBuscarListaTipoServicoDocument.ResultadoBuscarListaTipoServico.ListaTipoServico;
import br.com.vivo.sn.catalogoServico.ResultadoBuscarListaTipoServicoDocument.ResultadoBuscarListaTipoServico.ListaTipoServico.Retorno;
import br.com.vivo.sn.catalogoUF.BuscarListaUFPorIdPlanoRequestDocument;
import br.com.vivo.sn.catalogoUF.BuscarListaUFPorIdPlanoResponseDocument;
import br.com.vivo.sn.catalogoUF.ParametrosBuscarListaUfPorIdPlanoDocument.ParametrosBuscarListaUfPorIdPlano;
import br.com.vivo.sn.catalogoUF.ParametrosBuscarListaUfPorIdPlanoDocument.ParametrosBuscarListaUfPorIdPlano.ListaUfPorIdPlano;
import br.com.vivo.sn.catalogoUF.ResultadoBuscarListaUFDocument.ResultadoBuscarListaUF;
import br.com.vivo.sn.catalogoUF.ResultadoBuscarListaUFDocument.ResultadoBuscarListaUF.UF;

import com.bea.control.ServiceControlException;
*/
public class ParametrizacaoPlanoServicoController extends BaseMappingDispatchAction {
/*	private static final long serialVersionUID = 1L;
	
	private String[] regionaisSelecionadas;
	private String[] ufsSelecionados;
	private String[] valoresRegionaisSelecionadas;
	
	public String[] getRegionaisSelecionadas() {
		return regionaisSelecionadas;
	}
	public void setRegionaisSelecionadas(String[] regionaisSelecionadas) {
		this.regionaisSelecionadas = regionaisSelecionadas;
	}
	
	public String[] getUfsSelecionados() {
		return ufsSelecionados;
	}
	public void setUfsSelecionados(String[] ufsSelecionados) {
		this.ufsSelecionados = ufsSelecionados;
	}

	public String[] getValoresRegionaisSelecionadas() {
		return valoresRegionaisSelecionadas;
	}
	public void setValoresRegionaisSelecionadas(
			String[] valoresRegionaisSelecionadas) {
		this.valoresRegionaisSelecionadas = valoresRegionaisSelecionadas;
	}
	
	@Control
	private PlataformaSoapServiceControl plataformaSoapServiceControl;

	@Control
	private GrupoServicoServiceControl grupoServicoSoapServiceControl;

	@Control
	private ServicoSoapServiceControl servicoSoapServiceControl;

	@Control
	private PlanoServiceControl planoSoapServiceControl;

	@Control
	private RegionalSoapServiceControl regionalSoapServiceControl;

	@Control
	private UFSoapServiceControl ufSoapServiceControl;

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "parametrizacaoPlanoServico.jsp") })
	public Forward begin() {
		HttpServletRequest request = getRequest();
		
		
		BuscarListaServicoPendValidacaoRequestDocument buscarListaServicoPendValidacaoRequestDocument = BuscarListaServicoPendValidacaoRequestDocument.Factory.newInstance();
		buscarListaServicoPendValidacaoRequestDocument.addNewBuscarListaServicoPendValidacaoRequest();
		List<Servico> servicoList = new ArrayList<Servico>();
		
		prepareServiceControl(servicoSoapServiceControl);
		try {
			BuscarListaServicoPendValidacaoResponseDocument listaServicoPendValidacaoResponseDocument = servicoSoapServiceControl.buscarListaServicoPendValidacao(buscarListaServicoPendValidacaoRequestDocument);
			ResultadoBuscarListaServicoPendValidacao resultadoBuscarListaServicoPendValidacao = listaServicoPendValidacaoResponseDocument.getBuscarListaServicoPendValidacaoResponse().getResultadoBuscarListaServicoPendValidacao();
			servicoList = resultadoBuscarListaServicoPendValidacao.getListaServico().getServicoList();
		} catch (ServiceControlException e) {
			tratarVazio(e);
		} catch (NullPointerException e) {
			
		}
		

		BuscarListaPlanoPendValidacaoRequestDocument buscarListaPlanoPendValidacaoRequestDocument = BuscarListaPlanoPendValidacaoRequestDocument.Factory.newInstance();
		buscarListaPlanoPendValidacaoRequestDocument.addNewBuscarListaPlanoPendValidacaoRequest();
		List<RetornoPlano> planoList = new ArrayList<RetornoPlano>();

		prepareServiceControl(planoSoapServiceControl);
		try {
		BuscarListaPlanoPendValidacaoResponseDocument buscarListaPlanoPendValidacaoResponseDocument = planoSoapServiceControl.buscarListaPlanoPendValidacao(buscarListaPlanoPendValidacaoRequestDocument);
		ResultadoBuscarListaPlanoPendValidacao resultadoBuscarListaPlanoPendValidacao = buscarListaPlanoPendValidacaoResponseDocument.getBuscarListaPlanoPendValidacaoResponse().getResultadoBuscarListaPlanoPendValidacao();
		planoList = resultadoBuscarListaPlanoPendValidacao.getListaPlano().getRetornoPlanoList();
		}catch(ServiceControlException e) {
			tratarVazio(e);
		} catch(NullPointerException e) {
			
		}

		// Buscar a lista de Plataformas
		prepareServiceControl(plataformaSoapServiceControl);
		List<Plataforma> plataformaLista = PlataformaService.getInstance().buscarListaPlataforma(plataformaSoapServiceControl);
		
		for (Iterator iter = plataformaLista.iterator(); iter.hasNext();) {
			Plataforma element = (Plataforma) iter.next();
			// Removendo Plataforma PREPAGO - Solicitação Mantis 10826
			if(element.getIdPlataforma() == 1L){
				plataformaLista.remove(element);
			}
		}
		
		request.setAttribute("servicoList", servicoList.size());
		request.setAttribute("planoList", planoList.size());
		request.setAttribute("plataformas", plataformaLista);
		
		return new Forward("success");
	}
	private void tratarVazio(ServiceControlException e) {
		try {
			if (e.hasSoapFault()) {
				String dadosAdicionais = null;
				NodeList childNodes = e.getSoapFault().getDetail()
						.getDomNode().getFirstChild().getChildNodes();
				for (int i = 0; i < childNodes.getLength(); i++) {
					Node node = childNodes.item(i);
					ErroInfo erroInfo = ErroInfo.Factory.parse(node);
					if (erroInfo.getCodigo() != null) {
					} else if (erroInfo.getDescricao() != null) {
					} else if (erroInfo.getDadosAdicionais() != null) {
						dadosAdicionais = erroInfo.getDadosAdicionais()
								.getDomNode().getFirstChild()
								.getNodeValue();
					}
				}
				if (dadosAdicionais != null && dadosAdicionais.equalsIgnoreCase("2")) {
					return;
				}
			}
			throw e;
		} catch (Exception ex) {
			throw e;
		}
	}

	@Jpf.Action()
	public Forward listarGrupoServicos() throws IOException {
		Long idSistema = null;
		HttpServletRequest request = getRequest();
		String idPlataformaStr =  request.getParameter("idPlataforma");
		Long idPlataforma = Long.parseLong(idPlataformaStr);
		idSistema = (idPlataforma == 3) ? 2L : null;
		request.setAttribute("idSistema", idSistema);
		request.setAttribute("idPlataforma", idPlataforma);
		
		BuscarListaGrupoServicoRequestDocument requestDocument = BuscarListaGrupoServicoRequestDocument.Factory.newInstance();
		ParametrosListarGrupoServico parametrosListarGrupoServico = requestDocument.addNewBuscarListaGrupoServicoRequest().addNewParametrosListarGrupoServico();
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
		prepareServiceControl(grupoServicoSoapServiceControl);
		BuscarListaGrupoServicoResponseDocument responseDocument = grupoServicoSoapServiceControl.buscarListaGrupoServico(requestDocument);
		List<Categoria> categoriaLista = responseDocument.getBuscarListaGrupoServicoResponse().getResultadoListarGrupoServico().getCategoriaList();
		
		JSONObject resultadoJSON = new JSONObject();
		String result = "";
		JSONArray jsonArrayCategorias = new JSONArray();
		try {
			for (Categoria categoria : categoriaLista) {
				JSONObject jsonResult = new JSONObject();
				jsonResult.put("id", categoria.getIdCategoria());
				jsonResult.put("nome", categoria.getNmCategoria());
				jsonArrayCategorias.put(jsonResult);
			}
			resultadoJSON.put("arrayCategorias", jsonArrayCategorias);
		} catch (JSONException e) {
		}
		result = resultadoJSON.toString();
		PrintWriter out = this.getResponse().getWriter();
		out.println(result);
		out.flush();
		return null;
	}


	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "resultadoPesquisaServicos.jsp") })
	public Forward pesquisarServicos(br.com.vivo.catalogoPRS.pageflows.param.planoServico.parametrizacao.ParametrizacaoPlanoServicoController.PesquisaServicosFormBean form) {
		
		BuscarListaServicoParametrizacaoRequestDocument buscarListaServicoParametrizacaoRequestDocument = BuscarListaServicoParametrizacaoRequestDocument.Factory.newInstance();
		ParametrosBuscarListaServicoParametrizacao parametrosBuscarListaServicoParametrizacao = buscarListaServicoParametrizacaoRequestDocument.addNewBuscarListaServicoParametrizacaoRequest().addNewParametrosBuscarListaServicoParametrizacao();
		PaginacaoIn paginacaoIn = parametrosBuscarListaServicoParametrizacao.addNewPaginacaoIn();
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		if(form.getPaginaSolicitada() == null) {
			paginacaoIn.setPaginaSolicitada(1);
		}else {
			paginacaoIn.setPaginaSolicitada(form.getPaginaSolicitada());
		}
		if(form.getIdPlataforma() != null) {
			parametrosBuscarListaServicoParametrizacao.setIdPlataforma(form.getIdPlataforma());
		}
		if(form.getIdCategoria() != null) {
			parametrosBuscarListaServicoParametrizacao.setIdCategoriaCatalogo(form.getIdCategoria());
		}
		if(form.getNmServico() != null && form.getNmServico().length() > 0) {
			parametrosBuscarListaServicoParametrizacao.setNmServico(form.getNmServico());
		}
		if(form.getCdServico() != null && form.getCdServico().length() > 0) {
			parametrosBuscarListaServicoParametrizacao.setCdServico(form.getCdServico());
		}
		if(form.getDisponibilidade() != null && !form.getDisponibilidade().equalsIgnoreCase("A")) {
			parametrosBuscarListaServicoParametrizacao.setIndisponivel(form.getDisponibilidade());
		}
		
		prepareServiceControl(servicoSoapServiceControl);
		BuscarListaServicoParametrizacaoResponseDocument buscarListaServicoParametrizacaoResponseDocument = servicoSoapServiceControl.buscarListaServicoParametrizacao(buscarListaServicoParametrizacaoRequestDocument);
		ResultadoBuscarListaServicoParametrizacao resultadoBuscarListaServicoParametrizacao = buscarListaServicoParametrizacaoResponseDocument.getBuscarListaServicoParametrizacaoResponse().getResultadoBuscarListaServicoParametrizacao();
		PaginacaoOut paginacaoOut = resultadoBuscarListaServicoParametrizacao.getPaginacaoOut();
		tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina());
		ListaBuscarListaServicoParametrizacao listaServicoParametrizacao = resultadoBuscarListaServicoParametrizacao.getListaBuscarListaServicoParametrizacao();
		
		HttpServletRequest request = getRequest();
		if(listaServicoParametrizacao != null) {
			List<RetornoBuscarListaServicoParametrizacao> servicoParametrizacaoList = listaServicoParametrizacao.getRetornoBuscarListaServicoParametrizacaoList();
			request.setAttribute("servicos", servicoParametrizacaoList);
			request.setAttribute("idPlataforma", form.getIdPlataforma());
		}
		
		Forward forward = new Forward("success");
		return forward;
	}
	
	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "resultadoPesquisaPlanos.jsp") })
	public Forward pesquisarPlanos(br.com.vivo.catalogoPRS.pageflows.param.planoServico.parametrizacao.ParametrizacaoPlanoServicoController.PesquisaPlanosFormBean form) {
		HttpServletRequest request = getRequest();
		BuscarListaPlanoParametrizacaoRequestDocument buscarListaPlanoParametrizacaoRequestDocument = BuscarListaPlanoParametrizacaoRequestDocument.Factory.newInstance();
		ParametrosBuscarListaPlanoParametrizacao parametrosBuscarListaPlanoParametrizacao = buscarListaPlanoParametrizacaoRequestDocument.addNewBuscarListaPlanoParametrizacaoRequest().addNewParametrosBuscarListaPlanoParametrizacao();
		br.com.vivo.sn.catalogoPlano.PaginacaoInDocument.PaginacaoIn paginacaoIn = parametrosBuscarListaPlanoParametrizacao.addNewPaginacaoIn();
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		if(form.getPaginaSolicitada() == null) {
			paginacaoIn.setPaginaSolicitada(1);
		}else {
			paginacaoIn.setPaginaSolicitada(form.getPaginaSolicitada());
		}
		if(form.getIdPlataforma() != null) {
			parametrosBuscarListaPlanoParametrizacao.setIdPlataforma(form.getIdPlataforma());
		}
		if(form.getRegionais() != null && form.getRegionais().length() > 0) {
			List<String> listaRegionais = new ArrayList<String>();
			List<String> listaUfs = new ArrayList<String>();
			Map<String, String[]> mapRegionais = extrairJSONChaveValor(form.getRegionais(), listaRegionais, listaUfs);
			
			for (String idRegional : listaRegionais) {
				if(parametrosBuscarListaPlanoParametrizacao.getListaRegional() == null) {
					parametrosBuscarListaPlanoParametrizacao.addNewListaRegional();
				}
				ListaRegional listaRegional = parametrosBuscarListaPlanoParametrizacao.getListaRegional();
				Regional regional = listaRegional.addNewRegional();
				regional.setIdRegional(Long.valueOf(idRegional));
				
				String[] arrayUfs = mapRegionais.get(idRegional);
				if(arrayUfs != null && arrayUfs.length > 0) {
					ListaUf listaUf = regional.addNewListaUf();
					for (String idUf : arrayUfs) {
						if(idUf != null && idUf.length() > 0)
							listaUf.addNewUf().setIdUf(Long.valueOf(idUf));
					}
				}
			}
		}

		if(form.getNmServico() != null && form.getNmServico().length() > 0) {
			parametrosBuscarListaPlanoParametrizacao.setNmComercial(form.getNmServico());
		}
		if(form.getCdServico() != null && form.getCdServico().length() > 0) {
			parametrosBuscarListaPlanoParametrizacao.setCdCodigo(form.getCdServico());
		}
		//Diferente de "A" porque o A é o A de Ambos.
		if(form.getDisponibilidade().trim().length() > 0 && !form.getDisponibilidade().equalsIgnoreCase("A")) {
			parametrosBuscarListaPlanoParametrizacao.setIndisponivel(form.getDisponibilidade());
		}
		
		if(form.getIndTitular()){
			parametrosBuscarListaPlanoParametrizacao.setIndTitDep("T");
		}else{
			parametrosBuscarListaPlanoParametrizacao.setIndTitDep("D");
		}
		
		
		prepareServiceControl(planoSoapServiceControl);
		BuscarListaPlanoParametrizacaoResponseDocument buscarListaPlanoParametrizacaoResponseDocument = planoSoapServiceControl.buscarListaPlanoParametrizacao(buscarListaPlanoParametrizacaoRequestDocument);
		ResultadoBuscarListaPlanoParametrizacao resultadoBuscarListaPlanoParametrizacao = buscarListaPlanoParametrizacaoResponseDocument.getBuscarListaPlanoParametrizacaoResponse().getResultadoBuscarListaPlanoParametrizacao();
		PaginacaoOut paginacaoOut = resultadoBuscarListaPlanoParametrizacao.getPaginacaoOut();
		tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina());
		ListaBuscarListaPlanoParametrizacao listaPlanoParametrizacao = resultadoBuscarListaPlanoParametrizacao.getListaBuscarListaPlanoParametrizacao();
		
		if(listaPlanoParametrizacao != null) {
			List<RetornoBuscarListaPlanoParametrizacao> planoParametrizacaoList = listaPlanoParametrizacao.getRetornoBuscarListaPlanoParametrizacaoList();
			request.setAttribute("planos", planoParametrizacaoList);
			request.setAttribute("idPlataforma", form.getIdPlataforma());
		}
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "alterarServico.jsp") })
	public Forward carregarAlterarServico() throws ParseException {
		HttpServletRequest request = getRequest();
		if(request.getParameter("id_plataforma") != null && request.getParameter("id_plataforma").trim().length() > 0) {
			request.setAttribute("idPlataforma", request.getParameter("id_plataforma"));
		}
		if(request.getParameter("id_servico") != null && request.getParameter("id_servico").trim().length() > 0) {
			request.setAttribute("idServico", request.getParameter("id_servico"));
		}
		if(request.getParameter("id_tpServico") != null && request.getParameter("id_tpServico").trim().length() > 0) {
			request.setAttribute("idTpServico", request.getParameter("id_tpServico"));
		}
		if(request.getParameter("id_categoriaLegado") != null && request.getParameter("id_categoriaLegado").trim().length() > 0) {
			request.setAttribute("idCategoriaLegado", request.getParameter("id_categoriaLegado"));
		}
		if(request.getParameter("id_categoriaCatalogo") != null && request.getParameter("id_categoriaCatalogo").trim().length() > 0) {
			request.setAttribute("idCategoriaCatalogo", request.getParameter("id_categoriaCatalogo"));
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
			request.setAttribute("dtAlteracao", request.getParameter("dt_alteracao"));
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
		
		BuscarListaTipoServicoRequestDocument buscarListaTipoServicoRequestDocument = BuscarListaTipoServicoRequestDocument.Factory.newInstance();
		buscarListaTipoServicoRequestDocument.addNewBuscarListaTipoServicoRequest();
		prepareServiceControl(servicoSoapServiceControl);
		BuscarListaTipoServicoResponseDocument listaTipoServicoResponseDocument = servicoSoapServiceControl.buscarListaTipoServico(buscarListaTipoServicoRequestDocument);
		ListaTipoServico listaTipoServico = listaTipoServicoResponseDocument.getBuscarListaTipoServicoResponse().getResultadoBuscarListaTipoServico().getListaTipoServico();
		List<Retorno> tpServicoList = listaTipoServico.getRetornoList();
		
		Long idSistema = null;
		Long idPlataforma = null;
		String idPlataformaStr =  request.getParameter("id_plataforma");
		if(idPlataformaStr != null && idPlataformaStr.trim().length() > 0) {
			idPlataforma = Long.parseLong(idPlataformaStr);
		}
		idSistema = (idPlataforma == 3) ? 2L : null;
		
		prepareServiceControl(grupoServicoSoapServiceControl);
		List<Categoria> categoriaList = GrupoServicoService.getInstance().buscarListaGrupoServico(grupoServicoSoapServiceControl, idPlataforma, idSistema);

		request.setAttribute("categoriaList", categoriaList);
		request.setAttribute("tpServico", tpServicoList);
		
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupVisualizarServicos.jsp") })
	public Forward popupVisualizaServicos() {
		
		HttpServletRequest request = getRequest();
		BuscarListaServicoPendValidacaoRequestDocument buscarListaServicoPendValidacaoRequestDocument = BuscarListaServicoPendValidacaoRequestDocument.Factory.newInstance();
		buscarListaServicoPendValidacaoRequestDocument.addNewBuscarListaServicoPendValidacaoRequest();

		BuscarListaServicoPendValidacaoResponseDocument listaServicoPendValidacaoResponseDocument = servicoSoapServiceControl.buscarListaServicoPendValidacao(buscarListaServicoPendValidacaoRequestDocument);
		ResultadoBuscarListaServicoPendValidacao resultadoBuscarListaServicoPendValidacao = listaServicoPendValidacaoResponseDocument.getBuscarListaServicoPendValidacaoResponse().getResultadoBuscarListaServicoPendValidacao();
		List<Servico> servicoList = resultadoBuscarListaServicoPendValidacao.getListaServico().getServicoList();

		if(servicoList != null && servicoList.size() > 0) {
			request.setAttribute("servicosList", servicoList);
		}
	
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupVisualizarPlanos.jsp") })
	public Forward popupVisualizaPlanos() {
		
		HttpServletRequest request = getRequest();
		BuscarListaPlanoPendValidacaoRequestDocument buscarListaPlanoPendValidacaoRequestDocument = BuscarListaPlanoPendValidacaoRequestDocument.Factory.newInstance();
		buscarListaPlanoPendValidacaoRequestDocument.addNewBuscarListaPlanoPendValidacaoRequest();

		prepareServiceControl(planoSoapServiceControl);
		BuscarListaPlanoPendValidacaoResponseDocument buscarListaPlanoPendValidacaoResponseDocument = planoSoapServiceControl.buscarListaPlanoPendValidacao(buscarListaPlanoPendValidacaoRequestDocument);
		ResultadoBuscarListaPlanoPendValidacao resultadoBuscarListaPlanoPendValidacao = buscarListaPlanoPendValidacaoResponseDocument.getBuscarListaPlanoPendValidacaoResponse().getResultadoBuscarListaPlanoPendValidacao();
		List<RetornoPlano> retornoPlanoList = resultadoBuscarListaPlanoPendValidacao.getListaPlano().getRetornoPlanoList();
		
		if(retornoPlanoList != null && retornoPlanoList.size() > 0) {
			request.setAttribute("planos", retornoPlanoList);
		}
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupRegionaisAndUfs.jsp") })
	public Forward listarRegionais() {
		String regionaisJSON = getRequest().getParameter("hiddenRegionais");
		if (regionaisJSON != null) {
			List<String> chaves = new ArrayList<String>();
			List<String> valores = new ArrayList<String>();
			//Map<String, String[]> regionaisSelecionadas = extrairJSONChaveValor(regionaisJSON, chaves, valores);
			this.regionaisSelecionadas = chaves.toArray(new String[]{});
			this.ufsSelecionados = valores.toArray(new String[]{});
		}
		BuscarListaRegionalComUFRequestDocument regionalComUFRequestDocument = BuscarListaRegionalComUFRequestDocument.Factory.newInstance();
		regionalComUFRequestDocument.addNewBuscarListaRegionalComUFRequest();

		prepareServiceControl(regionalSoapServiceControl);
		BuscarListaRegionalComUFResponseDocument buscarListaRegionalComUFResponseDocument = regionalSoapServiceControl.buscarListaRegionalComUF(regionalComUFRequestDocument);
		List<ListaRegionalUf> listaRegionalUfList = buscarListaRegionalComUFResponseDocument.getBuscarListaRegionalComUFResponse().getListaRegionalUfList();
		
		HttpServletRequest request = getRequest();
		if(listaRegionalUfList != null) {
			request.setAttribute("regionais", listaRegionalUfList);
		}
		Forward forward = new Forward("success");
		return forward;
	}
	
	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupUfsPlanos.jsp") })
	public Forward popupUFs() {
		Long idPlano = Long.valueOf(getRequest().getParameter("id_plano"));
		BuscarListaUFPorIdPlanoRequestDocument buscarListaUFPorIdPlanoRequestDocument = BuscarListaUFPorIdPlanoRequestDocument.Factory.newInstance();
		ParametrosBuscarListaUfPorIdPlano parametrosBuscarListaUfPorIdPlano = buscarListaUFPorIdPlanoRequestDocument.addNewBuscarListaUFPorIdPlanoRequest().addNewParametrosBuscarListaUfPorIdPlano();
		ListaUfPorIdPlano listaUfPorIdPlano = parametrosBuscarListaUfPorIdPlano.addNewListaUfPorIdPlano();
		listaUfPorIdPlano.setIdPlano(Long.valueOf(idPlano));
		
		prepareServiceControl(ufSoapServiceControl);
		BuscarListaUFPorIdPlanoResponseDocument buscarListaUFPorIdPlanoResponseDocument = ufSoapServiceControl.buscarListaUFPorIdPlano(buscarListaUFPorIdPlanoRequestDocument);
		ResultadoBuscarListaUF resultadoBuscarListaUF = buscarListaUFPorIdPlanoResponseDocument.getBuscarListaUFPorIdPlanoResponse().getResultadoBuscarListaUF();
		if(resultadoBuscarListaUF != null) {
			List<UF> listUF = resultadoBuscarListaUF.getUFList();
			getRequest().setAttribute("ufs", listUF);
		}
		Forward forward = new Forward("success");
		return forward;
	}
	
	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupNovaCategoria.jsp") })
	public Forward pesquisarCategorias() {
		HttpServletRequest request = getRequest();
	
		Long idPlataforma = 1L;
		BuscarListaGrupoServicoRequestDocument requestDocument = BuscarListaGrupoServicoRequestDocument.Factory.newInstance();
		ParametrosListarGrupoServico parametrosListarGrupoServico = requestDocument.addNewBuscarListaGrupoServicoRequest().addNewParametrosListarGrupoServico();
		if(idPlataforma != null) {
			parametrosListarGrupoServico.setIndCatalogo(idPlataforma);
		}
		prepareServiceControl(grupoServicoSoapServiceControl);
		BuscarListaGrupoServicoResponseDocument responseDocument = grupoServicoSoapServiceControl.buscarListaGrupoServico(requestDocument);
		List<Categoria> categoriaLista = responseDocument.getBuscarListaGrupoServicoResponse().getResultadoListarGrupoServico().getCategoriaList();
		
		request.setAttribute("categorias", categoriaLista);
		request.setAttribute("idPlataforma", idPlataforma);
		Forward forward = new Forward("success");
		return forward;
	}
	
	@Jpf.Action()
	public Forward adicionarCategorias(br.com.vivo.catalogoPRS.pageflows.param.planoServico.parametrizacao.ParametrizacaoPlanoServicoController.AssociarCategoriaAtivaWifiFormBean form) throws IOException {

		CriarGrupoServicoCatalogoRequestDocument criarGrupoServicoCatalogoRequestDocument = CriarGrupoServicoCatalogoRequestDocument.Factory.newInstance();
		ParametroscriarGrupoServicoCatalogo grupoServicoCatalogo = criarGrupoServicoCatalogoRequestDocument.addNewCriarGrupoServicoCatalogoRequest().addNewParametroscriarGrupoServicoCatalogo();
		grupoServicoCatalogo.setNomeGrupoServico(form.getNmCategoria().toUpperCase());
		grupoServicoCatalogo.setIndisponivel(form.getIndisponivel());
		
		prepareServiceControl(grupoServicoSoapServiceControl);
		grupoServicoSoapServiceControl.criarGrupoServicoCatalogo(criarGrupoServicoCatalogoRequestDocument);
		
		try {
			HttpServletResponse response = getResponse();
			response.setContentType("text/javascript");
			PrintWriter out = this.getResponse().getWriter();
			out.println("$('pesquisar_categorias').onclick();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "alterarCategoria.jsp") })
	public Forward abrirAlterarCategoria() {
		
		HttpServletRequest request = getRequest();
		request.setAttribute("idCategoria", request.getParameter("id_categoria"));
		request.setAttribute("nmCategoria", request.getParameter("nm_categoria"));
		request.setAttribute("indisponivel", request.getParameter("indisponivel"));
		
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action()
	public Forward alterarValorCategoria(br.com.vivo.catalogoPRS.pageflows.param.planoServico.parametrizacao.ParametrizacaoPlanoServicoController.AlterarCategoriaFormBean form) {
		
		AlterarGrupoServicoCatalogoRequestDocument alterarGrupoServicoCatalogoRequestDocument = AlterarGrupoServicoCatalogoRequestDocument.Factory.newInstance();
		ParametrosAlterarGrupoServicoCatalogo alterarGrupoServicoCatalogo = alterarGrupoServicoCatalogoRequestDocument.addNewAlterarGrupoServicoCatalogoRequest().addNewParametrosAlterarGrupoServicoCatalogo();
		alterarGrupoServicoCatalogo.setIdGrupoServico(form.getIdCategoria());
		alterarGrupoServicoCatalogo.setNmGrupoServico(form.getNmCategoria());
		alterarGrupoServicoCatalogo.setIndisponivel(form.getIndisponivel());
		
		prepareServiceControl(grupoServicoSoapServiceControl);
		grupoServicoSoapServiceControl.alterarGrupoServicoCatalogo(alterarGrupoServicoCatalogoRequestDocument);
		
		try {
			HttpServletResponse response = getResponse();
			response.setContentType("text/javascript");
			PrintWriter out = this.getResponse().getWriter();
			out.println("$('pesquisar_categorias').onclick();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupAlterarCategoria.jsp") })
	public Forward popupAlterarValorCategoria() {
		
		HttpServletRequest request = getRequest();
		Long idCategoria = Long.valueOf(request.getParameter("idCategoria"));
		BuscarServicoPorGrupoServicoCatalogoRequestDocument servicoPorGrupoServicoCatalogoRequestDocument = BuscarServicoPorGrupoServicoCatalogoRequestDocument.Factory.newInstance();
		ParametrosBuscarListaServicoPorGrupoServicoCatalogo buscarListaServicoPorGrupoServicoCatalogo = servicoPorGrupoServicoCatalogoRequestDocument.addNewBuscarServicoPorGrupoServicoCatalogoRequest().addNewParametrosBuscarListaServicoPorGrupoServicoCatalogo();
		buscarListaServicoPorGrupoServicoCatalogo.setIdGrupoServico(idCategoria);
		
		prepareServiceControl(grupoServicoSoapServiceControl);
		BuscarServicoPorGrupoServicoCatalogoResponseDocument grupoServicoCatalogoResponseDocument = grupoServicoSoapServiceControl.buscarServicoPorGrupoServicoCatalogo(servicoPorGrupoServicoCatalogoRequestDocument);
		ResultadoBuscarServicoPorGrupoServicoCatalogo resultadoBuscarServicoPorGrupoServicoCatalogo = grupoServicoCatalogoResponseDocument.getBuscarServicoPorGrupoServicoCatalogoResponse().getResultadoBuscarServicoPorGrupoServicoCatalogo();
		long qtdeServico = resultadoBuscarServicoPorGrupoServicoCatalogo.getQtdeServico();
		if(qtdeServico > 0) {
			request.setAttribute("qtdeServico", qtdeServico);
		}else {
			request.setAttribute("qtdeServico", 0);
		}
		
		Forward forward = new Forward("success");
		return forward;
	}
	
	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupApagarCategoria.jsp") })
	public Forward abrirPopupApagarCategoria() {
		
		HttpServletRequest request = getRequest();
		Long idCategoria = Long.valueOf(request.getParameter("id_categoria"));
		String nmCategoria = request.getParameter("nm_categoria");
		BuscarServicoPorGrupoServicoCatalogoRequestDocument servicoPorGrupoServicoCatalogoRequestDocument = BuscarServicoPorGrupoServicoCatalogoRequestDocument.Factory.newInstance();
		ParametrosBuscarListaServicoPorGrupoServicoCatalogo buscarListaServicoPorGrupoServicoCatalogo = servicoPorGrupoServicoCatalogoRequestDocument.addNewBuscarServicoPorGrupoServicoCatalogoRequest().addNewParametrosBuscarListaServicoPorGrupoServicoCatalogo();
		buscarListaServicoPorGrupoServicoCatalogo.setIdGrupoServico(idCategoria);
		
		prepareServiceControl(grupoServicoSoapServiceControl);
		BuscarServicoPorGrupoServicoCatalogoResponseDocument grupoServicoCatalogoResponseDocument = grupoServicoSoapServiceControl.buscarServicoPorGrupoServicoCatalogo(servicoPorGrupoServicoCatalogoRequestDocument);
		ResultadoBuscarServicoPorGrupoServicoCatalogo resultadoBuscarServicoPorGrupoServicoCatalogo = grupoServicoCatalogoResponseDocument.getBuscarServicoPorGrupoServicoCatalogoResponse().getResultadoBuscarServicoPorGrupoServicoCatalogo();
		long qtdeServico = resultadoBuscarServicoPorGrupoServicoCatalogo.getQtdeServico();
		if(qtdeServico > 0) {
			request.setAttribute("qtdeServico", qtdeServico);
		}else {
			request.setAttribute("qtdeServico", 0);
		}
		
		request.setAttribute("nmCategoria", nmCategoria);
		request.setAttribute("idCategoria", idCategoria);
		Forward forward = new Forward("success");
		return forward;
	}
	
	@Jpf.Action()
	public Forward deletarCategoria() {
		ExcluirListaGrupoServicoCatalogoRequestDocument excluirListaGrupoServicoCatalogoRequestDocument = ExcluirListaGrupoServicoCatalogoRequestDocument.Factory.newInstance();
		ParametrosExcluirListaGrupoServicoCatalogo excluirListaGrupoServicoCatalogo = excluirListaGrupoServicoCatalogoRequestDocument.addNewExcluirListaGrupoServicoCatalogoRequest().addNewParametrosExcluirListaGrupoServicoCatalogo();
		excluirListaGrupoServicoCatalogo.setIdGrupoServico(Long.valueOf(getRequest().getParameter("id_categoria")));
		
		prepareServiceControl(grupoServicoSoapServiceControl);
		grupoServicoSoapServiceControl.excluirListaGrupoServicoCatalogo(excluirListaGrupoServicoCatalogoRequestDocument);
		try {
			HttpServletResponse response = getResponse();
			response.setContentType("text/javascript");
			PrintWriter out = this.getResponse().getWriter();
			out.println("$('pesquisar_categorias').onclick();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", action = "begin") })
	public Forward validarSicronizacaoServicos(br.com.vivo.catalogoPRS.pageflows.param.planoServico.parametrizacao.ParametrizacaoPlanoServicoController.ValidarServicosFormBean form) throws CatalogoPRSException {
		
		ValidarListaServicoPorIdRequestDocument validarListaServicoPorIdRequestDocument = ValidarListaServicoPorIdRequestDocument.Factory.newInstance();
		ParametrosValidarListaServicoPorId parametrosValidarListaServicoPorId = validarListaServicoPorIdRequestDocument.addNewValidarListaServicoPorIdRequest().addNewParametrosValidarListaServicoPorId();
		
		if(form.getIdsServico() != null && form.getIdsServico().toString().length() > 0) {
			Long idsServico[] = form.getIdsServico();
			for (Long idServico : idsServico) {
				parametrosValidarListaServicoPorId.addIdServico(idServico);
			}
		}
		
		prepareServiceControl(servicoSoapServiceControl);
		try {
			servicoSoapServiceControl.validarListaServicoPorId(validarListaServicoPorIdRequestDocument);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		Forward forward = new Forward("success");
		return forward;
	}
	
	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", action = "begin") })
	public Forward validarSicronizacaoPlanos(br.com.vivo.catalogoPRS.pageflows.param.planoServico.parametrizacao.ParametrizacaoPlanoServicoController.ValidarPlanosFormBean form) {
		
		ValidarListaPlanoPorIdRequestDocument validarListaPlanoPorIdRequestDocument = ValidarListaPlanoPorIdRequestDocument.Factory.newInstance();
		ParametrosValidarListaPlanoPorId parametrosValidarListaPlanoPorId = validarListaPlanoPorIdRequestDocument.addNewValidarListaPlanoPorIdRequest().addNewParametrosValidarListaPlanoPorId();
		if(form.getIdPlano() != null) {
			Long idsPlano[] = form.getIdPlano();
			for (Long idPlano : idsPlano) {
				parametrosValidarListaPlanoPorId.addIdPlano(idPlano);
			}
		}
		prepareServiceControl(planoSoapServiceControl);
		try {
			planoSoapServiceControl.validarListaPlanoPorId(validarListaPlanoPorIdRequestDocument);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Forward forward = new Forward("success");
		return forward;
	}
	
	
	@Jpf.Action()
	public Forward alterarServico(br.com.vivo.catalogoPRS.pageflows.param.planoServico.parametrizacao.ParametrizacaoPlanoServicoController.AlterarServicosFormBean form) throws IOException, CatalogoPRSException {
		if(form.getQtdMinAtivacaoCatalogo() > form.getQtdMinAtivLegado()) {
			throw new CatalogoPRSException("O limite para ativação padrão no Catálogo dever ser igual ou menor ao limite padrão no Sistema Origem.");
		}
		else if(form.getQtdMaxAtivacaoCatalogo() > form.getQtdMaxAtivLegado()){
			throw new CatalogoPRSException("O limite para ativação máxima no Catálogo dever ser igual ou menor ao limite máxmo no Sistema Origem.");
		}
		else {
			AlterarServicoParametrizacaoRequestDocument requestDocument = AlterarServicoParametrizacaoRequestDocument.Factory.newInstance();
			ParametrosAlterarServicoParametrizacao parametrosAlterarServicoParametrizacao = requestDocument.addNewAlterarServicoParametrizacaoRequest().addNewParametrosAlterarServicoParametrizacao();
			if(form.getIdServico() != null) {
				parametrosAlterarServicoParametrizacao.setIdServico(form.getIdServico());
			}
			if(form.getIdTipoServico() != null) {
				parametrosAlterarServicoParametrizacao.setIdTpServico(form.getIdTipoServico());
			}
			if(form.getIdCategoriaCatalogo() != null) {
				parametrosAlterarServicoParametrizacao.setIdCategoriaCatalogo(form.getIdCategoriaCatalogo());
			}
			if(form.getQtdMinAtivacaoCatalogo() != null) {
				parametrosAlterarServicoParametrizacao.setQtdMinAtivacaoCatalogo(form.getQtdMinAtivacaoCatalogo());
			}
			if(form.getQtdMaxAtivacaoCatalogo() != null) {
				parametrosAlterarServicoParametrizacao.setQtdMaxAtivacaoCatalogo(form.getQtdMaxAtivacaoCatalogo());
			}
			if(form.getInDisponibilidadeCatalogo() != null && form.getInDisponibilidadeCatalogo().trim().length() > 0) {
				parametrosAlterarServicoParametrizacao.setInDisponibilidadeCatalogo(br.com.vivo.sn.catalogoServico.ParametrosAlterarServicoParametrizacaoDocument.ParametrosAlterarServicoParametrizacao.InDisponibilidadeCatalogo.Enum.forString(form.getInDisponibilidadeCatalogo()));
			}
			if(form.getOpAtivaWiFi() != null && form.getOpAtivaWiFi().trim().length() > 0) {
				parametrosAlterarServicoParametrizacao.setAtivaWiFi(AtivaWiFi.Enum.forString(form.getOpAtivaWiFi()));
			}
			
			prepareServiceControl(servicoSoapServiceControl);
			servicoSoapServiceControl.alterarServicoParametrizacao(requestDocument);
			try {
				HttpServletResponse response = getResponse();
				response.setContentType("text/javascript");
				PrintWriter out = this.getResponse().getWriter();
				out.println("$('botao_pesquisar_parametrizacao').onclick();");
				out.println("$('servicos_update').hide();");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	
	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupAssociarCategoria.jsp") })
	public Forward abrirPoupuAssociarCategria(br.com.vivo.catalogoPRS.pageflows.param.planoServico.parametrizacao.ParametrizacaoPlanoServicoController.AssociarCategoriaAtivaWifiFormBean form) throws CatalogoPRSException {
		HttpServletRequest request = getRequest();
		Long idPlataforma = Long.valueOf(request.getParameter("id_plataforma"));

		BuscarListaGrupoServicoRequestDocument buscarListaGrupoServicoRequestDocument = BuscarListaGrupoServicoRequestDocument.Factory.newInstance();
		ParametrosListarGrupoServico parametrosListarGrupoServico = buscarListaGrupoServicoRequestDocument.addNewBuscarListaGrupoServicoRequest().addNewParametrosListarGrupoServico();
		
		if(PlataformaEnum.PREPAGO.equals(idPlataforma)){
			parametrosListarGrupoServico.setIndCatalogo(new Long(2));
		}else if(PlataformaEnum.POSPAGO.equals(idPlataforma)){
			parametrosListarGrupoServico.setIndCatalogo(new Long(1));
		}else if(PlataformaEnum.CONTROLE.equals(idPlataforma)){
			parametrosListarGrupoServico.setIndCatalogo(new Long(3));
		}
		
		
		prepareServiceControl(grupoServicoSoapServiceControl);
		BuscarListaGrupoServicoResponseDocument listaGrupoServicoResponseDocument = grupoServicoSoapServiceControl.buscarListaGrupoServico(buscarListaGrupoServicoRequestDocument);
		List<Categoria> categoriaList = listaGrupoServicoResponseDocument.getBuscarListaGrupoServicoResponse().getResultadoListarGrupoServico().getCategoriaList();
		
		request.setAttribute("listCategoria", categoriaList);
		Forward forward = new Forward("success");
		return forward;
	}
	
	@Jpf.Action()
	public Forward associarNovaCategria(br.com.vivo.catalogoPRS.pageflows.param.planoServico.parametrizacao.ParametrizacaoPlanoServicoController.AssociarCategoriaAtivaWifiFormBean form) throws CatalogoPRSException {
		try {
			HttpServletResponse response = getResponse();
			response.setContentType("text/javascript");
			PrintWriter out = this.getResponse().getWriter();
			out.println("$('hidden_idCategoria').value = " + form.getIdCategoria()+";");
			out.println("$('salvar_associacao_categoria').onclick();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Jpf.Action()
	public Forward gravarAssociacaoCategoria(br.com.vivo.catalogoPRS.pageflows.param.planoServico.parametrizacao.ParametrizacaoPlanoServicoController.AssociarCategoriaAtivaWifiFormBean form) {
		AlterarCategoriaListaServicoRequestDocument alterarCategoriaListaServicoRequestDocument = AlterarCategoriaListaServicoRequestDocument.Factory.newInstance();
		ParametrosAlterarCategoriaListaServico parametrosAlterarCategoriaListaServico = alterarCategoriaListaServicoRequestDocument.addNewAlterarCategoriaListaServicoRequest().addNewParametrosAlterarCategoriaListaServico();
		
		ListaIdServico listaIdServico = ListaIdServico.Factory.newInstance();
		Long[] arrayServicos = form.getIdsServicos();
		for (Long idServico : arrayServicos) {
			listaIdServico.addIdServico(idServico);
		}
		if(form.getIdCategoria() != null) {
			parametrosAlterarCategoriaListaServico.setIdCategoria(form.getIdCategoria());
		}
		
		if(listaIdServico.sizeOfIdServicoArray() > 0) {
			parametrosAlterarCategoriaListaServico.setListaIdServico(listaIdServico);
		}
		
		prepareServiceControl(servicoSoapServiceControl);
		try {
			 servicoSoapServiceControl.alterarCategoriaListaServico(alterarCategoriaListaServicoRequestDocument);
		} catch (Exception e) {
			e.getStackTrace();
		}
		try {
			HttpServletResponse response = getResponse();
			response.setContentType("text/javascript");
			PrintWriter out = this.getResponse().getWriter();
			out.println("$('botao_pesquisar_parametrizacao').onclick();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "alterarPlanos.jsp") })
	public Forward carregarAlterarPlano() {
		HttpServletRequest request = getRequest();
		if(request.getParameter("ind_titDep").equalsIgnoreCase("T")) {
			request.setAttribute("indTitDep", true);
		}
		else if(request.getParameter("ind_titDep").equalsIgnoreCase("D")){
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
			request.setAttribute("dtUltimaAlteracao", request.getParameter("dt_ultimaAlteracao"));
		}
		if(request.getParameter("nm_usuarioAlteracao") != null && request.getParameter("nm_usuarioAlteracao").trim().length() > 0) {
			request.setAttribute("nmUsuarioAlteracao", request.getParameter("nm_usuarioAlteracao"));
		}
		if(request.getParameter("indisponibilidade_catalogo") != null && request.getParameter("indisponibilidade_catalogo").trim().length() > 0) {
			request.setAttribute("inDispCatalogo", request.getParameter("indisponibilidade_catalogo"));
		}
		if(request.getParameter("indisponibilidade_legado").trim().length() > 0 && request.getParameter("indisponibilidade_legado").equalsIgnoreCase("N")) {
			request.setAttribute("inDispLegado", "Não");
		}
		else if(request.getParameter("indisponibilidade_legado").trim().length() > 0 && request.getParameter("indisponibilidade_legado").equalsIgnoreCase("S")) {
			request.setAttribute("inDispLegado", "Sim");
		}
		
		if(request.getParameter("ativa_wifi") != null && request.getParameter("ativa_wifi").trim().length() > 0) {
			request.setAttribute("ativaWiFi", request.getParameter("ativa_wifi"));
		}
		
		BuscarListaTipoPlanoRequestDocument requestDocument = BuscarListaTipoPlanoRequestDocument.Factory.newInstance();
		ParametrosBuscarListaTipoPlano parametrosBuscarListaTipoPlano = requestDocument.addNewBuscarListaTipoPlanoRequest().addNewParametrosBuscarListaTipoPlano();
		if(request.getParameter("id_tipoPlano") != null && request.getParameter("id_tipoPlano").trim().length() > 0) {
			parametrosBuscarListaTipoPlano.setIdTipoPlano(Long.valueOf(request.getParameter("id_tipoPlano")));
		}
		if(request.getParameter("id_plataforma") != null && request.getParameter("id_plataforma").trim().length() > 0) {
			parametrosBuscarListaTipoPlano.setIdPlataforma(Long.valueOf(request.getParameter("id_plataforma")));
		}
		BuscarListaTipoPlanoResponseDocument responseDocument = planoSoapServiceControl.buscarListaTipoPlano(requestDocument);
		List<TipoPlano> tipoPlanoLista = responseDocument.getBuscarListaTipoPlanoResponse().getResultadoBuscarListaTipoPlano().getListaTipoPlano().getTipoPlanoList();
		request.setAttribute("tipoPlano", tipoPlanoLista);
		Forward forward = new Forward("success");
		return forward;
	}
	
	@Jpf.Action()
	public Forward alterarPlanos(br.com.vivo.catalogoPRS.pageflows.param.planoServico.parametrizacao.ParametrizacaoPlanoServicoController.AlterarPlanosFormBean form) throws CatalogoPRSException {
		if(form.getQtdMaxDependenteCatalogo() > form.getQtdMaxDependenteLegado()) {
			throw new CatalogoPRSException(" O limite de dependentes no Catálogo deve ser menor ou igual ao limite de dependentes no Sistema Origem.");
		}else {
			AlterarPlanoParametrizacaoRequestDocument alterarPlanoParametrizacaoRequestDocument = AlterarPlanoParametrizacaoRequestDocument.Factory.newInstance();
			ParametroAlterarPlano parametroAlterarPlano = alterarPlanoParametrizacaoRequestDocument.addNewAlterarPlanoParametrizacaoRequest().addNewParametroAlterarPlano();
			if(form.getIdPlano() != null) {
				parametroAlterarPlano.setIdPlano(form.getIdPlano());
			}
			if(form.getQtdMaxDependenteCatalogo() != null) {
				parametroAlterarPlano.setQtdMaxDependentes(form.getQtdMaxDependenteCatalogo());
			}
			if(form.getIdTpPlano() != null) {
				parametroAlterarPlano.setIdTipoPlano(form.getIdTpPlano());
			}
			if(form.getInDisponibilidadeCatalogo() != null && form.getInDisponibilidadeCatalogo().trim().length() > 0) {
				parametroAlterarPlano.setInDisponibilidadeCatalogo(Enum.forString(form.getInDisponibilidadeCatalogo()));
			}
			if(form.getOpAtivaWiFi() != null && form.getOpAtivaWiFi().trim().length() > 0) {
				parametroAlterarPlano.setAtivaWiFi(br.com.vivo.sn.catalogoPlano.ParametroAlterarPlanoDocument.ParametroAlterarPlano.AtivaWiFi.Enum.forString(form.getOpAtivaWiFi()));
			}
			
			prepareServiceControl(planoSoapServiceControl);
			planoSoapServiceControl.alterarPlanoParametrizacao(alterarPlanoParametrizacaoRequestDocument);
			try {
				HttpServletResponse response = this.getResponse();
				response.setContentType("text/javascript");
				PrintWriter out = this.getResponse().getWriter();
				out.println("$('botao_pesquisar_parametrizacao').onclick();");
				out.println("$('servicos_update').hide();");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	@Jpf.Action()
	public Forward gravarAtivacaoWiFi(br.com.vivo.catalogoPRS.pageflows.param.planoServico.parametrizacao.ParametrizacaoPlanoServicoController.AssociarCategoriaAtivaWifiFormBean form) {
		
		AlterarAtivacaoWiFiRequestDocument alterarAtivacaoWiFiRequestDocument = AlterarAtivacaoWiFiRequestDocument.Factory.newInstance();
		ParamAlterarAtivacaoWiFi paramAlterarAtivacaoWiFi = alterarAtivacaoWiFiRequestDocument.addNewAlterarAtivacaoWiFiRequest().addNewParamAlterarAtivacaoWiFi();

		br.com.vivo.sn.catalogoServico.ParamAlterarAtivacaoWiFiDocument.ParamAlterarAtivacaoWiFi.ListaIdServico listaIdServico =
			br.com.vivo.sn.catalogoServico.ParamAlterarAtivacaoWiFiDocument.ParamAlterarAtivacaoWiFi.ListaIdServico.Factory.newInstance();
		
		Long[] arrayServicos = form.getIdsServicos();
		
		for (Long idServico : arrayServicos) {
			listaIdServico.addIdServico(idServico);
		}
		
		if(form.getOpAtivaWiFi() != null && !form.getOpAtivaWiFi().equals("")) {
			paramAlterarAtivacaoWiFi.setStatus(Status.Enum.forString(form.getOpAtivaWiFi()));
		}
		
		if(listaIdServico.sizeOfIdServicoArray() > 0) {
			paramAlterarAtivacaoWiFi.setListaIdServico(listaIdServico);
		}
		
		prepareServiceControl(servicoSoapServiceControl);
		try {
			 servicoSoapServiceControl.alterarAtivacaoWiFi(alterarAtivacaoWiFiRequestDocument);
		} catch (Exception e) {
			e.getStackTrace();
		}
		try {
			HttpServletResponse response = getResponse();
			response.setContentType("text/javascript");
			PrintWriter out = this.getResponse().getWriter();
			out.println("$('botao_pesquisar_parametrizacao').onclick();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Jpf.Action()
	public Forward gravarAtivacaoWiFiPlanos(br.com.vivo.catalogoPRS.pageflows.param.planoServico.parametrizacao.ParametrizacaoPlanoServicoController.AtivaWifiPlanosFormBean form) {
		
		br.com.vivo.sn.catalogoPlano.AlterarAtivacaoWiFiRequestDocument alterarAtivacaoWiFiRequestDocument =
			br.com.vivo.sn.catalogoPlano.AlterarAtivacaoWiFiRequestDocument.Factory.newInstance();
		
		br.com.vivo.sn.catalogoPlano.ParamAlterarAtivacaoWiFiDocument.ParamAlterarAtivacaoWiFi paramAlterarAtivacaoWiFi =
			alterarAtivacaoWiFiRequestDocument.addNewAlterarAtivacaoWiFiRequest().addNewParamAlterarAtivacaoWiFi();
		
		ListaIdPlano listaIdPlano = br.com.vivo.sn.catalogoPlano.ParamAlterarAtivacaoWiFiDocument.ParamAlterarAtivacaoWiFi.ListaIdPlano.Factory.newInstance();
		
		Long[] arrayPlanos = form.getIdsPlanos();
		
		for (Long idPlano : arrayPlanos) {
			listaIdPlano.addIdPlano(idPlano);
		}
		
		if(form.getOpAtivaWiFi() != null && form.getOpAtivaWiFi().trim().length() > 0) {
			paramAlterarAtivacaoWiFi.setStatus(br.com.vivo.sn.catalogoPlano.ParamAlterarAtivacaoWiFiDocument.ParamAlterarAtivacaoWiFi.Status.Enum.forString(form.getOpAtivaWiFi()));
		}
		
		if(listaIdPlano.sizeOfIdPlanoArray() > 0) {
			paramAlterarAtivacaoWiFi.setListaIdPlano(listaIdPlano);
		}
		
		prepareServiceControl(planoSoapServiceControl);
		try {
			 planoSoapServiceControl.alterarAtivacaoWiFi(alterarAtivacaoWiFiRequestDocument);
		} catch (Exception e) {
			e.getStackTrace();
		}
		try {
			HttpServletResponse response = getResponse();
			response.setContentType("text/javascript");
			PrintWriter out = this.getResponse().getWriter();
			out.println("$('botao_pesquisar_parametrizacao').onclick();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	protected void onCreate() {
		plataformaSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.PLATAFORMA));
		grupoServicoSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.GRUPO_SERVICO));
		servicoSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.SERVICO));
		planoSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.PLANO));
		regionalSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.REGIONAL));
		ufSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.UF));
		
		autorizaVazio.add("begin");
	}

	@Override
	protected void onDestroy(HttpSession session) {
	}

	@Jpf.FormBean
	public static class PesquisaPlanosFormBean implements java.io.Serializable {
		private static final long serialVersionUID = 1L;
		
		private Long paginaSolicitada;
		private Long idPlataforma;
		private Long idCategoria;
		private String regionais;
		private String nmServico;
		private String cdServico;
		private String disponibilidade;
		private Boolean indTitular;
		private String tpPesquisa;
		

		public Boolean getIndTitular() {
			return indTitular;
		}

		public void setIndTitular(Boolean indTitular) {
			this.indTitular = indTitular;
		}

		public String getNmServico() {
			return nmServico;
		}
		
		public void setNmServico(String nmServico) {
			this.nmServico = nmServico;
		}
		
		public String getCdServico() {
			return cdServico;
		}

		public void setCdServico(String cdServico) {
			this.cdServico = cdServico;
		}

		public String getDisponibilidade() {
			return disponibilidade;
		}

		public void setDisponibilidade(String disponibilidade) {
			this.disponibilidade = disponibilidade;
		}
		
		public Long getIdPlataforma() {
			return idPlataforma;
		}

		public void setIdPlataforma(Long idPlataforma) {
			this.idPlataforma = idPlataforma;
		}

		public Long getIdCategoria() {
			return idCategoria;
		}

		public void setIdCategoria(Long idCategoria) {
			this.idCategoria = idCategoria;
		}

		public Long getPaginaSolicitada() {
			return paginaSolicitada;
		}

		public void setPaginaSolicitada(Long paginaSolicitada) {
			this.paginaSolicitada = paginaSolicitada;
		}

		public String getTpPesquisa() {
			return tpPesquisa;
		}

		public void setTpPesquisa(String tpPesquisa) {
			this.tpPesquisa = tpPesquisa;
		}

		public String getRegionais() {
			return regionais;
		}

		public void setRegionais(String regionais) {
			this.regionais = regionais;
		}

	}

	@Jpf.FormBean
	public static class PesquisaServicosFormBean implements java.io.Serializable {
		private static final long serialVersionUID = 1L;
		
		private Long paginaSolicitada;
		private Long idPlataforma;
		private Long idCategoria;
		private String nmServico;
		private String cdServico;
		private String disponibilidade;
		private String tpPesquisa;
		private String indTitular;
		private String regionais;
		
		public String getIndTitular() {
			return indTitular;
		}
		public void setIndTitular(String indTitular) {
			this.indTitular = indTitular;
		}
		public String getCdServico() {
			return cdServico;
		}
		public void setCdServico(String cdServico) {
			this.cdServico = cdServico;
		}
		public String getDisponibilidade() {
			return disponibilidade;
		}
		public void setDisponibilidade(String disponibilidade) {
			this.disponibilidade = disponibilidade;
		}
		public Long getIdCategoria() {
			return idCategoria;
		}
		public void setIdCategoria(Long idCategoria) {
			this.idCategoria = idCategoria;
		}
		public Long getIdPlataforma() {
			return idPlataforma;
		}
		public void setIdPlataforma(Long idPlataforma) {
			this.idPlataforma = idPlataforma;
		}
		public String getNmServico() {
			return nmServico;
		}
		public void setNmServico(String nmServico) {
			this.nmServico = nmServico;
		}
		public Long getPaginaSolicitada() {
			return paginaSolicitada;
		}
		public void setPaginaSolicitada(Long paginaSolicitada) {
			this.paginaSolicitada = paginaSolicitada;
		}
		public String getTpPesquisa() {
			return tpPesquisa;
		}
		public void setTpPesquisa(String tpPesquisa) {
			this.tpPesquisa = tpPesquisa;
		}
		public String getRegionais() {
			return regionais;
		}
		public void setRegionais(String regionais) {
			this.regionais = regionais;
		}
	}

	@Jpf.FormBean
	public static class AlterarServicosFormBean implements java.io.Serializable {
		private static final long serialVersionUID = 1L;
		
		private Long idServico;
		private Long idPlataforma;
		private Long idTipoServico;
		private Long idCategoriaCatalogo;
		private Long qtdMinAtivacaoCatalogo;
		private Long qtdMaxAtivacaoCatalogo;
		private Long qtdMinAtivLegado;
		private Long qtdMaxAtivLegado;
		private String cdServico;
		private String nmComercial;
		private String nmCategoriaCatalogo;
		private String nmCategoriaLegado;
		private String nmUsuarioAlteracao;
		private String dscTipoServico;
		private String indTitular;
		private String inDisponibilidadeCatalogo;
		private String inDisponibilidadeLegado;
		private Date dtAlteracao;
		private String opAtivaWiFi;
		
		public String getOpAtivaWiFi() {
			return opAtivaWiFi;
		}
		public void setOpAtivaWiFi(String opAtivaWiFi) {
			this.opAtivaWiFi = opAtivaWiFi;
		}
		public Long getIdServico() {
			return idServico;
		}
		public void setIdServico(Long idServico) {
			this.idServico = idServico;
		}
		public Long getQtdMaxAtivacaoCatalogo() {
			return qtdMaxAtivacaoCatalogo;
		}
		public void setQtdMaxAtivacaoCatalogo(Long qtdMaxAtivacaoCatalogo) {
			this.qtdMaxAtivacaoCatalogo = qtdMaxAtivacaoCatalogo;
		}
		public Long getQtdMinAtivacaoCatalogo() {
			return qtdMinAtivacaoCatalogo;
		}
		public void setQtdMinAtivacaoCatalogo(Long qtdMinAtivacaoCatalogo) {
			this.qtdMinAtivacaoCatalogo = qtdMinAtivacaoCatalogo;
		}
		public Long getQtdMaxAtivLegado() {
			return qtdMaxAtivLegado;
		}
		public void setQtdMaxAtivLegado(Long qtdMaxAtivLegado) {
			this.qtdMaxAtivLegado = qtdMaxAtivLegado;
		}
		public Long getQtdMinAtivLegado() {
			return qtdMinAtivLegado;
		}
		public void setQtdMinAtivLegado(Long qtdMinAtivLegado) {
			this.qtdMinAtivLegado = qtdMinAtivLegado;
		}
		public String getCdServico() {
			return cdServico;
		}
		public void setCdServico(String cdServico) {
			this.cdServico = cdServico;
		}
		public Date getDtAlteracao() {
			return dtAlteracao;
		}
		public void setDtAlteracao(Date dtAlteracao) {
			this.dtAlteracao = dtAlteracao;
		}
		public String getNmUsuarioAlteracao() {
			return nmUsuarioAlteracao;
		}
		public void setNmUsuarioAlteracao(String nmUsuarioAlteracao) {
			this.nmUsuarioAlteracao = nmUsuarioAlteracao;
		}
		public String getNmCategoriaCatalogo() {
			return nmCategoriaCatalogo;
		}
		public void setNmCategoriaCatalogo(String nmCategoriaCatalogo) {
			this.nmCategoriaCatalogo = nmCategoriaCatalogo;
		}
		public String getNmCategoriaLegado() {
			return nmCategoriaLegado;
		}
		public void setNmCategoriaLegado(String nmCategoriaLegado) {
			this.nmCategoriaLegado = nmCategoriaLegado;
		}
		public String getNmComercial() {
			return nmComercial;
		}
		public void setNmComercial(String nmComercial) {
			this.nmComercial = nmComercial;
		}
		public String getDscTipoServico() {
			return dscTipoServico;
		}
		public void setDscTipoServico(String dscTipoServico) {
			this.dscTipoServico = dscTipoServico;
		}
		public Long getIdTipoServico() {
			return idTipoServico;
		}
		public void setIdTipoServico(Long idTipoServico) {
			this.idTipoServico = idTipoServico;
		}
		public Long getIdCategoriaCatalogo() {
			return idCategoriaCatalogo;
		}
		public void setIdCategoriaCatalogo(Long idCategoriaCatalogo) {
			this.idCategoriaCatalogo = idCategoriaCatalogo;
		}
		public Long getIdPlataforma() {
			return idPlataforma;
		}
		public void setIdPlataforma(Long idPlataforma) {
			this.idPlataforma = idPlataforma;
		}
		public String getIndTitular() {
			return indTitular;
		}
		public void setIndTitular(String indTitular) {
			this.indTitular = indTitular;
		}
		public String getInDisponibilidadeCatalogo() {
			return inDisponibilidadeCatalogo;
		}
		public void setInDisponibilidadeCatalogo(String inDisponibilidadeCatalogo) {
			this.inDisponibilidadeCatalogo = inDisponibilidadeCatalogo;
		}
		public String getInDisponibilidadeLegado() {
			return inDisponibilidadeLegado;
		}
		public void setInDisponibilidadeLegado(String inDisponibilidadeLegado) {
			this.inDisponibilidadeLegado = inDisponibilidadeLegado;
		}
	}

	@Jpf.FormBean
	public static class AssociarCategoriaAtivaWifiFormBean implements java.io.Serializable {
		private static final long serialVersionUID = 1L;
		
		private Long idSistema;
		private Long idCategoria;
		private Long idPlataforma;
		private Long[] idsServicos;
		private String indisponivel;
		private String nmCategoria;
		private String opAtivaWiFi;

		public String getOpAtivaWiFi() {
			return opAtivaWiFi;
		}
		public void setOpAtivaWiFi(String opAtivaWiFi) {
			this.opAtivaWiFi = opAtivaWiFi;
		}
		public String getNmCategoria() {
			return nmCategoria;
		}
		public void setNmCategoria(String nmCategoria) {
			this.nmCategoria = nmCategoria;
		}

		public String getIndisponivel() {
			return indisponivel;
		}
		public void setIndisponivel(String indisponivel) {
			this.indisponivel = indisponivel;
		}
		
		public Long getIdCategoria() {
			return idCategoria;
		}
		public void setIdCategoria(Long idCategoria) {
			this.idCategoria = idCategoria;
		}
		public Long getIdPlataforma() {
			return idPlataforma;
		}
		public void setIdPlataforma(Long idPlataforma) {
			this.idPlataforma = idPlataforma;
		}
		public Long getIdSistema() {
			return idSistema;
		}
		public void setIdSistema(Long idSistema) {
			this.idSistema = idSistema;
		}
		public Long[] getIdsServicos() {
			return idsServicos;
		}
		public void setIdsServicos(Long[] idsServicos) {
			this.idsServicos = idsServicos;
		}
				
	}

	@Jpf.FormBean
	public static class AlterarCategoriaFormBean implements java.io.Serializable {
		private static final long serialVersionUID = 1L;
		
		private Long idCategoria;
		private String nmCategoria;
		private String indisponivel;
		
		public Long getIdCategoria() {
			return idCategoria;
		}
		public void setIdCategoria(Long idCategoria) {
			this.idCategoria = idCategoria;
		}
		
		public String getIndisponivel() {
			return indisponivel;
		}
		public void setIndisponivel(String indisponivel) {
			this.indisponivel = indisponivel;
		}
		
		public String getNmCategoria() {
			return nmCategoria;
		}
		public void setNmCategoria(String nmCategoria) {
			this.nmCategoria = nmCategoria == null ? nmCategoria : nmCategoria.toUpperCase();
		}
	}

	@Jpf.FormBean
	public static class ApagarCategoriaFormBean implements java.io.Serializable {
		private static final long serialVersionUID = 1L;
		
		private Long idCategoria;

		public Long getIdCategoria() {
			return idCategoria;
		}
		public void setIdCategoria(Long idCategoria) {
			this.idCategoria = idCategoria;
		}
	}

	@Jpf.FormBean
	public static class ValidarServicosFormBean implements java.io.Serializable {
		private static final long serialVersionUID = 1L;
		
		private Long[] idsServico;
		private String nmComercial;
		private double qtdeMinAtivacaoCatalogo;
		private double qtdeMaxAtivacaoCatalogo;
		
		public Long[] getIdsServico() {
			return idsServico;
		}
		public void setIdsServico(Long[] idsServico) {
			this.idsServico = idsServico;
		}
		public String getNmComercial() {
			return nmComercial;
		}
		public void setNmComercial(String nmComercial) {
			this.nmComercial = nmComercial;
		}
		
		public double getQtdeMaxAtivacaoCatalogo() {
			return qtdeMaxAtivacaoCatalogo;
		}
		public void setQtdeMaxAtivacaoCatalogo(double qtdeMaxAtivacaoCatalogo) {
			this.qtdeMaxAtivacaoCatalogo = qtdeMaxAtivacaoCatalogo;
		}
		
		public double getQtdeMinAtivacaoCatalogo() {
			return qtdeMinAtivacaoCatalogo;
		}
		public void setQtdeMinAtivacaoCatalogo(double qtdeMinAtivacaoCatalogo) {
			this.qtdeMinAtivacaoCatalogo = qtdeMinAtivacaoCatalogo;
		}
	}

	@Jpf.FormBean
	public static class ValidarPlanosFormBean implements java.io.Serializable {
		private static final long serialVersionUID = 1L;
		
		private Long[] idPlano;
		private String nmComercial;
		private double qtMaximaDependentes;
		
		public Long[] getIdPlano() {
			return idPlano;
		}
		public void setIdPlano(Long[] idPlano) {
			this.idPlano = idPlano;
		}
		public String getNmComercial() {
			return nmComercial;
		}
		public void setNmComercial(String nmComercial) {
			this.nmComercial = nmComercial;
		}
		
		public double getQtMaximaDependentes() {
			return qtMaximaDependentes;
		}
		public void setQtMaximaDependentes(double qtMaximaDependentes) {
			this.qtMaximaDependentes = qtMaximaDependentes;
		}
	}

	@Jpf.FormBean
	public static class AlterarPlanosFormBean implements java.io.Serializable {
		private static final long serialVersionUID = 1L;
		
		private Long idPlano;
		private Long idPlataforma;
		private Long idTpPlano;
		private String cdCodigo;
		private String nmComercial;
		private String indTitDep;
		private String nmUsuarioAlteracao;
		private String inDisponibilidadeCatalogo;
		private String inDisponibilidadeLegado;
		private Long qtdMaxDependenteCatalogo;
		private Long qtdMaxDependenteLegado;
		private Date dtUltimaAlteracao;
		private String opAtivaWiFi;
		
		public String getOpAtivaWiFi() {
			return opAtivaWiFi;
		}
		public void setOpAtivaWiFi(String opAtivaWiFi) {
			this.opAtivaWiFi = opAtivaWiFi;
		}
		public String getCdCodigo() {
			return cdCodigo;
		}
		public void setCdCodigo(String cdCodigo) {
			this.cdCodigo = cdCodigo;
		}
		public Date getDtUltimaAlteracao() {
			return dtUltimaAlteracao;
		}
		public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
			this.dtUltimaAlteracao = dtUltimaAlteracao;
		}
		public Long getIdPlano() {
			return idPlano;
		}
		public void setIdPlano(Long idPlano) {
			this.idPlano = idPlano;
		}
		public String getIndTitDep() {
			return indTitDep;
		}
		public void setIndTitDep(String indTitDep) {
			this.indTitDep = indTitDep;
		}
		public String getNmComercial() {
			return nmComercial;
		}
		public void setNmComercial(String nmComercial) {
			this.nmComercial = nmComercial;
		}
		public String getNmUsuarioAlteracao() {
			return nmUsuarioAlteracao;
		}
		public void setNmUsuarioAlteracao(String nmUsuarioAlteracao) {
			this.nmUsuarioAlteracao = nmUsuarioAlteracao;
		}
		public Long getQtdMaxDependenteCatalogo() {
			return qtdMaxDependenteCatalogo;
		}
		public void setQtdMaxDependenteCatalogo(Long qtdMaxDependenteCatalogo) {
			this.qtdMaxDependenteCatalogo = qtdMaxDependenteCatalogo;
		}
		public Long getQtdMaxDependenteLegado() {
			return qtdMaxDependenteLegado;
		}
		public void setQtdMaxDependenteLegado(Long qtdMaxDependenteLegado) {
			this.qtdMaxDependenteLegado = qtdMaxDependenteLegado;
		}
		public Long getIdPlataforma() {
			return idPlataforma;
		}
		public void setIdPlataforma(Long idPlataforma) {
			this.idPlataforma = idPlataforma;
		}
		public Long getIdTpPlano() {
			return idTpPlano;
		}
		public void setIdTpPlano(Long idTpPlano) {
			this.idTpPlano = idTpPlano;
		}
		public String getInDisponibilidadeCatalogo() {
			return inDisponibilidadeCatalogo;
		}
		public void setInDisponibilidadeCatalogo(String inDisponibilidadeCatalogo) {
			this.inDisponibilidadeCatalogo = inDisponibilidadeCatalogo;
		}
		public String getInDisponibilidadeLegado() {
			return inDisponibilidadeLegado;
		}
		public void setInDisponibilidadeLegado(String inDisponibilidadeLegado) {
			this.inDisponibilidadeLegado = inDisponibilidadeLegado;
		}
	}

	@Jpf.FormBean
	public static class AtivaWifiPlanosFormBean implements java.io.Serializable {
		private static final long serialVersionUID = 1L;

		private Long[] idsPlanos;
		private String opAtivaWiFi;
		
		public Long[] getIdsPlanos() {
			return idsPlanos;
		}
		public void setIdsPlanos(Long[] idsPlanos) {
			this.idsPlanos = idsPlanos;
		}
		
		public String getOpAtivaWiFi() {
			return opAtivaWiFi;
		}
		public void setOpAtivaWiFi(String opAtivaWiFi) {
			this.opAtivaWiFi = opAtivaWiFi;
		}

	}*/

}