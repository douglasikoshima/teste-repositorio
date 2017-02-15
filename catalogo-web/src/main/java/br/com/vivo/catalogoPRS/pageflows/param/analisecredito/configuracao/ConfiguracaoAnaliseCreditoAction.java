package br.com.vivo.catalogoPRS.pageflows.param.analisecredito.configuracao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import br.com.vivo.catalogoPRS.delegate.AnaliseCreditoDelegate;
import br.com.vivo.catalogoPRS.delegate.CanalAtendimentoDelegate;
import br.com.vivo.catalogoPRS.delegate.CategoriaScoreDelegate;
import br.com.vivo.catalogoPRS.delegate.CategorizacaoAnaliseCreditoDelegate;
import br.com.vivo.catalogoPRS.delegate.ConfiguracaoAnaliseCreditoDelegate;
import br.com.vivo.catalogoPRS.delegate.RegionalDelegate;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.ConfiguracaoAnaliseCreditoForm;

import com.indracompany.catalogo.dao.CategoriaScoreTOBuilder;
import com.indracompany.catalogo.to.AnaliseCreditoTO;
import com.indracompany.catalogo.to.CabecalhoAnaliseCreditoTO;
import com.indracompany.catalogo.to.CanalAtendimentoTO;
import com.indracompany.catalogo.to.CategoriaScoreTO;
import com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoPorRegionalTO;
import com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO;
import com.indracompany.catalogo.to.OfServicoConfiguracaoScoreTO;
import com.indracompany.catalogo.to.PlanoConfiguracaoScoreTO;
import com.indracompany.catalogo.to.RegionalTO;
import com.indracompany.catalogo.to.ServicoConfiguracaoScoreTO;

/**
 * @author Luiz
 *
 */
