package br.com.vivo.catalogoPRS.pageflows.param.modelo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.vivo.catalogoPRS.bo.UserCatalogo;
import br.com.vivo.catalogoPRS.delegate.CaracteristicaDelegate;
import br.com.vivo.catalogoPRS.delegate.ClassificacaoMultimidiaDelegate;
import br.com.vivo.catalogoPRS.delegate.CorDelegate;
import br.com.vivo.catalogoPRS.delegate.FabricanteDelegate;
import br.com.vivo.catalogoPRS.delegate.GrupoProdutoDelegate;
import br.com.vivo.catalogoPRS.delegate.ModeloDelegate;
import br.com.vivo.catalogoPRS.delegate.TecnologiaDelegate;
import br.com.vivo.catalogoPRS.delegate.TipoProdutoDelegate;
import br.com.vivo.catalogoPRS.delegate.ValorCaracteristicaDelegate;
import br.com.vivo.catalogoPRS.pageflows.param.matrizOferta.cadastro.CadastroCabecalhoMatrizOfertaAction;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.ParametrizacaoProdutoModeloForm;
import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;
import br.com.vivo.catalogoPRS.util.ImagemUtil;

import com.indracompany.catalogo.dao.GrupoProdutoTOBuilder;
import com.indracompany.catalogo.dao.MultimidiaTOBuilder;
import com.indracompany.catalogo.dao.TipoProdutoTOBuilder;
import com.indracompany.catalogo.to.CaracteristicaTO;
import com.indracompany.catalogo.to.ClassificacaoMultimidiaTO;
import com.indracompany.catalogo.to.CorTO;
import com.indracompany.catalogo.to.FabricanteTO;
import com.indracompany.catalogo.to.GrupoProdutoTO;
import com.indracompany.catalogo.to.IdNomeTO;
import com.indracompany.catalogo.to.MultimidiaTO;
import com.indracompany.catalogo.to.PaginacaoDataTableTO;
import com.indracompany.catalogo.to.PesquisaGrupoProdutoTO;
import com.indracompany.catalogo.to.PesquisaIdNomeTO;
import com.indracompany.catalogo.to.ProdutoTO;
import com.indracompany.catalogo.to.TecnologiaTO;
import com.indracompany.catalogo.to.TipoFrequenciaTO;
import com.indracompany.catalogo.to.TipoProdutoTO;
import com.indracompany.catalogo.to.ValorCaracteristicaTO;
import com.indracompany.catalogo.to.VlFrequenciaTO;
import com.indracompany.catalogo.util.JSONUtil;

public class ModeloAction extends BaseMappingDispatchAction {

