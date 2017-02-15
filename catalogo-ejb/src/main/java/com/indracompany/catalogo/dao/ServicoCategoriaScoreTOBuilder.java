package com.indracompany.catalogo.dao;

import com.indracompany.catalogo.datalayer.CategoriaScore;
import com.indracompany.catalogo.datalayer.Servico;
import com.indracompany.catalogo.datalayer.ServicoCategoriaScore;
import com.indracompany.catalogo.to.CategoriaScoreTO;
import com.indracompany.catalogo.to.ServicoCategoriaScoreTO;

/**
 * @author Luiz Pereira
 *
 */
public class ServicoCategoriaScoreTOBuilder {

	/**
	 * @param servicoCategoriaScoreTO
	 * @return
	 */
	public ServicoCategoriaScore createCategorizacaoAnaliseCredito(ServicoCategoriaScoreTO servicoCategoriaScoreTO) {
		ServicoCategoriaScore servicoCategoriaScore = null;
		
		if(servicoCategoriaScoreTO != null) {
			servicoCategoriaScore = new ServicoCategoriaScore();
			servicoCategoriaScore.setCategoriaScore(new CategoriaScore(servicoCategoriaScoreTO.getCategoriaScoreTO().getIdCategoriaScore()));
			servicoCategoriaScore.setDtUsuarioCriacao(servicoCategoriaScoreTO.getDtUsuarioCriacao());
			servicoCategoriaScore.setNmUsuarioCriacao(servicoCategoriaScoreTO.getNmUsuarioCriacao());
			servicoCategoriaScore.setServico(new Servico(servicoCategoriaScoreTO.getIdServico()));
		}
		
		return servicoCategoriaScore;
	}
	
	
	public ServicoCategoriaScoreTO createCategorizacaoAnaliseCreditoTO(ServicoCategoriaScore  servicoCategoriaScore) {
		ServicoCategoriaScoreTO servicoCategoriaScoreTO = null;
		
		if(servicoCategoriaScore != null) {
			servicoCategoriaScoreTO = new ServicoCategoriaScoreTO();
			servicoCategoriaScoreTO.setCategoriaScoreTO(new CategoriaScoreTO(servicoCategoriaScore.getCategoriaScore().getIdCategoriaScore()));
			servicoCategoriaScoreTO.setDtUsuarioCriacao(servicoCategoriaScore.getDtUsuarioCriacao());
			servicoCategoriaScoreTO.setNmUsuarioCriacao(servicoCategoriaScore.getNmUsuarioCriacao());
			servicoCategoriaScoreTO.setIdServico(servicoCategoriaScore.getServico().getIdServico());
			servicoCategoriaScoreTO.setNmServico(servicoCategoriaScore.getServico().getNmComercial() + " - " + servicoCategoriaScore.getServico().getSistemaServico().getCdCodigo());			
			servicoCategoriaScoreTO.setIdServicoCategoriaScore(servicoCategoriaScore.getIdServicoCategoriaScore());
		}
		
		return servicoCategoriaScoreTO;
	}
}
