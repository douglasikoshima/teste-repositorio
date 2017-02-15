package com.indracompany.catalogo.to;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GerenciadorRegrasConfiguracaoTO {
	private CanalAtendimentoTO canalAtendimentoTO;
	private Integer idServico;
	private String nmServico;
	private Long inTipoFiltro;
	private Map<String,IndicadorComercialRegraPrioridadeTO> indicadorComercialRegraPrioridadeTOMap;
	
	public GerenciadorRegrasConfiguracaoTO() {
		super();
	}
	
	public GerenciadorRegrasConfiguracaoTO(Integer idServico, String nmServico) {
		super();
		this.idServico = idServico;
		this.nmServico = nmServico;
	}

	public GerenciadorRegrasConfiguracaoTO(Integer idCanalAtendimento, Integer idServico, String nmServico) {
		super();
		this.canalAtendimentoTO = new CanalAtendimentoTO(idCanalAtendimento);
		this.idServico = idServico;
		this.nmServico = nmServico;
	}
	/*	public String toString(){
		JSONObject gerenciadorRegrasJSON = new JSONObject();
		try {
			gerenciadorRegrasJSON.put("idGerencRegra", this.idGerencRegra);
			gerenciadorRegrasJSON.put("idCanalAtendimento", this.canalAtendimentoTO != null ? canalAtendimentoTO.getIdCanalAtendimento(): "" );
			gerenciadorRegrasJSON.put("idServico", this.idServico);
			gerenciadorRegrasJSON.put("nmServico", this.nmServico);
			gerenciadorRegrasJSON.put("inTipoFiltro", this.inTipoFiltro);
			Set<String> nmIndicadorComercialSet = this.indicadorComercialRegraPrioridadeTOMap != null ? this.indicadorComercialRegraPrioridadeTOMap.keySet() : new HashSet<String>();
			JSONArray indicadorComercialJSONArray = new JSONArray();
			for(String nmIndicadorComercial: nmIndicadorComercialSet){
				JSONObject indicadorComercialJSON = new JSONObject();
				indicadorComercialJSON.put("idIndicadorComercial", this.indicadorComercialRegraPrioridadeTOMap.get(nmIndicadorComercial).getIdIndicadorComercial());
				indicadorComercialJSON.put("nmIndicadorComercial", this.indicadorComercialRegraPrioridadeTOMap.get(nmIndicadorComercial).getNmIndicadorComercial());
				RegraPrioridadeAltaTO regraPrioridadeAltaTO = this.indicadorComercialRegraPrioridadeTOMap.get(nmIndicadorComercial).getRegraPrioridadeAltaTO();
				indicadorComercialJSON.put("idRegraPrioridadeAlta", regraPrioridadeAltaTO != null ? regraPrioridadeAltaTO.getIdRegraPrioridadeAlta() : "");
				indicadorComercialJSON.put("dsRegraAlta", regraPrioridadeAltaTO != null ? regraPrioridadeAltaTO.getDsRegraAlta() : "");
				indicadorComercialJSONArray.put(indicadorComercialJSON);
			}
			//indicadorComercialJSONArray.put(indicadorComercialRegraPrioridadeTOMap);
			gerenciadorRegrasJSON.put("indicadorComercialJSONArray", indicadorComercialJSONArray);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String s = gerenciadorRegrasJSON.toString();
		return gerenciadorRegrasJSON.toString();
	}*/
	public String toString(){
		JSONObject gerenciadorRegrasJSON = new JSONObject();
		try {
			gerenciadorRegrasJSON.put("idCanalAtendimento", this.canalAtendimentoTO != null ? canalAtendimentoTO.getIdCanalAtendimento(): "" );
			gerenciadorRegrasJSON.put("idServico", this.idServico);
			gerenciadorRegrasJSON.put("nmServico", this.nmServico);
			gerenciadorRegrasJSON.put("inTipoFiltro", this.inTipoFiltro);
			JSONArray idIndicadorComercialJSONArray = new JSONArray();
			Set<String> nmIndicadorComercialSet = null;
			if(this.indicadorComercialRegraPrioridadeTOMap != null)
				nmIndicadorComercialSet = this.indicadorComercialRegraPrioridadeTOMap.keySet();
			else
				nmIndicadorComercialSet = new HashSet<String>();
			for(String nmIndicadorComercial: nmIndicadorComercialSet){
				idIndicadorComercialJSONArray.put(this.indicadorComercialRegraPrioridadeTOMap.get(nmIndicadorComercial).getIdIndicadorComercial());
			}
			gerenciadorRegrasJSON.put("idIndicadorComercialJSONArray", idIndicadorComercialJSONArray);
			JSONObject indicadorComercialRegraMapJSON = new JSONObject();
			for(String nmIndicadorComercial: nmIndicadorComercialSet){
				JSONObject indicadorComercialRegraTOJSON = new JSONObject();
				indicadorComercialRegraTOJSON.put("idIndicadorComercial", this.indicadorComercialRegraPrioridadeTOMap.get(nmIndicadorComercial).getIdIndicadorComercial());
				indicadorComercialRegraTOJSON.put("nmIndicadorComercial", this.indicadorComercialRegraPrioridadeTOMap.get(nmIndicadorComercial).getNmIndicadorComercial());
				RegraPrioridadeAltaTO regraPrioridadeAltaTO = this.indicadorComercialRegraPrioridadeTOMap.get(nmIndicadorComercial).getRegraPrioridadeAltaTO();
				if(regraPrioridadeAltaTO == null){
					regraPrioridadeAltaTO = new RegraPrioridadeAltaTO(new Long(0),new Long(0),""); 
				}
				indicadorComercialRegraTOJSON.put("idRegraPrioridadeAlta", regraPrioridadeAltaTO.getIdRegraPrioridadeAlta());
				indicadorComercialRegraTOJSON.put("dsRegraAlta", regraPrioridadeAltaTO.getDsRegraAlta());
				indicadorComercialRegraMapJSON.put(this.indicadorComercialRegraPrioridadeTOMap.get(nmIndicadorComercial).getIdIndicadorComercial().toString(), indicadorComercialRegraTOJSON);
			}
			gerenciadorRegrasJSON.put("indicadorComercialRegraMapJSON", indicadorComercialRegraMapJSON);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return gerenciadorRegrasJSON.toString();
	}
	public CanalAtendimentoTO getCanalAtendimentoTO() {
		return canalAtendimentoTO;
	}
	public void setCanalAtendimentoTO(CanalAtendimentoTO canalAtendimentoTO) {
		this.canalAtendimentoTO = canalAtendimentoTO;
	}
	public Integer getIdServico() {
		return idServico;
	}
	public void setIdServico(Integer idServico) {
		this.idServico = idServico;
	}
	public String getNmServico() {
		return nmServico;
	}
	public void setNmServico(String nmServico) {
		this.nmServico = nmServico;
	}
	public Long getInTipoFiltro() {
		return inTipoFiltro;
	}
	public void setInTipoFiltro(Long tipoFiltro) {
		this.inTipoFiltro = tipoFiltro;
	}
	public Map<String, IndicadorComercialRegraPrioridadeTO> getIndicadorComercialRegraPrioridadeTOMap() {
		return indicadorComercialRegraPrioridadeTOMap;
	}
	public void setIndicadorComercialRegraPrioridadeTOMap(
			Map<String, IndicadorComercialRegraPrioridadeTO> indicadorComercialRegraPrioridadeTOMap) {
		this.indicadorComercialRegraPrioridadeTOMap = indicadorComercialRegraPrioridadeTOMap;
	}
}
