package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.CategoriaScore;
import com.indracompany.catalogo.datalayer.OfertaServico;
import com.indracompany.catalogo.datalayer.OfertaServicoCategoriaScore;
import com.indracompany.catalogo.to.CategoriaScoreTO;
import com.indracompany.catalogo.to.OfertaServicoCategoriaScoreTO;

/**
 * @author Luiz Pereira
 *
 */
public class OfertaServicoCategoriaScoreTOBuilder {
	

	/**
	 * @param ofertaServicoCategoriaScoreTO
	 * @return
	 */
	public OfertaServicoCategoriaScore createOfertaServicoCategoriaScore(OfertaServicoCategoriaScoreTO ofertaServicoCategoriaScoreTO) {
		
		OfertaServicoCategoriaScore ofertaServicoCategoriaScore = null;
		
		if(ofertaServicoCategoriaScoreTO != null) {
			ofertaServicoCategoriaScore = new OfertaServicoCategoriaScore();
			ofertaServicoCategoriaScore.setCategoriaScore(new CategoriaScore(ofertaServicoCategoriaScoreTO.getCategoriaScoreTO().getIdCategoriaScore()));
			ofertaServicoCategoriaScore.setDtUsuarioCriacao(ofertaServicoCategoriaScoreTO.getDtUsuarioCriacao());
			ofertaServicoCategoriaScore.setNmUsuarioCriacao(ofertaServicoCategoriaScoreTO.getNmUsuarioCriacao());
			ofertaServicoCategoriaScore.setOfertaServico(new OfertaServico(ofertaServicoCategoriaScoreTO.getIdOfertaServico()));
		}
		
		return ofertaServicoCategoriaScore;
	}
	

	/**
	 * @param ofertaServicoCategoriaScore
	 * @return
	 */
	public OfertaServicoCategoriaScoreTO createOfertaServicoCategoriaScoreTO(OfertaServicoCategoriaScore ofertaServicoCategoriaScore) {
		
		OfertaServicoCategoriaScoreTO ofertaServicoCategoriaScoreTO = null;
		
		if(ofertaServicoCategoriaScore != null) {
			ofertaServicoCategoriaScoreTO = new OfertaServicoCategoriaScoreTO();
			ofertaServicoCategoriaScoreTO.setCategoriaScoreTO(new CategoriaScoreTO(ofertaServicoCategoriaScore.getCategoriaScore().getIdCategoriaScore()));
			ofertaServicoCategoriaScoreTO.setDtUsuarioCriacao(ofertaServicoCategoriaScore.getDtUsuarioCriacao());
			ofertaServicoCategoriaScoreTO.setNmUsuarioCriacao(ofertaServicoCategoriaScore.getNmUsuarioCriacao());
			ofertaServicoCategoriaScoreTO.setIdOfertaServico(ofertaServicoCategoriaScore.getOfertaServico().getIdOfertaServico());
			ofertaServicoCategoriaScoreTO.setNmOfertaServico(ofertaServicoCategoriaScore.getOfertaServico().getNmOfertaServico() + " - " + 
					ofertaServicoCategoriaScore.getOfertaServico().getCdOfertaServico());
			ofertaServicoCategoriaScoreTO.setIdOfertaServicoCategoriaScore(ofertaServicoCategoriaScore.getIdOfertaServicoCategoriaScore());
			
		}
		
		return ofertaServicoCategoriaScoreTO;
	}
	

	/**
	 * @param ofertaServicoCategoriaScoreList
	 * @return
	 */
	public List<OfertaServicoCategoriaScoreTO> createCategorizacaoAnaliseCreditoTOList(List<OfertaServicoCategoriaScore> ofertaServicoCategoriaScoreList) {
		
		List<OfertaServicoCategoriaScoreTO> ofertaServicoCategoriaScoreTOList = new ArrayList<OfertaServicoCategoriaScoreTO>();
		
		if(ofertaServicoCategoriaScoreList != null){
			for(OfertaServicoCategoriaScore ofertaServicoCategoriaScore : ofertaServicoCategoriaScoreList) {
				ofertaServicoCategoriaScoreTOList.add(createOfertaServicoCategoriaScoreTO(ofertaServicoCategoriaScore));
			}
		}
		return ofertaServicoCategoriaScoreTOList;
	}

}
