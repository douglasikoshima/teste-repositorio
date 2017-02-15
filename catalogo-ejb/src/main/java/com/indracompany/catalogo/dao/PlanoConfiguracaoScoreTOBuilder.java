package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.AnaliseCredito;
import com.indracompany.catalogo.datalayer.CabecalhoAnaliseCredito;
import com.indracompany.catalogo.datalayer.PlanoCategoriaScore;
import com.indracompany.catalogo.datalayer.PlanoConfiguracaoScore;
import com.indracompany.catalogo.datalayer.Regional;
import com.indracompany.catalogo.to.AnaliseCreditoTO;
import com.indracompany.catalogo.to.PlanoConfiguracaoScoreTO;
import com.indracompany.catalogo.to.RegionalTO;

/**
 * @author Catalogo
 *
 */
public class PlanoConfiguracaoScoreTOBuilder {

	public PlanoConfiguracaoScore createPlanoConfiguracaoScore(PlanoConfiguracaoScoreTO to) {
		PlanoConfiguracaoScore bean = null;
		
		if(to != null) {
			bean = new PlanoConfiguracaoScore();
			bean.setIdPlanoConfiguracaoScore(to.getIdPlanoConfiguracaoScore());
			bean.setDtCriacao(to.getDtCriacao());
			bean.setNmUsuarioCriacao(to.getNmUsuarioCriacao());
			bean.setPlanoCategoriaScore( new PlanoCategoriaScore(to.getPlanoCategoriaScoreTO().getIdPlanoCategoriaScore()));
			bean.setAnaliseCredito(new AnaliseCredito(to.getAnaliseCreditoTO().getIdAnaliseCredito()));

			if(to.getRegionalTO() != null && to.getRegionalTO().getIdRegional() > 0){
				bean.setRegional(new Regional(to.getRegionalTO().getIdRegional(),to.getRegionalTO().getNmRegional()));
			}else{
				bean.setRegional(null);
			}
			
			CabecalhoAnaliseCredito cabecalhoAnaliseCredito = new CabecalhoAnaliseCredito();
			cabecalhoAnaliseCredito.setIdCabecalhoAnaliseCredito(to.getCabecalhoAnaliseCreditoTO().getIdCabecalhoAnaliseCredito());
			bean.setCabecalhoAnaliseCredito(cabecalhoAnaliseCredito);
		}
		
		return bean;
	}


	public PlanoConfiguracaoScoreTO createPlanoConfiguracaoScoreTO(PlanoConfiguracaoScore bean) {
		PlanoConfiguracaoScoreTO to = null;
		
		if(bean != null) {
			to = new PlanoConfiguracaoScoreTO();
			to.setIdPlanoConfiguracaoScore(bean.getIdPlanoConfiguracaoScore());
			to.setDtCriacao(bean.getDtCriacao());
			to.setNmUsuarioCriacao(bean.getNmUsuarioCriacao());
			to.setAnaliseCreditoTO(new AnaliseCreditoTO(bean.getAnaliseCredito().getIdAnaliseCredito()));

			if(bean.getRegional() !=null){			
			  to.setRegionalTO(new RegionalTO(bean.getRegional().getIdRegional(),bean.getRegional().getNmRegional()));
			}
			
			PlanoCategoriaScoreTOBuilder planoCategoriaScoreTOBuilder = new PlanoCategoriaScoreTOBuilder();
			to.setPlanoCategoriaScoreTO(planoCategoriaScoreTOBuilder.createCategorizacaoAnaliseCreditoTO(bean.getPlanoCategoriaScore()));
		}
		
		return to;
	}


	/**
	 * @param planoConfiguracaoScoreList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<PlanoConfiguracaoScoreTO> createPlanoConfiguracaoScoreTOList(List<PlanoConfiguracaoScore> beanList) {
		
		List<PlanoConfiguracaoScoreTO> toList = new ArrayList<PlanoConfiguracaoScoreTO>();
		
		if (beanList != null && beanList.size() > 0) {
			for (PlanoConfiguracaoScore bean : beanList) {
				toList.add(createPlanoConfiguracaoScoreTO(bean));
			}
		}
		
		return toList;
	}
	
	public List<PlanoConfiguracaoScore> createPlanoConfiguracaoScoreList(List<PlanoConfiguracaoScoreTO> toList) {
		List<PlanoConfiguracaoScore> entityList = new ArrayList<PlanoConfiguracaoScore>();
		
		if (toList != null && toList.size() > 0) {
			for (PlanoConfiguracaoScoreTO to : toList) {
				entityList.add(createPlanoConfiguracaoScore(to));
			}
		}
		
		return entityList;
	}
}