	private static final java.util.logging.Logger logging = java.util.logging.Logger.getLogger(CadastroCabecalhoMatrizOfertaAction.class.getName());
	
	
	private static final String SUCCESS = "success";

	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		List<TipoProdutoTO> tipoProdutos = (new TipoProdutoDelegate()).findAll(new TipoProdutoTOBuilder(false));
		request.setAttribute("tipoProdutos", tipoProdutos);
		List<TecnologiaTO> tecnologias = (new TecnologiaDelegate()).findAll();
		request.setAttribute("tecnologias", tecnologias);
		List<CorTO> cores = (new CorDelegate()).findAll();
		request.setAttribute("cores", cores);		

		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward getFabricante(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			FabricanteTO fabricanteTO = new FabricanteTO();
			fabricanteTO.setIdTipoProduto(Integer.parseInt(request.getParameter("idTipoProduto")));
			List<FabricanteTO> fabricantes = (new FabricanteDelegate()).search(fabricanteTO);
			JSONArray fabricantesJSON = new JSONArray();
			for (FabricanteTO fabricante : fabricantes) {
				JSONObject fabricanteJSON = new JSONObject();
				fabricanteJSON.put("idFabricante", fabricante.getIdFabricante());
				fabricanteJSON.put("nome", fabricante.getNmFabricante());
				fabricantesJSON.put(fabricanteJSON);
			}
			returnJson(fabricantesJSON.toString(), response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ActionForward getModelo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			GrupoProdutoTO gpTO = new GrupoProdutoTO();
			gpTO.setTipoProdutoTO(new TipoProdutoTO(Integer.parseInt(request.getParameter("idTipoProduto"))));
			gpTO.setFabricanteTO(new FabricanteTO(Integer.parseInt(request.getParameter("idFabricante"))));
			gpTO.setInDisponivel("S");
			if (request.getParameter("idTecnologia") != null) {
				gpTO.setTecnologiaTOList(new LinkedList<TecnologiaTO>());
				gpTO.getTecnologiaTOList().add(new TecnologiaTO(Integer.parseInt(request.getParameter("idTecnologia"))));
			}
			List<GrupoProdutoTO> modelos = (new ModeloDelegate()).searchModelo(gpTO);
			JSONArray modelosJSON = new JSONArray();
			for(GrupoProdutoTO modelo:modelos){
				JSONObject modeloJSON = new JSONObject();
				modeloJSON.put("id", modelo.getIdGrupoProduto());
				modeloJSON.put("nome", modelo.getNmGrupoProduto());
				modelosJSON.put(modeloJSON);
			}
			returnJson(modelosJSON.toString(), response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ActionForward obterCaracteristicas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			PesquisaIdNomeTO pesquisaTO = new PesquisaIdNomeTO();
			
			PaginacaoDataTableTO paginacaoDataTableTO = new PaginacaoDataTableTO();
			paginacaoDataTableTO.setRegistroAtual(Integer.parseInt(request.getParameter("iDisplayStart")));
			paginacaoDataTableTO.setRegistrosPorPagina(Integer.parseInt(request.getParameter("iDisplayLength")));
			pesquisaTO.setPaginacaoDataTableTO(paginacaoDataTableTO);
			
			JSONArray results = new JSONArray();
			new CaracteristicaDelegate().searchCaracteristica(pesquisaTO);
			for (IdNomeTO idNomeTO : pesquisaTO.getResultado()) {
				JSONObject json = new JSONObject();
				json.put("DT_RowId", idNomeTO.getId());
				json.put("0", idNomeTO.getId());
				json.put("1", idNomeTO.getNome());
				json.put("2", idNomeTO.childrenToJSONString());
				results.put(json);
			}
			
			JSONObject jsonResult = new JSONObject();
			if (paginacaoDataTableTO.getRegistroAtual() != null) {
				jsonResult.put("iTotalRecords", paginacaoDataTableTO.getTotalRegistros());
				jsonResult.put("iDisplayStart", paginacaoDataTableTO.getRegistroAtual());
				jsonResult.put("iDisplayLength", paginacaoDataTableTO.getRegistrosPorPagina());
				jsonResult.put("iTotalDisplayRecords", paginacaoDataTableTO.getTotalRegistros());
			}
			jsonResult.accumulate("aaData", results);
			returnJson(jsonResult.toString(), response);
		} catch (Exception e){
			e.printStackTrace();
			throw e;
		}
		return null;
	}
	
	public ActionForward pesquisarModelos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			PesquisaGrupoProdutoTO pesquisaTO = new PesquisaGrupoProdutoTO();
			
			PaginacaoDataTableTO paginacaoDataTableTO = new PaginacaoDataTableTO();
			paginacaoDataTableTO.setRegistroAtual(Integer.parseInt(request.getParameter("iDisplayStart")));
			paginacaoDataTableTO.setRegistrosPorPagina(Integer.parseInt(request.getParameter("iDisplayLength")));
			paginacaoDataTableTO.setNomeCampoOrdenacao("gp.nmGrupoProduto");
			paginacaoDataTableTO.setOrdemCrescente(true);
			pesquisaTO.setPaginacaoDataTableTO(paginacaoDataTableTO);
			
			JSONObject jsonParams = new JSONObject(request.getParameter("form"));
			PesquisaGrupoProdutoTO.Parametros searchParams = pesquisaTO.getParametros();
			searchParams.setIdTipoProduto(new Integer(jsonParams.getInt("idTipoProduto")));
			int idFabricante = jsonParams.getInt("idFabricante");
			if (idFabricante > 0) {
				searchParams.setIdFabricante(new Integer(idFabricante));
			}
			int idModelo = jsonParams.getInt("idModelo");
			if (idModelo > 0) {
				searchParams.setIdModelo(new Integer(idModelo));
			}
			int idTecnologia = jsonParams.getInt("idTecnologia");
			if (idTecnologia > 0) {
				searchParams.setIdTecnologia(new Integer(idTecnologia));
			}
			
			JSONArray jsonCaracteristicasArray = new JSONArray(jsonParams.getString("idsCaracteristicas"));
			int qtdeIdsCaracteristicas = jsonCaracteristicasArray.length();
			if (qtdeIdsCaracteristicas > 0) {
				List<Integer> idsCaracteristicas = null;
				List<Integer> idsValoresCaracteristicas = null;
				for (int i = 0; i < qtdeIdsCaracteristicas; i ++) {
					JSONObject jsonCaracteristica = jsonCaracteristicasArray.getJSONObject(i);
					JSONArray jsonValoresCaracteristicasArray = jsonCaracteristica.getJSONArray("valores");
					int qtdeIdsValoresCartacteristicas = jsonValoresCaracteristicasArray.length();
					if (qtdeIdsValoresCartacteristicas > 0) {
						if (idsValoresCaracteristicas == null) {
							idsValoresCaracteristicas = new ArrayList<Integer>();
						}
						for (int j = 0; j < qtdeIdsValoresCartacteristicas; j ++) {
							int idValorCaracteristica = jsonValoresCaracteristicasArray.getInt(j);
							idsValoresCaracteristicas.add(new Integer(idValorCaracteristica));
						}
					} else {
						if (idsCaracteristicas == null) {
							idsCaracteristicas = new ArrayList<Integer>();
						}
						int idCaracteristica = jsonCaracteristica.getInt("id");
						idsCaracteristicas.add(new Integer(idCaracteristica));
					}
				}
				if (idsCaracteristicas != null) {
					searchParams.setIdsCaracteristicas(idsCaracteristicas);
				}
				if (idsValoresCaracteristicas != null) {
					searchParams.setIdsValoresCaracteristicas(idsValoresCaracteristicas);
				}
			}
			
			JSONArray results = new JSONArray();
			new ModeloDelegate().searchModelo(pesquisaTO);
			for (PesquisaGrupoProdutoTO.LinhaResultado linha : pesquisaTO.getResultado()) {
				JSONObject json = new JSONObject();
				json.put("DT_RowId", linha.getIdGrupoProduto());
				json.put("0", linha.getNmGrupoProduto());
				json.put("1", linha.getNmFabricante());
				json.put("2", linha.getInFimVida().equals("S") ? true : false);
				json.put("3", linha.getInDisponivel().equals("S") ? true : false);
				json.put("4", linha.getInCaracteristica().equals("S") ? true : false);
				json.put("5", linha.getInMultimidia().equals("S") ? true : false);
				json.put("6", linha.getIdGrupoProduto());
				json.put("7", linha.getIdGrupoProduto());
				json.put("8", linha.getIdGrupoProduto());
				results.put(json);
			}
			
			JSONObject jsonResult = new JSONObject();
			if (paginacaoDataTableTO.getRegistroAtual() != null) {
				jsonResult.put("iTotalRecords", paginacaoDataTableTO.getTotalRegistros());
				jsonResult.put("iDisplayStart", paginacaoDataTableTO.getRegistroAtual());
				jsonResult.put("iDisplayLength", paginacaoDataTableTO.getRegistrosPorPagina());
				jsonResult.put("iTotalDisplayRecords", paginacaoDataTableTO.getTotalRegistros());
			}
			jsonResult.accumulate("aaData", results);
			logging.log(Level.INFO, "## results.length: {0}", new String[]{Integer.toString(results.length())});
			logging.log(Level.INFO, "## jsonResult.toString() {0}", new String[] {jsonResult.toString()});
			returnJson(jsonResult.toString(), response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		return null;
	}
	
	public ActionForward obterDetalhesModelo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws JSONException {
		JSONObject jsonResult = new JSONObject();
		try {
			String idModeloParam = request.getParameter("idModelo");
			
			JSONObject jsonGrupoProduto = jsonObjectFromGrupoProdutoTO(new Integer(idModeloParam));
			jsonResult.put("grupoProduto", jsonGrupoProduto);
			
			String caminho = ApplicationConfiguration.getCaminhoLinkImagensModelo();
			jsonResult.put("caminho_link_imagens_modelo", caminho);
			
			List<ClassificacaoMultimidiaTO> classificacoes = (new ClassificacaoMultimidiaDelegate()).findAll();
			jsonResult.put("classificacoes", JSONUtil.toJSONArray(classificacoes));
			
			List<CorTO> cores = (new CorDelegate()).findAll();
			jsonResult.put("cores", JSONUtil.toJSONArray(cores));
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.put("erro", obterErro(e, "Erro ao obter detalhes do modelo."));
			
		}
		returnJson(jsonResult.toString(), response);
		return null;
	}
	
	public ActionForward obterTecnologias(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			PesquisaIdNomeTO pesquisaTO = new PesquisaIdNomeTO();
			
			PaginacaoDataTableTO paginacaoDataTableTO = new PaginacaoDataTableTO();
			paginacaoDataTableTO.setRegistroAtual(Integer.parseInt(request.getParameter("iDisplayStart")));
			paginacaoDataTableTO.setRegistrosPorPagina(Integer.parseInt(request.getParameter("iDisplayLength")));
			pesquisaTO.setPaginacaoDataTableTO(paginacaoDataTableTO);
			
			JSONArray results = new JSONArray();
			new TecnologiaDelegate().searchTecnologia(pesquisaTO);
			for (IdNomeTO idNomeTO : pesquisaTO.getResultado()) {
				JSONObject json = new JSONObject();
				json.put("DT_RowId", idNomeTO.getId());
				json.put("0", idNomeTO.getId());
				json.put("1", idNomeTO.getNome());
				json.put("2", idNomeTO.childrenToJSONString());
				results.put(json);
			}
			
			JSONObject jsonResult = new JSONObject();
			if (paginacaoDataTableTO.getRegistroAtual() != null) {
				jsonResult.put("iTotalRecords", paginacaoDataTableTO.getTotalRegistros());
				jsonResult.put("iDisplayStart", paginacaoDataTableTO.getRegistroAtual());
				jsonResult.put("iDisplayLength", paginacaoDataTableTO.getRegistrosPorPagina());
				jsonResult.put("iTotalDisplayRecords", paginacaoDataTableTO.getTotalRegistros());
			}
			jsonResult.accumulate("aaData", results);
			returnJson(jsonResult.toString(), response);
		} catch (Exception e){
			e.printStackTrace();
			throw e;
		}
		return null;
	}

