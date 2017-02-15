package br.com.vivo.catalogoPRS.pageflows.param.produtos.produtos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*import weblogic.utils.StringUtils;*/

import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.services.TecnologiaService;
import br.com.vivo.catalogoPRS.services.TipoProdutoService;
import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;
import br.com.vivo.catalogoPRS.util.WebServicesConfiguration;
/*import br.com.vivo.sn.catalogoModelo.BuscarListaModeloDispComFabricanteRequestDocument;
import br.com.vivo.sn.catalogoModelo.BuscarListaModeloDispComFabricanteResponseDocument;
import br.com.vivo.sn.catalogoModelo.ModeloDocument.Modelo;
import br.com.vivo.sn.catalogoModelo.PaginacaoOutDocument.PaginacaoOut;
import br.com.vivo.sn.catalogoModelo.ParametrosBuscarListaModeloDispComFabricanteDocument.ParametrosBuscarListaModeloDispComFabricante.ParametrosModelosDisponiveisComFabricanteIn;
import br.com.vivo.sn.catalogoModelo.ResultadoBuscarListaModeloDispComFabricanteDocument.ResultadoBuscarListaModeloDispComFabricante.RaizGrupoProdutoOut;
import br.com.vivo.sn.catalogoModelo.ResultadoBuscarListaModeloDispComFabricanteDocument.ResultadoBuscarListaModeloDispComFabricante.RaizGrupoProdutoOut.ListaGrupoProduto;
import br.com.vivo.sn.catalogoProduto.AlterarDescricaoCatalogoRequestDocument;
import br.com.vivo.sn.catalogoProduto.AssociarProdModeloHabilitarVariaveisRequestDocument;
import br.com.vivo.sn.catalogoProduto.AtualizaCorProdutosRequestDocument;
import br.com.vivo.sn.catalogoProduto.BuscarListaCoresPorModeloRequestDocument;
import br.com.vivo.sn.catalogoProduto.BuscarListaCoresPorModeloResponseDocument;
import br.com.vivo.sn.catalogoProduto.BuscarListaProdutoRequestDocument;
import br.com.vivo.sn.catalogoProduto.BuscarListaProdutoSemCodigoAssociadoRequestDocument;
import br.com.vivo.sn.catalogoProduto.BuscarListaProdutoSemCodigoAssociadoResponseDocument;
import br.com.vivo.sn.catalogoProduto.DesassociarProdModDesabilitarVariaveisRequestDocument;
import br.com.vivo.sn.catalogoProduto.DisponibilizarProdutoRequestDocument;
import br.com.vivo.sn.catalogoProduto.AssociarProdModeloHabilitarVariaveisRequestDocument.AssociarProdModeloHabilitarVariaveisRequest;
import br.com.vivo.sn.catalogoProduto.DesassociarProdModDesabilitarVariaveisRequestDocument.DesassociarProdModDesabilitarVariaveisRequest;
import br.com.vivo.sn.catalogoProduto.ParametrosAlterarDescricaoCatalogoDocument.ParametrosAlterarDescricaoCatalogo.DescricaoCatalogo;
import br.com.vivo.sn.catalogoProduto.ParametrosAssociaProdutoModeloDocument.ParametrosAssociaProdutoModelo;
import br.com.vivo.sn.catalogoProduto.ParametrosAssociaProdutoModeloDocument.ParametrosAssociaProdutoModelo.ProdutoModeloAssociacao;
import br.com.vivo.sn.catalogoProduto.ParametrosAssociaProdutoModeloDocument.ParametrosAssociaProdutoModelo.ProdutoModeloAssociacao.ListaProdutoIn;
import br.com.vivo.sn.catalogoProduto.ParametrosDesabilitarVariaveisDocument.ParametrosDesabilitarVariaveis;
import br.com.vivo.sn.catalogoProduto.ParametrosDesabilitarVariaveisDocument.ParametrosDesabilitarVariaveis.ListaDesassociaProdutoModelo;
import br.com.vivo.sn.catalogoProduto.ParametrosDesabilitarVariaveisDocument.ParametrosDesabilitarVariaveis.ListaDesassociaProdutoModelo.DesassociacaoProdutoModelo;
import br.com.vivo.sn.catalogoProduto.ParametrosDesassociarProdutoModeloDocument.ParametrosDesassociarProdutoModelo;
import br.com.vivo.sn.catalogoProduto.ParametrosDesassociarProdutoModeloDocument.ParametrosDesassociarProdutoModelo.ListaProdutoModeloDesassociacao;
import br.com.vivo.sn.catalogoProduto.ParametrosDesassociarProdutoModeloDocument.ParametrosDesassociarProdutoModelo.ListaProdutoModeloDesassociacao.ProdutoModeloDesassociacao;
import br.com.vivo.sn.catalogoProduto.ParametrosDisponibilizarProdutoDocument.ParametrosDisponibilizarProduto.ParametrosDispProduto;
import br.com.vivo.sn.catalogoProduto.ParametrosHabilitarVariaveisDocument.ParametrosHabilitarVariaveis;
import br.com.vivo.sn.catalogoProduto.ParametrosHabilitarVariaveisDocument.ParametrosHabilitarVariaveis.ListaAssociaProdutoModelo;
import br.com.vivo.sn.catalogoProduto.ParametrosHabilitarVariaveisDocument.ParametrosHabilitarVariaveis.ListaAssociaProdutoModelo.HabilitaVariaveis;
import br.com.vivo.sn.catalogoProduto.ParametrosProdutosInDocument.ParametrosProdutosIn;
import br.com.vivo.sn.catalogoProduto.ResultadoBuscarListaCoresPorModeloDocument.ResultadoBuscarListaCoresPorModelo.ListaCores.Cor;
import br.com.vivo.sn.catalogoProduto.ResultadoBuscarListaProdutoDocument.ResultadoBuscarListaProduto;
import br.com.vivo.sn.catalogoProduto.ResultadoBuscarListaProdutoSemCodigoAssociadoDocument.ResultadoBuscarListaProdutoSemCodigoAssociado;
import br.com.vivo.sn.catalogoProduto.ResultadoBuscarListaProdutoSemCodigoAssociadoDocument.ResultadoBuscarListaProdutoSemCodigoAssociado.ListaProduto;
import br.com.vivo.sn.catalogoProduto.ResultadoBuscarListaProdutoSemCodigoAssociadoDocument.ResultadoBuscarListaProdutoSemCodigoAssociado.ListaProduto.Produto;
import br.com.vivo.sn.catalogoTecnologia.ResultadoBuscarListaTecnologiaDocument.ResultadoBuscarListaTecnologia.Tecnologia;
import br.com.vivo.sn.catalogoTipoProduto.BuscarListaTipoProdutoPorFabricanteRequestDocument;
import br.com.vivo.sn.catalogoTipoProduto.ResultadoListarTipoProdutoDocument.ResultadoListarTipoProduto.TipoProduto;
*/
public class ProdutosController extends BaseMappingDispatchAction {
/*	private static final long serialVersionUID = 1L;
	
	private Long idCor;

	@Control
	private TipoProdutoSoapServiceControl tipoProdutoSoapServiceControl;

	@Control
	private TecnologiaSoapServiceControl tecnologiaSoapServiceControl;

	@Control
	private ProdutosSoapServiceControl produtosSoapServiceControl;

	@Control
	private ModeloSoapServiceControl grupoProdutoSoapServiceControl;

	@Jpf.Action(forwards = { @Jpf.Forward(name = "default", path = "paramProdutos.jsp") })
	public Forward begin() {
		HttpServletRequest request = getRequest();
		
		prepareServiceControl(tipoProdutoSoapServiceControl);
		List<TipoProduto> tipoProdutoList = TipoProdutoService.getInstance().buscarListaTipoProduto(tipoProdutoSoapServiceControl);
		request.setAttribute("tipos_produto", tipoProdutoList);

		prepareServiceControl(tecnologiaSoapServiceControl);
		List<Tecnologia> tecnologiaList = TecnologiaService.getInstance().buscarListaTecnologia(tecnologiaSoapServiceControl);
		request.setAttribute("tecnologias", tecnologiaList);

		return new Forward("default");
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "resultadoPesquisarProdutos.jsp") }, validationErrorForward = @Jpf.Forward(name = "fail", navigateTo = Jpf.NavigateTo.previousAction))
	public Forward pesquisarProdutos(br.com.vivo.catalogoPRS.pageflows.param.produtos.produtos.ProdutosController.PesquisarProdutosFormBean form) {

		HttpServletRequest request = getRequest();

		BuscarListaProdutoRequestDocument paramListarProdutos = BuscarListaProdutoRequestDocument.Factory.newInstance();

		BuscarListaProdutoSemCodigoAssociadoRequestDocument paramListarProdutosSemCodigoAssociado = BuscarListaProdutoSemCodigoAssociadoRequestDocument.Factory.newInstance();

		ParametrosProdutosIn produtosIn;
		if (form.getCodigoAssociado().equalsIgnoreCase("nao"))
			produtosIn = paramListarProdutosSemCodigoAssociado.addNewBuscarListaProdutoSemCodigoAssociadoRequest().addNewParametrosBuscarListaProdutoSemCodigoAssociado().addNewParametrosProdutosIn();
		else
			produtosIn = paramListarProdutos.addNewBuscarListaProdutoRequest().addNewParametrosBuscarListaProduto().addNewParametrosProdutosIn();

		request.setAttribute("codigo_associado", form.getCodigoAssociado());

		if (form.getFabricante() != null) {
			produtosIn.setIdFabricante(form.getFabricante());
			request.setAttribute("id_fabricante", form.getFabricante());
		}
		if (form.getTecnologia() != null) {
			produtosIn.setIdTecnologia(form.getTecnologia());
			request.setAttribute("id_tecnologia", form.getTecnologia());
		}
		
		if(form.getIdModelo() != null){
			produtosIn.setIdModelo(form.getIdModelo());
		}
		
		if(form.getCodigoProduto() != null){
			produtosIn.setCdCodigo(form.getCodigoProduto());
		}
		produtosIn.setIdTipoProduto(form.getTipoProduto());
		request.setAttribute("id_tipo_produto", form.getTipoProduto());
		br.com.vivo.sn.catalogoProduto.PaginacaoInDocument.PaginacaoIn paginacaoIn = produtosIn.addNewPaginacaoIn();
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		if (form.getPaginaSolicitada() != null)
			paginacaoIn.setPaginaSolicitada(form.getPaginaSolicitada());
		else
			paginacaoIn.setPaginaSolicitada(1);

		prepareServiceControl(produtosSoapServiceControl);
		
		if (form.getCodigoAssociado().equals("nao")){
			BuscarListaProdutoSemCodigoAssociadoResponseDocument buscarListaProdutoSemCodigoAssociadoResponseDocument = produtosSoapServiceControl.buscarListaProdutoSemCodigoAssociado(paramListarProdutosSemCodigoAssociado);
			ResultadoBuscarListaProdutoSemCodigoAssociado resultadoBuscarListaProdutoSemCodigoAssociado = buscarListaProdutoSemCodigoAssociadoResponseDocument.getBuscarListaProdutoSemCodigoAssociadoResponse().getResultadoBuscarListaProdutoSemCodigoAssociado();
			ListaProduto listaProduto = resultadoBuscarListaProdutoSemCodigoAssociado.getListaProduto();
			
			if (listaProduto != null) {
				List<Produto> produtoList = listaProduto.getProdutoList();
				br.com.vivo.sn.catalogoProduto.PaginacaoOutDocument.PaginacaoOut paginacaoOut = resultadoBuscarListaProdutoSemCodigoAssociado.getPaginacaoOut();
				tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina());
				request.setAttribute("produtos", produtoList);
			}
		}
		else{
			ResultadoBuscarListaProduto resultadoBuscarListaProduto = produtosSoapServiceControl.buscarListaProduto(paramListarProdutos).getBuscarListaProdutoResponse().getResultadoBuscarListaProduto();
			br.com.vivo.sn.catalogoProduto.ResultadoBuscarListaProdutoDocument.ResultadoBuscarListaProduto.ListaProduto listaProduto = resultadoBuscarListaProduto.getListaProduto();
			if (listaProduto != null) {
				List<br.com.vivo.sn.catalogoProduto.ResultadoBuscarListaProdutoDocument.ResultadoBuscarListaProduto.ListaProduto.Produto> produtoList = listaProduto.getProdutoList();
				br.com.vivo.sn.catalogoProduto.PaginacaoOutDocument.PaginacaoOut paginacaoOut = resultadoBuscarListaProduto.getPaginacaoOut();
				tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina());
				request.setAttribute("produtos", produtoList);
			}
		}

		

		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupComposicao.jsp") })
	public Forward popupComposicao() {
		forwardParameter("dsProduto");
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action()
	public Forward desassociarProdutosModelos(
			br.com.vivo.catalogoPRS.pageflows.param.produtos.produtos.ProdutosController.DesassociarProdutosModelosFormBean form) throws IOException,
			CatalogoPRSException {

		if (form.getIdsProdutoGrupoProduto() == null || form.getIdsProdutoGrupoProduto().length == 0)
			throw new CatalogoPRSException("Por favor, selecione os produtos e modelos a desassociar.");

		DesassociarProdModDesabilitarVariaveisRequestDocument desassociarProdModDesabilitarVariaveisRequestDocument = DesassociarProdModDesabilitarVariaveisRequestDocument.Factory.newInstance();
		DesassociarProdModDesabilitarVariaveisRequest desassociarProdModDesabilitarVariaveisRequest = desassociarProdModDesabilitarVariaveisRequestDocument.addNewDesassociarProdModDesabilitarVariaveisRequest();
		
		ParametrosDesabilitarVariaveis parametrosDesabilitarVariaveis = desassociarProdModDesabilitarVariaveisRequest.addNewParametrosDesabilitarVariaveis();
		ParametrosDesassociarProdutoModelo parametrosDesassociarProdutoModelo = desassociarProdModDesabilitarVariaveisRequest.addNewParametrosDesassociarProdutoModelo();
		
		ListaProdutoModeloDesassociacao listaProdutoModeloDesassociacao = parametrosDesassociarProdutoModelo.addNewListaProdutoModeloDesassociacao();
		for (Long idProdutoGrupoProduto : form.getIdsProdutoGrupoProduto()) {
			ProdutoModeloDesassociacao produtoModeloDesassociacao = listaProdutoModeloDesassociacao.addNewProdutoModeloDesassociacao();
			produtoModeloDesassociacao.setIdProdutoGrupoProduto(idProdutoGrupoProduto);
		}

		ListaDesassociaProdutoModelo listaDesassociaProdutoModelo = parametrosDesabilitarVariaveis.addNewListaDesassociaProdutoModelo();
		for (int i = 0; i < form.getIdsProdutoGrupoProduto().length; i++) {
			DesassociacaoProdutoModelo desassociacaoProdutoModelo = listaDesassociaProdutoModelo.addNewDesassociacaoProdutoModelo();
			desassociacaoProdutoModelo.setIdGrupoProduto(form.getIdsGrupoProduto()[i]);
			desassociacaoProdutoModelo.setIdProduto(form.getIdsProduto()[i]);
			desassociacaoProdutoModelo.setIdProdutoGrupoProduto(form.getIdsProdutoGrupoProduto()[i]);
		}

		AtualizaCorProdutosRequestDocument atlPrd = AtualizaCorProdutosRequestDocument.Factory.newInstance();
		atlPrd.addNewAtualizaCorProdutosRequest().addNewParametrosAtualizaCorProdutos().addNewListaProdutos().setIdProdutoArray(this.toStringArray(form.getIdsProduto()));
		
		prepareServiceControl(produtosSoapServiceControl);
		produtosSoapServiceControl.desassociarProdModDesabilitarVariaveis(desassociarProdModDesabilitarVariaveisRequestDocument);
		produtosSoapServiceControl.atualizaCorProdutos(atlPrd);

		writeJSOutput("clearAndShow('resultado_pesquisa');");
		writeJSOutput("$('botao_pesquisar_produtos').onclick();");
		return null;
	}
	
	private String[] toStringArray(Long longs[]) {
		String[] els = new String[longs.length];
		for (int i = 0; i < els.length; i++) {
			els[i] = longs[i].toString();
		}
		return els;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupDesassociarProdutosModelos.jsp") })
	public Forward popupDesassociarProdutosModelos() throws CatalogoPRSException {
		Set set = getRequest().getParameterMap().keySet();
		List<String> checkBoxProdutos = new ArrayList<String>();
		List<String> checkBoxGruposProduto = new ArrayList<String>();
		List<String> checkProdutosGruposProduto = new ArrayList<String>();
		for (Object object : set) {
			String key = (String) object;
			if (key.startsWith("checkbox_produto_")) {
				checkBoxProdutos.add(getRequest().getParameter(key).substring("produto_".length()));
			} else {
				if (key.startsWith("checkbox_grupoproduto_")) {
					checkBoxGruposProduto.add(getRequest().getParameter(key).substring("grupoproduto_".length()));
				} else {
					if (key.startsWith("checkbox_produtogrupoproduto_")) {
						checkProdutosGruposProduto.add(getRequest().getParameter(key).substring("produtogrupoproduto_".length()));
					}
				}
			}
		}
		if(checkBoxGruposProduto.size() == 0 && checkBoxGruposProduto.size() == 0 && checkProdutosGruposProduto.size() == 0){
			throw new CatalogoPRSException("Por favor, selecione os produtos e modelos a desassociar.");
		}
		getRequest().setAttribute("ids_produto", checkBoxProdutos);
		getRequest().setAttribute("ids_grupo_produto", checkBoxGruposProduto);
		getRequest().setAttribute("ids_produto_grupo_produto", checkProdutosGruposProduto);
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "listaModelosCompativeis.jsp") })
	public Forward listarModelosCompativeis() throws CatalogoPRSException {

		HttpServletRequest request = getRequest();
		String idTipoProdutoString = request.getParameter("id_tipo_produto");
		String idFabricanteString = request.getParameter("id_fabricante");
		String idTecnologiaString = request.getParameter("id_tecnologia");
		String paginaSolicitadaString = request.getParameter("pagina_solicitada");

		forwardParameter("id_tipo_produto");
		forwardParameter("id_fabricante");
		forwardParameter("id_tecnologia");
		forwardParameter("codigo_associado");

		BuscarListaModeloDispComFabricanteRequestDocument paramListarModelosDispComfabricante = BuscarListaModeloDispComFabricanteRequestDocument.Factory.newInstance();
		ParametrosModelosDisponiveisComFabricanteIn parametrosModelosDisponiveisComFabricanteIn = paramListarModelosDispComfabricante.addNewBuscarListaModeloDispComFabricanteRequest().addNewParametrosBuscarListaModeloDispComFabricante().addNewParametrosModelosDisponiveisComFabricanteIn();
		br.com.vivo.sn.catalogoModelo.PaginacaoInDocument.PaginacaoIn paginacaoIn = parametrosModelosDisponiveisComFabricanteIn.addNewPaginacaoIn();
		paginacaoIn.setItensPorPagina(ApplicationConfiguration.getElementosPorPagina());
		
		if (StringUtils.isEmptyString(paginaSolicitadaString))
			paginacaoIn.setPaginaSolicitada(1);
		else
			paginacaoIn.setPaginaSolicitada(Integer.valueOf(paginaSolicitadaString));

		if (StringUtils.isEmptyString(idTipoProdutoString))
			throw new CatalogoPRSException("Ocorreu um erro a listar os modelos compativeis.");
		
		parametrosModelosDisponiveisComFabricanteIn.setIdTipoProduto(Long.valueOf(idTipoProdutoString));

		if (!StringUtils.isEmptyString(idFabricanteString))
			parametrosModelosDisponiveisComFabricanteIn.setIdFabricante(Long.valueOf(idFabricanteString));
		
		if (!StringUtils.isEmptyString(idTecnologiaString))
			parametrosModelosDisponiveisComFabricanteIn.setIdTecnologia(Long.valueOf(idTecnologiaString));

		prepareServiceControl(grupoProdutoSoapServiceControl);
		BuscarListaModeloDispComFabricanteResponseDocument buscarListaModeloDispComFabricanteResponseDocument = grupoProdutoSoapServiceControl
				.buscarListaModeloDispComFabricante(paramListarModelosDispComfabricante);
		
		RaizGrupoProdutoOut raizGrupoProdutoOut = buscarListaModeloDispComFabricanteResponseDocument.getBuscarListaModeloDispComFabricanteResponse().getResultadoBuscarListaModeloDispComFabricante().getRaizGrupoProdutoOut();
		PaginacaoOut paginacaoOut = raizGrupoProdutoOut.getPaginacaoOut();
		tratarPaginacao(paginacaoOut, ApplicationConfiguration.getElementosPorPagina());
		ListaGrupoProduto listaGrupoProduto = raizGrupoProdutoOut.getListaGrupoProduto();
		List<Modelo> grupoProdutoList;
		
		if (listaGrupoProduto != null) {
			grupoProdutoList = listaGrupoProduto.getModeloList();
		} else {
			grupoProdutoList = new ArrayList<Modelo>();
		}
		
		request.setAttribute("modelos", grupoProdutoList);
		request.setAttribute("size_modelos", paginacaoOut.getTotalRegistros());

		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action()
	public Forward associarProdutoModelo(br.com.vivo.catalogoPRS.pageflows.param.produtos.produtos.ProdutosController.AssociarProdutosModelosFormBean form)
			throws CatalogoPRSException, IOException {
		if (form.getIdsGruposProdutos() == null || form.getIdsGruposProdutos().length < 1 || form.getIdsProdutos() == null || form.getIdsProdutos().length < 1)
			throw new CatalogoPRSException("Por favor, selecione os produtos e modelos a associar.");
		
		String idCor = this.getRequest().getParameter("id_cor_request");
		AtualizaCorProdutosRequestDocument atlPrd = AtualizaCorProdutosRequestDocument.Factory.newInstance();
		atlPrd.addNewAtualizaCorProdutosRequest().addNewParametrosAtualizaCorProdutos().addNewListaProdutos().setIdProdutoArray(form.getIdsProdutos());
		
		if(!idCor.equals(""))
			atlPrd.getAtualizaCorProdutosRequest().getParametrosAtualizaCorProdutos().setIdCor(Long.parseLong(idCor));
		
		AssociarProdModeloHabilitarVariaveisRequestDocument associarProdModeloHabilitarVariaveisRequestDocument = AssociarProdModeloHabilitarVariaveisRequestDocument.Factory.newInstance();
		AssociarProdModeloHabilitarVariaveisRequest associarProdModeloHabilitarVariaveisRequest = associarProdModeloHabilitarVariaveisRequestDocument.addNewAssociarProdModeloHabilitarVariaveisRequest();
		ParametrosAssociaProdutoModelo parametrosAssociaProdutoModelo = associarProdModeloHabilitarVariaveisRequest.addNewParametrosAssociaProdutoModelo();
		ParametrosHabilitarVariaveis parametrosHabilitarVariaveis = associarProdModeloHabilitarVariaveisRequest.addNewParametrosHabilitarVariaveis();
		
		if (form.getCodigoAssociado().trim().equalsIgnoreCase("nao")) {
			ProdutoModeloAssociacao produtoModeloAssociacao = parametrosAssociaProdutoModelo.addNewProdutoModeloAssociacao();
			for (Long idGrupoProduto : form.getIdsGruposProdutos()) {
				for (String idProduto : form.getIdsProdutos()) {
					ListaProdutoIn listaProdutoIn = produtoModeloAssociacao.addNewListaProdutoIn();
					listaProdutoIn.setIdGrupoProduto(idGrupoProduto);
					listaProdutoIn.setIdProduto(Long.valueOf(idProduto));
				}
			}
			
			ListaAssociaProdutoModelo listaAssociaProdutoModelo = parametrosHabilitarVariaveis.addNewListaAssociaProdutoModelo();
			for (Long idGrupoProduto : form.getIdsGruposProdutos()) {
				for (String idProduto : form.getIdsProdutos()) {
					HabilitaVariaveis habilitaVariaveis = listaAssociaProdutoModelo.addNewHabilitaVariaveis();
					habilitaVariaveis.setIdGrupoProduto(idGrupoProduto);
					habilitaVariaveis.setIdProduto(Long.valueOf(idProduto));
				}
			}

			prepareServiceControl(produtosSoapServiceControl);
			produtosSoapServiceControl.associarProdModeloHabilitarVariaveis(associarProdModeloHabilitarVariaveisRequestDocument);
			produtosSoapServiceControl.atualizaCorProdutos(atlPrd);
			
		} else {
			
			DesassociarProdModDesabilitarVariaveisRequestDocument desassociarProdModDesabilitarVariaveisRequestDocument = DesassociarProdModDesabilitarVariaveisRequestDocument.Factory.newInstance();
			DesassociarProdModDesabilitarVariaveisRequest desassociarProdModDesabilitarVariaveisRequest = desassociarProdModDesabilitarVariaveisRequestDocument.addNewDesassociarProdModDesabilitarVariaveisRequest();
			ParametrosDesabilitarVariaveis parametrosDesabilitarVariaveis = desassociarProdModDesabilitarVariaveisRequest.addNewParametrosDesabilitarVariaveis();
			ParametrosDesassociarProdutoModelo parametrosDesassociarProdutoModelo = desassociarProdModDesabilitarVariaveisRequest.addNewParametrosDesassociarProdutoModelo();
			
			Long[] idsProdutos = extrairIdsComPrefix(form.getIdsProdutos(), "produto_");
			Long[] idsProdutosGruposProdutos = extrairIdsComPrefix(form.getIdsProdutos(), "produtogrupoproduto_");
			Long[] idsGruposProdutos = extrairIdsComPrefix(form.getIdsProdutos(), "grupoproduto_");
			
			atlPrd.getAtualizaCorProdutosRequest().getParametrosAtualizaCorProdutos().getListaProdutos().setIdProdutoArray(toStringArray(idsGruposProdutos));
			AtualizaCorProdutosRequestDocument atlPrdDes = AtualizaCorProdutosRequestDocument.Factory.newInstance();
			atlPrdDes.addNewAtualizaCorProdutosRequest().addNewParametrosAtualizaCorProdutos().addNewListaProdutos().setIdProdutoArray(extrairIdsComPrefixS(form.getIdsProdutos(), "produto_"));
			
			ListaProdutoModeloDesassociacao listaProdutoModeloDesassociacao = parametrosDesassociarProdutoModelo.addNewListaProdutoModeloDesassociacao();
			for (Long idProdutoGrupoProduto : idsProdutosGruposProdutos) {
				ProdutoModeloDesassociacao produtoModeloDesassociacao = listaProdutoModeloDesassociacao.addNewProdutoModeloDesassociacao();
				produtoModeloDesassociacao.setIdProdutoGrupoProduto(idProdutoGrupoProduto);
			}

			ListaDesassociaProdutoModelo listaDesassociaProdutoModelo = parametrosDesabilitarVariaveis.addNewListaDesassociaProdutoModelo();
			for (int i = 0; i < idsProdutos.length; i++) {
				DesassociacaoProdutoModelo desassociacaoProdutoModelo = listaDesassociaProdutoModelo.addNewDesassociacaoProdutoModelo();
				desassociacaoProdutoModelo.setIdGrupoProduto(idsGruposProdutos[i]);
				desassociacaoProdutoModelo.setIdProduto(idsProdutos[i]);
				desassociacaoProdutoModelo.setIdProdutoGrupoProduto(idsProdutosGruposProdutos[i]);
			}

			prepareServiceControl(produtosSoapServiceControl);
			produtosSoapServiceControl.desassociarProdModDesabilitarVariaveis(desassociarProdModDesabilitarVariaveisRequestDocument);
			produtosSoapServiceControl.atualizaCorProdutos(atlPrdDes);
			
			AssociarProdModeloHabilitarVariaveisRequestDocument associarProdModeloHabilitarVariaveisRequestDocument2 = AssociarProdModeloHabilitarVariaveisRequestDocument.Factory.newInstance();
			AssociarProdModeloHabilitarVariaveisRequest associarProdModeloHabilitarVariaveisRequest2 = associarProdModeloHabilitarVariaveisRequestDocument2.addNewAssociarProdModeloHabilitarVariaveisRequest();
			ParametrosAssociaProdutoModelo parametrosAssociaProdutoModelo2 = associarProdModeloHabilitarVariaveisRequest2.addNewParametrosAssociaProdutoModelo();
			ParametrosHabilitarVariaveis parametrosHabilitarVariaveis2 = associarProdModeloHabilitarVariaveisRequest2.addNewParametrosHabilitarVariaveis();
			
			ProdutoModeloAssociacao produtoModeloAssociacao = parametrosAssociaProdutoModelo2.addNewProdutoModeloAssociacao();
			for (Long idGrupoProduto : form.getIdsGruposProdutos()) {
				for (Long idProduto : idsProdutos) {
					ListaProdutoIn listaProdutoIn = produtoModeloAssociacao.addNewListaProdutoIn();
					listaProdutoIn.setIdGrupoProduto(idGrupoProduto);
					listaProdutoIn.setIdProduto(idProduto);
				}
			}

			ListaAssociaProdutoModelo listaAssociaProdutoModelo = parametrosHabilitarVariaveis2.addNewListaAssociaProdutoModelo();
			for (Long idGrupoProduto : form.getIdsGruposProdutos()) {
				for (Long idProduto : idsProdutos) {
					HabilitaVariaveis habilitaVariaveis = listaAssociaProdutoModelo.addNewHabilitaVariaveis();
					habilitaVariaveis.setIdGrupoProduto(idGrupoProduto);
					habilitaVariaveis.setIdProduto(idProduto);
				}
			}
			
			if(!idCor.equals(""))
				atlPrdDes.getAtualizaCorProdutosRequest().getParametrosAtualizaCorProdutos().setIdCor(Long.parseLong(idCor));

			prepareServiceControl(produtosSoapServiceControl);
			produtosSoapServiceControl.associarProdModeloHabilitarVariaveis(associarProdModeloHabilitarVariaveisRequestDocument2);
			produtosSoapServiceControl.atualizaCorProdutos(atlPrdDes);
			
		}

		writeJSOutput("$('botao_pesquisar_produtos').onclick();");
		return null;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupAlterarNomeProduto.jsp") })
	public Forward popupAlterarNomeProduto() {
		
		HttpServletRequest request = getRequest();
		forwardParameter("id_produto");
		forwardParameter("codigo_sap");
		forwardParameter("nome_comercial");
		forwardParameter("id_tipo_produto");
		forwardParameter("id_grupo_produto");
		forwardParameter("tipo_produto");
		forwardParameter("descricao_sap");
		forwardParameter("composicao");
		forwardParameter("destaque_produto");
		forwardParameter("id_cor");
		forwardParameter("cor_produto");
		forwardParameter("rgb_produto");
		forwardParameter("id_tipoProduto");
		forwardParameter("indicador_modelo");
		
		BuscarListaTipoProdutoPorFabricanteRequestDocument fabricante = BuscarListaTipoProdutoPorFabricanteRequestDocument.Factory.newInstance();
		fabricante.addNewBuscarListaTipoProdutoPorFabricanteRequest().addNewParamTipoProdutoPorFabricante().setIdFabricante(Long.parseLong(request.getParameter("id_fabricante")));
		
		prepareServiceControl(tipoProdutoSoapServiceControl);
		List<TipoProduto> tipoProdutoList = tipoProdutoSoapServiceControl.buscarListaTipoProdutoPorFabricante(fabricante).getBuscarListaTipoProdutoPorFabricanteResponse().getResultadoListarTipoProduto().getTipoProdutoList();
		request.setAttribute("tipoProdutoLista", tipoProdutoList);
		
		BuscarListaCoresPorModeloRequestDocument paramModelo = BuscarListaCoresPorModeloRequestDocument.Factory.newInstance();	
		paramModelo.addNewBuscarListaCoresPorModeloRequest().addNewParametroBuscarListaCoresPorModelo().setIdModelo(Long.parseLong(request.getParameter("id_grupo_produto")));
		prepareServiceControl(produtosSoapServiceControl);
		BuscarListaCoresPorModeloResponseDocument cores =  produtosSoapServiceControl.buscarListaCoresPorModelo(paramModelo);
		Collection<Cor> listaCores = cores.getBuscarListaCoresPorModeloResponse().getResultadoBuscarListaCoresPorModelo().getListaCores().getCorList();
		request.setAttribute("listaCores", listaCores);
		
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action()
	public Forward alterarNomeProduto(br.com.vivo.catalogoPRS.pageflows.param.produtos.produtos.ProdutosController.AlterarNomeProdutoFormBean form)
			throws IOException {

		AlterarDescricaoCatalogoRequestDocument paramAlterarDescricaoCatalogo = AlterarDescricaoCatalogoRequestDocument.Factory.newInstance();
		DescricaoCatalogo descricaoCatalogo = paramAlterarDescricaoCatalogo.addNewAlterarDescricaoCatalogoRequest().addNewParametrosAlterarDescricaoCatalogo().addNewDescricaoCatalogo();
		
		if(form.getIdProduto() != null && !form.getIdProduto().equals("")) {
			descricaoCatalogo.setIdProduto(form.getIdProduto());
		}
		if(form.getNomeComercialCadastro() != null && !form.getNomeComercialCadastro().equals("")) {
			descricaoCatalogo.setNmProduto(form.getNomeComercialCadastro());
		}
		if(form.getIdTipoProdutoCadastro() != null && !form.getIdTipoProdutoCadastro().equals("") && !form.getIndModelo().equals("S")) {
			descricaoCatalogo.setIdTipoProduto(Long.parseLong(form.getIdTipoProdutoCadastro()));
		}
		if(form.getDestaqueProdutoCadastro() != null && !form.getDestaqueProdutoCadastro().equals("")) {
			descricaoCatalogo.setDsNota(form.getDestaqueProdutoCadastro());
		}
		if(form.getIdCorCadastro() != null && !form.getIdCorCadastro().equals("")) {
			descricaoCatalogo.setIdCor(form.getIdCorCadastro());
		}

		prepareServiceControl(produtosSoapServiceControl);
		produtosSoapServiceControl.alterarDescricaoCatalogo(paramAlterarDescricaoCatalogo);
		writeJSOutput("$('botao_pesquisar_produtos').onclick();");
		return null;
	}
	
	@Jpf.Action()
	public Forward alterarDispProduto() throws IOException {
		HttpServletRequest request = getRequest();
		String idProduto = request.getParameter("id_produto");

		DisponibilizarProdutoRequestDocument paramDisponibilizarProduto = DisponibilizarProdutoRequestDocument.Factory.newInstance();
		ParametrosDispProduto parametrosDispProduto = paramDisponibilizarProduto.addNewDisponibilizarProdutoRequest().addNewParametrosDisponibilizarProduto().addNewParametrosDispProduto();
		parametrosDispProduto.setIdProduto(Long.valueOf(idProduto));
		parametrosDispProduto.setIndisponivel(request.getParameter("checked"));

		prepareServiceControl(produtosSoapServiceControl);
		produtosSoapServiceControl.disponibilizarProduto(paramDisponibilizarProduto);

		writeJSOutput("$('disp_" + idProduto + "').checked=!$('disp_" + idProduto + "').checked;");

		return null;
	}

	@Override
	protected void onCreate() {
		super.onCreate();
		
		funcionalidade = "27"; //Produtos/Parametrização de Produtos
		
		tecnologiaSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.TECNOLOGIA));
		tipoProdutoSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.TIPO_PRODUTO));
		produtosSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.PRODUTO));
		grupoProdutoSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.GRUPO_PRODUTO));

		permissoesDoController.put("alterarDispProduto", "alterarProduto");
		permissoesDoController.put("alterarNomeProduto", "alterarProduto");
		permissoesDoController.put("associarProdutoModelo", "associaProduto");
		permissoesDoController.put("begin", "consultarProduto");
		permissoesDoController.put("buscarFabricantes", "consultarProduto");
		permissoesDoController.put("buscarModelos", "consultarProduto");
		permissoesDoController.put("desassociarProdutosModelos", "excluirProduto");
		permissoesDoController.put("listarModelosCompativeis", "alterarProduto");
		permissoesDoController.put("pesquisarProdutos", "consultarProduto");
		permissoesDoController.put("popupAlterarNomeProduto", "alterarProduto");
		permissoesDoController.put("popupComposicao", "consultarProduto");
		permissoesDoController.put("popupDesassociarProdutosModelos", "alterarProduto");

	}

	@Override
	protected void onDestroy(HttpSession session) {
	}

	@Jpf.FormBean
	public static class PesquisarProdutosFormBean implements java.io.Serializable {
		private static final long serialVersionUID = 1L;

		private Long tecnologia;

		private Long idModelo;

		private String codigoProduto;

		private String codigoAssociado;

		private Long tipoProduto;

		private Long fabricante;

		private Integer paginaSolicitada;

		public Integer getPaginaSolicitada() {
			return paginaSolicitada;
		}

		public void setPaginaSolicitada(Integer paginaSolicitada) {
			this.paginaSolicitada = paginaSolicitada;
		}

		public Long getTecnologia() {
			return tecnologia;
		}

		public void setTecnologia(Long tecnologia) {
			this.tecnologia = tecnologia;
		}
		
		public String getCodigoProduto() {
			return codigoProduto;
		}

		public void setCodigoProduto(String codigoProduto) {
			this.codigoProduto = codigoProduto;
		}

		public String getCodigoAssociado() {
			return codigoAssociado;
		}

		public void setCodigoAssociado(String codigoAssociado) {
			this.codigoAssociado = codigoAssociado;
		}

		public Long getFabricante() {
			return fabricante;
		}

		public void setFabricante(Long fabricante) {
			this.fabricante = fabricante;
		}

		@Jpf.ValidatableProperty(validateRequired = @Jpf.ValidateRequired(), validateType = @Jpf.ValidateType(type = long.class), validateRange = @Jpf.ValidateRange(minInt = 1, maxInt = 99999))
		public Long getTipoProduto() {
			return tipoProduto;
		}

		public void setTipoProduto(Long tipoProduto) {
			this.tipoProduto = tipoProduto;
		}

		public Long getIdModelo() {
			return idModelo;
		}

		public void setIdModelo(Long idModelo) {
			this.idModelo = idModelo;
		}
	}

	@Jpf.FormBean
	public static class DesassociarProdutosModelosFormBean implements java.io.Serializable {

		private static final long serialVersionUID = 1L;

		private Long[] idsProdutoGrupoProduto;

		private Long[] idsProduto;

		private Long[] idsGrupoProduto;

		private String justificativa;

		public String getJustificativa() {
			return justificativa;
		}

		public void setJustificativa(String justificativa) {
			this.justificativa = justificativa;
		}

		public Long[] getIdsProdutoGrupoProduto() {
			return idsProdutoGrupoProduto;
		}

		public void setIdsProdutoGrupoProduto(Long[] idsProdutoGrupoProduto) {
			this.idsProdutoGrupoProduto = idsProdutoGrupoProduto;
		}

		public Long[] getIdsGrupoProduto() {
			return idsGrupoProduto;
		}

		public void setIdsGrupoProduto(Long[] idsGrupoProduto) {
			this.idsGrupoProduto = idsGrupoProduto;
		}

		public Long[] getIdsProduto() {
			return idsProduto;
		}

		public void setIdsProduto(Long[] idsProduto) {
			this.idsProduto = idsProduto;
		}

	}

	@Jpf.FormBean
	public static class AssociarProdutosModelosFormBean implements java.io.Serializable {
		private static final long serialVersionUID = 1L;

		private String[] idsProdutos;

		private Long[] idsGruposProdutos;
		
		private String codigoAssociado;
		
		private Long idCor;

		public Long getIdCor() {
			return idCor;
		}

		public void setIdCor(Long idCor) {
			this.idCor = idCor;
		}

		public String getCodigoAssociado() {
			return codigoAssociado;
		}

		public void setCodigoAssociado(String codigoAssociado) {
			this.codigoAssociado = codigoAssociado;
		}

		public Long[] getIdsGruposProdutos() {
			return idsGruposProdutos;
		}

		public void setIdsGruposProdutos(Long[] idsGruposProdutos) {
			this.idsGruposProdutos = idsGruposProdutos;
		}

		public String[] getIdsProdutos() {
			return idsProdutos;
		}

		public void setIdsProdutos(String[] idsProdutos) {
			this.idsProdutos = idsProdutos;
		}
	}

	@Jpf.FormBean
	public static class AlterarNomeProdutoFormBean implements java.io.Serializable {
		private static final long serialVersionUID = 1L;

		private Long idProduto;
		private Long idTipoProduto;
		private String nmTipoProduto;
		private String nomeProduto;
		
		private String codigoSAPCadastro;
		private String nomeComercialCadastro;
		private String descricaoSAPCadastro;
		private String composicaoCadastro;
		private String idTipoProdutoCadastro;
		private String destaqueProdutoCadastro;
		private String indModelo;
		private Long idCorCadastro;

		public Long getIdProduto() {
			return idProduto;
		}
		public void setIdProduto(Long idProduto) {
			this.idProduto = idProduto;
		}

		public String getNomeProduto() {
			return nomeProduto;
		}
		public void setNomeProduto(String nomeProduto) {
			this.nomeProduto = nomeProduto==null?nomeProduto:nomeProduto.toUpperCase();
		}

		public Long getIdTipoProduto() {
			return idTipoProduto;
		}
		public void setIdTipoProduto(Long idTipoProduto) {
			this.idTipoProduto = idTipoProduto;
		}

		public String getNmTipoProduto() {
			return nmTipoProduto;
		}
		public void setNmTipoProduto(String nmTipoProduto) {
			this.nmTipoProduto = nmTipoProduto;
		}
		public String getCodigoSAPCadastro() {
			return codigoSAPCadastro;
		}
		public void setCodigoSAPCadastro(String codigoSAPCadastro) {
			this.codigoSAPCadastro = codigoSAPCadastro;
		}
		public String getComposicaoCadastro() {
			return composicaoCadastro;
		}
		public void setComposicaoCadastro(String composicaoCadastro) {
			this.composicaoCadastro = composicaoCadastro;
		}
		public String getDescricaoSAPCadastro() {
			return descricaoSAPCadastro;
		}
		public void setDescricaoSAPCadastro(String descricaoSAPCadastro) {
			this.descricaoSAPCadastro = descricaoSAPCadastro;
		}
		public String getDestaqueProdutoCadastro() {
			return destaqueProdutoCadastro;
		}
		public void setDestaqueProdutoCadastro(String destaqueProdutoCadastro) {
			this.destaqueProdutoCadastro = destaqueProdutoCadastro;
		}
		public Long getIdCorCadastro() {
			return idCorCadastro;
		}
		public void setIdCorCadastro(Long idCorCadastro) {
			this.idCorCadastro = idCorCadastro;
		}
		public String getIdTipoProdutoCadastro() {
			return idTipoProdutoCadastro;
		}
		public void setIdTipoProdutoCadastro(String idTipoProdutoCadastro) {
			this.idTipoProdutoCadastro = idTipoProdutoCadastro;
		}
		public String getNomeComercialCadastro() {
			return nomeComercialCadastro;
		}
		public void setNomeComercialCadastro(String nomeComercialCadastro) {
			this.nomeComercialCadastro = nomeComercialCadastro;
		}
		public String getIndModelo() {
			return indModelo;
		}
		public void setIndModelo(String indModelo) {
			this.indModelo = indModelo;
		}
	}

	public Long getIdCor() {
		return idCor;
	}

	public void setIdCor(Long idCor) {
		this.idCor = idCor;
	}*/
}