package br.com.vivo.catalogoPRS.pageflows.param.produtos.produtosNovo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.vivo.catalogoPRS.bo.UserCatalogo;
import br.com.vivo.catalogoPRS.delegate.FabricanteDelegate;
import br.com.vivo.catalogoPRS.delegate.GrupoProdutoDelegate;
import br.com.vivo.catalogoPRS.delegate.ModeloDelegate;
import br.com.vivo.catalogoPRS.delegate.ProdutoDelegate;
import br.com.vivo.catalogoPRS.delegate.TecnologiaDelegate;
import br.com.vivo.catalogoPRS.delegate.TipoProdutoDelegate;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;

import com.indracompany.catalogo.to.CaracteristicaTO;
import com.indracompany.catalogo.to.CorTO;
import com.indracompany.catalogo.to.FabricanteTO;
import com.indracompany.catalogo.to.GrupoProdutoTO;
import com.indracompany.catalogo.to.MultimidiaTO;
import com.indracompany.catalogo.to.ParametrizacaoProdutosTO;
import com.indracompany.catalogo.to.ProdutoTO;
import com.indracompany.catalogo.to.TecnologiaTO;
import com.indracompany.catalogo.to.TipoProdutoTO;

public class ProdutosAction extends BaseMappingDispatchAction {

	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		List<TipoProdutoTO> tipoProdutos = (new TipoProdutoDelegate()).findAll();
		List<TecnologiaTO> tecnologias = (new TecnologiaDelegate()).findAll();
		request.setAttribute("tipoProdutos", tipoProdutos);
		request.setAttribute("tecnologias", tecnologias);
		return mapping.findForward("default");
	}

	public ActionForward getFabricante(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try {
			FabricanteTO fabricanteTO = new FabricanteTO();
			fabricanteTO.setIdTipoProduto(Integer.parseInt(request.getParameter("idTipoProduto")));
			List<FabricanteTO> fabricantes = (new FabricanteDelegate()).search(fabricanteTO);
			JSONArray fabricantesJSON = new JSONArray();
			for(FabricanteTO fabricante:fabricantes){

				JSONObject fabricanteJSON = new JSONObject();

				fabricanteJSON.put("idFabricante", fabricante.getIdFabricante());
				fabricanteJSON.put("nome", fabricante.getNmFabricante());
				fabricantesJSON.put(fabricanteJSON);
			}
			returnJson(fabricantesJSON.toString(), request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ActionForward getModelo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){

		try {
			GrupoProdutoTO gpTO = new GrupoProdutoTO();
			gpTO.setTipoProdutoTO(new TipoProdutoTO(Integer.parseInt(request.getParameter("idTipoProduto"))));
			gpTO.setFabricanteTO(new FabricanteTO(Integer.parseInt(request.getParameter("idFabricante"))));
			gpTO.setInDisponivel("S");
			if(request.getParameter("idTecnologia")!=null){
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
			returnJson(modelosJSON.toString(), request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward pesquisarProdutos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){

		try {


			ParametrizacaoProdutosTO pp = new ParametrizacaoProdutosTO();
			JSONObject filtros = new JSONObject(request.getParameter("form"));
			JSONObject jsonResult = new JSONObject();
			JSONArray results = new JSONArray();
			pp.setRegistroAtual(Integer.parseInt(request.getParameter("iDisplayStart")));
			pp.setRegistrosPorPagina(Integer.parseInt(request.getParameter("iDisplayLength")));
			pp.setNomeCampoOrdenacao("p.dsSAP");
			pp.setOrdemCrescente(true);
			pp.setIdTipoProduto(filtros.getInt("idTipoProduto"));
			pp.setIdFabricante(filtros.getInt("idFabricante"));
			if(filtros.getInt("idTecnologia")>0){

				pp.setIdTecnologia(filtros.getInt("idTecnologia"));
			}
			pp.setFiltrarModelos(filtros.getInt("filtrarPorModelos")==1);
			if(filtros.getInt("idModelo")>0){

				pp.setIdModelo(filtros.getInt("idModelo"));
			}
			if(!filtros.getString("codigoProduto").equals("")){

				pp.setCodigoProduto(filtros.getString("codigoProduto"));
			}
			for(ParametrizacaoProdutosTO result:(new ProdutoDelegate()).pesquisarParametrizacaoProdutos(pp)){

				JSONObject json = new JSONObject();
				json.put("DT_RowId", result.getIdProduto());
				json.put("0", result.getIdProduto());
				json.put("1", result.getCodigoProduto());
				json.put("2", result.getDescricaoSAP());
				json.put("3", result.getNomeProduto());
				json.put("4", result.getNomeTipoProduto());
				json.put("5", result.getNomeTecnologia());
				json.put("6", result.getNomeFabricante());
				if(result.getIdGrupoProduto()!=null){

					JSONArray gp = new JSONArray();
					gp.put(result.getIdGrupoProduto());
					gp.put(result.getNomeGrupoProduto());
					json.accumulate("7", gp);
				}else{
					json.put("7", "");
				}
				if(!result.getNomeCor().equals("")){

					JSONArray cor = new JSONArray();
					cor.put(result.getNomeCor());
					cor.put(result.getRgbCor());
					json.accumulate("8", cor);
				}else{

					json.put("8", "");
				}
				if(result.getRegistroAtual()!=null){

					jsonResult.put("iTotalRecords", result.getTotalRegistros());
					jsonResult.put("iDisplayStart",result.getRegistroAtual());
					jsonResult.put("iDisplayLength",result.getRegistrosPorPagina());
					jsonResult.put("iTotalDisplayRecords",result.getTotalRegistros());
				}
				results.put(json);
			}
			jsonResult.accumulate("aaData", results);
			returnJson(jsonResult.toString(), request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward consultarProduto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try {


			ParametrizacaoProdutosTO produto = (new ProdutoDelegate()).consultar(new ProdutoTO(Integer.parseInt(request.getParameter("idProduto"))));

			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			json.put("idProduto", produto.getIdProduto());
			json.put("codigoProduto", produto.getCodigoProduto());
			json.put("composicaoProduto", produto.getDescricaoProdutoCatalogo());
			json.put("descProduto", produto.getDescricaoSAP());
			json.put("nomeComercial",produto.getNomeProduto());
			json.put("idTipoProduto", produto.getIdTipoProduto());
			json.put("destaqueProduto", produto.getDescricaoNota());
			json.put("nomeCor", produto.getNomeCor());

			for(TipoProdutoTO tp: produto.getTipoProdutos()){

				JSONObject jsonTP = new JSONObject();
				jsonTP.put("idTipoProduto", tp.getIdTipoProduto());
				jsonTP.put("nmTipoProduto", tp.getNmTipoProduto());
				array.put(jsonTP);
			}
			json.put("tipoProdutos", array);
			returnJson(json.toString(), request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward salvarProduto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {


		UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
		
		ParametrizacaoProdutosTO pp = new ParametrizacaoProdutosTO();
		pp.setIdProduto(Integer.parseInt(request.getParameter("idProduto")));
		pp.setDescricaoNota(request.getParameter("destaqueProduto"));
		pp.setNomeProduto(request.getParameter("nomeComercial"));
		pp.setIdTipoProduto(Integer.parseInt(request.getParameter("idTipoProduto")));
		pp.setNomeUsuarioAltercao(userCatalogo.getUser().getUsername());
		boolean salvo = false;
		try {
			salvo = (new ProdutoDelegate()).atualizarProduto(pp);
			returnJson("{ \"salvo\": "+salvo+"}", request, response);
		} catch (BusinessException e) {
			returnJson("{ \"salvo\": "+salvo+", \"mensagem\":\""+e.getMessage()+"\"}", request, response);
			return null;
		}
		
		return null;
	}

	public ActionForward recuperarModelos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try {

			ParametrizacaoProdutosTO pp = new ParametrizacaoProdutosTO();
			JSONArray array = new JSONArray();
			JSONObject jsonResult = new JSONObject();
			JSONObject filtros = new JSONObject(request.getParameter("form"));
			pp.setIdTipoProduto(filtros.getInt("idTipoProduto"));
			pp.setIdFabricante(filtros.getInt("idFabricante"));
			if(filtros.getInt("idTecnologia")>0){

				pp.setIdTecnologia(filtros.getInt("idTecnologia"));
			}
			pp = (new GrupoProdutoDelegate()).recuperarModelos(pp);
			for(GrupoProdutoTO gp : pp.getGrupoProdutoTOs()){

				JSONObject json = new JSONObject();
				JSONArray cores = new JSONArray();
				json.put("DT_RowId", gp.getIdGrupoProduto());
				json.put("0", gp.getIdGrupoProduto());
				json.put("1", gp.getNmGrupoProduto());
				for(CorTO cor: gp.getCores()){

					JSONObject jsonCor = new JSONObject();
					jsonCor.put("idCor", cor.getIdCor());
					jsonCor.put("nomeCor", cor.getNmCor());
					cores.put(jsonCor);
				}
				json.put("2", cores);
				json.put("3", gp.getIdGrupoProduto());
				array.put(json);
			}
			jsonResult.accumulate("aaData", array);
			returnJson(jsonResult.toString(), request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward vincularModelos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {


			ParametrizacaoProdutosTO pp = new ParametrizacaoProdutosTO();
			JSONArray array = new JSONArray(request.getParameter("idProdutos"));
			pp.setRemoverVinculos(Boolean.parseBoolean(request.getParameter("removerAntigos")));
			pp.setIdProdutos(new LinkedList<Integer>());

			for(int i=0;i<array.length();i++){

				pp.getIdProdutos().add(array.getInt(i));
			}
			array = new JSONArray(request.getParameter("grupoProdutos"));
			pp.setGrupoProdutoTOs(new LinkedList<GrupoProdutoTO>());
			for(int i=0;i<array.length();i++){

				GrupoProdutoTO gp = new GrupoProdutoTO();
				JSONObject json = array.getJSONObject(i);
				System.out.println(json.toString());
				gp.setIdGrupoProduto(json.getInt("idGrupoProtudo"));
				gp.setCorTO(new CorTO());
				gp.getCorTO().setIdCor(json.getInt("idCor"));
				pp.getGrupoProdutoTOs().add(gp);
			}
			(new GrupoProdutoDelegate()).removerVinculoGrupoProduto(pp);
			(new GrupoProdutoDelegate()).vincularProdutosGrupoProduto(pp);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward removerVinculos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try {


			ParametrizacaoProdutosTO pp = new ParametrizacaoProdutosTO();
			JSONArray array = new JSONArray(request.getParameter("idProdutos"));
			pp.setRemoverVinculos(Boolean.parseBoolean(request.getParameter("removerAntigos")));
			pp.setIdProdutos(new LinkedList<Integer>());
			for(int i=0;i<array.length();i++){

				pp.getIdProdutos().add(array.getInt(i));
			}
			array = new JSONArray(request.getParameter("idGrupoProduto"));
			pp.setGrupoProdutoTOs(new LinkedList<GrupoProdutoTO>());
			for(int i=0;i<array.length();i++){

				GrupoProdutoTO gp = new GrupoProdutoTO();
				gp.setIdGrupoProduto(array.getInt(i));
				pp.getGrupoProdutoTOs().add(gp);
			}
			(new GrupoProdutoDelegate()).removerVinculoGrupoProduto(pp);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward consultarModelos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try {

			ParametrizacaoProdutosTO pp = new ParametrizacaoProdutosTO();
			pp.setIdModelo(Integer.parseInt(request.getParameter("idModelo")));
			List<MultimidiaTO> mults = (new ProdutoDelegate()).consultarMultimidida(pp);
			GrupoProdutoTO gp = (new GrupoProdutoDelegate()).findById(new GrupoProdutoTO(pp.getIdModelo()));
			
			JSONArray array = new JSONArray();
			JSONObject json = new JSONObject();
			for(MultimidiaTO m : mults){

				JSONObject obj = new JSONObject();
				obj.put("idMultimidia", m.getIdMultimidia());
				obj.put("nomeMultimidia", m.getNomeMultimidia());
				array.put(obj);
			}
			JSONArray nomes = new JSONArray();
			if(gp.getCaracteristicaTOList()!=null){
				
				for(CaracteristicaTO c : gp.getCaracteristicaTOList()){
					
					nomes.put(c.getNmCaracteristica());
				}
				
				json.put("caracteristicas", nomes);
			}
			json.put("folderImage", "/"+ApplicationConfiguration.getCaminhoLinkImagensModelo());
			json.put("multimidias",array);
			returnJson(json.toString(), request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void returnJson(String json, HttpServletRequest request, HttpServletResponse response){

		response.setContentType("application/json");
		response.setCharacterEncoding("iso-8859-1");
		response.setBufferSize(1024);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(json);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}