	public ActionForward salvarModelo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws JSONException {
		JSONObject jsonResult = new JSONObject();
		UserCatalogo usuarioLogado = (UserCatalogo) request.getSession().getAttribute("logged_user");
		
		try {
			JSONObject jsonModelo = new JSONObject(request.getParameter("jsonModelo"));
			GrupoProdutoTO grupoProdutoTO = grupoProdutoTOFromJSONObject(jsonModelo);
			
			grupoProdutoTO.setNmUsuarioCriacao(usuarioLogado.getUser().getUsername());
			grupoProdutoTO.setDtCriacao(new Date());
			new ModeloDelegate().save(grupoProdutoTO);
			
			JSONObject jsonGrupoProduto = jsonObjectFromGrupoProdutoTO(grupoProdutoTO.getIdGrupoProduto());
			jsonResult.put("grupoProduto", jsonGrupoProduto);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.put("erro", obterErro(e, "Erro ao salvar o modelo."));
			
		}
		returnJson(jsonResult.toString(), response);
		return null;
	}
	
	public ActionForward atualizarModelo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws JSONException {
		JSONObject jsonResult = new JSONObject();
		UserCatalogo usuarioLogado = (UserCatalogo) request.getSession().getAttribute("logged_user");
		try {
			JSONObject jsonModelo = new JSONObject(request.getParameter("jsonModelo"));
			GrupoProdutoTO grupoProdutoTO = grupoProdutoTOFromJSONObject(jsonModelo);
			
			grupoProdutoTO.setNmUsuarioAlteracao(usuarioLogado.getUser().getUsername());
			grupoProdutoTO.setDtUltimaAlteracao(new Date());
			new ModeloDelegate().update(grupoProdutoTO);
			
			JSONObject jsonGrupoProduto = jsonObjectFromGrupoProdutoTO(grupoProdutoTO.getIdGrupoProduto());
			jsonResult.put("grupoProduto", jsonGrupoProduto);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.put("erro", obterErro(e, "Erro ao atualizar o modelo."));
			
		}
		returnJson(jsonResult.toString(), response);
		return null;
	}
	