public class ConfiguracaoAnaliseCreditoAction extends BaseMappingDispatchAction implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	private final String PLANO = "1";
	private final String SERVICO = "2";
	private final String OFERTA = "3";
    private final String FIXA = "4";
    private String SUCCESS = "success";
	private List<CanalAtendimentoTO> canaisConfiguraveisPorRegionalList;
	
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		this.setCanaisConfiguraveisPorRegionalList(new ConfiguracaoAnaliseCreditoDelegate().getCanaisConfiguraveisPorRegional());
		
		search(mapping,form,request,response);
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		
		ConfiguracaoAnaliseCreditoDelegate configuracaoAnaliseCreditoDelegate = new ConfiguracaoAnaliseCreditoDelegate();
		
		request.setAttribute("cabecalhoAnaliseCreditoList", configuracaoAnaliseCreditoDelegate.findAllNoChild());
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		
		ConfiguracaoAnaliseCreditoForm configuracaoAnaliseCreditoForm = (ConfiguracaoAnaliseCreditoForm)form;
		
		carregar(request);
		resetForm(configuracaoAnaliseCreditoForm);
		atualizarCanaisConfiguraveisPorRegional(configuracaoAnaliseCreditoForm, request);
		
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward remove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		
		ConfiguracaoAnaliseCreditoDelegate configuracaoAnaliseCreditoDelegate = new ConfiguracaoAnaliseCreditoDelegate();
		ConfiguracaoAnaliseCreditoForm configuracaoAnaliseCreditoForm = (ConfiguracaoAnaliseCreditoForm)form;
		
		try {
			configuracaoAnaliseCreditoDelegate.removeAnaliseCredito(doCabecalhoAnaliseCreditoTO(configuracaoAnaliseCreditoForm,request));
		} catch (BusinessException e) {
			request.setAttribute("labelError", e.getMessage());
			return search(mapping,form,request,response);
		}
		return  search(mapping,form,request,response);
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		
		ConfiguracaoAnaliseCreditoForm configuracaoAnaliseCreditoForm = (ConfiguracaoAnaliseCreditoForm)form;
		
		carregar(request);
		
		if (configuracaoAnaliseCreditoForm.getIdCabecalhoAnaliseCredito() != null && !configuracaoAnaliseCreditoForm.getIdCabecalhoAnaliseCredito().equals("")) {
			ConfiguracaoAnaliseCreditoDelegate configuracaoAnaliseCreditoDelegate = new ConfiguracaoAnaliseCreditoDelegate();
			doForm(configuracaoAnaliseCreditoDelegate.findByIdNoChild(doCabecalhoAnaliseCreditoTO(configuracaoAnaliseCreditoForm,request)), configuracaoAnaliseCreditoForm);
		}
		
		atualizarCanaisConfiguraveisPorRegional(configuracaoAnaliseCreditoForm, request);
		
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward order(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		
		ConfiguracaoAnaliseCreditoForm configuracaoAnaliseCreditoForm = (ConfiguracaoAnaliseCreditoForm)form;
		
		String tpClassificacao = configuracaoAnaliseCreditoForm.getTpClassificacao();
		this.resultListConfiguracaoScoreRegional(tpClassificacao,Integer.parseInt(configuracaoAnaliseCreditoForm.getIdCanalAtendimento()),request);
		
		CategorizacaoAnaliseCreditoDelegate categorizacaoAnaliseCreditoDelegate = new CategorizacaoAnaliseCreditoDelegate();
		CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO = doCategorizacaoAnaliseCreditoTO(configuracaoAnaliseCreditoForm);
		
		if (this.PLANO.equals(tpClassificacao)) {
			List<CategorizacaoAnaliseCreditoTO> categorizacaoAnaliseCreditoPlanoTOList = categorizacaoAnaliseCreditoDelegate.searchPlanoConfig(categorizacaoAnaliseCreditoTO);
			if (categorizacaoAnaliseCreditoPlanoTOList != null && categorizacaoAnaliseCreditoPlanoTOList.size() > 0) {
				request.setAttribute("categorizacaoScoreList", buildCategorizacaoAnaliseCreditoTOPorRegional(categorizacaoAnaliseCreditoPlanoTOList, doRegionalTOList(configuracaoAnaliseCreditoForm)));
			}
		} else if (this.SERVICO.equals(tpClassificacao) || this.FIXA.equals(tpClassificacao)) {
			List<CategorizacaoAnaliseCreditoTO> categorizacaoAnaliseCreditoServicoTOList = categorizacaoAnaliseCreditoDelegate.searchServicoConfig(categorizacaoAnaliseCreditoTO);
			if (categorizacaoAnaliseCreditoServicoTOList != null && categorizacaoAnaliseCreditoServicoTOList.size() > 0) {
				request.setAttribute("registroList", categorizacaoAnaliseCreditoServicoTOList);
				request.setAttribute("categorizacaoScoreList", buildCategorizacaoAnaliseCreditoTOPorRegional(categorizacaoAnaliseCreditoServicoTOList, doRegionalTOList(configuracaoAnaliseCreditoForm)));
			}
		} else if (this.OFERTA.equals(tpClassificacao)) {
			List<CategorizacaoAnaliseCreditoTO> categorizacaoAnaliseCreditoOfertaServicoTOList = categorizacaoAnaliseCreditoDelegate.searchOfertaServicoConfig(categorizacaoAnaliseCreditoTO);
			if (categorizacaoAnaliseCreditoOfertaServicoTOList != null && categorizacaoAnaliseCreditoOfertaServicoTOList.size() > 0) {
				request.setAttribute("registroList", categorizacaoAnaliseCreditoOfertaServicoTOList);
				request.setAttribute("categorizacaoScoreList", buildCategorizacaoAnaliseCreditoTOPorRegional(categorizacaoAnaliseCreditoOfertaServicoTOList, doRegionalTOList(configuracaoAnaliseCreditoForm)));
			}
		}
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward copiar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		
		ConfiguracaoAnaliseCreditoForm configuracaoAnaliseCreditoForm = (ConfiguracaoAnaliseCreditoForm)form;
		
		carregar(request);
		CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO = null;
		if (configuracaoAnaliseCreditoForm.getIdCabecalhoAnaliseCredito() != null && !configuracaoAnaliseCreditoForm.getIdCabecalhoAnaliseCredito().equals("")) {
			ConfiguracaoAnaliseCreditoDelegate configuracaoAnaliseCreditoDelegate = new ConfiguracaoAnaliseCreditoDelegate();
			cabecalhoAnaliseCreditoTO = doCabecalhoAnaliseCreditoTO(configuracaoAnaliseCreditoForm,request);
			doForm(configuracaoAnaliseCreditoDelegate.findByIdNoChild(cabecalhoAnaliseCreditoTO), configuracaoAnaliseCreditoForm);
		}
		
		configuracaoAnaliseCreditoForm.setIdCabecalhoAnaliseCredito(null);
		configuracaoAnaliseCreditoForm.setIdCabecalhoAnaliseCreditoCopia(String.valueOf(cabecalhoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito()));
		configuracaoAnaliseCreditoForm.setIdCanalAtendimento(null);
		configuracaoAnaliseCreditoForm.setNome(null);
		configuracaoAnaliseCreditoForm.setStatus("Inativa");
		
		this.cleanHeader(response);
		return  mapping.findForward(SUCCESS);
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		
		ConfiguracaoAnaliseCreditoForm configuracaoAnaliseCreditoForm = (ConfiguracaoAnaliseCreditoForm)form;
		try {
			
			ConfiguracaoAnaliseCreditoDelegate configuracaoAnaliseCreditoDelegate = new ConfiguracaoAnaliseCreditoDelegate();
			if (this.PLANO.equals(configuracaoAnaliseCreditoForm.getTpClassificacao())) {
				configuracaoAnaliseCreditoDelegate.createUpdatePlanoAnaliseCredito(doCabecalhoAnaliseCreditoTO(configuracaoAnaliseCreditoForm,request));
			} else if (this.SERVICO.equals(configuracaoAnaliseCreditoForm.getTpClassificacao()) || this.FIXA.equals(configuracaoAnaliseCreditoForm.getTpClassificacao())) {
				configuracaoAnaliseCreditoDelegate.createUpdateServicoAnaliseCredito(doCabecalhoAnaliseCreditoTO(configuracaoAnaliseCreditoForm,request));
			} else if (this.OFERTA.equals(configuracaoAnaliseCreditoForm.getTpClassificacao())) {
				configuracaoAnaliseCreditoDelegate.createUpdateOfertaAnaliseCredito(doCabecalhoAnaliseCreditoTO(configuracaoAnaliseCreditoForm,request));
			}

			request.setAttribute("labelSucess", "Configura&ccedil;&atilde;o salva com Sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("labelError", e.getMessage());
			return  this.begin(mapping, configuracaoAnaliseCreditoForm, request, response);
		}		
		this.cleanHeader(response);
		return  this.begin(mapping, configuracaoAnaliseCreditoForm, request, response);
	}

	private List<PlanoConfiguracaoScoreTO> buildPlanoConfiguracaoAnaliseCreditoTOList(ConfiguracaoAnaliseCreditoForm configuracaoAnaliseCreditoForm,HttpServletRequest request) throws JSONException{
		JSONObject cfgScoreJSON;
		List<PlanoConfiguracaoScoreTO> configuracoesAnaliseCreditoList = new ArrayList<PlanoConfiguracaoScoreTO>();
			cfgScoreJSON = new JSONObject(configuracaoAnaliseCreditoForm.getConfiguracoesJson());
			JSONArray scoreConfigList =  new JSONArray(cfgScoreJSON.getString("cfgScoreKeysAGravar"));
			for(int i = 0; i < scoreConfigList.length(); i++ ){
				PlanoConfiguracaoScoreTO to = (PlanoConfiguracaoScoreTO) new PlanoConfiguracaoScoreTO().buildConfiguracaoAnaliseCreditoTO(scoreConfigList.getString(i));					
				to.setDtCriacao(new Date());
				
				UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
				
				to.setNmUsuarioCriacao(userCatalogo.getUser().getUsername());
				configuracoesAnaliseCreditoList.add(to);
			}
		return configuracoesAnaliseCreditoList;
	}

	private List<PlanoConfiguracaoScoreTO> buildPlanoConfiguracaoAnaliseCreditoTORemoveList(ConfiguracaoAnaliseCreditoForm configuracaoAnaliseCreditoForm,HttpServletRequest request) throws JSONException{
		JSONObject cfgScoreJSON;
		List<PlanoConfiguracaoScoreTO> configuracoesAnaliseCreditoList = new ArrayList<PlanoConfiguracaoScoreTO>();
			cfgScoreJSON = new JSONObject(configuracaoAnaliseCreditoForm.getConfiguracoesJson());
			JSONArray scoreConfigList =  new JSONArray(cfgScoreJSON.getString("cfgScoreKeysARemover"));
			for(int i = 0; i < scoreConfigList.length(); i++ ){
				PlanoConfiguracaoScoreTO to = (PlanoConfiguracaoScoreTO) new PlanoConfiguracaoScoreTO().buildConfiguracaoAnaliseCreditoTO(scoreConfigList.getString(i));					
				to.setDtCriacao(new Date());
				
				UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
				
				to.setNmUsuarioCriacao(userCatalogo.getUser().getUsername());
				configuracoesAnaliseCreditoList.add(to);
			}
		return configuracoesAnaliseCreditoList;
	}
	
	private List<ServicoConfiguracaoScoreTO> buildServicoConfiguracaoAnaliseCreditoTOList(ConfiguracaoAnaliseCreditoForm configuracaoAnaliseCreditoForm,HttpServletRequest request) throws JSONException{
		JSONObject cfgScoreJSON;
		List<ServicoConfiguracaoScoreTO> configuracoesAnaliseCreditoList = new ArrayList<ServicoConfiguracaoScoreTO>();
			cfgScoreJSON = new JSONObject(configuracaoAnaliseCreditoForm.getConfiguracoesJson());
			JSONArray scoreConfigList =  new JSONArray(cfgScoreJSON.getString("cfgScoreKeysAGravar"));
			for(int i = 0; i < scoreConfigList.length(); i++ ){
				ServicoConfiguracaoScoreTO to = (ServicoConfiguracaoScoreTO) new ServicoConfiguracaoScoreTO().buildConfiguracaoAnaliseCreditoTO(scoreConfigList.getString(i));					
				to.setDtCriacao(new Date());
				
				UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
				
				to.setNmUsuarioCriacao(userCatalogo.getUser().getUsername());
				configuracoesAnaliseCreditoList.add(to);
			}
		return configuracoesAnaliseCreditoList;
	}
	
	private List<ServicoConfiguracaoScoreTO> buildServicoConfiguracaoAnaliseCreditoTORemoveList(ConfiguracaoAnaliseCreditoForm configuracaoAnaliseCreditoForm,HttpServletRequest request) throws JSONException{
		JSONObject cfgScoreJSON;
		List<ServicoConfiguracaoScoreTO> configuracoesAnaliseCreditoList = new ArrayList<ServicoConfiguracaoScoreTO>();
			cfgScoreJSON = new JSONObject(configuracaoAnaliseCreditoForm.getConfiguracoesJson());
			JSONArray scoreConfigList =  new JSONArray(cfgScoreJSON.getString("cfgScoreKeysARemover"));
			for(int i = 0; i < scoreConfigList.length(); i++ ){
				ServicoConfiguracaoScoreTO to = (ServicoConfiguracaoScoreTO) new ServicoConfiguracaoScoreTO().buildConfiguracaoAnaliseCreditoTO(scoreConfigList.getString(i));					
				to.setDtCriacao(new Date());
				UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
				
				to.setNmUsuarioCriacao(userCatalogo.getUser().getUsername());
				configuracoesAnaliseCreditoList.add(to);
			}
		return configuracoesAnaliseCreditoList;
	}
	
	private List<OfServicoConfiguracaoScoreTO> buildOfertaConfiguracaoAnaliseCreditoTOList(ConfiguracaoAnaliseCreditoForm configuracaoAnaliseCreditoForm,HttpServletRequest request) throws JSONException{
		JSONObject cfgScoreJSON;
		List<OfServicoConfiguracaoScoreTO> configuracoesAnaliseCreditoList = new ArrayList<OfServicoConfiguracaoScoreTO>();
			cfgScoreJSON = new JSONObject(configuracaoAnaliseCreditoForm.getConfiguracoesJson());
			JSONArray scoreConfigList =  new JSONArray(cfgScoreJSON.getString("cfgScoreKeysAGravar"));
			for(int i = 0; i < scoreConfigList.length(); i++ ){
				OfServicoConfiguracaoScoreTO to = (OfServicoConfiguracaoScoreTO) new OfServicoConfiguracaoScoreTO().buildConfiguracaoAnaliseCreditoTO(scoreConfigList.getString(i));					
				to.setDtCriacao(new Date());
				UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
				
				to.setNmUsuarioCriacao(userCatalogo.getUser().getUsername());
				configuracoesAnaliseCreditoList.add(to);
			}
		return configuracoesAnaliseCreditoList;
	}
	
	private List<OfServicoConfiguracaoScoreTO> buildOfertaConfiguracaoAnaliseCreditoTORemoveList(ConfiguracaoAnaliseCreditoForm configuracaoAnaliseCreditoForm,HttpServletRequest request) throws JSONException{
		JSONObject cfgScoreJSON;
		List<OfServicoConfiguracaoScoreTO> configuracoesAnaliseCreditoList = new ArrayList<OfServicoConfiguracaoScoreTO>();
			cfgScoreJSON = new JSONObject(configuracaoAnaliseCreditoForm.getConfiguracoesJson());
			JSONArray scoreConfigList =  new JSONArray(cfgScoreJSON.getString("cfgScoreKeysARemover"));
			for(int i = 0; i < scoreConfigList.length(); i++ ){
				OfServicoConfiguracaoScoreTO to = (OfServicoConfiguracaoScoreTO) new OfServicoConfiguracaoScoreTO().buildConfiguracaoAnaliseCreditoTO(scoreConfigList.getString(i));					
				to.setDtCriacao(new Date());
				UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
				
				to.setNmUsuarioCriacao(userCatalogo.getUser().getUsername());
				configuracoesAnaliseCreditoList.add(to);
			}
		return configuracoesAnaliseCreditoList;
	}
	
	public ActionForward searchProduto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		
		ConfiguracaoAnaliseCreditoForm configuracaoAnaliseCreditoForm = (ConfiguracaoAnaliseCreditoForm)form;
		
		if (configuracaoAnaliseCreditoForm.isSalvarConfigScore()){
			save(mapping,form,request,response);
			configuracaoAnaliseCreditoForm.setSalvarConfigScore(false);
		}
		
		String tpClassificacao = configuracaoAnaliseCreditoForm.getTpClassificacao();
		this.resultListConfiguracaoScoreRegional(tpClassificacao,Integer.parseInt(configuracaoAnaliseCreditoForm.getIdCanalAtendimento()),request);
		
		CategorizacaoAnaliseCreditoDelegate categorizacaoAnaliseCreditoDelegate = new CategorizacaoAnaliseCreditoDelegate();
		CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO = doCategorizacaoAnaliseCreditoTO(configuracaoAnaliseCreditoForm);
		
		atualizarCanaisConfiguraveisPorRegional(configuracaoAnaliseCreditoForm, request);
		if (this.PLANO.equals(tpClassificacao)) {
			List<CategorizacaoAnaliseCreditoTO> categorizacaoAnaliseCreditoPlanoTOList = categorizacaoAnaliseCreditoDelegate.searchPlanoConfig(categorizacaoAnaliseCreditoTO);
			if (categorizacaoAnaliseCreditoPlanoTOList != null && categorizacaoAnaliseCreditoPlanoTOList.size() > 0) {
				if (configuracaoAnaliseCreditoForm.getCanalConfiguravelPorRegional()) {
					request.setAttribute("categorizacaoScoreList", buildCategorizacaoAnaliseCreditoTOPorRegional(categorizacaoAnaliseCreditoPlanoTOList, doRegionalTOList(configuracaoAnaliseCreditoForm)));
				} else {
					request.setAttribute("categorizacaoScoreList", categorizacaoAnaliseCreditoPlanoTOList);
				}
			}
		} else if (this.SERVICO.equals(tpClassificacao) || this.FIXA.equals(tpClassificacao)) {
			List<CategorizacaoAnaliseCreditoTO> categorizacaoAnaliseCreditoServicoTOList = categorizacaoAnaliseCreditoDelegate.searchServicoConfig(categorizacaoAnaliseCreditoTO);
			if (categorizacaoAnaliseCreditoServicoTOList != null && categorizacaoAnaliseCreditoServicoTOList.size() > 0) {
				if (configuracaoAnaliseCreditoForm.getCanalConfiguravelPorRegional()) {
					request.setAttribute("categorizacaoScoreList", buildCategorizacaoAnaliseCreditoTOPorRegional(categorizacaoAnaliseCreditoServicoTOList, doRegionalTOList(configuracaoAnaliseCreditoForm)));
				} else {
					request.setAttribute("categorizacaoScoreList", categorizacaoAnaliseCreditoServicoTOList);
				}
			}
		} else if (this.OFERTA.equals(tpClassificacao)) {
			List<CategorizacaoAnaliseCreditoTO> categorizacaoAnaliseCreditoOfertaServicoTOList = categorizacaoAnaliseCreditoDelegate.searchOfertaServicoConfig(categorizacaoAnaliseCreditoTO);
			if (categorizacaoAnaliseCreditoOfertaServicoTOList != null && categorizacaoAnaliseCreditoOfertaServicoTOList.size() > 0) {
				if (configuracaoAnaliseCreditoForm.getCanalConfiguravelPorRegional()) {
					request.setAttribute("categorizacaoScoreList", buildCategorizacaoAnaliseCreditoTOPorRegional(categorizacaoAnaliseCreditoOfertaServicoTOList, doRegionalTOList(configuracaoAnaliseCreditoForm)));
				} else {
					request.setAttribute("categorizacaoScoreList", categorizacaoAnaliseCreditoOfertaServicoTOList);
				}
			}
		}
	
		AnaliseCreditoDelegate analiseCreditoDelegate = new AnaliseCreditoDelegate();
		List<AnaliseCreditoTO> analiseCreditoList = analiseCreditoDelegate.findAll();
		request.setAttribute("analiseCreditoList", analiseCreditoList);
		
		// Necessario verificar se e uma copia. Utilizar idCabecalhoAnaliseCreditoCopia 
		String idCabecalhoAnaliseCredito = !configuracaoAnaliseCreditoForm.getIdCabecalhoAnaliseCreditoCopia().equals("") ? configuracaoAnaliseCreditoForm.getIdCabecalhoAnaliseCreditoCopia() : configuracaoAnaliseCreditoForm.getIdCabecalhoAnaliseCredito(); 
		configuracaoAnaliseCreditoForm.setIdCabecalhoAnaliseCredito(String.valueOf(idCabecalhoAnaliseCredito));
		
		if (configuracaoAnaliseCreditoForm.getIdCabecalhoAnaliseCredito() != null && !configuracaoAnaliseCreditoForm.getIdCabecalhoAnaliseCredito().equals("")) {
			ConfiguracaoAnaliseCreditoDelegate configuracaoAnaliseCreditoDelegate = new ConfiguracaoAnaliseCreditoDelegate();
			CabecalhoAnaliseCreditoTO analiseCreditoTO = null;
			if (this.PLANO.equals(tpClassificacao)) {
				analiseCreditoTO = configuracaoAnaliseCreditoDelegate.findByIdWithPlanos(doCabecalhoAnaliseCreditoTO(configuracaoAnaliseCreditoForm,request), categorizacaoAnaliseCreditoTO);
			} else if (this.SERVICO.equals(tpClassificacao) || this.FIXA.equals(tpClassificacao)) {
				analiseCreditoTO = configuracaoAnaliseCreditoDelegate.findByIdWithServicos(doCabecalhoAnaliseCreditoTO(configuracaoAnaliseCreditoForm,request));
			} else if (OFERTA.equals(tpClassificacao)) {
				analiseCreditoTO = configuracaoAnaliseCreditoDelegate.findByIdWithOfertas(doCabecalhoAnaliseCreditoTO(configuracaoAnaliseCreditoForm,request));
			}
			doForm(analiseCreditoTO, configuracaoAnaliseCreditoForm);
		}
		
		request.setAttribute("tpClassificacao", tpClassificacao);
		
		if(configuracaoAnaliseCreditoForm.getIdCabecalhoAnaliseCredito() !=null && !configuracaoAnaliseCreditoForm.getIdCabecalhoAnaliseCredito().equals("")){
			categorizacaoAnaliseCreditoTO.setIdCabecalhoAnaliseCredito(Integer.parseInt(configuracaoAnaliseCreditoForm.getIdCabecalhoAnaliseCredito()));
		}
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	private List<CategorizacaoAnaliseCreditoPorRegionalTO> buildCategorizacaoAnaliseCreditoTOPorRegional(List<CategorizacaoAnaliseCreditoTO> categorizacaoAnaliseCreditoTOList, List<RegionalTO> regionalTOList) {
		List<CategorizacaoAnaliseCreditoPorRegionalTO> result = new ArrayList<CategorizacaoAnaliseCreditoPorRegionalTO>();
		for (CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO : categorizacaoAnaliseCreditoTOList) {
			for (RegionalTO regionalTO : regionalTOList) {
				CategorizacaoAnaliseCreditoPorRegionalTO to = new CategorizacaoAnaliseCreditoPorRegionalTO(
					categorizacaoAnaliseCreditoTO.getId(),
					categorizacaoAnaliseCreditoTO.getNome(),
					categorizacaoAnaliseCreditoTO.getNmPlataforma(),
					categorizacaoAnaliseCreditoTO.getIdPlataforma(),
					categorizacaoAnaliseCreditoTO.getNmCategoria(),
					categorizacaoAnaliseCreditoTO.getIdCategoria(),
					categorizacaoAnaliseCreditoTO.getIdPlataformas(),
					categorizacaoAnaliseCreditoTO.getIdRegionais(),
					categorizacaoAnaliseCreditoTO.getValor(),
					categorizacaoAnaliseCreditoTO.getPrecoDe(),
					categorizacaoAnaliseCreditoTO.getPrecoAte(),
					categorizacaoAnaliseCreditoTO.getIdCategoriaScore(),
					categorizacaoAnaliseCreditoTO.getIdAnaliseCredito(),
					categorizacaoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito(),
					categorizacaoAnaliseCreditoTO.getScoresConfigurados(),
					regionalTO
				);
				result.add(to);
			}
		}
		return result;
	}
	
	private void resultListConfiguracaoScoreRegional(String tpClassificacao, Integer tpCanalAtendimento, HttpServletRequest request) {	
		if (this.FIXA.equals(tpClassificacao)) {
			request.setAttribute("resultListConfiguracaoScoreRegional", false);	
		}else if (this.PLANO.equals(tpClassificacao) || this.SERVICO.equals(tpClassificacao) || this.OFERTA.equals(tpClassificacao)){
			if (tpCanalAtendimento == 1 || tpCanalAtendimento == 2 || 
					 tpCanalAtendimento == 26 || tpCanalAtendimento == 1262 || tpCanalAtendimento == 23611){
				request.setAttribute("resultListConfiguracaoScoreRegional", true);
			}else{
				request.setAttribute("resultListConfiguracaoScoreRegional",false);
			}
		}	
	}
	
	private List<RegionalTO> doRegionalTOList(ConfiguracaoAnaliseCreditoForm configuracaoAnaliseCreditoForm){
		List<RegionalTO> regionalTOList = new ArrayList<RegionalTO>();
		JSONArray listaRegionais;
		try {
			listaRegionais = new JSONArray (new JSONObject(configuracaoAnaliseCreditoForm.getRegionaisJsonSearch()).getString("listaRegionais"));
			for(int i = 0; i < listaRegionais.length(); i++){
				JSONObject regionalJson = listaRegionais.getJSONObject(i);
				RegionalTO regionalTO = new RegionalTO();
				regionalTO.setIdRegional(regionalJson.getInt("idRegional"));
				regionalTO.setNmRegional(regionalJson.getString("nmRegional"));
				regionalTOList.add(regionalTO);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return	regionalTOList;
	}
	
	public ActionForward ativarDesativar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		
		ConfiguracaoAnaliseCreditoDelegate configuracaoAnaliseCreditoDelegate = new ConfiguracaoAnaliseCreditoDelegate();
		ConfiguracaoAnaliseCreditoForm configuracaoAnaliseCreditoForm = (ConfiguracaoAnaliseCreditoForm)form;
		try {
			String status = request.getParameter("status");
			
			String msgAtivacao = "";
			if ("S".equalsIgnoreCase(status)){
				msgAtivacao=  "Configura&ccedil;&atilde;o Desativada com sucesso!";
			} else if ("N".equalsIgnoreCase(status)) {
				msgAtivacao=  "Configura&ccedil;&atilde;o Ativada com sucesso!";
			} else {
				msgAtivacao=  "Configura&ccedil;&atilde;o Ativada/Desativada com sucesso!";
			}
			
			configuracaoAnaliseCreditoDelegate.desativarAtivar(doCabecalhoAnaliseCreditoTO(configuracaoAnaliseCreditoForm,request));
			
			request.setAttribute("labelSucess", msgAtivacao);
			search(mapping,form,request,response);
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("labelError", e.getMessage());
			return search(mapping,form,request,response);
		}
		
		this.cleanHeader(response);
		return  mapping.findForward(SUCCESS);
	}
	
	private CategorizacaoAnaliseCreditoTO doCategorizacaoAnaliseCreditoTO(ConfiguracaoAnaliseCreditoForm configuracaoAnaliseCreditoForm)  {
		
		CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO = new CategorizacaoAnaliseCreditoTO();
		categorizacaoAnaliseCreditoTO.setNome(configuracaoAnaliseCreditoForm.getNomeSearch());
		
		if(configuracaoAnaliseCreditoForm.getIdCategoriaSearch() !=null && !configuracaoAnaliseCreditoForm.getIdCategoriaSearch().equals("")){
			categorizacaoAnaliseCreditoTO.setIdCategoria(Integer.parseInt(configuracaoAnaliseCreditoForm.getIdCategoriaSearch()));
		}
		if(configuracaoAnaliseCreditoForm.getIdCabecalhoAnaliseCredito() !=null && !configuracaoAnaliseCreditoForm.getIdCabecalhoAnaliseCredito().equals("")){
			categorizacaoAnaliseCreditoTO.setIdCabecalhoAnaliseCredito(Integer.parseInt(configuracaoAnaliseCreditoForm.getIdCabecalhoAnaliseCredito()));
		}

		if (configuracaoAnaliseCreditoForm.getPrecoAteSearch() != null && !configuracaoAnaliseCreditoForm.getPrecoAteSearch().equals("")) {
			categorizacaoAnaliseCreditoTO.setPrecoAte(Float.parseFloat(configuracaoAnaliseCreditoForm.getPrecoAteSearch().replace(".", "").replace(",", ".")));
		}
		if (configuracaoAnaliseCreditoForm.getPrecoDeSearch() != null && !configuracaoAnaliseCreditoForm.getPrecoDeSearch().equals("")) {
			categorizacaoAnaliseCreditoTO.setPrecoDe(Float.parseFloat(configuracaoAnaliseCreditoForm.getPrecoDeSearch().replace(".", "").replace(",", ".")));
		}
		if (configuracaoAnaliseCreditoForm.getIdAnaliseCreditoSearch() !=null && !configuracaoAnaliseCreditoForm.getIdAnaliseCreditoSearch().equals("")) {
			categorizacaoAnaliseCreditoTO.setIdAnaliseCredito(Integer.parseInt(configuracaoAnaliseCreditoForm.getIdAnaliseCreditoSearch()));
		}
		if(configuracaoAnaliseCreditoForm.getRegionaisJsonSearch() != null){
			List<RegionalTO> regionalTOList = doRegionalTOList(configuracaoAnaliseCreditoForm);
			Integer[] idRegionais = new Integer[regionalTOList.size()];
			for(int i = 0; i < regionalTOList.size(); i++){
				idRegionais[i] = regionalTOList.get(i).getIdRegional();
			}
			categorizacaoAnaliseCreditoTO.setIdRegionais(idRegionais);
		}
		
		return categorizacaoAnaliseCreditoTO;
	}

	private CabecalhoAnaliseCreditoTO doCabecalhoAnaliseCreditoTO(ConfiguracaoAnaliseCreditoForm configuracaoAnaliseCreditoForm, HttpServletRequest request)  {
		
		CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO = new CabecalhoAnaliseCreditoTO();
		cabecalhoAnaliseCreditoTO.setNmCabecalhoAnaliseCredito(configuracaoAnaliseCreditoForm.getNome());
		cabecalhoAnaliseCreditoTO.setDtCriacao(new Date());
		
		UserCatalogo userCatalogo = (UserCatalogo)request.getSession().getAttribute("logged_user");
		
		cabecalhoAnaliseCreditoTO.setNmUsuarioCriacao(userCatalogo.getUser().getUsername());
		
		if(configuracaoAnaliseCreditoForm.getIdCanalAtendimento() != null && !configuracaoAnaliseCreditoForm.getIdCanalAtendimento().equals("")){
			cabecalhoAnaliseCreditoTO.setCanalAtendimento(new CanalAtendimentoTO(Integer.parseInt(configuracaoAnaliseCreditoForm.getIdCanalAtendimento())));
		}
		
		if(configuracaoAnaliseCreditoForm.getIdCabecalhoAnaliseCredito() != null && !configuracaoAnaliseCreditoForm.getIdCabecalhoAnaliseCredito().equals("")){
			cabecalhoAnaliseCreditoTO.setIdCabecalhoAnaliseCredito(Integer.parseInt(configuracaoAnaliseCreditoForm.getIdCabecalhoAnaliseCredito()));
		}
		
		cabecalhoAnaliseCreditoTO.setInDisponivel(configuracaoAnaliseCreditoForm.getInDisponivel());
		
		List<OfServicoConfiguracaoScoreTO> ofServicoConfiguracaoScoreList = new ArrayList<OfServicoConfiguracaoScoreTO>();
		List<PlanoConfiguracaoScoreTO> planoConfiguracaoScoreList = new ArrayList<PlanoConfiguracaoScoreTO>();
		List<ServicoConfiguracaoScoreTO> servicoConfiguracaoScoreList = new ArrayList<ServicoConfiguracaoScoreTO>();	
		
		List<OfServicoConfiguracaoScoreTO> ofServicoConfiguracaoScoreListRemove = new ArrayList<OfServicoConfiguracaoScoreTO>();
		List<PlanoConfiguracaoScoreTO> planoConfiguracaoScoreListRemove = new ArrayList<PlanoConfiguracaoScoreTO>();
		List<ServicoConfiguracaoScoreTO> servicoConfiguracaoScoreListRemove = new ArrayList<ServicoConfiguracaoScoreTO>();
		
		if(configuracaoAnaliseCreditoForm.getConfiguracoesJson() != null) {
			try {
				if(PLANO.equals(configuracaoAnaliseCreditoForm.getTpClassificacao())){
					planoConfiguracaoScoreList = buildPlanoConfiguracaoAnaliseCreditoTOList(configuracaoAnaliseCreditoForm,request);
					planoConfiguracaoScoreListRemove = buildPlanoConfiguracaoAnaliseCreditoTORemoveList(configuracaoAnaliseCreditoForm,request);
				} else if (SERVICO.equals(configuracaoAnaliseCreditoForm.getTpClassificacao())){
					servicoConfiguracaoScoreList = buildServicoConfiguracaoAnaliseCreditoTOList(configuracaoAnaliseCreditoForm,request);
					servicoConfiguracaoScoreListRemove = buildServicoConfiguracaoAnaliseCreditoTORemoveList(configuracaoAnaliseCreditoForm,request);
				} else if(OFERTA.equals(configuracaoAnaliseCreditoForm.getTpClassificacao())){
					ofServicoConfiguracaoScoreList = buildOfertaConfiguracaoAnaliseCreditoTOList(configuracaoAnaliseCreditoForm,request);
					ofServicoConfiguracaoScoreListRemove = buildOfertaConfiguracaoAnaliseCreditoTORemoveList(configuracaoAnaliseCreditoForm,request);
				}
			} catch (JSONException e) {
				request.setAttribute("erro", e.getMessage());
			}
		}
		
		cabecalhoAnaliseCreditoTO.setOfServicoConfiguracaoScoreList(ofServicoConfiguracaoScoreList);
		cabecalhoAnaliseCreditoTO.setPlanoConfiguracaoScoreList(planoConfiguracaoScoreList);
		cabecalhoAnaliseCreditoTO.setServicoConfiguracaoScoreList(servicoConfiguracaoScoreList);
		
		cabecalhoAnaliseCreditoTO.setOfServicoConfiguracaoScoreListRemove(ofServicoConfiguracaoScoreListRemove);
		cabecalhoAnaliseCreditoTO.setPlanoConfiguracaoScoreListRemove(planoConfiguracaoScoreListRemove);
		cabecalhoAnaliseCreditoTO.setServicoConfiguracaoScoreListRemove(servicoConfiguracaoScoreListRemove);		
		
		configuracaoAnaliseCreditoForm.setOfServicoConfiguracaoScoreList(ofServicoConfiguracaoScoreList);
		configuracaoAnaliseCreditoForm.setPlanoConfiguracaoScoreList(planoConfiguracaoScoreList);
		configuracaoAnaliseCreditoForm.setServicoConfiguracaoScoreList(servicoConfiguracaoScoreList);
		
		return cabecalhoAnaliseCreditoTO;
	}
	
	private void carregar(HttpServletRequest request) {
		
		CanalAtendimentoDelegate canalAtendimentoDelegate = new CanalAtendimentoDelegate();
		request.setAttribute("canalAtendimentoList", canalAtendimentoDelegate.findAll());
		
		RegionalDelegate regionalDelgate = new RegionalDelegate();
		request.setAttribute("regionalList", regionalDelgate.findAll());
		
		CategoriaScoreDelegate categoriaScoreDelegate = new CategoriaScoreDelegate();
		
		CategoriaScoreTOBuilder categoriaScoreTOBuilder = new CategoriaScoreTOBuilder(false);
		List<CategoriaScoreTO> categoriaScoreTOList = categoriaScoreDelegate.findAll(categoriaScoreTOBuilder);
		if (categoriaScoreTOList != null && categoriaScoreTOList.size() > 0) {
			request.setAttribute("categoriaScoreList", categoriaScoreTOList);
		}
		
		AnaliseCreditoDelegate analiseCreditoDelegate = new AnaliseCreditoDelegate();
		request.setAttribute("analiseCreditoList", analiseCreditoDelegate.findAll());
	}
	
	private void doForm(CabecalhoAnaliseCreditoTO cabecalhoAnaliseCreditoTO, ConfiguracaoAnaliseCreditoForm configuracaoAnaliseCreditoForm) {
		
		configuracaoAnaliseCreditoForm.setIdCabecalhoAnaliseCredito(String.valueOf(cabecalhoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito()));
		configuracaoAnaliseCreditoForm.setIdCanalAtendimento(String.valueOf(cabecalhoAnaliseCreditoTO.getCanalAtendimento().getIdCanalAtendimento()));
		configuracaoAnaliseCreditoForm.setNome(cabecalhoAnaliseCreditoTO.getNmCabecalhoAnaliseCredito());
		configuracaoAnaliseCreditoForm.setInDisponivel(cabecalhoAnaliseCreditoTO.getInDisponivel());
		
		if (cabecalhoAnaliseCreditoTO.getInDisponivel().equals("S")) {
			configuracaoAnaliseCreditoForm.setStatus("Ativa");
		} else {
			configuracaoAnaliseCreditoForm.setStatus("Inativa");
		}
		
	}
	
	private void resetForm(ConfiguracaoAnaliseCreditoForm configuracaoAnaliseCreditoForm)  {
		configuracaoAnaliseCreditoForm.setConfiguracoes(null);
		configuracaoAnaliseCreditoForm.setConfiguracoesRemove(null);
		configuracaoAnaliseCreditoForm.setIdCabecalhoAnaliseCredito(null);
		configuracaoAnaliseCreditoForm.setIdCanalAtendimento(null);
		configuracaoAnaliseCreditoForm.setInDisponivel(null);
		configuracaoAnaliseCreditoForm.setNome(null);
		configuracaoAnaliseCreditoForm.setStatus("Inativa");
		
	}
	
	private void atualizarCanaisConfiguraveisPorRegional(ConfiguracaoAnaliseCreditoForm configuracaoAnaliseCreditoForm, HttpServletRequest request) {
		JSONArray canaisConfiguraveisPorRegional = new JSONArray();
		Boolean analiseCredConfiguravelPorRegional = false;
		for (CanalAtendimentoTO canalAtendimentoTO : this.canaisConfiguraveisPorRegionalList) {
			canaisConfiguraveisPorRegional.put(canalAtendimentoTO.getIdCanalAtendimento());
			if(configuracaoAnaliseCreditoForm.getIdCanalAtendimento() !=null){
				if(canalAtendimentoTO.getIdCanalAtendimento().equals(Integer.parseInt(configuracaoAnaliseCreditoForm.getIdCanalAtendimento()))){
					analiseCredConfiguravelPorRegional = true;
				}
			}
		}
		configuracaoAnaliseCreditoForm.setCanaisConfiguraveisPorRegionalJSON(canaisConfiguraveisPorRegional.toString());
		configuracaoAnaliseCreditoForm.setCanalConfiguravelPorRegional(analiseCredConfiguravelPorRegional);
		request.setAttribute("canalConfiguravelPorRegional", analiseCredConfiguravelPorRegional);
	}

	public List<CanalAtendimentoTO> getCanaisConfiguraveisPorRegionalList() {
		return canaisConfiguraveisPorRegionalList;
	}

	public void setCanaisConfiguraveisPorRegionalList(
			List<CanalAtendimentoTO> canaisConfiguraveisPorRegionalList) {
		this.canaisConfiguraveisPorRegionalList = canaisConfiguraveisPorRegionalList;
	}
}

	