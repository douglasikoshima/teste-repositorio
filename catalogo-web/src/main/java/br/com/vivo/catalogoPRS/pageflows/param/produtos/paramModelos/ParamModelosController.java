package br.com.vivo.catalogoPRS.pageflows.param.produtos.paramModelos;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;
import org.json.JSONException;
import org.json.JSONObject;

/*import weblogic.utils.ArrayUtils;*/
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.exception.ExecutionServiceException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.services.FabricanteService;
import br.com.vivo.catalogoPRS.services.TecnologiaService;
import br.com.vivo.catalogoPRS.services.TipoProdutoService;
import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;
import br.com.vivo.catalogoPRS.util.ImagemUtil;
import br.com.vivo.catalogoPRS.util.WebServicesConfiguration;
/*import br.com.vivo.sn.catalogoCaracteristica.BuscarListaCaracteristicaComValorRequestDocument;
import br.com.vivo.sn.catalogoCaracteristica.BuscarListaCaracteristicaComValorResponseDocument;
import br.com.vivo.sn.catalogoCaracteristica.CriarValorCaracteristicaRequestDocument;
import br.com.vivo.sn.catalogoCaracteristica.CriarValorCaracteristicaResponseDocument;
import br.com.vivo.sn.catalogoCaracteristica.ParametrosBuscarListaCaracteristicaComValorDocument.ParametrosBuscarListaCaracteristicaComValor;
import br.com.vivo.sn.catalogoCaracteristica.ParametrosCriarValorCaracteristicaDocument.ParametrosCriarValorCaracteristica;
import br.com.vivo.sn.catalogoCaracteristica.ParametrosCriarValorCaracteristicaDocument.ParametrosCriarValorCaracteristica.ValorCaracteristicaCriacao;
import br.com.vivo.sn.catalogoCaracteristica.ResultadoCaracteristicaDocument.ResultadoCaracteristica;
import br.com.vivo.sn.catalogoFabricante.ResultadoBuscarListaFabricantePorTipoProdutoDocument.ResultadoBuscarListaFabricantePorTipoProduto.Fabricante;
import br.com.vivo.sn.catalogoModelo.AlterarFimVidaModeloRequestDocument;
import br.com.vivo.sn.catalogoModelo.AlterarModeloCaracteristicaRequestDocument;
import br.com.vivo.sn.catalogoModelo.AlterarModeloRequestDocument;
import br.com.vivo.sn.catalogoModelo.BuscarListaCaracteristicaDispProdutoModeloRequestDocument;
import br.com.vivo.sn.catalogoModelo.BuscarListaCaracteristicaDispProdutoModeloResponseDocument;
import br.com.vivo.sn.catalogoModelo.BuscarListaCaracteristicaProdutoModeloRequestDocument;
import br.com.vivo.sn.catalogoModelo.BuscarListaCaracteristicaProdutoModeloResponseDocument;
import br.com.vivo.sn.catalogoModelo.BuscarListaModeloRequestDocument;
import br.com.vivo.sn.catalogoModelo.BuscarListaModeloResponseDocument;
import br.com.vivo.sn.catalogoModelo.BuscarModeloPorIdRequestDocument;
import br.com.vivo.sn.catalogoModelo.BuscarModeloPorIdResponseDocument;
import br.com.vivo.sn.catalogoModelo.CriarModeloRequestDocument;
import br.com.vivo.sn.catalogoModelo.CriarModeloResponseDocument;
import br.com.vivo.sn.catalogoModelo.DisponibilizarModeloRequestDocument;
import br.com.vivo.sn.catalogoModelo.ExcluirListaModeloRequestDocument;
import br.com.vivo.sn.catalogoModelo.UpdateGrupoProdutoRequestDocument;
import br.com.vivo.sn.catalogoModelo.CaracteristicaDocument.Caracteristica;
import br.com.vivo.sn.catalogoModelo.ListaIndisponibilizaModeloDocument.ListaIndisponibilizaModelo;
import br.com.vivo.sn.catalogoModelo.ListaIndisponibilizaModeloDocument.ListaIndisponibilizaModelo.ParametrosDispModelo;
import br.com.vivo.sn.catalogoModelo.ListaModelosPorIdModeloOutDocument.ListaModelosPorIdModeloOut;
import br.com.vivo.sn.catalogoModelo.ListaModelosPorIdModeloOutDocument.ListaModelosPorIdModeloOut.ModeloPorIdModelo;
import br.com.vivo.sn.catalogoModelo.PaginacaoInDocument.PaginacaoIn;
import br.com.vivo.sn.catalogoModelo.ParamUpdateGrupoProdutoDocument.ParamUpdateGrupoProduto;
import br.com.vivo.sn.catalogoModelo.ParametrosAlterarFimVidaModeloDocument.ParametrosAlterarFimVidaModelo.ParametrosFimVidaModelo;
import br.com.vivo.sn.catalogoModelo.ParametrosAlterarModeloCaracteristicaDocument.ParametrosAlterarModeloCaracteristica.ParametrosAssociarModeloCaracteristica;
import br.com.vivo.sn.catalogoModelo.ParametrosAlterarModeloCaracteristicaDocument.ParametrosAlterarModeloCaracteristica.ParametrosAssociarModeloCaracteristica.ValorCaracteristica;
import br.com.vivo.sn.catalogoModelo.ParametrosBuscarListaCaracteristicaDispProdutoModeloDocument.ParametrosBuscarListaCaracteristicaDispProdutoModelo.BuscaCaracteristicaDispProdutoModelo;
import br.com.vivo.sn.catalogoModelo.ParametrosBuscarListaCaracteristicaProdutoModeloDocument.ParametrosBuscarListaCaracteristicaProdutoModelo.BuscaCaracteristicaProdutoModelo;
import br.com.vivo.sn.catalogoModelo.ParametrosBuscarListaModeloDocument.ParametrosBuscarListaModelo.ParametrosModeloIn;
import br.com.vivo.sn.catalogoModelo.ParametrosBuscarListaModeloDocument.ParametrosBuscarListaModelo.ParametrosModeloIn.ListaCaracteristica;
import br.com.vivo.sn.catalogoModelo.ParametrosBuscarListaModeloDocument.ParametrosBuscarListaModelo.ParametrosModeloIn.ListaTecnologia;
import br.com.vivo.sn.catalogoModelo.ParametrosBuscarListaModeloDocument.ParametrosBuscarListaModelo.ParametrosModeloIn.ListaValorCaracteristica;
import br.com.vivo.sn.catalogoModelo.ParametrosBuscarModeloPorIdDocument.ParametrosBuscarModeloPorId;
import br.com.vivo.sn.catalogoModelo.ParametrosBuscarModeloPorIdDocument.ParametrosBuscarModeloPorId.ParametrosModelosPorIdModeloIn;
import br.com.vivo.sn.catalogoModelo.ParametrosExcluirListaModeloDocument.ParametrosExcluirListaModelo.ListaModeloExclusao.ModeloExclusao;
import br.com.vivo.sn.catalogoModelo.ParametrosModeloDocument.ParametrosModelo;
import br.com.vivo.sn.catalogoModelo.ResultadoBuscarListaCaracteristicaProdutoModeloDocument.ResultadoBuscarListaCaracteristicaProdutoModelo.BuscaCaracteristicaProdutoModeloOut;
import br.com.vivo.sn.catalogoModelo.ResultadoBuscarListaModeloDocument.ResultadoBuscarListaModelo;
import br.com.vivo.sn.catalogoModelo.ResultadoBuscarListaModeloDocument.ResultadoBuscarListaModelo.ListaModelos;
import br.com.vivo.sn.catalogoModelo.ResultadoBuscarListaModeloDocument.ResultadoBuscarListaModelo.ListaModelos.RetornoModelo;
import br.com.vivo.sn.catalogoMultimidia.AlterarMultimidiaRequestDocument;
import br.com.vivo.sn.catalogoMultimidia.AssociarMultimidiaRequestDocument;
import br.com.vivo.sn.catalogoMultimidia.BuscarListaClassificacaoMultimidiaRequestDocument;
import br.com.vivo.sn.catalogoMultimidia.BuscarListaClassificacaoMultimidiaResponseDocument;
import br.com.vivo.sn.catalogoMultimidia.BuscarListaCorRequestDocument;
import br.com.vivo.sn.catalogoMultimidia.BuscarListaCorResponseDocument;
import br.com.vivo.sn.catalogoMultimidia.BuscarListaImagemPorModeloRequestDocument;
import br.com.vivo.sn.catalogoMultimidia.BuscarListaMultTMCorClassRequestDocument;
import br.com.vivo.sn.catalogoMultimidia.BuscarListaMultTMCorClassResponseDocument;
import br.com.vivo.sn.catalogoMultimidia.BuscarListaMultimidiaRequestDocument;
import br.com.vivo.sn.catalogoMultimidia.BuscarListaMultimidiaResponseDocument;
import br.com.vivo.sn.catalogoMultimidia.BuscarListaTipoMultimidiaRequestDocument;
import br.com.vivo.sn.catalogoMultimidia.BuscarListaTipoMultimidiaResponseDocument;
import br.com.vivo.sn.catalogoMultimidia.DesassociarListaMultimidiaRequestDocument;
import br.com.vivo.sn.catalogoMultimidia.ObterExistenciaClassMultRequestDocument;
import br.com.vivo.sn.catalogoMultimidia.ObterExistenciaClassMultResponseDocument;
import br.com.vivo.sn.catalogoMultimidia.ListaClassificacaoMultimidiaDocument.ListaClassificacaoMultimidia.ClassificacaoMultimidia;
import br.com.vivo.sn.catalogoMultimidia.ListaCorDocument.ListaCor.Cor;
import br.com.vivo.sn.catalogoMultimidia.ListaTipoMultimidiaDocument.ListaTipoMultimidia.TipoMultimidia;
import br.com.vivo.sn.catalogoMultimidia.ParamAlterarMultimidiaDocument.ParamAlterarMultimidia;
import br.com.vivo.sn.catalogoMultimidia.ParamBuscarListaMultTMCorClassDocument.ParamBuscarListaMultTMCorClass;
import br.com.vivo.sn.catalogoMultimidia.ParamBuscarListaMultimidiaDocument.ParamBuscarListaMultimidia;
import br.com.vivo.sn.catalogoMultimidia.ParamObterExistenciaClassMultDocument.ParamObterExistenciaClassMult;
import br.com.vivo.sn.catalogoMultimidia.ParametrosAssociarMultimidiaDocument.ParametrosAssociarMultimidia;
import br.com.vivo.sn.catalogoMultimidia.ParametrosAssociarMultimidiaDocument.ParametrosAssociarMultimidia.MultimidiaAssociacao;
import br.com.vivo.sn.catalogoMultimidia.ParametrosBuscarListaImagemPorModeloDocument.ParametrosBuscarListaImagemPorModelo.ParametrosImagemPorModeloIn;
import br.com.vivo.sn.catalogoMultimidia.ParametrosDesassociarListaMultimidiaDocument.ParametrosDesassociarListaMultimidia.ListaMultimidiaDesassociacao.MultimidiaDesassociacao;
import br.com.vivo.sn.catalogoMultimidia.ResultBuscarListaMultTMCorClassDocument.ResultBuscarListaMultTMCorClass;
import br.com.vivo.sn.catalogoMultimidia.ResultBuscarListaMultTMCorClassDocument.ResultBuscarListaMultTMCorClass.Multimidia;
import br.com.vivo.sn.catalogoMultimidia.ResultBuscarListaMultimidiaDocument.ResultBuscarListaMultimidia.ListaMultimidia;
import br.com.vivo.sn.catalogoMultimidia.ResultObterExistenciaClassMultDocument.ResultObterExistenciaClassMult;
import br.com.vivo.sn.catalogoMultimidia.ResultadoBuscarListaImagemPorModeloDocument.ResultadoBuscarListaImagemPorModelo;
import br.com.vivo.sn.catalogoProduto.BuscarListaCoresPorModeloRequestDocument;
import br.com.vivo.sn.catalogoProduto.BuscarListaCoresPorModeloResponseDocument;
import br.com.vivo.sn.catalogoProduto.BuscarListaProdutoPorModeloRequestDocument;
import br.com.vivo.sn.catalogoProduto.BuscarListaProdutoPorModeloResponseDocument;
import br.com.vivo.sn.catalogoProduto.ParametroBuscarListaCoresPorModeloDocument.ParametroBuscarListaCoresPorModelo;
import br.com.vivo.sn.catalogoProduto.ResultadoBuscarListaCoresPorModeloDocument.ResultadoBuscarListaCoresPorModelo.ListaCores;
import br.com.vivo.sn.catalogoProduto.ResultadoBuscarListaProdutoPorModeloDocument.ResultadoBuscarListaProdutoPorModelo.ListaProduto;
import br.com.vivo.sn.catalogoTecnologia.BuscarListaTecnologiaPorModeloRequestDocument;
import br.com.vivo.sn.catalogoTecnologia.BuscarListaTecnologiaPorModeloResponseDocument;
import br.com.vivo.sn.catalogoTecnologia.ListaTipoFrequenciaDocument.ListaTipoFrequencia.TipoFrequencia;
import br.com.vivo.sn.catalogoTecnologia.ListaValorFrequenciaDocument.ListaValorFrequencia.ValorFrequencia;
import br.com.vivo.sn.catalogoTecnologia.ParametrosBuscarListaTecnologiaPorModeloDocument.ParametrosBuscarListaTecnologiaPorModelo.ParametrosTecnologiaPorModeloIn;
import br.com.vivo.sn.catalogoTecnologia.ResultadoBuscarListaTecnologiaDocument.ResultadoBuscarListaTecnologia.Tecnologia;
import br.com.vivo.sn.catalogoTecnologia.ResultadoBuscarListaTecnologiaPorModeloDocument.ResultadoBuscarListaTecnologiaPorModelo;
import br.com.vivo.sn.catalogoTipoProduto.BuscarListaTipoProdutoPorFabricanteRequestDocument;
import br.com.vivo.sn.catalogoTipoProduto.BuscarListaTipoProdutoPorFabricanteResponseDocument;
import br.com.vivo.sn.catalogoTipoProduto.ParamTipoProdutoPorFabricanteDocument.ParamTipoProdutoPorFabricante;
import br.com.vivo.sn.catalogoTipoProduto.ResultadoListarTipoProdutoDocument.ResultadoListarTipoProduto.TipoProduto;

import com.bea.control.ServiceControlException;
*/
public class ParamModelosController extends BaseMappingDispatchAction {
	
/*	private static Logger logger = Logger.getLogger(ParamModelosController.class);
	
	private static final long serialVersionUID = 1L;

	String[] tecnologiasSelecionadas;

	String[] caracteristicasSelecionadas;

	String[] valoresCaracteristicasSelecionadas;

	String[] caracteristicasSelecionadasAlterar;

	String[] valoresCaracteristicasSelecionadasAlterar;

	String[] tecnologiasFrequencias_tecnologiasSelecionadas;

	Map<String, List<String>> tecnologiasFrequencias_tiposFrequenciaSelecionadas;

	Map<String, Map<String, List<String>>> tecnologiasFrequencias_frequenciaSelecionadas;

	@Control
	private TecnologiaSoapServiceControl tecnologiaSoapServiceControl;

	@Control
	private TipoProdutoSoapServiceControl tipoProdutoSoapServiceControl;

	@Control
	private CaracteristicaSoapServiceControl caracteristicaSoapServiceControl;

	@Control
	private ModeloSoapServiceControl grupoProdutoSoapServiceControl;

	@Control
	private MultimidiaServiceControl multimidiaSoapServiceControl;

	@Control
	private FabricanteServiceControl fabricanteSoapServiceControl;

	@Control
	private ProdutosSoapServiceControl produtosSoapServiceControl;
	

	@Jpf.Action(forwards = { @Jpf.Forward(name = "default", path = "ParamModelos.jsp") })
	public Forward begin() {
		
		prepareServiceControl(tipoProdutoSoapServiceControl);
		List<TipoProduto> tipoProdutoList = TipoProdutoService.getInstance().buscarListaTipoProduto(tipoProdutoSoapServiceControl);
		
		HttpServletRequest request = getRequest();

		request.setAttribute("tipos_produto", tipoProdutoList);
		return new Forward("default");
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "ResultadoBuscaModelos.jsp") }, validationErrorForward = @Jpf.Forward(name = "fail", navigateTo = Jpf.NavigateTo.previousAction))
	public Forward pesquisarModelo(br.com.vivo.catalogoPRS.pageflows.param.produtos.paramModelos.ParamModelosController.PesquisaModeloFormBean form) {
		String[] idsTecnologias = splitAndClean(form.getTecnologias());

		HttpServletRequest request = getRequest();

		request.setAttribute("idTipoProduto", form.getTipoProduto());
		
		if(form.getNomesTecnologias()!=null){
			request.setAttribute("nomes_tecnologias", form.getNomesTecnologias());
		}
		if( (form.getNomesCaracteristicasValores() != null) && (! form.getNomesCaracteristicasValores().equals(""))){
			try {
				StringBuffer sb = new StringBuffer();
				JSONObject hashCaracteristicas = new JSONObject(form.getNomesCaracteristicasValores());
				boolean first = true;
				for(Iterator itCaracteristica = hashCaracteristicas.keys(); itCaracteristica.hasNext();){
					if(first){
						first=false;
					}else{
						sb.append(", ");
					}
					String caracteristica = (String)itCaracteristica.next();
					sb.append(caracteristica);
					String valoresCaracteristica = hashCaracteristicas.getString(caracteristica);
					if(valoresCaracteristica != null && valoresCaracteristica.length() >0){
						sb.append("(");
						sb.append(valoresCaracteristica);
						sb.append(")");
					}
				}
				request.setAttribute("nomes_valores_caracteristicas", sb.toString());
			}catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		String[][] resultado = extrairCaracteristicas(form.getCaracteristicas());
		String[] idsCaracteristicas = resultado[0];
		String[] idsValorescaracteristicas = resultado[1];

		BuscarListaModeloRequestDocument paramBuscarModelo = BuscarListaModeloRequestDocument.Factory.newInstance();
		ParametrosModeloIn parametrosModeloIn = paramBuscarModelo.addNewBuscarListaModeloRequest().addNewParametrosBuscarListaModelo().addNewParametrosModeloIn();

		PaginacaoIn paramPaginacao = parametrosModeloIn.addNewPaginacaoIn();
		int elementosPorPagina = ApplicationConfiguration.getElementosPorPagina();
		paramPaginacao.setItensPorPagina(elementosPorPagina);
		if(form.getPaginaSolicitada() == null)
			paramPaginacao.setPaginaSolicitada(1);
		else
			paramPaginacao.setPaginaSolicitada(form.getPaginaSolicitada());

		if (form.getFabricante() != null)
			parametrosModeloIn.setIdFabricante(form.getFabricante());

		if (form.getTipoProduto() != null)
			parametrosModeloIn.setIdTipoProduto(form.getTipoProduto());

		if (form.getModelo() != null)
			parametrosModeloIn.setNmModelo(form.getModelo());

		if (idsCaracteristicas != null && idsCaracteristicas.length > 0) {
			ListaCaracteristica listaCaracteristica = parametrosModeloIn
					.addNewListaCaracteristica();
			ListaValorCaracteristica listaValorCaracteristica = parametrosModeloIn
					.addNewListaValorCaracteristica();
			for (String idCaracteristicaString : idsCaracteristicas) {
				if (!idCaracteristicaString.trim().equals("")) {
					listaCaracteristica.addIdCaracteristica(Long.valueOf(idCaracteristicaString));
				}
			}

			if (idsValorescaracteristicas != null && idsValorescaracteristicas.length > 0) {
				for (String idValorCaracteristicaString : idsValorescaracteristicas) {
					if (!idValorCaracteristicaString.trim().equals("")) {
						listaValorCaracteristica.addIdValorCaracteristica(Long.valueOf(idValorCaracteristicaString));
					}
				}
			}
		}

		if (idsTecnologias != null && idsTecnologias.length > 0) {
			ListaTecnologia listaTecnologia = parametrosModeloIn
					.addNewListaTecnologia();
			for (String idTecnologiaString : idsTecnologias) {
				if (!idTecnologiaString.trim().equals("")) {
					listaTecnologia.addIdTecnologia(Long.valueOf(idTecnologiaString));
				}
			}
		}


		prepareServiceControl(grupoProdutoSoapServiceControl);
		BuscarListaModeloResponseDocument buscarListaModeloResponseDocument = grupoProdutoSoapServiceControl.buscarListaModelo(paramBuscarModelo);

		ResultadoBuscarListaModelo resultadoBuscarListaModelo = buscarListaModeloResponseDocument.getBuscarListaModeloResponse().getResultadoBuscarListaModelo();
		tratarPaginacao(resultadoBuscarListaModelo.getPaginacaoOut(), ApplicationConfiguration.getElementosPorPagina());
		ListaModelos listaModelos = resultadoBuscarListaModelo.getListaModelos();
		List<RetornoModelo> retornoModeloList = new ArrayList<RetornoModelo>();
		if (listaModelos != null) {
			retornoModeloList = listaModelos.getRetornoModeloList();
		}
		request.setAttribute("modelos", retornoModeloList);

		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupSelecionarTecnologias.jsp") })
	public Forward listarTecnologias() {

		String tecnologiasCSV = getRequest().getParameter("hiddenTecnologias");
		if (tecnologiasCSV != null) {
			tecnologiasSelecionadas = tecnologiasCSV.split(",");
		}

		prepareServiceControl(tecnologiaSoapServiceControl);
		List<Tecnologia> tecnologiaList = TecnologiaService.getInstance().buscarListaTecnologia(tecnologiaSoapServiceControl);
		HttpServletRequest request = getRequest();
		request.setAttribute("tecnologias", tecnologiaList);
		Forward forward = new Forward("success");
		return forward;
	}


	private String[][] extrairCaracteristicas(String caracteristicasJSON) {
		String[][] resultado = new String[2][];
		if (caracteristicasJSON == null || caracteristicasJSON.trim().length() == 0)
			return resultado;
		try {
			JSONObject hashmap = new JSONObject(caracteristicasJSON);
			List<String> idsCaracteristicas = new ArrayList<String>();
			List<String> idsValoresCaracteristicas = new ArrayList<String>();
			for (Iterator caracs = hashmap.keys(); caracs.hasNext();) {
				String idCaracteristica = (String) caracs.next();
				idsCaracteristicas.add(idCaracteristica);
				if (hashmap.get(idCaracteristica) != null && ((String) hashmap.get(idCaracteristica)).trim().length() > 0)
					ArrayUtils.addAll(idsValoresCaracteristicas, splitAndClean((String) hashmap.get(idCaracteristica)));

			}
			String[] valores = idsValoresCaracteristicas.toArray(new String[idsValoresCaracteristicas.size()]);
			String[] caracteristicas = idsCaracteristicas.toArray(new String[idsCaracteristicas.size()]);
			resultado = new String[][] { caracteristicas, valores };
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	private void extrairTecnologiasFrequencias(String tecnologiasFrequenciasJSON) {
		if (tecnologiasFrequenciasJSON == null || tecnologiasFrequenciasJSON.trim().length() == 0)
			return;
		try {
			JSONObject hashmapTecnologias = new JSONObject(tecnologiasFrequenciasJSON);
			List<String> idsTecnologias = new ArrayList<String>();
			Map<String, List<String>> idsTiposFrequenciaPorTecnologia = new HashMap<String, List<String>>();
			Map<String, Map<String, List<String>>> idsFrequenciaPorTipoFrequenciaPorTecnologia = new HashMap<String, Map<String, List<String>>>();
			for (Iterator tecnologias = hashmapTecnologias.keys(); tecnologias.hasNext();) {
				String idTecnologia = (String) tecnologias.next();
				idsTecnologias.add(idTecnologia);
				Map<String, List<String>> idsFrequenciasPorTipoFrequencia = new HashMap<String, List<String>>();
				if (hashmapTecnologias.get(idTecnologia) != null) {
					List<String> idsTiposFrequencia = new ArrayList<String>();
					JSONObject hashmapTiposFrequencia = (JSONObject) hashmapTecnologias.get(idTecnologia);
					for (Iterator tiposFrequencia = hashmapTiposFrequencia.keys(); tiposFrequencia.hasNext();) {
						String idTipoFrequencia = (String) tiposFrequencia.next();
						idsTiposFrequencia.add(idTipoFrequencia);
						if (hashmapTiposFrequencia.get(idTipoFrequencia) != null && ((String) hashmapTiposFrequencia.get(idTipoFrequencia)).trim().length() > 0) {
							List<String> idsFrequencias = new ArrayList<String>();
							ArrayUtils.addAll(idsFrequencias, splitAndClean((String) hashmapTiposFrequencia.get(idTipoFrequencia)));
							idsFrequenciasPorTipoFrequencia.put(idTipoFrequencia, idsFrequencias);
						}
					}
					idsTiposFrequenciaPorTecnologia.put(idTecnologia, idsTiposFrequencia);
					idsFrequenciaPorTipoFrequenciaPorTecnologia.put(idTecnologia, idsFrequenciasPorTipoFrequencia);
				}
			}
			tecnologiasFrequencias_tecnologiasSelecionadas = idsTecnologias.toArray(new String[idsTecnologias.size()]);
			tecnologiasFrequencias_tiposFrequenciaSelecionadas = idsTiposFrequenciaPorTecnologia;
			tecnologiasFrequencias_frequenciaSelecionadas = idsFrequenciaPorTipoFrequenciaPorTecnologia;

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupSelecionarCaracteristicas.jsp") })
	public Forward listarCaracteristicas() {
		String caracteristicasJSON = getRequest().getParameter("hiddenCaracteristicas");
		if (caracteristicasJSON != null && !caracteristicasJSON.equals("")) {
			String[][] resultado = extrairCaracteristicas(caracteristicasJSON);
			caracteristicasSelecionadas = resultado[0];
			valoresCaracteristicasSelecionadas = resultado[1];
		}

		int paginaSolicitada = 1;
		String paginaSolicitadaString = getRequest().getParameter("pagina_solicitada");
		if (paginaSolicitadaString != null && !paginaSolicitadaString.trim().equals("")) {
			paginaSolicitada = Integer.valueOf(paginaSolicitadaString);
		}
		buscarCaracteristicas(paginaSolicitada, null);
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupSelecionarTecnolgiasFrequencias.jsp") })
	public Forward listarTecnologiasFrequencias() {
		tecnologiasFrequencias_tecnologiasSelecionadas = new String[]{};
		tecnologiasFrequencias_tiposFrequenciaSelecionadas = new HashMap<String, List<String>>();
		tecnologiasFrequencias_frequenciaSelecionadas = new HashMap<String, Map<String,List<String>>>();
		forwardParameter("gravar_select_tipo_produto");
		String tecnologiasFrequenciasJSON = getRequest().getParameter("hiddenTecnologiasFrequencias");
		if (tecnologiasFrequenciasJSON != null && !tecnologiasFrequenciasJSON.equals("")) {
			extrairTecnologiasFrequencias(tecnologiasFrequenciasJSON);
		}

		prepareServiceControl(tecnologiaSoapServiceControl);
		List<Tecnologia> tecnologiaList = TecnologiaService.getInstance().buscarListaTecnologia(tecnologiaSoapServiceControl);
		getRequest().setAttribute("tecnologias", tecnologiaList);

		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "alterarModelo.jsp") })
	public Forward carregarAlterarModelo() throws CatalogoPRSException {
		HttpServletRequest request = getRequest();
		String idModeloString = request.getParameter("id_modelo");
		forwardParameter("id_modelo");

		BuscarModeloPorIdRequestDocument paramBuscarModeloPorIdModelo = BuscarModeloPorIdRequestDocument.Factory.newInstance();
		ParametrosModelosPorIdModeloIn parametrosModelosPorIdModeloIn = paramBuscarModeloPorIdModelo.addNewBuscarModeloPorIdRequest().addNewParametrosBuscarModeloPorId().addNewParametrosModelosPorIdModeloIn();
		parametrosModelosPorIdModeloIn.setIdModelo(Long.valueOf(idModeloString));

		prepareServiceControl(grupoProdutoSoapServiceControl);
		BuscarModeloPorIdResponseDocument buscarModeloPorIdResponseDocument = grupoProdutoSoapServiceControl.buscarModeloPorId(paramBuscarModeloPorIdModelo);
		ListaModelosPorIdModeloOut listaModelosPorIdModeloOut = buscarModeloPorIdResponseDocument.getBuscarModeloPorIdResponse().getResultadoBuscarModeloPorId().getListaModelosPorIdModeloOut();
		if(listaModelosPorIdModeloOut == null || listaModelosPorIdModeloOut.getModeloPorIdModeloList() == null || listaModelosPorIdModeloOut.getModeloPorIdModeloList().size()==0)
			throw new CatalogoPRSException("Modelo não encontrado.");
		
		ModeloPorIdModelo modelo = listaModelosPorIdModeloOut.getModeloPorIdModeloList().get(0);
		request.setAttribute("modelo", modelo);
		
		List<TipoProduto> tipoProdutoList = null;
		
		if (modelo.getQtdProdutosAfetados() == 0) {
			prepareServiceControl(tipoProdutoSoapServiceControl);
			tipoProdutoList = TipoProdutoService.getInstance().buscarListaTipoProduto(tipoProdutoSoapServiceControl);
		} else {
			
			try {
				
				BuscarListaTipoProdutoPorFabricanteRequestDocument buscarListaTipoProdutoPorFabricanteRequestDocument = BuscarListaTipoProdutoPorFabricanteRequestDocument.Factory.newInstance();
				ParamTipoProdutoPorFabricante paramTipoProdutoPorFabricante = buscarListaTipoProdutoPorFabricanteRequestDocument.addNewBuscarListaTipoProdutoPorFabricanteRequest()
					.addNewParamTipoProdutoPorFabricante();
				paramTipoProdutoPorFabricante.setIdFabricante(modelo.getIdFabricante());
				prepareServiceControl(tipoProdutoSoapServiceControl);
				BuscarListaTipoProdutoPorFabricanteResponseDocument buscarListaTipoProdutoPorFabricanteResponseDocument = tipoProdutoSoapServiceControl
					.buscarListaTipoProdutoPorFabricante(buscarListaTipoProdutoPorFabricanteRequestDocument);
				tipoProdutoList = buscarListaTipoProdutoPorFabricanteResponseDocument.getBuscarListaTipoProdutoPorFabricanteResponse().getResultadoListarTipoProduto()
					.getTipoProdutoList();
				
			} catch (Exception e) {
				logger.warn("Não foi encontrado nenhum Tipo de Produto para o Fabricante informado" +
						" ou ocorreram problemas na chamada do Serviço TipoProduto.buscarListaTipoProdutoPorFabricante do Catalogo...");
			}
		}
		
		request.setAttribute("tipos_produto", tipoProdutoList);
		
		prepareServiceControl(fabricanteSoapServiceControl);
		List<Fabricante> listaFabricantes = FabricanteService.getInstance().buscarListaFabricantePorTipoProduto(fabricanteSoapServiceControl, modelo.getIdTipoProduto());
		
		if (listaFabricantes != null) {
			request.setAttribute("fabricantes", listaFabricantes);
		}

		try {
			JSONObject hashmapTecnologias = convertTecnologiasTiposFrequenciaVfFrequenciasToJSON(idModeloString);
			request.setAttribute("tecnologiasSelecionadas", hashmapTecnologias.toString());			
		} catch (ExecutionServiceException e) {
			logger.info("Exception tratada pois o negocio permite que determinados tipos de produto não possuam tecnologias associadas...");
		}
		
		String caracteristicasJSON = caracteristicasModeloToJson(Long.valueOf(idModeloString)).toString();
		
		request.setAttribute("caracteristicasModeloSelecionadas", caracteristicasJSON);

		Forward forward = new Forward("success");
		return forward;
	}

	private JSONObject convertTecnologiasTiposFrequenciaVfFrequenciasToJSON(String idModelo) throws CatalogoPRSException {
		BuscarListaTecnologiaPorModeloRequestDocument paramBuscarTecnologiaPorModelo = BuscarListaTecnologiaPorModeloRequestDocument.Factory.newInstance();
		ParametrosTecnologiaPorModeloIn parametrosTecnologiaPorModeloIn = paramBuscarTecnologiaPorModelo.addNewBuscarListaTecnologiaPorModeloRequest().addNewParametrosBuscarListaTecnologiaPorModelo().addNewParametrosTecnologiaPorModeloIn();
		parametrosTecnologiaPorModeloIn.setIdModelo(Long.valueOf(idModelo));
		JSONObject hashmapTecnologias = new JSONObject();
		try {
			prepareServiceControl(tecnologiaSoapServiceControl);
			//ListaTecnologiaDocument listaTecnologiaDocument = ;
			BuscarListaTecnologiaPorModeloResponseDocument buscarListaTecnologiaPorModeloResponseDocument = tecnologiaSoapServiceControl.buscarListaTecnologiaPorModelo(paramBuscarTecnologiaPorModelo);
			ResultadoBuscarListaTecnologiaPorModelo resultadoBuscarListaTecnologiaPorModelo = buscarListaTecnologiaPorModeloResponseDocument.getBuscarListaTecnologiaPorModeloResponse().getResultadoBuscarListaTecnologiaPorModelo();
			if (resultadoBuscarListaTecnologiaPorModelo != null) {
				List<br.com.vivo.sn.catalogoTecnologia.ResultadoBuscarListaTecnologiaPorModeloDocument.ResultadoBuscarListaTecnologiaPorModelo.Tecnologia> tecnologiaList = resultadoBuscarListaTecnologiaPorModelo.getTecnologiaList();

				for (br.com.vivo.sn.catalogoTecnologia.ResultadoBuscarListaTecnologiaPorModeloDocument.ResultadoBuscarListaTecnologiaPorModelo.Tecnologia tecnologia : tecnologiaList) {
					JSONObject hashmapTiposFrequencia = new JSONObject();
					if (tecnologia.getListaTipoFrequencia() != null && tecnologia.getListaTipoFrequencia().getTipoFrequenciaList().size() > 0) {
						List<TipoFrequencia> tipoFrequenciaList = tecnologia.getListaTipoFrequencia().getTipoFrequenciaList();
						for (TipoFrequencia tipoFrequencia : tipoFrequenciaList) {
							String frequencias = "";
							if (tipoFrequencia.getListaValorFrequencia() != null
									&& tipoFrequencia.getListaValorFrequencia().getValorFrequenciaList().size() > 0) {
								List<ValorFrequencia> valorFrequenciaList = tipoFrequencia.getListaValorFrequencia().getValorFrequenciaList();
								boolean first = true;
								for (ValorFrequencia valorFrequencia : valorFrequenciaList) {
									if (first) {
										first = false;
									} else {
										frequencias += ",";
									}
									frequencias += valorFrequencia.getIdTecnologiaTpFrequenciaVl();

								}
							}
							hashmapTiposFrequencia.put(String.valueOf(tipoFrequencia.getIdTipoFrequencia()), frequencias);
						}
					}
					hashmapTecnologias.put(String.valueOf(tecnologia.getIdTecnologia()), hashmapTiposFrequencia);
				}

			}

		} catch (JSONException e) {
			throw new CatalogoPRSException("Ocorreu um erro ao listar as tecnologias do modelo.");
		}
		return hashmapTecnologias;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupApagarModelo.jsp") })
	public Forward popupApagarModelo() {

		HttpServletRequest request = getRequest();

		forwardParameter("id_modelo");
		
		String idModeloString = request.getParameter("id_modelo");
		
		BuscarListaProdutoPorModeloRequestDocument buscarListaProdutoPorModeloRequestDocument = BuscarListaProdutoPorModeloRequestDocument.Factory.newInstance();
		buscarListaProdutoPorModeloRequestDocument.addNewBuscarListaProdutoPorModeloRequest().addNewParametrosBuscarListaProdutoPorModelo().setIdGrupoProduto(Long.valueOf(idModeloString));
		
		prepareServiceControl(produtosSoapServiceControl);
		BuscarListaProdutoPorModeloResponseDocument buscarListaProdutoPorModeloResponseDocument = produtosSoapServiceControl.buscarListaProdutoPorModelo(buscarListaProdutoPorModeloRequestDocument);
		ListaProduto listaProduto = buscarListaProdutoPorModeloResponseDocument.getBuscarListaProdutoPorModeloResponse().getResultadoBuscarListaProdutoPorModelo().getListaProduto();
		if(listaProduto!=null){
			request.setAttribute("produtos_afetados", listaProduto.getProdutoList());
		}
		

		Forward forward = new Forward("success");
		return forward;
	}
	
	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupAlterarModelo.jsp") })
	public Forward popupAlterarModelo() {

		HttpServletRequest request = getRequest();

		forwardParameter("id_modelo");
		
		String idModeloString = request.getParameter("id_modelo");
		
		//Servico responsavel em buscar a lista de produtos que estao associados ao modelo, pois caso a alteracao do modelo seja em seu tipo de produto,
		//o tipo de produto dos produtos associados tambem serao alterados, tendo como base a trigger TRGU_TPPRODUTOPORMODELO responsavel em realizar tal
		//operacao.
		BuscarListaProdutoPorModeloRequestDocument buscarListaProdutoPorModeloRequestDocument = BuscarListaProdutoPorModeloRequestDocument.Factory.newInstance();
		buscarListaProdutoPorModeloRequestDocument.addNewBuscarListaProdutoPorModeloRequest().addNewParametrosBuscarListaProdutoPorModelo().setIdGrupoProduto(Long.valueOf(idModeloString));
		
		prepareServiceControl(produtosSoapServiceControl);
		BuscarListaProdutoPorModeloResponseDocument buscarListaProdutoPorModeloResponseDocument = produtosSoapServiceControl.buscarListaProdutoPorModelo(buscarListaProdutoPorModeloRequestDocument);
		ListaProduto listaProduto = buscarListaProdutoPorModeloResponseDocument.getBuscarListaProdutoPorModeloResponse().getResultadoBuscarListaProdutoPorModelo().getListaProduto();
		if(listaProduto!=null){
			request.setAttribute("produtos_afetados", listaProduto.getProdutoList());
		}
		

		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "copiarModelo.jsp") })
	public Forward carregarCopiarModelo() throws CatalogoPRSException {
		HttpServletRequest request = getRequest();
		
		prepareServiceControl(tipoProdutoSoapServiceControl);
		List<TipoProduto> tipoProdutoList = TipoProdutoService.getInstance().buscarListaTipoProduto(tipoProdutoSoapServiceControl);
		
		request.setAttribute("tipos_produto", tipoProdutoList);
		
		String idModeloString = request.getParameter("id_modelo");
		forwardParameter("id_modelo");

		BuscarModeloPorIdRequestDocument paramBuscarModeloPorIdModelo = BuscarModeloPorIdRequestDocument.Factory.newInstance();
		ParametrosModelosPorIdModeloIn parametrosModelosPorIdModeloIn = paramBuscarModeloPorIdModelo.addNewBuscarModeloPorIdRequest().addNewParametrosBuscarModeloPorId().addNewParametrosModelosPorIdModeloIn();
		parametrosModelosPorIdModeloIn.setIdModelo(Long.valueOf(idModeloString));

		prepareServiceControl(grupoProdutoSoapServiceControl);
		BuscarModeloPorIdResponseDocument buscarModeloPorIdResponseDocument = grupoProdutoSoapServiceControl.buscarModeloPorId(paramBuscarModeloPorIdModelo);
		ListaModelosPorIdModeloOut listaModelosPorIdModeloOut = buscarModeloPorIdResponseDocument.getBuscarModeloPorIdResponse().getResultadoBuscarModeloPorId().getListaModelosPorIdModeloOut();
		if(listaModelosPorIdModeloOut == null || listaModelosPorIdModeloOut.getModeloPorIdModeloList() == null || listaModelosPorIdModeloOut.getModeloPorIdModeloList().size()==0)
			throw new CatalogoPRSException("Modelo não encontrado.");
		
		ModeloPorIdModelo modelo = listaModelosPorIdModeloOut.getModeloPorIdModeloList().get(0);
		request.setAttribute("modelo", modelo);

		prepareServiceControl(fabricanteSoapServiceControl);
		List<Fabricante> listaFabricantes = FabricanteService.getInstance().buscarListaFabricantePorTipoProduto(fabricanteSoapServiceControl, modelo.getIdTipoProduto());
		
		if (listaFabricantes != null) {
			request.setAttribute("fabricantes", listaFabricantes);
		}
		
		JSONObject hashmapTecnologias = convertTecnologiasTiposFrequenciaVfFrequenciasToJSON(idModeloString);
		request.setAttribute("tecnologiasSelecionadas", hashmapTecnologias.toString());
		
		String caracteristicasJSON = caracteristicasModeloToJson(modelo.getIdModelo()).toString();
		
		request.setAttribute("caracteristicasModeloSelecionadas", caracteristicasJSON);
		
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupImagemModelo.jsp") })
	public Forward popupImagemModelo() {

		HttpServletRequest request = getRequest();
		String idModeloString = request.getParameter("id_modelo");
		request.setAttribute("flagNewModelo", new Boolean(request.getParameter("flagNewModelo")).booleanValue());
		request.setAttribute("id_modelo", idModeloString);
		Long idModelo = Long.valueOf(idModeloString);
		request.setAttribute("totalMultimidias", 0);
		
		request.setAttribute("caminho_link_imagens_modelo", ApplicationConfiguration.getCaminhoLinkImagensModelo());
		
		request.setAttribute("corPadraoLista", new ArrayList<ListaCores.Cor>());

		//Buscar no Novo serviço as Multimidias
		BuscarListaMultTMCorClassRequestDocument multTMCorClassRequestDocument = BuscarListaMultTMCorClassRequestDocument.Factory.newInstance();
		ParamBuscarListaMultTMCorClass paramBuscarListaMultTMCorClass = multTMCorClassRequestDocument.addNewBuscarListaMultTMCorClassRequest()
																			.addNewParamBuscarListaMultTMCorClass();
		paramBuscarListaMultTMCorClass.setIdGrupoProduto(idModelo);
		
		paramBuscarListaMultTMCorClass.addNewListaSgTipoMultimidia().setSgTipoMultimidiaArray( new String[]{"IMG", "VDO"});
		prepareServiceControl(multimidiaSoapServiceControl);
		BuscarListaMultTMCorClassResponseDocument multTMCorClassResponseDocument = multimidiaSoapServiceControl
																					.buscarListaMultTMCorClass(multTMCorClassRequestDocument);		
		ResultBuscarListaMultTMCorClass resultBuscarListaMultTMCorClass = multTMCorClassResponseDocument.getBuscarListaMultTMCorClassResponse()
																			.getResultBuscarListaMultTMCorClass();
		
		if(resultBuscarListaMultTMCorClass != null){
			List<br.com.vivo.sn.catalogoMultimidia.ResultBuscarListaMultTMCorClassDocument.ResultBuscarListaMultTMCorClass.Multimidia> multimidiaLista =
				resultBuscarListaMultTMCorClass.getMultimidiaList();
			request.setAttribute("multimidias", multimidiaLista);
			if(multimidiaLista!=null)
				request.setAttribute("totalMultimidias", multimidiaLista.size());
			else
				request.setAttribute("totalMultimidias", 0);
		}
		
		List<Cor> corLista = new ArrayList<Cor>();
		
		try {
			//Buscando lista cor
			BuscarListaCorRequestDocument listaCorRequestDocument = BuscarListaCorRequestDocument.Factory.newInstance();
			listaCorRequestDocument.addNewBuscarListaCorRequest();
			prepareServiceControl(multimidiaSoapServiceControl);
			BuscarListaCorResponseDocument listaCorResponseDocument = multimidiaSoapServiceControl.buscarListaCor(listaCorRequestDocument);
			corLista = listaCorResponseDocument.getBuscarListaCorResponse().getListaCor().getCorList();
		} catch (Exception e) {
			//System.out.println("Lista de Cores vazia...");
		}
		
		List<ClassificacaoMultimidia> classificacaoLista = new ArrayList<ClassificacaoMultimidia>();
		
		try {
			//Buscando lista classificação de Multimidia
			BuscarListaClassificacaoMultimidiaRequestDocument classificacaoMultimidiaRequestDocument = BuscarListaClassificacaoMultimidiaRequestDocument
																										.Factory.newInstance();
			classificacaoMultimidiaRequestDocument.addNewBuscarListaClassificacaoMultimidiaRequest();
			prepareServiceControl(multimidiaSoapServiceControl);
			BuscarListaClassificacaoMultimidiaResponseDocument classificacaoMultimidiaResponseDocument = multimidiaSoapServiceControl
																											.buscarListaClassificacaoMultimidia(classificacaoMultimidiaRequestDocument);
			classificacaoLista = classificacaoMultimidiaResponseDocument.getBuscarListaClassificacaoMultimidiaResponse().getListaClassificacaoMultimidia()
																.getClassificacaoMultimidiaList();
		} catch (Exception e) {
			//System.out.println("Lista de Classificação vazia...");
		}
		
		//Buscar CorPadrao Modelo
		BuscarModeloPorIdRequestDocument modeloPorIdRequestDocument = BuscarModeloPorIdRequestDocument.Factory.newInstance();
		ParametrosBuscarModeloPorId parametrosBuscarModeloPorId = modeloPorIdRequestDocument.addNewBuscarModeloPorIdRequest().addNewParametrosBuscarModeloPorId();
		parametrosBuscarModeloPorId.addNewParametrosModelosPorIdModeloIn().setIdModelo(idModelo);
		prepareServiceControl(grupoProdutoSoapServiceControl);
		BuscarModeloPorIdResponseDocument modeloPorIdResponseDocument = grupoProdutoSoapServiceControl.buscarModeloPorId(modeloPorIdRequestDocument);
		List<ModeloPorIdModelo> modeloLista = modeloPorIdResponseDocument.getBuscarModeloPorIdResponse().getResultadoBuscarModeloPorId()
												.getListaModelosPorIdModeloOut().getModeloPorIdModeloList();
		
		ModeloPorIdModelo modeloCorPadrao = modeloLista.get(0);
		
		//Buscar Cores do Modelo
		BuscarListaCoresPorModeloRequestDocument listaCoresPorModeloRequestDocument = BuscarListaCoresPorModeloRequestDocument.Factory.newInstance();
		ParametroBuscarListaCoresPorModelo parametroBuscarListaCoresPorModelo = listaCoresPorModeloRequestDocument.addNewBuscarListaCoresPorModeloRequest()
																					.addNewParametroBuscarListaCoresPorModelo();
		parametroBuscarListaCoresPorModelo.setIdModelo(idModelo);
		prepareServiceControl(produtosSoapServiceControl);
		BuscarListaCoresPorModeloResponseDocument listaCoresPorModeloResponseDocument = produtosSoapServiceControl.buscarListaCoresPorModelo(listaCoresPorModeloRequestDocument);
		List<ListaCores.Cor> corPadraoLista =
			listaCoresPorModeloResponseDocument.getBuscarListaCoresPorModeloResponse().getResultadoBuscarListaCoresPorModelo().getListaCores().getCorList();
		
		Forward forward = new Forward("success");
		
		forward.addActionOutput("corLista", corLista);
		forward.addActionOutput("classificacaoLista", classificacaoLista);
		forward.addActionOutput("modeloCorPadrao", modeloCorPadrao);
		
		request.setAttribute("corPadraoLista", corPadraoLista);
		
		return forward;
	}

	@Jpf.Action()
	public Forward alterarCaracteristicasModelo() throws CatalogoPRSException, IOException {
		HttpServletRequest request = getRequest();

		String idModeloString = request.getParameter("id_modelo");
		Long idModelo = null;
		if (idModeloString != null && !idModeloString.trim().equals("")) {
			idModelo = Long.valueOf(idModeloString);
		} else {
			throw new CatalogoPRSException("Erro inesperado.");
		}

		String caracteristicasJSON = request.getParameter("caracteristicas_selecionadas");
		
		Boolean flag_novo_modelo = new Boolean(request.getParameter("novo_modelo"));
		if (caracteristicasJSON == null || caracteristicasJSON.trim().length() == 0 || caracteristicasJSON.trim().equalsIgnoreCase("{}")){
			if(!(flag_novo_modelo)){
			  throw new CatalogoPRSException("Não é permitida a exclusão de todas as características. Se necessário exclua o modelo.");
		    }
			else{
			  writeJSOutput("fecharPopup('popup1');$('carregarAlterarModelo_" + idModelo + "').onclick();");
			  return null;
			}
		  }
				AlterarModeloCaracteristicaRequestDocument parametrosAlterarGrupoProdutoCaracteristica = AlterarModeloCaracteristicaRequestDocument.Factory
						.newInstance();
				ParametrosAssociarModeloCaracteristica parametrosAssociarModeloCaracteristica = parametrosAlterarGrupoProdutoCaracteristica
						.addNewAlterarModeloCaracteristicaRequest().addNewParametrosAlterarModeloCaracteristica().addNewParametrosAssociarModeloCaracteristica();
		
				parametrosAssociarModeloCaracteristica.setIdModelo(idModelo);
		
				try {
					JSONObject hashmap = new JSONObject(caracteristicasJSON);
					for (Iterator caracs = hashmap.keys(); caracs.hasNext();) {
						String idCaracteristica = (String) caracs.next();
						String[] valoresCaracteristicas = new String[] {};
						if (hashmap.get(idCaracteristica) != null && ((String) hashmap.get(idCaracteristica)).trim().length() > 0) {
							valoresCaracteristicas = splitAndClean((String) hashmap.get(idCaracteristica));
						}
						if (valoresCaracteristicas.length > 0) {
							for (String idValorCaracteristica : valoresCaracteristicas) {
								ValorCaracteristica valorCaracteristica = parametrosAssociarModeloCaracteristica
										.addNewValorCaracteristica();
								valorCaracteristica.setIdCaracteristica(Long.valueOf(idCaracteristica));
								valorCaracteristica.setIdValorCaracteristica(Long.valueOf(idValorCaracteristica));
							}
						} else {
							ValorCaracteristica valorCaracteristica = parametrosAssociarModeloCaracteristica
									.addNewValorCaracteristica();
							valorCaracteristica.setIdCaracteristica(Long.valueOf(idCaracteristica));
						}
		
					}
				} catch (JSONException e) {
					throw new CatalogoPRSException("Um erro inesperado ocorreu ao salvar as características do modelo.");
				}
		
				prepareServiceControl(grupoProdutoSoapServiceControl);
				grupoProdutoSoapServiceControl.alterarModeloCaracteristica(parametrosAlterarGrupoProdutoCaracteristica);
				//writeJSOutput("$('novo_modelo').hide();fecharPopup('popup1');" +  ((flag_novo_modelo) ? "$('botao_pesquisar').onclick()": null));
				//writeJSOutput("$('botao_pesquisar').onclick();if(abrirLink('novo_modelo', br/com/vivo/catalogoPRS/pageflows/param/produtos/paramModelos/carregarAlterarModelo.do?id_modelo=" + idModeloString + ", 'resultado_pesquisa')){clearAndShow('novo_modelo');fecharPopup('popup1');}return false;");
				//writeJSOutput("fecharPopup('popup1');" +  ((flag_novo_modelo) ? "$('botao_pesquisar').onclick()": null));
				//writeJSOutput("fecharPopup('popup1');$('botao_pesquisar').onclick();pauseReCall($('botao_pesquisar'), 1500);");
				//writeJSOutput("$('novo_modelo').hide();fecharPopup('popup1');" +  ((flag_novo_modelo) ? "$('botao_pesquisar').onclick()": null));
								
				//request.setAttribute("id_modelo", idModelo);
				
				//String href = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/paramModelos/carregarAlterarModelo.do?id_modelo=" + idModelo;
				//String output = "fecharPopup('popup1');if(abrirLink('novo_modelo', " + href + ", 'resultado_pesquisa')){clearAndShow('novo_modelo');}";
				String output = "fecharPopup('popup1');$('carregarAlterarModelo_" + idModelo + "').onclick();";
				
				writeJSOutput(output);
				
		//}else{
		//	writeJSOutput("$('novo_modelo').hide();fecharPopup('popup1');$('botao_pesquisar').onclick()");
		//}
		return null;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupAlterarCaracteristicas.jsp") })
	public Forward popupAlterarCaracteristica() throws CatalogoPRSException {

		HttpServletRequest request = getRequest();
		
		String nomeCaracteristica = request.getParameter("pesquisaInput");
		
		//System.out.println("Disponivel: " + request.getParameter("disponivel"));
		
		//Repassa flag indicando que as caracteriscas estão sendo associadas a um novo modelo
		
		forwardParameter("novo_modelo");
		forwardParameter("pesquisaInput");
		
		String idModeloString = request.getParameter("id_modelo");
		forwardParameter("id_modelo");
		String idModeloCopiadoString = request.getParameter("id_modelo_copiado");
		forwardParameter("id_modelo_copiado");
		Long idModelo = null;
		if (idModeloString != null && !idModeloString.trim().equals("")) {
			idModelo = Long.valueOf(idModeloString);
		} else {
			throw new CatalogoPRSException("Erro inesperado.");
		}

		caracteristicasSelecionadasAlterar = new String[]{};
		valoresCaracteristicasSelecionadasAlterar = new String[]{};
		
		int paginaSolicitada = 1;
		String paginaSolicitadaString = getRequest().getParameter("pagina_solicitada");
		if (paginaSolicitadaString != null && !paginaSolicitadaString.trim().equals("")) {
			paginaSolicitada = Integer.valueOf(paginaSolicitadaString);
		}
		

		try {
			
			if (nomeCaracteristica != null && !nomeCaracteristica.trim().equals("")) {
				logger.debug("Parametro pesquisa: " + nomeCaracteristica);
				buscarCaracteristicas(paginaSolicitada, nomeCaracteristica);
			} else {
				logger.debug("Parametro pesquisa (null): " + nomeCaracteristica);
				buscarCaracteristicas(paginaSolicitada, null);
			}
			
		} catch (Exception e) {
			logger.debug("Caracteristicas não encontradas para o critério de pesquisa.");
		}
		
		
		String caracteristicasJSON = request.getParameter("caracteristicas_selecionadas");

		if (caracteristicasJSON == null) {
			if(idModeloCopiadoString != null && idModeloCopiadoString.trim().length() > 0)
				caracteristicasJSON = caracteristicasModeloToJson(Long.valueOf(idModeloCopiadoString)).toString();
			else
				caracteristicasJSON = caracteristicasModeloToJson(Long.valueOf(idModelo)).toString();
		}
		

		if (caracteristicasJSON != null) {
			request.setAttribute("caracteristicas_selecionadas", caracteristicasJSON);
			
			String[][] resultado = extrairCaracteristicas(caracteristicasJSON);
			caracteristicasSelecionadasAlterar = resultado[0];
			valoresCaracteristicasSelecionadasAlterar = resultado[1];
		}

		Forward forward = new Forward("success");
		return forward;
	}

	private JSONObject caracteristicasModeloToJson(Long idModelo) {
		BuscarListaCaracteristicaProdutoModeloRequestDocument paramBuscarCaracteristicaProdutoModelo = BuscarListaCaracteristicaProdutoModeloRequestDocument.Factory.newInstance();
		BuscaCaracteristicaProdutoModelo buscaCaracteristicaProdutoModelo = paramBuscarCaracteristicaProdutoModelo.addNewBuscarListaCaracteristicaProdutoModeloRequest().addNewParametrosBuscarListaCaracteristicaProdutoModelo().addNewBuscaCaracteristicaProdutoModelo();
		buscaCaracteristicaProdutoModelo.setIdModelo(idModelo);
		JSONObject caracteristicas = new JSONObject();
		br.com.vivo.sn.catalogoModelo.ListaCaracteristicaDocument.ListaCaracteristica listaCaracteristica = null;

		prepareServiceControl(grupoProdutoSoapServiceControl);
		try{
		BuscarListaCaracteristicaProdutoModeloResponseDocument buscarListaCaracteristicaProdutoModeloResponseDocument = grupoProdutoSoapServiceControl
				.buscarListaCaracteristicaProdutoModelo(paramBuscarCaracteristicaProdutoModelo);
		BuscaCaracteristicaProdutoModeloOut buscaCaracteristicaProdutoModeloOut = buscarListaCaracteristicaProdutoModeloResponseDocument.getBuscarListaCaracteristicaProdutoModeloResponse().getResultadoBuscarListaCaracteristicaProdutoModelo().getBuscaCaracteristicaProdutoModeloOut();
		listaCaracteristica = buscaCaracteristicaProdutoModeloOut.getListaCaracteristica();
		
		}catch(com.bea.control.ServiceControlException exception){
			return caracteristicas;
		}
		List<Caracteristica> caracteristicaList = new ArrayList<Caracteristica>();
		
		if (listaCaracteristica != null) {
			caracteristicaList = listaCaracteristica.getCaracteristicaList();
			for (Caracteristica caracteristica : caracteristicaList) {
				br.com.vivo.sn.catalogoModelo.ListaValorCaracteristicaDocument.ListaValorCaracteristica listaValorCaracteristica = caracteristica.getListaValorCaracteristica();
				String valores = "";
				if (listaValorCaracteristica != null) {
					List<br.com.vivo.sn.catalogoModelo.ValorCaracteristicaDocument.ValorCaracteristica> valorCaracteristicaList = listaValorCaracteristica.getValorCaracteristicaList();
					int j = 0;
					for (br.com.vivo.sn.catalogoModelo.ValorCaracteristicaDocument.ValorCaracteristica valorCaracteristica : valorCaracteristicaList) {
						if (j == 0)
							valores = String.valueOf(valorCaracteristica.getIdValorCaracteristica());
						else
							valores += "," + String.valueOf(valorCaracteristica.getIdValorCaracteristica());
						j++;
					}
				}
				try {
					caracteristicas.put(String.valueOf(caracteristica.getIdCaracteristica()), valores);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

		}
		return caracteristicas;
	}

	@Jpf.Action()
	public Forward uploadImagem(br.com.vivo.catalogoPRS.pageflows.param.produtos.paramModelos.ParamModelosController.UploadImagemFormBean form)
			throws Exception {

		Long idModelo = form.getIdModelo();
		String nomeArquivo;
		if(form.getImagem() == null || form.getImagem().getFileData() == null || form.getImagem().getFileData().length == 0)
			throw new CatalogoPRSException("Por favor, selecione uma multimídia.");
		
		if (!form.getImagem().getFileName().toUpperCase().endsWith(".SWF") && !form.getImagem().getFileName().toUpperCase().endsWith(".GIF")
				&& !form.getImagem().getFileName().toUpperCase().endsWith(".JPG") && !form.getImagem().getFileName().toUpperCase().endsWith(".JPEG")
				&& !form.getImagem().getFileName().toUpperCase().endsWith(".PNG")) {
			throw new CatalogoPRSException("Arquivo com extensão inválida, só poderão ser inseridos imagens no formato GIF, JPEG, JPG e PNG, vídeos no formato .swf");
		}
		
		long qtdClassificacao;
		
		ObterExistenciaClassMultRequestDocument obterExistenciaClassMultRequestDocument = ObterExistenciaClassMultRequestDocument.Factory.newInstance();
		ParamObterExistenciaClassMult paramObterExistenciaClassMult = obterExistenciaClassMultRequestDocument.addNewObterExistenciaClassMultRequest().addNewParamObterExistenciaClassMult();
		paramObterExistenciaClassMult.setIdGrupoProduto(idModelo);
		
		if ( form.getImagem().getFileName().toUpperCase().endsWith(".SWF") ) {
			paramObterExistenciaClassMult.setSgTipoMultimidia("VDO");
			
			prepareServiceControl(multimidiaSoapServiceControl);
			ObterExistenciaClassMultResponseDocument obterExistenciaClassMultResponseDocument = multimidiaSoapServiceControl.obterExistenciaClassMult(obterExistenciaClassMultRequestDocument);
			qtdClassificacao = obterExistenciaClassMultResponseDocument.getObterExistenciaClassMultResponse().getResultObterExistenciaClassMult().getQtdOcorrencia();
			
			if ( qtdClassificacao > 0 ) {
				throw new CatalogoPRSException("Só é permitido o cadastro de 1 (um) vídeo no formato .swf");
			}
			
		} else {
			paramObterExistenciaClassMult.setSgTipoMultimidia("IMG");
			
			prepareServiceControl(multimidiaSoapServiceControl);
			ObterExistenciaClassMultResponseDocument obterExistenciaClassMultResponseDocument = multimidiaSoapServiceControl.obterExistenciaClassMult(obterExistenciaClassMultRequestDocument);
			qtdClassificacao = obterExistenciaClassMultResponseDocument.getObterExistenciaClassMultResponse().getResultObterExistenciaClassMult().getQtdOcorrencia();
			
			if ( qtdClassificacao > 0 ) {
				throw new CatalogoPRSException("É necessário classificar as imagens existentes para carregar uma nova imagem");
			}
		}
		
		try {
			nomeArquivo = ImagemUtil.salvarImagem(form.getImagem());
		} catch (CatalogoPRSException e) {
			throw new CatalogoPRSException("Erro ao salvar a multimídia no disco compartilhado.<br>"+e.getMessage(), e);
		}
		
		long idTipoMultimidia;
		
		if ( form.getImagem().getFileName().toUpperCase().endsWith(".SWF")) {	
			idTipoMultimidia = buscarIdTipoMultimidia("VDO");
		} else {			
			idTipoMultimidia = buscarIdTipoMultimidia("IMG");
		}

		AssociarMultimidiaRequestDocument paramCreateMultimidiaAssociacao = AssociarMultimidiaRequestDocument.Factory.newInstance();
		MultimidiaAssociacao multimidiaAssociacao = paramCreateMultimidiaAssociacao.addNewAssociarMultimidiaRequest().addNewParametrosAssociarMultimidia().addNewMultimidiaAssociacao();
		multimidiaAssociacao.setNmMultimidia(nomeArquivo);
		multimidiaAssociacao.setIdGrupoProduto(idModelo);
		
		if (idTipoMultimidia != 0) {
			multimidiaAssociacao.setIdTipoMultimidia(idTipoMultimidia);
		}

		prepareServiceControl(multimidiaSoapServiceControl);
		multimidiaSoapServiceControl.associarMultimidia(paramCreateMultimidiaAssociacao);

		String reloadScript = "<input type='text' value='reload_popup_imagens_modelo'/>";
		
		PrintWriter out;
		try {

			//getResponse().setContentType("text/javascript");
			out = this.getResponse().getWriter();
			out.println(reloadScript);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupApagarImagem.jsp") })
	public Forward popupApagarImagem() {

		HttpServletRequest request = getRequest();

		String idModeloString = request.getParameter("id_modelo");
		request.setAttribute("id_multimidia", request.getParameter("id_multimidia"));
		request.setAttribute("id_modelo", idModeloString);
		request.setAttribute("idCorPadrao", request.getParameter("idCorPadrao"));

		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action()
	public Forward apagarImagem(br.com.vivo.catalogoPRS.pageflows.param.produtos.paramModelos.ParamModelosController.ApagarImagemFormBean form)
			throws CatalogoPRSException {
		
		BuscarListaMultTMCorClassRequestDocument multTMCorClassRequestDocument = BuscarListaMultTMCorClassRequestDocument.Factory.newInstance();
		ParamBuscarListaMultTMCorClass paramBuscarListaMultTMCorClass = multTMCorClassRequestDocument.addNewBuscarListaMultTMCorClassRequest()
																			.addNewParamBuscarListaMultTMCorClass();
		paramBuscarListaMultTMCorClass.setIdGrupoProduto(form.getIdModelo());
		paramBuscarListaMultTMCorClass.addNewListaSgTipoMultimidia().setSgTipoMultimidiaArray(new String[]{"IMG", "VDO"});
		prepareServiceControl(multimidiaSoapServiceControl);
		
		BuscarListaMultTMCorClassResponseDocument multTMCorClassResponseDocument = multimidiaSoapServiceControl.buscarListaMultTMCorClass(multTMCorClassRequestDocument);
		ResultBuscarListaMultTMCorClass resultBuscarListaMultTMCorClass = multTMCorClassResponseDocument.getBuscarListaMultTMCorClassResponse()
																			.getResultBuscarListaMultTMCorClass();

		//BuscarListaImagemPorModeloRequestDocument paramBuscarImagemPorModelo = BuscarListaImagemPorModeloRequestDocument.Factory.newInstance();
		//ParametrosImagemPorModeloIn parametrosImagemPorModeloIn = paramBuscarImagemPorModelo.addNewBuscarListaImagemPorModeloRequest().addNewParametrosBuscarListaImagemPorModelo().addNewParametrosImagemPorModeloIn();
		//parametrosImagemPorModeloIn.setIdGrupoProduto(form.getIdModelo());

		//prepareServiceControl(multimidiaSoapServiceControl);
		//BuscarListaImagemPorModeloResponseDocument buscarListaImagemPorModeloResponseDocument = multimidiaSoapServiceControl.buscarListaImagemPorModelo(paramBuscarImagemPorModelo);
		//ResultadoBuscarListaImagemPorModelo resultadoBuscarListaImagemPorModelo = buscarListaImagemPorModeloResponseDocument.getBuscarListaImagemPorModeloResponse().getResultadoBuscarListaImagemPorModelo();
		if (resultBuscarListaMultTMCorClass == null)
			throw new CatalogoPRSException("Multimídia não encontrada.");
		//List<Multimidia> multimidiaList = resultadoBuscarListaImagemPorModelo.getMultimidiaList();
		List<Multimidia> multimidiaLista = resultBuscarListaMultTMCorClass.getMultimidiaList();
		if (multimidiaLista.size() == 0)
			throw new CatalogoPRSException("Multimídia não encontrada.");
		if (multimidiaLista.size() == 1)
			throw new CatalogoPRSException("Não é permitida a exclusão de todas as multimídias. Se necessário insira nova multimídia e depois exclua a atual.");		
		
		if (form.getIdCorPadrao() != null && form.getIdCorPadrao() != 0) {
			
			BuscarListaCoresPorModeloRequestDocument listaCoresPorModeloRequestDocument = BuscarListaCoresPorModeloRequestDocument.Factory.newInstance();
			ParametroBuscarListaCoresPorModelo parametroBuscarListaCoresPorModelo = listaCoresPorModeloRequestDocument.addNewBuscarListaCoresPorModeloRequest()
																						.addNewParametroBuscarListaCoresPorModelo();
			parametroBuscarListaCoresPorModelo.setIdModelo(form.getIdModelo());
			prepareServiceControl(produtosSoapServiceControl);
			BuscarListaCoresPorModeloResponseDocument listaCoresPorModeloResponseDocument = produtosSoapServiceControl.buscarListaCoresPorModelo(listaCoresPorModeloRequestDocument);
			List<ListaCores.Cor> corLista =
				listaCoresPorModeloResponseDocument.getBuscarListaCoresPorModeloResponse().getResultadoBuscarListaCoresPorModelo().getListaCores().getCorList();

			
			for (Multimidia mult : multimidiaLista) {
				
				if (mult.getIdMultimidia() == form.getIdEntidade()) {
					
					if (mult.getIdCor() == form.getIdCorPadrao()) {
						for (ListaCores.Cor cor : corLista) {
							
							if (cor.getIdCor() == mult.getIdCor()) {
								
								if (cor.getQuantidade() == 1) {
									throw new CatalogoPRSException("Antes de remover esta imagem, selecione uma nova Cor Padrão para o Modelo.");
								} else {
									break;
								}
								
							}
						}
					}
					break;
				}
			}	
		}
		
		
		DesassociarListaMultimidiaRequestDocument paramDeleteListaMultimidiaDesassociacao = DesassociarListaMultimidiaRequestDocument.Factory.newInstance();
		MultimidiaDesassociacao multimidiaDesassociacao = paramDeleteListaMultimidiaDesassociacao.addNewDesassociarListaMultimidiaRequest().addNewParametrosDesassociarListaMultimidia().addNewListaMultimidiaDesassociacao().addNewMultimidiaDesassociacao();
		multimidiaDesassociacao.setIdMultimidia(form.getIdEntidade());

		prepareServiceControl(multimidiaSoapServiceControl);
		multimidiaSoapServiceControl.desassociarListaMultimidia(paramDeleteListaMultimidiaDesassociacao);
		
		//Atualiza as Cores do Modelo
		BuscarListaCoresPorModeloRequestDocument listaCoresPorModeloRequestDocument = BuscarListaCoresPorModeloRequestDocument.Factory.newInstance();
		ParametroBuscarListaCoresPorModelo parametroBuscarListaCoresPorModelo = listaCoresPorModeloRequestDocument.addNewBuscarListaCoresPorModeloRequest()
																					.addNewParametroBuscarListaCoresPorModelo();
		parametroBuscarListaCoresPorModelo.setIdModelo(form.getIdModelo());
		prepareServiceControl(produtosSoapServiceControl);
		BuscarListaCoresPorModeloResponseDocument listaCoresPorModeloResponseDocument = produtosSoapServiceControl.buscarListaCoresPorModelo(listaCoresPorModeloRequestDocument);
		List<ListaCores.Cor> corPadraoLista =
			listaCoresPorModeloResponseDocument.getBuscarListaCoresPorModeloResponse().getResultadoBuscarListaCoresPorModelo().getListaCores().getCorList();

		getRequest().setAttribute("corPadraoLista", corPadraoLista);

		String reloadScript = "$('reload_popup_imagens_modelo').onclick();";

		PrintWriter out;
		try {
			getResponse().setContentType("text/javascript");
			out = this.getResponse().getWriter();
			out.println(reloadScript);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Jpf.Action()
	public Forward alterarFimVidaModelo() throws IOException {
		HttpServletRequest request = getRequest();
		String idGrupoProduto = request.getParameter("id_grupo_produto");

		AlterarFimVidaModeloRequestDocument paramFimVidaGrupoProduto = AlterarFimVidaModeloRequestDocument.Factory.newInstance();
		ParametrosFimVidaModelo parametrosFimVidaModelo = paramFimVidaGrupoProduto.addNewAlterarFimVidaModeloRequest().addNewParametrosAlterarFimVidaModelo().addNewParametrosFimVidaModelo();
		parametrosFimVidaModelo.setIdModelo(Long.valueOf(idGrupoProduto));
		parametrosFimVidaModelo.setInFimVida(request.getParameter("checked"));

		prepareServiceControl(grupoProdutoSoapServiceControl);
		grupoProdutoSoapServiceControl.alterarFimVidaModelo(paramFimVidaGrupoProduto);

		writeJSOutput("$('fim_vida_" + idGrupoProduto + "').checked=!$('fim_vida_" + idGrupoProduto + "').checked;");

		return null;
	}

	@Jpf.Action()
	public Forward alterarDispModelo() throws IOException {
		HttpServletRequest request = getRequest();
		String idGrupoProduto = request.getParameter("id_grupo_produto");
		
	    // No caso de disponibilizacao do modelo, verifica se tem, no mínimo, 1 caracteritica disponivel e 1 imagem cadastradas
		if (request.getParameter("checked").equalsIgnoreCase("S")){
			BuscarListaCaracteristicaDispProdutoModeloRequestDocument paramBuscarCaracteristicaDispProdutoModelo = BuscarListaCaracteristicaDispProdutoModeloRequestDocument.Factory.newInstance();
			BuscaCaracteristicaDispProdutoModelo buscaCaracteristicaDispProdutoModelo = paramBuscarCaracteristicaDispProdutoModelo.addNewBuscarListaCaracteristicaDispProdutoModeloRequest().addNewParametrosBuscarListaCaracteristicaDispProdutoModelo().addNewBuscaCaracteristicaDispProdutoModelo();
			buscaCaracteristicaDispProdutoModelo.setIdModelo(Long.valueOf(idGrupoProduto));
			
			prepareServiceControl(grupoProdutoSoapServiceControl);
			BuscarListaCaracteristicaDispProdutoModeloResponseDocument buscarListaCaracteristicaDispProdutoModeloRequestDocument;
			
			try {
				buscarListaCaracteristicaDispProdutoModeloRequestDocument = grupoProdutoSoapServiceControl.buscarListaCaracteristicaDispProdutoModelo(paramBuscarCaracteristicaDispProdutoModelo);
			} catch (ServiceControlException e) {
				if(e.getSoapFault().getDetail().getDomNode().getFirstChild().getFirstChild().getFirstChild().getNodeValue().equals("11021")) {
					writeJSOutput("clearErrors();");
					writeJSOutput("addError('Característica devem estar cadastradas.', true);");
					writeJSOutput("abrirPopupErros();");		
					return null;
				} else {
					throw e;
				}
			}
			
			BuscarListaImagemPorModeloRequestDocument paramBuscarImagemPorModelo = BuscarListaImagemPorModeloRequestDocument.Factory.newInstance();
			ParametrosImagemPorModeloIn parametrosImagemPorModeloIn = paramBuscarImagemPorModelo.addNewBuscarListaImagemPorModeloRequest().addNewParametrosBuscarListaImagemPorModelo().addNewParametrosImagemPorModeloIn();
			parametrosImagemPorModeloIn.setIdGrupoProduto(Long.valueOf(idGrupoProduto));
	
			prepareServiceControl(multimidiaSoapServiceControl);
			ResultadoBuscarListaImagemPorModelo resultadoBuscarListaImagemPorModelo;
			
			resultadoBuscarListaImagemPorModelo = multimidiaSoapServiceControl.buscarListaImagemPorModelo(paramBuscarImagemPorModelo)
			.getBuscarListaImagemPorModeloResponse().getResultadoBuscarListaImagemPorModelo();

			if(resultadoBuscarListaImagemPorModelo.getDomNode().getFirstChild() == null) {
				writeJSOutput("clearErrors();");
				writeJSOutput("addError('Imagens devem estar cadastradas.', true);");
				writeJSOutput("abrirPopupErros();");		
				return null;
			}
		}
		DisponibilizarModeloRequestDocument paramDisponibilizarGrupoProduto = DisponibilizarModeloRequestDocument.Factory.newInstance();
		ListaIndisponibilizaModelo listaIndisponibilizaModelo = paramDisponibilizarGrupoProduto.addNewDisponibilizarModeloRequest().addNewParametrosDisponibilizarModelo().addNewListaIndisponibilizaModelo();
		ParametrosDispModelo parametrosDispGrupoProduto = listaIndisponibilizaModelo.addNewParametrosDispModelo();
		parametrosDispGrupoProduto.setIdModelo(Long.valueOf(idGrupoProduto));
		parametrosDispGrupoProduto.setInDisponivel(request.getParameter("checked"));

		prepareServiceControl(grupoProdutoSoapServiceControl);
		grupoProdutoSoapServiceControl.disponibilizarModelo(paramDisponibilizarGrupoProduto);

		writeJSOutput("$('disp_" + idGrupoProduto + "').checked=!$('disp_" + idGrupoProduto + "').checked;");

		return null;
	}

	@Jpf.Action()
	public Forward criarModelo(br.com.vivo.catalogoPRS.pageflows.param.produtos.paramModelos.ParamModelosController.NovoModeloFormBean form) throws IOException {
		
		HttpServletRequest request = getRequest();
		
		CriarModeloRequestDocument parametrosCriacaoModelo = CriarModeloRequestDocument.Factory
				.newInstance();
		ParametrosModelo parametrosModelo = parametrosCriacaoModelo.addNewCriarModeloRequest().addNewParametrosCriarModelo().addNewParametrosModelo();
		parametrosModelo.setIdTipoProduto(form.getIdTipoProduto());
		if (form.getIdFabricante() != null)
			parametrosModelo.setIdFabricante(form.getIdFabricante());
		else
			parametrosModelo.setIdFabricante(-1);
		parametrosModelo.setNmModelo(form.getNomeComercial());
		extrairTecnologiasFrequencias(form.getTecnologiasFrequencias());
		if (tecnologiasFrequencias_tecnologiasSelecionadas != null) {
			for (String idTecnologiaString : tecnologiasFrequencias_tecnologiasSelecionadas) {
				br.com.vivo.sn.catalogoModelo.ParametrosModeloDocument.ParametrosModelo.Tecnologia tecnologia = parametrosModelo.addNewTecnologia();
				tecnologia.setIdTecnologia(Long.valueOf(idTecnologiaString));
				List<String> tiposFrequenciaTecnologia = tecnologiasFrequencias_tiposFrequenciaSelecionadas.get(idTecnologiaString);
				for (String idTipoFrequenciaString : tiposFrequenciaTecnologia) {
					List<String> frequencias = tecnologiasFrequencias_frequenciaSelecionadas.get(idTecnologiaString).get(idTipoFrequenciaString);
					if (frequencias != null) {
						for (String idtecnologiaTipoFrequenciaValor : frequencias) {
							tecnologia.addIdTecnologiaTpFrequenciaVl(Long.valueOf(idtecnologiaTipoFrequenciaValor));
						}
					}
				}
			}
		}
		parametrosModelo.setIndisponivel(form.getDisponivel());
		parametrosModelo.setInFimVida(form.getFimVida());
		
		logger.debug("Destaque Comercial: " + form.getDsNota());
		
		parametrosModelo.setDsNota(form.getDsNota());
		
		prepareServiceControl(grupoProdutoSoapServiceControl);
		CriarModeloResponseDocument criarModeloResponseDocument = grupoProdutoSoapServiceControl.criarModelo(parametrosCriacaoModelo);
		Long idModelo = criarModeloResponseDocument.getCriarModeloResponse().getResultadoCriarModelo().getIDModelo();
		
		if ((form.getUrl() != null) && (! form.getUrl().trim().equals(""))) {
			
			long idTipoMultimidia = buscarIdTipoMultimidia("LNK");
			
			AssociarMultimidiaRequestDocument associarMultimidiaRequestDocument = AssociarMultimidiaRequestDocument.Factory.newInstance();
			ParametrosAssociarMultimidia parametrosAssociarMultimidia = associarMultimidiaRequestDocument.addNewAssociarMultimidiaRequest()
																			.addNewParametrosAssociarMultimidia();
			MultimidiaAssociacao multimidiaAssociacao = parametrosAssociarMultimidia.addNewMultimidiaAssociacao();
			multimidiaAssociacao.setIdGrupoProduto(idModelo);
			multimidiaAssociacao.setNmMultimidia(form.getUrl());
			
			if (idTipoMultimidia != 0) {
				multimidiaAssociacao.setIdTipoMultimidia(idTipoMultimidia);
			}
			
			prepareServiceControl(multimidiaSoapServiceControl);
			multimidiaSoapServiceControl.associarMultimidia(associarMultimidiaRequestDocument);
		
		}
		
		request.setAttribute("id_modelo", idModelo);
		
		String output = "$('btn_limpar').onclick();$('id_tipo_produto').value=" + form.getIdTipoProduto() + ";$('select_fabricante').value=" + form.getIdFabricante() + ";$('botao_pesquisar').onclick();";
		
		if (form.getTipoSubmit().equals("Carac")) {
			output += "abrirPopup1('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/paramModelos/popupAlterarCaracteristica.do?id_modelo="
				+ idModelo + "&novo_modelo=true";
			output += "', null, 'right_section');";
		} else {
			
			if (form.getTipoSubmit().equals("Mult")) {
				output += "abrirPopup1('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/paramModelos/popupImagemModelo.do?id_modelo="
					+ idModelo + "&flagNewModelo=true";
				output += "', null, 'novo_modelo');";
			} else {
				
				if (form.getTipoSubmit().equals("CaracCopy")) {
					
					if (form.getCaracteristicas() != null && !form.getCaracteristicas().trim().equals("")) {
						associarCaracteristicasModelo(idModelo, form.getCaracteristicas());
					}
					
					output += "abrirPopup1('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/paramModelos/popupAlterarCaracteristica.do?id_modelo="
						+ idModelo + "&novo_modelo=true&id_modelo_copiado=" + form.getIdModelo();
					output += "', null, 'right_section');";
				} else {
					
					if (form.getTipoSubmit().equals("btnCopy") || form.getTipoSubmit().equals("btnMult")) {
						
						if (form.getCaracteristicas() != null && !form.getCaracteristicas().trim().equals("")) {
							associarCaracteristicasModelo(idModelo, form.getCaracteristicas());
						}
						
						if (form.getTipoSubmit().equals("btnMult")) {
							output += "abrirPopup1('/catalogo/br/com/vivo/catalogoPRS/pageflows/param/produtos/paramModelos/popupImagemModelo.do?id_modelo="
								+ idModelo + "&flagNewModelo=true";
							output += "', null, 'novo_modelo');";
						}
						
					}
				}
				
			}
		}
		
		writeJSOutput(output);
		form.setTecnologiasFrequencias("");
		return null;
	}

	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "novoModelo.jsp") })
	public Forward abrirNovoModelo() {
		caracteristicasSelecionadasAlterar = new String[] {};
		tecnologiasFrequencias_tecnologiasSelecionadas = null;
		tecnologiasFrequencias_tiposFrequenciaSelecionadas = null;
		tecnologiasFrequencias_frequenciaSelecionadas = null;
		
		HttpServletRequest request = getRequest();
		
		Long idTipoProduto = Long.valueOf(request.getParameter("id_tipo_produto"));
		forwardParameter("id_tipo_produto");
		forwardParameter("select_fabricante");
		
		prepareServiceControl(fabricanteSoapServiceControl);
		List<Fabricante> listaFabricantes = FabricanteService.getInstance().buscarListaFabricantePorTipoProduto(fabricanteSoapServiceControl, idTipoProduto);
		
		if (listaFabricantes != null) {
			request.setAttribute("fabricantes", listaFabricantes);
		}
		
		prepareServiceControl(tipoProdutoSoapServiceControl);
		List<TipoProduto> tipoProdutoList = TipoProdutoService.getInstance().buscarListaTipoProduto(tipoProdutoSoapServiceControl);

		request.setAttribute("tipos_produto", tipoProdutoList);
		Forward forward = new Forward("success");
		return forward;
	}

	@Jpf.Action()
	public Forward alterarModelo(br.com.vivo.catalogoPRS.pageflows.param.produtos.paramModelos.ParamModelosController.NovoModeloFormBean form)
			throws IOException, CatalogoPRSException {

		BuscarModeloPorIdRequestDocument paramBuscarModeloPorIdModelo = BuscarModeloPorIdRequestDocument.Factory.newInstance();
		ParametrosModelosPorIdModeloIn parametrosModelosPorIdModeloIn = paramBuscarModeloPorIdModelo.addNewBuscarModeloPorIdRequest().addNewParametrosBuscarModeloPorId().addNewParametrosModelosPorIdModeloIn();
		parametrosModelosPorIdModeloIn.setIdModelo(form.getIdModelo());

		prepareServiceControl(grupoProdutoSoapServiceControl);
		BuscarModeloPorIdResponseDocument buscarModeloPorIdResponseDocument = grupoProdutoSoapServiceControl.buscarModeloPorId(paramBuscarModeloPorIdModelo);
		ListaModelosPorIdModeloOut listaModelosPorIdModeloOut = buscarModeloPorIdResponseDocument.getBuscarModeloPorIdResponse().getResultadoBuscarModeloPorId().getListaModelosPorIdModeloOut();
		if(listaModelosPorIdModeloOut == null || listaModelosPorIdModeloOut.getModeloPorIdModeloList() == null || listaModelosPorIdModeloOut.getModeloPorIdModeloList().size()==0)
			throw new CatalogoPRSException("Modelo não encontrado.");
		
		ModeloPorIdModelo modelo = listaModelosPorIdModeloOut.getModeloPorIdModeloList().get(0);

		AlterarModeloRequestDocument parametrosAlterarModelo = AlterarModeloRequestDocument.Factory.newInstance();
		ParametrosModelo parametrosModelo = parametrosAlterarModelo.addNewAlterarModeloRequest().addNewParametrosAlterarModelo().addNewParametrosModelo();
		
		parametrosModelo.setIdTipoProduto(form.getIdTipoProduto());
		
		if (modelo.getQtdProdutosAfetados() == 0) {
			if(form.getIdFabricante()!=null)
				parametrosModelo.setIdFabricante(form.getIdFabricante());
			else
				parametrosModelo.setIdFabricante(-1);
		} else {
			parametrosModelo.setIdFabricante(modelo.getIdFabricante());
		}
		
		parametrosModelo.setNmModelo(form.getNomeComercial());
		parametrosModelo.setIdModelo(modelo.getIdModelo());
		
		//if(modelo.getInDisponivel()!=null)
			//parametrosModelo.setIndisponivel(modelo.getInDisponivel());
			parametrosModelo.setIndisponivel(form.getDisponivel());
		//if(modelo.getInFimVida() != null)
			//parametrosModelo.setInFimVida(modelo.getInFimVida());
			parametrosModelo.setInFimVida(form.getFimVida());
		
		extrairTecnologiasFrequencias(form.getTecnologiasFrequencias());
		if (tecnologiasFrequencias_tecnologiasSelecionadas != null) {
			for (String idTecnologiaString : tecnologiasFrequencias_tecnologiasSelecionadas) {
				br.com.vivo.sn.catalogoModelo.ParametrosModeloDocument.ParametrosModelo.Tecnologia tecnologia = parametrosModelo.addNewTecnologia();
				tecnologia.setIdTecnologia(Long.valueOf(idTecnologiaString));
				List<String> tiposFrequenciaTecnologia = tecnologiasFrequencias_tiposFrequenciaSelecionadas.get(idTecnologiaString);
				for (String idTipoFrequenciaString : tiposFrequenciaTecnologia) {
					List<String> frequencias = tecnologiasFrequencias_frequenciaSelecionadas.get(idTecnologiaString).get(idTipoFrequenciaString);
					if (frequencias != null) {
						for (String idtecnologiaTipoFrequenciaValor : frequencias) {
							tecnologia.addIdTecnologiaTpFrequenciaVl(Long.valueOf(idtecnologiaTipoFrequenciaValor));
						}	
					}
				}
			}
		}

		parametrosModelo.setDsNota(form.getDsNota());
		
		if (modelo.getIdCorPadrao() != 0) {
			parametrosModelo.setIdCorPadrao(modelo.getIdCorPadrao());
		}
		
		if (modelo.getLink() != null) {
			
			if ((form.getUrl() != null) && (! form.getUrl().trim().equals(""))) {
				AlterarMultimidiaRequestDocument requestDocumentMultimidia = AlterarMultimidiaRequestDocument.Factory.newInstance();
				ParamAlterarMultimidia paramAlterarMultimidia = requestDocumentMultimidia.addNewAlterarMultimidiaRequest()
																	.addNewParamAlterarMultimidia();
				paramAlterarMultimidia.setIdMultimidia(modelo.getLink().getIdMultimidiaLink());
				paramAlterarMultimidia.setNmMultimidia(form.getUrl());
				prepareServiceControl(multimidiaSoapServiceControl);
				multimidiaSoapServiceControl.alterarMultimidia(requestDocumentMultimidia);
			} else {
				DesassociarListaMultimidiaRequestDocument desassociarListaMultimidiaRequestDocument = DesassociarListaMultimidiaRequestDocument.Factory.newInstance();
				MultimidiaDesassociacao multimidiaDesassociacao = desassociarListaMultimidiaRequestDocument.addNewDesassociarListaMultimidiaRequest().addNewParametrosDesassociarListaMultimidia().addNewListaMultimidiaDesassociacao()
																	.addNewMultimidiaDesassociacao();
				multimidiaDesassociacao.setIdMultimidia(modelo.getLink().getIdMultimidiaLink());

				prepareServiceControl(multimidiaSoapServiceControl);
				multimidiaSoapServiceControl.desassociarListaMultimidia(desassociarListaMultimidiaRequestDocument);
			}
			
		} else {
			
			if ((form.getUrl() != null) && (! form.getUrl().trim().equals(""))) {
			
				long idTipoMultimidia = buscarIdTipoMultimidia("LNK");
				
				AssociarMultimidiaRequestDocument associarMultimidiaRequestDocument = AssociarMultimidiaRequestDocument.Factory.newInstance();
				ParametrosAssociarMultimidia parametrosAssociarMultimidia = associarMultimidiaRequestDocument.addNewAssociarMultimidiaRequest()
																				.addNewParametrosAssociarMultimidia();
				MultimidiaAssociacao multimidiaAssociacao = parametrosAssociarMultimidia.addNewMultimidiaAssociacao();
				multimidiaAssociacao.setIdGrupoProduto(form.getIdModelo());
				multimidiaAssociacao.setNmMultimidia(form.getUrl());
				
				if (idTipoMultimidia != 0) {
					multimidiaAssociacao.setIdTipoMultimidia(idTipoMultimidia);
				}
				
				prepareServiceControl(multimidiaSoapServiceControl);
				multimidiaSoapServiceControl.associarMultimidia(associarMultimidiaRequestDocument);
			
			}
		}

		prepareServiceControl(grupoProdutoSoapServiceControl);
		grupoProdutoSoapServiceControl.alterarModelo(parametrosAlterarModelo);
		
		

		writeJSOutput("$('botao_pesquisar').onclick();");

		form.setTecnologiasFrequencias("");
		return null;
	}

	@Jpf.Action()
	public Forward apagarModelo(br.com.vivo.catalogoPRS.pageflows.shared.form.ApagarFormBean form) throws IOException {

		ExcluirListaModeloRequestDocument excluirListaModeloRequestDocument = ExcluirListaModeloRequestDocument.Factory.newInstance();
		ModeloExclusao modeloExclusao = excluirListaModeloRequestDocument.addNewExcluirListaModeloRequest().addNewParametrosExcluirListaModelo().addNewListaModeloExclusao().addNewModeloExclusao();
		modeloExclusao.setIdModelo(form.getIdEntidade());

		prepareServiceControl(grupoProdutoSoapServiceControl);
		grupoProdutoSoapServiceControl.excluirListaModelo(excluirListaModeloRequestDocument);

		writeJSOutput("$('botao_pesquisar').onclick();");
		return null;
	}

	private void buscarCaracteristicas(int paginaSolicitada, String nomeCaracteristica) throws ServiceControlException {
		
		HttpServletRequest request = getRequest();
		request.setAttribute("caracteristicas", new ArrayList<br.com.vivo.sn.catalogoCaracteristica.ListaCaracteristicaDocument.ListaCaracteristica.Caracteristica>());		
		
		BuscarListaCaracteristicaComValorRequestDocument param = BuscarListaCaracteristicaComValorRequestDocument.Factory.newInstance();
		//br.com.vivo.sn.catalogoGeral.PaginacaoInDocument.PaginacaoIn paginacaoIn = param.addNewBuscarListaCaracteristicaComValorRequest().addNewParametrosBuscarListaCaracteristicaComValor().addNewPaginacaoIn();
		
		ParametrosBuscarListaCaracteristicaComValor parametrosBuscarListaCaracteristicaComValor = param.addNewBuscarListaCaracteristicaComValorRequest().addNewParametrosBuscarListaCaracteristicaComValor();
		
		br.com.vivo.sn.catalogoGeral.PaginacaoInDocument.PaginacaoIn paginacaoIn = parametrosBuscarListaCaracteristicaComValor.addNewPaginacaoIn();

		
		int elementosPorPagina = ApplicationConfiguration.getElementosPorPaginaNosPopups();
		paginacaoIn.setItensPorPagina(elementosPorPagina);
		paginacaoIn.setPaginaSolicitada(paginaSolicitada);
		
		if (nomeCaracteristica != null) {
			parametrosBuscarListaCaracteristicaComValor.setNmCaracteristica(nomeCaracteristica);
		}
		
		prepareServiceControl(caracteristicaSoapServiceControl);
		BuscarListaCaracteristicaComValorResponseDocument buscarListaCaracteristicaComValorResponseDocument = caracteristicaSoapServiceControl.buscarListaCaracteristicaComValor(param);

		ResultadoCaracteristica resultadoCaracteristica = buscarListaCaracteristicaComValorResponseDocument.getBuscarListaCaracteristicaComValorResponse().getResultadoCaracteristica();
		br.com.vivo.sn.catalogoCaracteristica.ListaCaracteristicaDocument.ListaCaracteristica listaCaracteristica = resultadoCaracteristica.getListaCaracteristica();
		List<br.com.vivo.sn.catalogoCaracteristica.ListaCaracteristicaDocument.ListaCaracteristica.Caracteristica> vlcaracteristicaList;
		if (listaCaracteristica != null)
			vlcaracteristicaList = listaCaracteristica.getCaracteristicaList();
		else
			vlcaracteristicaList = new ArrayList<br.com.vivo.sn.catalogoCaracteristica.ListaCaracteristicaDocument.ListaCaracteristica.Caracteristica>();

		br.com.vivo.sn.catalogoGeral.PaginacaoOutDocument.PaginacaoOut paginacaoOut = resultadoCaracteristica.getPaginacaoOut();
		
		tratarPaginacao(paginacaoOut, elementosPorPagina);
		
		request.setAttribute("caracteristicas", vlcaracteristicaList);
	}
	
	@Jpf.Action()
	public Forward classificarMultimidia(ClassificarMultimidiaFormBean form) throws CatalogoPRSException {
		
		ObterExistenciaClassMultRequestDocument obterExistenciaClassMultRequestDocument = ObterExistenciaClassMultRequestDocument.Factory.newInstance();
		ParamObterExistenciaClassMult paramObterExistenciaClassMult = obterExistenciaClassMultRequestDocument.addNewObterExistenciaClassMultRequest().addNewParamObterExistenciaClassMult();
		paramObterExistenciaClassMult.setIdGrupoProduto(form.getIdModelo());
		paramObterExistenciaClassMult.setSgTipoMultimidia("IMG");
		
		if (form.getIdClassificacao() != null) {
			paramObterExistenciaClassMult.setIdClassificacao(form.getIdClassificacao());
		}
		
		if (form.getIdCor() != null) {
			paramObterExistenciaClassMult.setIdCor(form.getIdCor());
		}
		
		prepareServiceControl(multimidiaSoapServiceControl);
		ObterExistenciaClassMultResponseDocument obterExistenciaClassMultResponseDocument = multimidiaSoapServiceControl.obterExistenciaClassMult(obterExistenciaClassMultRequestDocument);
		ResultObterExistenciaClassMult resultObterExistenciaClassMult = obterExistenciaClassMultResponseDocument.getObterExistenciaClassMultResponse().getResultObterExistenciaClassMult();
		
		if ( resultObterExistenciaClassMult.getQtdOcorrencia() > 0 ) {
			throw new CatalogoPRSException("Já existe uma imagem classificada como '" + ( resultObterExistenciaClassMult.getNmClassificacao() == null ? "" : resultObterExistenciaClassMult.getNmClassificacao() ) +
					"' na cor '" + ( resultObterExistenciaClassMult.getNmCor() == null ? "" : resultObterExistenciaClassMult.getNmCor() ) + "' para este modelo. Favor selecionar uma nova classificação");
		}
		
		AlterarMultimidiaRequestDocument alterarMultimidiaRequestDocument = AlterarMultimidiaRequestDocument.Factory.newInstance();
		ParamAlterarMultimidia paramAlterarMultimidia = alterarMultimidiaRequestDocument.addNewAlterarMultimidiaRequest()
															.addNewParamAlterarMultimidia();
		paramAlterarMultimidia.setIdMultimidia(form.getIdMultimidia());
		
		if (form.getIdClassificacao() != null) {
			paramAlterarMultimidia.setIdClassificacao(form.getIdClassificacao());
		}
		
		if (form.getIdCor() != null) {
			
			BuscarListaMultimidiaRequestDocument buscarListaMultimidiaRequestDocument = BuscarListaMultimidiaRequestDocument.Factory.newInstance();
			ParamBuscarListaMultimidia paramBuscarListaMultimidia = buscarListaMultimidiaRequestDocument.addNewBuscarListaMultimidiaRequest()
																		.addNewParamBuscarListaMultimidia();
			paramBuscarListaMultimidia.setIdMultimidia(form.getIdMultimidia());
			prepareServiceControl(multimidiaSoapServiceControl);
			BuscarListaMultimidiaResponseDocument buscarListaMultimidiaResponseDocument = multimidiaSoapServiceControl.buscarListaMultimidia(buscarListaMultimidiaRequestDocument);
			ListaMultimidia.Multimidia mult = buscarListaMultimidiaResponseDocument.getBuscarListaMultimidiaResponse().getResultBuscarListaMultimidia()
												.getListaMultimidia().getMultimidiaArray(0);
			
			if (mult.getIdCor() == form.getIdCorPadrao() && form.getIdCorPadrao() != null) {
				
				//Buscar Cores do Modelo
				BuscarListaCoresPorModeloRequestDocument coresPorModeloRequestDocument = BuscarListaCoresPorModeloRequestDocument.Factory.newInstance();
				ParametroBuscarListaCoresPorModelo paramBuscarListaCoresPorModelo = coresPorModeloRequestDocument.addNewBuscarListaCoresPorModeloRequest()
																							.addNewParametroBuscarListaCoresPorModelo();
				paramBuscarListaCoresPorModelo.setIdModelo(form.getIdModelo());
				prepareServiceControl(produtosSoapServiceControl);
				BuscarListaCoresPorModeloResponseDocument coresPorModeloResponseDocument = produtosSoapServiceControl.buscarListaCoresPorModelo(coresPorModeloRequestDocument);
				List<ListaCores.Cor> corLista =
					coresPorModeloResponseDocument.getBuscarListaCoresPorModeloResponse().getResultadoBuscarListaCoresPorModelo().getListaCores().getCorList();

				
				if (corLista.size() != 1) {
					for (ListaCores.Cor cor : corLista) {
						
						if (cor.getIdCor() == mult.getIdCor()) {
							
							if (cor.getQuantidade() == 1) {
								throw new CatalogoPRSException("Antes de alterar a Cor desta imagem, selecione uma nova Cor Padrão para o Modelo.");
							} else {
								break;
							}
							
						}
					}
				}
				
			}
			
			paramAlterarMultimidia.setIdCor(form.getIdCor());
			
			prepareServiceControl(multimidiaSoapServiceControl);
			multimidiaSoapServiceControl.alterarMultimidia(alterarMultimidiaRequestDocument);
			
			//Buscar Cores do Modelo
			BuscarListaCoresPorModeloRequestDocument listaCoresPorModeloRequestDocument = BuscarListaCoresPorModeloRequestDocument.Factory.newInstance();
			ParametroBuscarListaCoresPorModelo parametroBuscarListaCoresPorModelo = listaCoresPorModeloRequestDocument.addNewBuscarListaCoresPorModeloRequest()
																						.addNewParametroBuscarListaCoresPorModelo();
			parametroBuscarListaCoresPorModelo.setIdModelo(form.getIdModelo());
			prepareServiceControl(produtosSoapServiceControl);
			BuscarListaCoresPorModeloResponseDocument listaCoresPorModeloResponseDocument = produtosSoapServiceControl.buscarListaCoresPorModelo(listaCoresPorModeloRequestDocument);
			List<ListaCores.Cor> corPadraoLista =
				listaCoresPorModeloResponseDocument.getBuscarListaCoresPorModeloResponse().getResultadoBuscarListaCoresPorModelo().getListaCores().getCorList();

			getRequest().setAttribute("corPadraoLista", corPadraoLista);
			
			if (corPadraoLista.size() == 1 && corPadraoLista.get(0).getQuantidade() == 1) {
				
				UpdateGrupoProdutoRequestDocument grupoProdutoRequestDocument = UpdateGrupoProdutoRequestDocument.Factory.newInstance();
				ParamUpdateGrupoProduto paramUpdateGrupoProduto = grupoProdutoRequestDocument.addNewUpdateGrupoProdutoRequest().addNewParamUpdateGrupoProduto();
				paramUpdateGrupoProduto.setIdGrupoProduto(form.getIdModelo());
				paramUpdateGrupoProduto.setDsNota(form.getDsNotaModelo());
				paramUpdateGrupoProduto.setIdCorPadrao(form.getIdCor());
				
				prepareServiceControl(grupoProdutoSoapServiceControl);
				grupoProdutoSoapServiceControl.updateGrupoProduto(grupoProdutoRequestDocument);
			}
			
			String reloadScript = "$('reload_popup_imagens_modelo').onclick();";

			PrintWriter out;
			try {
				getResponse().setContentType("text/javascript");
				out = this.getResponse().getWriter();
				out.println(reloadScript);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			prepareServiceControl(multimidiaSoapServiceControl);
			multimidiaSoapServiceControl.alterarMultimidia(alterarMultimidiaRequestDocument);
		}
		
		return null;
	}

	@Jpf.Action()
	public Forward classificarCorPadrao(ClassificarCorPadraoMultimidiaFormBean form) {
		
		UpdateGrupoProdutoRequestDocument grupoProdutoRequestDocument = UpdateGrupoProdutoRequestDocument.Factory.newInstance();
		ParamUpdateGrupoProduto paramUpdateGrupoProduto = grupoProdutoRequestDocument.addNewUpdateGrupoProdutoRequest().addNewParamUpdateGrupoProduto();
		paramUpdateGrupoProduto.setIdGrupoProduto(form.getIdModeloCor());
		paramUpdateGrupoProduto.setDsNota(form.getDsNotaModeloCor());
		
		if (form.getIdCorPadrao() != null) {
			paramUpdateGrupoProduto.setIdCorPadrao(form.getIdCorPadrao());
		}
		
		prepareServiceControl(grupoProdutoSoapServiceControl);
		grupoProdutoSoapServiceControl.updateGrupoProduto(grupoProdutoRequestDocument);
		
		String reloadScript = "$('reload_popup_imagens_modelo').onclick();";
		
		try {
			writeJSOutput(reloadScript);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private long buscarIdTipoMultimidia(String sgTipoMultimidia) {
		BuscarListaTipoMultimidiaRequestDocument tipoMultimidiaRequestDocument = BuscarListaTipoMultimidiaRequestDocument
																					.Factory.newInstance();
		tipoMultimidiaRequestDocument.addNewBuscarListaTipoMultimidiaRequest();
		prepareServiceControl(multimidiaSoapServiceControl);
		BuscarListaTipoMultimidiaResponseDocument tipoMultimidiaResponseDocument = multimidiaSoapServiceControl
				.buscarListaTipoMultimidia(tipoMultimidiaRequestDocument);
		List<TipoMultimidia> tipoMultimidiaLista = tipoMultimidiaResponseDocument.getBuscarListaTipoMultimidiaResponse()
		.getListaTipoMultimidia().getTipoMultimidiaList();
		
		long idTipoMultimidia = 0;
		
		for (TipoMultimidia tipoMultimidia : tipoMultimidiaLista) {
			if (tipoMultimidia.getSgTipoMultimidia().equals(sgTipoMultimidia)) {
				idTipoMultimidia = tipoMultimidia.getIdTipoMultimidia();
				break;
			}
		}
		
		return idTipoMultimidia;
	}
	
	@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupNovoValorCaracteristica.jsp") })
	public Forward popupNovoValorCaracteristica() {
		
		HttpServletRequest request = getRequest();
		
		logger.debug("Caracteristica: " + request.getParameter("idCaracteristica"));
		logger.debug("Pagina: " + request.getParameter("paginaSelecionada"));
		logger.debug("Pagina: " + request.getParameter("caracteristicas_selecionadas"));
		forwardParameter("idCaracteristica");
		forwardParameter("paginaSelecionada");
		forwardParameter("caracteristicas_selecionadas");
		Forward forward = new Forward("success");
		return forward;
	}
	
	@Jpf.Action()
	public Forward adicionarValorCaracteristica(NovoValorCaracteristicaFormBean form) throws CatalogoPRSException {
		
		logger.debug("Novo Valor da Caracteristica: " + form.getNmValorCaracteristica());
		logger.debug("ID Caracteristica: " + form.getIdCaracteristica());
		
		CriarValorCaracteristicaRequestDocument criarValorCaracteristicaRequestDocument = CriarValorCaracteristicaRequestDocument.Factory.newInstance();
		ParametrosCriarValorCaracteristica parametrosCriarValorCaracteristica = criarValorCaracteristicaRequestDocument.addNewCriarValorCaracteristicaRequest()
																					.addNewParametrosCriarValorCaracteristica();
		ValorCaracteristicaCriacao valorCaracteristicaCriacao = parametrosCriarValorCaracteristica.addNewValorCaracteristicaCriacao();
		valorCaracteristicaCriacao.setIdCaracteristica(form.getIdCaracteristica());
		valorCaracteristicaCriacao.setValor(form.getNmValorCaracteristica().toUpperCase());
		prepareServiceControl(caracteristicaSoapServiceControl);
		CriarValorCaracteristicaResponseDocument criarValorCaracteristicaResponseDocument = caracteristicaSoapServiceControl.criarValorCaracteristica(criarValorCaracteristicaRequestDocument);
		long idNovoValorCarac = criarValorCaracteristicaResponseDocument.getCriarValorCaracteristicaResponse().getResultadoCriarValorCaracteristica().getIdValorCaracteristica();
		
		logger.debug("Valor Caracteristica: " + idNovoValorCarac);
		
		String caracteristicasJSON = form.getCaracteristicasSelect();
		
		JSONObject caracteristicaMap = null;
		
		try {
			caracteristicaMap = new JSONObject(caracteristicasJSON);
			StringBuffer valoresCaracteristica = new StringBuffer((String) caracteristicaMap.get(String.valueOf(form.getIdCaracteristica())));
			logger.debug("Antigos valores: " + valoresCaracteristica.toString());
			
			if (valoresCaracteristica != null && !valoresCaracteristica.toString().trim().equals("")) {
				valoresCaracteristica.append("," + String.valueOf(idNovoValorCarac));
			} else {
				valoresCaracteristica.append(String.valueOf(idNovoValorCarac));
			}
			
			caracteristicaMap.put(String.valueOf(form.getIdCaracteristica()), valoresCaracteristica.toString());
			logger.debug("Novos valores: " + caracteristicaMap.get(String.valueOf(form.getIdCaracteristica())));
		} catch (JSONException exception) {
			exception.printStackTrace();
		}
		
		logger.debug("Novas caracterisitcas selecionadas: " + caracteristicaMap.toString());
		
		String reloadScript = "$('caracteristicas_selecionadas').value='" + caracteristicaMap.toString() + "';";
		reloadScript += "fecharPopup('popup2');$('pagina" + form.getPaginaSelecionada() + "').onclick();$('caracteristicaLabel_" + form.getIdCaracteristica() + "').onclick.delay(1);";
		logger.debug(reloadScript);
		try {
			writeJSOutput(reloadScript);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void associarCaracteristicasModelo(long idModelo, String caracteristicasJSON ) {
		
		AlterarModeloCaracteristicaRequestDocument alterarModeloCaracteristicaRequestDocument = AlterarModeloCaracteristicaRequestDocument.Factory.newInstance();
		ParametrosAssociarModeloCaracteristica parametrosAssociarModeloCaracteristica = alterarModeloCaracteristicaRequestDocument.addNewAlterarModeloCaracteristicaRequest()
			.addNewParametrosAlterarModeloCaracteristica().addNewParametrosAssociarModeloCaracteristica();
		
		parametrosAssociarModeloCaracteristica.setIdModelo(idModelo);
		
		try {
			JSONObject hashmap = new JSONObject(caracteristicasJSON);
			for (Iterator caracs = hashmap.keys(); caracs.hasNext();) {
				String idCaracteristica = (String) caracs.next();
				String[] valoresCaracteristicas = new String[] {};
				if (hashmap.get(idCaracteristica) != null && ((String) hashmap.get(idCaracteristica)).trim().length() > 0) {
					valoresCaracteristicas = splitAndClean((String) hashmap.get(idCaracteristica));
				}
				if (valoresCaracteristicas.length > 0) {
					for (String idValorCaracteristica : valoresCaracteristicas) {
						ValorCaracteristica valorCaracteristica = parametrosAssociarModeloCaracteristica
								.addNewValorCaracteristica();
						valorCaracteristica.setIdCaracteristica(Long.valueOf(idCaracteristica));
						valorCaracteristica.setIdValorCaracteristica(Long.valueOf(idValorCaracteristica));
					}
				} else {
					ValorCaracteristica valorCaracteristica = parametrosAssociarModeloCaracteristica
							.addNewValorCaracteristica();
					valorCaracteristica.setIdCaracteristica(Long.valueOf(idCaracteristica));
				}
		
			}
		} catch (JSONException e) {
			logger.error("Erro ao copiar Características do Modelo...");
		}
		
		prepareServiceControl(grupoProdutoSoapServiceControl);
		grupoProdutoSoapServiceControl.alterarModeloCaracteristica(alterarModeloCaracteristicaRequestDocument);
	}

	@Override
	protected void onCreate() {
		super.onCreate();

		funcionalidade = "26"; // Produtos/Parametrização de Modelos
		
		permissoesDoController.put("abrirNovoModelo", "criarModelo");
		permissoesDoController.put("alterarDispModelo", "disponibilizarModelo");
		permissoesDoController.put("alterarFimVidaModelo", "fimDeVidaModelo");
		permissoesDoController.put("alterarModelo", "alterarModelo");
		permissoesDoController.put("apagarImagem", "removerImagemModelo");
		permissoesDoController.put("apagarModelo", "excluirModelo");
		permissoesDoController.put("begin", "consultarModelo");
		permissoesDoController.put("carregarAlterarModelo", "alterarModelo");
		permissoesDoController.put("carregarCopiarModelo", "copiarModelo");
		permissoesDoController.put("criarModelo", "criarModelo");
		permissoesDoController.put("listarCaracteristicas", "consultarModelo");
		permissoesDoController.put("listarFabricantes", "consultarModelo");
		permissoesDoController.put("listarTecnologias", "consultarModelo");
		permissoesDoController.put("listarTecnologiasFrequencias", "consultarModelo");
		permissoesDoController.put("pesquisarModelo", "consultarModelo");
		permissoesDoController.put("popupAlterarCaracteristica", "consultarModelo");
		permissoesDoController.put("popupApagarImagem", "removerImagemModelo");
		permissoesDoController.put("popupApagarModelo", "excluirModelo");
		permissoesDoController.put("popupImagemModelo", "consultarModelo");
		permissoesDoController.put("uploadImagem", "associarImagemModelo");
		permissoesDoController.put("alterarCaracteristicasModelo", "alterarModelo");
		
		autorizaVazio.add("popupImagemModelo");
		autorizaVazio.add("popupApagarModelo");
		autorizaVazio.add("carregarAlterarModelo");
		autorizaVazio.add("carregarCopiarModelo");
		autorizaVazio.add("abrirNovoModelo");
		autorizaVazio.add("popupAlterarModelo");
		autorizaVazio.add("popupAlterarCaracteristica");
		

		tecnologiaSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.TECNOLOGIA));
		tipoProdutoSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.TIPO_PRODUTO));
		caracteristicaSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.CARACTERISTICAS));
		grupoProdutoSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.GRUPO_PRODUTO));
		multimidiaSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.MULTIMIDIA));
		fabricanteSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.FABRICANTE));
		produtosSoapServiceControl.setEndpointAddress(WebServicesConfiguration.getEndpointAddress(WebServicesConfiguration.PRODUTO));
	}

	@Override
	protected void onDestroy(HttpSession session) {
	}

	@Jpf.FormBean
	public static class PesquisaModeloFormBean implements java.io.Serializable {
		private static final long serialVersionUID = 1L;

		private Long tipoProduto;

		private Long fabricante;

		private String produto;

		private String tecnologias;

		private String caracteristicas;

		private String valoresCaracteristicas;
		
		private String nomesTecnologias;
		
		private String nomesCaracteristicasValores;

		private String modelo;
		
		private Long paginaSolicitada;

		public Long getPaginaSolicitada() {
			return paginaSolicitada;
		}

		public void setPaginaSolicitada(Long paginaSolicitada) {
			this.paginaSolicitada = paginaSolicitada;
		}

		public String getModelo() {
			return modelo;
		}

		public void setModelo(String modelo) {
			this.modelo = modelo;
		}

		public String getCaracteristicas() {
			return caracteristicas;
		}

		public void setCaracteristicas(String caracteristicas) {
			this.caracteristicas = caracteristicas;
		}

		public String getValoresCaracteristicas() {
			return valoresCaracteristicas;
		}

		public void setValoresCaracteristicas(String valoresCaracteristicas) {
			this.valoresCaracteristicas = valoresCaracteristicas;
		}

		public Long getFabricante() {
			return fabricante;
		}

		public void setFabricante(Long fabricante) {
			this.fabricante = fabricante;
		}

		public String getProduto() {
			return produto;
		}

		public void setProduto(String produto) {
			this.produto = produto;
		}

		@Jpf.ValidatableProperty(validateRequired = @Jpf.ValidateRequired(), validateType = @Jpf.ValidateType(type = long.class), validateRange = @Jpf.ValidateRange(maxInt = 9999, minInt = 1))
		public Long getTipoProduto() {
			return tipoProduto;
		}

		public void setTipoProduto(Long tipoProduto) {
			this.tipoProduto = tipoProduto;
		}

		public String getTecnologias() {
			return tecnologias;
		}

		public void setTecnologias(String tecnologias) {
			this.tecnologias = tecnologias;
		}

		public String getNomesTecnologias() {
			return nomesTecnologias;
		}

		public void setNomesTecnologias(String nomesTecnologias) {
			this.nomesTecnologias = nomesTecnologias;
		}

		public String getNomesCaracteristicasValores() {
			return nomesCaracteristicasValores;
		}

		public void setNomesCaracteristicasValores(String nomesCaracteristicasValores) {
			this.nomesCaracteristicasValores = nomesCaracteristicasValores;
		}

		

	}

	@Jpf.FormBean
	public static class UploadImagemFormBean implements java.io.Serializable {
		private static final long serialVersionUID = 1L;

		Long idModelo;

		FormFile imagem;

		public FormFile getImagem() {
			return imagem;
		}

		public void setImagem(FormFile imagem) {
			this.imagem = imagem;
		}

		public Long getIdModelo() {
			return idModelo;
		}

		public void setIdModelo(Long idModelo) {
			this.idModelo = idModelo;
		}

	}

	@Jpf.FormBean
	public static class ApagarImagemFormBean extends ApagarFormBean {
		private static final long serialVersionUID = 1L;

		private Long idModelo;
		
		private Long idCorPadrao;

		public Long getIdCorPadrao() {
			return idCorPadrao;
		}

		public void setIdCorPadrao(Long idCorPadrao) {
			this.idCorPadrao = idCorPadrao;
		}

		public Long getIdModelo() {
			return idModelo;
		}

		public void setIdModelo(Long idModelo) {
			this.idModelo = idModelo;
		}

	}

	@Jpf.FormBean
	public static class NovoModeloFormBean implements java.io.Serializable {
		private static final long serialVersionUID = 1L;

		private Long idModelo;

		private Long idTipoProduto;

		private Long idFabricante;

		private String tecnologiasFrequencias;

		private String nomeComercial;
		
		private String justificativa;
		
		private String disponivel;
		
		private String fimVida;
		
		private String url;
		
		private String caracteristicas;
		
		private String dsNota;
		
		private String nomesCaracteristicasValores;
		
		private String tipoSubmit;

		public String getTipoSubmit() {
			return tipoSubmit;
		}

		public void setTipoSubmit(String tipoSubmit) {
			this.tipoSubmit = tipoSubmit;
		}

		public String getNomesCaracteristicasValores() {
			return nomesCaracteristicasValores;
		}

		public void setNomesCaracteristicasValores(String nomesCaracteristicasValores) {
			this.nomesCaracteristicasValores = nomesCaracteristicasValores;
		}

		public String getDsNota() {
			return dsNota;
		}

		public void setDsNota(String dsNota) {
			this.dsNota = dsNota;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getFimVida() {
			return fimVida;
		}

		public void setFimVida(String fimVida) {
			this.fimVida = fimVida;
		}

		public String getDisponivel() {
			return disponivel;
		}

		public void setDisponivel(String disponivel) {
			this.disponivel = disponivel;
		}

		public String getNomeComercial() {
			return nomeComercial;
		}

		public void setNomeComercial(String nomeComercial) {
			this.nomeComercial = nomeComercial==null?nomeComercial:nomeComercial.toUpperCase();
		}

		public String getTecnologiasFrequencias() {
			return tecnologiasFrequencias;
		}

		public void setTecnologiasFrequencias(String tecnologiasFrequencias) {
			this.tecnologiasFrequencias = tecnologiasFrequencias;
		}

		public Long getIdFabricante() {
			return idFabricante;
		}

		public void setIdFabricante(Long idFabricante) {
			this.idFabricante = idFabricante;
		}

		public Long getIdTipoProduto() {
			return idTipoProduto;
		}

		public void setIdTipoProduto(Long idTipoProduto) {
			this.idTipoProduto = idTipoProduto;
		}

		public Long getIdModelo() {
			return idModelo;
		}

		public void setIdModelo(Long idModelo) {
			this.idModelo = idModelo;
		}

		public String getJustificativa() {
			return justificativa;
		}

		public void setJustificativa(String justificativa) {
			this.justificativa = justificativa;
		}

		public String getCaracteristicas() {
			return caracteristicas;
		}

		public void setCaracteristicas(String caracteristicas) {
			this.caracteristicas = caracteristicas;
		}

	}

	@Jpf.FormBean
	public static class ClassificarMultimidiaFormBean implements java.io.Serializable {
		
		private static final long serialVersionUID = 1L;
		
		private Long idCor;
		
		private Long idClassificacao;
		
		private Long idMultimidia;
		
		private Long idModelo;
		
		private String dsNotaModelo;
		
		private Long idCorPadrao;

		public Long getIdCorPadrao() {
			return idCorPadrao;
		}

		public void setIdCorPadrao(Long idCorPadrao) {
			this.idCorPadrao = idCorPadrao;
		}

		public String getDsNotaModelo() {
			return dsNotaModelo;
		}

		public void setDsNotaModelo(String dsNotaModelo) {
			this.dsNotaModelo = dsNotaModelo;
		}

		public Long getIdModelo() {
			return idModelo;
		}

		public void setIdModelo(Long idModelo) {
			this.idModelo = idModelo;
		}

		public Long getIdMultimidia() {
			return idMultimidia;
		}

		public void setIdMultimidia(Long idMultimidia) {
			this.idMultimidia = idMultimidia;
		}

		public Long getIdClassificacao() {
			return idClassificacao;
		}

		public void setIdClassificacao(Long idClassificacao) {
			this.idClassificacao = idClassificacao;
		}

		public Long getIdCor() {
			return idCor;
		}

		public void setIdCor(Long idCor) {
			this.idCor = idCor;
		}
	
	}

	@Jpf.FormBean
	public static class ClassificarCorPadraoMultimidiaFormBean implements java.io.Serializable {
		
		private static final long serialVersionUID = 1L;
		
		private Long idModeloCor;
		
		private Long idCorPadrao;
		
		private String dsNotaModeloCor;

		public String getDsNotaModeloCor() {
			return dsNotaModeloCor;
		}

		public void setDsNotaModeloCor(String dsNotaModeloCor) {
			this.dsNotaModeloCor = dsNotaModeloCor;
		}

		public Long getIdCorPadrao() {
			return idCorPadrao;
		}

		public void setIdCorPadrao(Long idCorPadrao) {
			this.idCorPadrao = idCorPadrao;
		}

		public Long getIdModeloCor() {
			return idModeloCor;
		}

		public void setIdModeloCor(Long idModeloCor) {
			this.idModeloCor = idModeloCor;
		}
	
	}

	@Jpf.FormBean
	public static class NovoValorCaracteristicaFormBean implements java.io.Serializable {
		
		private static final long serialVersionUID = 1L;
		
		private Long idCaracteristica;
		
		private String nmValorCaracteristica;
		
		private Long paginaSelecionada;
		
		private String caracteristicasSelect;

		public String getCaracteristicasSelect() {
			return caracteristicasSelect;
		}

		public void setCaracteristicasSelect(String caracteristicasSelect) {
			this.caracteristicasSelect = caracteristicasSelect;
		}

		public Long getPaginaSelecionada() {
			return paginaSelecionada;
		}

		public void setPaginaSelecionada(Long paginaSelecionada) {
			this.paginaSelecionada = paginaSelecionada;
		}

		public Long getIdCaracteristica() {
			return idCaracteristica;
		}

		public void setIdCaracteristica(Long idCaracteristica) {
			this.idCaracteristica = idCaracteristica;
		}

		public String getNmValorCaracteristica() {
			return nmValorCaracteristica;
		}

		public void setNmValorCaracteristica(String nmValorCaracteristica) {
			this.nmValorCaracteristica = nmValorCaracteristica;
		}
	
	}

	public String[] getCaracteristicasSelecionadas() {
		return caracteristicasSelecionadas;
	}

	public void setCaracteristicasSelecionadas(String[] caracteristicasSelecionadas) {
		this.caracteristicasSelecionadas = caracteristicasSelecionadas;
	}

	public String[] getTecnologiasSelecionadas() {
		return tecnologiasSelecionadas;
	}

	public void setTecnologiasSelecionadas(String[] tecnologiasSelecionadas) {
		this.tecnologiasSelecionadas = tecnologiasSelecionadas;
	}

	public String[] getValoresCaracteristicasSelecionadas() {
		return valoresCaracteristicasSelecionadas;
	}

	public void setValoresCaracteristicasSelecionadas(String[] valoresCaracteristicasSelecionadas) {
		this.valoresCaracteristicasSelecionadas = valoresCaracteristicasSelecionadas;
	}

	public String[] getCaracteristicasSelecionadasAlterar() {
		return caracteristicasSelecionadasAlterar;
	}

	public void setCaracteristicasSelecionadasAlterar(String[] caracteristicasSelecionadasAlterar) {
		this.caracteristicasSelecionadasAlterar = caracteristicasSelecionadasAlterar;
	}

	public String[] getValoresCaracteristicasSelecionadasAlterar() {
		return valoresCaracteristicasSelecionadasAlterar;
	}

	public void setValoresCaracteristicasSelecionadasAlterar(String[] valoresCaracteristicasSelecionadasAlterar) {
		this.valoresCaracteristicasSelecionadasAlterar = valoresCaracteristicasSelecionadasAlterar;
	}

	public String[] getTecnologiasFrequencias_tecnologiasSelecionadas() {
		return tecnologiasFrequencias_tecnologiasSelecionadas;
	}

	public void setTecnologiasFrequencias_tecnologiasSelecionadas(String[] tecnologiasFrequencias_tecnologiasSelecionadas) {
		this.tecnologiasFrequencias_tecnologiasSelecionadas = tecnologiasFrequencias_tecnologiasSelecionadas;
	}

	public Map<String, Map<String, List<String>>> getTecnologiasFrequencias_frequenciaSelecionadas() {
		return tecnologiasFrequencias_frequenciaSelecionadas;
	}

	public void setTecnologiasFrequencias_frequenciaSelecionadas(Map<String, Map<String, List<String>>> tecnologiasFrequencias_frequenciaSelecionadas) {
		this.tecnologiasFrequencias_frequenciaSelecionadas = tecnologiasFrequencias_frequenciaSelecionadas;
	}

	public Map<String, List<String>> getTecnologiasFrequencias_tiposFrequenciaSelecionadas() {
		return tecnologiasFrequencias_tiposFrequenciaSelecionadas;
	}

	public void setTecnologiasFrequencias_tiposFrequenciaSelecionadas(Map<String, List<String>> tecnologiasFrequencias_tiposFrequenciaSelecionadas) {
		this.tecnologiasFrequencias_tiposFrequenciaSelecionadas = tecnologiasFrequencias_tiposFrequenciaSelecionadas;
	}*/

}