	public ActionForward obterProdutosModelo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws JSONException {
		JSONObject jsonResult = new JSONObject();
		try {
			Integer idGrupoProduto = Integer.parseInt(request.getParameter("idGrupoProduto"));
			
			List<ProdutoTO> produtoTOList = (new GrupoProdutoDelegate()).obterProdutoTOList(idGrupoProduto);
			if (produtoTOList != null && !produtoTOList.isEmpty()) {
				JSONArray jsonProdutoList = JSONUtil.toJSONArray(produtoTOList);
				jsonResult.put("produtoList", jsonProdutoList);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.put("erro", obterErro(e, "Erro ao obter os produtos associados ao modelo."));
			
		}
		returnJson(jsonResult.toString(), response);
		return null;
	}
	
	public ActionForward excluirModelo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws JSONException {
		JSONObject jsonResult = new JSONObject();
		try {
			Integer idGrupoProduto = Integer.parseInt(request.getParameter("idGrupoProduto"));
			
			GrupoProdutoTO grupoProdutoTO = new GrupoProdutoTO();
			grupoProdutoTO.setIdGrupoProduto(idGrupoProduto);
			
			new ModeloDelegate().remove(grupoProdutoTO);
		
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.put("erro", obterErro(e, "Erro ao excluir o modelo."));
			
		}
		returnJson(jsonResult.toString(), response);
		return null;
	}
	
	public ActionForward salvarValor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws JSONException {
		JSONObject jsonResult = new JSONObject();
		UserCatalogo usuarioLogado = (UserCatalogo) request.getSession().getAttribute("logged_user");
		try {
			JSONObject jsonValor = new JSONObject(request.getParameter("jsonValor"));
			
			ValorCaracteristicaTO valorCaracteristicaTO = new ValorCaracteristicaTO();
			valorCaracteristicaTO.setValor(jsonValor.getString("nome"));
			valorCaracteristicaTO.setNmUsuarioCriacao(usuarioLogado.getUser().getUsername());
			valorCaracteristicaTO.setDtCriacao(new Date());
			valorCaracteristicaTO.setCaracteristicaTO(new CaracteristicaTO(jsonValor.getInt("idCaracteristica")));
			
			new ValorCaracteristicaDelegate().saveValorCaracteristica(valorCaracteristicaTO);
			jsonValor.accumulate("id", valorCaracteristicaTO.getIdValorCaracteristica());
			jsonResult.put("valor", jsonValor);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.put("erro", obterErro(e, "Erro ao salvar o valor da característica."));
			
		}
		returnJson(jsonResult.toString(), response);
		return null;
	}
	
	private JSONObject jsonObjectFromGrupoProdutoTO(Integer idGrupoProduto) throws JSONException, BusinessException {
		GrupoProdutoTOBuilder grupoProdutoTOBuilder = new GrupoProdutoTOBuilder(false);
		grupoProdutoTOBuilder.setCriarTipoProduto(new TipoProdutoTOBuilder(false));
		grupoProdutoTOBuilder.setCriarFabricante(true);
		grupoProdutoTOBuilder.setCriarCor(true);
		grupoProdutoTOBuilder.setCriarTecnologia(true);
		GrupoProdutoDelegate grupoProdutoDelegate = new GrupoProdutoDelegate();
		GrupoProdutoTO grupoProdutoTO = grupoProdutoDelegate.findById(idGrupoProduto, grupoProdutoTOBuilder);
		
		JSONObject jsonGrupoProduto = grupoProdutoTO.toJSONObject();
		
		List<TecnologiaTO> tecnologiaTOList = grupoProdutoTO.getTecnologiaTOList();
		if (tecnologiaTOList != null && !tecnologiaTOList.isEmpty()) {
			JSONArray jsonTecnologiaList = new JSONArray();
			for (TecnologiaTO tecnologiaTO : tecnologiaTOList) {
				JSONObject jsonTecnologia = tecnologiaTO.toJSONObject();
				
				List<TipoFrequenciaTO> tipoFrequenciaTOList = grupoProdutoDelegate.obterTipoFrequenciaTOList(idGrupoProduto, tecnologiaTO.getIdTecnologia());
				if (tipoFrequenciaTOList != null && !tipoFrequenciaTOList.isEmpty()) {
					JSONArray jsonTipoFrequenciaList = JSONUtil.toJSONArray(tipoFrequenciaTOList);
					jsonTecnologia.put("tipoFrequenciaList", jsonTipoFrequenciaList);
				}
				
				jsonTecnologiaList.put(jsonTecnologia);
			}
			jsonGrupoProduto.put("tecnologiaList", jsonTecnologiaList);
		}
		
		List<CaracteristicaTO> caracteristicaTOList = grupoProdutoDelegate.obterCaracteristicaTOList(idGrupoProduto);
		if (caracteristicaTOList != null && !caracteristicaTOList.isEmpty()) {
			JSONArray jsonCaracteristicaList = JSONUtil.toJSONArray(caracteristicaTOList);
			jsonGrupoProduto.put("caracteristicaList", jsonCaracteristicaList);
		}
		
		List<ProdutoTO> produtoTOList = grupoProdutoDelegate.obterProdutoTOList(idGrupoProduto);
		if (produtoTOList != null && !produtoTOList.isEmpty()) {
			JSONArray jsonProdutoList = JSONUtil.toJSONArray(produtoTOList);
			jsonGrupoProduto.put("produtoList", jsonProdutoList);
		}
		
		MultimidiaTOBuilder multimidiaTOBuilder = new MultimidiaTOBuilder(false);
		multimidiaTOBuilder.setCriarTipo(true);
		multimidiaTOBuilder.setCriarClassificacao(true);
		multimidiaTOBuilder.setCriarCor(true);
		List<MultimidiaTO> multimidiaTOList = grupoProdutoDelegate.obterMultimidiaTOList(idGrupoProduto, multimidiaTOBuilder);
		if (multimidiaTOList != null && !multimidiaTOList.isEmpty()) {
			JSONArray jsonMultimidiaList = JSONUtil.toJSONArray(multimidiaTOList);
			jsonGrupoProduto.put("multimidiaList", jsonMultimidiaList);
		}
		
		return jsonGrupoProduto;
	}
	
	private GrupoProdutoTO grupoProdutoTOFromJSONObject(JSONObject jsonModelo) throws JSONException {
		GrupoProdutoTO grupoProdutoTO = new GrupoProdutoTO();
		
		Integer idGrupoProduto = Integer.parseInt(jsonModelo.getString("idGrupoProduto"));
		if (idGrupoProduto > 0) {
			grupoProdutoTO.setIdGrupoProduto(idGrupoProduto);
		}
		
		grupoProdutoTO.setTipoProdutoTO(new TipoProdutoTO(Integer.parseInt(jsonModelo.getString("idTipoProduto"))));
		grupoProdutoTO.setFabricanteTO(new FabricanteTO(Integer.parseInt(jsonModelo.getString("idFabricante"))));
		grupoProdutoTO.setNmGrupoProduto(jsonModelo.getString("nmGrupoProduto"));
		grupoProdutoTO.setUrl(jsonModelo.getString("url"));
		grupoProdutoTO.setDsNota(jsonModelo.getString("dsNota"));
		grupoProdutoTO.setInFimVida(jsonModelo.getString("inFimVida"));
		grupoProdutoTO.setInDisponivel(jsonModelo.getString("inDisponivel"));
		
		Integer idCor = Integer.parseInt(jsonModelo.getString("idCor"));
		if (idCor > 0) {
			grupoProdutoTO.setCorTO(new CorTO(idCor));
		}
		
		JSONArray jsonCaracteristicasArray = new JSONArray(jsonModelo.getString("idsCaracteristicas"));
		int qtdeIdsCaracteristicas = jsonCaracteristicasArray.length();
		if (qtdeIdsCaracteristicas > 0) {
			List<CaracteristicaTO> caracteristicaTOList = new ArrayList<CaracteristicaTO>();
			List<ValorCaracteristicaTO> valorCaracteristicaTOList = new ArrayList<ValorCaracteristicaTO>();
			for (int i = 0; i < qtdeIdsCaracteristicas; i ++) {
				JSONObject jsonCaracteristica = jsonCaracteristicasArray.getJSONObject(i);
				int idCaracteristica = jsonCaracteristica.getInt("id");
				CaracteristicaTO caracteristicaTO = new CaracteristicaTO(new Integer(idCaracteristica));
				JSONArray jsonValoresCaracteristicasArray = jsonCaracteristica.getJSONArray("valores");
				int qtdeIdsValoresCartacteristicas = jsonValoresCaracteristicasArray.length();
				if (qtdeIdsValoresCartacteristicas > 0) {
					for (int j = 0; j < qtdeIdsValoresCartacteristicas; j ++) {
						int idValorCaracteristica = jsonValoresCaracteristicasArray.getInt(j);
						ValorCaracteristicaTO valorCaracteristicaTO = new ValorCaracteristicaTO(new Integer(idValorCaracteristica));
						valorCaracteristicaTO.setCaracteristicaTO(caracteristicaTO);
						valorCaracteristicaTOList.add(valorCaracteristicaTO);
					}
				} else {
					caracteristicaTOList.add(caracteristicaTO);
				}
			}
			grupoProdutoTO.setCaracteristicaTOList(caracteristicaTOList);
			grupoProdutoTO.setValorCaracteristicaTOList(valorCaracteristicaTOList);
		}
		
		JSONArray jsonTecnologiasArray = new JSONArray(jsonModelo.getString("idsTecnologias"));
		int qtdeIdsTecnologias = jsonTecnologiasArray.length();
		if (qtdeIdsTecnologias > 0) {
			List<TecnologiaTO> tecnologiaTOList = new ArrayList<TecnologiaTO>(qtdeIdsTecnologias);
			for (int i1 = 0; i1 < qtdeIdsTecnologias; i1 ++) {
				JSONObject jsonTecnologia = jsonTecnologiasArray.getJSONObject(i1);
				TecnologiaTO tecnologiaTO = new TecnologiaTO(jsonTecnologia.getInt("id"));
				tecnologiaTOList.add(tecnologiaTO);
				JSONArray jsonTiposFrequenciaArray = jsonTecnologia.getJSONArray("tiposFrequencia");
				int qtdeIdsTiposFrequencia = jsonTiposFrequenciaArray.length();
				if (qtdeIdsTiposFrequencia > 0) {
					List<TipoFrequenciaTO> tipoFrequenciaTOList = new ArrayList<TipoFrequenciaTO>(qtdeIdsTiposFrequencia);
					for (int i2 = 0; i2 < qtdeIdsTiposFrequencia; i2 ++) {
						JSONObject jsonTipoFrequencia = jsonTiposFrequenciaArray.getJSONObject(i2);
						TipoFrequenciaTO tipoFrequenciaTO = new TipoFrequenciaTO(jsonTipoFrequencia.getInt("id"));
						tipoFrequenciaTOList.add(tipoFrequenciaTO);
						JSONArray jsonValoresArray = jsonTipoFrequencia.getJSONArray("valores");
						int qtdeIdsValores = jsonValoresArray.length();
						if (qtdeIdsValores > 0) {
							List<VlFrequenciaTO> vlFrequenciaTOList = new ArrayList<VlFrequenciaTO>(qtdeIdsValores);
							for (int i3 = 0; i3 < qtdeIdsValores; i3 ++) {
								int idVlFrequencia = jsonValoresArray.getInt(i3);
								VlFrequenciaTO vlFrequenciaTO = new VlFrequenciaTO(new Integer(idVlFrequencia));
								vlFrequenciaTOList.add(vlFrequenciaTO);
							}
							tipoFrequenciaTO.setVlFrequenciaTOList(vlFrequenciaTOList);
						}
					}
					tecnologiaTO.setTipoFrequenciaTOList(tipoFrequenciaTOList);
				}
			}
			grupoProdutoTO.setTecnologiaTOList(tecnologiaTOList);
		}
		
		JSONArray jsonMultimidiasArray = new JSONArray(jsonModelo.getString("multimidias"));
		int qtdeMultimidias = jsonMultimidiasArray.length();
		if (qtdeMultimidias > 0) {
			List<MultimidiaTO> multimidiaTOList = new ArrayList<MultimidiaTO>(qtdeMultimidias);
			for (int i = 0; i < qtdeMultimidias; i ++) {
				JSONObject jsonMultimidia = jsonMultimidiasArray.getJSONObject(i);
				MultimidiaTO multimidiaTO = new MultimidiaTO();
				multimidiaTO.setNomeMultimidia(jsonMultimidia.getString("nome"));
				if (!jsonMultimidia.isNull("idClassificacao")) {
					Integer idClassificacao = jsonMultimidia.getInt("idClassificacao");
					if (idClassificacao > 0) {
						multimidiaTO.setClassificacaoTO(new ClassificacaoMultimidiaTO(idClassificacao));
					}
				}
				if (!jsonMultimidia.isNull("idCor")) {
					idCor = jsonMultimidia.getInt("idCor");
					if (idCor > 0) {
						multimidiaTO.setCorTO(new CorTO(idCor));
					}
				}
				multimidiaTOList.add(multimidiaTO);
			}
			grupoProdutoTO.setMultimidiaTOList(multimidiaTOList);
		}
		
		return grupoProdutoTO;
	}
	
	private String obterErro(Exception e, String erroDefault) {
		String erro = null;
		Throwable cause = e.getCause();
		if (cause != null) {
			erro = cause.getMessage();
		}
		if (erro == null || erro.length() == 0) {
			erro = e.getMessage();
		}
		if (erro == null || erro.length() == 0) {
			erro = erroDefault;
		}
		return erro;
	}
	
	private void returnJson(String json, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.setBufferSize(1024);
		try {
			PrintWriter writer = response.getWriter();
			writer.write(json);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void uploadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject jsonResult = new JSONObject();
		
		ParametrizacaoProdutoModeloForm parametrizacaoProdutoModeloForm = (ParametrizacaoProdutoModeloForm) request.getAttribute("parametrizacaoProdutoModeloForm");
		FormFile file = null; 
		if (parametrizacaoProdutoModeloForm.getFileMultimidia() != null) {
			file = parametrizacaoProdutoModeloForm.getFileMultimidia();
			
		}
		
		try {
			String fileName = ImagemUtil.salvarImagem(file);
			jsonResult.put("nome", fileName);
			jsonResult.put("caminho_link_imagens_modelo", ApplicationConfiguration.getCaminhoLinkImagensModelo());
			
			List<ClassificacaoMultimidiaTO> classificacoes = (new ClassificacaoMultimidiaDelegate()).findAll();
			jsonResult.put("classificacoes", JSONUtil.toJSONArray(classificacoes));
			
			List<CorTO> cores = (new CorDelegate()).findAll();
			jsonResult.put("cores", JSONUtil.toJSONArray(cores));
		} catch (Exception e) {
			e.printStackTrace();
			try {
				jsonResult.put("erro", obterErro(e, "Erro ao carregar o arquivo."));
			} catch (JSONException je) {}
		}
		
		response.setContentType("text/html");
		response.setCharacterEncoding("iso-8859-1");
		response.setBufferSize(1024);
		
		PrintWriter writer = response.getWriter();
		writer.write(jsonResult.toString());
		writer.flush();
		writer.close();
	}
	
	
}