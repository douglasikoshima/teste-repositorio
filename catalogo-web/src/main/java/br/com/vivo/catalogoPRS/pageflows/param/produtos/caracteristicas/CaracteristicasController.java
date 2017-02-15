package br.com.vivo.catalogoPRS.pageflows.param.produtos.caracteristicas;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*import weblogic.utils.StringUtils;*/
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.services.CaracteristicaService;
import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;
import br.com.vivo.catalogoPRS.util.WebServicesConfiguration;
/*import br.com.vivo.sn.catalogoCaracteristica.AlterarCaracteristicaRequestDocument;
import br.com.vivo.sn.catalogoCaracteristica.AlterarValorCaracteristicaRequestDocument;
import br.com.vivo.sn.catalogoCaracteristica.BuscarCaracteristicaPorIdRequestDocument;
import br.com.vivo.sn.catalogoCaracteristica.BuscarCaracteristicaPorIdResponseDocument;
import br.com.vivo.sn.catalogoCaracteristica.BuscarListaCaracteristicaRequestDocument;
import br.com.vivo.sn.catalogoCaracteristica.BuscarListaCaracteristicaResponseDocument;
import br.com.vivo.sn.catalogoCaracteristica.BuscarListaModeloPorCaracteristicaRequestDocument;
import br.com.vivo.sn.catalogoCaracteristica.BuscarListaModeloPorCaracteristicaResponseDocument;
import br.com.vivo.sn.catalogoCaracteristica.BuscarListaValorCaracteristicaRequestDocument;
import br.com.vivo.sn.catalogoCaracteristica.BuscarListaValorCaracteristicaResponseDocument;
import br.com.vivo.sn.catalogoCaracteristica.BuscarModelosPorValorCaracteristicaRequestDocument;
import br.com.vivo.sn.catalogoCaracteristica.BuscarModelosPorValorCaracteristicaResponseDocument;
import br.com.vivo.sn.catalogoCaracteristica.BuscarValorCaracteristicaPorIdRequestDocument;
import br.com.vivo.sn.catalogoCaracteristica.BuscarValorCaracteristicaPorIdResponseDocument;
import br.com.vivo.sn.catalogoCaracteristica.CriarCaracteristicaRequestDocument;
import br.com.vivo.sn.catalogoCaracteristica.CriarValorCaracteristicaRequestDocument;
import br.com.vivo.sn.catalogoCaracteristica.DisponibilizarCaracteristicaRequestDocument;
import br.com.vivo.sn.catalogoCaracteristica.ExcluirListaCaracteristicaRequestDocument;
import br.com.vivo.sn.catalogoCaracteristica.ExcluirListaValorCaracteristicaRequestDocument;
import br.com.vivo.sn.catalogoCaracteristica.ParametrosAlterarCaracteristicaDocument.ParametrosAlterarCaracteristica.ParametrosCaracteristicaAlteracao;
import br.com.vivo.sn.catalogoCaracteristica.ParametrosAlterarValorCaracteristicaDocument.ParametrosAlterarValorCaracteristica.ParametrosAlterarValorCaracteristica2;
import br.com.vivo.sn.catalogoCaracteristica.ParametrosBuscarCaracteristicaPorIdDocument.ParametrosBuscarCaracteristicaPorId.RaizCaracteristicaPorIdIn;
import br.com.vivo.sn.catalogoCaracteristica.ParametrosBuscarListaCaracteristicaDocument.ParametrosBuscarListaCaracteristica.RaizCaracteristicaIn;
import br.com.vivo.sn.catalogoCaracteristica.ParametrosBuscarListaModeloPorCaracteristicaDocument.ParametrosBuscarListaModeloPorCaracteristica.ParametrosModelosPorCaracteristicaIn;
import br.com.vivo.sn.catalogoCaracteristica.ParametrosBuscarListaModeloPorValorCaracteristicaDocument.ParametrosBuscarListaModeloPorValorCaracteristica.ParametrosModelosPorValorCaracteristicaIn;
import br.com.vivo.sn.catalogoCaracteristica.ParametrosBuscarListaValorCaracteristicaDocument.ParametrosBuscarListaValorCaracteristica.RaizValorCaracteristicaIn;
import br.com.vivo.sn.catalogoCaracteristica.ParametrosBuscarValorCaracteristicaPorIdDocument.ParametrosBuscarValorCaracteristicaPorId.ParametrosValorCaracteristicaPorIdIn;
import br.com.vivo.sn.catalogoCaracteristica.ParametrosCriarCaracteristicaDocument.ParametrosCriarCaracteristica.CaracteristicaCriacao;
import br.com.vivo.sn.catalogoCaracteristica.ParametrosCriarValorCaracteristicaDocument.ParametrosCriarValorCaracteristica.ValorCaracteristicaCriacao;
import br.com.vivo.sn.catalogoCaracteristica.ParametrosDisponibilizarCaracteristicaDocument.ParametrosDisponibilizarCaracteristica.ParametrosDispCaracteristica;
import br.com.vivo.sn.catalogoCaracteristica.ParametrosExcluirListaCaracteristicaDocument.ParametrosExcluirListaCaracteristica.ListaCaracteristicaExclusao;
import br.com.vivo.sn.catalogoCaracteristica.ParametrosExcluirListaCaracteristicaDocument.ParametrosExcluirListaCaracteristica.ListaCaracteristicaExclusao.CaracteristicaExclusao;
import br.com.vivo.sn.catalogoCaracteristica.ParametrosExcluirListaValorCaracteristicaDocument.ParametrosExcluirListaValorCaracteristica.ListaValorCaracteristicaExclusao;
import br.com.vivo.sn.catalogoCaracteristica.ParametrosExcluirListaValorCaracteristicaDocument.ParametrosExcluirListaValorCaracteristica.ListaValorCaracteristicaExclusao.ValorCaracteristicaExclusao;
import br.com.vivo.sn.catalogoCaracteristica.ResultadoBuscarListaModeloPorCaracteristicaDocument.ResultadoBuscarListaModeloPorCaracteristica;
import br.com.vivo.sn.catalogoCaracteristica.ResultadoBuscarListaModeloPorCaracteristicaDocument.ResultadoBuscarListaModeloPorCaracteristica.ModeloPorCaracteristica;
import br.com.vivo.sn.catalogoCaracteristica.ResultadoBuscarListaModeloPorValorCaracteristicaDocument.ResultadoBuscarListaModeloPorValorCaracteristica;
import br.com.vivo.sn.catalogoCaracteristica.ResultadoBuscarListaModeloPorValorCaracteristicaDocument.ResultadoBuscarListaModeloPorValorCaracteristica.ModeloPorValorCaracteristica;
import br.com.vivo.sn.catalogoCaracteristica.ResultadoBuscarValorCaracteristicaPorIdDocument.ResultadoBuscarValorCaracteristicaPorId;
import br.com.vivo.sn.catalogoCaracteristica.ResultadoCaracteristicaDocument.ResultadoCaracteristica;
import br.com.vivo.sn.catalogoGeral.PaginacaoInDocument.PaginacaoIn;
import br.com.vivo.sn.catalogoGeral.PaginacaoOutDocument.PaginacaoOut;

import com.bea.control.ServiceControlException;
*/
public class CaracteristicasController extends BaseMappingDispatchAction {
	private static final long serialVersionUID = 1L;

/*	@Control
	private CaracteristicaSoapServiceControl caracteristicaSoapServiceControl;

	@Jpf.Action(forwards = { @Jpf.Forward(name = "default", path = "Caracteristicas.jsp") })
	public Forward begin() {
		buscarCaracteristicas("", 1);
		return new Forward("default");
	}

	private void buscarCaracteristicas(String nomeCaracteristica, int paginaSolicitada) {
		BuscarListaCaracteristicaRequestDocument param = BuscarListaCaracteristicaRequestDocument.Factory.newInstance();
		RaizCaracteristicaIn in = param.addNewBuscarListaCaracteristicaRequest().addNewParametrosBuscarListaCaracteristica().addNewRaizCaracteristicaIn();
		in.setNmCaracteristica(nomeCaracteristica);

		PaginacaoIn paramPaginacao = in.addNewPaginacaoIn();
		int elementosPorPagina = ApplicationConfiguration.getElementosPorPagina();
		paramPaginacao.setItensPorPagina(elementosPorPagina);
		paramPaginacao.setPaginaSolicitada(paginaSolicitada);
		
		prepareServiceControl(caracteristicaSoapServiceControl);
		BuscarListaCaracteristicaResponseDocument buscarListaCaracteristicaResponseDocument = caracteristicaSoapServiceControl.buscarListaCaracteristica(param);

		ResultadoCaracteristica resultadoCaracteristica = buscarListaCaracteristicaResponseDocument.getBuscarListaCaracteristicaResponse().getResultadoCaracteristica();
		br.com.vivo.sn.catalogoCaracteristica.ListaCaracteristicaDocument.ListaCaracteristica listaCaracteristica = resultadoCaracteristica.getListaCaracteristica();
		List<br.com.vivo.sn.catalogoCaracteristica.ListaCaracteristicaDocument.ListaCaracteristica.Caracteristica> vlcaracteristicaList;
		if (listaCaracteristica != null)
			vlcaracteristicaList = listaCaracteristica.getCaracteristicaList();
		else
			vlcaracteristicaList = new ArrayList<br.com.vivo.sn.catalogoCaracteristica.ListaCaracteristicaDocument.ListaCaracteristica.Caracteristica>();

		PaginacaoOut paginacaoOut = resultadoCaracteristica.getPaginacaoOut();
		int paginaAtual = paginacaoOut.getPaginaAtual();
		int totalRegistros = paginacaoOut.getTotalRegistros();
		long totalPagina = Math.round((totalRegistros / elementosPorPagina));
		if (((totalRegistros / (double) elementosPorPagina) - totalPagina) > 0)
			totalPagina++;

		HttpServletRequest request = getRequest();
		request.setAttribute("caracteristicas", vlcaracteristicaList);
		request.setAttribute("size_caracteristicas", paginacaoOut.getTotalRegistros());
		request.setAttribute("paginaAtual", paginaAtual);
		request.setAttribute("totalPagina", totalPagina);
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "fail", navigateTo = Jpf.NavigateTo.previousAction) }, validationErrorForward = @Jpf.Forward(name = "fail", navigateTo = Jpf.NavigateTo.previousAction))
	public Forward apagarCaracteristica(br.com.vivo.catalogoPRS.pageflows.shared.form.ApagarFormBean form) {

		Long idCaracteristica = form.getIdEntidade();

		ExcluirListaCaracteristicaRequestDocument paramDeleteListaCaracteristicaExclusao = ExcluirListaCaracteristicaRequestDocument.Factory.newInstance();
		ListaCaracteristicaExclusao listaCaracteristicaExclusao = paramDeleteListaCaracteristicaExclusao.addNewExcluirListaCaracteristicaRequest().addNewParametrosExcluirListaCaracteristica().addNewListaCaracteristicaExclusao();
		CaracteristicaExclusao caracteristicaExclusao = listaCaracteristicaExclusao.addNewCaracteristicaExclusao();
		caracteristicaExclusao.setIdCaracteristica(idCaracteristica);
		if(form.getJustificativa() != null && !form.getJustificativa().trim().equalsIgnoreCase(""))
			caracteristicaExclusao.setJustificativa(form.getJustificativa());

		prepareServiceControl(caracteristicaSoapServiceControl);
		caracteristicaSoapServiceControl.excluirListaCaracteristica(paramDeleteListaCaracteristicaExclusao);
		try {
			HttpServletResponse response = getResponse();
			response.setContentType("text/javascript");
			PrintWriter out = this.getResponse().getWriter();
			out.println("$('botao_pesquisar').click();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "ApagarCaracteristica.jsp") })
	public Forward popupApagarCaracteristica() {

		HttpServletRequest request = getRequest();

		String idCaracteristicaString = (String) request.getParameter("id_caracteristica");
		request.setAttribute("id_caracteristica", idCaracteristicaString);

		if (idCaracteristicaString != null && !idCaracteristicaString.trim().equals("")) {
			Long idCaracteristica = Long.valueOf(idCaracteristicaString);
			BuscarListaModeloPorCaracteristicaRequestDocument paramBuscarModelosPorCaracteristica = BuscarListaModeloPorCaracteristicaRequestDocument.Factory.newInstance();
			ParametrosModelosPorCaracteristicaIn parametrosModelosPorCaracteristicaIn = paramBuscarModelosPorCaracteristica
					.addNewBuscarListaModeloPorCaracteristicaRequest().addNewParametrosBuscarListaModeloPorCaracteristica().addNewParametrosModelosPorCaracteristicaIn();
			parametrosModelosPorCaracteristicaIn.setIdCaracteristica(idCaracteristica);

			try {
				prepareServiceControl(caracteristicaSoapServiceControl);
				BuscarListaModeloPorCaracteristicaResponseDocument buscarListaModeloPorCaracteristicaResponseDocument = caracteristicaSoapServiceControl.buscarListaModeloPorCaracteristica(paramBuscarModelosPorCaracteristica);
				ResultadoBuscarListaModeloPorCaracteristica resultadoBuscarListaModeloPorCaracteristica = buscarListaModeloPorCaracteristicaResponseDocument.getBuscarListaModeloPorCaracteristicaResponse().getResultadoBuscarListaModeloPorCaracteristica();
				List<ModeloPorCaracteristica> modeloPorCaracteristicaList;
				if (resultadoBuscarListaModeloPorCaracteristica != null) {
					modeloPorCaracteristicaList = resultadoBuscarListaModeloPorCaracteristica.getModeloPorCaracteristicaList();
				} else {
					modeloPorCaracteristicaList = new ArrayList<ModeloPorCaracteristica>();
				}
				request.setAttribute("modelos_afetados", modeloPorCaracteristicaList);
			} catch (ServiceControlException e) {
				request.setAttribute("modelos_afetados", new ArrayList<ModeloPorCaracteristica>());
			}
		}

		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(validationErrorForward = @Jpf.Forward(name = "fail", navigateTo = Jpf.NavigateTo.previousAction))
	public Forward addCaracteristica(br.com.vivo.catalogoPRS.pageflows.param.produtos.caracteristicas.CaracteristicasController.NovaCaracteristicaFormBean form) {

		CriarCaracteristicaRequestDocument paramCreateCaracteristicaCriacao = CriarCaracteristicaRequestDocument.Factory.newInstance();
		CaracteristicaCriacao caracteristicaCriacao = paramCreateCaracteristicaCriacao.addNewCriarCaracteristicaRequest().addNewParametrosCriarCaracteristica().addNewCaracteristicaCriacao();
		caracteristicaCriacao.setNmCaracteristica(form.getNomeCaracteristica());
		caracteristicaCriacao.setDescricao(form.getDescricaoCaracteristica());
		caracteristicaCriacao.setIndisponivel("N");
		caracteristicaCriacao.setInSimulador("N");

		prepareServiceControl(caracteristicaSoapServiceControl);
		caracteristicaSoapServiceControl.criarCaracteristica(paramCreateCaracteristicaCriacao);
		buscarCaracteristicas("", 1);

		try {
			HttpServletResponse response = getResponse();
			response.setContentType("text/javascript");
			PrintWriter out = this.getResponse().getWriter();
			out.println("$('botao_pesquisar').click();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupValoresCaracteristica.jsp") })
	public Forward ValoresCaracteristica() {

		HttpServletRequest request = getRequest();
		
		BuscarListaValorCaracteristicaRequestDocument paramListarValorCaracteristica = BuscarListaValorCaracteristicaRequestDocument.Factory.newInstance();
		RaizValorCaracteristicaIn raizValorCaracteristicaIn = paramListarValorCaracteristica.addNewBuscarListaValorCaracteristicaRequest().addNewParametrosBuscarListaValorCaracteristica().addNewRaizValorCaracteristicaIn();
		PaginacaoIn paginacaoIn = raizValorCaracteristicaIn.addNewPaginacaoIn();
		Integer paginaSolicitada;
		if (getRequest().getParameter("pagina_solicitada") != null && !getRequest().getParameter("pagina_solicitada").trim().equalsIgnoreCase(""))
			paginaSolicitada = Integer.valueOf(getRequest().getParameter("pagina_solicitada"));
		else
			paginaSolicitada = 1;
		paginacaoIn.setPaginaSolicitada(paginaSolicitada);
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPaginaNosPopups());
		String idCaracteristicaString = getRequest().getParameter("id_caracteristica");
		Long idCaracteristica = Long.valueOf(-1);
		if (!StringUtils.isEmptyString(idCaracteristicaString)) {
			idCaracteristica = Long.parseLong(idCaracteristicaString);
		}
		raizValorCaracteristicaIn.setIdCaracteristica(idCaracteristica);
		
		prepareServiceControl(caracteristicaSoapServiceControl);
		BuscarListaValorCaracteristicaResponseDocument listaValorCaracteristicaResponseDocument = CaracteristicaService.getInstance().buscarListaValorCaracteristica(caracteristicaSoapServiceControl, paramListarValorCaracteristica, true);
		if (listaValorCaracteristicaResponseDocument != null) {
			br.com.vivo.sn.catalogoCaracteristica.ResultadoBuscarListaValorCaracteristicaDocument.ResultadoBuscarListaValorCaracteristica.ListaValorCaracteristica listaValorCaracteristica = listaValorCaracteristicaResponseDocument
					.getBuscarListaValorCaracteristicaResponse().getResultadoBuscarListaValorCaracteristica().getListaValorCaracteristica();
			List<br.com.vivo.sn.catalogoCaracteristica.ResultadoBuscarListaValorCaracteristicaDocument.ResultadoBuscarListaValorCaracteristica.ListaValorCaracteristica.ValorCaracteristica> valorCaracteristicaList;
			if (listaValorCaracteristica != null) {
				valorCaracteristicaList = listaValorCaracteristica.getValorCaracteristicaList();
			} else {
				valorCaracteristicaList = new ArrayList<br.com.vivo.sn.catalogoCaracteristica.ResultadoBuscarListaValorCaracteristicaDocument.ResultadoBuscarListaValorCaracteristica.ListaValorCaracteristica.ValorCaracteristica>();
			}

			PaginacaoOut paginacaoOut = listaValorCaracteristicaResponseDocument.getBuscarListaValorCaracteristicaResponse()
					.getResultadoBuscarListaValorCaracteristica().getPaginacaoOut();

			int elementosPorPagina = ApplicationConfiguration.getElementosPorPaginaNosPopups();
			int paginaAtual = paginacaoOut.getPaginaAtual();
			int totalRegistros = paginacaoOut.getTotalRegistros();
			long totalPagina = Math.round((totalRegistros / elementosPorPagina));
			if (((totalRegistros / (double) elementosPorPagina) - totalPagina) > 0)
				totalPagina++;
			if (totalPagina == 0)
				totalPagina++;

			

			request.setAttribute("valoresCaracteristica", valorCaracteristicaList);
			request.setAttribute("size_valoresCaracteristica", valorCaracteristicaList.size());
			request.setAttribute("totalPagina", totalPagina);
			request.setAttribute("paginaAtual", paginaAtual);
		}else{
			request.setAttribute("size_valoresCaracteristica", 0);
			request.setAttribute("totalPagina", 1);
			request.setAttribute("paginaAtual", 1);
		}
		
		request.setAttribute("id_caracteristica", idCaracteristicaString);
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "fail", navigateTo = Jpf.NavigateTo.previousAction) }, validationErrorForward = @Jpf.Forward(name = "fail", navigateTo = Jpf.NavigateTo.previousAction))
	public Forward apagarValorCaracteristica(
			br.com.vivo.catalogoPRS.pageflows.param.produtos.caracteristicas.CaracteristicasController.ApagarValorCaracteristicaFormBean form) {

		getRequest().setAttribute("id_caracteristica", form.getIdCaracteristica());

		Long idValorCaracteristica = form.getIdEntidade();
		ExcluirListaValorCaracteristicaRequestDocument paramExcluirListaValorCaracteristica = ExcluirListaValorCaracteristicaRequestDocument.Factory.newInstance();
		ListaValorCaracteristicaExclusao listaValorCaracteristicaExclusao = paramExcluirListaValorCaracteristica.addNewExcluirListaValorCaracteristicaRequest().addNewParametrosExcluirListaValorCaracteristica().addNewListaValorCaracteristicaExclusao();
		ValorCaracteristicaExclusao valorCaracteristicaExclusao = listaValorCaracteristicaExclusao.addNewValorCaracteristicaExclusao();
		valorCaracteristicaExclusao.setIdValorCaracteristica(idValorCaracteristica);
		if(form.getJustificativa() != null && !form.getJustificativa().trim().equalsIgnoreCase(""))
			valorCaracteristicaExclusao.setJustificativa(form.getJustificativa());

		prepareServiceControl(caracteristicaSoapServiceControl);
		caracteristicaSoapServiceControl.excluirListaValorCaracteristica(paramExcluirListaValorCaracteristica);

		try {
			HttpServletResponse response = getResponse();
			response.setContentType("text/javascript");
			PrintWriter out = this.getResponse().getWriter();
			out.println("$('pagina_valor_caracteristica_selecionada').onclick();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

	@Jpf.Action()
	public Forward alterarValorCaracteristica(
			br.com.vivo.catalogoPRS.pageflows.param.produtos.caracteristicas.CaracteristicasController.AlterarValorCaracteristicaFormBean form)
			throws IOException {
		AlterarValorCaracteristicaRequestDocument parametrosAlterarValorCaracteristica = AlterarValorCaracteristicaRequestDocument.Factory.newInstance();
		ParametrosAlterarValorCaracteristica2 parametrosAlterarValorCaracteristica2 = parametrosAlterarValorCaracteristica.addNewAlterarValorCaracteristicaRequest().addNewParametrosAlterarValorCaracteristica().addNewParametrosAlterarValorCaracteristica();
		parametrosAlterarValorCaracteristica2.setIdValorCaracteristica(form.getIdValorCaracteristica());
		parametrosAlterarValorCaracteristica2.setValor(form.getValorCaracteristica());
		parametrosAlterarValorCaracteristica2.setIdCaracteristica(form.getIdCaracteristica());
		if(form.getJustificativa()!=null && !form.getJustificativa().trim().equalsIgnoreCase(""))
			parametrosAlterarValorCaracteristica2.setJustificativa(form.getJustificativa());

		prepareServiceControl(caracteristicaSoapServiceControl);
		caracteristicaSoapServiceControl.alterarValorCaracteristica(parametrosAlterarValorCaracteristica);

		writeJSOutput("$('pagina_valor_caracteristica_selecionada').onclick();");

		return null;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "Caracteristicas.jsp") })
	public Forward pesquisarCaracteristicas(br.com.vivo.catalogoPRS.pageflows.param.produtos.caracteristicas.CaracteristicasController.PesquisarFormBean form) {
		int paginaSolicitada = 1;
		if (!form.getPaginaSolicitada().equals("")) {
			paginaSolicitada = Integer.parseInt(form.getPaginaSolicitada());
		}
		buscarCaracteristicas(form.getNomeCaracteristica(), paginaSolicitada);
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "AlterarCaracteristica.jsp"),
			@Jpf.Forward(name = "caracteristicaDesconhecida", navigateTo = Jpf.NavigateTo.previousAction) })
	public Forward carregarAlterarCaracteristica() {
		HttpServletRequest request = getRequest();
		String idCaracteristicaString = request.getParameter("id_caracteristica");
		if (idCaracteristicaString != null && !idCaracteristicaString.trim().equals("")) {
			Long idCaracteristica = Long.valueOf(idCaracteristicaString);
			BuscarCaracteristicaPorIdRequestDocument paramBuscarCaracteristicaPorId = BuscarCaracteristicaPorIdRequestDocument.Factory.newInstance();
			RaizCaracteristicaPorIdIn raizCaracteristicaPorIdIn = paramBuscarCaracteristicaPorId.addNewBuscarCaracteristicaPorIdRequest().addNewParametrosBuscarCaracteristicaPorId().addNewRaizCaracteristicaPorIdIn();
			raizCaracteristicaPorIdIn.setIdCaracteristica(idCaracteristica);

			prepareServiceControl(caracteristicaSoapServiceControl);
			BuscarCaracteristicaPorIdResponseDocument buscarCaracteristicaPorIdResponseDocument = caracteristicaSoapServiceControl.buscarCaracteristicaPorId(paramBuscarCaracteristicaPorId);
			br.com.vivo.sn.catalogoCaracteristica.ResultadoBuscarCaracteristicaPorIdDocument.ResultadoBuscarCaracteristicaPorId.Caracteristica caracteristica = buscarCaracteristicaPorIdResponseDocument.getBuscarCaracteristicaPorIdResponse().getResultadoBuscarCaracteristicaPorId().getCaracteristica();
			if (caracteristica != null) {
				request.setAttribute("id_caracteristica", caracteristica.getIdCaracteristica());
				request.setAttribute("nome_caracteristica", caracteristica.getNmCaracteristica());
				request.setAttribute("descricao_caracteristica", caracteristica.getDescricao());
				Forward forward = new Forward("success");
				return forward;
			}
		}
		Forward forward = new Forward("caracteristicaDesconhecida");
		return forward;

	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "Caracteristicas.jsp") }, validationErrorForward = @Jpf.Forward(name = "fail", navigateTo = Jpf.NavigateTo.previousAction))
	public Forward alterarCaracteristica(
			br.com.vivo.catalogoPRS.pageflows.param.produtos.caracteristicas.CaracteristicasController.AlterarCaracteristicaFormBean form) {

		AlterarCaracteristicaRequestDocument paramAlterarCaracteristica = AlterarCaracteristicaRequestDocument.Factory.newInstance();
		ParametrosCaracteristicaAlteracao parametrosCaracteristicaAlteracao = paramAlterarCaracteristica.addNewAlterarCaracteristicaRequest().addNewParametrosAlterarCaracteristica().addNewParametrosCaracteristicaAlteracao();
		parametrosCaracteristicaAlteracao.setDescricao(form.getDescricaoCaracteristica()==null?"":form.getDescricaoCaracteristica());
		parametrosCaracteristicaAlteracao.setIdCaracteristica(form.getIdCaracteristica());
		parametrosCaracteristicaAlteracao.setNmCaracteristica(form.getNomeCaracteristica());
		if(form.getJustificativaAlteracao() != null && !form.getJustificativaAlteracao().trim().equalsIgnoreCase(""))
			parametrosCaracteristicaAlteracao.setJustificativa(form.getJustificativaAlteracao());

		prepareServiceControl(caracteristicaSoapServiceControl);
		caracteristicaSoapServiceControl.alterarCaracteristica(paramAlterarCaracteristica);

		try {
			HttpServletResponse response = getResponse();
			response.setContentType("text/javascript");
			PrintWriter out = this.getResponse().getWriter();
			out.println("$('botao_pesquisar').click();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupApagarValorCaracteristica.jsp") })
	public Forward abrirPopupApagarValorCaracteristica() {

		HttpServletRequest request = getRequest();
		String idValorCaracteristicaString = request.getParameter("id_valor_caracteristica");

		String idCaracteristicaString = (String) request.getParameter("id_caracteristica");
		request.setAttribute("id_caracteristica", idCaracteristicaString);
		request.setAttribute("id_valor_caracteristica", idValorCaracteristicaString);

		if (idCaracteristicaString != null && !idCaracteristicaString.trim().equals("")) {
			BuscarModelosPorValorCaracteristicaRequestDocument buscarModelosPorValorCaracteristicaRequestDocument = BuscarModelosPorValorCaracteristicaRequestDocument.Factory.newInstance();
			ParametrosModelosPorValorCaracteristicaIn parametrosModelosPorValorCaracteristicaIn = buscarModelosPorValorCaracteristicaRequestDocument.addNewBuscarModelosPorValorCaracteristicaRequest().addNewParametrosBuscarListaModeloPorValorCaracteristica().addNewParametrosModelosPorValorCaracteristicaIn();
			parametrosModelosPorValorCaracteristicaIn.setIdValorCaracteristica(Long.valueOf(idValorCaracteristicaString));

			prepareServiceControl(caracteristicaSoapServiceControl);
			BuscarModelosPorValorCaracteristicaResponseDocument buscarModelosPorValorCaracteristicaResponseDocument = caracteristicaSoapServiceControl.buscarModelosPorValorCaracteristica(buscarModelosPorValorCaracteristicaRequestDocument);
			ResultadoBuscarListaModeloPorValorCaracteristica resultadoBuscarListaModeloPorValorCaracteristica = buscarModelosPorValorCaracteristicaResponseDocument.getBuscarModelosPorValorCaracteristicaResponse().getResultadoBuscarListaModeloPorValorCaracteristica();
			List<ModeloPorValorCaracteristica>  modeloPorValorCaracteristicaList;
			if (resultadoBuscarListaModeloPorValorCaracteristica != null) {
				modeloPorValorCaracteristicaList = resultadoBuscarListaModeloPorValorCaracteristica.getModeloPorValorCaracteristicaList();
			} else {
				modeloPorValorCaracteristicaList = new ArrayList<ModeloPorValorCaracteristica>();
			}
			request.setAttribute("modelos_afetados", modeloPorValorCaracteristicaList);
		}

		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(validationErrorForward = @Jpf.Forward(name = "fail", navigateTo = Jpf.NavigateTo.previousAction))
	public Forward addValorCaracteristica(
			br.com.vivo.catalogoPRS.pageflows.param.produtos.caracteristicas.CaracteristicasController.NovoValorCaracteristicaFormBean form) {

		CriarValorCaracteristicaRequestDocument paramCreateValorCaracteristicaCriacao = CriarValorCaracteristicaRequestDocument.Factory.newInstance();
		ValorCaracteristicaCriacao valorCaracteristicaCriacao = paramCreateValorCaracteristicaCriacao.addNewCriarValorCaracteristicaRequest().addNewParametrosCriarValorCaracteristica().addNewValorCaracteristicaCriacao();
		valorCaracteristicaCriacao.setIdCaracteristica(form.getIdCaracteristica());
		valorCaracteristicaCriacao.setValor(form.getValorCaracteristica());

		prepareServiceControl(caracteristicaSoapServiceControl);
		caracteristicaSoapServiceControl.criarValorCaracteristica(paramCreateValorCaracteristicaCriacao);

		try {
			HttpServletResponse response = getResponse();
			response.setContentType("text/javascript");
			PrintWriter out = this.getResponse().getWriter();
			out.println("$('pagina_valor_caracteristica_selecionada').onclick();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "alterarValorCaracteristica.jsp") })
	public Forward abrirAlterarValorCaracteristica() {
		BuscarValorCaracteristicaPorIdRequestDocument paramBuscarValorCaracteristicaPorId = BuscarValorCaracteristicaPorIdRequestDocument.Factory.newInstance();
		ParametrosValorCaracteristicaPorIdIn parametrosValorCaracteristicaPorIdIn = paramBuscarValorCaracteristicaPorId.addNewBuscarValorCaracteristicaPorIdRequest().addNewParametrosBuscarValorCaracteristicaPorId().addNewParametrosValorCaracteristicaPorIdIn();
		parametrosValorCaracteristicaPorIdIn.setIdValorCaracteristica(Long.valueOf(getRequest().getParameter("id_valor_caracteristica")));

		BuscarValorCaracteristicaPorIdResponseDocument buscarValorCaracteristicaPorIdResponseDocument = caracteristicaSoapServiceControl.buscarValorCaracteristicaPorId(paramBuscarValorCaracteristicaPorId);
		ResultadoBuscarValorCaracteristicaPorId resultadoBuscarValorCaracteristicaPorId = buscarValorCaracteristicaPorIdResponseDocument.getBuscarValorCaracteristicaPorIdResponse().getResultadoBuscarValorCaracteristicaPorId();

		getRequest().setAttribute("valorCaracteristica", resultadoBuscarValorCaracteristicaPorId);

		Forward forward = new Forward("success");

		return forward;
	}

	@Jpf.Action()
	public Forward disponibilizarCaracteristica() throws IOException {

		HttpServletRequest request = getRequest();
		String idCaracteristica = request.getParameter("id_caracteristica");

		DisponibilizarCaracteristicaRequestDocument caracteristica = DisponibilizarCaracteristicaRequestDocument.Factory.newInstance();
		ParametrosDispCaracteristica parametrosDispCaracteristica = caracteristica.addNewDisponibilizarCaracteristicaRequest().addNewParametrosDisponibilizarCaracteristica().addNewParametrosDispCaracteristica();
		parametrosDispCaracteristica.setIndisponivel(getRequest().getParameter("checked"));
		parametrosDispCaracteristica.setInsimulador("N");
		parametrosDispCaracteristica.setIdCaracteristica(Long.valueOf(idCaracteristica));

		prepareServiceControl(caracteristicaSoapServiceControl);
		caracteristicaSoapServiceControl.disponibilizarCaracteristica(caracteristica);

		writeJSOutput("$('disp_" + idCaracteristica + "').checked=!$('disp_" + idCaracteristica + "').checked;");
		writeJSOutput("$('filtro_" + idCaracteristica + "').checked=false;");
		writeJSOutput("if($('disp_" + idCaracteristica + "').checked){$('filtro_" + idCaracteristica + "').enable()}else{$('filtro_" + idCaracteristica
				+ "').disable()}");

		return null;
	}

	@Jpf.Action()
	public Forward ativarFiltroCaracteristica() throws IOException {
		HttpServletRequest request = getRequest();
		String idCaracteristica = request.getParameter("id_caracteristica");


		DisponibilizarCaracteristicaRequestDocument caracteristica = DisponibilizarCaracteristicaRequestDocument.Factory.newInstance();
		ParametrosDispCaracteristica parametrosDispCaracteristica = caracteristica.addNewDisponibilizarCaracteristicaRequest().addNewParametrosDisponibilizarCaracteristica().addNewParametrosDispCaracteristica();
		parametrosDispCaracteristica.setIndisponivel("S");
		parametrosDispCaracteristica.setInsimulador(getRequest().getParameter("checked"));
		parametrosDispCaracteristica.setIdCaracteristica(Long.valueOf(idCaracteristica));

		prepareServiceControl(caracteristicaSoapServiceControl);
		caracteristicaSoapServiceControl.disponibilizarCaracteristica(caracteristica);

		writeJSOutput("$('disp_" + idCaracteristica + "').checked=true");
		writeJSOutput("$('filtro_" + idCaracteristica + "').checked=!$('filtro_" + idCaracteristica + "').checked;");

		return null;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupAlterarCaracteristica.jsp") })
	public Forward popupAlterarCaracteristica() {
		HttpServletRequest request = getRequest();

		String idCaracteristicaString = (String) request.getParameter("id_caracteristica");
		request.setAttribute("id_caracteristica", idCaracteristicaString);

		if (idCaracteristicaString != null && !idCaracteristicaString.trim().equals("")) {
			Long idCaracteristica = Long.valueOf(idCaracteristicaString);
			BuscarListaModeloPorCaracteristicaRequestDocument paramBuscarModelosPorCaracteristica = BuscarListaModeloPorCaracteristicaRequestDocument.Factory.newInstance();
			ParametrosModelosPorCaracteristicaIn parametrosModelosPorCaracteristicaIn = paramBuscarModelosPorCaracteristica.addNewBuscarListaModeloPorCaracteristicaRequest().addNewParametrosBuscarListaModeloPorCaracteristica().addNewParametrosModelosPorCaracteristicaIn();
			parametrosModelosPorCaracteristicaIn.setIdCaracteristica(idCaracteristica);

			prepareServiceControl(caracteristicaSoapServiceControl);
			try {
				BuscarListaModeloPorCaracteristicaResponseDocument buscarListaModeloPorCaracteristicaResponseDocument = caracteristicaSoapServiceControl.buscarListaModeloPorCaracteristica(paramBuscarModelosPorCaracteristica);
				ResultadoBuscarListaModeloPorCaracteristica resultadoBuscarListaModeloPorCaracteristica = buscarListaModeloPorCaracteristicaResponseDocument.getBuscarListaModeloPorCaracteristicaResponse().getResultadoBuscarListaModeloPorCaracteristica();
				List<ModeloPorCaracteristica> modeloPorCaracteristicaList;
				if (resultadoBuscarListaModeloPorCaracteristica != null) {
					modeloPorCaracteristicaList = resultadoBuscarListaModeloPorCaracteristica.getModeloPorCaracteristicaList();
				} else {
					modeloPorCaracteristicaList = new ArrayList<ModeloPorCaracteristica>();
				}
				request.setAttribute("modelos_afetados", modeloPorCaracteristicaList);
			} catch (ServiceControlException e) {
				request.setAttribute("modelos_afetados", new ArrayList<ModeloPorCaracteristica>());
			}
		}

		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupAlterarValorCaracteristica.jsp") })
	public Forward popupAlterarValorCaracteristica() {
		HttpServletRequest request = getRequest();
		String idValorCaracteristicaString = request.getParameter("id_valor_caracteristica");

		String idCaracteristicaString = (String) request.getParameter("id_caracteristica");
		request.setAttribute("id_caracteristica", idCaracteristicaString);

		if (idCaracteristicaString != null && !idCaracteristicaString.trim().equals("")) {
			Long idCaracteristica = Long.valueOf(idCaracteristicaString);
			BuscarListaModeloPorCaracteristicaRequestDocument paramBuscarModelosPorCaracteristica = BuscarListaModeloPorCaracteristicaRequestDocument.Factory.newInstance();
			ParametrosModelosPorCaracteristicaIn parametrosModelosPorCaracteristicaIn = paramBuscarModelosPorCaracteristica.addNewBuscarListaModeloPorCaracteristicaRequest().addNewParametrosBuscarListaModeloPorCaracteristica().addNewParametrosModelosPorCaracteristicaIn();
			parametrosModelosPorCaracteristicaIn.setIdCaracteristica(idCaracteristica);

			prepareServiceControl(caracteristicaSoapServiceControl);
			BuscarListaModeloPorCaracteristicaResponseDocument buscarListaModeloPorCaracteristicaResponseDocument = caracteristicaSoapServiceControl.buscarListaModeloPorCaracteristica(paramBuscarModelosPorCaracteristica);
			ResultadoBuscarListaModeloPorCaracteristica resultadoBuscarListaModeloPorCaracteristica = buscarListaModeloPorCaracteristicaResponseDocument.getBuscarListaModeloPorCaracteristicaResponse().getResultadoBuscarListaModeloPorCaracteristica();
			List<ModeloPorCaracteristica> modeloPorCaracteristicaList;
			if (resultadoBuscarListaModeloPorCaracteristica != null) {
				modeloPorCaracteristicaList = resultadoBuscarListaModeloPorCaracteristica.getModeloPorCaracteristicaList();
			} else {
				modeloPorCaracteristicaList = new ArrayList<ModeloPorCaracteristica>();
			}
			request.setAttribute("modelos_afetados", modeloPorCaracteristicaList);
		}

		request.setAttribute("id_valor_caracteristica", idValorCaracteristicaString);
		Forward forward = new Forward("success");
		return forward;
	}

	@Override
	protected void onCreate() {
		super.onCreate();
		
		funcionalidade = "25"; // Produtos/Parametrização de Características
		
		permissoesDoController.put("abrirAlterarValorCaracteristica", "alterarValorCaracteristica");
		permissoesDoController.put("abrirPopupApagarValorCaracteristica", "excluirValor");
		permissoesDoController.put("addCaracteristica", "adicionarCaracteristica");
		permissoesDoController.put("addValorCaracteristica", "adicionarValor");
		permissoesDoController.put("alterarCaracteristica", "alterarCaracteristica");
		permissoesDoController.put("alterarValorCaracteristica", "alterarValorCaracteristica");
		permissoesDoController.put("apagarCaracteristica", "excluirCaracteristica");
		permissoesDoController.put("apagarValorCaracteristica", "excluirValor");
		permissoesDoController.put("ativarFiltroCaracteristica", "ativarFiltroCaracteristica");
		permissoesDoController.put("begin", "consultarCaracteristica");
		permissoesDoController.put("carregarAlterarCaracteristica", "alterarCaracteristica");
		permissoesDoController.put("disponibilizarCaracteristica", "disponibilizarCaracteristica");
		permissoesDoController.put("pesquisarCaracteristicas", "consultarCaracteristica");
		permissoesDoController.put("popupAlterarCaracteristica", "alterarCaracteristica");
		permissoesDoController.put("popupAlterarValorCaracteristica", "alterarCaracteristica");
		permissoesDoController.put("popupApagarCaracteristica", "excluirCaracteristica");
		permissoesDoController.put("ValoresCaracteristica", "consultarValoresCaracteristica");
		
		autorizaVazio.add("popupApagarCaracteristica");
		autorizaVazio.add("abrirPopupApagarValorCaracteristica");
		autorizaVazio.add("popupAlterarValorCaracteristica");
		autorizaVazio.add("popupAlterarCaracteristica");
		//autorizaVazio.add("pesquisarCaracteristicas");

		caracteristicaSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.CARACTERISTICAS));
	}

	@Override
	protected void onDestroy(HttpSession session) {
	}

	@Jpf.FormBean
	public static class NovaCaracteristicaFormBean implements java.io.Serializable {
		private static final long serialVersionUID = 1L;

		private String descricaoCaracteristica;

		private String nomeCaracteristica;

		public String getDescricaoCaracteristica() {
			return descricaoCaracteristica;
		}

		public void setDescricaoCaracteristica(String descricaoCaracteristica) {
			this.descricaoCaracteristica = descricaoCaracteristica==null?descricaoCaracteristica:descricaoCaracteristica.toUpperCase();
		}

		@Jpf.ValidatableProperty(validateRequired = @Jpf.ValidateRequired())
		public String getNomeCaracteristica() {
			return nomeCaracteristica;
		}

		public void setNomeCaracteristica(String nomeCaracteristica) {
			this.nomeCaracteristica = nomeCaracteristica==null?nomeCaracteristica:nomeCaracteristica.toUpperCase();;
		}

	}

	@Jpf.FormBean
	public static class PesquisarFormBean implements java.io.Serializable {
		private static final long serialVersionUID = 1L;

		private String nomeCaracteristica;

		private String paginaSolicitada;

		public String getNomeCaracteristica() {
			return nomeCaracteristica;
		}

		public void setNomeCaracteristica(String nomeCaracteristica) {
			this.nomeCaracteristica = nomeCaracteristica;
		}

		public String getPaginaSolicitada() {
			return paginaSolicitada;
		}

		public void setPaginaSolicitada(String paginaSolicitada) {
			this.paginaSolicitada = paginaSolicitada;
		}
	}

	@Jpf.FormBean
	public static class AlterarCaracteristicaFormBean implements java.io.Serializable {
		private static final long serialVersionUID = 1L;

		private Long idCaracteristica;

		private String nomeCaracteristica;

		private String descricaoCaracteristica;

		private String justificativaAlteracao;

		public String getDescricaoCaracteristica() {
			return descricaoCaracteristica;
		}

		public void setDescricaoCaracteristica(String descricaoCaracteristica) {
			this.descricaoCaracteristica = descricaoCaracteristica==null?descricaoCaracteristica:descricaoCaracteristica.toUpperCase();;
		}

		@Jpf.ValidatableProperty(validateRequired = @Jpf.ValidateRequired(), validateType = @Jpf.ValidateType(type = long.class))
		public Long getIdCaracteristica() {
			return idCaracteristica;
		}

		public void setIdCaracteristica(Long idCaracteristica) {
			this.idCaracteristica = idCaracteristica;
		}

		public String getJustificativaAlteracao() {
			return justificativaAlteracao;
		}

		public void setJustificativaAlteracao(String justificativaAlteracao) {
			this.justificativaAlteracao = justificativaAlteracao;
		}

		@Jpf.ValidatableProperty(validateRequired = @Jpf.ValidateRequired())
		public String getNomeCaracteristica() {
			return nomeCaracteristica;
		}

		public void setNomeCaracteristica(String nomeCaracteristica) {
			this.nomeCaracteristica = nomeCaracteristica==null?nomeCaracteristica:nomeCaracteristica.toUpperCase();
		}

	}

	@Jpf.FormBean
	public static class NovoValorCaracteristicaFormBean implements java.io.Serializable {
		private static final long serialVersionUID = 1L;

		private String valorCaracteristica;

		private Long idCaracteristica;

		@Jpf.ValidatableProperty(validateRequired = @Jpf.ValidateRequired())
		public String getValorCaracteristica() {
			return valorCaracteristica;
		}

		public void setValorCaracteristica(String valorCaracteristica) {
			this.valorCaracteristica = valorCaracteristica==null?valorCaracteristica:valorCaracteristica.toUpperCase();
		}

		@Jpf.ValidatableProperty(validateRequired = @Jpf.ValidateRequired(), validateType = @Jpf.ValidateType(type = long.class))
		public Long getIdCaracteristica() {
			return idCaracteristica;
		}

		public void setIdCaracteristica(Long idCaracteristica) {
			this.idCaracteristica = idCaracteristica;
		}

	}

	@Jpf.FormBean
	public static class ApagarValorCaracteristicaFormBean extends ApagarFormBean implements java.io.Serializable {
		private static final long serialVersionUID = 1L;

		private Long idCaracteristica;

		@Jpf.ValidatableProperty(validateRequired = @Jpf.ValidateRequired(), validateType = @Jpf.ValidateType(type = long.class))
		public Long getIdCaracteristica() {
			return idCaracteristica;
		}

		public void setIdCaracteristica(Long idCaracteristica) {
			this.idCaracteristica = idCaracteristica;
		}
	}

	@Jpf.FormBean
	public static class AlterarValorCaracteristicaFormBean implements java.io.Serializable {
		private static final long serialVersionUID = 1L;

		private String valorCaracteristica;

		private Long idCaracteristica;

		private Long idValorCaracteristica;

		private String justificativa;

		public Long getIdCaracteristica() {
			return idCaracteristica;
		}

		public void setIdCaracteristica(Long idCaracteristica) {
			this.idCaracteristica = idCaracteristica;
		}

		public Long getIdValorCaracteristica() {
			return idValorCaracteristica;
		}

		public void setIdValorCaracteristica(Long idValorCaracteristica) {
			this.idValorCaracteristica = idValorCaracteristica;
		}

		public String getJustificativa() {
			return justificativa;
		}

		public void setJustificativa(String justificativa) {
			this.justificativa = justificativa;
		}

		public String getValorCaracteristica() {
			return valorCaracteristica;
		}

		public void setValorCaracteristica(String valorCaracteristica) {
			this.valorCaracteristica = valorCaracteristica==null?valorCaracteristica:valorCaracteristica.toUpperCase();
		}

	}*/

}