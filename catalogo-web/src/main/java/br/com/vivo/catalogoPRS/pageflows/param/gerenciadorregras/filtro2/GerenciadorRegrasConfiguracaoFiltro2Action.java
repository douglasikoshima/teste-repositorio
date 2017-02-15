package br.com.vivo.catalogoPRS.pageflows.param.gerenciadorregras.filtro2;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.vivo.catalogoPRS.delegate.CanalAtendimentoDelegate;
import br.com.vivo.catalogoPRS.delegate.GerenciadorRegrasConfiguracaoDelegate;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.GerenciadorRegrasAbaFiltroForm;

import com.indracompany.catalogo.to.CanalAtendimentoTO;
import com.indracompany.catalogo.to.GerenciadorRegrasConfiguracaoTO;
import com.indracompany.catalogo.to.IndicadorComercialRegraPrioridadeTO;
import com.indracompany.catalogo.to.RegraPrioridadeAltaTO;

public class GerenciadorRegrasConfiguracaoFiltro2Action extends BaseMappingDispatchAction implements Serializable {

	private static final long serialVersionUID = -7247966832431287723L;
	private static final Long IN_TIPO_FILTRO = new Long(2);
	private static final String MENSAGEM_SUCESSO_INDICADOR_SALVO = "Indicadores salvos com sucesso.";
	private String SUCCESS = "success";
	
	private List<RegraPrioridadeAltaTO> regraPrioridadeAltaTOList;
	private List<CanalAtendimentoTO> canalAtendimentoDispScaTOList;
	private List<GerenciadorRegrasConfiguracaoTO> gerenciadorRegrasConfiguracaoTOList;
	private List<String> nmIndicadorComercialList;
	
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		this.regraPrioridadeAltaTOList = null;
		this.canalAtendimentoDispScaTOList = null;
		this.gerenciadorRegrasConfiguracaoTOList = null;
		this.nmIndicadorComercialList = null;
		
		this.setCanalAtendimentoDispScaTOList(new CanalAtendimentoDelegate().findAll());
		
		GerenciadorRegrasConfiguracaoDelegate gerenciadorRegrasConfiguracaoDelegate = new GerenciadorRegrasConfiguracaoDelegate();
		GerenciadorRegrasConfiguracaoTO gerenciadorRegrasConfiguracaoTO = new  GerenciadorRegrasConfiguracaoTO();
		gerenciadorRegrasConfiguracaoTO.setInTipoFiltro(GerenciadorRegrasConfiguracaoFiltro2Action.IN_TIPO_FILTRO);
		this.setNmIndicadorComercialList(gerenciadorRegrasConfiguracaoDelegate.buscarNmIndicadorComercialPorTipoFiltro(gerenciadorRegrasConfiguracaoTO));
		
		this.setRegraPrioridadeAltaTOList(gerenciadorRegrasConfiguracaoDelegate.buscarRegraPrioridadeAltaTO());
		
		loadRequestObjects(request);
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward salvar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		GerenciadorRegrasAbaFiltroForm formBean = (GerenciadorRegrasAbaFiltroForm)form;
		
