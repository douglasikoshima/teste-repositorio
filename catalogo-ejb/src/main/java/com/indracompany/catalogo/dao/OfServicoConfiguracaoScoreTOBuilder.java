package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.AnaliseCredito;
import com.indracompany.catalogo.datalayer.CabecalhoAnaliseCredito;
import com.indracompany.catalogo.datalayer.OfServicoConfiguracaoScore;
import com.indracompany.catalogo.datalayer.OfertaServicoCategoriaScore;
import com.indracompany.catalogo.datalayer.Regional;
import com.indracompany.catalogo.to.AnaliseCreditoTO;
import com.indracompany.catalogo.to.OfServicoConfiguracaoScoreTO;
import com.indracompany.catalogo.to.RegionalTO;

/**
 * @author Catalogo
 *
 */
public class OfServicoConfiguracaoScoreTOBuilder {

	public OfServicoConfiguracaoScore createOfServicoConfiguracaoScore(OfServicoConfiguracaoScoreTO to) {
		OfServicoConfiguracaoScore bean = null;
		
		if(to != null) {
			bean = new OfServicoConfiguracaoScore();
			bean.setIdOfServicoConfiguracaoScore(to.getIdOfServicoConfiguracaoScore());
			bean.setDtCriacao(to.getDtCriacao());
			bean.setNmUsuarioCriacao(to.getNmUsuarioCriacao());
			bean.setOfertaServicoCategoriaScore( new OfertaServicoCategoriaScore(to.getOfertaServicoCategoriaScoreTO().getIdOfertaServicoCategoriaScore()));
			bean.setAnaliseCredito(new AnaliseCredito(to.getAnaliseCreditoTO().getIdAnaliseCredito()));

			if(to.getRegionalTO() !=null){
			  bean.setRegional(new Regional(to.getRegionalTO().getIdRegional(),to.getRegionalTO().getNmRegional()));
			}
			
			CabecalhoAnaliseCredito cabecalhoAnaliseCredito = new CabecalhoAnaliseCredito();
			cabecalhoAnaliseCredito.setIdCabecalhoAnaliseCredito(to.getCabecalhoAnaliseCreditoTO().getIdCabecalhoAnaliseCredito());
			bean.setCabecalhoAnaliseCredito(cabecalhoAnaliseCredito);
		}
		
		return bean;
	}


	public OfServicoConfiguracaoScoreTO createOfServicoConfiguracaoScoreTO(OfServicoConfiguracaoScore bean) {
		OfServicoConfiguracaoScoreTO to = null;
		
		if(bean != null) {
			to = new OfServicoConfiguracaoScoreTO();
			to.setIdOfServicoConfiguracaoScore(bean.getIdOfServicoConfiguracaoScore());
			to.setDtCriacao(bean.getDtCriacao());
			to.setNmUsuarioCriacao(bean.getNmUsuarioCriacao());
			to.setAnaliseCreditoTO(new AnaliseCreditoTO(bean.getAnaliseCredito().getIdAnaliseCredito()));

			if(bean.getRegional() !=null){			
			  to.setRegionalTO(new RegionalTO(bean.getRegional().getIdRegional(),bean.getRegional().getNmRegional()));
			}
			
			OfertaServicoCategoriaScoreTOBuilder ofertaServicoCategoriaScoreTOBuilder = new OfertaServicoCategoriaScoreTOBuilder();
			to.setOfertaServicoCategoriaScoreTO(ofertaServicoCategoriaScoreTOBuilder.createOfertaServicoCategoriaScoreTO(bean.getOfertaServicoCategoriaScore()));
		}
		
		return to;
	}


	/**
	 * @param ofServicoConfiguracaoScoreList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<OfServicoConfiguracaoScoreTO> createOfServicoConfiguracaoScoreTOList(List<OfServicoConfiguracaoScore> beanList) {
		
		List<OfServicoConfiguracaoScoreTO> toList = new ArrayList<OfServicoConfiguracaoScoreTO>();
		
		if (beanList != null && beanList.size() > 0) {
			for (OfServicoConfiguracaoScore bean : beanList) {
				toList.add(createOfServicoConfiguracaoScoreTO(bean));
			}
		}
		
		return toList;
	}
	
	public List<OfServicoConfiguracaoScore> createOfServicoConfiguracaoScoreList(List<OfServicoConfiguracaoScoreTO> toList) {
		
		List<OfServicoConfiguracaoScore> entList = new ArrayList<OfServicoConfiguracaoScore>();
		
		if (toList != null && toList.size() > 0) {
			for (OfServicoConfiguracaoScoreTO to : toList) {
				entList.add(createOfServicoConfiguracaoScore(to));
			}
		}
		
		return entList;
	}
}
