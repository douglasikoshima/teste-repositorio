package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.AnaliseCredito;
import com.indracompany.catalogo.datalayer.CabecalhoAnaliseCredito;
import com.indracompany.catalogo.datalayer.Regional;
import com.indracompany.catalogo.datalayer.ServicoCategoriaScore;
import com.indracompany.catalogo.datalayer.ServicoConfiguracaoScore;
import com.indracompany.catalogo.to.AnaliseCreditoTO;
import com.indracompany.catalogo.to.RegionalTO;
import com.indracompany.catalogo.to.ServicoConfiguracaoScoreTO;

/**
 * @author Catalogo
 *
 */
public class ServicoConfiguracaoScoreTOBuilder {

	public ServicoConfiguracaoScore createServicoConfiguracaoScore(ServicoConfiguracaoScoreTO to) {
		ServicoConfiguracaoScore bean = null;
		
		if(to != null) {
			bean = new ServicoConfiguracaoScore();
			bean.setIdServicoConfiguracaoScore(to.getIdServicoConfiguracaoScore());
			bean.setDtCriacao(to.getDtCriacao());
			bean.setNmUsuarioCriacao(to.getNmUsuarioCriacao());
			bean.setServicoCategoriaScore( new ServicoCategoriaScore(to.getServicoCategoriaScoreTO().getIdServicoCategoriaScore()));
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


	public ServicoConfiguracaoScoreTO createServicoConfiguracaoScoreTO(ServicoConfiguracaoScore bean) {
		ServicoConfiguracaoScoreTO to = null;
		
		if(bean != null) {
			to = new ServicoConfiguracaoScoreTO();
			to.setIdServicoConfiguracaoScore(bean.getIdServicoConfiguracaoScore());
			to.setDtCriacao(bean.getDtCriacao());
			to.setNmUsuarioCriacao(bean.getNmUsuarioCriacao());
			
			ServicoCategoriaScoreTOBuilder servicoCategoriaScoreTOBuilder = new ServicoCategoriaScoreTOBuilder();
			to.setServicoCategoriaScoreTO(servicoCategoriaScoreTOBuilder.createCategorizacaoAnaliseCreditoTO(bean.getServicoCategoriaScore()));
			
			to.setAnaliseCreditoTO(new AnaliseCreditoTO(bean.getAnaliseCredito().getIdAnaliseCredito()));
			
			if(bean.getRegional() !=null){			
			  to.setRegionalTO(new RegionalTO(bean.getRegional().getIdRegional(),bean.getRegional().getNmRegional()));
			}
		}
		
		return to;
	}


	/**
	 * @param servicoConfiguracaoScoreList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<ServicoConfiguracaoScoreTO> createServicoConfiguracaoScoreTOList(List<ServicoConfiguracaoScore> beanList) {
		
		List<ServicoConfiguracaoScoreTO> toList = new ArrayList<ServicoConfiguracaoScoreTO>();
		
		if (beanList != null && beanList.size() > 0) {
			for (ServicoConfiguracaoScore bean : beanList) {
				toList.add(createServicoConfiguracaoScoreTO(bean));
			}
		}
		
		return toList;
	}
	
	public List<ServicoConfiguracaoScore> createServicoConfiguracaoScoreList(List<ServicoConfiguracaoScoreTO> toList) {
		
		List<ServicoConfiguracaoScore> entList = new ArrayList<ServicoConfiguracaoScore>();
		
		if (toList != null && toList.size() > 0) {
			for (ServicoConfiguracaoScoreTO to : toList) {
				entList.add(createServicoConfiguracaoScore(to));
			}
		}
		
		return entList;
	}
}