		GerenciadorRegrasConfiguracaoDelegate gerenciadorRegrasConfiguracaoDelegate = new GerenciadorRegrasConfiguracaoDelegate();
		try {
			gerenciadorRegrasConfiguracaoDelegate.salvar(doGerenciadorRegrasConfiguracaoTO(new JSONObject(formBean.getGerenciadorRegrasConfiguracaoTOJSONNew())));
			request.setAttribute("labelSucess", MENSAGEM_SUCESSO_INDICADOR_SALVO);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		List<GerenciadorRegrasConfiguracaoTO> gerenciadorRegrasConfiguracaoTOList = gerenciadorRegrasConfiguracaoDelegate.pesquisarPorIdCanalAtendimento(doGerenciadorRegrasConfiguracaoTO(formBean));
		this.setGerenciadorRegrasConfiguracaoTOList(gerenciadorRegrasConfiguracaoTOList);
		
		formBean.setGerenciadorRegrasConfiguracaoTOJSONNew(null);
		loadRequestObjects(request);
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward pesquisar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		GerenciadorRegrasAbaFiltroForm formBean = (GerenciadorRegrasAbaFiltroForm)form;
		
		List<GerenciadorRegrasConfiguracaoTO> gerenciadorRegrasConfiguracaoTOList = null;
		if(formBean.getIdCanalAtendimento() != null && !formBean.getIdCanalAtendimento().equals(0)){
			gerenciadorRegrasConfiguracaoTOList = new GerenciadorRegrasConfiguracaoDelegate().pesquisarPorIdCanalAtendimento(doGerenciadorRegrasConfiguracaoTO(formBean));
		}
		if(gerenciadorRegrasConfiguracaoTOList != null && !gerenciadorRegrasConfiguracaoTOList.isEmpty()){
			this.setGerenciadorRegrasConfiguracaoTOList(gerenciadorRegrasConfiguracaoTOList);
		} else {
			this.setGerenciadorRegrasConfiguracaoTOList(null);
		}

		loadRequestObjects(request);
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	private void loadRequestObjects(HttpServletRequest request){
		request.setAttribute("nmIndicadorComercialList", this.getNmIndicadorComercialList());
		request.setAttribute("regraPrioridadeAltaTOList", this.getRegraPrioridadeAltaTOList());
		request.setAttribute("canalAtendimentoDispScaTOList", this.getCanalAtendimentoDispScaTOList());
		request.setAttribute("gerenciadorRegrasConfiguracaoTOList", this.getGerenciadorRegrasConfiguracaoTOList());
	}
	
	private GerenciadorRegrasConfiguracaoTO doGerenciadorRegrasConfiguracaoTO(JSONObject gerenciadorRegrasConfiguracaoJSON) throws JSONException{
		GerenciadorRegrasConfiguracaoTO result = new GerenciadorRegrasConfiguracaoTO();
		
		result.setCanalAtendimentoTO(new CanalAtendimentoTO(new Integer(gerenciadorRegrasConfiguracaoJSON.getInt("idCanalAtendimento"))));
		result.setIdServico(new Integer(gerenciadorRegrasConfiguracaoJSON.getInt("idServico")));
		result.setInTipoFiltro(GerenciadorRegrasConfiguracaoFiltro2Action.IN_TIPO_FILTRO);
		
		JSONObject indicadorComercialRegraMapJSON = new JSONObject(gerenciadorRegrasConfiguracaoJSON.getString("indicadorComercialRegraMapJSON")); 
		Iterator indicadorComercialRegraMapKeys = indicadorComercialRegraMapJSON.keys();
		Map<String, IndicadorComercialRegraPrioridadeTO> indicadorComercialRegraMap = new HashMap<String, IndicadorComercialRegraPrioridadeTO>();
		while(indicadorComercialRegraMapKeys.hasNext()){
			String key = (String) indicadorComercialRegraMapKeys.next();
			JSONObject indicadorComercialRegraJSON = indicadorComercialRegraMapJSON.getJSONObject(key);
			RegraPrioridadeAltaTO regraPrioridadeAltaTO = null;
			if(indicadorComercialRegraJSON.getLong("idRegraPrioridadeAlta") != 0){
				 regraPrioridadeAltaTO = new RegraPrioridadeAltaTO(
						indicadorComercialRegraJSON.getLong("idRegraPrioridadeAlta")
						,null
						,indicadorComercialRegraJSON.getString("dsRegraAlta"));
			}
			indicadorComercialRegraMap.put(key,
					new IndicadorComercialRegraPrioridadeTO(
							indicadorComercialRegraJSON.getLong("idIndicadorComercial")
							,indicadorComercialRegraJSON.getString("nmIndicadorComercial")
							,regraPrioridadeAltaTO));
		}
		result.setIndicadorComercialRegraPrioridadeTOMap(indicadorComercialRegraMap);
		return result;
	}
	
	private GerenciadorRegrasConfiguracaoTO doGerenciadorRegrasConfiguracaoTO(GerenciadorRegrasAbaFiltroForm form){
		GerenciadorRegrasConfiguracaoTO result = null;

		if(form != null){
			result = new GerenciadorRegrasConfiguracaoTO();
			result.setCanalAtendimentoTO(new CanalAtendimentoTO(form.getIdCanalAtendimento()));
			result.setInTipoFiltro(GerenciadorRegrasConfiguracaoFiltro2Action.IN_TIPO_FILTRO);
		}
		
		return result;
	}
	
	public List<CanalAtendimentoTO> getCanalAtendimentoDispScaTOList() {
		return canalAtendimentoDispScaTOList;
	}

	public void setCanalAtendimentoDispScaTOList(
			List<CanalAtendimentoTO> canalAtendimentoDispScaTOList) {
		this.canalAtendimentoDispScaTOList = canalAtendimentoDispScaTOList;
	}

	public List<GerenciadorRegrasConfiguracaoTO> getGerenciadorRegrasConfiguracaoTOList() {
		return gerenciadorRegrasConfiguracaoTOList;
	}

	public void setGerenciadorRegrasConfiguracaoTOList(
			List<GerenciadorRegrasConfiguracaoTO> gerenciadorRegrasConfiguracaoTOList) {
		this.gerenciadorRegrasConfiguracaoTOList = gerenciadorRegrasConfiguracaoTOList;
	}
	
	public List<String> getNmIndicadorComercialList() {
		return nmIndicadorComercialList;
	}

	public void setNmIndicadorComercialList(List<String> nmIndicadorComercialList) {
		this.nmIndicadorComercialList = nmIndicadorComercialList;
	}

	public List<RegraPrioridadeAltaTO> getRegraPrioridadeAltaTOList() {
		return regraPrioridadeAltaTOList;
	}

	public void setRegraPrioridadeAltaTOList(
			List<RegraPrioridadeAltaTO> regraPrioridadeAltaTOList) {
		this.regraPrioridadeAltaTOList = regraPrioridadeAltaTOList;
	}
}
