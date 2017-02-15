package br.com.vivo.catalogoPRS.pageflows.param.matrizOferta.modeloVenda;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*import br.com.vivo.catalogoPRS.controls.ModeloSoapServiceControl;
import br.com.vivo.catalogoPRS.controls.ModeloVendaServiceControl;
import br.com.vivo.catalogoPRS.controls.TecnologiaSoapServiceControl;
import br.com.vivo.catalogoPRS.controls.TipoProdutoSoapServiceControl;
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseFlowController;
import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;
import br.com.vivo.catalogoPRS.util.WebServicesConfiguration;
import br.com.vivo.sn.catalogoGeral.PaginacaoInDocument.PaginacaoIn;
import br.com.vivo.sn.catalogoGeral.PaginacaoOutDocument.PaginacaoOut;
import br.com.vivo.sn.catalogoModeloVenda.AlterarModeloVendaRequestDocument;
import br.com.vivo.sn.catalogoModeloVenda.AlterarModeloVendaResponseDocument;
import br.com.vivo.sn.catalogoModeloVenda.AssociarProdutoModeloVendaRequestDocument;
import br.com.vivo.sn.catalogoModeloVenda.BuscarDadosModeloVendaRequestDocument;
import br.com.vivo.sn.catalogoModeloVenda.BuscarDadosModeloVendaResponseDocument;
import br.com.vivo.sn.catalogoModeloVenda.BuscarListaModeloVendaRequestDocument;
import br.com.vivo.sn.catalogoModeloVenda.BuscarListaModeloVendaResponseDocument;
import br.com.vivo.sn.catalogoModeloVenda.BuscarListaProdComModeloVendaRequestDocument;
import br.com.vivo.sn.catalogoModeloVenda.BuscarListaProdComModeloVendaResponseDocument;
import br.com.vivo.sn.catalogoModeloVenda.BuscarListaProdSemModeloVendaRequestDocument;
import br.com.vivo.sn.catalogoModeloVenda.BuscarListaProdSemModeloVendaResponseDocument;
import br.com.vivo.sn.catalogoModeloVenda.CriarModeloVendaRequestDocument;
import br.com.vivo.sn.catalogoModeloVenda.CriarModeloVendaResponseDocument;
import br.com.vivo.sn.catalogoModeloVenda.ExcluirModeloVendaRequestDocument;
import br.com.vivo.sn.catalogoModeloVenda.ExcluirProdutoModeloVendaRequestDocument;
import br.com.vivo.sn.catalogoModeloVenda.ParametrosAlterarModeloVendaDocument.ParametrosAlterarModeloVenda;
import br.com.vivo.sn.catalogoModeloVenda.ParametrosAssociarProdutoModeloVendaDocument.ParametrosAssociarProdutoModeloVenda;
import br.com.vivo.sn.catalogoModeloVenda.ParametrosAssociarProdutoModeloVendaDocument.ParametrosAssociarProdutoModeloVenda.ListaIdProdutos;
import br.com.vivo.sn.catalogoModeloVenda.ParametrosBuscarDadosModeloVendaDocument.ParametrosBuscarDadosModeloVenda;
import br.com.vivo.sn.catalogoModeloVenda.ParametrosBuscarListaModeloVendaDocument.ParametrosBuscarListaModeloVenda;
import br.com.vivo.sn.catalogoModeloVenda.ParametrosBuscarListaProdComModeloVendaDocument.ParametrosBuscarListaProdComModeloVenda;
import br.com.vivo.sn.catalogoModeloVenda.ParametrosBuscarListaProdSemModeloVendaDocument.ParametrosBuscarListaProdSemModeloVenda;
import br.com.vivo.sn.catalogoModeloVenda.ParametrosCriarModeloVendaDocument.ParametrosCriarModeloVenda;
import br.com.vivo.sn.catalogoModeloVenda.ParametrosExcluirModeloVendaDocument.ParametrosExcluirModeloVenda;
import br.com.vivo.sn.catalogoModeloVenda.ParametrosExcluirProdutoModeloVendaDocument.ParametrosExcluirProdutoModeloVenda;
import br.com.vivo.sn.catalogoModeloVenda.ResultadoBuscarDadosModeloVendaDocument.ResultadoBuscarDadosModeloVenda.ListaBuscarDadosModeloVenda.DadosModeloVenda;
import br.com.vivo.sn.catalogoModeloVenda.ResultadoBuscarListaModeloVendaDocument.ResultadoBuscarListaModeloVenda;
import br.com.vivo.sn.catalogoModeloVenda.ResultadoBuscarListaModeloVendaDocument.ResultadoBuscarListaModeloVenda.ListaModeloVenda.ModeloVenda;
import br.com.vivo.sn.catalogoModeloVenda.ResultadoBuscarListaProdComModeloVendaDocument.ResultadoBuscarListaProdComModeloVenda;
import br.com.vivo.sn.catalogoModeloVenda.ResultadoBuscarListaProdComModeloVendaDocument.ResultadoBuscarListaProdComModeloVenda.ListaProdComModVenda.ProdComModVenda;
import br.com.vivo.sn.catalogoModeloVenda.ResultadoBuscarListaProdSemModeloVendaDocument.ResultadoBuscarListaProdSemModeloVenda;
import br.com.vivo.sn.catalogoModeloVenda.ResultadoBuscarListaProdSemModeloVendaDocument.ResultadoBuscarListaProdSemModeloVenda.ListaProdSemModVenda.ProdSemModVenda;
import br.com.vivo.sn.catalogoTecnologia.BuscarListaTecnologiaRequestDocument;
import br.com.vivo.sn.catalogoTecnologia.BuscarListaTecnologiaResponseDocument;
import br.com.vivo.sn.catalogoTecnologia.ResultadoBuscarListaTecnologiaDocument.ResultadoBuscarListaTecnologia.Tecnologia;
import br.com.vivo.sn.catalogoTipoProduto.BuscarListaTipoProdutoRequestDocument;
import br.com.vivo.sn.catalogoTipoProduto.BuscarListaTipoProdutoResponseDocument;
import br.com.vivo.sn.catalogoTipoProduto.ResultadoListarTipoProdutoDocument.ResultadoListarTipoProduto.TipoProduto;
*/
public class ModeloVendaMatrizOfertaController  {
/*	private static final long serialVersionUID = 1L;

	@Control
	private ModeloVendaServiceControl modeloVendaServiceControl;

	@Control
	private TipoProdutoSoapServiceControl tipoProdutoSoapServiceControl;

	@Control
	private TecnologiaSoapServiceControl tecnologiaSoapServiceControl;

	@Control
	private ModeloSoapServiceControl modeloSoapServiceControl;

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "modeloVendaMatrizOferta.jsp") })
	public Forward begin() {
		return new Forward("success");
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "resultadoPesquisaModeloVenda.jsp") })
	public Forward pesquisarModeloVendaMatrizOferta(br.com.vivo.catalogoPRS.pageflows.param.matrizOferta.modeloVenda.ModeloVendaMatrizOfertaController.ModeloVendaMatrizOfertaFormBean form) {
		BuscarListaModeloVendaRequestDocument requestDocument = BuscarListaModeloVendaRequestDocument.Factory.newInstance();
		ParametrosBuscarListaModeloVenda buscarListaModeloVenda = requestDocument.addNewBuscarListaModeloVendaRequest().addNewParametrosBuscarListaModeloVenda();
		PaginacaoIn paginacaoIn = buscarListaModeloVenda.addNewPaginacaoIn();
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		if(form.getPaginaSolicitada() != null && !form.getPaginaSolicitada().equals("")) {
			paginacaoIn.setPaginaSolicitada(form.getPaginaSolicitada());
		}else {
			paginacaoIn.setPaginaSolicitada(1);
		}
		if(form.getCdModeloVenda() != null && form.getCdModeloVenda().length() > 0) {
			buscarListaModeloVenda.setCdModeloVenda(form.getCdModeloVenda());
		}
		if(form.getNmModeloVenda() != null && form.getNmModeloVenda().length() > 0) {
			buscarListaModeloVenda.setNmModeloVenda(form.getNmModeloVenda());
		}

		prepareServiceControl(modeloVendaServiceControl);
		BuscarListaModeloVendaResponseDocument responseDocument = modeloVendaServiceControl.buscarListaModeloVenda(requestDocument);
		ResultadoBuscarListaModeloVenda resultadoBuscarListaModeloVenda = responseDocument.getBuscarListaModeloVendaResponse().getResultadoBuscarListaModeloVenda();
		PaginacaoOut paginacaoOut = resultadoBuscarListaModeloVenda.getPaginacaoOut();
		tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina());
		List<ModeloVenda> modeloVendaList = resultadoBuscarListaModeloVenda.getListaModeloVenda().getModeloVendaList();
		
		this.getRequest().setAttribute("modelosVenda", modeloVendaList);
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", action = "pesquisarModeloVendaMatrizOferta") })
	public Forward criarModeloDeVendaMatrizOferta(br.com.vivo.catalogoPRS.pageflows.param.matrizOferta.modeloVenda.ModeloVendaMatrizOfertaController.ModeloVendaMatrizOfertaFormBean form) {
		CriarModeloVendaRequestDocument requestDocument = CriarModeloVendaRequestDocument.Factory.newInstance();
		ParametrosCriarModeloVenda modeloVenda = requestDocument.addNewCriarModeloVendaRequest().addNewParametrosCriarModeloVenda();
		if(form.getCdModeloVenda() != null && form.getCdModeloVenda().length() > 0) {
			modeloVenda.setCdModeloVenda(form.getCdModeloVenda());
		}
		if(form.getNmModeloVenda() != null && form.getNmModeloVenda().length() > 0) {
			modeloVenda.setNmModeloVenda(form.getNmModeloVenda());
		}
		
		prepareServiceControl(modeloVendaServiceControl);
		CriarModeloVendaResponseDocument responseDocument = modeloVendaServiceControl.criarModeloVenda(requestDocument);
		this.getRequest().setAttribute("idModeloVenda", responseDocument.getCriarModeloVendaResponse().getResultadoCriarModeloVenda().getIdModeloVenda());
		ModeloVendaMatrizOfertaFormBean formBean = new ModeloVendaMatrizOfertaFormBean();
		formBean.setCdModeloVenda(form.getCdModeloVenda());
		formBean.setNmModeloVenda(form.getNmModeloVenda());

		Forward forward = new Forward("success");
		forward.addOutputForm(formBean);
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupProdutosAssociados.jsp") })
	public Forward listarProdutosAssociado() {
		HttpServletRequest request = getRequest();
		Integer paginaSolicitada;
		if(request.getParameter("pagina_solicitada") != null && !request.getParameter("pagina_solicitada").trim().equalsIgnoreCase("")) {
			paginaSolicitada = Integer.valueOf(request.getParameter("pagina_solicitada"));
		}else {
			paginaSolicitada = 1;
		}
		
		BuscarListaProdComModeloVendaRequestDocument requestDocument = BuscarListaProdComModeloVendaRequestDocument.Factory.newInstance();
		ParametrosBuscarListaProdComModeloVenda prodComModeloVenda = requestDocument.addNewBuscarListaProdComModeloVendaRequest().addNewParametrosBuscarListaProdComModeloVenda();
		PaginacaoIn paginacaoIn = prodComModeloVenda.addNewPaginacaoIn();
		paginacaoIn.setPaginaSolicitada(paginaSolicitada);
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPaginaNosPopups());
		
		if(request.getParameter("id_modelo_venda") != null && !request.getParameter("id_modelo_venda").trim().equalsIgnoreCase("")) {
			prodComModeloVenda.setIdModeloVenda(Long.valueOf(request.getParameter("id_modelo_venda")));
			request.setAttribute("idModeloVenda", request.getParameter("id_modelo_venda"));
		}
		
		prepareServiceControl(modeloVendaServiceControl);
		BuscarListaProdComModeloVendaResponseDocument responseDocument = modeloVendaServiceControl.buscarListaProdComModeloVenda(requestDocument);
		ResultadoBuscarListaProdComModeloVenda resultadoBuscarListaProdComModeloVenda = responseDocument.getBuscarListaProdComModeloVendaResponse().getResultadoBuscarListaProdComModeloVenda();
		List<ProdComModVenda> prodComModVendaList = resultadoBuscarListaProdComModeloVenda.getListaProdComModVenda().getProdComModVendaList();
		PaginacaoOut paginacaoOut = resultadoBuscarListaProdComModeloVenda.getPaginacaoOut();
		
		int elementoPorPagina = ApplicationConfiguration.getElementosPorPaginaNosPopups();
		int paginaAtual = paginacaoOut.getPaginaAtual();
		int totalRegistros = paginacaoOut.getTotalRegistros();
		long totalPagina = Math.round(totalRegistros / elementoPorPagina);
		if(((totalRegistros / (double) elementoPorPagina) - totalPagina) > 0) {
			totalPagina++;
		}
		if(totalPagina == 0) {
			totalPagina++;
			request.setAttribute("totalPagina", totalPagina);
			request.setAttribute("paginaAtual", paginaAtual);
		}else {
			request.setAttribute("totalPagina", 1);
			request.setAttribute("paginaAtual", 1);
		}
		
		request.setAttribute("produtos", prodComModVendaList);
		request.setAttribute("produtos", prodComModVendaList);
		request.setAttribute("totalRegistros", totalRegistros);
		request.setAttribute("modeloVenda", request.getParameter("nm_modelo_venda"));
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "alterarModeloVendaMatrizOferta.jsp") })
	public Forward alterarModeloVendaMatrizOferta() {
		HttpServletRequest request = getRequest();
		if(request.getParameter("id_modelo_venda") != null && request.getParameter("id_modelo_venda").trim().length() > 0) {
			request.setAttribute("idModeloVenda", request.getParameter("id_modelo_venda"));
		}
		if(request.getParameter("cd_modelo_venda") != null && request.getParameter("cd_modelo_venda").trim().length() > 0) {
			request.setAttribute("cdModeloVenda", request.getParameter("cd_modelo_venda"));
		}
		if(request.getParameter("nm_modelo_venda") != null && request.getParameter("nm_modelo_venda").trim().length() > 0) {
			request.setAttribute("nmModeloVenda", request.getParameter("nm_modelo_venda"));
		}
		Forward forward = new Forward("success");
		return forward;
	}


	@Jpf.Action()
	public Forward updateModeloDeVendaMatrizOferta(br.com.vivo.catalogoPRS.pageflows.param.matrizOferta.modeloVenda.ModeloVendaMatrizOfertaController.ModeloVendaMatrizOfertaFormBean form) {
		AlterarModeloVendaRequestDocument requestDocument = AlterarModeloVendaRequestDocument.Factory.newInstance();
		ParametrosAlterarModeloVenda parametrosAlterarModeloVenda = requestDocument.addNewAlterarModeloVendaRequest().addNewParametrosAlterarModeloVenda();
		if(form.getIdModeloVenda() != null) {
			parametrosAlterarModeloVenda.setIdModeloVenda(form.getIdModeloVenda());
		}
		if(form.getNmModeloVenda() != null && form.getNmModeloVenda().trim().length() > 0) {
			parametrosAlterarModeloVenda.setNmModeloVenda(form.getNmModeloVenda());
		}
		
		prepareServiceControl(modeloVendaServiceControl);
		AlterarModeloVendaResponseDocument modeloVendaResponseDocument = modeloVendaServiceControl.alterarModeloVenda(requestDocument);
		modeloVendaResponseDocument.getAlterarModeloVendaResponse().getResultadoAlterarModeloVenda().getIdModeloVenda();
	
		PrintWriter out = null;
		try {
			HttpServletResponse response = getResponse();
			response.setContentType("text/javascript");
			out = this.getResponse().getWriter();
			out.println("$('botao_pesquisar_modelo_vendas').onclick();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			out.close();
		}
		return null;
	}

	@Jpf.Action(forwards = {@Jpf.Forward(name = "success", path = "popupConfirmAlteracaoModeloVenda.jsp")})
	public Forward confirmAlterarModeloVenda() {
		HttpServletRequest request = getRequest();
		request.setAttribute("cdModeloVenda", request.getParameter("cd_modelo_venda"));
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupConfirmExclusaoModeloVenda.jsp") })
	public Forward excluirModeloVendaMatrizOferta() {
		HttpServletRequest request = getRequest();
		BuscarListaProdComModeloVendaRequestDocument requestDocument = BuscarListaProdComModeloVendaRequestDocument.Factory.newInstance();
		ParametrosBuscarListaProdComModeloVenda parametrosBuscarListaProdComModeloVenda = requestDocument.addNewBuscarListaProdComModeloVendaRequest().addNewParametrosBuscarListaProdComModeloVenda();
		PaginacaoIn paginacaoIn = parametrosBuscarListaProdComModeloVenda.addNewPaginacaoIn();
		paginacaoIn.setPaginaSolicitada(1);
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPaginaNosPopups());
		if(request.getParameter("id_modelo_venda") != null && request.getParameter("id_modelo_venda").trim().length() > 0) {
			parametrosBuscarListaProdComModeloVenda.setIdModeloVenda(Long.valueOf(request.getParameter("id_modelo_venda")));
			request.setAttribute("idModeloVenda", request.getParameter("id_modelo_venda"));
		}
		prepareServiceControl(modeloVendaServiceControl);
		BuscarListaProdComModeloVendaResponseDocument responseDocument = modeloVendaServiceControl.buscarListaProdComModeloVenda(requestDocument);
		ResultadoBuscarListaProdComModeloVenda resultadoBuscarListaProdComModeloVenda = responseDocument.getBuscarListaProdComModeloVendaResponse().getResultadoBuscarListaProdComModeloVenda();
		List<ProdComModVenda> prodComModVendaList = resultadoBuscarListaProdComModeloVenda.getListaProdComModVenda().getProdComModVendaList();
		int totalProdutosAssociado = prodComModVendaList.size();
		
		if(request.getParameter("cd_modelo_venda") != null && request.getParameter("cd_modelo_venda").trim().length() > 0) {
			request.setAttribute("cdModeloVenda", request.getParameter("cd_modelo_venda"));
		}
		
		request.setAttribute("prodsAssociado", totalProdutosAssociado);
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action()
	public Forward deleteModeloVendaMatrizOferta(br.com.vivo.catalogoPRS.pageflows.param.matrizOferta.modeloVenda.ModeloVendaMatrizOfertaController.ModeloVendaMatrizOfertaFormBean form) {
		ExcluirModeloVendaRequestDocument requestDocument = ExcluirModeloVendaRequestDocument.Factory.newInstance();
		ParametrosExcluirModeloVenda parametrosExcluirModeloVenda = requestDocument.addNewExcluirModeloVendaRequest().addNewParametrosExcluirModeloVenda();
		if(form.getIdModeloVenda() != null) {
			parametrosExcluirModeloVenda.setIdModeloVenda(form.getIdModeloVenda());
		}
		prepareServiceControl(modeloVendaServiceControl);
		modeloVendaServiceControl.excluirModeloVenda(requestDocument);
		
		try {
			HttpServletResponse response = getResponse();
			response.setContentType("text/javascript");
			PrintWriter out = this.getResponse().getWriter();
			out.println("$('botao_pesquisar_modelo_vendas').onclick();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "resultadoPesquisaProdutosAssociados.jsp") })
	public Forward listarProdutosAssociadosAoModelo() {
		HttpServletRequest request = getRequest();
		Integer paginaSolicitada;
		if(request.getParameter("pagina_solicitada") != null && request.getParameter("pagina_solicitada").trim().length() > 0) {
			paginaSolicitada = Integer.valueOf(request.getParameter("pagina_solicitada"));
		}else {
			paginaSolicitada = 1;
		}
		
		BuscarListaProdComModeloVendaRequestDocument requestDocument = BuscarListaProdComModeloVendaRequestDocument.Factory.newInstance();
		ParametrosBuscarListaProdComModeloVenda buscarListaProdComModeloVenda = requestDocument.addNewBuscarListaProdComModeloVendaRequest().addNewParametrosBuscarListaProdComModeloVenda();
		PaginacaoIn paginacaoIn = buscarListaProdComModeloVenda.addNewPaginacaoIn();
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		paginacaoIn.setPaginaSolicitada(paginaSolicitada);

		if(request.getParameter("id_modelo_venda") != null && request.getParameter("id_modelo_venda").trim().length() > 0) {
			buscarListaProdComModeloVenda.setIdModeloVenda(Long.valueOf(request.getParameter("id_modelo_venda")));
			request.setAttribute("idModeloVenda", request.getParameter("id_modelo_venda"));
		}
		if(request.getParameter("cd_modelo_venda") != null && request.getParameter("cd_modelo_venda").trim().length() > 0) {
			request.setAttribute("cdModeloVenda", request.getParameter("cd_modelo_venda"));
		}
		if(request.getParameter("nm_modelo_venda") != null && request.getParameter("nm_modelo_venda").trim().length() > 0) {
			request.setAttribute("nmModeloVenda", request.getParameter("nm_modelo_venda"));
		}
		
		prepareServiceControl(modeloVendaServiceControl);
		BuscarListaProdComModeloVendaResponseDocument responseDocument = modeloVendaServiceControl.buscarListaProdComModeloVenda(requestDocument);
		ResultadoBuscarListaProdComModeloVenda resultadoBuscarListaProdComModeloVenda = responseDocument.getBuscarListaProdComModeloVendaResponse().getResultadoBuscarListaProdComModeloVenda();
		PaginacaoOut paginacaoOut = resultadoBuscarListaProdComModeloVenda.getPaginacaoOut();
		tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina());
		List<ProdComModVenda> produtosAssociadosList = resultadoBuscarListaProdComModeloVenda.getListaProdComModVenda().getProdComModVendaList();
		
		if(produtosAssociadosList != null && produtosAssociadosList.size() > 0) {
			long idGrupoProduto = produtosAssociadosList.get(0).getIdGrupoProduto();
			request.setAttribute("totalDeAssociados", produtosAssociadosList.size());
			request.setAttribute("idGrupoProduto", idGrupoProduto);
			request.setAttribute("nmGrupoProduto", produtosAssociadosList.get(0).getNmGrupoProduto());
		}
		
		request.setAttribute("produtosAssociados", produtosAssociadosList);
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupNovaAssociacaoProduto.jsp") })
	public Forward novaAssociacaoProduto() {
		HttpServletRequest request = getRequest();
		int produtosAssociados = 0;
		if(request.getParameter("totalDeAssociados") != null && request.getParameter("totalDeAssociados").trim().length() > 0) {
			 produtosAssociados = Integer.valueOf(request.getParameter("totalDeAssociados"));
		}
		if(request.getParameter("id_modelo_venda") != null && request.getParameter("id_modelo_venda").trim().length() > 0) {
			request.setAttribute("idModeloVenda", request.getParameter("id_modelo_venda"));
		}
		if(request.getParameter("cd_modelo_venda") != null && request.getParameter("cd_modelo_venda").trim().length() > 0) {
			request.setAttribute("cdModeloVenda", request.getParameter("cd_modelo_venda"));
		}
		if(request.getParameter("id_grupo_produto") != null && request.getParameter("id_grupo_produto").trim().length() > 0) {
			request.setAttribute("idGrupoProduto", request.getParameter("id_grupo_produto"));
		}
		if(request.getParameter("nm_modelo_venda") != null && request.getParameter("nm_modelo_venda").trim().length() > 0) {
			request.setAttribute("nmModeloVenda", request.getParameter("nm_modelo_venda"));
		}
		
		if(produtosAssociados > 0) {
			BuscarDadosModeloVendaRequestDocument modeloVendaRequestDocument = BuscarDadosModeloVendaRequestDocument.Factory.newInstance();
			ParametrosBuscarDadosModeloVenda buscarDadosModeloVenda = modeloVendaRequestDocument.addNewBuscarDadosModeloVendaRequest().addNewParametrosBuscarDadosModeloVenda();
			PaginacaoIn paginacaoIn = buscarDadosModeloVenda.addNewPaginacaoIn();
			paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPaginaNosPopups());
			paginacaoIn.setPaginaSolicitada(1);
			if(request.getParameter("id_modelo_venda") != null && request.getParameter("id_modelo_venda").trim().length() > 0) {
				buscarDadosModeloVenda.setIdModeloVenda(Long.valueOf(request.getParameter("id_modelo_venda")));
			}
			prepareServiceControl(modeloVendaServiceControl);
			BuscarDadosModeloVendaResponseDocument modeloVendaResponseDocument = modeloVendaServiceControl.buscarDadosModeloVenda(modeloVendaRequestDocument);
			List<DadosModeloVenda> dadosModeloVendaLista = modeloVendaResponseDocument.getBuscarDadosModeloVendaResponse().getResultadoBuscarDadosModeloVenda().getListaBuscarDadosModeloVenda().getDadosModeloVendaList();
			for (DadosModeloVenda modeloVenda : dadosModeloVendaLista) {
				request.setAttribute("idFabricante", modeloVenda.getIdFabricante());
				request.setAttribute("nmFabricante", modeloVenda.getNmFabricante());
				request.setAttribute("idTipoProduto", modeloVenda.getIdTipoProduto());
				request.setAttribute("nmTipoProduto", modeloVenda.getNmTipoProduto());
				request.setAttribute("idGrupoProduto", modeloVenda.getIdGrupoProduto());
				request.setAttribute("tecnologia", modeloVenda.getListaTecnologiaPai().getTecnologiaPaiList());
				request.setAttribute("nmGrupoProduto", request.getParameter("nm_grupo_produto"));
			}
		}
		BuscarListaTipoProdutoRequestDocument tipoProdutoRequestDocument = BuscarListaTipoProdutoRequestDocument.Factory.newInstance();
		tipoProdutoRequestDocument.addNewBuscarListaTipoProdutoRequest();
		prepareServiceControl(tipoProdutoSoapServiceControl);
		BuscarListaTipoProdutoResponseDocument tipoProdutoResponseDocument = tipoProdutoSoapServiceControl.buscarListaTipoProduto(tipoProdutoRequestDocument);
		List<TipoProduto> tipoProdutoLista = tipoProdutoResponseDocument.getBuscarListaTipoProdutoResponse().getResultadoListarTipoProduto().getTipoProdutoList();
		
		BuscarListaTecnologiaRequestDocument tecnologiaRequestDocument = BuscarListaTecnologiaRequestDocument.Factory.newInstance();
		tecnologiaRequestDocument.addNewBuscarListaTecnologiaRequest();
		prepareServiceControl(tecnologiaSoapServiceControl);
		BuscarListaTecnologiaResponseDocument tecnologiaResponseDocument = tecnologiaSoapServiceControl.buscarListaTecnologia(tecnologiaRequestDocument);
		List<Tecnologia> tecnologiaLista = tecnologiaResponseDocument.getBuscarListaTecnologiaResponse().getResultadoBuscarListaTecnologia().getTecnologiaList();
		
		request.setAttribute("tipoProdutos", tipoProdutoLista);
		request.setAttribute("tecnologiaLista", tecnologiaLista);
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "resultadoPesqProdutoDispParaAssociasao.jsp") })
	public Forward pesquisarTipoProdutoSemAssociasao(br.com.vivo.catalogoPRS.pageflows.param.matrizOferta.modeloVenda.ModeloVendaMatrizOfertaController.ModeloVendaMatrizOfertaFormBean form) {
		HttpServletRequest request = getRequest();
		BuscarListaProdSemModeloVendaRequestDocument requestDocument = BuscarListaProdSemModeloVendaRequestDocument.Factory.newInstance();
		ParametrosBuscarListaProdSemModeloVenda parametrosBuscarListaProdSemModeloVenda = requestDocument.addNewBuscarListaProdSemModeloVendaRequest().addNewParametrosBuscarListaProdSemModeloVenda();
		PaginacaoIn paginacaoIn = parametrosBuscarListaProdSemModeloVenda.addNewPaginacaoIn();
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPaginaNosPopups());
		if(form.getPaginaSolicitada() != null) {
			paginacaoIn.setPaginaSolicitada(form.getPaginaSolicitada());
		}else {
			paginacaoIn.setPaginaSolicitada(1);
		}
		if(form.getIdTipoProduto() != null) {
			parametrosBuscarListaProdSemModeloVenda.setIdTipoProduto(form.getIdTipoProduto());
		}
		if(form.getIdFabricante() != null) {
			parametrosBuscarListaProdSemModeloVenda.setIdFabricante(form.getIdFabricante());
		}
		if(form.getIdTecnologia() != null) {
			parametrosBuscarListaProdSemModeloVenda.setIdTecnologia(form.getIdTecnologia());
		}
		if(form.getIdModeloProduto() != null) {
			parametrosBuscarListaProdSemModeloVenda.setIdModelo(form.getIdModeloProduto());
		}
		
		prepareServiceControl(modeloVendaServiceControl);
		BuscarListaProdSemModeloVendaResponseDocument responseDocument = modeloVendaServiceControl.buscarListaProdSemModeloVenda(requestDocument);
		ResultadoBuscarListaProdSemModeloVenda resultadoBuscarListaProdSemModeloVenda = responseDocument.getBuscarListaProdSemModeloVendaResponse().getResultadoBuscarListaProdSemModeloVenda();
		PaginacaoOut paginacaoOut = resultadoBuscarListaProdSemModeloVenda.getPaginacaoOut();
		List<ProdSemModVenda> produtosSemModVendaList = resultadoBuscarListaProdSemModeloVenda.getListaProdSemModVenda().getProdSemModVendaList();
		
		int elementoPorPagina = ApplicationConfiguration.getElementosPorPaginaNosPopups();
		int paginaAtual = paginacaoOut.getPaginaAtual();
		int totalRegistros = paginacaoOut.getTotalRegistros();
		long totalPagina = Math.round(totalRegistros / elementoPorPagina);
		if(((totalRegistros / (double) elementoPorPagina) - totalPagina) > 0) {
			totalPagina++;
		}
		
		if(form.getIdModeloVenda() != null) {
			request.setAttribute("idModeloVenda", form.getIdModeloVenda());
		}
		if(form.getCdModeloVenda() != null && form.getCdModeloVenda().trim().length() > 0) {
			request.setAttribute("nmModeloVenda", form.getNmModeloVenda());
		}
		request.setAttribute("paginaAtual", paginaAtual);
		request.setAttribute("totalPagina", totalPagina);
		request.setAttribute("totalRegistros", totalRegistros);
		request.setAttribute("produtosSemAssociacao", produtosSemModVendaList);
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action()
	public Forward efetivarAssociasaoProdutoModelo(br.com.vivo.catalogoPRS.pageflows.param.matrizOferta.modeloVenda.ModeloVendaMatrizOfertaController.ModeloVendaMatrizOfertaFormBean form) throws CatalogoPRSException {
		if( form.getIdsProdutos().length == 0 || form.getIdsProdutos() == null){
			throw new CatalogoPRSException("Por favor, selecione um ou mas produtos a associar.");
		}
		
		AssociarProdutoModeloVendaRequestDocument requestDocument = AssociarProdutoModeloVendaRequestDocument.Factory.newInstance();
		ParametrosAssociarProdutoModeloVenda parametrosAssociarProdutoModeloVenda = requestDocument.addNewAssociarProdutoModeloVendaRequest().addNewParametrosAssociarProdutoModeloVenda();
		if(form.getIdModeloVenda() != null) {
			parametrosAssociarProdutoModeloVenda.setIdModeloVenda(form.getIdModeloVenda());
		}
		if(form.getIdsProdutos() != null && form.getIdsProdutos().length > 0) {
			ListaIdProdutos produtos = parametrosAssociarProdutoModeloVenda.addNewListaIdProdutos();
			for(Long idProduto : form.getIdsProdutos()) {
				produtos.addIdProdutos(idProduto);
			}
		}
		
		prepareServiceControl(modeloVendaServiceControl);
		modeloVendaServiceControl.associarProdutoModeloVenda(requestDocument);
		
		try {
			HttpServletResponse response = getResponse();
			response.setContentType("text/javascript");
			PrintWriter out = this.getResponse().getWriter();
			out.println("$('link_listar_produtos_associados').onclick();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupConfirmAssociacaoProdutoModelo.jsp") })
	public Forward confirmAssociacaoProdutoModelo() {
		HttpServletRequest request = getRequest();
		if(request.getParameter("cd_modelo_venda") != null && request.getParameter("cd_modelo_venda").trim().length() > 0) {
			request.setAttribute("cdModeloVenda", request.getParameter("cd_modelo_venda"));
		}
		
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupConfirmRemocaoAssociacaoProduto.jsp") })
	public Forward abrirPopupConfimRemocaoAssociacao() {
		HttpServletRequest request = getRequest();
		if(request.getParameter("id_produto_associado") != null && request.getParameter("id_produto_associado").trim().length() > 0) {
			request.setAttribute("idProdutoAssociado", request.getParameter("id_produto_associado"));
		}
		
		if(request.getParameter("ds_produto") != null && request.getParameter("ds_produto").trim().length() > 0) {
			request.setAttribute("dsProduto", request.getParameter("ds_produto"));
		}
		
		if(request.getParameter("cd_modelo_venda") != null && request.getParameter("cd_modelo_venda").trim().length() > 0) {
			request.setAttribute("cdModeloVenda", request.getParameter("cd_modelo_venda"));
		}
		
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action()
	public Forward efetivarRemocaoAssociacaoProdutoModelo(br.com.vivo.catalogoPRS.pageflows.param.matrizOferta.modeloVenda.ModeloVendaMatrizOfertaController.ModeloVendaMatrizOfertaFormBean form) {
		ExcluirProdutoModeloVendaRequestDocument requestDocument = ExcluirProdutoModeloVendaRequestDocument.Factory.newInstance();
		ParametrosExcluirProdutoModeloVenda parametrosExcluirProdutoModeloVenda = requestDocument.addNewExcluirProdutoModeloVendaRequest().addNewParametrosExcluirProdutoModeloVenda();
		if(form.getIdProdutoAssociado() != null) {
			parametrosExcluirProdutoModeloVenda.setIdModeloVendaProduto(form.getIdProdutoAssociado());
		}
		prepareServiceControl(modeloVendaServiceControl);
		modeloVendaServiceControl.excluirProdutoModeloVenda(requestDocument);
		
		try {
			HttpServletResponse response = getResponse();
			response.setContentType("text/javascript");
			PrintWriter out = this.getResponse().getWriter();
			out.println("$('link_listar_produtos_associados').onclick();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onCreate() {
		super.onCreate();
		modeloVendaServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.MODELOVENDA));
		tipoProdutoSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.TIPO_PRODUTO));
		tecnologiaSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.TECNOLOGIA));
		modeloSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.GRUPO_PRODUTO));
	}

	@Override
	protected void onDestroy(HttpSession session) {
	}

	@Jpf.FormBean
	public static class ModeloVendaMatrizOfertaFormBean implements java.io.Serializable {
		private static final long serialVersionUID = 1L;
		
		private Long paginaSolicitada;
		private Long idModeloVenda;
		private Long idTipoProduto;
		private Long idFabricante;
		private Long idTecnologia;
		private Long idModeloProduto;
		private Long idProdutoAssociado;
		private String codigoProduto;
		private String nmTipoProduto;
		private String cdModeloVenda;
		private String nmModeloVenda;
		private Long[] idsProdutos;
		
		public Long[] getIdsProdutos() {
			return idsProdutos;
		}
		public void setIdsProdutos(Long[] idsProdutos) {
			this.idsProdutos = idsProdutos;
		}
		public String getCdModeloVenda() {
			return cdModeloVenda;
		}
		public void setCdModeloVenda(String cdModeloVenda) {
			this.cdModeloVenda = cdModeloVenda;
		}
		public Long getIdModeloVenda() {
			return idModeloVenda;
		}
		public void setIdModeloVenda(Long idModeloVenda) {
			this.idModeloVenda = idModeloVenda;
		}
		public Long getIdTipoProduto() {
			return idTipoProduto;
		}
		public void setIdTipoProduto(Long idTipoProduto) {
			this.idTipoProduto = idTipoProduto;
		}
		public String getNmModeloVenda() {
			return nmModeloVenda;
		}
		public void setNmModeloVenda(String nmModeloVenda) {
			this.nmModeloVenda = nmModeloVenda;
		}
		public String getNmTipoProduto() {
			return nmTipoProduto;
		}
		public void setNmTipoProduto(String nmTipoProduto) {
			this.nmTipoProduto = nmTipoProduto;
		}
		public Long getPaginaSolicitada() {
			return paginaSolicitada;
		}
		public void setPaginaSolicitada(Long paginaSolicitada) {
			this.paginaSolicitada = paginaSolicitada;
		}
		public Long getIdFabricante() {
			return idFabricante;
		}
		public void setIdFabricante(Long idFabricante) {
			this.idFabricante = idFabricante;
		}
		public Long getIdTecnologia() {
			return idTecnologia;
		}
		public void setIdTecnologia(Long idTecnologia) {
			this.idTecnologia = idTecnologia;
		}
		public Long getIdModeloProduto() {
			return idModeloProduto;
		}
		public void setIdModeloProduto(Long idModeloProduto) {
			this.idModeloProduto = idModeloProduto;
		}
		public String getCodigoProduto() {
			return codigoProduto;
		}
		public void setCodigoProduto(String codigoProduto) {
			this.codigoProduto = codigoProduto;
		}
		public Long getIdProdutoAssociado() {
			return idProdutoAssociado;
		}
		public void setIdProdutoAssociado(Long idProdutoAssociado) {
			this.idProdutoAssociado = idProdutoAssociado;
		}
	
	}*/
}