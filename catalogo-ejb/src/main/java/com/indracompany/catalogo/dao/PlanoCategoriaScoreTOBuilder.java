package com.indracompany.catalogo.dao;

import com.indracompany.catalogo.datalayer.CategoriaScore;
import com.indracompany.catalogo.datalayer.Plano;
import com.indracompany.catalogo.datalayer.PlanoCategoriaScore;
import com.indracompany.catalogo.to.CategoriaScoreTO;
import com.indracompany.catalogo.to.PlanoCategoriaScoreTO;

/**
 * @author Luiz Pereira
 *
 */
public class PlanoCategoriaScoreTOBuilder {

	/**
	 * @param planoCategoriaScoreTO
	 * @return
	 */
	public PlanoCategoriaScore createCategorizacaoAnaliseCredito(PlanoCategoriaScoreTO planoCategoriaScoreTO) {
		PlanoCategoriaScore planoCategoriaScore = null;
		
		if(planoCategoriaScoreTO != null) {
			planoCategoriaScore = new PlanoCategoriaScore();
			planoCategoriaScore.setCategoriaScore(new CategoriaScore(planoCategoriaScoreTO.getCategoriaScoreTO().getIdCategoriaScore()));
			planoCategoriaScore.setDtUsuarioCriacao(planoCategoriaScoreTO.getDtUsuarioCriacao());
			planoCategoriaScore.setNmUsuarioCriacao(planoCategoriaScoreTO.getNmUsuarioCriacao());
			planoCategoriaScore.setPlano(new Plano(planoCategoriaScoreTO.getIdPlano()));
		}
		
		return planoCategoriaScore;
	}
	
	public PlanoCategoriaScoreTO createCategorizacaoAnaliseCreditoTO(PlanoCategoriaScore planoCategoriaScore) {
		PlanoCategoriaScoreTO planoCategoriaScoreTO = null;
		
		if(planoCategoriaScore != null) {
			planoCategoriaScoreTO = new PlanoCategoriaScoreTO();
			planoCategoriaScoreTO.setCategoriaScoreTO(new CategoriaScoreTO(planoCategoriaScore.getCategoriaScore().getIdCategoriaScore()));
			planoCategoriaScoreTO.setDtUsuarioCriacao(planoCategoriaScore.getDtUsuarioCriacao());
			planoCategoriaScoreTO.setNmUsuarioCriacao(planoCategoriaScore.getNmUsuarioCriacao());
			planoCategoriaScoreTO.setIdPlano(planoCategoriaScore.getPlano().getIdPlano());
			planoCategoriaScoreTO.setNmPlano(planoCategoriaScore.getPlano().getNmComercial() + " - " + planoCategoriaScore.getPlano().getSistemaPlano().getCdCodigo());			
			planoCategoriaScoreTO.setIdPlanoCategoriaScore(planoCategoriaScore.getIdPlanoCategoriaScore());
		}
		
		return planoCategoriaScoreTO;
	}
}
